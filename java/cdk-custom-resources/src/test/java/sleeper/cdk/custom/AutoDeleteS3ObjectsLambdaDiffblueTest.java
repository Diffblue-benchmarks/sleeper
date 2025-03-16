package sleeper.cdk.custom;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.CloudFormationCustomResourceEvent;
import com.amazonaws.services.lambda.runtime.events.CloudFormationCustomResourceEvent.CloudFormationCustomResourceEventBuilder;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.HashMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.internal.crossregion.S3CrossRegionSyncClient;

class AutoDeleteS3ObjectsLambdaDiffblueTest {
  /**
   * Test {@link AutoDeleteS3ObjectsLambda#handleEvent(CloudFormationCustomResourceEvent, Context)}.
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AutoDeleteS3ObjectsLambda#handleEvent(CloudFormationCustomResourceEvent, Context)}
   */
  @Test
  @DisplayName("Test handleEvent(CloudFormationCustomResourceEvent, Context); then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AutoDeleteS3ObjectsLambda.handleEvent(CloudFormationCustomResourceEvent, Context)"})
  void testHandleEvent_thenThrowIllegalArgumentException() {
    // Arrange
    AutoDeleteS3ObjectsLambda autoDeleteS3ObjectsLambda = new AutoDeleteS3ObjectsLambda(
        new S3CrossRegionSyncClient(mock(S3Client.class)), 3);
    CloudFormationCustomResourceEventBuilder withLogicalResourceIdResult = CloudFormationCustomResourceEvent.builder()
        .withLogicalResourceId("42");
    CloudFormationCustomResourceEventBuilder withRequestTypeResult = withLogicalResourceIdResult
        .withOldResourceProperties(new HashMap<>())
        .withPhysicalResourceId("42")
        .withRequestId("42")
        .withRequestType("Request Type");
    CloudFormationCustomResourceEvent event = withRequestTypeResult.withResourceProperties(new HashMap<>())
        .withResourceType("Resource Type")
        .withResponseUrl("https://example.org/example")
        .withServiceToken("ABC123")
        .withStackId("42")
        .build();

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> autoDeleteS3ObjectsLambda.handleEvent(event, mock(Context.class)));
  }
}
