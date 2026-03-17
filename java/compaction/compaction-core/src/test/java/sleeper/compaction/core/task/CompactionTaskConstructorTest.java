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

import org.junit.jupiter.api.Test;

import sleeper.compaction.core.job.CompactionJob;
import sleeper.compaction.core.job.CompactionJobCommitterOrSendToLambda;
import sleeper.compaction.core.job.CompactionRunner;
import sleeper.core.properties.PropertiesReloader;
import sleeper.core.properties.table.TablePropertiesProvider;
import sleeper.core.properties.testutils.FixedTablePropertiesProvider;
import sleeper.core.statestore.StateStoreProvider;
import sleeper.core.statestore.testutils.InMemoryTransactionLogStateStore;
import sleeper.core.tracker.job.run.RecordsProcessed;

import java.time.Instant;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;

public class CompactionTaskConstructorTest extends CompactionTaskTestBase {

    @Test
    void shouldRunTaskUsingSimpleConstructor() throws Exception {
        // Given
        CompactionJob job = createJobOnQueue("job1");
        Queue<Instant> times = new LinkedList<>(List.of(
                Instant.parse("2024-02-22T13:50:00Z"), // Task start
                Instant.parse("2024-02-22T13:50:01Z"), // Job start
                Instant.parse("2024-02-22T13:50:02Z"), // Job finish
                Instant.parse("2024-02-22T13:50:03Z"), // Commit
                Instant.parse("2024-02-22T13:50:05Z"))); // Task finish
        RecordsProcessed recordsProcessed = new RecordsProcessed(10L, 10L);

        // When
        new CompactionTask(
                instanceProperties,
                createTablePropertiesProvider(),
                PropertiesReloader.neverReload(),
                createStateStoreProvider(),
                createMessageReceiver(),
                createWaitForFiles(),
                createJobCommitter(times::poll),
                jobTracker,
                taskTracker,
                createCompactionRunnerFactory(processJobs(jobSucceeds(recordsProcessed))),
                DEFAULT_TASK_ID)
                .run();

        // Then
        assertThat(consumedJobs).containsExactly(job);
        assertThat(jobsReturnedToQueue).isEmpty();
        assertThat(jobsOnQueue).isEmpty();
        assertThat(jobTracker.getAllJobs(DEFAULT_TABLE_ID)).isNotEmpty();
    }

    @Test
    void shouldRunTaskUsingFullConstructor() throws Exception {
        // Given
        CompactionJob job = createJobOnQueue("job1");
        Queue<Instant> times = new LinkedList<>(List.of(
                Instant.parse("2024-02-22T13:50:00Z"), // Task start
                Instant.parse("2024-02-22T13:50:01Z"), // Job start
                Instant.parse("2024-02-22T13:50:02Z"), // Job finish
                Instant.parse("2024-02-22T13:50:03Z"), // Commit
                Instant.parse("2024-02-22T13:50:05Z"))); // Task finish
        RecordsProcessed recordsProcessed = new RecordsProcessed(10L, 10L);

        // When
        new CompactionTask(
                instanceProperties,
                createTablePropertiesProvider(),
                PropertiesReloader.neverReload(),
                createStateStoreProvider(),
                createMessageReceiver(),
                createWaitForFiles(),
                createJobCommitter(times::poll),
                jobTracker,
                taskTracker,
                createCompactionRunnerFactory(processJobs(jobSucceeds(recordsProcessed))),
                DEFAULT_TASK_ID,
                () -> "test-job-run-1",
                times::poll,
                sleeps::add)
                .run();

        // Then
        assertThat(consumedJobs).containsExactly(job);
        assertThat(jobsReturnedToQueue).isEmpty();
        assertThat(jobsOnQueue).isEmpty();
        assertThat(jobTracker.getAllJobs(DEFAULT_TABLE_ID)).isNotEmpty();
    }

    @Test
    void shouldRunMultipleJobsUsingFullConstructor() throws Exception {
        // Given
        CompactionJob job1 = createJobOnQueue("job1");
        CompactionJob job2 = createJobOnQueue("job2");
        Queue<Instant> times = new LinkedList<>(List.of(
                Instant.parse("2024-02-22T13:50:00Z"), // Task start
                Instant.parse("2024-02-22T13:50:01Z"), // Job1 start
                Instant.parse("2024-02-22T13:50:02Z"), // Job1 finish
                Instant.parse("2024-02-22T13:50:03Z"), // Job1 commit
                Instant.parse("2024-02-22T13:50:04Z"), // Job2 start
                Instant.parse("2024-02-22T13:50:05Z"), // Job2 finish
                Instant.parse("2024-02-22T13:50:06Z"), // Job2 commit
                Instant.parse("2024-02-22T13:50:07Z"))); // Task finish
        RecordsProcessed recordsProcessed = new RecordsProcessed(10L, 10L);

        // When
        new CompactionTask(
                instanceProperties,
                createTablePropertiesProvider(),
                PropertiesReloader.neverReload(),
                createStateStoreProvider(),
                createMessageReceiver(),
                createWaitForFiles(),
                createJobCommitter(times::poll),
                jobTracker,
                taskTracker,
                createCompactionRunnerFactory(processJobs(jobSucceeds(recordsProcessed), jobSucceeds(recordsProcessed))),
                DEFAULT_TASK_ID,
                createJobRunIdSupplier(),
                times::poll,
                sleeps::add)
                .run();

        // Then
        assertThat(consumedJobs).containsExactly(job1, job2);
        assertThat(jobsReturnedToQueue).isEmpty();
        assertThat(jobsOnQueue).isEmpty();
        assertThat(jobTracker.getAllJobs(DEFAULT_TABLE_ID)).hasSize(2);
    }

    @Test
    void shouldHandleJobFailureUsingFullConstructor() throws Exception {
        // Given
        CompactionJob job = createJobOnQueue("job1");
        Queue<Instant> times = new LinkedList<>(List.of(
                Instant.parse("2024-02-22T13:50:00Z"), // Task start
                Instant.parse("2024-02-22T13:50:01Z"), // Job start
                Instant.parse("2024-02-22T13:50:02Z"), // Job failure
                Instant.parse("2024-02-22T13:50:03Z"))); // Task finish

        // When
        new CompactionTask(
                instanceProperties,
                createTablePropertiesProvider(),
                PropertiesReloader.neverReload(),
                createStateStoreProvider(),
                createMessageReceiver(),
                createWaitForFiles(),
                createJobCommitter(times::poll),
                jobTracker,
                taskTracker,
                createCompactionRunnerFactory(processJobs(jobFails())),
                DEFAULT_TASK_ID,
                () -> "test-job-run-1",
                times::poll,
                sleeps::add)
                .run();

        // Then
        assertThat(consumedJobs).isEmpty();
        assertThat(jobsReturnedToQueue).containsExactly(job);
        assertThat(jobsOnQueue).isEmpty();
    }

    @Test
    void shouldHandleEmptyQueueUsingFullConstructor() throws Exception {
        // Given
        Queue<Instant> times = new LinkedList<>(List.of(
                Instant.parse("2024-02-22T13:50:00Z"), // Task start
                Instant.parse("2024-02-22T13:50:01Z"))); // Task finish

        // When
        new CompactionTask(
                instanceProperties,
                createTablePropertiesProvider(),
                PropertiesReloader.neverReload(),
                createStateStoreProvider(),
                createMessageReceiver(),
                createWaitForFiles(),
                createJobCommitter(times::poll),
                jobTracker,
                taskTracker,
                createCompactionRunnerFactory(processNoJobs()),
                DEFAULT_TASK_ID,
                () -> "test-job-run-1",
                times::poll,
                sleeps::add)
                .run();

        // Then
        assertThat(consumedJobs).isEmpty();
        assertThat(jobsReturnedToQueue).isEmpty();
        assertThat(jobsOnQueue).isEmpty();
        assertThat(jobTracker.getAllJobs(DEFAULT_TABLE_ID)).isEmpty();
    }

    private TablePropertiesProvider createTablePropertiesProvider() {
        return new FixedTablePropertiesProvider(List.of(tableProperties));
    }

    private StateStoreProvider createStateStoreProvider() {
        return InMemoryTransactionLogStateStore.createProvider(instanceProperties, transactionLogs);
    }

    private CompactionTask.MessageReceiver createMessageReceiver() {
        return () -> {
            CompactionJob job = jobsOnQueue.poll();
            if (job != null) {
                return Optional.of(new FakeMessageHandle(job));
            } else {
                return Optional.empty();
            }
        };
    }

    private StateStoreWaitForFiles createWaitForFiles() {
        return waitForFileAssignment().withAttempts(1);
    }

    private CompactionJobCommitterOrSendToLambda createJobCommitter(Supplier<Instant> timeSupplier) {
        return new CompactionJobCommitterOrSendToLambda(
                createTablePropertiesProvider(),
                createStateStoreProvider(),
                jobTracker,
                stateStoreCommitQueue::add,
                batcherCommitQueue::add,
                timeSupplier);
    }

    private CompactionRunnerFactory createCompactionRunnerFactory(CompactionRunner compactor) {
        return (job, properties) -> compactor;
    }

    private Supplier<String> createJobRunIdSupplier() {
        AtomicInteger runNumber = new AtomicInteger();
        return () -> "test-job-run-" + runNumber.incrementAndGet();
    }
}
