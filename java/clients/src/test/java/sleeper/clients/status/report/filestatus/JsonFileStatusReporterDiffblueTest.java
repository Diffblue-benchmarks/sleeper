package sleeper.clients.status.report.filestatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.statestore.AllReferencesToAFile;
import sleeper.core.statestore.AllReferencesToAFile.Builder;
import sleeper.core.statestore.AllReferencesToAllFiles;
import sleeper.core.statestore.FileReference;
import sleeper.core.statestore.FilesReportTestHelper;

class JsonFileStatusReporterDiffblueTest {
  /**
   * Test {@link JsonFileStatusReporter#report(TableFilesStatus, boolean)}.
   * <p>
   * Method under test: {@link JsonFileStatusReporter#report(TableFilesStatus, boolean)}
   */
  @Test
  @DisplayName("Test report(TableFilesStatus, boolean)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void JsonFileStatusReporter.report(TableFilesStatus, boolean)"})
  void testReport() {
    // Arrange
    JsonFileStatusReporter jsonFileStatusReporter = new JsonFileStatusReporter();

    ArrayList<AllReferencesToAFile> allReferencesToAFileList = new ArrayList<>();
    Builder filenameResult = AllReferencesToAFile.builder().filename("foo.txt");
    Builder lastStateStoreUpdateTimeResult = filenameResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    AllReferencesToAFile buildResult = lastStateStoreUpdateTimeResult.references(new ArrayList<>()).build();
    allReferencesToAFileList.add(buildResult);
    AllReferencesToAllFiles files = mock(AllReferencesToAllFiles.class);
    when(files.isMoreThanMax()).thenReturn(true);
    when(files.getFiles()).thenReturn(allReferencesToAFileList);
    TableFilesStatus.Builder nonLeafPartitionCountResult = TableFilesStatus.builder()
        .files(files)
        .leafPartitionCount(3)
        .nonLeafPartitionCount(3);
    AllReferencesToAllFiles files2 = FilesReportTestHelper.noFilesReport();
    TableFilesStatus status = nonLeafPartitionCountResult.statistics(TableFilesStatistics.from(files2, new HashMap<>()))
        .build();

    // Act
    jsonFileStatusReporter.report(status, true);

    // Assert
    verify(files).getFiles();
    verify(files).isMoreThanMax();
  }

  /**
   * Test {@link JsonFileStatusReporter#report(TableFilesStatus, boolean)}.
   * <p>
   * Method under test: {@link JsonFileStatusReporter#report(TableFilesStatus, boolean)}
   */
  @Test
  @DisplayName("Test report(TableFilesStatus, boolean)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void JsonFileStatusReporter.report(TableFilesStatus, boolean)"})
  void testReport2() {
    // Arrange
    JsonFileStatusReporter jsonFileStatusReporter = new JsonFileStatusReporter();

    ArrayList<AllReferencesToAFile> allReferencesToAFileList = new ArrayList<>();
    Builder filenameResult = AllReferencesToAFile.builder().filename("foo.txt");
    Builder lastStateStoreUpdateTimeResult = filenameResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    AllReferencesToAFile buildResult = lastStateStoreUpdateTimeResult.references(new ArrayList<>()).build();
    allReferencesToAFileList.add(buildResult);
    Builder filenameResult2 = AllReferencesToAFile.builder().filename("foo.txt");
    Builder lastStateStoreUpdateTimeResult2 = filenameResult2
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    AllReferencesToAFile buildResult2 = lastStateStoreUpdateTimeResult2.references(new ArrayList<>()).build();
    allReferencesToAFileList.add(buildResult2);
    AllReferencesToAllFiles files = mock(AllReferencesToAllFiles.class);
    when(files.isMoreThanMax()).thenReturn(true);
    when(files.getFiles()).thenReturn(allReferencesToAFileList);
    TableFilesStatus.Builder nonLeafPartitionCountResult = TableFilesStatus.builder()
        .files(files)
        .leafPartitionCount(3)
        .nonLeafPartitionCount(3);
    AllReferencesToAllFiles files2 = FilesReportTestHelper.noFilesReport();
    TableFilesStatus status = nonLeafPartitionCountResult.statistics(TableFilesStatistics.from(files2, new HashMap<>()))
        .build();

    // Act
    jsonFileStatusReporter.report(status, true);

    // Assert
    verify(files).getFiles();
    verify(files).isMoreThanMax();
  }

  /**
   * Test {@link JsonFileStatusReporter#report(TableFilesStatus, boolean)}.
   * <p>
   * Method under test: {@link JsonFileStatusReporter#report(TableFilesStatus, boolean)}
   */
  @Test
  @DisplayName("Test report(TableFilesStatus, boolean)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void JsonFileStatusReporter.report(TableFilesStatus, boolean)"})
  void testReport3() {
    // Arrange
    JsonFileStatusReporter jsonFileStatusReporter = new JsonFileStatusReporter();

    ArrayList<FileReference> references = new ArrayList<>();
    FileReference.Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference buildResult = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    references.add(buildResult);
    Builder filenameResult = AllReferencesToAFile.builder().filename("foo.txt");
    AllReferencesToAFile buildResult2 = filenameResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .references(references)
        .build();

    ArrayList<AllReferencesToAFile> allReferencesToAFileList = new ArrayList<>();
    allReferencesToAFileList.add(buildResult2);
    AllReferencesToAllFiles files = mock(AllReferencesToAllFiles.class);
    when(files.isMoreThanMax()).thenReturn(true);
    when(files.getFiles()).thenReturn(allReferencesToAFileList);
    TableFilesStatus.Builder nonLeafPartitionCountResult = TableFilesStatus.builder()
        .files(files)
        .leafPartitionCount(3)
        .nonLeafPartitionCount(3);
    AllReferencesToAllFiles files2 = FilesReportTestHelper.noFilesReport();
    TableFilesStatus status = nonLeafPartitionCountResult.statistics(TableFilesStatistics.from(files2, new HashMap<>()))
        .build();

    // Act
    jsonFileStatusReporter.report(status, true);

    // Assert
    verify(files).getFiles();
    verify(files).isMoreThanMax();
  }

  /**
   * Test {@link JsonFileStatusReporter#report(TableFilesStatus, boolean)}.
   * <p>
   * Method under test: {@link JsonFileStatusReporter#report(TableFilesStatus, boolean)}
   */
  @Test
  @DisplayName("Test report(TableFilesStatus, boolean)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void JsonFileStatusReporter.report(TableFilesStatus, boolean)"})
  void testReport4() {
    // Arrange
    JsonFileStatusReporter jsonFileStatusReporter = new JsonFileStatusReporter();

    ArrayList<AllReferencesToAFile> allReferencesToAFileList = new ArrayList<>();
    Builder filenameResult = AllReferencesToAFile.builder().filename("foo.txt");
    Builder lastStateStoreUpdateTimeResult = filenameResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    AllReferencesToAFile buildResult = lastStateStoreUpdateTimeResult.references(new ArrayList<>()).build();
    allReferencesToAFileList.add(buildResult);
    Builder filenameResult2 = AllReferencesToAFile.builder().filename("");
    Builder lastStateStoreUpdateTimeResult2 = filenameResult2
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    AllReferencesToAFile buildResult2 = lastStateStoreUpdateTimeResult2.references(new ArrayList<>()).build();
    allReferencesToAFileList.add(buildResult2);
    AllReferencesToAllFiles files = mock(AllReferencesToAllFiles.class);
    when(files.isMoreThanMax()).thenReturn(true);
    when(files.getFiles()).thenReturn(allReferencesToAFileList);
    TableFilesStatus.Builder nonLeafPartitionCountResult = TableFilesStatus.builder()
        .files(files)
        .leafPartitionCount(3)
        .nonLeafPartitionCount(3);
    AllReferencesToAllFiles files2 = FilesReportTestHelper.noFilesReport();
    TableFilesStatus status = nonLeafPartitionCountResult.statistics(TableFilesStatistics.from(files2, new HashMap<>()))
        .build();

    // Act
    jsonFileStatusReporter.report(status, true);

    // Assert
    verify(files).getFiles();
    verify(files).isMoreThanMax();
  }

  /**
   * Test {@link JsonFileStatusReporter#report(TableFilesStatus, boolean)}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()}.</li>
   *   <li>Then calls {@link AllReferencesToAllFiles#getFiles()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JsonFileStatusReporter#report(TableFilesStatus, boolean)}
   */
  @Test
  @DisplayName("Test report(TableFilesStatus, boolean); given ArrayList(); then calls getFiles()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void JsonFileStatusReporter.report(TableFilesStatus, boolean)"})
  void testReport_givenArrayList_thenCallsGetFiles() {
    // Arrange
    JsonFileStatusReporter jsonFileStatusReporter = new JsonFileStatusReporter();
    AllReferencesToAllFiles files = mock(AllReferencesToAllFiles.class);
    when(files.isMoreThanMax()).thenReturn(true);
    when(files.getFiles()).thenReturn(new ArrayList<>());
    TableFilesStatus.Builder nonLeafPartitionCountResult = TableFilesStatus.builder()
        .files(files)
        .leafPartitionCount(3)
        .nonLeafPartitionCount(3);
    AllReferencesToAllFiles files2 = FilesReportTestHelper.noFilesReport();
    TableFilesStatus status = nonLeafPartitionCountResult.statistics(TableFilesStatistics.from(files2, new HashMap<>()))
        .build();

    // Act
    jsonFileStatusReporter.report(status, true);

    // Assert
    verify(files).getFiles();
    verify(files).isMoreThanMax();
  }

  /**
   * Test {@link JsonFileStatusReporter#allFileReferencesJsonSerializer()}.
   * <p>
   * Method under test: {@link JsonFileStatusReporter#allFileReferencesJsonSerializer()}
   */
  @Test
  @DisplayName("Test allFileReferencesJsonSerializer()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"JsonSerializer JsonFileStatusReporter.allFileReferencesJsonSerializer()"})
  void testAllFileReferencesJsonSerializer() {
    // Arrange and Act
    JsonSerializer<AllReferencesToAllFiles> actualAllFileReferencesJsonSerializerResult = JsonFileStatusReporter
        .allFileReferencesJsonSerializer();
    AllReferencesToAllFiles noFilesReportResult = FilesReportTestHelper.noFilesReport();
    JsonElement actualSerializeResult = actualAllFileReferencesJsonSerializerResult.serialize(noFilesReportResult,
        new PlaceholderForType(1), mock(JsonSerializationContext.class));

    // Assert
    assertTrue(actualSerializeResult instanceof JsonArray);
    assertEquals(0, ((JsonArray) actualSerializeResult).size());
    assertFalse(((JsonArray) actualSerializeResult).iterator().hasNext());
    assertTrue(((JsonArray) actualSerializeResult).isEmpty());
    assertSame(actualSerializeResult, actualSerializeResult.getAsJsonArray());
  }

  /**
   * Test {@link JsonFileStatusReporter#allFileReferencesJsonSerializer()}.
   * <p>
   * Method under test: {@link JsonFileStatusReporter#allFileReferencesJsonSerializer()}
   */
  @Test
  @DisplayName("Test allFileReferencesJsonSerializer()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"JsonSerializer JsonFileStatusReporter.allFileReferencesJsonSerializer()"})
  void testAllFileReferencesJsonSerializer2() {
    // Arrange and Act
    JsonSerializer<AllReferencesToAllFiles> actualAllFileReferencesJsonSerializerResult = JsonFileStatusReporter
        .allFileReferencesJsonSerializer();
    ArrayList<AllReferencesToAFile> files = new ArrayList<>();
    Builder filenameResult = AllReferencesToAFile.builder().filename("foo.txt");
    Builder lastStateStoreUpdateTimeResult = filenameResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    AllReferencesToAFile buildResult = lastStateStoreUpdateTimeResult.references(new ArrayList<>()).build();
    files.add(buildResult);
    AllReferencesToAllFiles allReferencesToAllFiles = new AllReferencesToAllFiles(files, true);

    PlaceholderForType placeholderForType = new PlaceholderForType(1);
    JsonSerializationContext jsonSerializationContext = mock(JsonSerializationContext.class);
    when(jsonSerializationContext.serialize(Mockito.<Object>any())).thenReturn(new JsonArray(3));
    JsonElement actualSerializeResult = actualAllFileReferencesJsonSerializerResult.serialize(allReferencesToAllFiles,
        placeholderForType, jsonSerializationContext);

    // Assert
    verify(jsonSerializationContext).serialize(isA(Object.class));
    assertTrue(actualSerializeResult instanceof JsonArray);
    Iterator<JsonElement> iteratorResult = ((JsonArray) actualSerializeResult).iterator();
    JsonElement nextResult = iteratorResult.next();
    assertTrue(nextResult instanceof JsonObject);
    assertEquals(1, ((JsonArray) actualSerializeResult).size());
    assertEquals(4, ((JsonObject) nextResult).size());
    assertFalse(((JsonArray) actualSerializeResult).isEmpty());
    assertFalse(nextResult.isJsonArray());
    assertFalse(nextResult.isJsonNull());
    assertFalse(nextResult.isJsonPrimitive());
    assertFalse(((JsonObject) nextResult).isEmpty());
    assertFalse(iteratorResult.hasNext());
    assertTrue(nextResult.isJsonObject());
    assertSame(actualSerializeResult, actualSerializeResult.getAsJsonArray());
    assertSame(nextResult, nextResult.getAsJsonObject());
  }

  /**
   * Test {@link JsonFileStatusReporter#allFileReferencesJsonSerializer()}.
   * <p>
   * Method under test: {@link JsonFileStatusReporter#allFileReferencesJsonSerializer()}
   */
  @Test
  @DisplayName("Test allFileReferencesJsonSerializer()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"JsonSerializer JsonFileStatusReporter.allFileReferencesJsonSerializer()"})
  void testAllFileReferencesJsonSerializer3() {
    // Arrange and Act
    JsonSerializer<AllReferencesToAllFiles> actualAllFileReferencesJsonSerializerResult = JsonFileStatusReporter
        .allFileReferencesJsonSerializer();
    ArrayList<FileReference> references = new ArrayList<>();
    FileReference.Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference buildResult = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    references.add(buildResult);
    Builder filenameResult = AllReferencesToAFile.builder().filename("foo.txt");
    AllReferencesToAFile buildResult2 = filenameResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .references(references)
        .build();
    ArrayList<AllReferencesToAFile> files = new ArrayList<>();
    files.add(buildResult2);
    AllReferencesToAllFiles allReferencesToAllFiles = new AllReferencesToAllFiles(files, true);

    PlaceholderForType placeholderForType = new PlaceholderForType(1);
    JsonSerializationContext jsonSerializationContext = mock(JsonSerializationContext.class);
    when(jsonSerializationContext.serialize(Mockito.<Object>any())).thenReturn(new JsonArray(3));
    JsonElement actualSerializeResult = actualAllFileReferencesJsonSerializerResult.serialize(allReferencesToAllFiles,
        placeholderForType, jsonSerializationContext);

    // Assert
    verify(jsonSerializationContext, atLeast(1)).serialize(isA(Object.class));
    assertTrue(actualSerializeResult instanceof JsonArray);
    Iterator<JsonElement> iteratorResult = ((JsonArray) actualSerializeResult).iterator();
    JsonElement nextResult = iteratorResult.next();
    assertTrue(nextResult instanceof JsonObject);
    assertEquals(1, ((JsonArray) actualSerializeResult).size());
    assertEquals(4, ((JsonObject) nextResult).size());
    assertFalse(((JsonArray) actualSerializeResult).isEmpty());
    assertFalse(nextResult.isJsonArray());
    assertFalse(nextResult.isJsonNull());
    assertFalse(nextResult.isJsonPrimitive());
    assertFalse(((JsonObject) nextResult).isEmpty());
    assertFalse(iteratorResult.hasNext());
    assertTrue(nextResult.isJsonObject());
    assertSame(actualSerializeResult, actualSerializeResult.getAsJsonArray());
    assertSame(nextResult, nextResult.getAsJsonObject());
  }

  /**
   * Test {@link JsonFileStatusReporter#allFileReferencesJsonSerializer()}.
   * <p>
   * Method under test: {@link JsonFileStatusReporter#allFileReferencesJsonSerializer()}
   */
  @Test
  @DisplayName("Test allFileReferencesJsonSerializer()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"JsonSerializer JsonFileStatusReporter.allFileReferencesJsonSerializer()"})
  void testAllFileReferencesJsonSerializer4() {
    // Arrange and Act
    JsonSerializer<AllReferencesToAllFiles> actualAllFileReferencesJsonSerializerResult = JsonFileStatusReporter
        .allFileReferencesJsonSerializer();
    ArrayList<AllReferencesToAFile> files = new ArrayList<>();
    Builder filenameResult = AllReferencesToAFile.builder().filename("foo.txt");
    Builder lastStateStoreUpdateTimeResult = filenameResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    AllReferencesToAFile buildResult = lastStateStoreUpdateTimeResult.references(new ArrayList<>()).build();
    files.add(buildResult);
    AllReferencesToAllFiles allReferencesToAllFiles = new AllReferencesToAllFiles(files, true);

    PlaceholderForType placeholderForType = new PlaceholderForType(1);
    JsonSerializationContext jsonSerializationContext = mock(JsonSerializationContext.class);
    when(jsonSerializationContext.serialize(Mockito.<Object>any())).thenReturn(null);
    JsonElement actualSerializeResult = actualAllFileReferencesJsonSerializerResult.serialize(allReferencesToAllFiles,
        placeholderForType, jsonSerializationContext);

    // Assert
    verify(jsonSerializationContext).serialize(isA(Object.class));
    assertTrue(actualSerializeResult instanceof JsonArray);
    Iterator<JsonElement> iteratorResult = ((JsonArray) actualSerializeResult).iterator();
    JsonElement nextResult = iteratorResult.next();
    assertTrue(nextResult instanceof JsonObject);
    assertEquals(1, ((JsonArray) actualSerializeResult).size());
    assertEquals(4, ((JsonObject) nextResult).size());
    assertFalse(((JsonArray) actualSerializeResult).isEmpty());
    assertFalse(nextResult.isJsonArray());
    assertFalse(nextResult.isJsonNull());
    assertFalse(nextResult.isJsonPrimitive());
    assertFalse(((JsonObject) nextResult).isEmpty());
    assertFalse(iteratorResult.hasNext());
    assertTrue(nextResult.isJsonObject());
    assertSame(actualSerializeResult, actualSerializeResult.getAsJsonArray());
    assertSame(nextResult, nextResult.getAsJsonObject());
  }
}
