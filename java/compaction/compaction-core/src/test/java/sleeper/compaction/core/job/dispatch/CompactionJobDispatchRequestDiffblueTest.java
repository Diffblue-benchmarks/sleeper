package sleeper.compaction.core.job.dispatch;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
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
   * Test {@link CompactionJobDispatchRequest#forTableWithBatchIdAtTime(TableProperties, String, Instant)}.
   * <ul>
   *   <li>Then return BatchKey is {@code null/compactions/42.json}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobDispatchRequest#forTableWithBatchIdAtTime(TableProperties, String, Instant)}
   */
  @Test
  @DisplayName("Test forTableWithBatchIdAtTime(TableProperties, String, Instant); then return BatchKey is 'null/compactions/42.json'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "CompactionJobDispatchRequest CompactionJobDispatchRequest.forTableWithBatchIdAtTime(TableProperties, String, Instant)"})
  void testForTableWithBatchIdAtTime_thenReturnBatchKeyIsNullCompactions42Json() {
    // Arrange
    TableProperties tableProperties = new TableProperties(new InstanceProperties());
    Instant timeNow = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    // Act
    CompactionJobDispatchRequest actualForTableWithBatchIdAtTimeResult = CompactionJobDispatchRequest
        .forTableWithBatchIdAtTime(tableProperties, "42", timeNow);

    // Assert
    assertEquals("null/compactions/42.json", actualForTableWithBatchIdAtTimeResult.getBatchKey());
    assertNull(actualForTableWithBatchIdAtTimeResult.getTableId());
    Instant expectedCreateTime = timeNow.EPOCH;
    assertSame(expectedCreateTime, actualForTableWithBatchIdAtTimeResult.getCreateTime());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link CompactionJobDispatchRequest#toString()}
   *   <li>{@link CompactionJobDispatchRequest#getBatchKey()}
   *   <li>{@link CompactionJobDispatchRequest#getCreateTime()}
   *   <li>{@link CompactionJobDispatchRequest#getTableId()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String CompactionJobDispatchRequest.getBatchKey()",
      "Instant CompactionJobDispatchRequest.getCreateTime()", "String CompactionJobDispatchRequest.getTableId()",
      "String CompactionJobDispatchRequest.toString()"})
  void testGettersAndSetters() {
    // Arrange
    TableProperties tableProperties = new TableProperties(new InstanceProperties());
    CompactionJobDispatchRequest forTableWithBatchIdAtTimeResult = CompactionJobDispatchRequest
        .forTableWithBatchIdAtTime(tableProperties, "42",
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
    assertSame(actualCreateTime.EPOCH, actualCreateTime);
  }

  /**
   * Test {@link CompactionJobDispatchRequest#equals(Object)}, and {@link CompactionJobDispatchRequest#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link CompactionJobDispatchRequest#equals(Object)}
   *   <li>{@link CompactionJobDispatchRequest#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CompactionJobDispatchRequest.equals(Object)",
      "int CompactionJobDispatchRequest.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    TableProperties tableProperties = new TableProperties(new InstanceProperties());
    CompactionJobDispatchRequest forTableWithBatchIdAtTimeResult = CompactionJobDispatchRequest
        .forTableWithBatchIdAtTime(tableProperties, "42",
            LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    TableProperties tableProperties2 = new TableProperties(new InstanceProperties());
    CompactionJobDispatchRequest forTableWithBatchIdAtTimeResult2 = CompactionJobDispatchRequest
        .forTableWithBatchIdAtTime(tableProperties2, "42",
            LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act and Assert
    assertEquals(forTableWithBatchIdAtTimeResult, forTableWithBatchIdAtTimeResult2);
    int expectedHashCodeResult = forTableWithBatchIdAtTimeResult.hashCode();
    assertEquals(expectedHashCodeResult, forTableWithBatchIdAtTimeResult2.hashCode());
  }

  /**
   * Test {@link CompactionJobDispatchRequest#equals(Object)}, and {@link CompactionJobDispatchRequest#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link CompactionJobDispatchRequest#equals(Object)}
   *   <li>{@link CompactionJobDispatchRequest#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CompactionJobDispatchRequest.equals(Object)",
      "int CompactionJobDispatchRequest.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    TableProperties tableProperties = new TableProperties(new InstanceProperties());
    CompactionJobDispatchRequest forTableWithBatchIdAtTimeResult = CompactionJobDispatchRequest
        .forTableWithBatchIdAtTime(tableProperties, "42",
            LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act and Assert
    assertEquals(forTableWithBatchIdAtTimeResult, forTableWithBatchIdAtTimeResult);
    int expectedHashCodeResult = forTableWithBatchIdAtTimeResult.hashCode();
    assertEquals(expectedHashCodeResult, forTableWithBatchIdAtTimeResult.hashCode());
  }

  /**
   * Test {@link CompactionJobDispatchRequest#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobDispatchRequest#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CompactionJobDispatchRequest.equals(Object)",
      "int CompactionJobDispatchRequest.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.get(Mockito.<TableProperty>any())).thenReturn("Get");
    CompactionJobDispatchRequest forTableWithBatchIdAtTimeResult = CompactionJobDispatchRequest
        .forTableWithBatchIdAtTime(tableProperties, "42",
            LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    TableProperties tableProperties2 = new TableProperties(new InstanceProperties());

    // Act and Assert
    assertNotEquals(forTableWithBatchIdAtTimeResult, CompactionJobDispatchRequest.forTableWithBatchIdAtTime(
        tableProperties2, "42", LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
  }

  /**
   * Test {@link CompactionJobDispatchRequest#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobDispatchRequest#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CompactionJobDispatchRequest.equals(Object)",
      "int CompactionJobDispatchRequest.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange
    TableProperties tableProperties = new TableProperties(new InstanceProperties());

    // Act and Assert
    assertNotEquals(CompactionJobDispatchRequest.forTableWithBatchIdAtTime(tableProperties, "42",
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()), null);
  }

  /**
   * Test {@link CompactionJobDispatchRequest#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobDispatchRequest#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CompactionJobDispatchRequest.equals(Object)",
      "int CompactionJobDispatchRequest.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange
    TableProperties tableProperties = new TableProperties(new InstanceProperties());

    // Act and Assert
    assertNotEquals(
        CompactionJobDispatchRequest.forTableWithBatchIdAtTime(tableProperties, "42",
            LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()),
        "Different type to CompactionJobDispatchRequest");
  }
}
