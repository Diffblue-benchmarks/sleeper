package sleeper.compaction.tracker.task;

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
import sleeper.core.tracker.compaction.task.CompactionTaskStatus;

class DynamoDBCompactionTaskStatusFormatDiffblueTest {
  /**
   * Test {@link DynamoDBCompactionTaskStatusFormat#createTaskStartedRecord(CompactionTaskStatus)}.
   * <p>
   * Method under test: {@link DynamoDBCompactionTaskStatusFormat#createTaskStartedRecord(CompactionTaskStatus)}
   */
  @Test
  @DisplayName("Test createTaskStartedRecord(CompactionTaskStatus)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Map DynamoDBCompactionTaskStatusFormat.createTaskStartedRecord(CompactionTaskStatus)"})
  void testCreateTaskStartedRecord() {
    // Arrange
    Supplier<Instant> getTimeNow = mock(Supplier.class);
    when(getTimeNow.get()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    DynamoDBCompactionTaskStatusFormat dynamoDBCompactionTaskStatusFormat = new DynamoDBCompactionTaskStatusFormat(1,
        getTimeNow);
    CompactionTaskStatus taskStatus = mock(CompactionTaskStatus.class);
    when(taskStatus.getTaskId()).thenReturn("42");
    when(taskStatus.getStartTime())
        .thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act
    Map<String, AttributeValue> actualCreateTaskStartedRecordResult = dynamoDBCompactionTaskStatusFormat
        .createTaskStartedRecord(taskStatus);

    // Assert
    verify(getTimeNow).get();
    verify(taskStatus).getStartTime();
    verify(taskStatus).getTaskId();
    assertEquals(5, actualCreateTaskStartedRecordResult.size());
    assertTrue(actualCreateTaskStartedRecordResult.containsKey(DynamoDBCompactionTaskStatusFormat.EXPIRY_DATE));
    assertTrue(actualCreateTaskStartedRecordResult.containsKey(DynamoDBCompactionTaskStatusFormat.TASK_ID));
    String string = DynamoDBCompactionTaskStatusFormat.UPDATE_TIME;
    assertTrue(actualCreateTaskStartedRecordResult.containsKey(string));
    assertTrue(actualCreateTaskStartedRecordResult.containsKey(DynamoDBCompactionTaskStatusFormat.UPDATE_TYPE));
    AttributeValue expectedGetResult = actualCreateTaskStartedRecordResult.get(string);
    assertEquals(expectedGetResult,
        actualCreateTaskStartedRecordResult.get(DynamoDBCompactionTaskStatusFormat.START_TIME));
  }

  /**
   * Test {@link DynamoDBCompactionTaskStatusFormat#streamTaskStatuses(Stream)}.
   * <p>
   * Method under test: {@link DynamoDBCompactionTaskStatusFormat#streamTaskStatuses(Stream)}
   */
  @Test
  @DisplayName("Test streamTaskStatuses(Stream)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream DynamoDBCompactionTaskStatusFormat.streamTaskStatuses(Stream)"})
  void testStreamTaskStatuses() {
    // Arrange
    AttributeValue attributeValue = new AttributeValue(DynamoDBCompactionTaskStatusFormat.TASK_ID);
    attributeValue.addMEntry(DynamoDBCompactionTaskStatusFormat.TASK_ID,
        new AttributeValue(DynamoDBCompactionTaskStatusFormat.TASK_ID));

    HashMap<String, AttributeValue> stringAttributeValueMap = new HashMap<>();
    stringAttributeValueMap.put(DynamoDBCompactionTaskStatusFormat.UPDATE_TYPE, attributeValue);
    stringAttributeValueMap.put(DynamoDBCompactionTaskStatusFormat.TASK_ID,
        new AttributeValue(DynamoDBCompactionTaskStatusFormat.TASK_ID));

    ArrayList<Map<String, AttributeValue>> mapList = new ArrayList<>();
    mapList.add(stringAttributeValueMap);
    Stream<Map<String, AttributeValue>> items = mapList.stream();

    // Act
    Stream<CompactionTaskStatus> actualStreamTaskStatusesResult = DynamoDBCompactionTaskStatusFormat
        .streamTaskStatuses(items);

    // Assert
    assertTrue(actualStreamTaskStatusesResult.limit(5).collect(Collectors.toList()).isEmpty());
  }

  /**
   * Test {@link DynamoDBCompactionTaskStatusFormat#streamTaskStatuses(Stream)}.
   * <ul>
   *   <li>Given {@code A}.</li>
   *   <li>Then return limit five collect toList Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBCompactionTaskStatusFormat#streamTaskStatuses(Stream)}
   */
  @Test
  @DisplayName("Test streamTaskStatuses(Stream); given 'A'; then return limit five collect toList Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream DynamoDBCompactionTaskStatusFormat.streamTaskStatuses(Stream)"})
  void testStreamTaskStatuses_givenA_thenReturnLimitFiveCollectToListEmpty() {
    // Arrange
    AttributeValue attributeValue = new AttributeValue(DynamoDBCompactionTaskStatusFormat.TASK_ID);
    attributeValue.setB(ByteBuffer.wrap(new byte[]{'A', -1, 'A', -1, 'A', -1, 'A', -1}));
    attributeValue.addMEntry(DynamoDBCompactionTaskStatusFormat.TASK_ID,
        new AttributeValue(DynamoDBCompactionTaskStatusFormat.TASK_ID));

    HashMap<String, AttributeValue> stringAttributeValueMap = new HashMap<>();
    stringAttributeValueMap.put(DynamoDBCompactionTaskStatusFormat.UPDATE_TYPE, attributeValue);
    stringAttributeValueMap.put(DynamoDBCompactionTaskStatusFormat.TASK_ID,
        new AttributeValue(DynamoDBCompactionTaskStatusFormat.TASK_ID));

    ArrayList<Map<String, AttributeValue>> mapList = new ArrayList<>();
    mapList.add(stringAttributeValueMap);
    Stream<Map<String, AttributeValue>> items = mapList.stream();

    // Act
    Stream<CompactionTaskStatus> actualStreamTaskStatusesResult = DynamoDBCompactionTaskStatusFormat
        .streamTaskStatuses(items);

    // Assert
    assertTrue(actualStreamTaskStatusesResult.limit(5).collect(Collectors.toList()).isEmpty());
  }

  /**
   * Test {@link DynamoDBCompactionTaskStatusFormat#streamTaskStatuses(Stream)}.
   * <ul>
   *   <li>Given {@link AttributeValue#AttributeValue(String)} with s is {@link DynamoDBCompactionTaskStatusFormat#TASK_ID} BOOL is {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBCompactionTaskStatusFormat#streamTaskStatuses(Stream)}
   */
  @Test
  @DisplayName("Test streamTaskStatuses(Stream); given AttributeValue(String) with s is TASK_ID BOOL is 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream DynamoDBCompactionTaskStatusFormat.streamTaskStatuses(Stream)"})
  void testStreamTaskStatuses_givenAttributeValueWithSIsTask_idBoolIsTrue() {
    // Arrange
    AttributeValue attributeValue = new AttributeValue(DynamoDBCompactionTaskStatusFormat.TASK_ID);
    attributeValue.setBOOL(true);
    attributeValue.addMEntry(DynamoDBCompactionTaskStatusFormat.TASK_ID,
        new AttributeValue(DynamoDBCompactionTaskStatusFormat.TASK_ID));

    HashMap<String, AttributeValue> stringAttributeValueMap = new HashMap<>();
    stringAttributeValueMap.put(DynamoDBCompactionTaskStatusFormat.UPDATE_TYPE, attributeValue);
    stringAttributeValueMap.put(DynamoDBCompactionTaskStatusFormat.TASK_ID,
        new AttributeValue(DynamoDBCompactionTaskStatusFormat.TASK_ID));

    ArrayList<Map<String, AttributeValue>> mapList = new ArrayList<>();
    mapList.add(stringAttributeValueMap);
    Stream<Map<String, AttributeValue>> items = mapList.stream();

    // Act
    Stream<CompactionTaskStatus> actualStreamTaskStatusesResult = DynamoDBCompactionTaskStatusFormat
        .streamTaskStatuses(items);

    // Assert
    assertTrue(actualStreamTaskStatusesResult.limit(5).collect(Collectors.toList()).isEmpty());
  }

  /**
   * Test {@link DynamoDBCompactionTaskStatusFormat#streamTaskStatuses(Stream)}.
   * <ul>
   *   <li>Given {@link AttributeValue#AttributeValue(String)} with s is {@link DynamoDBCompactionTaskStatusFormat#TASK_ID} BS is {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBCompactionTaskStatusFormat#streamTaskStatuses(Stream)}
   */
  @Test
  @DisplayName("Test streamTaskStatuses(Stream); given AttributeValue(String) with s is TASK_ID BS is ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream DynamoDBCompactionTaskStatusFormat.streamTaskStatuses(Stream)"})
  void testStreamTaskStatuses_givenAttributeValueWithSIsTask_idBsIsArrayList() {
    // Arrange
    AttributeValue attributeValue = new AttributeValue(DynamoDBCompactionTaskStatusFormat.TASK_ID);
    attributeValue.setBS(new ArrayList<>());
    attributeValue.addMEntry(DynamoDBCompactionTaskStatusFormat.TASK_ID,
        new AttributeValue(DynamoDBCompactionTaskStatusFormat.TASK_ID));

    HashMap<String, AttributeValue> stringAttributeValueMap = new HashMap<>();
    stringAttributeValueMap.put(DynamoDBCompactionTaskStatusFormat.UPDATE_TYPE, attributeValue);
    stringAttributeValueMap.put(DynamoDBCompactionTaskStatusFormat.TASK_ID,
        new AttributeValue(DynamoDBCompactionTaskStatusFormat.TASK_ID));

    ArrayList<Map<String, AttributeValue>> mapList = new ArrayList<>();
    mapList.add(stringAttributeValueMap);
    Stream<Map<String, AttributeValue>> items = mapList.stream();

    // Act
    Stream<CompactionTaskStatus> actualStreamTaskStatusesResult = DynamoDBCompactionTaskStatusFormat
        .streamTaskStatuses(items);

    // Assert
    assertTrue(actualStreamTaskStatusesResult.limit(5).collect(Collectors.toList()).isEmpty());
  }

  /**
   * Test {@link DynamoDBCompactionTaskStatusFormat#streamTaskStatuses(Stream)}.
   * <ul>
   *   <li>Given {@link AttributeValue#AttributeValue(String)} with s is {@link DynamoDBCompactionTaskStatusFormat#TASK_ID} L is {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBCompactionTaskStatusFormat#streamTaskStatuses(Stream)}
   */
  @Test
  @DisplayName("Test streamTaskStatuses(Stream); given AttributeValue(String) with s is TASK_ID L is ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream DynamoDBCompactionTaskStatusFormat.streamTaskStatuses(Stream)"})
  void testStreamTaskStatuses_givenAttributeValueWithSIsTask_idLIsArrayList() {
    // Arrange
    AttributeValue attributeValue = new AttributeValue(DynamoDBCompactionTaskStatusFormat.TASK_ID);
    attributeValue.setL(new ArrayList<>());
    attributeValue.addMEntry(DynamoDBCompactionTaskStatusFormat.TASK_ID,
        new AttributeValue(DynamoDBCompactionTaskStatusFormat.TASK_ID));

    HashMap<String, AttributeValue> stringAttributeValueMap = new HashMap<>();
    stringAttributeValueMap.put(DynamoDBCompactionTaskStatusFormat.UPDATE_TYPE, attributeValue);
    stringAttributeValueMap.put(DynamoDBCompactionTaskStatusFormat.TASK_ID,
        new AttributeValue(DynamoDBCompactionTaskStatusFormat.TASK_ID));

    ArrayList<Map<String, AttributeValue>> mapList = new ArrayList<>();
    mapList.add(stringAttributeValueMap);
    Stream<Map<String, AttributeValue>> items = mapList.stream();

    // Act
    Stream<CompactionTaskStatus> actualStreamTaskStatusesResult = DynamoDBCompactionTaskStatusFormat
        .streamTaskStatuses(items);

    // Assert
    assertTrue(actualStreamTaskStatusesResult.limit(5).collect(Collectors.toList()).isEmpty());
  }

  /**
   * Test {@link DynamoDBCompactionTaskStatusFormat#streamTaskStatuses(Stream)}.
   * <ul>
   *   <li>Given {@link AttributeValue#AttributeValue(String)} with s is {@link DynamoDBCompactionTaskStatusFormat#TASK_ID} M is {@link HashMap#HashMap()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBCompactionTaskStatusFormat#streamTaskStatuses(Stream)}
   */
  @Test
  @DisplayName("Test streamTaskStatuses(Stream); given AttributeValue(String) with s is TASK_ID M is HashMap()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream DynamoDBCompactionTaskStatusFormat.streamTaskStatuses(Stream)"})
  void testStreamTaskStatuses_givenAttributeValueWithSIsTask_idMIsHashMap() {
    // Arrange
    AttributeValue attributeValue = new AttributeValue(DynamoDBCompactionTaskStatusFormat.TASK_ID);
    attributeValue.setM(new HashMap<>());

    HashMap<String, AttributeValue> stringAttributeValueMap = new HashMap<>();
    stringAttributeValueMap.put(DynamoDBCompactionTaskStatusFormat.UPDATE_TYPE, attributeValue);
    stringAttributeValueMap.put(DynamoDBCompactionTaskStatusFormat.TASK_ID,
        new AttributeValue(DynamoDBCompactionTaskStatusFormat.TASK_ID));

    ArrayList<Map<String, AttributeValue>> mapList = new ArrayList<>();
    mapList.add(stringAttributeValueMap);
    Stream<Map<String, AttributeValue>> items = mapList.stream();

    // Act
    Stream<CompactionTaskStatus> actualStreamTaskStatusesResult = DynamoDBCompactionTaskStatusFormat
        .streamTaskStatuses(items);

    // Assert
    assertTrue(actualStreamTaskStatusesResult.limit(5).collect(Collectors.toList()).isEmpty());
  }

  /**
   * Test {@link DynamoDBCompactionTaskStatusFormat#streamTaskStatuses(Stream)}.
   * <ul>
   *   <li>Given {@link AttributeValue#AttributeValue(String)} with s is {@link DynamoDBCompactionTaskStatusFormat#TASK_ID} N is {@link DynamoDBCompactionTaskStatusFormat#TASK_ID}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBCompactionTaskStatusFormat#streamTaskStatuses(Stream)}
   */
  @Test
  @DisplayName("Test streamTaskStatuses(Stream); given AttributeValue(String) with s is TASK_ID N is TASK_ID")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream DynamoDBCompactionTaskStatusFormat.streamTaskStatuses(Stream)"})
  void testStreamTaskStatuses_givenAttributeValueWithSIsTask_idNIsTask_id() {
    // Arrange
    AttributeValue attributeValue = new AttributeValue(DynamoDBCompactionTaskStatusFormat.TASK_ID);
    attributeValue.setN(DynamoDBCompactionTaskStatusFormat.TASK_ID);
    attributeValue.addMEntry(DynamoDBCompactionTaskStatusFormat.TASK_ID,
        new AttributeValue(DynamoDBCompactionTaskStatusFormat.TASK_ID));

    HashMap<String, AttributeValue> stringAttributeValueMap = new HashMap<>();
    stringAttributeValueMap.put(DynamoDBCompactionTaskStatusFormat.UPDATE_TYPE, attributeValue);
    stringAttributeValueMap.put(DynamoDBCompactionTaskStatusFormat.TASK_ID,
        new AttributeValue(DynamoDBCompactionTaskStatusFormat.TASK_ID));

    ArrayList<Map<String, AttributeValue>> mapList = new ArrayList<>();
    mapList.add(stringAttributeValueMap);
    Stream<Map<String, AttributeValue>> items = mapList.stream();

    // Act
    Stream<CompactionTaskStatus> actualStreamTaskStatusesResult = DynamoDBCompactionTaskStatusFormat
        .streamTaskStatuses(items);

    // Assert
    assertTrue(actualStreamTaskStatusesResult.limit(5).collect(Collectors.toList()).isEmpty());
  }

  /**
   * Test {@link DynamoDBCompactionTaskStatusFormat#streamTaskStatuses(Stream)}.
   * <ul>
   *   <li>Given {@link AttributeValue#AttributeValue(String)} with s is {@link DynamoDBCompactionTaskStatusFormat#TASK_ID} NS is {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBCompactionTaskStatusFormat#streamTaskStatuses(Stream)}
   */
  @Test
  @DisplayName("Test streamTaskStatuses(Stream); given AttributeValue(String) with s is TASK_ID NS is ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream DynamoDBCompactionTaskStatusFormat.streamTaskStatuses(Stream)"})
  void testStreamTaskStatuses_givenAttributeValueWithSIsTask_idNsIsArrayList() {
    // Arrange
    AttributeValue attributeValue = new AttributeValue(DynamoDBCompactionTaskStatusFormat.TASK_ID);
    attributeValue.setNS(new ArrayList<>());
    attributeValue.addMEntry(DynamoDBCompactionTaskStatusFormat.TASK_ID,
        new AttributeValue(DynamoDBCompactionTaskStatusFormat.TASK_ID));

    HashMap<String, AttributeValue> stringAttributeValueMap = new HashMap<>();
    stringAttributeValueMap.put(DynamoDBCompactionTaskStatusFormat.UPDATE_TYPE, attributeValue);
    stringAttributeValueMap.put(DynamoDBCompactionTaskStatusFormat.TASK_ID,
        new AttributeValue(DynamoDBCompactionTaskStatusFormat.TASK_ID));

    ArrayList<Map<String, AttributeValue>> mapList = new ArrayList<>();
    mapList.add(stringAttributeValueMap);
    Stream<Map<String, AttributeValue>> items = mapList.stream();

    // Act
    Stream<CompactionTaskStatus> actualStreamTaskStatusesResult = DynamoDBCompactionTaskStatusFormat
        .streamTaskStatuses(items);

    // Assert
    assertTrue(actualStreamTaskStatusesResult.limit(5).collect(Collectors.toList()).isEmpty());
  }

  /**
   * Test {@link DynamoDBCompactionTaskStatusFormat#streamTaskStatuses(Stream)}.
   * <ul>
   *   <li>Given {@link AttributeValue#AttributeValue(String)} with s is {@link DynamoDBCompactionTaskStatusFormat#TASK_ID} NULL is {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBCompactionTaskStatusFormat#streamTaskStatuses(Stream)}
   */
  @Test
  @DisplayName("Test streamTaskStatuses(Stream); given AttributeValue(String) with s is TASK_ID NULL is 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream DynamoDBCompactionTaskStatusFormat.streamTaskStatuses(Stream)"})
  void testStreamTaskStatuses_givenAttributeValueWithSIsTask_idNullIsTrue() {
    // Arrange
    AttributeValue attributeValue = new AttributeValue(DynamoDBCompactionTaskStatusFormat.TASK_ID);
    attributeValue.setNULL(true);
    attributeValue.addMEntry(DynamoDBCompactionTaskStatusFormat.TASK_ID,
        new AttributeValue(DynamoDBCompactionTaskStatusFormat.TASK_ID));

    HashMap<String, AttributeValue> stringAttributeValueMap = new HashMap<>();
    stringAttributeValueMap.put(DynamoDBCompactionTaskStatusFormat.UPDATE_TYPE, attributeValue);
    stringAttributeValueMap.put(DynamoDBCompactionTaskStatusFormat.TASK_ID,
        new AttributeValue(DynamoDBCompactionTaskStatusFormat.TASK_ID));

    ArrayList<Map<String, AttributeValue>> mapList = new ArrayList<>();
    mapList.add(stringAttributeValueMap);
    Stream<Map<String, AttributeValue>> items = mapList.stream();

    // Act
    Stream<CompactionTaskStatus> actualStreamTaskStatusesResult = DynamoDBCompactionTaskStatusFormat
        .streamTaskStatuses(items);

    // Assert
    assertTrue(actualStreamTaskStatusesResult.limit(5).collect(Collectors.toList()).isEmpty());
  }

  /**
   * Test {@link DynamoDBCompactionTaskStatusFormat#streamTaskStatuses(Stream)}.
   * <ul>
   *   <li>Given {@link AttributeValue#AttributeValue(String)} with s is {@link DynamoDBCompactionTaskStatusFormat#TASK_ID} SS is {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBCompactionTaskStatusFormat#streamTaskStatuses(Stream)}
   */
  @Test
  @DisplayName("Test streamTaskStatuses(Stream); given AttributeValue(String) with s is TASK_ID SS is ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream DynamoDBCompactionTaskStatusFormat.streamTaskStatuses(Stream)"})
  void testStreamTaskStatuses_givenAttributeValueWithSIsTask_idSsIsArrayList() {
    // Arrange
    AttributeValue attributeValue = new AttributeValue(DynamoDBCompactionTaskStatusFormat.TASK_ID);
    attributeValue.setSS(new ArrayList<>());
    attributeValue.addMEntry(DynamoDBCompactionTaskStatusFormat.TASK_ID,
        new AttributeValue(DynamoDBCompactionTaskStatusFormat.TASK_ID));

    HashMap<String, AttributeValue> stringAttributeValueMap = new HashMap<>();
    stringAttributeValueMap.put(DynamoDBCompactionTaskStatusFormat.UPDATE_TYPE, attributeValue);
    stringAttributeValueMap.put(DynamoDBCompactionTaskStatusFormat.TASK_ID,
        new AttributeValue(DynamoDBCompactionTaskStatusFormat.TASK_ID));

    ArrayList<Map<String, AttributeValue>> mapList = new ArrayList<>();
    mapList.add(stringAttributeValueMap);
    Stream<Map<String, AttributeValue>> items = mapList.stream();

    // Act
    Stream<CompactionTaskStatus> actualStreamTaskStatusesResult = DynamoDBCompactionTaskStatusFormat
        .streamTaskStatuses(items);

    // Assert
    assertTrue(actualStreamTaskStatusesResult.limit(5).collect(Collectors.toList()).isEmpty());
  }

  /**
   * Test {@link DynamoDBCompactionTaskStatusFormat#streamTaskStatuses(Stream)}.
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@link DynamoDBCompactionTaskStatusFormat#TASK_ID} is {@link AttributeValue#AttributeValue()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBCompactionTaskStatusFormat#streamTaskStatuses(Stream)}
   */
  @Test
  @DisplayName("Test streamTaskStatuses(Stream); given HashMap() TASK_ID is AttributeValue()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream DynamoDBCompactionTaskStatusFormat.streamTaskStatuses(Stream)"})
  void testStreamTaskStatuses_givenHashMapTask_idIsAttributeValue() {
    // Arrange
    HashMap<String, AttributeValue> stringAttributeValueMap = new HashMap<>();
    stringAttributeValueMap.put(DynamoDBCompactionTaskStatusFormat.UPDATE_TYPE,
        new AttributeValue(DynamoDBCompactionTaskStatusFormat.TASK_ID));
    stringAttributeValueMap.put(DynamoDBCompactionTaskStatusFormat.TASK_ID, new AttributeValue());

    ArrayList<Map<String, AttributeValue>> mapList = new ArrayList<>();
    mapList.add(stringAttributeValueMap);
    Stream<Map<String, AttributeValue>> items = mapList.stream();

    // Act
    Stream<CompactionTaskStatus> actualStreamTaskStatusesResult = DynamoDBCompactionTaskStatusFormat
        .streamTaskStatuses(items);

    // Assert
    assertTrue(actualStreamTaskStatusesResult.limit(5).collect(Collectors.toList()).isEmpty());
  }

  /**
   * Test {@link DynamoDBCompactionTaskStatusFormat#streamTaskStatuses(Stream)}.
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@link DynamoDBCompactionTaskStatusFormat#UPDATE_TYPE} is {@link AttributeValue#AttributeValue(String)} with s is {@link DynamoDBCompactionTaskStatusFormat#TASK_ID}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBCompactionTaskStatusFormat#streamTaskStatuses(Stream)}
   */
  @Test
  @DisplayName("Test streamTaskStatuses(Stream); given HashMap() UPDATE_TYPE is AttributeValue(String) with s is TASK_ID")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream DynamoDBCompactionTaskStatusFormat.streamTaskStatuses(Stream)"})
  void testStreamTaskStatuses_givenHashMapUpdate_typeIsAttributeValueWithSIsTask_id() {
    // Arrange
    HashMap<String, AttributeValue> stringAttributeValueMap = new HashMap<>();
    stringAttributeValueMap.put(DynamoDBCompactionTaskStatusFormat.UPDATE_TYPE,
        new AttributeValue(DynamoDBCompactionTaskStatusFormat.TASK_ID));
    stringAttributeValueMap.put(DynamoDBCompactionTaskStatusFormat.TASK_ID,
        new AttributeValue(DynamoDBCompactionTaskStatusFormat.TASK_ID));

    ArrayList<Map<String, AttributeValue>> mapList = new ArrayList<>();
    mapList.add(stringAttributeValueMap);
    Stream<Map<String, AttributeValue>> items = mapList.stream();

    // Act
    Stream<CompactionTaskStatus> actualStreamTaskStatusesResult = DynamoDBCompactionTaskStatusFormat
        .streamTaskStatuses(items);

    // Assert
    assertTrue(actualStreamTaskStatusesResult.limit(5).collect(Collectors.toList()).isEmpty());
  }

  /**
   * Test {@link DynamoDBCompactionTaskStatusFormat#streamTaskStatuses(Stream)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()} stream.</li>
   *   <li>Then return limit five collect toList Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBCompactionTaskStatusFormat#streamTaskStatuses(Stream)}
   */
  @Test
  @DisplayName("Test streamTaskStatuses(Stream); when ArrayList() stream; then return limit five collect toList Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream DynamoDBCompactionTaskStatusFormat.streamTaskStatuses(Stream)"})
  void testStreamTaskStatuses_whenArrayListStream_thenReturnLimitFiveCollectToListEmpty() {
    // Arrange
    ArrayList<Map<String, AttributeValue>> mapList = new ArrayList<>();
    Stream<Map<String, AttributeValue>> items = mapList.stream();

    // Act
    Stream<CompactionTaskStatus> actualStreamTaskStatusesResult = DynamoDBCompactionTaskStatusFormat
        .streamTaskStatuses(items);

    // Assert
    assertTrue(actualStreamTaskStatusesResult.limit(5).collect(Collectors.toList()).isEmpty());
  }
}
