package sleeper.core.statestore;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.statestore.AllReferencesToAFile.Builder;

class AllReferencesToAFileDiffblueTest {
  /**
   * Test Builder {@link Builder#build()}.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Builder#build()}
   *   <li>{@link Builder#filename(String)}
   *   <li>{@link Builder#lastStateStoreUpdateTime(Instant)}
   *   <li>{@link Builder#references(List)}
   * </ul>
   */
  @Test
  @DisplayName("Test Builder build()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"AllReferencesToAFile Builder.build()", "Builder Builder.filename(String)",
      "Builder Builder.lastStateStoreUpdateTime(Instant)", "Builder Builder.references(List)"})
  void testBuilderBuild() {
    // Arrange
    Builder filenameResult = AllReferencesToAFile.builder().filename("foo.txt");
    Builder lastStateStoreUpdateTimeResult = filenameResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    ArrayList<FileReference> references = new ArrayList<>();

    // Act
    AllReferencesToAFile actualBuildResult = lastStateStoreUpdateTimeResult.references(references).build();

    // Assert
    assertEquals("foo.txt", actualBuildResult.getFilename());
    Instant lastStateStoreUpdateTime = actualBuildResult.getLastStateStoreUpdateTime();
    assertEquals(0, lastStateStoreUpdateTime.getNano());
    assertEquals(0, actualBuildResult.getReferenceCount());
    assertEquals(0L, lastStateStoreUpdateTime.getEpochSecond());
    List<FileReference> references2 = actualBuildResult.getReferences();
    assertTrue(references2.isEmpty());
    assertSame(references, references2);
  }

  /**
   * Test {@link AllReferencesToAFile#newFilesWithReferences(Collection)} with {@code Collection}.
   * <ul>
   *   <li>Then return first Filename is {@code foo.txt}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AllReferencesToAFile#newFilesWithReferences(Collection)}
   */
  @Test
  @DisplayName("Test newFilesWithReferences(Collection) with 'Collection'; then return first Filename is 'foo.txt'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List AllReferencesToAFile.newFilesWithReferences(Collection)"})
  void testNewFilesWithReferencesWithCollection_thenReturnFirstFilenameIsFooTxt() {
    // Arrange
    ArrayList<FileReference> references = new ArrayList<>();
    FileReference.Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference buildResult = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    references.add(buildResult);

    // Act
    List<AllReferencesToAFile> actualNewFilesWithReferencesResult = AllReferencesToAFile
        .newFilesWithReferences(references);

    // Assert
    assertEquals(1, actualNewFilesWithReferencesResult.size());
    AllReferencesToAFile getResult = actualNewFilesWithReferencesResult.get(0);
    assertEquals("foo.txt", getResult.getFilename());
    List<FileReference> references2 = getResult.getReferences();
    assertEquals(1, references2.size());
    assertEquals("foo.txt", references2.get(0).getFilename());
    assertEquals(1, getResult.getReferenceCount());
  }

  /**
   * Test {@link AllReferencesToAFile#newFilesWithReferences(Collection)} with {@code Collection}.
   * <ul>
   *   <li>Then return first References size is two.</li>
   * </ul>
   * <p>
   * Method under test: {@link AllReferencesToAFile#newFilesWithReferences(Collection)}
   */
  @Test
  @DisplayName("Test newFilesWithReferences(Collection) with 'Collection'; then return first References size is two")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List AllReferencesToAFile.newFilesWithReferences(Collection)"})
  void testNewFilesWithReferencesWithCollection_thenReturnFirstReferencesSizeIsTwo() {
    // Arrange
    ArrayList<FileReference> references = new ArrayList<>();
    FileReference.Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference buildResult = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    references.add(buildResult);
    FileReference.Builder jobIdResult2 = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference buildResult2 = jobIdResult2
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    references.add(buildResult2);

    // Act
    List<AllReferencesToAFile> actualNewFilesWithReferencesResult = AllReferencesToAFile
        .newFilesWithReferences(references);

    // Assert
    assertEquals(1, actualNewFilesWithReferencesResult.size());
    AllReferencesToAFile getResult = actualNewFilesWithReferencesResult.get(0);
    assertEquals(2, getResult.getReferences().size());
    assertEquals(2, getResult.getReferenceCount());
  }

  /**
   * Test {@link AllReferencesToAFile#newFilesWithReferences(Collection)} with {@code Collection}.
   * <ul>
   *   <li>Then return size is two.</li>
   * </ul>
   * <p>
   * Method under test: {@link AllReferencesToAFile#newFilesWithReferences(Collection)}
   */
  @Test
  @DisplayName("Test newFilesWithReferences(Collection) with 'Collection'; then return size is two")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List AllReferencesToAFile.newFilesWithReferences(Collection)"})
  void testNewFilesWithReferencesWithCollection_thenReturnSizeIsTwo() {
    // Arrange
    ArrayList<FileReference> references = new ArrayList<>();
    FileReference.Builder jobIdResult = FileReference.builder()
        .countApproximate(true)
        .filename("filename must not be null")
        .jobId("42");
    FileReference buildResult = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    references.add(buildResult);
    FileReference.Builder jobIdResult2 = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference buildResult2 = jobIdResult2
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    references.add(buildResult2);

    // Act
    List<AllReferencesToAFile> actualNewFilesWithReferencesResult = AllReferencesToAFile
        .newFilesWithReferences(references);

    // Assert
    assertEquals(2, actualNewFilesWithReferencesResult.size());
    AllReferencesToAFile getResult = actualNewFilesWithReferencesResult.get(1);
    List<FileReference> references2 = getResult.getReferences();
    assertEquals(1, references2.size());
    FileReference getResult2 = references2.get(0);
    assertEquals("42", getResult2.getJobId());
    assertEquals("42", getResult2.getPartitionId());
    AllReferencesToAFile getResult3 = actualNewFilesWithReferencesResult.get(0);
    assertEquals("filename must not be null", getResult3.getFilename());
    List<FileReference> references3 = getResult3.getReferences();
    assertEquals(1, references3.size());
    assertEquals("filename must not be null", references3.get(0).getFilename());
    assertEquals("foo.txt", getResult.getFilename());
    assertEquals("foo.txt", getResult2.getFilename());
    assertNull(getResult.getLastStateStoreUpdateTime());
    assertEquals(1, getResult.getReferenceCount());
    assertEquals(1L, getResult2.getNumberOfRecords().longValue());
    assertTrue(getResult2.isCountApproximate());
    assertTrue(getResult2.onlyContainsDataForThisPartition());
  }

  /**
   * Test {@link AllReferencesToAFile#newFilesWithReferences(Collection)} with {@code Collection}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link AllReferencesToAFile#newFilesWithReferences(Collection)}
   */
  @Test
  @DisplayName("Test newFilesWithReferences(Collection) with 'Collection'; when ArrayList(); then return Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List AllReferencesToAFile.newFilesWithReferences(Collection)"})
  void testNewFilesWithReferencesWithCollection_whenArrayList_thenReturnEmpty() {
    // Arrange and Act
    List<AllReferencesToAFile> actualNewFilesWithReferencesResult = AllReferencesToAFile
        .newFilesWithReferences(new ArrayList<>());

    // Assert
    assertTrue(actualNewFilesWithReferencesResult.isEmpty());
  }

  /**
   * Test {@link AllReferencesToAFile#newFilesWithReferences(Stream)} with {@code Stream}.
   * <p>
   * Method under test: {@link AllReferencesToAFile#newFilesWithReferences(Stream)}
   */
  @Test
  @DisplayName("Test newFilesWithReferences(Stream) with 'Stream'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream AllReferencesToAFile.newFilesWithReferences(Stream)"})
  void testNewFilesWithReferencesWithStream() {
    // Arrange
    ArrayList<FileReference> fileReferenceList = new ArrayList<>();
    FileReference.Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference buildResult = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    fileReferenceList.add(buildResult);
    Stream<FileReference> references = fileReferenceList.stream();

    // Act
    Stream<AllReferencesToAFile> actualNewFilesWithReferencesResult = AllReferencesToAFile
        .newFilesWithReferences(references);

    // Assert
    List<AllReferencesToAFile> collectResult = actualNewFilesWithReferencesResult.limit(5).collect(Collectors.toList());
    assertEquals(1, collectResult.size());
    AllReferencesToAFile getResult = collectResult.get(0);
    assertEquals("foo.txt", getResult.getFilename());
    assertNull(getResult.getLastStateStoreUpdateTime());
    assertEquals(1, getResult.getReferenceCount());
    assertEquals(fileReferenceList, getResult.getReferences());
  }

  /**
   * Test {@link AllReferencesToAFile#newFilesWithReferences(Stream)} with {@code Stream}.
   * <p>
   * Method under test: {@link AllReferencesToAFile#newFilesWithReferences(Stream)}
   */
  @Test
  @DisplayName("Test newFilesWithReferences(Stream) with 'Stream'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream AllReferencesToAFile.newFilesWithReferences(Stream)"})
  void testNewFilesWithReferencesWithStream2() {
    // Arrange
    ArrayList<FileReference> fileReferenceList = new ArrayList<>();
    FileReference.Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference buildResult = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    fileReferenceList.add(buildResult);
    FileReference.Builder jobIdResult2 = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference buildResult2 = jobIdResult2
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    fileReferenceList.add(buildResult2);
    Stream<FileReference> references = fileReferenceList.stream();

    // Act
    Stream<AllReferencesToAFile> actualNewFilesWithReferencesResult = AllReferencesToAFile
        .newFilesWithReferences(references);

    // Assert
    List<AllReferencesToAFile> collectResult = actualNewFilesWithReferencesResult.limit(5).collect(Collectors.toList());
    assertEquals(1, collectResult.size());
    AllReferencesToAFile getResult = collectResult.get(0);
    assertEquals(2, getResult.getReferences().size());
    assertEquals(2, getResult.getReferenceCount());
  }

  /**
   * Test {@link AllReferencesToAFile#newFilesWithReferences(Stream)} with {@code Stream}.
   * <ul>
   *   <li>Then return limit five collect toList Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link AllReferencesToAFile#newFilesWithReferences(Stream)}
   */
  @Test
  @DisplayName("Test newFilesWithReferences(Stream) with 'Stream'; then return limit five collect toList Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream AllReferencesToAFile.newFilesWithReferences(Stream)"})
  void testNewFilesWithReferencesWithStream_thenReturnLimitFiveCollectToListEmpty() {
    // Arrange
    ArrayList<FileReference> fileReferenceList = new ArrayList<>();
    Stream<FileReference> references = fileReferenceList.stream();

    // Act
    Stream<AllReferencesToAFile> actualNewFilesWithReferencesResult = AllReferencesToAFile
        .newFilesWithReferences(references);

    // Assert
    assertTrue(actualNewFilesWithReferencesResult.limit(5).collect(Collectors.toList()).isEmpty());
  }

  /**
   * Test {@link AllReferencesToAFile#withCreatedUpdateTime(Instant)}.
   * <p>
   * Method under test: {@link AllReferencesToAFile#withCreatedUpdateTime(Instant)}
   */
  @Test
  @DisplayName("Test withCreatedUpdateTime(Instant)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"AllReferencesToAFile AllReferencesToAFile.withCreatedUpdateTime(Instant)"})
  void testWithCreatedUpdateTime() {
    // Arrange
    Builder filenameResult = AllReferencesToAFile.builder().filename("foo.txt");
    Builder lastStateStoreUpdateTimeResult = filenameResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    AllReferencesToAFile buildResult = lastStateStoreUpdateTimeResult.references(new ArrayList<>()).build();

    // Act and Assert
    assertEquals(buildResult,
        buildResult.withCreatedUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
  }

  /**
   * Test {@link AllReferencesToAFile#withCreatedUpdateTime(Instant)}.
   * <ul>
   *   <li>Then return ReferenceCount is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link AllReferencesToAFile#withCreatedUpdateTime(Instant)}
   */
  @Test
  @DisplayName("Test withCreatedUpdateTime(Instant); then return ReferenceCount is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"AllReferencesToAFile AllReferencesToAFile.withCreatedUpdateTime(Instant)"})
  void testWithCreatedUpdateTime_thenReturnReferenceCountIsOne() {
    // Arrange
    ArrayList<FileReference> references = new ArrayList<>();
    FileReference.Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference buildResult = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    references.add(buildResult);
    Builder filenameResult = AllReferencesToAFile.builder().filename("foo.txt");
    AllReferencesToAFile buildResult2 = filenameResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .references(references)
        .build();

    // Act
    AllReferencesToAFile actualWithCreatedUpdateTimeResult = buildResult2
        .withCreatedUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Assert
    assertEquals(1, actualWithCreatedUpdateTimeResult.getReferenceCount());
    assertEquals(references, actualWithCreatedUpdateTimeResult.getReferences());
  }

  /**
   * Test {@link AllReferencesToAFile#withCreatedUpdateTime(Instant)}.
   * <ul>
   *   <li>Then return ReferenceCount is two.</li>
   * </ul>
   * <p>
   * Method under test: {@link AllReferencesToAFile#withCreatedUpdateTime(Instant)}
   */
  @Test
  @DisplayName("Test withCreatedUpdateTime(Instant); then return ReferenceCount is two")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"AllReferencesToAFile AllReferencesToAFile.withCreatedUpdateTime(Instant)"})
  void testWithCreatedUpdateTime_thenReturnReferenceCountIsTwo() {
    // Arrange
    ArrayList<FileReference> references = new ArrayList<>();
    FileReference.Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference buildResult = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    references.add(buildResult);
    FileReference.Builder jobIdResult2 = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference buildResult2 = jobIdResult2
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    references.add(buildResult2);
    Builder filenameResult = AllReferencesToAFile.builder().filename("foo.txt");
    AllReferencesToAFile buildResult3 = filenameResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .references(references)
        .build();

    // Act
    AllReferencesToAFile actualWithCreatedUpdateTimeResult = buildResult3
        .withCreatedUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Assert
    assertEquals(2, actualWithCreatedUpdateTimeResult.getReferenceCount());
    assertEquals(references, actualWithCreatedUpdateTimeResult.getReferences());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link AllReferencesToAFile#toString()}
   *   <li>{@link AllReferencesToAFile#getFilename()}
   *   <li>{@link AllReferencesToAFile#getLastStateStoreUpdateTime()}
   *   <li>{@link AllReferencesToAFile#getReferences()}
   *   <li>{@link AllReferencesToAFile#toBuilder()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String AllReferencesToAFile.getFilename()",
      "Instant AllReferencesToAFile.getLastStateStoreUpdateTime()", "List AllReferencesToAFile.getReferences()",
      "Builder AllReferencesToAFile.toBuilder()", "String AllReferencesToAFile.toString()"})
  void testGettersAndSetters() {
    // Arrange
    Builder filenameResult = AllReferencesToAFile.builder().filename("foo.txt");
    Builder lastStateStoreUpdateTimeResult = filenameResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    ArrayList<FileReference> references = new ArrayList<>();
    AllReferencesToAFile buildResult = lastStateStoreUpdateTimeResult.references(references).build();

    // Act
    String actualToStringResult = buildResult.toString();
    String actualFilename = buildResult.getFilename();
    Instant actualLastStateStoreUpdateTime = buildResult.getLastStateStoreUpdateTime();
    List<FileReference> actualReferences = buildResult.getReferences();
    buildResult.toBuilder();

    // Assert
    assertEquals("AllReferencesToAFile{filename=foo.txt, lastStateStoreUpdateTime=1970-01-01T00:00:00Z, references=[]}",
        actualToStringResult);
    assertEquals("foo.txt", actualFilename);
    assertTrue(actualReferences.isEmpty());
    assertSame(references, actualReferences);
    assertSame(actualLastStateStoreUpdateTime.EPOCH, actualLastStateStoreUpdateTime);
  }

  /**
   * Test {@link AllReferencesToAFile#getReferenceCount()}.
   * <p>
   * Method under test: {@link AllReferencesToAFile#getReferenceCount()}
   */
  @Test
  @DisplayName("Test getReferenceCount()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"int AllReferencesToAFile.getReferenceCount()"})
  void testGetReferenceCount() {
    // Arrange
    Builder filenameResult = AllReferencesToAFile.builder().filename("foo.txt");
    Builder lastStateStoreUpdateTimeResult = filenameResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    AllReferencesToAFile buildResult = lastStateStoreUpdateTimeResult.references(new ArrayList<>()).build();

    // Act and Assert
    assertEquals(0, buildResult.getReferenceCount());
  }

  /**
   * Test {@link AllReferencesToAFile#equals(Object)}, and {@link AllReferencesToAFile#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link AllReferencesToAFile#equals(Object)}
   *   <li>{@link AllReferencesToAFile#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean AllReferencesToAFile.equals(Object)", "int AllReferencesToAFile.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    Builder filenameResult = AllReferencesToAFile.builder().filename("foo.txt");
    Builder lastStateStoreUpdateTimeResult = filenameResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    AllReferencesToAFile buildResult = lastStateStoreUpdateTimeResult.references(new ArrayList<>()).build();
    Builder filenameResult2 = AllReferencesToAFile.builder().filename("foo.txt");
    Builder lastStateStoreUpdateTimeResult2 = filenameResult2
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    AllReferencesToAFile buildResult2 = lastStateStoreUpdateTimeResult2.references(new ArrayList<>()).build();

    // Act and Assert
    assertEquals(buildResult, buildResult2);
    int expectedHashCodeResult = buildResult.hashCode();
    assertEquals(expectedHashCodeResult, buildResult2.hashCode());
  }

  /**
   * Test {@link AllReferencesToAFile#equals(Object)}, and {@link AllReferencesToAFile#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link AllReferencesToAFile#equals(Object)}
   *   <li>{@link AllReferencesToAFile#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean AllReferencesToAFile.equals(Object)", "int AllReferencesToAFile.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    Builder filenameResult = AllReferencesToAFile.builder().filename("foo.txt");
    Builder lastStateStoreUpdateTimeResult = filenameResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    AllReferencesToAFile buildResult = lastStateStoreUpdateTimeResult.references(new ArrayList<>()).build();

    // Act and Assert
    assertEquals(buildResult, buildResult);
    int expectedHashCodeResult = buildResult.hashCode();
    assertEquals(expectedHashCodeResult, buildResult.hashCode());
  }

  /**
   * Test {@link AllReferencesToAFile#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link AllReferencesToAFile#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean AllReferencesToAFile.equals(Object)", "int AllReferencesToAFile.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    Builder filenameResult = AllReferencesToAFile.builder().filename("Filename");
    Builder lastStateStoreUpdateTimeResult = filenameResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    AllReferencesToAFile buildResult = lastStateStoreUpdateTimeResult.references(new ArrayList<>()).build();
    Builder filenameResult2 = AllReferencesToAFile.builder().filename("foo.txt");
    Builder lastStateStoreUpdateTimeResult2 = filenameResult2
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    AllReferencesToAFile buildResult2 = lastStateStoreUpdateTimeResult2.references(new ArrayList<>()).build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link AllReferencesToAFile#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link AllReferencesToAFile#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean AllReferencesToAFile.equals(Object)", "int AllReferencesToAFile.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    Builder filenameResult = AllReferencesToAFile.builder().filename("foo.txt");
    Builder lastStateStoreUpdateTimeResult = filenameResult
        .lastStateStoreUpdateTime(LocalDate.now().atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    AllReferencesToAFile buildResult = lastStateStoreUpdateTimeResult.references(new ArrayList<>()).build();
    Builder filenameResult2 = AllReferencesToAFile.builder().filename("foo.txt");
    Builder lastStateStoreUpdateTimeResult2 = filenameResult2
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    AllReferencesToAFile buildResult2 = lastStateStoreUpdateTimeResult2.references(new ArrayList<>()).build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link AllReferencesToAFile#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link AllReferencesToAFile#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean AllReferencesToAFile.equals(Object)", "int AllReferencesToAFile.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual3() {
    // Arrange
    ArrayList<FileReference> references = new ArrayList<>();
    FileReference.Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference buildResult = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    references.add(buildResult);
    Builder filenameResult = AllReferencesToAFile.builder().filename("foo.txt");
    AllReferencesToAFile buildResult2 = filenameResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .references(references)
        .build();
    Builder filenameResult2 = AllReferencesToAFile.builder().filename("foo.txt");
    Builder lastStateStoreUpdateTimeResult = filenameResult2
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    AllReferencesToAFile buildResult3 = lastStateStoreUpdateTimeResult.references(new ArrayList<>()).build();

    // Act and Assert
    assertNotEquals(buildResult2, buildResult3);
  }

  /**
   * Test {@link AllReferencesToAFile#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link AllReferencesToAFile#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean AllReferencesToAFile.equals(Object)", "int AllReferencesToAFile.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange
    Builder filenameResult = AllReferencesToAFile.builder().filename("foo.txt");
    Builder lastStateStoreUpdateTimeResult = filenameResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    AllReferencesToAFile buildResult = lastStateStoreUpdateTimeResult.references(new ArrayList<>()).build();

    // Act and Assert
    assertNotEquals(buildResult, null);
  }

  /**
   * Test {@link AllReferencesToAFile#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link AllReferencesToAFile#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean AllReferencesToAFile.equals(Object)", "int AllReferencesToAFile.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange
    Builder filenameResult = AllReferencesToAFile.builder().filename("foo.txt");
    Builder lastStateStoreUpdateTimeResult = filenameResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    AllReferencesToAFile buildResult = lastStateStoreUpdateTimeResult.references(new ArrayList<>()).build();

    // Act and Assert
    assertNotEquals(buildResult, "Different type to AllReferencesToAFile");
  }
}
