package sleeper.compaction.core.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class TimedOutWaitingForFileAssignmentsExceptionDiffblueTest {
  /**
   * Test new {@link TimedOutWaitingForFileAssignmentsException} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link
   * TimedOutWaitingForFileAssignmentsException}
   */
  @Test
  @DisplayName("Test new TimedOutWaitingForFileAssignmentsException (default constructor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void TimedOutWaitingForFileAssignmentsException.<init>()"})
  void testNewTimedOutWaitingForFileAssignmentsException() {
    // Arrange and Act
    TimedOutWaitingForFileAssignmentsException actualTimedOutWaitingForFileAssignmentsException =
        new TimedOutWaitingForFileAssignmentsException();

    // Assert
    assertEquals(
        "Too many retries waiting for input files to be assigned to job in state store",
        actualTimedOutWaitingForFileAssignmentsException.getMessage());
    assertNull(actualTimedOutWaitingForFileAssignmentsException.getCause());
    assertEquals(0, actualTimedOutWaitingForFileAssignmentsException.getSuppressed().length);
  }
}
