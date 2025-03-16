package sleeper.compaction.core.job.creation;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Random;
import java.util.function.Supplier;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.compaction.core.job.creation.CreateCompactionJobs.BatchJobsWriter;
import sleeper.compaction.core.job.creation.CreateCompactionJobs.BatchMessageSender;
import sleeper.compaction.core.job.creation.CreateCompactionJobs.GenerateBatchId;
import sleeper.compaction.core.job.creation.CreateCompactionJobs.GenerateJobId;
import sleeper.core.partition.Partition;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.table.TableProperties;
import sleeper.core.range.Region;
import sleeper.core.statestore.FileReference;
import sleeper.core.statestore.FileReference.Builder;
import sleeper.core.statestore.StateStore;
import sleeper.core.statestore.StateStoreException;
import sleeper.core.statestore.StateStoreProvider;
import sleeper.core.statestore.StateStoreProvider.Factory;
import sleeper.core.statestore.commit.StateStoreCommitRequestSender;
import sleeper.core.statestore.testutils.FixedStateStoreProvider;
import sleeper.core.statestore.transactionlog.AddTransactionRequest;
import sleeper.core.util.ObjectFactory;
import sleeper.core.util.ObjectFactoryException;

class CreateCompactionJobsDiffblueTest {
  /**
   * Test {@link CreateCompactionJobs#createJobsWithStrategy(TableProperties)}.
   * <p>
   * Method under test: {@link CreateCompactionJobs#createJobsWithStrategy(TableProperties)}
   */
  @Test
  @DisplayName("Test createJobsWithStrategy(TableProperties)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void CreateCompactionJobs.createJobsWithStrategy(TableProperties)"})
  void testCreateJobsWithStrategy() throws IOException, StateStoreException, ObjectFactoryException {
    // Arrange
    StateStore stateStore = mock(StateStore.class);
    when(stateStore.getFileReferences()).thenReturn(new ArrayList<>());
    when(stateStore.getFileReferencesWithNoJobId()).thenReturn(new ArrayList<>());
    when(stateStore.getAllPartitions()).thenReturn(new ArrayList<>());
    doNothing().when(stateStore).addFilesTransaction(Mockito.<AddTransactionRequest>any());
    Factory stateStoreFactory = mock(Factory.class);
    when(stateStoreFactory.getStateStore(Mockito.<TableProperties>any())).thenReturn(stateStore);
    StateStoreProvider stateStoreProvider = new StateStoreProvider(0, stateStoreFactory);

    ObjectFactory objectFactory = ObjectFactory.noUserJars();
    InstanceProperties instanceProperties = new InstanceProperties();
    BatchJobsWriter batchJobsWriter = mock(BatchJobsWriter.class);
    BatchMessageSender batchMessageSender = mock(BatchMessageSender.class);
    StateStoreCommitRequestSender stateStoreCommitSender = mock(StateStoreCommitRequestSender.class);
    GenerateJobId generateJobId = mock(GenerateJobId.class);
    GenerateBatchId generateBatchId = mock(GenerateBatchId.class);
    CreateCompactionJobs createCompactionJobs = new CreateCompactionJobs(objectFactory, instanceProperties,
        stateStoreProvider, batchJobsWriter, batchMessageSender, stateStoreCommitSender, generateJobId, generateBatchId,
        new Random(), mock(Supplier.class));

    // Act
    createCompactionJobs.createJobsWithStrategy(new TableProperties(new InstanceProperties()));

    // Assert
    verify(stateStore).addFilesTransaction(isA(AddTransactionRequest.class));
    verify(stateStore).getFileReferences();
    verify(stateStore).getFileReferencesWithNoJobId();
    verify(stateStore, atLeast(1)).getAllPartitions();
    verify(stateStoreFactory).getStateStore(isA(TableProperties.class));
  }

  /**
   * Test {@link CreateCompactionJobs#createJobsWithStrategy(TableProperties)}.
   * <p>
   * Method under test: {@link CreateCompactionJobs#createJobsWithStrategy(TableProperties)}
   */
  @Test
  @DisplayName("Test createJobsWithStrategy(TableProperties)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void CreateCompactionJobs.createJobsWithStrategy(TableProperties)"})
  void testCreateJobsWithStrategy2() throws IOException, StateStoreException, ObjectFactoryException {
    // Arrange
    ArrayList<FileReference> fileReferenceList = new ArrayList<>();
    Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference buildResult = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    fileReferenceList.add(buildResult);
    StateStore stateStore = mock(StateStore.class);
    when(stateStore.getFileReferences()).thenReturn(fileReferenceList);
    when(stateStore.getFileReferencesWithNoJobId()).thenReturn(new ArrayList<>());
    when(stateStore.getAllPartitions()).thenReturn(new ArrayList<>());
    doNothing().when(stateStore).addFilesTransaction(Mockito.<AddTransactionRequest>any());
    Factory stateStoreFactory = mock(Factory.class);
    when(stateStoreFactory.getStateStore(Mockito.<TableProperties>any())).thenReturn(stateStore);
    StateStoreProvider stateStoreProvider = new StateStoreProvider(3, stateStoreFactory);

    ObjectFactory objectFactory = ObjectFactory.noUserJars();
    InstanceProperties instanceProperties = new InstanceProperties();
    BatchJobsWriter batchJobsWriter = mock(BatchJobsWriter.class);
    BatchMessageSender batchMessageSender = mock(BatchMessageSender.class);
    StateStoreCommitRequestSender stateStoreCommitSender = mock(StateStoreCommitRequestSender.class);
    GenerateJobId generateJobId = mock(GenerateJobId.class);
    GenerateBatchId generateBatchId = mock(GenerateBatchId.class);
    CreateCompactionJobs createCompactionJobs = new CreateCompactionJobs(objectFactory, instanceProperties,
        stateStoreProvider, batchJobsWriter, batchMessageSender, stateStoreCommitSender, generateJobId, generateBatchId,
        new Random(), mock(Supplier.class));

    // Act
    createCompactionJobs.createJobsWithStrategy(new TableProperties(new InstanceProperties()));

    // Assert
    verify(stateStore).addFilesTransaction(isA(AddTransactionRequest.class));
    verify(stateStore).getFileReferences();
    verify(stateStore).getFileReferencesWithNoJobId();
    verify(stateStore, atLeast(1)).getAllPartitions();
    verify(stateStoreFactory).getStateStore(isA(TableProperties.class));
  }

  /**
   * Test {@link CreateCompactionJobs#createJobsWithStrategy(TableProperties)}.
   * <p>
   * Method under test: {@link CreateCompactionJobs#createJobsWithStrategy(TableProperties)}
   */
  @Test
  @DisplayName("Test createJobsWithStrategy(TableProperties)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void CreateCompactionJobs.createJobsWithStrategy(TableProperties)"})
  void testCreateJobsWithStrategy3() throws IOException, StateStoreException, ObjectFactoryException {
    // Arrange
    ArrayList<FileReference> fileReferenceList = new ArrayList<>();
    Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference buildResult = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    fileReferenceList.add(buildResult);
    Builder jobIdResult2 = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference buildResult2 = jobIdResult2
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    fileReferenceList.add(buildResult2);
    StateStore stateStore = mock(StateStore.class);
    when(stateStore.getFileReferences()).thenReturn(fileReferenceList);
    when(stateStore.getFileReferencesWithNoJobId()).thenReturn(new ArrayList<>());
    when(stateStore.getAllPartitions()).thenReturn(new ArrayList<>());
    doNothing().when(stateStore).addFilesTransaction(Mockito.<AddTransactionRequest>any());
    Factory stateStoreFactory = mock(Factory.class);
    when(stateStoreFactory.getStateStore(Mockito.<TableProperties>any())).thenReturn(stateStore);
    StateStoreProvider stateStoreProvider = new StateStoreProvider(3, stateStoreFactory);

    ObjectFactory objectFactory = ObjectFactory.noUserJars();
    InstanceProperties instanceProperties = new InstanceProperties();
    BatchJobsWriter batchJobsWriter = mock(BatchJobsWriter.class);
    BatchMessageSender batchMessageSender = mock(BatchMessageSender.class);
    StateStoreCommitRequestSender stateStoreCommitSender = mock(StateStoreCommitRequestSender.class);
    GenerateJobId generateJobId = mock(GenerateJobId.class);
    GenerateBatchId generateBatchId = mock(GenerateBatchId.class);
    CreateCompactionJobs createCompactionJobs = new CreateCompactionJobs(objectFactory, instanceProperties,
        stateStoreProvider, batchJobsWriter, batchMessageSender, stateStoreCommitSender, generateJobId, generateBatchId,
        new Random(), mock(Supplier.class));

    // Act
    createCompactionJobs.createJobsWithStrategy(new TableProperties(new InstanceProperties()));

    // Assert
    verify(stateStore).addFilesTransaction(isA(AddTransactionRequest.class));
    verify(stateStore).getFileReferences();
    verify(stateStore).getFileReferencesWithNoJobId();
    verify(stateStore, atLeast(1)).getAllPartitions();
    verify(stateStoreFactory).getStateStore(isA(TableProperties.class));
  }

  /**
   * Test {@link CreateCompactionJobs#createJobsWithStrategy(TableProperties)}.
   * <p>
   * Method under test: {@link CreateCompactionJobs#createJobsWithStrategy(TableProperties)}
   */
  @Test
  @DisplayName("Test createJobsWithStrategy(TableProperties)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void CreateCompactionJobs.createJobsWithStrategy(TableProperties)"})
  void testCreateJobsWithStrategy4() throws IOException, StateStoreException, ObjectFactoryException {
    // Arrange
    StateStore stateStore = mock(StateStore.class);
    when(stateStore.getFileReferences()).thenReturn(new ArrayList<>());
    when(stateStore.getFileReferencesWithNoJobId()).thenReturn(new ArrayList<>());
    when(stateStore.getAllPartitions()).thenReturn(new ArrayList<>());
    doNothing().when(stateStore).addFilesTransaction(Mockito.<AddTransactionRequest>any());
    FixedStateStoreProvider stateStoreProvider = new FixedStateStoreProvider(
        new TableProperties(new InstanceProperties()), stateStore);

    ObjectFactory objectFactory = ObjectFactory.noUserJars();
    InstanceProperties instanceProperties = new InstanceProperties();
    BatchJobsWriter batchJobsWriter = mock(BatchJobsWriter.class);
    BatchMessageSender batchMessageSender = mock(BatchMessageSender.class);
    StateStoreCommitRequestSender stateStoreCommitSender = mock(StateStoreCommitRequestSender.class);
    GenerateJobId generateJobId = mock(GenerateJobId.class);
    GenerateBatchId generateBatchId = mock(GenerateBatchId.class);
    CreateCompactionJobs createCompactionJobs = new CreateCompactionJobs(objectFactory, instanceProperties,
        stateStoreProvider, batchJobsWriter, batchMessageSender, stateStoreCommitSender, generateJobId, generateBatchId,
        new Random(), mock(Supplier.class));

    // Act
    createCompactionJobs.createJobsWithStrategy(new TableProperties(new InstanceProperties()));

    // Assert
    verify(stateStore).addFilesTransaction(isA(AddTransactionRequest.class));
    verify(stateStore).getFileReferences();
    verify(stateStore).getFileReferencesWithNoJobId();
    verify(stateStore, atLeast(1)).getAllPartitions();
  }

  /**
   * Test {@link CreateCompactionJobs#createJobsWithStrategy(TableProperties)}.
   * <ul>
   *   <li>Then calls {@link Factory#getStateStore(TableProperties)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CreateCompactionJobs#createJobsWithStrategy(TableProperties)}
   */
  @Test
  @DisplayName("Test createJobsWithStrategy(TableProperties); then calls getStateStore(TableProperties)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void CreateCompactionJobs.createJobsWithStrategy(TableProperties)"})
  void testCreateJobsWithStrategy_thenCallsGetStateStore()
      throws IOException, StateStoreException, ObjectFactoryException {
    // Arrange
    StateStore stateStore = mock(StateStore.class);
    when(stateStore.getFileReferences()).thenReturn(new ArrayList<>());
    when(stateStore.getFileReferencesWithNoJobId()).thenReturn(new ArrayList<>());
    when(stateStore.getAllPartitions()).thenReturn(new ArrayList<>());
    doNothing().when(stateStore).addFilesTransaction(Mockito.<AddTransactionRequest>any());
    Factory stateStoreFactory = mock(Factory.class);
    when(stateStoreFactory.getStateStore(Mockito.<TableProperties>any())).thenReturn(stateStore);
    StateStoreProvider stateStoreProvider = new StateStoreProvider(3, stateStoreFactory);

    ObjectFactory objectFactory = ObjectFactory.noUserJars();
    InstanceProperties instanceProperties = new InstanceProperties();
    BatchJobsWriter batchJobsWriter = mock(BatchJobsWriter.class);
    BatchMessageSender batchMessageSender = mock(BatchMessageSender.class);
    StateStoreCommitRequestSender stateStoreCommitSender = mock(StateStoreCommitRequestSender.class);
    GenerateJobId generateJobId = mock(GenerateJobId.class);
    GenerateBatchId generateBatchId = mock(GenerateBatchId.class);
    CreateCompactionJobs createCompactionJobs = new CreateCompactionJobs(objectFactory, instanceProperties,
        stateStoreProvider, batchJobsWriter, batchMessageSender, stateStoreCommitSender, generateJobId, generateBatchId,
        new Random(), mock(Supplier.class));

    // Act
    createCompactionJobs.createJobsWithStrategy(new TableProperties(new InstanceProperties()));

    // Assert
    verify(stateStore).addFilesTransaction(isA(AddTransactionRequest.class));
    verify(stateStore).getFileReferences();
    verify(stateStore).getFileReferencesWithNoJobId();
    verify(stateStore, atLeast(1)).getAllPartitions();
    verify(stateStoreFactory).getStateStore(isA(TableProperties.class));
  }

  /**
   * Test {@link CreateCompactionJobs#createJobWithForceAllFiles(TableProperties)}.
   * <p>
   * Method under test: {@link CreateCompactionJobs#createJobWithForceAllFiles(TableProperties)}
   */
  @Test
  @DisplayName("Test createJobWithForceAllFiles(TableProperties)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void CreateCompactionJobs.createJobWithForceAllFiles(TableProperties)"})
  void testCreateJobWithForceAllFiles() throws IOException, StateStoreException, ObjectFactoryException {
    // Arrange
    StateStore stateStore = mock(StateStore.class);
    when(stateStore.getFileReferences()).thenReturn(new ArrayList<>());
    when(stateStore.getFileReferencesWithNoJobId()).thenReturn(new ArrayList<>());
    when(stateStore.getAllPartitions()).thenReturn(new ArrayList<>());
    doNothing().when(stateStore).addFilesTransaction(Mockito.<AddTransactionRequest>any());
    Factory stateStoreFactory = mock(Factory.class);
    when(stateStoreFactory.getStateStore(Mockito.<TableProperties>any())).thenReturn(stateStore);
    StateStoreProvider stateStoreProvider = new StateStoreProvider(0, stateStoreFactory);

    ObjectFactory objectFactory = ObjectFactory.noUserJars();
    InstanceProperties instanceProperties = new InstanceProperties();
    BatchJobsWriter batchJobsWriter = mock(BatchJobsWriter.class);
    BatchMessageSender batchMessageSender = mock(BatchMessageSender.class);
    StateStoreCommitRequestSender stateStoreCommitSender = mock(StateStoreCommitRequestSender.class);
    GenerateJobId generateJobId = mock(GenerateJobId.class);
    GenerateBatchId generateBatchId = mock(GenerateBatchId.class);
    CreateCompactionJobs createCompactionJobs = new CreateCompactionJobs(objectFactory, instanceProperties,
        stateStoreProvider, batchJobsWriter, batchMessageSender, stateStoreCommitSender, generateJobId, generateBatchId,
        new Random(), mock(Supplier.class));

    // Act
    createCompactionJobs.createJobWithForceAllFiles(new TableProperties(new InstanceProperties()));

    // Assert
    verify(stateStore).addFilesTransaction(isA(AddTransactionRequest.class));
    verify(stateStore).getFileReferences();
    verify(stateStore).getFileReferencesWithNoJobId();
    verify(stateStore, atLeast(1)).getAllPartitions();
    verify(stateStoreFactory).getStateStore(isA(TableProperties.class));
  }

  /**
   * Test {@link CreateCompactionJobs#createJobWithForceAllFiles(TableProperties)}.
   * <p>
   * Method under test: {@link CreateCompactionJobs#createJobWithForceAllFiles(TableProperties)}
   */
  @Test
  @DisplayName("Test createJobWithForceAllFiles(TableProperties)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void CreateCompactionJobs.createJobWithForceAllFiles(TableProperties)"})
  void testCreateJobWithForceAllFiles2() throws IOException, StateStoreException, ObjectFactoryException {
    // Arrange
    ArrayList<FileReference> fileReferenceList = new ArrayList<>();
    Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference buildResult = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    fileReferenceList.add(buildResult);
    StateStore stateStore = mock(StateStore.class);
    when(stateStore.getFileReferences()).thenReturn(fileReferenceList);
    when(stateStore.getFileReferencesWithNoJobId()).thenReturn(new ArrayList<>());
    when(stateStore.getAllPartitions()).thenReturn(new ArrayList<>());
    doNothing().when(stateStore).addFilesTransaction(Mockito.<AddTransactionRequest>any());
    Factory stateStoreFactory = mock(Factory.class);
    when(stateStoreFactory.getStateStore(Mockito.<TableProperties>any())).thenReturn(stateStore);
    StateStoreProvider stateStoreProvider = new StateStoreProvider(3, stateStoreFactory);

    ObjectFactory objectFactory = ObjectFactory.noUserJars();
    InstanceProperties instanceProperties = new InstanceProperties();
    BatchJobsWriter batchJobsWriter = mock(BatchJobsWriter.class);
    BatchMessageSender batchMessageSender = mock(BatchMessageSender.class);
    StateStoreCommitRequestSender stateStoreCommitSender = mock(StateStoreCommitRequestSender.class);
    GenerateJobId generateJobId = mock(GenerateJobId.class);
    GenerateBatchId generateBatchId = mock(GenerateBatchId.class);
    CreateCompactionJobs createCompactionJobs = new CreateCompactionJobs(objectFactory, instanceProperties,
        stateStoreProvider, batchJobsWriter, batchMessageSender, stateStoreCommitSender, generateJobId, generateBatchId,
        new Random(), mock(Supplier.class));

    // Act
    createCompactionJobs.createJobWithForceAllFiles(new TableProperties(new InstanceProperties()));

    // Assert
    verify(stateStore).addFilesTransaction(isA(AddTransactionRequest.class));
    verify(stateStore).getFileReferences();
    verify(stateStore).getFileReferencesWithNoJobId();
    verify(stateStore, atLeast(1)).getAllPartitions();
    verify(stateStoreFactory).getStateStore(isA(TableProperties.class));
  }

  /**
   * Test {@link CreateCompactionJobs#createJobWithForceAllFiles(TableProperties)}.
   * <p>
   * Method under test: {@link CreateCompactionJobs#createJobWithForceAllFiles(TableProperties)}
   */
  @Test
  @DisplayName("Test createJobWithForceAllFiles(TableProperties)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void CreateCompactionJobs.createJobWithForceAllFiles(TableProperties)"})
  void testCreateJobWithForceAllFiles3() throws IOException, StateStoreException, ObjectFactoryException {
    // Arrange
    ArrayList<FileReference> fileReferenceList = new ArrayList<>();
    Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference buildResult = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    fileReferenceList.add(buildResult);
    Builder jobIdResult2 = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference buildResult2 = jobIdResult2
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    fileReferenceList.add(buildResult2);
    StateStore stateStore = mock(StateStore.class);
    when(stateStore.getFileReferences()).thenReturn(fileReferenceList);
    when(stateStore.getFileReferencesWithNoJobId()).thenReturn(new ArrayList<>());
    when(stateStore.getAllPartitions()).thenReturn(new ArrayList<>());
    doNothing().when(stateStore).addFilesTransaction(Mockito.<AddTransactionRequest>any());
    Factory stateStoreFactory = mock(Factory.class);
    when(stateStoreFactory.getStateStore(Mockito.<TableProperties>any())).thenReturn(stateStore);
    StateStoreProvider stateStoreProvider = new StateStoreProvider(3, stateStoreFactory);

    ObjectFactory objectFactory = ObjectFactory.noUserJars();
    InstanceProperties instanceProperties = new InstanceProperties();
    BatchJobsWriter batchJobsWriter = mock(BatchJobsWriter.class);
    BatchMessageSender batchMessageSender = mock(BatchMessageSender.class);
    StateStoreCommitRequestSender stateStoreCommitSender = mock(StateStoreCommitRequestSender.class);
    GenerateJobId generateJobId = mock(GenerateJobId.class);
    GenerateBatchId generateBatchId = mock(GenerateBatchId.class);
    CreateCompactionJobs createCompactionJobs = new CreateCompactionJobs(objectFactory, instanceProperties,
        stateStoreProvider, batchJobsWriter, batchMessageSender, stateStoreCommitSender, generateJobId, generateBatchId,
        new Random(), mock(Supplier.class));

    // Act
    createCompactionJobs.createJobWithForceAllFiles(new TableProperties(new InstanceProperties()));

    // Assert
    verify(stateStore).addFilesTransaction(isA(AddTransactionRequest.class));
    verify(stateStore).getFileReferences();
    verify(stateStore).getFileReferencesWithNoJobId();
    verify(stateStore, atLeast(1)).getAllPartitions();
    verify(stateStoreFactory).getStateStore(isA(TableProperties.class));
  }

  /**
   * Test {@link CreateCompactionJobs#createJobWithForceAllFiles(TableProperties)}.
   * <p>
   * Method under test: {@link CreateCompactionJobs#createJobWithForceAllFiles(TableProperties)}
   */
  @Test
  @DisplayName("Test createJobWithForceAllFiles(TableProperties)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void CreateCompactionJobs.createJobWithForceAllFiles(TableProperties)"})
  void testCreateJobWithForceAllFiles4() throws IOException, StateStoreException, ObjectFactoryException {
    // Arrange
    ArrayList<Partition> partitionList = new ArrayList<>();
    Partition.Builder builderResult = Partition.builder();
    Partition.Builder parentPartitionIdResult = builderResult.childPartitionIds(new ArrayList<>())
        .dimension(1)
        .id("42")
        .leafPartition(true)
        .parentPartitionId("42");
    Partition buildResult = parentPartitionIdResult.region(new Region(new ArrayList<>())).build();
    partitionList.add(buildResult);
    StateStore stateStore = mock(StateStore.class);
    when(stateStore.getFileReferences()).thenReturn(new ArrayList<>());
    when(stateStore.getFileReferencesWithNoJobId()).thenReturn(new ArrayList<>());
    when(stateStore.getAllPartitions()).thenReturn(partitionList);
    doNothing().when(stateStore).addFilesTransaction(Mockito.<AddTransactionRequest>any());
    Factory stateStoreFactory = mock(Factory.class);
    when(stateStoreFactory.getStateStore(Mockito.<TableProperties>any())).thenReturn(stateStore);
    StateStoreProvider stateStoreProvider = new StateStoreProvider(3, stateStoreFactory);

    ObjectFactory objectFactory = ObjectFactory.noUserJars();
    InstanceProperties instanceProperties = new InstanceProperties();
    BatchJobsWriter batchJobsWriter = mock(BatchJobsWriter.class);
    BatchMessageSender batchMessageSender = mock(BatchMessageSender.class);
    StateStoreCommitRequestSender stateStoreCommitSender = mock(StateStoreCommitRequestSender.class);
    GenerateJobId generateJobId = mock(GenerateJobId.class);
    GenerateBatchId generateBatchId = mock(GenerateBatchId.class);
    CreateCompactionJobs createCompactionJobs = new CreateCompactionJobs(objectFactory, instanceProperties,
        stateStoreProvider, batchJobsWriter, batchMessageSender, stateStoreCommitSender, generateJobId, generateBatchId,
        new Random(), mock(Supplier.class));

    // Act
    createCompactionJobs.createJobWithForceAllFiles(new TableProperties(new InstanceProperties()));

    // Assert
    verify(stateStore).addFilesTransaction(isA(AddTransactionRequest.class));
    verify(stateStore).getFileReferences();
    verify(stateStore).getFileReferencesWithNoJobId();
    verify(stateStore, atLeast(1)).getAllPartitions();
    verify(stateStoreFactory).getStateStore(isA(TableProperties.class));
  }

  /**
   * Test {@link CreateCompactionJobs#createJobWithForceAllFiles(TableProperties)}.
   * <p>
   * Method under test: {@link CreateCompactionJobs#createJobWithForceAllFiles(TableProperties)}
   */
  @Test
  @DisplayName("Test createJobWithForceAllFiles(TableProperties)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void CreateCompactionJobs.createJobWithForceAllFiles(TableProperties)"})
  void testCreateJobWithForceAllFiles5() throws IOException, StateStoreException, ObjectFactoryException {
    // Arrange
    StateStore stateStore = mock(StateStore.class);
    when(stateStore.getFileReferences()).thenReturn(new ArrayList<>());
    when(stateStore.getFileReferencesWithNoJobId()).thenReturn(new ArrayList<>());
    when(stateStore.getAllPartitions()).thenReturn(new ArrayList<>());
    doNothing().when(stateStore).addFilesTransaction(Mockito.<AddTransactionRequest>any());
    FixedStateStoreProvider stateStoreProvider = new FixedStateStoreProvider(
        new TableProperties(new InstanceProperties()), stateStore);

    ObjectFactory objectFactory = ObjectFactory.noUserJars();
    InstanceProperties instanceProperties = new InstanceProperties();
    BatchJobsWriter batchJobsWriter = mock(BatchJobsWriter.class);
    BatchMessageSender batchMessageSender = mock(BatchMessageSender.class);
    StateStoreCommitRequestSender stateStoreCommitSender = mock(StateStoreCommitRequestSender.class);
    GenerateJobId generateJobId = mock(GenerateJobId.class);
    GenerateBatchId generateBatchId = mock(GenerateBatchId.class);
    CreateCompactionJobs createCompactionJobs = new CreateCompactionJobs(objectFactory, instanceProperties,
        stateStoreProvider, batchJobsWriter, batchMessageSender, stateStoreCommitSender, generateJobId, generateBatchId,
        new Random(), mock(Supplier.class));

    // Act
    createCompactionJobs.createJobWithForceAllFiles(new TableProperties(new InstanceProperties()));

    // Assert
    verify(stateStore).addFilesTransaction(isA(AddTransactionRequest.class));
    verify(stateStore).getFileReferences();
    verify(stateStore).getFileReferencesWithNoJobId();
    verify(stateStore, atLeast(1)).getAllPartitions();
  }

  /**
   * Test {@link CreateCompactionJobs#createJobWithForceAllFiles(TableProperties)}.
   * <ul>
   *   <li>Then calls {@link Factory#getStateStore(TableProperties)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CreateCompactionJobs#createJobWithForceAllFiles(TableProperties)}
   */
  @Test
  @DisplayName("Test createJobWithForceAllFiles(TableProperties); then calls getStateStore(TableProperties)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void CreateCompactionJobs.createJobWithForceAllFiles(TableProperties)"})
  void testCreateJobWithForceAllFiles_thenCallsGetStateStore()
      throws IOException, StateStoreException, ObjectFactoryException {
    // Arrange
    StateStore stateStore = mock(StateStore.class);
    when(stateStore.getFileReferences()).thenReturn(new ArrayList<>());
    when(stateStore.getFileReferencesWithNoJobId()).thenReturn(new ArrayList<>());
    when(stateStore.getAllPartitions()).thenReturn(new ArrayList<>());
    doNothing().when(stateStore).addFilesTransaction(Mockito.<AddTransactionRequest>any());
    Factory stateStoreFactory = mock(Factory.class);
    when(stateStoreFactory.getStateStore(Mockito.<TableProperties>any())).thenReturn(stateStore);
    StateStoreProvider stateStoreProvider = new StateStoreProvider(3, stateStoreFactory);

    ObjectFactory objectFactory = ObjectFactory.noUserJars();
    InstanceProperties instanceProperties = new InstanceProperties();
    BatchJobsWriter batchJobsWriter = mock(BatchJobsWriter.class);
    BatchMessageSender batchMessageSender = mock(BatchMessageSender.class);
    StateStoreCommitRequestSender stateStoreCommitSender = mock(StateStoreCommitRequestSender.class);
    GenerateJobId generateJobId = mock(GenerateJobId.class);
    GenerateBatchId generateBatchId = mock(GenerateBatchId.class);
    CreateCompactionJobs createCompactionJobs = new CreateCompactionJobs(objectFactory, instanceProperties,
        stateStoreProvider, batchJobsWriter, batchMessageSender, stateStoreCommitSender, generateJobId, generateBatchId,
        new Random(), mock(Supplier.class));

    // Act
    createCompactionJobs.createJobWithForceAllFiles(new TableProperties(new InstanceProperties()));

    // Assert
    verify(stateStore).addFilesTransaction(isA(AddTransactionRequest.class));
    verify(stateStore).getFileReferences();
    verify(stateStore).getFileReferencesWithNoJobId();
    verify(stateStore, atLeast(1)).getAllPartitions();
    verify(stateStoreFactory).getStateStore(isA(TableProperties.class));
  }
}
