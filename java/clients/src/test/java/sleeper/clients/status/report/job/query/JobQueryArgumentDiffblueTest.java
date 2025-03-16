package sleeper.clients.status.report.job.query;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.clients.status.report.job.query.JobQuery.Type;

class JobQueryArgumentDiffblueTest {
  /**
   * Test {@link JobQueryArgument#readTypeArgument(String[], int)}.
   * <ul>
   *   <li>When array of {@link String} with {@code Args} and {@code -u}.</li>
   *   <li>Then return {@code UNFINISHED}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JobQueryArgument#readTypeArgument(String[], int)}
   */
  @Test
  @DisplayName("Test readTypeArgument(String[], int); when array of String with 'Args' and '-u'; then return 'UNFINISHED'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Type JobQueryArgument.readTypeArgument(String[], int)"})
  void testReadTypeArgument_whenArrayOfStringWithArgsAndU_thenReturnUnfinished() {
    // Arrange, Act and Assert
    assertEquals(Type.UNFINISHED, JobQueryArgument.readTypeArgument(new String[]{"Args", "-u"}, 1));
  }

  /**
   * Test {@link JobQueryArgument#readTypeArgument(String[], int)}.
   * <ul>
   *   <li>When one.</li>
   *   <li>Then return {@code PROMPT}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JobQueryArgument#readTypeArgument(String[], int)}
   */
  @Test
  @DisplayName("Test readTypeArgument(String[], int); when one; then return 'PROMPT'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Type JobQueryArgument.readTypeArgument(String[], int)"})
  void testReadTypeArgument_whenOne_thenReturnPrompt() {
    // Arrange, Act and Assert
    assertEquals(Type.PROMPT, JobQueryArgument.readTypeArgument(new String[]{"Args"}, 1));
  }

  /**
   * Test {@link JobQueryArgument#readTypeArgument(String[], int)}.
   * <ul>
   *   <li>When zero.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JobQueryArgument#readTypeArgument(String[], int)}
   */
  @Test
  @DisplayName("Test readTypeArgument(String[], int); when zero; then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Type JobQueryArgument.readTypeArgument(String[], int)"})
  void testReadTypeArgument_whenZero_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> JobQueryArgument.readTypeArgument(new String[]{"Args"}, 0));
  }

  /**
   * Test {@link JobQueryArgument#readType(String)}.
   * <ul>
   *   <li>When {@code Query Type Str}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JobQueryArgument#readType(String)}
   */
  @Test
  @DisplayName("Test readType(String); when 'Query Type Str'; then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Type JobQueryArgument.readType(String)"})
  void testReadType_whenQueryTypeStr_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> JobQueryArgument.readType("Query Type Str"));
  }

  /**
   * Test {@link JobQueryArgument#readType(String)}.
   * <ul>
   *   <li>When {@code -u}.</li>
   *   <li>Then return {@code UNFINISHED}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JobQueryArgument#readType(String)}
   */
  @Test
  @DisplayName("Test readType(String); when '-u'; then return 'UNFINISHED'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Type JobQueryArgument.readType(String)"})
  void testReadType_whenU_thenReturnUnfinished() {
    // Arrange, Act and Assert
    assertEquals(Type.UNFINISHED, JobQueryArgument.readType("-u"));
  }
}
