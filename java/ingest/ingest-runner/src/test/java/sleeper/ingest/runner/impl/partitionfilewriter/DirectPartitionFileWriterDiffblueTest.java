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

class DirectPartitionFileWriterDiffblueTest {
  /**
   * Test {@link DirectPartitionFileWriter#DirectPartitionFileWriter(Partition, ParquetConfiguration, TableFilePaths, String)}.
   * <ul>
   *   <li>Then throw {@link IOException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DirectPartitionFileWriter#DirectPartitionFileWriter(Partition, ParquetConfiguration, TableFilePaths, String)}
   */
  @Test
  @DisplayName("Test new DirectPartitionFileWriter(Partition, ParquetConfiguration, TableFilePaths, String); then throw IOException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DirectPartitionFileWriter.<init>(Partition, ParquetConfiguration, TableFilePaths, String)"})
  void testNewDirectPartitionFileWriter_thenThrowIOException() throws IOException {
    // Arrange
    Partition partition = mock(Partition.class);
    when(partition.getId()).thenReturn("42");
    ParquetConfiguration parquetConfiguration = mock(ParquetConfiguration.class);
    when(parquetConfiguration.getHadoopConfiguration()).thenReturn(new Configuration());
    when(parquetConfiguration.createParquetWriter(Mockito.<String>any()))
        .thenThrow(new IOException("Created Parquet writer for partition {} to file {}"));
    when(parquetConfiguration.getTableProperties()).thenReturn(new TableProperties(new InstanceProperties()));

    // Act and Assert
    assertThrows(IOException.class, () -> new DirectPartitionFileWriter(partition, parquetConfiguration,
        TableFilePaths.fromPrefix("Prefix"), "foo.txt"));

    verify(partition, atLeast(1)).getId();
    verify(parquetConfiguration).createParquetWriter(eq("Prefix/data/partition_42/foo.txt.parquet"));
    verify(parquetConfiguration).getHadoopConfiguration();
    verify(parquetConfiguration).getTableProperties();
  }
}
