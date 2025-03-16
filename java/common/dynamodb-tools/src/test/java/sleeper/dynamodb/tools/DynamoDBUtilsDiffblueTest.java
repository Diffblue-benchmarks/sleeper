package sleeper.dynamodb.tools;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.amazonaws.services.dynamodbv2.model.AmazonDynamoDBException;
import com.amazonaws.services.dynamodbv2.model.CancellationReason;
import com.amazonaws.services.dynamodbv2.model.ResourceInUseException;
import com.amazonaws.services.dynamodbv2.model.TransactionCanceledException;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BooleanSupplier;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.util.PollWithRetries;
import sleeper.core.util.PollWithRetries.CheckFailedException;
import sleeper.core.util.ThreadSleep;

class DynamoDBUtilsDiffblueTest {
  /**
   * Test {@link DynamoDBUtils#instanceTableName(String, String)}.
   * <p>
   * Method under test: {@link DynamoDBUtils#instanceTableName(String, String)}
   */
  @Test
  @DisplayName("Test instanceTableName(String, String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String DynamoDBUtils.instanceTableName(String, String)"})
  void testInstanceTableName() {
    // Arrange, Act and Assert
    assertEquals("sleeper-42-Table Name", DynamoDBUtils.instanceTableName("42", "Table Name"));
  }

  /**
   * Test {@link DynamoDBUtils#streamResults(Object, Function, Function, Function)}.
   * <ul>
   *   <li>Given {@code Apply}.</li>
   *   <li>Then return limit five collect toList size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBUtils#streamResults(Object, Function, Function, Function)}
   */
  @Test
  @DisplayName("Test streamResults(Object, Function, Function, Function); given 'Apply'; then return limit five collect toList size is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream DynamoDBUtils.streamResults(Object, Function, Function, Function)"})
  void testStreamResults_givenApply_thenReturnLimitFiveCollectToListSizeIsOne() {
    // Arrange
    Function<Object, Object> query = mock(Function.class);
    when(query.apply(Mockito.<Object>any())).thenReturn("Apply");

    // Act
    Stream<Object> actualStreamResultsResult = DynamoDBUtils.<Object, Object, Object>streamResults("Request", query,
        mock(Function.class), mock(Function.class));

    // Assert
    verify(query).apply(isA(Object.class));
    List<Object> collectResult = actualStreamResultsResult.limit(5).collect(Collectors.toList());
    assertEquals(1, collectResult.size());
    assertEquals("Apply", collectResult.get(0));
  }

  /**
   * Test {@link DynamoDBUtils#streamResults(Object, Function, Function, Function)}.
   * <ul>
   *   <li>Then throw {@link ResourceInUseException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBUtils#streamResults(Object, Function, Function, Function)}
   */
  @Test
  @DisplayName("Test streamResults(Object, Function, Function, Function); then throw ResourceInUseException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream DynamoDBUtils.streamResults(Object, Function, Function, Function)"})
  void testStreamResults_thenThrowResourceInUseException() {
    // Arrange
    Function<Object, Object> query = mock(Function.class);
    when(query.apply(Mockito.<Object>any())).thenThrow(new ResourceInUseException("An error occurred"));

    // Act and Assert
    assertThrows(ResourceInUseException.class, () -> DynamoDBUtils.<Object, Object, Object>streamResults("Request",
        query, mock(Function.class), mock(Function.class)));
    verify(query).apply(isA(Object.class));
  }

  /**
   * Test {@link DynamoDBUtils#hasConditionalCheckFailure(AmazonDynamoDBException)} with {@code AmazonDynamoDBException}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBUtils#hasConditionalCheckFailure(AmazonDynamoDBException)}
   */
  @Test
  @DisplayName("Test hasConditionalCheckFailure(AmazonDynamoDBException) with 'AmazonDynamoDBException'; then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean DynamoDBUtils.hasConditionalCheckFailure(AmazonDynamoDBException)"})
  void testHasConditionalCheckFailureWithAmazonDynamoDBException_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(DynamoDBUtils.hasConditionalCheckFailure(new AmazonDynamoDBException("An error occurred")));
  }

  /**
   * Test {@link DynamoDBUtils#hasConditionalCheckFailure(TransactionCanceledException)} with {@code TransactionCanceledException}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBUtils#hasConditionalCheckFailure(TransactionCanceledException)}
   */
  @Test
  @DisplayName("Test hasConditionalCheckFailure(TransactionCanceledException) with 'TransactionCanceledException'; then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean DynamoDBUtils.hasConditionalCheckFailure(TransactionCanceledException)"})
  void testHasConditionalCheckFailureWithTransactionCanceledException_thenReturnFalse() {
    // Arrange
    TransactionCanceledException e = new TransactionCanceledException("An error occurred");
    e.setCancellationReasons(new ArrayList<>());

    // Act and Assert
    assertFalse(DynamoDBUtils.hasConditionalCheckFailure(e));
  }

  /**
   * Test {@link DynamoDBUtils#isConditionCheckFailure(CancellationReason)}.
   * <ul>
   *   <li>Given {@code ConditionalCheckFailed}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBUtils#isConditionCheckFailure(CancellationReason)}
   */
  @Test
  @DisplayName("Test isConditionCheckFailure(CancellationReason); given 'ConditionalCheckFailed'; then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean DynamoDBUtils.isConditionCheckFailure(CancellationReason)"})
  void testIsConditionCheckFailure_givenConditionalCheckFailed_thenReturnTrue() {
    // Arrange
    CancellationReason reason = new CancellationReason();
    reason.setCode("ConditionalCheckFailed");

    // Act and Assert
    assertTrue(DynamoDBUtils.isConditionCheckFailure(reason));
  }

  /**
   * Test {@link DynamoDBUtils#isConditionCheckFailure(CancellationReason)}.
   * <ul>
   *   <li>When {@link CancellationReason} (default constructor).</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBUtils#isConditionCheckFailure(CancellationReason)}
   */
  @Test
  @DisplayName("Test isConditionCheckFailure(CancellationReason); when CancellationReason (default constructor); then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean DynamoDBUtils.isConditionCheckFailure(CancellationReason)"})
  void testIsConditionCheckFailure_whenCancellationReason_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(DynamoDBUtils.isConditionCheckFailure(new CancellationReason()));
  }

  /**
   * Test {@link DynamoDBUtils#isThrottlingException(Throwable)}.
   * <p>
   * Method under test: {@link DynamoDBUtils#isThrottlingException(Throwable)}
   */
  @Test
  @DisplayName("Test isThrottlingException(Throwable)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean DynamoDBUtils.isThrottlingException(Throwable)"})
  void testIsThrottlingException() {
    // Arrange
    AmazonDynamoDBException amazonDynamoDBException = new AmazonDynamoDBException("An error occurred");
    amazonDynamoDBException.setErrorCode("ThrottlingException");

    Throwable e = new Throwable();
    e.initCause(amazonDynamoDBException);

    // Act and Assert
    assertTrue(DynamoDBUtils.isThrottlingException(e));
  }

  /**
   * Test {@link DynamoDBUtils#isThrottlingException(Throwable)}.
   * <ul>
   *   <li>Given {@code ThrottlingException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBUtils#isThrottlingException(Throwable)}
   */
  @Test
  @DisplayName("Test isThrottlingException(Throwable); given 'ThrottlingException'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean DynamoDBUtils.isThrottlingException(Throwable)"})
  void testIsThrottlingException_givenThrottlingException() {
    // Arrange
    AmazonDynamoDBException e = new AmazonDynamoDBException("An error occurred");
    e.setErrorCode("ThrottlingException");

    // Act and Assert
    assertTrue(DynamoDBUtils.isThrottlingException(e));
  }

  /**
   * Test {@link DynamoDBUtils#isThrottlingException(Throwable)}.
   * <ul>
   *   <li>When {@link Throwable#Throwable()}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBUtils#isThrottlingException(Throwable)}
   */
  @Test
  @DisplayName("Test isThrottlingException(Throwable); when Throwable(); then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean DynamoDBUtils.isThrottlingException(Throwable)"})
  void testIsThrottlingException_whenThrowable_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(DynamoDBUtils.isThrottlingException(new Throwable()));
  }

  /**
   * Test {@link DynamoDBUtils#retryOnThrottlingException(PollWithRetries, Runnable)}.
   * <p>
   * Method under test: {@link DynamoDBUtils#retryOnThrottlingException(PollWithRetries, Runnable)}
   */
  @Test
  @DisplayName("Test retryOnThrottlingException(PollWithRetries, Runnable)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBUtils.retryOnThrottlingException(PollWithRetries, Runnable)"})
  void testRetryOnThrottlingException() throws InterruptedException, CheckFailedException {
    // Arrange
    PollWithRetries pollWithRetries = mock(PollWithRetries.class);
    doThrow(new ResourceInUseException("An error occurred")).when(pollWithRetries)
        .pollUntil(Mockito.<String>any(), Mockito.<BooleanSupplier>any());

    // Act and Assert
    assertThrows(ResourceInUseException.class,
        () -> DynamoDBUtils.retryOnThrottlingException(pollWithRetries, mock(Runnable.class)));
    verify(pollWithRetries).pollUntil(eq("no throttling exception"), isA(BooleanSupplier.class));
  }

  /**
   * Test {@link DynamoDBUtils#retryOnThrottlingException(PollWithRetries, Runnable)}.
   * <p>
   * Method under test: {@link DynamoDBUtils#retryOnThrottlingException(PollWithRetries, Runnable)}
   */
  @Test
  @DisplayName("Test retryOnThrottlingException(PollWithRetries, Runnable)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBUtils.retryOnThrottlingException(PollWithRetries, Runnable)"})
  void testRetryOnThrottlingException2() throws InterruptedException {
    // Arrange
    PollWithRetries pollWithRetries = PollWithRetries.builder()
        .maxRetries(3)
        .pollIntervalMillis(42L)
        .sleepInInterval(mock(ThreadSleep.class))
        .build();
    Runnable runnable = mock(Runnable.class);
    doThrow(new ResourceInUseException("An error occurred")).when(runnable).run();

    // Act and Assert
    assertThrows(ResourceInUseException.class,
        () -> DynamoDBUtils.retryOnThrottlingException(pollWithRetries, runnable));
    verify(runnable).run();
  }

  /**
   * Test {@link DynamoDBUtils#retryOnThrottlingException(PollWithRetries, Runnable)}.
   * <ul>
   *   <li>Then throw {@link RuntimeException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBUtils#retryOnThrottlingException(PollWithRetries, Runnable)}
   */
  @Test
  @DisplayName("Test retryOnThrottlingException(PollWithRetries, Runnable); then throw RuntimeException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBUtils.retryOnThrottlingException(PollWithRetries, Runnable)"})
  void testRetryOnThrottlingException_thenThrowRuntimeException() throws InterruptedException {
    // Arrange
    PollWithRetries pollWithRetries = PollWithRetries.builder()
        .maxRetries(3)
        .pollIntervalMillis(42L)
        .sleepInInterval(mock(ThreadSleep.class))
        .build();
    Runnable runnable = mock(Runnable.class);
    doThrow(new RuntimeException("no throttling exception")).when(runnable).run();

    // Act and Assert
    assertThrows(RuntimeException.class, () -> DynamoDBUtils.retryOnThrottlingException(pollWithRetries, runnable));
    verify(runnable).run();
  }

  /**
   * Test {@link DynamoDBUtils#retryOnThrottlingException(PollWithRetries, Runnable)}.
   * <ul>
   *   <li>When {@link PollWithRetries} {@link PollWithRetries#pollUntil(String, BooleanSupplier)} does nothing.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBUtils#retryOnThrottlingException(PollWithRetries, Runnable)}
   */
  @Test
  @DisplayName("Test retryOnThrottlingException(PollWithRetries, Runnable); when PollWithRetries pollUntil(String, BooleanSupplier) does nothing")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBUtils.retryOnThrottlingException(PollWithRetries, Runnable)"})
  void testRetryOnThrottlingException_whenPollWithRetriesPollUntilDoesNothing()
      throws InterruptedException, CheckFailedException {
    // Arrange
    PollWithRetries pollWithRetries = mock(PollWithRetries.class);
    doNothing().when(pollWithRetries).pollUntil(Mockito.<String>any(), Mockito.<BooleanSupplier>any());

    // Act
    DynamoDBUtils.retryOnThrottlingException(pollWithRetries, mock(Runnable.class));

    // Assert
    verify(pollWithRetries).pollUntil(eq("no throttling exception"), isA(BooleanSupplier.class));
  }

  /**
   * Test {@link DynamoDBUtils#retryOnThrottlingException(PollWithRetries, Runnable)}.
   * <ul>
   *   <li>When {@link Runnable} {@link Runnable#run()} does nothing.</li>
   *   <li>Then calls {@link Runnable#run()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBUtils#retryOnThrottlingException(PollWithRetries, Runnable)}
   */
  @Test
  @DisplayName("Test retryOnThrottlingException(PollWithRetries, Runnable); when Runnable run() does nothing; then calls run()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DynamoDBUtils.retryOnThrottlingException(PollWithRetries, Runnable)"})
  void testRetryOnThrottlingException_whenRunnableRunDoesNothing_thenCallsRun() throws InterruptedException {
    // Arrange
    PollWithRetries pollWithRetries = PollWithRetries.builder()
        .maxRetries(3)
        .pollIntervalMillis(42L)
        .sleepInInterval(mock(ThreadSleep.class))
        .build();
    Runnable runnable = mock(Runnable.class);
    doNothing().when(runnable).run();

    // Act
    DynamoDBUtils.retryOnThrottlingException(pollWithRetries, runnable);

    // Assert
    verify(runnable).run();
  }
}
