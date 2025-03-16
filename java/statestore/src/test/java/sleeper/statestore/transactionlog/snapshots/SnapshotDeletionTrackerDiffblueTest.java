package sleeper.statestore.transactionlog.snapshots;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class SnapshotDeletionTrackerDiffblueTest {
  /**
   * Test {@link SnapshotDeletionTracker#deleteSuccess(long)}.
   * <p>
   * Method under test: {@link SnapshotDeletionTracker#deleteSuccess(long)}
   */
  @Test
  @DisplayName("Test deleteSuccess(long)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SnapshotDeletionTracker.deleteSuccess(long)"})
  void testDeleteSuccess() {
    // Arrange
    SnapshotDeletionTracker snapshotDeletionTracker = new SnapshotDeletionTracker();

    // Act
    snapshotDeletionTracker.deleteSuccess(1L);

    // Assert
    assertEquals(1, snapshotDeletionTracker.getDeletedCount().intValue());
    assertEquals(1L, snapshotDeletionTracker.getLastTransactionNumber().longValue());
  }

  /**
   * Test {@link SnapshotDeletionTracker#getLastTransactionNumber()}.
   * <p>
   * Method under test: {@link SnapshotDeletionTracker#getLastTransactionNumber()}
   */
  @Test
  @DisplayName("Test getLastTransactionNumber()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.lang.Long SnapshotDeletionTracker.getLastTransactionNumber()"})
  void testGetLastTransactionNumber() {
    // Arrange, Act and Assert
    assertNull((new SnapshotDeletionTracker()).getLastTransactionNumber());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>default or parameterless constructor of {@link SnapshotDeletionTracker}
   *   <li>{@link SnapshotDeletionTracker#getDeletedCount()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SnapshotDeletionTracker.<init>()",
      "java.lang.Integer SnapshotDeletionTracker.getDeletedCount()"})
  void testGettersAndSetters() {
    // Arrange, Act and Assert
    assertEquals(0, (new SnapshotDeletionTracker()).getDeletedCount().intValue());
  }
}
