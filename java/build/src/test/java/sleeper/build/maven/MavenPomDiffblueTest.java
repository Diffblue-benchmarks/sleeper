package sleeper.build.maven;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.json.JsonMapper.Builder;
import java.io.FileDescriptor;
import java.io.FileReader;
import java.io.Reader;
import java.io.StringReader;
import java.io.UncheckedIOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.build.maven.MavenPom.Build;
import sleeper.build.maven.MavenPom.ChildModule;
import sleeper.build.maven.MavenPom.Dependency;
import sleeper.build.maven.MavenPom.DependencyManagement;
import sleeper.build.maven.MavenPom.ParentRef;
import sleeper.build.maven.MavenPom.Plugin;
import sleeper.build.maven.MavenPom.PluginManagement;

class MavenPomDiffblueTest {
  /**
   * Test Build getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Build#getPluginManagement()}
   *   <li>{@link Build#getPlugins()}
   * </ul>
   */
  @Test
  @DisplayName("Test Build getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"PluginManagement Build.getPluginManagement()", "List Build.getPlugins()"})
  void testBuildGettersAndSetters() {
    // Arrange
    ArrayList<Plugin> plugins = new ArrayList<>();
    PluginManagement pluginManagement = new PluginManagement(new ArrayList<>());
    Build build = new Build(plugins, pluginManagement);

    // Act
    PluginManagement actualPluginManagement = build.getPluginManagement();
    List<Plugin> actualPlugins = build.getPlugins();

    // Assert
    assertTrue(actualPlugins.isEmpty());
    assertSame(plugins, actualPlugins);
    assertSame(pluginManagement, actualPluginManagement);
  }

  /**
   * Test Build {@link Build#Build(List, PluginManagement)}.
   * <ul>
   *   <li>Then return Plugins is {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Build#Build(List, PluginManagement)}
   */
  @Test
  @DisplayName("Test Build new Build(List, PluginManagement); then return Plugins is ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void Build.<init>(List, PluginManagement)"})
  void testBuildNewBuild_thenReturnPluginsIsArrayList() {
    // Arrange
    ArrayList<Plugin> plugins = new ArrayList<>();
    plugins.add(new Plugin("42", "42", "1.0.2", new ArrayList<>()));

    // Act and Assert
    assertSame(plugins, (new Build(plugins, new PluginManagement(new ArrayList<>()))).getPlugins());
  }

  /**
   * Test Build {@link Build#Build(List, PluginManagement)}.
   * <ul>
   *   <li>Then return Plugins size is two.</li>
   * </ul>
   * <p>
   * Method under test: {@link Build#Build(List, PluginManagement)}
   */
  @Test
  @DisplayName("Test Build new Build(List, PluginManagement); then return Plugins size is two")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void Build.<init>(List, PluginManagement)"})
  void testBuildNewBuild_thenReturnPluginsSizeIsTwo() {
    // Arrange
    ArrayList<Plugin> plugins = new ArrayList<>();
    plugins.add(new Plugin("42", "42", "1.0.2", new ArrayList<>()));
    Plugin plugin = new Plugin("42", "42", "1.0.2", new ArrayList<>());

    plugins.add(plugin);

    // Act and Assert
    List<Plugin> plugins2 = (new Build(plugins, new PluginManagement(new ArrayList<>()))).getPlugins();
    assertEquals(2, plugins2.size());
    assertSame(plugin, plugins2.get(1));
  }

  /**
   * Test Build {@link Build#Build(List, PluginManagement)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return PluginManagement Plugins Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link Build#Build(List, PluginManagement)}
   */
  @Test
  @DisplayName("Test Build new Build(List, PluginManagement); when ArrayList(); then return PluginManagement Plugins Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void Build.<init>(List, PluginManagement)"})
  void testBuildNewBuild_whenArrayList_thenReturnPluginManagementPluginsEmpty() {
    // Arrange
    ArrayList<Plugin> plugins = new ArrayList<>();
    PluginManagement pluginManagement = new PluginManagement(new ArrayList<>());

    // Act
    Build actualBuild = new Build(plugins, pluginManagement);

    // Assert
    assertTrue(actualBuild.getPlugins().isEmpty());
    PluginManagement pluginManagement2 = actualBuild.getPluginManagement();
    assertTrue(pluginManagement2.getPlugins().isEmpty());
    assertSame(pluginManagement, pluginManagement2);
  }

  /**
   * Test Build {@link Build#Build(List, PluginManagement)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return PluginManagement Plugins is Plugins.</li>
   * </ul>
   * <p>
   * Method under test: {@link Build#Build(List, PluginManagement)}
   */
  @Test
  @DisplayName("Test Build new Build(List, PluginManagement); when 'null'; then return PluginManagement Plugins is Plugins")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void Build.<init>(List, PluginManagement)"})
  void testBuildNewBuild_whenNull_thenReturnPluginManagementPluginsIsPlugins() {
    // Arrange and Act
    Build actualBuild = new Build(null, null);

    // Assert
    List<Plugin> plugins = actualBuild.getPlugins();
    assertTrue(plugins.isEmpty());
    assertSame(plugins, actualBuild.getPluginManagement().getPlugins());
  }

  /**
   * Test Dependency getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Dependency#Dependency(String, String, String, String, String)}
   *   <li>{@link Dependency#getArtifactId()}
   *   <li>{@link Dependency#getGroupId()}
   *   <li>{@link Dependency#getScope()}
   *   <li>{@link Dependency#getType()}
   *   <li>{@link Dependency#getVersion()}
   * </ul>
   */
  @Test
  @DisplayName("Test Dependency getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void Dependency.<init>(String, String, String, String, String)",
      "String Dependency.getArtifactId()", "String Dependency.getGroupId()", "String Dependency.getScope()",
      "String Dependency.getType()", "String Dependency.getVersion()"})
  void testDependencyGettersAndSetters() {
    // Arrange and Act
    Dependency actualDependency = new Dependency("42", "42", "1.0.2", "Type", "Scope");
    String actualArtifactId = actualDependency.getArtifactId();
    String actualGroupId = actualDependency.getGroupId();
    String actualScope = actualDependency.getScope();
    String actualType = actualDependency.getType();

    // Assert
    assertEquals("1.0.2", actualDependency.getVersion());
    assertEquals("42", actualArtifactId);
    assertEquals("42", actualGroupId);
    assertEquals("Scope", actualScope);
    assertEquals("Type", actualType);
  }

  /**
   * Test DependencyManagement {@link DependencyManagement#getDependencies()}.
   * <p>
   * Method under test: {@link DependencyManagement#getDependencies()}
   */
  @Test
  @DisplayName("Test DependencyManagement getDependencies()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List DependencyManagement.getDependencies()"})
  void testDependencyManagementGetDependencies() {
    // Arrange
    ArrayList<Dependency> dependencies = new ArrayList<>();

    // Act
    List<Dependency> actualDependencies = (new DependencyManagement(dependencies)).getDependencies();

    // Assert
    assertTrue(actualDependencies.isEmpty());
    assertSame(dependencies, actualDependencies);
  }

  /**
   * Test DependencyManagement {@link DependencyManagement#DependencyManagement(List)}.
   * <p>
   * Method under test: {@link DependencyManagement#DependencyManagement(List)}
   */
  @Test
  @DisplayName("Test DependencyManagement new DependencyManagement(List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DependencyManagement.<init>(List)"})
  void testDependencyManagementNewDependencyManagement() {
    // Arrange
    ArrayList<Dependency> dependencies = new ArrayList<>();
    dependencies.add(new Dependency("42", "42", "1.0.2", "Type", "Scope"));

    // Act and Assert
    assertSame(dependencies, (new DependencyManagement(dependencies)).getDependencies());
  }

  /**
   * Test DependencyManagement {@link DependencyManagement#DependencyManagement(List)}.
   * <ul>
   *   <li>Then return Dependencies size is two.</li>
   * </ul>
   * <p>
   * Method under test: {@link DependencyManagement#DependencyManagement(List)}
   */
  @Test
  @DisplayName("Test DependencyManagement new DependencyManagement(List); then return Dependencies size is two")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DependencyManagement.<init>(List)"})
  void testDependencyManagementNewDependencyManagement_thenReturnDependenciesSizeIsTwo() {
    // Arrange
    ArrayList<Dependency> dependencies = new ArrayList<>();
    dependencies.add(new Dependency("42", "42", "1.0.2", "Type", "Scope"));
    Dependency dependency = new Dependency("42", "42", "1.0.2", "Type", "Scope");

    dependencies.add(dependency);

    // Act and Assert
    List<Dependency> dependencies2 = (new DependencyManagement(dependencies)).getDependencies();
    assertEquals(2, dependencies2.size());
    assertSame(dependency, dependencies2.get(1));
  }

  /**
   * Test DependencyManagement {@link DependencyManagement#DependencyManagement(List)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DependencyManagement#DependencyManagement(List)}
   */
  @Test
  @DisplayName("Test DependencyManagement new DependencyManagement(List); when ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DependencyManagement.<init>(List)"})
  void testDependencyManagementNewDependencyManagement_whenArrayList() {
    // Arrange, Act and Assert
    assertTrue((new DependencyManagement(new ArrayList<>())).getDependencies().isEmpty());
  }

  /**
   * Test DependencyManagement {@link DependencyManagement#DependencyManagement(List)}.
   * <ul>
   *   <li>When {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DependencyManagement#DependencyManagement(List)}
   */
  @Test
  @DisplayName("Test DependencyManagement new DependencyManagement(List); when 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DependencyManagement.<init>(List)"})
  void testDependencyManagementNewDependencyManagement_whenNull() {
    // Arrange, Act and Assert
    assertTrue((new DependencyManagement(null)).getDependencies().isEmpty());
  }

  /**
   * Test {@link MavenPom#MavenPom(String, String, ParentRef, String, List, Map, List, DependencyManagement, Build)}.
   * <ul>
   *   <li>Given {@code 42}.</li>
   *   <li>When {@link ArrayList#ArrayList()} add {@code 42}.</li>
   *   <li>Then return Modules is {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MavenPom#MavenPom(String, String, ParentRef, String, List, Map, List, DependencyManagement, Build)}
   */
  @Test
  @DisplayName("Test new MavenPom(String, String, ParentRef, String, List, Map, List, DependencyManagement, Build); given '42'; when ArrayList() add '42'; then return Modules is ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void MavenPom.<init>(String, String, ParentRef, String, List, Map, List, DependencyManagement, Build)"})
  void testNewMavenPom_given42_whenArrayListAdd42_thenReturnModulesIsArrayList() {
    // Arrange
    ParentRef parent = new ParentRef("42");

    ArrayList<String> modules = new ArrayList<>();
    modules.add("42");
    modules.add("foo");
    HashMap<String, String> properties = new HashMap<>();
    ArrayList<Dependency> dependencies = new ArrayList<>();
    DependencyManagement dependencyManagement = new DependencyManagement(new ArrayList<>());
    ArrayList<Plugin> plugins = new ArrayList<>();
    Build build = new Build(plugins, new PluginManagement(new ArrayList<>()));

    // Act
    MavenPom actualMavenPom = new MavenPom("42", "42", parent, "Packaging", modules, properties, dependencies,
        dependencyManagement, build);

    // Assert
    assertTrue(actualMavenPom.getDependencies().isEmpty());
    DependencyManagement dependencyManagement2 = actualMavenPom.getDependencyManagement();
    assertTrue(dependencyManagement2.getDependencies().isEmpty());
    assertSame(modules, actualMavenPom.getModules());
    assertSame(build, actualMavenPom.getBuild());
    assertSame(dependencyManagement, dependencyManagement2);
  }

  /**
   * Test {@link MavenPom#MavenPom(String, String, ParentRef, String, List, Map, List, DependencyManagement, Build)}.
   * <ul>
   *   <li>Given {@code foo}.</li>
   *   <li>When {@link ArrayList#ArrayList()} add {@code foo}.</li>
   *   <li>Then return Modules is {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MavenPom#MavenPom(String, String, ParentRef, String, List, Map, List, DependencyManagement, Build)}
   */
  @Test
  @DisplayName("Test new MavenPom(String, String, ParentRef, String, List, Map, List, DependencyManagement, Build); given 'foo'; when ArrayList() add 'foo'; then return Modules is ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void MavenPom.<init>(String, String, ParentRef, String, List, Map, List, DependencyManagement, Build)"})
  void testNewMavenPom_givenFoo_whenArrayListAddFoo_thenReturnModulesIsArrayList() {
    // Arrange
    ParentRef parent = new ParentRef("42");

    ArrayList<String> modules = new ArrayList<>();
    modules.add("foo");
    HashMap<String, String> properties = new HashMap<>();
    ArrayList<Dependency> dependencies = new ArrayList<>();
    DependencyManagement dependencyManagement = new DependencyManagement(new ArrayList<>());
    ArrayList<Plugin> plugins = new ArrayList<>();
    Build build = new Build(plugins, new PluginManagement(new ArrayList<>()));

    // Act
    MavenPom actualMavenPom = new MavenPom("42", "42", parent, "Packaging", modules, properties, dependencies,
        dependencyManagement, build);

    // Assert
    assertTrue(actualMavenPom.getDependencies().isEmpty());
    DependencyManagement dependencyManagement2 = actualMavenPom.getDependencyManagement();
    assertTrue(dependencyManagement2.getDependencies().isEmpty());
    assertSame(modules, actualMavenPom.getModules());
    assertSame(build, actualMavenPom.getBuild());
    assertSame(dependencyManagement, dependencyManagement2);
  }

  /**
   * Test {@link MavenPom#MavenPom(String, String, ParentRef, String, List, Map, List, DependencyManagement, Build)}.
   * <ul>
   *   <li>Then return Dependencies is {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MavenPom#MavenPom(String, String, ParentRef, String, List, Map, List, DependencyManagement, Build)}
   */
  @Test
  @DisplayName("Test new MavenPom(String, String, ParentRef, String, List, Map, List, DependencyManagement, Build); then return Dependencies is ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void MavenPom.<init>(String, String, ParentRef, String, List, Map, List, DependencyManagement, Build)"})
  void testNewMavenPom_thenReturnDependenciesIsArrayList() {
    // Arrange
    ParentRef parent = new ParentRef("42");
    ArrayList<String> modules = new ArrayList<>();
    HashMap<String, String> properties = new HashMap<>();

    ArrayList<Dependency> dependencies = new ArrayList<>();
    dependencies.add(new Dependency("42", "42", "1.0.2", "Type", "Scope"));
    DependencyManagement dependencyManagement = new DependencyManagement(new ArrayList<>());
    ArrayList<Plugin> plugins = new ArrayList<>();

    // Act and Assert
    assertSame(dependencies, (new MavenPom("42", "42", parent, "Packaging", modules, properties, dependencies,
        dependencyManagement, new Build(plugins, new PluginManagement(new ArrayList<>())))).getDependencies());
  }

  /**
   * Test {@link MavenPom#MavenPom(String, String, ParentRef, String, List, Map, List, DependencyManagement, Build)}.
   * <ul>
   *   <li>Then return Dependencies size is two.</li>
   * </ul>
   * <p>
   * Method under test: {@link MavenPom#MavenPom(String, String, ParentRef, String, List, Map, List, DependencyManagement, Build)}
   */
  @Test
  @DisplayName("Test new MavenPom(String, String, ParentRef, String, List, Map, List, DependencyManagement, Build); then return Dependencies size is two")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void MavenPom.<init>(String, String, ParentRef, String, List, Map, List, DependencyManagement, Build)"})
  void testNewMavenPom_thenReturnDependenciesSizeIsTwo() {
    // Arrange
    ParentRef parent = new ParentRef("42");
    ArrayList<String> modules = new ArrayList<>();
    HashMap<String, String> properties = new HashMap<>();

    ArrayList<Dependency> dependencies = new ArrayList<>();
    dependencies.add(new Dependency("42", "42", "1.0.2", "Type", "Scope"));
    Dependency dependency = new Dependency("42", "42", "1.0.2", "Type", "Scope");

    dependencies.add(dependency);
    DependencyManagement dependencyManagement = new DependencyManagement(new ArrayList<>());
    ArrayList<Plugin> plugins = new ArrayList<>();

    // Act and Assert
    List<Dependency> dependencies2 = (new MavenPom("42", "42", parent, "Packaging", modules, properties, dependencies,
        dependencyManagement, new Build(plugins, new PluginManagement(new ArrayList<>())))).getDependencies();
    assertEquals(2, dependencies2.size());
    assertSame(dependency, dependencies2.get(1));
  }

  /**
   * Test {@link MavenPom#MavenPom(String, String, ParentRef, String, List, Map, List, DependencyManagement, Build)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return Modules Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link MavenPom#MavenPom(String, String, ParentRef, String, List, Map, List, DependencyManagement, Build)}
   */
  @Test
  @DisplayName("Test new MavenPom(String, String, ParentRef, String, List, Map, List, DependencyManagement, Build); when ArrayList(); then return Modules Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void MavenPom.<init>(String, String, ParentRef, String, List, Map, List, DependencyManagement, Build)"})
  void testNewMavenPom_whenArrayList_thenReturnModulesEmpty() {
    // Arrange
    ParentRef parent = new ParentRef("42");
    ArrayList<String> modules = new ArrayList<>();
    HashMap<String, String> properties = new HashMap<>();
    ArrayList<Dependency> dependencies = new ArrayList<>();
    DependencyManagement dependencyManagement = new DependencyManagement(new ArrayList<>());
    ArrayList<Plugin> plugins = new ArrayList<>();
    Build build = new Build(plugins, new PluginManagement(new ArrayList<>()));

    // Act
    MavenPom actualMavenPom = new MavenPom("42", "42", parent, "Packaging", modules, properties, dependencies,
        dependencyManagement, build);

    // Assert
    assertTrue(actualMavenPom.getDependencies().isEmpty());
    assertTrue(actualMavenPom.getModules().isEmpty());
    DependencyManagement dependencyManagement2 = actualMavenPom.getDependencyManagement();
    assertTrue(dependencyManagement2.getDependencies().isEmpty());
    assertSame(build, actualMavenPom.getBuild());
    assertSame(dependencyManagement, dependencyManagement2);
  }

  /**
   * Test {@link MavenPom#from(ObjectMapper, Path)} with {@code mapper}, {@code path}.
   * <p>
   * Method under test: {@link MavenPom#from(ObjectMapper, Path)}
   */
  @Test
  @DisplayName("Test from(ObjectMapper, Path) with 'mapper', 'path'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"MavenPom MavenPom.from(ObjectMapper, Path)"})
  void testFromWithMapperPath() {
    // Arrange, Act and Assert
    assertThrows(UncheckedIOException.class,
        () -> MavenPom.from((new Builder(JsonMapper.builder().findAndAddModules().build())).findAndAddModules().build(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt")));
  }

  /**
   * Test {@link MavenPom#from(ObjectMapper, Path)} with {@code mapper}, {@code path}.
   * <ul>
   *   <li>Then throw {@link UncheckedIOException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MavenPom#from(ObjectMapper, Path)}
   */
  @Test
  @DisplayName("Test from(ObjectMapper, Path) with 'mapper', 'path'; then throw UncheckedIOException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"MavenPom MavenPom.from(ObjectMapper, Path)"})
  void testFromWithMapperPath_thenThrowUncheckedIOException() {
    // Arrange, Act and Assert
    assertThrows(UncheckedIOException.class, () -> MavenPom.from(JsonMapper.builder().findAndAddModules().build(),
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt")));
  }

  /**
   * Test {@link MavenPom#from(Reader)} with {@code reader}.
   * <ul>
   *   <li>When {@link FileReader#FileReader(FileDescriptor)} with {@link FileDescriptor#FileDescriptor()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MavenPom#from(Reader)}
   */
  @Test
  @DisplayName("Test from(Reader) with 'reader'; when FileReader(FileDescriptor) with FileDescriptor()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"MavenPom MavenPom.from(Reader)"})
  void testFromWithReader_whenFileReaderWithFileDescriptor() {
    // Arrange, Act and Assert
    assertThrows(UncheckedIOException.class, () -> MavenPom.from(new FileReader(new FileDescriptor())));
  }

  /**
   * Test {@link MavenPom#from(Reader)} with {@code reader}.
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code foo}.</li>
   *   <li>Then throw {@link UncheckedIOException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MavenPom#from(Reader)}
   */
  @Test
  @DisplayName("Test from(Reader) with 'reader'; when StringReader(String) with 'foo'; then throw UncheckedIOException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"MavenPom MavenPom.from(Reader)"})
  void testFromWithReader_whenStringReaderWithFoo_thenThrowUncheckedIOException() {
    // Arrange, Act and Assert
    assertThrows(UncheckedIOException.class, () -> MavenPom.from(new StringReader("foo")));
  }

  /**
   * Test Plugin getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Plugin#getArtifactId()}
   *   <li>{@link Plugin#getDependencies()}
   *   <li>{@link Plugin#getGroupId()}
   *   <li>{@link Plugin#getVersion()}
   * </ul>
   */
  @Test
  @DisplayName("Test Plugin getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String Plugin.getArtifactId()", "List Plugin.getDependencies()", "String Plugin.getGroupId()",
      "String Plugin.getVersion()"})
  void testPluginGettersAndSetters() {
    // Arrange
    ArrayList<Dependency> dependencies = new ArrayList<>();
    Plugin plugin = new Plugin("42", "42", "1.0.2", dependencies);

    // Act
    String actualArtifactId = plugin.getArtifactId();
    List<Dependency> actualDependencies = plugin.getDependencies();
    String actualGroupId = plugin.getGroupId();

    // Assert
    assertEquals("1.0.2", plugin.getVersion());
    assertEquals("42", actualArtifactId);
    assertEquals("42", actualGroupId);
    assertTrue(actualDependencies.isEmpty());
    assertSame(dependencies, actualDependencies);
  }

  /**
   * Test PluginManagement {@link PluginManagement#getPlugins()}.
   * <p>
   * Method under test: {@link PluginManagement#getPlugins()}
   */
  @Test
  @DisplayName("Test PluginManagement getPlugins()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List PluginManagement.getPlugins()"})
  void testPluginManagementGetPlugins() {
    // Arrange
    ArrayList<Plugin> plugins = new ArrayList<>();

    // Act
    List<Plugin> actualPlugins = (new PluginManagement(plugins)).getPlugins();

    // Assert
    assertTrue(actualPlugins.isEmpty());
    assertSame(plugins, actualPlugins);
  }

  /**
   * Test PluginManagement {@link PluginManagement#PluginManagement(List)}.
   * <ul>
   *   <li>Then return Plugins is {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PluginManagement#PluginManagement(List)}
   */
  @Test
  @DisplayName("Test PluginManagement new PluginManagement(List); then return Plugins is ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void PluginManagement.<init>(List)"})
  void testPluginManagementNewPluginManagement_thenReturnPluginsIsArrayList() {
    // Arrange
    ArrayList<Plugin> plugins = new ArrayList<>();
    plugins.add(new Plugin("42", "42", "1.0.2", new ArrayList<>()));

    // Act and Assert
    assertSame(plugins, (new PluginManagement(plugins)).getPlugins());
  }

  /**
   * Test PluginManagement {@link PluginManagement#PluginManagement(List)}.
   * <ul>
   *   <li>Then return Plugins size is two.</li>
   * </ul>
   * <p>
   * Method under test: {@link PluginManagement#PluginManagement(List)}
   */
  @Test
  @DisplayName("Test PluginManagement new PluginManagement(List); then return Plugins size is two")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void PluginManagement.<init>(List)"})
  void testPluginManagementNewPluginManagement_thenReturnPluginsSizeIsTwo() {
    // Arrange
    ArrayList<Plugin> plugins = new ArrayList<>();
    plugins.add(new Plugin("42", "42", "1.0.2", new ArrayList<>()));
    Plugin plugin = new Plugin("42", "42", "1.0.2", new ArrayList<>());

    plugins.add(plugin);

    // Act and Assert
    List<Plugin> plugins2 = (new PluginManagement(plugins)).getPlugins();
    assertEquals(2, plugins2.size());
    assertSame(plugin, plugins2.get(1));
  }

  /**
   * Test PluginManagement {@link PluginManagement#PluginManagement(List)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return Plugins Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link PluginManagement#PluginManagement(List)}
   */
  @Test
  @DisplayName("Test PluginManagement new PluginManagement(List); when ArrayList(); then return Plugins Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void PluginManagement.<init>(List)"})
  void testPluginManagementNewPluginManagement_whenArrayList_thenReturnPluginsEmpty() {
    // Arrange, Act and Assert
    assertTrue((new PluginManagement(new ArrayList<>())).getPlugins().isEmpty());
  }

  /**
   * Test PluginManagement {@link PluginManagement#PluginManagement(List)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return Plugins Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link PluginManagement#PluginManagement(List)}
   */
  @Test
  @DisplayName("Test PluginManagement new PluginManagement(List); when 'null'; then return Plugins Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void PluginManagement.<init>(List)"})
  void testPluginManagementNewPluginManagement_whenNull_thenReturnPluginsEmpty() {
    // Arrange, Act and Assert
    assertTrue((new PluginManagement(null)).getPlugins().isEmpty());
  }

  /**
   * Test Plugin {@link Plugin#Plugin(String, String, String, List)}.
   * <ul>
   *   <li>Then return Dependencies is {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Plugin#Plugin(String, String, String, List)}
   */
  @Test
  @DisplayName("Test Plugin new Plugin(String, String, String, List); then return Dependencies is ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void Plugin.<init>(String, String, String, List)"})
  void testPluginNewPlugin_thenReturnDependenciesIsArrayList() {
    // Arrange
    ArrayList<Dependency> dependencies = new ArrayList<>();
    dependencies.add(new Dependency("42", "42", "1.0.2", "Type", "Scope"));

    // Act and Assert
    assertSame(dependencies, (new Plugin("42", "42", "1.0.2", dependencies)).getDependencies());
  }

  /**
   * Test Plugin {@link Plugin#Plugin(String, String, String, List)}.
   * <ul>
   *   <li>Then return Dependencies size is two.</li>
   * </ul>
   * <p>
   * Method under test: {@link Plugin#Plugin(String, String, String, List)}
   */
  @Test
  @DisplayName("Test Plugin new Plugin(String, String, String, List); then return Dependencies size is two")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void Plugin.<init>(String, String, String, List)"})
  void testPluginNewPlugin_thenReturnDependenciesSizeIsTwo() {
    // Arrange
    ArrayList<Dependency> dependencies = new ArrayList<>();
    dependencies.add(new Dependency("42", "42", "1.0.2", "Type", "Scope"));
    Dependency dependency = new Dependency("42", "42", "1.0.2", "Type", "Scope");

    dependencies.add(dependency);

    // Act and Assert
    List<Dependency> dependencies2 = (new Plugin("42", "42", "1.0.2", dependencies)).getDependencies();
    assertEquals(2, dependencies2.size());
    assertSame(dependency, dependencies2.get(1));
  }

  /**
   * Test Plugin {@link Plugin#Plugin(String, String, String, List)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return Version is {@code 1.0.2}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Plugin#Plugin(String, String, String, List)}
   */
  @Test
  @DisplayName("Test Plugin new Plugin(String, String, String, List); when ArrayList(); then return Version is '1.0.2'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void Plugin.<init>(String, String, String, List)"})
  void testPluginNewPlugin_whenArrayList_thenReturnVersionIs102() {
    // Arrange and Act
    Plugin actualPlugin = new Plugin("42", "42", "1.0.2", new ArrayList<>());

    // Assert
    assertEquals("1.0.2", actualPlugin.getVersion());
    assertEquals("42", actualPlugin.getArtifactId());
    assertEquals("42", actualPlugin.getGroupId());
    assertTrue(actualPlugin.getDependencies().isEmpty());
  }

  /**
   * Test Plugin {@link Plugin#Plugin(String, String, String, List)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return Version is {@code 1.0.2}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Plugin#Plugin(String, String, String, List)}
   */
  @Test
  @DisplayName("Test Plugin new Plugin(String, String, String, List); when 'null'; then return Version is '1.0.2'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void Plugin.<init>(String, String, String, List)"})
  void testPluginNewPlugin_whenNull_thenReturnVersionIs102() {
    // Arrange and Act
    Plugin actualPlugin = new Plugin("42", "42", "1.0.2", null);

    // Assert
    assertEquals("1.0.2", actualPlugin.getVersion());
    assertEquals("42", actualPlugin.getArtifactId());
    assertEquals("42", actualPlugin.getGroupId());
    assertTrue(actualPlugin.getDependencies().isEmpty());
  }

  /**
   * Test {@link MavenPom#readChildModules(ObjectMapper, Path)}.
   * <p>
   * Method under test: {@link MavenPom#readChildModules(ObjectMapper, Path)}
   */
  @Test
  @DisplayName("Test readChildModules(ObjectMapper, Path)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream MavenPom.readChildModules(ObjectMapper, Path)"})
  void testReadChildModules() {
    // Arrange
    ParentRef parent = new ParentRef("42");
    ArrayList<String> modules = new ArrayList<>();
    HashMap<String, String> properties = new HashMap<>();
    ArrayList<Dependency> dependencies = new ArrayList<>();
    DependencyManagement dependencyManagement = new DependencyManagement(new ArrayList<>());
    ArrayList<Plugin> plugins = new ArrayList<>();
    MavenPom mavenPom = new MavenPom("42", "42", parent, "Packaging", modules, properties, dependencies,
        dependencyManagement, new Build(plugins, new PluginManagement(new ArrayList<>())));

    // Act
    Stream<ChildModule> actualReadChildModulesResult = mavenPom.readChildModules(
        JsonMapper.builder().findAndAddModules().build(), Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));

    // Assert
    assertTrue(actualReadChildModulesResult.limit(5).collect(Collectors.toList()).isEmpty());
  }

  /**
   * Test {@link MavenPom#readDescendentModules(ObjectMapper, Path)}.
   * <p>
   * Method under test: {@link MavenPom#readDescendentModules(ObjectMapper, Path)}
   */
  @Test
  @DisplayName("Test readDescendentModules(ObjectMapper, Path)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream MavenPom.readDescendentModules(ObjectMapper, Path)"})
  void testReadDescendentModules() {
    // Arrange
    ParentRef parent = new ParentRef("42");
    ArrayList<String> modules = new ArrayList<>();
    HashMap<String, String> properties = new HashMap<>();
    ArrayList<Dependency> dependencies = new ArrayList<>();
    DependencyManagement dependencyManagement = new DependencyManagement(new ArrayList<>());
    ArrayList<Plugin> plugins = new ArrayList<>();
    MavenPom mavenPom = new MavenPom("42", "42", parent, "Packaging", modules, properties, dependencies,
        dependencyManagement, new Build(plugins, new PluginManagement(new ArrayList<>())));

    // Act
    Stream<ChildModule> actualReadDescendentModulesResult = mavenPom.readDescendentModules(
        JsonMapper.builder().findAndAddModules().build(), Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));

    // Assert
    assertTrue(actualReadDescendentModulesResult.limit(5).collect(Collectors.toList()).isEmpty());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link MavenPom#getArtifactId()}
   *   <li>{@link MavenPom#getBuild()}
   *   <li>{@link MavenPom#getDependencies()}
   *   <li>{@link MavenPom#getDependencyManagement()}
   *   <li>{@link MavenPom#getModules()}
   *   <li>{@link MavenPom#getPackaging()}
   *   <li>{@link MavenPom#getProperties()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String MavenPom.getArtifactId()", "Build MavenPom.getBuild()", "List MavenPom.getDependencies()",
      "DependencyManagement MavenPom.getDependencyManagement()", "List MavenPom.getModules()",
      "String MavenPom.getPackaging()", "Map MavenPom.getProperties()"})
  void testGettersAndSetters() {
    // Arrange
    ParentRef parent = new ParentRef("42");
    ArrayList<String> modules = new ArrayList<>();
    HashMap<String, String> properties = new HashMap<>();
    ArrayList<Dependency> dependencies = new ArrayList<>();
    DependencyManagement dependencyManagement = new DependencyManagement(new ArrayList<>());
    ArrayList<Plugin> plugins = new ArrayList<>();
    Build build = new Build(plugins, new PluginManagement(new ArrayList<>()));

    MavenPom mavenPom = new MavenPom("42", "42", parent, "Packaging", modules, properties, dependencies,
        dependencyManagement, build);

    // Act
    String actualArtifactId = mavenPom.getArtifactId();
    Build actualBuild = mavenPom.getBuild();
    List<Dependency> actualDependencies = mavenPom.getDependencies();
    DependencyManagement actualDependencyManagement = mavenPom.getDependencyManagement();
    List<String> actualModules = mavenPom.getModules();
    String actualPackaging = mavenPom.getPackaging();
    Map<String, String> actualProperties = mavenPom.getProperties();

    // Assert
    assertEquals("42", actualArtifactId);
    assertEquals("Packaging", actualPackaging);
    assertTrue(actualDependencies.isEmpty());
    assertTrue(actualModules.isEmpty());
    assertTrue(actualProperties.isEmpty());
    assertSame(dependencies, actualDependencies);
    assertSame(modules, actualModules);
    assertSame(properties, actualProperties);
    assertSame(build, actualBuild);
    assertSame(dependencyManagement, actualDependencyManagement);
  }

  /**
   * Test {@link MavenPom#getGroupId()}.
   * <p>
   * Method under test: {@link MavenPom#getGroupId()}
   */
  @Test
  @DisplayName("Test getGroupId()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String MavenPom.getGroupId()"})
  void testGetGroupId() {
    // Arrange
    ParentRef parent = new ParentRef("42");
    ArrayList<String> modules = new ArrayList<>();
    HashMap<String, String> properties = new HashMap<>();
    ArrayList<Dependency> dependencies = new ArrayList<>();
    DependencyManagement dependencyManagement = new DependencyManagement(new ArrayList<>());
    ArrayList<Plugin> plugins = new ArrayList<>();

    // Act and Assert
    assertEquals("42", (new MavenPom("42", "42", parent, "Packaging", modules, properties, dependencies,
        dependencyManagement, new Build(plugins, new PluginManagement(new ArrayList<>())))).getGroupId());
  }

  /**
   * Test {@link MavenPom#getGroupId()}.
   * <p>
   * Method under test: {@link MavenPom#getGroupId()}
   */
  @Test
  @DisplayName("Test getGroupId()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String MavenPom.getGroupId()"})
  void testGetGroupId2() {
    // Arrange
    ParentRef parent = new ParentRef("42");
    ArrayList<String> modules = new ArrayList<>();
    HashMap<String, String> properties = new HashMap<>();
    ArrayList<Dependency> dependencies = new ArrayList<>();
    DependencyManagement dependencyManagement = new DependencyManagement(new ArrayList<>());
    ArrayList<Plugin> plugins = new ArrayList<>();

    // Act and Assert
    assertEquals("42", (new MavenPom("42", null, parent, "Packaging", modules, properties, dependencies,
        dependencyManagement, new Build(plugins, new PluginManagement(new ArrayList<>())))).getGroupId());
  }

  /**
   * Test {@link MavenPom#getGroupId()}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MavenPom#getGroupId()}
   */
  @Test
  @DisplayName("Test getGroupId(); then return 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String MavenPom.getGroupId()"})
  void testGetGroupId_thenReturnNull() {
    // Arrange
    ArrayList<String> modules = new ArrayList<>();
    HashMap<String, String> properties = new HashMap<>();
    ArrayList<Dependency> dependencies = new ArrayList<>();
    DependencyManagement dependencyManagement = new DependencyManagement(new ArrayList<>());
    ArrayList<Plugin> plugins = new ArrayList<>();

    // Act and Assert
    assertNull((new MavenPom("42", null, null, "Packaging", modules, properties, dependencies, dependencyManagement,
        new Build(plugins, new PluginManagement(new ArrayList<>())))).getGroupId());
  }
}
