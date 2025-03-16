package sleeper.systemtest.dsl.instance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class NoTableChosenExceptionDiffblueTest {
  /**
   * Test new {@link NoTableChosenException} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link NoTableChosenException}
   */
  @Test
  @DisplayName("Test new NoTableChosenException (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void NoTableChosenException.<init>()"})
  void testNewNoTableChosenException() {
    // Arrange and Act
    NoTableChosenException actualNoTableChosenException = new NoTableChosenException();

    // Assert
    assertEquals("No Sleeper table has been chosen to work with.", actualNoTableChosenException.getMessage());
    assertNull(actualNoTableChosenException.getCause());
    assertEquals(0, actualNoTableChosenException.getSuppressed().length);
  }
}
