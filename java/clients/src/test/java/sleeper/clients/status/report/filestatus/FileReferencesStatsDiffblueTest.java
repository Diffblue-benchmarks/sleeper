package sleeper.clients.status.report.filestatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
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

class FileReferencesStatsDiffblueTest {
  /**
   * Test {@link FileReferencesStats#from(Collection)}.
   * <ul>
   *   <li>Then return AverageReferences doubleValue is {@code 1.5}.</li>
   * </ul>
   * <p>
   * Method under test: {@link FileReferencesStats#from(Collection)}
   */
  @Test
  @DisplayName("Test from(Collection); then return AverageReferences doubleValue is '1.5'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"FileReferencesStats FileReferencesStats.from(Collection)"})
  void testFrom_thenReturnAverageReferencesDoubleValueIs15() {
    // Arrange
    ArrayList<FileReference> references = new ArrayList<>();
    Builder jobIdResult = FileReference.builder().countApproximate(true).filename("Filename").jobId("42");
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
        .partitionId("Partition Id")
        .build();
    references.add(buildResult2);
    Builder jobIdResult3 = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference buildResult3 = jobIdResult3
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    references.add(buildResult3);

    // Act
    FileReferencesStats actualFromResult = FileReferencesStats.from(references);

    // Assert
    assertEquals(1, actualFromResult.getMinReferences().intValue());
    assertEquals(1.5d, actualFromResult.getAverageReferences().doubleValue());
    assertEquals(2, actualFromResult.getMaxReferences().intValue());
    assertEquals(3, actualFromResult.getTotalReferences().intValue());
  }

  /**
   * Test {@link FileReferencesStats#from(Collection)}.
   * <ul>
   *   <li>Then return AverageReferences doubleValue is {@code 1.3333333333333333}.</li>
   * </ul>
   * <p>
   * Method under test: {@link FileReferencesStats#from(Collection)}
   */
  @Test
  @DisplayName("Test from(Collection); then return AverageReferences doubleValue is '1.3333333333333333'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"FileReferencesStats FileReferencesStats.from(Collection)"})
  void testFrom_thenReturnAverageReferencesDoubleValueIs13333333333333333() {
    // Arrange
    ArrayList<FileReference> references = new ArrayList<>();
    Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference buildResult = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("")
        .build();
    references.add(buildResult);
    Builder jobIdResult2 = FileReference.builder().countApproximate(true).filename("Filename").jobId("42");
    FileReference buildResult2 = jobIdResult2
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    references.add(buildResult2);
    Builder jobIdResult3 = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference buildResult3 = jobIdResult3
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("Partition Id")
        .build();
    references.add(buildResult3);
    Builder jobIdResult4 = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference buildResult4 = jobIdResult4
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    references.add(buildResult4);

    // Act
    FileReferencesStats actualFromResult = FileReferencesStats.from(references);

    // Assert
    assertEquals(1, actualFromResult.getMinReferences().intValue());
    assertEquals(1.3333333333333333d, actualFromResult.getAverageReferences().doubleValue());
    assertEquals(2, actualFromResult.getMaxReferences().intValue());
    assertEquals(4, actualFromResult.getTotalReferences().intValue());
  }

  /**
   * Test {@link FileReferencesStats#from(Collection)}.
   * <ul>
   *   <li>Then return TotalReferences intValue is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link FileReferencesStats#from(Collection)}
   */
  @Test
  @DisplayName("Test from(Collection); then return TotalReferences intValue is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"FileReferencesStats FileReferencesStats.from(Collection)"})
  void testFrom_thenReturnTotalReferencesIntValueIsOne() {
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
    FileReferencesStats actualFromResult = FileReferencesStats.from(references);

    // Assert
    assertEquals(1, actualFromResult.getMaxReferences().intValue());
    assertEquals(1, actualFromResult.getMinReferences().intValue());
    assertEquals(1, actualFromResult.getTotalReferences().intValue());
    assertEquals(1.0d, actualFromResult.getAverageReferences().doubleValue());
  }

  /**
   * Test {@link FileReferencesStats#from(Collection)}.
   * <ul>
   *   <li>Then return TotalReferences intValue is two.</li>
   * </ul>
   * <p>
   * Method under test: {@link FileReferencesStats#from(Collection)}
   */
  @Test
  @DisplayName("Test from(Collection); then return TotalReferences intValue is two")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"FileReferencesStats FileReferencesStats.from(Collection)"})
  void testFrom_thenReturnTotalReferencesIntValueIsTwo() {
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
    FileReferencesStats actualFromResult = FileReferencesStats.from(references);

    // Assert
    assertEquals(1, actualFromResult.getMaxReferences().intValue());
    assertEquals(1, actualFromResult.getMinReferences().intValue());
    assertEquals(1.0d, actualFromResult.getAverageReferences().doubleValue());
    assertEquals(2, actualFromResult.getTotalReferences().intValue());
  }

  /**
   * Test {@link FileReferencesStats#from(Collection)}.
   * <ul>
   *   <li>Then return TotalReferences intValue is two.</li>
   * </ul>
   * <p>
   * Method under test: {@link FileReferencesStats#from(Collection)}
   */
  @Test
  @DisplayName("Test from(Collection); then return TotalReferences intValue is two")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"FileReferencesStats FileReferencesStats.from(Collection)"})
  void testFrom_thenReturnTotalReferencesIntValueIsTwo2() {
    // Arrange
    ArrayList<FileReference> references = new ArrayList<>();
    Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference buildResult = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("Partition Id")
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
    FileReferencesStats actualFromResult = FileReferencesStats.from(references);

    // Assert
    assertEquals(1, actualFromResult.getMaxReferences().intValue());
    assertEquals(1, actualFromResult.getMinReferences().intValue());
    assertEquals(1.0d, actualFromResult.getAverageReferences().doubleValue());
    assertEquals(2, actualFromResult.getTotalReferences().intValue());
  }

  /**
   * Test {@link FileReferencesStats#from(Collection)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return AverageReferences is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link FileReferencesStats#from(Collection)}
   */
  @Test
  @DisplayName("Test from(Collection); when ArrayList(); then return AverageReferences is 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"FileReferencesStats FileReferencesStats.from(Collection)"})
  void testFrom_whenArrayList_thenReturnAverageReferencesIsNull() {
    // Arrange and Act
    FileReferencesStats actualFromResult = FileReferencesStats.from(new ArrayList<>());

    // Assert
    assertNull(actualFromResult.getAverageReferences());
    assertNull(actualFromResult.getMaxReferences());
    assertNull(actualFromResult.getMinReferences());
    assertEquals(0, actualFromResult.getTotalReferences().intValue());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link FileReferencesStats#getAverageReferences()}
   *   <li>{@link FileReferencesStats#getMaxReferences()}
   *   <li>{@link FileReferencesStats#getMinReferences()}
   *   <li>{@link FileReferencesStats#getTotalReferences()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Double FileReferencesStats.getAverageReferences()",
      "Integer FileReferencesStats.getMaxReferences()", "Integer FileReferencesStats.getMinReferences()",
      "Integer FileReferencesStats.getTotalReferences()"})
  void testGettersAndSetters() {
    // Arrange
    FileReferencesStats fromResult = FileReferencesStats.from(new ArrayList<>());

    // Act
    Double actualAverageReferences = fromResult.getAverageReferences();
    Integer actualMaxReferences = fromResult.getMaxReferences();
    Integer actualMinReferences = fromResult.getMinReferences();

    // Assert
    assertNull(actualAverageReferences);
    assertNull(actualMaxReferences);
    assertNull(actualMinReferences);
    assertEquals(0, fromResult.getTotalReferences().intValue());
  }
}
