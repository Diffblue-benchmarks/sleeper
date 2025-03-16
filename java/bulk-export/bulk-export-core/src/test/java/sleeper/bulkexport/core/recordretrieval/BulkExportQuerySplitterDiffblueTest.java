package sleeper.bulkexport.core.recordretrieval;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.Instant;
import java.util.function.Supplier;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.table.TableProperties;
import sleeper.core.statestore.StateStore;
import sleeper.core.statestore.StateStoreException;

class BulkExportQuerySplitterDiffblueTest {
  /**
   * Test {@link BulkExportQuerySplitter#BulkExportQuerySplitter(TableProperties, StateStore)}.
   * <ul>
   *   <li>Then throw {@link StateStoreException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BulkExportQuerySplitter#BulkExportQuerySplitter(TableProperties, StateStore)}
   */
  @Test
  @DisplayName("Test new BulkExportQuerySplitter(TableProperties, StateStore); then throw StateStoreException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void BulkExportQuerySplitter.<init>(TableProperties, StateStore)"})
  void testNewBulkExportQuerySplitter_thenThrowStateStoreException() throws StateStoreException {
    // Arrange
    TableProperties tableProperties = new TableProperties(new InstanceProperties());
    StateStore stateStore = mock(StateStore.class);
    when(stateStore.getAllPartitions()).thenThrow(new StateStoreException("An error occurred"));

    // Act and Assert
    assertThrows(StateStoreException.class, () -> new BulkExportQuerySplitter(tableProperties, stateStore));

    verify(stateStore).getAllPartitions();
  }

  /**
   * Test {@link BulkExportQuerySplitter#BulkExportQuerySplitter(StateStore, TableProperties, Supplier, Supplier)}.
   * <ul>
   *   <li>Then throw {@link StateStoreException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BulkExportQuerySplitter#BulkExportQuerySplitter(StateStore, TableProperties, Supplier, Supplier)}
   */
  @Test
  @DisplayName("Test new BulkExportQuerySplitter(StateStore, TableProperties, Supplier, Supplier); then throw StateStoreException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void BulkExportQuerySplitter.<init>(StateStore, TableProperties, Supplier, Supplier)"})
  void testNewBulkExportQuerySplitter_thenThrowStateStoreException2() {
    // Arrange
    StateStore stateStore = mock(StateStore.class);
    TableProperties tableProperties = new TableProperties(new InstanceProperties());
    Supplier<String> idSupplier = mock(Supplier.class);
    Supplier<Instant> timeSupplier = mock(Supplier.class);
    when(timeSupplier.get()).thenThrow(new StateStoreException("An error occurred"));

    // Act and Assert
    assertThrows(StateStoreException.class,
        () -> new BulkExportQuerySplitter(stateStore, tableProperties, idSupplier, timeSupplier));

    verify(timeSupplier).get();
  }
}
