package sleeper.build.maven.dependencydraw;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.build.maven.ArtifactReference;
import sleeper.build.maven.DependencyReference;
import sleeper.build.maven.MavenModuleAndPath;
import sleeper.build.maven.MavenModuleStructure;
import sleeper.build.maven.MavenModuleStructure.Builder;

class GraphEdgeDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link GraphEdge#GraphEdge(MavenModuleAndPath, MavenModuleAndPath)}
   *   <li>{@link GraphEdge#toString()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void GraphEdge.<init>(MavenModuleAndPath, MavenModuleAndPath)",
      "java.lang.String GraphEdge.toString()"})
  void testGettersAndSetters() {
    // Arrange, Act and Assert
    assertEquals("null > null", (new GraphEdge(null, null)).toString());
  }

  /**
   * Test {@link GraphEdge#getFrom(GraphModel)}.
   * <ul>
   *   <li>Given {@link MavenModuleAndPath} {@link MavenModuleAndPath#artifactReference()} return {@code null}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GraphEdge#getFrom(GraphModel)}
   */
  @Test
  @DisplayName("Test getFrom(GraphModel); given MavenModuleAndPath artifactReference() return 'null'; then return 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"GraphNode GraphEdge.getFrom(GraphModel)"})
  void testGetFrom_givenMavenModuleAndPathArtifactReferenceReturnNull_thenReturnNull() {
    // Arrange
    MavenModuleAndPath from = mock(MavenModuleAndPath.class);
    when(from.artifactReference()).thenReturn(null);
    GraphEdge graphEdge = new GraphEdge(from, mock(MavenModuleAndPath.class));
    Builder artifactIdResult = MavenModuleStructure.builder().artifactId("42");
    Builder moduleRefResult = artifactIdResult.dependencies(new ArrayList<>()).groupId("42").moduleRef("Module Ref");
    MavenModuleStructure structure = moduleRefResult.modules(new ArrayList<>()).packaging("Packaging").build();

    // Act
    GraphNode actualFrom = graphEdge.getFrom(GraphModel.from(structure));

    // Assert
    verify(from).artifactReference();
    assertNull(actualFrom);
  }

  /**
   * Test {@link GraphEdge#getFrom(GraphModel)}.
   * <ul>
   *   <li>Then return Module ParentReference ArtifactId is {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GraphEdge#getFrom(GraphModel)}
   */
  @Test
  @DisplayName("Test getFrom(GraphModel); then return Module ParentReference ArtifactId is '42'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"GraphNode GraphEdge.getFrom(GraphModel)"})
  void testGetFrom_thenReturnModuleParentReferenceArtifactIdIs42() {
    // Arrange
    MavenModuleAndPath from = mock(MavenModuleAndPath.class);
    when(from.artifactReference()).thenReturn(ArtifactReference.groupAndArtifact("42", "42"));
    GraphEdge graphEdge = new GraphEdge(from, mock(MavenModuleAndPath.class));
    Builder artifactIdResult = MavenModuleStructure.builder().artifactId("42");
    Builder moduleRefResult = artifactIdResult.dependencies(new ArrayList<>()).groupId("42").moduleRef("Module Ref");
    MavenModuleStructure structure = moduleRefResult.modules(new ArrayList<>()).packaging("Packaging").build();

    // Act
    GraphNode actualFrom = graphEdge.getFrom(GraphModel.from(structure));

    // Assert
    verify(from).artifactReference();
    MavenModuleAndPath resultModule = actualFrom.getModule();
    assertEquals("42", resultModule.getParentReference().getArtifactId());
    assertEquals("42", actualFrom.getArtifactReference().getArtifactId());
    assertEquals("Module Ref", resultModule.getPath());
    assertEquals("Module Ref", resultModule.toString());
    assertEquals("Module Ref/pom.xml", resultModule.getPomPath());
    MavenModuleStructure structure2 = resultModule.getStructure();
    assertFalse(structure2.isPomPackage());
    Stream<DependencyReference> dependenciesResult = resultModule.dependencies();
    assertTrue(dependenciesResult.limit(5).collect(Collectors.toList()).isEmpty());
    Stream<DependencyReference> dependenciesResult2 = structure2.dependencies();
    assertTrue(dependenciesResult2.limit(5).collect(Collectors.toList()).isEmpty());
  }

  /**
   * Test {@link GraphEdge#getTo(GraphModel)}.
   * <ul>
   *   <li>Given {@link MavenModuleAndPath} {@link MavenModuleAndPath#artifactReference()} return {@code null}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GraphEdge#getTo(GraphModel)}
   */
  @Test
  @DisplayName("Test getTo(GraphModel); given MavenModuleAndPath artifactReference() return 'null'; then return 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"GraphNode GraphEdge.getTo(GraphModel)"})
  void testGetTo_givenMavenModuleAndPathArtifactReferenceReturnNull_thenReturnNull() {
    // Arrange
    MavenModuleAndPath resultTo = mock(MavenModuleAndPath.class);
    when(resultTo.artifactReference()).thenReturn(null);
    GraphEdge graphEdge = new GraphEdge(mock(MavenModuleAndPath.class), resultTo);
    Builder artifactIdResult = MavenModuleStructure.builder().artifactId("42");
    Builder moduleRefResult = artifactIdResult.dependencies(new ArrayList<>()).groupId("42").moduleRef("Module Ref");
    MavenModuleStructure structure = moduleRefResult.modules(new ArrayList<>()).packaging("Packaging").build();

    // Act
    GraphNode actualTo = graphEdge.getTo(GraphModel.from(structure));

    // Assert
    verify(resultTo).artifactReference();
    assertNull(actualTo);
  }

  /**
   * Test {@link GraphEdge#getTo(GraphModel)}.
   * <ul>
   *   <li>Then return Module ParentReference ArtifactId is {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GraphEdge#getTo(GraphModel)}
   */
  @Test
  @DisplayName("Test getTo(GraphModel); then return Module ParentReference ArtifactId is '42'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"GraphNode GraphEdge.getTo(GraphModel)"})
  void testGetTo_thenReturnModuleParentReferenceArtifactIdIs42() {
    // Arrange
    MavenModuleAndPath resultTo = mock(MavenModuleAndPath.class);
    when(resultTo.artifactReference()).thenReturn(ArtifactReference.groupAndArtifact("42", "42"));
    GraphEdge graphEdge = new GraphEdge(mock(MavenModuleAndPath.class), resultTo);
    Builder artifactIdResult = MavenModuleStructure.builder().artifactId("42");
    Builder moduleRefResult = artifactIdResult.dependencies(new ArrayList<>()).groupId("42").moduleRef("Module Ref");
    MavenModuleStructure structure = moduleRefResult.modules(new ArrayList<>()).packaging("Packaging").build();

    // Act
    GraphNode actualTo = graphEdge.getTo(GraphModel.from(structure));

    // Assert
    verify(resultTo).artifactReference();
    MavenModuleAndPath resultModule = actualTo.getModule();
    assertEquals("42", resultModule.getParentReference().getArtifactId());
    assertEquals("42", actualTo.getArtifactReference().getArtifactId());
    assertEquals("Module Ref", resultModule.getPath());
    assertEquals("Module Ref", resultModule.toString());
    assertEquals("Module Ref/pom.xml", resultModule.getPomPath());
    MavenModuleStructure structure2 = resultModule.getStructure();
    assertFalse(structure2.isPomPackage());
    Stream<DependencyReference> dependenciesResult = resultModule.dependencies();
    assertTrue(dependenciesResult.limit(5).collect(Collectors.toList()).isEmpty());
    Stream<DependencyReference> dependenciesResult2 = structure2.dependencies();
    assertTrue(dependenciesResult2.limit(5).collect(Collectors.toList()).isEmpty());
  }

  /**
   * Test {@link GraphEdge#getFromRef()}.
   * <p>
   * Method under test: {@link GraphEdge#getFromRef()}
   */
  @Test
  @DisplayName("Test getFromRef()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"ArtifactReference GraphEdge.getFromRef()"})
  void testGetFromRef() {
    // Arrange
    MavenModuleAndPath from = mock(MavenModuleAndPath.class);
    ArtifactReference groupAndArtifactResult = ArtifactReference.groupAndArtifact("42", "42");
    when(from.artifactReference()).thenReturn(groupAndArtifactResult);

    // Act
    ArtifactReference actualFromRef = (new GraphEdge(from, mock(MavenModuleAndPath.class))).getFromRef();

    // Assert
    verify(from).artifactReference();
    assertEquals("42", actualFromRef.getArtifactId());
    assertSame(groupAndArtifactResult, actualFromRef);
  }

  /**
   * Test {@link GraphEdge#getToRef()}.
   * <p>
   * Method under test: {@link GraphEdge#getToRef()}
   */
  @Test
  @DisplayName("Test getToRef()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"ArtifactReference GraphEdge.getToRef()"})
  void testGetToRef() {
    // Arrange
    MavenModuleAndPath resultTo = mock(MavenModuleAndPath.class);
    ArtifactReference groupAndArtifactResult = ArtifactReference.groupAndArtifact("42", "42");
    when(resultTo.artifactReference()).thenReturn(groupAndArtifactResult);

    // Act
    ArtifactReference actualToRef = (new GraphEdge(mock(MavenModuleAndPath.class), resultTo)).getToRef();

    // Assert
    verify(resultTo).artifactReference();
    assertEquals("42", actualToRef.getArtifactId());
    assertSame(groupAndArtifactResult, actualToRef);
  }

  /**
   * Test {@link GraphEdge#thisAndTransitives(GraphModel)}.
   * <ul>
   *   <li>Then return limit five collect toList size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link GraphEdge#thisAndTransitives(GraphModel)}
   */
  @Test
  @DisplayName("Test thisAndTransitives(GraphModel); then return limit five collect toList size is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream GraphEdge.thisAndTransitives(GraphModel)"})
  void testThisAndTransitives_thenReturnLimitFiveCollectToListSizeIsOne() {
    // Arrange
    MavenModuleAndPath resultTo = mock(MavenModuleAndPath.class);
    when(resultTo.artifactReference()).thenReturn(ArtifactReference.groupAndArtifact("42", "42"));
    GraphEdge graphEdge = new GraphEdge(mock(MavenModuleAndPath.class), resultTo);
    Builder artifactIdResult = MavenModuleStructure.builder().artifactId("42");
    Builder moduleRefResult = artifactIdResult.dependencies(new ArrayList<>()).groupId("42").moduleRef("Module Ref");
    MavenModuleStructure structure = moduleRefResult.modules(new ArrayList<>()).packaging("Packaging").build();

    // Act
    Stream<GraphEdge> actualThisAndTransitivesResult = graphEdge.thisAndTransitives(GraphModel.from(structure));

    // Assert
    verify(resultTo).artifactReference();
    List<GraphEdge> collectResult = actualThisAndTransitivesResult.limit(5).collect(Collectors.toList());
    assertEquals(1, collectResult.size());
    assertSame(graphEdge, collectResult.get(0));
  }
}
