package sleeper.bulkimport.runner.rdd;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.bulkimport.runner.BulkImportJobInput;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.table.TableProperties;

class BulkImportJobRDDDriverDiffblueTest {
  /**
   * Test {@link BulkImportJobRDDDriver#createFileReferences(BulkImportJobInput)}.
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BulkImportJobRDDDriver#createFileReferences(BulkImportJobInput)}
   */
  @Test
  @DisplayName("Test createFileReferences(BulkImportJobInput); then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"org.apache.spark.sql.Dataset BulkImportJobRDDDriver.createFileReferences(BulkImportJobInput)"})
  void testCreateFileReferences_thenThrowIllegalArgumentException() {
    // Arrange
    BulkImportJobInput input = mock(BulkImportJobInput.class);
    when(input.rows()).thenThrow(new IllegalArgumentException("foo"));
    when(input.tableProperties()).thenReturn(new TableProperties(new InstanceProperties()));

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> BulkImportJobRDDDriver.createFileReferences(input));
    verify(input).rows();
    verify(input).tableProperties();
  }
}
