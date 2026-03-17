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
import sleeper.core.properties.table.TableProperties;
import sleeper.core.tracker.job.run.JobRunTime;

import java.time.Instant;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static org.assertj.core.api.Assertions.assertThat;
import static sleeper.compaction.core.job.CompactionJobStatusFromJobTestData.compactionJobCreated;
import static sleeper.core.properties.instance.CompactionProperty.COMPACTION_TASK_MAX_CONSECUTIVE_FAILURES;
import static sleeper.core.properties.table.TableProperty.TABLE_ID;
import static sleeper.core.properties.testutils.TablePropertiesTestHelper.createTestTableProperties;
import static sleeper.core.tracker.compaction.job.CompactionJobStatusTestData.failedCompactionRun;

public class CompactionTaskTest extends CompactionTaskTestBase {

    @Test
    void shouldRunJobFromQueueThenTerminate() throws Exception {
        // Given
        CompactionJob job = createJobOnQueue("job1");

        // When
        runTask(jobsSucceed(1));

        // Then
        assertThat(consumedJobs).containsExactly(job);
        assertThat(jobsReturnedToQueue).isEmpty();
        assertThat(jobsOnQueue).isEmpty();
    }

    @Test
    void shouldFailJobFromQueueThenTerminate() throws Exception {
        // Given
        CompactionJob job = createJobOnQueue("job1");

        // When
        runTask(processJobs(jobFails()));

        // Then
        assertThat(consumedJobs).isEmpty();
        assertThat(jobsReturnedToQueue).containsExactly(job);
        assertThat(jobsOnQueue).isEmpty();
    }

    @Test
    void shouldProcessTwoJobsFromQueueThenTerminate() throws Exception {
        // Given
        CompactionJob job1 = createJobOnQueue("job1");
        CompactionJob job2 = createJobOnQueue("job2");

        // When
        runTask(processJobs(jobSucceeds(), jobFails()));

        // Then
        assertThat(consumedJobs).containsExactly(job1);
        assertThat(jobsReturnedToQueue).containsExactly(job2);
        assertThat(jobsOnQueue).isEmpty();
    }

    @Test
    void shouldDiscardJobsForNonExistentTable() throws Exception {
        // Given
        TableProperties table = createTestTableProperties(instanceProperties, schema);
        CompactionJob job1 = createJobNotInStateStore("job1", table);
        CompactionJob job2 = createJobNotInStateStore("job2", table);
        jobsOnQueue.add(job1);
        jobsOnQueue.add(job2);
        Instant startTime1 = Instant.parse("2024-02-22T13:50:01Z");
        Instant finishTime1 = Instant.parse("2024-02-22T13:50:02Z");
        Instant startTime2 = Instant.parse("2024-02-22T13:50:03Z");
        Instant finishTime2 = Instant.parse("2024-02-22T13:50:04Z");
        Queue<Instant> times = new LinkedList<>(List.of(
                Instant.parse("2024-02-22T13:50:00Z"),   // Task start
                startTime1, finishTime1, startTime2, finishTime2,
                Instant.parse("2024-02-22T13:50:07Z"))); // Task finish

        // When
        runTask(processNoJobs(), times::poll);

        // Then
        assertThat(consumedJobs).containsExactly(job1, job2);
        assertThat(jobsReturnedToQueue).isEmpty();
        assertThat(jobsOnQueue).isEmpty();
        assertThat(jobTracker.getAllJobs(table.get(TABLE_ID))).containsExactlyInAnyOrder(
                compactionJobCreated(job1, DEFAULT_CREATED_TIME,
                        failedCompactionRun(DEFAULT_TASK_ID, new JobRunTime(startTime1, finishTime1), List.of(
                                "Table not found with ID \"" + table.get(TABLE_ID) + "\""))),
                compactionJobCreated(job2, DEFAULT_CREATED_TIME,
                        failedCompactionRun(DEFAULT_TASK_ID, new JobRunTime(startTime2, finishTime2), List.of(
                                "Table not found with ID \"" + table.get(TABLE_ID) + "\""))));
    }

    @Test
    void shouldGetJobFromMessageHandle() throws Exception {
        // Given
        CompactionJob job = createJobOnQueue("job1");

        // When
        CompactionTask.MessageHandle handle = new FakeMessageHandle(job);

        // Then
        assertThat(handle.getJob()).isEqualTo(job);
    }

    @Test
    void shouldDeleteJobFromQueueWhenMessageHandleDeletes() throws Exception {
        // Given
        CompactionJob job = createJobOnQueue("job1");
        CompactionTask.MessageHandle handle = new FakeMessageHandle(job);

        // When
        handle.deleteFromQueue();

        // Then
        assertThat(consumedJobs).containsExactly(job);
    }

    @Test
    void shouldReturnJobToQueueWhenMessageHandleReturns() throws Exception {
        // Given
        CompactionJob job = createJobOnQueue("job1");
        CompactionTask.MessageHandle handle = new FakeMessageHandle(job);

        // When
        handle.returnToQueue();

        // Then
        assertThat(jobsReturnedToQueue).containsExactly(job);
    }

    @Test
    void shouldCloseMessageHandle() throws Exception {
        // Given
        CompactionJob job = createJobOnQueue("job1");
        CompactionTask.MessageHandle handle = new FakeMessageHandle(job);

        // When / Then
        handle.close();
    }

    @Test
    void shouldTerminateWhenMaxConsecutiveFailuresReached() throws Exception {
        // Given
        instanceProperties.setNumber(COMPACTION_TASK_MAX_CONSECUTIVE_FAILURES, 3);
        CompactionJob job1 = createJobOnQueue("job1");
        CompactionJob job2 = createJobOnQueue("job2");
        CompactionJob job3 = createJobOnQueue("job3");
        CompactionJob job4 = createJobOnQueue("job4");

        // When
        runTask(processJobs(jobFails(), jobFails(), jobFails()));

        // Then
        assertThat(consumedJobs).isEmpty();
        assertThat(jobsReturnedToQueue).containsExactly(job1, job2, job3);
        assertThat(jobsOnQueue).containsExactly(job4);
    }

    @Test
    void shouldResetConsecutiveFailuresAfterSuccess() throws Exception {
        // Given
        instanceProperties.setNumber(COMPACTION_TASK_MAX_CONSECUTIVE_FAILURES, 3);
        CompactionJob job1 = createJobOnQueue("job1");
        CompactionJob job2 = createJobOnQueue("job2");
        CompactionJob job3 = createJobOnQueue("job3");
        CompactionJob job4 = createJobOnQueue("job4");
        CompactionJob job5 = createJobOnQueue("job5");

        // When
        runTask(processJobs(jobFails(), jobFails(), jobSucceeds(), jobFails(), jobFails()));

        // Then
        assertThat(consumedJobs).containsExactly(job3);
        assertThat(jobsReturnedToQueue).containsExactly(job1, job2, job4, job5);
        assertThat(jobsOnQueue).isEmpty();
    }

    @Test
    void shouldContinueProcessingWhenBelowMaxConsecutiveFailures() throws Exception {
        // Given
        instanceProperties.setNumber(COMPACTION_TASK_MAX_CONSECUTIVE_FAILURES, 5);
        CompactionJob job1 = createJobOnQueue("job1");
        CompactionJob job2 = createJobOnQueue("job2");
        CompactionJob job3 = createJobOnQueue("job3");

        // When
        runTask(processJobs(jobFails(), jobFails(), jobSucceeds()));

        // Then
        assertThat(consumedJobs).containsExactly(job3);
        assertThat(jobsReturnedToQueue).containsExactly(job1, job2);
        assertThat(jobsOnQueue).isEmpty();
    }
}
