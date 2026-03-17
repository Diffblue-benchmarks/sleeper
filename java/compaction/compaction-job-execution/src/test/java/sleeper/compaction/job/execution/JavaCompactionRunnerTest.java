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

import org.apache.hadoop.conf.Configuration;
import org.junit.jupiter.api.Test;

import sleeper.core.util.ObjectFactory;

import static org.assertj.core.api.Assertions.assertThat;

class JavaCompactionRunnerTest {

    @Test
    void shouldReturnImplementationLanguage() {
        // Given
        JavaCompactionRunner runner = new JavaCompactionRunner(ObjectFactory.noUserJars(), new Configuration());

        // When
        String language = runner.implementationLanguage();

        // Then
        assertThat(language).isEqualTo("Java");
    }

    @Test
    void shouldSupportIterators() {
        // Given
        JavaCompactionRunner runner = new JavaCompactionRunner(ObjectFactory.noUserJars(), new Configuration());

        // When
        boolean supportsIterators = runner.supportsIterators();

        // Then
        assertThat(supportsIterators).isTrue();
    }

    @Test
    void shouldNotBeHardwareAccelerated() {
        // Given
        JavaCompactionRunner runner = new JavaCompactionRunner(ObjectFactory.noUserJars(), new Configuration());

        // When
        boolean isHardwareAccelerated = runner.isHardwareAccelerated();

        // Then
        assertThat(isHardwareAccelerated).isFalse();
    }
}
