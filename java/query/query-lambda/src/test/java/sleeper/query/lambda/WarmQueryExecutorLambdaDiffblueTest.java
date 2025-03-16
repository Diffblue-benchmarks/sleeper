package sleeper.query.lambda;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.range.Region;
import sleeper.core.schema.Field;
import sleeper.core.schema.Schema;
import sleeper.core.schema.type.ByteArrayType;
import sleeper.core.schema.type.PrimitiveType;

class WarmQueryExecutorLambdaDiffblueTest {
  /**
   * Test {@link WarmQueryExecutorLambda#getRegion(Schema)}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link Field#Field(String, Type)} with {@code Name} and type is {@link PrimitiveType} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link WarmQueryExecutorLambda#getRegion(Schema)}
   */
  @Test
  @DisplayName("Test getRegion(Schema); given ArrayList() add Field(String, Type) with 'Name' and type is PrimitiveType (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Region WarmQueryExecutorLambda.getRegion(Schema)"})
  void testGetRegion_givenArrayListAddFieldWithNameAndTypeIsPrimitiveType() {
    // Arrange
    ArrayList<Field> fieldList = new ArrayList<>();
    fieldList.add(new Field("Name", new PrimitiveType()));
    Schema schema = mock(Schema.class);
    when(schema.getRowKeyFields()).thenReturn(fieldList);

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> WarmQueryExecutorLambda.getRegion(schema));
    verify(schema).getRowKeyFields();
  }

  /**
   * Test {@link WarmQueryExecutorLambda#getRegion(Schema)}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return Ranges Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link WarmQueryExecutorLambda#getRegion(Schema)}
   */
  @Test
  @DisplayName("Test getRegion(Schema); given ArrayList(); then return Ranges Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Region WarmQueryExecutorLambda.getRegion(Schema)"})
  void testGetRegion_givenArrayList_thenReturnRangesEmpty() {
    // Arrange
    Schema schema = mock(Schema.class);
    when(schema.getRowKeyFields()).thenReturn(new ArrayList<>());

    // Act
    Region actualRegion = WarmQueryExecutorLambda.getRegion(schema);

    // Assert
    verify(schema).getRowKeyFields();
    assertTrue(actualRegion.getRanges().isEmpty());
  }

  /**
   * Test {@link WarmQueryExecutorLambda#getRegion(Schema)}.
   * <ul>
   *   <li>Given {@link IllegalArgumentException#IllegalArgumentException(String)} with {@code foo}.</li>
   *   <li>Then calls {@link Schema#getRowKeyFieldNames()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WarmQueryExecutorLambda#getRegion(Schema)}
   */
  @Test
  @DisplayName("Test getRegion(Schema); given IllegalArgumentException(String) with 'foo'; then calls getRowKeyFieldNames()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Region WarmQueryExecutorLambda.getRegion(Schema)"})
  void testGetRegion_givenIllegalArgumentExceptionWithFoo_thenCallsGetRowKeyFieldNames() {
    // Arrange
    ArrayList<Field> fieldList = new ArrayList<>();
    fieldList.add(new Field("Name", new ByteArrayType()));
    Schema schema = mock(Schema.class);
    when(schema.getRowKeyFieldNames()).thenThrow(new IllegalArgumentException("foo"));
    when(schema.getRowKeyFields()).thenReturn(fieldList);

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> WarmQueryExecutorLambda.getRegion(schema));
    verify(schema).getRowKeyFieldNames();
    verify(schema, atLeast(1)).getRowKeyFields();
  }
}
