package sleeper.systemtest.drivers.ingest;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.function.BooleanSupplier;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.instance.InstanceProperty;
import sleeper.core.properties.table.TableProperties;
import sleeper.core.properties.table.TableProperty;
import sleeper.core.util.PollWithRetries;
import sleeper.core.util.PollWithRetries.CheckFailedException;
import sleeper.core.util.ThreadSleep;
import sleeper.systemtest.configuration.SystemTestProperty;
import sleeper.systemtest.configuration.SystemTestStandaloneProperties;
import sleeper.systemtest.dsl.instance.DeployedSystemTestResources;
import sleeper.systemtest.dsl.instance.SystemTestInstanceContext;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.services.ecs.EcsClient;
import software.amazon.awssdk.services.ecs.model.RunTaskRequest;

class AwsDataGenerationTasksDriverDiffblueTest {
  /**
   * Test {@link AwsDataGenerationTasksDriver#runDataGenerationTasks(PollWithRetries)}.
   * <p>
   * Method under test: {@link AwsDataGenerationTasksDriver#runDataGenerationTasks(PollWithRetries)}
   */
  @Test
  @DisplayName("Test runDataGenerationTasks(PollWithRetries)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AwsDataGenerationTasksDriver.runDataGenerationTasks(PollWithRetries)"})
  void testRunDataGenerationTasks() {
    // Arrange
    SystemTestStandaloneProperties systemTestStandaloneProperties = mock(SystemTestStandaloneProperties.class);
    when(systemTestStandaloneProperties.getInt(Mockito.<SystemTestProperty>any())).thenReturn(0);
    when(systemTestStandaloneProperties.get(Mockito.<SystemTestProperty>any())).thenReturn("Get");
    DeployedSystemTestResources systemTest = mock(DeployedSystemTestResources.class);
    when(systemTest.getProperties()).thenReturn(systemTestStandaloneProperties);
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.getList(Mockito.<InstanceProperty>any())).thenReturn(new ArrayList<>());
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.get(Mockito.<TableProperty>any())).thenReturn("Get");
    SystemTestInstanceContext instance = mock(SystemTestInstanceContext.class);
    when(instance.getInstanceProperties()).thenReturn(instanceProperties);
    when(instance.getTableProperties()).thenReturn(tableProperties);
    AwsDataGenerationTasksDriver awsDataGenerationTasksDriver = new AwsDataGenerationTasksDriver(systemTest, instance,
        mock(EcsClient.class));
    PollWithRetries poll = PollWithRetries.builder()
        .maxRetries(3)
        .pollIntervalMillis(42L)
        .sleepInInterval(mock(ThreadSleep.class))
        .build();

    // Act
    awsDataGenerationTasksDriver.runDataGenerationTasks(poll);

    // Assert
    verify(systemTestStandaloneProperties, atLeast(1)).getInt(isA(SystemTestProperty.class));
    verify(instanceProperties, atLeast(1)).getList(Mockito.<InstanceProperty>any());
    verify(instanceProperties, atLeast(1)).get(Mockito.<InstanceProperty>any());
    verify(tableProperties).get(isA(TableProperty.class));
    verify(systemTestStandaloneProperties, atLeast(1)).get(Mockito.<SystemTestProperty>any());
    verify(systemTest).getProperties();
    verify(instance).getInstanceProperties();
    verify(instance).getTableProperties();
  }

  /**
   * Test {@link AwsDataGenerationTasksDriver#runDataGenerationTasks(PollWithRetries)}.
   * <ul>
   *   <li>Given {@link InterruptedException#InterruptedException(String)} with {@code SystemTestContainer}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AwsDataGenerationTasksDriver#runDataGenerationTasks(PollWithRetries)}
   */
  @Test
  @DisplayName("Test runDataGenerationTasks(PollWithRetries); given InterruptedException(String) with 'SystemTestContainer'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AwsDataGenerationTasksDriver.runDataGenerationTasks(PollWithRetries)"})
  void testRunDataGenerationTasks_givenInterruptedExceptionWithSystemTestContainer()
      throws InterruptedException, CheckFailedException {
    // Arrange
    SystemTestStandaloneProperties systemTestStandaloneProperties = mock(SystemTestStandaloneProperties.class);
    when(systemTestStandaloneProperties.getInt(Mockito.<SystemTestProperty>any())).thenReturn(0);
    when(systemTestStandaloneProperties.get(Mockito.<SystemTestProperty>any())).thenReturn("Get");
    DeployedSystemTestResources systemTest = mock(DeployedSystemTestResources.class);
    when(systemTest.getProperties()).thenReturn(systemTestStandaloneProperties);
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.getList(Mockito.<InstanceProperty>any())).thenReturn(new ArrayList<>());
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.get(Mockito.<TableProperty>any())).thenReturn("Get");
    SystemTestInstanceContext instance = mock(SystemTestInstanceContext.class);
    when(instance.getInstanceProperties()).thenReturn(instanceProperties);
    when(instance.getTableProperties()).thenReturn(tableProperties);
    AwsDataGenerationTasksDriver awsDataGenerationTasksDriver = new AwsDataGenerationTasksDriver(systemTest, instance,
        mock(EcsClient.class));
    PollWithRetries poll = mock(PollWithRetries.class);
    doThrow(new InterruptedException("SystemTestContainer")).when(poll)
        .pollUntil(Mockito.<String>any(), Mockito.<BooleanSupplier>any());

    // Act and Assert
    assertThrows(RuntimeException.class, () -> awsDataGenerationTasksDriver.runDataGenerationTasks(poll));
    verify(systemTestStandaloneProperties, atLeast(1)).getInt(isA(SystemTestProperty.class));
    verify(instanceProperties, atLeast(1)).getList(Mockito.<InstanceProperty>any());
    verify(instanceProperties, atLeast(1)).get(Mockito.<InstanceProperty>any());
    verify(tableProperties).get(isA(TableProperty.class));
    verify(poll).pollUntil(eq("generate data tasks finished"), isA(BooleanSupplier.class));
    verify(systemTestStandaloneProperties, atLeast(1)).get(Mockito.<SystemTestProperty>any());
    verify(systemTest).getProperties();
    verify(instance).getInstanceProperties();
    verify(instance).getTableProperties();
  }

  /**
   * Test {@link AwsDataGenerationTasksDriver#runDataGenerationTasks(PollWithRetries)}.
   * <ul>
   *   <li>Then calls {@link EcsClient#runTask(RunTaskRequest)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AwsDataGenerationTasksDriver#runDataGenerationTasks(PollWithRetries)}
   */
  @Test
  @DisplayName("Test runDataGenerationTasks(PollWithRetries); then calls runTask(RunTaskRequest)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AwsDataGenerationTasksDriver.runDataGenerationTasks(PollWithRetries)"})
  void testRunDataGenerationTasks_thenCallsRunTask() throws AwsServiceException, SdkClientException {
    // Arrange
    SystemTestStandaloneProperties systemTestStandaloneProperties = mock(SystemTestStandaloneProperties.class);
    when(systemTestStandaloneProperties.getInt(Mockito.<SystemTestProperty>any())).thenReturn(1);
    when(systemTestStandaloneProperties.get(Mockito.<SystemTestProperty>any())).thenReturn("Get");
    DeployedSystemTestResources systemTest = mock(DeployedSystemTestResources.class);
    when(systemTest.getProperties()).thenReturn(systemTestStandaloneProperties);
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.getList(Mockito.<InstanceProperty>any())).thenReturn(new ArrayList<>());
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.get(Mockito.<TableProperty>any())).thenReturn("Get");
    SystemTestInstanceContext instance = mock(SystemTestInstanceContext.class);
    when(instance.getInstanceProperties()).thenReturn(instanceProperties);
    when(instance.getTableProperties()).thenReturn(tableProperties);
    EcsClient ecsClient = mock(EcsClient.class);
    when(ecsClient.runTask(Mockito.<RunTaskRequest>any())).thenThrow(new RuntimeException("SystemTestContainer"));

    // Act and Assert
    assertThrows(RuntimeException.class,
        () -> (new AwsDataGenerationTasksDriver(systemTest, instance, ecsClient)).runDataGenerationTasks(null));
    verify(systemTestStandaloneProperties).getInt(isA(SystemTestProperty.class));
    verify(instanceProperties, atLeast(1)).getList(Mockito.<InstanceProperty>any());
    verify(instanceProperties, atLeast(1)).get(Mockito.<InstanceProperty>any());
    verify(tableProperties).get(isA(TableProperty.class));
    verify(systemTestStandaloneProperties, atLeast(1)).get(Mockito.<SystemTestProperty>any());
    verify(systemTest).getProperties();
    verify(instance).getInstanceProperties();
    verify(instance).getTableProperties();
    verify(ecsClient).runTask(isA(RunTaskRequest.class));
  }

  /**
   * Test {@link AwsDataGenerationTasksDriver#runDataGenerationTasks(PollWithRetries)}.
   * <ul>
   *   <li>When {@link PollWithRetries} {@link PollWithRetries#pollUntil(String, BooleanSupplier)} does nothing.</li>
   * </ul>
   * <p>
   * Method under test: {@link AwsDataGenerationTasksDriver#runDataGenerationTasks(PollWithRetries)}
   */
  @Test
  @DisplayName("Test runDataGenerationTasks(PollWithRetries); when PollWithRetries pollUntil(String, BooleanSupplier) does nothing")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AwsDataGenerationTasksDriver.runDataGenerationTasks(PollWithRetries)"})
  void testRunDataGenerationTasks_whenPollWithRetriesPollUntilDoesNothing()
      throws InterruptedException, CheckFailedException {
    // Arrange
    SystemTestStandaloneProperties systemTestStandaloneProperties = mock(SystemTestStandaloneProperties.class);
    when(systemTestStandaloneProperties.getInt(Mockito.<SystemTestProperty>any())).thenReturn(0);
    when(systemTestStandaloneProperties.get(Mockito.<SystemTestProperty>any())).thenReturn("Get");
    DeployedSystemTestResources systemTest = mock(DeployedSystemTestResources.class);
    when(systemTest.getProperties()).thenReturn(systemTestStandaloneProperties);
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.getList(Mockito.<InstanceProperty>any())).thenReturn(new ArrayList<>());
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.get(Mockito.<TableProperty>any())).thenReturn("Get");
    SystemTestInstanceContext instance = mock(SystemTestInstanceContext.class);
    when(instance.getInstanceProperties()).thenReturn(instanceProperties);
    when(instance.getTableProperties()).thenReturn(tableProperties);
    AwsDataGenerationTasksDriver awsDataGenerationTasksDriver = new AwsDataGenerationTasksDriver(systemTest, instance,
        mock(EcsClient.class));
    PollWithRetries poll = mock(PollWithRetries.class);
    doNothing().when(poll).pollUntil(Mockito.<String>any(), Mockito.<BooleanSupplier>any());

    // Act
    awsDataGenerationTasksDriver.runDataGenerationTasks(poll);

    // Assert
    verify(systemTestStandaloneProperties, atLeast(1)).getInt(isA(SystemTestProperty.class));
    verify(instanceProperties, atLeast(1)).getList(Mockito.<InstanceProperty>any());
    verify(instanceProperties, atLeast(1)).get(Mockito.<InstanceProperty>any());
    verify(tableProperties).get(isA(TableProperty.class));
    verify(poll).pollUntil(eq("generate data tasks finished"), isA(BooleanSupplier.class));
    verify(systemTestStandaloneProperties, atLeast(1)).get(Mockito.<SystemTestProperty>any());
    verify(systemTest).getProperties();
    verify(instance).getInstanceProperties();
    verify(instance).getTableProperties();
  }
}
