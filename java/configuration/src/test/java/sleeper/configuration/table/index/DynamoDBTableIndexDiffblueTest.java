package sleeper.configuration.table.index;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.amazonaws.auth.AnonymousAWSCredentials;
import com.amazonaws.auth.BasicSessionCredentials;
import com.amazonaws.handlers.AsyncHandler;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClient;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.HashMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.properties.SleeperPropertyValues;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.instance.InstanceProperty;

class DynamoDBTableIndexDiffblueTest {
  /**
   * Test {@link DynamoDBTableIndex#DynamoDBTableIndex(InstanceProperties, AmazonDynamoDB)}.
   * <p>
   * Method under test: {@link DynamoDBTableIndex#DynamoDBTableIndex(InstanceProperties, AmazonDynamoDB)}
   */
  @Test
  @DisplayName("Test new DynamoDBTableIndex(InstanceProperties, AmazonDynamoDB)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBTableIndex.<init>(InstanceProperties, AmazonDynamoDB)"})
  void testNewDynamoDBTableIndex() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.getBoolean(Mockito.<InstanceProperty>any())).thenReturn(true);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");

    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    dynamoDB.putItemAsync("Table Name", new HashMap<>(), mock(AsyncHandler.class));

    // Act
    new DynamoDBTableIndex(instanceProperties, dynamoDB);

    // Assert
    verify(instanceProperties).getBoolean(isA(InstanceProperty.class));
    verify(instanceProperties, atLeast(1)).get(Mockito.<InstanceProperty>any());
  }

  /**
   * Test {@link DynamoDBTableIndex#DynamoDBTableIndex(InstanceProperties, AmazonDynamoDB)}.
   * <p>
   * Method under test: {@link DynamoDBTableIndex#DynamoDBTableIndex(InstanceProperties, AmazonDynamoDB)}
   */
  @Test
  @DisplayName("Test new DynamoDBTableIndex(InstanceProperties, AmazonDynamoDB)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBTableIndex.<init>(InstanceProperties, AmazonDynamoDB)"})
  void testNewDynamoDBTableIndex2() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.getBoolean(Mockito.<InstanceProperty>any())).thenReturn(true);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");

    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    dynamoDB.putItemAsync("", new HashMap<>());
    dynamoDB.putItemAsync("Table Name", new HashMap<>());

    // Act
    new DynamoDBTableIndex(instanceProperties, dynamoDB);

    // Assert
    verify(instanceProperties).getBoolean(isA(InstanceProperty.class));
    verify(instanceProperties, atLeast(1)).get(Mockito.<InstanceProperty>any());
  }

  /**
   * Test {@link DynamoDBTableIndex#DynamoDBTableIndex(InstanceProperties, AmazonDynamoDB)}.
   * <p>
   * Method under test: {@link DynamoDBTableIndex#DynamoDBTableIndex(InstanceProperties, AmazonDynamoDB)}
   */
  @Test
  @DisplayName("Test new DynamoDBTableIndex(InstanceProperties, AmazonDynamoDB)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBTableIndex.<init>(InstanceProperties, AmazonDynamoDB)"})
  void testNewDynamoDBTableIndex3() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.getBoolean(Mockito.<InstanceProperty>any())).thenReturn(true);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");

    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    dynamoDB.putItemAsync("", new HashMap<>(), mock(AsyncHandler.class));
    dynamoDB.putItemAsync("Table Name", new HashMap<>());

    // Act
    new DynamoDBTableIndex(instanceProperties, dynamoDB);

    // Assert
    verify(instanceProperties).getBoolean(isA(InstanceProperty.class));
    verify(instanceProperties, atLeast(1)).get(Mockito.<InstanceProperty>any());
  }

  /**
   * Test {@link DynamoDBTableIndex#DynamoDBTableIndex(InstanceProperties, AmazonDynamoDB)}.
   * <p>
   * Method under test: {@link DynamoDBTableIndex#DynamoDBTableIndex(InstanceProperties, AmazonDynamoDB)}
   */
  @Test
  @DisplayName("Test new DynamoDBTableIndex(InstanceProperties, AmazonDynamoDB)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBTableIndex.<init>(InstanceProperties, AmazonDynamoDB)"})
  void testNewDynamoDBTableIndex4() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.getBoolean(Mockito.<InstanceProperty>any())).thenReturn(true);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");

    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient(new AnonymousAWSCredentials());
    dynamoDB.putItemAsync(new PutItemRequest());
    dynamoDB.putItemAsync("Table Name", new HashMap<>());

    // Act
    new DynamoDBTableIndex(instanceProperties, dynamoDB);

    // Assert
    verify(instanceProperties).getBoolean(isA(InstanceProperty.class));
    verify(instanceProperties, atLeast(1)).get(Mockito.<InstanceProperty>any());
  }

  /**
   * Test {@link DynamoDBTableIndex#DynamoDBTableIndex(InstanceProperties, AmazonDynamoDB)}.
   * <p>
   * Method under test: {@link DynamoDBTableIndex#DynamoDBTableIndex(InstanceProperties, AmazonDynamoDB)}
   */
  @Test
  @DisplayName("Test new DynamoDBTableIndex(InstanceProperties, AmazonDynamoDB)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBTableIndex.<init>(InstanceProperties, AmazonDynamoDB)"})
  void testNewDynamoDBTableIndex5() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.getBoolean(Mockito.<InstanceProperty>any())).thenReturn(true);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");

    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient(new AnonymousAWSCredentials());
    dynamoDB.putItemAsync("", new HashMap<>());
    dynamoDB.putItemAsync("Table Name", new HashMap<>());

    // Act
    new DynamoDBTableIndex(instanceProperties, dynamoDB);

    // Assert
    verify(instanceProperties).getBoolean(isA(InstanceProperty.class));
    verify(instanceProperties, atLeast(1)).get(Mockito.<InstanceProperty>any());
  }

  /**
   * Test {@link DynamoDBTableIndex#DynamoDBTableIndex(InstanceProperties, AmazonDynamoDB)}.
   * <p>
   * Method under test: {@link DynamoDBTableIndex#DynamoDBTableIndex(InstanceProperties, AmazonDynamoDB)}
   */
  @Test
  @DisplayName("Test new DynamoDBTableIndex(InstanceProperties, AmazonDynamoDB)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBTableIndex.<init>(InstanceProperties, AmazonDynamoDB)"})
  void testNewDynamoDBTableIndex6() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.getBoolean(Mockito.<InstanceProperty>any())).thenReturn(true);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");

    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient(
        new BasicSessionCredentials("EXAMPLEakiAIOSFODNN7", "EXAMPLEKEYwjalrXUtnFEMI/K7MDENG/bPxRfiCY", "ABC123"));
    dynamoDB.putItemAsync(new PutItemRequest());
    dynamoDB.putItemAsync("Table Name", new HashMap<>());

    // Act
    new DynamoDBTableIndex(instanceProperties, dynamoDB);

    // Assert
    verify(instanceProperties).getBoolean(isA(InstanceProperty.class));
    verify(instanceProperties, atLeast(1)).get(Mockito.<InstanceProperty>any());
  }

  /**
   * Test {@link DynamoDBTableIndex#DynamoDBTableIndex(InstanceProperties, AmazonDynamoDB)}.
   * <p>
   * Method under test: {@link DynamoDBTableIndex#DynamoDBTableIndex(InstanceProperties, AmazonDynamoDB)}
   */
  @Test
  @DisplayName("Test new DynamoDBTableIndex(InstanceProperties, AmazonDynamoDB)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBTableIndex.<init>(InstanceProperties, AmazonDynamoDB)"})
  void testNewDynamoDBTableIndex7() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.getBoolean(Mockito.<InstanceProperty>any())).thenReturn(true);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");

    AttributeValue attributeValue = new AttributeValue("foo");
    attributeValue.setNS(new ArrayList<>());

    HashMap<String, AttributeValue> item = new HashMap<>();
    item.putIfAbsent("42", attributeValue);
    PutItemRequest request = mock(PutItemRequest.class);
    when(request.getRequestMetricCollector()).thenReturn(null);

    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    dynamoDB.putItemAsync("", new HashMap<>());
    dynamoDB.putItemAsync(request);
    dynamoDB.putItemAsync("Table Name", item);

    // Act
    new DynamoDBTableIndex(instanceProperties, dynamoDB);

    // Assert
    verify(request).getRequestMetricCollector();
    verify(instanceProperties).getBoolean(isA(InstanceProperty.class));
    verify(instanceProperties, atLeast(1)).get(Mockito.<InstanceProperty>any());
  }

  /**
   * Test {@link DynamoDBTableIndex#DynamoDBTableIndex(InstanceProperties, AmazonDynamoDB)}.
   * <ul>
   *   <li>Given {@link AttributeValue#AttributeValue(String)} with s is {@code foo} NS is {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBTableIndex#DynamoDBTableIndex(InstanceProperties, AmazonDynamoDB)}
   */
  @Test
  @DisplayName("Test new DynamoDBTableIndex(InstanceProperties, AmazonDynamoDB); given AttributeValue(String) with s is 'foo' NS is ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBTableIndex.<init>(InstanceProperties, AmazonDynamoDB)"})
  void testNewDynamoDBTableIndex_givenAttributeValueWithSIsFooNsIsArrayList() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.getBoolean(Mockito.<InstanceProperty>any())).thenReturn(true);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");

    AttributeValue attributeValue = new AttributeValue("foo");
    attributeValue.setNS(new ArrayList<>());

    HashMap<String, AttributeValue> item = new HashMap<>();
    item.putIfAbsent("42", attributeValue);
    PutItemRequest request = mock(PutItemRequest.class);
    when(request.getRequestMetricCollector()).thenReturn(null);

    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    dynamoDB.putItemAsync(request);
    dynamoDB.putItemAsync("Table Name", item);

    // Act
    new DynamoDBTableIndex(instanceProperties, dynamoDB);

    // Assert
    verify(request).getRequestMetricCollector();
    verify(instanceProperties).getBoolean(isA(InstanceProperty.class));
    verify(instanceProperties, atLeast(1)).get(Mockito.<InstanceProperty>any());
  }

  /**
   * Test {@link DynamoDBTableIndex#DynamoDBTableIndex(InstanceProperties, AmazonDynamoDB)}.
   * <ul>
   *   <li>Given {@link HashMap#HashMap()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBTableIndex#DynamoDBTableIndex(InstanceProperties, AmazonDynamoDB)}
   */
  @Test
  @DisplayName("Test new DynamoDBTableIndex(InstanceProperties, AmazonDynamoDB); given HashMap()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBTableIndex.<init>(InstanceProperties, AmazonDynamoDB)"})
  void testNewDynamoDBTableIndex_givenHashMap() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.getBoolean(Mockito.<InstanceProperty>any())).thenReturn(true);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");

    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    dynamoDB.putItemAsync("Table Name", new HashMap<>());

    // Act
    new DynamoDBTableIndex(instanceProperties, dynamoDB);

    // Assert
    verify(instanceProperties).getBoolean(isA(InstanceProperty.class));
    verify(instanceProperties, atLeast(1)).get(Mockito.<InstanceProperty>any());
  }

  /**
   * Test {@link DynamoDBTableIndex#DynamoDBTableIndex(InstanceProperties, AmazonDynamoDB)}.
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@code 42} is {@link AttributeValue#AttributeValue(String)} with s is {@code foo}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBTableIndex#DynamoDBTableIndex(InstanceProperties, AmazonDynamoDB)}
   */
  @Test
  @DisplayName("Test new DynamoDBTableIndex(InstanceProperties, AmazonDynamoDB); given HashMap() '42' is AttributeValue(String) with s is 'foo'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBTableIndex.<init>(InstanceProperties, AmazonDynamoDB)"})
  void testNewDynamoDBTableIndex_givenHashMap42IsAttributeValueWithSIsFoo() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.getBoolean(Mockito.<InstanceProperty>any())).thenReturn(true);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");

    HashMap<String, AttributeValue> item = new HashMap<>();
    item.put("42", new AttributeValue("foo"));

    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient(new AnonymousAWSCredentials());
    dynamoDB.putItemAsync("", item);
    dynamoDB.putItemAsync("Table Name", new HashMap<>());

    // Act
    new DynamoDBTableIndex(instanceProperties, dynamoDB);

    // Assert
    verify(instanceProperties).getBoolean(isA(InstanceProperty.class));
    verify(instanceProperties, atLeast(1)).get(Mockito.<InstanceProperty>any());
  }

  /**
   * Test {@link DynamoDBTableIndex#DynamoDBTableIndex(InstanceProperties, AmazonDynamoDB)}.
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} empty string is {@link AttributeValue#AttributeValue(String)} with s is {@code foo}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBTableIndex#DynamoDBTableIndex(InstanceProperties, AmazonDynamoDB)}
   */
  @Test
  @DisplayName("Test new DynamoDBTableIndex(InstanceProperties, AmazonDynamoDB); given HashMap() empty string is AttributeValue(String) with s is 'foo'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBTableIndex.<init>(InstanceProperties, AmazonDynamoDB)"})
  void testNewDynamoDBTableIndex_givenHashMapEmptyStringIsAttributeValueWithSIsFoo() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.getBoolean(Mockito.<InstanceProperty>any())).thenReturn(true);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");

    HashMap<String, AttributeValue> item = new HashMap<>();
    item.put("", new AttributeValue("foo"));
    item.putIfAbsent("42", new AttributeValue("foo"));

    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    dynamoDB.putItemAsync(new PutItemRequest());
    dynamoDB.putItemAsync("Table Name", item);

    // Act
    new DynamoDBTableIndex(instanceProperties, dynamoDB);

    // Assert
    verify(instanceProperties).getBoolean(isA(InstanceProperty.class));
    verify(instanceProperties, atLeast(1)).get(Mockito.<InstanceProperty>any());
  }

  /**
   * Test {@link DynamoDBTableIndex#DynamoDBTableIndex(InstanceProperties, AmazonDynamoDB)}.
   * <ul>
   *   <li>When {@link AmazonDynamoDBAsyncClient#AmazonDynamoDBAsyncClient()} ItemAsync is {@link PutItemRequest#PutItemRequest()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBTableIndex#DynamoDBTableIndex(InstanceProperties, AmazonDynamoDB)}
   */
  @Test
  @DisplayName("Test new DynamoDBTableIndex(InstanceProperties, AmazonDynamoDB); when AmazonDynamoDBAsyncClient() ItemAsync is PutItemRequest()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBTableIndex.<init>(InstanceProperties, AmazonDynamoDB)"})
  void testNewDynamoDBTableIndex_whenAmazonDynamoDBAsyncClientItemAsyncIsPutItemRequest() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.getBoolean(Mockito.<InstanceProperty>any())).thenReturn(true);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");

    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    dynamoDB.putItemAsync(new PutItemRequest());
    dynamoDB.putItemAsync("Table Name", new HashMap<>());

    // Act
    new DynamoDBTableIndex(instanceProperties, dynamoDB);

    // Assert
    verify(instanceProperties).getBoolean(isA(InstanceProperty.class));
    verify(instanceProperties, atLeast(1)).get(Mockito.<InstanceProperty>any());
  }

  /**
   * Test {@link DynamoDBTableIndex#DynamoDBTableIndex(InstanceProperties, AmazonDynamoDB)}.
   * <ul>
   *   <li>When {@link AmazonDynamoDBAsyncClient#AmazonDynamoDBAsyncClient()} ItemAsync is {@link PutItemRequest#PutItemRequest()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBTableIndex#DynamoDBTableIndex(InstanceProperties, AmazonDynamoDB)}
   */
  @Test
  @DisplayName("Test new DynamoDBTableIndex(InstanceProperties, AmazonDynamoDB); when AmazonDynamoDBAsyncClient() ItemAsync is PutItemRequest()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBTableIndex.<init>(InstanceProperties, AmazonDynamoDB)"})
  void testNewDynamoDBTableIndex_whenAmazonDynamoDBAsyncClientItemAsyncIsPutItemRequest2() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.getBoolean(Mockito.<InstanceProperty>any())).thenReturn(true);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");

    HashMap<String, AttributeValue> item = new HashMap<>();
    item.putIfAbsent("42", new AttributeValue("foo"));

    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    dynamoDB.putItemAsync(new PutItemRequest());
    dynamoDB.putItemAsync("Table Name", item);

    // Act
    new DynamoDBTableIndex(instanceProperties, dynamoDB);

    // Assert
    verify(instanceProperties).getBoolean(isA(InstanceProperty.class));
    verify(instanceProperties, atLeast(1)).get(Mockito.<InstanceProperty>any());
  }

  /**
   * Test {@link DynamoDBTableIndex#DynamoDBTableIndex(InstanceProperties, AmazonDynamoDB)}.
   * <ul>
   *   <li>When {@link AmazonDynamoDBAsyncClient#AmazonDynamoDBAsyncClient()}.</li>
   *   <li>Then calls {@link SleeperPropertyValues#getBoolean(SleeperProperty)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBTableIndex#DynamoDBTableIndex(InstanceProperties, AmazonDynamoDB)}
   */
  @Test
  @DisplayName("Test new DynamoDBTableIndex(InstanceProperties, AmazonDynamoDB); when AmazonDynamoDBAsyncClient(); then calls getBoolean(SleeperProperty)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBTableIndex.<init>(InstanceProperties, AmazonDynamoDB)"})
  void testNewDynamoDBTableIndex_whenAmazonDynamoDBAsyncClient_thenCallsGetBoolean() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.getBoolean(Mockito.<InstanceProperty>any())).thenReturn(true);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");

    // Act
    new DynamoDBTableIndex(instanceProperties, new AmazonDynamoDBAsyncClient());

    // Assert
    verify(instanceProperties).getBoolean(isA(InstanceProperty.class));
    verify(instanceProperties, atLeast(1)).get(Mockito.<InstanceProperty>any());
  }
}
