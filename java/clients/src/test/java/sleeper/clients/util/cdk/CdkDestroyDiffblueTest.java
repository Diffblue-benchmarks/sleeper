package sleeper.clients.util.cdk;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class CdkDestroyDiffblueTest {
  /**
   * Test {@link CdkDestroy#getCommand()}.
   * <p>
   * Method under test: {@link CdkDestroy#getCommand()}
   */
  @Test
  @DisplayName("Test getCommand()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream CdkDestroy.getCommand()"})
  void testGetCommand() {
    // Arrange and Act
    Stream<String> actualCommand = (new CdkDestroy()).getCommand();

    // Assert
    List<String> collectResult = actualCommand.limit(5).collect(Collectors.toList());
    assertEquals(2, collectResult.size());
    assertEquals("--force", collectResult.get(1));
    assertEquals("destroy", collectResult.get(0));
  }

  /**
   * Test {@link CdkDestroy#getArguments()}.
   * <p>
   * Method under test: {@link CdkDestroy#getArguments()}
   */
  @Test
  @DisplayName("Test getArguments()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream CdkDestroy.getArguments()"})
  void testGetArguments() {
    // Arrange and Act
    Stream<String> actualArguments = (new CdkDestroy()).getArguments();

    // Assert
    List<String> collectResult = actualArguments.limit(5).collect(Collectors.toList());
    assertEquals(2, collectResult.size());
    assertEquals("-c", collectResult.get(0));
    assertEquals("validate=false", collectResult.get(1));
  }

  /**
   * Test new {@link CdkDestroy} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link CdkDestroy}
   */
  @Test
  @DisplayName("Test new CdkDestroy (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void CdkDestroy.<init>()"})
  void testNewCdkDestroy() {
    // Arrange and Act
    CdkDestroy actualCdkDestroy = new CdkDestroy();

    // Assert
    Stream<String> command = actualCdkDestroy.getCommand();
    List<String> collectResult = command.limit(5).collect(Collectors.toList());
    assertEquals(2, collectResult.size());
    assertEquals("--force", collectResult.get(1));
    Stream<String> arguments = actualCdkDestroy.getArguments();
    List<String> collectResult2 = arguments.limit(5).collect(Collectors.toList());
    assertEquals(2, collectResult2.size());
    assertEquals("-c", collectResult2.get(0));
    assertEquals("destroy", collectResult.get(0));
    assertEquals("validate=false", collectResult2.get(1));
  }
}
