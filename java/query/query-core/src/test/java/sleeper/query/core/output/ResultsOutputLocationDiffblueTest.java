package sleeper.query.core.output;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class ResultsOutputLocationDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link ResultsOutputLocation#ResultsOutputLocation(String, String)}
   *   <li>{@link ResultsOutputLocation#toString()}
   *   <li>{@link ResultsOutputLocation#getLocation()}
   *   <li>{@link ResultsOutputLocation#getType()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ResultsOutputLocation.<init>(String, String)", "String ResultsOutputLocation.getLocation()",
      "String ResultsOutputLocation.getType()", "String ResultsOutputLocation.toString()"})
  void testGettersAndSetters() {
    // Arrange and Act
    ResultsOutputLocation actualResultsOutputLocation = new ResultsOutputLocation("Type", "Location");
    String actualToStringResult = actualResultsOutputLocation.toString();
    String actualLocation = actualResultsOutputLocation.getLocation();

    // Assert
    assertEquals("Location", actualLocation);
    assertEquals("ResultsOutputLocation{type=Type, location=Location}", actualToStringResult);
    assertEquals("Type", actualResultsOutputLocation.getType());
  }
}
