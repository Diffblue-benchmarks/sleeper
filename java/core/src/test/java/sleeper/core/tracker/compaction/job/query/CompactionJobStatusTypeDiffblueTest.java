package sleeper.core.tracker.compaction.job.query;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class CompactionJobStatusTypeDiffblueTest {
  /**
   * Test {@link CompactionJobStatusType#furthestStatusTypeOfJob(Collection)}.
   * <ul>
   *   <li>Given {@code FAILED}.</li>
   *   <li>When {@link ArrayList#ArrayList()} add {@code FAILED}.</li>
   *   <li>Then return {@code FAILED}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobStatusType#furthestStatusTypeOfJob(Collection)}
   */
  @Test
  @DisplayName("Test furthestStatusTypeOfJob(Collection); given 'FAILED'; when ArrayList() add 'FAILED'; then return 'FAILED'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"CompactionJobStatusType CompactionJobStatusType.furthestStatusTypeOfJob(Collection)"})
  void testFurthestStatusTypeOfJob_givenFailed_whenArrayListAddFailed_thenReturnFailed() {
    // Arrange
    ArrayList<CompactionJobStatusType> runStatusTypes = new ArrayList<>();
    runStatusTypes.add(CompactionJobStatusType.FAILED);
    runStatusTypes.add(CompactionJobStatusType.CREATED);

    // Act and Assert
    assertEquals(CompactionJobStatusType.FAILED, CompactionJobStatusType.furthestStatusTypeOfJob(runStatusTypes));
  }

  /**
   * Test {@link CompactionJobStatusType#furthestStatusTypeOfJob(Collection)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return {@code CREATED}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobStatusType#furthestStatusTypeOfJob(Collection)}
   */
  @Test
  @DisplayName("Test furthestStatusTypeOfJob(Collection); when ArrayList(); then return 'CREATED'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"CompactionJobStatusType CompactionJobStatusType.furthestStatusTypeOfJob(Collection)"})
  void testFurthestStatusTypeOfJob_whenArrayList_thenReturnCreated() {
    // Arrange, Act and Assert
    assertEquals(CompactionJobStatusType.CREATED, CompactionJobStatusType.furthestStatusTypeOfJob(new ArrayList<>()));
  }

  /**
   * Test {@link CompactionJobStatusType#furthestStatusTypeOfJob(Collection)}.
   * <ul>
   *   <li>When {@link LinkedHashSet#LinkedHashSet()} add {@code CREATED}.</li>
   *   <li>Then return {@code CREATED}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobStatusType#furthestStatusTypeOfJob(Collection)}
   */
  @Test
  @DisplayName("Test furthestStatusTypeOfJob(Collection); when LinkedHashSet() add 'CREATED'; then return 'CREATED'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"CompactionJobStatusType CompactionJobStatusType.furthestStatusTypeOfJob(Collection)"})
  void testFurthestStatusTypeOfJob_whenLinkedHashSetAddCreated_thenReturnCreated() {
    // Arrange
    LinkedHashSet<CompactionJobStatusType> runStatusTypes = new LinkedHashSet<>();
    runStatusTypes.add(CompactionJobStatusType.CREATED);

    // Act and Assert
    assertEquals(CompactionJobStatusType.CREATED, CompactionJobStatusType.furthestStatusTypeOfJob(runStatusTypes));
  }
}
