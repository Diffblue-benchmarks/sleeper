package sleeper.configuration.properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import sleeper.core.properties.SleeperProperties;
import sleeper.core.properties.SleeperPropertyIndex;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.instance.InstanceProperty;

class S3InstancePropertiesDiffblueTest {
  /**
   * Test {@link S3InstanceProperties#loadGivenInstanceIdNoValidation(AmazonS3, String)}.
   * <ul>
   *   <li>Given {@code Object As String}.</li>
   *   <li>Then return toMap size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link S3InstanceProperties#loadGivenInstanceIdNoValidation(AmazonS3, String)}
   */
  @Test
  @DisplayName("Test loadGivenInstanceIdNoValidation(AmazonS3, String); given 'Object As String'; then return toMap size is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"InstanceProperties S3InstanceProperties.loadGivenInstanceIdNoValidation(AmazonS3, String)"})
  void testLoadGivenInstanceIdNoValidation_givenObjectAsString_thenReturnToMapSizeIsOne()
      throws SdkClientException, UnknownHostException {
    try (MockedStatic<InetAddress> mockInetAddress = mockStatic(InetAddress.class)) {

      // Arrange
      mockInetAddress.when(() -> InetAddress.getByAddress(Mockito.<byte[]>any())).thenReturn(mock(InetAddress.class));
      mockInetAddress.when(() -> InetAddress.getByName(Mockito.<String>any())).thenReturn(mock(InetAddress.class));
      mockInetAddress.when(() -> InetAddress.getAllByName(Mockito.<String>any()))
          .thenReturn(new InetAddress[]{mock(InetAddress.class)});
      AmazonS3Client s3Client = mock(AmazonS3Client.class);
      when(s3Client.getObjectAsString(Mockito.<String>any(), Mockito.<String>any())).thenReturn("Object As String");

      // Act
      InstanceProperties actualLoadGivenInstanceIdNoValidationResult = S3InstanceProperties
          .loadGivenInstanceIdNoValidation(s3Client, "42");

      // Assert
      verify(s3Client).getObjectAsString(eq("sleeper-42-config"), eq("instance.properties"));
      Map<String, String> toMapResult = actualLoadGivenInstanceIdNoValidationResult.toMap();
      assertEquals(1, toMapResult.size());
      assertEquals("As String", toMapResult.get("Object"));
      Properties properties = actualLoadGivenInstanceIdNoValidationResult.getProperties();
      assertEquals(1, properties.size());
      assertEquals("As String", properties.get("Object"));
      Stream<Entry<String, String>> unknownProperties = actualLoadGivenInstanceIdNoValidationResult
          .getUnknownProperties();
      assertEquals(1, unknownProperties.limit(5).collect(Collectors.toList()).size());
      SleeperPropertyIndex<InstanceProperty> propertiesIndex = actualLoadGivenInstanceIdNoValidationResult
          .getPropertiesIndex();
      assertEquals(131, propertiesIndex.getCdkDefined().size());
      assertEquals(325, propertiesIndex.getUserDefined().size());
      assertEquals(456, propertiesIndex.getAll().size());
      assertTrue(actualLoadGivenInstanceIdNoValidationResult.getTags().isEmpty());
      assertTrue(actualLoadGivenInstanceIdNoValidationResult.getTagsProperties().isEmpty());
    }
  }

  /**
   * Test {@link S3InstanceProperties#saveToS3(AmazonS3, InstanceProperties)}.
   * <ul>
   *   <li>Given {@code Save As String}.</li>
   *   <li>Then calls {@link SleeperProperties#saveAsString()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link S3InstanceProperties#saveToS3(AmazonS3, InstanceProperties)}
   */
  @Test
  @DisplayName("Test saveToS3(AmazonS3, InstanceProperties); given 'Save As String'; then calls saveAsString()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void S3InstanceProperties.saveToS3(AmazonS3, InstanceProperties)"})
  void testSaveToS3_givenSaveAsString_thenCallsSaveAsString() throws SdkClientException {
    // Arrange
    PutObjectResult putObjectResult = new PutObjectResult();
    putObjectResult.setBucketKeyEnabled(true);
    putObjectResult.setContentMd5("MjdjN2NmNDAwMjI5MTAzZTAwYzZkODgzMDAyOWUyOWI=");
    putObjectResult.setETag("E Tag");
    putObjectResult
        .setExpirationTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    putObjectResult.setExpirationTimeRuleId("42");
    putObjectResult.setMetadata(new ObjectMetadata());
    putObjectResult.setRequesterCharged(true);
    putObjectResult.setSSEAlgorithm("Algorithm");
    putObjectResult.setSSECustomerAlgorithm("Algorithm");
    putObjectResult.setSSECustomerKeyMd5("27c7cf400229103e00c6d8830029e29b");
    putObjectResult.setVersionId("42");
    AmazonS3Client s3Client = mock(AmazonS3Client.class);
    when(s3Client.putObject(Mockito.<String>any(), Mockito.<String>any(), Mockito.<String>any()))
        .thenReturn(putObjectResult);
    InstanceProperties properties = mock(InstanceProperties.class);
    when(properties.saveAsString()).thenReturn("Save As String");
    when(properties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");

    // Act
    S3InstanceProperties.saveToS3(s3Client, properties);

    // Assert
    verify(s3Client).putObject(eq("Get"), eq("instance.properties"), eq("Save As String"));
    verify(properties).saveAsString();
    verify(properties).get(isA(InstanceProperty.class));
  }

  /**
   * Test {@link S3InstanceProperties#reload(AmazonS3, InstanceProperties)}.
   * <ul>
   *   <li>Given {@code Get}.</li>
   *   <li>Then calls {@link AmazonS3Client#getObjectAsString(String, String)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link S3InstanceProperties#reload(AmazonS3, InstanceProperties)}
   */
  @Test
  @DisplayName("Test reload(AmazonS3, InstanceProperties); given 'Get'; then calls getObjectAsString(String, String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void S3InstanceProperties.reload(AmazonS3, InstanceProperties)"})
  void testReload_givenGet_thenCallsGetObjectAsString() throws SdkClientException {
    // Arrange
    AmazonS3Client s3Client = mock(AmazonS3Client.class);
    when(s3Client.getObjectAsString(Mockito.<String>any(), Mockito.<String>any())).thenReturn("Object As String");
    InstanceProperties properties = mock(InstanceProperties.class);
    when(properties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    doNothing().when(properties).resetAndValidate(Mockito.<Properties>any());

    // Act
    S3InstanceProperties.reload(s3Client, properties);

    // Assert
    verify(s3Client).getObjectAsString(eq("Get"), eq("instance.properties"));
    verify(properties).resetAndValidate(isA(Properties.class));
    verify(properties).get(isA(InstanceProperty.class));
  }

  /**
   * Test {@link S3InstanceProperties#reloadGivenInstanceId(AmazonS3, InstanceProperties, String)}.
   * <ul>
   *   <li>Then calls {@link AmazonS3Client#getObjectAsString(String, String)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link S3InstanceProperties#reloadGivenInstanceId(AmazonS3, InstanceProperties, String)}
   */
  @Test
  @DisplayName("Test reloadGivenInstanceId(AmazonS3, InstanceProperties, String); then calls getObjectAsString(String, String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void S3InstanceProperties.reloadGivenInstanceId(AmazonS3, InstanceProperties, String)"})
  void testReloadGivenInstanceId_thenCallsGetObjectAsString() throws SdkClientException, UnknownHostException {
    try (MockedStatic<InetAddress> mockInetAddress = mockStatic(InetAddress.class)) {

      // Arrange
      mockInetAddress.when(() -> InetAddress.getByAddress(Mockito.<byte[]>any())).thenReturn(mock(InetAddress.class));
      mockInetAddress.when(() -> InetAddress.getByName(Mockito.<String>any())).thenReturn(mock(InetAddress.class));
      mockInetAddress.when(() -> InetAddress.getAllByName(Mockito.<String>any()))
          .thenReturn(new InetAddress[]{mock(InetAddress.class)});
      AmazonS3Client s3Client = mock(AmazonS3Client.class);
      when(s3Client.getObjectAsString(Mockito.<String>any(), Mockito.<String>any())).thenReturn("Object As String");
      InstanceProperties properties = mock(InstanceProperties.class);
      doNothing().when(properties).resetAndValidate(Mockito.<Properties>any());

      // Act
      S3InstanceProperties.reloadGivenInstanceId(s3Client, properties, "42");

      // Assert
      verify(s3Client).getObjectAsString(eq("sleeper-42-config"), eq("instance.properties"));
      verify(properties).resetAndValidate(isA(Properties.class));
    }
  }
}
