package sleeper.statestore.transactionlog.snapshots;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class TransactionLogSnapshotMetadataDiffblueTest {
  /**
   * Test {@link TransactionLogSnapshotMetadata#forFiles(String, long)} with {@code basePath}, {@code transactionNumber}.
   * <p>
   * Method under test: {@link TransactionLogSnapshotMetadata#forFiles(String, long)}
   */
  @Test
  @DisplayName("Test forFiles(String, long) with 'basePath', 'transactionNumber'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"TransactionLogSnapshotMetadata TransactionLogSnapshotMetadata.forFiles(String, long)"})
  void testForFilesWithBasePathTransactionNumber() {
    // Arrange and Act
    TransactionLogSnapshotMetadata actualForFilesResult = TransactionLogSnapshotMetadata.forFiles("Base Path", 1L);

    // Assert
    assertEquals("Base Path/statestore/snapshots/1-files.arrow", actualForFilesResult.getPath());
    assertNull(actualForFilesResult.getCreatedTime());
    assertEquals(1L, actualForFilesResult.getTransactionNumber());
    assertEquals(SnapshotType.FILES, actualForFilesResult.getType());
  }

  /**
   * Test {@link TransactionLogSnapshotMetadata#forFiles(String, long, Instant)} with {@code basePath}, {@code transactionNumber}, {@code createdTime}.
   * <p>
   * Method under test: {@link TransactionLogSnapshotMetadata#forFiles(String, long, Instant)}
   */
  @Test
  @DisplayName("Test forFiles(String, long, Instant) with 'basePath', 'transactionNumber', 'createdTime'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"TransactionLogSnapshotMetadata TransactionLogSnapshotMetadata.forFiles(String, long, Instant)"})
  void testForFilesWithBasePathTransactionNumberCreatedTime() {
    // Arrange
    Instant createdTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    // Act
    TransactionLogSnapshotMetadata actualForFilesResult = TransactionLogSnapshotMetadata.forFiles("Base Path", 1L,
        createdTime);

    // Assert
    assertEquals("Base Path/statestore/snapshots/1-files.arrow", actualForFilesResult.getPath());
    assertEquals(1L, actualForFilesResult.getTransactionNumber());
    assertEquals(SnapshotType.FILES, actualForFilesResult.getType());
    Instant expectedCreatedTime = createdTime.EPOCH;
    assertSame(expectedCreatedTime, actualForFilesResult.getCreatedTime());
  }

  /**
   * Test {@link TransactionLogSnapshotMetadata#forPartitions(String, long)} with {@code basePath}, {@code transactionNumber}.
   * <p>
   * Method under test: {@link TransactionLogSnapshotMetadata#forPartitions(String, long)}
   */
  @Test
  @DisplayName("Test forPartitions(String, long) with 'basePath', 'transactionNumber'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"TransactionLogSnapshotMetadata TransactionLogSnapshotMetadata.forPartitions(String, long)"})
  void testForPartitionsWithBasePathTransactionNumber() {
    // Arrange and Act
    TransactionLogSnapshotMetadata actualForPartitionsResult = TransactionLogSnapshotMetadata.forPartitions("Base Path",
        1L);

    // Assert
    assertEquals("Base Path/statestore/snapshots/1-partitions.arrow", actualForPartitionsResult.getPath());
    assertNull(actualForPartitionsResult.getCreatedTime());
    assertEquals(1L, actualForPartitionsResult.getTransactionNumber());
    assertEquals(SnapshotType.PARTITIONS, actualForPartitionsResult.getType());
  }

  /**
   * Test {@link TransactionLogSnapshotMetadata#forPartitions(String, long, Instant)} with {@code basePath}, {@code transactionNumber}, {@code createdTime}.
   * <p>
   * Method under test: {@link TransactionLogSnapshotMetadata#forPartitions(String, long, Instant)}
   */
  @Test
  @DisplayName("Test forPartitions(String, long, Instant) with 'basePath', 'transactionNumber', 'createdTime'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "TransactionLogSnapshotMetadata TransactionLogSnapshotMetadata.forPartitions(String, long, Instant)"})
  void testForPartitionsWithBasePathTransactionNumberCreatedTime() {
    // Arrange
    Instant createdTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    // Act
    TransactionLogSnapshotMetadata actualForPartitionsResult = TransactionLogSnapshotMetadata.forPartitions("Base Path",
        1L, createdTime);

    // Assert
    assertEquals("Base Path/statestore/snapshots/1-partitions.arrow", actualForPartitionsResult.getPath());
    assertEquals(1L, actualForPartitionsResult.getTransactionNumber());
    assertEquals(SnapshotType.PARTITIONS, actualForPartitionsResult.getType());
    Instant expectedCreatedTime = createdTime.EPOCH;
    assertSame(expectedCreatedTime, actualForPartitionsResult.getCreatedTime());
  }

  /**
   * Test {@link TransactionLogSnapshotMetadata#TransactionLogSnapshotMetadata(String, SnapshotType, long)}.
   * <p>
   * Method under test: {@link TransactionLogSnapshotMetadata#TransactionLogSnapshotMetadata(String, SnapshotType, long)}
   */
  @Test
  @DisplayName("Test new TransactionLogSnapshotMetadata(String, SnapshotType, long)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void TransactionLogSnapshotMetadata.<init>(String, SnapshotType, long)"})
  void testNewTransactionLogSnapshotMetadata() {
    // Arrange and Act
    TransactionLogSnapshotMetadata actualTransactionLogSnapshotMetadata = new TransactionLogSnapshotMetadata("Path",
        SnapshotType.FILES, 1L);

    // Assert
    assertEquals("Path", actualTransactionLogSnapshotMetadata.getPath());
    assertNull(actualTransactionLogSnapshotMetadata.getCreatedTime());
    assertEquals(1L, actualTransactionLogSnapshotMetadata.getTransactionNumber());
    assertEquals(SnapshotType.FILES, actualTransactionLogSnapshotMetadata.getType());
  }

  /**
   * Test {@link TransactionLogSnapshotMetadata#TransactionLogSnapshotMetadata(String, SnapshotType, long, Instant)}.
   * <p>
   * Method under test: {@link TransactionLogSnapshotMetadata#TransactionLogSnapshotMetadata(String, SnapshotType, long, Instant)}
   */
  @Test
  @DisplayName("Test new TransactionLogSnapshotMetadata(String, SnapshotType, long, Instant)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void TransactionLogSnapshotMetadata.<init>(String, SnapshotType, long, Instant)"})
  void testNewTransactionLogSnapshotMetadata2() {
    // Arrange
    Instant createdTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    // Act
    TransactionLogSnapshotMetadata actualTransactionLogSnapshotMetadata = new TransactionLogSnapshotMetadata("Path",
        SnapshotType.FILES, 1L, createdTime);

    // Assert
    assertEquals("Path", actualTransactionLogSnapshotMetadata.getPath());
    assertEquals(1L, actualTransactionLogSnapshotMetadata.getTransactionNumber());
    assertEquals(SnapshotType.FILES, actualTransactionLogSnapshotMetadata.getType());
    Instant expectedCreatedTime = createdTime.EPOCH;
    assertSame(expectedCreatedTime, actualTransactionLogSnapshotMetadata.getCreatedTime());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link TransactionLogSnapshotMetadata#toString()}
   *   <li>{@link TransactionLogSnapshotMetadata#getCreatedTime()}
   *   <li>{@link TransactionLogSnapshotMetadata#getPath()}
   *   <li>{@link TransactionLogSnapshotMetadata#getTransactionNumber()}
   *   <li>{@link TransactionLogSnapshotMetadata#getType()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Instant TransactionLogSnapshotMetadata.getCreatedTime()",
      "String TransactionLogSnapshotMetadata.getPath()", "long TransactionLogSnapshotMetadata.getTransactionNumber()",
      "SnapshotType TransactionLogSnapshotMetadata.getType()", "String TransactionLogSnapshotMetadata.toString()"})
  void testGettersAndSetters() {
    // Arrange
    TransactionLogSnapshotMetadata forFilesResult = TransactionLogSnapshotMetadata.forFiles("Base Path", 1L);

    // Act
    String actualToStringResult = forFilesResult.toString();
    Instant actualCreatedTime = forFilesResult.getCreatedTime();
    String actualPath = forFilesResult.getPath();
    long actualTransactionNumber = forFilesResult.getTransactionNumber();

    // Assert
    assertEquals("Base Path/statestore/snapshots/1-files.arrow", actualPath);
    assertEquals(
        "TransactionLogSnapshot{path=Base Path/statestore/snapshots/1-files.arrow, type=FILES, transactionNumber=1,"
            + " createdTime=null}",
        actualToStringResult);
    assertNull(actualCreatedTime);
    assertEquals(1L, actualTransactionNumber);
    assertEquals(SnapshotType.FILES, forFilesResult.getType());
  }

  /**
   * Test {@link TransactionLogSnapshotMetadata#equals(Object)}, and {@link TransactionLogSnapshotMetadata#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link TransactionLogSnapshotMetadata#equals(Object)}
   *   <li>{@link TransactionLogSnapshotMetadata#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean TransactionLogSnapshotMetadata.equals(Object)",
      "int TransactionLogSnapshotMetadata.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    TransactionLogSnapshotMetadata forFilesResult = TransactionLogSnapshotMetadata.forFiles("Base Path", 1L);
    TransactionLogSnapshotMetadata forFilesResult2 = TransactionLogSnapshotMetadata.forFiles("Base Path", 1L);

    // Act and Assert
    assertEquals(forFilesResult, forFilesResult2);
    int expectedHashCodeResult = forFilesResult.hashCode();
    assertEquals(expectedHashCodeResult, forFilesResult2.hashCode());
  }

  /**
   * Test {@link TransactionLogSnapshotMetadata#equals(Object)}, and {@link TransactionLogSnapshotMetadata#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link TransactionLogSnapshotMetadata#equals(Object)}
   *   <li>{@link TransactionLogSnapshotMetadata#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean TransactionLogSnapshotMetadata.equals(Object)",
      "int TransactionLogSnapshotMetadata.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    TransactionLogSnapshotMetadata forFilesResult = TransactionLogSnapshotMetadata.forFiles("Base Path", 1L);

    // Act and Assert
    assertEquals(forFilesResult, forFilesResult);
    int expectedHashCodeResult = forFilesResult.hashCode();
    assertEquals(expectedHashCodeResult, forFilesResult.hashCode());
  }

  /**
   * Test {@link TransactionLogSnapshotMetadata#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link TransactionLogSnapshotMetadata#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean TransactionLogSnapshotMetadata.equals(Object)",
      "int TransactionLogSnapshotMetadata.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    TransactionLogSnapshotMetadata forFilesResult = TransactionLogSnapshotMetadata
        .forFiles("sleeper.statestore.transactionlog.snapshots.TransactionLogSnapshotMetadata", 1L);

    // Act and Assert
    assertNotEquals(forFilesResult, TransactionLogSnapshotMetadata.forFiles("Base Path", 1L));
  }

  /**
   * Test {@link TransactionLogSnapshotMetadata#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link TransactionLogSnapshotMetadata#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean TransactionLogSnapshotMetadata.equals(Object)",
      "int TransactionLogSnapshotMetadata.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(TransactionLogSnapshotMetadata.forFiles("Base Path", 1L), null);
  }

  /**
   * Test {@link TransactionLogSnapshotMetadata#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link TransactionLogSnapshotMetadata#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean TransactionLogSnapshotMetadata.equals(Object)",
      "int TransactionLogSnapshotMetadata.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(TransactionLogSnapshotMetadata.forFiles("Base Path", 1L),
        "Different type to TransactionLogSnapshotMetadata");
  }
}
