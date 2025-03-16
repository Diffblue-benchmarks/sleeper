package sleeper.clients.docker;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClient;
import com.amazonaws.services.s3.AmazonS3Client;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.function.Consumer;
import org.apache.hadoop.conf.Configuration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import sleeper.clients.docker.DeployDockerInstance.Builder;
import sleeper.core.properties.table.TableProperties;
import software.amazon.awssdk.services.sqs.SqsClient;

class DeployDockerInstanceDiffblueTest {
  /**
   * Test {@link DeployDockerInstance#deploy(String)} with {@code instanceId}.
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeployDockerInstance#deploy(String)}
   */
  @Test
  @DisplayName("Test deploy(String) with 'instanceId'; then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DeployDockerInstance.deploy(String)"})
  void testDeployWithInstanceId_thenThrowIllegalArgumentException() throws UnknownHostException {
    try (MockedStatic<InetAddress> mockInetAddress = mockStatic(InetAddress.class)) {

      // Arrange
      mockInetAddress.when(() -> InetAddress.getByAddress(Mockito.<byte[]>any())).thenReturn(mock(InetAddress.class));
      mockInetAddress.when(() -> InetAddress.getByName(Mockito.<String>any())).thenReturn(mock(InetAddress.class));
      mockInetAddress.when(() -> InetAddress.getAllByName(Mockito.<String>any()))
          .thenReturn(new InetAddress[]{mock(InetAddress.class)});
      Consumer<TableProperties> extraTableProperties = mock(Consumer.class);
      doThrow(new IllegalArgumentException("9.9.9.9")).when(extraTableProperties)
          .accept(Mockito.<TableProperties>any());
      Builder builderResult = DeployDockerInstance.builder();
      Builder configurationResult = builderResult.configuration(new Configuration());
      Builder extraTablePropertiesResult = configurationResult.dynamoDB(new AmazonDynamoDBAsyncClient())
          .extraTableProperties(extraTableProperties);
      DeployDockerInstance buildResult = extraTablePropertiesResult.s3Client(new AmazonS3Client())
          .sqsClient(mock(SqsClient.class))
          .build();

      // Act and Assert
      assertThrows(IllegalArgumentException.class, () -> buildResult.deploy("42"));
      verify(extraTableProperties).accept(isA(TableProperties.class));
    }
  }
}
