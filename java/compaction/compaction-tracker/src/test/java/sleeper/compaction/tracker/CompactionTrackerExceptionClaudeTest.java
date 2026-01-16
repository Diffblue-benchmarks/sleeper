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
package sleeper.compaction.tracker;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CompactionTrackerExceptionClaudeTest {

    @Nested
    @DisplayName("Constructor with message and cause")
    class ConstructorWithMessageAndCause {

        @Test
        void shouldStoreMessage() {
            // Given
            String message = "Test error message";
            Throwable cause = new RuntimeException("Original cause");

            // When
            CompactionTrackerException exception = new CompactionTrackerException(message, cause);

            // Then
            assertThat(exception.getMessage()).isEqualTo(message);
        }

        @Test
        void shouldStoreCause() {
            // Given
            String message = "Test error message";
            RuntimeException cause = new RuntimeException("Original cause");

            // When
            CompactionTrackerException exception = new CompactionTrackerException(message, cause);

            // Then
            assertThat(exception.getCause()).isSameAs(cause);
        }

        @Test
        void shouldBeARuntimeException() {
            // Given
            String message = "Test error message";
            Throwable cause = new RuntimeException("Original cause");

            // When
            CompactionTrackerException exception = new CompactionTrackerException(message, cause);

            // Then
            assertThat(exception).isInstanceOf(RuntimeException.class);
        }

        @Test
        void shouldBeThrowable() {
            // Given
            String message = "Test error message";
            Throwable cause = new RuntimeException("Original cause");

            // When/Then
            assertThatThrownBy(() -> {
                throw new CompactionTrackerException(message, cause);
            }).isInstanceOf(CompactionTrackerException.class)
                    .hasMessage(message)
                    .hasCause(cause);
        }

        @Test
        void shouldHandleNullMessage() {
            // Given
            String message = null;
            Throwable cause = new RuntimeException("Original cause");

            // When
            CompactionTrackerException exception = new CompactionTrackerException(message, cause);

            // Then
            assertThat(exception.getMessage()).isNull();
            assertThat(exception.getCause()).isSameAs(cause);
        }

        @Test
        void shouldHandleNullCause() {
            // Given
            String message = "Test error message";
            Throwable cause = null;

            // When
            CompactionTrackerException exception = new CompactionTrackerException(message, cause);

            // Then
            assertThat(exception.getMessage()).isEqualTo(message);
            assertThat(exception.getCause()).isNull();
        }

        @Test
        void shouldHandleBothNullMessageAndCause() {
            // When
            CompactionTrackerException exception = new CompactionTrackerException(null, null);

            // Then
            assertThat(exception.getMessage()).isNull();
            assertThat(exception.getCause()).isNull();
        }

        @Test
        void shouldPreserveNestedCauseChain() {
            // Given
            IllegalArgumentException rootCause = new IllegalArgumentException("Root cause");
            RuntimeException intermediateCause = new RuntimeException("Intermediate cause", rootCause);
            String message = "Top level error";

            // When
            CompactionTrackerException exception = new CompactionTrackerException(message, intermediateCause);

            // Then
            assertThat(exception.getCause()).isSameAs(intermediateCause);
            assertThat(exception.getCause().getCause()).isSameAs(rootCause);
        }

        @Test
        void shouldWorkWithDifferentExceptionTypes() {
            // Given - test with various exception types as causes
            IOException ioCause = new IOException("IO error");
            IllegalStateException stateCause = new IllegalStateException("State error");
            NullPointerException npeCause = new NullPointerException("NPE");

            // When
            CompactionTrackerException ioException = new CompactionTrackerException("IO failure", ioCause);
            CompactionTrackerException stateException = new CompactionTrackerException("State failure", stateCause);
            CompactionTrackerException npeException = new CompactionTrackerException("NPE failure", npeCause);

            // Then
            assertThat(ioException.getCause()).isInstanceOf(IOException.class);
            assertThat(stateException.getCause()).isInstanceOf(IllegalStateException.class);
            assertThat(npeException.getCause()).isInstanceOf(NullPointerException.class);
        }

        @Test
        void shouldWorkWithEmptyMessage() {
            // Given
            String message = "";
            Throwable cause = new RuntimeException("Cause");

            // When
            CompactionTrackerException exception = new CompactionTrackerException(message, cause);

            // Then
            assertThat(exception.getMessage()).isEmpty();
        }

        @Test
        void shouldPreserveSpecialCharactersInMessage() {
            // Given
            String message = "Error: failed with special chars \n\t'\"<>&";
            Throwable cause = new RuntimeException("Cause");

            // When
            CompactionTrackerException exception = new CompactionTrackerException(message, cause);

            // Then
            assertThat(exception.getMessage()).isEqualTo(message);
        }

        @Test
        void shouldPreserveUnicodeInMessage() {
            // Given
            String message = "Error with unicode: \u00e9\u00e8\u00ea \u4e2d\u6587";
            Throwable cause = new RuntimeException("Cause");

            // When
            CompactionTrackerException exception = new CompactionTrackerException(message, cause);

            // Then
            assertThat(exception.getMessage()).isEqualTo(message);
        }
    }

    // Inner exception class for testing
    private static class IOException extends Exception {
        IOException(String message) {
            super(message);
        }
    }
}
