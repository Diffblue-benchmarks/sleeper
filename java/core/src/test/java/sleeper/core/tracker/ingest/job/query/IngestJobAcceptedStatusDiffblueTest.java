package sleeper.core.tracker.ingest.job.query;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class IngestJobAcceptedStatusDiffblueTest {
  /**
   * Test {@link IngestJobAcceptedStatus#from(int, Instant, Instant)}.
   * <p>
   * Method under test: {@link IngestJobAcceptedStatus#from(int, Instant, Instant)}
   */
  @Test
  @DisplayName("Test from(int, Instant, Instant)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"IngestJobAcceptedStatus IngestJobAcceptedStatus.from(int, Instant, Instant)"})
  void testFrom() {
    // Arrange
    Instant validationTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    Instant updateTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    // Act
    IngestJobAcceptedStatus actualFromResult = IngestJobAcceptedStatus.from(3, validationTime, updateTime);

    // Assert
    assertEquals(3, actualFromResult.getInputFileCount());
    assertTrue(actualFromResult.isValid());
    Instant instant = updateTime.EPOCH;
    assertSame(instant, actualFromResult.getStartTime());
    assertSame(instant, actualFromResult.getUpdateTime());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link IngestJobAcceptedStatus#toString()}
   *   <li>{@link IngestJobAcceptedStatus#getInputFileCount()}
   *   <li>{@link IngestJobAcceptedStatus#getStartTime()}
   *   <li>{@link IngestJobAcceptedStatus#getUpdateTime()}
   *   <li>{@link IngestJobAcceptedStatus#isValid()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"int IngestJobAcceptedStatus.getInputFileCount()",
      "Instant IngestJobAcceptedStatus.getStartTime()", "Instant IngestJobAcceptedStatus.getUpdateTime()",
      "boolean IngestJobAcceptedStatus.isValid()", "String IngestJobAcceptedStatus.toString()"})
  void testGettersAndSetters() {
    // Arrange
    Instant validationTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    IngestJobAcceptedStatus fromResult = IngestJobAcceptedStatus.from(3, validationTime,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act
    String actualToStringResult = fromResult.toString();
    int actualInputFileCount = fromResult.getInputFileCount();
    Instant actualStartTime = fromResult.getStartTime();
    Instant actualUpdateTime = fromResult.getUpdateTime();

    // Assert
    assertEquals("IngestJobAcceptedStatus{validationTime=1970-01-01T00:00:00Z, updateTime=1970-01-01T00:00:00Z,"
        + " inputFileCount=3}", actualToStringResult);
    assertEquals(3, actualInputFileCount);
    assertTrue(fromResult.isValid());
    Instant instant = actualUpdateTime.EPOCH;
    assertSame(instant, actualStartTime);
    assertSame(instant, actualUpdateTime);
  }

  /**
   * Test {@link IngestJobAcceptedStatus#equals(Object)}, and {@link IngestJobAcceptedStatus#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link IngestJobAcceptedStatus#equals(Object)}
   *   <li>{@link IngestJobAcceptedStatus#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestJobAcceptedStatus.equals(Object)", "int IngestJobAcceptedStatus.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    Instant validationTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    IngestJobAcceptedStatus fromResult = IngestJobAcceptedStatus.from(3, validationTime,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    Instant validationTime2 = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    IngestJobAcceptedStatus fromResult2 = IngestJobAcceptedStatus.from(3, validationTime2,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act and Assert
    assertEquals(fromResult, fromResult2);
    int expectedHashCodeResult = fromResult.hashCode();
    assertEquals(expectedHashCodeResult, fromResult2.hashCode());
  }

  /**
   * Test {@link IngestJobAcceptedStatus#equals(Object)}, and {@link IngestJobAcceptedStatus#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link IngestJobAcceptedStatus#equals(Object)}
   *   <li>{@link IngestJobAcceptedStatus#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestJobAcceptedStatus.equals(Object)", "int IngestJobAcceptedStatus.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    Instant validationTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    IngestJobAcceptedStatus fromResult = IngestJobAcceptedStatus.from(3, validationTime,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act and Assert
    assertEquals(fromResult, fromResult);
    int expectedHashCodeResult = fromResult.hashCode();
    assertEquals(expectedHashCodeResult, fromResult.hashCode());
  }

  /**
   * Test {@link IngestJobAcceptedStatus#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobAcceptedStatus#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestJobAcceptedStatus.equals(Object)", "int IngestJobAcceptedStatus.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    Instant validationTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    IngestJobAcceptedStatus fromResult = IngestJobAcceptedStatus.from(1, validationTime,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    Instant validationTime2 = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    // Act and Assert
    assertNotEquals(fromResult, IngestJobAcceptedStatus.from(3, validationTime2,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
  }

  /**
   * Test {@link IngestJobAcceptedStatus#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobAcceptedStatus#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestJobAcceptedStatus.equals(Object)", "int IngestJobAcceptedStatus.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    Instant validationTime = LocalDate.now().atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    IngestJobAcceptedStatus fromResult = IngestJobAcceptedStatus.from(3, validationTime,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    Instant validationTime2 = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    // Act and Assert
    assertNotEquals(fromResult, IngestJobAcceptedStatus.from(3, validationTime2,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
  }

  /**
   * Test {@link IngestJobAcceptedStatus#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobAcceptedStatus#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestJobAcceptedStatus.equals(Object)", "int IngestJobAcceptedStatus.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual3() {
    // Arrange
    Instant validationTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    IngestJobAcceptedStatus fromResult = IngestJobAcceptedStatus.from(3, validationTime,
        LocalDate.now().atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    Instant validationTime2 = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    // Act and Assert
    assertNotEquals(fromResult, IngestJobAcceptedStatus.from(3, validationTime2,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
  }

  /**
   * Test {@link IngestJobAcceptedStatus#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobAcceptedStatus#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestJobAcceptedStatus.equals(Object)", "int IngestJobAcceptedStatus.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange
    Instant validationTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    // Act and Assert
    assertNotEquals(IngestJobAcceptedStatus.from(3, validationTime,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()), null);
  }

  /**
   * Test {@link IngestJobAcceptedStatus#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobAcceptedStatus#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestJobAcceptedStatus.equals(Object)", "int IngestJobAcceptedStatus.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange
    Instant validationTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    // Act and Assert
    assertNotEquals(
        IngestJobAcceptedStatus.from(3, validationTime,
            LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()),
        "Different type to IngestJobAcceptedStatus");
  }
}
