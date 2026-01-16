/*
 * Copyright 2022-2024 Crown Copyright
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package sleeper.compaction.core.job.creation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import sleeper.compaction.core.job.CompactionJob;
import sleeper.compaction.core.job.CompactionJobFactory;
import sleeper.compaction.core.job.creation.CreateCompactionJobs.BatchJobsWriter;
import sleeper.compaction.core.job.creation.CreateCompactionJobs.BatchMessageSender;
import sleeper.compaction.core.job.creation.strategy.CompactionStrategy;
import sleeper.compaction.core.job.creation.strategy.CompactionStrategyIndex;
import sleeper.compaction.core.job.dispatch.CompactionJobDispatchRequest;
import sleeper.core.partition.PartitionTree;
import sleeper.core.partition.PartitionsBuilder;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.table.TableProperties;
import sleeper.core.schema.Schema;
import sleeper.core.schema.type.StringType;
import sleeper.core.statestore.FileReference;
import sleeper.core.statestore.FileReferenceFactory;
import sleeper.core.statestore.FilesReportTestHelper;
import sleeper.core.statestore.StateStore;
import sleeper.core.statestore.StateStoreProvider;
import sleeper.core.statestore.commit.StateStoreCommitRequest;
import sleeper.core.statestore.testutils.InMemoryTransactionLogStateStore;
import sleeper.core.statestore.testutils.InMemoryTransactionLogs;
import sleeper.core.util.ObjectFactory;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThat;
import static sleeper.core.properties.instance.CdkDefinedInstanceProperty.DATA_BUCKET;
import static sleeper.core.properties.table.TableProperty.COMPACTION_FILES_BATCH_SIZE;
import static sleeper.core.properties.table.TableProperty.COMPACTION_JOB_CREATION_LIMIT;
import static sleeper.core.properties.table.TableProperty.COMPACTION_JOB_ID_ASSIGNMENT_COMMIT_ASYNC;
import static sleeper.core.properties.table.TableProperty.COMPACTION_JOB_SEND_BATCH_SIZE;
import static sleeper.core.properties.table.TableProperty.COMPACTION_STRATEGY_CLASS;
import static sleeper.core.properties.testutils.InstancePropertiesTestHelper.createTestInstanceProperties;
import static sleeper.core.properties.testutils.TablePropertiesTestHelper.createTestTableProperties;
import static sleeper.core.schema.SchemaTestHelper.schemaWithKey;
import static sleeper.core.statestore.testutils.StateStoreUpdatesWrapper.update;

public class CreateCompactionJobsClaudeTest {

    private static final Instant DEFAULT_FILE_UPDATE_TIME = FilesReportTestHelper.DEFAULT_UPDATE_TIME;
    private static final Instant DEFAULT_TIME = Instant.parse("2024-06-01T10:00:00Z");

    private final Schema schema = schemaWithKey("key", new StringType());
    private final InstanceProperties instanceProperties = createTestInstanceProperties();
    private final InMemoryTransactionLogs transactionLogs = new InMemoryTransactionLogs();
    private final List<CompactionJobDispatchRequest> sentDispatchRequests = new ArrayList<>();
    private final List<WrittenBatch> writtenBatches = new ArrayList<>();
    private final List<StateStoreCommitRequest> sentCommitRequests = new ArrayList<>();

    private PartitionTree partitions;
    private FileReferenceFactory fileFactory;
    private TableProperties tableProperties;
    private StateStore stateStore;

    private final AtomicInteger jobIdCounter = new AtomicInteger(0);
    private final AtomicInteger batchIdCounter = new AtomicInteger(0);

    @BeforeEach
    void setUp() {
        partitions = new PartitionsBuilder(schema).singlePartition("root").buildTree();
        fileFactory = FileReferenceFactory.fromUpdatedAt(partitions, DEFAULT_FILE_UPDATE_TIME);
        tableProperties = createTableProperties();
        stateStore = createStateStore(tableProperties);
    }

    @Nested
    @DisplayName("Constructor")
    class Constructor {

        @Test
        void shouldCreateInstanceWithAllDependencies() {
            // When
            CreateCompactionJobs createJobs = createCompactionJobs(stateStore);

            // Then
            assertThat(createJobs).isNotNull();
        }
    }

    @Nested
    @DisplayName("createJobsWithStrategy")
    class CreateJobsWithStrategy {

        @Test
        void shouldCreateNoJobsWhenNoFilesExist() throws Exception {
            // Given
            CreateCompactionJobs createJobs = createCompactionJobs(stateStore);

            // When
            createJobs.createJobsWithStrategy(tableProperties);

            // Then
            assertThat(writtenBatches).isEmpty();
            assertThat(sentDispatchRequests).isEmpty();
        }

        @Test
        void shouldCreateJobWhenFilesExistAndStrategyCreatesJob() throws Exception {
            // Given
            FileReference file1 = fileFactory.rootFile("file1.parquet", 100L);
            FileReference file2 = fileFactory.rootFile("file2.parquet", 200L);
            update(stateStore).addFiles(List.of(file1, file2));

            tableProperties.set(COMPACTION_STRATEGY_CLASS, TestCompactionStrategy.class.getName());
            CreateCompactionJobs createJobs = createCompactionJobs(stateStore);

            // When
            createJobs.createJobsWithStrategy(tableProperties);

            // Then
            assertThat(writtenBatches).hasSize(1);
            assertThat(writtenBatches.get(0).jobs()).hasSize(1);
            assertThat(sentDispatchRequests).hasSize(1);
        }

        @Test
        void shouldCreateMultipleJobsInSameBatch() throws Exception {
            // Given
            FileReference file1 = fileFactory.rootFile("file1.parquet", 100L);
            FileReference file2 = fileFactory.rootFile("file2.parquet", 200L);
            FileReference file3 = fileFactory.rootFile("file3.parquet", 300L);
            FileReference file4 = fileFactory.rootFile("file4.parquet", 400L);
            update(stateStore).addFiles(List.of(file1, file2, file3, file4));

            tableProperties.set(COMPACTION_STRATEGY_CLASS, TwoJobsCompactionStrategy.class.getName());
            tableProperties.set(COMPACTION_JOB_SEND_BATCH_SIZE, "10");
            CreateCompactionJobs createJobs = createCompactionJobs(stateStore);

            // When
            createJobs.createJobsWithStrategy(tableProperties);

            // Then
            assertThat(writtenBatches).hasSize(1);
            assertThat(writtenBatches.get(0).jobs()).hasSize(2);
            assertThat(sentDispatchRequests).hasSize(1);
        }

        @Test
        void shouldSplitJobsIntoMultipleBatches() throws Exception {
            // Given
            FileReference file1 = fileFactory.rootFile("file1.parquet", 100L);
            FileReference file2 = fileFactory.rootFile("file2.parquet", 200L);
            FileReference file3 = fileFactory.rootFile("file3.parquet", 300L);
            FileReference file4 = fileFactory.rootFile("file4.parquet", 400L);
            update(stateStore).addFiles(List.of(file1, file2, file3, file4));

            tableProperties.set(COMPACTION_STRATEGY_CLASS, TwoJobsCompactionStrategy.class.getName());
            tableProperties.set(COMPACTION_JOB_SEND_BATCH_SIZE, "1");
            CreateCompactionJobs createJobs = createCompactionJobs(stateStore);

            // When
            createJobs.createJobsWithStrategy(tableProperties);

            // Then
            assertThat(writtenBatches).hasSize(2);
            assertThat(writtenBatches.get(0).jobs()).hasSize(1);
            assertThat(writtenBatches.get(1).jobs()).hasSize(1);
            assertThat(sentDispatchRequests).hasSize(2);
        }

        @Test
        void shouldUseSynchronousCommitWhenAsyncFlagIsFalse() throws Exception {
            // Given
            FileReference file1 = fileFactory.rootFile("file1.parquet", 100L);
            FileReference file2 = fileFactory.rootFile("file2.parquet", 200L);
            update(stateStore).addFiles(List.of(file1, file2));

            tableProperties.set(COMPACTION_STRATEGY_CLASS, TestCompactionStrategy.class.getName());
            tableProperties.set(COMPACTION_JOB_ID_ASSIGNMENT_COMMIT_ASYNC, "false");
            CreateCompactionJobs createJobs = createCompactionJobs(stateStore);

            // When
            createJobs.createJobsWithStrategy(tableProperties);

            // Then - files should be assigned directly to the state store
            assertThat(stateStore.getFileReferences())
                    .extracting(FileReference::getJobId)
                    .allMatch(jobId -> jobId != null);
            assertThat(sentCommitRequests).isEmpty();
        }

        @Test
        void shouldUseAsyncCommitWhenAsyncFlagIsTrue() throws Exception {
            // Given
            FileReference file1 = fileFactory.rootFile("file1.parquet", 100L);
            FileReference file2 = fileFactory.rootFile("file2.parquet", 200L);
            update(stateStore).addFiles(List.of(file1, file2));

            tableProperties.set(COMPACTION_STRATEGY_CLASS, TestCompactionStrategy.class.getName());
            tableProperties.set(COMPACTION_JOB_ID_ASSIGNMENT_COMMIT_ASYNC, "true");
            CreateCompactionJobs createJobs = createCompactionJobs(stateStore);

            // When
            createJobs.createJobsWithStrategy(tableProperties);

            // Then - commit request should be sent to the queue
            assertThat(sentCommitRequests).hasSize(1);
            // Files in the state store shouldn't have job IDs yet (async)
            assertThat(stateStore.getFileReferences())
                    .extracting(FileReference::getJobId)
                    .allMatch(jobId -> jobId == null);
        }

        @Test
        void shouldLimitJobsWhenExceedingCreationLimit() throws Exception {
            // Given
            List<FileReference> files = new ArrayList<>();
            for (int i = 0; i < 20; i++) {
                files.add(fileFactory.rootFile("file" + i + ".parquet", 100L));
            }
            update(stateStore).addFiles(files);

            tableProperties.set(COMPACTION_STRATEGY_CLASS, ManyJobsCompactionStrategy.class.getName());
            tableProperties.set(COMPACTION_JOB_CREATION_LIMIT, "5");
            tableProperties.set(COMPACTION_JOB_ID_ASSIGNMENT_COMMIT_ASYNC, "false");

            // Use a seeded random for deterministic tests
            Random seededRandom = new Random(12345L);
            CreateCompactionJobs createJobs = createCompactionJobsWithRandom(stateStore, seededRandom);

            // When
            createJobs.createJobsWithStrategy(tableProperties);

            // Then
            int totalJobs = writtenBatches.stream().mapToInt(b -> b.jobs().size()).sum();
            assertThat(totalJobs).isEqualTo(5);
        }

        @Test
        void shouldPreSplitFilesInNonLeafPartitions() throws Exception {
            // Given - partition tree with a split
            partitions = new PartitionsBuilder(schema)
                    .rootFirst("root")
                    .splitToNewChildren("root", "left", "right", "b")
                    .buildTree();
            fileFactory = FileReferenceFactory.fromUpdatedAt(partitions, DEFAULT_FILE_UPDATE_TIME);
            stateStore = createStateStore(tableProperties);
            update(stateStore).initialise(partitions.getAllPartitions());

            // Add a file on the non-leaf root partition (needs splitting)
            FileReference rootFile = FileReference.builder()
                    .filename("root-file.parquet")
                    .partitionId("root")
                    .numberOfRecords(100L)
                    .lastStateStoreUpdateTime(DEFAULT_FILE_UPDATE_TIME)
                    .build();
            update(stateStore).addFile(rootFile);

            tableProperties.set(COMPACTION_STRATEGY_CLASS, TestCompactionStrategy.class.getName());
            CreateCompactionJobs createJobs = createCompactionJobs(stateStore);

            // When
            createJobs.createJobsWithStrategy(tableProperties);

            // Then - the root file should have been split to child partitions
            List<FileReference> fileRefs = stateStore.getFileReferences();
            assertThat(fileRefs)
                    .extracting(FileReference::getPartitionId)
                    .doesNotContain("root");
        }
    }

    @Nested
    @DisplayName("createJobWithForceAllFiles")
    class CreateJobWithForceAllFiles {

        @Test
        void shouldCreateJobsForLeftoverFilesNotAssignedByStrategy() throws Exception {
            // Given
            FileReference file1 = fileFactory.rootFile("file1.parquet", 100L);
            FileReference file2 = fileFactory.rootFile("file2.parquet", 200L);
            FileReference file3 = fileFactory.rootFile("file3.parquet", 300L);
            update(stateStore).addFiles(List.of(file1, file2, file3));

            // Strategy that creates no jobs, leaving all files as leftover
            tableProperties.set(COMPACTION_STRATEGY_CLASS, NoJobsCompactionStrategy.class.getName());
            tableProperties.set(COMPACTION_FILES_BATCH_SIZE, "10");
            CreateCompactionJobs createJobs = createCompactionJobs(stateStore);

            // When
            createJobs.createJobWithForceAllFiles(tableProperties);

            // Then - should create a job for the leftover files
            assertThat(writtenBatches).hasSize(1);
            assertThat(writtenBatches.get(0).jobs()).hasSize(1);
        }

        @Test
        void shouldCreateMultipleJobsWhenLeftoverFilesExceedBatchSize() throws Exception {
            // Given
            FileReference file1 = fileFactory.rootFile("file1.parquet", 100L);
            FileReference file2 = fileFactory.rootFile("file2.parquet", 200L);
            FileReference file3 = fileFactory.rootFile("file3.parquet", 300L);
            FileReference file4 = fileFactory.rootFile("file4.parquet", 400L);
            FileReference file5 = fileFactory.rootFile("file5.parquet", 500L);
            update(stateStore).addFiles(List.of(file1, file2, file3, file4, file5));

            tableProperties.set(COMPACTION_STRATEGY_CLASS, NoJobsCompactionStrategy.class.getName());
            tableProperties.set(COMPACTION_FILES_BATCH_SIZE, "2");
            CreateCompactionJobs createJobs = createCompactionJobs(stateStore);

            // When
            createJobs.createJobWithForceAllFiles(tableProperties);

            // Then - should create 3 jobs (2 full batches of 2, plus 1 with remaining file)
            int totalJobs = writtenBatches.stream().mapToInt(b -> b.jobs().size()).sum();
            assertThat(totalJobs).isEqualTo(3);
        }

        @Test
        void shouldNotCreateJobsForFilesAlreadyAssignedByStrategy() throws Exception {
            // Given
            FileReference file1 = fileFactory.rootFile("file1.parquet", 100L);
            FileReference file2 = fileFactory.rootFile("file2.parquet", 200L);
            FileReference file3 = fileFactory.rootFile("file3.parquet", 300L);
            update(stateStore).addFiles(List.of(file1, file2, file3));

            // Strategy that creates a job for first two files
            tableProperties.set(COMPACTION_STRATEGY_CLASS, TestCompactionStrategy.class.getName());
            tableProperties.set(COMPACTION_FILES_BATCH_SIZE, "10");
            CreateCompactionJobs createJobs = createCompactionJobs(stateStore);

            // When
            createJobs.createJobWithForceAllFiles(tableProperties);

            // Then - should create 2 jobs total (1 from strategy + 1 for leftover)
            int totalJobs = writtenBatches.stream().mapToInt(b -> b.jobs().size()).sum();
            assertThat(totalJobs).isEqualTo(2);
        }

        @Test
        void shouldOnlyCreateJobsForFilesInLeafPartitions() throws Exception {
            // Given - partition tree with a split
            partitions = new PartitionsBuilder(schema)
                    .rootFirst("root")
                    .splitToNewChildren("root", "left", "right", "b")
                    .buildTree();
            fileFactory = FileReferenceFactory.fromUpdatedAt(partitions, DEFAULT_FILE_UPDATE_TIME);
            stateStore = createStateStore(tableProperties);
            update(stateStore).initialise(partitions.getAllPartitions());

            // Add files in leaf partitions
            FileReference leftFile = FileReference.builder()
                    .filename("left-file.parquet")
                    .partitionId("left")
                    .numberOfRecords(100L)
                    .lastStateStoreUpdateTime(DEFAULT_FILE_UPDATE_TIME)
                    .build();
            FileReference rightFile = FileReference.builder()
                    .filename("right-file.parquet")
                    .partitionId("right")
                    .numberOfRecords(100L)
                    .lastStateStoreUpdateTime(DEFAULT_FILE_UPDATE_TIME)
                    .build();
            update(stateStore).addFiles(List.of(leftFile, rightFile));

            tableProperties.set(COMPACTION_STRATEGY_CLASS, NoJobsCompactionStrategy.class.getName());
            tableProperties.set(COMPACTION_FILES_BATCH_SIZE, "10");
            CreateCompactionJobs createJobs = createCompactionJobs(stateStore);

            // When
            createJobs.createJobWithForceAllFiles(tableProperties);

            // Then - should create 2 jobs (one for each leaf partition)
            int totalJobs = writtenBatches.stream().mapToInt(b -> b.jobs().size()).sum();
            assertThat(totalJobs).isEqualTo(2);
        }

        @Test
        void shouldNotCreateJobsForFilesAlreadyWithJobId() throws Exception {
            // Given
            FileReference file1 = fileFactory.rootFile("file1.parquet", 100L);
            FileReference file2 = FileReference.builder()
                    .filename("file2.parquet")
                    .partitionId("root")
                    .numberOfRecords(200L)
                    .lastStateStoreUpdateTime(DEFAULT_FILE_UPDATE_TIME)
                    .jobId("existing-job-id")
                    .build();
            update(stateStore).addFiles(List.of(file1, file2));

            tableProperties.set(COMPACTION_STRATEGY_CLASS, NoJobsCompactionStrategy.class.getName());
            tableProperties.set(COMPACTION_FILES_BATCH_SIZE, "10");
            CreateCompactionJobs createJobs = createCompactionJobs(stateStore);

            // When
            createJobs.createJobWithForceAllFiles(tableProperties);

            // Then - should create a job for only file1
            assertThat(writtenBatches).hasSize(1);
            List<CompactionJob> jobs = writtenBatches.get(0).jobs();
            assertThat(jobs).hasSize(1);
            assertThat(jobs.get(0).getInputFiles()).containsExactly("file1.parquet");
        }

        @Test
        void shouldHandleNoFilesGracefully() throws Exception {
            // Given - no files in state store
            tableProperties.set(COMPACTION_STRATEGY_CLASS, NoJobsCompactionStrategy.class.getName());
            CreateCompactionJobs createJobs = createCompactionJobs(stateStore);

            // When
            createJobs.createJobWithForceAllFiles(tableProperties);

            // Then
            assertThat(writtenBatches).isEmpty();
            assertThat(sentDispatchRequests).isEmpty();
        }
    }

    @Nested
    @DisplayName("GenerateJobId.random()")
    class GenerateJobIdRandom {

        @Test
        void shouldReturnNonNullGenerateJobId() {
            // When
            CreateCompactionJobs.GenerateJobId generator = CreateCompactionJobs.GenerateJobId.random();

            // Then
            assertThat(generator).isNotNull();
        }

        @Test
        void shouldGenerateValidUuidString() {
            // Given
            CreateCompactionJobs.GenerateJobId generator = CreateCompactionJobs.GenerateJobId.random();

            // When
            String jobId = generator.generate();

            // Then
            assertThat(jobId).isNotNull();
            assertThat(UUID.fromString(jobId)).isNotNull();
        }

        @Test
        void shouldGenerateDifferentValuesOnEachCall() {
            // Given
            CreateCompactionJobs.GenerateJobId generator = CreateCompactionJobs.GenerateJobId.random();

            // When
            String jobId1 = generator.generate();
            String jobId2 = generator.generate();
            String jobId3 = generator.generate();

            // Then
            assertThat(jobId1).isNotEqualTo(jobId2);
            assertThat(jobId2).isNotEqualTo(jobId3);
            assertThat(jobId1).isNotEqualTo(jobId3);
        }

        @Test
        void shouldGenerateUniqueValuesAcrossMultipleCalls() {
            // Given
            CreateCompactionJobs.GenerateJobId generator = CreateCompactionJobs.GenerateJobId.random();
            Set<String> generatedIds = new HashSet<>();

            // When
            for (int i = 0; i < 100; i++) {
                generatedIds.add(generator.generate());
            }

            // Then
            assertThat(generatedIds).hasSize(100);
        }

        @Test
        void shouldGenerateUuidVersion4Format() {
            // Given
            CreateCompactionJobs.GenerateJobId generator = CreateCompactionJobs.GenerateJobId.random();

            // When
            String jobId = generator.generate();
            UUID uuid = UUID.fromString(jobId);

            // Then - UUID.randomUUID() generates version 4 UUIDs
            assertThat(uuid.version()).isEqualTo(4);
        }

        @Test
        void shouldCreateIndependentGeneratorsFromMultipleCalls() {
            // Given
            CreateCompactionJobs.GenerateJobId generator1 = CreateCompactionJobs.GenerateJobId.random();
            CreateCompactionJobs.GenerateJobId generator2 = CreateCompactionJobs.GenerateJobId.random();

            // When
            String jobId1 = generator1.generate();
            String jobId2 = generator2.generate();

            // Then - Both generators should produce valid but different UUIDs
            assertThat(UUID.fromString(jobId1)).isNotNull();
            assertThat(UUID.fromString(jobId2)).isNotNull();
            assertThat(jobId1).isNotEqualTo(jobId2);
        }
    }

    @Nested
    @DisplayName("GenerateBatchId.random()")
    class GenerateBatchIdRandom {

        @Test
        void shouldReturnNonNullGenerateBatchId() {
            // When
            CreateCompactionJobs.GenerateBatchId generator = CreateCompactionJobs.GenerateBatchId.random();

            // Then
            assertThat(generator).isNotNull();
        }

        @Test
        void shouldGenerateValidUuidString() {
            // Given
            CreateCompactionJobs.GenerateBatchId generator = CreateCompactionJobs.GenerateBatchId.random();

            // When
            String batchId = generator.generate();

            // Then
            assertThat(batchId).isNotNull();
            assertThat(UUID.fromString(batchId)).isNotNull();
        }

        @Test
        void shouldGenerateDifferentValuesOnEachCall() {
            // Given
            CreateCompactionJobs.GenerateBatchId generator = CreateCompactionJobs.GenerateBatchId.random();

            // When
            String batchId1 = generator.generate();
            String batchId2 = generator.generate();
            String batchId3 = generator.generate();

            // Then
            assertThat(batchId1).isNotEqualTo(batchId2);
            assertThat(batchId2).isNotEqualTo(batchId3);
            assertThat(batchId1).isNotEqualTo(batchId3);
        }

        @Test
        void shouldGenerateUniqueValuesAcrossMultipleCalls() {
            // Given
            CreateCompactionJobs.GenerateBatchId generator = CreateCompactionJobs.GenerateBatchId.random();
            Set<String> generatedIds = new HashSet<>();

            // When
            for (int i = 0; i < 100; i++) {
                generatedIds.add(generator.generate());
            }

            // Then
            assertThat(generatedIds).hasSize(100);
        }

        @Test
        void shouldGenerateUuidVersion4Format() {
            // Given
            CreateCompactionJobs.GenerateBatchId generator = CreateCompactionJobs.GenerateBatchId.random();

            // When
            String batchId = generator.generate();
            UUID uuid = UUID.fromString(batchId);

            // Then - UUID.randomUUID() generates version 4 UUIDs
            assertThat(uuid.version()).isEqualTo(4);
        }

        @Test
        void shouldCreateIndependentGeneratorsFromMultipleCalls() {
            // Given
            CreateCompactionJobs.GenerateBatchId generator1 = CreateCompactionJobs.GenerateBatchId.random();
            CreateCompactionJobs.GenerateBatchId generator2 = CreateCompactionJobs.GenerateBatchId.random();

            // When
            String batchId1 = generator1.generate();
            String batchId2 = generator2.generate();

            // Then - Both generators should produce valid but different UUIDs
            assertThat(UUID.fromString(batchId1)).isNotNull();
            assertThat(UUID.fromString(batchId2)).isNotNull();
            assertThat(batchId1).isNotEqualTo(batchId2);
        }
    }

    @Nested
    @DisplayName("Batch writing and message sending")
    class BatchWritingAndMessageSending {

        @Test
        void shouldWriteBatchToCorrectBucket() throws Exception {
            // Given
            FileReference file1 = fileFactory.rootFile("file1.parquet", 100L);
            FileReference file2 = fileFactory.rootFile("file2.parquet", 200L);
            update(stateStore).addFiles(List.of(file1, file2));

            tableProperties.set(COMPACTION_STRATEGY_CLASS, TestCompactionStrategy.class.getName());
            CreateCompactionJobs createJobs = createCompactionJobs(stateStore);

            // When
            createJobs.createJobsWithStrategy(tableProperties);

            // Then
            assertThat(writtenBatches).hasSize(1);
            assertThat(writtenBatches.get(0).bucketName()).isEqualTo(instanceProperties.get(DATA_BUCKET));
        }

        @Test
        void shouldSendDispatchRequestWithCorrectTableId() throws Exception {
            // Given
            FileReference file1 = fileFactory.rootFile("file1.parquet", 100L);
            FileReference file2 = fileFactory.rootFile("file2.parquet", 200L);
            update(stateStore).addFiles(List.of(file1, file2));

            tableProperties.set(COMPACTION_STRATEGY_CLASS, TestCompactionStrategy.class.getName());
            CreateCompactionJobs createJobs = createCompactionJobs(stateStore);

            // When
            createJobs.createJobsWithStrategy(tableProperties);

            // Then
            assertThat(sentDispatchRequests).hasSize(1);
            assertThat(sentDispatchRequests.get(0).getTableId())
                    .isEqualTo(tableProperties.getStatus().getTableUniqueId());
        }

        @Test
        void shouldUseProvidedTimeSupplier() throws Exception {
            // Given
            FileReference file1 = fileFactory.rootFile("file1.parquet", 100L);
            FileReference file2 = fileFactory.rootFile("file2.parquet", 200L);
            update(stateStore).addFiles(List.of(file1, file2));

            Instant customTime = Instant.parse("2024-12-25T12:00:00Z");
            tableProperties.set(COMPACTION_STRATEGY_CLASS, TestCompactionStrategy.class.getName());
            CreateCompactionJobs createJobs = createCompactionJobsWithTime(stateStore, customTime);

            // When
            createJobs.createJobsWithStrategy(tableProperties);

            // Then
            assertThat(sentDispatchRequests).hasSize(1);
            assertThat(sentDispatchRequests.get(0).getCreateTime()).isEqualTo(customTime);
        }

        @Test
        void shouldUseGeneratedBatchId() throws Exception {
            // Given
            FileReference file1 = fileFactory.rootFile("file1.parquet", 100L);
            FileReference file2 = fileFactory.rootFile("file2.parquet", 200L);
            update(stateStore).addFiles(List.of(file1, file2));

            tableProperties.set(COMPACTION_STRATEGY_CLASS, TestCompactionStrategy.class.getName());
            CreateCompactionJobs createJobs = createCompactionJobs(stateStore);

            // When
            createJobs.createJobsWithStrategy(tableProperties);

            // Then
            assertThat(sentDispatchRequests).hasSize(1);
            assertThat(sentDispatchRequests.get(0).getBatchKey()).contains("batch-0");
        }
    }

    private TableProperties createTableProperties() {
        return createTestTableProperties(instanceProperties, schema);
    }

    private StateStore createStateStore(TableProperties tableProperties) {
        StateStore store = InMemoryTransactionLogStateStore.create(tableProperties, transactionLogs);
        update(store).initialise(partitions.getAllPartitions());
        store.fixFileUpdateTime(DEFAULT_FILE_UPDATE_TIME);
        return store;
    }

    private CreateCompactionJobs createCompactionJobs(StateStore stateStore) {
        return createCompactionJobsWithRandom(stateStore, new Random());
    }

    private CreateCompactionJobs createCompactionJobsWithRandom(StateStore stateStore, Random random) {
        return createCompactionJobsWithTimeAndRandom(stateStore, DEFAULT_TIME, random);
    }

    private CreateCompactionJobs createCompactionJobsWithTime(StateStore stateStore, Instant time) {
        return createCompactionJobsWithTimeAndRandom(stateStore, time, new Random());
    }

    private CreateCompactionJobs createCompactionJobsWithTimeAndRandom(StateStore stateStore, Instant time, Random random) {
        StateStoreProvider stateStoreProvider = new StateStoreProvider(1, props -> stateStore);

        BatchJobsWriter batchWriter = (bucketName, key, jobs) -> {
            writtenBatches.add(new WrittenBatch(bucketName, key, jobs));
        };

        BatchMessageSender messageSender = request -> {
            sentDispatchRequests.add(request);
        };

        return new CreateCompactionJobs(
                ObjectFactory.noUserJars(),
                instanceProperties,
                stateStoreProvider,
                batchWriter,
                messageSender,
                sentCommitRequests::add,
                () -> "job-" + jobIdCounter.getAndIncrement(),
                () -> "batch-" + batchIdCounter.getAndIncrement(),
                random,
                () -> time);
    }

    private record WrittenBatch(String bucketName, String key, List<CompactionJob> jobs) {
    }

    /**
     * A test strategy that creates one job from the first two files found.
     */
    public static class TestCompactionStrategy implements CompactionStrategy {
        @Override
        public List<CompactionJob> createCompactionJobs(
                InstanceProperties instanceProperties, TableProperties tableProperties,
                CompactionJobFactory factory, CompactionStrategyIndex index) {
            List<CompactionJob> jobs = new ArrayList<>();
            index.getFilesInLeafPartitions().forEach(partition -> {
                List<FileReference> files = new ArrayList<>(partition.getFilesWithNoJobIdInAscendingOrder());
                if (files.size() >= 2) {
                    jobs.add(factory.createCompactionJob(files.subList(0, 2), partition.getPartitionId()));
                }
            });
            return jobs;
        }
    }

    /**
     * A test strategy that creates two jobs from four files (2 files per job).
     */
    public static class TwoJobsCompactionStrategy implements CompactionStrategy {
        @Override
        public List<CompactionJob> createCompactionJobs(
                InstanceProperties instanceProperties, TableProperties tableProperties,
                CompactionJobFactory factory, CompactionStrategyIndex index) {
            List<CompactionJob> jobs = new ArrayList<>();
            index.getFilesInLeafPartitions().forEach(partition -> {
                List<FileReference> files = new ArrayList<>(partition.getFilesWithNoJobIdInAscendingOrder());
                if (files.size() >= 4) {
                    jobs.add(factory.createCompactionJob(files.subList(0, 2), partition.getPartitionId()));
                    jobs.add(factory.createCompactionJob(files.subList(2, 4), partition.getPartitionId()));
                }
            });
            return jobs;
        }
    }

    /**
     * A test strategy that creates no jobs (returns mutable list since production code mutates it).
     */
    public static class NoJobsCompactionStrategy implements CompactionStrategy {
        @Override
        public List<CompactionJob> createCompactionJobs(
                InstanceProperties instanceProperties, TableProperties tableProperties,
                CompactionJobFactory factory, CompactionStrategyIndex index) {
            return new ArrayList<>();
        }
    }

    /**
     * A test strategy that creates one job per file pair.
     */
    public static class ManyJobsCompactionStrategy implements CompactionStrategy {
        @Override
        public List<CompactionJob> createCompactionJobs(
                InstanceProperties instanceProperties, TableProperties tableProperties,
                CompactionJobFactory factory, CompactionStrategyIndex index) {
            List<CompactionJob> jobs = new ArrayList<>();
            index.getFilesInLeafPartitions().forEach(partition -> {
                List<FileReference> files = new ArrayList<>(partition.getFilesWithNoJobIdInAscendingOrder());
                for (int i = 0; i + 1 < files.size(); i += 2) {
                    jobs.add(factory.createCompactionJob(files.subList(i, i + 2), partition.getPartitionId()));
                }
            });
            return jobs;
        }
    }
}
