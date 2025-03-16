package sleeper.query.runner.tracker;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AnonymousAWSCredentials;
import com.amazonaws.auth.STSSessionCredentials;
import com.amazonaws.handlers.AsyncHandler;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClient;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;
import com.amazonaws.services.securitytoken.AWSSecurityTokenServiceAsyncClient;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.HashMap;
import java.util.function.BiFunction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.properties.SleeperPropertyValues;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.instance.InstanceProperty;

class DynamoDBQueryTrackerDiffblueTest {
  /**
   * Test {@link DynamoDBQueryTracker#DynamoDBQueryTracker(InstanceProperties, AmazonDynamoDB)}.
   * <p>
   * Method under test: {@link DynamoDBQueryTracker#DynamoDBQueryTracker(InstanceProperties, AmazonDynamoDB)}
   */
  @Test
  @DisplayName("Test new DynamoDBQueryTracker(InstanceProperties, AmazonDynamoDB)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBQueryTracker.<init>(InstanceProperties, AmazonDynamoDB)"})
  void testNewDynamoDBQueryTracker() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.getLong(Mockito.<InstanceProperty>any())).thenReturn(1L);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");

    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    dynamoDB.putItemAsync(new PutItemRequest());

    // Act
    new DynamoDBQueryTracker(instanceProperties, dynamoDB);

    // Assert
    verify(instanceProperties).getLong(isA(InstanceProperty.class));
    verify(instanceProperties).get(isA(InstanceProperty.class));
  }

  /**
   * Test {@link DynamoDBQueryTracker#DynamoDBQueryTracker(InstanceProperties, AmazonDynamoDB)}.
   * <p>
   * Method under test: {@link DynamoDBQueryTracker#DynamoDBQueryTracker(InstanceProperties, AmazonDynamoDB)}
   */
  @Test
  @DisplayName("Test new DynamoDBQueryTracker(InstanceProperties, AmazonDynamoDB)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBQueryTracker.<init>(InstanceProperties, AmazonDynamoDB)"})
  void testNewDynamoDBQueryTracker2() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.getLong(Mockito.<InstanceProperty>any())).thenReturn(1L);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");

    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    dynamoDB.putItemAsync("42", new HashMap<>(), "Return Values", mock(AsyncHandler.class));
    dynamoDB.putItemAsync(new PutItemRequest());

    // Act
    new DynamoDBQueryTracker(instanceProperties, dynamoDB);

    // Assert
    verify(instanceProperties).getLong(isA(InstanceProperty.class));
    verify(instanceProperties).get(isA(InstanceProperty.class));
  }

  /**
   * Test {@link DynamoDBQueryTracker#DynamoDBQueryTracker(InstanceProperties, AmazonDynamoDB)}.
   * <p>
   * Method under test: {@link DynamoDBQueryTracker#DynamoDBQueryTracker(InstanceProperties, AmazonDynamoDB)}
   */
  @Test
  @DisplayName("Test new DynamoDBQueryTracker(InstanceProperties, AmazonDynamoDB)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBQueryTracker.<init>(InstanceProperties, AmazonDynamoDB)"})
  void testNewDynamoDBQueryTracker3() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.getLong(Mockito.<InstanceProperty>any())).thenReturn(1L);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");

    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    dynamoDB.putItemAsync(new PutItemRequest(), mock(AsyncHandler.class));
    dynamoDB.putItemAsync("Table Name", new HashMap<>(), "42");

    // Act
    new DynamoDBQueryTracker(instanceProperties, dynamoDB);

    // Assert
    verify(instanceProperties).getLong(isA(InstanceProperty.class));
    verify(instanceProperties).get(isA(InstanceProperty.class));
  }

  /**
   * Test {@link DynamoDBQueryTracker#DynamoDBQueryTracker(InstanceProperties, AmazonDynamoDB)}.
   * <p>
   * Method under test: {@link DynamoDBQueryTracker#DynamoDBQueryTracker(InstanceProperties, AmazonDynamoDB)}
   */
  @Test
  @DisplayName("Test new DynamoDBQueryTracker(InstanceProperties, AmazonDynamoDB)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBQueryTracker.<init>(InstanceProperties, AmazonDynamoDB)"})
  void testNewDynamoDBQueryTracker4() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.getLong(Mockito.<InstanceProperty>any())).thenReturn(1L);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");

    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient(new AnonymousAWSCredentials());
    dynamoDB.putItemAsync("42", new HashMap<>(), "Return Values", mock(AsyncHandler.class));
    dynamoDB.putItemAsync(new PutItemRequest());

    // Act
    new DynamoDBQueryTracker(instanceProperties, dynamoDB);

    // Assert
    verify(instanceProperties).getLong(isA(InstanceProperty.class));
    verify(instanceProperties).get(isA(InstanceProperty.class));
  }

  /**
   * Test {@link DynamoDBQueryTracker#DynamoDBQueryTracker(InstanceProperties, AmazonDynamoDB)}.
   * <p>
   * Method under test: {@link DynamoDBQueryTracker#DynamoDBQueryTracker(InstanceProperties, AmazonDynamoDB)}
   */
  @Test
  @DisplayName("Test new DynamoDBQueryTracker(InstanceProperties, AmazonDynamoDB)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBQueryTracker.<init>(InstanceProperties, AmazonDynamoDB)"})
  void testNewDynamoDBQueryTracker5() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.getLong(Mockito.<InstanceProperty>any())).thenReturn(1L);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");

    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    dynamoDB.putItemAsync(new PutItemRequest(), mock(AsyncHandler.class));
    dynamoDB.putItemAsync("42", new HashMap<>(), "Return Values", mock(AsyncHandler.class));
    dynamoDB.putItemAsync(new PutItemRequest());

    // Act
    new DynamoDBQueryTracker(instanceProperties, dynamoDB);

    // Assert
    verify(instanceProperties).getLong(isA(InstanceProperty.class));
    verify(instanceProperties).get(isA(InstanceProperty.class));
  }

  /**
   * Test {@link DynamoDBQueryTracker#DynamoDBQueryTracker(InstanceProperties, AmazonDynamoDB)}.
   * <p>
   * Method under test: {@link DynamoDBQueryTracker#DynamoDBQueryTracker(InstanceProperties, AmazonDynamoDB)}
   */
  @Test
  @DisplayName("Test new DynamoDBQueryTracker(InstanceProperties, AmazonDynamoDB)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBQueryTracker.<init>(InstanceProperties, AmazonDynamoDB)"})
  void testNewDynamoDBQueryTracker6() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.getLong(Mockito.<InstanceProperty>any())).thenReturn(1L);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");

    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient(new AnonymousAWSCredentials());
    dynamoDB.putItemAsync(new PutItemRequest(), mock(AsyncHandler.class));
    dynamoDB.putItemAsync("Table Name", new HashMap<>(), "42");

    // Act
    new DynamoDBQueryTracker(instanceProperties, dynamoDB);

    // Assert
    verify(instanceProperties).getLong(isA(InstanceProperty.class));
    verify(instanceProperties).get(isA(InstanceProperty.class));
  }

  /**
   * Test {@link DynamoDBQueryTracker#DynamoDBQueryTracker(InstanceProperties, AmazonDynamoDB)}.
   * <p>
   * Method under test: {@link DynamoDBQueryTracker#DynamoDBQueryTracker(InstanceProperties, AmazonDynamoDB)}
   */
  @Test
  @DisplayName("Test new DynamoDBQueryTracker(InstanceProperties, AmazonDynamoDB)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBQueryTracker.<init>(InstanceProperties, AmazonDynamoDB)"})
  void testNewDynamoDBQueryTracker7() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.getLong(Mockito.<InstanceProperty>any())).thenReturn(1L);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");

    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient((AWSCredentials) null);
    dynamoDB.putItemAsync("42", new HashMap<>(), "Return Values", mock(AsyncHandler.class));
    dynamoDB.putItemAsync(new PutItemRequest());

    // Act
    new DynamoDBQueryTracker(instanceProperties, dynamoDB);

    // Assert
    verify(instanceProperties).getLong(isA(InstanceProperty.class));
    verify(instanceProperties).get(isA(InstanceProperty.class));
  }

  /**
   * Test {@link DynamoDBQueryTracker#DynamoDBQueryTracker(InstanceProperties, AmazonDynamoDB)}.
   * <p>
   * Method under test: {@link DynamoDBQueryTracker#DynamoDBQueryTracker(InstanceProperties, AmazonDynamoDB)}
   */
  @Test
  @DisplayName("Test new DynamoDBQueryTracker(InstanceProperties, AmazonDynamoDB)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBQueryTracker.<init>(InstanceProperties, AmazonDynamoDB)"})
  void testNewDynamoDBQueryTracker8() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.getLong(Mockito.<InstanceProperty>any())).thenReturn(1L);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");

    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient(
        new STSSessionCredentials(new AnonymousAWSCredentials(), 1));
    dynamoDB.putItemAsync("42", new HashMap<>(), "Return Values", mock(AsyncHandler.class));
    dynamoDB.putItemAsync(new PutItemRequest());

    // Act
    new DynamoDBQueryTracker(instanceProperties, dynamoDB);

    // Assert
    verify(instanceProperties).getLong(isA(InstanceProperty.class));
    verify(instanceProperties).get(isA(InstanceProperty.class));
  }

  /**
   * Test {@link DynamoDBQueryTracker#DynamoDBQueryTracker(InstanceProperties, AmazonDynamoDB)}.
   * <p>
   * Method under test: {@link DynamoDBQueryTracker#DynamoDBQueryTracker(InstanceProperties, AmazonDynamoDB)}
   */
  @Test
  @DisplayName("Test new DynamoDBQueryTracker(InstanceProperties, AmazonDynamoDB)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBQueryTracker.<init>(InstanceProperties, AmazonDynamoDB)"})
  void testNewDynamoDBQueryTracker9() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.getLong(Mockito.<InstanceProperty>any())).thenReturn(1L);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");

    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient(new AnonymousAWSCredentials());
    dynamoDB.putItemAsync(new PutItemRequest());
    dynamoDB.putItemAsync("42", new HashMap<>(), "Return Values", mock(AsyncHandler.class));
    dynamoDB.putItemAsync(new PutItemRequest());

    // Act
    new DynamoDBQueryTracker(instanceProperties, dynamoDB);

    // Assert
    verify(instanceProperties).getLong(isA(InstanceProperty.class));
    verify(instanceProperties).get(isA(InstanceProperty.class));
  }

  /**
   * Test {@link DynamoDBQueryTracker#DynamoDBQueryTracker(InstanceProperties, AmazonDynamoDB)}.
   * <p>
   * Method under test: {@link DynamoDBQueryTracker#DynamoDBQueryTracker(InstanceProperties, AmazonDynamoDB)}
   */
  @Test
  @DisplayName("Test new DynamoDBQueryTracker(InstanceProperties, AmazonDynamoDB)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBQueryTracker.<init>(InstanceProperties, AmazonDynamoDB)"})
  void testNewDynamoDBQueryTracker10() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.getLong(Mockito.<InstanceProperty>any())).thenReturn(1L);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");

    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient(new AnonymousAWSCredentials());
    dynamoDB.putItemAsync("42", new HashMap<>(), mock(AsyncHandler.class));
    dynamoDB.putItemAsync(new PutItemRequest());
    dynamoDB.putItemAsync("42", new HashMap<>(), "Return Values", mock(AsyncHandler.class));
    dynamoDB.putItemAsync(new PutItemRequest());

    // Act
    new DynamoDBQueryTracker(instanceProperties, dynamoDB);

    // Assert
    verify(instanceProperties).getLong(isA(InstanceProperty.class));
    verify(instanceProperties).get(isA(InstanceProperty.class));
  }

  /**
   * Test {@link DynamoDBQueryTracker#DynamoDBQueryTracker(InstanceProperties, AmazonDynamoDB)}.
   * <p>
   * Method under test: {@link DynamoDBQueryTracker#DynamoDBQueryTracker(InstanceProperties, AmazonDynamoDB)}
   */
  @Test
  @DisplayName("Test new DynamoDBQueryTracker(InstanceProperties, AmazonDynamoDB)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBQueryTracker.<init>(InstanceProperties, AmazonDynamoDB)"})
  void testNewDynamoDBQueryTracker11() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.getLong(Mockito.<InstanceProperty>any())).thenReturn(1L);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");

    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient(new AnonymousAWSCredentials());
    dynamoDB.putItemAsync("42", new HashMap<>(), "Return Values");
    dynamoDB.putItemAsync(new PutItemRequest());
    dynamoDB.putItemAsync("42", new HashMap<>(), "Return Values", mock(AsyncHandler.class));
    dynamoDB.putItemAsync(new PutItemRequest());

    // Act
    new DynamoDBQueryTracker(instanceProperties, dynamoDB);

    // Assert
    verify(instanceProperties).getLong(isA(InstanceProperty.class));
    verify(instanceProperties).get(isA(InstanceProperty.class));
  }

  /**
   * Test {@link DynamoDBQueryTracker#DynamoDBQueryTracker(InstanceProperties, AmazonDynamoDB)}.
   * <p>
   * Method under test: {@link DynamoDBQueryTracker#DynamoDBQueryTracker(InstanceProperties, AmazonDynamoDB)}
   */
  @Test
  @DisplayName("Test new DynamoDBQueryTracker(InstanceProperties, AmazonDynamoDB)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBQueryTracker.<init>(InstanceProperties, AmazonDynamoDB)"})
  void testNewDynamoDBQueryTracker12() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.getLong(Mockito.<InstanceProperty>any())).thenReturn(1L);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");

    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient(
        new STSSessionCredentials(new AWSSecurityTokenServiceAsyncClient(), 1));
    dynamoDB.putItemAsync(new PutItemRequest(), mock(AsyncHandler.class));
    dynamoDB.putItemAsync("Table Name", new HashMap<>(), "42");

    // Act
    new DynamoDBQueryTracker(instanceProperties, dynamoDB);

    // Assert
    verify(instanceProperties).getLong(isA(InstanceProperty.class));
    verify(instanceProperties).get(isA(InstanceProperty.class));
  }

  /**
   * Test {@link DynamoDBQueryTracker#DynamoDBQueryTracker(InstanceProperties, AmazonDynamoDB)}.
   * <p>
   * Method under test: {@link DynamoDBQueryTracker#DynamoDBQueryTracker(InstanceProperties, AmazonDynamoDB)}
   */
  @Test
  @DisplayName("Test new DynamoDBQueryTracker(InstanceProperties, AmazonDynamoDB)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBQueryTracker.<init>(InstanceProperties, AmazonDynamoDB)"})
  void testNewDynamoDBQueryTracker13() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.getLong(Mockito.<InstanceProperty>any())).thenReturn(1L);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");

    PutItemRequest request = new PutItemRequest();
    request.addExpressionAttributeValuesEntry("42", new AttributeValue("foo"));

    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient(new AnonymousAWSCredentials());
    dynamoDB.putItemAsync(request, mock(AsyncHandler.class));
    dynamoDB.putItemAsync("Table Name", new HashMap<>(), "42");

    // Act
    new DynamoDBQueryTracker(instanceProperties, dynamoDB);

    // Assert
    verify(instanceProperties).getLong(isA(InstanceProperty.class));
    verify(instanceProperties).get(isA(InstanceProperty.class));
  }

  /**
   * Test {@link DynamoDBQueryTracker#DynamoDBQueryTracker(InstanceProperties, AmazonDynamoDB)}.
   * <p>
   * Method under test: {@link DynamoDBQueryTracker#DynamoDBQueryTracker(InstanceProperties, AmazonDynamoDB)}
   */
  @Test
  @DisplayName("Test new DynamoDBQueryTracker(InstanceProperties, AmazonDynamoDB)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBQueryTracker.<init>(InstanceProperties, AmazonDynamoDB)"})
  void testNewDynamoDBQueryTracker14() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.getLong(Mockito.<InstanceProperty>any())).thenReturn(1L);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");

    PutItemRequest request = new PutItemRequest();
    request.setExpressionAttributeValues(new HashMap<>());
    request.addExpressionAttributeValuesEntry("42", new AttributeValue("foo"));

    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient(new AnonymousAWSCredentials());
    dynamoDB.putItemAsync(request, mock(AsyncHandler.class));
    dynamoDB.putItemAsync("Table Name", new HashMap<>(), "42");

    // Act
    new DynamoDBQueryTracker(instanceProperties, dynamoDB);

    // Assert
    verify(instanceProperties).getLong(isA(InstanceProperty.class));
    verify(instanceProperties).get(isA(InstanceProperty.class));
  }

  /**
   * Test {@link DynamoDBQueryTracker#DynamoDBQueryTracker(InstanceProperties, AmazonDynamoDB)}.
   * <ul>
   *   <li>Given empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBQueryTracker#DynamoDBQueryTracker(InstanceProperties, AmazonDynamoDB)}
   */
  @Test
  @DisplayName("Test new DynamoDBQueryTracker(InstanceProperties, AmazonDynamoDB); given empty string")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBQueryTracker.<init>(InstanceProperties, AmazonDynamoDB)"})
  void testNewDynamoDBQueryTracker_givenEmptyString() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.getLong(Mockito.<InstanceProperty>any())).thenReturn(1L);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");

    HashMap<String, AttributeValue> item = new HashMap<>();
    item.put("42", new AttributeValue("foo"));

    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    dynamoDB.putItemAsync("", new HashMap<>(), mock(AsyncHandler.class));
    dynamoDB.putItemAsync(new PutItemRequest(), mock(AsyncHandler.class));
    dynamoDB.putItemAsync("Table Name", item, "42");

    // Act
    new DynamoDBQueryTracker(instanceProperties, dynamoDB);

    // Assert
    verify(instanceProperties).getLong(isA(InstanceProperty.class));
    verify(instanceProperties).get(isA(InstanceProperty.class));
  }

  /**
   * Test {@link DynamoDBQueryTracker#DynamoDBQueryTracker(InstanceProperties, AmazonDynamoDB)}.
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@code 42} is {@link AttributeValue#AttributeValue(String)} with s is {@code foo}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBQueryTracker#DynamoDBQueryTracker(InstanceProperties, AmazonDynamoDB)}
   */
  @Test
  @DisplayName("Test new DynamoDBQueryTracker(InstanceProperties, AmazonDynamoDB); given HashMap() '42' is AttributeValue(String) with s is 'foo'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBQueryTracker.<init>(InstanceProperties, AmazonDynamoDB)"})
  void testNewDynamoDBQueryTracker_givenHashMap42IsAttributeValueWithSIsFoo() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.getLong(Mockito.<InstanceProperty>any())).thenReturn(1L);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");

    HashMap<String, AttributeValue> item = new HashMap<>();
    item.put("42", new AttributeValue("foo"));

    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    dynamoDB.putItemAsync(new PutItemRequest(), mock(AsyncHandler.class));
    dynamoDB.putItemAsync("Table Name", item, "42");

    // Act
    new DynamoDBQueryTracker(instanceProperties, dynamoDB);

    // Assert
    verify(instanceProperties).getLong(isA(InstanceProperty.class));
    verify(instanceProperties).get(isA(InstanceProperty.class));
  }

  /**
   * Test {@link DynamoDBQueryTracker#DynamoDBQueryTracker(InstanceProperties, AmazonDynamoDB)}.
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} replaceAll {@link BiFunction}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBQueryTracker#DynamoDBQueryTracker(InstanceProperties, AmazonDynamoDB)}
   */
  @Test
  @DisplayName("Test new DynamoDBQueryTracker(InstanceProperties, AmazonDynamoDB); given HashMap() replaceAll BiFunction")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBQueryTracker.<init>(InstanceProperties, AmazonDynamoDB)"})
  void testNewDynamoDBQueryTracker_givenHashMapReplaceAllBiFunction() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.getLong(Mockito.<InstanceProperty>any())).thenReturn(1L);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");

    HashMap<String, AttributeValue> item = new HashMap<>();
    item.replaceAll(mock(BiFunction.class));
    item.put("42", new AttributeValue("foo"));

    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    dynamoDB.putItemAsync(new PutItemRequest(), mock(AsyncHandler.class));
    dynamoDB.putItemAsync("Table Name", item, "42");

    // Act
    new DynamoDBQueryTracker(instanceProperties, dynamoDB);

    // Assert
    verify(instanceProperties).getLong(isA(InstanceProperty.class));
    verify(instanceProperties).get(isA(InstanceProperty.class));
  }

  /**
   * Test {@link DynamoDBQueryTracker#DynamoDBQueryTracker(InstanceProperties, AmazonDynamoDB)}.
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} replaceAll {@link BiFunction}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBQueryTracker#DynamoDBQueryTracker(InstanceProperties, AmazonDynamoDB)}
   */
  @Test
  @DisplayName("Test new DynamoDBQueryTracker(InstanceProperties, AmazonDynamoDB); given HashMap() replaceAll BiFunction")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBQueryTracker.<init>(InstanceProperties, AmazonDynamoDB)"})
  void testNewDynamoDBQueryTracker_givenHashMapReplaceAllBiFunction2() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.getLong(Mockito.<InstanceProperty>any())).thenReturn(1L);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");

    HashMap<String, AttributeValue> item = new HashMap<>();
    item.replaceAll(mock(BiFunction.class));
    item.put("42", new AttributeValue("foo"));

    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    dynamoDB.putItemAsync("42", new HashMap<>(), "Return Values");
    dynamoDB.putItemAsync(new PutItemRequest(), mock(AsyncHandler.class));
    dynamoDB.putItemAsync("Table Name", item, "42");

    // Act
    new DynamoDBQueryTracker(instanceProperties, dynamoDB);

    // Assert
    verify(instanceProperties).getLong(isA(InstanceProperty.class));
    verify(instanceProperties).get(isA(InstanceProperty.class));
  }

  /**
   * Test {@link DynamoDBQueryTracker#DynamoDBQueryTracker(InstanceProperties, AmazonDynamoDB)}.
   * <ul>
   *   <li>Given one.</li>
   *   <li>When {@link AmazonDynamoDBAsyncClient#AmazonDynamoDBAsyncClient()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBQueryTracker#DynamoDBQueryTracker(InstanceProperties, AmazonDynamoDB)}
   */
  @Test
  @DisplayName("Test new DynamoDBQueryTracker(InstanceProperties, AmazonDynamoDB); given one; when AmazonDynamoDBAsyncClient()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBQueryTracker.<init>(InstanceProperties, AmazonDynamoDB)"})
  void testNewDynamoDBQueryTracker_givenOne_whenAmazonDynamoDBAsyncClient() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.getLong(Mockito.<InstanceProperty>any())).thenReturn(1L);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");

    // Act
    new DynamoDBQueryTracker(instanceProperties, new AmazonDynamoDBAsyncClient());

    // Assert
    verify(instanceProperties).getLong(isA(InstanceProperty.class));
    verify(instanceProperties).get(isA(InstanceProperty.class));
  }

  /**
   * Test {@link DynamoDBQueryTracker#DynamoDBQueryTracker(InstanceProperties, AmazonDynamoDB)}.
   * <ul>
   *   <li>Given {@link PutItemRequest#PutItemRequest()} SdkRequestTimeout is ten.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBQueryTracker#DynamoDBQueryTracker(InstanceProperties, AmazonDynamoDB)}
   */
  @Test
  @DisplayName("Test new DynamoDBQueryTracker(InstanceProperties, AmazonDynamoDB); given PutItemRequest() SdkRequestTimeout is ten")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBQueryTracker.<init>(InstanceProperties, AmazonDynamoDB)"})
  void testNewDynamoDBQueryTracker_givenPutItemRequestSdkRequestTimeoutIsTen() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.getLong(Mockito.<InstanceProperty>any())).thenReturn(1L);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");

    PutItemRequest request = new PutItemRequest();
    request.setSdkRequestTimeout(10);
    request.addExpressionAttributeValuesEntry("42", new AttributeValue("foo"));

    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient(new AnonymousAWSCredentials());
    dynamoDB.putItemAsync(request, mock(AsyncHandler.class));
    dynamoDB.putItemAsync("Table Name", new HashMap<>(), "42");

    // Act
    new DynamoDBQueryTracker(instanceProperties, dynamoDB);

    // Assert
    verify(instanceProperties).getLong(isA(InstanceProperty.class));
    verify(instanceProperties).get(isA(InstanceProperty.class));
  }

  /**
   * Test {@link DynamoDBQueryTracker#DynamoDBQueryTracker(InstanceProperties, AmazonDynamoDB)}.
   * <ul>
   *   <li>Given {@code Table Name}.</li>
   *   <li>When {@link AmazonDynamoDBAsyncClient#AmazonDynamoDBAsyncClient()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBQueryTracker#DynamoDBQueryTracker(InstanceProperties, AmazonDynamoDB)}
   */
  @Test
  @DisplayName("Test new DynamoDBQueryTracker(InstanceProperties, AmazonDynamoDB); given 'Table Name'; when AmazonDynamoDBAsyncClient()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBQueryTracker.<init>(InstanceProperties, AmazonDynamoDB)"})
  void testNewDynamoDBQueryTracker_givenTableName_whenAmazonDynamoDBAsyncClient() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.getLong(Mockito.<InstanceProperty>any())).thenReturn(1L);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");

    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    dynamoDB.putItemAsync("Table Name", new HashMap<>(), "42");

    // Act
    new DynamoDBQueryTracker(instanceProperties, dynamoDB);

    // Assert
    verify(instanceProperties).getLong(isA(InstanceProperty.class));
    verify(instanceProperties).get(isA(InstanceProperty.class));
  }

  /**
   * Test {@link DynamoDBQueryTracker#DynamoDBQueryTracker(InstanceProperties, AmazonDynamoDB)}.
   * <ul>
   *   <li>Given zero.</li>
   *   <li>When {@link InstanceProperties} {@link SleeperPropertyValues#getLong(SleeperProperty)} return zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBQueryTracker#DynamoDBQueryTracker(InstanceProperties, AmazonDynamoDB)}
   */
  @Test
  @DisplayName("Test new DynamoDBQueryTracker(InstanceProperties, AmazonDynamoDB); given zero; when InstanceProperties getLong(SleeperProperty) return zero")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBQueryTracker.<init>(InstanceProperties, AmazonDynamoDB)"})
  void testNewDynamoDBQueryTracker_givenZero_whenInstancePropertiesGetLongReturnZero() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.getLong(Mockito.<InstanceProperty>any())).thenReturn(0L);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");

    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient(new AnonymousAWSCredentials());
    dynamoDB.putItemAsync("42", new HashMap<>(), "Return Values", mock(AsyncHandler.class));
    dynamoDB.putItemAsync(new PutItemRequest());

    // Act
    new DynamoDBQueryTracker(instanceProperties, dynamoDB);

    // Assert
    verify(instanceProperties).getLong(isA(InstanceProperty.class));
    verify(instanceProperties).get(isA(InstanceProperty.class));
  }
}
