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

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.security.Permission;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Tests for ECSCompactionTaskRunner.main() method.
 * <p>
 * Note: The main() method is designed as an entry point for ECS tasks and creates real AWS clients.
 * Full coverage of the main method (lines 83-126) would require:
 * 1. Refactoring to allow dependency injection of AWS clients
 * 2. A full LocalStack integration test with S3, DynamoDB, SQS infrastructure
 * 3. Creating actual compaction jobs and running them
 * <p>
 * These tests cover the argument validation logic (lines 77-79) by intercepting System.exit calls.
 * The remaining lines involve AWS client creation and CompactionTask execution that cannot be
 * tested without refactoring the main method to support dependency injection.
 */
class ECSCompactionTaskRunnerClaudeMainTest {

    private SecurityManager originalSecurityManager;
    private PrintStream originalErr;
    private ByteArrayOutputStream errContent;

    @BeforeEach
    void setUp() {
        originalSecurityManager = System.getSecurityManager();
        originalErr = System.err;
        errContent = new ByteArrayOutputStream();
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    void tearDown() {
        System.setSecurityManager(originalSecurityManager);
        System.setErr(originalErr);
    }

    @DisplayName("Argument validation")
    @Nested
    class ArgumentValidation {

        @BeforeEach
        void setUpSecurityManager() {
            System.setSecurityManager(new NoExitSecurityManager());
        }

        @Test
        void shouldExitWithCode1WhenNoArgumentsProvided() {
            // When/Then
            assertThatThrownBy(() -> ECSCompactionTaskRunner.main(new String[]{}))
                    .isInstanceOf(ExitException.class)
                    .satisfies(ex -> assertThat(((ExitException) ex).status).isEqualTo(1));

            assertThat(errContent.toString())
                    .contains("Error: must have 1 argument (config bucket), got 0 arguments");
        }

        @Test
        void shouldExitWithCode1WhenTwoArgumentsProvided() {
            // When/Then
            assertThatThrownBy(() -> ECSCompactionTaskRunner.main(new String[]{"bucket1", "bucket2"}))
                    .isInstanceOf(ExitException.class)
                    .satisfies(ex -> assertThat(((ExitException) ex).status).isEqualTo(1));

            assertThat(errContent.toString())
                    .contains("Error: must have 1 argument (config bucket), got 2 arguments")
                    .contains("bucket1,bucket2");
        }

        @Test
        void shouldExitWithCode1WhenThreeArgumentsProvided() {
            // When/Then
            assertThatThrownBy(() -> ECSCompactionTaskRunner.main(new String[]{"a", "b", "c"}))
                    .isInstanceOf(ExitException.class)
                    .satisfies(ex -> assertThat(((ExitException) ex).status).isEqualTo(1));

            assertThat(errContent.toString())
                    .contains("Error: must have 1 argument (config bucket), got 3 arguments")
                    .contains("a,b,c");
        }
    }

    /**
     * Custom SecurityManager that throws ExitException instead of allowing System.exit().
     * This allows tests to verify exit behavior without terminating the JVM.
     */
    private static class NoExitSecurityManager extends SecurityManager {
        @Override
        public void checkPermission(Permission perm) {
            // Allow all permissions except exit
        }

        @Override
        public void checkPermission(Permission perm, Object context) {
            // Allow all permissions except exit
        }

        @Override
        public void checkExit(int status) {
            throw new ExitException(status);
        }
    }

    /**
     * Exception thrown when System.exit() is called, capturing the exit status.
     */
    private static class ExitException extends SecurityException {
        final int status;

        ExitException(int status) {
            super("System.exit(" + status + ") called");
            this.status = status;
        }
    }
}
