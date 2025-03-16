package sleeper.athena;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.schema.Field;
import sleeper.core.schema.Schema;
import sleeper.core.schema.type.ByteArrayType;

class FilterTranslatorDiffblueTest {
  /**
   * Test {@link FilterTranslator#FilterTranslator(Schema)}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link Field#Field(String, Type)} with {@code Name} and type is {@link ByteArrayType} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link FilterTranslator#FilterTranslator(Schema)}
   */
  @Test
  @DisplayName("Test new FilterTranslator(Schema); given ArrayList() add Field(String, Type) with 'Name' and type is ByteArrayType (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void FilterTranslator.<init>(Schema)"})
  void testNewFilterTranslator_givenArrayListAddFieldWithNameAndTypeIsByteArrayType() {
    // Arrange
    ArrayList<Field> fieldList = new ArrayList<>();
    fieldList.add(new Field("Name", new ByteArrayType()));
    Schema schema = mock(Schema.class);
    when(schema.getAllFields()).thenReturn(fieldList);

    // Act
    FilterTranslator actualFilterTranslator = new FilterTranslator(schema);

    // Assert
    verify(schema).getAllFields();
    assertNull(actualFilterTranslator.toPredicate(null));
  }

  /**
   * Test {@link FilterTranslator#FilterTranslator(Schema)}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link Field#Field(String, Type)} with {@code Name} and type is {@link ByteArrayType} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link FilterTranslator#FilterTranslator(Schema)}
   */
  @Test
  @DisplayName("Test new FilterTranslator(Schema); given ArrayList() add Field(String, Type) with 'Name' and type is ByteArrayType (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void FilterTranslator.<init>(Schema)"})
  void testNewFilterTranslator_givenArrayListAddFieldWithNameAndTypeIsByteArrayType2() {
    // Arrange
    ArrayList<Field> fieldList = new ArrayList<>();
    fieldList.add(new Field("Name", new ByteArrayType()));
    fieldList.add(new Field("Name", new ByteArrayType()));
    Schema schema = mock(Schema.class);
    when(schema.getAllFields()).thenReturn(fieldList);

    // Act
    FilterTranslator actualFilterTranslator = new FilterTranslator(schema);

    // Assert
    verify(schema).getAllFields();
    assertNull(actualFilterTranslator.toPredicate(null));
  }

  /**
   * Test {@link FilterTranslator#FilterTranslator(Schema)}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link Field#Field(String, Type)} with name is {@code 42} and type is {@link ByteArrayType} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link FilterTranslator#FilterTranslator(Schema)}
   */
  @Test
  @DisplayName("Test new FilterTranslator(Schema); given ArrayList() add Field(String, Type) with name is '42' and type is ByteArrayType (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void FilterTranslator.<init>(Schema)"})
  void testNewFilterTranslator_givenArrayListAddFieldWithNameIs42AndTypeIsByteArrayType() {
    // Arrange
    ArrayList<Field> fieldList = new ArrayList<>();
    fieldList.add(new Field("Name", new ByteArrayType()));
    fieldList.add(new Field("42", new ByteArrayType()));
    Schema schema = mock(Schema.class);
    when(schema.getAllFields()).thenReturn(fieldList);

    // Act
    FilterTranslator actualFilterTranslator = new FilterTranslator(schema);

    // Assert
    verify(schema).getAllFields();
    assertNull(actualFilterTranslator.toPredicate(null));
  }

  /**
   * Test {@link FilterTranslator#FilterTranslator(Schema)}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return toPredicate {@code null} is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link FilterTranslator#FilterTranslator(Schema)}
   */
  @Test
  @DisplayName("Test new FilterTranslator(Schema); given ArrayList(); then return toPredicate 'null' is 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void FilterTranslator.<init>(Schema)"})
  void testNewFilterTranslator_givenArrayList_thenReturnToPredicateNullIsNull() {
    // Arrange
    Schema schema = mock(Schema.class);
    when(schema.getAllFields()).thenReturn(new ArrayList<>());

    // Act
    FilterTranslator actualFilterTranslator = new FilterTranslator(schema);

    // Assert
    verify(schema).getAllFields();
    assertNull(actualFilterTranslator.toPredicate(null));
  }
}
