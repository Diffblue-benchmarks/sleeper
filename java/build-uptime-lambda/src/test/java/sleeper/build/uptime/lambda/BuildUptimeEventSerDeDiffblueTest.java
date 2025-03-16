package sleeper.build.uptime.lambda;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class BuildUptimeEventSerDeDiffblueTest {
  /**
   * Test {@link BuildUptimeEventSerDe#fromJson(InputStream)}.
   * <ul>
   *   <li>When {@link ByteArrayInputStream#ByteArrayInputStream(byte[])} with empty array of {@code byte}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BuildUptimeEventSerDe#fromJson(InputStream)}
   */
  @Test
  @DisplayName("Test fromJson(InputStream); when ByteArrayInputStream(byte[]) with empty array of byte; then return 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"BuildUptimeEvent BuildUptimeEventSerDe.fromJson(InputStream)"})
  void testFromJson_whenByteArrayInputStreamWithEmptyArrayOfByte_thenReturnNull() {
    // Arrange
    BuildUptimeEventSerDe buildUptimeEventSerDe = new BuildUptimeEventSerDe();

    // Act and Assert
    assertNull(buildUptimeEventSerDe.fromJson(new ByteArrayInputStream(new byte[]{})));
  }

  /**
   * Test {@link BuildUptimeEventSerDe#toJson(BuildUptimeEvent)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BuildUptimeEventSerDe#toJson(BuildUptimeEvent)}
   */
  @Test
  @DisplayName("Test toJson(BuildUptimeEvent); when 'null'; then return 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.lang.String BuildUptimeEventSerDe.toJson(BuildUptimeEvent)"})
  void testToJson_whenNull_thenReturnNull() {
    // Arrange, Act and Assert
    assertEquals("null", (new BuildUptimeEventSerDe()).toJson(null));
  }

  /**
   * Test new {@link BuildUptimeEventSerDe} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link BuildUptimeEventSerDe}
   */
  @Test
  @DisplayName("Test new BuildUptimeEventSerDe (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void BuildUptimeEventSerDe.<init>()"})
  void testNewBuildUptimeEventSerDe() {
    // Arrange, Act and Assert
    assertEquals("null", (new BuildUptimeEventSerDe()).toJson(null));
  }
}
