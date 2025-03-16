package sleeper.ingest.runner.impl;

import static org.junit.jupiter.api.Assertions.assertSame;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.apache.hadoop.conf.Configuration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.table.TableProperties;
import sleeper.ingest.runner.impl.ParquetConfiguration.Builder;

class ParquetConfigurationDiffblueTest {
  /**
   * Test Builder {@link Builder#build()}.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Builder#build()}
   *   <li>{@link Builder#hadoopConfiguration(Configuration)}
   *   <li>{@link Builder#tableProperties(TableProperties)}
   * </ul>
   */
  @Test
  @DisplayName("Test Builder build()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"ParquetConfiguration Builder.build()", "Builder Builder.hadoopConfiguration(Configuration)",
      "Builder Builder.tableProperties(TableProperties)"})
  void testBuilderBuild() {
    // Arrange
    Builder builderResult = ParquetConfiguration.builder();
    Configuration hadoopConfiguration = new Configuration();
    Builder hadoopConfigurationResult = builderResult.hadoopConfiguration(hadoopConfiguration);
    TableProperties tableProperties = new TableProperties(new InstanceProperties());

    // Act
    ParquetConfiguration actualBuildResult = hadoopConfigurationResult.tableProperties(tableProperties).build();

    // Assert
    assertSame(hadoopConfiguration, actualBuildResult.getHadoopConfiguration());
    assertSame(tableProperties, actualBuildResult.getTableProperties());
  }

  /**
   * Test {@link ParquetConfiguration#from(TableProperties, Configuration)}.
   * <ul>
   *   <li>Then return HadoopConfiguration is {@link Configuration#Configuration()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ParquetConfiguration#from(TableProperties, Configuration)}
   */
  @Test
  @DisplayName("Test from(TableProperties, Configuration); then return HadoopConfiguration is Configuration()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"ParquetConfiguration ParquetConfiguration.from(TableProperties, Configuration)"})
  void testFrom_thenReturnHadoopConfigurationIsConfiguration() {
    // Arrange
    TableProperties tableProperties = new TableProperties(new InstanceProperties());
    Configuration hadoopConfiguration = new Configuration();

    // Act
    ParquetConfiguration actualFromResult = ParquetConfiguration.from(tableProperties, hadoopConfiguration);

    // Assert
    assertSame(hadoopConfiguration, actualFromResult.getHadoopConfiguration());
    assertSame(tableProperties, actualFromResult.getTableProperties());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link ParquetConfiguration#builderWith(TableProperties)}
   *   <li>{@link ParquetConfiguration#getHadoopConfiguration()}
   *   <li>{@link ParquetConfiguration#getTableProperties()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder ParquetConfiguration.builderWith(TableProperties)",
      "Configuration ParquetConfiguration.getHadoopConfiguration()",
      "TableProperties ParquetConfiguration.getTableProperties()"})
  void testGettersAndSetters() {
    // Arrange
    Builder builderResult = ParquetConfiguration.builder();
    Configuration hadoopConfiguration = new Configuration();
    Builder hadoopConfigurationResult = builderResult.hadoopConfiguration(hadoopConfiguration);
    TableProperties tableProperties = new TableProperties(new InstanceProperties());
    ParquetConfiguration buildResult = hadoopConfigurationResult.tableProperties(tableProperties).build();

    // Act
    buildResult.builderWith(new TableProperties(new InstanceProperties()));
    Configuration actualHadoopConfiguration = buildResult.getHadoopConfiguration();

    // Assert
    assertSame(hadoopConfiguration, actualHadoopConfiguration);
    assertSame(tableProperties, buildResult.getTableProperties());
  }
}
