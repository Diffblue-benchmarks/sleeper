package sleeper.bulkimport.starter.executor;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.HashMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.bulkimport.core.job.BulkImportJob;
import sleeper.bulkimport.core.job.BulkImportJob.Builder;
import sleeper.core.properties.instance.InstanceProperties;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.services.emrserverless.EmrServerlessClient;
import software.amazon.awssdk.services.emrserverless.model.StartJobRunRequest;

class EmrServerlessPlatformExecutorDiffblueTest {
  /**
   * Test {@link EmrServerlessPlatformExecutor#runJobOnPlatform(BulkImportArguments)}.
   * <ul>
   *   <li>Then calls {@link BulkImportArguments#getBulkImportJob()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link EmrServerlessPlatformExecutor#runJobOnPlatform(BulkImportArguments)}
   */
  @Test
  @DisplayName("Test runJobOnPlatform(BulkImportArguments); then calls getBulkImportJob()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void EmrServerlessPlatformExecutor.runJobOnPlatform(BulkImportArguments)"})
  void testRunJobOnPlatform_thenCallsGetBulkImportJob() throws AwsServiceException, SdkClientException {
    // Arrange
    EmrServerlessClient emrClient = mock(EmrServerlessClient.class);
    when(emrClient.startJobRun(Mockito.<StartJobRunRequest>any())).thenReturn(null);
    EmrServerlessPlatformExecutor emrServerlessPlatformExecutor = new EmrServerlessPlatformExecutor(emrClient,
        new InstanceProperties());
    BulkImportArguments arguments = mock(BulkImportArguments.class);
    when(arguments.sparkSubmitParametersForServerless()).thenReturn("Spark Submit Parameters For Serverless");
    when(arguments.getJobRunId()).thenReturn("42");
    Builder classNameResult = BulkImportJob.builder().className("Class Name");
    Builder idResult = classNameResult.files(new ArrayList<>()).id("42");
    BulkImportJob buildResult = idResult.platformSpec(new HashMap<>())
        .sparkConf("Key", "42")
        .tableId("42")
        .tableName("Table Name")
        .build();
    when(arguments.getBulkImportJob()).thenReturn(buildResult);

    // Act
    emrServerlessPlatformExecutor.runJobOnPlatform(arguments);

    // Assert
    verify(arguments).getBulkImportJob();
    verify(arguments, atLeast(1)).getJobRunId();
    verify(arguments).sparkSubmitParametersForServerless();
    verify(emrClient).startJobRun(isA(StartJobRunRequest.class));
  }
}
