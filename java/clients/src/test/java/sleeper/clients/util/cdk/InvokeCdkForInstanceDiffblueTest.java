package sleeper.clients.util.cdk;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.IOException;
import java.nio.file.Paths;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.clients.util.CommandRunner;
import sleeper.clients.util.cdk.InvokeCdkForInstance.Type;
import sleeper.core.properties.instance.InstanceProperties;

class InvokeCdkForInstanceDiffblueTest {
  /**
   * Test {@link InvokeCdkForInstance#invokeInferringType(InstanceProperties, CdkCommand, CommandRunner)} with {@code instanceProperties}, {@code cdkCommand}, {@code runCommand}.
   * <p>
   * Method under test: {@link InvokeCdkForInstance#invokeInferringType(InstanceProperties, CdkCommand, CommandRunner)}
   */
  @Test
  @DisplayName("Test invokeInferringType(InstanceProperties, CdkCommand, CommandRunner) with 'instanceProperties', 'cdkCommand', 'runCommand'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void InvokeCdkForInstance.invokeInferringType(InstanceProperties, CdkCommand, CommandRunner)"})
  void testInvokeInferringTypeWithInstancePropertiesCdkCommandRunCommand() throws IOException, InterruptedException {
    // Arrange
    InvokeCdkForInstance buildResult = InvokeCdkForInstance.builder()
        .jarsDirectory(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .propertiesFile(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .version("1.0.2")
        .build();
    InstanceProperties instanceProperties = new InstanceProperties();
    CdkDestroy cdkCommand = CdkCommand.destroy();
    CommandRunner runCommand = mock(CommandRunner.class);
    when(runCommand.run(isA(String[].class))).thenThrow(new CdkFailedException(2));

    // Act and Assert
    assertThrows(CdkFailedException.class,
        () -> buildResult.invokeInferringType(instanceProperties, cdkCommand, runCommand));
    verify(runCommand).run(isA(String[].class));
  }

  /**
   * Test {@link InvokeCdkForInstance#invokeInferringType(InstanceProperties, CdkCommand, CommandRunner)} with {@code instanceProperties}, {@code cdkCommand}, {@code runCommand}.
   * <ul>
   *   <li>Given one.</li>
   * </ul>
   * <p>
   * Method under test: {@link InvokeCdkForInstance#invokeInferringType(InstanceProperties, CdkCommand, CommandRunner)}
   */
  @Test
  @DisplayName("Test invokeInferringType(InstanceProperties, CdkCommand, CommandRunner) with 'instanceProperties', 'cdkCommand', 'runCommand'; given one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void InvokeCdkForInstance.invokeInferringType(InstanceProperties, CdkCommand, CommandRunner)"})
  void testInvokeInferringTypeWithInstancePropertiesCdkCommandRunCommand_givenOne()
      throws IOException, InterruptedException {
    // Arrange
    InvokeCdkForInstance buildResult = InvokeCdkForInstance.builder()
        .jarsDirectory(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .propertiesFile(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .version("1.0.2")
        .build();
    InstanceProperties instanceProperties = new InstanceProperties();
    CdkDestroy cdkCommand = CdkCommand.destroy();
    CommandRunner runCommand = mock(CommandRunner.class);
    when(runCommand.run(isA(String[].class))).thenReturn(1);

    // Act and Assert
    assertThrows(CdkFailedException.class,
        () -> buildResult.invokeInferringType(instanceProperties, cdkCommand, runCommand));
    verify(runCommand).run(isA(String[].class));
  }

  /**
   * Test {@link InvokeCdkForInstance#invokeInferringType(InstanceProperties, CdkCommand, CommandRunner)} with {@code instanceProperties}, {@code cdkCommand}, {@code runCommand}.
   * <ul>
   *   <li>Given zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link InvokeCdkForInstance#invokeInferringType(InstanceProperties, CdkCommand, CommandRunner)}
   */
  @Test
  @DisplayName("Test invokeInferringType(InstanceProperties, CdkCommand, CommandRunner) with 'instanceProperties', 'cdkCommand', 'runCommand'; given zero")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void InvokeCdkForInstance.invokeInferringType(InstanceProperties, CdkCommand, CommandRunner)"})
  void testInvokeInferringTypeWithInstancePropertiesCdkCommandRunCommand_givenZero()
      throws IOException, InterruptedException {
    // Arrange
    InvokeCdkForInstance buildResult = InvokeCdkForInstance.builder()
        .jarsDirectory(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .propertiesFile(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .version("1.0.2")
        .build();
    InstanceProperties instanceProperties = new InstanceProperties();
    CdkDestroy cdkCommand = CdkCommand.destroy();
    CommandRunner runCommand = mock(CommandRunner.class);
    when(runCommand.run(isA(String[].class))).thenReturn(0);

    // Act
    buildResult.invokeInferringType(instanceProperties, cdkCommand, runCommand);

    // Assert
    verify(runCommand).run(isA(String[].class));
  }

  /**
   * Test {@link InvokeCdkForInstance#invoke(Type, CdkCommand, CommandRunner)} with {@code instanceType}, {@code cdkCommand}, {@code runCommand}.
   * <p>
   * Method under test: {@link InvokeCdkForInstance#invoke(Type, CdkCommand, CommandRunner)}
   */
  @Test
  @DisplayName("Test invoke(Type, CdkCommand, CommandRunner) with 'instanceType', 'cdkCommand', 'runCommand'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void InvokeCdkForInstance.invoke(Type, CdkCommand, CommandRunner)"})
  void testInvokeWithInstanceTypeCdkCommandRunCommand() throws IOException, InterruptedException {
    // Arrange
    InvokeCdkForInstance buildResult = InvokeCdkForInstance.builder()
        .jarsDirectory(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .propertiesFile(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .version("1.0.2")
        .build();
    CdkDestroy cdkCommand = CdkCommand.destroy();
    CommandRunner runCommand = mock(CommandRunner.class);
    when(runCommand.run(isA(String[].class))).thenThrow(new CdkFailedException(2));

    // Act and Assert
    assertThrows(CdkFailedException.class, () -> buildResult.invoke(Type.STANDARD, cdkCommand, runCommand));
    verify(runCommand).run(isA(String[].class));
  }

  /**
   * Test {@link InvokeCdkForInstance#invoke(Type, CdkCommand, CommandRunner)} with {@code instanceType}, {@code cdkCommand}, {@code runCommand}.
   * <ul>
   *   <li>Given one.</li>
   * </ul>
   * <p>
   * Method under test: {@link InvokeCdkForInstance#invoke(Type, CdkCommand, CommandRunner)}
   */
  @Test
  @DisplayName("Test invoke(Type, CdkCommand, CommandRunner) with 'instanceType', 'cdkCommand', 'runCommand'; given one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void InvokeCdkForInstance.invoke(Type, CdkCommand, CommandRunner)"})
  void testInvokeWithInstanceTypeCdkCommandRunCommand_givenOne() throws IOException, InterruptedException {
    // Arrange
    InvokeCdkForInstance buildResult = InvokeCdkForInstance.builder()
        .jarsDirectory(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .propertiesFile(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .version("1.0.2")
        .build();
    CdkDestroy cdkCommand = CdkCommand.destroy();
    CommandRunner runCommand = mock(CommandRunner.class);
    when(runCommand.run(isA(String[].class))).thenReturn(1);

    // Act and Assert
    assertThrows(CdkFailedException.class, () -> buildResult.invoke(Type.STANDARD, cdkCommand, runCommand));
    verify(runCommand).run(isA(String[].class));
  }

  /**
   * Test {@link InvokeCdkForInstance#invoke(Type, CdkCommand, CommandRunner)} with {@code instanceType}, {@code cdkCommand}, {@code runCommand}.
   * <ul>
   *   <li>Given one.</li>
   *   <li>When {@code SYSTEM_TEST}.</li>
   * </ul>
   * <p>
   * Method under test: {@link InvokeCdkForInstance#invoke(Type, CdkCommand, CommandRunner)}
   */
  @Test
  @DisplayName("Test invoke(Type, CdkCommand, CommandRunner) with 'instanceType', 'cdkCommand', 'runCommand'; given one; when 'SYSTEM_TEST'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void InvokeCdkForInstance.invoke(Type, CdkCommand, CommandRunner)"})
  void testInvokeWithInstanceTypeCdkCommandRunCommand_givenOne_whenSystemTest()
      throws IOException, InterruptedException {
    // Arrange
    InvokeCdkForInstance buildResult = InvokeCdkForInstance.builder()
        .jarsDirectory(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .propertiesFile(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .version("1.0.2")
        .build();
    CdkDestroy cdkCommand = CdkCommand.destroy();
    CommandRunner runCommand = mock(CommandRunner.class);
    when(runCommand.run(isA(String[].class))).thenReturn(1);

    // Act and Assert
    assertThrows(CdkFailedException.class, () -> buildResult.invoke(Type.SYSTEM_TEST, cdkCommand, runCommand));
    verify(runCommand).run(isA(String[].class));
  }

  /**
   * Test {@link InvokeCdkForInstance#invoke(Type, CdkCommand, CommandRunner)} with {@code instanceType}, {@code cdkCommand}, {@code runCommand}.
   * <ul>
   *   <li>Given zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link InvokeCdkForInstance#invoke(Type, CdkCommand, CommandRunner)}
   */
  @Test
  @DisplayName("Test invoke(Type, CdkCommand, CommandRunner) with 'instanceType', 'cdkCommand', 'runCommand'; given zero")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void InvokeCdkForInstance.invoke(Type, CdkCommand, CommandRunner)"})
  void testInvokeWithInstanceTypeCdkCommandRunCommand_givenZero() throws IOException, InterruptedException {
    // Arrange
    InvokeCdkForInstance buildResult = InvokeCdkForInstance.builder()
        .jarsDirectory(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .propertiesFile(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .version("1.0.2")
        .build();
    CdkDestroy cdkCommand = CdkCommand.destroy();
    CommandRunner runCommand = mock(CommandRunner.class);
    when(runCommand.run(isA(String[].class))).thenReturn(0);

    // Act
    buildResult.invoke(Type.STANDARD, cdkCommand, runCommand);

    // Assert
    verify(runCommand).run(isA(String[].class));
  }
}
