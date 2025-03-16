package sleeper.ingest.runner.impl.partitionfilewriter;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.partition.Partition;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.table.TableProperties;
import sleeper.core.table.TableFilePaths;
import sleeper.ingest.runner.impl.ParquetConfiguration;
import software.amazon.awssdk.transfer.s3.S3TransferManager;

class AsyncS3PartitionFileWriterDiffblueTest {
  /**
   * Test {@link AsyncS3PartitionFileWriter#AsyncS3PartitionFileWriter(Partition, ParquetConfiguration, String, TableFilePaths, S3TransferManager, String, String)}.
   * <ul>
   *   <li>Then throw {@link IOException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AsyncS3PartitionFileWriter#AsyncS3PartitionFileWriter(Partition, ParquetConfiguration, String, TableFilePaths, S3TransferManager, String, String)}
   */
  @Test
  @DisplayName("Test new AsyncS3PartitionFileWriter(Partition, ParquetConfiguration, String, TableFilePaths, S3TransferManager, String, String); then throw IOException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void AsyncS3PartitionFileWriter.<init>(Partition, ParquetConfiguration, String, TableFilePaths, S3TransferManager, String, String)"})
  void testNewAsyncS3PartitionFileWriter_thenThrowIOException() throws IOException {
    // Arrange
    Partition partition = mock(Partition.class);
    when(partition.getId()).thenReturn("42");
    ParquetConfiguration parquetConfiguration = mock(ParquetConfiguration.class);
    when(parquetConfiguration.getHadoopConfiguration()).thenReturn(new Configuration());
    when(parquetConfiguration.createParquetWriter(Mockito.<String>any()))
        .thenThrow(new IOException("%s/partition_%s_%s.parquet"));
    when(parquetConfiguration.getTableProperties()).thenReturn(new TableProperties(new InstanceProperties()));

    // Act and Assert
    assertThrows(IOException.class,
        () -> new AsyncS3PartitionFileWriter(partition, parquetConfiguration, "s3://bucket-name/object-key",
            TableFilePaths.fromPrefix("Prefix"), mock(S3TransferManager.class), "/directory", "foo.txt"));

    verify(partition, atLeast(1)).getId();
    verify(parquetConfiguration).createParquetWriter(eq("/directory/partition_42_foo.txt.parquet"));
    verify(parquetConfiguration).getHadoopConfiguration();
    verify(parquetConfiguration).getTableProperties();
  }
}
