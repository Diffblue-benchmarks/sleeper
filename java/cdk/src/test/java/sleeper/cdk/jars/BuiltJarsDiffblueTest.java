package sleeper.cdk.jars;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.deploy.LambdaJar;
import sleeper.core.properties.instance.InstanceProperties;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.internal.crossregion.S3CrossRegionSyncClient;

class BuiltJarsDiffblueTest {
  /**
   * Test {@link BuiltJars#from(S3Client, InstanceProperties)}.
   * <ul>
   *   <li>When {@link InstanceProperties#InstanceProperties()}.</li>
   *   <li>Then return bucketName is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BuiltJars#from(S3Client, InstanceProperties)}
   */
  @Test
  @DisplayName("Test from(S3Client, InstanceProperties); when InstanceProperties(); then return bucketName is 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"BuiltJars BuiltJars.from(S3Client, InstanceProperties)"})
  void testFrom_whenInstanceProperties_thenReturnBucketNameIsNull() {
    // Arrange
    S3CrossRegionSyncClient s3 = new S3CrossRegionSyncClient(mock(S3Client.class));

    // Act and Assert
    assertNull(BuiltJars.from(s3, new InstanceProperties()).bucketName());
  }

  /**
   * Test {@link BuiltJars#getRepositoryName(LambdaJar)}.
   * <ul>
   *   <li>Then return {@code null/Image Name}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BuiltJars#getRepositoryName(LambdaJar)}
   */
  @Test
  @DisplayName("Test getRepositoryName(LambdaJar); then return 'null/Image Name'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.lang.String BuiltJars.getRepositoryName(LambdaJar)"})
  void testGetRepositoryName_thenReturnNullImageName() {
    // Arrange
    S3CrossRegionSyncClient s3 = new S3CrossRegionSyncClient(mock(S3Client.class));
    BuiltJars fromResult = BuiltJars.from(s3, new InstanceProperties());

    // Act and Assert
    assertEquals("null/Image Name", fromResult.getRepositoryName(LambdaJar.withFormatAndImage("Format", "Image Name")));
  }
}
