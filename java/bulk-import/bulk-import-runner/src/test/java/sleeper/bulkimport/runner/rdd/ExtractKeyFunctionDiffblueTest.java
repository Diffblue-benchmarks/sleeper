package sleeper.bulkimport.runner.rdd;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.catalyst.expressions.GenericRow;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import scala.Tuple2;
import sleeper.core.key.Key;

class ExtractKeyFunctionDiffblueTest {
  /**
   * Test {@link ExtractKeyFunction#call(Row)} with {@code Row}.
   * <ul>
   *   <li>Then {@link Tuple2#_2} return {@link GenericRow}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ExtractKeyFunction#call(Row)}
   */
  @Test
  @DisplayName("Test call(Row) with 'Row'; then _2 return GenericRow")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Tuple2 ExtractKeyFunction.call(Row)"})
  void testCallWithRow_then_2ReturnGenericRow() {
    // Arrange
    ExtractKeyFunction extractKeyFunction = new ExtractKeyFunction(1);

    // Act
    Tuple2<Key, Row> actualCallResult = extractKeyFunction.call(new GenericRow(3));

    // Assert
    Row row = actualCallResult._2;
    assertTrue(row instanceof GenericRow);
    assertNull(row.schema());
    Key key = actualCallResult._1;
    assertEquals(1, key.size());
    assertEquals(3, row.length());
    assertEquals(3, row.size());
    assertFalse(key.isEmpty());
    Key expected_1Result = actualCallResult._1;
    assertSame(expected_1Result, actualCallResult._1());
    Row expected_2Result = actualCallResult._2;
    assertSame(expected_2Result, actualCallResult._2());
  }
}
