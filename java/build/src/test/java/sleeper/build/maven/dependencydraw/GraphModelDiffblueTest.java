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
import sleeper.build.maven.InternalModuleIndex;
import sleeper.build.maven.MavenModuleAndPath;
import sleeper.build.maven.MavenModuleStructure;
import sleeper.build.maven.MavenModuleStructure.Builder;

class GraphModelDiffblueTest {
  /**
   * Test {@link GraphModel#from(MavenModuleStructure)}.
   * <p>
   * Method under test: {@link GraphModel#from(MavenModuleStructure)}
   */
  @Test
  @DisplayName("Test from(MavenModuleStructure)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"GraphModel GraphModel.from(MavenModuleStructure)"})
  void testFrom() {
    // Arrange
    ArrayList<MavenModuleStructure> modules = new ArrayList<>();
    Builder artifactIdResult = MavenModuleStructure.builder().artifactId("groupId must not be null");
    Builder moduleRefResult = artifactIdResult.dependencies(new ArrayList<>()).groupId("42").moduleRef("Module Ref");
    MavenModuleStructure buildResult = moduleRefResult.modules(new ArrayList<>()).packaging("Packaging").build();
    modules.add(buildResult);
    Builder artifactIdResult2 = MavenModuleStructure.builder().artifactId("42");
    MavenModuleStructure structure = artifactIdResult2.dependencies(new ArrayList<>())
        .groupId("42")
        .moduleRef("Module Ref")
        .modules(modules)
        .packaging("Packaging")
        .build();

    // Act and Assert
    List<GraphNode> nodes = GraphModel.from(structure).getNodes();
    assertEquals(1, nodes.size());
    GraphNode getResult = nodes.get(0);
    ArtifactReference artifactReference = getResult.getArtifactReference();
    assertEquals("42", artifactReference.getArtifactId());
    MavenModuleAndPath resultModule = getResult.getModule();
    assertEquals("Module Ref", resultModule.getPath());
    assertEquals("Module Ref", resultModule.toString());
    assertEquals("Module Ref/pom.xml", resultModule.getPomPath());
    Stream<DependencyReference> dependenciesResult = resultModule.dependencies();
    assertTrue(dependenciesResult.limit(5).collect(Collectors.toList()).isEmpty());
    assertEquals(artifactReference, resultModule.getParentReference());
    assertSame(structure, resultModule.getStructure());
  }

  /**
   * Test {@link GraphModel#from(MavenModuleStructure)}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} stream.</li>
   *   <li>Then calls {@link MavenModuleStructure#allModules()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GraphModel#from(MavenModuleStructure)}
   */
  @Test
  @DisplayName("Test from(MavenModuleStructure); given ArrayList() stream; then calls allModules()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"GraphModel GraphModel.from(MavenModuleStructure)"})
  void testFrom_givenArrayListStream_thenCallsAllModules() {
    // Arrange
    MavenModuleStructure structure = mock(MavenModuleStructure.class);

    ArrayList<MavenModuleAndPath> mavenModuleAndPathList = new ArrayList<>();
    Stream<MavenModuleAndPath> streamResult = mavenModuleAndPathList.stream();
    when(structure.allModules()).thenReturn(streamResult);
    when(structure.indexInternalModules()).thenReturn(mock(InternalModuleIndex.class));

    // Act
    GraphModel actualFromResult = GraphModel.from(structure);

    // Assert
    verify(structure).allModules();
    verify(structure).indexInternalModules();
    List<GraphEdge> edges = actualFromResult.getEdges();
    assertTrue(edges.isEmpty());
    assertSame(edges, actualFromResult.getNodes());
  }

  /**
   * Test {@link GraphModel#from(MavenModuleStructure)}.
   * <ul>
   *   <li>Then return Edges Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link GraphModel#from(MavenModuleStructure)}
   */
  @Test
  @DisplayName("Test from(MavenModuleStructure); then return Edges Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"GraphModel GraphModel.from(MavenModuleStructure)"})
  void testFrom_thenReturnEdgesEmpty() {
    // Arrange
    Builder artifactIdResult = MavenModuleStructure.builder().artifactId("42");
    Builder moduleRefResult = artifactIdResult.dependencies(new ArrayList<>()).groupId("42").moduleRef("Module Ref");
    MavenModuleStructure structure = moduleRefResult.modules(new ArrayList<>()).packaging("pom").build();

    // Act
    GraphModel actualFromResult = GraphModel.from(structure);

    // Assert
    List<GraphEdge> edges = actualFromResult.getEdges();
    assertTrue(edges.isEmpty());
    assertSame(edges, actualFromResult.getNodes());
  }

  /**
   * Test {@link GraphModel#from(MavenModuleStructure)}.
   * <ul>
   *   <li>Then return Nodes size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link GraphModel#from(MavenModuleStructure)}
   */
  @Test
  @DisplayName("Test from(MavenModuleStructure); then return Nodes size is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"GraphModel GraphModel.from(MavenModuleStructure)"})
  void testFrom_thenReturnNodesSizeIsOne() {
    // Arrange
    Builder artifactIdResult = MavenModuleStructure.builder().artifactId("42");
    Builder moduleRefResult = artifactIdResult.dependencies(new ArrayList<>()).groupId("42").moduleRef("Module Ref");
    MavenModuleStructure structure = moduleRefResult.modules(new ArrayList<>()).packaging("Packaging").build();

    // Act and Assert
    List<GraphNode> nodes = GraphModel.from(structure).getNodes();
    assertEquals(1, nodes.size());
    GraphNode getResult = nodes.get(0);
    ArtifactReference artifactReference = getResult.getArtifactReference();
    assertEquals("42", artifactReference.getArtifactId());
    MavenModuleAndPath resultModule = getResult.getModule();
    assertEquals("Module Ref", resultModule.getPath());
    assertEquals("Module Ref", resultModule.toString());
    assertEquals("Module Ref/pom.xml", resultModule.getPomPath());
    Stream<DependencyReference> dependenciesResult = resultModule.dependencies();
    assertTrue(dependenciesResult.limit(5).collect(Collectors.toList()).isEmpty());
    assertEquals(artifactReference, resultModule.getParentReference());
    assertSame(structure, resultModule.getStructure());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link GraphModel#getEdges()}
   *   <li>{@link GraphModel#getNodes()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List GraphModel.getEdges()", "List GraphModel.getNodes()"})
  void testGettersAndSetters() {
    // Arrange
    Builder artifactIdResult = MavenModuleStructure.builder().artifactId("42");
    Builder moduleRefResult = artifactIdResult.dependencies(new ArrayList<>()).groupId("42").moduleRef("Module Ref");
    MavenModuleStructure structure = moduleRefResult.modules(new ArrayList<>()).packaging("Packaging").build();
    GraphModel fromResult = GraphModel.from(structure);

    // Act
    List<GraphEdge> actualEdges = fromResult.getEdges();
    List<GraphNode> actualNodes = fromResult.getNodes();

    // Assert
    assertEquals(1, actualNodes.size());
    GraphNode getResult = actualNodes.get(0);
    MavenModuleAndPath resultModule = getResult.getModule();
    assertEquals("42", resultModule.getParentReference().getArtifactId());
    assertEquals("42", getResult.getArtifactReference().getArtifactId());
    assertEquals("Module Ref", resultModule.getPath());
    assertEquals("Module Ref", resultModule.toString());
    assertEquals("Module Ref/pom.xml", resultModule.getPomPath());
    MavenModuleStructure structure2 = resultModule.getStructure();
    assertFalse(structure2.isPomPackage());
    Stream<DependencyReference> dependenciesResult = resultModule.dependencies();
    assertTrue(dependenciesResult.limit(5).collect(Collectors.toList()).isEmpty());
    Stream<DependencyReference> dependenciesResult2 = structure2.dependencies();
    assertTrue(dependenciesResult2.limit(5).collect(Collectors.toList()).isEmpty());
    assertTrue(actualEdges.isEmpty());
  }

  /**
   * Test {@link GraphModel#getNode(ArtifactReference)}.
   * <ul>
   *   <li>When groupAndArtifact {@code 42} and {@code 42}.</li>
   *   <li>Then return Module Path is {@code Module Ref}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GraphModel#getNode(ArtifactReference)}
   */
  @Test
  @DisplayName("Test getNode(ArtifactReference); when groupAndArtifact '42' and '42'; then return Module Path is 'Module Ref'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"GraphNode GraphModel.getNode(ArtifactReference)"})
  void testGetNode_whenGroupAndArtifact42And42_thenReturnModulePathIsModuleRef() {
    // Arrange
    Builder artifactIdResult = MavenModuleStructure.builder().artifactId("42");
    Builder moduleRefResult = artifactIdResult.dependencies(new ArrayList<>()).groupId("42").moduleRef("Module Ref");
    MavenModuleStructure structure = moduleRefResult.modules(new ArrayList<>()).packaging("Packaging").build();
    GraphModel fromResult = GraphModel.from(structure);
    ArtifactReference artifactReference = ArtifactReference.groupAndArtifact("42", "42");

    // Act
    GraphNode actualNode = fromResult.getNode(artifactReference);

    // Assert
    MavenModuleAndPath resultModule = actualNode.getModule();
    assertEquals("Module Ref", resultModule.getPath());
    assertEquals("Module Ref", resultModule.toString());
    assertEquals("Module Ref/pom.xml", resultModule.getPomPath());
    MavenModuleStructure structure2 = resultModule.getStructure();
    assertFalse(structure2.isPomPackage());
    Stream<DependencyReference> dependenciesResult = resultModule.dependencies();
    assertTrue(dependenciesResult.limit(5).collect(Collectors.toList()).isEmpty());
    Stream<DependencyReference> dependenciesResult2 = structure2.dependencies();
    assertTrue(dependenciesResult2.limit(5).collect(Collectors.toList()).isEmpty());
    assertEquals(artifactReference, resultModule.getParentReference());
    assertEquals(artifactReference, actualNode.getArtifactReference());
  }

  /**
   * Test {@link GraphModel#getNode(ArtifactReference)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GraphModel#getNode(ArtifactReference)}
   */
  @Test
  @DisplayName("Test getNode(ArtifactReference); when 'null'; then return 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"GraphNode GraphModel.getNode(ArtifactReference)"})
  void testGetNode_whenNull_thenReturnNull() {
    // Arrange
    Builder artifactIdResult = MavenModuleStructure.builder().artifactId("42");
    Builder moduleRefResult = artifactIdResult.dependencies(new ArrayList<>()).groupId("42").moduleRef("Module Ref");
    MavenModuleStructure structure = moduleRefResult.modules(new ArrayList<>()).packaging("Packaging").build();

    // Act and Assert
    assertNull(GraphModel.from(structure).getNode(null));
  }

  /**
   * Test {@link GraphModel#getEdgesTo(ArtifactReference)}.
   * <p>
   * Method under test: {@link GraphModel#getEdgesTo(ArtifactReference)}
   */
  @Test
  @DisplayName("Test getEdgesTo(ArtifactReference)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List GraphModel.getEdgesTo(ArtifactReference)"})
  void testGetEdgesTo() {
    // Arrange
    Builder artifactIdResult = MavenModuleStructure.builder().artifactId("42");
    Builder moduleRefResult = artifactIdResult.dependencies(new ArrayList<>()).groupId("42").moduleRef("Module Ref");
    MavenModuleStructure structure = moduleRefResult.modules(new ArrayList<>()).packaging("Packaging").build();
    GraphModel fromResult = GraphModel.from(structure);

    // Act and Assert
    assertTrue(fromResult.getEdgesTo(ArtifactReference.groupAndArtifact("42", "42")).isEmpty());
  }

  /**
   * Test {@link GraphModel#getEdgesFrom(ArtifactReference)}.
   * <p>
   * Method under test: {@link GraphModel#getEdgesFrom(ArtifactReference)}
   */
  @Test
  @DisplayName("Test getEdgesFrom(ArtifactReference)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List GraphModel.getEdgesFrom(ArtifactReference)"})
  void testGetEdgesFrom() {
    // Arrange
    Builder artifactIdResult = MavenModuleStructure.builder().artifactId("42");
    Builder moduleRefResult = artifactIdResult.dependencies(new ArrayList<>()).groupId("42").moduleRef("Module Ref");
    MavenModuleStructure structure = moduleRefResult.modules(new ArrayList<>()).packaging("Packaging").build();
    GraphModel fromResult = GraphModel.from(structure);

    // Act and Assert
    assertTrue(fromResult.getEdgesFrom(ArtifactReference.groupAndArtifact("42", "42")).isEmpty());
  }

  /**
   * Test {@link GraphModel#edgesTo(GraphNode)}.
   * <ul>
   *   <li>Given groupAndArtifact {@code 42} and {@code 42}.</li>
   *   <li>Then return limit five collect toList Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link GraphModel#edgesTo(GraphNode)}
   */
  @Test
  @DisplayName("Test edgesTo(GraphNode); given groupAndArtifact '42' and '42'; then return limit five collect toList Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream GraphModel.edgesTo(GraphNode)"})
  void testEdgesTo_givenGroupAndArtifact42And42_thenReturnLimitFiveCollectToListEmpty() {
    // Arrange
    Builder artifactIdResult = MavenModuleStructure.builder().artifactId("42");
    Builder moduleRefResult = artifactIdResult.dependencies(new ArrayList<>()).groupId("42").moduleRef("Module Ref");
    MavenModuleStructure structure = moduleRefResult.modules(new ArrayList<>()).packaging("Packaging").build();
    GraphModel fromResult = GraphModel.from(structure);
    MavenModuleAndPath resultModule = mock(MavenModuleAndPath.class);
    when(resultModule.artifactReference()).thenReturn(ArtifactReference.groupAndArtifact("42", "42"));

    // Act
    Stream<GraphEdge> actualEdgesToResult = fromResult.edgesTo(new GraphNode(resultModule));

    // Assert
    verify(resultModule).artifactReference();
    assertTrue(actualEdgesToResult.limit(5).collect(Collectors.toList()).isEmpty());
  }

  /**
   * Test {@link GraphModel#edgesFrom(GraphNode)}.
   * <ul>
   *   <li>Given groupAndArtifact {@code 42} and {@code 42}.</li>
   *   <li>Then return limit five collect toList Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link GraphModel#edgesFrom(GraphNode)}
   */
  @Test
  @DisplayName("Test edgesFrom(GraphNode); given groupAndArtifact '42' and '42'; then return limit five collect toList Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream GraphModel.edgesFrom(GraphNode)"})
  void testEdgesFrom_givenGroupAndArtifact42And42_thenReturnLimitFiveCollectToListEmpty() {
    // Arrange
    Builder artifactIdResult = MavenModuleStructure.builder().artifactId("42");
    Builder moduleRefResult = artifactIdResult.dependencies(new ArrayList<>()).groupId("42").moduleRef("Module Ref");
    MavenModuleStructure structure = moduleRefResult.modules(new ArrayList<>()).packaging("Packaging").build();
    GraphModel fromResult = GraphModel.from(structure);
    MavenModuleAndPath resultModule = mock(MavenModuleAndPath.class);
    when(resultModule.artifactReference()).thenReturn(ArtifactReference.groupAndArtifact("42", "42"));

    // Act
    Stream<GraphEdge> actualEdgesFromResult = fromResult.edgesFrom(new GraphNode(resultModule));

    // Assert
    verify(resultModule).artifactReference();
    assertTrue(actualEdgesFromResult.limit(5).collect(Collectors.toList()).isEmpty());
  }

  /**
   * Test {@link GraphModel#transitiveInternalDependencies(GraphNode)}.
   * <ul>
   *   <li>Then return limit five collect toList Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link GraphModel#transitiveInternalDependencies(GraphNode)}
   */
  @Test
  @DisplayName("Test transitiveInternalDependencies(GraphNode); then return limit five collect toList Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream GraphModel.transitiveInternalDependencies(GraphNode)"})
  void testTransitiveInternalDependencies_thenReturnLimitFiveCollectToListEmpty() {
    // Arrange
    Builder artifactIdResult = MavenModuleStructure.builder().artifactId("42");
    Builder moduleRefResult = artifactIdResult.dependencies(new ArrayList<>()).groupId("42").moduleRef("Module Ref");
    MavenModuleStructure structure = moduleRefResult.modules(new ArrayList<>()).packaging("Packaging").build();
    GraphModel fromResult = GraphModel.from(structure);
    MavenModuleAndPath resultModule = mock(MavenModuleAndPath.class);
    when(resultModule.artifactReference()).thenReturn(ArtifactReference.groupAndArtifact("42", "42"));

    // Act
    Stream<GraphEdge> actualTransitiveInternalDependenciesResult = fromResult
        .transitiveInternalDependencies(new GraphNode(resultModule));

    // Assert
    verify(resultModule).artifactReference();
    assertTrue(actualTransitiveInternalDependenciesResult.limit(5).collect(Collectors.toList()).isEmpty());
  }

  /**
   * Test {@link GraphModel#edgeByFromTo(ArtifactReference, ArtifactReference)}.
   * <p>
   * Method under test: {@link GraphModel#edgeByFromTo(ArtifactReference, ArtifactReference)}
   */
  @Test
  @DisplayName("Test edgeByFromTo(ArtifactReference, ArtifactReference)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.util.Optional GraphModel.edgeByFromTo(ArtifactReference, ArtifactReference)"})
  void testEdgeByFromTo() {
    // Arrange
    Builder artifactIdResult = MavenModuleStructure.builder().artifactId("42");
    Builder moduleRefResult = artifactIdResult.dependencies(new ArrayList<>()).groupId("42").moduleRef("Module Ref");
    MavenModuleStructure structure = moduleRefResult.modules(new ArrayList<>()).packaging("Packaging").build();
    GraphModel fromResult = GraphModel.from(structure);
    ArtifactReference from = ArtifactReference.groupAndArtifact("42", "42");

    // Act and Assert
    assertFalse(fromResult.edgeByFromTo(from, ArtifactReference.groupAndArtifact("42", "42")).isPresent());
  }
}
