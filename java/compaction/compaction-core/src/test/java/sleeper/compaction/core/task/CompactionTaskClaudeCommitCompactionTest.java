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
import sleeper.core.statestore.exception.FileNotFoundException;
import sleeper.core.statestore.exception.FileReferenceNotAssignedToJobException;
import sleeper.core.statestore.testutils.InMemoryTransactionLogStateStore;
import sleeper.core.statestore.testutils.InMemoryTransactionLogs;
import sleeper.core.tracker.compaction.job.InMemoryCompactionJobTracker;
import sleeper.core.tracker.compaction.job.query.CompactionJobStatus;
import sleeper.core.tracker.compaction.job.update.CompactionJobFinishedEvent;
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

/**
 * Tests for the commitCompaction method in CompactionTask.
 * This class tests the uncovered lines 215-217 where commit fails with a non-file-related exception.
 */
public class CompactionTaskClaudeCommitCompactionTest {

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
    @DisplayName("Commit failure handling")
    class CommitFailureHandling {

        @Test
        void shouldReturnMessageToQueueWhenCommitFailsWithNonFileException() throws Exception {
            // Given - A job that will successfully compact but fail during commit with a
            // non-file-related exception (e.g., network error, timeout, etc.)
            FileReference file = fileFactory.rootFile("input.parquet", 100);
            update(stateStore).addFile(file);

            CompactionJob job = createCompactionJob("output.parquet", List.of("input.parquet"));
            update(stateStore).assignJobId(job.getId(), PARTITION_ID, job.getInputFiles());

            FakeMessageHandle handle = new FakeMessageHandle(job);
            FakeMessageReceiver receiver = new FakeMessageReceiver(handle);

            instanceProperties.setNumber(COMPACTION_TASK_MAX_CONSECUTIVE_FAILURES, 1);

            times.add(Instant.parse("2024-01-15T10:00:00Z")); // task start
            times.add(Instant.parse("2024-01-15T10:01:00Z")); // job start
            times.add(Instant.parse("2024-01-15T10:02:00Z")); // job finish
            times.add(Instant.parse("2024-01-15T10:02:01Z")); // failure time
            times.add(Instant.parse("2024-01-15T10:02:01Z")); // task finish
            jobRunIds.add("run-1");

            // Use a committer that fails with a RuntimeException (not file-related)
            CompactionTask task = createTaskWithFailingCommitter(receiver,
                    new RuntimeException("Network connection failed"));

            // When
            task.run();

            // Then - message should have been returned to queue (lines 215-217)
            assertThat(handle.wasReturned()).isTrue();
            assertThat(handle.wasDeleted()).isFalse();

            // Job failure should be tracked
            Optional<CompactionJobStatus> jobStatus = jobTracker.getJob(job.getId());
            assertThat(jobStatus).isPresent();
        }

        @Test
        void shouldReturnMessageToQueueWhenCommitFailsWithIOException() throws Exception {
            // Given - A job that will fail during commit with an IOException
            FileReference file = fileFactory.rootFile("input.parquet", 100);
            update(stateStore).addFile(file);

            CompactionJob job = createCompactionJob("output.parquet", List.of("input.parquet"));
            update(stateStore).assignJobId(job.getId(), PARTITION_ID, job.getInputFiles());

            FakeMessageHandle handle = new FakeMessageHandle(job);
            FakeMessageReceiver receiver = new FakeMessageReceiver(handle);

            instanceProperties.setNumber(COMPACTION_TASK_MAX_CONSECUTIVE_FAILURES, 1);

            times.add(Instant.parse("2024-01-15T10:00:00Z")); // task start
            times.add(Instant.parse("2024-01-15T10:01:00Z")); // job start
            times.add(Instant.parse("2024-01-15T10:02:00Z")); // job finish
            times.add(Instant.parse("2024-01-15T10:02:01Z")); // failure time
            times.add(Instant.parse("2024-01-15T10:02:01Z")); // task finish
            jobRunIds.add("run-1");

            // Use a committer that fails with an IOException wrapped in RuntimeException
            CompactionTask task = createTaskWithFailingCommitter(receiver,
                    new RuntimeException("Commit failed", new IOException("Disk full")));

            // When
            task.run();

            // Then - message should have been returned to queue (lines 215-217)
            assertThat(handle.wasReturned()).isTrue();
            assertThat(handle.wasDeleted()).isFalse();
        }

        @Test
        void shouldIncrementConsecutiveFailuresWhenCommitFails() throws Exception {
            // Given - Two jobs where the first fails during commit with a non-file exception
            FileReference file1 = fileFactory.rootFile("input1.parquet", 100);
            FileReference file2 = fileFactory.rootFile("input2.parquet", 100);
            update(stateStore).addFiles(List.of(file1, file2));

            CompactionJob job1 = createCompactionJob("output1.parquet", List.of("input1.parquet"));
            CompactionJob job2 = createCompactionJob("output2.parquet", List.of("input2.parquet"));
            update(stateStore).assignJobId(job1.getId(), PARTITION_ID, job1.getInputFiles());
            update(stateStore).assignJobId(job2.getId(), PARTITION_ID, job2.getInputFiles());

            FakeMessageHandle handle1 = new FakeMessageHandle(job1);
            FakeMessageHandle handle2 = new FakeMessageHandle(job2);
            FakeMessageReceiver receiver = new FakeMessageReceiver(handle1, handle2);

            // Set max consecutive failures to 2 so task terminates after 2 commit failures
            instanceProperties.setNumber(COMPACTION_TASK_MAX_CONSECUTIVE_FAILURES, 2);

            times.add(Instant.parse("2024-01-15T10:00:00Z")); // task start
            times.add(Instant.parse("2024-01-15T10:01:00Z")); // job1 start
            times.add(Instant.parse("2024-01-15T10:02:00Z")); // job1 compact finish
            times.add(Instant.parse("2024-01-15T10:02:01Z")); // job1 commit failure time
            times.add(Instant.parse("2024-01-15T10:03:00Z")); // job2 start
            times.add(Instant.parse("2024-01-15T10:04:00Z")); // job2 compact finish
            times.add(Instant.parse("2024-01-15T10:04:01Z")); // job2 commit failure time
            times.add(Instant.parse("2024-01-15T10:04:01Z")); // task finish
            jobRunIds.add("run-1");
            jobRunIds.add("run-2");

            // Both commits fail with non-file exceptions
            CompactionTask task = createTaskWithFailingCommitter(receiver,
                    new RuntimeException("Database unavailable"));

            // When
            task.run();

            // Then - both messages should have been returned to queue
            assertThat(handle1.wasReturned()).isTrue();
            assertThat(handle2.wasReturned()).isTrue();

            // Task should have terminated due to consecutive failures
            List<CompactionTaskStatus> allTasks = taskTracker.getAllTasks();
            assertThat(allTasks).hasSize(1);
            assertThat(allTasks.get(0).isFinished()).isTrue();
        }

        @Test
        void shouldDeleteFromQueueWhenCommitFailsWithFileNotFoundException() throws Exception {
            // Given - A job where commit fails with FileNotFoundException as cause
            FileReference file = fileFactory.rootFile("input.parquet", 100);
            update(stateStore).addFile(file);

            CompactionJob job = createCompactionJob("output.parquet", List.of("input.parquet"));
            update(stateStore).assignJobId(job.getId(), PARTITION_ID, job.getInputFiles());

            FakeMessageHandle handle = new FakeMessageHandle(job);
            FakeMessageReceiver receiver = new FakeMessageReceiver(handle);

            instanceProperties.setNumber(COMPACTION_TASK_MAX_CONSECUTIVE_FAILURES, 1);

            times.add(Instant.parse("2024-01-15T10:00:00Z")); // task start
            times.add(Instant.parse("2024-01-15T10:01:00Z")); // job start
            times.add(Instant.parse("2024-01-15T10:02:00Z")); // job finish
            times.add(Instant.parse("2024-01-15T10:02:01Z")); // failure time
            times.add(Instant.parse("2024-01-15T11:03:00Z")); // idle check (exceeds max idle)
            times.add(Instant.parse("2024-01-15T11:03:00Z")); // task finish
            jobRunIds.add("run-1");

            // Committer fails with FileNotFoundException as cause - should delete, not return
            CompactionTask task = createTaskWithFailingCommitter(receiver,
                    new RuntimeException("Commit failed", new FileNotFoundException("input.parquet")));

            // When
            task.run();

            // Then - message should have been DELETED (not returned) due to FileNotFoundException
            assertThat(handle.wasDeleted()).isTrue();
            assertThat(handle.wasReturned()).isFalse();
        }

        @Test
        void shouldDeleteFromQueueWhenCommitFailsWithFileReferenceNotAssignedToJobException() throws Exception {
            // Given - A job where commit fails with FileReferenceNotAssignedToJobException as cause
            FileReference file = fileFactory.rootFile("input.parquet", 100);
            update(stateStore).addFile(file);

            CompactionJob job = createCompactionJob("output.parquet", List.of("input.parquet"));
            update(stateStore).assignJobId(job.getId(), PARTITION_ID, job.getInputFiles());

            FakeMessageHandle handle = new FakeMessageHandle(job);
            FakeMessageReceiver receiver = new FakeMessageReceiver(handle);

            instanceProperties.setNumber(COMPACTION_TASK_MAX_CONSECUTIVE_FAILURES, 1);

            times.add(Instant.parse("2024-01-15T10:00:00Z")); // task start
            times.add(Instant.parse("2024-01-15T10:01:00Z")); // job start
            times.add(Instant.parse("2024-01-15T10:02:00Z")); // job finish
            times.add(Instant.parse("2024-01-15T10:02:01Z")); // failure time
            times.add(Instant.parse("2024-01-15T11:03:00Z")); // idle check (exceeds max idle)
            times.add(Instant.parse("2024-01-15T11:03:00Z")); // task finish
            jobRunIds.add("run-1");

            // Committer fails with FileReferenceNotAssignedToJobException - should delete
            CompactionTask task = createTaskWithFailingCommitter(receiver,
                    new RuntimeException("Commit failed",
                            new FileReferenceNotAssignedToJobException(file, job.getId())));

            // When
            task.run();

            // Then - message should have been DELETED due to FileReferenceNotAssignedToJobException
            assertThat(handle.wasDeleted()).isTrue();
            assertThat(handle.wasReturned()).isFalse();
        }
    }

    // Helper methods

    private CompactionTask createTaskWithFailingCommitter(MessageReceiver receiver, Exception exceptionToThrow) {
        return new CompactionTask(
                instanceProperties,
                new FixedTablePropertiesProvider(tableProperties),
                PropertiesReloader.neverReload(),
                stateStoreProvider(),
                receiver,
                createNoOpWaitForFiles(),
                createFailingCommitter(exceptionToThrow),
                jobTracker,
                taskTracker,
                fakeCompactionRunnerFactory(),
                TASK_ID,
                () -> jobRunIds.isEmpty() ? "default-run-id" : jobRunIds.poll(),
                () -> times.isEmpty() ? Instant.now() : times.poll(),
                sleepDurations::add);
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

    private CompactionJobCommitterOrSendToLambda createFailingCommitter(Exception exceptionToThrow) {
        return new CompactionJobCommitterOrSendToLambda(
                new FixedTablePropertiesProvider(tableProperties),
                stateStoreProvider(),
                jobTracker,
                request -> { },
                message -> { }) {
            @Override
            public void commit(CompactionJob job, CompactionJobFinishedEvent finishedEvent) {
                // Track that the job finished before throwing
                jobTracker.jobFinished(finishedEvent);
                if (exceptionToThrow instanceof RuntimeException) {
                    throw (RuntimeException) exceptionToThrow;
                } else {
                    throw new RuntimeException(exceptionToThrow);
                }
            }
        };
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

    private StateStoreProvider stateStoreProvider() {
        return new StateStoreProvider(instanceProperties, props -> stateStore);
    }

    private CompactionRunnerFactory fakeCompactionRunnerFactory() {
        return (job, tableProperties) -> (compactionJob, tProps, partition) -> {
            // Simulate reading and writing records
            return new RecordsProcessed(100, 90);
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
