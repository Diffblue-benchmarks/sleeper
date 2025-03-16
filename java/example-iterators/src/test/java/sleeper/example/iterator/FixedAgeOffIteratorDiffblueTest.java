package sleeper.example.iterator;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.function.Predicate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.iterator.CloseableIterator;
import sleeper.core.iterator.ConcatenatingIterator;
import sleeper.core.iterator.FilteringIterator;
import sleeper.core.iterator.WrappedIterator;
import sleeper.core.record.Record;
import sleeper.core.schema.Schema;

class FixedAgeOffIteratorDiffblueTest {
  /**
   * Test {@link FixedAgeOffIterator#apply(CloseableIterator)} with {@code CloseableIterator}.
   * <p>
   * Method under test: {@link FixedAgeOffIterator#apply(CloseableIterator)}
   */
  @Test
  @DisplayName("Test apply(CloseableIterator) with 'CloseableIterator'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"CloseableIterator FixedAgeOffIterator.apply(CloseableIterator)"})
  void testApplyWithCloseableIterator() {
    // Arrange
    FixedAgeOffIterator fixedAgeOffIterator = new FixedAgeOffIterator();

    // Act
    CloseableIterator<Record> actualApplyResult = fixedAgeOffIterator
        .apply(new FilteringIterator<>(new ConcatenatingIterator(new ArrayList<>()), mock(Predicate.class)));

    // Assert
    assertTrue(actualApplyResult instanceof FilteringIterator);
    assertFalse(actualApplyResult.hasNext());
  }

  /**
   * Test {@link FixedAgeOffIterator#apply(CloseableIterator)} with {@code CloseableIterator}.
   * <ul>
   *   <li>Given {@link Record#Record()}.</li>
   *   <li>When {@link ArrayList#ArrayList()} add {@link Record#Record()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link FixedAgeOffIterator#apply(CloseableIterator)}
   */
  @Test
  @DisplayName("Test apply(CloseableIterator) with 'CloseableIterator'; given Record(); when ArrayList() add Record()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"CloseableIterator FixedAgeOffIterator.apply(CloseableIterator)"})
  void testApplyWithCloseableIterator_givenRecord_whenArrayListAddRecord() {
    // Arrange
    FixedAgeOffIterator fixedAgeOffIterator = new FixedAgeOffIterator();

    ArrayList<Record> resultRecordList = new ArrayList<>();
    resultRecordList.add(new Record());

    // Act
    CloseableIterator<Record> actualApplyResult = fixedAgeOffIterator
        .apply(new WrappedIterator<>(resultRecordList.iterator()));

    // Assert
    assertTrue(actualApplyResult instanceof FilteringIterator);
    assertFalse(actualApplyResult.hasNext());
  }

  /**
   * Test {@link FixedAgeOffIterator#apply(CloseableIterator)} with {@code CloseableIterator}.
   * <ul>
   *   <li>When {@link ConcatenatingIterator#ConcatenatingIterator(List)} with suppliers is {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link FixedAgeOffIterator#apply(CloseableIterator)}
   */
  @Test
  @DisplayName("Test apply(CloseableIterator) with 'CloseableIterator'; when ConcatenatingIterator(List) with suppliers is ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"CloseableIterator FixedAgeOffIterator.apply(CloseableIterator)"})
  void testApplyWithCloseableIterator_whenConcatenatingIteratorWithSuppliersIsArrayList() {
    // Arrange
    FixedAgeOffIterator fixedAgeOffIterator = new FixedAgeOffIterator();

    // Act
    CloseableIterator<Record> actualApplyResult = fixedAgeOffIterator
        .apply(new ConcatenatingIterator(new ArrayList<>()));

    // Assert
    assertTrue(actualApplyResult instanceof FilteringIterator);
    assertFalse(actualApplyResult.hasNext());
  }

  /**
   * Test {@link FixedAgeOffIterator#init(String, Schema)}.
   * <p>
   * Method under test: {@link FixedAgeOffIterator#init(String, Schema)}
   */
  @Test
  @DisplayName("Test init(String, Schema)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void FixedAgeOffIterator.init(String, Schema)"})
  void testInit() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> (new FixedAgeOffIterator()).init("Config String", mock(Schema.class)));
  }
}
