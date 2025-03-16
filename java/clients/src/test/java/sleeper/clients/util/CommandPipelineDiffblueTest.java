package sleeper.clients.util;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mockStatic;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

class CommandPipelineDiffblueTest {
  /**
   * Test {@link CommandPipeline#CommandPipeline(List)}.
   * <ul>
   *   <li>Then return Commands is {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CommandPipeline#CommandPipeline(List)}
   */
  @Test
  @DisplayName("Test new CommandPipeline(List); then return Commands is ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void CommandPipeline.<init>(List)"})
  void testNewCommandPipeline_thenReturnCommandsIsArrayList() {
    // Arrange
    ArrayList<Command> commands = new ArrayList<>();
    commands.add(Command.command("commands must not be null"));

    // Act and Assert
    assertSame(commands, (new CommandPipeline(commands)).getCommands());
  }

  /**
   * Test {@link CommandPipeline#CommandPipeline(List)}.
   * <ul>
   *   <li>Then return Commands size is two.</li>
   * </ul>
   * <p>
   * Method under test: {@link CommandPipeline#CommandPipeline(List)}
   */
  @Test
  @DisplayName("Test new CommandPipeline(List); then return Commands size is two")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void CommandPipeline.<init>(List)"})
  void testNewCommandPipeline_thenReturnCommandsSizeIsTwo() {
    // Arrange
    ArrayList<Command> commands = new ArrayList<>();
    commands.add(Command.command("commands must not be null"));
    Command commandResult = Command.command("commands must not be null");
    commands.add(commandResult);

    // Act and Assert
    List<Command> commands2 = (new CommandPipeline(commands)).getCommands();
    assertEquals(2, commands2.size());
    assertSame(commandResult, commands2.get(1));
  }

  /**
   * Test {@link CommandPipeline#CommandPipeline(List)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return Commands Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link CommandPipeline#CommandPipeline(List)}
   */
  @Test
  @DisplayName("Test new CommandPipeline(List); when ArrayList(); then return Commands Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void CommandPipeline.<init>(List)"})
  void testNewCommandPipeline_whenArrayList_thenReturnCommandsEmpty() {
    // Arrange, Act and Assert
    assertTrue((new CommandPipeline(new ArrayList<>())).getCommands().isEmpty());
  }

  /**
   * Test {@link CommandPipeline#pipeline(Command[])}.
   * <ul>
   *   <li>When {@code Command}.</li>
   *   <li>Then return Commands size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link CommandPipeline#pipeline(Command[])}
   */
  @Test
  @DisplayName("Test pipeline(Command[]); when 'Command'; then return Commands size is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"CommandPipeline CommandPipeline.pipeline(Command[])"})
  void testPipeline_whenCommand_thenReturnCommandsSizeIsOne() {
    // Arrange
    Command commandResult = Command.command("Command");

    // Act and Assert
    List<Command> commands = CommandPipeline.pipeline(commandResult).getCommands();
    assertEquals(1, commands.size());
    Command getResult = commands.get(0);
    assertSame(commandResult, getResult);
    assertArrayEquals(new String[]{"Command"}, getResult.toArray());
  }

  /**
   * Test {@link CommandPipeline#getCommands()}.
   * <p>
   * Method under test: {@link CommandPipeline#getCommands()}
   */
  @Test
  @DisplayName("Test getCommands()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List CommandPipeline.getCommands()"})
  void testGetCommands() {
    // Arrange
    Command commandResult = Command.command("Command");

    // Act
    List<Command> actualCommands = CommandPipeline.pipeline(commandResult).getCommands();

    // Assert
    assertEquals(1, actualCommands.size());
    Command getResult = actualCommands.get(0);
    assertSame(commandResult, getResult);
    assertArrayEquals(new String[]{"Command"}, getResult.toArray());
  }

  /**
   * Test {@link CommandPipeline#startProcesses()}.
   * <ul>
   *   <li>Given pipeline.</li>
   *   <li>Then return Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link CommandPipeline#startProcesses()}
   */
  @Test
  @DisplayName("Test startProcesses(); given pipeline; then return Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List CommandPipeline.startProcesses()"})
  void testStartProcesses_givenPipeline_thenReturnEmpty() throws IOException {
    // Arrange, Act and Assert
    assertTrue(CommandPipeline.pipeline().startProcesses().isEmpty());
  }

  /**
   * Test {@link CommandPipeline#startProcesses()}.
   * <ul>
   *   <li>Given {@link ProcessBuilder} {@link ProcessBuilder#startPipeline(List)} return {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CommandPipeline#startProcesses()}
   */
  @Test
  @DisplayName("Test startProcesses(); given ProcessBuilder startPipeline(List) return ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List CommandPipeline.startProcesses()"})
  void testStartProcesses_givenProcessBuilderStartPipelineReturnArrayList() throws IOException {
    try (MockedStatic<ProcessBuilder> mockProcessBuilder = mockStatic(ProcessBuilder.class)) {

      // Arrange
      mockProcessBuilder.when(() -> ProcessBuilder.startPipeline(Mockito.<List<ProcessBuilder>>any()))
          .thenReturn(new ArrayList<>());
      Command commandResult = Command.command("Command");

      // Act
      List<Process> actualStartProcessesResult = CommandPipeline.pipeline(commandResult, Command.command("Command"))
          .startProcesses();

      // Assert
      mockProcessBuilder.verify(() -> ProcessBuilder.startPipeline(Mockito.<List<ProcessBuilder>>any()));
      assertTrue(actualStartProcessesResult.isEmpty());
    }
  }

  /**
   * Test {@link CommandPipeline#startProcesses()}.
   * <ul>
   *   <li>Then throw {@link IOException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CommandPipeline#startProcesses()}
   */
  @Test
  @DisplayName("Test startProcesses(); then throw IOException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List CommandPipeline.startProcesses()"})
  void testStartProcesses_thenThrowIOException() throws IOException {
    try (MockedStatic<ProcessBuilder> mockProcessBuilder = mockStatic(ProcessBuilder.class)) {

      // Arrange
      mockProcessBuilder.when(() -> ProcessBuilder.startPipeline(Mockito.<List<ProcessBuilder>>any()))
          .thenThrow(new IOException("foo"));
      Command commandResult = Command.command("Command");

      // Act and Assert
      assertThrows(IOException.class,
          () -> CommandPipeline.pipeline(commandResult, Command.command("Command")).startProcesses());
      mockProcessBuilder.verify(() -> ProcessBuilder.startPipeline(Mockito.<List<ProcessBuilder>>any()));
    }
  }

  /**
   * Test {@link CommandPipeline#startProcessesInheritIO()}.
   * <ul>
   *   <li>Given pipeline.</li>
   *   <li>Then return Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link CommandPipeline#startProcessesInheritIO()}
   */
  @Test
  @DisplayName("Test startProcessesInheritIO(); given pipeline; then return Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List CommandPipeline.startProcessesInheritIO()"})
  void testStartProcessesInheritIO_givenPipeline_thenReturnEmpty() throws IOException {
    // Arrange, Act and Assert
    assertTrue(CommandPipeline.pipeline().startProcessesInheritIO().isEmpty());
  }

  /**
   * Test {@link CommandPipeline#startProcessesInheritIO()}.
   * <ul>
   *   <li>Given {@link ProcessBuilder} {@link ProcessBuilder#startPipeline(List)} return {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CommandPipeline#startProcessesInheritIO()}
   */
  @Test
  @DisplayName("Test startProcessesInheritIO(); given ProcessBuilder startPipeline(List) return ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List CommandPipeline.startProcessesInheritIO()"})
  void testStartProcessesInheritIO_givenProcessBuilderStartPipelineReturnArrayList() throws IOException {
    try (MockedStatic<ProcessBuilder> mockProcessBuilder = mockStatic(ProcessBuilder.class)) {

      // Arrange
      mockProcessBuilder.when(() -> ProcessBuilder.startPipeline(Mockito.<List<ProcessBuilder>>any()))
          .thenReturn(new ArrayList<>());
      Command commandResult = Command.command("Command");

      // Act
      List<Process> actualStartProcessesInheritIOResult = CommandPipeline
          .pipeline(commandResult, Command.command("Command"))
          .startProcessesInheritIO();

      // Assert
      mockProcessBuilder.verify(() -> ProcessBuilder.startPipeline(Mockito.<List<ProcessBuilder>>any()));
      assertTrue(actualStartProcessesInheritIOResult.isEmpty());
    }
  }

  /**
   * Test {@link CommandPipeline#startProcessesInheritIO()}.
   * <ul>
   *   <li>Then throw {@link IOException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CommandPipeline#startProcessesInheritIO()}
   */
  @Test
  @DisplayName("Test startProcessesInheritIO(); then throw IOException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List CommandPipeline.startProcessesInheritIO()"})
  void testStartProcessesInheritIO_thenThrowIOException() throws IOException {
    try (MockedStatic<ProcessBuilder> mockProcessBuilder = mockStatic(ProcessBuilder.class)) {

      // Arrange
      mockProcessBuilder.when(() -> ProcessBuilder.startPipeline(Mockito.<List<ProcessBuilder>>any()))
          .thenThrow(new IOException("foo"));
      Command commandResult = Command.command("Command");

      // Act and Assert
      assertThrows(IOException.class,
          () -> CommandPipeline.pipeline(commandResult, Command.command("Command")).startProcessesInheritIO());
      mockProcessBuilder.verify(() -> ProcessBuilder.startPipeline(Mockito.<List<ProcessBuilder>>any()));
    }
  }

  /**
   * Test {@link CommandPipeline#equals(Object)}, and {@link CommandPipeline#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link CommandPipeline#equals(Object)}
   *   <li>{@link CommandPipeline#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CommandPipeline.equals(Object)", "int CommandPipeline.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    CommandPipeline pipelineResult = CommandPipeline.pipeline(Command.command("Command"));
    CommandPipeline pipelineResult2 = CommandPipeline.pipeline(Command.command("Command"));

    // Act and Assert
    assertEquals(pipelineResult, pipelineResult2);
    int expectedHashCodeResult = pipelineResult.hashCode();
    assertEquals(expectedHashCodeResult, pipelineResult2.hashCode());
  }

  /**
   * Test {@link CommandPipeline#equals(Object)}, and {@link CommandPipeline#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link CommandPipeline#equals(Object)}
   *   <li>{@link CommandPipeline#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CommandPipeline.equals(Object)", "int CommandPipeline.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    CommandPipeline pipelineResult = CommandPipeline.pipeline(Command.command("Command"));

    // Act and Assert
    assertEquals(pipelineResult, pipelineResult);
    int expectedHashCodeResult = pipelineResult.hashCode();
    assertEquals(expectedHashCodeResult, pipelineResult.hashCode());
  }

  /**
   * Test {@link CommandPipeline#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link CommandPipeline#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CommandPipeline.equals(Object)", "int CommandPipeline.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(CommandPipeline.pipeline(Command.command("Command")), null);
  }

  /**
   * Test {@link CommandPipeline#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link CommandPipeline#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CommandPipeline.equals(Object)", "int CommandPipeline.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(CommandPipeline.pipeline(Command.command("Command")), "Different type to CommandPipeline");
  }

  /**
   * Test {@link CommandPipeline#toString()}.
   * <ul>
   *   <li>Given command {@code Command}.</li>
   *   <li>Then return {@code [Command]}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CommandPipeline#toString()}
   */
  @Test
  @DisplayName("Test toString(); given command 'Command'; then return '[Command]'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String CommandPipeline.toString()"})
  void testToString_givenCommandCommand_thenReturnCommand() {
    // Arrange, Act and Assert
    assertEquals("[Command]", CommandPipeline.pipeline(Command.command("Command")).toString());
  }

  /**
   * Test {@link CommandPipeline#toString()}.
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@code foo} is {@code foo}.</li>
   *   <li>Then return {@code [Command]{foo=foo}}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CommandPipeline#toString()}
   */
  @Test
  @DisplayName("Test toString(); given HashMap() 'foo' is 'foo'; then return '[Command]{foo=foo}'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String CommandPipeline.toString()"})
  void testToString_givenHashMapFooIsFoo_thenReturnCommandFooFoo() {
    // Arrange
    HashMap<String, String> envVars = new HashMap<>();
    envVars.put("foo", "foo");

    // Act and Assert
    assertEquals("[Command]{foo=foo}", CommandPipeline.pipeline(Command.envAndCommand(envVars, "Command")).toString());
  }

  /**
   * Test {@link CommandPipeline#toString()}.
   * <ul>
   *   <li>Given pipeline.</li>
   *   <li>Then return {@code CommandPipeline{commands=[]}}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CommandPipeline#toString()}
   */
  @Test
  @DisplayName("Test toString(); given pipeline; then return 'CommandPipeline{commands=[]}'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String CommandPipeline.toString()"})
  void testToString_givenPipeline_thenReturnCommandPipelineCommands() {
    // Arrange, Act and Assert
    assertEquals("CommandPipeline{commands=[]}", CommandPipeline.pipeline().toString());
  }

  /**
   * Test {@link CommandPipeline#toString()}.
   * <ul>
   *   <li>Then return {@code CommandPipeline{commands=[[Command], [Command]]}}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CommandPipeline#toString()}
   */
  @Test
  @DisplayName("Test toString(); then return 'CommandPipeline{commands=[[Command], [Command]]}'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String CommandPipeline.toString()"})
  void testToString_thenReturnCommandPipelineCommandsCommandCommand() {
    // Arrange
    Command commandResult = Command.command("Command");

    // Act and Assert
    assertEquals("CommandPipeline{commands=[[Command], [Command]]}",
        CommandPipeline.pipeline(commandResult, Command.command("Command")).toString());
  }
}
