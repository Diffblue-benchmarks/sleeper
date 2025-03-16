package sleeper.core.statestore.commit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.table.TableProperties;
import sleeper.core.properties.table.TablePropertiesProvider;
import sleeper.core.properties.table.TablePropertiesStore;
import sleeper.core.properties.table.TablePropertiesStore.Client;
import sleeper.core.schema.Field;
import sleeper.core.schema.Schema;
import sleeper.core.schema.type.ByteArrayType;
import sleeper.core.statestore.AllReferencesToAFile;
import sleeper.core.statestore.AllReferencesToAFile.Builder;
import sleeper.core.statestore.FileReference;
import sleeper.core.statestore.transactionlog.transaction.TransactionSerDeProvider;
import sleeper.core.statestore.transactionlog.transaction.TransactionType;
import sleeper.core.statestore.transactionlog.transaction.impl.AddFilesTransaction;
import sleeper.core.table.InMemoryTableIndex;

class StateStoreCommitRequestSerDeDiffblueTest {
  /**
   * Test {@link StateStoreCommitRequestSerDe#StateStoreCommitRequestSerDe(TablePropertiesProvider)}.
   * <p>
   * Method under test: {@link StateStoreCommitRequestSerDe#StateStoreCommitRequestSerDe(TablePropertiesProvider)}
   */
  @Test
  @DisplayName("Test new StateStoreCommitRequestSerDe(TablePropertiesProvider)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void StateStoreCommitRequestSerDe.<init>(TablePropertiesProvider)"})
  void testNewStateStoreCommitRequestSerDe() {
    // Arrange
    InstanceProperties instanceProperties = new InstanceProperties();

    // Act
    StateStoreCommitRequestSerDe actualStateStoreCommitRequestSerDe = new StateStoreCommitRequestSerDe(
        new TablePropertiesProvider(instanceProperties,
            new TablePropertiesStore(new InMemoryTableIndex(), mock(Client.class))));

    // Assert
    assertEquals("null", actualStateStoreCommitRequestSerDe.toJson(null));
    assertEquals("null", actualStateStoreCommitRequestSerDe.toJsonPrettyPrint(null));
  }

  /**
   * Test {@link StateStoreCommitRequestSerDe#StateStoreCommitRequestSerDe(TransactionSerDeProvider)}.
   * <p>
   * Method under test: {@link StateStoreCommitRequestSerDe#StateStoreCommitRequestSerDe(TransactionSerDeProvider)}
   */
  @Test
  @DisplayName("Test new StateStoreCommitRequestSerDe(TransactionSerDeProvider)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void StateStoreCommitRequestSerDe.<init>(TransactionSerDeProvider)"})
  void testNewStateStoreCommitRequestSerDe2() {
    // Arrange and Act
    StateStoreCommitRequestSerDe actualStateStoreCommitRequestSerDe = new StateStoreCommitRequestSerDe(
        mock(TransactionSerDeProvider.class));

    // Assert
    assertEquals("null", actualStateStoreCommitRequestSerDe.toJson(null));
    assertEquals("null", actualStateStoreCommitRequestSerDe.toJsonPrettyPrint(null));
  }

  /**
   * Test {@link StateStoreCommitRequestSerDe#StateStoreCommitRequestSerDe(TableProperties)}.
   * <ul>
   *   <li>Then return toJson {@code null} is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreCommitRequestSerDe#StateStoreCommitRequestSerDe(TableProperties)}
   */
  @Test
  @DisplayName("Test new StateStoreCommitRequestSerDe(TableProperties); then return toJson 'null' is 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void StateStoreCommitRequestSerDe.<init>(TableProperties)"})
  void testNewStateStoreCommitRequestSerDe_thenReturnToJsonNullIsNull() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Schema.Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Schema.Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();

    TableProperties tableProperties = new TableProperties(new InstanceProperties());
    tableProperties.setSchema(schema);

    // Act
    StateStoreCommitRequestSerDe actualStateStoreCommitRequestSerDe = new StateStoreCommitRequestSerDe(tableProperties);

    // Assert
    assertEquals("null", actualStateStoreCommitRequestSerDe.toJson(null));
    assertEquals("null", actualStateStoreCommitRequestSerDe.toJsonPrettyPrint(null));
  }

  /**
   * Test {@link StateStoreCommitRequestSerDe#forFileTransactions()}.
   * <p>
   * Method under test: {@link StateStoreCommitRequestSerDe#forFileTransactions()}
   */
  @Test
  @DisplayName("Test forFileTransactions()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"StateStoreCommitRequestSerDe StateStoreCommitRequestSerDe.forFileTransactions()"})
  void testForFileTransactions() {
    // Arrange and Act
    StateStoreCommitRequestSerDe actualForFileTransactionsResult = StateStoreCommitRequestSerDe.forFileTransactions();

    // Assert
    assertEquals("null", actualForFileTransactionsResult.toJson(null));
    assertEquals("null", actualForFileTransactionsResult.toJsonPrettyPrint(null));
  }

  /**
   * Test {@link StateStoreCommitRequestSerDe#toJson(StateStoreCommitRequest)}.
   * <p>
   * Method under test: {@link StateStoreCommitRequestSerDe#toJson(StateStoreCommitRequest)}
   */
  @Test
  @DisplayName("Test toJson(StateStoreCommitRequest)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String StateStoreCommitRequestSerDe.toJson(StateStoreCommitRequest)"})
  void testToJson() {
    // Arrange
    StateStoreCommitRequestSerDe forFileTransactionsResult = StateStoreCommitRequestSerDe.forFileTransactions();

    // Act and Assert
    assertEquals("{\"tableId\":\"42\",\"transactionType\":\"ADD_FILES\",\"bodyKey\":\"Not all who wander are lost\"}",
        forFileTransactionsResult
            .toJson(StateStoreCommitRequest.create("42", "Not all who wander are lost", TransactionType.ADD_FILES)));
  }

  /**
   * Test {@link StateStoreCommitRequestSerDe#toJson(StateStoreCommitRequest)}.
   * <p>
   * Method under test: {@link StateStoreCommitRequestSerDe#toJson(StateStoreCommitRequest)}
   */
  @Test
  @DisplayName("Test toJson(StateStoreCommitRequest)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String StateStoreCommitRequestSerDe.toJson(StateStoreCommitRequest)"})
  void testToJson2() {
    // Arrange
    StateStoreCommitRequestSerDe forFileTransactionsResult = StateStoreCommitRequestSerDe.forFileTransactions();

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
        "{\"tableId\":\"42\",\"transactionType\":\"ADD_FILES\",\"transaction\":{\"files\":[{\"filename\":\"foo.txt\",\"references"
            + "\":[{\"partitionId\":\"42\",\"numberOfRecords\":1,\"jobId\":\"42\",\"countApproximate\":true,\"onlyContainsDataFor"
            + "ThisPartition\":true}]}]}}",
        forFileTransactionsResult.toJson(StateStoreCommitRequest.create("42", new AddFilesTransaction(files))));
  }

  /**
   * Test {@link StateStoreCommitRequestSerDe#toJson(StateStoreCommitRequest)}.
   * <p>
   * Method under test: {@link StateStoreCommitRequestSerDe#toJson(StateStoreCommitRequest)}
   */
  @Test
  @DisplayName("Test toJson(StateStoreCommitRequest)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String StateStoreCommitRequestSerDe.toJson(StateStoreCommitRequest)"})
  void testToJson3() {
    // Arrange
    StateStoreCommitRequestSerDe forFileTransactionsResult = StateStoreCommitRequestSerDe.forFileTransactions();

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
        "{\"tableId\":\"42\",\"transactionType\":\"ADD_FILES\",\"transaction\":{\"files\":[{\"filename\":\"foo.txt\",\"references"
            + "\":[{\"partitionId\":\"42\",\"numberOfRecords\":1,\"jobId\":\"42\",\"countApproximate\":true,\"onlyContainsDataFor"
            + "ThisPartition\":true},{\"partitionId\":\"42\",\"numberOfRecords\":1,\"jobId\":\"42\",\"countApproximate\":true,"
            + "\"onlyContainsDataForThisPartition\":true}]}]}}",
        forFileTransactionsResult.toJson(StateStoreCommitRequest.create("42", new AddFilesTransaction(files))));
  }

  /**
   * Test {@link StateStoreCommitRequestSerDe#toJson(StateStoreCommitRequest)}.
   * <p>
   * Method under test: {@link StateStoreCommitRequestSerDe#toJson(StateStoreCommitRequest)}
   */
  @Test
  @DisplayName("Test toJson(StateStoreCommitRequest)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String StateStoreCommitRequestSerDe.toJson(StateStoreCommitRequest)"})
  void testToJson4() {
    // Arrange
    StateStoreCommitRequestSerDe forFileTransactionsResult = StateStoreCommitRequestSerDe.forFileTransactions();

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
        "{\"tableId\":\"42\",\"transactionType\":\"ADD_FILES\",\"transaction\":{\"files\":[{\"filename\":\"foo.txt\",\"references"
            + "\":[{\"partitionId\":\"42\",\"numberOfRecords\":1,\"jobId\":\"42\",\"countApproximate\":false,\"onlyContainsDataFo"
            + "rThisPartition\":true}]}]}}",
        forFileTransactionsResult.toJson(StateStoreCommitRequest.create("42", new AddFilesTransaction(files))));
  }

  /**
   * Test {@link StateStoreCommitRequestSerDe#toJson(StateStoreCommitRequest)}.
   * <ul>
   *   <li>Given forFileTransactions.</li>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreCommitRequestSerDe#toJson(StateStoreCommitRequest)}
   */
  @Test
  @DisplayName("Test toJson(StateStoreCommitRequest); given forFileTransactions; when 'null'; then return 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String StateStoreCommitRequestSerDe.toJson(StateStoreCommitRequest)"})
  void testToJson_givenForFileTransactions_whenNull_thenReturnNull() {
    // Arrange, Act and Assert
    assertEquals("null", StateStoreCommitRequestSerDe.forFileTransactions().toJson(null));
  }

  /**
   * Test {@link StateStoreCommitRequestSerDe#toJson(StateStoreCommitRequest)}.
   * <ul>
   *   <li>Then return a string.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreCommitRequestSerDe#toJson(StateStoreCommitRequest)}
   */
  @Test
  @DisplayName("Test toJson(StateStoreCommitRequest); then return a string")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String StateStoreCommitRequestSerDe.toJson(StateStoreCommitRequest)"})
  void testToJson_thenReturnAString() {
    // Arrange
    StateStoreCommitRequestSerDe forFileTransactionsResult = StateStoreCommitRequestSerDe.forFileTransactions();

    ArrayList<AllReferencesToAFile> files = new ArrayList<>();
    Builder filenameResult = AllReferencesToAFile.builder().filename("foo.txt");
    Builder lastStateStoreUpdateTimeResult = filenameResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    AllReferencesToAFile buildResult = lastStateStoreUpdateTimeResult.references(new ArrayList<>()).build();
    files.add(buildResult);

    // Act and Assert
    assertEquals(
        "{\"tableId\":\"42\",\"transactionType\":\"ADD_FILES\",\"transaction\":{\"files\":[{\"filename\":\"foo.txt\",\"references"
            + "\":[]}]}}",
        forFileTransactionsResult.toJson(StateStoreCommitRequest.create("42", new AddFilesTransaction(files))));
  }

  /**
   * Test {@link StateStoreCommitRequestSerDe#toJson(StateStoreCommitRequest)}.
   * <ul>
   *   <li>Then return a string.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreCommitRequestSerDe#toJson(StateStoreCommitRequest)}
   */
  @Test
  @DisplayName("Test toJson(StateStoreCommitRequest); then return a string")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String StateStoreCommitRequestSerDe.toJson(StateStoreCommitRequest)"})
  void testToJson_thenReturnAString2() {
    // Arrange
    StateStoreCommitRequestSerDe forFileTransactionsResult = StateStoreCommitRequestSerDe.forFileTransactions();

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
        "{\"tableId\":\"42\",\"transactionType\":\"ADD_FILES\",\"transaction\":{\"files\":[{\"filename\":\"foo.txt\",\"references"
            + "\":[]},{\"filename\":\"foo.txt\",\"references\":[]}]}}",
        forFileTransactionsResult.toJson(StateStoreCommitRequest.create("42", new AddFilesTransaction(files))));
  }

  /**
   * Test {@link StateStoreCommitRequestSerDe#toJson(StateStoreCommitRequest)}.
   * <ul>
   *   <li>Then return {@code {"tableId":"42","transactionType":"ADD_FILES","transaction":{"files":[]}}}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreCommitRequestSerDe#toJson(StateStoreCommitRequest)}
   */
  @Test
  @DisplayName("Test toJson(StateStoreCommitRequest); then return '{\"tableId\":\"42\",\"transactionType\":\"ADD_FILES\",\"transaction\":{\"files\":[]}}'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String StateStoreCommitRequestSerDe.toJson(StateStoreCommitRequest)"})
  void testToJson_thenReturnTableId42TransactionTypeAddFilesTransactionFiles() {
    // Arrange
    StateStoreCommitRequestSerDe forFileTransactionsResult = StateStoreCommitRequestSerDe.forFileTransactions();

    // Act and Assert
    assertEquals("{\"tableId\":\"42\",\"transactionType\":\"ADD_FILES\",\"transaction\":{\"files\":[]}}",
        forFileTransactionsResult
            .toJson(StateStoreCommitRequest.create("42", new AddFilesTransaction(new ArrayList<>()))));
  }

  /**
   * Test {@link StateStoreCommitRequestSerDe#toJson(StateStoreCommitRequest)}.
   * <ul>
   *   <li>Then return {@code {"tableId":"","transactionType":"ADD_FILES","bodyKey":"Not all who wander are lost"}}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreCommitRequestSerDe#toJson(StateStoreCommitRequest)}
   */
  @Test
  @DisplayName("Test toJson(StateStoreCommitRequest); then return '{\"tableId\":\"\",\"transactionType\":\"ADD_FILES\",\"bodyKey\":\"Not all who wander are lost\"}'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String StateStoreCommitRequestSerDe.toJson(StateStoreCommitRequest)"})
  void testToJson_thenReturnTableIdTransactionTypeAddFilesBodyKeyNotAllWhoWanderAreLost() {
    // Arrange
    StateStoreCommitRequestSerDe forFileTransactionsResult = StateStoreCommitRequestSerDe.forFileTransactions();

    // Act and Assert
    assertEquals("{\"tableId\":\"\",\"transactionType\":\"ADD_FILES\",\"bodyKey\":\"Not all who wander are lost\"}",
        forFileTransactionsResult
            .toJson(StateStoreCommitRequest.create("", "Not all who wander are lost", TransactionType.ADD_FILES)));
  }

  /**
   * Test {@link StateStoreCommitRequestSerDe#toJsonPrettyPrint(StateStoreCommitRequest)}.
   * <p>
   * Method under test: {@link StateStoreCommitRequestSerDe#toJsonPrettyPrint(StateStoreCommitRequest)}
   */
  @Test
  @DisplayName("Test toJsonPrettyPrint(StateStoreCommitRequest)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String StateStoreCommitRequestSerDe.toJsonPrettyPrint(StateStoreCommitRequest)"})
  void testToJsonPrettyPrint() {
    // Arrange
    StateStoreCommitRequestSerDe forFileTransactionsResult = StateStoreCommitRequestSerDe.forFileTransactions();

    // Act and Assert
    assertEquals(
        "{\n  \"tableId\": \"42\",\n  \"transactionType\": \"ADD_FILES\",\n  \"bodyKey\": \"Not all who wander are lost\"\n}",
        forFileTransactionsResult.toJsonPrettyPrint(
            StateStoreCommitRequest.create("42", "Not all who wander are lost", TransactionType.ADD_FILES)));
  }

  /**
   * Test {@link StateStoreCommitRequestSerDe#toJsonPrettyPrint(StateStoreCommitRequest)}.
   * <p>
   * Method under test: {@link StateStoreCommitRequestSerDe#toJsonPrettyPrint(StateStoreCommitRequest)}
   */
  @Test
  @DisplayName("Test toJsonPrettyPrint(StateStoreCommitRequest)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String StateStoreCommitRequestSerDe.toJsonPrettyPrint(StateStoreCommitRequest)"})
  void testToJsonPrettyPrint2() {
    // Arrange
    StateStoreCommitRequestSerDe forFileTransactionsResult = StateStoreCommitRequestSerDe.forFileTransactions();

    // Act and Assert
    assertEquals(
        "{\n  \"tableId\": \"\",\n  \"transactionType\": \"ADD_FILES\",\n  \"bodyKey\": \"Not all who wander are lost\"\n}",
        forFileTransactionsResult.toJsonPrettyPrint(
            StateStoreCommitRequest.create("", "Not all who wander are lost", TransactionType.ADD_FILES)));
  }

  /**
   * Test {@link StateStoreCommitRequestSerDe#toJsonPrettyPrint(StateStoreCommitRequest)}.
   * <p>
   * Method under test: {@link StateStoreCommitRequestSerDe#toJsonPrettyPrint(StateStoreCommitRequest)}
   */
  @Test
  @DisplayName("Test toJsonPrettyPrint(StateStoreCommitRequest)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String StateStoreCommitRequestSerDe.toJsonPrettyPrint(StateStoreCommitRequest)"})
  void testToJsonPrettyPrint3() {
    // Arrange
    StateStoreCommitRequestSerDe forFileTransactionsResult = StateStoreCommitRequestSerDe.forFileTransactions();

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
        "{\n" + "  \"tableId\": \"42\",\n" + "  \"transactionType\": \"ADD_FILES\",\n" + "  \"transaction\": {\n"
            + "    \"files\": [\n" + "      {\n" + "        \"filename\": \"foo.txt\",\n"
            + "        \"references\": [\n" + "          {\n" + "            \"partitionId\": \"42\",\n"
            + "            \"numberOfRecords\": 1,\n" + "            \"jobId\": \"42\",\n"
            + "            \"countApproximate\": true,\n" + "            \"onlyContainsDataForThisPartition\": true\n"
            + "          }\n" + "        ]\n" + "      }\n" + "    ]\n" + "  }\n" + "}",
        forFileTransactionsResult
            .toJsonPrettyPrint(StateStoreCommitRequest.create("42", new AddFilesTransaction(files))));
  }

  /**
   * Test {@link StateStoreCommitRequestSerDe#toJsonPrettyPrint(StateStoreCommitRequest)}.
   * <p>
   * Method under test: {@link StateStoreCommitRequestSerDe#toJsonPrettyPrint(StateStoreCommitRequest)}
   */
  @Test
  @DisplayName("Test toJsonPrettyPrint(StateStoreCommitRequest)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String StateStoreCommitRequestSerDe.toJsonPrettyPrint(StateStoreCommitRequest)"})
  void testToJsonPrettyPrint4() {
    // Arrange
    StateStoreCommitRequestSerDe forFileTransactionsResult = StateStoreCommitRequestSerDe.forFileTransactions();

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
        "{\n" + "  \"tableId\": \"42\",\n" + "  \"transactionType\": \"ADD_FILES\",\n" + "  \"transaction\": {\n"
            + "    \"files\": [\n" + "      {\n" + "        \"filename\": \"foo.txt\",\n"
            + "        \"references\": [\n" + "          {\n" + "            \"partitionId\": \"42\",\n"
            + "            \"numberOfRecords\": 1,\n" + "            \"jobId\": \"42\",\n"
            + "            \"countApproximate\": true,\n" + "            \"onlyContainsDataForThisPartition\": true\n"
            + "          },\n" + "          {\n" + "            \"partitionId\": \"42\",\n"
            + "            \"numberOfRecords\": 1,\n" + "            \"jobId\": \"42\",\n"
            + "            \"countApproximate\": true,\n" + "            \"onlyContainsDataForThisPartition\": true\n"
            + "          }\n" + "        ]\n" + "      }\n" + "    ]\n" + "  }\n" + "}",
        forFileTransactionsResult
            .toJsonPrettyPrint(StateStoreCommitRequest.create("42", new AddFilesTransaction(files))));
  }

  /**
   * Test {@link StateStoreCommitRequestSerDe#toJsonPrettyPrint(StateStoreCommitRequest)}.
   * <p>
   * Method under test: {@link StateStoreCommitRequestSerDe#toJsonPrettyPrint(StateStoreCommitRequest)}
   */
  @Test
  @DisplayName("Test toJsonPrettyPrint(StateStoreCommitRequest)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String StateStoreCommitRequestSerDe.toJsonPrettyPrint(StateStoreCommitRequest)"})
  void testToJsonPrettyPrint5() {
    // Arrange
    StateStoreCommitRequestSerDe forFileTransactionsResult = StateStoreCommitRequestSerDe.forFileTransactions();

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
        "{\n" + "  \"tableId\": \"42\",\n" + "  \"transactionType\": \"ADD_FILES\",\n" + "  \"transaction\": {\n"
            + "    \"files\": [\n" + "      {\n" + "        \"filename\": \"foo.txt\",\n"
            + "        \"references\": [\n" + "          {\n" + "            \"partitionId\": \"42\",\n"
            + "            \"numberOfRecords\": 1,\n" + "            \"jobId\": \"42\",\n"
            + "            \"countApproximate\": false,\n" + "            \"onlyContainsDataForThisPartition\": true\n"
            + "          }\n" + "        ]\n" + "      }\n" + "    ]\n" + "  }\n" + "}",
        forFileTransactionsResult
            .toJsonPrettyPrint(StateStoreCommitRequest.create("42", new AddFilesTransaction(files))));
  }

  /**
   * Test {@link StateStoreCommitRequestSerDe#toJsonPrettyPrint(StateStoreCommitRequest)}.
   * <ul>
   *   <li>Given forFileTransactions.</li>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreCommitRequestSerDe#toJsonPrettyPrint(StateStoreCommitRequest)}
   */
  @Test
  @DisplayName("Test toJsonPrettyPrint(StateStoreCommitRequest); given forFileTransactions; when 'null'; then return 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String StateStoreCommitRequestSerDe.toJsonPrettyPrint(StateStoreCommitRequest)"})
  void testToJsonPrettyPrint_givenForFileTransactions_whenNull_thenReturnNull() {
    // Arrange, Act and Assert
    assertEquals("null", StateStoreCommitRequestSerDe.forFileTransactions().toJsonPrettyPrint(null));
  }

  /**
   * Test {@link StateStoreCommitRequestSerDe#toJsonPrettyPrint(StateStoreCommitRequest)}.
   * <ul>
   *   <li>Then return a string.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreCommitRequestSerDe#toJsonPrettyPrint(StateStoreCommitRequest)}
   */
  @Test
  @DisplayName("Test toJsonPrettyPrint(StateStoreCommitRequest); then return a string")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String StateStoreCommitRequestSerDe.toJsonPrettyPrint(StateStoreCommitRequest)"})
  void testToJsonPrettyPrint_thenReturnAString() {
    // Arrange
    StateStoreCommitRequestSerDe forFileTransactionsResult = StateStoreCommitRequestSerDe.forFileTransactions();

    ArrayList<AllReferencesToAFile> files = new ArrayList<>();
    Builder filenameResult = AllReferencesToAFile.builder().filename("foo.txt");
    Builder lastStateStoreUpdateTimeResult = filenameResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    AllReferencesToAFile buildResult = lastStateStoreUpdateTimeResult.references(new ArrayList<>()).build();
    files.add(buildResult);

    // Act and Assert
    assertEquals(
        "{\n" + "  \"tableId\": \"42\",\n" + "  \"transactionType\": \"ADD_FILES\",\n" + "  \"transaction\": {\n"
            + "    \"files\": [\n" + "      {\n" + "        \"filename\": \"foo.txt\",\n"
            + "        \"references\": []\n" + "      }\n" + "    ]\n" + "  }\n" + "}",
        forFileTransactionsResult
            .toJsonPrettyPrint(StateStoreCommitRequest.create("42", new AddFilesTransaction(files))));
  }

  /**
   * Test {@link StateStoreCommitRequestSerDe#toJsonPrettyPrint(StateStoreCommitRequest)}.
   * <ul>
   *   <li>Then return a string.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreCommitRequestSerDe#toJsonPrettyPrint(StateStoreCommitRequest)}
   */
  @Test
  @DisplayName("Test toJsonPrettyPrint(StateStoreCommitRequest); then return a string")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String StateStoreCommitRequestSerDe.toJsonPrettyPrint(StateStoreCommitRequest)"})
  void testToJsonPrettyPrint_thenReturnAString2() {
    // Arrange
    StateStoreCommitRequestSerDe forFileTransactionsResult = StateStoreCommitRequestSerDe.forFileTransactions();

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
        "{\n" + "  \"tableId\": \"42\",\n" + "  \"transactionType\": \"ADD_FILES\",\n" + "  \"transaction\": {\n"
            + "    \"files\": [\n" + "      {\n" + "        \"filename\": \"foo.txt\",\n"
            + "        \"references\": []\n" + "      },\n" + "      {\n" + "        \"filename\": \"foo.txt\",\n"
            + "        \"references\": []\n" + "      }\n" + "    ]\n" + "  }\n" + "}",
        forFileTransactionsResult
            .toJsonPrettyPrint(StateStoreCommitRequest.create("42", new AddFilesTransaction(files))));
  }

  /**
   * Test {@link StateStoreCommitRequestSerDe#toJsonPrettyPrint(StateStoreCommitRequest)}.
   * <ul>
   *   <li>Then return {@code { "tableId": "42", "transactionType": "ADD_FILES", "transaction": { "files": [] } }}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreCommitRequestSerDe#toJsonPrettyPrint(StateStoreCommitRequest)}
   */
  @Test
  @DisplayName("Test toJsonPrettyPrint(StateStoreCommitRequest); then return '{ \"tableId\": \"42\", \"transactionType\": \"ADD_FILES\", \"transaction\": { \"files\": [] } }'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String StateStoreCommitRequestSerDe.toJsonPrettyPrint(StateStoreCommitRequest)"})
  void testToJsonPrettyPrint_thenReturnTableId42TransactionTypeAddFilesTransactionFiles() {
    // Arrange
    StateStoreCommitRequestSerDe forFileTransactionsResult = StateStoreCommitRequestSerDe.forFileTransactions();

    // Act and Assert
    assertEquals(
        "{\n  \"tableId\": \"42\",\n  \"transactionType\": \"ADD_FILES\",\n  \"transaction\": {\n    \"files\": []\n  }\n}",
        forFileTransactionsResult
            .toJsonPrettyPrint(StateStoreCommitRequest.create("42", new AddFilesTransaction(new ArrayList<>()))));
  }

  /**
   * Test {@link StateStoreCommitRequestSerDe#fromJson(String)}.
   * <ul>
   *   <li>When empty string.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreCommitRequestSerDe#fromJson(String)}
   */
  @Test
  @DisplayName("Test fromJson(String); when empty string; then return 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"StateStoreCommitRequest StateStoreCommitRequestSerDe.fromJson(String)"})
  void testFromJson_whenEmptyString_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(StateStoreCommitRequestSerDe.forFileTransactions().fromJson(""));
  }
}
