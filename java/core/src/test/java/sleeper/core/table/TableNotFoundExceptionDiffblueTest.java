package sleeper.core.table;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class TableNotFoundExceptionDiffblueTest {
  /**
   * Test {@link TableNotFoundException#withTableId(String)} with {@code tableId}.
   * <p>
   * Method under test: {@link TableNotFoundException#withTableId(String)}
   */
  @Test
  @DisplayName("Test withTableId(String) with 'tableId'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"TableNotFoundException TableNotFoundException.withTableId(String)"})
  void testWithTableIdWithTableId() {
    // Arrange and Act
    TableNotFoundException actualWithTableIdResult = TableNotFoundException.withTableId("42");

    // Assert
    assertEquals("Table not found with ID \"42\"", actualWithTableIdResult.getLocalizedMessage());
    assertEquals("Table not found with ID \"42\"", actualWithTableIdResult.getMessage());
    assertNull(actualWithTableIdResult.getCause());
    assertEquals(0, actualWithTableIdResult.getSuppressed().length);
  }

  /**
   * Test {@link TableNotFoundException#withTableId(String, Exception)} with {@code tableId}, {@code cause}.
   * <p>
   * Method under test: {@link TableNotFoundException#withTableId(String, Exception)}
   */
  @Test
  @DisplayName("Test withTableId(String, Exception) with 'tableId', 'cause'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"TableNotFoundException TableNotFoundException.withTableId(String, Exception)"})
  void testWithTableIdWithTableIdCause() {
    // Arrange
    Exception cause = new Exception("foo");

    // Act
    TableNotFoundException actualWithTableIdResult = TableNotFoundException.withTableId("42", cause);

    // Assert
    assertEquals("Table not found with ID \"42\"", actualWithTableIdResult.getLocalizedMessage());
    assertEquals("Table not found with ID \"42\"", actualWithTableIdResult.getMessage());
    assertEquals(0, actualWithTableIdResult.getSuppressed().length);
    assertSame(cause, actualWithTableIdResult.getCause());
  }

  /**
   * Test {@link TableNotFoundException#withTableName(String)} with {@code tableName}.
   * <p>
   * Method under test: {@link TableNotFoundException#withTableName(String)}
   */
  @Test
  @DisplayName("Test withTableName(String) with 'tableName'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"TableNotFoundException TableNotFoundException.withTableName(String)"})
  void testWithTableNameWithTableName() {
    // Arrange and Act
    TableNotFoundException actualWithTableNameResult = TableNotFoundException.withTableName("Table Name");

    // Assert
    assertEquals("Table not found with name \"Table Name\"", actualWithTableNameResult.getLocalizedMessage());
    assertEquals("Table not found with name \"Table Name\"", actualWithTableNameResult.getMessage());
    assertNull(actualWithTableNameResult.getCause());
    assertEquals(0, actualWithTableNameResult.getSuppressed().length);
  }

  /**
   * Test {@link TableNotFoundException#withTableName(String, Exception)} with {@code tableName}, {@code cause}.
   * <p>
   * Method under test: {@link TableNotFoundException#withTableName(String, Exception)}
   */
  @Test
  @DisplayName("Test withTableName(String, Exception) with 'tableName', 'cause'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"TableNotFoundException TableNotFoundException.withTableName(String, Exception)"})
  void testWithTableNameWithTableNameCause() {
    // Arrange
    Exception cause = new Exception("foo");

    // Act
    TableNotFoundException actualWithTableNameResult = TableNotFoundException.withTableName("Table Name", cause);

    // Assert
    assertEquals("Table not found with name \"Table Name\"", actualWithTableNameResult.getLocalizedMessage());
    assertEquals("Table not found with name \"Table Name\"", actualWithTableNameResult.getMessage());
    assertEquals(0, actualWithTableNameResult.getSuppressed().length);
    assertSame(cause, actualWithTableNameResult.getCause());
  }

  /**
   * Test {@link TableNotFoundException#withTable(TableStatus)} with {@code table}.
   * <p>
   * Method under test: {@link TableNotFoundException#withTable(TableStatus)}
   */
  @Test
  @DisplayName("Test withTable(TableStatus) with 'table'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"TableNotFoundException TableNotFoundException.withTable(TableStatus)"})
  void testWithTableWithTable() {
    // Arrange and Act
    TableNotFoundException actualWithTableResult = TableNotFoundException
        .withTable(TableStatus.uniqueIdAndName("42", "Table Name", true));

    // Assert
    assertEquals("Table not found: Table Name (42)", actualWithTableResult.getLocalizedMessage());
    assertEquals("Table not found: Table Name (42)", actualWithTableResult.getMessage());
    assertNull(actualWithTableResult.getCause());
    assertEquals(0, actualWithTableResult.getSuppressed().length);
  }

  /**
   * Test {@link TableNotFoundException#withTable(TableStatus, Exception)} with {@code table}, {@code cause}.
   * <p>
   * Method under test: {@link TableNotFoundException#withTable(TableStatus, Exception)}
   */
  @Test
  @DisplayName("Test withTable(TableStatus, Exception) with 'table', 'cause'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"TableNotFoundException TableNotFoundException.withTable(TableStatus, Exception)"})
  void testWithTableWithTableCause() {
    // Arrange
    TableStatus table = TableStatus.uniqueIdAndName("42", "Table Name", true);
    Exception cause = new Exception("foo");

    // Act
    TableNotFoundException actualWithTableResult = TableNotFoundException.withTable(table, cause);

    // Assert
    assertEquals("Table not found: Table Name (42)", actualWithTableResult.getLocalizedMessage());
    assertEquals("Table not found: Table Name (42)", actualWithTableResult.getMessage());
    assertEquals(0, actualWithTableResult.getSuppressed().length);
    assertSame(cause, actualWithTableResult.getCause());
  }
}
