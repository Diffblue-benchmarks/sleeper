package sleeper.clients.util.cdk;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class CdkCommandDiffblueTest {
  /**
   * Test {@link CdkCommand#deployPropertiesChange()}.
   * <p>
   * Method under test: {@link CdkCommand#deployPropertiesChange()}
   */
  @Test
  @DisplayName("Test deployPropertiesChange()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"CdkDeploy CdkCommand.deployPropertiesChange()"})
  void testDeployPropertiesChange() {
    // Arrange and Act
    CdkDeploy actualDeployPropertiesChangeResult = CdkCommand.deployPropertiesChange();

    // Assert
    Stream<String> command = actualDeployPropertiesChangeResult.getCommand();
    List<String> collectResult = command.limit(5).collect(Collectors.toList());
    assertEquals(3, collectResult.size());
    assertEquals("--require-approval", collectResult.get(1));
    assertEquals("deploy", collectResult.get(0));
    assertEquals("never", collectResult.get(2));
    Stream<String> arguments = actualDeployPropertiesChangeResult.getArguments();
    assertTrue(arguments.limit(5).collect(Collectors.toList()).isEmpty());
  }

  /**
   * Test {@link CdkCommand#deploySystemTestStandalone()}.
   * <p>
   * Method under test: {@link CdkCommand#deploySystemTestStandalone()}
   */
  @Test
  @DisplayName("Test deploySystemTestStandalone()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"CdkDeploy CdkCommand.deploySystemTestStandalone()"})
  void testDeploySystemTestStandalone() {
    // Arrange and Act
    CdkDeploy actualDeploySystemTestStandaloneResult = CdkCommand.deploySystemTestStandalone();

    // Assert
    Stream<String> command = actualDeploySystemTestStandaloneResult.getCommand();
    List<String> collectResult = command.limit(5).collect(Collectors.toList());
    assertEquals(3, collectResult.size());
    assertEquals("--require-approval", collectResult.get(1));
    assertEquals("deploy", collectResult.get(0));
    assertEquals("never", collectResult.get(2));
    Stream<String> arguments = actualDeploySystemTestStandaloneResult.getArguments();
    assertTrue(arguments.limit(5).collect(Collectors.toList()).isEmpty());
  }

  /**
   * Test {@link CdkCommand#deployExisting()}.
   * <p>
   * Method under test: {@link CdkCommand#deployExisting()}
   */
  @Test
  @DisplayName("Test deployExisting()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"CdkDeploy CdkCommand.deployExisting()"})
  void testDeployExisting() {
    // Arrange and Act
    CdkDeploy actualDeployExistingResult = CdkCommand.deployExisting();

    // Assert
    Stream<String> command = actualDeployExistingResult.getCommand();
    List<String> collectResult = command.limit(5).collect(Collectors.toList());
    assertEquals(3, collectResult.size());
    assertEquals("--require-approval", collectResult.get(1));
    Stream<String> arguments = actualDeployExistingResult.getArguments();
    List<String> collectResult2 = arguments.limit(5).collect(Collectors.toList());
    assertEquals(2, collectResult2.size());
    assertEquals("-c", collectResult2.get(0));
    assertEquals("deploy", collectResult.get(0));
    assertEquals("never", collectResult.get(2));
    assertEquals("skipVersionCheck=true", collectResult2.get(1));
  }

  /**
   * Test {@link CdkCommand#deployExistingPaused()}.
   * <p>
   * Method under test: {@link CdkCommand#deployExistingPaused()}
   */
  @Test
  @DisplayName("Test deployExistingPaused()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"CdkDeploy CdkCommand.deployExistingPaused()"})
  void testDeployExistingPaused() {
    // Arrange and Act
    CdkDeploy actualDeployExistingPausedResult = CdkCommand.deployExistingPaused();

    // Assert
    Stream<String> command = actualDeployExistingPausedResult.getCommand();
    List<String> collectResult = command.limit(5).collect(Collectors.toList());
    assertEquals(3, collectResult.size());
    assertEquals("--require-approval", collectResult.get(1));
    Stream<String> arguments = actualDeployExistingPausedResult.getArguments();
    List<String> collectResult2 = arguments.limit(5).collect(Collectors.toList());
    assertEquals(4, collectResult2.size());
    assertEquals("-c", collectResult2.get(0));
    assertEquals("-c", collectResult2.get(2));
    assertEquals("deploy", collectResult.get(0));
    assertEquals("deployPaused=true", collectResult2.get(3));
    assertEquals("never", collectResult.get(2));
    assertEquals("skipVersionCheck=true", collectResult2.get(1));
  }

  /**
   * Test {@link CdkCommand#deployNew()}.
   * <p>
   * Method under test: {@link CdkCommand#deployNew()}
   */
  @Test
  @DisplayName("Test deployNew()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"CdkDeploy CdkCommand.deployNew()"})
  void testDeployNew() {
    // Arrange and Act
    CdkDeploy actualDeployNewResult = CdkCommand.deployNew();

    // Assert
    Stream<String> command = actualDeployNewResult.getCommand();
    List<String> collectResult = command.limit(5).collect(Collectors.toList());
    assertEquals(3, collectResult.size());
    assertEquals("--require-approval", collectResult.get(1));
    Stream<String> arguments = actualDeployNewResult.getArguments();
    List<String> collectResult2 = arguments.limit(5).collect(Collectors.toList());
    assertEquals(2, collectResult2.size());
    assertEquals("-c", collectResult2.get(0));
    assertEquals("deploy", collectResult.get(0));
    assertEquals("never", collectResult.get(2));
    assertEquals("newinstance=true", collectResult2.get(1));
  }

  /**
   * Test {@link CdkCommand#deployNewPaused()}.
   * <p>
   * Method under test: {@link CdkCommand#deployNewPaused()}
   */
  @Test
  @DisplayName("Test deployNewPaused()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"CdkDeploy CdkCommand.deployNewPaused()"})
  void testDeployNewPaused() {
    // Arrange and Act
    CdkDeploy actualDeployNewPausedResult = CdkCommand.deployNewPaused();

    // Assert
    Stream<String> command = actualDeployNewPausedResult.getCommand();
    List<String> collectResult = command.limit(5).collect(Collectors.toList());
    assertEquals(3, collectResult.size());
    assertEquals("--require-approval", collectResult.get(1));
    Stream<String> arguments = actualDeployNewPausedResult.getArguments();
    List<String> collectResult2 = arguments.limit(5).collect(Collectors.toList());
    assertEquals(4, collectResult2.size());
    assertEquals("-c", collectResult2.get(0));
    assertEquals("-c", collectResult2.get(2));
    assertEquals("deploy", collectResult.get(0));
    assertEquals("deployPaused=true", collectResult2.get(3));
    assertEquals("never", collectResult.get(2));
    assertEquals("newinstance=true", collectResult2.get(1));
  }

  /**
   * Test {@link CdkCommand#destroy()}.
   * <p>
   * Method under test: {@link CdkCommand#destroy()}
   */
  @Test
  @DisplayName("Test destroy()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"CdkDestroy CdkCommand.destroy()"})
  void testDestroy() {
    // Arrange and Act
    CdkDestroy actualDestroyResult = CdkCommand.destroy();

    // Assert
    Stream<String> command = actualDestroyResult.getCommand();
    List<String> collectResult = command.limit(5).collect(Collectors.toList());
    assertEquals(2, collectResult.size());
    assertEquals("--force", collectResult.get(1));
    Stream<String> arguments = actualDestroyResult.getArguments();
    List<String> collectResult2 = arguments.limit(5).collect(Collectors.toList());
    assertEquals(2, collectResult2.size());
    assertEquals("-c", collectResult2.get(0));
    assertEquals("destroy", collectResult.get(0));
    assertEquals("validate=false", collectResult2.get(1));
  }
}
