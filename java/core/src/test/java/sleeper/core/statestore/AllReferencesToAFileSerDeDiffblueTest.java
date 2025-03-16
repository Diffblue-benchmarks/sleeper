package sleeper.core.statestore;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.fasterxml.jackson.databind.type.PlaceholderForType;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.statestore.AllReferencesToAFileSerDe.NoUpdateTimesGsonSerDe;
import sleeper.core.statestore.FileReference.Builder;

class AllReferencesToAFileSerDeDiffblueTest {
  /**
   * Test NoUpdateTimesGsonSerDe {@link NoUpdateTimesGsonSerDe#deserialize(JsonElement, Type, JsonDeserializationContext)}.
   * <ul>
   *   <li>Then return LastStateStoreUpdateTime is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NoUpdateTimesGsonSerDe#deserialize(JsonElement, Type, JsonDeserializationContext)}
   */
  @Test
  @DisplayName("Test NoUpdateTimesGsonSerDe deserialize(JsonElement, Type, JsonDeserializationContext); then return LastStateStoreUpdateTime is 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "AllReferencesToAFile NoUpdateTimesGsonSerDe.deserialize(JsonElement, Type, JsonDeserializationContext)"})
  void testNoUpdateTimesGsonSerDeDeserialize_thenReturnLastStateStoreUpdateTimeIsNull() throws JsonParseException {
    // Arrange
    NoUpdateTimesGsonSerDe noUpdateTimesResult = AllReferencesToAFileSerDe.noUpdateTimes();

    JsonObject json = new JsonObject();
    json.add("references", new JsonArray(3));
    json.addProperty("filename", false);

    // Act
    AllReferencesToAFile actualDeserializeResult = noUpdateTimesResult.deserialize(json, new PlaceholderForType(1),
        mock(JsonDeserializationContext.class));

    // Assert
    assertNull(actualDeserializeResult.getLastStateStoreUpdateTime());
    assertEquals(0, actualDeserializeResult.getReferenceCount());
    assertTrue(actualDeserializeResult.getReferences().isEmpty());
    String expectedFilename = Boolean.FALSE.toString();
    assertEquals(expectedFilename, actualDeserializeResult.getFilename());
  }

  /**
   * Test NoUpdateTimesGsonSerDe {@link NoUpdateTimesGsonSerDe#serialize(AllReferencesToAFile, Type, JsonSerializationContext)} with {@code AllReferencesToAFile}, {@code Type}, {@code JsonSerializationContext}.
   * <p>
   * Method under test: {@link NoUpdateTimesGsonSerDe#serialize(AllReferencesToAFile, Type, JsonSerializationContext)}
   */
  @Test
  @DisplayName("Test NoUpdateTimesGsonSerDe serialize(AllReferencesToAFile, Type, JsonSerializationContext) with 'AllReferencesToAFile', 'Type', 'JsonSerializationContext'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "JsonElement NoUpdateTimesGsonSerDe.serialize(AllReferencesToAFile, Type, JsonSerializationContext)"})
  void testNoUpdateTimesGsonSerDeSerializeWithAllReferencesToAFileTypeJsonSerializationContext() {
    // Arrange
    NoUpdateTimesGsonSerDe noUpdateTimesResult = AllReferencesToAFileSerDe.noUpdateTimes();
    AllReferencesToAFile.Builder filenameResult = AllReferencesToAFile.builder().filename("foo.txt");
    AllReferencesToAFile.Builder lastStateStoreUpdateTimeResult = filenameResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    AllReferencesToAFile file = lastStateStoreUpdateTimeResult.references(new ArrayList<>()).build();

    // Act
    JsonElement actualSerializeResult = noUpdateTimesResult.serialize(file, new PlaceholderForType(1),
        mock(JsonSerializationContext.class));

    // Assert
    assertTrue(actualSerializeResult instanceof JsonObject);
    assertEquals(2, ((JsonObject) actualSerializeResult).size());
    assertFalse(actualSerializeResult.isJsonArray());
    assertFalse(actualSerializeResult.isJsonNull());
    assertFalse(actualSerializeResult.isJsonPrimitive());
    assertFalse(((JsonObject) actualSerializeResult).isEmpty());
    assertTrue(actualSerializeResult.isJsonObject());
    assertSame(actualSerializeResult, actualSerializeResult.getAsJsonObject());
  }

  /**
   * Test NoUpdateTimesGsonSerDe {@link NoUpdateTimesGsonSerDe#serialize(AllReferencesToAFile, Type, JsonSerializationContext)} with {@code AllReferencesToAFile}, {@code Type}, {@code JsonSerializationContext}.
   * <p>
   * Method under test: {@link NoUpdateTimesGsonSerDe#serialize(AllReferencesToAFile, Type, JsonSerializationContext)}
   */
  @Test
  @DisplayName("Test NoUpdateTimesGsonSerDe serialize(AllReferencesToAFile, Type, JsonSerializationContext) with 'AllReferencesToAFile', 'Type', 'JsonSerializationContext'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "JsonElement NoUpdateTimesGsonSerDe.serialize(AllReferencesToAFile, Type, JsonSerializationContext)"})
  void testNoUpdateTimesGsonSerDeSerializeWithAllReferencesToAFileTypeJsonSerializationContext2() {
    // Arrange
    NoUpdateTimesGsonSerDe noUpdateTimesResult = AllReferencesToAFileSerDe.noUpdateTimes();
    AllReferencesToAFile file = mock(AllReferencesToAFile.class);
    when(file.getFilename()).thenReturn("foo.txt");
    when(file.getReferences()).thenReturn(new ArrayList<>());

    // Act
    JsonElement actualSerializeResult = noUpdateTimesResult.serialize(file, new PlaceholderForType(1),
        mock(JsonSerializationContext.class));

    // Assert
    verify(file).getFilename();
    verify(file).getReferences();
    assertTrue(actualSerializeResult instanceof JsonObject);
    assertEquals(2, ((JsonObject) actualSerializeResult).size());
    assertFalse(actualSerializeResult.isJsonArray());
    assertFalse(actualSerializeResult.isJsonNull());
    assertFalse(actualSerializeResult.isJsonPrimitive());
    assertFalse(((JsonObject) actualSerializeResult).isEmpty());
    assertTrue(actualSerializeResult.isJsonObject());
    assertSame(actualSerializeResult, actualSerializeResult.getAsJsonObject());
  }

  /**
   * Test NoUpdateTimesGsonSerDe {@link NoUpdateTimesGsonSerDe#serialize(AllReferencesToAFile, Type, JsonSerializationContext)} with {@code AllReferencesToAFile}, {@code Type}, {@code JsonSerializationContext}.
   * <p>
   * Method under test: {@link NoUpdateTimesGsonSerDe#serialize(AllReferencesToAFile, Type, JsonSerializationContext)}
   */
  @Test
  @DisplayName("Test NoUpdateTimesGsonSerDe serialize(AllReferencesToAFile, Type, JsonSerializationContext) with 'AllReferencesToAFile', 'Type', 'JsonSerializationContext'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "JsonElement NoUpdateTimesGsonSerDe.serialize(AllReferencesToAFile, Type, JsonSerializationContext)"})
  void testNoUpdateTimesGsonSerDeSerializeWithAllReferencesToAFileTypeJsonSerializationContext3() {
    // Arrange
    NoUpdateTimesGsonSerDe noUpdateTimesResult = AllReferencesToAFileSerDe.noUpdateTimes();

    ArrayList<FileReference> fileReferenceList = new ArrayList<>();
    Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference buildResult = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    fileReferenceList.add(buildResult);
    AllReferencesToAFile file = mock(AllReferencesToAFile.class);
    when(file.getFilename()).thenReturn("foo.txt");
    when(file.getReferences()).thenReturn(fileReferenceList);
    PlaceholderForType type = new PlaceholderForType(1);
    JsonSerializationContext context = mock(JsonSerializationContext.class);
    when(context.serialize(Mockito.<Object>any())).thenReturn(new JsonObject());

    // Act
    JsonElement actualSerializeResult = noUpdateTimesResult.serialize(file, type, context);

    // Assert
    verify(context).serialize(isA(Object.class));
    verify(file).getFilename();
    verify(file).getReferences();
    assertTrue(actualSerializeResult instanceof JsonObject);
    assertEquals(2, ((JsonObject) actualSerializeResult).size());
    assertFalse(actualSerializeResult.isJsonArray());
    assertFalse(actualSerializeResult.isJsonNull());
    assertFalse(actualSerializeResult.isJsonPrimitive());
    assertFalse(((JsonObject) actualSerializeResult).isEmpty());
    assertTrue(actualSerializeResult.isJsonObject());
    assertSame(actualSerializeResult, actualSerializeResult.getAsJsonObject());
  }

  /**
   * Test NoUpdateTimesGsonSerDe {@link NoUpdateTimesGsonSerDe#serialize(AllReferencesToAFile, Type, JsonSerializationContext)} with {@code AllReferencesToAFile}, {@code Type}, {@code JsonSerializationContext}.
   * <p>
   * Method under test: {@link NoUpdateTimesGsonSerDe#serialize(AllReferencesToAFile, Type, JsonSerializationContext)}
   */
  @Test
  @DisplayName("Test NoUpdateTimesGsonSerDe serialize(AllReferencesToAFile, Type, JsonSerializationContext) with 'AllReferencesToAFile', 'Type', 'JsonSerializationContext'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "JsonElement NoUpdateTimesGsonSerDe.serialize(AllReferencesToAFile, Type, JsonSerializationContext)"})
  void testNoUpdateTimesGsonSerDeSerializeWithAllReferencesToAFileTypeJsonSerializationContext4() {
    // Arrange
    NoUpdateTimesGsonSerDe noUpdateTimesResult = AllReferencesToAFileSerDe.noUpdateTimes();

    ArrayList<FileReference> fileReferenceList = new ArrayList<>();
    Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference buildResult = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    fileReferenceList.add(buildResult);
    AllReferencesToAFile file = mock(AllReferencesToAFile.class);
    when(file.getFilename()).thenReturn("foo.txt");
    when(file.getReferences()).thenReturn(fileReferenceList);
    PlaceholderForType type = new PlaceholderForType(1);
    JsonSerializationContext context = mock(JsonSerializationContext.class);
    when(context.serialize(Mockito.<Object>any())).thenThrow(new JsonParseException("filename"));

    // Act and Assert
    assertThrows(JsonParseException.class, () -> noUpdateTimesResult.serialize(file, type, context));
    verify(context).serialize(isA(Object.class));
    verify(file).getFilename();
    verify(file).getReferences();
  }

  /**
   * Test NoUpdateTimesGsonSerDe {@link NoUpdateTimesGsonSerDe#serialize(AllReferencesToAFile, Type, JsonSerializationContext)} with {@code AllReferencesToAFile}, {@code Type}, {@code JsonSerializationContext}.
   * <p>
   * Method under test: {@link NoUpdateTimesGsonSerDe#serialize(AllReferencesToAFile, Type, JsonSerializationContext)}
   */
  @Test
  @DisplayName("Test NoUpdateTimesGsonSerDe serialize(AllReferencesToAFile, Type, JsonSerializationContext) with 'AllReferencesToAFile', 'Type', 'JsonSerializationContext'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "JsonElement NoUpdateTimesGsonSerDe.serialize(AllReferencesToAFile, Type, JsonSerializationContext)"})
  void testNoUpdateTimesGsonSerDeSerializeWithAllReferencesToAFileTypeJsonSerializationContext5() {
    // Arrange
    NoUpdateTimesGsonSerDe noUpdateTimesResult = AllReferencesToAFileSerDe.noUpdateTimes();

    ArrayList<FileReference> fileReferenceList = new ArrayList<>();
    Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference buildResult = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    fileReferenceList.add(buildResult);
    AllReferencesToAFile file = mock(AllReferencesToAFile.class);
    when(file.getFilename()).thenReturn("foo.txt");
    when(file.getReferences()).thenReturn(fileReferenceList);
    PlaceholderForType type = new PlaceholderForType(1);

    JsonObject jsonObject = new JsonObject();
    jsonObject.add("references", new JsonArray(3));
    JsonSerializationContext context = mock(JsonSerializationContext.class);
    when(context.serialize(Mockito.<Object>any())).thenReturn(jsonObject);

    // Act
    JsonElement actualSerializeResult = noUpdateTimesResult.serialize(file, type, context);

    // Assert
    verify(context).serialize(isA(Object.class));
    verify(file).getFilename();
    verify(file).getReferences();
    assertTrue(actualSerializeResult instanceof JsonObject);
    assertEquals(2, ((JsonObject) actualSerializeResult).size());
    assertFalse(actualSerializeResult.isJsonArray());
    assertFalse(actualSerializeResult.isJsonNull());
    assertFalse(actualSerializeResult.isJsonPrimitive());
    assertFalse(((JsonObject) actualSerializeResult).isEmpty());
    assertTrue(actualSerializeResult.isJsonObject());
    assertSame(actualSerializeResult, actualSerializeResult.getAsJsonObject());
  }

  /**
   * Test NoUpdateTimesGsonSerDe {@link NoUpdateTimesGsonSerDe#serialize(AllReferencesToAFile, Type, JsonSerializationContext)} with {@code AllReferencesToAFile}, {@code Type}, {@code JsonSerializationContext}.
   * <p>
   * Method under test: {@link NoUpdateTimesGsonSerDe#serialize(AllReferencesToAFile, Type, JsonSerializationContext)}
   */
  @Test
  @DisplayName("Test NoUpdateTimesGsonSerDe serialize(AllReferencesToAFile, Type, JsonSerializationContext) with 'AllReferencesToAFile', 'Type', 'JsonSerializationContext'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "JsonElement NoUpdateTimesGsonSerDe.serialize(AllReferencesToAFile, Type, JsonSerializationContext)"})
  void testNoUpdateTimesGsonSerDeSerializeWithAllReferencesToAFileTypeJsonSerializationContext6() {
    // Arrange
    NoUpdateTimesGsonSerDe noUpdateTimesResult = AllReferencesToAFileSerDe.noUpdateTimes();

    ArrayList<FileReference> fileReferenceList = new ArrayList<>();
    Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference buildResult = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    fileReferenceList.add(buildResult);
    AllReferencesToAFile file = mock(AllReferencesToAFile.class);
    when(file.getFilename()).thenReturn("foo.txt");
    when(file.getReferences()).thenReturn(fileReferenceList);
    PlaceholderForType type = new PlaceholderForType(1);

    JsonObject jsonObject = new JsonObject();
    jsonObject.add("Property", new JsonArray(3));
    jsonObject.add("references", new JsonArray(3));
    JsonSerializationContext context = mock(JsonSerializationContext.class);
    when(context.serialize(Mockito.<Object>any())).thenReturn(jsonObject);

    // Act
    JsonElement actualSerializeResult = noUpdateTimesResult.serialize(file, type, context);

    // Assert
    verify(context).serialize(isA(Object.class));
    verify(file).getFilename();
    verify(file).getReferences();
    assertTrue(actualSerializeResult instanceof JsonObject);
    assertEquals(2, ((JsonObject) actualSerializeResult).size());
    assertFalse(actualSerializeResult.isJsonArray());
    assertFalse(actualSerializeResult.isJsonNull());
    assertFalse(actualSerializeResult.isJsonPrimitive());
    assertFalse(((JsonObject) actualSerializeResult).isEmpty());
    assertTrue(actualSerializeResult.isJsonObject());
    assertSame(actualSerializeResult, actualSerializeResult.getAsJsonObject());
  }

  /**
   * Test NoUpdateTimesGsonSerDe {@link NoUpdateTimesGsonSerDe#serialize(AllReferencesToAFile, Type, JsonSerializationContext)} with {@code AllReferencesToAFile}, {@code Type}, {@code JsonSerializationContext}.
   * <p>
   * Method under test: {@link NoUpdateTimesGsonSerDe#serialize(AllReferencesToAFile, Type, JsonSerializationContext)}
   */
  @Test
  @DisplayName("Test NoUpdateTimesGsonSerDe serialize(AllReferencesToAFile, Type, JsonSerializationContext) with 'AllReferencesToAFile', 'Type', 'JsonSerializationContext'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "JsonElement NoUpdateTimesGsonSerDe.serialize(AllReferencesToAFile, Type, JsonSerializationContext)"})
  void testNoUpdateTimesGsonSerDeSerializeWithAllReferencesToAFileTypeJsonSerializationContext7() {
    // Arrange
    NoUpdateTimesGsonSerDe noUpdateTimesResult = AllReferencesToAFileSerDe.noUpdateTimes();

    ArrayList<FileReference> fileReferenceList = new ArrayList<>();
    Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference buildResult = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    fileReferenceList.add(buildResult);
    AllReferencesToAFile file = mock(AllReferencesToAFile.class);
    when(file.getFilename()).thenReturn("foo.txt");
    when(file.getReferences()).thenReturn(fileReferenceList);
    PlaceholderForType type = new PlaceholderForType(1);

    JsonObject jsonObject = new JsonObject();
    jsonObject.add("filename", new JsonArray(3));
    jsonObject.add("Property", new JsonArray(3));
    jsonObject.add("references", new JsonArray(3));
    JsonSerializationContext context = mock(JsonSerializationContext.class);
    when(context.serialize(Mockito.<Object>any())).thenReturn(jsonObject);

    // Act
    JsonElement actualSerializeResult = noUpdateTimesResult.serialize(file, type, context);

    // Assert
    verify(context).serialize(isA(Object.class));
    verify(file).getFilename();
    verify(file).getReferences();
    assertTrue(actualSerializeResult instanceof JsonObject);
    assertEquals(2, ((JsonObject) actualSerializeResult).size());
    assertFalse(actualSerializeResult.isJsonArray());
    assertFalse(actualSerializeResult.isJsonNull());
    assertFalse(actualSerializeResult.isJsonPrimitive());
    assertFalse(((JsonObject) actualSerializeResult).isEmpty());
    assertTrue(actualSerializeResult.isJsonObject());
    assertSame(actualSerializeResult, actualSerializeResult.getAsJsonObject());
  }

  /**
   * Test NoUpdateTimesGsonSerDe {@link NoUpdateTimesGsonSerDe#serialize(AllReferencesToAFile, Type, JsonSerializationContext)} with {@code AllReferencesToAFile}, {@code Type}, {@code JsonSerializationContext}.
   * <p>
   * Method under test: {@link NoUpdateTimesGsonSerDe#serialize(AllReferencesToAFile, Type, JsonSerializationContext)}
   */
  @Test
  @DisplayName("Test NoUpdateTimesGsonSerDe serialize(AllReferencesToAFile, Type, JsonSerializationContext) with 'AllReferencesToAFile', 'Type', 'JsonSerializationContext'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "JsonElement NoUpdateTimesGsonSerDe.serialize(AllReferencesToAFile, Type, JsonSerializationContext)"})
  void testNoUpdateTimesGsonSerDeSerializeWithAllReferencesToAFileTypeJsonSerializationContext8() {
    // Arrange
    NoUpdateTimesGsonSerDe noUpdateTimesResult = AllReferencesToAFileSerDe.noUpdateTimes();

    ArrayList<FileReference> fileReferenceList = new ArrayList<>();
    Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference buildResult = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    fileReferenceList.add(buildResult);
    AllReferencesToAFile file = mock(AllReferencesToAFile.class);
    when(file.getFilename()).thenReturn("foo.txt");
    when(file.getReferences()).thenReturn(fileReferenceList);
    PlaceholderForType type = new PlaceholderForType(1);

    JsonObject jsonObject = new JsonObject();
    jsonObject.add("Property", new JsonArray(3));
    jsonObject.add("filename", new JsonArray(3));
    JsonSerializationContext context = mock(JsonSerializationContext.class);
    when(context.serialize(Mockito.<Object>any())).thenReturn(jsonObject);

    // Act
    JsonElement actualSerializeResult = noUpdateTimesResult.serialize(file, type, context);

    // Assert
    verify(context).serialize(isA(Object.class));
    verify(file).getFilename();
    verify(file).getReferences();
    assertTrue(actualSerializeResult instanceof JsonObject);
    assertEquals(2, ((JsonObject) actualSerializeResult).size());
    assertFalse(actualSerializeResult.isJsonArray());
    assertFalse(actualSerializeResult.isJsonNull());
    assertFalse(actualSerializeResult.isJsonPrimitive());
    assertFalse(((JsonObject) actualSerializeResult).isEmpty());
    assertTrue(actualSerializeResult.isJsonObject());
    assertSame(actualSerializeResult, actualSerializeResult.getAsJsonObject());
  }

  /**
   * Test NoUpdateTimesGsonSerDe {@link NoUpdateTimesGsonSerDe#serialize(AllReferencesToAFile, Type, JsonSerializationContext)} with {@code AllReferencesToAFile}, {@code Type}, {@code JsonSerializationContext}.
   * <p>
   * Method under test: {@link NoUpdateTimesGsonSerDe#serialize(AllReferencesToAFile, Type, JsonSerializationContext)}
   */
  @Test
  @DisplayName("Test NoUpdateTimesGsonSerDe serialize(AllReferencesToAFile, Type, JsonSerializationContext) with 'AllReferencesToAFile', 'Type', 'JsonSerializationContext'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "JsonElement NoUpdateTimesGsonSerDe.serialize(AllReferencesToAFile, Type, JsonSerializationContext)"})
  void testNoUpdateTimesGsonSerDeSerializeWithAllReferencesToAFileTypeJsonSerializationContext9() {
    // Arrange
    NoUpdateTimesGsonSerDe noUpdateTimesResult = AllReferencesToAFileSerDe.noUpdateTimes();

    ArrayList<FileReference> fileReferenceList = new ArrayList<>();
    Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference buildResult = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    fileReferenceList.add(buildResult);
    AllReferencesToAFile file = mock(AllReferencesToAFile.class);
    when(file.getFilename()).thenReturn("foo.txt");
    when(file.getReferences()).thenReturn(fileReferenceList);
    PlaceholderForType type = new PlaceholderForType(1);

    JsonObject jsonObject = new JsonObject();
    jsonObject.add("filename", new JsonArray(3));
    jsonObject.add("filename", new JsonArray(3));
    jsonObject.add("references", new JsonArray(3));
    JsonSerializationContext context = mock(JsonSerializationContext.class);
    when(context.serialize(Mockito.<Object>any())).thenReturn(jsonObject);

    // Act
    JsonElement actualSerializeResult = noUpdateTimesResult.serialize(file, type, context);

    // Assert
    verify(context).serialize(isA(Object.class));
    verify(file).getFilename();
    verify(file).getReferences();
    assertTrue(actualSerializeResult instanceof JsonObject);
    assertEquals(2, ((JsonObject) actualSerializeResult).size());
    assertFalse(actualSerializeResult.isJsonArray());
    assertFalse(actualSerializeResult.isJsonNull());
    assertFalse(actualSerializeResult.isJsonPrimitive());
    assertFalse(((JsonObject) actualSerializeResult).isEmpty());
    assertTrue(actualSerializeResult.isJsonObject());
    assertSame(actualSerializeResult, actualSerializeResult.getAsJsonObject());
  }
}
