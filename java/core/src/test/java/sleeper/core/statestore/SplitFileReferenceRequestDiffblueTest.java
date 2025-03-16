package sleeper.core.statestore;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.statestore.FileReference.Builder;

class SplitFileReferenceRequestDiffblueTest {
  /**
   * Test {@link SplitFileReferenceRequest#SplitFileReferenceRequest(FileReference, List)}.
   * <ul>
   *   <li>Then return Filename is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SplitFileReferenceRequest#SplitFileReferenceRequest(FileReference, List)}
   */
  @Test
  @DisplayName("Test new SplitFileReferenceRequest(FileReference, List); then return Filename is 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SplitFileReferenceRequest.<init>(FileReference, List)"})
  void testNewSplitFileReferenceRequest_thenReturnFilenameIsNull() {
    // Arrange
    FileReference oldReference = mock(FileReference.class);

    ArrayList<FileReference> newReferences = new ArrayList<>();
    Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference buildResult = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    newReferences.add(buildResult);

    // Act
    SplitFileReferenceRequest actualSplitFileReferenceRequest = new SplitFileReferenceRequest(oldReference,
        newReferences);

    // Assert
    assertNull(actualSplitFileReferenceRequest.getFilename());
    assertNull(actualSplitFileReferenceRequest.getFromPartitionId());
    assertSame(newReferences, actualSplitFileReferenceRequest.getNewReferences());
    assertSame(oldReference, actualSplitFileReferenceRequest.getOldReference());
  }

  /**
   * Test {@link SplitFileReferenceRequest#SplitFileReferenceRequest(FileReference, List)}.
   * <ul>
   *   <li>Then return NewReferences size is two.</li>
   * </ul>
   * <p>
   * Method under test: {@link SplitFileReferenceRequest#SplitFileReferenceRequest(FileReference, List)}
   */
  @Test
  @DisplayName("Test new SplitFileReferenceRequest(FileReference, List); then return NewReferences size is two")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SplitFileReferenceRequest.<init>(FileReference, List)"})
  void testNewSplitFileReferenceRequest_thenReturnNewReferencesSizeIsTwo() {
    // Arrange
    FileReference oldReference = mock(FileReference.class);

    ArrayList<FileReference> newReferences = new ArrayList<>();
    Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference buildResult = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    newReferences.add(buildResult);
    Builder jobIdResult2 = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference buildResult2 = jobIdResult2
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    newReferences.add(buildResult2);

    // Act and Assert
    List<FileReference> newReferences2 = (new SplitFileReferenceRequest(oldReference, newReferences))
        .getNewReferences();
    assertEquals(2, newReferences2.size());
    assertEquals(newReferences2.get(0), newReferences2.get(1));
  }

  /**
   * Test {@link SplitFileReferenceRequest#SplitFileReferenceRequest(FileReference, List)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SplitFileReferenceRequest#SplitFileReferenceRequest(FileReference, List)}
   */
  @Test
  @DisplayName("Test new SplitFileReferenceRequest(FileReference, List); when ArrayList(); then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SplitFileReferenceRequest.<init>(FileReference, List)"})
  void testNewSplitFileReferenceRequest_whenArrayList_thenThrowIllegalArgumentException() {
    // Arrange
    FileReference oldReference = mock(FileReference.class);

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> new SplitFileReferenceRequest(oldReference, new ArrayList<>()));

  }

  /**
   * Test {@link SplitFileReferenceRequest#splitFileToChildPartitions(FileReference, String, String)}.
   * <ul>
   *   <li>Given one.</li>
   *   <li>Then return NewReferences size is two.</li>
   * </ul>
   * <p>
   * Method under test: {@link SplitFileReferenceRequest#splitFileToChildPartitions(FileReference, String, String)}
   */
  @Test
  @DisplayName("Test splitFileToChildPartitions(FileReference, String, String); given one; then return NewReferences size is two")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "SplitFileReferenceRequest SplitFileReferenceRequest.splitFileToChildPartitions(FileReference, String, String)"})
  void testSplitFileToChildPartitions_givenOne_thenReturnNewReferencesSizeIsTwo() {
    // Arrange
    FileReference file = mock(FileReference.class);
    when(file.getNumberOfRecords()).thenReturn(1L);
    when(file.getFilename()).thenReturn("foo.txt");

    // Act
    SplitFileReferenceRequest actualSplitFileToChildPartitionsResult = SplitFileReferenceRequest
        .splitFileToChildPartitions(file, "Left Partition", "Right Partition");

    // Assert
    verify(file, atLeast(1)).getFilename();
    verify(file, atLeast(1)).getNumberOfRecords();
    List<FileReference> newReferences = actualSplitFileToChildPartitionsResult.getNewReferences();
    assertEquals(2, newReferences.size());
    FileReference getResult = newReferences.get(0);
    assertEquals("Left Partition", getResult.getPartitionId());
    FileReference getResult2 = newReferences.get(1);
    assertEquals("Right Partition", getResult2.getPartitionId());
    assertEquals("foo.txt", getResult.getFilename());
    assertEquals("foo.txt", getResult2.getFilename());
    assertEquals("foo.txt", actualSplitFileToChildPartitionsResult.getFilename());
    assertNull(getResult.getJobId());
    assertNull(getResult2.getJobId());
    assertNull(actualSplitFileToChildPartitionsResult.getFromPartitionId());
    assertNull(getResult.getLastStateStoreUpdateTime());
    assertNull(getResult2.getLastStateStoreUpdateTime());
    assertEquals(0L, getResult.getNumberOfRecords().longValue());
    assertEquals(0L, getResult2.getNumberOfRecords().longValue());
    assertFalse(getResult.onlyContainsDataForThisPartition());
    assertFalse(getResult2.onlyContainsDataForThisPartition());
    assertTrue(getResult.isCountApproximate());
    assertTrue(getResult2.isCountApproximate());
    assertSame(file, actualSplitFileToChildPartitionsResult.getOldReference());
  }

  /**
   * Test {@link SplitFileReferenceRequest#splitFileToChildPartitions(FileReference, String, String)}.
   * <ul>
   *   <li>Then return FromPartitionId is {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SplitFileReferenceRequest#splitFileToChildPartitions(FileReference, String, String)}
   */
  @Test
  @DisplayName("Test splitFileToChildPartitions(FileReference, String, String); then return FromPartitionId is '42'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "SplitFileReferenceRequest SplitFileReferenceRequest.splitFileToChildPartitions(FileReference, String, String)"})
  void testSplitFileToChildPartitions_thenReturnFromPartitionIdIs42() {
    // Arrange
    Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference file = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();

    // Act
    SplitFileReferenceRequest actualSplitFileToChildPartitionsResult = SplitFileReferenceRequest
        .splitFileToChildPartitions(file, "Left Partition", "Right Partition");

    // Assert
    assertEquals("42", actualSplitFileToChildPartitionsResult.getFromPartitionId());
    assertSame(file, actualSplitFileToChildPartitionsResult.getOldReference());
  }

  /**
   * Test {@link SplitFileReferenceRequest#splitFileToChildPartitions(FileReference, String, String)}.
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SplitFileReferenceRequest#splitFileToChildPartitions(FileReference, String, String)}
   */
  @Test
  @DisplayName("Test splitFileToChildPartitions(FileReference, String, String); then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "SplitFileReferenceRequest SplitFileReferenceRequest.splitFileToChildPartitions(FileReference, String, String)"})
  void testSplitFileToChildPartitions_thenThrowIllegalArgumentException() {
    // Arrange
    FileReference file = mock(FileReference.class);
    when(file.getNumberOfRecords()).thenThrow(new IllegalArgumentException("filename must not be null"));

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> SplitFileReferenceRequest.splitFileToChildPartitions(file, "Left Partition", "Right Partition"));
    verify(file).getNumberOfRecords();
  }

  /**
   * Test {@link SplitFileReferenceRequest#withNoUpdateTimes()}.
   * <p>
   * Method under test: {@link SplitFileReferenceRequest#withNoUpdateTimes()}
   */
  @Test
  @DisplayName("Test withNoUpdateTimes()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"SplitFileReferenceRequest SplitFileReferenceRequest.withNoUpdateTimes()"})
  void testWithNoUpdateTimes() {
    // Arrange
    Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference file = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();

    // Act
    SplitFileReferenceRequest actualWithNoUpdateTimesResult = SplitFileReferenceRequest
        .splitFileToChildPartitions(file, "Left Partition", "Right Partition")
        .withNoUpdateTimes();

    // Assert
    FileReference oldReference = actualWithNoUpdateTimesResult.getOldReference();
    assertEquals("42", oldReference.getJobId());
    assertEquals("42", oldReference.getPartitionId());
    assertEquals("42", actualWithNoUpdateTimesResult.getFromPartitionId());
    assertEquals("foo.txt", oldReference.getFilename());
    assertEquals("foo.txt", actualWithNoUpdateTimesResult.getFilename());
    assertNull(oldReference.getLastStateStoreUpdateTime());
    assertEquals(1L, oldReference.getNumberOfRecords().longValue());
    assertEquals(2, actualWithNoUpdateTimesResult.getNewReferences().size());
    assertTrue(oldReference.isCountApproximate());
    assertTrue(oldReference.onlyContainsDataForThisPartition());
  }

  /**
   * Test {@link SplitFileReferenceRequest#getFilename()}.
   * <p>
   * Method under test: {@link SplitFileReferenceRequest#getFilename()}
   */
  @Test
  @DisplayName("Test getFilename()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String SplitFileReferenceRequest.getFilename()"})
  void testGetFilename() {
    // Arrange
    Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference file = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();

    // Act and Assert
    assertEquals("foo.txt",
        SplitFileReferenceRequest.splitFileToChildPartitions(file, "Left Partition", "Right Partition").getFilename());
  }

  /**
   * Test {@link SplitFileReferenceRequest#getFromPartitionId()}.
   * <p>
   * Method under test: {@link SplitFileReferenceRequest#getFromPartitionId()}
   */
  @Test
  @DisplayName("Test getFromPartitionId()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String SplitFileReferenceRequest.getFromPartitionId()"})
  void testGetFromPartitionId() {
    // Arrange
    Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference file = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();

    // Act and Assert
    assertEquals("42", SplitFileReferenceRequest.splitFileToChildPartitions(file, "Left Partition", "Right Partition")
        .getFromPartitionId());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link SplitFileReferenceRequest#toString()}
   *   <li>{@link SplitFileReferenceRequest#getNewReferences()}
   *   <li>{@link SplitFileReferenceRequest#getOldReference()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List SplitFileReferenceRequest.getNewReferences()",
      "FileReference SplitFileReferenceRequest.getOldReference()", "String SplitFileReferenceRequest.toString()"})
  void testGettersAndSetters() {
    // Arrange
    Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference file = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    SplitFileReferenceRequest splitFileToChildPartitionsResult = SplitFileReferenceRequest
        .splitFileToChildPartitions(file, "Left Partition", "Right Partition");

    // Act
    String actualToStringResult = splitFileToChildPartitionsResult.toString();
    List<FileReference> actualNewReferences = splitFileToChildPartitionsResult.getNewReferences();
    FileReference actualOldReference = splitFileToChildPartitionsResult.getOldReference();

    // Assert
    assertEquals("42", actualOldReference.getJobId());
    assertEquals("42", actualOldReference.getPartitionId());
    assertEquals(2, actualNewReferences.size());
    FileReference getResult = actualNewReferences.get(0);
    assertEquals("Left Partition", getResult.getPartitionId());
    FileReference getResult2 = actualNewReferences.get(1);
    assertEquals("Right Partition", getResult2.getPartitionId());
    assertEquals(
        "SplitFileReferenceRequest{oldReference=FileReference{filename='foo.txt', partitionId='42', numberOfRecords=1,"
            + " jobId='42', lastStateStoreUpdateTime=1970-01-01T00:00:00Z, countApproximate=true, onlyContainsDataF"
            + "orThisPartition=true}, newReferences=[FileReference{filename='foo.txt', partitionId='Left Partition',"
            + " numberOfRecords=0, jobId='null', lastStateStoreUpdateTime=null, countApproximate=true, onlyContains"
            + "DataForThisPartition=false}, FileReference{filename='foo.txt', partitionId='Right Partition',"
            + " numberOfRecords=0, jobId='null', lastStateStoreUpdateTime=null, countApproximate=true, onlyContains"
            + "DataForThisPartition=false}]}",
        actualToStringResult);
    assertEquals("foo.txt", getResult.getFilename());
    assertEquals("foo.txt", getResult2.getFilename());
    assertEquals("foo.txt", actualOldReference.getFilename());
    assertNull(getResult.getJobId());
    assertNull(getResult2.getJobId());
    assertNull(getResult.getLastStateStoreUpdateTime());
    assertNull(getResult2.getLastStateStoreUpdateTime());
    Instant lastStateStoreUpdateTime = actualOldReference.getLastStateStoreUpdateTime();
    assertEquals(0, lastStateStoreUpdateTime.getNano());
    assertEquals(0L, getResult.getNumberOfRecords().longValue());
    assertEquals(0L, getResult2.getNumberOfRecords().longValue());
    assertEquals(0L, lastStateStoreUpdateTime.getEpochSecond());
    assertEquals(1L, actualOldReference.getNumberOfRecords().longValue());
    assertFalse(getResult.onlyContainsDataForThisPartition());
    assertFalse(getResult2.onlyContainsDataForThisPartition());
    assertTrue(getResult.isCountApproximate());
    assertTrue(getResult2.isCountApproximate());
    assertTrue(actualOldReference.isCountApproximate());
    assertTrue(actualOldReference.onlyContainsDataForThisPartition());
  }

  /**
   * Test {@link SplitFileReferenceRequest#equals(Object)}, and {@link SplitFileReferenceRequest#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link SplitFileReferenceRequest#equals(Object)}
   *   <li>{@link SplitFileReferenceRequest#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SplitFileReferenceRequest.equals(Object)", "int SplitFileReferenceRequest.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference file = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    SplitFileReferenceRequest splitFileToChildPartitionsResult = SplitFileReferenceRequest
        .splitFileToChildPartitions(file, "Left Partition", "Right Partition");
    Builder jobIdResult2 = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference file2 = jobIdResult2
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    SplitFileReferenceRequest splitFileToChildPartitionsResult2 = SplitFileReferenceRequest
        .splitFileToChildPartitions(file2, "Left Partition", "Right Partition");

    // Act and Assert
    assertEquals(splitFileToChildPartitionsResult, splitFileToChildPartitionsResult2);
    int expectedHashCodeResult = splitFileToChildPartitionsResult.hashCode();
    assertEquals(expectedHashCodeResult, splitFileToChildPartitionsResult2.hashCode());
  }

  /**
   * Test {@link SplitFileReferenceRequest#equals(Object)}, and {@link SplitFileReferenceRequest#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link SplitFileReferenceRequest#equals(Object)}
   *   <li>{@link SplitFileReferenceRequest#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SplitFileReferenceRequest.equals(Object)", "int SplitFileReferenceRequest.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference file = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    SplitFileReferenceRequest splitFileToChildPartitionsResult = SplitFileReferenceRequest
        .splitFileToChildPartitions(file, "Left Partition", "Right Partition");

    // Act and Assert
    assertEquals(splitFileToChildPartitionsResult, splitFileToChildPartitionsResult);
    int expectedHashCodeResult = splitFileToChildPartitionsResult.hashCode();
    assertEquals(expectedHashCodeResult, splitFileToChildPartitionsResult.hashCode());
  }

  /**
   * Test {@link SplitFileReferenceRequest#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link SplitFileReferenceRequest#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SplitFileReferenceRequest.equals(Object)", "int SplitFileReferenceRequest.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    Builder jobIdResult = FileReference.builder().countApproximate(false).filename("foo.txt").jobId("42");
    FileReference file = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    SplitFileReferenceRequest splitFileToChildPartitionsResult = SplitFileReferenceRequest
        .splitFileToChildPartitions(file, "Left Partition", "Right Partition");
    Builder jobIdResult2 = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference file2 = jobIdResult2
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();

    // Act and Assert
    assertNotEquals(splitFileToChildPartitionsResult,
        SplitFileReferenceRequest.splitFileToChildPartitions(file2, "Left Partition", "Right Partition"));
  }

  /**
   * Test {@link SplitFileReferenceRequest#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link SplitFileReferenceRequest#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SplitFileReferenceRequest.equals(Object)", "int SplitFileReferenceRequest.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference file = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    SplitFileReferenceRequest splitFileToChildPartitionsResult = SplitFileReferenceRequest
        .splitFileToChildPartitions(file, "java.util.List", "Right Partition");
    Builder jobIdResult2 = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference file2 = jobIdResult2
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();

    // Act and Assert
    assertNotEquals(splitFileToChildPartitionsResult,
        SplitFileReferenceRequest.splitFileToChildPartitions(file2, "Left Partition", "Right Partition"));
  }

  /**
   * Test {@link SplitFileReferenceRequest#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link SplitFileReferenceRequest#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SplitFileReferenceRequest.equals(Object)", "int SplitFileReferenceRequest.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange
    Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference file = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();

    // Act and Assert
    assertNotEquals(SplitFileReferenceRequest.splitFileToChildPartitions(file, "Left Partition", "Right Partition"),
        null);
  }

  /**
   * Test {@link SplitFileReferenceRequest#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link SplitFileReferenceRequest#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SplitFileReferenceRequest.equals(Object)", "int SplitFileReferenceRequest.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange
    Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference file = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();

    // Act and Assert
    assertNotEquals(SplitFileReferenceRequest.splitFileToChildPartitions(file, "Left Partition", "Right Partition"),
        "Different type to SplitFileReferenceRequest");
  }
}
