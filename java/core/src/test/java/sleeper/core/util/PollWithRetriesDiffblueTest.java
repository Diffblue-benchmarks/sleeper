package sleeper.core.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.Duration;
import java.util.function.BooleanSupplier;
import java.util.function.Predicate;
import java.util.function.Supplier;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.statestore.transactionlog.TransactionLogStateStore;
import sleeper.core.util.PollWithRetries.Builder;
import sleeper.core.util.PollWithRetries.CheckFailedException;
import sleeper.core.util.PollWithRetries.TimedOutException;

class PollWithRetriesDiffblueTest {
  /**
   * Test Builder {@link Builder#immediateRetries(int)}.
   * <p>
   * Method under test: {@link Builder#immediateRetries(int)}
   */
  @Test
  @DisplayName("Test Builder immediateRetries(int)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.immediateRetries(int)"})
  void testBuilderImmediateRetries() {
    // Arrange
    Builder builderResult = PollWithRetries.builder();

    // Act and Assert
    assertSame(builderResult, builderResult.immediateRetries(1));
  }

  /**
   * Test Builder {@link Builder#noRetries()}.
   * <p>
   * Method under test: {@link Builder#noRetries()}
   */
  @Test
  @DisplayName("Test Builder noRetries()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.noRetries()"})
  void testBuilderNoRetries() {
    // Arrange
    Builder builderResult = PollWithRetries.builder();

    // Act and Assert
    assertSame(builderResult, builderResult.noRetries());
  }

  /**
   * Test Builder {@link Builder#pollIntervalAndTimeout(Duration, Duration)}.
   * <ul>
   *   <li>Then return builder.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#pollIntervalAndTimeout(Duration, Duration)}
   */
  @Test
  @DisplayName("Test Builder pollIntervalAndTimeout(Duration, Duration); then return builder")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.pollIntervalAndTimeout(Duration, Duration)"})
  void testBuilderPollIntervalAndTimeout_thenReturnBuilder() {
    // Arrange
    Builder builderResult = PollWithRetries.builder();

    // Act and Assert
    assertSame(builderResult,
        builderResult.pollIntervalAndTimeout(TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS,
            TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS));
  }

  /**
   * Test Builder {@link Builder#trackMaxRetriesAcrossInvocations()}.
   * <p>
   * Method under test: {@link Builder#trackMaxRetriesAcrossInvocations()}
   */
  @Test
  @DisplayName("Test Builder trackMaxRetriesAcrossInvocations()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.trackMaxRetriesAcrossInvocations()"})
  void testBuilderTrackMaxRetriesAcrossInvocations() {
    // Arrange
    Builder builderResult = PollWithRetries.builder();

    // Act and Assert
    assertSame(builderResult, builderResult.trackMaxRetriesAcrossInvocations());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link PollWithRetries#toBuilder()}
   *   <li>{@link PollWithRetries#toString()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder PollWithRetries.toBuilder()", "String PollWithRetries.toString()"})
  void testGettersAndSetters() {
    // Arrange
    PollWithRetries buildResult = PollWithRetries.builder()
        .maxRetries(3)
        .pollIntervalMillis(42L)
        .sleepInInterval(mock(ThreadSleep.class))
        .build();

    // Act
    buildResult.toBuilder();

    // Assert
    assertEquals("PollWithRetries{pollIntervalMillis=42, maxRetries=3, pollsTracker=TrackRetriesPerInvocation{}}",
        buildResult.toString());
  }

  /**
   * Test {@link PollWithRetries#pollUntil(String, BooleanSupplier)}.
   * <p>
   * Method under test: {@link PollWithRetries#pollUntil(String, BooleanSupplier)}
   */
  @Test
  @DisplayName("Test pollUntil(String, BooleanSupplier)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void PollWithRetries.pollUntil(String, BooleanSupplier)"})
  void testPollUntil() throws InterruptedException, CheckFailedException {
    // Arrange
    ThreadSleep sleepInInterval = mock(ThreadSleep.class);
    doNothing().when(sleepInInterval).waitForMillis(anyLong());
    PollWithRetries buildResult = PollWithRetries.builder()
        .maxRetries(3)
        .pollIntervalMillis(-1L)
        .sleepInInterval(sleepInInterval)
        .build();
    BooleanSupplier checkFinished = mock(BooleanSupplier.class);
    when(checkFinished.getAsBoolean()).thenReturn(false);

    // Act and Assert
    assertThrows(TimedOutException.class,
        () -> buildResult.pollUntil("The characteristics of someone or something", checkFinished));
    verify(checkFinished, atLeast(1)).getAsBoolean();
    verify(sleepInInterval, atLeast(1)).waitForMillis(eq(-1L));
  }

  /**
   * Test {@link PollWithRetries#pollUntil(String, BooleanSupplier)}.
   * <p>
   * Method under test: {@link PollWithRetries#pollUntil(String, BooleanSupplier)}
   */
  @Test
  @DisplayName("Test pollUntil(String, BooleanSupplier)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void PollWithRetries.pollUntil(String, BooleanSupplier)"})
  void testPollUntil2() throws InterruptedException, CheckFailedException {
    // Arrange
    ThreadSleep sleepInInterval = mock(ThreadSleep.class);
    doNothing().when(sleepInInterval).waitForMillis(anyLong());
    PollWithRetries buildResult = PollWithRetries.builder()
        .maxRetries(3)
        .pollIntervalMillis(Long.MAX_VALUE)
        .sleepInInterval(sleepInInterval)
        .build();
    BooleanSupplier checkFinished = mock(BooleanSupplier.class);
    when(checkFinished.getAsBoolean()).thenReturn(false);

    // Act and Assert
    assertThrows(TimedOutException.class,
        () -> buildResult.pollUntil("The characteristics of someone or something", checkFinished));
    verify(checkFinished, atLeast(1)).getAsBoolean();
    verify(sleepInInterval, atLeast(1)).waitForMillis(eq(9223372036854775807L));
  }

  /**
   * Test {@link PollWithRetries#pollUntil(String, BooleanSupplier)}.
   * <ul>
   *   <li>Given {@code true}.</li>
   *   <li>When {@link BooleanSupplier} {@link BooleanSupplier#getAsBoolean()} return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PollWithRetries#pollUntil(String, BooleanSupplier)}
   */
  @Test
  @DisplayName("Test pollUntil(String, BooleanSupplier); given 'true'; when BooleanSupplier getAsBoolean() return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void PollWithRetries.pollUntil(String, BooleanSupplier)"})
  void testPollUntil_givenTrue_whenBooleanSupplierGetAsBooleanReturnTrue()
      throws InterruptedException, CheckFailedException {
    // Arrange
    PollWithRetries buildResult = PollWithRetries.builder()
        .maxRetries(3)
        .pollIntervalMillis(42L)
        .sleepInInterval(mock(ThreadSleep.class))
        .build();
    BooleanSupplier checkFinished = mock(BooleanSupplier.class);
    when(checkFinished.getAsBoolean()).thenReturn(true);

    // Act
    buildResult.pollUntil("The characteristics of someone or something", checkFinished);

    // Assert
    verify(checkFinished).getAsBoolean();
  }

  /**
   * Test {@link PollWithRetries#pollUntil(String, BooleanSupplier)}.
   * <ul>
   *   <li>Then throw {@link CheckFailedException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PollWithRetries#pollUntil(String, BooleanSupplier)}
   */
  @Test
  @DisplayName("Test pollUntil(String, BooleanSupplier); then throw CheckFailedException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void PollWithRetries.pollUntil(String, BooleanSupplier)"})
  void testPollUntil_thenThrowCheckFailedException() throws InterruptedException, CheckFailedException {
    // Arrange
    PollWithRetries buildResult = PollWithRetries.builder()
        .maxRetries(-1)
        .pollIntervalMillis(42L)
        .sleepInInterval(mock(ThreadSleep.class))
        .build();
    BooleanSupplier checkFinished = mock(BooleanSupplier.class);
    when(checkFinished.getAsBoolean()).thenReturn(false);

    // Act and Assert
    assertThrows(CheckFailedException.class,
        () -> buildResult.pollUntil("The characteristics of someone or something", checkFinished));
    verify(checkFinished).getAsBoolean();
  }

  /**
   * Test {@link PollWithRetries#pollUntil(String, BooleanSupplier)}.
   * <ul>
   *   <li>Then throw {@link TimedOutException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PollWithRetries#pollUntil(String, BooleanSupplier)}
   */
  @Test
  @DisplayName("Test pollUntil(String, BooleanSupplier); then throw TimedOutException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void PollWithRetries.pollUntil(String, BooleanSupplier)"})
  void testPollUntil_thenThrowTimedOutException() throws InterruptedException, CheckFailedException {
    // Arrange
    ThreadSleep sleepInInterval = mock(ThreadSleep.class);
    doNothing().when(sleepInInterval).waitForMillis(anyLong());
    PollWithRetries buildResult = PollWithRetries.builder()
        .maxRetries(3)
        .pollIntervalMillis(42L)
        .sleepInInterval(sleepInInterval)
        .build();
    BooleanSupplier checkFinished = mock(BooleanSupplier.class);
    when(checkFinished.getAsBoolean()).thenReturn(false);

    // Act and Assert
    assertThrows(TimedOutException.class,
        () -> buildResult.pollUntil("The characteristics of someone or something", checkFinished));
    verify(checkFinished, atLeast(1)).getAsBoolean();
    verify(sleepInInterval, atLeast(1)).waitForMillis(eq(42L));
  }

  /**
   * Test {@link PollWithRetries#queryUntil(String, Supplier, Predicate)}.
   * <p>
   * Method under test: {@link PollWithRetries#queryUntil(String, Supplier, Predicate)}
   */
  @Test
  @DisplayName("Test queryUntil(String, Supplier, Predicate)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Object PollWithRetries.queryUntil(String, Supplier, Predicate)"})
  void testQueryUntil() throws InterruptedException, CheckFailedException {
    // Arrange
    Supplier<Object> query = mock(Supplier.class);
    when(query.get()).thenReturn("Get");
    ThreadSleep sleepInInterval = mock(ThreadSleep.class);
    doNothing().when(sleepInInterval).waitForMillis(anyLong());
    PollWithRetries buildResult = PollWithRetries.builder()
        .maxRetries(3)
        .pollIntervalMillis(42L)
        .sleepInInterval(sleepInInterval)
        .build();

    // Act and Assert
    assertThrows(TimedOutException.class,
        () -> buildResult.queryUntil("The characteristics of someone or something", query, buildResult::equals));
    verify(query, atLeast(1)).get();
    verify(sleepInInterval, atLeast(1)).waitForMillis(eq(42L));
  }

  /**
   * Test {@link PollWithRetries#queryUntil(String, Supplier, Predicate)}.
   * <p>
   * Method under test: {@link PollWithRetries#queryUntil(String, Supplier, Predicate)}
   */
  @Test
  @DisplayName("Test queryUntil(String, Supplier, Predicate)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Object PollWithRetries.queryUntil(String, Supplier, Predicate)"})
  void testQueryUntil2() throws InterruptedException, CheckFailedException {
    // Arrange
    Supplier<Object> query = mock(Supplier.class);
    when(query.get()).thenReturn("Get");
    ThreadSleep sleepInInterval = mock(ThreadSleep.class);
    doNothing().when(sleepInInterval).waitForMillis(anyLong());
    PollWithRetries buildResult = PollWithRetries.builder()
        .maxRetries(3)
        .pollIntervalMillis(-1L)
        .sleepInInterval(sleepInInterval)
        .build();

    // Act and Assert
    assertThrows(TimedOutException.class,
        () -> buildResult.queryUntil("The characteristics of someone or something", query, buildResult::equals));
    verify(query, atLeast(1)).get();
    verify(sleepInInterval, atLeast(1)).waitForMillis(eq(-1L));
  }

  /**
   * Test {@link PollWithRetries#queryUntil(String, Supplier, Predicate)}.
   * <p>
   * Method under test: {@link PollWithRetries#queryUntil(String, Supplier, Predicate)}
   */
  @Test
  @DisplayName("Test queryUntil(String, Supplier, Predicate)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Object PollWithRetries.queryUntil(String, Supplier, Predicate)"})
  void testQueryUntil3() throws InterruptedException, CheckFailedException {
    // Arrange
    Supplier<Object> query = mock(Supplier.class);
    when(query.get()).thenReturn("Get");
    ThreadSleep sleepInInterval = mock(ThreadSleep.class);
    doNothing().when(sleepInInterval).waitForMillis(anyLong());
    PollWithRetries buildResult = PollWithRetries.builder()
        .maxRetries(3)
        .pollIntervalMillis(Long.MAX_VALUE)
        .sleepInInterval(sleepInInterval)
        .build();

    // Act and Assert
    assertThrows(TimedOutException.class,
        () -> buildResult.queryUntil("The characteristics of someone or something", query, buildResult::equals));
    verify(query, atLeast(1)).get();
    verify(sleepInInterval, atLeast(1)).waitForMillis(eq(9223372036854775807L));
  }

  /**
   * Test {@link PollWithRetries#queryUntil(String, Supplier, Predicate)}.
   * <ul>
   *   <li>Then throw {@link CheckFailedException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PollWithRetries#queryUntil(String, Supplier, Predicate)}
   */
  @Test
  @DisplayName("Test queryUntil(String, Supplier, Predicate); then throw CheckFailedException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Object PollWithRetries.queryUntil(String, Supplier, Predicate)"})
  void testQueryUntil_thenThrowCheckFailedException() throws InterruptedException, CheckFailedException {
    // Arrange
    Supplier<Object> query = mock(Supplier.class);
    when(query.get()).thenReturn("Get");
    PollWithRetries buildResult = PollWithRetries.builder()
        .maxRetries(0)
        .pollIntervalMillis(42L)
        .sleepInInterval(mock(ThreadSleep.class))
        .build();

    // Act and Assert
    assertThrows(CheckFailedException.class,
        () -> buildResult.queryUntil("The characteristics of someone or something", query, buildResult::equals));
    verify(query).get();
  }

  /**
   * Test {@link PollWithRetries#equals(Object)}, and {@link PollWithRetries#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link PollWithRetries#equals(Object)}
   *   <li>{@link PollWithRetries#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean PollWithRetries.equals(Object)", "int PollWithRetries.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    PollWithRetries buildResult = PollWithRetries.builder()
        .maxRetries(3)
        .pollIntervalMillis(42L)
        .sleepInInterval(mock(ThreadSleep.class))
        .build();
    PollWithRetries buildResult2 = PollWithRetries.builder()
        .maxRetries(3)
        .pollIntervalMillis(42L)
        .sleepInInterval(mock(ThreadSleep.class))
        .build();

    // Act and Assert
    assertEquals(buildResult, buildResult2);
    int expectedHashCodeResult = buildResult.hashCode();
    assertEquals(expectedHashCodeResult, buildResult2.hashCode());
  }

  /**
   * Test {@link PollWithRetries#equals(Object)}, and {@link PollWithRetries#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link PollWithRetries#equals(Object)}
   *   <li>{@link PollWithRetries#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean PollWithRetries.equals(Object)", "int PollWithRetries.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    PollWithRetries buildResult = PollWithRetries.builder()
        .maxRetries(3)
        .pollIntervalMillis(42L)
        .sleepInInterval(mock(ThreadSleep.class))
        .build();

    // Act and Assert
    assertEquals(buildResult, buildResult);
    int expectedHashCodeResult = buildResult.hashCode();
    assertEquals(expectedHashCodeResult, buildResult.hashCode());
  }

  /**
   * Test {@link PollWithRetries#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link PollWithRetries#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean PollWithRetries.equals(Object)", "int PollWithRetries.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    Builder builder = mock(Builder.class);
    when(builder.maxRetries(anyInt())).thenReturn(PollWithRetries.builder());
    PollWithRetries buildResult = builder.maxRetries(3)
        .pollIntervalMillis(42L)
        .sleepInInterval(mock(ThreadSleep.class))
        .build();
    PollWithRetries buildResult2 = PollWithRetries.builder()
        .maxRetries(3)
        .pollIntervalMillis(42L)
        .sleepInInterval(mock(ThreadSleep.class))
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link PollWithRetries#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link PollWithRetries#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean PollWithRetries.equals(Object)", "int PollWithRetries.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    Builder builder = mock(Builder.class);
    when(builder.pollIntervalMillis(anyLong())).thenReturn(PollWithRetries.builder());
    Builder builder2 = mock(Builder.class);
    when(builder2.maxRetries(anyInt())).thenReturn(builder);
    PollWithRetries buildResult = builder2.maxRetries(3)
        .pollIntervalMillis(42L)
        .sleepInInterval(mock(ThreadSleep.class))
        .build();
    PollWithRetries buildResult2 = PollWithRetries.builder()
        .maxRetries(3)
        .pollIntervalMillis(42L)
        .sleepInInterval(mock(ThreadSleep.class))
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link PollWithRetries#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link PollWithRetries#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean PollWithRetries.equals(Object)", "int PollWithRetries.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange
    PollWithRetries buildResult = PollWithRetries.builder()
        .maxRetries(3)
        .pollIntervalMillis(42L)
        .sleepInInterval(mock(ThreadSleep.class))
        .build();

    // Act and Assert
    assertNotEquals(buildResult, null);
  }

  /**
   * Test {@link PollWithRetries#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link PollWithRetries#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean PollWithRetries.equals(Object)", "int PollWithRetries.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange
    PollWithRetries buildResult = PollWithRetries.builder()
        .maxRetries(3)
        .pollIntervalMillis(42L)
        .sleepInInterval(mock(ThreadSleep.class))
        .build();

    // Act and Assert
    assertNotEquals(buildResult, "Different type to PollWithRetries");
  }
}
