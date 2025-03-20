package sleeper.ingest.tracker.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AnonymousAWSCredentials;
import com.amazonaws.auth.STSSessionCredentials;
import com.amazonaws.handlers.AsyncHandler;
import com.amazonaws.metrics.RequestMetricCollector;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClient;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;
import com.amazonaws.services.dynamodbv2.model.PutItemResult;
import com.amazonaws.services.securitytoken.AWSSecurityTokenServiceAsyncClient;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.function.Supplier;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.tracker.ingest.task.IngestTaskStatus;
import sleeper.ingest.tracker.IngestTrackerException;

@Disabled
class DynamoDBIngestTaskTrackerDiffblueTest {
  /**
   * Test {@link DynamoDBIngestTaskTracker#taskStatusTableName(String)}.
   * <p>
   * Method under test: {@link DynamoDBIngestTaskTracker#taskStatusTableName(String)}
   */
  @Test
  @DisplayName("Test taskStatusTableName(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String DynamoDBIngestTaskTracker.taskStatusTableName(String)"})
  void testTaskStatusTableName() {
    // Arrange, Act and Assert
    assertEquals("sleeper-42-ingest-task-status", DynamoDBIngestTaskTracker.taskStatusTableName("42"));
  }

  /**
   * Test {@link DynamoDBIngestTaskTracker#taskStarted(IngestTaskStatus)}.
   * <p>
   * Method under test: {@link DynamoDBIngestTaskTracker#taskStarted(IngestTaskStatus)}
   */
  @Test
  @DisplayName("Test taskStarted(IngestTaskStatus)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBIngestTaskTracker.taskStarted(IngestTaskStatus)"})
  void testTaskStarted() {
    // Arrange
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    DynamoDBIngestTaskTracker dynamoDBIngestTaskTracker = new DynamoDBIngestTaskTracker(dynamoDB,
        new InstanceProperties());
    IngestTaskStatus taskStatus = mock(IngestTaskStatus.class);
    when(taskStatus.getTaskId()).thenReturn("42");
    when(taskStatus.getStartTime())
        .thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act and Assert
    assertThrows(IngestTrackerException.class, () -> dynamoDBIngestTaskTracker.taskStarted(taskStatus));
    verify(taskStatus).getStartTime();
    verify(taskStatus, atLeast(1)).getTaskId();
  }

  /**
   * Test {@link DynamoDBIngestTaskTracker#taskStarted(IngestTaskStatus)}.
   * <p>
   * Method under test: {@link DynamoDBIngestTaskTracker#taskStarted(IngestTaskStatus)}
   */
  @Test
  @DisplayName("Test taskStarted(IngestTaskStatus)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBIngestTaskTracker.taskStarted(IngestTaskStatus)"})
  void testTaskStarted2() {
    // Arrange
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient(new AnonymousAWSCredentials());
    DynamoDBIngestTaskTracker dynamoDBIngestTaskTracker = new DynamoDBIngestTaskTracker(dynamoDB,
        new InstanceProperties());
    IngestTaskStatus taskStatus = mock(IngestTaskStatus.class);
    when(taskStatus.getTaskId()).thenReturn("42");
    when(taskStatus.getStartTime())
        .thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act and Assert
    assertThrows(IngestTrackerException.class, () -> dynamoDBIngestTaskTracker.taskStarted(taskStatus));
    verify(taskStatus).getStartTime();
    verify(taskStatus, atLeast(1)).getTaskId();
  }

  /**
   * Test {@link DynamoDBIngestTaskTracker#taskStarted(IngestTaskStatus)}.
   * <p>
   * Method under test: {@link DynamoDBIngestTaskTracker#taskStarted(IngestTaskStatus)}
   */
  @Test
  @DisplayName("Test taskStarted(IngestTaskStatus)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBIngestTaskTracker.taskStarted(IngestTaskStatus)"})
  void testTaskStarted3() {
    // Arrange
    Supplier<Instant> getTimeNow = mock(Supplier.class);
    when(getTimeNow.get()).thenThrow(new RuntimeException(DynamoDBIngestTaskStatusFormat.STARTED));
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    DynamoDBIngestTaskTracker dynamoDBIngestTaskTracker = new DynamoDBIngestTaskTracker(dynamoDB,
        new InstanceProperties(), getTimeNow);
    IngestTaskStatus taskStatus = mock(IngestTaskStatus.class);
    when(taskStatus.getTaskId()).thenReturn("42");

    // Act and Assert
    assertThrows(IngestTrackerException.class, () -> dynamoDBIngestTaskTracker.taskStarted(taskStatus));
    verify(getTimeNow).get();
    verify(taskStatus).getTaskId();
  }

  /**
   * Test {@link DynamoDBIngestTaskTracker#taskStarted(IngestTaskStatus)}.
   * <p>
   * Method under test: {@link DynamoDBIngestTaskTracker#taskStarted(IngestTaskStatus)}
   */
  @Test
  @DisplayName("Test taskStarted(IngestTaskStatus)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBIngestTaskTracker.taskStarted(IngestTaskStatus)"})
  void testTaskStarted4() {
    // Arrange
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient(new AnonymousAWSCredentials());
    dynamoDB.putItemAsync(new PutItemRequest(), mock(AsyncHandler.class));
    Supplier<Instant> getTimeNow = mock(Supplier.class);
    when(getTimeNow.get()).thenThrow(new RuntimeException(DynamoDBIngestTaskStatusFormat.STARTED));
    DynamoDBIngestTaskTracker dynamoDBIngestTaskTracker = new DynamoDBIngestTaskTracker(dynamoDB,
        new InstanceProperties(), getTimeNow);
    IngestTaskStatus taskStatus = mock(IngestTaskStatus.class);
    when(taskStatus.getTaskId()).thenReturn("42");

    // Act and Assert
    assertThrows(IngestTrackerException.class, () -> dynamoDBIngestTaskTracker.taskStarted(taskStatus));
    verify(getTimeNow).get();
    verify(taskStatus).getTaskId();
  }

  /**
   * Test {@link DynamoDBIngestTaskTracker#taskStarted(IngestTaskStatus)}.
   * <p>
   * Method under test: {@link DynamoDBIngestTaskTracker#taskStarted(IngestTaskStatus)}
   */
  @Test
  @DisplayName("Test taskStarted(IngestTaskStatus)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBIngestTaskTracker.taskStarted(IngestTaskStatus)"})
  void testTaskStarted5() {
    // Arrange
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient((AWSCredentials) null);
    dynamoDB.putItemAsync(new PutItemRequest(), mock(AsyncHandler.class));
    Supplier<Instant> getTimeNow = mock(Supplier.class);
    when(getTimeNow.get()).thenThrow(new RuntimeException(DynamoDBIngestTaskStatusFormat.STARTED));
    DynamoDBIngestTaskTracker dynamoDBIngestTaskTracker = new DynamoDBIngestTaskTracker(dynamoDB,
        new InstanceProperties(), getTimeNow);
    IngestTaskStatus taskStatus = mock(IngestTaskStatus.class);
    when(taskStatus.getTaskId()).thenReturn("42");

    // Act and Assert
    assertThrows(IngestTrackerException.class, () -> dynamoDBIngestTaskTracker.taskStarted(taskStatus));
    verify(getTimeNow).get();
    verify(taskStatus).getTaskId();
  }

  /**
   * Test {@link DynamoDBIngestTaskTracker#taskStarted(IngestTaskStatus)}.
   * <p>
   * Method under test: {@link DynamoDBIngestTaskTracker#taskStarted(IngestTaskStatus)}
   */
  @Test
  @DisplayName("Test taskStarted(IngestTaskStatus)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBIngestTaskTracker.taskStarted(IngestTaskStatus)"})
  void testTaskStarted6() {
    // Arrange
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient(
        new STSSessionCredentials(new AnonymousAWSCredentials(), 1));
    dynamoDB.putItemAsync(new PutItemRequest(), mock(AsyncHandler.class));
    Supplier<Instant> getTimeNow = mock(Supplier.class);
    when(getTimeNow.get()).thenThrow(new RuntimeException(DynamoDBIngestTaskStatusFormat.STARTED));
    DynamoDBIngestTaskTracker dynamoDBIngestTaskTracker = new DynamoDBIngestTaskTracker(dynamoDB,
        new InstanceProperties(), getTimeNow);
    IngestTaskStatus taskStatus = mock(IngestTaskStatus.class);
    when(taskStatus.getTaskId()).thenReturn("42");

    // Act and Assert
    assertThrows(IngestTrackerException.class, () -> dynamoDBIngestTaskTracker.taskStarted(taskStatus));
    verify(getTimeNow).get();
    verify(taskStatus).getTaskId();
  }

  /**
   * Test {@link DynamoDBIngestTaskTracker#taskStarted(IngestTaskStatus)}.
   * <p>
   * Method under test: {@link DynamoDBIngestTaskTracker#taskStarted(IngestTaskStatus)}
   */
  @Test
  @DisplayName("Test taskStarted(IngestTaskStatus)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBIngestTaskTracker.taskStarted(IngestTaskStatus)"})
  void testTaskStarted7() {
    // Arrange
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient(
        new STSSessionCredentials(new AWSSecurityTokenServiceAsyncClient(), 1));
    dynamoDB.putItemAsync(new PutItemRequest(), mock(AsyncHandler.class));
    Supplier<Instant> getTimeNow = mock(Supplier.class);
    when(getTimeNow.get()).thenThrow(new RuntimeException(DynamoDBIngestTaskStatusFormat.STARTED));
    DynamoDBIngestTaskTracker dynamoDBIngestTaskTracker = new DynamoDBIngestTaskTracker(dynamoDB,
        new InstanceProperties(), getTimeNow);
    IngestTaskStatus taskStatus = mock(IngestTaskStatus.class);
    when(taskStatus.getTaskId()).thenReturn("42");

    // Act and Assert
    assertThrows(IngestTrackerException.class, () -> dynamoDBIngestTaskTracker.taskStarted(taskStatus));
    verify(getTimeNow).get();
    verify(taskStatus).getTaskId();
  }

  /**
   * Test {@link DynamoDBIngestTaskTracker#taskStarted(IngestTaskStatus)}.
   * <p>
   * Method under test: {@link DynamoDBIngestTaskTracker#taskStarted(IngestTaskStatus)}
   */
  @Test
  @DisplayName("Test taskStarted(IngestTaskStatus)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBIngestTaskTracker.taskStarted(IngestTaskStatus)"})
  void testTaskStarted8() {
    // Arrange
    PutItemRequest request = new PutItemRequest();
    request.putCustomQueryParameter("Name", DynamoDBIngestTaskStatusFormat.STARTED);

    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient((AWSCredentials) null);
    dynamoDB.putItemAsync(request, mock(AsyncHandler.class));
    Supplier<Instant> getTimeNow = mock(Supplier.class);
    when(getTimeNow.get()).thenThrow(new RuntimeException(DynamoDBIngestTaskStatusFormat.STARTED));
    DynamoDBIngestTaskTracker dynamoDBIngestTaskTracker = new DynamoDBIngestTaskTracker(dynamoDB,
        new InstanceProperties(), getTimeNow);
    IngestTaskStatus taskStatus = mock(IngestTaskStatus.class);
    when(taskStatus.getTaskId()).thenReturn("42");

    // Act and Assert
    assertThrows(IngestTrackerException.class, () -> dynamoDBIngestTaskTracker.taskStarted(taskStatus));
    verify(getTimeNow).get();
    verify(taskStatus).getTaskId();
  }

  /**
   * Test {@link DynamoDBIngestTaskTracker#taskStarted(IngestTaskStatus)}.
   * <p>
   * Method under test: {@link DynamoDBIngestTaskTracker#taskStarted(IngestTaskStatus)}
   */
  @Test
  @DisplayName("Test taskStarted(IngestTaskStatus)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBIngestTaskTracker.taskStarted(IngestTaskStatus)"})
  void testTaskStarted9() {
    // Arrange
    RequestMetricCollector requestMetricCollector = mock(RequestMetricCollector.class);
    when(requestMetricCollector.isEnabled()).thenReturn(true);

    PutItemRequest request = new PutItemRequest();
    request.setRequestMetricCollector(requestMetricCollector);
    request.putCustomQueryParameter("Name", DynamoDBIngestTaskStatusFormat.STARTED);

    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient(new AnonymousAWSCredentials());
    dynamoDB.putItemAsync(request, mock(AsyncHandler.class));
    Supplier<Instant> getTimeNow = mock(Supplier.class);
    when(getTimeNow.get()).thenThrow(new RuntimeException(DynamoDBIngestTaskStatusFormat.STARTED));
    DynamoDBIngestTaskTracker dynamoDBIngestTaskTracker = new DynamoDBIngestTaskTracker(dynamoDB,
        new InstanceProperties(), getTimeNow);
    IngestTaskStatus taskStatus = mock(IngestTaskStatus.class);
    when(taskStatus.getTaskId()).thenReturn("42");

    // Act and Assert
    assertThrows(IngestTrackerException.class, () -> dynamoDBIngestTaskTracker.taskStarted(taskStatus));
    verify(requestMetricCollector).isEnabled();
    verify(getTimeNow).get();
    verify(taskStatus).getTaskId();
  }

  /**
   * Test {@link DynamoDBIngestTaskTracker#taskStarted(IngestTaskStatus)}.
   * <p>
   * Method under test: {@link DynamoDBIngestTaskTracker#taskStarted(IngestTaskStatus)}
   */
  @Test
  @DisplayName("Test taskStarted(IngestTaskStatus)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBIngestTaskTracker.taskStarted(IngestTaskStatus)"})
  void testTaskStarted10() {
    // Arrange
    RequestMetricCollector requestMetricCollector = mock(RequestMetricCollector.class);
    when(requestMetricCollector.isEnabled()).thenReturn(true);

    PutItemRequest request = new PutItemRequest();
    request.setRequestMetricCollector(requestMetricCollector);
    request.putCustomQueryParameter("Name", DynamoDBIngestTaskStatusFormat.STARTED);

    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient(
        new STSSessionCredentials(new AnonymousAWSCredentials(), 1));
    dynamoDB.putItemAsync(request, mock(AsyncHandler.class));
    Supplier<Instant> getTimeNow = mock(Supplier.class);
    when(getTimeNow.get()).thenThrow(new RuntimeException(DynamoDBIngestTaskStatusFormat.STARTED));
    DynamoDBIngestTaskTracker dynamoDBIngestTaskTracker = new DynamoDBIngestTaskTracker(dynamoDB,
        new InstanceProperties(), getTimeNow);
    IngestTaskStatus taskStatus = mock(IngestTaskStatus.class);
    when(taskStatus.getTaskId()).thenReturn("42");

    // Act and Assert
    assertThrows(IngestTrackerException.class, () -> dynamoDBIngestTaskTracker.taskStarted(taskStatus));
    verify(requestMetricCollector).isEnabled();
    verify(getTimeNow).get();
    verify(taskStatus).getTaskId();
  }

  /**
   * Test {@link DynamoDBIngestTaskTracker#taskStarted(IngestTaskStatus)}.
   * <p>
   * Method under test: {@link DynamoDBIngestTaskTracker#taskStarted(IngestTaskStatus)}
   */
  @Test
  @DisplayName("Test taskStarted(IngestTaskStatus)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBIngestTaskTracker.taskStarted(IngestTaskStatus)"})
  void testTaskStarted11() {
    // Arrange
    RequestMetricCollector requestMetricCollector = mock(RequestMetricCollector.class);
    when(requestMetricCollector.isEnabled()).thenReturn(true);

    PutItemRequest request = new PutItemRequest();
    request.setRequestMetricCollector(requestMetricCollector);
    request.putCustomQueryParameter("Name", DynamoDBIngestTaskStatusFormat.STARTED);

    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient(
        new STSSessionCredentials(new AWSSecurityTokenServiceAsyncClient(), 1));
    dynamoDB.putItemAsync(request, mock(AsyncHandler.class));
    Supplier<Instant> getTimeNow = mock(Supplier.class);
    when(getTimeNow.get()).thenThrow(new RuntimeException(DynamoDBIngestTaskStatusFormat.STARTED));
    DynamoDBIngestTaskTracker dynamoDBIngestTaskTracker = new DynamoDBIngestTaskTracker(dynamoDB,
        new InstanceProperties(), getTimeNow);
    IngestTaskStatus taskStatus = mock(IngestTaskStatus.class);
    when(taskStatus.getTaskId()).thenReturn("42");

    // Act and Assert
    assertThrows(IngestTrackerException.class, () -> dynamoDBIngestTaskTracker.taskStarted(taskStatus));
    verify(requestMetricCollector).isEnabled();
    verify(getTimeNow).get();
    verify(taskStatus).getTaskId();
  }

  /**
   * Test {@link DynamoDBIngestTaskTracker#taskStarted(IngestTaskStatus)}.
   * <p>
   * Method under test: {@link DynamoDBIngestTaskTracker#taskStarted(IngestTaskStatus)}
   */
  @Test
  @DisplayName("Test taskStarted(IngestTaskStatus)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBIngestTaskTracker.taskStarted(IngestTaskStatus)"})
  void testTaskStarted12() {
    // Arrange
    RequestMetricCollector requestMetricCollector = mock(RequestMetricCollector.class);
    when(requestMetricCollector.isEnabled()).thenReturn(true);

    PutItemRequest request = new PutItemRequest();
    request.addExpressionAttributeValuesEntry(DynamoDBIngestTaskStatusFormat.STARTED,
        new AttributeValue(DynamoDBIngestTaskStatusFormat.STARTED));
    request.setRequestMetricCollector(requestMetricCollector);
    request.putCustomQueryParameter("Name", DynamoDBIngestTaskStatusFormat.STARTED);

    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient((AWSCredentials) null);
    dynamoDB.putItemAsync(request, mock(AsyncHandler.class));
    Supplier<Instant> getTimeNow = mock(Supplier.class);
    when(getTimeNow.get()).thenThrow(new RuntimeException(DynamoDBIngestTaskStatusFormat.STARTED));
    DynamoDBIngestTaskTracker dynamoDBIngestTaskTracker = new DynamoDBIngestTaskTracker(dynamoDB,
        new InstanceProperties(), getTimeNow);
    IngestTaskStatus taskStatus = mock(IngestTaskStatus.class);
    when(taskStatus.getTaskId()).thenReturn("42");

    // Act and Assert
    assertThrows(IngestTrackerException.class, () -> dynamoDBIngestTaskTracker.taskStarted(taskStatus));
    verify(requestMetricCollector).isEnabled();
    verify(getTimeNow).get();
    verify(taskStatus).getTaskId();
  }

  /**
   * Test {@link DynamoDBIngestTaskTracker#taskStarted(IngestTaskStatus)}.
   * <p>
   * Method under test: {@link DynamoDBIngestTaskTracker#taskStarted(IngestTaskStatus)}
   */
  @Test
  @DisplayName("Test taskStarted(IngestTaskStatus)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBIngestTaskTracker.taskStarted(IngestTaskStatus)"})
  void testTaskStarted13() {
    // Arrange
    RequestMetricCollector requestMetricCollector = mock(RequestMetricCollector.class);
    when(requestMetricCollector.isEnabled()).thenReturn(true);

    PutItemRequest request = new PutItemRequest();
    request.setRequestMetricCollector(requestMetricCollector);
    request.putCustomQueryParameter("Name", DynamoDBIngestTaskStatusFormat.STARTED);

    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient((AWSCredentials) null);
    dynamoDB.setTimeOffset(1);
    dynamoDB.putItemAsync(request, mock(AsyncHandler.class));
    Supplier<Instant> getTimeNow = mock(Supplier.class);
    when(getTimeNow.get()).thenThrow(new RuntimeException(DynamoDBIngestTaskStatusFormat.STARTED));
    DynamoDBIngestTaskTracker dynamoDBIngestTaskTracker = new DynamoDBIngestTaskTracker(dynamoDB,
        new InstanceProperties(), getTimeNow);
    IngestTaskStatus taskStatus = mock(IngestTaskStatus.class);
    when(taskStatus.getTaskId()).thenReturn("42");

    // Act and Assert
    assertThrows(IngestTrackerException.class, () -> dynamoDBIngestTaskTracker.taskStarted(taskStatus));
    verify(requestMetricCollector).isEnabled();
    verify(getTimeNow).get();
    verify(taskStatus).getTaskId();
  }

  /**
   * Test {@link DynamoDBIngestTaskTracker#taskStarted(IngestTaskStatus)}.
   * <p>
   * Method under test: {@link DynamoDBIngestTaskTracker#taskStarted(IngestTaskStatus)}
   */
  @Test
  @DisplayName("Test taskStarted(IngestTaskStatus)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBIngestTaskTracker.taskStarted(IngestTaskStatus)"})
  void testTaskStarted14() {
    // Arrange
    RequestMetricCollector requestMetricCollector = mock(RequestMetricCollector.class);
    when(requestMetricCollector.isEnabled()).thenReturn(true);

    PutItemRequest request = new PutItemRequest();
    request.addExpressionAttributeValuesEntry("Key", new AttributeValue(DynamoDBIngestTaskStatusFormat.STARTED));
    request.setRequestMetricCollector(requestMetricCollector);
    request.putCustomQueryParameter("Name", DynamoDBIngestTaskStatusFormat.STARTED);

    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient(new AnonymousAWSCredentials());
    dynamoDB.putItemAsync(request, mock(AsyncHandler.class));
    Supplier<Instant> getTimeNow = mock(Supplier.class);
    when(getTimeNow.get()).thenThrow(new RuntimeException(DynamoDBIngestTaskStatusFormat.STARTED));
    DynamoDBIngestTaskTracker dynamoDBIngestTaskTracker = new DynamoDBIngestTaskTracker(dynamoDB,
        new InstanceProperties(), getTimeNow);
    IngestTaskStatus taskStatus = mock(IngestTaskStatus.class);
    when(taskStatus.getTaskId()).thenReturn("42");

    // Act and Assert
    assertThrows(IngestTrackerException.class, () -> dynamoDBIngestTaskTracker.taskStarted(taskStatus));
    verify(requestMetricCollector).isEnabled();
    verify(getTimeNow).get();
    verify(taskStatus).getTaskId();
  }

  /**
   * Test {@link DynamoDBIngestTaskTracker#taskStarted(IngestTaskStatus)}.
   * <ul>
   *   <li>Given {@link AsyncHandler} {@link AsyncHandler#onError(Exception)} does nothing.</li>
   *   <li>Then calls {@link AsyncHandler#onError(Exception)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBIngestTaskTracker#taskStarted(IngestTaskStatus)}
   */
  @Test
  @DisplayName("Test taskStarted(IngestTaskStatus); given AsyncHandler onError(Exception) does nothing; then calls onError(Exception)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBIngestTaskTracker.taskStarted(IngestTaskStatus)"})
  void testTaskStarted_givenAsyncHandlerOnErrorDoesNothing_thenCallsOnError() {
    // Arrange
    AsyncHandler<PutItemRequest, PutItemResult> asyncHandler = mock(AsyncHandler.class);
    doNothing().when(asyncHandler).onError(Mockito.<Exception>any());

    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    dynamoDB.putItemAsync(new PutItemRequest(), asyncHandler);
    DynamoDBIngestTaskTracker dynamoDBIngestTaskTracker = new DynamoDBIngestTaskTracker(dynamoDB,
        new InstanceProperties());
    IngestTaskStatus taskStatus = mock(IngestTaskStatus.class);
    when(taskStatus.getTaskId()).thenReturn("42");
    when(taskStatus.getStartTime())
        .thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act and Assert
    assertThrows(IngestTrackerException.class, () -> dynamoDBIngestTaskTracker.taskStarted(taskStatus));
    verify(asyncHandler).onError(isA(Exception.class));
    verify(taskStatus).getStartTime();
    verify(taskStatus, atLeast(1)).getTaskId();
  }

  /**
   * Test {@link DynamoDBIngestTaskTracker#taskStarted(IngestTaskStatus)}.
   * <ul>
   *   <li>Given {@link PutItemRequest#PutItemRequest()} ConditionalOperator is {@link DynamoDBIngestTaskStatusFormat#STARTED}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBIngestTaskTracker#taskStarted(IngestTaskStatus)}
   */
  @Test
  @DisplayName("Test taskStarted(IngestTaskStatus); given PutItemRequest() ConditionalOperator is STARTED")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBIngestTaskTracker.taskStarted(IngestTaskStatus)"})
  void testTaskStarted_givenPutItemRequestConditionalOperatorIsStarted() {
    // Arrange
    RequestMetricCollector requestMetricCollector = mock(RequestMetricCollector.class);
    when(requestMetricCollector.isEnabled()).thenReturn(true);

    PutItemRequest request = new PutItemRequest();
    request.setConditionalOperator(DynamoDBIngestTaskStatusFormat.STARTED);
    request.setRequestMetricCollector(requestMetricCollector);
    request.putCustomQueryParameter("Name", DynamoDBIngestTaskStatusFormat.STARTED);

    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient((AWSCredentials) null);
    dynamoDB.putItemAsync(request, mock(AsyncHandler.class));
    Supplier<Instant> getTimeNow = mock(Supplier.class);
    when(getTimeNow.get()).thenThrow(new RuntimeException(DynamoDBIngestTaskStatusFormat.STARTED));
    DynamoDBIngestTaskTracker dynamoDBIngestTaskTracker = new DynamoDBIngestTaskTracker(dynamoDB,
        new InstanceProperties(), getTimeNow);
    IngestTaskStatus taskStatus = mock(IngestTaskStatus.class);
    when(taskStatus.getTaskId()).thenReturn("42");

    // Act and Assert
    assertThrows(IngestTrackerException.class, () -> dynamoDBIngestTaskTracker.taskStarted(taskStatus));
    verify(requestMetricCollector).isEnabled();
    verify(getTimeNow).get();
    verify(taskStatus).getTaskId();
  }

  /**
   * Test {@link DynamoDBIngestTaskTracker#taskStarted(IngestTaskStatus)}.
   * <ul>
   *   <li>Given {@link PutItemRequest#PutItemRequest()} TableName is {@link DynamoDBIngestTaskStatusFormat#STARTED}.</li>
   *   <li>Then calls {@link RequestMetricCollector#isEnabled()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBIngestTaskTracker#taskStarted(IngestTaskStatus)}
   */
  @Test
  @DisplayName("Test taskStarted(IngestTaskStatus); given PutItemRequest() TableName is STARTED; then calls isEnabled()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBIngestTaskTracker.taskStarted(IngestTaskStatus)"})
  void testTaskStarted_givenPutItemRequestTableNameIsStarted_thenCallsIsEnabled() {
    // Arrange
    RequestMetricCollector requestMetricCollector = mock(RequestMetricCollector.class);
    when(requestMetricCollector.isEnabled()).thenReturn(true);

    PutItemRequest request = new PutItemRequest();
    request.setTableName(DynamoDBIngestTaskStatusFormat.STARTED);
    request.addExpressionAttributeValuesEntry("Key", new AttributeValue(DynamoDBIngestTaskStatusFormat.STARTED));
    request.setRequestMetricCollector(requestMetricCollector);
    request.putCustomQueryParameter("Name", DynamoDBIngestTaskStatusFormat.STARTED);

    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient(new AnonymousAWSCredentials());
    dynamoDB.putItemAsync(request, mock(AsyncHandler.class));
    Supplier<Instant> getTimeNow = mock(Supplier.class);
    when(getTimeNow.get()).thenThrow(new RuntimeException(DynamoDBIngestTaskStatusFormat.STARTED));
    DynamoDBIngestTaskTracker dynamoDBIngestTaskTracker = new DynamoDBIngestTaskTracker(dynamoDB,
        new InstanceProperties(), getTimeNow);
    IngestTaskStatus taskStatus = mock(IngestTaskStatus.class);
    when(taskStatus.getTaskId()).thenReturn("42");

    // Act and Assert
    assertThrows(IngestTrackerException.class, () -> dynamoDBIngestTaskTracker.taskStarted(taskStatus));
    verify(requestMetricCollector).isEnabled();
    verify(getTimeNow).get();
    verify(taskStatus).getTaskId();
  }

  /**
   * Test {@link DynamoDBIngestTaskTracker#taskFinished(IngestTaskStatus)}.
   * <p>
   * Method under test: {@link DynamoDBIngestTaskTracker#taskFinished(IngestTaskStatus)}
   */
  @Test
  @DisplayName("Test taskFinished(IngestTaskStatus)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBIngestTaskTracker.taskFinished(IngestTaskStatus)"})
  void testTaskFinished() {
    // Arrange
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    dynamoDB.putItemAsync(new PutItemRequest(), mock(AsyncHandler.class));
    DynamoDBIngestTaskTracker dynamoDBIngestTaskTracker = new DynamoDBIngestTaskTracker(dynamoDB,
        new InstanceProperties());
    IngestTaskStatus taskStatus = mock(IngestTaskStatus.class);
    when(taskStatus.getFinishedStatus()).thenThrow(new RuntimeException(DynamoDBIngestTaskStatusFormat.FINISHED));
    when(taskStatus.getTaskId()).thenReturn("42");
    when(taskStatus.getStartTime())
        .thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act and Assert
    assertThrows(IngestTrackerException.class, () -> dynamoDBIngestTaskTracker.taskFinished(taskStatus));
    verify(taskStatus).getFinishedStatus();
    verify(taskStatus).getStartTime();
    verify(taskStatus, atLeast(1)).getTaskId();
  }

  /**
   * Test {@link DynamoDBIngestTaskTracker#taskFinished(IngestTaskStatus)}.
   * <p>
   * Method under test: {@link DynamoDBIngestTaskTracker#taskFinished(IngestTaskStatus)}
   */
  @Test
  @DisplayName("Test taskFinished(IngestTaskStatus)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBIngestTaskTracker.taskFinished(IngestTaskStatus)"})
  void testTaskFinished2() {
    // Arrange
    Supplier<Instant> getTimeNow = mock(Supplier.class);
    when(getTimeNow.get()).thenThrow(new RuntimeException(DynamoDBIngestTaskStatusFormat.FINISHED));
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    DynamoDBIngestTaskTracker dynamoDBIngestTaskTracker = new DynamoDBIngestTaskTracker(dynamoDB,
        new InstanceProperties(), getTimeNow);
    IngestTaskStatus taskStatus = mock(IngestTaskStatus.class);
    when(taskStatus.getTaskId()).thenReturn("42");

    // Act and Assert
    assertThrows(IngestTrackerException.class, () -> dynamoDBIngestTaskTracker.taskFinished(taskStatus));
    verify(getTimeNow).get();
    verify(taskStatus).getTaskId();
  }

  /**
   * Test {@link DynamoDBIngestTaskTracker#taskFinished(IngestTaskStatus)}.
   * <p>
   * Method under test: {@link DynamoDBIngestTaskTracker#taskFinished(IngestTaskStatus)}
   */
  @Test
  @DisplayName("Test taskFinished(IngestTaskStatus)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBIngestTaskTracker.taskFinished(IngestTaskStatus)"})
  void testTaskFinished3() {
    // Arrange
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient(new AnonymousAWSCredentials());
    dynamoDB.putItemAsync("Table Name", new HashMap<>(), mock(AsyncHandler.class));
    Supplier<Instant> getTimeNow = mock(Supplier.class);
    when(getTimeNow.get()).thenThrow(new RuntimeException(DynamoDBIngestTaskStatusFormat.FINISHED));
    DynamoDBIngestTaskTracker dynamoDBIngestTaskTracker = new DynamoDBIngestTaskTracker(dynamoDB,
        new InstanceProperties(), getTimeNow);
    IngestTaskStatus taskStatus = mock(IngestTaskStatus.class);
    when(taskStatus.getTaskId()).thenReturn("42");

    // Act and Assert
    assertThrows(IngestTrackerException.class, () -> dynamoDBIngestTaskTracker.taskFinished(taskStatus));
    verify(getTimeNow).get();
    verify(taskStatus).getTaskId();
  }

  /**
   * Test {@link DynamoDBIngestTaskTracker#taskFinished(IngestTaskStatus)}.
   * <p>
   * Method under test: {@link DynamoDBIngestTaskTracker#taskFinished(IngestTaskStatus)}
   */
  @Test
  @DisplayName("Test taskFinished(IngestTaskStatus)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBIngestTaskTracker.taskFinished(IngestTaskStatus)"})
  void testTaskFinished4() {
    // Arrange
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient(
        new STSSessionCredentials(new AnonymousAWSCredentials(), 1));
    dynamoDB.putItemAsync("Table Name", new HashMap<>(), mock(AsyncHandler.class));
    Supplier<Instant> getTimeNow = mock(Supplier.class);
    when(getTimeNow.get()).thenThrow(new RuntimeException(DynamoDBIngestTaskStatusFormat.FINISHED));
    DynamoDBIngestTaskTracker dynamoDBIngestTaskTracker = new DynamoDBIngestTaskTracker(dynamoDB,
        new InstanceProperties(), getTimeNow);
    IngestTaskStatus taskStatus = mock(IngestTaskStatus.class);
    when(taskStatus.getTaskId()).thenReturn("42");

    // Act and Assert
    assertThrows(IngestTrackerException.class, () -> dynamoDBIngestTaskTracker.taskFinished(taskStatus));
    verify(getTimeNow).get();
    verify(taskStatus).getTaskId();
  }

  /**
   * Test {@link DynamoDBIngestTaskTracker#taskFinished(IngestTaskStatus)}.
   * <p>
   * Method under test: {@link DynamoDBIngestTaskTracker#taskFinished(IngestTaskStatus)}
   */
  @Test
  @DisplayName("Test taskFinished(IngestTaskStatus)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBIngestTaskTracker.taskFinished(IngestTaskStatus)"})
  void testTaskFinished5() {
    // Arrange
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient(
        new STSSessionCredentials((AWSCredentials) null, 1));
    dynamoDB.putItemAsync("Table Name", new HashMap<>(), mock(AsyncHandler.class));
    Supplier<Instant> getTimeNow = mock(Supplier.class);
    when(getTimeNow.get()).thenThrow(new RuntimeException(DynamoDBIngestTaskStatusFormat.FINISHED));
    DynamoDBIngestTaskTracker dynamoDBIngestTaskTracker = new DynamoDBIngestTaskTracker(dynamoDB,
        new InstanceProperties(), getTimeNow);
    IngestTaskStatus taskStatus = mock(IngestTaskStatus.class);
    when(taskStatus.getTaskId()).thenReturn("42");

    // Act and Assert
    assertThrows(IngestTrackerException.class, () -> dynamoDBIngestTaskTracker.taskFinished(taskStatus));
    verify(getTimeNow).get();
    verify(taskStatus).getTaskId();
  }

  /**
   * Test {@link DynamoDBIngestTaskTracker#taskFinished(IngestTaskStatus)}.
   * <p>
   * Method under test: {@link DynamoDBIngestTaskTracker#taskFinished(IngestTaskStatus)}
   */
  @Test
  @DisplayName("Test taskFinished(IngestTaskStatus)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBIngestTaskTracker.taskFinished(IngestTaskStatus)"})
  void testTaskFinished6() {
    // Arrange
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient(
        new STSSessionCredentials((AWSCredentials) null, 1));
    dynamoDB.putItemAsync("Table Name", new HashMap<>(), mock(AsyncHandler.class));
    Supplier<Instant> getTimeNow = mock(Supplier.class);
    when(getTimeNow.get()).thenThrow(new RuntimeException(DynamoDBIngestTaskStatusFormat.FINISHED));
    DynamoDBIngestTaskTracker dynamoDBIngestTaskTracker = new DynamoDBIngestTaskTracker(dynamoDB,
        new InstanceProperties(), getTimeNow);
    IngestTaskStatus taskStatus = mock(IngestTaskStatus.class);
    when(taskStatus.getTaskId()).thenReturn("42");

    // Act and Assert
    assertThrows(IngestTrackerException.class, () -> dynamoDBIngestTaskTracker.taskFinished(taskStatus));
    verify(getTimeNow).get();
    verify(taskStatus).getTaskId();
  }

  /**
   * Test {@link DynamoDBIngestTaskTracker#taskFinished(IngestTaskStatus)}.
   * <p>
   * Method under test: {@link DynamoDBIngestTaskTracker#taskFinished(IngestTaskStatus)}
   */
  @Test
  @DisplayName("Test taskFinished(IngestTaskStatus)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBIngestTaskTracker.taskFinished(IngestTaskStatus)"})
  void testTaskFinished7() {
    // Arrange
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient(
        new STSSessionCredentials((AWSCredentials) null, 1));
    dynamoDB.putItemAsync("Table Name", new HashMap<>(), mock(AsyncHandler.class));
    Supplier<Instant> getTimeNow = mock(Supplier.class);
    when(getTimeNow.get()).thenThrow(new RuntimeException(DynamoDBIngestTaskStatusFormat.FINISHED));
    DynamoDBIngestTaskTracker dynamoDBIngestTaskTracker = new DynamoDBIngestTaskTracker(dynamoDB,
        new InstanceProperties(), getTimeNow);
    IngestTaskStatus taskStatus = mock(IngestTaskStatus.class);
    when(taskStatus.getTaskId()).thenReturn("42");

    // Act and Assert
    assertThrows(IngestTrackerException.class, () -> dynamoDBIngestTaskTracker.taskFinished(taskStatus));
    verify(getTimeNow).get();
    verify(taskStatus).getTaskId();
  }

  /**
   * Test {@link DynamoDBIngestTaskTracker#taskFinished(IngestTaskStatus)}.
   * <p>
   * Method under test: {@link DynamoDBIngestTaskTracker#taskFinished(IngestTaskStatus)}
   */
  @Test
  @DisplayName("Test taskFinished(IngestTaskStatus)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBIngestTaskTracker.taskFinished(IngestTaskStatus)"})
  void testTaskFinished8() {
    // Arrange
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient(
        new STSSessionCredentials(new AnonymousAWSCredentials(), Integer.MIN_VALUE));
    dynamoDB.putItemAsync("Table Name", new HashMap<>(), mock(AsyncHandler.class));
    Supplier<Instant> getTimeNow = mock(Supplier.class);
    when(getTimeNow.get()).thenThrow(new RuntimeException(DynamoDBIngestTaskStatusFormat.FINISHED));
    DynamoDBIngestTaskTracker dynamoDBIngestTaskTracker = new DynamoDBIngestTaskTracker(dynamoDB,
        new InstanceProperties(), getTimeNow);
    IngestTaskStatus taskStatus = mock(IngestTaskStatus.class);
    when(taskStatus.getTaskId()).thenReturn("42");

    // Act and Assert
    assertThrows(IngestTrackerException.class, () -> dynamoDBIngestTaskTracker.taskFinished(taskStatus));
    verify(getTimeNow).get();
    verify(taskStatus).getTaskId();
  }

  /**
   * Test {@link DynamoDBIngestTaskTracker#taskFinished(IngestTaskStatus)}.
   * <p>
   * Method under test: {@link DynamoDBIngestTaskTracker#taskFinished(IngestTaskStatus)}
   */
  @Test
  @DisplayName("Test taskFinished(IngestTaskStatus)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBIngestTaskTracker.taskFinished(IngestTaskStatus)"})
  void testTaskFinished9() {
    // Arrange
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient(
        new STSSessionCredentials(new AnonymousAWSCredentials(), 1));
    dynamoDB.putItemAsync(DynamoDBIngestTaskStatusFormat.FINISHED, new HashMap<>(), mock(AsyncHandler.class));
    Supplier<Instant> getTimeNow = mock(Supplier.class);
    when(getTimeNow.get()).thenThrow(new RuntimeException(DynamoDBIngestTaskStatusFormat.FINISHED));
    DynamoDBIngestTaskTracker dynamoDBIngestTaskTracker = new DynamoDBIngestTaskTracker(dynamoDB,
        new InstanceProperties(), getTimeNow);
    IngestTaskStatus taskStatus = mock(IngestTaskStatus.class);
    when(taskStatus.getTaskId()).thenReturn("42");

    // Act and Assert
    assertThrows(IngestTrackerException.class, () -> dynamoDBIngestTaskTracker.taskFinished(taskStatus));
    verify(getTimeNow).get();
    verify(taskStatus).getTaskId();
  }

  /**
   * Test {@link DynamoDBIngestTaskTracker#taskFinished(IngestTaskStatus)}.
   * <p>
   * Method under test: {@link DynamoDBIngestTaskTracker#taskFinished(IngestTaskStatus)}
   */
  @Test
  @DisplayName("Test taskFinished(IngestTaskStatus)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBIngestTaskTracker.taskFinished(IngestTaskStatus)"})
  void testTaskFinished10() {
    // Arrange
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient(
        new STSSessionCredentials(new AnonymousAWSCredentials(), 1));
    dynamoDB.putItemAsync("Table Name", new HashMap<>(), mock(AsyncHandler.class));
    Supplier<Instant> getTimeNow = mock(Supplier.class);
    when(getTimeNow.get()).thenThrow(new RuntimeException(DynamoDBIngestTaskStatusFormat.FINISHED));
    DynamoDBIngestTaskTracker dynamoDBIngestTaskTracker = new DynamoDBIngestTaskTracker(dynamoDB,
        new InstanceProperties(), getTimeNow);
    IngestTaskStatus taskStatus = mock(IngestTaskStatus.class);
    when(taskStatus.getTaskId()).thenReturn("42");

    // Act and Assert
    assertThrows(IngestTrackerException.class, () -> dynamoDBIngestTaskTracker.taskFinished(taskStatus));
    verify(getTimeNow).get();
    verify(taskStatus).getTaskId();
  }

  /**
   * Test {@link DynamoDBIngestTaskTracker#taskFinished(IngestTaskStatus)}.
   * <p>
   * Method under test: {@link DynamoDBIngestTaskTracker#taskFinished(IngestTaskStatus)}
   */
  @Test
  @DisplayName("Test taskFinished(IngestTaskStatus)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBIngestTaskTracker.taskFinished(IngestTaskStatus)"})
  void testTaskFinished11() {
    // Arrange
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient(
        new STSSessionCredentials((AWSCredentials) null, 1));
    dynamoDB.putItemAsync("Table Name", new HashMap<>(), mock(AsyncHandler.class));
    Supplier<Instant> getTimeNow = mock(Supplier.class);
    when(getTimeNow.get()).thenThrow(new RuntimeException(DynamoDBIngestTaskStatusFormat.FINISHED));
    DynamoDBIngestTaskTracker dynamoDBIngestTaskTracker = new DynamoDBIngestTaskTracker(dynamoDB,
        new InstanceProperties(), getTimeNow);
    IngestTaskStatus taskStatus = mock(IngestTaskStatus.class);
    when(taskStatus.getTaskId()).thenReturn("42");

    // Act and Assert
    assertThrows(IngestTrackerException.class, () -> dynamoDBIngestTaskTracker.taskFinished(taskStatus));
    verify(getTimeNow).get();
    verify(taskStatus).getTaskId();
  }

  /**
   * Test {@link DynamoDBIngestTaskTracker#taskFinished(IngestTaskStatus)}.
   * <ul>
   *   <li>Given {@link Supplier} {@link Supplier#get()} throw {@link RuntimeException#RuntimeException(String)} with {@code 42}.</li>
   *   <li>Then calls {@link Supplier#get()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBIngestTaskTracker#taskFinished(IngestTaskStatus)}
   */
  @Test
  @DisplayName("Test taskFinished(IngestTaskStatus); given Supplier get() throw RuntimeException(String) with '42'; then calls get()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBIngestTaskTracker.taskFinished(IngestTaskStatus)"})
  void testTaskFinished_givenSupplierGetThrowRuntimeExceptionWith42_thenCallsGet() {
    // Arrange
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient(
        new STSSessionCredentials((AWSCredentials) null, 1));
    dynamoDB.putItemAsync("Table Name", new HashMap<>(), mock(AsyncHandler.class));
    Supplier<Instant> getTimeNow = mock(Supplier.class);
    when(getTimeNow.get()).thenThrow(new RuntimeException("42"));
    DynamoDBIngestTaskTracker dynamoDBIngestTaskTracker = new DynamoDBIngestTaskTracker(dynamoDB,
        new InstanceProperties(), getTimeNow);
    IngestTaskStatus taskStatus = mock(IngestTaskStatus.class);
    when(taskStatus.getTaskId()).thenReturn("42");

    // Act and Assert
    assertThrows(IngestTrackerException.class, () -> dynamoDBIngestTaskTracker.taskFinished(taskStatus));
    verify(getTimeNow).get();
    verify(taskStatus).getTaskId();
  }

  /**
   * Test {@link DynamoDBIngestTaskTracker#taskFinished(IngestTaskStatus)}.
   * <ul>
   *   <li>Then calls {@link IngestTaskStatus#getFinishedStatus()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBIngestTaskTracker#taskFinished(IngestTaskStatus)}
   */
  @Test
  @DisplayName("Test taskFinished(IngestTaskStatus); then calls getFinishedStatus()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBIngestTaskTracker.taskFinished(IngestTaskStatus)"})
  void testTaskFinished_thenCallsGetFinishedStatus() {
    // Arrange
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    DynamoDBIngestTaskTracker dynamoDBIngestTaskTracker = new DynamoDBIngestTaskTracker(dynamoDB,
        new InstanceProperties());
    IngestTaskStatus taskStatus = mock(IngestTaskStatus.class);
    when(taskStatus.getFinishedStatus()).thenThrow(new RuntimeException(DynamoDBIngestTaskStatusFormat.FINISHED));
    when(taskStatus.getTaskId()).thenReturn("42");
    when(taskStatus.getStartTime())
        .thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act and Assert
    assertThrows(IngestTrackerException.class, () -> dynamoDBIngestTaskTracker.taskFinished(taskStatus));
    verify(taskStatus).getFinishedStatus();
    verify(taskStatus).getStartTime();
    verify(taskStatus, atLeast(1)).getTaskId();
  }
}
