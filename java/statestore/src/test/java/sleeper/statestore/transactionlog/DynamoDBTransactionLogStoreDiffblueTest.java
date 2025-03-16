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
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.instance.InstanceProperty;
import sleeper.core.properties.table.TableProperties;
import sleeper.core.properties.table.TableProperty;
import sleeper.core.schema.Field;
import sleeper.core.schema.Schema;
import sleeper.core.schema.Schema.Builder;
import sleeper.core.schema.type.ByteArrayType;
import sleeper.core.table.TableStatus;

class DynamoDBTransactionLogStoreDiffblueTest {
  /**
   * Test {@link DynamoDBTransactionLogStore#forFiles(InstanceProperties, TableProperties, AmazonDynamoDB, AmazonS3)}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link Field#Field(String, Type)} with {@code Name} and type is {@link ByteArrayType} (default constructor).</li>
   *   <li>Then calls {@link InstanceProperties#get(InstanceProperty)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBTransactionLogStore#forFiles(InstanceProperties, TableProperties, AmazonDynamoDB, AmazonS3)}
   */
  @Test
  @DisplayName("Test forFiles(InstanceProperties, TableProperties, AmazonDynamoDB, AmazonS3); given ArrayList() add Field(String, Type) with 'Name' and type is ByteArrayType (default constructor); then calls get(InstanceProperty)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "DynamoDBTransactionLogStore DynamoDBTransactionLogStore.forFiles(InstanceProperties, TableProperties, AmazonDynamoDB, AmazonS3)"})
  void testForFiles_givenArrayListAddFieldWithNameAndTypeIsByteArrayType_thenCallsGet() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");

    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema buildResult = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.get(Mockito.<TableProperty>any())).thenReturn("Get");
    when(tableProperties.getSchema()).thenReturn(buildResult);
    when(tableProperties.getStatus()).thenReturn(TableStatus.uniqueIdAndName("42", "Table Name", true));
    AmazonDynamoDBAsyncClient dynamoClient = new AmazonDynamoDBAsyncClient();

    // Act
    DynamoDBTransactionLogStore.forFiles(instanceProperties, tableProperties, dynamoClient, new AmazonS3Client());

    // Assert
    verify(instanceProperties).get(isA(InstanceProperty.class));
    verify(tableProperties).get(isA(TableProperty.class));
    verify(tableProperties, atLeast(1)).getSchema();
    verify(tableProperties).getStatus();
  }

  /**
   * Test {@link DynamoDBTransactionLogStore#forPartitions(InstanceProperties, TableProperties, AmazonDynamoDB, AmazonS3)}.
   * <ul>
   *   <li>Then calls {@link InstanceProperties#get(InstanceProperty)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBTransactionLogStore#forPartitions(InstanceProperties, TableProperties, AmazonDynamoDB, AmazonS3)}
   */
  @Test
  @DisplayName("Test forPartitions(InstanceProperties, TableProperties, AmazonDynamoDB, AmazonS3); then calls get(InstanceProperty)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "DynamoDBTransactionLogStore DynamoDBTransactionLogStore.forPartitions(InstanceProperties, TableProperties, AmazonDynamoDB, AmazonS3)"})
  void testForPartitions_thenCallsGet() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");

    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema buildResult = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.get(Mockito.<TableProperty>any())).thenReturn("Get");
    when(tableProperties.getSchema()).thenReturn(buildResult);
    when(tableProperties.getStatus()).thenReturn(TableStatus.uniqueIdAndName("42", "Table Name", true));
    AmazonDynamoDBAsyncClient dynamoClient = new AmazonDynamoDBAsyncClient();

    // Act
    DynamoDBTransactionLogStore.forPartitions(instanceProperties, tableProperties, dynamoClient, new AmazonS3Client());

    // Assert
    verify(instanceProperties).get(isA(InstanceProperty.class));
    verify(tableProperties).get(isA(TableProperty.class));
    verify(tableProperties, atLeast(1)).getSchema();
    verify(tableProperties).getStatus();
  }
}
