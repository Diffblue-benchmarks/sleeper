package sleeper.ingest.tracker.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.nio.ByteBuffer;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.tracker.ingest.task.IngestTaskStatus;

class DynamoDBIngestTaskStatusFormatDiffblueTest {
  /**
   * Test {@link DynamoDBIngestTaskStatusFormat#createTaskStartedRecord(IngestTaskStatus)}.
   * <p>
   * Method under test: {@link DynamoDBIngestTaskStatusFormat#createTaskStartedRecord(IngestTaskStatus)}
   */
  @Test
  @DisplayName("Test createTaskStartedRecord(IngestTaskStatus)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Map DynamoDBIngestTaskStatusFormat.createTaskStartedRecord(IngestTaskStatus)"})
  void testCreateTaskStartedRecord() {
    // Arrange
    Supplier<Instant> getTimeNow = mock(Supplier.class);
    when(getTimeNow.get()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    DynamoDBIngestTaskStatusFormat dynamoDBIngestTaskStatusFormat = new DynamoDBIngestTaskStatusFormat(1, getTimeNow);
    IngestTaskStatus taskStatus = mock(IngestTaskStatus.class);
    when(taskStatus.getTaskId()).thenReturn("42");
    when(taskStatus.getStartTime())
        .thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act
    Map<String, AttributeValue> actualCreateTaskStartedRecordResult = dynamoDBIngestTaskStatusFormat
        .createTaskStartedRecord(taskStatus);

    // Assert
    verify(getTimeNow).get();
    verify(taskStatus).getStartTime();
    verify(taskStatus).getTaskId();
    assertEquals(5, actualCreateTaskStartedRecordResult.size());
    assertTrue(actualCreateTaskStartedRecordResult.containsKey(DynamoDBIngestTaskStatusFormat.EXPIRY_DATE));
    assertTrue(actualCreateTaskStartedRecordResult.containsKey(DynamoDBIngestTaskStatusFormat.TASK_ID));
    String string = DynamoDBIngestTaskStatusFormat.UPDATE_TIME;
    assertTrue(actualCreateTaskStartedRecordResult.containsKey(string));
    assertTrue(actualCreateTaskStartedRecordResult.containsKey(DynamoDBIngestTaskStatusFormat.UPDATE_TYPE));
    AttributeValue expectedGetResult = actualCreateTaskStartedRecordResult.get(string);
    assertEquals(expectedGetResult, actualCreateTaskStartedRecordResult.get(DynamoDBIngestTaskStatusFormat.START_TIME));
  }

  /**
   * Test {@link DynamoDBIngestTaskStatusFormat#streamTaskStatuses(Stream)}.
   * <p>
   * Method under test: {@link DynamoDBIngestTaskStatusFormat#streamTaskStatuses(Stream)}
   */
  @Test
  @DisplayName("Test streamTaskStatuses(Stream)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream DynamoDBIngestTaskStatusFormat.streamTaskStatuses(Stream)"})
  void testStreamTaskStatuses() {
    // Arrange
    AttributeValue attributeValue = new AttributeValue(DynamoDBIngestTaskStatusFormat.TASK_ID);
    attributeValue.addMEntry(DynamoDBIngestTaskStatusFormat.TASK_ID,
        new AttributeValue(DynamoDBIngestTaskStatusFormat.TASK_ID));

    HashMap<String, AttributeValue> stringAttributeValueMap = new HashMap<>();
    stringAttributeValueMap.put(DynamoDBIngestTaskStatusFormat.UPDATE_TYPE, attributeValue);
    stringAttributeValueMap.put(DynamoDBIngestTaskStatusFormat.TASK_ID,
        new AttributeValue(DynamoDBIngestTaskStatusFormat.TASK_ID));

    ArrayList<Map<String, AttributeValue>> mapList = new ArrayList<>();
    mapList.add(stringAttributeValueMap);
    Stream<Map<String, AttributeValue>> items = mapList.stream();

    // Act
    Stream<IngestTaskStatus> actualStreamTaskStatusesResult = DynamoDBIngestTaskStatusFormat.streamTaskStatuses(items);

    // Assert
    assertTrue(actualStreamTaskStatusesResult.limit(5).collect(Collectors.toList()).isEmpty());
  }

  /**
   * Test {@link DynamoDBIngestTaskStatusFormat#streamTaskStatuses(Stream)}.
   * <ul>
   *   <li>Given {@code A}.</li>
   *   <li>Then return limit five collect toList Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBIngestTaskStatusFormat#streamTaskStatuses(Stream)}
   */
  @Test
  @DisplayName("Test streamTaskStatuses(Stream); given 'A'; then return limit five collect toList Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream DynamoDBIngestTaskStatusFormat.streamTaskStatuses(Stream)"})
  void testStreamTaskStatuses_givenA_thenReturnLimitFiveCollectToListEmpty() {
    // Arrange
    AttributeValue attributeValue = new AttributeValue(DynamoDBIngestTaskStatusFormat.TASK_ID);
    attributeValue.setB(ByteBuffer.wrap(new byte[]{'A', -1, 'A', -1, 'A', -1, 'A', -1}));
    attributeValue.addMEntry(DynamoDBIngestTaskStatusFormat.TASK_ID,
        new AttributeValue(DynamoDBIngestTaskStatusFormat.TASK_ID));

    HashMap<String, AttributeValue> stringAttributeValueMap = new HashMap<>();
    stringAttributeValueMap.put(DynamoDBIngestTaskStatusFormat.UPDATE_TYPE, attributeValue);
    stringAttributeValueMap.put(DynamoDBIngestTaskStatusFormat.TASK_ID,
        new AttributeValue(DynamoDBIngestTaskStatusFormat.TASK_ID));

    ArrayList<Map<String, AttributeValue>> mapList = new ArrayList<>();
    mapList.add(stringAttributeValueMap);
    Stream<Map<String, AttributeValue>> items = mapList.stream();

    // Act
    Stream<IngestTaskStatus> actualStreamTaskStatusesResult = DynamoDBIngestTaskStatusFormat.streamTaskStatuses(items);

    // Assert
    assertTrue(actualStreamTaskStatusesResult.limit(5).collect(Collectors.toList()).isEmpty());
  }

  /**
   * Test {@link DynamoDBIngestTaskStatusFormat#streamTaskStatuses(Stream)}.
   * <ul>
   *   <li>Given {@link AttributeValue#AttributeValue(String)} with s is {@link DynamoDBIngestTaskStatusFormat#TASK_ID} BOOL is {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBIngestTaskStatusFormat#streamTaskStatuses(Stream)}
   */
  @Test
  @DisplayName("Test streamTaskStatuses(Stream); given AttributeValue(String) with s is TASK_ID BOOL is 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream DynamoDBIngestTaskStatusFormat.streamTaskStatuses(Stream)"})
  void testStreamTaskStatuses_givenAttributeValueWithSIsTask_idBoolIsTrue() {
    // Arrange
    AttributeValue attributeValue = new AttributeValue(DynamoDBIngestTaskStatusFormat.TASK_ID);
    attributeValue.setBOOL(true);
    attributeValue.addMEntry(DynamoDBIngestTaskStatusFormat.TASK_ID,
        new AttributeValue(DynamoDBIngestTaskStatusFormat.TASK_ID));

    HashMap<String, AttributeValue> stringAttributeValueMap = new HashMap<>();
    stringAttributeValueMap.put(DynamoDBIngestTaskStatusFormat.UPDATE_TYPE, attributeValue);
    stringAttributeValueMap.put(DynamoDBIngestTaskStatusFormat.TASK_ID,
        new AttributeValue(DynamoDBIngestTaskStatusFormat.TASK_ID));

    ArrayList<Map<String, AttributeValue>> mapList = new ArrayList<>();
    mapList.add(stringAttributeValueMap);
    Stream<Map<String, AttributeValue>> items = mapList.stream();

    // Act
    Stream<IngestTaskStatus> actualStreamTaskStatusesResult = DynamoDBIngestTaskStatusFormat.streamTaskStatuses(items);

    // Assert
    assertTrue(actualStreamTaskStatusesResult.limit(5).collect(Collectors.toList()).isEmpty());
  }

  /**
   * Test {@link DynamoDBIngestTaskStatusFormat#streamTaskStatuses(Stream)}.
   * <ul>
   *   <li>Given {@link AttributeValue#AttributeValue(String)} with s is {@link DynamoDBIngestTaskStatusFormat#TASK_ID} BS is {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBIngestTaskStatusFormat#streamTaskStatuses(Stream)}
   */
  @Test
  @DisplayName("Test streamTaskStatuses(Stream); given AttributeValue(String) with s is TASK_ID BS is ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream DynamoDBIngestTaskStatusFormat.streamTaskStatuses(Stream)"})
  void testStreamTaskStatuses_givenAttributeValueWithSIsTask_idBsIsArrayList() {
    // Arrange
    AttributeValue attributeValue = new AttributeValue(DynamoDBIngestTaskStatusFormat.TASK_ID);
    attributeValue.setBS(new ArrayList<>());
    attributeValue.addMEntry(DynamoDBIngestTaskStatusFormat.TASK_ID,
        new AttributeValue(DynamoDBIngestTaskStatusFormat.TASK_ID));

    HashMap<String, AttributeValue> stringAttributeValueMap = new HashMap<>();
    stringAttributeValueMap.put(DynamoDBIngestTaskStatusFormat.UPDATE_TYPE, attributeValue);
    stringAttributeValueMap.put(DynamoDBIngestTaskStatusFormat.TASK_ID,
        new AttributeValue(DynamoDBIngestTaskStatusFormat.TASK_ID));

    ArrayList<Map<String, AttributeValue>> mapList = new ArrayList<>();
    mapList.add(stringAttributeValueMap);
    Stream<Map<String, AttributeValue>> items = mapList.stream();

    // Act
    Stream<IngestTaskStatus> actualStreamTaskStatusesResult = DynamoDBIngestTaskStatusFormat.streamTaskStatuses(items);

    // Assert
    assertTrue(actualStreamTaskStatusesResult.limit(5).collect(Collectors.toList()).isEmpty());
  }

  /**
   * Test {@link DynamoDBIngestTaskStatusFormat#streamTaskStatuses(Stream)}.
   * <ul>
   *   <li>Given {@link AttributeValue#AttributeValue(String)} with s is {@link DynamoDBIngestTaskStatusFormat#TASK_ID} L is {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBIngestTaskStatusFormat#streamTaskStatuses(Stream)}
   */
  @Test
  @DisplayName("Test streamTaskStatuses(Stream); given AttributeValue(String) with s is TASK_ID L is ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream DynamoDBIngestTaskStatusFormat.streamTaskStatuses(Stream)"})
  void testStreamTaskStatuses_givenAttributeValueWithSIsTask_idLIsArrayList() {
    // Arrange
    AttributeValue attributeValue = new AttributeValue(DynamoDBIngestTaskStatusFormat.TASK_ID);
    attributeValue.setL(new ArrayList<>());
    attributeValue.addMEntry(DynamoDBIngestTaskStatusFormat.TASK_ID,
        new AttributeValue(DynamoDBIngestTaskStatusFormat.TASK_ID));

    HashMap<String, AttributeValue> stringAttributeValueMap = new HashMap<>();
    stringAttributeValueMap.put(DynamoDBIngestTaskStatusFormat.UPDATE_TYPE, attributeValue);
    stringAttributeValueMap.put(DynamoDBIngestTaskStatusFormat.TASK_ID,
        new AttributeValue(DynamoDBIngestTaskStatusFormat.TASK_ID));

    ArrayList<Map<String, AttributeValue>> mapList = new ArrayList<>();
    mapList.add(stringAttributeValueMap);
    Stream<Map<String, AttributeValue>> items = mapList.stream();

    // Act
    Stream<IngestTaskStatus> actualStreamTaskStatusesResult = DynamoDBIngestTaskStatusFormat.streamTaskStatuses(items);

    // Assert
    assertTrue(actualStreamTaskStatusesResult.limit(5).collect(Collectors.toList()).isEmpty());
  }

  /**
   * Test {@link DynamoDBIngestTaskStatusFormat#streamTaskStatuses(Stream)}.
   * <ul>
   *   <li>Given {@link AttributeValue#AttributeValue(String)} with s is {@link DynamoDBIngestTaskStatusFormat#TASK_ID} M is {@link HashMap#HashMap()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBIngestTaskStatusFormat#streamTaskStatuses(Stream)}
   */
  @Test
  @DisplayName("Test streamTaskStatuses(Stream); given AttributeValue(String) with s is TASK_ID M is HashMap()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream DynamoDBIngestTaskStatusFormat.streamTaskStatuses(Stream)"})
  void testStreamTaskStatuses_givenAttributeValueWithSIsTask_idMIsHashMap() {
    // Arrange
    AttributeValue attributeValue = new AttributeValue(DynamoDBIngestTaskStatusFormat.TASK_ID);
    attributeValue.setM(new HashMap<>());

    HashMap<String, AttributeValue> stringAttributeValueMap = new HashMap<>();
    stringAttributeValueMap.put(DynamoDBIngestTaskStatusFormat.UPDATE_TYPE, attributeValue);
    stringAttributeValueMap.put(DynamoDBIngestTaskStatusFormat.TASK_ID,
        new AttributeValue(DynamoDBIngestTaskStatusFormat.TASK_ID));

    ArrayList<Map<String, AttributeValue>> mapList = new ArrayList<>();
    mapList.add(stringAttributeValueMap);
    Stream<Map<String, AttributeValue>> items = mapList.stream();

    // Act
    Stream<IngestTaskStatus> actualStreamTaskStatusesResult = DynamoDBIngestTaskStatusFormat.streamTaskStatuses(items);

    // Assert
    assertTrue(actualStreamTaskStatusesResult.limit(5).collect(Collectors.toList()).isEmpty());
  }

  /**
   * Test {@link DynamoDBIngestTaskStatusFormat#streamTaskStatuses(Stream)}.
   * <ul>
   *   <li>Given {@link AttributeValue#AttributeValue(String)} with s is {@link DynamoDBIngestTaskStatusFormat#TASK_ID} N is {@link DynamoDBIngestTaskStatusFormat#TASK_ID}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBIngestTaskStatusFormat#streamTaskStatuses(Stream)}
   */
  @Test
  @DisplayName("Test streamTaskStatuses(Stream); given AttributeValue(String) with s is TASK_ID N is TASK_ID")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream DynamoDBIngestTaskStatusFormat.streamTaskStatuses(Stream)"})
  void testStreamTaskStatuses_givenAttributeValueWithSIsTask_idNIsTask_id() {
    // Arrange
    AttributeValue attributeValue = new AttributeValue(DynamoDBIngestTaskStatusFormat.TASK_ID);
    attributeValue.setN(DynamoDBIngestTaskStatusFormat.TASK_ID);
    attributeValue.addMEntry(DynamoDBIngestTaskStatusFormat.TASK_ID,
        new AttributeValue(DynamoDBIngestTaskStatusFormat.TASK_ID));

    HashMap<String, AttributeValue> stringAttributeValueMap = new HashMap<>();
    stringAttributeValueMap.put(DynamoDBIngestTaskStatusFormat.UPDATE_TYPE, attributeValue);
    stringAttributeValueMap.put(DynamoDBIngestTaskStatusFormat.TASK_ID,
        new AttributeValue(DynamoDBIngestTaskStatusFormat.TASK_ID));

    ArrayList<Map<String, AttributeValue>> mapList = new ArrayList<>();
    mapList.add(stringAttributeValueMap);
    Stream<Map<String, AttributeValue>> items = mapList.stream();

    // Act
    Stream<IngestTaskStatus> actualStreamTaskStatusesResult = DynamoDBIngestTaskStatusFormat.streamTaskStatuses(items);

    // Assert
    assertTrue(actualStreamTaskStatusesResult.limit(5).collect(Collectors.toList()).isEmpty());
  }

  /**
   * Test {@link DynamoDBIngestTaskStatusFormat#streamTaskStatuses(Stream)}.
   * <ul>
   *   <li>Given {@link AttributeValue#AttributeValue(String)} with s is {@link DynamoDBIngestTaskStatusFormat#TASK_ID} NS is {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBIngestTaskStatusFormat#streamTaskStatuses(Stream)}
   */
  @Test
  @DisplayName("Test streamTaskStatuses(Stream); given AttributeValue(String) with s is TASK_ID NS is ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream DynamoDBIngestTaskStatusFormat.streamTaskStatuses(Stream)"})
  void testStreamTaskStatuses_givenAttributeValueWithSIsTask_idNsIsArrayList() {
    // Arrange
    AttributeValue attributeValue = new AttributeValue(DynamoDBIngestTaskStatusFormat.TASK_ID);
    attributeValue.setNS(new ArrayList<>());
    attributeValue.addMEntry(DynamoDBIngestTaskStatusFormat.TASK_ID,
        new AttributeValue(DynamoDBIngestTaskStatusFormat.TASK_ID));

    HashMap<String, AttributeValue> stringAttributeValueMap = new HashMap<>();
    stringAttributeValueMap.put(DynamoDBIngestTaskStatusFormat.UPDATE_TYPE, attributeValue);
    stringAttributeValueMap.put(DynamoDBIngestTaskStatusFormat.TASK_ID,
        new AttributeValue(DynamoDBIngestTaskStatusFormat.TASK_ID));

    ArrayList<Map<String, AttributeValue>> mapList = new ArrayList<>();
    mapList.add(stringAttributeValueMap);
    Stream<Map<String, AttributeValue>> items = mapList.stream();

    // Act
    Stream<IngestTaskStatus> actualStreamTaskStatusesResult = DynamoDBIngestTaskStatusFormat.streamTaskStatuses(items);

    // Assert
    assertTrue(actualStreamTaskStatusesResult.limit(5).collect(Collectors.toList()).isEmpty());
  }

  /**
   * Test {@link DynamoDBIngestTaskStatusFormat#streamTaskStatuses(Stream)}.
   * <ul>
   *   <li>Given {@link AttributeValue#AttributeValue(String)} with s is {@link DynamoDBIngestTaskStatusFormat#TASK_ID} NULL is {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBIngestTaskStatusFormat#streamTaskStatuses(Stream)}
   */
  @Test
  @DisplayName("Test streamTaskStatuses(Stream); given AttributeValue(String) with s is TASK_ID NULL is 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream DynamoDBIngestTaskStatusFormat.streamTaskStatuses(Stream)"})
  void testStreamTaskStatuses_givenAttributeValueWithSIsTask_idNullIsTrue() {
    // Arrange
    AttributeValue attributeValue = new AttributeValue(DynamoDBIngestTaskStatusFormat.TASK_ID);
    attributeValue.setNULL(true);
    attributeValue.addMEntry(DynamoDBIngestTaskStatusFormat.TASK_ID,
        new AttributeValue(DynamoDBIngestTaskStatusFormat.TASK_ID));

    HashMap<String, AttributeValue> stringAttributeValueMap = new HashMap<>();
    stringAttributeValueMap.put(DynamoDBIngestTaskStatusFormat.UPDATE_TYPE, attributeValue);
    stringAttributeValueMap.put(DynamoDBIngestTaskStatusFormat.TASK_ID,
        new AttributeValue(DynamoDBIngestTaskStatusFormat.TASK_ID));

    ArrayList<Map<String, AttributeValue>> mapList = new ArrayList<>();
    mapList.add(stringAttributeValueMap);
    Stream<Map<String, AttributeValue>> items = mapList.stream();

    // Act
    Stream<IngestTaskStatus> actualStreamTaskStatusesResult = DynamoDBIngestTaskStatusFormat.streamTaskStatuses(items);

    // Assert
    assertTrue(actualStreamTaskStatusesResult.limit(5).collect(Collectors.toList()).isEmpty());
  }

  /**
   * Test {@link DynamoDBIngestTaskStatusFormat#streamTaskStatuses(Stream)}.
   * <ul>
   *   <li>Given {@link AttributeValue#AttributeValue(String)} with s is {@link DynamoDBIngestTaskStatusFormat#TASK_ID} SS is {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBIngestTaskStatusFormat#streamTaskStatuses(Stream)}
   */
  @Test
  @DisplayName("Test streamTaskStatuses(Stream); given AttributeValue(String) with s is TASK_ID SS is ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream DynamoDBIngestTaskStatusFormat.streamTaskStatuses(Stream)"})
  void testStreamTaskStatuses_givenAttributeValueWithSIsTask_idSsIsArrayList() {
    // Arrange
    AttributeValue attributeValue = new AttributeValue(DynamoDBIngestTaskStatusFormat.TASK_ID);
    attributeValue.setSS(new ArrayList<>());
    attributeValue.addMEntry(DynamoDBIngestTaskStatusFormat.TASK_ID,
        new AttributeValue(DynamoDBIngestTaskStatusFormat.TASK_ID));

    HashMap<String, AttributeValue> stringAttributeValueMap = new HashMap<>();
    stringAttributeValueMap.put(DynamoDBIngestTaskStatusFormat.UPDATE_TYPE, attributeValue);
    stringAttributeValueMap.put(DynamoDBIngestTaskStatusFormat.TASK_ID,
        new AttributeValue(DynamoDBIngestTaskStatusFormat.TASK_ID));

    ArrayList<Map<String, AttributeValue>> mapList = new ArrayList<>();
    mapList.add(stringAttributeValueMap);
    Stream<Map<String, AttributeValue>> items = mapList.stream();

    // Act
    Stream<IngestTaskStatus> actualStreamTaskStatusesResult = DynamoDBIngestTaskStatusFormat.streamTaskStatuses(items);

    // Assert
    assertTrue(actualStreamTaskStatusesResult.limit(5).collect(Collectors.toList()).isEmpty());
  }

  /**
   * Test {@link DynamoDBIngestTaskStatusFormat#streamTaskStatuses(Stream)}.
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@link DynamoDBIngestTaskStatusFormat#TASK_ID} is {@link AttributeValue#AttributeValue()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBIngestTaskStatusFormat#streamTaskStatuses(Stream)}
   */
  @Test
  @DisplayName("Test streamTaskStatuses(Stream); given HashMap() TASK_ID is AttributeValue()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream DynamoDBIngestTaskStatusFormat.streamTaskStatuses(Stream)"})
  void testStreamTaskStatuses_givenHashMapTask_idIsAttributeValue() {
    // Arrange
    HashMap<String, AttributeValue> stringAttributeValueMap = new HashMap<>();
    stringAttributeValueMap.put(DynamoDBIngestTaskStatusFormat.UPDATE_TYPE,
        new AttributeValue(DynamoDBIngestTaskStatusFormat.TASK_ID));
    stringAttributeValueMap.put(DynamoDBIngestTaskStatusFormat.TASK_ID, new AttributeValue());

    ArrayList<Map<String, AttributeValue>> mapList = new ArrayList<>();
    mapList.add(stringAttributeValueMap);
    Stream<Map<String, AttributeValue>> items = mapList.stream();

    // Act
    Stream<IngestTaskStatus> actualStreamTaskStatusesResult = DynamoDBIngestTaskStatusFormat.streamTaskStatuses(items);

    // Assert
    assertTrue(actualStreamTaskStatusesResult.limit(5).collect(Collectors.toList()).isEmpty());
  }

  /**
   * Test {@link DynamoDBIngestTaskStatusFormat#streamTaskStatuses(Stream)}.
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@link DynamoDBIngestTaskStatusFormat#UPDATE_TYPE} is {@link AttributeValue#AttributeValue(String)} with s is {@link DynamoDBIngestTaskStatusFormat#TASK_ID}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBIngestTaskStatusFormat#streamTaskStatuses(Stream)}
   */
  @Test
  @DisplayName("Test streamTaskStatuses(Stream); given HashMap() UPDATE_TYPE is AttributeValue(String) with s is TASK_ID")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream DynamoDBIngestTaskStatusFormat.streamTaskStatuses(Stream)"})
  void testStreamTaskStatuses_givenHashMapUpdate_typeIsAttributeValueWithSIsTask_id() {
    // Arrange
    HashMap<String, AttributeValue> stringAttributeValueMap = new HashMap<>();
    stringAttributeValueMap.put(DynamoDBIngestTaskStatusFormat.UPDATE_TYPE,
        new AttributeValue(DynamoDBIngestTaskStatusFormat.TASK_ID));
    stringAttributeValueMap.put(DynamoDBIngestTaskStatusFormat.TASK_ID,
        new AttributeValue(DynamoDBIngestTaskStatusFormat.TASK_ID));

    ArrayList<Map<String, AttributeValue>> mapList = new ArrayList<>();
    mapList.add(stringAttributeValueMap);
    Stream<Map<String, AttributeValue>> items = mapList.stream();

    // Act
    Stream<IngestTaskStatus> actualStreamTaskStatusesResult = DynamoDBIngestTaskStatusFormat.streamTaskStatuses(items);

    // Assert
    assertTrue(actualStreamTaskStatusesResult.limit(5).collect(Collectors.toList()).isEmpty());
  }

  /**
   * Test {@link DynamoDBIngestTaskStatusFormat#streamTaskStatuses(Stream)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()} stream.</li>
   *   <li>Then return limit five collect toList Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBIngestTaskStatusFormat#streamTaskStatuses(Stream)}
   */
  @Test
  @DisplayName("Test streamTaskStatuses(Stream); when ArrayList() stream; then return limit five collect toList Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream DynamoDBIngestTaskStatusFormat.streamTaskStatuses(Stream)"})
  void testStreamTaskStatuses_whenArrayListStream_thenReturnLimitFiveCollectToListEmpty() {
    // Arrange
    ArrayList<Map<String, AttributeValue>> mapList = new ArrayList<>();
    Stream<Map<String, AttributeValue>> items = mapList.stream();

    // Act
    Stream<IngestTaskStatus> actualStreamTaskStatusesResult = DynamoDBIngestTaskStatusFormat.streamTaskStatuses(items);

    // Assert
    assertTrue(actualStreamTaskStatusesResult.limit(5).collect(Collectors.toList()).isEmpty());
  }
}
