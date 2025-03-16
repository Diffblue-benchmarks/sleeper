package sleeper.core.tracker.compaction.job.query;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.statestore.AssignJobIdRequest;
import sleeper.core.tracker.compaction.job.query.CompactionJobCreatedStatus.Builder;
import sleeper.core.tracker.compaction.job.update.CompactionJobCreatedEvent;

class CompactionJobCreatedStatusDiffblueTest {
  /**
   * Test Builder {@link Builder#build()}.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Builder#build()}
   *   <li>{@link Builder#inputFilesCount(int)}
   *   <li>{@link Builder#partitionId(String)}
   *   <li>{@link Builder#updateTime(Instant)}
   * </ul>
   */
  @Test
  @DisplayName("Test Builder build()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"CompactionJobCreatedStatus Builder.build()", "Builder Builder.inputFilesCount(int)",
      "Builder Builder.partitionId(String)", "Builder Builder.updateTime(Instant)"})
  void testBuilderBuild() {
    // Arrange
    Builder partitionIdResult = CompactionJobCreatedStatus.builder().inputFilesCount(3).partitionId("42");

    // Act
    CompactionJobCreatedStatus actualBuildResult = partitionIdResult
        .updateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();

    // Assert
    assertEquals("42", actualBuildResult.getPartitionId());
    Instant updateTime = actualBuildResult.getUpdateTime();
    assertEquals(0, updateTime.getNano());
    assertEquals(0L, updateTime.getEpochSecond());
    assertEquals(3, actualBuildResult.getInputFilesCount());
  }

  /**
   * Test {@link CompactionJobCreatedStatus#CompactionJobCreatedStatus(Builder)}.
   * <ul>
   *   <li>When builder.</li>
   *   <li>Then return PartitionId is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobCreatedStatus#CompactionJobCreatedStatus(Builder)}
   */
  @Test
  @DisplayName("Test new CompactionJobCreatedStatus(Builder); when builder; then return PartitionId is 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void CompactionJobCreatedStatus.<init>(Builder)"})
  void testNewCompactionJobCreatedStatus_whenBuilder_thenReturnPartitionIdIsNull() {
    // Arrange and Act
    CompactionJobCreatedStatus actualCompactionJobCreatedStatus = new CompactionJobCreatedStatus(
        CompactionJobCreatedStatus.builder());

    // Assert
    assertNull(actualCompactionJobCreatedStatus.getPartitionId());
    assertNull(actualCompactionJobCreatedStatus.getUpdateTime());
    assertEquals(0, actualCompactionJobCreatedStatus.getInputFilesCount());
  }

  /**
   * Test {@link CompactionJobCreatedStatus#from(CompactionJobCreatedEvent, Instant)} with {@code event}, {@code updateTime}.
   * <ul>
   *   <li>Then return PartitionId is {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobCreatedStatus#from(CompactionJobCreatedEvent, Instant)}
   */
  @Test
  @DisplayName("Test from(CompactionJobCreatedEvent, Instant) with 'event', 'updateTime'; then return PartitionId is '42'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"CompactionJobCreatedStatus CompactionJobCreatedStatus.from(CompactionJobCreatedEvent, Instant)"})
  void testFromWithEventUpdateTime_thenReturnPartitionIdIs42() {
    // Arrange
    CompactionJobCreatedEvent event = CompactionJobCreatedEvent.builder()
        .inputFilesCount(3)
        .jobId("42")
        .partitionId("42")
        .tableId("42")
        .build();
    Instant updateTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    // Act
    CompactionJobCreatedStatus actualFromResult = CompactionJobCreatedStatus.from(event, updateTime);

    // Assert
    assertEquals("42", actualFromResult.getPartitionId());
    assertEquals(3, actualFromResult.getInputFilesCount());
    Instant expectedUpdateTime = updateTime.EPOCH;
    assertSame(expectedUpdateTime, actualFromResult.getUpdateTime());
  }

  /**
   * Test {@link CompactionJobCreatedStatus#from(AssignJobIdRequest, Instant)} with {@code request}, {@code updateTime}.
   * <p>
   * Method under test: {@link CompactionJobCreatedStatus#from(AssignJobIdRequest, Instant)}
   */
  @Test
  @DisplayName("Test from(AssignJobIdRequest, Instant) with 'request', 'updateTime'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"CompactionJobCreatedStatus CompactionJobCreatedStatus.from(AssignJobIdRequest, Instant)"})
  void testFromWithRequestUpdateTime() {
    // Arrange
    AssignJobIdRequest request = mock(AssignJobIdRequest.class);
    when(request.getPartitionId()).thenReturn("42");
    when(request.getFilenames()).thenReturn(new ArrayList<>());
    Instant updateTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    // Act
    CompactionJobCreatedStatus actualFromResult = CompactionJobCreatedStatus.from(request, updateTime);

    // Assert
    verify(request).getFilenames();
    verify(request).getPartitionId();
    assertEquals("42", actualFromResult.getPartitionId());
    assertEquals(0, actualFromResult.getInputFilesCount());
    Instant expectedUpdateTime = updateTime.EPOCH;
    assertSame(expectedUpdateTime, actualFromResult.getUpdateTime());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link CompactionJobCreatedStatus#toString()}
   *   <li>{@link CompactionJobCreatedStatus#getInputFilesCount()}
   *   <li>{@link CompactionJobCreatedStatus#getPartitionId()}
   *   <li>{@link CompactionJobCreatedStatus#getUpdateTime()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"int CompactionJobCreatedStatus.getInputFilesCount()",
      "String CompactionJobCreatedStatus.getPartitionId()", "Instant CompactionJobCreatedStatus.getUpdateTime()",
      "String CompactionJobCreatedStatus.toString()"})
  void testGettersAndSetters() {
    // Arrange
    Builder partitionIdResult = CompactionJobCreatedStatus.builder().inputFilesCount(3).partitionId("42");
    CompactionJobCreatedStatus buildResult = partitionIdResult
        .updateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();

    // Act
    String actualToStringResult = buildResult.toString();
    int actualInputFilesCount = buildResult.getInputFilesCount();
    String actualPartitionId = buildResult.getPartitionId();
    Instant actualUpdateTime = buildResult.getUpdateTime();

    // Assert
    assertEquals("42", actualPartitionId);
    assertEquals("CompactionJobCreatedStatus{updateTime=1970-01-01T00:00:00Z, partitionId=42, inputFilesCount=3}",
        actualToStringResult);
    assertEquals(3, actualInputFilesCount);
    assertSame(actualUpdateTime.EPOCH, actualUpdateTime);
  }

  /**
   * Test {@link CompactionJobCreatedStatus#equals(Object)}, and {@link CompactionJobCreatedStatus#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link CompactionJobCreatedStatus#equals(Object)}
   *   <li>{@link CompactionJobCreatedStatus#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CompactionJobCreatedStatus.equals(Object)", "int CompactionJobCreatedStatus.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    Builder partitionIdResult = CompactionJobCreatedStatus.builder().inputFilesCount(3).partitionId("42");
    CompactionJobCreatedStatus buildResult = partitionIdResult
        .updateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();
    Builder partitionIdResult2 = CompactionJobCreatedStatus.builder().inputFilesCount(3).partitionId("42");
    CompactionJobCreatedStatus buildResult2 = partitionIdResult2
        .updateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();

    // Act and Assert
    assertEquals(buildResult, buildResult2);
    int expectedHashCodeResult = buildResult.hashCode();
    assertEquals(expectedHashCodeResult, buildResult2.hashCode());
  }

  /**
   * Test {@link CompactionJobCreatedStatus#equals(Object)}, and {@link CompactionJobCreatedStatus#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link CompactionJobCreatedStatus#equals(Object)}
   *   <li>{@link CompactionJobCreatedStatus#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CompactionJobCreatedStatus.equals(Object)", "int CompactionJobCreatedStatus.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    Builder partitionIdResult = CompactionJobCreatedStatus.builder().inputFilesCount(3).partitionId("42");
    CompactionJobCreatedStatus buildResult = partitionIdResult
        .updateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();

    // Act and Assert
    assertEquals(buildResult, buildResult);
    int expectedHashCodeResult = buildResult.hashCode();
    assertEquals(expectedHashCodeResult, buildResult.hashCode());
  }

  /**
   * Test {@link CompactionJobCreatedStatus#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobCreatedStatus#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CompactionJobCreatedStatus.equals(Object)", "int CompactionJobCreatedStatus.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    Builder partitionIdResult = CompactionJobCreatedStatus.builder().inputFilesCount(1).partitionId("42");
    CompactionJobCreatedStatus buildResult = partitionIdResult
        .updateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();
    Builder partitionIdResult2 = CompactionJobCreatedStatus.builder().inputFilesCount(3).partitionId("42");
    CompactionJobCreatedStatus buildResult2 = partitionIdResult2
        .updateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link CompactionJobCreatedStatus#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobCreatedStatus#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CompactionJobCreatedStatus.equals(Object)", "int CompactionJobCreatedStatus.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    Builder partitionIdResult = CompactionJobCreatedStatus.builder().inputFilesCount(3).partitionId("Partition Id");
    CompactionJobCreatedStatus buildResult = partitionIdResult
        .updateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();
    Builder partitionIdResult2 = CompactionJobCreatedStatus.builder().inputFilesCount(3).partitionId("42");
    CompactionJobCreatedStatus buildResult2 = partitionIdResult2
        .updateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link CompactionJobCreatedStatus#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobCreatedStatus#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CompactionJobCreatedStatus.equals(Object)", "int CompactionJobCreatedStatus.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual3() {
    // Arrange
    Builder partitionIdResult = CompactionJobCreatedStatus.builder().inputFilesCount(3).partitionId("42");
    CompactionJobCreatedStatus buildResult = partitionIdResult
        .updateTime(LocalDate.now().atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();
    Builder partitionIdResult2 = CompactionJobCreatedStatus.builder().inputFilesCount(3).partitionId("42");
    CompactionJobCreatedStatus buildResult2 = partitionIdResult2
        .updateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link CompactionJobCreatedStatus#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobCreatedStatus#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CompactionJobCreatedStatus.equals(Object)", "int CompactionJobCreatedStatus.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange
    Builder partitionIdResult = CompactionJobCreatedStatus.builder().inputFilesCount(3).partitionId("42");
    CompactionJobCreatedStatus buildResult = partitionIdResult
        .updateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();

    // Act and Assert
    assertNotEquals(buildResult, null);
  }

  /**
   * Test {@link CompactionJobCreatedStatus#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionJobCreatedStatus#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CompactionJobCreatedStatus.equals(Object)", "int CompactionJobCreatedStatus.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange
    Builder partitionIdResult = CompactionJobCreatedStatus.builder().inputFilesCount(3).partitionId("42");
    CompactionJobCreatedStatus buildResult = partitionIdResult
        .updateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();

    // Act and Assert
    assertNotEquals(buildResult, "Different type to CompactionJobCreatedStatus");
  }
}
