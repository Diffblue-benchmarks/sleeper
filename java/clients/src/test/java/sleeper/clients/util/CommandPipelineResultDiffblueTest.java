package sleeper.clients.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class CommandPipelineResultDiffblueTest {
  /**
   * Test {@link CommandPipelineResult#CommandPipelineResult(int[])}.
   * <p>
   * Method under test: {@link CommandPipelineResult#CommandPipelineResult(int[])}
   */
  @Test
  @DisplayName("Test new CommandPipelineResult(int[])")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void CommandPipelineResult.<init>(int[])"})
  void testNewCommandPipelineResult() {
    // Arrange, Act and Assert
    assertEquals(-1, (new CommandPipelineResult(1, -1, 1, -1)).getLastExitCode());
  }

  /**
   * Test {@link CommandPipelineResult#getLastExitCode()}.
   * <ul>
   *   <li>Then return minus one.</li>
   * </ul>
   * <p>
   * Method under test: {@link CommandPipelineResult#getLastExitCode()}
   */
  @Test
  @DisplayName("Test getLastExitCode(); then return minus one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"int CommandPipelineResult.getLastExitCode()"})
  void testGetLastExitCode_thenReturnMinusOne() {
    // Arrange, Act and Assert
    assertEquals(-1, (new CommandPipelineResult(1, -1, 1, -1)).getLastExitCode());
  }

  /**
   * Test {@link CommandPipelineResult#toString()}.
   * <p>
   * Method under test: {@link CommandPipelineResult#toString()}
   */
  @Test
  @DisplayName("Test toString()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.lang.String CommandPipelineResult.toString()"})
  void testToString() {
    // Arrange, Act and Assert
    assertEquals("[1, -1, 1, -1]", (new CommandPipelineResult(1, -1, 1, -1)).toString());
  }

  /**
   * Test {@link CommandPipelineResult#equals(Object)}, and {@link CommandPipelineResult#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link CommandPipelineResult#equals(Object)}
   *   <li>{@link CommandPipelineResult#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CommandPipelineResult.equals(Object)", "int CommandPipelineResult.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    CommandPipelineResult commandPipelineResult = new CommandPipelineResult(1, -1, 1, -1);
    CommandPipelineResult commandPipelineResult2 = new CommandPipelineResult(1, -1, 1, -1);

    // Act and Assert
    assertEquals(commandPipelineResult, commandPipelineResult2);
    int expectedHashCodeResult = commandPipelineResult.hashCode();
    assertEquals(expectedHashCodeResult, commandPipelineResult2.hashCode());
  }

  /**
   * Test {@link CommandPipelineResult#equals(Object)}, and {@link CommandPipelineResult#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link CommandPipelineResult#equals(Object)}
   *   <li>{@link CommandPipelineResult#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CommandPipelineResult.equals(Object)", "int CommandPipelineResult.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    CommandPipelineResult commandPipelineResult = new CommandPipelineResult(1, -1, 1, -1);

    // Act and Assert
    assertEquals(commandPipelineResult, commandPipelineResult);
    int expectedHashCodeResult = commandPipelineResult.hashCode();
    assertEquals(expectedHashCodeResult, commandPipelineResult.hashCode());
  }

  /**
   * Test {@link CommandPipelineResult#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link CommandPipelineResult#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CommandPipelineResult.equals(Object)", "int CommandPipelineResult.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    CommandPipelineResult commandPipelineResult = new CommandPipelineResult(0, -1, 1, -1);

    // Act and Assert
    assertNotEquals(commandPipelineResult, new CommandPipelineResult(1, -1, 1, -1));
  }

  /**
   * Test {@link CommandPipelineResult#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link CommandPipelineResult#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CommandPipelineResult.equals(Object)", "int CommandPipelineResult.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new CommandPipelineResult(1, -1, 1, -1), null);
  }

  /**
   * Test {@link CommandPipelineResult#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link CommandPipelineResult#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CommandPipelineResult.equals(Object)", "int CommandPipelineResult.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new CommandPipelineResult(1, -1, 1, -1), "Different type to CommandPipelineResult");
  }
}
