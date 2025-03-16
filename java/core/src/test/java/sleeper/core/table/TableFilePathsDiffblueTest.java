package sleeper.core.table;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.partition.Partition;
import sleeper.core.partition.Partition.Builder;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.table.TableProperties;
import sleeper.core.range.Region;

class TableFilePathsDiffblueTest {
  /**
   * Test {@link TableFilePaths#fromPrefix(String)}.
   * <p>
   * Method under test: {@link TableFilePaths#fromPrefix(String)}
   */
  @Test
  @DisplayName("Test fromPrefix(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"TableFilePaths TableFilePaths.fromPrefix(String)"})
  void testFromPrefix() {
    // Arrange, Act and Assert
    assertEquals("Prefix", TableFilePaths.fromPrefix("Prefix").getFilePathPrefix());
  }

  /**
   * Test {@link TableFilePaths#buildDataFilePathPrefix(InstanceProperties, TableProperties)}.
   * <ul>
   *   <li>Then return FilePathPrefix is {@code s3a://null/null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TableFilePaths#buildDataFilePathPrefix(InstanceProperties, TableProperties)}
   */
  @Test
  @DisplayName("Test buildDataFilePathPrefix(InstanceProperties, TableProperties); then return FilePathPrefix is 's3a://null/null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"TableFilePaths TableFilePaths.buildDataFilePathPrefix(InstanceProperties, TableProperties)"})
  void testBuildDataFilePathPrefix_thenReturnFilePathPrefixIsS3aNullNull() {
    // Arrange
    InstanceProperties instanceProperties = new InstanceProperties();

    // Act and Assert
    assertEquals("s3a://null/null",
        TableFilePaths.buildDataFilePathPrefix(instanceProperties, new TableProperties(new InstanceProperties()))
            .getFilePathPrefix());
  }

  /**
   * Test {@link TableFilePaths#buildObjectKeyInDataBucket(TableProperties)}.
   * <ul>
   *   <li>Then return FilePathPrefix is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TableFilePaths#buildObjectKeyInDataBucket(TableProperties)}
   */
  @Test
  @DisplayName("Test buildObjectKeyInDataBucket(TableProperties); then return FilePathPrefix is 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"TableFilePaths TableFilePaths.buildObjectKeyInDataBucket(TableProperties)"})
  void testBuildObjectKeyInDataBucket_thenReturnFilePathPrefixIsNull() {
    // Arrange, Act and Assert
    assertNull(
        TableFilePaths.buildObjectKeyInDataBucket(new TableProperties(new InstanceProperties())).getFilePathPrefix());
  }

  /**
   * Test {@link TableFilePaths#constructPartitionParquetFilePath(Partition, String)} with {@code partition}, {@code fileName}.
   * <p>
   * Method under test: {@link TableFilePaths#constructPartitionParquetFilePath(Partition, String)}
   */
  @Test
  @DisplayName("Test constructPartitionParquetFilePath(Partition, String) with 'partition', 'fileName'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String TableFilePaths.constructPartitionParquetFilePath(Partition, String)"})
  void testConstructPartitionParquetFilePathWithPartitionFileName() {
    // Arrange
    TableFilePaths fromPrefixResult = TableFilePaths.fromPrefix("Prefix");
    Builder builderResult = Partition.builder();
    Builder parentPartitionIdResult = builderResult.childPartitionIds(new ArrayList<>())
        .dimension(1)
        .id("42")
        .leafPartition(true)
        .parentPartitionId("42");
    Partition partition = parentPartitionIdResult.region(new Region(new ArrayList<>())).build();

    // Act and Assert
    assertEquals("Prefix/data/partition_42/foo.txt.parquet",
        fromPrefixResult.constructPartitionParquetFilePath(partition, "foo.txt"));
  }

  /**
   * Test {@link TableFilePaths#constructPartitionParquetFilePath(String, String)} with {@code partitionId}, {@code fileName}.
   * <p>
   * Method under test: {@link TableFilePaths#constructPartitionParquetFilePath(String, String)}
   */
  @Test
  @DisplayName("Test constructPartitionParquetFilePath(String, String) with 'partitionId', 'fileName'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String TableFilePaths.constructPartitionParquetFilePath(String, String)"})
  void testConstructPartitionParquetFilePathWithPartitionIdFileName() {
    // Arrange, Act and Assert
    assertEquals("Prefix/data/partition_42/foo.txt.parquet",
        TableFilePaths.fromPrefix("Prefix").constructPartitionParquetFilePath("42", "foo.txt"));
  }

  /**
   * Test {@link TableFilePaths#constructQuantileSketchesFilePath(Partition, String)}.
   * <p>
   * Method under test: {@link TableFilePaths#constructQuantileSketchesFilePath(Partition, String)}
   */
  @Test
  @DisplayName("Test constructQuantileSketchesFilePath(Partition, String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String TableFilePaths.constructQuantileSketchesFilePath(Partition, String)"})
  void testConstructQuantileSketchesFilePath() {
    // Arrange
    TableFilePaths fromPrefixResult = TableFilePaths.fromPrefix("Prefix");
    Builder builderResult = Partition.builder();
    Builder parentPartitionIdResult = builderResult.childPartitionIds(new ArrayList<>())
        .dimension(1)
        .id("42")
        .leafPartition(true)
        .parentPartitionId("42");
    Partition partition = parentPartitionIdResult.region(new Region(new ArrayList<>())).build();

    // Act and Assert
    assertEquals("Prefix/data/partition_42/foo.txt.sketches",
        fromPrefixResult.constructQuantileSketchesFilePath(partition, "foo.txt"));
  }

  /**
   * Test {@link TableFilePaths#constructCompactionJobBatchPath(String)}.
   * <p>
   * Method under test: {@link TableFilePaths#constructCompactionJobBatchPath(String)}
   */
  @Test
  @DisplayName("Test constructCompactionJobBatchPath(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String TableFilePaths.constructCompactionJobBatchPath(String)"})
  void testConstructCompactionJobBatchPath() {
    // Arrange, Act and Assert
    assertEquals("Prefix/compactions/42.json",
        TableFilePaths.fromPrefix("Prefix").constructCompactionJobBatchPath("42"));
  }

  /**
   * Test {@link TableFilePaths#getFilePathPrefix()}.
   * <p>
   * Method under test: {@link TableFilePaths#getFilePathPrefix()}
   */
  @Test
  @DisplayName("Test getFilePathPrefix()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String TableFilePaths.getFilePathPrefix()"})
  void testGetFilePathPrefix() {
    // Arrange, Act and Assert
    assertEquals("Prefix", TableFilePaths.fromPrefix("Prefix").getFilePathPrefix());
  }
}
