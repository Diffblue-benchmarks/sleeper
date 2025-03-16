package sleeper.configuration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class WriteSplitPointsDiffblueTest {
  /**
   * Test {@link WriteSplitPoints#toString(List, boolean)} with {@code List}, {@code boolean}.
   * <ul>
   *   <li>Given {@code 42}.</li>
   *   <li>When {@link ArrayList#ArrayList()} add {@code 42}.</li>
   *   <li>Then return {@code NDI=}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WriteSplitPoints#toString(List, boolean)}
   */
  @Test
  @DisplayName("Test toString(List, boolean) with 'List', 'boolean'; given '42'; when ArrayList() add '42'; then return 'NDI='")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.lang.String WriteSplitPoints.toString(List, boolean)"})
  void testToStringWithListBoolean_given42_whenArrayListAdd42_thenReturnNdi() {
    // Arrange
    ArrayList<Object> splitPoints = new ArrayList<>();
    splitPoints.add("42");

    // Act and Assert
    assertEquals("NDI=\n", WriteSplitPoints.toString(splitPoints, true));
  }

  /**
   * Test {@link WriteSplitPoints#toString(List, boolean)} with {@code List}, {@code boolean}.
   * <ul>
   *   <li>Given {@code 42}.</li>
   *   <li>When {@link ArrayList#ArrayList()} add {@code 42}.</li>
   *   <li>Then return {@code NDI= NDI=}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WriteSplitPoints#toString(List, boolean)}
   */
  @Test
  @DisplayName("Test toString(List, boolean) with 'List', 'boolean'; given '42'; when ArrayList() add '42'; then return 'NDI= NDI='")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.lang.String WriteSplitPoints.toString(List, boolean)"})
  void testToStringWithListBoolean_given42_whenArrayListAdd42_thenReturnNdiNdi() {
    // Arrange
    ArrayList<Object> splitPoints = new ArrayList<>();
    splitPoints.add("42");
    splitPoints.add("42");

    // Act and Assert
    assertEquals("NDI=\nNDI=\n", WriteSplitPoints.toString(splitPoints, true));
  }

  /**
   * Test {@link WriteSplitPoints#toString(List, boolean)} with {@code List}, {@code boolean}.
   * <ul>
   *   <li>Given empty string.</li>
   *   <li>Then return lf.</li>
   * </ul>
   * <p>
   * Method under test: {@link WriteSplitPoints#toString(List, boolean)}
   */
  @Test
  @DisplayName("Test toString(List, boolean) with 'List', 'boolean'; given empty string; then return lf")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.lang.String WriteSplitPoints.toString(List, boolean)"})
  void testToStringWithListBoolean_givenEmptyString_thenReturnLf() {
    // Arrange
    ArrayList<Object> splitPoints = new ArrayList<>();
    splitPoints.add("");

    // Act and Assert
    assertEquals("\n", WriteSplitPoints.toString(splitPoints, true));
  }

  /**
   * Test {@link WriteSplitPoints#toString(List, boolean)} with {@code List}, {@code boolean}.
   * <ul>
   *   <li>Given {@code foo}.</li>
   *   <li>When {@link ArrayList#ArrayList()} add {@code foo}.</li>
   *   <li>Then return {@code Zm9v}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WriteSplitPoints#toString(List, boolean)}
   */
  @Test
  @DisplayName("Test toString(List, boolean) with 'List', 'boolean'; given 'foo'; when ArrayList() add 'foo'; then return 'Zm9v'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.lang.String WriteSplitPoints.toString(List, boolean)"})
  void testToStringWithListBoolean_givenFoo_whenArrayListAddFoo_thenReturnZm9v() {
    // Arrange
    ArrayList<Object> splitPoints = new ArrayList<>();
    splitPoints.add("foo");

    // Act and Assert
    assertEquals("Zm9v\n", WriteSplitPoints.toString(splitPoints, true));
  }

  /**
   * Test {@link WriteSplitPoints#toString(List, boolean)} with {@code List}, {@code boolean}.
   * <ul>
   *   <li>Given lf.</li>
   *   <li>When {@link ArrayList#ArrayList()} add lf.</li>
   *   <li>Then return {@code Cg==}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WriteSplitPoints#toString(List, boolean)}
   */
  @Test
  @DisplayName("Test toString(List, boolean) with 'List', 'boolean'; given lf; when ArrayList() add lf; then return 'Cg=='")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.lang.String WriteSplitPoints.toString(List, boolean)"})
  void testToStringWithListBoolean_givenLf_whenArrayListAddLf_thenReturnCg() {
    // Arrange
    ArrayList<Object> splitPoints = new ArrayList<>();
    splitPoints.add("\n");

    // Act and Assert
    assertEquals("Cg==\n", WriteSplitPoints.toString(splitPoints, true));
  }

  /**
   * Test {@link WriteSplitPoints#toString(List, boolean)} with {@code List}, {@code boolean}.
   * <ul>
   *   <li>Given {@code Split Points}.</li>
   *   <li>Then return {@code Split Points}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WriteSplitPoints#toString(List, boolean)}
   */
  @Test
  @DisplayName("Test toString(List, boolean) with 'List', 'boolean'; given 'Split Points'; then return 'Split Points'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.lang.String WriteSplitPoints.toString(List, boolean)"})
  void testToStringWithListBoolean_givenSplitPoints_thenReturnSplitPoints() {
    // Arrange
    ArrayList<Object> splitPoints = new ArrayList<>();
    splitPoints.add("Split Points");

    // Act and Assert
    assertEquals("Split Points\n", WriteSplitPoints.toString(splitPoints, false));
  }

  /**
   * Test {@link WriteSplitPoints#toString(List, boolean)} with {@code List}, {@code boolean}.
   * <ul>
   *   <li>Given two.</li>
   *   <li>When {@link ArrayList#ArrayList()} add two.</li>
   *   <li>Then return {@code 2}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WriteSplitPoints#toString(List, boolean)}
   */
  @Test
  @DisplayName("Test toString(List, boolean) with 'List', 'boolean'; given two; when ArrayList() add two; then return '2'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.lang.String WriteSplitPoints.toString(List, boolean)"})
  void testToStringWithListBoolean_givenTwo_whenArrayListAddTwo_thenReturn2() {
    // Arrange
    ArrayList<Object> splitPoints = new ArrayList<>();
    splitPoints.add(2);

    // Act and Assert
    assertEquals("2\n", WriteSplitPoints.toString(splitPoints, true));
  }

  /**
   * Test {@link WriteSplitPoints#toString(List, boolean)} with {@code List}, {@code boolean}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link WriteSplitPoints#toString(List, boolean)}
   */
  @Test
  @DisplayName("Test toString(List, boolean) with 'List', 'boolean'; when ArrayList(); then return empty string")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.lang.String WriteSplitPoints.toString(List, boolean)"})
  void testToStringWithListBoolean_whenArrayList_thenReturnEmptyString() {
    // Arrange, Act and Assert
    assertEquals("", WriteSplitPoints.toString(new ArrayList<>(), true));
  }

  /**
   * Test {@link WriteSplitPoints#writeSplitPoints(List, Writer, boolean)}.
   * <ul>
   *   <li>Given {@code 42}.</li>
   *   <li>When {@link ArrayList#ArrayList()} add {@code 42}.</li>
   *   <li>Then {@link StringWriter#StringWriter()} toString is {@code NDI=}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WriteSplitPoints#writeSplitPoints(List, Writer, boolean)}
   */
  @Test
  @DisplayName("Test writeSplitPoints(List, Writer, boolean); given '42'; when ArrayList() add '42'; then StringWriter() toString is 'NDI='")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void WriteSplitPoints.writeSplitPoints(List, Writer, boolean)"})
  void testWriteSplitPoints_given42_whenArrayListAdd42_thenStringWriterToStringIsNdi() throws IOException {
    // Arrange
    ArrayList<Object> splitPoints = new ArrayList<>();
    splitPoints.add("42");
    StringWriter writer = new StringWriter();

    // Act
    WriteSplitPoints.writeSplitPoints(splitPoints, writer, true);

    // Assert
    assertEquals("NDI=\n", writer.toString());
  }

  /**
   * Test {@link WriteSplitPoints#writeSplitPoints(List, Writer, boolean)}.
   * <ul>
   *   <li>Given {@code 42}.</li>
   *   <li>When {@link ArrayList#ArrayList()} add {@code 42}.</li>
   *   <li>Then {@link StringWriter#StringWriter()} toString is {@code NDI= NDI=}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WriteSplitPoints#writeSplitPoints(List, Writer, boolean)}
   */
  @Test
  @DisplayName("Test writeSplitPoints(List, Writer, boolean); given '42'; when ArrayList() add '42'; then StringWriter() toString is 'NDI= NDI='")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void WriteSplitPoints.writeSplitPoints(List, Writer, boolean)"})
  void testWriteSplitPoints_given42_whenArrayListAdd42_thenStringWriterToStringIsNdiNdi() throws IOException {
    // Arrange
    ArrayList<Object> splitPoints = new ArrayList<>();
    splitPoints.add("42");
    splitPoints.add("42");
    StringWriter writer = new StringWriter();

    // Act
    WriteSplitPoints.writeSplitPoints(splitPoints, writer, true);

    // Assert
    assertEquals("NDI=\nNDI=\n", writer.toString());
  }

  /**
   * Test {@link WriteSplitPoints#writeSplitPoints(List, Writer, boolean)}.
   * <ul>
   *   <li>Given empty string.</li>
   *   <li>Then {@link StringWriter#StringWriter()} toString is lf.</li>
   * </ul>
   * <p>
   * Method under test: {@link WriteSplitPoints#writeSplitPoints(List, Writer, boolean)}
   */
  @Test
  @DisplayName("Test writeSplitPoints(List, Writer, boolean); given empty string; then StringWriter() toString is lf")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void WriteSplitPoints.writeSplitPoints(List, Writer, boolean)"})
  void testWriteSplitPoints_givenEmptyString_thenStringWriterToStringIsLf() throws IOException {
    // Arrange
    ArrayList<Object> splitPoints = new ArrayList<>();
    splitPoints.add("");
    StringWriter writer = new StringWriter();

    // Act
    WriteSplitPoints.writeSplitPoints(splitPoints, writer, true);

    // Assert
    assertEquals("\n", writer.toString());
  }

  /**
   * Test {@link WriteSplitPoints#writeSplitPoints(List, Writer, boolean)}.
   * <ul>
   *   <li>Given {@code foo}.</li>
   *   <li>When {@link ArrayList#ArrayList()} add {@code foo}.</li>
   *   <li>Then {@link StringWriter#StringWriter()} toString is {@code Zm9v}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WriteSplitPoints#writeSplitPoints(List, Writer, boolean)}
   */
  @Test
  @DisplayName("Test writeSplitPoints(List, Writer, boolean); given 'foo'; when ArrayList() add 'foo'; then StringWriter() toString is 'Zm9v'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void WriteSplitPoints.writeSplitPoints(List, Writer, boolean)"})
  void testWriteSplitPoints_givenFoo_whenArrayListAddFoo_thenStringWriterToStringIsZm9v() throws IOException {
    // Arrange
    ArrayList<Object> splitPoints = new ArrayList<>();
    splitPoints.add("foo");
    StringWriter writer = new StringWriter();

    // Act
    WriteSplitPoints.writeSplitPoints(splitPoints, writer, true);

    // Assert
    assertEquals("Zm9v\n", writer.toString());
  }

  /**
   * Test {@link WriteSplitPoints#writeSplitPoints(List, Writer, boolean)}.
   * <ul>
   *   <li>Given lf.</li>
   *   <li>When {@link ArrayList#ArrayList()} add lf.</li>
   *   <li>Then {@link StringWriter#StringWriter()} toString is {@code Cg==}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WriteSplitPoints#writeSplitPoints(List, Writer, boolean)}
   */
  @Test
  @DisplayName("Test writeSplitPoints(List, Writer, boolean); given lf; when ArrayList() add lf; then StringWriter() toString is 'Cg=='")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void WriteSplitPoints.writeSplitPoints(List, Writer, boolean)"})
  void testWriteSplitPoints_givenLf_whenArrayListAddLf_thenStringWriterToStringIsCg() throws IOException {
    // Arrange
    ArrayList<Object> splitPoints = new ArrayList<>();
    splitPoints.add("\n");
    StringWriter writer = new StringWriter();

    // Act
    WriteSplitPoints.writeSplitPoints(splitPoints, writer, true);

    // Assert
    assertEquals("Cg==\n", writer.toString());
  }

  /**
   * Test {@link WriteSplitPoints#writeSplitPoints(List, Writer, boolean)}.
   * <ul>
   *   <li>Given {@code Split Points}.</li>
   *   <li>Then {@link StringWriter#StringWriter()} toString is {@code Split Points}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WriteSplitPoints#writeSplitPoints(List, Writer, boolean)}
   */
  @Test
  @DisplayName("Test writeSplitPoints(List, Writer, boolean); given 'Split Points'; then StringWriter() toString is 'Split Points'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void WriteSplitPoints.writeSplitPoints(List, Writer, boolean)"})
  void testWriteSplitPoints_givenSplitPoints_thenStringWriterToStringIsSplitPoints() throws IOException {
    // Arrange
    ArrayList<Object> splitPoints = new ArrayList<>();
    splitPoints.add("Split Points");
    StringWriter writer = new StringWriter();

    // Act
    WriteSplitPoints.writeSplitPoints(splitPoints, writer, false);

    // Assert
    assertEquals("Split Points\n", writer.toString());
  }

  /**
   * Test {@link WriteSplitPoints#writeSplitPoints(List, Writer, boolean)}.
   * <ul>
   *   <li>Given two.</li>
   *   <li>When {@link ArrayList#ArrayList()} add two.</li>
   *   <li>Then {@link StringWriter#StringWriter()} toString is {@code 2}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WriteSplitPoints#writeSplitPoints(List, Writer, boolean)}
   */
  @Test
  @DisplayName("Test writeSplitPoints(List, Writer, boolean); given two; when ArrayList() add two; then StringWriter() toString is '2'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void WriteSplitPoints.writeSplitPoints(List, Writer, boolean)"})
  void testWriteSplitPoints_givenTwo_whenArrayListAddTwo_thenStringWriterToStringIs2() throws IOException {
    // Arrange
    ArrayList<Object> splitPoints = new ArrayList<>();
    splitPoints.add(2);
    StringWriter writer = new StringWriter();

    // Act
    WriteSplitPoints.writeSplitPoints(splitPoints, writer, true);

    // Assert
    assertEquals("2\n", writer.toString());
  }

  /**
   * Test {@link WriteSplitPoints#writeSplitPoints(List, Writer, boolean)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then {@link StringWriter#StringWriter()} toString is empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link WriteSplitPoints#writeSplitPoints(List, Writer, boolean)}
   */
  @Test
  @DisplayName("Test writeSplitPoints(List, Writer, boolean); when ArrayList(); then StringWriter() toString is empty string")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void WriteSplitPoints.writeSplitPoints(List, Writer, boolean)"})
  void testWriteSplitPoints_whenArrayList_thenStringWriterToStringIsEmptyString() throws IOException {
    // Arrange
    ArrayList<Object> splitPoints = new ArrayList<>();
    StringWriter writer = new StringWriter();

    // Act
    WriteSplitPoints.writeSplitPoints(splitPoints, writer, true);

    // Assert that nothing has changed
    assertEquals("", writer.toString());
  }
}
