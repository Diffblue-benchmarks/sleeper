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
package sleeper.compaction.tracker.job;

import org.junit.jupiter.api.Test;

import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.tracker.compaction.job.CompactionJobTracker;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static sleeper.core.properties.instance.CompactionProperty.COMPACTION_TRACKER_ENABLED;
import static sleeper.core.properties.testutils.InstancePropertiesTestHelper.createTestInstanceProperties;

public class CompactionJobTrackerFactoryIT {

    private final InstanceProperties instanceProperties = createTestInstanceProperties();

    @Test
    public void shouldReturnDynamoDBTrackerWhenEnabled() {
        // Given
        instanceProperties.set(COMPACTION_TRACKER_ENABLED, "true");

        // When
        CompactionJobTracker tracker = CompactionJobTrackerFactory.getTracker(null, instanceProperties);

        // Then
        assertThat(tracker).isInstanceOf(DynamoDBCompactionJobTracker.class);
    }

    @Test
    public void shouldReturnNoneTrackerWhenDisabled() {
        // Given
        instanceProperties.set(COMPACTION_TRACKER_ENABLED, "false");

        // When
        CompactionJobTracker tracker = CompactionJobTrackerFactory.getTracker(null, instanceProperties);

        // Then
        assertThat(tracker).isSameAs(CompactionJobTracker.NONE);
    }

    @Test
    public void shouldReturnDynamoDBTrackerWithStronglyConsistentReadsWhenEnabled() {
        // Given
        instanceProperties.set(COMPACTION_TRACKER_ENABLED, "true");

        // When
        CompactionJobTracker tracker = CompactionJobTrackerFactory.getTrackerWithStronglyConsistentReads(null, instanceProperties);

        // Then
        assertThat(tracker).isInstanceOf(DynamoDBCompactionJobTracker.class);
    }

    @Test
    public void shouldReturnNoneTrackerWithStronglyConsistentReadsWhenDisabled() {
        // Given
        instanceProperties.set(COMPACTION_TRACKER_ENABLED, "false");

        // When
        CompactionJobTracker tracker = CompactionJobTrackerFactory.getTrackerWithStronglyConsistentReads(null, instanceProperties);

        // Then
        assertThat(tracker).isSameAs(CompactionJobTracker.NONE);
    }

    @Test
    public void shouldInvokePrivateConstructor() throws Exception {
        // Given
        Constructor<CompactionJobTrackerFactory> constructor = CompactionJobTrackerFactory.class.getDeclaredConstructor();
        constructor.setAccessible(true);

        // When
        CompactionJobTrackerFactory instance = constructor.newInstance();

        // Then
        assertThat(instance).isNotNull();
    }
}
