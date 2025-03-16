package sleeper.systemtest.dsl.instance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class NoInstanceConnectedExceptionDiffblueTest {
  /**
   * Test new {@link NoInstanceConnectedException} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link NoInstanceConnectedException}
   */
  @Test
  @DisplayName("Test new NoInstanceConnectedException (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void NoInstanceConnectedException.<init>()"})
  void testNewNoInstanceConnectedException() {
    // Arrange and Act
    NoInstanceConnectedException actualNoInstanceConnectedException = new NoInstanceConnectedException();

    // Assert
    assertEquals("Not connected to a Sleeper instance", actualNoInstanceConnectedException.getMessage());
    assertNull(actualNoInstanceConnectedException.getCause());
    assertEquals(0, actualNoInstanceConnectedException.getSuppressed().length);
  }
}
