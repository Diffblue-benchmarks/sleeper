package sleeper.clients.status.report.statestore;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.clients.status.report.statestore.PageThroughLogs.GetLogs;
import sleeper.clients.status.report.statestore.PageThroughLogs.Waiter;

class PageThroughLogsDiffblueTest {
  /**
   * Test {@link PageThroughLogs#getLogsInPeriod(Instant, Instant)}.
   * <ul>
   *   <li>Then return Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link PageThroughLogs#getLogsInPeriod(Instant, Instant)}
   */
  @Test
  @DisplayName("Test getLogsInPeriod(Instant, Instant); then return Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List PageThroughLogs.getLogsInPeriod(Instant, Instant)"})
  void testGetLogsInPeriod_thenReturnEmpty() throws InterruptedException {
    // Arrange
    GetLogs<LogEntry> getLogs = mock(GetLogs.class);
    when(getLogs.getLogsInPeriodWithLimit(Mockito.<Instant>any(), Mockito.<Instant>any(), anyInt()))
        .thenReturn(new ArrayList<>());
    PageThroughLogs<LogEntry> fromResult = PageThroughLogs.from(getLogs);
    Instant startTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    // Act
    List<LogEntry> actualLogsInPeriod = fromResult.getLogsInPeriod(startTime,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Assert
    verify(getLogs).getLogsInPeriodWithLimit(isA(Instant.class), isA(Instant.class), eq(10000));
    assertTrue(actualLogsInPeriod.isEmpty());
  }

  /**
   * Test {@link PageThroughLogs#getLogsInPeriod(Instant, Instant)}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PageThroughLogs#getLogsInPeriod(Instant, Instant)}
   */
  @Test
  @DisplayName("Test getLogsInPeriod(Instant, Instant); then throw IllegalStateException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List PageThroughLogs.getLogsInPeriod(Instant, Instant)"})
  void testGetLogsInPeriod_thenThrowIllegalStateException() throws InterruptedException {
    // Arrange
    GetLogs<LogEntry> getLogs = mock(GetLogs.class);
    when(getLogs.getLogsInPeriodWithLimit(Mockito.<Instant>any(), Mockito.<Instant>any(), anyInt()))
        .thenThrow(new IllegalStateException("foo"));
    PageThroughLogs<LogEntry> pageThroughLogs = new PageThroughLogs<>(3, PageThroughLogs.PAGE_MIN_AGE, getLogs,
        mock(Supplier.class), mock(Waiter.class));
    Instant startTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> pageThroughLogs.getLogsInPeriod(startTime,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    verify(getLogs).getLogsInPeriodWithLimit(isA(Instant.class), isA(Instant.class), eq(3));
  }
}
