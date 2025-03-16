package sleeper.core.properties.instance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class CdkDefinedInstancePropertyDiffblueTest {
  /**
   * Test {@link CdkDefinedInstanceProperty#getAll()}.
   * <p>
   * Method under test: {@link CdkDefinedInstanceProperty#getAll()}
   */
  @Test
  @DisplayName("Test getAll()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List CdkDefinedInstanceProperty.getAll()"})
  void testGetAll() {
    // Arrange and Act
    List<CdkDefinedInstanceProperty> actualAll = CdkDefinedInstanceProperty.getAll();

    // Assert
    assertEquals(131, actualAll.size());
    assertTrue(actualAll.get(0) instanceof CdkDefinedInstancePropertyImpl);
    assertTrue(actualAll.get(1) instanceof CdkDefinedInstancePropertyImpl);
    assertTrue(actualAll.get(125) instanceof CdkDefinedInstancePropertyImpl);
    assertTrue(actualAll.get(126) instanceof CdkDefinedInstancePropertyImpl);
    assertTrue(actualAll.get(128) instanceof CdkDefinedInstancePropertyImpl);
    assertTrue(actualAll.get(129) instanceof CdkDefinedInstancePropertyImpl);
    assertTrue(actualAll.get(130) instanceof CdkDefinedInstancePropertyImpl);
    assertTrue(actualAll.get(2) instanceof CdkDefinedInstancePropertyImpl);
    assertTrue(actualAll.get(3) instanceof CdkDefinedInstancePropertyImpl);
    assertTrue(actualAll.get(4) instanceof CdkDefinedInstancePropertyImpl);
    assertTrue(actualAll.get(5) instanceof CdkDefinedInstancePropertyImpl);
    assertTrue(actualAll.get(Float.MAX_EXPONENT) instanceof CdkDefinedInstancePropertyImpl);
  }
}
