package sleeper.compaction.tracker.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.amazonaws.PredefinedClientConfigurations;
import com.amazonaws.auth.AnonymousAWSCredentials;
import com.amazonaws.auth.STSSessionCredentials;
import com.amazonaws.handlers.AsyncHandler;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClient;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;
import com.amazonaws.services.securitytoken.AWSSecurityTokenServiceAsyncClient;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.function.Supplier;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.compaction.tracker.CompactionTrackerException;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.instance.InstanceProperty;
import sleeper.core.tracker.compaction.task.CompactionTaskStatus;

class DynamoDBCompactionTaskTrackerDiffblueTest {
  /**
   * Test {@link DynamoDBCompactionTaskTracker#DynamoDBCompactionTaskTracker(AmazonDynamoDB, InstanceProperties)}.
   * <p>
   * Method under test: {@link DynamoDBCompactionTaskTracker#DynamoDBCompactionTaskTracker(AmazonDynamoDB, InstanceProperties)}
   */
  @Test
  @DisplayName("Test new DynamoDBCompactionTaskTracker(AmazonDynamoDB, InstanceProperties)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBCompactionTaskTracker.<init>(AmazonDynamoDB, InstanceProperties)"})
  void testNewDynamoDBCompactionTaskTracker() {
    // Arrange
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient(new AnonymousAWSCredentials());
    dynamoDB.putItemAsync("Table Name", new HashMap<>());
    dynamoDB.putItemAsync("42", new HashMap<>());
    dynamoDB.putItemAsync("Table Name", new HashMap<>(), "compaction-task-status", mock(AsyncHandler.class));
    dynamoDB.putItemAsync(new PutItemRequest());
    InstanceProperties properties = mock(InstanceProperties.class);
    when(properties.getInt(Mockito.<InstanceProperty>any())).thenReturn(1);
    when(properties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");

    // Act
    new DynamoDBCompactionTaskTracker(dynamoDB, properties);

    // Assert
    verify(properties).getInt(isA(InstanceProperty.class));
    verify(properties).get(isA(InstanceProperty.class));
  }

  /**
   * Test {@link DynamoDBCompactionTaskTracker#DynamoDBCompactionTaskTracker(AmazonDynamoDB, InstanceProperties)}.
   * <p>
   * Method under test: {@link DynamoDBCompactionTaskTracker#DynamoDBCompactionTaskTracker(AmazonDynamoDB, InstanceProperties)}
   */
  @Test
  @DisplayName("Test new DynamoDBCompactionTaskTracker(AmazonDynamoDB, InstanceProperties)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBCompactionTaskTracker.<init>(AmazonDynamoDB, InstanceProperties)"})
  void testNewDynamoDBCompactionTaskTracker2() {
    // Arrange
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient(
        new STSSessionCredentials(new AWSSecurityTokenServiceAsyncClient()));
    dynamoDB.putItemAsync("Table Name", new HashMap<>());
    dynamoDB.putItemAsync("42", new HashMap<>());
    dynamoDB.putItemAsync("Table Name", new HashMap<>(), "compaction-task-status", mock(AsyncHandler.class));
    dynamoDB.putItemAsync(new PutItemRequest());
    InstanceProperties properties = mock(InstanceProperties.class);
    when(properties.getInt(Mockito.<InstanceProperty>any())).thenReturn(1);
    when(properties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");

    // Act
    new DynamoDBCompactionTaskTracker(dynamoDB, properties);

    // Assert
    verify(properties).getInt(isA(InstanceProperty.class));
    verify(properties).get(isA(InstanceProperty.class));
  }

  /**
   * Test {@link DynamoDBCompactionTaskTracker#DynamoDBCompactionTaskTracker(AmazonDynamoDB, InstanceProperties)}.
   * <p>
   * Method under test: {@link DynamoDBCompactionTaskTracker#DynamoDBCompactionTaskTracker(AmazonDynamoDB, InstanceProperties)}
   */
  @Test
  @DisplayName("Test new DynamoDBCompactionTaskTracker(AmazonDynamoDB, InstanceProperties)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBCompactionTaskTracker.<init>(AmazonDynamoDB, InstanceProperties)"})
  void testNewDynamoDBCompactionTaskTracker3() {
    // Arrange
    PutItemRequest request = new PutItemRequest();
    request.addExpectedEntry("compaction-task-status", new ExpectedAttributeValue());

    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient(new AnonymousAWSCredentials());
    dynamoDB.putItemAsync("Table Name", new HashMap<>());
    dynamoDB.putItemAsync("42", new HashMap<>());
    dynamoDB.putItemAsync("Table Name", new HashMap<>(), "compaction-task-status", mock(AsyncHandler.class));
    dynamoDB.putItemAsync(request);
    InstanceProperties properties = mock(InstanceProperties.class);
    when(properties.getInt(Mockito.<InstanceProperty>any())).thenReturn(1);
    when(properties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");

    // Act
    new DynamoDBCompactionTaskTracker(dynamoDB, properties);

    // Assert
    verify(properties).getInt(isA(InstanceProperty.class));
    verify(properties).get(isA(InstanceProperty.class));
  }

  /**
   * Test {@link DynamoDBCompactionTaskTracker#DynamoDBCompactionTaskTracker(AmazonDynamoDB, InstanceProperties)}.
   * <p>
   * Method under test: {@link DynamoDBCompactionTaskTracker#DynamoDBCompactionTaskTracker(AmazonDynamoDB, InstanceProperties)}
   */
  @Test
  @DisplayName("Test new DynamoDBCompactionTaskTracker(AmazonDynamoDB, InstanceProperties)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBCompactionTaskTracker.<init>(AmazonDynamoDB, InstanceProperties)"})
  void testNewDynamoDBCompactionTaskTracker4() {
    // Arrange
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient(new STSSessionCredentials(
        new AWSSecurityTokenServiceAsyncClient(PredefinedClientConfigurations.defaultConfig())));
    dynamoDB.putItemAsync("Table Name", new HashMap<>());
    dynamoDB.putItemAsync("42", new HashMap<>());
    dynamoDB.putItemAsync("Table Name", new HashMap<>(), "compaction-task-status", mock(AsyncHandler.class));
    dynamoDB.putItemAsync(new PutItemRequest());
    InstanceProperties properties = mock(InstanceProperties.class);
    when(properties.getInt(Mockito.<InstanceProperty>any())).thenReturn(1);
    when(properties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");

    // Act
    new DynamoDBCompactionTaskTracker(dynamoDB, properties);

    // Assert
    verify(properties).getInt(isA(InstanceProperty.class));
    verify(properties).get(isA(InstanceProperty.class));
  }

  /**
   * Test {@link DynamoDBCompactionTaskTracker#taskStarted(CompactionTaskStatus)}.
   * <p>
   * Method under test: {@link DynamoDBCompactionTaskTracker#taskStarted(CompactionTaskStatus)}
   */
  @Test
  @DisplayName("Test taskStarted(CompactionTaskStatus)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBCompactionTaskTracker.taskStarted(CompactionTaskStatus)"})
  void testTaskStarted() {
    // Arrange
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    DynamoDBCompactionTaskTracker dynamoDBCompactionTaskTracker = new DynamoDBCompactionTaskTracker(dynamoDB,
        new InstanceProperties());
    CompactionTaskStatus taskStatus = mock(CompactionTaskStatus.class);
    when(taskStatus.getTaskId()).thenReturn("42");
    when(taskStatus.getStartTime())
        .thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act and Assert
    assertThrows(CompactionTrackerException.class, () -> dynamoDBCompactionTaskTracker.taskStarted(taskStatus));
    verify(taskStatus).getStartTime();
    verify(taskStatus, atLeast(1)).getTaskId();
  }

  /**
   * Test {@link DynamoDBCompactionTaskTracker#taskStarted(CompactionTaskStatus)}.
   * <p>
   * Method under test: {@link DynamoDBCompactionTaskTracker#taskStarted(CompactionTaskStatus)}
   */
  @Test
  @DisplayName("Test taskStarted(CompactionTaskStatus)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBCompactionTaskTracker.taskStarted(CompactionTaskStatus)"})
  void testTaskStarted2() {
    // Arrange
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient(new AnonymousAWSCredentials());
    DynamoDBCompactionTaskTracker dynamoDBCompactionTaskTracker = new DynamoDBCompactionTaskTracker(dynamoDB,
        new InstanceProperties());
    CompactionTaskStatus taskStatus = mock(CompactionTaskStatus.class);
    when(taskStatus.getTaskId()).thenReturn("42");
    when(taskStatus.getStartTime())
        .thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act and Assert
    assertThrows(CompactionTrackerException.class, () -> dynamoDBCompactionTaskTracker.taskStarted(taskStatus));
    verify(taskStatus).getStartTime();
    verify(taskStatus, atLeast(1)).getTaskId();
  }

  /**
   * Test {@link DynamoDBCompactionTaskTracker#taskStarted(CompactionTaskStatus)}.
   * <ul>
   *   <li>Given {@link AmazonDynamoDBAsyncClient#AmazonDynamoDBAsyncClient()} ItemAsync is {@link PutItemRequest#PutItemRequest()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBCompactionTaskTracker#taskStarted(CompactionTaskStatus)}
   */
  @Test
  @DisplayName("Test taskStarted(CompactionTaskStatus); given AmazonDynamoDBAsyncClient() ItemAsync is PutItemRequest()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBCompactionTaskTracker.taskStarted(CompactionTaskStatus)"})
  void testTaskStarted_givenAmazonDynamoDBAsyncClientItemAsyncIsPutItemRequest() {
    // Arrange
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    dynamoDB.putItemAsync(new PutItemRequest());
    DynamoDBCompactionTaskTracker dynamoDBCompactionTaskTracker = new DynamoDBCompactionTaskTracker(dynamoDB,
        new InstanceProperties());
    CompactionTaskStatus taskStatus = mock(CompactionTaskStatus.class);
    when(taskStatus.getTaskId()).thenReturn("42");
    when(taskStatus.getStartTime())
        .thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act and Assert
    assertThrows(CompactionTrackerException.class, () -> dynamoDBCompactionTaskTracker.taskStarted(taskStatus));
    verify(taskStatus).getStartTime();
    verify(taskStatus, atLeast(1)).getTaskId();
  }

  /**
   * Test {@link DynamoDBCompactionTaskTracker#taskStarted(CompactionTaskStatus)}.
   * <ul>
   *   <li>Given {@link Supplier} {@link Supplier#get()} throw {@link RuntimeException#RuntimeException(String)} with {@link DynamoDBCompactionTaskStatusFormat#STARTED}.</li>
   *   <li>Then calls {@link Supplier#get()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBCompactionTaskTracker#taskStarted(CompactionTaskStatus)}
   */
  @Test
  @DisplayName("Test taskStarted(CompactionTaskStatus); given Supplier get() throw RuntimeException(String) with STARTED; then calls get()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBCompactionTaskTracker.taskStarted(CompactionTaskStatus)"})
  void testTaskStarted_givenSupplierGetThrowRuntimeExceptionWithStarted_thenCallsGet() {
    // Arrange
    Supplier<Instant> getTimeNow = mock(Supplier.class);
    when(getTimeNow.get()).thenThrow(new RuntimeException(DynamoDBCompactionTaskStatusFormat.STARTED));
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    DynamoDBCompactionTaskTracker dynamoDBCompactionTaskTracker = new DynamoDBCompactionTaskTracker(dynamoDB,
        new InstanceProperties(), getTimeNow);
    CompactionTaskStatus taskStatus = mock(CompactionTaskStatus.class);
    when(taskStatus.getTaskId()).thenReturn("42");

    // Act and Assert
    assertThrows(CompactionTrackerException.class, () -> dynamoDBCompactionTaskTracker.taskStarted(taskStatus));
    verify(getTimeNow).get();
    verify(taskStatus).getTaskId();
  }

  /**
   * Test {@link DynamoDBCompactionTaskTracker#taskFinished(CompactionTaskStatus)}.
   * <p>
   * Method under test: {@link DynamoDBCompactionTaskTracker#taskFinished(CompactionTaskStatus)}
   */
  @Test
  @DisplayName("Test taskFinished(CompactionTaskStatus)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBCompactionTaskTracker.taskFinished(CompactionTaskStatus)"})
  void testTaskFinished() {
    // Arrange
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    dynamoDB.putItemAsync(new PutItemRequest(), mock(AsyncHandler.class));
    DynamoDBCompactionTaskTracker dynamoDBCompactionTaskTracker = new DynamoDBCompactionTaskTracker(dynamoDB,
        new InstanceProperties());
    CompactionTaskStatus taskStatus = mock(CompactionTaskStatus.class);
    when(taskStatus.getFinishedStatus()).thenThrow(new RuntimeException(DynamoDBCompactionTaskStatusFormat.FINISHED));
    when(taskStatus.getTaskId()).thenReturn("42");
    when(taskStatus.getStartTime())
        .thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act and Assert
    assertThrows(CompactionTrackerException.class, () -> dynamoDBCompactionTaskTracker.taskFinished(taskStatus));
    verify(taskStatus).getFinishedStatus();
    verify(taskStatus).getStartTime();
    verify(taskStatus, atLeast(1)).getTaskId();
  }

  /**
   * Test {@link DynamoDBCompactionTaskTracker#taskFinished(CompactionTaskStatus)}.
   * <ul>
   *   <li>Given {@link Supplier} {@link Supplier#get()} throw {@link RuntimeException#RuntimeException(String)} with {@link DynamoDBCompactionTaskStatusFormat#FINISHED}.</li>
   *   <li>Then calls {@link Supplier#get()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBCompactionTaskTracker#taskFinished(CompactionTaskStatus)}
   */
  @Test
  @DisplayName("Test taskFinished(CompactionTaskStatus); given Supplier get() throw RuntimeException(String) with FINISHED; then calls get()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBCompactionTaskTracker.taskFinished(CompactionTaskStatus)"})
  void testTaskFinished_givenSupplierGetThrowRuntimeExceptionWithFinished_thenCallsGet() {
    // Arrange
    Supplier<Instant> getTimeNow = mock(Supplier.class);
    when(getTimeNow.get()).thenThrow(new RuntimeException(DynamoDBCompactionTaskStatusFormat.FINISHED));
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    DynamoDBCompactionTaskTracker dynamoDBCompactionTaskTracker = new DynamoDBCompactionTaskTracker(dynamoDB,
        new InstanceProperties(), getTimeNow);
    CompactionTaskStatus taskStatus = mock(CompactionTaskStatus.class);
    when(taskStatus.getTaskId()).thenReturn("42");

    // Act and Assert
    assertThrows(CompactionTrackerException.class, () -> dynamoDBCompactionTaskTracker.taskFinished(taskStatus));
    verify(getTimeNow).get();
    verify(taskStatus).getTaskId();
  }

  /**
   * Test {@link DynamoDBCompactionTaskTracker#taskFinished(CompactionTaskStatus)}.
   * <ul>
   *   <li>Then calls {@link CompactionTaskStatus#getFinishedStatus()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBCompactionTaskTracker#taskFinished(CompactionTaskStatus)}
   */
  @Test
  @DisplayName("Test taskFinished(CompactionTaskStatus); then calls getFinishedStatus()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBCompactionTaskTracker.taskFinished(CompactionTaskStatus)"})
  void testTaskFinished_thenCallsGetFinishedStatus() {
    // Arrange
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    DynamoDBCompactionTaskTracker dynamoDBCompactionTaskTracker = new DynamoDBCompactionTaskTracker(dynamoDB,
        new InstanceProperties());
    CompactionTaskStatus taskStatus = mock(CompactionTaskStatus.class);
    when(taskStatus.getFinishedStatus()).thenThrow(new RuntimeException(DynamoDBCompactionTaskStatusFormat.FINISHED));
    when(taskStatus.getTaskId()).thenReturn("42");
    when(taskStatus.getStartTime())
        .thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act and Assert
    assertThrows(CompactionTrackerException.class, () -> dynamoDBCompactionTaskTracker.taskFinished(taskStatus));
    verify(taskStatus).getFinishedStatus();
    verify(taskStatus).getStartTime();
    verify(taskStatus, atLeast(1)).getTaskId();
  }

  /**
   * Test {@link DynamoDBCompactionTaskTracker#taskStatusTableName(String)}.
   * <p>
   * Method under test: {@link DynamoDBCompactionTaskTracker#taskStatusTableName(String)}
   */
  @Test
  @DisplayName("Test taskStatusTableName(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String DynamoDBCompactionTaskTracker.taskStatusTableName(String)"})
  void testTaskStatusTableName() {
    // Arrange, Act and Assert
    assertEquals("sleeper-42-compaction-task-status", DynamoDBCompactionTaskTracker.taskStatusTableName("42"));
  }
}
