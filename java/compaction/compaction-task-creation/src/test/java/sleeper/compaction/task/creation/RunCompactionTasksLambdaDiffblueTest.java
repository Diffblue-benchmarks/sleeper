package sleeper.compaction.task.creation;

import static org.junit.jupiter.api.Assertions.assertThrows;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class RunCompactionTasksLambdaDiffblueTest {
  /**
   * Test new {@link RunCompactionTasksLambda} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link RunCompactionTasksLambda}
   */
  @Test
  @DisplayName("Test new RunCompactionTasksLambda (default constructor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void RunCompactionTasksLambda.<init>()"})
  void testNewRunCompactionTasksLambda() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> new RunCompactionTasksLambda());
  }
}
