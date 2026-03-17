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
package sleeper.compaction.tracker.job;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import org.junit.jupiter.api.Test;

import sleeper.core.tracker.compaction.job.query.CompactionJobStatus;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static sleeper.dynamodb.tools.DynamoDBAttributes.createNumberAttribute;
import static sleeper.dynamodb.tools.DynamoDBAttributes.createStringAttribute;

public class DynamoDBCompactionJobStatusFormatIT {

    @Test
    public void shouldStreamJobStatusFromDynamoDBItems() {
        // Given
        Instant updateTime = Instant.parse("2022-09-23T10:50:00.001Z");
        Map<String, AttributeValue> item = Map.of(
                "JobId", createStringAttribute("job-1"),
                "UpdateTime", createNumberAttribute(updateTime.toEpochMilli()),
                "UpdateType", createStringAttribute("created"),
                "PartitionId", createStringAttribute("partition-1"),
                "InputFilesCount", createNumberAttribute(3),
                "ExpiryDate", createNumberAttribute(updateTime.plusSeconds(604800).getEpochSecond()));

        // When
        List<CompactionJobStatus> statuses = DynamoDBCompactionJobStatusFormat
                .streamJobStatuses(Stream.of(item))
                .toList();

        // Then
        assertThat(statuses).hasSize(1);
        assertThat(statuses.get(0).getJobId()).isEqualTo("job-1");
        assertThat(statuses.get(0).getPartitionId()).isEqualTo("partition-1");
        assertThat(statuses.get(0).getInputFilesCount()).isEqualTo(3);
    }

    @Test
    public void shouldStreamMultipleJobStatusesFromDynamoDBItems() {
        // Given
        Instant updateTime1 = Instant.parse("2022-09-23T10:50:00.001Z");
        Instant updateTime2 = Instant.parse("2022-09-23T10:51:00.001Z");
        Map<String, AttributeValue> item1 = Map.of(
                "JobId", createStringAttribute("job-1"),
                "UpdateTime", createNumberAttribute(updateTime1.toEpochMilli()),
                "UpdateType", createStringAttribute("created"),
                "PartitionId", createStringAttribute("partition-1"),
                "InputFilesCount", createNumberAttribute(2),
                "ExpiryDate", createNumberAttribute(updateTime1.plusSeconds(604800).getEpochSecond()));

        Map<String, AttributeValue> item2 = Map.of(
                "JobId", createStringAttribute("job-2"),
                "UpdateTime", createNumberAttribute(updateTime2.toEpochMilli()),
                "UpdateType", createStringAttribute("created"),
                "PartitionId", createStringAttribute("partition-2"),
                "InputFilesCount", createNumberAttribute(5),
                "ExpiryDate", createNumberAttribute(updateTime2.plusSeconds(604800).getEpochSecond()));

        // When
        List<CompactionJobStatus> statuses = DynamoDBCompactionJobStatusFormat
                .streamJobStatuses(Stream.of(item1, item2))
                .toList();

        // Then
        assertThat(statuses).hasSize(2);
        assertThat(statuses)
                .extracting(CompactionJobStatus::getJobId)
                .containsExactlyInAnyOrder("job-1", "job-2");
        assertThat(statuses)
                .extracting(CompactionJobStatus::getPartitionId)
                .containsExactlyInAnyOrder("partition-1", "partition-2");
        assertThat(statuses)
                .extracting(CompactionJobStatus::getInputFilesCount)
                .containsExactlyInAnyOrder(2, 5);
    }

    @Test
    public void shouldStreamEmptyResultWhenNoItems() {
        // Given / When
        List<CompactionJobStatus> statuses = DynamoDBCompactionJobStatusFormat
                .streamJobStatuses(Stream.empty())
                .toList();

        // Then
        assertThat(statuses).isEmpty();
    }
}
