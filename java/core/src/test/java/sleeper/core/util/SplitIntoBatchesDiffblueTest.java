package sleeper.core.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class SplitIntoBatchesDiffblueTest {
  /**
   * Test {@link SplitIntoBatches#splitListIntoBatchesOf(int, List)}.
   * <ul>
   *   <li>Given {@code 42}.</li>
   *   <li>Then return iterator next is {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SplitIntoBatches#splitListIntoBatchesOf(int, List)}
   */
  @Test
  @DisplayName("Test splitListIntoBatchesOf(int, List); given '42'; then return iterator next is ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Iterable SplitIntoBatches.splitListIntoBatchesOf(int, List)"})
  void testSplitListIntoBatchesOf_given42_thenReturnIteratorNextIsArrayList() {
    // Arrange
    ArrayList<Object> list = new ArrayList<>();
    list.add("42");

    // Act
    Iterable<List<Object>> actualSplitListIntoBatchesOfResult = SplitIntoBatches.splitListIntoBatchesOf(3, list);
    Iterator<List<Object>> actualIteratorResult = actualSplitListIntoBatchesOfResult.iterator();

    // Assert
    List<Object> actualNextResult = actualIteratorResult.next();
    assertFalse(actualIteratorResult.hasNext());
    assertEquals(list, actualNextResult);
  }

  /**
   * Test {@link SplitIntoBatches#splitListIntoBatchesOf(int, List)}.
   * <ul>
   *   <li>Given {@code 42}.</li>
   *   <li>Then return iterator next is {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SplitIntoBatches#splitListIntoBatchesOf(int, List)}
   */
  @Test
  @DisplayName("Test splitListIntoBatchesOf(int, List); given '42'; then return iterator next is ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Iterable SplitIntoBatches.splitListIntoBatchesOf(int, List)"})
  void testSplitListIntoBatchesOf_given42_thenReturnIteratorNextIsArrayList2() {
    // Arrange
    ArrayList<Object> list = new ArrayList<>();
    list.add("42");
    list.add("42");

    // Act
    Iterable<List<Object>> actualSplitListIntoBatchesOfResult = SplitIntoBatches.splitListIntoBatchesOf(3, list);
    Iterator<List<Object>> actualIteratorResult = actualSplitListIntoBatchesOfResult.iterator();

    // Assert
    List<Object> actualNextResult = actualIteratorResult.next();
    assertFalse(actualIteratorResult.hasNext());
    assertEquals(list, actualNextResult);
  }

  /**
   * Test {@link SplitIntoBatches#splitListIntoBatchesOf(int, List)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SplitIntoBatches#splitListIntoBatchesOf(int, List)}
   */
  @Test
  @DisplayName("Test splitListIntoBatchesOf(int, List); when ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Iterable SplitIntoBatches.splitListIntoBatchesOf(int, List)"})
  void testSplitListIntoBatchesOf_whenArrayList() {
    // Arrange and Act
    Iterable<List<Object>> actualSplitListIntoBatchesOfResult = SplitIntoBatches.splitListIntoBatchesOf(3,
        new ArrayList<>());

    // Assert
    assertFalse(actualSplitListIntoBatchesOfResult.iterator().hasNext());
  }

  /**
   * Test {@link SplitIntoBatches#streamBatchesOf(int, Stream)} with {@code int}, {@code Stream}.
   * <ul>
   *   <li>When three.</li>
   *   <li>Then return limit five collect toList Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link SplitIntoBatches#streamBatchesOf(int, Stream)}
   */
  @Test
  @DisplayName("Test streamBatchesOf(int, Stream) with 'int', 'Stream'; when three; then return limit five collect toList Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream SplitIntoBatches.streamBatchesOf(int, Stream)"})
  void testStreamBatchesOfWithIntStream_whenThree_thenReturnLimitFiveCollectToListEmpty() {
    // Arrange
    ArrayList<Object> objectList = new ArrayList<>();
    Stream<Object> items = objectList.stream();

    // Act
    Stream<List<Object>> actualStreamBatchesOfResult = SplitIntoBatches.streamBatchesOf(3, items);

    // Assert
    assertTrue(actualStreamBatchesOfResult.limit(5).collect(Collectors.toList()).isEmpty());
  }

  /**
   * Test {@link SplitIntoBatches#streamBatchesOf(int, Stream)} with {@code int}, {@code Stream}.
   * <ul>
   *   <li>When zero.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SplitIntoBatches#streamBatchesOf(int, Stream)}
   */
  @Test
  @DisplayName("Test streamBatchesOf(int, Stream) with 'int', 'Stream'; when zero; then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream SplitIntoBatches.streamBatchesOf(int, Stream)"})
  void testStreamBatchesOfWithIntStream_whenZero_thenThrowIllegalArgumentException() {
    // Arrange
    ArrayList<Object> objectList = new ArrayList<>();
    Stream<Object> items = objectList.stream();

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> SplitIntoBatches.streamBatchesOf(0, items));
  }

  /**
   * Test {@link SplitIntoBatches#reusingListOfSize(int, Stream, Consumer)}.
   * <ul>
   *   <li>Given {@code 42}.</li>
   *   <li>When {@link Consumer} {@link Consumer#accept(Object)} does nothing.</li>
   *   <li>Then calls {@link Consumer#accept(Object)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SplitIntoBatches#reusingListOfSize(int, Stream, Consumer)}
   */
  @Test
  @DisplayName("Test reusingListOfSize(int, Stream, Consumer); given '42'; when Consumer accept(Object) does nothing; then calls accept(Object)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SplitIntoBatches.reusingListOfSize(int, Stream, Consumer)"})
  void testReusingListOfSize_given42_whenConsumerAcceptDoesNothing_thenCallsAccept() {
    // Arrange
    ArrayList<Object> objectList = new ArrayList<>();
    objectList.add("42");
    Stream<Object> items = objectList.stream();
    Consumer<List<Object>> operation = mock(Consumer.class);
    doNothing().when(operation).accept(Mockito.<List<Object>>any());

    // Act
    SplitIntoBatches.reusingListOfSize(3, items, operation);

    // Assert
    verify(operation).accept(isA(List.class));
  }

  /**
   * Test {@link SplitIntoBatches#reusingListOfSize(int, Stream, Consumer)}.
   * <ul>
   *   <li>Given {@code 42}.</li>
   *   <li>When {@link Consumer} {@link Consumer#accept(Object)} does nothing.</li>
   *   <li>Then calls {@link Consumer#accept(Object)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SplitIntoBatches#reusingListOfSize(int, Stream, Consumer)}
   */
  @Test
  @DisplayName("Test reusingListOfSize(int, Stream, Consumer); given '42'; when Consumer accept(Object) does nothing; then calls accept(Object)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SplitIntoBatches.reusingListOfSize(int, Stream, Consumer)"})
  void testReusingListOfSize_given42_whenConsumerAcceptDoesNothing_thenCallsAccept2() {
    // Arrange
    ArrayList<Object> objectList = new ArrayList<>();
    objectList.add("42");
    objectList.add("42");
    Stream<Object> items = objectList.stream();
    Consumer<List<Object>> operation = mock(Consumer.class);
    doNothing().when(operation).accept(Mockito.<List<Object>>any());

    // Act
    SplitIntoBatches.reusingListOfSize(3, items, operation);

    // Assert
    verify(operation).accept(isA(List.class));
  }

  /**
   * Test {@link SplitIntoBatches#reusingListOfSize(int, Stream, Consumer)}.
   * <ul>
   *   <li>Given {@code 42}.</li>
   *   <li>When one.</li>
   *   <li>Then calls {@link Consumer#accept(Object)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SplitIntoBatches#reusingListOfSize(int, Stream, Consumer)}
   */
  @Test
  @DisplayName("Test reusingListOfSize(int, Stream, Consumer); given '42'; when one; then calls accept(Object)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SplitIntoBatches.reusingListOfSize(int, Stream, Consumer)"})
  void testReusingListOfSize_given42_whenOne_thenCallsAccept() {
    // Arrange
    ArrayList<Object> objectList = new ArrayList<>();
    objectList.add("42");
    objectList.add("42");
    Stream<Object> items = objectList.stream();
    Consumer<List<Object>> operation = mock(Consumer.class);
    doNothing().when(operation).accept(Mockito.<List<Object>>any());

    // Act
    SplitIntoBatches.reusingListOfSize(1, items, operation);

    // Assert
    verify(operation, atLeast(1)).accept(isA(List.class));
  }

  /**
   * Test {@link SplitIntoBatches#reusingListOfSize(int, Stream, Consumer)}.
   * <ul>
   *   <li>Given {@link IllegalArgumentException#IllegalArgumentException(String)} with {@code foo}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SplitIntoBatches#reusingListOfSize(int, Stream, Consumer)}
   */
  @Test
  @DisplayName("Test reusingListOfSize(int, Stream, Consumer); given IllegalArgumentException(String) with 'foo'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SplitIntoBatches.reusingListOfSize(int, Stream, Consumer)"})
  void testReusingListOfSize_givenIllegalArgumentExceptionWithFoo() {
    // Arrange
    ArrayList<Object> objectList = new ArrayList<>();
    objectList.add("42");
    Stream<Object> items = objectList.stream();
    Consumer<List<Object>> operation = mock(Consumer.class);
    doThrow(new IllegalArgumentException("foo")).when(operation).accept(Mockito.<List<Object>>any());

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> SplitIntoBatches.reusingListOfSize(3, items, operation));
    verify(operation).accept(isA(List.class));
  }

  /**
   * Test {@link SplitIntoBatches#reusingListOfSize(int, Stream, Consumer)}.
   * <ul>
   *   <li>Given {@link IllegalArgumentException#IllegalArgumentException(String)} with {@code foo}.</li>
   *   <li>When one.</li>
   * </ul>
   * <p>
   * Method under test: {@link SplitIntoBatches#reusingListOfSize(int, Stream, Consumer)}
   */
  @Test
  @DisplayName("Test reusingListOfSize(int, Stream, Consumer); given IllegalArgumentException(String) with 'foo'; when one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SplitIntoBatches.reusingListOfSize(int, Stream, Consumer)"})
  void testReusingListOfSize_givenIllegalArgumentExceptionWithFoo_whenOne() {
    // Arrange
    ArrayList<Object> objectList = new ArrayList<>();
    objectList.add("42");
    objectList.add("42");
    Stream<Object> items = objectList.stream();
    Consumer<List<Object>> operation = mock(Consumer.class);
    doThrow(new IllegalArgumentException("foo")).when(operation).accept(Mockito.<List<Object>>any());

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> SplitIntoBatches.reusingListOfSize(1, items, operation));
    verify(operation).accept(isA(List.class));
  }

  /**
   * Test {@link SplitIntoBatches#reusingListOfSize(int, Stream, Consumer)}.
   * <ul>
   *   <li>When zero.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SplitIntoBatches#reusingListOfSize(int, Stream, Consumer)}
   */
  @Test
  @DisplayName("Test reusingListOfSize(int, Stream, Consumer); when zero; then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SplitIntoBatches.reusingListOfSize(int, Stream, Consumer)"})
  void testReusingListOfSize_whenZero_thenThrowIllegalArgumentException() {
    // Arrange
    ArrayList<Object> objectList = new ArrayList<>();
    Stream<Object> items = objectList.stream();

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> SplitIntoBatches.reusingListOfSize(0, items, mock(Consumer.class)));
  }
}
