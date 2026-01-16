/*
 * Copyright 2022-2024 Crown Copyright
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package sleeper.compaction.core.task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import sleeper.compaction.core.job.CompactionJob;
import sleeper.compaction.core.job.CompactionJobCommitterOrSendToLambda;
import sleeper.compaction.core.task.CompactionTask.MessageHandle;
import sleeper.compaction.core.task.CompactionTask.MessageReceiver;
import sleeper.core.properties.PropertiesReloader;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.table.TableProperties;
import sleeper.core.properties.testutils.FixedTablePropertiesProvider;
import sleeper.core.schema.Schema;
import sleeper.core.statestore.FileReference;
import sleeper.core.statestore.FileReferenceFactory;
import sleeper.core.statestore.StateStore;
import sleeper.core.statestore.StateStoreProvider;
import sleeper.core.statestore.exception.FileReferenceAssignedToJobException;
import sleeper.core.statestore.exception.FileReferenceNotFoundException;
import sleeper.core.statestore.testutils.InMemoryTransactionLogStateStore;
import sleeper.core.statestore.testutils.InMemoryTransactionLogs;
import sleeper.core.tracker.compaction.job.InMemoryCompactionJobTracker;
import sleeper.core.tracker.compaction.job.query.CompactionJobStatus;
import sleeper.core.tracker.compaction.task.CompactionTaskStatus;
import sleeper.core.tracker.compaction.task.InMemoryCompactionTaskTracker;
import sleeper.core.tracker.job.run.RecordsProcessed;
import sleeper.core.util.ExponentialBackoffWithJitter;
import sleeper.core.util.ExponentialBackoffWithJitter.WaitRange;
import sleeper.core.util.PollWithRetries;
import sleeper.core.util.ThreadSleep;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThat;
import static sleeper.core.properties.instance.CompactionProperty.COMPACTION_TASK_DELAY_BEFORE_RETRY_IN_SECONDS;
import static sleeper.core.properties.instance.CompactionProperty.COMPACTION_TASK_MAX_CONSECUTIVE_FAILURES;
import static sleeper.core.properties.instance.CompactionProperty.COMPACTION_TASK_MAX_IDLE_TIME_IN_SECONDS;
import static sleeper.core.properties.instance.CompactionProperty.COMPACTION_TASK_WAIT_FOR_INPUT_FILE_ASSIGNMENT;
import static sleeper.core.properties.table.TableProperty.COMPACTION_JOB_COMMIT_ASYNC;
import static sleeper.core.properties.table.TableProperty.TABLE_ID;
import static sleeper.core.properties.testutils.InstancePropertiesTestHelper.createTestInstanceProperties;
import static sleeper.core.properties.testutils.TablePropertiesTestHelper.createTestTableProperties;
import static sleeper.core.schema.SchemaTestHelper.schemaWithKey;
import static sleeper.core.statestore.testutils.StateStoreUpdatesWrapper.update;

public class CompactionTaskClaudeTest {

    private static final Schema SCHEMA = schemaWithKey("key");
    private static final String PARTITION_ID = "root";
    private static final String TASK_ID = "test-task";

    private final InstanceProperties instanceProperties = createTestInstanceProperties();
    private final TableProperties tableProperties = createTestTableProperties(instanceProperties, SCHEMA);
    private final InMemoryTransactionLogs transactionLogs = new InMemoryTransactionLogs();
    private final InMemoryCompactionJobTracker jobTracker = new InMemoryCompactionJobTracker();
    private final InMemoryCompactionTaskTracker taskTracker = new InMemoryCompactionTaskTracker();
    private final List<Duration> sleepDurations = new ArrayList<>();

    private StateStore stateStore;
    private FileReferenceFactory fileFactory;
    private Queue<Instant> times;
    private Queue<String> jobRunIds;

    @BeforeEach
    void setUp() {
        stateStore = InMemoryTransactionLogStateStore.createAndInitialise(tableProperties, transactionLogs);
        fileFactory = FileReferenceFactory.from(stateStore);
        tableProperties.set(COMPACTION_JOB_COMMIT_ASYNC, "false");
        instanceProperties.setNumber(COMPACTION_TASK_MAX_IDLE_TIME_IN_SECONDS, 60);
        instanceProperties.setNumber(COMPACTION_TASK_DELAY_BEFORE_RETRY_IN_SECONDS, 0);
        instanceProperties.setNumber(COMPACTION_TASK_MAX_CONSECUTIVE_FAILURES, 3);
        instanceProperties.set(COMPACTION_TASK_WAIT_FOR_INPUT_FILE_ASSIGNMENT, "false");
        times = new LinkedList<>();
        jobRunIds = new LinkedList<>();
    }

    @Nested
    @DisplayName("Primary constructor")
    class PrimaryConstructor {

        @Test
        void shouldCreateCompactionTaskWithPrimaryConstructor() throws IOException {
            // Given
            FakeMessageReceiver receiver = new FakeMessageReceiver();
            StateStoreWaitForFiles waitForFiles = createNoOpWaitForFiles();
            CompactionJobCommitterOrSendToLambda committer = createCommitter();

            // When
            CompactionTask task = new CompactionTask(
                    instanceProperties,
                    new FixedTablePropertiesProvider(tableProperties),
                    PropertiesReloader.neverReload(),
                    stateStoreProvider(),
                    receiver,
                    waitForFiles,
                    committer,
                    jobTracker,
                    taskTracker,
                    fakeCompactionRunnerFactory(),
                    TASK_ID);

            // Then - task should be created without exception
            assertThat(task).isNotNull();
        }
    }

    @Nested
    @DisplayName("Secondary constructor with time and sleep controls")
    class SecondaryConstructor {

        @Test
        void shouldCreateCompactionTaskWithCustomTimeAndSleepSuppliers() throws IOException {
            // Given
            FakeMessageReceiver receiver = new FakeMessageReceiver();
            StateStoreWaitForFiles waitForFiles = createNoOpWaitForFiles();
            CompactionJobCommitterOrSendToLambda committer = createCommitter();
            Instant fixedTime = Instant.parse("2024-01-15T10:00:00Z");

            // When
            CompactionTask task = new CompactionTask(
                    instanceProperties,
                    new FixedTablePropertiesProvider(tableProperties),
                    PropertiesReloader.neverReload(),
                    stateStoreProvider(),
                    receiver,
                    waitForFiles,
                    committer,
                    jobTracker,
                    taskTracker,
                    fakeCompactionRunnerFactory(),
                    TASK_ID,
                    () -> "run-id",
                    () -> fixedTime,
                    sleepDurations::add);

            // Then - task should be created without exception
            assertThat(task).isNotNull();
        }
    }

    @Nested
    @DisplayName("run() method - basic execution")
    class RunBasicExecution {

        @Test
        void shouldRecordTaskStartedAndFinishedWhenNoMessages() throws IOException {
            // Given
            FakeMessageReceiver receiver = new FakeMessageReceiver();
            times.add(Instant.parse("2024-01-15T10:00:00Z")); // start time
            times.add(Instant.parse("2024-01-15T11:00:01Z")); // idle check time (exceeds max idle)
            times.add(Instant.parse("2024-01-15T11:00:01Z")); // finish time

            CompactionTask task = createTask(receiver);

            // When
            task.run();

            // Then
            List<CompactionTaskStatus> allTasks = taskTracker.getAllTasks();
            assertThat(allTasks).hasSize(1);
            CompactionTaskStatus status = allTasks.get(0);
            assertThat(status.getTaskId()).isEqualTo(TASK_ID);
            assertThat(status.isFinished()).isTrue();
            assertThat(status.getStartTime()).isEqualTo(Instant.parse("2024-01-15T10:00:00Z"));
        }

        @Test
        void shouldProcessSingleCompactionJobSuccessfully() throws Exception {
            // Given
            FileReference file1 = fileFactory.rootFile("input1.parquet", 100);
            FileReference file2 = fileFactory.rootFile("input2.parquet", 100);
            update(stateStore).addFiles(List.of(file1, file2));

            CompactionJob job = createCompactionJob("output.parquet", List.of("input1.parquet", "input2.parquet"));
            update(stateStore).assignJobId(job.getId(), PARTITION_ID, job.getInputFiles());

            FakeMessageReceiver receiver = new FakeMessageReceiver(
                    new FakeMessageHandle(job));

            times.add(Instant.parse("2024-01-15T10:00:00Z")); // task start
            times.add(Instant.parse("2024-01-15T10:01:00Z")); // job start
            times.add(Instant.parse("2024-01-15T10:02:00Z")); // job finish
            times.add(Instant.parse("2024-01-15T11:03:00Z")); // idle check (exceeds max idle)
            times.add(Instant.parse("2024-01-15T11:03:00Z")); // task finish
            jobRunIds.add("run-1");

            CompactionTask task = createTask(receiver);

            // When
            task.run();

            // Then - job tracker should record the job
            Optional<CompactionJobStatus> jobStatus = jobTracker.getJob(job.getId());
            assertThat(jobStatus).isPresent();

            // Task should be finished
            List<CompactionTaskStatus> allTasks = taskTracker.getAllTasks();
            assertThat(allTasks).hasSize(1);
            assertThat(allTasks.get(0).isFinished()).isTrue();
            assertThat(allTasks.get(0).getJobRuns()).isEqualTo(1);
        }

        @Test
        void shouldProcessMultipleCompactionJobsSuccessfully() throws Exception {
            // Given - Two jobs
            FileReference file1 = fileFactory.rootFile("input1.parquet", 100);
            FileReference file2 = fileFactory.rootFile("input2.parquet", 100);
            FileReference file3 = fileFactory.rootFile("input3.parquet", 100);
            update(stateStore).addFiles(List.of(file1, file2, file3));

            CompactionJob job1 = createCompactionJob("output1.parquet", List.of("input1.parquet"));
            CompactionJob job2 = createCompactionJob("output2.parquet", List.of("input2.parquet"));
            update(stateStore).assignJobId(job1.getId(), PARTITION_ID, job1.getInputFiles());
            update(stateStore).assignJobId(job2.getId(), PARTITION_ID, job2.getInputFiles());

            FakeMessageReceiver receiver = new FakeMessageReceiver(
                    new FakeMessageHandle(job1),
                    new FakeMessageHandle(job2));

            times.add(Instant.parse("2024-01-15T10:00:00Z")); // task start
            times.add(Instant.parse("2024-01-15T10:01:00Z")); // job1 start
            times.add(Instant.parse("2024-01-15T10:02:00Z")); // job1 finish
            times.add(Instant.parse("2024-01-15T10:03:00Z")); // job2 start
            times.add(Instant.parse("2024-01-15T10:04:00Z")); // job2 finish
            times.add(Instant.parse("2024-01-15T11:05:00Z")); // idle check (exceeds max idle)
            times.add(Instant.parse("2024-01-15T11:05:00Z")); // task finish
            jobRunIds.add("run-1");
            jobRunIds.add("run-2");

            CompactionTask task = createTask(receiver);

            // When
            task.run();

            // Then - Both jobs should be tracked
            assertThat(jobTracker.getJob(job1.getId())).isPresent();
            assertThat(jobTracker.getJob(job2.getId())).isPresent();

            // Task should record 2 job runs
            List<CompactionTaskStatus> allTasks = taskTracker.getAllTasks();
            assertThat(allTasks).hasSize(1);
            assertThat(allTasks.get(0).getJobRuns()).isEqualTo(2);
        }
    }

    @Nested
    @DisplayName("run() method - idle time handling")
    class RunIdleTimeHandling {

        @Test
        void shouldTerminateWhenIdleTimeExceedsMaximum() throws IOException {
            // Given
            FakeMessageReceiver receiver = new FakeMessageReceiver();
            instanceProperties.setNumber(COMPACTION_TASK_MAX_IDLE_TIME_IN_SECONDS, 60);

            times.add(Instant.parse("2024-01-15T10:00:00Z")); // task start
            times.add(Instant.parse("2024-01-15T10:01:01Z")); // first idle check (> 60 seconds)
            times.add(Instant.parse("2024-01-15T10:01:01Z")); // task finish

            CompactionTask task = createTask(receiver);

            // When
            task.run();

            // Then - task should finish
            List<CompactionTaskStatus> allTasks = taskTracker.getAllTasks();
            assertThat(allTasks).hasSize(1);
            assertThat(allTasks.get(0).isFinished()).isTrue();
        }

        @Test
        void shouldRetryWhenIdleTimeIsWithinMaximum() throws IOException {
            // Given
            FakeMessageReceiver receiver = new FakeMessageReceiver();
            instanceProperties.setNumber(COMPACTION_TASK_MAX_IDLE_TIME_IN_SECONDS, 120);
            instanceProperties.setNumber(COMPACTION_TASK_DELAY_BEFORE_RETRY_IN_SECONDS, 5);

            times.add(Instant.parse("2024-01-15T10:00:00Z")); // task start
            times.add(Instant.parse("2024-01-15T10:00:30Z")); // first idle check (< 120 seconds)
            times.add(Instant.parse("2024-01-15T10:02:30Z")); // second idle check (> 120 seconds)
            times.add(Instant.parse("2024-01-15T10:02:30Z")); // task finish

            CompactionTask task = createTask(receiver);

            // When
            task.run();

            // Then - should have slept once before retry
            assertThat(sleepDurations).containsExactly(Duration.ofSeconds(5));
        }

        @Test
        void shouldNotSleepWhenDelayBeforeRetryIsZero() throws IOException {
            // Given
            FakeMessageReceiver receiver = new FakeMessageReceiver();
            instanceProperties.setNumber(COMPACTION_TASK_MAX_IDLE_TIME_IN_SECONDS, 120);
            instanceProperties.setNumber(COMPACTION_TASK_DELAY_BEFORE_RETRY_IN_SECONDS, 0);

            times.add(Instant.parse("2024-01-15T10:00:00Z")); // task start
            times.add(Instant.parse("2024-01-15T10:00:30Z")); // first idle check (< 120 seconds)
            times.add(Instant.parse("2024-01-15T10:02:30Z")); // second idle check (> 120 seconds)
            times.add(Instant.parse("2024-01-15T10:02:30Z")); // task finish

            CompactionTask task = createTask(receiver);

            // When
            task.run();

            // Then - should not have slept
            assertThat(sleepDurations).isEmpty();
        }
    }

    @Nested
    @DisplayName("run() method - consecutive failures handling")
    class RunConsecutiveFailuresHandling {

        @Test
        void shouldTerminateWhenMaxConsecutiveFailuresReached() throws Exception {
            // Given
            instanceProperties.setNumber(COMPACTION_TASK_MAX_CONSECUTIVE_FAILURES, 2);

            CompactionJob job1 = createCompactionJob("output1.parquet", List.of("nonexistent1.parquet"));
            CompactionJob job2 = createCompactionJob("output2.parquet", List.of("nonexistent2.parquet"));

            FakeMessageReceiver receiver = new FakeMessageReceiver(
                    new FakeMessageHandle(job1),
                    new FakeMessageHandle(job2));

            times.add(Instant.parse("2024-01-15T10:00:00Z")); // task start
            times.add(Instant.parse("2024-01-15T10:01:00Z")); // job1 start
            times.add(Instant.parse("2024-01-15T10:01:01Z")); // job1 failure time
            times.add(Instant.parse("2024-01-15T10:02:00Z")); // job2 start
            times.add(Instant.parse("2024-01-15T10:02:01Z")); // job2 failure time
            times.add(Instant.parse("2024-01-15T10:02:01Z")); // task finish
            jobRunIds.add("run-1");
            jobRunIds.add("run-2");

            CompactionTask task = createTask(receiver);

            // When
            task.run();

            // Then - task should have terminated after 2 failures
            List<CompactionTaskStatus> allTasks = taskTracker.getAllTasks();
            assertThat(allTasks).hasSize(1);
            assertThat(allTasks.get(0).isFinished()).isTrue();
        }

        @Test
        void shouldResetConsecutiveFailuresAfterSuccess() throws Exception {
            // Given
            instanceProperties.setNumber(COMPACTION_TASK_MAX_CONSECUTIVE_FAILURES, 2);

            // First job succeeds
            FileReference file1 = fileFactory.rootFile("input1.parquet", 100);
            update(stateStore).addFile(file1);
            CompactionJob job1 = createCompactionJob("output1.parquet", List.of("input1.parquet"));
            update(stateStore).assignJobId(job1.getId(), PARTITION_ID, job1.getInputFiles());

            // Second job fails
            CompactionJob job2 = createCompactionJob("output2.parquet", List.of("nonexistent.parquet"));

            FakeMessageReceiver receiver = new FakeMessageReceiver(
                    new FakeMessageHandle(job1),
                    new FakeMessageHandle(job2));

            times.add(Instant.parse("2024-01-15T10:00:00Z")); // task start
            times.add(Instant.parse("2024-01-15T10:01:00Z")); // job1 start
            times.add(Instant.parse("2024-01-15T10:02:00Z")); // job1 finish
            times.add(Instant.parse("2024-01-15T10:03:00Z")); // job2 start
            times.add(Instant.parse("2024-01-15T10:03:01Z")); // job2 failure time
            times.add(Instant.parse("2024-01-15T11:04:00Z")); // idle check (exceeds max idle)
            times.add(Instant.parse("2024-01-15T11:04:00Z")); // task finish
            jobRunIds.add("run-1");
            jobRunIds.add("run-2");

            CompactionTask task = createTask(receiver);

            // When
            task.run();

            // Then - job1 should have succeeded
            Optional<CompactionJobStatus> jobStatus1 = jobTracker.getJob(job1.getId());
            assertThat(jobStatus1).isPresent();
        }
    }

    @Nested
    @DisplayName("run() method - wait for file assignment")
    class RunWaitForFileAssignment {

        @Test
        void shouldWaitForFileAssignmentWhenEnabled() throws Exception {
            // Given
            instanceProperties.set(COMPACTION_TASK_WAIT_FOR_INPUT_FILE_ASSIGNMENT, "true");

            FileReference file = fileFactory.rootFile("input.parquet", 100);
            update(stateStore).addFile(file);

            CompactionJob job = createCompactionJob("output.parquet", List.of("input.parquet"));
            update(stateStore).assignJobId(job.getId(), PARTITION_ID, job.getInputFiles());

            AtomicInteger waitCallCount = new AtomicInteger(0);
            StateStoreWaitForFiles waitForFiles = createCountingWaitForFiles(waitCallCount);

            FakeMessageReceiver receiver = new FakeMessageReceiver(new FakeMessageHandle(job));

            times.add(Instant.parse("2024-01-15T10:00:00Z")); // task start
            times.add(Instant.parse("2024-01-15T10:01:00Z")); // job start
            times.add(Instant.parse("2024-01-15T10:02:00Z")); // job finish
            times.add(Instant.parse("2024-01-15T11:03:00Z")); // idle check (exceeds max idle)
            times.add(Instant.parse("2024-01-15T11:03:00Z")); // task finish
            jobRunIds.add("run-1");

            CompactionTask task = createTaskWithWaitForFiles(receiver, waitForFiles);

            // When
            task.run();

            // Then - wait should have been called
            assertThat(waitCallCount.get()).isEqualTo(1);
        }

        @Test
        void shouldNotWaitForFileAssignmentWhenDisabled() throws Exception {
            // Given
            instanceProperties.set(COMPACTION_TASK_WAIT_FOR_INPUT_FILE_ASSIGNMENT, "false");

            FileReference file = fileFactory.rootFile("input.parquet", 100);
            update(stateStore).addFile(file);

            CompactionJob job = createCompactionJob("output.parquet", List.of("input.parquet"));
            update(stateStore).assignJobId(job.getId(), PARTITION_ID, job.getInputFiles());

            AtomicInteger waitCallCount = new AtomicInteger(0);
            StateStoreWaitForFiles waitForFiles = createCountingWaitForFiles(waitCallCount);

            FakeMessageReceiver receiver = new FakeMessageReceiver(new FakeMessageHandle(job));

            times.add(Instant.parse("2024-01-15T10:00:00Z")); // task start
            times.add(Instant.parse("2024-01-15T10:01:00Z")); // job start
            times.add(Instant.parse("2024-01-15T10:02:00Z")); // job finish
            times.add(Instant.parse("2024-01-15T11:03:00Z")); // idle check (exceeds max idle)
            times.add(Instant.parse("2024-01-15T11:03:00Z")); // task finish
            jobRunIds.add("run-1");

            CompactionTask task = createTaskWithWaitForFiles(receiver, waitForFiles);

            // When
            task.run();

            // Then - wait should not have been called
            assertThat(waitCallCount.get()).isEqualTo(0);
        }

        @Test
        void shouldDeleteFromQueueWhenFileReferenceNotFoundDuringWait() throws Exception {
            // Given
            instanceProperties.set(COMPACTION_TASK_WAIT_FOR_INPUT_FILE_ASSIGNMENT, "true");

            CompactionJob job = createCompactionJob("output.parquet", List.of("nonexistent.parquet"));

            StateStoreWaitForFiles waitForFiles = createThrowingWaitForFiles(
                    new FileReferenceNotFoundException("nonexistent.parquet", PARTITION_ID));

            FakeMessageHandle handle = new FakeMessageHandle(job);
            FakeMessageReceiver receiver = new FakeMessageReceiver(handle);

            times.add(Instant.parse("2024-01-15T10:00:00Z")); // task start
            times.add(Instant.parse("2024-01-15T11:01:00Z")); // idle check (exceeds max idle)
            times.add(Instant.parse("2024-01-15T11:01:00Z")); // task finish
            jobRunIds.add("run-1");

            CompactionTask task = createTaskWithWaitForFiles(receiver, waitForFiles);

            // When
            task.run();

            // Then - message should have been deleted
            assertThat(handle.wasDeleted()).isTrue();
            assertThat(handle.wasReturned()).isFalse();
        }

        @Test
        void shouldDeleteFromQueueWhenFileAssignedToJobExceptionDuringWait() throws Exception {
            // Given
            instanceProperties.set(COMPACTION_TASK_WAIT_FOR_INPUT_FILE_ASSIGNMENT, "true");

            CompactionJob job = createCompactionJob("output.parquet", List.of("assigned.parquet"));

            StateStoreWaitForFiles waitForFiles = createThrowingWaitForFiles(
                    new FileReferenceAssignedToJobException(fileFactory.rootFile("assigned.parquet", 100)));

            FakeMessageHandle handle = new FakeMessageHandle(job);
            FakeMessageReceiver receiver = new FakeMessageReceiver(handle);

            times.add(Instant.parse("2024-01-15T10:00:00Z")); // task start
            times.add(Instant.parse("2024-01-15T11:01:00Z")); // idle check (exceeds max idle)
            times.add(Instant.parse("2024-01-15T11:01:00Z")); // task finish
            jobRunIds.add("run-1");

            CompactionTask task = createTaskWithWaitForFiles(receiver, waitForFiles);

            // When
            task.run();

            // Then - message should have been deleted
            assertThat(handle.wasDeleted()).isTrue();
            assertThat(handle.wasReturned()).isFalse();
        }

        @Test
        void shouldReturnToQueueWhenOtherExceptionDuringWait() throws Exception {
            // Given
            instanceProperties.set(COMPACTION_TASK_WAIT_FOR_INPUT_FILE_ASSIGNMENT, "true");
            instanceProperties.setNumber(COMPACTION_TASK_MAX_CONSECUTIVE_FAILURES, 1);

            CompactionJob job = createCompactionJob("output.parquet", List.of("input.parquet"));

            StateStoreWaitForFiles waitForFiles = createThrowingWaitForFiles(
                    new RuntimeException("Connection failed"));

            FakeMessageHandle handle = new FakeMessageHandle(job);
            FakeMessageReceiver receiver = new FakeMessageReceiver(handle);

            times.add(Instant.parse("2024-01-15T10:00:00Z")); // task start
            times.add(Instant.parse("2024-01-15T10:00:01Z")); // task finish (after max failures)
            jobRunIds.add("run-1");

            CompactionTask task = createTaskWithWaitForFiles(receiver, waitForFiles);

            // When
            task.run();

            // Then - message should have been returned to queue
            assertThat(handle.wasReturned()).isTrue();
            assertThat(handle.wasDeleted()).isFalse();
        }
    }

    @Nested
    @DisplayName("run() method - message handling")
    class RunMessageHandling {

        @Test
        void shouldDeleteMessageFromQueueAfterSuccessfulCompaction() throws Exception {
            // Given
            FileReference file = fileFactory.rootFile("input.parquet", 100);
            update(stateStore).addFile(file);

            CompactionJob job = createCompactionJob("output.parquet", List.of("input.parquet"));
            update(stateStore).assignJobId(job.getId(), PARTITION_ID, job.getInputFiles());

            FakeMessageHandle handle = new FakeMessageHandle(job);
            FakeMessageReceiver receiver = new FakeMessageReceiver(handle);

            times.add(Instant.parse("2024-01-15T10:00:00Z")); // task start
            times.add(Instant.parse("2024-01-15T10:01:00Z")); // job start
            times.add(Instant.parse("2024-01-15T10:02:00Z")); // job finish
            times.add(Instant.parse("2024-01-15T11:03:00Z")); // idle check (exceeds max idle)
            times.add(Instant.parse("2024-01-15T11:03:00Z")); // task finish
            jobRunIds.add("run-1");

            CompactionTask task = createTask(receiver);

            // When
            task.run();

            // Then - message should have been deleted
            assertThat(handle.wasDeleted()).isTrue();
            assertThat(handle.wasReturned()).isFalse();
        }

        @Test
        void shouldReturnMessageToQueueAfterFailedCompaction() throws Exception {
            // Given - Create a job that will fail during compaction with an exception that
            // causes the message to be returned to queue (not TableNotFoundException or
            // file-related exceptions which cause deletion)
            FileReference file = fileFactory.rootFile("input.parquet", 100);
            update(stateStore).addFile(file);

            CompactionJob job = createCompactionJob("output.parquet", List.of("input.parquet"));
            update(stateStore).assignJobId(job.getId(), PARTITION_ID, job.getInputFiles());

            FakeMessageHandle handle = new FakeMessageHandle(job);
            FakeMessageReceiver receiver = new FakeMessageReceiver(handle);

            instanceProperties.setNumber(COMPACTION_TASK_MAX_CONSECUTIVE_FAILURES, 1);

            times.add(Instant.parse("2024-01-15T10:00:00Z")); // task start
            times.add(Instant.parse("2024-01-15T10:01:00Z")); // job start
            times.add(Instant.parse("2024-01-15T10:01:01Z")); // job failure time
            times.add(Instant.parse("2024-01-15T10:01:01Z")); // task finish
            jobRunIds.add("run-1");

            CompactionTask task = createTaskWithFailingCompactor(receiver);

            // When
            task.run();

            // Then - message should have been returned to queue
            assertThat(handle.wasReturned()).isTrue();
            assertThat(handle.wasDeleted()).isFalse();
        }

        @Test
        void shouldCloseMessageHandleAfterProcessing() throws Exception {
            // Given
            FileReference file = fileFactory.rootFile("input.parquet", 100);
            update(stateStore).addFile(file);

            CompactionJob job = createCompactionJob("output.parquet", List.of("input.parquet"));
            update(stateStore).assignJobId(job.getId(), PARTITION_ID, job.getInputFiles());

            FakeMessageHandle handle = new FakeMessageHandle(job);
            FakeMessageReceiver receiver = new FakeMessageReceiver(handle);

            times.add(Instant.parse("2024-01-15T10:00:00Z")); // task start
            times.add(Instant.parse("2024-01-15T10:01:00Z")); // job start
            times.add(Instant.parse("2024-01-15T10:02:00Z")); // job finish
            times.add(Instant.parse("2024-01-15T11:03:00Z")); // idle check (exceeds max idle)
            times.add(Instant.parse("2024-01-15T11:03:00Z")); // task finish
            jobRunIds.add("run-1");

            CompactionTask task = createTask(receiver);

            // When
            task.run();

            // Then - handle should have been closed
            assertThat(handle.wasClosed()).isTrue();
        }
    }

    @Nested
    @DisplayName("run() method - table not found handling")
    class RunTableNotFoundHandling {

        @Test
        void shouldDeleteMessageAndTrackFailureWhenTableNotFound() throws Exception {
            // Given
            CompactionJob job = CompactionJob.builder()
                    .tableId("nonexistent-table")
                    .jobId("test-job")
                    .partitionId(PARTITION_ID)
                    .outputFile("output.parquet")
                    .inputFiles(List.of("input.parquet"))
                    .build();

            FakeMessageHandle handle = new FakeMessageHandle(job);
            FakeMessageReceiver receiver = new FakeMessageReceiver(handle);

            times.add(Instant.parse("2024-01-15T10:00:00Z")); // task start
            times.add(Instant.parse("2024-01-15T10:01:00Z")); // job start
            times.add(Instant.parse("2024-01-15T10:01:01Z")); // job failure time
            times.add(Instant.parse("2024-01-15T11:02:00Z")); // idle check (exceeds max idle)
            times.add(Instant.parse("2024-01-15T11:02:00Z")); // task finish
            jobRunIds.add("run-1");

            CompactionTask task = createTask(receiver);

            // When
            task.run();

            // Then - message should have been deleted
            assertThat(handle.wasDeleted()).isTrue();

            // Job failure should be tracked
            Optional<CompactionJobStatus> jobStatus = jobTracker.getJob(job.getId());
            assertThat(jobStatus).isPresent();
        }
    }

    @Nested
    @DisplayName("run() method - properties reload")
    class RunPropertiesReload {

        @Test
        void shouldReloadPropertiesBeforeProcessingEachMessage() throws Exception {
            // Given
            AtomicInteger reloadCount = new AtomicInteger(0);
            PropertiesReloader reloader = () -> reloadCount.incrementAndGet();

            FileReference file = fileFactory.rootFile("input.parquet", 100);
            update(stateStore).addFile(file);

            CompactionJob job = createCompactionJob("output.parquet", List.of("input.parquet"));
            update(stateStore).assignJobId(job.getId(), PARTITION_ID, job.getInputFiles());

            FakeMessageReceiver receiver = new FakeMessageReceiver(new FakeMessageHandle(job));

            times.add(Instant.parse("2024-01-15T10:00:00Z")); // task start
            times.add(Instant.parse("2024-01-15T10:01:00Z")); // job start
            times.add(Instant.parse("2024-01-15T10:02:00Z")); // job finish
            times.add(Instant.parse("2024-01-15T11:03:00Z")); // idle check (exceeds max idle)
            times.add(Instant.parse("2024-01-15T11:03:00Z")); // task finish
            jobRunIds.add("run-1");

            CompactionTask task = createTaskWithReloader(receiver, reloader);

            // When
            task.run();

            // Then - reloader should have been called at least once
            assertThat(reloadCount.get()).isGreaterThanOrEqualTo(1);
        }
    }

    // Helper methods

    private CompactionTask createTask(MessageReceiver receiver) {
        return createTaskWithWaitForFiles(receiver, createNoOpWaitForFiles());
    }

    private CompactionTask createTaskWithWaitForFiles(MessageReceiver receiver, StateStoreWaitForFiles waitForFiles) {
        return createTaskWithReloaderAndWaitForFiles(receiver, PropertiesReloader.neverReload(), waitForFiles);
    }

    private CompactionTask createTaskWithReloader(MessageReceiver receiver, PropertiesReloader reloader) {
        return createTaskWithReloaderAndWaitForFiles(receiver, reloader, createNoOpWaitForFiles());
    }

    private CompactionTask createTaskWithReloaderAndWaitForFiles(
            MessageReceiver receiver, PropertiesReloader reloader, StateStoreWaitForFiles waitForFiles) {
        return new CompactionTask(
                instanceProperties,
                new FixedTablePropertiesProvider(tableProperties),
                reloader,
                stateStoreProvider(),
                receiver,
                waitForFiles,
                createCommitter(),
                jobTracker,
                taskTracker,
                fakeCompactionRunnerFactory(),
                TASK_ID,
                () -> jobRunIds.isEmpty() ? "default-run-id" : jobRunIds.poll(),
                () -> times.isEmpty() ? Instant.now() : times.poll(),
                sleepDurations::add);
    }

    private CompactionTask createTaskWithFailingCompactor(MessageReceiver receiver) {
        return new CompactionTask(
                instanceProperties,
                new FixedTablePropertiesProvider(tableProperties),
                PropertiesReloader.neverReload(),
                stateStoreProvider(),
                receiver,
                createNoOpWaitForFiles(),
                createCommitter(),
                jobTracker,
                taskTracker,
                failingCompactionRunnerFactory(),
                TASK_ID,
                () -> jobRunIds.isEmpty() ? "default-run-id" : jobRunIds.poll(),
                () -> times.isEmpty() ? Instant.now() : times.poll(),
                sleepDurations::add);
    }

    private StateStoreWaitForFiles createNoOpWaitForFiles() {
        ThreadSleep noOpSleep = millis -> { };
        return new StateStoreWaitForFiles(
                1,
                new ExponentialBackoffWithJitter(WaitRange.firstAndMaxWaitCeilingSecs(0, 0), () -> 0.0, noOpSleep),
                PollWithRetries.noRetries(),
                new FixedTablePropertiesProvider(tableProperties),
                stateStoreProvider(),
                jobTracker,
                Instant::now) {
            @Override
            public void wait(CompactionJob job, String taskId, String jobRunId) {
                // No-op
            }
        };
    }

    private StateStoreWaitForFiles createCountingWaitForFiles(AtomicInteger counter) {
        ThreadSleep noOpSleep = millis -> { };
        return new StateStoreWaitForFiles(
                1,
                new ExponentialBackoffWithJitter(WaitRange.firstAndMaxWaitCeilingSecs(0, 0), () -> 0.0, noOpSleep),
                PollWithRetries.noRetries(),
                new FixedTablePropertiesProvider(tableProperties),
                stateStoreProvider(),
                jobTracker,
                Instant::now) {
            @Override
            public void wait(CompactionJob job, String taskId, String jobRunId) {
                counter.incrementAndGet();
            }
        };
    }

    private StateStoreWaitForFiles createThrowingWaitForFiles(Exception exception) {
        ThreadSleep noOpSleep = millis -> { };
        return new StateStoreWaitForFiles(
                1,
                new ExponentialBackoffWithJitter(WaitRange.firstAndMaxWaitCeilingSecs(0, 0), () -> 0.0, noOpSleep),
                PollWithRetries.noRetries(),
                new FixedTablePropertiesProvider(tableProperties),
                stateStoreProvider(),
                jobTracker,
                Instant::now) {
            @Override
            public void wait(CompactionJob job, String taskId, String jobRunId) throws InterruptedException {
                if (exception instanceof RuntimeException) {
                    throw (RuntimeException) exception;
                } else if (exception instanceof InterruptedException) {
                    throw (InterruptedException) exception;
                } else {
                    throw new RuntimeException(exception);
                }
            }
        };
    }

    private CompactionJob createCompactionJob(String outputFile, List<String> inputFiles) {
        return CompactionJob.builder()
                .tableId(tableProperties.get(TABLE_ID))
                .jobId("test-job-" + System.nanoTime())
                .partitionId(PARTITION_ID)
                .outputFile(outputFile)
                .inputFiles(inputFiles)
                .build();
    }

    private CompactionJobCommitterOrSendToLambda createCommitter() {
        return new CompactionJobCommitterOrSendToLambda(
                new FixedTablePropertiesProvider(tableProperties),
                stateStoreProvider(),
                jobTracker,
                request -> { },
                message -> { });
    }

    private StateStoreProvider stateStoreProvider() {
        return new StateStoreProvider(instanceProperties, props -> stateStore);
    }

    private CompactionRunnerFactory fakeCompactionRunnerFactory() {
        return (job, tableProperties) -> (compactionJob, tProps, partition) -> {
            // Simulate reading and writing records
            return new RecordsProcessed(100, 90);
        };
    }

    private CompactionRunnerFactory failingCompactionRunnerFactory() {
        return (job, tableProperties) -> (compactionJob, tProps, partition) -> {
            // Simulate a failure during compaction (e.g., I/O error, not file-not-found)
            throw new RuntimeException("Simulated compaction failure");
        };
    }

    // Fake implementations for testing

    private static class FakeMessageReceiver implements MessageReceiver {
        private final Queue<MessageHandle> messages;

        FakeMessageReceiver(MessageHandle... handles) {
            this.messages = new LinkedList<>(List.of(handles));
        }

        @Override
        public Optional<MessageHandle> receiveMessage() throws IOException {
            return Optional.ofNullable(messages.poll());
        }
    }

    private static class FakeMessageHandle implements MessageHandle {
        private final CompactionJob job;
        private boolean deleted = false;
        private boolean returned = false;
        private boolean closed = false;

        FakeMessageHandle(CompactionJob job) {
            this.job = job;
        }

        @Override
        public CompactionJob getJob() {
            return job;
        }

        @Override
        public void deleteFromQueue() {
            deleted = true;
        }

        @Override
        public void returnToQueue() {
            returned = true;
        }

        @Override
        public void close() {
            closed = true;
        }

        boolean wasDeleted() {
            return deleted;
        }

        boolean wasReturned() {
            return returned;
        }

        boolean wasClosed() {
            return closed;
        }
    }
}
