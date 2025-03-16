package sleeper.clients.status.update;

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

class TakeTableOfflineDiffblueTest {
  /**
   * Test {@link TakeTableOffline#TakeTableOffline(AmazonS3, AmazonDynamoDB, InstanceProperties)}.
   * <ul>
   *   <li>Given {@code true}.</li>
   *   <li>Then calls {@link SleeperPropertyValues#getBoolean(SleeperProperty)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TakeTableOffline#TakeTableOffline(AmazonS3, AmazonDynamoDB, InstanceProperties)}
   */
  @Test
  @DisplayName("Test new TakeTableOffline(AmazonS3, AmazonDynamoDB, InstanceProperties); given 'true'; then calls getBoolean(SleeperProperty)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void TakeTableOffline.<init>(AmazonS3, AmazonDynamoDB, InstanceProperties)"})
  void testNewTakeTableOffline_givenTrue_thenCallsGetBoolean() {
    // Arrange
    AmazonS3Client s3 = new AmazonS3Client();
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.getBoolean(Mockito.<InstanceProperty>any())).thenReturn(true);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");

    // Act
    new TakeTableOffline(s3, dynamoDB, instanceProperties);

    // Assert
    verify(instanceProperties).getBoolean(isA(InstanceProperty.class));
    verify(instanceProperties, atLeast(1)).get(Mockito.<InstanceProperty>any());
  }
}
