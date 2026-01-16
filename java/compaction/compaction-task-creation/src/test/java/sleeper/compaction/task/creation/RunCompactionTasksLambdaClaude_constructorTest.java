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
package sleeper.compaction.task.creation;

import org.junit.jupiter.api.Test;

import sleeper.task.common.QueueMessageCount;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static sleeper.core.properties.instance.CdkDefinedInstanceProperty.CONFIG_BUCKET;

/**
 * Tests for the RunCompactionTasksLambda no-args constructor initialization path.
 * <p>
 * The no-args constructor (lines 42-52) performs the following initialization:
 * <ul>
 *   <li>Line 43: Validates CONFIG_BUCKET environment variable via validateParameter()</li>
 *   <li>Line 44: Creates SQS client using AmazonSQSClientBuilder.defaultClient()</li>
 *   <li>Line 45: Creates ECS client using EcsClient.create()</li>
 *   <li>Line 46: Creates S3 client using AmazonS3ClientBuilder.defaultClient()</li>
 *   <li>Line 47: Creates AutoScaling client using AutoScalingClient.create()</li>
 *   <li>Line 48: Creates EC2 client using Ec2Client.create()</li>
 *   <li>Line 49: Loads instance properties from S3</li>
 *   <li>Line 50: Creates RunCompactionTasks with ECS, AutoScaling, and EC2 clients</li>
 *   <li>Line 51: Creates QueueMessageCount.Client with SQS client</li>
 * </ul>
 * <p>
 * Since the defaultClient() and create() methods use AWS SDK configuration that cannot be
 * easily redirected in unit tests, we focus on testing:
 * <ol>
 *   <li>The validateParameter behavior - verifying the constructor throws when CONFIG_BUCKET
 *       environment variable is not set</li>
 *   <li>The QueueMessageCount factory method that is used in line 51</li>
 * </ol>
 */
@SuppressWarnings("checkstyle:typeName")
public class RunCompactionTasksLambdaClaude_constructorTest {

    @Test
    void shouldThrowWhenConfigBucketEnvironmentVariableIsNotSet() {
        // The constructor calls validateParameter(CONFIG_BUCKET.toEnvironmentVariable())
        // which should throw IllegalArgumentException when the env var is not set.
        // Since we're in a test environment where CONFIG_BUCKET is not set,
        // the constructor should fail immediately at line 43.
        assertThatThrownBy(RunCompactionTasksLambda::new)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Missing environment variable");
    }

    @Test
    void shouldHaveCorrectConfigBucketEnvironmentVariableName() {
        // Verify that the constructor would look for the correct environment variable name
        // This tests that line 43 uses the correct property
        String envVarName = CONFIG_BUCKET.toEnvironmentVariable();
        assertThat(envVarName).isEqualTo("SLEEPER_CONFIG_BUCKET");
    }

    @Test
    void shouldCreateQueueMessageCountClientWithSqsClient() {
        // This verifies line 51: this.queueMessageCount = QueueMessageCount.withSqsClient(sqsClient);
        // We verify the factory method works (without needing a real SQS client for the assertion)
        QueueMessageCount.Client client = QueueMessageCount.withSqsClient(null);
        // The client is a functional interface wrapper; it's not null even with null input
        assertThat(client).isNotNull();
    }

    @Test
    void shouldHaveQueueMessageCountWithApproximateNumberFactory() {
        // Verify QueueMessageCount can be created via factory method
        // This tests the message count object that would be returned by the client created in line 51
        QueueMessageCount messageCount = QueueMessageCount.approximateNumberVisibleAndNotVisible(5, 2);

        assertThat(messageCount.getApproximateNumberOfMessages()).isEqualTo(5);
        assertThat(messageCount.getApproximateNumberOfMessagesNotVisible()).isEqualTo(2);
    }

    @Test
    void shouldHaveQueueMessageCountEquality() {
        // Verify QueueMessageCount equality works correctly
        QueueMessageCount count1 = QueueMessageCount.approximateNumberVisibleAndNotVisible(5, 2);
        QueueMessageCount count2 = QueueMessageCount.approximateNumberVisibleAndNotVisible(5, 2);
        QueueMessageCount count3 = QueueMessageCount.approximateNumberVisibleAndNotVisible(3, 1);

        assertThat(count1).isEqualTo(count2);
        assertThat(count1).isNotEqualTo(count3);
    }

    @Test
    void shouldHaveQueueMessageCountHashCode() {
        // Verify QueueMessageCount hashCode is consistent with equality
        QueueMessageCount count1 = QueueMessageCount.approximateNumberVisibleAndNotVisible(5, 2);
        QueueMessageCount count2 = QueueMessageCount.approximateNumberVisibleAndNotVisible(5, 2);

        assertThat(count1.hashCode()).isEqualTo(count2.hashCode());
    }

    @Test
    void shouldHaveQueueMessageCountToString() {
        // Verify QueueMessageCount toString includes relevant information
        QueueMessageCount count = QueueMessageCount.approximateNumberVisibleAndNotVisible(5, 2);

        String toString = count.toString();
        assertThat(toString).contains("5");
        assertThat(toString).contains("2");
    }
}
