package sleeper.core.tracker.ingest.job.query;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class IngestJobStatusTypeDiffblueTest {
  /**
   * Test {@link IngestJobStatusType#isRunInProgress()}.
   * <ul>
   *   <li>Given {@link IngestJobStatusType#ACCEPTED}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobStatusType#isRunInProgress()}
   */
  @Test
  @DisplayName("Test isRunInProgress(); given ACCEPTED; then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestJobStatusType.isRunInProgress()"})
  void testIsRunInProgress_givenAccepted_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(IngestJobStatusType.ACCEPTED.isRunInProgress());
  }

  /**
   * Test {@link IngestJobStatusType#isRunInProgress()}.
   * <ul>
   *   <li>Given {@link IngestJobStatusType#IN_PROGRESS}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobStatusType#isRunInProgress()}
   */
  @Test
  @DisplayName("Test isRunInProgress(); given IN_PROGRESS; then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestJobStatusType.isRunInProgress()"})
  void testIsRunInProgress_givenIn_progress_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(IngestJobStatusType.IN_PROGRESS.isRunInProgress());
  }

  /**
   * Test {@link IngestJobStatusType#isRunInProgress()}.
   * <ul>
   *   <li>Given {@code REJECTED}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobStatusType#isRunInProgress()}
   */
  @Test
  @DisplayName("Test isRunInProgress(); given 'REJECTED'; then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestJobStatusType.isRunInProgress()"})
  void testIsRunInProgress_givenRejected_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(IngestJobStatusType.REJECTED.isRunInProgress());
  }

  /**
   * Test {@link IngestJobStatusType#isEndOfJob()}.
   * <ul>
   *   <li>Given {@code ACCEPTED}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobStatusType#isEndOfJob()}
   */
  @Test
  @DisplayName("Test isEndOfJob(); given 'ACCEPTED'; then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestJobStatusType.isEndOfJob()"})
  void testIsEndOfJob_givenAccepted_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(IngestJobStatusType.ACCEPTED.isEndOfJob());
  }

  /**
   * Test {@link IngestJobStatusType#isEndOfJob()}.
   * <ul>
   *   <li>Given {@link IngestJobStatusType#FINISHED}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobStatusType#isEndOfJob()}
   */
  @Test
  @DisplayName("Test isEndOfJob(); given FINISHED; then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestJobStatusType.isEndOfJob()"})
  void testIsEndOfJob_givenFinished_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(IngestJobStatusType.FINISHED.isEndOfJob());
  }

  /**
   * Test {@link IngestJobStatusType#isEndOfJob()}.
   * <ul>
   *   <li>Given {@code REJECTED}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobStatusType#isEndOfJob()}
   */
  @Test
  @DisplayName("Test isEndOfJob(); given 'REJECTED'; then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestJobStatusType.isEndOfJob()"})
  void testIsEndOfJob_givenRejected_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(IngestJobStatusType.REJECTED.isEndOfJob());
  }

  /**
   * Test {@link IngestJobStatusType#statusTypeOfFurthestRunOfJob(Collection)}.
   * <ul>
   *   <li>Given {@code ACCEPTED}.</li>
   *   <li>Then return {@code ACCEPTED}.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobStatusType#statusTypeOfFurthestRunOfJob(Collection)}
   */
  @Test
  @DisplayName("Test statusTypeOfFurthestRunOfJob(Collection); given 'ACCEPTED'; then return 'ACCEPTED'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"IngestJobStatusType IngestJobStatusType.statusTypeOfFurthestRunOfJob(Collection)"})
  void testStatusTypeOfFurthestRunOfJob_givenAccepted_thenReturnAccepted() {
    // Arrange
    ArrayList<IngestJobStatusType> runStatusTypes = new ArrayList<>();
    runStatusTypes.add(IngestJobStatusType.ACCEPTED);
    runStatusTypes.add(IngestJobStatusType.REJECTED);

    // Act and Assert
    assertEquals(IngestJobStatusType.ACCEPTED, IngestJobStatusType.statusTypeOfFurthestRunOfJob(runStatusTypes));
  }

  /**
   * Test {@link IngestJobStatusType#statusTypeOfFurthestRunOfJob(Collection)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobStatusType#statusTypeOfFurthestRunOfJob(Collection)}
   */
  @Test
  @DisplayName("Test statusTypeOfFurthestRunOfJob(Collection); when ArrayList(); then return 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"IngestJobStatusType IngestJobStatusType.statusTypeOfFurthestRunOfJob(Collection)"})
  void testStatusTypeOfFurthestRunOfJob_whenArrayList_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(IngestJobStatusType.statusTypeOfFurthestRunOfJob(new ArrayList<>()));
  }

  /**
   * Test {@link IngestJobStatusType#statusTypeOfFurthestRunOfJob(Collection)}.
   * <ul>
   *   <li>When {@link LinkedHashSet#LinkedHashSet()} add {@code REJECTED}.</li>
   *   <li>Then return {@code REJECTED}.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobStatusType#statusTypeOfFurthestRunOfJob(Collection)}
   */
  @Test
  @DisplayName("Test statusTypeOfFurthestRunOfJob(Collection); when LinkedHashSet() add 'REJECTED'; then return 'REJECTED'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"IngestJobStatusType IngestJobStatusType.statusTypeOfFurthestRunOfJob(Collection)"})
  void testStatusTypeOfFurthestRunOfJob_whenLinkedHashSetAddRejected_thenReturnRejected() {
    // Arrange
    LinkedHashSet<IngestJobStatusType> runStatusTypes = new LinkedHashSet<>();
    runStatusTypes.add(IngestJobStatusType.REJECTED);

    // Act and Assert
    assertEquals(IngestJobStatusType.REJECTED, IngestJobStatusType.statusTypeOfFurthestRunOfJob(runStatusTypes));
  }
}
