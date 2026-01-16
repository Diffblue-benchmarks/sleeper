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

import com.amazonaws.services.lambda.runtime.Context;
import org.junit.jupiter.api.Test;

import sleeper.task.common.EC2InstanceDetails;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.time.Instant;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static sleeper.core.properties.instance.CdkDefinedInstanceProperty.CONFIG_BUCKET;

/**
 * Tests for SafeTerminationLambda.
 * <p>
 * The class has static methods for processing termination requests which can be tested
 * directly without AWS dependencies. The constructor and handleRequest require AWS clients
 * and environment configuration.
 */
class SafeTerminationLambdaClaudeTest {

    // ========== Constructor tests ==========

    @Test
    void shouldThrowWhenConfigBucketEnvironmentVariableIsNotSet() {
        // The constructor calls validateParameter(CONFIG_BUCKET.toEnvironmentVariable())
        // which should throw IllegalArgumentException when the env var is not set
        assertThatThrownBy(SafeTerminationLambda::new)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Missing environment variable");
    }

    @Test
    void shouldHaveCorrectConfigBucketEnvironmentVariableName() {
        // Verify the constructor would look for the correct environment variable name
        String envVarName = CONFIG_BUCKET.toEnvironmentVariable();
        assertThat(envVarName).isEqualTo("SLEEPER_CONFIG_BUCKET");
    }

    // ========== totalTerminations tests ==========

    @Test
    void shouldReturnZeroForEmptyCapacityArray() {
        String json = "{\"CapacityToTerminate\": []}";
        int result = SafeTerminationLambda.totalTerminations(new StringReader(json));
        assertThat(result).isZero();
    }

    @Test
    void shouldReturnCapacityForSingleElement() {
        String json = "{\"CapacityToTerminate\": [{\"Capacity\": 5}]}";
        int result = SafeTerminationLambda.totalTerminations(new StringReader(json));
        assertThat(result).isEqualTo(5);
    }

    @Test
    void shouldSumCapacitiesForMultipleElements() {
        String json = "{\"CapacityToTerminate\": [{\"Capacity\": 3}, {\"Capacity\": 7}, {\"Capacity\": 2}]}";
        int result = SafeTerminationLambda.totalTerminations(new StringReader(json));
        assertThat(result).isEqualTo(12);
    }

    @Test
    void shouldThrowForNullReader() {
        assertThatThrownBy(() -> SafeTerminationLambda.totalTerminations(null))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void shouldThrowForInvalidJson() {
        // JSON parses as a string literal "not", then getAsJsonObject throws IllegalStateException
        String invalidJson = "not valid json";
        assertThatThrownBy(() -> SafeTerminationLambda.totalTerminations(new StringReader(invalidJson)))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void shouldThrowForMissingCapacityToTerminateField() {
        String json = "{\"other\": []}";
        assertThatThrownBy(() -> SafeTerminationLambda.totalTerminations(new StringReader(json)))
                .isInstanceOf(NullPointerException.class);
    }

    // ========== findEmptyInstances tests ==========

    @Test
    void shouldReturnEmptySetWhenNoInstances() {
        Context context = mockContextWithRemainingTime(10000);
        Set<String> result = SafeTerminationLambda.findEmptyInstances(Collections.emptyList(), 5, context);
        assertThat(result).isEmpty();
    }

    @Test
    void shouldFindEmptyInstanceWithNoTasks() {
        Context context = mockContextWithRemainingTime(10000);
        EC2InstanceDetails emptyInstance = createInstance("i-empty", 0, 0);
        List<EC2InstanceDetails> instances = Collections.singletonList(emptyInstance);

        Set<String> result = SafeTerminationLambda.findEmptyInstances(instances, 5, context);

        assertThat(result).containsExactly("i-empty");
    }

    @Test
    void shouldNotIncludeInstancesWithRunningTasks() {
        Context context = mockContextWithRemainingTime(10000);
        EC2InstanceDetails runningInstance = createInstance("i-running", 1, 0);
        List<EC2InstanceDetails> instances = Collections.singletonList(runningInstance);

        Set<String> result = SafeTerminationLambda.findEmptyInstances(instances, 5, context);

        assertThat(result).isEmpty();
    }

    @Test
    void shouldNotIncludeInstancesWithPendingTasks() {
        Context context = mockContextWithRemainingTime(10000);
        EC2InstanceDetails pendingInstance = createInstance("i-pending", 0, 1);
        List<EC2InstanceDetails> instances = Collections.singletonList(pendingInstance);

        Set<String> result = SafeTerminationLambda.findEmptyInstances(instances, 5, context);

        assertThat(result).isEmpty();
    }

    @Test
    void shouldNotIncludeInstancesWithBothRunningAndPendingTasks() {
        Context context = mockContextWithRemainingTime(10000);
        EC2InstanceDetails busyInstance = createInstance("i-busy", 2, 3);
        List<EC2InstanceDetails> instances = Collections.singletonList(busyInstance);

        Set<String> result = SafeTerminationLambda.findEmptyInstances(instances, 5, context);

        assertThat(result).isEmpty();
    }

    @Test
    void shouldFilterOnlyEmptyInstances() {
        Context context = mockContextWithRemainingTime(10000);
        EC2InstanceDetails empty1 = createInstance("i-empty1", 0, 0);
        EC2InstanceDetails running = createInstance("i-running", 1, 0);
        EC2InstanceDetails empty2 = createInstance("i-empty2", 0, 0);
        EC2InstanceDetails pending = createInstance("i-pending", 0, 2);
        List<EC2InstanceDetails> instances = Arrays.asList(empty1, running, empty2, pending);

        Set<String> result = SafeTerminationLambda.findEmptyInstances(instances, 10, context);

        assertThat(result).containsExactlyInAnyOrder("i-empty1", "i-empty2");
    }

    @Test
    void shouldLimitResultsToSuggestedSize() {
        Context context = mockContextWithRemainingTime(10000);
        EC2InstanceDetails empty1 = createInstance("i-empty1", 0, 0);
        EC2InstanceDetails empty2 = createInstance("i-empty2", 0, 0);
        EC2InstanceDetails empty3 = createInstance("i-empty3", 0, 0);
        List<EC2InstanceDetails> instances = Arrays.asList(empty1, empty2, empty3);

        Set<String> result = SafeTerminationLambda.findEmptyInstances(instances, 2, context);

        assertThat(result).hasSize(2);
    }

    @Test
    void shouldReturnSingleInstanceWhenSuggestedSizeIsZero() {
        // The loop adds first, then checks >= 0 which immediately breaks
        // So one instance gets added before the break
        Context context = mockContextWithRemainingTime(10000);
        EC2InstanceDetails emptyInstance = createInstance("i-empty", 0, 0);
        List<EC2InstanceDetails> instances = Collections.singletonList(emptyInstance);

        Set<String> result = SafeTerminationLambda.findEmptyInstances(instances, 0, context);

        assertThat(result).containsExactly("i-empty");
    }

    @Test
    void shouldStopWhenRunningOutOfTime() {
        // The time check happens after the instance is added, so the instance that triggers
        // the timeout is still included in the result before breaking
        EC2InstanceDetails empty1 = createInstance("i-empty1", 0, 0);
        EC2InstanceDetails empty2 = createInstance("i-empty2", 0, 0);
        EC2InstanceDetails empty3 = createInstance("i-empty3", 0, 0);
        List<EC2InstanceDetails> instances = Arrays.asList(empty1, empty2, empty3);

        // Context that returns low time after second iteration
        Context context = mock(Context.class);
        when(context.getRemainingTimeInMillis())
                .thenReturn(500)  // First check: enough time, continue
                .thenReturn(100); // Second check: below SAFE_TIME_LIMIT (200), break

        Set<String> result = SafeTerminationLambda.findEmptyInstances(instances, 10, context);

        // Should stop after second instance due to time constraint (both i-empty1 and i-empty2 are added)
        // i-empty3 is NOT processed
        assertThat(result).containsExactlyInAnyOrder("i-empty1", "i-empty2");
    }

    @Test
    void shouldThrowForNullDetailsIterable() {
        Context context = mockContextWithRemainingTime(10000);
        assertThatThrownBy(() -> SafeTerminationLambda.findEmptyInstances(null, 5, context))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining("detailsIt");
    }

    @Test
    void shouldThrowForNullContext() {
        List<EC2InstanceDetails> instances = Collections.emptyList();
        assertThatThrownBy(() -> SafeTerminationLambda.findEmptyInstances(instances, 5, null))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining("context");
    }

    @Test
    void shouldThrowForNegativeSuggestedSize() {
        Context context = mockContextWithRemainingTime(10000);
        List<EC2InstanceDetails> instances = Collections.emptyList();
        assertThatThrownBy(() -> SafeTerminationLambda.findEmptyInstances(instances, -1, context))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("suggested size < 0");
    }

    // ========== suggestIDsToTerminate tests ==========

    @Test
    void shouldWriteEmptyInstancesJsonWhenNoEmptyInstances() throws IOException {
        Context context = mockContextWithRemainingTime(10000);
        String inputJson = "{\"CapacityToTerminate\": [{\"Capacity\": 2}]}";
        StringWriter outputWriter = new StringWriter();

        EC2InstanceDetails runningInstance = createInstance("i-running", 1, 0);
        List<EC2InstanceDetails> instances = Collections.singletonList(runningInstance);

        SafeTerminationLambda.suggestIDsToTerminate(
                new StringReader(inputJson),
                outputWriter,
                instances,
                context);

        assertThat(outputWriter.toString()).isEqualTo("{\"InstanceIDs\":[]}");
    }

    @Test
    void shouldWriteSuggestedInstanceIdsToOutput() throws IOException {
        Context context = mockContextWithRemainingTime(10000);
        String inputJson = "{\"CapacityToTerminate\": [{\"Capacity\": 2}]}";
        StringWriter outputWriter = new StringWriter();

        EC2InstanceDetails empty1 = createInstance("i-empty1", 0, 0);
        EC2InstanceDetails empty2 = createInstance("i-empty2", 0, 0);
        List<EC2InstanceDetails> instances = Arrays.asList(empty1, empty2);

        SafeTerminationLambda.suggestIDsToTerminate(
                new StringReader(inputJson),
                outputWriter,
                instances,
                context);

        // TreeSet gives sorted order, so i-empty1 comes before i-empty2
        assertThat(outputWriter.toString()).isEqualTo("{\"InstanceIDs\":[\"i-empty1\",\"i-empty2\"]}");
    }

    @Test
    void shouldLimitSuggestedInstancesToRequestedCapacity() throws IOException {
        Context context = mockContextWithRemainingTime(10000);
        String inputJson = "{\"CapacityToTerminate\": [{\"Capacity\": 1}]}";
        StringWriter outputWriter = new StringWriter();

        EC2InstanceDetails empty1 = createInstance("i-empty1", 0, 0);
        EC2InstanceDetails empty2 = createInstance("i-empty2", 0, 0);
        EC2InstanceDetails empty3 = createInstance("i-empty3", 0, 0);
        List<EC2InstanceDetails> instances = Arrays.asList(empty1, empty2, empty3);

        SafeTerminationLambda.suggestIDsToTerminate(
                new StringReader(inputJson),
                outputWriter,
                instances,
                context);

        // Should only suggest 1 instance (matching capacity)
        String output = outputWriter.toString();
        assertThat(output).contains("InstanceIDs");
        // Count the number of instance IDs - should be exactly 1
        assertThat(output.split("i-empty").length - 1).isEqualTo(1);
    }

    @Test
    void shouldSumMultipleCapacitiesWhenSuggesting() throws IOException {
        Context context = mockContextWithRemainingTime(10000);
        String inputJson = "{\"CapacityToTerminate\": [{\"Capacity\": 1}, {\"Capacity\": 2}]}";
        StringWriter outputWriter = new StringWriter();

        EC2InstanceDetails empty1 = createInstance("i-empty1", 0, 0);
        EC2InstanceDetails empty2 = createInstance("i-empty2", 0, 0);
        EC2InstanceDetails empty3 = createInstance("i-empty3", 0, 0);
        List<EC2InstanceDetails> instances = Arrays.asList(empty1, empty2, empty3);

        SafeTerminationLambda.suggestIDsToTerminate(
                new StringReader(inputJson),
                outputWriter,
                instances,
                context);

        // Should suggest all 3 instances (matching total capacity of 3)
        assertThat(outputWriter.toString()).isEqualTo("{\"InstanceIDs\":[\"i-empty1\",\"i-empty2\",\"i-empty3\"]}");
    }

    @Test
    void shouldThrowForNullInput() {
        Context context = mockContextWithRemainingTime(10000);
        StringWriter outputWriter = new StringWriter();
        List<EC2InstanceDetails> instances = Collections.emptyList();

        assertThatThrownBy(() -> SafeTerminationLambda.suggestIDsToTerminate(
                null, outputWriter, instances, context))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining("input");
    }

    @Test
    void shouldThrowForNullOutput() {
        Context context = mockContextWithRemainingTime(10000);
        String inputJson = "{\"CapacityToTerminate\": []}";
        List<EC2InstanceDetails> instances = Collections.emptyList();

        assertThatThrownBy(() -> SafeTerminationLambda.suggestIDsToTerminate(
                new StringReader(inputJson), null, instances, context))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining("output");
    }

    @Test
    void shouldThrowForNullDetailsItInSuggestIds() {
        Context context = mockContextWithRemainingTime(10000);
        String inputJson = "{\"CapacityToTerminate\": []}";
        StringWriter outputWriter = new StringWriter();

        assertThatThrownBy(() -> SafeTerminationLambda.suggestIDsToTerminate(
                new StringReader(inputJson), outputWriter, null, context))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining("detailsIt");
    }

    @Test
    void shouldThrowForNullContextInSuggestIds() {
        String inputJson = "{\"CapacityToTerminate\": []}";
        StringWriter outputWriter = new StringWriter();
        List<EC2InstanceDetails> instances = Collections.emptyList();

        assertThatThrownBy(() -> SafeTerminationLambda.suggestIDsToTerminate(
                new StringReader(inputJson), outputWriter, instances, null))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining("context");
    }

    // ========== Helper methods ==========

    private Context mockContextWithRemainingTime(int timeInMillis) {
        Context context = mock(Context.class);
        when(context.getRemainingTimeInMillis()).thenReturn(timeInMillis);
        return context;
    }

    private EC2InstanceDetails createInstance(String instanceId, int runningTasks, int pendingTasks) {
        return new EC2InstanceDetails(
                instanceId,
                "arn:aws:ecs:us-east-1:123456789012:container-instance/" + instanceId,
                Instant.now(),
                1024,   // availableCPU
                2048,   // availableRAM
                2048,   // totalCPU
                4096,   // totalRAM
                runningTasks,
                pendingTasks);
    }
}
