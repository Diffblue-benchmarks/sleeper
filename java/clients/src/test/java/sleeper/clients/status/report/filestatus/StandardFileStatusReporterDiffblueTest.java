package sleeper.clients.status.report.filestatus;

import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.statestore.AllReferencesToAFile;
import sleeper.core.statestore.AllReferencesToAFile.Builder;
import sleeper.core.statestore.FileReference;

class StandardFileStatusReporterDiffblueTest {
  /**
   * Test {@link StandardFileStatusReporter#report(TableFilesStatus, boolean)}.
   * <p>
   * Method under test: {@link StandardFileStatusReporter#report(TableFilesStatus, boolean)}
   */
  @Test
  @DisplayName("Test report(TableFilesStatus, boolean)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void StandardFileStatusReporter.report(TableFilesStatus, boolean)"})
  void testReport() {
    // Arrange
    StandardFileStatusReporter standardFileStatusReporter = new StandardFileStatusReporter();

    ArrayList<FileReference> references = new ArrayList<>();
    FileReference.Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference buildResult = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    references.add(buildResult);
    FileReferencesStats fromResult = FileReferencesStats.from(references);
    TableFilesStatus status = mock(TableFilesStatus.class);
    when(status.getTotalRecords()).thenReturn(1L);
    when(status.getTotalRecordsApprox()).thenReturn(1L);
    when(status.getTotalRecordsInLeafPartitions()).thenReturn(1L);
    when(status.getTotalRecordsInLeafPartitionsApprox()).thenReturn(1L);
    when(status.getTotalRecordsInNonLeafPartitions()).thenReturn(1L);
    when(status.getTotalRecordsInNonLeafPartitionsApprox()).thenReturn(1L);
    when(status.getNonLeafPartitionFileReferenceStats()).thenReturn(fromResult);
    when(status.isMoreThanMax()).thenReturn(true);
    when(status.getFileCount()).thenReturn(3);
    when(status.getLeafPartitionCount()).thenReturn(3);
    when(status.getNonLeafPartitionCount()).thenReturn(3);
    when(status.getFilesWithNoReferences()).thenReturn(new ArrayList<>());
    when(status.getFilesWithReferences()).thenReturn(new ArrayList<>());
    when(status.getFileReferenceCount()).thenReturn(3L);
    when(status.getLeafPartitionFileReferenceStats()).thenReturn(FileReferencesStats.from(new ArrayList<>()));

    // Act
    standardFileStatusReporter.report(status, true);

    // Assert
    verify(status).getFileCount();
    verify(status).getFileReferenceCount();
    verify(status, atLeast(1)).getFilesWithNoReferences();
    verify(status, atLeast(1)).getFilesWithReferences();
    verify(status).getLeafPartitionCount();
    verify(status).getLeafPartitionFileReferenceStats();
    verify(status).getNonLeafPartitionCount();
    verify(status).getNonLeafPartitionFileReferenceStats();
    verify(status, atLeast(1)).getTotalRecords();
    verify(status).getTotalRecordsApprox();
    verify(status, atLeast(1)).getTotalRecordsInLeafPartitions();
    verify(status).getTotalRecordsInLeafPartitionsApprox();
    verify(status).getTotalRecordsInNonLeafPartitions();
    verify(status).getTotalRecordsInNonLeafPartitionsApprox();
    verify(status, atLeast(1)).isMoreThanMax();
  }

  /**
   * Test {@link StandardFileStatusReporter#report(TableFilesStatus, boolean)}.
   * <p>
   * Method under test: {@link StandardFileStatusReporter#report(TableFilesStatus, boolean)}
   */
  @Test
  @DisplayName("Test report(TableFilesStatus, boolean)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void StandardFileStatusReporter.report(TableFilesStatus, boolean)"})
  void testReport2() {
    // Arrange
    StandardFileStatusReporter standardFileStatusReporter = new StandardFileStatusReporter();

    ArrayList<AllReferencesToAFile> allReferencesToAFileList = new ArrayList<>();
    Builder filenameResult = AllReferencesToAFile.builder().filename("foo.txt");
    Builder lastStateStoreUpdateTimeResult = filenameResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    AllReferencesToAFile buildResult = lastStateStoreUpdateTimeResult.references(new ArrayList<>()).build();
    allReferencesToAFileList.add(buildResult);
    TableFilesStatus status = mock(TableFilesStatus.class);
    when(status.getTotalRecords()).thenReturn(1L);
    when(status.getTotalRecordsApprox()).thenReturn(1L);
    when(status.getTotalRecordsInLeafPartitions()).thenReturn(1L);
    when(status.getTotalRecordsInLeafPartitionsApprox()).thenReturn(1L);
    when(status.getTotalRecordsInNonLeafPartitions()).thenReturn(1L);
    when(status.getTotalRecordsInNonLeafPartitionsApprox()).thenReturn(1L);
    when(status.getNonLeafPartitionFileReferenceStats()).thenReturn(FileReferencesStats.from(new ArrayList<>()));
    when(status.isMoreThanMax()).thenReturn(true);
    when(status.getFileCount()).thenReturn(3);
    when(status.getLeafPartitionCount()).thenReturn(3);
    when(status.getNonLeafPartitionCount()).thenReturn(3);
    when(status.getFilesWithNoReferences()).thenReturn(allReferencesToAFileList);
    when(status.getFilesWithReferences()).thenReturn(new ArrayList<>());
    when(status.getFileReferenceCount()).thenReturn(3L);
    when(status.getLeafPartitionFileReferenceStats()).thenReturn(FileReferencesStats.from(new ArrayList<>()));

    // Act
    standardFileStatusReporter.report(status, true);

    // Assert
    verify(status).getFileCount();
    verify(status).getFileReferenceCount();
    verify(status, atLeast(1)).getFilesWithNoReferences();
    verify(status, atLeast(1)).getFilesWithReferences();
    verify(status).getLeafPartitionCount();
    verify(status).getLeafPartitionFileReferenceStats();
    verify(status).getNonLeafPartitionCount();
    verify(status).getNonLeafPartitionFileReferenceStats();
    verify(status, atLeast(1)).getTotalRecords();
    verify(status).getTotalRecordsApprox();
    verify(status, atLeast(1)).getTotalRecordsInLeafPartitions();
    verify(status).getTotalRecordsInLeafPartitionsApprox();
    verify(status).getTotalRecordsInNonLeafPartitions();
    verify(status).getTotalRecordsInNonLeafPartitionsApprox();
    verify(status, atLeast(1)).isMoreThanMax();
  }

  /**
   * Test {@link StandardFileStatusReporter#report(TableFilesStatus, boolean)}.
   * <p>
   * Method under test: {@link StandardFileStatusReporter#report(TableFilesStatus, boolean)}
   */
  @Test
  @DisplayName("Test report(TableFilesStatus, boolean)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void StandardFileStatusReporter.report(TableFilesStatus, boolean)"})
  void testReport3() {
    // Arrange
    StandardFileStatusReporter standardFileStatusReporter = new StandardFileStatusReporter();

    ArrayList<AllReferencesToAFile> allReferencesToAFileList = new ArrayList<>();
    Builder filenameResult = AllReferencesToAFile.builder().filename("foo.txt");
    Builder lastStateStoreUpdateTimeResult = filenameResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    AllReferencesToAFile buildResult = lastStateStoreUpdateTimeResult.references(new ArrayList<>()).build();
    allReferencesToAFileList.add(buildResult);
    Builder filenameResult2 = AllReferencesToAFile.builder().filename("foo.txt");
    Builder lastStateStoreUpdateTimeResult2 = filenameResult2
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    AllReferencesToAFile buildResult2 = lastStateStoreUpdateTimeResult2.references(new ArrayList<>()).build();
    allReferencesToAFileList.add(buildResult2);
    TableFilesStatus status = mock(TableFilesStatus.class);
    when(status.getTotalRecords()).thenReturn(1L);
    when(status.getTotalRecordsApprox()).thenReturn(1L);
    when(status.getTotalRecordsInLeafPartitions()).thenReturn(1L);
    when(status.getTotalRecordsInLeafPartitionsApprox()).thenReturn(1L);
    when(status.getTotalRecordsInNonLeafPartitions()).thenReturn(1L);
    when(status.getTotalRecordsInNonLeafPartitionsApprox()).thenReturn(1L);
    when(status.getNonLeafPartitionFileReferenceStats()).thenReturn(FileReferencesStats.from(new ArrayList<>()));
    when(status.isMoreThanMax()).thenReturn(true);
    when(status.getFileCount()).thenReturn(3);
    when(status.getLeafPartitionCount()).thenReturn(3);
    when(status.getNonLeafPartitionCount()).thenReturn(3);
    when(status.getFilesWithNoReferences()).thenReturn(allReferencesToAFileList);
    when(status.getFilesWithReferences()).thenReturn(new ArrayList<>());
    when(status.getFileReferenceCount()).thenReturn(3L);
    when(status.getLeafPartitionFileReferenceStats()).thenReturn(FileReferencesStats.from(new ArrayList<>()));

    // Act
    standardFileStatusReporter.report(status, true);

    // Assert
    verify(status).getFileCount();
    verify(status).getFileReferenceCount();
    verify(status, atLeast(1)).getFilesWithNoReferences();
    verify(status, atLeast(1)).getFilesWithReferences();
    verify(status).getLeafPartitionCount();
    verify(status).getLeafPartitionFileReferenceStats();
    verify(status).getNonLeafPartitionCount();
    verify(status).getNonLeafPartitionFileReferenceStats();
    verify(status, atLeast(1)).getTotalRecords();
    verify(status).getTotalRecordsApprox();
    verify(status, atLeast(1)).getTotalRecordsInLeafPartitions();
    verify(status).getTotalRecordsInLeafPartitionsApprox();
    verify(status).getTotalRecordsInNonLeafPartitions();
    verify(status).getTotalRecordsInNonLeafPartitionsApprox();
    verify(status, atLeast(1)).isMoreThanMax();
  }

  /**
   * Test {@link StandardFileStatusReporter#report(TableFilesStatus, boolean)}.
   * <p>
   * Method under test: {@link StandardFileStatusReporter#report(TableFilesStatus, boolean)}
   */
  @Test
  @DisplayName("Test report(TableFilesStatus, boolean)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void StandardFileStatusReporter.report(TableFilesStatus, boolean)"})
  void testReport4() {
    // Arrange
    StandardFileStatusReporter standardFileStatusReporter = new StandardFileStatusReporter();

    ArrayList<AllReferencesToAFile> allReferencesToAFileList = new ArrayList<>();
    Builder filenameResult = AllReferencesToAFile.builder().filename("foo.txt");
    Builder lastStateStoreUpdateTimeResult = filenameResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    AllReferencesToAFile buildResult = lastStateStoreUpdateTimeResult.references(new ArrayList<>()).build();
    allReferencesToAFileList.add(buildResult);
    TableFilesStatus status = mock(TableFilesStatus.class);
    when(status.getTotalRecords()).thenReturn(1L);
    when(status.getTotalRecordsApprox()).thenReturn(1L);
    when(status.getTotalRecordsInLeafPartitions()).thenReturn(1L);
    when(status.getTotalRecordsInLeafPartitionsApprox()).thenReturn(1L);
    when(status.getTotalRecordsInNonLeafPartitions()).thenReturn(1L);
    when(status.getTotalRecordsInNonLeafPartitionsApprox()).thenReturn(1L);
    when(status.getNonLeafPartitionFileReferenceStats()).thenReturn(FileReferencesStats.from(new ArrayList<>()));
    when(status.isMoreThanMax()).thenReturn(true);
    when(status.getFileCount()).thenReturn(3);
    when(status.getLeafPartitionCount()).thenReturn(3);
    when(status.getNonLeafPartitionCount()).thenReturn(3);
    when(status.getFilesWithNoReferences()).thenReturn(new ArrayList<>());
    when(status.getFilesWithReferences()).thenReturn(allReferencesToAFileList);
    when(status.getFileReferenceCount()).thenReturn(3L);
    when(status.getLeafPartitionFileReferenceStats()).thenReturn(FileReferencesStats.from(new ArrayList<>()));

    // Act
    standardFileStatusReporter.report(status, true);

    // Assert
    verify(status).getFileCount();
    verify(status).getFileReferenceCount();
    verify(status, atLeast(1)).getFilesWithNoReferences();
    verify(status, atLeast(1)).getFilesWithReferences();
    verify(status).getLeafPartitionCount();
    verify(status).getLeafPartitionFileReferenceStats();
    verify(status).getNonLeafPartitionCount();
    verify(status).getNonLeafPartitionFileReferenceStats();
    verify(status, atLeast(1)).getTotalRecords();
    verify(status).getTotalRecordsApprox();
    verify(status, atLeast(1)).getTotalRecordsInLeafPartitions();
    verify(status).getTotalRecordsInLeafPartitionsApprox();
    verify(status).getTotalRecordsInNonLeafPartitions();
    verify(status).getTotalRecordsInNonLeafPartitionsApprox();
    verify(status, atLeast(1)).isMoreThanMax();
  }

  /**
   * Test {@link StandardFileStatusReporter#report(TableFilesStatus, boolean)}.
   * <p>
   * Method under test: {@link StandardFileStatusReporter#report(TableFilesStatus, boolean)}
   */
  @Test
  @DisplayName("Test report(TableFilesStatus, boolean)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void StandardFileStatusReporter.report(TableFilesStatus, boolean)"})
  void testReport5() {
    // Arrange
    StandardFileStatusReporter standardFileStatusReporter = new StandardFileStatusReporter();

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

    ArrayList<AllReferencesToAFile> allReferencesToAFileList = new ArrayList<>();
    allReferencesToAFileList.add(buildResult2);
    TableFilesStatus status = mock(TableFilesStatus.class);
    when(status.getTotalRecords()).thenReturn(1L);
    when(status.getTotalRecordsApprox()).thenReturn(1L);
    when(status.getTotalRecordsInLeafPartitions()).thenReturn(1L);
    when(status.getTotalRecordsInLeafPartitionsApprox()).thenReturn(1L);
    when(status.getTotalRecordsInNonLeafPartitions()).thenReturn(1L);
    when(status.getTotalRecordsInNonLeafPartitionsApprox()).thenReturn(1L);
    when(status.getNonLeafPartitionFileReferenceStats()).thenReturn(FileReferencesStats.from(new ArrayList<>()));
    when(status.isMoreThanMax()).thenReturn(true);
    when(status.getFileCount()).thenReturn(3);
    when(status.getLeafPartitionCount()).thenReturn(3);
    when(status.getNonLeafPartitionCount()).thenReturn(3);
    when(status.getFilesWithNoReferences()).thenReturn(allReferencesToAFileList);
    when(status.getFilesWithReferences()).thenReturn(new ArrayList<>());
    when(status.getFileReferenceCount()).thenReturn(3L);
    when(status.getLeafPartitionFileReferenceStats()).thenReturn(FileReferencesStats.from(new ArrayList<>()));

    // Act
    standardFileStatusReporter.report(status, true);

    // Assert
    verify(status).getFileCount();
    verify(status).getFileReferenceCount();
    verify(status, atLeast(1)).getFilesWithNoReferences();
    verify(status, atLeast(1)).getFilesWithReferences();
    verify(status).getLeafPartitionCount();
    verify(status).getLeafPartitionFileReferenceStats();
    verify(status).getNonLeafPartitionCount();
    verify(status).getNonLeafPartitionFileReferenceStats();
    verify(status, atLeast(1)).getTotalRecords();
    verify(status).getTotalRecordsApprox();
    verify(status, atLeast(1)).getTotalRecordsInLeafPartitions();
    verify(status).getTotalRecordsInLeafPartitionsApprox();
    verify(status).getTotalRecordsInNonLeafPartitions();
    verify(status).getTotalRecordsInNonLeafPartitionsApprox();
    verify(status, atLeast(1)).isMoreThanMax();
  }

  /**
   * Test {@link StandardFileStatusReporter#report(TableFilesStatus, boolean)}.
   * <p>
   * Method under test: {@link StandardFileStatusReporter#report(TableFilesStatus, boolean)}
   */
  @Test
  @DisplayName("Test report(TableFilesStatus, boolean)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void StandardFileStatusReporter.report(TableFilesStatus, boolean)"})
  void testReport6() {
    // Arrange
    StandardFileStatusReporter standardFileStatusReporter = new StandardFileStatusReporter();

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

    ArrayList<AllReferencesToAFile> allReferencesToAFileList = new ArrayList<>();
    allReferencesToAFileList.add(buildResult3);
    TableFilesStatus status = mock(TableFilesStatus.class);
    when(status.getTotalRecords()).thenReturn(1L);
    when(status.getTotalRecordsApprox()).thenReturn(1L);
    when(status.getTotalRecordsInLeafPartitions()).thenReturn(1L);
    when(status.getTotalRecordsInLeafPartitionsApprox()).thenReturn(1L);
    when(status.getTotalRecordsInNonLeafPartitions()).thenReturn(1L);
    when(status.getTotalRecordsInNonLeafPartitionsApprox()).thenReturn(1L);
    when(status.getNonLeafPartitionFileReferenceStats()).thenReturn(FileReferencesStats.from(new ArrayList<>()));
    when(status.isMoreThanMax()).thenReturn(true);
    when(status.getFileCount()).thenReturn(3);
    when(status.getLeafPartitionCount()).thenReturn(3);
    when(status.getNonLeafPartitionCount()).thenReturn(3);
    when(status.getFilesWithNoReferences()).thenReturn(allReferencesToAFileList);
    when(status.getFilesWithReferences()).thenReturn(new ArrayList<>());
    when(status.getFileReferenceCount()).thenReturn(3L);
    when(status.getLeafPartitionFileReferenceStats()).thenReturn(FileReferencesStats.from(new ArrayList<>()));

    // Act
    standardFileStatusReporter.report(status, true);

    // Assert
    verify(status).getFileCount();
    verify(status).getFileReferenceCount();
    verify(status, atLeast(1)).getFilesWithNoReferences();
    verify(status, atLeast(1)).getFilesWithReferences();
    verify(status).getLeafPartitionCount();
    verify(status).getLeafPartitionFileReferenceStats();
    verify(status).getNonLeafPartitionCount();
    verify(status).getNonLeafPartitionFileReferenceStats();
    verify(status, atLeast(1)).getTotalRecords();
    verify(status).getTotalRecordsApprox();
    verify(status, atLeast(1)).getTotalRecordsInLeafPartitions();
    verify(status).getTotalRecordsInLeafPartitionsApprox();
    verify(status).getTotalRecordsInNonLeafPartitions();
    verify(status).getTotalRecordsInNonLeafPartitionsApprox();
    verify(status, atLeast(1)).isMoreThanMax();
  }

  /**
   * Test {@link StandardFileStatusReporter#report(TableFilesStatus, boolean)}.
   * <ul>
   *   <li>Given {@code 1000000}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardFileStatusReporter#report(TableFilesStatus, boolean)}
   */
  @Test
  @DisplayName("Test report(TableFilesStatus, boolean); given '1000000'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void StandardFileStatusReporter.report(TableFilesStatus, boolean)"})
  void testReport_given1000000() {
    // Arrange
    StandardFileStatusReporter standardFileStatusReporter = new StandardFileStatusReporter();
    TableFilesStatus status = mock(TableFilesStatus.class);
    when(status.getTotalRecords()).thenReturn(1000L);
    when(status.getTotalRecordsApprox()).thenReturn(1L);
    when(status.getTotalRecordsInLeafPartitions()).thenReturn(1000000L);
    when(status.getTotalRecordsInLeafPartitionsApprox()).thenReturn(1L);
    when(status.getTotalRecordsInNonLeafPartitions()).thenReturn(1L);
    when(status.getTotalRecordsInNonLeafPartitionsApprox()).thenReturn(1L);
    when(status.getNonLeafPartitionFileReferenceStats()).thenReturn(FileReferencesStats.from(new ArrayList<>()));
    when(status.isMoreThanMax()).thenReturn(true);
    when(status.getFileCount()).thenReturn(3);
    when(status.getLeafPartitionCount()).thenReturn(3);
    when(status.getNonLeafPartitionCount()).thenReturn(3);
    when(status.getFilesWithNoReferences()).thenReturn(new ArrayList<>());
    when(status.getFilesWithReferences()).thenReturn(new ArrayList<>());
    when(status.getFileReferenceCount()).thenReturn(3L);
    when(status.getLeafPartitionFileReferenceStats()).thenReturn(FileReferencesStats.from(new ArrayList<>()));

    // Act
    standardFileStatusReporter.report(status, true);

    // Assert
    verify(status).getFileCount();
    verify(status).getFileReferenceCount();
    verify(status, atLeast(1)).getFilesWithNoReferences();
    verify(status, atLeast(1)).getFilesWithReferences();
    verify(status).getLeafPartitionCount();
    verify(status).getLeafPartitionFileReferenceStats();
    verify(status).getNonLeafPartitionCount();
    verify(status).getNonLeafPartitionFileReferenceStats();
    verify(status, atLeast(1)).getTotalRecords();
    verify(status).getTotalRecordsApprox();
    verify(status, atLeast(1)).getTotalRecordsInLeafPartitions();
    verify(status).getTotalRecordsInLeafPartitionsApprox();
    verify(status).getTotalRecordsInNonLeafPartitions();
    verify(status).getTotalRecordsInNonLeafPartitionsApprox();
    verify(status, atLeast(1)).isMoreThanMax();
  }

  /**
   * Test {@link StandardFileStatusReporter#report(TableFilesStatus, boolean)}.
   * <ul>
   *   <li>Given {@code 1000000000}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardFileStatusReporter#report(TableFilesStatus, boolean)}
   */
  @Test
  @DisplayName("Test report(TableFilesStatus, boolean); given '1000000000'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void StandardFileStatusReporter.report(TableFilesStatus, boolean)"})
  void testReport_given1000000000() {
    // Arrange
    StandardFileStatusReporter standardFileStatusReporter = new StandardFileStatusReporter();
    TableFilesStatus status = mock(TableFilesStatus.class);
    when(status.getTotalRecords()).thenReturn(Long.MAX_VALUE);
    when(status.getTotalRecordsApprox()).thenReturn(1L);
    when(status.getTotalRecordsInLeafPartitions()).thenReturn(1000000000L);
    when(status.getTotalRecordsInLeafPartitionsApprox()).thenReturn(1L);
    when(status.getTotalRecordsInNonLeafPartitions()).thenReturn(1L);
    when(status.getTotalRecordsInNonLeafPartitionsApprox()).thenReturn(1L);
    when(status.getNonLeafPartitionFileReferenceStats()).thenReturn(FileReferencesStats.from(new ArrayList<>()));
    when(status.isMoreThanMax()).thenReturn(true);
    when(status.getFileCount()).thenReturn(3);
    when(status.getLeafPartitionCount()).thenReturn(3);
    when(status.getNonLeafPartitionCount()).thenReturn(3);
    when(status.getFilesWithNoReferences()).thenReturn(new ArrayList<>());
    when(status.getFilesWithReferences()).thenReturn(new ArrayList<>());
    when(status.getFileReferenceCount()).thenReturn(3L);
    when(status.getLeafPartitionFileReferenceStats()).thenReturn(FileReferencesStats.from(new ArrayList<>()));

    // Act
    standardFileStatusReporter.report(status, true);

    // Assert
    verify(status).getFileCount();
    verify(status).getFileReferenceCount();
    verify(status, atLeast(1)).getFilesWithNoReferences();
    verify(status, atLeast(1)).getFilesWithReferences();
    verify(status).getLeafPartitionCount();
    verify(status).getLeafPartitionFileReferenceStats();
    verify(status).getNonLeafPartitionCount();
    verify(status).getNonLeafPartitionFileReferenceStats();
    verify(status, atLeast(1)).getTotalRecords();
    verify(status).getTotalRecordsApprox();
    verify(status, atLeast(1)).getTotalRecordsInLeafPartitions();
    verify(status).getTotalRecordsInLeafPartitionsApprox();
    verify(status).getTotalRecordsInNonLeafPartitions();
    verify(status).getTotalRecordsInNonLeafPartitionsApprox();
    verify(status, atLeast(1)).isMoreThanMax();
  }

  /**
   * Test {@link StandardFileStatusReporter#report(TableFilesStatus, boolean)}.
   * <ul>
   *   <li>Given {@link Long#MAX_VALUE}.</li>
   *   <li>When {@link TableFilesStatus} {@link TableFilesStatus#getTotalRecords()} return {@link Long#MAX_VALUE}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardFileStatusReporter#report(TableFilesStatus, boolean)}
   */
  @Test
  @DisplayName("Test report(TableFilesStatus, boolean); given MAX_VALUE; when TableFilesStatus getTotalRecords() return MAX_VALUE")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void StandardFileStatusReporter.report(TableFilesStatus, boolean)"})
  void testReport_givenMax_value_whenTableFilesStatusGetTotalRecordsReturnMax_value() {
    // Arrange
    StandardFileStatusReporter standardFileStatusReporter = new StandardFileStatusReporter();
    TableFilesStatus status = mock(TableFilesStatus.class);
    when(status.getTotalRecords()).thenReturn(Long.MAX_VALUE);
    when(status.getTotalRecordsApprox()).thenReturn(1L);
    when(status.getTotalRecordsInLeafPartitions()).thenReturn(1L);
    when(status.getTotalRecordsInLeafPartitionsApprox()).thenReturn(1L);
    when(status.getTotalRecordsInNonLeafPartitions()).thenReturn(1L);
    when(status.getTotalRecordsInNonLeafPartitionsApprox()).thenReturn(1L);
    when(status.getNonLeafPartitionFileReferenceStats()).thenReturn(FileReferencesStats.from(new ArrayList<>()));
    when(status.isMoreThanMax()).thenReturn(true);
    when(status.getFileCount()).thenReturn(3);
    when(status.getLeafPartitionCount()).thenReturn(3);
    when(status.getNonLeafPartitionCount()).thenReturn(3);
    when(status.getFilesWithNoReferences()).thenReturn(new ArrayList<>());
    when(status.getFilesWithReferences()).thenReturn(new ArrayList<>());
    when(status.getFileReferenceCount()).thenReturn(3L);
    when(status.getLeafPartitionFileReferenceStats()).thenReturn(FileReferencesStats.from(new ArrayList<>()));

    // Act
    standardFileStatusReporter.report(status, true);

    // Assert
    verify(status).getFileCount();
    verify(status).getFileReferenceCount();
    verify(status, atLeast(1)).getFilesWithNoReferences();
    verify(status, atLeast(1)).getFilesWithReferences();
    verify(status).getLeafPartitionCount();
    verify(status).getLeafPartitionFileReferenceStats();
    verify(status).getNonLeafPartitionCount();
    verify(status).getNonLeafPartitionFileReferenceStats();
    verify(status, atLeast(1)).getTotalRecords();
    verify(status).getTotalRecordsApprox();
    verify(status, atLeast(1)).getTotalRecordsInLeafPartitions();
    verify(status).getTotalRecordsInLeafPartitionsApprox();
    verify(status).getTotalRecordsInNonLeafPartitions();
    verify(status).getTotalRecordsInNonLeafPartitionsApprox();
    verify(status, atLeast(1)).isMoreThanMax();
  }

  /**
   * Test {@link StandardFileStatusReporter#report(TableFilesStatus, boolean)}.
   * <ul>
   *   <li>Given one thousand.</li>
   *   <li>When {@link TableFilesStatus} {@link TableFilesStatus#getTotalRecords()} return one thousand.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardFileStatusReporter#report(TableFilesStatus, boolean)}
   */
  @Test
  @DisplayName("Test report(TableFilesStatus, boolean); given one thousand; when TableFilesStatus getTotalRecords() return one thousand")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void StandardFileStatusReporter.report(TableFilesStatus, boolean)"})
  void testReport_givenOneThousand_whenTableFilesStatusGetTotalRecordsReturnOneThousand() {
    // Arrange
    StandardFileStatusReporter standardFileStatusReporter = new StandardFileStatusReporter();
    TableFilesStatus status = mock(TableFilesStatus.class);
    when(status.getTotalRecords()).thenReturn(1000L);
    when(status.getTotalRecordsApprox()).thenReturn(1L);
    when(status.getTotalRecordsInLeafPartitions()).thenReturn(1L);
    when(status.getTotalRecordsInLeafPartitionsApprox()).thenReturn(1L);
    when(status.getTotalRecordsInNonLeafPartitions()).thenReturn(1L);
    when(status.getTotalRecordsInNonLeafPartitionsApprox()).thenReturn(1L);
    when(status.getNonLeafPartitionFileReferenceStats()).thenReturn(FileReferencesStats.from(new ArrayList<>()));
    when(status.isMoreThanMax()).thenReturn(true);
    when(status.getFileCount()).thenReturn(3);
    when(status.getLeafPartitionCount()).thenReturn(3);
    when(status.getNonLeafPartitionCount()).thenReturn(3);
    when(status.getFilesWithNoReferences()).thenReturn(new ArrayList<>());
    when(status.getFilesWithReferences()).thenReturn(new ArrayList<>());
    when(status.getFileReferenceCount()).thenReturn(3L);
    when(status.getLeafPartitionFileReferenceStats()).thenReturn(FileReferencesStats.from(new ArrayList<>()));

    // Act
    standardFileStatusReporter.report(status, true);

    // Assert
    verify(status).getFileCount();
    verify(status).getFileReferenceCount();
    verify(status, atLeast(1)).getFilesWithNoReferences();
    verify(status, atLeast(1)).getFilesWithReferences();
    verify(status).getLeafPartitionCount();
    verify(status).getLeafPartitionFileReferenceStats();
    verify(status).getNonLeafPartitionCount();
    verify(status).getNonLeafPartitionFileReferenceStats();
    verify(status, atLeast(1)).getTotalRecords();
    verify(status).getTotalRecordsApprox();
    verify(status, atLeast(1)).getTotalRecordsInLeafPartitions();
    verify(status).getTotalRecordsInLeafPartitionsApprox();
    verify(status).getTotalRecordsInNonLeafPartitions();
    verify(status).getTotalRecordsInNonLeafPartitionsApprox();
    verify(status, atLeast(1)).isMoreThanMax();
  }

  /**
   * Test {@link StandardFileStatusReporter#report(TableFilesStatus, boolean)}.
   * <ul>
   *   <li>Given one.</li>
   *   <li>When {@code false}.</li>
   *   <li>Then calls {@link TableFilesStatus#getFileCount()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardFileStatusReporter#report(TableFilesStatus, boolean)}
   */
  @Test
  @DisplayName("Test report(TableFilesStatus, boolean); given one; when 'false'; then calls getFileCount()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void StandardFileStatusReporter.report(TableFilesStatus, boolean)"})
  void testReport_givenOne_whenFalse_thenCallsGetFileCount() {
    // Arrange
    StandardFileStatusReporter standardFileStatusReporter = new StandardFileStatusReporter();
    TableFilesStatus status = mock(TableFilesStatus.class);
    when(status.getTotalRecords()).thenReturn(1L);
    when(status.getTotalRecordsApprox()).thenReturn(1L);
    when(status.getTotalRecordsInLeafPartitions()).thenReturn(1L);
    when(status.getTotalRecordsInLeafPartitionsApprox()).thenReturn(1L);
    when(status.getTotalRecordsInNonLeafPartitions()).thenReturn(1L);
    when(status.getTotalRecordsInNonLeafPartitionsApprox()).thenReturn(1L);
    when(status.getNonLeafPartitionFileReferenceStats()).thenReturn(FileReferencesStats.from(new ArrayList<>()));
    when(status.isMoreThanMax()).thenReturn(true);
    when(status.getFileCount()).thenReturn(3);
    when(status.getLeafPartitionCount()).thenReturn(3);
    when(status.getNonLeafPartitionCount()).thenReturn(3);
    when(status.getFilesWithNoReferences()).thenReturn(new ArrayList<>());
    when(status.getFilesWithReferences()).thenReturn(new ArrayList<>());
    when(status.getFileReferenceCount()).thenReturn(3L);
    when(status.getLeafPartitionFileReferenceStats()).thenReturn(FileReferencesStats.from(new ArrayList<>()));

    // Act
    standardFileStatusReporter.report(status, false);

    // Assert
    verify(status).getFileCount();
    verify(status).getFileReferenceCount();
    verify(status).getFilesWithNoReferences();
    verify(status).getFilesWithReferences();
    verify(status).getLeafPartitionCount();
    verify(status).getLeafPartitionFileReferenceStats();
    verify(status).getNonLeafPartitionCount();
    verify(status).getNonLeafPartitionFileReferenceStats();
    verify(status, atLeast(1)).getTotalRecords();
    verify(status).getTotalRecordsApprox();
    verify(status, atLeast(1)).getTotalRecordsInLeafPartitions();
    verify(status).getTotalRecordsInLeafPartitionsApprox();
    verify(status).getTotalRecordsInNonLeafPartitions();
    verify(status).getTotalRecordsInNonLeafPartitionsApprox();
    verify(status, atLeast(1)).isMoreThanMax();
  }

  /**
   * Test {@link StandardFileStatusReporter#report(TableFilesStatus, boolean)}.
   * <ul>
   *   <li>When {@link TableFilesStatus} {@link TableFilesStatus#getTotalRecords()} return one.</li>
   *   <li>Then calls {@link TableFilesStatus#getFileCount()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardFileStatusReporter#report(TableFilesStatus, boolean)}
   */
  @Test
  @DisplayName("Test report(TableFilesStatus, boolean); when TableFilesStatus getTotalRecords() return one; then calls getFileCount()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void StandardFileStatusReporter.report(TableFilesStatus, boolean)"})
  void testReport_whenTableFilesStatusGetTotalRecordsReturnOne_thenCallsGetFileCount() {
    // Arrange
    StandardFileStatusReporter standardFileStatusReporter = new StandardFileStatusReporter();
    TableFilesStatus status = mock(TableFilesStatus.class);
    when(status.getTotalRecords()).thenReturn(1L);
    when(status.getTotalRecordsApprox()).thenReturn(1L);
    when(status.getTotalRecordsInLeafPartitions()).thenReturn(1L);
    when(status.getTotalRecordsInLeafPartitionsApprox()).thenReturn(1L);
    when(status.getTotalRecordsInNonLeafPartitions()).thenReturn(1L);
    when(status.getTotalRecordsInNonLeafPartitionsApprox()).thenReturn(1L);
    when(status.getNonLeafPartitionFileReferenceStats()).thenReturn(FileReferencesStats.from(new ArrayList<>()));
    when(status.isMoreThanMax()).thenReturn(true);
    when(status.getFileCount()).thenReturn(3);
    when(status.getLeafPartitionCount()).thenReturn(3);
    when(status.getNonLeafPartitionCount()).thenReturn(3);
    when(status.getFilesWithNoReferences()).thenReturn(new ArrayList<>());
    when(status.getFilesWithReferences()).thenReturn(new ArrayList<>());
    when(status.getFileReferenceCount()).thenReturn(3L);
    when(status.getLeafPartitionFileReferenceStats()).thenReturn(FileReferencesStats.from(new ArrayList<>()));

    // Act
    standardFileStatusReporter.report(status, true);

    // Assert
    verify(status).getFileCount();
    verify(status).getFileReferenceCount();
    verify(status, atLeast(1)).getFilesWithNoReferences();
    verify(status, atLeast(1)).getFilesWithReferences();
    verify(status).getLeafPartitionCount();
    verify(status).getLeafPartitionFileReferenceStats();
    verify(status).getNonLeafPartitionCount();
    verify(status).getNonLeafPartitionFileReferenceStats();
    verify(status, atLeast(1)).getTotalRecords();
    verify(status).getTotalRecordsApprox();
    verify(status, atLeast(1)).getTotalRecordsInLeafPartitions();
    verify(status).getTotalRecordsInLeafPartitionsApprox();
    verify(status).getTotalRecordsInNonLeafPartitions();
    verify(status).getTotalRecordsInNonLeafPartitionsApprox();
    verify(status, atLeast(1)).isMoreThanMax();
  }
}
