package sleeper.core.tracker.job.run;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.tracker.job.status.JobStatusUpdate;
import sleeper.core.tracker.job.status.JobStatusUpdateRecord;
import sleeper.core.tracker.job.status.JobStatusUpdateRecord.Builder;
import sleeper.core.tracker.job.status.TestJobRunStatus;

class JobRunsDiffblueTest {
  /**
   * Test {@link JobRuns#latestFirst(List)}.
   * <ul>
   *   <li>Given builder taskId {@code 42} build.</li>
   *   <li>Then return FirstRun TaskId is {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JobRuns#latestFirst(List)}
   */
  @Test
  @DisplayName("Test latestFirst(List); given builder taskId '42' build; then return FirstRun TaskId is '42'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"JobRuns JobRuns.latestFirst(List)"})
  void testLatestFirst_givenBuilderTaskId42Build_thenReturnFirstRunTaskIdIs42() {
    // Arrange
    ArrayList<JobRun> latestFirst = new ArrayList<>();
    JobRun buildResult = JobRun.builder().taskId("42").build();
    latestFirst.add(buildResult);

    // Act
    JobRuns actualLatestFirstResult = JobRuns.latestFirst(latestFirst);

    // Assert
    Optional<JobRun> firstRun = actualLatestFirstResult.getFirstRun();
    JobRun getResult = firstRun.get();
    assertEquals("42", getResult.getTaskId());
    List<JobRun> runsLatestFirst = actualLatestFirstResult.getRunsLatestFirst();
    assertEquals(1, runsLatestFirst.size());
    assertTrue(getResult.getStatusUpdates().isEmpty());
    assertTrue(firstRun.isPresent());
    assertTrue(actualLatestFirstResult.getLatestRun().isPresent());
    assertTrue(actualLatestFirstResult.isStarted());
    assertSame(getResult, runsLatestFirst.get(0));
  }

  /**
   * Test {@link JobRuns#latestFirst(List)}.
   * <ul>
   *   <li>Given builder taskId {@code 42} build.</li>
   *   <li>Then return LatestRun TaskId is {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JobRuns#latestFirst(List)}
   */
  @Test
  @DisplayName("Test latestFirst(List); given builder taskId '42' build; then return LatestRun TaskId is '42'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"JobRuns JobRuns.latestFirst(List)"})
  void testLatestFirst_givenBuilderTaskId42Build_thenReturnLatestRunTaskIdIs42() {
    // Arrange
    ArrayList<JobRun> latestFirst = new ArrayList<>();
    JobRun buildResult = JobRun.builder().taskId("42").build();
    latestFirst.add(buildResult);
    JobRun buildResult2 = JobRun.builder().taskId("42").build();
    latestFirst.add(buildResult2);

    // Act
    JobRuns actualLatestFirstResult = JobRuns.latestFirst(latestFirst);

    // Assert
    JobRun getResult = actualLatestFirstResult.getLatestRun().get();
    assertEquals("42", getResult.getTaskId());
    List<JobRun> runsLatestFirst = actualLatestFirstResult.getRunsLatestFirst();
    assertEquals(2, runsLatestFirst.size());
    assertTrue(getResult.getStatusUpdates().isEmpty());
    assertSame(getResult, runsLatestFirst.get(0));
  }

  /**
   * Test {@link JobRuns#latestFirst(List)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return not FirstRun Present.</li>
   * </ul>
   * <p>
   * Method under test: {@link JobRuns#latestFirst(List)}
   */
  @Test
  @DisplayName("Test latestFirst(List); when ArrayList(); then return not FirstRun Present")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"JobRuns JobRuns.latestFirst(List)"})
  void testLatestFirst_whenArrayList_thenReturnNotFirstRunPresent() {
    // Arrange and Act
    JobRuns actualLatestFirstResult = JobRuns.latestFirst(new ArrayList<>());

    // Assert
    Optional<JobRun> firstRun = actualLatestFirstResult.getFirstRun();
    assertFalse(firstRun.isPresent());
    assertFalse(actualLatestFirstResult.isStarted());
    assertTrue(actualLatestFirstResult.getRunsLatestFirst().isEmpty());
    assertSame(firstRun, actualLatestFirstResult.getLatestRun());
  }

  /**
   * Test {@link JobRuns#fromRecordsLatestFirst(List)}.
   * <p>
   * Method under test: {@link JobRuns#fromRecordsLatestFirst(List)}
   */
  @Test
  @DisplayName("Test fromRecordsLatestFirst(List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"JobRuns JobRuns.fromRecordsLatestFirst(List)"})
  void testFromRecordsLatestFirst() {
    // Arrange
    ArrayList<JobStatusUpdateRecord> recordList = new ArrayList<>();
    Builder builderResult = JobStatusUpdateRecord.builder();
    JobStatusUpdateRecord buildResult = builderResult
        .expiryDate(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .jobId("42")
        .jobRunId("42")
        .statusUpdate(mock(JobStatusUpdate.class))
        .taskId("42")
        .build();
    recordList.add(buildResult);

    // Act
    JobRuns actualFromRecordsLatestFirstResult = JobRuns.fromRecordsLatestFirst(recordList);

    // Assert
    Optional<JobRun> firstRun = actualFromRecordsLatestFirstResult.getFirstRun();
    assertFalse(firstRun.isPresent());
    assertFalse(actualFromRecordsLatestFirstResult.isStarted());
    assertTrue(actualFromRecordsLatestFirstResult.getRunsLatestFirst().isEmpty());
    assertSame(firstRun, actualFromRecordsLatestFirstResult.getLatestRun());
  }

  /**
   * Test {@link JobRuns#fromRecordsLatestFirst(List)}.
   * <p>
   * Method under test: {@link JobRuns#fromRecordsLatestFirst(List)}
   */
  @Test
  @DisplayName("Test fromRecordsLatestFirst(List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"JobRuns JobRuns.fromRecordsLatestFirst(List)"})
  void testFromRecordsLatestFirst2() {
    // Arrange
    ArrayList<JobStatusUpdateRecord> recordList = new ArrayList<>();
    Builder builderResult = JobStatusUpdateRecord.builder();
    JobStatusUpdateRecord buildResult = builderResult
        .expiryDate(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .jobId("42")
        .jobRunId("42")
        .statusUpdate(mock(JobStatusUpdate.class))
        .taskId("42")
        .build();
    recordList.add(buildResult);
    Builder builderResult2 = JobStatusUpdateRecord.builder();
    JobStatusUpdateRecord buildResult2 = builderResult2
        .expiryDate(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .jobId("42")
        .jobRunId("42")
        .statusUpdate(mock(JobStatusUpdate.class))
        .taskId("42")
        .build();
    recordList.add(buildResult2);

    // Act
    JobRuns actualFromRecordsLatestFirstResult = JobRuns.fromRecordsLatestFirst(recordList);

    // Assert
    Optional<JobRun> firstRun = actualFromRecordsLatestFirstResult.getFirstRun();
    assertFalse(firstRun.isPresent());
    assertFalse(actualFromRecordsLatestFirstResult.isStarted());
    assertTrue(actualFromRecordsLatestFirstResult.getRunsLatestFirst().isEmpty());
    assertSame(firstRun, actualFromRecordsLatestFirstResult.getLatestRun());
  }

  /**
   * Test {@link JobRuns#fromRecordsLatestFirst(List)}.
   * <ul>
   *   <li>Then FirstRun FirstUpdate return {@link TestJobRunStatus}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JobRuns#fromRecordsLatestFirst(List)}
   */
  @Test
  @DisplayName("Test fromRecordsLatestFirst(List); then FirstRun FirstUpdate return TestJobRunStatus")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"JobRuns JobRuns.fromRecordsLatestFirst(List)"})
  void testFromRecordsLatestFirst_thenFirstRunFirstUpdateReturnTestJobRunStatus() {
    // Arrange
    ArrayList<JobStatusUpdateRecord> recordList = new ArrayList<>();
    Builder builderResult = JobStatusUpdateRecord.builder();
    Builder jobRunIdResult = builderResult
        .expiryDate(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .jobId("42")
        .jobRunId("42");
    TestJobRunStatus statusUpdate = new TestJobRunStatus(
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    JobStatusUpdateRecord buildResult = jobRunIdResult.statusUpdate(statusUpdate).taskId("42").build();
    recordList.add(buildResult);

    // Act
    JobRuns actualFromRecordsLatestFirstResult = JobRuns.fromRecordsLatestFirst(recordList);

    // Assert
    Optional<JobRun> firstRun = actualFromRecordsLatestFirstResult.getFirstRun();
    JobRun getResult = firstRun.get();
    JobStatusUpdate firstUpdate = getResult.getFirstUpdate();
    assertTrue(firstUpdate instanceof TestJobRunStatus);
    assertEquals("42", getResult.getTaskId());
    assertEquals(1, getResult.getStatusUpdates().size());
    List<JobRun> runsLatestFirst = actualFromRecordsLatestFirstResult.getRunsLatestFirst();
    assertEquals(1, runsLatestFirst.size());
    assertTrue(firstRun.isPresent());
    assertTrue(actualFromRecordsLatestFirstResult.getLatestRun().isPresent());
    assertTrue(actualFromRecordsLatestFirstResult.isStarted());
    assertSame(statusUpdate, firstUpdate);
    assertSame(statusUpdate, getResult.getLatestUpdate());
    assertSame(getResult, runsLatestFirst.get(0));
  }

  /**
   * Test {@link JobRuns#fromRecordsLatestFirst(List)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return not FirstRun Present.</li>
   * </ul>
   * <p>
   * Method under test: {@link JobRuns#fromRecordsLatestFirst(List)}
   */
  @Test
  @DisplayName("Test fromRecordsLatestFirst(List); when ArrayList(); then return not FirstRun Present")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"JobRuns JobRuns.fromRecordsLatestFirst(List)"})
  void testFromRecordsLatestFirst_whenArrayList_thenReturnNotFirstRunPresent() {
    // Arrange and Act
    JobRuns actualFromRecordsLatestFirstResult = JobRuns.fromRecordsLatestFirst(new ArrayList<>());

    // Assert
    Optional<JobRun> firstRun = actualFromRecordsLatestFirstResult.getFirstRun();
    assertFalse(firstRun.isPresent());
    assertFalse(actualFromRecordsLatestFirstResult.isStarted());
    assertTrue(actualFromRecordsLatestFirstResult.getRunsLatestFirst().isEmpty());
    assertSame(firstRun, actualFromRecordsLatestFirstResult.getLatestRun());
  }

  /**
   * Test {@link JobRuns#isStarted()}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add builder taskId {@code 42} build.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JobRuns#isStarted()}
   */
  @Test
  @DisplayName("Test isStarted(); given ArrayList() add builder taskId '42' build; then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean JobRuns.isStarted()"})
  void testIsStarted_givenArrayListAddBuilderTaskId42Build_thenReturnTrue() {
    // Arrange
    ArrayList<JobRun> latestFirst = new ArrayList<>();
    JobRun buildResult = JobRun.builder().taskId("42").build();
    latestFirst.add(buildResult);

    // Act and Assert
    assertTrue(JobRuns.latestFirst(latestFirst).isStarted());
  }

  /**
   * Test {@link JobRuns#isStarted()}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JobRuns#isStarted()}
   */
  @Test
  @DisplayName("Test isStarted(); then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean JobRuns.isStarted()"})
  void testIsStarted_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(JobRuns.latestFirst(new ArrayList<>()).isStarted());
  }

  /**
   * Test {@link JobRuns#isTaskIdAssigned(String)}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add builder taskId {@code 42} build.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JobRuns#isTaskIdAssigned(String)}
   */
  @Test
  @DisplayName("Test isTaskIdAssigned(String); given ArrayList() add builder taskId '42' build; then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean JobRuns.isTaskIdAssigned(String)"})
  void testIsTaskIdAssigned_givenArrayListAddBuilderTaskId42Build_thenReturnTrue() {
    // Arrange
    ArrayList<JobRun> latestFirst = new ArrayList<>();
    JobRun buildResult = JobRun.builder().taskId("42").build();
    latestFirst.add(buildResult);

    // Act and Assert
    assertTrue(JobRuns.latestFirst(latestFirst).isTaskIdAssigned("42"));
  }

  /**
   * Test {@link JobRuns#isTaskIdAssigned(String)}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add builder taskId {@code Task Id} build.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JobRuns#isTaskIdAssigned(String)}
   */
  @Test
  @DisplayName("Test isTaskIdAssigned(String); given ArrayList() add builder taskId 'Task Id' build; then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean JobRuns.isTaskIdAssigned(String)"})
  void testIsTaskIdAssigned_givenArrayListAddBuilderTaskIdTaskIdBuild_thenReturnTrue() {
    // Arrange
    ArrayList<JobRun> latestFirst = new ArrayList<>();
    JobRun buildResult = JobRun.builder().taskId("Task Id").build();
    latestFirst.add(buildResult);
    JobRun buildResult2 = JobRun.builder().taskId("42").build();
    latestFirst.add(buildResult2);

    // Act and Assert
    assertTrue(JobRuns.latestFirst(latestFirst).isTaskIdAssigned("42"));
  }

  /**
   * Test {@link JobRuns#isTaskIdAssigned(String)}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JobRuns#isTaskIdAssigned(String)}
   */
  @Test
  @DisplayName("Test isTaskIdAssigned(String); then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean JobRuns.isTaskIdAssigned(String)"})
  void testIsTaskIdAssigned_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(JobRuns.latestFirst(new ArrayList<>()).isTaskIdAssigned("42"));
  }

  /**
   * Test {@link JobRuns#lastTime()}.
   * <ul>
   *   <li>Then return not Present.</li>
   * </ul>
   * <p>
   * Method under test: {@link JobRuns#lastTime()}
   */
  @Test
  @DisplayName("Test lastTime(); then return not Present")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Optional JobRuns.lastTime()"})
  void testLastTime_thenReturnNotPresent() {
    // Arrange, Act and Assert
    assertFalse(JobRuns.latestFirst(new ArrayList<>()).lastTime().isPresent());
  }

  /**
   * Test {@link JobRuns#firstTime()}.
   * <ul>
   *   <li>Then return not Present.</li>
   * </ul>
   * <p>
   * Method under test: {@link JobRuns#firstTime()}
   */
  @Test
  @DisplayName("Test firstTime(); then return not Present")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Optional JobRuns.firstTime()"})
  void testFirstTime_thenReturnNotPresent() {
    // Arrange, Act and Assert
    assertFalse(JobRuns.latestFirst(new ArrayList<>()).firstTime().isPresent());
  }

  /**
   * Test {@link JobRuns#getLatestRun()}.
   * <p>
   * Method under test: {@link JobRuns#getLatestRun()}
   */
  @Test
  @DisplayName("Test getLatestRun()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Optional JobRuns.getLatestRun()"})
  void testGetLatestRun() {
    // Arrange, Act and Assert
    assertFalse(JobRuns.latestFirst(new ArrayList<>()).getLatestRun().isPresent());
  }

  /**
   * Test {@link JobRuns#getFirstRun()}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add builder taskId {@code 42} build.</li>
   *   <li>Then return {@link Optional#get()} TaskId is {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JobRuns#getFirstRun()}
   */
  @Test
  @DisplayName("Test getFirstRun(); given ArrayList() add builder taskId '42' build; then return get() TaskId is '42'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Optional JobRuns.getFirstRun()"})
  void testGetFirstRun_givenArrayListAddBuilderTaskId42Build_thenReturnGetTaskIdIs42() {
    // Arrange
    ArrayList<JobRun> latestFirst = new ArrayList<>();
    JobRun buildResult = JobRun.builder().taskId("42").build();
    latestFirst.add(buildResult);

    // Act
    Optional<JobRun> actualFirstRun = JobRuns.latestFirst(latestFirst).getFirstRun();

    // Assert
    JobRun getResult = actualFirstRun.get();
    assertEquals("42", getResult.getTaskId());
    assertTrue(getResult.getStatusUpdates().isEmpty());
    assertTrue(actualFirstRun.isPresent());
  }

  /**
   * Test {@link JobRuns#getFirstRun()}.
   * <ul>
   *   <li>Then return not Present.</li>
   * </ul>
   * <p>
   * Method under test: {@link JobRuns#getFirstRun()}
   */
  @Test
  @DisplayName("Test getFirstRun(); then return not Present")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Optional JobRuns.getFirstRun()"})
  void testGetFirstRun_thenReturnNotPresent() {
    // Arrange, Act and Assert
    assertFalse(JobRuns.latestFirst(new ArrayList<>()).getFirstRun().isPresent());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link JobRuns#toString()}
   *   <li>{@link JobRuns#getRunsLatestFirst()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List JobRuns.getRunsLatestFirst()", "String JobRuns.toString()"})
  void testGettersAndSetters() {
    // Arrange
    JobRuns latestFirstResult = JobRuns.latestFirst(new ArrayList<>());

    // Act
    String actualToStringResult = latestFirstResult.toString();

    // Assert
    assertEquals("JobRuns{latestFirst=[]}", actualToStringResult);
    assertTrue(latestFirstResult.getRunsLatestFirst().isEmpty());
  }

  /**
   * Test {@link JobRuns#equals(Object)}, and {@link JobRuns#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link JobRuns#equals(Object)}
   *   <li>{@link JobRuns#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean JobRuns.equals(Object)", "int JobRuns.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    JobRuns latestFirstResult = JobRuns.latestFirst(new ArrayList<>());
    JobRuns latestFirstResult2 = JobRuns.latestFirst(new ArrayList<>());

    // Act and Assert
    assertEquals(latestFirstResult, latestFirstResult2);
    int expectedHashCodeResult = latestFirstResult.hashCode();
    assertEquals(expectedHashCodeResult, latestFirstResult2.hashCode());
  }

  /**
   * Test {@link JobRuns#equals(Object)}, and {@link JobRuns#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link JobRuns#equals(Object)}
   *   <li>{@link JobRuns#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean JobRuns.equals(Object)", "int JobRuns.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    JobRuns latestFirstResult = JobRuns.latestFirst(new ArrayList<>());

    // Act and Assert
    assertEquals(latestFirstResult, latestFirstResult);
    int expectedHashCodeResult = latestFirstResult.hashCode();
    assertEquals(expectedHashCodeResult, latestFirstResult.hashCode());
  }

  /**
   * Test {@link JobRuns#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link JobRuns#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean JobRuns.equals(Object)", "int JobRuns.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    ArrayList<JobRun> latestFirst = new ArrayList<>();
    JobRun buildResult = JobRun.builder().taskId("42").build();
    latestFirst.add(buildResult);
    JobRuns latestFirstResult = JobRuns.latestFirst(latestFirst);

    // Act and Assert
    assertNotEquals(latestFirstResult, JobRuns.latestFirst(new ArrayList<>()));
  }

  /**
   * Test {@link JobRuns#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link JobRuns#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean JobRuns.equals(Object)", "int JobRuns.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(JobRuns.latestFirst(new ArrayList<>()), null);
  }

  /**
   * Test {@link JobRuns#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link JobRuns#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean JobRuns.equals(Object)", "int JobRuns.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(JobRuns.latestFirst(new ArrayList<>()), "Different type to JobRuns");
  }
}
