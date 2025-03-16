package sleeper.ingest.batcher.store;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.amazonaws.PredefinedClientConfigurations;
import com.amazonaws.auth.AnonymousAWSCredentials;
import com.amazonaws.handlers.AsyncHandler;
import com.amazonaws.metrics.RequestMetricCollector;
import com.amazonaws.monitoring.internal.ClientSideMonitoringRequestHandler;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClient;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;
import com.amazonaws.services.dynamodbv2.model.PutItemResult;
import com.amazonaws.services.dynamodbv2.model.TransactionCanceledException;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.BiFunction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.instance.InstanceProperty;
import sleeper.core.properties.table.TablePropertiesProvider;
import sleeper.core.properties.table.TablePropertiesStore;
import sleeper.core.properties.table.TablePropertiesStore.Client;
import sleeper.core.table.InMemoryTableIndex;
import sleeper.ingest.batcher.core.FileIngestRequest;
import sleeper.ingest.batcher.core.FileIngestRequest.Builder;

class DynamoDBIngestBatcherStoreDiffblueTest {
  /**
   * Test {@link DynamoDBIngestBatcherStore#DynamoDBIngestBatcherStore(AmazonDynamoDB, InstanceProperties, TablePropertiesProvider)}.
   * <p>
   * Method under test: {@link DynamoDBIngestBatcherStore#DynamoDBIngestBatcherStore(AmazonDynamoDB, InstanceProperties, TablePropertiesProvider)}
   */
  @Test
  @DisplayName("Test new DynamoDBIngestBatcherStore(AmazonDynamoDB, InstanceProperties, TablePropertiesProvider)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void DynamoDBIngestBatcherStore.<init>(AmazonDynamoDB, InstanceProperties, TablePropertiesProvider)"})
  void testNewDynamoDBIngestBatcherStore() {
    // Arrange
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient(new AnonymousAWSCredentials());
    dynamoDB.putItemAsync(new PutItemRequest(), mock(AsyncHandler.class));
    dynamoDB.addRequestHandler(new ClientSideMonitoringRequestHandler("42", new ArrayList<>()));
    dynamoDB.putItemAsync("Table Name", new HashMap<>(), mock(AsyncHandler.class));
    dynamoDB.putItemAsync(new PutItemRequest());
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    InstanceProperties instanceProperties2 = new InstanceProperties();

    // Act
    new DynamoDBIngestBatcherStore(dynamoDB, instanceProperties, new TablePropertiesProvider(instanceProperties2,
        new TablePropertiesStore(new InMemoryTableIndex(), mock(Client.class))));

    // Assert
    verify(instanceProperties).get(isA(InstanceProperty.class));
  }

  /**
   * Test {@link DynamoDBIngestBatcherStore#DynamoDBIngestBatcherStore(AmazonDynamoDB, InstanceProperties, TablePropertiesProvider)}.
   * <p>
   * Method under test: {@link DynamoDBIngestBatcherStore#DynamoDBIngestBatcherStore(AmazonDynamoDB, InstanceProperties, TablePropertiesProvider)}
   */
  @Test
  @DisplayName("Test new DynamoDBIngestBatcherStore(AmazonDynamoDB, InstanceProperties, TablePropertiesProvider)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void DynamoDBIngestBatcherStore.<init>(AmazonDynamoDB, InstanceProperties, TablePropertiesProvider)"})
  void testNewDynamoDBIngestBatcherStore2() {
    // Arrange
    HashMap<String, AttributeValue> item = new HashMap<>();
    AsyncHandler<PutItemRequest, PutItemResult> asyncHandler = mock(AsyncHandler.class);
    doNothing().when(asyncHandler).onError(Mockito.<Exception>any());

    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    dynamoDB.putItemAsync("", item, asyncHandler);

    PutItemRequest request = new PutItemRequest();
    request.setExpressionAttributeValues(new HashMap<>());
    AsyncHandler<PutItemRequest, PutItemResult> asyncHandler2 = mock(AsyncHandler.class);
    doThrow(new TransactionCanceledException("An error occurred")).when(asyncHandler2)
        .onError(Mockito.<Exception>any());
    dynamoDB.putItemAsync(request, asyncHandler2);
    dynamoDB.addRequestHandler(new ClientSideMonitoringRequestHandler("42", new ArrayList<>()));
    HashMap<String, AttributeValue> item2 = new HashMap<>();
    AsyncHandler<PutItemRequest, PutItemResult> asyncHandler3 = mock(AsyncHandler.class);
    doNothing().when(asyncHandler3).onError(Mockito.<Exception>any());
    dynamoDB.putItemAsync("Table Name", item2, asyncHandler3);
    PutItemRequest request2 = mock(PutItemRequest.class);
    when(request2.getRequestMetricCollector()).thenReturn(null);
    dynamoDB.putItemAsync(request2);
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any()))
        .thenThrow(new RuntimeException("ingest-batcher-store"));
    TablePropertiesStore propertiesStore = new TablePropertiesStore(new InMemoryTableIndex(), mock(Client.class));

    // Act and Assert
    assertThrows(RuntimeException.class, () -> new DynamoDBIngestBatcherStore(dynamoDB, instanceProperties,
        new TablePropertiesProvider(new InstanceProperties(), propertiesStore)));

    verify(request2).getRequestMetricCollector();
    verify(asyncHandler).onError(isA(Exception.class));
    verify(instanceProperties).get(isA(InstanceProperty.class));
  }

  /**
   * Test {@link DynamoDBIngestBatcherStore#DynamoDBIngestBatcherStore(AmazonDynamoDB, InstanceProperties, TablePropertiesProvider, int)}.
   * <p>
   * Method under test: {@link DynamoDBIngestBatcherStore#DynamoDBIngestBatcherStore(AmazonDynamoDB, InstanceProperties, TablePropertiesProvider, int)}
   */
  @Test
  @DisplayName("Test new DynamoDBIngestBatcherStore(AmazonDynamoDB, InstanceProperties, TablePropertiesProvider, int)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void DynamoDBIngestBatcherStore.<init>(AmazonDynamoDB, InstanceProperties, TablePropertiesProvider, int)"})
  void testNewDynamoDBIngestBatcherStore3() {
    // Arrange
    AttributeValue attributeValue = new AttributeValue("ingest-batcher-store");
    AttributeValue attributeValue2 = new AttributeValue("ingest-batcher-store");

    HashMap<String, AttributeValue> item = new HashMap<>();
    item.replace("ingest-batcher-store", attributeValue, attributeValue2);
    AsyncHandler<PutItemRequest, PutItemResult> asyncHandler = mock(AsyncHandler.class);
    doThrow(new TransactionCanceledException("An error occurred")).when(asyncHandler).onError(Mockito.<Exception>any());

    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    dynamoDB.putItemAsync("Table Name", item, "ingest-batcher-store", asyncHandler);
    HashMap<String, AttributeValue> item2 = new HashMap<>();
    AsyncHandler<PutItemRequest, PutItemResult> asyncHandler2 = mock(AsyncHandler.class);
    doThrow(new TransactionCanceledException("An error occurred")).when(asyncHandler2)
        .onError(Mockito.<Exception>any());
    dynamoDB.putItemAsync("ingest-batcher-store", item2, asyncHandler2);
    RequestMetricCollector requestMetricCollector = mock(RequestMetricCollector.class);
    when(requestMetricCollector.isEnabled()).thenThrow(new TransactionCanceledException("An error occurred"));

    PutItemRequest request = new PutItemRequest();
    request.setRequestMetricCollector(requestMetricCollector);
    request.putCustomRequestHeader("ingest-batcher-store", "42");
    AsyncHandler<PutItemRequest, PutItemResult> asyncHandler3 = mock(AsyncHandler.class);
    doThrow(new TransactionCanceledException("An error occurred")).when(asyncHandler3)
        .onError(Mockito.<Exception>any());
    dynamoDB.putItemAsync(request, asyncHandler3);
    dynamoDB.putItemAsync("Table Name", new HashMap<>(), "ingest-batcher-store");
    HashMap<String, AttributeValue> item3 = new HashMap<>();
    AsyncHandler<PutItemRequest, PutItemResult> asyncHandler4 = mock(AsyncHandler.class);
    doNothing().when(asyncHandler4).onError(Mockito.<Exception>any());
    dynamoDB.putItemAsync("ingest-batcher-store", item3, asyncHandler4);
    TablePropertiesStore propertiesStore = new TablePropertiesStore(new InMemoryTableIndex(), mock(Client.class));

    TablePropertiesProvider tablePropertiesProvider = new TablePropertiesProvider(new InstanceProperties(),
        propertiesStore);

    // Act
    new DynamoDBIngestBatcherStore(dynamoDB, new InstanceProperties(), tablePropertiesProvider, 1);

    // Assert
    verify(asyncHandler).onError(isA(Exception.class));
    verify(asyncHandler2).onError(isA(Exception.class));
    verify(asyncHandler3).onError(isA(Exception.class));
    verify(requestMetricCollector).isEnabled();
  }

  /**
   * Test {@link DynamoDBIngestBatcherStore#ingestRequestsTableName(String)}.
   * <p>
   * Method under test: {@link DynamoDBIngestBatcherStore#ingestRequestsTableName(String)}
   */
  @Test
  @DisplayName("Test ingestRequestsTableName(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String DynamoDBIngestBatcherStore.ingestRequestsTableName(String)"})
  void testIngestRequestsTableName() {
    // Arrange, Act and Assert
    assertEquals("sleeper-42-ingest-batcher-store", DynamoDBIngestBatcherStore.ingestRequestsTableName("42"));
  }

  /**
   * Test {@link DynamoDBIngestBatcherStore#assignJobGetAssigned(String, List)}.
   * <p>
   * Method under test: {@link DynamoDBIngestBatcherStore#assignJobGetAssigned(String, List)}
   */
  @Test
  @DisplayName("Test assignJobGetAssigned(String, List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List DynamoDBIngestBatcherStore.assignJobGetAssigned(String, List)"})
  void testAssignJobGetAssigned() {
    // Arrange
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    InstanceProperties instanceProperties = new InstanceProperties();
    InstanceProperties instanceProperties2 = new InstanceProperties();
    DynamoDBIngestBatcherStore dynamoDBIngestBatcherStore = new DynamoDBIngestBatcherStore(dynamoDB, instanceProperties,
        new TablePropertiesProvider(instanceProperties2,
            new TablePropertiesStore(new InMemoryTableIndex(), mock(Client.class))));

    // Act and Assert
    assertTrue(dynamoDBIngestBatcherStore.assignJobGetAssigned("42", new ArrayList<>()).isEmpty());
  }

  /**
   * Test {@link DynamoDBIngestBatcherStore#assignJobGetAssigned(String, List)}.
   * <p>
   * Method under test: {@link DynamoDBIngestBatcherStore#assignJobGetAssigned(String, List)}
   */
  @Test
  @DisplayName("Test assignJobGetAssigned(String, List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List DynamoDBIngestBatcherStore.assignJobGetAssigned(String, List)"})
  void testAssignJobGetAssigned2() {
    // Arrange
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    dynamoDB.putItemAsync("Table Name", new HashMap<>(), mock(AsyncHandler.class));
    InstanceProperties instanceProperties = new InstanceProperties();
    InstanceProperties instanceProperties2 = new InstanceProperties();
    DynamoDBIngestBatcherStore dynamoDBIngestBatcherStore = new DynamoDBIngestBatcherStore(dynamoDB, instanceProperties,
        new TablePropertiesProvider(instanceProperties2,
            new TablePropertiesStore(new InMemoryTableIndex(), mock(Client.class))));

    // Act and Assert
    assertTrue(dynamoDBIngestBatcherStore.assignJobGetAssigned("42", new ArrayList<>()).isEmpty());
  }

  /**
   * Test {@link DynamoDBIngestBatcherStore#assignJobGetAssigned(String, List)}.
   * <p>
   * Method under test: {@link DynamoDBIngestBatcherStore#assignJobGetAssigned(String, List)}
   */
  @Test
  @DisplayName("Test assignJobGetAssigned(String, List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List DynamoDBIngestBatcherStore.assignJobGetAssigned(String, List)"})
  void testAssignJobGetAssigned3() {
    // Arrange
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    dynamoDB.putItemAsync("Table Name", new HashMap<>(), "42", mock(AsyncHandler.class));
    InstanceProperties instanceProperties = new InstanceProperties();
    InstanceProperties instanceProperties2 = new InstanceProperties();
    DynamoDBIngestBatcherStore dynamoDBIngestBatcherStore = new DynamoDBIngestBatcherStore(dynamoDB, instanceProperties,
        new TablePropertiesProvider(instanceProperties2,
            new TablePropertiesStore(new InMemoryTableIndex(), mock(Client.class))));

    // Act and Assert
    assertTrue(dynamoDBIngestBatcherStore.assignJobGetAssigned("42", new ArrayList<>()).isEmpty());
  }

  /**
   * Test {@link DynamoDBIngestBatcherStore#assignJobGetAssigned(String, List)}.
   * <p>
   * Method under test: {@link DynamoDBIngestBatcherStore#assignJobGetAssigned(String, List)}
   */
  @Test
  @DisplayName("Test assignJobGetAssigned(String, List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List DynamoDBIngestBatcherStore.assignJobGetAssigned(String, List)"})
  void testAssignJobGetAssigned4() {
    // Arrange
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient(new AnonymousAWSCredentials());
    dynamoDB.putItemAsync("Table Name", new HashMap<>(), mock(AsyncHandler.class));
    InstanceProperties instanceProperties = new InstanceProperties();
    InstanceProperties instanceProperties2 = new InstanceProperties();
    DynamoDBIngestBatcherStore dynamoDBIngestBatcherStore = new DynamoDBIngestBatcherStore(dynamoDB, instanceProperties,
        new TablePropertiesProvider(instanceProperties2,
            new TablePropertiesStore(new InMemoryTableIndex(), mock(Client.class))));

    // Act and Assert
    assertTrue(dynamoDBIngestBatcherStore.assignJobGetAssigned("42", new ArrayList<>()).isEmpty());
  }

  /**
   * Test {@link DynamoDBIngestBatcherStore#assignJobGetAssigned(String, List)}.
   * <p>
   * Method under test: {@link DynamoDBIngestBatcherStore#assignJobGetAssigned(String, List)}
   */
  @Test
  @DisplayName("Test assignJobGetAssigned(String, List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List DynamoDBIngestBatcherStore.assignJobGetAssigned(String, List)"})
  void testAssignJobGetAssigned5() {
    // Arrange
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    dynamoDB.putItemAsync("", new HashMap<>(), "", mock(AsyncHandler.class));
    dynamoDB.putItemAsync("Table Name", new HashMap<>(), "42", mock(AsyncHandler.class));
    InstanceProperties instanceProperties = new InstanceProperties();
    InstanceProperties instanceProperties2 = new InstanceProperties();
    DynamoDBIngestBatcherStore dynamoDBIngestBatcherStore = new DynamoDBIngestBatcherStore(dynamoDB, instanceProperties,
        new TablePropertiesProvider(instanceProperties2,
            new TablePropertiesStore(new InMemoryTableIndex(), mock(Client.class))));

    // Act and Assert
    assertTrue(dynamoDBIngestBatcherStore.assignJobGetAssigned("42", new ArrayList<>()).isEmpty());
  }

  /**
   * Test {@link DynamoDBIngestBatcherStore#assignJobGetAssigned(String, List)}.
   * <p>
   * Method under test: {@link DynamoDBIngestBatcherStore#assignJobGetAssigned(String, List)}
   */
  @Test
  @DisplayName("Test assignJobGetAssigned(String, List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List DynamoDBIngestBatcherStore.assignJobGetAssigned(String, List)"})
  void testAssignJobGetAssigned6() {
    // Arrange
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    dynamoDB.putItemAsync("42", new HashMap<>(), "42", mock(AsyncHandler.class));
    InstanceProperties instanceProperties = new InstanceProperties();
    InstanceProperties instanceProperties2 = new InstanceProperties();
    DynamoDBIngestBatcherStore dynamoDBIngestBatcherStore = new DynamoDBIngestBatcherStore(dynamoDB, instanceProperties,
        new TablePropertiesProvider(instanceProperties2,
            new TablePropertiesStore(new InMemoryTableIndex(), mock(Client.class))));

    // Act and Assert
    assertTrue(dynamoDBIngestBatcherStore.assignJobGetAssigned("42", new ArrayList<>()).isEmpty());
  }

  /**
   * Test {@link DynamoDBIngestBatcherStore#assignJobGetAssigned(String, List)}.
   * <p>
   * Method under test: {@link DynamoDBIngestBatcherStore#assignJobGetAssigned(String, List)}
   */
  @Test
  @DisplayName("Test assignJobGetAssigned(String, List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List DynamoDBIngestBatcherStore.assignJobGetAssigned(String, List)"})
  void testAssignJobGetAssigned7() {
    // Arrange
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient(new AnonymousAWSCredentials());
    dynamoDB.putItemAsync(new PutItemRequest());
    dynamoDB.putItemAsync("Table Name", new HashMap<>(), mock(AsyncHandler.class));
    InstanceProperties instanceProperties = new InstanceProperties();
    InstanceProperties instanceProperties2 = new InstanceProperties();
    DynamoDBIngestBatcherStore dynamoDBIngestBatcherStore = new DynamoDBIngestBatcherStore(dynamoDB, instanceProperties,
        new TablePropertiesProvider(instanceProperties2,
            new TablePropertiesStore(new InMemoryTableIndex(), mock(Client.class))));

    // Act and Assert
    assertTrue(dynamoDBIngestBatcherStore.assignJobGetAssigned("42", new ArrayList<>()).isEmpty());
  }

  /**
   * Test {@link DynamoDBIngestBatcherStore#assignJobGetAssigned(String, List)}.
   * <p>
   * Method under test: {@link DynamoDBIngestBatcherStore#assignJobGetAssigned(String, List)}
   */
  @Test
  @DisplayName("Test assignJobGetAssigned(String, List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List DynamoDBIngestBatcherStore.assignJobGetAssigned(String, List)"})
  void testAssignJobGetAssigned8() {
    // Arrange
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient(PredefinedClientConfigurations.defaultConfig());
    dynamoDB.putItemAsync("", new HashMap<>(), "", mock(AsyncHandler.class));
    dynamoDB.putItemAsync("Table Name", new HashMap<>(), "42", mock(AsyncHandler.class));
    InstanceProperties instanceProperties = new InstanceProperties();
    InstanceProperties instanceProperties2 = new InstanceProperties();
    DynamoDBIngestBatcherStore dynamoDBIngestBatcherStore = new DynamoDBIngestBatcherStore(dynamoDB, instanceProperties,
        new TablePropertiesProvider(instanceProperties2,
            new TablePropertiesStore(new InMemoryTableIndex(), mock(Client.class))));

    // Act and Assert
    assertTrue(dynamoDBIngestBatcherStore.assignJobGetAssigned("42", new ArrayList<>()).isEmpty());
  }

  /**
   * Test {@link DynamoDBIngestBatcherStore#assignJobGetAssigned(String, List)}.
   * <p>
   * Method under test: {@link DynamoDBIngestBatcherStore#assignJobGetAssigned(String, List)}
   */
  @Test
  @DisplayName("Test assignJobGetAssigned(String, List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List DynamoDBIngestBatcherStore.assignJobGetAssigned(String, List)"})
  void testAssignJobGetAssigned9() {
    // Arrange
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient(new AnonymousAWSCredentials());
    dynamoDB.putItemAsync("", new HashMap<>(), "", mock(AsyncHandler.class));
    dynamoDB.putItemAsync("Table Name", new HashMap<>(), "42", mock(AsyncHandler.class));
    InstanceProperties instanceProperties = new InstanceProperties();
    InstanceProperties instanceProperties2 = new InstanceProperties();
    DynamoDBIngestBatcherStore dynamoDBIngestBatcherStore = new DynamoDBIngestBatcherStore(dynamoDB, instanceProperties,
        new TablePropertiesProvider(instanceProperties2,
            new TablePropertiesStore(new InMemoryTableIndex(), mock(Client.class))));

    // Act and Assert
    assertTrue(dynamoDBIngestBatcherStore.assignJobGetAssigned("42", new ArrayList<>()).isEmpty());
  }

  /**
   * Test {@link DynamoDBIngestBatcherStore#assignJobGetAssigned(String, List)}.
   * <p>
   * Method under test: {@link DynamoDBIngestBatcherStore#assignJobGetAssigned(String, List)}
   */
  @Test
  @DisplayName("Test assignJobGetAssigned(String, List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List DynamoDBIngestBatcherStore.assignJobGetAssigned(String, List)"})
  void testAssignJobGetAssigned10() {
    // Arrange
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    InstanceProperties instanceProperties = new InstanceProperties();
    InstanceProperties instanceProperties2 = new InstanceProperties();
    DynamoDBIngestBatcherStore dynamoDBIngestBatcherStore = new DynamoDBIngestBatcherStore(dynamoDB, instanceProperties,
        new TablePropertiesProvider(instanceProperties2,
            new TablePropertiesStore(new InMemoryTableIndex(), mock(Client.class))));

    ArrayList<FileIngestRequest> filesInJob = new ArrayList<>();
    Builder jobIdResult = FileIngestRequest.builder().file("File").fileSizeBytes(3L).jobId("42");
    FileIngestRequest buildResult = jobIdResult
        .receivedTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .tableId("42")
        .build();
    filesInJob.add(buildResult);

    // Act and Assert
    assertTrue(dynamoDBIngestBatcherStore.assignJobGetAssigned("42", filesInJob).isEmpty());
  }

  /**
   * Test {@link DynamoDBIngestBatcherStore#assignJobGetAssigned(String, List)}.
   * <p>
   * Method under test: {@link DynamoDBIngestBatcherStore#assignJobGetAssigned(String, List)}
   */
  @Test
  @DisplayName("Test assignJobGetAssigned(String, List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List DynamoDBIngestBatcherStore.assignJobGetAssigned(String, List)"})
  void testAssignJobGetAssigned11() {
    // Arrange
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    InstanceProperties instanceProperties = new InstanceProperties();
    InstanceProperties instanceProperties2 = new InstanceProperties();
    DynamoDBIngestBatcherStore dynamoDBIngestBatcherStore = new DynamoDBIngestBatcherStore(dynamoDB, instanceProperties,
        new TablePropertiesProvider(instanceProperties2,
            new TablePropertiesStore(new InMemoryTableIndex(), mock(Client.class))));

    ArrayList<FileIngestRequest> filesInJob = new ArrayList<>();
    Builder jobIdResult = FileIngestRequest.builder().file("File").fileSizeBytes(3L).jobId("42");
    FileIngestRequest buildResult = jobIdResult
        .receivedTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .tableId("42")
        .build();
    filesInJob.add(buildResult);
    Builder jobIdResult2 = FileIngestRequest.builder().file("File").fileSizeBytes(3L).jobId("42");
    FileIngestRequest buildResult2 = jobIdResult2
        .receivedTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .tableId("42")
        .build();
    filesInJob.add(buildResult2);

    // Act and Assert
    assertTrue(dynamoDBIngestBatcherStore.assignJobGetAssigned("42", filesInJob).isEmpty());
  }

  /**
   * Test {@link DynamoDBIngestBatcherStore#assignJobGetAssigned(String, List)}.
   * <p>
   * Method under test: {@link DynamoDBIngestBatcherStore#assignJobGetAssigned(String, List)}
   */
  @Test
  @DisplayName("Test assignJobGetAssigned(String, List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List DynamoDBIngestBatcherStore.assignJobGetAssigned(String, List)"})
  void testAssignJobGetAssigned12() {
    // Arrange
    HashMap<String, AttributeValue> item = new HashMap<>();
    item.putIfAbsent("", new AttributeValue("foo"));

    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    dynamoDB.putItemAsync("Table Name", item, mock(AsyncHandler.class));
    InstanceProperties instanceProperties = new InstanceProperties();
    InstanceProperties instanceProperties2 = new InstanceProperties();
    DynamoDBIngestBatcherStore dynamoDBIngestBatcherStore = new DynamoDBIngestBatcherStore(dynamoDB, instanceProperties,
        new TablePropertiesProvider(instanceProperties2,
            new TablePropertiesStore(new InMemoryTableIndex(), mock(Client.class))));

    // Act and Assert
    assertTrue(dynamoDBIngestBatcherStore.assignJobGetAssigned("42", new ArrayList<>()).isEmpty());
  }

  /**
   * Test {@link DynamoDBIngestBatcherStore#assignJobGetAssigned(String, List)}.
   * <p>
   * Method under test: {@link DynamoDBIngestBatcherStore#assignJobGetAssigned(String, List)}
   */
  @Test
  @DisplayName("Test assignJobGetAssigned(String, List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List DynamoDBIngestBatcherStore.assignJobGetAssigned(String, List)"})
  void testAssignJobGetAssigned13() {
    // Arrange
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient(new AnonymousAWSCredentials());
    dynamoDB.putItemAsync("", new HashMap<>());
    dynamoDB.putItemAsync("Table Name", new HashMap<>(), mock(AsyncHandler.class));
    InstanceProperties instanceProperties = new InstanceProperties();
    InstanceProperties instanceProperties2 = new InstanceProperties();
    DynamoDBIngestBatcherStore dynamoDBIngestBatcherStore = new DynamoDBIngestBatcherStore(dynamoDB, instanceProperties,
        new TablePropertiesProvider(instanceProperties2,
            new TablePropertiesStore(new InMemoryTableIndex(), mock(Client.class))));

    // Act and Assert
    assertTrue(dynamoDBIngestBatcherStore.assignJobGetAssigned("42", new ArrayList<>()).isEmpty());
  }

  /**
   * Test {@link DynamoDBIngestBatcherStore#assignJobGetAssigned(String, List)}.
   * <p>
   * Method under test: {@link DynamoDBIngestBatcherStore#assignJobGetAssigned(String, List)}
   */
  @Test
  @DisplayName("Test assignJobGetAssigned(String, List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List DynamoDBIngestBatcherStore.assignJobGetAssigned(String, List)"})
  void testAssignJobGetAssigned14() {
    // Arrange
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient(new AnonymousAWSCredentials());
    dynamoDB.putItemAsync("Table Name", new HashMap<>(), "42", mock(AsyncHandler.class));
    dynamoDB.putItemAsync("", new HashMap<>());
    dynamoDB.putItemAsync("Table Name", new HashMap<>(), mock(AsyncHandler.class));
    InstanceProperties instanceProperties = new InstanceProperties();
    InstanceProperties instanceProperties2 = new InstanceProperties();
    DynamoDBIngestBatcherStore dynamoDBIngestBatcherStore = new DynamoDBIngestBatcherStore(dynamoDB, instanceProperties,
        new TablePropertiesProvider(instanceProperties2,
            new TablePropertiesStore(new InMemoryTableIndex(), mock(Client.class))));

    // Act and Assert
    assertTrue(dynamoDBIngestBatcherStore.assignJobGetAssigned("42", new ArrayList<>()).isEmpty());
  }

  /**
   * Test {@link DynamoDBIngestBatcherStore#assignJobGetAssigned(String, List)}.
   * <p>
   * Method under test: {@link DynamoDBIngestBatcherStore#assignJobGetAssigned(String, List)}
   */
  @Test
  @DisplayName("Test assignJobGetAssigned(String, List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List DynamoDBIngestBatcherStore.assignJobGetAssigned(String, List)"})
  void testAssignJobGetAssigned15() {
    // Arrange
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient(new AnonymousAWSCredentials());
    dynamoDB.putItemAsync("42", new HashMap<>(), mock(AsyncHandler.class));
    dynamoDB.putItemAsync("Table Name", new HashMap<>(), mock(AsyncHandler.class));
    InstanceProperties instanceProperties = new InstanceProperties();
    InstanceProperties instanceProperties2 = new InstanceProperties();
    DynamoDBIngestBatcherStore dynamoDBIngestBatcherStore = new DynamoDBIngestBatcherStore(dynamoDB, instanceProperties,
        new TablePropertiesProvider(instanceProperties2,
            new TablePropertiesStore(new InMemoryTableIndex(), mock(Client.class))));

    // Act and Assert
    assertTrue(dynamoDBIngestBatcherStore.assignJobGetAssigned("42", new ArrayList<>()).isEmpty());
  }

  /**
   * Test {@link DynamoDBIngestBatcherStore#assignJobGetAssigned(String, List)}.
   * <p>
   * Method under test: {@link DynamoDBIngestBatcherStore#assignJobGetAssigned(String, List)}
   */
  @Test
  @DisplayName("Test assignJobGetAssigned(String, List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List DynamoDBIngestBatcherStore.assignJobGetAssigned(String, List)"})
  void testAssignJobGetAssigned16() {
    // Arrange
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    dynamoDB.putItemAsync("Table Name", new HashMap<>(), "42", mock(AsyncHandler.class));
    dynamoDB.putItemAsync("", new HashMap<>());
    dynamoDB.putItemAsync("Table Name", new HashMap<>(), mock(AsyncHandler.class));
    InstanceProperties instanceProperties = new InstanceProperties();
    InstanceProperties instanceProperties2 = new InstanceProperties();
    DynamoDBIngestBatcherStore dynamoDBIngestBatcherStore = new DynamoDBIngestBatcherStore(dynamoDB, instanceProperties,
        new TablePropertiesProvider(instanceProperties2,
            new TablePropertiesStore(new InMemoryTableIndex(), mock(Client.class))));

    // Act and Assert
    assertTrue(dynamoDBIngestBatcherStore.assignJobGetAssigned("42", new ArrayList<>()).isEmpty());
  }

  /**
   * Test {@link DynamoDBIngestBatcherStore#assignJobGetAssigned(String, List)}.
   * <p>
   * Method under test: {@link DynamoDBIngestBatcherStore#assignJobGetAssigned(String, List)}
   */
  @Test
  @DisplayName("Test assignJobGetAssigned(String, List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List DynamoDBIngestBatcherStore.assignJobGetAssigned(String, List)"})
  void testAssignJobGetAssigned17() {
    // Arrange
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient(new AnonymousAWSCredentials());
    dynamoDB.putItemAsync(new PutItemRequest(), mock(AsyncHandler.class));
    dynamoDB.putItemAsync("Table Name", new HashMap<>(), "42", mock(AsyncHandler.class));
    dynamoDB.putItemAsync("", new HashMap<>());
    dynamoDB.putItemAsync("Table Name", new HashMap<>(), mock(AsyncHandler.class));
    InstanceProperties instanceProperties = new InstanceProperties();
    InstanceProperties instanceProperties2 = new InstanceProperties();
    DynamoDBIngestBatcherStore dynamoDBIngestBatcherStore = new DynamoDBIngestBatcherStore(dynamoDB, instanceProperties,
        new TablePropertiesProvider(instanceProperties2,
            new TablePropertiesStore(new InMemoryTableIndex(), mock(Client.class))));

    // Act and Assert
    assertTrue(dynamoDBIngestBatcherStore.assignJobGetAssigned("42", new ArrayList<>()).isEmpty());
  }

  /**
   * Test {@link DynamoDBIngestBatcherStore#assignJobGetAssigned(String, List)}.
   * <ul>
   *   <li>Given {@link AmazonDynamoDBAsyncClient#AmazonDynamoDBAsyncClient()} ItemAsync is {@link PutItemRequest#PutItemRequest()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBIngestBatcherStore#assignJobGetAssigned(String, List)}
   */
  @Test
  @DisplayName("Test assignJobGetAssigned(String, List); given AmazonDynamoDBAsyncClient() ItemAsync is PutItemRequest()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List DynamoDBIngestBatcherStore.assignJobGetAssigned(String, List)"})
  void testAssignJobGetAssigned_givenAmazonDynamoDBAsyncClientItemAsyncIsPutItemRequest() {
    // Arrange
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    dynamoDB.putItemAsync(new PutItemRequest());
    dynamoDB.putItemAsync("Table Name", new HashMap<>(), mock(AsyncHandler.class));
    InstanceProperties instanceProperties = new InstanceProperties();
    InstanceProperties instanceProperties2 = new InstanceProperties();
    DynamoDBIngestBatcherStore dynamoDBIngestBatcherStore = new DynamoDBIngestBatcherStore(dynamoDB, instanceProperties,
        new TablePropertiesProvider(instanceProperties2,
            new TablePropertiesStore(new InMemoryTableIndex(), mock(Client.class))));

    // Act and Assert
    assertTrue(dynamoDBIngestBatcherStore.assignJobGetAssigned("42", new ArrayList<>()).isEmpty());
  }

  /**
   * Test {@link DynamoDBIngestBatcherStore#assignJobGetAssigned(String, List)}.
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} All is {@link HashMap#HashMap()}.</li>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBIngestBatcherStore#assignJobGetAssigned(String, List)}
   */
  @Test
  @DisplayName("Test assignJobGetAssigned(String, List); given HashMap() All is HashMap(); when ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List DynamoDBIngestBatcherStore.assignJobGetAssigned(String, List)"})
  void testAssignJobGetAssigned_givenHashMapAllIsHashMap_whenArrayList() {
    // Arrange
    HashMap<String, AttributeValue> item = new HashMap<>();
    item.putAll(new HashMap<>());
    item.put("foo", new AttributeValue("foo"));

    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    dynamoDB.putItemAsync("Table Name", item, mock(AsyncHandler.class));
    InstanceProperties instanceProperties = new InstanceProperties();
    InstanceProperties instanceProperties2 = new InstanceProperties();
    DynamoDBIngestBatcherStore dynamoDBIngestBatcherStore = new DynamoDBIngestBatcherStore(dynamoDB, instanceProperties,
        new TablePropertiesProvider(instanceProperties2,
            new TablePropertiesStore(new InMemoryTableIndex(), mock(Client.class))));

    // Act and Assert
    assertTrue(dynamoDBIngestBatcherStore.assignJobGetAssigned("42", new ArrayList<>()).isEmpty());
  }

  /**
   * Test {@link DynamoDBIngestBatcherStore#assignJobGetAssigned(String, List)}.
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@code foo} is {@link AttributeValue#AttributeValue(String)} with s is {@code foo}.</li>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBIngestBatcherStore#assignJobGetAssigned(String, List)}
   */
  @Test
  @DisplayName("Test assignJobGetAssigned(String, List); given HashMap() 'foo' is AttributeValue(String) with s is 'foo'; when ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List DynamoDBIngestBatcherStore.assignJobGetAssigned(String, List)"})
  void testAssignJobGetAssigned_givenHashMapFooIsAttributeValueWithSIsFoo_whenArrayList() {
    // Arrange
    HashMap<String, AttributeValue> item = new HashMap<>();
    item.put("foo", new AttributeValue("foo"));

    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    dynamoDB.putItemAsync("Table Name", item, mock(AsyncHandler.class));
    InstanceProperties instanceProperties = new InstanceProperties();
    InstanceProperties instanceProperties2 = new InstanceProperties();
    DynamoDBIngestBatcherStore dynamoDBIngestBatcherStore = new DynamoDBIngestBatcherStore(dynamoDB, instanceProperties,
        new TablePropertiesProvider(instanceProperties2,
            new TablePropertiesStore(new InMemoryTableIndex(), mock(Client.class))));

    // Act and Assert
    assertTrue(dynamoDBIngestBatcherStore.assignJobGetAssigned("42", new ArrayList<>()).isEmpty());
  }

  /**
   * Test {@link DynamoDBIngestBatcherStore#assignJobGetAssigned(String, List)}.
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} IfAbsent {@code 42} is {@link AttributeValue#AttributeValue(String)} with s is {@code foo}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBIngestBatcherStore#assignJobGetAssigned(String, List)}
   */
  @Test
  @DisplayName("Test assignJobGetAssigned(String, List); given HashMap() IfAbsent '42' is AttributeValue(String) with s is 'foo'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List DynamoDBIngestBatcherStore.assignJobGetAssigned(String, List)"})
  void testAssignJobGetAssigned_givenHashMapIfAbsent42IsAttributeValueWithSIsFoo() {
    // Arrange
    HashMap<String, AttributeValue> item = new HashMap<>();
    item.putIfAbsent("42", new AttributeValue("foo"));

    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    dynamoDB.putItemAsync("Table Name", item, mock(AsyncHandler.class));
    InstanceProperties instanceProperties = new InstanceProperties();
    InstanceProperties instanceProperties2 = new InstanceProperties();
    DynamoDBIngestBatcherStore dynamoDBIngestBatcherStore = new DynamoDBIngestBatcherStore(dynamoDB, instanceProperties,
        new TablePropertiesProvider(instanceProperties2,
            new TablePropertiesStore(new InMemoryTableIndex(), mock(Client.class))));

    // Act and Assert
    assertTrue(dynamoDBIngestBatcherStore.assignJobGetAssigned("42", new ArrayList<>()).isEmpty());
  }

  /**
   * Test {@link DynamoDBIngestBatcherStore#assignJobGetAssigned(String, List)}.
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} replaceAll {@link BiFunction}.</li>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBIngestBatcherStore#assignJobGetAssigned(String, List)}
   */
  @Test
  @DisplayName("Test assignJobGetAssigned(String, List); given HashMap() replaceAll BiFunction; when ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List DynamoDBIngestBatcherStore.assignJobGetAssigned(String, List)"})
  void testAssignJobGetAssigned_givenHashMapReplaceAllBiFunction_whenArrayList() {
    // Arrange
    HashMap<String, AttributeValue> item = new HashMap<>();
    item.replaceAll(mock(BiFunction.class));
    item.put("foo", new AttributeValue("foo"));

    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    dynamoDB.putItemAsync("Table Name", item, mock(AsyncHandler.class));
    InstanceProperties instanceProperties = new InstanceProperties();
    InstanceProperties instanceProperties2 = new InstanceProperties();
    DynamoDBIngestBatcherStore dynamoDBIngestBatcherStore = new DynamoDBIngestBatcherStore(dynamoDB, instanceProperties,
        new TablePropertiesProvider(instanceProperties2,
            new TablePropertiesStore(new InMemoryTableIndex(), mock(Client.class))));

    // Act and Assert
    assertTrue(dynamoDBIngestBatcherStore.assignJobGetAssigned("42", new ArrayList<>()).isEmpty());
  }
}
