package sleeper.clients.teardown;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
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
import sleeper.clients.teardown.WaitForStackToDelete.DeleteFailedException;
import sleeper.core.util.PollWithRetries;
import sleeper.core.util.ThreadSleep;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.services.cloudformation.CloudFormationClient;
import software.amazon.awssdk.services.cloudformation.model.CloudFormationException;
import software.amazon.awssdk.services.cloudformation.model.DescribeStacksRequest;
import software.amazon.awssdk.services.cloudformation.model.DescribeStacksRequest.Builder;

class WaitForStackToDeleteDiffblueTest {
  /**
   * Test DeleteFailedException {@link DeleteFailedException#DeleteFailedException(String)}.
   * <p>
   * Method under test: {@link DeleteFailedException#DeleteFailedException(String)}
   */
  @Test
  @DisplayName("Test DeleteFailedException new DeleteFailedException(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DeleteFailedException.<init>(String)"})
  void testDeleteFailedExceptionNewDeleteFailedException() {
    // Arrange and Act
    DeleteFailedException actualDeleteFailedException = new DeleteFailedException("Stack Name");

    // Assert
    assertEquals("Failed to delete stack \"Stack Name\"", actualDeleteFailedException.getLocalizedMessage());
    assertEquals("Failed to delete stack \"Stack Name\"", actualDeleteFailedException.getMessage());
    assertNull(actualDeleteFailedException.getCause());
    assertEquals(0, actualDeleteFailedException.getSuppressed().length);
  }

  /**
   * Test {@link WaitForStackToDelete#pollUntilFinished()}.
   * <ul>
   *   <li>Then calls {@link AwsServiceException#getMessage()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WaitForStackToDelete#pollUntilFinished()}
   */
  @Test
  @DisplayName("Test pollUntilFinished(); then calls getMessage()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void WaitForStackToDelete.pollUntilFinished()"})
  void testPollUntilFinished_thenCallsGetMessage()
      throws InterruptedException, AwsServiceException, SdkClientException {
    // Arrange
    new DeleteFailedException("Waiting for CloudFormation stack to delete: {}");
    CloudFormationException cloudFormationException = mock(CloudFormationException.class);
    when(cloudFormationException.getMessage()).thenReturn("An error occurred");
    CloudFormationClient cloudFormationClient = mock(CloudFormationClient.class);
    when(cloudFormationClient.describeStacks(Mockito.<Consumer<Builder>>any())).thenThrow(cloudFormationException);
    PollWithRetries poll = PollWithRetries.builder()
        .maxRetries(3)
        .pollIntervalMillis(42L)
        .sleepInInterval(mock(ThreadSleep.class))
        .build();

    // Act
    WaitForStackToDelete.from(poll, cloudFormationClient, "Waiting for CloudFormation stack to delete: {}")
        .pollUntilFinished();

    // Assert
    verify(cloudFormationException).getMessage();
    verify(cloudFormationClient).describeStacks(isA(Consumer.class));
  }

  /**
   * Test {@link WaitForStackToDelete#pollUntilFinished()}.
   * <ul>
   *   <li>Then throw {@link DeleteFailedException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WaitForStackToDelete#pollUntilFinished()}
   */
  @Test
  @DisplayName("Test pollUntilFinished(); then throw DeleteFailedException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void WaitForStackToDelete.pollUntilFinished()"})
  void testPollUntilFinished_thenThrowDeleteFailedException()
      throws InterruptedException, AwsServiceException, SdkClientException {
    // Arrange
    CloudFormationClient cloudFormationClient = mock(CloudFormationClient.class);
    when(cloudFormationClient.describeStacks(Mockito.<Consumer<Builder>>any()))
        .thenThrow(new DeleteFailedException("Waiting for CloudFormation stack to delete: {}"));
    PollWithRetries poll = PollWithRetries.builder()
        .maxRetries(3)
        .pollIntervalMillis(42L)
        .sleepInInterval(mock(ThreadSleep.class))
        .build();

    // Act and Assert
    assertThrows(DeleteFailedException.class,
        () -> (new WaitForStackToDelete(poll, cloudFormationClient, "Waiting for CloudFormation stack to delete: {}"))
            .pollUntilFinished());
    verify(cloudFormationClient).describeStacks(isA(Consumer.class));
  }
}
