package sleeper.clients.admin;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.nio.file.Paths;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.clients.admin.properties.AdminClientPropertiesStore;
import sleeper.clients.deploy.UploadDockerImages;
import sleeper.clients.deploy.UploadDockerImages.Builder;
import sleeper.clients.deploy.UploadDockerImages.CopyFile;
import sleeper.clients.util.InMemoryEcrRepositories;
import sleeper.clients.util.cdk.InvokeCdkForInstance;
import sleeper.clients.util.console.ConsoleInput;
import sleeper.clients.util.console.ConsoleOutput;
import sleeper.clients.util.console.UserExitedException;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.table.TableProperties;

class TableSelectHelperDiffblueTest {
  /**
   * Test {@link TableSelectHelper#chooseTableOrReturnToMain(InstanceProperties)} with {@code properties}.
   * <ul>
   *   <li>Given {@link ConsoleInput} {@link ConsoleInput#promptLine(String)} return {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TableSelectHelper#chooseTableOrReturnToMain(InstanceProperties)}
   */
  @Test
  @DisplayName("Test chooseTableOrReturnToMain(InstanceProperties) with 'properties'; given ConsoleInput promptLine(String) return '42'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Optional TableSelectHelper.chooseTableOrReturnToMain(InstanceProperties)"})
  void testChooseTableOrReturnToMainWithProperties_givenConsoleInputPromptLineReturn42() throws UserExitedException {
    // Arrange
    InvokeCdkForInstance.builder()
        .jarsDirectory(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .propertiesFile(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .version("1.0.2")
        .build();
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    Builder copyFileResult = UploadDockerImages.builder()
        .baseDockerDirectory(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .copyFile(mock(CopyFile.class));
    copyFileResult.ecrClient(new InMemoryEcrRepositories())
        .jarsDirectory(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .build();
    ConsoleOutput out = mock(ConsoleOutput.class);
    doNothing().when(out).clearScreen(Mockito.<String>any());
    doNothing().when(out).printf(Mockito.<String>any(), isA(Object[].class));
    doNothing().when(out).println();
    doNothing().when(out).println(Mockito.<String>any());
    ConsoleInput in = mock(ConsoleInput.class);
    when(in.promptLine(Mockito.<String>any())).thenReturn("42");
    AdminClientPropertiesStore store = mock(AdminClientPropertiesStore.class);
    TableProperties tableProperties = new TableProperties(new InstanceProperties());
    when(store.loadTableProperties(Mockito.<InstanceProperties>any(), Mockito.<String>any()))
        .thenReturn(tableProperties);
    TableSelectHelper tableSelectHelper = new TableSelectHelper(out, in, store);

    // Act
    Optional<TableProperties> actualChooseTableOrReturnToMainResult = tableSelectHelper
        .chooseTableOrReturnToMain(new InstanceProperties());

    // Assert
    verify(store).loadTableProperties(isA(InstanceProperties.class), eq("42"));
    verify(in).promptLine(eq("Input: "));
    verify(out).clearScreen(eq(""));
    verify(out).printf(eq("[%s] %s%n"), isA(Object[].class));
    verify(out).println();
    verify(out, atLeast(1)).println(Mockito.<String>any());
    assertTrue(actualChooseTableOrReturnToMainResult.isPresent());
    assertSame(tableProperties, actualChooseTableOrReturnToMainResult.get());
  }

  /**
   * Test {@link TableSelectHelper#chooseTableOrReturnToMain(InstanceProperties)} with {@code properties}.
   * <ul>
   *   <li>Then return Present.</li>
   * </ul>
   * <p>
   * Method under test: {@link TableSelectHelper#chooseTableOrReturnToMain(InstanceProperties)}
   */
  @Test
  @DisplayName("Test chooseTableOrReturnToMain(InstanceProperties) with 'properties'; then return Present")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Optional TableSelectHelper.chooseTableOrReturnToMain(InstanceProperties)"})
  void testChooseTableOrReturnToMainWithProperties_thenReturnPresent() throws UserExitedException {
    // Arrange
    InvokeCdkForInstance.builder()
        .jarsDirectory(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .propertiesFile(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .version("1.0.2")
        .build();
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    Builder copyFileResult = UploadDockerImages.builder()
        .baseDockerDirectory(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .copyFile(mock(CopyFile.class));
    copyFileResult.ecrClient(new InMemoryEcrRepositories())
        .jarsDirectory(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .build();
    ConsoleOutput out = mock(ConsoleOutput.class);
    doNothing().when(out).clearScreen(Mockito.<String>any());
    doNothing().when(out).printf(Mockito.<String>any(), isA(Object[].class));
    doNothing().when(out).println();
    doNothing().when(out).println(Mockito.<String>any());
    ConsoleInput in = mock(ConsoleInput.class);
    when(in.promptLine(Mockito.<String>any())).thenReturn("Prompt Line");
    AdminClientPropertiesStore store = mock(AdminClientPropertiesStore.class);
    TableProperties tableProperties = new TableProperties(new InstanceProperties());
    when(store.loadTableProperties(Mockito.<InstanceProperties>any(), Mockito.<String>any()))
        .thenReturn(tableProperties);
    TableSelectHelper tableSelectHelper = new TableSelectHelper(out, in, store);

    // Act
    Optional<TableProperties> actualChooseTableOrReturnToMainResult = tableSelectHelper
        .chooseTableOrReturnToMain(new InstanceProperties());

    // Assert
    verify(store).loadTableProperties(isA(InstanceProperties.class), eq("Prompt Line"));
    verify(in).promptLine(eq("Input: "));
    verify(out).clearScreen(eq(""));
    verify(out).printf(eq("[%s] %s%n"), isA(Object[].class));
    verify(out).println();
    verify(out, atLeast(1)).println(Mockito.<String>any());
    assertTrue(actualChooseTableOrReturnToMainResult.isPresent());
    assertSame(tableProperties, actualChooseTableOrReturnToMainResult.get());
  }
}
