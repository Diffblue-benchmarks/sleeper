package sleeper.core.statestore;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.partition.Partition;
import sleeper.core.properties.SleeperPropertyValues;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.instance.InstanceProperty;
import sleeper.core.properties.table.TableProperties;
import sleeper.core.statestore.StateStoreProvider.Factory;
import sleeper.core.statestore.testutils.InMemoryTransactionLogStateStore;
import sleeper.core.statestore.testutils.InMemoryTransactionLogsPerTable;
import sleeper.core.statestore.transactionlog.TransactionLogStateStore;

class StateStoreProviderDiffblueTest {
  /**
   * Test {@link StateStoreProvider#StateStoreProvider(InstanceProperties, Factory)}.
   * <ul>
   *   <li>Given one.</li>
   *   <li>Then calls {@link SleeperPropertyValues#getInt(SleeperProperty)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreProvider#StateStoreProvider(InstanceProperties, Factory)}
   */
  @Test
  @DisplayName("Test new StateStoreProvider(InstanceProperties, Factory); given one; then calls getInt(SleeperProperty)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void StateStoreProvider.<init>(InstanceProperties, Factory)"})
  void testNewStateStoreProvider_givenOne_thenCallsGetInt() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.getInt(Mockito.<InstanceProperty>any())).thenReturn(1);

    // Act
    new StateStoreProvider(instanceProperties, mock(Factory.class));

    // Assert
    verify(instanceProperties).getInt(isA(InstanceProperty.class));
  }

  /**
   * Test {@link StateStoreProvider#getStateStore(TableProperties)}.
   * <ul>
   *   <li>Then return {@link TransactionLogStateStore}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreProvider#getStateStore(TableProperties)}
   */
  @Test
  @DisplayName("Test getStateStore(TableProperties); then return TransactionLogStateStore")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"StateStore StateStoreProvider.getStateStore(TableProperties)"})
  void testGetStateStore_thenReturnTransactionLogStateStore() throws StateStoreException {
    // Arrange
    InstanceProperties instanceProperties = new InstanceProperties();
    StateStoreProvider createProviderResult = InMemoryTransactionLogStateStore.createProvider(instanceProperties,
        new InMemoryTransactionLogsPerTable());

    // Act
    StateStore actualStateStore = createProviderResult.getStateStore(new TableProperties(new InstanceProperties()));

    // Assert
    assertTrue(actualStateStore instanceof TransactionLogStateStore);
    List<FileReference> fileReferences = actualStateStore.getFileReferences();
    assertTrue(fileReferences.isEmpty());
    List<Partition> allPartitions = actualStateStore.getAllPartitions();
    assertTrue(allPartitions.isEmpty());
    assertTrue(actualStateStore.getPartitionToReferencedFilesMap().isEmpty());
    assertTrue(actualStateStore.hasNoFiles());
    assertSame(fileReferences, actualStateStore.getFileReferencesWithNoJobId());
    assertSame(allPartitions, actualStateStore.getLeafPartitions());
  }
}
