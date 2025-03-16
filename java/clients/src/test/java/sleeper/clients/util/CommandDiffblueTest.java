package sleeper.clients.util;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class CommandDiffblueTest {
  /**
   * Test {@link Command#envAndCommand(Map, String[])}.
   * <p>
   * Method under test: {@link Command#envAndCommand(Map, String[])}
   */
  @Test
  @DisplayName("Test envAndCommand(Map, String[])")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Command Command.envAndCommand(Map, String[])"})
  void testEnvAndCommand() {
    // Arrange
    String[] command = new String[]{"Command"};

    // Act and Assert
    assertSame(command, Command.envAndCommand(new HashMap<>(), command).toArray());
  }

  /**
   * Test {@link Command#command(String[])}.
   * <p>
   * Method under test: {@link Command#command(String[])}
   */
  @Test
  @DisplayName("Test command(String[])")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Command Command.command(String[])"})
  void testCommand() {
    // Arrange
    String[] command = new String[]{"Command"};

    // Act and Assert
    assertSame(command, Command.command(command).toArray());
  }

  /**
   * Test {@link Command#equals(Object)}, and {@link Command#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Command#equals(Object)}
   *   <li>{@link Command#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean Command.equals(Object)", "int Command.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    Command commandResult = Command.command("Command");
    Command commandResult2 = Command.command("Command");

    // Act and Assert
    assertEquals(commandResult, commandResult2);
    int expectedHashCodeResult = commandResult.hashCode();
    assertEquals(expectedHashCodeResult, commandResult2.hashCode());
  }

  /**
   * Test {@link Command#equals(Object)}, and {@link Command#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Command#equals(Object)}
   *   <li>{@link Command#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean Command.equals(Object)", "int Command.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    Command commandResult = Command.command("Command");

    // Act and Assert
    assertEquals(commandResult, commandResult);
    int expectedHashCodeResult = commandResult.hashCode();
    assertEquals(expectedHashCodeResult, commandResult.hashCode());
  }

  /**
   * Test {@link Command#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link Command#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean Command.equals(Object)", "int Command.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    Command commandResult = Command.command("java.lang.String[]");

    // Act and Assert
    assertNotEquals(commandResult, Command.command("Command"));
  }

  /**
   * Test {@link Command#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link Command#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean Command.equals(Object)", "int Command.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    HashMap<String, String> envVars = new HashMap<>();
    envVars.put("foo", "foo");
    Command envAndCommandResult = Command.envAndCommand(envVars, "Command");

    // Act and Assert
    assertNotEquals(envAndCommandResult, Command.command("Command"));
  }

  /**
   * Test {@link Command#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link Command#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean Command.equals(Object)", "int Command.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(Command.command("Command"), null);
  }

  /**
   * Test {@link Command#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link Command#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean Command.equals(Object)", "int Command.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(Command.command("Command"), "Different type to Command");
  }

  /**
   * Test {@link Command#toString()}.
   * <ul>
   *   <li>Given command {@code Command}.</li>
   *   <li>Then return {@code [Command]}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Command#toString()}
   */
  @Test
  @DisplayName("Test toString(); given command 'Command'; then return '[Command]'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String Command.toString()"})
  void testToString_givenCommandCommand_thenReturnCommand() {
    // Arrange, Act and Assert
    assertEquals("[Command]", Command.command("Command").toString());
  }

  /**
   * Test {@link Command#toString()}.
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@code foo} is {@code foo}.</li>
   *   <li>Then return {@code [Command]{foo=foo}}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Command#toString()}
   */
  @Test
  @DisplayName("Test toString(); given HashMap() 'foo' is 'foo'; then return '[Command]{foo=foo}'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String Command.toString()"})
  void testToString_givenHashMapFooIsFoo_thenReturnCommandFooFoo() {
    // Arrange
    HashMap<String, String> envVars = new HashMap<>();
    envVars.put("foo", "foo");

    // Act and Assert
    assertEquals("[Command]{foo=foo}", Command.envAndCommand(envVars, "Command").toString());
  }

  /**
   * Test {@link Command#toArray()}.
   * <p>
   * Method under test: {@link Command#toArray()}
   */
  @Test
  @DisplayName("Test toArray()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String[] Command.toArray()"})
  void testToArray() {
    // Arrange, Act and Assert
    assertArrayEquals(new String[]{"Command"}, Command.command("Command").toArray());
  }

  /**
   * Test {@link Command#toProcessBuilder()}.
   * <p>
   * Method under test: {@link Command#toProcessBuilder()}
   */
  @Test
  @DisplayName("Test toProcessBuilder()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"ProcessBuilder Command.toProcessBuilder()"})
  void testToProcessBuilder() {
    // Arrange and Act
    ProcessBuilder actualToProcessBuilderResult = Command.command("Command").toProcessBuilder();

    // Assert
    List<String> commandResult = actualToProcessBuilderResult.command();
    assertEquals(1, commandResult.size());
    assertEquals("Command", commandResult.get(0));
    assertNull(actualToProcessBuilderResult.directory());
    assertFalse(actualToProcessBuilderResult.redirectErrorStream());
  }

  /**
   * Test {@link Command#toProcessBuilderInheritIO(int, int)}.
   * <ul>
   *   <li>When one.</li>
   * </ul>
   * <p>
   * Method under test: {@link Command#toProcessBuilderInheritIO(int, int)}
   */
  @Test
  @DisplayName("Test toProcessBuilderInheritIO(int, int); when one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"ProcessBuilder Command.toProcessBuilderInheritIO(int, int)"})
  void testToProcessBuilderInheritIO_whenOne() {
    // Arrange and Act
    ProcessBuilder actualToProcessBuilderInheritIOResult = Command.command("Command").toProcessBuilderInheritIO(1, 2);

    // Assert
    List<String> commandResult = actualToProcessBuilderInheritIOResult.command();
    assertEquals(1, commandResult.size());
    assertEquals("Command", commandResult.get(0));
    assertNull(actualToProcessBuilderInheritIOResult.directory());
    assertFalse(actualToProcessBuilderInheritIOResult.redirectErrorStream());
  }

  /**
   * Test {@link Command#toProcessBuilderInheritIO(int, int)}.
   * <ul>
   *   <li>When zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link Command#toProcessBuilderInheritIO(int, int)}
   */
  @Test
  @DisplayName("Test toProcessBuilderInheritIO(int, int); when zero")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"ProcessBuilder Command.toProcessBuilderInheritIO(int, int)"})
  void testToProcessBuilderInheritIO_whenZero() {
    // Arrange and Act
    ProcessBuilder actualToProcessBuilderInheritIOResult = Command.command("Command").toProcessBuilderInheritIO(0, 2);

    // Assert
    List<String> commandResult = actualToProcessBuilderInheritIOResult.command();
    assertEquals(1, commandResult.size());
    assertEquals("Command", commandResult.get(0));
    assertNull(actualToProcessBuilderInheritIOResult.directory());
    assertFalse(actualToProcessBuilderInheritIOResult.redirectErrorStream());
  }
}
