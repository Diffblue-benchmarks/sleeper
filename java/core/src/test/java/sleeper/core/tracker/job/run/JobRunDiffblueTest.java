package sleeper.core.tracker.job.run;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.tracker.job.run.JobRun.Builder;
import sleeper.core.tracker.job.status.JobStatusUpdate;

class JobRunDiffblueTest {
  /**
   * Test Builder {@link Builder#build()}.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Builder#build()}
   *   <li>{@link Builder#taskId(String)}
   * </ul>
   */
  @Test
  @DisplayName("Test Builder build()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"JobRun Builder.build()", "Builder Builder.taskId(String)"})
  void testBuilderBuild() {
    // Arrange and Act
    JobRun actualBuildResult = JobRun.builder().taskId("42").build();

    // Assert
    assertEquals("42", actualBuildResult.getTaskId());
    assertTrue(actualBuildResult.getStatusUpdates().isEmpty());
  }

  /**
   * Test Builder {@link Builder#statusUpdate(JobStatusUpdate)}.
   * <p>
   * Method under test: {@link Builder#statusUpdate(JobStatusUpdate)}
   */
  @Test
  @DisplayName("Test Builder statusUpdate(JobStatusUpdate)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.statusUpdate(JobStatusUpdate)"})
  void testBuilderStatusUpdate() {
    // Arrange
    Builder builderResult = JobRun.builder();
    JobStatusUpdate statusUpdate = mock(JobStatusUpdate.class);

    // Act
    Builder actualStatusUpdateResult = builderResult.statusUpdate(statusUpdate);

    // Assert
    JobRun buildResult = builderResult.build();
    assertNull(buildResult.getLatestUpdateTime());
    List<JobStatusUpdate> statusUpdates = buildResult.getStatusUpdates();
    assertEquals(1, statusUpdates.size());
    assertSame(builderResult, actualStatusUpdateResult);
    assertSame(statusUpdate, statusUpdates.get(0));
    assertSame(statusUpdate, buildResult.getFirstUpdate());
    assertSame(statusUpdate, buildResult.getLatestUpdate());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link JobRun#toString()}
   *   <li>{@link JobRun#getStatusUpdates()}
   *   <li>{@link JobRun#getTaskId()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List JobRun.getStatusUpdates()", "String JobRun.getTaskId()", "String JobRun.toString()"})
  void testGettersAndSetters() {
    // Arrange
    JobRun buildResult = JobRun.builder().taskId("42").build();

    // Act
    String actualToStringResult = buildResult.toString();
    List<JobStatusUpdate> actualStatusUpdates = buildResult.getStatusUpdates();

    // Assert
    assertEquals("42", buildResult.getTaskId());
    assertEquals("JobRun{taskId='42', statusUpdates=[]}", actualToStringResult);
    assertTrue(actualStatusUpdates.isEmpty());
  }

  /**
   * Test {@link JobRun#getLastStatusOfType(Class)}.
   * <p>
   * Method under test: {@link JobRun#getLastStatusOfType(Class)}
   */
  @Test
  @DisplayName("Test getLastStatusOfType(Class)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.util.Optional JobRun.getLastStatusOfType(Class)"})
  void testGetLastStatusOfType() {
    // Arrange
    JobRun buildResult = JobRun.builder().taskId("42").build();
    Class<JobStatusUpdate> updateType = JobStatusUpdate.class;

    // Act and Assert
    assertFalse(buildResult.getLastStatusOfType(updateType).isPresent());
  }

  /**
   * Test {@link JobRun#equals(Object)}, and {@link JobRun#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link JobRun#equals(Object)}
   *   <li>{@link JobRun#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean JobRun.equals(Object)", "int JobRun.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    JobRun buildResult = JobRun.builder().taskId("42").build();
    JobRun buildResult2 = JobRun.builder().taskId("42").build();

    // Act and Assert
    assertEquals(buildResult, buildResult2);
    int expectedHashCodeResult = buildResult.hashCode();
    assertEquals(expectedHashCodeResult, buildResult2.hashCode());
  }

  /**
   * Test {@link JobRun#equals(Object)}, and {@link JobRun#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link JobRun#equals(Object)}
   *   <li>{@link JobRun#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean JobRun.equals(Object)", "int JobRun.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    JobRun buildResult = JobRun.builder().taskId("42").build();

    // Act and Assert
    assertEquals(buildResult, buildResult);
    int expectedHashCodeResult = buildResult.hashCode();
    assertEquals(expectedHashCodeResult, buildResult.hashCode());
  }

  /**
   * Test {@link JobRun#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link JobRun#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean JobRun.equals(Object)", "int JobRun.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    JobRun buildResult = JobRun.builder().taskId("Task Id").build();
    JobRun buildResult2 = JobRun.builder().taskId("42").build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link JobRun#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link JobRun#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean JobRun.equals(Object)", "int JobRun.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange
    JobRun buildResult = JobRun.builder().taskId("42").build();

    // Act and Assert
    assertNotEquals(buildResult, null);
  }

  /**
   * Test {@link JobRun#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link JobRun#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean JobRun.equals(Object)", "int JobRun.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange
    JobRun buildResult = JobRun.builder().taskId("42").build();

    // Act and Assert
    assertNotEquals(buildResult, "Different type to JobRun");
  }
}
