package sleeper.query.core.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.query.core.model.QueryProcessingConfig.Builder;

class QueryProcessingConfigDiffblueTest {
  /**
   * Test Builder {@link Builder#build()}.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Builder#build()}
   *   <li>{@link Builder#queryTimeIteratorClassName(String)}
   *   <li>{@link Builder#queryTimeIteratorConfig(String)}
   *   <li>{@link Builder#requestedValueFields(List)}
   *   <li>{@link Builder#resultsPublisherConfig(Map)}
   *   <li>{@link Builder#statusReportDestinations(List)}
   * </ul>
   */
  @Test
  @DisplayName("Test Builder build()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"QueryProcessingConfig Builder.build()", "Builder Builder.queryTimeIteratorClassName(String)",
      "Builder Builder.queryTimeIteratorConfig(String)", "Builder Builder.requestedValueFields(List)",
      "Builder Builder.resultsPublisherConfig(Map)", "Builder Builder.statusReportDestinations(List)"})
  void testBuilderBuild() {
    // Arrange
    Builder queryTimeIteratorConfigResult = QueryProcessingConfig.builder()
        .queryTimeIteratorClassName("Query Time Iterator Class Name")
        .queryTimeIteratorConfig("Query Time Iterator Config");
    ArrayList<String> requestedValueFields = new ArrayList<>();
    Builder requestedValueFieldsResult = queryTimeIteratorConfigResult.requestedValueFields(requestedValueFields);
    HashMap<String, String> resultsPublisherConfig = new HashMap<>();
    Builder resultsPublisherConfigResult = requestedValueFieldsResult.resultsPublisherConfig(resultsPublisherConfig);
    ArrayList<Map<String, String>> statusReportDestinations = new ArrayList<>();

    // Act
    QueryProcessingConfig actualBuildResult = resultsPublisherConfigResult
        .statusReportDestinations(statusReportDestinations)
        .build();

    // Assert
    assertEquals("Query Time Iterator Class Name", actualBuildResult.getQueryTimeIteratorClassName());
    assertEquals("Query Time Iterator Config", actualBuildResult.getQueryTimeIteratorConfig());
    List<String> requestedValueFields2 = actualBuildResult.getRequestedValueFields();
    assertTrue(requestedValueFields2.isEmpty());
    List<Map<String, String>> statusReportDestinations2 = actualBuildResult.getStatusReportDestinations();
    assertTrue(statusReportDestinations2.isEmpty());
    Map<String, String> resultsPublisherConfig2 = actualBuildResult.getResultsPublisherConfig();
    assertTrue(resultsPublisherConfig2.isEmpty());
    assertSame(requestedValueFields, requestedValueFields2);
    assertSame(statusReportDestinations, statusReportDestinations2);
    assertSame(resultsPublisherConfig, resultsPublisherConfig2);
  }

  /**
   * Test {@link QueryProcessingConfig#none()}.
   * <p>
   * Method under test: {@link QueryProcessingConfig#none()}
   */
  @Test
  @DisplayName("Test none()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"QueryProcessingConfig QueryProcessingConfig.none()"})
  void testNone() {
    // Arrange and Act
    QueryProcessingConfig actualNoneResult = QueryProcessingConfig.none();

    // Assert
    assertNull(actualNoneResult.getQueryTimeIteratorClassName());
    assertNull(actualNoneResult.getQueryTimeIteratorConfig());
    assertNull(actualNoneResult.getRequestedValueFields());
    assertTrue(actualNoneResult.getStatusReportDestinations().isEmpty());
    assertTrue(actualNoneResult.getResultsPublisherConfig().isEmpty());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link QueryProcessingConfig#toString()}
   *   <li>{@link QueryProcessingConfig#getQueryTimeIteratorClassName()}
   *   <li>{@link QueryProcessingConfig#getQueryTimeIteratorConfig()}
   *   <li>{@link QueryProcessingConfig#getRequestedValueFields()}
   *   <li>{@link QueryProcessingConfig#getResultsPublisherConfig()}
   *   <li>{@link QueryProcessingConfig#getStatusReportDestinations()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String QueryProcessingConfig.getQueryTimeIteratorClassName()",
      "String QueryProcessingConfig.getQueryTimeIteratorConfig()",
      "List QueryProcessingConfig.getRequestedValueFields()", "Map QueryProcessingConfig.getResultsPublisherConfig()",
      "List QueryProcessingConfig.getStatusReportDestinations()", "String QueryProcessingConfig.toString()"})
  void testGettersAndSetters() {
    // Arrange
    Builder queryTimeIteratorConfigResult = QueryProcessingConfig.builder()
        .queryTimeIteratorClassName("Query Time Iterator Class Name")
        .queryTimeIteratorConfig("Query Time Iterator Config");
    ArrayList<String> requestedValueFields = new ArrayList<>();
    Builder requestedValueFieldsResult = queryTimeIteratorConfigResult.requestedValueFields(requestedValueFields);
    HashMap<String, String> resultsPublisherConfig = new HashMap<>();
    Builder resultsPublisherConfigResult = requestedValueFieldsResult.resultsPublisherConfig(resultsPublisherConfig);
    ArrayList<Map<String, String>> statusReportDestinations = new ArrayList<>();
    QueryProcessingConfig buildResult = resultsPublisherConfigResult.statusReportDestinations(statusReportDestinations)
        .build();

    // Act
    String actualToStringResult = buildResult.toString();
    String actualQueryTimeIteratorClassName = buildResult.getQueryTimeIteratorClassName();
    String actualQueryTimeIteratorConfig = buildResult.getQueryTimeIteratorConfig();
    List<String> actualRequestedValueFields = buildResult.getRequestedValueFields();
    Map<String, String> actualResultsPublisherConfig = buildResult.getResultsPublisherConfig();
    List<Map<String, String>> actualStatusReportDestinations = buildResult.getStatusReportDestinations();

    // Assert
    assertEquals("Query Time Iterator Class Name", actualQueryTimeIteratorClassName);
    assertEquals("Query Time Iterator Config", actualQueryTimeIteratorConfig);
    assertEquals(
        "QueryProcessingConfig{queryTimeIteratorClassName='Query Time Iterator Class Name', queryTimeIteratorConfig"
            + "='Query Time Iterator Config', resultsPublisherConfig={}, statusReportDestinations=[], requestedValueFields"
            + "=[]}",
        actualToStringResult);
    assertTrue(actualRequestedValueFields.isEmpty());
    assertTrue(actualStatusReportDestinations.isEmpty());
    assertTrue(actualResultsPublisherConfig.isEmpty());
    assertSame(requestedValueFields, actualRequestedValueFields);
    assertSame(statusReportDestinations, actualStatusReportDestinations);
    assertSame(resultsPublisherConfig, actualResultsPublisherConfig);
  }

  /**
   * Test {@link QueryProcessingConfig#withRequestedValueFields(List)}.
   * <ul>
   *   <li>Given {@code statusReportDestinations must not be null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link QueryProcessingConfig#withRequestedValueFields(List)}
   */
  @Test
  @DisplayName("Test withRequestedValueFields(List); given 'statusReportDestinations must not be null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"QueryProcessingConfig QueryProcessingConfig.withRequestedValueFields(List)"})
  void testWithRequestedValueFields_givenStatusReportDestinationsMustNotBeNull() {
    // Arrange
    QueryProcessingConfig noneResult = QueryProcessingConfig.none();

    ArrayList<String> requestedValueFields = new ArrayList<>();
    requestedValueFields.add("statusReportDestinations must not be null");
    requestedValueFields.add("resultsPublisherConfig must not be null");

    // Act
    QueryProcessingConfig actualWithRequestedValueFieldsResult = noneResult
        .withRequestedValueFields(requestedValueFields);

    // Assert
    assertNull(actualWithRequestedValueFieldsResult.getQueryTimeIteratorClassName());
    assertNull(actualWithRequestedValueFieldsResult.getQueryTimeIteratorConfig());
    assertTrue(actualWithRequestedValueFieldsResult.getStatusReportDestinations().isEmpty());
    assertTrue(actualWithRequestedValueFieldsResult.getResultsPublisherConfig().isEmpty());
    assertSame(requestedValueFields, actualWithRequestedValueFieldsResult.getRequestedValueFields());
  }

  /**
   * Test {@link QueryProcessingConfig#withRequestedValueFields(List)}.
   * <ul>
   *   <li>Then return RequestedValueFields is {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link QueryProcessingConfig#withRequestedValueFields(List)}
   */
  @Test
  @DisplayName("Test withRequestedValueFields(List); then return RequestedValueFields is ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"QueryProcessingConfig QueryProcessingConfig.withRequestedValueFields(List)"})
  void testWithRequestedValueFields_thenReturnRequestedValueFieldsIsArrayList() {
    // Arrange
    QueryProcessingConfig noneResult = QueryProcessingConfig.none();

    ArrayList<String> requestedValueFields = new ArrayList<>();
    requestedValueFields.add("resultsPublisherConfig must not be null");

    // Act
    QueryProcessingConfig actualWithRequestedValueFieldsResult = noneResult
        .withRequestedValueFields(requestedValueFields);

    // Assert
    assertNull(actualWithRequestedValueFieldsResult.getQueryTimeIteratorClassName());
    assertNull(actualWithRequestedValueFieldsResult.getQueryTimeIteratorConfig());
    assertTrue(actualWithRequestedValueFieldsResult.getStatusReportDestinations().isEmpty());
    assertTrue(actualWithRequestedValueFieldsResult.getResultsPublisherConfig().isEmpty());
    assertSame(requestedValueFields, actualWithRequestedValueFieldsResult.getRequestedValueFields());
  }

  /**
   * Test {@link QueryProcessingConfig#withRequestedValueFields(List)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return RequestedValueFields Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link QueryProcessingConfig#withRequestedValueFields(List)}
   */
  @Test
  @DisplayName("Test withRequestedValueFields(List); when ArrayList(); then return RequestedValueFields Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"QueryProcessingConfig QueryProcessingConfig.withRequestedValueFields(List)"})
  void testWithRequestedValueFields_whenArrayList_thenReturnRequestedValueFieldsEmpty() {
    // Arrange
    QueryProcessingConfig noneResult = QueryProcessingConfig.none();

    // Act
    QueryProcessingConfig actualWithRequestedValueFieldsResult = noneResult.withRequestedValueFields(new ArrayList<>());

    // Assert
    assertNull(actualWithRequestedValueFieldsResult.getQueryTimeIteratorClassName());
    assertNull(actualWithRequestedValueFieldsResult.getQueryTimeIteratorConfig());
    assertTrue(actualWithRequestedValueFieldsResult.getRequestedValueFields().isEmpty());
    assertTrue(actualWithRequestedValueFieldsResult.getStatusReportDestinations().isEmpty());
    assertTrue(actualWithRequestedValueFieldsResult.getResultsPublisherConfig().isEmpty());
  }

  /**
   * Test {@link QueryProcessingConfig#withResultsPublisherConfig(Map)}.
   * <p>
   * Method under test: {@link QueryProcessingConfig#withResultsPublisherConfig(Map)}
   */
  @Test
  @DisplayName("Test withResultsPublisherConfig(Map)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"QueryProcessingConfig QueryProcessingConfig.withResultsPublisherConfig(Map)"})
  void testWithResultsPublisherConfig() {
    // Arrange
    QueryProcessingConfig noneResult = QueryProcessingConfig.none();

    // Act and Assert
    assertEquals(noneResult, noneResult.withResultsPublisherConfig(new HashMap<>()));
  }

  /**
   * Test {@link QueryProcessingConfig#withStatusReportDestination(Map)}.
   * <p>
   * Method under test: {@link QueryProcessingConfig#withStatusReportDestination(Map)}
   */
  @Test
  @DisplayName("Test withStatusReportDestination(Map)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"QueryProcessingConfig QueryProcessingConfig.withStatusReportDestination(Map)"})
  void testWithStatusReportDestination() {
    // Arrange
    QueryProcessingConfig noneResult = QueryProcessingConfig.none();

    // Act
    QueryProcessingConfig actualWithStatusReportDestinationResult = noneResult
        .withStatusReportDestination(new HashMap<>());

    // Assert
    assertNull(actualWithStatusReportDestinationResult.getQueryTimeIteratorClassName());
    assertNull(actualWithStatusReportDestinationResult.getQueryTimeIteratorConfig());
    assertNull(actualWithStatusReportDestinationResult.getRequestedValueFields());
    List<Map<String, String>> statusReportDestinations = actualWithStatusReportDestinationResult
        .getStatusReportDestinations();
    assertEquals(1, statusReportDestinations.size());
    assertTrue(statusReportDestinations.get(0).isEmpty());
    assertTrue(actualWithStatusReportDestinationResult.getResultsPublisherConfig().isEmpty());
  }

  /**
   * Test {@link QueryProcessingConfig#equals(Object)}, and {@link QueryProcessingConfig#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link QueryProcessingConfig#equals(Object)}
   *   <li>{@link QueryProcessingConfig#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean QueryProcessingConfig.equals(Object)", "int QueryProcessingConfig.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    Builder queryTimeIteratorConfigResult = QueryProcessingConfig.builder()
        .queryTimeIteratorClassName("Query Time Iterator Class Name")
        .queryTimeIteratorConfig("Query Time Iterator Config");
    Builder requestedValueFieldsResult = queryTimeIteratorConfigResult.requestedValueFields(new ArrayList<>());
    Builder resultsPublisherConfigResult = requestedValueFieldsResult.resultsPublisherConfig(new HashMap<>());
    QueryProcessingConfig buildResult = resultsPublisherConfigResult.statusReportDestinations(new ArrayList<>())
        .build();
    Builder queryTimeIteratorConfigResult2 = QueryProcessingConfig.builder()
        .queryTimeIteratorClassName("Query Time Iterator Class Name")
        .queryTimeIteratorConfig("Query Time Iterator Config");
    Builder requestedValueFieldsResult2 = queryTimeIteratorConfigResult2.requestedValueFields(new ArrayList<>());
    Builder resultsPublisherConfigResult2 = requestedValueFieldsResult2.resultsPublisherConfig(new HashMap<>());
    QueryProcessingConfig buildResult2 = resultsPublisherConfigResult2.statusReportDestinations(new ArrayList<>())
        .build();

    // Act and Assert
    assertEquals(buildResult, buildResult2);
    int expectedHashCodeResult = buildResult.hashCode();
    assertEquals(expectedHashCodeResult, buildResult2.hashCode());
  }

  /**
   * Test {@link QueryProcessingConfig#equals(Object)}, and {@link QueryProcessingConfig#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link QueryProcessingConfig#equals(Object)}
   *   <li>{@link QueryProcessingConfig#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean QueryProcessingConfig.equals(Object)", "int QueryProcessingConfig.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    Builder queryTimeIteratorConfigResult = QueryProcessingConfig.builder()
        .queryTimeIteratorClassName("Query Time Iterator Class Name")
        .queryTimeIteratorConfig("Query Time Iterator Config");
    Builder requestedValueFieldsResult = queryTimeIteratorConfigResult.requestedValueFields(new ArrayList<>());
    Builder resultsPublisherConfigResult = requestedValueFieldsResult.resultsPublisherConfig(new HashMap<>());
    QueryProcessingConfig buildResult = resultsPublisherConfigResult.statusReportDestinations(new ArrayList<>())
        .build();

    // Act and Assert
    assertEquals(buildResult, buildResult);
    int expectedHashCodeResult = buildResult.hashCode();
    assertEquals(expectedHashCodeResult, buildResult.hashCode());
  }

  /**
   * Test {@link QueryProcessingConfig#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link QueryProcessingConfig#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean QueryProcessingConfig.equals(Object)", "int QueryProcessingConfig.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    Builder queryTimeIteratorConfigResult = QueryProcessingConfig.builder()
        .queryTimeIteratorClassName(null)
        .queryTimeIteratorConfig("Query Time Iterator Config");
    Builder requestedValueFieldsResult = queryTimeIteratorConfigResult.requestedValueFields(new ArrayList<>());
    Builder resultsPublisherConfigResult = requestedValueFieldsResult.resultsPublisherConfig(new HashMap<>());
    QueryProcessingConfig buildResult = resultsPublisherConfigResult.statusReportDestinations(new ArrayList<>())
        .build();
    Builder queryTimeIteratorConfigResult2 = QueryProcessingConfig.builder()
        .queryTimeIteratorClassName("Query Time Iterator Class Name")
        .queryTimeIteratorConfig("Query Time Iterator Config");
    Builder requestedValueFieldsResult2 = queryTimeIteratorConfigResult2.requestedValueFields(new ArrayList<>());
    Builder resultsPublisherConfigResult2 = requestedValueFieldsResult2.resultsPublisherConfig(new HashMap<>());
    QueryProcessingConfig buildResult2 = resultsPublisherConfigResult2.statusReportDestinations(new ArrayList<>())
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link QueryProcessingConfig#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link QueryProcessingConfig#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean QueryProcessingConfig.equals(Object)", "int QueryProcessingConfig.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    Builder queryTimeIteratorConfigResult = QueryProcessingConfig.builder()
        .queryTimeIteratorClassName("Query Time Iterator Class Name")
        .queryTimeIteratorConfig(null);
    Builder requestedValueFieldsResult = queryTimeIteratorConfigResult.requestedValueFields(new ArrayList<>());
    Builder resultsPublisherConfigResult = requestedValueFieldsResult.resultsPublisherConfig(new HashMap<>());
    QueryProcessingConfig buildResult = resultsPublisherConfigResult.statusReportDestinations(new ArrayList<>())
        .build();
    Builder queryTimeIteratorConfigResult2 = QueryProcessingConfig.builder()
        .queryTimeIteratorClassName("Query Time Iterator Class Name")
        .queryTimeIteratorConfig("Query Time Iterator Config");
    Builder requestedValueFieldsResult2 = queryTimeIteratorConfigResult2.requestedValueFields(new ArrayList<>());
    Builder resultsPublisherConfigResult2 = requestedValueFieldsResult2.resultsPublisherConfig(new HashMap<>());
    QueryProcessingConfig buildResult2 = resultsPublisherConfigResult2.statusReportDestinations(new ArrayList<>())
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link QueryProcessingConfig#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link QueryProcessingConfig#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean QueryProcessingConfig.equals(Object)", "int QueryProcessingConfig.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual3() {
    // Arrange
    ArrayList<String> requestedValueFields = new ArrayList<>();
    requestedValueFields.add("foo");
    Builder requestedValueFieldsResult = QueryProcessingConfig.builder()
        .queryTimeIteratorClassName("Query Time Iterator Class Name")
        .queryTimeIteratorConfig("Query Time Iterator Config")
        .requestedValueFields(requestedValueFields);
    Builder resultsPublisherConfigResult = requestedValueFieldsResult.resultsPublisherConfig(new HashMap<>());
    QueryProcessingConfig buildResult = resultsPublisherConfigResult.statusReportDestinations(new ArrayList<>())
        .build();
    Builder queryTimeIteratorConfigResult = QueryProcessingConfig.builder()
        .queryTimeIteratorClassName("Query Time Iterator Class Name")
        .queryTimeIteratorConfig("Query Time Iterator Config");
    Builder requestedValueFieldsResult2 = queryTimeIteratorConfigResult.requestedValueFields(new ArrayList<>());
    Builder resultsPublisherConfigResult2 = requestedValueFieldsResult2.resultsPublisherConfig(new HashMap<>());
    QueryProcessingConfig buildResult2 = resultsPublisherConfigResult2.statusReportDestinations(new ArrayList<>())
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link QueryProcessingConfig#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link QueryProcessingConfig#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean QueryProcessingConfig.equals(Object)", "int QueryProcessingConfig.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual4() {
    // Arrange
    HashMap<String, String> resultsPublisherConfig = new HashMap<>();
    resultsPublisherConfig.put("foo", "foo");
    Builder queryTimeIteratorConfigResult = QueryProcessingConfig.builder()
        .queryTimeIteratorClassName("Query Time Iterator Class Name")
        .queryTimeIteratorConfig("Query Time Iterator Config");
    Builder resultsPublisherConfigResult = queryTimeIteratorConfigResult.requestedValueFields(new ArrayList<>())
        .resultsPublisherConfig(resultsPublisherConfig);
    QueryProcessingConfig buildResult = resultsPublisherConfigResult.statusReportDestinations(new ArrayList<>())
        .build();
    Builder queryTimeIteratorConfigResult2 = QueryProcessingConfig.builder()
        .queryTimeIteratorClassName("Query Time Iterator Class Name")
        .queryTimeIteratorConfig("Query Time Iterator Config");
    Builder requestedValueFieldsResult = queryTimeIteratorConfigResult2.requestedValueFields(new ArrayList<>());
    Builder resultsPublisherConfigResult2 = requestedValueFieldsResult.resultsPublisherConfig(new HashMap<>());
    QueryProcessingConfig buildResult2 = resultsPublisherConfigResult2.statusReportDestinations(new ArrayList<>())
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link QueryProcessingConfig#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link QueryProcessingConfig#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean QueryProcessingConfig.equals(Object)", "int QueryProcessingConfig.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual5() {
    // Arrange
    ArrayList<Map<String, String>> statusReportDestinations = new ArrayList<>();
    statusReportDestinations.add(new HashMap<>());
    Builder queryTimeIteratorConfigResult = QueryProcessingConfig.builder()
        .queryTimeIteratorClassName("Query Time Iterator Class Name")
        .queryTimeIteratorConfig("Query Time Iterator Config");
    Builder requestedValueFieldsResult = queryTimeIteratorConfigResult.requestedValueFields(new ArrayList<>());
    QueryProcessingConfig buildResult = requestedValueFieldsResult.resultsPublisherConfig(new HashMap<>())
        .statusReportDestinations(statusReportDestinations)
        .build();
    Builder queryTimeIteratorConfigResult2 = QueryProcessingConfig.builder()
        .queryTimeIteratorClassName("Query Time Iterator Class Name")
        .queryTimeIteratorConfig("Query Time Iterator Config");
    Builder requestedValueFieldsResult2 = queryTimeIteratorConfigResult2.requestedValueFields(new ArrayList<>());
    Builder resultsPublisherConfigResult = requestedValueFieldsResult2.resultsPublisherConfig(new HashMap<>());
    QueryProcessingConfig buildResult2 = resultsPublisherConfigResult.statusReportDestinations(new ArrayList<>())
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link QueryProcessingConfig#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link QueryProcessingConfig#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean QueryProcessingConfig.equals(Object)", "int QueryProcessingConfig.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange
    Builder queryTimeIteratorConfigResult = QueryProcessingConfig.builder()
        .queryTimeIteratorClassName("Query Time Iterator Class Name")
        .queryTimeIteratorConfig("Query Time Iterator Config");
    Builder requestedValueFieldsResult = queryTimeIteratorConfigResult.requestedValueFields(new ArrayList<>());
    Builder resultsPublisherConfigResult = requestedValueFieldsResult.resultsPublisherConfig(new HashMap<>());
    QueryProcessingConfig buildResult = resultsPublisherConfigResult.statusReportDestinations(new ArrayList<>())
        .build();

    // Act and Assert
    assertNotEquals(buildResult, null);
  }

  /**
   * Test {@link QueryProcessingConfig#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link QueryProcessingConfig#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean QueryProcessingConfig.equals(Object)", "int QueryProcessingConfig.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange
    Builder queryTimeIteratorConfigResult = QueryProcessingConfig.builder()
        .queryTimeIteratorClassName("Query Time Iterator Class Name")
        .queryTimeIteratorConfig("Query Time Iterator Config");
    Builder requestedValueFieldsResult = queryTimeIteratorConfigResult.requestedValueFields(new ArrayList<>());
    Builder resultsPublisherConfigResult = requestedValueFieldsResult.resultsPublisherConfig(new HashMap<>());
    QueryProcessingConfig buildResult = resultsPublisherConfigResult.statusReportDestinations(new ArrayList<>())
        .build();

    // Act and Assert
    assertNotEquals(buildResult, "Different type to QueryProcessingConfig");
  }
}
