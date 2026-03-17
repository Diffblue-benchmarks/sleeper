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

import com.amazonaws.services.lambda.runtime.ClientContext;
import com.amazonaws.services.lambda.runtime.CognitoIdentity;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.events.ScheduledEvent;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RunCompactionTasksLambdaTest {

    /**
     * Fake Lambda context class.
     */
    public static class FakeContext implements Context {

        @Override
        public String getAwsRequestId() {
            return null;
        }

        @Override
        public String getLogGroupName() {
            return null;
        }

        @Override
        public String getLogStreamName() {
            return null;
        }

        @Override
        public String getFunctionName() {
            return null;
        }

        @Override
        public String getFunctionVersion() {
            return null;
        }

        @Override
        public String getInvokedFunctionArn() {
            return null;
        }

        @Override
        public CognitoIdentity getIdentity() {
            return null;
        }

        @Override
        public ClientContext getClientContext() {
            return null;
        }

        @Override
        public int getRemainingTimeInMillis() {
            return 10000;
        }

        @Override
        public int getMemoryLimitInMB() {
            return 0;
        }

        @Override
        public LambdaLogger getLogger() {
            return null;
        }
    }

    @Test
    void shouldThrowExceptionWhenEnvironmentVariableIsNull() throws Exception {
        // Given
        String parameterName = "NON_EXISTENT_ENV_VAR_12345";

        // When/Then
        assertThatThrownBy(() -> invokeValidateParameter(parameterName))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Missing environment variable");
    }

    @Test
    void shouldReturnEnvironmentVariableWhenPresent() throws Exception {
        // Given - use an environment variable that exists (PATH should always exist)
        String parameterName = "PATH";

        // When
        String result = invokeValidateParameter(parameterName);

        // Then
        assertThat(result).isNotNull();
        assertThat(result).isNotEmpty();
    }

    @Test
    void shouldThrowExceptionWhenConstructorCalledWithMissingConfigBucket() {
        // Given - no CONFIG_BUCKET environment variable set
        // (assuming it's not set in the test environment)

        // When/Then
        assertThatThrownBy(() -> new RunCompactionTasksLambda())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Missing environment variable");
    }

    /**
     * Helper method to invoke the private validateParameter method using reflection.
     * Unwraps InvocationTargetException to get the actual exception thrown.
     */
    private String invokeValidateParameter(String parameterName) throws Exception {
        Method method = RunCompactionTasksLambda.class.getDeclaredMethod("validateParameter", String.class);
        method.setAccessible(true);
        try {
            return (String) method.invoke(null, parameterName);
        } catch (java.lang.reflect.InvocationTargetException e) {
            // Unwrap the actual exception
            Throwable cause = e.getCause();
            if (cause instanceof RuntimeException) {
                throw (RuntimeException) cause;
            } else if (cause instanceof Exception) {
                throw (Exception) cause;
            } else {
                throw new RuntimeException(cause);
            }
        }
    }
}
