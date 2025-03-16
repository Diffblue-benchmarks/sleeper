package sleeper.clients.admin;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Optional;
import java.util.function.Supplier;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.clients.admin.properties.AdminClientPropertiesStore;
import sleeper.clients.admin.properties.AdminClientPropertiesStore.CouldNotLoadInstanceProperties;
import sleeper.clients.util.console.ConsoleInput;
import sleeper.clients.util.console.ConsoleOutput;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.table.TableProperties;

class AdminCommonPromptsDiffblueTest {
  /**
   * Test {@link AdminCommonPrompts#confirmReturnToMainScreen(ConsoleOutput, ConsoleInput)}.
   * <ul>
   *   <li>Then calls {@link ConsoleInput#waitForLine()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AdminCommonPrompts#confirmReturnToMainScreen(ConsoleOutput, ConsoleInput)}
   */
  @Test
  @DisplayName("Test confirmReturnToMainScreen(ConsoleOutput, ConsoleInput); then calls waitForLine()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AdminCommonPrompts.confirmReturnToMainScreen(ConsoleOutput, ConsoleInput)"})
  void testConfirmReturnToMainScreen_thenCallsWaitForLine() {
    // Arrange
    ConsoleOutput out = mock(ConsoleOutput.class);
    doNothing().when(out).println(Mockito.<String>any());
    ConsoleInput in = mock(ConsoleInput.class);
    doNothing().when(in).waitForLine();

    // Act
    AdminCommonPrompts.confirmReturnToMainScreen(out, in);

    // Assert
    verify(in).waitForLine();
    verify(out, atLeast(1)).println(Mockito.<String>any());
  }

  /**
   * Test {@link AdminCommonPrompts#tryLoadInstanceProperties(ConsoleOutput, ConsoleInput, Supplier)} with {@code out}, {@code in}, {@code loadInstanceProperties}.
   * <ul>
   *   <li>Then return Present.</li>
   * </ul>
   * <p>
   * Method under test: {@link AdminCommonPrompts#tryLoadInstanceProperties(ConsoleOutput, ConsoleInput, Supplier)}
   */
  @Test
  @DisplayName("Test tryLoadInstanceProperties(ConsoleOutput, ConsoleInput, Supplier) with 'out', 'in', 'loadInstanceProperties'; then return Present")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Optional AdminCommonPrompts.tryLoadInstanceProperties(ConsoleOutput, ConsoleInput, Supplier)"})
  void testTryLoadInstancePropertiesWithOutInLoadInstanceProperties_thenReturnPresent() {
    // Arrange
    ConsoleOutput out = new ConsoleOutput(new PrintStream(new ByteArrayOutputStream(1)));
    ConsoleInput in = new ConsoleInput(null);
    Supplier<InstanceProperties> loadInstanceProperties = mock(Supplier.class);
    InstanceProperties instanceProperties = new InstanceProperties();
    when(loadInstanceProperties.get()).thenReturn(instanceProperties);

    // Act
    Optional<InstanceProperties> actualTryLoadInstancePropertiesResult = AdminCommonPrompts
        .tryLoadInstanceProperties(out, in, loadInstanceProperties);

    // Assert
    verify(loadInstanceProperties).get();
    assertTrue(actualTryLoadInstancePropertiesResult.isPresent());
    assertSame(instanceProperties, actualTryLoadInstancePropertiesResult.get());
  }

  /**
   * Test {@link AdminCommonPrompts#tryLoadTableProperties(ConsoleOutput, ConsoleInput, Supplier)} with {@code out}, {@code in}, {@code loadTableProperties}.
   * <p>
   * Method under test: {@link AdminCommonPrompts#tryLoadTableProperties(ConsoleOutput, ConsoleInput, Supplier)}
   */
  @Test
  @DisplayName("Test tryLoadTableProperties(ConsoleOutput, ConsoleInput, Supplier) with 'out', 'in', 'loadTableProperties'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Optional AdminCommonPrompts.tryLoadTableProperties(ConsoleOutput, ConsoleInput, Supplier)"})
  void testTryLoadTablePropertiesWithOutInLoadTableProperties() {
    // Arrange
    ConsoleOutput out = new ConsoleOutput(new PrintStream(new ByteArrayOutputStream(1)));
    ConsoleInput in = new ConsoleInput(null);
    Supplier<TableProperties> loadTableProperties = mock(Supplier.class);
    when(loadTableProperties.get()).thenThrow(new CouldNotLoadInstanceProperties("42", new Throwable()));

    // Act and Assert
    assertThrows(CouldNotLoadInstanceProperties.class,
        () -> AdminCommonPrompts.tryLoadTableProperties(out, in, loadTableProperties));
    verify(loadTableProperties).get();
  }

  /**
   * Test {@link AdminCommonPrompts#tryLoadTableProperties(ConsoleOutput, ConsoleInput, Supplier)} with {@code out}, {@code in}, {@code loadTableProperties}.
   * <ul>
   *   <li>Then return Present.</li>
   * </ul>
   * <p>
   * Method under test: {@link AdminCommonPrompts#tryLoadTableProperties(ConsoleOutput, ConsoleInput, Supplier)}
   */
  @Test
  @DisplayName("Test tryLoadTableProperties(ConsoleOutput, ConsoleInput, Supplier) with 'out', 'in', 'loadTableProperties'; then return Present")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Optional AdminCommonPrompts.tryLoadTableProperties(ConsoleOutput, ConsoleInput, Supplier)"})
  void testTryLoadTablePropertiesWithOutInLoadTableProperties_thenReturnPresent() {
    // Arrange
    ConsoleOutput out = new ConsoleOutput(new PrintStream(new ByteArrayOutputStream(1)));
    ConsoleInput in = new ConsoleInput(null);
    Supplier<TableProperties> loadTableProperties = mock(Supplier.class);
    TableProperties tableProperties = new TableProperties(new InstanceProperties());
    when(loadTableProperties.get()).thenReturn(tableProperties);

    // Act
    Optional<TableProperties> actualTryLoadTablePropertiesResult = AdminCommonPrompts.tryLoadTableProperties(out, in,
        loadTableProperties);

    // Assert
    verify(loadTableProperties).get();
    assertTrue(actualTryLoadTablePropertiesResult.isPresent());
    assertSame(tableProperties, actualTryLoadTablePropertiesResult.get());
  }
}
