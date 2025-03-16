package sleeper.build.status;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.build.github.GitHubHead;
import sleeper.build.status.ChunkStatuses.Builder;

class ChunkStatusesDiffblueTest {
  /**
   * Test {@link ChunkStatuses#reportLines()}.
   * <p>
   * Method under test: {@link ChunkStatuses#reportLines()}
   */
  @Test
  @DisplayName("Test reportLines()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List ChunkStatuses.reportLines()"})
  void testReportLines() throws UnsupportedEncodingException {
    // Arrange
    Builder builderResult = ChunkStatuses.builder();
    Builder chunksResult = builderResult.chunks(new ArrayList<>());
    GitHubHead head = GitHubHead.builder()
        .branch("janedoe/featurebranch")
        .owner("Owner")
        .repository("Repository")
        .sha("Sha")
        .build();
    ChunkStatuses buildResult = chunksResult.head(head).build();

    // Act
    List<String> actualReportLinesResult = buildResult.reportLines();

    // Assert
    assertEquals(1, actualReportLinesResult.size());
    assertEquals("", actualReportLinesResult.get(0));
  }

  /**
   * Test {@link ChunkStatuses#chunksForHead(GitHubHead, ChunkStatus[])} with {@code GitHubHead}, {@code ChunkStatus[]}.
   * <ul>
   *   <li>Then return reportLines size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link ChunkStatuses#chunksForHead(GitHubHead, ChunkStatus[])}
   */
  @Test
  @DisplayName("Test chunksForHead(GitHubHead, ChunkStatus[]) with 'GitHubHead', 'ChunkStatus[]'; then return reportLines size is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"ChunkStatuses ChunkStatuses.chunksForHead(GitHubHead, ChunkStatus[])"})
  void testChunksForHeadWithGitHubHeadChunkStatus_thenReturnReportLinesSizeIsOne() throws UnsupportedEncodingException {
    // Arrange and Act
    ChunkStatuses actualChunksForHeadResult = ChunkStatuses.chunksForHead(mock(GitHubHead.class),
        mock(ChunkStatus.class));

    // Assert
    List<String> reportLinesResult = actualChunksForHeadResult.reportLines();
    assertEquals(1, reportLinesResult.size());
    assertEquals("", reportLinesResult.get(0));
    assertFalse(actualChunksForHeadResult.isFailCheck());
  }

  /**
   * Test {@link ChunkStatuses#chunksForHead(GitHubHead, List)} with {@code GitHubHead}, {@code List}.
   * <ul>
   *   <li>Then return reportLines size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link ChunkStatuses#chunksForHead(GitHubHead, List)}
   */
  @Test
  @DisplayName("Test chunksForHead(GitHubHead, List) with 'GitHubHead', 'List'; then return reportLines size is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"ChunkStatuses ChunkStatuses.chunksForHead(GitHubHead, List)"})
  void testChunksForHeadWithGitHubHeadList_thenReturnReportLinesSizeIsOne() throws UnsupportedEncodingException {
    // Arrange
    GitHubHead head = mock(GitHubHead.class);

    // Act
    ChunkStatuses actualChunksForHeadResult = ChunkStatuses.chunksForHead(head, new ArrayList<>());

    // Assert
    List<String> reportLinesResult = actualChunksForHeadResult.reportLines();
    assertEquals(1, reportLinesResult.size());
    assertEquals("", reportLinesResult.get(0));
    assertFalse(actualChunksForHeadResult.isFailCheck());
  }

  /**
   * Test {@link ChunkStatuses#equals(Object)}, and {@link ChunkStatuses#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link ChunkStatuses#equals(Object)}
   *   <li>{@link ChunkStatuses#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean ChunkStatuses.equals(Object)", "int ChunkStatuses.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    Builder builderResult = ChunkStatuses.builder();
    Builder chunksResult = builderResult.chunks(new ArrayList<>());
    GitHubHead head = GitHubHead.builder()
        .branch("janedoe/featurebranch")
        .owner("Owner")
        .repository("Repository")
        .sha("Sha")
        .build();
    ChunkStatuses buildResult = chunksResult.head(head).build();
    Builder builderResult2 = ChunkStatuses.builder();
    Builder chunksResult2 = builderResult2.chunks(new ArrayList<>());
    GitHubHead head2 = GitHubHead.builder()
        .branch("janedoe/featurebranch")
        .owner("Owner")
        .repository("Repository")
        .sha("Sha")
        .build();
    ChunkStatuses buildResult2 = chunksResult2.head(head2).build();

    // Act and Assert
    assertEquals(buildResult, buildResult2);
    int expectedHashCodeResult = buildResult.hashCode();
    assertEquals(expectedHashCodeResult, buildResult2.hashCode());
  }

  /**
   * Test {@link ChunkStatuses#equals(Object)}, and {@link ChunkStatuses#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link ChunkStatuses#equals(Object)}
   *   <li>{@link ChunkStatuses#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean ChunkStatuses.equals(Object)", "int ChunkStatuses.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    Builder builderResult = ChunkStatuses.builder();
    Builder chunksResult = builderResult.chunks(new ArrayList<>());
    GitHubHead head = GitHubHead.builder()
        .branch("janedoe/featurebranch")
        .owner("Owner")
        .repository("Repository")
        .sha("Sha")
        .build();
    ChunkStatuses buildResult = chunksResult.head(head).build();

    // Act and Assert
    assertEquals(buildResult, buildResult);
    int expectedHashCodeResult = buildResult.hashCode();
    assertEquals(expectedHashCodeResult, buildResult.hashCode());
  }

  /**
   * Test {@link ChunkStatuses#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link ChunkStatuses#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean ChunkStatuses.equals(Object)", "int ChunkStatuses.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    Builder builderResult = ChunkStatuses.builder();
    Builder chunksResult = builderResult.chunks(new ArrayList<>());
    GitHubHead head = GitHubHead.builder().branch("Owner").owner("Owner").repository("Repository").sha("Sha").build();
    ChunkStatuses buildResult = chunksResult.head(head).build();
    Builder builderResult2 = ChunkStatuses.builder();
    Builder chunksResult2 = builderResult2.chunks(new ArrayList<>());
    GitHubHead head2 = GitHubHead.builder()
        .branch("janedoe/featurebranch")
        .owner("Owner")
        .repository("Repository")
        .sha("Sha")
        .build();
    ChunkStatuses buildResult2 = chunksResult2.head(head2).build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link ChunkStatuses#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link ChunkStatuses#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean ChunkStatuses.equals(Object)", "int ChunkStatuses.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange
    Builder builderResult = ChunkStatuses.builder();
    Builder chunksResult = builderResult.chunks(new ArrayList<>());
    GitHubHead head = GitHubHead.builder()
        .branch("janedoe/featurebranch")
        .owner("Owner")
        .repository("Repository")
        .sha("Sha")
        .build();
    ChunkStatuses buildResult = chunksResult.head(head).build();

    // Act and Assert
    assertNotEquals(buildResult, null);
  }

  /**
   * Test {@link ChunkStatuses#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link ChunkStatuses#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean ChunkStatuses.equals(Object)", "int ChunkStatuses.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange
    Builder builderResult = ChunkStatuses.builder();
    Builder chunksResult = builderResult.chunks(new ArrayList<>());
    GitHubHead head = GitHubHead.builder()
        .branch("janedoe/featurebranch")
        .owner("Owner")
        .repository("Repository")
        .sha("Sha")
        .build();
    ChunkStatuses buildResult = chunksResult.head(head).build();

    // Act and Assert
    assertNotEquals(buildResult, "Different type to ChunkStatuses");
  }

  /**
   * Test {@link ChunkStatuses#toString()}.
   * <p>
   * Method under test: {@link ChunkStatuses#toString()}
   */
  @Test
  @DisplayName("Test toString()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String ChunkStatuses.toString()"})
  void testToString() {
    // Arrange
    Builder builderResult = ChunkStatuses.builder();
    Builder chunksResult = builderResult.chunks(new ArrayList<>());
    GitHubHead head = GitHubHead.builder()
        .branch("janedoe/featurebranch")
        .owner("Owner")
        .repository("Repository")
        .sha("Sha")
        .build();
    ChunkStatuses buildResult = chunksResult.head(head).build();

    // Act and Assert
    assertEquals("ChunksStatus{head=GitHubHead{owner='Owner', repository='Repository', branch='janedoe/featurebranch',"
        + " sha='Sha'}, chunks=[]}", buildResult.toString());
  }
}
