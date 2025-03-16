package sleeper.core.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class NumberFormatUtilsDiffblueTest {
  /**
   * Test {@link NumberFormatUtils#formatBytes(long)}.
   * <ul>
   *   <li>When {@code 1000000}.</li>
   *   <li>Then return {@code 1000000B (1.0MB)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NumberFormatUtils#formatBytes(long)}
   */
  @Test
  @DisplayName("Test formatBytes(long); when '1000000'; then return '1000000B (1.0MB)'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String NumberFormatUtils.formatBytes(long)"})
  void testFormatBytes_when1000000_thenReturn1000000b10mb() {
    // Arrange, Act and Assert
    assertEquals("1000000B (1.0MB)", NumberFormatUtils.formatBytes(1000000L));
  }

  /**
   * Test {@link NumberFormatUtils#formatBytes(long)}.
   * <ul>
   *   <li>When {@link Long#MAX_VALUE}.</li>
   *   <li>Then return {@code 9223372036854775807B (9,223,372TB)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NumberFormatUtils#formatBytes(long)}
   */
  @Test
  @DisplayName("Test formatBytes(long); when MAX_VALUE; then return '9223372036854775807B (9,223,372TB)'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String NumberFormatUtils.formatBytes(long)"})
  void testFormatBytes_whenMax_value_thenReturn9223372036854775807b9223372tb() {
    // Arrange, Act and Assert
    assertEquals("9223372036854775807B (9,223,372TB)", NumberFormatUtils.formatBytes(Long.MAX_VALUE));
  }

  /**
   * Test {@link NumberFormatUtils#formatBytes(long)}.
   * <ul>
   *   <li>When one thousand.</li>
   *   <li>Then return {@code 1000B (1.0KB)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NumberFormatUtils#formatBytes(long)}
   */
  @Test
  @DisplayName("Test formatBytes(long); when one thousand; then return '1000B (1.0KB)'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String NumberFormatUtils.formatBytes(long)"})
  void testFormatBytes_whenOneThousand_thenReturn1000b10kb() {
    // Arrange, Act and Assert
    assertEquals("1000B (1.0KB)", NumberFormatUtils.formatBytes(1000L));
  }

  /**
   * Test {@link NumberFormatUtils#formatBytes(long)}.
   * <ul>
   *   <li>When three.</li>
   *   <li>Then return {@code 3B}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NumberFormatUtils#formatBytes(long)}
   */
  @Test
  @DisplayName("Test formatBytes(long); when three; then return '3B'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String NumberFormatUtils.formatBytes(long)"})
  void testFormatBytes_whenThree_thenReturn3b() {
    // Arrange, Act and Assert
    assertEquals("3B", NumberFormatUtils.formatBytes(3L));
  }

  /**
   * Test {@link NumberFormatUtils#formatBytesAsHumanReadableString(long)}.
   * <ul>
   *   <li>When {@code 1000000}.</li>
   *   <li>Then return {@code 1.0MB}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NumberFormatUtils#formatBytesAsHumanReadableString(long)}
   */
  @Test
  @DisplayName("Test formatBytesAsHumanReadableString(long); when '1000000'; then return '1.0MB'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String NumberFormatUtils.formatBytesAsHumanReadableString(long)"})
  void testFormatBytesAsHumanReadableString_when1000000_thenReturn10mb() {
    // Arrange, Act and Assert
    assertEquals("1.0MB", NumberFormatUtils.formatBytesAsHumanReadableString(1000000L));
  }

  /**
   * Test {@link NumberFormatUtils#formatBytesAsHumanReadableString(long)}.
   * <ul>
   *   <li>When {@code 1000000000}.</li>
   *   <li>Then return {@code 1.0GB}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NumberFormatUtils#formatBytesAsHumanReadableString(long)}
   */
  @Test
  @DisplayName("Test formatBytesAsHumanReadableString(long); when '1000000000'; then return '1.0GB'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String NumberFormatUtils.formatBytesAsHumanReadableString(long)"})
  void testFormatBytesAsHumanReadableString_when1000000000_thenReturn10gb() {
    // Arrange, Act and Assert
    assertEquals("1.0GB", NumberFormatUtils.formatBytesAsHumanReadableString(1000000000L));
  }

  /**
   * Test {@link NumberFormatUtils#formatBytesAsHumanReadableString(long)}.
   * <ul>
   *   <li>When {@code 1000000000000}.</li>
   *   <li>Then return {@code 1TB}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NumberFormatUtils#formatBytesAsHumanReadableString(long)}
   */
  @Test
  @DisplayName("Test formatBytesAsHumanReadableString(long); when '1000000000000'; then return '1TB'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String NumberFormatUtils.formatBytesAsHumanReadableString(long)"})
  void testFormatBytesAsHumanReadableString_when1000000000000_thenReturn1tb() {
    // Arrange, Act and Assert
    assertEquals("1TB", NumberFormatUtils.formatBytesAsHumanReadableString(1000000000000L));
  }

  /**
   * Test {@link NumberFormatUtils#formatBytesAsHumanReadableString(long)}.
   * <ul>
   *   <li>When {@link Long#MAX_VALUE}.</li>
   *   <li>Then return {@code 9,223,372TB}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NumberFormatUtils#formatBytesAsHumanReadableString(long)}
   */
  @Test
  @DisplayName("Test formatBytesAsHumanReadableString(long); when MAX_VALUE; then return '9,223,372TB'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String NumberFormatUtils.formatBytesAsHumanReadableString(long)"})
  void testFormatBytesAsHumanReadableString_whenMax_value_thenReturn9223372tb() {
    // Arrange, Act and Assert
    assertEquals("9,223,372TB", NumberFormatUtils.formatBytesAsHumanReadableString(Long.MAX_VALUE));
  }

  /**
   * Test {@link NumberFormatUtils#formatBytesAsHumanReadableString(long)}.
   * <ul>
   *   <li>When one thousand.</li>
   *   <li>Then return {@code 1.0KB}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NumberFormatUtils#formatBytesAsHumanReadableString(long)}
   */
  @Test
  @DisplayName("Test formatBytesAsHumanReadableString(long); when one thousand; then return '1.0KB'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String NumberFormatUtils.formatBytesAsHumanReadableString(long)"})
  void testFormatBytesAsHumanReadableString_whenOneThousand_thenReturn10kb() {
    // Arrange, Act and Assert
    assertEquals("1.0KB", NumberFormatUtils.formatBytesAsHumanReadableString(1000L));
  }

  /**
   * Test {@link NumberFormatUtils#formatBytesAsHumanReadableString(long)}.
   * <ul>
   *   <li>When three.</li>
   *   <li>Then return {@code 3B}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NumberFormatUtils#formatBytesAsHumanReadableString(long)}
   */
  @Test
  @DisplayName("Test formatBytesAsHumanReadableString(long); when three; then return '3B'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String NumberFormatUtils.formatBytesAsHumanReadableString(long)"})
  void testFormatBytesAsHumanReadableString_whenThree_thenReturn3b() {
    // Arrange, Act and Assert
    assertEquals("3B", NumberFormatUtils.formatBytesAsHumanReadableString(3L));
  }

  /**
   * Test {@link NumberFormatUtils#countWithCommas(long)}.
   * <ul>
   *   <li>When one thousand.</li>
   *   <li>Then return {@code 1,000}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NumberFormatUtils#countWithCommas(long)}
   */
  @Test
  @DisplayName("Test countWithCommas(long); when one thousand; then return '1,000'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String NumberFormatUtils.countWithCommas(long)"})
  void testCountWithCommas_whenOneThousand_thenReturn1000() {
    // Arrange, Act and Assert
    assertEquals("1,000", NumberFormatUtils.countWithCommas(1000L));
  }

  /**
   * Test {@link NumberFormatUtils#countWithCommas(long)}.
   * <ul>
   *   <li>When three.</li>
   *   <li>Then return {@code 3}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NumberFormatUtils#countWithCommas(long)}
   */
  @Test
  @DisplayName("Test countWithCommas(long); when three; then return '3'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String NumberFormatUtils.countWithCommas(long)"})
  void testCountWithCommas_whenThree_thenReturn3() {
    // Arrange, Act and Assert
    assertEquals("3", NumberFormatUtils.countWithCommas(3L));
  }

  /**
   * Test {@link NumberFormatUtils#decimalWithCommas(String, double)}.
   * <ul>
   *   <li>When empty string.</li>
   *   <li>Then return empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link NumberFormatUtils#decimalWithCommas(String, double)}
   */
  @Test
  @DisplayName("Test decimalWithCommas(String, double); when empty string; then return empty string")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String NumberFormatUtils.decimalWithCommas(String, double)"})
  void testDecimalWithCommas_whenEmptyString_thenReturnEmptyString() {
    // Arrange, Act and Assert
    assertEquals("", NumberFormatUtils.decimalWithCommas("", 10.0d));
  }

  /**
   * Test {@link NumberFormatUtils#decimalWithCommas(String, double)}.
   * <ul>
   *   <li>When {@code Format Str}.</li>
   *   <li>Then return {@code F,orm,at ,Str}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NumberFormatUtils#decimalWithCommas(String, double)}
   */
  @Test
  @DisplayName("Test decimalWithCommas(String, double); when 'Format Str'; then return 'F,orm,at ,Str'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String NumberFormatUtils.decimalWithCommas(String, double)"})
  void testDecimalWithCommas_whenFormatStr_thenReturnFOrmAtStr() {
    // Arrange, Act and Assert
    assertEquals("F,orm,at ,Str", NumberFormatUtils.decimalWithCommas("Format Str", 10.0d));
  }
}
