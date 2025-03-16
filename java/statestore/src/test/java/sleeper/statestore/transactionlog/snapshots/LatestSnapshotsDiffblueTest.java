package sleeper.statestore.transactionlog.snapshots;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class LatestSnapshotsDiffblueTest {
  /**
   * Test {@link LatestSnapshots#empty()}.
   * <p>
   * Method under test: {@link LatestSnapshots#empty()}
   */
  @Test
  @DisplayName("Test empty()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"LatestSnapshots LatestSnapshots.empty()"})
  void testEmpty() {
    // Arrange and Act
    LatestSnapshots actualEmptyResult = LatestSnapshots.empty();

    // Assert
    Optional<TransactionLogSnapshotMetadata> filesSnapshot = actualEmptyResult.getFilesSnapshot();
    assertFalse(filesSnapshot.isPresent());
    assertSame(filesSnapshot, actualEmptyResult.getPartitionsSnapshot());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link LatestSnapshots#LatestSnapshots(TransactionLogSnapshotMetadata, TransactionLogSnapshotMetadata)}
   *   <li>{@link LatestSnapshots#toString()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void LatestSnapshots.<init>(TransactionLogSnapshotMetadata, TransactionLogSnapshotMetadata)",
      "java.lang.String LatestSnapshots.toString()"})
  void testGettersAndSetters() {
    // Arrange
    TransactionLogSnapshotMetadata filesSnapshot = TransactionLogSnapshotMetadata.forFiles("Base Path", 1L);

    // Act and Assert
    assertEquals(
        "LatestSnapshots{filesSnapshot=TransactionLogSnapshot{path=Base Path/statestore/snapshots/1-files.arrow,"
            + " type=FILES, transactionNumber=1, createdTime=null}, partitionsSnapshot=TransactionLogSnapshot{path=Base"
            + " Path/statestore/snapshots/1-files.arrow, type=FILES, transactionNumber=1, createdTime=null}}",
        (new LatestSnapshots(filesSnapshot, TransactionLogSnapshotMetadata.forFiles("Base Path", 1L))).toString());
  }

  /**
   * Test {@link LatestSnapshots#getFilesSnapshot()}.
   * <p>
   * Method under test: {@link LatestSnapshots#getFilesSnapshot()}
   */
  @Test
  @DisplayName("Test getFilesSnapshot()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Optional LatestSnapshots.getFilesSnapshot()"})
  void testGetFilesSnapshot() {
    // Arrange, Act and Assert
    assertFalse(LatestSnapshots.empty().getFilesSnapshot().isPresent());
  }

  /**
   * Test {@link LatestSnapshots#getPartitionsSnapshot()}.
   * <p>
   * Method under test: {@link LatestSnapshots#getPartitionsSnapshot()}
   */
  @Test
  @DisplayName("Test getPartitionsSnapshot()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Optional LatestSnapshots.getPartitionsSnapshot()"})
  void testGetPartitionsSnapshot() {
    // Arrange, Act and Assert
    assertFalse(LatestSnapshots.empty().getPartitionsSnapshot().isPresent());
  }

  /**
   * Test {@link LatestSnapshots#getSnapshot(SnapshotType)}.
   * <ul>
   *   <li>When {@code FILES}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LatestSnapshots#getSnapshot(SnapshotType)}
   */
  @Test
  @DisplayName("Test getSnapshot(SnapshotType); when 'FILES'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Optional LatestSnapshots.getSnapshot(SnapshotType)"})
  void testGetSnapshot_whenFiles() {
    // Arrange, Act and Assert
    assertFalse(LatestSnapshots.empty().getSnapshot(SnapshotType.FILES).isPresent());
  }

  /**
   * Test {@link LatestSnapshots#getSnapshot(SnapshotType)}.
   * <ul>
   *   <li>When {@code PARTITIONS}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LatestSnapshots#getSnapshot(SnapshotType)}
   */
  @Test
  @DisplayName("Test getSnapshot(SnapshotType); when 'PARTITIONS'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Optional LatestSnapshots.getSnapshot(SnapshotType)"})
  void testGetSnapshot_whenPartitions() {
    // Arrange, Act and Assert
    assertFalse(LatestSnapshots.empty().getSnapshot(SnapshotType.PARTITIONS).isPresent());
  }

  /**
   * Test {@link LatestSnapshots#equals(Object)}, and {@link LatestSnapshots#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link LatestSnapshots#equals(Object)}
   *   <li>{@link LatestSnapshots#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean LatestSnapshots.equals(Object)", "int LatestSnapshots.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    LatestSnapshots emptyResult = LatestSnapshots.empty();
    LatestSnapshots emptyResult2 = LatestSnapshots.empty();

    // Act and Assert
    assertEquals(emptyResult, emptyResult2);
    int expectedHashCodeResult = emptyResult.hashCode();
    assertEquals(expectedHashCodeResult, emptyResult2.hashCode());
  }

  /**
   * Test {@link LatestSnapshots#equals(Object)}, and {@link LatestSnapshots#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link LatestSnapshots#equals(Object)}
   *   <li>{@link LatestSnapshots#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean LatestSnapshots.equals(Object)", "int LatestSnapshots.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    LatestSnapshots emptyResult = LatestSnapshots.empty();

    // Act and Assert
    assertEquals(emptyResult, emptyResult);
    int expectedHashCodeResult = emptyResult.hashCode();
    assertEquals(expectedHashCodeResult, emptyResult.hashCode());
  }

  /**
   * Test {@link LatestSnapshots#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link LatestSnapshots#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean LatestSnapshots.equals(Object)", "int LatestSnapshots.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    TransactionLogSnapshotMetadata filesSnapshot = TransactionLogSnapshotMetadata.forFiles("Base Path", 1L);
    LatestSnapshots latestSnapshots = new LatestSnapshots(filesSnapshot,
        TransactionLogSnapshotMetadata.forFiles("Base Path", 1L));

    // Act and Assert
    assertNotEquals(latestSnapshots, LatestSnapshots.empty());
  }

  /**
   * Test {@link LatestSnapshots#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link LatestSnapshots#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean LatestSnapshots.equals(Object)", "int LatestSnapshots.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    LatestSnapshots latestSnapshots = new LatestSnapshots(null,
        TransactionLogSnapshotMetadata.forFiles("Base Path", 1L));

    // Act and Assert
    assertNotEquals(latestSnapshots, LatestSnapshots.empty());
  }

  /**
   * Test {@link LatestSnapshots#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link LatestSnapshots#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean LatestSnapshots.equals(Object)", "int LatestSnapshots.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(LatestSnapshots.empty(), null);
  }

  /**
   * Test {@link LatestSnapshots#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link LatestSnapshots#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean LatestSnapshots.equals(Object)", "int LatestSnapshots.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(LatestSnapshots.empty(), "Different type to LatestSnapshots");
  }
}
