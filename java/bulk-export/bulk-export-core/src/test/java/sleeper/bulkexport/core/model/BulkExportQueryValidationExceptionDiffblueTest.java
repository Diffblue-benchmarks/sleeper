package sleeper.bulkexport.core.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class BulkExportQueryValidationExceptionDiffblueTest {
  /**
   * Test {@link BulkExportQueryValidationException#BulkExportQueryValidationException(String, Exception)}.
   * <p>
   * Method under test: {@link BulkExportQueryValidationException#BulkExportQueryValidationException(String, Exception)}
   */
  @Test
  @DisplayName("Test new BulkExportQueryValidationException(String, Exception)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void BulkExportQueryValidationException.<init>(String, Exception)"})
  void testNewBulkExportQueryValidationException() {
    // Arrange and Act
    BulkExportQueryValidationException actualBulkExportQueryValidationException = new BulkExportQueryValidationException(
        null, new Exception("foo"));

    // Assert
    assertEquals("Query validation failed: foo", actualBulkExportQueryValidationException.getLocalizedMessage());
    assertEquals("Query validation failed: foo", actualBulkExportQueryValidationException.getMessage());
    assertFalse(actualBulkExportQueryValidationException.getExportId().isPresent());
  }

  /**
   * Test {@link BulkExportQueryValidationException#BulkExportQueryValidationException(String, String)}.
   * <p>
   * Method under test: {@link BulkExportQueryValidationException#BulkExportQueryValidationException(String, String)}
   */
  @Test
  @DisplayName("Test new BulkExportQueryValidationException(String, String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void BulkExportQueryValidationException.<init>(String, String)"})
  void testNewBulkExportQueryValidationException2() {
    // Arrange and Act
    BulkExportQueryValidationException actualBulkExportQueryValidationException = new BulkExportQueryValidationException(
        null, "An error occurred");

    // Assert
    assertEquals("Query validation failed: An error occurred",
        actualBulkExportQueryValidationException.getLocalizedMessage());
    assertEquals("Query validation failed: An error occurred", actualBulkExportQueryValidationException.getMessage());
    assertNull(actualBulkExportQueryValidationException.getCause());
    assertEquals(0, actualBulkExportQueryValidationException.getSuppressed().length);
    assertFalse(actualBulkExportQueryValidationException.getExportId().isPresent());
  }

  /**
   * Test {@link BulkExportQueryValidationException#BulkExportQueryValidationException(String, Exception)}.
   * <ul>
   *   <li>When {@code 42}.</li>
   *   <li>Then return ExportId is {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BulkExportQueryValidationException#BulkExportQueryValidationException(String, Exception)}
   */
  @Test
  @DisplayName("Test new BulkExportQueryValidationException(String, Exception); when '42'; then return ExportId is '42'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void BulkExportQueryValidationException.<init>(String, Exception)"})
  void testNewBulkExportQueryValidationException_when42_thenReturnExportIdIs42() {
    // Arrange and Act
    BulkExportQueryValidationException actualBulkExportQueryValidationException = new BulkExportQueryValidationException(
        "42", new Exception("foo"));

    // Assert
    Optional<String> exportId = actualBulkExportQueryValidationException.getExportId();
    assertEquals("42", exportId.get());
    assertEquals("Query validation failed for export \"42\": foo",
        actualBulkExportQueryValidationException.getLocalizedMessage());
    assertEquals("Query validation failed for export \"42\": foo",
        actualBulkExportQueryValidationException.getMessage());
    assertTrue(exportId.isPresent());
  }

  /**
   * Test {@link BulkExportQueryValidationException#BulkExportQueryValidationException(String, String)}.
   * <ul>
   *   <li>When {@code 42}.</li>
   *   <li>Then return ExportId is {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BulkExportQueryValidationException#BulkExportQueryValidationException(String, String)}
   */
  @Test
  @DisplayName("Test new BulkExportQueryValidationException(String, String); when '42'; then return ExportId is '42'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void BulkExportQueryValidationException.<init>(String, String)"})
  void testNewBulkExportQueryValidationException_when42_thenReturnExportIdIs422() {
    // Arrange and Act
    BulkExportQueryValidationException actualBulkExportQueryValidationException = new BulkExportQueryValidationException(
        "42", "An error occurred");

    // Assert
    Optional<String> exportId = actualBulkExportQueryValidationException.getExportId();
    assertEquals("42", exportId.get());
    assertEquals("Query validation failed for export \"42\": An error occurred",
        actualBulkExportQueryValidationException.getLocalizedMessage());
    assertEquals("Query validation failed for export \"42\": An error occurred",
        actualBulkExportQueryValidationException.getMessage());
    assertTrue(exportId.isPresent());
  }

  /**
   * Test {@link BulkExportQueryValidationException#getExportId()}.
   * <p>
   * Method under test: {@link BulkExportQueryValidationException#getExportId()}
   */
  @Test
  @DisplayName("Test getExportId()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Optional BulkExportQueryValidationException.getExportId()"})
  void testGetExportId() {
    // Arrange and Act
    Optional<String> actualExportId = (new BulkExportQueryValidationException("42", "An error occurred")).getExportId();

    // Assert
    assertEquals("42", actualExportId.get());
    assertTrue(actualExportId.isPresent());
  }
}
