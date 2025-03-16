package sleeper.systemtest.dsl.query;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class QueryRangeDiffblueTest {
  /**
   * Test {@link QueryRange#range(Object, Object)}.
   * <p>
   * Method under test: {@link QueryRange#range(Object, Object)}
   */
  @Test
  @DisplayName("Test range(Object, Object)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"QueryRange QueryRange.range(Object, Object)"})
  void testRange() {
    // Arrange and Act
    QueryRange actualRangeResult = QueryRange.range("Min", "Max");

    // Assert
    assertEquals("Max", actualRangeResult.getMax());
    assertEquals("Min", actualRangeResult.getMin());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link QueryRange#getMax()}
   *   <li>{@link QueryRange#getMin()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Object QueryRange.getMax()", "Object QueryRange.getMin()"})
  void testGettersAndSetters() {
    // Arrange
    QueryRange rangeResult = QueryRange.range("Min", "Max");

    // Act
    Object actualMax = rangeResult.getMax();

    // Assert
    assertEquals("Max", actualMax);
    assertEquals("Min", rangeResult.getMin());
  }
}
