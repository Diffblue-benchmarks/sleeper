package sleeper.core.key;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.schema.Schema;
import sleeper.core.schema.type.ByteArrayType;
import sleeper.core.schema.type.IntType;
import sleeper.core.schema.type.LongType;
import sleeper.core.schema.type.PrimitiveType;
import sleeper.core.schema.type.StringType;

class KeySerDeDiffblueTest {
  /**
   * Test {@link KeySerDe#KeySerDe(List)}.
   * <ul>
   *   <li>Given {@link PrimitiveType} (default constructor).</li>
   *   <li>When {@link ArrayList#ArrayList()} add {@link PrimitiveType} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link KeySerDe#KeySerDe(List)}
   */
  @Test
  @DisplayName("Test new KeySerDe(List); given PrimitiveType (default constructor); when ArrayList() add PrimitiveType (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void KeySerDe.<init>(List)"})
  void testNewKeySerDe_givenPrimitiveType_whenArrayListAddPrimitiveType() throws IOException {
    // Arrange
    ArrayList<PrimitiveType> rowKeyTypes = new ArrayList<>();
    rowKeyTypes.add(new PrimitiveType());

    // Act and Assert
    assertArrayEquals(new byte[]{0, 0, 0, 0}, (new KeySerDe(rowKeyTypes)).serialise(null));
  }

  /**
   * Test {@link KeySerDe#KeySerDe(List)}.
   * <ul>
   *   <li>Given {@link PrimitiveType} (default constructor).</li>
   *   <li>When {@link ArrayList#ArrayList()} add {@link PrimitiveType} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link KeySerDe#KeySerDe(List)}
   */
  @Test
  @DisplayName("Test new KeySerDe(List); given PrimitiveType (default constructor); when ArrayList() add PrimitiveType (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void KeySerDe.<init>(List)"})
  void testNewKeySerDe_givenPrimitiveType_whenArrayListAddPrimitiveType2() throws IOException {
    // Arrange
    ArrayList<PrimitiveType> rowKeyTypes = new ArrayList<>();
    rowKeyTypes.add(new PrimitiveType());
    rowKeyTypes.add(new PrimitiveType());

    // Act and Assert
    assertArrayEquals(new byte[]{0, 0, 0, 0}, (new KeySerDe(rowKeyTypes)).serialise(null));
  }

  /**
   * Test {@link KeySerDe#KeySerDe(Schema)}.
   * <ul>
   *   <li>Then return serialise {@code null} is array of {@code byte} with zero and zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link KeySerDe#KeySerDe(Schema)}
   */
  @Test
  @DisplayName("Test new KeySerDe(Schema); then return serialise 'null' is array of byte with zero and zero")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void KeySerDe.<init>(Schema)"})
  void testNewKeySerDe_thenReturnSerialiseNullIsArrayOfByteWithZeroAndZero() throws IOException {
    // Arrange
    Schema schema = mock(Schema.class);
    when(schema.getRowKeyTypes()).thenReturn(new ArrayList<>());

    // Act
    KeySerDe actualKeySerDe = new KeySerDe(schema);

    // Assert
    verify(schema).getRowKeyTypes();
    assertArrayEquals(new byte[]{0, 0, 0, 0}, actualKeySerDe.serialise(null));
  }

  /**
   * Test {@link KeySerDe#KeySerDe(Schema)}.
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link KeySerDe#KeySerDe(Schema)}
   */
  @Test
  @DisplayName("Test new KeySerDe(Schema); then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void KeySerDe.<init>(Schema)"})
  void testNewKeySerDe_thenThrowIllegalArgumentException() {
    // Arrange
    Schema schema = mock(Schema.class);
    when(schema.getRowKeyTypes()).thenThrow(new IllegalArgumentException("foo"));

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> new KeySerDe(schema));
    verify(schema).getRowKeyTypes();
  }

  /**
   * Test {@link KeySerDe#KeySerDe(List)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link KeySerDe#KeySerDe(List)}
   */
  @Test
  @DisplayName("Test new KeySerDe(List); when ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void KeySerDe.<init>(List)"})
  void testNewKeySerDe_whenArrayList() throws IOException {
    // Arrange, Act and Assert
    assertArrayEquals(new byte[]{0, 0, 0, 0}, (new KeySerDe(new ArrayList<>())).serialise(null));
  }

  /**
   * Test {@link KeySerDe#serialise(Key)}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link ByteArrayType} (default constructor).</li>
   *   <li>When create {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link KeySerDe#serialise(Key)}
   */
  @Test
  @DisplayName("Test serialise(Key); given ArrayList() add ByteArrayType (default constructor); when create 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"byte[] KeySerDe.serialise(Key)"})
  void testSerialise_givenArrayListAddByteArrayType_whenCreateNull() throws IOException {
    // Arrange
    ArrayList<PrimitiveType> rowKeyTypes = new ArrayList<>();
    rowKeyTypes.add(new ByteArrayType());
    KeySerDe keySerDe = new KeySerDe(rowKeyTypes);

    // Act and Assert
    assertArrayEquals(new byte[]{0, 0, 0, 1, 0, 0, 0, 23, 'S', 'L', 'E', 'E', 'P', 'E', 'R', '-', 'N', 'U', 'L', 'L',
        '-', 'B', 'Y', 'T', 'E', '-', 'A', 'R', 'R', 'A', 'Y'}, keySerDe.serialise(Key.create(null)));
  }

  /**
   * Test {@link KeySerDe#serialise(Key)}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link IntType} (default constructor).</li>
   *   <li>When create {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link KeySerDe#serialise(Key)}
   */
  @Test
  @DisplayName("Test serialise(Key); given ArrayList() add IntType (default constructor); when create 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"byte[] KeySerDe.serialise(Key)"})
  void testSerialise_givenArrayListAddIntType_whenCreateNull() throws IOException {
    // Arrange
    ArrayList<PrimitiveType> rowKeyTypes = new ArrayList<>();
    rowKeyTypes.add(new IntType());
    KeySerDe keySerDe = new KeySerDe(rowKeyTypes);

    // Act and Assert
    assertArrayEquals(new byte[]{0, 0, 0, 1, 1}, keySerDe.serialise(Key.create(null)));
  }

  /**
   * Test {@link KeySerDe#serialise(Key)}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link IntType} (default constructor).</li>
   *   <li>When create one.</li>
   * </ul>
   * <p>
   * Method under test: {@link KeySerDe#serialise(Key)}
   */
  @Test
  @DisplayName("Test serialise(Key); given ArrayList() add IntType (default constructor); when create one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"byte[] KeySerDe.serialise(Key)"})
  void testSerialise_givenArrayListAddIntType_whenCreateOne() throws IOException {
    // Arrange
    ArrayList<PrimitiveType> rowKeyTypes = new ArrayList<>();
    rowKeyTypes.add(new IntType());
    KeySerDe keySerDe = new KeySerDe(rowKeyTypes);

    // Act and Assert
    assertArrayEquals(new byte[]{0, 0, 0, 1, 0, 0, 0, 0, 1}, keySerDe.serialise(Key.create(1)));
  }

  /**
   * Test {@link KeySerDe#serialise(Key)}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link LongType} (default constructor).</li>
   *   <li>When create {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link KeySerDe#serialise(Key)}
   */
  @Test
  @DisplayName("Test serialise(Key); given ArrayList() add LongType (default constructor); when create 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"byte[] KeySerDe.serialise(Key)"})
  void testSerialise_givenArrayListAddLongType_whenCreateNull() throws IOException {
    // Arrange
    ArrayList<PrimitiveType> rowKeyTypes = new ArrayList<>();
    rowKeyTypes.add(new LongType());
    KeySerDe keySerDe = new KeySerDe(rowKeyTypes);

    // Act and Assert
    assertArrayEquals(new byte[]{0, 0, 0, 1, 1}, keySerDe.serialise(Key.create(null)));
  }

  /**
   * Test {@link KeySerDe#serialise(Key)}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link LongType} (default constructor).</li>
   *   <li>When create one.</li>
   * </ul>
   * <p>
   * Method under test: {@link KeySerDe#serialise(Key)}
   */
  @Test
  @DisplayName("Test serialise(Key); given ArrayList() add LongType (default constructor); when create one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"byte[] KeySerDe.serialise(Key)"})
  void testSerialise_givenArrayListAddLongType_whenCreateOne() throws IOException {
    // Arrange
    ArrayList<PrimitiveType> rowKeyTypes = new ArrayList<>();
    rowKeyTypes.add(new LongType());
    KeySerDe keySerDe = new KeySerDe(rowKeyTypes);

    // Act and Assert
    assertArrayEquals(new byte[]{0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1}, keySerDe.serialise(Key.create(1L)));
  }

  /**
   * Test {@link KeySerDe#serialise(Key)}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link PrimitiveType} (default constructor).</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link KeySerDe#serialise(Key)}
   */
  @Test
  @DisplayName("Test serialise(Key); given ArrayList() add PrimitiveType (default constructor); then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"byte[] KeySerDe.serialise(Key)"})
  void testSerialise_givenArrayListAddPrimitiveType_thenThrowIllegalArgumentException() throws IOException {
    // Arrange
    ArrayList<PrimitiveType> rowKeyTypes = new ArrayList<>();
    rowKeyTypes.add(new PrimitiveType());
    KeySerDe keySerDe = new KeySerDe(rowKeyTypes);

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> keySerDe.serialise(Key.create("Obj")));
  }

  /**
   * Test {@link KeySerDe#serialise(Key)}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link StringType} (default constructor).</li>
   *   <li>When create {@code Obj}.</li>
   * </ul>
   * <p>
   * Method under test: {@link KeySerDe#serialise(Key)}
   */
  @Test
  @DisplayName("Test serialise(Key); given ArrayList() add StringType (default constructor); when create 'Obj'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"byte[] KeySerDe.serialise(Key)"})
  void testSerialise_givenArrayListAddStringType_whenCreateObj() throws IOException {
    // Arrange
    ArrayList<PrimitiveType> rowKeyTypes = new ArrayList<>();
    rowKeyTypes.add(new StringType());
    KeySerDe keySerDe = new KeySerDe(rowKeyTypes);

    // Act and Assert
    assertArrayEquals(new byte[]{0, 0, 0, 1, 0, 3, 'O', 'b', 'j'}, keySerDe.serialise(Key.create("Obj")));
  }

  /**
   * Test {@link KeySerDe#serialise(Key)}.
   * <ul>
   *   <li>When create {@code Obj}.</li>
   *   <li>Then return array of {@code byte} with zero and zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link KeySerDe#serialise(Key)}
   */
  @Test
  @DisplayName("Test serialise(Key); when create 'Obj'; then return array of byte with zero and zero")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"byte[] KeySerDe.serialise(Key)"})
  void testSerialise_whenCreateObj_thenReturnArrayOfByteWithZeroAndZero() throws IOException {
    // Arrange
    KeySerDe keySerDe = new KeySerDe(new ArrayList<>());

    // Act and Assert
    assertArrayEquals(new byte[]{0, 0, 0, 0}, keySerDe.serialise(Key.create("Obj")));
  }

  /**
   * Test {@link KeySerDe#serialise(Key)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return array of {@code byte} with zero and zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link KeySerDe#serialise(Key)}
   */
  @Test
  @DisplayName("Test serialise(Key); when 'null'; then return array of byte with zero and zero")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"byte[] KeySerDe.serialise(Key)"})
  void testSerialise_whenNull_thenReturnArrayOfByteWithZeroAndZero() throws IOException {
    // Arrange, Act and Assert
    assertArrayEquals(new byte[]{0, 0, 0, 0}, (new KeySerDe(new ArrayList<>())).serialise(null));
  }

  /**
   * Test {@link KeySerDe#deserialise(byte[])}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link PrimitiveType} (default constructor).</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link KeySerDe#deserialise(byte[])}
   */
  @Test
  @DisplayName("Test deserialise(byte[]); given ArrayList() add PrimitiveType (default constructor); then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Key KeySerDe.deserialise(byte[])"})
  void testDeserialise_givenArrayListAddPrimitiveType_thenThrowIllegalArgumentException() throws IOException {
    // Arrange
    ArrayList<PrimitiveType> rowKeyTypes = new ArrayList<>();
    rowKeyTypes.add(new PrimitiveType());
    KeySerDe keySerDe = new KeySerDe(rowKeyTypes);

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> keySerDe.deserialise("AXAXAXAX".getBytes("UTF-8")));
  }

  /**
   * Test {@link KeySerDe#deserialise(byte[])}.
   * <ul>
   *   <li>When array of {@code byte} with minus one and {@code X}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link KeySerDe#deserialise(byte[])}
   */
  @Test
  @DisplayName("Test deserialise(byte[]); when array of byte with minus one and 'X'; then return 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Key KeySerDe.deserialise(byte[])"})
  void testDeserialise_whenArrayOfByteWithMinusOneAndX_thenReturnNull() throws IOException {
    // Arrange, Act and Assert
    assertNull((new KeySerDe(new ArrayList<>())).deserialise(new byte[]{-1, 'X', 'A', 'X', 'A', 'X', 'A', 'X'}));
  }
}
