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

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CompactionRunnerDetailsTest {

    @Test
    public void shouldReturnFalseForIsHardwareAccelerated() {
        // Given
        CompactionRunnerDetails runnerDetails = new TestCompactionRunner();

        // When
        boolean result = runnerDetails.isHardwareAccelerated();

        // Then
        assertThat(result).isFalse();
    }

    @Test
    public void shouldReturnJavaForImplementationLanguage() {
        // Given
        CompactionRunnerDetails runnerDetails = new TestCompactionRunner();

        // When
        String result = runnerDetails.implementationLanguage();

        // Then
        assertThat(result).isEqualTo("Java");
    }

    @Test
    public void shouldReturnFalseForSupportsIterators() {
        // Given
        CompactionRunnerDetails runnerDetails = new TestCompactionRunner();

        // When
        boolean result = runnerDetails.supportsIterators();

        // Then
        assertThat(result).isFalse();
    }

    private static class TestCompactionRunner implements CompactionRunnerDetails {
        // Uses default implementations from interface
    }
}
