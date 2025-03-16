package sleeper.compaction.job.execution;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.auth.AnonymousAWSCredentials;
import com.amazonaws.handlers.AsyncHandler;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSAsyncClient;
import com.amazonaws.services.sqs.model.AddPermissionRequest;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import org.apache.hadoop.fs.s3a.statistics.impl.AwsStatisticsCollector;
import org.apache.hadoop.fs.s3a.statistics.impl.EmptyS3AStatisticsContext;
import org.apache.hadoop.fs.s3a.statistics.impl.StatisticsFromAwsSdkImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.instance.InstanceProperty;
import sleeper.core.properties.table.TablePropertiesProvider;
import sleeper.core.properties.table.TablePropertiesStore;
import sleeper.core.properties.table.TablePropertiesStore.Client;
import sleeper.core.statestore.StateStoreProvider;
import sleeper.core.statestore.StateStoreProvider.Factory;
import sleeper.core.tracker.compaction.job.CompactionJobTracker;
import sleeper.core.tracker.compaction.job.InMemoryCompactionJobTracker;
import software.amazon.awssdk.services.ecs.EcsClient;

class ECSCompactionTaskRunnerDiffblueTest {
  /**
   * Test {@link ECSCompactionTaskRunner#logEC2Metadata(InstanceProperties, EcsClient)}.
   * <ul>
   *   <li>Given {@code EC2}.</li>
   *   <li>When {@link InstanceProperties} {@link InstanceProperties#get(InstanceProperty)} return {@code EC2}.</li>
   *   <li>Then calls {@link InstanceProperties#get(InstanceProperty)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ECSCompactionTaskRunner#logEC2Metadata(InstanceProperties, EcsClient)}
   */
  @Test
  @DisplayName("Test logEC2Metadata(InstanceProperties, EcsClient); given 'EC2'; when InstanceProperties get(InstanceProperty) return 'EC2'; then calls get(InstanceProperty)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ECSCompactionTaskRunner.logEC2Metadata(InstanceProperties, EcsClient)"})
  void testLogEC2Metadata_givenEc2_whenInstancePropertiesGetReturnEc2_thenCallsGet() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("EC2");

    // Act
    ECSCompactionTaskRunner.logEC2Metadata(instanceProperties, mock(EcsClient.class));

    // Assert
    verify(instanceProperties).get(isA(InstanceProperty.class));
  }

  /**
   * Test {@link ECSCompactionTaskRunner#logEC2Metadata(InstanceProperties, EcsClient)}.
   * <ul>
   *   <li>Given {@code Get}.</li>
   *   <li>When {@link InstanceProperties} {@link InstanceProperties#get(InstanceProperty)} return {@code Get}.</li>
   *   <li>Then calls {@link InstanceProperties#get(InstanceProperty)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ECSCompactionTaskRunner#logEC2Metadata(InstanceProperties, EcsClient)}
   */
  @Test
  @DisplayName("Test logEC2Metadata(InstanceProperties, EcsClient); given 'Get'; when InstanceProperties get(InstanceProperty) return 'Get'; then calls get(InstanceProperty)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ECSCompactionTaskRunner.logEC2Metadata(InstanceProperties, EcsClient)"})
  void testLogEC2Metadata_givenGet_whenInstancePropertiesGetReturnGet_thenCallsGet() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");

    // Act
    ECSCompactionTaskRunner.logEC2Metadata(instanceProperties, mock(EcsClient.class));

    // Assert
    verify(instanceProperties).get(isA(InstanceProperty.class));
  }

  /**
   * Test {@link ECSCompactionTaskRunner#committerOrSendToLambda(TablePropertiesProvider, StateStoreProvider, CompactionJobTracker, InstanceProperties, AmazonSQS)}.
   * <ul>
   *   <li>Then calls {@link AmazonWebServiceRequest#getRequestMetricCollector()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ECSCompactionTaskRunner#committerOrSendToLambda(TablePropertiesProvider, StateStoreProvider, CompactionJobTracker, InstanceProperties, AmazonSQS)}
   */
  @Test
  @DisplayName("Test committerOrSendToLambda(TablePropertiesProvider, StateStoreProvider, CompactionJobTracker, InstanceProperties, AmazonSQS); then calls getRequestMetricCollector()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "sleeper.compaction.core.job.CompactionJobCommitterOrSendToLambda ECSCompactionTaskRunner.committerOrSendToLambda(TablePropertiesProvider, StateStoreProvider, CompactionJobTracker, InstanceProperties, AmazonSQS)"})
  void testCommitterOrSendToLambda_thenCallsGetRequestMetricCollector() {
    // Arrange
    TablePropertiesStore propertiesStore = new TablePropertiesStore(null, mock(Client.class));

    TablePropertiesProvider tablePropertiesProvider = new TablePropertiesProvider(new InstanceProperties(),
        propertiesStore);

    StateStoreProvider stateStoreProvider = new StateStoreProvider(3, mock(Factory.class));

    InMemoryCompactionJobTracker jobTracker = new InMemoryCompactionJobTracker();
    InstanceProperties instanceProperties = new InstanceProperties();

    AmazonSQSAsyncClient sqsClient = new AmazonSQSAsyncClient(new AnonymousAWSCredentials());
    AddPermissionRequest request = mock(AddPermissionRequest.class);
    when(request.getRequestMetricCollector())
        .thenReturn(new AwsStatisticsCollector(new StatisticsFromAwsSdkImpl(new EmptyS3AStatisticsContext())));
    sqsClient.addPermissionAsync(request);
    ArrayList<String> aWSAccountIds = new ArrayList<>();
    sqsClient.addPermissionAsync("42", "Label", aWSAccountIds, new ArrayList<>(), mock(AsyncHandler.class));

    // Act
    ECSCompactionTaskRunner.committerOrSendToLambda(tablePropertiesProvider, stateStoreProvider, jobTracker,
        instanceProperties, sqsClient);

    // Assert
    verify(request).getRequestMetricCollector();
  }
}
