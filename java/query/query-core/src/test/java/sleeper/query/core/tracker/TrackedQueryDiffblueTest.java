package sleeper.query.core.tracker;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.query.core.tracker.TrackedQuery.Builder;

class TrackedQueryDiffblueTest {
  /**
   * Test Builder {@link Builder#build()}.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Builder#build()}
   *   <li>{@link Builder#errorMessage(String)}
   *   <li>{@link Builder#expiryDate(Long)}
   *   <li>{@link Builder#lastKnownState(QueryState)}
   *   <li>{@link Builder#lastUpdateTime(Long)}
   *   <li>{@link Builder#queryId(String)}
   *   <li>{@link Builder#recordCount(Long)}
   *   <li>{@link Builder#subQueryId(String)}
   * </ul>
   */
  @Test
  @DisplayName("Test Builder build()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"TrackedQuery Builder.build()", "Builder Builder.errorMessage(String)",
      "Builder Builder.expiryDate(Long)", "Builder Builder.lastKnownState(QueryState)",
      "Builder Builder.lastUpdateTime(Long)", "Builder Builder.queryId(String)", "Builder Builder.recordCount(Long)",
      "Builder Builder.subQueryId(String)"})
  void testBuilderBuild() {
    // Arrange and Act
    TrackedQuery actualBuildResult = TrackedQuery.builder()
        .errorMessage("An error occurred")
        .expiryDate(1L)
        .lastKnownState(QueryState.COMPLETED)
        .lastUpdateTime(1L)
        .queryId("42")
        .recordCount(3L)
        .subQueryId("42")
        .build();

    // Assert
    assertEquals("42", actualBuildResult.getQueryId());
    assertEquals("42", actualBuildResult.getSubQueryId());
    assertEquals("An error occurred", actualBuildResult.getErrorMessage());
    assertEquals(1L, actualBuildResult.getExpiryDate().longValue());
    assertEquals(1L, actualBuildResult.getLastUpdateTime().longValue());
    assertEquals(3L, actualBuildResult.getRecordCount().longValue());
    assertEquals(QueryState.COMPLETED, actualBuildResult.getLastKnownState());
  }

  /**
   * Test Builder {@link Builder#expiryDate(Instant)} with {@code Instant}.
   * <p>
   * Method under test: {@link Builder#expiryDate(Instant)}
   */
  @Test
  @DisplayName("Test Builder expiryDate(Instant) with 'Instant'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.expiryDate(Instant)"})
  void testBuilderExpiryDateWithInstant() {
    // Arrange
    Builder builderResult = TrackedQuery.builder();

    // Act
    Builder actualExpiryDateResult = builderResult
        .expiryDate(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Assert
    assertEquals(0L, builderResult.build().getExpiryDate().longValue());
    assertSame(builderResult, actualExpiryDateResult);
  }

  /**
   * Test Builder {@link Builder#lastUpdateTime(Instant)} with {@code Instant}.
   * <p>
   * Method under test: {@link Builder#lastUpdateTime(Instant)}
   */
  @Test
  @DisplayName("Test Builder lastUpdateTime(Instant) with 'Instant'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.lastUpdateTime(Instant)"})
  void testBuilderLastUpdateTimeWithInstant() {
    // Arrange
    Builder builderResult = TrackedQuery.builder();

    // Act
    Builder actualLastUpdateTimeResult = builderResult
        .lastUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Assert
    assertEquals(0L, builderResult.build().getLastUpdateTime().longValue());
    assertSame(builderResult, actualLastUpdateTimeResult);
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link TrackedQuery#toString()}
   *   <li>{@link TrackedQuery#getErrorMessage()}
   *   <li>{@link TrackedQuery#getExpiryDate()}
   *   <li>{@link TrackedQuery#getLastKnownState()}
   *   <li>{@link TrackedQuery#getLastUpdateTime()}
   *   <li>{@link TrackedQuery#getQueryId()}
   *   <li>{@link TrackedQuery#getRecordCount()}
   *   <li>{@link TrackedQuery#getSubQueryId()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String TrackedQuery.getErrorMessage()", "Long TrackedQuery.getExpiryDate()",
      "QueryState TrackedQuery.getLastKnownState()", "Long TrackedQuery.getLastUpdateTime()",
      "String TrackedQuery.getQueryId()", "Long TrackedQuery.getRecordCount()", "String TrackedQuery.getSubQueryId()",
      "String TrackedQuery.toString()"})
  void testGettersAndSetters() {
    // Arrange
    TrackedQuery buildResult = TrackedQuery.builder()
        .errorMessage("An error occurred")
        .expiryDate(1L)
        .lastKnownState(QueryState.COMPLETED)
        .lastUpdateTime(1L)
        .queryId("42")
        .recordCount(3L)
        .subQueryId("42")
        .build();

    // Act
    String actualToStringResult = buildResult.toString();
    String actualErrorMessage = buildResult.getErrorMessage();
    Long actualExpiryDate = buildResult.getExpiryDate();
    QueryState actualLastKnownState = buildResult.getLastKnownState();
    Long actualLastUpdateTime = buildResult.getLastUpdateTime();
    String actualQueryId = buildResult.getQueryId();
    Long actualRecordCount = buildResult.getRecordCount();

    // Assert
    assertEquals("42", actualQueryId);
    assertEquals("42", buildResult.getSubQueryId());
    assertEquals("An error occurred", actualErrorMessage);
    assertEquals("TrackedQuery{queryId='42', subQueryId='42', lastUpdateTime=1, expiryDate=1, lastKnownState=COMPLETED,"
        + " recordCount=3, errorMessage='An error occurred'}", actualToStringResult);
    assertEquals(1L, actualExpiryDate.longValue());
    assertEquals(1L, actualLastUpdateTime.longValue());
    assertEquals(3L, actualRecordCount.longValue());
    assertEquals(QueryState.COMPLETED, actualLastKnownState);
  }

  /**
   * Test {@link TrackedQuery#equals(Object)}, and {@link TrackedQuery#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link TrackedQuery#equals(Object)}
   *   <li>{@link TrackedQuery#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean TrackedQuery.equals(Object)", "int TrackedQuery.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    TrackedQuery buildResult = TrackedQuery.builder()
        .errorMessage("An error occurred")
        .expiryDate(1L)
        .lastKnownState(QueryState.COMPLETED)
        .lastUpdateTime(1L)
        .queryId("42")
        .recordCount(3L)
        .subQueryId("42")
        .build();
    TrackedQuery buildResult2 = TrackedQuery.builder()
        .errorMessage("An error occurred")
        .expiryDate(1L)
        .lastKnownState(QueryState.COMPLETED)
        .lastUpdateTime(1L)
        .queryId("42")
        .recordCount(3L)
        .subQueryId("42")
        .build();

    // Act and Assert
    assertEquals(buildResult, buildResult2);
    int expectedHashCodeResult = buildResult.hashCode();
    assertEquals(expectedHashCodeResult, buildResult2.hashCode());
  }

  /**
   * Test {@link TrackedQuery#equals(Object)}, and {@link TrackedQuery#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link TrackedQuery#equals(Object)}
   *   <li>{@link TrackedQuery#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean TrackedQuery.equals(Object)", "int TrackedQuery.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    TrackedQuery buildResult = TrackedQuery.builder()
        .errorMessage("An error occurred")
        .expiryDate(1L)
        .lastKnownState(QueryState.COMPLETED)
        .lastUpdateTime(1L)
        .queryId("42")
        .recordCount(3L)
        .subQueryId("42")
        .build();

    // Act and Assert
    assertEquals(buildResult, buildResult);
    int expectedHashCodeResult = buildResult.hashCode();
    assertEquals(expectedHashCodeResult, buildResult.hashCode());
  }

  /**
   * Test {@link TrackedQuery#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link TrackedQuery#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean TrackedQuery.equals(Object)", "int TrackedQuery.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    TrackedQuery buildResult = TrackedQuery.builder()
        .errorMessage("Error Message")
        .expiryDate(1L)
        .lastKnownState(QueryState.COMPLETED)
        .lastUpdateTime(1L)
        .queryId("42")
        .recordCount(3L)
        .subQueryId("42")
        .build();
    TrackedQuery buildResult2 = TrackedQuery.builder()
        .errorMessage("An error occurred")
        .expiryDate(1L)
        .lastKnownState(QueryState.COMPLETED)
        .lastUpdateTime(1L)
        .queryId("42")
        .recordCount(3L)
        .subQueryId("42")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link TrackedQuery#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link TrackedQuery#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean TrackedQuery.equals(Object)", "int TrackedQuery.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    TrackedQuery buildResult = TrackedQuery.builder()
        .errorMessage("An error occurred")
        .expiryDate(0L)
        .lastKnownState(QueryState.COMPLETED)
        .lastUpdateTime(1L)
        .queryId("42")
        .recordCount(3L)
        .subQueryId("42")
        .build();
    TrackedQuery buildResult2 = TrackedQuery.builder()
        .errorMessage("An error occurred")
        .expiryDate(1L)
        .lastKnownState(QueryState.COMPLETED)
        .lastUpdateTime(1L)
        .queryId("42")
        .recordCount(3L)
        .subQueryId("42")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link TrackedQuery#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link TrackedQuery#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean TrackedQuery.equals(Object)", "int TrackedQuery.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual3() {
    // Arrange
    TrackedQuery buildResult = TrackedQuery.builder()
        .errorMessage("An error occurred")
        .expiryDate(1L)
        .lastKnownState(null)
        .lastUpdateTime(1L)
        .queryId("42")
        .recordCount(3L)
        .subQueryId("42")
        .build();
    TrackedQuery buildResult2 = TrackedQuery.builder()
        .errorMessage("An error occurred")
        .expiryDate(1L)
        .lastKnownState(QueryState.COMPLETED)
        .lastUpdateTime(1L)
        .queryId("42")
        .recordCount(3L)
        .subQueryId("42")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link TrackedQuery#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link TrackedQuery#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean TrackedQuery.equals(Object)", "int TrackedQuery.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual4() {
    // Arrange
    TrackedQuery buildResult = TrackedQuery.builder()
        .errorMessage("An error occurred")
        .expiryDate(1L)
        .lastKnownState(QueryState.COMPLETED)
        .lastUpdateTime(0L)
        .queryId("42")
        .recordCount(3L)
        .subQueryId("42")
        .build();
    TrackedQuery buildResult2 = TrackedQuery.builder()
        .errorMessage("An error occurred")
        .expiryDate(1L)
        .lastKnownState(QueryState.COMPLETED)
        .lastUpdateTime(1L)
        .queryId("42")
        .recordCount(3L)
        .subQueryId("42")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link TrackedQuery#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link TrackedQuery#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean TrackedQuery.equals(Object)", "int TrackedQuery.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual5() {
    // Arrange
    TrackedQuery buildResult = TrackedQuery.builder()
        .errorMessage("An error occurred")
        .expiryDate(1L)
        .lastKnownState(QueryState.COMPLETED)
        .lastUpdateTime(1L)
        .queryId("Query Id")
        .recordCount(3L)
        .subQueryId("42")
        .build();
    TrackedQuery buildResult2 = TrackedQuery.builder()
        .errorMessage("An error occurred")
        .expiryDate(1L)
        .lastKnownState(QueryState.COMPLETED)
        .lastUpdateTime(1L)
        .queryId("42")
        .recordCount(3L)
        .subQueryId("42")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link TrackedQuery#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link TrackedQuery#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean TrackedQuery.equals(Object)", "int TrackedQuery.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual6() {
    // Arrange
    TrackedQuery buildResult = TrackedQuery.builder()
        .errorMessage("An error occurred")
        .expiryDate(1L)
        .lastKnownState(QueryState.COMPLETED)
        .lastUpdateTime(1L)
        .queryId("42")
        .recordCount(1L)
        .subQueryId("42")
        .build();
    TrackedQuery buildResult2 = TrackedQuery.builder()
        .errorMessage("An error occurred")
        .expiryDate(1L)
        .lastKnownState(QueryState.COMPLETED)
        .lastUpdateTime(1L)
        .queryId("42")
        .recordCount(3L)
        .subQueryId("42")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link TrackedQuery#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link TrackedQuery#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean TrackedQuery.equals(Object)", "int TrackedQuery.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual7() {
    // Arrange
    TrackedQuery buildResult = TrackedQuery.builder()
        .errorMessage("An error occurred")
        .expiryDate(1L)
        .lastKnownState(QueryState.COMPLETED)
        .lastUpdateTime(1L)
        .queryId("42")
        .recordCount(3L)
        .subQueryId("Sub Query Id")
        .build();
    TrackedQuery buildResult2 = TrackedQuery.builder()
        .errorMessage("An error occurred")
        .expiryDate(1L)
        .lastKnownState(QueryState.COMPLETED)
        .lastUpdateTime(1L)
        .queryId("42")
        .recordCount(3L)
        .subQueryId("42")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link TrackedQuery#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link TrackedQuery#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean TrackedQuery.equals(Object)", "int TrackedQuery.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange
    TrackedQuery buildResult = TrackedQuery.builder()
        .errorMessage("An error occurred")
        .expiryDate(1L)
        .lastKnownState(QueryState.COMPLETED)
        .lastUpdateTime(1L)
        .queryId("42")
        .recordCount(3L)
        .subQueryId("42")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, null);
  }

  /**
   * Test {@link TrackedQuery#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link TrackedQuery#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean TrackedQuery.equals(Object)", "int TrackedQuery.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange
    TrackedQuery buildResult = TrackedQuery.builder()
        .errorMessage("An error occurred")
        .expiryDate(1L)
        .lastKnownState(QueryState.COMPLETED)
        .lastUpdateTime(1L)
        .queryId("42")
        .recordCount(3L)
        .subQueryId("42")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, "Different type to TrackedQuery");
  }
}
