package sleeper.systemtest.dsl.gc;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.Instant;
import java.util.ArrayList;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.properties.table.TableProperties;
import sleeper.core.properties.table.TableProperty;
import sleeper.core.statestore.StateStore;
import sleeper.core.statestore.StateStoreException;
import sleeper.core.util.PollWithRetries;
import sleeper.core.util.ThreadSleep;
import sleeper.systemtest.dsl.instance.SystemTestInstanceContext;

class WaitForGCDiffblueTest {
  /**
   * Test {@link WaitForGC#waitUntilNoUnreferencedFiles(SystemTestInstanceContext, PollWithRetries)}.
   * <ul>
   *   <li>Then calls {@link TableProperties#get(TableProperty)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WaitForGC#waitUntilNoUnreferencedFiles(SystemTestInstanceContext, PollWithRetries)}
   */
  @Test
  @DisplayName("Test waitUntilNoUnreferencedFiles(SystemTestInstanceContext, PollWithRetries); then calls get(TableProperty)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void WaitForGC.waitUntilNoUnreferencedFiles(SystemTestInstanceContext, PollWithRetries)"})
  void testWaitUntilNoUnreferencedFiles_thenCallsGet() throws StateStoreException {
    // Arrange
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.get(Mockito.<TableProperty>any())).thenReturn("Get");

    ArrayList<TableProperties> tablePropertiesList = new ArrayList<>();
    tablePropertiesList.add(tableProperties);
    Stream<TableProperties> streamResult = tablePropertiesList.stream();
    StateStore stateStore = mock(StateStore.class);

    ArrayList<String> stringList = new ArrayList<>();
    Stream<String> streamResult2 = stringList.stream();
    when(stateStore.getReadyForGCFilenamesBefore(Mockito.<Instant>any())).thenReturn(streamResult2);
    SystemTestInstanceContext instance = mock(SystemTestInstanceContext.class);
    when(instance.getStateStore(Mockito.<TableProperties>any())).thenReturn(stateStore);
    when(instance.streamTableProperties()).thenReturn(streamResult);
    PollWithRetries poll = PollWithRetries.builder()
        .maxRetries(3)
        .pollIntervalMillis(42L)
        .sleepInInterval(mock(ThreadSleep.class))
        .build();

    // Act
    WaitForGC.waitUntilNoUnreferencedFiles(instance, poll);

    // Assert
    verify(tableProperties, atLeast(1)).get(isA(TableProperty.class));
    verify(stateStore).getReadyForGCFilenamesBefore(isA(Instant.class));
    verify(instance).getStateStore(isA(TableProperties.class));
    verify(instance).streamTableProperties();
  }

  /**
   * Test {@link WaitForGC#waitUntilNoUnreferencedFiles(SystemTestInstanceContext, PollWithRetries)}.
   * <ul>
   *   <li>Then throw {@link RuntimeException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WaitForGC#waitUntilNoUnreferencedFiles(SystemTestInstanceContext, PollWithRetries)}
   */
  @Test
  @DisplayName("Test waitUntilNoUnreferencedFiles(SystemTestInstanceContext, PollWithRetries); then throw RuntimeException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void WaitForGC.waitUntilNoUnreferencedFiles(SystemTestInstanceContext, PollWithRetries)"})
  void testWaitUntilNoUnreferencedFiles_thenThrowRuntimeException() throws StateStoreException {
    // Arrange
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.get(Mockito.<TableProperty>any())).thenReturn("Get");

    ArrayList<TableProperties> tablePropertiesList = new ArrayList<>();
    tablePropertiesList.add(tableProperties);
    Stream<TableProperties> streamResult = tablePropertiesList.stream();
    StateStore stateStore = mock(StateStore.class);
    when(stateStore.getReadyForGCFilenamesBefore(Mockito.<Instant>any()))
        .thenThrow(new RuntimeException("no unreferenced files are present"));
    SystemTestInstanceContext instance = mock(SystemTestInstanceContext.class);
    when(instance.getStateStore(Mockito.<TableProperties>any())).thenReturn(stateStore);
    when(instance.streamTableProperties()).thenReturn(streamResult);
    PollWithRetries poll = PollWithRetries.builder()
        .maxRetries(3)
        .pollIntervalMillis(42L)
        .sleepInInterval(mock(ThreadSleep.class))
        .build();

    // Act and Assert
    assertThrows(RuntimeException.class, () -> WaitForGC.waitUntilNoUnreferencedFiles(instance, poll));
    verify(tableProperties).get(isA(TableProperty.class));
    verify(stateStore).getReadyForGCFilenamesBefore(isA(Instant.class));
    verify(instance).getStateStore(isA(TableProperties.class));
    verify(instance).streamTableProperties();
  }
}
