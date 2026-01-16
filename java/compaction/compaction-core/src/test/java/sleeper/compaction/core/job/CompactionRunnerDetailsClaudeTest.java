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
package sleeper.compaction.core.job;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CompactionRunnerDetailsClaudeTest {

    /**
     * A minimal implementation of CompactionRunnerDetails that uses only the default methods.
     * This allows us to test the default behavior without overriding anything.
     */
    private static class DefaultCompactionRunnerDetails implements CompactionRunnerDetails {
        // No methods overridden - all defaults are used
    }

    /**
     * A custom implementation that overrides all methods with non-default values.
     * Used to verify that the interface allows custom implementations.
     */
    private static class CustomCompactionRunnerDetails implements CompactionRunnerDetails {
        @Override
        public boolean isHardwareAccelerated() {
            return true;
        }

        @Override
        public String implementationLanguage() {
            return "Rust";
        }

        @Override
        public boolean supportsIterators() {
            return true;
        }
    }

    @Nested
    @DisplayName("Default implementation behavior")
    class DefaultImplementationBehavior {

        private final CompactionRunnerDetails details = new DefaultCompactionRunnerDetails();

        @Test
        void shouldReturnFalseForIsHardwareAcceleratedByDefault() {
            // When/Then
            assertThat(details.isHardwareAccelerated()).isFalse();
        }

        @Test
        void shouldReturnJavaForImplementationLanguageByDefault() {
            // When/Then
            assertThat(details.implementationLanguage()).isEqualTo("Java");
        }

        @Test
        void shouldReturnFalseForSupportsIteratorsByDefault() {
            // When/Then
            assertThat(details.supportsIterators()).isFalse();
        }
    }

    @Nested
    @DisplayName("Custom implementation behavior")
    class CustomImplementationBehavior {

        private final CompactionRunnerDetails details = new CustomCompactionRunnerDetails();

        @Test
        void shouldReturnTrueForIsHardwareAcceleratedWhenOverridden() {
            // When/Then
            assertThat(details.isHardwareAccelerated()).isTrue();
        }

        @Test
        void shouldReturnCustomLanguageWhenImplementationLanguageIsOverridden() {
            // When/Then
            assertThat(details.implementationLanguage()).isEqualTo("Rust");
        }

        @Test
        void shouldReturnTrueForSupportsIteratorsWhenOverridden() {
            // When/Then
            assertThat(details.supportsIterators()).isTrue();
        }
    }

    @Nested
    @DisplayName("Anonymous implementation")
    class AnonymousImplementation {

        @Test
        void shouldAllowAnonymousImplementationWithDefaults() {
            // Given
            CompactionRunnerDetails details = new CompactionRunnerDetails() {
                // All defaults used
            };

            // When/Then
            assertThat(details.isHardwareAccelerated()).isFalse();
            assertThat(details.implementationLanguage()).isEqualTo("Java");
            assertThat(details.supportsIterators()).isFalse();
        }

        @Test
        void shouldAllowAnonymousImplementationWithPartialOverride() {
            // Given
            CompactionRunnerDetails details = new CompactionRunnerDetails() {
                @Override
                public boolean supportsIterators() {
                    return true;
                }
            };

            // When/Then
            assertThat(details.isHardwareAccelerated()).isFalse(); // default
            assertThat(details.implementationLanguage()).isEqualTo("Java"); // default
            assertThat(details.supportsIterators()).isTrue(); // overridden
        }
    }
}
