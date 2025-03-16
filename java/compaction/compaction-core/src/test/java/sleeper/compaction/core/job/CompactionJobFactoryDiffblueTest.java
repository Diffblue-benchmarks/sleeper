package sleeper.compaction.core.job;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.table.TableProperties;
import sleeper.core.properties.table.TableProperty;
import sleeper.core.statestore.FileReference;
import sleeper.core.statestore.FileReference.Builder;

class CompactionJobFactoryDiffblueTest {
  /**
   * Test {@link CompactionJobFactory#CompactionJobFactory(InstanceProperties, TableProperties)}.
   * <ul>
   *   <li>Then return OutputFilePrefix is {@code s3a://null/null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobFactory#CompactionJobFactory(InstanceProperties, TableProperties)}
   */
  @Test
  @DisplayName("Test new CompactionJobFactory(InstanceProperties, TableProperties); then return OutputFilePrefix is 's3a://null/null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void CompactionJobFactory.<init>(InstanceProperties, TableProperties)"})
  void testNewCompactionJobFactory_thenReturnOutputFilePrefixIsS3aNullNull() {
    // Arrange
    InstanceProperties instanceProperties = new InstanceProperties();

    // Act and Assert
    assertEquals("s3a://null/null",
        (new CompactionJobFactory(instanceProperties, new TableProperties(new InstanceProperties())))
            .getOutputFilePrefix());
  }

  /**
   * Test {@link CompactionJobFactory#CompactionJobFactory(InstanceProperties, TableProperties, Supplier)}.
   * <ul>
   *   <li>Then return OutputFilePrefix is {@code s3a://null/null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobFactory#CompactionJobFactory(InstanceProperties, TableProperties, Supplier)}
   */
  @Test
  @DisplayName("Test new CompactionJobFactory(InstanceProperties, TableProperties, Supplier); then return OutputFilePrefix is 's3a://null/null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void CompactionJobFactory.<init>(InstanceProperties, TableProperties, Supplier)"})
  void testNewCompactionJobFactory_thenReturnOutputFilePrefixIsS3aNullNull2() {
    // Arrange
    InstanceProperties instanceProperties = new InstanceProperties();

    // Act and Assert
    assertEquals("s3a://null/null", (new CompactionJobFactory(instanceProperties,
        new TableProperties(new InstanceProperties()), mock(Supplier.class))).getOutputFilePrefix());
  }

  /**
   * Test {@link CompactionJobFactory#getOutputFilePrefix()}.
   * <p>
   * Method under test: {@link CompactionJobFactory#getOutputFilePrefix()}
   */
  @Test
  @DisplayName("Test getOutputFilePrefix()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String CompactionJobFactory.getOutputFilePrefix()"})
  void testGetOutputFilePrefix() {
    // Arrange
    InstanceProperties instanceProperties = new InstanceProperties();

    // Act and Assert
    assertEquals("s3a://null/null",
        (new CompactionJobFactory(instanceProperties, new TableProperties(new InstanceProperties())))
            .getOutputFilePrefix());
  }

  /**
   * Test {@link CompactionJobFactory#createCompactionJob(List, String)} with {@code files}, {@code partition}.
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobFactory#createCompactionJob(List, String)}
   */
  @Test
  @DisplayName("Test createCompactionJob(List, String) with 'files', 'partition'; then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"CompactionJob CompactionJobFactory.createCompactionJob(List, String)"})
  void testCreateCompactionJobWithFilesPartition_thenThrowIllegalArgumentException() {
    // Arrange
    Supplier<String> jobIdSupplier = mock(Supplier.class);
    when(jobIdSupplier.get()).thenReturn("Get");
    InstanceProperties instanceProperties = new InstanceProperties();
    CompactionJobFactory compactionJobFactory = new CompactionJobFactory(instanceProperties,
        new TableProperties(new InstanceProperties()), jobIdSupplier);

    ArrayList<FileReference> files = new ArrayList<>();
    Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference buildResult = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    files.add(buildResult);

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> compactionJobFactory.createCompactionJob(files, "Partition"));
    verify(jobIdSupplier).get();
  }

  /**
   * Test {@link CompactionJobFactory#createCompactionJob(List, String)} with {@code files}, {@code partition}.
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobFactory#createCompactionJob(List, String)}
   */
  @Test
  @DisplayName("Test createCompactionJob(List, String) with 'files', 'partition'; then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"CompactionJob CompactionJobFactory.createCompactionJob(List, String)"})
  void testCreateCompactionJobWithFilesPartition_thenThrowIllegalArgumentException2() {
    // Arrange
    Supplier<String> jobIdSupplier = mock(Supplier.class);
    when(jobIdSupplier.get()).thenReturn("Get");
    InstanceProperties instanceProperties = new InstanceProperties();
    CompactionJobFactory compactionJobFactory = new CompactionJobFactory(instanceProperties,
        new TableProperties(new InstanceProperties()), jobIdSupplier);

    ArrayList<FileReference> files = new ArrayList<>();
    Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference buildResult = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    files.add(buildResult);
    Builder jobIdResult2 = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference buildResult2 = jobIdResult2
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    files.add(buildResult2);

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> compactionJobFactory.createCompactionJob(files, "Partition"));
    verify(jobIdSupplier).get();
  }

  /**
   * Test {@link CompactionJobFactory#createCompactionJob(String, List, String)} with {@code jobId}, {@code files}, {@code partition}.
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobFactory#createCompactionJob(String, List, String)}
   */
  @Test
  @DisplayName("Test createCompactionJob(String, List, String) with 'jobId', 'files', 'partition'; then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"CompactionJob CompactionJobFactory.createCompactionJob(String, List, String)"})
  void testCreateCompactionJobWithJobIdFilesPartition_thenThrowIllegalArgumentException() {
    // Arrange
    InstanceProperties instanceProperties = new InstanceProperties();
    CompactionJobFactory compactionJobFactory = new CompactionJobFactory(instanceProperties,
        new TableProperties(new InstanceProperties()));

    ArrayList<FileReference> files = new ArrayList<>();
    Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference buildResult = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    files.add(buildResult);

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> compactionJobFactory.createCompactionJob("42", files, "Partition"));
  }

  /**
   * Test {@link CompactionJobFactory#createCompactionJob(String, List, String)} with {@code jobId}, {@code files}, {@code partition}.
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobFactory#createCompactionJob(String, List, String)}
   */
  @Test
  @DisplayName("Test createCompactionJob(String, List, String) with 'jobId', 'files', 'partition'; then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"CompactionJob CompactionJobFactory.createCompactionJob(String, List, String)"})
  void testCreateCompactionJobWithJobIdFilesPartition_thenThrowIllegalArgumentException2() {
    // Arrange
    InstanceProperties instanceProperties = new InstanceProperties();
    CompactionJobFactory compactionJobFactory = new CompactionJobFactory(instanceProperties,
        new TableProperties(new InstanceProperties()));

    ArrayList<FileReference> files = new ArrayList<>();
    Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference buildResult = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    files.add(buildResult);
    Builder jobIdResult2 = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference buildResult2 = jobIdResult2
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    files.add(buildResult2);

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> compactionJobFactory.createCompactionJob("42", files, "Partition"));
  }

  /**
   * Test {@link CompactionJobFactory#createCompactionJobWithFilenames(String, List, String)}.
   * <ul>
   *   <li>Then return InputFiles is {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobFactory#createCompactionJobWithFilenames(String, List, String)}
   */
  @Test
  @DisplayName("Test createCompactionJobWithFilenames(String, List, String); then return InputFiles is ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"CompactionJob CompactionJobFactory.createCompactionJobWithFilenames(String, List, String)"})
  void testCreateCompactionJobWithFilenames_thenReturnInputFilesIsArrayList() {
    // Arrange
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.get(Mockito.<TableProperty>any())).thenReturn("Get");
    CompactionJobFactory compactionJobFactory = new CompactionJobFactory(new InstanceProperties(), tableProperties);

    ArrayList<String> filenames = new ArrayList<>();
    filenames.add("tableId must not be null");

    // Act
    CompactionJob actualCreateCompactionJobWithFilenamesResult = compactionJobFactory
        .createCompactionJobWithFilenames("42", filenames, "42");

    // Assert
    verify(tableProperties, atLeast(1)).get(Mockito.<TableProperty>any());
    assertEquals("42", actualCreateCompactionJobWithFilenamesResult.getId());
    assertEquals("42", actualCreateCompactionJobWithFilenamesResult.getPartitionId());
    assertEquals("Get", actualCreateCompactionJobWithFilenamesResult.getIteratorClassName());
    assertEquals("Get", actualCreateCompactionJobWithFilenamesResult.getIteratorConfig());
    assertEquals("Get", actualCreateCompactionJobWithFilenamesResult.getTableId());
    assertEquals("s3a://null/Get/data/partition_42/42.parquet",
        actualCreateCompactionJobWithFilenamesResult.getOutputFile());
    assertSame(filenames, actualCreateCompactionJobWithFilenamesResult.getInputFiles());
  }

  /**
   * Test {@link CompactionJobFactory#createCompactionJobWithFilenames(String, List, String)}.
   * <ul>
   *   <li>Then return InputFiles is {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobFactory#createCompactionJobWithFilenames(String, List, String)}
   */
  @Test
  @DisplayName("Test createCompactionJobWithFilenames(String, List, String); then return InputFiles is ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"CompactionJob CompactionJobFactory.createCompactionJobWithFilenames(String, List, String)"})
  void testCreateCompactionJobWithFilenames_thenReturnInputFilesIsArrayList2() {
    // Arrange
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.get(Mockito.<TableProperty>any())).thenReturn("Get");
    CompactionJobFactory compactionJobFactory = new CompactionJobFactory(new InstanceProperties(), tableProperties);

    ArrayList<String> filenames = new ArrayList<>();
    filenames.add("jobId must not be null");
    filenames.add("tableId must not be null");

    // Act
    CompactionJob actualCreateCompactionJobWithFilenamesResult = compactionJobFactory
        .createCompactionJobWithFilenames("42", filenames, "42");

    // Assert
    verify(tableProperties, atLeast(1)).get(Mockito.<TableProperty>any());
    assertEquals("42", actualCreateCompactionJobWithFilenamesResult.getId());
    assertEquals("42", actualCreateCompactionJobWithFilenamesResult.getPartitionId());
    assertEquals("Get", actualCreateCompactionJobWithFilenamesResult.getIteratorClassName());
    assertEquals("Get", actualCreateCompactionJobWithFilenamesResult.getIteratorConfig());
    assertEquals("Get", actualCreateCompactionJobWithFilenamesResult.getTableId());
    assertEquals("s3a://null/Get/data/partition_42/42.parquet",
        actualCreateCompactionJobWithFilenamesResult.getOutputFile());
    assertSame(filenames, actualCreateCompactionJobWithFilenamesResult.getInputFiles());
  }

  /**
   * Test {@link CompactionJobFactory#createCompactionJobWithFilenames(String, List, String)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return InputFiles Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobFactory#createCompactionJobWithFilenames(String, List, String)}
   */
  @Test
  @DisplayName("Test createCompactionJobWithFilenames(String, List, String); when ArrayList(); then return InputFiles Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"CompactionJob CompactionJobFactory.createCompactionJobWithFilenames(String, List, String)"})
  void testCreateCompactionJobWithFilenames_whenArrayList_thenReturnInputFilesEmpty() {
    // Arrange
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.get(Mockito.<TableProperty>any())).thenReturn("Get");
    CompactionJobFactory compactionJobFactory = new CompactionJobFactory(new InstanceProperties(), tableProperties);

    // Act
    CompactionJob actualCreateCompactionJobWithFilenamesResult = compactionJobFactory
        .createCompactionJobWithFilenames("42", new ArrayList<>(), "42");

    // Assert
    verify(tableProperties, atLeast(1)).get(Mockito.<TableProperty>any());
    assertEquals("42", actualCreateCompactionJobWithFilenamesResult.getId());
    assertEquals("42", actualCreateCompactionJobWithFilenamesResult.getPartitionId());
    assertEquals("Get", actualCreateCompactionJobWithFilenamesResult.getIteratorClassName());
    assertEquals("Get", actualCreateCompactionJobWithFilenamesResult.getIteratorConfig());
    assertEquals("Get", actualCreateCompactionJobWithFilenamesResult.getTableId());
    assertEquals("s3a://null/Get/data/partition_42/42.parquet",
        actualCreateCompactionJobWithFilenamesResult.getOutputFile());
    assertTrue(actualCreateCompactionJobWithFilenamesResult.getInputFiles().isEmpty());
  }
}
