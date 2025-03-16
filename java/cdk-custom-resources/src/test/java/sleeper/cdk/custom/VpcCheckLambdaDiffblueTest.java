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
import software.amazon.awssdk.services.ec2.Ec2Client;

class VpcCheckLambdaDiffblueTest {
  /**
   * Test {@link VpcCheckLambda#handleEvent(CloudFormationCustomResourceEvent, Context)}.
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link VpcCheckLambda#handleEvent(CloudFormationCustomResourceEvent, Context)}
   */
  @Test
  @DisplayName("Test handleEvent(CloudFormationCustomResourceEvent, Context); then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void VpcCheckLambda.handleEvent(CloudFormationCustomResourceEvent, Context)"})
  void testHandleEvent_thenThrowIllegalArgumentException() {
    // Arrange
    VpcCheckLambda vpcCheckLambda = new VpcCheckLambda(mock(Ec2Client.class));
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
    assertThrows(IllegalArgumentException.class, () -> vpcCheckLambda.handleEvent(event, mock(Context.class)));
  }
}
