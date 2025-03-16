package sleeper.clients;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.clients.QueryWebSocketClient.ClientCloser;
import sleeper.clients.QueryWebSocketClient.WebSocketMessageHandler;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.table.TablePropertiesProvider;
import sleeper.core.properties.table.TablePropertiesStore;
import sleeper.core.properties.table.TablePropertiesStore.Client;
import sleeper.core.table.InMemoryTableIndex;
import sleeper.query.core.model.QuerySerDe;

class QueryWebSocketClientDiffblueTest {
  /**
   * Test {@link QueryWebSocketClient#QueryWebSocketClient(InstanceProperties, TablePropertiesProvider)}.
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link QueryWebSocketClient#QueryWebSocketClient(InstanceProperties, TablePropertiesProvider)}
   */
  @Test
  @DisplayName("Test new QueryWebSocketClient(InstanceProperties, TablePropertiesProvider); then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void QueryWebSocketClient.<init>(InstanceProperties, TablePropertiesProvider)"})
  void testNewQueryWebSocketClient_thenThrowIllegalArgumentException() {
    // Arrange
    InstanceProperties instanceProperties = new InstanceProperties();
    InstanceProperties instanceProperties2 = new InstanceProperties();

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> new QueryWebSocketClient(instanceProperties, new TablePropertiesProvider(instanceProperties2,
            new TablePropertiesStore(new InMemoryTableIndex(), mock(Client.class)))));

  }

  /**
   * Test {@link QueryWebSocketClient#QueryWebSocketClient(InstanceProperties, TablePropertiesProvider, Supplier)}.
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link QueryWebSocketClient#QueryWebSocketClient(InstanceProperties, TablePropertiesProvider, Supplier)}
   */
  @Test
  @DisplayName("Test new QueryWebSocketClient(InstanceProperties, TablePropertiesProvider, Supplier); then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void QueryWebSocketClient.<init>(InstanceProperties, TablePropertiesProvider, Supplier)"})
  void testNewQueryWebSocketClient_thenThrowIllegalArgumentException2() {
    // Arrange
    InstanceProperties instanceProperties = new InstanceProperties();
    InstanceProperties instanceProperties2 = new InstanceProperties();

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> new QueryWebSocketClient(instanceProperties, new TablePropertiesProvider(instanceProperties2,
            new TablePropertiesStore(new InMemoryTableIndex(), mock(Client.class))), mock(Supplier.class)));

  }

  /**
   * Test WebSocketMessageHandler {@link WebSocketMessageHandler#getResults(String)}.
   * <p>
   * Method under test: {@link WebSocketMessageHandler#getResults(String)}
   */
  @Test
  @DisplayName("Test WebSocketMessageHandler getResults(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.util.List WebSocketMessageHandler.getResults(String)"})
  void testWebSocketMessageHandlerGetResults() {
    // Arrange
    InstanceProperties instanceProperties = new InstanceProperties();

    // Act and Assert
    assertTrue((new WebSocketMessageHandler(new QuerySerDe(new TablePropertiesProvider(instanceProperties,
        new TablePropertiesStore(new InMemoryTableIndex(), mock(Client.class)))))).getResults("42").isEmpty());
  }

  /**
   * Test WebSocketMessageHandler getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link WebSocketMessageHandler#setCloser(ClientCloser)}
   *   <li>{@link WebSocketMessageHandler#setFuture(CompletableFuture)}
   *   <li>{@link WebSocketMessageHandler#getTotalRecordsReturned()}
   * </ul>
   */
  @Test
  @DisplayName("Test WebSocketMessageHandler getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"long WebSocketMessageHandler.getTotalRecordsReturned()",
      "void WebSocketMessageHandler.setCloser(ClientCloser)",
      "void WebSocketMessageHandler.setFuture(CompletableFuture)"})
  void testWebSocketMessageHandlerGettersAndSetters() {
    // Arrange
    InstanceProperties instanceProperties = new InstanceProperties();
    WebSocketMessageHandler webSocketMessageHandler = new WebSocketMessageHandler(
        new QuerySerDe(new TablePropertiesProvider(instanceProperties,
            new TablePropertiesStore(new InMemoryTableIndex(), mock(Client.class)))));

    // Act
    webSocketMessageHandler.setCloser(mock(ClientCloser.class));
    webSocketMessageHandler.setFuture(new CompletableFuture<>());

    // Assert
    assertEquals(0L, webSocketMessageHandler.getTotalRecordsReturned());
  }

  /**
   * Test WebSocketMessageHandler {@link WebSocketMessageHandler#hasQueryFinished()}.
   * <p>
   * Method under test: {@link WebSocketMessageHandler#hasQueryFinished()}
   */
  @Test
  @DisplayName("Test WebSocketMessageHandler hasQueryFinished()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean WebSocketMessageHandler.hasQueryFinished()"})
  void testWebSocketMessageHandlerHasQueryFinished() {
    // Arrange
    InstanceProperties instanceProperties = new InstanceProperties();

    // Act and Assert
    assertFalse((new WebSocketMessageHandler(new QuerySerDe(new TablePropertiesProvider(instanceProperties,
        new TablePropertiesStore(new InMemoryTableIndex(), mock(Client.class)))))).hasQueryFinished());
  }

  /**
   * Test WebSocketMessageHandler {@link WebSocketMessageHandler#WebSocketMessageHandler(QuerySerDe)}.
   * <p>
   * Method under test: {@link WebSocketMessageHandler#WebSocketMessageHandler(QuerySerDe)}
   */
  @Test
  @DisplayName("Test WebSocketMessageHandler new WebSocketMessageHandler(QuerySerDe)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void WebSocketMessageHandler.<init>(QuerySerDe)"})
  void testWebSocketMessageHandlerNewWebSocketMessageHandler() {
    // Arrange
    InstanceProperties instanceProperties = new InstanceProperties();

    // Act
    WebSocketMessageHandler actualWebSocketMessageHandler = new WebSocketMessageHandler(
        new QuerySerDe(new TablePropertiesProvider(instanceProperties,
            new TablePropertiesStore(new InMemoryTableIndex(), mock(Client.class)))));

    // Assert
    assertEquals(0L, actualWebSocketMessageHandler.getTotalRecordsReturned());
    assertFalse(actualWebSocketMessageHandler.hasQueryFinished());
  }

  /**
   * Test WebSocketMessageHandler {@link WebSocketMessageHandler#onClose(String)}.
   * <p>
   * Method under test: {@link WebSocketMessageHandler#onClose(String)}
   */
  @Test
  @DisplayName("Test WebSocketMessageHandler onClose(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void WebSocketMessageHandler.onClose(String)"})
  void testWebSocketMessageHandlerOnClose() {
    // Arrange
    InstanceProperties instanceProperties = new InstanceProperties();

    WebSocketMessageHandler webSocketMessageHandler = new WebSocketMessageHandler(
        new QuerySerDe(new TablePropertiesProvider(instanceProperties,
            new TablePropertiesStore(new InMemoryTableIndex(), mock(Client.class)))));
    webSocketMessageHandler.setFuture(new CompletableFuture<>());

    // Act
    webSocketMessageHandler.onClose("Just cause");

    // Assert
    assertTrue(webSocketMessageHandler.hasQueryFinished());
  }
}
