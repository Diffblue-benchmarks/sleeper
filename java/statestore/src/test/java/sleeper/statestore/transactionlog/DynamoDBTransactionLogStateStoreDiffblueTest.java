package sleeper.statestore.transactionlog;

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
import java.util.ArrayList;
import org.apache.hadoop.conf.Configuration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.properties.SleeperPropertyValues;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.instance.InstanceProperty;
import sleeper.core.properties.table.TableProperties;
import sleeper.core.properties.table.TableProperty;
import sleeper.core.schema.Field;
import sleeper.core.schema.Schema;
import sleeper.core.schema.Schema.Builder;
import sleeper.core.schema.type.ByteArrayType;
import sleeper.core.table.TableStatus;

class DynamoDBTransactionLogStateStoreDiffblueTest {
  /**
   * Test {@link DynamoDBTransactionLogStateStore#builderFrom(InstanceProperties, TableProperties, AmazonDynamoDB, AmazonS3, Configuration)}.
   * <ul>
   *   <li>Then calls {@link SleeperPropertyValues#getInt(SleeperProperty)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBTransactionLogStateStore#builderFrom(InstanceProperties, TableProperties, AmazonDynamoDB, AmazonS3, Configuration)}
   */
  @Test
  @DisplayName("Test builderFrom(InstanceProperties, TableProperties, AmazonDynamoDB, AmazonS3, Configuration); then calls getInt(SleeperProperty)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "sleeper.core.statestore.transactionlog.TransactionLogStateStore.Builder DynamoDBTransactionLogStateStore.builderFrom(InstanceProperties, TableProperties, AmazonDynamoDB, AmazonS3, Configuration)"})
  void testBuilderFrom_thenCallsGetInt() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");

    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema buildResult = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.getInt(Mockito.<TableProperty>any())).thenReturn(1);
    when(tableProperties.getLong(Mockito.<TableProperty>any())).thenReturn(1L);
    when(tableProperties.get(Mockito.<TableProperty>any())).thenReturn("Get");
    when(tableProperties.getSchema()).thenReturn(buildResult);
    when(tableProperties.getStatus()).thenReturn(TableStatus.uniqueIdAndName("42", "Table Name", true));
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    AmazonS3Client s3 = new AmazonS3Client();

    // Act
    DynamoDBTransactionLogStateStore.builderFrom(instanceProperties, tableProperties, dynamoDB, s3,
        new Configuration());

    // Assert
    verify(tableProperties).getInt(isA(TableProperty.class));
    verify(tableProperties, atLeast(1)).getLong(Mockito.<TableProperty>any());
    verify(instanceProperties, atLeast(1)).get(Mockito.<InstanceProperty>any());
    verify(tableProperties, atLeast(1)).get(isA(TableProperty.class));
    verify(tableProperties, atLeast(1)).getSchema();
    verify(tableProperties, atLeast(1)).getStatus();
  }
}
