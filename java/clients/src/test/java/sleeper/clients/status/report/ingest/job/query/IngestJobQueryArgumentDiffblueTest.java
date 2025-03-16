package sleeper.clients.status.report.ingest.job.query;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.clients.status.report.job.query.JobQuery;
import sleeper.clients.status.report.job.query.JobQuery.Type;

class IngestJobQueryArgumentDiffblueTest {
  /**
   * Test {@link IngestJobQueryArgument#readTypeArgument(String[], int)}.
   * <ul>
   *   <li>When array of {@link String} with {@code Args} and {@code -n}.</li>
   *   <li>Then return {@code REJECTED}.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobQueryArgument#readTypeArgument(String[], int)}
   */
  @Test
  @DisplayName("Test readTypeArgument(String[], int); when array of String with 'Args' and '-n'; then return 'REJECTED'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Type IngestJobQueryArgument.readTypeArgument(String[], int)"})
  void testReadTypeArgument_whenArrayOfStringWithArgsAndN_thenReturnRejected() {
    // Arrange, Act and Assert
    assertEquals(Type.REJECTED, IngestJobQueryArgument.readTypeArgument(new String[]{"Args", "-n"}, 1));
  }

  /**
   * Test {@link IngestJobQueryArgument#readTypeArgument(String[], int)}.
   * <ul>
   *   <li>When one.</li>
   *   <li>Then return {@code PROMPT}.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobQueryArgument#readTypeArgument(String[], int)}
   */
  @Test
  @DisplayName("Test readTypeArgument(String[], int); when one; then return 'PROMPT'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Type IngestJobQueryArgument.readTypeArgument(String[], int)"})
  void testReadTypeArgument_whenOne_thenReturnPrompt() {
    // Arrange, Act and Assert
    assertEquals(Type.PROMPT, IngestJobQueryArgument.readTypeArgument(new String[]{"Args"}, 1));
  }

  /**
   * Test {@link IngestJobQueryArgument#readTypeArgument(String[], int)}.
   * <ul>
   *   <li>When zero.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobQueryArgument#readTypeArgument(String[], int)}
   */
  @Test
  @DisplayName("Test readTypeArgument(String[], int); when zero; then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Type IngestJobQueryArgument.readTypeArgument(String[], int)"})
  void testReadTypeArgument_whenZero_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> IngestJobQueryArgument.readTypeArgument(new String[]{"Args"}, 0));
  }
}
