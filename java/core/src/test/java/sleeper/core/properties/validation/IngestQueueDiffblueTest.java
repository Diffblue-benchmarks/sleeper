package sleeper.core.properties.validation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.properties.instance.InstanceProperties;

class IngestQueueDiffblueTest {
  /**
   * Test {@link IngestQueue#isValid(String)}.
   * <ul>
   *   <li>When {@code 42}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestQueue#isValid(String)}
   */
  @Test
  @DisplayName("Test isValid(String); when '42'; then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestQueue.isValid(String)"})
  void testIsValid_when42_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(IngestQueue.isValid("42"));
  }

  /**
   * Test {@link IngestQueue#isValid(String)}.
   * <ul>
   *   <li>When {@code BULK_IMPORT_EKS}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestQueue#isValid(String)}
   */
  @Test
  @DisplayName("Test isValid(String); when 'BULK_IMPORT_EKS'; then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestQueue.isValid(String)"})
  void testIsValid_whenBulkImportEks_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(IngestQueue.isValid("BULK_IMPORT_EKS"));
  }

  /**
   * Test {@link IngestQueue#isValid(String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestQueue#isValid(String)}
   */
  @Test
  @DisplayName("Test isValid(String); when 'null'; then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestQueue.isValid(String)"})
  void testIsValid_whenNull_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(IngestQueue.isValid(null));
  }

  /**
   * Test {@link IngestQueue#getJobQueueUrl(InstanceProperties)}.
   * <ul>
   *   <li>When {@link InstanceProperties#InstanceProperties()}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestQueue#getJobQueueUrl(InstanceProperties)}
   */
  @Test
  @DisplayName("Test getJobQueueUrl(InstanceProperties); when InstanceProperties(); then return 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String IngestQueue.getJobQueueUrl(InstanceProperties)"})
  void testGetJobQueueUrl_whenInstanceProperties_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(IngestQueue.STANDARD_INGEST.getJobQueueUrl(new InstanceProperties()));
  }
}
