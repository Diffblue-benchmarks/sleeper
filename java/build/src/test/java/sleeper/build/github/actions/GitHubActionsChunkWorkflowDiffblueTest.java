package sleeper.build.github.actions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.build.github.actions.GitHubActionsChunkWorkflow.Builder;

class GitHubActionsChunkWorkflowDiffblueTest {
  /**
   * Test Builder {@link Builder#build()}.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Builder#build()}
   *   <li>{@link Builder#chunkId(String)}
   *   <li>{@link Builder#name(String)}
   *   <li>{@link Builder#onTriggerPaths(List)}
   *   <li>{@link Builder#usesWorkflowPath(Path)}
   * </ul>
   */
  @Test
  @DisplayName("Test Builder build()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"GitHubActionsChunkWorkflow Builder.build()", "Builder Builder.chunkId(String)",
      "Builder Builder.name(String)", "Builder Builder.onTriggerPaths(List)", "Builder Builder.usesWorkflowPath(Path)"})
  void testBuilderBuild() {
    // Arrange
    Builder nameResult = GitHubActionsChunkWorkflow.builder().chunkId("42").name("Name");
    Path usesWorkflowPath = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    // Act and Assert
    assertSame(usesWorkflowPath,
        nameResult.onTriggerPaths(new ArrayList<>()).usesWorkflowPath(usesWorkflowPath).build().getUsesWorkflowPath());
  }

  /**
   * Test Builder {@link Builder#onTriggerPathsArray(String[])}.
   * <p>
   * Method under test: {@link Builder#onTriggerPathsArray(String[])}
   */
  @Test
  @DisplayName("Test Builder onTriggerPathsArray(String[])")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.onTriggerPathsArray(String[])"})
  void testBuilderOnTriggerPathsArray() {
    // Arrange
    Builder builderResult = GitHubActionsChunkWorkflow.builder();

    // Act and Assert
    assertSame(builderResult, builderResult.onTriggerPathsArray("On Trigger Paths"));
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link GitHubActionsChunkWorkflow#toString()}
   *   <li>{@link GitHubActionsChunkWorkflow#getUsesWorkflowPath()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Path GitHubActionsChunkWorkflow.getUsesWorkflowPath()",
      "String GitHubActionsChunkWorkflow.toString()"})
  void testGettersAndSetters() {
    // Arrange
    Builder nameResult = GitHubActionsChunkWorkflow.builder().chunkId("42").name("Name");
    Path usesWorkflowPath = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    GitHubActionsChunkWorkflow buildResult = nameResult.onTriggerPaths(new ArrayList<>())
        .usesWorkflowPath(usesWorkflowPath)
        .build();

    // Act
    String actualToStringResult = buildResult.toString();
    Path actualUsesWorkflowPath = buildResult.getUsesWorkflowPath();

    // Assert
    assertEquals(
        String.join("", "GitHubActionsChunkWorkflow{chunkId='42', name='Name', usesWorkflowPath=",
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toString(), ", onPushPaths=[]}"),
        actualToStringResult);
    assertSame(usesWorkflowPath, actualUsesWorkflowPath);
  }

  /**
   * Test {@link GitHubActionsChunkWorkflow#equals(Object)}, and {@link GitHubActionsChunkWorkflow#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link GitHubActionsChunkWorkflow#equals(Object)}
   *   <li>{@link GitHubActionsChunkWorkflow#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean GitHubActionsChunkWorkflow.equals(Object)", "int GitHubActionsChunkWorkflow.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    Builder nameResult = GitHubActionsChunkWorkflow.builder().chunkId("42").name("Name");
    GitHubActionsChunkWorkflow buildResult = nameResult.onTriggerPaths(new ArrayList<>())
        .usesWorkflowPath(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .build();
    Builder nameResult2 = GitHubActionsChunkWorkflow.builder().chunkId("42").name("Name");
    GitHubActionsChunkWorkflow buildResult2 = nameResult2.onTriggerPaths(new ArrayList<>())
        .usesWorkflowPath(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .build();

    // Act and Assert
    assertEquals(buildResult, buildResult2);
    int expectedHashCodeResult = buildResult.hashCode();
    assertEquals(expectedHashCodeResult, buildResult2.hashCode());
  }

  /**
   * Test {@link GitHubActionsChunkWorkflow#equals(Object)}, and {@link GitHubActionsChunkWorkflow#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link GitHubActionsChunkWorkflow#equals(Object)}
   *   <li>{@link GitHubActionsChunkWorkflow#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean GitHubActionsChunkWorkflow.equals(Object)", "int GitHubActionsChunkWorkflow.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    Builder nameResult = GitHubActionsChunkWorkflow.builder().chunkId("42").name("Name");
    GitHubActionsChunkWorkflow buildResult = nameResult.onTriggerPaths(new ArrayList<>())
        .usesWorkflowPath(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .build();

    // Act and Assert
    assertEquals(buildResult, buildResult);
    int expectedHashCodeResult = buildResult.hashCode();
    assertEquals(expectedHashCodeResult, buildResult.hashCode());
  }

  /**
   * Test {@link GitHubActionsChunkWorkflow#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link GitHubActionsChunkWorkflow#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean GitHubActionsChunkWorkflow.equals(Object)", "int GitHubActionsChunkWorkflow.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    Builder nameResult = GitHubActionsChunkWorkflow.builder().chunkId("Name").name("Name");
    GitHubActionsChunkWorkflow buildResult = nameResult.onTriggerPaths(new ArrayList<>())
        .usesWorkflowPath(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .build();
    Builder nameResult2 = GitHubActionsChunkWorkflow.builder().chunkId("42").name("Name");
    GitHubActionsChunkWorkflow buildResult2 = nameResult2.onTriggerPaths(new ArrayList<>())
        .usesWorkflowPath(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link GitHubActionsChunkWorkflow#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link GitHubActionsChunkWorkflow#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean GitHubActionsChunkWorkflow.equals(Object)", "int GitHubActionsChunkWorkflow.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    Builder nameResult = GitHubActionsChunkWorkflow.builder().chunkId("42").name("42");
    GitHubActionsChunkWorkflow buildResult = nameResult.onTriggerPaths(new ArrayList<>())
        .usesWorkflowPath(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .build();
    Builder nameResult2 = GitHubActionsChunkWorkflow.builder().chunkId("42").name("Name");
    GitHubActionsChunkWorkflow buildResult2 = nameResult2.onTriggerPaths(new ArrayList<>())
        .usesWorkflowPath(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link GitHubActionsChunkWorkflow#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link GitHubActionsChunkWorkflow#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean GitHubActionsChunkWorkflow.equals(Object)", "int GitHubActionsChunkWorkflow.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual3() {
    // Arrange
    ArrayList<String> onTriggerPaths = new ArrayList<>();
    onTriggerPaths.add("42");
    GitHubActionsChunkWorkflow buildResult = GitHubActionsChunkWorkflow.builder()
        .chunkId("42")
        .name("Name")
        .onTriggerPaths(onTriggerPaths)
        .usesWorkflowPath(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .build();
    Builder nameResult = GitHubActionsChunkWorkflow.builder().chunkId("42").name("Name");
    GitHubActionsChunkWorkflow buildResult2 = nameResult.onTriggerPaths(new ArrayList<>())
        .usesWorkflowPath(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link GitHubActionsChunkWorkflow#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link GitHubActionsChunkWorkflow#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean GitHubActionsChunkWorkflow.equals(Object)", "int GitHubActionsChunkWorkflow.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual4() {
    // Arrange
    Builder nameResult = GitHubActionsChunkWorkflow.builder().chunkId("42").name("Name");
    GitHubActionsChunkWorkflow buildResult = nameResult.onTriggerPaths(new ArrayList<>())
        .usesWorkflowPath(Paths.get(System.getProperty("java.io.tmpdir"), "42"))
        .build();
    Builder nameResult2 = GitHubActionsChunkWorkflow.builder().chunkId("42").name("Name");
    GitHubActionsChunkWorkflow buildResult2 = nameResult2.onTriggerPaths(new ArrayList<>())
        .usesWorkflowPath(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link GitHubActionsChunkWorkflow#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link GitHubActionsChunkWorkflow#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean GitHubActionsChunkWorkflow.equals(Object)", "int GitHubActionsChunkWorkflow.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange
    Builder nameResult = GitHubActionsChunkWorkflow.builder().chunkId("42").name("Name");
    GitHubActionsChunkWorkflow buildResult = nameResult.onTriggerPaths(new ArrayList<>())
        .usesWorkflowPath(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .build();

    // Act and Assert
    assertNotEquals(buildResult, null);
  }

  /**
   * Test {@link GitHubActionsChunkWorkflow#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link GitHubActionsChunkWorkflow#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean GitHubActionsChunkWorkflow.equals(Object)", "int GitHubActionsChunkWorkflow.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange
    Builder nameResult = GitHubActionsChunkWorkflow.builder().chunkId("42").name("Name");
    GitHubActionsChunkWorkflow buildResult = nameResult.onTriggerPaths(new ArrayList<>())
        .usesWorkflowPath(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .build();

    // Act and Assert
    assertNotEquals(buildResult, "Different type to GitHubActionsChunkWorkflow");
  }
}
