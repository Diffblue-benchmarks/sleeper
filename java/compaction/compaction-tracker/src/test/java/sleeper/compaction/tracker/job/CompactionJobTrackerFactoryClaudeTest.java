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

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.tracker.compaction.job.CompactionJobTracker;
import sleeper.localstack.test.LocalStackTestBase;

import static org.assertj.core.api.Assertions.assertThat;
import static sleeper.core.properties.instance.CompactionProperty.COMPACTION_TRACKER_ENABLED;
import static sleeper.core.properties.testutils.InstancePropertiesTestHelper.createTestInstanceProperties;

public class CompactionJobTrackerFactoryClaudeTest extends LocalStackTestBase {

    private final InstanceProperties instanceProperties = createTestInstanceProperties();

    @Nested
    @DisplayName("getTracker")
    class GetTracker {

        @Test
        void shouldReturnNoOpTrackerWhenTrackerDisabled() {
            // Given
            instanceProperties.set(COMPACTION_TRACKER_ENABLED, "false");

            // When
            CompactionJobTracker tracker = CompactionJobTrackerFactory.getTracker(dynamoClient, instanceProperties);

            // Then
            assertThat(tracker).isSameAs(CompactionJobTracker.NONE);
        }

        @Test
        void shouldReturnDynamoDBTrackerWhenTrackerEnabled() {
            // Given
            instanceProperties.set(COMPACTION_TRACKER_ENABLED, "true");
            DynamoDBCompactionJobTrackerCreator.create(instanceProperties, dynamoClient);

            // When
            CompactionJobTracker tracker = CompactionJobTrackerFactory.getTracker(dynamoClient, instanceProperties);

            // Then
            assertThat(tracker).isInstanceOf(DynamoDBCompactionJobTracker.class);
        }

        @Test
        void shouldReturnTrackerThatIsNotSameAsNONEWhenEnabled() {
            // Given
            instanceProperties.set(COMPACTION_TRACKER_ENABLED, "true");
            DynamoDBCompactionJobTrackerCreator.create(instanceProperties, dynamoClient);

            // When
            CompactionJobTracker tracker = CompactionJobTrackerFactory.getTracker(dynamoClient, instanceProperties);

            // Then
            assertThat(tracker).isNotSameAs(CompactionJobTracker.NONE);
        }
    }

    @Nested
    @DisplayName("getTrackerWithStronglyConsistentReads")
    class GetTrackerWithStronglyConsistentReads {

        @Test
        void shouldReturnNoOpTrackerWhenTrackerDisabled() {
            // Given
            instanceProperties.set(COMPACTION_TRACKER_ENABLED, "false");

            // When
            CompactionJobTracker tracker = CompactionJobTrackerFactory.getTrackerWithStronglyConsistentReads(
                    dynamoClient, instanceProperties);

            // Then
            assertThat(tracker).isSameAs(CompactionJobTracker.NONE);
        }

        @Test
        void shouldReturnDynamoDBTrackerWhenTrackerEnabled() {
            // Given
            instanceProperties.set(COMPACTION_TRACKER_ENABLED, "true");
            DynamoDBCompactionJobTrackerCreator.create(instanceProperties, dynamoClient);

            // When
            CompactionJobTracker tracker = CompactionJobTrackerFactory.getTrackerWithStronglyConsistentReads(
                    dynamoClient, instanceProperties);

            // Then
            assertThat(tracker).isInstanceOf(DynamoDBCompactionJobTracker.class);
        }

        @Test
        void shouldReturnTrackerThatIsNotSameAsNONEWhenEnabled() {
            // Given
            instanceProperties.set(COMPACTION_TRACKER_ENABLED, "true");
            DynamoDBCompactionJobTrackerCreator.create(instanceProperties, dynamoClient);

            // When
            CompactionJobTracker tracker = CompactionJobTrackerFactory.getTrackerWithStronglyConsistentReads(
                    dynamoClient, instanceProperties);

            // Then
            assertThat(tracker).isNotSameAs(CompactionJobTracker.NONE);
        }
    }

    @Nested
    @DisplayName("Both methods with same disabled configuration")
    class BothMethodsDisabled {

        @BeforeEach
        void setUp() {
            instanceProperties.set(COMPACTION_TRACKER_ENABLED, "false");
        }

        @Test
        void shouldReturnSameNONEInstanceForBothMethods() {
            // When
            CompactionJobTracker tracker1 = CompactionJobTrackerFactory.getTracker(dynamoClient, instanceProperties);
            CompactionJobTracker tracker2 = CompactionJobTrackerFactory.getTrackerWithStronglyConsistentReads(
                    dynamoClient, instanceProperties);

            // Then
            assertThat(tracker1).isSameAs(tracker2);
            assertThat(tracker1).isSameAs(CompactionJobTracker.NONE);
        }
    }

    @Nested
    @DisplayName("Both methods with same enabled configuration")
    class BothMethodsEnabled {

        @BeforeEach
        void setUp() {
            instanceProperties.set(COMPACTION_TRACKER_ENABLED, "true");
            DynamoDBCompactionJobTrackerCreator.create(instanceProperties, dynamoClient);
        }

        @Test
        void shouldReturnDifferentInstancesForBothMethods() {
            // When
            CompactionJobTracker tracker1 = CompactionJobTrackerFactory.getTracker(dynamoClient, instanceProperties);
            CompactionJobTracker tracker2 = CompactionJobTrackerFactory.getTrackerWithStronglyConsistentReads(
                    dynamoClient, instanceProperties);

            // Then
            assertThat(tracker1).isNotSameAs(tracker2);
        }

        @Test
        void shouldReturnDynamoDBTrackerForBothMethods() {
            // When
            CompactionJobTracker tracker1 = CompactionJobTrackerFactory.getTracker(dynamoClient, instanceProperties);
            CompactionJobTracker tracker2 = CompactionJobTrackerFactory.getTrackerWithStronglyConsistentReads(
                    dynamoClient, instanceProperties);

            // Then
            assertThat(tracker1).isInstanceOf(DynamoDBCompactionJobTracker.class);
            assertThat(tracker2).isInstanceOf(DynamoDBCompactionJobTracker.class);
        }
    }
}
