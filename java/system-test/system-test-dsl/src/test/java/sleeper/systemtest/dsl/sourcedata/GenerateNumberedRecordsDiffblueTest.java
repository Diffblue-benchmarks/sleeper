package sleeper.systemtest.dsl.sourcedata;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.record.Record;
import sleeper.core.schema.Field;
import sleeper.core.schema.Schema;
import sleeper.systemtest.dsl.util.SystemTestSchema;

class GenerateNumberedRecordsDiffblueTest {
  /**
   * Test {@link GenerateNumberedRecords#from(Schema)} with {@code schema}.
   * <p>
   * Method under test: {@link GenerateNumberedRecords#from(Schema)}
   */
  @Test
  @DisplayName("Test from(Schema) with 'schema'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"GenerateNumberedRecords GenerateNumberedRecords.from(Schema)"})
  void testFromWithSchema() {
    // Arrange, Act and Assert
    Set<String> keys = GenerateNumberedRecords.from(SystemTestSchema.DEFAULT_SCHEMA).generateRecord(1L).getKeys();
    assertEquals(3, keys.size());
    assertTrue(keys.contains("key"));
    assertTrue(keys.contains("timestamp"));
    assertTrue(keys.contains("value"));
  }

  /**
   * Test {@link GenerateNumberedRecords#from(Schema, GenerateNumberedValueOverrides)} with {@code schema}, {@code overrides}.
   * <p>
   * Method under test: {@link GenerateNumberedRecords#from(Schema, GenerateNumberedValueOverrides)}
   */
  @Test
  @DisplayName("Test from(Schema, GenerateNumberedValueOverrides) with 'schema', 'overrides'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"GenerateNumberedRecords GenerateNumberedRecords.from(Schema, GenerateNumberedValueOverrides)"})
  void testFromWithSchemaOverrides() {
    // Arrange, Act and Assert
    Set<String> keys = GenerateNumberedRecords
        .from(SystemTestSchema.DEFAULT_SCHEMA, mock(GenerateNumberedValueOverrides.class))
        .generateRecord(1L)
        .getKeys();
    assertEquals(3, keys.size());
    assertTrue(keys.contains("key"));
    assertTrue(keys.contains("timestamp"));
    assertTrue(keys.contains("value"));
  }

  /**
   * Test {@link GenerateNumberedRecords#generateRecord(long)}.
   * <ul>
   *   <li>Given from {@link SystemTestSchema#DEFAULT_SCHEMA}.</li>
   *   <li>When one.</li>
   *   <li>Then return Keys size is three.</li>
   * </ul>
   * <p>
   * Method under test: {@link GenerateNumberedRecords#generateRecord(long)}
   */
  @Test
  @DisplayName("Test generateRecord(long); given from DEFAULT_SCHEMA; when one; then return Keys size is three")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Record GenerateNumberedRecords.generateRecord(long)"})
  void testGenerateRecord_givenFromDefault_schema_whenOne_thenReturnKeysSizeIsThree() {
    // Arrange, Act and Assert
    Set<String> keys = GenerateNumberedRecords.from(SystemTestSchema.DEFAULT_SCHEMA).generateRecord(1L).getKeys();
    assertEquals(3, keys.size());
    assertTrue(keys.contains("key"));
    assertTrue(keys.contains("timestamp"));
    assertTrue(keys.contains("value"));
  }

  /**
   * Test {@link GenerateNumberedRecords#generateRecord(long)}.
   * <ul>
   *   <li>Given {@link GenerateNumberedValueOverrides} {@link GenerateNumberedValueOverrides#getGenerator(KeyType, Field)} return empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link GenerateNumberedRecords#generateRecord(long)}
   */
  @Test
  @DisplayName("Test generateRecord(long); given GenerateNumberedValueOverrides getGenerator(KeyType, Field) return empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Record GenerateNumberedRecords.generateRecord(long)"})
  void testGenerateRecord_givenGenerateNumberedValueOverridesGetGeneratorReturnEmpty() {
    // Arrange
    GenerateNumberedValueOverrides overrides = mock(GenerateNumberedValueOverrides.class);
    Optional<GenerateNumberedValue> emptyResult = Optional.empty();
    when(overrides.getGenerator(Mockito.<KeyType>any(), Mockito.<Field>any())).thenReturn(emptyResult);

    // Act
    Record actualGenerateRecordResult = GenerateNumberedRecords.from(SystemTestSchema.DEFAULT_SCHEMA, overrides)
        .generateRecord(1L);

    // Assert
    verify(overrides, atLeast(1)).getGenerator(Mockito.<KeyType>any(), Mockito.<Field>any());
    Set<String> keys = actualGenerateRecordResult.getKeys();
    assertEquals(3, keys.size());
    assertTrue(keys.contains("key"));
    assertTrue(keys.contains("timestamp"));
    assertTrue(keys.contains("value"));
  }

  /**
   * Test {@link GenerateNumberedRecords#generateRecord(long)}.
   * <ul>
   *   <li>Then calls {@link GenerateNumberedValue#generateValue(long)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GenerateNumberedRecords#generateRecord(long)}
   */
  @Test
  @DisplayName("Test generateRecord(long); then calls generateValue(long)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Record GenerateNumberedRecords.generateRecord(long)"})
  void testGenerateRecord_thenCallsGenerateValue() {
    // Arrange
    GenerateNumberedValue generateNumberedValue = mock(GenerateNumberedValue.class);
    when(generateNumberedValue.generateValue(anyLong())).thenReturn("Generate Value");
    Optional<GenerateNumberedValue> ofResult = Optional.of(generateNumberedValue);
    GenerateNumberedValueOverrides overrides = mock(GenerateNumberedValueOverrides.class);
    when(overrides.getGenerator(Mockito.<KeyType>any(), Mockito.<Field>any())).thenReturn(ofResult);

    // Act
    Record actualGenerateRecordResult = GenerateNumberedRecords.from(SystemTestSchema.DEFAULT_SCHEMA, overrides)
        .generateRecord(1L);

    // Assert
    verify(generateNumberedValue, atLeast(1)).generateValue(eq(1L));
    verify(overrides, atLeast(1)).getGenerator(Mockito.<KeyType>any(), Mockito.<Field>any());
    Set<String> keys = actualGenerateRecordResult.getKeys();
    assertEquals(3, keys.size());
    assertTrue(keys.contains("key"));
    assertTrue(keys.contains("timestamp"));
    assertTrue(keys.contains("value"));
  }

  /**
   * Test {@link GenerateNumberedRecords#generateRecord(long)}.
   * <ul>
   *   <li>When {@link Long#MAX_VALUE}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GenerateNumberedRecords#generateRecord(long)}
   */
  @Test
  @DisplayName("Test generateRecord(long); when MAX_VALUE")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Record GenerateNumberedRecords.generateRecord(long)"})
  void testGenerateRecord_whenMax_value() {
    // Arrange
    GenerateNumberedValueOverrides overrides = mock(GenerateNumberedValueOverrides.class);
    Optional<GenerateNumberedValue> emptyResult = Optional.empty();
    when(overrides.getGenerator(Mockito.<KeyType>any(), Mockito.<Field>any())).thenReturn(emptyResult);

    // Act
    Record actualGenerateRecordResult = GenerateNumberedRecords.from(SystemTestSchema.DEFAULT_SCHEMA, overrides)
        .generateRecord(Long.MAX_VALUE);

    // Assert
    verify(overrides, atLeast(1)).getGenerator(Mockito.<KeyType>any(), Mockito.<Field>any());
    Set<String> keys = actualGenerateRecordResult.getKeys();
    assertEquals(3, keys.size());
    assertTrue(keys.contains("key"));
    assertTrue(keys.contains("timestamp"));
    assertTrue(keys.contains("value"));
  }
}
