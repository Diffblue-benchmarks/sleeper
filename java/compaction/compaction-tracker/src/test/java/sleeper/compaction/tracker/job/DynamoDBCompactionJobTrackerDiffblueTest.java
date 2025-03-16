package sleeper.compaction.tracker.job;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.amazonaws.PredefinedClientConfigurations;
import com.amazonaws.auth.AnonymousAWSCredentials;
import com.amazonaws.auth.STSSessionCredentials;
import com.amazonaws.handlers.AsyncHandler;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClient;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;
import com.amazonaws.services.dynamodbv2.model.PutItemResult;
import com.amazonaws.services.sqs.MessageMD5ChecksumHandler;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Supplier;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.compaction.tracker.CompactionTrackerException;
import sleeper.core.properties.SleeperPropertyValues;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.instance.InstanceProperty;
import sleeper.core.tracker.compaction.job.update.CompactionJobCommittedEvent;
import sleeper.core.tracker.compaction.job.update.CompactionJobCreatedEvent;
import sleeper.core.tracker.compaction.job.update.CompactionJobFailedEvent;
import sleeper.core.tracker.compaction.job.update.CompactionJobFailedEvent.Builder;
import sleeper.core.tracker.compaction.job.update.CompactionJobFinishedEvent;
import sleeper.core.tracker.compaction.job.update.CompactionJobStartedEvent;
import sleeper.core.tracker.job.run.RecordsProcessed;

class DynamoDBCompactionJobTrackerDiffblueTest {
  /**
   * Test {@link DynamoDBCompactionJobTracker#stronglyConsistentReads(AmazonDynamoDB, InstanceProperties)}.
   * <p>
   * Method under test: {@link DynamoDBCompactionJobTracker#stronglyConsistentReads(AmazonDynamoDB, InstanceProperties)}
   */
  @Test
  @DisplayName("Test stronglyConsistentReads(AmazonDynamoDB, InstanceProperties)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "DynamoDBCompactionJobTracker DynamoDBCompactionJobTracker.stronglyConsistentReads(AmazonDynamoDB, InstanceProperties)"})
  void testStronglyConsistentReads() {
    // Arrange
    HashMap<String, AttributeValue> item = new HashMap<>();
    AsyncHandler<PutItemRequest, PutItemResult> asyncHandler = mock(AsyncHandler.class);
    doThrow(new RuntimeException("foo")).when(asyncHandler).onError(Mockito.<Exception>any());

    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    dynamoDB.putItemAsync("compaction-job-updates", item, "42", asyncHandler);
    HashMap<String, AttributeValue> item2 = new HashMap<>();
    AsyncHandler<PutItemRequest, PutItemResult> asyncHandler2 = mock(AsyncHandler.class);
    doThrow(new RuntimeException("foo")).when(asyncHandler2).onError(Mockito.<Exception>any());
    dynamoDB.putItemAsync("Table Name", item2, asyncHandler2);
    HashMap<String, AttributeValue> item3 = new HashMap<>();
    AsyncHandler<PutItemRequest, PutItemResult> asyncHandler3 = mock(AsyncHandler.class);
    doNothing().when(asyncHandler3).onError(Mockito.<Exception>any());
    dynamoDB.putItemAsync("Table Name", item3, asyncHandler3);
    dynamoDB.addRequestHandler(new MessageMD5ChecksumHandler());
    AsyncHandler<PutItemRequest, PutItemResult> asyncHandler4 = mock(AsyncHandler.class);
    doNothing().when(asyncHandler4).onError(Mockito.<Exception>any());
    dynamoDB.putItemAsync(new PutItemRequest(), asyncHandler4);
    STSSessionCredentials credentials = new STSSessionCredentials(new AnonymousAWSCredentials(), 1);

    PutItemRequest request = new PutItemRequest();
    request.setRequestCredentials(credentials);
    dynamoDB.putItemAsync(request);
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.getInt(Mockito.<InstanceProperty>any())).thenReturn(1);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");

    // Act
    DynamoDBCompactionJobTracker.stronglyConsistentReads(dynamoDB, instanceProperties);

    // Assert
    verify(asyncHandler).onError(Mockito.<Exception>any());
    verify(asyncHandler2).onError(isA(Exception.class));
    verify(asyncHandler3).onError(isA(Exception.class));
    verify(asyncHandler4).onError(isA(Exception.class));
    verify(instanceProperties).getInt(isA(InstanceProperty.class));
    verify(instanceProperties, atLeast(1)).get(isA(InstanceProperty.class));
  }

  /**
   * Test {@link DynamoDBCompactionJobTracker#eventuallyConsistentReads(AmazonDynamoDB, InstanceProperties)}.
   * <ul>
   *   <li>Given one.</li>
   *   <li>Then calls {@link SleeperPropertyValues#getInt(SleeperProperty)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBCompactionJobTracker#eventuallyConsistentReads(AmazonDynamoDB, InstanceProperties)}
   */
  @Test
  @DisplayName("Test eventuallyConsistentReads(AmazonDynamoDB, InstanceProperties); given one; then calls getInt(SleeperProperty)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "DynamoDBCompactionJobTracker DynamoDBCompactionJobTracker.eventuallyConsistentReads(AmazonDynamoDB, InstanceProperties)"})
  void testEventuallyConsistentReads_givenOne_thenCallsGetInt() {
    // Arrange
    PutItemRequest request = new PutItemRequest();
    AsyncHandler<PutItemRequest, PutItemResult> asyncHandler = mock(AsyncHandler.class);
    doThrow(new RuntimeException("foo")).when(asyncHandler).onError(Mockito.<Exception>any());

    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    dynamoDB.putItemAsync(request, asyncHandler);
    dynamoDB.putItemAsync("Table Name", new HashMap<>(), "compaction-job-lookup");
    AsyncHandler<PutItemRequest, PutItemResult> asyncHandler2 = mock(AsyncHandler.class);
    doThrow(new RuntimeException("foo")).when(asyncHandler2).onError(Mockito.<Exception>any());
    dynamoDB.putItemAsync(new PutItemRequest(), asyncHandler2);
    dynamoDB.putItemAsync("compaction-job-lookup", new HashMap<>());
    dynamoDB.putItemAsync(new PutItemRequest());
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.getInt(Mockito.<InstanceProperty>any())).thenReturn(1);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");

    // Act
    DynamoDBCompactionJobTracker.eventuallyConsistentReads(dynamoDB, instanceProperties);

    // Assert
    verify(asyncHandler).onError(isA(Exception.class));
    verify(asyncHandler2).onError(isA(Exception.class));
    verify(instanceProperties).getInt(isA(InstanceProperty.class));
    verify(instanceProperties, atLeast(1)).get(isA(InstanceProperty.class));
  }

  /**
   * Test {@link DynamoDBCompactionJobTracker#jobUpdatesTableName(String)}.
   * <p>
   * Method under test: {@link DynamoDBCompactionJobTracker#jobUpdatesTableName(String)}
   */
  @Test
  @DisplayName("Test jobUpdatesTableName(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String DynamoDBCompactionJobTracker.jobUpdatesTableName(String)"})
  void testJobUpdatesTableName() {
    // Arrange, Act and Assert
    assertEquals("sleeper-42-compaction-job-updates", DynamoDBCompactionJobTracker.jobUpdatesTableName("42"));
  }

  /**
   * Test {@link DynamoDBCompactionJobTracker#jobLookupTableName(String)}.
   * <p>
   * Method under test: {@link DynamoDBCompactionJobTracker#jobLookupTableName(String)}
   */
  @Test
  @DisplayName("Test jobLookupTableName(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String DynamoDBCompactionJobTracker.jobLookupTableName(String)"})
  void testJobLookupTableName() {
    // Arrange, Act and Assert
    assertEquals("sleeper-42-compaction-job-lookup", DynamoDBCompactionJobTracker.jobLookupTableName("42"));
  }

  /**
   * Test {@link DynamoDBCompactionJobTracker#jobCreated(CompactionJobCreatedEvent)}.
   * <ul>
   *   <li>Then throw {@link CompactionTrackerException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBCompactionJobTracker#jobCreated(CompactionJobCreatedEvent)}
   */
  @Test
  @DisplayName("Test jobCreated(CompactionJobCreatedEvent); then throw CompactionTrackerException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBCompactionJobTracker.jobCreated(CompactionJobCreatedEvent)"})
  void testJobCreated_thenThrowCompactionTrackerException() {
    // Arrange
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    DynamoDBCompactionJobTracker eventuallyConsistentReadsResult = DynamoDBCompactionJobTracker
        .eventuallyConsistentReads(dynamoDB, new InstanceProperties());
    CompactionJobCreatedEvent event = CompactionJobCreatedEvent.builder()
        .inputFilesCount(3)
        .jobId("42")
        .partitionId("42")
        .tableId("42")
        .build();

    // Act and Assert
    assertThrows(CompactionTrackerException.class, () -> eventuallyConsistentReadsResult.jobCreated(event));
  }

  /**
   * Test {@link DynamoDBCompactionJobTracker#jobStarted(CompactionJobStartedEvent)}.
   * <p>
   * Method under test: {@link DynamoDBCompactionJobTracker#jobStarted(CompactionJobStartedEvent)}
   */
  @Test
  @DisplayName("Test jobStarted(CompactionJobStartedEvent)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBCompactionJobTracker.jobStarted(CompactionJobStartedEvent)"})
  void testJobStarted() {
    // Arrange
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    DynamoDBCompactionJobTracker eventuallyConsistentReadsResult = DynamoDBCompactionJobTracker
        .eventuallyConsistentReads(dynamoDB, new InstanceProperties());
    CompactionJobStartedEvent.Builder jobRunIdResult = CompactionJobStartedEvent.builder().jobId("42").jobRunId("42");
    CompactionJobStartedEvent event = jobRunIdResult
        .startTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .tableId("42")
        .taskId("42")
        .build();

    // Act and Assert
    assertThrows(CompactionTrackerException.class, () -> eventuallyConsistentReadsResult.jobStarted(event));
  }

  /**
   * Test {@link DynamoDBCompactionJobTracker#jobStarted(CompactionJobStartedEvent)}.
   * <p>
   * Method under test: {@link DynamoDBCompactionJobTracker#jobStarted(CompactionJobStartedEvent)}
   */
  @Test
  @DisplayName("Test jobStarted(CompactionJobStartedEvent)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBCompactionJobTracker.jobStarted(CompactionJobStartedEvent)"})
  void testJobStarted2() {
    // Arrange
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    DynamoDBCompactionJobTracker eventuallyConsistentReadsResult = DynamoDBCompactionJobTracker
        .eventuallyConsistentReads(dynamoDB, new InstanceProperties());
    CompactionJobStartedEvent event = mock(CompactionJobStartedEvent.class);
    when(event.getTaskId()).thenThrow(new CompactionTrackerException("An error occurred", new Throwable()));
    when(event.getJobId()).thenReturn("42");
    when(event.getTableId()).thenReturn("42");
    when(event.getStartTime()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act and Assert
    assertThrows(CompactionTrackerException.class, () -> eventuallyConsistentReadsResult.jobStarted(event));
    verify(event, atLeast(1)).getJobId();
    verify(event).getStartTime();
    verify(event).getTableId();
    verify(event).getTaskId();
  }

  /**
   * Test {@link DynamoDBCompactionJobTracker#jobStarted(CompactionJobStartedEvent)}.
   * <p>
   * Method under test: {@link DynamoDBCompactionJobTracker#jobStarted(CompactionJobStartedEvent)}
   */
  @Test
  @DisplayName("Test jobStarted(CompactionJobStartedEvent)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBCompactionJobTracker.jobStarted(CompactionJobStartedEvent)"})
  void testJobStarted3() {
    // Arrange
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient(new AnonymousAWSCredentials());
    DynamoDBCompactionJobTracker eventuallyConsistentReadsResult = DynamoDBCompactionJobTracker
        .eventuallyConsistentReads(dynamoDB, new InstanceProperties());
    CompactionJobStartedEvent event = mock(CompactionJobStartedEvent.class);
    when(event.getJobRunId()).thenReturn("42");
    when(event.getTaskId()).thenReturn("42");
    when(event.getJobId()).thenReturn("42");
    when(event.getTableId()).thenReturn("42");
    when(event.getStartTime()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act and Assert
    assertThrows(CompactionTrackerException.class, () -> eventuallyConsistentReadsResult.jobStarted(event));
    verify(event, atLeast(1)).getJobId();
    verify(event).getJobRunId();
    verify(event).getStartTime();
    verify(event).getTableId();
    verify(event).getTaskId();
  }

  /**
   * Test {@link DynamoDBCompactionJobTracker#jobStarted(CompactionJobStartedEvent)}.
   * <p>
   * Method under test: {@link DynamoDBCompactionJobTracker#jobStarted(CompactionJobStartedEvent)}
   */
  @Test
  @DisplayName("Test jobStarted(CompactionJobStartedEvent)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBCompactionJobTracker.jobStarted(CompactionJobStartedEvent)"})
  void testJobStarted4() {
    // Arrange
    AsyncHandler<PutItemRequest, PutItemResult> asyncHandler = mock(AsyncHandler.class);
    doNothing().when(asyncHandler).onError(Mockito.<Exception>any());

    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient(PredefinedClientConfigurations.defaultConfig());
    dynamoDB.putItemAsync("Table Name", new HashMap<>(), asyncHandler);
    DynamoDBCompactionJobTracker eventuallyConsistentReadsResult = DynamoDBCompactionJobTracker
        .eventuallyConsistentReads(dynamoDB, new InstanceProperties());
    CompactionJobStartedEvent event = mock(CompactionJobStartedEvent.class);
    when(event.getJobRunId()).thenReturn("42");
    when(event.getTaskId()).thenReturn("42");
    when(event.getJobId()).thenReturn("42");
    when(event.getTableId()).thenReturn("42");
    when(event.getStartTime()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act and Assert
    assertThrows(CompactionTrackerException.class, () -> eventuallyConsistentReadsResult.jobStarted(event));
    verify(asyncHandler).onError(isA(Exception.class));
    verify(event, atLeast(1)).getJobId();
    verify(event).getJobRunId();
    verify(event).getStartTime();
    verify(event).getTableId();
    verify(event).getTaskId();
  }

  /**
   * Test {@link DynamoDBCompactionJobTracker#jobStarted(CompactionJobStartedEvent)}.
   * <ul>
   *   <li>Given {@link Supplier} {@link Supplier#get()} throw {@link RuntimeException#RuntimeException(String)} with {@code foo}.</li>
   *   <li>Then calls {@link Supplier#get()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBCompactionJobTracker#jobStarted(CompactionJobStartedEvent)}
   */
  @Test
  @DisplayName("Test jobStarted(CompactionJobStartedEvent); given Supplier get() throw RuntimeException(String) with 'foo'; then calls get()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBCompactionJobTracker.jobStarted(CompactionJobStartedEvent)"})
  void testJobStarted_givenSupplierGetThrowRuntimeExceptionWithFoo_thenCallsGet() {
    // Arrange
    Supplier<Instant> getTimeNow = mock(Supplier.class);
    when(getTimeNow.get()).thenThrow(new RuntimeException("foo"));
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    DynamoDBCompactionJobTracker dynamoDBCompactionJobTracker = new DynamoDBCompactionJobTracker(dynamoDB,
        new InstanceProperties(), true, getTimeNow);
    CompactionJobStartedEvent event = mock(CompactionJobStartedEvent.class);
    when(event.getJobId()).thenReturn("42");
    when(event.getTableId()).thenReturn("42");

    // Act and Assert
    assertThrows(CompactionTrackerException.class, () -> dynamoDBCompactionJobTracker.jobStarted(event));
    verify(getTimeNow).get();
    verify(event, atLeast(1)).getJobId();
    verify(event).getTableId();
  }

  /**
   * Test {@link DynamoDBCompactionJobTracker#jobFinished(CompactionJobFinishedEvent)}.
   * <p>
   * Method under test: {@link DynamoDBCompactionJobTracker#jobFinished(CompactionJobFinishedEvent)}
   */
  @Test
  @DisplayName("Test jobFinished(CompactionJobFinishedEvent)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBCompactionJobTracker.jobFinished(CompactionJobFinishedEvent)"})
  void testJobFinished() {
    // Arrange
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    DynamoDBCompactionJobTracker eventuallyConsistentReadsResult = DynamoDBCompactionJobTracker
        .eventuallyConsistentReads(dynamoDB, new InstanceProperties());
    CompactionJobFinishedEvent event = mock(CompactionJobFinishedEvent.class);
    when(event.getJobId()).thenReturn("42");
    when(event.getJobRunId()).thenReturn("42");
    when(event.getTableId()).thenReturn("42");
    when(event.getTaskId()).thenReturn("42");
    when(event.getFinishTime()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    when(event.getRecordsProcessed()).thenReturn(new RecordsProcessed(1L, 1L));

    // Act and Assert
    assertThrows(CompactionTrackerException.class, () -> eventuallyConsistentReadsResult.jobFinished(event));
    verify(event).getFinishTime();
    verify(event, atLeast(1)).getJobId();
    verify(event).getJobRunId();
    verify(event).getRecordsProcessed();
    verify(event).getTableId();
    verify(event).getTaskId();
  }

  /**
   * Test {@link DynamoDBCompactionJobTracker#jobFinished(CompactionJobFinishedEvent)}.
   * <p>
   * Method under test: {@link DynamoDBCompactionJobTracker#jobFinished(CompactionJobFinishedEvent)}
   */
  @Test
  @DisplayName("Test jobFinished(CompactionJobFinishedEvent)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBCompactionJobTracker.jobFinished(CompactionJobFinishedEvent)"})
  void testJobFinished2() {
    // Arrange
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient(new AnonymousAWSCredentials());
    DynamoDBCompactionJobTracker eventuallyConsistentReadsResult = DynamoDBCompactionJobTracker
        .eventuallyConsistentReads(dynamoDB, new InstanceProperties());
    CompactionJobFinishedEvent event = mock(CompactionJobFinishedEvent.class);
    when(event.getJobId()).thenReturn("42");
    when(event.getJobRunId()).thenReturn("42");
    when(event.getTableId()).thenReturn("42");
    when(event.getTaskId()).thenReturn("42");
    when(event.getFinishTime()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    when(event.getRecordsProcessed()).thenReturn(new RecordsProcessed(1L, 1L));

    // Act and Assert
    assertThrows(CompactionTrackerException.class, () -> eventuallyConsistentReadsResult.jobFinished(event));
    verify(event).getFinishTime();
    verify(event, atLeast(1)).getJobId();
    verify(event).getJobRunId();
    verify(event).getRecordsProcessed();
    verify(event).getTableId();
    verify(event).getTaskId();
  }

  /**
   * Test {@link DynamoDBCompactionJobTracker#jobFinished(CompactionJobFinishedEvent)}.
   * <ul>
   *   <li>Given {@link Supplier} {@link Supplier#get()} throw {@link RuntimeException#RuntimeException(String)} with {@code foo}.</li>
   *   <li>Then calls {@link Supplier#get()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBCompactionJobTracker#jobFinished(CompactionJobFinishedEvent)}
   */
  @Test
  @DisplayName("Test jobFinished(CompactionJobFinishedEvent); given Supplier get() throw RuntimeException(String) with 'foo'; then calls get()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBCompactionJobTracker.jobFinished(CompactionJobFinishedEvent)"})
  void testJobFinished_givenSupplierGetThrowRuntimeExceptionWithFoo_thenCallsGet() {
    // Arrange
    new RuntimeException("foo");
    Supplier<Instant> getTimeNow = mock(Supplier.class);
    when(getTimeNow.get()).thenThrow(new RuntimeException("foo"));
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    DynamoDBCompactionJobTracker dynamoDBCompactionJobTracker = new DynamoDBCompactionJobTracker(dynamoDB,
        new InstanceProperties(), true, getTimeNow);
    CompactionJobFinishedEvent event = mock(CompactionJobFinishedEvent.class);
    when(event.getJobId()).thenReturn("42");
    when(event.getTableId()).thenReturn("42");

    // Act and Assert
    assertThrows(CompactionTrackerException.class, () -> dynamoDBCompactionJobTracker.jobFinished(event));
    verify(getTimeNow).get();
    verify(event, atLeast(1)).getJobId();
    verify(event).getTableId();
  }

  /**
   * Test {@link DynamoDBCompactionJobTracker#jobFinished(CompactionJobFinishedEvent)}.
   * <ul>
   *   <li>Then calls {@link AsyncHandler#onError(Exception)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBCompactionJobTracker#jobFinished(CompactionJobFinishedEvent)}
   */
  @Test
  @DisplayName("Test jobFinished(CompactionJobFinishedEvent); then calls onError(Exception)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBCompactionJobTracker.jobFinished(CompactionJobFinishedEvent)"})
  void testJobFinished_thenCallsOnError() {
    // Arrange
    AsyncHandler<PutItemRequest, PutItemResult> asyncHandler = mock(AsyncHandler.class);
    doThrow(new RuntimeException("foo")).when(asyncHandler).onError(Mockito.<Exception>any());

    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    dynamoDB.putItemAsync(new PutItemRequest(), asyncHandler);
    DynamoDBCompactionJobTracker eventuallyConsistentReadsResult = DynamoDBCompactionJobTracker
        .eventuallyConsistentReads(dynamoDB, new InstanceProperties());
    CompactionJobFinishedEvent event = mock(CompactionJobFinishedEvent.class);
    when(event.getJobId()).thenReturn("42");
    when(event.getJobRunId()).thenReturn("42");
    when(event.getTableId()).thenReturn("42");
    when(event.getTaskId()).thenReturn("42");
    when(event.getFinishTime()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    when(event.getRecordsProcessed()).thenReturn(new RecordsProcessed(1L, 1L));

    // Act and Assert
    assertThrows(CompactionTrackerException.class, () -> eventuallyConsistentReadsResult.jobFinished(event));
    verify(asyncHandler).onError(isA(Exception.class));
    verify(event).getFinishTime();
    verify(event, atLeast(1)).getJobId();
    verify(event).getJobRunId();
    verify(event).getRecordsProcessed();
    verify(event).getTableId();
    verify(event).getTaskId();
  }

  /**
   * Test {@link DynamoDBCompactionJobTracker#jobCommitted(CompactionJobCommittedEvent)}.
   * <p>
   * Method under test: {@link DynamoDBCompactionJobTracker#jobCommitted(CompactionJobCommittedEvent)}
   */
  @Test
  @DisplayName("Test jobCommitted(CompactionJobCommittedEvent)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBCompactionJobTracker.jobCommitted(CompactionJobCommittedEvent)"})
  void testJobCommitted() {
    // Arrange
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    DynamoDBCompactionJobTracker eventuallyConsistentReadsResult = DynamoDBCompactionJobTracker
        .eventuallyConsistentReads(dynamoDB, new InstanceProperties());
    CompactionJobCommittedEvent.Builder builderResult = CompactionJobCommittedEvent.builder();
    CompactionJobCommittedEvent event = builderResult
        .commitTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .jobId("42")
        .jobRunId("42")
        .tableId("42")
        .taskId("42")
        .build();

    // Act and Assert
    assertThrows(CompactionTrackerException.class, () -> eventuallyConsistentReadsResult.jobCommitted(event));
  }

  /**
   * Test {@link DynamoDBCompactionJobTracker#jobCommitted(CompactionJobCommittedEvent)}.
   * <p>
   * Method under test: {@link DynamoDBCompactionJobTracker#jobCommitted(CompactionJobCommittedEvent)}
   */
  @Test
  @DisplayName("Test jobCommitted(CompactionJobCommittedEvent)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBCompactionJobTracker.jobCommitted(CompactionJobCommittedEvent)"})
  void testJobCommitted2() {
    // Arrange
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient(new AnonymousAWSCredentials());
    DynamoDBCompactionJobTracker eventuallyConsistentReadsResult = DynamoDBCompactionJobTracker
        .eventuallyConsistentReads(dynamoDB, new InstanceProperties());
    CompactionJobCommittedEvent event = mock(CompactionJobCommittedEvent.class);
    when(event.getJobId()).thenReturn("42");
    when(event.getJobRunId()).thenReturn("42");
    when(event.getTableId()).thenReturn("42");
    when(event.getTaskId()).thenReturn("42");
    when(event.getCommitTime()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act and Assert
    assertThrows(CompactionTrackerException.class, () -> eventuallyConsistentReadsResult.jobCommitted(event));
    verify(event).getCommitTime();
    verify(event, atLeast(1)).getJobId();
    verify(event).getJobRunId();
    verify(event).getTableId();
    verify(event).getTaskId();
  }

  /**
   * Test {@link DynamoDBCompactionJobTracker#jobCommitted(CompactionJobCommittedEvent)}.
   * <ul>
   *   <li>Given {@link AsyncHandler} {@link AsyncHandler#onError(Exception)} does nothing.</li>
   *   <li>Then calls {@link AsyncHandler#onError(Exception)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBCompactionJobTracker#jobCommitted(CompactionJobCommittedEvent)}
   */
  @Test
  @DisplayName("Test jobCommitted(CompactionJobCommittedEvent); given AsyncHandler onError(Exception) does nothing; then calls onError(Exception)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBCompactionJobTracker.jobCommitted(CompactionJobCommittedEvent)"})
  void testJobCommitted_givenAsyncHandlerOnErrorDoesNothing_thenCallsOnError() {
    // Arrange
    AsyncHandler<PutItemRequest, PutItemResult> asyncHandler = mock(AsyncHandler.class);
    doNothing().when(asyncHandler).onError(Mockito.<Exception>any());

    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient(PredefinedClientConfigurations.defaultConfig());
    dynamoDB.putItemAsync("Table Name", new HashMap<>(), asyncHandler);
    DynamoDBCompactionJobTracker eventuallyConsistentReadsResult = DynamoDBCompactionJobTracker
        .eventuallyConsistentReads(dynamoDB, new InstanceProperties());
    CompactionJobCommittedEvent event = mock(CompactionJobCommittedEvent.class);
    when(event.getJobId()).thenReturn("42");
    when(event.getJobRunId()).thenReturn("42");
    when(event.getTableId()).thenReturn("42");
    when(event.getTaskId()).thenReturn("42");
    when(event.getCommitTime()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act and Assert
    assertThrows(CompactionTrackerException.class, () -> eventuallyConsistentReadsResult.jobCommitted(event));
    verify(asyncHandler).onError(isA(Exception.class));
    verify(event).getCommitTime();
    verify(event, atLeast(1)).getJobId();
    verify(event).getJobRunId();
    verify(event).getTableId();
    verify(event).getTaskId();
  }

  /**
   * Test {@link DynamoDBCompactionJobTracker#jobCommitted(CompactionJobCommittedEvent)}.
   * <ul>
   *   <li>Given {@link Supplier} {@link Supplier#get()} throw {@link RuntimeException#RuntimeException(String)} with {@code foo}.</li>
   *   <li>Then calls {@link Supplier#get()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBCompactionJobTracker#jobCommitted(CompactionJobCommittedEvent)}
   */
  @Test
  @DisplayName("Test jobCommitted(CompactionJobCommittedEvent); given Supplier get() throw RuntimeException(String) with 'foo'; then calls get()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBCompactionJobTracker.jobCommitted(CompactionJobCommittedEvent)"})
  void testJobCommitted_givenSupplierGetThrowRuntimeExceptionWithFoo_thenCallsGet() {
    // Arrange
    Supplier<Instant> getTimeNow = mock(Supplier.class);
    when(getTimeNow.get()).thenThrow(new RuntimeException("foo"));
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    DynamoDBCompactionJobTracker dynamoDBCompactionJobTracker = new DynamoDBCompactionJobTracker(dynamoDB,
        new InstanceProperties(), true, getTimeNow);
    CompactionJobCommittedEvent event = mock(CompactionJobCommittedEvent.class);
    when(event.getJobId()).thenReturn("42");
    when(event.getTableId()).thenReturn("42");

    // Act and Assert
    assertThrows(CompactionTrackerException.class, () -> dynamoDBCompactionJobTracker.jobCommitted(event));
    verify(getTimeNow).get();
    verify(event, atLeast(1)).getJobId();
    verify(event).getTableId();
  }

  /**
   * Test {@link DynamoDBCompactionJobTracker#jobFailed(CompactionJobFailedEvent)}.
   * <p>
   * Method under test: {@link DynamoDBCompactionJobTracker#jobFailed(CompactionJobFailedEvent)}
   */
  @Test
  @DisplayName("Test jobFailed(CompactionJobFailedEvent)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBCompactionJobTracker.jobFailed(CompactionJobFailedEvent)"})
  void testJobFailed() {
    // Arrange
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    DynamoDBCompactionJobTracker eventuallyConsistentReadsResult = DynamoDBCompactionJobTracker
        .eventuallyConsistentReads(dynamoDB, new InstanceProperties());
    Builder builderResult = CompactionJobFailedEvent.builder();
    Builder failureReasonsResult = builderResult.failureReasons(new ArrayList<>());
    CompactionJobFailedEvent event = failureReasonsResult
        .failureTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .jobId("42")
        .jobRunId("42")
        .tableId("42")
        .taskId("42")
        .build();

    // Act and Assert
    assertThrows(CompactionTrackerException.class, () -> eventuallyConsistentReadsResult.jobFailed(event));
  }

  /**
   * Test {@link DynamoDBCompactionJobTracker#jobFailed(CompactionJobFailedEvent)}.
   * <p>
   * Method under test: {@link DynamoDBCompactionJobTracker#jobFailed(CompactionJobFailedEvent)}
   */
  @Test
  @DisplayName("Test jobFailed(CompactionJobFailedEvent)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBCompactionJobTracker.jobFailed(CompactionJobFailedEvent)"})
  void testJobFailed2() {
    // Arrange
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient(new AnonymousAWSCredentials());
    DynamoDBCompactionJobTracker eventuallyConsistentReadsResult = DynamoDBCompactionJobTracker
        .eventuallyConsistentReads(dynamoDB, new InstanceProperties());
    Builder builderResult = CompactionJobFailedEvent.builder();
    Builder failureReasonsResult = builderResult.failureReasons(new ArrayList<>());
    CompactionJobFailedEvent event = failureReasonsResult
        .failureTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .jobId("42")
        .jobRunId("42")
        .tableId("42")
        .taskId("42")
        .build();

    // Act and Assert
    assertThrows(CompactionTrackerException.class, () -> eventuallyConsistentReadsResult.jobFailed(event));
  }

  /**
   * Test {@link DynamoDBCompactionJobTracker#jobFailed(CompactionJobFailedEvent)}.
   * <p>
   * Method under test: {@link DynamoDBCompactionJobTracker#jobFailed(CompactionJobFailedEvent)}
   */
  @Test
  @DisplayName("Test jobFailed(CompactionJobFailedEvent)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBCompactionJobTracker.jobFailed(CompactionJobFailedEvent)"})
  void testJobFailed3() {
    // Arrange
    AsyncHandler<PutItemRequest, PutItemResult> asyncHandler = mock(AsyncHandler.class);
    doNothing().when(asyncHandler).onError(Mockito.<Exception>any());

    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    dynamoDB.putItemAsync(new PutItemRequest(), asyncHandler);
    DynamoDBCompactionJobTracker eventuallyConsistentReadsResult = DynamoDBCompactionJobTracker
        .eventuallyConsistentReads(dynamoDB, new InstanceProperties());
    Builder builderResult = CompactionJobFailedEvent.builder();
    Builder failureReasonsResult = builderResult.failureReasons(new ArrayList<>());
    CompactionJobFailedEvent event = failureReasonsResult
        .failureTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .jobId("42")
        .jobRunId("42")
        .tableId("42")
        .taskId("42")
        .build();

    // Act and Assert
    assertThrows(CompactionTrackerException.class, () -> eventuallyConsistentReadsResult.jobFailed(event));
    verify(asyncHandler).onError(isA(Exception.class));
  }

  /**
   * Test {@link DynamoDBCompactionJobTracker#jobFailed(CompactionJobFailedEvent)}.
   * <p>
   * Method under test: {@link DynamoDBCompactionJobTracker#jobFailed(CompactionJobFailedEvent)}
   */
  @Test
  @DisplayName("Test jobFailed(CompactionJobFailedEvent)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBCompactionJobTracker.jobFailed(CompactionJobFailedEvent)"})
  void testJobFailed4() {
    // Arrange
    Supplier<Instant> getTimeNow = mock(Supplier.class);
    when(getTimeNow.get()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    DynamoDBCompactionJobTracker dynamoDBCompactionJobTracker = new DynamoDBCompactionJobTracker(dynamoDB,
        new InstanceProperties(), true, getTimeNow);

    ArrayList<String> failureReasons = new ArrayList<>();
    failureReasons.add("foo");
    Builder failureReasonsResult = CompactionJobFailedEvent.builder().failureReasons(failureReasons);
    CompactionJobFailedEvent event = failureReasonsResult
        .failureTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .jobId("42")
        .jobRunId("42")
        .tableId("42")
        .taskId("42")
        .build();

    // Act and Assert
    assertThrows(CompactionTrackerException.class, () -> dynamoDBCompactionJobTracker.jobFailed(event));
    verify(getTimeNow).get();
  }

  /**
   * Test {@link DynamoDBCompactionJobTracker#jobFailed(CompactionJobFailedEvent)}.
   * <ul>
   *   <li>Given {@link DynamoDBCompactionJobTracker#JOB_ID}.</li>
   *   <li>When {@link ArrayList#ArrayList()} add {@link DynamoDBCompactionJobTracker#JOB_ID}.</li>
   *   <li>Then calls {@link Supplier#get()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBCompactionJobTracker#jobFailed(CompactionJobFailedEvent)}
   */
  @Test
  @DisplayName("Test jobFailed(CompactionJobFailedEvent); given JOB_ID; when ArrayList() add JOB_ID; then calls get()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBCompactionJobTracker.jobFailed(CompactionJobFailedEvent)"})
  void testJobFailed_givenJob_id_whenArrayListAddJob_id_thenCallsGet() {
    // Arrange
    Supplier<Instant> getTimeNow = mock(Supplier.class);
    when(getTimeNow.get()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    DynamoDBCompactionJobTracker dynamoDBCompactionJobTracker = new DynamoDBCompactionJobTracker(dynamoDB,
        new InstanceProperties(), true, getTimeNow);

    ArrayList<String> failureReasons = new ArrayList<>();
    failureReasons.add(DynamoDBCompactionJobTracker.JOB_ID);
    failureReasons.add("foo");
    Builder failureReasonsResult = CompactionJobFailedEvent.builder().failureReasons(failureReasons);
    CompactionJobFailedEvent event = failureReasonsResult
        .failureTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .jobId("42")
        .jobRunId("42")
        .tableId("42")
        .taskId("42")
        .build();

    // Act and Assert
    assertThrows(CompactionTrackerException.class, () -> dynamoDBCompactionJobTracker.jobFailed(event));
    verify(getTimeNow).get();
  }

  /**
   * Test {@link DynamoDBCompactionJobTracker#jobFailed(CompactionJobFailedEvent)}.
   * <ul>
   *   <li>Given {@link RuntimeException#RuntimeException(String)} with {@code foo}.</li>
   *   <li>Then calls {@link CompactionJobFailedEvent#getFailureReasons()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBCompactionJobTracker#jobFailed(CompactionJobFailedEvent)}
   */
  @Test
  @DisplayName("Test jobFailed(CompactionJobFailedEvent); given RuntimeException(String) with 'foo'; then calls getFailureReasons()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBCompactionJobTracker.jobFailed(CompactionJobFailedEvent)"})
  void testJobFailed_givenRuntimeExceptionWithFoo_thenCallsGetFailureReasons() {
    // Arrange
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    DynamoDBCompactionJobTracker eventuallyConsistentReadsResult = DynamoDBCompactionJobTracker
        .eventuallyConsistentReads(dynamoDB, new InstanceProperties());
    CompactionJobFailedEvent event = mock(CompactionJobFailedEvent.class);
    when(event.getFailureReasons()).thenThrow(new RuntimeException("foo"));
    when(event.getJobId()).thenReturn("42");
    when(event.getJobRunId()).thenReturn("42");
    when(event.getTableId()).thenReturn("42");
    when(event.getTaskId()).thenReturn("42");
    when(event.getFailureTime()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act and Assert
    assertThrows(CompactionTrackerException.class, () -> eventuallyConsistentReadsResult.jobFailed(event));
    verify(event).getFailureReasons();
    verify(event).getFailureTime();
    verify(event, atLeast(1)).getJobId();
    verify(event).getJobRunId();
    verify(event).getTableId();
    verify(event).getTaskId();
  }

  /**
   * Test {@link DynamoDBCompactionJobTracker#jobFailed(CompactionJobFailedEvent)}.
   * <ul>
   *   <li>Given {@link Supplier} {@link Supplier#get()} throw {@link RuntimeException#RuntimeException(String)} with {@code foo}.</li>
   *   <li>Then calls {@link Supplier#get()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBCompactionJobTracker#jobFailed(CompactionJobFailedEvent)}
   */
  @Test
  @DisplayName("Test jobFailed(CompactionJobFailedEvent); given Supplier get() throw RuntimeException(String) with 'foo'; then calls get()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBCompactionJobTracker.jobFailed(CompactionJobFailedEvent)"})
  void testJobFailed_givenSupplierGetThrowRuntimeExceptionWithFoo_thenCallsGet() {
    // Arrange
    Supplier<Instant> getTimeNow = mock(Supplier.class);
    when(getTimeNow.get()).thenThrow(new RuntimeException("foo"));
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    DynamoDBCompactionJobTracker dynamoDBCompactionJobTracker = new DynamoDBCompactionJobTracker(dynamoDB,
        new InstanceProperties(), true, getTimeNow);
    CompactionJobFailedEvent event = mock(CompactionJobFailedEvent.class);
    when(event.getJobId()).thenReturn("42");
    when(event.getTableId()).thenReturn("42");

    // Act and Assert
    assertThrows(CompactionTrackerException.class, () -> dynamoDBCompactionJobTracker.jobFailed(event));
    verify(getTimeNow).get();
    verify(event, atLeast(1)).getJobId();
    verify(event).getTableId();
  }
}
