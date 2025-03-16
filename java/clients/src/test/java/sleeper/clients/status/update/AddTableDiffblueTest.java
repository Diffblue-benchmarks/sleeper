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
import org.apache.hadoop.conf.Configuration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.properties.SleeperPropertyValues;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.instance.InstanceProperty;
import sleeper.core.properties.table.TableProperties;

class AddTableDiffblueTest {
  /**
   * Test {@link AddTable#AddTable(AmazonS3, AmazonDynamoDB, InstanceProperties, TableProperties)}.
   * <ul>
   *   <li>Given {@code true}.</li>
   *   <li>Then calls {@link SleeperPropertyValues#getBoolean(SleeperProperty)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AddTable#AddTable(AmazonS3, AmazonDynamoDB, InstanceProperties, TableProperties)}
   */
  @Test
  @DisplayName("Test new AddTable(AmazonS3, AmazonDynamoDB, InstanceProperties, TableProperties); given 'true'; then calls getBoolean(SleeperProperty)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AddTable.<init>(AmazonS3, AmazonDynamoDB, InstanceProperties, TableProperties)"})
  void testNewAddTable_givenTrue_thenCallsGetBoolean() {
    // Arrange
    AmazonS3Client s3Client = new AmazonS3Client();
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.getBoolean(Mockito.<InstanceProperty>any())).thenReturn(true);
    when(instanceProperties.getInt(Mockito.<InstanceProperty>any())).thenReturn(1);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");

    // Act
    new AddTable(s3Client, dynamoDB, instanceProperties, new TableProperties(new InstanceProperties()));

    // Assert
    verify(instanceProperties).getBoolean(isA(InstanceProperty.class));
    verify(instanceProperties).getInt(isA(InstanceProperty.class));
    verify(instanceProperties, atLeast(1)).get(Mockito.<InstanceProperty>any());
  }

  /**
   * Test {@link AddTable#AddTable(AmazonS3, AmazonDynamoDB, InstanceProperties, TableProperties, Configuration)}.
   * <ul>
   *   <li>Given {@code true}.</li>
   *   <li>Then calls {@link SleeperPropertyValues#getBoolean(SleeperProperty)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AddTable#AddTable(AmazonS3, AmazonDynamoDB, InstanceProperties, TableProperties, Configuration)}
   */
  @Test
  @DisplayName("Test new AddTable(AmazonS3, AmazonDynamoDB, InstanceProperties, TableProperties, Configuration); given 'true'; then calls getBoolean(SleeperProperty)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void AddTable.<init>(AmazonS3, AmazonDynamoDB, InstanceProperties, TableProperties, Configuration)"})
  void testNewAddTable_givenTrue_thenCallsGetBoolean2() {
    // Arrange
    AmazonS3Client s3Client = new AmazonS3Client();
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.getBoolean(Mockito.<InstanceProperty>any())).thenReturn(true);
    when(instanceProperties.getInt(Mockito.<InstanceProperty>any())).thenReturn(1);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    TableProperties tableProperties = new TableProperties(new InstanceProperties());

    // Act
    new AddTable(s3Client, dynamoDB, instanceProperties, tableProperties, new Configuration());

    // Assert
    verify(instanceProperties).getBoolean(isA(InstanceProperty.class));
    verify(instanceProperties).getInt(isA(InstanceProperty.class));
    verify(instanceProperties, atLeast(1)).get(Mockito.<InstanceProperty>any());
  }
}
