package sleeper.systemtest.dsl.metrics;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.metrics.TableMetrics;

class SystemTestTableMetricsDiffblueTest {
  /**
   * Test {@link SystemTestTableMetrics#SystemTestTableMetrics(TableMetricsDriver)}.
   * <p>
   * Method under test: {@link SystemTestTableMetrics#SystemTestTableMetrics(TableMetricsDriver)}
   */
  @Test
  @DisplayName("Test new SystemTestTableMetrics(TableMetricsDriver)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SystemTestTableMetrics.<init>(TableMetricsDriver)"})
  void testNewSystemTestTableMetrics() {
    // Arrange, Act and Assert
    assertNull((new SystemTestTableMetrics(mock(TableMetricsDriver.class))).get());
  }

  /**
   * Test {@link SystemTestTableMetrics#generate()}.
   * <p>
   * Method under test: {@link SystemTestTableMetrics#generate()}
   */
  @Test
  @DisplayName("Test generate()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"SystemTestTableMetrics SystemTestTableMetrics.generate()"})
  void testGenerate() {
    // Arrange
    TableMetricsDriver driver = mock(TableMetricsDriver.class);
    doNothing().when(driver).generateTableMetrics();
    SystemTestTableMetrics systemTestTableMetrics = new SystemTestTableMetrics(driver);

    // Act
    SystemTestTableMetrics actualGenerateResult = systemTestTableMetrics.generate();

    // Assert
    verify(driver).generateTableMetrics();
    assertSame(systemTestTableMetrics, actualGenerateResult);
  }

  /**
   * Test {@link SystemTestTableMetrics#get()}.
   * <p>
   * Method under test: {@link SystemTestTableMetrics#get()}
   */
  @Test
  @DisplayName("Test get()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"TableMetrics SystemTestTableMetrics.get()"})
  void testGet() {
    // Arrange
    TableMetricsDriver driver = mock(TableMetricsDriver.class);
    TableMetrics buildResult = TableMetrics.builder()
        .averageFileReferencesPerPartition(10.0d)
        .fileCount(3)
        .instanceId("42")
        .leafPartitionCount(3)
        .partitionCount(3)
        .recordCount(3L)
        .tableName("Table Name")
        .build();
    when(driver.getTableMetrics()).thenReturn(buildResult);

    // Act
    TableMetrics actualGetResult = (new SystemTestTableMetrics(driver)).get();

    // Assert
    verify(driver).getTableMetrics();
    assertEquals("42", actualGetResult.getInstanceId());
    assertEquals("Table Name", actualGetResult.getTableName());
    assertEquals(10.0d, actualGetResult.getAverageFileReferencesPerPartition());
    assertEquals(3, actualGetResult.getFileCount());
    assertEquals(3, actualGetResult.getLeafPartitionCount());
    assertEquals(3, actualGetResult.getPartitionCount());
    assertEquals(3L, actualGetResult.getRecordCount());
  }
}
