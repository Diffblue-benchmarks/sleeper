package sleeper.ingest.runner.impl.recordbatch.arraylist;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
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
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.table.TableProperties;
import sleeper.core.record.Record;
import sleeper.ingest.runner.impl.ParquetConfiguration;
import sleeper.ingest.runner.impl.ParquetConfiguration.Builder;

class ArrayListRecordBatchDiffblueTest {
  /**
   * Test {@link ArrayListRecordBatch#ArrayListRecordBatch(ParquetConfiguration, ArrayListRecordMapper, String, int, long)}.
   * <p>
   * Method under test: {@link ArrayListRecordBatch#ArrayListRecordBatch(ParquetConfiguration, ArrayListRecordMapper, String, int, long)}
   */
  @Test
  @DisplayName("Test new ArrayListRecordBatch(ParquetConfiguration, ArrayListRecordMapper, String, int, long)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void ArrayListRecordBatch.<init>(ParquetConfiguration, ArrayListRecordMapper, String, int, long)"})
  void testNewArrayListRecordBatch() {
    // Arrange
    Builder builderResult = ParquetConfiguration.builder();
    Builder hadoopConfigurationResult = builderResult.hadoopConfiguration(new Configuration());
    ParquetConfiguration parquetConfiguration = hadoopConfigurationResult
        .tableProperties(new TableProperties(new InstanceProperties()))
        .build();

    // Act
    ArrayListRecordBatch<Object> actualArrayListRecordBatch = new ArrayListRecordBatch<>(parquetConfiguration,
        mock(ArrayListRecordMapper.class), "/directory", 3, 1L);

    // Assert
    assertFalse(actualArrayListRecordBatch.isFull());
  }

  /**
   * Test {@link ArrayListRecordBatch#ArrayListRecordBatch(ParquetConfiguration, ArrayListRecordMapper, String, int, long)}.
   * <ul>
   *   <li>Given {@link Configuration#Configuration()}.</li>
   *   <li>Then return not Full.</li>
   * </ul>
   * <p>
   * Method under test: {@link ArrayListRecordBatch#ArrayListRecordBatch(ParquetConfiguration, ArrayListRecordMapper, String, int, long)}
   */
  @Test
  @DisplayName("Test new ArrayListRecordBatch(ParquetConfiguration, ArrayListRecordMapper, String, int, long); given Configuration(); then return not Full")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void ArrayListRecordBatch.<init>(ParquetConfiguration, ArrayListRecordMapper, String, int, long)"})
  void testNewArrayListRecordBatch_givenConfiguration_thenReturnNotFull() {
    // Arrange
    ParquetConfiguration parquetConfiguration = mock(ParquetConfiguration.class);
    when(parquetConfiguration.getHadoopConfiguration()).thenReturn(new Configuration());
    when(parquetConfiguration.getTableProperties()).thenReturn(new TableProperties(new InstanceProperties()));

    // Act
    ArrayListRecordBatch<Object> actualArrayListRecordBatch = new ArrayListRecordBatch<>(parquetConfiguration,
        mock(ArrayListRecordMapper.class), "/directory", 3, 1L);

    // Assert
    verify(parquetConfiguration).getHadoopConfiguration();
    verify(parquetConfiguration).getTableProperties();
    assertFalse(actualArrayListRecordBatch.isFull());
  }

  /**
   * Test {@link ArrayListRecordBatch#ArrayListRecordBatch(ParquetConfiguration, ArrayListRecordMapper, String, int, long)}.
   * <ul>
   *   <li>Then throw {@link RuntimeException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ArrayListRecordBatch#ArrayListRecordBatch(ParquetConfiguration, ArrayListRecordMapper, String, int, long)}
   */
  @Test
  @DisplayName("Test new ArrayListRecordBatch(ParquetConfiguration, ArrayListRecordMapper, String, int, long); then throw RuntimeException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void ArrayListRecordBatch.<init>(ParquetConfiguration, ArrayListRecordMapper, String, int, long)"})
  void testNewArrayListRecordBatch_thenThrowRuntimeException() {
    // Arrange
    ParquetConfiguration parquetConfiguration = mock(ParquetConfiguration.class);
    when(parquetConfiguration.getHadoopConfiguration()).thenThrow(new RuntimeException("foo"));
    when(parquetConfiguration.getTableProperties()).thenReturn(new TableProperties(new InstanceProperties()));

    // Act and Assert
    assertThrows(RuntimeException.class,
        () -> new ArrayListRecordBatch<>(parquetConfiguration, mock(ArrayListRecordMapper.class), "/directory", 3, 1L));

    verify(parquetConfiguration).getHadoopConfiguration();
    verify(parquetConfiguration).getTableProperties();
  }

  /**
   * Test {@link ArrayListRecordBatch#addRecordToBatch(Record)}.
   * <p>
   * Method under test: {@link ArrayListRecordBatch#addRecordToBatch(Record)}
   */
  @Test
  @DisplayName("Test addRecordToBatch(Record)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ArrayListRecordBatch.addRecordToBatch(Record)"})
  void testAddRecordToBatch() throws IOException {
    // Arrange
    Builder builderResult = ParquetConfiguration.builder();
    Builder hadoopConfigurationResult = builderResult.hadoopConfiguration(new Configuration());
    ParquetConfiguration parquetConfiguration = hadoopConfigurationResult
        .tableProperties(new TableProperties(new InstanceProperties()))
        .build();
    ArrayListRecordBatch<Object> arrayListRecordBatch = new ArrayListRecordBatch<>(parquetConfiguration,
        mock(ArrayListRecordMapper.class), "/directory", 3, 1L);

    // Act
    arrayListRecordBatch.addRecordToBatch(new Record());

    // Assert that nothing has changed
    assertFalse(arrayListRecordBatch.isFull());
  }

  /**
   * Test {@link ArrayListRecordBatch#addRecordToBatch(Record)}.
   * <p>
   * Method under test: {@link ArrayListRecordBatch#addRecordToBatch(Record)}
   */
  @Test
  @DisplayName("Test addRecordToBatch(Record)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ArrayListRecordBatch.addRecordToBatch(Record)"})
  void testAddRecordToBatch2() throws IOException {
    // Arrange
    Builder builderResult = ParquetConfiguration.builder();
    Builder hadoopConfigurationResult = builderResult.hadoopConfiguration(new Configuration());
    ParquetConfiguration parquetConfiguration = hadoopConfigurationResult
        .tableProperties(new TableProperties(new InstanceProperties()))
        .build();
    ArrayListRecordBatch<Object> arrayListRecordBatch = new ArrayListRecordBatch<>(parquetConfiguration,
        mock(ArrayListRecordMapper.class), "/directory", 0, 1L);

    // Act
    arrayListRecordBatch.addRecordToBatch(new Record());

    // Assert
    assertTrue(arrayListRecordBatch.isFull());
  }

  /**
   * Test {@link ArrayListRecordBatch#append(Object)}.
   * <p>
   * Method under test: {@link ArrayListRecordBatch#append(Object)}
   */
  @Test
  @DisplayName("Test append(Object)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ArrayListRecordBatch.append(Object)"})
  void testAppend() throws IOException {
    // Arrange
    ArrayListRecordMapper<Object> recordMapper = mock(ArrayListRecordMapper.class);
    when(recordMapper.map(Mockito.<Object>any())).thenReturn(new Record());
    Builder builderResult = ParquetConfiguration.builder();
    Builder hadoopConfigurationResult = builderResult.hadoopConfiguration(new Configuration());
    ParquetConfiguration parquetConfiguration = hadoopConfigurationResult
        .tableProperties(new TableProperties(new InstanceProperties()))
        .build();
    ArrayListRecordBatch<Object> arrayListRecordBatch = new ArrayListRecordBatch<>(parquetConfiguration, recordMapper,
        "/directory", 3, 1L);

    // Act
    arrayListRecordBatch.append("Data");

    // Assert that nothing has changed
    verify(recordMapper).map(isA(Object.class));
    assertFalse(arrayListRecordBatch.isFull());
  }

  /**
   * Test {@link ArrayListRecordBatch#append(Object)}.
   * <p>
   * Method under test: {@link ArrayListRecordBatch#append(Object)}
   */
  @Test
  @DisplayName("Test append(Object)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ArrayListRecordBatch.append(Object)"})
  void testAppend2() throws IOException {
    // Arrange
    ArrayListRecordMapper<Object> recordMapper = mock(ArrayListRecordMapper.class);
    when(recordMapper.map(Mockito.<Object>any())).thenReturn(new Record());
    Builder builderResult = ParquetConfiguration.builder();
    Builder hadoopConfigurationResult = builderResult.hadoopConfiguration(new Configuration());
    ParquetConfiguration parquetConfiguration = hadoopConfigurationResult
        .tableProperties(new TableProperties(new InstanceProperties()))
        .build();
    ArrayListRecordBatch<Object> arrayListRecordBatch = new ArrayListRecordBatch<>(parquetConfiguration, recordMapper,
        "/directory", 0, 1L);

    // Act
    arrayListRecordBatch.append("Data");

    // Assert
    verify(recordMapper).map(isA(Object.class));
    assertTrue(arrayListRecordBatch.isFull());
  }

  /**
   * Test {@link ArrayListRecordBatch#append(Object)}.
   * <ul>
   *   <li>Then throw {@link RuntimeException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ArrayListRecordBatch#append(Object)}
   */
  @Test
  @DisplayName("Test append(Object); then throw RuntimeException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ArrayListRecordBatch.append(Object)"})
  void testAppend_thenThrowRuntimeException() throws IOException {
    // Arrange
    ArrayListRecordMapper<Object> recordMapper = mock(ArrayListRecordMapper.class);
    when(recordMapper.map(Mockito.<Object>any())).thenThrow(new RuntimeException("foo"));
    Builder builderResult = ParquetConfiguration.builder();
    Builder hadoopConfigurationResult = builderResult.hadoopConfiguration(new Configuration());
    ParquetConfiguration parquetConfiguration = hadoopConfigurationResult
        .tableProperties(new TableProperties(new InstanceProperties()))
        .build();
    ArrayListRecordBatch<Object> arrayListRecordBatch = new ArrayListRecordBatch<>(parquetConfiguration, recordMapper,
        "/directory", 3, 1L);

    // Act and Assert
    assertThrows(RuntimeException.class, () -> arrayListRecordBatch.append("Data"));
    verify(recordMapper).map(isA(Object.class));
  }

  /**
   * Test {@link ArrayListRecordBatch#isFull()}.
   * <p>
   * Method under test: {@link ArrayListRecordBatch#isFull()}
   */
  @Test
  @DisplayName("Test isFull()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean ArrayListRecordBatch.isFull()"})
  void testIsFull() {
    // Arrange
    Builder builderResult = ParquetConfiguration.builder();
    Builder hadoopConfigurationResult = builderResult.hadoopConfiguration(new Configuration());
    ParquetConfiguration parquetConfiguration = hadoopConfigurationResult
        .tableProperties(new TableProperties(new InstanceProperties()))
        .build();
    ArrayListRecordBatch<Object> arrayListRecordBatch = new ArrayListRecordBatch<>(parquetConfiguration,
        mock(ArrayListRecordMapper.class), "/directory", 3, 1L);

    // Act and Assert
    assertFalse(arrayListRecordBatch.isFull());
  }

  /**
   * Test {@link ArrayListRecordBatch#isFull()}.
   * <p>
   * Method under test: {@link ArrayListRecordBatch#isFull()}
   */
  @Test
  @DisplayName("Test isFull()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean ArrayListRecordBatch.isFull()"})
  void testIsFull2() {
    // Arrange
    Builder builderResult = ParquetConfiguration.builder();
    Builder hadoopConfigurationResult = builderResult.hadoopConfiguration(new Configuration());
    ParquetConfiguration parquetConfiguration = hadoopConfigurationResult
        .tableProperties(new TableProperties(new InstanceProperties()))
        .build();
    ArrayListRecordBatch<Object> arrayListRecordBatch = new ArrayListRecordBatch<>(parquetConfiguration,
        mock(ArrayListRecordMapper.class), "/directory", 0, 1L);

    // Act and Assert
    assertFalse(arrayListRecordBatch.isFull());
  }

  /**
   * Test {@link ArrayListRecordBatch#isFull()}.
   * <ul>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ArrayListRecordBatch#isFull()}
   */
  @Test
  @DisplayName("Test isFull(); then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean ArrayListRecordBatch.isFull()"})
  void testIsFull_thenReturnTrue() {
    // Arrange
    Builder builderResult = ParquetConfiguration.builder();
    Builder hadoopConfigurationResult = builderResult.hadoopConfiguration(new Configuration());
    ParquetConfiguration parquetConfiguration = hadoopConfigurationResult
        .tableProperties(new TableProperties(new InstanceProperties()))
        .build();
    ArrayListRecordBatch<Object> arrayListRecordBatch = new ArrayListRecordBatch<>(parquetConfiguration,
        mock(ArrayListRecordMapper.class), "/directory", 0, -1L);

    // Act and Assert
    assertTrue(arrayListRecordBatch.isFull());
  }
}
