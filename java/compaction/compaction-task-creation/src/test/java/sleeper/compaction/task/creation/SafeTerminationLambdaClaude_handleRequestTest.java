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
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;
import software.amazon.awssdk.services.ecs.EcsClient;
import software.amazon.awssdk.services.ecs.paginators.ListContainerInstancesIterable;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.function.Consumer;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Tests for the handleRequest method of SafeTerminationLambda.
 * <p>
 * The handleRequest method (lines 193-202) performs the following:
 * <ul>
 *   <li>Lines 194-195: Creates BufferedReader/BufferedWriter from input/output streams</li>
 *   <li>Line 197: Calls suggestIDsToTerminate with the streams and EC2InstanceDetails iterator</li>
 *   <li>Lines 199-201: Catches IllegalStateException/JsonSyntaxException and logs error</li>
 * </ul>
 * <p>
 * Since the constructor uses AWS SDK defaultClient() methods which require credentials,
 * we use Objenesis to create instances without calling the constructor and inject
 * mock dependencies via reflection. This allows us to test handleRequest directly.
 */
@SuppressWarnings("checkstyle:typeName")
public class SafeTerminationLambdaClaude_handleRequestTest {

    @Test
    void shouldProcessValidInputAndWriteEmptyInstancesWhenNoInstancesInCluster() throws Exception {
        // Given - mock ECS client that returns no container instances
        EcsClient mockEcsClient = createMockEcsClientWithNoInstances();
        SafeTerminationLambda lambda = createLambdaWithDependencies(mockEcsClient, "test-cluster");

        String inputJson = "{\"CapacityToTerminate\": [{\"Capacity\": 5}]}";
        InputStream input = new ByteArrayInputStream(inputJson.getBytes(StandardCharsets.UTF_8));
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Context context = mockContextWithRemainingTime(10000);

        // When
        lambda.handleRequest(input, output, context);

        // Then - should write empty instances JSON
        String outputJson = output.toString(StandardCharsets.UTF_8);
        assertThat(outputJson).isEqualTo("{\"InstanceIDs\":[]}");
    }

    @Test
    void shouldProcessZeroCapacityRequest() throws Exception {
        // Given - mock ECS client that returns no container instances
        EcsClient mockEcsClient = createMockEcsClientWithNoInstances();
        SafeTerminationLambda lambda = createLambdaWithDependencies(mockEcsClient, "test-cluster");

        String inputJson = "{\"CapacityToTerminate\": []}";
        InputStream input = new ByteArrayInputStream(inputJson.getBytes(StandardCharsets.UTF_8));
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Context context = mockContextWithRemainingTime(10000);

        // When
        lambda.handleRequest(input, output, context);

        // Then - should write empty instances JSON
        String outputJson = output.toString(StandardCharsets.UTF_8);
        assertThat(outputJson).isEqualTo("{\"InstanceIDs\":[]}");
    }

    @Test
    void shouldHandleInvalidJsonInputGracefully() throws Exception {
        // Given - mock ECS client (won't be called due to JSON parse failure)
        EcsClient mockEcsClient = createMockEcsClientWithNoInstances();
        SafeTerminationLambda lambda = createLambdaWithDependencies(mockEcsClient, "test-cluster");

        String invalidJson = "not valid json at all";
        InputStream input = new ByteArrayInputStream(invalidJson.getBytes(StandardCharsets.UTF_8));
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Context context = mockContextWithRemainingTime(10000);

        // When - should not throw, exception is caught and logged
        lambda.handleRequest(input, output, context);

        // Then - output should be empty since exception was caught
        String outputJson = output.toString(StandardCharsets.UTF_8);
        assertThat(outputJson).isEmpty();
    }

    @Test
    void shouldHandleMalformedJsonInputGracefully() throws Exception {
        // Given - JSON that parses but is not an object
        EcsClient mockEcsClient = createMockEcsClientWithNoInstances();
        SafeTerminationLambda lambda = createLambdaWithDependencies(mockEcsClient, "test-cluster");

        String malformedJson = "[1, 2, 3]";
        InputStream input = new ByteArrayInputStream(malformedJson.getBytes(StandardCharsets.UTF_8));
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Context context = mockContextWithRemainingTime(10000);

        // When - should not throw, IllegalStateException is caught and logged
        lambda.handleRequest(input, output, context);

        // Then - output should be empty since exception was caught
        String outputJson = output.toString(StandardCharsets.UTF_8);
        assertThat(outputJson).isEmpty();
    }

    @Test
    void shouldHandleEmptyInputStream() throws Exception {
        // Given
        EcsClient mockEcsClient = createMockEcsClientWithNoInstances();
        SafeTerminationLambda lambda = createLambdaWithDependencies(mockEcsClient, "test-cluster");

        InputStream input = new ByteArrayInputStream(new byte[0]);
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Context context = mockContextWithRemainingTime(10000);

        // When - should handle gracefully
        lambda.handleRequest(input, output, context);

        // Then - output should be empty since no valid JSON was provided
        String outputJson = output.toString(StandardCharsets.UTF_8);
        assertThat(outputJson).isEmpty();
    }

    @Test
    void shouldHandleMultipleCapacitiesToTerminate() throws Exception {
        // Given - mock ECS client with no instances
        EcsClient mockEcsClient = createMockEcsClientWithNoInstances();
        SafeTerminationLambda lambda = createLambdaWithDependencies(mockEcsClient, "test-cluster");

        String inputJson = "{\"CapacityToTerminate\": [{\"Capacity\": 2}, {\"Capacity\": 3}]}";
        InputStream input = new ByteArrayInputStream(inputJson.getBytes(StandardCharsets.UTF_8));
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Context context = mockContextWithRemainingTime(10000);

        // When
        lambda.handleRequest(input, output, context);

        // Then - should process and return empty (no instances available)
        String outputJson = output.toString(StandardCharsets.UTF_8);
        assertThat(outputJson).isEqualTo("{\"InstanceIDs\":[]}");
    }

    // ========== Helper methods ==========

    /**
     * Creates a SafeTerminationLambda instance with injected dependencies using reflection.
     * <p>
     * This is necessary because the production constructor uses defaultClient() methods
     * which require AWS credentials. We use Objenesis to create an instance without
     * calling the constructor, then inject the dependencies via reflection.
     * <p>
     * Reflection is used here because there is no other way to test handleRequest:
     * - The class only has a no-arg constructor that requires AWS credentials
     * - There is no test constructor or factory method available
     * - The ecsClient and ecsClusterName fields are private and final
     *
     * @param ecsClient      the mock ECS client
     * @param ecsClusterName the cluster name
     * @return a lambda instance with injected dependencies
     * @throws Exception if reflection fails
     */
    private SafeTerminationLambda createLambdaWithDependencies(EcsClient ecsClient, String ecsClusterName)
            throws Exception {
        // Create instance without calling constructor using Objenesis
        Objenesis objenesis = new ObjenesisStd();
        SafeTerminationLambda lambda = objenesis.newInstance(SafeTerminationLambda.class);

        // Use reflection to set private final fields
        setField(lambda, "ecsClient", ecsClient);
        setField(lambda, "ecsClusterName", ecsClusterName);

        return lambda;
    }

    private void setField(Object target, String fieldName, Object value) throws Exception {
        Field field = SafeTerminationLambda.class.getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(target, value);
    }

    private EcsClient createMockEcsClientWithNoInstances() {
        EcsClient mockClient = mock(EcsClient.class);

        // Create a mock paginator that returns an empty stream
        ListContainerInstancesIterable mockIterable = mock(ListContainerInstancesIterable.class);
        when(mockIterable.stream()).thenReturn(Stream.empty());

        when(mockClient.listContainerInstancesPaginator(any(Consumer.class))).thenReturn(mockIterable);

        return mockClient;
    }

    private Context mockContextWithRemainingTime(int timeInMillis) {
        Context context = mock(Context.class);
        when(context.getRemainingTimeInMillis()).thenReturn(timeInMillis);
        return context;
    }
}
