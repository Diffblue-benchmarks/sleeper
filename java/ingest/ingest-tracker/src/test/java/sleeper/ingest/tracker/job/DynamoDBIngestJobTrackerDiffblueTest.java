package sleeper.ingest.tracker.job;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.amazonaws.PredefinedClientConfigurations;
import com.amazonaws.Request;
import com.amazonaws.Response;
import com.amazonaws.auth.AnonymousAWSCredentials;
import com.amazonaws.handlers.AsyncHandler;
import com.amazonaws.metrics.RequestMetricCollector;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClient;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;
import com.amazonaws.services.dynamodbv2.model.PutItemResult;
import com.amazonaws.services.dynamodbv2.model.ReturnItemCollectionMetrics;
import com.amazonaws.services.sqs.MessageMD5ChecksumHandler;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Supplier;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.tracker.ingest.job.update.IngestJobAddedFilesEvent;
import sleeper.core.tracker.ingest.job.update.IngestJobFailedEvent;
import sleeper.core.tracker.ingest.job.update.IngestJobFinishedEvent;
import sleeper.core.tracker.ingest.job.update.IngestJobStartedEvent;
import sleeper.core.tracker.ingest.job.update.IngestJobValidatedEvent;
import sleeper.core.tracker.job.run.RecordsProcessed;
import sleeper.ingest.tracker.IngestTrackerException;

@Disabled
class DynamoDBIngestJobTrackerDiffblueTest {
  /**
   * Test {@link DynamoDBIngestJobTracker#jobUpdatesTableName(String)}.
   * <p>
   * Method under test: {@link DynamoDBIngestJobTracker#jobUpdatesTableName(String)}
   */
  @Test
  @DisplayName("Test jobUpdatesTableName(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String DynamoDBIngestJobTracker.jobUpdatesTableName(String)"})
  void testJobUpdatesTableName() {
    // Arrange, Act and Assert
    assertEquals("sleeper-42-ingest-job-updates", DynamoDBIngestJobTracker.jobUpdatesTableName("42"));
  }

  /**
   * Test {@link DynamoDBIngestJobTracker#jobLookupTableName(String)}.
   * <p>
   * Method under test: {@link DynamoDBIngestJobTracker#jobLookupTableName(String)}
   */
  @Test
  @DisplayName("Test jobLookupTableName(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String DynamoDBIngestJobTracker.jobLookupTableName(String)"})
  void testJobLookupTableName() {
    // Arrange, Act and Assert
    assertEquals("sleeper-42-ingest-job-lookup", DynamoDBIngestJobTracker.jobLookupTableName("42"));
  }

  /**
   * Test {@link DynamoDBIngestJobTracker#jobValidated(IngestJobValidatedEvent)}.
   * <p>
   * Method under test: {@link DynamoDBIngestJobTracker#jobValidated(IngestJobValidatedEvent)}
   */
  @Test
  @DisplayName("Test jobValidated(IngestJobValidatedEvent)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBIngestJobTracker.jobValidated(IngestJobValidatedEvent)"})
  void testJobValidated() {
    // Arrange
    Supplier<Instant> getTimeNow = mock(Supplier.class);
    when(getTimeNow.get()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    DynamoDBIngestJobTracker dynamoDBIngestJobTracker = new DynamoDBIngestJobTracker(dynamoDB, new InstanceProperties(),
        getTimeNow);
    IngestJobValidatedEvent event = mock(IngestJobValidatedEvent.class);
    when(event.isAccepted()).thenThrow(new IngestTrackerException("An error occurred", new Throwable()));
    when(event.getJobId()).thenReturn("42");
    when(event.getTableId()).thenReturn("42");
    when(event.getValidationTime())
        .thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act and Assert
    assertThrows(IngestTrackerException.class, () -> dynamoDBIngestJobTracker.jobValidated(event));
    verify(getTimeNow).get();
    verify(event, atLeast(1)).getJobId();
    verify(event).getTableId();
    verify(event).getValidationTime();
    verify(event).isAccepted();
  }

  /**
   * Test {@link DynamoDBIngestJobTracker#jobValidated(IngestJobValidatedEvent)}.
   * <p>
   * Method under test: {@link DynamoDBIngestJobTracker#jobValidated(IngestJobValidatedEvent)}
   */
  @Test
  @DisplayName("Test jobValidated(IngestJobValidatedEvent)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBIngestJobTracker.jobValidated(IngestJobValidatedEvent)"})
  void testJobValidated2() {
    // Arrange
    Supplier<Instant> getTimeNow = mock(Supplier.class);
    when(getTimeNow.get()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient(new AnonymousAWSCredentials());
    DynamoDBIngestJobTracker dynamoDBIngestJobTracker = new DynamoDBIngestJobTracker(dynamoDB, new InstanceProperties(),
        getTimeNow);
    IngestJobValidatedEvent event = mock(IngestJobValidatedEvent.class);
    when(event.isAccepted()).thenReturn(true);
    when(event.getFileCount()).thenReturn(3);
    when(event.getJobRunId()).thenReturn("42");
    when(event.getJsonMessage()).thenReturn("Json Message");
    when(event.getReasons()).thenReturn(new ArrayList<>());
    when(event.getJobId()).thenReturn("42");
    when(event.getTableId()).thenReturn("42");
    when(event.getValidationTime())
        .thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act and Assert
    assertThrows(IngestTrackerException.class, () -> dynamoDBIngestJobTracker.jobValidated(event));
    verify(getTimeNow).get();
    verify(event).getFileCount();
    verify(event, atLeast(1)).getJobId();
    verify(event).getJobRunId();
    verify(event).getJsonMessage();
    verify(event).getReasons();
    verify(event).getTableId();
    verify(event).getValidationTime();
    verify(event).isAccepted();
  }

  /**
   * Test {@link DynamoDBIngestJobTracker#jobValidated(IngestJobValidatedEvent)}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link DynamoDBIngestJobTracker#JOB_ID}.</li>
   *   <li>Then calls {@link IngestJobValidatedEvent#getFileCount()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBIngestJobTracker#jobValidated(IngestJobValidatedEvent)}
   */
  @Test
  @DisplayName("Test jobValidated(IngestJobValidatedEvent); given ArrayList() add JOB_ID; then calls getFileCount()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBIngestJobTracker.jobValidated(IngestJobValidatedEvent)"})
  void testJobValidated_givenArrayListAddJob_id_thenCallsGetFileCount() {
    // Arrange
    Supplier<Instant> getTimeNow = mock(Supplier.class);
    when(getTimeNow.get()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    DynamoDBIngestJobTracker dynamoDBIngestJobTracker = new DynamoDBIngestJobTracker(dynamoDB, new InstanceProperties(),
        getTimeNow);

    ArrayList<String> stringList = new ArrayList<>();
    stringList.add(DynamoDBIngestJobTracker.JOB_ID);
    stringList.add(DynamoDBIngestJobTracker.TABLE_ID);
    IngestJobValidatedEvent event = mock(IngestJobValidatedEvent.class);
    when(event.isAccepted()).thenReturn(true);
    when(event.getFileCount()).thenReturn(3);
    when(event.getJobRunId()).thenReturn("42");
    when(event.getJsonMessage()).thenReturn("Json Message");
    when(event.getReasons()).thenReturn(stringList);
    when(event.getJobId()).thenReturn("42");
    when(event.getTableId()).thenReturn("42");
    when(event.getValidationTime())
        .thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act and Assert
    assertThrows(IngestTrackerException.class, () -> dynamoDBIngestJobTracker.jobValidated(event));
    verify(getTimeNow).get();
    verify(event).getFileCount();
    verify(event, atLeast(1)).getJobId();
    verify(event).getJobRunId();
    verify(event).getJsonMessage();
    verify(event).getReasons();
    verify(event).getTableId();
    verify(event).getValidationTime();
    verify(event).isAccepted();
  }

  /**
   * Test {@link DynamoDBIngestJobTracker#jobValidated(IngestJobValidatedEvent)}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link DynamoDBIngestJobTracker#TABLE_ID}.</li>
   *   <li>Then calls {@link IngestJobValidatedEvent#getFileCount()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBIngestJobTracker#jobValidated(IngestJobValidatedEvent)}
   */
  @Test
  @DisplayName("Test jobValidated(IngestJobValidatedEvent); given ArrayList() add TABLE_ID; then calls getFileCount()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBIngestJobTracker.jobValidated(IngestJobValidatedEvent)"})
  void testJobValidated_givenArrayListAddTable_id_thenCallsGetFileCount() {
    // Arrange
    Supplier<Instant> getTimeNow = mock(Supplier.class);
    when(getTimeNow.get()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    DynamoDBIngestJobTracker dynamoDBIngestJobTracker = new DynamoDBIngestJobTracker(dynamoDB, new InstanceProperties(),
        getTimeNow);

    ArrayList<String> stringList = new ArrayList<>();
    stringList.add(DynamoDBIngestJobTracker.TABLE_ID);
    IngestJobValidatedEvent event = mock(IngestJobValidatedEvent.class);
    when(event.isAccepted()).thenReturn(true);
    when(event.getFileCount()).thenReturn(3);
    when(event.getJobRunId()).thenReturn("42");
    when(event.getJsonMessage()).thenReturn("Json Message");
    when(event.getReasons()).thenReturn(stringList);
    when(event.getJobId()).thenReturn("42");
    when(event.getTableId()).thenReturn("42");
    when(event.getValidationTime())
        .thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act and Assert
    assertThrows(IngestTrackerException.class, () -> dynamoDBIngestJobTracker.jobValidated(event));
    verify(getTimeNow).get();
    verify(event).getFileCount();
    verify(event, atLeast(1)).getJobId();
    verify(event).getJobRunId();
    verify(event).getJsonMessage();
    verify(event).getReasons();
    verify(event).getTableId();
    verify(event).getValidationTime();
    verify(event).isAccepted();
  }

  /**
   * Test {@link DynamoDBIngestJobTracker#jobValidated(IngestJobValidatedEvent)}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()}.</li>
   *   <li>Then calls {@link IngestJobValidatedEvent#getFileCount()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBIngestJobTracker#jobValidated(IngestJobValidatedEvent)}
   */
  @Test
  @DisplayName("Test jobValidated(IngestJobValidatedEvent); given ArrayList(); then calls getFileCount()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBIngestJobTracker.jobValidated(IngestJobValidatedEvent)"})
  void testJobValidated_givenArrayList_thenCallsGetFileCount() {
    // Arrange
    Supplier<Instant> getTimeNow = mock(Supplier.class);
    when(getTimeNow.get()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    DynamoDBIngestJobTracker dynamoDBIngestJobTracker = new DynamoDBIngestJobTracker(dynamoDB, new InstanceProperties(),
        getTimeNow);
    IngestJobValidatedEvent event = mock(IngestJobValidatedEvent.class);
    when(event.isAccepted()).thenReturn(true);
    when(event.getFileCount()).thenReturn(3);
    when(event.getJobRunId()).thenReturn("42");
    when(event.getJsonMessage()).thenReturn("Json Message");
    when(event.getReasons()).thenReturn(new ArrayList<>());
    when(event.getJobId()).thenReturn("42");
    when(event.getTableId()).thenReturn("42");
    when(event.getValidationTime())
        .thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act and Assert
    assertThrows(IngestTrackerException.class, () -> dynamoDBIngestJobTracker.jobValidated(event));
    verify(getTimeNow).get();
    verify(event).getFileCount();
    verify(event, atLeast(1)).getJobId();
    verify(event).getJobRunId();
    verify(event).getJsonMessage();
    verify(event).getReasons();
    verify(event).getTableId();
    verify(event).getValidationTime();
    verify(event).isAccepted();
  }

  /**
   * Test {@link DynamoDBIngestJobTracker#jobStarted(IngestJobStartedEvent)}.
   * <p>
   * Method under test: {@link DynamoDBIngestJobTracker#jobStarted(IngestJobStartedEvent)}
   */
  @Test
  @DisplayName("Test jobStarted(IngestJobStartedEvent)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBIngestJobTracker.jobStarted(IngestJobStartedEvent)"})
  void testJobStarted() {
    // Arrange
    Supplier<Instant> getTimeNow = mock(Supplier.class);
    when(getTimeNow.get()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    DynamoDBIngestJobTracker dynamoDBIngestJobTracker = new DynamoDBIngestJobTracker(dynamoDB, new InstanceProperties(),
        getTimeNow);
    IngestJobStartedEvent event = mock(IngestJobStartedEvent.class);
    when(event.getFileCount()).thenReturn(3);
    when(event.getJobRunId()).thenReturn("42");
    when(event.getTaskId()).thenReturn("42");
    when(event.getJobId()).thenReturn("42");
    when(event.getTableId()).thenReturn("42");
    when(event.getStartTime()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act and Assert
    assertThrows(IngestTrackerException.class, () -> dynamoDBIngestJobTracker.jobStarted(event));
    verify(getTimeNow).get();
    verify(event).getFileCount();
    verify(event, atLeast(1)).getJobId();
    verify(event).getJobRunId();
    verify(event).getStartTime();
    verify(event).getTableId();
    verify(event).getTaskId();
  }

  /**
   * Test {@link DynamoDBIngestJobTracker#jobStarted(IngestJobStartedEvent)}.
   * <p>
   * Method under test: {@link DynamoDBIngestJobTracker#jobStarted(IngestJobStartedEvent)}
   */
  @Test
  @DisplayName("Test jobStarted(IngestJobStartedEvent)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBIngestJobTracker.jobStarted(IngestJobStartedEvent)"})
  void testJobStarted2() {
    // Arrange
    Supplier<Instant> getTimeNow = mock(Supplier.class);
    when(getTimeNow.get()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    DynamoDBIngestJobTracker dynamoDBIngestJobTracker = new DynamoDBIngestJobTracker(dynamoDB, new InstanceProperties(),
        getTimeNow);
    IngestJobStartedEvent event = mock(IngestJobStartedEvent.class);
    when(event.getJobRunId()).thenThrow(new IngestTrackerException("An error occurred", new Throwable()));
    when(event.getJobId()).thenReturn("42");
    when(event.getTableId()).thenReturn("42");
    when(event.getStartTime()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act and Assert
    assertThrows(IngestTrackerException.class, () -> dynamoDBIngestJobTracker.jobStarted(event));
    verify(getTimeNow).get();
    verify(event, atLeast(1)).getJobId();
    verify(event).getJobRunId();
    verify(event).getStartTime();
    verify(event).getTableId();
  }

  /**
   * Test {@link DynamoDBIngestJobTracker#jobStarted(IngestJobStartedEvent)}.
   * <p>
   * Method under test: {@link DynamoDBIngestJobTracker#jobStarted(IngestJobStartedEvent)}
   */
  @Test
  @DisplayName("Test jobStarted(IngestJobStartedEvent)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBIngestJobTracker.jobStarted(IngestJobStartedEvent)"})
  void testJobStarted3() {
    // Arrange
    Supplier<Instant> getTimeNow = mock(Supplier.class);
    when(getTimeNow.get()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient(new AnonymousAWSCredentials());
    DynamoDBIngestJobTracker dynamoDBIngestJobTracker = new DynamoDBIngestJobTracker(dynamoDB, new InstanceProperties(),
        getTimeNow);
    IngestJobStartedEvent event = mock(IngestJobStartedEvent.class);
    when(event.getFileCount()).thenReturn(3);
    when(event.getJobRunId()).thenReturn("42");
    when(event.getTaskId()).thenReturn("42");
    when(event.getJobId()).thenReturn("42");
    when(event.getTableId()).thenReturn("42");
    when(event.getStartTime()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act and Assert
    assertThrows(IngestTrackerException.class, () -> dynamoDBIngestJobTracker.jobStarted(event));
    verify(getTimeNow).get();
    verify(event).getFileCount();
    verify(event, atLeast(1)).getJobId();
    verify(event).getJobRunId();
    verify(event).getStartTime();
    verify(event).getTableId();
    verify(event).getTaskId();
  }

  /**
   * Test {@link DynamoDBIngestJobTracker#jobStarted(IngestJobStartedEvent)}.
   * <p>
   * Method under test: {@link DynamoDBIngestJobTracker#jobStarted(IngestJobStartedEvent)}
   */
  @Test
  @DisplayName("Test jobStarted(IngestJobStartedEvent)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBIngestJobTracker.jobStarted(IngestJobStartedEvent)"})
  void testJobStarted4() {
    // Arrange
    AsyncHandler<PutItemRequest, PutItemResult> asyncHandler = mock(AsyncHandler.class);
    doNothing().when(asyncHandler).onError(Mockito.<Exception>any());

    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    dynamoDB.putItemAsync(new PutItemRequest("Table Name", new HashMap<>()), asyncHandler);
    Supplier<Instant> getTimeNow = mock(Supplier.class);
    when(getTimeNow.get()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    DynamoDBIngestJobTracker dynamoDBIngestJobTracker = new DynamoDBIngestJobTracker(dynamoDB, new InstanceProperties(),
        getTimeNow);
    IngestJobStartedEvent event = mock(IngestJobStartedEvent.class);
    when(event.getFileCount()).thenReturn(3);
    when(event.getJobRunId()).thenReturn("42");
    when(event.getTaskId()).thenReturn("42");
    when(event.getJobId()).thenReturn("42");
    when(event.getTableId()).thenReturn("42");
    when(event.getStartTime()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act and Assert
    assertThrows(IngestTrackerException.class, () -> dynamoDBIngestJobTracker.jobStarted(event));
    verify(asyncHandler).onError(isA(Exception.class));
    verify(getTimeNow).get();
    verify(event).getFileCount();
    verify(event, atLeast(1)).getJobId();
    verify(event).getJobRunId();
    verify(event).getStartTime();
    verify(event).getTableId();
    verify(event).getTaskId();
  }

  /**
   * Test {@link DynamoDBIngestJobTracker#jobStarted(IngestJobStartedEvent)}.
   * <p>
   * Method under test: {@link DynamoDBIngestJobTracker#jobStarted(IngestJobStartedEvent)}
   */
  @Test
  @DisplayName("Test jobStarted(IngestJobStartedEvent)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBIngestJobTracker.jobStarted(IngestJobStartedEvent)"})
  void testJobStarted5() {
    // Arrange
    RequestMetricCollector requestMetricCollector = mock(RequestMetricCollector.class);
    doNothing().when(requestMetricCollector)
        .collectMetrics(Mockito.<Request<Object>>any(), Mockito.<Response<Object>>any());
    when(requestMetricCollector.isEnabled()).thenReturn(true);

    PutItemRequest request = new PutItemRequest();
    request.setRequestMetricCollector(requestMetricCollector);
    AsyncHandler<PutItemRequest, PutItemResult> asyncHandler = mock(AsyncHandler.class);
    doThrow(new RuntimeException(DynamoDBIngestJobTracker.TABLE_ID)).when(asyncHandler)
        .onError(Mockito.<Exception>any());

    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    dynamoDB.putItemAsync(request, asyncHandler);
    Supplier<Instant> getTimeNow = mock(Supplier.class);
    when(getTimeNow.get()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    DynamoDBIngestJobTracker dynamoDBIngestJobTracker = new DynamoDBIngestJobTracker(dynamoDB, new InstanceProperties(),
        getTimeNow);
    IngestJobStartedEvent event = mock(IngestJobStartedEvent.class);
    when(event.getFileCount()).thenReturn(3);
    when(event.getJobRunId()).thenReturn("42");
    when(event.getTaskId()).thenReturn("42");
    when(event.getJobId()).thenReturn("42");
    when(event.getTableId()).thenReturn("42");
    when(event.getStartTime()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act and Assert
    assertThrows(IngestTrackerException.class, () -> dynamoDBIngestJobTracker.jobStarted(event));
    verify(asyncHandler).onError(isA(Exception.class));
    verify(requestMetricCollector).collectMetrics(isA(Request.class), isNull());
    verify(requestMetricCollector).isEnabled();
    verify(getTimeNow).get();
    verify(event).getFileCount();
    verify(event, atLeast(1)).getJobId();
    verify(event).getJobRunId();
    verify(event).getStartTime();
    verify(event).getTableId();
    verify(event).getTaskId();
  }

  /**
   * Test {@link DynamoDBIngestJobTracker#jobStarted(IngestJobStartedEvent)}.
   * <p>
   * Method under test: {@link DynamoDBIngestJobTracker#jobStarted(IngestJobStartedEvent)}
   */
  @Test
  @DisplayName("Test jobStarted(IngestJobStartedEvent)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBIngestJobTracker.jobStarted(IngestJobStartedEvent)"})
  void testJobStarted6() {
    // Arrange
    RequestMetricCollector requestMetricCollector = mock(RequestMetricCollector.class);
    doNothing().when(requestMetricCollector)
        .collectMetrics(Mockito.<Request<Object>>any(), Mockito.<Response<Object>>any());
    when(requestMetricCollector.isEnabled()).thenReturn(true);

    PutItemRequest request = new PutItemRequest();
    request.setRequestMetricCollector(requestMetricCollector);
    AsyncHandler<PutItemRequest, PutItemResult> asyncHandler = mock(AsyncHandler.class);
    doThrow(new RuntimeException(DynamoDBIngestJobTracker.TABLE_ID)).when(asyncHandler)
        .onError(Mockito.<Exception>any());

    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient(PredefinedClientConfigurations.defaultConfig());
    dynamoDB.putItemAsync(request, asyncHandler);
    Supplier<Instant> getTimeNow = mock(Supplier.class);
    when(getTimeNow.get()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    DynamoDBIngestJobTracker dynamoDBIngestJobTracker = new DynamoDBIngestJobTracker(dynamoDB, new InstanceProperties(),
        getTimeNow);
    IngestJobStartedEvent event = mock(IngestJobStartedEvent.class);
    when(event.getFileCount()).thenReturn(3);
    when(event.getJobRunId()).thenReturn("42");
    when(event.getTaskId()).thenReturn("42");
    when(event.getJobId()).thenReturn("42");
    when(event.getTableId()).thenReturn("42");
    when(event.getStartTime()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act and Assert
    assertThrows(IngestTrackerException.class, () -> dynamoDBIngestJobTracker.jobStarted(event));
    verify(asyncHandler).onError(isA(Exception.class));
    verify(requestMetricCollector).collectMetrics(isA(Request.class), isNull());
    verify(requestMetricCollector).isEnabled();
    verify(getTimeNow).get();
    verify(event).getFileCount();
    verify(event, atLeast(1)).getJobId();
    verify(event).getJobRunId();
    verify(event).getStartTime();
    verify(event).getTableId();
    verify(event).getTaskId();
  }

  /**
   * Test {@link DynamoDBIngestJobTracker#jobAddedFiles(IngestJobAddedFilesEvent)}.
   * <p>
   * Method under test: {@link DynamoDBIngestJobTracker#jobAddedFiles(IngestJobAddedFilesEvent)}
   */
  @Test
  @DisplayName("Test jobAddedFiles(IngestJobAddedFilesEvent)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBIngestJobTracker.jobAddedFiles(IngestJobAddedFilesEvent)"})
  void testJobAddedFiles() {
    // Arrange
    Supplier<Instant> getTimeNow = mock(Supplier.class);
    when(getTimeNow.get()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    DynamoDBIngestJobTracker dynamoDBIngestJobTracker = new DynamoDBIngestJobTracker(dynamoDB, new InstanceProperties(),
        getTimeNow);
    IngestJobAddedFilesEvent event = mock(IngestJobAddedFilesEvent.class);
    when(event.getFileCount()).thenReturn(3);
    when(event.getJobRunId()).thenReturn("42");
    when(event.getTaskId()).thenReturn("42");
    when(event.getJobId()).thenReturn("42");
    when(event.getTableId()).thenReturn("42");
    when(event.getWrittenTime()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act and Assert
    assertThrows(IngestTrackerException.class, () -> dynamoDBIngestJobTracker.jobAddedFiles(event));
    verify(getTimeNow).get();
    verify(event).getFileCount();
    verify(event, atLeast(1)).getJobId();
    verify(event).getJobRunId();
    verify(event).getTableId();
    verify(event).getTaskId();
    verify(event).getWrittenTime();
  }

  /**
   * Test {@link DynamoDBIngestJobTracker#jobAddedFiles(IngestJobAddedFilesEvent)}.
   * <p>
   * Method under test: {@link DynamoDBIngestJobTracker#jobAddedFiles(IngestJobAddedFilesEvent)}
   */
  @Test
  @DisplayName("Test jobAddedFiles(IngestJobAddedFilesEvent)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBIngestJobTracker.jobAddedFiles(IngestJobAddedFilesEvent)"})
  void testJobAddedFiles2() {
    // Arrange
    Supplier<Instant> getTimeNow = mock(Supplier.class);
    when(getTimeNow.get()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    DynamoDBIngestJobTracker dynamoDBIngestJobTracker = new DynamoDBIngestJobTracker(dynamoDB, new InstanceProperties(),
        getTimeNow);
    IngestJobAddedFilesEvent event = mock(IngestJobAddedFilesEvent.class);
    when(event.getJobRunId()).thenThrow(new IngestTrackerException("An error occurred", new Throwable()));
    when(event.getJobId()).thenReturn("42");
    when(event.getTableId()).thenReturn("42");
    when(event.getWrittenTime()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act and Assert
    assertThrows(IngestTrackerException.class, () -> dynamoDBIngestJobTracker.jobAddedFiles(event));
    verify(getTimeNow).get();
    verify(event, atLeast(1)).getJobId();
    verify(event).getJobRunId();
    verify(event).getTableId();
    verify(event).getWrittenTime();
  }

  /**
   * Test {@link DynamoDBIngestJobTracker#jobAddedFiles(IngestJobAddedFilesEvent)}.
   * <p>
   * Method under test: {@link DynamoDBIngestJobTracker#jobAddedFiles(IngestJobAddedFilesEvent)}
   */
  @Test
  @DisplayName("Test jobAddedFiles(IngestJobAddedFilesEvent)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBIngestJobTracker.jobAddedFiles(IngestJobAddedFilesEvent)"})
  void testJobAddedFiles3() {
    // Arrange
    Supplier<Instant> getTimeNow = mock(Supplier.class);
    when(getTimeNow.get()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient(new AnonymousAWSCredentials());
    DynamoDBIngestJobTracker dynamoDBIngestJobTracker = new DynamoDBIngestJobTracker(dynamoDB, new InstanceProperties(),
        getTimeNow);
    IngestJobAddedFilesEvent event = mock(IngestJobAddedFilesEvent.class);
    when(event.getFileCount()).thenReturn(3);
    when(event.getJobRunId()).thenReturn("42");
    when(event.getTaskId()).thenReturn("42");
    when(event.getJobId()).thenReturn("42");
    when(event.getTableId()).thenReturn("42");
    when(event.getWrittenTime()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act and Assert
    assertThrows(IngestTrackerException.class, () -> dynamoDBIngestJobTracker.jobAddedFiles(event));
    verify(getTimeNow).get();
    verify(event).getFileCount();
    verify(event, atLeast(1)).getJobId();
    verify(event).getJobRunId();
    verify(event).getTableId();
    verify(event).getTaskId();
    verify(event).getWrittenTime();
  }

  /**
   * Test {@link DynamoDBIngestJobTracker#jobAddedFiles(IngestJobAddedFilesEvent)}.
   * <p>
   * Method under test: {@link DynamoDBIngestJobTracker#jobAddedFiles(IngestJobAddedFilesEvent)}
   */
  @Test
  @DisplayName("Test jobAddedFiles(IngestJobAddedFilesEvent)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBIngestJobTracker.jobAddedFiles(IngestJobAddedFilesEvent)"})
  void testJobAddedFiles4() {
    // Arrange
    RequestMetricCollector requestMetricCollector = mock(RequestMetricCollector.class);
    doNothing().when(requestMetricCollector)
        .collectMetrics(Mockito.<Request<Object>>any(), Mockito.<Response<Object>>any());
    when(requestMetricCollector.isEnabled()).thenReturn(true);

    PutItemRequest request = new PutItemRequest();
    request.setRequestMetricCollector(requestMetricCollector);

    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient(PredefinedClientConfigurations.defaultConfig());
    dynamoDB.putItemAsync(request);
    Supplier<Instant> getTimeNow = mock(Supplier.class);
    when(getTimeNow.get()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    DynamoDBIngestJobTracker dynamoDBIngestJobTracker = new DynamoDBIngestJobTracker(dynamoDB, new InstanceProperties(),
        getTimeNow);
    IngestJobAddedFilesEvent event = mock(IngestJobAddedFilesEvent.class);
    when(event.getFileCount()).thenReturn(3);
    when(event.getJobRunId()).thenReturn("42");
    when(event.getTaskId()).thenReturn("42");
    when(event.getJobId()).thenReturn("42");
    when(event.getTableId()).thenReturn("42");
    when(event.getWrittenTime()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act and Assert
    assertThrows(IngestTrackerException.class, () -> dynamoDBIngestJobTracker.jobAddedFiles(event));
    verify(requestMetricCollector).collectMetrics(isA(Request.class), isNull());
    verify(requestMetricCollector).isEnabled();
    verify(getTimeNow).get();
    verify(event).getFileCount();
    verify(event, atLeast(1)).getJobId();
    verify(event).getJobRunId();
    verify(event).getTableId();
    verify(event).getTaskId();
    verify(event).getWrittenTime();
  }

  /**
   * Test {@link DynamoDBIngestJobTracker#jobAddedFiles(IngestJobAddedFilesEvent)}.
   * <p>
   * Method under test: {@link DynamoDBIngestJobTracker#jobAddedFiles(IngestJobAddedFilesEvent)}
   */
  @Test
  @DisplayName("Test jobAddedFiles(IngestJobAddedFilesEvent)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBIngestJobTracker.jobAddedFiles(IngestJobAddedFilesEvent)"})
  void testJobAddedFiles5() {
    // Arrange
    RequestMetricCollector requestMetricCollector = mock(RequestMetricCollector.class);
    doNothing().when(requestMetricCollector)
        .collectMetrics(Mockito.<Request<Object>>any(), Mockito.<Response<Object>>any());
    when(requestMetricCollector.isEnabled()).thenReturn(true);

    PutItemRequest request = new PutItemRequest();
    request.setRequestMetricCollector(requestMetricCollector);
    AsyncHandler<PutItemRequest, PutItemResult> asyncHandler = mock(AsyncHandler.class);
    doThrow(new IngestTrackerException("An error occurred", new Throwable())).when(asyncHandler)
        .onError(Mockito.<Exception>any());

    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    dynamoDB.putItemAsync(new PutItemRequest(), asyncHandler);
    dynamoDB.putItemAsync(request);
    Supplier<Instant> getTimeNow = mock(Supplier.class);
    when(getTimeNow.get()).thenReturn(LocalDate.ofYearDay(4, 4).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    DynamoDBIngestJobTracker dynamoDBIngestJobTracker = new DynamoDBIngestJobTracker(dynamoDB, new InstanceProperties(),
        getTimeNow);
    IngestJobAddedFilesEvent event = mock(IngestJobAddedFilesEvent.class);
    when(event.getFileCount()).thenReturn(3);
    when(event.getJobRunId()).thenReturn("42");
    when(event.getTaskId()).thenReturn("42");
    when(event.getJobId()).thenReturn("42");
    when(event.getTableId()).thenReturn("42");
    when(event.getWrittenTime()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act and Assert
    assertThrows(IngestTrackerException.class, () -> dynamoDBIngestJobTracker.jobAddedFiles(event));
    verify(asyncHandler).onError(isA(Exception.class));
    verify(requestMetricCollector).collectMetrics(isA(Request.class), isNull());
    verify(requestMetricCollector).isEnabled();
    verify(getTimeNow).get();
    verify(event).getFileCount();
    verify(event, atLeast(1)).getJobId();
    verify(event).getJobRunId();
    verify(event).getTableId();
    verify(event).getTaskId();
    verify(event).getWrittenTime();
  }

  /**
   * Test {@link DynamoDBIngestJobTracker#jobAddedFiles(IngestJobAddedFilesEvent)}.
   * <p>
   * Method under test: {@link DynamoDBIngestJobTracker#jobAddedFiles(IngestJobAddedFilesEvent)}
   */
  @Test
  @DisplayName("Test jobAddedFiles(IngestJobAddedFilesEvent)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBIngestJobTracker.jobAddedFiles(IngestJobAddedFilesEvent)"})
  void testJobAddedFiles6() {
    // Arrange
    RequestMetricCollector requestMetricCollector = mock(RequestMetricCollector.class);
    doNothing().when(requestMetricCollector)
        .collectMetrics(Mockito.<Request<Object>>any(), Mockito.<Response<Object>>any());
    when(requestMetricCollector.isEnabled()).thenReturn(true);

    PutItemRequest request = new PutItemRequest();
    request.setRequestMetricCollector(requestMetricCollector);

    PutItemRequest request2 = new PutItemRequest();
    request2.putCustomRequestHeader("Name", "42");
    AsyncHandler<PutItemRequest, PutItemResult> asyncHandler = mock(AsyncHandler.class);
    doNothing().when(asyncHandler).onError(Mockito.<Exception>any());

    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    dynamoDB.addRequestHandler(new MessageMD5ChecksumHandler());
    dynamoDB.putItemAsync(request2, asyncHandler);
    dynamoDB.putItemAsync(request);
    Supplier<Instant> getTimeNow = mock(Supplier.class);
    when(getTimeNow.get()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    DynamoDBIngestJobTracker dynamoDBIngestJobTracker = new DynamoDBIngestJobTracker(dynamoDB, new InstanceProperties(),
        getTimeNow);
    IngestJobAddedFilesEvent event = mock(IngestJobAddedFilesEvent.class);
    when(event.getFileCount()).thenReturn(3);
    when(event.getJobRunId()).thenReturn("42");
    when(event.getTaskId()).thenReturn("42");
    when(event.getJobId()).thenReturn("42");
    when(event.getTableId()).thenReturn("42");
    when(event.getWrittenTime()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act and Assert
    assertThrows(IngestTrackerException.class, () -> dynamoDBIngestJobTracker.jobAddedFiles(event));
    verify(asyncHandler).onError(Mockito.<Exception>any());
    verify(requestMetricCollector).collectMetrics(isA(Request.class), isNull());
    verify(requestMetricCollector).isEnabled();
    verify(getTimeNow).get();
    verify(event).getFileCount();
    verify(event, atLeast(1)).getJobId();
    verify(event).getJobRunId();
    verify(event).getTableId();
    verify(event).getTaskId();
    verify(event).getWrittenTime();
  }

  /**
   * Test {@link DynamoDBIngestJobTracker#jobAddedFiles(IngestJobAddedFilesEvent)}.
   * <ul>
   *   <li>Given {@link AmazonDynamoDBAsyncClient#AmazonDynamoDBAsyncClient()} ItemAsync is {@link PutItemRequest#PutItemRequest()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBIngestJobTracker#jobAddedFiles(IngestJobAddedFilesEvent)}
   */
  @Test
  @DisplayName("Test jobAddedFiles(IngestJobAddedFilesEvent); given AmazonDynamoDBAsyncClient() ItemAsync is PutItemRequest()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBIngestJobTracker.jobAddedFiles(IngestJobAddedFilesEvent)"})
  void testJobAddedFiles_givenAmazonDynamoDBAsyncClientItemAsyncIsPutItemRequest() {
    // Arrange
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    dynamoDB.putItemAsync(new PutItemRequest());
    Supplier<Instant> getTimeNow = mock(Supplier.class);
    when(getTimeNow.get()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    DynamoDBIngestJobTracker dynamoDBIngestJobTracker = new DynamoDBIngestJobTracker(dynamoDB, new InstanceProperties(),
        getTimeNow);
    IngestJobAddedFilesEvent event = mock(IngestJobAddedFilesEvent.class);
    when(event.getFileCount()).thenReturn(3);
    when(event.getJobRunId()).thenReturn("42");
    when(event.getTaskId()).thenReturn("42");
    when(event.getJobId()).thenReturn("42");
    when(event.getTableId()).thenReturn("42");
    when(event.getWrittenTime()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act and Assert
    assertThrows(IngestTrackerException.class, () -> dynamoDBIngestJobTracker.jobAddedFiles(event));
    verify(getTimeNow).get();
    verify(event).getFileCount();
    verify(event, atLeast(1)).getJobId();
    verify(event).getJobRunId();
    verify(event).getTableId();
    verify(event).getTaskId();
    verify(event).getWrittenTime();
  }

  /**
   * Test {@link DynamoDBIngestJobTracker#jobAddedFiles(IngestJobAddedFilesEvent)}.
   * <ul>
   *   <li>Given {@link PutItemRequest#PutItemRequest()} ConditionExpression is {@code Condition Expression}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBIngestJobTracker#jobAddedFiles(IngestJobAddedFilesEvent)}
   */
  @Test
  @DisplayName("Test jobAddedFiles(IngestJobAddedFilesEvent); given PutItemRequest() ConditionExpression is 'Condition Expression'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBIngestJobTracker.jobAddedFiles(IngestJobAddedFilesEvent)"})
  void testJobAddedFiles_givenPutItemRequestConditionExpressionIsConditionExpression() {
    // Arrange
    RequestMetricCollector requestMetricCollector = mock(RequestMetricCollector.class);
    doNothing().when(requestMetricCollector)
        .collectMetrics(Mockito.<Request<Object>>any(), Mockito.<Response<Object>>any());
    when(requestMetricCollector.isEnabled()).thenReturn(true);

    PutItemRequest request = new PutItemRequest();
    request.setConditionExpression("Condition Expression");
    request.setRequestMetricCollector(requestMetricCollector);
    AsyncHandler<PutItemRequest, PutItemResult> asyncHandler = mock(AsyncHandler.class);
    doNothing().when(asyncHandler).onError(Mockito.<Exception>any());

    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    dynamoDB.putItemAsync(new PutItemRequest(), asyncHandler);
    dynamoDB.putItemAsync(request);
    Supplier<Instant> getTimeNow = mock(Supplier.class);
    when(getTimeNow.get()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    DynamoDBIngestJobTracker dynamoDBIngestJobTracker = new DynamoDBIngestJobTracker(dynamoDB, new InstanceProperties(),
        getTimeNow);
    IngestJobAddedFilesEvent event = mock(IngestJobAddedFilesEvent.class);
    when(event.getFileCount()).thenReturn(3);
    when(event.getJobRunId()).thenReturn("42");
    when(event.getTaskId()).thenReturn("42");
    when(event.getJobId()).thenReturn("42");
    when(event.getTableId()).thenReturn("42");
    when(event.getWrittenTime()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act and Assert
    assertThrows(IngestTrackerException.class, () -> dynamoDBIngestJobTracker.jobAddedFiles(event));
    verify(asyncHandler).onError(isA(Exception.class));
    verify(requestMetricCollector).collectMetrics(isA(Request.class), isNull());
    verify(requestMetricCollector).isEnabled();
    verify(getTimeNow).get();
    verify(event).getFileCount();
    verify(event, atLeast(1)).getJobId();
    verify(event).getJobRunId();
    verify(event).getTableId();
    verify(event).getTaskId();
    verify(event).getWrittenTime();
  }

  /**
   * Test {@link DynamoDBIngestJobTracker#jobAddedFiles(IngestJobAddedFilesEvent)}.
   * <ul>
   *   <li>Given {@link Supplier} {@link Supplier#get()} return now atStartOfDay atZone {@link ZoneOffset#UTC} toInstant.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBIngestJobTracker#jobAddedFiles(IngestJobAddedFilesEvent)}
   */
  @Test
  @DisplayName("Test jobAddedFiles(IngestJobAddedFilesEvent); given Supplier get() return now atStartOfDay atZone UTC toInstant")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBIngestJobTracker.jobAddedFiles(IngestJobAddedFilesEvent)"})
  void testJobAddedFiles_givenSupplierGetReturnNowAtStartOfDayAtZoneUtcToInstant() {
    // Arrange
    RequestMetricCollector requestMetricCollector = mock(RequestMetricCollector.class);
    doNothing().when(requestMetricCollector)
        .collectMetrics(Mockito.<Request<Object>>any(), Mockito.<Response<Object>>any());
    when(requestMetricCollector.isEnabled()).thenReturn(true);

    PutItemRequest request = new PutItemRequest();
    request.setRequestMetricCollector(requestMetricCollector);
    AsyncHandler<PutItemRequest, PutItemResult> asyncHandler = mock(AsyncHandler.class);
    doNothing().when(asyncHandler).onError(Mockito.<Exception>any());

    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    dynamoDB.putItemAsync(new PutItemRequest(), asyncHandler);
    dynamoDB.putItemAsync(request);
    Supplier<Instant> getTimeNow = mock(Supplier.class);
    when(getTimeNow.get()).thenReturn(LocalDate.now().atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    DynamoDBIngestJobTracker dynamoDBIngestJobTracker = new DynamoDBIngestJobTracker(dynamoDB, new InstanceProperties(),
        getTimeNow);
    IngestJobAddedFilesEvent event = mock(IngestJobAddedFilesEvent.class);
    when(event.getFileCount()).thenReturn(3);
    when(event.getJobRunId()).thenReturn("42");
    when(event.getTaskId()).thenReturn("42");
    when(event.getJobId()).thenReturn("42");
    when(event.getTableId()).thenReturn("42");
    when(event.getWrittenTime()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act and Assert
    assertThrows(IngestTrackerException.class, () -> dynamoDBIngestJobTracker.jobAddedFiles(event));
    verify(asyncHandler).onError(Mockito.<Exception>any());
    verify(requestMetricCollector).collectMetrics(isA(Request.class), isNull());
    verify(requestMetricCollector).isEnabled();
    verify(getTimeNow).get();
    verify(event).getFileCount();
    verify(event, atLeast(1)).getJobId();
    verify(event).getJobRunId();
    verify(event).getTableId();
    verify(event).getTaskId();
    verify(event).getWrittenTime();
  }

  /**
   * Test {@link DynamoDBIngestJobTracker#jobFinished(IngestJobFinishedEvent)}.
   * <p>
   * Method under test: {@link DynamoDBIngestJobTracker#jobFinished(IngestJobFinishedEvent)}
   */
  @Test
  @DisplayName("Test jobFinished(IngestJobFinishedEvent)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBIngestJobTracker.jobFinished(IngestJobFinishedEvent)"})
  void testJobFinished() {
    // Arrange
    Supplier<Instant> getTimeNow = mock(Supplier.class);
    when(getTimeNow.get()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    DynamoDBIngestJobTracker dynamoDBIngestJobTracker = new DynamoDBIngestJobTracker(dynamoDB, new InstanceProperties(),
        getTimeNow);
    IngestJobFinishedEvent event = mock(IngestJobFinishedEvent.class);
    when(event.isCommittedBySeparateFileUpdates())
        .thenThrow(new IngestTrackerException("An error occurred", new Throwable()));
    when(event.getJobId()).thenReturn("42");
    when(event.getJobRunId()).thenReturn("42");
    when(event.getTableId()).thenReturn("42");
    when(event.getTaskId()).thenReturn("42");
    when(event.getFinishTime()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    when(event.getRecordsProcessed()).thenReturn(new RecordsProcessed(1L, 1L));

    // Act and Assert
    assertThrows(IngestTrackerException.class, () -> dynamoDBIngestJobTracker.jobFinished(event));
    verify(getTimeNow).get();
    verify(event).getFinishTime();
    verify(event, atLeast(1)).getJobId();
    verify(event).getJobRunId();
    verify(event).getRecordsProcessed();
    verify(event).getTableId();
    verify(event).getTaskId();
    verify(event).isCommittedBySeparateFileUpdates();
  }

  /**
   * Test {@link DynamoDBIngestJobTracker#jobFinished(IngestJobFinishedEvent)}.
   * <p>
   * Method under test: {@link DynamoDBIngestJobTracker#jobFinished(IngestJobFinishedEvent)}
   */
  @Test
  @DisplayName("Test jobFinished(IngestJobFinishedEvent)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBIngestJobTracker.jobFinished(IngestJobFinishedEvent)"})
  void testJobFinished2() {
    // Arrange
    Supplier<Instant> getTimeNow = mock(Supplier.class);
    when(getTimeNow.get()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient(new AnonymousAWSCredentials());
    DynamoDBIngestJobTracker dynamoDBIngestJobTracker = new DynamoDBIngestJobTracker(dynamoDB, new InstanceProperties(),
        getTimeNow);
    IngestJobFinishedEvent event = mock(IngestJobFinishedEvent.class);
    when(event.isCommittedBySeparateFileUpdates()).thenReturn(true);
    when(event.getNumFilesWrittenByJob()).thenReturn(10);
    when(event.getJobId()).thenReturn("42");
    when(event.getJobRunId()).thenReturn("42");
    when(event.getTableId()).thenReturn("42");
    when(event.getTaskId()).thenReturn("42");
    when(event.getFinishTime()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    when(event.getRecordsProcessed()).thenReturn(new RecordsProcessed(1L, 1L));

    // Act and Assert
    assertThrows(IngestTrackerException.class, () -> dynamoDBIngestJobTracker.jobFinished(event));
    verify(getTimeNow).get();
    verify(event).getFinishTime();
    verify(event, atLeast(1)).getJobId();
    verify(event).getJobRunId();
    verify(event).getNumFilesWrittenByJob();
    verify(event).getRecordsProcessed();
    verify(event).getTableId();
    verify(event).getTaskId();
    verify(event).isCommittedBySeparateFileUpdates();
  }

  /**
   * Test {@link DynamoDBIngestJobTracker#jobFinished(IngestJobFinishedEvent)}.
   * <ul>
   *   <li>Then calls {@link IngestJobFinishedEvent#getNumFilesWrittenByJob()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBIngestJobTracker#jobFinished(IngestJobFinishedEvent)}
   */
  @Test
  @DisplayName("Test jobFinished(IngestJobFinishedEvent); then calls getNumFilesWrittenByJob()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBIngestJobTracker.jobFinished(IngestJobFinishedEvent)"})
  void testJobFinished_thenCallsGetNumFilesWrittenByJob() {
    // Arrange
    Supplier<Instant> getTimeNow = mock(Supplier.class);
    when(getTimeNow.get()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    DynamoDBIngestJobTracker dynamoDBIngestJobTracker = new DynamoDBIngestJobTracker(dynamoDB, new InstanceProperties(),
        getTimeNow);
    IngestJobFinishedEvent event = mock(IngestJobFinishedEvent.class);
    when(event.isCommittedBySeparateFileUpdates()).thenReturn(true);
    when(event.getNumFilesWrittenByJob()).thenReturn(10);
    when(event.getJobId()).thenReturn("42");
    when(event.getJobRunId()).thenReturn("42");
    when(event.getTableId()).thenReturn("42");
    when(event.getTaskId()).thenReturn("42");
    when(event.getFinishTime()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    when(event.getRecordsProcessed()).thenReturn(new RecordsProcessed(1L, 1L));

    // Act and Assert
    assertThrows(IngestTrackerException.class, () -> dynamoDBIngestJobTracker.jobFinished(event));
    verify(getTimeNow).get();
    verify(event).getFinishTime();
    verify(event, atLeast(1)).getJobId();
    verify(event).getJobRunId();
    verify(event).getNumFilesWrittenByJob();
    verify(event).getRecordsProcessed();
    verify(event).getTableId();
    verify(event).getTaskId();
    verify(event).isCommittedBySeparateFileUpdates();
  }

  /**
   * Test {@link DynamoDBIngestJobTracker#jobFinished(IngestJobFinishedEvent)}.
   * <ul>
   *   <li>Then calls {@link AsyncHandler#onError(Exception)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBIngestJobTracker#jobFinished(IngestJobFinishedEvent)}
   */
  @Test
  @DisplayName("Test jobFinished(IngestJobFinishedEvent); then calls onError(Exception)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBIngestJobTracker.jobFinished(IngestJobFinishedEvent)"})
  void testJobFinished_thenCallsOnError() {
    // Arrange
    PutItemRequest request = new PutItemRequest();
    AsyncHandler<PutItemRequest, PutItemResult> asyncHandler = mock(AsyncHandler.class);
    doThrow(new RuntimeException("foo")).when(asyncHandler).onError(Mockito.<Exception>any());

    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    dynamoDB.putItemAsync(request, asyncHandler);
    Supplier<Instant> getTimeNow = mock(Supplier.class);
    when(getTimeNow.get()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    DynamoDBIngestJobTracker dynamoDBIngestJobTracker = new DynamoDBIngestJobTracker(dynamoDB, new InstanceProperties(),
        getTimeNow);
    IngestJobFinishedEvent event = mock(IngestJobFinishedEvent.class);
    when(event.isCommittedBySeparateFileUpdates()).thenReturn(true);
    when(event.getNumFilesWrittenByJob()).thenReturn(10);
    when(event.getJobId()).thenReturn("42");
    when(event.getJobRunId()).thenReturn("42");
    when(event.getTableId()).thenReturn("42");
    when(event.getTaskId()).thenReturn("42");
    when(event.getFinishTime()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    when(event.getRecordsProcessed()).thenReturn(new RecordsProcessed(1L, 1L));

    // Act and Assert
    assertThrows(IngestTrackerException.class, () -> dynamoDBIngestJobTracker.jobFinished(event));
    verify(asyncHandler).onError(isA(Exception.class));
    verify(getTimeNow).get();
    verify(event).getFinishTime();
    verify(event, atLeast(1)).getJobId();
    verify(event).getJobRunId();
    verify(event).getNumFilesWrittenByJob();
    verify(event).getRecordsProcessed();
    verify(event).getTableId();
    verify(event).getTaskId();
    verify(event).isCommittedBySeparateFileUpdates();
  }

  /**
   * Test {@link DynamoDBIngestJobTracker#jobFailed(IngestJobFailedEvent)}.
   * <p>
   * Method under test: {@link DynamoDBIngestJobTracker#jobFailed(IngestJobFailedEvent)}
   */
  @Test
  @DisplayName("Test jobFailed(IngestJobFailedEvent)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBIngestJobTracker.jobFailed(IngestJobFailedEvent)"})
  void testJobFailed() {
    // Arrange
    Supplier<Instant> getTimeNow = mock(Supplier.class);
    when(getTimeNow.get()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    DynamoDBIngestJobTracker dynamoDBIngestJobTracker = new DynamoDBIngestJobTracker(dynamoDB, new InstanceProperties(),
        getTimeNow);
    IngestJobFailedEvent event = mock(IngestJobFailedEvent.class);
    when(event.getFailureReasons()).thenReturn(new ArrayList<>());
    when(event.getJobId()).thenReturn("42");
    when(event.getJobRunId()).thenReturn("42");
    when(event.getTableId()).thenReturn("42");
    when(event.getTaskId()).thenReturn("42");
    when(event.getFailureTime()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act and Assert
    assertThrows(IngestTrackerException.class, () -> dynamoDBIngestJobTracker.jobFailed(event));
    verify(getTimeNow).get();
    verify(event).getFailureReasons();
    verify(event).getFailureTime();
    verify(event, atLeast(1)).getJobId();
    verify(event).getJobRunId();
    verify(event).getTableId();
    verify(event).getTaskId();
  }

  /**
   * Test {@link DynamoDBIngestJobTracker#jobFailed(IngestJobFailedEvent)}.
   * <p>
   * Method under test: {@link DynamoDBIngestJobTracker#jobFailed(IngestJobFailedEvent)}
   */
  @Test
  @DisplayName("Test jobFailed(IngestJobFailedEvent)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBIngestJobTracker.jobFailed(IngestJobFailedEvent)"})
  void testJobFailed2() {
    // Arrange
    Supplier<Instant> getTimeNow = mock(Supplier.class);
    when(getTimeNow.get()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient(new AnonymousAWSCredentials());
    DynamoDBIngestJobTracker dynamoDBIngestJobTracker = new DynamoDBIngestJobTracker(dynamoDB, new InstanceProperties(),
        getTimeNow);
    IngestJobFailedEvent event = mock(IngestJobFailedEvent.class);
    when(event.getFailureReasons()).thenReturn(new ArrayList<>());
    when(event.getJobId()).thenReturn("42");
    when(event.getJobRunId()).thenReturn("42");
    when(event.getTableId()).thenReturn("42");
    when(event.getTaskId()).thenReturn("42");
    when(event.getFailureTime()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act and Assert
    assertThrows(IngestTrackerException.class, () -> dynamoDBIngestJobTracker.jobFailed(event));
    verify(getTimeNow).get();
    verify(event).getFailureReasons();
    verify(event).getFailureTime();
    verify(event, atLeast(1)).getJobId();
    verify(event).getJobRunId();
    verify(event).getTableId();
    verify(event).getTaskId();
  }

  /**
   * Test {@link DynamoDBIngestJobTracker#jobFailed(IngestJobFailedEvent)}.
   * <p>
   * Method under test: {@link DynamoDBIngestJobTracker#jobFailed(IngestJobFailedEvent)}
   */
  @Test
  @DisplayName("Test jobFailed(IngestJobFailedEvent)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBIngestJobTracker.jobFailed(IngestJobFailedEvent)"})
  void testJobFailed3() {
    // Arrange
    RequestMetricCollector requestMetricCollector = mock(RequestMetricCollector.class);
    doNothing().when(requestMetricCollector)
        .collectMetrics(Mockito.<Request<Object>>any(), Mockito.<Response<Object>>any());
    when(requestMetricCollector.isEnabled()).thenReturn(true);

    PutItemRequest request = new PutItemRequest();
    request.setRequestMetricCollector(requestMetricCollector);
    AsyncHandler<PutItemRequest, PutItemResult> asyncHandler = mock(AsyncHandler.class);
    doThrow(new RuntimeException(DynamoDBIngestJobTracker.TABLE_ID)).when(asyncHandler)
        .onError(Mockito.<Exception>any());

    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient(PredefinedClientConfigurations.defaultConfig());
    dynamoDB.putItemAsync(request, asyncHandler);
    Supplier<Instant> getTimeNow = mock(Supplier.class);
    when(getTimeNow.get()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    DynamoDBIngestJobTracker dynamoDBIngestJobTracker = new DynamoDBIngestJobTracker(dynamoDB, new InstanceProperties(),
        getTimeNow);
    IngestJobFailedEvent event = mock(IngestJobFailedEvent.class);
    when(event.getFailureReasons()).thenReturn(new ArrayList<>());
    when(event.getJobId()).thenReturn("42");
    when(event.getJobRunId()).thenReturn("42");
    when(event.getTableId()).thenReturn("42");
    when(event.getTaskId()).thenReturn("42");
    when(event.getFailureTime()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act and Assert
    assertThrows(IngestTrackerException.class, () -> dynamoDBIngestJobTracker.jobFailed(event));
    verify(asyncHandler).onError(isA(Exception.class));
    verify(requestMetricCollector).collectMetrics(isA(Request.class), isNull());
    verify(requestMetricCollector).isEnabled();
    verify(getTimeNow).get();
    verify(event).getFailureReasons();
    verify(event).getFailureTime();
    verify(event, atLeast(1)).getJobId();
    verify(event).getJobRunId();
    verify(event).getTableId();
    verify(event).getTaskId();
  }

  /**
   * Test {@link DynamoDBIngestJobTracker#jobFailed(IngestJobFailedEvent)}.
   * <p>
   * Method under test: {@link DynamoDBIngestJobTracker#jobFailed(IngestJobFailedEvent)}
   */
  @Test
  @DisplayName("Test jobFailed(IngestJobFailedEvent)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBIngestJobTracker.jobFailed(IngestJobFailedEvent)"})
  void testJobFailed4() {
    // Arrange
    RequestMetricCollector requestMetricCollector = mock(RequestMetricCollector.class);
    when(requestMetricCollector.isEnabled()).thenReturn(true);

    PutItemRequest request = new PutItemRequest();
    request.setRequestMetricCollector(requestMetricCollector);

    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient(new AnonymousAWSCredentials());
    dynamoDB.putItemAsync(request, mock(AsyncHandler.class));
    Supplier<Instant> getTimeNow = mock(Supplier.class);
    when(getTimeNow.get()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    DynamoDBIngestJobTracker dynamoDBIngestJobTracker = new DynamoDBIngestJobTracker(dynamoDB, new InstanceProperties(),
        getTimeNow);
    IngestJobFailedEvent event = mock(IngestJobFailedEvent.class);
    when(event.getFailureReasons()).thenReturn(new ArrayList<>());
    when(event.getJobId()).thenReturn("42");
    when(event.getJobRunId()).thenReturn("42");
    when(event.getTableId()).thenReturn("42");
    when(event.getTaskId()).thenReturn("42");
    when(event.getFailureTime()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act and Assert
    assertThrows(IngestTrackerException.class, () -> dynamoDBIngestJobTracker.jobFailed(event));
    verify(requestMetricCollector).isEnabled();
    verify(getTimeNow).get();
    verify(event).getFailureReasons();
    verify(event).getFailureTime();
    verify(event, atLeast(1)).getJobId();
    verify(event).getJobRunId();
    verify(event).getTableId();
    verify(event).getTaskId();
  }

  /**
   * Test {@link DynamoDBIngestJobTracker#jobFailed(IngestJobFailedEvent)}.
   * <p>
   * Method under test: {@link DynamoDBIngestJobTracker#jobFailed(IngestJobFailedEvent)}
   */
  @Test
  @DisplayName("Test jobFailed(IngestJobFailedEvent)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBIngestJobTracker.jobFailed(IngestJobFailedEvent)"})
  void testJobFailed5() {
    // Arrange
    RequestMetricCollector requestMetricCollector = mock(RequestMetricCollector.class);
    when(requestMetricCollector.isEnabled()).thenReturn(true);

    PutItemRequest request = new PutItemRequest();
    request.setRequestMetricCollector(requestMetricCollector);
    AsyncHandler<PutItemRequest, PutItemResult> asyncHandler = mock(AsyncHandler.class);
    doThrow(new RuntimeException(DynamoDBIngestJobTracker.TABLE_ID)).when(asyncHandler)
        .onError(Mockito.<Exception>any());
    AsyncHandler<PutItemRequest, PutItemResult> asyncHandler2 = mock(AsyncHandler.class);
    doThrow(new IngestTrackerException("An error occurred", new Throwable())).when(asyncHandler2)
        .onError(Mockito.<Exception>any());

    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient(new AnonymousAWSCredentials());
    dynamoDB.putItemAsync("Table Name", new HashMap<>(), "42", asyncHandler2);
    dynamoDB.putItemAsync(request, asyncHandler);
    Supplier<Instant> getTimeNow = mock(Supplier.class);
    when(getTimeNow.get()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    DynamoDBIngestJobTracker dynamoDBIngestJobTracker = new DynamoDBIngestJobTracker(dynamoDB, new InstanceProperties(),
        getTimeNow);
    IngestJobFailedEvent event = mock(IngestJobFailedEvent.class);
    when(event.getFailureReasons()).thenReturn(new ArrayList<>());
    when(event.getJobId()).thenReturn("42");
    when(event.getJobRunId()).thenReturn("42");
    when(event.getTableId()).thenReturn("42");
    when(event.getTaskId()).thenReturn("42");
    when(event.getFailureTime()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act and Assert
    assertThrows(IngestTrackerException.class, () -> dynamoDBIngestJobTracker.jobFailed(event));
    verify(asyncHandler2).onError(isA(Exception.class));
    verify(requestMetricCollector).isEnabled();
    verify(getTimeNow).get();
    verify(event).getFailureReasons();
    verify(event).getFailureTime();
    verify(event, atLeast(1)).getJobId();
    verify(event).getJobRunId();
    verify(event).getTableId();
    verify(event).getTaskId();
  }

  /**
   * Test {@link DynamoDBIngestJobTracker#jobFailed(IngestJobFailedEvent)}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@code foo}.</li>
   *   <li>Then calls {@link AsyncHandler#onError(Exception)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBIngestJobTracker#jobFailed(IngestJobFailedEvent)}
   */
  @Test
  @DisplayName("Test jobFailed(IngestJobFailedEvent); given ArrayList() add 'foo'; then calls onError(Exception)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBIngestJobTracker.jobFailed(IngestJobFailedEvent)"})
  void testJobFailed_givenArrayListAddFoo_thenCallsOnError() {
    // Arrange
    AsyncHandler<PutItemRequest, PutItemResult> asyncHandler = mock(AsyncHandler.class);
    doNothing().when(asyncHandler).onError(Mockito.<Exception>any());

    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    dynamoDB.putItemAsync(new PutItemRequest(), asyncHandler);
    Supplier<Instant> getTimeNow = mock(Supplier.class);
    when(getTimeNow.get()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    DynamoDBIngestJobTracker dynamoDBIngestJobTracker = new DynamoDBIngestJobTracker(dynamoDB, new InstanceProperties(),
        getTimeNow);

    ArrayList<String> stringList = new ArrayList<>();
    stringList.add("foo");
    IngestJobFailedEvent event = mock(IngestJobFailedEvent.class);
    when(event.getFailureReasons()).thenReturn(stringList);
    when(event.getJobId()).thenReturn("42");
    when(event.getJobRunId()).thenReturn("42");
    when(event.getTableId()).thenReturn("42");
    when(event.getTaskId()).thenReturn("42");
    when(event.getFailureTime()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act and Assert
    assertThrows(IngestTrackerException.class, () -> dynamoDBIngestJobTracker.jobFailed(event));
    verify(asyncHandler).onError(isA(Exception.class));
    verify(getTimeNow).get();
    verify(event).getFailureReasons();
    verify(event).getFailureTime();
    verify(event, atLeast(1)).getJobId();
    verify(event).getJobRunId();
    verify(event).getTableId();
    verify(event).getTaskId();
  }

  /**
   * Test {@link DynamoDBIngestJobTracker#jobFailed(IngestJobFailedEvent)}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link DynamoDBIngestJobTracker#JOB_ID}.</li>
   *   <li>Then calls {@link AsyncHandler#onError(Exception)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBIngestJobTracker#jobFailed(IngestJobFailedEvent)}
   */
  @Test
  @DisplayName("Test jobFailed(IngestJobFailedEvent); given ArrayList() add JOB_ID; then calls onError(Exception)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBIngestJobTracker.jobFailed(IngestJobFailedEvent)"})
  void testJobFailed_givenArrayListAddJob_id_thenCallsOnError() {
    // Arrange
    AsyncHandler<PutItemRequest, PutItemResult> asyncHandler = mock(AsyncHandler.class);
    doNothing().when(asyncHandler).onError(Mockito.<Exception>any());

    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    dynamoDB.putItemAsync(new PutItemRequest(), asyncHandler);
    Supplier<Instant> getTimeNow = mock(Supplier.class);
    when(getTimeNow.get()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    DynamoDBIngestJobTracker dynamoDBIngestJobTracker = new DynamoDBIngestJobTracker(dynamoDB, new InstanceProperties(),
        getTimeNow);

    ArrayList<String> stringList = new ArrayList<>();
    stringList.add(DynamoDBIngestJobTracker.JOB_ID);
    stringList.add("foo");
    IngestJobFailedEvent event = mock(IngestJobFailedEvent.class);
    when(event.getFailureReasons()).thenReturn(stringList);
    when(event.getJobId()).thenReturn("42");
    when(event.getJobRunId()).thenReturn("42");
    when(event.getTableId()).thenReturn("42");
    when(event.getTaskId()).thenReturn("42");
    when(event.getFailureTime()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act and Assert
    assertThrows(IngestTrackerException.class, () -> dynamoDBIngestJobTracker.jobFailed(event));
    verify(asyncHandler).onError(isA(Exception.class));
    verify(getTimeNow).get();
    verify(event).getFailureReasons();
    verify(event).getFailureTime();
    verify(event, atLeast(1)).getJobId();
    verify(event).getJobRunId();
    verify(event).getTableId();
    verify(event).getTaskId();
  }

  /**
   * Test {@link DynamoDBIngestJobTracker#jobFailed(IngestJobFailedEvent)}.
   * <ul>
   *   <li>Given {@link PutItemRequest#PutItemRequest()} RequestCredentials is {@link AnonymousAWSCredentials} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBIngestJobTracker#jobFailed(IngestJobFailedEvent)}
   */
  @Test
  @DisplayName("Test jobFailed(IngestJobFailedEvent); given PutItemRequest() RequestCredentials is AnonymousAWSCredentials (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBIngestJobTracker.jobFailed(IngestJobFailedEvent)"})
  void testJobFailed_givenPutItemRequestRequestCredentialsIsAnonymousAWSCredentials() {
    // Arrange
    RequestMetricCollector requestMetricCollector = mock(RequestMetricCollector.class);
    when(requestMetricCollector.isEnabled()).thenReturn(true);

    PutItemRequest request = new PutItemRequest();
    request.setRequestCredentials(new AnonymousAWSCredentials());
    request.addItemEntry("Key", new AttributeValue("foo"));
    request.setRequestMetricCollector(requestMetricCollector);
    AsyncHandler<PutItemRequest, PutItemResult> asyncHandler = mock(AsyncHandler.class);
    doThrow(new RuntimeException(DynamoDBIngestJobTracker.TABLE_ID)).when(asyncHandler)
        .onError(Mockito.<Exception>any());
    AsyncHandler<PutItemRequest, PutItemResult> asyncHandler2 = mock(AsyncHandler.class);
    doThrow(new RuntimeException(DynamoDBIngestJobTracker.TABLE_ID)).when(asyncHandler2)
        .onError(Mockito.<Exception>any());

    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    dynamoDB.putItemAsync("Table Name", new HashMap<>(), "42", asyncHandler2);
    dynamoDB.putItemAsync(request, asyncHandler);
    Supplier<Instant> getTimeNow = mock(Supplier.class);
    when(getTimeNow.get()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    DynamoDBIngestJobTracker dynamoDBIngestJobTracker = new DynamoDBIngestJobTracker(dynamoDB, new InstanceProperties(),
        getTimeNow);
    IngestJobFailedEvent event = mock(IngestJobFailedEvent.class);
    when(event.getFailureReasons()).thenReturn(new ArrayList<>());
    when(event.getJobId()).thenReturn("42");
    when(event.getJobRunId()).thenReturn("42");
    when(event.getTableId()).thenReturn("42");
    when(event.getTaskId()).thenReturn("42");
    when(event.getFailureTime()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act and Assert
    assertThrows(IngestTrackerException.class, () -> dynamoDBIngestJobTracker.jobFailed(event));
    verify(asyncHandler2).onError(isA(Exception.class));
    verify(requestMetricCollector).isEnabled();
    verify(getTimeNow).get();
    verify(event).getFailureReasons();
    verify(event).getFailureTime();
    verify(event, atLeast(1)).getJobId();
    verify(event).getJobRunId();
    verify(event).getTableId();
    verify(event).getTaskId();
  }

  /**
   * Test {@link DynamoDBIngestJobTracker#jobFailed(IngestJobFailedEvent)}.
   * <ul>
   *   <li>Given {@link PutItemRequest#PutItemRequest()} withReturnItemCollectionMetrics {@code SIZE}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBIngestJobTracker#jobFailed(IngestJobFailedEvent)}
   */
  @Test
  @DisplayName("Test jobFailed(IngestJobFailedEvent); given PutItemRequest() withReturnItemCollectionMetrics 'SIZE'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBIngestJobTracker.jobFailed(IngestJobFailedEvent)"})
  void testJobFailed_givenPutItemRequestWithReturnItemCollectionMetricsSize() {
    // Arrange
    RequestMetricCollector requestMetricCollector = mock(RequestMetricCollector.class);
    when(requestMetricCollector.isEnabled()).thenReturn(true);

    PutItemRequest request = new PutItemRequest();
    request.withReturnItemCollectionMetrics(ReturnItemCollectionMetrics.SIZE);
    request.setRequestMetricCollector(requestMetricCollector);
    AsyncHandler<PutItemRequest, PutItemResult> asyncHandler = mock(AsyncHandler.class);
    doThrow(new RuntimeException(DynamoDBIngestJobTracker.TABLE_ID)).when(asyncHandler)
        .onError(Mockito.<Exception>any());
    AsyncHandler<PutItemRequest, PutItemResult> asyncHandler2 = mock(AsyncHandler.class);
    doThrow(new RuntimeException(DynamoDBIngestJobTracker.TABLE_ID)).when(asyncHandler2)
        .onError(Mockito.<Exception>any());

    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    dynamoDB.putItemAsync("Table Name", new HashMap<>(), "42", asyncHandler2);
    dynamoDB.putItemAsync(request, asyncHandler);
    Supplier<Instant> getTimeNow = mock(Supplier.class);
    when(getTimeNow.get()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    DynamoDBIngestJobTracker dynamoDBIngestJobTracker = new DynamoDBIngestJobTracker(dynamoDB, new InstanceProperties(),
        getTimeNow);
    IngestJobFailedEvent event = mock(IngestJobFailedEvent.class);
    when(event.getFailureReasons()).thenReturn(new ArrayList<>());
    when(event.getJobId()).thenReturn("42");
    when(event.getJobRunId()).thenReturn("42");
    when(event.getTableId()).thenReturn("42");
    when(event.getTaskId()).thenReturn("42");
    when(event.getFailureTime()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act and Assert
    assertThrows(IngestTrackerException.class, () -> dynamoDBIngestJobTracker.jobFailed(event));
    verify(asyncHandler2).onError(isA(Exception.class));
    verify(requestMetricCollector).isEnabled();
    verify(getTimeNow).get();
    verify(event).getFailureReasons();
    verify(event).getFailureTime();
    verify(event, atLeast(1)).getJobId();
    verify(event).getJobRunId();
    verify(event).getTableId();
    verify(event).getTaskId();
  }

  /**
   * Test {@link DynamoDBIngestJobTracker#jobFailed(IngestJobFailedEvent)}.
   * <ul>
   *   <li>Given {@link RuntimeException#RuntimeException(String)} with {@link DynamoDBIngestJobTracker#TABLE_ID}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBIngestJobTracker#jobFailed(IngestJobFailedEvent)}
   */
  @Test
  @DisplayName("Test jobFailed(IngestJobFailedEvent); given RuntimeException(String) with TABLE_ID")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBIngestJobTracker.jobFailed(IngestJobFailedEvent)"})
  void testJobFailed_givenRuntimeExceptionWithTable_id() {
    // Arrange
    Supplier<Instant> getTimeNow = mock(Supplier.class);
    when(getTimeNow.get()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    DynamoDBIngestJobTracker dynamoDBIngestJobTracker = new DynamoDBIngestJobTracker(dynamoDB, new InstanceProperties(),
        getTimeNow);
    IngestJobFailedEvent event = mock(IngestJobFailedEvent.class);
    when(event.getFailureReasons()).thenThrow(new RuntimeException(DynamoDBIngestJobTracker.TABLE_ID));
    when(event.getJobId()).thenReturn("42");
    when(event.getJobRunId()).thenReturn("42");
    when(event.getTableId()).thenReturn("42");
    when(event.getTaskId()).thenReturn("42");
    when(event.getFailureTime()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act and Assert
    assertThrows(IngestTrackerException.class, () -> dynamoDBIngestJobTracker.jobFailed(event));
    verify(getTimeNow).get();
    verify(event).getFailureReasons();
    verify(event).getFailureTime();
    verify(event, atLeast(1)).getJobId();
    verify(event).getJobRunId();
    verify(event).getTableId();
    verify(event).getTaskId();
  }
}
