package sleeper.compaction.tracker.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.nio.ByteBuffer;
import java.time.Duration;
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
import sleeper.core.tracker.compaction.task.CompactionTaskFinishedStatus;
import sleeper.core.tracker.compaction.task.CompactionTaskStatus;

class DynamoDBCompactionTaskStatusFormatDiffblueTest {
  /**
   * Test {@link DynamoDBCompactionTaskStatusFormat#createTaskStartedRecord(CompactionTaskStatus)}.
   *
   * <p>Method under test: {@link
   * DynamoDBCompactionTaskStatusFormat#createTaskStartedRecord(CompactionTaskStatus)}
   */
  @Test
  @DisplayName("Test createTaskStartedRecord(CompactionTaskStatus)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Map DynamoDBCompactionTaskStatusFormat.createTaskStartedRecord(CompactionTaskStatus)"
  })
  void testCreateTaskStartedRecord() {
    // Arrange
    Supplier<Instant> getTimeNow = mock(Supplier.class);
    when(getTimeNow.get())
        .thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    DynamoDBCompactionTaskStatusFormat dynamoDBCompactionTaskStatusFormat =
        new DynamoDBCompactionTaskStatusFormat(1, getTimeNow);

    CompactionTaskStatus taskStatus = mock(CompactionTaskStatus.class);
    when(taskStatus.getTaskId()).thenReturn("42");
    when(taskStatus.getStartTime())
        .thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act
    Map<String, AttributeValue> actualCreateTaskStartedRecordResult =
        dynamoDBCompactionTaskStatusFormat.createTaskStartedRecord(taskStatus);

    // Assert
    verify(getTimeNow).get();
    verify(taskStatus).getStartTime();
    verify(taskStatus).getTaskId();
    assertEquals(5, actualCreateTaskStartedRecordResult.size());
    assertTrue(
        actualCreateTaskStartedRecordResult.containsKey(
            DynamoDBCompactionTaskStatusFormat.EXPIRY_DATE));
    assertTrue(
        actualCreateTaskStartedRecordResult.containsKey(
            DynamoDBCompactionTaskStatusFormat.TASK_ID));
    String string = DynamoDBCompactionTaskStatusFormat.UPDATE_TIME;
    assertTrue(actualCreateTaskStartedRecordResult.containsKey(string));
    assertTrue(
        actualCreateTaskStartedRecordResult.containsKey(
            DynamoDBCompactionTaskStatusFormat.UPDATE_TYPE));
    AttributeValue expectedGetResult = actualCreateTaskStartedRecordResult.get(string);
    assertEquals(
        expectedGetResult,
        actualCreateTaskStartedRecordResult.get(DynamoDBCompactionTaskStatusFormat.START_TIME));
  }

  /**
   * Test {@link DynamoDBCompactionTaskStatusFormat#createTaskFinishedRecord(CompactionTaskStatus)}.
   *
   * <ul>
   *   <li>Then return {@link DynamoDBCompactionTaskStatusFormat#READ_RATE} BOOL is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link
   * DynamoDBCompactionTaskStatusFormat#createTaskFinishedRecord(CompactionTaskStatus)}
   */
  @Test
  @DisplayName(
      "Test createTaskFinishedRecord(CompactionTaskStatus); then return READ_RATE BOOL is 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Map DynamoDBCompactionTaskStatusFormat.createTaskFinishedRecord(CompactionTaskStatus)"
  })
  void testCreateTaskFinishedRecord_thenReturnRead_rateBoolIsNull() {
    // Arrange
    Supplier<Instant> getTimeNow = mock(Supplier.class);
    when(getTimeNow.get())
        .thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    DynamoDBCompactionTaskStatusFormat dynamoDBCompactionTaskStatusFormat =
        new DynamoDBCompactionTaskStatusFormat(1, getTimeNow);

    CompactionTaskStatus taskStatus = mock(CompactionTaskStatus.class);
    when(taskStatus.getFinishedStatus())
        .thenReturn(
            CompactionTaskFinishedStatus.builder()
                .finishTime(
                    LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
                .recordsReadPerSecond(Double.NaN)
                .recordsWrittenPerSecond(10.0d)
                .timeSpentOnJobs(Duration.ofSeconds(1L))
                .totalJobRuns(1)
                .totalRecordsRead(1L)
                .totalRecordsWritten(1L)
                .build());
    when(taskStatus.getTaskId()).thenReturn("42");
    when(taskStatus.getStartTime())
        .thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act
    Map<String, AttributeValue> actualCreateTaskFinishedRecordResult =
        dynamoDBCompactionTaskStatusFormat.createTaskFinishedRecord(taskStatus);

    // Assert
    verify(getTimeNow).get();
    verify(taskStatus, atLeast(1)).getFinishedStatus();
    verify(taskStatus).getStartTime();
    verify(taskStatus).getTaskId();
    assertEquals(12, actualCreateTaskFinishedRecordResult.size());
    AttributeValue getResult =
        actualCreateTaskFinishedRecordResult.get(DynamoDBCompactionTaskStatusFormat.READ_RATE);
    assertNull(getResult.getBOOL());
    assertNull(getResult.isBOOL());
    assertNull(getResult.getN());
    assertNull(getResult.getS());
    assertNull(getResult.getB());
    assertNull(getResult.getL());
    assertNull(getResult.getNS());
    assertNull(getResult.getSS());
    assertNull(getResult.getBS());
    assertNull(getResult.getM());
    assertTrue(getResult.getNULL());
    assertTrue(getResult.isNULL());
    assertTrue(
        actualCreateTaskFinishedRecordResult.containsKey(
            DynamoDBCompactionTaskStatusFormat.EXPIRY_DATE));
    assertTrue(
        actualCreateTaskFinishedRecordResult.containsKey(
            DynamoDBCompactionTaskStatusFormat.FINISH_TIME));
    assertTrue(
        actualCreateTaskFinishedRecordResult.containsKey(
            DynamoDBCompactionTaskStatusFormat.MILLIS_SPENT_ON_JOBS));
    assertTrue(
        actualCreateTaskFinishedRecordResult.containsKey(
            DynamoDBCompactionTaskStatusFormat.NUMBER_OF_JOBS));
    assertTrue(
        actualCreateTaskFinishedRecordResult.containsKey(
            DynamoDBCompactionTaskStatusFormat.RECORDS_READ));
    assertTrue(
        actualCreateTaskFinishedRecordResult.containsKey(
            DynamoDBCompactionTaskStatusFormat.RECORDS_WRITTEN));
    assertTrue(
        actualCreateTaskFinishedRecordResult.containsKey(
            DynamoDBCompactionTaskStatusFormat.START_TIME));
    assertTrue(
        actualCreateTaskFinishedRecordResult.containsKey(
            DynamoDBCompactionTaskStatusFormat.TASK_ID));
    assertTrue(
        actualCreateTaskFinishedRecordResult.containsKey(
            DynamoDBCompactionTaskStatusFormat.UPDATE_TIME));
    assertTrue(
        actualCreateTaskFinishedRecordResult.containsKey(
            DynamoDBCompactionTaskStatusFormat.WRITE_RATE));
  }

  /**
   * Test {@link DynamoDBCompactionTaskStatusFormat#createTaskFinishedRecord(CompactionTaskStatus)}.
   *
   * <ul>
   *   <li>Then return {@link DynamoDBCompactionTaskStatusFormat#START_TIME} is {@link
   *       DynamoDBCompactionTaskStatusFormat#FINISH_TIME}.
   * </ul>
   *
   * <p>Method under test: {@link
   * DynamoDBCompactionTaskStatusFormat#createTaskFinishedRecord(CompactionTaskStatus)}
   */
  @Test
  @DisplayName(
      "Test createTaskFinishedRecord(CompactionTaskStatus); then return START_TIME is FINISH_TIME")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Map DynamoDBCompactionTaskStatusFormat.createTaskFinishedRecord(CompactionTaskStatus)"
  })
  void testCreateTaskFinishedRecord_thenReturnStart_timeIsFinish_time() {
    // Arrange
    Supplier<Instant> getTimeNow = mock(Supplier.class);
    when(getTimeNow.get())
        .thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    DynamoDBCompactionTaskStatusFormat dynamoDBCompactionTaskStatusFormat =
        new DynamoDBCompactionTaskStatusFormat(1, getTimeNow);

    CompactionTaskStatus taskStatus = mock(CompactionTaskStatus.class);
    when(taskStatus.getFinishedStatus())
        .thenReturn(
            CompactionTaskFinishedStatus.builder()
                .finishTime(
                    LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
                .recordsReadPerSecond(10.0d)
                .recordsWrittenPerSecond(10.0d)
                .timeSpentOnJobs(Duration.ofSeconds(1L))
                .totalJobRuns(1)
                .totalRecordsRead(1L)
                .totalRecordsWritten(1L)
                .build());
    when(taskStatus.getTaskId()).thenReturn("42");
    when(taskStatus.getStartTime())
        .thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act
    Map<String, AttributeValue> actualCreateTaskFinishedRecordResult =
        dynamoDBCompactionTaskStatusFormat.createTaskFinishedRecord(taskStatus);

    // Assert
    verify(getTimeNow).get();
    verify(taskStatus, atLeast(1)).getFinishedStatus();
    verify(taskStatus).getStartTime();
    verify(taskStatus).getTaskId();
    assertEquals(12, actualCreateTaskFinishedRecordResult.size());
    String string = DynamoDBCompactionTaskStatusFormat.FINISH_TIME;
    assertTrue(actualCreateTaskFinishedRecordResult.containsKey(string));
    assertTrue(
        actualCreateTaskFinishedRecordResult.containsKey(
            DynamoDBCompactionTaskStatusFormat.MILLIS_SPENT_ON_JOBS));
    String string2 = DynamoDBCompactionTaskStatusFormat.RECORDS_WRITTEN;
    assertTrue(actualCreateTaskFinishedRecordResult.containsKey(string2));
    assertTrue(
        actualCreateTaskFinishedRecordResult.containsKey(
            DynamoDBCompactionTaskStatusFormat.TASK_ID));
    String string3 = DynamoDBCompactionTaskStatusFormat.WRITE_RATE;
    assertTrue(actualCreateTaskFinishedRecordResult.containsKey(string3));
    AttributeValue getResult = actualCreateTaskFinishedRecordResult.get(string);
    assertEquals(
        getResult,
        actualCreateTaskFinishedRecordResult.get(DynamoDBCompactionTaskStatusFormat.START_TIME));
    assertEquals(
        getResult,
        actualCreateTaskFinishedRecordResult.get(DynamoDBCompactionTaskStatusFormat.UPDATE_TIME));
    AttributeValue getResult2 = actualCreateTaskFinishedRecordResult.get(string2);
    assertEquals(
        getResult2,
        actualCreateTaskFinishedRecordResult.get(DynamoDBCompactionTaskStatusFormat.EXPIRY_DATE));
    assertEquals(
        getResult2,
        actualCreateTaskFinishedRecordResult.get(
            DynamoDBCompactionTaskStatusFormat.NUMBER_OF_JOBS));
    assertEquals(
        getResult2,
        actualCreateTaskFinishedRecordResult.get(DynamoDBCompactionTaskStatusFormat.RECORDS_READ));
    AttributeValue expectedGetResult = actualCreateTaskFinishedRecordResult.get(string3);
    assertEquals(
        expectedGetResult,
        actualCreateTaskFinishedRecordResult.get(DynamoDBCompactionTaskStatusFormat.READ_RATE));
  }

  /**
   * Test {@link DynamoDBCompactionTaskStatusFormat#streamTaskStatuses(Stream)}.
   *
   * <p>Method under test: {@link DynamoDBCompactionTaskStatusFormat#streamTaskStatuses(Stream)}
   */
  @Test
  @DisplayName("Test streamTaskStatuses(Stream)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Stream DynamoDBCompactionTaskStatusFormat.streamTaskStatuses(Stream)"})
  void testStreamTaskStatuses() {
    // Arrange
    AttributeValue attributeValue = new AttributeValue(DynamoDBCompactionTaskStatusFormat.TASK_ID);
    attributeValue.addMEntry(DynamoDBCompactionTaskStatusFormat.UPDATE_TYPE, new AttributeValue());

    HashMap<String, AttributeValue> stringAttributeValueMap = new HashMap<>();
    stringAttributeValueMap.put(DynamoDBCompactionTaskStatusFormat.UPDATE_TYPE, attributeValue);
    stringAttributeValueMap.put(DynamoDBCompactionTaskStatusFormat.TASK_ID, new AttributeValue());

    ArrayList<Map<String, AttributeValue>> mapList = new ArrayList<>();
    mapList.add(stringAttributeValueMap);
    Stream<Map<String, AttributeValue>> items = mapList.stream();

    // Act
    Stream<CompactionTaskStatus> actualStreamTaskStatusesResult =
        DynamoDBCompactionTaskStatusFormat.streamTaskStatuses(items);

    // Assert
    assertTrue(actualStreamTaskStatusesResult.limit(5).collect(Collectors.toList()).isEmpty());
  }

  /**
   * Test {@link DynamoDBCompactionTaskStatusFormat#streamTaskStatuses(Stream)}.
   *
   * <ul>
   *   <li>Given {@code A}.
   *   <li>Then return limit five collect toList Empty.
   * </ul>
   *
   * <p>Method under test: {@link DynamoDBCompactionTaskStatusFormat#streamTaskStatuses(Stream)}
   */
  @Test
  @DisplayName(
      "Test streamTaskStatuses(Stream); given 'A'; then return limit five collect toList Empty")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Stream DynamoDBCompactionTaskStatusFormat.streamTaskStatuses(Stream)"})
  void testStreamTaskStatuses_givenA_thenReturnLimitFiveCollectToListEmpty() {
    // Arrange
    AttributeValue attributeValue = new AttributeValue(DynamoDBCompactionTaskStatusFormat.TASK_ID);
    attributeValue.setB(ByteBuffer.wrap(new byte[] {'A', -1, 'A', -1, 'A', -1, 'A', -1}));
    attributeValue.addMEntry(DynamoDBCompactionTaskStatusFormat.UPDATE_TYPE, new AttributeValue());

    HashMap<String, AttributeValue> stringAttributeValueMap = new HashMap<>();
    stringAttributeValueMap.put(DynamoDBCompactionTaskStatusFormat.UPDATE_TYPE, attributeValue);
    stringAttributeValueMap.put(DynamoDBCompactionTaskStatusFormat.TASK_ID, new AttributeValue());

    ArrayList<Map<String, AttributeValue>> mapList = new ArrayList<>();
    mapList.add(stringAttributeValueMap);
    Stream<Map<String, AttributeValue>> items = mapList.stream();

    // Act
    Stream<CompactionTaskStatus> actualStreamTaskStatusesResult =
        DynamoDBCompactionTaskStatusFormat.streamTaskStatuses(items);

    // Assert
    assertTrue(actualStreamTaskStatusesResult.limit(5).collect(Collectors.toList()).isEmpty());
  }

  /**
   * Test {@link DynamoDBCompactionTaskStatusFormat#streamTaskStatuses(Stream)}.
   *
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link AttributeValue#AttributeValue()}.
   * </ul>
   *
   * <p>Method under test: {@link DynamoDBCompactionTaskStatusFormat#streamTaskStatuses(Stream)}
   */
  @Test
  @DisplayName("Test streamTaskStatuses(Stream); given ArrayList() add AttributeValue()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Stream DynamoDBCompactionTaskStatusFormat.streamTaskStatuses(Stream)"})
  void testStreamTaskStatuses_givenArrayListAddAttributeValue() {
    // Arrange
    ArrayList<AttributeValue> l = new ArrayList<>();
    l.add(new AttributeValue());

    AttributeValue attributeValue = new AttributeValue(DynamoDBCompactionTaskStatusFormat.TASK_ID);
    attributeValue.setL(l);
    attributeValue.addMEntry(DynamoDBCompactionTaskStatusFormat.UPDATE_TYPE, new AttributeValue());

    HashMap<String, AttributeValue> stringAttributeValueMap = new HashMap<>();
    stringAttributeValueMap.put(DynamoDBCompactionTaskStatusFormat.UPDATE_TYPE, attributeValue);
    stringAttributeValueMap.put(DynamoDBCompactionTaskStatusFormat.TASK_ID, new AttributeValue());

    ArrayList<Map<String, AttributeValue>> mapList = new ArrayList<>();
    mapList.add(stringAttributeValueMap);
    Stream<Map<String, AttributeValue>> items = mapList.stream();

    // Act
    Stream<CompactionTaskStatus> actualStreamTaskStatusesResult =
        DynamoDBCompactionTaskStatusFormat.streamTaskStatuses(items);

    // Assert
    assertTrue(actualStreamTaskStatusesResult.limit(5).collect(Collectors.toList()).isEmpty());
  }

  /**
   * Test {@link DynamoDBCompactionTaskStatusFormat#streamTaskStatuses(Stream)}.
   *
   * <ul>
   *   <li>Given {@link AttributeValue#AttributeValue(String)} with s is {@link
   *       DynamoDBCompactionTaskStatusFormat#TASK_ID} BOOL is {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DynamoDBCompactionTaskStatusFormat#streamTaskStatuses(Stream)}
   */
  @Test
  @DisplayName(
      "Test streamTaskStatuses(Stream); given AttributeValue(String) with s is TASK_ID BOOL is 'true'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Stream DynamoDBCompactionTaskStatusFormat.streamTaskStatuses(Stream)"})
  void testStreamTaskStatuses_givenAttributeValueWithSIsTask_idBoolIsTrue() {
    // Arrange
    AttributeValue attributeValue = new AttributeValue(DynamoDBCompactionTaskStatusFormat.TASK_ID);
    attributeValue.setBOOL(true);
    attributeValue.addMEntry(DynamoDBCompactionTaskStatusFormat.UPDATE_TYPE, new AttributeValue());

    HashMap<String, AttributeValue> stringAttributeValueMap = new HashMap<>();
    stringAttributeValueMap.put(DynamoDBCompactionTaskStatusFormat.UPDATE_TYPE, attributeValue);
    stringAttributeValueMap.put(DynamoDBCompactionTaskStatusFormat.TASK_ID, new AttributeValue());

    ArrayList<Map<String, AttributeValue>> mapList = new ArrayList<>();
    mapList.add(stringAttributeValueMap);
    Stream<Map<String, AttributeValue>> items = mapList.stream();

    // Act
    Stream<CompactionTaskStatus> actualStreamTaskStatusesResult =
        DynamoDBCompactionTaskStatusFormat.streamTaskStatuses(items);

    // Assert
    assertTrue(actualStreamTaskStatusesResult.limit(5).collect(Collectors.toList()).isEmpty());
  }

  /**
   * Test {@link DynamoDBCompactionTaskStatusFormat#streamTaskStatuses(Stream)}.
   *
   * <ul>
   *   <li>Given {@link AttributeValue#AttributeValue(String)} with s is {@link
   *       DynamoDBCompactionTaskStatusFormat#TASK_ID} BS is {@link ArrayList#ArrayList()}.
   * </ul>
   *
   * <p>Method under test: {@link DynamoDBCompactionTaskStatusFormat#streamTaskStatuses(Stream)}
   */
  @Test
  @DisplayName(
      "Test streamTaskStatuses(Stream); given AttributeValue(String) with s is TASK_ID BS is ArrayList()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Stream DynamoDBCompactionTaskStatusFormat.streamTaskStatuses(Stream)"})
  void testStreamTaskStatuses_givenAttributeValueWithSIsTask_idBsIsArrayList() {
    // Arrange
    AttributeValue attributeValue = new AttributeValue(DynamoDBCompactionTaskStatusFormat.TASK_ID);
    attributeValue.setBS(new ArrayList<>());
    attributeValue.addMEntry(DynamoDBCompactionTaskStatusFormat.UPDATE_TYPE, new AttributeValue());

    HashMap<String, AttributeValue> stringAttributeValueMap = new HashMap<>();
    stringAttributeValueMap.put(DynamoDBCompactionTaskStatusFormat.UPDATE_TYPE, attributeValue);
    stringAttributeValueMap.put(DynamoDBCompactionTaskStatusFormat.TASK_ID, new AttributeValue());

    ArrayList<Map<String, AttributeValue>> mapList = new ArrayList<>();
    mapList.add(stringAttributeValueMap);
    Stream<Map<String, AttributeValue>> items = mapList.stream();

    // Act
    Stream<CompactionTaskStatus> actualStreamTaskStatusesResult =
        DynamoDBCompactionTaskStatusFormat.streamTaskStatuses(items);

    // Assert
    assertTrue(actualStreamTaskStatusesResult.limit(5).collect(Collectors.toList()).isEmpty());
  }

  /**
   * Test {@link DynamoDBCompactionTaskStatusFormat#streamTaskStatuses(Stream)}.
   *
   * <ul>
   *   <li>Given {@link AttributeValue#AttributeValue(String)} with s is {@link
   *       DynamoDBCompactionTaskStatusFormat#TASK_ID} L is {@link ArrayList#ArrayList()}.
   * </ul>
   *
   * <p>Method under test: {@link DynamoDBCompactionTaskStatusFormat#streamTaskStatuses(Stream)}
   */
  @Test
  @DisplayName(
      "Test streamTaskStatuses(Stream); given AttributeValue(String) with s is TASK_ID L is ArrayList()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Stream DynamoDBCompactionTaskStatusFormat.streamTaskStatuses(Stream)"})
  void testStreamTaskStatuses_givenAttributeValueWithSIsTask_idLIsArrayList() {
    // Arrange
    AttributeValue attributeValue = new AttributeValue(DynamoDBCompactionTaskStatusFormat.TASK_ID);
    attributeValue.setL(new ArrayList<>());
    attributeValue.addMEntry(DynamoDBCompactionTaskStatusFormat.UPDATE_TYPE, new AttributeValue());

    HashMap<String, AttributeValue> stringAttributeValueMap = new HashMap<>();
    stringAttributeValueMap.put(DynamoDBCompactionTaskStatusFormat.UPDATE_TYPE, attributeValue);
    stringAttributeValueMap.put(DynamoDBCompactionTaskStatusFormat.TASK_ID, new AttributeValue());

    ArrayList<Map<String, AttributeValue>> mapList = new ArrayList<>();
    mapList.add(stringAttributeValueMap);
    Stream<Map<String, AttributeValue>> items = mapList.stream();

    // Act
    Stream<CompactionTaskStatus> actualStreamTaskStatusesResult =
        DynamoDBCompactionTaskStatusFormat.streamTaskStatuses(items);

    // Assert
    assertTrue(actualStreamTaskStatusesResult.limit(5).collect(Collectors.toList()).isEmpty());
  }

  /**
   * Test {@link DynamoDBCompactionTaskStatusFormat#streamTaskStatuses(Stream)}.
   *
   * <ul>
   *   <li>Given {@link AttributeValue#AttributeValue(String)} with s is {@link
   *       DynamoDBCompactionTaskStatusFormat#TASK_ID} M is {@link HashMap#HashMap()}.
   * </ul>
   *
   * <p>Method under test: {@link DynamoDBCompactionTaskStatusFormat#streamTaskStatuses(Stream)}
   */
  @Test
  @DisplayName(
      "Test streamTaskStatuses(Stream); given AttributeValue(String) with s is TASK_ID M is HashMap()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Stream DynamoDBCompactionTaskStatusFormat.streamTaskStatuses(Stream)"})
  void testStreamTaskStatuses_givenAttributeValueWithSIsTask_idMIsHashMap() {
    // Arrange
    AttributeValue attributeValue = new AttributeValue(DynamoDBCompactionTaskStatusFormat.TASK_ID);
    attributeValue.setM(new HashMap<>());

    HashMap<String, AttributeValue> stringAttributeValueMap = new HashMap<>();
    stringAttributeValueMap.put(DynamoDBCompactionTaskStatusFormat.UPDATE_TYPE, attributeValue);
    stringAttributeValueMap.put(DynamoDBCompactionTaskStatusFormat.TASK_ID, new AttributeValue());

    ArrayList<Map<String, AttributeValue>> mapList = new ArrayList<>();
    mapList.add(stringAttributeValueMap);
    Stream<Map<String, AttributeValue>> items = mapList.stream();

    // Act
    Stream<CompactionTaskStatus> actualStreamTaskStatusesResult =
        DynamoDBCompactionTaskStatusFormat.streamTaskStatuses(items);

    // Assert
    assertTrue(actualStreamTaskStatusesResult.limit(5).collect(Collectors.toList()).isEmpty());
  }

  /**
   * Test {@link DynamoDBCompactionTaskStatusFormat#streamTaskStatuses(Stream)}.
   *
   * <ul>
   *   <li>Given {@link AttributeValue#AttributeValue(String)} with s is {@link
   *       DynamoDBCompactionTaskStatusFormat#TASK_ID} N is {@link
   *       DynamoDBCompactionTaskStatusFormat#TASK_ID}.
   * </ul>
   *
   * <p>Method under test: {@link DynamoDBCompactionTaskStatusFormat#streamTaskStatuses(Stream)}
   */
  @Test
  @DisplayName(
      "Test streamTaskStatuses(Stream); given AttributeValue(String) with s is TASK_ID N is TASK_ID")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Stream DynamoDBCompactionTaskStatusFormat.streamTaskStatuses(Stream)"})
  void testStreamTaskStatuses_givenAttributeValueWithSIsTask_idNIsTask_id() {
    // Arrange
    AttributeValue attributeValue = new AttributeValue(DynamoDBCompactionTaskStatusFormat.TASK_ID);
    attributeValue.setN(DynamoDBCompactionTaskStatusFormat.TASK_ID);
    attributeValue.addMEntry(DynamoDBCompactionTaskStatusFormat.UPDATE_TYPE, new AttributeValue());

    HashMap<String, AttributeValue> stringAttributeValueMap = new HashMap<>();
    stringAttributeValueMap.put(DynamoDBCompactionTaskStatusFormat.UPDATE_TYPE, attributeValue);
    stringAttributeValueMap.put(DynamoDBCompactionTaskStatusFormat.TASK_ID, new AttributeValue());

    ArrayList<Map<String, AttributeValue>> mapList = new ArrayList<>();
    mapList.add(stringAttributeValueMap);
    Stream<Map<String, AttributeValue>> items = mapList.stream();

    // Act
    Stream<CompactionTaskStatus> actualStreamTaskStatusesResult =
        DynamoDBCompactionTaskStatusFormat.streamTaskStatuses(items);

    // Assert
    assertTrue(actualStreamTaskStatusesResult.limit(5).collect(Collectors.toList()).isEmpty());
  }

  /**
   * Test {@link DynamoDBCompactionTaskStatusFormat#streamTaskStatuses(Stream)}.
   *
   * <ul>
   *   <li>Given {@link AttributeValue#AttributeValue(String)} with s is {@link
   *       DynamoDBCompactionTaskStatusFormat#TASK_ID} NS is {@link ArrayList#ArrayList()}.
   * </ul>
   *
   * <p>Method under test: {@link DynamoDBCompactionTaskStatusFormat#streamTaskStatuses(Stream)}
   */
  @Test
  @DisplayName(
      "Test streamTaskStatuses(Stream); given AttributeValue(String) with s is TASK_ID NS is ArrayList()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Stream DynamoDBCompactionTaskStatusFormat.streamTaskStatuses(Stream)"})
  void testStreamTaskStatuses_givenAttributeValueWithSIsTask_idNsIsArrayList() {
    // Arrange
    AttributeValue attributeValue = new AttributeValue(DynamoDBCompactionTaskStatusFormat.TASK_ID);
    attributeValue.setNS(new ArrayList<>());
    attributeValue.addMEntry(DynamoDBCompactionTaskStatusFormat.UPDATE_TYPE, new AttributeValue());

    HashMap<String, AttributeValue> stringAttributeValueMap = new HashMap<>();
    stringAttributeValueMap.put(DynamoDBCompactionTaskStatusFormat.UPDATE_TYPE, attributeValue);
    stringAttributeValueMap.put(DynamoDBCompactionTaskStatusFormat.TASK_ID, new AttributeValue());

    ArrayList<Map<String, AttributeValue>> mapList = new ArrayList<>();
    mapList.add(stringAttributeValueMap);
    Stream<Map<String, AttributeValue>> items = mapList.stream();

    // Act
    Stream<CompactionTaskStatus> actualStreamTaskStatusesResult =
        DynamoDBCompactionTaskStatusFormat.streamTaskStatuses(items);

    // Assert
    assertTrue(actualStreamTaskStatusesResult.limit(5).collect(Collectors.toList()).isEmpty());
  }

  /**
   * Test {@link DynamoDBCompactionTaskStatusFormat#streamTaskStatuses(Stream)}.
   *
   * <ul>
   *   <li>Given {@link AttributeValue#AttributeValue(String)} with s is {@link
   *       DynamoDBCompactionTaskStatusFormat#TASK_ID} NULL is {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DynamoDBCompactionTaskStatusFormat#streamTaskStatuses(Stream)}
   */
  @Test
  @DisplayName(
      "Test streamTaskStatuses(Stream); given AttributeValue(String) with s is TASK_ID NULL is 'true'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Stream DynamoDBCompactionTaskStatusFormat.streamTaskStatuses(Stream)"})
  void testStreamTaskStatuses_givenAttributeValueWithSIsTask_idNullIsTrue() {
    // Arrange
    AttributeValue attributeValue = new AttributeValue(DynamoDBCompactionTaskStatusFormat.TASK_ID);
    attributeValue.setNULL(true);
    attributeValue.addMEntry(DynamoDBCompactionTaskStatusFormat.UPDATE_TYPE, new AttributeValue());

    HashMap<String, AttributeValue> stringAttributeValueMap = new HashMap<>();
    stringAttributeValueMap.put(DynamoDBCompactionTaskStatusFormat.UPDATE_TYPE, attributeValue);
    stringAttributeValueMap.put(DynamoDBCompactionTaskStatusFormat.TASK_ID, new AttributeValue());

    ArrayList<Map<String, AttributeValue>> mapList = new ArrayList<>();
    mapList.add(stringAttributeValueMap);
    Stream<Map<String, AttributeValue>> items = mapList.stream();

    // Act
    Stream<CompactionTaskStatus> actualStreamTaskStatusesResult =
        DynamoDBCompactionTaskStatusFormat.streamTaskStatuses(items);

    // Assert
    assertTrue(actualStreamTaskStatusesResult.limit(5).collect(Collectors.toList()).isEmpty());
  }

  /**
   * Test {@link DynamoDBCompactionTaskStatusFormat#streamTaskStatuses(Stream)}.
   *
   * <ul>
   *   <li>Given {@link AttributeValue#AttributeValue(String)} with s is {@link
   *       DynamoDBCompactionTaskStatusFormat#TASK_ID} SS is {@link ArrayList#ArrayList()}.
   * </ul>
   *
   * <p>Method under test: {@link DynamoDBCompactionTaskStatusFormat#streamTaskStatuses(Stream)}
   */
  @Test
  @DisplayName(
      "Test streamTaskStatuses(Stream); given AttributeValue(String) with s is TASK_ID SS is ArrayList()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Stream DynamoDBCompactionTaskStatusFormat.streamTaskStatuses(Stream)"})
  void testStreamTaskStatuses_givenAttributeValueWithSIsTask_idSsIsArrayList() {
    // Arrange
    AttributeValue attributeValue = new AttributeValue(DynamoDBCompactionTaskStatusFormat.TASK_ID);
    attributeValue.setSS(new ArrayList<>());
    attributeValue.addMEntry(DynamoDBCompactionTaskStatusFormat.UPDATE_TYPE, new AttributeValue());

    HashMap<String, AttributeValue> stringAttributeValueMap = new HashMap<>();
    stringAttributeValueMap.put(DynamoDBCompactionTaskStatusFormat.UPDATE_TYPE, attributeValue);
    stringAttributeValueMap.put(DynamoDBCompactionTaskStatusFormat.TASK_ID, new AttributeValue());

    ArrayList<Map<String, AttributeValue>> mapList = new ArrayList<>();
    mapList.add(stringAttributeValueMap);
    Stream<Map<String, AttributeValue>> items = mapList.stream();

    // Act
    Stream<CompactionTaskStatus> actualStreamTaskStatusesResult =
        DynamoDBCompactionTaskStatusFormat.streamTaskStatuses(items);

    // Assert
    assertTrue(actualStreamTaskStatusesResult.limit(5).collect(Collectors.toList()).isEmpty());
  }

  /**
   * Test {@link DynamoDBCompactionTaskStatusFormat#streamTaskStatuses(Stream)}.
   *
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@link DynamoDBCompactionTaskStatusFormat#TASK_ID} is
   *       {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DynamoDBCompactionTaskStatusFormat#streamTaskStatuses(Stream)}
   */
  @Test
  @DisplayName("Test streamTaskStatuses(Stream); given HashMap() TASK_ID is 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Stream DynamoDBCompactionTaskStatusFormat.streamTaskStatuses(Stream)"})
  void testStreamTaskStatuses_givenHashMapTask_idIsNull() {
    // Arrange
    HashMap<String, AttributeValue> stringAttributeValueMap = new HashMap<>();
    stringAttributeValueMap.put(
        DynamoDBCompactionTaskStatusFormat.UPDATE_TYPE,
        new AttributeValue(DynamoDBCompactionTaskStatusFormat.TASK_ID));
    stringAttributeValueMap.put(DynamoDBCompactionTaskStatusFormat.TASK_ID, null);

    ArrayList<Map<String, AttributeValue>> mapList = new ArrayList<>();
    mapList.add(stringAttributeValueMap);
    Stream<Map<String, AttributeValue>> items = mapList.stream();

    // Act
    Stream<CompactionTaskStatus> actualStreamTaskStatusesResult =
        DynamoDBCompactionTaskStatusFormat.streamTaskStatuses(items);

    // Assert
    assertTrue(actualStreamTaskStatusesResult.limit(5).collect(Collectors.toList()).isEmpty());
  }

  /**
   * Test {@link DynamoDBCompactionTaskStatusFormat#streamTaskStatuses(Stream)}.
   *
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@link DynamoDBCompactionTaskStatusFormat#UPDATE_TYPE} is
   *       {@link AttributeValue#AttributeValue(String)} with s is {@link
   *       DynamoDBCompactionTaskStatusFormat#TASK_ID}.
   * </ul>
   *
   * <p>Method under test: {@link DynamoDBCompactionTaskStatusFormat#streamTaskStatuses(Stream)}
   */
  @Test
  @DisplayName(
      "Test streamTaskStatuses(Stream); given HashMap() UPDATE_TYPE is AttributeValue(String) with s is TASK_ID")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Stream DynamoDBCompactionTaskStatusFormat.streamTaskStatuses(Stream)"})
  void testStreamTaskStatuses_givenHashMapUpdate_typeIsAttributeValueWithSIsTask_id() {
    // Arrange
    HashMap<String, AttributeValue> stringAttributeValueMap = new HashMap<>();
    stringAttributeValueMap.put(
        DynamoDBCompactionTaskStatusFormat.UPDATE_TYPE,
        new AttributeValue(DynamoDBCompactionTaskStatusFormat.TASK_ID));
    stringAttributeValueMap.put(DynamoDBCompactionTaskStatusFormat.TASK_ID, new AttributeValue());

    ArrayList<Map<String, AttributeValue>> mapList = new ArrayList<>();
    mapList.add(stringAttributeValueMap);
    Stream<Map<String, AttributeValue>> items = mapList.stream();

    // Act
    Stream<CompactionTaskStatus> actualStreamTaskStatusesResult =
        DynamoDBCompactionTaskStatusFormat.streamTaskStatuses(items);

    // Assert
    assertTrue(actualStreamTaskStatusesResult.limit(5).collect(Collectors.toList()).isEmpty());
  }

  /**
   * Test {@link DynamoDBCompactionTaskStatusFormat#streamTaskStatuses(Stream)}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()} stream.
   *   <li>Then return limit five collect toList Empty.
   * </ul>
   *
   * <p>Method under test: {@link DynamoDBCompactionTaskStatusFormat#streamTaskStatuses(Stream)}
   */
  @Test
  @DisplayName(
      "Test streamTaskStatuses(Stream); when ArrayList() stream; then return limit five collect toList Empty")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Stream DynamoDBCompactionTaskStatusFormat.streamTaskStatuses(Stream)"})
  void testStreamTaskStatuses_whenArrayListStream_thenReturnLimitFiveCollectToListEmpty() {
    // Arrange
    ArrayList<Map<String, AttributeValue>> mapList = new ArrayList<>();
    Stream<Map<String, AttributeValue>> items = mapList.stream();

    // Act
    Stream<CompactionTaskStatus> actualStreamTaskStatusesResult =
        DynamoDBCompactionTaskStatusFormat.streamTaskStatuses(items);

    // Assert
    assertTrue(actualStreamTaskStatusesResult.limit(5).collect(Collectors.toList()).isEmpty());
  }
}
