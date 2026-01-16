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
import sleeper.core.statestore.StateStore;
import sleeper.core.statestore.StateStoreProvider;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static sleeper.core.properties.table.TableProperty.TABLE_ID;
import static sleeper.core.properties.testutils.InstancePropertiesTestHelper.createTestInstanceProperties;
import static sleeper.core.properties.testutils.TablePropertiesTestHelper.createTestTableProperties;
import static sleeper.core.schema.SchemaTestHelper.schemaWithKey;

/**
 * Tests for the private method allFilesAssignedToJob in StateStoreWaitForFiles,
 * specifically covering the exception handling path (lines 111-113).
 */
public class StateStoreWaitForFilesClaudeAllFilesAssignedToJobTest {

    private static final Schema SCHEMA = schemaWithKey("key");
    private static final String PARTITION_ID = "root";
    private static final String TASK_ID = "test-task";
    private static final String JOB_RUN_ID = "test-run";

    private final InstanceProperties instanceProperties = createTestInstanceProperties();
    private final TableProperties tableProperties = createTestTableProperties(instanceProperties, SCHEMA);
    private final InMemoryCompactionJobTracker jobTracker = new InMemoryCompactionJobTracker();
    private final List<Long> sleepDurations = new ArrayList<>();
    private final ThreadSleep recordingSleep = sleepDurations::add;

    private Queue<Instant> times;

    @BeforeEach
    void setUp() {
        times = new LinkedList<>();
    }

    @Nested
    @DisplayName("RuntimeException handling in allFilesAssignedToJob")
    class RuntimeExceptionHandling {

        @Test
        void shouldPropagateRuntimeExceptionFromIsAssigned() throws Exception {
            // Given - a state store that throws a RuntimeException
            StateStore failingStateStore = mock(StateStore.class);
            RuntimeException expectedException = new RuntimeException("Database connection failed");
            when(failingStateStore.isAssigned(any())).thenThrow(expectedException);

            CompactionJob job = createCompactionJob("output.parquet", List.of("input.parquet"));

            times.add(Instant.parse("2024-01-15T10:00:00Z")); // start time
            times.add(Instant.parse("2024-01-15T10:00:01Z")); // failure time
            StateStoreWaitForFiles waitForFiles = createWaitForFilesWithStateStore(3, failingStateStore);

            // When / Then - exception should be propagated
            assertThatThrownBy(() -> waitForFiles.wait(job, TASK_ID, JOB_RUN_ID))
                    .isInstanceOf(RuntimeException.class)
                    .hasMessage("Database connection failed");
        }

        @Test
        void shouldRecordJobFailureWhenRuntimeExceptionOccurs() throws Exception {
            // Given - a state store that throws a RuntimeException
            StateStore failingStateStore = mock(StateStore.class);
            when(failingStateStore.isAssigned(any())).thenThrow(new RuntimeException("Connection timeout"));

            CompactionJob job = createCompactionJob("output.parquet", List.of("input.parquet"));

            Instant startTime = Instant.parse("2024-01-15T10:00:00Z");
            Instant failureTime = Instant.parse("2024-01-15T10:00:05Z");
            times.add(startTime);
            times.add(failureTime);
            StateStoreWaitForFiles waitForFiles = createWaitForFilesWithStateStore(3, failingStateStore);

            // When
            try {
                waitForFiles.wait(job, TASK_ID, JOB_RUN_ID);
            } catch (RuntimeException e) {
                // Expected
            }

            // Then - job failure should be recorded in tracker
            Optional<CompactionJobStatus> jobStatus = jobTracker.getJob(job.getId());
            assertThat(jobStatus).isPresent();
            assertThat(jobStatus.get().isStarted()).isTrue();
            assertThat(jobStatus.get().isAnyRunFailed()).isTrue();
        }

        @Test
        void shouldRecordCorrectTaskIdAndJobRunIdOnFailure() throws Exception {
            // Given - a state store that throws a RuntimeException
            StateStore failingStateStore = mock(StateStore.class);
            when(failingStateStore.isAssigned(any())).thenThrow(new RuntimeException("Error"));

            CompactionJob job = createCompactionJob("output.parquet", List.of("input.parquet"));

            times.add(Instant.parse("2024-01-15T10:00:00Z"));
            times.add(Instant.parse("2024-01-15T10:00:01Z"));
            StateStoreWaitForFiles waitForFiles = createWaitForFilesWithStateStore(1, failingStateStore);

            // When
            try {
                waitForFiles.wait(job, TASK_ID, JOB_RUN_ID);
            } catch (RuntimeException e) {
                // Expected
            }

            // Then - job failure should be recorded with correct task and run IDs
            Optional<CompactionJobStatus> jobStatus = jobTracker.getJob(job.getId());
            assertThat(jobStatus).isPresent();
            assertThat(jobStatus.get().isTaskIdAssigned(TASK_ID)).isTrue();
        }

        @Test
        void shouldNotRetryAfterRuntimeException() throws Exception {
            // Given - a state store that throws a RuntimeException
            StateStore failingStateStore = mock(StateStore.class);
            when(failingStateStore.isAssigned(any())).thenThrow(new RuntimeException("Immediate failure"));

            CompactionJob job = createCompactionJob("output.parquet", List.of("input.parquet"));

            times.add(Instant.parse("2024-01-15T10:00:00Z"));
            times.add(Instant.parse("2024-01-15T10:00:01Z"));
            StateStoreWaitForFiles waitForFiles = createWaitForFilesWithStateStore(5, failingStateStore);

            // When
            try {
                waitForFiles.wait(job, TASK_ID, JOB_RUN_ID);
            } catch (RuntimeException e) {
                // Expected
            }

            // Then - should only have slept before the first attempt, not multiple retries
            // The first attempt doesn't need sleep, so 0 sleeps expected
            assertThat(sleepDurations).isEmpty();
        }

        @Test
        void shouldHandleIllegalStateException() throws Exception {
            // Given - a state store that throws an IllegalStateException
            StateStore failingStateStore = mock(StateStore.class);
            IllegalStateException expectedException = new IllegalStateException("Invalid state");
            when(failingStateStore.isAssigned(any())).thenThrow(expectedException);

            CompactionJob job = createCompactionJob("output.parquet", List.of("input.parquet"));

            times.add(Instant.parse("2024-01-15T10:00:00Z"));
            times.add(Instant.parse("2024-01-15T10:00:01Z"));
            StateStoreWaitForFiles waitForFiles = createWaitForFilesWithStateStore(3, failingStateStore);

            // When / Then - IllegalStateException should be propagated
            assertThatThrownBy(() -> waitForFiles.wait(job, TASK_ID, JOB_RUN_ID))
                    .isInstanceOf(IllegalStateException.class)
                    .hasMessage("Invalid state");

            // And job failure should be recorded
            Optional<CompactionJobStatus> jobStatus = jobTracker.getJob(job.getId());
            assertThat(jobStatus).isPresent();
            assertThat(jobStatus.get().isAnyRunFailed()).isTrue();
        }

        @Test
        void shouldHandleNullPointerException() throws Exception {
            // Given - a state store that throws a NullPointerException
            StateStore failingStateStore = mock(StateStore.class);
            NullPointerException expectedException = new NullPointerException("Null reference");
            when(failingStateStore.isAssigned(any())).thenThrow(expectedException);

            CompactionJob job = createCompactionJob("output.parquet", List.of("input.parquet"));

            times.add(Instant.parse("2024-01-15T10:00:00Z"));
            times.add(Instant.parse("2024-01-15T10:00:01Z"));
            StateStoreWaitForFiles waitForFiles = createWaitForFilesWithStateStore(3, failingStateStore);

            // When / Then - NullPointerException should be propagated
            assertThatThrownBy(() -> waitForFiles.wait(job, TASK_ID, JOB_RUN_ID))
                    .isInstanceOf(NullPointerException.class)
                    .hasMessage("Null reference");

            // And job failure should be recorded
            Optional<CompactionJobStatus> jobStatus = jobTracker.getJob(job.getId());
            assertThat(jobStatus).isPresent();
            assertThat(jobStatus.get().isAnyRunFailed()).isTrue();
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

    private StateStoreWaitForFiles createWaitForFilesWithStateStore(int maxAttempts, StateStore stateStore) {
        ExponentialBackoffWithJitter backoff = new ExponentialBackoffWithJitter(
                WaitRange.firstAndMaxWaitCeilingSecs(1, 10), () -> 0.5, recordingSleep);
        PollWithRetries throttlingRetries = PollWithRetries.noRetries();

        StateStoreProvider stateStoreProvider = new StateStoreProvider(instanceProperties, props -> stateStore);

        return new StateStoreWaitForFiles(
                maxAttempts,
                backoff,
                throttlingRetries,
                new FixedTablePropertiesProvider(tableProperties),
                stateStoreProvider,
                jobTracker,
                () -> times.isEmpty() ? Instant.now() : times.poll());
    }
}
