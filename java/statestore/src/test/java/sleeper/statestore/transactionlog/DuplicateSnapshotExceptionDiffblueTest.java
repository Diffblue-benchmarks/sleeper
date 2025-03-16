package sleeper.statestore.transactionlog;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class DuplicateSnapshotExceptionDiffblueTest {
  /**
   * Test {@link DuplicateSnapshotException#DuplicateSnapshotException(String, Exception)}.
   * <p>
   * Method under test: {@link DuplicateSnapshotException#DuplicateSnapshotException(String, Exception)}
   */
  @Test
  @DisplayName("Test new DuplicateSnapshotException(String, Exception)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DuplicateSnapshotException.<init>(String, Exception)"})
  void testNewDuplicateSnapshotException() {
    // Arrange
    Exception cause = new Exception("foo");

    // Act
    DuplicateSnapshotException actualDuplicateSnapshotException = new DuplicateSnapshotException("Path", cause);

    // Assert
    assertEquals("Snapshot already exists: Path", actualDuplicateSnapshotException.getLocalizedMessage());
    assertEquals("Snapshot already exists: Path", actualDuplicateSnapshotException.getMessage());
    assertEquals(0, actualDuplicateSnapshotException.getSuppressed().length);
    assertSame(cause, actualDuplicateSnapshotException.getCause());
  }
}
