package sleeper.systemtest.drivers.nightly;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class NightlyTestTimestampDiffblueTest {
  /**
   * Test {@link NightlyTestTimestamp#from(String)} with {@code commandLineArgument}.
   * <ul>
   *   <li>When {@code 42}.</li>
   *   <li>Then return S3FolderName is {@code 19700101_000042}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NightlyTestTimestamp#from(String)}
   */
  @Test
  @DisplayName("Test from(String) with 'commandLineArgument'; when '42'; then return S3FolderName is '19700101_000042'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"NightlyTestTimestamp NightlyTestTimestamp.from(String)"})
  void testFromWithCommandLineArgument_when42_thenReturnS3FolderNameIs19700101000042() {
    // Arrange and Act
    NightlyTestTimestamp actualFromResult = NightlyTestTimestamp.from("42");

    // Assert
    assertEquals("19700101_000042", actualFromResult.getS3FolderName());
    Instant toInstantResult = actualFromResult.toInstant();
    assertEquals(0, toInstantResult.getNano());
    assertEquals(42L, toInstantResult.getEpochSecond());
  }

  /**
   * Test {@link NightlyTestTimestamp#from(Instant)} with {@code startTime}.
   * <p>
   * Method under test: {@link NightlyTestTimestamp#from(Instant)}
   */
  @Test
  @DisplayName("Test from(Instant) with 'startTime'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"NightlyTestTimestamp NightlyTestTimestamp.from(Instant)"})
  void testFromWithStartTime() {
    // Arrange
    Instant startTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    // Act
    NightlyTestTimestamp actualFromResult = NightlyTestTimestamp.from(startTime);

    // Assert
    assertEquals("19700101_000000", actualFromResult.getS3FolderName());
    Instant expectedToInstantResult = startTime.EPOCH;
    assertSame(expectedToInstantResult, actualFromResult.toInstant());
  }

  /**
   * Test {@link NightlyTestTimestamp#getS3FolderName()}.
   * <p>
   * Method under test: {@link NightlyTestTimestamp#getS3FolderName()}
   */
  @Test
  @DisplayName("Test getS3FolderName()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String NightlyTestTimestamp.getS3FolderName()"})
  void testGetS3FolderName() {
    // Arrange, Act and Assert
    assertEquals("19700101_000000",
        NightlyTestTimestamp.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
            .getS3FolderName());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link NightlyTestTimestamp#toString()}
   *   <li>{@link NightlyTestTimestamp#toInstant()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Instant NightlyTestTimestamp.toInstant()", "String NightlyTestTimestamp.toString()"})
  void testGettersAndSetters() {
    // Arrange
    NightlyTestTimestamp fromResult = NightlyTestTimestamp
        .from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act
    String actualToStringResult = fromResult.toString();
    Instant actualToInstantResult = fromResult.toInstant();

    // Assert
    assertEquals("1970-01-01T00:00:00Z", actualToStringResult);
    assertSame(actualToInstantResult.EPOCH, actualToInstantResult);
  }

  /**
   * Test {@link NightlyTestTimestamp#equals(Object)}, and {@link NightlyTestTimestamp#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link NightlyTestTimestamp#equals(Object)}
   *   <li>{@link NightlyTestTimestamp#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean NightlyTestTimestamp.equals(Object)", "int NightlyTestTimestamp.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    NightlyTestTimestamp fromResult = NightlyTestTimestamp
        .from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    NightlyTestTimestamp fromResult2 = NightlyTestTimestamp
        .from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act and Assert
    assertEquals(fromResult, fromResult2);
    int expectedHashCodeResult = fromResult.hashCode();
    assertEquals(expectedHashCodeResult, fromResult2.hashCode());
  }

  /**
   * Test {@link NightlyTestTimestamp#equals(Object)}, and {@link NightlyTestTimestamp#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link NightlyTestTimestamp#equals(Object)}
   *   <li>{@link NightlyTestTimestamp#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean NightlyTestTimestamp.equals(Object)", "int NightlyTestTimestamp.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    NightlyTestTimestamp fromResult = NightlyTestTimestamp
        .from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act and Assert
    assertEquals(fromResult, fromResult);
    int expectedHashCodeResult = fromResult.hashCode();
    assertEquals(expectedHashCodeResult, fromResult.hashCode());
  }

  /**
   * Test {@link NightlyTestTimestamp#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link NightlyTestTimestamp#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean NightlyTestTimestamp.equals(Object)", "int NightlyTestTimestamp.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    NightlyTestTimestamp fromResult = NightlyTestTimestamp
        .from(LocalDate.now().atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act and Assert
    assertNotEquals(fromResult,
        NightlyTestTimestamp.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
  }

  /**
   * Test {@link NightlyTestTimestamp#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link NightlyTestTimestamp#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean NightlyTestTimestamp.equals(Object)", "int NightlyTestTimestamp.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(
        NightlyTestTimestamp.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()), null);
  }

  /**
   * Test {@link NightlyTestTimestamp#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link NightlyTestTimestamp#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean NightlyTestTimestamp.equals(Object)", "int NightlyTestTimestamp.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(
        NightlyTestTimestamp.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()),
        "Different type to NightlyTestTimestamp");
  }
}
