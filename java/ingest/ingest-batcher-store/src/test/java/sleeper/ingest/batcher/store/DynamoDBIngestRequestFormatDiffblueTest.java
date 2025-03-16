package sleeper.ingest.batcher.store;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Map;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.properties.SleeperPropertiesInvalidException;
import sleeper.core.properties.SleeperPropertyValues;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.table.TableProperties;
import sleeper.core.properties.table.TablePropertiesProvider;
import sleeper.core.properties.table.TablePropertiesStore;
import sleeper.core.properties.table.TablePropertiesStore.Client;
import sleeper.core.properties.table.TableProperty;
import sleeper.core.table.InMemoryTableIndex;
import sleeper.core.table.TableStatus;
import sleeper.ingest.batcher.core.FileIngestRequest;
import sleeper.ingest.batcher.core.FileIngestRequest.Builder;

class DynamoDBIngestRequestFormatDiffblueTest {
  /**
   * Test {@link DynamoDBIngestRequestFormat#createRecord(TablePropertiesProvider, FileIngestRequest)}.
   * <ul>
   *   <li>Given {@link TableProperties} {@link SleeperPropertyValues#getInt(SleeperProperty)} return one.</li>
   *   <li>Then return {@link DynamoDBIngestRequestFormat#EXPIRY_TIME} N is {@code 60}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBIngestRequestFormat#createRecord(TablePropertiesProvider, FileIngestRequest)}
   */
  @Test
  @DisplayName("Test createRecord(TablePropertiesProvider, FileIngestRequest); given TableProperties getInt(SleeperProperty) return one; then return EXPIRY_TIME N is '60'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Map DynamoDBIngestRequestFormat.createRecord(TablePropertiesProvider, FileIngestRequest)"})
  void testCreateRecord_givenTablePropertiesGetIntReturnOne_thenReturnExpiry_timeNIs60()
      throws SleeperPropertiesInvalidException {
    // Arrange
    InMemoryTableIndex tableIndex = mock(InMemoryTableIndex.class);
    Optional<TableStatus> ofResult = Optional.of(TableStatus.uniqueIdAndName("42", "Table Name", true));
    when(tableIndex.getTableByUniqueId(Mockito.<String>any())).thenReturn(ofResult);
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.getInt(Mockito.<TableProperty>any())).thenReturn(1);
    doNothing().when(tableProperties).validate();
    when(tableProperties.get(Mockito.<TableProperty>any())).thenReturn("Get");
    when(tableProperties.getStatus()).thenReturn(TableStatus.uniqueIdAndName("42", "Table Name", true));
    Client client = mock(Client.class);
    when(client.loadProperties(Mockito.<TableStatus>any())).thenReturn(tableProperties);
    TablePropertiesStore propertiesStore = new TablePropertiesStore(tableIndex, client);

    TablePropertiesProvider tablePropertiesProvider = new TablePropertiesProvider(new InstanceProperties(),
        propertiesStore);

    Builder jobIdResult = FileIngestRequest.builder().file("File").fileSizeBytes(3L).jobId("42");
    FileIngestRequest fileIngestRequest = jobIdResult
        .receivedTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .tableId("42")
        .build();

    // Act
    Map<String, AttributeValue> actualCreateRecordResult = DynamoDBIngestRequestFormat
        .createRecord(tablePropertiesProvider, fileIngestRequest);

    // Assert
    verify(tableProperties).validate();
    verify(tableProperties).getInt(isA(TableProperty.class));
    verify(tableProperties, atLeast(1)).get(Mockito.<TableProperty>any());
    verify(tableProperties).getStatus();
    verify(client).loadProperties(isA(TableStatus.class));
    verify(tableIndex).getTableByUniqueId(eq("42"));
    assertEquals(5, actualCreateRecordResult.size());
    AttributeValue getResult = actualCreateRecordResult.get(DynamoDBIngestRequestFormat.RECEIVED_TIME);
    assertEquals("0", getResult.getN());
    AttributeValue getResult2 = actualCreateRecordResult.get(DynamoDBIngestRequestFormat.FILE_SIZE);
    assertEquals("3", getResult2.getN());
    AttributeValue getResult3 = actualCreateRecordResult.get(DynamoDBIngestRequestFormat.JOB_ID);
    assertEquals("42", getResult3.getS());
    AttributeValue getResult4 = actualCreateRecordResult.get(DynamoDBIngestRequestFormat.FILE_PATH);
    assertEquals("42/File", getResult4.getS());
    AttributeValue getResult5 = actualCreateRecordResult.get(DynamoDBIngestRequestFormat.EXPIRY_TIME);
    assertEquals("60", getResult5.getN());
    assertNull(getResult5.getBOOL());
    assertNull(getResult4.getBOOL());
    assertNull(getResult2.getBOOL());
    assertNull(getResult3.getBOOL());
    assertNull(getResult.getBOOL());
    assertNull(getResult5.getNULL());
    assertNull(getResult4.getNULL());
    assertNull(getResult2.getNULL());
    assertNull(getResult3.getNULL());
    assertNull(getResult.getNULL());
    assertNull(getResult5.isBOOL());
    assertNull(getResult4.isBOOL());
    assertNull(getResult2.isBOOL());
    assertNull(getResult3.isBOOL());
    assertNull(getResult.isBOOL());
    assertNull(getResult5.isNULL());
    assertNull(getResult4.isNULL());
    assertNull(getResult2.isNULL());
    assertNull(getResult3.isNULL());
    assertNull(getResult.isNULL());
    assertNull(getResult4.getN());
    assertNull(getResult3.getN());
    assertNull(getResult5.getS());
    assertNull(getResult2.getS());
    assertNull(getResult.getS());
    assertNull(getResult5.getB());
    assertNull(getResult4.getB());
    assertNull(getResult2.getB());
    assertNull(getResult3.getB());
    assertNull(getResult.getB());
    assertNull(getResult5.getL());
    assertNull(getResult4.getL());
    assertNull(getResult2.getL());
    assertNull(getResult3.getL());
    assertNull(getResult.getL());
    assertNull(getResult5.getNS());
    assertNull(getResult4.getNS());
    assertNull(getResult2.getNS());
    assertNull(getResult3.getNS());
    assertNull(getResult.getNS());
    assertNull(getResult5.getSS());
    assertNull(getResult4.getSS());
    assertNull(getResult2.getSS());
    assertNull(getResult3.getSS());
    assertNull(getResult.getSS());
    assertNull(getResult5.getBS());
    assertNull(getResult4.getBS());
    assertNull(getResult2.getBS());
    assertNull(getResult3.getBS());
    assertNull(getResult.getBS());
    assertNull(getResult5.getM());
    assertNull(getResult4.getM());
    assertNull(getResult2.getM());
    assertNull(getResult3.getM());
    assertNull(getResult.getM());
  }

  /**
   * Test {@link DynamoDBIngestRequestFormat#createRecord(TablePropertiesProvider, FileIngestRequest)}.
   * <ul>
   *   <li>Then calls {@link TablePropertiesProvider#getById(String)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBIngestRequestFormat#createRecord(TablePropertiesProvider, FileIngestRequest)}
   */
  @Test
  @DisplayName("Test createRecord(TablePropertiesProvider, FileIngestRequest); then calls getById(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Map DynamoDBIngestRequestFormat.createRecord(TablePropertiesProvider, FileIngestRequest)"})
  void testCreateRecord_thenCallsGetById() {
    // Arrange
    TablePropertiesProvider tablePropertiesProvider = mock(TablePropertiesProvider.class);
    when(tablePropertiesProvider.getById(Mockito.<String>any()))
        .thenReturn(new TableProperties(new InstanceProperties()));
    Builder jobIdResult = FileIngestRequest.builder().file("File").fileSizeBytes(3L).jobId("42");
    FileIngestRequest fileIngestRequest = jobIdResult
        .receivedTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .tableId("42")
        .build();

    // Act
    Map<String, AttributeValue> actualCreateRecordResult = DynamoDBIngestRequestFormat
        .createRecord(tablePropertiesProvider, fileIngestRequest);

    // Assert
    verify(tablePropertiesProvider).getById(eq("42"));
    assertEquals(5, actualCreateRecordResult.size());
    AttributeValue getResult = actualCreateRecordResult.get(DynamoDBIngestRequestFormat.RECEIVED_TIME);
    assertEquals("0", getResult.getN());
    AttributeValue getResult2 = actualCreateRecordResult.get(DynamoDBIngestRequestFormat.FILE_SIZE);
    assertEquals("3", getResult2.getN());
    AttributeValue getResult3 = actualCreateRecordResult.get(DynamoDBIngestRequestFormat.JOB_ID);
    assertEquals("42", getResult3.getS());
    AttributeValue getResult4 = actualCreateRecordResult.get(DynamoDBIngestRequestFormat.FILE_PATH);
    assertEquals("42/File", getResult4.getS());
    AttributeValue getResult5 = actualCreateRecordResult.get(DynamoDBIngestRequestFormat.EXPIRY_TIME);
    assertEquals("604800", getResult5.getN());
    assertNull(getResult5.getBOOL());
    assertNull(getResult4.getBOOL());
    assertNull(getResult2.getBOOL());
    assertNull(getResult3.getBOOL());
    assertNull(getResult.getBOOL());
    assertNull(getResult5.getNULL());
    assertNull(getResult4.getNULL());
    assertNull(getResult2.getNULL());
    assertNull(getResult3.getNULL());
    assertNull(getResult.getNULL());
    assertNull(getResult5.isBOOL());
    assertNull(getResult4.isBOOL());
    assertNull(getResult2.isBOOL());
    assertNull(getResult3.isBOOL());
    assertNull(getResult.isBOOL());
    assertNull(getResult5.isNULL());
    assertNull(getResult4.isNULL());
    assertNull(getResult2.isNULL());
    assertNull(getResult3.isNULL());
    assertNull(getResult.isNULL());
    assertNull(getResult4.getN());
    assertNull(getResult3.getN());
    assertNull(getResult5.getS());
    assertNull(getResult2.getS());
    assertNull(getResult.getS());
    assertNull(getResult5.getB());
    assertNull(getResult4.getB());
    assertNull(getResult2.getB());
    assertNull(getResult3.getB());
    assertNull(getResult.getB());
    assertNull(getResult5.getL());
    assertNull(getResult4.getL());
    assertNull(getResult2.getL());
    assertNull(getResult3.getL());
    assertNull(getResult.getL());
    assertNull(getResult5.getNS());
    assertNull(getResult4.getNS());
    assertNull(getResult2.getNS());
    assertNull(getResult3.getNS());
    assertNull(getResult.getNS());
    assertNull(getResult5.getSS());
    assertNull(getResult4.getSS());
    assertNull(getResult2.getSS());
    assertNull(getResult3.getSS());
    assertNull(getResult.getSS());
    assertNull(getResult5.getBS());
    assertNull(getResult4.getBS());
    assertNull(getResult2.getBS());
    assertNull(getResult3.getBS());
    assertNull(getResult.getBS());
    assertNull(getResult5.getM());
    assertNull(getResult4.getM());
    assertNull(getResult2.getM());
    assertNull(getResult3.getM());
    assertNull(getResult.getM());
  }

  /**
   * Test {@link DynamoDBIngestRequestFormat#createRecord(TablePropertiesProvider, FileIngestRequest)}.
   * <ul>
   *   <li>Then calls {@link TablePropertiesStore#loadById(String)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBIngestRequestFormat#createRecord(TablePropertiesProvider, FileIngestRequest)}
   */
  @Test
  @DisplayName("Test createRecord(TablePropertiesProvider, FileIngestRequest); then calls loadById(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Map DynamoDBIngestRequestFormat.createRecord(TablePropertiesProvider, FileIngestRequest)"})
  void testCreateRecord_thenCallsLoadById() {
    // Arrange
    TablePropertiesStore propertiesStore = mock(TablePropertiesStore.class);
    when(propertiesStore.loadById(Mockito.<String>any())).thenReturn(new TableProperties(new InstanceProperties()));
    TablePropertiesProvider tablePropertiesProvider = new TablePropertiesProvider(new InstanceProperties(),
        propertiesStore);

    Builder jobIdResult = FileIngestRequest.builder().file("File").fileSizeBytes(3L).jobId("42");
    FileIngestRequest fileIngestRequest = jobIdResult
        .receivedTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .tableId("42")
        .build();

    // Act
    Map<String, AttributeValue> actualCreateRecordResult = DynamoDBIngestRequestFormat
        .createRecord(tablePropertiesProvider, fileIngestRequest);

    // Assert
    verify(propertiesStore).loadById(eq("42"));
    assertEquals(5, actualCreateRecordResult.size());
    AttributeValue getResult = actualCreateRecordResult.get(DynamoDBIngestRequestFormat.RECEIVED_TIME);
    assertEquals("0", getResult.getN());
    AttributeValue getResult2 = actualCreateRecordResult.get(DynamoDBIngestRequestFormat.FILE_SIZE);
    assertEquals("3", getResult2.getN());
    AttributeValue getResult3 = actualCreateRecordResult.get(DynamoDBIngestRequestFormat.JOB_ID);
    assertEquals("42", getResult3.getS());
    AttributeValue getResult4 = actualCreateRecordResult.get(DynamoDBIngestRequestFormat.FILE_PATH);
    assertEquals("42/File", getResult4.getS());
    AttributeValue getResult5 = actualCreateRecordResult.get(DynamoDBIngestRequestFormat.EXPIRY_TIME);
    assertEquals("604800", getResult5.getN());
    assertNull(getResult5.getBOOL());
    assertNull(getResult4.getBOOL());
    assertNull(getResult2.getBOOL());
    assertNull(getResult3.getBOOL());
    assertNull(getResult.getBOOL());
    assertNull(getResult5.getNULL());
    assertNull(getResult4.getNULL());
    assertNull(getResult2.getNULL());
    assertNull(getResult3.getNULL());
    assertNull(getResult.getNULL());
    assertNull(getResult5.isBOOL());
    assertNull(getResult4.isBOOL());
    assertNull(getResult2.isBOOL());
    assertNull(getResult3.isBOOL());
    assertNull(getResult.isBOOL());
    assertNull(getResult5.isNULL());
    assertNull(getResult4.isNULL());
    assertNull(getResult2.isNULL());
    assertNull(getResult3.isNULL());
    assertNull(getResult.isNULL());
    assertNull(getResult4.getN());
    assertNull(getResult3.getN());
    assertNull(getResult5.getS());
    assertNull(getResult2.getS());
    assertNull(getResult.getS());
    assertNull(getResult5.getB());
    assertNull(getResult4.getB());
    assertNull(getResult2.getB());
    assertNull(getResult3.getB());
    assertNull(getResult.getB());
    assertNull(getResult5.getL());
    assertNull(getResult4.getL());
    assertNull(getResult2.getL());
    assertNull(getResult3.getL());
    assertNull(getResult.getL());
    assertNull(getResult5.getNS());
    assertNull(getResult4.getNS());
    assertNull(getResult2.getNS());
    assertNull(getResult3.getNS());
    assertNull(getResult.getNS());
    assertNull(getResult5.getSS());
    assertNull(getResult4.getSS());
    assertNull(getResult2.getSS());
    assertNull(getResult3.getSS());
    assertNull(getResult.getSS());
    assertNull(getResult5.getBS());
    assertNull(getResult4.getBS());
    assertNull(getResult2.getBS());
    assertNull(getResult3.getBS());
    assertNull(getResult.getBS());
    assertNull(getResult5.getM());
    assertNull(getResult4.getM());
    assertNull(getResult2.getM());
    assertNull(getResult3.getM());
    assertNull(getResult.getM());
  }

  /**
   * Test {@link DynamoDBIngestRequestFormat#createUnassignedKey(FileIngestRequest)}.
   * <p>
   * Method under test: {@link DynamoDBIngestRequestFormat#createUnassignedKey(FileIngestRequest)}
   */
  @Test
  @DisplayName("Test createUnassignedKey(FileIngestRequest)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Map DynamoDBIngestRequestFormat.createUnassignedKey(FileIngestRequest)"})
  void testCreateUnassignedKey() {
    // Arrange
    Builder jobIdResult = FileIngestRequest.builder().file("File").fileSizeBytes(3L).jobId("42");
    FileIngestRequest fileIngestRequest = jobIdResult
        .receivedTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .tableId("42")
        .build();

    // Act
    Map<String, AttributeValue> actualCreateUnassignedKeyResult = DynamoDBIngestRequestFormat
        .createUnassignedKey(fileIngestRequest);

    // Assert
    assertEquals(2, actualCreateUnassignedKeyResult.size());
    AttributeValue getResult = actualCreateUnassignedKeyResult.get(DynamoDBIngestRequestFormat.FILE_PATH);
    assertEquals("42/File", getResult.getS());
    assertNull(getResult.getBOOL());
    AttributeValue getResult2 = actualCreateUnassignedKeyResult.get(DynamoDBIngestRequestFormat.JOB_ID);
    assertNull(getResult2.getBOOL());
    assertNull(getResult.getNULL());
    assertNull(getResult2.getNULL());
    assertNull(getResult.isBOOL());
    assertNull(getResult2.isBOOL());
    assertNull(getResult.isNULL());
    assertNull(getResult2.isNULL());
    assertNull(getResult.getN());
    assertNull(getResult2.getN());
    assertNull(getResult.getB());
    assertNull(getResult2.getB());
    assertNull(getResult.getL());
    assertNull(getResult2.getL());
    assertNull(getResult.getNS());
    assertNull(getResult2.getNS());
    assertNull(getResult.getSS());
    assertNull(getResult2.getSS());
    assertNull(getResult.getBS());
    assertNull(getResult2.getBS());
    assertNull(getResult.getM());
    assertNull(getResult2.getM());
    assertEquals(DynamoDBIngestRequestFormat.NOT_ASSIGNED_TO_JOB, getResult2.getS());
  }

  /**
   * Test {@link DynamoDBIngestRequestFormat#createUnassignedKey(FileIngestRequest)}.
   * <ul>
   *   <li>Given {@code File}.</li>
   *   <li>Then calls {@link FileIngestRequest#getFile()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamoDBIngestRequestFormat#createUnassignedKey(FileIngestRequest)}
   */
  @Test
  @DisplayName("Test createUnassignedKey(FileIngestRequest); given 'File'; then calls getFile()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Map DynamoDBIngestRequestFormat.createUnassignedKey(FileIngestRequest)"})
  void testCreateUnassignedKey_givenFile_thenCallsGetFile() {
    // Arrange
    FileIngestRequest fileIngestRequest = mock(FileIngestRequest.class);
    when(fileIngestRequest.getFile()).thenReturn("File");
    when(fileIngestRequest.getTableId()).thenReturn("42");

    // Act
    Map<String, AttributeValue> actualCreateUnassignedKeyResult = DynamoDBIngestRequestFormat
        .createUnassignedKey(fileIngestRequest);

    // Assert
    verify(fileIngestRequest).getFile();
    verify(fileIngestRequest).getTableId();
    assertEquals(2, actualCreateUnassignedKeyResult.size());
    AttributeValue getResult = actualCreateUnassignedKeyResult.get(DynamoDBIngestRequestFormat.FILE_PATH);
    assertEquals("42/File", getResult.getS());
    assertNull(getResult.getBOOL());
    AttributeValue getResult2 = actualCreateUnassignedKeyResult.get(DynamoDBIngestRequestFormat.JOB_ID);
    assertNull(getResult2.getBOOL());
    assertNull(getResult.getNULL());
    assertNull(getResult2.getNULL());
    assertNull(getResult.isBOOL());
    assertNull(getResult2.isBOOL());
    assertNull(getResult.isNULL());
    assertNull(getResult2.isNULL());
    assertNull(getResult.getN());
    assertNull(getResult2.getN());
    assertNull(getResult.getB());
    assertNull(getResult2.getB());
    assertNull(getResult.getL());
    assertNull(getResult2.getL());
    assertNull(getResult.getNS());
    assertNull(getResult2.getNS());
    assertNull(getResult.getSS());
    assertNull(getResult2.getSS());
    assertNull(getResult.getBS());
    assertNull(getResult2.getBS());
    assertNull(getResult.getM());
    assertNull(getResult2.getM());
    assertEquals(DynamoDBIngestRequestFormat.NOT_ASSIGNED_TO_JOB, getResult2.getS());
  }
}
