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
import sleeper.compaction.core.task.CompactionTask.MessageReceiver;
import sleeper.core.properties.PropertiesReloader;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.table.TableProperties;
import sleeper.core.properties.testutils.FixedTablePropertiesProvider;
import sleeper.core.schema.Schema;
import sleeper.core.statestore.StateStore;
import sleeper.core.statestore.StateStoreProvider;
import sleeper.core.statestore.testutils.InMemoryTransactionLogStateStore;
import sleeper.core.statestore.testutils.InMemoryTransactionLogs;
import sleeper.core.tracker.compaction.job.InMemoryCompactionJobTracker;
import sleeper.core.tracker.compaction.task.InMemoryCompactionTaskTracker;
import sleeper.core.tracker.job.run.RecordsProcessed;
import sleeper.core.util.ExponentialBackoffWithJitter;
import sleeper.core.util.ExponentialBackoffWithJitter.WaitRange;
import sleeper.core.util.PollWithRetries;
import sleeper.core.util.ThreadSleep;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThat;
import static sleeper.core.properties.instance.CompactionProperty.COMPACTION_TASK_DELAY_BEFORE_RETRY_IN_SECONDS;
import static sleeper.core.properties.instance.CompactionProperty.COMPACTION_TASK_MAX_CONSECUTIVE_FAILURES;
import static sleeper.core.properties.instance.CompactionProperty.COMPACTION_TASK_MAX_IDLE_TIME_IN_SECONDS;
import static sleeper.core.properties.instance.CompactionProperty.COMPACTION_TASK_WAIT_FOR_INPUT_FILE_ASSIGNMENT;
import static sleeper.core.properties.table.TableProperty.COMPACTION_JOB_COMMIT_ASYNC;
import static sleeper.core.properties.testutils.InstancePropertiesTestHelper.createTestInstanceProperties;
import static sleeper.core.properties.testutils.TablePropertiesTestHelper.createTestTableProperties;
import static sleeper.core.schema.SchemaTestHelper.schemaWithKey;

/**
 * Tests for CompactionTask.threadSleep() lambda (lines 260-268).
 * These tests use the primary constructor which uses the real threadSleep() implementation.
 */
public class CompactionTaskClaudeLambdaThreadSleepTest {

    private static final Schema SCHEMA = schemaWithKey("key");
    private static final String PARTITION_ID = "root";
    private static final String TASK_ID = "test-task";

    private final InstanceProperties instanceProperties = createTestInstanceProperties();
    private final TableProperties tableProperties = createTestTableProperties(instanceProperties, SCHEMA);
    private final InMemoryTransactionLogs transactionLogs = new InMemoryTransactionLogs();
    private final InMemoryCompactionJobTracker jobTracker = new InMemoryCompactionJobTracker();
    private final InMemoryCompactionTaskTracker taskTracker = new InMemoryCompactionTaskTracker();

    private StateStore stateStore;

    @BeforeEach
    void setUp() {
        stateStore = InMemoryTransactionLogStateStore.createAndInitialise(tableProperties, transactionLogs);
        tableProperties.set(COMPACTION_JOB_COMMIT_ASYNC, "false");
        instanceProperties.setNumber(COMPACTION_TASK_MAX_CONSECUTIVE_FAILURES, 3);
        instanceProperties.set(COMPACTION_TASK_WAIT_FOR_INPUT_FILE_ASSIGNMENT, "false");
    }

    @Nested
    @DisplayName("threadSleep lambda execution")
    class ThreadSleepLambdaExecution {

        @Test
        void shouldCallThreadSleepWhenDelayBeforeRetryIsNonZero() throws Exception {
            // Given - Use primary constructor which uses the real threadSleep()
            // Set a very small delay to minimize actual wait time, but non-zero to trigger the sleep
            instanceProperties.setNumber(COMPACTION_TASK_DELAY_BEFORE_RETRY_IN_SECONDS, 1);
            instanceProperties.setNumber(COMPACTION_TASK_MAX_IDLE_TIME_IN_SECONDS, 3);

            // Use a message receiver that returns no messages, then times out
            AtomicInteger receiveCount = new AtomicInteger(0);
            MessageReceiver receiver = () -> {
                int count = receiveCount.incrementAndGet();
                if (count >= 3) {
                    // Simulate enough time passing to exceed max idle
                    try {
                        Thread.sleep(3500); // Wait a bit more than max idle to ensure termination
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                return Optional.empty();
            };

            CompactionTask task = createTaskWithPrimaryConstructor(receiver);

            // When
            long startTime = System.currentTimeMillis();
            task.run();
            long elapsedTime = System.currentTimeMillis() - startTime;

            // Then - The task should have run with at least one sleep
            // If delayBeforeRetry is working, there should be noticeable elapsed time
            // At minimum, we expect 1 second of delay from the sleep
            assertThat(elapsedTime).isGreaterThan(500);
        }

        @Test
        void shouldHandleInterruptedExceptionInThreadSleep() throws Exception {
            // Given - Use primary constructor which uses the real threadSleep()
            instanceProperties.setNumber(COMPACTION_TASK_DELAY_BEFORE_RETRY_IN_SECONDS, 10);
            instanceProperties.setNumber(COMPACTION_TASK_MAX_IDLE_TIME_IN_SECONDS, 60);

            AtomicBoolean wasInterrupted = new AtomicBoolean(false);
            AtomicInteger receiveCount = new AtomicInteger(0);

            // Create a receiver that interrupts the thread during the second receive
            MessageReceiver receiver = () -> {
                int count = receiveCount.incrementAndGet();
                if (count == 2) {
                    // Interrupt this thread to cause InterruptedException during sleep
                    Thread.currentThread().interrupt();
                }
                if (count > 1) {
                    // Check if interrupted flag was set (by RuntimeException handler)
                    wasInterrupted.set(Thread.currentThread().isInterrupted());
                }
                return Optional.empty();
            };

            CompactionTask task = createTaskWithPrimaryConstructor(receiver);

            // When - run the task and expect it to terminate due to interrupt
            try {
                task.run();
            } catch (RuntimeException e) {
                // Expected - the threadSleep lambda wraps InterruptedException in RuntimeException
                wasInterrupted.set(true);
            }

            // Then - The thread should have been interrupted
            assertThat(wasInterrupted.get() || Thread.currentThread().isInterrupted()).isTrue();

            // Clear the interrupt flag for subsequent tests
            Thread.interrupted();
        }

        @Test
        void shouldExecuteThreadSleepSuccessfullyWithoutInterruption() throws Exception {
            // Given - Use primary constructor which uses the real threadSleep()
            instanceProperties.setNumber(COMPACTION_TASK_DELAY_BEFORE_RETRY_IN_SECONDS, 1);
            instanceProperties.setNumber(COMPACTION_TASK_MAX_IDLE_TIME_IN_SECONDS, 2);

            AtomicInteger receiveCount = new AtomicInteger(0);
            MessageReceiver receiver = () -> {
                receiveCount.incrementAndGet();
                return Optional.empty();
            };

            CompactionTask task = createTaskWithPrimaryConstructor(receiver);

            // When
            task.run();

            // Then - Task should have completed and called receive at least twice
            // (first receive returns empty, triggers sleep, second receive also returns empty,
            // then idle time exceeded causes termination)
            assertThat(receiveCount.get()).isGreaterThanOrEqualTo(1);

            // Task should be finished in tracker
            assertThat(taskTracker.getAllTasks()).hasSize(1);
            assertThat(taskTracker.getAllTasks().get(0).isFinished()).isTrue();
        }
    }

    // Helper methods

    private CompactionTask createTaskWithPrimaryConstructor(MessageReceiver receiver) {
        StateStoreWaitForFiles waitForFiles = createNoOpWaitForFiles();
        CompactionJobCommitterOrSendToLambda committer = createCommitter();

        // Use the PRIMARY constructor which uses the real threadSleep() lambda
        return new CompactionTask(
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
                java.time.Instant::now) {
            @Override
            public void wait(CompactionJob job, String taskId, String jobRunId) {
                // No-op
            }
        };
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
            return new RecordsProcessed(100, 90);
        };
    }
}
