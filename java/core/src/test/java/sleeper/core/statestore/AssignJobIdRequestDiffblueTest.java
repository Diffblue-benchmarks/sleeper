package sleeper.core.statestore;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class AssignJobIdRequestDiffblueTest {
  /**
   * Test {@link AssignJobIdRequest#assignJobOnPartitionToFiles(String, String, List)}.
   * <ul>
   *   <li>Given {@code 42}.</li>
   *   <li>When {@link ArrayList#ArrayList()} add {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AssignJobIdRequest#assignJobOnPartitionToFiles(String, String, List)}
   */
  @Test
  @DisplayName("Test assignJobOnPartitionToFiles(String, String, List); given '42'; when ArrayList() add '42'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"AssignJobIdRequest AssignJobIdRequest.assignJobOnPartitionToFiles(String, String, List)"})
  void testAssignJobOnPartitionToFiles_given42_whenArrayListAdd42() {
    // Arrange
    ArrayList<String> filenames = new ArrayList<>();
    filenames.add("42");
    filenames.add("foo");

    // Act
    AssignJobIdRequest actualAssignJobOnPartitionToFilesResult = AssignJobIdRequest.assignJobOnPartitionToFiles("42",
        "42", filenames);

    // Assert
    assertEquals("42", actualAssignJobOnPartitionToFilesResult.getJobId());
    assertEquals("42", actualAssignJobOnPartitionToFilesResult.getPartitionId());
    assertSame(filenames, actualAssignJobOnPartitionToFilesResult.getFilenames());
  }

  /**
   * Test {@link AssignJobIdRequest#assignJobOnPartitionToFiles(String, String, List)}.
   * <ul>
   *   <li>Given {@code foo}.</li>
   *   <li>Then return Filenames is {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AssignJobIdRequest#assignJobOnPartitionToFiles(String, String, List)}
   */
  @Test
  @DisplayName("Test assignJobOnPartitionToFiles(String, String, List); given 'foo'; then return Filenames is ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"AssignJobIdRequest AssignJobIdRequest.assignJobOnPartitionToFiles(String, String, List)"})
  void testAssignJobOnPartitionToFiles_givenFoo_thenReturnFilenamesIsArrayList() {
    // Arrange
    ArrayList<String> filenames = new ArrayList<>();
    filenames.add("foo");

    // Act
    AssignJobIdRequest actualAssignJobOnPartitionToFilesResult = AssignJobIdRequest.assignJobOnPartitionToFiles("42",
        "42", filenames);

    // Assert
    assertEquals("42", actualAssignJobOnPartitionToFilesResult.getJobId());
    assertEquals("42", actualAssignJobOnPartitionToFilesResult.getPartitionId());
    assertSame(filenames, actualAssignJobOnPartitionToFilesResult.getFilenames());
  }

  /**
   * Test {@link AssignJobIdRequest#assignJobOnPartitionToFiles(String, String, List)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return Filenames Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link AssignJobIdRequest#assignJobOnPartitionToFiles(String, String, List)}
   */
  @Test
  @DisplayName("Test assignJobOnPartitionToFiles(String, String, List); when ArrayList(); then return Filenames Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"AssignJobIdRequest AssignJobIdRequest.assignJobOnPartitionToFiles(String, String, List)"})
  void testAssignJobOnPartitionToFiles_whenArrayList_thenReturnFilenamesEmpty() {
    // Arrange and Act
    AssignJobIdRequest actualAssignJobOnPartitionToFilesResult = AssignJobIdRequest.assignJobOnPartitionToFiles("42",
        "42", new ArrayList<>());

    // Assert
    assertEquals("42", actualAssignJobOnPartitionToFilesResult.getJobId());
    assertEquals("42", actualAssignJobOnPartitionToFilesResult.getPartitionId());
    assertTrue(actualAssignJobOnPartitionToFilesResult.getFilenames().isEmpty());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link AssignJobIdRequest#toString()}
   *   <li>{@link AssignJobIdRequest#getFilenames()}
   *   <li>{@link AssignJobIdRequest#getJobId()}
   *   <li>{@link AssignJobIdRequest#getPartitionId()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List AssignJobIdRequest.getFilenames()", "String AssignJobIdRequest.getJobId()",
      "String AssignJobIdRequest.getPartitionId()", "String AssignJobIdRequest.toString()"})
  void testGettersAndSetters() {
    // Arrange
    ArrayList<String> filenames = new ArrayList<>();
    AssignJobIdRequest assignJobOnPartitionToFilesResult = AssignJobIdRequest.assignJobOnPartitionToFiles("42", "42",
        filenames);

    // Act
    String actualToStringResult = assignJobOnPartitionToFilesResult.toString();
    List<String> actualFilenames = assignJobOnPartitionToFilesResult.getFilenames();
    String actualJobId = assignJobOnPartitionToFilesResult.getJobId();

    // Assert
    assertEquals("42", actualJobId);
    assertEquals("42", assignJobOnPartitionToFilesResult.getPartitionId());
    assertEquals("AssignJobIdRequest{jobId=42, partitionId=42, filenames=[]}", actualToStringResult);
    assertTrue(actualFilenames.isEmpty());
    assertSame(filenames, actualFilenames);
  }

  /**
   * Test {@link AssignJobIdRequest#equals(Object)}, and {@link AssignJobIdRequest#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link AssignJobIdRequest#equals(Object)}
   *   <li>{@link AssignJobIdRequest#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean AssignJobIdRequest.equals(Object)", "int AssignJobIdRequest.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    AssignJobIdRequest assignJobOnPartitionToFilesResult = AssignJobIdRequest.assignJobOnPartitionToFiles("42", "42",
        new ArrayList<>());
    AssignJobIdRequest assignJobOnPartitionToFilesResult2 = AssignJobIdRequest.assignJobOnPartitionToFiles("42", "42",
        new ArrayList<>());

    // Act and Assert
    assertEquals(assignJobOnPartitionToFilesResult, assignJobOnPartitionToFilesResult2);
    int expectedHashCodeResult = assignJobOnPartitionToFilesResult.hashCode();
    assertEquals(expectedHashCodeResult, assignJobOnPartitionToFilesResult2.hashCode());
  }

  /**
   * Test {@link AssignJobIdRequest#equals(Object)}, and {@link AssignJobIdRequest#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link AssignJobIdRequest#equals(Object)}
   *   <li>{@link AssignJobIdRequest#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean AssignJobIdRequest.equals(Object)", "int AssignJobIdRequest.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    AssignJobIdRequest assignJobOnPartitionToFilesResult = AssignJobIdRequest.assignJobOnPartitionToFiles("42", "42",
        new ArrayList<>());

    // Act and Assert
    assertEquals(assignJobOnPartitionToFilesResult, assignJobOnPartitionToFilesResult);
    int expectedHashCodeResult = assignJobOnPartitionToFilesResult.hashCode();
    assertEquals(expectedHashCodeResult, assignJobOnPartitionToFilesResult.hashCode());
  }

  /**
   * Test {@link AssignJobIdRequest#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link AssignJobIdRequest#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean AssignJobIdRequest.equals(Object)", "int AssignJobIdRequest.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    AssignJobIdRequest assignJobOnPartitionToFilesResult = AssignJobIdRequest.assignJobOnPartitionToFiles("Job Id",
        "42", new ArrayList<>());

    // Act and Assert
    assertNotEquals(assignJobOnPartitionToFilesResult,
        AssignJobIdRequest.assignJobOnPartitionToFiles("42", "42", new ArrayList<>()));
  }

  /**
   * Test {@link AssignJobIdRequest#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link AssignJobIdRequest#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean AssignJobIdRequest.equals(Object)", "int AssignJobIdRequest.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    AssignJobIdRequest assignJobOnPartitionToFilesResult = AssignJobIdRequest.assignJobOnPartitionToFiles("42",
        "Partition Id", new ArrayList<>());

    // Act and Assert
    assertNotEquals(assignJobOnPartitionToFilesResult,
        AssignJobIdRequest.assignJobOnPartitionToFiles("42", "42", new ArrayList<>()));
  }

  /**
   * Test {@link AssignJobIdRequest#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link AssignJobIdRequest#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean AssignJobIdRequest.equals(Object)", "int AssignJobIdRequest.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual3() {
    // Arrange
    ArrayList<String> filenames = new ArrayList<>();
    filenames.add("foo");
    AssignJobIdRequest assignJobOnPartitionToFilesResult = AssignJobIdRequest.assignJobOnPartitionToFiles("42", "42",
        filenames);

    // Act and Assert
    assertNotEquals(assignJobOnPartitionToFilesResult,
        AssignJobIdRequest.assignJobOnPartitionToFiles("42", "42", new ArrayList<>()));
  }

  /**
   * Test {@link AssignJobIdRequest#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link AssignJobIdRequest#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean AssignJobIdRequest.equals(Object)", "int AssignJobIdRequest.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(AssignJobIdRequest.assignJobOnPartitionToFiles("42", "42", new ArrayList<>()), null);
  }

  /**
   * Test {@link AssignJobIdRequest#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link AssignJobIdRequest#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean AssignJobIdRequest.equals(Object)", "int AssignJobIdRequest.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(AssignJobIdRequest.assignJobOnPartitionToFiles("42", "42", new ArrayList<>()),
        "Different type to AssignJobIdRequest");
  }
}
