package sleeper.systemtest.drivers.query;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectListing;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.instance.InstanceProperty;
import sleeper.systemtest.dsl.instance.SystemTestInstanceContext;

class S3ResultsDriverDiffblueTest {
  /**
   * Test {@link S3ResultsDriver#deleteAllQueryResults()}.
   * <ul>
   *   <li>Then calls {@link AmazonS3Client#listObjects(String)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link S3ResultsDriver#deleteAllQueryResults()}
   */
  @Test
  @DisplayName("Test deleteAllQueryResults(); then calls listObjects(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void S3ResultsDriver.deleteAllQueryResults()"})
  void testDeleteAllQueryResults_thenCallsListObjects() throws SdkClientException, UnknownHostException {
    try (MockedStatic<InetAddress> mockInetAddress = mockStatic(InetAddress.class)) {

      // Arrange
      mockInetAddress.when(() -> InetAddress.getByAddress(Mockito.<byte[]>any())).thenReturn(mock(InetAddress.class));
      mockInetAddress.when(() -> InetAddress.getByName(Mockito.<String>any())).thenReturn(mock(InetAddress.class));
      mockInetAddress.when(() -> InetAddress.getAllByName(Mockito.<String>any()))
          .thenReturn(new InetAddress[]{mock(InetAddress.class)});
      InstanceProperties instanceProperties = mock(InstanceProperties.class);
      when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
      SystemTestInstanceContext instance = mock(SystemTestInstanceContext.class);
      when(instance.getInstanceProperties()).thenReturn(instanceProperties);

      ObjectListing objectListing = new ObjectListing();
      objectListing.setBucketName("bucket-name");
      objectListing.setCommonPrefixes(new ArrayList<>());
      objectListing.setDelimiter("Delimiter");
      objectListing.setEncodingType("UTF-8");
      objectListing.setMarker("Marker");
      objectListing.setMaxKeys(3);
      objectListing.setNextMarker("Next Marker");
      objectListing.setPrefix("Prefix");
      objectListing.setRequesterCharged(true);
      objectListing.setTruncated(true);
      AmazonS3Client s3 = mock(AmazonS3Client.class);
      when(s3.listObjects(Mockito.<String>any())).thenReturn(objectListing);

      // Act
      (new S3ResultsDriver(instance, s3)).deleteAllQueryResults();

      // Assert
      verify(s3).listObjects(eq("Get"));
      verify(instanceProperties).get(isA(InstanceProperty.class));
      verify(instance).getInstanceProperties();
    }
  }
}
