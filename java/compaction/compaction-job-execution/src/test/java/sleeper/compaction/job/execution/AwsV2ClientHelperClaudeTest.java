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
import software.amazon.awssdk.auth.credentials.AnonymousCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ecs.EcsClient;
import software.amazon.awssdk.services.ecs.EcsClientBuilder;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for the AwsV2ClientHelper class.
 */
class AwsV2ClientHelperClaudeTest {

    @DisplayName("Build AWS v2 client")
    @Nested
    class BuildAwsV2Client {

        @Test
        void shouldBuildClientWhenEndpointEnvVarIsNotSet() {
            // Given
            // AWS_ENDPOINT_URL environment variable is not set in test environment
            EcsClientBuilder builder = EcsClient.builder()
                    .region(Region.US_EAST_1)
                    .credentialsProvider(AnonymousCredentialsProvider.create());

            // When
            EcsClient client = AwsV2ClientHelper.buildAwsV2Client(builder);

            // Then
            assertThat(client).isNotNull();
            client.close();
        }

        @Test
        void shouldBuildClientWithDifferentRegion() {
            // Given
            // AWS_ENDPOINT_URL environment variable is not set in test environment
            // This tests that the generic method works with different configurations
            EcsClientBuilder ecsBuilder = EcsClient.builder()
                    .region(Region.EU_WEST_1)
                    .credentialsProvider(AnonymousCredentialsProvider.create());

            // When
            EcsClient ecsClient = AwsV2ClientHelper.buildAwsV2Client(ecsBuilder);

            // Then
            assertThat(ecsClient).isNotNull();
            ecsClient.close();
        }
    }
}
