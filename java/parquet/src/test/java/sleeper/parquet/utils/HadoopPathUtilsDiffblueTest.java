package sleeper.parquet.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.yarn.conf.YarnConfiguration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.properties.instance.InstanceProperties;

class HadoopPathUtilsDiffblueTest {
  /**
   * Test {@link HadoopPathUtils#expandDirectories(List, Configuration, InstanceProperties)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link HadoopPathUtils#expandDirectories(List, Configuration, InstanceProperties)}
   */
  @Test
  @DisplayName("Test expandDirectories(List, Configuration, InstanceProperties); when ArrayList(); then return Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List HadoopPathUtils.expandDirectories(List, Configuration, InstanceProperties)"})
  void testExpandDirectories_whenArrayList_thenReturnEmpty() {
    // Arrange
    ArrayList<String> files = new ArrayList<>();
    Configuration conf = HadoopConfigurationProvider.getConfigurationForClient();

    // Act
    List<String> actualExpandDirectoriesResult = HadoopPathUtils.expandDirectories(files, conf,
        new InstanceProperties());

    // Assert
    assertTrue(actualExpandDirectoriesResult.isEmpty());
  }

  /**
   * Test {@link HadoopPathUtils#expandDirectories(List, Configuration, InstanceProperties)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link HadoopPathUtils#expandDirectories(List, Configuration, InstanceProperties)}
   */
  @Test
  @DisplayName("Test expandDirectories(List, Configuration, InstanceProperties); when 'null'; then return Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List HadoopPathUtils.expandDirectories(List, Configuration, InstanceProperties)"})
  void testExpandDirectories_whenNull_thenReturnEmpty() {
    // Arrange
    Configuration conf = HadoopConfigurationProvider.getConfigurationForClient();

    // Act
    List<String> actualExpandDirectoriesResult = HadoopPathUtils.expandDirectories(null, conf,
        new InstanceProperties());

    // Assert
    assertTrue(actualExpandDirectoriesResult.isEmpty());
  }

  /**
   * Test {@link HadoopPathUtils#getPaths(List, Configuration, String)}.
   * <ul>
   *   <li>Given {@code foo}.</li>
   *   <li>When {@link ArrayList#ArrayList()} add {@code foo}.</li>
   *   <li>Then throw {@link UncheckedIOException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link HadoopPathUtils#getPaths(List, Configuration, String)}
   */
  @Test
  @DisplayName("Test getPaths(List, Configuration, String); given 'foo'; when ArrayList() add 'foo'; then throw UncheckedIOException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List HadoopPathUtils.getPaths(List, Configuration, String)"})
  void testGetPaths_givenFoo_whenArrayListAddFoo_thenThrowUncheckedIOException() {
    // Arrange
    ArrayList<String> files = new ArrayList<>();
    files.add("foo");

    // Act and Assert
    assertThrows(UncheckedIOException.class, () -> HadoopPathUtils.getPaths(files,
        HadoopConfigurationProvider.getConfigurationForClient(), "File System Property"));
  }

  /**
   * Test {@link HadoopPathUtils#getPaths(List, Configuration, String)}.
   * <ul>
   *   <li>Given {@code foo}.</li>
   *   <li>When {@link ArrayList#ArrayList()} add {@code foo}.</li>
   *   <li>Then throw {@link UncheckedIOException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link HadoopPathUtils#getPaths(List, Configuration, String)}
   */
  @Test
  @DisplayName("Test getPaths(List, Configuration, String); given 'foo'; when ArrayList() add 'foo'; then throw UncheckedIOException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List HadoopPathUtils.getPaths(List, Configuration, String)"})
  void testGetPaths_givenFoo_whenArrayListAddFoo_thenThrowUncheckedIOException2() {
    // Arrange
    ArrayList<String> files = new ArrayList<>();
    files.add("foo");
    files.add("foo");

    // Act and Assert
    assertThrows(UncheckedIOException.class, () -> HadoopPathUtils.getPaths(files,
        HadoopConfigurationProvider.getConfigurationForClient(), "File System Property"));
  }

  /**
   * Test {@link HadoopPathUtils#getPaths(List, Configuration, String)}.
   * <ul>
   *   <li>Given {@code foo}.</li>
   *   <li>When {@link Configuration#Configuration()}.</li>
   *   <li>Then throw {@link UncheckedIOException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link HadoopPathUtils#getPaths(List, Configuration, String)}
   */
  @Test
  @DisplayName("Test getPaths(List, Configuration, String); given 'foo'; when Configuration(); then throw UncheckedIOException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List HadoopPathUtils.getPaths(List, Configuration, String)"})
  void testGetPaths_givenFoo_whenConfiguration_thenThrowUncheckedIOException() {
    // Arrange
    ArrayList<String> files = new ArrayList<>();
    files.add("foo");

    // Act and Assert
    assertThrows(UncheckedIOException.class,
        () -> HadoopPathUtils.getPaths(files, new Configuration(), "File System Property"));
  }

  /**
   * Test {@link HadoopPathUtils#getPaths(List, Configuration, String)}.
   * <ul>
   *   <li>Given {@code foo}.</li>
   *   <li>When {@code /}.</li>
   *   <li>Then throw {@link UncheckedIOException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link HadoopPathUtils#getPaths(List, Configuration, String)}
   */
  @Test
  @DisplayName("Test getPaths(List, Configuration, String); given 'foo'; when '/'; then throw UncheckedIOException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List HadoopPathUtils.getPaths(List, Configuration, String)"})
  void testGetPaths_givenFoo_whenSlash_thenThrowUncheckedIOException() {
    // Arrange
    ArrayList<String> files = new ArrayList<>();
    files.add("foo");

    // Act and Assert
    assertThrows(UncheckedIOException.class,
        () -> HadoopPathUtils.getPaths(files, HadoopConfigurationProvider.getConfigurationForClient(), "/"));
  }

  /**
   * Test {@link HadoopPathUtils#getPaths(List, Configuration, String)}.
   * <ul>
   *   <li>Given {@code /}.</li>
   *   <li>When {@link ArrayList#ArrayList()} add {@code /}.</li>
   *   <li>Then throw {@link UncheckedIOException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link HadoopPathUtils#getPaths(List, Configuration, String)}
   */
  @Test
  @DisplayName("Test getPaths(List, Configuration, String); given '/'; when ArrayList() add '/'; then throw UncheckedIOException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List HadoopPathUtils.getPaths(List, Configuration, String)"})
  void testGetPaths_givenSlash_whenArrayListAddSlash_thenThrowUncheckedIOException() {
    // Arrange
    ArrayList<String> files = new ArrayList<>();
    files.add("/");

    // Act and Assert
    assertThrows(UncheckedIOException.class, () -> HadoopPathUtils.getPaths(files,
        HadoopConfigurationProvider.getConfigurationForClient(), "File System Property"));
  }

  /**
   * Test {@link HadoopPathUtils#getPaths(List, Configuration, String)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link HadoopPathUtils#getPaths(List, Configuration, String)}
   */
  @Test
  @DisplayName("Test getPaths(List, Configuration, String); when ArrayList(); then return Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List HadoopPathUtils.getPaths(List, Configuration, String)"})
  void testGetPaths_whenArrayList_thenReturnEmpty() {
    // Arrange
    ArrayList<String> files = new ArrayList<>();

    // Act
    List<Path> actualPaths = HadoopPathUtils.getPaths(files, HadoopConfigurationProvider.getConfigurationForClient(),
        "File System Property");

    // Assert
    assertTrue(actualPaths.isEmpty());
  }

  /**
   * Test {@link HadoopPathUtils#getPaths(List, Configuration, String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link HadoopPathUtils#getPaths(List, Configuration, String)}
   */
  @Test
  @DisplayName("Test getPaths(List, Configuration, String); when 'null'; then return Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List HadoopPathUtils.getPaths(List, Configuration, String)"})
  void testGetPaths_whenNull_thenReturnEmpty() {
    // Arrange and Act
    List<Path> actualPaths = HadoopPathUtils.getPaths(null, HadoopConfigurationProvider.getConfigurationForClient(),
        "File System Property");

    // Assert
    assertTrue(actualPaths.isEmpty());
  }

  /**
   * Test {@link HadoopPathUtils#getPaths(List, Configuration, String)}.
   * <ul>
   *   <li>When {@link YarnConfiguration#YarnConfiguration(Configuration)} with conf is ConfigurationForClient.</li>
   * </ul>
   * <p>
   * Method under test: {@link HadoopPathUtils#getPaths(List, Configuration, String)}
   */
  @Test
  @DisplayName("Test getPaths(List, Configuration, String); when YarnConfiguration(Configuration) with conf is ConfigurationForClient")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List HadoopPathUtils.getPaths(List, Configuration, String)"})
  void testGetPaths_whenYarnConfigurationWithConfIsConfigurationForClient() {
    // Arrange
    ArrayList<String> files = new ArrayList<>();
    files.add("foo");

    // Act and Assert
    assertThrows(UncheckedIOException.class, () -> HadoopPathUtils.getPaths(files,
        new YarnConfiguration(HadoopConfigurationProvider.getConfigurationForClient()), "File System Property"));
  }

  /**
   * Test {@link HadoopPathUtils#getRequestPath(FileStatus)}.
   * <ul>
   *   <li>Given {@link Path#Path(String)} with {@code Path String}.</li>
   *   <li>Then return {@code nullPath String}.</li>
   * </ul>
   * <p>
   * Method under test: {@link HadoopPathUtils#getRequestPath(FileStatus)}
   */
  @Test
  @DisplayName("Test getRequestPath(FileStatus); given Path(String) with 'Path String'; then return 'nullPath String'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String HadoopPathUtils.getRequestPath(FileStatus)"})
  void testGetRequestPath_givenPathWithPathString_thenReturnNullPathString() throws IllegalArgumentException {
    // Arrange
    FileStatus file = new FileStatus();
    file.setPath(new Path("Path String"));

    // Act and Assert
    assertEquals("nullPath String", HadoopPathUtils.getRequestPath(file));
  }
}
