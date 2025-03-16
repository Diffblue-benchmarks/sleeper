package sleeper.build.notices;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.build.notices.DependencyVersions.Builder;
import sleeper.build.notices.DependencyVersions.Dependency;
import sleeper.build.notices.DependencyVersions.DependencyId;
import sleeper.build.notices.DependencyVersions.Version;

class DependencyVersionsDiffblueTest {
  /**
   * Test Builder {@link Builder#build()}.
   * <p>
   * Method under test: {@link Builder#build()}
   */
  @Test
  @DisplayName("Test Builder build()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"DependencyVersions Builder.build()"})
  void testBuilderBuild() {
    // Arrange, Act and Assert
    assertTrue(DependencyVersions.builder().build().getDependencies().isEmpty());
  }

  /**
   * Test Builder {@link Builder#dependency(String, String, String)}.
   * <p>
   * Method under test: {@link Builder#dependency(String, String, String)}
   */
  @Test
  @DisplayName("Test Builder dependency(String, String, String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.dependency(String, String, String)"})
  void testBuilderDependency() {
    // Arrange
    Builder builderResult = DependencyVersions.builder();

    // Act
    Builder actualDependencyResult = builderResult.dependency("42", "42", "1.0.2");

    // Assert
    List<Dependency> dependencies = builderResult.build().getDependencies();
    assertEquals(1, dependencies.size());
    List<Version> versionsResult = dependencies.get(0).versions();
    assertEquals(1, versionsResult.size());
    Version getResult = versionsResult.get(0);
    assertEquals("1.0.2", getResult.version());
    assertEquals(1, getResult.major().intValue());
    assertSame(builderResult, actualDependencyResult);
  }

  /**
   * Test Dependency {@link Dependency#artifactId()}.
   * <ul>
   *   <li>Then return {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Dependency#artifactId()}
   */
  @Test
  @DisplayName("Test Dependency artifactId(); then return '42'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String Dependency.artifactId()"})
  void testDependencyArtifactId_thenReturn42() {
    // Arrange
    DependencyId id = new DependencyId("42", "42");

    // Act and Assert
    assertEquals("42", (new Dependency(id, new ArrayList<>())).artifactId());
  }

  /**
   * Test Dependency {@link Dependency#describe()}.
   * <ul>
   *   <li>Then return {@code 42:42:}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Dependency#describe()}
   */
  @Test
  @DisplayName("Test Dependency describe(); then return '42:42:'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String Dependency.describe()"})
  void testDependencyDescribe_thenReturn4242() {
    // Arrange
    DependencyId id = new DependencyId("42", "42");

    // Act and Assert
    assertEquals("42:42:", (new Dependency(id, new ArrayList<>())).describe());
  }

  /**
   * Test Dependency {@link Dependency#from(DependencyId, Set)}.
   * <ul>
   *   <li>Given {@code 42}.</li>
   *   <li>When {@link HashSet#HashSet()} add {@code 42}.</li>
   *   <li>Then return versions size is two.</li>
   * </ul>
   * <p>
   * Method under test: {@link Dependency#from(DependencyId, Set)}
   */
  @Test
  @DisplayName("Test Dependency from(DependencyId, Set); given '42'; when HashSet() add '42'; then return versions size is two")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Dependency Dependency.from(DependencyId, Set)"})
  void testDependencyFrom_given42_whenHashSetAdd42_thenReturnVersionsSizeIsTwo() {
    // Arrange
    DependencyId id = new DependencyId("42", "42");

    HashSet<String> versions = new HashSet<>();
    versions.add("42");
    versions.add("foo");

    // Act and Assert
    List<Version> versionsResult = Dependency.from(id, versions).versions();
    assertEquals(2, versionsResult.size());
    Version getResult = versionsResult.get(0);
    assertEquals("42", getResult.version());
    Version getResult2 = versionsResult.get(1);
    assertEquals("foo", getResult2.version());
    assertNull(getResult2.major());
    assertEquals(42, getResult.major().intValue());
  }

  /**
   * Test Dependency {@link Dependency#from(DependencyId, Set)}.
   * <ul>
   *   <li>Given {@code foo}.</li>
   *   <li>When {@link HashSet#HashSet()} add {@code foo}.</li>
   *   <li>Then return versions size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link Dependency#from(DependencyId, Set)}
   */
  @Test
  @DisplayName("Test Dependency from(DependencyId, Set); given 'foo'; when HashSet() add 'foo'; then return versions size is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Dependency Dependency.from(DependencyId, Set)"})
  void testDependencyFrom_givenFoo_whenHashSetAddFoo_thenReturnVersionsSizeIsOne() {
    // Arrange
    DependencyId id = new DependencyId("42", "42");

    HashSet<String> versions = new HashSet<>();
    versions.add("foo");

    // Act
    Dependency actualFromResult = Dependency.from(id, versions);

    // Assert
    List<Version> versionsResult = actualFromResult.versions();
    assertEquals(1, versionsResult.size());
    Version getResult = versionsResult.get(0);
    assertEquals("foo", getResult.version());
    assertNull(getResult.major());
    assertSame(id, actualFromResult.id());
  }

  /**
   * Test Dependency {@link Dependency#from(DependencyId, Set)}.
   * <ul>
   *   <li>When {@link HashSet#HashSet()}.</li>
   *   <li>Then return versions Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link Dependency#from(DependencyId, Set)}
   */
  @Test
  @DisplayName("Test Dependency from(DependencyId, Set); when HashSet(); then return versions Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Dependency Dependency.from(DependencyId, Set)"})
  void testDependencyFrom_whenHashSet_thenReturnVersionsEmpty() {
    // Arrange
    DependencyId id = new DependencyId("42", "42");

    // Act
    Dependency actualFromResult = Dependency.from(id, new HashSet<>());

    // Assert
    assertTrue(actualFromResult.versions().isEmpty());
    assertSame(id, actualFromResult.id());
  }

  /**
   * Test Dependency {@link Dependency#groupIdAndArtifactId()}.
   * <ul>
   *   <li>Then return {@code 42:42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Dependency#groupIdAndArtifactId()}
   */
  @Test
  @DisplayName("Test Dependency groupIdAndArtifactId(); then return '42:42'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String Dependency.groupIdAndArtifactId()"})
  void testDependencyGroupIdAndArtifactId_thenReturn4242() {
    // Arrange
    DependencyId id = new DependencyId("42", "42");

    // Act and Assert
    assertEquals("42:42", (new Dependency(id, new ArrayList<>())).groupIdAndArtifactId());
  }

  /**
   * Test Dependency {@link Dependency#groupId()}.
   * <ul>
   *   <li>Then return {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Dependency#groupId()}
   */
  @Test
  @DisplayName("Test Dependency groupId(); then return '42'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String Dependency.groupId()"})
  void testDependencyGroupId_thenReturn42() {
    // Arrange
    DependencyId id = new DependencyId("42", "42");

    // Act and Assert
    assertEquals("42", (new Dependency(id, new ArrayList<>())).groupId());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link DependencyVersions#toString()}
   *   <li>{@link DependencyVersions#getDependencies()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List DependencyVersions.getDependencies()", "String DependencyVersions.toString()"})
  void testGettersAndSetters() {
    // Arrange
    DependencyVersions buildResult = DependencyVersions.builder().build();

    // Act
    String actualToStringResult = buildResult.toString();

    // Assert
    assertEquals("DependencyVersions{dependencies=[]}", actualToStringResult);
    assertTrue(buildResult.getDependencies().isEmpty());
  }

  /**
   * Test {@link DependencyVersions#equals(Object)}, and {@link DependencyVersions#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link DependencyVersions#equals(Object)}
   *   <li>{@link DependencyVersions#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean DependencyVersions.equals(Object)", "int DependencyVersions.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    DependencyVersions buildResult = DependencyVersions.builder().build();
    DependencyVersions buildResult2 = DependencyVersions.builder().build();

    // Act and Assert
    assertEquals(buildResult, buildResult2);
    int expectedHashCodeResult = buildResult.hashCode();
    assertEquals(expectedHashCodeResult, buildResult2.hashCode());
  }

  /**
   * Test {@link DependencyVersions#equals(Object)}, and {@link DependencyVersions#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link DependencyVersions#equals(Object)}
   *   <li>{@link DependencyVersions#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean DependencyVersions.equals(Object)", "int DependencyVersions.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    DependencyVersions buildResult = DependencyVersions.builder().build();

    // Act and Assert
    assertEquals(buildResult, buildResult);
    int expectedHashCodeResult = buildResult.hashCode();
    assertEquals(expectedHashCodeResult, buildResult.hashCode());
  }

  /**
   * Test {@link DependencyVersions#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link DependencyVersions#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean DependencyVersions.equals(Object)", "int DependencyVersions.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange
    DependencyVersions buildResult = DependencyVersions.builder().build();

    // Act and Assert
    assertNotEquals(buildResult, null);
  }

  /**
   * Test {@link DependencyVersions#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link DependencyVersions#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean DependencyVersions.equals(Object)", "int DependencyVersions.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange
    DependencyVersions buildResult = DependencyVersions.builder().build();

    // Act and Assert
    assertNotEquals(buildResult, "Different type to DependencyVersions");
  }

  /**
   * Test Version {@link Version#parse(String)}.
   * <ul>
   *   <li>When {@code 1.0.2}.</li>
   *   <li>Then return version is {@code 1.0.2}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Version#parse(String)}
   */
  @Test
  @DisplayName("Test Version parse(String); when '1.0.2'; then return version is '1.0.2'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Version Version.parse(String)"})
  void testVersionParse_when102_thenReturnVersionIs102() {
    // Arrange and Act
    Version actualParseResult = Version.parse("1.0.2");

    // Assert
    assertEquals("1.0.2", actualParseResult.version());
    assertEquals(1, actualParseResult.major().intValue());
  }

  /**
   * Test Version {@link Version#parse(String)}.
   * <ul>
   *   <li>When {@code \.}.</li>
   *   <li>Then return version is {@code \.}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Version#parse(String)}
   */
  @Test
  @DisplayName("Test Version parse(String); when '\\.'; then return version is '\\.'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Version Version.parse(String)"})
  void testVersionParse_whenBackslashDot_thenReturnVersionIsBackslashDot() {
    // Arrange and Act
    Version actualParseResult = Version.parse("\\.");

    // Assert
    assertEquals("\\.", actualParseResult.version());
    assertNull(actualParseResult.major());
  }
}
