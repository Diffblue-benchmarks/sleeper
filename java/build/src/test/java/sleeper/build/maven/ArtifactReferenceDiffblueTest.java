package sleeper.build.maven;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class ArtifactReferenceDiffblueTest {
  /**
   * Test {@link ArtifactReference#groupAndArtifact(String, String)}.
   * <p>
   * Method under test: {@link ArtifactReference#groupAndArtifact(String, String)}
   */
  @Test
  @DisplayName("Test groupAndArtifact(String, String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"ArtifactReference ArtifactReference.groupAndArtifact(String, String)"})
  void testGroupAndArtifact() {
    // Arrange, Act and Assert
    assertEquals("42", ArtifactReference.groupAndArtifact("42", "42").getArtifactId());
  }

  /**
   * Test {@link ArtifactReference#equals(Object)}, and {@link ArtifactReference#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link ArtifactReference#equals(Object)}
   *   <li>{@link ArtifactReference#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean ArtifactReference.equals(Object)", "int ArtifactReference.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    ArtifactReference groupAndArtifactResult = ArtifactReference.groupAndArtifact("42", "42");
    ArtifactReference groupAndArtifactResult2 = ArtifactReference.groupAndArtifact("42", "42");

    // Act and Assert
    assertEquals(groupAndArtifactResult, groupAndArtifactResult2);
    int expectedHashCodeResult = groupAndArtifactResult.hashCode();
    assertEquals(expectedHashCodeResult, groupAndArtifactResult2.hashCode());
  }

  /**
   * Test {@link ArtifactReference#equals(Object)}, and {@link ArtifactReference#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link ArtifactReference#equals(Object)}
   *   <li>{@link ArtifactReference#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean ArtifactReference.equals(Object)", "int ArtifactReference.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    ArtifactReference groupAndArtifactResult = ArtifactReference.groupAndArtifact("42", "42");

    // Act and Assert
    assertEquals(groupAndArtifactResult, groupAndArtifactResult);
    int expectedHashCodeResult = groupAndArtifactResult.hashCode();
    assertEquals(expectedHashCodeResult, groupAndArtifactResult.hashCode());
  }

  /**
   * Test {@link ArtifactReference#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link ArtifactReference#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean ArtifactReference.equals(Object)", "int ArtifactReference.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    ArtifactReference groupAndArtifactResult = ArtifactReference.groupAndArtifact("Group Id", "42");

    // Act and Assert
    assertNotEquals(groupAndArtifactResult, ArtifactReference.groupAndArtifact("42", "42"));
  }

  /**
   * Test {@link ArtifactReference#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link ArtifactReference#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean ArtifactReference.equals(Object)", "int ArtifactReference.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    ArtifactReference groupAndArtifactResult = ArtifactReference.groupAndArtifact("42", "Artifact Id");

    // Act and Assert
    assertNotEquals(groupAndArtifactResult, ArtifactReference.groupAndArtifact("42", "42"));
  }

  /**
   * Test {@link ArtifactReference#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link ArtifactReference#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean ArtifactReference.equals(Object)", "int ArtifactReference.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(ArtifactReference.groupAndArtifact("42", "42"), null);
  }

  /**
   * Test {@link ArtifactReference#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link ArtifactReference#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean ArtifactReference.equals(Object)", "int ArtifactReference.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(ArtifactReference.groupAndArtifact("42", "42"), "Different type to ArtifactReference");
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link ArtifactReference#toString()}
   *   <li>{@link ArtifactReference#getArtifactId()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String ArtifactReference.getArtifactId()", "String ArtifactReference.toString()"})
  void testGettersAndSetters() {
    // Arrange
    ArtifactReference groupAndArtifactResult = ArtifactReference.groupAndArtifact("42", "42");

    // Act
    String actualToStringResult = groupAndArtifactResult.toString();

    // Assert
    assertEquals("42", groupAndArtifactResult.getArtifactId());
    assertEquals("42:42", actualToStringResult);
  }
}
