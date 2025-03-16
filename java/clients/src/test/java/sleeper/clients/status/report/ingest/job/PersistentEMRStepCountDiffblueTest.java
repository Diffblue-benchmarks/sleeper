package sleeper.clients.status.report.ingest.job;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.clients.util.EmrUtils;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.util.StaticRateLimit;
import software.amazon.awssdk.services.emr.EmrClient;

class PersistentEMRStepCountDiffblueTest {
  /**
   * Test {@link PersistentEMRStepCount#byStatus(InstanceProperties, EmrClient, StaticRateLimit)} with {@code instanceProperties}, {@code emrClient}, {@code listActiveClustersLimit}.
   * <p>
   * Method under test: {@link PersistentEMRStepCount#byStatus(InstanceProperties, EmrClient, StaticRateLimit)}
   */
  @Test
  @DisplayName("Test byStatus(InstanceProperties, EmrClient, StaticRateLimit) with 'instanceProperties', 'emrClient', 'listActiveClustersLimit'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Map PersistentEMRStepCount.byStatus(InstanceProperties, EmrClient, StaticRateLimit)"})
  void testByStatusWithInstancePropertiesEmrClientListActiveClustersLimit() {
    // Arrange and Act
    Map<String, Integer> actualByStatusResult = PersistentEMRStepCount.byStatus(new InstanceProperties(),
        mock(EmrClient.class), EmrUtils.LIST_ACTIVE_CLUSTERS_LIMIT);

    // Assert
    assertTrue(actualByStatusResult.isEmpty());
  }

  /**
   * Test {@link PersistentEMRStepCount#byStatus(InstanceProperties, EmrClient)} with {@code instanceProperties}, {@code emrClient}.
   * <ul>
   *   <li>Then return Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link PersistentEMRStepCount#byStatus(InstanceProperties, EmrClient)}
   */
  @Test
  @DisplayName("Test byStatus(InstanceProperties, EmrClient) with 'instanceProperties', 'emrClient'; then return Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Map PersistentEMRStepCount.byStatus(InstanceProperties, EmrClient)"})
  void testByStatusWithInstancePropertiesEmrClient_thenReturnEmpty() {
    // Arrange and Act
    Map<String, Integer> actualByStatusResult = PersistentEMRStepCount.byStatus(new InstanceProperties(),
        mock(EmrClient.class));

    // Assert
    assertTrue(actualByStatusResult.isEmpty());
  }
}
