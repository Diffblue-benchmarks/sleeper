package sleeper.systemtest.dsl.instance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class InstanceDidNotDeployExceptionDiffblueTest {
  /**
   * Test {@link InstanceDidNotDeployException#InstanceDidNotDeployException(String, Throwable)}.
   * <p>
   * Method under test: {@link InstanceDidNotDeployException#InstanceDidNotDeployException(String, Throwable)}
   */
  @Test
  @DisplayName("Test new InstanceDidNotDeployException(String, Throwable)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void InstanceDidNotDeployException.<init>(String, Throwable)"})
  void testNewInstanceDidNotDeployException() {
    // Arrange
    Throwable cause = new Throwable();

    // Act
    InstanceDidNotDeployException actualInstanceDidNotDeployException = new InstanceDidNotDeployException("42", cause);

    // Assert
    assertEquals("Instance did not deploy: 42", actualInstanceDidNotDeployException.getLocalizedMessage());
    assertEquals("Instance did not deploy: 42", actualInstanceDidNotDeployException.getMessage());
    assertEquals(0, actualInstanceDidNotDeployException.getSuppressed().length);
    assertSame(cause, actualInstanceDidNotDeployException.getCause());
  }
}
