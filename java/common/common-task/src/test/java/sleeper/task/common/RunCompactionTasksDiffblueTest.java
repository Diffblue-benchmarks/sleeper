package sleeper.task.common;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.task.common.QueueMessageCount.Client;
import sleeper.task.common.RunCompactionTasks.TaskCounts;
import sleeper.task.common.RunCompactionTasks.TaskLauncher;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.services.autoscaling.AutoScalingClient;
import software.amazon.awssdk.services.ec2.Ec2Client;
import software.amazon.awssdk.services.ecs.EcsClient;
import software.amazon.awssdk.services.ecs.model.DescribeClustersRequest;
import software.amazon.awssdk.services.ecs.model.DescribeClustersRequest.Builder;

class RunCompactionTasksDiffblueTest {
  /**
   * Test {@link RunCompactionTasks#run(Client)}.
   * <ul>
   *   <li>Given {@link IllegalArgumentException#IllegalArgumentException(String)} with {@code Queue URL is {}}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RunCompactionTasks#run(Client)}
   */
  @Test
  @DisplayName("Test run(Client); given IllegalArgumentException(String) with 'Queue URL is {}'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void RunCompactionTasks.run(Client)"})
  void testRun_givenIllegalArgumentExceptionWithQueueUrlIs() {
    // Arrange
    new IllegalArgumentException("Queue URL is {}");
    TaskCounts taskCounts = mock(TaskCounts.class);
    when(taskCounts.getRunningAndPending()).thenReturn(-1);
    InstanceProperties instanceProperties = new InstanceProperties();
    RunCompactionTasks runCompactionTasks = new RunCompactionTasks(instanceProperties, taskCounts,
        EC2Scaler.create(new InstanceProperties(), mock(AutoScalingClient.class), mock(Ec2Client.class)),
        mock(TaskLauncher.class));
    Client queueMessageCount = mock(Client.class);
    when(queueMessageCount.getQueueMessageCount(Mockito.<String>any()))
        .thenReturn(QueueMessageCount.approximateNumberVisibleAndNotVisible(-1, 1));

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> runCompactionTasks.run(queueMessageCount));
    verify(queueMessageCount).getQueueMessageCount(isNull());
    verify(taskCounts).getRunningAndPending();
  }

  /**
   * Test {@link RunCompactionTasks#run(Client)}.
   * <ul>
   *   <li>Given {@link TaskCounts} {@link TaskCounts#getRunningAndPending()} return {@link Integer#MIN_VALUE}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RunCompactionTasks#run(Client)}
   */
  @Test
  @DisplayName("Test run(Client); given TaskCounts getRunningAndPending() return MIN_VALUE")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void RunCompactionTasks.run(Client)"})
  void testRun_givenTaskCountsGetRunningAndPendingReturnMin_value() {
    // Arrange
    TaskCounts taskCounts = mock(TaskCounts.class);
    when(taskCounts.getRunningAndPending()).thenReturn(Integer.MIN_VALUE);
    InstanceProperties instanceProperties = new InstanceProperties();
    RunCompactionTasks runCompactionTasks = new RunCompactionTasks(instanceProperties, taskCounts,
        EC2Scaler.create(new InstanceProperties(), mock(AutoScalingClient.class), mock(Ec2Client.class)),
        mock(TaskLauncher.class));
    Client queueMessageCount = mock(Client.class);
    when(queueMessageCount.getQueueMessageCount(Mockito.<String>any()))
        .thenReturn(QueueMessageCount.approximateNumberVisibleAndNotVisible(1, 1));

    // Act
    runCompactionTasks.run(queueMessageCount);

    // Assert
    verify(queueMessageCount).getQueueMessageCount(isNull());
    verify(taskCounts).getRunningAndPending();
  }

  /**
   * Test {@link RunCompactionTasks#run(Client)}.
   * <ul>
   *   <li>Given {@link TaskLauncher} {@link TaskLauncher#launchTasks(int, BooleanSupplier)} does nothing.</li>
   *   <li>Then calls {@link TaskLauncher#launchTasks(int, BooleanSupplier)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RunCompactionTasks#run(Client)}
   */
  @Test
  @DisplayName("Test run(Client); given TaskLauncher launchTasks(int, BooleanSupplier) does nothing; then calls launchTasks(int, BooleanSupplier)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void RunCompactionTasks.run(Client)"})
  void testRun_givenTaskLauncherLaunchTasksDoesNothing_thenCallsLaunchTasks() {
    // Arrange
    TaskCounts taskCounts = mock(TaskCounts.class);
    when(taskCounts.getRunningAndPending()).thenReturn(3);
    TaskLauncher taskLauncher = mock(TaskLauncher.class);
    doNothing().when(taskLauncher).launchTasks(anyInt(), Mockito.<BooleanSupplier>any());
    InstanceProperties instanceProperties = new InstanceProperties();
    RunCompactionTasks runCompactionTasks = new RunCompactionTasks(instanceProperties, taskCounts,
        EC2Scaler.create(new InstanceProperties(), mock(AutoScalingClient.class), mock(Ec2Client.class)), taskLauncher);
    Client queueMessageCount = mock(Client.class);
    when(queueMessageCount.getQueueMessageCount(Mockito.<String>any()))
        .thenReturn(QueueMessageCount.approximateNumberVisibleAndNotVisible(1, 1));

    // Act
    runCompactionTasks.run(queueMessageCount);

    // Assert
    verify(queueMessageCount).getQueueMessageCount(isNull());
    verify(taskCounts).getRunningAndPending();
    verify(taskLauncher).launchTasks(eq(1), isA(BooleanSupplier.class));
  }

  /**
   * Test {@link RunCompactionTasks#run(Client)}.
   * <ul>
   *   <li>Given {@link TaskLauncher} {@link TaskLauncher#launchTasks(int, BooleanSupplier)} throw {@link IllegalArgumentException#IllegalArgumentException(String)} with {@code Queue URL is {}}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RunCompactionTasks#run(Client)}
   */
  @Test
  @DisplayName("Test run(Client); given TaskLauncher launchTasks(int, BooleanSupplier) throw IllegalArgumentException(String) with 'Queue URL is {}'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void RunCompactionTasks.run(Client)"})
  void testRun_givenTaskLauncherLaunchTasksThrowIllegalArgumentExceptionWithQueueUrlIs() {
    // Arrange
    TaskCounts taskCounts = mock(TaskCounts.class);
    when(taskCounts.getRunningAndPending()).thenReturn(3);
    TaskLauncher taskLauncher = mock(TaskLauncher.class);
    doThrow(new IllegalArgumentException("Queue URL is {}")).when(taskLauncher)
        .launchTasks(anyInt(), Mockito.<BooleanSupplier>any());
    InstanceProperties instanceProperties = new InstanceProperties();
    RunCompactionTasks runCompactionTasks = new RunCompactionTasks(instanceProperties, taskCounts,
        EC2Scaler.create(new InstanceProperties(), mock(AutoScalingClient.class), mock(Ec2Client.class)), taskLauncher);
    Client queueMessageCount = mock(Client.class);
    when(queueMessageCount.getQueueMessageCount(Mockito.<String>any()))
        .thenReturn(QueueMessageCount.approximateNumberVisibleAndNotVisible(1, 1));

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> runCompactionTasks.run(queueMessageCount));
    verify(queueMessageCount).getQueueMessageCount(isNull());
    verify(taskCounts).getRunningAndPending();
    verify(taskLauncher).launchTasks(eq(1), isA(BooleanSupplier.class));
  }

  /**
   * Test {@link RunCompactionTasks#run(Client)}.
   * <ul>
   *   <li>Then calls {@link EcsClient#describeClusters(Consumer)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RunCompactionTasks#run(Client)}
   */
  @Test
  @DisplayName("Test run(Client); then calls describeClusters(Consumer)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void RunCompactionTasks.run(Client)"})
  void testRun_thenCallsDescribeClusters() throws AwsServiceException, SdkClientException {
    // Arrange
    EcsClient ecsClient = mock(EcsClient.class);
    when(ecsClient.describeClusters(Mockito.<Consumer<Builder>>any()))
        .thenThrow(new IllegalArgumentException("Queue URL is {}"));
    RunCompactionTasks runCompactionTasks = new RunCompactionTasks(new InstanceProperties(), ecsClient,
        mock(AutoScalingClient.class), mock(Ec2Client.class));
    Client queueMessageCount = mock(Client.class);
    when(queueMessageCount.getQueueMessageCount(Mockito.<String>any()))
        .thenReturn(QueueMessageCount.approximateNumberVisibleAndNotVisible(1, 1));

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> runCompactionTasks.run(queueMessageCount));
    verify(queueMessageCount).getQueueMessageCount(isNull());
    verify(ecsClient).describeClusters(isA(Consumer.class));
  }

  /**
   * Test {@link RunCompactionTasks#runToMeetTargetTasks(int)}.
   * <ul>
   *   <li>Given {@link IllegalArgumentException#IllegalArgumentException(String)} with {@code foo}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RunCompactionTasks#runToMeetTargetTasks(int)}
   */
  @Test
  @DisplayName("Test runToMeetTargetTasks(int); given IllegalArgumentException(String) with 'foo'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void RunCompactionTasks.runToMeetTargetTasks(int)"})
  void testRunToMeetTargetTasks_givenIllegalArgumentExceptionWithFoo() {
    // Arrange
    new IllegalArgumentException("foo");
    TaskCounts taskCounts = mock(TaskCounts.class);
    when(taskCounts.getRunningAndPending()).thenReturn(1);
    TaskLauncher taskLauncher = mock(TaskLauncher.class);
    doThrow(new IllegalArgumentException("Number of running and pending tasks is {}")).when(taskLauncher)
        .launchTasks(anyInt(), Mockito.<BooleanSupplier>any());
    InstanceProperties instanceProperties = new InstanceProperties();

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> (new RunCompactionTasks(instanceProperties, taskCounts,
            EC2Scaler.create(new InstanceProperties(), mock(AutoScalingClient.class), mock(Ec2Client.class)),
            taskLauncher)).runToMeetTargetTasks(3));
    verify(taskCounts).getRunningAndPending();
    verify(taskLauncher).launchTasks(eq(2), isA(BooleanSupplier.class));
  }

  /**
   * Test {@link RunCompactionTasks#runToMeetTargetTasks(int)}.
   * <ul>
   *   <li>Given {@link TaskCounts} {@link TaskCounts#getRunningAndPending()} return three.</li>
   * </ul>
   * <p>
   * Method under test: {@link RunCompactionTasks#runToMeetTargetTasks(int)}
   */
  @Test
  @DisplayName("Test runToMeetTargetTasks(int); given TaskCounts getRunningAndPending() return three")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void RunCompactionTasks.runToMeetTargetTasks(int)"})
  void testRunToMeetTargetTasks_givenTaskCountsGetRunningAndPendingReturnThree() {
    // Arrange
    TaskCounts taskCounts = mock(TaskCounts.class);
    when(taskCounts.getRunningAndPending()).thenReturn(3);
    InstanceProperties instanceProperties = new InstanceProperties();

    // Act
    (new RunCompactionTasks(instanceProperties, taskCounts,
        EC2Scaler.create(new InstanceProperties(), mock(AutoScalingClient.class), mock(Ec2Client.class)),
        mock(TaskLauncher.class))).runToMeetTargetTasks(3);

    // Assert
    verify(taskCounts).getRunningAndPending();
  }

  /**
   * Test {@link RunCompactionTasks#runToMeetTargetTasks(int)}.
   * <ul>
   *   <li>Then calls {@link EcsClient#describeClusters(Consumer)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RunCompactionTasks#runToMeetTargetTasks(int)}
   */
  @Test
  @DisplayName("Test runToMeetTargetTasks(int); then calls describeClusters(Consumer)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void RunCompactionTasks.runToMeetTargetTasks(int)"})
  void testRunToMeetTargetTasks_thenCallsDescribeClusters() throws AwsServiceException, SdkClientException {
    // Arrange
    EcsClient ecsClient = mock(EcsClient.class);
    when(ecsClient.describeClusters(Mockito.<Consumer<Builder>>any())).thenThrow(new IllegalArgumentException("foo"));

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> (new RunCompactionTasks(new InstanceProperties(), ecsClient,
        mock(AutoScalingClient.class), mock(Ec2Client.class))).runToMeetTargetTasks(3));
    verify(ecsClient).describeClusters(isA(Consumer.class));
  }

  /**
   * Test {@link RunCompactionTasks#runToMeetTargetTasks(int)}.
   * <ul>
   *   <li>Then calls {@link TaskLauncher#launchTasks(int, BooleanSupplier)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RunCompactionTasks#runToMeetTargetTasks(int)}
   */
  @Test
  @DisplayName("Test runToMeetTargetTasks(int); then calls launchTasks(int, BooleanSupplier)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void RunCompactionTasks.runToMeetTargetTasks(int)"})
  void testRunToMeetTargetTasks_thenCallsLaunchTasks() {
    // Arrange
    TaskCounts taskCounts = mock(TaskCounts.class);
    when(taskCounts.getRunningAndPending()).thenReturn(1);
    TaskLauncher taskLauncher = mock(TaskLauncher.class);
    doNothing().when(taskLauncher).launchTasks(anyInt(), Mockito.<BooleanSupplier>any());
    InstanceProperties instanceProperties = new InstanceProperties();

    // Act
    (new RunCompactionTasks(instanceProperties, taskCounts,
        EC2Scaler.create(new InstanceProperties(), mock(AutoScalingClient.class), mock(Ec2Client.class)), taskLauncher))
        .runToMeetTargetTasks(3);

    // Assert
    verify(taskCounts).getRunningAndPending();
    verify(taskLauncher).launchTasks(eq(2), isA(BooleanSupplier.class));
  }
}
