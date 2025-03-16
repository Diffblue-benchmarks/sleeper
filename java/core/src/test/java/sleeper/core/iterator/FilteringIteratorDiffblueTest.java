package sleeper.core.iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.function.Predicate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class FilteringIteratorDiffblueTest {
  /**
   * Test {@link FilteringIterator#FilteringIterator(CloseableIterator, Predicate)}.
   * <ul>
   *   <li>Given {@code 42}.</li>
   *   <li>When {@link ArrayList#ArrayList()} add {@code 42}.</li>
   *   <li>Then return next is {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link FilteringIterator#FilteringIterator(CloseableIterator, Predicate)}
   */
  @Test
  @DisplayName("Test new FilteringIterator(CloseableIterator, Predicate); given '42'; when ArrayList() add '42'; then return next is '42'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void FilteringIterator.<init>(CloseableIterator, Predicate)"})
  void testNewFilteringIterator_given42_whenArrayListAdd42_thenReturnNextIs42() {
    // Arrange
    ArrayList<Object> objectList = new ArrayList<>();
    objectList.add("42");
    WrappedIterator<Object> input = new WrappedIterator<>(objectList.iterator());
    Predicate<Object> predicate = mock(Predicate.class);
    when(predicate.test(Mockito.<Object>any())).thenReturn(true);

    // Act
    FilteringIterator<Object> actualFilteringIterator = new FilteringIterator<>(input, predicate);

    // Assert
    verify(predicate).test(isA(Object.class));
    assertEquals("42", actualFilteringIterator.next());
    assertFalse(actualFilteringIterator.hasNext());
    assertFalse(input.hasNext());
  }

  /**
   * Test {@link FilteringIterator#FilteringIterator(CloseableIterator, Predicate)}.
   * <ul>
   *   <li>Given {@code false}.</li>
   *   <li>When {@link Predicate} {@link Predicate#test(Object)} return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link FilteringIterator#FilteringIterator(CloseableIterator, Predicate)}
   */
  @Test
  @DisplayName("Test new FilteringIterator(CloseableIterator, Predicate); given 'false'; when Predicate test(Object) return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void FilteringIterator.<init>(CloseableIterator, Predicate)"})
  void testNewFilteringIterator_givenFalse_whenPredicateTestReturnFalse() {
    // Arrange
    ArrayList<Object> objectList = new ArrayList<>();
    objectList.add("42");
    WrappedIterator<Object> input = new WrappedIterator<>(objectList.iterator());
    Predicate<Object> predicate = mock(Predicate.class);
    when(predicate.test(Mockito.<Object>any())).thenReturn(false);

    // Act
    FilteringIterator<Object> actualFilteringIterator = new FilteringIterator<>(input, predicate);

    // Assert
    verify(predicate).test(isA(Object.class));
    assertFalse(actualFilteringIterator.hasNext());
    assertFalse(input.hasNext());
  }

  /**
   * Test {@link FilteringIterator#FilteringIterator(CloseableIterator, Predicate)}.
   * <ul>
   *   <li>Then return next is {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link FilteringIterator#FilteringIterator(CloseableIterator, Predicate)}
   */
  @Test
  @DisplayName("Test new FilteringIterator(CloseableIterator, Predicate); then return next is '42'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void FilteringIterator.<init>(CloseableIterator, Predicate)"})
  void testNewFilteringIterator_thenReturnNextIs42() {
    // Arrange
    ArrayList<Object> objectList = new ArrayList<>();
    objectList.add("42");
    WrappedIterator<Object> input = new WrappedIterator<>(objectList.iterator());
    Predicate<Object> predicate = mock(Predicate.class);
    when(predicate.test(Mockito.<Object>any())).thenReturn(true);
    FilteringIterator<Object> input2 = new FilteringIterator<>(input, predicate);

    Predicate<Object> predicate2 = mock(Predicate.class);
    when(predicate2.test(Mockito.<Object>any())).thenReturn(true);

    // Act
    FilteringIterator<Object> actualFilteringIterator = new FilteringIterator<>(input2, predicate2);

    // Assert
    verify(predicate).test(isA(Object.class));
    verify(predicate2).test(isA(Object.class));
    assertEquals("42", actualFilteringIterator.next());
    assertFalse(actualFilteringIterator.hasNext());
    assertFalse(input2.hasNext());
  }

  /**
   * Test {@link FilteringIterator#FilteringIterator(CloseableIterator, Predicate)}.
   * <ul>
   *   <li>When {@link Predicate}.</li>
   * </ul>
   * <p>
   * Method under test: {@link FilteringIterator#FilteringIterator(CloseableIterator, Predicate)}
   */
  @Test
  @DisplayName("Test new FilteringIterator(CloseableIterator, Predicate); when Predicate")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void FilteringIterator.<init>(CloseableIterator, Predicate)"})
  void testNewFilteringIterator_whenPredicate() {
    // Arrange
    ArrayList<Object> objectList = new ArrayList<>();
    WrappedIterator<Object> input = new WrappedIterator<>(objectList.iterator());

    // Act
    FilteringIterator<Object> actualFilteringIterator = new FilteringIterator<>(input, mock(Predicate.class));

    // Assert
    assertFalse(actualFilteringIterator.hasNext());
    assertFalse(input.hasNext());
  }

  /**
   * Test {@link FilteringIterator#FilteringIterator(CloseableIterator, Predicate)}.
   * <ul>
   *   <li>When {@link Predicate}.</li>
   * </ul>
   * <p>
   * Method under test: {@link FilteringIterator#FilteringIterator(CloseableIterator, Predicate)}
   */
  @Test
  @DisplayName("Test new FilteringIterator(CloseableIterator, Predicate); when Predicate")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void FilteringIterator.<init>(CloseableIterator, Predicate)"})
  void testNewFilteringIterator_whenPredicate2() {
    // Arrange
    ArrayList<Object> objectList = new ArrayList<>();
    FilteringIterator<Object> input = new FilteringIterator<>(new WrappedIterator<>(objectList.iterator()),
        mock(Predicate.class));

    // Act
    FilteringIterator<Object> actualFilteringIterator = new FilteringIterator<>(input, mock(Predicate.class));

    // Assert
    assertFalse(actualFilteringIterator.hasNext());
    assertFalse(input.hasNext());
  }

  /**
   * Test {@link FilteringIterator#hasNext()}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@code 42}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link FilteringIterator#hasNext()}
   */
  @Test
  @DisplayName("Test hasNext(); given ArrayList() add '42'; then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean FilteringIterator.hasNext()"})
  void testHasNext_givenArrayListAdd42_thenReturnTrue() {
    // Arrange
    ArrayList<Object> objectList = new ArrayList<>();
    objectList.add("42");
    WrappedIterator<Object> input = new WrappedIterator<>(objectList.iterator());
    Predicate<Object> predicate = mock(Predicate.class);
    when(predicate.test(Mockito.<Object>any())).thenReturn(true);
    FilteringIterator<Object> filteringIterator = new FilteringIterator<>(input, predicate);

    // Act
    boolean actualHasNextResult = filteringIterator.hasNext();

    // Assert
    verify(predicate).test(isA(Object.class));
    assertTrue(actualHasNextResult);
  }

  /**
   * Test {@link FilteringIterator#hasNext()}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link FilteringIterator#hasNext()}
   */
  @Test
  @DisplayName("Test hasNext(); then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean FilteringIterator.hasNext()"})
  void testHasNext_thenReturnFalse() {
    // Arrange
    ArrayList<Object> objectList = new ArrayList<>();
    FilteringIterator<Object> filteringIterator = new FilteringIterator<>(new WrappedIterator<>(objectList.iterator()),
        mock(Predicate.class));

    // Act and Assert
    assertFalse(filteringIterator.hasNext());
  }

  /**
   * Test {@link FilteringIterator#next()}.
   * <p>
   * Method under test: {@link FilteringIterator#next()}
   */
  @Test
  @DisplayName("Test next()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Object FilteringIterator.next()"})
  void testNext() {
    // Arrange
    ArrayList<Object> objectList = new ArrayList<>();
    FilteringIterator<Object> filteringIterator = new FilteringIterator<>(new WrappedIterator<>(objectList.iterator()),
        mock(Predicate.class));

    // Act and Assert
    assertNull(filteringIterator.next());
  }

  /**
   * Test {@link FilteringIterator#next()}.
   * <ul>
   *   <li>Given {@link FilteringIterator#FilteringIterator(CloseableIterator, Predicate)} with input is {@link FilteringIterator#FilteringIterator(CloseableIterator, Predicate)} and {@link Predicate}.</li>
   * </ul>
   * <p>
   * Method under test: {@link FilteringIterator#next()}
   */
  @Test
  @DisplayName("Test next(); given FilteringIterator(CloseableIterator, Predicate) with input is FilteringIterator(CloseableIterator, Predicate) and Predicate")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Object FilteringIterator.next()"})
  void testNext_givenFilteringIteratorWithInputIsFilteringIteratorAndPredicate() {
    // Arrange
    ArrayList<Object> objectList = new ArrayList<>();
    FilteringIterator<Object> filteringIterator = new FilteringIterator<>(
        new FilteringIterator<>(new WrappedIterator<>(objectList.iterator()), mock(Predicate.class)),
        mock(Predicate.class));

    // Act and Assert
    assertNull(filteringIterator.next());
  }
}
