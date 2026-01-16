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
package sleeper.compaction.core.task;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for the static initializer of StateStoreWaitForFiles,
 * covering the static field declarations on lines 40, 43, and 44.
 */
public class StateStoreWaitForFilesClaudeClinitTest {

    @Nested
    @DisplayName("Static constants initialization")
    class StaticConstantsInitialization {

        @Test
        void shouldInitializeLogger() {
            // When - accessing the LOGGER static field triggers class initialization (line 40)
            // Then - LOGGER should be initialized and not null
            assertThat(StateStoreWaitForFiles.LOGGER).isNotNull();
        }

        @Test
        void shouldInitializeJobAssignmentWaitAttempts() {
            // When - accessing JOB_ASSIGNMENT_WAIT_ATTEMPTS
            // Then - should have expected value of 10
            assertThat(StateStoreWaitForFiles.JOB_ASSIGNMENT_WAIT_ATTEMPTS).isEqualTo(10);
        }

        @Test
        void shouldInitializeJobAssignmentWaitRange() {
            // When - accessing JOB_ASSIGNMENT_WAIT_RANGE triggers class initialization (line 43)
            // Then - should be initialized with correct wait range values
            assertThat(StateStoreWaitForFiles.JOB_ASSIGNMENT_WAIT_RANGE).isNotNull();
            // The wait range is created with firstAndMaxWaitCeilingSecs(4, 60)
            assertThat(StateStoreWaitForFiles.JOB_ASSIGNMENT_WAIT_RANGE.getFirstWaitCeiling())
                    .isEqualTo(Duration.ofSeconds(4));
            assertThat(StateStoreWaitForFiles.JOB_ASSIGNMENT_WAIT_RANGE.getMaxWaitCeiling())
                    .isEqualTo(Duration.ofSeconds(60));
        }

        @Test
        void shouldInitializeJobAssignmentThrottlingRetries() {
            // When - accessing JOB_ASSIGNMENT_THROTTLING_RETRIES triggers class initialization (line 44)
            // Then - should be initialized and not null
            assertThat(StateStoreWaitForFiles.JOB_ASSIGNMENT_THROTTLING_RETRIES).isNotNull();
        }
    }
}
