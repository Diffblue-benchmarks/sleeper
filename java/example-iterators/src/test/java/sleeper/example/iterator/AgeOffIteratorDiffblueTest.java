package sleeper.example.iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
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

class AgeOffIteratorDiffblueTest {
  /**
   * Test new {@link AgeOffIterator} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link AgeOffIterator}
   */
  @Test
  @DisplayName("Test new AgeOffIterator (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AgeOffIterator.<init>()"})
  void testNewAgeOffIterator() {
    // Arrange, Act and Assert
    List<String> requiredValueFields = (new AgeOffIterator()).getRequiredValueFields();
    assertEquals(1, requiredValueFields.size());
    assertNull(requiredValueFields.get(0));
  }

  /**
   * Test {@link AgeOffIterator#init(String, Schema)}.
   * <p>
   * Method under test: {@link AgeOffIterator#init(String, Schema)}
   */
  @Test
  @DisplayName("Test init(String, Schema)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AgeOffIterator.init(String, Schema)"})
  void testInit() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> (new AgeOffIterator()).init("Config String", mock(Schema.class)));
  }

  /**
   * Test {@link AgeOffIterator#getRequiredValueFields()}.
   * <p>
   * Method under test: {@link AgeOffIterator#getRequiredValueFields()}
   */
  @Test
  @DisplayName("Test getRequiredValueFields()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List AgeOffIterator.getRequiredValueFields()"})
  void testGetRequiredValueFields() {
    // Arrange and Act
    List<String> actualRequiredValueFields = (new AgeOffIterator()).getRequiredValueFields();

    // Assert
    assertEquals(1, actualRequiredValueFields.size());
    assertNull(actualRequiredValueFields.get(0));
  }

  /**
   * Test {@link AgeOffIterator#apply(CloseableIterator)} with {@code CloseableIterator}.
   * <p>
   * Method under test: {@link AgeOffIterator#apply(CloseableIterator)}
   */
  @Test
  @DisplayName("Test apply(CloseableIterator) with 'CloseableIterator'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"CloseableIterator AgeOffIterator.apply(CloseableIterator)"})
  void testApplyWithCloseableIterator() {
    // Arrange
    AgeOffIterator ageOffIterator = new AgeOffIterator();

    // Act
    CloseableIterator<Record> actualApplyResult = ageOffIterator
        .apply(new FilteringIterator<>(new ConcatenatingIterator(new ArrayList<>()), mock(Predicate.class)));

    // Assert
    assertTrue(actualApplyResult instanceof FilteringIterator);
    assertFalse(actualApplyResult.hasNext());
  }

  /**
   * Test {@link AgeOffIterator#apply(CloseableIterator)} with {@code CloseableIterator}.
   * <ul>
   *   <li>Given {@link Record#Record()}.</li>
   *   <li>When {@link ArrayList#ArrayList()} add {@link Record#Record()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AgeOffIterator#apply(CloseableIterator)}
   */
  @Test
  @DisplayName("Test apply(CloseableIterator) with 'CloseableIterator'; given Record(); when ArrayList() add Record()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"CloseableIterator AgeOffIterator.apply(CloseableIterator)"})
  void testApplyWithCloseableIterator_givenRecord_whenArrayListAddRecord() {
    // Arrange
    AgeOffIterator ageOffIterator = new AgeOffIterator();

    ArrayList<Record> resultRecordList = new ArrayList<>();
    resultRecordList.add(new Record());

    // Act
    CloseableIterator<Record> actualApplyResult = ageOffIterator
        .apply(new WrappedIterator<>(resultRecordList.iterator()));

    // Assert
    assertTrue(actualApplyResult instanceof FilteringIterator);
    assertFalse(actualApplyResult.hasNext());
  }

  /**
   * Test {@link AgeOffIterator#apply(CloseableIterator)} with {@code CloseableIterator}.
   * <ul>
   *   <li>When {@link ConcatenatingIterator#ConcatenatingIterator(List)} with suppliers is {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AgeOffIterator#apply(CloseableIterator)}
   */
  @Test
  @DisplayName("Test apply(CloseableIterator) with 'CloseableIterator'; when ConcatenatingIterator(List) with suppliers is ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"CloseableIterator AgeOffIterator.apply(CloseableIterator)"})
  void testApplyWithCloseableIterator_whenConcatenatingIteratorWithSuppliersIsArrayList() {
    // Arrange
    AgeOffIterator ageOffIterator = new AgeOffIterator();

    // Act
    CloseableIterator<Record> actualApplyResult = ageOffIterator.apply(new ConcatenatingIterator(new ArrayList<>()));

    // Assert
    assertTrue(actualApplyResult instanceof FilteringIterator);
    assertFalse(actualApplyResult.hasNext());
  }
}
