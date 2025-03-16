package sleeper.build.maven.dependencydraw;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.Collection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.build.maven.ArtifactReference;
import sleeper.build.maven.MavenModuleAndPath;
import sleeper.build.maven.MavenModuleStructure;
import sleeper.build.maven.MavenModuleStructure.Builder;

class PickedNodeEdgesDiffblueTest {
  /**
   * Test {@link PickedNodeEdges#PickedNodeEdges()}.
   * <p>
   * Method under test: {@link PickedNodeEdges#PickedNodeEdges()}
   */
  @Test
  @DisplayName("Test new PickedNodeEdges()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void PickedNodeEdges.<init>()"})
  void testNewPickedNodeEdges() {
    // Arrange and Act
    PickedNodeEdges actualPickedNodeEdges = new PickedNodeEdges();

    // Assert
    assertFalse(actualPickedNodeEdges.isDirectDependency(null));
    assertFalse(actualPickedNodeEdges.isDirectDependent(null));
    assertFalse(actualPickedNodeEdges.isTransitiveDependency(null));
  }

  /**
   * Test {@link PickedNodeEdges#PickedNodeEdges(GraphModel, Collection)}.
   * <ul>
   *   <li>Then calls {@link MavenModuleAndPath#artifactReference()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PickedNodeEdges#PickedNodeEdges(GraphModel, Collection)}
   */
  @Test
  @DisplayName("Test new PickedNodeEdges(GraphModel, Collection); then calls artifactReference()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void PickedNodeEdges.<init>(GraphModel, Collection)"})
  void testNewPickedNodeEdges_thenCallsArtifactReference() {
    // Arrange
    Builder artifactIdResult = MavenModuleStructure.builder().artifactId("42");
    Builder moduleRefResult = artifactIdResult.dependencies(new ArrayList<>()).groupId("42").moduleRef("Module Ref");
    MavenModuleStructure structure = moduleRefResult.modules(new ArrayList<>()).packaging("Packaging").build();
    GraphModel model = GraphModel.from(structure);
    MavenModuleAndPath resultModule = mock(MavenModuleAndPath.class);
    when(resultModule.artifactReference()).thenReturn(ArtifactReference.groupAndArtifact("42", "42"));
    GraphNode graphNode = new GraphNode(resultModule);

    ArrayList<GraphNode> picked = new ArrayList<>();
    picked.add(graphNode);

    // Act
    new PickedNodeEdges(model, picked);

    // Assert
    verify(resultModule, atLeast(1)).artifactReference();
  }

  /**
   * Test {@link PickedNodeEdges#PickedNodeEdges(GraphModel, Collection)}.
   * <ul>
   *   <li>Then calls {@link MavenModuleAndPath#artifactReference()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PickedNodeEdges#PickedNodeEdges(GraphModel, Collection)}
   */
  @Test
  @DisplayName("Test new PickedNodeEdges(GraphModel, Collection); then calls artifactReference()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void PickedNodeEdges.<init>(GraphModel, Collection)"})
  void testNewPickedNodeEdges_thenCallsArtifactReference2() {
    // Arrange
    Builder artifactIdResult = MavenModuleStructure.builder().artifactId("42");
    Builder moduleRefResult = artifactIdResult.dependencies(new ArrayList<>()).groupId("42").moduleRef("Module Ref");
    MavenModuleStructure structure = moduleRefResult.modules(new ArrayList<>()).packaging("Packaging").build();
    GraphModel model = GraphModel.from(structure);
    MavenModuleAndPath resultModule = mock(MavenModuleAndPath.class);
    when(resultModule.artifactReference()).thenReturn(ArtifactReference.groupAndArtifact("42", "42"));
    GraphNode graphNode = new GraphNode(resultModule);
    MavenModuleAndPath resultModule2 = mock(MavenModuleAndPath.class);
    when(resultModule2.artifactReference()).thenReturn(ArtifactReference.groupAndArtifact("42", "42"));
    GraphNode graphNode2 = new GraphNode(resultModule2);

    ArrayList<GraphNode> picked = new ArrayList<>();
    picked.add(graphNode2);
    picked.add(graphNode);

    // Act
    new PickedNodeEdges(model, picked);

    // Assert
    verify(resultModule2, atLeast(1)).artifactReference();
    verify(resultModule, atLeast(1)).artifactReference();
  }

  /**
   * Test {@link PickedNodeEdges#isDirectDependency(GraphEdge)}.
   * <ul>
   *   <li>Given {@link PickedNodeEdges#PickedNodeEdges()}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PickedNodeEdges#isDirectDependency(GraphEdge)}
   */
  @Test
  @DisplayName("Test isDirectDependency(GraphEdge); given PickedNodeEdges(); then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean PickedNodeEdges.isDirectDependency(GraphEdge)"})
  void testIsDirectDependency_givenPickedNodeEdges_thenReturnFalse() {
    // Arrange
    PickedNodeEdges pickedNodeEdges = new PickedNodeEdges();

    // Act and Assert
    assertFalse(pickedNodeEdges
        .isDirectDependency(new GraphEdge(mock(MavenModuleAndPath.class), mock(MavenModuleAndPath.class))));
  }

  /**
   * Test {@link PickedNodeEdges#isTransitiveDependency(GraphEdge)}.
   * <ul>
   *   <li>Given {@link PickedNodeEdges#PickedNodeEdges()}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PickedNodeEdges#isTransitiveDependency(GraphEdge)}
   */
  @Test
  @DisplayName("Test isTransitiveDependency(GraphEdge); given PickedNodeEdges(); then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean PickedNodeEdges.isTransitiveDependency(GraphEdge)"})
  void testIsTransitiveDependency_givenPickedNodeEdges_thenReturnFalse() {
    // Arrange
    PickedNodeEdges pickedNodeEdges = new PickedNodeEdges();

    // Act and Assert
    assertFalse(pickedNodeEdges
        .isTransitiveDependency(new GraphEdge(mock(MavenModuleAndPath.class), mock(MavenModuleAndPath.class))));
  }

  /**
   * Test {@link PickedNodeEdges#isDirectDependent(GraphEdge)}.
   * <ul>
   *   <li>Given {@link PickedNodeEdges#PickedNodeEdges()}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PickedNodeEdges#isDirectDependent(GraphEdge)}
   */
  @Test
  @DisplayName("Test isDirectDependent(GraphEdge); given PickedNodeEdges(); then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean PickedNodeEdges.isDirectDependent(GraphEdge)"})
  void testIsDirectDependent_givenPickedNodeEdges_thenReturnFalse() {
    // Arrange
    PickedNodeEdges pickedNodeEdges = new PickedNodeEdges();

    // Act and Assert
    assertFalse(pickedNodeEdges
        .isDirectDependent(new GraphEdge(mock(MavenModuleAndPath.class), mock(MavenModuleAndPath.class))));
  }
}
