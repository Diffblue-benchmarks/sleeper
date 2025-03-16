package sleeper.cdk.util;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Consumer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.instance.InstanceProperty;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.HeadBucketRequest;
import software.amazon.awssdk.services.s3.model.HeadBucketRequest.Builder;

class NewInstanceValidatorDiffblueTest {
  /**
   * Test {@link NewInstanceValidator#validate(InstanceProperties, Path)}.
   * <ul>
   *   <li>Given {@code Get}.</li>
   *   <li>When {@link InstanceProperties} {@link InstanceProperties#get(InstanceProperty)} return {@code Get}.</li>
   *   <li>Then calls {@link InstanceProperties#get(InstanceProperty)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NewInstanceValidator#validate(InstanceProperties, Path)}
   */
  @Test
  @DisplayName("Test validate(InstanceProperties, Path); given 'Get'; when InstanceProperties get(InstanceProperty) return 'Get'; then calls get(InstanceProperty)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void NewInstanceValidator.validate(InstanceProperties, Path)"})
  void testValidate_givenGet_whenInstancePropertiesGetReturnGet_thenCallsGet()
      throws AwsServiceException, SdkClientException {
    // Arrange
    S3Client s3Client = mock(S3Client.class);
    when(s3Client.headBucket(Mockito.<Consumer<Builder>>any())).thenReturn(null);
    NewInstanceValidator newInstanceValidator = new NewInstanceValidator(s3Client, mock(DynamoDbClient.class));
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> newInstanceValidator.validate(instanceProperties,
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt")));
    verify(instanceProperties).get(isA(InstanceProperty.class));
    verify(s3Client).headBucket(isA(Consumer.class));
  }

  /**
   * Test {@link NewInstanceValidator#validate(InstanceProperties, Path)}.
   * <ul>
   *   <li>Given {@link S3Client} {@link S3Client#headBucket(Consumer)} return {@code null}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NewInstanceValidator#validate(InstanceProperties, Path)}
   */
  @Test
  @DisplayName("Test validate(InstanceProperties, Path); given S3Client headBucket(Consumer) return 'null'; then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void NewInstanceValidator.validate(InstanceProperties, Path)"})
  void testValidate_givenS3ClientHeadBucketReturnNull_thenThrowIllegalArgumentException()
      throws AwsServiceException, SdkClientException {
    // Arrange
    S3Client s3Client = mock(S3Client.class);
    when(s3Client.headBucket(Mockito.<Consumer<Builder>>any())).thenReturn(null);
    NewInstanceValidator newInstanceValidator = new NewInstanceValidator(s3Client, mock(DynamoDbClient.class));

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> newInstanceValidator.validate(UtilsTestHelper.createUserDefinedInstanceProperties(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt")));
    verify(s3Client).headBucket(isA(Consumer.class));
  }
}
