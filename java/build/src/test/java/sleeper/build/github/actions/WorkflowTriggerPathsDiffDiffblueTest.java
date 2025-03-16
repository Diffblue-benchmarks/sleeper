package sleeper.build.github.actions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.build.chunks.ProjectStructure;
import sleeper.build.github.actions.WorkflowTriggerPathsDiff.Builder;

class WorkflowTriggerPathsDiffDiffblueTest {
  /**
   * Test Builder {@link Builder#build()}.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Builder#build()}
   *   <li>{@link Builder#actual(List)}
   *   <li>{@link Builder#expected(List)}
   *   <li>{@link Builder#extraEntries(List)}
   *   <li>{@link Builder#missingEntries(List)}
   * </ul>
   */
  @Test
  @DisplayName("Test Builder build()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.actual(List)", "WorkflowTriggerPathsDiff Builder.build()",
      "Builder Builder.expected(List)", "Builder Builder.extraEntries(List)", "Builder Builder.missingEntries(List)"})
  void testBuilderBuild() {
    // Arrange
    Builder builderResult = WorkflowTriggerPathsDiff.builder();
    ArrayList<String> actual = new ArrayList<>();
    Builder actualResult = builderResult.actual(actual);
    ArrayList<String> expected = new ArrayList<>();
    Builder expectedResult = actualResult.expected(expected);
    ArrayList<String> extraEntries = new ArrayList<>();
    Builder extraEntriesResult = expectedResult.extraEntries(extraEntries);
    ArrayList<String> missingEntries = new ArrayList<>();

    // Act
    WorkflowTriggerPathsDiff actualBuildResult = extraEntriesResult.missingEntries(missingEntries).build();

    // Assert
    List<String> actual2 = actualBuildResult.getActual();
    assertTrue(actual2.isEmpty());
    List<String> expected2 = actualBuildResult.getExpected();
    assertTrue(expected2.isEmpty());
    List<String> extraEntries2 = actualBuildResult.getExtraEntries();
    assertTrue(extraEntries2.isEmpty());
    List<String> missingEntries2 = actualBuildResult.getMissingEntries();
    assertTrue(missingEntries2.isEmpty());
    assertTrue(actualBuildResult.isValid());
    assertSame(actual, actual2);
    assertSame(expected, expected2);
    assertSame(extraEntries, extraEntries2);
    assertSame(missingEntries, missingEntries2);
  }

  /**
   * Test {@link WorkflowTriggerPathsDiff#fromExpectedAndActual(ProjectStructure, List, List)}.
   * <ul>
   *   <li>Given {@code actual must not be null}.</li>
   *   <li>Then return Actual Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link WorkflowTriggerPathsDiff#fromExpectedAndActual(ProjectStructure, List, List)}
   */
  @Test
  @DisplayName("Test fromExpectedAndActual(ProjectStructure, List, List); given 'actual must not be null'; then return Actual Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "WorkflowTriggerPathsDiff WorkflowTriggerPathsDiff.fromExpectedAndActual(ProjectStructure, List, List)"})
  void testFromExpectedAndActual_givenActualMustNotBeNull_thenReturnActualEmpty() {
    // Arrange
    ProjectStructure project = mock(ProjectStructure.class);

    ArrayList<String> expected = new ArrayList<>();
    expected.add("actual must not be null");
    expected.add("expected must not be null");

    // Act
    WorkflowTriggerPathsDiff actualFromExpectedAndActualResult = WorkflowTriggerPathsDiff.fromExpectedAndActual(project,
        expected, new ArrayList<>());

    // Assert
    assertTrue(actualFromExpectedAndActualResult.getActual().isEmpty());
    assertSame(expected, actualFromExpectedAndActualResult.getExpected());
  }

  /**
   * Test {@link WorkflowTriggerPathsDiff#fromExpectedAndActual(ProjectStructure, List, List)}.
   * <ul>
   *   <li>Given {@code true}.</li>
   *   <li>Then return Actual size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link WorkflowTriggerPathsDiff#fromExpectedAndActual(ProjectStructure, List, List)}
   */
  @Test
  @DisplayName("Test fromExpectedAndActual(ProjectStructure, List, List); given 'true'; then return Actual size is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "WorkflowTriggerPathsDiff WorkflowTriggerPathsDiff.fromExpectedAndActual(ProjectStructure, List, List)"})
  void testFromExpectedAndActual_givenTrue_thenReturnActualSizeIsOne() {
    // Arrange
    ProjectStructure project = mock(ProjectStructure.class);
    when(project.isUnderMavenPathRepositoryRelative(Mockito.<String>any())).thenReturn(true);
    ArrayList<String> expected = new ArrayList<>();

    ArrayList<String> actual = new ArrayList<>();
    actual.add("expected must not be null");

    // Act
    WorkflowTriggerPathsDiff actualFromExpectedAndActualResult = WorkflowTriggerPathsDiff.fromExpectedAndActual(project,
        expected, actual);

    // Assert
    verify(project).isUnderMavenPathRepositoryRelative(eq("expected must not be null"));
    List<String> actual2 = actualFromExpectedAndActualResult.getActual();
    assertEquals(1, actual2.size());
    assertEquals("expected must not be null", actual2.get(0));
  }

  /**
   * Test {@link WorkflowTriggerPathsDiff#fromExpectedAndActual(ProjectStructure, List, List)}.
   * <ul>
   *   <li>Given {@code true}.</li>
   *   <li>Then return Actual size is two.</li>
   * </ul>
   * <p>
   * Method under test: {@link WorkflowTriggerPathsDiff#fromExpectedAndActual(ProjectStructure, List, List)}
   */
  @Test
  @DisplayName("Test fromExpectedAndActual(ProjectStructure, List, List); given 'true'; then return Actual size is two")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "WorkflowTriggerPathsDiff WorkflowTriggerPathsDiff.fromExpectedAndActual(ProjectStructure, List, List)"})
  void testFromExpectedAndActual_givenTrue_thenReturnActualSizeIsTwo() {
    // Arrange
    ProjectStructure project = mock(ProjectStructure.class);
    when(project.isUnderMavenPathRepositoryRelative(Mockito.<String>any())).thenReturn(true);
    ArrayList<String> expected = new ArrayList<>();

    ArrayList<String> actual = new ArrayList<>();
    actual.add("actual must not be null");
    actual.add("expected must not be null");

    // Act
    WorkflowTriggerPathsDiff actualFromExpectedAndActualResult = WorkflowTriggerPathsDiff.fromExpectedAndActual(project,
        expected, actual);

    // Assert
    verify(project, atLeast(1)).isUnderMavenPathRepositoryRelative(Mockito.<String>any());
    List<String> actual2 = actualFromExpectedAndActualResult.getActual();
    assertEquals(2, actual2.size());
    assertEquals("actual must not be null", actual2.get(0));
    assertEquals("expected must not be null", actual2.get(1));
  }

  /**
   * Test {@link WorkflowTriggerPathsDiff#fromExpectedAndActual(ProjectStructure, List, List)}.
   * <ul>
   *   <li>When {@link ProjectStructure}.</li>
   *   <li>Then return Actual Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link WorkflowTriggerPathsDiff#fromExpectedAndActual(ProjectStructure, List, List)}
   */
  @Test
  @DisplayName("Test fromExpectedAndActual(ProjectStructure, List, List); when ProjectStructure; then return Actual Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "WorkflowTriggerPathsDiff WorkflowTriggerPathsDiff.fromExpectedAndActual(ProjectStructure, List, List)"})
  void testFromExpectedAndActual_whenProjectStructure_thenReturnActualEmpty() {
    // Arrange
    ProjectStructure project = mock(ProjectStructure.class);

    ArrayList<String> expected = new ArrayList<>();
    expected.add("expected must not be null");

    // Act
    WorkflowTriggerPathsDiff actualFromExpectedAndActualResult = WorkflowTriggerPathsDiff.fromExpectedAndActual(project,
        expected, new ArrayList<>());

    // Assert
    assertTrue(actualFromExpectedAndActualResult.getActual().isEmpty());
    assertSame(expected, actualFromExpectedAndActualResult.getExpected());
  }

  /**
   * Test {@link WorkflowTriggerPathsDiff#fromExpectedAndActual(ProjectStructure, List, List)}.
   * <ul>
   *   <li>When {@link ProjectStructure}.</li>
   *   <li>Then return Actual is {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WorkflowTriggerPathsDiff#fromExpectedAndActual(ProjectStructure, List, List)}
   */
  @Test
  @DisplayName("Test fromExpectedAndActual(ProjectStructure, List, List); when ProjectStructure; then return Actual is ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "WorkflowTriggerPathsDiff WorkflowTriggerPathsDiff.fromExpectedAndActual(ProjectStructure, List, List)"})
  void testFromExpectedAndActual_whenProjectStructure_thenReturnActualIsArrayList() {
    // Arrange
    ProjectStructure project = mock(ProjectStructure.class);

    ArrayList<String> expected = new ArrayList<>();
    expected.add("expected must not be null");

    ArrayList<String> actual = new ArrayList<>();
    actual.add("expected must not be null");

    // Act
    WorkflowTriggerPathsDiff actualFromExpectedAndActualResult = WorkflowTriggerPathsDiff.fromExpectedAndActual(project,
        expected, actual);

    // Assert
    assertTrue(actualFromExpectedAndActualResult.isValid());
    assertSame(actual, actualFromExpectedAndActualResult.getActual());
    assertSame(expected, actualFromExpectedAndActualResult.getExpected());
  }

  /**
   * Test {@link WorkflowTriggerPathsDiff#fromExpectedAndActual(ProjectStructure, List, List)}.
   * <ul>
   *   <li>When {@link ProjectStructure}.</li>
   *   <li>Then return Expected Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link WorkflowTriggerPathsDiff#fromExpectedAndActual(ProjectStructure, List, List)}
   */
  @Test
  @DisplayName("Test fromExpectedAndActual(ProjectStructure, List, List); when ProjectStructure; then return Expected Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "WorkflowTriggerPathsDiff WorkflowTriggerPathsDiff.fromExpectedAndActual(ProjectStructure, List, List)"})
  void testFromExpectedAndActual_whenProjectStructure_thenReturnExpectedEmpty() {
    // Arrange
    ProjectStructure project = mock(ProjectStructure.class);
    ArrayList<String> expected = new ArrayList<>();

    // Act
    WorkflowTriggerPathsDiff actualFromExpectedAndActualResult = WorkflowTriggerPathsDiff.fromExpectedAndActual(project,
        expected, new ArrayList<>());

    // Assert
    assertTrue(actualFromExpectedAndActualResult.getExpected().isEmpty());
    assertTrue(actualFromExpectedAndActualResult.getExtraEntries().isEmpty());
    assertTrue(actualFromExpectedAndActualResult.getMissingEntries().isEmpty());
    assertTrue(actualFromExpectedAndActualResult.isValid());
  }

  /**
   * Test {@link WorkflowTriggerPathsDiff#isValid()}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@code foo}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WorkflowTriggerPathsDiff#isValid()}
   */
  @Test
  @DisplayName("Test isValid(); given ArrayList() add 'foo'; then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean WorkflowTriggerPathsDiff.isValid()"})
  void testIsValid_givenArrayListAddFoo_thenReturnFalse() {
    // Arrange
    ArrayList<String> extraEntries = new ArrayList<>();
    extraEntries.add("foo");
    Builder builderResult = WorkflowTriggerPathsDiff.builder();
    Builder actualResult = builderResult.actual(new ArrayList<>());
    Builder extraEntriesResult = actualResult.expected(new ArrayList<>()).extraEntries(extraEntries);
    WorkflowTriggerPathsDiff buildResult = extraEntriesResult.missingEntries(new ArrayList<>()).build();

    // Act and Assert
    assertFalse(buildResult.isValid());
  }

  /**
   * Test {@link WorkflowTriggerPathsDiff#isValid()}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@code foo}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WorkflowTriggerPathsDiff#isValid()}
   */
  @Test
  @DisplayName("Test isValid(); given ArrayList() add 'foo'; then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean WorkflowTriggerPathsDiff.isValid()"})
  void testIsValid_givenArrayListAddFoo_thenReturnFalse2() {
    // Arrange
    ArrayList<String> missingEntries = new ArrayList<>();
    missingEntries.add("foo");
    Builder builderResult = WorkflowTriggerPathsDiff.builder();
    Builder actualResult = builderResult.actual(new ArrayList<>());
    Builder expectedResult = actualResult.expected(new ArrayList<>());
    WorkflowTriggerPathsDiff buildResult = expectedResult.extraEntries(new ArrayList<>())
        .missingEntries(missingEntries)
        .build();

    // Act and Assert
    assertFalse(buildResult.isValid());
  }

  /**
   * Test {@link WorkflowTriggerPathsDiff#isValid()}.
   * <ul>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WorkflowTriggerPathsDiff#isValid()}
   */
  @Test
  @DisplayName("Test isValid(); then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean WorkflowTriggerPathsDiff.isValid()"})
  void testIsValid_thenReturnTrue() {
    // Arrange
    Builder builderResult = WorkflowTriggerPathsDiff.builder();
    Builder actualResult = builderResult.actual(new ArrayList<>());
    Builder expectedResult = actualResult.expected(new ArrayList<>());
    Builder extraEntriesResult = expectedResult.extraEntries(new ArrayList<>());
    WorkflowTriggerPathsDiff buildResult = extraEntriesResult.missingEntries(new ArrayList<>()).build();

    // Act and Assert
    assertTrue(buildResult.isValid());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link WorkflowTriggerPathsDiff#toString()}
   *   <li>{@link WorkflowTriggerPathsDiff#getActual()}
   *   <li>{@link WorkflowTriggerPathsDiff#getExpected()}
   *   <li>{@link WorkflowTriggerPathsDiff#getExtraEntries()}
   *   <li>{@link WorkflowTriggerPathsDiff#getMissingEntries()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List WorkflowTriggerPathsDiff.getActual()", "List WorkflowTriggerPathsDiff.getExpected()",
      "List WorkflowTriggerPathsDiff.getExtraEntries()", "List WorkflowTriggerPathsDiff.getMissingEntries()",
      "String WorkflowTriggerPathsDiff.toString()"})
  void testGettersAndSetters() {
    // Arrange
    Builder builderResult = WorkflowTriggerPathsDiff.builder();
    ArrayList<String> actual = new ArrayList<>();
    Builder actualResult = builderResult.actual(actual);
    ArrayList<String> expected = new ArrayList<>();
    Builder expectedResult = actualResult.expected(expected);
    ArrayList<String> extraEntries = new ArrayList<>();
    Builder extraEntriesResult = expectedResult.extraEntries(extraEntries);
    ArrayList<String> missingEntries = new ArrayList<>();
    WorkflowTriggerPathsDiff buildResult = extraEntriesResult.missingEntries(missingEntries).build();

    // Act
    String actualToStringResult = buildResult.toString();
    List<String> actualActual = buildResult.getActual();
    List<String> actualExpected = buildResult.getExpected();
    List<String> actualExtraEntries = buildResult.getExtraEntries();
    List<String> actualMissingEntries = buildResult.getMissingEntries();

    // Assert
    assertEquals("OnPullRequestPathsDiff{expected=[], actual=[], missingEntries=[], extraEntries=[]}",
        actualToStringResult);
    assertTrue(actualActual.isEmpty());
    assertTrue(actualExpected.isEmpty());
    assertTrue(actualExtraEntries.isEmpty());
    assertTrue(actualMissingEntries.isEmpty());
    assertSame(actual, actualActual);
    assertSame(expected, actualExpected);
    assertSame(extraEntries, actualExtraEntries);
    assertSame(missingEntries, actualMissingEntries);
  }

  /**
   * Test {@link WorkflowTriggerPathsDiff#equals(Object)}, and {@link WorkflowTriggerPathsDiff#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link WorkflowTriggerPathsDiff#equals(Object)}
   *   <li>{@link WorkflowTriggerPathsDiff#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean WorkflowTriggerPathsDiff.equals(Object)", "int WorkflowTriggerPathsDiff.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    Builder builderResult = WorkflowTriggerPathsDiff.builder();
    Builder actualResult = builderResult.actual(new ArrayList<>());
    Builder expectedResult = actualResult.expected(new ArrayList<>());
    Builder extraEntriesResult = expectedResult.extraEntries(new ArrayList<>());
    WorkflowTriggerPathsDiff buildResult = extraEntriesResult.missingEntries(new ArrayList<>()).build();
    Builder builderResult2 = WorkflowTriggerPathsDiff.builder();
    Builder actualResult2 = builderResult2.actual(new ArrayList<>());
    Builder expectedResult2 = actualResult2.expected(new ArrayList<>());
    Builder extraEntriesResult2 = expectedResult2.extraEntries(new ArrayList<>());
    WorkflowTriggerPathsDiff buildResult2 = extraEntriesResult2.missingEntries(new ArrayList<>()).build();

    // Act and Assert
    assertEquals(buildResult, buildResult2);
    int expectedHashCodeResult = buildResult.hashCode();
    assertEquals(expectedHashCodeResult, buildResult2.hashCode());
  }

  /**
   * Test {@link WorkflowTriggerPathsDiff#equals(Object)}, and {@link WorkflowTriggerPathsDiff#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link WorkflowTriggerPathsDiff#equals(Object)}
   *   <li>{@link WorkflowTriggerPathsDiff#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean WorkflowTriggerPathsDiff.equals(Object)", "int WorkflowTriggerPathsDiff.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    Builder builderResult = WorkflowTriggerPathsDiff.builder();
    Builder actualResult = builderResult.actual(new ArrayList<>());
    Builder expectedResult = actualResult.expected(new ArrayList<>());
    Builder extraEntriesResult = expectedResult.extraEntries(new ArrayList<>());
    WorkflowTriggerPathsDiff buildResult = extraEntriesResult.missingEntries(new ArrayList<>()).build();

    // Act and Assert
    assertEquals(buildResult, buildResult);
    int expectedHashCodeResult = buildResult.hashCode();
    assertEquals(expectedHashCodeResult, buildResult.hashCode());
  }

  /**
   * Test {@link WorkflowTriggerPathsDiff#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link WorkflowTriggerPathsDiff#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean WorkflowTriggerPathsDiff.equals(Object)", "int WorkflowTriggerPathsDiff.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    ArrayList<String> actual = new ArrayList<>();
    actual.add("foo");
    Builder actualResult = WorkflowTriggerPathsDiff.builder().actual(actual);
    Builder expectedResult = actualResult.expected(new ArrayList<>());
    Builder extraEntriesResult = expectedResult.extraEntries(new ArrayList<>());
    WorkflowTriggerPathsDiff buildResult = extraEntriesResult.missingEntries(new ArrayList<>()).build();
    Builder builderResult = WorkflowTriggerPathsDiff.builder();
    Builder actualResult2 = builderResult.actual(new ArrayList<>());
    Builder expectedResult2 = actualResult2.expected(new ArrayList<>());
    Builder extraEntriesResult2 = expectedResult2.extraEntries(new ArrayList<>());
    WorkflowTriggerPathsDiff buildResult2 = extraEntriesResult2.missingEntries(new ArrayList<>()).build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link WorkflowTriggerPathsDiff#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link WorkflowTriggerPathsDiff#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean WorkflowTriggerPathsDiff.equals(Object)", "int WorkflowTriggerPathsDiff.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    ArrayList<String> expected = new ArrayList<>();
    expected.add("foo");
    Builder builderResult = WorkflowTriggerPathsDiff.builder();
    Builder expectedResult = builderResult.actual(new ArrayList<>()).expected(expected);
    Builder extraEntriesResult = expectedResult.extraEntries(new ArrayList<>());
    WorkflowTriggerPathsDiff buildResult = extraEntriesResult.missingEntries(new ArrayList<>()).build();
    Builder builderResult2 = WorkflowTriggerPathsDiff.builder();
    Builder actualResult = builderResult2.actual(new ArrayList<>());
    Builder expectedResult2 = actualResult.expected(new ArrayList<>());
    Builder extraEntriesResult2 = expectedResult2.extraEntries(new ArrayList<>());
    WorkflowTriggerPathsDiff buildResult2 = extraEntriesResult2.missingEntries(new ArrayList<>()).build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link WorkflowTriggerPathsDiff#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link WorkflowTriggerPathsDiff#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean WorkflowTriggerPathsDiff.equals(Object)", "int WorkflowTriggerPathsDiff.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual3() {
    // Arrange
    ArrayList<String> extraEntries = new ArrayList<>();
    extraEntries.add("foo");
    Builder builderResult = WorkflowTriggerPathsDiff.builder();
    Builder actualResult = builderResult.actual(new ArrayList<>());
    Builder extraEntriesResult = actualResult.expected(new ArrayList<>()).extraEntries(extraEntries);
    WorkflowTriggerPathsDiff buildResult = extraEntriesResult.missingEntries(new ArrayList<>()).build();
    Builder builderResult2 = WorkflowTriggerPathsDiff.builder();
    Builder actualResult2 = builderResult2.actual(new ArrayList<>());
    Builder expectedResult = actualResult2.expected(new ArrayList<>());
    Builder extraEntriesResult2 = expectedResult.extraEntries(new ArrayList<>());
    WorkflowTriggerPathsDiff buildResult2 = extraEntriesResult2.missingEntries(new ArrayList<>()).build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link WorkflowTriggerPathsDiff#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link WorkflowTriggerPathsDiff#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean WorkflowTriggerPathsDiff.equals(Object)", "int WorkflowTriggerPathsDiff.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual4() {
    // Arrange
    ArrayList<String> missingEntries = new ArrayList<>();
    missingEntries.add("foo");
    Builder builderResult = WorkflowTriggerPathsDiff.builder();
    Builder actualResult = builderResult.actual(new ArrayList<>());
    Builder expectedResult = actualResult.expected(new ArrayList<>());
    WorkflowTriggerPathsDiff buildResult = expectedResult.extraEntries(new ArrayList<>())
        .missingEntries(missingEntries)
        .build();
    Builder builderResult2 = WorkflowTriggerPathsDiff.builder();
    Builder actualResult2 = builderResult2.actual(new ArrayList<>());
    Builder expectedResult2 = actualResult2.expected(new ArrayList<>());
    Builder extraEntriesResult = expectedResult2.extraEntries(new ArrayList<>());
    WorkflowTriggerPathsDiff buildResult2 = extraEntriesResult.missingEntries(new ArrayList<>()).build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link WorkflowTriggerPathsDiff#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link WorkflowTriggerPathsDiff#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean WorkflowTriggerPathsDiff.equals(Object)", "int WorkflowTriggerPathsDiff.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange
    Builder builderResult = WorkflowTriggerPathsDiff.builder();
    Builder actualResult = builderResult.actual(new ArrayList<>());
    Builder expectedResult = actualResult.expected(new ArrayList<>());
    Builder extraEntriesResult = expectedResult.extraEntries(new ArrayList<>());
    WorkflowTriggerPathsDiff buildResult = extraEntriesResult.missingEntries(new ArrayList<>()).build();

    // Act and Assert
    assertNotEquals(buildResult, null);
  }

  /**
   * Test {@link WorkflowTriggerPathsDiff#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link WorkflowTriggerPathsDiff#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean WorkflowTriggerPathsDiff.equals(Object)", "int WorkflowTriggerPathsDiff.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange
    Builder builderResult = WorkflowTriggerPathsDiff.builder();
    Builder actualResult = builderResult.actual(new ArrayList<>());
    Builder expectedResult = actualResult.expected(new ArrayList<>());
    Builder extraEntriesResult = expectedResult.extraEntries(new ArrayList<>());
    WorkflowTriggerPathsDiff buildResult = extraEntriesResult.missingEntries(new ArrayList<>()).build();

    // Act and Assert
    assertNotEquals(buildResult, "Different type to WorkflowTriggerPathsDiff");
  }
}
