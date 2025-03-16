package sleeper.task.common;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.function.Consumer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.task.common.RunECSTasks.Builder;
import software.amazon.awssdk.services.ecs.model.RunTaskRequest;

class RunECSTasksDiffblueTest {
  /**
   * Test Builder {@link Builder#runTaskRequest(Consumer)} with {@code config}.
   * <p>
   * Method under test: {@link Builder#runTaskRequest(Consumer)}
   */
  @Test
  @DisplayName("Test Builder runTaskRequest(Consumer) with 'config'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.runTaskRequest(Consumer)"})
  void testBuilderRunTaskRequestWithConfig() {
    // Arrange
    Builder builderResult = RunECSTasks.builder();
    Consumer<RunTaskRequest.Builder> config = mock(Consumer.class);
    doNothing().when(config).accept(Mockito.<RunTaskRequest.Builder>any());

    // Act
    Builder actualRunTaskRequestResult = builderResult.runTaskRequest(config);

    // Assert
    verify(config).accept(isA(RunTaskRequest.Builder.class));
    assertSame(builderResult, actualRunTaskRequestResult);
  }

  /**
   * Test {@link RunECSTasks#runTasks(Consumer)} with {@code configuration}.
   * <ul>
   *   <li>When {@link Consumer} {@link Consumer#accept(Object)} does nothing.</li>
   *   <li>Then calls {@link Consumer#accept(Object)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RunECSTasks#runTasks(Consumer)}
   */
  @Test
  @DisplayName("Test runTasks(Consumer) with 'configuration'; when Consumer accept(Object) does nothing; then calls accept(Object)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void RunECSTasks.runTasks(Consumer)"})
  void testRunTasksWithConfiguration_whenConsumerAcceptDoesNothing_thenCallsAccept() {
    // Arrange
    Consumer<Builder> configuration = mock(Consumer.class);
    doNothing().when(configuration).accept(Mockito.<Builder>any());

    // Act
    RunECSTasks.runTasks(configuration);

    // Assert
    verify(configuration).accept(isA(Builder.class));
  }

  /**
   * Test {@link RunECSTasks#runTasksOrThrow(Consumer)} with {@code Consumer}.
   * <ul>
   *   <li>When {@link Consumer} {@link Consumer#accept(Object)} does nothing.</li>
   *   <li>Then calls {@link Consumer#accept(Object)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RunECSTasks#runTasksOrThrow(Consumer)}
   */
  @Test
  @DisplayName("Test runTasksOrThrow(Consumer) with 'Consumer'; when Consumer accept(Object) does nothing; then calls accept(Object)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void RunECSTasks.runTasksOrThrow(Consumer)"})
  void testRunTasksOrThrowWithConsumer_whenConsumerAcceptDoesNothing_thenCallsAccept() {
    // Arrange
    Consumer<Builder> configuration = mock(Consumer.class);
    doNothing().when(configuration).accept(Mockito.<Builder>any());

    // Act
    RunECSTasks.runTasksOrThrow(configuration);

    // Assert
    verify(configuration).accept(isA(Builder.class));
  }
}
