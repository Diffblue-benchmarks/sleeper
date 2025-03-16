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
import java.util.Iterator;
import java.util.Set;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.table.TableProperties;
import sleeper.core.record.Record;
import sleeper.core.schema.Field;
import sleeper.core.schema.Schema;
import sleeper.core.schema.Schema.Builder;
import sleeper.core.schema.type.ByteArrayType;
import sleeper.core.schema.type.IntType;
import sleeper.core.schema.type.LongType;
import sleeper.core.schema.type.StringType;
import sleeper.systemtest.configuration.SystemTestProperties;
import sleeper.systemtest.configuration.SystemTestProperty;
import sleeper.systemtest.configuration.SystemTestPropertyValues;

public class WriteRandomDataDiffblueTest {
  /**
   * Test {@link WriteRandomData#createRecordIterator(SystemTestProperties, TableProperties)} with {@code SystemTestProperties}, {@code TableProperties}.
   * <p>
   * Method under test: {@link WriteRandomData#createRecordIterator(SystemTestProperties, TableProperties)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"Iterator WriteRandomData.createRecordIterator(SystemTestProperties, TableProperties)"})
  public void testCreateRecordIteratorWithSystemTestPropertiesTableProperties() {
    // Arrange
    SystemTestProperties systemTestProperties = new SystemTestProperties();

    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new IntType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();

    TableProperties tableProperties = new TableProperties(new InstanceProperties());
    tableProperties.setSchema(schema);

    // Act
    Iterator<Record> actualCreateRecordIteratorResult = WriteRandomData.createRecordIterator(systemTestProperties,
        tableProperties);

    // Assert
    Set<String> keys = actualCreateRecordIteratorResult.next().getKeys();
    assertEquals(1, keys.size());
    assertTrue(actualCreateRecordIteratorResult.hasNext());
    assertTrue(keys.contains("Name"));
    assertEquals(keys, actualCreateRecordIteratorResult.next().getKeys());
    assertEquals(keys, actualCreateRecordIteratorResult.next().getKeys());
    assertEquals(keys, actualCreateRecordIteratorResult.next().getKeys());
    assertEquals(keys, actualCreateRecordIteratorResult.next().getKeys());
    assertEquals(keys, actualCreateRecordIteratorResult.next().getKeys());
    assertEquals(keys, actualCreateRecordIteratorResult.next().getKeys());
    assertEquals(keys, actualCreateRecordIteratorResult.next().getKeys());
    assertEquals(keys, actualCreateRecordIteratorResult.next().getKeys());
    assertEquals(keys, actualCreateRecordIteratorResult.next().getKeys());
  }

  /**
   * Test {@link WriteRandomData#createRecordIterator(SystemTestProperties, TableProperties)} with {@code SystemTestProperties}, {@code TableProperties}.
   * <p>
   * Method under test: {@link WriteRandomData#createRecordIterator(SystemTestProperties, TableProperties)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"Iterator WriteRandomData.createRecordIterator(SystemTestProperties, TableProperties)"})
  public void testCreateRecordIteratorWithSystemTestPropertiesTableProperties2() {
    // Arrange
    SystemTestProperties systemTestProperties = new SystemTestProperties();

    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new LongType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();

    TableProperties tableProperties = new TableProperties(new InstanceProperties());
    tableProperties.setSchema(schema);

    // Act
    Iterator<Record> actualCreateRecordIteratorResult = WriteRandomData.createRecordIterator(systemTestProperties,
        tableProperties);

    // Assert
    Set<String> keys = actualCreateRecordIteratorResult.next().getKeys();
    assertEquals(1, keys.size());
    assertTrue(actualCreateRecordIteratorResult.hasNext());
    assertTrue(keys.contains("Name"));
    assertEquals(keys, actualCreateRecordIteratorResult.next().getKeys());
    assertEquals(keys, actualCreateRecordIteratorResult.next().getKeys());
    assertEquals(keys, actualCreateRecordIteratorResult.next().getKeys());
    assertEquals(keys, actualCreateRecordIteratorResult.next().getKeys());
    assertEquals(keys, actualCreateRecordIteratorResult.next().getKeys());
    assertEquals(keys, actualCreateRecordIteratorResult.next().getKeys());
    assertEquals(keys, actualCreateRecordIteratorResult.next().getKeys());
    assertEquals(keys, actualCreateRecordIteratorResult.next().getKeys());
    assertEquals(keys, actualCreateRecordIteratorResult.next().getKeys());
  }

  /**
   * Test {@link WriteRandomData#createRecordIterator(SystemTestProperties, TableProperties)} with {@code SystemTestProperties}, {@code TableProperties}.
   * <p>
   * Method under test: {@link WriteRandomData#createRecordIterator(SystemTestProperties, TableProperties)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"Iterator WriteRandomData.createRecordIterator(SystemTestProperties, TableProperties)"})
  public void testCreateRecordIteratorWithSystemTestPropertiesTableProperties3() {
    // Arrange
    SystemTestProperties systemTestProperties = new SystemTestProperties();

    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new StringType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();

    TableProperties tableProperties = new TableProperties(new InstanceProperties());
    tableProperties.setSchema(schema);

    // Act
    Iterator<Record> actualCreateRecordIteratorResult = WriteRandomData.createRecordIterator(systemTestProperties,
        tableProperties);

    // Assert
    Set<String> keys = actualCreateRecordIteratorResult.next().getKeys();
    assertEquals(1, keys.size());
    assertTrue(actualCreateRecordIteratorResult.hasNext());
    assertTrue(keys.contains("Name"));
    assertEquals(keys, actualCreateRecordIteratorResult.next().getKeys());
    assertEquals(keys, actualCreateRecordIteratorResult.next().getKeys());
    assertEquals(keys, actualCreateRecordIteratorResult.next().getKeys());
    assertEquals(keys, actualCreateRecordIteratorResult.next().getKeys());
    assertEquals(keys, actualCreateRecordIteratorResult.next().getKeys());
    assertEquals(keys, actualCreateRecordIteratorResult.next().getKeys());
    assertEquals(keys, actualCreateRecordIteratorResult.next().getKeys());
    assertEquals(keys, actualCreateRecordIteratorResult.next().getKeys());
    assertEquals(keys, actualCreateRecordIteratorResult.next().getKeys());
  }

  /**
   * Test {@link WriteRandomData#createRecordIterator(SystemTestPropertyValues, TableProperties)} with {@code SystemTestPropertyValues}, {@code TableProperties}.
   * <p>
   * Method under test: {@link WriteRandomData#createRecordIterator(SystemTestPropertyValues, TableProperties)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"Iterator WriteRandomData.createRecordIterator(SystemTestPropertyValues, TableProperties)"})
  public void testCreateRecordIteratorWithSystemTestPropertyValuesTableProperties() {
    // Arrange
    SystemTestPropertyValues systemTestProperties = mock(SystemTestPropertyValues.class);
    when(systemTestProperties.getInt(Mockito.<SystemTestProperty>any())).thenReturn(1);
    when(systemTestProperties.getLong(Mockito.<SystemTestProperty>any())).thenReturn(1L);

    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();

    TableProperties tableProperties = new TableProperties(new InstanceProperties());
    tableProperties.setSchema(schema);

    // Act
    WriteRandomData.createRecordIterator(systemTestProperties, tableProperties);

    // Assert
    verify(systemTestProperties, atLeast(1)).getInt(Mockito.<SystemTestProperty>any());
    verify(systemTestProperties, atLeast(1)).getLong(Mockito.<SystemTestProperty>any());
  }

  /**
   * Test {@link WriteRandomData#createRecordIterator(SystemTestPropertyValues, TableProperties)} with {@code SystemTestPropertyValues}, {@code TableProperties}.
   * <p>
   * Method under test: {@link WriteRandomData#createRecordIterator(SystemTestPropertyValues, TableProperties)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"Iterator WriteRandomData.createRecordIterator(SystemTestPropertyValues, TableProperties)"})
  public void testCreateRecordIteratorWithSystemTestPropertyValuesTableProperties2() {
    // Arrange
    SystemTestPropertyValues systemTestProperties = mock(SystemTestPropertyValues.class);
    when(systemTestProperties.getInt(Mockito.<SystemTestProperty>any())).thenReturn(1);
    when(systemTestProperties.getLong(Mockito.<SystemTestProperty>any())).thenReturn(1L);

    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new IntType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();

    TableProperties tableProperties = new TableProperties(new InstanceProperties());
    tableProperties.setSchema(schema);

    // Act
    WriteRandomData.createRecordIterator(systemTestProperties, tableProperties);

    // Assert
    verify(systemTestProperties, atLeast(1)).getInt(Mockito.<SystemTestProperty>any());
    verify(systemTestProperties, atLeast(1)).getLong(Mockito.<SystemTestProperty>any());
  }

  /**
   * Test {@link WriteRandomData#createRecordIterator(SystemTestPropertyValues, TableProperties)} with {@code SystemTestPropertyValues}, {@code TableProperties}.
   * <p>
   * Method under test: {@link WriteRandomData#createRecordIterator(SystemTestPropertyValues, TableProperties)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"Iterator WriteRandomData.createRecordIterator(SystemTestPropertyValues, TableProperties)"})
  public void testCreateRecordIteratorWithSystemTestPropertyValuesTableProperties3() {
    // Arrange
    SystemTestPropertyValues systemTestProperties = mock(SystemTestPropertyValues.class);
    when(systemTestProperties.getInt(Mockito.<SystemTestProperty>any())).thenReturn(1);
    when(systemTestProperties.getLong(Mockito.<SystemTestProperty>any())).thenReturn(1L);

    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new LongType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();

    TableProperties tableProperties = new TableProperties(new InstanceProperties());
    tableProperties.setSchema(schema);

    // Act
    WriteRandomData.createRecordIterator(systemTestProperties, tableProperties);

    // Assert
    verify(systemTestProperties, atLeast(1)).getInt(Mockito.<SystemTestProperty>any());
    verify(systemTestProperties, atLeast(1)).getLong(Mockito.<SystemTestProperty>any());
  }

  /**
   * Test {@link WriteRandomData#createRecordIterator(SystemTestPropertyValues, TableProperties)} with {@code SystemTestPropertyValues}, {@code TableProperties}.
   * <p>
   * Method under test: {@link WriteRandomData#createRecordIterator(SystemTestPropertyValues, TableProperties)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"Iterator WriteRandomData.createRecordIterator(SystemTestPropertyValues, TableProperties)"})
  public void testCreateRecordIteratorWithSystemTestPropertyValuesTableProperties4() {
    // Arrange
    SystemTestPropertyValues systemTestProperties = mock(SystemTestPropertyValues.class);
    when(systemTestProperties.getInt(Mockito.<SystemTestProperty>any())).thenReturn(1);
    when(systemTestProperties.getLong(Mockito.<SystemTestProperty>any())).thenReturn(1L);

    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new StringType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();

    TableProperties tableProperties = new TableProperties(new InstanceProperties());
    tableProperties.setSchema(schema);

    // Act
    Iterator<Record> actualCreateRecordIteratorResult = WriteRandomData.createRecordIterator(systemTestProperties,
        tableProperties);

    // Assert
    verify(systemTestProperties, atLeast(1)).getInt(Mockito.<SystemTestProperty>any());
    verify(systemTestProperties, atLeast(1)).getLong(Mockito.<SystemTestProperty>any());
    Set<String> keys = actualCreateRecordIteratorResult.next().getKeys();
    assertEquals(1, keys.size());
    assertFalse(actualCreateRecordIteratorResult.hasNext());
    assertTrue(keys.contains("Name"));
  }
}
