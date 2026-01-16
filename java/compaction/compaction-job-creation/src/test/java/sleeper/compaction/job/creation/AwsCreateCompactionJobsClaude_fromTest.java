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

import com.amazonaws.services.sqs.model.CreateQueueRequest;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sleeper.compaction.core.job.CompactionJob;
import sleeper.compaction.core.job.CompactionJobSerDe;
import sleeper.compaction.core.job.creation.CreateCompactionJobs;
import sleeper.compaction.core.job.creation.strategy.impl.BasicCompactionStrategy;
import sleeper.compaction.core.job.dispatch.CompactionJobDispatchRequest;
import sleeper.compaction.core.job.dispatch.CompactionJobDispatchRequestSerDe;
import sleeper.core.partition.PartitionsBuilder;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.table.TableProperties;
import sleeper.core.properties.testutils.FixedTablePropertiesProvider;
import sleeper.core.statestore.FileReference;
import sleeper.core.statestore.FileReferenceFactory;
import sleeper.core.statestore.StateStore;
import sleeper.core.statestore.testutils.FixedStateStoreProvider;
import sleeper.core.statestore.testutils.InMemoryTransactionLogStateStore;
import sleeper.core.statestore.testutils.InMemoryTransactionLogs;
import sleeper.core.util.ObjectFactory;
import sleeper.localstack.test.LocalStackTestBase;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static sleeper.core.properties.instance.CdkDefinedInstanceProperty.COMPACTION_PENDING_QUEUE_URL;
import static sleeper.core.properties.instance.CdkDefinedInstanceProperty.DATA_BUCKET;
import static sleeper.core.properties.instance.CdkDefinedInstanceProperty.STATESTORE_COMMITTER_QUEUE_URL;
import static sleeper.core.properties.table.TableProperty.COMPACTION_FILES_BATCH_SIZE;
import static sleeper.core.properties.table.TableProperty.COMPACTION_STRATEGY_CLASS;
import static sleeper.core.properties.testutils.InstancePropertiesTestHelper.createTestInstanceProperties;
import static sleeper.core.properties.testutils.TablePropertiesTestHelper.createTestTableProperties;
import static sleeper.core.schema.SchemaTestHelper.schemaWithKey;
import static sleeper.core.statestore.testutils.StateStoreUpdatesWrapper.update;

/**
 * Tests for the factory method in AwsCreateCompactionJobs.
 */
@SuppressWarnings("checkstyle:typeName")
class AwsCreateCompactionJobsClaude_fromTest extends LocalStackTestBase {

    private final InstanceProperties instanceProperties = createTestInstanceProperties();
    private final TableProperties tableProperties = createTestTableProperties(instanceProperties, schemaWithKey("key"));
    private final InMemoryTransactionLogs transactionLogs = new InMemoryTransactionLogs();
    private StateStore stateStore;

    @BeforeEach
    void setUp() {
        // Set up S3 bucket for data
        s3Client.createBucket(instanceProperties.get(DATA_BUCKET));

        // Set up SQS queue for compaction pending queue
        instanceProperties.set(COMPACTION_PENDING_QUEUE_URL, sqsClient.createQueue(
                "compaction-pending-" + UUID.randomUUID().toString()).getQueueUrl());

        // Set up SQS FIFO queue for state store committer
        instanceProperties.set(STATESTORE_COMMITTER_QUEUE_URL, sqsClient.createQueue(new CreateQueueRequest()
                .withQueueName(UUID.randomUUID().toString() + ".fifo")
                .withAttributes(Map.of("FifoQueue", "true")))
                .getQueueUrl());

        // Configure table properties for compaction strategy that triggers with 2 files
        tableProperties.set(COMPACTION_STRATEGY_CLASS, BasicCompactionStrategy.class.getName());
        tableProperties.setNumber(COMPACTION_FILES_BATCH_SIZE, 2);

        // Create and initialise state store with partitions
        stateStore = InMemoryTransactionLogStateStore.create(tableProperties, transactionLogs);
        update(stateStore).initialise(new PartitionsBuilder(tableProperties.getSchema()).singlePartition("root").buildList());
    }

    @Test
    void shouldCreateCompactionJobsInstanceWithWorkingAWSIntegration() throws Exception {
        // Given - set up file references that will trigger compaction
        FileReferenceFactory factory = FileReferenceFactory.from(stateStore);
        FileReference file1 = factory.rootFile("file1.parquet", 100L);
        FileReference file2 = factory.rootFile("file2.parquet", 100L);
        update(stateStore).addFiles(List.of(file1, file2));

        CreateCompactionJobs jobCreator = AwsCreateCompactionJobs.from(
                ObjectFactory.noUserJars(),
                instanceProperties,
                new FixedTablePropertiesProvider(tableProperties),
                new FixedStateStoreProvider(tableProperties, stateStore),
                s3Client,
                sqsClient);

        // When
        jobCreator.createJobsWithStrategy(tableProperties);

        // Then - verify messages were sent to the compaction pending queue
        List<Message> messages = sqsClient.receiveMessage(new ReceiveMessageRequest()
                .withQueueUrl(instanceProperties.get(COMPACTION_PENDING_QUEUE_URL))
                .withMaxNumberOfMessages(10))
                .getMessages();

        assertThat(messages).hasSize(1);

        // Verify the dispatch request can be deserialized
        CompactionJobDispatchRequestSerDe dispatchSerDe = new CompactionJobDispatchRequestSerDe();
        CompactionJobDispatchRequest dispatchRequest = dispatchSerDe.fromJson(messages.get(0).getBody());
        assertThat(dispatchRequest.getTableId()).isEqualTo(tableProperties.getStatus().getTableUniqueId());

        // Verify the compaction jobs were written to S3
        String jobsJson = s3Client.getObjectAsString(
                instanceProperties.get(DATA_BUCKET),
                dispatchRequest.getBatchKey());
        CompactionJobSerDe jobSerDe = new CompactionJobSerDe();
        List<CompactionJob> jobs = jobSerDe.batchFromJson(jobsJson);

        assertThat(jobs).hasSize(1);
        assertThat(jobs.get(0).getInputFiles()).containsExactlyInAnyOrder("file1.parquet", "file2.parquet");
    }

    @Test
    void shouldReturnNonNullCreateCompactionJobsInstance() {
        // When
        CreateCompactionJobs jobCreator = AwsCreateCompactionJobs.from(
                ObjectFactory.noUserJars(),
                instanceProperties,
                new FixedTablePropertiesProvider(tableProperties),
                new FixedStateStoreProvider(tableProperties, stateStore),
                s3Client,
                sqsClient);

        // Then
        assertThat(jobCreator).isNotNull();
    }
}
