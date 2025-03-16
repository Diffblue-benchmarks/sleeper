package sleeper.configuration.jars;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import sleeper.core.properties.SleeperPropertyValues;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.instance.InstanceProperty;
import sleeper.core.util.ObjectFactoryException;

class S3UserJarsLoaderDiffblueTest {
  /**
   * Test {@link S3UserJarsLoader#buildObjectFactory()}.
   * <ul>
   *   <li>Then calls {@link SleeperPropertyValues#getList(SleeperProperty)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link S3UserJarsLoader#buildObjectFactory()}
   */
  @Test
  @DisplayName("Test buildObjectFactory(); then calls getList(SleeperProperty)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"sleeper.core.util.ObjectFactory S3UserJarsLoader.buildObjectFactory()"})
  void testBuildObjectFactory_thenCallsGetList() throws ObjectFactoryException {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.getList(Mockito.<InstanceProperty>any())).thenReturn(new ArrayList<>());

    // Act
    (new S3UserJarsLoader(instanceProperties, new AmazonS3Client(), "Local Dir")).buildObjectFactory();

    // Assert
    verify(instanceProperties).getList(isA(InstanceProperty.class));
  }

  /**
   * Test {@link S3UserJarsLoader#buildObjectFactory()}.
   * <ul>
   *   <li>Then calls {@link AmazonS3#getObject(GetObjectRequest, File)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link S3UserJarsLoader#buildObjectFactory()}
   */
  @Test
  @DisplayName("Test buildObjectFactory(); then calls getObject(GetObjectRequest, File)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"sleeper.core.util.ObjectFactory S3UserJarsLoader.buildObjectFactory()"})
  void testBuildObjectFactory_thenCallsGetObject()
      throws SdkClientException, UnknownHostException, ObjectFactoryException {
    try (MockedStatic<InetAddress> mockInetAddress = mockStatic(InetAddress.class)) {

      // Arrange
      mockInetAddress.when(() -> InetAddress.getByAddress(Mockito.<byte[]>any())).thenReturn(mock(InetAddress.class));
      mockInetAddress.when(() -> InetAddress.getByName(Mockito.<String>any())).thenReturn(mock(InetAddress.class));
      mockInetAddress.when(() -> InetAddress.getAllByName(Mockito.<String>any()))
          .thenReturn(new InetAddress[]{mock(InetAddress.class)});

      ArrayList<String> stringList = new ArrayList<>();
      stringList.add("Created ClassLoader from jars {}");
      InstanceProperties instanceProperties = mock(InstanceProperties.class);
      when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
      when(instanceProperties.getList(Mockito.<InstanceProperty>any())).thenReturn(stringList);
      AmazonS3 s3Client = mock(AmazonS3.class);
      when(s3Client.getObject(Mockito.<GetObjectRequest>any(), Mockito.<File>any())).thenReturn(new ObjectMetadata());

      // Act
      (new S3UserJarsLoader(instanceProperties, s3Client, "Local Dir")).buildObjectFactory();

      // Assert
      verify(s3Client).getObject(isA(GetObjectRequest.class), isA(File.class));
      verify(instanceProperties).getList(isA(InstanceProperty.class));
      verify(instanceProperties).get(isA(InstanceProperty.class));
    }
  }
}
