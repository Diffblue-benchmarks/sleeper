package sleeper.core.statestore.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class NewReferenceSameAsOldReferenceExceptionDiffblueTest {
  /**
   * Test {@link NewReferenceSameAsOldReferenceException#NewReferenceSameAsOldReferenceException(String)}.
   * <p>
   * Method under test: {@link NewReferenceSameAsOldReferenceException#NewReferenceSameAsOldReferenceException(String)}
   */
  @Test
  @DisplayName("Test new NewReferenceSameAsOldReferenceException(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void NewReferenceSameAsOldReferenceException.<init>(String)"})
  void testNewNewReferenceSameAsOldReferenceException() {
    // Arrange and Act
    NewReferenceSameAsOldReferenceException actualNewReferenceSameAsOldReferenceException = new NewReferenceSameAsOldReferenceException(
        "foo.txt");

    // Assert
    assertEquals("New file has the same filename as a file being removed: foo.txt",
        actualNewReferenceSameAsOldReferenceException.getLocalizedMessage());
    assertEquals("New file has the same filename as a file being removed: foo.txt",
        actualNewReferenceSameAsOldReferenceException.getMessage());
    assertNull(actualNewReferenceSameAsOldReferenceException.getCause());
    assertEquals(0, actualNewReferenceSameAsOldReferenceException.getSuppressed().length);
  }
}
