package sleeper.compaction.tracker.job;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
import java.util.List;
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
import sleeper.core.tracker.job.run.JobRunSummary;
import sleeper.core.tracker.job.run.JobRunTime;
import sleeper.core.tracker.job.run.RecordsProcessed;
import sleeper.dynamodb.tools.DynamoDBRecordBuilder;

class DynamoDBCompactionJobStatusFormatDiffblueTest {
  /**
   * Test {@link DynamoDBCompactionJobStatusFormat#createFilesAssignedUpdate(AssignJobIdRequest,
   * DynamoDBRecordBuilder)}.
   *
   * <ul>
   *   <li>Then {@link DynamoDBRecordBuilder} (default constructor) build size is two.
   * </ul>
   *
   * <p>Method under test: {@link
   * DynamoDBCompactionJobStatusFormat#createFilesAssignedUpdate(AssignJobIdRequest,
   * DynamoDBRecordBuilder)}
   */
  @Test
  @DisplayName(
      "Test createFilesAssignedUpdate(AssignJobIdRequest, DynamoDBRecordBuilder); then DynamoDBRecordBuilder (default constructor) build size is two")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Map DynamoDBCompactionJobStatusFormat.createFilesAssignedUpdate(AssignJobIdRequest, DynamoDBRecordBuilder)"
  })
  void testCreateFilesAssignedUpdate_thenDynamoDBRecordBuilderBuildSizeIsTwo() {
    // Arrange
    AssignJobIdRequest request =
        AssignJobIdRequest.assignJobOnPartitionToFiles("42", null, new ArrayList<>());
    DynamoDBRecordBuilder builder = new DynamoDBRecordBuilder();

    // Act
    Map<String, AttributeValue> actualCreateFilesAssignedUpdateResult =
        DynamoDBCompactionJobStatusFormat.createFilesAssignedUpdate(request, builder);

    // Assert
    Map<String, AttributeValue> stringAttributeValueMap = builder.build();
    assertEquals(2, stringAttributeValueMap.size());
    assertEquals(2, actualCreateFilesAssignedUpdateResult.size());
    assertTrue(stringAttributeValueMap.containsKey("InputFilesCount"));
    assertTrue(stringAttributeValueMap.containsKey("UpdateType"));
    assertTrue(actualCreateFilesAssignedUpdateResult.containsKey("InputFilesCount"));
    assertTrue(actualCreateFilesAssignedUpdateResult.containsKey("UpdateType"));
  }

  /**
   * Test {@link DynamoDBCompactionJobStatusFormat#createFilesAssignedUpdate(AssignJobIdRequest,
   * DynamoDBRecordBuilder)}.
   *
   * <ul>
   *   <li>Then return size is three.
   * </ul>
   *
   * <p>Method under test: {@link
   * DynamoDBCompactionJobStatusFormat#createFilesAssignedUpdate(AssignJobIdRequest,
   * DynamoDBRecordBuilder)}
   */
  @Test
  @DisplayName(
      "Test createFilesAssignedUpdate(AssignJobIdRequest, DynamoDBRecordBuilder); then return size is three")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Map DynamoDBCompactionJobStatusFormat.createFilesAssignedUpdate(AssignJobIdRequest, DynamoDBRecordBuilder)"
  })
  void testCreateFilesAssignedUpdate_thenReturnSizeIsThree() {
    // Arrange
    AssignJobIdRequest request =
        AssignJobIdRequest.assignJobOnPartitionToFiles("42", "42", new ArrayList<>());
    DynamoDBRecordBuilder builder = new DynamoDBRecordBuilder();

    // Act
    Map<String, AttributeValue> actualCreateFilesAssignedUpdateResult =
        DynamoDBCompactionJobStatusFormat.createFilesAssignedUpdate(request, builder);

    // Assert
    assertEquals(3, actualCreateFilesAssignedUpdateResult.size());
    AttributeValue getResult = actualCreateFilesAssignedUpdateResult.get("PartitionId");
    assertEquals("42", getResult.getS());
    assertNull(getResult.getBOOL());
    assertNull(getResult.getNULL());
    assertNull(getResult.isBOOL());
    assertNull(getResult.isNULL());
    assertNull(getResult.getN());
    assertNull(getResult.getB());
    assertNull(getResult.getL());
    assertNull(getResult.getNS());
    assertNull(getResult.getSS());
    assertNull(getResult.getBS());
    assertNull(getResult.getM());
    Map<String, AttributeValue> stringAttributeValueMap = builder.build();
    assertEquals(3, stringAttributeValueMap.size());
    assertTrue(stringAttributeValueMap.containsKey("InputFilesCount"));
    assertTrue(stringAttributeValueMap.containsKey("PartitionId"));
    assertTrue(stringAttributeValueMap.containsKey("UpdateType"));
    assertTrue(actualCreateFilesAssignedUpdateResult.containsKey("InputFilesCount"));
    assertTrue(actualCreateFilesAssignedUpdateResult.containsKey("UpdateType"));
  }

  /**
   * Test {@link DynamoDBCompactionJobStatusFormat#createFilesAssignedUpdate(AssignJobIdRequest,
   * DynamoDBRecordBuilder)}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link
   * DynamoDBCompactionJobStatusFormat#createFilesAssignedUpdate(AssignJobIdRequest,
   * DynamoDBRecordBuilder)}
   */
  @Test
  @DisplayName(
      "Test createFilesAssignedUpdate(AssignJobIdRequest, DynamoDBRecordBuilder); then throw IllegalArgumentException")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Map DynamoDBCompactionJobStatusFormat.createFilesAssignedUpdate(AssignJobIdRequest, DynamoDBRecordBuilder)"
  })
  void testCreateFilesAssignedUpdate_thenThrowIllegalArgumentException() {
    // Arrange
    AssignJobIdRequest request = mock(AssignJobIdRequest.class);
    when(request.getPartitionId()).thenThrow(new IllegalArgumentException());

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            DynamoDBCompactionJobStatusFormat.createFilesAssignedUpdate(
                request, new DynamoDBRecordBuilder()));
    verify(request).getPartitionId();
  }

  /**
   * Test {@link DynamoDBCompactionJobStatusFormat#createJobCreated(CompactionJobCreatedEvent,
   * DynamoDBRecordBuilder)}.
   *
   * <ul>
   *   <li>Then {@link DynamoDBRecordBuilder} (default constructor) build size is three.
   * </ul>
   *
   * <p>Method under test: {@link
   * DynamoDBCompactionJobStatusFormat#createJobCreated(CompactionJobCreatedEvent,
   * DynamoDBRecordBuilder)}
   */
  @Test
  @DisplayName(
      "Test createJobCreated(CompactionJobCreatedEvent, DynamoDBRecordBuilder); then DynamoDBRecordBuilder (default constructor) build size is three")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Map DynamoDBCompactionJobStatusFormat.createJobCreated(CompactionJobCreatedEvent, DynamoDBRecordBuilder)"
  })
  void testCreateJobCreated_thenDynamoDBRecordBuilderBuildSizeIsThree() {
    // Arrange
    CompactionJobCreatedEvent event =
        CompactionJobCreatedEvent.builder()
            .inputFilesCount(3)
            .jobId("42")
            .partitionId("42")
            .tableId("42")
            .build();
    DynamoDBRecordBuilder builder = new DynamoDBRecordBuilder();

    // Act
    Map<String, AttributeValue> actualCreateJobCreatedResult =
        DynamoDBCompactionJobStatusFormat.createJobCreated(event, builder);

    // Assert
    Map<String, AttributeValue> stringAttributeValueMap = builder.build();
    assertEquals(3, stringAttributeValueMap.size());
    assertEquals(3, actualCreateJobCreatedResult.size());
    assertTrue(stringAttributeValueMap.containsKey("InputFilesCount"));
    assertTrue(stringAttributeValueMap.containsKey("PartitionId"));
    assertTrue(stringAttributeValueMap.containsKey("UpdateType"));
    assertTrue(actualCreateJobCreatedResult.containsKey("InputFilesCount"));
    assertTrue(actualCreateJobCreatedResult.containsKey("PartitionId"));
    assertTrue(actualCreateJobCreatedResult.containsKey("UpdateType"));
  }

  /**
   * Test {@link DynamoDBCompactionJobStatusFormat#createJobStartedUpdate(CompactionJobStartedEvent,
   * DynamoDBRecordBuilder)}.
   *
   * <p>Method under test: {@link
   * DynamoDBCompactionJobStatusFormat#createJobStartedUpdate(CompactionJobStartedEvent,
   * DynamoDBRecordBuilder)}
   */
  @Test
  @DisplayName("Test createJobStartedUpdate(CompactionJobStartedEvent, DynamoDBRecordBuilder)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Map DynamoDBCompactionJobStatusFormat.createJobStartedUpdate(CompactionJobStartedEvent, DynamoDBRecordBuilder)"
  })
  void testCreateJobStartedUpdate() {
    // Arrange
    CompactionJobStartedEvent event = mock(CompactionJobStartedEvent.class);
    when(event.getStartTime()).thenThrow(new IllegalArgumentException());

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            DynamoDBCompactionJobStatusFormat.createJobStartedUpdate(
                event, new DynamoDBRecordBuilder()));
    verify(event).getStartTime();
  }

  /**
   * Test {@link DynamoDBCompactionJobStatusFormat#createJobStartedUpdate(CompactionJobStartedEvent,
   * DynamoDBRecordBuilder)}.
   *
   * <ul>
   *   <li>Then calls {@link CompactionJobStartedEvent#getTaskId()}.
   * </ul>
   *
   * <p>Method under test: {@link
   * DynamoDBCompactionJobStatusFormat#createJobStartedUpdate(CompactionJobStartedEvent,
   * DynamoDBRecordBuilder)}
   */
  @Test
  @DisplayName(
      "Test createJobStartedUpdate(CompactionJobStartedEvent, DynamoDBRecordBuilder); then calls getTaskId()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Map DynamoDBCompactionJobStatusFormat.createJobStartedUpdate(CompactionJobStartedEvent, DynamoDBRecordBuilder)"
  })
  void testCreateJobStartedUpdate_thenCallsGetTaskId() {
    // Arrange
    CompactionJobStartedEvent event = mock(CompactionJobStartedEvent.class);
    when(event.getTaskId()).thenThrow(new IllegalArgumentException());
    when(event.getStartTime())
        .thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            DynamoDBCompactionJobStatusFormat.createJobStartedUpdate(
                event, new DynamoDBRecordBuilder()));
    verify(event).getStartTime();
    verify(event).getTaskId();
  }

  /**
   * Test {@link DynamoDBCompactionJobStatusFormat#createJobStartedUpdate(CompactionJobStartedEvent,
   * DynamoDBRecordBuilder)}.
   *
   * <ul>
   *   <li>Then {@link DynamoDBRecordBuilder} (default constructor) build size is four.
   * </ul>
   *
   * <p>Method under test: {@link
   * DynamoDBCompactionJobStatusFormat#createJobStartedUpdate(CompactionJobStartedEvent,
   * DynamoDBRecordBuilder)}
   */
  @Test
  @DisplayName(
      "Test createJobStartedUpdate(CompactionJobStartedEvent, DynamoDBRecordBuilder); then DynamoDBRecordBuilder (default constructor) build size is four")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Map DynamoDBCompactionJobStatusFormat.createJobStartedUpdate(CompactionJobStartedEvent, DynamoDBRecordBuilder)"
  })
  void testCreateJobStartedUpdate_thenDynamoDBRecordBuilderBuildSizeIsFour() {
    // Arrange
    CompactionJobStartedEvent event =
        CompactionJobStartedEvent.builder()
            .jobId("42")
            .jobRunId("42")
            .startTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
            .tableId("42")
            .taskId("42")
            .build();
    DynamoDBRecordBuilder builder = new DynamoDBRecordBuilder();

    // Act
    Map<String, AttributeValue> actualCreateJobStartedUpdateResult =
        DynamoDBCompactionJobStatusFormat.createJobStartedUpdate(event, builder);

    // Assert
    Map<String, AttributeValue> stringAttributeValueMap = builder.build();
    assertEquals(4, stringAttributeValueMap.size());
    assertEquals(4, actualCreateJobStartedUpdateResult.size());
    assertTrue(stringAttributeValueMap.containsKey("JobRunId"));
    assertTrue(stringAttributeValueMap.containsKey("StartTime"));
    assertTrue(stringAttributeValueMap.containsKey("TaskId"));
    assertTrue(stringAttributeValueMap.containsKey("UpdateType"));
    assertTrue(actualCreateJobStartedUpdateResult.containsKey("StartTime"));
    assertTrue(actualCreateJobStartedUpdateResult.containsKey("TaskId"));
    assertTrue(actualCreateJobStartedUpdateResult.containsKey("UpdateType"));
    AttributeValue expectedGetResult = actualCreateJobStartedUpdateResult.get("TaskId");
    assertEquals(expectedGetResult, actualCreateJobStartedUpdateResult.get("JobRunId"));
  }

  /**
   * Test {@link
   * DynamoDBCompactionJobStatusFormat#createJobFinishedUpdate(CompactionJobFinishedEvent,
   * DynamoDBRecordBuilder)}.
   *
   * <ul>
   *   <li>Then {@link DynamoDBRecordBuilder} (default constructor) build size is six.
   * </ul>
   *
   * <p>Method under test: {@link
   * DynamoDBCompactionJobStatusFormat#createJobFinishedUpdate(CompactionJobFinishedEvent,
   * DynamoDBRecordBuilder)}
   */
  @Test
  @DisplayName(
      "Test createJobFinishedUpdate(CompactionJobFinishedEvent, DynamoDBRecordBuilder); then DynamoDBRecordBuilder (default constructor) build size is six")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Map DynamoDBCompactionJobStatusFormat.createJobFinishedUpdate(CompactionJobFinishedEvent, DynamoDBRecordBuilder)"
  })
  void testCreateJobFinishedUpdate_thenDynamoDBRecordBuilderBuildSizeIsSix() {
    // Arrange
    CompactionJobFinishedEvent.Builder jobRunIdResult =
        CompactionJobFinishedEvent.builder().jobId("42").jobRunId("42");
    RecordsProcessed recordsProcessed = new RecordsProcessed(1L, 1L);
    JobRunTime runTime =
        new JobRunTime(
            LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
            Duration.ofSeconds(1L));

    JobRunSummary summary = new JobRunSummary(recordsProcessed, runTime);
    CompactionJobFinishedEvent event =
        jobRunIdResult.summary(summary).tableId("42").taskId("42").build();
    DynamoDBRecordBuilder builder = new DynamoDBRecordBuilder();

    // Act
    Map<String, AttributeValue> actualCreateJobFinishedUpdateResult =
        DynamoDBCompactionJobStatusFormat.createJobFinishedUpdate(event, builder);

    // Assert
    Map<String, AttributeValue> stringAttributeValueMap = builder.build();
    assertEquals(6, stringAttributeValueMap.size());
    assertEquals(6, actualCreateJobFinishedUpdateResult.size());
    assertTrue(stringAttributeValueMap.containsKey("FinishTime"));
    assertTrue(stringAttributeValueMap.containsKey("JobRunId"));
    assertTrue(stringAttributeValueMap.containsKey("RecordsRead"));
    assertTrue(stringAttributeValueMap.containsKey("RecordsWritten"));
    assertTrue(stringAttributeValueMap.containsKey("TaskId"));
    assertTrue(stringAttributeValueMap.containsKey("UpdateType"));
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
   * Test {@link
   * DynamoDBCompactionJobStatusFormat#createJobFinishedUpdate(CompactionJobFinishedEvent,
   * DynamoDBRecordBuilder)}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link
   * DynamoDBCompactionJobStatusFormat#createJobFinishedUpdate(CompactionJobFinishedEvent,
   * DynamoDBRecordBuilder)}
   */
  @Test
  @DisplayName(
      "Test createJobFinishedUpdate(CompactionJobFinishedEvent, DynamoDBRecordBuilder); then throw IllegalArgumentException")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Map DynamoDBCompactionJobStatusFormat.createJobFinishedUpdate(CompactionJobFinishedEvent, DynamoDBRecordBuilder)"
  })
  void testCreateJobFinishedUpdate_thenThrowIllegalArgumentException() {
    // Arrange
    CompactionJobFinishedEvent event = mock(CompactionJobFinishedEvent.class);
    when(event.getRecordsProcessed()).thenThrow(new IllegalArgumentException());

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            DynamoDBCompactionJobStatusFormat.createJobFinishedUpdate(
                event, new DynamoDBRecordBuilder()));
    verify(event).getRecordsProcessed();
  }

  /**
   * Test {@link
   * DynamoDBCompactionJobStatusFormat#createJobCommittedUpdate(CompactionJobCommittedEvent,
   * DynamoDBRecordBuilder)}.
   *
   * <ul>
   *   <li>Then {@link DynamoDBRecordBuilder} (default constructor) build size is four.
   * </ul>
   *
   * <p>Method under test: {@link
   * DynamoDBCompactionJobStatusFormat#createJobCommittedUpdate(CompactionJobCommittedEvent,
   * DynamoDBRecordBuilder)}
   */
  @Test
  @DisplayName(
      "Test createJobCommittedUpdate(CompactionJobCommittedEvent, DynamoDBRecordBuilder); then DynamoDBRecordBuilder (default constructor) build size is four")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Map DynamoDBCompactionJobStatusFormat.createJobCommittedUpdate(CompactionJobCommittedEvent, DynamoDBRecordBuilder)"
  })
  void testCreateJobCommittedUpdate_thenDynamoDBRecordBuilderBuildSizeIsFour() {
    // Arrange
    CompactionJobCommittedEvent event =
        CompactionJobCommittedEvent.builder()
            .commitTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
            .jobId("42")
            .jobRunId("42")
            .tableId("42")
            .taskId("42")
            .build();
    DynamoDBRecordBuilder builder = new DynamoDBRecordBuilder();

    // Act
    Map<String, AttributeValue> actualCreateJobCommittedUpdateResult =
        DynamoDBCompactionJobStatusFormat.createJobCommittedUpdate(event, builder);

    // Assert
    Map<String, AttributeValue> stringAttributeValueMap = builder.build();
    assertEquals(4, stringAttributeValueMap.size());
    assertEquals(4, actualCreateJobCommittedUpdateResult.size());
    assertTrue(stringAttributeValueMap.containsKey("CommitTime"));
    assertTrue(stringAttributeValueMap.containsKey("JobRunId"));
    assertTrue(stringAttributeValueMap.containsKey("TaskId"));
    assertTrue(stringAttributeValueMap.containsKey("UpdateType"));
    assertTrue(actualCreateJobCommittedUpdateResult.containsKey("CommitTime"));
    assertTrue(actualCreateJobCommittedUpdateResult.containsKey("TaskId"));
    assertTrue(actualCreateJobCommittedUpdateResult.containsKey("UpdateType"));
    AttributeValue expectedGetResult = actualCreateJobCommittedUpdateResult.get("TaskId");
    assertEquals(expectedGetResult, actualCreateJobCommittedUpdateResult.get("JobRunId"));
  }

  /**
   * Test {@link
   * DynamoDBCompactionJobStatusFormat#createJobCommittedUpdate(CompactionJobCommittedEvent,
   * DynamoDBRecordBuilder)}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link
   * DynamoDBCompactionJobStatusFormat#createJobCommittedUpdate(CompactionJobCommittedEvent,
   * DynamoDBRecordBuilder)}
   */
  @Test
  @DisplayName(
      "Test createJobCommittedUpdate(CompactionJobCommittedEvent, DynamoDBRecordBuilder); then throw IllegalArgumentException")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Map DynamoDBCompactionJobStatusFormat.createJobCommittedUpdate(CompactionJobCommittedEvent, DynamoDBRecordBuilder)"
  })
  void testCreateJobCommittedUpdate_thenThrowIllegalArgumentException() {
    // Arrange
    CompactionJobCommittedEvent event = mock(CompactionJobCommittedEvent.class);
    when(event.getTaskId()).thenThrow(new IllegalArgumentException());

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            DynamoDBCompactionJobStatusFormat.createJobCommittedUpdate(
                event, new DynamoDBRecordBuilder()));
    verify(event).getTaskId();
  }

  /**
   * Test {@link DynamoDBCompactionJobStatusFormat#createJobFailedUpdate(CompactionJobFailedEvent,
   * DynamoDBRecordBuilder)}.
   *
   * <p>Method under test: {@link
   * DynamoDBCompactionJobStatusFormat#createJobFailedUpdate(CompactionJobFailedEvent,
   * DynamoDBRecordBuilder)}
   */
  @Test
  @DisplayName("Test createJobFailedUpdate(CompactionJobFailedEvent, DynamoDBRecordBuilder)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Map DynamoDBCompactionJobStatusFormat.createJobFailedUpdate(CompactionJobFailedEvent, DynamoDBRecordBuilder)"
  })
  void testCreateJobFailedUpdate() {
    // Arrange
    CompactionJobFailedEvent event = mock(CompactionJobFailedEvent.class);
    when(event.getTaskId()).thenThrow(new IllegalArgumentException());

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            DynamoDBCompactionJobStatusFormat.createJobFailedUpdate(
                event, new DynamoDBRecordBuilder()));
    verify(event).getTaskId();
  }

  /**
   * Test {@link DynamoDBCompactionJobStatusFormat#createJobFailedUpdate(CompactionJobFailedEvent,
   * DynamoDBRecordBuilder)}.
   *
   * <p>Method under test: {@link
   * DynamoDBCompactionJobStatusFormat#createJobFailedUpdate(CompactionJobFailedEvent,
   * DynamoDBRecordBuilder)}
   */
  @Test
  @DisplayName("Test createJobFailedUpdate(CompactionJobFailedEvent, DynamoDBRecordBuilder)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Map DynamoDBCompactionJobStatusFormat.createJobFailedUpdate(CompactionJobFailedEvent, DynamoDBRecordBuilder)"
  })
  void testCreateJobFailedUpdate2() {
    // Arrange
    CompactionJobFailedEvent event = mock(CompactionJobFailedEvent.class);
    when(event.getFailureReasons()).thenThrow(new IllegalArgumentException());
    when(event.getJobRunId()).thenReturn("42");
    when(event.getTaskId()).thenReturn("42");
    when(event.getFailureTime())
        .thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            DynamoDBCompactionJobStatusFormat.createJobFailedUpdate(
                event, new DynamoDBRecordBuilder()));
    verify(event).getFailureReasons();
    verify(event).getFailureTime();
    verify(event).getJobRunId();
    verify(event).getTaskId();
  }

  /**
   * Test {@link DynamoDBCompactionJobStatusFormat#createJobFailedUpdate(CompactionJobFailedEvent,
   * DynamoDBRecordBuilder)}.
   *
   * <ul>
   *   <li>Then {@link DynamoDBRecordBuilder} (default constructor) build size is five.
   * </ul>
   *
   * <p>Method under test: {@link
   * DynamoDBCompactionJobStatusFormat#createJobFailedUpdate(CompactionJobFailedEvent,
   * DynamoDBRecordBuilder)}
   */
  @Test
  @DisplayName(
      "Test createJobFailedUpdate(CompactionJobFailedEvent, DynamoDBRecordBuilder); then DynamoDBRecordBuilder (default constructor) build size is five")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Map DynamoDBCompactionJobStatusFormat.createJobFailedUpdate(CompactionJobFailedEvent, DynamoDBRecordBuilder)"
  })
  void testCreateJobFailedUpdate_thenDynamoDBRecordBuilderBuildSizeIsFive() {
    // Arrange
    Builder builderResult = CompactionJobFailedEvent.builder();
    CompactionJobFailedEvent event =
        builderResult
            .failureReasons(new ArrayList<>())
            .failureTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
            .jobId("42")
            .jobRunId("42")
            .tableId("42")
            .taskId("42")
            .build();
    DynamoDBRecordBuilder builder = new DynamoDBRecordBuilder();

    // Act
    Map<String, AttributeValue> actualCreateJobFailedUpdateResult =
        DynamoDBCompactionJobStatusFormat.createJobFailedUpdate(event, builder);

    // Assert
    Map<String, AttributeValue> stringAttributeValueMap = builder.build();
    assertEquals(5, stringAttributeValueMap.size());
    assertEquals(5, actualCreateJobFailedUpdateResult.size());
    assertTrue(stringAttributeValueMap.containsKey("FailureReasons"));
    assertTrue(stringAttributeValueMap.containsKey("FinishTime"));
    assertTrue(stringAttributeValueMap.containsKey("JobRunId"));
    assertTrue(stringAttributeValueMap.containsKey("TaskId"));
    assertTrue(stringAttributeValueMap.containsKey("UpdateType"));
    assertTrue(actualCreateJobFailedUpdateResult.containsKey("FailureReasons"));
    assertTrue(actualCreateJobFailedUpdateResult.containsKey("FinishTime"));
    assertTrue(actualCreateJobFailedUpdateResult.containsKey("TaskId"));
    assertTrue(actualCreateJobFailedUpdateResult.containsKey("UpdateType"));
    AttributeValue expectedGetResult = actualCreateJobFailedUpdateResult.get("TaskId");
    assertEquals(expectedGetResult, actualCreateJobFailedUpdateResult.get("JobRunId"));
  }

  /**
   * Test {@link DynamoDBCompactionJobStatusFormat#createJobFailedUpdate(CompactionJobFailedEvent,
   * DynamoDBRecordBuilder)}.
   *
   * <ul>
   *   <li>Then return {@code FailureReasons} L size is one.
   * </ul>
   *
   * <p>Method under test: {@link
   * DynamoDBCompactionJobStatusFormat#createJobFailedUpdate(CompactionJobFailedEvent,
   * DynamoDBRecordBuilder)}
   */
  @Test
  @DisplayName(
      "Test createJobFailedUpdate(CompactionJobFailedEvent, DynamoDBRecordBuilder); then return 'FailureReasons' L size is one")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Map DynamoDBCompactionJobStatusFormat.createJobFailedUpdate(CompactionJobFailedEvent, DynamoDBRecordBuilder)"
  })
  void testCreateJobFailedUpdate_thenReturnFailureReasonsLSizeIsOne() {
    // Arrange
    ArrayList<String> stringList = new ArrayList<>();
    stringList.add("Just cause");

    CompactionJobFailedEvent event = mock(CompactionJobFailedEvent.class);
    when(event.getFailureReasons()).thenReturn(stringList);
    when(event.getJobRunId()).thenReturn("42");
    when(event.getTaskId()).thenReturn("42");
    when(event.getFailureTime())
        .thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act
    Map<String, AttributeValue> actualCreateJobFailedUpdateResult =
        DynamoDBCompactionJobStatusFormat.createJobFailedUpdate(event, new DynamoDBRecordBuilder());

    // Assert
    verify(event).getFailureReasons();
    verify(event).getFailureTime();
    verify(event).getJobRunId();
    verify(event).getTaskId();
    assertEquals(5, actualCreateJobFailedUpdateResult.size());
    List<AttributeValue> l = actualCreateJobFailedUpdateResult.get("FailureReasons").getL();
    assertEquals(1, l.size());
    AttributeValue getResult = l.get(0);
    assertEquals("Just cause", getResult.getS());
    assertNull(getResult.getBOOL());
    assertNull(getResult.getNULL());
    assertNull(getResult.isBOOL());
    assertNull(getResult.isNULL());
    assertNull(getResult.getN());
    assertNull(getResult.getB());
    assertNull(getResult.getL());
    assertNull(getResult.getNS());
    assertNull(getResult.getSS());
    assertNull(getResult.getBS());
    assertNull(getResult.getM());
    assertTrue(actualCreateJobFailedUpdateResult.containsKey("FinishTime"));
    assertTrue(actualCreateJobFailedUpdateResult.containsKey("TaskId"));
    assertTrue(actualCreateJobFailedUpdateResult.containsKey("UpdateType"));
    AttributeValue expectedGetResult = actualCreateJobFailedUpdateResult.get("TaskId");
    assertEquals(expectedGetResult, actualCreateJobFailedUpdateResult.get("JobRunId"));
  }

  /**
   * Test {@link DynamoDBCompactionJobStatusFormat#createJobFailedUpdate(CompactionJobFailedEvent,
   * DynamoDBRecordBuilder)}.
   *
   * <ul>
   *   <li>Then return {@code FailureReasons} L size is two.
   * </ul>
   *
   * <p>Method under test: {@link
   * DynamoDBCompactionJobStatusFormat#createJobFailedUpdate(CompactionJobFailedEvent,
   * DynamoDBRecordBuilder)}
   */
  @Test
  @DisplayName(
      "Test createJobFailedUpdate(CompactionJobFailedEvent, DynamoDBRecordBuilder); then return 'FailureReasons' L size is two")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Map DynamoDBCompactionJobStatusFormat.createJobFailedUpdate(CompactionJobFailedEvent, DynamoDBRecordBuilder)"
  })
  void testCreateJobFailedUpdate_thenReturnFailureReasonsLSizeIsTwo() {
    // Arrange
    ArrayList<String> stringList = new ArrayList<>();
    stringList.add("UpdateType");
    stringList.add("Just cause");

    CompactionJobFailedEvent event = mock(CompactionJobFailedEvent.class);
    when(event.getFailureReasons()).thenReturn(stringList);
    when(event.getJobRunId()).thenReturn("42");
    when(event.getTaskId()).thenReturn("42");
    when(event.getFailureTime())
        .thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act
    Map<String, AttributeValue> actualCreateJobFailedUpdateResult =
        DynamoDBCompactionJobStatusFormat.createJobFailedUpdate(event, new DynamoDBRecordBuilder());

    // Assert
    verify(event).getFailureReasons();
    verify(event).getFailureTime();
    verify(event).getJobRunId();
    verify(event).getTaskId();
    assertEquals(5, actualCreateJobFailedUpdateResult.size());
    List<AttributeValue> l = actualCreateJobFailedUpdateResult.get("FailureReasons").getL();
    assertEquals(2, l.size());
    AttributeValue getResult = l.get(1);
    assertEquals("Just cause", getResult.getS());
    assertEquals("UpdateType", l.get(0).getS());
    assertNull(getResult.getBOOL());
    assertNull(getResult.getNULL());
    assertNull(getResult.isBOOL());
    assertNull(getResult.isNULL());
    assertNull(getResult.getN());
    assertNull(getResult.getB());
    assertNull(getResult.getL());
    assertNull(getResult.getNS());
    assertNull(getResult.getSS());
    assertNull(getResult.getBS());
    assertNull(getResult.getM());
    assertTrue(actualCreateJobFailedUpdateResult.containsKey("FinishTime"));
    assertTrue(actualCreateJobFailedUpdateResult.containsKey("JobRunId"));
    assertTrue(actualCreateJobFailedUpdateResult.containsKey("TaskId"));
    assertTrue(actualCreateJobFailedUpdateResult.containsKey("UpdateType"));
  }

  /**
   * Test {@link DynamoDBCompactionJobStatusFormat#jobUpdateBuilder(String, String, Instant,
   * Instant)}.
   *
   * <ul>
   *   <li>Then return build size is five.
   * </ul>
   *
   * <p>Method under test: {@link DynamoDBCompactionJobStatusFormat#jobUpdateBuilder(String, String,
   * Instant, Instant)}
   */
  @Test
  @DisplayName(
      "Test jobUpdateBuilder(String, String, Instant, Instant); then return build size is five")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DynamoDBRecordBuilder DynamoDBCompactionJobStatusFormat.jobUpdateBuilder(String, String, Instant, Instant)"
  })
  void testJobUpdateBuilder_thenReturnBuildSizeIsFive() {
    // Arrange and Act
    DynamoDBRecordBuilder actualJobUpdateBuilderResult =
        DynamoDBCompactionJobStatusFormat.jobUpdateBuilder(
            "42",
            "42",
            LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
            LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Assert
    Map<String, AttributeValue> stringAttributeValueMap = actualJobUpdateBuilderResult.build();
    assertEquals(5, stringAttributeValueMap.size());
    AttributeValue getResult = stringAttributeValueMap.get(DynamoDBCompactionJobTracker.TABLE_ID);
    assertEquals("42", getResult.getS());
    assertNull(getResult.getBOOL());
    assertNull(getResult.getNULL());
    assertNull(getResult.isBOOL());
    assertNull(getResult.isNULL());
    assertNull(getResult.getN());
    assertNull(getResult.getB());
    assertNull(getResult.getL());
    assertNull(getResult.getNS());
    assertNull(getResult.getSS());
    assertNull(getResult.getBS());
    assertNull(getResult.getM());
    String string = DynamoDBCompactionJobTracker.EXPIRY_DATE;
    assertTrue(stringAttributeValueMap.containsKey(string));
    assertTrue(stringAttributeValueMap.containsKey(DynamoDBCompactionJobTracker.JOB_ID_AND_UPDATE));
    AttributeValue expectedGetResult = stringAttributeValueMap.get(string);
    assertEquals(expectedGetResult, stringAttributeValueMap.get("UpdateTime"));
    assertEquals(getResult, stringAttributeValueMap.get(DynamoDBCompactionJobTracker.JOB_ID));
  }

  /**
   * Test {@link DynamoDBCompactionJobStatusFormat#jobUpdateBuilder(String, String, Instant,
   * Instant)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return build {@link DynamoDBCompactionJobTracker#JOB_ID} S is {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link DynamoDBCompactionJobStatusFormat#jobUpdateBuilder(String, String,
   * Instant, Instant)}
   */
  @Test
  @DisplayName(
      "Test jobUpdateBuilder(String, String, Instant, Instant); when 'null'; then return build JOB_ID S is '42'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DynamoDBRecordBuilder DynamoDBCompactionJobStatusFormat.jobUpdateBuilder(String, String, Instant, Instant)"
  })
  void testJobUpdateBuilder_whenNull_thenReturnBuildJob_idSIs42() {
    // Arrange and Act
    DynamoDBRecordBuilder actualJobUpdateBuilderResult =
        DynamoDBCompactionJobStatusFormat.jobUpdateBuilder(
            null,
            "42",
            LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
            LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Assert
    Map<String, AttributeValue> stringAttributeValueMap = actualJobUpdateBuilderResult.build();
    assertEquals(4, stringAttributeValueMap.size());
    AttributeValue getResult = stringAttributeValueMap.get(DynamoDBCompactionJobTracker.JOB_ID);
    assertEquals("42", getResult.getS());
    assertNull(getResult.getBOOL());
    assertNull(getResult.getNULL());
    assertNull(getResult.isBOOL());
    assertNull(getResult.isNULL());
    assertNull(getResult.getN());
    assertNull(getResult.getB());
    assertNull(getResult.getL());
    assertNull(getResult.getNS());
    assertNull(getResult.getSS());
    assertNull(getResult.getBS());
    assertNull(getResult.getM());
    String string = DynamoDBCompactionJobTracker.EXPIRY_DATE;
    assertTrue(stringAttributeValueMap.containsKey(string));
    assertTrue(stringAttributeValueMap.containsKey(DynamoDBCompactionJobTracker.JOB_ID_AND_UPDATE));
    AttributeValue expectedGetResult = stringAttributeValueMap.get(string);
    assertEquals(expectedGetResult, stringAttributeValueMap.get("UpdateTime"));
  }

  /**
   * Test {@link DynamoDBCompactionJobStatusFormat#jobUpdateBuilder(String, String, Instant,
   * Instant)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return build size is four.
   * </ul>
   *
   * <p>Method under test: {@link DynamoDBCompactionJobStatusFormat#jobUpdateBuilder(String, String,
   * Instant, Instant)}
   */
  @Test
  @DisplayName(
      "Test jobUpdateBuilder(String, String, Instant, Instant); when 'null'; then return build size is four")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DynamoDBRecordBuilder DynamoDBCompactionJobStatusFormat.jobUpdateBuilder(String, String, Instant, Instant)"
  })
  void testJobUpdateBuilder_whenNull_thenReturnBuildSizeIsFour() {
    // Arrange and Act
    DynamoDBRecordBuilder actualJobUpdateBuilderResult =
        DynamoDBCompactionJobStatusFormat.jobUpdateBuilder(
            "42",
            null,
            LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
            LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Assert
    Map<String, AttributeValue> stringAttributeValueMap = actualJobUpdateBuilderResult.build();
    assertEquals(4, stringAttributeValueMap.size());
    AttributeValue getResult = stringAttributeValueMap.get(DynamoDBCompactionJobTracker.TABLE_ID);
    assertEquals("42", getResult.getS());
    assertNull(getResult.getBOOL());
    assertNull(getResult.getNULL());
    assertNull(getResult.isBOOL());
    assertNull(getResult.isNULL());
    assertNull(getResult.getN());
    assertNull(getResult.getB());
    assertNull(getResult.getL());
    assertNull(getResult.getNS());
    assertNull(getResult.getSS());
    assertNull(getResult.getBS());
    assertNull(getResult.getM());
    String string = DynamoDBCompactionJobTracker.EXPIRY_DATE;
    assertTrue(stringAttributeValueMap.containsKey(string));
    assertTrue(stringAttributeValueMap.containsKey(DynamoDBCompactionJobTracker.JOB_ID_AND_UPDATE));
    AttributeValue expectedGetResult = stringAttributeValueMap.get(string);
    assertEquals(expectedGetResult, stringAttributeValueMap.get("UpdateTime"));
  }

  /**
   * Test {@link DynamoDBCompactionJobStatusFormat#streamJobStatuses(Stream)}.
   *
   * <p>Method under test: {@link DynamoDBCompactionJobStatusFormat#streamJobStatuses(Stream)}
   */
  @Test
  @DisplayName("Test streamJobStatuses(Stream)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Stream DynamoDBCompactionJobStatusFormat.streamJobStatuses(Stream)"})
  void testStreamJobStatuses() {
    // Arrange
    AttributeValue attributeValue = new AttributeValue(DynamoDBCompactionJobTracker.JOB_ID);
    attributeValue.addMEntry("UpdateType", new AttributeValue());

    HashMap<String, AttributeValue> stringAttributeValueMap = new HashMap<>();
    stringAttributeValueMap.put("UpdateType", attributeValue);
    stringAttributeValueMap.put(DynamoDBCompactionJobTracker.JOB_ID, new AttributeValue());

    ArrayList<Map<String, AttributeValue>> mapList = new ArrayList<>();
    mapList.add(stringAttributeValueMap);
    Stream<Map<String, AttributeValue>> items = mapList.stream();

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> DynamoDBCompactionJobStatusFormat.streamJobStatuses(items));
  }

  /**
   * Test {@link DynamoDBCompactionJobStatusFormat#streamJobStatuses(Stream)}.
   *
   * <ul>
   *   <li>Given {@code A}.
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link DynamoDBCompactionJobStatusFormat#streamJobStatuses(Stream)}
   */
  @Test
  @DisplayName("Test streamJobStatuses(Stream); given 'A'; then throw IllegalArgumentException")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Stream DynamoDBCompactionJobStatusFormat.streamJobStatuses(Stream)"})
  void testStreamJobStatuses_givenA_thenThrowIllegalArgumentException() {
    // Arrange
    AttributeValue attributeValue = new AttributeValue(DynamoDBCompactionJobTracker.JOB_ID);
    attributeValue.setB(ByteBuffer.wrap(new byte[] {'A', -1, 'A', -1, 'A', -1, 'A', -1}));
    attributeValue.addMEntry("UpdateType", new AttributeValue());

    HashMap<String, AttributeValue> stringAttributeValueMap = new HashMap<>();
    stringAttributeValueMap.put("UpdateType", attributeValue);
    stringAttributeValueMap.put(DynamoDBCompactionJobTracker.JOB_ID, new AttributeValue());

    ArrayList<Map<String, AttributeValue>> mapList = new ArrayList<>();
    mapList.add(stringAttributeValueMap);
    Stream<Map<String, AttributeValue>> items = mapList.stream();

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> DynamoDBCompactionJobStatusFormat.streamJobStatuses(items));
  }

  /**
   * Test {@link DynamoDBCompactionJobStatusFormat#streamJobStatuses(Stream)}.
   *
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link AttributeValue#AttributeValue()}.
   * </ul>
   *
   * <p>Method under test: {@link DynamoDBCompactionJobStatusFormat#streamJobStatuses(Stream)}
   */
  @Test
  @DisplayName("Test streamJobStatuses(Stream); given ArrayList() add AttributeValue()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Stream DynamoDBCompactionJobStatusFormat.streamJobStatuses(Stream)"})
  void testStreamJobStatuses_givenArrayListAddAttributeValue() {
    // Arrange
    ArrayList<AttributeValue> l = new ArrayList<>();
    l.add(new AttributeValue());

    AttributeValue attributeValue = new AttributeValue(DynamoDBCompactionJobTracker.JOB_ID);
    attributeValue.setL(l);
    attributeValue.addMEntry("UpdateType", new AttributeValue());

    HashMap<String, AttributeValue> stringAttributeValueMap = new HashMap<>();
    stringAttributeValueMap.put("UpdateType", attributeValue);
    stringAttributeValueMap.put(DynamoDBCompactionJobTracker.JOB_ID, new AttributeValue());

    ArrayList<Map<String, AttributeValue>> mapList = new ArrayList<>();
    mapList.add(stringAttributeValueMap);
    Stream<Map<String, AttributeValue>> items = mapList.stream();

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> DynamoDBCompactionJobStatusFormat.streamJobStatuses(items));
  }

  /**
   * Test {@link DynamoDBCompactionJobStatusFormat#streamJobStatuses(Stream)}.
   *
   * <ul>
   *   <li>Given {@link AttributeValue#AttributeValue(String)} with s is {@link
   *       DynamoDBCompactionJobTracker#JOB_ID} BOOL is {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DynamoDBCompactionJobStatusFormat#streamJobStatuses(Stream)}
   */
  @Test
  @DisplayName(
      "Test streamJobStatuses(Stream); given AttributeValue(String) with s is JOB_ID BOOL is 'true'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Stream DynamoDBCompactionJobStatusFormat.streamJobStatuses(Stream)"})
  void testStreamJobStatuses_givenAttributeValueWithSIsJob_idBoolIsTrue() {
    // Arrange
    AttributeValue attributeValue = new AttributeValue(DynamoDBCompactionJobTracker.JOB_ID);
    attributeValue.setBOOL(true);
    attributeValue.addMEntry("UpdateType", new AttributeValue());

    HashMap<String, AttributeValue> stringAttributeValueMap = new HashMap<>();
    stringAttributeValueMap.put("UpdateType", attributeValue);
    stringAttributeValueMap.put(DynamoDBCompactionJobTracker.JOB_ID, new AttributeValue());

    ArrayList<Map<String, AttributeValue>> mapList = new ArrayList<>();
    mapList.add(stringAttributeValueMap);
    Stream<Map<String, AttributeValue>> items = mapList.stream();

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> DynamoDBCompactionJobStatusFormat.streamJobStatuses(items));
  }

  /**
   * Test {@link DynamoDBCompactionJobStatusFormat#streamJobStatuses(Stream)}.
   *
   * <ul>
   *   <li>Given {@link AttributeValue#AttributeValue(String)} with s is {@link
   *       DynamoDBCompactionJobTracker#JOB_ID} BS is {@link ArrayList#ArrayList()}.
   * </ul>
   *
   * <p>Method under test: {@link DynamoDBCompactionJobStatusFormat#streamJobStatuses(Stream)}
   */
  @Test
  @DisplayName(
      "Test streamJobStatuses(Stream); given AttributeValue(String) with s is JOB_ID BS is ArrayList()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Stream DynamoDBCompactionJobStatusFormat.streamJobStatuses(Stream)"})
  void testStreamJobStatuses_givenAttributeValueWithSIsJob_idBsIsArrayList() {
    // Arrange
    AttributeValue attributeValue = new AttributeValue(DynamoDBCompactionJobTracker.JOB_ID);
    attributeValue.setBS(new ArrayList<>());
    attributeValue.addMEntry("UpdateType", new AttributeValue());

    HashMap<String, AttributeValue> stringAttributeValueMap = new HashMap<>();
    stringAttributeValueMap.put("UpdateType", attributeValue);
    stringAttributeValueMap.put(DynamoDBCompactionJobTracker.JOB_ID, new AttributeValue());

    ArrayList<Map<String, AttributeValue>> mapList = new ArrayList<>();
    mapList.add(stringAttributeValueMap);
    Stream<Map<String, AttributeValue>> items = mapList.stream();

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> DynamoDBCompactionJobStatusFormat.streamJobStatuses(items));
  }

  /**
   * Test {@link DynamoDBCompactionJobStatusFormat#streamJobStatuses(Stream)}.
   *
   * <ul>
   *   <li>Given {@link AttributeValue#AttributeValue(String)} with s is {@link
   *       DynamoDBCompactionJobTracker#JOB_ID} L is {@link ArrayList#ArrayList()}.
   * </ul>
   *
   * <p>Method under test: {@link DynamoDBCompactionJobStatusFormat#streamJobStatuses(Stream)}
   */
  @Test
  @DisplayName(
      "Test streamJobStatuses(Stream); given AttributeValue(String) with s is JOB_ID L is ArrayList()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Stream DynamoDBCompactionJobStatusFormat.streamJobStatuses(Stream)"})
  void testStreamJobStatuses_givenAttributeValueWithSIsJob_idLIsArrayList() {
    // Arrange
    AttributeValue attributeValue = new AttributeValue(DynamoDBCompactionJobTracker.JOB_ID);
    attributeValue.setL(new ArrayList<>());
    attributeValue.addMEntry("UpdateType", new AttributeValue());

    HashMap<String, AttributeValue> stringAttributeValueMap = new HashMap<>();
    stringAttributeValueMap.put("UpdateType", attributeValue);
    stringAttributeValueMap.put(DynamoDBCompactionJobTracker.JOB_ID, new AttributeValue());

    ArrayList<Map<String, AttributeValue>> mapList = new ArrayList<>();
    mapList.add(stringAttributeValueMap);
    Stream<Map<String, AttributeValue>> items = mapList.stream();

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> DynamoDBCompactionJobStatusFormat.streamJobStatuses(items));
  }

  /**
   * Test {@link DynamoDBCompactionJobStatusFormat#streamJobStatuses(Stream)}.
   *
   * <ul>
   *   <li>Given {@link AttributeValue#AttributeValue(String)} with s is {@link
   *       DynamoDBCompactionJobTracker#JOB_ID} M is {@link HashMap#HashMap()}.
   * </ul>
   *
   * <p>Method under test: {@link DynamoDBCompactionJobStatusFormat#streamJobStatuses(Stream)}
   */
  @Test
  @DisplayName(
      "Test streamJobStatuses(Stream); given AttributeValue(String) with s is JOB_ID M is HashMap()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Stream DynamoDBCompactionJobStatusFormat.streamJobStatuses(Stream)"})
  void testStreamJobStatuses_givenAttributeValueWithSIsJob_idMIsHashMap() {
    // Arrange
    AttributeValue attributeValue = new AttributeValue(DynamoDBCompactionJobTracker.JOB_ID);
    attributeValue.setM(new HashMap<>());

    HashMap<String, AttributeValue> stringAttributeValueMap = new HashMap<>();
    stringAttributeValueMap.put("UpdateType", attributeValue);
    stringAttributeValueMap.put(DynamoDBCompactionJobTracker.JOB_ID, new AttributeValue());

    ArrayList<Map<String, AttributeValue>> mapList = new ArrayList<>();
    mapList.add(stringAttributeValueMap);
    Stream<Map<String, AttributeValue>> items = mapList.stream();

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> DynamoDBCompactionJobStatusFormat.streamJobStatuses(items));
  }

  /**
   * Test {@link DynamoDBCompactionJobStatusFormat#streamJobStatuses(Stream)}.
   *
   * <ul>
   *   <li>Given {@link AttributeValue#AttributeValue(String)} with s is {@link
   *       DynamoDBCompactionJobTracker#JOB_ID} N is {@link DynamoDBCompactionJobTracker#JOB_ID}.
   * </ul>
   *
   * <p>Method under test: {@link DynamoDBCompactionJobStatusFormat#streamJobStatuses(Stream)}
   */
  @Test
  @DisplayName(
      "Test streamJobStatuses(Stream); given AttributeValue(String) with s is JOB_ID N is JOB_ID")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Stream DynamoDBCompactionJobStatusFormat.streamJobStatuses(Stream)"})
  void testStreamJobStatuses_givenAttributeValueWithSIsJob_idNIsJob_id() {
    // Arrange
    AttributeValue attributeValue = new AttributeValue(DynamoDBCompactionJobTracker.JOB_ID);
    attributeValue.setN(DynamoDBCompactionJobTracker.JOB_ID);
    attributeValue.addMEntry("UpdateType", new AttributeValue());

    HashMap<String, AttributeValue> stringAttributeValueMap = new HashMap<>();
    stringAttributeValueMap.put("UpdateType", attributeValue);
    stringAttributeValueMap.put(DynamoDBCompactionJobTracker.JOB_ID, new AttributeValue());

    ArrayList<Map<String, AttributeValue>> mapList = new ArrayList<>();
    mapList.add(stringAttributeValueMap);
    Stream<Map<String, AttributeValue>> items = mapList.stream();

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> DynamoDBCompactionJobStatusFormat.streamJobStatuses(items));
  }

  /**
   * Test {@link DynamoDBCompactionJobStatusFormat#streamJobStatuses(Stream)}.
   *
   * <ul>
   *   <li>Given {@link AttributeValue#AttributeValue(String)} with s is {@link
   *       DynamoDBCompactionJobTracker#JOB_ID} NS is {@link ArrayList#ArrayList()}.
   * </ul>
   *
   * <p>Method under test: {@link DynamoDBCompactionJobStatusFormat#streamJobStatuses(Stream)}
   */
  @Test
  @DisplayName(
      "Test streamJobStatuses(Stream); given AttributeValue(String) with s is JOB_ID NS is ArrayList()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Stream DynamoDBCompactionJobStatusFormat.streamJobStatuses(Stream)"})
  void testStreamJobStatuses_givenAttributeValueWithSIsJob_idNsIsArrayList() {
    // Arrange
    AttributeValue attributeValue = new AttributeValue(DynamoDBCompactionJobTracker.JOB_ID);
    attributeValue.setNS(new ArrayList<>());
    attributeValue.addMEntry("UpdateType", new AttributeValue());

    HashMap<String, AttributeValue> stringAttributeValueMap = new HashMap<>();
    stringAttributeValueMap.put("UpdateType", attributeValue);
    stringAttributeValueMap.put(DynamoDBCompactionJobTracker.JOB_ID, new AttributeValue());

    ArrayList<Map<String, AttributeValue>> mapList = new ArrayList<>();
    mapList.add(stringAttributeValueMap);
    Stream<Map<String, AttributeValue>> items = mapList.stream();

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> DynamoDBCompactionJobStatusFormat.streamJobStatuses(items));
  }

  /**
   * Test {@link DynamoDBCompactionJobStatusFormat#streamJobStatuses(Stream)}.
   *
   * <ul>
   *   <li>Given {@link AttributeValue#AttributeValue(String)} with s is {@link
   *       DynamoDBCompactionJobTracker#JOB_ID} NULL is {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DynamoDBCompactionJobStatusFormat#streamJobStatuses(Stream)}
   */
  @Test
  @DisplayName(
      "Test streamJobStatuses(Stream); given AttributeValue(String) with s is JOB_ID NULL is 'true'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Stream DynamoDBCompactionJobStatusFormat.streamJobStatuses(Stream)"})
  void testStreamJobStatuses_givenAttributeValueWithSIsJob_idNullIsTrue() {
    // Arrange
    AttributeValue attributeValue = new AttributeValue(DynamoDBCompactionJobTracker.JOB_ID);
    attributeValue.setNULL(true);
    attributeValue.addMEntry("UpdateType", new AttributeValue());

    HashMap<String, AttributeValue> stringAttributeValueMap = new HashMap<>();
    stringAttributeValueMap.put("UpdateType", attributeValue);
    stringAttributeValueMap.put(DynamoDBCompactionJobTracker.JOB_ID, new AttributeValue());

    ArrayList<Map<String, AttributeValue>> mapList = new ArrayList<>();
    mapList.add(stringAttributeValueMap);
    Stream<Map<String, AttributeValue>> items = mapList.stream();

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> DynamoDBCompactionJobStatusFormat.streamJobStatuses(items));
  }

  /**
   * Test {@link DynamoDBCompactionJobStatusFormat#streamJobStatuses(Stream)}.
   *
   * <ul>
   *   <li>Given {@link AttributeValue#AttributeValue(String)} with s is {@link
   *       DynamoDBCompactionJobTracker#JOB_ID} SS is {@link ArrayList#ArrayList()}.
   * </ul>
   *
   * <p>Method under test: {@link DynamoDBCompactionJobStatusFormat#streamJobStatuses(Stream)}
   */
  @Test
  @DisplayName(
      "Test streamJobStatuses(Stream); given AttributeValue(String) with s is JOB_ID SS is ArrayList()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Stream DynamoDBCompactionJobStatusFormat.streamJobStatuses(Stream)"})
  void testStreamJobStatuses_givenAttributeValueWithSIsJob_idSsIsArrayList() {
    // Arrange
    AttributeValue attributeValue = new AttributeValue(DynamoDBCompactionJobTracker.JOB_ID);
    attributeValue.setSS(new ArrayList<>());
    attributeValue.addMEntry("UpdateType", new AttributeValue());

    HashMap<String, AttributeValue> stringAttributeValueMap = new HashMap<>();
    stringAttributeValueMap.put("UpdateType", attributeValue);
    stringAttributeValueMap.put(DynamoDBCompactionJobTracker.JOB_ID, new AttributeValue());

    ArrayList<Map<String, AttributeValue>> mapList = new ArrayList<>();
    mapList.add(stringAttributeValueMap);
    Stream<Map<String, AttributeValue>> items = mapList.stream();

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> DynamoDBCompactionJobStatusFormat.streamJobStatuses(items));
  }

  /**
   * Test {@link DynamoDBCompactionJobStatusFormat#streamJobStatuses(Stream)}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link DynamoDBCompactionJobStatusFormat#streamJobStatuses(Stream)}
   */
  @Test
  @DisplayName("Test streamJobStatuses(Stream); then throw IllegalArgumentException")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Stream DynamoDBCompactionJobStatusFormat.streamJobStatuses(Stream)"})
  void testStreamJobStatuses_thenThrowIllegalArgumentException() {
    // Arrange
    HashMap<String, AttributeValue> stringAttributeValueMap = new HashMap<>();
    stringAttributeValueMap.put(
        "UpdateType", new AttributeValue(DynamoDBCompactionJobTracker.JOB_ID));
    stringAttributeValueMap.put(DynamoDBCompactionJobTracker.JOB_ID, new AttributeValue());

    ArrayList<Map<String, AttributeValue>> mapList = new ArrayList<>();
    mapList.add(stringAttributeValueMap);
    Stream<Map<String, AttributeValue>> items = mapList.stream();

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> DynamoDBCompactionJobStatusFormat.streamJobStatuses(items));
  }

  /**
   * Test {@link DynamoDBCompactionJobStatusFormat#streamJobStatuses(Stream)}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()} stream.
   *   <li>Then return limit five collect toList Empty.
   * </ul>
   *
   * <p>Method under test: {@link DynamoDBCompactionJobStatusFormat#streamJobStatuses(Stream)}
   */
  @Test
  @DisplayName(
      "Test streamJobStatuses(Stream); when ArrayList() stream; then return limit five collect toList Empty")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Stream DynamoDBCompactionJobStatusFormat.streamJobStatuses(Stream)"})
  void testStreamJobStatuses_whenArrayListStream_thenReturnLimitFiveCollectToListEmpty() {
    // Arrange
    ArrayList<Map<String, AttributeValue>> mapList = new ArrayList<>();
    Stream<Map<String, AttributeValue>> items = mapList.stream();

    // Act
    Stream<CompactionJobStatus> actualStreamJobStatusesResult =
        DynamoDBCompactionJobStatusFormat.streamJobStatuses(items);

    // Assert
    assertTrue(actualStreamJobStatusesResult.limit(5).collect(Collectors.toList()).isEmpty());
  }
}
