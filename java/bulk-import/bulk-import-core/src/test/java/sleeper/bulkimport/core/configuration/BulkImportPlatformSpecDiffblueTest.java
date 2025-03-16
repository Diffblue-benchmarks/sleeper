package sleeper.bulkimport.core.configuration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.bulkimport.core.job.BulkImportJob;
import sleeper.bulkimport.core.job.BulkImportJob.Builder;
import sleeper.core.properties.SleeperProperty;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.table.TableProperties;
import sleeper.core.properties.table.TableProperty;
import sleeper.core.properties.table.TablePropertyComputeValue;

class BulkImportPlatformSpecDiffblueTest {
  /**
   * Test {@link BulkImportPlatformSpec#BulkImportPlatformSpec(TableProperties, BulkImportJob)}.
   * <p>
   * Method under test: {@link BulkImportPlatformSpec#BulkImportPlatformSpec(TableProperties, BulkImportJob)}
   */
  @Test
  @DisplayName("Test new BulkImportPlatformSpec(TableProperties, BulkImportJob)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void BulkImportPlatformSpec.<init>(TableProperties, BulkImportJob)"})
  void testNewBulkImportPlatformSpec() {
    // Arrange
    TableProperties tableProperties = new TableProperties(new InstanceProperties());
    Builder classNameResult = BulkImportJob.builder().className("Class Name");
    Builder idResult = classNameResult.files(new ArrayList<>()).id("42");
    BulkImportJob job = idResult.platformSpec(new HashMap<>())
        .sparkConf("Key", "42")
        .tableId("42")
        .tableName("Table Name")
        .build();

    // Act and Assert
    assertSame(tableProperties, (new BulkImportPlatformSpec(tableProperties, job)).getTableProperties());
  }

  /**
   * Test {@link BulkImportPlatformSpec#get(TableProperty)}.
   * <ul>
   *   <li>Given {@link TableProperties#TableProperties(InstanceProperties)} with instanceProperties is {@link InstanceProperties#InstanceProperties()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BulkImportPlatformSpec#get(TableProperty)}
   */
  @Test
  @DisplayName("Test get(TableProperty); given TableProperties(InstanceProperties) with instanceProperties is InstanceProperties()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String BulkImportPlatformSpec.get(TableProperty)"})
  void testGet_givenTablePropertiesWithInstancePropertiesIsInstanceProperties() {
    // Arrange
    TableProperties tableProperties = new TableProperties(new InstanceProperties());
    Builder classNameResult = BulkImportJob.builder().className("Class Name");
    Builder idResult = classNameResult.files(new ArrayList<>()).id("42");
    BulkImportJob job = idResult.platformSpec(new HashMap<>())
        .sparkConf("Key", "42")
        .tableId("42")
        .tableName("Table Name")
        .build();
    BulkImportPlatformSpec bulkImportPlatformSpec = new BulkImportPlatformSpec(tableProperties, job);
    TableProperty property = mock(TableProperty.class);
    when(
        property.computeValue(Mockito.<String>any(), Mockito.<InstanceProperties>any(), Mockito.<TableProperties>any()))
        .thenReturn("42");
    when(property.isIgnoreEmptyValue()).thenReturn(true);
    when(property.getPropertyName()).thenReturn("Property Name");

    // Act
    String actualGetResult = bulkImportPlatformSpec.get(property);

    // Assert
    verify(property, atLeast(1)).getPropertyName();
    verify(property).isIgnoreEmptyValue();
    verify(property).computeValue(isNull(), isA(InstanceProperties.class), isA(TableProperties.class));
    assertEquals("42", actualGetResult);
  }

  /**
   * Test {@link BulkImportPlatformSpec#get(TableProperty)}.
   * <ul>
   *   <li>Given {@link TableProperty} {@link TablePropertyComputeValue#computeValue(String, InstanceProperties, TableProperties)} return {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BulkImportPlatformSpec#get(TableProperty)}
   */
  @Test
  @DisplayName("Test get(TableProperty); given TableProperty computeValue(String, InstanceProperties, TableProperties) return '42'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String BulkImportPlatformSpec.get(TableProperty)"})
  void testGet_givenTablePropertyComputeValueReturn42() {
    // Arrange
    TableProperty tableProperty = mock(TableProperty.class);
    when(tableProperty.computeValue(Mockito.<String>any(), Mockito.<InstanceProperties>any(),
        Mockito.<TableProperties>any())).thenReturn("42");
    when(tableProperty.isIgnoreEmptyValue()).thenReturn(true);
    when(tableProperty.getPropertyName()).thenReturn("Property Name");

    TableProperties tableProperties = new TableProperties(new InstanceProperties());
    tableProperties.addToListIfMissing(tableProperty, new ArrayList<>());
    Builder classNameResult = BulkImportJob.builder().className("Class Name");
    Builder idResult = classNameResult.files(new ArrayList<>()).id("42");
    BulkImportJob job = idResult.platformSpec(new HashMap<>())
        .sparkConf("Key", "42")
        .tableId("42")
        .tableName("Table Name")
        .build();
    BulkImportPlatformSpec bulkImportPlatformSpec = new BulkImportPlatformSpec(tableProperties, job);
    TableProperty property = mock(TableProperty.class);
    when(
        property.computeValue(Mockito.<String>any(), Mockito.<InstanceProperties>any(), Mockito.<TableProperties>any()))
        .thenReturn("42");
    when(property.isIgnoreEmptyValue()).thenReturn(false);
    when(property.getPropertyName()).thenReturn("Property Name");

    // Act
    String actualGetResult = bulkImportPlatformSpec.get(property);

    // Assert
    verify(tableProperty, atLeast(1)).getPropertyName();
    verify(property, atLeast(1)).getPropertyName();
    verify(tableProperty).isIgnoreEmptyValue();
    verify(property).isIgnoreEmptyValue();
    verify(property).computeValue(eq("42"), isA(InstanceProperties.class), isA(TableProperties.class));
    verify(tableProperty).computeValue(isNull(), isA(InstanceProperties.class), isA(TableProperties.class));
    assertEquals("42", actualGetResult);
  }

  /**
   * Test {@link BulkImportPlatformSpec#get(TableProperty)}.
   * <ul>
   *   <li>Given {@link TableProperty} {@link TablePropertyComputeValue#computeValue(String, InstanceProperties, TableProperties)} return empty string.</li>
   *   <li>Then return {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BulkImportPlatformSpec#get(TableProperty)}
   */
  @Test
  @DisplayName("Test get(TableProperty); given TableProperty computeValue(String, InstanceProperties, TableProperties) return empty string; then return '42'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String BulkImportPlatformSpec.get(TableProperty)"})
  void testGet_givenTablePropertyComputeValueReturnEmptyString_thenReturn42() {
    // Arrange
    TableProperty tableProperty = mock(TableProperty.class);
    when(tableProperty.computeValue(Mockito.<String>any(), Mockito.<InstanceProperties>any(),
        Mockito.<TableProperties>any())).thenReturn("");
    when(tableProperty.isIgnoreEmptyValue()).thenReturn(true);
    when(tableProperty.getPropertyName()).thenReturn("Property Name");

    TableProperties tableProperties = new TableProperties(new InstanceProperties());
    tableProperties.addToListIfMissing(tableProperty, new ArrayList<>());
    Builder classNameResult = BulkImportJob.builder().className("Class Name");
    Builder idResult = classNameResult.files(new ArrayList<>()).id("42");
    BulkImportJob job = idResult.platformSpec(new HashMap<>())
        .sparkConf("Key", "42")
        .tableId("42")
        .tableName("Table Name")
        .build();
    BulkImportPlatformSpec bulkImportPlatformSpec = new BulkImportPlatformSpec(tableProperties, job);
    TableProperty property = mock(TableProperty.class);
    when(
        property.computeValue(Mockito.<String>any(), Mockito.<InstanceProperties>any(), Mockito.<TableProperties>any()))
        .thenReturn("42");
    when(property.isIgnoreEmptyValue()).thenReturn(true);
    when(property.getPropertyName()).thenReturn("Property Name");

    // Act
    String actualGetResult = bulkImportPlatformSpec.get(property);

    // Assert
    verify(tableProperty, atLeast(1)).getPropertyName();
    verify(property, atLeast(1)).getPropertyName();
    verify(tableProperty).isIgnoreEmptyValue();
    verify(property).isIgnoreEmptyValue();
    verify(tableProperty).computeValue(isNull(), isA(InstanceProperties.class), isA(TableProperties.class));
    verify(property).computeValue(isNull(), isA(InstanceProperties.class), isA(TableProperties.class));
    assertEquals("42", actualGetResult);
  }

  /**
   * Test {@link BulkImportPlatformSpec#getBoolean(TableProperty)}.
   * <ul>
   *   <li>Given {@link TableProperties#TableProperties(InstanceProperties)} with instanceProperties is {@link InstanceProperties#InstanceProperties()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BulkImportPlatformSpec#getBoolean(TableProperty)}
   */
  @Test
  @DisplayName("Test getBoolean(TableProperty); given TableProperties(InstanceProperties) with instanceProperties is InstanceProperties()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean BulkImportPlatformSpec.getBoolean(TableProperty)"})
  void testGetBoolean_givenTablePropertiesWithInstancePropertiesIsInstanceProperties() {
    // Arrange
    TableProperties tableProperties = new TableProperties(new InstanceProperties());
    Builder classNameResult = BulkImportJob.builder().className("Class Name");
    Builder idResult = classNameResult.files(new ArrayList<>()).id("42");
    BulkImportJob job = idResult.platformSpec(new HashMap<>())
        .sparkConf("Key", "42")
        .tableId("42")
        .tableName("Table Name")
        .build();
    BulkImportPlatformSpec bulkImportPlatformSpec = new BulkImportPlatformSpec(tableProperties, job);
    TableProperty property = mock(TableProperty.class);
    when(
        property.computeValue(Mockito.<String>any(), Mockito.<InstanceProperties>any(), Mockito.<TableProperties>any()))
        .thenReturn("42");
    when(property.isIgnoreEmptyValue()).thenReturn(true);
    when(property.getPropertyName()).thenReturn("Property Name");

    // Act
    boolean actualBoolean = bulkImportPlatformSpec.getBoolean(property);

    // Assert
    verify(property, atLeast(1)).getPropertyName();
    verify(property).isIgnoreEmptyValue();
    verify(property).computeValue(isNull(), isA(InstanceProperties.class), isA(TableProperties.class));
    assertFalse(actualBoolean);
  }

  /**
   * Test {@link BulkImportPlatformSpec#getBoolean(TableProperty)}.
   * <ul>
   *   <li>Given {@link TableProperty} {@link TablePropertyComputeValue#computeValue(String, InstanceProperties, TableProperties)} return {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BulkImportPlatformSpec#getBoolean(TableProperty)}
   */
  @Test
  @DisplayName("Test getBoolean(TableProperty); given TableProperty computeValue(String, InstanceProperties, TableProperties) return '42'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean BulkImportPlatformSpec.getBoolean(TableProperty)"})
  void testGetBoolean_givenTablePropertyComputeValueReturn42() {
    // Arrange
    TableProperty tableProperty = mock(TableProperty.class);
    when(tableProperty.computeValue(Mockito.<String>any(), Mockito.<InstanceProperties>any(),
        Mockito.<TableProperties>any())).thenReturn("42");
    when(tableProperty.isIgnoreEmptyValue()).thenReturn(true);
    when(tableProperty.getPropertyName()).thenReturn("Property Name");

    TableProperties tableProperties = new TableProperties(new InstanceProperties());
    tableProperties.addToListIfMissing(tableProperty, new ArrayList<>());
    Builder classNameResult = BulkImportJob.builder().className("Class Name");
    Builder idResult = classNameResult.files(new ArrayList<>()).id("42");
    BulkImportJob job = idResult.platformSpec(new HashMap<>())
        .sparkConf("Key", "42")
        .tableId("42")
        .tableName("Table Name")
        .build();
    BulkImportPlatformSpec bulkImportPlatformSpec = new BulkImportPlatformSpec(tableProperties, job);
    TableProperty property = mock(TableProperty.class);
    when(
        property.computeValue(Mockito.<String>any(), Mockito.<InstanceProperties>any(), Mockito.<TableProperties>any()))
        .thenReturn("42");
    when(property.isIgnoreEmptyValue()).thenReturn(false);
    when(property.getPropertyName()).thenReturn("Property Name");

    // Act
    boolean actualBoolean = bulkImportPlatformSpec.getBoolean(property);

    // Assert
    verify(tableProperty, atLeast(1)).getPropertyName();
    verify(property, atLeast(1)).getPropertyName();
    verify(tableProperty).isIgnoreEmptyValue();
    verify(property).isIgnoreEmptyValue();
    verify(property).computeValue(eq("42"), isA(InstanceProperties.class), isA(TableProperties.class));
    verify(tableProperty).computeValue(isNull(), isA(InstanceProperties.class), isA(TableProperties.class));
    assertFalse(actualBoolean);
  }

  /**
   * Test {@link BulkImportPlatformSpec#getBoolean(TableProperty)}.
   * <ul>
   *   <li>Given {@link TableProperty} {@link TablePropertyComputeValue#computeValue(String, InstanceProperties, TableProperties)} return empty string.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BulkImportPlatformSpec#getBoolean(TableProperty)}
   */
  @Test
  @DisplayName("Test getBoolean(TableProperty); given TableProperty computeValue(String, InstanceProperties, TableProperties) return empty string; then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean BulkImportPlatformSpec.getBoolean(TableProperty)"})
  void testGetBoolean_givenTablePropertyComputeValueReturnEmptyString_thenReturnFalse() {
    // Arrange
    TableProperty tableProperty = mock(TableProperty.class);
    when(tableProperty.computeValue(Mockito.<String>any(), Mockito.<InstanceProperties>any(),
        Mockito.<TableProperties>any())).thenReturn("");
    when(tableProperty.isIgnoreEmptyValue()).thenReturn(true);
    when(tableProperty.getPropertyName()).thenReturn("Property Name");

    TableProperties tableProperties = new TableProperties(new InstanceProperties());
    tableProperties.addToListIfMissing(tableProperty, new ArrayList<>());
    Builder classNameResult = BulkImportJob.builder().className("Class Name");
    Builder idResult = classNameResult.files(new ArrayList<>()).id("42");
    BulkImportJob job = idResult.platformSpec(new HashMap<>())
        .sparkConf("Key", "42")
        .tableId("42")
        .tableName("Table Name")
        .build();
    BulkImportPlatformSpec bulkImportPlatformSpec = new BulkImportPlatformSpec(tableProperties, job);
    TableProperty property = mock(TableProperty.class);
    when(
        property.computeValue(Mockito.<String>any(), Mockito.<InstanceProperties>any(), Mockito.<TableProperties>any()))
        .thenReturn("42");
    when(property.isIgnoreEmptyValue()).thenReturn(true);
    when(property.getPropertyName()).thenReturn("Property Name");

    // Act
    boolean actualBoolean = bulkImportPlatformSpec.getBoolean(property);

    // Assert
    verify(tableProperty, atLeast(1)).getPropertyName();
    verify(property, atLeast(1)).getPropertyName();
    verify(tableProperty).isIgnoreEmptyValue();
    verify(property).isIgnoreEmptyValue();
    verify(tableProperty).computeValue(isNull(), isA(InstanceProperties.class), isA(TableProperties.class));
    verify(property).computeValue(isNull(), isA(InstanceProperties.class), isA(TableProperties.class));
    assertFalse(actualBoolean);
  }

  /**
   * Test {@link BulkImportPlatformSpec#getInt(TableProperty)}.
   * <ul>
   *   <li>Given {@code false}.</li>
   *   <li>When {@link TableProperty} {@link SleeperProperty#isIgnoreEmptyValue()} return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BulkImportPlatformSpec#getInt(TableProperty)}
   */
  @Test
  @DisplayName("Test getInt(TableProperty); given 'false'; when TableProperty isIgnoreEmptyValue() return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"int BulkImportPlatformSpec.getInt(TableProperty)"})
  void testGetInt_givenFalse_whenTablePropertyIsIgnoreEmptyValueReturnFalse() {
    // Arrange
    TableProperty tableProperty = mock(TableProperty.class);
    when(tableProperty.computeValue(Mockito.<String>any(), Mockito.<InstanceProperties>any(),
        Mockito.<TableProperties>any())).thenReturn("42");
    when(tableProperty.isIgnoreEmptyValue()).thenReturn(true);
    when(tableProperty.getPropertyName()).thenReturn("Property Name");

    TableProperties tableProperties = new TableProperties(new InstanceProperties());
    tableProperties.addToListIfMissing(tableProperty, new ArrayList<>());
    Builder classNameResult = BulkImportJob.builder().className("Class Name");
    Builder idResult = classNameResult.files(new ArrayList<>()).id("42");
    BulkImportJob job = idResult.platformSpec(new HashMap<>())
        .sparkConf("Key", "42")
        .tableId("42")
        .tableName("Table Name")
        .build();
    BulkImportPlatformSpec bulkImportPlatformSpec = new BulkImportPlatformSpec(tableProperties, job);
    TableProperty property = mock(TableProperty.class);
    when(
        property.computeValue(Mockito.<String>any(), Mockito.<InstanceProperties>any(), Mockito.<TableProperties>any()))
        .thenReturn("42");
    when(property.isIgnoreEmptyValue()).thenReturn(false);
    when(property.getPropertyName()).thenReturn("Property Name");

    // Act
    int actualInt = bulkImportPlatformSpec.getInt(property);

    // Assert
    verify(tableProperty, atLeast(1)).getPropertyName();
    verify(property, atLeast(1)).getPropertyName();
    verify(tableProperty).isIgnoreEmptyValue();
    verify(property).isIgnoreEmptyValue();
    verify(property).computeValue(eq("42"), isA(InstanceProperties.class), isA(TableProperties.class));
    verify(tableProperty).computeValue(isNull(), isA(InstanceProperties.class), isA(TableProperties.class));
    assertEquals(42, actualInt);
  }

  /**
   * Test {@link BulkImportPlatformSpec#getInt(TableProperty)}.
   * <ul>
   *   <li>Given {@link TableProperties#TableProperties(InstanceProperties)} with instanceProperties is {@link InstanceProperties#InstanceProperties()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BulkImportPlatformSpec#getInt(TableProperty)}
   */
  @Test
  @DisplayName("Test getInt(TableProperty); given TableProperties(InstanceProperties) with instanceProperties is InstanceProperties()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"int BulkImportPlatformSpec.getInt(TableProperty)"})
  void testGetInt_givenTablePropertiesWithInstancePropertiesIsInstanceProperties() {
    // Arrange
    TableProperties tableProperties = new TableProperties(new InstanceProperties());
    Builder classNameResult = BulkImportJob.builder().className("Class Name");
    Builder idResult = classNameResult.files(new ArrayList<>()).id("42");
    BulkImportJob job = idResult.platformSpec(new HashMap<>())
        .sparkConf("Key", "42")
        .tableId("42")
        .tableName("Table Name")
        .build();
    BulkImportPlatformSpec bulkImportPlatformSpec = new BulkImportPlatformSpec(tableProperties, job);
    TableProperty property = mock(TableProperty.class);
    when(
        property.computeValue(Mockito.<String>any(), Mockito.<InstanceProperties>any(), Mockito.<TableProperties>any()))
        .thenReturn("42");
    when(property.isIgnoreEmptyValue()).thenReturn(true);
    when(property.getPropertyName()).thenReturn("Property Name");

    // Act
    int actualInt = bulkImportPlatformSpec.getInt(property);

    // Assert
    verify(property, atLeast(1)).getPropertyName();
    verify(property).isIgnoreEmptyValue();
    verify(property).computeValue(isNull(), isA(InstanceProperties.class), isA(TableProperties.class));
    assertEquals(42, actualInt);
  }

  /**
   * Test {@link BulkImportPlatformSpec#getInt(TableProperty)}.
   * <ul>
   *   <li>Given {@link TableProperty} {@link TablePropertyComputeValue#computeValue(String, InstanceProperties, TableProperties)} return empty string.</li>
   *   <li>Then return forty-two.</li>
   * </ul>
   * <p>
   * Method under test: {@link BulkImportPlatformSpec#getInt(TableProperty)}
   */
  @Test
  @DisplayName("Test getInt(TableProperty); given TableProperty computeValue(String, InstanceProperties, TableProperties) return empty string; then return forty-two")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"int BulkImportPlatformSpec.getInt(TableProperty)"})
  void testGetInt_givenTablePropertyComputeValueReturnEmptyString_thenReturnFortyTwo() {
    // Arrange
    TableProperty tableProperty = mock(TableProperty.class);
    when(tableProperty.computeValue(Mockito.<String>any(), Mockito.<InstanceProperties>any(),
        Mockito.<TableProperties>any())).thenReturn("");
    when(tableProperty.isIgnoreEmptyValue()).thenReturn(true);
    when(tableProperty.getPropertyName()).thenReturn("Property Name");

    TableProperties tableProperties = new TableProperties(new InstanceProperties());
    tableProperties.addToListIfMissing(tableProperty, new ArrayList<>());
    Builder classNameResult = BulkImportJob.builder().className("Class Name");
    Builder idResult = classNameResult.files(new ArrayList<>()).id("42");
    BulkImportJob job = idResult.platformSpec(new HashMap<>())
        .sparkConf("Key", "42")
        .tableId("42")
        .tableName("Table Name")
        .build();
    BulkImportPlatformSpec bulkImportPlatformSpec = new BulkImportPlatformSpec(tableProperties, job);
    TableProperty property = mock(TableProperty.class);
    when(
        property.computeValue(Mockito.<String>any(), Mockito.<InstanceProperties>any(), Mockito.<TableProperties>any()))
        .thenReturn("42");
    when(property.isIgnoreEmptyValue()).thenReturn(true);
    when(property.getPropertyName()).thenReturn("Property Name");

    // Act
    int actualInt = bulkImportPlatformSpec.getInt(property);

    // Assert
    verify(tableProperty, atLeast(1)).getPropertyName();
    verify(property, atLeast(1)).getPropertyName();
    verify(tableProperty).isIgnoreEmptyValue();
    verify(property).isIgnoreEmptyValue();
    verify(tableProperty).computeValue(isNull(), isA(InstanceProperties.class), isA(TableProperties.class));
    verify(property).computeValue(isNull(), isA(InstanceProperties.class), isA(TableProperties.class));
    assertEquals(42, actualInt);
  }

  /**
   * Test {@link BulkImportPlatformSpec#getList(TableProperty)}.
   * <ul>
   *   <li>Given empty string.</li>
   *   <li>Then return Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link BulkImportPlatformSpec#getList(TableProperty)}
   */
  @Test
  @DisplayName("Test getList(TableProperty); given empty string; then return Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List BulkImportPlatformSpec.getList(TableProperty)"})
  void testGetList_givenEmptyString_thenReturnEmpty() {
    // Arrange
    TableProperty tableProperty = mock(TableProperty.class);
    when(tableProperty.computeValue(Mockito.<String>any(), Mockito.<InstanceProperties>any(),
        Mockito.<TableProperties>any())).thenReturn("42");
    when(tableProperty.isIgnoreEmptyValue()).thenReturn(true);
    when(tableProperty.getPropertyName()).thenReturn("Property Name");

    TableProperties tableProperties = new TableProperties(new InstanceProperties());
    tableProperties.addToListIfMissing(tableProperty, new ArrayList<>());
    Builder classNameResult = BulkImportJob.builder().className("Class Name");
    Builder idResult = classNameResult.files(new ArrayList<>()).id("42");
    BulkImportJob job = idResult.platformSpec(new HashMap<>())
        .sparkConf("Key", "42")
        .tableId("42")
        .tableName("Table Name")
        .build();
    BulkImportPlatformSpec bulkImportPlatformSpec = new BulkImportPlatformSpec(tableProperties, job);
    TableProperty property = mock(TableProperty.class);
    when(
        property.computeValue(Mockito.<String>any(), Mockito.<InstanceProperties>any(), Mockito.<TableProperties>any()))
        .thenReturn("");
    when(property.isIgnoreEmptyValue()).thenReturn(true);
    when(property.getPropertyName()).thenReturn("Property Name");

    // Act
    List<String> actualList = bulkImportPlatformSpec.getList(property);

    // Assert
    verify(tableProperty, atLeast(1)).getPropertyName();
    verify(property, atLeast(1)).getPropertyName();
    verify(tableProperty).isIgnoreEmptyValue();
    verify(property).isIgnoreEmptyValue();
    verify(property).computeValue(eq("42"), isA(InstanceProperties.class), isA(TableProperties.class));
    verify(tableProperty).computeValue(isNull(), isA(InstanceProperties.class), isA(TableProperties.class));
    assertTrue(actualList.isEmpty());
  }

  /**
   * Test {@link BulkImportPlatformSpec#getList(TableProperty)}.
   * <ul>
   *   <li>Given {@code false}.</li>
   *   <li>When {@link TableProperty} {@link SleeperProperty#isIgnoreEmptyValue()} return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BulkImportPlatformSpec#getList(TableProperty)}
   */
  @Test
  @DisplayName("Test getList(TableProperty); given 'false'; when TableProperty isIgnoreEmptyValue() return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List BulkImportPlatformSpec.getList(TableProperty)"})
  void testGetList_givenFalse_whenTablePropertyIsIgnoreEmptyValueReturnFalse() {
    // Arrange
    TableProperty tableProperty = mock(TableProperty.class);
    when(tableProperty.computeValue(Mockito.<String>any(), Mockito.<InstanceProperties>any(),
        Mockito.<TableProperties>any())).thenReturn("42");
    when(tableProperty.isIgnoreEmptyValue()).thenReturn(true);
    when(tableProperty.getPropertyName()).thenReturn("Property Name");

    TableProperties tableProperties = new TableProperties(new InstanceProperties());
    tableProperties.addToListIfMissing(tableProperty, new ArrayList<>());
    Builder classNameResult = BulkImportJob.builder().className("Class Name");
    Builder idResult = classNameResult.files(new ArrayList<>()).id("42");
    BulkImportJob job = idResult.platformSpec(new HashMap<>())
        .sparkConf("Key", "42")
        .tableId("42")
        .tableName("Table Name")
        .build();
    BulkImportPlatformSpec bulkImportPlatformSpec = new BulkImportPlatformSpec(tableProperties, job);
    TableProperty property = mock(TableProperty.class);
    when(
        property.computeValue(Mockito.<String>any(), Mockito.<InstanceProperties>any(), Mockito.<TableProperties>any()))
        .thenReturn("42");
    when(property.isIgnoreEmptyValue()).thenReturn(false);
    when(property.getPropertyName()).thenReturn("Property Name");

    // Act
    List<String> actualList = bulkImportPlatformSpec.getList(property);

    // Assert
    verify(tableProperty, atLeast(1)).getPropertyName();
    verify(property, atLeast(1)).getPropertyName();
    verify(tableProperty).isIgnoreEmptyValue();
    verify(property).isIgnoreEmptyValue();
    verify(property).computeValue(eq("42"), isA(InstanceProperties.class), isA(TableProperties.class));
    verify(tableProperty).computeValue(isNull(), isA(InstanceProperties.class), isA(TableProperties.class));
    assertEquals(1, actualList.size());
    assertEquals("42", actualList.get(0));
  }

  /**
   * Test {@link BulkImportPlatformSpec#getList(TableProperty)}.
   * <ul>
   *   <li>Given {@link TableProperties#TableProperties(InstanceProperties)} with instanceProperties is {@link InstanceProperties#InstanceProperties()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BulkImportPlatformSpec#getList(TableProperty)}
   */
  @Test
  @DisplayName("Test getList(TableProperty); given TableProperties(InstanceProperties) with instanceProperties is InstanceProperties()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List BulkImportPlatformSpec.getList(TableProperty)"})
  void testGetList_givenTablePropertiesWithInstancePropertiesIsInstanceProperties() {
    // Arrange
    TableProperties tableProperties = new TableProperties(new InstanceProperties());
    Builder classNameResult = BulkImportJob.builder().className("Class Name");
    Builder idResult = classNameResult.files(new ArrayList<>()).id("42");
    BulkImportJob job = idResult.platformSpec(new HashMap<>())
        .sparkConf("Key", "42")
        .tableId("42")
        .tableName("Table Name")
        .build();
    BulkImportPlatformSpec bulkImportPlatformSpec = new BulkImportPlatformSpec(tableProperties, job);
    TableProperty property = mock(TableProperty.class);
    when(
        property.computeValue(Mockito.<String>any(), Mockito.<InstanceProperties>any(), Mockito.<TableProperties>any()))
        .thenReturn("42");
    when(property.isIgnoreEmptyValue()).thenReturn(true);
    when(property.getPropertyName()).thenReturn("Property Name");

    // Act
    List<String> actualList = bulkImportPlatformSpec.getList(property);

    // Assert
    verify(property, atLeast(1)).getPropertyName();
    verify(property).isIgnoreEmptyValue();
    verify(property).computeValue(isNull(), isA(InstanceProperties.class), isA(TableProperties.class));
    assertEquals(1, actualList.size());
    assertEquals("42", actualList.get(0));
  }

  /**
   * Test {@link BulkImportPlatformSpec#getList(TableProperty)}.
   * <ul>
   *   <li>Given {@link TableProperty} {@link TablePropertyComputeValue#computeValue(String, InstanceProperties, TableProperties)} return empty string.</li>
   *   <li>Then return size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link BulkImportPlatformSpec#getList(TableProperty)}
   */
  @Test
  @DisplayName("Test getList(TableProperty); given TableProperty computeValue(String, InstanceProperties, TableProperties) return empty string; then return size is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List BulkImportPlatformSpec.getList(TableProperty)"})
  void testGetList_givenTablePropertyComputeValueReturnEmptyString_thenReturnSizeIsOne() {
    // Arrange
    TableProperty tableProperty = mock(TableProperty.class);
    when(tableProperty.computeValue(Mockito.<String>any(), Mockito.<InstanceProperties>any(),
        Mockito.<TableProperties>any())).thenReturn("");
    when(tableProperty.isIgnoreEmptyValue()).thenReturn(true);
    when(tableProperty.getPropertyName()).thenReturn("Property Name");

    TableProperties tableProperties = new TableProperties(new InstanceProperties());
    tableProperties.addToListIfMissing(tableProperty, new ArrayList<>());
    Builder classNameResult = BulkImportJob.builder().className("Class Name");
    Builder idResult = classNameResult.files(new ArrayList<>()).id("42");
    BulkImportJob job = idResult.platformSpec(new HashMap<>())
        .sparkConf("Key", "42")
        .tableId("42")
        .tableName("Table Name")
        .build();
    BulkImportPlatformSpec bulkImportPlatformSpec = new BulkImportPlatformSpec(tableProperties, job);
    TableProperty property = mock(TableProperty.class);
    when(
        property.computeValue(Mockito.<String>any(), Mockito.<InstanceProperties>any(), Mockito.<TableProperties>any()))
        .thenReturn("42");
    when(property.isIgnoreEmptyValue()).thenReturn(true);
    when(property.getPropertyName()).thenReturn("Property Name");

    // Act
    List<String> actualList = bulkImportPlatformSpec.getList(property);

    // Assert
    verify(tableProperty, atLeast(1)).getPropertyName();
    verify(property, atLeast(1)).getPropertyName();
    verify(tableProperty).isIgnoreEmptyValue();
    verify(property).isIgnoreEmptyValue();
    verify(tableProperty).computeValue(isNull(), isA(InstanceProperties.class), isA(TableProperties.class));
    verify(property).computeValue(isNull(), isA(InstanceProperties.class), isA(TableProperties.class));
    assertEquals(1, actualList.size());
    assertEquals("42", actualList.get(0));
  }

  /**
   * Test {@link BulkImportPlatformSpec#getOrDefault(TableProperty, String)}.
   * <ul>
   *   <li>Given {@code null}.</li>
   *   <li>When {@link TableProperty} {@link TablePropertyComputeValue#computeValue(String, InstanceProperties, TableProperties)} return {@code null}.</li>
   *   <li>Then return {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BulkImportPlatformSpec#getOrDefault(TableProperty, String)}
   */
  @Test
  @DisplayName("Test getOrDefault(TableProperty, String); given 'null'; when TableProperty computeValue(String, InstanceProperties, TableProperties) return 'null'; then return '42'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String BulkImportPlatformSpec.getOrDefault(TableProperty, String)"})
  void testGetOrDefault_givenNull_whenTablePropertyComputeValueReturnNull_thenReturn42() {
    // Arrange
    TableProperty tableProperty = mock(TableProperty.class);
    when(tableProperty.computeValue(Mockito.<String>any(), Mockito.<InstanceProperties>any(),
        Mockito.<TableProperties>any())).thenReturn("");
    when(tableProperty.isIgnoreEmptyValue()).thenReturn(true);
    when(tableProperty.getPropertyName()).thenReturn("Property Name");

    TableProperties tableProperties = new TableProperties(new InstanceProperties());
    tableProperties.addToListIfMissing(tableProperty, new ArrayList<>());
    Builder classNameResult = BulkImportJob.builder().className("Class Name");
    Builder idResult = classNameResult.files(new ArrayList<>()).id("42");
    BulkImportJob job = idResult.platformSpec(new HashMap<>())
        .sparkConf("Key", "42")
        .tableId("42")
        .tableName("Table Name")
        .build();
    BulkImportPlatformSpec bulkImportPlatformSpec = new BulkImportPlatformSpec(tableProperties, job);
    TableProperty property = mock(TableProperty.class);
    when(
        property.computeValue(Mockito.<String>any(), Mockito.<InstanceProperties>any(), Mockito.<TableProperties>any()))
        .thenReturn(null);
    when(property.isIgnoreEmptyValue()).thenReturn(true);
    when(property.getPropertyName()).thenReturn("Property Name");

    // Act
    String actualOrDefault = bulkImportPlatformSpec.getOrDefault(property, "42");

    // Assert
    verify(tableProperty, atLeast(1)).getPropertyName();
    verify(property, atLeast(1)).getPropertyName();
    verify(tableProperty).isIgnoreEmptyValue();
    verify(property).isIgnoreEmptyValue();
    verify(tableProperty).computeValue(isNull(), isA(InstanceProperties.class), isA(TableProperties.class));
    verify(property).computeValue(isNull(), isA(InstanceProperties.class), isA(TableProperties.class));
    assertEquals("42", actualOrDefault);
  }

  /**
   * Test {@link BulkImportPlatformSpec#getOrDefault(TableProperty, String)}.
   * <ul>
   *   <li>Given {@link TableProperties#TableProperties(InstanceProperties)} with instanceProperties is {@link InstanceProperties#InstanceProperties()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BulkImportPlatformSpec#getOrDefault(TableProperty, String)}
   */
  @Test
  @DisplayName("Test getOrDefault(TableProperty, String); given TableProperties(InstanceProperties) with instanceProperties is InstanceProperties()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String BulkImportPlatformSpec.getOrDefault(TableProperty, String)"})
  void testGetOrDefault_givenTablePropertiesWithInstancePropertiesIsInstanceProperties() {
    // Arrange
    TableProperties tableProperties = new TableProperties(new InstanceProperties());
    Builder classNameResult = BulkImportJob.builder().className("Class Name");
    Builder idResult = classNameResult.files(new ArrayList<>()).id("42");
    BulkImportJob job = idResult.platformSpec(new HashMap<>())
        .sparkConf("Key", "42")
        .tableId("42")
        .tableName("Table Name")
        .build();
    BulkImportPlatformSpec bulkImportPlatformSpec = new BulkImportPlatformSpec(tableProperties, job);
    TableProperty property = mock(TableProperty.class);
    when(
        property.computeValue(Mockito.<String>any(), Mockito.<InstanceProperties>any(), Mockito.<TableProperties>any()))
        .thenReturn("42");
    when(property.isIgnoreEmptyValue()).thenReturn(true);
    when(property.getPropertyName()).thenReturn("Property Name");

    // Act
    String actualOrDefault = bulkImportPlatformSpec.getOrDefault(property, "42");

    // Assert
    verify(property, atLeast(1)).getPropertyName();
    verify(property).isIgnoreEmptyValue();
    verify(property).computeValue(isNull(), isA(InstanceProperties.class), isA(TableProperties.class));
    assertEquals("42", actualOrDefault);
  }

  /**
   * Test {@link BulkImportPlatformSpec#getOrDefault(TableProperty, String)}.
   * <ul>
   *   <li>Given {@link TableProperty} {@link TablePropertyComputeValue#computeValue(String, InstanceProperties, TableProperties)} return {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BulkImportPlatformSpec#getOrDefault(TableProperty, String)}
   */
  @Test
  @DisplayName("Test getOrDefault(TableProperty, String); given TableProperty computeValue(String, InstanceProperties, TableProperties) return '42'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String BulkImportPlatformSpec.getOrDefault(TableProperty, String)"})
  void testGetOrDefault_givenTablePropertyComputeValueReturn42() {
    // Arrange
    TableProperty tableProperty = mock(TableProperty.class);
    when(tableProperty.computeValue(Mockito.<String>any(), Mockito.<InstanceProperties>any(),
        Mockito.<TableProperties>any())).thenReturn("42");
    when(tableProperty.isIgnoreEmptyValue()).thenReturn(true);
    when(tableProperty.getPropertyName()).thenReturn("Property Name");

    TableProperties tableProperties = new TableProperties(new InstanceProperties());
    tableProperties.addToListIfMissing(tableProperty, new ArrayList<>());
    Builder classNameResult = BulkImportJob.builder().className("Class Name");
    Builder idResult = classNameResult.files(new ArrayList<>()).id("42");
    BulkImportJob job = idResult.platformSpec(new HashMap<>())
        .sparkConf("Key", "42")
        .tableId("42")
        .tableName("Table Name")
        .build();
    BulkImportPlatformSpec bulkImportPlatformSpec = new BulkImportPlatformSpec(tableProperties, job);
    TableProperty property = mock(TableProperty.class);
    when(
        property.computeValue(Mockito.<String>any(), Mockito.<InstanceProperties>any(), Mockito.<TableProperties>any()))
        .thenReturn("42");
    when(property.isIgnoreEmptyValue()).thenReturn(false);
    when(property.getPropertyName()).thenReturn("Property Name");

    // Act
    String actualOrDefault = bulkImportPlatformSpec.getOrDefault(property, "42");

    // Assert
    verify(tableProperty, atLeast(1)).getPropertyName();
    verify(property, atLeast(1)).getPropertyName();
    verify(tableProperty).isIgnoreEmptyValue();
    verify(property).isIgnoreEmptyValue();
    verify(property).computeValue(eq("42"), isA(InstanceProperties.class), isA(TableProperties.class));
    verify(tableProperty).computeValue(isNull(), isA(InstanceProperties.class), isA(TableProperties.class));
    assertEquals("42", actualOrDefault);
  }

  /**
   * Test {@link BulkImportPlatformSpec#getOrDefault(TableProperty, String)}.
   * <ul>
   *   <li>Given {@link TableProperty} {@link TablePropertyComputeValue#computeValue(String, InstanceProperties, TableProperties)} return empty string.</li>
   *   <li>Then return {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BulkImportPlatformSpec#getOrDefault(TableProperty, String)}
   */
  @Test
  @DisplayName("Test getOrDefault(TableProperty, String); given TableProperty computeValue(String, InstanceProperties, TableProperties) return empty string; then return '42'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String BulkImportPlatformSpec.getOrDefault(TableProperty, String)"})
  void testGetOrDefault_givenTablePropertyComputeValueReturnEmptyString_thenReturn42() {
    // Arrange
    TableProperty tableProperty = mock(TableProperty.class);
    when(tableProperty.computeValue(Mockito.<String>any(), Mockito.<InstanceProperties>any(),
        Mockito.<TableProperties>any())).thenReturn("");
    when(tableProperty.isIgnoreEmptyValue()).thenReturn(true);
    when(tableProperty.getPropertyName()).thenReturn("Property Name");

    TableProperties tableProperties = new TableProperties(new InstanceProperties());
    tableProperties.addToListIfMissing(tableProperty, new ArrayList<>());
    Builder classNameResult = BulkImportJob.builder().className("Class Name");
    Builder idResult = classNameResult.files(new ArrayList<>()).id("42");
    BulkImportJob job = idResult.platformSpec(new HashMap<>())
        .sparkConf("Key", "42")
        .tableId("42")
        .tableName("Table Name")
        .build();
    BulkImportPlatformSpec bulkImportPlatformSpec = new BulkImportPlatformSpec(tableProperties, job);
    TableProperty property = mock(TableProperty.class);
    when(
        property.computeValue(Mockito.<String>any(), Mockito.<InstanceProperties>any(), Mockito.<TableProperties>any()))
        .thenReturn("42");
    when(property.isIgnoreEmptyValue()).thenReturn(true);
    when(property.getPropertyName()).thenReturn("Property Name");

    // Act
    String actualOrDefault = bulkImportPlatformSpec.getOrDefault(property, "42");

    // Assert
    verify(tableProperty, atLeast(1)).getPropertyName();
    verify(property, atLeast(1)).getPropertyName();
    verify(tableProperty).isIgnoreEmptyValue();
    verify(property).isIgnoreEmptyValue();
    verify(tableProperty).computeValue(isNull(), isA(InstanceProperties.class), isA(TableProperties.class));
    verify(property).computeValue(isNull(), isA(InstanceProperties.class), isA(TableProperties.class));
    assertEquals("42", actualOrDefault);
  }

  /**
   * Test {@link BulkImportPlatformSpec#getTableProperties()}.
   * <p>
   * Method under test: {@link BulkImportPlatformSpec#getTableProperties()}
   */
  @Test
  @DisplayName("Test getTableProperties()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"TableProperties BulkImportPlatformSpec.getTableProperties()"})
  void testGetTableProperties() {
    // Arrange
    TableProperties tableProperties = new TableProperties(new InstanceProperties());
    Builder classNameResult = BulkImportJob.builder().className("Class Name");
    Builder idResult = classNameResult.files(new ArrayList<>()).id("42");
    BulkImportJob job = idResult.platformSpec(new HashMap<>())
        .sparkConf("Key", "42")
        .tableId("42")
        .tableName("Table Name")
        .build();

    // Act and Assert
    assertSame(tableProperties, (new BulkImportPlatformSpec(tableProperties, job)).getTableProperties());
  }
}
