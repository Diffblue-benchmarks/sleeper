package sleeper.clients.deploy;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.amazonaws.services.securitytoken.AWSSecurityTokenService;
import com.amazonaws.services.securitytoken.AWSSecurityTokenServiceAsyncClient;
import com.amazonaws.services.securitytoken.AWSSecurityTokenServiceClient;
import com.amazonaws.services.securitytoken.model.GetCallerIdentityRequest;
import com.amazonaws.services.securitytoken.model.GetCallerIdentityResult;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import software.amazon.awssdk.regions.providers.AwsRegionProvider;

class PopulateInstancePropertiesAwsDiffblueTest {
  /**
   * Test {@link PopulateInstancePropertiesAws#builder(AWSSecurityTokenService, AwsRegionProvider)}.
   * <ul>
   *   <li>Given {@link GetCallerIdentityResult} (default constructor).</li>
   *   <li>Then calls {@link AWSSecurityTokenServiceClient#getCallerIdentity(GetCallerIdentityRequest)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PopulateInstancePropertiesAws#builder(AWSSecurityTokenService, AwsRegionProvider)}
   */
  @Test
  @DisplayName("Test builder(AWSSecurityTokenService, AwsRegionProvider); given GetCallerIdentityResult (default constructor); then calls getCallerIdentity(GetCallerIdentityRequest)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "sleeper.core.deploy.PopulateInstanceProperties.Builder PopulateInstancePropertiesAws.builder(AWSSecurityTokenService, AwsRegionProvider)"})
  void testBuilder_givenGetCallerIdentityResult_thenCallsGetCallerIdentity() throws UnknownHostException {
    try (MockedStatic<InetAddress> mockInetAddress = mockStatic(InetAddress.class)) {

      // Arrange
      mockInetAddress.when(() -> InetAddress.getByAddress(Mockito.<byte[]>any())).thenReturn(mock(InetAddress.class));
      mockInetAddress.when(() -> InetAddress.getByName(Mockito.<String>any())).thenReturn(mock(InetAddress.class));
      mockInetAddress.when(() -> InetAddress.getAllByName(Mockito.<String>any()))
          .thenReturn(new InetAddress[]{mock(InetAddress.class)});
      AWSSecurityTokenServiceAsyncClient sts = mock(AWSSecurityTokenServiceAsyncClient.class);
      when(sts.getCallerIdentity(Mockito.<GetCallerIdentityRequest>any())).thenReturn(new GetCallerIdentityResult());

      // Act
      PopulateInstancePropertiesAws.builder(sts, mock(AwsRegionProvider.class));

      // Assert
      verify(sts).getCallerIdentity(isA(GetCallerIdentityRequest.class));
    }
  }
}
