package sleeper.core.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.function.Supplier;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.statestore.transactionlog.TransactionLogStateStore;

class StaticRateLimitDiffblueTest {
  /**
   * Test {@link StaticRateLimit#requestOrGetLast(Supplier)}.
   * <p>
   * Method under test: {@link StaticRateLimit#requestOrGetLast(Supplier)}
   */
  @Test
  @DisplayName("Test requestOrGetLast(Supplier)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Object StaticRateLimit.requestOrGetLast(Supplier)"})
  void testRequestOrGetLast() {
    // Arrange
    Supplier<Instant> timeSupplier = mock(Supplier.class);
    when(timeSupplier.get()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    StaticRateLimit<Object> withWaitBetweenRequestsResult = StaticRateLimit
        .withWaitBetweenRequests(TransactionLogStateStore.DEFAULT_TIME_BETWEEN_SNAPSHOT_CHECKS, timeSupplier);
    Supplier<Object> request = mock(Supplier.class);
    when(request.get()).thenReturn("Get");

    // Act
    Object actualRequestOrGetLastResult = withWaitBetweenRequestsResult.requestOrGetLast(request);

    // Assert
    verify(request).get();
    verify(timeSupplier).get();
    assertEquals("Get", actualRequestOrGetLastResult);
  }
}
