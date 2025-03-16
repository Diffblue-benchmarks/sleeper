package sleeper.parquet.record;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.IOException;
import java.util.NoSuchElementException;
import org.apache.parquet.hadoop.ParquetReader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.record.Record;

class ParquetReaderIteratorDiffblueTest {
  /**
   * Test {@link ParquetReaderIterator#ParquetReaderIterator(ParquetReader)}.
   * <ul>
   *   <li>Given {@code null}.</li>
   *   <li>Then return not hasNext.</li>
   * </ul>
   * <p>
   * Method under test: {@link ParquetReaderIterator#ParquetReaderIterator(ParquetReader)}
   */
  @Test
  @DisplayName("Test new ParquetReaderIterator(ParquetReader); given 'null'; then return not hasNext")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ParquetReaderIterator.<init>(ParquetReader)"})
  void testNewParquetReaderIterator_givenNull_thenReturnNotHasNext() throws IOException {
    // Arrange
    ParquetReader<Record> reader = mock(ParquetReader.class);
    when(reader.read()).thenReturn(null);

    // Act
    ParquetReaderIterator actualParquetReaderIterator = new ParquetReaderIterator(reader);

    // Assert
    verify(reader).read();
    assertFalse(actualParquetReaderIterator.hasNext());
  }

  /**
   * Test {@link ParquetReaderIterator#ParquetReaderIterator(ParquetReader)}.
   * <ul>
   *   <li>Given {@link Record#Record()}.</li>
   *   <li>Then return hasNext.</li>
   * </ul>
   * <p>
   * Method under test: {@link ParquetReaderIterator#ParquetReaderIterator(ParquetReader)}
   */
  @Test
  @DisplayName("Test new ParquetReaderIterator(ParquetReader); given Record(); then return hasNext")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ParquetReaderIterator.<init>(ParquetReader)"})
  void testNewParquetReaderIterator_givenRecord_thenReturnHasNext() throws IOException, NoSuchElementException {
    // Arrange
    ParquetReader<Record> reader = mock(ParquetReader.class);
    Record resultRecord = new Record();
    when(reader.read()).thenReturn(resultRecord);

    // Act
    ParquetReaderIterator actualParquetReaderIterator = new ParquetReaderIterator(reader);

    // Assert
    verify(reader).read();
    assertTrue(actualParquetReaderIterator.hasNext());
    assertEquals(resultRecord, actualParquetReaderIterator.next());
    assertEquals(resultRecord, actualParquetReaderIterator.next());
    assertEquals(resultRecord, actualParquetReaderIterator.next());
    assertEquals(resultRecord, actualParquetReaderIterator.next());
    assertEquals(resultRecord, actualParquetReaderIterator.next());
    assertEquals(resultRecord, actualParquetReaderIterator.next());
    assertEquals(resultRecord, actualParquetReaderIterator.next());
    assertEquals(resultRecord, actualParquetReaderIterator.next());
    assertEquals(resultRecord, actualParquetReaderIterator.next());
    assertEquals(resultRecord, actualParquetReaderIterator.next());
  }
}
