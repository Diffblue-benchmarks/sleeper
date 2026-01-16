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
package sleeper.compaction.tracker.task;

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

public class DynamoDBCompactionTaskTrackerCreatorClaudeTest extends LocalStackTestBase {

    private InstanceProperties instanceProperties;
    private boolean tableCreated = false;

    @AfterEach
    void tearDown() {
        if (tableCreated && instanceProperties != null) {
            instanceProperties.set(COMPACTION_TRACKER_ENABLED, "true");
            DynamoDBCompactionTaskTrackerCreator.tearDown(instanceProperties, dynamoClient);
        }
    }

    @Nested
    @DisplayName("create method")
    class CreateMethod {

        @Test
        void shouldCreateTableWhenTrackerEnabled() {
            // Given
            instanceProperties = createTestInstanceProperties();
            instanceProperties.set(COMPACTION_TRACKER_ENABLED, "true");
            String instanceId = instanceProperties.get(ID);
            String tableName = DynamoDBCompactionTaskTracker.taskStatusTableName(instanceId);

            // When
            DynamoDBCompactionTaskTrackerCreator.create(instanceProperties, dynamoClient);
            tableCreated = true;

            // Then
            assertThat(dynamoClient.describeTable(tableName))
                    .extracting(DescribeTableResult::getTable)
                    .isNotNull();
        }

        @Test
        void shouldNotCreateTableWhenTrackerDisabled() {
            // Given
            instanceProperties = createTestInstanceProperties();
            instanceProperties.set(COMPACTION_TRACKER_ENABLED, "false");
            String instanceId = instanceProperties.get(ID);
            String tableName = DynamoDBCompactionTaskTracker.taskStatusTableName(instanceId);

            // When
            DynamoDBCompactionTaskTrackerCreator.create(instanceProperties, dynamoClient);

            // Then - table should not exist
            assertThatThrownBy(() -> dynamoClient.describeTable(tableName))
                    .isInstanceOf(ResourceNotFoundException.class);
        }

        @Test
        void shouldCreateTableWithCorrectKeySchema() {
            // Given
            instanceProperties = createTestInstanceProperties();
            instanceProperties.set(COMPACTION_TRACKER_ENABLED, "true");
            String instanceId = instanceProperties.get(ID);
            String tableName = DynamoDBCompactionTaskTracker.taskStatusTableName(instanceId);

            // When
            DynamoDBCompactionTaskTrackerCreator.create(instanceProperties, dynamoClient);
            tableCreated = true;

            // Then
            DescribeTableResult result = dynamoClient.describeTable(tableName);
            assertThat(result.getTable().getKeySchema())
                    .hasSize(2);
            assertThat(result.getTable().getKeySchema())
                    .anyMatch(key -> key.getAttributeName().equals(DynamoDBCompactionTaskStatusFormat.TASK_ID)
                            && key.getKeyType().equals("HASH"));
            assertThat(result.getTable().getKeySchema())
                    .anyMatch(key -> key.getAttributeName().equals(DynamoDBCompactionTaskStatusFormat.UPDATE_TIME)
                            && key.getKeyType().equals("RANGE"));
        }

        @Test
        void shouldCreateTableWithCorrectAttributeDefinitions() {
            // Given
            instanceProperties = createTestInstanceProperties();
            instanceProperties.set(COMPACTION_TRACKER_ENABLED, "true");
            String instanceId = instanceProperties.get(ID);
            String tableName = DynamoDBCompactionTaskTracker.taskStatusTableName(instanceId);

            // When
            DynamoDBCompactionTaskTrackerCreator.create(instanceProperties, dynamoClient);
            tableCreated = true;

            // Then
            DescribeTableResult result = dynamoClient.describeTable(tableName);
            assertThat(result.getTable().getAttributeDefinitions())
                    .hasSize(2);
            assertThat(result.getTable().getAttributeDefinitions())
                    .anyMatch(attr -> attr.getAttributeName().equals(DynamoDBCompactionTaskStatusFormat.TASK_ID)
                            && attr.getAttributeType().equals("S"));
            assertThat(result.getTable().getAttributeDefinitions())
                    .anyMatch(attr -> attr.getAttributeName().equals(DynamoDBCompactionTaskStatusFormat.UPDATE_TIME)
                            && attr.getAttributeType().equals("N"));
        }

        @Test
        void shouldCreateTableUsableByTracker() {
            // Given
            instanceProperties = createTestInstanceProperties();
            instanceProperties.set(COMPACTION_TRACKER_ENABLED, "true");

            // When
            DynamoDBCompactionTaskTrackerCreator.create(instanceProperties, dynamoClient);
            tableCreated = true;

            // Then - verify table is usable by creating a tracker and using it
            DynamoDBCompactionTaskTracker tracker = new DynamoDBCompactionTaskTracker(dynamoClient, instanceProperties);
            assertThat(tracker).isNotNull();
            // Verify we can query without errors
            assertThat(tracker.getTask("non-existent-task")).isNull();
        }
    }

    @Nested
    @DisplayName("tearDown method")
    class TearDownMethod {

        @Test
        void shouldDeleteTableWhenTrackerEnabled() {
            // Given
            instanceProperties = createTestInstanceProperties();
            instanceProperties.set(COMPACTION_TRACKER_ENABLED, "true");
            String instanceId = instanceProperties.get(ID);
            String tableName = DynamoDBCompactionTaskTracker.taskStatusTableName(instanceId);

            DynamoDBCompactionTaskTrackerCreator.create(instanceProperties, dynamoClient);

            // When
            DynamoDBCompactionTaskTrackerCreator.tearDown(instanceProperties, dynamoClient);

            // Then - table should not exist anymore
            assertThatThrownBy(() -> dynamoClient.describeTable(tableName))
                    .isInstanceOf(ResourceNotFoundException.class);
        }

        @Test
        void shouldDoNothingWhenTrackerDisabled() {
            // Given
            instanceProperties = createTestInstanceProperties();
            instanceProperties.set(COMPACTION_TRACKER_ENABLED, "true");
            String instanceId = instanceProperties.get(ID);
            String tableName = DynamoDBCompactionTaskTracker.taskStatusTableName(instanceId);

            DynamoDBCompactionTaskTrackerCreator.create(instanceProperties, dynamoClient);
            tableCreated = true;

            // When - call tearDown with tracker disabled
            instanceProperties.set(COMPACTION_TRACKER_ENABLED, "false");
            DynamoDBCompactionTaskTrackerCreator.tearDown(instanceProperties, dynamoClient);

            // Then - table should still exist
            assertThat(dynamoClient.describeTable(tableName))
                    .extracting(DescribeTableResult::getTable)
                    .isNotNull();
        }
    }

    @Nested
    @DisplayName("create and tearDown lifecycle")
    class CreateAndTearDownLifecycle {

        @Test
        void shouldBeAbleToCreateTableAfterTearDown() {
            // Given
            instanceProperties = createTestInstanceProperties();
            instanceProperties.set(COMPACTION_TRACKER_ENABLED, "true");
            String instanceId = instanceProperties.get(ID);
            String tableName = DynamoDBCompactionTaskTracker.taskStatusTableName(instanceId);

            // Create and tear down
            DynamoDBCompactionTaskTrackerCreator.create(instanceProperties, dynamoClient);
            DynamoDBCompactionTaskTrackerCreator.tearDown(instanceProperties, dynamoClient);

            // When - recreate
            DynamoDBCompactionTaskTrackerCreator.create(instanceProperties, dynamoClient);
            tableCreated = true;

            // Then
            assertThat(dynamoClient.describeTable(tableName))
                    .extracting(DescribeTableResult::getTable)
                    .isNotNull();
        }
    }
}
