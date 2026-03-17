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
package sleeper.compaction.job.execution;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import software.amazon.awssdk.services.ecs.EcsClient;

import sleeper.core.properties.instance.InstanceProperties;

import static org.assertj.core.api.Assertions.assertThatCode;
import static sleeper.core.properties.instance.CompactionProperty.COMPACTION_ECS_LAUNCHTYPE;
import static sleeper.core.properties.testutils.InstancePropertiesTestHelper.createTestInstanceProperties;

@DisplayName("ECSCompactionTaskRunner.logEC2Metadata")
public class ECSCompactionTaskRunnerLogEC2MetadataIT {

    @Nested
    @DisplayName("When launch type is not EC2")
    class WhenLaunchTypeIsNotEC2 {
        @Test
        void shouldHandleFargateLaunchType() {
            // Given
            InstanceProperties instanceProperties = createTestInstanceProperties();
            instanceProperties.set(COMPACTION_ECS_LAUNCHTYPE, "FARGATE");

            // When / Then
            assertThatCode(() -> ECSCompactionTaskRunner.logEC2Metadata(instanceProperties, null))
                    .doesNotThrowAnyException();
        }

        @Test
        void shouldHandleExternalLaunchType() {
            // Given
            InstanceProperties instanceProperties = createTestInstanceProperties();
            instanceProperties.set(COMPACTION_ECS_LAUNCHTYPE, "EXTERNAL");

            // When / Then
            assertThatCode(() -> ECSCompactionTaskRunner.logEC2Metadata(instanceProperties, null))
                    .doesNotThrowAnyException();
        }
    }

    @Nested
    @DisplayName("When launch type is EC2")
    class WhenLaunchTypeIsEC2 {
        @Test
        void shouldHandleNullEcsClient() {
            // Given
            InstanceProperties instanceProperties = createTestInstanceProperties();
            instanceProperties.set(COMPACTION_ECS_LAUNCHTYPE, "EC2");

            // When / Then
            assertThatCode(() -> ECSCompactionTaskRunner.logEC2Metadata(instanceProperties, null))
                    .doesNotThrowAnyException();
        }

        @Test
        void shouldHandleEC2LaunchTypeWithLowerCase() {
            // Given
            InstanceProperties instanceProperties = createTestInstanceProperties();
            instanceProperties.set(COMPACTION_ECS_LAUNCHTYPE, "ec2");

            // When / Then
            assertThatCode(() -> ECSCompactionTaskRunner.logEC2Metadata(instanceProperties, null))
                    .doesNotThrowAnyException();
        }

        @Test
        void shouldHandleEC2LaunchTypeWithMixedCase() {
            // Given
            InstanceProperties instanceProperties = createTestInstanceProperties();
            instanceProperties.set(COMPACTION_ECS_LAUNCHTYPE, "Ec2");

            // When / Then
            assertThatCode(() -> ECSCompactionTaskRunner.logEC2Metadata(instanceProperties, null))
                    .doesNotThrowAnyException();
        }

        @Test
        void shouldHandleNonNullEcsClientWhenMetadataNotAvailable() {
            // Given
            InstanceProperties instanceProperties = createTestInstanceProperties();
            instanceProperties.set(COMPACTION_ECS_LAUNCHTYPE, "EC2");
            EcsClient ecsClient = EcsClient.builder()
                    .region(software.amazon.awssdk.regions.Region.US_EAST_1)
                    .build();

            // When / Then
            try {
                assertThatCode(() -> ECSCompactionTaskRunner.logEC2Metadata(instanceProperties, ecsClient))
                        .doesNotThrowAnyException();
            } finally {
                ecsClient.close();
            }
        }
    }
}
