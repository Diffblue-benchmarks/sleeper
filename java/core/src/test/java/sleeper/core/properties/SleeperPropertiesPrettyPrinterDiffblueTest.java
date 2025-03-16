package sleeper.core.properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.properties.SleeperPropertiesPrettyPrinter.Builder;
import sleeper.core.properties.instance.ArrayListIngestProperty;
import sleeper.core.properties.instance.AthenaProperty;
import sleeper.core.properties.instance.InstancePropertyGroup;
import sleeper.core.properties.table.TablePropertyGroup;

class SleeperPropertiesPrettyPrinterDiffblueTest {
  /**
   * Test Builder {@link Builder#properties(List, List)}.
   * <ul>
   *   <li>Given {@link AthenaProperty#ATHENA_COMPOSITE_HANDLER_CLASSES}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#properties(List, List)}
   */
  @Test
  @DisplayName("Test Builder properties(List, List); given ATHENA_COMPOSITE_HANDLER_CLASSES")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.properties(List, List)"})
  void testBuilderProperties_givenAthena_composite_handler_classes() {
    // Arrange
    Builder<?> builderResult = SleeperPropertiesPrettyPrinter.builder();

    ArrayList<SleeperProperty> properties = new ArrayList<>();
    properties.add(AthenaProperty.ATHENA_COMPOSITE_HANDLER_CLASSES);
    properties.add(ArrayListIngestProperty.MAX_IN_MEMORY_BATCH_SIZE);

    ArrayList<PropertyGroup> groups = new ArrayList<>();
    groups.add(InstancePropertyGroup.ATHENA);

    // Act and Assert
    assertSame(builderResult, builderResult.properties(properties, groups));
  }

  /**
   * Test Builder {@link Builder#properties(List, List)}.
   * <ul>
   *   <li>Given {@link InstancePropertyGroup#ATHENA}.</li>
   *   <li>When {@link ArrayList#ArrayList()} add {@link InstancePropertyGroup#ATHENA}.</li>
   *   <li>Then return builder.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#properties(List, List)}
   */
  @Test
  @DisplayName("Test Builder properties(List, List); given ATHENA; when ArrayList() add ATHENA; then return builder")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.properties(List, List)"})
  void testBuilderProperties_givenAthena_whenArrayListAddAthena_thenReturnBuilder() {
    // Arrange
    Builder<?> builderResult = SleeperPropertiesPrettyPrinter.builder();
    ArrayList<SleeperProperty> properties = new ArrayList<>();

    ArrayList<PropertyGroup> groups = new ArrayList<>();
    groups.add(InstancePropertyGroup.ATHENA);

    // Act and Assert
    assertSame(builderResult, builderResult.properties(properties, groups));
  }

  /**
   * Test Builder {@link Builder#properties(List, List)}.
   * <ul>
   *   <li>Given {@link InstancePropertyGroup#ATHENA}.</li>
   *   <li>When {@link ArrayList#ArrayList()} add {@link InstancePropertyGroup#ATHENA}.</li>
   *   <li>Then return builder.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#properties(List, List)}
   */
  @Test
  @DisplayName("Test Builder properties(List, List); given ATHENA; when ArrayList() add ATHENA; then return builder")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.properties(List, List)"})
  void testBuilderProperties_givenAthena_whenArrayListAddAthena_thenReturnBuilder2() {
    // Arrange
    Builder<?> builderResult = SleeperPropertiesPrettyPrinter.builder();
    ArrayList<SleeperProperty> properties = new ArrayList<>();

    ArrayList<PropertyGroup> groups = new ArrayList<>();
    groups.add(InstancePropertyGroup.ATHENA);
    groups.add(InstancePropertyGroup.ATHENA);

    // Act and Assert
    assertSame(builderResult, builderResult.properties(properties, groups));
  }

  /**
   * Test Builder {@link Builder#properties(List, List)}.
   * <ul>
   *   <li>Given {@link InstancePropertyGroup#ATHENA}.</li>
   *   <li>When {@link ArrayList#ArrayList()} add {@link InstancePropertyGroup#ATHENA}.</li>
   *   <li>Then return builder.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#properties(List, List)}
   */
  @Test
  @DisplayName("Test Builder properties(List, List); given ATHENA; when ArrayList() add ATHENA; then return builder")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.properties(List, List)"})
  void testBuilderProperties_givenAthena_whenArrayListAddAthena_thenReturnBuilder3() {
    // Arrange
    Builder<?> builderResult = SleeperPropertiesPrettyPrinter.builder();

    ArrayList<SleeperProperty> properties = new ArrayList<>();
    properties.add(ArrayListIngestProperty.MAX_IN_MEMORY_BATCH_SIZE);
    properties.add(ArrayListIngestProperty.MAX_IN_MEMORY_BATCH_SIZE);

    ArrayList<PropertyGroup> groups = new ArrayList<>();
    groups.add(InstancePropertyGroup.ATHENA);

    // Act and Assert
    assertSame(builderResult, builderResult.properties(properties, groups));
  }

  /**
   * Test Builder {@link Builder#properties(List, List)}.
   * <ul>
   *   <li>Given {@link InstancePropertyGroup#ATHENA}.</li>
   *   <li>When {@link ArrayList#ArrayList()} add {@link InstancePropertyGroup#ATHENA}.</li>
   *   <li>Then return builder.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#properties(List, List)}
   */
  @Test
  @DisplayName("Test Builder properties(List, List); given ATHENA; when ArrayList() add ATHENA; then return builder")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.properties(List, List)"})
  void testBuilderProperties_givenAthena_whenArrayListAddAthena_thenReturnBuilder4() {
    // Arrange
    Builder<?> builderResult = SleeperPropertiesPrettyPrinter.builder();

    ArrayList<SleeperProperty> properties = new ArrayList<>();
    properties.add(ArrayListIngestProperty.MAX_IN_MEMORY_BATCH_SIZE);
    properties.add(ArrayListIngestProperty.MAX_IN_MEMORY_BATCH_SIZE);

    ArrayList<PropertyGroup> groups = new ArrayList<>();
    groups.add(InstancePropertyGroup.ATHENA);
    groups.add(InstancePropertyGroup.ATHENA);

    // Act and Assert
    assertSame(builderResult, builderResult.properties(properties, groups));
  }

  /**
   * Test Builder {@link Builder#properties(List, List)}.
   * <ul>
   *   <li>Given {@link TablePropertyGroup#INGEST}.</li>
   *   <li>When {@link ArrayList#ArrayList()} add {@link TablePropertyGroup#INGEST}.</li>
   *   <li>Then return builder.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#properties(List, List)}
   */
  @Test
  @DisplayName("Test Builder properties(List, List); given INGEST; when ArrayList() add INGEST; then return builder")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.properties(List, List)"})
  void testBuilderProperties_givenIngest_whenArrayListAddIngest_thenReturnBuilder() {
    // Arrange
    Builder<?> builderResult = SleeperPropertiesPrettyPrinter.builder();

    ArrayList<SleeperProperty> properties = new ArrayList<>();
    properties.add(ArrayListIngestProperty.MAX_IN_MEMORY_BATCH_SIZE);
    properties.add(ArrayListIngestProperty.MAX_IN_MEMORY_BATCH_SIZE);

    ArrayList<PropertyGroup> groups = new ArrayList<>();
    groups.add(TablePropertyGroup.INGEST);

    // Act and Assert
    assertSame(builderResult, builderResult.properties(properties, groups));
  }

  /**
   * Test Builder {@link Builder#properties(List, List)}.
   * <ul>
   *   <li>Given {@link ArrayListIngestProperty#MAX_IN_MEMORY_BATCH_SIZE}.</li>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#properties(List, List)}
   */
  @Test
  @DisplayName("Test Builder properties(List, List); given MAX_IN_MEMORY_BATCH_SIZE; when ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.properties(List, List)"})
  void testBuilderProperties_givenMax_in_memory_batch_size_whenArrayList() {
    // Arrange
    Builder<?> builderResult = SleeperPropertiesPrettyPrinter.builder();

    ArrayList<SleeperProperty> properties = new ArrayList<>();
    properties.add(ArrayListIngestProperty.MAX_IN_MEMORY_BATCH_SIZE);

    // Act and Assert
    assertSame(builderResult, builderResult.properties(properties, new ArrayList<>()));
  }

  /**
   * Test Builder {@link Builder#properties(List, List)}.
   * <ul>
   *   <li>Given {@link ArrayListIngestProperty#MAX_IN_MEMORY_BATCH_SIZE}.</li>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#properties(List, List)}
   */
  @Test
  @DisplayName("Test Builder properties(List, List); given MAX_IN_MEMORY_BATCH_SIZE; when ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.properties(List, List)"})
  void testBuilderProperties_givenMax_in_memory_batch_size_whenArrayList2() {
    // Arrange
    Builder<?> builderResult = SleeperPropertiesPrettyPrinter.builder();

    ArrayList<SleeperProperty> properties = new ArrayList<>();
    properties.add(ArrayListIngestProperty.MAX_IN_MEMORY_BATCH_SIZE);
    properties.add(ArrayListIngestProperty.MAX_IN_MEMORY_BATCH_SIZE);

    // Act and Assert
    assertSame(builderResult, builderResult.properties(properties, new ArrayList<>()));
  }

  /**
   * Test Builder {@link Builder#properties(List, List)}.
   * <ul>
   *   <li>Given {@code null}.</li>
   *   <li>When {@link ArrayList#ArrayList()} add {@code null}.</li>
   *   <li>Then return builder.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#properties(List, List)}
   */
  @Test
  @DisplayName("Test Builder properties(List, List); given 'null'; when ArrayList() add 'null'; then return builder")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.properties(List, List)"})
  void testBuilderProperties_givenNull_whenArrayListAddNull_thenReturnBuilder() {
    // Arrange
    Builder<?> builderResult = SleeperPropertiesPrettyPrinter.builder();

    ArrayList<SleeperProperty> properties = new ArrayList<>();
    properties.add(ArrayListIngestProperty.MAX_IN_MEMORY_BATCH_SIZE);
    properties.add(ArrayListIngestProperty.MAX_IN_MEMORY_BATCH_SIZE);

    ArrayList<PropertyGroup> groups = new ArrayList<>();
    groups.add(null);

    // Act and Assert
    assertSame(builderResult, builderResult.properties(properties, groups));
  }

  /**
   * Test Builder {@link Builder#properties(List, List)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return builder.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#properties(List, List)}
   */
  @Test
  @DisplayName("Test Builder properties(List, List); when ArrayList(); then return builder")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.properties(List, List)"})
  void testBuilderProperties_whenArrayList_thenReturnBuilder() {
    // Arrange
    Builder<?> builderResult = SleeperPropertiesPrettyPrinter.builder();
    ArrayList<SleeperProperty> properties = new ArrayList<>();

    // Act and Assert
    assertSame(builderResult, builderResult.properties(properties, new ArrayList<>()));
  }

  /**
   * Test {@link SleeperPropertiesPrettyPrinter#formatDescription(String, String)}.
   * <ul>
   *   <li>Then return {@code Line StartThe characteristics of someone or something}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertiesPrettyPrinter#formatDescription(String, String)}
   */
  @Test
  @DisplayName("Test formatDescription(String, String); then return 'Line StartThe characteristics of someone or something'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String SleeperPropertiesPrettyPrinter.formatDescription(String, String)"})
  void testFormatDescription_thenReturnLineStartTheCharacteristicsOfSomeoneOrSomething() {
    // Arrange, Act and Assert
    assertEquals("Line StartThe characteristics of someone or something",
        SleeperPropertiesPrettyPrinter.formatDescription("Line Start", "The characteristics of someone or something"));
  }

  /**
   * Test {@link SleeperPropertiesPrettyPrinter#formatDescription(String, String)}.
   * <ul>
   *   <li>When {@code Description}.</li>
   *   <li>Then return {@code Line StartDescription}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertiesPrettyPrinter#formatDescription(String, String)}
   */
  @Test
  @DisplayName("Test formatDescription(String, String); when 'Description'; then return 'Line StartDescription'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String SleeperPropertiesPrettyPrinter.formatDescription(String, String)"})
  void testFormatDescription_whenDescription_thenReturnLineStartDescription() {
    // Arrange, Act and Assert
    assertEquals("Line StartDescription",
        SleeperPropertiesPrettyPrinter.formatDescription("Line Start", "Description"));
  }

  /**
   * Test {@link SleeperPropertiesPrettyPrinter#formatDescription(String, String)}.
   * <ul>
   *   <li>When lf.</li>
   *   <li>Then return empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertiesPrettyPrinter#formatDescription(String, String)}
   */
  @Test
  @DisplayName("Test formatDescription(String, String); when lf; then return empty string")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String SleeperPropertiesPrettyPrinter.formatDescription(String, String)"})
  void testFormatDescription_whenLf_thenReturnEmptyString() {
    // Arrange, Act and Assert
    assertEquals("", SleeperPropertiesPrettyPrinter.formatDescription("Line Start", "\n"));
  }

  /**
   * Test {@link SleeperPropertiesPrettyPrinter#formatDescription(String, String)}.
   * <ul>
   *   <li>When space.</li>
   *   <li>Then return {@code Line Start}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertiesPrettyPrinter#formatDescription(String, String)}
   */
  @Test
  @DisplayName("Test formatDescription(String, String); when space; then return 'Line Start'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String SleeperPropertiesPrettyPrinter.formatDescription(String, String)"})
  void testFormatDescription_whenSpace_thenReturnLineStart() {
    // Arrange, Act and Assert
    assertEquals("Line Start", SleeperPropertiesPrettyPrinter.formatDescription("Line Start", " "));
  }
}
