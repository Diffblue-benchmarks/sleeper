package sleeper.garbagecollector;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.apache.hadoop.conf.Configuration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.properties.SleeperPropertyValues;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.instance.InstanceProperty;
import sleeper.core.properties.table.TableProperties;
import sleeper.core.properties.table.TableProperty;
import sleeper.core.statestore.FileReferenceStore;
import sleeper.core.statestore.FileReferenceStoreQueries;
import sleeper.core.statestore.StateStore;
import sleeper.core.statestore.StateStoreException;
import sleeper.core.statestore.StateStoreProvider;
import sleeper.core.statestore.StateStoreProvider.Factory;
import sleeper.core.statestore.commit.StateStoreCommitRequest;
import sleeper.core.statestore.commit.StateStoreCommitRequestSender;
import sleeper.core.statestore.testutils.FixedStateStoreProvider;
import sleeper.core.statestore.transactionlog.AddTransactionRequest;
import sleeper.core.table.TableStatus;
import sleeper.garbagecollector.GarbageCollector.DeleteFile;

class GarbageCollectorDiffblueTest {
  /**
   * Test {@link GarbageCollector#run(List)}.
   * <p>
   * Method under test: {@link GarbageCollector#run(List)}
   */
  @Test
  @DisplayName("Test run(List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void GarbageCollector.run(List)"})
  void testRun() throws StateStoreException, FailedGarbageCollectionException {
    // Arrange
    StateStore stateStore = mock(StateStore.class);

    ArrayList<String> stringList = new ArrayList<>();
    Stream<String> streamResult = stringList.stream();
    when(stateStore.getReadyForGCFilenamesBefore(Mockito.<Instant>any())).thenReturn(streamResult);
    Factory stateStoreFactory = mock(Factory.class);
    when(stateStoreFactory.getStateStore(Mockito.<TableProperties>any())).thenReturn(stateStore);
    StateStoreProvider stateStoreProvider = new StateStoreProvider(3, stateStoreFactory);

    DeleteFile deleteFile = mock(DeleteFile.class);
    GarbageCollector garbageCollector = new GarbageCollector(deleteFile, new InstanceProperties(), stateStoreProvider,
        mock(StateStoreCommitRequestSender.class));

    ArrayList<TableProperties> tables = new ArrayList<>();
    tables.add(new TableProperties(new InstanceProperties()));

    // Act
    garbageCollector.run(tables);

    // Assert
    verify(stateStore).getReadyForGCFilenamesBefore(isA(Instant.class));
    verify(stateStoreFactory).getStateStore(isA(TableProperties.class));
  }

  /**
   * Test {@link GarbageCollector#run(List)}.
   * <p>
   * Method under test: {@link GarbageCollector#run(List)}
   */
  @Test
  @DisplayName("Test run(List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void GarbageCollector.run(List)"})
  void testRun2() throws StateStoreException, FailedGarbageCollectionException {
    // Arrange
    StateStore stateStore = mock(StateStore.class);
    when(stateStore.getReadyForGCFilenamesBefore(Mockito.<Instant>any()))
        .thenThrow(new FailedGarbageCollectionException(new ArrayList<>()));
    Factory stateStoreFactory = mock(Factory.class);
    when(stateStoreFactory.getStateStore(Mockito.<TableProperties>any())).thenReturn(stateStore);
    StateStoreProvider stateStoreProvider = new StateStoreProvider(3, stateStoreFactory);

    DeleteFile deleteFile = mock(DeleteFile.class);
    GarbageCollector garbageCollector = new GarbageCollector(deleteFile, new InstanceProperties(), stateStoreProvider,
        mock(StateStoreCommitRequestSender.class));

    ArrayList<TableProperties> tables = new ArrayList<>();
    tables.add(new TableProperties(new InstanceProperties()));

    // Act and Assert
    assertThrows(FailedGarbageCollectionException.class, () -> garbageCollector.run(tables));
    verify(stateStore).getReadyForGCFilenamesBefore(isA(Instant.class));
    verify(stateStoreFactory).getStateStore(isA(TableProperties.class));
  }

  /**
   * Test {@link GarbageCollector#run(List)}.
   * <p>
   * Method under test: {@link GarbageCollector#run(List)}
   */
  @Test
  @DisplayName("Test run(List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void GarbageCollector.run(List)"})
  void testRun3() throws StateStoreException, FailedGarbageCollectionException {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.getInt(Mockito.<InstanceProperty>any())).thenReturn(1);
    StateStore stateStore = mock(StateStore.class);

    ArrayList<String> stringList = new ArrayList<>();
    Stream<String> streamResult = stringList.stream();
    when(stateStore.getReadyForGCFilenamesBefore(Mockito.<Instant>any())).thenReturn(streamResult);
    Factory stateStoreFactory = mock(Factory.class);
    when(stateStoreFactory.getStateStore(Mockito.<TableProperties>any())).thenReturn(stateStore);
    GarbageCollector garbageCollector = new GarbageCollector(mock(DeleteFile.class), instanceProperties,
        new StateStoreProvider(3, stateStoreFactory), mock(StateStoreCommitRequestSender.class));

    ArrayList<TableProperties> tables = new ArrayList<>();
    tables.add(new TableProperties(new InstanceProperties()));

    // Act
    garbageCollector.run(tables);

    // Assert
    verify(instanceProperties).getInt(isA(InstanceProperty.class));
    verify(stateStore).getReadyForGCFilenamesBefore(isA(Instant.class));
    verify(stateStoreFactory).getStateStore(isA(TableProperties.class));
  }

  /**
   * Test {@link GarbageCollector#run(List)}.
   * <p>
   * Method under test: {@link GarbageCollector#run(List)}
   */
  @Test
  @DisplayName("Test run(List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void GarbageCollector.run(List)"})
  void testRun4() throws IOException, StateStoreException, FailedGarbageCollectionException {
    // Arrange
    DeleteFile deleteFile = mock(DeleteFile.class);
    doNothing().when(deleteFile).deleteFileAndSketches(Mockito.<String>any());
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.getInt(Mockito.<InstanceProperty>any())).thenReturn(1);

    ArrayList<String> stringList = new ArrayList<>();
    stringList.add("Obtained list of {} tables");
    Stream<String> streamResult = stringList.stream();
    StateStore stateStore = mock(StateStore.class);
    when(stateStore.getReadyForGCFilenamesBefore(Mockito.<Instant>any())).thenReturn(streamResult);
    Factory stateStoreFactory = mock(Factory.class);
    when(stateStoreFactory.getStateStore(Mockito.<TableProperties>any())).thenReturn(stateStore);
    StateStoreProvider stateStoreProvider = new StateStoreProvider(3, stateStoreFactory);

    StateStoreCommitRequestSender sendAsyncCommit = mock(StateStoreCommitRequestSender.class);
    doThrow(new FailedGarbageCollectionException(new ArrayList<>())).when(sendAsyncCommit)
        .send(Mockito.<StateStoreCommitRequest>any());
    GarbageCollector garbageCollector = new GarbageCollector(deleteFile, instanceProperties, stateStoreProvider,
        sendAsyncCommit);

    ArrayList<TableProperties> tables = new ArrayList<>();
    tables.add(new TableProperties(new InstanceProperties()));

    // Act and Assert
    assertThrows(FailedGarbageCollectionException.class, () -> garbageCollector.run(tables));
    verify(instanceProperties).getInt(isA(InstanceProperty.class));
    verify(stateStore).getReadyForGCFilenamesBefore(isA(Instant.class));
    verify(stateStoreFactory).getStateStore(isA(TableProperties.class));
    verify(sendAsyncCommit).send(isA(StateStoreCommitRequest.class));
    verify(deleteFile).deleteFileAndSketches(eq("Obtained list of {} tables"));
  }

  /**
   * Test {@link GarbageCollector#run(List)}.
   * <p>
   * Method under test: {@link GarbageCollector#run(List)}
   */
  @Test
  @DisplayName("Test run(List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void GarbageCollector.run(List)"})
  void testRun5() throws FailedGarbageCollectionException {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.getInt(Mockito.<InstanceProperty>any())).thenReturn(1);
    Factory stateStoreFactory = mock(Factory.class);
    when(stateStoreFactory.getStateStore(Mockito.<TableProperties>any()))
        .thenThrow(new FailedGarbageCollectionException(new ArrayList<>()));
    GarbageCollector garbageCollector = new GarbageCollector(mock(DeleteFile.class), instanceProperties,
        new StateStoreProvider(new InstanceProperties(), stateStoreFactory), mock(StateStoreCommitRequestSender.class));

    ArrayList<TableProperties> tables = new ArrayList<>();
    tables.add(new TableProperties(new InstanceProperties()));

    // Act and Assert
    assertThrows(FailedGarbageCollectionException.class, () -> garbageCollector.run(tables));
    verify(instanceProperties).getInt(isA(InstanceProperty.class));
    verify(stateStoreFactory).getStateStore(isA(TableProperties.class));
  }

  /**
   * Test {@link GarbageCollector#run(List)}.
   * <p>
   * Method under test: {@link GarbageCollector#run(List)}
   */
  @Test
  @DisplayName("Test run(List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void GarbageCollector.run(List)"})
  void testRun6() throws StateStoreException, FailedGarbageCollectionException {
    // Arrange
    new FailedGarbageCollectionException(new ArrayList<>());
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.getInt(Mockito.<InstanceProperty>any())).thenReturn(1);
    StateStore stateStore = mock(StateStore.class);

    ArrayList<String> stringList = new ArrayList<>();
    Stream<String> streamResult = stringList.stream();
    when(stateStore.getReadyForGCFilenamesBefore(Mockito.<Instant>any())).thenReturn(streamResult);
    StateStoreProvider stateStoreProvider = mock(StateStoreProvider.class);
    when(stateStoreProvider.getStateStore(Mockito.<TableProperties>any())).thenReturn(stateStore);
    GarbageCollector garbageCollector = new GarbageCollector(mock(DeleteFile.class), instanceProperties,
        stateStoreProvider, mock(StateStoreCommitRequestSender.class));
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.getInt(Mockito.<TableProperty>any())).thenReturn(1);
    when(tableProperties.getStatus()).thenReturn(TableStatus.uniqueIdAndName("42", "Table Name", true));

    ArrayList<TableProperties> tables = new ArrayList<>();
    tables.add(tableProperties);

    // Act
    garbageCollector.run(tables);

    // Assert
    verify(instanceProperties).getInt(isA(InstanceProperty.class));
    verify(tableProperties).getInt(isA(TableProperty.class));
    verify(tableProperties).getStatus();
    verify(stateStore).getReadyForGCFilenamesBefore(isA(Instant.class));
    verify(stateStoreProvider).getStateStore(isA(TableProperties.class));
  }

  /**
   * Test {@link GarbageCollector#run(List)}.
   * <p>
   * Method under test: {@link GarbageCollector#run(List)}
   */
  @Test
  @DisplayName("Test run(List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void GarbageCollector.run(List)"})
  void testRun7() throws IOException, StateStoreException, FailedGarbageCollectionException {
    // Arrange
    new FailedGarbageCollectionException(new ArrayList<>());
    DeleteFile deleteFile = mock(DeleteFile.class);
    doNothing().when(deleteFile).deleteFileAndSketches(Mockito.<String>any());
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.getInt(Mockito.<InstanceProperty>any())).thenReturn(1);

    ArrayList<String> stringList = new ArrayList<>();
    stringList.add("Starting GC for table {}");
    Stream<String> streamResult = stringList.stream();
    StateStore stateStore = mock(StateStore.class);
    when(stateStore.getReadyForGCFilenamesBefore(Mockito.<Instant>any())).thenReturn(streamResult);
    StateStoreProvider stateStoreProvider = mock(StateStoreProvider.class);
    when(stateStoreProvider.getStateStore(Mockito.<TableProperties>any())).thenReturn(stateStore);
    GarbageCollector garbageCollector = new GarbageCollector(deleteFile, instanceProperties, stateStoreProvider,
        mock(StateStoreCommitRequestSender.class));
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.get(Mockito.<TableProperty>any()))
        .thenThrow(new FailedGarbageCollectionException(new ArrayList<>()));
    when(tableProperties.getBoolean(Mockito.<TableProperty>any())).thenReturn(true);
    when(tableProperties.getInt(Mockito.<TableProperty>any())).thenReturn(1);
    when(tableProperties.getStatus()).thenReturn(TableStatus.uniqueIdAndName("42", "Table Name", true));

    ArrayList<TableProperties> tables = new ArrayList<>();
    tables.add(tableProperties);

    // Act and Assert
    assertThrows(FailedGarbageCollectionException.class, () -> garbageCollector.run(tables));
    verify(tableProperties).getBoolean(isA(TableProperty.class));
    verify(instanceProperties).getInt(isA(InstanceProperty.class));
    verify(tableProperties).getInt(isA(TableProperty.class));
    verify(tableProperties).get(isA(TableProperty.class));
    verify(tableProperties).getStatus();
    verify(stateStore).getReadyForGCFilenamesBefore(isA(Instant.class));
    verify(stateStoreProvider).getStateStore(isA(TableProperties.class));
    verify(deleteFile).deleteFileAndSketches(eq("Starting GC for table {}"));
  }

  /**
   * Test {@link GarbageCollector#run(List)}.
   * <p>
   * Method under test: {@link GarbageCollector#run(List)}
   */
  @Test
  @DisplayName("Test run(List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void GarbageCollector.run(List)"})
  void testRun8() throws IOException, StateStoreException, FailedGarbageCollectionException {
    // Arrange
    new FailedGarbageCollectionException(new ArrayList<>());
    DeleteFile deleteFile = mock(DeleteFile.class);
    doNothing().when(deleteFile).deleteFileAndSketches(Mockito.<String>any());
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.getInt(Mockito.<InstanceProperty>any())).thenReturn(1);

    ArrayList<String> stringList = new ArrayList<>();
    stringList.add("Starting GC for table {}");
    Stream<String> streamResult = stringList.stream();
    StateStore stateStore = mock(StateStore.class);
    when(stateStore.getReadyForGCFilenamesBefore(Mockito.<Instant>any())).thenReturn(streamResult);
    StateStoreProvider stateStoreProvider = mock(StateStoreProvider.class);
    when(stateStoreProvider.getStateStore(Mockito.<TableProperties>any())).thenReturn(stateStore);
    StateStoreCommitRequestSender sendAsyncCommit = mock(StateStoreCommitRequestSender.class);
    doNothing().when(sendAsyncCommit).send(Mockito.<StateStoreCommitRequest>any());
    GarbageCollector garbageCollector = new GarbageCollector(deleteFile, instanceProperties, stateStoreProvider,
        sendAsyncCommit);
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.get(Mockito.<TableProperty>any())).thenReturn("Get");
    when(tableProperties.getBoolean(Mockito.<TableProperty>any())).thenReturn(true);
    when(tableProperties.getInt(Mockito.<TableProperty>any())).thenReturn(1);
    when(tableProperties.getStatus()).thenReturn(TableStatus.uniqueIdAndName("42", "Table Name", false));

    ArrayList<TableProperties> tables = new ArrayList<>();
    tables.add(tableProperties);

    // Act
    garbageCollector.run(tables);

    // Assert
    verify(tableProperties).getBoolean(isA(TableProperty.class));
    verify(instanceProperties).getInt(isA(InstanceProperty.class));
    verify(tableProperties).getInt(isA(TableProperty.class));
    verify(tableProperties).get(isA(TableProperty.class));
    verify(tableProperties, atLeast(1)).getStatus();
    verify(stateStore).getReadyForGCFilenamesBefore(isA(Instant.class));
    verify(stateStoreProvider).getStateStore(isA(TableProperties.class));
    verify(sendAsyncCommit).send(isA(StateStoreCommitRequest.class));
    verify(deleteFile).deleteFileAndSketches(eq("Starting GC for table {}"));
  }

  /**
   * Test {@link GarbageCollector#run(List)}.
   * <p>
   * Method under test: {@link GarbageCollector#run(List)}
   */
  @Test
  @DisplayName("Test run(List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void GarbageCollector.run(List)"})
  void testRun9() throws IOException, StateStoreException, FailedGarbageCollectionException {
    // Arrange
    new FailedGarbageCollectionException(new ArrayList<>());
    DeleteFile deleteFile = mock(DeleteFile.class);
    doNothing().when(deleteFile).deleteFileAndSketches(Mockito.<String>any());
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.getInt(Mockito.<InstanceProperty>any())).thenReturn(1);

    ArrayList<String> stringList = new ArrayList<>();
    stringList.add("Starting GC for table {}");
    Stream<String> streamResult = stringList.stream();
    StateStore stateStore = mock(StateStore.class);
    when(stateStore.getReadyForGCFilenamesBefore(Mockito.<Instant>any())).thenReturn(streamResult);
    StateStoreProvider stateStoreProvider = mock(StateStoreProvider.class);
    when(stateStoreProvider.getStateStore(Mockito.<TableProperties>any())).thenReturn(stateStore);
    StateStoreCommitRequestSender sendAsyncCommit = mock(StateStoreCommitRequestSender.class);
    doNothing().when(sendAsyncCommit).send(Mockito.<StateStoreCommitRequest>any());
    GarbageCollector garbageCollector = new GarbageCollector(deleteFile, instanceProperties, stateStoreProvider,
        sendAsyncCommit);
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.getInt(Mockito.<TableProperty>any())).thenReturn(1);
    when(tableProperties.getStatus()).thenReturn(TableStatus.uniqueIdAndName("42", "Table Name", true));

    ArrayList<TableProperties> tables = new ArrayList<>();
    tables.add(new TableProperties(new InstanceProperties()));
    tables.add(new TableProperties(new InstanceProperties()));
    tables.add(tableProperties);

    // Act and Assert
    assertThrows(FailedGarbageCollectionException.class, () -> garbageCollector.run(tables));
    verify(tableProperties).getInt(isA(TableProperty.class));
    verify(instanceProperties, atLeast(1)).getInt(isA(InstanceProperty.class));
    verify(tableProperties).getStatus();
    verify(stateStore, atLeast(1)).getReadyForGCFilenamesBefore(Mockito.<Instant>any());
    verify(stateStoreProvider, atLeast(1)).getStateStore(Mockito.<TableProperties>any());
    verify(sendAsyncCommit).send(isA(StateStoreCommitRequest.class));
    verify(deleteFile).deleteFileAndSketches(eq("Starting GC for table {}"));
  }

  /**
   * Test {@link GarbageCollector#run(List)}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@code Obtained list of {} tables}.</li>
   *   <li>Then calls {@link StateStoreCommitRequestSender#send(StateStoreCommitRequest)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GarbageCollector#run(List)}
   */
  @Test
  @DisplayName("Test run(List); given ArrayList() add 'Obtained list of {} tables'; then calls send(StateStoreCommitRequest)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void GarbageCollector.run(List)"})
  void testRun_givenArrayListAddObtainedListOfTables_thenCallsSend()
      throws IOException, StateStoreException, FailedGarbageCollectionException {
    // Arrange
    DeleteFile deleteFile = mock(DeleteFile.class);
    doNothing().when(deleteFile).deleteFileAndSketches(Mockito.<String>any());
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.getInt(Mockito.<InstanceProperty>any())).thenReturn(1);

    ArrayList<String> stringList = new ArrayList<>();
    stringList.add("Obtained list of {} tables");
    Stream<String> streamResult = stringList.stream();
    StateStore stateStore = mock(StateStore.class);
    when(stateStore.getReadyForGCFilenamesBefore(Mockito.<Instant>any())).thenReturn(streamResult);
    Factory stateStoreFactory = mock(Factory.class);
    when(stateStoreFactory.getStateStore(Mockito.<TableProperties>any())).thenReturn(stateStore);
    StateStoreProvider stateStoreProvider = new StateStoreProvider(3, stateStoreFactory);

    StateStoreCommitRequestSender sendAsyncCommit = mock(StateStoreCommitRequestSender.class);
    doNothing().when(sendAsyncCommit).send(Mockito.<StateStoreCommitRequest>any());
    GarbageCollector garbageCollector = new GarbageCollector(deleteFile, instanceProperties, stateStoreProvider,
        sendAsyncCommit);

    ArrayList<TableProperties> tables = new ArrayList<>();
    tables.add(new TableProperties(new InstanceProperties()));

    // Act
    garbageCollector.run(tables);

    // Assert
    verify(instanceProperties).getInt(isA(InstanceProperty.class));
    verify(stateStore).getReadyForGCFilenamesBefore(isA(Instant.class));
    verify(stateStoreFactory).getStateStore(isA(TableProperties.class));
    verify(sendAsyncCommit).send(isA(StateStoreCommitRequest.class));
    verify(deleteFile).deleteFileAndSketches(eq("Obtained list of {} tables"));
  }

  /**
   * Test {@link GarbageCollector#run(List)}.
   * <ul>
   *   <li>Given {@link InstanceProperties} {@link SleeperPropertyValues#getInt(SleeperProperty)} return zero.</li>
   *   <li>Then calls {@link StateStoreCommitRequestSender#send(StateStoreCommitRequest)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GarbageCollector#run(List)}
   */
  @Test
  @DisplayName("Test run(List); given InstanceProperties getInt(SleeperProperty) return zero; then calls send(StateStoreCommitRequest)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void GarbageCollector.run(List)"})
  void testRun_givenInstancePropertiesGetIntReturnZero_thenCallsSend()
      throws IOException, StateStoreException, FailedGarbageCollectionException {
    // Arrange
    DeleteFile deleteFile = mock(DeleteFile.class);
    doNothing().when(deleteFile).deleteFileAndSketches(Mockito.<String>any());
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.getInt(Mockito.<InstanceProperty>any())).thenReturn(0);

    ArrayList<String> stringList = new ArrayList<>();
    stringList.add("Obtained list of {} tables");
    Stream<String> streamResult = stringList.stream();
    StateStore stateStore = mock(StateStore.class);
    when(stateStore.getReadyForGCFilenamesBefore(Mockito.<Instant>any())).thenReturn(streamResult);
    Factory stateStoreFactory = mock(Factory.class);
    when(stateStoreFactory.getStateStore(Mockito.<TableProperties>any())).thenReturn(stateStore);
    StateStoreProvider stateStoreProvider = new StateStoreProvider(3, stateStoreFactory);

    StateStoreCommitRequestSender sendAsyncCommit = mock(StateStoreCommitRequestSender.class);
    doNothing().when(sendAsyncCommit).send(Mockito.<StateStoreCommitRequest>any());
    GarbageCollector garbageCollector = new GarbageCollector(deleteFile, instanceProperties, stateStoreProvider,
        sendAsyncCommit);

    ArrayList<TableProperties> tables = new ArrayList<>();
    tables.add(new TableProperties(new InstanceProperties()));

    // Act
    garbageCollector.run(tables);

    // Assert
    verify(instanceProperties).getInt(isA(InstanceProperty.class));
    verify(stateStore).getReadyForGCFilenamesBefore(isA(Instant.class));
    verify(stateStoreFactory).getStateStore(isA(TableProperties.class));
    verify(sendAsyncCommit).send(isA(StateStoreCommitRequest.class));
    verify(deleteFile).deleteFileAndSketches(eq("Obtained list of {} tables"));
  }

  /**
   * Test {@link GarbageCollector#run(List)}.
   * <ul>
   *   <li>Given {@link StateStoreProvider#StateStoreProvider(int, Factory)} with cacheSize is zero and stateStoreFactory is {@link Factory}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GarbageCollector#run(List)}
   */
  @Test
  @DisplayName("Test run(List); given StateStoreProvider(int, Factory) with cacheSize is zero and stateStoreFactory is Factory")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void GarbageCollector.run(List)"})
  void testRun_givenStateStoreProviderWithCacheSizeIsZeroAndStateStoreFactoryIsFactory()
      throws StateStoreException, FailedGarbageCollectionException {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.getInt(Mockito.<InstanceProperty>any())).thenReturn(1);
    StateStore stateStore = mock(StateStore.class);

    ArrayList<String> stringList = new ArrayList<>();
    Stream<String> streamResult = stringList.stream();
    when(stateStore.getReadyForGCFilenamesBefore(Mockito.<Instant>any())).thenReturn(streamResult);
    Factory stateStoreFactory = mock(Factory.class);
    when(stateStoreFactory.getStateStore(Mockito.<TableProperties>any())).thenReturn(stateStore);
    GarbageCollector garbageCollector = new GarbageCollector(mock(DeleteFile.class), instanceProperties,
        new StateStoreProvider(0, stateStoreFactory), mock(StateStoreCommitRequestSender.class));

    ArrayList<TableProperties> tables = new ArrayList<>();
    tables.add(new TableProperties(new InstanceProperties()));

    // Act
    garbageCollector.run(tables);

    // Assert
    verify(instanceProperties).getInt(isA(InstanceProperty.class));
    verify(stateStore).getReadyForGCFilenamesBefore(isA(Instant.class));
    verify(stateStoreFactory).getStateStore(isA(TableProperties.class));
  }

  /**
   * Test {@link GarbageCollector#run(List)}.
   * <ul>
   *   <li>Given {@link TableProperties} {@link SleeperPropertyValues#getBoolean(SleeperProperty)} return {@code false}.</li>
   *   <li>Then calls {@link FileReferenceStore#addFilesTransaction(AddTransactionRequest)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GarbageCollector#run(List)}
   */
  @Test
  @DisplayName("Test run(List); given TableProperties getBoolean(SleeperProperty) return 'false'; then calls addFilesTransaction(AddTransactionRequest)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void GarbageCollector.run(List)"})
  void testRun_givenTablePropertiesGetBooleanReturnFalse_thenCallsAddFilesTransaction()
      throws IOException, StateStoreException, FailedGarbageCollectionException {
    // Arrange
    new FailedGarbageCollectionException(new ArrayList<>());
    DeleteFile deleteFile = mock(DeleteFile.class);
    doNothing().when(deleteFile).deleteFileAndSketches(Mockito.<String>any());
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.getInt(Mockito.<InstanceProperty>any())).thenReturn(1);

    ArrayList<String> stringList = new ArrayList<>();
    stringList.add("Starting GC for table {}");
    Stream<String> streamResult = stringList.stream();
    StateStore stateStore = mock(StateStore.class);
    doNothing().when(stateStore).addFilesTransaction(Mockito.<AddTransactionRequest>any());
    when(stateStore.getReadyForGCFilenamesBefore(Mockito.<Instant>any())).thenReturn(streamResult);
    StateStoreProvider stateStoreProvider = mock(StateStoreProvider.class);
    when(stateStoreProvider.getStateStore(Mockito.<TableProperties>any())).thenReturn(stateStore);
    GarbageCollector garbageCollector = new GarbageCollector(deleteFile, instanceProperties, stateStoreProvider,
        mock(StateStoreCommitRequestSender.class));
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.getBoolean(Mockito.<TableProperty>any())).thenReturn(false);
    when(tableProperties.getInt(Mockito.<TableProperty>any())).thenReturn(1);
    when(tableProperties.getStatus()).thenReturn(TableStatus.uniqueIdAndName("42", "Table Name", true));

    ArrayList<TableProperties> tables = new ArrayList<>();
    tables.add(tableProperties);

    // Act
    garbageCollector.run(tables);

    // Assert
    verify(tableProperties).getBoolean(isA(TableProperty.class));
    verify(instanceProperties).getInt(isA(InstanceProperty.class));
    verify(tableProperties).getInt(isA(TableProperty.class));
    verify(tableProperties).getStatus();
    verify(stateStore).addFilesTransaction(isA(AddTransactionRequest.class));
    verify(stateStore).getReadyForGCFilenamesBefore(isA(Instant.class));
    verify(stateStoreProvider).getStateStore(isA(TableProperties.class));
    verify(deleteFile).deleteFileAndSketches(eq("Starting GC for table {}"));
  }

  /**
   * Test {@link GarbageCollector#run(List)}.
   * <ul>
   *   <li>Given {@link TableProperties} {@link TableProperties#get(TableProperty)} return {@code Get}.</li>
   *   <li>Then calls {@link TableProperties#get(TableProperty)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GarbageCollector#run(List)}
   */
  @Test
  @DisplayName("Test run(List); given TableProperties get(TableProperty) return 'Get'; then calls get(TableProperty)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void GarbageCollector.run(List)"})
  void testRun_givenTablePropertiesGetReturnGet_thenCallsGet()
      throws IOException, StateStoreException, FailedGarbageCollectionException {
    // Arrange
    new FailedGarbageCollectionException(new ArrayList<>());
    DeleteFile deleteFile = mock(DeleteFile.class);
    doNothing().when(deleteFile).deleteFileAndSketches(Mockito.<String>any());
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.getInt(Mockito.<InstanceProperty>any())).thenReturn(1);

    ArrayList<String> stringList = new ArrayList<>();
    stringList.add("Starting GC for table {}");
    Stream<String> streamResult = stringList.stream();
    StateStore stateStore = mock(StateStore.class);
    when(stateStore.getReadyForGCFilenamesBefore(Mockito.<Instant>any())).thenReturn(streamResult);
    StateStoreProvider stateStoreProvider = mock(StateStoreProvider.class);
    when(stateStoreProvider.getStateStore(Mockito.<TableProperties>any())).thenReturn(stateStore);
    StateStoreCommitRequestSender sendAsyncCommit = mock(StateStoreCommitRequestSender.class);
    doNothing().when(sendAsyncCommit).send(Mockito.<StateStoreCommitRequest>any());
    GarbageCollector garbageCollector = new GarbageCollector(deleteFile, instanceProperties, stateStoreProvider,
        sendAsyncCommit);
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.get(Mockito.<TableProperty>any())).thenReturn("Get");
    when(tableProperties.getBoolean(Mockito.<TableProperty>any())).thenReturn(true);
    when(tableProperties.getInt(Mockito.<TableProperty>any())).thenReturn(1);
    when(tableProperties.getStatus()).thenReturn(TableStatus.uniqueIdAndName("42", "Table Name", true));

    ArrayList<TableProperties> tables = new ArrayList<>();
    tables.add(tableProperties);

    // Act
    garbageCollector.run(tables);

    // Assert
    verify(tableProperties).getBoolean(isA(TableProperty.class));
    verify(instanceProperties).getInt(isA(InstanceProperty.class));
    verify(tableProperties).getInt(isA(TableProperty.class));
    verify(tableProperties).get(isA(TableProperty.class));
    verify(tableProperties, atLeast(1)).getStatus();
    verify(stateStore).getReadyForGCFilenamesBefore(isA(Instant.class));
    verify(stateStoreProvider).getStateStore(isA(TableProperties.class));
    verify(sendAsyncCommit).send(isA(StateStoreCommitRequest.class));
    verify(deleteFile).deleteFileAndSketches(eq("Starting GC for table {}"));
  }

  /**
   * Test {@link GarbageCollector#run(List)}.
   * <ul>
   *   <li>Given {@link TableProperties} {@link TableProperties#getStatus()} return {@code null}.</li>
   *   <li>Then calls {@link TableProperties#get(TableProperty)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GarbageCollector#run(List)}
   */
  @Test
  @DisplayName("Test run(List); given TableProperties getStatus() return 'null'; then calls get(TableProperty)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void GarbageCollector.run(List)"})
  void testRun_givenTablePropertiesGetStatusReturnNull_thenCallsGet()
      throws IOException, StateStoreException, FailedGarbageCollectionException {
    // Arrange
    new FailedGarbageCollectionException(new ArrayList<>());
    DeleteFile deleteFile = mock(DeleteFile.class);
    doNothing().when(deleteFile).deleteFileAndSketches(Mockito.<String>any());
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.getInt(Mockito.<InstanceProperty>any())).thenReturn(1);

    ArrayList<String> stringList = new ArrayList<>();
    stringList.add("Starting GC for table {}");
    Stream<String> streamResult = stringList.stream();
    StateStore stateStore = mock(StateStore.class);
    when(stateStore.getReadyForGCFilenamesBefore(Mockito.<Instant>any())).thenReturn(streamResult);
    StateStoreProvider stateStoreProvider = mock(StateStoreProvider.class);
    when(stateStoreProvider.getStateStore(Mockito.<TableProperties>any())).thenReturn(stateStore);
    StateStoreCommitRequestSender sendAsyncCommit = mock(StateStoreCommitRequestSender.class);
    doNothing().when(sendAsyncCommit).send(Mockito.<StateStoreCommitRequest>any());
    GarbageCollector garbageCollector = new GarbageCollector(deleteFile, instanceProperties, stateStoreProvider,
        sendAsyncCommit);
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.get(Mockito.<TableProperty>any())).thenReturn("Get");
    when(tableProperties.getBoolean(Mockito.<TableProperty>any())).thenReturn(true);
    when(tableProperties.getInt(Mockito.<TableProperty>any())).thenReturn(1);
    when(tableProperties.getStatus()).thenReturn(null);

    ArrayList<TableProperties> tables = new ArrayList<>();
    tables.add(tableProperties);

    // Act
    garbageCollector.run(tables);

    // Assert
    verify(tableProperties).getBoolean(isA(TableProperty.class));
    verify(instanceProperties).getInt(isA(InstanceProperty.class));
    verify(tableProperties).getInt(isA(TableProperty.class));
    verify(tableProperties).get(isA(TableProperty.class));
    verify(tableProperties, atLeast(1)).getStatus();
    verify(stateStore).getReadyForGCFilenamesBefore(isA(Instant.class));
    verify(stateStoreProvider).getStateStore(isA(TableProperties.class));
    verify(sendAsyncCommit).send(isA(StateStoreCommitRequest.class));
    verify(deleteFile).deleteFileAndSketches(eq("Starting GC for table {}"));
  }

  /**
   * Test {@link GarbageCollector#run(List)}.
   * <ul>
   *   <li>Given {@link TableProperties} {@link TableProperties#getStatus()} return {@link TableStatus}.</li>
   *   <li>Then calls {@link TableProperties#get(TableProperty)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GarbageCollector#run(List)}
   */
  @Test
  @DisplayName("Test run(List); given TableProperties getStatus() return TableStatus; then calls get(TableProperty)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void GarbageCollector.run(List)"})
  void testRun_givenTablePropertiesGetStatusReturnTableStatus_thenCallsGet()
      throws IOException, StateStoreException, FailedGarbageCollectionException {
    // Arrange
    new FailedGarbageCollectionException(new ArrayList<>());
    DeleteFile deleteFile = mock(DeleteFile.class);
    doNothing().when(deleteFile).deleteFileAndSketches(Mockito.<String>any());
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.getInt(Mockito.<InstanceProperty>any())).thenReturn(1);

    ArrayList<String> stringList = new ArrayList<>();
    stringList.add("Starting GC for table {}");
    Stream<String> streamResult = stringList.stream();
    StateStore stateStore = mock(StateStore.class);
    when(stateStore.getReadyForGCFilenamesBefore(Mockito.<Instant>any())).thenReturn(streamResult);
    StateStoreProvider stateStoreProvider = mock(StateStoreProvider.class);
    when(stateStoreProvider.getStateStore(Mockito.<TableProperties>any())).thenReturn(stateStore);
    GarbageCollector garbageCollector = new GarbageCollector(deleteFile, instanceProperties, stateStoreProvider,
        mock(StateStoreCommitRequestSender.class));
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.get(Mockito.<TableProperty>any()))
        .thenThrow(new FailedGarbageCollectionException(new ArrayList<>()));
    when(tableProperties.getBoolean(Mockito.<TableProperty>any())).thenReturn(true);
    when(tableProperties.getInt(Mockito.<TableProperty>any())).thenReturn(1);
    when(tableProperties.getStatus()).thenReturn(mock(TableStatus.class));

    ArrayList<TableProperties> tables = new ArrayList<>();
    tables.add(tableProperties);

    // Act and Assert
    assertThrows(FailedGarbageCollectionException.class, () -> garbageCollector.run(tables));
    verify(tableProperties).getBoolean(isA(TableProperty.class));
    verify(instanceProperties).getInt(isA(InstanceProperty.class));
    verify(tableProperties).getInt(isA(TableProperty.class));
    verify(tableProperties).get(isA(TableProperty.class));
    verify(tableProperties).getStatus();
    verify(stateStore).getReadyForGCFilenamesBefore(isA(Instant.class));
    verify(stateStoreProvider).getStateStore(isA(TableProperties.class));
    verify(deleteFile).deleteFileAndSketches(eq("Starting GC for table {}"));
  }

  /**
   * Test {@link GarbageCollector#run(List)}.
   * <ul>
   *   <li>Then calls {@link StateStoreProvider#getStateStore(TableProperties)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GarbageCollector#run(List)}
   */
  @Test
  @DisplayName("Test run(List); then calls getStateStore(TableProperties)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void GarbageCollector.run(List)"})
  void testRun_thenCallsGetStateStore() throws StateStoreException, FailedGarbageCollectionException {
    // Arrange
    new FailedGarbageCollectionException(new ArrayList<>());
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.getInt(Mockito.<InstanceProperty>any())).thenReturn(1);
    StateStore stateStore = mock(StateStore.class);

    ArrayList<String> stringList = new ArrayList<>();
    Stream<String> streamResult = stringList.stream();
    when(stateStore.getReadyForGCFilenamesBefore(Mockito.<Instant>any())).thenReturn(streamResult);
    StateStoreProvider stateStoreProvider = mock(StateStoreProvider.class);
    when(stateStoreProvider.getStateStore(Mockito.<TableProperties>any())).thenReturn(stateStore);
    GarbageCollector garbageCollector = new GarbageCollector(mock(DeleteFile.class), instanceProperties,
        stateStoreProvider, mock(StateStoreCommitRequestSender.class));

    ArrayList<TableProperties> tables = new ArrayList<>();
    tables.add(new TableProperties(new InstanceProperties()));

    // Act
    garbageCollector.run(tables);

    // Assert
    verify(instanceProperties).getInt(isA(InstanceProperty.class));
    verify(stateStore).getReadyForGCFilenamesBefore(isA(Instant.class));
    verify(stateStoreProvider).getStateStore(isA(TableProperties.class));
  }

  /**
   * Test {@link GarbageCollector#run(List)}.
   * <ul>
   *   <li>Then throw {@link FailedGarbageCollectionException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GarbageCollector#run(List)}
   */
  @Test
  @DisplayName("Test run(List); then throw FailedGarbageCollectionException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void GarbageCollector.run(List)"})
  void testRun_thenThrowFailedGarbageCollectionException()
      throws StateStoreException, FailedGarbageCollectionException {
    // Arrange
    new FailedGarbageCollectionException(new ArrayList<>());
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.getInt(Mockito.<InstanceProperty>any())).thenReturn(1);
    StateStore stateStore = mock(StateStore.class);

    ArrayList<String> stringList = new ArrayList<>();
    Stream<String> streamResult = stringList.stream();
    when(stateStore.getReadyForGCFilenamesBefore(Mockito.<Instant>any())).thenReturn(streamResult);
    StateStoreProvider stateStoreProvider = mock(StateStoreProvider.class);
    when(stateStoreProvider.getStateStore(Mockito.<TableProperties>any())).thenReturn(stateStore);
    GarbageCollector garbageCollector = new GarbageCollector(mock(DeleteFile.class), instanceProperties,
        stateStoreProvider, mock(StateStoreCommitRequestSender.class));

    ArrayList<TableProperties> tables = new ArrayList<>();
    tables.add(new TableProperties(new InstanceProperties()));
    tables.add(new TableProperties(new InstanceProperties()));

    // Act and Assert
    assertThrows(FailedGarbageCollectionException.class, () -> garbageCollector.run(tables));
    verify(instanceProperties, atLeast(1)).getInt(isA(InstanceProperty.class));
    verify(stateStore, atLeast(1)).getReadyForGCFilenamesBefore(isA(Instant.class));
    verify(stateStoreProvider, atLeast(1)).getStateStore(isA(TableProperties.class));
  }

  /**
   * Test {@link GarbageCollector#runAtTime(Instant, List)}.
   * <p>
   * Method under test: {@link GarbageCollector#runAtTime(Instant, List)}
   */
  @Test
  @DisplayName("Test runAtTime(Instant, List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void GarbageCollector.runAtTime(Instant, List)"})
  void testRunAtTime() throws StateStoreException, FailedGarbageCollectionException {
    // Arrange
    StateStore stateStore = mock(StateStore.class);
    when(stateStore.getReadyForGCFilenamesBefore(Mockito.<Instant>any()))
        .thenThrow(new FailedGarbageCollectionException(new ArrayList<>()));
    Factory stateStoreFactory = mock(Factory.class);
    when(stateStoreFactory.getStateStore(Mockito.<TableProperties>any())).thenReturn(stateStore);
    StateStoreProvider stateStoreProvider = new StateStoreProvider(3, stateStoreFactory);

    DeleteFile deleteFile = mock(DeleteFile.class);
    GarbageCollector garbageCollector = new GarbageCollector(deleteFile, new InstanceProperties(), stateStoreProvider,
        mock(StateStoreCommitRequestSender.class));
    Instant startTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    ArrayList<TableProperties> tables = new ArrayList<>();
    tables.add(new TableProperties(new InstanceProperties()));

    // Act and Assert
    assertThrows(FailedGarbageCollectionException.class, () -> garbageCollector.runAtTime(startTime, tables));
    verify(stateStore).getReadyForGCFilenamesBefore(isA(Instant.class));
    verify(stateStoreFactory).getStateStore(isA(TableProperties.class));
  }

  /**
   * Test {@link GarbageCollector#runAtTime(Instant, List)}.
   * <p>
   * Method under test: {@link GarbageCollector#runAtTime(Instant, List)}
   */
  @Test
  @DisplayName("Test runAtTime(Instant, List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void GarbageCollector.runAtTime(Instant, List)"})
  void testRunAtTime2() throws StateStoreException, FailedGarbageCollectionException {
    // Arrange
    StateStore stateStore = mock(StateStore.class);

    ArrayList<String> stringList = new ArrayList<>();
    Stream<String> streamResult = stringList.stream();
    when(stateStore.getReadyForGCFilenamesBefore(Mockito.<Instant>any())).thenReturn(streamResult);
    Factory stateStoreFactory = mock(Factory.class);
    when(stateStoreFactory.getStateStore(Mockito.<TableProperties>any())).thenReturn(stateStore);
    StateStoreProvider stateStoreProvider = new StateStoreProvider(0, stateStoreFactory);

    DeleteFile deleteFile = mock(DeleteFile.class);
    GarbageCollector garbageCollector = new GarbageCollector(deleteFile, new InstanceProperties(), stateStoreProvider,
        mock(StateStoreCommitRequestSender.class));
    Instant startTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    ArrayList<TableProperties> tables = new ArrayList<>();
    tables.add(new TableProperties(new InstanceProperties()));

    // Act
    garbageCollector.runAtTime(startTime, tables);

    // Assert
    verify(stateStore).getReadyForGCFilenamesBefore(isA(Instant.class));
    verify(stateStoreFactory).getStateStore(isA(TableProperties.class));
  }

  /**
   * Test {@link GarbageCollector#runAtTime(Instant, List)}.
   * <p>
   * Method under test: {@link GarbageCollector#runAtTime(Instant, List)}
   */
  @Test
  @DisplayName("Test runAtTime(Instant, List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void GarbageCollector.runAtTime(Instant, List)"})
  void testRunAtTime3() throws IOException, StateStoreException, FailedGarbageCollectionException {
    // Arrange
    DeleteFile deleteFile = mock(DeleteFile.class);
    doNothing().when(deleteFile).deleteFileAndSketches(Mockito.<String>any());

    ArrayList<String> stringList = new ArrayList<>();
    stringList.add("Obtained list of {} tables");
    Stream<String> streamResult = stringList.stream();
    StateStore stateStore = mock(StateStore.class);
    when(stateStore.getReadyForGCFilenamesBefore(Mockito.<Instant>any())).thenReturn(streamResult);
    Factory stateStoreFactory = mock(Factory.class);
    when(stateStoreFactory.getStateStore(Mockito.<TableProperties>any())).thenReturn(stateStore);
    StateStoreProvider stateStoreProvider = new StateStoreProvider(3, stateStoreFactory);

    StateStoreCommitRequestSender sendAsyncCommit = mock(StateStoreCommitRequestSender.class);
    doThrow(new FailedGarbageCollectionException(new ArrayList<>())).when(sendAsyncCommit)
        .send(Mockito.<StateStoreCommitRequest>any());
    GarbageCollector garbageCollector = new GarbageCollector(deleteFile, new InstanceProperties(), stateStoreProvider,
        sendAsyncCommit);
    Instant startTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    ArrayList<TableProperties> tables = new ArrayList<>();
    tables.add(new TableProperties(new InstanceProperties()));

    // Act and Assert
    assertThrows(FailedGarbageCollectionException.class, () -> garbageCollector.runAtTime(startTime, tables));
    verify(stateStore).getReadyForGCFilenamesBefore(isA(Instant.class));
    verify(stateStoreFactory).getStateStore(isA(TableProperties.class));
    verify(sendAsyncCommit).send(isA(StateStoreCommitRequest.class));
    verify(deleteFile).deleteFileAndSketches(eq("Obtained list of {} tables"));
  }

  /**
   * Test {@link GarbageCollector#runAtTime(Instant, List)}.
   * <p>
   * Method under test: {@link GarbageCollector#runAtTime(Instant, List)}
   */
  @Test
  @DisplayName("Test runAtTime(Instant, List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void GarbageCollector.runAtTime(Instant, List)"})
  void testRunAtTime4() throws FailedGarbageCollectionException {
    // Arrange
    Factory stateStoreFactory = mock(Factory.class);
    when(stateStoreFactory.getStateStore(Mockito.<TableProperties>any()))
        .thenThrow(new FailedGarbageCollectionException(new ArrayList<>()));
    StateStoreProvider stateStoreProvider = new StateStoreProvider(new InstanceProperties(), stateStoreFactory);

    DeleteFile deleteFile = mock(DeleteFile.class);
    GarbageCollector garbageCollector = new GarbageCollector(deleteFile, new InstanceProperties(), stateStoreProvider,
        mock(StateStoreCommitRequestSender.class));
    Instant startTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    ArrayList<TableProperties> tables = new ArrayList<>();
    tables.add(new TableProperties(new InstanceProperties()));

    // Act and Assert
    assertThrows(FailedGarbageCollectionException.class, () -> garbageCollector.runAtTime(startTime, tables));
    verify(stateStoreFactory).getStateStore(isA(TableProperties.class));
  }

  /**
   * Test {@link GarbageCollector#runAtTime(Instant, List)}.
   * <p>
   * Method under test: {@link GarbageCollector#runAtTime(Instant, List)}
   */
  @Test
  @DisplayName("Test runAtTime(Instant, List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void GarbageCollector.runAtTime(Instant, List)"})
  void testRunAtTime5() throws FailedGarbageCollectionException {
    // Arrange
    Factory stateStoreFactory = mock(Factory.class);
    when(stateStoreFactory.getStateStore(Mockito.<TableProperties>any()))
        .thenThrow(new FailedGarbageCollectionException(new ArrayList<>()));
    StateStoreProvider stateStoreProvider = new StateStoreProvider(new InstanceProperties(), stateStoreFactory);

    DeleteFile deleteFile = mock(DeleteFile.class);
    GarbageCollector garbageCollector = new GarbageCollector(deleteFile, new InstanceProperties(), stateStoreProvider,
        mock(StateStoreCommitRequestSender.class));
    Instant startTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    ArrayList<TableProperties> tables = new ArrayList<>();
    tables.add(new TableProperties(new InstanceProperties()));
    tables.add(new TableProperties(new InstanceProperties()));

    // Act and Assert
    assertThrows(FailedGarbageCollectionException.class, () -> garbageCollector.runAtTime(startTime, tables));
    verify(stateStoreFactory, atLeast(1)).getStateStore(isA(TableProperties.class));
  }

  /**
   * Test {@link GarbageCollector#runAtTime(Instant, List)}.
   * <p>
   * Method under test: {@link GarbageCollector#runAtTime(Instant, List)}
   */
  @Test
  @DisplayName("Test runAtTime(Instant, List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void GarbageCollector.runAtTime(Instant, List)"})
  void testRunAtTime6() throws StateStoreException, FailedGarbageCollectionException {
    // Arrange
    new FailedGarbageCollectionException(new ArrayList<>());
    new FailedGarbageCollectionException(new ArrayList<>());
    StateStore stateStore = mock(StateStore.class);

    ArrayList<String> stringList = new ArrayList<>();
    Stream<String> streamResult = stringList.stream();
    when(stateStore.getReadyForGCFilenamesBefore(Mockito.<Instant>any())).thenReturn(streamResult);
    FixedStateStoreProvider stateStoreProvider = new FixedStateStoreProvider(
        new TableProperties(new InstanceProperties()), stateStore);

    DeleteFile deleteFile = mock(DeleteFile.class);
    GarbageCollector garbageCollector = new GarbageCollector(deleteFile, new InstanceProperties(), stateStoreProvider,
        mock(StateStoreCommitRequestSender.class));
    Instant startTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    ArrayList<TableProperties> tables = new ArrayList<>();
    tables.add(new TableProperties(new InstanceProperties()));
    tables.add(new TableProperties(new InstanceProperties()));

    // Act and Assert
    assertThrows(FailedGarbageCollectionException.class, () -> garbageCollector.runAtTime(startTime, tables));
    verify(stateStore, atLeast(1)).getReadyForGCFilenamesBefore(isA(Instant.class));
  }

  /**
   * Test {@link GarbageCollector#runAtTime(Instant, List)}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@code Obtained list of {} tables}.</li>
   *   <li>Then calls {@link StateStoreCommitRequestSender#send(StateStoreCommitRequest)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GarbageCollector#runAtTime(Instant, List)}
   */
  @Test
  @DisplayName("Test runAtTime(Instant, List); given ArrayList() add 'Obtained list of {} tables'; then calls send(StateStoreCommitRequest)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void GarbageCollector.runAtTime(Instant, List)"})
  void testRunAtTime_givenArrayListAddObtainedListOfTables_thenCallsSend()
      throws IOException, StateStoreException, FailedGarbageCollectionException {
    // Arrange
    DeleteFile deleteFile = mock(DeleteFile.class);
    doNothing().when(deleteFile).deleteFileAndSketches(Mockito.<String>any());

    ArrayList<String> stringList = new ArrayList<>();
    stringList.add("Obtained list of {} tables");
    Stream<String> streamResult = stringList.stream();
    StateStore stateStore = mock(StateStore.class);
    when(stateStore.getReadyForGCFilenamesBefore(Mockito.<Instant>any())).thenReturn(streamResult);
    Factory stateStoreFactory = mock(Factory.class);
    when(stateStoreFactory.getStateStore(Mockito.<TableProperties>any())).thenReturn(stateStore);
    StateStoreProvider stateStoreProvider = new StateStoreProvider(3, stateStoreFactory);

    StateStoreCommitRequestSender sendAsyncCommit = mock(StateStoreCommitRequestSender.class);
    doNothing().when(sendAsyncCommit).send(Mockito.<StateStoreCommitRequest>any());
    GarbageCollector garbageCollector = new GarbageCollector(deleteFile, new InstanceProperties(), stateStoreProvider,
        sendAsyncCommit);
    Instant startTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    ArrayList<TableProperties> tables = new ArrayList<>();
    tables.add(new TableProperties(new InstanceProperties()));

    // Act
    garbageCollector.runAtTime(startTime, tables);

    // Assert
    verify(stateStore).getReadyForGCFilenamesBefore(isA(Instant.class));
    verify(stateStoreFactory).getStateStore(isA(TableProperties.class));
    verify(sendAsyncCommit).send(isA(StateStoreCommitRequest.class));
    verify(deleteFile).deleteFileAndSketches(eq("Obtained list of {} tables"));
  }

  /**
   * Test {@link GarbageCollector#runAtTime(Instant, List)}.
   * <ul>
   *   <li>Given {@link FailedGarbageCollectionException#FailedGarbageCollectionException(List)} with tableFailures is {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GarbageCollector#runAtTime(Instant, List)}
   */
  @Test
  @DisplayName("Test runAtTime(Instant, List); given FailedGarbageCollectionException(List) with tableFailures is ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void GarbageCollector.runAtTime(Instant, List)"})
  void testRunAtTime_givenFailedGarbageCollectionExceptionWithTableFailuresIsArrayList()
      throws StateStoreException, FailedGarbageCollectionException {
    // Arrange
    new FailedGarbageCollectionException(new ArrayList<>());
    StateStore stateStore = mock(StateStore.class);

    ArrayList<String> stringList = new ArrayList<>();
    Stream<String> streamResult = stringList.stream();
    when(stateStore.getReadyForGCFilenamesBefore(Mockito.<Instant>any())).thenReturn(streamResult);
    Factory stateStoreFactory = mock(Factory.class);
    when(stateStoreFactory.getStateStore(Mockito.<TableProperties>any())).thenReturn(stateStore);
    StateStoreProvider stateStoreProvider = new StateStoreProvider(3, stateStoreFactory);

    DeleteFile deleteFile = mock(DeleteFile.class);
    GarbageCollector garbageCollector = new GarbageCollector(deleteFile, new InstanceProperties(), stateStoreProvider,
        mock(StateStoreCommitRequestSender.class));
    Instant startTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    ArrayList<TableProperties> tables = new ArrayList<>();
    tables.add(new TableProperties(new InstanceProperties()));
    tables.add(new TableProperties(new InstanceProperties()));

    // Act and Assert
    assertThrows(FailedGarbageCollectionException.class, () -> garbageCollector.runAtTime(startTime, tables));
    verify(stateStore, atLeast(1)).getReadyForGCFilenamesBefore(isA(Instant.class));
    verify(stateStoreFactory).getStateStore(isA(TableProperties.class));
  }

  /**
   * Test {@link GarbageCollector#runAtTime(Instant, List)}.
   * <ul>
   *   <li>Then calls {@link FileReferenceStoreQueries#getReadyForGCFilenamesBefore(Instant)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GarbageCollector#runAtTime(Instant, List)}
   */
  @Test
  @DisplayName("Test runAtTime(Instant, List); then calls getReadyForGCFilenamesBefore(Instant)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void GarbageCollector.runAtTime(Instant, List)"})
  void testRunAtTime_thenCallsGetReadyForGCFilenamesBefore()
      throws StateStoreException, FailedGarbageCollectionException {
    // Arrange
    StateStore stateStore = mock(StateStore.class);

    ArrayList<String> stringList = new ArrayList<>();
    Stream<String> streamResult = stringList.stream();
    when(stateStore.getReadyForGCFilenamesBefore(Mockito.<Instant>any())).thenReturn(streamResult);
    Factory stateStoreFactory = mock(Factory.class);
    when(stateStoreFactory.getStateStore(Mockito.<TableProperties>any())).thenReturn(stateStore);
    StateStoreProvider stateStoreProvider = new StateStoreProvider(3, stateStoreFactory);

    DeleteFile deleteFile = mock(DeleteFile.class);
    GarbageCollector garbageCollector = new GarbageCollector(deleteFile, new InstanceProperties(), stateStoreProvider,
        mock(StateStoreCommitRequestSender.class));
    Instant startTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    ArrayList<TableProperties> tables = new ArrayList<>();
    tables.add(new TableProperties(new InstanceProperties()));

    // Act
    garbageCollector.runAtTime(startTime, tables);

    // Assert
    verify(stateStore).getReadyForGCFilenamesBefore(isA(Instant.class));
    verify(stateStoreFactory).getStateStore(isA(TableProperties.class));
  }

  /**
   * Test {@link GarbageCollector#deleteFileAndSketches(Configuration)}.
   * <ul>
   *   <li>Then throw {@link FailedGarbageCollectionException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GarbageCollector#deleteFileAndSketches(Configuration)}
   */
  @Test
  @DisplayName("Test deleteFileAndSketches(Configuration); then throw FailedGarbageCollectionException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"DeleteFile GarbageCollector.deleteFileAndSketches(Configuration)"})
  void testDeleteFileAndSketches_thenThrowFailedGarbageCollectionException() throws IOException {
    // Arrange
    Configuration conf = mock(Configuration.class);
    when(conf.getBoolean(Mockito.<String>any(), anyBoolean()))
        .thenThrow(new FailedGarbageCollectionException(new ArrayList<>()));
    when(conf.get(Mockito.<String>any(), Mockito.<String>any())).thenReturn("Get");

    // Act and Assert
    assertThrows(FailedGarbageCollectionException.class,
        () -> GarbageCollector.deleteFileAndSketches(conf).deleteFileAndSketches("foo"));
    verify(conf).get(eq("fs.defaultFS"), eq("file:///"));
    verify(conf).getBoolean(eq("fs.hdfs.impl.disable.cache"), eq(false));
  }
}
