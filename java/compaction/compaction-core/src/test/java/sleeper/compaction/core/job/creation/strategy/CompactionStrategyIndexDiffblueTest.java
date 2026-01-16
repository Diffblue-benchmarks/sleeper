package sleeper.compaction.core.job.creation.strategy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.compaction.core.job.creation.strategy.CompactionStrategyIndex.FilesInPartition;
import sleeper.core.partition.Partition;
import sleeper.core.partition.Partition.Builder;
import sleeper.core.range.Region;
import sleeper.core.statestore.FileReference;
import sleeper.core.table.TableStatus;

class CompactionStrategyIndexDiffblueTest {
  /**
   * Test FilesInPartition {@link FilesInPartition#equals(Object)}, and {@link
   * FilesInPartition#hashCode()}.
   *
   * <ul>
   *   <li>When other is equal.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link FilesInPartition#equals(Object)}
   *   <li>{@link FilesInPartition#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName(
      "Test FilesInPartition equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean FilesInPartition.equals(Object)", "int FilesInPartition.hashCode()"})
  void testFilesInPartitionEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    TableStatus tableStatus = TableStatus.uniqueIdAndName("42", "Table Name", true);
    ArrayList<FileReference> filesWithNoJobIdInAscendingOrder = new ArrayList<>();

    FilesInPartition filesInPartition =
        new FilesInPartition(
            tableStatus, "42", filesWithNoJobIdInAscendingOrder, new ArrayList<>());
    TableStatus tableStatus2 = TableStatus.uniqueIdAndName("42", "Table Name", true);
    ArrayList<FileReference> filesWithNoJobIdInAscendingOrder2 = new ArrayList<>();

    FilesInPartition filesInPartition2 =
        new FilesInPartition(
            tableStatus2, "42", filesWithNoJobIdInAscendingOrder2, new ArrayList<>());

    // Act and Assert
    assertEquals(filesInPartition, filesInPartition2);
    assertEquals(filesInPartition.hashCode(), filesInPartition2.hashCode());
  }

  /**
   * Test FilesInPartition {@link FilesInPartition#equals(Object)}, and {@link
   * FilesInPartition#hashCode()}.
   *
   * <ul>
   *   <li>When other is same.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link FilesInPartition#equals(Object)}
   *   <li>{@link FilesInPartition#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName(
      "Test FilesInPartition equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean FilesInPartition.equals(Object)", "int FilesInPartition.hashCode()"})
  void testFilesInPartitionEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    TableStatus tableStatus = TableStatus.uniqueIdAndName("42", "Table Name", true);
    ArrayList<FileReference> filesWithNoJobIdInAscendingOrder = new ArrayList<>();

    FilesInPartition filesInPartition =
        new FilesInPartition(
            tableStatus, "42", filesWithNoJobIdInAscendingOrder, new ArrayList<>());

    // Act and Assert
    assertEquals(filesInPartition, filesInPartition);
    int expectedHashCodeResult = filesInPartition.hashCode();
    assertEquals(expectedHashCodeResult, filesInPartition.hashCode());
  }

  /**
   * Test FilesInPartition {@link FilesInPartition#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link FilesInPartition#equals(Object)}
   */
  @Test
  @DisplayName(
      "Test FilesInPartition equals(Object); when other is different; then return not equal")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean FilesInPartition.equals(Object)", "int FilesInPartition.hashCode()"})
  void testFilesInPartitionEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    ArrayList<FileReference> filesWithNoJobIdInAscendingOrder = new ArrayList<>();
    FilesInPartition filesInPartition =
        new FilesInPartition(null, "42", filesWithNoJobIdInAscendingOrder, new ArrayList<>());
    TableStatus tableStatus = TableStatus.uniqueIdAndName("42", "Table Name", true);
    ArrayList<FileReference> filesWithNoJobIdInAscendingOrder2 = new ArrayList<>();

    // Act and Assert
    assertNotEquals(
        filesInPartition,
        new FilesInPartition(
            tableStatus, "42", filesWithNoJobIdInAscendingOrder2, new ArrayList<>()));
  }

  /**
   * Test FilesInPartition {@link FilesInPartition#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link FilesInPartition#equals(Object)}
   */
  @Test
  @DisplayName(
      "Test FilesInPartition equals(Object); when other is different; then return not equal")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean FilesInPartition.equals(Object)", "int FilesInPartition.hashCode()"})
  void testFilesInPartitionEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    TableStatus tableStatus = TableStatus.uniqueIdAndName("42", "Table Name", true);
    ArrayList<FileReference> filesWithNoJobIdInAscendingOrder = new ArrayList<>();

    FilesInPartition filesInPartition =
        new FilesInPartition(
            tableStatus, "Partition Id", filesWithNoJobIdInAscendingOrder, new ArrayList<>());
    TableStatus tableStatus2 = TableStatus.uniqueIdAndName("42", "Table Name", true);
    ArrayList<FileReference> filesWithNoJobIdInAscendingOrder2 = new ArrayList<>();

    // Act and Assert
    assertNotEquals(
        filesInPartition,
        new FilesInPartition(
            tableStatus2, "42", filesWithNoJobIdInAscendingOrder2, new ArrayList<>()));
  }

  /**
   * Test FilesInPartition {@link FilesInPartition#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link FilesInPartition#equals(Object)}
   */
  @Test
  @DisplayName(
      "Test FilesInPartition equals(Object); when other is different; then return not equal")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean FilesInPartition.equals(Object)", "int FilesInPartition.hashCode()"})
  void testFilesInPartitionEquals_whenOtherIsDifferent_thenReturnNotEqual3() {
    // Arrange
    ArrayList<FileReference> filesWithNoJobIdInAscendingOrder = new ArrayList<>();
    filesWithNoJobIdInAscendingOrder.add(
        FileReference.builder()
            .countApproximate(true)
            .filename("foo.txt")
            .jobId("42")
            .lastStateStoreUpdateTime(
                LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
            .numberOfRecords(1L)
            .onlyContainsDataForThisPartition(true)
            .partitionId("42")
            .build());
    TableStatus tableStatus = TableStatus.uniqueIdAndName("42", "Table Name", true);

    FilesInPartition filesInPartition =
        new FilesInPartition(
            tableStatus, "42", filesWithNoJobIdInAscendingOrder, new ArrayList<>());
    TableStatus tableStatus2 = TableStatus.uniqueIdAndName("42", "Table Name", true);
    ArrayList<FileReference> filesWithNoJobIdInAscendingOrder2 = new ArrayList<>();

    // Act and Assert
    assertNotEquals(
        filesInPartition,
        new FilesInPartition(
            tableStatus2, "42", filesWithNoJobIdInAscendingOrder2, new ArrayList<>()));
  }

  /**
   * Test FilesInPartition {@link FilesInPartition#equals(Object)}.
   *
   * <ul>
   *   <li>When other is {@code null}.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link FilesInPartition#equals(Object)}
   */
  @Test
  @DisplayName("Test FilesInPartition equals(Object); when other is 'null'; then return not equal")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean FilesInPartition.equals(Object)", "int FilesInPartition.hashCode()"})
  void testFilesInPartitionEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange
    TableStatus tableStatus = TableStatus.uniqueIdAndName("42", "Table Name", true);
    ArrayList<FileReference> filesWithNoJobIdInAscendingOrder = new ArrayList<>();

    // Act and Assert
    assertNotEquals(
        new FilesInPartition(
            tableStatus, "42", filesWithNoJobIdInAscendingOrder, new ArrayList<>()),
        null);
  }

  /**
   * Test FilesInPartition {@link FilesInPartition#equals(Object)}.
   *
   * <ul>
   *   <li>When other is wrong type.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link FilesInPartition#equals(Object)}
   */
  @Test
  @DisplayName(
      "Test FilesInPartition equals(Object); when other is wrong type; then return not equal")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean FilesInPartition.equals(Object)", "int FilesInPartition.hashCode()"})
  void testFilesInPartitionEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange
    TableStatus tableStatus = TableStatus.uniqueIdAndName("42", "Table Name", true);
    ArrayList<FileReference> filesWithNoJobIdInAscendingOrder = new ArrayList<>();

    // Act and Assert
    assertNotEquals(
        new FilesInPartition(
            tableStatus, "42", filesWithNoJobIdInAscendingOrder, new ArrayList<>()),
        "Different type to FilesInPartition");
  }

  /**
   * Test FilesInPartition getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link FilesInPartition#FilesInPartition(TableStatus, String, List, List)}
   *   <li>{@link FilesInPartition#toString()}
   *   <li>{@link FilesInPartition#getFilesWithJobId()}
   *   <li>{@link FilesInPartition#getFilesWithNoJobIdInAscendingOrder()}
   *   <li>{@link FilesInPartition#getPartitionId()}
   *   <li>{@link FilesInPartition#getTableStatus()}
   * </ul>
   */
  @Test
  @DisplayName("Test FilesInPartition getters and setters")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void FilesInPartition.<init>(TableStatus, String, List, List)",
    "List FilesInPartition.getFilesWithJobId()",
    "List FilesInPartition.getFilesWithNoJobIdInAscendingOrder()",
    "String FilesInPartition.getPartitionId()",
    "TableStatus FilesInPartition.getTableStatus()",
    "String FilesInPartition.toString()"
  })
  void testFilesInPartitionGettersAndSetters() {
    // Arrange
    TableStatus tableStatus = TableStatus.uniqueIdAndName("42", "Table Name", true);
    ArrayList<FileReference> filesWithNoJobIdInAscendingOrder = new ArrayList<>();
    ArrayList<FileReference> filesWithJobId = new ArrayList<>();

    // Act
    FilesInPartition actualFilesInPartition =
        new FilesInPartition(tableStatus, "42", filesWithNoJobIdInAscendingOrder, filesWithJobId);
    String actualToStringResult = actualFilesInPartition.toString();
    List<FileReference> actualFilesWithJobId = actualFilesInPartition.getFilesWithJobId();
    List<FileReference> actualFilesWithNoJobIdInAscendingOrder =
        actualFilesInPartition.getFilesWithNoJobIdInAscendingOrder();
    String actualPartitionId = actualFilesInPartition.getPartitionId();
    TableStatus actualTableStatus = actualFilesInPartition.getTableStatus();

    // Assert
    assertEquals("42", actualPartitionId);
    assertEquals(
        "FilesInPartition{filesWithJobId=[], filesWithNoJobIdInAscendingOrder=[], partitionId=42, tableStatus=Table"
            + " Name (42)}",
        actualToStringResult);
    assertTrue(actualFilesWithJobId.isEmpty());
    assertTrue(actualFilesWithNoJobIdInAscendingOrder.isEmpty());
    assertSame(filesWithJobId, actualFilesWithJobId);
    assertSame(filesWithNoJobIdInAscendingOrder, actualFilesWithNoJobIdInAscendingOrder);
    assertSame(tableStatus, actualTableStatus);
  }

  /**
   * Test {@link CompactionStrategyIndex#CompactionStrategyIndex(TableStatus, List, List)}.
   *
   * <p>Method under test: {@link CompactionStrategyIndex#CompactionStrategyIndex(TableStatus, List,
   * List)}
   */
  @Test
  @DisplayName("Test new CompactionStrategyIndex(TableStatus, List, List)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void CompactionStrategyIndex.<init>(TableStatus, List, List)"})
  void testNewCompactionStrategyIndex() {
    // Arrange
    TableStatus tableStatus = TableStatus.uniqueIdAndName("42", "Table Name", true);

    ArrayList<FileReference> allFileReferences = new ArrayList<>();
    allFileReferences.add(
        FileReference.builder()
            .countApproximate(true)
            .filename("foo.txt")
            .jobId("42")
            .lastStateStoreUpdateTime(
                LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
            .numberOfRecords(1L)
            .onlyContainsDataForThisPartition(true)
            .partitionId("42")
            .build());

    ArrayList<Partition> allPartitions = new ArrayList<>();

    Builder builderResult = Partition.builder();

    Builder parentPartitionIdResult =
        builderResult
            .childPartitionIds(new ArrayList<>())
            .dimension(1)
            .id("42")
            .leafPartition(true)
            .parentPartitionId("42");
    allPartitions.add(parentPartitionIdResult.region(new Region(new ArrayList<>())).build());

    // Act
    CompactionStrategyIndex actualCompactionStrategyIndex =
        new CompactionStrategyIndex(tableStatus, allFileReferences, allPartitions);

    // Assert
    assertTrue(actualCompactionStrategyIndex.getFilesInLeafPartitions().isEmpty());
  }

  /**
   * Test {@link CompactionStrategyIndex#CompactionStrategyIndex(TableStatus, List, List)}.
   *
   * <p>Method under test: {@link CompactionStrategyIndex#CompactionStrategyIndex(TableStatus, List,
   * List)}
   */
  @Test
  @DisplayName("Test new CompactionStrategyIndex(TableStatus, List, List)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void CompactionStrategyIndex.<init>(TableStatus, List, List)"})
  void testNewCompactionStrategyIndex2() {
    // Arrange
    TableStatus tableStatus = TableStatus.uniqueIdAndName("42", "Table Name", true);

    ArrayList<FileReference> allFileReferences = new ArrayList<>();
    allFileReferences.add(
        FileReference.builder()
            .countApproximate(true)
            .filename("foo.txt")
            .jobId("42")
            .lastStateStoreUpdateTime(
                LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
            .numberOfRecords(1L)
            .onlyContainsDataForThisPartition(true)
            .partitionId("42")
            .build());
    allFileReferences.add(
        FileReference.builder()
            .countApproximate(true)
            .filename("foo.txt")
            .jobId("42")
            .lastStateStoreUpdateTime(
                LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
            .numberOfRecords(1L)
            .onlyContainsDataForThisPartition(true)
            .partitionId("42")
            .build());

    ArrayList<Partition> allPartitions = new ArrayList<>();

    Builder builderResult = Partition.builder();

    Builder parentPartitionIdResult =
        builderResult
            .childPartitionIds(new ArrayList<>())
            .dimension(1)
            .id("42")
            .leafPartition(true)
            .parentPartitionId("42");
    allPartitions.add(parentPartitionIdResult.region(new Region(new ArrayList<>())).build());

    // Act
    CompactionStrategyIndex actualCompactionStrategyIndex =
        new CompactionStrategyIndex(tableStatus, allFileReferences, allPartitions);

    // Assert
    assertTrue(actualCompactionStrategyIndex.getFilesInLeafPartitions().isEmpty());
  }

  /**
   * Test {@link CompactionStrategyIndex#CompactionStrategyIndex(TableStatus, List, List)}.
   *
   * <p>Method under test: {@link CompactionStrategyIndex#CompactionStrategyIndex(TableStatus, List,
   * List)}
   */
  @Test
  @DisplayName("Test new CompactionStrategyIndex(TableStatus, List, List)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void CompactionStrategyIndex.<init>(TableStatus, List, List)"})
  void testNewCompactionStrategyIndex3() {
    // Arrange
    TableStatus tableStatus = TableStatus.uniqueIdAndName("42", "Table Name", true);

    ArrayList<FileReference> allFileReferences = new ArrayList<>();
    allFileReferences.add(
        FileReference.builder()
            .countApproximate(true)
            .filename("foo.txt")
            .jobId(null)
            .lastStateStoreUpdateTime(
                LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
            .numberOfRecords(1L)
            .onlyContainsDataForThisPartition(true)
            .partitionId("42")
            .build());
    allFileReferences.add(
        FileReference.builder()
            .countApproximate(true)
            .filename("foo.txt")
            .jobId("42")
            .lastStateStoreUpdateTime(
                LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
            .numberOfRecords(1L)
            .onlyContainsDataForThisPartition(true)
            .partitionId("42")
            .build());
    allFileReferences.add(
        FileReference.builder()
            .countApproximate(true)
            .filename("foo.txt")
            .jobId("42")
            .lastStateStoreUpdateTime(
                LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
            .numberOfRecords(1L)
            .onlyContainsDataForThisPartition(true)
            .partitionId("42")
            .build());

    ArrayList<Partition> allPartitions = new ArrayList<>();

    Builder builderResult = Partition.builder();

    Builder parentPartitionIdResult =
        builderResult
            .childPartitionIds(new ArrayList<>())
            .dimension(1)
            .id("42")
            .leafPartition(true)
            .parentPartitionId("42");
    allPartitions.add(parentPartitionIdResult.region(new Region(new ArrayList<>())).build());

    // Act
    CompactionStrategyIndex actualCompactionStrategyIndex =
        new CompactionStrategyIndex(tableStatus, allFileReferences, allPartitions);

    // Assert
    List<FilesInPartition> filesInLeafPartitions =
        actualCompactionStrategyIndex.getFilesInLeafPartitions();
    assertEquals(1, filesInLeafPartitions.size());
    FilesInPartition getResult = filesInLeafPartitions.get(0);
    assertEquals("42", getResult.getPartitionId());
    assertEquals(1, getResult.getFilesWithNoJobIdInAscendingOrder().size());
    assertEquals(2, getResult.getFilesWithJobId().size());
    assertSame(tableStatus, getResult.getTableStatus());
  }

  /**
   * Test {@link CompactionStrategyIndex#CompactionStrategyIndex(TableStatus, List, List)}.
   *
   * <p>Method under test: {@link CompactionStrategyIndex#CompactionStrategyIndex(TableStatus, List,
   * List)}
   */
  @Test
  @DisplayName("Test new CompactionStrategyIndex(TableStatus, List, List)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void CompactionStrategyIndex.<init>(TableStatus, List, List)"})
  void testNewCompactionStrategyIndex4() {
    // Arrange
    TableStatus tableStatus = TableStatus.uniqueIdAndName("42", "Table Name", true);

    ArrayList<FileReference> allFileReferences = new ArrayList<>();
    allFileReferences.add(
        FileReference.builder()
            .countApproximate(true)
            .filename("foo.txt")
            .jobId(null)
            .lastStateStoreUpdateTime(
                LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
            .numberOfRecords(1L)
            .onlyContainsDataForThisPartition(true)
            .partitionId("42")
            .build());
    allFileReferences.add(
        FileReference.builder()
            .countApproximate(true)
            .filename("foo.txt")
            .jobId(null)
            .lastStateStoreUpdateTime(
                LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
            .numberOfRecords(1L)
            .onlyContainsDataForThisPartition(true)
            .partitionId("42")
            .build());
    allFileReferences.add(
        FileReference.builder()
            .countApproximate(true)
            .filename("foo.txt")
            .jobId("42")
            .lastStateStoreUpdateTime(
                LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
            .numberOfRecords(1L)
            .onlyContainsDataForThisPartition(true)
            .partitionId("42")
            .build());
    allFileReferences.add(
        FileReference.builder()
            .countApproximate(true)
            .filename("foo.txt")
            .jobId("42")
            .lastStateStoreUpdateTime(
                LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
            .numberOfRecords(1L)
            .onlyContainsDataForThisPartition(true)
            .partitionId("42")
            .build());

    ArrayList<Partition> allPartitions = new ArrayList<>();

    Builder builderResult = Partition.builder();

    Builder parentPartitionIdResult =
        builderResult
            .childPartitionIds(new ArrayList<>())
            .dimension(1)
            .id("42")
            .leafPartition(true)
            .parentPartitionId("42");
    allPartitions.add(parentPartitionIdResult.region(new Region(new ArrayList<>())).build());

    // Act
    CompactionStrategyIndex actualCompactionStrategyIndex =
        new CompactionStrategyIndex(tableStatus, allFileReferences, allPartitions);

    // Assert
    List<FilesInPartition> filesInLeafPartitions =
        actualCompactionStrategyIndex.getFilesInLeafPartitions();
    assertEquals(1, filesInLeafPartitions.size());
    FilesInPartition getResult = filesInLeafPartitions.get(0);
    assertEquals("42", getResult.getPartitionId());
    assertEquals(2, getResult.getFilesWithJobId().size());
    assertEquals(2, getResult.getFilesWithNoJobIdInAscendingOrder().size());
    assertSame(tableStatus, getResult.getTableStatus());
  }

  /**
   * Test {@link CompactionStrategyIndex#CompactionStrategyIndex(TableStatus, List, List)}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.
   * </ul>
   *
   * <p>Method under test: {@link CompactionStrategyIndex#CompactionStrategyIndex(TableStatus, List,
   * List)}
   */
  @Test
  @DisplayName("Test new CompactionStrategyIndex(TableStatus, List, List); when ArrayList()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void CompactionStrategyIndex.<init>(TableStatus, List, List)"})
  void testNewCompactionStrategyIndex_whenArrayList() {
    // Arrange
    TableStatus tableStatus = TableStatus.uniqueIdAndName("42", "Table Name", true);
    ArrayList<FileReference> allFileReferences = new ArrayList<>();

    // Act
    CompactionStrategyIndex actualCompactionStrategyIndex =
        new CompactionStrategyIndex(tableStatus, allFileReferences, new ArrayList<>());

    // Assert
    assertTrue(actualCompactionStrategyIndex.getFilesInLeafPartitions().isEmpty());
  }

  /**
   * Test {@link CompactionStrategyIndex#CompactionStrategyIndex(TableStatus, List, List)}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.
   * </ul>
   *
   * <p>Method under test: {@link CompactionStrategyIndex#CompactionStrategyIndex(TableStatus, List,
   * List)}
   */
  @Test
  @DisplayName("Test new CompactionStrategyIndex(TableStatus, List, List); when ArrayList()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void CompactionStrategyIndex.<init>(TableStatus, List, List)"})
  void testNewCompactionStrategyIndex_whenArrayList2() {
    // Arrange
    TableStatus tableStatus = TableStatus.uniqueIdAndName("42", "Table Name", true);

    ArrayList<FileReference> allFileReferences = new ArrayList<>();
    allFileReferences.add(
        FileReference.builder()
            .countApproximate(true)
            .filename("foo.txt")
            .jobId("42")
            .lastStateStoreUpdateTime(
                LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
            .numberOfRecords(1L)
            .onlyContainsDataForThisPartition(true)
            .partitionId("42")
            .build());

    // Act
    CompactionStrategyIndex actualCompactionStrategyIndex =
        new CompactionStrategyIndex(tableStatus, allFileReferences, new ArrayList<>());

    // Assert
    assertTrue(actualCompactionStrategyIndex.getFilesInLeafPartitions().isEmpty());
  }

  /**
   * Test {@link CompactionStrategyIndex#CompactionStrategyIndex(TableStatus, List, List)}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.
   * </ul>
   *
   * <p>Method under test: {@link CompactionStrategyIndex#CompactionStrategyIndex(TableStatus, List,
   * List)}
   */
  @Test
  @DisplayName("Test new CompactionStrategyIndex(TableStatus, List, List); when ArrayList()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void CompactionStrategyIndex.<init>(TableStatus, List, List)"})
  void testNewCompactionStrategyIndex_whenArrayList3() {
    // Arrange
    TableStatus tableStatus = TableStatus.uniqueIdAndName("42", "Table Name", true);

    ArrayList<FileReference> allFileReferences = new ArrayList<>();
    allFileReferences.add(
        FileReference.builder()
            .countApproximate(true)
            .filename("foo.txt")
            .jobId("42")
            .lastStateStoreUpdateTime(
                LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
            .numberOfRecords(1L)
            .onlyContainsDataForThisPartition(true)
            .partitionId("42")
            .build());
    allFileReferences.add(
        FileReference.builder()
            .countApproximate(true)
            .filename("foo.txt")
            .jobId("42")
            .lastStateStoreUpdateTime(
                LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
            .numberOfRecords(1L)
            .onlyContainsDataForThisPartition(true)
            .partitionId("42")
            .build());

    // Act
    CompactionStrategyIndex actualCompactionStrategyIndex =
        new CompactionStrategyIndex(tableStatus, allFileReferences, new ArrayList<>());

    // Assert
    assertTrue(actualCompactionStrategyIndex.getFilesInLeafPartitions().isEmpty());
  }

  /**
   * Test {@link CompactionStrategyIndex#CompactionStrategyIndex(TableStatus, List, List)}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.
   * </ul>
   *
   * <p>Method under test: {@link CompactionStrategyIndex#CompactionStrategyIndex(TableStatus, List,
   * List)}
   */
  @Test
  @DisplayName("Test new CompactionStrategyIndex(TableStatus, List, List); when ArrayList()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void CompactionStrategyIndex.<init>(TableStatus, List, List)"})
  void testNewCompactionStrategyIndex_whenArrayList4() {
    // Arrange
    TableStatus tableStatus = TableStatus.uniqueIdAndName("42", "Table Name", true);
    ArrayList<FileReference> allFileReferences = new ArrayList<>();

    ArrayList<Partition> allPartitions = new ArrayList<>();

    Builder builderResult = Partition.builder();

    Builder parentPartitionIdResult =
        builderResult
            .childPartitionIds(new ArrayList<>())
            .dimension(1)
            .id("42")
            .leafPartition(true)
            .parentPartitionId("42");
    allPartitions.add(parentPartitionIdResult.region(new Region(new ArrayList<>())).build());

    // Act
    CompactionStrategyIndex actualCompactionStrategyIndex =
        new CompactionStrategyIndex(tableStatus, allFileReferences, allPartitions);

    // Assert
    assertTrue(actualCompactionStrategyIndex.getFilesInLeafPartitions().isEmpty());
  }

  /**
   * Test {@link CompactionStrategyIndex#CompactionStrategyIndex(TableStatus, List, List)}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.
   * </ul>
   *
   * <p>Method under test: {@link CompactionStrategyIndex#CompactionStrategyIndex(TableStatus, List,
   * List)}
   */
  @Test
  @DisplayName("Test new CompactionStrategyIndex(TableStatus, List, List); when ArrayList()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void CompactionStrategyIndex.<init>(TableStatus, List, List)"})
  void testNewCompactionStrategyIndex_whenArrayList5() {
    // Arrange
    TableStatus tableStatus = TableStatus.uniqueIdAndName("42", "Table Name", true);
    ArrayList<FileReference> allFileReferences = new ArrayList<>();

    ArrayList<Partition> allPartitions = new ArrayList<>();

    Builder builderResult = Partition.builder();

    Builder parentPartitionIdResult =
        builderResult
            .childPartitionIds(new ArrayList<>())
            .dimension(1)
            .id("42")
            .leafPartition(true)
            .parentPartitionId("42");
    allPartitions.add(parentPartitionIdResult.region(new Region(new ArrayList<>())).build());

    Builder builderResult2 = Partition.builder();

    Builder parentPartitionIdResult2 =
        builderResult2
            .childPartitionIds(new ArrayList<>())
            .dimension(1)
            .id("42")
            .leafPartition(true)
            .parentPartitionId("42");
    allPartitions.add(parentPartitionIdResult2.region(new Region(new ArrayList<>())).build());

    // Act
    CompactionStrategyIndex actualCompactionStrategyIndex =
        new CompactionStrategyIndex(tableStatus, allFileReferences, allPartitions);

    // Assert
    assertTrue(actualCompactionStrategyIndex.getFilesInLeafPartitions().isEmpty());
  }

  /**
   * Test {@link CompactionStrategyIndex#getFilesInLeafPartitions()}.
   *
   * <p>Method under test: {@link CompactionStrategyIndex#getFilesInLeafPartitions()}
   */
  @Test
  @DisplayName("Test getFilesInLeafPartitions()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"List CompactionStrategyIndex.getFilesInLeafPartitions()"})
  void testGetFilesInLeafPartitions() {
    // Arrange
    TableStatus tableStatus = TableStatus.uniqueIdAndName("42", "Table Name", true);
    ArrayList<FileReference> allFileReferences = new ArrayList<>();

    CompactionStrategyIndex compactionStrategyIndex =
        new CompactionStrategyIndex(tableStatus, allFileReferences, new ArrayList<>());

    // Act and Assert
    assertTrue(compactionStrategyIndex.getFilesInLeafPartitions().isEmpty());
  }
}
