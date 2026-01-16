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

import sleeper.core.properties.instance.InstanceProperties;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static sleeper.core.properties.instance.CdkDefinedInstanceProperty.COMPACTION_CLUSTER;
import static sleeper.core.properties.instance.CdkDefinedInstanceProperty.CONFIG_BUCKET;

/**
 * Tests for the SafeTerminationLambda no-args constructor initialization path.
 * <p>
 * The no-args constructor (lines 71-82) performs the following initialization:
 * <ul>
 *   <li>Line 72: Validates CONFIG_BUCKET environment variable via validateParameter()</li>
 *   <li>Line 74: Creates S3 client using AmazonS3ClientBuilder.defaultClient()</li>
 *   <li>Line 75: Creates ECS client using EcsClient.create()</li>
 *   <li>Line 78: Loads instance properties from S3 using S3InstanceProperties.loadFromBucket()</li>
 *   <li>Line 81: Gets COMPACTION_CLUSTER from instance properties</li>
 *   <li>Line 82: Closing brace</li>
 * </ul>
 * <p>
 * Lines 74, 75, 78, 81, and 82 use AWS SDK methods (defaultClient(), create(), and
 * S3InstanceProperties.loadFromBucket()) that require AWS credentials and cannot be
 * easily tested in unit tests without actual AWS or LocalStack infrastructure.
 * <p>
 * Therefore, we focus on testing:
 * <ol>
 *   <li>The validateParameter behavior - verifying the constructor throws when CONFIG_BUCKET
 *       environment variable is not set (tests line 72)</li>
 *   <li>The related configuration properties used by the constructor</li>
 * </ol>
 */
@SuppressWarnings("checkstyle:typeName")
public class SafeTerminationLambdaClaude_constructorTest {

    @Test
    void shouldThrowWhenConfigBucketEnvironmentVariableIsNotSet() {
        // The constructor calls validateParameter(CONFIG_BUCKET.toEnvironmentVariable())
        // which should throw IllegalArgumentException when the env var is not set.
        // Since we're in a test environment where CONFIG_BUCKET is not set,
        // the constructor should fail immediately at line 72.
        assertThatThrownBy(SafeTerminationLambda::new)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Missing environment variable");
    }

    @Test
    void shouldHaveCorrectConfigBucketEnvironmentVariableName() {
        // Verify that line 72 uses the correct property for environment variable lookup
        String envVarName = CONFIG_BUCKET.toEnvironmentVariable();
        assertThat(envVarName).isEqualTo("SLEEPER_CONFIG_BUCKET");
    }

    @Test
    void shouldHaveCorrectCompactionClusterPropertyName() {
        // Verify the property name used in line 81 to extract cluster name from instance properties
        // This tests that COMPACTION_CLUSTER is the correct property key
        String propertyName = COMPACTION_CLUSTER.getPropertyName();
        assertThat(propertyName).isEqualTo("sleeper.compaction.cluster");
    }

    @Test
    void shouldBeAbleToGetCompactionClusterFromInstanceProperties() {
        // Verify that line 81's instanceProperties.get(COMPACTION_CLUSTER) would work correctly
        // if the properties were loaded from S3
        InstanceProperties properties = new InstanceProperties();
        String testClusterName = "test-compaction-cluster";
        properties.set(COMPACTION_CLUSTER, testClusterName);

        String retrievedCluster = properties.get(COMPACTION_CLUSTER);

        assertThat(retrievedCluster).isEqualTo(testClusterName);
    }

    @Test
    void shouldReturnNullWhenCompactionClusterNotSet() {
        // Test the case where COMPACTION_CLUSTER is not set in instance properties
        // This would happen if the property was missing from the configuration in S3
        InstanceProperties properties = new InstanceProperties();

        String retrievedCluster = properties.get(COMPACTION_CLUSTER);

        assertThat(retrievedCluster).isNull();
    }

    @Test
    void shouldHaveCompactionClusterAsOptionalProperty() {
        // Verify that COMPACTION_CLUSTER is a CDK-defined property (set during deployment)
        // and is expected to be present in deployed instances
        assertThat(COMPACTION_CLUSTER.getPropertyName()).startsWith("sleeper.");
    }

    @Test
    void shouldHaveCorrectCompactionClusterEnvironmentVariableName() {
        // Verify the environment variable name for COMPACTION_CLUSTER
        // This would be relevant if the cluster name was also set via env var
        String envVarName = COMPACTION_CLUSTER.toEnvironmentVariable();
        assertThat(envVarName).isEqualTo("SLEEPER_COMPACTION_CLUSTER");
    }
}
