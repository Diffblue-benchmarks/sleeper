package sleeper.ingest.runner.impl;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.iterator.CloseableIterator;
import sleeper.core.iterator.ConcatenatingIterator;
import sleeper.core.iterator.IteratorCreationException;
import sleeper.core.schema.Schema;
import sleeper.core.util.ObjectFactory;

class RecordIteratorWithSleeperIteratorAppliedDiffblueTest {
  /**
   * Test {@link RecordIteratorWithSleeperIteratorApplied#RecordIteratorWithSleeperIteratorApplied(ObjectFactory, Schema, String, String, CloseableIterator)}.
   * <p>
   * Method under test: {@link RecordIteratorWithSleeperIteratorApplied#RecordIteratorWithSleeperIteratorApplied(ObjectFactory, Schema, String, String, CloseableIterator)}
   */
  @Test
  @DisplayName("Test new RecordIteratorWithSleeperIteratorApplied(ObjectFactory, Schema, String, String, CloseableIterator)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void RecordIteratorWithSleeperIteratorApplied.<init>(ObjectFactory, Schema, String, String, CloseableIterator)"})
  void testNewRecordIteratorWithSleeperIteratorApplied() throws IteratorCreationException {
    // Arrange
    ObjectFactory objectFactory = ObjectFactory.noUserJars();
    Schema sleeperSchema = mock(Schema.class);

    // Act and Assert
    assertThrows(IteratorCreationException.class,
        () -> new RecordIteratorWithSleeperIteratorApplied(objectFactory, sleeperSchema, "Sleeper Iterator Class Name",
            "Sleeper Iterator Config", new ConcatenatingIterator(new ArrayList<>())));

  }

  /**
   * Test {@link RecordIteratorWithSleeperIteratorApplied#RecordIteratorWithSleeperIteratorApplied(ObjectFactory, Schema, String, String, CloseableIterator)}.
   * <ul>
   *   <li>Then return not hasNext.</li>
   * </ul>
   * <p>
   * Method under test: {@link RecordIteratorWithSleeperIteratorApplied#RecordIteratorWithSleeperIteratorApplied(ObjectFactory, Schema, String, String, CloseableIterator)}
   */
  @Test
  @DisplayName("Test new RecordIteratorWithSleeperIteratorApplied(ObjectFactory, Schema, String, String, CloseableIterator); then return not hasNext")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void RecordIteratorWithSleeperIteratorApplied.<init>(ObjectFactory, Schema, String, String, CloseableIterator)"})
  void testNewRecordIteratorWithSleeperIteratorApplied_thenReturnNotHasNext() throws IteratorCreationException {
    // Arrange
    ObjectFactory objectFactory = ObjectFactory.noUserJars();
    Schema sleeperSchema = mock(Schema.class);

    // Act and Assert
    assertFalse((new RecordIteratorWithSleeperIteratorApplied(objectFactory, sleeperSchema, null,
        "Sleeper Iterator Config", new ConcatenatingIterator(new ArrayList<>()))).hasNext());
  }
}
