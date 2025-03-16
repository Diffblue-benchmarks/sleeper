package sleeper.core.properties.local;

import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.table.TableProperties;

class LoadLocalPropertiesDiffblueTest {
  /**
   * Test {@link LoadLocalProperties#loadTablesFromInstancePropertiesFile(InstanceProperties, Path)}.
   * <ul>
   *   <li>Then return limit five collect toList Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link LoadLocalProperties#loadTablesFromInstancePropertiesFile(InstanceProperties, Path)}
   */
  @Test
  @DisplayName("Test loadTablesFromInstancePropertiesFile(InstanceProperties, Path); then return limit five collect toList Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream LoadLocalProperties.loadTablesFromInstancePropertiesFile(InstanceProperties, Path)"})
  void testLoadTablesFromInstancePropertiesFile_thenReturnLimitFiveCollectToListEmpty() {
    // Arrange and Act
    Stream<TableProperties> actualLoadTablesFromInstancePropertiesFileResult = LoadLocalProperties
        .loadTablesFromInstancePropertiesFile(new InstanceProperties(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));

    // Assert
    assertTrue(actualLoadTablesFromInstancePropertiesFileResult.limit(5).collect(Collectors.toList()).isEmpty());
  }

  /**
   * Test {@link LoadLocalProperties#loadTablesFromInstancePropertiesFileNoValidation(InstanceProperties, Path)}.
   * <p>
   * Method under test: {@link LoadLocalProperties#loadTablesFromInstancePropertiesFileNoValidation(InstanceProperties, Path)}
   */
  @Test
  @DisplayName("Test loadTablesFromInstancePropertiesFileNoValidation(InstanceProperties, Path)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "Stream LoadLocalProperties.loadTablesFromInstancePropertiesFileNoValidation(InstanceProperties, Path)"})
  void testLoadTablesFromInstancePropertiesFileNoValidation() {
    // Arrange and Act
    Stream<TableProperties> actualLoadTablesFromInstancePropertiesFileNoValidationResult = LoadLocalProperties
        .loadTablesFromInstancePropertiesFileNoValidation(new InstanceProperties(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));

    // Assert
    assertTrue(
        actualLoadTablesFromInstancePropertiesFileNoValidationResult.limit(5).collect(Collectors.toList()).isEmpty());
  }

  /**
   * Test {@link LoadLocalProperties#loadTablesFromDirectory(InstanceProperties, Path)}.
   * <ul>
   *   <li>Then return limit five collect toList Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link LoadLocalProperties#loadTablesFromDirectory(InstanceProperties, Path)}
   */
  @Test
  @DisplayName("Test loadTablesFromDirectory(InstanceProperties, Path); then return limit five collect toList Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream LoadLocalProperties.loadTablesFromDirectory(InstanceProperties, Path)"})
  void testLoadTablesFromDirectory_thenReturnLimitFiveCollectToListEmpty() {
    // Arrange and Act
    Stream<TableProperties> actualLoadTablesFromDirectoryResult = LoadLocalProperties
        .loadTablesFromDirectory(new InstanceProperties(), Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));

    // Assert
    assertTrue(actualLoadTablesFromDirectoryResult.limit(5).collect(Collectors.toList()).isEmpty());
  }

  /**
   * Test {@link LoadLocalProperties#loadTablesFromDirectoryNoValidation(InstanceProperties, Path)}.
   * <ul>
   *   <li>Then return limit five collect toList Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link LoadLocalProperties#loadTablesFromDirectoryNoValidation(InstanceProperties, Path)}
   */
  @Test
  @DisplayName("Test loadTablesFromDirectoryNoValidation(InstanceProperties, Path); then return limit five collect toList Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream LoadLocalProperties.loadTablesFromDirectoryNoValidation(InstanceProperties, Path)"})
  void testLoadTablesFromDirectoryNoValidation_thenReturnLimitFiveCollectToListEmpty() {
    // Arrange and Act
    Stream<TableProperties> actualLoadTablesFromDirectoryNoValidationResult = LoadLocalProperties
        .loadTablesFromDirectoryNoValidation(new InstanceProperties(),
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));

    // Assert
    assertTrue(actualLoadTablesFromDirectoryNoValidationResult.limit(5).collect(Collectors.toList()).isEmpty());
  }
}
