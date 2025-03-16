package sleeper.build.notices;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.List;
import java.util.regex.Pattern;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class NoticeDeclarationDiffblueTest {
  /**
   * Test {@link NoticeDeclaration#from(int, String, String, String, String)}.
   * <p>
   * Method under test: {@link NoticeDeclaration#from(int, String, String, String, String)}
   */
  @Test
  @DisplayName("Test from(int, String, String, String, String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"NoticeDeclaration NoticeDeclaration.from(int, String, String, String, String)"})
  void testFrom() {
    // Arrange and Act
    NoticeDeclaration actualFromResult = NoticeDeclaration.from(10, "Declaration", "42", "42", "1.0.2");

    // Assert
    assertEquals("1.0.2", actualFromResult.version().pattern());
    assertEquals("42", actualFromResult.artifactId().pattern());
    assertEquals("42", actualFromResult.groupId().pattern());
    assertEquals("Declaration", actualFromResult.declaration());
    assertEquals(10, actualFromResult.number());
  }

  /**
   * Test {@link NoticeDeclaration#findDeclarations(String)}.
   * <ul>
   *   <li>When {@code Notices}.</li>
   *   <li>Then return Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link NoticeDeclaration#findDeclarations(String)}
   */
  @Test
  @DisplayName("Test findDeclarations(String); when 'Notices'; then return Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List NoticeDeclaration.findDeclarations(String)"})
  void testFindDeclarations_whenNotices_thenReturnEmpty() {
    // Arrange and Act
    List<NoticeDeclaration> actualFindDeclarationsResult = NoticeDeclaration.findDeclarations("Notices");

    // Assert
    assertTrue(actualFindDeclarationsResult.isEmpty());
  }

  /**
   * Test {@link NoticeDeclaration#findDeclarations(String)}.
   * <ul>
   *   <li>When {@code U:U:U}.</li>
   *   <li>Then return size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link NoticeDeclaration#findDeclarations(String)}
   */
  @Test
  @DisplayName("Test findDeclarations(String); when 'U:U:U'; then return size is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List NoticeDeclaration.findDeclarations(String)"})
  void testFindDeclarations_whenUUU_thenReturnSizeIsOne() {
    // Arrange and Act
    List<NoticeDeclaration> actualFindDeclarationsResult = NoticeDeclaration.findDeclarations("U:U:U");

    // Assert
    assertEquals(1, actualFindDeclarationsResult.size());
    NoticeDeclaration getResult = actualFindDeclarationsResult.get(0);
    assertEquals("U", getResult.artifactId().pattern());
    assertEquals("U", getResult.groupId().pattern());
    assertEquals("U", getResult.version().pattern());
    assertEquals("U:U:U", getResult.declaration());
    assertEquals(0, getResult.number());
  }

  /**
   * Test {@link NoticeDeclaration#unmatchedMessage()}.
   * <p>
   * Method under test: {@link NoticeDeclaration#unmatchedMessage()}
   */
  @Test
  @DisplayName("Test unmatchedMessage()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String NoticeDeclaration.unmatchedMessage()"})
  void testUnmatchedMessage() {
    // Arrange
    Pattern groupId = Pattern.compile(".*\\.txt");
    Pattern artifactId = Pattern.compile(".*\\.txt");

    // Act and Assert
    assertEquals("Dependency not present in pom.xml: Declaration",
        (new NoticeDeclaration(10, "Declaration", groupId, artifactId, Pattern.compile(".*\\.txt")))
            .unmatchedMessage());
  }
}
