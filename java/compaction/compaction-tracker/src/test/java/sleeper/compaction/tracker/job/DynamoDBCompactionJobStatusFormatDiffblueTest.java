package sleeper.compaction.tracker.job;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.statestore.AssignJobIdRequest;
import sleeper.core.tracker.compaction.job.query.CompactionJobStatus;
import sleeper.core.tracker.compaction.job.update.CompactionJobCommittedEvent;
import sleeper.core.tracker.compaction.job.update.CompactionJobCreatedEvent;
import sleeper.core.tracker.compaction.job.update.CompactionJobFailedEvent;
import sleeper.core.tracker.compaction.job.update.CompactionJobFailedEvent.Builder;
import sleeper.core.tracker.compaction.job.update.CompactionJobFinishedEvent;
import sleeper.core.tracker.compaction.job.update.CompactionJobStartedEvent;
import sleeper.core.tracker.job.run.RecordsProcessed;
import sleeper.dynamodb.tools.DynamoDBRecordBuilder;

class DynamoDBCompactionJobStatusFormatDiffblueTest {
  /**
   * Test {@link DynamoDBCompactionJobStatusFormat#createFilesAssignedUpdate(AssignJobIdRequest, DynamoDBRecordBuilder)}.
   * <ul>
   *   <li>Given {@code 42}.</li>
   *   <li>Then {@link DynamoDBRecordBuilder} (default constructor) build size is three.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBCompactionJobStatusFormat#createFilesAssignedUpdate(AssignJobIdRequest, DynamoDBRecordBuilder)}
   */
  @Test
  @DisplayName("Test createFilesAssignedUpdate(AssignJobIdRequest, DynamoDBRecordBuilder); given '42'; then DynamoDBRecordBuilder (default constructor) build size is three")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "Map DynamoDBCompactionJobStatusFormat.createFilesAssignedUpdate(AssignJobIdRequest, DynamoDBRecordBuilder)"})
  void testCreateFilesAssignedUpdate_given42_thenDynamoDBRecordBuilderBuildSizeIsThree() {
    // Arrange
    AssignJobIdRequest request = mock(AssignJobIdRequest.class);
    when(request.getPartitionId()).thenReturn("42");
    when(request.getFilenames()).thenReturn(new ArrayList<>());
    DynamoDBRecordBuilder builder = new DynamoDBRecordBuilder();

    // Act
    Map<String, AttributeValue> actualCreateFilesAssignedUpdateResult = DynamoDBCompactionJobStatusFormat
        .createFilesAssignedUpdate(request, builder);

    // Assert
    verify(request).getFilenames();
    verify(request).getPartitionId();
    Map<String, AttributeValue> buildResult = builder.build();
    assertEquals(3, buildResult.size());
    assertEquals(3, actualCreateFilesAssignedUpdateResult.size());
    assertTrue(buildResult.containsKey("InputFilesCount"));
    assertTrue(buildResult.containsKey("PartitionId"));
    assertTrue(buildResult.containsKey("UpdateType"));
    assertTrue(actualCreateFilesAssignedUpdateResult.containsKey("InputFilesCount"));
    assertTrue(actualCreateFilesAssignedUpdateResult.containsKey("PartitionId"));
    assertTrue(actualCreateFilesAssignedUpdateResult.containsKey("UpdateType"));
  }

  /**
   * Test {@link DynamoDBCompactionJobStatusFormat#createFilesAssignedUpdate(AssignJobIdRequest, DynamoDBRecordBuilder)}.
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBCompactionJobStatusFormat#createFilesAssignedUpdate(AssignJobIdRequest, DynamoDBRecordBuilder)}
   */
  @Test
  @DisplayName("Test createFilesAssignedUpdate(AssignJobIdRequest, DynamoDBRecordBuilder); then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "Map DynamoDBCompactionJobStatusFormat.createFilesAssignedUpdate(AssignJobIdRequest, DynamoDBRecordBuilder)"})
  void testCreateFilesAssignedUpdate_thenThrowIllegalArgumentException() {
    // Arrange
    AssignJobIdRequest request = mock(AssignJobIdRequest.class);
    when(request.getPartitionId()).thenThrow(new IllegalArgumentException("UpdateType"));

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> DynamoDBCompactionJobStatusFormat.createFilesAssignedUpdate(request, new DynamoDBRecordBuilder()));
    verify(request).getPartitionId();
  }

  /**
   * Test {@link DynamoDBCompactionJobStatusFormat#createJobCreated(CompactionJobCreatedEvent, DynamoDBRecordBuilder)}.
   * <ul>
   *   <li>Then {@link DynamoDBRecordBuilder} (default constructor) build size is three.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBCompactionJobStatusFormat#createJobCreated(CompactionJobCreatedEvent, DynamoDBRecordBuilder)}
   */
  @Test
  @DisplayName("Test createJobCreated(CompactionJobCreatedEvent, DynamoDBRecordBuilder); then DynamoDBRecordBuilder (default constructor) build size is three")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "Map DynamoDBCompactionJobStatusFormat.createJobCreated(CompactionJobCreatedEvent, DynamoDBRecordBuilder)"})
  void testCreateJobCreated_thenDynamoDBRecordBuilderBuildSizeIsThree() {
    // Arrange
    CompactionJobCreatedEvent event = CompactionJobCreatedEvent.builder()
        .inputFilesCount(3)
        .jobId("42")
        .partitionId("42")
        .tableId("42")
        .build();
    DynamoDBRecordBuilder builder = new DynamoDBRecordBuilder();

    // Act
    Map<String, AttributeValue> actualCreateJobCreatedResult = DynamoDBCompactionJobStatusFormat.createJobCreated(event,
        builder);

    // Assert
    Map<String, AttributeValue> buildResult = builder.build();
    assertEquals(3, buildResult.size());
    assertEquals(3, actualCreateJobCreatedResult.size());
    assertTrue(buildResult.containsKey("InputFilesCount"));
    assertTrue(buildResult.containsKey("PartitionId"));
    assertTrue(buildResult.containsKey("UpdateType"));
    assertTrue(actualCreateJobCreatedResult.containsKey("InputFilesCount"));
    assertTrue(actualCreateJobCreatedResult.containsKey("PartitionId"));
    assertTrue(actualCreateJobCreatedResult.containsKey("UpdateType"));
  }

  /**
   * Test {@link DynamoDBCompactionJobStatusFormat#createJobStartedUpdate(CompactionJobStartedEvent, DynamoDBRecordBuilder)}.
   * <ul>
   *   <li>Then {@link DynamoDBRecordBuilder} (default constructor) build size is four.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBCompactionJobStatusFormat#createJobStartedUpdate(CompactionJobStartedEvent, DynamoDBRecordBuilder)}
   */
  @Test
  @DisplayName("Test createJobStartedUpdate(CompactionJobStartedEvent, DynamoDBRecordBuilder); then DynamoDBRecordBuilder (default constructor) build size is four")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "Map DynamoDBCompactionJobStatusFormat.createJobStartedUpdate(CompactionJobStartedEvent, DynamoDBRecordBuilder)"})
  void testCreateJobStartedUpdate_thenDynamoDBRecordBuilderBuildSizeIsFour() {
    // Arrange
    CompactionJobStartedEvent.Builder jobRunIdResult = CompactionJobStartedEvent.builder().jobId("42").jobRunId("42");
    CompactionJobStartedEvent event = jobRunIdResult
        .startTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .tableId("42")
        .taskId("42")
        .build();
    DynamoDBRecordBuilder builder = new DynamoDBRecordBuilder();

    // Act
    Map<String, AttributeValue> actualCreateJobStartedUpdateResult = DynamoDBCompactionJobStatusFormat
        .createJobStartedUpdate(event, builder);

    // Assert
    Map<String, AttributeValue> buildResult = builder.build();
    assertEquals(4, buildResult.size());
    assertEquals(4, actualCreateJobStartedUpdateResult.size());
    assertTrue(buildResult.containsKey("JobRunId"));
    assertTrue(buildResult.containsKey("StartTime"));
    assertTrue(buildResult.containsKey("TaskId"));
    assertTrue(buildResult.containsKey("UpdateType"));
    assertTrue(actualCreateJobStartedUpdateResult.containsKey("StartTime"));
    assertTrue(actualCreateJobStartedUpdateResult.containsKey("TaskId"));
    assertTrue(actualCreateJobStartedUpdateResult.containsKey("UpdateType"));
    AttributeValue expectedGetResult = actualCreateJobStartedUpdateResult.get("TaskId");
    assertEquals(expectedGetResult, actualCreateJobStartedUpdateResult.get("JobRunId"));
  }

  /**
   * Test {@link DynamoDBCompactionJobStatusFormat#createJobStartedUpdate(CompactionJobStartedEvent, DynamoDBRecordBuilder)}.
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBCompactionJobStatusFormat#createJobStartedUpdate(CompactionJobStartedEvent, DynamoDBRecordBuilder)}
   */
  @Test
  @DisplayName("Test createJobStartedUpdate(CompactionJobStartedEvent, DynamoDBRecordBuilder); then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "Map DynamoDBCompactionJobStatusFormat.createJobStartedUpdate(CompactionJobStartedEvent, DynamoDBRecordBuilder)"})
  void testCreateJobStartedUpdate_thenThrowIllegalArgumentException() {
    // Arrange
    CompactionJobStartedEvent event = mock(CompactionJobStartedEvent.class);
    when(event.getTaskId()).thenThrow(new IllegalArgumentException("UpdateType"));
    when(event.getStartTime()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> DynamoDBCompactionJobStatusFormat.createJobStartedUpdate(event, new DynamoDBRecordBuilder()));
    verify(event).getStartTime();
    verify(event).getTaskId();
  }

  /**
   * Test {@link DynamoDBCompactionJobStatusFormat#createJobFinishedUpdate(CompactionJobFinishedEvent, DynamoDBRecordBuilder)}.
   * <ul>
   *   <li>Then {@link DynamoDBRecordBuilder} (default constructor) build size is six.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBCompactionJobStatusFormat#createJobFinishedUpdate(CompactionJobFinishedEvent, DynamoDBRecordBuilder)}
   */
  @Test
  @DisplayName("Test createJobFinishedUpdate(CompactionJobFinishedEvent, DynamoDBRecordBuilder); then DynamoDBRecordBuilder (default constructor) build size is six")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "Map DynamoDBCompactionJobStatusFormat.createJobFinishedUpdate(CompactionJobFinishedEvent, DynamoDBRecordBuilder)"})
  void testCreateJobFinishedUpdate_thenDynamoDBRecordBuilderBuildSizeIsSix() {
    // Arrange
    CompactionJobFinishedEvent event = mock(CompactionJobFinishedEvent.class);
    when(event.getJobRunId()).thenReturn("42");
    when(event.getTaskId()).thenReturn("42");
    when(event.getFinishTime()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    when(event.getRecordsProcessed()).thenReturn(new RecordsProcessed(1L, 1L));
    DynamoDBRecordBuilder builder = new DynamoDBRecordBuilder();

    // Act
    Map<String, AttributeValue> actualCreateJobFinishedUpdateResult = DynamoDBCompactionJobStatusFormat
        .createJobFinishedUpdate(event, builder);

    // Assert
    verify(event).getFinishTime();
    verify(event).getJobRunId();
    verify(event).getRecordsProcessed();
    verify(event).getTaskId();
    Map<String, AttributeValue> buildResult = builder.build();
    assertEquals(6, buildResult.size());
    assertEquals(6, actualCreateJobFinishedUpdateResult.size());
    assertTrue(buildResult.containsKey("FinishTime"));
    assertTrue(buildResult.containsKey("JobRunId"));
    assertTrue(buildResult.containsKey("RecordsRead"));
    assertTrue(buildResult.containsKey("RecordsWritten"));
    assertTrue(buildResult.containsKey("TaskId"));
    assertTrue(buildResult.containsKey("UpdateType"));
    assertTrue(actualCreateJobFinishedUpdateResult.containsKey("FinishTime"));
    assertTrue(actualCreateJobFinishedUpdateResult.containsKey("RecordsWritten"));
    assertTrue(actualCreateJobFinishedUpdateResult.containsKey("TaskId"));
    assertTrue(actualCreateJobFinishedUpdateResult.containsKey("UpdateType"));
    AttributeValue expectedGetResult = actualCreateJobFinishedUpdateResult.get("RecordsWritten");
    assertEquals(expectedGetResult, actualCreateJobFinishedUpdateResult.get("RecordsRead"));
    AttributeValue expectedGetResult2 = actualCreateJobFinishedUpdateResult.get("TaskId");
    assertEquals(expectedGetResult2, actualCreateJobFinishedUpdateResult.get("JobRunId"));
  }

  /**
   * Test {@link DynamoDBCompactionJobStatusFormat#createJobFinishedUpdate(CompactionJobFinishedEvent, DynamoDBRecordBuilder)}.
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBCompactionJobStatusFormat#createJobFinishedUpdate(CompactionJobFinishedEvent, DynamoDBRecordBuilder)}
   */
  @Test
  @DisplayName("Test createJobFinishedUpdate(CompactionJobFinishedEvent, DynamoDBRecordBuilder); then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "Map DynamoDBCompactionJobStatusFormat.createJobFinishedUpdate(CompactionJobFinishedEvent, DynamoDBRecordBuilder)"})
  void testCreateJobFinishedUpdate_thenThrowIllegalArgumentException() {
    // Arrange
    CompactionJobFinishedEvent event = mock(CompactionJobFinishedEvent.class);
    when(event.getRecordsProcessed()).thenThrow(new IllegalArgumentException("UpdateType"));

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> DynamoDBCompactionJobStatusFormat.createJobFinishedUpdate(event, new DynamoDBRecordBuilder()));
    verify(event).getRecordsProcessed();
  }

  /**
   * Test {@link DynamoDBCompactionJobStatusFormat#createJobCommittedUpdate(CompactionJobCommittedEvent, DynamoDBRecordBuilder)}.
   * <ul>
   *   <li>Then {@link DynamoDBRecordBuilder} (default constructor) build size is four.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBCompactionJobStatusFormat#createJobCommittedUpdate(CompactionJobCommittedEvent, DynamoDBRecordBuilder)}
   */
  @Test
  @DisplayName("Test createJobCommittedUpdate(CompactionJobCommittedEvent, DynamoDBRecordBuilder); then DynamoDBRecordBuilder (default constructor) build size is four")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "Map DynamoDBCompactionJobStatusFormat.createJobCommittedUpdate(CompactionJobCommittedEvent, DynamoDBRecordBuilder)"})
  void testCreateJobCommittedUpdate_thenDynamoDBRecordBuilderBuildSizeIsFour() {
    // Arrange
    CompactionJobCommittedEvent.Builder builderResult = CompactionJobCommittedEvent.builder();
    CompactionJobCommittedEvent event = builderResult
        .commitTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .jobId("42")
        .jobRunId("42")
        .tableId("42")
        .taskId("42")
        .build();
    DynamoDBRecordBuilder builder = new DynamoDBRecordBuilder();

    // Act
    Map<String, AttributeValue> actualCreateJobCommittedUpdateResult = DynamoDBCompactionJobStatusFormat
        .createJobCommittedUpdate(event, builder);

    // Assert
    Map<String, AttributeValue> buildResult = builder.build();
    assertEquals(4, buildResult.size());
    assertEquals(4, actualCreateJobCommittedUpdateResult.size());
    assertTrue(buildResult.containsKey("CommitTime"));
    assertTrue(buildResult.containsKey("JobRunId"));
    assertTrue(buildResult.containsKey("TaskId"));
    assertTrue(buildResult.containsKey("UpdateType"));
    assertTrue(actualCreateJobCommittedUpdateResult.containsKey("CommitTime"));
    assertTrue(actualCreateJobCommittedUpdateResult.containsKey("TaskId"));
    assertTrue(actualCreateJobCommittedUpdateResult.containsKey("UpdateType"));
    AttributeValue expectedGetResult = actualCreateJobCommittedUpdateResult.get("TaskId");
    assertEquals(expectedGetResult, actualCreateJobCommittedUpdateResult.get("JobRunId"));
  }

  /**
   * Test {@link DynamoDBCompactionJobStatusFormat#createJobCommittedUpdate(CompactionJobCommittedEvent, DynamoDBRecordBuilder)}.
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBCompactionJobStatusFormat#createJobCommittedUpdate(CompactionJobCommittedEvent, DynamoDBRecordBuilder)}
   */
  @Test
  @DisplayName("Test createJobCommittedUpdate(CompactionJobCommittedEvent, DynamoDBRecordBuilder); then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "Map DynamoDBCompactionJobStatusFormat.createJobCommittedUpdate(CompactionJobCommittedEvent, DynamoDBRecordBuilder)"})
  void testCreateJobCommittedUpdate_thenThrowIllegalArgumentException() {
    // Arrange
    CompactionJobCommittedEvent event = mock(CompactionJobCommittedEvent.class);
    when(event.getTaskId()).thenThrow(new IllegalArgumentException("UpdateType"));

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> DynamoDBCompactionJobStatusFormat.createJobCommittedUpdate(event, new DynamoDBRecordBuilder()));
    verify(event).getTaskId();
  }

  /**
   * Test {@link DynamoDBCompactionJobStatusFormat#createJobFailedUpdate(CompactionJobFailedEvent, DynamoDBRecordBuilder)}.
   * <ul>
   *   <li>Then {@link DynamoDBRecordBuilder} (default constructor) build size is five.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBCompactionJobStatusFormat#createJobFailedUpdate(CompactionJobFailedEvent, DynamoDBRecordBuilder)}
   */
  @Test
  @DisplayName("Test createJobFailedUpdate(CompactionJobFailedEvent, DynamoDBRecordBuilder); then DynamoDBRecordBuilder (default constructor) build size is five")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "Map DynamoDBCompactionJobStatusFormat.createJobFailedUpdate(CompactionJobFailedEvent, DynamoDBRecordBuilder)"})
  void testCreateJobFailedUpdate_thenDynamoDBRecordBuilderBuildSizeIsFive() {
    // Arrange
    Builder builderResult = CompactionJobFailedEvent.builder();
    Builder failureReasonsResult = builderResult.failureReasons(new ArrayList<>());
    CompactionJobFailedEvent event = failureReasonsResult
        .failureTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .jobId("42")
        .jobRunId("42")
        .tableId("42")
        .taskId("42")
        .build();
    DynamoDBRecordBuilder builder = new DynamoDBRecordBuilder();

    // Act
    Map<String, AttributeValue> actualCreateJobFailedUpdateResult = DynamoDBCompactionJobStatusFormat
        .createJobFailedUpdate(event, builder);

    // Assert
    Map<String, AttributeValue> buildResult = builder.build();
    assertEquals(5, buildResult.size());
    assertEquals(5, actualCreateJobFailedUpdateResult.size());
    assertTrue(buildResult.containsKey("FailureReasons"));
    assertTrue(buildResult.containsKey("FinishTime"));
    assertTrue(buildResult.containsKey("JobRunId"));
    assertTrue(buildResult.containsKey("TaskId"));
    assertTrue(buildResult.containsKey("UpdateType"));
    assertTrue(actualCreateJobFailedUpdateResult.containsKey("FailureReasons"));
    assertTrue(actualCreateJobFailedUpdateResult.containsKey("FinishTime"));
    assertTrue(actualCreateJobFailedUpdateResult.containsKey("TaskId"));
    assertTrue(actualCreateJobFailedUpdateResult.containsKey("UpdateType"));
    AttributeValue expectedGetResult = actualCreateJobFailedUpdateResult.get("TaskId");
    assertEquals(expectedGetResult, actualCreateJobFailedUpdateResult.get("JobRunId"));
  }

  /**
   * Test {@link DynamoDBCompactionJobStatusFormat#createJobFailedUpdate(CompactionJobFailedEvent, DynamoDBRecordBuilder)}.
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBCompactionJobStatusFormat#createJobFailedUpdate(CompactionJobFailedEvent, DynamoDBRecordBuilder)}
   */
  @Test
  @DisplayName("Test createJobFailedUpdate(CompactionJobFailedEvent, DynamoDBRecordBuilder); then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "Map DynamoDBCompactionJobStatusFormat.createJobFailedUpdate(CompactionJobFailedEvent, DynamoDBRecordBuilder)"})
  void testCreateJobFailedUpdate_thenThrowIllegalArgumentException() {
    // Arrange
    CompactionJobFailedEvent event = mock(CompactionJobFailedEvent.class);
    when(event.getFailureReasons()).thenThrow(new IllegalArgumentException("foo"));
    when(event.getJobRunId()).thenReturn("42");
    when(event.getTaskId()).thenReturn("42");
    when(event.getFailureTime()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> DynamoDBCompactionJobStatusFormat.createJobFailedUpdate(event, new DynamoDBRecordBuilder()));
    verify(event).getFailureReasons();
    verify(event).getFailureTime();
    verify(event).getJobRunId();
    verify(event).getTaskId();
  }

  /**
   * Test {@link DynamoDBCompactionJobStatusFormat#jobUpdateBuilder(String, String, Instant, Instant)}.
   * <p>
   * Method under test: {@link DynamoDBCompactionJobStatusFormat#jobUpdateBuilder(String, String, Instant, Instant)}
   */
  @Test
  @DisplayName("Test jobUpdateBuilder(String, String, Instant, Instant)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "DynamoDBRecordBuilder DynamoDBCompactionJobStatusFormat.jobUpdateBuilder(String, String, Instant, Instant)"})
  void testJobUpdateBuilder() {
    // Arrange
    Instant timeNow = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    // Act and Assert
    Map<String, AttributeValue> buildResult = DynamoDBCompactionJobStatusFormat
        .jobUpdateBuilder("42", "42", timeNow,
            LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();
    assertEquals(5, buildResult.size());
    String string = DynamoDBCompactionJobTracker.EXPIRY_DATE;
    assertTrue(buildResult.containsKey(string));
    assertTrue(buildResult.containsKey(DynamoDBCompactionJobTracker.JOB_ID_AND_UPDATE));
    String string2 = DynamoDBCompactionJobTracker.TABLE_ID;
    assertTrue(buildResult.containsKey(string2));
    AttributeValue expectedGetResult = buildResult.get(string);
    assertEquals(expectedGetResult, buildResult.get("UpdateTime"));
    AttributeValue expectedGetResult2 = buildResult.get(string2);
    assertEquals(expectedGetResult2, buildResult.get(DynamoDBCompactionJobTracker.JOB_ID));
  }

  /**
   * Test {@link DynamoDBCompactionJobStatusFormat#streamJobStatuses(Stream)}.
   * <p>
   * Method under test: {@link DynamoDBCompactionJobStatusFormat#streamJobStatuses(Stream)}
   */
  @Test
  @DisplayName("Test streamJobStatuses(Stream)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream DynamoDBCompactionJobStatusFormat.streamJobStatuses(Stream)"})
  void testStreamJobStatuses() {
    // Arrange
    AttributeValue attributeValue = new AttributeValue(DynamoDBCompactionJobTracker.JOB_ID);
    attributeValue.addMEntry(DynamoDBCompactionJobTracker.JOB_ID,
        new AttributeValue(DynamoDBCompactionJobTracker.JOB_ID));

    HashMap<String, AttributeValue> stringAttributeValueMap = new HashMap<>();
    stringAttributeValueMap.put("UpdateType", attributeValue);
    stringAttributeValueMap.put(DynamoDBCompactionJobTracker.JOB_ID,
        new AttributeValue(DynamoDBCompactionJobTracker.JOB_ID));

    ArrayList<Map<String, AttributeValue>> mapList = new ArrayList<>();
    mapList.add(stringAttributeValueMap);
    Stream<Map<String, AttributeValue>> items = mapList.stream();

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> DynamoDBCompactionJobStatusFormat.streamJobStatuses(items));
  }

  /**
   * Test {@link DynamoDBCompactionJobStatusFormat#streamJobStatuses(Stream)}.
   * <ul>
   *   <li>Given {@code A}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBCompactionJobStatusFormat#streamJobStatuses(Stream)}
   */
  @Test
  @DisplayName("Test streamJobStatuses(Stream); given 'A'; then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream DynamoDBCompactionJobStatusFormat.streamJobStatuses(Stream)"})
  void testStreamJobStatuses_givenA_thenThrowIllegalArgumentException() {
    // Arrange
    AttributeValue attributeValue = new AttributeValue(DynamoDBCompactionJobTracker.JOB_ID);
    attributeValue.setB(ByteBuffer.wrap(new byte[]{'A', -1, 'A', -1, 'A', -1, 'A', -1}));
    attributeValue.addMEntry(DynamoDBCompactionJobTracker.JOB_ID,
        new AttributeValue(DynamoDBCompactionJobTracker.JOB_ID));

    HashMap<String, AttributeValue> stringAttributeValueMap = new HashMap<>();
    stringAttributeValueMap.put("UpdateType", attributeValue);
    stringAttributeValueMap.put(DynamoDBCompactionJobTracker.JOB_ID,
        new AttributeValue(DynamoDBCompactionJobTracker.JOB_ID));

    ArrayList<Map<String, AttributeValue>> mapList = new ArrayList<>();
    mapList.add(stringAttributeValueMap);
    Stream<Map<String, AttributeValue>> items = mapList.stream();

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> DynamoDBCompactionJobStatusFormat.streamJobStatuses(items));
  }

  /**
   * Test {@link DynamoDBCompactionJobStatusFormat#streamJobStatuses(Stream)}.
   * <ul>
   *   <li>Given {@link AttributeValue#AttributeValue(String)} with s is {@link DynamoDBCompactionJobTracker#JOB_ID} BOOL is {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBCompactionJobStatusFormat#streamJobStatuses(Stream)}
   */
  @Test
  @DisplayName("Test streamJobStatuses(Stream); given AttributeValue(String) with s is JOB_ID BOOL is 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream DynamoDBCompactionJobStatusFormat.streamJobStatuses(Stream)"})
  void testStreamJobStatuses_givenAttributeValueWithSIsJob_idBoolIsTrue() {
    // Arrange
    AttributeValue attributeValue = new AttributeValue(DynamoDBCompactionJobTracker.JOB_ID);
    attributeValue.setBOOL(true);
    attributeValue.addMEntry(DynamoDBCompactionJobTracker.JOB_ID,
        new AttributeValue(DynamoDBCompactionJobTracker.JOB_ID));

    HashMap<String, AttributeValue> stringAttributeValueMap = new HashMap<>();
    stringAttributeValueMap.put("UpdateType", attributeValue);
    stringAttributeValueMap.put(DynamoDBCompactionJobTracker.JOB_ID,
        new AttributeValue(DynamoDBCompactionJobTracker.JOB_ID));

    ArrayList<Map<String, AttributeValue>> mapList = new ArrayList<>();
    mapList.add(stringAttributeValueMap);
    Stream<Map<String, AttributeValue>> items = mapList.stream();

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> DynamoDBCompactionJobStatusFormat.streamJobStatuses(items));
  }

  /**
   * Test {@link DynamoDBCompactionJobStatusFormat#streamJobStatuses(Stream)}.
   * <ul>
   *   <li>Given {@link AttributeValue#AttributeValue(String)} with s is {@link DynamoDBCompactionJobTracker#JOB_ID} BS is {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBCompactionJobStatusFormat#streamJobStatuses(Stream)}
   */
  @Test
  @DisplayName("Test streamJobStatuses(Stream); given AttributeValue(String) with s is JOB_ID BS is ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream DynamoDBCompactionJobStatusFormat.streamJobStatuses(Stream)"})
  void testStreamJobStatuses_givenAttributeValueWithSIsJob_idBsIsArrayList() {
    // Arrange
    AttributeValue attributeValue = new AttributeValue(DynamoDBCompactionJobTracker.JOB_ID);
    attributeValue.setBS(new ArrayList<>());
    attributeValue.addMEntry(DynamoDBCompactionJobTracker.JOB_ID,
        new AttributeValue(DynamoDBCompactionJobTracker.JOB_ID));

    HashMap<String, AttributeValue> stringAttributeValueMap = new HashMap<>();
    stringAttributeValueMap.put("UpdateType", attributeValue);
    stringAttributeValueMap.put(DynamoDBCompactionJobTracker.JOB_ID,
        new AttributeValue(DynamoDBCompactionJobTracker.JOB_ID));

    ArrayList<Map<String, AttributeValue>> mapList = new ArrayList<>();
    mapList.add(stringAttributeValueMap);
    Stream<Map<String, AttributeValue>> items = mapList.stream();

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> DynamoDBCompactionJobStatusFormat.streamJobStatuses(items));
  }

  /**
   * Test {@link DynamoDBCompactionJobStatusFormat#streamJobStatuses(Stream)}.
   * <ul>
   *   <li>Given {@link AttributeValue#AttributeValue(String)} with s is {@link DynamoDBCompactionJobTracker#JOB_ID} L is {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBCompactionJobStatusFormat#streamJobStatuses(Stream)}
   */
  @Test
  @DisplayName("Test streamJobStatuses(Stream); given AttributeValue(String) with s is JOB_ID L is ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream DynamoDBCompactionJobStatusFormat.streamJobStatuses(Stream)"})
  void testStreamJobStatuses_givenAttributeValueWithSIsJob_idLIsArrayList() {
    // Arrange
    AttributeValue attributeValue = new AttributeValue(DynamoDBCompactionJobTracker.JOB_ID);
    attributeValue.setL(new ArrayList<>());
    attributeValue.addMEntry(DynamoDBCompactionJobTracker.JOB_ID,
        new AttributeValue(DynamoDBCompactionJobTracker.JOB_ID));

    HashMap<String, AttributeValue> stringAttributeValueMap = new HashMap<>();
    stringAttributeValueMap.put("UpdateType", attributeValue);
    stringAttributeValueMap.put(DynamoDBCompactionJobTracker.JOB_ID,
        new AttributeValue(DynamoDBCompactionJobTracker.JOB_ID));

    ArrayList<Map<String, AttributeValue>> mapList = new ArrayList<>();
    mapList.add(stringAttributeValueMap);
    Stream<Map<String, AttributeValue>> items = mapList.stream();

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> DynamoDBCompactionJobStatusFormat.streamJobStatuses(items));
  }

  /**
   * Test {@link DynamoDBCompactionJobStatusFormat#streamJobStatuses(Stream)}.
   * <ul>
   *   <li>Given {@link AttributeValue#AttributeValue(String)} with s is {@link DynamoDBCompactionJobTracker#JOB_ID} M is {@link HashMap#HashMap()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBCompactionJobStatusFormat#streamJobStatuses(Stream)}
   */
  @Test
  @DisplayName("Test streamJobStatuses(Stream); given AttributeValue(String) with s is JOB_ID M is HashMap()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream DynamoDBCompactionJobStatusFormat.streamJobStatuses(Stream)"})
  void testStreamJobStatuses_givenAttributeValueWithSIsJob_idMIsHashMap() {
    // Arrange
    AttributeValue attributeValue = new AttributeValue(DynamoDBCompactionJobTracker.JOB_ID);
    attributeValue.setM(new HashMap<>());

    HashMap<String, AttributeValue> stringAttributeValueMap = new HashMap<>();
    stringAttributeValueMap.put("UpdateType", attributeValue);
    stringAttributeValueMap.put(DynamoDBCompactionJobTracker.JOB_ID,
        new AttributeValue(DynamoDBCompactionJobTracker.JOB_ID));

    ArrayList<Map<String, AttributeValue>> mapList = new ArrayList<>();
    mapList.add(stringAttributeValueMap);
    Stream<Map<String, AttributeValue>> items = mapList.stream();

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> DynamoDBCompactionJobStatusFormat.streamJobStatuses(items));
  }

  /**
   * Test {@link DynamoDBCompactionJobStatusFormat#streamJobStatuses(Stream)}.
   * <ul>
   *   <li>Given {@link AttributeValue#AttributeValue(String)} with s is {@link DynamoDBCompactionJobTracker#JOB_ID} N is {@link DynamoDBCompactionJobTracker#JOB_ID}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBCompactionJobStatusFormat#streamJobStatuses(Stream)}
   */
  @Test
  @DisplayName("Test streamJobStatuses(Stream); given AttributeValue(String) with s is JOB_ID N is JOB_ID")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream DynamoDBCompactionJobStatusFormat.streamJobStatuses(Stream)"})
  void testStreamJobStatuses_givenAttributeValueWithSIsJob_idNIsJob_id() {
    // Arrange
    AttributeValue attributeValue = new AttributeValue(DynamoDBCompactionJobTracker.JOB_ID);
    attributeValue.setN(DynamoDBCompactionJobTracker.JOB_ID);
    attributeValue.addMEntry(DynamoDBCompactionJobTracker.JOB_ID,
        new AttributeValue(DynamoDBCompactionJobTracker.JOB_ID));

    HashMap<String, AttributeValue> stringAttributeValueMap = new HashMap<>();
    stringAttributeValueMap.put("UpdateType", attributeValue);
    stringAttributeValueMap.put(DynamoDBCompactionJobTracker.JOB_ID,
        new AttributeValue(DynamoDBCompactionJobTracker.JOB_ID));

    ArrayList<Map<String, AttributeValue>> mapList = new ArrayList<>();
    mapList.add(stringAttributeValueMap);
    Stream<Map<String, AttributeValue>> items = mapList.stream();

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> DynamoDBCompactionJobStatusFormat.streamJobStatuses(items));
  }

  /**
   * Test {@link DynamoDBCompactionJobStatusFormat#streamJobStatuses(Stream)}.
   * <ul>
   *   <li>Given {@link AttributeValue#AttributeValue(String)} with s is {@link DynamoDBCompactionJobTracker#JOB_ID} NS is {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBCompactionJobStatusFormat#streamJobStatuses(Stream)}
   */
  @Test
  @DisplayName("Test streamJobStatuses(Stream); given AttributeValue(String) with s is JOB_ID NS is ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream DynamoDBCompactionJobStatusFormat.streamJobStatuses(Stream)"})
  void testStreamJobStatuses_givenAttributeValueWithSIsJob_idNsIsArrayList() {
    // Arrange
    AttributeValue attributeValue = new AttributeValue(DynamoDBCompactionJobTracker.JOB_ID);
    attributeValue.setNS(new ArrayList<>());
    attributeValue.addMEntry(DynamoDBCompactionJobTracker.JOB_ID,
        new AttributeValue(DynamoDBCompactionJobTracker.JOB_ID));

    HashMap<String, AttributeValue> stringAttributeValueMap = new HashMap<>();
    stringAttributeValueMap.put("UpdateType", attributeValue);
    stringAttributeValueMap.put(DynamoDBCompactionJobTracker.JOB_ID,
        new AttributeValue(DynamoDBCompactionJobTracker.JOB_ID));

    ArrayList<Map<String, AttributeValue>> mapList = new ArrayList<>();
    mapList.add(stringAttributeValueMap);
    Stream<Map<String, AttributeValue>> items = mapList.stream();

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> DynamoDBCompactionJobStatusFormat.streamJobStatuses(items));
  }

  /**
   * Test {@link DynamoDBCompactionJobStatusFormat#streamJobStatuses(Stream)}.
   * <ul>
   *   <li>Given {@link AttributeValue#AttributeValue(String)} with s is {@link DynamoDBCompactionJobTracker#JOB_ID} NULL is {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBCompactionJobStatusFormat#streamJobStatuses(Stream)}
   */
  @Test
  @DisplayName("Test streamJobStatuses(Stream); given AttributeValue(String) with s is JOB_ID NULL is 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream DynamoDBCompactionJobStatusFormat.streamJobStatuses(Stream)"})
  void testStreamJobStatuses_givenAttributeValueWithSIsJob_idNullIsTrue() {
    // Arrange
    AttributeValue attributeValue = new AttributeValue(DynamoDBCompactionJobTracker.JOB_ID);
    attributeValue.setNULL(true);
    attributeValue.addMEntry(DynamoDBCompactionJobTracker.JOB_ID,
        new AttributeValue(DynamoDBCompactionJobTracker.JOB_ID));

    HashMap<String, AttributeValue> stringAttributeValueMap = new HashMap<>();
    stringAttributeValueMap.put("UpdateType", attributeValue);
    stringAttributeValueMap.put(DynamoDBCompactionJobTracker.JOB_ID,
        new AttributeValue(DynamoDBCompactionJobTracker.JOB_ID));

    ArrayList<Map<String, AttributeValue>> mapList = new ArrayList<>();
    mapList.add(stringAttributeValueMap);
    Stream<Map<String, AttributeValue>> items = mapList.stream();

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> DynamoDBCompactionJobStatusFormat.streamJobStatuses(items));
  }

  /**
   * Test {@link DynamoDBCompactionJobStatusFormat#streamJobStatuses(Stream)}.
   * <ul>
   *   <li>Given {@link AttributeValue#AttributeValue(String)} with s is {@link DynamoDBCompactionJobTracker#JOB_ID} SS is {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBCompactionJobStatusFormat#streamJobStatuses(Stream)}
   */
  @Test
  @DisplayName("Test streamJobStatuses(Stream); given AttributeValue(String) with s is JOB_ID SS is ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream DynamoDBCompactionJobStatusFormat.streamJobStatuses(Stream)"})
  void testStreamJobStatuses_givenAttributeValueWithSIsJob_idSsIsArrayList() {
    // Arrange
    AttributeValue attributeValue = new AttributeValue(DynamoDBCompactionJobTracker.JOB_ID);
    attributeValue.setSS(new ArrayList<>());
    attributeValue.addMEntry(DynamoDBCompactionJobTracker.JOB_ID,
        new AttributeValue(DynamoDBCompactionJobTracker.JOB_ID));

    HashMap<String, AttributeValue> stringAttributeValueMap = new HashMap<>();
    stringAttributeValueMap.put("UpdateType", attributeValue);
    stringAttributeValueMap.put(DynamoDBCompactionJobTracker.JOB_ID,
        new AttributeValue(DynamoDBCompactionJobTracker.JOB_ID));

    ArrayList<Map<String, AttributeValue>> mapList = new ArrayList<>();
    mapList.add(stringAttributeValueMap);
    Stream<Map<String, AttributeValue>> items = mapList.stream();

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> DynamoDBCompactionJobStatusFormat.streamJobStatuses(items));
  }

  /**
   * Test {@link DynamoDBCompactionJobStatusFormat#streamJobStatuses(Stream)}.
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@link DynamoDBCompactionJobTracker#JOB_ID} is {@link AttributeValue#AttributeValue()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBCompactionJobStatusFormat#streamJobStatuses(Stream)}
   */
  @Test
  @DisplayName("Test streamJobStatuses(Stream); given HashMap() JOB_ID is AttributeValue()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream DynamoDBCompactionJobStatusFormat.streamJobStatuses(Stream)"})
  void testStreamJobStatuses_givenHashMapJob_idIsAttributeValue() {
    // Arrange
    HashMap<String, AttributeValue> stringAttributeValueMap = new HashMap<>();
    stringAttributeValueMap.put("UpdateType", new AttributeValue(DynamoDBCompactionJobTracker.JOB_ID));
    stringAttributeValueMap.put(DynamoDBCompactionJobTracker.JOB_ID, new AttributeValue());

    ArrayList<Map<String, AttributeValue>> mapList = new ArrayList<>();
    mapList.add(stringAttributeValueMap);
    Stream<Map<String, AttributeValue>> items = mapList.stream();

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> DynamoDBCompactionJobStatusFormat.streamJobStatuses(items));
  }

  /**
   * Test {@link DynamoDBCompactionJobStatusFormat#streamJobStatuses(Stream)}.
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBCompactionJobStatusFormat#streamJobStatuses(Stream)}
   */
  @Test
  @DisplayName("Test streamJobStatuses(Stream); then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream DynamoDBCompactionJobStatusFormat.streamJobStatuses(Stream)"})
  void testStreamJobStatuses_thenThrowIllegalArgumentException() {
    // Arrange
    HashMap<String, AttributeValue> stringAttributeValueMap = new HashMap<>();
    stringAttributeValueMap.put("UpdateType", new AttributeValue(DynamoDBCompactionJobTracker.JOB_ID));
    stringAttributeValueMap.put(DynamoDBCompactionJobTracker.JOB_ID,
        new AttributeValue(DynamoDBCompactionJobTracker.JOB_ID));

    ArrayList<Map<String, AttributeValue>> mapList = new ArrayList<>();
    mapList.add(stringAttributeValueMap);
    Stream<Map<String, AttributeValue>> items = mapList.stream();

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> DynamoDBCompactionJobStatusFormat.streamJobStatuses(items));
  }

  /**
   * Test {@link DynamoDBCompactionJobStatusFormat#streamJobStatuses(Stream)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()} stream.</li>
   *   <li>Then return limit five collect toList Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBCompactionJobStatusFormat#streamJobStatuses(Stream)}
   */
  @Test
  @DisplayName("Test streamJobStatuses(Stream); when ArrayList() stream; then return limit five collect toList Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream DynamoDBCompactionJobStatusFormat.streamJobStatuses(Stream)"})
  void testStreamJobStatuses_whenArrayListStream_thenReturnLimitFiveCollectToListEmpty() {
    // Arrange
    ArrayList<Map<String, AttributeValue>> mapList = new ArrayList<>();
    Stream<Map<String, AttributeValue>> items = mapList.stream();

    // Act
    Stream<CompactionJobStatus> actualStreamJobStatusesResult = DynamoDBCompactionJobStatusFormat
        .streamJobStatuses(items);

    // Assert
    assertTrue(actualStreamJobStatusesResult.limit(5).collect(Collectors.toList()).isEmpty());
  }
}
