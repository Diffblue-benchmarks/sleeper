package sleeper.bulkimport.runner;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
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
import java.util.HashMap;
import java.util.Optional;
import java.util.function.Supplier;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.bulkimport.core.job.BulkImportJob;
import sleeper.bulkimport.core.job.BulkImportJob.Builder;
import sleeper.bulkimport.runner.BulkImportJobDriver.SessionRunner;
import sleeper.core.properties.SleeperPropertiesInvalidException;
import sleeper.core.properties.SleeperPropertyValues;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.table.TableProperties;
import sleeper.core.properties.table.TablePropertiesProvider;
import sleeper.core.properties.table.TablePropertiesStore;
import sleeper.core.properties.table.TablePropertiesStore.Client;
import sleeper.core.properties.table.TableProperty;
import sleeper.core.statestore.FileReference;
import sleeper.core.statestore.StateStore;
import sleeper.core.statestore.StateStoreProvider;
import sleeper.core.statestore.StateStoreProvider.Factory;
import sleeper.core.statestore.commit.StateStoreCommitRequest;
import sleeper.core.statestore.commit.StateStoreCommitRequestSender;
import sleeper.core.statestore.transactionlog.AddTransactionRequest;
import sleeper.core.table.InMemoryTableIndex;
import sleeper.core.table.TableStatus;
import sleeper.core.tracker.ingest.job.InMemoryIngestJobTracker;

class BulkImportJobDriverDiffblueTest {
  /**
   * Test {@link BulkImportJobDriver#run(BulkImportJob, String, String)}.
   * <p>
   * Method under test: {@link BulkImportJobDriver#run(BulkImportJob, String, String)}
   */
  @Test
  @DisplayName("Test run(BulkImportJob, String, String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void BulkImportJobDriver.run(BulkImportJob, String, String)"})
  void testRun() throws IOException, SleeperPropertiesInvalidException {
    // Arrange
    SessionRunner sessionRunner = mock(SessionRunner.class);
    when(sessionRunner.run(Mockito.<BulkImportJob>any()))
        .thenReturn(new BulkImportJobOutput(new ArrayList<>(), mock(Runnable.class)));
    InMemoryTableIndex tableIndex = mock(InMemoryTableIndex.class);
    Optional<TableStatus> ofResult = Optional.of(TableStatus.uniqueIdAndName("42", "Table Name", true));
    when(tableIndex.getTableByName(Mockito.<String>any())).thenReturn(ofResult);
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.getBoolean(Mockito.<TableProperty>any())).thenReturn(true);
    doNothing().when(tableProperties).validate();
    when(tableProperties.get(Mockito.<TableProperty>any())).thenReturn("Get");
    when(tableProperties.getStatus()).thenReturn(TableStatus.uniqueIdAndName("42", "Table Name", true));
    Client client = mock(Client.class);
    when(client.loadProperties(Mockito.<TableStatus>any())).thenReturn(tableProperties);
    TablePropertiesStore propertiesStore = new TablePropertiesStore(tableIndex, client);

    TablePropertiesProvider tablePropertiesProvider = new TablePropertiesProvider(new InstanceProperties(),
        propertiesStore);

    StateStoreCommitRequestSender asyncSender = mock(StateStoreCommitRequestSender.class);
    doThrow(new RuntimeException("Received bulk import job at time {}, {}")).when(asyncSender)
        .send(Mockito.<StateStoreCommitRequest>any());
    Supplier<Instant> getTime = mock(Supplier.class);
    when(getTime.get()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    StateStoreProvider stateStoreProvider = new StateStoreProvider(3, mock(Factory.class));

    BulkImportJobDriver bulkImportJobDriver = new BulkImportJobDriver(sessionRunner, tablePropertiesProvider,
        stateStoreProvider, new InMemoryIngestJobTracker(), asyncSender, getTime);
    Builder classNameResult = BulkImportJob.builder().className("Class Name");
    Builder idResult = classNameResult.files(new ArrayList<>()).id("42");
    BulkImportJob job = idResult.platformSpec(new HashMap<>())
        .sparkConf("Key", "42")
        .tableId("42")
        .tableName("Table Name")
        .build();

    // Act and Assert
    assertThrows(RuntimeException.class, () -> bulkImportJobDriver.run(job, "42", "42"));
    verify(getTime, atLeast(1)).get();
    verify(sessionRunner).run(isA(BulkImportJob.class));
    verify(tableProperties).validate();
    verify(tableProperties).getBoolean(isA(TableProperty.class));
    verify(tableProperties, atLeast(1)).get(Mockito.<TableProperty>any());
    verify(tableProperties, atLeast(1)).getStatus();
    verify(client).loadProperties(isA(TableStatus.class));
    verify(asyncSender).send(isA(StateStoreCommitRequest.class));
    verify(tableIndex).getTableByName(eq("Table Name"));
  }

  /**
   * Test {@link BulkImportJobDriver#run(BulkImportJob, String, String)}.
   * <p>
   * Method under test: {@link BulkImportJobDriver#run(BulkImportJob, String, String)}
   */
  @Test
  @DisplayName("Test run(BulkImportJob, String, String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void BulkImportJobDriver.run(BulkImportJob, String, String)"})
  void testRun2() throws IOException, SleeperPropertiesInvalidException {
    // Arrange
    ArrayList<FileReference> fileReferences = new ArrayList<>();
    FileReference.Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference buildResult = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    fileReferences.add(buildResult);
    Runnable stopSparkContext = mock(Runnable.class);
    doNothing().when(stopSparkContext).run();
    SessionRunner sessionRunner = mock(SessionRunner.class);
    when(sessionRunner.run(Mockito.<BulkImportJob>any()))
        .thenReturn(new BulkImportJobOutput(fileReferences, stopSparkContext));
    InMemoryTableIndex tableIndex = mock(InMemoryTableIndex.class);
    Optional<TableStatus> ofResult = Optional.of(TableStatus.uniqueIdAndName("42", "Table Name", true));
    when(tableIndex.getTableByName(Mockito.<String>any())).thenReturn(ofResult);
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.getBoolean(Mockito.<TableProperty>any())).thenReturn(true);
    doNothing().when(tableProperties).validate();
    when(tableProperties.get(Mockito.<TableProperty>any())).thenReturn("Get");
    when(tableProperties.getStatus()).thenReturn(TableStatus.uniqueIdAndName("42", "Table Name", true));
    Client client = mock(Client.class);
    when(client.loadProperties(Mockito.<TableStatus>any())).thenReturn(tableProperties);
    TablePropertiesStore propertiesStore = new TablePropertiesStore(tableIndex, client);

    TablePropertiesProvider tablePropertiesProvider = new TablePropertiesProvider(new InstanceProperties(),
        propertiesStore);

    StateStoreCommitRequestSender asyncSender = mock(StateStoreCommitRequestSender.class);
    doNothing().when(asyncSender).send(Mockito.<StateStoreCommitRequest>any());
    Supplier<Instant> getTime = mock(Supplier.class);
    when(getTime.get()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    StateStoreProvider stateStoreProvider = new StateStoreProvider(3, mock(Factory.class));

    BulkImportJobDriver bulkImportJobDriver = new BulkImportJobDriver(sessionRunner, tablePropertiesProvider,
        stateStoreProvider, new InMemoryIngestJobTracker(), asyncSender, getTime);
    Builder classNameResult = BulkImportJob.builder().className("Class Name");
    Builder idResult = classNameResult.files(new ArrayList<>()).id("42");
    BulkImportJob job = idResult.platformSpec(new HashMap<>())
        .sparkConf("Key", "42")
        .tableId("42")
        .tableName("Table Name")
        .build();

    // Act
    bulkImportJobDriver.run(job, "42", "42");

    // Assert
    verify(stopSparkContext).run();
    verify(getTime, atLeast(1)).get();
    verify(sessionRunner).run(isA(BulkImportJob.class));
    verify(tableProperties).validate();
    verify(tableProperties).getBoolean(isA(TableProperty.class));
    verify(tableProperties, atLeast(1)).get(Mockito.<TableProperty>any());
    verify(tableProperties, atLeast(1)).getStatus();
    verify(client).loadProperties(isA(TableStatus.class));
    verify(asyncSender).send(isA(StateStoreCommitRequest.class));
    verify(tableIndex).getTableByName(eq("Table Name"));
  }

  /**
   * Test {@link BulkImportJobDriver#run(BulkImportJob, String, String)}.
   * <p>
   * Method under test: {@link BulkImportJobDriver#run(BulkImportJob, String, String)}
   */
  @Test
  @DisplayName("Test run(BulkImportJob, String, String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void BulkImportJobDriver.run(BulkImportJob, String, String)"})
  void testRun3() throws IOException, SleeperPropertiesInvalidException {
    // Arrange
    ArrayList<FileReference> fileReferences = new ArrayList<>();
    FileReference.Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference buildResult = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    fileReferences.add(buildResult);
    FileReference.Builder jobIdResult2 = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference buildResult2 = jobIdResult2
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    fileReferences.add(buildResult2);
    Runnable stopSparkContext = mock(Runnable.class);
    doNothing().when(stopSparkContext).run();
    SessionRunner sessionRunner = mock(SessionRunner.class);
    when(sessionRunner.run(Mockito.<BulkImportJob>any()))
        .thenReturn(new BulkImportJobOutput(fileReferences, stopSparkContext));
    InMemoryTableIndex tableIndex = mock(InMemoryTableIndex.class);
    Optional<TableStatus> ofResult = Optional.of(TableStatus.uniqueIdAndName("42", "Table Name", true));
    when(tableIndex.getTableByName(Mockito.<String>any())).thenReturn(ofResult);
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.getBoolean(Mockito.<TableProperty>any())).thenReturn(true);
    doNothing().when(tableProperties).validate();
    when(tableProperties.get(Mockito.<TableProperty>any())).thenReturn("Get");
    when(tableProperties.getStatus()).thenReturn(TableStatus.uniqueIdAndName("42", "Table Name", true));
    Client client = mock(Client.class);
    when(client.loadProperties(Mockito.<TableStatus>any())).thenReturn(tableProperties);
    TablePropertiesStore propertiesStore = new TablePropertiesStore(tableIndex, client);

    TablePropertiesProvider tablePropertiesProvider = new TablePropertiesProvider(new InstanceProperties(),
        propertiesStore);

    StateStoreCommitRequestSender asyncSender = mock(StateStoreCommitRequestSender.class);
    doNothing().when(asyncSender).send(Mockito.<StateStoreCommitRequest>any());
    Supplier<Instant> getTime = mock(Supplier.class);
    when(getTime.get()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    StateStoreProvider stateStoreProvider = new StateStoreProvider(3, mock(Factory.class));

    BulkImportJobDriver bulkImportJobDriver = new BulkImportJobDriver(sessionRunner, tablePropertiesProvider,
        stateStoreProvider, new InMemoryIngestJobTracker(), asyncSender, getTime);
    Builder classNameResult = BulkImportJob.builder().className("Class Name");
    Builder idResult = classNameResult.files(new ArrayList<>()).id("42");
    BulkImportJob job = idResult.platformSpec(new HashMap<>())
        .sparkConf("Key", "42")
        .tableId("42")
        .tableName("Table Name")
        .build();

    // Act
    bulkImportJobDriver.run(job, "42", "42");

    // Assert
    verify(stopSparkContext).run();
    verify(getTime, atLeast(1)).get();
    verify(sessionRunner).run(isA(BulkImportJob.class));
    verify(tableProperties).validate();
    verify(tableProperties).getBoolean(isA(TableProperty.class));
    verify(tableProperties, atLeast(1)).get(Mockito.<TableProperty>any());
    verify(tableProperties, atLeast(1)).getStatus();
    verify(client).loadProperties(isA(TableStatus.class));
    verify(asyncSender).send(isA(StateStoreCommitRequest.class));
    verify(tableIndex).getTableByName(eq("Table Name"));
  }

  /**
   * Test {@link BulkImportJobDriver#run(BulkImportJob, String, String)}.
   * <p>
   * Method under test: {@link BulkImportJobDriver#run(BulkImportJob, String, String)}
   */
  @Test
  @DisplayName("Test run(BulkImportJob, String, String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void BulkImportJobDriver.run(BulkImportJob, String, String)"})
  void testRun4() throws IOException, SleeperPropertiesInvalidException {
    // Arrange
    BulkImportJobOutput bulkImportJobOutput = mock(BulkImportJobOutput.class);
    when(bulkImportJobOutput.fileReferences()).thenReturn(new ArrayList<>());
    SessionRunner sessionRunner = mock(SessionRunner.class);
    when(sessionRunner.run(Mockito.<BulkImportJob>any())).thenReturn(bulkImportJobOutput);
    InMemoryTableIndex tableIndex = mock(InMemoryTableIndex.class);
    Optional<TableStatus> ofResult = Optional.of(TableStatus.uniqueIdAndName("42", "Table Name", true));
    when(tableIndex.getTableByName(Mockito.<String>any())).thenReturn(ofResult);
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.getBoolean(Mockito.<TableProperty>any())).thenReturn(false);
    doNothing().when(tableProperties).validate();
    when(tableProperties.get(Mockito.<TableProperty>any())).thenReturn("Get");
    when(tableProperties.getStatus()).thenReturn(TableStatus.uniqueIdAndName("42", "Table Name", true));
    Client client = mock(Client.class);
    when(client.loadProperties(Mockito.<TableStatus>any())).thenReturn(tableProperties);
    TablePropertiesStore propertiesStore = new TablePropertiesStore(tableIndex, client);

    TablePropertiesProvider tablePropertiesProvider = new TablePropertiesProvider(new InstanceProperties(),
        propertiesStore);

    StateStore stateStore = mock(StateStore.class);
    doThrow(new RuntimeException("Received bulk import job at time {}, {}")).when(stateStore)
        .addTransaction(Mockito.<AddTransactionRequest>any());
    Factory stateStoreFactory = mock(Factory.class);
    when(stateStoreFactory.getStateStore(Mockito.<TableProperties>any())).thenReturn(stateStore);
    StateStoreProvider stateStoreProvider = new StateStoreProvider(3, stateStoreFactory);

    Supplier<Instant> getTime = mock(Supplier.class);
    when(getTime.get()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    BulkImportJobDriver bulkImportJobDriver = new BulkImportJobDriver(sessionRunner, tablePropertiesProvider,
        stateStoreProvider, new InMemoryIngestJobTracker(), mock(StateStoreCommitRequestSender.class), getTime);
    Builder classNameResult = BulkImportJob.builder().className("Class Name");
    Builder idResult = classNameResult.files(new ArrayList<>()).id("42");
    BulkImportJob job = idResult.platformSpec(new HashMap<>())
        .sparkConf("Key", "42")
        .tableId("42")
        .tableName("Table Name")
        .build();

    // Act and Assert
    assertThrows(RuntimeException.class, () -> bulkImportJobDriver.run(job, "42", "42"));
    verify(getTime, atLeast(1)).get();
    verify(sessionRunner).run(isA(BulkImportJob.class));
    verify(bulkImportJobOutput).fileReferences();
    verify(tableProperties).validate();
    verify(tableProperties).getBoolean(isA(TableProperty.class));
    verify(tableProperties, atLeast(1)).get(Mockito.<TableProperty>any());
    verify(tableProperties, atLeast(1)).getStatus();
    verify(client).loadProperties(isA(TableStatus.class));
    verify(stateStore).addTransaction(isA(AddTransactionRequest.class));
    verify(stateStoreFactory).getStateStore(isA(TableProperties.class));
    verify(tableIndex).getTableByName(eq("Table Name"));
  }

  /**
   * Test {@link BulkImportJobDriver#run(BulkImportJob, String, String)}.
   * <ul>
   *   <li>Given {@link BulkImportJobOutput} {@link BulkImportJobOutput#numFiles()} return ten.</li>
   *   <li>Then calls {@link BulkImportJobOutput#numFiles()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BulkImportJobDriver#run(BulkImportJob, String, String)}
   */
  @Test
  @DisplayName("Test run(BulkImportJob, String, String); given BulkImportJobOutput numFiles() return ten; then calls numFiles()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void BulkImportJobDriver.run(BulkImportJob, String, String)"})
  void testRun_givenBulkImportJobOutputNumFilesReturnTen_thenCallsNumFiles()
      throws IOException, SleeperPropertiesInvalidException {
    // Arrange
    BulkImportJobOutput bulkImportJobOutput = mock(BulkImportJobOutput.class);
    when(bulkImportJobOutput.numFiles()).thenReturn(10);
    when(bulkImportJobOutput.fileReferences()).thenReturn(new ArrayList<>());
    when(bulkImportJobOutput.numRecords()).thenReturn(1L);
    doNothing().when(bulkImportJobOutput).stopSparkContext();
    SessionRunner sessionRunner = mock(SessionRunner.class);
    when(sessionRunner.run(Mockito.<BulkImportJob>any())).thenReturn(bulkImportJobOutput);
    InMemoryTableIndex tableIndex = mock(InMemoryTableIndex.class);
    Optional<TableStatus> ofResult = Optional.of(TableStatus.uniqueIdAndName("42", "Table Name", true));
    when(tableIndex.getTableByName(Mockito.<String>any())).thenReturn(ofResult);
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.getBoolean(Mockito.<TableProperty>any())).thenReturn(true);
    doNothing().when(tableProperties).validate();
    when(tableProperties.get(Mockito.<TableProperty>any())).thenReturn("Get");
    when(tableProperties.getStatus()).thenReturn(TableStatus.uniqueIdAndName("42", "Table Name", true));
    Client client = mock(Client.class);
    when(client.loadProperties(Mockito.<TableStatus>any())).thenReturn(tableProperties);
    TablePropertiesStore propertiesStore = new TablePropertiesStore(tableIndex, client);

    TablePropertiesProvider tablePropertiesProvider = new TablePropertiesProvider(new InstanceProperties(),
        propertiesStore);

    StateStoreCommitRequestSender asyncSender = mock(StateStoreCommitRequestSender.class);
    doNothing().when(asyncSender).send(Mockito.<StateStoreCommitRequest>any());
    Supplier<Instant> getTime = mock(Supplier.class);
    when(getTime.get()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    StateStoreProvider stateStoreProvider = new StateStoreProvider(3, mock(Factory.class));

    BulkImportJobDriver bulkImportJobDriver = new BulkImportJobDriver(sessionRunner, tablePropertiesProvider,
        stateStoreProvider, new InMemoryIngestJobTracker(), asyncSender, getTime);
    Builder classNameResult = BulkImportJob.builder().className("Class Name");
    Builder idResult = classNameResult.files(new ArrayList<>()).id("42");
    BulkImportJob job = idResult.platformSpec(new HashMap<>())
        .sparkConf("Key", "42")
        .tableId("42")
        .tableName("Table Name")
        .build();

    // Act
    bulkImportJobDriver.run(job, "42", "42");

    // Assert
    verify(getTime, atLeast(1)).get();
    verify(sessionRunner).run(isA(BulkImportJob.class));
    verify(bulkImportJobOutput, atLeast(1)).fileReferences();
    verify(bulkImportJobOutput).numFiles();
    verify(bulkImportJobOutput).numRecords();
    verify(bulkImportJobOutput).stopSparkContext();
    verify(tableProperties).validate();
    verify(tableProperties).getBoolean(isA(TableProperty.class));
    verify(tableProperties, atLeast(1)).get(Mockito.<TableProperty>any());
    verify(tableProperties, atLeast(1)).getStatus();
    verify(client).loadProperties(isA(TableStatus.class));
    verify(asyncSender).send(isA(StateStoreCommitRequest.class));
    verify(tableIndex).getTableByName(eq("Table Name"));
  }

  /**
   * Test {@link BulkImportJobDriver#run(BulkImportJob, String, String)}.
   * <ul>
   *   <li>Given {@link Runnable} {@link Runnable#run()} does nothing.</li>
   *   <li>Then calls {@link Runnable#run()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BulkImportJobDriver#run(BulkImportJob, String, String)}
   */
  @Test
  @DisplayName("Test run(BulkImportJob, String, String); given Runnable run() does nothing; then calls run()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void BulkImportJobDriver.run(BulkImportJob, String, String)"})
  void testRun_givenRunnableRunDoesNothing_thenCallsRun() throws IOException, SleeperPropertiesInvalidException {
    // Arrange
    Runnable stopSparkContext = mock(Runnable.class);
    doNothing().when(stopSparkContext).run();
    SessionRunner sessionRunner = mock(SessionRunner.class);
    when(sessionRunner.run(Mockito.<BulkImportJob>any()))
        .thenReturn(new BulkImportJobOutput(new ArrayList<>(), stopSparkContext));
    InMemoryTableIndex tableIndex = mock(InMemoryTableIndex.class);
    Optional<TableStatus> ofResult = Optional.of(TableStatus.uniqueIdAndName("42", "Table Name", true));
    when(tableIndex.getTableByName(Mockito.<String>any())).thenReturn(ofResult);
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.getBoolean(Mockito.<TableProperty>any())).thenReturn(true);
    doNothing().when(tableProperties).validate();
    when(tableProperties.get(Mockito.<TableProperty>any())).thenReturn("Get");
    when(tableProperties.getStatus()).thenReturn(TableStatus.uniqueIdAndName("42", "Table Name", true));
    Client client = mock(Client.class);
    when(client.loadProperties(Mockito.<TableStatus>any())).thenReturn(tableProperties);
    TablePropertiesStore propertiesStore = new TablePropertiesStore(tableIndex, client);

    TablePropertiesProvider tablePropertiesProvider = new TablePropertiesProvider(new InstanceProperties(),
        propertiesStore);

    StateStoreCommitRequestSender asyncSender = mock(StateStoreCommitRequestSender.class);
    doNothing().when(asyncSender).send(Mockito.<StateStoreCommitRequest>any());
    Supplier<Instant> getTime = mock(Supplier.class);
    when(getTime.get()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    StateStoreProvider stateStoreProvider = new StateStoreProvider(3, mock(Factory.class));

    BulkImportJobDriver bulkImportJobDriver = new BulkImportJobDriver(sessionRunner, tablePropertiesProvider,
        stateStoreProvider, new InMemoryIngestJobTracker(), asyncSender, getTime);
    Builder classNameResult = BulkImportJob.builder().className("Class Name");
    Builder idResult = classNameResult.files(new ArrayList<>()).id("42");
    BulkImportJob job = idResult.platformSpec(new HashMap<>())
        .sparkConf("Key", "42")
        .tableId("42")
        .tableName("Table Name")
        .build();

    // Act
    bulkImportJobDriver.run(job, "42", "42");

    // Assert
    verify(stopSparkContext).run();
    verify(getTime, atLeast(1)).get();
    verify(sessionRunner).run(isA(BulkImportJob.class));
    verify(tableProperties).validate();
    verify(tableProperties).getBoolean(isA(TableProperty.class));
    verify(tableProperties, atLeast(1)).get(Mockito.<TableProperty>any());
    verify(tableProperties, atLeast(1)).getStatus();
    verify(client).loadProperties(isA(TableStatus.class));
    verify(asyncSender).send(isA(StateStoreCommitRequest.class));
    verify(tableIndex).getTableByName(eq("Table Name"));
  }

  /**
   * Test {@link BulkImportJobDriver#run(BulkImportJob, String, String)}.
   * <ul>
   *   <li>Given {@link TableProperties} {@link SleeperPropertyValues#getBoolean(SleeperProperty)} return {@code false}.</li>
   *   <li>Then calls {@link BulkImportJobOutput#numFiles()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BulkImportJobDriver#run(BulkImportJob, String, String)}
   */
  @Test
  @DisplayName("Test run(BulkImportJob, String, String); given TableProperties getBoolean(SleeperProperty) return 'false'; then calls numFiles()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void BulkImportJobDriver.run(BulkImportJob, String, String)"})
  void testRun_givenTablePropertiesGetBooleanReturnFalse_thenCallsNumFiles()
      throws IOException, SleeperPropertiesInvalidException {
    // Arrange
    BulkImportJobOutput bulkImportJobOutput = mock(BulkImportJobOutput.class);
    when(bulkImportJobOutput.numFiles()).thenReturn(10);
    when(bulkImportJobOutput.fileReferences()).thenReturn(new ArrayList<>());
    when(bulkImportJobOutput.numRecords()).thenReturn(1L);
    doNothing().when(bulkImportJobOutput).stopSparkContext();
    SessionRunner sessionRunner = mock(SessionRunner.class);
    when(sessionRunner.run(Mockito.<BulkImportJob>any())).thenReturn(bulkImportJobOutput);
    InMemoryTableIndex tableIndex = mock(InMemoryTableIndex.class);
    Optional<TableStatus> ofResult = Optional.of(TableStatus.uniqueIdAndName("42", "Table Name", true));
    when(tableIndex.getTableByName(Mockito.<String>any())).thenReturn(ofResult);
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.getBoolean(Mockito.<TableProperty>any())).thenReturn(false);
    doNothing().when(tableProperties).validate();
    when(tableProperties.get(Mockito.<TableProperty>any())).thenReturn("Get");
    when(tableProperties.getStatus()).thenReturn(TableStatus.uniqueIdAndName("42", "Table Name", true));
    Client client = mock(Client.class);
    when(client.loadProperties(Mockito.<TableStatus>any())).thenReturn(tableProperties);
    TablePropertiesStore propertiesStore = new TablePropertiesStore(tableIndex, client);

    TablePropertiesProvider tablePropertiesProvider = new TablePropertiesProvider(new InstanceProperties(),
        propertiesStore);

    StateStore stateStore = mock(StateStore.class);
    doNothing().when(stateStore).addTransaction(Mockito.<AddTransactionRequest>any());
    Factory stateStoreFactory = mock(Factory.class);
    when(stateStoreFactory.getStateStore(Mockito.<TableProperties>any())).thenReturn(stateStore);
    StateStoreProvider stateStoreProvider = new StateStoreProvider(3, stateStoreFactory);

    Supplier<Instant> getTime = mock(Supplier.class);
    when(getTime.get()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    BulkImportJobDriver bulkImportJobDriver = new BulkImportJobDriver(sessionRunner, tablePropertiesProvider,
        stateStoreProvider, new InMemoryIngestJobTracker(), mock(StateStoreCommitRequestSender.class), getTime);
    Builder classNameResult = BulkImportJob.builder().className("Class Name");
    Builder idResult = classNameResult.files(new ArrayList<>()).id("42");
    BulkImportJob job = idResult.platformSpec(new HashMap<>())
        .sparkConf("Key", "42")
        .tableId("42")
        .tableName("Table Name")
        .build();

    // Act
    bulkImportJobDriver.run(job, "42", "42");

    // Assert
    verify(getTime, atLeast(1)).get();
    verify(sessionRunner).run(isA(BulkImportJob.class));
    verify(bulkImportJobOutput, atLeast(1)).fileReferences();
    verify(bulkImportJobOutput).numFiles();
    verify(bulkImportJobOutput).numRecords();
    verify(bulkImportJobOutput).stopSparkContext();
    verify(tableProperties).validate();
    verify(tableProperties).getBoolean(isA(TableProperty.class));
    verify(tableProperties, atLeast(1)).get(Mockito.<TableProperty>any());
    verify(tableProperties, atLeast(1)).getStatus();
    verify(client).loadProperties(isA(TableStatus.class));
    verify(stateStore).addTransaction(isA(AddTransactionRequest.class));
    verify(stateStoreFactory).getStateStore(isA(TableProperties.class));
    verify(tableIndex).getTableByName(eq("Table Name"));
  }

  /**
   * Test {@link BulkImportJobDriver#start(String[], BulkImportJobRunner)}.
   * <ul>
   *   <li>When array of {@link String} with {@code Args}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BulkImportJobDriver#start(String[], BulkImportJobRunner)}
   */
  @Test
  @DisplayName("Test start(String[], BulkImportJobRunner); when array of String with 'Args'; then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void BulkImportJobDriver.start(String[], BulkImportJobRunner)"})
  void testStart_whenArrayOfStringWithArgs_thenThrowIllegalArgumentException() throws Exception {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> BulkImportJobDriver.start(new String[]{"Args"}, mock(BulkImportJobRunner.class)));
  }
}
