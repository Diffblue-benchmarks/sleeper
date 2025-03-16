package sleeper.ingest.tracker.job;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
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
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.tracker.ingest.job.query.IngestJobStatus;
import sleeper.core.tracker.ingest.job.update.IngestJobAddedFilesEvent;
import sleeper.core.tracker.ingest.job.update.IngestJobFailedEvent;
import sleeper.core.tracker.ingest.job.update.IngestJobFinishedEvent;
import sleeper.core.tracker.ingest.job.update.IngestJobStartedEvent;
import sleeper.core.tracker.ingest.job.update.IngestJobValidatedEvent;
import sleeper.core.tracker.ingest.job.update.IngestJobValidatedEvent.Builder;
import sleeper.core.tracker.job.run.RecordsProcessed;
import sleeper.dynamodb.tools.DynamoDBRecordBuilder;

class DynamoDBIngestJobStatusFormatDiffblueTest {
  /**
   * Test {@link DynamoDBIngestJobStatusFormat#createJobValidatedUpdate(IngestJobValidatedEvent, DynamoDBRecordBuilder)}.
   * <p>
   * Method under test: {@link DynamoDBIngestJobStatusFormat#createJobValidatedUpdate(IngestJobValidatedEvent, DynamoDBRecordBuilder)}
   */
  @Test
  @DisplayName("Test createJobValidatedUpdate(IngestJobValidatedEvent, DynamoDBRecordBuilder)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "Map DynamoDBIngestJobStatusFormat.createJobValidatedUpdate(IngestJobValidatedEvent, DynamoDBRecordBuilder)"})
  void testCreateJobValidatedUpdate() {
    // Arrange
    Builder jsonMessageResult = IngestJobValidatedEvent.builder()
        .fileCount(3)
        .jobId("42")
        .jobRunId("42")
        .jsonMessage("Json Message");
    Builder tableIdResult = jsonMessageResult.reasons(new ArrayList<>()).tableId("42");
    IngestJobValidatedEvent event = tableIdResult
        .validationTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();
    DynamoDBRecordBuilder builder = new DynamoDBRecordBuilder();

    // Act
    Map<String, AttributeValue> actualCreateJobValidatedUpdateResult = DynamoDBIngestJobStatusFormat
        .createJobValidatedUpdate(event, builder);

    // Assert
    Map<String, AttributeValue> buildResult = builder.build();
    assertEquals(7, buildResult.size());
    assertEquals(7, actualCreateJobValidatedUpdateResult.size());
    assertTrue(buildResult.containsKey("InputFilesCount"));
    assertTrue(buildResult.containsKey("JobRunId"));
    assertTrue(buildResult.containsKey("JsonMessage"));
    assertTrue(buildResult.containsKey("ValidationReasons"));
    assertTrue(buildResult.containsKey("ValidationResult"));
    assertTrue(buildResult.containsKey("ValidationTime"));
    assertTrue(actualCreateJobValidatedUpdateResult.containsKey("InputFilesCount"));
    assertTrue(actualCreateJobValidatedUpdateResult.containsKey("JobRunId"));
    assertTrue(actualCreateJobValidatedUpdateResult.containsKey("JsonMessage"));
    assertTrue(actualCreateJobValidatedUpdateResult.containsKey("UpdateType"));
    assertTrue(actualCreateJobValidatedUpdateResult.containsKey("ValidationReasons"));
    assertTrue(actualCreateJobValidatedUpdateResult.containsKey("ValidationResult"));
    assertTrue(actualCreateJobValidatedUpdateResult.containsKey("ValidationTime"));
  }

  /**
   * Test {@link DynamoDBIngestJobStatusFormat#createJobValidatedUpdate(IngestJobValidatedEvent, DynamoDBRecordBuilder)}.
   * <ul>
   *   <li>Given {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBIngestJobStatusFormat#createJobValidatedUpdate(IngestJobValidatedEvent, DynamoDBRecordBuilder)}
   */
  @Test
  @DisplayName("Test createJobValidatedUpdate(IngestJobValidatedEvent, DynamoDBRecordBuilder); given 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "Map DynamoDBIngestJobStatusFormat.createJobValidatedUpdate(IngestJobValidatedEvent, DynamoDBRecordBuilder)"})
  void testCreateJobValidatedUpdate_givenFalse() {
    // Arrange
    IngestJobValidatedEvent event = mock(IngestJobValidatedEvent.class);
    when(event.isAccepted()).thenReturn(false);
    when(event.getFileCount()).thenReturn(3);
    when(event.getJobRunId()).thenReturn("42");
    when(event.getJsonMessage()).thenReturn("Json Message");
    when(event.getReasons()).thenReturn(new ArrayList<>());
    when(event.getValidationTime())
        .thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    DynamoDBRecordBuilder builder = new DynamoDBRecordBuilder();

    // Act
    Map<String, AttributeValue> actualCreateJobValidatedUpdateResult = DynamoDBIngestJobStatusFormat
        .createJobValidatedUpdate(event, builder);

    // Assert
    verify(event).getFileCount();
    verify(event).getJobRunId();
    verify(event).getJsonMessage();
    verify(event).getReasons();
    verify(event).getValidationTime();
    verify(event).isAccepted();
    Map<String, AttributeValue> buildResult = builder.build();
    assertEquals(7, buildResult.size());
    assertEquals(7, actualCreateJobValidatedUpdateResult.size());
    assertTrue(buildResult.containsKey("InputFilesCount"));
    assertTrue(buildResult.containsKey("JobRunId"));
    assertTrue(buildResult.containsKey("JsonMessage"));
    assertTrue(buildResult.containsKey("ValidationReasons"));
    assertTrue(buildResult.containsKey("ValidationResult"));
    assertTrue(buildResult.containsKey("ValidationTime"));
    assertTrue(actualCreateJobValidatedUpdateResult.containsKey("InputFilesCount"));
    assertTrue(actualCreateJobValidatedUpdateResult.containsKey("JobRunId"));
    assertTrue(actualCreateJobValidatedUpdateResult.containsKey("JsonMessage"));
    assertTrue(actualCreateJobValidatedUpdateResult.containsKey("UpdateType"));
    assertTrue(actualCreateJobValidatedUpdateResult.containsKey("ValidationReasons"));
    assertTrue(actualCreateJobValidatedUpdateResult.containsKey("ValidationResult"));
    assertTrue(actualCreateJobValidatedUpdateResult.containsKey("ValidationTime"));
  }

  /**
   * Test {@link DynamoDBIngestJobStatusFormat#createJobValidatedUpdate(IngestJobValidatedEvent, DynamoDBRecordBuilder)}.
   * <ul>
   *   <li>Then return {@code ValidationReasons} L size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBIngestJobStatusFormat#createJobValidatedUpdate(IngestJobValidatedEvent, DynamoDBRecordBuilder)}
   */
  @Test
  @DisplayName("Test createJobValidatedUpdate(IngestJobValidatedEvent, DynamoDBRecordBuilder); then return 'ValidationReasons' L size is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "Map DynamoDBIngestJobStatusFormat.createJobValidatedUpdate(IngestJobValidatedEvent, DynamoDBRecordBuilder)"})
  void testCreateJobValidatedUpdate_thenReturnValidationReasonsLSizeIsOne() {
    // Arrange
    ArrayList<String> stringList = new ArrayList<>();
    stringList.add("foo");
    IngestJobValidatedEvent event = mock(IngestJobValidatedEvent.class);
    when(event.isAccepted()).thenReturn(true);
    when(event.getFileCount()).thenReturn(3);
    when(event.getJobRunId()).thenReturn("42");
    when(event.getJsonMessage()).thenReturn("Json Message");
    when(event.getReasons()).thenReturn(stringList);
    when(event.getValidationTime())
        .thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act
    Map<String, AttributeValue> actualCreateJobValidatedUpdateResult = DynamoDBIngestJobStatusFormat
        .createJobValidatedUpdate(event, new DynamoDBRecordBuilder());

    // Assert
    verify(event).getFileCount();
    verify(event).getJobRunId();
    verify(event).getJsonMessage();
    verify(event).getReasons();
    verify(event).getValidationTime();
    verify(event).isAccepted();
    assertEquals(7, actualCreateJobValidatedUpdateResult.size());
    List<AttributeValue> l = actualCreateJobValidatedUpdateResult.get("ValidationReasons").getL();
    assertEquals(1, l.size());
    AttributeValue getResult = l.get(0);
    assertEquals("foo", getResult.getS());
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
    assertTrue(actualCreateJobValidatedUpdateResult.containsKey("InputFilesCount"));
    assertTrue(actualCreateJobValidatedUpdateResult.containsKey("JobRunId"));
    assertTrue(actualCreateJobValidatedUpdateResult.containsKey("JsonMessage"));
    assertTrue(actualCreateJobValidatedUpdateResult.containsKey("UpdateType"));
    assertTrue(actualCreateJobValidatedUpdateResult.containsKey("ValidationResult"));
    assertTrue(actualCreateJobValidatedUpdateResult.containsKey("ValidationTime"));
  }

  /**
   * Test {@link DynamoDBIngestJobStatusFormat#createJobValidatedUpdate(IngestJobValidatedEvent, DynamoDBRecordBuilder)}.
   * <ul>
   *   <li>Then return {@code ValidationReasons} L size is two.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBIngestJobStatusFormat#createJobValidatedUpdate(IngestJobValidatedEvent, DynamoDBRecordBuilder)}
   */
  @Test
  @DisplayName("Test createJobValidatedUpdate(IngestJobValidatedEvent, DynamoDBRecordBuilder); then return 'ValidationReasons' L size is two")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "Map DynamoDBIngestJobStatusFormat.createJobValidatedUpdate(IngestJobValidatedEvent, DynamoDBRecordBuilder)"})
  void testCreateJobValidatedUpdate_thenReturnValidationReasonsLSizeIsTwo() {
    // Arrange
    ArrayList<String> stringList = new ArrayList<>();
    stringList.add("validated");
    stringList.add("foo");
    IngestJobValidatedEvent event = mock(IngestJobValidatedEvent.class);
    when(event.isAccepted()).thenReturn(true);
    when(event.getFileCount()).thenReturn(3);
    when(event.getJobRunId()).thenReturn("42");
    when(event.getJsonMessage()).thenReturn("Json Message");
    when(event.getReasons()).thenReturn(stringList);
    when(event.getValidationTime())
        .thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act
    Map<String, AttributeValue> actualCreateJobValidatedUpdateResult = DynamoDBIngestJobStatusFormat
        .createJobValidatedUpdate(event, new DynamoDBRecordBuilder());

    // Assert
    verify(event).getFileCount();
    verify(event).getJobRunId();
    verify(event).getJsonMessage();
    verify(event).getReasons();
    verify(event).getValidationTime();
    verify(event).isAccepted();
    assertEquals(7, actualCreateJobValidatedUpdateResult.size());
    List<AttributeValue> l = actualCreateJobValidatedUpdateResult.get("ValidationReasons").getL();
    assertEquals(2, l.size());
    AttributeValue getResult = l.get(1);
    assertEquals("foo", getResult.getS());
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
    assertTrue(actualCreateJobValidatedUpdateResult.containsKey("InputFilesCount"));
    assertTrue(actualCreateJobValidatedUpdateResult.containsKey("JobRunId"));
    assertTrue(actualCreateJobValidatedUpdateResult.containsKey("JsonMessage"));
    assertTrue(actualCreateJobValidatedUpdateResult.containsKey("UpdateType"));
    assertTrue(actualCreateJobValidatedUpdateResult.containsKey("ValidationResult"));
    assertTrue(actualCreateJobValidatedUpdateResult.containsKey("ValidationTime"));
    assertEquals(actualCreateJobValidatedUpdateResult.get("UpdateType"), l.get(0));
  }

  /**
   * Test {@link DynamoDBIngestJobStatusFormat#createJobValidatedUpdate(IngestJobValidatedEvent, DynamoDBRecordBuilder)}.
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBIngestJobStatusFormat#createJobValidatedUpdate(IngestJobValidatedEvent, DynamoDBRecordBuilder)}
   */
  @Test
  @DisplayName("Test createJobValidatedUpdate(IngestJobValidatedEvent, DynamoDBRecordBuilder); then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "Map DynamoDBIngestJobStatusFormat.createJobValidatedUpdate(IngestJobValidatedEvent, DynamoDBRecordBuilder)"})
  void testCreateJobValidatedUpdate_thenThrowIllegalArgumentException() {
    // Arrange
    IngestJobValidatedEvent event = mock(IngestJobValidatedEvent.class);
    when(event.isAccepted()).thenThrow(new IllegalArgumentException("UpdateType"));
    when(event.getValidationTime())
        .thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> DynamoDBIngestJobStatusFormat.createJobValidatedUpdate(event, new DynamoDBRecordBuilder()));
    verify(event).getValidationTime();
    verify(event).isAccepted();
  }

  /**
   * Test {@link DynamoDBIngestJobStatusFormat#getValidationResult(IngestJobValidatedEvent)}.
   * <ul>
   *   <li>Given {@code false}.</li>
   *   <li>Then return {@code REJECTED}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBIngestJobStatusFormat#getValidationResult(IngestJobValidatedEvent)}
   */
  @Test
  @DisplayName("Test getValidationResult(IngestJobValidatedEvent); given 'false'; then return 'REJECTED'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String DynamoDBIngestJobStatusFormat.getValidationResult(IngestJobValidatedEvent)"})
  void testGetValidationResult_givenFalse_thenReturnRejected() {
    // Arrange
    IngestJobValidatedEvent event = mock(IngestJobValidatedEvent.class);
    when(event.isAccepted()).thenReturn(false);

    // Act
    String actualValidationResult = DynamoDBIngestJobStatusFormat.getValidationResult(event);

    // Assert
    verify(event).isAccepted();
    assertEquals("REJECTED", actualValidationResult);
  }

  /**
   * Test {@link DynamoDBIngestJobStatusFormat#getValidationResult(IngestJobValidatedEvent)}.
   * <ul>
   *   <li>Then return {@code ACCEPTED}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBIngestJobStatusFormat#getValidationResult(IngestJobValidatedEvent)}
   */
  @Test
  @DisplayName("Test getValidationResult(IngestJobValidatedEvent); then return 'ACCEPTED'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String DynamoDBIngestJobStatusFormat.getValidationResult(IngestJobValidatedEvent)"})
  void testGetValidationResult_thenReturnAccepted() {
    // Arrange
    Builder jsonMessageResult = IngestJobValidatedEvent.builder()
        .fileCount(3)
        .jobId("42")
        .jobRunId("42")
        .jsonMessage("Json Message");
    Builder tableIdResult = jsonMessageResult.reasons(new ArrayList<>()).tableId("42");
    IngestJobValidatedEvent event = tableIdResult
        .validationTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();

    // Act and Assert
    assertEquals("ACCEPTED", DynamoDBIngestJobStatusFormat.getValidationResult(event));
  }

  /**
   * Test {@link DynamoDBIngestJobStatusFormat#getValidationResult(IngestJobValidatedEvent)}.
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBIngestJobStatusFormat#getValidationResult(IngestJobValidatedEvent)}
   */
  @Test
  @DisplayName("Test getValidationResult(IngestJobValidatedEvent); then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String DynamoDBIngestJobStatusFormat.getValidationResult(IngestJobValidatedEvent)"})
  void testGetValidationResult_thenThrowIllegalArgumentException() {
    // Arrange
    IngestJobValidatedEvent event = mock(IngestJobValidatedEvent.class);
    when(event.isAccepted()).thenThrow(new IllegalArgumentException("REJECTED"));

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> DynamoDBIngestJobStatusFormat.getValidationResult(event));
    verify(event).isAccepted();
  }

  /**
   * Test {@link DynamoDBIngestJobStatusFormat#createJobStartedUpdate(IngestJobStartedEvent, DynamoDBRecordBuilder)}.
   * <ul>
   *   <li>Then {@link DynamoDBRecordBuilder} (default constructor) build size is five.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBIngestJobStatusFormat#createJobStartedUpdate(IngestJobStartedEvent, DynamoDBRecordBuilder)}
   */
  @Test
  @DisplayName("Test createJobStartedUpdate(IngestJobStartedEvent, DynamoDBRecordBuilder); then DynamoDBRecordBuilder (default constructor) build size is five")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "Map DynamoDBIngestJobStatusFormat.createJobStartedUpdate(IngestJobStartedEvent, DynamoDBRecordBuilder)"})
  void testCreateJobStartedUpdate_thenDynamoDBRecordBuilderBuildSizeIsFive() {
    // Arrange
    IngestJobStartedEvent.Builder jobRunIdResult = IngestJobStartedEvent.builder()
        .fileCount(3)
        .jobId("42")
        .jobRunId("42");
    IngestJobStartedEvent event = jobRunIdResult
        .startTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .tableId("42")
        .taskId("42")
        .build();
    DynamoDBRecordBuilder builder = new DynamoDBRecordBuilder();

    // Act
    Map<String, AttributeValue> actualCreateJobStartedUpdateResult = DynamoDBIngestJobStatusFormat
        .createJobStartedUpdate(event, builder);

    // Assert
    Map<String, AttributeValue> buildResult = builder.build();
    assertEquals(5, buildResult.size());
    assertEquals(5, actualCreateJobStartedUpdateResult.size());
    assertTrue(buildResult.containsKey("InputFilesCount"));
    assertTrue(buildResult.containsKey("JobRunId"));
    assertTrue(buildResult.containsKey("StartTime"));
    assertTrue(buildResult.containsKey("TaskId"));
    assertTrue(buildResult.containsKey("UpdateType"));
    assertTrue(actualCreateJobStartedUpdateResult.containsKey("InputFilesCount"));
    assertTrue(actualCreateJobStartedUpdateResult.containsKey("StartTime"));
    assertTrue(actualCreateJobStartedUpdateResult.containsKey("TaskId"));
    assertTrue(actualCreateJobStartedUpdateResult.containsKey("UpdateType"));
    AttributeValue expectedGetResult = actualCreateJobStartedUpdateResult.get("TaskId");
    assertEquals(expectedGetResult, actualCreateJobStartedUpdateResult.get("JobRunId"));
  }

  /**
   * Test {@link DynamoDBIngestJobStatusFormat#createJobStartedUpdate(IngestJobStartedEvent, DynamoDBRecordBuilder)}.
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBIngestJobStatusFormat#createJobStartedUpdate(IngestJobStartedEvent, DynamoDBRecordBuilder)}
   */
  @Test
  @DisplayName("Test createJobStartedUpdate(IngestJobStartedEvent, DynamoDBRecordBuilder); then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "Map DynamoDBIngestJobStatusFormat.createJobStartedUpdate(IngestJobStartedEvent, DynamoDBRecordBuilder)"})
  void testCreateJobStartedUpdate_thenThrowIllegalArgumentException() {
    // Arrange
    IngestJobStartedEvent event = mock(IngestJobStartedEvent.class);
    when(event.getJobRunId()).thenThrow(new IllegalArgumentException("UpdateType"));
    when(event.getStartTime()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> DynamoDBIngestJobStatusFormat.createJobStartedUpdate(event, new DynamoDBRecordBuilder()));
    verify(event).getJobRunId();
    verify(event).getStartTime();
  }

  /**
   * Test {@link DynamoDBIngestJobStatusFormat#createJobAddedFilesUpdate(IngestJobAddedFilesEvent, DynamoDBRecordBuilder)}.
   * <ul>
   *   <li>Then {@link DynamoDBRecordBuilder} (default constructor) build size is five.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBIngestJobStatusFormat#createJobAddedFilesUpdate(IngestJobAddedFilesEvent, DynamoDBRecordBuilder)}
   */
  @Test
  @DisplayName("Test createJobAddedFilesUpdate(IngestJobAddedFilesEvent, DynamoDBRecordBuilder); then DynamoDBRecordBuilder (default constructor) build size is five")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "Map DynamoDBIngestJobStatusFormat.createJobAddedFilesUpdate(IngestJobAddedFilesEvent, DynamoDBRecordBuilder)"})
  void testCreateJobAddedFilesUpdate_thenDynamoDBRecordBuilderBuildSizeIsFive() {
    // Arrange
    IngestJobAddedFilesEvent.Builder builderResult = IngestJobAddedFilesEvent.builder();
    IngestJobAddedFilesEvent.Builder taskIdResult = builderResult.files(new ArrayList<>())
        .jobId("42")
        .jobRunId("42")
        .tableId("42")
        .taskId("42");
    IngestJobAddedFilesEvent event = taskIdResult
        .writtenTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();
    DynamoDBRecordBuilder builder = new DynamoDBRecordBuilder();

    // Act
    Map<String, AttributeValue> actualCreateJobAddedFilesUpdateResult = DynamoDBIngestJobStatusFormat
        .createJobAddedFilesUpdate(event, builder);

    // Assert
    Map<String, AttributeValue> buildResult = builder.build();
    assertEquals(5, buildResult.size());
    assertEquals(5, actualCreateJobAddedFilesUpdateResult.size());
    assertTrue(buildResult.containsKey("FilesWrittenCount"));
    assertTrue(buildResult.containsKey("FilesWrittenTime"));
    assertTrue(buildResult.containsKey("JobRunId"));
    assertTrue(buildResult.containsKey("TaskId"));
    assertTrue(buildResult.containsKey("UpdateType"));
    assertTrue(actualCreateJobAddedFilesUpdateResult.containsKey("FilesWrittenTime"));
    assertTrue(actualCreateJobAddedFilesUpdateResult.containsKey("TaskId"));
    assertTrue(actualCreateJobAddedFilesUpdateResult.containsKey("UpdateType"));
    AttributeValue expectedGetResult = actualCreateJobAddedFilesUpdateResult.get("FilesWrittenTime");
    assertEquals(expectedGetResult, actualCreateJobAddedFilesUpdateResult.get("FilesWrittenCount"));
    AttributeValue expectedGetResult2 = actualCreateJobAddedFilesUpdateResult.get("TaskId");
    assertEquals(expectedGetResult2, actualCreateJobAddedFilesUpdateResult.get("JobRunId"));
  }

  /**
   * Test {@link DynamoDBIngestJobStatusFormat#createJobAddedFilesUpdate(IngestJobAddedFilesEvent, DynamoDBRecordBuilder)}.
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBIngestJobStatusFormat#createJobAddedFilesUpdate(IngestJobAddedFilesEvent, DynamoDBRecordBuilder)}
   */
  @Test
  @DisplayName("Test createJobAddedFilesUpdate(IngestJobAddedFilesEvent, DynamoDBRecordBuilder); then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "Map DynamoDBIngestJobStatusFormat.createJobAddedFilesUpdate(IngestJobAddedFilesEvent, DynamoDBRecordBuilder)"})
  void testCreateJobAddedFilesUpdate_thenThrowIllegalArgumentException() {
    // Arrange
    IngestJobAddedFilesEvent event = mock(IngestJobAddedFilesEvent.class);
    when(event.getJobRunId()).thenThrow(new IllegalArgumentException("UpdateType"));
    when(event.getWrittenTime()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> DynamoDBIngestJobStatusFormat.createJobAddedFilesUpdate(event, new DynamoDBRecordBuilder()));
    verify(event).getJobRunId();
    verify(event).getWrittenTime();
  }

  /**
   * Test {@link DynamoDBIngestJobStatusFormat#createJobFinishedUpdate(IngestJobFinishedEvent, DynamoDBRecordBuilder)}.
   * <ul>
   *   <li>Given {@code true}.</li>
   *   <li>Then {@link DynamoDBRecordBuilder} (default constructor) build size is eight.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBIngestJobStatusFormat#createJobFinishedUpdate(IngestJobFinishedEvent, DynamoDBRecordBuilder)}
   */
  @Test
  @DisplayName("Test createJobFinishedUpdate(IngestJobFinishedEvent, DynamoDBRecordBuilder); given 'true'; then DynamoDBRecordBuilder (default constructor) build size is eight")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "Map DynamoDBIngestJobStatusFormat.createJobFinishedUpdate(IngestJobFinishedEvent, DynamoDBRecordBuilder)"})
  void testCreateJobFinishedUpdate_givenTrue_thenDynamoDBRecordBuilderBuildSizeIsEight() {
    // Arrange
    IngestJobFinishedEvent event = mock(IngestJobFinishedEvent.class);
    when(event.isCommittedBySeparateFileUpdates()).thenReturn(true);
    when(event.getNumFilesWrittenByJob()).thenReturn(10);
    when(event.getJobRunId()).thenReturn("42");
    when(event.getTaskId()).thenReturn("42");
    when(event.getFinishTime()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    when(event.getRecordsProcessed()).thenReturn(new RecordsProcessed(1L, 1L));
    DynamoDBRecordBuilder builder = new DynamoDBRecordBuilder();

    // Act
    Map<String, AttributeValue> actualCreateJobFinishedUpdateResult = DynamoDBIngestJobStatusFormat
        .createJobFinishedUpdate(event, builder);

    // Assert
    verify(event).getFinishTime();
    verify(event).getJobRunId();
    verify(event).getNumFilesWrittenByJob();
    verify(event).getRecordsProcessed();
    verify(event).getTaskId();
    verify(event).isCommittedBySeparateFileUpdates();
    Map<String, AttributeValue> buildResult = builder.build();
    assertEquals(8, buildResult.size());
    assertEquals(8, actualCreateJobFinishedUpdateResult.size());
    assertTrue(buildResult.containsKey("FinishTime"));
    assertTrue(buildResult.containsKey("JobCommittedWhenFilesAdded"));
    assertTrue(buildResult.containsKey("JobRunId"));
    assertTrue(buildResult.containsKey("RecordsRead"));
    assertTrue(buildResult.containsKey("RecordsWritten"));
    assertTrue(buildResult.containsKey("TaskId"));
    assertTrue(actualCreateJobFinishedUpdateResult.containsKey("FilesWrittenCount"));
    assertTrue(actualCreateJobFinishedUpdateResult.containsKey("FinishTime"));
    assertTrue(actualCreateJobFinishedUpdateResult.containsKey("JobCommittedWhenFilesAdded"));
    assertTrue(actualCreateJobFinishedUpdateResult.containsKey("RecordsWritten"));
    assertTrue(actualCreateJobFinishedUpdateResult.containsKey("TaskId"));
    assertTrue(actualCreateJobFinishedUpdateResult.containsKey("UpdateType"));
    AttributeValue expectedGetResult = actualCreateJobFinishedUpdateResult.get("RecordsWritten");
    assertEquals(expectedGetResult, actualCreateJobFinishedUpdateResult.get("RecordsRead"));
    AttributeValue expectedGetResult2 = actualCreateJobFinishedUpdateResult.get("TaskId");
    assertEquals(expectedGetResult2, actualCreateJobFinishedUpdateResult.get("JobRunId"));
  }

  /**
   * Test {@link DynamoDBIngestJobStatusFormat#createJobFinishedUpdate(IngestJobFinishedEvent, DynamoDBRecordBuilder)}.
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBIngestJobStatusFormat#createJobFinishedUpdate(IngestJobFinishedEvent, DynamoDBRecordBuilder)}
   */
  @Test
  @DisplayName("Test createJobFinishedUpdate(IngestJobFinishedEvent, DynamoDBRecordBuilder); then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "Map DynamoDBIngestJobStatusFormat.createJobFinishedUpdate(IngestJobFinishedEvent, DynamoDBRecordBuilder)"})
  void testCreateJobFinishedUpdate_thenThrowIllegalArgumentException() {
    // Arrange
    IngestJobFinishedEvent event = mock(IngestJobFinishedEvent.class);
    when(event.isCommittedBySeparateFileUpdates()).thenThrow(new IllegalArgumentException("UpdateType"));
    when(event.getJobRunId()).thenReturn("42");
    when(event.getTaskId()).thenReturn("42");
    when(event.getFinishTime()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    when(event.getRecordsProcessed()).thenReturn(new RecordsProcessed(1L, 1L));

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> DynamoDBIngestJobStatusFormat.createJobFinishedUpdate(event, new DynamoDBRecordBuilder()));
    verify(event).getFinishTime();
    verify(event).getJobRunId();
    verify(event).getRecordsProcessed();
    verify(event).getTaskId();
    verify(event).isCommittedBySeparateFileUpdates();
  }

  /**
   * Test {@link DynamoDBIngestJobStatusFormat#createJobFailedUpdate(IngestJobFailedEvent, DynamoDBRecordBuilder)}.
   * <ul>
   *   <li>Then {@link DynamoDBRecordBuilder} (default constructor) build size is five.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBIngestJobStatusFormat#createJobFailedUpdate(IngestJobFailedEvent, DynamoDBRecordBuilder)}
   */
  @Test
  @DisplayName("Test createJobFailedUpdate(IngestJobFailedEvent, DynamoDBRecordBuilder); then DynamoDBRecordBuilder (default constructor) build size is five")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "Map DynamoDBIngestJobStatusFormat.createJobFailedUpdate(IngestJobFailedEvent, DynamoDBRecordBuilder)"})
  void testCreateJobFailedUpdate_thenDynamoDBRecordBuilderBuildSizeIsFive() {
    // Arrange
    IngestJobFailedEvent.Builder builderResult = IngestJobFailedEvent.builder();
    IngestJobFailedEvent.Builder failureReasonsResult = builderResult.failureReasons(new ArrayList<>());
    IngestJobFailedEvent event = failureReasonsResult
        .failureTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .jobId("42")
        .jobRunId("42")
        .tableId("42")
        .taskId("42")
        .build();
    DynamoDBRecordBuilder builder = new DynamoDBRecordBuilder();

    // Act
    Map<String, AttributeValue> actualCreateJobFailedUpdateResult = DynamoDBIngestJobStatusFormat
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
   * Test {@link DynamoDBIngestJobStatusFormat#createJobFailedUpdate(IngestJobFailedEvent, DynamoDBRecordBuilder)}.
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBIngestJobStatusFormat#createJobFailedUpdate(IngestJobFailedEvent, DynamoDBRecordBuilder)}
   */
  @Test
  @DisplayName("Test createJobFailedUpdate(IngestJobFailedEvent, DynamoDBRecordBuilder); then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "Map DynamoDBIngestJobStatusFormat.createJobFailedUpdate(IngestJobFailedEvent, DynamoDBRecordBuilder)"})
  void testCreateJobFailedUpdate_thenThrowIllegalArgumentException() {
    // Arrange
    IngestJobFailedEvent event = mock(IngestJobFailedEvent.class);
    when(event.getFailureReasons()).thenThrow(new IllegalArgumentException("foo"));
    when(event.getJobRunId()).thenReturn("42");
    when(event.getTaskId()).thenReturn("42");
    when(event.getFailureTime()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> DynamoDBIngestJobStatusFormat.createJobFailedUpdate(event, new DynamoDBRecordBuilder()));
    verify(event).getFailureReasons();
    verify(event).getFailureTime();
    verify(event).getJobRunId();
    verify(event).getTaskId();
  }

  /**
   * Test {@link DynamoDBIngestJobStatusFormat#jobUpdateBuilder(String, String, Instant, Instant)}.
   * <ul>
   *   <li>Then return build {@link DynamoDBIngestJobTracker#TABLE_ID} S is {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBIngestJobStatusFormat#jobUpdateBuilder(String, String, Instant, Instant)}
   */
  @Test
  @DisplayName("Test jobUpdateBuilder(String, String, Instant, Instant); then return build TABLE_ID S is '42'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "DynamoDBRecordBuilder DynamoDBIngestJobStatusFormat.jobUpdateBuilder(String, String, Instant, Instant)"})
  void testJobUpdateBuilder_thenReturnBuildTable_idSIs42() {
    // Arrange
    Instant timeNow = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    // Act and Assert
    Map<String, AttributeValue> buildResult = DynamoDBIngestJobStatusFormat
        .jobUpdateBuilder("42", "42", timeNow,
            LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();
    assertEquals(5, buildResult.size());
    AttributeValue getResult = buildResult.get(DynamoDBIngestJobTracker.TABLE_ID);
    assertEquals("42", getResult.getS());
    String string = DynamoDBIngestJobTracker.EXPIRY_DATE;
    assertTrue(buildResult.containsKey(string));
    assertTrue(buildResult.containsKey(DynamoDBIngestJobTracker.JOB_ID_AND_UPDATE));
    AttributeValue expectedGetResult = buildResult.get(string);
    assertEquals(expectedGetResult, buildResult.get("UpdateTime"));
    assertEquals(getResult, buildResult.get(DynamoDBIngestJobTracker.JOB_ID));
  }

  /**
   * Test {@link DynamoDBIngestJobStatusFormat#jobUpdateBuilder(String, String, Instant, Instant)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return build size is four.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBIngestJobStatusFormat#jobUpdateBuilder(String, String, Instant, Instant)}
   */
  @Test
  @DisplayName("Test jobUpdateBuilder(String, String, Instant, Instant); when 'null'; then return build size is four")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "DynamoDBRecordBuilder DynamoDBIngestJobStatusFormat.jobUpdateBuilder(String, String, Instant, Instant)"})
  void testJobUpdateBuilder_whenNull_thenReturnBuildSizeIsFour() {
    // Arrange
    Instant timeNow = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    // Act and Assert
    Map<String, AttributeValue> buildResult = DynamoDBIngestJobStatusFormat
        .jobUpdateBuilder("42", null, timeNow,
            LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();
    assertEquals(4, buildResult.size());
    String string = DynamoDBIngestJobTracker.EXPIRY_DATE;
    assertTrue(buildResult.containsKey(string));
    assertTrue(buildResult.containsKey(DynamoDBIngestJobTracker.JOB_ID_AND_UPDATE));
    assertTrue(buildResult.containsKey(DynamoDBIngestJobTracker.TABLE_ID));
    AttributeValue expectedGetResult = buildResult.get(string);
    assertEquals(expectedGetResult, buildResult.get("UpdateTime"));
  }

  /**
   * Test {@link DynamoDBIngestJobStatusFormat#jobUpdateBuilder(String, String, Instant, Instant)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return build {@link DynamoDBIngestJobTracker#TABLE_ID} S is {@code -}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBIngestJobStatusFormat#jobUpdateBuilder(String, String, Instant, Instant)}
   */
  @Test
  @DisplayName("Test jobUpdateBuilder(String, String, Instant, Instant); when 'null'; then return build TABLE_ID S is '-'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "DynamoDBRecordBuilder DynamoDBIngestJobStatusFormat.jobUpdateBuilder(String, String, Instant, Instant)"})
  void testJobUpdateBuilder_whenNull_thenReturnBuildTable_idSIsDash() {
    // Arrange
    Instant timeNow = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    // Act and Assert
    Map<String, AttributeValue> buildResult = DynamoDBIngestJobStatusFormat
        .jobUpdateBuilder(null, "42", timeNow,
            LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();
    assertEquals(5, buildResult.size());
    assertEquals("-", buildResult.get(DynamoDBIngestJobTracker.TABLE_ID).getS());
    AttributeValue getResult = buildResult.get(DynamoDBIngestJobTracker.JOB_ID);
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
    String string = DynamoDBIngestJobTracker.EXPIRY_DATE;
    assertTrue(buildResult.containsKey(string));
    assertTrue(buildResult.containsKey(DynamoDBIngestJobTracker.JOB_ID_AND_UPDATE));
    AttributeValue expectedGetResult = buildResult.get(string);
    assertEquals(expectedGetResult, buildResult.get("UpdateTime"));
  }

  /**
   * Test {@link DynamoDBIngestJobStatusFormat#streamJobStatuses(Stream)}.
   * <p>
   * Method under test: {@link DynamoDBIngestJobStatusFormat#streamJobStatuses(Stream)}
   */
  @Test
  @DisplayName("Test streamJobStatuses(Stream)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream DynamoDBIngestJobStatusFormat.streamJobStatuses(Stream)"})
  void testStreamJobStatuses() {
    // Arrange
    AttributeValue attributeValue = new AttributeValue(DynamoDBIngestJobTracker.JOB_ID);
    attributeValue.addMEntry(DynamoDBIngestJobTracker.JOB_ID, new AttributeValue(DynamoDBIngestJobTracker.JOB_ID));

    HashMap<String, AttributeValue> stringAttributeValueMap = new HashMap<>();
    stringAttributeValueMap.put("UpdateType", attributeValue);
    stringAttributeValueMap.put(DynamoDBIngestJobTracker.JOB_ID, new AttributeValue(DynamoDBIngestJobTracker.JOB_ID));

    ArrayList<Map<String, AttributeValue>> mapList = new ArrayList<>();
    mapList.add(stringAttributeValueMap);
    Stream<Map<String, AttributeValue>> items = mapList.stream();

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> DynamoDBIngestJobStatusFormat.streamJobStatuses(items));
  }

  /**
   * Test {@link DynamoDBIngestJobStatusFormat#streamJobStatuses(Stream)}.
   * <ul>
   *   <li>Given {@code A}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBIngestJobStatusFormat#streamJobStatuses(Stream)}
   */
  @Test
  @DisplayName("Test streamJobStatuses(Stream); given 'A'; then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream DynamoDBIngestJobStatusFormat.streamJobStatuses(Stream)"})
  void testStreamJobStatuses_givenA_thenThrowIllegalArgumentException() {
    // Arrange
    AttributeValue attributeValue = new AttributeValue(DynamoDBIngestJobTracker.JOB_ID);
    attributeValue.setB(ByteBuffer.wrap(new byte[]{'A', -1, 'A', -1, 'A', -1, 'A', -1}));
    attributeValue.addMEntry(DynamoDBIngestJobTracker.JOB_ID, new AttributeValue(DynamoDBIngestJobTracker.JOB_ID));

    HashMap<String, AttributeValue> stringAttributeValueMap = new HashMap<>();
    stringAttributeValueMap.put("UpdateType", attributeValue);
    stringAttributeValueMap.put(DynamoDBIngestJobTracker.JOB_ID, new AttributeValue(DynamoDBIngestJobTracker.JOB_ID));

    ArrayList<Map<String, AttributeValue>> mapList = new ArrayList<>();
    mapList.add(stringAttributeValueMap);
    Stream<Map<String, AttributeValue>> items = mapList.stream();

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> DynamoDBIngestJobStatusFormat.streamJobStatuses(items));
  }

  /**
   * Test {@link DynamoDBIngestJobStatusFormat#streamJobStatuses(Stream)}.
   * <ul>
   *   <li>Given {@link AttributeValue#AttributeValue(String)} with s is {@link DynamoDBIngestJobTracker#JOB_ID} BOOL is {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBIngestJobStatusFormat#streamJobStatuses(Stream)}
   */
  @Test
  @DisplayName("Test streamJobStatuses(Stream); given AttributeValue(String) with s is JOB_ID BOOL is 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream DynamoDBIngestJobStatusFormat.streamJobStatuses(Stream)"})
  void testStreamJobStatuses_givenAttributeValueWithSIsJob_idBoolIsTrue() {
    // Arrange
    AttributeValue attributeValue = new AttributeValue(DynamoDBIngestJobTracker.JOB_ID);
    attributeValue.setBOOL(true);
    attributeValue.addMEntry(DynamoDBIngestJobTracker.JOB_ID, new AttributeValue(DynamoDBIngestJobTracker.JOB_ID));

    HashMap<String, AttributeValue> stringAttributeValueMap = new HashMap<>();
    stringAttributeValueMap.put("UpdateType", attributeValue);
    stringAttributeValueMap.put(DynamoDBIngestJobTracker.JOB_ID, new AttributeValue(DynamoDBIngestJobTracker.JOB_ID));

    ArrayList<Map<String, AttributeValue>> mapList = new ArrayList<>();
    mapList.add(stringAttributeValueMap);
    Stream<Map<String, AttributeValue>> items = mapList.stream();

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> DynamoDBIngestJobStatusFormat.streamJobStatuses(items));
  }

  /**
   * Test {@link DynamoDBIngestJobStatusFormat#streamJobStatuses(Stream)}.
   * <ul>
   *   <li>Given {@link AttributeValue#AttributeValue(String)} with s is {@link DynamoDBIngestJobTracker#JOB_ID} BS is {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBIngestJobStatusFormat#streamJobStatuses(Stream)}
   */
  @Test
  @DisplayName("Test streamJobStatuses(Stream); given AttributeValue(String) with s is JOB_ID BS is ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream DynamoDBIngestJobStatusFormat.streamJobStatuses(Stream)"})
  void testStreamJobStatuses_givenAttributeValueWithSIsJob_idBsIsArrayList() {
    // Arrange
    AttributeValue attributeValue = new AttributeValue(DynamoDBIngestJobTracker.JOB_ID);
    attributeValue.setBS(new ArrayList<>());
    attributeValue.addMEntry(DynamoDBIngestJobTracker.JOB_ID, new AttributeValue(DynamoDBIngestJobTracker.JOB_ID));

    HashMap<String, AttributeValue> stringAttributeValueMap = new HashMap<>();
    stringAttributeValueMap.put("UpdateType", attributeValue);
    stringAttributeValueMap.put(DynamoDBIngestJobTracker.JOB_ID, new AttributeValue(DynamoDBIngestJobTracker.JOB_ID));

    ArrayList<Map<String, AttributeValue>> mapList = new ArrayList<>();
    mapList.add(stringAttributeValueMap);
    Stream<Map<String, AttributeValue>> items = mapList.stream();

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> DynamoDBIngestJobStatusFormat.streamJobStatuses(items));
  }

  /**
   * Test {@link DynamoDBIngestJobStatusFormat#streamJobStatuses(Stream)}.
   * <ul>
   *   <li>Given {@link AttributeValue#AttributeValue(String)} with s is {@link DynamoDBIngestJobTracker#JOB_ID} L is {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBIngestJobStatusFormat#streamJobStatuses(Stream)}
   */
  @Test
  @DisplayName("Test streamJobStatuses(Stream); given AttributeValue(String) with s is JOB_ID L is ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream DynamoDBIngestJobStatusFormat.streamJobStatuses(Stream)"})
  void testStreamJobStatuses_givenAttributeValueWithSIsJob_idLIsArrayList() {
    // Arrange
    AttributeValue attributeValue = new AttributeValue(DynamoDBIngestJobTracker.JOB_ID);
    attributeValue.setL(new ArrayList<>());
    attributeValue.addMEntry(DynamoDBIngestJobTracker.JOB_ID, new AttributeValue(DynamoDBIngestJobTracker.JOB_ID));

    HashMap<String, AttributeValue> stringAttributeValueMap = new HashMap<>();
    stringAttributeValueMap.put("UpdateType", attributeValue);
    stringAttributeValueMap.put(DynamoDBIngestJobTracker.JOB_ID, new AttributeValue(DynamoDBIngestJobTracker.JOB_ID));

    ArrayList<Map<String, AttributeValue>> mapList = new ArrayList<>();
    mapList.add(stringAttributeValueMap);
    Stream<Map<String, AttributeValue>> items = mapList.stream();

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> DynamoDBIngestJobStatusFormat.streamJobStatuses(items));
  }

  /**
   * Test {@link DynamoDBIngestJobStatusFormat#streamJobStatuses(Stream)}.
   * <ul>
   *   <li>Given {@link AttributeValue#AttributeValue(String)} with s is {@link DynamoDBIngestJobTracker#JOB_ID} M is {@link HashMap#HashMap()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBIngestJobStatusFormat#streamJobStatuses(Stream)}
   */
  @Test
  @DisplayName("Test streamJobStatuses(Stream); given AttributeValue(String) with s is JOB_ID M is HashMap()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream DynamoDBIngestJobStatusFormat.streamJobStatuses(Stream)"})
  void testStreamJobStatuses_givenAttributeValueWithSIsJob_idMIsHashMap() {
    // Arrange
    AttributeValue attributeValue = new AttributeValue(DynamoDBIngestJobTracker.JOB_ID);
    attributeValue.setM(new HashMap<>());

    HashMap<String, AttributeValue> stringAttributeValueMap = new HashMap<>();
    stringAttributeValueMap.put("UpdateType", attributeValue);
    stringAttributeValueMap.put(DynamoDBIngestJobTracker.JOB_ID, new AttributeValue(DynamoDBIngestJobTracker.JOB_ID));

    ArrayList<Map<String, AttributeValue>> mapList = new ArrayList<>();
    mapList.add(stringAttributeValueMap);
    Stream<Map<String, AttributeValue>> items = mapList.stream();

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> DynamoDBIngestJobStatusFormat.streamJobStatuses(items));
  }

  /**
   * Test {@link DynamoDBIngestJobStatusFormat#streamJobStatuses(Stream)}.
   * <ul>
   *   <li>Given {@link AttributeValue#AttributeValue(String)} with s is {@link DynamoDBIngestJobTracker#JOB_ID} N is {@link DynamoDBIngestJobTracker#JOB_ID}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBIngestJobStatusFormat#streamJobStatuses(Stream)}
   */
  @Test
  @DisplayName("Test streamJobStatuses(Stream); given AttributeValue(String) with s is JOB_ID N is JOB_ID")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream DynamoDBIngestJobStatusFormat.streamJobStatuses(Stream)"})
  void testStreamJobStatuses_givenAttributeValueWithSIsJob_idNIsJob_id() {
    // Arrange
    AttributeValue attributeValue = new AttributeValue(DynamoDBIngestJobTracker.JOB_ID);
    attributeValue.setN(DynamoDBIngestJobTracker.JOB_ID);
    attributeValue.addMEntry(DynamoDBIngestJobTracker.JOB_ID, new AttributeValue(DynamoDBIngestJobTracker.JOB_ID));

    HashMap<String, AttributeValue> stringAttributeValueMap = new HashMap<>();
    stringAttributeValueMap.put("UpdateType", attributeValue);
    stringAttributeValueMap.put(DynamoDBIngestJobTracker.JOB_ID, new AttributeValue(DynamoDBIngestJobTracker.JOB_ID));

    ArrayList<Map<String, AttributeValue>> mapList = new ArrayList<>();
    mapList.add(stringAttributeValueMap);
    Stream<Map<String, AttributeValue>> items = mapList.stream();

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> DynamoDBIngestJobStatusFormat.streamJobStatuses(items));
  }

  /**
   * Test {@link DynamoDBIngestJobStatusFormat#streamJobStatuses(Stream)}.
   * <ul>
   *   <li>Given {@link AttributeValue#AttributeValue(String)} with s is {@link DynamoDBIngestJobTracker#JOB_ID} NS is {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBIngestJobStatusFormat#streamJobStatuses(Stream)}
   */
  @Test
  @DisplayName("Test streamJobStatuses(Stream); given AttributeValue(String) with s is JOB_ID NS is ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream DynamoDBIngestJobStatusFormat.streamJobStatuses(Stream)"})
  void testStreamJobStatuses_givenAttributeValueWithSIsJob_idNsIsArrayList() {
    // Arrange
    AttributeValue attributeValue = new AttributeValue(DynamoDBIngestJobTracker.JOB_ID);
    attributeValue.setNS(new ArrayList<>());
    attributeValue.addMEntry(DynamoDBIngestJobTracker.JOB_ID, new AttributeValue(DynamoDBIngestJobTracker.JOB_ID));

    HashMap<String, AttributeValue> stringAttributeValueMap = new HashMap<>();
    stringAttributeValueMap.put("UpdateType", attributeValue);
    stringAttributeValueMap.put(DynamoDBIngestJobTracker.JOB_ID, new AttributeValue(DynamoDBIngestJobTracker.JOB_ID));

    ArrayList<Map<String, AttributeValue>> mapList = new ArrayList<>();
    mapList.add(stringAttributeValueMap);
    Stream<Map<String, AttributeValue>> items = mapList.stream();

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> DynamoDBIngestJobStatusFormat.streamJobStatuses(items));
  }

  /**
   * Test {@link DynamoDBIngestJobStatusFormat#streamJobStatuses(Stream)}.
   * <ul>
   *   <li>Given {@link AttributeValue#AttributeValue(String)} with s is {@link DynamoDBIngestJobTracker#JOB_ID} NULL is {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBIngestJobStatusFormat#streamJobStatuses(Stream)}
   */
  @Test
  @DisplayName("Test streamJobStatuses(Stream); given AttributeValue(String) with s is JOB_ID NULL is 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream DynamoDBIngestJobStatusFormat.streamJobStatuses(Stream)"})
  void testStreamJobStatuses_givenAttributeValueWithSIsJob_idNullIsTrue() {
    // Arrange
    AttributeValue attributeValue = new AttributeValue(DynamoDBIngestJobTracker.JOB_ID);
    attributeValue.setNULL(true);
    attributeValue.addMEntry(DynamoDBIngestJobTracker.JOB_ID, new AttributeValue(DynamoDBIngestJobTracker.JOB_ID));

    HashMap<String, AttributeValue> stringAttributeValueMap = new HashMap<>();
    stringAttributeValueMap.put("UpdateType", attributeValue);
    stringAttributeValueMap.put(DynamoDBIngestJobTracker.JOB_ID, new AttributeValue(DynamoDBIngestJobTracker.JOB_ID));

    ArrayList<Map<String, AttributeValue>> mapList = new ArrayList<>();
    mapList.add(stringAttributeValueMap);
    Stream<Map<String, AttributeValue>> items = mapList.stream();

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> DynamoDBIngestJobStatusFormat.streamJobStatuses(items));
  }

  /**
   * Test {@link DynamoDBIngestJobStatusFormat#streamJobStatuses(Stream)}.
   * <ul>
   *   <li>Given {@link AttributeValue#AttributeValue(String)} with s is {@link DynamoDBIngestJobTracker#JOB_ID} SS is {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBIngestJobStatusFormat#streamJobStatuses(Stream)}
   */
  @Test
  @DisplayName("Test streamJobStatuses(Stream); given AttributeValue(String) with s is JOB_ID SS is ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream DynamoDBIngestJobStatusFormat.streamJobStatuses(Stream)"})
  void testStreamJobStatuses_givenAttributeValueWithSIsJob_idSsIsArrayList() {
    // Arrange
    AttributeValue attributeValue = new AttributeValue(DynamoDBIngestJobTracker.JOB_ID);
    attributeValue.setSS(new ArrayList<>());
    attributeValue.addMEntry(DynamoDBIngestJobTracker.JOB_ID, new AttributeValue(DynamoDBIngestJobTracker.JOB_ID));

    HashMap<String, AttributeValue> stringAttributeValueMap = new HashMap<>();
    stringAttributeValueMap.put("UpdateType", attributeValue);
    stringAttributeValueMap.put(DynamoDBIngestJobTracker.JOB_ID, new AttributeValue(DynamoDBIngestJobTracker.JOB_ID));

    ArrayList<Map<String, AttributeValue>> mapList = new ArrayList<>();
    mapList.add(stringAttributeValueMap);
    Stream<Map<String, AttributeValue>> items = mapList.stream();

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> DynamoDBIngestJobStatusFormat.streamJobStatuses(items));
  }

  /**
   * Test {@link DynamoDBIngestJobStatusFormat#streamJobStatuses(Stream)}.
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@link DynamoDBIngestJobTracker#JOB_ID} is {@link AttributeValue#AttributeValue()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBIngestJobStatusFormat#streamJobStatuses(Stream)}
   */
  @Test
  @DisplayName("Test streamJobStatuses(Stream); given HashMap() JOB_ID is AttributeValue()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream DynamoDBIngestJobStatusFormat.streamJobStatuses(Stream)"})
  void testStreamJobStatuses_givenHashMapJob_idIsAttributeValue() {
    // Arrange
    HashMap<String, AttributeValue> stringAttributeValueMap = new HashMap<>();
    stringAttributeValueMap.put("UpdateType", new AttributeValue(DynamoDBIngestJobTracker.JOB_ID));
    stringAttributeValueMap.put(DynamoDBIngestJobTracker.JOB_ID, new AttributeValue());

    ArrayList<Map<String, AttributeValue>> mapList = new ArrayList<>();
    mapList.add(stringAttributeValueMap);
    Stream<Map<String, AttributeValue>> items = mapList.stream();

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> DynamoDBIngestJobStatusFormat.streamJobStatuses(items));
  }

  /**
   * Test {@link DynamoDBIngestJobStatusFormat#streamJobStatuses(Stream)}.
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBIngestJobStatusFormat#streamJobStatuses(Stream)}
   */
  @Test
  @DisplayName("Test streamJobStatuses(Stream); then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream DynamoDBIngestJobStatusFormat.streamJobStatuses(Stream)"})
  void testStreamJobStatuses_thenThrowIllegalArgumentException() {
    // Arrange
    HashMap<String, AttributeValue> stringAttributeValueMap = new HashMap<>();
    stringAttributeValueMap.put("UpdateType", new AttributeValue(DynamoDBIngestJobTracker.JOB_ID));
    stringAttributeValueMap.put(DynamoDBIngestJobTracker.JOB_ID, new AttributeValue(DynamoDBIngestJobTracker.JOB_ID));

    ArrayList<Map<String, AttributeValue>> mapList = new ArrayList<>();
    mapList.add(stringAttributeValueMap);
    Stream<Map<String, AttributeValue>> items = mapList.stream();

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> DynamoDBIngestJobStatusFormat.streamJobStatuses(items));
  }

  /**
   * Test {@link DynamoDBIngestJobStatusFormat#streamJobStatuses(Stream)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()} stream.</li>
   *   <li>Then return limit five collect toList Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBIngestJobStatusFormat#streamJobStatuses(Stream)}
   */
  @Test
  @DisplayName("Test streamJobStatuses(Stream); when ArrayList() stream; then return limit five collect toList Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream DynamoDBIngestJobStatusFormat.streamJobStatuses(Stream)"})
  void testStreamJobStatuses_whenArrayListStream_thenReturnLimitFiveCollectToListEmpty() {
    // Arrange
    ArrayList<Map<String, AttributeValue>> mapList = new ArrayList<>();
    Stream<Map<String, AttributeValue>> items = mapList.stream();

    // Act
    Stream<IngestJobStatus> actualStreamJobStatusesResult = DynamoDBIngestJobStatusFormat.streamJobStatuses(items);

    // Assert
    assertTrue(actualStreamJobStatusesResult.limit(5).collect(Collectors.toList()).isEmpty());
  }
}
