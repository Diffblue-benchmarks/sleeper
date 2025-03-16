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

class CheckFileAssignmentsRequestDiffblueTest {
  /**
   * Test {@link CheckFileAssignmentsRequest#isJobAssignedToFilesOnPartition(String, List, String)}.
   * <ul>
   *   <li>Given {@code 42}.</li>
   *   <li>When {@link ArrayList#ArrayList()} add {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CheckFileAssignmentsRequest#isJobAssignedToFilesOnPartition(String, List, String)}
   */
  @Test
  @DisplayName("Test isJobAssignedToFilesOnPartition(String, List, String); given '42'; when ArrayList() add '42'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "CheckFileAssignmentsRequest CheckFileAssignmentsRequest.isJobAssignedToFilesOnPartition(String, List, String)"})
  void testIsJobAssignedToFilesOnPartition_given42_whenArrayListAdd42() {
    // Arrange
    ArrayList<String> filenames = new ArrayList<>();
    filenames.add("42");
    filenames.add("foo");

    // Act
    CheckFileAssignmentsRequest actualIsJobAssignedToFilesOnPartitionResult = CheckFileAssignmentsRequest
        .isJobAssignedToFilesOnPartition("42", filenames, "42");

    // Assert
    assertEquals("42", actualIsJobAssignedToFilesOnPartitionResult.getJobId());
    assertEquals("42", actualIsJobAssignedToFilesOnPartitionResult.getPartitionId());
    assertSame(filenames, actualIsJobAssignedToFilesOnPartitionResult.getFilenames());
  }

  /**
   * Test {@link CheckFileAssignmentsRequest#isJobAssignedToFilesOnPartition(String, List, String)}.
   * <ul>
   *   <li>Given {@code foo}.</li>
   *   <li>Then return Filenames is {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CheckFileAssignmentsRequest#isJobAssignedToFilesOnPartition(String, List, String)}
   */
  @Test
  @DisplayName("Test isJobAssignedToFilesOnPartition(String, List, String); given 'foo'; then return Filenames is ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "CheckFileAssignmentsRequest CheckFileAssignmentsRequest.isJobAssignedToFilesOnPartition(String, List, String)"})
  void testIsJobAssignedToFilesOnPartition_givenFoo_thenReturnFilenamesIsArrayList() {
    // Arrange
    ArrayList<String> filenames = new ArrayList<>();
    filenames.add("foo");

    // Act
    CheckFileAssignmentsRequest actualIsJobAssignedToFilesOnPartitionResult = CheckFileAssignmentsRequest
        .isJobAssignedToFilesOnPartition("42", filenames, "42");

    // Assert
    assertEquals("42", actualIsJobAssignedToFilesOnPartitionResult.getJobId());
    assertEquals("42", actualIsJobAssignedToFilesOnPartitionResult.getPartitionId());
    assertSame(filenames, actualIsJobAssignedToFilesOnPartitionResult.getFilenames());
  }

  /**
   * Test {@link CheckFileAssignmentsRequest#isJobAssignedToFilesOnPartition(String, List, String)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return Filenames Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link CheckFileAssignmentsRequest#isJobAssignedToFilesOnPartition(String, List, String)}
   */
  @Test
  @DisplayName("Test isJobAssignedToFilesOnPartition(String, List, String); when ArrayList(); then return Filenames Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "CheckFileAssignmentsRequest CheckFileAssignmentsRequest.isJobAssignedToFilesOnPartition(String, List, String)"})
  void testIsJobAssignedToFilesOnPartition_whenArrayList_thenReturnFilenamesEmpty() {
    // Arrange and Act
    CheckFileAssignmentsRequest actualIsJobAssignedToFilesOnPartitionResult = CheckFileAssignmentsRequest
        .isJobAssignedToFilesOnPartition("42", new ArrayList<>(), "42");

    // Assert
    assertEquals("42", actualIsJobAssignedToFilesOnPartitionResult.getJobId());
    assertEquals("42", actualIsJobAssignedToFilesOnPartitionResult.getPartitionId());
    assertTrue(actualIsJobAssignedToFilesOnPartitionResult.getFilenames().isEmpty());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link CheckFileAssignmentsRequest#toString()}
   *   <li>{@link CheckFileAssignmentsRequest#getFilenames()}
   *   <li>{@link CheckFileAssignmentsRequest#getJobId()}
   *   <li>{@link CheckFileAssignmentsRequest#getPartitionId()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List CheckFileAssignmentsRequest.getFilenames()", "String CheckFileAssignmentsRequest.getJobId()",
      "String CheckFileAssignmentsRequest.getPartitionId()", "String CheckFileAssignmentsRequest.toString()"})
  void testGettersAndSetters() {
    // Arrange
    ArrayList<String> filenames = new ArrayList<>();
    CheckFileAssignmentsRequest isJobAssignedToFilesOnPartitionResult = CheckFileAssignmentsRequest
        .isJobAssignedToFilesOnPartition("42", filenames, "42");

    // Act
    String actualToStringResult = isJobAssignedToFilesOnPartitionResult.toString();
    List<String> actualFilenames = isJobAssignedToFilesOnPartitionResult.getFilenames();
    String actualJobId = isJobAssignedToFilesOnPartitionResult.getJobId();

    // Assert
    assertEquals("42", actualJobId);
    assertEquals("42", isJobAssignedToFilesOnPartitionResult.getPartitionId());
    assertEquals("CheckFileAssignmentsRequest{jobId=42, filenames=[], partitionId=42}", actualToStringResult);
    assertTrue(actualFilenames.isEmpty());
    assertSame(filenames, actualFilenames);
  }

  /**
   * Test {@link CheckFileAssignmentsRequest#equals(Object)}, and {@link CheckFileAssignmentsRequest#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link CheckFileAssignmentsRequest#equals(Object)}
   *   <li>{@link CheckFileAssignmentsRequest#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CheckFileAssignmentsRequest.equals(Object)",
      "int CheckFileAssignmentsRequest.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    CheckFileAssignmentsRequest isJobAssignedToFilesOnPartitionResult = CheckFileAssignmentsRequest
        .isJobAssignedToFilesOnPartition("42", new ArrayList<>(), "42");
    CheckFileAssignmentsRequest isJobAssignedToFilesOnPartitionResult2 = CheckFileAssignmentsRequest
        .isJobAssignedToFilesOnPartition("42", new ArrayList<>(), "42");

    // Act and Assert
    assertEquals(isJobAssignedToFilesOnPartitionResult, isJobAssignedToFilesOnPartitionResult2);
    int expectedHashCodeResult = isJobAssignedToFilesOnPartitionResult.hashCode();
    assertEquals(expectedHashCodeResult, isJobAssignedToFilesOnPartitionResult2.hashCode());
  }

  /**
   * Test {@link CheckFileAssignmentsRequest#equals(Object)}, and {@link CheckFileAssignmentsRequest#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link CheckFileAssignmentsRequest#equals(Object)}
   *   <li>{@link CheckFileAssignmentsRequest#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CheckFileAssignmentsRequest.equals(Object)",
      "int CheckFileAssignmentsRequest.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    CheckFileAssignmentsRequest isJobAssignedToFilesOnPartitionResult = CheckFileAssignmentsRequest
        .isJobAssignedToFilesOnPartition("42", new ArrayList<>(), "42");

    // Act and Assert
    assertEquals(isJobAssignedToFilesOnPartitionResult, isJobAssignedToFilesOnPartitionResult);
    int expectedHashCodeResult = isJobAssignedToFilesOnPartitionResult.hashCode();
    assertEquals(expectedHashCodeResult, isJobAssignedToFilesOnPartitionResult.hashCode());
  }

  /**
   * Test {@link CheckFileAssignmentsRequest#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link CheckFileAssignmentsRequest#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CheckFileAssignmentsRequest.equals(Object)",
      "int CheckFileAssignmentsRequest.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    CheckFileAssignmentsRequest isJobAssignedToFilesOnPartitionResult = CheckFileAssignmentsRequest
        .isJobAssignedToFilesOnPartition("Job Id", new ArrayList<>(), "42");

    // Act and Assert
    assertNotEquals(isJobAssignedToFilesOnPartitionResult,
        CheckFileAssignmentsRequest.isJobAssignedToFilesOnPartition("42", new ArrayList<>(), "42"));
  }

  /**
   * Test {@link CheckFileAssignmentsRequest#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link CheckFileAssignmentsRequest#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CheckFileAssignmentsRequest.equals(Object)",
      "int CheckFileAssignmentsRequest.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    ArrayList<String> filenames = new ArrayList<>();
    filenames.add("foo");
    CheckFileAssignmentsRequest isJobAssignedToFilesOnPartitionResult = CheckFileAssignmentsRequest
        .isJobAssignedToFilesOnPartition("42", filenames, "42");

    // Act and Assert
    assertNotEquals(isJobAssignedToFilesOnPartitionResult,
        CheckFileAssignmentsRequest.isJobAssignedToFilesOnPartition("42", new ArrayList<>(), "42"));
  }

  /**
   * Test {@link CheckFileAssignmentsRequest#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link CheckFileAssignmentsRequest#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CheckFileAssignmentsRequest.equals(Object)",
      "int CheckFileAssignmentsRequest.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual3() {
    // Arrange
    CheckFileAssignmentsRequest isJobAssignedToFilesOnPartitionResult = CheckFileAssignmentsRequest
        .isJobAssignedToFilesOnPartition("42", new ArrayList<>(), "Partition Id");

    // Act and Assert
    assertNotEquals(isJobAssignedToFilesOnPartitionResult,
        CheckFileAssignmentsRequest.isJobAssignedToFilesOnPartition("42", new ArrayList<>(), "42"));
  }

  /**
   * Test {@link CheckFileAssignmentsRequest#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link CheckFileAssignmentsRequest#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CheckFileAssignmentsRequest.equals(Object)",
      "int CheckFileAssignmentsRequest.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(CheckFileAssignmentsRequest.isJobAssignedToFilesOnPartition("42", new ArrayList<>(), "42"), null);
  }

  /**
   * Test {@link CheckFileAssignmentsRequest#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link CheckFileAssignmentsRequest#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CheckFileAssignmentsRequest.equals(Object)",
      "int CheckFileAssignmentsRequest.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(CheckFileAssignmentsRequest.isJobAssignedToFilesOnPartition("42", new ArrayList<>(), "42"),
        "Different type to CheckFileAssignmentsRequest");
  }
}
