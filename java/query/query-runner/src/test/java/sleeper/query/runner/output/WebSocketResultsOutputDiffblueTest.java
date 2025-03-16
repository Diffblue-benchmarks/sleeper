package sleeper.query.runner.output;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.schema.Schema;

class WebSocketResultsOutputDiffblueTest {
  /**
   * Test {@link WebSocketResultsOutput#WebSocketResultsOutput(Schema, Map)}.
   * <ul>
   *   <li>Given {@code 42}.</li>
   *   <li>When {@link HashMap#HashMap()} {@link WebSocketResultsOutput#MAX_BATCH_SIZE} is {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WebSocketResultsOutput#WebSocketResultsOutput(Schema, Map)}
   */
  @Test
  @DisplayName("Test new WebSocketResultsOutput(Schema, Map); given '42'; when HashMap() MAX_BATCH_SIZE is '42'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void WebSocketResultsOutput.<init>(Schema, Map)"})
  void testNewWebSocketResultsOutput_given42_whenHashMapMax_batch_sizeIs42() {
    // Arrange
    Schema schema = mock(Schema.class);

    HashMap<String, String> config = new HashMap<>();
    config.put(WebSocketResultsOutput.MAX_BATCH_SIZE, "42");
    config.put(WebSocketOutput.ENDPOINT, "Config");
    config.put(WebSocketOutput.CONNECTION_ID, "Config");
    config.put(WebSocketOutput.REGION, "Config");
    config.put(WebSocketOutput.ACCESS_KEY, null);
    config.put(WebSocketOutput.SECRET_KEY, null);

    // Act
    WebSocketResultsOutput actualWebSocketResultsOutput = new WebSocketResultsOutput(schema, config);

    // Assert
    assertEquals("Config", actualWebSocketResultsOutput.getConnectionId());
    assertEquals("Config", actualWebSocketResultsOutput.getEndpoint());
    assertFalse(actualWebSocketResultsOutput.isClientGone());
  }

  /**
   * Test {@link WebSocketResultsOutput#WebSocketResultsOutput(Schema, Map)}.
   * <ul>
   *   <li>Given empty string.</li>
   *   <li>When {@link HashMap#HashMap()} {@link WebSocketOutput#ACCESS_KEY} is {@code Config}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WebSocketResultsOutput#WebSocketResultsOutput(Schema, Map)}
   */
  @Test
  @DisplayName("Test new WebSocketResultsOutput(Schema, Map); given empty string; when HashMap() ACCESS_KEY is 'Config'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void WebSocketResultsOutput.<init>(Schema, Map)"})
  void testNewWebSocketResultsOutput_givenEmptyString_whenHashMapAccess_keyIsConfig() {
    // Arrange
    Schema schema = mock(Schema.class);

    HashMap<String, String> config = new HashMap<>();
    config.put(WebSocketResultsOutput.MAX_BATCH_SIZE, null);
    config.put(WebSocketOutput.ENDPOINT, "Config");
    config.put(WebSocketOutput.CONNECTION_ID, "Config");
    config.put(WebSocketOutput.REGION, "Config");
    config.put(WebSocketOutput.ACCESS_KEY, "Config");
    config.put(WebSocketOutput.SECRET_KEY, "");

    // Act
    WebSocketResultsOutput actualWebSocketResultsOutput = new WebSocketResultsOutput(schema, config);

    // Assert
    assertEquals("Config", actualWebSocketResultsOutput.getConnectionId());
    assertEquals("Config", actualWebSocketResultsOutput.getEndpoint());
    assertFalse(actualWebSocketResultsOutput.isClientGone());
  }

  /**
   * Test {@link WebSocketResultsOutput#WebSocketResultsOutput(Schema, Map)}.
   * <ul>
   *   <li>When {@link HashMap#HashMap()} {@link WebSocketOutput#ACCESS_KEY} is empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link WebSocketResultsOutput#WebSocketResultsOutput(Schema, Map)}
   */
  @Test
  @DisplayName("Test new WebSocketResultsOutput(Schema, Map); when HashMap() ACCESS_KEY is empty string")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void WebSocketResultsOutput.<init>(Schema, Map)"})
  void testNewWebSocketResultsOutput_whenHashMapAccess_keyIsEmptyString() {
    // Arrange
    Schema schema = mock(Schema.class);

    HashMap<String, String> config = new HashMap<>();
    config.put(WebSocketResultsOutput.MAX_BATCH_SIZE, null);
    config.put(WebSocketOutput.ENDPOINT, "Config");
    config.put(WebSocketOutput.CONNECTION_ID, "Config");
    config.put(WebSocketOutput.REGION, "Config");
    config.put(WebSocketOutput.ACCESS_KEY, "");
    config.put(WebSocketOutput.SECRET_KEY, null);

    // Act
    WebSocketResultsOutput actualWebSocketResultsOutput = new WebSocketResultsOutput(schema, config);

    // Assert
    assertEquals("Config", actualWebSocketResultsOutput.getConnectionId());
    assertEquals("Config", actualWebSocketResultsOutput.getEndpoint());
    assertFalse(actualWebSocketResultsOutput.isClientGone());
  }

  /**
   * Test {@link WebSocketResultsOutput#WebSocketResultsOutput(Schema, Map)}.
   * <ul>
   *   <li>When {@link HashMap#HashMap()} {@link WebSocketOutput#ACCESS_KEY} is empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link WebSocketResultsOutput#WebSocketResultsOutput(Schema, Map)}
   */
  @Test
  @DisplayName("Test new WebSocketResultsOutput(Schema, Map); when HashMap() ACCESS_KEY is empty string")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void WebSocketResultsOutput.<init>(Schema, Map)"})
  void testNewWebSocketResultsOutput_whenHashMapAccess_keyIsEmptyString2() {
    // Arrange
    Schema schema = mock(Schema.class);

    HashMap<String, String> config = new HashMap<>();
    config.put(WebSocketResultsOutput.MAX_BATCH_SIZE, null);
    config.put(WebSocketOutput.ENDPOINT, "Config");
    config.put(WebSocketOutput.CONNECTION_ID, "Config");
    config.put(WebSocketOutput.REGION, "Config");
    config.put(WebSocketOutput.ACCESS_KEY, "");
    config.put(WebSocketOutput.SECRET_KEY, "");

    // Act
    WebSocketResultsOutput actualWebSocketResultsOutput = new WebSocketResultsOutput(schema, config);

    // Assert
    assertEquals("Config", actualWebSocketResultsOutput.getConnectionId());
    assertEquals("Config", actualWebSocketResultsOutput.getEndpoint());
    assertFalse(actualWebSocketResultsOutput.isClientGone());
  }

  /**
   * Test {@link WebSocketResultsOutput#WebSocketResultsOutput(Schema, Map)}.
   * <ul>
   *   <li>When {@link HashMap#HashMap()} {@link WebSocketOutput#ACCESS_KEY} is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WebSocketResultsOutput#WebSocketResultsOutput(Schema, Map)}
   */
  @Test
  @DisplayName("Test new WebSocketResultsOutput(Schema, Map); when HashMap() ACCESS_KEY is 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void WebSocketResultsOutput.<init>(Schema, Map)"})
  void testNewWebSocketResultsOutput_whenHashMapAccess_keyIsNull() {
    // Arrange
    Schema schema = mock(Schema.class);

    HashMap<String, String> config = new HashMap<>();
    config.put(WebSocketResultsOutput.MAX_BATCH_SIZE, null);
    config.put(WebSocketOutput.ENDPOINT, "Config");
    config.put(WebSocketOutput.CONNECTION_ID, "Config");
    config.put(WebSocketOutput.REGION, "Config");
    config.put(WebSocketOutput.ACCESS_KEY, null);
    config.put(WebSocketOutput.SECRET_KEY, null);

    // Act
    WebSocketResultsOutput actualWebSocketResultsOutput = new WebSocketResultsOutput(schema, config);

    // Assert
    assertEquals("Config", actualWebSocketResultsOutput.getConnectionId());
    assertEquals("Config", actualWebSocketResultsOutput.getEndpoint());
    assertFalse(actualWebSocketResultsOutput.isClientGone());
  }

  /**
   * Test {@link WebSocketResultsOutput#WebSocketResultsOutput(Schema, Map)}.
   * <ul>
   *   <li>When {@link HashMap#HashMap()} {@link WebSocketResultsOutput#MAX_BATCH_SIZE} is empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link WebSocketResultsOutput#WebSocketResultsOutput(Schema, Map)}
   */
  @Test
  @DisplayName("Test new WebSocketResultsOutput(Schema, Map); when HashMap() MAX_BATCH_SIZE is empty string")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void WebSocketResultsOutput.<init>(Schema, Map)"})
  void testNewWebSocketResultsOutput_whenHashMapMax_batch_sizeIsEmptyString() {
    // Arrange
    Schema schema = mock(Schema.class);

    HashMap<String, String> config = new HashMap<>();
    config.put(WebSocketResultsOutput.MAX_BATCH_SIZE, "");
    config.put(WebSocketOutput.ENDPOINT, "Config");
    config.put(WebSocketOutput.CONNECTION_ID, "Config");
    config.put(WebSocketOutput.REGION, "Config");
    config.put(WebSocketOutput.ACCESS_KEY, null);
    config.put(WebSocketOutput.SECRET_KEY, null);

    // Act
    WebSocketResultsOutput actualWebSocketResultsOutput = new WebSocketResultsOutput(schema, config);

    // Assert
    assertEquals("Config", actualWebSocketResultsOutput.getConnectionId());
    assertEquals("Config", actualWebSocketResultsOutput.getEndpoint());
    assertFalse(actualWebSocketResultsOutput.isClientGone());
  }

  /**
   * Test {@link WebSocketResultsOutput#WebSocketResultsOutput(Schema, Map)}.
   * <ul>
   *   <li>When {@link HashMap#HashMap()} {@link WebSocketOutput#SECRET_KEY} is {@code Config}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WebSocketResultsOutput#WebSocketResultsOutput(Schema, Map)}
   */
  @Test
  @DisplayName("Test new WebSocketResultsOutput(Schema, Map); when HashMap() SECRET_KEY is 'Config'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void WebSocketResultsOutput.<init>(Schema, Map)"})
  void testNewWebSocketResultsOutput_whenHashMapSecret_keyIsConfig() {
    // Arrange
    Schema schema = mock(Schema.class);

    HashMap<String, String> config = new HashMap<>();
    config.put(WebSocketResultsOutput.MAX_BATCH_SIZE, null);
    config.put(WebSocketOutput.ENDPOINT, "Config");
    config.put(WebSocketOutput.CONNECTION_ID, "Config");
    config.put(WebSocketOutput.REGION, "Config");
    config.put(WebSocketOutput.ACCESS_KEY, "Config");
    config.put(WebSocketOutput.SECRET_KEY, "Config");

    // Act
    WebSocketResultsOutput actualWebSocketResultsOutput = new WebSocketResultsOutput(schema, config);

    // Assert
    assertEquals("Config", actualWebSocketResultsOutput.getConnectionId());
    assertEquals("Config", actualWebSocketResultsOutput.getEndpoint());
    assertFalse(actualWebSocketResultsOutput.isClientGone());
  }
}
