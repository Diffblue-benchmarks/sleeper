package sleeper.core.properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.properties.instance.ArrayListIngestProperty;

class SleeperPropertiesInvalidExceptionDiffblueTest {
  /**
   * Test {@link SleeperPropertiesInvalidException#SleeperPropertiesInvalidException(Map)}.
   * <p>
   * Method under test: {@link SleeperPropertiesInvalidException#SleeperPropertiesInvalidException(Map)}
   */
  @Test
  @DisplayName("Test new SleeperPropertiesInvalidException(Map)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SleeperPropertiesInvalidException.<init>(Map)"})
  void testNewSleeperPropertiesInvalidException() {
    // Arrange
    HashMap<SleeperProperty, String> invalidValues = new HashMap<>();
    invalidValues.put(ArrayListIngestProperty.MAX_IN_MEMORY_BATCH_SIZE, "foo");

    // Act
    SleeperPropertiesInvalidException actualSleeperPropertiesInvalidException = new SleeperPropertiesInvalidException(
        invalidValues);

    // Assert
    assertEquals("Property sleeper.ingest.memory.max.batch.size was invalid. It was \"foo\".",
        actualSleeperPropertiesInvalidException.getLocalizedMessage());
    assertEquals("Property sleeper.ingest.memory.max.batch.size was invalid. It was \"foo\".",
        actualSleeperPropertiesInvalidException.getMessage());
    assertNull(actualSleeperPropertiesInvalidException.getCause());
    assertEquals(0, actualSleeperPropertiesInvalidException.getSuppressed().length);
    assertSame(invalidValues, actualSleeperPropertiesInvalidException.getInvalidValues());
  }

  /**
   * Test {@link SleeperPropertiesInvalidException#SleeperPropertiesInvalidException(Map)}.
   * <p>
   * Method under test: {@link SleeperPropertiesInvalidException#SleeperPropertiesInvalidException(Map)}
   */
  @Test
  @DisplayName("Test new SleeperPropertiesInvalidException(Map)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SleeperPropertiesInvalidException.<init>(Map)"})
  void testNewSleeperPropertiesInvalidException2() {
    // Arrange
    HashMap<SleeperProperty, String> invalidValues = new HashMap<>();
    invalidValues.put(ArrayListIngestProperty.MAX_IN_MEMORY_BATCH_SIZE, null);

    // Act
    SleeperPropertiesInvalidException actualSleeperPropertiesInvalidException = new SleeperPropertiesInvalidException(
        invalidValues);

    // Assert
    assertEquals("Property sleeper.ingest.memory.max.batch.size was invalid. It was unset.",
        actualSleeperPropertiesInvalidException.getLocalizedMessage());
    assertEquals("Property sleeper.ingest.memory.max.batch.size was invalid. It was unset.",
        actualSleeperPropertiesInvalidException.getMessage());
    assertNull(actualSleeperPropertiesInvalidException.getCause());
    assertEquals(0, actualSleeperPropertiesInvalidException.getSuppressed().length);
    assertSame(invalidValues, actualSleeperPropertiesInvalidException.getInvalidValues());
  }
}
