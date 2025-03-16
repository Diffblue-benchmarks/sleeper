package sleeper.clients.status.report.filestatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.partition.Partition;
import sleeper.core.partition.Partition.Builder;
import sleeper.core.range.Region;
import sleeper.core.statestore.FilesReportTestHelper;
import sleeper.core.statestore.StateStore;
import sleeper.core.statestore.StateStoreException;

class FileStatusCollectorDiffblueTest {
  /**
   * Test {@link FileStatusCollector#run(int)}.
   * <ul>
   *   <li>Then return LeafPartitionCount is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link FileStatusCollector#run(int)}
   */
  @Test
  @DisplayName("Test run(int); then return LeafPartitionCount is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"TableFilesStatus FileStatusCollector.run(int)"})
  void testRun_thenReturnLeafPartitionCountIsOne() throws StateStoreException {
    // Arrange
    ArrayList<Partition> partitionList = new ArrayList<>();
    Builder builderResult = Partition.builder();
    Builder parentPartitionIdResult = builderResult.childPartitionIds(new ArrayList<>())
        .dimension(1)
        .id("42")
        .leafPartition(true)
        .parentPartitionId("42");
    Partition buildResult = parentPartitionIdResult.region(new Region(new ArrayList<>())).build();
    partitionList.add(buildResult);
    StateStore stateStore = mock(StateStore.class);
    when(stateStore.getAllPartitions()).thenReturn(partitionList);
    when(stateStore.getAllFilesWithMaxUnreferenced(anyInt())).thenReturn(FilesReportTestHelper.noFilesReport());

    // Act
    TableFilesStatus actualRunResult = (new FileStatusCollector(stateStore)).run(3);

    // Assert
    verify(stateStore).getAllFilesWithMaxUnreferenced(eq(3));
    verify(stateStore).getAllPartitions();
    assertEquals(0, actualRunResult.getFileCount());
    assertEquals(0, actualRunResult.getNonLeafPartitionCount());
    assertEquals(0L, actualRunResult.getFileReferenceCount());
    assertEquals(0L, actualRunResult.getReferencesInLeafPartitions());
    assertEquals(0L, actualRunResult.getReferencesInNonLeafPartitions());
    assertEquals(0L, actualRunResult.getTotalRecords());
    assertEquals(0L, actualRunResult.getTotalRecordsApprox());
    assertEquals(0L, actualRunResult.getTotalRecordsInLeafPartitions());
    assertEquals(0L, actualRunResult.getTotalRecordsInLeafPartitionsApprox());
    assertEquals(0L, actualRunResult.getTotalRecordsInNonLeafPartitions());
    assertEquals(0L, actualRunResult.getTotalRecordsInNonLeafPartitionsApprox());
    assertEquals(1, actualRunResult.getLeafPartitionCount());
    assertFalse(actualRunResult.isMoreThanMax());
    assertTrue(actualRunResult.getFilesWithNoReferences().isEmpty());
    assertTrue(actualRunResult.getFilesWithReferences().isEmpty());
  }

  /**
   * Test {@link FileStatusCollector#run(int)}.
   * <ul>
   *   <li>Then return LeafPartitionCount is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link FileStatusCollector#run(int)}
   */
  @Test
  @DisplayName("Test run(int); then return LeafPartitionCount is zero")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"TableFilesStatus FileStatusCollector.run(int)"})
  void testRun_thenReturnLeafPartitionCountIsZero() throws StateStoreException {
    // Arrange
    StateStore stateStore = mock(StateStore.class);
    when(stateStore.getAllPartitions()).thenReturn(new ArrayList<>());
    when(stateStore.getAllFilesWithMaxUnreferenced(anyInt())).thenReturn(FilesReportTestHelper.noFilesReport());

    // Act
    TableFilesStatus actualRunResult = (new FileStatusCollector(stateStore)).run(3);

    // Assert
    verify(stateStore).getAllFilesWithMaxUnreferenced(eq(3));
    verify(stateStore).getAllPartitions();
    assertEquals(0, actualRunResult.getFileCount());
    assertEquals(0, actualRunResult.getLeafPartitionCount());
    assertEquals(0, actualRunResult.getNonLeafPartitionCount());
    assertEquals(0L, actualRunResult.getFileReferenceCount());
    assertEquals(0L, actualRunResult.getReferencesInLeafPartitions());
    assertEquals(0L, actualRunResult.getReferencesInNonLeafPartitions());
    assertEquals(0L, actualRunResult.getTotalRecords());
    assertEquals(0L, actualRunResult.getTotalRecordsApprox());
    assertEquals(0L, actualRunResult.getTotalRecordsInLeafPartitions());
    assertEquals(0L, actualRunResult.getTotalRecordsInLeafPartitionsApprox());
    assertEquals(0L, actualRunResult.getTotalRecordsInNonLeafPartitions());
    assertEquals(0L, actualRunResult.getTotalRecordsInNonLeafPartitionsApprox());
    assertFalse(actualRunResult.isMoreThanMax());
    assertTrue(actualRunResult.getFilesWithNoReferences().isEmpty());
    assertTrue(actualRunResult.getFilesWithReferences().isEmpty());
  }
}
