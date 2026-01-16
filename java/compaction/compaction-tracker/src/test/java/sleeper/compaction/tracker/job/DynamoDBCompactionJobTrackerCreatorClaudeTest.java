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

import com.amazonaws.services.dynamodbv2.model.DescribeTableResult;
import com.amazonaws.services.dynamodbv2.model.ResourceNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import sleeper.core.properties.instance.InstanceProperties;
import sleeper.localstack.test.LocalStackTestBase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static sleeper.core.properties.instance.CommonProperty.ID;
import static sleeper.core.properties.instance.CompactionProperty.COMPACTION_TRACKER_ENABLED;
import static sleeper.core.properties.testutils.InstancePropertiesTestHelper.createTestInstanceProperties;

public class DynamoDBCompactionJobTrackerCreatorClaudeTest extends LocalStackTestBase {

    private InstanceProperties instanceProperties;
    private boolean tablesCreated = false;

    @AfterEach
    void tearDown() {
        if (tablesCreated && instanceProperties != null) {
            instanceProperties.set(COMPACTION_TRACKER_ENABLED, "true");
            DynamoDBCompactionJobTrackerCreator.tearDown(instanceProperties, dynamoClient);
        }
    }

    @Nested
    @DisplayName("create method")
    class CreateMethod {

        @Test
        void shouldCreateBothTablesWhenTrackerEnabled() {
            // Given
            instanceProperties = createTestInstanceProperties();
            instanceProperties.set(COMPACTION_TRACKER_ENABLED, "true");
            String instanceId = instanceProperties.get(ID);
            String updatesTableName = DynamoDBCompactionJobTracker.jobUpdatesTableName(instanceId);
            String lookupTableName = DynamoDBCompactionJobTracker.jobLookupTableName(instanceId);

            // When
            DynamoDBCompactionJobTrackerCreator.create(instanceProperties, dynamoClient);
            tablesCreated = true;

            // Then
            assertThat(dynamoClient.describeTable(updatesTableName))
                    .extracting(DescribeTableResult::getTable)
                    .isNotNull();
            assertThat(dynamoClient.describeTable(lookupTableName))
                    .extracting(DescribeTableResult::getTable)
                    .isNotNull();
        }

        @Test
        void shouldNotCreateTablesWhenTrackerDisabled() {
            // Given
            instanceProperties = createTestInstanceProperties();
            instanceProperties.set(COMPACTION_TRACKER_ENABLED, "false");
            String instanceId = instanceProperties.get(ID);
            String updatesTableName = DynamoDBCompactionJobTracker.jobUpdatesTableName(instanceId);
            String lookupTableName = DynamoDBCompactionJobTracker.jobLookupTableName(instanceId);

            // When
            DynamoDBCompactionJobTrackerCreator.create(instanceProperties, dynamoClient);

            // Then - tables should not exist
            assertThatThrownBy(() -> dynamoClient.describeTable(updatesTableName))
                    .isInstanceOf(ResourceNotFoundException.class);
            assertThatThrownBy(() -> dynamoClient.describeTable(lookupTableName))
                    .isInstanceOf(ResourceNotFoundException.class);
        }

        @Test
        void shouldCreateUpdatesTableWithCorrectKeySchema() {
            // Given
            instanceProperties = createTestInstanceProperties();
            instanceProperties.set(COMPACTION_TRACKER_ENABLED, "true");
            String instanceId = instanceProperties.get(ID);
            String updatesTableName = DynamoDBCompactionJobTracker.jobUpdatesTableName(instanceId);

            // When
            DynamoDBCompactionJobTrackerCreator.create(instanceProperties, dynamoClient);
            tablesCreated = true;

            // Then
            DescribeTableResult result = dynamoClient.describeTable(updatesTableName);
            assertThat(result.getTable().getKeySchema())
                    .hasSize(2);
            assertThat(result.getTable().getKeySchema())
                    .anyMatch(key -> key.getAttributeName().equals(DynamoDBCompactionJobTracker.TABLE_ID)
                            && key.getKeyType().equals("HASH"));
            assertThat(result.getTable().getKeySchema())
                    .anyMatch(key -> key.getAttributeName().equals(DynamoDBCompactionJobTracker.JOB_ID_AND_UPDATE)
                            && key.getKeyType().equals("RANGE"));
        }

        @Test
        void shouldCreateLookupTableWithCorrectKeySchema() {
            // Given
            instanceProperties = createTestInstanceProperties();
            instanceProperties.set(COMPACTION_TRACKER_ENABLED, "true");
            String instanceId = instanceProperties.get(ID);
            String lookupTableName = DynamoDBCompactionJobTracker.jobLookupTableName(instanceId);

            // When
            DynamoDBCompactionJobTrackerCreator.create(instanceProperties, dynamoClient);
            tablesCreated = true;

            // Then
            DescribeTableResult result = dynamoClient.describeTable(lookupTableName);
            assertThat(result.getTable().getKeySchema())
                    .hasSize(1);
            assertThat(result.getTable().getKeySchema())
                    .anyMatch(key -> key.getAttributeName().equals(DynamoDBCompactionJobTracker.JOB_ID)
                            && key.getKeyType().equals("HASH"));
        }

        @Test
        void shouldCreateTablesUsableByTracker() {
            // Given
            instanceProperties = createTestInstanceProperties();
            instanceProperties.set(COMPACTION_TRACKER_ENABLED, "true");

            // When
            DynamoDBCompactionJobTrackerCreator.create(instanceProperties, dynamoClient);
            tablesCreated = true;

            // Then - verify tables are usable by creating a tracker and using it
            DynamoDBCompactionJobTracker tracker = DynamoDBCompactionJobTracker.stronglyConsistentReads(
                    dynamoClient, instanceProperties);
            assertThat(tracker).isNotNull();
            // Verify we can query without errors
            assertThat(tracker.getJob("non-existent-job")).isEmpty();
        }
    }

    @Nested
    @DisplayName("tearDown method")
    class TearDownMethod {

        @Test
        void shouldDeleteBothTablesWhenTrackerEnabled() {
            // Given
            instanceProperties = createTestInstanceProperties();
            instanceProperties.set(COMPACTION_TRACKER_ENABLED, "true");
            String instanceId = instanceProperties.get(ID);
            String updatesTableName = DynamoDBCompactionJobTracker.jobUpdatesTableName(instanceId);
            String lookupTableName = DynamoDBCompactionJobTracker.jobLookupTableName(instanceId);

            DynamoDBCompactionJobTrackerCreator.create(instanceProperties, dynamoClient);

            // When
            DynamoDBCompactionJobTrackerCreator.tearDown(instanceProperties, dynamoClient);

            // Then - tables should not exist anymore
            assertThatThrownBy(() -> dynamoClient.describeTable(updatesTableName))
                    .isInstanceOf(ResourceNotFoundException.class);
            assertThatThrownBy(() -> dynamoClient.describeTable(lookupTableName))
                    .isInstanceOf(ResourceNotFoundException.class);
        }

        @Test
        void shouldDoNothingWhenTrackerDisabled() {
            // Given
            instanceProperties = createTestInstanceProperties();
            instanceProperties.set(COMPACTION_TRACKER_ENABLED, "true");
            String instanceId = instanceProperties.get(ID);
            String updatesTableName = DynamoDBCompactionJobTracker.jobUpdatesTableName(instanceId);
            String lookupTableName = DynamoDBCompactionJobTracker.jobLookupTableName(instanceId);

            DynamoDBCompactionJobTrackerCreator.create(instanceProperties, dynamoClient);
            tablesCreated = true;

            // When - call tearDown with tracker disabled
            instanceProperties.set(COMPACTION_TRACKER_ENABLED, "false");
            DynamoDBCompactionJobTrackerCreator.tearDown(instanceProperties, dynamoClient);

            // Then - tables should still exist
            assertThat(dynamoClient.describeTable(updatesTableName))
                    .extracting(DescribeTableResult::getTable)
                    .isNotNull();
            assertThat(dynamoClient.describeTable(lookupTableName))
                    .extracting(DescribeTableResult::getTable)
                    .isNotNull();
        }
    }

    @Nested
    @DisplayName("create and tearDown lifecycle")
    class CreateAndTearDownLifecycle {

        @Test
        void shouldBeAbleToCreateTablesAfterTearDown() {
            // Given
            instanceProperties = createTestInstanceProperties();
            instanceProperties.set(COMPACTION_TRACKER_ENABLED, "true");
            String instanceId = instanceProperties.get(ID);
            String updatesTableName = DynamoDBCompactionJobTracker.jobUpdatesTableName(instanceId);
            String lookupTableName = DynamoDBCompactionJobTracker.jobLookupTableName(instanceId);

            // Create and tear down
            DynamoDBCompactionJobTrackerCreator.create(instanceProperties, dynamoClient);
            DynamoDBCompactionJobTrackerCreator.tearDown(instanceProperties, dynamoClient);

            // When - recreate
            DynamoDBCompactionJobTrackerCreator.create(instanceProperties, dynamoClient);
            tablesCreated = true;

            // Then
            assertThat(dynamoClient.describeTable(updatesTableName))
                    .extracting(DescribeTableResult::getTable)
                    .isNotNull();
            assertThat(dynamoClient.describeTable(lookupTableName))
                    .extracting(DescribeTableResult::getTable)
                    .isNotNull();
        }
    }
}
