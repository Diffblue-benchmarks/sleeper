package sleeper.example.iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.iterator.CloseableIterator;
import sleeper.core.iterator.ConcatenatingIterator;
import sleeper.core.iterator.WrappedIterator;
import sleeper.core.record.Record;
import sleeper.core.schema.Schema;
import sleeper.example.iterator.SecurityFilteringIterator.SecurityFilteringIteratorInternal;

class SecurityFilteringIteratorDiffblueTest {
  /**
   * Test new {@link SecurityFilteringIterator} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link SecurityFilteringIterator}
   */
  @Test
  @DisplayName("Test new SecurityFilteringIterator (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SecurityFilteringIterator.<init>()"})
  void testNewSecurityFilteringIterator() {
    // Arrange, Act and Assert
    List<String> requiredValueFields = (new SecurityFilteringIterator()).getRequiredValueFields();
    assertEquals(1, requiredValueFields.size());
    assertNull(requiredValueFields.get(0));
  }

  /**
   * Test {@link SecurityFilteringIterator#init(String, Schema)}.
   * <ul>
   *   <li>Then {@link SecurityFilteringIterator} (default constructor) RequiredValueFields size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link SecurityFilteringIterator#init(String, Schema)}
   */
  @Test
  @DisplayName("Test init(String, Schema); then SecurityFilteringIterator (default constructor) RequiredValueFields size is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SecurityFilteringIterator.init(String, Schema)"})
  void testInit_thenSecurityFilteringIteratorRequiredValueFieldsSizeIsOne() {
    // Arrange
    SecurityFilteringIterator securityFilteringIterator = new SecurityFilteringIterator();

    // Act
    securityFilteringIterator.init("Config String", mock(Schema.class));

    // Assert
    List<String> requiredValueFields = securityFilteringIterator.getRequiredValueFields();
    assertEquals(1, requiredValueFields.size());
    assertEquals("Config String", requiredValueFields.get(0));
  }

  /**
   * Test {@link SecurityFilteringIterator#init(String, Schema)}.
   * <ul>
   *   <li>When {@code ,}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SecurityFilteringIterator#init(String, Schema)}
   */
  @Test
  @DisplayName("Test init(String, Schema); when ','; then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SecurityFilteringIterator.init(String, Schema)"})
  void testInit_whenComma_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> (new SecurityFilteringIterator()).init(",", mock(Schema.class)));
  }

  /**
   * Test {@link SecurityFilteringIterator#getRequiredValueFields()}.
   * <p>
   * Method under test: {@link SecurityFilteringIterator#getRequiredValueFields()}
   */
  @Test
  @DisplayName("Test getRequiredValueFields()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List SecurityFilteringIterator.getRequiredValueFields()"})
  void testGetRequiredValueFields() {
    // Arrange and Act
    List<String> actualRequiredValueFields = (new SecurityFilteringIterator()).getRequiredValueFields();

    // Assert
    assertEquals(1, actualRequiredValueFields.size());
    assertNull(actualRequiredValueFields.get(0));
  }

  /**
   * Test {@link SecurityFilteringIterator#apply(CloseableIterator)} with {@code CloseableIterator}.
   * <p>
   * Method under test: {@link SecurityFilteringIterator#apply(CloseableIterator)}
   */
  @Test
  @DisplayName("Test apply(CloseableIterator) with 'CloseableIterator'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"CloseableIterator SecurityFilteringIterator.apply(CloseableIterator)"})
  void testApplyWithCloseableIterator() {
    // Arrange
    SecurityFilteringIterator securityFilteringIterator = new SecurityFilteringIterator();
    ConcatenatingIterator input = new ConcatenatingIterator(new ArrayList<>());

    // Act
    CloseableIterator<Record> actualApplyResult = securityFilteringIterator.apply(input);

    // Assert
    assertTrue(actualApplyResult instanceof SecurityFilteringIteratorInternal);
    assertFalse(actualApplyResult.hasNext());
    assertFalse(input.hasNext());
  }

  /**
   * Test {@link SecurityFilteringIterator#apply(CloseableIterator)} with {@code CloseableIterator}.
   * <p>
   * Method under test: {@link SecurityFilteringIterator#apply(CloseableIterator)}
   */
  @Test
  @DisplayName("Test apply(CloseableIterator) with 'CloseableIterator'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"CloseableIterator SecurityFilteringIterator.apply(CloseableIterator)"})
  void testApplyWithCloseableIterator2() {
    // Arrange
    SecurityFilteringIterator securityFilteringIterator = new SecurityFilteringIterator();
    ConcatenatingIterator iterator = new ConcatenatingIterator(new ArrayList<>());
    SecurityFilteringIteratorInternal input = new SecurityFilteringIteratorInternal(iterator, "Field Name",
        new HashSet<>());

    // Act
    CloseableIterator<Record> actualApplyResult = securityFilteringIterator.apply(input);

    // Assert
    assertTrue(actualApplyResult instanceof SecurityFilteringIteratorInternal);
    assertFalse(actualApplyResult.hasNext());
    assertFalse(input.hasNext());
  }

  /**
   * Test SecurityFilteringIteratorInternal {@link SecurityFilteringIteratorInternal#hasNext()}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SecurityFilteringIteratorInternal#hasNext()}
   */
  @Test
  @DisplayName("Test SecurityFilteringIteratorInternal hasNext(); then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SecurityFilteringIteratorInternal.hasNext()"})
  void testSecurityFilteringIteratorInternalHasNext_thenReturnFalse() {
    // Arrange
    ConcatenatingIterator iterator = new ConcatenatingIterator(new ArrayList<>());

    // Act and Assert
    assertFalse((new SecurityFilteringIteratorInternal(iterator, "Field Name", new HashSet<>())).hasNext());
  }

  /**
   * Test SecurityFilteringIteratorInternal {@link SecurityFilteringIteratorInternal#hasNext()}.
   * <ul>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SecurityFilteringIteratorInternal#hasNext()}
   */
  @Test
  @DisplayName("Test SecurityFilteringIteratorInternal hasNext(); then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SecurityFilteringIteratorInternal.hasNext()"})
  void testSecurityFilteringIteratorInternalHasNext_thenReturnTrue() {
    // Arrange
    ArrayList<Record> resultRecordList = new ArrayList<>();
    resultRecordList.add(new Record());
    WrappedIterator<Record> iterator = new WrappedIterator<>(resultRecordList.iterator());

    // Act and Assert
    assertTrue((new SecurityFilteringIteratorInternal(iterator, "Field Name", new HashSet<>())).hasNext());
  }

  /**
   * Test SecurityFilteringIteratorInternal {@link SecurityFilteringIteratorInternal#SecurityFilteringIteratorInternal(CloseableIterator, String, Set)}.
   * <p>
   * Method under test: {@link SecurityFilteringIteratorInternal#SecurityFilteringIteratorInternal(CloseableIterator, String, Set)}
   */
  @Test
  @DisplayName("Test SecurityFilteringIteratorInternal new SecurityFilteringIteratorInternal(CloseableIterator, String, Set)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SecurityFilteringIteratorInternal.<init>(CloseableIterator, String, Set)"})
  void testSecurityFilteringIteratorInternalNewSecurityFilteringIteratorInternal() {
    // Arrange
    ConcatenatingIterator iterator = new ConcatenatingIterator(new ArrayList<>());

    // Act and Assert
    assertFalse((new SecurityFilteringIteratorInternal(iterator, "Field Name", new HashSet<>())).hasNext());
  }

  /**
   * Test SecurityFilteringIteratorInternal {@link SecurityFilteringIteratorInternal#SecurityFilteringIteratorInternal(CloseableIterator, String, Set)}.
   * <p>
   * Method under test: {@link SecurityFilteringIteratorInternal#SecurityFilteringIteratorInternal(CloseableIterator, String, Set)}
   */
  @Test
  @DisplayName("Test SecurityFilteringIteratorInternal new SecurityFilteringIteratorInternal(CloseableIterator, String, Set)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SecurityFilteringIteratorInternal.<init>(CloseableIterator, String, Set)"})
  void testSecurityFilteringIteratorInternalNewSecurityFilteringIteratorInternal2() {
    // Arrange
    ConcatenatingIterator iterator = new ConcatenatingIterator(new ArrayList<>());
    SecurityFilteringIteratorInternal iterator2 = new SecurityFilteringIteratorInternal(iterator, "Field Name",
        new HashSet<>());

    // Act and Assert
    assertFalse((new SecurityFilteringIteratorInternal(iterator2, "Field Name", new HashSet<>())).hasNext());
  }

  /**
   * Test SecurityFilteringIteratorInternal {@link SecurityFilteringIteratorInternal#SecurityFilteringIteratorInternal(CloseableIterator, String, Set)}.
   * <p>
   * Method under test: {@link SecurityFilteringIteratorInternal#SecurityFilteringIteratorInternal(CloseableIterator, String, Set)}
   */
  @Test
  @DisplayName("Test SecurityFilteringIteratorInternal new SecurityFilteringIteratorInternal(CloseableIterator, String, Set)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SecurityFilteringIteratorInternal.<init>(CloseableIterator, String, Set)"})
  void testSecurityFilteringIteratorInternalNewSecurityFilteringIteratorInternal3() {
    // Arrange
    ConcatenatingIterator iterator = new ConcatenatingIterator(new ArrayList<>());

    HashSet<String> auths = new HashSet<>();
    auths.add("foo");

    // Act and Assert
    assertFalse((new SecurityFilteringIteratorInternal(iterator, "Field Name", auths)).hasNext());
  }

  /**
   * Test SecurityFilteringIteratorInternal {@link SecurityFilteringIteratorInternal#SecurityFilteringIteratorInternal(CloseableIterator, String, Set)}.
   * <p>
   * Method under test: {@link SecurityFilteringIteratorInternal#SecurityFilteringIteratorInternal(CloseableIterator, String, Set)}
   */
  @Test
  @DisplayName("Test SecurityFilteringIteratorInternal new SecurityFilteringIteratorInternal(CloseableIterator, String, Set)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SecurityFilteringIteratorInternal.<init>(CloseableIterator, String, Set)"})
  void testSecurityFilteringIteratorInternalNewSecurityFilteringIteratorInternal4() {
    // Arrange
    ConcatenatingIterator iterator = new ConcatenatingIterator(new ArrayList<>());

    HashSet<String> auths = new HashSet<>();
    auths.add("42");
    auths.add("foo");

    // Act and Assert
    assertFalse((new SecurityFilteringIteratorInternal(iterator, "Field Name", auths)).hasNext());
  }

  /**
   * Test SecurityFilteringIteratorInternal {@link SecurityFilteringIteratorInternal#SecurityFilteringIteratorInternal(CloseableIterator, String, Set)}.
   * <p>
   * Method under test: {@link SecurityFilteringIteratorInternal#SecurityFilteringIteratorInternal(CloseableIterator, String, Set)}
   */
  @Test
  @DisplayName("Test SecurityFilteringIteratorInternal new SecurityFilteringIteratorInternal(CloseableIterator, String, Set)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SecurityFilteringIteratorInternal.<init>(CloseableIterator, String, Set)"})
  void testSecurityFilteringIteratorInternalNewSecurityFilteringIteratorInternal5() {
    // Arrange
    ArrayList<Record> resultRecordList = new ArrayList<>();
    Record resultRecord = new Record();
    resultRecordList.add(resultRecord);
    WrappedIterator<Record> iterator = new WrappedIterator<>(resultRecordList.iterator());

    // Act
    SecurityFilteringIteratorInternal actualSecurityFilteringIteratorInternal = new SecurityFilteringIteratorInternal(
        iterator, "Field Name", new HashSet<>());

    // Assert
    Record nextResult = actualSecurityFilteringIteratorInternal.next();
    assertFalse(actualSecurityFilteringIteratorInternal.hasNext());
    assertTrue(nextResult.getKeys().isEmpty());
    assertSame(resultRecord, nextResult);
  }

  /**
   * Test SecurityFilteringIteratorInternal {@link SecurityFilteringIteratorInternal#SecurityFilteringIteratorInternal(CloseableIterator, String, Set)}.
   * <p>
   * Method under test: {@link SecurityFilteringIteratorInternal#SecurityFilteringIteratorInternal(CloseableIterator, String, Set)}
   */
  @Test
  @DisplayName("Test SecurityFilteringIteratorInternal new SecurityFilteringIteratorInternal(CloseableIterator, String, Set)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SecurityFilteringIteratorInternal.<init>(CloseableIterator, String, Set)"})
  void testSecurityFilteringIteratorInternalNewSecurityFilteringIteratorInternal6() {
    // Arrange
    Record resultRecord = new Record();
    resultRecord.put("Field Name", "Value");

    ArrayList<Record> resultRecordList = new ArrayList<>();
    resultRecordList.add(resultRecord);
    WrappedIterator<Record> iterator = new WrappedIterator<>(resultRecordList.iterator());

    // Act and Assert
    assertFalse((new SecurityFilteringIteratorInternal(iterator, "Field Name", new HashSet<>())).hasNext());
  }

  /**
   * Test SecurityFilteringIteratorInternal {@link SecurityFilteringIteratorInternal#SecurityFilteringIteratorInternal(CloseableIterator, String, Set)}.
   * <p>
   * Method under test: {@link SecurityFilteringIteratorInternal#SecurityFilteringIteratorInternal(CloseableIterator, String, Set)}
   */
  @Test
  @DisplayName("Test SecurityFilteringIteratorInternal new SecurityFilteringIteratorInternal(CloseableIterator, String, Set)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SecurityFilteringIteratorInternal.<init>(CloseableIterator, String, Set)"})
  void testSecurityFilteringIteratorInternalNewSecurityFilteringIteratorInternal7() {
    // Arrange
    Record resultRecord = new Record();
    resultRecord.put("Field Name", "");

    ArrayList<Record> resultRecordList = new ArrayList<>();
    resultRecordList.add(resultRecord);
    WrappedIterator<Record> iterator = new WrappedIterator<>(resultRecordList.iterator());

    // Act
    SecurityFilteringIteratorInternal actualSecurityFilteringIteratorInternal = new SecurityFilteringIteratorInternal(
        iterator, "Field Name", new HashSet<>());

    // Assert
    Record nextResult = actualSecurityFilteringIteratorInternal.next();
    Set<String> keys = nextResult.getKeys();
    assertEquals(1, keys.size());
    assertFalse(actualSecurityFilteringIteratorInternal.hasNext());
    assertTrue(keys.contains("Field Name"));
    assertSame(resultRecord, nextResult);
  }

  /**
   * Test SecurityFilteringIteratorInternal {@link SecurityFilteringIteratorInternal#next()}.
   * <p>
   * Method under test: {@link SecurityFilteringIteratorInternal#next()}
   */
  @Test
  @DisplayName("Test SecurityFilteringIteratorInternal next()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Record SecurityFilteringIteratorInternal.next()"})
  void testSecurityFilteringIteratorInternalNext() {
    // Arrange
    ConcatenatingIterator iterator = new ConcatenatingIterator(new ArrayList<>());

    // Act and Assert
    assertNull((new SecurityFilteringIteratorInternal(iterator, "Field Name", new HashSet<>())).next());
  }

  /**
   * Test SecurityFilteringIteratorInternal {@link SecurityFilteringIteratorInternal#next()}.
   * <p>
   * Method under test: {@link SecurityFilteringIteratorInternal#next()}
   */
  @Test
  @DisplayName("Test SecurityFilteringIteratorInternal next()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Record SecurityFilteringIteratorInternal.next()"})
  void testSecurityFilteringIteratorInternalNext2() {
    // Arrange
    ConcatenatingIterator iterator = new ConcatenatingIterator(new ArrayList<>());
    SecurityFilteringIteratorInternal iterator2 = new SecurityFilteringIteratorInternal(iterator, "Field Name",
        new HashSet<>());

    // Act and Assert
    assertNull((new SecurityFilteringIteratorInternal(iterator2, "Field Name", new HashSet<>())).next());
  }
}
