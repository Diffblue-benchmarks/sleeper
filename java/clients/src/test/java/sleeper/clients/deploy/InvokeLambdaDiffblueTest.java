package sleeper.clients.deploy;

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
import software.amazon.awssdk.services.lambda.LambdaClient;
import software.amazon.awssdk.services.lambda.model.InvokeRequest;
import software.amazon.awssdk.services.lambda.model.InvokeRequest.Builder;

class InvokeLambdaDiffblueTest {
  /**
   * Test {@link InvokeLambda#invokeWith(LambdaClient, String)}.
   * <p>
   * Method under test: {@link InvokeLambda#invokeWith(LambdaClient, String)}
   */
  @Test
  @DisplayName("Test invokeWith(LambdaClient, String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void InvokeLambda.invokeWith(LambdaClient, String)"})
  void testInvokeWith() throws AwsServiceException, SdkClientException {
    // Arrange
    LambdaClient lambdaClient = mock(LambdaClient.class);
    when(lambdaClient.invoke(Mockito.<Consumer<Builder>>any())).thenReturn(null);

    // Act
    InvokeLambda.invokeWith(lambdaClient, "Lambda Function");

    // Assert
    verify(lambdaClient).invoke(isA(Consumer.class));
  }
}
