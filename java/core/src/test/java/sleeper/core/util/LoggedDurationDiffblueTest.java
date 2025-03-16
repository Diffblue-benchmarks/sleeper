package sleeper.core.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.statestore.transactionlog.TransactionLogStateStore;

class LoggedDurationDiffblueTest {
  /**
   * Test {@link LoggedDuration#withFullOutput(Duration)} with {@code duration}.
   * <p>
   * Method under test: {@link LoggedDuration#withFullOutput(Duration)}
   */
  @Test
  @DisplayName("Test withFullOutput(Duration) with 'duration'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"LoggedDuration LoggedDuration.withFullOutput(Duration)"})
  void testWithFullOutputWithDuration() {
    // Arrange, Act and Assert
    assertEquals(60L,
        LoggedDuration.withFullOutput(TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS).getSeconds());
  }

  /**
   * Test {@link LoggedDuration#withFullOutput(Instant, Instant)} with {@code start}, {@code end}.
   * <p>
   * Method under test: {@link LoggedDuration#withFullOutput(Instant, Instant)}
   */
  @Test
  @DisplayName("Test withFullOutput(Instant, Instant) with 'start', 'end'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"LoggedDuration LoggedDuration.withFullOutput(Instant, Instant)"})
  void testWithFullOutputWithStartEnd() {
    // Arrange
    Instant start = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    // Act and Assert
    assertEquals(0L,
        LoggedDuration.withFullOutput(start, LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
            .getSeconds());
  }

  /**
   * Test {@link LoggedDuration#withShortOutput(Duration)} with {@code duration}.
   * <p>
   * Method under test: {@link LoggedDuration#withShortOutput(Duration)}
   */
  @Test
  @DisplayName("Test withShortOutput(Duration) with 'duration'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"LoggedDuration LoggedDuration.withShortOutput(Duration)"})
  void testWithShortOutputWithDuration() {
    // Arrange, Act and Assert
    assertEquals(60L,
        LoggedDuration.withShortOutput(TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS).getSeconds());
  }

  /**
   * Test {@link LoggedDuration#withShortOutput(Instant, Instant)} with {@code start}, {@code end}.
   * <p>
   * Method under test: {@link LoggedDuration#withShortOutput(Instant, Instant)}
   */
  @Test
  @DisplayName("Test withShortOutput(Instant, Instant) with 'start', 'end'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"LoggedDuration LoggedDuration.withShortOutput(Instant, Instant)"})
  void testWithShortOutputWithStartEnd() {
    // Arrange
    Instant start = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    // Act and Assert
    assertEquals(0L,
        LoggedDuration
            .withShortOutput(start, LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
            .getSeconds());
  }

  /**
   * Test {@link LoggedDuration#getSeconds()}.
   * <ul>
   *   <li>Then return sixty.</li>
   * </ul>
   * <p>
   * Method under test: {@link LoggedDuration#getSeconds()}
   */
  @Test
  @DisplayName("Test getSeconds(); then return sixty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"long LoggedDuration.getSeconds()"})
  void testGetSeconds_thenReturnSixty() {
    // Arrange, Act and Assert
    assertEquals(60L,
        LoggedDuration.withFullOutput(TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS).getSeconds());
  }

  /**
   * Test {@link LoggedDuration#toString()}.
   * <ul>
   *   <li>Then return {@code 0 seconds}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LoggedDuration#toString()}
   */
  @Test
  @DisplayName("Test toString(); then return '0 seconds'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.lang.String LoggedDuration.toString()"})
  void testToString_thenReturn0Seconds() {
    // Arrange, Act and Assert
    assertEquals("0 seconds",
        LoggedDuration.withFullOutput(TransactionLogStateStore.DEFAULT_TIME_BETWEEN_TRANSACTION_CHECKS).toString());
  }

  /**
   * Test {@link LoggedDuration#toString()}.
   * <ul>
   *   <li>Then return {@code 0s}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LoggedDuration#toString()}
   */
  @Test
  @DisplayName("Test toString(); then return '0s'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.lang.String LoggedDuration.toString()"})
  void testToString_thenReturn0s() {
    // Arrange, Act and Assert
    assertEquals("0s",
        LoggedDuration.withShortOutput(TransactionLogStateStore.DEFAULT_TIME_BETWEEN_TRANSACTION_CHECKS).toString());
  }

  /**
   * Test {@link LoggedDuration#toString()}.
   * <ul>
   *   <li>Then return {@code 1 minute 0 seconds}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LoggedDuration#toString()}
   */
  @Test
  @DisplayName("Test toString(); then return '1 minute 0 seconds'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.lang.String LoggedDuration.toString()"})
  void testToString_thenReturn1Minute0Seconds() {
    // Arrange, Act and Assert
    assertEquals("1 minute 0 seconds",
        LoggedDuration.withFullOutput(TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS).toString());
  }

  /**
   * Test {@link LoggedDuration#toString()}.
   * <ul>
   *   <li>Then return {@code 1m 0s}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LoggedDuration#toString()}
   */
  @Test
  @DisplayName("Test toString(); then return '1m 0s'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.lang.String LoggedDuration.toString()"})
  void testToString_thenReturn1m0s() {
    // Arrange, Act and Assert
    assertEquals("1m 0s",
        LoggedDuration.withShortOutput(TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS).toString());
  }
}
