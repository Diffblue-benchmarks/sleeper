package sleeper.core.tracker.job.run;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class RecordsProcessedDiffblueTest {
  /**
   * Test {@link RecordsProcessed#RecordsProcessed(long, long)}.
   * <p>
   * Method under test: {@link RecordsProcessed#RecordsProcessed(long, long)}
   */
  @Test
  @DisplayName("Test new RecordsProcessed(long, long)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void RecordsProcessed.<init>(long, long)"})
  void testNewRecordsProcessed() {
    // Arrange and Act
    RecordsProcessed actualRecordsProcessed = new RecordsProcessed(1L, 1L);

    // Assert
    assertEquals(1L, actualRecordsProcessed.getRecordsRead());
    assertEquals(1L, actualRecordsProcessed.getRecordsWritten());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link RecordsProcessed#toString()}
   *   <li>{@link RecordsProcessed#getRecordsRead()}
   *   <li>{@link RecordsProcessed#getRecordsWritten()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"long RecordsProcessed.getRecordsRead()", "long RecordsProcessed.getRecordsWritten()",
      "String RecordsProcessed.toString()"})
  void testGettersAndSetters() {
    // Arrange
    RecordsProcessed recordsProcessed = new RecordsProcessed(1L, 1L);

    // Act
    String actualToStringResult = recordsProcessed.toString();
    long actualRecordsRead = recordsProcessed.getRecordsRead();

    // Assert
    assertEquals("RecordsProcessed{recordsRead=1, recordsWritten=1}", actualToStringResult);
    assertEquals(1L, actualRecordsRead);
    assertEquals(1L, recordsProcessed.getRecordsWritten());
  }

  /**
   * Test {@link RecordsProcessed#equals(Object)}, and {@link RecordsProcessed#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link RecordsProcessed#equals(Object)}
   *   <li>{@link RecordsProcessed#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean RecordsProcessed.equals(Object)", "int RecordsProcessed.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    RecordsProcessed recordsProcessed = RecordsProcessed.NONE;
    RecordsProcessed recordsProcessed2 = RecordsProcessed.NONE;

    // Act and Assert
    assertEquals(recordsProcessed, recordsProcessed2);
    int expectedHashCodeResult = recordsProcessed.hashCode();
    assertEquals(expectedHashCodeResult, recordsProcessed2.hashCode());
  }

  /**
   * Test {@link RecordsProcessed#equals(Object)}, and {@link RecordsProcessed#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link RecordsProcessed#equals(Object)}
   *   <li>{@link RecordsProcessed#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean RecordsProcessed.equals(Object)", "int RecordsProcessed.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual2() {
    // Arrange
    RecordsProcessed recordsProcessed = new RecordsProcessed(1L, 1L);
    RecordsProcessed recordsProcessed2 = new RecordsProcessed(1L, 1L);

    // Act and Assert
    assertEquals(recordsProcessed, recordsProcessed2);
    int expectedHashCodeResult = recordsProcessed.hashCode();
    assertEquals(expectedHashCodeResult, recordsProcessed2.hashCode());
  }

  /**
   * Test {@link RecordsProcessed#equals(Object)}, and {@link RecordsProcessed#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link RecordsProcessed#equals(Object)}
   *   <li>{@link RecordsProcessed#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean RecordsProcessed.equals(Object)", "int RecordsProcessed.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    RecordsProcessed recordsProcessed = RecordsProcessed.NONE;

    // Act and Assert
    assertEquals(recordsProcessed, recordsProcessed);
    int expectedHashCodeResult = recordsProcessed.hashCode();
    assertEquals(expectedHashCodeResult, recordsProcessed.hashCode());
  }

  /**
   * Test {@link RecordsProcessed#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link RecordsProcessed#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean RecordsProcessed.equals(Object)", "int RecordsProcessed.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new RecordsProcessed(1L, 1L), RecordsProcessed.NONE);
  }

  /**
   * Test {@link RecordsProcessed#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link RecordsProcessed#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean RecordsProcessed.equals(Object)", "int RecordsProcessed.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange, Act and Assert
    assertNotEquals(new RecordsProcessed(0L, 1L), RecordsProcessed.NONE);
  }

  /**
   * Test {@link RecordsProcessed#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link RecordsProcessed#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean RecordsProcessed.equals(Object)", "int RecordsProcessed.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(RecordsProcessed.NONE, null);
  }

  /**
   * Test {@link RecordsProcessed#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link RecordsProcessed#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean RecordsProcessed.equals(Object)", "int RecordsProcessed.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(RecordsProcessed.NONE, "Different type to RecordsProcessed");
  }
}
