package sleeper.core.properties.table;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.properties.SleeperProperties;
import sleeper.core.properties.SleeperPropertiesInvalidException;
import sleeper.core.properties.table.TablePropertiesStore.Client;
import sleeper.core.table.InMemoryTableIndex;
import sleeper.core.table.TableAlreadyExistsException;
import sleeper.core.table.TableIndex;
import sleeper.core.table.TableStatus;

class TablePropertiesStoreDiffblueTest {
  /**
   * Test {@link TablePropertiesStore#loadProperties(TableStatus)}.
   * <ul>
   *   <li>Then throw {@link TableAlreadyExistsException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TablePropertiesStore#loadProperties(TableStatus)}
   */
  @Test
  @DisplayName("Test loadProperties(TableStatus); then throw TableAlreadyExistsException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"TableProperties TablePropertiesStore.loadProperties(TableStatus)"})
  void testLoadProperties_thenThrowTableAlreadyExistsException() {
    // Arrange
    Client client = mock(Client.class);
    when(client.loadProperties(Mockito.<TableStatus>any()))
        .thenThrow(new TableAlreadyExistsException(TableStatus.uniqueIdAndName("42", "Table Name", true)));
    TablePropertiesStore tablePropertiesStore = new TablePropertiesStore(new InMemoryTableIndex(), client);

    // Act and Assert
    assertThrows(TableAlreadyExistsException.class,
        () -> tablePropertiesStore.loadProperties(TableStatus.uniqueIdAndName("42", "Table Name", true)));
    verify(client).loadProperties(isA(TableStatus.class));
  }

  /**
   * Test {@link TablePropertiesStore#loadByName(String)}.
   * <ul>
   *   <li>Given {@link TableProperties} {@link SleeperProperties#validate()} does nothing.</li>
   *   <li>Then calls {@link SleeperProperties#validate()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TablePropertiesStore#loadByName(String)}
   */
  @Test
  @DisplayName("Test loadByName(String); given TableProperties validate() does nothing; then calls validate()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"TableProperties TablePropertiesStore.loadByName(String)"})
  void testLoadByName_givenTablePropertiesValidateDoesNothing_thenCallsValidate()
      throws SleeperPropertiesInvalidException {
    // Arrange
    TableIndex tableIndex = mock(TableIndex.class);
    Optional<TableStatus> ofResult = Optional.of(TableStatus.uniqueIdAndName("42", "Table Name", true));
    when(tableIndex.getTableByName(Mockito.<String>any())).thenReturn(ofResult);
    TableProperties tableProperties = mock(TableProperties.class);
    doNothing().when(tableProperties).validate();
    Client client = mock(Client.class);
    when(client.loadProperties(Mockito.<TableStatus>any())).thenReturn(tableProperties);

    // Act
    (new TablePropertiesStore(tableIndex, client)).loadByName("Table Name");

    // Assert
    verify(tableProperties).validate();
    verify(client).loadProperties(isA(TableStatus.class));
    verify(tableIndex).getTableByName(eq("Table Name"));
  }

  /**
   * Test {@link TablePropertiesStore#loadByName(String)}.
   * <ul>
   *   <li>Then throw {@link TableAlreadyExistsException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TablePropertiesStore#loadByName(String)}
   */
  @Test
  @DisplayName("Test loadByName(String); then throw TableAlreadyExistsException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"TableProperties TablePropertiesStore.loadByName(String)"})
  void testLoadByName_thenThrowTableAlreadyExistsException() {
    // Arrange
    TableIndex tableIndex = mock(TableIndex.class);
    Optional<TableStatus> ofResult = Optional.of(TableStatus.uniqueIdAndName("42", "Table Name", true));
    when(tableIndex.getTableByName(Mockito.<String>any())).thenReturn(ofResult);
    Client client = mock(Client.class);
    when(client.loadProperties(Mockito.<TableStatus>any()))
        .thenThrow(new TableAlreadyExistsException(TableStatus.uniqueIdAndName("42", "Table Name", true)));

    // Act and Assert
    assertThrows(TableAlreadyExistsException.class,
        () -> (new TablePropertiesStore(tableIndex, client)).loadByName("Table Name"));
    verify(client).loadProperties(isA(TableStatus.class));
    verify(tableIndex).getTableByName(eq("Table Name"));
  }

  /**
   * Test {@link TablePropertiesStore#loadById(String)}.
   * <ul>
   *   <li>Given {@link TableProperties} {@link SleeperProperties#validate()} does nothing.</li>
   *   <li>Then calls {@link SleeperProperties#validate()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TablePropertiesStore#loadById(String)}
   */
  @Test
  @DisplayName("Test loadById(String); given TableProperties validate() does nothing; then calls validate()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"TableProperties TablePropertiesStore.loadById(String)"})
  void testLoadById_givenTablePropertiesValidateDoesNothing_thenCallsValidate()
      throws SleeperPropertiesInvalidException {
    // Arrange
    TableIndex tableIndex = mock(TableIndex.class);
    Optional<TableStatus> ofResult = Optional.of(TableStatus.uniqueIdAndName("42", "Table Name", true));
    when(tableIndex.getTableByUniqueId(Mockito.<String>any())).thenReturn(ofResult);
    TableProperties tableProperties = mock(TableProperties.class);
    doNothing().when(tableProperties).validate();
    Client client = mock(Client.class);
    when(client.loadProperties(Mockito.<TableStatus>any())).thenReturn(tableProperties);

    // Act
    (new TablePropertiesStore(tableIndex, client)).loadById("42");

    // Assert
    verify(tableProperties).validate();
    verify(client).loadProperties(isA(TableStatus.class));
    verify(tableIndex).getTableByUniqueId(eq("42"));
  }

  /**
   * Test {@link TablePropertiesStore#loadById(String)}.
   * <ul>
   *   <li>Then throw {@link TableAlreadyExistsException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TablePropertiesStore#loadById(String)}
   */
  @Test
  @DisplayName("Test loadById(String); then throw TableAlreadyExistsException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"TableProperties TablePropertiesStore.loadById(String)"})
  void testLoadById_thenThrowTableAlreadyExistsException() {
    // Arrange
    TableIndex tableIndex = mock(TableIndex.class);
    Optional<TableStatus> ofResult = Optional.of(TableStatus.uniqueIdAndName("42", "Table Name", true));
    when(tableIndex.getTableByUniqueId(Mockito.<String>any())).thenReturn(ofResult);
    Client client = mock(Client.class);
    when(client.loadProperties(Mockito.<TableStatus>any()))
        .thenThrow(new TableAlreadyExistsException(TableStatus.uniqueIdAndName("42", "Table Name", true)));

    // Act and Assert
    assertThrows(TableAlreadyExistsException.class,
        () -> (new TablePropertiesStore(tableIndex, client)).loadById("42"));
    verify(client).loadProperties(isA(TableStatus.class));
    verify(tableIndex).getTableByUniqueId(eq("42"));
  }

  /**
   * Test {@link TablePropertiesStore#streamAllTables()}.
   * <ul>
   *   <li>Then return limit five collect toList Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link TablePropertiesStore#streamAllTables()}
   */
  @Test
  @DisplayName("Test streamAllTables(); then return limit five collect toList Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream TablePropertiesStore.streamAllTables()"})
  void testStreamAllTables_thenReturnLimitFiveCollectToListEmpty() {
    // Arrange and Act
    Stream<TableProperties> actualStreamAllTablesResult = (new TablePropertiesStore(new InMemoryTableIndex(),
        mock(Client.class))).streamAllTables();

    // Assert
    assertTrue(actualStreamAllTablesResult.limit(5).collect(Collectors.toList()).isEmpty());
  }

  /**
   * Test {@link TablePropertiesStore#streamAllTableStatuses()}.
   * <ul>
   *   <li>Then return limit five collect toList Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link TablePropertiesStore#streamAllTableStatuses()}
   */
  @Test
  @DisplayName("Test streamAllTableStatuses(); then return limit five collect toList Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream TablePropertiesStore.streamAllTableStatuses()"})
  void testStreamAllTableStatuses_thenReturnLimitFiveCollectToListEmpty() {
    // Arrange and Act
    Stream<TableStatus> actualStreamAllTableStatusesResult = (new TablePropertiesStore(new InMemoryTableIndex(),
        mock(Client.class))).streamAllTableStatuses();

    // Assert
    assertTrue(actualStreamAllTableStatusesResult.limit(5).collect(Collectors.toList()).isEmpty());
  }

  /**
   * Test {@link TablePropertiesStore#streamOnlineTableIds()}.
   * <ul>
   *   <li>Then return limit five collect toList Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link TablePropertiesStore#streamOnlineTableIds()}
   */
  @Test
  @DisplayName("Test streamOnlineTableIds(); then return limit five collect toList Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream TablePropertiesStore.streamOnlineTableIds()"})
  void testStreamOnlineTableIds_thenReturnLimitFiveCollectToListEmpty() {
    // Arrange and Act
    Stream<TableStatus> actualStreamOnlineTableIdsResult = (new TablePropertiesStore(new InMemoryTableIndex(),
        mock(Client.class))).streamOnlineTableIds();

    // Assert
    assertTrue(actualStreamOnlineTableIdsResult.limit(5).collect(Collectors.toList()).isEmpty());
  }

  /**
   * Test {@link TablePropertiesStore#deleteByName(String)}.
   * <ul>
   *   <li>Given {@link Client} {@link Client#deleteProperties(TableStatus)} does nothing.</li>
   * </ul>
   * <p>
   * Method under test: {@link TablePropertiesStore#deleteByName(String)}
   */
  @Test
  @DisplayName("Test deleteByName(String); given Client deleteProperties(TableStatus) does nothing")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void TablePropertiesStore.deleteByName(String)"})
  void testDeleteByName_givenClientDeletePropertiesDoesNothing() {
    // Arrange
    TableIndex tableIndex = mock(TableIndex.class);
    doNothing().when(tableIndex).delete(Mockito.<TableStatus>any());
    Optional<TableStatus> ofResult = Optional.of(TableStatus.uniqueIdAndName("42", "Table Name", true));
    when(tableIndex.getTableByName(Mockito.<String>any())).thenReturn(ofResult);
    Client client = mock(Client.class);
    doNothing().when(client).deleteProperties(Mockito.<TableStatus>any());

    // Act
    (new TablePropertiesStore(tableIndex, client)).deleteByName("Table Name");

    // Assert
    verify(client).deleteProperties(isA(TableStatus.class));
    verify(tableIndex).delete(isA(TableStatus.class));
    verify(tableIndex).getTableByName(eq("Table Name"));
  }

  /**
   * Test {@link TablePropertiesStore#deleteByName(String)}.
   * <ul>
   *   <li>Then throw {@link TableAlreadyExistsException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TablePropertiesStore#deleteByName(String)}
   */
  @Test
  @DisplayName("Test deleteByName(String); then throw TableAlreadyExistsException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void TablePropertiesStore.deleteByName(String)"})
  void testDeleteByName_thenThrowTableAlreadyExistsException() {
    // Arrange
    TableIndex tableIndex = mock(TableIndex.class);
    doNothing().when(tableIndex).delete(Mockito.<TableStatus>any());
    Optional<TableStatus> ofResult = Optional.of(TableStatus.uniqueIdAndName("42", "Table Name", true));
    when(tableIndex.getTableByName(Mockito.<String>any())).thenReturn(ofResult);
    Client client = mock(Client.class);
    doThrow(new TableAlreadyExistsException(TableStatus.uniqueIdAndName("42", "Table Name", true))).when(client)
        .deleteProperties(Mockito.<TableStatus>any());

    // Act and Assert
    assertThrows(TableAlreadyExistsException.class,
        () -> (new TablePropertiesStore(tableIndex, client)).deleteByName("Table Name"));
    verify(client).deleteProperties(isA(TableStatus.class));
    verify(tableIndex).delete(isA(TableStatus.class));
    verify(tableIndex).getTableByName(eq("Table Name"));
  }

  /**
   * Test {@link TablePropertiesStore#delete(TableStatus)}.
   * <ul>
   *   <li>Given {@link Client} {@link Client#deleteProperties(TableStatus)} does nothing.</li>
   *   <li>Then calls {@link Client#deleteProperties(TableStatus)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TablePropertiesStore#delete(TableStatus)}
   */
  @Test
  @DisplayName("Test delete(TableStatus); given Client deleteProperties(TableStatus) does nothing; then calls deleteProperties(TableStatus)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void TablePropertiesStore.delete(TableStatus)"})
  void testDelete_givenClientDeletePropertiesDoesNothing_thenCallsDeleteProperties() {
    // Arrange
    InMemoryTableIndex tableIndex = mock(InMemoryTableIndex.class);
    doNothing().when(tableIndex).delete(Mockito.<TableStatus>any());
    Client client = mock(Client.class);
    doNothing().when(client).deleteProperties(Mockito.<TableStatus>any());
    TablePropertiesStore tablePropertiesStore = new TablePropertiesStore(tableIndex, client);

    // Act
    tablePropertiesStore.delete(TableStatus.uniqueIdAndName("42", "Table Name", true));

    // Assert
    verify(client).deleteProperties(isA(TableStatus.class));
    verify(tableIndex).delete(isA(TableStatus.class));
  }

  /**
   * Test {@link TablePropertiesStore#delete(TableStatus)}.
   * <ul>
   *   <li>Then throw {@link TableAlreadyExistsException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TablePropertiesStore#delete(TableStatus)}
   */
  @Test
  @DisplayName("Test delete(TableStatus); then throw TableAlreadyExistsException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void TablePropertiesStore.delete(TableStatus)"})
  void testDelete_thenThrowTableAlreadyExistsException() {
    // Arrange
    InMemoryTableIndex tableIndex = mock(InMemoryTableIndex.class);
    doNothing().when(tableIndex).delete(Mockito.<TableStatus>any());
    Client client = mock(Client.class);
    doThrow(new TableAlreadyExistsException(TableStatus.uniqueIdAndName("42", "Table Name", true))).when(client)
        .deleteProperties(Mockito.<TableStatus>any());
    TablePropertiesStore tablePropertiesStore = new TablePropertiesStore(tableIndex, client);

    // Act and Assert
    assertThrows(TableAlreadyExistsException.class,
        () -> tablePropertiesStore.delete(TableStatus.uniqueIdAndName("42", "Table Name", true)));
    verify(client).deleteProperties(isA(TableStatus.class));
    verify(tableIndex).delete(isA(TableStatus.class));
  }
}
