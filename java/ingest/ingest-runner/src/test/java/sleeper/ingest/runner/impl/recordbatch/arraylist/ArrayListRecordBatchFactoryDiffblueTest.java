package sleeper.ingest.runner.impl.recordbatch.arraylist;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.apache.hadoop.conf.Configuration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.table.TableProperties;
import sleeper.ingest.runner.impl.ParquetConfiguration;
import sleeper.ingest.runner.impl.recordbatch.RecordBatch;
import sleeper.ingest.runner.impl.recordbatch.arraylist.ArrayListRecordBatchFactory.Builder;

class ArrayListRecordBatchFactoryDiffblueTest {
  /**
   * Test Builder {@link Builder#instanceProperties(InstanceProperties)}.
   * <ul>
   *   <li>When {@link InstanceProperties#InstanceProperties()}.</li>
   *   <li>Then return builder.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#instanceProperties(InstanceProperties)}
   */
  @Test
  @DisplayName("Test Builder instanceProperties(InstanceProperties); when InstanceProperties(); then return builder")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.instanceProperties(InstanceProperties)"})
  void testBuilderInstanceProperties_whenInstanceProperties_thenReturnBuilder() {
    // Arrange
    Builder<?> builderResult = ArrayListRecordBatchFactory.builder();

    // Act and Assert
    assertSame(builderResult, builderResult.instanceProperties(new InstanceProperties()));
  }

  /**
   * Test {@link ArrayListRecordBatchFactory#createRecordBatch()}.
   * <p>
   * Method under test: {@link ArrayListRecordBatchFactory#createRecordBatch()}
   */
  @Test
  @DisplayName("Test createRecordBatch()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"RecordBatch ArrayListRecordBatchFactory.createRecordBatch()"})
  void testCreateRecordBatch() {
    // Arrange
    Builder<?> builderResult = ArrayListRecordBatchFactory.builder();
    Builder<?> maxNoOfRecordsInMemoryResult = builderResult.localWorkingDirectory("/directory")
        .maxNoOfRecordsInLocalStore(1L)
        .maxNoOfRecordsInMemory(3);
    ParquetConfiguration.Builder builderResult2 = ParquetConfiguration.builder();
    ParquetConfiguration.Builder hadoopConfigurationResult = builderResult2.hadoopConfiguration(new Configuration());
    ParquetConfiguration parquetConfiguration = hadoopConfigurationResult
        .tableProperties(new TableProperties(new InstanceProperties()))
        .build();
    ArrayListRecordBatchFactory<Object> buildResult = maxNoOfRecordsInMemoryResult
        .parquetConfiguration(parquetConfiguration)
        .<Object>recordMapper(mock(ArrayListRecordMapper.class))
        .build();

    // Act
    RecordBatch<Object> actualCreateRecordBatchResult = buildResult.createRecordBatch();

    // Assert
    assertTrue(actualCreateRecordBatchResult instanceof ArrayListRecordBatch);
    assertFalse(actualCreateRecordBatchResult.isFull());
  }
}
