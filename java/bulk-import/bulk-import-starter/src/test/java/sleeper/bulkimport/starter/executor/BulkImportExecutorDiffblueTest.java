package sleeper.bulkimport.starter.executor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
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
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.bulkimport.core.job.BulkImportJob;
import sleeper.bulkimport.starter.executor.BulkImportExecutor.WriteJobToBucket;
import sleeper.core.properties.SleeperPropertiesInvalidException;
import sleeper.core.properties.SleeperPropertyIndex;
import sleeper.core.properties.SleeperPropertyValues;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.instance.InstanceProperty;
import sleeper.core.properties.table.TableProperties;
import sleeper.core.properties.table.TablePropertiesProvider;
import sleeper.core.properties.table.TablePropertiesStore;
import sleeper.core.properties.table.TablePropertiesStore.Client;
import sleeper.core.properties.table.TableProperty;
import sleeper.core.statestore.StateStore;
import sleeper.core.statestore.StateStoreException;
import sleeper.core.statestore.StateStoreProvider;
import sleeper.core.statestore.StateStoreProvider.Factory;
import sleeper.core.table.InMemoryTableIndex;
import sleeper.core.table.TableIndex;
import sleeper.core.table.TableStatus;
import sleeper.core.tracker.ingest.job.InMemoryIngestJobTracker;
import sleeper.core.tracker.ingest.job.IngestJobTracker;
import sleeper.core.tracker.ingest.job.query.IngestJobRejectedStatus;
import sleeper.core.tracker.ingest.job.query.IngestJobRun;
import sleeper.core.tracker.ingest.job.query.IngestJobStatus;
import sleeper.core.tracker.ingest.job.update.IngestJobFailedEvent;
import sleeper.core.tracker.ingest.job.update.IngestJobValidatedEvent;
import sleeper.core.tracker.job.status.JobStatusUpdate;
import sleeper.ingest.core.job.IngestJob;
import sleeper.ingest.core.job.IngestJob.Builder;

class BulkImportExecutorDiffblueTest {
  /**
   * Test {@link BulkImportExecutor#BulkImportExecutor(InstanceProperties, TablePropertiesProvider, StateStoreProvider, IngestJobTracker, WriteJobToBucket, PlatformExecutor, Supplier)}.
   * <p>
   * Method under test: {@link BulkImportExecutor#BulkImportExecutor(InstanceProperties, TablePropertiesProvider, StateStoreProvider, IngestJobTracker, WriteJobToBucket, PlatformExecutor, Supplier)}
   */
  @Test
  @DisplayName("Test new BulkImportExecutor(InstanceProperties, TablePropertiesProvider, StateStoreProvider, IngestJobTracker, WriteJobToBucket, PlatformExecutor, Supplier)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void BulkImportExecutor.<init>(InstanceProperties, TablePropertiesProvider, StateStoreProvider, IngestJobTracker, WriteJobToBucket, PlatformExecutor, Supplier)"})
  void testNewBulkImportExecutor() {
    // Arrange
    InstanceProperties instanceProperties = new InstanceProperties();
    InstanceProperties instanceProperties2 = new InstanceProperties();
    TablePropertiesProvider tablePropertiesProvider = new TablePropertiesProvider(instanceProperties2,
        new TablePropertiesStore(new InMemoryTableIndex(), mock(Client.class)));

    StateStoreProvider stateStoreProvider = new StateStoreProvider(3, mock(Factory.class));

    // Act and Assert
    InstanceProperties instanceProperties3 = (new BulkImportExecutor(instanceProperties, tablePropertiesProvider,
        stateStoreProvider, new InMemoryIngestJobTracker(), mock(WriteJobToBucket.class), mock(PlatformExecutor.class),
        mock(Supplier.class))).instanceProperties;
    SleeperPropertyIndex<InstanceProperty> propertiesIndex = instanceProperties3.getPropertiesIndex();
    List<InstanceProperty> cdkDefined = propertiesIndex.getCdkDefined();
    assertEquals(131, cdkDefined.size());
    List<InstanceProperty> userDefined = propertiesIndex.getUserDefined();
    assertEquals(325, userDefined.size());
    List<InstanceProperty> all = propertiesIndex.getAll();
    assertEquals(456, all.size());
    InstanceProperty getResult = all.get(0);
    assertFalse(getResult.isEditable());
    InstanceProperty getResult2 = all.get(454);
    assertFalse(getResult2.isEditable());
    InstanceProperty getResult3 = all.get(455);
    assertFalse(getResult3.isEditable());
    InstanceProperty getResult4 = cdkDefined.get(0);
    assertFalse(getResult4.isEditable());
    InstanceProperty getResult5 = cdkDefined.get(1);
    assertFalse(getResult5.isEditable());
    assertFalse(getResult.isIncludedInBasicTemplate());
    InstanceProperty getResult6 = all.get(1);
    assertFalse(getResult6.isIncludedInBasicTemplate());
    assertFalse(getResult2.isIncludedInBasicTemplate());
    assertFalse(getResult3.isIncludedInBasicTemplate());
    assertFalse(getResult4.isIncludedInBasicTemplate());
    assertFalse(getResult5.isIncludedInBasicTemplate());
    InstanceProperty getResult7 = userDefined.get(323);
    assertFalse(getResult7.isIncludedInBasicTemplate());
    InstanceProperty getResult8 = userDefined.get(324);
    assertFalse(getResult8.isIncludedInBasicTemplate());
    assertFalse(getResult2.isIncludedInTemplate());
    assertFalse(getResult3.isIncludedInTemplate());
    assertFalse(getResult4.isIncludedInTemplate());
    assertFalse(getResult5.isIncludedInTemplate());
    assertFalse(getResult.isSetByCdk());
    assertFalse(getResult6.isSetByCdk());
    assertFalse(getResult7.isSetByCdk());
    assertFalse(getResult8.isSetByCdk());
    assertFalse(getResult2.isUserDefined());
    assertFalse(getResult3.isUserDefined());
    assertFalse(getResult4.isUserDefined());
    assertFalse(getResult5.isUserDefined());
    Stream<Entry<String, String>> unknownProperties = instanceProperties3.getUnknownProperties();
    assertTrue(unknownProperties.limit(5).collect(Collectors.toList()).isEmpty());
    assertTrue(instanceProperties3.toMap().isEmpty());
    assertTrue(instanceProperties3.getTags().isEmpty());
    assertTrue(getResult6.isEditable());
    assertTrue(getResult7.isEditable());
    assertTrue(getResult8.isEditable());
    assertTrue(getResult.isIgnoreEmptyValue());
    assertTrue(getResult6.isIgnoreEmptyValue());
    assertTrue(getResult2.isIgnoreEmptyValue());
    assertTrue(getResult3.isIgnoreEmptyValue());
    assertTrue(getResult4.isIgnoreEmptyValue());
    assertTrue(getResult5.isIgnoreEmptyValue());
    assertTrue(getResult7.isIgnoreEmptyValue());
    assertTrue(getResult8.isIgnoreEmptyValue());
    assertTrue(getResult.isIncludedInTemplate());
    assertTrue(getResult6.isIncludedInTemplate());
    assertTrue(getResult7.isIncludedInTemplate());
    assertTrue(getResult8.isIncludedInTemplate());
    assertTrue(getResult2.isSetByCdk());
    assertTrue(getResult3.isSetByCdk());
    assertTrue(getResult4.isSetByCdk());
    assertTrue(getResult5.isSetByCdk());
    assertTrue(getResult.isUserDefined());
    assertTrue(getResult6.isUserDefined());
    assertTrue(getResult7.isUserDefined());
    assertTrue(getResult8.isUserDefined());
  }

  /**
   * Test {@link BulkImportExecutor#runJob(BulkImportJob)} with {@code bulkImportJob}.
   * <p>
   * Method under test: {@link BulkImportExecutor#runJob(BulkImportJob)}
   */
  @Test
  @DisplayName("Test runJob(BulkImportJob) with 'bulkImportJob'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void BulkImportExecutor.runJob(BulkImportJob)"})
  void testRunJobWithBulkImportJob() {
    // Arrange
    InstanceProperties instanceProperties = new InstanceProperties();
    InstanceProperties instanceProperties2 = new InstanceProperties();
    TablePropertiesProvider tablePropertiesProvider = new TablePropertiesProvider(instanceProperties2,
        new TablePropertiesStore(new InMemoryTableIndex(), mock(Client.class)));

    StateStoreProvider stateStoreProvider = new StateStoreProvider(3, mock(Factory.class));

    BulkImportExecutor bulkImportExecutor = new BulkImportExecutor(instanceProperties, tablePropertiesProvider,
        stateStoreProvider, new InMemoryIngestJobTracker(), mock(WriteJobToBucket.class), mock(PlatformExecutor.class),
        mock(Supplier.class));

    // Act
    bulkImportExecutor.runJob(null);

    // Assert that nothing has changed
    IngestJobTracker ingestJobTracker = bulkImportExecutor.ingestJobTracker;
    assertTrue(ingestJobTracker instanceof InMemoryIngestJobTracker);
    assertTrue(ingestJobTracker.getInvalidJobs().isEmpty());
  }

  /**
   * Test {@link BulkImportExecutor#runJob(BulkImportJob)} with {@code bulkImportJob}.
   * <p>
   * Method under test: {@link BulkImportExecutor#runJob(BulkImportJob)}
   */
  @Test
  @DisplayName("Test runJob(BulkImportJob) with 'bulkImportJob'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void BulkImportExecutor.runJob(BulkImportJob)"})
  void testRunJobWithBulkImportJob2() throws SleeperPropertiesInvalidException, StateStoreException {
    // Arrange
    TableIndex tableIndex = mock(TableIndex.class);
    Optional<TableStatus> ofResult = Optional.of(TableStatus.uniqueIdAndName("42", "Table Name", true));
    when(tableIndex.getTableByName(Mockito.<String>any())).thenReturn(ofResult);
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.getInt(Mockito.<TableProperty>any())).thenReturn(1);
    doNothing().when(tableProperties).validate();
    when(tableProperties.get(Mockito.<TableProperty>any())).thenReturn("Get");
    when(tableProperties.getStatus()).thenReturn(TableStatus.uniqueIdAndName("42", "Table Name", true));
    Client client = mock(Client.class);
    when(client.loadProperties(Mockito.<TableStatus>any())).thenReturn(tableProperties);
    TablePropertiesStore propertiesStore = new TablePropertiesStore(tableIndex, client);

    TablePropertiesProvider tablePropertiesProvider = new TablePropertiesProvider(new InstanceProperties(),
        propertiesStore);

    StateStore stateStore = mock(StateStore.class);
    when(stateStore.getLeafPartitions()).thenReturn(new ArrayList<>());
    Factory stateStoreFactory = mock(Factory.class);
    when(stateStoreFactory.getStateStore(Mockito.<TableProperties>any())).thenReturn(stateStore);
    StateStoreProvider stateStoreProvider = new StateStoreProvider(3, stateStoreFactory);

    InstanceProperties instanceProperties = new InstanceProperties();
    BulkImportExecutor bulkImportExecutor = new BulkImportExecutor(instanceProperties, tablePropertiesProvider,
        stateStoreProvider, new InMemoryIngestJobTracker(), mock(WriteJobToBucket.class), mock(PlatformExecutor.class),
        mock(Supplier.class));
    BulkImportJob bulkImportJob = mock(BulkImportJob.class);
    when(bulkImportJob.getFiles()).thenThrow(new RuntimeException("Validating job: {}"));
    when(bulkImportJob.getTableName()).thenReturn("Table Name");
    when(bulkImportJob.getId()).thenReturn("42");

    // Act and Assert
    assertThrows(RuntimeException.class, () -> bulkImportExecutor.runJob(bulkImportJob));
    verify(bulkImportJob).getFiles();
    verify(bulkImportJob, atLeast(1)).getId();
    verify(bulkImportJob).getTableName();
    verify(tableProperties).validate();
    verify(tableProperties).getInt(isA(TableProperty.class));
    verify(tableProperties, atLeast(1)).get(Mockito.<TableProperty>any());
    verify(tableProperties).getStatus();
    verify(client).loadProperties(isA(TableStatus.class));
    verify(stateStore).getLeafPartitions();
    verify(stateStoreFactory).getStateStore(isA(TableProperties.class));
    verify(tableIndex).getTableByName(eq("Table Name"));
  }

  /**
   * Test {@link BulkImportExecutor#runJob(BulkImportJob)} with {@code bulkImportJob}.
   * <p>
   * Method under test: {@link BulkImportExecutor#runJob(BulkImportJob)}
   */
  @Test
  @DisplayName("Test runJob(BulkImportJob) with 'bulkImportJob'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void BulkImportExecutor.runJob(BulkImportJob)"})
  void testRunJobWithBulkImportJob3() throws StateStoreException {
    // Arrange
    TablePropertiesProvider tablePropertiesProvider = mock(TablePropertiesProvider.class);
    when(tablePropertiesProvider.getByName(Mockito.<String>any()))
        .thenReturn(new TableProperties(new InstanceProperties()));
    StateStore stateStore = mock(StateStore.class);
    when(stateStore.getLeafPartitions()).thenReturn(new ArrayList<>());
    Factory stateStoreFactory = mock(Factory.class);
    when(stateStoreFactory.getStateStore(Mockito.<TableProperties>any())).thenReturn(stateStore);
    StateStoreProvider stateStoreProvider = new StateStoreProvider(3, stateStoreFactory);

    Supplier<Instant> validationTimeSupplier = mock(Supplier.class);
    when(validationTimeSupplier.get())
        .thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    InstanceProperties instanceProperties = new InstanceProperties();
    BulkImportExecutor bulkImportExecutor = new BulkImportExecutor(instanceProperties, tablePropertiesProvider,
        stateStoreProvider, new InMemoryIngestJobTracker(), mock(WriteJobToBucket.class), mock(PlatformExecutor.class),
        validationTimeSupplier);
    BulkImportJob bulkImportJob = mock(BulkImportJob.class);
    when(bulkImportJob.getFiles()).thenReturn(new ArrayList<>());
    Builder builderResult = IngestJob.builder();
    IngestJob buildResult = builderResult.files(new ArrayList<>())
        .id("42")
        .tableId("42")
        .tableName("Table Name")
        .build();
    when(bulkImportJob.toIngestJob()).thenReturn(buildResult);
    when(bulkImportJob.getTableName()).thenReturn("Table Name");
    when(bulkImportJob.getId()).thenReturn("42");

    // Act
    bulkImportExecutor.runJob(bulkImportJob);

    // Assert
    verify(validationTimeSupplier).get();
    verify(bulkImportJob, atLeast(1)).getFiles();
    verify(bulkImportJob, atLeast(1)).getId();
    verify(bulkImportJob).getTableName();
    verify(bulkImportJob).toIngestJob();
    verify(tablePropertiesProvider).getByName(eq("Table Name"));
    verify(stateStore).getLeafPartitions();
    verify(stateStoreFactory).getStateStore(isA(TableProperties.class));
    IngestJobTracker ingestJobTracker = bulkImportExecutor.ingestJobTracker;
    assertTrue(ingestJobTracker instanceof InMemoryIngestJobTracker);
    List<IngestJobStatus> invalidJobs = ingestJobTracker.getInvalidJobs();
    assertEquals(1, invalidJobs.size());
    assertEquals(1, invalidJobs.get(0).getRunsLatestFirst().size());
  }

  /**
   * Test {@link BulkImportExecutor#runJob(BulkImportJob)} with {@code bulkImportJob}.
   * <p>
   * Method under test: {@link BulkImportExecutor#runJob(BulkImportJob)}
   */
  @Test
  @DisplayName("Test runJob(BulkImportJob) with 'bulkImportJob'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void BulkImportExecutor.runJob(BulkImportJob)"})
  void testRunJobWithBulkImportJob4() throws StateStoreException {
    // Arrange
    TablePropertiesProvider tablePropertiesProvider = mock(TablePropertiesProvider.class);
    when(tablePropertiesProvider.getByName(Mockito.<String>any()))
        .thenReturn(new TableProperties(new InstanceProperties()));
    StateStore stateStore = mock(StateStore.class);
    when(stateStore.getLeafPartitions()).thenReturn(new ArrayList<>());
    Factory stateStoreFactory = mock(Factory.class);
    when(stateStoreFactory.getStateStore(Mockito.<TableProperties>any())).thenReturn(stateStore);
    StateStoreProvider stateStoreProvider = new StateStoreProvider(0, stateStoreFactory);

    Supplier<Instant> validationTimeSupplier = mock(Supplier.class);
    when(validationTimeSupplier.get())
        .thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    InstanceProperties instanceProperties = new InstanceProperties();
    BulkImportExecutor bulkImportExecutor = new BulkImportExecutor(instanceProperties, tablePropertiesProvider,
        stateStoreProvider, new InMemoryIngestJobTracker(), mock(WriteJobToBucket.class), mock(PlatformExecutor.class),
        validationTimeSupplier);
    BulkImportJob bulkImportJob = mock(BulkImportJob.class);
    when(bulkImportJob.getFiles()).thenReturn(new ArrayList<>());
    Builder builderResult = IngestJob.builder();
    IngestJob buildResult = builderResult.files(new ArrayList<>())
        .id("42")
        .tableId("42")
        .tableName("Table Name")
        .build();
    when(bulkImportJob.toIngestJob()).thenReturn(buildResult);
    when(bulkImportJob.getTableName()).thenReturn("Table Name");
    when(bulkImportJob.getId()).thenReturn("42");

    // Act
    bulkImportExecutor.runJob(bulkImportJob);

    // Assert
    verify(validationTimeSupplier).get();
    verify(bulkImportJob, atLeast(1)).getFiles();
    verify(bulkImportJob, atLeast(1)).getId();
    verify(bulkImportJob).getTableName();
    verify(bulkImportJob).toIngestJob();
    verify(tablePropertiesProvider).getByName(eq("Table Name"));
    verify(stateStore).getLeafPartitions();
    verify(stateStoreFactory).getStateStore(isA(TableProperties.class));
    IngestJobTracker ingestJobTracker = bulkImportExecutor.ingestJobTracker;
    assertTrue(ingestJobTracker instanceof InMemoryIngestJobTracker);
    List<IngestJobStatus> invalidJobs = ingestJobTracker.getInvalidJobs();
    assertEquals(1, invalidJobs.size());
    assertEquals(1, invalidJobs.get(0).getRunsLatestFirst().size());
  }

  /**
   * Test {@link BulkImportExecutor#runJob(BulkImportJob)} with {@code bulkImportJob}.
   * <p>
   * Method under test: {@link BulkImportExecutor#runJob(BulkImportJob)}
   */
  @Test
  @DisplayName("Test runJob(BulkImportJob) with 'bulkImportJob'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void BulkImportExecutor.runJob(BulkImportJob)"})
  void testRunJobWithBulkImportJob5() throws StateStoreException {
    // Arrange
    new RuntimeException("Validating job: {}");
    TablePropertiesProvider tablePropertiesProvider = mock(TablePropertiesProvider.class);
    when(tablePropertiesProvider.getByName(Mockito.<String>any()))
        .thenReturn(new TableProperties(new InstanceProperties()));
    StateStore stateStore = mock(StateStore.class);
    when(stateStore.getLeafPartitions()).thenReturn(new ArrayList<>());
    StateStoreProvider stateStoreProvider = mock(StateStoreProvider.class);
    when(stateStoreProvider.getStateStore(Mockito.<TableProperties>any())).thenReturn(stateStore);
    Supplier<Instant> validationTimeSupplier = mock(Supplier.class);
    when(validationTimeSupplier.get())
        .thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    InstanceProperties instanceProperties = new InstanceProperties();
    BulkImportExecutor bulkImportExecutor = new BulkImportExecutor(instanceProperties, tablePropertiesProvider,
        stateStoreProvider, new InMemoryIngestJobTracker(), mock(WriteJobToBucket.class), mock(PlatformExecutor.class),
        validationTimeSupplier);

    ArrayList<String> stringList = new ArrayList<>();
    stringList.add("Validating job: {}");
    BulkImportJob bulkImportJob = mock(BulkImportJob.class);
    when(bulkImportJob.getFiles()).thenReturn(stringList);
    Builder builderResult = IngestJob.builder();
    IngestJob buildResult = builderResult.files(new ArrayList<>())
        .id("42")
        .tableId("42")
        .tableName("Table Name")
        .build();
    when(bulkImportJob.toIngestJob()).thenReturn(buildResult);
    when(bulkImportJob.getTableName()).thenReturn("Table Name");
    when(bulkImportJob.getId()).thenReturn("42");

    // Act
    bulkImportExecutor.runJob(bulkImportJob);

    // Assert
    verify(validationTimeSupplier).get();
    verify(bulkImportJob, atLeast(1)).getFiles();
    verify(bulkImportJob, atLeast(1)).getId();
    verify(bulkImportJob).getTableName();
    verify(bulkImportJob).toIngestJob();
    verify(tablePropertiesProvider).getByName(eq("Table Name"));
    verify(stateStore).getLeafPartitions();
    verify(stateStoreProvider).getStateStore(isA(TableProperties.class));
    IngestJobTracker ingestJobTracker = bulkImportExecutor.ingestJobTracker;
    assertTrue(ingestJobTracker instanceof InMemoryIngestJobTracker);
    List<IngestJobStatus> invalidJobs = ingestJobTracker.getInvalidJobs();
    assertEquals(1, invalidJobs.size());
    assertEquals(1, invalidJobs.get(0).getRunsLatestFirst().size());
  }

  /**
   * Test {@link BulkImportExecutor#runJob(BulkImportJob)} with {@code bulkImportJob}.
   * <p>
   * Method under test: {@link BulkImportExecutor#runJob(BulkImportJob)}
   */
  @Test
  @DisplayName("Test runJob(BulkImportJob) with 'bulkImportJob'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void BulkImportExecutor.runJob(BulkImportJob)"})
  void testRunJobWithBulkImportJob6() throws StateStoreException {
    // Arrange
    new RuntimeException("Validating job: {}");
    TablePropertiesProvider tablePropertiesProvider = mock(TablePropertiesProvider.class);
    when(tablePropertiesProvider.getByName(Mockito.<String>any()))
        .thenReturn(new TableProperties(new InstanceProperties()));
    StateStore stateStore = mock(StateStore.class);
    when(stateStore.getLeafPartitions()).thenReturn(new ArrayList<>());
    StateStoreProvider stateStoreProvider = mock(StateStoreProvider.class);
    when(stateStoreProvider.getStateStore(Mockito.<TableProperties>any())).thenReturn(stateStore);
    Supplier<Instant> validationTimeSupplier = mock(Supplier.class);
    when(validationTimeSupplier.get())
        .thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    InstanceProperties instanceProperties = new InstanceProperties();
    BulkImportExecutor bulkImportExecutor = new BulkImportExecutor(instanceProperties, tablePropertiesProvider,
        stateStoreProvider, new InMemoryIngestJobTracker(), mock(WriteJobToBucket.class), mock(PlatformExecutor.class),
        validationTimeSupplier);
    BulkImportJob bulkImportJob = mock(BulkImportJob.class);
    when(bulkImportJob.getFiles()).thenReturn(new ArrayList<>());
    Builder builderResult = IngestJob.builder();
    IngestJob buildResult = builderResult.files(new ArrayList<>())
        .id("42")
        .tableId("42")
        .tableName("Table Name")
        .build();
    when(bulkImportJob.toIngestJob()).thenReturn(buildResult);
    when(bulkImportJob.getTableName()).thenReturn("Table Name");
    when(bulkImportJob.getId())
        .thenReturn("Minimum partition count was {}, but found {} leaf partitions. Skipping job {}");

    // Act
    bulkImportExecutor.runJob(bulkImportJob);

    // Assert
    verify(validationTimeSupplier).get();
    verify(bulkImportJob, atLeast(1)).getFiles();
    verify(bulkImportJob, atLeast(1)).getId();
    verify(bulkImportJob).getTableName();
    verify(bulkImportJob).toIngestJob();
    verify(tablePropertiesProvider).getByName(eq("Table Name"));
    verify(stateStore).getLeafPartitions();
    verify(stateStoreProvider).getStateStore(isA(TableProperties.class));
    IngestJobTracker ingestJobTracker = bulkImportExecutor.ingestJobTracker;
    assertTrue(ingestJobTracker instanceof InMemoryIngestJobTracker);
    List<IngestJobStatus> invalidJobs = ingestJobTracker.getInvalidJobs();
    assertEquals(1, invalidJobs.size());
    List<IngestJobRun> runsLatestFirst = invalidJobs.get(0).getRunsLatestFirst();
    assertEquals(1, runsLatestFirst.size());
    List<JobStatusUpdate> statusUpdates = runsLatestFirst.get(0).getStatusUpdates();
    assertEquals(1, statusUpdates.size());
    JobStatusUpdate getResult = statusUpdates.get(0);
    assertTrue(getResult instanceof IngestJobRejectedStatus);
    List<String> failureReasons = ((IngestJobRejectedStatus) getResult).getFailureReasons();
    assertEquals(4, failureReasons.size());
    assertEquals("Job IDs are only allowed to be up to 63 characters long.", failureReasons.get(1));
    assertEquals("The input files must be set to a non-null and non-empty value.", failureReasons.get(3));
    assertEquals("The minimum partition count was not reached", failureReasons.get(2));
  }

  /**
   * Test {@link BulkImportExecutor#runJob(BulkImportJob, String)} with {@code bulkImportJob}, {@code jobRunId}.
   * <p>
   * Method under test: {@link BulkImportExecutor#runJob(BulkImportJob, String)}
   */
  @Test
  @DisplayName("Test runJob(BulkImportJob, String) with 'bulkImportJob', 'jobRunId'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void BulkImportExecutor.runJob(BulkImportJob, String)"})
  void testRunJobWithBulkImportJobJobRunId() {
    // Arrange
    InstanceProperties instanceProperties = new InstanceProperties();
    InstanceProperties instanceProperties2 = new InstanceProperties();
    TablePropertiesProvider tablePropertiesProvider = new TablePropertiesProvider(instanceProperties2,
        new TablePropertiesStore(new InMemoryTableIndex(), mock(Client.class)));

    StateStoreProvider stateStoreProvider = new StateStoreProvider(3, mock(Factory.class));

    BulkImportExecutor bulkImportExecutor = new BulkImportExecutor(instanceProperties, tablePropertiesProvider,
        stateStoreProvider, new InMemoryIngestJobTracker(), mock(WriteJobToBucket.class), mock(PlatformExecutor.class),
        mock(Supplier.class));

    // Act
    bulkImportExecutor.runJob(null, "42");

    // Assert that nothing has changed
    IngestJobTracker ingestJobTracker = bulkImportExecutor.ingestJobTracker;
    assertTrue(ingestJobTracker instanceof InMemoryIngestJobTracker);
    assertTrue(ingestJobTracker.getInvalidJobs().isEmpty());
  }

  /**
   * Test {@link BulkImportExecutor#runJob(BulkImportJob, String)} with {@code bulkImportJob}, {@code jobRunId}.
   * <p>
   * Method under test: {@link BulkImportExecutor#runJob(BulkImportJob, String)}
   */
  @Test
  @DisplayName("Test runJob(BulkImportJob, String) with 'bulkImportJob', 'jobRunId'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void BulkImportExecutor.runJob(BulkImportJob, String)"})
  void testRunJobWithBulkImportJobJobRunId2() throws SleeperPropertiesInvalidException, StateStoreException {
    // Arrange
    TableIndex tableIndex = mock(TableIndex.class);
    Optional<TableStatus> ofResult = Optional.of(TableStatus.uniqueIdAndName("42", "Table Name", true));
    when(tableIndex.getTableByName(Mockito.<String>any())).thenReturn(ofResult);
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.getInt(Mockito.<TableProperty>any())).thenReturn(1);
    doNothing().when(tableProperties).validate();
    when(tableProperties.get(Mockito.<TableProperty>any())).thenReturn("Get");
    when(tableProperties.getStatus()).thenReturn(TableStatus.uniqueIdAndName("42", "Table Name", true));
    Client client = mock(Client.class);
    when(client.loadProperties(Mockito.<TableStatus>any())).thenReturn(tableProperties);
    TablePropertiesStore propertiesStore = new TablePropertiesStore(tableIndex, client);

    TablePropertiesProvider tablePropertiesProvider = new TablePropertiesProvider(new InstanceProperties(),
        propertiesStore);

    StateStore stateStore = mock(StateStore.class);
    when(stateStore.getLeafPartitions()).thenReturn(new ArrayList<>());
    Factory stateStoreFactory = mock(Factory.class);
    when(stateStoreFactory.getStateStore(Mockito.<TableProperties>any())).thenReturn(stateStore);
    StateStoreProvider stateStoreProvider = new StateStoreProvider(3, stateStoreFactory);

    InstanceProperties instanceProperties = new InstanceProperties();
    BulkImportExecutor bulkImportExecutor = new BulkImportExecutor(instanceProperties, tablePropertiesProvider,
        stateStoreProvider, new InMemoryIngestJobTracker(), mock(WriteJobToBucket.class), mock(PlatformExecutor.class),
        mock(Supplier.class));
    BulkImportJob bulkImportJob = mock(BulkImportJob.class);
    when(bulkImportJob.getFiles()).thenThrow(new RuntimeException("Validating job: {}"));
    when(bulkImportJob.getTableName()).thenReturn("Table Name");
    when(bulkImportJob.getId()).thenReturn("42");

    // Act and Assert
    assertThrows(RuntimeException.class, () -> bulkImportExecutor.runJob(bulkImportJob, "42"));
    verify(bulkImportJob).getFiles();
    verify(bulkImportJob, atLeast(1)).getId();
    verify(bulkImportJob).getTableName();
    verify(tableProperties).validate();
    verify(tableProperties).getInt(isA(TableProperty.class));
    verify(tableProperties, atLeast(1)).get(Mockito.<TableProperty>any());
    verify(tableProperties).getStatus();
    verify(client).loadProperties(isA(TableStatus.class));
    verify(stateStore).getLeafPartitions();
    verify(stateStoreFactory).getStateStore(isA(TableProperties.class));
    verify(tableIndex).getTableByName(eq("Table Name"));
  }

  /**
   * Test {@link BulkImportExecutor#runJob(BulkImportJob, String)} with {@code bulkImportJob}, {@code jobRunId}.
   * <p>
   * Method under test: {@link BulkImportExecutor#runJob(BulkImportJob, String)}
   */
  @Test
  @DisplayName("Test runJob(BulkImportJob, String) with 'bulkImportJob', 'jobRunId'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void BulkImportExecutor.runJob(BulkImportJob, String)"})
  void testRunJobWithBulkImportJobJobRunId3() throws StateStoreException {
    // Arrange
    TablePropertiesProvider tablePropertiesProvider = mock(TablePropertiesProvider.class);
    when(tablePropertiesProvider.getByName(Mockito.<String>any()))
        .thenReturn(new TableProperties(new InstanceProperties()));
    StateStore stateStore = mock(StateStore.class);
    when(stateStore.getLeafPartitions()).thenReturn(new ArrayList<>());
    Factory stateStoreFactory = mock(Factory.class);
    when(stateStoreFactory.getStateStore(Mockito.<TableProperties>any())).thenReturn(stateStore);
    StateStoreProvider stateStoreProvider = new StateStoreProvider(3, stateStoreFactory);

    Supplier<Instant> validationTimeSupplier = mock(Supplier.class);
    when(validationTimeSupplier.get())
        .thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    InstanceProperties instanceProperties = new InstanceProperties();
    BulkImportExecutor bulkImportExecutor = new BulkImportExecutor(instanceProperties, tablePropertiesProvider,
        stateStoreProvider, new InMemoryIngestJobTracker(), mock(WriteJobToBucket.class), mock(PlatformExecutor.class),
        validationTimeSupplier);
    BulkImportJob bulkImportJob = mock(BulkImportJob.class);
    when(bulkImportJob.getFiles()).thenReturn(new ArrayList<>());
    Builder builderResult = IngestJob.builder();
    IngestJob buildResult = builderResult.files(new ArrayList<>())
        .id("42")
        .tableId("42")
        .tableName("Table Name")
        .build();
    when(bulkImportJob.toIngestJob()).thenReturn(buildResult);
    when(bulkImportJob.getTableName()).thenReturn("Table Name");
    when(bulkImportJob.getId()).thenReturn("42");

    // Act
    bulkImportExecutor.runJob(bulkImportJob, "42");

    // Assert
    verify(validationTimeSupplier).get();
    verify(bulkImportJob, atLeast(1)).getFiles();
    verify(bulkImportJob, atLeast(1)).getId();
    verify(bulkImportJob).getTableName();
    verify(bulkImportJob).toIngestJob();
    verify(tablePropertiesProvider).getByName(eq("Table Name"));
    verify(stateStore).getLeafPartitions();
    verify(stateStoreFactory).getStateStore(isA(TableProperties.class));
    IngestJobTracker ingestJobTracker = bulkImportExecutor.ingestJobTracker;
    assertTrue(ingestJobTracker instanceof InMemoryIngestJobTracker);
    List<IngestJobStatus> invalidJobs = ingestJobTracker.getInvalidJobs();
    assertEquals(1, invalidJobs.size());
    assertEquals(1, invalidJobs.get(0).getRunsLatestFirst().size());
  }

  /**
   * Test {@link BulkImportExecutor#runJob(BulkImportJob, String)} with {@code bulkImportJob}, {@code jobRunId}.
   * <p>
   * Method under test: {@link BulkImportExecutor#runJob(BulkImportJob, String)}
   */
  @Test
  @DisplayName("Test runJob(BulkImportJob, String) with 'bulkImportJob', 'jobRunId'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void BulkImportExecutor.runJob(BulkImportJob, String)"})
  void testRunJobWithBulkImportJobJobRunId4() throws StateStoreException {
    // Arrange
    TablePropertiesProvider tablePropertiesProvider = mock(TablePropertiesProvider.class);
    when(tablePropertiesProvider.getByName(Mockito.<String>any()))
        .thenReturn(new TableProperties(new InstanceProperties()));
    StateStore stateStore = mock(StateStore.class);
    when(stateStore.getLeafPartitions()).thenReturn(new ArrayList<>());
    Factory stateStoreFactory = mock(Factory.class);
    when(stateStoreFactory.getStateStore(Mockito.<TableProperties>any())).thenReturn(stateStore);
    StateStoreProvider stateStoreProvider = new StateStoreProvider(0, stateStoreFactory);

    Supplier<Instant> validationTimeSupplier = mock(Supplier.class);
    when(validationTimeSupplier.get())
        .thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    InstanceProperties instanceProperties = new InstanceProperties();
    BulkImportExecutor bulkImportExecutor = new BulkImportExecutor(instanceProperties, tablePropertiesProvider,
        stateStoreProvider, new InMemoryIngestJobTracker(), mock(WriteJobToBucket.class), mock(PlatformExecutor.class),
        validationTimeSupplier);
    BulkImportJob bulkImportJob = mock(BulkImportJob.class);
    when(bulkImportJob.getFiles()).thenReturn(new ArrayList<>());
    Builder builderResult = IngestJob.builder();
    IngestJob buildResult = builderResult.files(new ArrayList<>())
        .id("42")
        .tableId("42")
        .tableName("Table Name")
        .build();
    when(bulkImportJob.toIngestJob()).thenReturn(buildResult);
    when(bulkImportJob.getTableName()).thenReturn("Table Name");
    when(bulkImportJob.getId()).thenReturn("42");

    // Act
    bulkImportExecutor.runJob(bulkImportJob, "42");

    // Assert
    verify(validationTimeSupplier).get();
    verify(bulkImportJob, atLeast(1)).getFiles();
    verify(bulkImportJob, atLeast(1)).getId();
    verify(bulkImportJob).getTableName();
    verify(bulkImportJob).toIngestJob();
    verify(tablePropertiesProvider).getByName(eq("Table Name"));
    verify(stateStore).getLeafPartitions();
    verify(stateStoreFactory).getStateStore(isA(TableProperties.class));
    IngestJobTracker ingestJobTracker = bulkImportExecutor.ingestJobTracker;
    assertTrue(ingestJobTracker instanceof InMemoryIngestJobTracker);
    List<IngestJobStatus> invalidJobs = ingestJobTracker.getInvalidJobs();
    assertEquals(1, invalidJobs.size());
    assertEquals(1, invalidJobs.get(0).getRunsLatestFirst().size());
  }

  /**
   * Test {@link BulkImportExecutor#runJob(BulkImportJob, String)} with {@code bulkImportJob}, {@code jobRunId}.
   * <p>
   * Method under test: {@link BulkImportExecutor#runJob(BulkImportJob, String)}
   */
  @Test
  @DisplayName("Test runJob(BulkImportJob, String) with 'bulkImportJob', 'jobRunId'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void BulkImportExecutor.runJob(BulkImportJob, String)"})
  void testRunJobWithBulkImportJobJobRunId5() throws StateStoreException {
    // Arrange
    new RuntimeException("Validating job: {}");
    TablePropertiesProvider tablePropertiesProvider = mock(TablePropertiesProvider.class);
    when(tablePropertiesProvider.getByName(Mockito.<String>any()))
        .thenReturn(new TableProperties(new InstanceProperties()));
    StateStore stateStore = mock(StateStore.class);
    when(stateStore.getLeafPartitions()).thenReturn(new ArrayList<>());
    StateStoreProvider stateStoreProvider = mock(StateStoreProvider.class);
    when(stateStoreProvider.getStateStore(Mockito.<TableProperties>any())).thenReturn(stateStore);
    Supplier<Instant> validationTimeSupplier = mock(Supplier.class);
    when(validationTimeSupplier.get())
        .thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    InstanceProperties instanceProperties = new InstanceProperties();
    BulkImportExecutor bulkImportExecutor = new BulkImportExecutor(instanceProperties, tablePropertiesProvider,
        stateStoreProvider, new InMemoryIngestJobTracker(), mock(WriteJobToBucket.class), mock(PlatformExecutor.class),
        validationTimeSupplier);
    BulkImportJob bulkImportJob = mock(BulkImportJob.class);
    when(bulkImportJob.getFiles()).thenReturn(new ArrayList<>());
    Builder builderResult = IngestJob.builder();
    IngestJob buildResult = builderResult.files(new ArrayList<>())
        .id("42")
        .tableId("42")
        .tableName("Table Name")
        .build();
    when(bulkImportJob.toIngestJob()).thenReturn(buildResult);
    when(bulkImportJob.getTableName()).thenReturn("Table Name");
    when(bulkImportJob.getId()).thenReturn("42");

    // Act
    bulkImportExecutor.runJob(bulkImportJob, "42");

    // Assert
    verify(validationTimeSupplier).get();
    verify(bulkImportJob, atLeast(1)).getFiles();
    verify(bulkImportJob, atLeast(1)).getId();
    verify(bulkImportJob).getTableName();
    verify(bulkImportJob).toIngestJob();
    verify(tablePropertiesProvider).getByName(eq("Table Name"));
    verify(stateStore).getLeafPartitions();
    verify(stateStoreProvider).getStateStore(isA(TableProperties.class));
    IngestJobTracker ingestJobTracker = bulkImportExecutor.ingestJobTracker;
    assertTrue(ingestJobTracker instanceof InMemoryIngestJobTracker);
    List<IngestJobStatus> invalidJobs = ingestJobTracker.getInvalidJobs();
    assertEquals(1, invalidJobs.size());
    assertEquals(1, invalidJobs.get(0).getRunsLatestFirst().size());
  }

  /**
   * Test {@link BulkImportExecutor#runJob(BulkImportJob, String)} with {@code bulkImportJob}, {@code jobRunId}.
   * <p>
   * Method under test: {@link BulkImportExecutor#runJob(BulkImportJob, String)}
   */
  @Test
  @DisplayName("Test runJob(BulkImportJob, String) with 'bulkImportJob', 'jobRunId'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void BulkImportExecutor.runJob(BulkImportJob, String)"})
  void testRunJobWithBulkImportJobJobRunId6() throws StateStoreException {
    // Arrange
    new RuntimeException("Validating job: {}");
    TablePropertiesProvider tablePropertiesProvider = mock(TablePropertiesProvider.class);
    when(tablePropertiesProvider.getByName(Mockito.<String>any()))
        .thenReturn(new TableProperties(new InstanceProperties()));
    StateStore stateStore = mock(StateStore.class);
    when(stateStore.getLeafPartitions()).thenReturn(new ArrayList<>());
    StateStoreProvider stateStoreProvider = mock(StateStoreProvider.class);
    when(stateStoreProvider.getStateStore(Mockito.<TableProperties>any())).thenReturn(stateStore);
    Supplier<Instant> validationTimeSupplier = mock(Supplier.class);
    when(validationTimeSupplier.get())
        .thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    InstanceProperties instanceProperties = new InstanceProperties();
    BulkImportExecutor bulkImportExecutor = new BulkImportExecutor(instanceProperties, tablePropertiesProvider,
        stateStoreProvider, new InMemoryIngestJobTracker(), mock(WriteJobToBucket.class), mock(PlatformExecutor.class),
        validationTimeSupplier);

    ArrayList<String> stringList = new ArrayList<>();
    stringList.add("Validating job: {}");
    BulkImportJob bulkImportJob = mock(BulkImportJob.class);
    when(bulkImportJob.getFiles()).thenReturn(stringList);
    Builder builderResult = IngestJob.builder();
    IngestJob buildResult = builderResult.files(new ArrayList<>())
        .id("42")
        .tableId("42")
        .tableName("Table Name")
        .build();
    when(bulkImportJob.toIngestJob()).thenReturn(buildResult);
    when(bulkImportJob.getTableName()).thenReturn("Table Name");
    when(bulkImportJob.getId()).thenReturn("42");

    // Act
    bulkImportExecutor.runJob(bulkImportJob, "42");

    // Assert
    verify(validationTimeSupplier).get();
    verify(bulkImportJob, atLeast(1)).getFiles();
    verify(bulkImportJob, atLeast(1)).getId();
    verify(bulkImportJob).getTableName();
    verify(bulkImportJob).toIngestJob();
    verify(tablePropertiesProvider).getByName(eq("Table Name"));
    verify(stateStore).getLeafPartitions();
    verify(stateStoreProvider).getStateStore(isA(TableProperties.class));
    IngestJobTracker ingestJobTracker = bulkImportExecutor.ingestJobTracker;
    assertTrue(ingestJobTracker instanceof InMemoryIngestJobTracker);
    List<IngestJobStatus> invalidJobs = ingestJobTracker.getInvalidJobs();
    assertEquals(1, invalidJobs.size());
    assertEquals(1, invalidJobs.get(0).getRunsLatestFirst().size());
  }

  /**
   * Test {@link BulkImportExecutor#runJob(BulkImportJob, String)} with {@code bulkImportJob}, {@code jobRunId}.
   * <p>
   * Method under test: {@link BulkImportExecutor#runJob(BulkImportJob, String)}
   */
  @Test
  @DisplayName("Test runJob(BulkImportJob, String) with 'bulkImportJob', 'jobRunId'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void BulkImportExecutor.runJob(BulkImportJob, String)"})
  void testRunJobWithBulkImportJobJobRunId7() throws StateStoreException {
    // Arrange
    new RuntimeException("Validating job: {}");
    TablePropertiesProvider tablePropertiesProvider = mock(TablePropertiesProvider.class);
    when(tablePropertiesProvider.getByName(Mockito.<String>any()))
        .thenReturn(new TableProperties(new InstanceProperties()));
    StateStore stateStore = mock(StateStore.class);
    when(stateStore.getLeafPartitions()).thenReturn(new ArrayList<>());
    StateStoreProvider stateStoreProvider = mock(StateStoreProvider.class);
    when(stateStoreProvider.getStateStore(Mockito.<TableProperties>any())).thenReturn(stateStore);
    Supplier<Instant> validationTimeSupplier = mock(Supplier.class);
    when(validationTimeSupplier.get())
        .thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    InstanceProperties instanceProperties = new InstanceProperties();
    BulkImportExecutor bulkImportExecutor = new BulkImportExecutor(instanceProperties, tablePropertiesProvider,
        stateStoreProvider, new InMemoryIngestJobTracker(), mock(WriteJobToBucket.class), mock(PlatformExecutor.class),
        validationTimeSupplier);
    BulkImportJob bulkImportJob = mock(BulkImportJob.class);
    when(bulkImportJob.getFiles()).thenReturn(new ArrayList<>());
    Builder builderResult = IngestJob.builder();
    IngestJob buildResult = builderResult.files(new ArrayList<>())
        .id("42")
        .tableId("42")
        .tableName("Table Name")
        .build();
    when(bulkImportJob.toIngestJob()).thenReturn(buildResult);
    when(bulkImportJob.getTableName()).thenReturn("Table Name");
    when(bulkImportJob.getId()).thenReturn("Validating job: {}");

    // Act
    bulkImportExecutor.runJob(bulkImportJob, "42");

    // Assert
    verify(validationTimeSupplier).get();
    verify(bulkImportJob, atLeast(1)).getFiles();
    verify(bulkImportJob, atLeast(1)).getId();
    verify(bulkImportJob).getTableName();
    verify(bulkImportJob).toIngestJob();
    verify(tablePropertiesProvider).getByName(eq("Table Name"));
    verify(stateStore).getLeafPartitions();
    verify(stateStoreProvider).getStateStore(isA(TableProperties.class));
    IngestJobTracker ingestJobTracker = bulkImportExecutor.ingestJobTracker;
    assertTrue(ingestJobTracker instanceof InMemoryIngestJobTracker);
    List<IngestJobStatus> invalidJobs = ingestJobTracker.getInvalidJobs();
    assertEquals(1, invalidJobs.size());
    List<IngestJobRun> runsLatestFirst = invalidJobs.get(0).getRunsLatestFirst();
    assertEquals(1, runsLatestFirst.size());
    List<JobStatusUpdate> statusUpdates = runsLatestFirst.get(0).getStatusUpdates();
    assertEquals(1, statusUpdates.size());
    JobStatusUpdate getResult = statusUpdates.get(0);
    assertTrue(getResult instanceof IngestJobRejectedStatus);
    List<String> failureReasons = ((IngestJobRejectedStatus) getResult).getFailureReasons();
    assertEquals(3, failureReasons.size());
    assertEquals("Job Ids must only contain lowercase alphanumerics and dashes.", failureReasons.get(0));
    assertEquals("The input files must be set to a non-null and non-empty value.", failureReasons.get(2));
    assertEquals("The minimum partition count was not reached", failureReasons.get(1));
  }

  /**
   * Test {@link BulkImportExecutor#runJob(BulkImportJob, String)} with {@code bulkImportJob}, {@code jobRunId}.
   * <p>
   * Method under test: {@link BulkImportExecutor#runJob(BulkImportJob, String)}
   */
  @Test
  @DisplayName("Test runJob(BulkImportJob, String) with 'bulkImportJob', 'jobRunId'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void BulkImportExecutor.runJob(BulkImportJob, String)"})
  void testRunJobWithBulkImportJobJobRunId8() throws StateStoreException {
    // Arrange
    new RuntimeException("Validating job: {}");
    TablePropertiesProvider tablePropertiesProvider = mock(TablePropertiesProvider.class);
    when(tablePropertiesProvider.getByName(Mockito.<String>any()))
        .thenReturn(new TableProperties(new InstanceProperties()));
    StateStore stateStore = mock(StateStore.class);
    when(stateStore.getLeafPartitions()).thenReturn(new ArrayList<>());
    StateStoreProvider stateStoreProvider = mock(StateStoreProvider.class);
    when(stateStoreProvider.getStateStore(Mockito.<TableProperties>any())).thenReturn(stateStore);
    Supplier<Instant> validationTimeSupplier = mock(Supplier.class);
    when(validationTimeSupplier.get())
        .thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    InstanceProperties instanceProperties = new InstanceProperties();
    BulkImportExecutor bulkImportExecutor = new BulkImportExecutor(instanceProperties, tablePropertiesProvider,
        stateStoreProvider, new InMemoryIngestJobTracker(), mock(WriteJobToBucket.class), mock(PlatformExecutor.class),
        validationTimeSupplier);
    BulkImportJob bulkImportJob = mock(BulkImportJob.class);
    when(bulkImportJob.getFiles()).thenReturn(new ArrayList<>());
    Builder builderResult = IngestJob.builder();
    IngestJob buildResult = builderResult.files(new ArrayList<>())
        .id("42")
        .tableId("42")
        .tableName("Table Name")
        .build();
    when(bulkImportJob.toIngestJob()).thenReturn(buildResult);
    when(bulkImportJob.getTableName()).thenReturn("Table Name");
    when(bulkImportJob.getId())
        .thenReturn("Minimum partition count was {}, but found {} leaf partitions. Skipping job {}");

    // Act
    bulkImportExecutor.runJob(bulkImportJob, "42");

    // Assert
    verify(validationTimeSupplier).get();
    verify(bulkImportJob, atLeast(1)).getFiles();
    verify(bulkImportJob, atLeast(1)).getId();
    verify(bulkImportJob).getTableName();
    verify(bulkImportJob).toIngestJob();
    verify(tablePropertiesProvider).getByName(eq("Table Name"));
    verify(stateStore).getLeafPartitions();
    verify(stateStoreProvider).getStateStore(isA(TableProperties.class));
    IngestJobTracker ingestJobTracker = bulkImportExecutor.ingestJobTracker;
    assertTrue(ingestJobTracker instanceof InMemoryIngestJobTracker);
    List<IngestJobStatus> invalidJobs = ingestJobTracker.getInvalidJobs();
    assertEquals(1, invalidJobs.size());
    List<IngestJobRun> runsLatestFirst = invalidJobs.get(0).getRunsLatestFirst();
    assertEquals(1, runsLatestFirst.size());
    List<JobStatusUpdate> statusUpdates = runsLatestFirst.get(0).getStatusUpdates();
    assertEquals(1, statusUpdates.size());
    JobStatusUpdate getResult = statusUpdates.get(0);
    assertTrue(getResult instanceof IngestJobRejectedStatus);
    List<String> failureReasons = ((IngestJobRejectedStatus) getResult).getFailureReasons();
    assertEquals(4, failureReasons.size());
    assertEquals("Job IDs are only allowed to be up to 63 characters long.", failureReasons.get(1));
    assertEquals("The input files must be set to a non-null and non-empty value.", failureReasons.get(3));
    assertEquals("The minimum partition count was not reached", failureReasons.get(2));
  }

  /**
   * Test {@link BulkImportExecutor#runJob(BulkImportJob, String)} with {@code bulkImportJob}, {@code jobRunId}.
   * <p>
   * Method under test: {@link BulkImportExecutor#runJob(BulkImportJob, String)}
   */
  @Test
  @DisplayName("Test runJob(BulkImportJob, String) with 'bulkImportJob', 'jobRunId'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void BulkImportExecutor.runJob(BulkImportJob, String)"})
  void testRunJobWithBulkImportJobJobRunId9() throws StateStoreException {
    // Arrange
    new RuntimeException("Validating job: {}");
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.getInt(Mockito.<TableProperty>any())).thenReturn(-1);
    TablePropertiesProvider tablePropertiesProvider = mock(TablePropertiesProvider.class);
    when(tablePropertiesProvider.getByName(Mockito.<String>any())).thenReturn(tableProperties);
    StateStore stateStore = mock(StateStore.class);
    when(stateStore.getLeafPartitions()).thenReturn(new ArrayList<>());
    StateStoreProvider stateStoreProvider = mock(StateStoreProvider.class);
    when(stateStoreProvider.getStateStore(Mockito.<TableProperties>any())).thenReturn(stateStore);
    WriteJobToBucket writeJobToBucket = mock(WriteJobToBucket.class);
    doNothing().when(writeJobToBucket).writeJobToBulkImportBucket(Mockito.<BulkImportJob>any(), Mockito.<String>any());
    PlatformExecutor platformExecutor = mock(PlatformExecutor.class);
    doThrow(new RuntimeException("Validating job: {}")).when(platformExecutor)
        .runJobOnPlatform(Mockito.<BulkImportArguments>any());
    Supplier<Instant> validationTimeSupplier = mock(Supplier.class);
    when(validationTimeSupplier.get())
        .thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    InstanceProperties instanceProperties = new InstanceProperties();
    BulkImportExecutor bulkImportExecutor = new BulkImportExecutor(instanceProperties, tablePropertiesProvider,
        stateStoreProvider, new InMemoryIngestJobTracker(), writeJobToBucket, platformExecutor, validationTimeSupplier);

    ArrayList<String> stringList = new ArrayList<>();
    stringList.add("Validating job: {}");
    BulkImportJob bulkImportJob = mock(BulkImportJob.class);
    when(bulkImportJob.getTableId()).thenThrow(new RuntimeException("Validating job: {}"));
    when(bulkImportJob.getFiles()).thenReturn(stringList);
    Builder builderResult = IngestJob.builder();
    IngestJob buildResult = builderResult.files(new ArrayList<>())
        .id("42")
        .tableId("42")
        .tableName("Table Name")
        .build();
    when(bulkImportJob.toIngestJob()).thenReturn(buildResult);
    when(bulkImportJob.getTableName()).thenReturn("Table Name");
    when(bulkImportJob.getId()).thenReturn("42");

    // Act and Assert
    assertThrows(RuntimeException.class, () -> bulkImportExecutor.runJob(bulkImportJob, "42"));
    verify(validationTimeSupplier).get();
    verify(bulkImportJob, atLeast(1)).getFiles();
    verify(bulkImportJob, atLeast(1)).getId();
    verify(bulkImportJob).getTableId();
    verify(bulkImportJob).getTableName();
    verify(bulkImportJob).toIngestJob();
    verify(writeJobToBucket).writeJobToBulkImportBucket(isA(BulkImportJob.class), eq("42"));
    verify(platformExecutor).runJobOnPlatform(isA(BulkImportArguments.class));
    verify(tableProperties).getInt(isA(TableProperty.class));
    verify(tablePropertiesProvider).getByName(eq("Table Name"));
    verify(stateStore).getLeafPartitions();
    verify(stateStoreProvider).getStateStore(isA(TableProperties.class));
  }

  /**
   * Test {@link BulkImportExecutor#runJob(BulkImportJob, String)} with {@code bulkImportJob}, {@code jobRunId}.
   * <ul>
   *   <li>Given {@link TableProperties} {@link SleeperPropertyValues#getInt(SleeperProperty)} return zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link BulkImportExecutor#runJob(BulkImportJob, String)}
   */
  @Test
  @DisplayName("Test runJob(BulkImportJob, String) with 'bulkImportJob', 'jobRunId'; given TableProperties getInt(SleeperProperty) return zero")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void BulkImportExecutor.runJob(BulkImportJob, String)"})
  void testRunJobWithBulkImportJobJobRunId_givenTablePropertiesGetIntReturnZero()
      throws SleeperPropertiesInvalidException, StateStoreException {
    // Arrange
    TableIndex tableIndex = mock(TableIndex.class);
    Optional<TableStatus> ofResult = Optional.of(TableStatus.uniqueIdAndName("42", "Table Name", true));
    when(tableIndex.getTableByName(Mockito.<String>any())).thenReturn(ofResult);
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.getInt(Mockito.<TableProperty>any())).thenReturn(0);
    doNothing().when(tableProperties).validate();
    when(tableProperties.get(Mockito.<TableProperty>any())).thenReturn("Get");
    when(tableProperties.getStatus()).thenReturn(TableStatus.uniqueIdAndName("42", "Table Name", true));
    Client client = mock(Client.class);
    when(client.loadProperties(Mockito.<TableStatus>any())).thenReturn(tableProperties);
    TablePropertiesStore propertiesStore = new TablePropertiesStore(tableIndex, client);

    TablePropertiesProvider tablePropertiesProvider = new TablePropertiesProvider(new InstanceProperties(),
        propertiesStore);

    StateStore stateStore = mock(StateStore.class);
    when(stateStore.getLeafPartitions()).thenReturn(new ArrayList<>());
    Factory stateStoreFactory = mock(Factory.class);
    when(stateStoreFactory.getStateStore(Mockito.<TableProperties>any())).thenReturn(stateStore);
    StateStoreProvider stateStoreProvider = new StateStoreProvider(3, stateStoreFactory);

    Supplier<Instant> validationTimeSupplier = mock(Supplier.class);
    when(validationTimeSupplier.get())
        .thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    InstanceProperties instanceProperties = new InstanceProperties();
    BulkImportExecutor bulkImportExecutor = new BulkImportExecutor(instanceProperties, tablePropertiesProvider,
        stateStoreProvider, new InMemoryIngestJobTracker(), mock(WriteJobToBucket.class), mock(PlatformExecutor.class),
        validationTimeSupplier);
    BulkImportJob bulkImportJob = mock(BulkImportJob.class);
    when(bulkImportJob.getFiles()).thenReturn(new ArrayList<>());
    Builder builderResult = IngestJob.builder();
    IngestJob buildResult = builderResult.files(new ArrayList<>())
        .id("42")
        .tableId("42")
        .tableName("Table Name")
        .build();
    when(bulkImportJob.toIngestJob()).thenReturn(buildResult);
    when(bulkImportJob.getTableName()).thenReturn("Table Name");
    when(bulkImportJob.getId()).thenReturn("42");

    // Act
    bulkImportExecutor.runJob(bulkImportJob, "42");

    // Assert
    verify(validationTimeSupplier).get();
    verify(bulkImportJob, atLeast(1)).getFiles();
    verify(bulkImportJob, atLeast(1)).getId();
    verify(bulkImportJob).getTableName();
    verify(bulkImportJob).toIngestJob();
    verify(tableProperties).validate();
    verify(tableProperties).getInt(isA(TableProperty.class));
    verify(tableProperties, atLeast(1)).get(Mockito.<TableProperty>any());
    verify(tableProperties).getStatus();
    verify(client).loadProperties(isA(TableStatus.class));
    verify(stateStore).getLeafPartitions();
    verify(stateStoreFactory).getStateStore(isA(TableProperties.class));
    verify(tableIndex).getTableByName(eq("Table Name"));
    IngestJobTracker ingestJobTracker = bulkImportExecutor.ingestJobTracker;
    assertTrue(ingestJobTracker instanceof InMemoryIngestJobTracker);
    List<IngestJobStatus> invalidJobs = ingestJobTracker.getInvalidJobs();
    assertEquals(1, invalidJobs.size());
    assertEquals(1, invalidJobs.get(0).getRunsLatestFirst().size());
  }

  /**
   * Test {@link BulkImportExecutor#runJob(BulkImportJob, String)} with {@code bulkImportJob}, {@code jobRunId}.
   * <ul>
   *   <li>Then calls {@link BulkImportJob#getTableId()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BulkImportExecutor#runJob(BulkImportJob, String)}
   */
  @Test
  @DisplayName("Test runJob(BulkImportJob, String) with 'bulkImportJob', 'jobRunId'; then calls getTableId()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void BulkImportExecutor.runJob(BulkImportJob, String)"})
  void testRunJobWithBulkImportJobJobRunId_thenCallsGetTableId() throws StateStoreException {
    // Arrange
    new RuntimeException("Validating job: {}");
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.getInt(Mockito.<TableProperty>any())).thenReturn(-1);
    TablePropertiesProvider tablePropertiesProvider = mock(TablePropertiesProvider.class);
    when(tablePropertiesProvider.getByName(Mockito.<String>any())).thenReturn(tableProperties);
    StateStore stateStore = mock(StateStore.class);
    when(stateStore.getLeafPartitions()).thenReturn(new ArrayList<>());
    StateStoreProvider stateStoreProvider = mock(StateStoreProvider.class);
    when(stateStoreProvider.getStateStore(Mockito.<TableProperties>any())).thenReturn(stateStore);
    WriteJobToBucket writeJobToBucket = mock(WriteJobToBucket.class);
    doNothing().when(writeJobToBucket).writeJobToBulkImportBucket(Mockito.<BulkImportJob>any(), Mockito.<String>any());
    PlatformExecutor platformExecutor = mock(PlatformExecutor.class);
    doThrow(new RuntimeException("Validating job: {}")).when(platformExecutor)
        .runJobOnPlatform(Mockito.<BulkImportArguments>any());
    Supplier<Instant> validationTimeSupplier = mock(Supplier.class);
    when(validationTimeSupplier.get())
        .thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    InstanceProperties instanceProperties = new InstanceProperties();
    BulkImportExecutor bulkImportExecutor = new BulkImportExecutor(instanceProperties, tablePropertiesProvider,
        stateStoreProvider, new InMemoryIngestJobTracker(), writeJobToBucket, platformExecutor, validationTimeSupplier);

    ArrayList<String> stringList = new ArrayList<>();
    stringList.add("Validating job: {}");
    BulkImportJob bulkImportJob = mock(BulkImportJob.class);
    when(bulkImportJob.getTableId()).thenReturn("42");
    when(bulkImportJob.getFiles()).thenReturn(stringList);
    Builder builderResult = IngestJob.builder();
    IngestJob buildResult = builderResult.files(new ArrayList<>())
        .id("42")
        .tableId("42")
        .tableName("Table Name")
        .build();
    when(bulkImportJob.toIngestJob()).thenReturn(buildResult);
    when(bulkImportJob.getTableName()).thenReturn("Table Name");
    when(bulkImportJob.getId()).thenReturn("42");

    // Act and Assert
    assertThrows(RuntimeException.class, () -> bulkImportExecutor.runJob(bulkImportJob, "42"));
    verify(validationTimeSupplier, atLeast(1)).get();
    verify(bulkImportJob, atLeast(1)).getFiles();
    verify(bulkImportJob, atLeast(1)).getId();
    verify(bulkImportJob).getTableId();
    verify(bulkImportJob).getTableName();
    verify(bulkImportJob, atLeast(1)).toIngestJob();
    verify(writeJobToBucket).writeJobToBulkImportBucket(isA(BulkImportJob.class), eq("42"));
    verify(platformExecutor).runJobOnPlatform(isA(BulkImportArguments.class));
    verify(tableProperties).getInt(isA(TableProperty.class));
    verify(tablePropertiesProvider).getByName(eq("Table Name"));
    verify(stateStore).getLeafPartitions();
    verify(stateStoreProvider).getStateStore(isA(TableProperties.class));
  }

  /**
   * Test {@link BulkImportExecutor#runJob(BulkImportJob, String)} with {@code bulkImportJob}, {@code jobRunId}.
   * <ul>
   *   <li>Then calls {@link IngestJobTracker#jobFailed(IngestJobFailedEvent)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BulkImportExecutor#runJob(BulkImportJob, String)}
   */
  @Test
  @DisplayName("Test runJob(BulkImportJob, String) with 'bulkImportJob', 'jobRunId'; then calls jobFailed(IngestJobFailedEvent)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void BulkImportExecutor.runJob(BulkImportJob, String)"})
  void testRunJobWithBulkImportJobJobRunId_thenCallsJobFailed() throws StateStoreException {
    // Arrange
    new RuntimeException("Validating job: {}");
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.getInt(Mockito.<TableProperty>any())).thenReturn(-1);
    TablePropertiesProvider tablePropertiesProvider = mock(TablePropertiesProvider.class);
    when(tablePropertiesProvider.getByName(Mockito.<String>any())).thenReturn(tableProperties);
    StateStore stateStore = mock(StateStore.class);
    when(stateStore.getLeafPartitions()).thenReturn(new ArrayList<>());
    StateStoreProvider stateStoreProvider = mock(StateStoreProvider.class);
    when(stateStoreProvider.getStateStore(Mockito.<TableProperties>any())).thenReturn(stateStore);
    IngestJobTracker ingestJobTracker = mock(IngestJobTracker.class);
    doNothing().when(ingestJobTracker).jobFailed(Mockito.<IngestJobFailedEvent>any());
    doNothing().when(ingestJobTracker).jobValidated(Mockito.<IngestJobValidatedEvent>any());
    WriteJobToBucket writeJobToBucket = mock(WriteJobToBucket.class);
    doNothing().when(writeJobToBucket).writeJobToBulkImportBucket(Mockito.<BulkImportJob>any(), Mockito.<String>any());
    PlatformExecutor platformExecutor = mock(PlatformExecutor.class);
    doThrow(new RuntimeException("Validating job: {}")).when(platformExecutor)
        .runJobOnPlatform(Mockito.<BulkImportArguments>any());
    Supplier<Instant> validationTimeSupplier = mock(Supplier.class);
    when(validationTimeSupplier.get())
        .thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    BulkImportExecutor bulkImportExecutor = new BulkImportExecutor(new InstanceProperties(), tablePropertiesProvider,
        stateStoreProvider, ingestJobTracker, writeJobToBucket, platformExecutor, validationTimeSupplier);

    ArrayList<String> stringList = new ArrayList<>();
    stringList.add("Validating job: {}");
    BulkImportJob bulkImportJob = mock(BulkImportJob.class);
    when(bulkImportJob.getTableId()).thenReturn("42");
    when(bulkImportJob.getFiles()).thenReturn(stringList);
    Builder builderResult = IngestJob.builder();
    IngestJob buildResult = builderResult.files(new ArrayList<>())
        .id("42")
        .tableId("42")
        .tableName("Table Name")
        .build();
    when(bulkImportJob.toIngestJob()).thenReturn(buildResult);
    when(bulkImportJob.getTableName()).thenReturn("Table Name");
    when(bulkImportJob.getId()).thenReturn("42");

    // Act and Assert
    assertThrows(RuntimeException.class, () -> bulkImportExecutor.runJob(bulkImportJob, "42"));
    verify(validationTimeSupplier, atLeast(1)).get();
    verify(bulkImportJob, atLeast(1)).getFiles();
    verify(bulkImportJob, atLeast(1)).getId();
    verify(bulkImportJob).getTableId();
    verify(bulkImportJob).getTableName();
    verify(bulkImportJob, atLeast(1)).toIngestJob();
    verify(writeJobToBucket).writeJobToBulkImportBucket(isA(BulkImportJob.class), eq("42"));
    verify(platformExecutor).runJobOnPlatform(isA(BulkImportArguments.class));
    verify(tableProperties).getInt(isA(TableProperty.class));
    verify(tablePropertiesProvider).getByName(eq("Table Name"));
    verify(stateStore).getLeafPartitions();
    verify(stateStoreProvider).getStateStore(isA(TableProperties.class));
    verify(ingestJobTracker).jobFailed(isA(IngestJobFailedEvent.class));
    verify(ingestJobTracker).jobValidated(isA(IngestJobValidatedEvent.class));
  }

  /**
   * Test {@link BulkImportExecutor#runJob(BulkImportJob)} with {@code bulkImportJob}.
   * <ul>
   *   <li>Given {@link TableProperties} {@link SleeperPropertyValues#getInt(SleeperProperty)} return one.</li>
   * </ul>
   * <p>
   * Method under test: {@link BulkImportExecutor#runJob(BulkImportJob)}
   */
  @Test
  @DisplayName("Test runJob(BulkImportJob) with 'bulkImportJob'; given TableProperties getInt(SleeperProperty) return one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void BulkImportExecutor.runJob(BulkImportJob)"})
  void testRunJobWithBulkImportJob_givenTablePropertiesGetIntReturnOne()
      throws SleeperPropertiesInvalidException, StateStoreException {
    // Arrange
    TableIndex tableIndex = mock(TableIndex.class);
    Optional<TableStatus> ofResult = Optional.of(TableStatus.uniqueIdAndName("42", "Table Name", true));
    when(tableIndex.getTableByName(Mockito.<String>any())).thenReturn(ofResult);
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.getInt(Mockito.<TableProperty>any())).thenReturn(1);
    doNothing().when(tableProperties).validate();
    when(tableProperties.get(Mockito.<TableProperty>any())).thenReturn("Get");
    when(tableProperties.getStatus()).thenReturn(TableStatus.uniqueIdAndName("42", "Table Name", true));
    Client client = mock(Client.class);
    when(client.loadProperties(Mockito.<TableStatus>any())).thenReturn(tableProperties);
    TablePropertiesStore propertiesStore = new TablePropertiesStore(tableIndex, client);

    TablePropertiesProvider tablePropertiesProvider = new TablePropertiesProvider(new InstanceProperties(),
        propertiesStore);

    StateStore stateStore = mock(StateStore.class);
    when(stateStore.getLeafPartitions()).thenReturn(new ArrayList<>());
    Factory stateStoreFactory = mock(Factory.class);
    when(stateStoreFactory.getStateStore(Mockito.<TableProperties>any())).thenReturn(stateStore);
    StateStoreProvider stateStoreProvider = new StateStoreProvider(3, stateStoreFactory);

    Supplier<Instant> validationTimeSupplier = mock(Supplier.class);
    when(validationTimeSupplier.get())
        .thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    InstanceProperties instanceProperties = new InstanceProperties();
    BulkImportExecutor bulkImportExecutor = new BulkImportExecutor(instanceProperties, tablePropertiesProvider,
        stateStoreProvider, new InMemoryIngestJobTracker(), mock(WriteJobToBucket.class), mock(PlatformExecutor.class),
        validationTimeSupplier);
    BulkImportJob bulkImportJob = mock(BulkImportJob.class);
    when(bulkImportJob.getFiles()).thenReturn(new ArrayList<>());
    Builder builderResult = IngestJob.builder();
    IngestJob buildResult = builderResult.files(new ArrayList<>())
        .id("42")
        .tableId("42")
        .tableName("Table Name")
        .build();
    when(bulkImportJob.toIngestJob()).thenReturn(buildResult);
    when(bulkImportJob.getTableName()).thenReturn("Table Name");
    when(bulkImportJob.getId()).thenReturn("42");

    // Act
    bulkImportExecutor.runJob(bulkImportJob);

    // Assert
    verify(validationTimeSupplier).get();
    verify(bulkImportJob, atLeast(1)).getFiles();
    verify(bulkImportJob, atLeast(1)).getId();
    verify(bulkImportJob).getTableName();
    verify(bulkImportJob).toIngestJob();
    verify(tableProperties).validate();
    verify(tableProperties).getInt(isA(TableProperty.class));
    verify(tableProperties, atLeast(1)).get(Mockito.<TableProperty>any());
    verify(tableProperties).getStatus();
    verify(client).loadProperties(isA(TableStatus.class));
    verify(stateStore).getLeafPartitions();
    verify(stateStoreFactory).getStateStore(isA(TableProperties.class));
    verify(tableIndex).getTableByName(eq("Table Name"));
    IngestJobTracker ingestJobTracker = bulkImportExecutor.ingestJobTracker;
    assertTrue(ingestJobTracker instanceof InMemoryIngestJobTracker);
    List<IngestJobStatus> invalidJobs = ingestJobTracker.getInvalidJobs();
    assertEquals(1, invalidJobs.size());
    assertEquals(1, invalidJobs.get(0).getRunsLatestFirst().size());
  }

  /**
   * Test {@link BulkImportExecutor#runJob(BulkImportJob)} with {@code bulkImportJob}.
   * <ul>
   *   <li>Given {@link TableProperties} {@link SleeperPropertyValues#getInt(SleeperProperty)} return zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link BulkImportExecutor#runJob(BulkImportJob)}
   */
  @Test
  @DisplayName("Test runJob(BulkImportJob) with 'bulkImportJob'; given TableProperties getInt(SleeperProperty) return zero")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void BulkImportExecutor.runJob(BulkImportJob)"})
  void testRunJobWithBulkImportJob_givenTablePropertiesGetIntReturnZero()
      throws SleeperPropertiesInvalidException, StateStoreException {
    // Arrange
    TableIndex tableIndex = mock(TableIndex.class);
    Optional<TableStatus> ofResult = Optional.of(TableStatus.uniqueIdAndName("42", "Table Name", true));
    when(tableIndex.getTableByName(Mockito.<String>any())).thenReturn(ofResult);
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.getInt(Mockito.<TableProperty>any())).thenReturn(0);
    doNothing().when(tableProperties).validate();
    when(tableProperties.get(Mockito.<TableProperty>any())).thenReturn("Get");
    when(tableProperties.getStatus()).thenReturn(TableStatus.uniqueIdAndName("42", "Table Name", true));
    Client client = mock(Client.class);
    when(client.loadProperties(Mockito.<TableStatus>any())).thenReturn(tableProperties);
    TablePropertiesStore propertiesStore = new TablePropertiesStore(tableIndex, client);

    TablePropertiesProvider tablePropertiesProvider = new TablePropertiesProvider(new InstanceProperties(),
        propertiesStore);

    StateStore stateStore = mock(StateStore.class);
    when(stateStore.getLeafPartitions()).thenReturn(new ArrayList<>());
    Factory stateStoreFactory = mock(Factory.class);
    when(stateStoreFactory.getStateStore(Mockito.<TableProperties>any())).thenReturn(stateStore);
    StateStoreProvider stateStoreProvider = new StateStoreProvider(3, stateStoreFactory);

    Supplier<Instant> validationTimeSupplier = mock(Supplier.class);
    when(validationTimeSupplier.get())
        .thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    InstanceProperties instanceProperties = new InstanceProperties();
    BulkImportExecutor bulkImportExecutor = new BulkImportExecutor(instanceProperties, tablePropertiesProvider,
        stateStoreProvider, new InMemoryIngestJobTracker(), mock(WriteJobToBucket.class), mock(PlatformExecutor.class),
        validationTimeSupplier);
    BulkImportJob bulkImportJob = mock(BulkImportJob.class);
    when(bulkImportJob.getFiles()).thenReturn(new ArrayList<>());
    Builder builderResult = IngestJob.builder();
    IngestJob buildResult = builderResult.files(new ArrayList<>())
        .id("42")
        .tableId("42")
        .tableName("Table Name")
        .build();
    when(bulkImportJob.toIngestJob()).thenReturn(buildResult);
    when(bulkImportJob.getTableName()).thenReturn("Table Name");
    when(bulkImportJob.getId()).thenReturn("42");

    // Act
    bulkImportExecutor.runJob(bulkImportJob);

    // Assert
    verify(validationTimeSupplier).get();
    verify(bulkImportJob, atLeast(1)).getFiles();
    verify(bulkImportJob, atLeast(1)).getId();
    verify(bulkImportJob).getTableName();
    verify(bulkImportJob).toIngestJob();
    verify(tableProperties).validate();
    verify(tableProperties).getInt(isA(TableProperty.class));
    verify(tableProperties, atLeast(1)).get(Mockito.<TableProperty>any());
    verify(tableProperties).getStatus();
    verify(client).loadProperties(isA(TableStatus.class));
    verify(stateStore).getLeafPartitions();
    verify(stateStoreFactory).getStateStore(isA(TableProperties.class));
    verify(tableIndex).getTableByName(eq("Table Name"));
    IngestJobTracker ingestJobTracker = bulkImportExecutor.ingestJobTracker;
    assertTrue(ingestJobTracker instanceof InMemoryIngestJobTracker);
    List<IngestJobStatus> invalidJobs = ingestJobTracker.getInvalidJobs();
    assertEquals(1, invalidJobs.size());
    assertEquals(1, invalidJobs.get(0).getRunsLatestFirst().size());
  }
}
