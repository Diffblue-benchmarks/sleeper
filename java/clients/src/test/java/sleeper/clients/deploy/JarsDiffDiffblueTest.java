package sleeper.clients.deploy;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Response;
import software.amazon.awssdk.services.s3.paginators.ListObjectsV2Iterable;

class JarsDiffDiffblueTest {
  /**
   * Test {@link JarsDiff#from(Path, List, ListObjectsV2Iterable)} with {@code directory}, {@code localFiles}, {@code listObjects}.
   * <ul>
   *   <li>Then S3KeysToDelete return {@link List}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JarsDiff#from(Path, List, ListObjectsV2Iterable)}
   */
  @Test
  @DisplayName("Test from(Path, List, ListObjectsV2Iterable) with 'directory', 'localFiles', 'listObjects'; then S3KeysToDelete return List")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"JarsDiff JarsDiff.from(Path, List, ListObjectsV2Iterable)"})
  void testFromWithDirectoryLocalFilesListObjects_thenS3KeysToDeleteReturnList() throws IOException {
    // Arrange
    Path directory = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    ArrayList<Path> localFiles = new ArrayList<>();
    ListObjectsV2Iterable listObjects = mock(ListObjectsV2Iterable.class);

    ArrayList<ListObjectsV2Response> listObjectsV2ResponseList = new ArrayList<>();
    Stream<ListObjectsV2Response> streamResult = listObjectsV2ResponseList.stream();
    when(listObjects.stream()).thenReturn(streamResult);

    // Act
    JarsDiff actualFromResult = JarsDiff.from(directory, localFiles, listObjects);

    // Assert
    verify(listObjects).stream();
    Collection<String> s3KeysToDelete = actualFromResult.getS3KeysToDelete();
    assertTrue(s3KeysToDelete instanceof List);
    Collection<Path> modifiedAndNew = actualFromResult.getModifiedAndNew();
    assertTrue(modifiedAndNew instanceof Set);
    assertTrue(modifiedAndNew.isEmpty());
    assertTrue(s3KeysToDelete.isEmpty());
  }
}
