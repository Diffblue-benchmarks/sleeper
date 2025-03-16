package sleeper.clients;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.Instant;
import java.util.function.Supplier;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.clients.util.console.ConsoleInput;
import sleeper.clients.util.console.ConsoleOutput;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.instance.InstanceProperty;
import sleeper.core.properties.table.TableProperties;
import sleeper.core.properties.table.TablePropertiesProvider;
import sleeper.core.properties.table.TablePropertiesStore;
import sleeper.core.properties.table.TablePropertiesStore.Client;
import sleeper.core.table.InMemoryTableIndex;
import sleeper.core.table.TableIndex;
import sleeper.query.core.model.Query;

class QueryWebSocketCommandLineClientDiffblueTest {
  /**
   * Test {@link QueryWebSocketCommandLineClient#QueryWebSocketCommandLineClient(InstanceProperties, TableIndex, TablePropertiesProvider, ConsoleInput, ConsoleOutput, QueryWebSocketClient, Supplier, Supplier)}.
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link QueryWebSocketCommandLineClient#QueryWebSocketCommandLineClient(InstanceProperties, TableIndex, TablePropertiesProvider, ConsoleInput, ConsoleOutput, QueryWebSocketClient, Supplier, Supplier)}
   */
  @Test
  @DisplayName("Test new QueryWebSocketCommandLineClient(InstanceProperties, TableIndex, TablePropertiesProvider, ConsoleInput, ConsoleOutput, QueryWebSocketClient, Supplier, Supplier); then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void QueryWebSocketCommandLineClient.<init>(InstanceProperties, TableIndex, TablePropertiesProvider, ConsoleInput, ConsoleOutput, QueryWebSocketClient, Supplier, Supplier)"})
  void testNewQueryWebSocketCommandLineClient_thenThrowIllegalArgumentException() {
    // Arrange
    InstanceProperties instanceProperties = new InstanceProperties();
    InMemoryTableIndex tableIndex = new InMemoryTableIndex();
    InstanceProperties instanceProperties2 = new InstanceProperties();
    TablePropertiesProvider tablePropertiesProvider = new TablePropertiesProvider(instanceProperties2,
        new TablePropertiesStore(new InMemoryTableIndex(), mock(Client.class)));

    ConsoleInput in = new ConsoleInput(null);

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> new QueryWebSocketCommandLineClient(instanceProperties, tableIndex, tablePropertiesProvider, in,
            new ConsoleOutput(new PrintStream(new ByteArrayOutputStream(1))), null, mock(Supplier.class),
            mock(Supplier.class)));

  }

  /**
   * Test {@link QueryWebSocketCommandLineClient#submitQuery(TableProperties, Query)}.
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link QueryWebSocketCommandLineClient#submitQuery(TableProperties, Query)}
   */
  @Test
  @DisplayName("Test submitQuery(TableProperties, Query); then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void QueryWebSocketCommandLineClient.submitQuery(TableProperties, Query)"})
  void testSubmitQuery_thenThrowIllegalArgumentException() throws InterruptedException {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    InstanceProperties instanceProperties2 = mock(InstanceProperties.class);
    when(instanceProperties2.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    InstanceProperties instanceProperties3 = new InstanceProperties();
    QueryWebSocketClient client = new QueryWebSocketClient(instanceProperties2, new TablePropertiesProvider(
        instanceProperties3, new TablePropertiesStore(new InMemoryTableIndex(), mock(Client.class))));

    Supplier<Instant> timeSupplier = mock(Supplier.class);
    when(timeSupplier.get()).thenThrow(new IllegalArgumentException("foo"));
    InMemoryTableIndex tableIndex = new InMemoryTableIndex();
    InstanceProperties instanceProperties4 = new InstanceProperties();
    TablePropertiesProvider tablePropertiesProvider = new TablePropertiesProvider(instanceProperties4,
        new TablePropertiesStore(new InMemoryTableIndex(), mock(Client.class)));

    ConsoleInput in = new ConsoleInput(null);
    QueryWebSocketCommandLineClient queryWebSocketCommandLineClient = new QueryWebSocketCommandLineClient(
        instanceProperties, tableIndex, tablePropertiesProvider, in,
        new ConsoleOutput(new PrintStream(new ByteArrayOutputStream(1))), client, mock(Supplier.class), timeSupplier);

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> queryWebSocketCommandLineClient.submitQuery(new TableProperties(new InstanceProperties()), null));
    verify(timeSupplier).get();
    verify(instanceProperties).get(isA(InstanceProperty.class));
    verify(instanceProperties2).get(isA(InstanceProperty.class));
  }
}
