package sleeper.compaction.core.job.dispatch;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.table.TableProperties;
import sleeper.core.properties.table.TableProperty;

class CompactionJobDispatchRequestDiffblueTest {
  /**
   * Test {@link CompactionJobDispatchRequest#forTableWithBatchIdAtTime(TableProperties, String,
   * Instant)}.
   *
   * <ul>
   *   <li>Then return BatchKey is {@code null/compactions/42.json}.
   * </ul>
   *
   * <p>Method under test: {@link
   * CompactionJobDispatchRequest#forTableWithBatchIdAtTime(TableProperties, String, Instant)}
   */
  @Test
  @DisplayName(
      "Test forTableWithBatchIdAtTime(TableProperties, String, Instant); then return BatchKey is 'null/compactions/42.json'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "CompactionJobDispatchRequest CompactionJobDispatchRequest.forTableWithBatchIdAtTime(TableProperties, String, Instant)"
  })
  void testForTableWithBatchIdAtTime_thenReturnBatchKeyIsNullCompactions42Json() {
    // Arrange and Act
    CompactionJobDispatchRequest actualForTableWithBatchIdAtTimeResult =
        CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
            new TableProperties(new InstanceProperties()),
            "42",
            LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Assert
    assertEquals("null/compactions/42.json", actualForTableWithBatchIdAtTimeResult.getBatchKey());
    assertNull(actualForTableWithBatchIdAtTimeResult.getTableId());
    assertSame(Instant.EPOCH, actualForTableWithBatchIdAtTimeResult.getCreateTime());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link CompactionJobDispatchRequest#toString()}
   *   <li>{@link CompactionJobDispatchRequest#getBatchKey()}
   *   <li>{@link CompactionJobDispatchRequest#getCreateTime()}
   *   <li>{@link CompactionJobDispatchRequest#getTableId()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String CompactionJobDispatchRequest.getBatchKey()",
    "Instant CompactionJobDispatchRequest.getCreateTime()",
    "String CompactionJobDispatchRequest.getTableId()",
    "String CompactionJobDispatchRequest.toString()"
  })
  void testGettersAndSetters() {
    // Arrange
    CompactionJobDispatchRequest forTableWithBatchIdAtTimeResult =
        CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
            new TableProperties(new InstanceProperties()),
            "42",
            LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act
    String actualToStringResult = forTableWithBatchIdAtTimeResult.toString();
    String actualBatchKey = forTableWithBatchIdAtTimeResult.getBatchKey();
    Instant actualCreateTime = forTableWithBatchIdAtTimeResult.getCreateTime();

    // Assert
    assertEquals(
        "CompactionJobDispatchRequest{tableId=null, batchKey=null/compactions/42.json, createTime=1970-01-01T00"
            + ":00:00Z}",
        actualToStringResult);
    assertEquals("null/compactions/42.json", actualBatchKey);
    assertNull(forTableWithBatchIdAtTimeResult.getTableId());
    assertSame(Instant.EPOCH, actualCreateTime);
  }

  /**
   * Test {@link CompactionJobDispatchRequest#equals(Object)}, and {@link
   * CompactionJobDispatchRequest#hashCode()}.
   *
   * <ul>
   *   <li>When other is equal.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link CompactionJobDispatchRequest#equals(Object)}
   *   <li>{@link CompactionJobDispatchRequest#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean CompactionJobDispatchRequest.equals(Object)",
    "int CompactionJobDispatchRequest.hashCode()"
  })
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    CompactionJobDispatchRequest forTableWithBatchIdAtTimeResult =
        CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
            new TableProperties(new InstanceProperties()),
            "42",
            LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    CompactionJobDispatchRequest forTableWithBatchIdAtTimeResult2 =
        CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
            new TableProperties(new InstanceProperties()),
            "42",
            LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act and Assert
    assertEquals(forTableWithBatchIdAtTimeResult, forTableWithBatchIdAtTimeResult2);
    assertEquals(
        forTableWithBatchIdAtTimeResult.hashCode(), forTableWithBatchIdAtTimeResult2.hashCode());
  }

  /**
   * Test {@link CompactionJobDispatchRequest#equals(Object)}, and {@link
   * CompactionJobDispatchRequest#hashCode()}.
   *
   * <ul>
   *   <li>When other is same.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link CompactionJobDispatchRequest#equals(Object)}
   *   <li>{@link CompactionJobDispatchRequest#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean CompactionJobDispatchRequest.equals(Object)",
    "int CompactionJobDispatchRequest.hashCode()"
  })
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    CompactionJobDispatchRequest forTableWithBatchIdAtTimeResult =
        CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
            new TableProperties(new InstanceProperties()),
            "42",
            LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act and Assert
    assertEquals(forTableWithBatchIdAtTimeResult, forTableWithBatchIdAtTimeResult);
    int expectedHashCodeResult = forTableWithBatchIdAtTimeResult.hashCode();
    assertEquals(expectedHashCodeResult, forTableWithBatchIdAtTimeResult.hashCode());
  }

  /**
   * Test {@link CompactionJobDispatchRequest#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link CompactionJobDispatchRequest#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean CompactionJobDispatchRequest.equals(Object)",
    "int CompactionJobDispatchRequest.hashCode()"
  })
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(
        CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
            new TableProperties(new InstanceProperties()),
            "42",
            LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()),
        1);
  }

  /**
   * Test {@link CompactionJobDispatchRequest#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link CompactionJobDispatchRequest#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean CompactionJobDispatchRequest.equals(Object)",
    "int CompactionJobDispatchRequest.hashCode()"
  })
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    CompactionJobDispatchRequest forTableWithBatchIdAtTimeResult =
        CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
            new TableProperties(new InstanceProperties()),
            "Batch Id",
            LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act and Assert
    assertNotEquals(
        forTableWithBatchIdAtTimeResult,
        CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
            new TableProperties(new InstanceProperties()),
            "42",
            LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
  }

  /**
   * Test {@link CompactionJobDispatchRequest#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link CompactionJobDispatchRequest#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean CompactionJobDispatchRequest.equals(Object)",
    "int CompactionJobDispatchRequest.hashCode()"
  })
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual3() {
    // Arrange
    CompactionJobDispatchRequest forTableWithBatchIdAtTimeResult =
        CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
            new TableProperties(new InstanceProperties()),
            "42",
            LocalDate.now().atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act and Assert
    assertNotEquals(
        forTableWithBatchIdAtTimeResult,
        CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
            new TableProperties(new InstanceProperties()),
            "42",
            LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
  }

  /**
   * Test {@link CompactionJobDispatchRequest#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link CompactionJobDispatchRequest#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean CompactionJobDispatchRequest.equals(Object)",
    "int CompactionJobDispatchRequest.hashCode()"
  })
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual4() {
    // Arrange
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.get(Mockito.<TableProperty>any())).thenReturn("Get");
    CompactionJobDispatchRequest forTableWithBatchIdAtTimeResult =
        CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
            tableProperties,
            "42",
            LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act and Assert
    assertNotEquals(
        forTableWithBatchIdAtTimeResult,
        CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
            new TableProperties(new InstanceProperties()),
            "42",
            LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
  }

  /**
   * Test {@link CompactionJobDispatchRequest#equals(Object)}.
   *
   * <ul>
   *   <li>When other is {@code null}.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link CompactionJobDispatchRequest#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean CompactionJobDispatchRequest.equals(Object)",
    "int CompactionJobDispatchRequest.hashCode()"
  })
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(
        CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
            new TableProperties(new InstanceProperties()),
            "42",
            LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()),
        null);
  }

  /**
   * Test {@link CompactionJobDispatchRequest#equals(Object)}.
   *
   * <ul>
   *   <li>When other is wrong type.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link CompactionJobDispatchRequest#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean CompactionJobDispatchRequest.equals(Object)",
    "int CompactionJobDispatchRequest.hashCode()"
  })
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(
        CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
            new TableProperties(new InstanceProperties()),
            "42",
            LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()),
        "Different type to CompactionJobDispatchRequest");
  }
}
