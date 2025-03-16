package sleeper.core.iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.Iterator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class WrappedIteratorDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link WrappedIterator#WrappedIterator(Iterator)}
   *   <li>{@link WrappedIterator#close()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void WrappedIterator.<init>(Iterator)", "void WrappedIterator.close()"})
  void testGettersAndSetters() {
    // Arrange
    ArrayList<Object> objectList = new ArrayList<>();

    // Act
    WrappedIterator<Object> actualWrappedIterator = new WrappedIterator<>(objectList.iterator());
    actualWrappedIterator.close();

    // Assert
    assertFalse(actualWrappedIterator.hasNext());
  }

  /**
   * Test {@link WrappedIterator#hasNext()}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@code 42}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WrappedIterator#hasNext()}
   */
  @Test
  @DisplayName("Test hasNext(); given ArrayList() add '42'; then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean WrappedIterator.hasNext()"})
  void testHasNext_givenArrayListAdd42_thenReturnTrue() {
    // Arrange
    ArrayList<Object> objectList = new ArrayList<>();
    objectList.add("42");
    WrappedIterator<Object> wrappedIterator = new WrappedIterator<>(objectList.iterator());

    // Act and Assert
    assertTrue(wrappedIterator.hasNext());
  }

  /**
   * Test {@link WrappedIterator#hasNext()}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WrappedIterator#hasNext()}
   */
  @Test
  @DisplayName("Test hasNext(); then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean WrappedIterator.hasNext()"})
  void testHasNext_thenReturnFalse() {
    // Arrange
    ArrayList<Object> objectList = new ArrayList<>();
    WrappedIterator<Object> wrappedIterator = new WrappedIterator<>(objectList.iterator());

    // Act and Assert
    assertFalse(wrappedIterator.hasNext());
  }

  /**
   * Test {@link WrappedIterator#next()}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@code 42}.</li>
   *   <li>Then return {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WrappedIterator#next()}
   */
  @Test
  @DisplayName("Test next(); given ArrayList() add '42'; then return '42'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Object WrappedIterator.next()"})
  void testNext_givenArrayListAdd42_thenReturn42() {
    // Arrange
    ArrayList<Object> objectList = new ArrayList<>();
    objectList.add("42");
    WrappedIterator<Object> wrappedIterator = new WrappedIterator<>(objectList.iterator());

    // Act and Assert
    assertEquals("42", wrappedIterator.next());
  }
}
