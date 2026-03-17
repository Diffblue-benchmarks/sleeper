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
package sleeper.compaction.core.job.creation.strategy;

import org.junit.jupiter.api.Test;

import sleeper.compaction.core.job.creation.strategy.CompactionStrategyIndex.FilesInPartition;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.table.TableProperties;
import sleeper.core.table.TableStatus;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static sleeper.core.properties.instance.CdkDefinedInstanceProperty.DATA_BUCKET;
import static sleeper.core.properties.table.TableProperty.TABLE_ID;
import static sleeper.core.schema.SchemaTestHelper.schemaWithKey;

public class ShouldCreateJobsStrategyTest {

    @Test
    void shouldReturnStrategyThatAllowsUnlimitedJobsFromYes() {
        // Given
        TableStatus tableStatus = TableStatus.uniqueIdAndName("test-table-id", "test-table", true);
        FilesInPartition filesInPartition = new FilesInPartition(tableStatus, "partition1", List.of(), List.of());

        // When
        ShouldCreateJobsStrategy strategy = ShouldCreateJobsStrategy.yes();

        // Then
        assertThat(strategy.maxCompactionJobsToCreate(filesInPartition)).isEqualTo(Long.MAX_VALUE);
    }

    @Test
    void shouldCallDefaultInitMethodWithoutException() {
        // Given
        InstanceProperties instanceProperties = new InstanceProperties();
        instanceProperties.set(DATA_BUCKET, "test-bucket");
        TableProperties tableProperties = new TableProperties(instanceProperties);
        tableProperties.set(TABLE_ID, "test-table");
        tableProperties.setSchema(schemaWithKey("key"));
        ShouldCreateJobsStrategy strategy = ShouldCreateJobsStrategy.yes();

        // When / Then
        strategy.init(instanceProperties, tableProperties);
    }
}
