package sleeper.configuration.properties;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClient;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.properties.SleeperPropertyValues;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.instance.InstanceProperty;

class S3TablePropertiesDiffblueTest {
  /**
   * Test {@link S3TableProperties#createStore(InstanceProperties, AmazonS3, AmazonDynamoDB)}.
   * <ul>
   *   <li>Given {@code true}.</li>
   *   <li>Then calls {@link SleeperPropertyValues#getBoolean(SleeperProperty)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link S3TableProperties#createStore(InstanceProperties, AmazonS3, AmazonDynamoDB)}
   */
  @Test
  @DisplayName("Test createStore(InstanceProperties, AmazonS3, AmazonDynamoDB); given 'true'; then calls getBoolean(SleeperProperty)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "sleeper.core.properties.table.TablePropertiesStore S3TableProperties.createStore(InstanceProperties, AmazonS3, AmazonDynamoDB)"})
  void testCreateStore_givenTrue_thenCallsGetBoolean() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.getBoolean(Mockito.<InstanceProperty>any())).thenReturn(true);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    AmazonS3Client s3Client = new AmazonS3Client();

    // Act
    S3TableProperties.createStore(instanceProperties, s3Client, new AmazonDynamoDBAsyncClient());

    // Assert
    verify(instanceProperties).getBoolean(isA(InstanceProperty.class));
    verify(instanceProperties, atLeast(1)).get(Mockito.<InstanceProperty>any());
  }

  /**
   * Test {@link S3TableProperties#createProvider(InstanceProperties, AmazonS3, AmazonDynamoDB)}.
   * <ul>
   *   <li>Given {@code true}.</li>
   *   <li>Then calls {@link SleeperPropertyValues#getBoolean(SleeperProperty)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link S3TableProperties#createProvider(InstanceProperties, AmazonS3, AmazonDynamoDB)}
   */
  @Test
  @DisplayName("Test createProvider(InstanceProperties, AmazonS3, AmazonDynamoDB); given 'true'; then calls getBoolean(SleeperProperty)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "sleeper.core.properties.table.TablePropertiesProvider S3TableProperties.createProvider(InstanceProperties, AmazonS3, AmazonDynamoDB)"})
  void testCreateProvider_givenTrue_thenCallsGetBoolean() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.getBoolean(Mockito.<InstanceProperty>any())).thenReturn(true);
    when(instanceProperties.getInt(Mockito.<InstanceProperty>any())).thenReturn(1);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    AmazonS3Client s3Client = new AmazonS3Client();

    // Act
    S3TableProperties.createProvider(instanceProperties, s3Client, new AmazonDynamoDBAsyncClient());

    // Assert
    verify(instanceProperties).getBoolean(isA(InstanceProperty.class));
    verify(instanceProperties).getInt(isA(InstanceProperty.class));
    verify(instanceProperties, atLeast(1)).get(Mockito.<InstanceProperty>any());
  }
}
