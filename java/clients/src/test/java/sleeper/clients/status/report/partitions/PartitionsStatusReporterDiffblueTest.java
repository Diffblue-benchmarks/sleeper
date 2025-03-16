package sleeper.clients.status.report.partitions;

import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.partition.Partition;
import sleeper.core.partition.Partition.Builder;
import sleeper.core.range.Region;
import sleeper.core.schema.Field;
import sleeper.core.schema.type.ByteArrayType;
import sleeper.splitter.core.status.PartitionStatus;
import sleeper.splitter.core.status.PartitionsStatus;

class PartitionsStatusReporterDiffblueTest {
  /**
   * Test {@link PartitionsStatusReporter#report(PartitionsStatus)}.
   * <ul>
   *   <li>Given {@link PartitionStatus} {@link PartitionStatus#willBeSplit()} return {@code true}.</li>
   *   <li>Then calls {@link PartitionStatus#getApproxRecords()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PartitionsStatusReporter#report(PartitionsStatus)}
   */
  @Test
  @DisplayName("Test report(PartitionsStatus); given PartitionStatus willBeSplit() return 'true'; then calls getApproxRecords()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void PartitionsStatusReporter.report(PartitionsStatus)"})
  void testReport_givenPartitionStatusWillBeSplitReturnTrue_thenCallsGetApproxRecords() {
    // Arrange
    PartitionsStatusReporter partitionsStatusReporter = new PartitionsStatusReporter(
        new PrintStream(new ByteArrayOutputStream(1)));
    PartitionStatus partitionStatus = mock(PartitionStatus.class);
    when(partitionStatus.willBeSplit()).thenReturn(true);
    when(partitionStatus.getNumberOfFiles()).thenReturn(10);
    when(partitionStatus.getNumberOfFilesOnJobs()).thenReturn(10);
    when(partitionStatus.getIndexInParent()).thenReturn(1);
    when(partitionStatus.getSplitValue()).thenReturn("Split Value");
    when(partitionStatus.getApproxRecords()).thenReturn(1L);
    when(partitionStatus.getApproxRecordsReferenced()).thenReturn(1L);
    when(partitionStatus.getExactRecordsReferenced()).thenReturn(1L);
    when(partitionStatus.getSplitField()).thenReturn(new Field("Name", new ByteArrayType()));
    when(partitionStatus.isLeafPartition()).thenReturn(true);
    Builder builderResult = Partition.builder();
    Builder parentPartitionIdResult = builderResult.childPartitionIds(new ArrayList<>())
        .dimension(1)
        .id("42")
        .leafPartition(true)
        .parentPartitionId("42");
    Partition buildResult = parentPartitionIdResult.region(new Region(new ArrayList<>())).build();
    when(partitionStatus.getPartition()).thenReturn(buildResult);

    ArrayList<PartitionStatus> partitions = new ArrayList<>();
    partitions.add(partitionStatus);

    // Act
    partitionsStatusReporter.report(new PartitionsStatus(partitions, 1L));

    // Assert
    verify(partitionStatus).getApproxRecords();
    verify(partitionStatus).getApproxRecordsReferenced();
    verify(partitionStatus).getExactRecordsReferenced();
    verify(partitionStatus).getIndexInParent();
    verify(partitionStatus).getNumberOfFiles();
    verify(partitionStatus).getNumberOfFilesOnJobs();
    verify(partitionStatus).getPartition();
    verify(partitionStatus).getSplitField();
    verify(partitionStatus).getSplitValue();
    verify(partitionStatus, atLeast(1)).isLeafPartition();
    verify(partitionStatus, atLeast(1)).willBeSplit();
  }

  /**
   * Test {@link PartitionsStatusReporter#report(PartitionsStatus)}.
   * <ul>
   *   <li>Given {@link PartitionStatus} {@link PartitionStatus#willBeSplit()} return {@code true}.</li>
   *   <li>Then calls {@link PartitionStatus#getApproxRecords()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PartitionsStatusReporter#report(PartitionsStatus)}
   */
  @Test
  @DisplayName("Test report(PartitionsStatus); given PartitionStatus willBeSplit() return 'true'; then calls getApproxRecords()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void PartitionsStatusReporter.report(PartitionsStatus)"})
  void testReport_givenPartitionStatusWillBeSplitReturnTrue_thenCallsGetApproxRecords2() {
    // Arrange
    PartitionsStatusReporter partitionsStatusReporter = new PartitionsStatusReporter(
        new PrintStream(new ByteArrayOutputStream(1)));
    PartitionStatus partitionStatus = mock(PartitionStatus.class);
    when(partitionStatus.willBeSplit()).thenReturn(true);
    when(partitionStatus.getNumberOfFiles()).thenReturn(10);
    when(partitionStatus.getNumberOfFilesOnJobs()).thenReturn(10);
    when(partitionStatus.getIndexInParent()).thenReturn(1);
    when(partitionStatus.getSplitValue()).thenReturn("Split Value");
    when(partitionStatus.getApproxRecords()).thenReturn(1L);
    when(partitionStatus.getApproxRecordsReferenced()).thenReturn(1L);
    when(partitionStatus.getExactRecordsReferenced()).thenReturn(1L);
    when(partitionStatus.getSplitField()).thenReturn(new Field("Name", new ByteArrayType()));
    when(partitionStatus.isLeafPartition()).thenReturn(true);
    Builder builderResult = Partition.builder();
    Builder parentPartitionIdResult = builderResult.childPartitionIds(new ArrayList<>())
        .dimension(1)
        .id("42")
        .leafPartition(true)
        .parentPartitionId("42");
    Partition buildResult = parentPartitionIdResult.region(new Region(new ArrayList<>())).build();
    when(partitionStatus.getPartition()).thenReturn(buildResult);
    PartitionStatus partitionStatus2 = mock(PartitionStatus.class);
    when(partitionStatus2.willBeSplit()).thenReturn(true);
    when(partitionStatus2.getNumberOfFiles()).thenReturn(10);
    when(partitionStatus2.getNumberOfFilesOnJobs()).thenReturn(10);
    when(partitionStatus2.getIndexInParent()).thenReturn(1);
    when(partitionStatus2.getSplitValue()).thenReturn("Split Value");
    when(partitionStatus2.getApproxRecords()).thenReturn(1L);
    when(partitionStatus2.getApproxRecordsReferenced()).thenReturn(1L);
    when(partitionStatus2.getExactRecordsReferenced()).thenReturn(1L);
    when(partitionStatus2.getSplitField()).thenReturn(new Field("Name", new ByteArrayType()));
    when(partitionStatus2.isLeafPartition()).thenReturn(true);
    Builder builderResult2 = Partition.builder();
    Builder parentPartitionIdResult2 = builderResult2.childPartitionIds(new ArrayList<>())
        .dimension(1)
        .id("42")
        .leafPartition(true)
        .parentPartitionId("42");
    Partition buildResult2 = parentPartitionIdResult2.region(new Region(new ArrayList<>())).build();
    when(partitionStatus2.getPartition()).thenReturn(buildResult2);

    ArrayList<PartitionStatus> partitions = new ArrayList<>();
    partitions.add(partitionStatus2);
    partitions.add(partitionStatus);

    // Act
    partitionsStatusReporter.report(new PartitionsStatus(partitions, 1L));

    // Assert
    verify(partitionStatus2).getApproxRecords();
    verify(partitionStatus).getApproxRecords();
    verify(partitionStatus2).getApproxRecordsReferenced();
    verify(partitionStatus).getApproxRecordsReferenced();
    verify(partitionStatus2).getExactRecordsReferenced();
    verify(partitionStatus).getExactRecordsReferenced();
    verify(partitionStatus2).getIndexInParent();
    verify(partitionStatus).getIndexInParent();
    verify(partitionStatus2).getNumberOfFiles();
    verify(partitionStatus).getNumberOfFiles();
    verify(partitionStatus2).getNumberOfFilesOnJobs();
    verify(partitionStatus).getNumberOfFilesOnJobs();
    verify(partitionStatus2).getPartition();
    verify(partitionStatus).getPartition();
    verify(partitionStatus2).getSplitField();
    verify(partitionStatus).getSplitField();
    verify(partitionStatus2).getSplitValue();
    verify(partitionStatus).getSplitValue();
    verify(partitionStatus2, atLeast(1)).isLeafPartition();
    verify(partitionStatus, atLeast(1)).isLeafPartition();
    verify(partitionStatus2, atLeast(1)).willBeSplit();
    verify(partitionStatus, atLeast(1)).willBeSplit();
  }
}
