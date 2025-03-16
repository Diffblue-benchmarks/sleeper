package sleeper.core.record;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.key.Key;
import sleeper.core.schema.type.PrimitiveType;

class KeyComparatorDiffblueTest {
  /**
   * Test {@link KeyComparator#KeyComparator(List)}.
   * <ul>
   *   <li>Given {@link PrimitiveType} (default constructor).</li>
   *   <li>Then return compare {@link Key} and {@link Key} is minus one.</li>
   * </ul>
   * <p>
   * Method under test: {@link KeyComparator#KeyComparator(List)}
   */
  @Test
  @DisplayName("Test new KeyComparator(List); given PrimitiveType (default constructor); then return compare Key and Key is minus one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void KeyComparator.<init>(List)"})
  void testNewKeyComparator_givenPrimitiveType_thenReturnCompareKeyAndKeyIsMinusOne() {
    // Arrange
    ArrayList<PrimitiveType> rowKeyTypes = new ArrayList<>();
    rowKeyTypes.add(new PrimitiveType());

    // Act
    KeyComparator actualKeyComparator = new KeyComparator(rowKeyTypes);
    Key key1 = mock(Key.class);
    when(key1.get(anyInt())).thenReturn("Get");
    Key key2 = mock(Key.class);
    when(key2.get(anyInt())).thenReturn(null);
    int actualCompareResult = actualKeyComparator.compare(key1, key2);

    // Assert
    verify(key1).get(eq(0));
    verify(key2).get(eq(0));
    assertEquals(-1, actualCompareResult);
  }

  /**
   * Test {@link KeyComparator#KeyComparator(List)}.
   * <ul>
   *   <li>Given {@link PrimitiveType} (default constructor).</li>
   *   <li>Then return compare {@link Key} and {@link Key} is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link KeyComparator#KeyComparator(List)}
   */
  @Test
  @DisplayName("Test new KeyComparator(List); given PrimitiveType (default constructor); then return compare Key and Key is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void KeyComparator.<init>(List)"})
  void testNewKeyComparator_givenPrimitiveType_thenReturnCompareKeyAndKeyIsOne() {
    // Arrange
    ArrayList<PrimitiveType> rowKeyTypes = new ArrayList<>();
    rowKeyTypes.add(new PrimitiveType());

    // Act
    KeyComparator actualKeyComparator = new KeyComparator(rowKeyTypes);
    Key key1 = mock(Key.class);
    when(key1.get(anyInt())).thenReturn(null);
    Key key2 = mock(Key.class);
    when(key2.get(anyInt())).thenReturn("Get");
    int actualCompareResult = actualKeyComparator.compare(key1, key2);

    // Assert
    verify(key1).get(eq(0));
    verify(key2).get(eq(0));
    assertEquals(1, actualCompareResult);
  }

  /**
   * Test {@link KeyComparator#KeyComparator(List)}.
   * <ul>
   *   <li>Given {@link PrimitiveType} (default constructor).</li>
   *   <li>Then return compare {@link Key} and {@link Key} is thirty-one.</li>
   * </ul>
   * <p>
   * Method under test: {@link KeyComparator#KeyComparator(List)}
   */
  @Test
  @DisplayName("Test new KeyComparator(List); given PrimitiveType (default constructor); then return compare Key and Key is thirty-one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void KeyComparator.<init>(List)"})
  void testNewKeyComparator_givenPrimitiveType_thenReturnCompareKeyAndKeyIsThirtyOne() {
    // Arrange
    ArrayList<PrimitiveType> rowKeyTypes = new ArrayList<>();
    rowKeyTypes.add(new PrimitiveType());

    // Act
    KeyComparator actualKeyComparator = new KeyComparator(rowKeyTypes);
    Key key1 = mock(Key.class);
    when(key1.get(anyInt())).thenReturn("foo");
    Key key2 = mock(Key.class);
    when(key2.get(anyInt())).thenReturn("Get");
    int actualCompareResult = actualKeyComparator.compare(key1, key2);

    // Assert
    verify(key1).get(eq(0));
    verify(key2).get(eq(0));
    assertEquals(31, actualCompareResult);
  }

  /**
   * Test {@link KeyComparator#KeyComparator(List)}.
   * <ul>
   *   <li>Given {@link PrimitiveType} (default constructor).</li>
   *   <li>Then return compare {@link Key} and {@link Key} is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link KeyComparator#KeyComparator(List)}
   */
  @Test
  @DisplayName("Test new KeyComparator(List); given PrimitiveType (default constructor); then return compare Key and Key is zero")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void KeyComparator.<init>(List)"})
  void testNewKeyComparator_givenPrimitiveType_thenReturnCompareKeyAndKeyIsZero() {
    // Arrange
    ArrayList<PrimitiveType> rowKeyTypes = new ArrayList<>();
    rowKeyTypes.add(new PrimitiveType());

    // Act
    KeyComparator actualKeyComparator = new KeyComparator(rowKeyTypes);
    Key key1 = mock(Key.class);
    when(key1.get(anyInt())).thenReturn("Get");
    Key key2 = mock(Key.class);
    when(key2.get(anyInt())).thenReturn("Get");
    int actualCompareResult = actualKeyComparator.compare(key1, key2);

    // Assert
    verify(key1).get(eq(0));
    verify(key2).get(eq(0));
    assertEquals(0, actualCompareResult);
  }

  /**
   * Test {@link KeyComparator#KeyComparator(List)}.
   * <ul>
   *   <li>Given {@link PrimitiveType} (default constructor).</li>
   *   <li>Then return compare {@link Key} and {@link Key} is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link KeyComparator#KeyComparator(List)}
   */
  @Test
  @DisplayName("Test new KeyComparator(List); given PrimitiveType (default constructor); then return compare Key and Key is zero")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void KeyComparator.<init>(List)"})
  void testNewKeyComparator_givenPrimitiveType_thenReturnCompareKeyAndKeyIsZero2() {
    // Arrange
    ArrayList<PrimitiveType> rowKeyTypes = new ArrayList<>();
    rowKeyTypes.add(new PrimitiveType());
    rowKeyTypes.add(new PrimitiveType());

    // Act
    KeyComparator actualKeyComparator = new KeyComparator(rowKeyTypes);
    Key key1 = mock(Key.class);
    when(key1.get(anyInt())).thenReturn("Get");
    Key key2 = mock(Key.class);
    when(key2.get(anyInt())).thenReturn("Get");
    int actualCompareResult = actualKeyComparator.compare(key1, key2);

    // Assert
    verify(key1, atLeast(1)).get(anyInt());
    verify(key2, atLeast(1)).get(anyInt());
    assertEquals(0, actualCompareResult);
  }

  /**
   * Test {@link KeyComparator#KeyComparator(PrimitiveType[])}.
   * <ul>
   *   <li>Then return compare {@link Key} and {@link Key} is minus one.</li>
   * </ul>
   * <p>
   * Method under test: {@link KeyComparator#KeyComparator(PrimitiveType[])}
   */
  @Test
  @DisplayName("Test new KeyComparator(PrimitiveType[]); then return compare Key and Key is minus one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void KeyComparator.<init>(PrimitiveType[])"})
  void testNewKeyComparator_thenReturnCompareKeyAndKeyIsMinusOne() {
    // Arrange and Act
    KeyComparator actualKeyComparator = new KeyComparator(new PrimitiveType());
    Key key1 = mock(Key.class);
    when(key1.get(anyInt())).thenReturn("Get");
    Key key2 = mock(Key.class);
    when(key2.get(anyInt())).thenReturn(null);
    int actualCompareResult = actualKeyComparator.compare(key1, key2);

    // Assert
    verify(key1).get(eq(0));
    verify(key2).get(eq(0));
    assertEquals(-1, actualCompareResult);
  }

  /**
   * Test {@link KeyComparator#KeyComparator(PrimitiveType[])}.
   * <ul>
   *   <li>Then return compare {@link Key} and {@link Key} is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link KeyComparator#KeyComparator(PrimitiveType[])}
   */
  @Test
  @DisplayName("Test new KeyComparator(PrimitiveType[]); then return compare Key and Key is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void KeyComparator.<init>(PrimitiveType[])"})
  void testNewKeyComparator_thenReturnCompareKeyAndKeyIsOne() {
    // Arrange and Act
    KeyComparator actualKeyComparator = new KeyComparator(new PrimitiveType());
    Key key1 = mock(Key.class);
    when(key1.get(anyInt())).thenReturn(null);
    Key key2 = mock(Key.class);
    when(key2.get(anyInt())).thenReturn("Get");
    int actualCompareResult = actualKeyComparator.compare(key1, key2);

    // Assert
    verify(key1).get(eq(0));
    verify(key2).get(eq(0));
    assertEquals(1, actualCompareResult);
  }

  /**
   * Test {@link KeyComparator#KeyComparator(PrimitiveType[])}.
   * <ul>
   *   <li>Then return compare {@link Key} and {@link Key} is thirty-one.</li>
   * </ul>
   * <p>
   * Method under test: {@link KeyComparator#KeyComparator(PrimitiveType[])}
   */
  @Test
  @DisplayName("Test new KeyComparator(PrimitiveType[]); then return compare Key and Key is thirty-one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void KeyComparator.<init>(PrimitiveType[])"})
  void testNewKeyComparator_thenReturnCompareKeyAndKeyIsThirtyOne() {
    // Arrange and Act
    KeyComparator actualKeyComparator = new KeyComparator(new PrimitiveType());
    Key key1 = mock(Key.class);
    when(key1.get(anyInt())).thenReturn("foo");
    Key key2 = mock(Key.class);
    when(key2.get(anyInt())).thenReturn("Get");
    int actualCompareResult = actualKeyComparator.compare(key1, key2);

    // Assert
    verify(key1).get(eq(0));
    verify(key2).get(eq(0));
    assertEquals(31, actualCompareResult);
  }

  /**
   * Test {@link KeyComparator#KeyComparator(PrimitiveType[])}.
   * <ul>
   *   <li>Then return compare {@link Key} and {@link Key} is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link KeyComparator#KeyComparator(PrimitiveType[])}
   */
  @Test
  @DisplayName("Test new KeyComparator(PrimitiveType[]); then return compare Key and Key is zero")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void KeyComparator.<init>(PrimitiveType[])"})
  void testNewKeyComparator_thenReturnCompareKeyAndKeyIsZero() {
    // Arrange and Act
    KeyComparator actualKeyComparator = new KeyComparator(new PrimitiveType());
    Key key1 = mock(Key.class);
    when(key1.get(anyInt())).thenReturn("Get");
    Key key2 = mock(Key.class);
    when(key2.get(anyInt())).thenReturn("Get");
    int actualCompareResult = actualKeyComparator.compare(key1, key2);

    // Assert
    verify(key1).get(eq(0));
    verify(key2).get(eq(0));
    assertEquals(0, actualCompareResult);
  }

  /**
   * Test {@link KeyComparator#KeyComparator(List)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return compare {@link Key} and {@link Key} is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link KeyComparator#KeyComparator(List)}
   */
  @Test
  @DisplayName("Test new KeyComparator(List); when ArrayList(); then return compare Key and Key is zero")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void KeyComparator.<init>(List)"})
  void testNewKeyComparator_whenArrayList_thenReturnCompareKeyAndKeyIsZero() {
    // Arrange, Act and Assert
    assertEquals(0, (new KeyComparator(new ArrayList<>())).compare(mock(Key.class), mock(Key.class)));
  }

  /**
   * Test {@link KeyComparator#compare(Key, Key)} with {@code Key}, {@code Key}.
   * <ul>
   *   <li>Given {@code foo}.</li>
   *   <li>When {@link Key} {@link Key#get(int)} return {@code foo}.</li>
   *   <li>Then return thirty-one.</li>
   * </ul>
   * <p>
   * Method under test: {@link KeyComparator#compare(Key, Key)}
   */
  @Test
  @DisplayName("Test compare(Key, Key) with 'Key', 'Key'; given 'foo'; when Key get(int) return 'foo'; then return thirty-one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"int KeyComparator.compare(Key, Key)"})
  void testCompareWithKeyKey_givenFoo_whenKeyGetReturnFoo_thenReturnThirtyOne() {
    // Arrange
    KeyComparator keyComparator = new KeyComparator(new PrimitiveType());
    Key key1 = mock(Key.class);
    when(key1.get(anyInt())).thenReturn("foo");
    Key key2 = mock(Key.class);
    when(key2.get(anyInt())).thenReturn("Get");

    // Act
    int actualCompareResult = keyComparator.compare(key1, key2);

    // Assert
    verify(key1).get(eq(0));
    verify(key2).get(eq(0));
    assertEquals(31, actualCompareResult);
  }

  /**
   * Test {@link KeyComparator#compare(Key, Key)} with {@code Key}, {@code Key}.
   * <ul>
   *   <li>Given {@link KeyComparator#KeyComparator()}.</li>
   *   <li>When {@link Key}.</li>
   *   <li>Then return zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link KeyComparator#compare(Key, Key)}
   */
  @Test
  @DisplayName("Test compare(Key, Key) with 'Key', 'Key'; given KeyComparator(); when Key; then return zero")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"int KeyComparator.compare(Key, Key)"})
  void testCompareWithKeyKey_givenKeyComparator_whenKey_thenReturnZero() {
    // Arrange, Act and Assert
    assertEquals(0, (new KeyComparator()).compare(mock(Key.class), mock(Key.class)));
  }

  /**
   * Test {@link KeyComparator#compare(Key, Key)} with {@code Key}, {@code Key}.
   * <ul>
   *   <li>Given {@code null}.</li>
   *   <li>When {@link Key} {@link Key#get(int)} return {@code null}.</li>
   *   <li>Then return minus one.</li>
   * </ul>
   * <p>
   * Method under test: {@link KeyComparator#compare(Key, Key)}
   */
  @Test
  @DisplayName("Test compare(Key, Key) with 'Key', 'Key'; given 'null'; when Key get(int) return 'null'; then return minus one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"int KeyComparator.compare(Key, Key)"})
  void testCompareWithKeyKey_givenNull_whenKeyGetReturnNull_thenReturnMinusOne() {
    // Arrange
    KeyComparator keyComparator = new KeyComparator(new PrimitiveType());
    Key key1 = mock(Key.class);
    when(key1.get(anyInt())).thenReturn("Get");
    Key key2 = mock(Key.class);
    when(key2.get(anyInt())).thenReturn(null);

    // Act
    int actualCompareResult = keyComparator.compare(key1, key2);

    // Assert
    verify(key1).get(eq(0));
    verify(key2).get(eq(0));
    assertEquals(-1, actualCompareResult);
  }

  /**
   * Test {@link KeyComparator#compare(Key, Key)} with {@code Key}, {@code Key}.
   * <ul>
   *   <li>Given {@code null}.</li>
   *   <li>When {@link Key} {@link Key#get(int)} return {@code null}.</li>
   *   <li>Then return one.</li>
   * </ul>
   * <p>
   * Method under test: {@link KeyComparator#compare(Key, Key)}
   */
  @Test
  @DisplayName("Test compare(Key, Key) with 'Key', 'Key'; given 'null'; when Key get(int) return 'null'; then return one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"int KeyComparator.compare(Key, Key)"})
  void testCompareWithKeyKey_givenNull_whenKeyGetReturnNull_thenReturnOne() {
    // Arrange
    KeyComparator keyComparator = new KeyComparator(new PrimitiveType());
    Key key1 = mock(Key.class);
    when(key1.get(anyInt())).thenReturn(null);
    Key key2 = mock(Key.class);
    when(key2.get(anyInt())).thenReturn("Get");

    // Act
    int actualCompareResult = keyComparator.compare(key1, key2);

    // Assert
    verify(key1).get(eq(0));
    verify(key2).get(eq(0));
    assertEquals(1, actualCompareResult);
  }

  /**
   * Test {@link KeyComparator#compare(Key, Key)} with {@code Key}, {@code Key}.
   * <ul>
   *   <li>Then return zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link KeyComparator#compare(Key, Key)}
   */
  @Test
  @DisplayName("Test compare(Key, Key) with 'Key', 'Key'; then return zero")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"int KeyComparator.compare(Key, Key)"})
  void testCompareWithKeyKey_thenReturnZero() {
    // Arrange
    KeyComparator keyComparator = new KeyComparator(new PrimitiveType());
    Key key1 = mock(Key.class);
    when(key1.get(anyInt())).thenReturn("Get");
    Key key2 = mock(Key.class);
    when(key2.get(anyInt())).thenReturn("Get");

    // Act
    int actualCompareResult = keyComparator.compare(key1, key2);

    // Assert
    verify(key1).get(eq(0));
    verify(key2).get(eq(0));
    assertEquals(0, actualCompareResult);
  }
}
