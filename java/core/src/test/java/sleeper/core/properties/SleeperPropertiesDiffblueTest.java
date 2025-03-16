package sleeper.core.properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.awt.Component;
import java.awt.Component.BaselineResizeBehavior;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PipedOutputStream;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import sleeper.core.properties.instance.ArrayListIngestProperty;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.instance.InstanceProperty;
import sleeper.core.properties.testutils.DummyInstanceProperty;
import sleeper.core.properties.testutils.InstancePropertiesTestHelper;

class SleeperPropertiesDiffblueTest {
  /**
   * Test {@link SleeperProperties#validate(SleeperPropertiesValidationReporter)} with {@code SleeperPropertiesValidationReporter}.
   * <p>
   * Method under test: {@link SleeperProperties#validate(SleeperPropertiesValidationReporter)}
   */
  @Test
  @DisplayName("Test validate(SleeperPropertiesValidationReporter) with 'SleeperPropertiesValidationReporter'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SleeperProperties.validate(SleeperPropertiesValidationReporter)"})
  void testValidateWithSleeperPropertiesValidationReporter() {
    // Arrange
    InstanceProperties createTestInstancePropertiesWithIdResult = InstancePropertiesTestHelper
        .createTestInstancePropertiesWithId("");
    SleeperPropertiesValidationReporter reporter = mock(SleeperPropertiesValidationReporter.class);
    doNothing().when(reporter).invalidProperty(Mockito.<SleeperProperty>any(), Mockito.<String>any());

    // Act
    createTestInstancePropertiesWithIdResult.validate(reporter);

    // Assert
    verify(reporter).invalidProperty(isA(SleeperProperty.class), isNull());
  }

  /**
   * Test {@link SleeperProperties#validate(SleeperPropertiesValidationReporter)} with {@code SleeperPropertiesValidationReporter}.
   * <ul>
   *   <li>Then calls {@link SleeperPropertiesValidationReporter#invalidProperty(SleeperProperty, String)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperProperties#validate(SleeperPropertiesValidationReporter)}
   */
  @Test
  @DisplayName("Test validate(SleeperPropertiesValidationReporter) with 'SleeperPropertiesValidationReporter'; then calls invalidProperty(SleeperProperty, String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SleeperProperties.validate(SleeperPropertiesValidationReporter)"})
  void testValidateWithSleeperPropertiesValidationReporter_thenCallsInvalidProperty() {
    // Arrange
    InstanceProperties instanceProperties = new InstanceProperties();
    SleeperPropertiesValidationReporter reporter = mock(SleeperPropertiesValidationReporter.class);
    doNothing().when(reporter).invalidProperty(Mockito.<SleeperProperty>any(), Mockito.<String>any());

    // Act
    instanceProperties.validate(reporter);

    // Assert
    verify(reporter, atLeast(1)).invalidProperty(Mockito.<SleeperProperty>any(), isNull());
  }

  /**
   * Test {@link SleeperProperties#compute(SleeperProperty, UnaryOperator)}.
   * <ul>
   *   <li>Given {@code Apply}.</li>
   *   <li>When {@link DummyInstanceProperty#DummyInstanceProperty(String)} with {@code Property Name}.</li>
   *   <li>Then return {@code Apply}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperProperties#compute(SleeperProperty, UnaryOperator)}
   */
  @Test
  @DisplayName("Test compute(SleeperProperty, UnaryOperator); given 'Apply'; when DummyInstanceProperty(String) with 'Property Name'; then return 'Apply'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String SleeperProperties.compute(SleeperProperty, UnaryOperator)"})
  void testCompute_givenApply_whenDummyInstancePropertyWithPropertyName_thenReturnApply() {
    // Arrange
    InstanceProperties instanceProperties = new InstanceProperties();
    DummyInstanceProperty dummyInstanceProperty = new DummyInstanceProperty("Property Name");
    UnaryOperator<String> compute = mock(UnaryOperator.class);
    when(compute.apply(Mockito.<String>any())).thenReturn("Apply");

    // Act
    String actualComputeResult = instanceProperties.compute(dummyInstanceProperty, compute);

    // Assert
    verify(compute).apply(isNull());
    assertEquals("Apply", actualComputeResult);
  }

  /**
   * Test {@link SleeperProperties#compute(SleeperProperty, UnaryOperator)}.
   * <ul>
   *   <li>Given {@code Apply}.</li>
   *   <li>When {@link ArrayListIngestProperty#MAX_IN_MEMORY_BATCH_SIZE}.</li>
   *   <li>Then return {@code Apply}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperProperties#compute(SleeperProperty, UnaryOperator)}
   */
  @Test
  @DisplayName("Test compute(SleeperProperty, UnaryOperator); given 'Apply'; when MAX_IN_MEMORY_BATCH_SIZE; then return 'Apply'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String SleeperProperties.compute(SleeperProperty, UnaryOperator)"})
  void testCompute_givenApply_whenMax_in_memory_batch_size_thenReturnApply() {
    // Arrange
    InstanceProperties instanceProperties = new InstanceProperties();
    UnaryOperator<String> compute = mock(UnaryOperator.class);
    when(compute.apply(Mockito.<String>any())).thenReturn("Apply");

    // Act
    String actualComputeResult = instanceProperties.compute(ArrayListIngestProperty.MAX_IN_MEMORY_BATCH_SIZE, compute);

    // Assert
    verify(compute).apply(isNull());
    assertEquals("Apply", actualComputeResult);
  }

  /**
   * Test {@link SleeperProperties#compute(SleeperProperty, UnaryOperator)}.
   * <ul>
   *   <li>Given {@link IOException#IOException(String)} with {@code foo}.</li>
   *   <li>Then throw {@link UncheckedIOException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperProperties#compute(SleeperProperty, UnaryOperator)}
   */
  @Test
  @DisplayName("Test compute(SleeperProperty, UnaryOperator); given IOException(String) with 'foo'; then throw UncheckedIOException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String SleeperProperties.compute(SleeperProperty, UnaryOperator)"})
  void testCompute_givenIOExceptionWithFoo_thenThrowUncheckedIOException() {
    // Arrange
    InstanceProperties instanceProperties = new InstanceProperties();
    UnaryOperator<String> compute = mock(UnaryOperator.class);
    when(compute.apply(Mockito.<String>any())).thenThrow(new UncheckedIOException(new IOException("foo")));

    // Act and Assert
    assertThrows(UncheckedIOException.class,
        () -> instanceProperties.compute(ArrayListIngestProperty.MAX_IN_MEMORY_BATCH_SIZE, compute));
    verify(compute).apply(isNull());
  }

  /**
   * Test {@link SleeperProperties#set(SleeperProperty, String)}.
   * <ul>
   *   <li>When {@link ArrayListIngestProperty#MAX_IN_MEMORY_BATCH_SIZE}.</li>
   *   <li>Then {@link InstanceProperties#InstanceProperties()} toMap Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperProperties#set(SleeperProperty, String)}
   */
  @Test
  @DisplayName("Test set(SleeperProperty, String); when MAX_IN_MEMORY_BATCH_SIZE; then InstanceProperties() toMap Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SleeperProperties.set(SleeperProperty, String)"})
  void testSet_whenMax_in_memory_batch_size_thenInstancePropertiesToMapEmpty() {
    // Arrange
    InstanceProperties instanceProperties = new InstanceProperties();

    // Act
    instanceProperties.set(ArrayListIngestProperty.MAX_IN_MEMORY_BATCH_SIZE, null);

    // Assert that nothing has changed
    assertTrue(instanceProperties.toMap().isEmpty());
    Properties properties = instanceProperties.getProperties();
    assertTrue(properties.isEmpty());
    assertEquals(properties, instanceProperties.getTagsProperties());
  }

  /**
   * Test {@link SleeperProperties#set(SleeperProperty, String)}.
   * <ul>
   *   <li>When {@link ArrayListIngestProperty#MAX_IN_MEMORY_BATCH_SIZE}.</li>
   *   <li>Then {@link InstanceProperties#InstanceProperties()} toMap size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperProperties#set(SleeperProperty, String)}
   */
  @Test
  @DisplayName("Test set(SleeperProperty, String); when MAX_IN_MEMORY_BATCH_SIZE; then InstanceProperties() toMap size is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SleeperProperties.set(SleeperProperty, String)"})
  void testSet_whenMax_in_memory_batch_size_thenInstancePropertiesToMapSizeIsOne() {
    // Arrange
    InstanceProperties instanceProperties = new InstanceProperties();

    // Act
    instanceProperties.set(ArrayListIngestProperty.MAX_IN_MEMORY_BATCH_SIZE, "42");

    // Assert
    Map<String, String> toMapResult = instanceProperties.toMap();
    assertEquals(1, toMapResult.size());
    assertEquals("42", toMapResult.get("sleeper.ingest.memory.max.batch.size"));
    Properties properties = instanceProperties.getProperties();
    assertEquals(1, properties.size());
    assertEquals("42", properties.get("sleeper.ingest.memory.max.batch.size"));
    assertTrue(instanceProperties.getTagsProperties().isEmpty());
  }

  /**
   * Test {@link SleeperProperties#setNumber(SleeperProperty, Number)}.
   * <ul>
   *   <li>When {@link ArrayListIngestProperty#MAX_IN_MEMORY_BATCH_SIZE}.</li>
   *   <li>Then {@link InstanceProperties#InstanceProperties()} toMap Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperProperties#setNumber(SleeperProperty, Number)}
   */
  @Test
  @DisplayName("Test setNumber(SleeperProperty, Number); when MAX_IN_MEMORY_BATCH_SIZE; then InstanceProperties() toMap Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SleeperProperties.setNumber(SleeperProperty, Number)"})
  void testSetNumber_whenMax_in_memory_batch_size_thenInstancePropertiesToMapEmpty() {
    // Arrange
    InstanceProperties instanceProperties = new InstanceProperties();

    // Act
    instanceProperties.setNumber(ArrayListIngestProperty.MAX_IN_MEMORY_BATCH_SIZE, null);

    // Assert that nothing has changed
    assertTrue(instanceProperties.toMap().isEmpty());
    Properties properties = instanceProperties.getProperties();
    assertTrue(properties.isEmpty());
    assertEquals(properties, instanceProperties.getTagsProperties());
  }

  /**
   * Test {@link SleeperProperties#setNumber(SleeperProperty, Number)}.
   * <ul>
   *   <li>When {@link ArrayListIngestProperty#MAX_IN_MEMORY_BATCH_SIZE}.</li>
   *   <li>Then {@link InstanceProperties#InstanceProperties()} toMap size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperProperties#setNumber(SleeperProperty, Number)}
   */
  @Test
  @DisplayName("Test setNumber(SleeperProperty, Number); when MAX_IN_MEMORY_BATCH_SIZE; then InstanceProperties() toMap size is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SleeperProperties.setNumber(SleeperProperty, Number)"})
  void testSetNumber_whenMax_in_memory_batch_size_thenInstancePropertiesToMapSizeIsOne() {
    // Arrange
    InstanceProperties instanceProperties = new InstanceProperties();

    // Act
    instanceProperties.setNumber(ArrayListIngestProperty.MAX_IN_MEMORY_BATCH_SIZE, Integer.valueOf(1));

    // Assert
    Map<String, String> toMapResult = instanceProperties.toMap();
    assertEquals(1, toMapResult.size());
    assertEquals("1", toMapResult.get("sleeper.ingest.memory.max.batch.size"));
    Properties properties = instanceProperties.getProperties();
    assertEquals(1, properties.size());
    assertEquals("1", properties.get("sleeper.ingest.memory.max.batch.size"));
    assertTrue(instanceProperties.getTagsProperties().isEmpty());
  }

  /**
   * Test {@link SleeperProperties#setList(SleeperProperty, List)}.
   * <p>
   * Method under test: {@link SleeperProperties#setList(SleeperProperty, List)}
   */
  @Test
  @DisplayName("Test setList(SleeperProperty, List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SleeperProperties.setList(SleeperProperty, List)"})
  void testSetList() {
    // Arrange
    InstanceProperties instanceProperties = new InstanceProperties();

    // Act
    instanceProperties.setList(ArrayListIngestProperty.MAX_IN_MEMORY_BATCH_SIZE, new ArrayList<>());

    // Assert
    Map<String, String> toMapResult = instanceProperties.toMap();
    assertEquals(1, toMapResult.size());
    assertEquals("", toMapResult.get("sleeper.ingest.memory.max.batch.size"));
    Properties properties = instanceProperties.getProperties();
    assertEquals(1, properties.size());
    assertEquals("", properties.get("sleeper.ingest.memory.max.batch.size"));
    assertTrue(instanceProperties.getTagsProperties().isEmpty());
  }

  /**
   * Test {@link SleeperProperties#setList(SleeperProperty, List)}.
   * <ul>
   *   <li>Then {@link InstanceProperties#InstanceProperties()} toMap {@code sleeper.ingest.memory.max.batch.size} is {@code ,}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperProperties#setList(SleeperProperty, List)}
   */
  @Test
  @DisplayName("Test setList(SleeperProperty, List); then InstanceProperties() toMap 'sleeper.ingest.memory.max.batch.size' is ','")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SleeperProperties.setList(SleeperProperty, List)"})
  void testSetList_thenInstancePropertiesToMapSleeperIngestMemoryMaxBatchSizeIsComma() {
    // Arrange
    InstanceProperties instanceProperties = new InstanceProperties();

    ArrayList<String> list = new ArrayList<>();
    list.add(",");

    // Act
    instanceProperties.setList(ArrayListIngestProperty.MAX_IN_MEMORY_BATCH_SIZE, list);

    // Assert
    Map<String, String> toMapResult = instanceProperties.toMap();
    assertEquals(1, toMapResult.size());
    assertEquals(",", toMapResult.get("sleeper.ingest.memory.max.batch.size"));
    Properties properties = instanceProperties.getProperties();
    assertEquals(1, properties.size());
    assertEquals(",", properties.get("sleeper.ingest.memory.max.batch.size"));
    assertTrue(instanceProperties.getTagsProperties().isEmpty());
  }

  /**
   * Test {@link SleeperProperties#setList(SleeperProperty, List)}.
   * <ul>
   *   <li>Then {@link InstanceProperties#InstanceProperties()} toMap {@code sleeper.ingest.memory.max.batch.size} is {@code foo,,}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperProperties#setList(SleeperProperty, List)}
   */
  @Test
  @DisplayName("Test setList(SleeperProperty, List); then InstanceProperties() toMap 'sleeper.ingest.memory.max.batch.size' is 'foo,,'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SleeperProperties.setList(SleeperProperty, List)"})
  void testSetList_thenInstancePropertiesToMapSleeperIngestMemoryMaxBatchSizeIsFoo() {
    // Arrange
    InstanceProperties instanceProperties = new InstanceProperties();

    ArrayList<String> list = new ArrayList<>();
    list.add("foo");
    list.add(",");

    // Act
    instanceProperties.setList(ArrayListIngestProperty.MAX_IN_MEMORY_BATCH_SIZE, list);

    // Assert
    Map<String, String> toMapResult = instanceProperties.toMap();
    assertEquals(1, toMapResult.size());
    assertEquals("foo,,", toMapResult.get("sleeper.ingest.memory.max.batch.size"));
    Properties properties = instanceProperties.getProperties();
    assertEquals(1, properties.size());
    assertEquals("foo,,", properties.get("sleeper.ingest.memory.max.batch.size"));
    assertTrue(instanceProperties.getTagsProperties().isEmpty());
  }

  /**
   * Test {@link SleeperProperties#addToListIfMissing(SleeperProperty, List)}.
   * <p>
   * Method under test: {@link SleeperProperties#addToListIfMissing(SleeperProperty, List)}
   */
  @Test
  @DisplayName("Test addToListIfMissing(SleeperProperty, List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SleeperProperties.addToListIfMissing(SleeperProperty, List)"})
  void testAddToListIfMissing() {
    // Arrange
    InstanceProperties instanceProperties = new InstanceProperties();

    // Act
    instanceProperties.addToListIfMissing(ArrayListIngestProperty.MAX_IN_MEMORY_BATCH_SIZE, new ArrayList<>());

    // Assert
    Map<String, String> toMapResult = instanceProperties.toMap();
    assertEquals(1, toMapResult.size());
    assertEquals("1000000", toMapResult.get("sleeper.ingest.memory.max.batch.size"));
    Properties properties = instanceProperties.getProperties();
    assertEquals(1, properties.size());
    assertEquals("1000000", properties.get("sleeper.ingest.memory.max.batch.size"));
    Stream<Entry<String, String>> unknownProperties = instanceProperties.getUnknownProperties();
    assertTrue(unknownProperties.limit(5).collect(Collectors.toList()).isEmpty());
  }

  /**
   * Test {@link SleeperProperties#addToListIfMissing(SleeperProperty, List)}.
   * <p>
   * Method under test: {@link SleeperProperties#addToListIfMissing(SleeperProperty, List)}
   */
  @Test
  @DisplayName("Test addToListIfMissing(SleeperProperty, List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SleeperProperties.addToListIfMissing(SleeperProperty, List)"})
  void testAddToListIfMissing2() {
    // Arrange
    InstanceProperties instanceProperties = new InstanceProperties();

    ArrayList<String> list = new ArrayList<>();
    list.add(",");

    // Act
    instanceProperties.addToListIfMissing(ArrayListIngestProperty.MAX_IN_MEMORY_BATCH_SIZE, list);

    // Assert
    Map<String, String> toMapResult = instanceProperties.toMap();
    assertEquals(1, toMapResult.size());
    assertEquals("1000000,,", toMapResult.get("sleeper.ingest.memory.max.batch.size"));
    Properties properties = instanceProperties.getProperties();
    assertEquals(1, properties.size());
    assertEquals("1000000,,", properties.get("sleeper.ingest.memory.max.batch.size"));
    Stream<Entry<String, String>> unknownProperties = instanceProperties.getUnknownProperties();
    assertTrue(unknownProperties.limit(5).collect(Collectors.toList()).isEmpty());
  }

  /**
   * Test {@link SleeperProperties#addToListIfMissing(SleeperProperty, List)}.
   * <p>
   * Method under test: {@link SleeperProperties#addToListIfMissing(SleeperProperty, List)}
   */
  @Test
  @DisplayName("Test addToListIfMissing(SleeperProperty, List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SleeperProperties.addToListIfMissing(SleeperProperty, List)"})
  void testAddToListIfMissing3() {
    // Arrange
    InstanceProperties instanceProperties = new InstanceProperties();

    ArrayList<String> list = new ArrayList<>();
    list.add("foo");
    list.add(",");

    // Act
    instanceProperties.addToListIfMissing(ArrayListIngestProperty.MAX_IN_MEMORY_BATCH_SIZE, list);

    // Assert
    Map<String, String> toMapResult = instanceProperties.toMap();
    assertEquals(1, toMapResult.size());
    assertEquals("1000000,foo,,", toMapResult.get("sleeper.ingest.memory.max.batch.size"));
    Properties properties = instanceProperties.getProperties();
    assertEquals(1, properties.size());
    assertEquals("1000000,foo,,", properties.get("sleeper.ingest.memory.max.batch.size"));
    Stream<Entry<String, String>> unknownProperties = instanceProperties.getUnknownProperties();
    assertTrue(unknownProperties.limit(5).collect(Collectors.toList()).isEmpty());
  }

  /**
   * Test {@link SleeperProperties#addToListIfMissing(SleeperProperty, List)}.
   * <ul>
   *   <li>Then {@link InstanceProperties#InstanceProperties()} toMap {@code ,} is empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperProperties#addToListIfMissing(SleeperProperty, List)}
   */
  @Test
  @DisplayName("Test addToListIfMissing(SleeperProperty, List); then InstanceProperties() toMap ',' is empty string")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SleeperProperties.addToListIfMissing(SleeperProperty, List)"})
  void testAddToListIfMissing_thenInstancePropertiesToMapCommaIsEmptyString() {
    // Arrange
    InstanceProperties instanceProperties = new InstanceProperties();
    DummyInstanceProperty dummyInstanceProperty = new DummyInstanceProperty(",");

    // Act
    instanceProperties.addToListIfMissing(dummyInstanceProperty, new ArrayList<>());

    // Assert
    Map<String, String> toMapResult = instanceProperties.toMap();
    assertEquals(1, toMapResult.size());
    assertEquals("", toMapResult.get(","));
    Properties properties = instanceProperties.getProperties();
    assertEquals(1, properties.size());
    assertEquals("", properties.get(","));
    Stream<Entry<String, String>> unknownProperties = instanceProperties.getUnknownProperties();
    assertEquals(1, unknownProperties.limit(5).collect(Collectors.toList()).size());
  }

  /**
   * Test {@link SleeperProperties#setEnum(SleeperProperty, Enum)}.
   * <ul>
   *   <li>When {@link ArrayListIngestProperty#MAX_IN_MEMORY_BATCH_SIZE}.</li>
   *   <li>Then {@link InstanceProperties#InstanceProperties()} toMap Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperProperties#setEnum(SleeperProperty, Object)}
   */
  @Test
  @DisplayName("Test setEnum(SleeperProperty, Enum); when MAX_IN_MEMORY_BATCH_SIZE; then InstanceProperties() toMap Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SleeperProperties.setEnum(SleeperProperty, java.lang.Enum)"})
  void testSetEnum_whenMax_in_memory_batch_size_thenInstancePropertiesToMapEmpty() {
    // Arrange
    InstanceProperties instanceProperties = new InstanceProperties();

    // Act
    instanceProperties.setEnum(ArrayListIngestProperty.MAX_IN_MEMORY_BATCH_SIZE, null);

    // Assert
    assertTrue(instanceProperties.toMap().isEmpty());
    Properties properties = instanceProperties.getProperties();
    assertTrue(properties.isEmpty());
    assertEquals(properties, instanceProperties.getTagsProperties());
  }

  /**
   * Test {@link SleeperProperties#setEnum(SleeperProperty, Enum)}.
   * <ul>
   *   <li>When {@link ArrayListIngestProperty#MAX_IN_MEMORY_BATCH_SIZE}.</li>
   *   <li>Then {@link InstanceProperties#InstanceProperties()} toMap size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperProperties#setEnum(SleeperProperty, Object)}
   */
  @Test
  @DisplayName("Test setEnum(SleeperProperty, Enum); when MAX_IN_MEMORY_BATCH_SIZE; then InstanceProperties() toMap size is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SleeperProperties.setEnum(SleeperProperty, java.lang.Enum)"})
  void testSetEnum_whenMax_in_memory_batch_size_thenInstancePropertiesToMapSizeIsOne() {
    // Arrange
    InstanceProperties instanceProperties = new InstanceProperties();

    // Act
    instanceProperties.setEnum(ArrayListIngestProperty.MAX_IN_MEMORY_BATCH_SIZE,
        BaselineResizeBehavior.CONSTANT_ASCENT);

    // Assert
    Map<String, String> toMapResult = instanceProperties.toMap();
    assertEquals(1, toMapResult.size());
    assertEquals("constant_ascent", toMapResult.get("sleeper.ingest.memory.max.batch.size"));
    Properties properties = instanceProperties.getProperties();
    assertEquals(1, properties.size());
    assertEquals("constant_ascent", properties.get("sleeper.ingest.memory.max.batch.size"));
    assertTrue(instanceProperties.getTagsProperties().isEmpty());
  }

  /**
   * Test {@link SleeperProperties#isAnyPropertySetStartingWith(String)}.
   * <ul>
   *   <li>Given {@link InstanceProperties#InstanceProperties()}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperProperties#isAnyPropertySetStartingWith(String)}
   */
  @Test
  @DisplayName("Test isAnyPropertySetStartingWith(String); given InstanceProperties(); then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperProperties.isAnyPropertySetStartingWith(String)"})
  void testIsAnyPropertySetStartingWith_givenInstanceProperties_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new InstanceProperties()).isAnyPropertySetStartingWith("Property Name Start"));
  }

  /**
   * Test {@link SleeperProperties#isAnyPropertySetStartingWith(String)}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperProperties#isAnyPropertySetStartingWith(String)}
   */
  @Test
  @DisplayName("Test isAnyPropertySetStartingWith(String); then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperProperties.isAnyPropertySetStartingWith(String)"})
  void testIsAnyPropertySetStartingWith_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(InstancePropertiesTestHelper.createTestInstancePropertiesWithId("42")
        .isAnyPropertySetStartingWith("Property Name Start"));
  }

  /**
   * Test {@link SleeperProperties#isAnyPropertySetStartingWith(String)}.
   * <ul>
   *   <li>When empty string.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperProperties#isAnyPropertySetStartingWith(String)}
   */
  @Test
  @DisplayName("Test isAnyPropertySetStartingWith(String); when empty string; then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperProperties.isAnyPropertySetStartingWith(String)"})
  void testIsAnyPropertySetStartingWith_whenEmptyString_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(InstancePropertiesTestHelper.createTestInstancePropertiesWithId("42").isAnyPropertySetStartingWith(""));
  }

  /**
   * Test {@link SleeperProperties#isSet(SleeperProperty)}.
   * <ul>
   *   <li>Given createTestInstancePropertiesWithId {@code 42}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperProperties#isSet(SleeperProperty)}
   */
  @Test
  @DisplayName("Test isSet(SleeperProperty); given createTestInstancePropertiesWithId '42'; then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperProperties.isSet(SleeperProperty)"})
  void testIsSet_givenCreateTestInstancePropertiesWithId42_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(InstancePropertiesTestHelper.createTestInstancePropertiesWithId("42")
        .isSet(ArrayListIngestProperty.MAX_IN_MEMORY_BATCH_SIZE));
  }

  /**
   * Test {@link SleeperProperties#isSet(SleeperProperty)}.
   * <ul>
   *   <li>Given {@link InstanceProperties#InstanceProperties()} {@link ArrayListIngestProperty#MAX_IN_MEMORY_BATCH_SIZE} is empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperProperties#isSet(SleeperProperty)}
   */
  @Test
  @DisplayName("Test isSet(SleeperProperty); given InstanceProperties() MAX_IN_MEMORY_BATCH_SIZE is empty string")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperProperties.isSet(SleeperProperty)"})
  void testIsSet_givenInstancePropertiesMax_in_memory_batch_sizeIsEmptyString() {
    // Arrange
    InstanceProperties instanceProperties = new InstanceProperties();
    instanceProperties.set(ArrayListIngestProperty.MAX_IN_MEMORY_BATCH_SIZE, "");

    // Act and Assert
    assertFalse(instanceProperties.isSet(ArrayListIngestProperty.MAX_IN_MEMORY_BATCH_SIZE));
  }

  /**
   * Test {@link SleeperProperties#isSet(SleeperProperty)}.
   * <ul>
   *   <li>Given {@link InstanceProperties#InstanceProperties()}.</li>
   *   <li>When {@link ArrayListIngestProperty#MAX_IN_MEMORY_BATCH_SIZE}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperProperties#isSet(SleeperProperty)}
   */
  @Test
  @DisplayName("Test isSet(SleeperProperty); given InstanceProperties(); when MAX_IN_MEMORY_BATCH_SIZE; then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperProperties.isSet(SleeperProperty)"})
  void testIsSet_givenInstanceProperties_whenMax_in_memory_batch_size_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new InstanceProperties()).isSet(ArrayListIngestProperty.MAX_IN_MEMORY_BATCH_SIZE));
  }

  /**
   * Test {@link SleeperProperties#streamNonDefaultEntries()}.
   * <p>
   * Method under test: {@link SleeperProperties#streamNonDefaultEntries()}
   */
  @Test
  @DisplayName("Test streamNonDefaultEntries()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream SleeperProperties.streamNonDefaultEntries()"})
  void testStreamNonDefaultEntries() {
    // Arrange and Act
    Stream<Entry<InstanceProperty, String>> actualStreamNonDefaultEntriesResult = (new InstanceProperties())
        .streamNonDefaultEntries();

    // Assert
    assertTrue(actualStreamNonDefaultEntriesResult.limit(5).collect(Collectors.toList()).isEmpty());
  }

  /**
   * Test {@link SleeperProperties#getProperties()}.
   * <p>
   * Method under test: {@link SleeperProperties#getProperties()}
   */
  @Test
  @DisplayName("Test getProperties()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Properties SleeperProperties.getProperties()"})
  void testGetProperties() {
    // Arrange, Act and Assert
    assertTrue((new InstanceProperties()).getProperties().isEmpty());
  }

  /**
   * Test {@link SleeperProperties#save(File)} with {@code File}.
   * <ul>
   *   <li>Then throw {@link UncheckedIOException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperProperties#save(File)}
   */
  @Test
  @DisplayName("Test save(File) with 'File'; then throw UncheckedIOException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SleeperProperties.save(File)"})
  void testSaveWithFile_thenThrowUncheckedIOException() {
    // Arrange
    InstanceProperties instanceProperties = new InstanceProperties();

    // Act and Assert
    assertThrows(UncheckedIOException.class,
        () -> instanceProperties.save(Paths.get(System.getProperty("java.io.tmpdir"), "foo", "42", "foo").toFile()));
  }

  /**
   * Test {@link SleeperProperties#save(OutputStream)} with {@code OutputStream}.
   * <ul>
   *   <li>When {@link PipedOutputStream#PipedOutputStream()}.</li>
   *   <li>Then throw {@link UncheckedIOException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperProperties#save(OutputStream)}
   */
  @Test
  @DisplayName("Test save(OutputStream) with 'OutputStream'; when PipedOutputStream(); then throw UncheckedIOException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SleeperProperties.save(OutputStream)"})
  void testSaveWithOutputStream_whenPipedOutputStream_thenThrowUncheckedIOException() {
    // Arrange
    InstanceProperties instanceProperties = new InstanceProperties();

    // Act and Assert
    assertThrows(UncheckedIOException.class, () -> instanceProperties.save(new PipedOutputStream()));
  }

  /**
   * Test {@link SleeperProperties#save(Path)} with {@code Path}.
   * <ul>
   *   <li>Given {@link Files} {@link Files#newOutputStream(Path, OpenOption[])} return {@link ByteArrayOutputStream#ByteArrayOutputStream(int)} with one.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperProperties#save(Path)}
   */
  @Test
  @DisplayName("Test save(Path) with 'Path'; given Files newOutputStream(Path, OpenOption[]) return ByteArrayOutputStream(int) with one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SleeperProperties.save(Path)"})
  void testSaveWithPath_givenFilesNewOutputStreamReturnByteArrayOutputStreamWithOne() throws IOException {
    try (MockedStatic<Files> mockFiles = mockStatic(Files.class)) {

      // Arrange
      mockFiles.when(() -> Files.newOutputStream(Mockito.<Path>any(), isA(OpenOption[].class)))
          .thenReturn(new ByteArrayOutputStream(1));

      // Act
      (new InstanceProperties()).save(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));

      // Assert
      mockFiles.verify(() -> Files.newOutputStream(Mockito.<Path>any(), isA(OpenOption[].class)));
    }
  }

  /**
   * Test {@link SleeperProperties#save(Path)} with {@code Path}.
   * <ul>
   *   <li>Given {@link Files} {@link Files#newOutputStream(Path, OpenOption[])} throw {@link IOException#IOException(String)} with {@code foo}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperProperties#save(Path)}
   */
  @Test
  @DisplayName("Test save(Path) with 'Path'; given Files newOutputStream(Path, OpenOption[]) throw IOException(String) with 'foo'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SleeperProperties.save(Path)"})
  void testSaveWithPath_givenFilesNewOutputStreamThrowIOExceptionWithFoo() throws IOException {
    try (MockedStatic<Files> mockFiles = mockStatic(Files.class)) {

      // Arrange
      mockFiles.when(() -> Files.newOutputStream(Mockito.<Path>any(), isA(OpenOption[].class)))
          .thenThrow(new IOException("foo"));

      // Act and Assert
      assertThrows(UncheckedIOException.class,
          () -> (new InstanceProperties()).save(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt")));
      mockFiles.verify(() -> Files.newOutputStream(Mockito.<Path>any(), isA(OpenOption[].class)));
    }
  }

  /**
   * Test {@link SleeperProperties#save(Path)} with {@code Path}.
   * <ul>
   *   <li>Given {@link IOException#IOException(String)} with {@code foo}.</li>
   *   <li>Then throw {@link UncheckedIOException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperProperties#save(Path)}
   */
  @Test
  @DisplayName("Test save(Path) with 'Path'; given IOException(String) with 'foo'; then throw UncheckedIOException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SleeperProperties.save(Path)"})
  void testSaveWithPath_givenIOExceptionWithFoo_thenThrowUncheckedIOException() throws IOException {
    try (MockedStatic<Files> mockFiles = mockStatic(Files.class)) {

      // Arrange
      mockFiles.when(() -> Files.newOutputStream(Mockito.<Path>any(), isA(OpenOption[].class)))
          .thenThrow(new UncheckedIOException(new IOException("foo")));

      // Act and Assert
      assertThrows(UncheckedIOException.class,
          () -> (new InstanceProperties()).save(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt")));
      mockFiles.verify(() -> Files.newOutputStream(Mockito.<Path>any(), isA(OpenOption[].class)));
    }
  }

  /**
   * Test {@link SleeperProperties#getUnknownProperties()}.
   * <p>
   * Method under test: {@link SleeperProperties#getUnknownProperties()}
   */
  @Test
  @DisplayName("Test getUnknownProperties()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream SleeperProperties.getUnknownProperties()"})
  void testGetUnknownProperties() {
    // Arrange and Act
    Stream<Entry<String, String>> actualUnknownProperties = (new InstanceProperties()).getUnknownProperties();

    // Assert
    assertTrue(actualUnknownProperties.limit(5).collect(Collectors.toList()).isEmpty());
  }

  /**
   * Test {@link SleeperProperties#toMap()}.
   * <ul>
   *   <li>Given createTestInstancePropertiesWithId {@code 42}.</li>
   *   <li>Then return size is twenty-eight.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperProperties#toMap()}
   */
  @Test
  @DisplayName("Test toMap(); given createTestInstancePropertiesWithId '42'; then return size is twenty-eight")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Map SleeperProperties.toMap()"})
  void testToMap_givenCreateTestInstancePropertiesWithId42_thenReturnSizeIsTwentyEight() {
    // Arrange and Act
    Map<String, String> actualToMapResult = InstancePropertiesTestHelper.createTestInstancePropertiesWithId("42")
        .toMap();

    // Assert
    assertEquals(28, actualToMapResult.size());
    assertEquals("128", actualToMapResult.get("sleeper.ingest.arrow.max.single.write.to.file.records"));
    assertEquals("16777216", actualToMapResult.get("sleeper.ingest.arrow.batch.buffer.bytes"));
    assertEquals("sleeper-42-config", actualToMapResult.get("sleeper.config.bucket"));
    assertEquals("sleeper-42-file-transaction-log",
        actualToMapResult.get("sleeper.statestore.transactionlog.dynamo.file.log.table"));
    assertEquals("sleeper-42-partition-transaction-log",
        actualToMapResult.get("sleeper.statestore.transactionlog.dynamo.partition.log.table"));
    assertEquals("sleeper-42-query-results", actualToMapResult.get("sleeper.query.results.bucket"));
    assertEquals("sleeper-42-query-tracking-table", actualToMapResult.get("sleeper.query.tracker.table.name"));
    assertEquals("sleeper-42-transaction-log-all-snapshots",
        actualToMapResult.get("sleeper.statestore.transactionlog.dynamo.all.snapshots.table"));
    assertEquals("test-bucket", actualToMapResult.get("sleeper.jars.bucket"));
    assertEquals("test-subnet", actualToMapResult.get("sleeper.subnets"));
    assertEquals("test-vpc", actualToMapResult.get("sleeper.vpc"));
  }

  /**
   * Test {@link SleeperProperties#toMap()}.
   * <ul>
   *   <li>Given {@link InstanceProperties#InstanceProperties()}.</li>
   *   <li>Then return Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperProperties#toMap()}
   */
  @Test
  @DisplayName("Test toMap(); given InstanceProperties(); then return Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Map SleeperProperties.toMap()"})
  void testToMap_givenInstanceProperties_thenReturnEmpty() {
    // Arrange, Act and Assert
    assertTrue((new InstanceProperties()).toMap().isEmpty());
  }

  /**
   * Test {@link SleeperProperties#equals(Object)}, and {@link SleeperProperties#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link SleeperProperties#equals(Object)}
   *   <li>{@link SleeperProperties#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperProperties.equals(Object)", "int SleeperProperties.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    InstanceProperties createTestInstancePropertiesWithIdResult = InstancePropertiesTestHelper
        .createTestInstancePropertiesWithId("42");
    InstanceProperties createTestInstancePropertiesWithIdResult2 = InstancePropertiesTestHelper
        .createTestInstancePropertiesWithId("42");

    // Act and Assert
    assertEquals(createTestInstancePropertiesWithIdResult, createTestInstancePropertiesWithIdResult2);
    int expectedHashCodeResult = createTestInstancePropertiesWithIdResult.hashCode();
    assertEquals(expectedHashCodeResult, createTestInstancePropertiesWithIdResult2.hashCode());
  }

  /**
   * Test {@link SleeperProperties#equals(Object)}, and {@link SleeperProperties#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link SleeperProperties#equals(Object)}
   *   <li>{@link SleeperProperties#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperProperties.equals(Object)", "int SleeperProperties.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    InstanceProperties createTestInstancePropertiesWithIdResult = InstancePropertiesTestHelper
        .createTestInstancePropertiesWithId("42");

    // Act and Assert
    assertEquals(createTestInstancePropertiesWithIdResult, createTestInstancePropertiesWithIdResult);
    int expectedHashCodeResult = createTestInstancePropertiesWithIdResult.hashCode();
    assertEquals(expectedHashCodeResult, createTestInstancePropertiesWithIdResult.hashCode());
  }

  /**
   * Test {@link SleeperProperties#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperProperties#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperProperties.equals(Object)", "int SleeperProperties.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    InstanceProperties createTestInstancePropertiesWithIdResult = InstancePropertiesTestHelper
        .createTestInstancePropertiesWithId("Id");

    // Act and Assert
    assertNotEquals(createTestInstancePropertiesWithIdResult,
        InstancePropertiesTestHelper.createTestInstancePropertiesWithId("42"));
  }

  /**
   * Test {@link SleeperProperties#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperProperties#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperProperties.equals(Object)", "int SleeperProperties.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(InstancePropertiesTestHelper.createTestInstancePropertiesWithId("42"), null);
  }

  /**
   * Test {@link SleeperProperties#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperProperties#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperProperties.equals(Object)", "int SleeperProperties.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(InstancePropertiesTestHelper.createTestInstancePropertiesWithId("42"),
        "Different type to SleeperProperties");
  }
}
