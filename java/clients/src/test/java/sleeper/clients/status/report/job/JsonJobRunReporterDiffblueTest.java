package sleeper.clients.status.report.job;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Function;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.tracker.job.run.JobRun;
import sleeper.core.tracker.job.run.JobRuns;

class JsonJobRunReporterDiffblueTest {
  /**
   * Test {@link JsonJobRunReporter#jobRunsJsonSerializer(Function)}.
   * <p>
   * Method under test: {@link JsonJobRunReporter#jobRunsJsonSerializer(Function)}
   */
  @Test
  @DisplayName("Test jobRunsJsonSerializer(Function)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"JsonSerializer JsonJobRunReporter.jobRunsJsonSerializer(Function)"})
  void testJobRunsJsonSerializer() {
    // Arrange and Act
    JsonSerializer<JobRuns> actualJobRunsJsonSerializerResult = JsonJobRunReporter
        .jobRunsJsonSerializer(mock(Function.class));
    JobRuns jobRuns = mock(JobRuns.class);
    when(jobRuns.getRunsLatestFirst()).thenReturn(new ArrayList<>());
    JsonElement actualSerializeResult = actualJobRunsJsonSerializerResult.serialize(jobRuns, new PlaceholderForType(1),
        mock(JsonSerializationContext.class));

    // Assert
    verify(jobRuns).getRunsLatestFirst();
    assertTrue(actualSerializeResult instanceof JsonArray);
    assertEquals(0, ((JsonArray) actualSerializeResult).size());
    assertFalse(actualSerializeResult.isJsonNull());
    assertFalse(actualSerializeResult.isJsonObject());
    assertFalse(actualSerializeResult.isJsonPrimitive());
    assertFalse(((JsonArray) actualSerializeResult).iterator().hasNext());
    assertTrue(((JsonArray) actualSerializeResult).isEmpty());
    assertTrue(actualSerializeResult.isJsonArray());
    assertSame(actualSerializeResult, actualSerializeResult.getAsJsonArray());
  }

  /**
   * Test {@link JsonJobRunReporter#jobRunsJsonSerializer(Function)}.
   * <p>
   * Method under test: {@link JsonJobRunReporter#jobRunsJsonSerializer(Function)}
   */
  @Test
  @DisplayName("Test jobRunsJsonSerializer(Function)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"JsonSerializer JsonJobRunReporter.jobRunsJsonSerializer(Function)"})
  void testJobRunsJsonSerializer2() {
    // Arrange and Act
    JsonSerializer<JobRuns> actualJobRunsJsonSerializerResult = JsonJobRunReporter
        .jobRunsJsonSerializer(mock(Function.class));
    ArrayList<JobRun> jobRunList = new ArrayList<>();
    JobRun buildResult = JobRun.builder().taskId("42").build();
    jobRunList.add(buildResult);
    JobRuns jobRuns = mock(JobRuns.class);
    when(jobRuns.getRunsLatestFirst()).thenReturn(jobRunList);
    JsonElement actualSerializeResult = actualJobRunsJsonSerializerResult.serialize(jobRuns, new PlaceholderForType(1),
        mock(JsonSerializationContext.class));

    // Assert
    verify(jobRuns).getRunsLatestFirst();
    assertTrue(actualSerializeResult instanceof JsonArray);
    Iterator<JsonElement> iteratorResult = ((JsonArray) actualSerializeResult).iterator();
    JsonElement nextResult = iteratorResult.next();
    assertTrue(nextResult instanceof JsonObject);
    assertEquals(1, ((JsonArray) actualSerializeResult).size());
    assertEquals(2, ((JsonObject) nextResult).size());
    assertFalse(((JsonArray) actualSerializeResult).isEmpty());
    assertFalse(nextResult.isJsonArray());
    assertFalse(nextResult.isJsonNull());
    assertFalse(nextResult.isJsonPrimitive());
    assertFalse(((JsonObject) nextResult).isEmpty());
    assertFalse(iteratorResult.hasNext());
    assertTrue(nextResult.isJsonObject());
    assertSame(nextResult, nextResult.getAsJsonObject());
  }
}
