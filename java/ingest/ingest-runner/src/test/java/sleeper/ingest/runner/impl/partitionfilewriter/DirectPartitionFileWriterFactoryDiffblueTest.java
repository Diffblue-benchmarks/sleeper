package sleeper.ingest.runner.impl.partitionfilewriter;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.function.Supplier;
import org.apache.hadoop.conf.Configuration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.partition.Partition;
import sleeper.core.partition.Partition.Builder;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.table.TableProperties;
import sleeper.core.range.Region;
import sleeper.ingest.runner.impl.ParquetConfiguration;

class DirectPartitionFileWriterFactoryDiffblueTest {
  /**
   * Test {@link DirectPartitionFileWriterFactory#from(ParquetConfiguration, String, Supplier)} with {@code configuration}, {@code filePathPrefix}, {@code fileNameGenerator}.
   * <p>
   * Method under test: {@link DirectPartitionFileWriterFactory#from(ParquetConfiguration, String, Supplier)}
   */
  @Test
  @DisplayName("Test from(ParquetConfiguration, String, Supplier) with 'configuration', 'filePathPrefix', 'fileNameGenerator'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "DirectPartitionFileWriterFactory DirectPartitionFileWriterFactory.from(ParquetConfiguration, String, Supplier)"})
  void testFromWithConfigurationFilePathPrefixFileNameGenerator() {
    // Arrange
    ParquetConfiguration configuration = mock(ParquetConfiguration.class);
    when(configuration.getHadoopConfiguration())
        .thenThrow(new RuntimeException("Created Parquet writer for partition {} to file {}"));
    when(configuration.getTableProperties()).thenReturn(new TableProperties(new InstanceProperties()));
    Supplier<String> fileNameGenerator = mock(Supplier.class);
    when(fileNameGenerator.get()).thenReturn("Get");

    // Act
    DirectPartitionFileWriterFactory actualFromResult = DirectPartitionFileWriterFactory.from(configuration,
        "/directory/foo.txt", fileNameGenerator);
    Builder builderResult = Partition.builder();
    Builder parentPartitionIdResult = builderResult.childPartitionIds(new ArrayList<>())
        .dimension(1)
        .id("42")
        .leafPartition(true)
        .parentPartitionId("42");
    Partition partition = parentPartitionIdResult.region(new Region(new ArrayList<>())).build();

    // Assert
    assertThrows(RuntimeException.class, () -> actualFromResult.createPartitionFileWriter(partition));
    verify(fileNameGenerator).get();
    verify(configuration).getHadoopConfiguration();
    verify(configuration).getTableProperties();
  }

  /**
   * Test {@link DirectPartitionFileWriterFactory#from(ParquetConfiguration, String, Supplier)} with {@code configuration}, {@code filePathPrefix}, {@code fileNameGenerator}.
   * <p>
   * Method under test: {@link DirectPartitionFileWriterFactory#from(ParquetConfiguration, String, Supplier)}
   */
  @Test
  @DisplayName("Test from(ParquetConfiguration, String, Supplier) with 'configuration', 'filePathPrefix', 'fileNameGenerator'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "DirectPartitionFileWriterFactory DirectPartitionFileWriterFactory.from(ParquetConfiguration, String, Supplier)"})
  void testFromWithConfigurationFilePathPrefixFileNameGenerator2() {
    // Arrange
    ParquetConfiguration configuration = mock(ParquetConfiguration.class);
    Supplier<String> fileNameGenerator = mock(Supplier.class);
    when(fileNameGenerator.get()).thenThrow(new RuntimeException("foo"));

    // Act
    DirectPartitionFileWriterFactory actualFromResult = DirectPartitionFileWriterFactory.from(configuration,
        "/directory/foo.txt", fileNameGenerator);
    Builder builderResult = Partition.builder();
    Builder parentPartitionIdResult = builderResult.childPartitionIds(new ArrayList<>())
        .dimension(1)
        .id("42")
        .leafPartition(true)
        .parentPartitionId("42");
    Partition partition = parentPartitionIdResult.region(new Region(new ArrayList<>())).build();

    // Assert
    assertThrows(RuntimeException.class, () -> actualFromResult.createPartitionFileWriter(partition));
    verify(fileNameGenerator).get();
  }

  /**
   * Test {@link DirectPartitionFileWriterFactory#from(ParquetConfiguration, String)} with {@code configuration}, {@code filePathPrefix}.
   * <ul>
   *   <li>Then throw {@link RuntimeException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DirectPartitionFileWriterFactory#from(ParquetConfiguration, String)}
   */
  @Test
  @DisplayName("Test from(ParquetConfiguration, String) with 'configuration', 'filePathPrefix'; then throw RuntimeException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "DirectPartitionFileWriterFactory DirectPartitionFileWriterFactory.from(ParquetConfiguration, String)"})
  void testFromWithConfigurationFilePathPrefix_thenThrowRuntimeException() {
    // Arrange
    ParquetConfiguration configuration = mock(ParquetConfiguration.class);
    when(configuration.getHadoopConfiguration())
        .thenThrow(new RuntimeException("Created Parquet writer for partition {} to file {}"));
    when(configuration.getTableProperties()).thenReturn(new TableProperties(new InstanceProperties()));

    // Act
    DirectPartitionFileWriterFactory actualFromResult = DirectPartitionFileWriterFactory.from(configuration,
        "/directory/foo.txt");
    Builder builderResult = Partition.builder();
    Builder parentPartitionIdResult = builderResult.childPartitionIds(new ArrayList<>())
        .dimension(1)
        .id("42")
        .leafPartition(true)
        .parentPartitionId("42");
    Partition partition = parentPartitionIdResult.region(new Region(new ArrayList<>())).build();

    // Assert
    assertThrows(RuntimeException.class, () -> actualFromResult.createPartitionFileWriter(partition));
    verify(configuration).getHadoopConfiguration();
    verify(configuration).getTableProperties();
  }

  /**
   * Test {@link DirectPartitionFileWriterFactory#from(ParquetConfiguration, InstanceProperties, TableProperties)} with {@code configuration}, {@code instanceProperties}, {@code tableProperties}.
   * <p>
   * Method under test: {@link DirectPartitionFileWriterFactory#from(ParquetConfiguration, InstanceProperties, TableProperties)}
   */
  @Test
  @DisplayName("Test from(ParquetConfiguration, InstanceProperties, TableProperties) with 'configuration', 'instanceProperties', 'tableProperties'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "DirectPartitionFileWriterFactory DirectPartitionFileWriterFactory.from(ParquetConfiguration, InstanceProperties, TableProperties)"})
  void testFromWithConfigurationInstancePropertiesTableProperties() {
    // Arrange
    ParquetConfiguration configuration = mock(ParquetConfiguration.class);
    when(configuration.getHadoopConfiguration())
        .thenThrow(new RuntimeException("Created Parquet writer for partition {} to file {}"));
    when(configuration.getTableProperties()).thenReturn(new TableProperties(new InstanceProperties()));
    InstanceProperties instanceProperties = new InstanceProperties();

    // Act
    DirectPartitionFileWriterFactory actualFromResult = DirectPartitionFileWriterFactory.from(configuration,
        instanceProperties, new TableProperties(new InstanceProperties()));
    Builder builderResult = Partition.builder();
    Builder parentPartitionIdResult = builderResult.childPartitionIds(new ArrayList<>())
        .dimension(1)
        .id("42")
        .leafPartition(true)
        .parentPartitionId("42");
    Partition partition = parentPartitionIdResult.region(new Region(new ArrayList<>())).build();

    // Assert
    assertThrows(RuntimeException.class, () -> actualFromResult.createPartitionFileWriter(partition));
    verify(configuration).getHadoopConfiguration();
    verify(configuration).getTableProperties();
  }

  /**
   * Test {@link DirectPartitionFileWriterFactory#from(ParquetConfiguration, InstanceProperties, TableProperties, Supplier)} with {@code configuration}, {@code instanceProperties}, {@code tableProperties}, {@code fileNameGenerator}.
   * <p>
   * Method under test: {@link DirectPartitionFileWriterFactory#from(ParquetConfiguration, InstanceProperties, TableProperties, Supplier)}
   */
  @Test
  @DisplayName("Test from(ParquetConfiguration, InstanceProperties, TableProperties, Supplier) with 'configuration', 'instanceProperties', 'tableProperties', 'fileNameGenerator'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "DirectPartitionFileWriterFactory DirectPartitionFileWriterFactory.from(ParquetConfiguration, InstanceProperties, TableProperties, Supplier)"})
  void testFromWithConfigurationInstancePropertiesTablePropertiesFileNameGenerator() {
    // Arrange
    ParquetConfiguration configuration = mock(ParquetConfiguration.class);
    when(configuration.getHadoopConfiguration())
        .thenThrow(new RuntimeException("Created Parquet writer for partition {} to file {}"));
    when(configuration.getTableProperties()).thenReturn(new TableProperties(new InstanceProperties()));
    InstanceProperties instanceProperties = new InstanceProperties();
    TableProperties tableProperties = new TableProperties(new InstanceProperties());
    Supplier<String> fileNameGenerator = mock(Supplier.class);
    when(fileNameGenerator.get()).thenReturn("Get");

    // Act
    DirectPartitionFileWriterFactory actualFromResult = DirectPartitionFileWriterFactory.from(configuration,
        instanceProperties, tableProperties, fileNameGenerator);
    Builder builderResult = Partition.builder();
    Builder parentPartitionIdResult = builderResult.childPartitionIds(new ArrayList<>())
        .dimension(1)
        .id("42")
        .leafPartition(true)
        .parentPartitionId("42");
    Partition partition = parentPartitionIdResult.region(new Region(new ArrayList<>())).build();

    // Assert
    assertThrows(RuntimeException.class, () -> actualFromResult.createPartitionFileWriter(partition));
    verify(fileNameGenerator).get();
    verify(configuration).getHadoopConfiguration();
    verify(configuration).getTableProperties();
  }

  /**
   * Test {@link DirectPartitionFileWriterFactory#from(ParquetConfiguration, InstanceProperties, TableProperties, Supplier)} with {@code configuration}, {@code instanceProperties}, {@code tableProperties}, {@code fileNameGenerator}.
   * <p>
   * Method under test: {@link DirectPartitionFileWriterFactory#from(ParquetConfiguration, InstanceProperties, TableProperties, Supplier)}
   */
  @Test
  @DisplayName("Test from(ParquetConfiguration, InstanceProperties, TableProperties, Supplier) with 'configuration', 'instanceProperties', 'tableProperties', 'fileNameGenerator'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "DirectPartitionFileWriterFactory DirectPartitionFileWriterFactory.from(ParquetConfiguration, InstanceProperties, TableProperties, Supplier)"})
  void testFromWithConfigurationInstancePropertiesTablePropertiesFileNameGenerator2() {
    // Arrange
    ParquetConfiguration configuration = mock(ParquetConfiguration.class);
    InstanceProperties instanceProperties = new InstanceProperties();
    TableProperties tableProperties = new TableProperties(new InstanceProperties());
    Supplier<String> fileNameGenerator = mock(Supplier.class);
    when(fileNameGenerator.get()).thenThrow(new RuntimeException("foo"));

    // Act
    DirectPartitionFileWriterFactory actualFromResult = DirectPartitionFileWriterFactory.from(configuration,
        instanceProperties, tableProperties, fileNameGenerator);
    Builder builderResult = Partition.builder();
    Builder parentPartitionIdResult = builderResult.childPartitionIds(new ArrayList<>())
        .dimension(1)
        .id("42")
        .leafPartition(true)
        .parentPartitionId("42");
    Partition partition = parentPartitionIdResult.region(new Region(new ArrayList<>())).build();

    // Assert
    assertThrows(RuntimeException.class, () -> actualFromResult.createPartitionFileWriter(partition));
    verify(fileNameGenerator).get();
  }

  /**
   * Test {@link DirectPartitionFileWriterFactory#createPartitionFileWriter(Partition)}.
   * <ul>
   *   <li>Then throw {@link RuntimeException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DirectPartitionFileWriterFactory#createPartitionFileWriter(Partition)}
   */
  @Test
  @DisplayName("Test createPartitionFileWriter(Partition); then throw RuntimeException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "sleeper.ingest.runner.impl.partitionfilewriter.PartitionFileWriter DirectPartitionFileWriterFactory.createPartitionFileWriter(Partition)"})
  void testCreatePartitionFileWriter_thenThrowRuntimeException() {
    // Arrange
    Supplier<String> fileNameGenerator = mock(Supplier.class);
    when(fileNameGenerator.get()).thenReturn("Get");
    ParquetConfiguration.Builder builderResult = ParquetConfiguration.builder();
    ParquetConfiguration.Builder hadoopConfigurationResult = builderResult.hadoopConfiguration(new Configuration());
    ParquetConfiguration configuration = hadoopConfigurationResult
        .tableProperties(new TableProperties(new InstanceProperties()))
        .build();
    DirectPartitionFileWriterFactory fromResult = DirectPartitionFileWriterFactory.from(configuration,
        "/directory/foo.txt", fileNameGenerator);
    Partition partition = mock(Partition.class);
    when(partition.getId()).thenThrow(new RuntimeException("/"));

    // Act and Assert
    assertThrows(RuntimeException.class, () -> fromResult.createPartitionFileWriter(partition));
    verify(fileNameGenerator).get();
    verify(partition).getId();
  }
}
