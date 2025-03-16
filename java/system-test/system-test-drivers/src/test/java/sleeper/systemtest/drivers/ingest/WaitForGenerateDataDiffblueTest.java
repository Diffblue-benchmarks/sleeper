package sleeper.systemtest.drivers.ingest;

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
import java.util.ArrayList;
import java.util.List;
import java.util.function.BooleanSupplier;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.util.PollWithRetries;
import sleeper.core.util.PollWithRetries.CheckFailedException;
import sleeper.core.util.ThreadSleep;
import sleeper.systemtest.drivers.ingest.WaitForGenerateData.ECSTaskStatusFormat;
import sleeper.systemtest.drivers.ingest.json.TasksJson;
import sleeper.systemtest.drivers.ingest.json.TasksSummaryJson;
import software.amazon.awssdk.services.ecs.EcsClient;
import software.amazon.awssdk.services.ecs.model.Task;

class WaitForGenerateDataDiffblueTest {
  /**
   * Test {@link WaitForGenerateData#pollUntilFinished()}.
   * <p>
   * Method under test: {@link WaitForGenerateData#pollUntilFinished()}
   */
  @Test
  @DisplayName("Test pollUntilFinished()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void WaitForGenerateData.pollUntilFinished()"})
  void testPollUntilFinished() throws InterruptedException {
    // Arrange
    ECSTaskStatusFormat ecsStatusFormat = mock(ECSTaskStatusFormat.class);
    when(ecsStatusFormat.statusOutput(Mockito.<List<Task>>any())).thenReturn("Status Output");
    EcsClient ecsClient = mock(EcsClient.class);

    // Act
    (new WaitForGenerateData(ecsClient, new ArrayList<>(), ecsStatusFormat)).pollUntilFinished();

    // Assert
    verify(ecsStatusFormat).statusOutput(isA(List.class));
  }

  /**
   * Test {@link WaitForGenerateData#pollUntilFinished(PollWithRetries)} with {@code PollWithRetries}.
   * <ul>
   *   <li>Then calls {@link ECSTaskStatusFormat#statusOutput(List)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WaitForGenerateData#pollUntilFinished(PollWithRetries)}
   */
  @Test
  @DisplayName("Test pollUntilFinished(PollWithRetries) with 'PollWithRetries'; then calls statusOutput(List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void WaitForGenerateData.pollUntilFinished(PollWithRetries)"})
  void testPollUntilFinishedWithPollWithRetries_thenCallsStatusOutput() throws InterruptedException {
    // Arrange
    ECSTaskStatusFormat ecsStatusFormat = mock(ECSTaskStatusFormat.class);
    when(ecsStatusFormat.statusOutput(Mockito.<List<Task>>any())).thenReturn("Status Output");
    EcsClient ecsClient = mock(EcsClient.class);
    WaitForGenerateData waitForGenerateData = new WaitForGenerateData(ecsClient, new ArrayList<>(), ecsStatusFormat);
    PollWithRetries poll = PollWithRetries.builder()
        .maxRetries(3)
        .pollIntervalMillis(42L)
        .sleepInInterval(mock(ThreadSleep.class))
        .build();

    // Act
    waitForGenerateData.pollUntilFinished(poll);

    // Assert
    verify(ecsStatusFormat).statusOutput(isA(List.class));
  }

  /**
   * Test {@link WaitForGenerateData#pollUntilFinished(PollWithRetries)} with {@code PollWithRetries}.
   * <ul>
   *   <li>Then throw {@link InterruptedException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WaitForGenerateData#pollUntilFinished(PollWithRetries)}
   */
  @Test
  @DisplayName("Test pollUntilFinished(PollWithRetries) with 'PollWithRetries'; then throw InterruptedException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void WaitForGenerateData.pollUntilFinished(PollWithRetries)"})
  void testPollUntilFinishedWithPollWithRetries_thenThrowInterruptedException()
      throws InterruptedException, CheckFailedException {
    // Arrange
    EcsClient ecsClient = mock(EcsClient.class);
    WaitForGenerateData waitForGenerateData = new WaitForGenerateData(ecsClient, new ArrayList<>(),
        mock(ECSTaskStatusFormat.class));
    PollWithRetries poll = mock(PollWithRetries.class);
    doThrow(new InterruptedException("generate data tasks finished")).when(poll)
        .pollUntil(Mockito.<String>any(), Mockito.<BooleanSupplier>any());

    // Act and Assert
    assertThrows(InterruptedException.class, () -> waitForGenerateData.pollUntilFinished(poll));
    verify(poll).pollUntil(eq("generate data tasks finished"), isA(BooleanSupplier.class));
  }

  /**
   * Test {@link WaitForGenerateData#pollUntilFinished(PollWithRetries)} with {@code PollWithRetries}.
   * <ul>
   *   <li>When {@link PollWithRetries} {@link PollWithRetries#pollUntil(String, BooleanSupplier)} does nothing.</li>
   * </ul>
   * <p>
   * Method under test: {@link WaitForGenerateData#pollUntilFinished(PollWithRetries)}
   */
  @Test
  @DisplayName("Test pollUntilFinished(PollWithRetries) with 'PollWithRetries'; when PollWithRetries pollUntil(String, BooleanSupplier) does nothing")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void WaitForGenerateData.pollUntilFinished(PollWithRetries)"})
  void testPollUntilFinishedWithPollWithRetries_whenPollWithRetriesPollUntilDoesNothing()
      throws InterruptedException, CheckFailedException {
    // Arrange
    EcsClient ecsClient = mock(EcsClient.class);
    WaitForGenerateData waitForGenerateData = new WaitForGenerateData(ecsClient, new ArrayList<>(),
        mock(ECSTaskStatusFormat.class));
    PollWithRetries poll = mock(PollWithRetries.class);
    doNothing().when(poll).pollUntil(Mockito.<String>any(), Mockito.<BooleanSupplier>any());

    // Act
    waitForGenerateData.pollUntilFinished(poll);

    // Assert
    verify(poll).pollUntil(eq("generate data tasks finished"), isA(BooleanSupplier.class));
  }

  /**
   * Test {@link WaitForGenerateData#ecsTaskStatusFormat(String)}.
   * <ul>
   *   <li>Then statusOutput {@link ArrayList#ArrayList()} return {@link TasksSummaryJson}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WaitForGenerateData#ecsTaskStatusFormat(String)}
   */
  @Test
  @DisplayName("Test ecsTaskStatusFormat(String); then statusOutput ArrayList() return TasksSummaryJson")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"ECSTaskStatusFormat WaitForGenerateData.ecsTaskStatusFormat(String)"})
  void testEcsTaskStatusFormat_thenStatusOutputArrayListReturnTasksSummaryJson() {
    // Arrange and Act
    ECSTaskStatusFormat actualEcsTaskStatusFormatResult = WaitForGenerateData.ecsTaskStatusFormat("Format");

    // Assert
    assertTrue(actualEcsTaskStatusFormatResult.statusOutput(new ArrayList<>()) instanceof TasksSummaryJson);
  }

  /**
   * Test {@link WaitForGenerateData#ecsTaskStatusFormat(String)}.
   * <ul>
   *   <li>When {@code full}.</li>
   *   <li>Then statusOutput {@link ArrayList#ArrayList()} return {@link TasksJson}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WaitForGenerateData#ecsTaskStatusFormat(String)}
   */
  @Test
  @DisplayName("Test ecsTaskStatusFormat(String); when 'full'; then statusOutput ArrayList() return TasksJson")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"ECSTaskStatusFormat WaitForGenerateData.ecsTaskStatusFormat(String)"})
  void testEcsTaskStatusFormat_whenFull_thenStatusOutputArrayListReturnTasksJson() {
    // Arrange and Act
    ECSTaskStatusFormat actualEcsTaskStatusFormatResult = WaitForGenerateData.ecsTaskStatusFormat("full");
    Object actualStatusOutputResult = actualEcsTaskStatusFormatResult.statusOutput(new ArrayList<>());

    // Assert
    assertTrue(actualStatusOutputResult instanceof TasksJson);
    assertTrue(((TasksJson) actualStatusOutputResult).getTasks().isEmpty());
  }

  /**
   * Test {@link WaitForGenerateData#ecsTaskStatusFormat(String)}.
   * <ul>
   *   <li>When {@code full}.</li>
   *   <li>Then statusOutput {@link ArrayList#ArrayList()} return {@link TasksJson}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WaitForGenerateData#ecsTaskStatusFormat(String)}
   */
  @Test
  @DisplayName("Test ecsTaskStatusFormat(String); when 'full'; then statusOutput ArrayList() return TasksJson")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"ECSTaskStatusFormat WaitForGenerateData.ecsTaskStatusFormat(String)"})
  void testEcsTaskStatusFormat_whenFull_thenStatusOutputArrayListReturnTasksJson2() {
    // Arrange and Act
    ECSTaskStatusFormat actualEcsTaskStatusFormatResult = WaitForGenerateData.ecsTaskStatusFormat("full");
    ArrayList<Task> taskList = new ArrayList<>();
    taskList.addAll(new ArrayList<>());
    Object actualStatusOutputResult = actualEcsTaskStatusFormatResult.statusOutput(taskList);

    // Assert
    assertTrue(actualStatusOutputResult instanceof TasksJson);
    assertTrue(((TasksJson) actualStatusOutputResult).getTasks().isEmpty());
  }

  /**
   * Test {@link WaitForGenerateData#ecsTaskStatusFormat(String)}.
   * <ul>
   *   <li>When {@code status}.</li>
   *   <li>Then statusOutput {@link ArrayList#ArrayList()} return {@link List}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WaitForGenerateData#ecsTaskStatusFormat(String)}
   */
  @Test
  @DisplayName("Test ecsTaskStatusFormat(String); when 'status'; then statusOutput ArrayList() return List")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"ECSTaskStatusFormat WaitForGenerateData.ecsTaskStatusFormat(String)"})
  void testEcsTaskStatusFormat_whenStatus_thenStatusOutputArrayListReturnList() {
    // Arrange and Act
    ECSTaskStatusFormat actualEcsTaskStatusFormatResult = WaitForGenerateData.ecsTaskStatusFormat("status");
    Object actualStatusOutputResult = actualEcsTaskStatusFormatResult.statusOutput(new ArrayList<>());

    // Assert
    assertTrue(actualStatusOutputResult instanceof List);
    assertTrue(((List<Object>) actualStatusOutputResult).isEmpty());
  }
}
