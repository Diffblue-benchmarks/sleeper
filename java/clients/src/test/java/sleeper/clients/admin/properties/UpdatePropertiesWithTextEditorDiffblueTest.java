package sleeper.clients.admin.properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import sleeper.clients.util.CommandRunner;
import sleeper.core.properties.PropertyGroup;
import sleeper.core.properties.SleeperPropertyIndex;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.instance.InstanceProperty;
import sleeper.core.properties.table.TableProperties;
import sleeper.core.properties.testutils.DummyInstanceProperty;

class UpdatePropertiesWithTextEditorDiffblueTest {
  /**
   * Test {@link UpdatePropertiesWithTextEditor#openPropertiesFile(InstanceProperties)} with {@code InstanceProperties}.
   * <p>
   * Method under test: {@link UpdatePropertiesWithTextEditor#openPropertiesFile(InstanceProperties)}
   */
  @Test
  @DisplayName("Test openPropertiesFile(InstanceProperties) with 'InstanceProperties'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"UpdatePropertiesRequest UpdatePropertiesWithTextEditor.openPropertiesFile(InstanceProperties)"})
  void testOpenPropertiesFileWithInstanceProperties() throws IOException, InterruptedException {
    try (MockedStatic<Files> mockFiles = mockStatic(Files.class)) {

      // Arrange
      mockFiles.when(() -> Files.newBufferedReader(Mockito.<Path>any()))
          .thenReturn(new BufferedReader(new StringReader("foo"), 1));
      mockFiles.when(() -> Files.newBufferedWriter(Mockito.<Path>any(), isA(OpenOption[].class)))
          .thenReturn(new BufferedWriter(new StringWriter(), 1));
      mockFiles.when(() -> Files.createDirectories(Mockito.<Path>any(), isA(FileAttribute[].class)))
          .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));
      Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
      CommandRunner runCommand = mock(CommandRunner.class);
      when(runCommand.run(isA(String[].class))).thenReturn(1);
      UpdatePropertiesWithTextEditor updatePropertiesWithTextEditor = new UpdatePropertiesWithTextEditor(
          Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"), runCommand);
      InstanceProperties properties = mock(InstanceProperties.class);
      when(properties.isSet(Mockito.<InstanceProperty>any())).thenReturn(true);
      when(properties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
      HashMap<String, String> stringStringMap = new HashMap<>();
      when(properties.toMap()).thenReturn(stringStringMap);

      ArrayList<Entry<String, String>> entryList = new ArrayList<>();
      Stream<Entry<String, String>> streamResult = entryList.stream();
      when(properties.getUnknownProperties()).thenReturn(streamResult);

      // Act
      UpdatePropertiesRequest<InstanceProperties> actualOpenPropertiesFileResult = updatePropertiesWithTextEditor
          .openPropertiesFile(properties);

      // Assert
      mockFiles.verify(() -> Files.createDirectories(Mockito.<Path>any(), isA(FileAttribute[].class)));
      mockFiles.verify(() -> Files.newBufferedReader(Mockito.<Path>any()));
      mockFiles.verify(() -> Files.newBufferedWriter(Mockito.<Path>any(), isA(OpenOption[].class)));
      verify(runCommand).run(isA(String[].class));
      verify(properties).getUnknownProperties();
      verify(properties, atLeast(1)).isSet(Mockito.<InstanceProperty>any());
      verify(properties).toMap();
      verify(properties, atLeast(1)).get(Mockito.<InstanceProperty>any());
      InstanceProperties updatedProperties = actualOpenPropertiesFileResult.getUpdatedProperties();
      SleeperPropertyIndex<InstanceProperty> propertiesIndex = updatedProperties.getPropertiesIndex();
      assertEquals(131, propertiesIndex.getCdkDefined().size());
      assertEquals(325, propertiesIndex.getUserDefined().size());
      assertEquals(stringStringMap, updatedProperties.getTagsProperties());
    }
  }

  /**
   * Test {@link UpdatePropertiesWithTextEditor#openPropertiesFile(InstanceProperties)} with {@code InstanceProperties}.
   * <p>
   * Method under test: {@link UpdatePropertiesWithTextEditor#openPropertiesFile(InstanceProperties)}
   */
  @Test
  @DisplayName("Test openPropertiesFile(InstanceProperties) with 'InstanceProperties'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"UpdatePropertiesRequest UpdatePropertiesWithTextEditor.openPropertiesFile(InstanceProperties)"})
  void testOpenPropertiesFileWithInstanceProperties2() throws IOException, InterruptedException {
    try (MockedStatic<Files> mockFiles = mockStatic(Files.class)) {

      // Arrange
      mockFiles.when(() -> Files.newBufferedReader(Mockito.<Path>any()))
          .thenReturn(new BufferedReader(new StringReader("foo"), 1));
      mockFiles.when(() -> Files.newBufferedWriter(Mockito.<Path>any(), isA(OpenOption[].class)))
          .thenReturn(new BufferedWriter(new StringWriter(), 1));
      mockFiles.when(() -> Files.createDirectories(Mockito.<Path>any(), isA(FileAttribute[].class)))
          .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));
      Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
      CommandRunner runCommand = mock(CommandRunner.class);
      when(runCommand.run(isA(String[].class))).thenReturn(1);
      UpdatePropertiesWithTextEditor updatePropertiesWithTextEditor = new UpdatePropertiesWithTextEditor(
          Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"), runCommand);

      ArrayList<Entry<String, String>> entryList = new ArrayList<>();
      entryList.add(new SimpleEntry<>("foo", "foo"));
      entryList.add(new SimpleEntry<>(" ", " "));
      Stream<Entry<String, String>> streamResult = entryList.stream();
      InstanceProperties properties = mock(InstanceProperties.class);
      when(properties.isSet(Mockito.<InstanceProperty>any())).thenReturn(true);
      when(properties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
      HashMap<String, String> stringStringMap = new HashMap<>();
      when(properties.toMap()).thenReturn(stringStringMap);
      when(properties.getUnknownProperties()).thenReturn(streamResult);

      // Act
      UpdatePropertiesRequest<InstanceProperties> actualOpenPropertiesFileResult = updatePropertiesWithTextEditor
          .openPropertiesFile(properties);

      // Assert
      mockFiles.verify(() -> Files.createDirectories(Mockito.<Path>any(), isA(FileAttribute[].class)));
      mockFiles.verify(() -> Files.newBufferedReader(Mockito.<Path>any()));
      mockFiles.verify(() -> Files.newBufferedWriter(Mockito.<Path>any(), isA(OpenOption[].class)));
      verify(runCommand).run(isA(String[].class));
      verify(properties).getUnknownProperties();
      verify(properties, atLeast(1)).isSet(Mockito.<InstanceProperty>any());
      verify(properties).toMap();
      verify(properties, atLeast(1)).get(Mockito.<InstanceProperty>any());
      InstanceProperties updatedProperties = actualOpenPropertiesFileResult.getUpdatedProperties();
      SleeperPropertyIndex<InstanceProperty> propertiesIndex = updatedProperties.getPropertiesIndex();
      assertEquals(131, propertiesIndex.getCdkDefined().size());
      assertEquals(325, propertiesIndex.getUserDefined().size());
      assertEquals(stringStringMap, updatedProperties.getTagsProperties());
    }
  }

  /**
   * Test {@link UpdatePropertiesWithTextEditor#openPropertiesFile(InstanceProperties, PropertyGroup)} with {@code InstanceProperties}, {@code PropertyGroup}.
   * <p>
   * Method under test: {@link UpdatePropertiesWithTextEditor#openPropertiesFile(InstanceProperties, PropertyGroup)}
   */
  @Test
  @DisplayName("Test openPropertiesFile(InstanceProperties, PropertyGroup) with 'InstanceProperties', 'PropertyGroup'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "UpdatePropertiesRequest UpdatePropertiesWithTextEditor.openPropertiesFile(InstanceProperties, PropertyGroup)"})
  void testOpenPropertiesFileWithInstancePropertiesPropertyGroup() throws IOException, InterruptedException {
    try (MockedStatic<Files> mockFiles = mockStatic(Files.class)) {

      // Arrange
      mockFiles.when(() -> Files.newBufferedReader(Mockito.<Path>any()))
          .thenReturn(new BufferedReader(new StringReader("foo"), 1));
      mockFiles.when(() -> Files.newBufferedWriter(Mockito.<Path>any(), isA(OpenOption[].class)))
          .thenReturn(new BufferedWriter(new StringWriter(), 1));
      mockFiles.when(() -> Files.createDirectories(Mockito.<Path>any(), isA(FileAttribute[].class)))
          .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));
      Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
      CommandRunner runCommand = mock(CommandRunner.class);
      when(runCommand.run(isA(String[].class))).thenReturn(1);
      UpdatePropertiesWithTextEditor updatePropertiesWithTextEditor = new UpdatePropertiesWithTextEditor(
          Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"), runCommand);

      // Act
      UpdatePropertiesRequest<InstanceProperties> actualOpenPropertiesFileResult = updatePropertiesWithTextEditor
          .openPropertiesFile(new InstanceProperties(), mock(PropertyGroup.class));

      // Assert
      mockFiles.verify(() -> Files.createDirectories(Mockito.<Path>any(), isA(FileAttribute[].class)));
      mockFiles.verify(() -> Files.newBufferedReader(Mockito.<Path>any()));
      mockFiles.verify(() -> Files.newBufferedWriter(Mockito.<Path>any(), isA(OpenOption[].class)));
      verify(runCommand).run(isA(String[].class));
      InstanceProperties updatedProperties = actualOpenPropertiesFileResult.getUpdatedProperties();
      Stream<Entry<String, String>> unknownProperties = updatedProperties.getUnknownProperties();
      assertEquals(1, unknownProperties.limit(5).collect(Collectors.toList()).size());
      Map<String, String> toMapResult = updatedProperties.toMap();
      assertEquals(1, toMapResult.size());
      Properties properties = updatedProperties.getProperties();
      assertEquals(1, properties.size());
      assertTrue(toMapResult.containsKey("foo"));
      assertTrue(properties.containsKey("foo"));
    }
  }

  /**
   * Test {@link UpdatePropertiesWithTextEditor#openPropertiesFile(InstanceProperties, PropertyGroup)} with {@code InstanceProperties}, {@code PropertyGroup}.
   * <p>
   * Method under test: {@link UpdatePropertiesWithTextEditor#openPropertiesFile(InstanceProperties, PropertyGroup)}
   */
  @Test
  @DisplayName("Test openPropertiesFile(InstanceProperties, PropertyGroup) with 'InstanceProperties', 'PropertyGroup'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "UpdatePropertiesRequest UpdatePropertiesWithTextEditor.openPropertiesFile(InstanceProperties, PropertyGroup)"})
  void testOpenPropertiesFileWithInstancePropertiesPropertyGroup2() throws IOException, InterruptedException {
    try (MockedStatic<Files> mockFiles = mockStatic(Files.class)) {

      // Arrange
      mockFiles.when(() -> Files.newBufferedReader(Mockito.<Path>any()))
          .thenReturn(new BufferedReader(new StringReader(""), 1));
      mockFiles.when(() -> Files.newBufferedWriter(Mockito.<Path>any(), isA(OpenOption[].class)))
          .thenReturn(new BufferedWriter(new StringWriter(), 1));
      mockFiles.when(() -> Files.createDirectories(Mockito.<Path>any(), isA(FileAttribute[].class)))
          .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));
      Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
      CommandRunner runCommand = mock(CommandRunner.class);
      when(runCommand.run(isA(String[].class))).thenReturn(1);
      UpdatePropertiesWithTextEditor updatePropertiesWithTextEditor = new UpdatePropertiesWithTextEditor(
          Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"), runCommand);
      InstanceProperties properties = new InstanceProperties();

      // Act
      UpdatePropertiesRequest<InstanceProperties> actualOpenPropertiesFileResult = updatePropertiesWithTextEditor
          .openPropertiesFile(properties, mock(PropertyGroup.class));

      // Assert
      mockFiles.verify(() -> Files.createDirectories(Mockito.<Path>any(), isA(FileAttribute[].class)));
      mockFiles.verify(() -> Files.newBufferedReader(Mockito.<Path>any()));
      mockFiles.verify(() -> Files.newBufferedWriter(Mockito.<Path>any(), isA(OpenOption[].class)));
      verify(runCommand).run(isA(String[].class));
      PropertiesDiff diff = actualOpenPropertiesFileResult.getDiff();
      assertFalse(diff.isChanged());
      assertTrue(diff.getChanges().isEmpty());
      assertEquals(properties, actualOpenPropertiesFileResult.getUpdatedProperties());
    }
  }

  /**
   * Test {@link UpdatePropertiesWithTextEditor#openPropertiesFile(InstanceProperties, PropertyGroup)} with {@code InstanceProperties}, {@code PropertyGroup}.
   * <p>
   * Method under test: {@link UpdatePropertiesWithTextEditor#openPropertiesFile(InstanceProperties, PropertyGroup)}
   */
  @Test
  @DisplayName("Test openPropertiesFile(InstanceProperties, PropertyGroup) with 'InstanceProperties', 'PropertyGroup'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "UpdatePropertiesRequest UpdatePropertiesWithTextEditor.openPropertiesFile(InstanceProperties, PropertyGroup)"})
  void testOpenPropertiesFileWithInstancePropertiesPropertyGroup3() throws IOException, InterruptedException {
    try (MockedStatic<Files> mockFiles = mockStatic(Files.class)) {

      // Arrange
      mockFiles.when(() -> Files.newBufferedReader(Mockito.<Path>any()))
          .thenReturn(new BufferedReader(new StringReader("foo"), 1));
      mockFiles.when(() -> Files.newBufferedWriter(Mockito.<Path>any(), isA(OpenOption[].class)))
          .thenReturn(new BufferedWriter(new StringWriter(), 1));
      mockFiles.when(() -> Files.createDirectories(Mockito.<Path>any(), isA(FileAttribute[].class)))
          .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));
      Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
      CommandRunner runCommand = mock(CommandRunner.class);
      when(runCommand.run(isA(String[].class))).thenReturn(1);
      UpdatePropertiesWithTextEditor updatePropertiesWithTextEditor = new UpdatePropertiesWithTextEditor(
          Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"), runCommand);

      InstanceProperties properties = new InstanceProperties();
      DummyInstanceProperty dummyInstanceProperty = new DummyInstanceProperty("sleeper/admin");
      properties.addToListIfMissing(dummyInstanceProperty, new ArrayList<>());

      // Act
      UpdatePropertiesRequest<InstanceProperties> actualOpenPropertiesFileResult = updatePropertiesWithTextEditor
          .openPropertiesFile(properties, mock(PropertyGroup.class));

      // Assert
      mockFiles.verify(() -> Files.createDirectories(Mockito.<Path>any(), isA(FileAttribute[].class)));
      mockFiles.verify(() -> Files.newBufferedReader(Mockito.<Path>any()));
      mockFiles.verify(() -> Files.newBufferedWriter(Mockito.<Path>any(), isA(OpenOption[].class)));
      verify(runCommand).run(isA(String[].class));
      InstanceProperties updatedProperties = actualOpenPropertiesFileResult.getUpdatedProperties();
      Map<String, String> toMapResult = updatedProperties.toMap();
      assertEquals(2, toMapResult.size());
      assertEquals("", toMapResult.get("sleeper/admin"));
      Properties properties2 = updatedProperties.getProperties();
      assertEquals(2, properties2.size());
      assertEquals("", properties2.get("sleeper/admin"));
      Stream<Entry<String, String>> unknownProperties = updatedProperties.getUnknownProperties();
      assertEquals(2, unknownProperties.limit(5).collect(Collectors.toList()).size());
      assertTrue(toMapResult.containsKey("foo"));
      assertTrue(properties2.containsKey("foo"));
    }
  }

  /**
   * Test {@link UpdatePropertiesWithTextEditor#openPropertiesFile(InstanceProperties)} with {@code InstanceProperties}.
   * <ul>
   *   <li>Then return Diff Changes size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link UpdatePropertiesWithTextEditor#openPropertiesFile(InstanceProperties)}
   */
  @Test
  @DisplayName("Test openPropertiesFile(InstanceProperties) with 'InstanceProperties'; then return Diff Changes size is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"UpdatePropertiesRequest UpdatePropertiesWithTextEditor.openPropertiesFile(InstanceProperties)"})
  void testOpenPropertiesFileWithInstanceProperties_thenReturnDiffChangesSizeIsOne()
      throws IOException, InterruptedException {
    try (MockedStatic<Files> mockFiles = mockStatic(Files.class)) {

      // Arrange
      mockFiles.when(() -> Files.newBufferedReader(Mockito.<Path>any()))
          .thenReturn(new BufferedReader(new StringReader("foo"), 1));
      mockFiles.when(() -> Files.newBufferedWriter(Mockito.<Path>any(), isA(OpenOption[].class)))
          .thenReturn(new BufferedWriter(new StringWriter(), 1));
      mockFiles.when(() -> Files.createDirectories(Mockito.<Path>any(), isA(FileAttribute[].class)))
          .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));
      Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
      CommandRunner runCommand = mock(CommandRunner.class);
      when(runCommand.run(isA(String[].class))).thenReturn(1);
      UpdatePropertiesWithTextEditor updatePropertiesWithTextEditor = new UpdatePropertiesWithTextEditor(
          Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"), runCommand);

      // Act
      UpdatePropertiesRequest<InstanceProperties> actualOpenPropertiesFileResult = updatePropertiesWithTextEditor
          .openPropertiesFile(new InstanceProperties());

      // Assert
      mockFiles.verify(() -> Files.createDirectories(Mockito.<Path>any(), isA(FileAttribute[].class)));
      mockFiles.verify(() -> Files.newBufferedReader(Mockito.<Path>any()));
      mockFiles.verify(() -> Files.newBufferedWriter(Mockito.<Path>any(), isA(OpenOption[].class)));
      verify(runCommand).run(isA(String[].class));
      List<PropertyDiff> changes = actualOpenPropertiesFileResult.getDiff().getChanges();
      assertEquals(1, changes.size());
      PropertyDiff getResult = changes.get(0);
      assertEquals("", getResult.getNewValue());
      assertEquals("foo", getResult.getPropertyName());
      assertNull(getResult.getOldValue());
      SleeperPropertyIndex<InstanceProperty> propertiesIndex = actualOpenPropertiesFileResult.getUpdatedProperties()
          .getPropertiesIndex();
      assertEquals(131, propertiesIndex.getCdkDefined().size());
      assertEquals(325, propertiesIndex.getUserDefined().size());
    }
  }

  /**
   * Test {@link UpdatePropertiesWithTextEditor#openPropertiesFile(InstanceProperties)} with {@code InstanceProperties}.
   * <ul>
   *   <li>Then return Diff Changes size is two.</li>
   * </ul>
   * <p>
   * Method under test: {@link UpdatePropertiesWithTextEditor#openPropertiesFile(InstanceProperties)}
   */
  @Test
  @DisplayName("Test openPropertiesFile(InstanceProperties) with 'InstanceProperties'; then return Diff Changes size is two")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"UpdatePropertiesRequest UpdatePropertiesWithTextEditor.openPropertiesFile(InstanceProperties)"})
  void testOpenPropertiesFileWithInstanceProperties_thenReturnDiffChangesSizeIsTwo()
      throws IOException, InterruptedException {
    try (MockedStatic<Files> mockFiles = mockStatic(Files.class)) {

      // Arrange
      mockFiles.when(() -> Files.newBufferedReader(Mockito.<Path>any()))
          .thenReturn(new BufferedReader(new StringReader("foo"), 1));
      mockFiles.when(() -> Files.newBufferedWriter(Mockito.<Path>any(), isA(OpenOption[].class)))
          .thenReturn(new BufferedWriter(new StringWriter(), 1));
      mockFiles.when(() -> Files.createDirectories(Mockito.<Path>any(), isA(FileAttribute[].class)))
          .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));
      Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
      CommandRunner runCommand = mock(CommandRunner.class);
      when(runCommand.run(isA(String[].class))).thenReturn(1);
      UpdatePropertiesWithTextEditor updatePropertiesWithTextEditor = new UpdatePropertiesWithTextEditor(
          Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"), runCommand);

      InstanceProperties properties = new InstanceProperties();
      DummyInstanceProperty dummyInstanceProperty = new DummyInstanceProperty(" ");
      properties.addToListIfMissing(dummyInstanceProperty, new ArrayList<>());

      // Act
      UpdatePropertiesRequest<InstanceProperties> actualOpenPropertiesFileResult = updatePropertiesWithTextEditor
          .openPropertiesFile(properties);

      // Assert
      mockFiles.verify(() -> Files.createDirectories(Mockito.<Path>any(), isA(FileAttribute[].class)));
      mockFiles.verify(() -> Files.newBufferedReader(Mockito.<Path>any()));
      mockFiles.verify(() -> Files.newBufferedWriter(Mockito.<Path>any(), isA(OpenOption[].class)));
      verify(runCommand).run(isA(String[].class));
      List<PropertyDiff> changes = actualOpenPropertiesFileResult.getDiff().getChanges();
      assertEquals(2, changes.size());
      PropertyDiff getResult = changes.get(0);
      assertEquals(" ", getResult.getPropertyName());
      PropertyDiff getResult2 = changes.get(1);
      assertEquals("", getResult2.getNewValue());
      assertEquals("", getResult.getOldValue());
      assertEquals("foo", getResult2.getPropertyName());
      assertNull(getResult.getNewValue());
      assertNull(getResult2.getOldValue());
    }
  }

  /**
   * Test {@link UpdatePropertiesWithTextEditor#openPropertiesFile(InstanceProperties)} with {@code InstanceProperties}.
   * <ul>
   *   <li>Then return not Diff Changed.</li>
   * </ul>
   * <p>
   * Method under test: {@link UpdatePropertiesWithTextEditor#openPropertiesFile(InstanceProperties)}
   */
  @Test
  @DisplayName("Test openPropertiesFile(InstanceProperties) with 'InstanceProperties'; then return not Diff Changed")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"UpdatePropertiesRequest UpdatePropertiesWithTextEditor.openPropertiesFile(InstanceProperties)"})
  void testOpenPropertiesFileWithInstanceProperties_thenReturnNotDiffChanged()
      throws IOException, InterruptedException {
    try (MockedStatic<Files> mockFiles = mockStatic(Files.class)) {

      // Arrange
      mockFiles.when(() -> Files.newBufferedReader(Mockito.<Path>any()))
          .thenReturn(new BufferedReader(new StringReader(" "), 1));
      mockFiles.when(() -> Files.newBufferedWriter(Mockito.<Path>any(), isA(OpenOption[].class)))
          .thenReturn(new BufferedWriter(new StringWriter(), 1));
      mockFiles.when(() -> Files.createDirectories(Mockito.<Path>any(), isA(FileAttribute[].class)))
          .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));
      Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
      CommandRunner runCommand = mock(CommandRunner.class);
      when(runCommand.run(isA(String[].class))).thenReturn(1);
      UpdatePropertiesWithTextEditor updatePropertiesWithTextEditor = new UpdatePropertiesWithTextEditor(
          Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"), runCommand);
      InstanceProperties properties = new InstanceProperties();

      // Act
      UpdatePropertiesRequest<InstanceProperties> actualOpenPropertiesFileResult = updatePropertiesWithTextEditor
          .openPropertiesFile(properties);

      // Assert
      mockFiles.verify(() -> Files.createDirectories(Mockito.<Path>any(), isA(FileAttribute[].class)));
      mockFiles.verify(() -> Files.newBufferedReader(Mockito.<Path>any()));
      mockFiles.verify(() -> Files.newBufferedWriter(Mockito.<Path>any(), isA(OpenOption[].class)));
      verify(runCommand).run(isA(String[].class));
      PropertiesDiff diff = actualOpenPropertiesFileResult.getDiff();
      assertFalse(diff.isChanged());
      assertTrue(diff.getChanges().isEmpty());
      assertEquals(properties, actualOpenPropertiesFileResult.getUpdatedProperties());
    }
  }

  /**
   * Test {@link UpdatePropertiesWithTextEditor#openPropertiesFile(TableProperties)} with {@code TableProperties}.
   * <p>
   * Method under test: {@link UpdatePropertiesWithTextEditor#openPropertiesFile(TableProperties)}
   */
  @Test
  @DisplayName("Test openPropertiesFile(TableProperties) with 'TableProperties'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"UpdatePropertiesRequest UpdatePropertiesWithTextEditor.openPropertiesFile(TableProperties)"})
  void testOpenPropertiesFileWithTableProperties() throws IOException, InterruptedException {
    try (MockedStatic<Files> mockFiles = mockStatic(Files.class)) {

      // Arrange
      mockFiles.when(() -> Files.newBufferedReader(Mockito.<Path>any()))
          .thenReturn(new BufferedReader(new StringReader("foo"), 1));
      mockFiles.when(() -> Files.newBufferedWriter(Mockito.<Path>any(), isA(OpenOption[].class)))
          .thenReturn(new BufferedWriter(new StringWriter(), 1));
      mockFiles.when(() -> Files.createDirectories(Mockito.<Path>any(), isA(FileAttribute[].class)))
          .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));
      Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
      CommandRunner runCommand = mock(CommandRunner.class);
      when(runCommand.run(isA(String[].class))).thenReturn(1);
      UpdatePropertiesWithTextEditor updatePropertiesWithTextEditor = new UpdatePropertiesWithTextEditor(
          Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"), runCommand);

      // Act
      UpdatePropertiesRequest<TableProperties> actualOpenPropertiesFileResult = updatePropertiesWithTextEditor
          .openPropertiesFile(new TableProperties(new InstanceProperties()));

      // Assert
      mockFiles.verify(() -> Files.createDirectories(Mockito.<Path>any(), isA(FileAttribute[].class)));
      mockFiles.verify(() -> Files.newBufferedReader(Mockito.<Path>any()));
      mockFiles.verify(() -> Files.newBufferedWriter(Mockito.<Path>any(), isA(OpenOption[].class)));
      verify(runCommand).run(isA(String[].class));
      TableProperties updatedProperties = actualOpenPropertiesFileResult.getUpdatedProperties();
      Map<String, String> toMapResult = updatedProperties.toMap();
      assertEquals(1, toMapResult.size());
      assertEquals("", toMapResult.get("foo"));
      PropertiesDiff diff = actualOpenPropertiesFileResult.getDiff();
      List<PropertyDiff> changes = diff.getChanges();
      assertEquals(1, changes.size());
      PropertyDiff getResult = changes.get(0);
      assertEquals("", getResult.getNewValue());
      Properties properties = updatedProperties.getProperties();
      assertEquals(1, properties.size());
      assertEquals("", properties.get("foo"));
      assertEquals("foo", getResult.getPropertyName());
      assertNull(getResult.getOldValue());
      Stream<Entry<String, String>> unknownProperties = updatedProperties.getUnknownProperties();
      assertEquals(1, unknownProperties.limit(5).collect(Collectors.toList()).size());
      assertTrue(diff.isChanged());
    }
  }

  /**
   * Test {@link UpdatePropertiesWithTextEditor#openPropertiesFile(TableProperties, PropertyGroup)} with {@code TableProperties}, {@code PropertyGroup}.
   * <p>
   * Method under test: {@link UpdatePropertiesWithTextEditor#openPropertiesFile(TableProperties, PropertyGroup)}
   */
  @Test
  @DisplayName("Test openPropertiesFile(TableProperties, PropertyGroup) with 'TableProperties', 'PropertyGroup'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "UpdatePropertiesRequest UpdatePropertiesWithTextEditor.openPropertiesFile(TableProperties, PropertyGroup)"})
  void testOpenPropertiesFileWithTablePropertiesPropertyGroup() throws IOException, InterruptedException {
    try (MockedStatic<Files> mockFiles = mockStatic(Files.class)) {

      // Arrange
      mockFiles.when(() -> Files.newBufferedReader(Mockito.<Path>any()))
          .thenReturn(new BufferedReader(new StringReader("foo"), 1));
      mockFiles.when(() -> Files.newBufferedWriter(Mockito.<Path>any(), isA(OpenOption[].class)))
          .thenReturn(new BufferedWriter(new StringWriter(), 1));
      mockFiles.when(() -> Files.createDirectories(Mockito.<Path>any(), isA(FileAttribute[].class)))
          .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));
      Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
      CommandRunner runCommand = mock(CommandRunner.class);
      when(runCommand.run(isA(String[].class))).thenReturn(1);
      UpdatePropertiesWithTextEditor updatePropertiesWithTextEditor = new UpdatePropertiesWithTextEditor(
          Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"), runCommand);

      // Act
      UpdatePropertiesRequest<TableProperties> actualOpenPropertiesFileResult = updatePropertiesWithTextEditor
          .openPropertiesFile(new TableProperties(new InstanceProperties()), mock(PropertyGroup.class));

      // Assert
      mockFiles.verify(() -> Files.createDirectories(Mockito.<Path>any(), isA(FileAttribute[].class)));
      mockFiles.verify(() -> Files.newBufferedReader(Mockito.<Path>any()));
      mockFiles.verify(() -> Files.newBufferedWriter(Mockito.<Path>any(), isA(OpenOption[].class)));
      verify(runCommand).run(isA(String[].class));
      TableProperties updatedProperties = actualOpenPropertiesFileResult.getUpdatedProperties();
      Map<String, String> toMapResult = updatedProperties.toMap();
      assertEquals(1, toMapResult.size());
      assertEquals("", toMapResult.get("foo"));
      PropertiesDiff diff = actualOpenPropertiesFileResult.getDiff();
      List<PropertyDiff> changes = diff.getChanges();
      assertEquals(1, changes.size());
      PropertyDiff getResult = changes.get(0);
      assertEquals("", getResult.getNewValue());
      Properties properties = updatedProperties.getProperties();
      assertEquals(1, properties.size());
      assertEquals("", properties.get("foo"));
      assertEquals("foo", getResult.getPropertyName());
      assertNull(getResult.getOldValue());
      Stream<Entry<String, String>> unknownProperties = updatedProperties.getUnknownProperties();
      assertEquals(1, unknownProperties.limit(5).collect(Collectors.toList()).size());
      assertTrue(diff.isChanged());
    }
  }

  /**
   * Test {@link UpdatePropertiesWithTextEditor#openPropertiesFile(TableProperties, PropertyGroup)} with {@code TableProperties}, {@code PropertyGroup}.
   * <ul>
   *   <li>Then return not Diff Changed.</li>
   * </ul>
   * <p>
   * Method under test: {@link UpdatePropertiesWithTextEditor#openPropertiesFile(TableProperties, PropertyGroup)}
   */
  @Test
  @DisplayName("Test openPropertiesFile(TableProperties, PropertyGroup) with 'TableProperties', 'PropertyGroup'; then return not Diff Changed")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "UpdatePropertiesRequest UpdatePropertiesWithTextEditor.openPropertiesFile(TableProperties, PropertyGroup)"})
  void testOpenPropertiesFileWithTablePropertiesPropertyGroup_thenReturnNotDiffChanged()
      throws IOException, InterruptedException {
    try (MockedStatic<Files> mockFiles = mockStatic(Files.class)) {

      // Arrange
      mockFiles.when(() -> Files.newBufferedReader(Mockito.<Path>any()))
          .thenReturn(new BufferedReader(new StringReader(""), 1));
      mockFiles.when(() -> Files.newBufferedWriter(Mockito.<Path>any(), isA(OpenOption[].class)))
          .thenReturn(new BufferedWriter(new StringWriter(), 1));
      mockFiles.when(() -> Files.createDirectories(Mockito.<Path>any(), isA(FileAttribute[].class)))
          .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));
      Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
      CommandRunner runCommand = mock(CommandRunner.class);
      when(runCommand.run(isA(String[].class))).thenReturn(1);
      UpdatePropertiesWithTextEditor updatePropertiesWithTextEditor = new UpdatePropertiesWithTextEditor(
          Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"), runCommand);
      TableProperties properties = new TableProperties(new InstanceProperties());

      // Act
      UpdatePropertiesRequest<TableProperties> actualOpenPropertiesFileResult = updatePropertiesWithTextEditor
          .openPropertiesFile(properties, mock(PropertyGroup.class));

      // Assert
      mockFiles.verify(() -> Files.createDirectories(Mockito.<Path>any(), isA(FileAttribute[].class)));
      mockFiles.verify(() -> Files.newBufferedReader(Mockito.<Path>any()));
      mockFiles.verify(() -> Files.newBufferedWriter(Mockito.<Path>any(), isA(OpenOption[].class)));
      verify(runCommand).run(isA(String[].class));
      PropertiesDiff diff = actualOpenPropertiesFileResult.getDiff();
      assertFalse(diff.isChanged());
      assertTrue(diff.getChanges().isEmpty());
      assertEquals(properties, actualOpenPropertiesFileResult.getUpdatedProperties());
    }
  }

  /**
   * Test {@link UpdatePropertiesWithTextEditor#openPropertiesFile(TableProperties)} with {@code TableProperties}.
   * <ul>
   *   <li>Then return not Diff Changed.</li>
   * </ul>
   * <p>
   * Method under test: {@link UpdatePropertiesWithTextEditor#openPropertiesFile(TableProperties)}
   */
  @Test
  @DisplayName("Test openPropertiesFile(TableProperties) with 'TableProperties'; then return not Diff Changed")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"UpdatePropertiesRequest UpdatePropertiesWithTextEditor.openPropertiesFile(TableProperties)"})
  void testOpenPropertiesFileWithTableProperties_thenReturnNotDiffChanged() throws IOException, InterruptedException {
    try (MockedStatic<Files> mockFiles = mockStatic(Files.class)) {

      // Arrange
      mockFiles.when(() -> Files.newBufferedReader(Mockito.<Path>any()))
          .thenReturn(new BufferedReader(new StringReader(" "), 1));
      mockFiles.when(() -> Files.newBufferedWriter(Mockito.<Path>any(), isA(OpenOption[].class)))
          .thenReturn(new BufferedWriter(new StringWriter(), 1));
      mockFiles.when(() -> Files.createDirectories(Mockito.<Path>any(), isA(FileAttribute[].class)))
          .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));
      Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
      CommandRunner runCommand = mock(CommandRunner.class);
      when(runCommand.run(isA(String[].class))).thenReturn(1);
      UpdatePropertiesWithTextEditor updatePropertiesWithTextEditor = new UpdatePropertiesWithTextEditor(
          Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"), runCommand);
      TableProperties properties = new TableProperties(new InstanceProperties());

      // Act
      UpdatePropertiesRequest<TableProperties> actualOpenPropertiesFileResult = updatePropertiesWithTextEditor
          .openPropertiesFile(properties);

      // Assert
      mockFiles.verify(() -> Files.createDirectories(Mockito.<Path>any(), isA(FileAttribute[].class)));
      mockFiles.verify(() -> Files.newBufferedReader(Mockito.<Path>any()));
      mockFiles.verify(() -> Files.newBufferedWriter(Mockito.<Path>any(), isA(OpenOption[].class)));
      verify(runCommand).run(isA(String[].class));
      PropertiesDiff diff = actualOpenPropertiesFileResult.getDiff();
      assertFalse(diff.isChanged());
      assertTrue(diff.getChanges().isEmpty());
      assertEquals(properties, actualOpenPropertiesFileResult.getUpdatedProperties());
    }
  }
}
