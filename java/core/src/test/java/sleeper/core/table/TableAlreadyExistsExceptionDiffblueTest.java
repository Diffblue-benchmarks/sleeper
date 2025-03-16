package sleeper.core.table;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class TableAlreadyExistsExceptionDiffblueTest {
  /**
   * Test {@link TableAlreadyExistsException#TableAlreadyExistsException(TableStatus)}.
   * <p>
   * Method under test: {@link TableAlreadyExistsException#TableAlreadyExistsException(TableStatus)}
   */
  @Test
  @DisplayName("Test new TableAlreadyExistsException(TableStatus)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void TableAlreadyExistsException.<init>(TableStatus)"})
  void testNewTableAlreadyExistsException() {
    // Arrange and Act
    TableAlreadyExistsException actualTableAlreadyExistsException = new TableAlreadyExistsException(
        TableStatus.uniqueIdAndName("42", "Table Name", true));

    // Assert
    assertEquals("Table already exists: Table Name (42)", actualTableAlreadyExistsException.getLocalizedMessage());
    assertEquals("Table already exists: Table Name (42)", actualTableAlreadyExistsException.getMessage());
    assertNull(actualTableAlreadyExistsException.getCause());
    assertEquals(0, actualTableAlreadyExistsException.getSuppressed().length);
  }

  /**
   * Test {@link TableAlreadyExistsException#TableAlreadyExistsException(TableStatus, Exception)}.
   * <p>
   * Method under test: {@link TableAlreadyExistsException#TableAlreadyExistsException(TableStatus, Exception)}
   */
  @Test
  @DisplayName("Test new TableAlreadyExistsException(TableStatus, Exception)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void TableAlreadyExistsException.<init>(TableStatus, Exception)"})
  void testNewTableAlreadyExistsException2() {
    // Arrange
    TableStatus table = TableStatus.uniqueIdAndName("42", "Table Name", true);
    Exception cause = new Exception("foo");

    // Act
    TableAlreadyExistsException actualTableAlreadyExistsException = new TableAlreadyExistsException(table, cause);

    // Assert
    assertEquals("Table already exists: Table Name (42)", actualTableAlreadyExistsException.getLocalizedMessage());
    assertEquals("Table already exists: Table Name (42)", actualTableAlreadyExistsException.getMessage());
    assertEquals(0, actualTableAlreadyExistsException.getSuppressed().length);
    assertSame(cause, actualTableAlreadyExistsException.getCause());
  }
}
