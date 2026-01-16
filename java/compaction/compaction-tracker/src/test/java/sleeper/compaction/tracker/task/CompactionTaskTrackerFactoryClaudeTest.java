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

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.tracker.compaction.task.CompactionTaskTracker;
import sleeper.localstack.test.LocalStackTestBase;

import static org.assertj.core.api.Assertions.assertThat;
import static sleeper.core.properties.instance.CompactionProperty.COMPACTION_TRACKER_ENABLED;
import static sleeper.core.properties.testutils.InstancePropertiesTestHelper.createTestInstanceProperties;

public class CompactionTaskTrackerFactoryClaudeTest extends LocalStackTestBase {

    private final InstanceProperties instanceProperties = createTestInstanceProperties();

    @Nested
    @DisplayName("getTracker")
    class GetTracker {

        @Test
        void shouldReturnNoOpTrackerWhenTrackerDisabled() {
            // Given
            instanceProperties.set(COMPACTION_TRACKER_ENABLED, "false");

            // When
            CompactionTaskTracker tracker = CompactionTaskTrackerFactory.getTracker(dynamoClient, instanceProperties);

            // Then
            assertThat(tracker).isSameAs(CompactionTaskTracker.NONE);
        }

        @Test
        void shouldReturnDynamoDBTrackerWhenTrackerEnabled() {
            // Given
            instanceProperties.set(COMPACTION_TRACKER_ENABLED, "true");
            DynamoDBCompactionTaskTrackerCreator.create(instanceProperties, dynamoClient);

            // When
            CompactionTaskTracker tracker = CompactionTaskTrackerFactory.getTracker(dynamoClient, instanceProperties);

            // Then
            assertThat(tracker).isInstanceOf(DynamoDBCompactionTaskTracker.class);
        }

        @Test
        void shouldReturnTrackerThatIsNotSameAsNONEWhenEnabled() {
            // Given
            instanceProperties.set(COMPACTION_TRACKER_ENABLED, "true");
            DynamoDBCompactionTaskTrackerCreator.create(instanceProperties, dynamoClient);

            // When
            CompactionTaskTracker tracker = CompactionTaskTrackerFactory.getTracker(dynamoClient, instanceProperties);

            // Then
            assertThat(tracker).isNotSameAs(CompactionTaskTracker.NONE);
        }
    }
}
