package sleeper.clients.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.amazonaws.auth.BasicSessionCredentials;
import com.amazonaws.auth.STSAssumeRoleSessionCredentialsProvider;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class AssumeSleeperRoleV1DiffblueTest {
  /**
   * Test {@link AssumeSleeperRoleV1#authEnvVars()}.
   * <ul>
   *   <li>Then return size is five.</li>
   * </ul>
   * <p>
   * Method under test: {@link AssumeSleeperRoleV1#authEnvVars()}
   */
  @Test
  @DisplayName("Test authEnvVars(); then return size is five")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Map AssumeSleeperRoleV1.authEnvVars()"})
  void testAuthEnvVars_thenReturnSizeIsFive() {
    // Arrange
    STSAssumeRoleSessionCredentialsProvider provider = mock(STSAssumeRoleSessionCredentialsProvider.class);
    when(provider.getCredentials()).thenReturn(
        new BasicSessionCredentials("EXAMPLEakiAIOSFODNN7", "EXAMPLEKEYwjalrXUtnFEMI/K7MDENG/bPxRfiCY", "ABC123"));

    // Act
    Map<String, String> actualAuthEnvVarsResult = (new AssumeSleeperRoleV1("us-east-2", provider)).authEnvVars();

    // Assert
    verify(provider).getCredentials();
    assertEquals(5, actualAuthEnvVarsResult.size());
    assertEquals("ABC123", actualAuthEnvVarsResult.get("AWS_SESSION_TOKEN"));
    assertEquals("EXAMPLEKEYwjalrXUtnFEMI/K7MDENG/bPxRfiCY", actualAuthEnvVarsResult.get("AWS_SECRET_ACCESS_KEY"));
    assertEquals("EXAMPLEakiAIOSFODNN7", actualAuthEnvVarsResult.get("AWS_ACCESS_KEY_ID"));
    assertEquals("us-east-2", actualAuthEnvVarsResult.get("AWS_DEFAULT_REGION"));
    assertEquals("us-east-2", actualAuthEnvVarsResult.get("AWS_REGION"));
  }
}
