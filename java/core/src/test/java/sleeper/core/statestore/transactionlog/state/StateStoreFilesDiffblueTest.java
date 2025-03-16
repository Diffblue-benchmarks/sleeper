package sleeper.core.statestore.transactionlog.state;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
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
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.statestore.AllReferencesToAFile;
import sleeper.core.statestore.AllReferencesToAFile.Builder;
import sleeper.core.statestore.AllReferencesToAllFiles;
import sleeper.core.statestore.FileReference;

class StateStoreFilesDiffblueTest {
  /**
   * Test {@link StateStoreFiles#references()}.
   * <p>
   * Method under test: {@link StateStoreFiles#references()}
   */
  @Test
  @DisplayName("Test references()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream StateStoreFiles.references()"})
  void testReferences() {
    // Arrange and Act
    Stream<FileReference> actualReferencesResult = (new StateStoreFiles()).references();

    // Assert
    assertTrue(actualReferencesResult.limit(5).collect(Collectors.toList()).isEmpty());
  }

  /**
   * Test {@link StateStoreFiles#referencedAndUnreferenced()}.
   * <p>
   * Method under test: {@link StateStoreFiles#referencedAndUnreferenced()}
   */
  @Test
  @DisplayName("Test referencedAndUnreferenced()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.util.Collection StateStoreFiles.referencedAndUnreferenced()"})
  void testReferencedAndUnreferenced() {
    // Arrange, Act and Assert
    assertTrue((new StateStoreFiles()).referencedAndUnreferenced().isEmpty());
  }

  /**
   * Test {@link StateStoreFiles#allReferencesToAllFiles(int)} with {@code int}.
   * <ul>
   *   <li>Given {@link StateStoreFiles} (default constructor).</li>
   *   <li>Then return Files Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreFiles#allReferencesToAllFiles(int)}
   */
  @Test
  @DisplayName("Test allReferencesToAllFiles(int) with 'int'; given StateStoreFiles (default constructor); then return Files Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"AllReferencesToAllFiles StateStoreFiles.allReferencesToAllFiles(int)"})
  void testAllReferencesToAllFilesWithInt_givenStateStoreFiles_thenReturnFilesEmpty() {
    // Arrange and Act
    AllReferencesToAllFiles actualAllReferencesToAllFilesResult = (new StateStoreFiles()).allReferencesToAllFiles(3);

    // Assert
    assertFalse(actualAllReferencesToAllFilesResult.isMoreThanMax());
    assertTrue(actualAllReferencesToAllFilesResult.getFiles().isEmpty());
    assertTrue(actualAllReferencesToAllFilesResult.getFilesWithNoReferences().isEmpty());
    assertTrue(actualAllReferencesToAllFilesResult.getFilesWithReferences().isEmpty());
  }

  /**
   * Test {@link StateStoreFiles#allReferencesToAllFiles(int)} with {@code int}.
   * <ul>
   *   <li>Then return Files size is two.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreFiles#allReferencesToAllFiles(int)}
   */
  @Test
  @DisplayName("Test allReferencesToAllFiles(int) with 'int'; then return Files size is two")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"AllReferencesToAllFiles StateStoreFiles.allReferencesToAllFiles(int)"})
  void testAllReferencesToAllFilesWithInt_thenReturnFilesSizeIsTwo() {
    // Arrange
    StateStoreFiles stateStoreFiles = new StateStoreFiles();
    Builder filenameResult = AllReferencesToAFile.builder().filename("Filename");
    Builder lastStateStoreUpdateTimeResult = filenameResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    AllReferencesToAFile model = lastStateStoreUpdateTimeResult.references(new ArrayList<>()).build();
    stateStoreFiles.add(StateStoreFile.from(model));
    Builder filenameResult2 = AllReferencesToAFile.builder().filename("foo.txt");
    Builder lastStateStoreUpdateTimeResult2 = filenameResult2
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    AllReferencesToAFile model2 = lastStateStoreUpdateTimeResult2.references(new ArrayList<>()).build();
    stateStoreFiles.add(StateStoreFile.from(model2));

    // Act
    AllReferencesToAllFiles actualAllReferencesToAllFilesResult = stateStoreFiles.allReferencesToAllFiles(3);

    // Assert
    assertEquals(2, actualAllReferencesToAllFilesResult.getFiles().size());
    assertEquals(2, actualAllReferencesToAllFilesResult.getFilesWithNoReferences().size());
    assertFalse(actualAllReferencesToAllFilesResult.isMoreThanMax());
    assertTrue(actualAllReferencesToAllFilesResult.getFilesWithReferences().isEmpty());
  }

  /**
   * Test {@link StateStoreFiles#allReferencesToAllFiles(int)} with {@code int}.
   * <ul>
   *   <li>Then return FilesWithNoReferences size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreFiles#allReferencesToAllFiles(int)}
   */
  @Test
  @DisplayName("Test allReferencesToAllFiles(int) with 'int'; then return FilesWithNoReferences size is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"AllReferencesToAllFiles StateStoreFiles.allReferencesToAllFiles(int)"})
  void testAllReferencesToAllFilesWithInt_thenReturnFilesWithNoReferencesSizeIsOne() {
    // Arrange
    StateStoreFiles stateStoreFiles = new StateStoreFiles();
    Builder filenameResult = AllReferencesToAFile.builder().filename("foo.txt");
    Builder lastStateStoreUpdateTimeResult = filenameResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    AllReferencesToAFile model = lastStateStoreUpdateTimeResult.references(new ArrayList<>()).build();
    stateStoreFiles.add(StateStoreFile.from(model));

    // Act
    AllReferencesToAllFiles actualAllReferencesToAllFilesResult = stateStoreFiles.allReferencesToAllFiles(3);

    // Assert
    assertEquals(1, actualAllReferencesToAllFilesResult.getFiles().size());
    assertEquals(1, actualAllReferencesToAllFilesResult.getFilesWithNoReferences().size());
    assertFalse(actualAllReferencesToAllFilesResult.isMoreThanMax());
    assertTrue(actualAllReferencesToAllFilesResult.getFilesWithReferences().isEmpty());
  }

  /**
   * Test {@link StateStoreFiles#allReferencesToAllFiles(int)} with {@code int}.
   * <ul>
   *   <li>Then return FilesWithReferences size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreFiles#allReferencesToAllFiles(int)}
   */
  @Test
  @DisplayName("Test allReferencesToAllFiles(int) with 'int'; then return FilesWithReferences size is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"AllReferencesToAllFiles StateStoreFiles.allReferencesToAllFiles(int)"})
  void testAllReferencesToAllFilesWithInt_thenReturnFilesWithReferencesSizeIsOne() {
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
    StateStoreFile file = StateStoreFile.from(model);

    StateStoreFiles stateStoreFiles = new StateStoreFiles();
    stateStoreFiles.add(file);

    // Act
    AllReferencesToAllFiles actualAllReferencesToAllFilesResult = stateStoreFiles.allReferencesToAllFiles(3);

    // Assert
    assertEquals(1, actualAllReferencesToAllFilesResult.getFiles().size());
    assertEquals(1, actualAllReferencesToAllFilesResult.getFilesWithReferences().size());
    assertFalse(actualAllReferencesToAllFilesResult.isMoreThanMax());
    assertTrue(actualAllReferencesToAllFilesResult.getFilesWithNoReferences().isEmpty());
  }

  /**
   * Test {@link StateStoreFiles#allReferencesToAllFiles(int)} with {@code int}.
   * <ul>
   *   <li>When zero.</li>
   *   <li>Then return MoreThanMax.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreFiles#allReferencesToAllFiles(int)}
   */
  @Test
  @DisplayName("Test allReferencesToAllFiles(int) with 'int'; when zero; then return MoreThanMax")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"AllReferencesToAllFiles StateStoreFiles.allReferencesToAllFiles(int)"})
  void testAllReferencesToAllFilesWithInt_whenZero_thenReturnMoreThanMax() {
    // Arrange
    StateStoreFiles stateStoreFiles = new StateStoreFiles();
    Builder filenameResult = AllReferencesToAFile.builder().filename("foo.txt");
    Builder lastStateStoreUpdateTimeResult = filenameResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    AllReferencesToAFile model = lastStateStoreUpdateTimeResult.references(new ArrayList<>()).build();
    stateStoreFiles.add(StateStoreFile.from(model));

    // Act
    AllReferencesToAllFiles actualAllReferencesToAllFilesResult = stateStoreFiles.allReferencesToAllFiles(0);

    // Assert
    assertTrue(actualAllReferencesToAllFilesResult.getFiles().isEmpty());
    assertTrue(actualAllReferencesToAllFilesResult.getFilesWithNoReferences().isEmpty());
    assertTrue(actualAllReferencesToAllFilesResult.getFilesWithReferences().isEmpty());
    assertTrue(actualAllReferencesToAllFilesResult.isMoreThanMax());
  }

  /**
   * Test {@link StateStoreFiles#allReferencesToAllFiles()}.
   * <ul>
   *   <li>Given {@link StateStoreFiles} (default constructor).</li>
   *   <li>Then return Files Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreFiles#allReferencesToAllFiles()}
   */
  @Test
  @DisplayName("Test allReferencesToAllFiles(); given StateStoreFiles (default constructor); then return Files Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"AllReferencesToAllFiles StateStoreFiles.allReferencesToAllFiles()"})
  void testAllReferencesToAllFiles_givenStateStoreFiles_thenReturnFilesEmpty() {
    // Arrange and Act
    AllReferencesToAllFiles actualAllReferencesToAllFilesResult = (new StateStoreFiles()).allReferencesToAllFiles();

    // Assert
    assertFalse(actualAllReferencesToAllFilesResult.isMoreThanMax());
    assertTrue(actualAllReferencesToAllFilesResult.getFiles().isEmpty());
    assertTrue(actualAllReferencesToAllFilesResult.getFilesWithNoReferences().isEmpty());
    assertTrue(actualAllReferencesToAllFilesResult.getFilesWithReferences().isEmpty());
  }

  /**
   * Test {@link StateStoreFiles#allReferencesToAllFiles()}.
   * <ul>
   *   <li>Then return Files size is two.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreFiles#allReferencesToAllFiles()}
   */
  @Test
  @DisplayName("Test allReferencesToAllFiles(); then return Files size is two")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"AllReferencesToAllFiles StateStoreFiles.allReferencesToAllFiles()"})
  void testAllReferencesToAllFiles_thenReturnFilesSizeIsTwo() {
    // Arrange
    StateStoreFiles stateStoreFiles = new StateStoreFiles();
    Builder filenameResult = AllReferencesToAFile.builder().filename("Filename");
    Builder lastStateStoreUpdateTimeResult = filenameResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    AllReferencesToAFile model = lastStateStoreUpdateTimeResult.references(new ArrayList<>()).build();
    stateStoreFiles.add(StateStoreFile.from(model));
    Builder filenameResult2 = AllReferencesToAFile.builder().filename("foo.txt");
    Builder lastStateStoreUpdateTimeResult2 = filenameResult2
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    AllReferencesToAFile model2 = lastStateStoreUpdateTimeResult2.references(new ArrayList<>()).build();
    stateStoreFiles.add(StateStoreFile.from(model2));

    // Act
    AllReferencesToAllFiles actualAllReferencesToAllFilesResult = stateStoreFiles.allReferencesToAllFiles();

    // Assert
    assertEquals(2, actualAllReferencesToAllFilesResult.getFiles().size());
    assertEquals(2, actualAllReferencesToAllFilesResult.getFilesWithNoReferences().size());
    assertFalse(actualAllReferencesToAllFilesResult.isMoreThanMax());
    assertTrue(actualAllReferencesToAllFilesResult.getFilesWithReferences().isEmpty());
  }

  /**
   * Test {@link StateStoreFiles#allReferencesToAllFiles()}.
   * <ul>
   *   <li>Then return FilesWithNoReferences size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreFiles#allReferencesToAllFiles()}
   */
  @Test
  @DisplayName("Test allReferencesToAllFiles(); then return FilesWithNoReferences size is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"AllReferencesToAllFiles StateStoreFiles.allReferencesToAllFiles()"})
  void testAllReferencesToAllFiles_thenReturnFilesWithNoReferencesSizeIsOne() {
    // Arrange
    StateStoreFiles stateStoreFiles = new StateStoreFiles();
    Builder filenameResult = AllReferencesToAFile.builder().filename("foo.txt");
    Builder lastStateStoreUpdateTimeResult = filenameResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    AllReferencesToAFile model = lastStateStoreUpdateTimeResult.references(new ArrayList<>()).build();
    stateStoreFiles.add(StateStoreFile.from(model));

    // Act
    AllReferencesToAllFiles actualAllReferencesToAllFilesResult = stateStoreFiles.allReferencesToAllFiles();

    // Assert
    assertEquals(1, actualAllReferencesToAllFilesResult.getFiles().size());
    assertEquals(1, actualAllReferencesToAllFilesResult.getFilesWithNoReferences().size());
    assertFalse(actualAllReferencesToAllFilesResult.isMoreThanMax());
    assertTrue(actualAllReferencesToAllFilesResult.getFilesWithReferences().isEmpty());
  }

  /**
   * Test {@link StateStoreFiles#allReferencesToAllFiles()}.
   * <ul>
   *   <li>Then return FilesWithReferences size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreFiles#allReferencesToAllFiles()}
   */
  @Test
  @DisplayName("Test allReferencesToAllFiles(); then return FilesWithReferences size is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"AllReferencesToAllFiles StateStoreFiles.allReferencesToAllFiles()"})
  void testAllReferencesToAllFiles_thenReturnFilesWithReferencesSizeIsOne() {
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
    StateStoreFile file = StateStoreFile.from(model);

    StateStoreFiles stateStoreFiles = new StateStoreFiles();
    stateStoreFiles.add(file);

    // Act
    AllReferencesToAllFiles actualAllReferencesToAllFilesResult = stateStoreFiles.allReferencesToAllFiles();

    // Assert
    assertEquals(1, actualAllReferencesToAllFilesResult.getFiles().size());
    assertEquals(1, actualAllReferencesToAllFilesResult.getFilesWithReferences().size());
    assertFalse(actualAllReferencesToAllFilesResult.isMoreThanMax());
    assertTrue(actualAllReferencesToAllFilesResult.getFilesWithNoReferences().isEmpty());
  }

  /**
   * Test {@link StateStoreFiles#unreferencedBefore(Instant)}.
   * <p>
   * Method under test: {@link StateStoreFiles#unreferencedBefore(Instant)}
   */
  @Test
  @DisplayName("Test unreferencedBefore(Instant)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream StateStoreFiles.unreferencedBefore(Instant)"})
  void testUnreferencedBefore() {
    // Arrange
    StateStoreFiles stateStoreFiles = new StateStoreFiles();
    Builder filenameResult = AllReferencesToAFile.builder().filename("foo.txt");
    Builder lastStateStoreUpdateTimeResult = filenameResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    AllReferencesToAFile model = lastStateStoreUpdateTimeResult.references(new ArrayList<>()).build();
    stateStoreFiles.add(StateStoreFile.from(model));

    // Act
    Stream<String> actualUnreferencedBeforeResult = stateStoreFiles
        .unreferencedBefore(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Assert
    assertTrue(actualUnreferencedBeforeResult.limit(5).collect(Collectors.toList()).isEmpty());
  }

  /**
   * Test {@link StateStoreFiles#unreferencedBefore(Instant)}.
   * <p>
   * Method under test: {@link StateStoreFiles#unreferencedBefore(Instant)}
   */
  @Test
  @DisplayName("Test unreferencedBefore(Instant)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream StateStoreFiles.unreferencedBefore(Instant)"})
  void testUnreferencedBefore2() {
    // Arrange
    StateStoreFiles stateStoreFiles = new StateStoreFiles();
    Builder filenameResult = AllReferencesToAFile.builder().filename("Filename");
    Builder lastStateStoreUpdateTimeResult = filenameResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    AllReferencesToAFile model = lastStateStoreUpdateTimeResult.references(new ArrayList<>()).build();
    stateStoreFiles.add(StateStoreFile.from(model));
    Builder filenameResult2 = AllReferencesToAFile.builder().filename("foo.txt");
    Builder lastStateStoreUpdateTimeResult2 = filenameResult2
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    AllReferencesToAFile model2 = lastStateStoreUpdateTimeResult2.references(new ArrayList<>()).build();
    stateStoreFiles.add(StateStoreFile.from(model2));

    // Act
    Stream<String> actualUnreferencedBeforeResult = stateStoreFiles
        .unreferencedBefore(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Assert
    assertTrue(actualUnreferencedBeforeResult.limit(5).collect(Collectors.toList()).isEmpty());
  }

  /**
   * Test {@link StateStoreFiles#unreferencedBefore(Instant)}.
   * <ul>
   *   <li>Given {@link StateStoreFiles} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreFiles#unreferencedBefore(Instant)}
   */
  @Test
  @DisplayName("Test unreferencedBefore(Instant); given StateStoreFiles (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream StateStoreFiles.unreferencedBefore(Instant)"})
  void testUnreferencedBefore_givenStateStoreFiles() {
    // Arrange
    StateStoreFiles stateStoreFiles = new StateStoreFiles();

    // Act
    Stream<String> actualUnreferencedBeforeResult = stateStoreFiles
        .unreferencedBefore(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Assert
    assertTrue(actualUnreferencedBeforeResult.limit(5).collect(Collectors.toList()).isEmpty());
  }

  /**
   * Test {@link StateStoreFiles#unreferencedBefore(Instant)}.
   * <ul>
   *   <li>Then return limit five collect toList size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreFiles#unreferencedBefore(Instant)}
   */
  @Test
  @DisplayName("Test unreferencedBefore(Instant); then return limit five collect toList size is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream StateStoreFiles.unreferencedBefore(Instant)"})
  void testUnreferencedBefore_thenReturnLimitFiveCollectToListSizeIsOne() {
    // Arrange
    StateStoreFiles stateStoreFiles = new StateStoreFiles();
    Builder filenameResult = AllReferencesToAFile.builder().filename("foo.txt");
    Builder lastStateStoreUpdateTimeResult = filenameResult
        .lastStateStoreUpdateTime(LocalDate.ofYearDay(1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    AllReferencesToAFile model = lastStateStoreUpdateTimeResult.references(new ArrayList<>()).build();
    stateStoreFiles.add(StateStoreFile.from(model));

    // Act
    Stream<String> actualUnreferencedBeforeResult = stateStoreFiles
        .unreferencedBefore(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Assert
    List<String> collectResult = actualUnreferencedBeforeResult.limit(5).collect(Collectors.toList());
    assertEquals(1, collectResult.size());
    assertEquals("foo.txt", collectResult.get(0));
  }

  /**
   * Test {@link StateStoreFiles#file(String)}.
   * <p>
   * Method under test: {@link StateStoreFiles#file(String)}
   */
  @Test
  @DisplayName("Test file(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.util.Optional StateStoreFiles.file(String)"})
  void testFile() {
    // Arrange, Act and Assert
    assertFalse((new StateStoreFiles()).file("foo.txt").isPresent());
  }

  /**
   * Test {@link StateStoreFiles#isEmpty()}.
   * <ul>
   *   <li>Given {@link StateStoreFiles} (default constructor).</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreFiles#isEmpty()}
   */
  @Test
  @DisplayName("Test isEmpty(); given StateStoreFiles (default constructor); then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean StateStoreFiles.isEmpty()"})
  void testIsEmpty_givenStateStoreFiles_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue((new StateStoreFiles()).isEmpty());
  }

  /**
   * Test {@link StateStoreFiles#isEmpty()}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreFiles#isEmpty()}
   */
  @Test
  @DisplayName("Test isEmpty(); then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean StateStoreFiles.isEmpty()"})
  void testIsEmpty_thenReturnFalse() {
    // Arrange
    StateStoreFiles stateStoreFiles = new StateStoreFiles();
    Builder filenameResult = AllReferencesToAFile.builder().filename("foo.txt");
    Builder lastStateStoreUpdateTimeResult = filenameResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    AllReferencesToAFile model = lastStateStoreUpdateTimeResult.references(new ArrayList<>()).build();
    stateStoreFiles.add(StateStoreFile.from(model));

    // Act and Assert
    assertFalse(stateStoreFiles.isEmpty());
  }

  /**
   * Test {@link StateStoreFiles#add(StateStoreFile)}.
   * <ul>
   *   <li>Then not {@link StateStoreFiles} (default constructor) Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreFiles#add(StateStoreFile)}
   */
  @Test
  @DisplayName("Test add(StateStoreFile); then not StateStoreFiles (default constructor) Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void StateStoreFiles.add(StateStoreFile)"})
  void testAdd_thenNotStateStoreFilesEmpty() {
    // Arrange
    StateStoreFiles stateStoreFiles = new StateStoreFiles();
    Builder filenameResult = AllReferencesToAFile.builder().filename("foo.txt");
    Builder lastStateStoreUpdateTimeResult = filenameResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    AllReferencesToAFile model = lastStateStoreUpdateTimeResult.references(new ArrayList<>()).build();

    // Act
    stateStoreFiles.add(StateStoreFile.from(model));

    // Assert
    assertFalse(stateStoreFiles.isEmpty());
  }

  /**
   * Test {@link StateStoreFiles#updateFile(String, Consumer)}.
   * <ul>
   *   <li>Given {@link StateStoreFile} {@link StateStoreFile#getFilename()} return {@code foo.txt}.</li>
   *   <li>Then calls {@link StateStoreFile#getFilename()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreFiles#updateFile(String, Consumer)}
   */
  @Test
  @DisplayName("Test updateFile(String, Consumer); given StateStoreFile getFilename() return 'foo.txt'; then calls getFilename()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void StateStoreFiles.updateFile(String, Consumer)"})
  void testUpdateFile_givenStateStoreFileGetFilenameReturnFooTxt_thenCallsGetFilename() {
    // Arrange
    StateStoreFile file = mock(StateStoreFile.class);
    when(file.getFilename()).thenReturn("foo.txt");

    StateStoreFiles stateStoreFiles = new StateStoreFiles();
    stateStoreFiles.add(file);

    // Act
    stateStoreFiles.updateFile("foo.txt", stateStoreFiles::add);

    // Assert
    verify(file, atLeast(1)).getFilename();
  }

  /**
   * Test {@link StateStoreFiles#equals(Object)}, and {@link StateStoreFiles#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link StateStoreFiles#equals(Object)}
   *   <li>{@link StateStoreFiles#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean StateStoreFiles.equals(Object)", "int StateStoreFiles.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    StateStoreFiles stateStoreFiles = new StateStoreFiles();
    StateStoreFiles stateStoreFiles2 = new StateStoreFiles();

    // Act and Assert
    assertEquals(stateStoreFiles, stateStoreFiles2);
    int expectedHashCodeResult = stateStoreFiles.hashCode();
    assertEquals(expectedHashCodeResult, stateStoreFiles2.hashCode());
  }

  /**
   * Test {@link StateStoreFiles#equals(Object)}, and {@link StateStoreFiles#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link StateStoreFiles#equals(Object)}
   *   <li>{@link StateStoreFiles#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean StateStoreFiles.equals(Object)", "int StateStoreFiles.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    StateStoreFiles stateStoreFiles = new StateStoreFiles();

    // Act and Assert
    assertEquals(stateStoreFiles, stateStoreFiles);
    int expectedHashCodeResult = stateStoreFiles.hashCode();
    assertEquals(expectedHashCodeResult, stateStoreFiles.hashCode());
  }

  /**
   * Test {@link StateStoreFiles#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreFiles#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean StateStoreFiles.equals(Object)", "int StateStoreFiles.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    StateStoreFiles stateStoreFiles = new StateStoreFiles();
    Builder filenameResult = AllReferencesToAFile.builder().filename("foo.txt");
    Builder lastStateStoreUpdateTimeResult = filenameResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    AllReferencesToAFile model = lastStateStoreUpdateTimeResult.references(new ArrayList<>()).build();
    stateStoreFiles.add(StateStoreFile.from(model));

    // Act and Assert
    assertNotEquals(stateStoreFiles, new StateStoreFiles());
  }

  /**
   * Test {@link StateStoreFiles#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreFiles#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean StateStoreFiles.equals(Object)", "int StateStoreFiles.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new StateStoreFiles(), null);
  }

  /**
   * Test {@link StateStoreFiles#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreFiles#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean StateStoreFiles.equals(Object)", "int StateStoreFiles.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new StateStoreFiles(), "Different type to StateStoreFiles");
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>default or parameterless constructor of {@link StateStoreFiles}
   *   <li>{@link StateStoreFiles#toString()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void StateStoreFiles.<init>()", "String StateStoreFiles.toString()"})
  void testGettersAndSetters() {
    // Arrange, Act and Assert
    assertEquals("StateStoreFiles{filesByFilename={}}", (new StateStoreFiles()).toString());
  }
}
