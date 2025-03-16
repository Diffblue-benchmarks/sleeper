package sleeper.bulkimport.core;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.bulkimport.core.job.BulkImportJob;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.table.TableProperties;
import sleeper.core.statestore.StateStore;
import sleeper.core.statestore.StateStoreException;

class CheckLeafPartitionCountDiffblueTest {
  /**
   * Test {@link CheckLeafPartitionCount#hasMinimumPartitions(TableProperties, StateStore, BulkImportJob)}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CheckLeafPartitionCount#hasMinimumPartitions(TableProperties, StateStore, BulkImportJob)}
   */
  @Test
  @DisplayName("Test hasMinimumPartitions(TableProperties, StateStore, BulkImportJob); then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "boolean CheckLeafPartitionCount.hasMinimumPartitions(TableProperties, StateStore, BulkImportJob)"})
  void testHasMinimumPartitions_thenReturnFalse() throws StateStoreException {
    // Arrange
    TableProperties tableProperties = new TableProperties(new InstanceProperties());
    StateStore stateStore = mock(StateStore.class);
    when(stateStore.getLeafPartitions()).thenReturn(new ArrayList<>());
    BulkImportJob job = mock(BulkImportJob.class);
    when(job.getId()).thenReturn("42");

    // Act
    boolean actualHasMinimumPartitionsResult = CheckLeafPartitionCount.hasMinimumPartitions(tableProperties, stateStore,
        job);

    // Assert
    verify(job).getId();
    verify(stateStore).getLeafPartitions();
    assertFalse(actualHasMinimumPartitionsResult);
  }
}
