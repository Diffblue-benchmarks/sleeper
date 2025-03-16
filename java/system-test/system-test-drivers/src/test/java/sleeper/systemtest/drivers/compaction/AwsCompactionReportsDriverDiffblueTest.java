package sleeper.systemtest.drivers.compaction;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClient;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.model.QueryRequest;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.nio.file.Paths;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.instance.InstanceProperty;
import sleeper.core.table.TableStatus;
import sleeper.core.tracker.compaction.job.query.CompactionJobStatus;
import sleeper.core.tracker.compaction.task.CompactionTaskStatus;
import sleeper.systemtest.dsl.instance.SystemTestInstanceContext;
import sleeper.systemtest.dsl.reporting.ReportingContext;

class AwsCompactionReportsDriverDiffblueTest {
  /**
   * Test {@link AwsCompactionReportsDriver#jobs(ReportingContext)}.
   * <ul>
   *   <li>Given {@link AmazonDynamoDBAsyncClient} {@link AmazonDynamoDBClient#query(QueryRequest)} return {@code null}.</li>
   *   <li>Then return Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link AwsCompactionReportsDriver#jobs(ReportingContext)}
   */
  @Test
  @DisplayName("Test jobs(ReportingContext); given AmazonDynamoDBAsyncClient query(QueryRequest) return 'null'; then return Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List AwsCompactionReportsDriver.jobs(ReportingContext)"})
  void testJobs_givenAmazonDynamoDBAsyncClientQueryReturnNull_thenReturnEmpty() {
    // Arrange
    TableStatus tableStatus = mock(TableStatus.class);
    when(tableStatus.getTableUniqueId()).thenReturn("42");
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.getInt(Mockito.<InstanceProperty>any())).thenReturn(1);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    when(instanceProperties.getBoolean(Mockito.<InstanceProperty>any())).thenReturn(true);
    SystemTestInstanceContext instance = mock(SystemTestInstanceContext.class);
    when(instance.getInstanceProperties()).thenReturn(instanceProperties);
    when(instance.getTableStatus()).thenReturn(tableStatus);
    AmazonDynamoDBAsyncClient dynamoDB = mock(AmazonDynamoDBAsyncClient.class);
    when(dynamoDB.query(Mockito.<QueryRequest>any())).thenReturn(null);
    AwsCompactionReportsDriver awsCompactionReportsDriver = new AwsCompactionReportsDriver(instance, dynamoDB);

    // Act
    List<CompactionJobStatus> actualJobsResult = awsCompactionReportsDriver
        .jobs(new ReportingContext(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt")));

    // Assert
    verify(dynamoDB).query(isA(QueryRequest.class));
    verify(instanceProperties).getBoolean(isA(InstanceProperty.class));
    verify(instanceProperties).getInt(isA(InstanceProperty.class));
    verify(instanceProperties, atLeast(1)).get(isA(InstanceProperty.class));
    verify(tableStatus).getTableUniqueId();
    verify(instance).getInstanceProperties();
    verify(instance).getTableStatus();
    assertTrue(actualJobsResult.isEmpty());
  }

  /**
   * Test {@link AwsCompactionReportsDriver#tasks(ReportingContext)}.
   * <ul>
   *   <li>Given {@link AmazonDynamoDBAsyncClient} {@link AmazonDynamoDBClient#scan(ScanRequest)} return {@code null}.</li>
   *   <li>Then return Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link AwsCompactionReportsDriver#tasks(ReportingContext)}
   */
  @Test
  @DisplayName("Test tasks(ReportingContext); given AmazonDynamoDBAsyncClient scan(ScanRequest) return 'null'; then return Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List AwsCompactionReportsDriver.tasks(ReportingContext)"})
  void testTasks_givenAmazonDynamoDBAsyncClientScanReturnNull_thenReturnEmpty() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.getInt(Mockito.<InstanceProperty>any())).thenReturn(1);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    when(instanceProperties.getBoolean(Mockito.<InstanceProperty>any())).thenReturn(true);
    SystemTestInstanceContext instance = mock(SystemTestInstanceContext.class);
    when(instance.getInstanceProperties()).thenReturn(instanceProperties);
    AmazonDynamoDBAsyncClient dynamoDB = mock(AmazonDynamoDBAsyncClient.class);
    when(dynamoDB.scan(Mockito.<ScanRequest>any())).thenReturn(null);
    AwsCompactionReportsDriver awsCompactionReportsDriver = new AwsCompactionReportsDriver(instance, dynamoDB);

    // Act
    List<CompactionTaskStatus> actualTasksResult = awsCompactionReportsDriver
        .tasks(new ReportingContext(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt")));

    // Assert
    verify(dynamoDB).scan(isA(ScanRequest.class));
    verify(instanceProperties).getBoolean(isA(InstanceProperty.class));
    verify(instanceProperties).getInt(isA(InstanceProperty.class));
    verify(instanceProperties).get(isA(InstanceProperty.class));
    verify(instance).getInstanceProperties();
    assertTrue(actualTasksResult.isEmpty());
  }
}
