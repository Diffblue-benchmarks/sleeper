package sleeper.clients.teardown;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.properties.instance.InstanceProperties;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.services.ecr.EcrClient;
import software.amazon.awssdk.services.ecr.model.DeleteRepositoryRequest;
import software.amazon.awssdk.services.ecr.model.DeleteRepositoryRequest.Builder;

class RemoveECRRepositoriesDiffblueTest {
  /**
   * Test {@link RemoveECRRepositories#remove(EcrClient, InstanceProperties, List)}.
   * <ul>
   *   <li>Given {@code null}.</li>
   *   <li>Then calls {@link EcrClient#deleteRepository(Consumer)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RemoveECRRepositories#remove(EcrClient, InstanceProperties, List)}
   */
  @Test
  @DisplayName("Test remove(EcrClient, InstanceProperties, List); given 'null'; then calls deleteRepository(Consumer)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void RemoveECRRepositories.remove(EcrClient, InstanceProperties, List)"})
  void testRemove_givenNull_thenCallsDeleteRepository() throws AwsServiceException, SdkClientException {
    // Arrange
    EcrClient ecr = mock(EcrClient.class);
    when(ecr.deleteRepository(Mockito.<Consumer<Builder>>any())).thenReturn(null);
    InstanceProperties properties = new InstanceProperties();

    ArrayList<String> extraRepositories = new ArrayList<>();
    extraRepositories.add("foo");

    // Act
    RemoveECRRepositories.remove(ecr, properties, extraRepositories);

    // Assert
    verify(ecr).deleteRepository(isA(Consumer.class));
  }

  /**
   * Test {@link RemoveECRRepositories#remove(EcrClient, InstanceProperties, List)}.
   * <ul>
   *   <li>Given {@code null}.</li>
   *   <li>Then calls {@link EcrClient#deleteRepository(Consumer)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RemoveECRRepositories#remove(EcrClient, InstanceProperties, List)}
   */
  @Test
  @DisplayName("Test remove(EcrClient, InstanceProperties, List); given 'null'; then calls deleteRepository(Consumer)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void RemoveECRRepositories.remove(EcrClient, InstanceProperties, List)"})
  void testRemove_givenNull_thenCallsDeleteRepository2() throws AwsServiceException, SdkClientException {
    // Arrange
    EcrClient ecr = mock(EcrClient.class);
    when(ecr.deleteRepository(Mockito.<Consumer<Builder>>any())).thenReturn(null);
    InstanceProperties properties = new InstanceProperties();

    ArrayList<String> extraRepositories = new ArrayList<>();
    extraRepositories.add("foo");
    extraRepositories.add("foo");

    // Act
    RemoveECRRepositories.remove(ecr, properties, extraRepositories);

    // Assert
    verify(ecr, atLeast(1)).deleteRepository(Mockito.<Consumer<Builder>>any());
  }
}
