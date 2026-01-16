package sleeper.compaction.job.execution;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import org.apache.hadoop.conf.Configuration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.compaction.core.job.CompactionJob;
import sleeper.compaction.core.job.CompactionJob.Builder;
import sleeper.compaction.core.job.CompactionRunner;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.table.TableProperties;
import sleeper.core.util.ObjectFactory;

class DefaultCompactionRunnerFactoryDiffblueTest {
  /**
   * Test {@link DefaultCompactionRunnerFactory#createCompactor(CompactionJob, TableProperties)}.
   *
   * <ul>
   *   <li>Given {@code 42}.
   *   <li>When {@link CompactionJob} {@link CompactionJob#getId()} return {@code 42}.
   *   <li>Then calls {@link CompactionJob#getId()}.
   * </ul>
   *
   * <p>Method under test: {@link DefaultCompactionRunnerFactory#createCompactor(CompactionJob,
   * TableProperties)}
   */
  @Test
  @DisplayName(
      "Test createCompactor(CompactionJob, TableProperties); given '42'; when CompactionJob getId() return '42'; then calls getId()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "CompactionRunner DefaultCompactionRunnerFactory.createCompactor(CompactionJob, TableProperties)"
  })
  void testCreateCompactor_given42_whenCompactionJobGetIdReturn42_thenCallsGetId() {
    // Arrange
    ObjectFactory objectFactory = ObjectFactory.noUserJars();
    DefaultCompactionRunnerFactory defaultCompactionRunnerFactory =
        new DefaultCompactionRunnerFactory(objectFactory, new Configuration());

    CompactionJob job = mock(CompactionJob.class);
    when(job.getId()).thenReturn("42");
    when(job.getIteratorClassName()).thenReturn("Iterator Class Name");
    when(job.getTableId()).thenReturn("42");

    // Act
    CompactionRunner actualCreateCompactorResult =
        defaultCompactionRunnerFactory.createCompactor(
            job, new TableProperties(new InstanceProperties()));

    // Assert
    verify(job).getId();
    verify(job).getIteratorClassName();
    verify(job).getTableId();
    assertTrue(actualCreateCompactorResult instanceof JavaCompactionRunner);
    assertFalse(actualCreateCompactorResult.isHardwareAccelerated());
  }

  /**
   * Test {@link DefaultCompactionRunnerFactory#createCompactor(CompactionJob, TableProperties)}.
   *
   * <ul>
   *   <li>Then return {@link JavaCompactionRunner}.
   * </ul>
   *
   * <p>Method under test: {@link DefaultCompactionRunnerFactory#createCompactor(CompactionJob,
   * TableProperties)}
   */
  @Test
  @DisplayName(
      "Test createCompactor(CompactionJob, TableProperties); then return JavaCompactionRunner")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "CompactionRunner DefaultCompactionRunnerFactory.createCompactor(CompactionJob, TableProperties)"
  })
  void testCreateCompactor_thenReturnJavaCompactionRunner() {
    // Arrange
    ObjectFactory objectFactory = ObjectFactory.noUserJars();
    DefaultCompactionRunnerFactory defaultCompactionRunnerFactory =
        new DefaultCompactionRunnerFactory(objectFactory, new Configuration());

    Builder builderResult = CompactionJob.builder();
    CompactionJob job =
        builderResult
            .inputFiles(new ArrayList<>())
            .jobId("42")
            .outputFile("Output File")
            .partitionId("42")
            .tableId("42")
            .build();

    // Act
    CompactionRunner actualCreateCompactorResult =
        defaultCompactionRunnerFactory.createCompactor(
            job, new TableProperties(new InstanceProperties()));

    // Assert
    assertTrue(actualCreateCompactorResult instanceof JavaCompactionRunner);
    assertFalse(actualCreateCompactorResult.isHardwareAccelerated());
  }
}
