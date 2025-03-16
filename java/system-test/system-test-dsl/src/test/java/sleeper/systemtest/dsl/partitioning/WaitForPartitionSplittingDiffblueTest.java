package sleeper.systemtest.dsl.partitioning;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.function.BooleanSupplier;
import java.util.function.Function;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.table.TableProperties;
import sleeper.core.statestore.StateStore;
import sleeper.core.statestore.StateStoreException;
import sleeper.core.util.PollWithRetries;
import sleeper.core.util.PollWithRetries.CheckFailedException;
import sleeper.systemtest.dsl.instance.DeployedSleeperInstances;
import sleeper.systemtest.dsl.instance.SleeperInstanceDriver;
import sleeper.systemtest.dsl.instance.SystemTestInstanceContext;
import sleeper.systemtest.dsl.instance.SystemTestParameters;
import sleeper.systemtest.dsl.util.TestContext;

class WaitForPartitionSplittingDiffblueTest {
  /**
   * Test {@link WaitForPartitionSplitting#forCurrentPartitionsNeedingSplitting(SystemTestInstanceContext)} with {@code instance}.
   * <p>
   * Method under test: {@link WaitForPartitionSplitting#forCurrentPartitionsNeedingSplitting(SystemTestInstanceContext)}
   */
  @Test
  @DisplayName("Test forCurrentPartitionsNeedingSplitting(SystemTestInstanceContext) with 'instance'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "WaitForPartitionSplitting WaitForPartitionSplitting.forCurrentPartitionsNeedingSplitting(SystemTestInstanceContext)"})
  void testForCurrentPartitionsNeedingSplittingWithInstance() {
    // Arrange
    SystemTestInstanceContext instance = mock(SystemTestInstanceContext.class);

    ArrayList<TableProperties> tablePropertiesList = new ArrayList<>();
    Stream<TableProperties> streamResult = tablePropertiesList.stream();
    when(instance.streamTableProperties()).thenReturn(streamResult);

    // Act
    WaitForPartitionSplitting.forCurrentPartitionsNeedingSplitting(instance);

    // Assert
    verify(instance).streamTableProperties();
  }

  /**
   * Test {@link WaitForPartitionSplitting#pollUntilFinished(SystemTestInstanceContext, PollWithRetries)}.
   * <ul>
   *   <li>Then throw {@link InterruptedException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WaitForPartitionSplitting#pollUntilFinished(SystemTestInstanceContext, PollWithRetries)}
   */
  @Test
  @DisplayName("Test pollUntilFinished(SystemTestInstanceContext, PollWithRetries); then throw InterruptedException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void WaitForPartitionSplitting.pollUntilFinished(SystemTestInstanceContext, PollWithRetries)"})
  void testPollUntilFinished_thenThrowInterruptedException() throws InterruptedException, CheckFailedException {
    // Arrange
    ArrayList<TableProperties> tablePropertiesList = new ArrayList<>();
    Stream<TableProperties> tablePropertiesStream = tablePropertiesList.stream();
    WaitForPartitionSplitting forCurrentPartitionsNeedingSplittingResult = WaitForPartitionSplitting
        .forCurrentPartitionsNeedingSplitting(tablePropertiesStream, mock(Function.class));
    SystemTestInstanceContext instance = new SystemTestInstanceContext(mock(SystemTestParameters.class),
        mock(DeployedSleeperInstances.class), mock(SleeperInstanceDriver.class), mock(TestContext.class));

    PollWithRetries poll = mock(PollWithRetries.class);
    doThrow(new InterruptedException("Waiting for splits, expecting partitions to be split: {}")).when(poll)
        .pollUntil(Mockito.<String>any(), Mockito.<BooleanSupplier>any());

    // Act and Assert
    assertThrows(InterruptedException.class,
        () -> forCurrentPartitionsNeedingSplittingResult.pollUntilFinished(instance, poll));
    verify(poll).pollUntil(eq("partition splits finished"), isA(BooleanSupplier.class));
  }

  /**
   * Test {@link WaitForPartitionSplitting#pollUntilFinished(SystemTestInstanceContext, PollWithRetries)}.
   * <ul>
   *   <li>When {@link PollWithRetries} {@link PollWithRetries#pollUntil(String, BooleanSupplier)} does nothing.</li>
   *   <li>Then calls {@link PollWithRetries#pollUntil(String, BooleanSupplier)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WaitForPartitionSplitting#pollUntilFinished(SystemTestInstanceContext, PollWithRetries)}
   */
  @Test
  @DisplayName("Test pollUntilFinished(SystemTestInstanceContext, PollWithRetries); when PollWithRetries pollUntil(String, BooleanSupplier) does nothing; then calls pollUntil(String, BooleanSupplier)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void WaitForPartitionSplitting.pollUntilFinished(SystemTestInstanceContext, PollWithRetries)"})
  void testPollUntilFinished_whenPollWithRetriesPollUntilDoesNothing_thenCallsPollUntil()
      throws InterruptedException, CheckFailedException {
    // Arrange
    ArrayList<TableProperties> tablePropertiesList = new ArrayList<>();
    Stream<TableProperties> tablePropertiesStream = tablePropertiesList.stream();
    WaitForPartitionSplitting forCurrentPartitionsNeedingSplittingResult = WaitForPartitionSplitting
        .forCurrentPartitionsNeedingSplitting(tablePropertiesStream, mock(Function.class));
    SystemTestInstanceContext instance = new SystemTestInstanceContext(mock(SystemTestParameters.class),
        mock(DeployedSleeperInstances.class), mock(SleeperInstanceDriver.class), mock(TestContext.class));

    PollWithRetries poll = mock(PollWithRetries.class);
    doNothing().when(poll).pollUntil(Mockito.<String>any(), Mockito.<BooleanSupplier>any());

    // Act
    forCurrentPartitionsNeedingSplittingResult.pollUntilFinished(instance, poll);

    // Assert
    verify(poll).pollUntil(eq("partition splits finished"), isA(BooleanSupplier.class));
  }

  /**
   * Test {@link WaitForPartitionSplitting#isSplitFinished(TableProperties, StateStore)} with {@code properties}, {@code stateStore}.
   * <ul>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WaitForPartitionSplitting#isSplitFinished(TableProperties, StateStore)}
   */
  @Test
  @DisplayName("Test isSplitFinished(TableProperties, StateStore) with 'properties', 'stateStore'; then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean WaitForPartitionSplitting.isSplitFinished(TableProperties, StateStore)"})
  void testIsSplitFinishedWithPropertiesStateStore_thenReturnTrue() throws StateStoreException {
    // Arrange
    ArrayList<TableProperties> tablePropertiesList = new ArrayList<>();
    Stream<TableProperties> tablePropertiesStream = tablePropertiesList.stream();
    WaitForPartitionSplitting forCurrentPartitionsNeedingSplittingResult = WaitForPartitionSplitting
        .forCurrentPartitionsNeedingSplitting(tablePropertiesStream, mock(Function.class));
    TableProperties properties = new TableProperties(new InstanceProperties());
    StateStore stateStore = mock(StateStore.class);
    when(stateStore.getLeafPartitions()).thenReturn(new ArrayList<>());

    // Act
    boolean actualIsSplitFinishedResult = forCurrentPartitionsNeedingSplittingResult.isSplitFinished(properties,
        stateStore);

    // Assert
    verify(stateStore).getLeafPartitions();
    assertTrue(actualIsSplitFinishedResult);
  }
}
