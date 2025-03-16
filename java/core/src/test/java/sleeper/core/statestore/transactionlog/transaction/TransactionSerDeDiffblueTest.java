package sleeper.core.statestore.transactionlog.transaction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.schema.Schema;
import sleeper.core.statestore.AllReferencesToAFile;
import sleeper.core.statestore.AllReferencesToAFile.Builder;
import sleeper.core.statestore.FileReference;
import sleeper.core.statestore.ReplaceFileReferencesRequest;
import sleeper.core.statestore.StateStoreException;
import sleeper.core.statestore.transactionlog.transaction.impl.AddFilesTransaction;
import sleeper.core.statestore.transactionlog.transaction.impl.ClearFilesTransaction;
import sleeper.core.statestore.transactionlog.transaction.impl.ReplaceFileReferencesTransaction;

class TransactionSerDeDiffblueTest {
  /**
   * Test {@link TransactionSerDe#TransactionSerDe(Schema)}.
   * <p>
   * Method under test: {@link TransactionSerDe#TransactionSerDe(Schema)}
   */
  @Test
  @DisplayName("Test new TransactionSerDe(Schema)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void TransactionSerDe.<init>(Schema)"})
  void testNewTransactionSerDe() {
    // Arrange
    Schema schema = mock(Schema.class);
    when(schema.getRowKeyFieldNames()).thenReturn(new ArrayList<>());
    when(schema.getRowKeyFields()).thenReturn(new ArrayList<>());

    // Act
    TransactionSerDe actualTransactionSerDe = new TransactionSerDe(schema);

    // Assert
    verify(schema).getRowKeyFieldNames();
    verify(schema).getRowKeyFields();
    JsonElement toJsonTreeResult = actualTransactionSerDe.toJsonTree(null);
    assertTrue(toJsonTreeResult instanceof JsonNull);
    assertEquals("null", actualTransactionSerDe.toJson(null));
    assertEquals("null", actualTransactionSerDe.toJsonPrettyPrint(null));
    assertFalse(toJsonTreeResult.isJsonArray());
    assertFalse(toJsonTreeResult.isJsonObject());
    assertFalse(toJsonTreeResult.isJsonPrimitive());
    assertTrue(toJsonTreeResult.isJsonNull());
    assertSame(toJsonTreeResult, toJsonTreeResult.getAsJsonNull());
  }

  /**
   * Test {@link TransactionSerDe#forFileTransactions()}.
   * <p>
   * Method under test: {@link TransactionSerDe#forFileTransactions()}
   */
  @Test
  @DisplayName("Test forFileTransactions()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"TransactionSerDe TransactionSerDe.forFileTransactions()"})
  void testForFileTransactions() {
    // Arrange and Act
    TransactionSerDe actualForFileTransactionsResult = TransactionSerDe.forFileTransactions();

    // Assert
    JsonElement toJsonTreeResult = actualForFileTransactionsResult.toJsonTree(null);
    assertTrue(toJsonTreeResult instanceof JsonNull);
    assertEquals("null", actualForFileTransactionsResult.toJson(null));
    assertEquals("null", actualForFileTransactionsResult.toJsonPrettyPrint(null));
    assertFalse(toJsonTreeResult.isJsonArray());
    assertFalse(toJsonTreeResult.isJsonObject());
    assertFalse(toJsonTreeResult.isJsonPrimitive());
    assertTrue(toJsonTreeResult.isJsonNull());
    assertSame(toJsonTreeResult, toJsonTreeResult.getAsJsonNull());
  }

  /**
   * Test {@link TransactionSerDe#toJson(StateStoreTransaction)}.
   * <p>
   * Method under test: {@link TransactionSerDe#toJson(StateStoreTransaction)}
   */
  @Test
  @DisplayName("Test toJson(StateStoreTransaction)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String TransactionSerDe.toJson(StateStoreTransaction)"})
  void testToJson() {
    // Arrange
    TransactionSerDe forFileTransactionsResult = TransactionSerDe.forFileTransactions();

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

    // Act and Assert
    assertEquals(
        "{\"files\":[{\"filename\":\"foo.txt\",\"references\":[{\"partitionId\":\"42\",\"numberOfRecords\":1,\"jobId\":\"42\","
            + "\"countApproximate\":true,\"onlyContainsDataForThisPartition\":true}]}]}",
        forFileTransactionsResult.toJson(new AddFilesTransaction(files)));
  }

  /**
   * Test {@link TransactionSerDe#toJson(StateStoreTransaction)}.
   * <p>
   * Method under test: {@link TransactionSerDe#toJson(StateStoreTransaction)}
   */
  @Test
  @DisplayName("Test toJson(StateStoreTransaction)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String TransactionSerDe.toJson(StateStoreTransaction)"})
  void testToJson2() {
    // Arrange
    TransactionSerDe forFileTransactionsResult = TransactionSerDe.forFileTransactions();

    ArrayList<FileReference> references = new ArrayList<>();
    FileReference.Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference buildResult = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    references.add(buildResult);
    FileReference.Builder jobIdResult2 = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference buildResult2 = jobIdResult2
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    references.add(buildResult2);
    Builder filenameResult = AllReferencesToAFile.builder().filename("foo.txt");
    AllReferencesToAFile buildResult3 = filenameResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .references(references)
        .build();

    ArrayList<AllReferencesToAFile> files = new ArrayList<>();
    files.add(buildResult3);

    // Act and Assert
    assertEquals(
        "{\"files\":[{\"filename\":\"foo.txt\",\"references\":[{\"partitionId\":\"42\",\"numberOfRecords\":1,\"jobId\":\"42\","
            + "\"countApproximate\":true,\"onlyContainsDataForThisPartition\":true},{\"partitionId\":\"42\",\"numberOfRecords"
            + "\":1,\"jobId\":\"42\",\"countApproximate\":true,\"onlyContainsDataForThisPartition\":true}]}]}",
        forFileTransactionsResult.toJson(new AddFilesTransaction(files)));
  }

  /**
   * Test {@link TransactionSerDe#toJson(StateStoreTransaction)}.
   * <p>
   * Method under test: {@link TransactionSerDe#toJson(StateStoreTransaction)}
   */
  @Test
  @DisplayName("Test toJson(StateStoreTransaction)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String TransactionSerDe.toJson(StateStoreTransaction)"})
  void testToJson3() {
    // Arrange
    TransactionSerDe forFileTransactionsResult = TransactionSerDe.forFileTransactions();

    ArrayList<FileReference> references = new ArrayList<>();
    FileReference.Builder jobIdResult = FileReference.builder().countApproximate(false).filename("foo.txt").jobId("42");
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

    // Act and Assert
    assertEquals(
        "{\"files\":[{\"filename\":\"foo.txt\",\"references\":[{\"partitionId\":\"42\",\"numberOfRecords\":1,\"jobId\":\"42\","
            + "\"countApproximate\":false,\"onlyContainsDataForThisPartition\":true}]}]}",
        forFileTransactionsResult.toJson(new AddFilesTransaction(files)));
  }

  /**
   * Test {@link TransactionSerDe#toJson(StateStoreTransaction)}.
   * <p>
   * Method under test: {@link TransactionSerDe#toJson(StateStoreTransaction)}
   */
  @Test
  @DisplayName("Test toJson(StateStoreTransaction)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String TransactionSerDe.toJson(StateStoreTransaction)"})
  void testToJson4() throws StateStoreException {
    // Arrange
    TransactionSerDe forFileTransactionsResult = TransactionSerDe.forFileTransactions();

    ArrayList<ReplaceFileReferencesRequest> jobs = new ArrayList<>();
    ReplaceFileReferencesRequest.Builder builderResult = ReplaceFileReferencesRequest.builder();
    ReplaceFileReferencesRequest.Builder jobRunIdResult = builderResult.inputFiles(new ArrayList<>())
        .jobId("42")
        .jobRunId("42");
    FileReference.Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference newReference = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    ReplaceFileReferencesRequest buildResult = jobRunIdResult.newReference(newReference).taskId("42").build();
    jobs.add(buildResult);

    // Act and Assert
    assertEquals(
        "{\"jobs\":[{\"jobId\":\"42\",\"taskId\":\"42\",\"jobRunId\":\"42\",\"inputFiles\":[],\"newReference\":{\"filename\":\"foo"
            + ".txt\",\"partitionId\":\"42\",\"numberOfRecords\":1,\"jobId\":\"42\",\"countApproximate\":true,\"onlyContainsDataF"
            + "orThisPartition\":true}}]}",
        forFileTransactionsResult.toJson(new ReplaceFileReferencesTransaction(jobs)));
  }

  /**
   * Test {@link TransactionSerDe#toJson(StateStoreTransaction)}.
   * <p>
   * Method under test: {@link TransactionSerDe#toJson(StateStoreTransaction)}
   */
  @Test
  @DisplayName("Test toJson(StateStoreTransaction)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String TransactionSerDe.toJson(StateStoreTransaction)"})
  void testToJson5() throws StateStoreException {
    // Arrange
    TransactionSerDe forFileTransactionsResult = TransactionSerDe.forFileTransactions();

    ArrayList<ReplaceFileReferencesRequest> jobs = new ArrayList<>();
    ReplaceFileReferencesRequest.Builder builderResult = ReplaceFileReferencesRequest.builder();
    ReplaceFileReferencesRequest.Builder jobRunIdResult = builderResult.inputFiles(new ArrayList<>())
        .jobId("42")
        .jobRunId("42");
    FileReference.Builder jobIdResult = FileReference.builder().countApproximate(false).filename("foo.txt").jobId("42");
    FileReference newReference = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    ReplaceFileReferencesRequest buildResult = jobRunIdResult.newReference(newReference).taskId("42").build();
    jobs.add(buildResult);

    // Act and Assert
    assertEquals(
        "{\"jobs\":[{\"jobId\":\"42\",\"taskId\":\"42\",\"jobRunId\":\"42\",\"inputFiles\":[],\"newReference\":{\"filename\":\"foo"
            + ".txt\",\"partitionId\":\"42\",\"numberOfRecords\":1,\"jobId\":\"42\",\"countApproximate\":false,\"onlyContainsData"
            + "ForThisPartition\":true}}]}",
        forFileTransactionsResult.toJson(new ReplaceFileReferencesTransaction(jobs)));
  }

  /**
   * Test {@link TransactionSerDe#toJson(StateStoreTransaction)}.
   * <ul>
   *   <li>Then return {@code {"files":[{"filename":"foo.txt","references":[]}]}}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TransactionSerDe#toJson(StateStoreTransaction)}
   */
  @Test
  @DisplayName("Test toJson(StateStoreTransaction); then return '{\"files\":[{\"filename\":\"foo.txt\",\"references\":[]}]}'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String TransactionSerDe.toJson(StateStoreTransaction)"})
  void testToJson_thenReturnFilesFilenameFooTxtReferences() {
    // Arrange
    TransactionSerDe forFileTransactionsResult = TransactionSerDe.forFileTransactions();

    ArrayList<AllReferencesToAFile> files = new ArrayList<>();
    Builder filenameResult = AllReferencesToAFile.builder().filename("foo.txt");
    Builder lastStateStoreUpdateTimeResult = filenameResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    AllReferencesToAFile buildResult = lastStateStoreUpdateTimeResult.references(new ArrayList<>()).build();
    files.add(buildResult);

    // Act and Assert
    assertEquals("{\"files\":[{\"filename\":\"foo.txt\",\"references\":[]}]}",
        forFileTransactionsResult.toJson(new AddFilesTransaction(files)));
  }

  /**
   * Test {@link TransactionSerDe#toJson(StateStoreTransaction)}.
   * <ul>
   *   <li>Then return {@code {"files":[{"filename":"foo.txt","references":[]},{"filename":"foo.txt","references":[]}]}}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TransactionSerDe#toJson(StateStoreTransaction)}
   */
  @Test
  @DisplayName("Test toJson(StateStoreTransaction); then return '{\"files\":[{\"filename\":\"foo.txt\",\"references\":[]},{\"filename\":\"foo.txt\",\"references\":[]}]}'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String TransactionSerDe.toJson(StateStoreTransaction)"})
  void testToJson_thenReturnFilesFilenameFooTxtReferencesFilenameFooTxtReferences() {
    // Arrange
    TransactionSerDe forFileTransactionsResult = TransactionSerDe.forFileTransactions();

    ArrayList<AllReferencesToAFile> files = new ArrayList<>();
    Builder filenameResult = AllReferencesToAFile.builder().filename("foo.txt");
    Builder lastStateStoreUpdateTimeResult = filenameResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    AllReferencesToAFile buildResult = lastStateStoreUpdateTimeResult.references(new ArrayList<>()).build();
    files.add(buildResult);
    Builder filenameResult2 = AllReferencesToAFile.builder().filename("foo.txt");
    Builder lastStateStoreUpdateTimeResult2 = filenameResult2
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    AllReferencesToAFile buildResult2 = lastStateStoreUpdateTimeResult2.references(new ArrayList<>()).build();
    files.add(buildResult2);

    // Act and Assert
    assertEquals(
        "{\"files\":[{\"filename\":\"foo.txt\",\"references\":[]},{\"filename\":\"foo.txt\",\"references\":[]}]}",
        forFileTransactionsResult.toJson(new AddFilesTransaction(files)));
  }

  /**
   * Test {@link TransactionSerDe#toJson(StateStoreTransaction)}.
   * <ul>
   *   <li>Then return {@code {"files":[{"filename":"","references":[]}]}}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TransactionSerDe#toJson(StateStoreTransaction)}
   */
  @Test
  @DisplayName("Test toJson(StateStoreTransaction); then return '{\"files\":[{\"filename\":\"\",\"references\":[]}]}'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String TransactionSerDe.toJson(StateStoreTransaction)"})
  void testToJson_thenReturnFilesFilenameReferences() {
    // Arrange
    TransactionSerDe forFileTransactionsResult = TransactionSerDe.forFileTransactions();

    ArrayList<AllReferencesToAFile> files = new ArrayList<>();
    Builder filenameResult = AllReferencesToAFile.builder().filename("");
    Builder lastStateStoreUpdateTimeResult = filenameResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    AllReferencesToAFile buildResult = lastStateStoreUpdateTimeResult.references(new ArrayList<>()).build();
    files.add(buildResult);

    // Act and Assert
    assertEquals("{\"files\":[{\"filename\":\"\",\"references\":[]}]}",
        forFileTransactionsResult.toJson(new AddFilesTransaction(files)));
  }

  /**
   * Test {@link TransactionSerDe#toJson(StateStoreTransaction)}.
   * <ul>
   *   <li>Then return {@code {"jobId":"42","taskId":"42","jobRunId":"42","writtenTime":0,"files":[]}}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TransactionSerDe#toJson(StateStoreTransaction)}
   */
  @Test
  @DisplayName("Test toJson(StateStoreTransaction); then return '{\"jobId\":\"42\",\"taskId\":\"42\",\"jobRunId\":\"42\",\"writtenTime\":0,\"files\":[]}'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String TransactionSerDe.toJson(StateStoreTransaction)"})
  void testToJson_thenReturnJobId42TaskId42JobRunId42WrittenTime0Files() {
    // Arrange
    TransactionSerDe forFileTransactionsResult = TransactionSerDe.forFileTransactions();
    AddFilesTransaction.Builder builderResult = AddFilesTransaction.builder();
    AddFilesTransaction.Builder taskIdResult = builderResult.files(new ArrayList<>())
        .jobId("42")
        .jobRunId("42")
        .taskId("42");
    AddFilesTransaction transaction = taskIdResult
        .writtenTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();

    // Act and Assert
    assertEquals("{\"jobId\":\"42\",\"taskId\":\"42\",\"jobRunId\":\"42\",\"writtenTime\":0,\"files\":[]}",
        forFileTransactionsResult.toJson(transaction));
  }

  /**
   * Test {@link TransactionSerDe#toJson(StateStoreTransaction)}.
   * <ul>
   *   <li>Then return {@code {"jobs":[]}}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TransactionSerDe#toJson(StateStoreTransaction)}
   */
  @Test
  @DisplayName("Test toJson(StateStoreTransaction); then return '{\"jobs\":[]}'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String TransactionSerDe.toJson(StateStoreTransaction)"})
  void testToJson_thenReturnJobs() throws StateStoreException {
    // Arrange
    TransactionSerDe forFileTransactionsResult = TransactionSerDe.forFileTransactions();

    // Act and Assert
    assertEquals("{\"jobs\":[]}",
        forFileTransactionsResult.toJson(new ReplaceFileReferencesTransaction(new ArrayList<>())));
  }

  /**
   * Test {@link TransactionSerDe#toJson(StateStoreTransaction)}.
   * <ul>
   *   <li>When {@link AddFilesTransaction#AddFilesTransaction(List)} with files is {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return {@code {"files":[]}}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TransactionSerDe#toJson(StateStoreTransaction)}
   */
  @Test
  @DisplayName("Test toJson(StateStoreTransaction); when AddFilesTransaction(List) with files is ArrayList(); then return '{\"files\":[]}'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String TransactionSerDe.toJson(StateStoreTransaction)"})
  void testToJson_whenAddFilesTransactionWithFilesIsArrayList_thenReturnFiles() {
    // Arrange
    TransactionSerDe forFileTransactionsResult = TransactionSerDe.forFileTransactions();

    // Act and Assert
    assertEquals("{\"files\":[]}", forFileTransactionsResult.toJson(new AddFilesTransaction(new ArrayList<>())));
  }

  /**
   * Test {@link TransactionSerDe#toJson(StateStoreTransaction)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TransactionSerDe#toJson(StateStoreTransaction)}
   */
  @Test
  @DisplayName("Test toJson(StateStoreTransaction); when 'null'; then return 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String TransactionSerDe.toJson(StateStoreTransaction)"})
  void testToJson_whenNull_thenReturnNull() {
    // Arrange, Act and Assert
    assertEquals("null", TransactionSerDe.forFileTransactions().toJson(null));
  }

  /**
   * Test {@link TransactionSerDe#toJsonPrettyPrint(StateStoreTransaction)}.
   * <p>
   * Method under test: {@link TransactionSerDe#toJsonPrettyPrint(StateStoreTransaction)}
   */
  @Test
  @DisplayName("Test toJsonPrettyPrint(StateStoreTransaction)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String TransactionSerDe.toJsonPrettyPrint(StateStoreTransaction)"})
  void testToJsonPrettyPrint() {
    // Arrange
    TransactionSerDe forFileTransactionsResult = TransactionSerDe.forFileTransactions();

    ArrayList<AllReferencesToAFile> files = new ArrayList<>();
    Builder filenameResult = AllReferencesToAFile.builder().filename("foo.txt");
    Builder lastStateStoreUpdateTimeResult = filenameResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    AllReferencesToAFile buildResult = lastStateStoreUpdateTimeResult.references(new ArrayList<>()).build();
    files.add(buildResult);
    Builder filenameResult2 = AllReferencesToAFile.builder().filename("foo.txt");
    Builder lastStateStoreUpdateTimeResult2 = filenameResult2
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    AllReferencesToAFile buildResult2 = lastStateStoreUpdateTimeResult2.references(new ArrayList<>()).build();
    files.add(buildResult2);

    // Act and Assert
    assertEquals("{\n" + "  \"files\": [\n" + "    {\n" + "      \"filename\": \"foo.txt\",\n"
        + "      \"references\": []\n" + "    },\n" + "    {\n" + "      \"filename\": \"foo.txt\",\n"
        + "      \"references\": []\n" + "    }\n" + "  ]\n" + "}",
        forFileTransactionsResult.toJsonPrettyPrint(new AddFilesTransaction(files)));
  }

  /**
   * Test {@link TransactionSerDe#toJsonPrettyPrint(StateStoreTransaction)}.
   * <p>
   * Method under test: {@link TransactionSerDe#toJsonPrettyPrint(StateStoreTransaction)}
   */
  @Test
  @DisplayName("Test toJsonPrettyPrint(StateStoreTransaction)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String TransactionSerDe.toJsonPrettyPrint(StateStoreTransaction)"})
  void testToJsonPrettyPrint2() {
    // Arrange
    TransactionSerDe forFileTransactionsResult = TransactionSerDe.forFileTransactions();

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

    // Act and Assert
    assertEquals("{\n" + "  \"files\": [\n" + "    {\n" + "      \"filename\": \"foo.txt\",\n"
        + "      \"references\": [\n" + "        {\n" + "          \"partitionId\": \"42\",\n"
        + "          \"numberOfRecords\": 1,\n" + "          \"jobId\": \"42\",\n"
        + "          \"countApproximate\": true,\n" + "          \"onlyContainsDataForThisPartition\": true\n"
        + "        }\n" + "      ]\n" + "    }\n" + "  ]\n" + "}",
        forFileTransactionsResult.toJsonPrettyPrint(new AddFilesTransaction(files)));
  }

  /**
   * Test {@link TransactionSerDe#toJsonPrettyPrint(StateStoreTransaction)}.
   * <p>
   * Method under test: {@link TransactionSerDe#toJsonPrettyPrint(StateStoreTransaction)}
   */
  @Test
  @DisplayName("Test toJsonPrettyPrint(StateStoreTransaction)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String TransactionSerDe.toJsonPrettyPrint(StateStoreTransaction)"})
  void testToJsonPrettyPrint3() {
    // Arrange
    TransactionSerDe forFileTransactionsResult = TransactionSerDe.forFileTransactions();

    ArrayList<FileReference> references = new ArrayList<>();
    FileReference.Builder jobIdResult = FileReference.builder().countApproximate(false).filename("foo.txt").jobId("42");
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

    // Act and Assert
    assertEquals("{\n" + "  \"files\": [\n" + "    {\n" + "      \"filename\": \"foo.txt\",\n"
        + "      \"references\": [\n" + "        {\n" + "          \"partitionId\": \"42\",\n"
        + "          \"numberOfRecords\": 1,\n" + "          \"jobId\": \"42\",\n"
        + "          \"countApproximate\": false,\n" + "          \"onlyContainsDataForThisPartition\": true\n"
        + "        }\n" + "      ]\n" + "    }\n" + "  ]\n" + "}",
        forFileTransactionsResult.toJsonPrettyPrint(new AddFilesTransaction(files)));
  }

  /**
   * Test {@link TransactionSerDe#toJsonPrettyPrint(StateStoreTransaction)}.
   * <p>
   * Method under test: {@link TransactionSerDe#toJsonPrettyPrint(StateStoreTransaction)}
   */
  @Test
  @DisplayName("Test toJsonPrettyPrint(StateStoreTransaction)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String TransactionSerDe.toJsonPrettyPrint(StateStoreTransaction)"})
  void testToJsonPrettyPrint4() throws StateStoreException {
    // Arrange
    TransactionSerDe forFileTransactionsResult = TransactionSerDe.forFileTransactions();

    ArrayList<ReplaceFileReferencesRequest> jobs = new ArrayList<>();
    ReplaceFileReferencesRequest.Builder builderResult = ReplaceFileReferencesRequest.builder();
    ReplaceFileReferencesRequest.Builder jobRunIdResult = builderResult.inputFiles(new ArrayList<>())
        .jobId("42")
        .jobRunId("42");
    FileReference.Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference newReference = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    ReplaceFileReferencesRequest buildResult = jobRunIdResult.newReference(newReference).taskId("42").build();
    jobs.add(buildResult);

    // Act and Assert
    assertEquals("{\n" + "  \"jobs\": [\n" + "    {\n" + "      \"jobId\": \"42\",\n" + "      \"taskId\": \"42\",\n"
        + "      \"jobRunId\": \"42\",\n" + "      \"inputFiles\": [],\n" + "      \"newReference\": {\n"
        + "        \"filename\": \"foo.txt\",\n" + "        \"partitionId\": \"42\",\n"
        + "        \"numberOfRecords\": 1,\n" + "        \"jobId\": \"42\",\n" + "        \"countApproximate\": true,\n"
        + "        \"onlyContainsDataForThisPartition\": true\n" + "      }\n" + "    }\n" + "  ]\n" + "}",
        forFileTransactionsResult.toJsonPrettyPrint(new ReplaceFileReferencesTransaction(jobs)));
  }

  /**
   * Test {@link TransactionSerDe#toJsonPrettyPrint(StateStoreTransaction)}.
   * <p>
   * Method under test: {@link TransactionSerDe#toJsonPrettyPrint(StateStoreTransaction)}
   */
  @Test
  @DisplayName("Test toJsonPrettyPrint(StateStoreTransaction)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String TransactionSerDe.toJsonPrettyPrint(StateStoreTransaction)"})
  void testToJsonPrettyPrint5() throws StateStoreException {
    // Arrange
    TransactionSerDe forFileTransactionsResult = TransactionSerDe.forFileTransactions();

    ArrayList<ReplaceFileReferencesRequest> jobs = new ArrayList<>();
    ReplaceFileReferencesRequest.Builder builderResult = ReplaceFileReferencesRequest.builder();
    ReplaceFileReferencesRequest.Builder jobRunIdResult = builderResult.inputFiles(new ArrayList<>())
        .jobId("42")
        .jobRunId("42");
    FileReference.Builder jobIdResult = FileReference.builder().countApproximate(false).filename("foo.txt").jobId("42");
    FileReference newReference = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    ReplaceFileReferencesRequest buildResult = jobRunIdResult.newReference(newReference).taskId("42").build();
    jobs.add(buildResult);

    // Act and Assert
    assertEquals(
        "{\n" + "  \"jobs\": [\n" + "    {\n" + "      \"jobId\": \"42\",\n" + "      \"taskId\": \"42\",\n"
            + "      \"jobRunId\": \"42\",\n" + "      \"inputFiles\": [],\n" + "      \"newReference\": {\n"
            + "        \"filename\": \"foo.txt\",\n" + "        \"partitionId\": \"42\",\n"
            + "        \"numberOfRecords\": 1,\n" + "        \"jobId\": \"42\",\n"
            + "        \"countApproximate\": false,\n" + "        \"onlyContainsDataForThisPartition\": true\n"
            + "      }\n" + "    }\n" + "  ]\n" + "}",
        forFileTransactionsResult.toJsonPrettyPrint(new ReplaceFileReferencesTransaction(jobs)));
  }

  /**
   * Test {@link TransactionSerDe#toJsonPrettyPrint(StateStoreTransaction)}.
   * <ul>
   *   <li>Then return {@code { "files": [] }}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TransactionSerDe#toJsonPrettyPrint(StateStoreTransaction)}
   */
  @Test
  @DisplayName("Test toJsonPrettyPrint(StateStoreTransaction); then return '{ \"files\": [] }'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String TransactionSerDe.toJsonPrettyPrint(StateStoreTransaction)"})
  void testToJsonPrettyPrint_thenReturnFiles() {
    // Arrange
    TransactionSerDe forFileTransactionsResult = TransactionSerDe.forFileTransactions();

    // Act and Assert
    assertEquals("{\n  \"files\": []\n}",
        forFileTransactionsResult.toJsonPrettyPrint(new AddFilesTransaction(new ArrayList<>())));
  }

  /**
   * Test {@link TransactionSerDe#toJsonPrettyPrint(StateStoreTransaction)}.
   * <ul>
   *   <li>Then return {@code { "files": [ { "filename": "foo.txt", "references": [] } ] }}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TransactionSerDe#toJsonPrettyPrint(StateStoreTransaction)}
   */
  @Test
  @DisplayName("Test toJsonPrettyPrint(StateStoreTransaction); then return '{ \"files\": [ { \"filename\": \"foo.txt\", \"references\": [] } ] }'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String TransactionSerDe.toJsonPrettyPrint(StateStoreTransaction)"})
  void testToJsonPrettyPrint_thenReturnFilesFilenameFooTxtReferences() {
    // Arrange
    TransactionSerDe forFileTransactionsResult = TransactionSerDe.forFileTransactions();

    ArrayList<AllReferencesToAFile> files = new ArrayList<>();
    Builder filenameResult = AllReferencesToAFile.builder().filename("foo.txt");
    Builder lastStateStoreUpdateTimeResult = filenameResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    AllReferencesToAFile buildResult = lastStateStoreUpdateTimeResult.references(new ArrayList<>()).build();
    files.add(buildResult);

    // Act and Assert
    assertEquals("{\n  \"files\": [\n    {\n      \"filename\": \"foo.txt\",\n      \"references\": []\n    }\n  ]\n}",
        forFileTransactionsResult.toJsonPrettyPrint(new AddFilesTransaction(files)));
  }

  /**
   * Test {@link TransactionSerDe#toJsonPrettyPrint(StateStoreTransaction)}.
   * <ul>
   *   <li>Then return {@code { "files": [ { "filename": "", "references": [] } ] }}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TransactionSerDe#toJsonPrettyPrint(StateStoreTransaction)}
   */
  @Test
  @DisplayName("Test toJsonPrettyPrint(StateStoreTransaction); then return '{ \"files\": [ { \"filename\": \"\", \"references\": [] } ] }'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String TransactionSerDe.toJsonPrettyPrint(StateStoreTransaction)"})
  void testToJsonPrettyPrint_thenReturnFilesFilenameReferences() {
    // Arrange
    TransactionSerDe forFileTransactionsResult = TransactionSerDe.forFileTransactions();

    ArrayList<AllReferencesToAFile> files = new ArrayList<>();
    Builder filenameResult = AllReferencesToAFile.builder().filename("");
    Builder lastStateStoreUpdateTimeResult = filenameResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    AllReferencesToAFile buildResult = lastStateStoreUpdateTimeResult.references(new ArrayList<>()).build();
    files.add(buildResult);

    // Act and Assert
    assertEquals("{\n  \"files\": [\n    {\n      \"filename\": \"\",\n      \"references\": []\n    }\n  ]\n}",
        forFileTransactionsResult.toJsonPrettyPrint(new AddFilesTransaction(files)));
  }

  /**
   * Test {@link TransactionSerDe#toJsonPrettyPrint(StateStoreTransaction)}.
   * <ul>
   *   <li>Then return {@code { "jobId": "42", "taskId": "42", "jobRunId": "42", "writtenTime": 0, "files": [] }}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TransactionSerDe#toJsonPrettyPrint(StateStoreTransaction)}
   */
  @Test
  @DisplayName("Test toJsonPrettyPrint(StateStoreTransaction); then return '{ \"jobId\": \"42\", \"taskId\": \"42\", \"jobRunId\": \"42\", \"writtenTime\": 0, \"files\": [] }'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String TransactionSerDe.toJsonPrettyPrint(StateStoreTransaction)"})
  void testToJsonPrettyPrint_thenReturnJobId42TaskId42JobRunId42WrittenTime0Files() {
    // Arrange
    TransactionSerDe forFileTransactionsResult = TransactionSerDe.forFileTransactions();
    AddFilesTransaction.Builder builderResult = AddFilesTransaction.builder();
    AddFilesTransaction.Builder taskIdResult = builderResult.files(new ArrayList<>())
        .jobId("42")
        .jobRunId("42")
        .taskId("42");
    AddFilesTransaction transaction = taskIdResult
        .writtenTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();

    // Act and Assert
    assertEquals(
        "{\n  \"jobId\": \"42\",\n  \"taskId\": \"42\",\n  \"jobRunId\": \"42\",\n  \"writtenTime\": 0,\n  \"files\": []\n}",
        forFileTransactionsResult.toJsonPrettyPrint(transaction));
  }

  /**
   * Test {@link TransactionSerDe#toJsonPrettyPrint(StateStoreTransaction)}.
   * <ul>
   *   <li>Then return {@code { "jobs": [] }}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TransactionSerDe#toJsonPrettyPrint(StateStoreTransaction)}
   */
  @Test
  @DisplayName("Test toJsonPrettyPrint(StateStoreTransaction); then return '{ \"jobs\": [] }'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String TransactionSerDe.toJsonPrettyPrint(StateStoreTransaction)"})
  void testToJsonPrettyPrint_thenReturnJobs() throws StateStoreException {
    // Arrange
    TransactionSerDe forFileTransactionsResult = TransactionSerDe.forFileTransactions();

    // Act and Assert
    assertEquals("{\n  \"jobs\": []\n}",
        forFileTransactionsResult.toJsonPrettyPrint(new ReplaceFileReferencesTransaction(new ArrayList<>())));
  }

  /**
   * Test {@link TransactionSerDe#toJsonPrettyPrint(StateStoreTransaction)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TransactionSerDe#toJsonPrettyPrint(StateStoreTransaction)}
   */
  @Test
  @DisplayName("Test toJsonPrettyPrint(StateStoreTransaction); when 'null'; then return 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String TransactionSerDe.toJsonPrettyPrint(StateStoreTransaction)"})
  void testToJsonPrettyPrint_whenNull_thenReturnNull() {
    // Arrange, Act and Assert
    assertEquals("null", TransactionSerDe.forFileTransactions().toJsonPrettyPrint(null));
  }

  /**
   * Test {@link TransactionSerDe#toJsonTree(StateStoreTransaction)}.
   * <p>
   * Method under test: {@link TransactionSerDe#toJsonTree(StateStoreTransaction)}
   */
  @Test
  @DisplayName("Test toJsonTree(StateStoreTransaction)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"JsonElement TransactionSerDe.toJsonTree(StateStoreTransaction)"})
  void testToJsonTree() {
    // Arrange
    TransactionSerDe forFileTransactionsResult = TransactionSerDe.forFileTransactions();

    ArrayList<AllReferencesToAFile> files = new ArrayList<>();
    Builder filenameResult = AllReferencesToAFile.builder().filename("foo.txt");
    Builder lastStateStoreUpdateTimeResult = filenameResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    AllReferencesToAFile buildResult = lastStateStoreUpdateTimeResult.references(new ArrayList<>()).build();
    files.add(buildResult);

    // Act
    JsonElement actualToJsonTreeResult = forFileTransactionsResult.toJsonTree(new AddFilesTransaction(files));

    // Assert
    assertTrue(actualToJsonTreeResult instanceof JsonObject);
    assertEquals(1, ((JsonObject) actualToJsonTreeResult).size());
    assertFalse(actualToJsonTreeResult.isJsonNull());
    assertFalse(((JsonObject) actualToJsonTreeResult).isEmpty());
    assertTrue(actualToJsonTreeResult.isJsonObject());
    assertSame(actualToJsonTreeResult, actualToJsonTreeResult.getAsJsonObject());
  }

  /**
   * Test {@link TransactionSerDe#toJsonTree(StateStoreTransaction)}.
   * <p>
   * Method under test: {@link TransactionSerDe#toJsonTree(StateStoreTransaction)}
   */
  @Test
  @DisplayName("Test toJsonTree(StateStoreTransaction)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"JsonElement TransactionSerDe.toJsonTree(StateStoreTransaction)"})
  void testToJsonTree2() {
    // Arrange
    TransactionSerDe forFileTransactionsResult = TransactionSerDe.forFileTransactions();

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

    // Act
    JsonElement actualToJsonTreeResult = forFileTransactionsResult.toJsonTree(new AddFilesTransaction(files));

    // Assert
    assertTrue(actualToJsonTreeResult instanceof JsonObject);
    assertEquals(1, ((JsonObject) actualToJsonTreeResult).size());
    assertFalse(actualToJsonTreeResult.isJsonNull());
    assertFalse(((JsonObject) actualToJsonTreeResult).isEmpty());
    assertTrue(actualToJsonTreeResult.isJsonObject());
    assertSame(actualToJsonTreeResult, actualToJsonTreeResult.getAsJsonObject());
  }

  /**
   * Test {@link TransactionSerDe#toJsonTree(StateStoreTransaction)}.
   * <ul>
   *   <li>Then return size is five.</li>
   * </ul>
   * <p>
   * Method under test: {@link TransactionSerDe#toJsonTree(StateStoreTransaction)}
   */
  @Test
  @DisplayName("Test toJsonTree(StateStoreTransaction); then return size is five")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"JsonElement TransactionSerDe.toJsonTree(StateStoreTransaction)"})
  void testToJsonTree_thenReturnSizeIsFive() {
    // Arrange
    TransactionSerDe forFileTransactionsResult = TransactionSerDe.forFileTransactions();
    AddFilesTransaction.Builder builderResult = AddFilesTransaction.builder();
    AddFilesTransaction.Builder taskIdResult = builderResult.files(new ArrayList<>())
        .jobId("42")
        .jobRunId("42")
        .taskId("42");
    AddFilesTransaction transaction = taskIdResult
        .writtenTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();

    // Act
    JsonElement actualToJsonTreeResult = forFileTransactionsResult.toJsonTree(transaction);

    // Assert
    assertTrue(actualToJsonTreeResult instanceof JsonObject);
    assertEquals(5, ((JsonObject) actualToJsonTreeResult).size());
    assertFalse(actualToJsonTreeResult.isJsonNull());
    assertFalse(((JsonObject) actualToJsonTreeResult).isEmpty());
    assertTrue(actualToJsonTreeResult.isJsonObject());
    assertSame(actualToJsonTreeResult, actualToJsonTreeResult.getAsJsonObject());
  }

  /**
   * Test {@link TransactionSerDe#toJsonTree(StateStoreTransaction)}.
   * <ul>
   *   <li>When {@link AddFilesTransaction#AddFilesTransaction(List)} with files is {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link TransactionSerDe#toJsonTree(StateStoreTransaction)}
   */
  @Test
  @DisplayName("Test toJsonTree(StateStoreTransaction); when AddFilesTransaction(List) with files is ArrayList(); then return size is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"JsonElement TransactionSerDe.toJsonTree(StateStoreTransaction)"})
  void testToJsonTree_whenAddFilesTransactionWithFilesIsArrayList_thenReturnSizeIsOne() {
    // Arrange
    TransactionSerDe forFileTransactionsResult = TransactionSerDe.forFileTransactions();

    // Act
    JsonElement actualToJsonTreeResult = forFileTransactionsResult
        .toJsonTree(new AddFilesTransaction(new ArrayList<>()));

    // Assert
    assertTrue(actualToJsonTreeResult instanceof JsonObject);
    assertEquals(1, ((JsonObject) actualToJsonTreeResult).size());
    assertFalse(actualToJsonTreeResult.isJsonNull());
    assertFalse(((JsonObject) actualToJsonTreeResult).isEmpty());
    assertTrue(actualToJsonTreeResult.isJsonObject());
    assertSame(actualToJsonTreeResult, actualToJsonTreeResult.getAsJsonObject());
  }

  /**
   * Test {@link TransactionSerDe#toJsonTree(StateStoreTransaction)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@link JsonNull#INSTANCE}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TransactionSerDe#toJsonTree(StateStoreTransaction)}
   */
  @Test
  @DisplayName("Test toJsonTree(StateStoreTransaction); when 'null'; then return INSTANCE")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"JsonElement TransactionSerDe.toJsonTree(StateStoreTransaction)"})
  void testToJsonTree_whenNull_thenReturnInstance() {
    // Arrange and Act
    JsonElement actualToJsonTreeResult = TransactionSerDe.forFileTransactions().toJsonTree(null);

    // Assert
    assertSame(((JsonNull) actualToJsonTreeResult).INSTANCE, actualToJsonTreeResult);
  }

  /**
   * Test {@link TransactionSerDe#toJsonTree(StateStoreTransaction)}.
   * <ul>
   *   <li>When {@link ReplaceFileReferencesTransaction#ReplaceFileReferencesTransaction(List)} with jobs is {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TransactionSerDe#toJsonTree(StateStoreTransaction)}
   */
  @Test
  @DisplayName("Test toJsonTree(StateStoreTransaction); when ReplaceFileReferencesTransaction(List) with jobs is ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"JsonElement TransactionSerDe.toJsonTree(StateStoreTransaction)"})
  void testToJsonTree_whenReplaceFileReferencesTransactionWithJobsIsArrayList() throws StateStoreException {
    // Arrange
    TransactionSerDe forFileTransactionsResult = TransactionSerDe.forFileTransactions();

    // Act
    JsonElement actualToJsonTreeResult = forFileTransactionsResult
        .toJsonTree(new ReplaceFileReferencesTransaction(new ArrayList<>()));

    // Assert
    assertTrue(actualToJsonTreeResult instanceof JsonObject);
    assertEquals(1, ((JsonObject) actualToJsonTreeResult).size());
    assertFalse(actualToJsonTreeResult.isJsonNull());
    assertFalse(((JsonObject) actualToJsonTreeResult).isEmpty());
    assertTrue(actualToJsonTreeResult.isJsonObject());
    assertSame(actualToJsonTreeResult, actualToJsonTreeResult.getAsJsonObject());
  }

  /**
   * Test {@link TransactionSerDe#toTransaction(TransactionType, JsonElement)} with {@code TransactionType}, {@code JsonElement}.
   * <p>
   * Method under test: {@link TransactionSerDe#toTransaction(TransactionType, JsonElement)}
   */
  @Test
  @DisplayName("Test toTransaction(TransactionType, JsonElement) with 'TransactionType', 'JsonElement'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"StateStoreTransaction TransactionSerDe.toTransaction(TransactionType, JsonElement)"})
  void testToTransactionWithTransactionTypeJsonElement() {
    // Arrange
    TransactionSerDe forFileTransactionsResult = TransactionSerDe.forFileTransactions();

    JsonObject json = new JsonObject();
    json.add("42", new JsonArray(3));

    // Act
    StateStoreTransaction<?> actualToTransactionResult = forFileTransactionsResult
        .toTransaction(TransactionType.CLEAR_FILES, json);

    // Assert
    assertTrue(actualToTransactionResult instanceof ClearFilesTransaction);
    assertFalse(actualToTransactionResult.isEmpty());
  }

  /**
   * Test {@link TransactionSerDe#toTransaction(TransactionType, JsonElement)} with {@code TransactionType}, {@code JsonElement}.
   * <ul>
   *   <li>Given valueOf two.</li>
   * </ul>
   * <p>
   * Method under test: {@link TransactionSerDe#toTransaction(TransactionType, JsonElement)}
   */
  @Test
  @DisplayName("Test toTransaction(TransactionType, JsonElement) with 'TransactionType', 'JsonElement'; given valueOf two")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"StateStoreTransaction TransactionSerDe.toTransaction(TransactionType, JsonElement)"})
  void testToTransactionWithTransactionTypeJsonElement_givenValueOfTwo() {
    // Arrange
    TransactionSerDe forFileTransactionsResult = TransactionSerDe.forFileTransactions();

    JsonObject json = new JsonObject();
    json.addProperty("42", Integer.valueOf(2));

    // Act
    StateStoreTransaction<?> actualToTransactionResult = forFileTransactionsResult
        .toTransaction(TransactionType.CLEAR_FILES, json);

    // Assert
    assertTrue(actualToTransactionResult instanceof ClearFilesTransaction);
    assertFalse(actualToTransactionResult.isEmpty());
  }

  /**
   * Test {@link TransactionSerDe#toTransaction(TransactionType, JsonElement)} with {@code TransactionType}, {@code JsonElement}.
   * <ul>
   *   <li>When {@link JsonNull} (default constructor).</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TransactionSerDe#toTransaction(TransactionType, JsonElement)}
   */
  @Test
  @DisplayName("Test toTransaction(TransactionType, JsonElement) with 'TransactionType', 'JsonElement'; when JsonNull (default constructor); then return 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"StateStoreTransaction TransactionSerDe.toTransaction(TransactionType, JsonElement)"})
  void testToTransactionWithTransactionTypeJsonElement_whenJsonNull_thenReturnNull() {
    // Arrange
    TransactionSerDe forFileTransactionsResult = TransactionSerDe.forFileTransactions();

    // Act and Assert
    assertNull(forFileTransactionsResult.toTransaction(TransactionType.ADD_FILES, new JsonNull()));
  }

  /**
   * Test {@link TransactionSerDe#toTransaction(TransactionType, JsonElement)} with {@code TransactionType}, {@code JsonElement}.
   * <ul>
   *   <li>When {@link JsonObject} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link TransactionSerDe#toTransaction(TransactionType, JsonElement)}
   */
  @Test
  @DisplayName("Test toTransaction(TransactionType, JsonElement) with 'TransactionType', 'JsonElement'; when JsonObject (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"StateStoreTransaction TransactionSerDe.toTransaction(TransactionType, JsonElement)"})
  void testToTransactionWithTransactionTypeJsonElement_whenJsonObject() {
    // Arrange
    TransactionSerDe forFileTransactionsResult = TransactionSerDe.forFileTransactions();

    // Act
    StateStoreTransaction<?> actualToTransactionResult = forFileTransactionsResult
        .toTransaction(TransactionType.CLEAR_FILES, new JsonObject());

    // Assert
    assertTrue(actualToTransactionResult instanceof ClearFilesTransaction);
    assertFalse(actualToTransactionResult.isEmpty());
  }

  /**
   * Test {@link TransactionSerDe#toTransaction(TransactionType, JsonElement)} with {@code TransactionType}, {@code JsonElement}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TransactionSerDe#toTransaction(TransactionType, JsonElement)}
   */
  @Test
  @DisplayName("Test toTransaction(TransactionType, JsonElement) with 'TransactionType', 'JsonElement'; when 'null'; then return 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"StateStoreTransaction TransactionSerDe.toTransaction(TransactionType, JsonElement)"})
  void testToTransactionWithTransactionTypeJsonElement_whenNull_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(TransactionSerDe.forFileTransactions().toTransaction(TransactionType.ADD_FILES, (JsonElement) null));
  }

  /**
   * Test {@link TransactionSerDe#toTransaction(TransactionType, String)} with {@code TransactionType}, {@code String}.
   * <ul>
   *   <li>When empty string.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TransactionSerDe#toTransaction(TransactionType, String)}
   */
  @Test
  @DisplayName("Test toTransaction(TransactionType, String) with 'TransactionType', 'String'; when empty string; then return 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"StateStoreTransaction TransactionSerDe.toTransaction(TransactionType, String)"})
  void testToTransactionWithTransactionTypeString_whenEmptyString_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(TransactionSerDe.forFileTransactions().toTransaction(TransactionType.ADD_FILES, ""));
  }
}
