package sleeper.clients.status.report.job;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.fasterxml.jackson.databind.type.PlaceholderForType;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.time.LocalDate;
import java.time.ZoneOffset;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.clients.status.report.statestore.PageThroughLogs;
import sleeper.core.tracker.job.run.JobRunSummary;
import sleeper.core.tracker.job.run.JobRunTime;
import sleeper.core.tracker.job.run.RecordsProcessed;

class JsonJobRunSummaryDiffblueTest {
  /**
   * Test {@link JsonJobRunSummary#serializer()}.
   * <p>
   * Method under test: {@link JsonJobRunSummary#serializer()}
   */
  @Test
  @DisplayName("Test serializer()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"JsonSerializer JsonJobRunSummary.serializer()"})
  void testSerializer() {
    // Arrange and Act
    JsonSerializer<JobRunSummary> actualSerializerResult = JsonJobRunSummary.serializer();
    RecordsProcessed recordsProcessed = new RecordsProcessed(1L, 1L);

    JobRunSummary jobRunSummary = new JobRunSummary(recordsProcessed, new JobRunTime(
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(), PageThroughLogs.PAGE_MIN_AGE));

    PlaceholderForType placeholderForType = new PlaceholderForType(1);
    JsonSerializationContext jsonSerializationContext = mock(JsonSerializationContext.class);
    when(jsonSerializationContext.serialize(Mockito.<Object>any())).thenReturn(new JsonArray(3));
    JsonElement actualSerializeResult = actualSerializerResult.serialize(jobRunSummary, placeholderForType,
        jsonSerializationContext);

    // Assert
    verify(jsonSerializationContext, atLeast(1)).serialize(Mockito.<Object>any());
    assertTrue(actualSerializeResult instanceof JsonObject);
    assertEquals(4, ((JsonObject) actualSerializeResult).size());
    assertFalse(actualSerializeResult.isJsonArray());
    assertFalse(actualSerializeResult.isJsonNull());
    assertFalse(actualSerializeResult.isJsonPrimitive());
    assertFalse(((JsonObject) actualSerializeResult).isEmpty());
    assertTrue(actualSerializeResult.isJsonObject());
    assertSame(actualSerializeResult, actualSerializeResult.getAsJsonObject());
  }

  /**
   * Test {@link JsonJobRunSummary#serializer()}.
   * <p>
   * Method under test: {@link JsonJobRunSummary#serializer()}
   */
  @Test
  @DisplayName("Test serializer()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"JsonSerializer JsonJobRunSummary.serializer()"})
  void testSerializer2() {
    // Arrange and Act
    JsonSerializer<JobRunSummary> actualSerializerResult = JsonJobRunSummary.serializer();
    RecordsProcessed recordsProcessed = new RecordsProcessed(1L, 1L);

    JobRunSummary jobRunSummary = new JobRunSummary(recordsProcessed, new JobRunTime(
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(), PageThroughLogs.PAGE_MIN_AGE));

    PlaceholderForType placeholderForType = new PlaceholderForType(1);
    JsonSerializationContext jsonSerializationContext = mock(JsonSerializationContext.class);
    when(jsonSerializationContext.serialize(Mockito.<Object>any())).thenReturn(null);
    JsonElement actualSerializeResult = actualSerializerResult.serialize(jobRunSummary, placeholderForType,
        jsonSerializationContext);

    // Assert
    verify(jsonSerializationContext, atLeast(1)).serialize(Mockito.<Object>any());
    assertTrue(actualSerializeResult instanceof JsonObject);
    assertEquals(4, ((JsonObject) actualSerializeResult).size());
    assertFalse(actualSerializeResult.isJsonArray());
    assertFalse(actualSerializeResult.isJsonNull());
    assertFalse(actualSerializeResult.isJsonPrimitive());
    assertFalse(((JsonObject) actualSerializeResult).isEmpty());
    assertTrue(actualSerializeResult.isJsonObject());
    assertSame(actualSerializeResult, actualSerializeResult.getAsJsonObject());
  }
}
