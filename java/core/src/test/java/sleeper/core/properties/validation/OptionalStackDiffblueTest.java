package sleeper.core.properties.validation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class OptionalStackDiffblueTest {
  /**
   * Test {@link OptionalStack#isValid(String)}.
   * <ul>
   *   <li>When {@code 42}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link OptionalStack#isValid(String)}
   */
  @Test
  @DisplayName("Test isValid(String); when '42'; then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean OptionalStack.isValid(String)"})
  void testIsValid_when42_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(OptionalStack.isValid("42"));
  }

  /**
   * Test {@link OptionalStack#isValid(String)}.
   * <ul>
   *   <li>When {@code AthenaStack}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link OptionalStack#isValid(String)}
   */
  @Test
  @DisplayName("Test isValid(String); when 'AthenaStack'; then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean OptionalStack.isValid(String)"})
  void testIsValid_whenAthenaStack_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(OptionalStack.isValid("AthenaStack"));
  }

  /**
   * Test {@link OptionalStack#isValid(String)}.
   * <ul>
   *   <li>When empty string.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link OptionalStack#isValid(String)}
   */
  @Test
  @DisplayName("Test isValid(String); when empty string; then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean OptionalStack.isValid(String)"})
  void testIsValid_whenEmptyString_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(OptionalStack.isValid(""));
  }

  /**
   * Test {@link OptionalStack#isValid(String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link OptionalStack#isValid(String)}
   */
  @Test
  @DisplayName("Test isValid(String); when 'null'; then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean OptionalStack.isValid(String)"})
  void testIsValid_whenNull_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(OptionalStack.isValid(null));
  }

  /**
   * Test {@link OptionalStack#getDefaultValue()}.
   * <p>
   * Method under test: {@link OptionalStack#getDefaultValue()}
   */
  @Test
  @DisplayName("Test getDefaultValue()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String OptionalStack.getDefaultValue()"})
  void testGetDefaultValue() {
    // Arrange, Act and Assert
    assertEquals(
        "IngestStack,IngestBatcherStack,EmrServerlessBulkImportStack,EmrStudioStack,QueryStack,AthenaStack"
            + ",CompactionStack,GarbageCollectorStack,PartitionSplittingStack,DashboardStack,TableMetricsStack",
        OptionalStack.getDefaultValue());
  }

  /**
   * Test {@link OptionalStack#all()}.
   * <p>
   * Method under test: {@link OptionalStack#all()}
   */
  @Test
  @DisplayName("Test all()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List OptionalStack.all()"})
  void testAll() {
    // Arrange and Act
    List<OptionalStack> actualAllResult = OptionalStack.all();

    // Assert
    assertEquals(Short.SIZE, actualAllResult.size());
    assertEquals(OptionalStack.CompactionStack, actualAllResult.get(11));
    assertEquals(OptionalStack.DashboardStack, actualAllResult.get(14));
    assertEquals(OptionalStack.EksBulkImportStack, actualAllResult.get(5));
    assertEquals(OptionalStack.EmrBulkImportStack, actualAllResult.get(3));
    assertEquals(OptionalStack.EmrServerlessBulkImportStack, actualAllResult.get(2));
    assertEquals(OptionalStack.GarbageCollectorStack, actualAllResult.get(12));
    assertEquals(OptionalStack.IngestBatcherStack, actualAllResult.get(1));
    assertEquals(OptionalStack.IngestStack, actualAllResult.get(0));
    assertEquals(OptionalStack.KeepLambdaWarmStack, actualAllResult.get(10));
    assertEquals(OptionalStack.PartitionSplittingStack, actualAllResult.get(13));
    assertEquals(OptionalStack.PersistentEmrBulkImportStack, actualAllResult.get(4));
    assertEquals(OptionalStack.TableMetricsStack, actualAllResult.get(15));
  }
}
