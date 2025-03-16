package sleeper.clients.status.report.filestatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Collection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.statestore.FileReference;
import sleeper.core.statestore.FileReference.Builder;

class FileRecordsStatsDiffblueTest {
  /**
   * Test {@link FileRecordsStats#from(Collection)}.
   * <ul>
   *   <li>Then return TotalRecordsApprox is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link FileRecordsStats#from(Collection)}
   */
  @Test
  @DisplayName("Test from(Collection); then return TotalRecordsApprox is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"FileRecordsStats FileRecordsStats.from(Collection)"})
  void testFrom_thenReturnTotalRecordsApproxIsOne() {
    // Arrange
    ArrayList<FileReference> references = new ArrayList<>();
    Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference buildResult = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    references.add(buildResult);

    // Act
    FileRecordsStats actualFromResult = FileRecordsStats.from(references);

    // Assert
    assertEquals(0L, actualFromResult.getTotalRecordsKnown());
    assertEquals(1L, actualFromResult.getTotalRecords());
    assertEquals(1L, actualFromResult.getTotalRecordsApprox());
  }

  /**
   * Test {@link FileRecordsStats#from(Collection)}.
   * <ul>
   *   <li>Then return TotalRecords is two.</li>
   * </ul>
   * <p>
   * Method under test: {@link FileRecordsStats#from(Collection)}
   */
  @Test
  @DisplayName("Test from(Collection); then return TotalRecords is two")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"FileRecordsStats FileRecordsStats.from(Collection)"})
  void testFrom_thenReturnTotalRecordsIsTwo() {
    // Arrange
    ArrayList<FileReference> references = new ArrayList<>();
    Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference buildResult = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    references.add(buildResult);
    Builder jobIdResult2 = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference buildResult2 = jobIdResult2
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    references.add(buildResult2);

    // Act
    FileRecordsStats actualFromResult = FileRecordsStats.from(references);

    // Assert
    assertEquals(0L, actualFromResult.getTotalRecordsKnown());
    assertEquals(2L, actualFromResult.getTotalRecords());
    assertEquals(2L, actualFromResult.getTotalRecordsApprox());
  }

  /**
   * Test {@link FileRecordsStats#from(Collection)}.
   * <ul>
   *   <li>Then return TotalRecordsKnown is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link FileRecordsStats#from(Collection)}
   */
  @Test
  @DisplayName("Test from(Collection); then return TotalRecordsKnown is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"FileRecordsStats FileRecordsStats.from(Collection)"})
  void testFrom_thenReturnTotalRecordsKnownIsOne() {
    // Arrange
    ArrayList<FileReference> references = new ArrayList<>();
    Builder jobIdResult = FileReference.builder().countApproximate(false).filename("foo.txt").jobId("42");
    FileReference buildResult = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    references.add(buildResult);

    // Act
    FileRecordsStats actualFromResult = FileRecordsStats.from(references);

    // Assert
    assertEquals(0L, actualFromResult.getTotalRecordsApprox());
    assertEquals(1L, actualFromResult.getTotalRecords());
    assertEquals(1L, actualFromResult.getTotalRecordsKnown());
  }

  /**
   * Test {@link FileRecordsStats#from(Collection)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return TotalRecords is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link FileRecordsStats#from(Collection)}
   */
  @Test
  @DisplayName("Test from(Collection); when ArrayList(); then return TotalRecords is zero")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"FileRecordsStats FileRecordsStats.from(Collection)"})
  void testFrom_whenArrayList_thenReturnTotalRecordsIsZero() {
    // Arrange and Act
    FileRecordsStats actualFromResult = FileRecordsStats.from(new ArrayList<>());

    // Assert
    assertEquals(0L, actualFromResult.getTotalRecords());
    assertEquals(0L, actualFromResult.getTotalRecordsApprox());
    assertEquals(0L, actualFromResult.getTotalRecordsKnown());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link FileRecordsStats#getTotalRecords()}
   *   <li>{@link FileRecordsStats#getTotalRecordsApprox()}
   *   <li>{@link FileRecordsStats#getTotalRecordsKnown()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"long FileRecordsStats.getTotalRecords()", "long FileRecordsStats.getTotalRecordsApprox()",
      "long FileRecordsStats.getTotalRecordsKnown()"})
  void testGettersAndSetters() {
    // Arrange
    FileRecordsStats fromResult = FileRecordsStats.from(new ArrayList<>());

    // Act
    long actualTotalRecords = fromResult.getTotalRecords();
    long actualTotalRecordsApprox = fromResult.getTotalRecordsApprox();

    // Assert
    assertEquals(0L, actualTotalRecords);
    assertEquals(0L, actualTotalRecordsApprox);
    assertEquals(0L, fromResult.getTotalRecordsKnown());
  }
}
