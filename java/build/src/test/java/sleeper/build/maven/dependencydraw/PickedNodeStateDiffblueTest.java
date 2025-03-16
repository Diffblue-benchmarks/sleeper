package sleeper.build.maven.dependencydraw;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.awt.Color;
import java.awt.Paint;
import java.awt.color.ColorSpace;
import java.awt.color.ICC_ColorSpace;
import java.util.ArrayList;
import java.util.Collection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.build.maven.ArtifactReference;
import sleeper.build.maven.MavenModuleAndPath;
import sleeper.build.maven.MavenModuleStructure;
import sleeper.build.maven.MavenModuleStructure.Builder;

class PickedNodeStateDiffblueTest {
  /**
   * Test {@link PickedNodeState#PickedNodeState(GraphModel)}.
   * <p>
   * Method under test: {@link PickedNodeState#PickedNodeState(GraphModel)}
   */
  @Test
  @DisplayName("Test new PickedNodeState(GraphModel)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void PickedNodeState.<init>(GraphModel)"})
  void testNewPickedNodeState() {
    // Arrange
    Builder artifactIdResult = MavenModuleStructure.builder().artifactId("42");
    Builder moduleRefResult = artifactIdResult.dependencies(new ArrayList<>()).groupId("42").moduleRef("Module Ref");
    MavenModuleStructure structure = moduleRefResult.modules(new ArrayList<>()).packaging("Packaging").build();

    // Act
    PickedNodeState actualPickedNodeState = new PickedNodeState(GraphModel.from(structure));

    // Assert
    Paint calculateArrowColorResult = actualPickedNodeState.calculateArrowColor(null, true);
    assertTrue(calculateArrowColorResult instanceof Color);
    Color calculateEdgeColorResult = actualPickedNodeState.calculateEdgeColor(null, true);
    ColorSpace colorSpace = calculateEdgeColorResult.getColorSpace();
    assertTrue(colorSpace instanceof ICC_ColorSpace);
    assertEquals(-4144960, calculateEdgeColorResult.getRGB());
    assertEquals(0, ((Color) calculateArrowColorResult).getAlpha());
    assertEquals(1, calculateEdgeColorResult.getTransparency());
    assertEquals(16777215, ((Color) calculateArrowColorResult).getRGB());
    assertEquals(192, calculateEdgeColorResult.getBlue());
    assertEquals(192, calculateEdgeColorResult.getGreen());
    assertEquals(192, calculateEdgeColorResult.getRed());
    assertEquals(2, calculateArrowColorResult.getTransparency());
    assertEquals(255, calculateEdgeColorResult.getAlpha());
    assertEquals(255, ((Color) calculateArrowColorResult).getBlue());
    assertEquals(255, ((Color) calculateArrowColorResult).getGreen());
    assertEquals(255, ((Color) calculateArrowColorResult).getRed());
    assertEquals(calculateArrowColorResult, ((Color) calculateArrowColorResult).brighter());
    assertSame(colorSpace, ((Color) calculateArrowColorResult).getColorSpace());
  }

  /**
   * Test {@link PickedNodeState#updatePicked(Collection)}.
   * <ul>
   *   <li>Then calls {@link MavenModuleAndPath#artifactReference()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PickedNodeState#updatePicked(Collection)}
   */
  @Test
  @DisplayName("Test updatePicked(Collection); then calls artifactReference()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void PickedNodeState.updatePicked(Collection)"})
  void testUpdatePicked_thenCallsArtifactReference() {
    // Arrange
    Builder artifactIdResult = MavenModuleStructure.builder().artifactId("42");
    Builder moduleRefResult = artifactIdResult.dependencies(new ArrayList<>()).groupId("42").moduleRef("Module Ref");
    MavenModuleStructure structure = moduleRefResult.modules(new ArrayList<>()).packaging("Packaging").build();
    PickedNodeState pickedNodeState = new PickedNodeState(GraphModel.from(structure));
    MavenModuleAndPath resultModule = mock(MavenModuleAndPath.class);
    when(resultModule.artifactReference()).thenReturn(ArtifactReference.groupAndArtifact("42", "42"));
    GraphNode graphNode = new GraphNode(resultModule);

    ArrayList<GraphNode> picked = new ArrayList<>();
    picked.add(graphNode);

    // Act
    pickedNodeState.updatePicked(picked);

    // Assert
    verify(resultModule, atLeast(1)).artifactReference();
  }

  /**
   * Test {@link PickedNodeState#updatePicked(Collection)}.
   * <ul>
   *   <li>Then calls {@link MavenModuleAndPath#artifactReference()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PickedNodeState#updatePicked(Collection)}
   */
  @Test
  @DisplayName("Test updatePicked(Collection); then calls artifactReference()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void PickedNodeState.updatePicked(Collection)"})
  void testUpdatePicked_thenCallsArtifactReference2() {
    // Arrange
    Builder artifactIdResult = MavenModuleStructure.builder().artifactId("42");
    Builder moduleRefResult = artifactIdResult.dependencies(new ArrayList<>()).groupId("42").moduleRef("Module Ref");
    MavenModuleStructure structure = moduleRefResult.modules(new ArrayList<>()).packaging("Packaging").build();
    PickedNodeState pickedNodeState = new PickedNodeState(GraphModel.from(structure));
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
    pickedNodeState.updatePicked(picked);

    // Assert
    verify(resultModule2, atLeast(1)).artifactReference();
    verify(resultModule, atLeast(1)).artifactReference();
  }

  /**
   * Test {@link PickedNodeState#calculateEdgeColor(GraphEdge, boolean)}.
   * <ul>
   *   <li>When {@code false}.</li>
   *   <li>Then return {@link Color#black}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PickedNodeState#calculateEdgeColor(GraphEdge, boolean)}
   */
  @Test
  @DisplayName("Test calculateEdgeColor(GraphEdge, boolean); when 'false'; then return black")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Color PickedNodeState.calculateEdgeColor(GraphEdge, boolean)"})
  void testCalculateEdgeColor_whenFalse_thenReturnBlack() {
    // Arrange
    Builder artifactIdResult = MavenModuleStructure.builder().artifactId("42");
    Builder moduleRefResult = artifactIdResult.dependencies(new ArrayList<>()).groupId("42").moduleRef("Module Ref");
    MavenModuleStructure structure = moduleRefResult.modules(new ArrayList<>()).packaging("Packaging").build();
    PickedNodeState pickedNodeState = new PickedNodeState(GraphModel.from(structure));

    // Act
    Color actualCalculateEdgeColorResult = pickedNodeState
        .calculateEdgeColor(new GraphEdge(mock(MavenModuleAndPath.class), mock(MavenModuleAndPath.class)), false);

    // Assert
    assertSame(actualCalculateEdgeColorResult.black, actualCalculateEdgeColorResult);
  }

  /**
   * Test {@link PickedNodeState#calculateEdgeColor(GraphEdge, boolean)}.
   * <ul>
   *   <li>When {@code true}.</li>
   *   <li>Then return {@link Color#lightGray}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PickedNodeState#calculateEdgeColor(GraphEdge, boolean)}
   */
  @Test
  @DisplayName("Test calculateEdgeColor(GraphEdge, boolean); when 'true'; then return lightGray")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Color PickedNodeState.calculateEdgeColor(GraphEdge, boolean)"})
  void testCalculateEdgeColor_whenTrue_thenReturnLightGray() {
    // Arrange
    Builder artifactIdResult = MavenModuleStructure.builder().artifactId("42");
    Builder moduleRefResult = artifactIdResult.dependencies(new ArrayList<>()).groupId("42").moduleRef("Module Ref");
    MavenModuleStructure structure = moduleRefResult.modules(new ArrayList<>()).packaging("Packaging").build();
    PickedNodeState pickedNodeState = new PickedNodeState(GraphModel.from(structure));

    // Act
    Color actualCalculateEdgeColorResult = pickedNodeState
        .calculateEdgeColor(new GraphEdge(mock(MavenModuleAndPath.class), mock(MavenModuleAndPath.class)), true);

    // Assert
    assertSame(actualCalculateEdgeColorResult.lightGray, actualCalculateEdgeColorResult);
  }

  /**
   * Test {@link PickedNodeState#calculateArrowColor(GraphEdge, boolean)}.
   * <ul>
   *   <li>When {@code false}.</li>
   *   <li>Then return {@link Color#black}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PickedNodeState#calculateArrowColor(GraphEdge, boolean)}
   */
  @Test
  @DisplayName("Test calculateArrowColor(GraphEdge, boolean); when 'false'; then return black")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Paint PickedNodeState.calculateArrowColor(GraphEdge, boolean)"})
  void testCalculateArrowColor_whenFalse_thenReturnBlack() {
    // Arrange
    Builder artifactIdResult = MavenModuleStructure.builder().artifactId("42");
    Builder moduleRefResult = artifactIdResult.dependencies(new ArrayList<>()).groupId("42").moduleRef("Module Ref");
    MavenModuleStructure structure = moduleRefResult.modules(new ArrayList<>()).packaging("Packaging").build();
    PickedNodeState pickedNodeState = new PickedNodeState(GraphModel.from(structure));

    // Act
    Paint actualCalculateArrowColorResult = pickedNodeState
        .calculateArrowColor(new GraphEdge(mock(MavenModuleAndPath.class), mock(MavenModuleAndPath.class)), false);

    // Assert
    assertSame(((Color) actualCalculateArrowColorResult).black, actualCalculateArrowColorResult);
  }

  /**
   * Test {@link PickedNodeState#calculateArrowColor(GraphEdge, boolean)}.
   * <ul>
   *   <li>When {@code true}.</li>
   *   <li>Then return {@link Color}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PickedNodeState#calculateArrowColor(GraphEdge, boolean)}
   */
  @Test
  @DisplayName("Test calculateArrowColor(GraphEdge, boolean); when 'true'; then return Color")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Paint PickedNodeState.calculateArrowColor(GraphEdge, boolean)"})
  void testCalculateArrowColor_whenTrue_thenReturnColor() {
    // Arrange
    Builder artifactIdResult = MavenModuleStructure.builder().artifactId("42");
    Builder moduleRefResult = artifactIdResult.dependencies(new ArrayList<>()).groupId("42").moduleRef("Module Ref");
    MavenModuleStructure structure = moduleRefResult.modules(new ArrayList<>()).packaging("Packaging").build();
    PickedNodeState pickedNodeState = new PickedNodeState(GraphModel.from(structure));

    // Act
    Paint actualCalculateArrowColorResult = pickedNodeState
        .calculateArrowColor(new GraphEdge(mock(MavenModuleAndPath.class), mock(MavenModuleAndPath.class)), true);

    // Assert
    assertTrue(actualCalculateArrowColorResult instanceof Color);
    assertEquals(0, ((Color) actualCalculateArrowColorResult).getAlpha());
    assertEquals(16777215, ((Color) actualCalculateArrowColorResult).getRGB());
    assertEquals(2, actualCalculateArrowColorResult.getTransparency());
    assertEquals(255, ((Color) actualCalculateArrowColorResult).getBlue());
    assertEquals(255, ((Color) actualCalculateArrowColorResult).getGreen());
    assertEquals(255, ((Color) actualCalculateArrowColorResult).getRed());
    assertEquals(actualCalculateArrowColorResult, ((Color) actualCalculateArrowColorResult).brighter());
  }
}
