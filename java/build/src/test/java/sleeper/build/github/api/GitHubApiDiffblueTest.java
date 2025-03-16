package sleeper.build.github.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import javax.ws.rs.RuntimeType;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Configuration;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.HttpUrlConnectorProvider;
import org.glassfish.jersey.client.JerseyWebTarget;
import org.glassfish.jersey.uri.internal.JerseyUriBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class GitHubApiDiffblueTest {
  /**
   * Test {@link GitHubApi#path(String)}.
   * <p>
   * Method under test: {@link GitHubApi#path(String)}
   */
  @Test
  @DisplayName("Test path(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"WebTarget GitHubApi.path(String)"})
  void testPath() {
    // Arrange and Act
    WebTarget actualPathResult = GitHubApi
        .withBaseUrlAndToken("https://example.org/example", "https://example.org/example")
        .path("Path");

    // Assert
    Configuration configuration = actualPathResult.getConfiguration();
    assertTrue(((ClientConfig) configuration).getConnectorProvider() instanceof HttpUrlConnectorProvider);
    assertTrue(actualPathResult instanceof JerseyWebTarget);
    UriBuilder uriBuilder = actualPathResult.getUriBuilder();
    assertTrue(uriBuilder instanceof JerseyUriBuilder);
    assertEquals("https://example.org/example/Path", actualPathResult.getUri().toString());
    assertNull(((ClientConfig) configuration).getExecutorService());
    assertNull(((ClientConfig) configuration).getScheduledExecutorService());
    assertEquals(1, configuration.getClasses().size());
    assertEquals(RuntimeType.CLIENT, configuration.getRuntimeType());
    assertTrue(configuration.getPropertyNames().isEmpty());
    assertTrue(configuration.getProperties().isEmpty());
    assertTrue(configuration.getInstances().isEmpty());
    assertTrue(((JerseyUriBuilder) uriBuilder).isAbsolute());
  }

  /**
   * Test {@link GitHubApi#withToken(String)}.
   * <p>
   * Method under test: {@link GitHubApi#withToken(String)}
   */
  @Test
  @DisplayName("Test withToken(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"GitHubApi GitHubApi.withToken(String)"})
  void testWithToken() {
    // Arrange, Act and Assert
    WebTarget pathResult = GitHubApi.withToken("ABC123").path("Path");
    Configuration configuration = pathResult.getConfiguration();
    assertTrue(((ClientConfig) configuration).getConnectorProvider() instanceof HttpUrlConnectorProvider);
    assertTrue(pathResult instanceof JerseyWebTarget);
    UriBuilder uriBuilder = pathResult.getUriBuilder();
    assertTrue(uriBuilder instanceof JerseyUriBuilder);
    assertEquals("https://api.github.com/Path", pathResult.getUri().toString());
    assertNull(((ClientConfig) configuration).getExecutorService());
    assertNull(((ClientConfig) configuration).getScheduledExecutorService());
    assertEquals(1, configuration.getClasses().size());
    assertEquals(RuntimeType.CLIENT, configuration.getRuntimeType());
    assertTrue(configuration.getPropertyNames().isEmpty());
    assertTrue(configuration.getProperties().isEmpty());
    assertTrue(configuration.getInstances().isEmpty());
    assertTrue(((JerseyUriBuilder) uriBuilder).isAbsolute());
  }

  /**
   * Test {@link GitHubApi#withBaseUrlAndToken(String, String)}.
   * <p>
   * Method under test: {@link GitHubApi#withBaseUrlAndToken(String, String)}
   */
  @Test
  @DisplayName("Test withBaseUrlAndToken(String, String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"GitHubApi GitHubApi.withBaseUrlAndToken(String, String)"})
  void testWithBaseUrlAndToken() {
    // Arrange, Act and Assert
    WebTarget pathResult = GitHubApi.withBaseUrlAndToken("https://example.org/example", "https://example.org/example")
        .path("Path");
    Configuration configuration = pathResult.getConfiguration();
    assertTrue(((ClientConfig) configuration).getConnectorProvider() instanceof HttpUrlConnectorProvider);
    assertTrue(pathResult instanceof JerseyWebTarget);
    UriBuilder uriBuilder = pathResult.getUriBuilder();
    assertTrue(uriBuilder instanceof JerseyUriBuilder);
    assertEquals("https://example.org/example/Path", pathResult.getUri().toString());
    assertNull(((ClientConfig) configuration).getExecutorService());
    assertNull(((ClientConfig) configuration).getScheduledExecutorService());
    assertEquals(1, configuration.getClasses().size());
    assertEquals(RuntimeType.CLIENT, configuration.getRuntimeType());
    assertTrue(configuration.getPropertyNames().isEmpty());
    assertTrue(configuration.getProperties().isEmpty());
    assertTrue(configuration.getInstances().isEmpty());
    assertTrue(((JerseyUriBuilder) uriBuilder).isAbsolute());
  }
}
