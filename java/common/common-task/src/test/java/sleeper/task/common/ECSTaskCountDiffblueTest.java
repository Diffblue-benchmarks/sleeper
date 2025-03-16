package sleeper.task.common;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.function.Consumer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.services.ecs.EcsClient;
import software.amazon.awssdk.services.ecs.model.DescribeClustersRequest;
import software.amazon.awssdk.services.ecs.model.DescribeClustersRequest.Builder;

class ECSTaskCountDiffblueTest {
  /**
   * Test {@link ECSTaskCount#getNumPendingAndRunningTasks(String, EcsClient)}.
   * <ul>
   *   <li>Then throw {@link DescribeClusterException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ECSTaskCount#getNumPendingAndRunningTasks(String, EcsClient)}
   */
  @Test
  @DisplayName("Test getNumPendingAndRunningTasks(String, EcsClient); then throw DescribeClusterException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"int ECSTaskCount.getNumPendingAndRunningTasks(String, EcsClient)"})
  void testGetNumPendingAndRunningTasks_thenThrowDescribeClusterException()
      throws DescribeClusterException, AwsServiceException, SdkClientException {
    // Arrange
    EcsClient ecsClient = mock(EcsClient.class);
    when(ecsClient.describeClusters(Mockito.<Consumer<Builder>>any()))
        .thenThrow(new DescribeClusterException("An error occurred"));

    // Act and Assert
    assertThrows(DescribeClusterException.class,
        () -> ECSTaskCount.getNumPendingAndRunningTasks("Cluster Name", ecsClient));
    verify(ecsClient).describeClusters(isA(Consumer.class));
  }
}
