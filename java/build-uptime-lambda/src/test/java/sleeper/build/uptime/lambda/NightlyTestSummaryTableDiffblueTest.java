package sleeper.build.uptime.lambda;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.google.gson.Gson;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class NightlyTestSummaryTableDiffblueTest {
  /**
   * Test {@link NightlyTestSummaryTable#empty()}.
   * <p>
   * Method under test: {@link NightlyTestSummaryTable#empty()}
   */
  @Test
  @DisplayName("Test empty()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"NightlyTestSummaryTable NightlyTestSummaryTable.empty()"})
  void testEmpty() {
    // Arrange, Act and Assert
    assertEquals("{\"executions\":[]}", NightlyTestSummaryTable.empty().toJson());
  }

  /**
   * Test {@link NightlyTestSummaryTable#fromJson(String)}.
   * <ul>
   *   <li>When empty string.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NightlyTestSummaryTable#fromJson(String)}
   */
  @Test
  @DisplayName("Test fromJson(String); when empty string; then return 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"NightlyTestSummaryTable NightlyTestSummaryTable.fromJson(String)"})
  void testFromJson_whenEmptyString_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(NightlyTestSummaryTable.fromJson(""));
  }

  /**
   * Test {@link NightlyTestSummaryTable#fromJson(String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NightlyTestSummaryTable#fromJson(String)}
   */
  @Test
  @DisplayName("Test fromJson(String); when 'null'; then return 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"NightlyTestSummaryTable NightlyTestSummaryTable.fromJson(String)"})
  void testFromJson_whenNull_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(NightlyTestSummaryTable.fromJson(null));
  }

  /**
   * Test {@link NightlyTestSummaryTable#fromS3(GetS3ObjectAsString, String)}.
   * <ul>
   *   <li>Given empty.</li>
   *   <li>When {@link GetS3ObjectAsString} {@link GetS3ObjectAsString#getS3ObjectAsString(String, String)} return empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link NightlyTestSummaryTable#fromS3(GetS3ObjectAsString, String)}
   */
  @Test
  @DisplayName("Test fromS3(GetS3ObjectAsString, String); given empty; when GetS3ObjectAsString getS3ObjectAsString(String, String) return empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"NightlyTestSummaryTable NightlyTestSummaryTable.fromS3(GetS3ObjectAsString, String)"})
  void testFromS3_givenEmpty_whenGetS3ObjectAsStringGetS3ObjectAsStringReturnEmpty() {
    // Arrange
    GetS3ObjectAsString s3 = mock(GetS3ObjectAsString.class);
    Optional<String> emptyResult = Optional.empty();
    when(s3.getS3ObjectAsString(Mockito.<String>any(), Mockito.<String>any())).thenReturn(emptyResult);

    // Act
    NightlyTestSummaryTable actualFromS3Result = NightlyTestSummaryTable.fromS3(s3, "s3://bucket-name/object-key");

    // Assert
    verify(s3).getS3ObjectAsString(eq("s3://bucket-name/object-key"), eq("summary.json"));
    assertEquals("{\"executions\":[]}", actualFromS3Result.toJson());
  }

  /**
   * Test {@link NightlyTestSummaryTable#fromS3(GetS3ObjectAsString, String)}.
   * <ul>
   *   <li>Given {@link Optional} with empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link NightlyTestSummaryTable#fromS3(GetS3ObjectAsString, String)}
   */
  @Test
  @DisplayName("Test fromS3(GetS3ObjectAsString, String); given Optional with empty string")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"NightlyTestSummaryTable NightlyTestSummaryTable.fromS3(GetS3ObjectAsString, String)"})
  void testFromS3_givenOptionalWithEmptyString() {
    // Arrange
    GetS3ObjectAsString s3 = mock(GetS3ObjectAsString.class);
    Optional<String> ofResult = Optional.of("");
    when(s3.getS3ObjectAsString(Mockito.<String>any(), Mockito.<String>any())).thenReturn(ofResult);

    // Act
    NightlyTestSummaryTable actualFromS3Result = NightlyTestSummaryTable.fromS3(s3, "s3://bucket-name/object-key");

    // Assert
    verify(s3).getS3ObjectAsString(eq("s3://bucket-name/object-key"), eq("summary.json"));
    assertEquals("{\"executions\":[]}", actualFromS3Result.toJson());
  }

  /**
   * Test {@link NightlyTestSummaryTable#toJson()}.
   * <p>
   * Method under test: {@link NightlyTestSummaryTable#toJson()}
   */
  @Test
  @DisplayName("Test toJson()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String NightlyTestSummaryTable.toJson()"})
  void testToJson() {
    // Arrange, Act and Assert
    assertEquals("{\"executions\":[]}", NightlyTestSummaryTable.empty().toJson());
  }

  /**
   * Test {@link NightlyTestSummaryTable#containsTestFromToday(Instant)}.
   * <p>
   * Method under test: {@link NightlyTestSummaryTable#containsTestFromToday(Instant)}
   */
  @Test
  @DisplayName("Test containsTestFromToday(Instant)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean NightlyTestSummaryTable.containsTestFromToday(Instant)"})
  void testContainsTestFromToday() {
    // Arrange
    NightlyTestSummaryTable emptyResult = NightlyTestSummaryTable.empty();

    // Act and Assert
    assertFalse(
        emptyResult.containsTestFromToday(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
  }

  /**
   * Test {@link NightlyTestSummaryTable#createGson()}.
   * <p>
   * Method under test: {@link NightlyTestSummaryTable#createGson()}
   */
  @Test
  @DisplayName("Test createGson()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Gson NightlyTestSummaryTable.createGson()"})
  void testCreateGson() {
    // Arrange and Act
    Gson actualCreateGsonResult = NightlyTestSummaryTable.createGson();

    // Assert
    assertFalse(actualCreateGsonResult.serializeNulls());
    assertTrue(actualCreateGsonResult.htmlSafe());
  }
}
