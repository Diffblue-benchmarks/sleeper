package sleeper.garbagecollector;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.table.TableStatus;
import sleeper.garbagecollector.FailedGarbageCollectionException.TableFailures;

class TableFilesDeletedDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link TableFilesDeleted#TableFilesDeleted(TableStatus)}
   *   <li>{@link TableFilesDeleted#getDeletedFilenames()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void TableFilesDeleted.<init>(TableStatus)", "List TableFilesDeleted.getDeletedFilenames()"})
  void testGettersAndSetters() {
    // Arrange, Act and Assert
    assertTrue(
        (new TableFilesDeleted(TableStatus.uniqueIdAndName("42", "Table Name", true))).getDeletedFilenames().isEmpty());
  }

  /**
   * Test {@link TableFilesDeleted#deleted(String)}.
   * <p>
   * Method under test: {@link TableFilesDeleted#deleted(String)}
   */
  @Test
  @DisplayName("Test deleted(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void TableFilesDeleted.deleted(String)"})
  void testDeleted() {
    // Arrange
    TableFilesDeleted tableFilesDeleted = new TableFilesDeleted(TableStatus.uniqueIdAndName("42", "Table Name", true));

    // Act
    tableFilesDeleted.deleted("foo.txt");

    // Assert
    List<String> deletedFilenames = tableFilesDeleted.getDeletedFilenames();
    assertEquals(1, deletedFilenames.size());
    assertEquals("foo.txt", deletedFilenames.get(0));
  }

  /**
   * Test {@link TableFilesDeleted#buildTableFailures()}.
   * <p>
   * Method under test: {@link TableFilesDeleted#buildTableFailures()}
   */
  @Test
  @DisplayName("Test buildTableFailures()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.util.Optional TableFilesDeleted.buildTableFailures()"})
  void testBuildTableFailures() {
    // Arrange, Act and Assert
    assertFalse((new TableFilesDeleted(TableStatus.uniqueIdAndName("42", "Table Name", true))).buildTableFailures()
        .isPresent());
  }

  /**
   * Test {@link TableFilesDeleted#buildTableFailures(Exception)} with {@code Exception}.
   * <p>
   * Method under test: {@link TableFilesDeleted#buildTableFailures(Exception)}
   */
  @Test
  @DisplayName("Test buildTableFailures(Exception) with 'Exception'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"TableFailures TableFilesDeleted.buildTableFailures(Exception)"})
  void testBuildTableFailuresWithException() {
    // Arrange
    TableStatus table = TableStatus.uniqueIdAndName("42", "Table Name", true);
    TableFilesDeleted tableFilesDeleted = new TableFilesDeleted(table);
    Exception tableFailure = new Exception("foo");

    // Act
    TableFailures actualBuildTableFailuresResult = tableFilesDeleted.buildTableFailures(tableFailure);

    // Assert
    assertTrue(actualBuildTableFailuresResult.getFileFailures().isEmpty());
    assertTrue(actualBuildTableFailuresResult.getStateStoreUpdateFailures().isEmpty());
    assertSame(tableFailure, actualBuildTableFailuresResult.getTableFailure());
    assertSame(table, actualBuildTableFailuresResult.getTable());
  }
}
