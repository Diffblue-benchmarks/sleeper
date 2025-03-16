package sleeper.compaction.core.job.dispatch;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.compaction.core.job.CompactionJob;
import sleeper.compaction.core.job.dispatch.CompactionJobDispatcher.ReadBatch;
import sleeper.compaction.core.job.dispatch.CompactionJobDispatcher.ReturnRequestToPendingQueue;
import sleeper.compaction.core.job.dispatch.CompactionJobDispatcher.SendDeadLetter;
import sleeper.compaction.core.job.dispatch.CompactionJobDispatcher.SendJobs;
import sleeper.core.properties.SleeperProperties;
import sleeper.core.properties.SleeperPropertiesInvalidException;
import sleeper.core.properties.SleeperPropertyValues;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.instance.InstanceProperty;
import sleeper.core.properties.table.TableProperties;
import sleeper.core.properties.table.TablePropertiesProvider;
import sleeper.core.properties.table.TablePropertiesStore;
import sleeper.core.properties.table.TablePropertiesStore.Client;
import sleeper.core.properties.table.TableProperty;
import sleeper.core.statestore.CheckFileAssignmentsRequest;
import sleeper.core.statestore.FileReference;
import sleeper.core.statestore.FileReference.Builder;
import sleeper.core.statestore.StateStore;
import sleeper.core.statestore.StateStoreException;
import sleeper.core.statestore.StateStoreProvider;
import sleeper.core.statestore.StateStoreProvider.Factory;
import sleeper.core.statestore.exception.FileReferenceAssignedToJobException;
import sleeper.core.table.InMemoryTableIndex;
import sleeper.core.table.TableStatus;
import sleeper.core.tracker.compaction.job.InMemoryCompactionJobTracker;

class CompactionJobDispatcherDiffblueTest {
  /**
   * Test {@link CompactionJobDispatcher#dispatch(CompactionJobDispatchRequest)}.
   * <p>
   * Method under test: {@link CompactionJobDispatcher#dispatch(CompactionJobDispatchRequest)}
   */
  @Test
  @DisplayName("Test dispatch(CompactionJobDispatchRequest)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void CompactionJobDispatcher.dispatch(CompactionJobDispatchRequest)"})
  void testDispatch() throws SleeperPropertiesInvalidException {
    // Arrange
    InMemoryTableIndex tableIndex = mock(InMemoryTableIndex.class);
    Optional<TableStatus> ofResult = Optional.of(TableStatus.uniqueIdAndName("42", "Table Name", true));
    when(tableIndex.getTableByUniqueId(Mockito.<String>any())).thenReturn(ofResult);
    TableProperties tableProperties = mock(TableProperties.class);
    doNothing().when(tableProperties).validate();
    when(tableProperties.get(Mockito.<TableProperty>any())).thenReturn("Get");
    when(tableProperties.getStatus()).thenReturn(TableStatus.uniqueIdAndName("42", "Table Name", true));
    Client client = mock(Client.class);
    when(client.loadProperties(Mockito.<TableStatus>any())).thenReturn(tableProperties);
    TablePropertiesStore propertiesStore = new TablePropertiesStore(tableIndex, client);

    TablePropertiesProvider tablePropertiesProvider = new TablePropertiesProvider(new InstanceProperties(),
        propertiesStore);

    ReadBatch readBatch = mock(ReadBatch.class);
    Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference fileReference = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    when(readBatch.read(Mockito.<String>any(), Mockito.<String>any()))
        .thenThrow(new FileReferenceAssignedToJobException(fileReference));
    InstanceProperties instanceProperties = new InstanceProperties();
    StateStoreProvider stateStoreProvider = new StateStoreProvider(3, mock(Factory.class));

    CompactionJobDispatcher compactionJobDispatcher = new CompactionJobDispatcher(instanceProperties,
        tablePropertiesProvider, stateStoreProvider, new InMemoryCompactionJobTracker(), readBatch,
        mock(SendJobs.class), 3, mock(ReturnRequestToPendingQueue.class), mock(SendDeadLetter.class),
        mock(Supplier.class));
    TableProperties tableProperties2 = new TableProperties(new InstanceProperties());

    // Act and Assert
    assertThrows(FileReferenceAssignedToJobException.class,
        () -> compactionJobDispatcher.dispatch(CompactionJobDispatchRequest.forTableWithBatchIdAtTime(tableProperties2,
            "42", LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())));
    verify(readBatch).read(isNull(), eq("null/compactions/42.json"));
    verify(tableProperties).validate();
    verify(tableProperties, atLeast(1)).get(Mockito.<TableProperty>any());
    verify(tableProperties, atLeast(1)).getStatus();
    verify(client).loadProperties(isA(TableStatus.class));
    verify(tableIndex).getTableByUniqueId(isNull());
  }

  /**
   * Test {@link CompactionJobDispatcher#dispatch(CompactionJobDispatchRequest)}.
   * <p>
   * Method under test: {@link CompactionJobDispatcher#dispatch(CompactionJobDispatchRequest)}
   */
  @Test
  @DisplayName("Test dispatch(CompactionJobDispatchRequest)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void CompactionJobDispatcher.dispatch(CompactionJobDispatchRequest)"})
  void testDispatch2() throws StateStoreException {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    TablePropertiesProvider tablePropertiesProvider = mock(TablePropertiesProvider.class);
    when(tablePropertiesProvider.getById(Mockito.<String>any()))
        .thenReturn(new TableProperties(new InstanceProperties()));
    StateStore stateStore = mock(StateStore.class);
    when(stateStore.isAssigned(Mockito.<List<CheckFileAssignmentsRequest>>any())).thenReturn(true);
    Factory stateStoreFactory = mock(Factory.class);
    when(stateStoreFactory.getStateStore(Mockito.<TableProperties>any())).thenReturn(stateStore);
    StateStoreProvider stateStoreProvider = new StateStoreProvider(3, stateStoreFactory);

    ReadBatch readBatch = mock(ReadBatch.class);
    when(readBatch.read(Mockito.<String>any(), Mockito.<String>any())).thenReturn(new ArrayList<>());
    CompactionJobDispatcher compactionJobDispatcher = new CompactionJobDispatcher(instanceProperties,
        tablePropertiesProvider, stateStoreProvider, new InMemoryCompactionJobTracker(), readBatch,
        mock(SendJobs.class), 3, mock(ReturnRequestToPendingQueue.class), mock(SendDeadLetter.class),
        mock(Supplier.class));
    TableProperties tableProperties = new TableProperties(new InstanceProperties());

    // Act
    compactionJobDispatcher.dispatch(CompactionJobDispatchRequest.forTableWithBatchIdAtTime(tableProperties, "42",
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

    // Assert
    verify(readBatch).read(eq("Get"), eq("null/compactions/42.json"));
    verify(instanceProperties).get(isA(InstanceProperty.class));
    verify(tablePropertiesProvider).getById(isNull());
    verify(stateStore).isAssigned(isA(List.class));
    verify(stateStoreFactory).getStateStore(isA(TableProperties.class));
  }

  /**
   * Test {@link CompactionJobDispatcher#dispatch(CompactionJobDispatchRequest)}.
   * <p>
   * Method under test: {@link CompactionJobDispatcher#dispatch(CompactionJobDispatchRequest)}
   */
  @Test
  @DisplayName("Test dispatch(CompactionJobDispatchRequest)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void CompactionJobDispatcher.dispatch(CompactionJobDispatchRequest)"})
  void testDispatch3() throws StateStoreException {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    TablePropertiesProvider tablePropertiesProvider = mock(TablePropertiesProvider.class);
    when(tablePropertiesProvider.getById(Mockito.<String>any()))
        .thenReturn(new TableProperties(new InstanceProperties()));
    StateStore stateStore = mock(StateStore.class);
    when(stateStore.isAssigned(Mockito.<List<CheckFileAssignmentsRequest>>any())).thenReturn(true);
    Factory stateStoreFactory = mock(Factory.class);
    when(stateStoreFactory.getStateStore(Mockito.<TableProperties>any())).thenReturn(stateStore);
    StateStoreProvider stateStoreProvider = new StateStoreProvider(0, stateStoreFactory);

    ReadBatch readBatch = mock(ReadBatch.class);
    when(readBatch.read(Mockito.<String>any(), Mockito.<String>any())).thenReturn(new ArrayList<>());
    CompactionJobDispatcher compactionJobDispatcher = new CompactionJobDispatcher(instanceProperties,
        tablePropertiesProvider, stateStoreProvider, new InMemoryCompactionJobTracker(), readBatch,
        mock(SendJobs.class), 3, mock(ReturnRequestToPendingQueue.class), mock(SendDeadLetter.class),
        mock(Supplier.class));
    TableProperties tableProperties = new TableProperties(new InstanceProperties());

    // Act
    compactionJobDispatcher.dispatch(CompactionJobDispatchRequest.forTableWithBatchIdAtTime(tableProperties, "42",
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

    // Assert
    verify(readBatch).read(eq("Get"), eq("null/compactions/42.json"));
    verify(instanceProperties).get(isA(InstanceProperty.class));
    verify(tablePropertiesProvider).getById(isNull());
    verify(stateStore).isAssigned(isA(List.class));
    verify(stateStoreFactory).getStateStore(isA(TableProperties.class));
  }

  /**
   * Test {@link CompactionJobDispatcher#dispatch(CompactionJobDispatchRequest)}.
   * <p>
   * Method under test: {@link CompactionJobDispatcher#dispatch(CompactionJobDispatchRequest)}
   */
  @Test
  @DisplayName("Test dispatch(CompactionJobDispatchRequest)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void CompactionJobDispatcher.dispatch(CompactionJobDispatchRequest)"})
  void testDispatch4() throws StateStoreException {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    TablePropertiesProvider tablePropertiesProvider = mock(TablePropertiesProvider.class);
    when(tablePropertiesProvider.getById(Mockito.<String>any()))
        .thenReturn(new TableProperties(new InstanceProperties()));
    StateStore stateStore = mock(StateStore.class);
    when(stateStore.isAssigned(Mockito.<List<CheckFileAssignmentsRequest>>any())).thenReturn(false);
    Factory stateStoreFactory = mock(Factory.class);
    when(stateStoreFactory.getStateStore(Mockito.<TableProperties>any())).thenReturn(stateStore);
    StateStoreProvider stateStoreProvider = new StateStoreProvider(3, stateStoreFactory);

    ReadBatch readBatch = mock(ReadBatch.class);
    when(readBatch.read(Mockito.<String>any(), Mockito.<String>any())).thenReturn(new ArrayList<>());
    ReturnRequestToPendingQueue returnToPendingQueue = mock(ReturnRequestToPendingQueue.class);
    Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference fileReference = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    doThrow(new FileReferenceAssignedToJobException(fileReference)).when(returnToPendingQueue)
        .sendWithDelay(Mockito.<CompactionJobDispatchRequest>any(), anyInt());
    SendDeadLetter sendDeadLetter = mock(SendDeadLetter.class);
    Builder jobIdResult2 = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference fileReference2 = jobIdResult2
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    doThrow(new FileReferenceAssignedToJobException(fileReference2)).when(sendDeadLetter)
        .send(Mockito.<CompactionJobDispatchRequest>any());
    Supplier<Instant> timeSupplier = mock(Supplier.class);
    when(timeSupplier.get()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    CompactionJobDispatcher compactionJobDispatcher = new CompactionJobDispatcher(instanceProperties,
        tablePropertiesProvider, stateStoreProvider, new InMemoryCompactionJobTracker(), readBatch,
        mock(SendJobs.class), 3, returnToPendingQueue, sendDeadLetter, timeSupplier);
    TableProperties tableProperties = new TableProperties(new InstanceProperties());

    // Act and Assert
    assertThrows(FileReferenceAssignedToJobException.class,
        () -> compactionJobDispatcher.dispatch(CompactionJobDispatchRequest.forTableWithBatchIdAtTime(tableProperties,
            "42", LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())));
    verify(timeSupplier).get();
    verify(readBatch).read(eq("Get"), eq("null/compactions/42.json"));
    verify(returnToPendingQueue).sendWithDelay(isA(CompactionJobDispatchRequest.class), eq(30));
    verify(sendDeadLetter).send(isA(CompactionJobDispatchRequest.class));
    verify(instanceProperties).get(isA(InstanceProperty.class));
    verify(tablePropertiesProvider).getById(isNull());
    verify(stateStore).isAssigned(isA(List.class));
    verify(stateStoreFactory).getStateStore(isA(TableProperties.class));
  }

  /**
   * Test {@link CompactionJobDispatcher#dispatch(CompactionJobDispatchRequest)}.
   * <p>
   * Method under test: {@link CompactionJobDispatcher#dispatch(CompactionJobDispatchRequest)}
   */
  @Test
  @DisplayName("Test dispatch(CompactionJobDispatchRequest)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void CompactionJobDispatcher.dispatch(CompactionJobDispatchRequest)"})
  void testDispatch5() throws StateStoreException {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.get(Mockito.<TableProperty>any())).thenReturn("Get");
    when(tableProperties.getStatus()).thenReturn(TableStatus.uniqueIdAndName("42", "Table Name", true));
    TablePropertiesProvider tablePropertiesProvider = mock(TablePropertiesProvider.class);
    when(tablePropertiesProvider.getById(Mockito.<String>any())).thenReturn(tableProperties);
    StateStore stateStore = mock(StateStore.class);
    Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference fileReference = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    when(stateStore.isAssigned(Mockito.<List<CheckFileAssignmentsRequest>>any()))
        .thenThrow(new FileReferenceAssignedToJobException(fileReference));
    Factory stateStoreFactory = mock(Factory.class);
    when(stateStoreFactory.getStateStore(Mockito.<TableProperties>any())).thenReturn(stateStore);
    StateStoreProvider stateStoreProvider = new StateStoreProvider(new InstanceProperties(), stateStoreFactory);

    ReadBatch readBatch = mock(ReadBatch.class);
    when(readBatch.read(Mockito.<String>any(), Mockito.<String>any())).thenReturn(new ArrayList<>());
    SendDeadLetter sendDeadLetter = mock(SendDeadLetter.class);
    doNothing().when(sendDeadLetter).send(Mockito.<CompactionJobDispatchRequest>any());
    CompactionJobDispatcher compactionJobDispatcher = new CompactionJobDispatcher(instanceProperties,
        tablePropertiesProvider, stateStoreProvider, new InMemoryCompactionJobTracker(), readBatch,
        mock(SendJobs.class), 3, mock(ReturnRequestToPendingQueue.class), sendDeadLetter, mock(Supplier.class));
    TableProperties tableProperties2 = new TableProperties(new InstanceProperties());

    // Act
    compactionJobDispatcher.dispatch(CompactionJobDispatchRequest.forTableWithBatchIdAtTime(tableProperties2, "42",
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

    // Assert
    verify(readBatch).read(eq("Get"), eq("null/compactions/42.json"));
    verify(sendDeadLetter).send(isA(CompactionJobDispatchRequest.class));
    verify(instanceProperties).get(isA(InstanceProperty.class));
    verify(tableProperties).get(isA(TableProperty.class));
    verify(tableProperties).getStatus();
    verify(tablePropertiesProvider).getById(isNull());
    verify(stateStore).isAssigned(isA(List.class));
    verify(stateStoreFactory).getStateStore(isA(TableProperties.class));
  }

  /**
   * Test {@link CompactionJobDispatcher#dispatch(CompactionJobDispatchRequest)}.
   * <p>
   * Method under test: {@link CompactionJobDispatcher#dispatch(CompactionJobDispatchRequest)}
   */
  @Test
  @DisplayName("Test dispatch(CompactionJobDispatchRequest)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void CompactionJobDispatcher.dispatch(CompactionJobDispatchRequest)"})
  void testDispatch6() {
    // Arrange
    Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference fileReference = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    new FileReferenceAssignedToJobException(fileReference);
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.get(Mockito.<TableProperty>any())).thenReturn("Get");
    when(tableProperties.getStatus()).thenReturn(TableStatus.uniqueIdAndName("42", "Table Name", true));
    TablePropertiesProvider tablePropertiesProvider = mock(TablePropertiesProvider.class);
    when(tablePropertiesProvider.getById(Mockito.<String>any())).thenReturn(tableProperties);
    Factory stateStoreFactory = mock(Factory.class);
    Builder jobIdResult2 = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference fileReference2 = jobIdResult2
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    when(stateStoreFactory.getStateStore(Mockito.<TableProperties>any()))
        .thenThrow(new FileReferenceAssignedToJobException(fileReference2));
    StateStoreProvider stateStoreProvider = new StateStoreProvider(3, stateStoreFactory);

    ReadBatch readBatch = mock(ReadBatch.class);
    when(readBatch.read(Mockito.<String>any(), Mockito.<String>any())).thenReturn(new ArrayList<>());
    SendDeadLetter sendDeadLetter = mock(SendDeadLetter.class);
    doNothing().when(sendDeadLetter).send(Mockito.<CompactionJobDispatchRequest>any());
    CompactionJobDispatcher compactionJobDispatcher = new CompactionJobDispatcher(instanceProperties,
        tablePropertiesProvider, stateStoreProvider, new InMemoryCompactionJobTracker(), readBatch,
        mock(SendJobs.class), 3, mock(ReturnRequestToPendingQueue.class), sendDeadLetter, mock(Supplier.class));
    TableProperties tableProperties2 = new TableProperties(new InstanceProperties());

    // Act
    compactionJobDispatcher.dispatch(CompactionJobDispatchRequest.forTableWithBatchIdAtTime(tableProperties2, "42",
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

    // Assert
    verify(readBatch).read(eq("Get"), eq("null/compactions/42.json"));
    verify(sendDeadLetter).send(isA(CompactionJobDispatchRequest.class));
    verify(instanceProperties).get(isA(InstanceProperty.class));
    verify(tableProperties).get(isA(TableProperty.class));
    verify(tableProperties).getStatus();
    verify(tablePropertiesProvider).getById(isNull());
    verify(stateStoreFactory).getStateStore(isA(TableProperties.class));
  }

  /**
   * Test {@link CompactionJobDispatcher#dispatch(CompactionJobDispatchRequest)}.
   * <ul>
   *   <li>Given {@link ReturnRequestToPendingQueue} {@link ReturnRequestToPendingQueue#sendWithDelay(CompactionJobDispatchRequest, int)} does nothing.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobDispatcher#dispatch(CompactionJobDispatchRequest)}
   */
  @Test
  @DisplayName("Test dispatch(CompactionJobDispatchRequest); given ReturnRequestToPendingQueue sendWithDelay(CompactionJobDispatchRequest, int) does nothing")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void CompactionJobDispatcher.dispatch(CompactionJobDispatchRequest)"})
  void testDispatch_givenReturnRequestToPendingQueueSendWithDelayDoesNothing() throws StateStoreException {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    TablePropertiesProvider tablePropertiesProvider = mock(TablePropertiesProvider.class);
    when(tablePropertiesProvider.getById(Mockito.<String>any()))
        .thenReturn(new TableProperties(new InstanceProperties()));
    StateStore stateStore = mock(StateStore.class);
    when(stateStore.isAssigned(Mockito.<List<CheckFileAssignmentsRequest>>any())).thenReturn(false);
    Factory stateStoreFactory = mock(Factory.class);
    when(stateStoreFactory.getStateStore(Mockito.<TableProperties>any())).thenReturn(stateStore);
    StateStoreProvider stateStoreProvider = new StateStoreProvider(3, stateStoreFactory);

    ReadBatch readBatch = mock(ReadBatch.class);
    when(readBatch.read(Mockito.<String>any(), Mockito.<String>any())).thenReturn(new ArrayList<>());
    ReturnRequestToPendingQueue returnToPendingQueue = mock(ReturnRequestToPendingQueue.class);
    doNothing().when(returnToPendingQueue).sendWithDelay(Mockito.<CompactionJobDispatchRequest>any(), anyInt());
    Supplier<Instant> timeSupplier = mock(Supplier.class);
    when(timeSupplier.get()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    CompactionJobDispatcher compactionJobDispatcher = new CompactionJobDispatcher(instanceProperties,
        tablePropertiesProvider, stateStoreProvider, new InMemoryCompactionJobTracker(), readBatch,
        mock(SendJobs.class), 3, returnToPendingQueue, mock(SendDeadLetter.class), timeSupplier);
    TableProperties tableProperties = new TableProperties(new InstanceProperties());

    // Act
    compactionJobDispatcher.dispatch(CompactionJobDispatchRequest.forTableWithBatchIdAtTime(tableProperties, "42",
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

    // Assert
    verify(timeSupplier).get();
    verify(readBatch).read(eq("Get"), eq("null/compactions/42.json"));
    verify(returnToPendingQueue).sendWithDelay(isA(CompactionJobDispatchRequest.class), eq(30));
    verify(instanceProperties).get(isA(InstanceProperty.class));
    verify(tablePropertiesProvider).getById(isNull());
    verify(stateStore).isAssigned(isA(List.class));
    verify(stateStoreFactory).getStateStore(isA(TableProperties.class));
  }

  /**
   * Test {@link CompactionJobDispatcher#dispatch(CompactionJobDispatchRequest)}.
   * <ul>
   *   <li>Given {@link TableProperties} {@link SleeperPropertyValues#getInt(SleeperProperty)} return minus one.</li>
   *   <li>Then calls {@link SleeperPropertyValues#getInt(SleeperProperty)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobDispatcher#dispatch(CompactionJobDispatchRequest)}
   */
  @Test
  @DisplayName("Test dispatch(CompactionJobDispatchRequest); given TableProperties getInt(SleeperProperty) return minus one; then calls getInt(SleeperProperty)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void CompactionJobDispatcher.dispatch(CompactionJobDispatchRequest)"})
  void testDispatch_givenTablePropertiesGetIntReturnMinusOne_thenCallsGetInt() throws StateStoreException {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.getInt(Mockito.<TableProperty>any())).thenReturn(-1);
    when(tableProperties.get(Mockito.<TableProperty>any())).thenReturn("Get");
    when(tableProperties.getStatus()).thenReturn(TableStatus.uniqueIdAndName("42", "Table Name", true));
    TablePropertiesProvider tablePropertiesProvider = mock(TablePropertiesProvider.class);
    when(tablePropertiesProvider.getById(Mockito.<String>any())).thenReturn(tableProperties);
    StateStore stateStore = mock(StateStore.class);
    when(stateStore.isAssigned(Mockito.<List<CheckFileAssignmentsRequest>>any())).thenReturn(false);
    Factory stateStoreFactory = mock(Factory.class);
    when(stateStoreFactory.getStateStore(Mockito.<TableProperties>any())).thenReturn(stateStore);
    StateStoreProvider stateStoreProvider = new StateStoreProvider(3, stateStoreFactory);

    ReadBatch readBatch = mock(ReadBatch.class);
    when(readBatch.read(Mockito.<String>any(), Mockito.<String>any())).thenReturn(new ArrayList<>());
    SendDeadLetter sendDeadLetter = mock(SendDeadLetter.class);
    doNothing().when(sendDeadLetter).send(Mockito.<CompactionJobDispatchRequest>any());
    Supplier<Instant> timeSupplier = mock(Supplier.class);
    when(timeSupplier.get()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    CompactionJobDispatcher compactionJobDispatcher = new CompactionJobDispatcher(instanceProperties,
        tablePropertiesProvider, stateStoreProvider, new InMemoryCompactionJobTracker(), readBatch,
        mock(SendJobs.class), 3, mock(ReturnRequestToPendingQueue.class), sendDeadLetter, timeSupplier);
    TableProperties tableProperties2 = new TableProperties(new InstanceProperties());

    // Act
    compactionJobDispatcher.dispatch(CompactionJobDispatchRequest.forTableWithBatchIdAtTime(tableProperties2, "42",
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

    // Assert
    verify(timeSupplier).get();
    verify(readBatch).read(eq("Get"), eq("null/compactions/42.json"));
    verify(sendDeadLetter).send(isA(CompactionJobDispatchRequest.class));
    verify(tableProperties).getInt(isA(TableProperty.class));
    verify(instanceProperties).get(isA(InstanceProperty.class));
    verify(tableProperties).get(isA(TableProperty.class));
    verify(tableProperties).getStatus();
    verify(tablePropertiesProvider).getById(isNull());
    verify(stateStore).isAssigned(isA(List.class));
    verify(stateStoreFactory).getStateStore(isA(TableProperties.class));
  }

  /**
   * Test {@link CompactionJobDispatcher#dispatch(CompactionJobDispatchRequest)}.
   * <ul>
   *   <li>Given {@link TableProperties} {@link SleeperPropertyValues#getInt(SleeperProperty)} return one.</li>
   *   <li>Then calls {@link SleeperPropertyValues#getInt(SleeperProperty)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobDispatcher#dispatch(CompactionJobDispatchRequest)}
   */
  @Test
  @DisplayName("Test dispatch(CompactionJobDispatchRequest); given TableProperties getInt(SleeperProperty) return one; then calls getInt(SleeperProperty)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void CompactionJobDispatcher.dispatch(CompactionJobDispatchRequest)"})
  void testDispatch_givenTablePropertiesGetIntReturnOne_thenCallsGetInt() throws StateStoreException {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.getInt(Mockito.<TableProperty>any())).thenReturn(1);
    when(tableProperties.get(Mockito.<TableProperty>any())).thenReturn("Get");
    when(tableProperties.getStatus()).thenReturn(TableStatus.uniqueIdAndName("42", "Table Name", true));
    TablePropertiesProvider tablePropertiesProvider = mock(TablePropertiesProvider.class);
    when(tablePropertiesProvider.getById(Mockito.<String>any())).thenReturn(tableProperties);
    StateStore stateStore = mock(StateStore.class);
    when(stateStore.isAssigned(Mockito.<List<CheckFileAssignmentsRequest>>any())).thenReturn(false);
    Factory stateStoreFactory = mock(Factory.class);
    when(stateStoreFactory.getStateStore(Mockito.<TableProperties>any())).thenReturn(stateStore);
    StateStoreProvider stateStoreProvider = new StateStoreProvider(3, stateStoreFactory);

    ReadBatch readBatch = mock(ReadBatch.class);
    when(readBatch.read(Mockito.<String>any(), Mockito.<String>any())).thenReturn(new ArrayList<>());
    ReturnRequestToPendingQueue returnToPendingQueue = mock(ReturnRequestToPendingQueue.class);
    Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference fileReference = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    doThrow(new FileReferenceAssignedToJobException(fileReference)).when(returnToPendingQueue)
        .sendWithDelay(Mockito.<CompactionJobDispatchRequest>any(), anyInt());
    SendDeadLetter sendDeadLetter = mock(SendDeadLetter.class);
    doNothing().when(sendDeadLetter).send(Mockito.<CompactionJobDispatchRequest>any());
    Supplier<Instant> timeSupplier = mock(Supplier.class);
    when(timeSupplier.get()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    CompactionJobDispatcher compactionJobDispatcher = new CompactionJobDispatcher(instanceProperties,
        tablePropertiesProvider, stateStoreProvider, new InMemoryCompactionJobTracker(), readBatch,
        mock(SendJobs.class), 3, returnToPendingQueue, sendDeadLetter, timeSupplier);
    TableProperties tableProperties2 = new TableProperties(new InstanceProperties());

    // Act
    compactionJobDispatcher.dispatch(CompactionJobDispatchRequest.forTableWithBatchIdAtTime(tableProperties2, "42",
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

    // Assert
    verify(timeSupplier).get();
    verify(readBatch).read(eq("Get"), eq("null/compactions/42.json"));
    verify(returnToPendingQueue).sendWithDelay(isA(CompactionJobDispatchRequest.class), eq(1));
    verify(sendDeadLetter).send(isA(CompactionJobDispatchRequest.class));
    verify(tableProperties, atLeast(1)).getInt(Mockito.<TableProperty>any());
    verify(instanceProperties).get(isA(InstanceProperty.class));
    verify(tableProperties).get(isA(TableProperty.class));
    verify(tableProperties).getStatus();
    verify(tablePropertiesProvider).getById(isNull());
    verify(stateStore).isAssigned(isA(List.class));
    verify(stateStoreFactory).getStateStore(isA(TableProperties.class));
  }

  /**
   * Test {@link CompactionJobDispatcher#dispatch(CompactionJobDispatchRequest)}.
   * <ul>
   *   <li>Given {@link TableProperties} {@link SleeperProperties#validate()} does nothing.</li>
   *   <li>Then calls {@link SleeperProperties#validate()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobDispatcher#dispatch(CompactionJobDispatchRequest)}
   */
  @Test
  @DisplayName("Test dispatch(CompactionJobDispatchRequest); given TableProperties validate() does nothing; then calls validate()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void CompactionJobDispatcher.dispatch(CompactionJobDispatchRequest)"})
  void testDispatch_givenTablePropertiesValidateDoesNothing_thenCallsValidate()
      throws SleeperPropertiesInvalidException, StateStoreException {
    // Arrange
    InMemoryTableIndex tableIndex = mock(InMemoryTableIndex.class);
    Optional<TableStatus> ofResult = Optional.of(TableStatus.uniqueIdAndName("42", "Table Name", true));
    when(tableIndex.getTableByUniqueId(Mockito.<String>any())).thenReturn(ofResult);
    TableProperties tableProperties = mock(TableProperties.class);
    doNothing().when(tableProperties).validate();
    when(tableProperties.get(Mockito.<TableProperty>any())).thenReturn("Get");
    when(tableProperties.getStatus()).thenReturn(TableStatus.uniqueIdAndName("42", "Table Name", true));
    Client client = mock(Client.class);
    when(client.loadProperties(Mockito.<TableStatus>any())).thenReturn(tableProperties);
    TablePropertiesStore propertiesStore = new TablePropertiesStore(tableIndex, client);

    TablePropertiesProvider tablePropertiesProvider = new TablePropertiesProvider(new InstanceProperties(),
        propertiesStore);

    StateStore stateStore = mock(StateStore.class);
    when(stateStore.isAssigned(Mockito.<List<CheckFileAssignmentsRequest>>any())).thenReturn(true);
    Factory stateStoreFactory = mock(Factory.class);
    when(stateStoreFactory.getStateStore(Mockito.<TableProperties>any())).thenReturn(stateStore);
    StateStoreProvider stateStoreProvider = new StateStoreProvider(3, stateStoreFactory);

    ReadBatch readBatch = mock(ReadBatch.class);
    when(readBatch.read(Mockito.<String>any(), Mockito.<String>any())).thenReturn(new ArrayList<>());
    InstanceProperties instanceProperties = new InstanceProperties();
    CompactionJobDispatcher compactionJobDispatcher = new CompactionJobDispatcher(instanceProperties,
        tablePropertiesProvider, stateStoreProvider, new InMemoryCompactionJobTracker(), readBatch,
        mock(SendJobs.class), 3, mock(ReturnRequestToPendingQueue.class), mock(SendDeadLetter.class),
        mock(Supplier.class));
    TableProperties tableProperties2 = new TableProperties(new InstanceProperties());

    // Act
    compactionJobDispatcher.dispatch(CompactionJobDispatchRequest.forTableWithBatchIdAtTime(tableProperties2, "42",
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

    // Assert
    verify(readBatch).read(isNull(), eq("null/compactions/42.json"));
    verify(tableProperties).validate();
    verify(tableProperties, atLeast(1)).get(Mockito.<TableProperty>any());
    verify(tableProperties, atLeast(1)).getStatus();
    verify(client).loadProperties(isA(TableStatus.class));
    verify(stateStore).isAssigned(isA(List.class));
    verify(stateStoreFactory).getStateStore(isA(TableProperties.class));
    verify(tableIndex).getTableByUniqueId(isNull());
  }

  /**
   * Test {@link CompactionJobDispatcher#dispatch(CompactionJobDispatchRequest)}.
   * <ul>
   *   <li>Then calls {@link StateStoreProvider#getStateStore(TableProperties)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobDispatcher#dispatch(CompactionJobDispatchRequest)}
   */
  @Test
  @DisplayName("Test dispatch(CompactionJobDispatchRequest); then calls getStateStore(TableProperties)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void CompactionJobDispatcher.dispatch(CompactionJobDispatchRequest)"})
  void testDispatch_thenCallsGetStateStore() throws StateStoreException {
    // Arrange
    Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference fileReference = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    new FileReferenceAssignedToJobException(fileReference);
    Builder jobIdResult2 = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference fileReference2 = jobIdResult2
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    new FileReferenceAssignedToJobException(fileReference2);
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.getStatus()).thenReturn(TableStatus.uniqueIdAndName("42", "Table Name", true));
    TablePropertiesProvider tablePropertiesProvider = mock(TablePropertiesProvider.class);
    when(tablePropertiesProvider.getById(Mockito.<String>any())).thenReturn(tableProperties);
    StateStore stateStore = mock(StateStore.class);
    when(stateStore.isAssigned(Mockito.<List<CheckFileAssignmentsRequest>>any())).thenReturn(true);
    StateStoreProvider stateStoreProvider = mock(StateStoreProvider.class);
    when(stateStoreProvider.getStateStore(Mockito.<TableProperties>any())).thenReturn(stateStore);
    ReadBatch readBatch = mock(ReadBatch.class);
    when(readBatch.read(Mockito.<String>any(), Mockito.<String>any())).thenReturn(new ArrayList<>());
    CompactionJobDispatcher compactionJobDispatcher = new CompactionJobDispatcher(instanceProperties,
        tablePropertiesProvider, stateStoreProvider, new InMemoryCompactionJobTracker(), readBatch,
        mock(SendJobs.class), 3, mock(ReturnRequestToPendingQueue.class), mock(SendDeadLetter.class),
        mock(Supplier.class));
    TableProperties tableProperties2 = new TableProperties(new InstanceProperties());

    // Act
    compactionJobDispatcher.dispatch(CompactionJobDispatchRequest.forTableWithBatchIdAtTime(tableProperties2, "42",
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

    // Assert
    verify(readBatch).read(eq("Get"), eq("null/compactions/42.json"));
    verify(instanceProperties).get(isA(InstanceProperty.class));
    verify(tableProperties).getStatus();
    verify(tablePropertiesProvider).getById(isNull());
    verify(stateStore).isAssigned(isA(List.class));
    verify(stateStoreProvider).getStateStore(isA(TableProperties.class));
  }

  /**
   * Test {@link CompactionJobDispatcher#dispatch(CompactionJobDispatchRequest)}.
   * <ul>
   *   <li>Then calls {@link TablePropertiesStore#loadById(String)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobDispatcher#dispatch(CompactionJobDispatchRequest)}
   */
  @Test
  @DisplayName("Test dispatch(CompactionJobDispatchRequest); then calls loadById(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void CompactionJobDispatcher.dispatch(CompactionJobDispatchRequest)"})
  void testDispatch_thenCallsLoadById() throws StateStoreException {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    TablePropertiesStore propertiesStore = mock(TablePropertiesStore.class);
    when(propertiesStore.loadById(Mockito.<String>any())).thenReturn(new TableProperties(new InstanceProperties()));
    TablePropertiesProvider tablePropertiesProvider = new TablePropertiesProvider(new InstanceProperties(),
        propertiesStore);

    StateStore stateStore = mock(StateStore.class);
    when(stateStore.isAssigned(Mockito.<List<CheckFileAssignmentsRequest>>any())).thenReturn(true);
    Factory stateStoreFactory = mock(Factory.class);
    when(stateStoreFactory.getStateStore(Mockito.<TableProperties>any())).thenReturn(stateStore);
    StateStoreProvider stateStoreProvider = new StateStoreProvider(3, stateStoreFactory);

    ReadBatch readBatch = mock(ReadBatch.class);
    when(readBatch.read(Mockito.<String>any(), Mockito.<String>any())).thenReturn(new ArrayList<>());
    CompactionJobDispatcher compactionJobDispatcher = new CompactionJobDispatcher(instanceProperties,
        tablePropertiesProvider, stateStoreProvider, new InMemoryCompactionJobTracker(), readBatch,
        mock(SendJobs.class), 3, mock(ReturnRequestToPendingQueue.class), mock(SendDeadLetter.class),
        mock(Supplier.class));
    TableProperties tableProperties = new TableProperties(new InstanceProperties());

    // Act
    compactionJobDispatcher.dispatch(CompactionJobDispatchRequest.forTableWithBatchIdAtTime(tableProperties, "42",
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

    // Assert
    verify(readBatch).read(eq("Get"), eq("null/compactions/42.json"));
    verify(instanceProperties).get(isA(InstanceProperty.class));
    verify(propertiesStore).loadById(isNull());
    verify(stateStore).isAssigned(isA(List.class));
    verify(stateStoreFactory).getStateStore(isA(TableProperties.class));
  }

  /**
   * Test {@link CompactionJobDispatcher#dispatch(CompactionJobDispatchRequest)}.
   * <ul>
   *   <li>Then calls {@link SendJobs#send(List)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobDispatcher#dispatch(CompactionJobDispatchRequest)}
   */
  @Test
  @DisplayName("Test dispatch(CompactionJobDispatchRequest); then calls send(List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void CompactionJobDispatcher.dispatch(CompactionJobDispatchRequest)"})
  void testDispatch_thenCallsSend() throws StateStoreException {
    // Arrange
    Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference fileReference = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    new FileReferenceAssignedToJobException(fileReference);
    Builder jobIdResult2 = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference fileReference2 = jobIdResult2
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    new FileReferenceAssignedToJobException(fileReference2);
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.getStatus()).thenReturn(TableStatus.uniqueIdAndName("42", "Table Name", true));
    TablePropertiesProvider tablePropertiesProvider = mock(TablePropertiesProvider.class);
    when(tablePropertiesProvider.getById(Mockito.<String>any())).thenReturn(tableProperties);
    StateStore stateStore = mock(StateStore.class);
    when(stateStore.isAssigned(Mockito.<List<CheckFileAssignmentsRequest>>any())).thenReturn(true);
    StateStoreProvider stateStoreProvider = mock(StateStoreProvider.class);
    when(stateStoreProvider.getStateStore(Mockito.<TableProperties>any())).thenReturn(stateStore);

    ArrayList<CompactionJob> compactionJobList = new ArrayList<>();
    CompactionJob.Builder builderResult = CompactionJob.builder();
    CompactionJob buildResult = builderResult.inputFiles(new ArrayList<>())
        .jobId("42")
        .outputFile("Output File")
        .partitionId("42")
        .tableId("42")
        .build();
    compactionJobList.add(buildResult);
    ReadBatch readBatch = mock(ReadBatch.class);
    when(readBatch.read(Mockito.<String>any(), Mockito.<String>any())).thenReturn(compactionJobList);
    SendJobs sendJobs = mock(SendJobs.class);
    doNothing().when(sendJobs).send(Mockito.<List<CompactionJob>>any());
    CompactionJobDispatcher compactionJobDispatcher = new CompactionJobDispatcher(instanceProperties,
        tablePropertiesProvider, stateStoreProvider, new InMemoryCompactionJobTracker(), readBatch, sendJobs, 3,
        mock(ReturnRequestToPendingQueue.class), mock(SendDeadLetter.class), mock(Supplier.class));
    TableProperties tableProperties2 = new TableProperties(new InstanceProperties());

    // Act
    compactionJobDispatcher.dispatch(CompactionJobDispatchRequest.forTableWithBatchIdAtTime(tableProperties2, "42",
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

    // Assert
    verify(readBatch).read(eq("Get"), eq("null/compactions/42.json"));
    verify(sendJobs).send(isA(List.class));
    verify(instanceProperties).get(isA(InstanceProperty.class));
    verify(tableProperties).getStatus();
    verify(tablePropertiesProvider).getById(isNull());
    verify(stateStore).isAssigned(isA(List.class));
    verify(stateStoreProvider).getStateStore(isA(TableProperties.class));
  }

  /**
   * Test {@link CompactionJobDispatcher#dispatch(CompactionJobDispatchRequest)}.
   * <ul>
   *   <li>Then calls {@link ReturnRequestToPendingQueue#sendWithDelay(CompactionJobDispatchRequest, int)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobDispatcher#dispatch(CompactionJobDispatchRequest)}
   */
  @Test
  @DisplayName("Test dispatch(CompactionJobDispatchRequest); then calls sendWithDelay(CompactionJobDispatchRequest, int)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void CompactionJobDispatcher.dispatch(CompactionJobDispatchRequest)"})
  void testDispatch_thenCallsSendWithDelay() throws StateStoreException {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    TablePropertiesProvider tablePropertiesProvider = mock(TablePropertiesProvider.class);
    when(tablePropertiesProvider.getById(Mockito.<String>any()))
        .thenReturn(new TableProperties(new InstanceProperties()));
    StateStore stateStore = mock(StateStore.class);
    when(stateStore.isAssigned(Mockito.<List<CheckFileAssignmentsRequest>>any())).thenReturn(false);
    Factory stateStoreFactory = mock(Factory.class);
    when(stateStoreFactory.getStateStore(Mockito.<TableProperties>any())).thenReturn(stateStore);
    StateStoreProvider stateStoreProvider = new StateStoreProvider(3, stateStoreFactory);

    ReadBatch readBatch = mock(ReadBatch.class);
    when(readBatch.read(Mockito.<String>any(), Mockito.<String>any())).thenReturn(new ArrayList<>());
    ReturnRequestToPendingQueue returnToPendingQueue = mock(ReturnRequestToPendingQueue.class);
    Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference fileReference = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    doThrow(new FileReferenceAssignedToJobException(fileReference)).when(returnToPendingQueue)
        .sendWithDelay(Mockito.<CompactionJobDispatchRequest>any(), anyInt());
    SendDeadLetter sendDeadLetter = mock(SendDeadLetter.class);
    doNothing().when(sendDeadLetter).send(Mockito.<CompactionJobDispatchRequest>any());
    Supplier<Instant> timeSupplier = mock(Supplier.class);
    when(timeSupplier.get()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    CompactionJobDispatcher compactionJobDispatcher = new CompactionJobDispatcher(instanceProperties,
        tablePropertiesProvider, stateStoreProvider, new InMemoryCompactionJobTracker(), readBatch,
        mock(SendJobs.class), 3, returnToPendingQueue, sendDeadLetter, timeSupplier);
    TableProperties tableProperties = new TableProperties(new InstanceProperties());

    // Act
    compactionJobDispatcher.dispatch(CompactionJobDispatchRequest.forTableWithBatchIdAtTime(tableProperties, "42",
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

    // Assert
    verify(timeSupplier).get();
    verify(readBatch).read(eq("Get"), eq("null/compactions/42.json"));
    verify(returnToPendingQueue).sendWithDelay(isA(CompactionJobDispatchRequest.class), eq(30));
    verify(sendDeadLetter).send(isA(CompactionJobDispatchRequest.class));
    verify(instanceProperties).get(isA(InstanceProperty.class));
    verify(tablePropertiesProvider).getById(isNull());
    verify(stateStore).isAssigned(isA(List.class));
    verify(stateStoreFactory).getStateStore(isA(TableProperties.class));
  }

  /**
   * Test {@link CompactionJobDispatcher#dispatch(CompactionJobDispatchRequest)}.
   * <ul>
   *   <li>Then calls {@link SleeperProperties#validate()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobDispatcher#dispatch(CompactionJobDispatchRequest)}
   */
  @Test
  @DisplayName("Test dispatch(CompactionJobDispatchRequest); then calls validate()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void CompactionJobDispatcher.dispatch(CompactionJobDispatchRequest)"})
  void testDispatch_thenCallsValidate() throws SleeperPropertiesInvalidException, StateStoreException {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    InMemoryTableIndex tableIndex = mock(InMemoryTableIndex.class);
    Optional<TableStatus> ofResult = Optional.of(TableStatus.uniqueIdAndName("42", "Table Name", true));
    when(tableIndex.getTableByUniqueId(Mockito.<String>any())).thenReturn(ofResult);
    TableProperties tableProperties = mock(TableProperties.class);
    doNothing().when(tableProperties).validate();
    when(tableProperties.get(Mockito.<TableProperty>any())).thenReturn("Get");
    when(tableProperties.getStatus()).thenReturn(TableStatus.uniqueIdAndName("42", "Table Name", true));
    Client client = mock(Client.class);
    when(client.loadProperties(Mockito.<TableStatus>any())).thenReturn(tableProperties);
    TablePropertiesStore propertiesStore = new TablePropertiesStore(tableIndex, client);

    TablePropertiesProvider tablePropertiesProvider = new TablePropertiesProvider(new InstanceProperties(),
        propertiesStore);

    StateStore stateStore = mock(StateStore.class);
    when(stateStore.isAssigned(Mockito.<List<CheckFileAssignmentsRequest>>any())).thenReturn(true);
    Factory stateStoreFactory = mock(Factory.class);
    when(stateStoreFactory.getStateStore(Mockito.<TableProperties>any())).thenReturn(stateStore);
    StateStoreProvider stateStoreProvider = new StateStoreProvider(3, stateStoreFactory);

    ReadBatch readBatch = mock(ReadBatch.class);
    when(readBatch.read(Mockito.<String>any(), Mockito.<String>any())).thenReturn(new ArrayList<>());
    CompactionJobDispatcher compactionJobDispatcher = new CompactionJobDispatcher(instanceProperties,
        tablePropertiesProvider, stateStoreProvider, new InMemoryCompactionJobTracker(), readBatch,
        mock(SendJobs.class), 3, mock(ReturnRequestToPendingQueue.class), mock(SendDeadLetter.class),
        mock(Supplier.class));
    TableProperties tableProperties2 = new TableProperties(new InstanceProperties());

    // Act
    compactionJobDispatcher.dispatch(CompactionJobDispatchRequest.forTableWithBatchIdAtTime(tableProperties2, "42",
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

    // Assert
    verify(readBatch).read(eq("Get"), eq("null/compactions/42.json"));
    verify(tableProperties).validate();
    verify(instanceProperties).get(isA(InstanceProperty.class));
    verify(tableProperties, atLeast(1)).get(Mockito.<TableProperty>any());
    verify(tableProperties, atLeast(1)).getStatus();
    verify(client).loadProperties(isA(TableStatus.class));
    verify(stateStore).isAssigned(isA(List.class));
    verify(stateStoreFactory).getStateStore(isA(TableProperties.class));
    verify(tableIndex).getTableByUniqueId(isNull());
  }
}
