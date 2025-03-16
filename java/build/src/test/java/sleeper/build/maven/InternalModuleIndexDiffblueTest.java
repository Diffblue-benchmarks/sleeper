package sleeper.build.maven;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.build.maven.MavenModuleStructure.Builder;

class InternalModuleIndexDiffblueTest {
  /**
   * Test {@link InternalModuleIndex#InternalModuleIndex(List)}.
   * <ul>
   *   <li>Then calls {@link MavenModuleAndPath#getPath()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link InternalModuleIndex#InternalModuleIndex(List)}
   */
  @Test
  @DisplayName("Test new InternalModuleIndex(List); then calls getPath()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void InternalModuleIndex.<init>(List)"})
  void testNewInternalModuleIndex_thenCallsGetPath() {
    // Arrange
    MavenModuleAndPath mavenModuleAndPath = mock(MavenModuleAndPath.class);
    when(mavenModuleAndPath.getPath()).thenReturn("Path");
    Builder artifactIdResult = MavenModuleStructure.builder().artifactId("42");
    Builder moduleRefResult = artifactIdResult.dependencies(new ArrayList<>()).groupId("42").moduleRef("Module Ref");
    MavenModuleStructure buildResult = moduleRefResult.modules(new ArrayList<>()).packaging("Packaging").build();
    when(mavenModuleAndPath.getStructure()).thenReturn(buildResult);

    ArrayList<MavenModuleAndPath> paths = new ArrayList<>();
    paths.add(mavenModuleAndPath);

    // Act
    new InternalModuleIndex(paths);

    // Assert
    verify(mavenModuleAndPath).getPath();
    verify(mavenModuleAndPath).getStructure();
  }

  /**
   * Test {@link InternalModuleIndex#dependencyPathsForModules(String[])}.
   * <ul>
   *   <li>Then return limit five collect toList Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link InternalModuleIndex#dependencyPathsForModules(String[])}
   */
  @Test
  @DisplayName("Test dependencyPathsForModules(String[]); then return limit five collect toList Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream InternalModuleIndex.dependencyPathsForModules(String[])"})
  void testDependencyPathsForModules_thenReturnLimitFiveCollectToListEmpty() {
    // Arrange and Act
    Stream<String> actualDependencyPathsForModulesResult = (new InternalModuleIndex(new ArrayList<>()))
        .dependencyPathsForModules();

    // Assert
    assertTrue(actualDependencyPathsForModulesResult.limit(5).collect(Collectors.toList()).isEmpty());
  }

  /**
   * Test {@link InternalModuleIndex#dependencyPathsForModules(String[])}.
   * <ul>
   *   <li>When {@code Paths}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link InternalModuleIndex#dependencyPathsForModules(String[])}
   */
  @Test
  @DisplayName("Test dependencyPathsForModules(String[]); when 'Paths'; then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream InternalModuleIndex.dependencyPathsForModules(String[])"})
  void testDependencyPathsForModules_whenPaths_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> (new InternalModuleIndex(new ArrayList<>())).dependencyPathsForModules("Paths"));
  }

  /**
   * Test {@link InternalModuleIndex#dependencyPathsForModulesExcludingUnexportedTransitives(String[])}.
   * <p>
   * Method under test: {@link InternalModuleIndex#dependencyPathsForModulesExcludingUnexportedTransitives(String[])}
   */
  @Test
  @DisplayName("Test dependencyPathsForModulesExcludingUnexportedTransitives(String[])")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream InternalModuleIndex.dependencyPathsForModulesExcludingUnexportedTransitives(String[])"})
  void testDependencyPathsForModulesExcludingUnexportedTransitives() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> (new InternalModuleIndex(new ArrayList<>()))
        .dependencyPathsForModulesExcludingUnexportedTransitives("Paths"));
  }

  /**
   * Test {@link InternalModuleIndex#dependencyPathsForModulesExcludingUnexportedTransitives(String[])}.
   * <p>
   * Method under test: {@link InternalModuleIndex#dependencyPathsForModulesExcludingUnexportedTransitives(String[])}
   */
  @Test
  @DisplayName("Test dependencyPathsForModulesExcludingUnexportedTransitives(String[])")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream InternalModuleIndex.dependencyPathsForModulesExcludingUnexportedTransitives(String[])"})
  void testDependencyPathsForModulesExcludingUnexportedTransitives2() {
    // Arrange and Act
    Stream<String> actualDependencyPathsForModulesExcludingUnexportedTransitivesResult = (new InternalModuleIndex(
        new ArrayList<>())).dependencyPathsForModulesExcludingUnexportedTransitives();

    // Assert
    assertTrue(actualDependencyPathsForModulesExcludingUnexportedTransitivesResult.limit(5)
        .collect(Collectors.toList())
        .isEmpty());
  }

  /**
   * Test {@link InternalModuleIndex#dependenciesForModules(List)}.
   * <ul>
   *   <li>Given {@code 42}.</li>
   *   <li>When {@link ArrayList#ArrayList()} add {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link InternalModuleIndex#dependenciesForModules(List)}
   */
  @Test
  @DisplayName("Test dependenciesForModules(List); given '42'; when ArrayList() add '42'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream InternalModuleIndex.dependenciesForModules(List)"})
  void testDependenciesForModules_given42_whenArrayListAdd42() {
    // Arrange
    InternalModuleIndex internalModuleIndex = new InternalModuleIndex(new ArrayList<>());

    ArrayList<String> paths = new ArrayList<>();
    paths.add("42");
    paths.add("foo");

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> internalModuleIndex.dependenciesForModules(paths));
  }

  /**
   * Test {@link InternalModuleIndex#dependenciesForModules(List)}.
   * <ul>
   *   <li>Given {@code foo}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link InternalModuleIndex#dependenciesForModules(List)}
   */
  @Test
  @DisplayName("Test dependenciesForModules(List); given 'foo'; then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream InternalModuleIndex.dependenciesForModules(List)"})
  void testDependenciesForModules_givenFoo_thenThrowIllegalArgumentException() {
    // Arrange
    InternalModuleIndex internalModuleIndex = new InternalModuleIndex(new ArrayList<>());

    ArrayList<String> paths = new ArrayList<>();
    paths.add("foo");

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> internalModuleIndex.dependenciesForModules(paths));
  }

  /**
   * Test {@link InternalModuleIndex#dependenciesForModules(List)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return limit five collect toList Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link InternalModuleIndex#dependenciesForModules(List)}
   */
  @Test
  @DisplayName("Test dependenciesForModules(List); when ArrayList(); then return limit five collect toList Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream InternalModuleIndex.dependenciesForModules(List)"})
  void testDependenciesForModules_whenArrayList_thenReturnLimitFiveCollectToListEmpty() {
    // Arrange
    InternalModuleIndex internalModuleIndex = new InternalModuleIndex(new ArrayList<>());

    // Act
    Stream<MavenModuleAndPath> actualDependenciesForModulesResult = internalModuleIndex
        .dependenciesForModules(new ArrayList<>());

    // Assert
    assertTrue(actualDependenciesForModulesResult.limit(5).collect(Collectors.toList()).isEmpty());
  }

  /**
   * Test {@link InternalModuleIndex#dependenciesForModulesExcludingUnexportedTransitives(List)}.
   * <p>
   * Method under test: {@link InternalModuleIndex#dependenciesForModulesExcludingUnexportedTransitives(List)}
   */
  @Test
  @DisplayName("Test dependenciesForModulesExcludingUnexportedTransitives(List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream InternalModuleIndex.dependenciesForModulesExcludingUnexportedTransitives(List)"})
  void testDependenciesForModulesExcludingUnexportedTransitives() {
    // Arrange
    InternalModuleIndex internalModuleIndex = new InternalModuleIndex(new ArrayList<>());

    // Act
    Stream<MavenModuleAndPath> actualDependenciesForModulesExcludingUnexportedTransitivesResult = internalModuleIndex
        .dependenciesForModulesExcludingUnexportedTransitives(new ArrayList<>());

    // Assert
    assertTrue(actualDependenciesForModulesExcludingUnexportedTransitivesResult.limit(5)
        .collect(Collectors.toList())
        .isEmpty());
  }

  /**
   * Test {@link InternalModuleIndex#dependenciesForModulesExcludingUnexportedTransitives(List)}.
   * <p>
   * Method under test: {@link InternalModuleIndex#dependenciesForModulesExcludingUnexportedTransitives(List)}
   */
  @Test
  @DisplayName("Test dependenciesForModulesExcludingUnexportedTransitives(List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream InternalModuleIndex.dependenciesForModulesExcludingUnexportedTransitives(List)"})
  void testDependenciesForModulesExcludingUnexportedTransitives2() {
    // Arrange
    InternalModuleIndex internalModuleIndex = new InternalModuleIndex(new ArrayList<>());

    ArrayList<String> paths = new ArrayList<>();
    paths.add("foo");

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> internalModuleIndex.dependenciesForModulesExcludingUnexportedTransitives(paths));
  }

  /**
   * Test {@link InternalModuleIndex#dependenciesForModulesExcludingUnexportedTransitives(List)}.
   * <ul>
   *   <li>Given {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link InternalModuleIndex#dependenciesForModulesExcludingUnexportedTransitives(List)}
   */
  @Test
  @DisplayName("Test dependenciesForModulesExcludingUnexportedTransitives(List); given '42'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream InternalModuleIndex.dependenciesForModulesExcludingUnexportedTransitives(List)"})
  void testDependenciesForModulesExcludingUnexportedTransitives_given42() {
    // Arrange
    InternalModuleIndex internalModuleIndex = new InternalModuleIndex(new ArrayList<>());

    ArrayList<String> paths = new ArrayList<>();
    paths.add("42");
    paths.add("foo");

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> internalModuleIndex.dependenciesForModulesExcludingUnexportedTransitives(paths));
  }

  /**
   * Test {@link InternalModuleIndex#lookupDependencies(Stream)}.
   * <p>
   * Method under test: {@link InternalModuleIndex#lookupDependencies(Stream)}
   */
  @Test
  @DisplayName("Test lookupDependencies(Stream)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream InternalModuleIndex.lookupDependencies(Stream)"})
  void testLookupDependencies() {
    // Arrange
    InternalModuleIndex internalModuleIndex = new InternalModuleIndex(new ArrayList<>());

    ArrayList<DependencyReference> dependencyReferenceList = new ArrayList<>();
    Stream<DependencyReference> dependencies = dependencyReferenceList.stream();

    // Act
    Stream<MavenModuleAndPath> actualLookupDependenciesResult = internalModuleIndex.lookupDependencies(dependencies);

    // Assert
    assertTrue(actualLookupDependenciesResult.limit(5).collect(Collectors.toList()).isEmpty());
  }

  /**
   * Test {@link InternalModuleIndex#streamByArtifactRef(ArtifactReference)}.
   * <ul>
   *   <li>Then return limit five collect toList Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link InternalModuleIndex#streamByArtifactRef(ArtifactReference)}
   */
  @Test
  @DisplayName("Test streamByArtifactRef(ArtifactReference); then return limit five collect toList Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream InternalModuleIndex.streamByArtifactRef(ArtifactReference)"})
  void testStreamByArtifactRef_thenReturnLimitFiveCollectToListEmpty() {
    // Arrange
    InternalModuleIndex internalModuleIndex = new InternalModuleIndex(new ArrayList<>());

    // Act
    Stream<MavenModuleAndPath> actualStreamByArtifactRefResult = internalModuleIndex
        .streamByArtifactRef(ArtifactReference.groupAndArtifact("42", "42"));

    // Assert
    assertTrue(actualStreamByArtifactRefResult.limit(5).collect(Collectors.toList()).isEmpty());
  }

  /**
   * Test {@link InternalModuleIndex#streamByArtifactRef(ArtifactReference)}.
   * <ul>
   *   <li>Then return limit five collect toList size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link InternalModuleIndex#streamByArtifactRef(ArtifactReference)}
   */
  @Test
  @DisplayName("Test streamByArtifactRef(ArtifactReference); then return limit five collect toList size is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream InternalModuleIndex.streamByArtifactRef(ArtifactReference)"})
  void testStreamByArtifactRef_thenReturnLimitFiveCollectToListSizeIsOne() {
    // Arrange
    ArrayList<MavenModuleAndPath> paths = new ArrayList<>();
    Builder artifactIdResult = MavenModuleStructure.builder().artifactId("42");
    Builder moduleRefResult = artifactIdResult.dependencies(new ArrayList<>()).groupId("42").moduleRef("Module Ref");
    MavenModuleStructure structure = moduleRefResult.modules(new ArrayList<>()).packaging("Packaging").build();
    MavenModuleAndPath rootResult = MavenModuleAndPath.root(structure);
    paths.add(rootResult);
    InternalModuleIndex internalModuleIndex = new InternalModuleIndex(paths);

    // Act
    Stream<MavenModuleAndPath> actualStreamByArtifactRefResult = internalModuleIndex
        .streamByArtifactRef(ArtifactReference.groupAndArtifact("42", "42"));

    // Assert
    List<MavenModuleAndPath> collectResult = actualStreamByArtifactRefResult.limit(5).collect(Collectors.toList());
    assertEquals(1, collectResult.size());
    assertSame(rootResult, collectResult.get(0));
  }

  /**
   * Test {@link InternalModuleIndex#moduleByArtifactRef(ArtifactReference)}.
   * <ul>
   *   <li>Then return not Present.</li>
   * </ul>
   * <p>
   * Method under test: {@link InternalModuleIndex#moduleByArtifactRef(ArtifactReference)}
   */
  @Test
  @DisplayName("Test moduleByArtifactRef(ArtifactReference); then return not Present")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Optional InternalModuleIndex.moduleByArtifactRef(ArtifactReference)"})
  void testModuleByArtifactRef_thenReturnNotPresent() {
    // Arrange
    InternalModuleIndex internalModuleIndex = new InternalModuleIndex(new ArrayList<>());

    // Act and Assert
    assertFalse(internalModuleIndex.moduleByArtifactRef(ArtifactReference.groupAndArtifact("42", "42")).isPresent());
  }

  /**
   * Test {@link InternalModuleIndex#moduleByArtifactRef(ArtifactReference)}.
   * <ul>
   *   <li>Then return Present.</li>
   * </ul>
   * <p>
   * Method under test: {@link InternalModuleIndex#moduleByArtifactRef(ArtifactReference)}
   */
  @Test
  @DisplayName("Test moduleByArtifactRef(ArtifactReference); then return Present")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Optional InternalModuleIndex.moduleByArtifactRef(ArtifactReference)"})
  void testModuleByArtifactRef_thenReturnPresent() {
    // Arrange
    ArrayList<MavenModuleAndPath> paths = new ArrayList<>();
    Builder artifactIdResult = MavenModuleStructure.builder().artifactId("42");
    Builder moduleRefResult = artifactIdResult.dependencies(new ArrayList<>()).groupId("42").moduleRef("Module Ref");
    MavenModuleStructure structure = moduleRefResult.modules(new ArrayList<>()).packaging("Packaging").build();
    MavenModuleAndPath rootResult = MavenModuleAndPath.root(structure);
    paths.add(rootResult);
    InternalModuleIndex internalModuleIndex = new InternalModuleIndex(paths);

    // Act
    Optional<MavenModuleAndPath> actualModuleByArtifactRefResult = internalModuleIndex
        .moduleByArtifactRef(ArtifactReference.groupAndArtifact("42", "42"));

    // Assert
    assertTrue(actualModuleByArtifactRefResult.isPresent());
    assertSame(rootResult, actualModuleByArtifactRefResult.get());
  }
}
