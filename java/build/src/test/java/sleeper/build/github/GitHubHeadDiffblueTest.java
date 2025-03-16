package sleeper.build.github;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.build.github.GitHubHead.Builder;

class GitHubHeadDiffblueTest {
  /**
   * Test Builder {@link Builder#build()}.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Builder#build()}
   *   <li>{@link Builder#branch(String)}
   *   <li>{@link Builder#owner(String)}
   *   <li>{@link Builder#repository(String)}
   *   <li>{@link Builder#sha(String)}
   * </ul>
   */
  @Test
  @DisplayName("Test Builder build()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.branch(String)", "GitHubHead Builder.build()", "Builder Builder.owner(String)",
      "Builder Builder.repository(String)", "Builder Builder.sha(String)"})
  void testBuilderBuild() {
    // Arrange and Act
    GitHubHead actualBuildResult = GitHubHead.builder()
        .branch("janedoe/featurebranch")
        .owner("Owner")
        .repository("Repository")
        .sha("Sha")
        .build();

    // Assert
    assertEquals("Owner/Repository", actualBuildResult.getOwnerAndRepository());
    assertEquals("Sha", actualBuildResult.getSha());
    assertEquals("janedoe/featurebranch", actualBuildResult.getBranch());
  }

  /**
   * Test Builder {@link Builder#ownerAndRepository(String)}.
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#ownerAndRepository(String)}
   */
  @Test
  @DisplayName("Test Builder ownerAndRepository(String); then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.ownerAndRepository(String)"})
  void testBuilderOwnerAndRepository_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> GitHubHead.builder().ownerAndRepository("Owner And Repository"));
  }

  /**
   * Test Builder {@link Builder#ownerAndRepository(String)}.
   * <ul>
   *   <li>When {@code /Owner And Repository}.</li>
   *   <li>Then return builder.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#ownerAndRepository(String)}
   */
  @Test
  @DisplayName("Test Builder ownerAndRepository(String); when '/Owner And Repository'; then return builder")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.ownerAndRepository(String)"})
  void testBuilderOwnerAndRepository_whenOwnerAndRepository_thenReturnBuilder() {
    // Arrange
    Builder builderResult = GitHubHead.builder();

    // Act and Assert
    assertSame(builderResult, builderResult.ownerAndRepository("/Owner And Repository"));
  }

  /**
   * Test {@link GitHubHead#getOwnerAndRepository()}.
   * <p>
   * Method under test: {@link GitHubHead#getOwnerAndRepository()}
   */
  @Test
  @DisplayName("Test getOwnerAndRepository()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String GitHubHead.getOwnerAndRepository()"})
  void testGetOwnerAndRepository() {
    // Arrange
    GitHubHead buildResult = GitHubHead.builder()
        .branch("janedoe/featurebranch")
        .owner("Owner")
        .repository("Repository")
        .sha("Sha")
        .build();

    // Act and Assert
    assertEquals("Owner/Repository", buildResult.getOwnerAndRepository());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link GitHubHead#toString()}
   *   <li>{@link GitHubHead#getBranch()}
   *   <li>{@link GitHubHead#getSha()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String GitHubHead.getBranch()", "String GitHubHead.getSha()", "String GitHubHead.toString()"})
  void testGettersAndSetters() {
    // Arrange
    GitHubHead buildResult = GitHubHead.builder()
        .branch("janedoe/featurebranch")
        .owner("Owner")
        .repository("Repository")
        .sha("Sha")
        .build();

    // Act
    String actualToStringResult = buildResult.toString();
    String actualBranch = buildResult.getBranch();

    // Assert
    assertEquals("GitHubHead{owner='Owner', repository='Repository', branch='janedoe/featurebranch', sha='Sha'}",
        actualToStringResult);
    assertEquals("Sha", buildResult.getSha());
    assertEquals("janedoe/featurebranch", actualBranch);
  }

  /**
   * Test {@link GitHubHead#equals(Object)}, and {@link GitHubHead#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link GitHubHead#equals(Object)}
   *   <li>{@link GitHubHead#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean GitHubHead.equals(Object)", "int GitHubHead.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    GitHubHead buildResult = GitHubHead.builder()
        .branch("janedoe/featurebranch")
        .owner("Owner")
        .repository("Repository")
        .sha("Sha")
        .build();
    GitHubHead buildResult2 = GitHubHead.builder()
        .branch("janedoe/featurebranch")
        .owner("Owner")
        .repository("Repository")
        .sha("Sha")
        .build();

    // Act and Assert
    assertEquals(buildResult, buildResult2);
    int expectedHashCodeResult = buildResult.hashCode();
    assertEquals(expectedHashCodeResult, buildResult2.hashCode());
  }

  /**
   * Test {@link GitHubHead#equals(Object)}, and {@link GitHubHead#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link GitHubHead#equals(Object)}
   *   <li>{@link GitHubHead#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean GitHubHead.equals(Object)", "int GitHubHead.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    GitHubHead buildResult = GitHubHead.builder()
        .branch("janedoe/featurebranch")
        .owner("Owner")
        .repository("Repository")
        .sha("Sha")
        .build();

    // Act and Assert
    assertEquals(buildResult, buildResult);
    int expectedHashCodeResult = buildResult.hashCode();
    assertEquals(expectedHashCodeResult, buildResult.hashCode());
  }

  /**
   * Test {@link GitHubHead#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link GitHubHead#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean GitHubHead.equals(Object)", "int GitHubHead.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    GitHubHead buildResult = GitHubHead.builder()
        .branch("Owner")
        .owner("Owner")
        .repository("Repository")
        .sha("Sha")
        .build();
    GitHubHead buildResult2 = GitHubHead.builder()
        .branch("janedoe/featurebranch")
        .owner("Owner")
        .repository("Repository")
        .sha("Sha")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link GitHubHead#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link GitHubHead#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean GitHubHead.equals(Object)", "int GitHubHead.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    GitHubHead buildResult = GitHubHead.builder()
        .branch("janedoe/featurebranch")
        .owner("Repository")
        .repository("Repository")
        .sha("Sha")
        .build();
    GitHubHead buildResult2 = GitHubHead.builder()
        .branch("janedoe/featurebranch")
        .owner("Owner")
        .repository("Repository")
        .sha("Sha")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link GitHubHead#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link GitHubHead#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean GitHubHead.equals(Object)", "int GitHubHead.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual3() {
    // Arrange
    GitHubHead buildResult = GitHubHead.builder()
        .branch("janedoe/featurebranch")
        .owner("Owner")
        .repository("Owner")
        .sha("Sha")
        .build();
    GitHubHead buildResult2 = GitHubHead.builder()
        .branch("janedoe/featurebranch")
        .owner("Owner")
        .repository("Repository")
        .sha("Sha")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link GitHubHead#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link GitHubHead#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean GitHubHead.equals(Object)", "int GitHubHead.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual4() {
    // Arrange
    GitHubHead buildResult = GitHubHead.builder()
        .branch("janedoe/featurebranch")
        .owner("Owner")
        .repository("Repository")
        .sha("Owner")
        .build();
    GitHubHead buildResult2 = GitHubHead.builder()
        .branch("janedoe/featurebranch")
        .owner("Owner")
        .repository("Repository")
        .sha("Sha")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link GitHubHead#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link GitHubHead#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean GitHubHead.equals(Object)", "int GitHubHead.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange
    GitHubHead buildResult = GitHubHead.builder()
        .branch("janedoe/featurebranch")
        .owner("Owner")
        .repository("Repository")
        .sha("Sha")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, null);
  }

  /**
   * Test {@link GitHubHead#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link GitHubHead#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean GitHubHead.equals(Object)", "int GitHubHead.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange
    GitHubHead buildResult = GitHubHead.builder()
        .branch("janedoe/featurebranch")
        .owner("Owner")
        .repository("Repository")
        .sha("Sha")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, "Different type to GitHubHead");
  }
}
