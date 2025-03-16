package sleeper.query.core.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class QueryValidationExceptionDiffblueTest {
  /**
   * Test {@link QueryValidationException#QueryValidationException(String, List, Exception)}.
   * <p>
   * Method under test: {@link QueryValidationException#QueryValidationException(String, List, Exception)}
   */
  @Test
  @DisplayName("Test new QueryValidationException(String, List, Exception)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void QueryValidationException.<init>(String, List, Exception)"})
  void testNewQueryValidationException() {
    // Arrange
    ArrayList<Map<String, String>> statusReportDestinations = new ArrayList<>();

    // Act
    QueryValidationException actualQueryValidationException = new QueryValidationException(null,
        statusReportDestinations, new Exception("foo"));

    // Assert
    assertEquals("Query validation failed: foo", actualQueryValidationException.getLocalizedMessage());
    assertEquals("Query validation failed: foo", actualQueryValidationException.getMessage());
    assertFalse(actualQueryValidationException.getQueryId().isPresent());
  }

  /**
   * Test {@link QueryValidationException#QueryValidationException(String, List, String)}.
   * <p>
   * Method under test: {@link QueryValidationException#QueryValidationException(String, List, String)}
   */
  @Test
  @DisplayName("Test new QueryValidationException(String, List, String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void QueryValidationException.<init>(String, List, String)"})
  void testNewQueryValidationException2() {
    // Arrange and Act
    QueryValidationException actualQueryValidationException = new QueryValidationException(null, new ArrayList<>(),
        "An error occurred");

    // Assert
    assertEquals("Query validation failed: An error occurred", actualQueryValidationException.getLocalizedMessage());
    assertEquals("Query validation failed: An error occurred", actualQueryValidationException.getMessage());
    assertFalse(actualQueryValidationException.getQueryId().isPresent());
    assertTrue(actualQueryValidationException.getStatusReportDestinations().isEmpty());
  }

  /**
   * Test {@link QueryValidationException#QueryValidationException(String, List, Exception)}.
   * <ul>
   *   <li>Then return StatusReportDestinations Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link QueryValidationException#QueryValidationException(String, List, Exception)}
   */
  @Test
  @DisplayName("Test new QueryValidationException(String, List, Exception); then return StatusReportDestinations Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void QueryValidationException.<init>(String, List, Exception)"})
  void testNewQueryValidationException_thenReturnStatusReportDestinationsEmpty() {
    // Arrange
    ArrayList<Map<String, String>> statusReportDestinations = new ArrayList<>();

    // Act
    QueryValidationException actualQueryValidationException = new QueryValidationException("42",
        statusReportDestinations, new Exception("foo"));

    // Assert
    Optional<String> queryId = actualQueryValidationException.getQueryId();
    assertEquals("42", queryId.get());
    assertEquals("Query validation failed for query \"42\": foo", actualQueryValidationException.getLocalizedMessage());
    assertEquals("Query validation failed for query \"42\": foo", actualQueryValidationException.getMessage());
    assertTrue(actualQueryValidationException.getStatusReportDestinations().isEmpty());
    assertTrue(queryId.isPresent());
  }

  /**
   * Test {@link QueryValidationException#QueryValidationException(String, List, Exception)}.
   * <ul>
   *   <li>Then return StatusReportDestinations is {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link QueryValidationException#QueryValidationException(String, List, Exception)}
   */
  @Test
  @DisplayName("Test new QueryValidationException(String, List, Exception); then return StatusReportDestinations is ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void QueryValidationException.<init>(String, List, Exception)"})
  void testNewQueryValidationException_thenReturnStatusReportDestinationsIsArrayList() {
    // Arrange
    ArrayList<Map<String, String>> statusReportDestinations = new ArrayList<>();
    statusReportDestinations.add(new HashMap<>());
    statusReportDestinations.add(new HashMap<>());

    // Act and Assert
    assertSame(statusReportDestinations,
        (new QueryValidationException("42", statusReportDestinations, new Exception("foo")))
            .getStatusReportDestinations());
  }

  /**
   * Test {@link QueryValidationException#QueryValidationException(String, List, String)}.
   * <ul>
   *   <li>Then return StatusReportDestinations is {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link QueryValidationException#QueryValidationException(String, List, String)}
   */
  @Test
  @DisplayName("Test new QueryValidationException(String, List, String); then return StatusReportDestinations is ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void QueryValidationException.<init>(String, List, String)"})
  void testNewQueryValidationException_thenReturnStatusReportDestinationsIsArrayList2() {
    // Arrange
    ArrayList<Map<String, String>> statusReportDestinations = new ArrayList<>();
    statusReportDestinations.add(new HashMap<>());
    statusReportDestinations.add(new HashMap<>());

    // Act and Assert
    assertSame(statusReportDestinations,
        (new QueryValidationException("42", statusReportDestinations, "An error occurred"))
            .getStatusReportDestinations());
  }

  /**
   * Test {@link QueryValidationException#QueryValidationException(String, List, Exception)}.
   * <ul>
   *   <li>Then return StatusReportDestinations size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link QueryValidationException#QueryValidationException(String, List, Exception)}
   */
  @Test
  @DisplayName("Test new QueryValidationException(String, List, Exception); then return StatusReportDestinations size is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void QueryValidationException.<init>(String, List, Exception)"})
  void testNewQueryValidationException_thenReturnStatusReportDestinationsSizeIsOne() {
    // Arrange
    ArrayList<Map<String, String>> statusReportDestinations = new ArrayList<>();
    statusReportDestinations.add(new HashMap<>());

    // Act
    QueryValidationException actualQueryValidationException = new QueryValidationException("42",
        statusReportDestinations, new Exception("foo"));

    // Assert
    Optional<String> queryId = actualQueryValidationException.getQueryId();
    assertEquals("42", queryId.get());
    assertEquals("Query validation failed for query \"42\": foo", actualQueryValidationException.getLocalizedMessage());
    assertEquals("Query validation failed for query \"42\": foo", actualQueryValidationException.getMessage());
    List<Map<String, String>> statusReportDestinations2 = actualQueryValidationException.getStatusReportDestinations();
    assertEquals(1, statusReportDestinations2.size());
    assertTrue(statusReportDestinations2.get(0).isEmpty());
    assertTrue(queryId.isPresent());
    assertSame(statusReportDestinations, statusReportDestinations2);
  }

  /**
   * Test {@link QueryValidationException#QueryValidationException(String, List, String)}.
   * <ul>
   *   <li>Then return StatusReportDestinations size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link QueryValidationException#QueryValidationException(String, List, String)}
   */
  @Test
  @DisplayName("Test new QueryValidationException(String, List, String); then return StatusReportDestinations size is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void QueryValidationException.<init>(String, List, String)"})
  void testNewQueryValidationException_thenReturnStatusReportDestinationsSizeIsOne2() {
    // Arrange
    ArrayList<Map<String, String>> statusReportDestinations = new ArrayList<>();
    statusReportDestinations.add(new HashMap<>());

    // Act
    QueryValidationException actualQueryValidationException = new QueryValidationException("42",
        statusReportDestinations, "An error occurred");

    // Assert
    Optional<String> queryId = actualQueryValidationException.getQueryId();
    assertEquals("42", queryId.get());
    assertEquals("Query validation failed for query \"42\": An error occurred",
        actualQueryValidationException.getLocalizedMessage());
    assertEquals("Query validation failed for query \"42\": An error occurred",
        actualQueryValidationException.getMessage());
    List<Map<String, String>> statusReportDestinations2 = actualQueryValidationException.getStatusReportDestinations();
    assertEquals(1, statusReportDestinations2.size());
    assertTrue(statusReportDestinations2.get(0).isEmpty());
    assertTrue(queryId.isPresent());
    assertSame(statusReportDestinations, statusReportDestinations2);
  }

  /**
   * Test {@link QueryValidationException#QueryValidationException(String, List, String)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return QueryId is {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link QueryValidationException#QueryValidationException(String, List, String)}
   */
  @Test
  @DisplayName("Test new QueryValidationException(String, List, String); when ArrayList(); then return QueryId is '42'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void QueryValidationException.<init>(String, List, String)"})
  void testNewQueryValidationException_whenArrayList_thenReturnQueryIdIs42() {
    // Arrange and Act
    QueryValidationException actualQueryValidationException = new QueryValidationException("42", new ArrayList<>(),
        "An error occurred");

    // Assert
    Optional<String> queryId = actualQueryValidationException.getQueryId();
    assertEquals("42", queryId.get());
    assertEquals("Query validation failed for query \"42\": An error occurred",
        actualQueryValidationException.getLocalizedMessage());
    assertEquals("Query validation failed for query \"42\": An error occurred",
        actualQueryValidationException.getMessage());
    assertTrue(actualQueryValidationException.getStatusReportDestinations().isEmpty());
    assertTrue(queryId.isPresent());
  }

  /**
   * Test {@link QueryValidationException#getQueryId()}.
   * <p>
   * Method under test: {@link QueryValidationException#getQueryId()}
   */
  @Test
  @DisplayName("Test getQueryId()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Optional QueryValidationException.getQueryId()"})
  void testGetQueryId() {
    // Arrange
    ArrayList<Map<String, String>> statusReportDestinations = new ArrayList<>();

    // Act
    Optional<String> actualQueryId = (new QueryValidationException("42", statusReportDestinations,
        new Exception("foo"))).getQueryId();

    // Assert
    assertEquals("42", actualQueryId.get());
    assertTrue(actualQueryId.isPresent());
  }

  /**
   * Test {@link QueryValidationException#getStatusReportDestinations()}.
   * <p>
   * Method under test: {@link QueryValidationException#getStatusReportDestinations()}
   */
  @Test
  @DisplayName("Test getStatusReportDestinations()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List QueryValidationException.getStatusReportDestinations()"})
  void testGetStatusReportDestinations() {
    // Arrange
    ArrayList<Map<String, String>> statusReportDestinations = new ArrayList<>();

    // Act
    List<Map<String, String>> actualStatusReportDestinations = (new QueryValidationException("42",
        statusReportDestinations, new Exception("foo"))).getStatusReportDestinations();

    // Assert
    assertTrue(actualStatusReportDestinations.isEmpty());
    assertSame(statusReportDestinations, actualStatusReportDestinations);
  }
}
