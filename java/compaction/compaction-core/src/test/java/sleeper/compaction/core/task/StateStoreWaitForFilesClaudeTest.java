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
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.table.TableProperties;
import sleeper.core.properties.testutils.FixedTablePropertiesProvider;
import sleeper.core.schema.Schema;
import sleeper.core.statestore.FileReference;
import sleeper.core.statestore.FileReferenceFactory;
import sleeper.core.statestore.StateStore;
import sleeper.core.statestore.StateStoreProvider;
import sleeper.core.statestore.testutils.InMemoryTransactionLogStateStore;
import sleeper.core.statestore.testutils.InMemoryTransactionLogs;
import sleeper.core.tracker.compaction.job.InMemoryCompactionJobTracker;
import sleeper.core.tracker.compaction.job.query.CompactionJobStatus;
import sleeper.core.util.ExponentialBackoffWithJitter;
import sleeper.core.util.ExponentialBackoffWithJitter.WaitRange;
import sleeper.core.util.PollWithRetries;
import sleeper.core.util.ThreadSleep;

import java.time.Instant;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static sleeper.core.properties.table.TableProperty.TABLE_ID;
import static sleeper.core.properties.testutils.InstancePropertiesTestHelper.createTestInstanceProperties;
import static sleeper.core.properties.testutils.TablePropertiesTestHelper.createTestTableProperties;
import static sleeper.core.schema.SchemaTestHelper.schemaWithKey;
import static sleeper.core.statestore.testutils.StateStoreUpdatesWrapper.update;

public class StateStoreWaitForFilesClaudeTest {

    private static final Schema SCHEMA = schemaWithKey("key");
    private static final String PARTITION_ID = "root";
    private static final String TASK_ID = "test-task";
    private static final String JOB_RUN_ID = "test-run";

    private final InstanceProperties instanceProperties = createTestInstanceProperties();
    private final TableProperties tableProperties = createTestTableProperties(instanceProperties, SCHEMA);
    private final InMemoryTransactionLogs transactionLogs = new InMemoryTransactionLogs();
    private final InMemoryCompactionJobTracker jobTracker = new InMemoryCompactionJobTracker();
    private final List<Long> sleepDurations = new ArrayList<>();
    private final ThreadSleep recordingSleep = sleepDurations::add;

    private StateStore stateStore;
    private FileReferenceFactory fileFactory;
    private Queue<Instant> times;

    @BeforeEach
    void setUp() {
        stateStore = InMemoryTransactionLogStateStore.createAndInitialise(tableProperties, transactionLogs);
        fileFactory = FileReferenceFactory.from(stateStore);
        times = new LinkedList<>();
    }

    @Nested
    @DisplayName("Primary constructor")
    class PrimaryConstructor {

        @Test
        void shouldCreateWithDefaultSettings() {
            // When
            StateStoreWaitForFiles waitForFiles = new StateStoreWaitForFiles(
                    new FixedTablePropertiesProvider(tableProperties),
                    stateStoreProvider(),
                    jobTracker);

            // Then - should be created without exception
            assertThat(waitForFiles).isNotNull();
        }
    }

    @Nested
    @DisplayName("Secondary constructor with full dependency injection")
    class SecondaryConstructor {

        @Test
        void shouldCreateWithCustomSettings() {
            // Given
            ExponentialBackoffWithJitter backoff = new ExponentialBackoffWithJitter(
                    WaitRange.firstAndMaxWaitCeilingSecs(1, 10), () -> 0.5, recordingSleep);
            PollWithRetries throttlingRetries = PollWithRetries.noRetries();
            Instant fixedTime = Instant.parse("2024-01-15T10:00:00Z");

            // When
            StateStoreWaitForFiles waitForFiles = new StateStoreWaitForFiles(
                    5,
                    backoff,
                    throttlingRetries,
                    new FixedTablePropertiesProvider(tableProperties),
                    stateStoreProvider(),
                    jobTracker,
                    () -> fixedTime);

            // Then - should be created without exception
            assertThat(waitForFiles).isNotNull();
        }
    }

    @Nested
    @DisplayName("wait() method - files already assigned")
    class WaitFilesAlreadyAssigned {

        @Test
        void shouldSucceedImmediatelyWhenFilesAlreadyAssigned() throws Exception {
            // Given - files are already assigned to the job
            FileReference file1 = fileFactory.rootFile("input1.parquet", 100);
            FileReference file2 = fileFactory.rootFile("input2.parquet", 200);
            update(stateStore).addFiles(List.of(file1, file2));

            CompactionJob job = createCompactionJob("output.parquet", List.of("input1.parquet", "input2.parquet"));
            update(stateStore).assignJobId(job.getId(), PARTITION_ID, job.getInputFiles());

            times.add(Instant.parse("2024-01-15T10:00:00Z"));
            StateStoreWaitForFiles waitForFiles = createWaitForFiles(3);

            // When
            waitForFiles.wait(job, TASK_ID, JOB_RUN_ID);

            // Then - should complete without any backoff waits
            assertThat(sleepDurations).isEmpty();
        }

        @Test
        void shouldSucceedImmediatelyWithSingleFile() throws Exception {
            // Given - single file already assigned
            FileReference file = fileFactory.rootFile("input.parquet", 100);
            update(stateStore).addFile(file);

            CompactionJob job = createCompactionJob("output.parquet", List.of("input.parquet"));
            update(stateStore).assignJobId(job.getId(), PARTITION_ID, job.getInputFiles());

            times.add(Instant.parse("2024-01-15T10:00:00Z"));
            StateStoreWaitForFiles waitForFiles = createWaitForFiles(3);

            // When
            waitForFiles.wait(job, TASK_ID, JOB_RUN_ID);

            // Then - should complete without any backoff waits
            assertThat(sleepDurations).isEmpty();
        }
    }

    @Nested
    @DisplayName("wait() method - timeout when maximum attempts exceeded")
    class WaitTimeoutExceeded {

        @Test
        void shouldThrowExceptionWhenMaximumAttemptsExceeded() throws Exception {
            // Given - files never assigned
            FileReference file = fileFactory.rootFile("input.parquet", 100);
            update(stateStore).addFile(file);

            CompactionJob job = createCompactionJob("output.parquet", List.of("input.parquet"));
            // Files not assigned to job

            times.add(Instant.parse("2024-01-15T10:00:00Z"));
            times.add(Instant.parse("2024-01-15T10:00:01Z")); // failure time
            StateStoreWaitForFiles waitForFiles = createWaitForFiles(3);

            // When / Then
            assertThatThrownBy(() -> waitForFiles.wait(job, TASK_ID, JOB_RUN_ID))
                    .isInstanceOf(TimedOutWaitingForFileAssignmentsException.class)
                    .hasMessageContaining("Too many retries waiting for input files to be assigned");
        }

        @Test
        void shouldRecordFailedJobInTrackerWhenTimedOut() throws Exception {
            // Given - files never assigned
            FileReference file = fileFactory.rootFile("input.parquet", 100);
            update(stateStore).addFile(file);

            CompactionJob job = createCompactionJob("output.parquet", List.of("input.parquet"));
            // Files not assigned to job

            times.add(Instant.parse("2024-01-15T10:00:00Z")); // start time
            times.add(Instant.parse("2024-01-15T10:00:30Z")); // failure time
            StateStoreWaitForFiles waitForFiles = createWaitForFiles(3);

            // When
            try {
                waitForFiles.wait(job, TASK_ID, JOB_RUN_ID);
            } catch (TimedOutWaitingForFileAssignmentsException e) {
                // Expected
            }

            // Then - job failure should be tracked
            Optional<CompactionJobStatus> jobStatus = jobTracker.getJob(job.getId());
            assertThat(jobStatus).isPresent();
            assertThat(jobStatus.get().isStarted()).isTrue();
            assertThat(jobStatus.get().isAnyRunFailed()).isTrue();
        }

        @Test
        void shouldUseBackoffBetweenAttempts() throws Exception {
            // Given - files never assigned
            FileReference file = fileFactory.rootFile("input.parquet", 100);
            update(stateStore).addFile(file);

            CompactionJob job = createCompactionJob("output.parquet", List.of("input.parquet"));

            times.add(Instant.parse("2024-01-15T10:00:00Z"));
            times.add(Instant.parse("2024-01-15T10:00:30Z"));
            StateStoreWaitForFiles waitForFiles = createWaitForFiles(3);

            // When
            try {
                waitForFiles.wait(job, TASK_ID, JOB_RUN_ID);
            } catch (TimedOutWaitingForFileAssignmentsException e) {
                // Expected
            }

            // Then - should have used backoff (with 3 attempts, 2 sleep waits)
            assertThat(sleepDurations).hasSize(2);
        }
    }

    @Nested
    @DisplayName("wait() method - with single attempt")
    class WaitSingleAttempt {

        @Test
        void shouldSucceedWithSingleAttemptWhenFilesAssigned() throws Exception {
            // Given - files already assigned
            FileReference file = fileFactory.rootFile("input.parquet", 100);
            update(stateStore).addFile(file);

            CompactionJob job = createCompactionJob("output.parquet", List.of("input.parquet"));
            update(stateStore).assignJobId(job.getId(), PARTITION_ID, job.getInputFiles());

            times.add(Instant.parse("2024-01-15T10:00:00Z"));
            StateStoreWaitForFiles waitForFiles = createWaitForFiles(1);

            // When
            waitForFiles.wait(job, TASK_ID, JOB_RUN_ID);

            // Then - should complete without any backoff waits
            assertThat(sleepDurations).isEmpty();
        }

        @Test
        void shouldFailWithSingleAttemptWhenFilesNotAssigned() throws Exception {
            // Given - files not assigned
            FileReference file = fileFactory.rootFile("input.parquet", 100);
            update(stateStore).addFile(file);

            CompactionJob job = createCompactionJob("output.parquet", List.of("input.parquet"));
            // Not assigned

            times.add(Instant.parse("2024-01-15T10:00:00Z"));
            times.add(Instant.parse("2024-01-15T10:00:01Z"));
            StateStoreWaitForFiles waitForFiles = createWaitForFiles(1);

            // When / Then
            assertThatThrownBy(() -> waitForFiles.wait(job, TASK_ID, JOB_RUN_ID))
                    .isInstanceOf(TimedOutWaitingForFileAssignmentsException.class);
        }
    }

    @Nested
    @DisplayName("wait() method - job tracker interaction")
    class WaitJobTrackerInteraction {

        @Test
        void shouldRecordStartedAndFailedEventsWhenTimedOut() throws Exception {
            // Given
            FileReference file = fileFactory.rootFile("input.parquet", 100);
            update(stateStore).addFile(file);

            CompactionJob job = createCompactionJob("output.parquet", List.of("input.parquet"));

            Instant startTime = Instant.parse("2024-01-15T10:00:00Z");
            Instant failureTime = Instant.parse("2024-01-15T10:00:30Z");
            times.add(startTime);
            times.add(failureTime);
            StateStoreWaitForFiles waitForFiles = createWaitForFiles(3);

            // When
            try {
                waitForFiles.wait(job, TASK_ID, JOB_RUN_ID);
            } catch (TimedOutWaitingForFileAssignmentsException e) {
                // Expected
            }

            // Then
            Optional<CompactionJobStatus> jobStatus = jobTracker.getJob(job.getId());
            assertThat(jobStatus).isPresent();
            assertThat(jobStatus.get().isStarted()).isTrue();
            assertThat(jobStatus.get().isAnyRunFailed()).isTrue();
        }

        @Test
        void shouldNotRecordAnyEventsOnSuccess() throws Exception {
            // Given
            FileReference file = fileFactory.rootFile("input.parquet", 100);
            update(stateStore).addFile(file);

            CompactionJob job = createCompactionJob("output.parquet", List.of("input.parquet"));
            update(stateStore).assignJobId(job.getId(), PARTITION_ID, job.getInputFiles());

            times.add(Instant.parse("2024-01-15T10:00:00Z"));
            StateStoreWaitForFiles waitForFiles = createWaitForFiles(3);

            // When
            waitForFiles.wait(job, TASK_ID, JOB_RUN_ID);

            // Then - no job events should be recorded on success
            Optional<CompactionJobStatus> jobStatus = jobTracker.getJob(job.getId());
            assertThat(jobStatus).isEmpty();
        }
    }

    @Nested
    @DisplayName("wait() method - no-op first backoff attempt")
    class WaitNoOpFirstBackoffAttempt {

        @Test
        void shouldNotSleepOnFirstAttempt() throws Exception {
            // Given - files already assigned (succeeds on first attempt)
            FileReference file = fileFactory.rootFile("input.parquet", 100);
            update(stateStore).addFile(file);

            CompactionJob job = createCompactionJob("output.parquet", List.of("input.parquet"));
            update(stateStore).assignJobId(job.getId(), PARTITION_ID, job.getInputFiles());

            times.add(Instant.parse("2024-01-15T10:00:00Z"));
            StateStoreWaitForFiles waitForFiles = createWaitForFiles(5);

            // When
            waitForFiles.wait(job, TASK_ID, JOB_RUN_ID);

            // Then - no sleep on first attempt
            assertThat(sleepDurations).isEmpty();
        }
    }

    // Helper methods

    private CompactionJob createCompactionJob(String outputFile, List<String> inputFiles) {
        return CompactionJob.builder()
                .tableId(tableProperties.get(TABLE_ID))
                .jobId("test-job-" + System.nanoTime())
                .partitionId(PARTITION_ID)
                .outputFile(outputFile)
                .inputFiles(inputFiles)
                .build();
    }

    private StateStoreProvider stateStoreProvider() {
        return new StateStoreProvider(instanceProperties, props -> stateStore);
    }

    private StateStoreWaitForFiles createWaitForFiles(int maxAttempts) {
        ExponentialBackoffWithJitter backoff = new ExponentialBackoffWithJitter(
                WaitRange.firstAndMaxWaitCeilingSecs(1, 10), () -> 0.5, recordingSleep);
        PollWithRetries throttlingRetries = PollWithRetries.noRetries();

        return new StateStoreWaitForFiles(
                maxAttempts,
                backoff,
                throttlingRetries,
                new FixedTablePropertiesProvider(tableProperties),
                stateStoreProvider(),
                jobTracker,
                () -> times.isEmpty() ? Instant.now() : times.poll());
    }
}
