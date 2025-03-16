package sleeper.systemtest.dsl.sourcedata;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class GeneratedIngestSourceFilesDiffblueTest {
  /**
   * Test {@link GeneratedIngestSourceFiles#GeneratedIngestSourceFiles(String, List)}.
   * <p>
   * Method under test: {@link GeneratedIngestSourceFiles#GeneratedIngestSourceFiles(String, List)}
   */
  @Test
  @DisplayName("Test new GeneratedIngestSourceFiles(String, List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void GeneratedIngestSourceFiles.<init>(String, List)"})
  void testNewGeneratedIngestSourceFiles() {
    // Arrange and Act
    GeneratedIngestSourceFiles actualGeneratedIngestSourceFiles = new GeneratedIngestSourceFiles(
        "s3://bucket-name/object-key", new ArrayList<>());

    // Assert
    List<String> ingestJobFilesCombiningAll = actualGeneratedIngestSourceFiles.getIngestJobFilesCombiningAll();
    assertTrue(ingestJobFilesCombiningAll.isEmpty());
    assertSame(ingestJobFilesCombiningAll, actualGeneratedIngestSourceFiles.getJobIdsFromIndividualFiles());
  }

  /**
   * Test {@link GeneratedIngestSourceFiles#getJobIdsFromIndividualFiles()}.
   * <ul>
   *   <li>Then return Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link GeneratedIngestSourceFiles#getJobIdsFromIndividualFiles()}
   */
  @Test
  @DisplayName("Test getJobIdsFromIndividualFiles(); then return Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List GeneratedIngestSourceFiles.getJobIdsFromIndividualFiles()"})
  void testGetJobIdsFromIndividualFiles_thenReturnEmpty() {
    // Arrange, Act and Assert
    assertTrue((new GeneratedIngestSourceFiles("s3://bucket-name/object-key", new ArrayList<>()))
        .getJobIdsFromIndividualFiles()
        .isEmpty());
  }

  /**
   * Test {@link GeneratedIngestSourceFiles#getIngestJobFilesCombiningAll()}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@code 42}.</li>
   *   <li>Then return size is two.</li>
   * </ul>
   * <p>
   * Method under test: {@link GeneratedIngestSourceFiles#getIngestJobFilesCombiningAll()}
   */
  @Test
  @DisplayName("Test getIngestJobFilesCombiningAll(); given ArrayList() add '42'; then return size is two")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List GeneratedIngestSourceFiles.getIngestJobFilesCombiningAll()"})
  void testGetIngestJobFilesCombiningAll_givenArrayListAdd42_thenReturnSizeIsTwo() {
    // Arrange
    ArrayList<String> objectKeys = new ArrayList<>();
    objectKeys.add("42");
    objectKeys.add("foo");

    // Act
    List<String> actualIngestJobFilesCombiningAll = (new GeneratedIngestSourceFiles("s3://bucket-name/object-key",
        objectKeys)).getIngestJobFilesCombiningAll();

    // Assert
    assertEquals(2, actualIngestJobFilesCombiningAll.size());
    assertEquals("s3://bucket-name/object-key/42", actualIngestJobFilesCombiningAll.get(0));
    assertEquals("s3://bucket-name/object-key/foo", actualIngestJobFilesCombiningAll.get(1));
  }

  /**
   * Test {@link GeneratedIngestSourceFiles#getIngestJobFilesCombiningAll()}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@code foo}.</li>
   *   <li>Then return size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link GeneratedIngestSourceFiles#getIngestJobFilesCombiningAll()}
   */
  @Test
  @DisplayName("Test getIngestJobFilesCombiningAll(); given ArrayList() add 'foo'; then return size is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List GeneratedIngestSourceFiles.getIngestJobFilesCombiningAll()"})
  void testGetIngestJobFilesCombiningAll_givenArrayListAddFoo_thenReturnSizeIsOne() {
    // Arrange
    ArrayList<String> objectKeys = new ArrayList<>();
    objectKeys.add("foo");

    // Act
    List<String> actualIngestJobFilesCombiningAll = (new GeneratedIngestSourceFiles("s3://bucket-name/object-key",
        objectKeys)).getIngestJobFilesCombiningAll();

    // Assert
    assertEquals(1, actualIngestJobFilesCombiningAll.size());
    assertEquals("s3://bucket-name/object-key/foo", actualIngestJobFilesCombiningAll.get(0));
  }

  /**
   * Test {@link GeneratedIngestSourceFiles#getIngestJobFilesCombiningAll()}.
   * <ul>
   *   <li>Then return Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link GeneratedIngestSourceFiles#getIngestJobFilesCombiningAll()}
   */
  @Test
  @DisplayName("Test getIngestJobFilesCombiningAll(); then return Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List GeneratedIngestSourceFiles.getIngestJobFilesCombiningAll()"})
  void testGetIngestJobFilesCombiningAll_thenReturnEmpty() {
    // Arrange, Act and Assert
    assertTrue((new GeneratedIngestSourceFiles("s3://bucket-name/object-key", new ArrayList<>()))
        .getIngestJobFilesCombiningAll()
        .isEmpty());
  }
}
