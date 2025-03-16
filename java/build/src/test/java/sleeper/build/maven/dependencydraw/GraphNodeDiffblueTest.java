package sleeper.build.maven.dependencydraw;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.build.maven.ArtifactReference;
import sleeper.build.maven.MavenModuleAndPath;

class GraphNodeDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link GraphNode#GraphNode(MavenModuleAndPath)}
   *   <li>{@link GraphNode#getModule()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void GraphNode.<init>(MavenModuleAndPath)", "MavenModuleAndPath GraphNode.getModule()",
      "java.lang.String GraphNode.toString()"})
  void testGettersAndSetters() {
    // Arrange, Act and Assert
    assertNull((new GraphNode(null)).getModule());
  }

  /**
   * Test {@link GraphNode#getArtifactReference()}.
   * <p>
   * Method under test: {@link GraphNode#getArtifactReference()}
   */
  @Test
  @DisplayName("Test getArtifactReference()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"ArtifactReference GraphNode.getArtifactReference()"})
  void testGetArtifactReference() {
    // Arrange
    MavenModuleAndPath resultModule = mock(MavenModuleAndPath.class);
    ArtifactReference groupAndArtifactResult = ArtifactReference.groupAndArtifact("42", "42");
    when(resultModule.artifactReference()).thenReturn(groupAndArtifactResult);

    // Act
    ArtifactReference actualArtifactReference = (new GraphNode(resultModule)).getArtifactReference();

    // Assert
    verify(resultModule).artifactReference();
    assertEquals("42", actualArtifactReference.getArtifactId());
    assertSame(groupAndArtifactResult, actualArtifactReference);
  }
}
