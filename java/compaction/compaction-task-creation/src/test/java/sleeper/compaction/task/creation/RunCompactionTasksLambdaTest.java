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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RunCompactionTasksLambdaTest {

    @Test
    void shouldThrowWhenConfigBucketEnvironmentVariableNotSet() {
        // Given / When / Then
        assertThatThrownBy(RunCompactionTasksLambda::new)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Missing environment variable:");
    }

    @Test
    void shouldReturnValueWhenValidatedParameterIsPresent() throws Exception {
        // Given
        Method method = RunCompactionTasksLambda.class.getDeclaredMethod("validateParameter", String.class);
        method.setAccessible(true);
        String envVarName = "PATH";

        // When
        String result = (String) method.invoke(null, envVarName);

        // Then
        assertThat(result).isEqualTo(System.getenv(envVarName));
    }

    @Test
    void shouldThrowWhenValidatedParameterIsNullViaReflection() throws Exception {
        // Given
        Method method = RunCompactionTasksLambda.class.getDeclaredMethod("validateParameter", String.class);
        method.setAccessible(true);

        // When / Then
        assertThatThrownBy(() -> {
            try {
                method.invoke(null, "NON_EXISTENT_ENV_VAR_FOR_TEST_12345");
            } catch (InvocationTargetException e) {
                throw e.getCause();
            }
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Missing environment variable:");
    }
}
