package sleeper.bulkimport.runner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import scala.collection.Parallel;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.List;
import scala.collection.immutable.Stream;
import scala.collection.immutable.Stream.Cons;
import scala.collection.immutable.Vector;
import scala.collection.parallel.mutable.ParArray;

class SparkFileReferenceRowDiffblueTest {
  /**
   * Test {@link SparkFileReferenceRow#createFileReferenceSchema()}.
   * <p>
   * Method under test: {@link SparkFileReferenceRow#createFileReferenceSchema()}
   */
  @Test
  @DisplayName("Test createFileReferenceSchema()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"StructType SparkFileReferenceRow.createFileReferenceSchema()"})
  void testCreateFileReferenceSchema() {
    // Arrange and Act
    StructType actualCreateFileReferenceSchemaResult = SparkFileReferenceRow.createFileReferenceSchema();

    // Assert
    assertTrue(actualCreateFileReferenceSchemaResult.companion() instanceof scala.collection.Seq$);
    Object distinctResult = actualCreateFileReferenceSchemaResult.distinct();
    assertTrue(distinctResult instanceof scala.collection.immutable.$colon$colon);
    assertTrue(actualCreateFileReferenceSchemaResult.init() instanceof scala.collection.immutable.$colon$colon);
    assertTrue(actualCreateFileReferenceSchemaResult.tail() instanceof scala.collection.immutable.$colon$colon);
    List<StructField> toListResult = actualCreateFileReferenceSchemaResult.toList();
    assertTrue(toListResult instanceof scala.collection.immutable.$colon$colon);
    Stream<StructField> toStreamResult = actualCreateFileReferenceSchemaResult.toStream();
    assertTrue(toStreamResult instanceof Cons);
    IndexedSeq<StructField> toIndexedSeqResult = actualCreateFileReferenceSchemaResult.toIndexedSeq();
    assertTrue(toIndexedSeqResult instanceof Vector);
    Parallel parResult = actualCreateFileReferenceSchemaResult.par();
    assertTrue(parResult instanceof ParArray);
    assertEquals(3, actualCreateFileReferenceSchemaResult.length());
    assertEquals(3, actualCreateFileReferenceSchemaResult.size());
    StructField[] copy$default$1Result = actualCreateFileReferenceSchemaResult.copy$default$1();
    assertEquals(3, copy$default$1Result.length);
    assertFalse(actualCreateFileReferenceSchemaResult.findNestedField$default$2());
    assertFalse(actualCreateFileReferenceSchemaResult.isEmpty());
    assertTrue(actualCreateFileReferenceSchemaResult.hasDefiniteSize());
    assertTrue(actualCreateFileReferenceSchemaResult.isTraversableAgain());
    assertTrue(actualCreateFileReferenceSchemaResult.merge$default$2());
    assertEquals(distinctResult, actualCreateFileReferenceSchemaResult.toVector());
    assertEquals(distinctResult, toListResult);
    assertEquals(distinctResult, toStreamResult);
    assertEquals(distinctResult, toIndexedSeqResult);
    assertEquals(distinctResult, parResult);
    assertSame(copy$default$1Result, actualCreateFileReferenceSchemaResult.fields());
    assertSame(actualCreateFileReferenceSchemaResult, actualCreateFileReferenceSchemaResult.repr());
    assertSame(actualCreateFileReferenceSchemaResult, actualCreateFileReferenceSchemaResult.seq());
    assertSame(actualCreateFileReferenceSchemaResult, actualCreateFileReferenceSchemaResult.toIterable());
    assertSame(actualCreateFileReferenceSchemaResult, actualCreateFileReferenceSchemaResult.toSeq());
    assertSame(actualCreateFileReferenceSchemaResult, actualCreateFileReferenceSchemaResult.toTraversable());
  }
}
