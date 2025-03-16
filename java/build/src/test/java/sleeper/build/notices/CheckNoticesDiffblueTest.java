package sleeper.build.notices;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.build.notices.CheckNotices.DependencyMatch;
import sleeper.build.notices.DependencyVersions.Dependency;
import sleeper.build.notices.DependencyVersions.DependencyId;
import sleeper.build.notices.DependencyVersions.Version;

class CheckNoticesDiffblueTest {
  /**
   * Test DependencyMatch {@link DependencyMatch#buildMessage()}.
   * <p>
   * Method under test: {@link DependencyMatch#buildMessage()}
   */
  @Test
  @DisplayName("Test DependencyMatch buildMessage()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Optional DependencyMatch.buildMessage()"})
  void testDependencyMatchBuildMessage() {
    // Arrange
    ArrayList<Version> versions = new ArrayList<>();
    versions.add(new Version("1.0.2", 1));
    Dependency dependency = new Dependency(new DependencyId("42", "42"), versions);

    HashSet<String> versionsMatched = new HashSet<>();

    // Act
    Optional<String> actualBuildMessageResult = (new DependencyMatch(dependency, true, true, versionsMatched,
        new ArrayList<>())).buildMessage();

    // Assert
    assertEquals("Dependency versions did not match: 42:42:1.0.2", actualBuildMessageResult.get());
    assertTrue(actualBuildMessageResult.isPresent());
  }

  /**
   * Test DependencyMatch {@link DependencyMatch#buildMessage()}.
   * <p>
   * Method under test: {@link DependencyMatch#buildMessage()}
   */
  @Test
  @DisplayName("Test DependencyMatch buildMessage()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Optional DependencyMatch.buildMessage()"})
  void testDependencyMatchBuildMessage2() {
    // Arrange
    DependencyId id = new DependencyId("42", "42");

    Dependency dependency = new Dependency(id, new ArrayList<>());

    HashSet<String> versionsMatched = new HashSet<>();

    // Act
    Optional<String> actualBuildMessageResult = (new DependencyMatch(dependency, true, false, versionsMatched,
        new ArrayList<>())).buildMessage();

    // Assert
    assertEquals("Dependency artifact ID not matched: 42:42:", actualBuildMessageResult.get());
    assertTrue(actualBuildMessageResult.isPresent());
  }

  /**
   * Test DependencyMatch {@link DependencyMatch#buildMessage()}.
   * <ul>
   *   <li>Then return {@link Optional#get()} is {@code Dependency not found: 42:42:}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DependencyMatch#buildMessage()}
   */
  @Test
  @DisplayName("Test DependencyMatch buildMessage(); then return get() is 'Dependency not found: 42:42:'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Optional DependencyMatch.buildMessage()"})
  void testDependencyMatchBuildMessage_thenReturnGetIsDependencyNotFound4242() {
    // Arrange
    DependencyId id = new DependencyId("42", "42");

    Dependency dependency = new Dependency(id, new ArrayList<>());

    HashSet<String> versionsMatched = new HashSet<>();

    // Act
    Optional<String> actualBuildMessageResult = (new DependencyMatch(dependency, false, true, versionsMatched,
        new ArrayList<>())).buildMessage();

    // Assert
    assertEquals("Dependency not found: 42:42:", actualBuildMessageResult.get());
    assertTrue(actualBuildMessageResult.isPresent());
  }

  /**
   * Test DependencyMatch {@link DependencyMatch#buildMessage()}.
   * <ul>
   *   <li>Then return not Present.</li>
   * </ul>
   * <p>
   * Method under test: {@link DependencyMatch#buildMessage()}
   */
  @Test
  @DisplayName("Test DependencyMatch buildMessage(); then return not Present")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Optional DependencyMatch.buildMessage()"})
  void testDependencyMatchBuildMessage_thenReturnNotPresent() {
    // Arrange
    DependencyId id = new DependencyId("42", "42");

    Dependency dependency = new Dependency(id, new ArrayList<>());

    HashSet<String> versionsMatched = new HashSet<>();

    // Act and Assert
    assertFalse(
        (new DependencyMatch(dependency, true, true, versionsMatched, new ArrayList<>())).buildMessage().isPresent());
  }

  /**
   * Test {@link CheckNotices#findProblemsInNotices(String, DependencyVersions)}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()}.</li>
   *   <li>When {@code Notices}.</li>
   *   <li>Then return Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link CheckNotices#findProblemsInNotices(String, DependencyVersions)}
   */
  @Test
  @DisplayName("Test findProblemsInNotices(String, DependencyVersions); given ArrayList(); when 'Notices'; then return Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List CheckNotices.findProblemsInNotices(String, DependencyVersions)"})
  void testFindProblemsInNotices_givenArrayList_whenNotices_thenReturnEmpty() {
    // Arrange
    DependencyVersions versions = mock(DependencyVersions.class);
    when(versions.getDependencies()).thenReturn(new ArrayList<>());

    // Act
    List<String> actualFindProblemsInNoticesResult = CheckNotices.findProblemsInNotices("Notices", versions);

    // Assert
    verify(versions).getDependencies();
    assertTrue(actualFindProblemsInNoticesResult.isEmpty());
  }

  /**
   * Test {@link CheckNotices#findProblemsInNotices(String, DependencyVersions)}.
   * <ul>
   *   <li>Then return first is {@code Dependency not present in pom.xml: U:U:U}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CheckNotices#findProblemsInNotices(String, DependencyVersions)}
   */
  @Test
  @DisplayName("Test findProblemsInNotices(String, DependencyVersions); then return first is 'Dependency not present in pom.xml: U:U:U'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List CheckNotices.findProblemsInNotices(String, DependencyVersions)"})
  void testFindProblemsInNotices_thenReturnFirstIsDependencyNotPresentInPomXmlUUU() {
    // Arrange
    DependencyVersions versions = mock(DependencyVersions.class);
    when(versions.getDependencies()).thenReturn(new ArrayList<>());

    // Act
    List<String> actualFindProblemsInNoticesResult = CheckNotices.findProblemsInNotices("U:U:U", versions);

    // Assert
    verify(versions).getDependencies();
    assertEquals(1, actualFindProblemsInNoticesResult.size());
    assertEquals("Dependency not present in pom.xml: U:U:U", actualFindProblemsInNoticesResult.get(0));
  }

  /**
   * Test {@link CheckNotices#findProblemsInNotices(String, DependencyVersions)}.
   * <ul>
   *   <li>Then return second is {@code Dependency not found: 42:42:}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CheckNotices#findProblemsInNotices(String, DependencyVersions)}
   */
  @Test
  @DisplayName("Test findProblemsInNotices(String, DependencyVersions); then return second is 'Dependency not found: 42:42:'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List CheckNotices.findProblemsInNotices(String, DependencyVersions)"})
  void testFindProblemsInNotices_thenReturnSecondIsDependencyNotFound4242() {
    // Arrange
    ArrayList<Dependency> dependencyList = new ArrayList<>();
    DependencyId id = new DependencyId("42", "42");

    dependencyList.add(new Dependency(id, new ArrayList<>()));
    DependencyId id2 = new DependencyId("42", "42");

    dependencyList.add(new Dependency(id2, new ArrayList<>()));
    DependencyVersions versions = mock(DependencyVersions.class);
    when(versions.getDependencies()).thenReturn(dependencyList);

    // Act
    List<String> actualFindProblemsInNoticesResult = CheckNotices.findProblemsInNotices("Notices", versions);

    // Assert
    verify(versions).getDependencies();
    assertEquals(2, actualFindProblemsInNoticesResult.size());
    assertEquals("Dependency not found: 42:42:", actualFindProblemsInNoticesResult.get(0));
    assertEquals("Dependency not found: 42:42:", actualFindProblemsInNoticesResult.get(1));
  }

  /**
   * Test {@link CheckNotices#findProblemsInNotices(String, DependencyVersions)}.
   * <ul>
   *   <li>Then return second is {@code Dependency not present in pom.xml: U:U:U}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CheckNotices#findProblemsInNotices(String, DependencyVersions)}
   */
  @Test
  @DisplayName("Test findProblemsInNotices(String, DependencyVersions); then return second is 'Dependency not present in pom.xml: U:U:U'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List CheckNotices.findProblemsInNotices(String, DependencyVersions)"})
  void testFindProblemsInNotices_thenReturnSecondIsDependencyNotPresentInPomXmlUUU() {
    // Arrange
    ArrayList<Dependency> dependencyList = new ArrayList<>();
    DependencyId id = new DependencyId("42", "42");

    dependencyList.add(new Dependency(id, new ArrayList<>()));
    DependencyVersions versions = mock(DependencyVersions.class);
    when(versions.getDependencies()).thenReturn(dependencyList);

    // Act
    List<String> actualFindProblemsInNoticesResult = CheckNotices.findProblemsInNotices("U:U:U", versions);

    // Assert
    verify(versions).getDependencies();
    assertEquals(2, actualFindProblemsInNoticesResult.size());
    assertEquals("Dependency not found: 42:42:", actualFindProblemsInNoticesResult.get(0));
    assertEquals("Dependency not present in pom.xml: U:U:U", actualFindProblemsInNoticesResult.get(1));
  }

  /**
   * Test {@link CheckNotices#findProblemsInNotices(String, DependencyVersions)}.
   * <ul>
   *   <li>Then return size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link CheckNotices#findProblemsInNotices(String, DependencyVersions)}
   */
  @Test
  @DisplayName("Test findProblemsInNotices(String, DependencyVersions); then return size is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List CheckNotices.findProblemsInNotices(String, DependencyVersions)"})
  void testFindProblemsInNotices_thenReturnSizeIsOne() {
    // Arrange
    ArrayList<Dependency> dependencyList = new ArrayList<>();
    DependencyId id = new DependencyId("42", "42");

    dependencyList.add(new Dependency(id, new ArrayList<>()));
    DependencyVersions versions = mock(DependencyVersions.class);
    when(versions.getDependencies()).thenReturn(dependencyList);

    // Act
    List<String> actualFindProblemsInNoticesResult = CheckNotices.findProblemsInNotices("Notices", versions);

    // Assert
    verify(versions).getDependencies();
    assertEquals(1, actualFindProblemsInNoticesResult.size());
    assertEquals("Dependency not found: 42:42:", actualFindProblemsInNoticesResult.get(0));
  }

  /**
   * Test {@link CheckNotices#findProblemsInNotices(String, DependencyVersions)}.
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CheckNotices#findProblemsInNotices(String, DependencyVersions)}
   */
  @Test
  @DisplayName("Test findProblemsInNotices(String, DependencyVersions); then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List CheckNotices.findProblemsInNotices(String, DependencyVersions)"})
  void testFindProblemsInNotices_thenThrowIllegalArgumentException() {
    // Arrange
    DependencyVersions versions = mock(DependencyVersions.class);
    when(versions.getDependencies()).thenThrow(new IllegalArgumentException("U:U:U"));

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> CheckNotices.findProblemsInNotices("Notices", versions));
    verify(versions).getDependencies();
  }

  /**
   * Test {@link CheckNotices#findProblemsInNotices(String, DependencyVersions)}.
   * <ul>
   *   <li>When builder build.</li>
   *   <li>Then return Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link CheckNotices#findProblemsInNotices(String, DependencyVersions)}
   */
  @Test
  @DisplayName("Test findProblemsInNotices(String, DependencyVersions); when builder build; then return Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List CheckNotices.findProblemsInNotices(String, DependencyVersions)"})
  void testFindProblemsInNotices_whenBuilderBuild_thenReturnEmpty() {
    // Arrange
    DependencyVersions versions = DependencyVersions.builder().build();

    // Act
    List<String> actualFindProblemsInNoticesResult = CheckNotices.findProblemsInNotices("Notices", versions);

    // Assert
    assertTrue(actualFindProblemsInNoticesResult.isEmpty());
  }
}
