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
package sleeper.compaction.job.creation;

import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sleeper.compaction.core.job.CompactionJob;
import sleeper.compaction.core.job.CompactionJobSerDe;
import sleeper.compaction.core.job.creation.CreateCompactionJobs;
import sleeper.compaction.core.job.dispatch.CompactionJobDispatchRequest;
import sleeper.compaction.core.job.dispatch.CompactionJobDispatchRequestSerDe;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.table.TableProperties;
import sleeper.core.schema.Schema;
import sleeper.core.statestore.FileReference;
import sleeper.core.statestore.FileReferenceFactory;
import sleeper.core.statestore.StateStore;
import sleeper.core.statestore.StateStoreProvider;
import sleeper.core.statestore.testutils.InMemoryTransactionLogStateStore;
import sleeper.core.statestore.testutils.InMemoryTransactionLogsPerTable;
import sleeper.core.util.ObjectFactory;
import sleeper.localstack.test.LocalStackTestBase;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static sleeper.core.properties.instance.CdkDefinedInstanceProperty.COMPACTION_PENDING_QUEUE_URL;
import static sleeper.core.properties.instance.CdkDefinedInstanceProperty.DATA_BUCKET;
import static sleeper.core.properties.instance.CdkDefinedInstanceProperty.STATESTORE_COMMITTER_QUEUE_URL;
import static sleeper.core.properties.table.TableProperty.COMPACTION_FILES_BATCH_SIZE;
import static sleeper.core.properties.table.TableProperty.COMPACTION_JOB_ID_ASSIGNMENT_COMMIT_ASYNC;
import static sleeper.core.properties.table.TableProperty.COMPACTION_STRATEGY_CLASS;
import static sleeper.core.properties.table.TableProperty.TABLE_ID;
import static sleeper.core.properties.testutils.InstancePropertiesTestHelper.createTestInstanceProperties;
import static sleeper.core.properties.testutils.TablePropertiesTestHelper.createTestTableProperties;
import static sleeper.core.schema.SchemaTestHelper.schemaWithKey;
import static sleeper.core.statestore.testutils.StateStoreUpdatesWrapper.update;

public class AwsCreateCompactionJobsClaudeTest extends LocalStackTestBase {

    private final InstanceProperties instanceProperties = createTestInstanceProperties();
    private final Schema schema = schemaWithKey("key");
    private final TableProperties tableProperties = createTestTableProperties(instanceProperties, schema);
    private final InMemoryTransactionLogsPerTable transactionLogs = new InMemoryTransactionLogsPerTable();
    private final CompactionJobSerDe jobSerDe = new CompactionJobSerDe();
    private final CompactionJobDispatchRequestSerDe dispatchSerDe = new CompactionJobDispatchRequestSerDe();

    @BeforeEach
    void setUp() {
        s3Client.createBucket(instanceProperties.get(DATA_BUCKET));
        instanceProperties.set(COMPACTION_PENDING_QUEUE_URL, sqsClient.createQueue(
                "compaction-pending-" + UUID.randomUUID()).getQueueUrl());
        instanceProperties.set(STATESTORE_COMMITTER_QUEUE_URL, createFifoQueueGetUrl());
        tableProperties.set(COMPACTION_JOB_ID_ASSIGNMENT_COMMIT_ASYNC, "false");
        tableProperties.set(COMPACTION_FILES_BATCH_SIZE, "2");
        tableProperties.set(COMPACTION_STRATEGY_CLASS, "sleeper.compaction.core.job.creation.strategy.impl.BasicCompactionStrategy");
        transactionLogs.initialiseTable(tableProperties);
    }

    @Test
    void shouldCreateCompactionJobsAndWriteToS3AndSendMessageToSqs() throws Exception {
        // Given
        StateStore stateStore = stateStore();
        addFilesToStateStore(stateStore, "file1.parquet", "file2.parquet");

        // When
        CreateCompactionJobs createJobs = AwsCreateCompactionJobs.from(
                ObjectFactory.noUserJars(),
                instanceProperties,
                tablePropertiesProvider(),
                stateStoreProvider(),
                s3Client,
                sqsClient);
        createJobs.createJobsWithStrategy(tableProperties);

        // Then
        List<Message> messages = receiveMessagesFromPendingQueue();
        assertThat(messages).hasSize(1);

        CompactionJobDispatchRequest dispatchRequest = dispatchSerDe.fromJson(messages.get(0).getBody());
        assertThat(dispatchRequest.getTableId()).isEqualTo(tableProperties.get(TABLE_ID));

        String jobsBatchJson = s3Client.getObjectAsString(
                instanceProperties.get(DATA_BUCKET), dispatchRequest.getBatchKey());
        List<CompactionJob> jobs = jobSerDe.batchFromJson(jobsBatchJson);
        assertThat(jobs).hasSize(1);
        assertThat(jobs.get(0).getInputFiles())
                .containsExactlyInAnyOrder("file1.parquet", "file2.parquet");
    }

    @Test
    void shouldAssignJobIdToFilesInStateStore() throws Exception {
        // Given
        StateStore stateStore = stateStore();
        addFilesToStateStore(stateStore, "file1.parquet", "file2.parquet");

        // When
        CreateCompactionJobs createJobs = AwsCreateCompactionJobs.from(
                ObjectFactory.noUserJars(),
                instanceProperties,
                tablePropertiesProvider(),
                stateStoreProvider(),
                s3Client,
                sqsClient);
        createJobs.createJobsWithStrategy(tableProperties);

        // Then
        List<FileReference> files = stateStore.getFileReferences();
        assertThat(files).hasSize(2);
        assertThat(files.stream().map(FileReference::getJobId).distinct())
                .hasSize(1)
                .doesNotContainNull();
    }

    @Test
    void shouldForceCreateJobsForAllFilesWhenRequested() throws Exception {
        // Given
        tableProperties.set(COMPACTION_FILES_BATCH_SIZE, "10");
        StateStore stateStore = stateStore();
        addFilesToStateStore(stateStore, "file1.parquet", "file2.parquet");

        // When
        CreateCompactionJobs createJobs = AwsCreateCompactionJobs.from(
                ObjectFactory.noUserJars(),
                instanceProperties,
                tablePropertiesProvider(),
                stateStoreProvider(),
                s3Client,
                sqsClient);
        createJobs.createJobWithForceAllFiles(tableProperties);

        // Then
        List<Message> messages = receiveMessagesFromPendingQueue();
        assertThat(messages).hasSize(1);

        CompactionJobDispatchRequest dispatchRequest = dispatchSerDe.fromJson(messages.get(0).getBody());
        String jobsBatchJson = s3Client.getObjectAsString(
                instanceProperties.get(DATA_BUCKET), dispatchRequest.getBatchKey());
        List<CompactionJob> jobs = jobSerDe.batchFromJson(jobsBatchJson);
        assertThat(jobs).hasSize(1);
        assertThat(jobs.get(0).getInputFiles())
                .containsExactlyInAnyOrder("file1.parquet", "file2.parquet");
    }

    @Test
    void shouldNotCreateJobsWhenNoFilesExist() throws Exception {
        // Given no files in state store

        // When
        CreateCompactionJobs createJobs = AwsCreateCompactionJobs.from(
                ObjectFactory.noUserJars(),
                instanceProperties,
                tablePropertiesProvider(),
                stateStoreProvider(),
                s3Client,
                sqsClient);
        createJobs.createJobsWithStrategy(tableProperties);

        // Then
        List<Message> messages = receiveMessagesFromPendingQueue();
        assertThat(messages).isEmpty();
    }

    @Test
    void shouldNotCreateJobsWhenNotEnoughFilesToMeetBatchSize() throws Exception {
        // Given
        tableProperties.set(COMPACTION_FILES_BATCH_SIZE, "3");
        StateStore stateStore = stateStore();
        addFilesToStateStore(stateStore, "file1.parquet", "file2.parquet");

        // When
        CreateCompactionJobs createJobs = AwsCreateCompactionJobs.from(
                ObjectFactory.noUserJars(),
                instanceProperties,
                tablePropertiesProvider(),
                stateStoreProvider(),
                s3Client,
                sqsClient);
        createJobs.createJobsWithStrategy(tableProperties);

        // Then
        List<Message> messages = receiveMessagesFromPendingQueue();
        assertThat(messages).isEmpty();
    }

    private StateStore stateStore() {
        return InMemoryTransactionLogStateStore.create(tableProperties, transactionLogs.forTable(tableProperties));
    }

    private void addFilesToStateStore(StateStore stateStore, String... filenames) {
        FileReferenceFactory factory = FileReferenceFactory.from(stateStore);
        for (String filename : filenames) {
            update(stateStore).addFile(factory.rootFile(filename, 100));
        }
    }

    private sleeper.core.properties.testutils.FixedTablePropertiesProvider tablePropertiesProvider() {
        return new sleeper.core.properties.testutils.FixedTablePropertiesProvider(tableProperties);
    }

    private StateStoreProvider stateStoreProvider() {
        return InMemoryTransactionLogStateStore.createProvider(instanceProperties, transactionLogs);
    }

    private List<Message> receiveMessagesFromPendingQueue() {
        return sqsClient.receiveMessage(new ReceiveMessageRequest()
                .withQueueUrl(instanceProperties.get(COMPACTION_PENDING_QUEUE_URL))
                .withMaxNumberOfMessages(10)
                .withWaitTimeSeconds(1)).getMessages();
    }
}
