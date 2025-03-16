package sleeper.garbagecollector;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.table.TableStatus;
import sleeper.garbagecollector.FailedGarbageCollectionException.FileFailure;
import sleeper.garbagecollector.FailedGarbageCollectionException.StateStoreUpdateFailure;
import sleeper.garbagecollector.FailedGarbageCollectionException.TableFailures;

class FailedGarbageCollectionExceptionDiffblueTest {
  /**
   * Test FileFailure getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link FileFailure#FileFailure(String, Exception)}
   *   <li>{@link FileFailure#getCause()}
   *   <li>{@link FileFailure#getFilename()}
   * </ul>
   */
  @Test
  @DisplayName("Test FileFailure getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void FileFailure.<init>(String, Exception)", "Exception FileFailure.getCause()",
      "String FileFailure.getFilename()"})
  void testFileFailureGettersAndSetters() {
    // Arrange
    Exception cause = new Exception("foo");

    // Act
    FileFailure actualFileFailure = new FileFailure("foo.txt", cause);
    Exception actualCause = actualFileFailure.getCause();

    // Assert
    assertEquals("foo.txt", actualFileFailure.getFilename());
    assertSame(cause, actualCause);
  }

  /**
   * Test {@link FailedGarbageCollectionException#FailedGarbageCollectionException(List)}.
   * <p>
   * Method under test: {@link FailedGarbageCollectionException#FailedGarbageCollectionException(List)}
   */
  @Test
  @DisplayName("Test new FailedGarbageCollectionException(List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void FailedGarbageCollectionException.<init>(List)"})
  void testNewFailedGarbageCollectionException() {
    // Arrange and Act
    FailedGarbageCollectionException actualFailedGarbageCollectionException = new FailedGarbageCollectionException(
        new ArrayList<>());

    // Assert
    assertEquals("Found garbage collection failures for tables: []",
        actualFailedGarbageCollectionException.getLocalizedMessage());
    assertEquals("Found garbage collection failures for tables: []",
        actualFailedGarbageCollectionException.getMessage());
    assertNull(actualFailedGarbageCollectionException.getCause());
    assertTrue(actualFailedGarbageCollectionException.getTableFailures().isEmpty());
  }

  /**
   * Test {@link FailedGarbageCollectionException#FailedGarbageCollectionException(List)}.
   * <p>
   * Method under test: {@link FailedGarbageCollectionException#FailedGarbageCollectionException(List)}
   */
  @Test
  @DisplayName("Test new FailedGarbageCollectionException(List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void FailedGarbageCollectionException.<init>(List)"})
  void testNewFailedGarbageCollectionException2() {
    // Arrange
    ArrayList<TableFailures> tableFailures = new ArrayList<>();
    TableStatus table = TableStatus.uniqueIdAndName("42", "Table Name", true);
    Exception tableFailure = new Exception("foo");
    ArrayList<FileFailure> fileFailures = new ArrayList<>();
    TableFailures tableFailures2 = new TableFailures(table, tableFailure, fileFailures, new ArrayList<>());

    tableFailures.add(tableFailures2);

    // Act
    FailedGarbageCollectionException actualFailedGarbageCollectionException = new FailedGarbageCollectionException(
        tableFailures);

    // Assert
    assertEquals("Found garbage collection failures for tables: [Table Name (42)]",
        actualFailedGarbageCollectionException.getLocalizedMessage());
    assertEquals("Found garbage collection failures for tables: [Table Name (42)]",
        actualFailedGarbageCollectionException.getMessage());
    List<TableFailures> tableFailures3 = actualFailedGarbageCollectionException.getTableFailures();
    assertEquals(1, tableFailures3.size());
    assertSame(tableFailures2, tableFailures3.get(0));
  }

  /**
   * Test {@link FailedGarbageCollectionException#FailedGarbageCollectionException(List)}.
   * <p>
   * Method under test: {@link FailedGarbageCollectionException#FailedGarbageCollectionException(List)}
   */
  @Test
  @DisplayName("Test new FailedGarbageCollectionException(List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void FailedGarbageCollectionException.<init>(List)"})
  void testNewFailedGarbageCollectionException3() {
    // Arrange
    ArrayList<TableFailures> tableFailures = new ArrayList<>();
    TableStatus table = TableStatus.uniqueIdAndName("42", "Table Name", true);
    Exception tableFailure = new Exception("foo");
    ArrayList<FileFailure> fileFailures = new ArrayList<>();
    tableFailures.add(new TableFailures(table, tableFailure, fileFailures, new ArrayList<>()));
    TableStatus table2 = TableStatus.uniqueIdAndName("42", "Table Name", true);
    Exception tableFailure2 = new Exception("foo");
    ArrayList<FileFailure> fileFailures2 = new ArrayList<>();
    tableFailures.add(new TableFailures(table2, tableFailure2, fileFailures2, new ArrayList<>()));

    // Act and Assert
    List<TableFailures> tableFailures2 = (new FailedGarbageCollectionException(tableFailures)).getTableFailures();
    assertEquals(2, tableFailures2.size());
    Exception tableFailure3 = tableFailures2.get(1).getTableFailure();
    assertEquals("foo", tableFailure3.getLocalizedMessage());
    assertEquals("foo", tableFailure3.getMessage());
    assertNull(tableFailure3.getCause());
  }

  /**
   * Test {@link FailedGarbageCollectionException#FailedGarbageCollectionException(List)}.
   * <p>
   * Method under test: {@link FailedGarbageCollectionException#FailedGarbageCollectionException(List)}
   */
  @Test
  @DisplayName("Test new FailedGarbageCollectionException(List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void FailedGarbageCollectionException.<init>(List)"})
  void testNewFailedGarbageCollectionException4() {
    // Arrange
    ArrayList<TableFailures> tableFailures = new ArrayList<>();
    TableStatus table = TableStatus.uniqueIdAndName("42", "Table Name", true);
    ArrayList<FileFailure> fileFailures = new ArrayList<>();
    TableFailures tableFailures2 = new TableFailures(table, null, fileFailures, new ArrayList<>());

    tableFailures.add(tableFailures2);
    TableStatus table2 = TableStatus.uniqueIdAndName("42", "Table Name", true);
    Exception tableFailure = new Exception("foo");
    ArrayList<FileFailure> fileFailures2 = new ArrayList<>();
    TableFailures tableFailures3 = new TableFailures(table2, tableFailure, fileFailures2, new ArrayList<>());

    tableFailures.add(tableFailures3);

    // Act
    FailedGarbageCollectionException actualFailedGarbageCollectionException = new FailedGarbageCollectionException(
        tableFailures);

    // Assert
    assertEquals("Found garbage collection failures for tables: [Table Name (42), Table Name (42)]",
        actualFailedGarbageCollectionException.getLocalizedMessage());
    assertEquals("Found garbage collection failures for tables: [Table Name (42), Table Name (42)]",
        actualFailedGarbageCollectionException.getMessage());
    List<TableFailures> tableFailures4 = actualFailedGarbageCollectionException.getTableFailures();
    assertEquals(2, tableFailures4.size());
    TableFailures getResult = tableFailures4.get(0);
    assertNull(getResult.getTableFailure());
    assertSame(tableFailures2, getResult);
    assertSame(tableFailures3, tableFailures4.get(1));
  }

  /**
   * Test {@link FailedGarbageCollectionException#getTableFailures()}.
   * <p>
   * Method under test: {@link FailedGarbageCollectionException#getTableFailures()}
   */
  @Test
  @DisplayName("Test getTableFailures()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List FailedGarbageCollectionException.getTableFailures()"})
  void testGetTableFailures() {
    // Arrange
    ArrayList<TableFailures> tableFailures = new ArrayList<>();

    // Act
    List<TableFailures> actualTableFailures = (new FailedGarbageCollectionException(tableFailures)).getTableFailures();

    // Assert
    assertTrue(actualTableFailures.isEmpty());
    assertSame(tableFailures, actualTableFailures);
  }

  /**
   * Test StateStoreUpdateFailure getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link StateStoreUpdateFailure#StateStoreUpdateFailure(List, Exception)}
   *   <li>{@link StateStoreUpdateFailure#getCause()}
   *   <li>{@link StateStoreUpdateFailure#getFilenames()}
   * </ul>
   */
  @Test
  @DisplayName("Test StateStoreUpdateFailure getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void StateStoreUpdateFailure.<init>(List, Exception)",
      "Exception StateStoreUpdateFailure.getCause()", "List StateStoreUpdateFailure.getFilenames()"})
  void testStateStoreUpdateFailureGettersAndSetters() {
    // Arrange
    ArrayList<String> filenames = new ArrayList<>();
    Exception cause = new Exception("foo");

    // Act
    StateStoreUpdateFailure actualStateStoreUpdateFailure = new StateStoreUpdateFailure(filenames, cause);
    Exception actualCause = actualStateStoreUpdateFailure.getCause();
    List<String> actualFilenames = actualStateStoreUpdateFailure.getFilenames();

    // Assert
    assertTrue(actualFilenames.isEmpty());
    assertSame(cause, actualCause);
    assertSame(filenames, actualFilenames);
  }

  /**
   * Test TableFailures getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link TableFailures#TableFailures(TableStatus, Exception, List, List)}
   *   <li>{@link TableFailures#getFileFailures()}
   *   <li>{@link TableFailures#getStateStoreUpdateFailures()}
   *   <li>{@link TableFailures#getTable()}
   *   <li>{@link TableFailures#getTableFailure()}
   * </ul>
   */
  @Test
  @DisplayName("Test TableFailures getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void TableFailures.<init>(TableStatus, Exception, List, List)",
      "List TableFailures.getFileFailures()", "List TableFailures.getStateStoreUpdateFailures()",
      "TableStatus TableFailures.getTable()", "Exception TableFailures.getTableFailure()"})
  void testTableFailuresGettersAndSetters() {
    // Arrange
    TableStatus table = TableStatus.uniqueIdAndName("42", "Table Name", true);
    Exception tableFailure = new Exception("foo");
    ArrayList<FileFailure> fileFailures = new ArrayList<>();
    ArrayList<StateStoreUpdateFailure> stateStoreUpdateFailures = new ArrayList<>();

    // Act
    TableFailures actualTableFailures = new TableFailures(table, tableFailure, fileFailures, stateStoreUpdateFailures);
    List<FileFailure> actualFileFailures = actualTableFailures.getFileFailures();
    List<StateStoreUpdateFailure> actualStateStoreUpdateFailures = actualTableFailures.getStateStoreUpdateFailures();
    TableStatus actualTable = actualTableFailures.getTable();
    Exception actualTableFailure = actualTableFailures.getTableFailure();

    // Assert
    assertTrue(actualFileFailures.isEmpty());
    assertTrue(actualStateStoreUpdateFailures.isEmpty());
    assertSame(tableFailure, actualTableFailure);
    assertSame(fileFailures, actualFileFailures);
    assertSame(stateStoreUpdateFailures, actualStateStoreUpdateFailures);
    assertSame(table, actualTable);
  }

  /**
   * Test TableFailures {@link TableFailures#streamFailures()}.
   * <p>
   * Method under test: {@link TableFailures#streamFailures()}
   */
  @Test
  @DisplayName("Test TableFailures streamFailures()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream TableFailures.streamFailures()"})
  void testTableFailuresStreamFailures() {
    // Arrange
    TableStatus table = TableStatus.uniqueIdAndName("42", "Table Name", true);
    Exception tableFailure = new Exception("foo");
    ArrayList<FileFailure> fileFailures = new ArrayList<>();

    // Act
    Stream<Exception> actualStreamFailuresResult = (new TableFailures(table, tableFailure, fileFailures,
        new ArrayList<>())).streamFailures();

    // Assert
    List<Exception> collectResult = actualStreamFailuresResult.limit(5).collect(Collectors.toList());
    assertEquals(1, collectResult.size());
    assertSame(tableFailure, collectResult.get(0));
  }
}
