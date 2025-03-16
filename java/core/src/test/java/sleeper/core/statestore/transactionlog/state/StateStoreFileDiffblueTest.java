package sleeper.core.statestore.transactionlog.state;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Collection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.statestore.AllReferencesToAFile;
import sleeper.core.statestore.AllReferencesToAFile.Builder;
import sleeper.core.statestore.FileReference;

class StateStoreFileDiffblueTest {
  /**
   * Test {@link StateStoreFile#StateStoreFile(String, Instant, Collection)}.
   * <ul>
   *   <li>Then return References size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreFile#StateStoreFile(String, Instant, Collection)}
   */
  @Test
  @DisplayName("Test new StateStoreFile(String, Instant, Collection); then return References size is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void StateStoreFile.<init>(String, Instant, Collection)"})
  void testNewStateStoreFile_thenReturnReferencesSizeIsOne() {
    // Arrange
    Instant lastStateStoreUpdateTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

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
    StateStoreFile actualStateStoreFile = new StateStoreFile("foo.txt", lastStateStoreUpdateTime, references);

    // Assert
    assertEquals("foo.txt", actualStateStoreFile.getFilename());
    assertEquals(1, actualStateStoreFile.getReferences().size());
    Instant expectedLastStateStoreUpdateTime = lastStateStoreUpdateTime.EPOCH;
    assertSame(expectedLastStateStoreUpdateTime, actualStateStoreFile.getLastStateStoreUpdateTime());
  }

  /**
   * Test {@link StateStoreFile#StateStoreFile(String, Instant, Collection)}.
   * <ul>
   *   <li>Then return References size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreFile#StateStoreFile(String, Instant, Collection)}
   */
  @Test
  @DisplayName("Test new StateStoreFile(String, Instant, Collection); then return References size is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void StateStoreFile.<init>(String, Instant, Collection)"})
  void testNewStateStoreFile_thenReturnReferencesSizeIsOne2() {
    // Arrange
    Instant lastStateStoreUpdateTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

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
    StateStoreFile actualStateStoreFile = new StateStoreFile("foo.txt", lastStateStoreUpdateTime, references);

    // Assert
    assertEquals("foo.txt", actualStateStoreFile.getFilename());
    assertEquals(1, actualStateStoreFile.getReferences().size());
    Instant expectedLastStateStoreUpdateTime = lastStateStoreUpdateTime.EPOCH;
    assertSame(expectedLastStateStoreUpdateTime, actualStateStoreFile.getLastStateStoreUpdateTime());
  }

  /**
   * Test {@link StateStoreFile#StateStoreFile(String, Instant, Collection)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return References Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreFile#StateStoreFile(String, Instant, Collection)}
   */
  @Test
  @DisplayName("Test new StateStoreFile(String, Instant, Collection); when ArrayList(); then return References Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void StateStoreFile.<init>(String, Instant, Collection)"})
  void testNewStateStoreFile_whenArrayList_thenReturnReferencesEmpty() {
    // Arrange
    Instant lastStateStoreUpdateTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    // Act
    StateStoreFile actualStateStoreFile = new StateStoreFile("foo.txt", lastStateStoreUpdateTime, new ArrayList<>());

    // Assert
    assertEquals("foo.txt", actualStateStoreFile.getFilename());
    assertTrue(actualStateStoreFile.getReferences().isEmpty());
    Instant expectedLastStateStoreUpdateTime = lastStateStoreUpdateTime.EPOCH;
    assertSame(expectedLastStateStoreUpdateTime, actualStateStoreFile.getLastStateStoreUpdateTime());
  }

  /**
   * Test {@link StateStoreFile#from(AllReferencesToAFile)}.
   * <p>
   * Method under test: {@link StateStoreFile#from(AllReferencesToAFile)}
   */
  @Test
  @DisplayName("Test from(AllReferencesToAFile)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"StateStoreFile StateStoreFile.from(AllReferencesToAFile)"})
  void testFrom() {
    // Arrange
    Builder filenameResult = AllReferencesToAFile.builder().filename("foo.txt");
    Builder lastStateStoreUpdateTimeResult = filenameResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    AllReferencesToAFile model = lastStateStoreUpdateTimeResult.references(new ArrayList<>()).build();

    // Act
    StateStoreFile actualFromResult = StateStoreFile.from(model);

    // Assert
    assertEquals("foo.txt", actualFromResult.getFilename());
    Instant lastStateStoreUpdateTime = actualFromResult.getLastStateStoreUpdateTime();
    assertEquals(0, lastStateStoreUpdateTime.getNano());
    assertEquals(0L, lastStateStoreUpdateTime.getEpochSecond());
    assertTrue(actualFromResult.getReferences().isEmpty());
  }

  /**
   * Test {@link StateStoreFile#from(AllReferencesToAFile)}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return References Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreFile#from(AllReferencesToAFile)}
   */
  @Test
  @DisplayName("Test from(AllReferencesToAFile); given ArrayList(); then return References Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"StateStoreFile StateStoreFile.from(AllReferencesToAFile)"})
  void testFrom_givenArrayList_thenReturnReferencesEmpty() {
    // Arrange
    AllReferencesToAFile model = mock(AllReferencesToAFile.class);
    when(model.getFilename()).thenReturn("foo.txt");
    when(model.getLastStateStoreUpdateTime())
        .thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    when(model.getReferences()).thenReturn(new ArrayList<>());

    // Act
    StateStoreFile actualFromResult = StateStoreFile.from(model);

    // Assert
    verify(model).getFilename();
    verify(model).getLastStateStoreUpdateTime();
    verify(model).getReferences();
    assertEquals("foo.txt", actualFromResult.getFilename());
    Instant lastStateStoreUpdateTime = actualFromResult.getLastStateStoreUpdateTime();
    assertEquals(0, lastStateStoreUpdateTime.getNano());
    assertEquals(0L, lastStateStoreUpdateTime.getEpochSecond());
    assertTrue(actualFromResult.getReferences().isEmpty());
  }

  /**
   * Test {@link StateStoreFile#from(AllReferencesToAFile)}.
   * <ul>
   *   <li>Then return References size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreFile#from(AllReferencesToAFile)}
   */
  @Test
  @DisplayName("Test from(AllReferencesToAFile); then return References size is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"StateStoreFile StateStoreFile.from(AllReferencesToAFile)"})
  void testFrom_thenReturnReferencesSizeIsOne() {
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
    AllReferencesToAFile model = mock(AllReferencesToAFile.class);
    when(model.getFilename()).thenReturn("foo.txt");
    when(model.getLastStateStoreUpdateTime())
        .thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    when(model.getReferences()).thenReturn(fileReferenceList);

    // Act
    StateStoreFile actualFromResult = StateStoreFile.from(model);

    // Assert
    verify(model).getFilename();
    verify(model).getLastStateStoreUpdateTime();
    verify(model).getReferences();
    assertEquals("foo.txt", actualFromResult.getFilename());
    Instant lastStateStoreUpdateTime = actualFromResult.getLastStateStoreUpdateTime();
    assertEquals(0, lastStateStoreUpdateTime.getNano());
    assertEquals(0L, lastStateStoreUpdateTime.getEpochSecond());
    assertEquals(1, actualFromResult.getReferences().size());
  }

  /**
   * Test {@link StateStoreFile#from(AllReferencesToAFile)}.
   * <ul>
   *   <li>Then return References size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreFile#from(AllReferencesToAFile)}
   */
  @Test
  @DisplayName("Test from(AllReferencesToAFile); then return References size is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"StateStoreFile StateStoreFile.from(AllReferencesToAFile)"})
  void testFrom_thenReturnReferencesSizeIsOne2() {
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
    AllReferencesToAFile model = mock(AllReferencesToAFile.class);
    when(model.getFilename()).thenReturn("foo.txt");
    when(model.getLastStateStoreUpdateTime())
        .thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    when(model.getReferences()).thenReturn(fileReferenceList);

    // Act
    StateStoreFile actualFromResult = StateStoreFile.from(model);

    // Assert
    verify(model).getFilename();
    verify(model).getLastStateStoreUpdateTime();
    verify(model).getReferences();
    assertEquals("foo.txt", actualFromResult.getFilename());
    Instant lastStateStoreUpdateTime = actualFromResult.getLastStateStoreUpdateTime();
    assertEquals(0, lastStateStoreUpdateTime.getNano());
    assertEquals(0L, lastStateStoreUpdateTime.getEpochSecond());
    assertEquals(1, actualFromResult.getReferences().size());
  }

  /**
   * Test {@link StateStoreFile#newFile(Instant, AllReferencesToAFile)} with {@code updateTime}, {@code model}.
   * <p>
   * Method under test: {@link StateStoreFile#newFile(Instant, AllReferencesToAFile)}
   */
  @Test
  @DisplayName("Test newFile(Instant, AllReferencesToAFile) with 'updateTime', 'model'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"StateStoreFile StateStoreFile.newFile(Instant, AllReferencesToAFile)"})
  void testNewFileWithUpdateTimeModel() {
    // Arrange
    Instant updateTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    Builder filenameResult = AllReferencesToAFile.builder().filename("foo.txt");
    Builder lastStateStoreUpdateTimeResult = filenameResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    AllReferencesToAFile model = lastStateStoreUpdateTimeResult.references(new ArrayList<>()).build();

    // Act
    StateStoreFile actualNewFileResult = StateStoreFile.newFile(updateTime, model);

    // Assert
    assertEquals("foo.txt", actualNewFileResult.getFilename());
    assertTrue(actualNewFileResult.getReferences().isEmpty());
    Instant expectedLastStateStoreUpdateTime = updateTime.EPOCH;
    assertSame(expectedLastStateStoreUpdateTime, actualNewFileResult.getLastStateStoreUpdateTime());
  }

  /**
   * Test {@link StateStoreFile#newFile(Instant, AllReferencesToAFile)} with {@code updateTime}, {@code model}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return References Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreFile#newFile(Instant, AllReferencesToAFile)}
   */
  @Test
  @DisplayName("Test newFile(Instant, AllReferencesToAFile) with 'updateTime', 'model'; given ArrayList(); then return References Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"StateStoreFile StateStoreFile.newFile(Instant, AllReferencesToAFile)"})
  void testNewFileWithUpdateTimeModel_givenArrayList_thenReturnReferencesEmpty() {
    // Arrange
    Instant updateTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    AllReferencesToAFile model = mock(AllReferencesToAFile.class);
    when(model.getFilename()).thenReturn("foo.txt");
    when(model.getReferences()).thenReturn(new ArrayList<>());

    // Act
    StateStoreFile actualNewFileResult = StateStoreFile.newFile(updateTime, model);

    // Assert
    verify(model).getFilename();
    verify(model).getReferences();
    assertEquals("foo.txt", actualNewFileResult.getFilename());
    assertTrue(actualNewFileResult.getReferences().isEmpty());
    Instant expectedLastStateStoreUpdateTime = updateTime.EPOCH;
    assertSame(expectedLastStateStoreUpdateTime, actualNewFileResult.getLastStateStoreUpdateTime());
  }

  /**
   * Test {@link StateStoreFile#newFile(Instant, AllReferencesToAFile)} with {@code updateTime}, {@code model}.
   * <ul>
   *   <li>Then return References size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreFile#newFile(Instant, AllReferencesToAFile)}
   */
  @Test
  @DisplayName("Test newFile(Instant, AllReferencesToAFile) with 'updateTime', 'model'; then return References size is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"StateStoreFile StateStoreFile.newFile(Instant, AllReferencesToAFile)"})
  void testNewFileWithUpdateTimeModel_thenReturnReferencesSizeIsOne() {
    // Arrange
    Instant updateTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    ArrayList<FileReference> fileReferenceList = new ArrayList<>();
    FileReference.Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference buildResult = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    fileReferenceList.add(buildResult);
    AllReferencesToAFile model = mock(AllReferencesToAFile.class);
    when(model.getFilename()).thenReturn("foo.txt");
    when(model.getReferences()).thenReturn(fileReferenceList);

    // Act
    StateStoreFile actualNewFileResult = StateStoreFile.newFile(updateTime, model);

    // Assert
    verify(model).getFilename();
    verify(model).getReferences();
    assertEquals("foo.txt", actualNewFileResult.getFilename());
    assertEquals(1, actualNewFileResult.getReferences().size());
    Instant expectedLastStateStoreUpdateTime = updateTime.EPOCH;
    assertSame(expectedLastStateStoreUpdateTime, actualNewFileResult.getLastStateStoreUpdateTime());
  }

  /**
   * Test {@link StateStoreFile#newFile(Instant, AllReferencesToAFile)} with {@code updateTime}, {@code model}.
   * <ul>
   *   <li>Then return References size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreFile#newFile(Instant, AllReferencesToAFile)}
   */
  @Test
  @DisplayName("Test newFile(Instant, AllReferencesToAFile) with 'updateTime', 'model'; then return References size is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"StateStoreFile StateStoreFile.newFile(Instant, AllReferencesToAFile)"})
  void testNewFileWithUpdateTimeModel_thenReturnReferencesSizeIsOne2() {
    // Arrange
    Instant updateTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

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
    AllReferencesToAFile model = mock(AllReferencesToAFile.class);
    when(model.getFilename()).thenReturn("foo.txt");
    when(model.getReferences()).thenReturn(fileReferenceList);

    // Act
    StateStoreFile actualNewFileResult = StateStoreFile.newFile(updateTime, model);

    // Assert
    verify(model).getFilename();
    verify(model).getReferences();
    assertEquals("foo.txt", actualNewFileResult.getFilename());
    assertEquals(1, actualNewFileResult.getReferences().size());
    Instant expectedLastStateStoreUpdateTime = updateTime.EPOCH;
    assertSame(expectedLastStateStoreUpdateTime, actualNewFileResult.getLastStateStoreUpdateTime());
  }

  /**
   * Test {@link StateStoreFile#newFile(Instant, FileReference)} with {@code updateTime}, {@code reference}.
   * <ul>
   *   <li>Then return Filename is {@code foo.txt}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreFile#newFile(Instant, FileReference)}
   */
  @Test
  @DisplayName("Test newFile(Instant, FileReference) with 'updateTime', 'reference'; then return Filename is 'foo.txt'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"StateStoreFile StateStoreFile.newFile(Instant, FileReference)"})
  void testNewFileWithUpdateTimeReference_thenReturnFilenameIsFooTxt() {
    // Arrange
    Instant updateTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    FileReference.Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference reference = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();

    // Act
    StateStoreFile actualNewFileResult = StateStoreFile.newFile(updateTime, reference);

    // Assert
    assertEquals("foo.txt", actualNewFileResult.getFilename());
    assertEquals(1, actualNewFileResult.getReferences().size());
    Instant expectedLastStateStoreUpdateTime = updateTime.EPOCH;
    assertSame(expectedLastStateStoreUpdateTime, actualNewFileResult.getLastStateStoreUpdateTime());
  }

  /**
   * Test {@link StateStoreFile#toModel()}.
   * <p>
   * Method under test: {@link StateStoreFile#toModel()}
   */
  @Test
  @DisplayName("Test toModel()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"AllReferencesToAFile StateStoreFile.toModel()"})
  void testToModel() {
    // Arrange
    Builder filenameResult = AllReferencesToAFile.builder().filename("foo.txt");
    Builder lastStateStoreUpdateTimeResult = filenameResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    AllReferencesToAFile model = lastStateStoreUpdateTimeResult.references(new ArrayList<>()).build();

    // Act
    AllReferencesToAFile actualToModelResult = StateStoreFile.from(model).toModel();

    // Assert
    assertEquals("foo.txt", actualToModelResult.getFilename());
    Instant lastStateStoreUpdateTime = actualToModelResult.getLastStateStoreUpdateTime();
    assertEquals(0, lastStateStoreUpdateTime.getNano());
    assertEquals(0, actualToModelResult.getReferenceCount());
    assertEquals(0L, lastStateStoreUpdateTime.getEpochSecond());
    assertTrue(actualToModelResult.getReferences().isEmpty());
  }

  /**
   * Test {@link StateStoreFile#splitReferenceFromPartition(String, Collection, Instant)}.
   * <p>
   * Method under test: {@link StateStoreFile#splitReferenceFromPartition(String, Collection, Instant)}
   */
  @Test
  @DisplayName("Test splitReferenceFromPartition(String, Collection, Instant)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void StateStoreFile.splitReferenceFromPartition(String, Collection, Instant)"})
  void testSplitReferenceFromPartition() {
    // Arrange
    Builder filenameResult = AllReferencesToAFile.builder().filename("foo.txt");
    Builder lastStateStoreUpdateTimeResult = filenameResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    AllReferencesToAFile model = lastStateStoreUpdateTimeResult.references(new ArrayList<>()).build();
    StateStoreFile fromResult = StateStoreFile.from(model);
    ArrayList<FileReference> newReferences = new ArrayList<>();

    // Act
    fromResult.splitReferenceFromPartition("42", newReferences,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Assert that nothing has changed
    assertTrue(fromResult.getReferences().isEmpty());
  }

  /**
   * Test {@link StateStoreFile#splitReferenceFromPartition(String, Collection, Instant)}.
   * <p>
   * Method under test: {@link StateStoreFile#splitReferenceFromPartition(String, Collection, Instant)}
   */
  @Test
  @DisplayName("Test splitReferenceFromPartition(String, Collection, Instant)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void StateStoreFile.splitReferenceFromPartition(String, Collection, Instant)"})
  void testSplitReferenceFromPartition2() {
    // Arrange
    Builder filenameResult = AllReferencesToAFile.builder().filename("foo.txt");
    Builder lastStateStoreUpdateTimeResult = filenameResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    AllReferencesToAFile model = lastStateStoreUpdateTimeResult.references(new ArrayList<>()).build();
    StateStoreFile fromResult = StateStoreFile.from(model);

    ArrayList<FileReference> newReferences = new ArrayList<>();
    FileReference.Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference buildResult = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    newReferences.add(buildResult);

    // Act
    fromResult.splitReferenceFromPartition("42", newReferences,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Assert
    assertEquals(1, fromResult.getReferences().size());
  }

  /**
   * Test {@link StateStoreFile#splitReferenceFromPartition(String, Collection, Instant)}.
   * <p>
   * Method under test: {@link StateStoreFile#splitReferenceFromPartition(String, Collection, Instant)}
   */
  @Test
  @DisplayName("Test splitReferenceFromPartition(String, Collection, Instant)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void StateStoreFile.splitReferenceFromPartition(String, Collection, Instant)"})
  void testSplitReferenceFromPartition3() {
    // Arrange
    Builder filenameResult = AllReferencesToAFile.builder().filename("foo.txt");
    Builder lastStateStoreUpdateTimeResult = filenameResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    AllReferencesToAFile model = lastStateStoreUpdateTimeResult.references(new ArrayList<>()).build();
    StateStoreFile fromResult = StateStoreFile.from(model);

    ArrayList<FileReference> newReferences = new ArrayList<>();
    FileReference.Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference buildResult = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    newReferences.add(buildResult);
    FileReference.Builder jobIdResult2 = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference buildResult2 = jobIdResult2
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    newReferences.add(buildResult2);

    // Act
    fromResult.splitReferenceFromPartition("42", newReferences,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Assert
    assertEquals(1, fromResult.getReferences().size());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link StateStoreFile#toString()}
   *   <li>{@link StateStoreFile#getFilename()}
   *   <li>{@link StateStoreFile#getLastStateStoreUpdateTime()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String StateStoreFile.getFilename()", "Instant StateStoreFile.getLastStateStoreUpdateTime()",
      "String StateStoreFile.toString()"})
  void testGettersAndSetters() {
    // Arrange
    Builder filenameResult = AllReferencesToAFile.builder().filename("foo.txt");
    Builder lastStateStoreUpdateTimeResult = filenameResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    AllReferencesToAFile model = lastStateStoreUpdateTimeResult.references(new ArrayList<>()).build();
    StateStoreFile fromResult = StateStoreFile.from(model);

    // Act
    String actualToStringResult = fromResult.toString();
    String actualFilename = fromResult.getFilename();
    Instant actualLastStateStoreUpdateTime = fromResult.getLastStateStoreUpdateTime();

    // Assert
    assertEquals("StateStoreFile{filename=foo.txt, lastStateStoreUpdateTime=1970-01-01T00:00:00Z, references=[]}",
        actualToStringResult);
    assertEquals("foo.txt", actualFilename);
    assertSame(actualLastStateStoreUpdateTime.EPOCH, actualLastStateStoreUpdateTime);
  }

  /**
   * Test {@link StateStoreFile#getReferences()}.
   * <p>
   * Method under test: {@link StateStoreFile#getReferences()}
   */
  @Test
  @DisplayName("Test getReferences()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Collection StateStoreFile.getReferences()"})
  void testGetReferences() {
    // Arrange
    Builder filenameResult = AllReferencesToAFile.builder().filename("foo.txt");
    Builder lastStateStoreUpdateTimeResult = filenameResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    AllReferencesToAFile model = lastStateStoreUpdateTimeResult.references(new ArrayList<>()).build();

    // Act and Assert
    assertTrue(StateStoreFile.from(model).getReferences().isEmpty());
  }

  /**
   * Test {@link StateStoreFile#getReferenceForPartitionId(String)}.
   * <p>
   * Method under test: {@link StateStoreFile#getReferenceForPartitionId(String)}
   */
  @Test
  @DisplayName("Test getReferenceForPartitionId(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.util.Optional StateStoreFile.getReferenceForPartitionId(String)"})
  void testGetReferenceForPartitionId() {
    // Arrange
    Builder filenameResult = AllReferencesToAFile.builder().filename("foo.txt");
    Builder lastStateStoreUpdateTimeResult = filenameResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    AllReferencesToAFile model = lastStateStoreUpdateTimeResult.references(new ArrayList<>()).build();

    // Act and Assert
    assertFalse(StateStoreFile.from(model).getReferenceForPartitionId("42").isPresent());
  }

  /**
   * Test {@link StateStoreFile#equals(Object)}, and {@link StateStoreFile#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link StateStoreFile#equals(Object)}
   *   <li>{@link StateStoreFile#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean StateStoreFile.equals(Object)", "int StateStoreFile.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    Builder filenameResult = AllReferencesToAFile.builder().filename("foo.txt");
    Builder lastStateStoreUpdateTimeResult = filenameResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    AllReferencesToAFile model = lastStateStoreUpdateTimeResult.references(new ArrayList<>()).build();
    StateStoreFile fromResult = StateStoreFile.from(model);
    Builder filenameResult2 = AllReferencesToAFile.builder().filename("foo.txt");
    Builder lastStateStoreUpdateTimeResult2 = filenameResult2
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    AllReferencesToAFile model2 = lastStateStoreUpdateTimeResult2.references(new ArrayList<>()).build();
    StateStoreFile fromResult2 = StateStoreFile.from(model2);

    // Act and Assert
    assertEquals(fromResult, fromResult2);
    int expectedHashCodeResult = fromResult.hashCode();
    assertEquals(expectedHashCodeResult, fromResult2.hashCode());
  }

  /**
   * Test {@link StateStoreFile#equals(Object)}, and {@link StateStoreFile#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link StateStoreFile#equals(Object)}
   *   <li>{@link StateStoreFile#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean StateStoreFile.equals(Object)", "int StateStoreFile.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    Builder filenameResult = AllReferencesToAFile.builder().filename("foo.txt");
    Builder lastStateStoreUpdateTimeResult = filenameResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    AllReferencesToAFile model = lastStateStoreUpdateTimeResult.references(new ArrayList<>()).build();
    StateStoreFile fromResult = StateStoreFile.from(model);

    // Act and Assert
    assertEquals(fromResult, fromResult);
    int expectedHashCodeResult = fromResult.hashCode();
    assertEquals(expectedHashCodeResult, fromResult.hashCode());
  }

  /**
   * Test {@link StateStoreFile#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreFile#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean StateStoreFile.equals(Object)", "int StateStoreFile.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    Builder filenameResult = AllReferencesToAFile.builder().filename("Filename");
    Builder lastStateStoreUpdateTimeResult = filenameResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    AllReferencesToAFile model = lastStateStoreUpdateTimeResult.references(new ArrayList<>()).build();
    StateStoreFile fromResult = StateStoreFile.from(model);
    Builder filenameResult2 = AllReferencesToAFile.builder().filename("foo.txt");
    Builder lastStateStoreUpdateTimeResult2 = filenameResult2
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    AllReferencesToAFile model2 = lastStateStoreUpdateTimeResult2.references(new ArrayList<>()).build();

    // Act and Assert
    assertNotEquals(fromResult, StateStoreFile.from(model2));
  }

  /**
   * Test {@link StateStoreFile#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreFile#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean StateStoreFile.equals(Object)", "int StateStoreFile.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    Builder filenameResult = AllReferencesToAFile.builder().filename("foo.txt");
    Builder lastStateStoreUpdateTimeResult = filenameResult
        .lastStateStoreUpdateTime(LocalDate.now().atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    AllReferencesToAFile model = lastStateStoreUpdateTimeResult.references(new ArrayList<>()).build();
    StateStoreFile fromResult = StateStoreFile.from(model);
    Builder filenameResult2 = AllReferencesToAFile.builder().filename("foo.txt");
    Builder lastStateStoreUpdateTimeResult2 = filenameResult2
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    AllReferencesToAFile model2 = lastStateStoreUpdateTimeResult2.references(new ArrayList<>()).build();

    // Act and Assert
    assertNotEquals(fromResult, StateStoreFile.from(model2));
  }

  /**
   * Test {@link StateStoreFile#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreFile#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean StateStoreFile.equals(Object)", "int StateStoreFile.hashCode()"})
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
    AllReferencesToAFile model = filenameResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .references(references)
        .build();
    StateStoreFile fromResult = StateStoreFile.from(model);
    Builder filenameResult2 = AllReferencesToAFile.builder().filename("foo.txt");
    Builder lastStateStoreUpdateTimeResult = filenameResult2
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    AllReferencesToAFile model2 = lastStateStoreUpdateTimeResult.references(new ArrayList<>()).build();

    // Act and Assert
    assertNotEquals(fromResult, StateStoreFile.from(model2));
  }

  /**
   * Test {@link StateStoreFile#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreFile#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean StateStoreFile.equals(Object)", "int StateStoreFile.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange
    Builder filenameResult = AllReferencesToAFile.builder().filename("foo.txt");
    Builder lastStateStoreUpdateTimeResult = filenameResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    AllReferencesToAFile model = lastStateStoreUpdateTimeResult.references(new ArrayList<>()).build();

    // Act and Assert
    assertNotEquals(StateStoreFile.from(model), null);
  }

  /**
   * Test {@link StateStoreFile#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreFile#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean StateStoreFile.equals(Object)", "int StateStoreFile.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange
    Builder filenameResult = AllReferencesToAFile.builder().filename("foo.txt");
    Builder lastStateStoreUpdateTimeResult = filenameResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    AllReferencesToAFile model = lastStateStoreUpdateTimeResult.references(new ArrayList<>()).build();

    // Act and Assert
    assertNotEquals(StateStoreFile.from(model), "Different type to StateStoreFile");
  }
}
