package sleeper.systemtest.drivers.cdk;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.amazonaws.services.s3.AmazonS3Client;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.clients.teardown.TearDownClients;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.services.cloudformation.CloudFormationClient;
import software.amazon.awssdk.services.cloudformation.model.DeleteStackRequest;
import software.amazon.awssdk.services.cloudformation.model.DeleteStackRequest.Builder;
import software.amazon.awssdk.services.cloudformation.model.DescribeStacksRequest;
import software.amazon.awssdk.services.ecs.EcsClient;

class TearDownMavenSystemTestDiffblueTest {
  /**
   * Test {@link TearDownMavenSystemTest#tearDown(Path, List, List, List, TearDownClients)}.
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TearDownMavenSystemTest#tearDown(Path, List, List, List, TearDownClients)}
   */
  @Test
  @DisplayName("Test tearDown(Path, List, List, List, TearDownClients); then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void TearDownMavenSystemTest.tearDown(Path, List, List, List, TearDownClients)"})
  void testTearDown_thenThrowIllegalArgumentException()
      throws IOException, InterruptedException, AwsServiceException, SdkClientException {
    // Arrange
    Path scriptsDir = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    ArrayList<String> shortIds = new ArrayList<>();
    shortIds.add("Found system test short IDs to tear down: {}");
    ArrayList<String> shortInstanceNames = new ArrayList<>();
    ArrayList<String> standaloneInstanceIds = new ArrayList<>();
    CloudFormationClient cloudFormationClient = mock(CloudFormationClient.class);
    when(cloudFormationClient.deleteStack(Mockito.<Consumer<Builder>>any()))
        .thenThrow(new IllegalArgumentException("+"));
    when(cloudFormationClient.describeStacks(Mockito.<Consumer<DescribeStacksRequest.Builder>>any()))
        .thenThrow(new IllegalArgumentException("+"));
    TearDownClients clients = mock(TearDownClients.class);
    when(clients.getS3()).thenReturn(new AmazonS3Client());
    when(clients.getCloudFormation()).thenReturn(cloudFormationClient);
    when(clients.getEcs()).thenReturn(mock(EcsClient.class));

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> TearDownMavenSystemTest.tearDown(scriptsDir, shortIds,
        shortInstanceNames, standaloneInstanceIds, clients));
    verify(clients, atLeast(1)).getCloudFormation();
    verify(clients).getEcs();
    verify(clients).getS3();
    verify(cloudFormationClient).deleteStack(isA(Consumer.class));
    verify(cloudFormationClient).describeStacks(isA(Consumer.class));
  }
}
