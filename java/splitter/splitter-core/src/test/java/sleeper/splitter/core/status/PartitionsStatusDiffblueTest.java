package sleeper.splitter.core.status;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.table.TableProperties;
import sleeper.core.statestore.StateStore;
import sleeper.core.statestore.StateStoreException;

class PartitionsStatusDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link PartitionsStatus#PartitionsStatus(List, long)}
   *   <li>{@link PartitionsStatus#getPartitions()}
   *   <li>{@link PartitionsStatus#getSplitThreshold()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void PartitionsStatus.<init>(List, long)", "List PartitionsStatus.getPartitions()",
      "long PartitionsStatus.getSplitThreshold()"})
  void testGettersAndSetters() {
    // Arrange
    ArrayList<PartitionStatus> partitions = new ArrayList<>();

    // Act
    PartitionsStatus actualPartitionsStatus = new PartitionsStatus(partitions, 1L);
    List<PartitionStatus> actualPartitions = actualPartitionsStatus.getPartitions();

    // Assert
    assertEquals(1L, actualPartitionsStatus.getSplitThreshold());
    assertTrue(actualPartitions.isEmpty());
    assertSame(partitions, actualPartitions);
  }

  /**
   * Test {@link PartitionsStatus#from(TableProperties, StateStore)}.
   * <ul>
   *   <li>Then return NumPartitions is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link PartitionsStatus#from(TableProperties, StateStore)}
   */
  @Test
  @DisplayName("Test from(TableProperties, StateStore); then return NumPartitions is zero")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"PartitionsStatus PartitionsStatus.from(TableProperties, StateStore)"})
  void testFrom_thenReturnNumPartitionsIsZero() throws StateStoreException {
    // Arrange
    TableProperties tableProperties = new TableProperties(new InstanceProperties());
    StateStore store = mock(StateStore.class);
    when(store.getAllPartitions()).thenReturn(new ArrayList<>());

    // Act
    PartitionsStatus actualFromResult = PartitionsStatus.from(tableProperties, store);

    // Assert
    verify(store).getAllPartitions();
    assertEquals(0, actualFromResult.getNumPartitions());
    assertEquals(0L, actualFromResult.getNumLeafPartitions());
    assertEquals(0L, actualFromResult.getNumLeafPartitionsThatWillBeSplit());
    assertEquals(1000000000L, actualFromResult.getSplitThreshold());
    assertTrue(actualFromResult.getPartitions().isEmpty());
  }

  /**
   * Test {@link PartitionsStatus#getNumPartitions()}.
   * <p>
   * Method under test: {@link PartitionsStatus#getNumPartitions()}
   */
  @Test
  @DisplayName("Test getNumPartitions()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"int PartitionsStatus.getNumPartitions()"})
  void testGetNumPartitions() throws StateStoreException {
    // Arrange
    StateStore store = mock(StateStore.class);
    when(store.getAllPartitions()).thenReturn(new ArrayList<>());

    // Act
    int actualNumPartitions = PartitionsStatus.from(new TableProperties(new InstanceProperties()), store)
        .getNumPartitions();

    // Assert
    verify(store).getAllPartitions();
    assertEquals(0, actualNumPartitions);
  }

  /**
   * Test {@link PartitionsStatus#getNumLeafPartitions()}.
   * <p>
   * Method under test: {@link PartitionsStatus#getNumLeafPartitions()}
   */
  @Test
  @DisplayName("Test getNumLeafPartitions()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"long PartitionsStatus.getNumLeafPartitions()"})
  void testGetNumLeafPartitions() throws StateStoreException {
    // Arrange
    StateStore store = mock(StateStore.class);
    when(store.getAllPartitions()).thenReturn(new ArrayList<>());

    // Act
    long actualNumLeafPartitions = PartitionsStatus.from(new TableProperties(new InstanceProperties()), store)
        .getNumLeafPartitions();

    // Assert
    verify(store).getAllPartitions();
    assertEquals(0L, actualNumLeafPartitions);
  }

  /**
   * Test {@link PartitionsStatus#getNumLeafPartitionsThatWillBeSplit()}.
   * <p>
   * Method under test: {@link PartitionsStatus#getNumLeafPartitionsThatWillBeSplit()}
   */
  @Test
  @DisplayName("Test getNumLeafPartitionsThatWillBeSplit()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"long PartitionsStatus.getNumLeafPartitionsThatWillBeSplit()"})
  void testGetNumLeafPartitionsThatWillBeSplit() throws StateStoreException {
    // Arrange
    StateStore store = mock(StateStore.class);
    when(store.getAllPartitions()).thenReturn(new ArrayList<>());

    // Act
    long actualNumLeafPartitionsThatWillBeSplit = PartitionsStatus
        .from(new TableProperties(new InstanceProperties()), store)
        .getNumLeafPartitionsThatWillBeSplit();

    // Assert
    verify(store).getAllPartitions();
    assertEquals(0L, actualNumLeafPartitionsThatWillBeSplit);
  }
}
