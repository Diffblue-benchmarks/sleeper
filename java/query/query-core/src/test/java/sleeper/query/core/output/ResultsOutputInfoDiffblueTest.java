package sleeper.query.core.output;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class ResultsOutputInfoDiffblueTest {
  /**
   * Test {@link ResultsOutputInfo#ResultsOutputInfo(long, List)}.
   * <ul>
   *   <li>Then return Locations is {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResultsOutputInfo#ResultsOutputInfo(long, List)}
   */
  @Test
  @DisplayName("Test new ResultsOutputInfo(long, List); then return Locations is ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ResultsOutputInfo.<init>(long, List)"})
  void testNewResultsOutputInfo_thenReturnLocationsIsArrayList() {
    // Arrange
    ArrayList<ResultsOutputLocation> locations = new ArrayList<>();
    locations.add(new ResultsOutputLocation("Type", "Location"));

    // Act and Assert
    assertSame(locations, (new ResultsOutputInfo(3L, locations)).getLocations());
  }

  /**
   * Test {@link ResultsOutputInfo#ResultsOutputInfo(long, List, Exception)}.
   * <ul>
   *   <li>Then return Locations is {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResultsOutputInfo#ResultsOutputInfo(long, List, Exception)}
   */
  @Test
  @DisplayName("Test new ResultsOutputInfo(long, List, Exception); then return Locations is ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ResultsOutputInfo.<init>(long, List, Exception)"})
  void testNewResultsOutputInfo_thenReturnLocationsIsArrayList2() {
    // Arrange
    ArrayList<ResultsOutputLocation> locations = new ArrayList<>();
    locations.add(new ResultsOutputLocation("Type", "Location"));

    // Act and Assert
    assertSame(locations, (new ResultsOutputInfo(3L, locations, new Exception("foo"))).getLocations());
  }

  /**
   * Test {@link ResultsOutputInfo#ResultsOutputInfo(long, List)}.
   * <ul>
   *   <li>Then return Locations size is two.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResultsOutputInfo#ResultsOutputInfo(long, List)}
   */
  @Test
  @DisplayName("Test new ResultsOutputInfo(long, List); then return Locations size is two")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ResultsOutputInfo.<init>(long, List)"})
  void testNewResultsOutputInfo_thenReturnLocationsSizeIsTwo() {
    // Arrange
    ArrayList<ResultsOutputLocation> locations = new ArrayList<>();
    locations.add(new ResultsOutputLocation("Type", "Location"));
    ResultsOutputLocation resultsOutputLocation = new ResultsOutputLocation("Type", "Location");

    locations.add(resultsOutputLocation);

    // Act and Assert
    List<ResultsOutputLocation> locations2 = (new ResultsOutputInfo(3L, locations)).getLocations();
    assertEquals(2, locations2.size());
    assertSame(resultsOutputLocation, locations2.get(1));
  }

  /**
   * Test {@link ResultsOutputInfo#ResultsOutputInfo(long, List, Exception)}.
   * <ul>
   *   <li>Then return Locations size is two.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResultsOutputInfo#ResultsOutputInfo(long, List, Exception)}
   */
  @Test
  @DisplayName("Test new ResultsOutputInfo(long, List, Exception); then return Locations size is two")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ResultsOutputInfo.<init>(long, List, Exception)"})
  void testNewResultsOutputInfo_thenReturnLocationsSizeIsTwo2() {
    // Arrange
    ArrayList<ResultsOutputLocation> locations = new ArrayList<>();
    locations.add(new ResultsOutputLocation("Type", "Location"));
    ResultsOutputLocation resultsOutputLocation = new ResultsOutputLocation("Type", "Location");

    locations.add(resultsOutputLocation);

    // Act and Assert
    List<ResultsOutputLocation> locations2 = (new ResultsOutputInfo(3L, locations, new Exception("foo")))
        .getLocations();
    assertEquals(2, locations2.size());
    assertSame(resultsOutputLocation, locations2.get(1));
  }

  /**
   * Test {@link ResultsOutputInfo#ResultsOutputInfo(long, List)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return Error is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResultsOutputInfo#ResultsOutputInfo(long, List)}
   */
  @Test
  @DisplayName("Test new ResultsOutputInfo(long, List); when ArrayList(); then return Error is 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ResultsOutputInfo.<init>(long, List)"})
  void testNewResultsOutputInfo_whenArrayList_thenReturnErrorIsNull() {
    // Arrange and Act
    ResultsOutputInfo actualResultsOutputInfo = new ResultsOutputInfo(3L, new ArrayList<>());

    // Assert
    assertNull(actualResultsOutputInfo.getError());
    assertEquals(3L, actualResultsOutputInfo.getRecordCount());
    assertTrue(actualResultsOutputInfo.getLocations().isEmpty());
  }

  /**
   * Test {@link ResultsOutputInfo#ResultsOutputInfo(long, List, Exception)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return RecordCount is three.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResultsOutputInfo#ResultsOutputInfo(long, List, Exception)}
   */
  @Test
  @DisplayName("Test new ResultsOutputInfo(long, List, Exception); when ArrayList(); then return RecordCount is three")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ResultsOutputInfo.<init>(long, List, Exception)"})
  void testNewResultsOutputInfo_whenArrayList_thenReturnRecordCountIsThree() {
    // Arrange
    ArrayList<ResultsOutputLocation> locations = new ArrayList<>();
    Exception error = new Exception("foo");

    // Act
    ResultsOutputInfo actualResultsOutputInfo = new ResultsOutputInfo(3L, locations, error);

    // Assert
    assertEquals(3L, actualResultsOutputInfo.getRecordCount());
    assertTrue(actualResultsOutputInfo.getLocations().isEmpty());
    assertSame(error, actualResultsOutputInfo.getError());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link ResultsOutputInfo#toString()}
   *   <li>{@link ResultsOutputInfo#getError()}
   *   <li>{@link ResultsOutputInfo#getLocations()}
   *   <li>{@link ResultsOutputInfo#getRecordCount()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Exception ResultsOutputInfo.getError()", "List ResultsOutputInfo.getLocations()",
      "long ResultsOutputInfo.getRecordCount()", "String ResultsOutputInfo.toString()"})
  void testGettersAndSetters() {
    // Arrange
    ArrayList<ResultsOutputLocation> locations = new ArrayList<>();
    ResultsOutputInfo resultsOutputInfo = new ResultsOutputInfo(3L, locations);

    // Act
    String actualToStringResult = resultsOutputInfo.toString();
    Exception actualError = resultsOutputInfo.getError();
    List<ResultsOutputLocation> actualLocations = resultsOutputInfo.getLocations();

    // Assert
    assertEquals("ResultsOutputInfo{recordCount=3, locations=[], error=null}", actualToStringResult);
    assertNull(actualError);
    assertEquals(3L, resultsOutputInfo.getRecordCount());
    assertTrue(actualLocations.isEmpty());
    assertSame(locations, actualLocations);
  }
}
