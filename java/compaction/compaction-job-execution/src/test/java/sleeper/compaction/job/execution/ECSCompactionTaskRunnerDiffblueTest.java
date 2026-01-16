package sleeper.compaction.job.execution;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.instance.InstanceProperty;
import software.amazon.awssdk.services.ecs.EcsClient;

class ECSCompactionTaskRunnerDiffblueTest {
  /**
   * Test {@link ECSCompactionTaskRunner#logEC2Metadata(InstanceProperties, EcsClient)}.
   *
   * <ul>
   *   <li>Given {@code EC2}.
   *   <li>When {@link InstanceProperties} {@link InstanceProperties#get(InstanceProperty)} return
   *       {@code EC2}.
   *   <li>Then calls {@link InstanceProperties#get(InstanceProperty)}.
   * </ul>
   *
   * <p>Method under test: {@link ECSCompactionTaskRunner#logEC2Metadata(InstanceProperties,
   * EcsClient)}
   */
  @Test
  @DisplayName(
      "Test logEC2Metadata(InstanceProperties, EcsClient); given 'EC2'; when InstanceProperties get(InstanceProperty) return 'EC2'; then calls get(InstanceProperty)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ECSCompactionTaskRunner.logEC2Metadata(InstanceProperties, EcsClient)"})
  void testLogEC2Metadata_givenEc2_whenInstancePropertiesGetReturnEc2_thenCallsGet() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("EC2");

    // Act
    ECSCompactionTaskRunner.logEC2Metadata(instanceProperties, mock(EcsClient.class));

    // Assert
    verify(instanceProperties).get(isA(InstanceProperty.class));
  }
}
