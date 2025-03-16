package sleeper.example.iterator;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.iterator.CloseableIterator;
import sleeper.core.iterator.ConcatenatingIterator;
import sleeper.core.iterator.WrappedIterator;
import sleeper.core.record.Record;
import sleeper.core.schema.Schema;
import sleeper.example.iterator.AdditionIterator.AdditionIteratorInternal;

class AdditionIteratorDiffblueTest {
  /**
   * Test AdditionIteratorInternal {@link AdditionIteratorInternal#AdditionIteratorInternal(CloseableIterator, List, List, List)}.
   * <p>
   * Method under test: {@link AdditionIteratorInternal#AdditionIteratorInternal(CloseableIterator, List, List, List)}
   */
  @Test
  @DisplayName("Test AdditionIteratorInternal new AdditionIteratorInternal(CloseableIterator, List, List, List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AdditionIteratorInternal.<init>(CloseableIterator, List, List, List)"})
  void testAdditionIteratorInternalNewAdditionIteratorInternal() {
    // Arrange
    ConcatenatingIterator input = new ConcatenatingIterator(new ArrayList<>());
    ArrayList<String> rowKeyFieldNames = new ArrayList<>();
    ArrayList<String> sortKeyFieldNames = new ArrayList<>();
    AdditionIteratorInternal input2 = new AdditionIteratorInternal(input, rowKeyFieldNames, sortKeyFieldNames,
        new ArrayList<>());

    ArrayList<String> rowKeyFieldNames2 = new ArrayList<>();
    ArrayList<String> sortKeyFieldNames2 = new ArrayList<>();

    // Act and Assert
    assertFalse(
        (new AdditionIteratorInternal(input2, rowKeyFieldNames2, sortKeyFieldNames2, new ArrayList<>())).hasNext());
  }

  /**
   * Test AdditionIteratorInternal {@link AdditionIteratorInternal#AdditionIteratorInternal(CloseableIterator, List, List, List)}.
   * <p>
   * Method under test: {@link AdditionIteratorInternal#AdditionIteratorInternal(CloseableIterator, List, List, List)}
   */
  @Test
  @DisplayName("Test AdditionIteratorInternal new AdditionIteratorInternal(CloseableIterator, List, List, List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AdditionIteratorInternal.<init>(CloseableIterator, List, List, List)"})
  void testAdditionIteratorInternalNewAdditionIteratorInternal2() {
    // Arrange
    ConcatenatingIterator input = new ConcatenatingIterator(new ArrayList<>());
    ArrayList<String> rowKeyFieldNames = new ArrayList<>();
    ArrayList<String> sortKeyFieldNames = new ArrayList<>();
    AdditionIteratorInternal input2 = new AdditionIteratorInternal(input, rowKeyFieldNames, sortKeyFieldNames,
        new ArrayList<>());

    ArrayList<String> rowKeyFieldNames2 = new ArrayList<>();
    ArrayList<String> sortKeyFieldNames2 = new ArrayList<>();

    // Act and Assert
    assertFalse(
        (new AdditionIteratorInternal(input2, rowKeyFieldNames2, sortKeyFieldNames2, new ArrayList<>())).hasNext());
  }

  /**
   * Test AdditionIteratorInternal {@link AdditionIteratorInternal#AdditionIteratorInternal(CloseableIterator, List, List, List)}.
   * <ul>
   *   <li>Given {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AdditionIteratorInternal#AdditionIteratorInternal(CloseableIterator, List, List, List)}
   */
  @Test
  @DisplayName("Test AdditionIteratorInternal new AdditionIteratorInternal(CloseableIterator, List, List, List); given '42'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AdditionIteratorInternal.<init>(CloseableIterator, List, List, List)"})
  void testAdditionIteratorInternalNewAdditionIteratorInternal_given42() {
    // Arrange
    ConcatenatingIterator input = new ConcatenatingIterator(new ArrayList<>());

    ArrayList<String> rowKeyFieldNames = new ArrayList<>();
    rowKeyFieldNames.add("42");
    rowKeyFieldNames.add("foo");
    ArrayList<String> sortKeyFieldNames = new ArrayList<>();

    // Act and Assert
    assertFalse(
        (new AdditionIteratorInternal(input, rowKeyFieldNames, sortKeyFieldNames, new ArrayList<>())).hasNext());
  }

  /**
   * Test AdditionIteratorInternal {@link AdditionIteratorInternal#AdditionIteratorInternal(CloseableIterator, List, List, List)}.
   * <ul>
   *   <li>Given {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AdditionIteratorInternal#AdditionIteratorInternal(CloseableIterator, List, List, List)}
   */
  @Test
  @DisplayName("Test AdditionIteratorInternal new AdditionIteratorInternal(CloseableIterator, List, List, List); given '42'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AdditionIteratorInternal.<init>(CloseableIterator, List, List, List)"})
  void testAdditionIteratorInternalNewAdditionIteratorInternal_given422() {
    // Arrange
    ConcatenatingIterator input = new ConcatenatingIterator(new ArrayList<>());
    ArrayList<String> rowKeyFieldNames = new ArrayList<>();

    ArrayList<String> sortKeyFieldNames = new ArrayList<>();
    sortKeyFieldNames.add("42");
    sortKeyFieldNames.add("foo");

    // Act and Assert
    assertFalse(
        (new AdditionIteratorInternal(input, rowKeyFieldNames, sortKeyFieldNames, new ArrayList<>())).hasNext());
  }

  /**
   * Test AdditionIteratorInternal {@link AdditionIteratorInternal#AdditionIteratorInternal(CloseableIterator, List, List, List)}.
   * <ul>
   *   <li>Given {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AdditionIteratorInternal#AdditionIteratorInternal(CloseableIterator, List, List, List)}
   */
  @Test
  @DisplayName("Test AdditionIteratorInternal new AdditionIteratorInternal(CloseableIterator, List, List, List); given '42'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AdditionIteratorInternal.<init>(CloseableIterator, List, List, List)"})
  void testAdditionIteratorInternalNewAdditionIteratorInternal_given423() {
    // Arrange
    ConcatenatingIterator input = new ConcatenatingIterator(new ArrayList<>());
    ArrayList<String> rowKeyFieldNames = new ArrayList<>();
    ArrayList<String> sortKeyFieldNames = new ArrayList<>();

    ArrayList<String> valueFieldNames = new ArrayList<>();
    valueFieldNames.add("42");
    valueFieldNames.add("foo");

    // Act and Assert
    assertFalse((new AdditionIteratorInternal(input, rowKeyFieldNames, sortKeyFieldNames, valueFieldNames)).hasNext());
  }

  /**
   * Test AdditionIteratorInternal {@link AdditionIteratorInternal#AdditionIteratorInternal(CloseableIterator, List, List, List)}.
   * <ul>
   *   <li>Given {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AdditionIteratorInternal#AdditionIteratorInternal(CloseableIterator, List, List, List)}
   */
  @Test
  @DisplayName("Test AdditionIteratorInternal new AdditionIteratorInternal(CloseableIterator, List, List, List); given '42'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AdditionIteratorInternal.<init>(CloseableIterator, List, List, List)"})
  void testAdditionIteratorInternalNewAdditionIteratorInternal_given424() {
    // Arrange
    ConcatenatingIterator input = new ConcatenatingIterator(new ArrayList<>());

    ArrayList<String> rowKeyFieldNames = new ArrayList<>();
    rowKeyFieldNames.add("42");
    rowKeyFieldNames.add("foo");
    ArrayList<String> sortKeyFieldNames = new ArrayList<>();

    // Act and Assert
    assertFalse(
        (new AdditionIteratorInternal(input, rowKeyFieldNames, sortKeyFieldNames, new ArrayList<>())).hasNext());
  }

  /**
   * Test AdditionIteratorInternal {@link AdditionIteratorInternal#AdditionIteratorInternal(CloseableIterator, List, List, List)}.
   * <ul>
   *   <li>Given {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AdditionIteratorInternal#AdditionIteratorInternal(CloseableIterator, List, List, List)}
   */
  @Test
  @DisplayName("Test AdditionIteratorInternal new AdditionIteratorInternal(CloseableIterator, List, List, List); given '42'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AdditionIteratorInternal.<init>(CloseableIterator, List, List, List)"})
  void testAdditionIteratorInternalNewAdditionIteratorInternal_given425() {
    // Arrange
    ConcatenatingIterator input = new ConcatenatingIterator(new ArrayList<>());
    ArrayList<String> rowKeyFieldNames = new ArrayList<>();

    ArrayList<String> sortKeyFieldNames = new ArrayList<>();
    sortKeyFieldNames.add("42");
    sortKeyFieldNames.add("foo");

    // Act and Assert
    assertFalse(
        (new AdditionIteratorInternal(input, rowKeyFieldNames, sortKeyFieldNames, new ArrayList<>())).hasNext());
  }

  /**
   * Test AdditionIteratorInternal {@link AdditionIteratorInternal#AdditionIteratorInternal(CloseableIterator, List, List, List)}.
   * <ul>
   *   <li>Given {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AdditionIteratorInternal#AdditionIteratorInternal(CloseableIterator, List, List, List)}
   */
  @Test
  @DisplayName("Test AdditionIteratorInternal new AdditionIteratorInternal(CloseableIterator, List, List, List); given '42'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AdditionIteratorInternal.<init>(CloseableIterator, List, List, List)"})
  void testAdditionIteratorInternalNewAdditionIteratorInternal_given426() {
    // Arrange
    ConcatenatingIterator input = new ConcatenatingIterator(new ArrayList<>());
    ArrayList<String> rowKeyFieldNames = new ArrayList<>();
    ArrayList<String> sortKeyFieldNames = new ArrayList<>();

    ArrayList<String> valueFieldNames = new ArrayList<>();
    valueFieldNames.add("42");
    valueFieldNames.add("foo");

    // Act and Assert
    assertFalse((new AdditionIteratorInternal(input, rowKeyFieldNames, sortKeyFieldNames, valueFieldNames)).hasNext());
  }

  /**
   * Test AdditionIteratorInternal {@link AdditionIteratorInternal#AdditionIteratorInternal(CloseableIterator, List, List, List)}.
   * <ul>
   *   <li>Given {@code foo}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AdditionIteratorInternal#AdditionIteratorInternal(CloseableIterator, List, List, List)}
   */
  @Test
  @DisplayName("Test AdditionIteratorInternal new AdditionIteratorInternal(CloseableIterator, List, List, List); given 'foo'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AdditionIteratorInternal.<init>(CloseableIterator, List, List, List)"})
  void testAdditionIteratorInternalNewAdditionIteratorInternal_givenFoo() {
    // Arrange
    ConcatenatingIterator input = new ConcatenatingIterator(new ArrayList<>());

    ArrayList<String> rowKeyFieldNames = new ArrayList<>();
    rowKeyFieldNames.add("foo");
    ArrayList<String> sortKeyFieldNames = new ArrayList<>();

    // Act and Assert
    assertFalse(
        (new AdditionIteratorInternal(input, rowKeyFieldNames, sortKeyFieldNames, new ArrayList<>())).hasNext());
  }

  /**
   * Test AdditionIteratorInternal {@link AdditionIteratorInternal#AdditionIteratorInternal(CloseableIterator, List, List, List)}.
   * <ul>
   *   <li>Given {@code foo}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AdditionIteratorInternal#AdditionIteratorInternal(CloseableIterator, List, List, List)}
   */
  @Test
  @DisplayName("Test AdditionIteratorInternal new AdditionIteratorInternal(CloseableIterator, List, List, List); given 'foo'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AdditionIteratorInternal.<init>(CloseableIterator, List, List, List)"})
  void testAdditionIteratorInternalNewAdditionIteratorInternal_givenFoo2() {
    // Arrange
    ConcatenatingIterator input = new ConcatenatingIterator(new ArrayList<>());
    ArrayList<String> rowKeyFieldNames = new ArrayList<>();

    ArrayList<String> sortKeyFieldNames = new ArrayList<>();
    sortKeyFieldNames.add("foo");

    // Act and Assert
    assertFalse(
        (new AdditionIteratorInternal(input, rowKeyFieldNames, sortKeyFieldNames, new ArrayList<>())).hasNext());
  }

  /**
   * Test AdditionIteratorInternal {@link AdditionIteratorInternal#AdditionIteratorInternal(CloseableIterator, List, List, List)}.
   * <ul>
   *   <li>Given {@code foo}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AdditionIteratorInternal#AdditionIteratorInternal(CloseableIterator, List, List, List)}
   */
  @Test
  @DisplayName("Test AdditionIteratorInternal new AdditionIteratorInternal(CloseableIterator, List, List, List); given 'foo'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AdditionIteratorInternal.<init>(CloseableIterator, List, List, List)"})
  void testAdditionIteratorInternalNewAdditionIteratorInternal_givenFoo3() {
    // Arrange
    ConcatenatingIterator input = new ConcatenatingIterator(new ArrayList<>());
    ArrayList<String> rowKeyFieldNames = new ArrayList<>();
    ArrayList<String> sortKeyFieldNames = new ArrayList<>();

    ArrayList<String> valueFieldNames = new ArrayList<>();
    valueFieldNames.add("foo");

    // Act and Assert
    assertFalse((new AdditionIteratorInternal(input, rowKeyFieldNames, sortKeyFieldNames, valueFieldNames)).hasNext());
  }

  /**
   * Test AdditionIteratorInternal {@link AdditionIteratorInternal#AdditionIteratorInternal(CloseableIterator, List, List, List)}.
   * <ul>
   *   <li>Given {@code foo}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AdditionIteratorInternal#AdditionIteratorInternal(CloseableIterator, List, List, List)}
   */
  @Test
  @DisplayName("Test AdditionIteratorInternal new AdditionIteratorInternal(CloseableIterator, List, List, List); given 'foo'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AdditionIteratorInternal.<init>(CloseableIterator, List, List, List)"})
  void testAdditionIteratorInternalNewAdditionIteratorInternal_givenFoo4() {
    // Arrange
    ConcatenatingIterator input = new ConcatenatingIterator(new ArrayList<>());

    ArrayList<String> rowKeyFieldNames = new ArrayList<>();
    rowKeyFieldNames.add("foo");
    ArrayList<String> sortKeyFieldNames = new ArrayList<>();

    // Act and Assert
    assertFalse(
        (new AdditionIteratorInternal(input, rowKeyFieldNames, sortKeyFieldNames, new ArrayList<>())).hasNext());
  }

  /**
   * Test AdditionIteratorInternal {@link AdditionIteratorInternal#AdditionIteratorInternal(CloseableIterator, List, List, List)}.
   * <ul>
   *   <li>Given {@code foo}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AdditionIteratorInternal#AdditionIteratorInternal(CloseableIterator, List, List, List)}
   */
  @Test
  @DisplayName("Test AdditionIteratorInternal new AdditionIteratorInternal(CloseableIterator, List, List, List); given 'foo'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AdditionIteratorInternal.<init>(CloseableIterator, List, List, List)"})
  void testAdditionIteratorInternalNewAdditionIteratorInternal_givenFoo5() {
    // Arrange
    ConcatenatingIterator input = new ConcatenatingIterator(new ArrayList<>());
    ArrayList<String> rowKeyFieldNames = new ArrayList<>();

    ArrayList<String> sortKeyFieldNames = new ArrayList<>();
    sortKeyFieldNames.add("foo");

    // Act and Assert
    assertFalse(
        (new AdditionIteratorInternal(input, rowKeyFieldNames, sortKeyFieldNames, new ArrayList<>())).hasNext());
  }

  /**
   * Test AdditionIteratorInternal {@link AdditionIteratorInternal#AdditionIteratorInternal(CloseableIterator, List, List, List)}.
   * <ul>
   *   <li>Given {@code foo}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AdditionIteratorInternal#AdditionIteratorInternal(CloseableIterator, List, List, List)}
   */
  @Test
  @DisplayName("Test AdditionIteratorInternal new AdditionIteratorInternal(CloseableIterator, List, List, List); given 'foo'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AdditionIteratorInternal.<init>(CloseableIterator, List, List, List)"})
  void testAdditionIteratorInternalNewAdditionIteratorInternal_givenFoo6() {
    // Arrange
    ConcatenatingIterator input = new ConcatenatingIterator(new ArrayList<>());
    ArrayList<String> rowKeyFieldNames = new ArrayList<>();
    ArrayList<String> sortKeyFieldNames = new ArrayList<>();

    ArrayList<String> valueFieldNames = new ArrayList<>();
    valueFieldNames.add("foo");

    // Act and Assert
    assertFalse((new AdditionIteratorInternal(input, rowKeyFieldNames, sortKeyFieldNames, valueFieldNames)).hasNext());
  }

  /**
   * Test AdditionIteratorInternal {@link AdditionIteratorInternal#AdditionIteratorInternal(CloseableIterator, List, List, List)}.
   * <ul>
   *   <li>Then return not hasNext.</li>
   * </ul>
   * <p>
   * Method under test: {@link AdditionIteratorInternal#AdditionIteratorInternal(CloseableIterator, List, List, List)}
   */
  @Test
  @DisplayName("Test AdditionIteratorInternal new AdditionIteratorInternal(CloseableIterator, List, List, List); then return not hasNext")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AdditionIteratorInternal.<init>(CloseableIterator, List, List, List)"})
  void testAdditionIteratorInternalNewAdditionIteratorInternal_thenReturnNotHasNext() {
    // Arrange
    ConcatenatingIterator input = new ConcatenatingIterator(new ArrayList<>());
    ArrayList<String> rowKeyFieldNames = new ArrayList<>();
    ArrayList<String> sortKeyFieldNames = new ArrayList<>();

    // Act and Assert
    assertFalse(
        (new AdditionIteratorInternal(input, rowKeyFieldNames, sortKeyFieldNames, new ArrayList<>())).hasNext());
  }

  /**
   * Test AdditionIteratorInternal {@link AdditionIteratorInternal#AdditionIteratorInternal(CloseableIterator, List, List, List)}.
   * <ul>
   *   <li>Then return not hasNext.</li>
   * </ul>
   * <p>
   * Method under test: {@link AdditionIteratorInternal#AdditionIteratorInternal(CloseableIterator, List, List, List)}
   */
  @Test
  @DisplayName("Test AdditionIteratorInternal new AdditionIteratorInternal(CloseableIterator, List, List, List); then return not hasNext")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AdditionIteratorInternal.<init>(CloseableIterator, List, List, List)"})
  void testAdditionIteratorInternalNewAdditionIteratorInternal_thenReturnNotHasNext2() {
    // Arrange
    ConcatenatingIterator input = new ConcatenatingIterator(new ArrayList<>());
    ArrayList<String> rowKeyFieldNames = new ArrayList<>();
    ArrayList<String> sortKeyFieldNames = new ArrayList<>();

    // Act and Assert
    assertFalse(
        (new AdditionIteratorInternal(input, rowKeyFieldNames, sortKeyFieldNames, new ArrayList<>())).hasNext());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>default or parameterless constructor of {@link AdditionIterator}
   *   <li>{@link AdditionIterator#getRequiredValueFields()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AdditionIterator.<init>()", "List AdditionIterator.getRequiredValueFields()"})
  void testGettersAndSetters() {
    // Arrange, Act and Assert
    assertNull((new AdditionIterator()).getRequiredValueFields());
  }

  /**
   * Test {@link AdditionIterator#init(String, Schema)}.
   * <p>
   * Method under test: {@link AdditionIterator#init(String, Schema)}
   */
  @Test
  @DisplayName("Test init(String, Schema)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AdditionIterator.init(String, Schema)"})
  void testInit() {
    // Arrange
    AdditionIterator additionIterator = new AdditionIterator();
    Schema schema = mock(Schema.class);
    when(schema.getRowKeyFieldNames()).thenReturn(new ArrayList<>());
    when(schema.getSortKeyFieldNames()).thenReturn(new ArrayList<>());
    when(schema.getValueFieldNames()).thenReturn(new ArrayList<>());

    // Act
    additionIterator.init("Config String", schema);

    // Assert
    verify(schema).getRowKeyFieldNames();
    verify(schema).getSortKeyFieldNames();
    verify(schema).getValueFieldNames();
    assertTrue(additionIterator.getRequiredValueFields().isEmpty());
  }

  /**
   * Test {@link AdditionIterator#apply(CloseableIterator)} with {@code CloseableIterator}.
   * <p>
   * Method under test: {@link AdditionIterator#apply(CloseableIterator)}
   */
  @Test
  @DisplayName("Test apply(CloseableIterator) with 'CloseableIterator'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"CloseableIterator AdditionIterator.apply(CloseableIterator)"})
  void testApplyWithCloseableIterator() {
    // Arrange
    AdditionIterator additionIterator = new AdditionIterator();
    ConcatenatingIterator input = new ConcatenatingIterator(new ArrayList<>());

    // Act
    CloseableIterator<Record> actualApplyResult = additionIterator.apply(input);

    // Assert
    assertTrue(actualApplyResult instanceof AdditionIteratorInternal);
    assertFalse(actualApplyResult.hasNext());
    assertFalse(input.hasNext());
  }

  /**
   * Test {@link AdditionIterator#apply(CloseableIterator)} with {@code CloseableIterator}.
   * <p>
   * Method under test: {@link AdditionIterator#apply(CloseableIterator)}
   */
  @Test
  @DisplayName("Test apply(CloseableIterator) with 'CloseableIterator'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"CloseableIterator AdditionIterator.apply(CloseableIterator)"})
  void testApplyWithCloseableIterator2() {
    // Arrange
    AdditionIterator additionIterator = new AdditionIterator();
    ConcatenatingIterator input = new ConcatenatingIterator(new ArrayList<>());
    ArrayList<String> rowKeyFieldNames = new ArrayList<>();
    ArrayList<String> sortKeyFieldNames = new ArrayList<>();
    AdditionIteratorInternal input2 = new AdditionIteratorInternal(input, rowKeyFieldNames, sortKeyFieldNames,
        new ArrayList<>());

    // Act
    CloseableIterator<Record> actualApplyResult = additionIterator.apply(input2);

    // Assert
    assertTrue(actualApplyResult instanceof AdditionIteratorInternal);
    assertFalse(actualApplyResult.hasNext());
    assertFalse(input2.hasNext());
  }

  /**
   * Test {@link AdditionIterator#apply(CloseableIterator)} with {@code CloseableIterator}.
   * <ul>
   *   <li>Given {@link Record#Record()} {@code 42} is {@link Record#Record()}.</li>
   *   <li>When {@link ArrayList#ArrayList()} add {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AdditionIterator#apply(CloseableIterator)}
   */
  @Test
  @DisplayName("Test apply(CloseableIterator) with 'CloseableIterator'; given Record() '42' is Record(); when ArrayList() add '42'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"CloseableIterator AdditionIterator.apply(CloseableIterator)"})
  void testApplyWithCloseableIterator_givenRecord42IsRecord_whenArrayListAdd42() {
    // Arrange
    AdditionIterator additionIterator = new AdditionIterator();

    Record resultRecord = new Record();
    resultRecord.put("42", new Record());

    ArrayList<Record> resultRecordList = new ArrayList<>();
    resultRecordList.add(resultRecord);
    resultRecordList.add(new Record());
    WrappedIterator<Record> input = new WrappedIterator<>(resultRecordList.iterator());

    ArrayList<String> rowKeyFieldNames = new ArrayList<>();
    rowKeyFieldNames.add("42");
    ArrayList<String> sortKeyFieldNames = new ArrayList<>();

    // Act and Assert
    assertTrue(additionIterator.apply(new AdditionIteratorInternal(input, rowKeyFieldNames, sortKeyFieldNames,
        new ArrayList<>())) instanceof AdditionIteratorInternal);
  }

  /**
   * Test {@link AdditionIterator#apply(CloseableIterator)} with {@code CloseableIterator}.
   * <ul>
   *   <li>Given {@link Record#Record()} {@code 42} is {@code Value}.</li>
   *   <li>When {@link ArrayList#ArrayList()} add {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AdditionIterator#apply(CloseableIterator)}
   */
  @Test
  @DisplayName("Test apply(CloseableIterator) with 'CloseableIterator'; given Record() '42' is 'Value'; when ArrayList() add '42'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"CloseableIterator AdditionIterator.apply(CloseableIterator)"})
  void testApplyWithCloseableIterator_givenRecord42IsValue_whenArrayListAdd42() {
    // Arrange
    AdditionIterator additionIterator = new AdditionIterator();

    Record resultRecord = new Record();
    resultRecord.put("42", "Value");

    ArrayList<Record> resultRecordList = new ArrayList<>();
    resultRecordList.add(resultRecord);
    resultRecordList.add(new Record());
    WrappedIterator<Record> input = new WrappedIterator<>(resultRecordList.iterator());

    ArrayList<String> rowKeyFieldNames = new ArrayList<>();
    rowKeyFieldNames.add("42");
    ArrayList<String> sortKeyFieldNames = new ArrayList<>();

    // Act and Assert
    assertTrue(additionIterator.apply(new AdditionIteratorInternal(input, rowKeyFieldNames, sortKeyFieldNames,
        new ArrayList<>())) instanceof AdditionIteratorInternal);
  }
}
