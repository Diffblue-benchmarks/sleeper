package sleeper.core.properties.instance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.properties.SleeperPropertyValues;
import sleeper.core.properties.testutils.DummyInstanceProperty;

class InstancePropertyDiffblueTest {
  /**
   * Test {@link InstanceProperty#getAll()}.
   * <p>
   * Method under test: {@link InstanceProperty#getAll()}
   */
  @Test
  @DisplayName("Test getAll()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List InstanceProperty.getAll()"})
  void testGetAll() {
    // Arrange and Act
    List<InstanceProperty> actualAll = InstanceProperty.getAll();

    // Assert
    assertEquals(456, actualAll.size());
    assertTrue(actualAll.get(450) instanceof CdkDefinedInstancePropertyImpl);
    assertTrue(actualAll.get(451) instanceof CdkDefinedInstancePropertyImpl);
    assertTrue(actualAll.get(452) instanceof CdkDefinedInstancePropertyImpl);
    assertTrue(actualAll.get(453) instanceof CdkDefinedInstancePropertyImpl);
    assertTrue(actualAll.get(454) instanceof CdkDefinedInstancePropertyImpl);
    assertTrue(actualAll.get(455) instanceof CdkDefinedInstancePropertyImpl);
    assertTrue(actualAll.get(0) instanceof UserDefinedInstancePropertyImpl);
    assertTrue(actualAll.get(1) instanceof UserDefinedInstancePropertyImpl);
    assertTrue(actualAll.get(2) instanceof UserDefinedInstancePropertyImpl);
    assertTrue(actualAll.get(3) instanceof UserDefinedInstancePropertyImpl);
    assertTrue(actualAll.get(4) instanceof UserDefinedInstancePropertyImpl);
    assertTrue(actualAll.get(5) instanceof UserDefinedInstancePropertyImpl);
  }

  /**
   * Test {@link InstanceProperty#computeValue(String, SleeperPropertyValues)}.
   * <p>
   * Method under test: {@link InstanceProperty#computeValue(String, SleeperPropertyValues)}
   */
  @Test
  @DisplayName("Test computeValue(String, SleeperPropertyValues)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String InstanceProperty.computeValue(String, SleeperPropertyValues)"})
  void testComputeValue() {
    // Arrange, Act and Assert
    assertEquals("42",
        (new DummyInstanceProperty("Property Name")).computeValue("42", mock(SleeperPropertyValues.class)));
  }
}
