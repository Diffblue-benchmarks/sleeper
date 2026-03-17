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

package sleeper.compaction.tracker.task;

import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.ResponseMetadata;
import com.amazonaws.regions.Region;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.AttributeValueUpdate;
import com.amazonaws.services.dynamodbv2.model.BatchExecuteStatementRequest;
import com.amazonaws.services.dynamodbv2.model.BatchExecuteStatementResult;
import com.amazonaws.services.dynamodbv2.model.BatchGetItemRequest;
import com.amazonaws.services.dynamodbv2.model.BatchGetItemResult;
import com.amazonaws.services.dynamodbv2.model.BatchWriteItemRequest;
import com.amazonaws.services.dynamodbv2.model.BatchWriteItemResult;
import com.amazonaws.services.dynamodbv2.model.Condition;
import com.amazonaws.services.dynamodbv2.model.CreateBackupRequest;
import com.amazonaws.services.dynamodbv2.model.CreateBackupResult;
import com.amazonaws.services.dynamodbv2.model.CreateGlobalTableRequest;
import com.amazonaws.services.dynamodbv2.model.CreateGlobalTableResult;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.CreateTableResult;
import com.amazonaws.services.dynamodbv2.model.DeleteBackupRequest;
import com.amazonaws.services.dynamodbv2.model.DeleteBackupResult;
import com.amazonaws.services.dynamodbv2.model.DeleteItemRequest;
import com.amazonaws.services.dynamodbv2.model.DeleteItemResult;
import com.amazonaws.services.dynamodbv2.model.DeleteTableRequest;
import com.amazonaws.services.dynamodbv2.model.DeleteTableResult;
import com.amazonaws.services.dynamodbv2.model.DescribeBackupRequest;
import com.amazonaws.services.dynamodbv2.model.DescribeBackupResult;
import com.amazonaws.services.dynamodbv2.model.DescribeContinuousBackupsRequest;
import com.amazonaws.services.dynamodbv2.model.DescribeContinuousBackupsResult;
import com.amazonaws.services.dynamodbv2.model.DescribeContributorInsightsRequest;
import com.amazonaws.services.dynamodbv2.model.DescribeContributorInsightsResult;
import com.amazonaws.services.dynamodbv2.model.DescribeEndpointsRequest;
import com.amazonaws.services.dynamodbv2.model.DescribeEndpointsResult;
import com.amazonaws.services.dynamodbv2.model.DescribeExportRequest;
import com.amazonaws.services.dynamodbv2.model.DescribeExportResult;
import com.amazonaws.services.dynamodbv2.model.DescribeGlobalTableRequest;
import com.amazonaws.services.dynamodbv2.model.DescribeGlobalTableResult;
import com.amazonaws.services.dynamodbv2.model.DescribeGlobalTableSettingsRequest;
import com.amazonaws.services.dynamodbv2.model.DescribeGlobalTableSettingsResult;
import com.amazonaws.services.dynamodbv2.model.DescribeImportRequest;
import com.amazonaws.services.dynamodbv2.model.DescribeImportResult;
import com.amazonaws.services.dynamodbv2.model.DescribeKinesisStreamingDestinationRequest;
import com.amazonaws.services.dynamodbv2.model.DescribeKinesisStreamingDestinationResult;
import com.amazonaws.services.dynamodbv2.model.DescribeLimitsRequest;
import com.amazonaws.services.dynamodbv2.model.DescribeLimitsResult;
import com.amazonaws.services.dynamodbv2.model.DescribeTableReplicaAutoScalingRequest;
import com.amazonaws.services.dynamodbv2.model.DescribeTableReplicaAutoScalingResult;
import com.amazonaws.services.dynamodbv2.model.DescribeTableRequest;
import com.amazonaws.services.dynamodbv2.model.DescribeTableResult;
import com.amazonaws.services.dynamodbv2.model.DescribeTimeToLiveRequest;
import com.amazonaws.services.dynamodbv2.model.DescribeTimeToLiveResult;
import com.amazonaws.services.dynamodbv2.model.DisableKinesisStreamingDestinationRequest;
import com.amazonaws.services.dynamodbv2.model.DisableKinesisStreamingDestinationResult;
import com.amazonaws.services.dynamodbv2.model.EnableKinesisStreamingDestinationRequest;
import com.amazonaws.services.dynamodbv2.model.EnableKinesisStreamingDestinationResult;
import com.amazonaws.services.dynamodbv2.model.ExecuteStatementRequest;
import com.amazonaws.services.dynamodbv2.model.ExecuteStatementResult;
import com.amazonaws.services.dynamodbv2.model.ExecuteTransactionRequest;
import com.amazonaws.services.dynamodbv2.model.ExecuteTransactionResult;
import com.amazonaws.services.dynamodbv2.model.ExportTableToPointInTimeRequest;
import com.amazonaws.services.dynamodbv2.model.ExportTableToPointInTimeResult;
import com.amazonaws.services.dynamodbv2.model.GetItemRequest;
import com.amazonaws.services.dynamodbv2.model.GetItemResult;
import com.amazonaws.services.dynamodbv2.model.ImportTableRequest;
import com.amazonaws.services.dynamodbv2.model.ImportTableResult;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeysAndAttributes;
import com.amazonaws.services.dynamodbv2.model.ListBackupsRequest;
import com.amazonaws.services.dynamodbv2.model.ListBackupsResult;
import com.amazonaws.services.dynamodbv2.model.ListContributorInsightsRequest;
import com.amazonaws.services.dynamodbv2.model.ListContributorInsightsResult;
import com.amazonaws.services.dynamodbv2.model.ListExportsRequest;
import com.amazonaws.services.dynamodbv2.model.ListExportsResult;
import com.amazonaws.services.dynamodbv2.model.ListGlobalTablesRequest;
import com.amazonaws.services.dynamodbv2.model.ListGlobalTablesResult;
import com.amazonaws.services.dynamodbv2.model.ListImportsRequest;
import com.amazonaws.services.dynamodbv2.model.ListImportsResult;
import com.amazonaws.services.dynamodbv2.model.ListTablesRequest;
import com.amazonaws.services.dynamodbv2.model.ListTablesResult;
import com.amazonaws.services.dynamodbv2.model.ListTagsOfResourceRequest;
import com.amazonaws.services.dynamodbv2.model.ListTagsOfResourceResult;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;
import com.amazonaws.services.dynamodbv2.model.PutItemResult;
import com.amazonaws.services.dynamodbv2.model.QueryRequest;
import com.amazonaws.services.dynamodbv2.model.QueryResult;
import com.amazonaws.services.dynamodbv2.model.RestoreTableFromBackupRequest;
import com.amazonaws.services.dynamodbv2.model.RestoreTableFromBackupResult;
import com.amazonaws.services.dynamodbv2.model.RestoreTableToPointInTimeRequest;
import com.amazonaws.services.dynamodbv2.model.RestoreTableToPointInTimeResult;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import com.amazonaws.services.dynamodbv2.model.TableDescription;
import com.amazonaws.services.dynamodbv2.model.Tag;
import com.amazonaws.services.dynamodbv2.model.TagResourceRequest;
import com.amazonaws.services.dynamodbv2.model.TagResourceResult;
import com.amazonaws.services.dynamodbv2.model.TransactGetItemsRequest;
import com.amazonaws.services.dynamodbv2.model.TransactGetItemsResult;
import com.amazonaws.services.dynamodbv2.model.TransactWriteItemsRequest;
import com.amazonaws.services.dynamodbv2.model.TransactWriteItemsResult;
import com.amazonaws.services.dynamodbv2.model.UntagResourceRequest;
import com.amazonaws.services.dynamodbv2.model.UntagResourceResult;
import com.amazonaws.services.dynamodbv2.model.UpdateContinuousBackupsRequest;
import com.amazonaws.services.dynamodbv2.model.UpdateContinuousBackupsResult;
import com.amazonaws.services.dynamodbv2.model.UpdateContributorInsightsRequest;
import com.amazonaws.services.dynamodbv2.model.UpdateContributorInsightsResult;
import com.amazonaws.services.dynamodbv2.model.UpdateGlobalTableRequest;
import com.amazonaws.services.dynamodbv2.model.UpdateGlobalTableResult;
import com.amazonaws.services.dynamodbv2.model.UpdateGlobalTableSettingsRequest;
import com.amazonaws.services.dynamodbv2.model.UpdateGlobalTableSettingsResult;
import com.amazonaws.services.dynamodbv2.model.UpdateItemRequest;
import com.amazonaws.services.dynamodbv2.model.UpdateItemResult;
import com.amazonaws.services.dynamodbv2.model.UpdateTableReplicaAutoScalingRequest;
import com.amazonaws.services.dynamodbv2.model.UpdateTableReplicaAutoScalingResult;
import com.amazonaws.services.dynamodbv2.model.UpdateTableRequest;
import com.amazonaws.services.dynamodbv2.model.UpdateTableResult;
import com.amazonaws.services.dynamodbv2.model.UpdateTimeToLiveRequest;
import com.amazonaws.services.dynamodbv2.model.UpdateTimeToLiveResult;
import com.amazonaws.services.dynamodbv2.waiters.AmazonDynamoDBWaiters;

import org.junit.jupiter.api.Test;

import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.tracker.compaction.task.CompactionTaskTracker;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static sleeper.core.properties.instance.CompactionProperty.COMPACTION_TRACKER_ENABLED;
import static sleeper.core.properties.testutils.InstancePropertiesTestHelper.createTestInstanceProperties;

public class CompactionTaskTrackerFactoryIT {

    @Test
    public void shouldReturnDynamoDBCompactionTaskTrackerWhenTrackerEnabled() {
        // Given
        AmazonDynamoDB dynamoDB = new StubDynamoDB();
        InstanceProperties properties = createTestInstanceProperties();
        properties.set(COMPACTION_TRACKER_ENABLED, "true");

        // When
        CompactionTaskTracker tracker = CompactionTaskTrackerFactory.getTracker(dynamoDB, properties);

        // Then
        assertThat(tracker).isInstanceOf(DynamoDBCompactionTaskTracker.class);
    }

    @Test
    public void shouldReturnNoTrackerWhenTrackerDisabled() {
        // Given
        AmazonDynamoDB dynamoDB = new StubDynamoDB();
        InstanceProperties properties = createTestInstanceProperties();
        properties.set(COMPACTION_TRACKER_ENABLED, "false");

        // When
        CompactionTaskTracker tracker = CompactionTaskTrackerFactory.getTracker(dynamoDB, properties);

        // Then
        assertThat(tracker).isSameAs(CompactionTaskTracker.NONE);
    }

    /**
     * Minimal stub implementation of AmazonDynamoDB for testing the factory method.
     * The factory method only passes the client to the DynamoDBCompactionTaskTracker constructor
     * without invoking any methods, so all methods can return null.
     */
    private static class StubDynamoDB implements AmazonDynamoDB {
        public void setEndpoint(String endpoint) {
        }

        public void setRegion(Region region) {
        }

        public BatchExecuteStatementResult batchExecuteStatement(BatchExecuteStatementRequest batchExecuteStatementRequest) {
            return null;
        }

        public BatchGetItemResult batchGetItem(BatchGetItemRequest batchGetItemRequest) {
            return null;
        }

        public BatchGetItemResult batchGetItem(Map<String, KeysAndAttributes> requestItems, String returnConsumedCapacity) {
            return null;
        }

        public BatchGetItemResult batchGetItem(Map<String, KeysAndAttributes> requestItems) {
            return null;
        }

        public BatchWriteItemResult batchWriteItem(BatchWriteItemRequest batchWriteItemRequest) {
            return null;
        }

        public BatchWriteItemResult batchWriteItem(Map requestItems) {
            return null;
        }

        public CreateBackupResult createBackup(CreateBackupRequest createBackupRequest) {
            return null;
        }

        public CreateGlobalTableResult createGlobalTable(CreateGlobalTableRequest createGlobalTableRequest) {
            return null;
        }

        public CreateTableResult createTable(CreateTableRequest createTableRequest) {
            return null;
        }

        public CreateTableResult createTable(List<AttributeDefinition> attributeDefinitions, String tableName, List<KeySchemaElement> keySchema, ProvisionedThroughput provisionedThroughput) {
            return null;
        }

        public DeleteBackupResult deleteBackup(DeleteBackupRequest deleteBackupRequest) {
            return null;
        }

        public DeleteItemResult deleteItem(DeleteItemRequest deleteItemRequest) {
            return null;
        }

        public DeleteItemResult deleteItem(String tableName, Map<String, AttributeValue> key) {
            return null;
        }

        public DeleteItemResult deleteItem(String tableName, Map<String, AttributeValue> key, String returnValues) {
            return null;
        }

        public DeleteTableResult deleteTable(DeleteTableRequest deleteTableRequest) {
            return null;
        }

        public DeleteTableResult deleteTable(String tableName) {
            return null;
        }

        public DescribeBackupResult describeBackup(DescribeBackupRequest describeBackupRequest) {
            return null;
        }

        public DescribeContinuousBackupsResult describeContinuousBackups(DescribeContinuousBackupsRequest describeContinuousBackupsRequest) {
            return null;
        }

        public DescribeContributorInsightsResult describeContributorInsights(DescribeContributorInsightsRequest describeContributorInsightsRequest) {
            return null;
        }

        public DescribeEndpointsResult describeEndpoints(DescribeEndpointsRequest describeEndpointsRequest) {
            return null;
        }

        public DescribeExportResult describeExport(DescribeExportRequest describeExportRequest) {
            return null;
        }

        public DescribeGlobalTableResult describeGlobalTable(DescribeGlobalTableRequest describeGlobalTableRequest) {
            return null;
        }

        public DescribeGlobalTableSettingsResult describeGlobalTableSettings(DescribeGlobalTableSettingsRequest describeGlobalTableSettingsRequest) {
            return null;
        }

        public DescribeImportResult describeImport(DescribeImportRequest describeImportRequest) {
            return null;
        }

        public DescribeKinesisStreamingDestinationResult describeKinesisStreamingDestination(DescribeKinesisStreamingDestinationRequest describeKinesisStreamingDestinationRequest) {
            return null;
        }

        public DescribeLimitsResult describeLimits(DescribeLimitsRequest describeLimitsRequest) {
            return null;
        }

        public DescribeTableResult describeTable(DescribeTableRequest describeTableRequest) {
            return null;
        }

        public DescribeTableResult describeTable(String tableName) {
            return null;
        }

        public DescribeTableReplicaAutoScalingResult describeTableReplicaAutoScaling(DescribeTableReplicaAutoScalingRequest describeTableReplicaAutoScalingRequest) {
            return null;
        }

        public DescribeTimeToLiveResult describeTimeToLive(DescribeTimeToLiveRequest describeTimeToLiveRequest) {
            return null;
        }

        public DisableKinesisStreamingDestinationResult disableKinesisStreamingDestination(DisableKinesisStreamingDestinationRequest disableKinesisStreamingDestinationRequest) {
            return null;
        }

        public EnableKinesisStreamingDestinationResult enableKinesisStreamingDestination(EnableKinesisStreamingDestinationRequest enableKinesisStreamingDestinationRequest) {
            return null;
        }

        public ExecuteStatementResult executeStatement(ExecuteStatementRequest executeStatementRequest) {
            return null;
        }

        public ExecuteTransactionResult executeTransaction(ExecuteTransactionRequest executeTransactionRequest) {
            return null;
        }

        public ExportTableToPointInTimeResult exportTableToPointInTime(ExportTableToPointInTimeRequest exportTableToPointInTimeRequest) {
            return null;
        }

        public GetItemResult getItem(GetItemRequest getItemRequest) {
            return null;
        }

        public GetItemResult getItem(String tableName, Map<String, AttributeValue> key) {
            return null;
        }

        public GetItemResult getItem(String tableName, Map<String, AttributeValue> key, Boolean consistentRead) {
            return null;
        }

        public ImportTableResult importTable(ImportTableRequest importTableRequest) {
            return null;
        }

        public ListBackupsResult listBackups(ListBackupsRequest listBackupsRequest) {
            return null;
        }

        public ListContributorInsightsResult listContributorInsights(ListContributorInsightsRequest listContributorInsightsRequest) {
            return null;
        }

        public ListExportsResult listExports(ListExportsRequest listExportsRequest) {
            return null;
        }

        public ListGlobalTablesResult listGlobalTables(ListGlobalTablesRequest listGlobalTablesRequest) {
            return null;
        }

        public ListImportsResult listImports(ListImportsRequest listImportsRequest) {
            return null;
        }

        public ListTablesResult listTables(ListTablesRequest listTablesRequest) {
            return null;
        }

        public ListTablesResult listTables() {
            return null;
        }

        public ListTablesResult listTables(String exclusiveStartTableName) {
            return null;
        }

        public ListTablesResult listTables(String exclusiveStartTableName, Integer limit) {
            return null;
        }

        public ListTablesResult listTables(Integer limit) {
            return null;
        }

        public ListTagsOfResourceResult listTagsOfResource(ListTagsOfResourceRequest listTagsOfResourceRequest) {
            return null;
        }

        public PutItemResult putItem(PutItemRequest putItemRequest) {
            return null;
        }

        public PutItemResult putItem(String tableName, Map<String, AttributeValue> item) {
            return null;
        }

        public PutItemResult putItem(String tableName, Map<String, AttributeValue> item, String returnValues) {
            return null;
        }

        public QueryResult query(QueryRequest queryRequest) {
            return null;
        }

        public RestoreTableFromBackupResult restoreTableFromBackup(RestoreTableFromBackupRequest restoreTableFromBackupRequest) {
            return null;
        }

        public RestoreTableToPointInTimeResult restoreTableToPointInTime(RestoreTableToPointInTimeRequest restoreTableToPointInTimeRequest) {
            return null;
        }

        public ScanResult scan(ScanRequest scanRequest) {
            return null;
        }

        public ScanResult scan(String tableName, List<String> attributesToGet) {
            return null;
        }

        public ScanResult scan(String tableName, Map<String, Condition> scanFilter) {
            return null;
        }

        public ScanResult scan(String tableName, List<String> attributesToGet, Map<String, Condition> scanFilter) {
            return null;
        }

        public TagResourceResult tagResource(TagResourceRequest tagResourceRequest) {
            return null;
        }

        public TransactGetItemsResult transactGetItems(TransactGetItemsRequest transactGetItemsRequest) {
            return null;
        }

        public TransactWriteItemsResult transactWriteItems(TransactWriteItemsRequest transactWriteItemsRequest) {
            return null;
        }

        public UntagResourceResult untagResource(UntagResourceRequest untagResourceRequest) {
            return null;
        }

        public UpdateContinuousBackupsResult updateContinuousBackups(UpdateContinuousBackupsRequest updateContinuousBackupsRequest) {
            return null;
        }

        public UpdateContributorInsightsResult updateContributorInsights(UpdateContributorInsightsRequest updateContributorInsightsRequest) {
            return null;
        }

        public UpdateGlobalTableResult updateGlobalTable(UpdateGlobalTableRequest updateGlobalTableRequest) {
            return null;
        }

        public UpdateGlobalTableSettingsResult updateGlobalTableSettings(UpdateGlobalTableSettingsRequest updateGlobalTableSettingsRequest) {
            return null;
        }

        public UpdateItemResult updateItem(UpdateItemRequest updateItemRequest) {
            return null;
        }

        public UpdateItemResult updateItem(String tableName, Map<String, AttributeValue> key, Map<String, AttributeValueUpdate> attributeUpdates) {
            return null;
        }

        public UpdateItemResult updateItem(String tableName, Map<String, AttributeValue> key, Map<String, AttributeValueUpdate> attributeUpdates, String returnValues) {
            return null;
        }

        public UpdateTableResult updateTable(UpdateTableRequest updateTableRequest) {
            return null;
        }

        public UpdateTableResult updateTable(String tableName, ProvisionedThroughput provisionedThroughput) {
            return null;
        }

        public UpdateTableReplicaAutoScalingResult updateTableReplicaAutoScaling(UpdateTableReplicaAutoScalingRequest updateTableReplicaAutoScalingRequest) {
            return null;
        }

        public UpdateTimeToLiveResult updateTimeToLive(UpdateTimeToLiveRequest updateTimeToLiveRequest) {
            return null;
        }

        public void shutdown() {
        }

        public ResponseMetadata getCachedResponseMetadata(AmazonWebServiceRequest request) {
            return null;
        }

        public AmazonDynamoDBWaiters waiters() {
            return null;
        }
    }
}
