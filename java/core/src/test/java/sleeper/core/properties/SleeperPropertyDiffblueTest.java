package sleeper.core.properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.properties.instance.ArrayListIngestProperty;
import sleeper.core.properties.instance.AsyncIngestPartitionFileWriterProperty;
import sleeper.core.properties.instance.UserDefinedInstanceProperty;
import sleeper.core.properties.testutils.DummySleeperProperty;

class SleeperPropertyDiffblueTest {
  /**
   * Test {@link SleeperProperty#isSetByCdk()}.
   * <p>
   * Method under test: {@link SleeperProperty#isSetByCdk()}
   */
  @Test
  @DisplayName("Test isSetByCdk()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperProperty.isSetByCdk()"})
  void testIsSetByCdk() {
    // Arrange, Act and Assert
    assertFalse((new DummySleeperProperty()).isSetByCdk());
  }

  /**
   * Test {@link SleeperProperty#isEditable()}.
   * <ul>
   *   <li>Given {@link DummySleeperProperty} (default constructor).</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperProperty#isEditable()}
   */
  @Test
  @DisplayName("Test isEditable(); given DummySleeperProperty (default constructor); then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperProperty.isEditable()"})
  void testIsEditable_givenDummySleeperProperty_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue((new DummySleeperProperty()).isEditable());
  }

  /**
   * Test {@link SleeperProperty#isEditable()}.
   * <ul>
   *   <li>Then calls {@link SleeperProperty#isEditable()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperProperty#isEditable()}
   */
  @Test
  @DisplayName("Test isEditable(); then calls isEditable()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperProperty.isEditable()"})
  void testIsEditable_thenCallsIsEditable() {
    // Arrange
    UserDefinedInstanceProperty userDefinedInstanceProperty = mock(UserDefinedInstanceProperty.class);
    when(userDefinedInstanceProperty.isEditable()).thenReturn(false);

    // Act
    userDefinedInstanceProperty.isEditable();

    // Assert
    verify(userDefinedInstanceProperty).isEditable();
  }

  /**
   * Test {@link SleeperProperty#isUserDefined()}.
   * <ul>
   *   <li>Given {@link DummySleeperProperty} (default constructor).</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperProperty#isUserDefined()}
   */
  @Test
  @DisplayName("Test isUserDefined(); given DummySleeperProperty (default constructor); then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperProperty.isUserDefined()"})
  void testIsUserDefined_givenDummySleeperProperty_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue((new DummySleeperProperty()).isUserDefined());
  }

  /**
   * Test {@link SleeperProperty#isUserDefined()}.
   * <ul>
   *   <li>Then calls {@link SleeperProperty#isUserDefined()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperProperty#isUserDefined()}
   */
  @Test
  @DisplayName("Test isUserDefined(); then calls isUserDefined()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperProperty.isUserDefined()"})
  void testIsUserDefined_thenCallsIsUserDefined() {
    // Arrange
    UserDefinedInstanceProperty userDefinedInstanceProperty = mock(UserDefinedInstanceProperty.class);
    when(userDefinedInstanceProperty.isUserDefined()).thenReturn(false);

    // Act
    userDefinedInstanceProperty.isUserDefined();

    // Assert
    verify(userDefinedInstanceProperty).isUserDefined();
  }

  /**
   * Test {@link SleeperProperty#getValidationPredicate()}.
   * <ul>
   *   <li>Given {@link DummySleeperProperty} (default constructor).</li>
   *   <li>Then return test {@code foo}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperProperty#getValidationPredicate()}
   */
  @Test
  @DisplayName("Test getValidationPredicate(); given DummySleeperProperty (default constructor); then return test 'foo'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.util.function.Predicate SleeperProperty.getValidationPredicate()"})
  void testGetValidationPredicate_givenDummySleeperProperty_thenReturnTestFoo() {
    // Arrange, Act and Assert
    assertTrue((new DummySleeperProperty()).getValidationPredicate().test("foo"));
  }

  /**
   * Test {@link SleeperProperty#getValidationPredicate()}.
   * <ul>
   *   <li>Then return not test {@code foo}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperProperty#getValidationPredicate()}
   */
  @Test
  @DisplayName("Test getValidationPredicate(); then return not test 'foo'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.util.function.Predicate SleeperProperty.getValidationPredicate()"})
  void testGetValidationPredicate_thenReturnNotTestFoo() {
    // Arrange, Act and Assert
    assertFalse(
        AsyncIngestPartitionFileWriterProperty.ASYNC_INGEST_CRT_PART_SIZE_BYTES.getValidationPredicate().test("foo"));
  }

  /**
   * Test {@link SleeperProperty#toEnvironmentVariable()}.
   * <p>
   * Method under test: {@link SleeperProperty#toEnvironmentVariable()}
   */
  @Test
  @DisplayName("Test toEnvironmentVariable()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.lang.String SleeperProperty.toEnvironmentVariable()"})
  void testToEnvironmentVariable() {
    // Arrange, Act and Assert
    assertEquals("MADE_UP", (new DummySleeperProperty()).toEnvironmentVariable());
  }

  /**
   * Test {@link SleeperProperty#isIncludedInTemplate()}.
   * <ul>
   *   <li>Given {@link DummySleeperProperty} (default constructor).</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperProperty#isIncludedInTemplate()}
   */
  @Test
  @DisplayName("Test isIncludedInTemplate(); given DummySleeperProperty (default constructor); then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperProperty.isIncludedInTemplate()"})
  void testIsIncludedInTemplate_givenDummySleeperProperty_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new DummySleeperProperty()).isIncludedInTemplate());
  }

  /**
   * Test {@link SleeperProperty#isIncludedInTemplate()}.
   * <ul>
   *   <li>Given {@link ArrayListIngestProperty#MAX_IN_MEMORY_BATCH_SIZE}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperProperty#isIncludedInTemplate()}
   */
  @Test
  @DisplayName("Test isIncludedInTemplate(); given MAX_IN_MEMORY_BATCH_SIZE; then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperProperty.isIncludedInTemplate()"})
  void testIsIncludedInTemplate_givenMax_in_memory_batch_size_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(ArrayListIngestProperty.MAX_IN_MEMORY_BATCH_SIZE.isIncludedInTemplate());
  }

  /**
   * Test {@link SleeperProperty#isIncludedInBasicTemplate()}.
   * <p>
   * Method under test: {@link SleeperProperty#isIncludedInBasicTemplate()}
   */
  @Test
  @DisplayName("Test isIncludedInBasicTemplate()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperProperty.isIncludedInBasicTemplate()"})
  void testIsIncludedInBasicTemplate() {
    // Arrange, Act and Assert
    assertFalse((new DummySleeperProperty()).isIncludedInBasicTemplate());
  }

  /**
   * Test {@link SleeperProperty#isIgnoreEmptyValue()}.
   * <ul>
   *   <li>Given {@link DummySleeperProperty} (default constructor).</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperProperty#isIgnoreEmptyValue()}
   */
  @Test
  @DisplayName("Test isIgnoreEmptyValue(); given DummySleeperProperty (default constructor); then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperProperty.isIgnoreEmptyValue()"})
  void testIsIgnoreEmptyValue_givenDummySleeperProperty_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue((new DummySleeperProperty()).isIgnoreEmptyValue());
  }

  /**
   * Test {@link SleeperProperty#isIgnoreEmptyValue()}.
   * <ul>
   *   <li>Then calls {@link SleeperProperty#isIgnoreEmptyValue()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperProperty#isIgnoreEmptyValue()}
   */
  @Test
  @DisplayName("Test isIgnoreEmptyValue(); then calls isIgnoreEmptyValue()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SleeperProperty.isIgnoreEmptyValue()"})
  void testIsIgnoreEmptyValue_thenCallsIsIgnoreEmptyValue() {
    // Arrange
    UserDefinedInstanceProperty userDefinedInstanceProperty = mock(UserDefinedInstanceProperty.class);
    when(userDefinedInstanceProperty.isIgnoreEmptyValue()).thenReturn(false);

    // Act
    userDefinedInstanceProperty.isIgnoreEmptyValue();

    // Assert
    verify(userDefinedInstanceProperty).isIgnoreEmptyValue();
  }
}
