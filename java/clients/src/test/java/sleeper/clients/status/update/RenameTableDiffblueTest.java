package sleeper.clients.status.update;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClient;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.properties.SleeperProperties;
import sleeper.core.properties.SleeperPropertiesInvalidException;
import sleeper.core.properties.SleeperPropertyValues;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.instance.InstanceProperty;
import sleeper.core.properties.table.TableProperties;
import sleeper.core.properties.table.TablePropertiesStore;
import sleeper.core.properties.table.TablePropertiesStore.Client;
import sleeper.core.properties.table.TableProperty;
import sleeper.core.table.InMemoryTableIndex;
import sleeper.core.table.TableAlreadyExistsException;
import sleeper.core.table.TableStatus;

class RenameTableDiffblueTest {
  /**
   * Test {@link RenameTable#RenameTable(AmazonS3, AmazonDynamoDB, InstanceProperties)}.
   * <ul>
   *   <li>Given {@code true}.</li>
   *   <li>Then calls {@link SleeperPropertyValues#getBoolean(SleeperProperty)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RenameTable#RenameTable(AmazonS3, AmazonDynamoDB, InstanceProperties)}
   */
  @Test
  @DisplayName("Test new RenameTable(AmazonS3, AmazonDynamoDB, InstanceProperties); given 'true'; then calls getBoolean(SleeperProperty)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void RenameTable.<init>(AmazonS3, AmazonDynamoDB, InstanceProperties)"})
  void testNewRenameTable_givenTrue_thenCallsGetBoolean() {
    // Arrange
    AmazonS3Client s3Client = new AmazonS3Client();
    AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.getBoolean(Mockito.<InstanceProperty>any())).thenReturn(true);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");

    // Act
    new RenameTable(s3Client, dynamoDB, instanceProperties);

    // Assert
    verify(instanceProperties).getBoolean(isA(InstanceProperty.class));
    verify(instanceProperties, atLeast(1)).get(Mockito.<InstanceProperty>any());
  }

  /**
   * Test {@link RenameTable#rename(String, String)}.
   * <p>
   * Method under test: {@link RenameTable#rename(String, String)}
   */
  @Test
  @DisplayName("Test rename(String, String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void RenameTable.rename(String, String)"})
  void testRename() throws SleeperPropertiesInvalidException {
    // Arrange
    InMemoryTableIndex tableIndex = mock(InMemoryTableIndex.class);
    Optional<TableStatus> ofResult = Optional.of(TableStatus.uniqueIdAndName("42", "Table Name", true));
    when(tableIndex.getTableByUniqueId(Mockito.<String>any())).thenReturn(ofResult);
    doNothing().when(tableIndex).update(Mockito.<TableStatus>any());
    Optional<TableStatus> ofResult2 = Optional.of(TableStatus.uniqueIdAndName("42", "Table Name", true));
    when(tableIndex.getTableByName(Mockito.<String>any())).thenReturn(ofResult2);
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.isSet(Mockito.<TableProperty>any())).thenReturn(true);
    when(tableProperties.getBoolean(Mockito.<TableProperty>any())).thenReturn(true);
    doNothing().when(tableProperties).validate();
    when(tableProperties.get(Mockito.<TableProperty>any())).thenReturn("Get");
    doNothing().when(tableProperties).set(Mockito.<TableProperty>any(), Mockito.<String>any());
    Client client = mock(Client.class);
    doNothing().when(client).saveProperties(Mockito.<TableProperties>any());
    when(client.loadProperties(Mockito.<TableStatus>any())).thenReturn(tableProperties);

    // Act
    (new RenameTable(new TablePropertiesStore(tableIndex, client))).rename("Old Name", "New Name");

    // Assert
    verify(tableProperties).isSet(isA(TableProperty.class));
    verify(tableProperties, atLeast(1)).set(Mockito.<TableProperty>any(), Mockito.<String>any());
    verify(tableProperties).validate();
    verify(tableProperties).getBoolean(isA(TableProperty.class));
    verify(tableProperties, atLeast(1)).get(Mockito.<TableProperty>any());
    verify(client).loadProperties(isA(TableStatus.class));
    verify(client).saveProperties(isA(TableProperties.class));
    verify(tableIndex).getTableByName(eq("Old Name"));
    verify(tableIndex).getTableByUniqueId(eq("Get"));
    verify(tableIndex).update(isA(TableStatus.class));
  }

  /**
   * Test {@link RenameTable#rename(String, String)}.
   * <ul>
   *   <li>Given {@link TableProperties} {@link SleeperProperties#isSet(SleeperProperty)} return {@code false}.</li>
   *   <li>Then calls {@link SleeperPropertyValues#getBoolean(SleeperProperty)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RenameTable#rename(String, String)}
   */
  @Test
  @DisplayName("Test rename(String, String); given TableProperties isSet(SleeperProperty) return 'false'; then calls getBoolean(SleeperProperty)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void RenameTable.rename(String, String)"})
  void testRename_givenTablePropertiesIsSetReturnFalse_thenCallsGetBoolean() throws SleeperPropertiesInvalidException {
    // Arrange
    InMemoryTableIndex tableIndex = mock(InMemoryTableIndex.class);
    doNothing().when(tableIndex).update(Mockito.<TableStatus>any());
    Optional<TableStatus> ofResult = Optional.of(TableStatus.uniqueIdAndName("42", "Table Name", true));
    when(tableIndex.getTableByName(Mockito.<String>any())).thenReturn(ofResult);
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.isSet(Mockito.<TableProperty>any())).thenReturn(false);
    when(tableProperties.getBoolean(Mockito.<TableProperty>any())).thenReturn(true);
    doNothing().when(tableProperties).validate();
    when(tableProperties.get(Mockito.<TableProperty>any())).thenReturn("Get");
    doNothing().when(tableProperties).set(Mockito.<TableProperty>any(), Mockito.<String>any());
    Client client = mock(Client.class);
    doNothing().when(client).saveProperties(Mockito.<TableProperties>any());
    when(client.loadProperties(Mockito.<TableStatus>any())).thenReturn(tableProperties);

    // Act
    (new RenameTable(new TablePropertiesStore(tableIndex, client))).rename("Old Name", "New Name");

    // Assert
    verify(tableProperties).isSet(isA(TableProperty.class));
    verify(tableProperties, atLeast(1)).set(Mockito.<TableProperty>any(), Mockito.<String>any());
    verify(tableProperties).validate();
    verify(tableProperties).getBoolean(isA(TableProperty.class));
    verify(tableProperties, atLeast(1)).get(isA(TableProperty.class));
    verify(client).loadProperties(isA(TableStatus.class));
    verify(client).saveProperties(isA(TableProperties.class));
    verify(tableIndex, atLeast(1)).getTableByName(Mockito.<String>any());
    verify(tableIndex).update(isA(TableStatus.class));
  }

  /**
   * Test {@link RenameTable#rename(String, String)}.
   * <ul>
   *   <li>Given {@link TableStatus} {@link TableStatus#getTableName()} return {@code Table Name}.</li>
   *   <li>Then calls {@link TableStatus#getTableName()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RenameTable#rename(String, String)}
   */
  @Test
  @DisplayName("Test rename(String, String); given TableStatus getTableName() return 'Table Name'; then calls getTableName()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void RenameTable.rename(String, String)"})
  void testRename_givenTableStatusGetTableNameReturnTableName_thenCallsGetTableName()
      throws SleeperPropertiesInvalidException {
    // Arrange
    TableStatus tableStatus = mock(TableStatus.class);
    when(tableStatus.getTableName()).thenReturn("Table Name");
    when(tableStatus.getTableUniqueId()).thenReturn("42");
    Optional<TableStatus> ofResult = Optional.of(tableStatus);
    InMemoryTableIndex tableIndex = mock(InMemoryTableIndex.class);
    when(tableIndex.getTableByUniqueId(Mockito.<String>any())).thenReturn(ofResult);
    doNothing().when(tableIndex).update(Mockito.<TableStatus>any());
    Optional<TableStatus> ofResult2 = Optional.of(TableStatus.uniqueIdAndName("42", "Table Name", true));
    when(tableIndex.getTableByName(Mockito.<String>any())).thenReturn(ofResult2);
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.isSet(Mockito.<TableProperty>any())).thenReturn(true);
    when(tableProperties.getBoolean(Mockito.<TableProperty>any())).thenReturn(true);
    doNothing().when(tableProperties).validate();
    when(tableProperties.get(Mockito.<TableProperty>any())).thenReturn("Get");
    doNothing().when(tableProperties).set(Mockito.<TableProperty>any(), Mockito.<String>any());
    Client client = mock(Client.class);
    doNothing().when(client).saveProperties(Mockito.<TableProperties>any());
    when(client.loadProperties(Mockito.<TableStatus>any())).thenReturn(tableProperties);

    // Act
    (new RenameTable(new TablePropertiesStore(tableIndex, client))).rename("Old Name", "New Name");

    // Assert
    verify(tableProperties).isSet(isA(TableProperty.class));
    verify(tableProperties, atLeast(1)).set(Mockito.<TableProperty>any(), Mockito.<String>any());
    verify(tableProperties).validate();
    verify(tableProperties).getBoolean(isA(TableProperty.class));
    verify(tableProperties, atLeast(1)).get(Mockito.<TableProperty>any());
    verify(client).loadProperties(isA(TableStatus.class));
    verify(client).saveProperties(isA(TableProperties.class));
    verify(tableIndex).getTableByName(eq("Old Name"));
    verify(tableIndex).getTableByUniqueId(eq("Get"));
    verify(tableIndex).update(isA(TableStatus.class));
    verify(tableStatus).getTableName();
    verify(tableStatus, atLeast(1)).getTableUniqueId();
  }

  /**
   * Test {@link RenameTable#rename(String, String)}.
   * <ul>
   *   <li>Then calls {@link TableProperties#getStatus()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RenameTable#rename(String, String)}
   */
  @Test
  @DisplayName("Test rename(String, String); then calls getStatus()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void RenameTable.rename(String, String)"})
  void testRename_thenCallsGetStatus() throws SleeperPropertiesInvalidException, TableAlreadyExistsException {
    // Arrange
    InMemoryTableIndex tableIndex = mock(InMemoryTableIndex.class);
    Optional<TableStatus> emptyResult = Optional.empty();
    when(tableIndex.getTableByUniqueId(Mockito.<String>any())).thenReturn(emptyResult);
    doNothing().when(tableIndex).create(Mockito.<TableStatus>any());
    Optional<TableStatus> ofResult = Optional.of(TableStatus.uniqueIdAndName("42", "Table Name", true));
    when(tableIndex.getTableByName(Mockito.<String>any())).thenReturn(ofResult);
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.getStatus()).thenReturn(TableStatus.uniqueIdAndName("42", "Table Name", true));
    when(tableProperties.isSet(Mockito.<TableProperty>any())).thenReturn(true);
    doNothing().when(tableProperties).validate();
    when(tableProperties.get(Mockito.<TableProperty>any())).thenReturn("Get");
    doNothing().when(tableProperties).set(Mockito.<TableProperty>any(), Mockito.<String>any());
    Client client = mock(Client.class);
    doNothing().when(client).saveProperties(Mockito.<TableProperties>any());
    when(client.loadProperties(Mockito.<TableStatus>any())).thenReturn(tableProperties);

    // Act
    (new RenameTable(new TablePropertiesStore(tableIndex, client))).rename("Old Name", "New Name");

    // Assert
    verify(tableProperties, atLeast(1)).isSet(isA(TableProperty.class));
    verify(tableProperties).set(isA(TableProperty.class), eq("New Name"));
    verify(tableProperties).validate();
    verify(tableProperties).get(isA(TableProperty.class));
    verify(tableProperties).getStatus();
    verify(client).loadProperties(isA(TableStatus.class));
    verify(client).saveProperties(isA(TableProperties.class));
    verify(tableIndex).create(isA(TableStatus.class));
    verify(tableIndex).getTableByName(eq("Old Name"));
    verify(tableIndex).getTableByUniqueId(eq("Get"));
  }

  /**
   * Test {@link RenameTable#rename(String, String)}.
   * <ul>
   *   <li>Then calls {@link TablePropertiesStore#loadByName(String)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RenameTable#rename(String, String)}
   */
  @Test
  @DisplayName("Test rename(String, String); then calls loadByName(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void RenameTable.rename(String, String)"})
  void testRename_thenCallsLoadByName() {
    // Arrange
    TablePropertiesStore tablePropertiesStore = mock(TablePropertiesStore.class);
    doNothing().when(tablePropertiesStore).save(Mockito.<TableProperties>any());
    when(tablePropertiesStore.loadByName(Mockito.<String>any()))
        .thenReturn(new TableProperties(new InstanceProperties()));

    // Act
    (new RenameTable(tablePropertiesStore)).rename("Old Name", "New Name");

    // Assert
    verify(tablePropertiesStore).loadByName(eq("Old Name"));
    verify(tablePropertiesStore).save(isA(TableProperties.class));
  }
}
