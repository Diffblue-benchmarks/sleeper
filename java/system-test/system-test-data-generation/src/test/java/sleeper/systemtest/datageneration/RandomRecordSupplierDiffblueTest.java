package sleeper.systemtest.datageneration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MaintainedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import org.apache.commons.math3.random.ISAACRandom;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;
import sleeper.core.key.Key;
import sleeper.core.properties.SleeperPropertyValues;
import sleeper.core.record.Record;
import sleeper.core.schema.Field;
import sleeper.core.schema.Schema;
import sleeper.core.schema.Schema.Builder;
import sleeper.core.schema.type.ByteArrayType;
import sleeper.core.schema.type.IntType;
import sleeper.core.schema.type.LongType;
import sleeper.core.schema.type.PrimitiveType;
import sleeper.core.schema.type.StringType;
import sleeper.systemtest.configuration.SystemTestProperties;
import sleeper.systemtest.configuration.SystemTestProperty;
import sleeper.systemtest.configuration.SystemTestPropertyValues;

public class RandomRecordSupplierDiffblueTest {
  /**
   * Test {@link RandomRecordSupplier#RandomRecordSupplier(Schema, RandomRecordSupplierConfig)}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link Field#Field(String, Type)} with {@code Name} and type is {@link IntType} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link RandomRecordSupplier#RandomRecordSupplier(Schema, RandomRecordSupplierConfig)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void RandomRecordSupplier.<init>(Schema, RandomRecordSupplierConfig)"})
  public void testNewRandomRecordSupplier_givenArrayListAddFieldWithNameAndTypeIsIntType() {
    // Arrange
    ArrayList<Field> fieldList = new ArrayList<>();
    fieldList.add(new Field("Name", new IntType()));
    Schema schema = mock(Schema.class);
    when(schema.getAllFields()).thenReturn(fieldList);

    // Act
    Record actualGetResult = (new RandomRecordSupplier(schema,
        new RandomRecordSupplierConfig(new SystemTestProperties()))).get();

    // Assert
    verify(schema).getAllFields();
    Set<String> keys = actualGetResult.getKeys();
    assertEquals(1, keys.size());
    assertTrue(keys.contains("Name"));
  }

  /**
   * Test {@link RandomRecordSupplier#RandomRecordSupplier(Schema, RandomRecordSupplierConfig)}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link Field#Field(String, Type)} with {@code Name} and type is {@link LongType} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link RandomRecordSupplier#RandomRecordSupplier(Schema, RandomRecordSupplierConfig)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void RandomRecordSupplier.<init>(Schema, RandomRecordSupplierConfig)"})
  public void testNewRandomRecordSupplier_givenArrayListAddFieldWithNameAndTypeIsLongType() {
    // Arrange
    ArrayList<Field> fieldList = new ArrayList<>();
    fieldList.add(new Field("Name", new LongType()));
    Schema schema = mock(Schema.class);
    when(schema.getAllFields()).thenReturn(fieldList);

    // Act
    Record actualGetResult = (new RandomRecordSupplier(schema,
        new RandomRecordSupplierConfig(new SystemTestProperties()))).get();

    // Assert
    verify(schema).getAllFields();
    Set<String> keys = actualGetResult.getKeys();
    assertEquals(1, keys.size());
    assertTrue(keys.contains("Name"));
  }

  /**
   * Test {@link RandomRecordSupplier#RandomRecordSupplier(Schema, RandomRecordSupplierConfig)}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link Field#Field(String, Type)} with {@code Name} and type is {@link StringType} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link RandomRecordSupplier#RandomRecordSupplier(Schema, RandomRecordSupplierConfig)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void RandomRecordSupplier.<init>(Schema, RandomRecordSupplierConfig)"})
  public void testNewRandomRecordSupplier_givenArrayListAddFieldWithNameAndTypeIsStringType() {
    // Arrange
    ArrayList<Field> fieldList = new ArrayList<>();
    fieldList.add(new Field("Name", new StringType()));
    Schema schema = mock(Schema.class);
    when(schema.getAllFields()).thenReturn(fieldList);

    // Act
    Record actualGetResult = (new RandomRecordSupplier(schema,
        new RandomRecordSupplierConfig(new SystemTestProperties()))).get();

    // Assert
    verify(schema).getAllFields();
    Set<String> keys = actualGetResult.getKeys();
    assertEquals(1, keys.size());
    assertTrue(keys.contains("Name"));
  }

  /**
   * Test {@link RandomRecordSupplier#RandomRecordSupplier(Schema, RandomRecordSupplierConfig)}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return {@link RandomRecordSupplier#get()} Keys Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link RandomRecordSupplier#RandomRecordSupplier(Schema, RandomRecordSupplierConfig)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void RandomRecordSupplier.<init>(Schema, RandomRecordSupplierConfig)"})
  public void testNewRandomRecordSupplier_givenArrayList_thenReturnGetKeysEmpty() {
    // Arrange
    Schema schema = mock(Schema.class);
    when(schema.getAllFields()).thenReturn(new ArrayList<>());

    // Act
    Record actualGetResult = (new RandomRecordSupplier(schema,
        new RandomRecordSupplierConfig(new SystemTestProperties()))).get();

    // Assert
    verify(schema).getAllFields();
    assertTrue(actualGetResult.getKeys().isEmpty());
  }

  /**
   * Test {@link RandomRecordSupplier#RandomRecordSupplier(Schema, RandomRecordSupplierConfig)}.
   * <ul>
   *   <li>Given zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link RandomRecordSupplier#RandomRecordSupplier(Schema, RandomRecordSupplierConfig)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void RandomRecordSupplier.<init>(Schema, RandomRecordSupplierConfig)"})
  public void testNewRandomRecordSupplier_givenZero() {
    // Arrange
    ArrayList<Field> fieldList = new ArrayList<>();
    fieldList.add(new Field("Name", new StringType()));
    Schema schema = mock(Schema.class);
    when(schema.getAllFields()).thenReturn(fieldList);
    SystemTestPropertyValues systemTestProperties = mock(SystemTestPropertyValues.class);
    when(systemTestProperties.getInt(Mockito.<SystemTestProperty>any())).thenReturn(0);
    when(systemTestProperties.getLong(Mockito.<SystemTestProperty>any())).thenReturn(1L);

    // Act
    Record actualGetResult = (new RandomRecordSupplier(schema,
        new RandomRecordSupplierConfig(systemTestProperties, new ISAACRandom()))).get();

    // Assert
    verify(systemTestProperties, atLeast(1)).getInt(Mockito.<SystemTestProperty>any());
    verify(systemTestProperties, atLeast(1)).getLong(Mockito.<SystemTestProperty>any());
    verify(schema).getAllFields();
    Set<String> keys = actualGetResult.getKeys();
    assertEquals(1, keys.size());
    assertTrue(keys.contains("Name"));
  }

  /**
   * Test {@link RandomRecordSupplier#RandomRecordSupplier(Schema, RandomRecordSupplierConfig)}.
   * <ul>
   *   <li>Then calls {@link SleeperPropertyValues#getInt(SleeperProperty)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RandomRecordSupplier#RandomRecordSupplier(Schema, RandomRecordSupplierConfig)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void RandomRecordSupplier.<init>(Schema, RandomRecordSupplierConfig)"})
  public void testNewRandomRecordSupplier_thenCallsGetInt() {
    // Arrange
    ArrayList<Field> fieldList = new ArrayList<>();
    fieldList.add(new Field("Name", new ByteArrayType()));
    Schema schema = mock(Schema.class);
    when(schema.getAllFields()).thenReturn(fieldList);
    SystemTestPropertyValues systemTestProperties = mock(SystemTestPropertyValues.class);
    when(systemTestProperties.getInt(Mockito.<SystemTestProperty>any())).thenReturn(1);
    when(systemTestProperties.getLong(Mockito.<SystemTestProperty>any())).thenReturn(1L);

    // Act
    Record actualGetResult = (new RandomRecordSupplier(schema,
        new RandomRecordSupplierConfig(systemTestProperties, new ISAACRandom()))).get();

    // Assert
    verify(systemTestProperties, atLeast(1)).getInt(Mockito.<SystemTestProperty>any());
    verify(systemTestProperties, atLeast(1)).getLong(Mockito.<SystemTestProperty>any());
    verify(schema).getAllFields();
    Set<String> keys = actualGetResult.getKeys();
    assertEquals(1, keys.size());
    assertTrue(keys.contains("Name"));
  }

  /**
   * Test {@link RandomRecordSupplier#RandomRecordSupplier(Schema, RandomRecordSupplierConfig)}.
   * <ul>
   *   <li>Then calls {@link SleeperPropertyValues#getInt(SleeperProperty)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RandomRecordSupplier#RandomRecordSupplier(Schema, RandomRecordSupplierConfig)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void RandomRecordSupplier.<init>(Schema, RandomRecordSupplierConfig)"})
  public void testNewRandomRecordSupplier_thenCallsGetInt2() {
    // Arrange
    ArrayList<Field> fieldList = new ArrayList<>();
    fieldList.add(new Field("Name", new StringType()));
    Schema schema = mock(Schema.class);
    when(schema.getAllFields()).thenReturn(fieldList);
    SystemTestPropertyValues systemTestProperties = mock(SystemTestPropertyValues.class);
    when(systemTestProperties.getInt(Mockito.<SystemTestProperty>any())).thenReturn(1);
    when(systemTestProperties.getLong(Mockito.<SystemTestProperty>any())).thenReturn(1L);

    // Act
    Record actualGetResult = (new RandomRecordSupplier(schema,
        new RandomRecordSupplierConfig(systemTestProperties, new ISAACRandom()))).get();

    // Assert
    verify(systemTestProperties, atLeast(1)).getInt(Mockito.<SystemTestProperty>any());
    verify(systemTestProperties, atLeast(1)).getLong(Mockito.<SystemTestProperty>any());
    verify(schema).getAllFields();
    Set<String> keys = actualGetResult.getKeys();
    assertEquals(1, keys.size());
    assertTrue(keys.contains("Name"));
  }

  /**
   * Test {@link RandomRecordSupplier#get()}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link Field#Field(String, Type)} with {@code Name} and type is {@link ByteArrayType} (default constructor).</li>
   *   <li>Then calls {@link SleeperPropertyValues#getInt(SleeperProperty)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RandomRecordSupplier#get()}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"Record RandomRecordSupplier.get()"})
  public void testGet_givenArrayListAddFieldWithNameAndTypeIsByteArrayType_thenCallsGetInt() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    SystemTestPropertyValues systemTestProperties = mock(SystemTestPropertyValues.class);
    when(systemTestProperties.getInt(Mockito.<SystemTestProperty>any())).thenReturn(1);
    when(systemTestProperties.getLong(Mockito.<SystemTestProperty>any())).thenReturn(1L);

    // Act
    Record actualGetResult = (new RandomRecordSupplier(schema,
        new RandomRecordSupplierConfig(systemTestProperties, new ISAACRandom()))).get();

    // Assert
    verify(systemTestProperties, atLeast(1)).getInt(Mockito.<SystemTestProperty>any());
    verify(systemTestProperties, atLeast(1)).getLong(Mockito.<SystemTestProperty>any());
    Set<String> keys = actualGetResult.getKeys();
    assertEquals(1, keys.size());
    assertTrue(keys.contains("Name"));
  }

  /**
   * Test {@link RandomRecordSupplier#get()}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link Field#Field(String, Type)} with {@code Name} and type is {@link IntType} (default constructor).</li>
   *   <li>Then return Keys size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link RandomRecordSupplier#get()}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"Record RandomRecordSupplier.get()"})
  public void testGet_givenArrayListAddFieldWithNameAndTypeIsIntType_thenReturnKeysSizeIsOne() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new IntType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();

    // Act and Assert
    Set<String> keys = (new RandomRecordSupplier(schema, new RandomRecordSupplierConfig(new SystemTestProperties())))
        .get()
        .getKeys();
    assertEquals(1, keys.size());
    assertTrue(keys.contains("Name"));
  }

  /**
   * Test {@link RandomRecordSupplier#get()}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link Field#Field(String, Type)} with {@code Name} and type is {@link LongType} (default constructor).</li>
   *   <li>Then return Keys size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link RandomRecordSupplier#get()}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"Record RandomRecordSupplier.get()"})
  public void testGet_givenArrayListAddFieldWithNameAndTypeIsLongType_thenReturnKeysSizeIsOne() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new LongType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();

    // Act and Assert
    Set<String> keys = (new RandomRecordSupplier(schema, new RandomRecordSupplierConfig(new SystemTestProperties())))
        .get()
        .getKeys();
    assertEquals(1, keys.size());
    assertTrue(keys.contains("Name"));
  }

  /**
   * Test {@link RandomRecordSupplier#get()}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link Field#Field(String, Type)} with {@code Name} and type is {@link StringType} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link RandomRecordSupplier#get()}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"Record RandomRecordSupplier.get()"})
  public void testGet_givenArrayListAddFieldWithNameAndTypeIsStringType() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new StringType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();

    // Act and Assert
    Set<String> keys = (new RandomRecordSupplier(schema, new RandomRecordSupplierConfig(new SystemTestProperties())))
        .get()
        .getKeys();
    assertEquals(1, keys.size());
    assertTrue(keys.contains("Name"));
  }

  /**
   * Test {@link RandomRecordSupplier#get()}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link Field#Field(String, Type)} with {@code Name} and type is {@link StringType} (default constructor).</li>
   *   <li>Then calls {@link SleeperPropertyValues#getInt(SleeperProperty)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RandomRecordSupplier#get()}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"Record RandomRecordSupplier.get()"})
  public void testGet_givenArrayListAddFieldWithNameAndTypeIsStringType_thenCallsGetInt() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new StringType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    SystemTestPropertyValues systemTestProperties = mock(SystemTestPropertyValues.class);
    when(systemTestProperties.getInt(Mockito.<SystemTestProperty>any())).thenReturn(1);
    when(systemTestProperties.getLong(Mockito.<SystemTestProperty>any())).thenReturn(1L);

    // Act
    Record actualGetResult = (new RandomRecordSupplier(schema,
        new RandomRecordSupplierConfig(systemTestProperties, new ISAACRandom()))).get();

    // Assert
    verify(systemTestProperties, atLeast(1)).getInt(Mockito.<SystemTestProperty>any());
    verify(systemTestProperties, atLeast(1)).getLong(Mockito.<SystemTestProperty>any());
    Set<String> keys = actualGetResult.getKeys();
    assertEquals(1, keys.size());
    assertTrue(keys.contains("Name"));
  }

  /**
   * Test {@link RandomRecordSupplier#get()}.
   * <ul>
   *   <li>Given {@link SystemTestPropertyValues} {@link SleeperPropertyValues#getInt(SleeperProperty)} return zero.</li>
   *   <li>Then calls {@link SleeperPropertyValues#getInt(SleeperProperty)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RandomRecordSupplier#get()}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"Record RandomRecordSupplier.get()"})
  public void testGet_givenSystemTestPropertyValuesGetIntReturnZero_thenCallsGetInt() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new StringType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    SystemTestPropertyValues systemTestProperties = mock(SystemTestPropertyValues.class);
    when(systemTestProperties.getInt(Mockito.<SystemTestProperty>any())).thenReturn(0);
    when(systemTestProperties.getLong(Mockito.<SystemTestProperty>any())).thenReturn(1L);

    // Act
    Record actualGetResult = (new RandomRecordSupplier(schema,
        new RandomRecordSupplierConfig(systemTestProperties, new ISAACRandom()))).get();

    // Assert
    verify(systemTestProperties, atLeast(1)).getInt(Mockito.<SystemTestProperty>any());
    verify(systemTestProperties, atLeast(1)).getLong(Mockito.<SystemTestProperty>any());
    Set<String> keys = actualGetResult.getKeys();
    assertEquals(1, keys.size());
    assertTrue(keys.contains("Name"));
  }

  /**
   * Test {@link RandomRecordSupplier#getSupplier(List, RandomRecordSupplierConfig)} with {@code List}, {@code RandomRecordSupplierConfig}.
   * <ul>
   *   <li>Given {@link IntType} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link RandomRecordSupplier#getSupplier(List, RandomRecordSupplierConfig)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"Supplier RandomRecordSupplier.getSupplier(List, RandomRecordSupplierConfig)"})
  public void testGetSupplierWithListRandomRecordSupplierConfig_givenIntType() {
    // Arrange
    ArrayList<PrimitiveType> types = new ArrayList<>();
    types.add(new IntType());

    // Act
    Supplier<Key> actualSupplier = RandomRecordSupplier.getSupplier(types,
        new RandomRecordSupplierConfig(new SystemTestProperties()));
    Key actualGetResult = actualSupplier.get();

    // Assert
    assertEquals(1, actualGetResult.getKeys().size());
    assertEquals(1, actualGetResult.size());
    assertFalse(actualGetResult.isEmpty());
  }

  /**
   * Test {@link RandomRecordSupplier#getSupplier(List, RandomRecordSupplierConfig)} with {@code List}, {@code RandomRecordSupplierConfig}.
   * <ul>
   *   <li>Given {@link LongType} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link RandomRecordSupplier#getSupplier(List, RandomRecordSupplierConfig)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"Supplier RandomRecordSupplier.getSupplier(List, RandomRecordSupplierConfig)"})
  public void testGetSupplierWithListRandomRecordSupplierConfig_givenLongType() {
    // Arrange
    ArrayList<PrimitiveType> types = new ArrayList<>();
    types.add(new LongType());

    // Act
    Supplier<Key> actualSupplier = RandomRecordSupplier.getSupplier(types,
        new RandomRecordSupplierConfig(new SystemTestProperties()));
    Key actualGetResult = actualSupplier.get();

    // Assert
    assertEquals(1, actualGetResult.getKeys().size());
    assertEquals(1, actualGetResult.size());
    assertFalse(actualGetResult.isEmpty());
  }

  /**
   * Test {@link RandomRecordSupplier#getSupplier(List, RandomRecordSupplierConfig)} with {@code List}, {@code RandomRecordSupplierConfig}.
   * <ul>
   *   <li>Given {@link StringType} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link RandomRecordSupplier#getSupplier(List, RandomRecordSupplierConfig)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"Supplier RandomRecordSupplier.getSupplier(List, RandomRecordSupplierConfig)"})
  public void testGetSupplierWithListRandomRecordSupplierConfig_givenStringType() {
    // Arrange
    ArrayList<PrimitiveType> types = new ArrayList<>();
    types.add(new StringType());

    // Act
    Supplier<Key> actualSupplier = RandomRecordSupplier.getSupplier(types,
        new RandomRecordSupplierConfig(new SystemTestProperties()));
    Key actualGetResult = actualSupplier.get();

    // Assert
    assertEquals(1, actualGetResult.getKeys().size());
    assertEquals(1, actualGetResult.size());
    assertFalse(actualGetResult.isEmpty());
  }

  /**
   * Test {@link RandomRecordSupplier#getSupplier(List, RandomRecordSupplierConfig)} with {@code List}, {@code RandomRecordSupplierConfig}.
   * <ul>
   *   <li>Then return {@link Supplier#get()} size is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link RandomRecordSupplier#getSupplier(List, RandomRecordSupplierConfig)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"Supplier RandomRecordSupplier.getSupplier(List, RandomRecordSupplierConfig)"})
  public void testGetSupplierWithListRandomRecordSupplierConfig_thenReturnGetSizeIsZero() {
    // Arrange
    ArrayList<PrimitiveType> types = new ArrayList<>();

    // Act
    Supplier<Key> actualSupplier = RandomRecordSupplier.getSupplier(types,
        new RandomRecordSupplierConfig(new SystemTestProperties()));
    Key actualGetResult = actualSupplier.get();

    // Assert
    assertEquals(0, actualGetResult.size());
    assertTrue(actualGetResult.getKeys().isEmpty());
    assertTrue(actualGetResult.isEmpty());
  }
}
