package sleeper.core.tracker.job.status;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.tracker.job.run.JobRun;
import sleeper.core.tracker.job.run.JobRuns;
import sleeper.core.tracker.job.status.JobStatusUpdateRecord.Builder;

class JobStatusUpdatesDiffblueTest {
  /**
   * Test {@link JobStatusUpdates#from(String, List)}.
   * <ul>
   *   <li>Then return FirstRecord JobId is {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JobStatusUpdates#from(String, List)}
   */
  @Test
  @DisplayName("Test from(String, List); then return FirstRecord JobId is '42'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"JobStatusUpdates JobStatusUpdates.from(String, List)"})
  void testFrom_thenReturnFirstRecordJobIdIs42() {
    // Arrange
    ArrayList<JobStatusUpdateRecord> records = new ArrayList<>();
    Builder builderResult = JobStatusUpdateRecord.builder();
    JobStatusUpdateRecord buildResult = builderResult
        .expiryDate(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .jobId("42")
        .jobRunId("42")
        .statusUpdate(mock(JobStatusUpdate.class))
        .taskId("42")
        .build();
    records.add(buildResult);

    // Act
    JobStatusUpdates actualFromResult = JobStatusUpdates.from("42", records);

    // Assert
    JobStatusUpdateRecord firstRecord = actualFromResult.getFirstRecord();
    assertEquals("42", firstRecord.getJobId());
    assertEquals("42", firstRecord.getJobRunId());
    assertEquals("42", firstRecord.getTaskId());
    assertNull(firstRecord.getUpdateTime());
    Instant expiryDate = firstRecord.getExpiryDate();
    assertEquals(0, expiryDate.getNano());
    assertEquals(0L, expiryDate.getEpochSecond());
    assertSame(firstRecord, actualFromResult.getLastRecord());
  }

  /**
   * Test {@link JobStatusUpdates#from(String, List)}.
   * <ul>
   *   <li>Then return LastRecord JobId is {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JobStatusUpdates#from(String, List)}
   */
  @Test
  @DisplayName("Test from(String, List); then return LastRecord JobId is '42'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"JobStatusUpdates JobStatusUpdates.from(String, List)"})
  void testFrom_thenReturnLastRecordJobIdIs42() {
    // Arrange
    JobStatusUpdate statusUpdate = mock(JobStatusUpdate.class);
    when(statusUpdate.getUpdateTime())
        .thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    Builder builderResult = JobStatusUpdateRecord.builder();
    JobStatusUpdateRecord buildResult = builderResult
        .expiryDate(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .jobId("42")
        .jobRunId("42")
        .statusUpdate(statusUpdate)
        .taskId("42")
        .build();
    JobStatusUpdate statusUpdate2 = mock(JobStatusUpdate.class);
    when(statusUpdate2.getUpdateTime())
        .thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    Builder builderResult2 = JobStatusUpdateRecord.builder();
    JobStatusUpdateRecord buildResult2 = builderResult2
        .expiryDate(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .jobId("42")
        .jobRunId("42")
        .statusUpdate(statusUpdate2)
        .taskId("42")
        .build();

    ArrayList<JobStatusUpdateRecord> records = new ArrayList<>();
    records.add(buildResult2);
    records.add(buildResult);

    // Act
    JobStatusUpdates actualFromResult = JobStatusUpdates.from("42", records);

    // Assert
    verify(statusUpdate2).getUpdateTime();
    verify(statusUpdate).getUpdateTime();
    JobStatusUpdateRecord lastRecord = actualFromResult.getLastRecord();
    assertEquals("42", lastRecord.getJobId());
    assertEquals("42", lastRecord.getJobRunId());
    assertEquals("42", lastRecord.getTaskId());
  }

  /**
   * Test {@link JobStatusUpdates#from(String, List)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return JobId is {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JobStatusUpdates#from(String, List)}
   */
  @Test
  @DisplayName("Test from(String, List); when ArrayList(); then return JobId is '42'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"JobStatusUpdates JobStatusUpdates.from(String, List)"})
  void testFrom_whenArrayList_thenReturnJobIdIs42() {
    // Arrange and Act
    JobStatusUpdates actualFromResult = JobStatusUpdates.from("42", new ArrayList<>());

    // Assert
    assertEquals("42", actualFromResult.getJobId());
    JobRuns runs = actualFromResult.getRuns();
    Optional<JobRun> firstRun = runs.getFirstRun();
    assertFalse(firstRun.isPresent());
    assertFalse(runs.isStarted());
    assertTrue(runs.getRunsLatestFirst().isEmpty());
    assertSame(firstRun, runs.getLatestRun());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link JobStatusUpdates#getJobId()}
   *   <li>{@link JobStatusUpdates#getRuns()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String JobStatusUpdates.getJobId()", "JobRuns JobStatusUpdates.getRuns()"})
  void testGettersAndSetters() {
    // Arrange
    JobStatusUpdates fromResult = JobStatusUpdates.from("42", new ArrayList<>());

    // Act
    String actualJobId = fromResult.getJobId();
    JobRuns actualRuns = fromResult.getRuns();

    // Assert
    assertEquals("42", actualJobId);
    assertFalse(actualRuns.getFirstRun().isPresent());
    assertFalse(actualRuns.isStarted());
    assertTrue(actualRuns.getRunsLatestFirst().isEmpty());
  }

  /**
   * Test {@link JobStatusUpdates#getFirstRecord()}.
   * <ul>
   *   <li>Then return JobId is {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JobStatusUpdates#getFirstRecord()}
   */
  @Test
  @DisplayName("Test getFirstRecord(); then return JobId is '42'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"JobStatusUpdateRecord JobStatusUpdates.getFirstRecord()"})
  void testGetFirstRecord_thenReturnJobIdIs42() {
    // Arrange
    ArrayList<JobStatusUpdateRecord> records = new ArrayList<>();
    Builder builderResult = JobStatusUpdateRecord.builder();
    JobStatusUpdateRecord buildResult = builderResult
        .expiryDate(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .jobId("42")
        .jobRunId("42")
        .statusUpdate(mock(JobStatusUpdate.class))
        .taskId("42")
        .build();
    records.add(buildResult);

    // Act
    JobStatusUpdateRecord actualFirstRecord = JobStatusUpdates.from("42", records).getFirstRecord();

    // Assert
    assertEquals("42", actualFirstRecord.getJobId());
    assertEquals("42", actualFirstRecord.getJobRunId());
    assertEquals("42", actualFirstRecord.getTaskId());
    assertNull(actualFirstRecord.getUpdateTime());
    Instant expiryDate = actualFirstRecord.getExpiryDate();
    assertEquals(0, expiryDate.getNano());
    assertEquals(0L, expiryDate.getEpochSecond());
  }

  /**
   * Test {@link JobStatusUpdates#getLastRecord()}.
   * <ul>
   *   <li>Then return JobId is {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link JobStatusUpdates#getLastRecord()}
   */
  @Test
  @DisplayName("Test getLastRecord(); then return JobId is '42'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"JobStatusUpdateRecord JobStatusUpdates.getLastRecord()"})
  void testGetLastRecord_thenReturnJobIdIs42() {
    // Arrange
    ArrayList<JobStatusUpdateRecord> records = new ArrayList<>();
    Builder builderResult = JobStatusUpdateRecord.builder();
    JobStatusUpdateRecord buildResult = builderResult
        .expiryDate(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .jobId("42")
        .jobRunId("42")
        .statusUpdate(mock(JobStatusUpdate.class))
        .taskId("42")
        .build();
    records.add(buildResult);

    // Act
    JobStatusUpdateRecord actualLastRecord = JobStatusUpdates.from("42", records).getLastRecord();

    // Assert
    assertEquals("42", actualLastRecord.getJobId());
    assertEquals("42", actualLastRecord.getJobRunId());
    assertEquals("42", actualLastRecord.getTaskId());
    assertNull(actualLastRecord.getUpdateTime());
    Instant expiryDate = actualLastRecord.getExpiryDate();
    assertEquals(0, expiryDate.getNano());
    assertEquals(0L, expiryDate.getEpochSecond());
  }

  /**
   * Test {@link JobStatusUpdates#getFirstStatusUpdateOfType(Class)}.
   * <ul>
   *   <li>Then return not Present.</li>
   * </ul>
   * <p>
   * Method under test: {@link JobStatusUpdates#getFirstStatusUpdateOfType(Class)}
   */
  @Test
  @DisplayName("Test getFirstStatusUpdateOfType(Class); then return not Present")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Optional JobStatusUpdates.getFirstStatusUpdateOfType(Class)"})
  void testGetFirstStatusUpdateOfType_thenReturnNotPresent() {
    // Arrange
    JobStatusUpdates fromResult = JobStatusUpdates.from("42", new ArrayList<>());
    Class<JobStatusUpdate> updateType = JobStatusUpdate.class;

    // Act and Assert
    assertFalse(fromResult.getFirstStatusUpdateOfType(updateType).isPresent());
  }

  /**
   * Test {@link JobStatusUpdates#getFirstStatusUpdateOfType(Class)}.
   * <ul>
   *   <li>Then return Present.</li>
   * </ul>
   * <p>
   * Method under test: {@link JobStatusUpdates#getFirstStatusUpdateOfType(Class)}
   */
  @Test
  @DisplayName("Test getFirstStatusUpdateOfType(Class); then return Present")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Optional JobStatusUpdates.getFirstStatusUpdateOfType(Class)"})
  void testGetFirstStatusUpdateOfType_thenReturnPresent() {
    // Arrange
    ArrayList<JobStatusUpdateRecord> records = new ArrayList<>();
    Builder builderResult = JobStatusUpdateRecord.builder();
    JobStatusUpdateRecord buildResult = builderResult
        .expiryDate(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .jobId("42")
        .jobRunId("42")
        .statusUpdate(mock(JobStatusUpdate.class))
        .taskId("42")
        .build();
    records.add(buildResult);
    JobStatusUpdates fromResult = JobStatusUpdates.from("42", records);
    Class<JobStatusUpdate> updateType = JobStatusUpdate.class;

    // Act and Assert
    assertTrue(fromResult.getFirstStatusUpdateOfType(updateType).isPresent());
  }
}
