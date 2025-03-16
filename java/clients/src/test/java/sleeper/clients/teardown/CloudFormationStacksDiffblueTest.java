package sleeper.clients.teardown;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.core.pagination.sync.SdkIterable;
import software.amazon.awssdk.services.cloudformation.CloudFormationClient;
import software.amazon.awssdk.services.cloudformation.model.ListStacksRequest;
import software.amazon.awssdk.services.cloudformation.model.ListStacksRequest.Builder;
import software.amazon.awssdk.services.cloudformation.model.StackSummary;
import software.amazon.awssdk.services.cloudformation.paginators.ListStacksIterable;

class CloudFormationStacksDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link CloudFormationStacks#CloudFormationStacks(List)}
   *   <li>{@link CloudFormationStacks#getStackNames()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void CloudFormationStacks.<init>(List)", "List CloudFormationStacks.getStackNames()"})
  void testGettersAndSetters() {
    // Arrange
    ArrayList<String> stackNames = new ArrayList<>();

    // Act
    List<String> actualStackNames = (new CloudFormationStacks(stackNames)).getStackNames();

    // Assert
    assertTrue(actualStackNames.isEmpty());
    assertSame(stackNames, actualStackNames);
  }

  /**
   * Test {@link CloudFormationStacks#CloudFormationStacks(CloudFormationClient)}.
   * <ul>
   *   <li>Then return StackNames Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link CloudFormationStacks#CloudFormationStacks(CloudFormationClient)}
   */
  @Test
  @DisplayName("Test new CloudFormationStacks(CloudFormationClient); then return StackNames Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void CloudFormationStacks.<init>(CloudFormationClient)"})
  void testNewCloudFormationStacks_thenReturnStackNamesEmpty() throws AwsServiceException, SdkClientException {
    // Arrange
    SdkIterable<StackSummary> sdkIterable = mock(SdkIterable.class);

    ArrayList<StackSummary> stackSummaryList = new ArrayList<>();
    Stream<StackSummary> streamResult = stackSummaryList.stream();
    when(sdkIterable.stream()).thenReturn(streamResult);
    ListStacksIterable listStacksIterable = mock(ListStacksIterable.class);
    when(listStacksIterable.stackSummaries()).thenReturn(sdkIterable);
    CloudFormationClient cloudFormation = mock(CloudFormationClient.class);
    when(cloudFormation.listStacksPaginator(Mockito.<Consumer<Builder>>any())).thenReturn(listStacksIterable);

    // Act
    CloudFormationStacks actualCloudFormationStacks = new CloudFormationStacks(cloudFormation);

    // Assert
    verify(sdkIterable).stream();
    verify(cloudFormation).listStacksPaginator(isA(Consumer.class));
    verify(listStacksIterable).stackSummaries();
    assertTrue(actualCloudFormationStacks.getStackNames().isEmpty());
  }

  /**
   * Test {@link CloudFormationStacks#anyIn(String)}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add empty string.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CloudFormationStacks#anyIn(String)}
   */
  @Test
  @DisplayName("Test anyIn(String); given ArrayList() add empty string; then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CloudFormationStacks.anyIn(String)"})
  void testAnyIn_givenArrayListAddEmptyString_thenReturnTrue() {
    // Arrange
    ArrayList<String> stackNames = new ArrayList<>();
    stackNames.add("");

    // Act and Assert
    assertTrue((new CloudFormationStacks(stackNames)).anyIn("String"));
  }

  /**
   * Test {@link CloudFormationStacks#anyIn(String)}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CloudFormationStacks#anyIn(String)}
   */
  @Test
  @DisplayName("Test anyIn(String); then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CloudFormationStacks.anyIn(String)"})
  void testAnyIn_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new CloudFormationStacks(new ArrayList<>())).anyIn("String"));
  }
}
