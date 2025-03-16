package sleeper.build.github.api.containers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.build.github.api.containers.GitHubPackageVersionResponse.ContainerMetadata;
import sleeper.build.github.api.containers.GitHubPackageVersionResponse.Metadata;

class GitHubPackageVersionResponseDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link GitHubPackageVersionResponse#GitHubPackageVersionResponse(String, Instant, Metadata)}
   *   <li>{@link GitHubPackageVersionResponse#getId()}
   *   <li>{@link GitHubPackageVersionResponse#getUpdatedAt()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void GitHubPackageVersionResponse.<init>(String, Instant, Metadata)",
      "String GitHubPackageVersionResponse.getId()", "Instant GitHubPackageVersionResponse.getUpdatedAt()"})
  void testGettersAndSetters() {
    // Arrange
    Instant updatedAt = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    ArrayList<String> tags = new ArrayList<>();

    // Act
    GitHubPackageVersionResponse actualGitHubPackageVersionResponse = new GitHubPackageVersionResponse("42", updatedAt,
        new Metadata(new ContainerMetadata(tags)));
    String actualId = actualGitHubPackageVersionResponse.getId();
    Instant actualUpdatedAt = actualGitHubPackageVersionResponse.getUpdatedAt();

    // Assert
    assertEquals("42", actualId);
    List<String> tags2 = actualGitHubPackageVersionResponse.getTags();
    assertTrue(tags2.isEmpty());
    assertSame(tags, tags2);
    assertSame(actualUpdatedAt.EPOCH, actualUpdatedAt);
  }

  /**
   * Test {@link GitHubPackageVersionResponse#getTags()}.
   * <ul>
   *   <li>Given {@link ContainerMetadata#ContainerMetadata(List)} with tags is {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link GitHubPackageVersionResponse#getTags()}
   */
  @Test
  @DisplayName("Test getTags(); given ContainerMetadata(List) with tags is ArrayList(); then return Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List GitHubPackageVersionResponse.getTags()"})
  void testGetTags_givenContainerMetadataWithTagsIsArrayList_thenReturnEmpty() {
    // Arrange
    Instant updatedAt = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    // Act and Assert
    assertTrue(
        (new GitHubPackageVersionResponse("42", updatedAt, new Metadata(new ContainerMetadata(new ArrayList<>()))))
            .getTags()
            .isEmpty());
  }
}
