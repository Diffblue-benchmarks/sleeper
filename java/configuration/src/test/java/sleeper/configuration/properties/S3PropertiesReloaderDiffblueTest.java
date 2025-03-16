package sleeper.configuration.properties;

import static org.junit.jupiter.api.Assertions.assertTrue;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClient;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.properties.PropertiesReloader;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.table.TablePropertiesProvider;

class S3PropertiesReloaderDiffblueTest {
  /**
   * Test {@link S3PropertiesReloader#ifConfigured(AmazonS3, InstanceProperties, TablePropertiesProvider)} with {@code s3Client}, {@code instanceProperties}, {@code tablePropertiesProvider}.
   * <p>
   * Method under test: {@link S3PropertiesReloader#ifConfigured(AmazonS3, InstanceProperties, TablePropertiesProvider)}
   */
  @Test
  @DisplayName("Test ifConfigured(AmazonS3, InstanceProperties, TablePropertiesProvider) with 's3Client', 'instanceProperties', 'tablePropertiesProvider'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "PropertiesReloader S3PropertiesReloader.ifConfigured(AmazonS3, InstanceProperties, TablePropertiesProvider)"})
  void testIfConfiguredWithS3ClientInstancePropertiesTablePropertiesProvider() {
    // Arrange
    AmazonS3Client s3Client = new AmazonS3Client();
    InstanceProperties instanceProperties = new InstanceProperties();
    InstanceProperties instanceProperties2 = new InstanceProperties();
    AmazonS3Client s3Client2 = new AmazonS3Client();

    // Act
    PropertiesReloader actualIfConfiguredResult = S3PropertiesReloader.ifConfigured(s3Client, instanceProperties,
        S3TableProperties.createProvider(instanceProperties2, s3Client2, new AmazonDynamoDBAsyncClient()));
    actualIfConfiguredResult.reloadIfNeeded();

    // Assert
    assertTrue(actualIfConfiguredResult instanceof S3PropertiesReloader);
  }

  /**
   * Test {@link S3PropertiesReloader#ifConfigured(AmazonS3, InstanceProperties)} with {@code s3Client}, {@code instanceProperties}.
   * <ul>
   *   <li>Then return {@link S3PropertiesReloader}.</li>
   * </ul>
   * <p>
   * Method under test: {@link S3PropertiesReloader#ifConfigured(AmazonS3, InstanceProperties)}
   */
  @Test
  @DisplayName("Test ifConfigured(AmazonS3, InstanceProperties) with 's3Client', 'instanceProperties'; then return S3PropertiesReloader")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"PropertiesReloader S3PropertiesReloader.ifConfigured(AmazonS3, InstanceProperties)"})
  void testIfConfiguredWithS3ClientInstanceProperties_thenReturnS3PropertiesReloader() {
    // Arrange
    AmazonS3Client s3Client = new AmazonS3Client();

    // Act
    PropertiesReloader actualIfConfiguredResult = S3PropertiesReloader.ifConfigured(s3Client, new InstanceProperties());
    actualIfConfiguredResult.reloadIfNeeded();

    // Assert
    assertTrue(actualIfConfiguredResult instanceof S3PropertiesReloader);
  }
}
