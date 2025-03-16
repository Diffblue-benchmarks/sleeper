package sleeper.ingest.batcher.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.ingest.batcher.core.FileIngestRequest.Builder;

class FileIngestRequestDiffblueTest {
  /**
   * Test Builder {@link Builder#build()}.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Builder#build()}
   *   <li>{@link Builder#file(String)}
   *   <li>{@link Builder#fileSizeBytes(long)}
   *   <li>{@link Builder#jobId(String)}
   *   <li>{@link Builder#receivedTime(Instant)}
   *   <li>{@link Builder#tableId(String)}
   * </ul>
   */
  @Test
  @DisplayName("Test Builder build()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"FileIngestRequest Builder.build()", "Builder Builder.file(String)",
      "Builder Builder.fileSizeBytes(long)", "Builder Builder.jobId(String)", "Builder Builder.receivedTime(Instant)",
      "Builder Builder.tableId(String)"})
  void testBuilderBuild() {
    // Arrange
    Builder jobIdResult = FileIngestRequest.builder().file("File").fileSizeBytes(3L).jobId("42");

    // Act
    FileIngestRequest actualBuildResult = jobIdResult
        .receivedTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .tableId("42")
        .build();

    // Assert
    assertEquals("42", actualBuildResult.getJobId());
    assertEquals("42", actualBuildResult.getTableId());
    assertEquals("File", actualBuildResult.getFile());
    Instant receivedTime = actualBuildResult.getReceivedTime();
    assertEquals(0, receivedTime.getNano());
    assertEquals(0L, receivedTime.getEpochSecond());
    assertEquals(3L, actualBuildResult.getFileSizeBytes());
    assertTrue(actualBuildResult.isAssignedToJob());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link FileIngestRequest#toString()}
   *   <li>{@link FileIngestRequest#getFile()}
   *   <li>{@link FileIngestRequest#getFileSizeBytes()}
   *   <li>{@link FileIngestRequest#getJobId()}
   *   <li>{@link FileIngestRequest#getReceivedTime()}
   *   <li>{@link FileIngestRequest#getTableId()}
   *   <li>{@link FileIngestRequest#toBuilder()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String FileIngestRequest.getFile()", "long FileIngestRequest.getFileSizeBytes()",
      "String FileIngestRequest.getJobId()", "Instant FileIngestRequest.getReceivedTime()",
      "String FileIngestRequest.getTableId()", "Builder FileIngestRequest.toBuilder()",
      "String FileIngestRequest.toString()"})
  void testGettersAndSetters() {
    // Arrange
    Builder jobIdResult = FileIngestRequest.builder().file("File").fileSizeBytes(3L).jobId("42");
    FileIngestRequest buildResult = jobIdResult
        .receivedTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .tableId("42")
        .build();

    // Act
    String actualToStringResult = buildResult.toString();
    String actualFile = buildResult.getFile();
    long actualFileSizeBytes = buildResult.getFileSizeBytes();
    String actualJobId = buildResult.getJobId();
    Instant actualReceivedTime = buildResult.getReceivedTime();
    String actualTableId = buildResult.getTableId();
    buildResult.toBuilder();

    // Assert
    assertEquals("42", actualJobId);
    assertEquals("42", actualTableId);
    assertEquals("File", actualFile);
    assertEquals("FileIngestRequest{file='File', fileSizeBytes=3, tableId='42', receivedTime=1970-01-01T00:00:00Z,"
        + " jobId='42'}", actualToStringResult);
    assertEquals(3L, actualFileSizeBytes);
    assertSame(actualReceivedTime.EPOCH, actualReceivedTime);
  }

  /**
   * Test {@link FileIngestRequest#equals(Object)}, and {@link FileIngestRequest#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link FileIngestRequest#equals(Object)}
   *   <li>{@link FileIngestRequest#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean FileIngestRequest.equals(Object)", "int FileIngestRequest.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    Builder jobIdResult = FileIngestRequest.builder().file("File").fileSizeBytes(3L).jobId("42");
    FileIngestRequest buildResult = jobIdResult
        .receivedTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .tableId("42")
        .build();
    Builder jobIdResult2 = FileIngestRequest.builder().file("File").fileSizeBytes(3L).jobId("42");
    FileIngestRequest buildResult2 = jobIdResult2
        .receivedTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .tableId("42")
        .build();

    // Act and Assert
    assertEquals(buildResult, buildResult2);
    int expectedHashCodeResult = buildResult.hashCode();
    assertEquals(expectedHashCodeResult, buildResult2.hashCode());
  }

  /**
   * Test {@link FileIngestRequest#equals(Object)}, and {@link FileIngestRequest#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link FileIngestRequest#equals(Object)}
   *   <li>{@link FileIngestRequest#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean FileIngestRequest.equals(Object)", "int FileIngestRequest.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    Builder jobIdResult = FileIngestRequest.builder().file("File").fileSizeBytes(3L).jobId("42");
    FileIngestRequest buildResult = jobIdResult
        .receivedTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .tableId("42")
        .build();

    // Act and Assert
    assertEquals(buildResult, buildResult);
    int expectedHashCodeResult = buildResult.hashCode();
    assertEquals(expectedHashCodeResult, buildResult.hashCode());
  }

  /**
   * Test {@link FileIngestRequest#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link FileIngestRequest#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean FileIngestRequest.equals(Object)", "int FileIngestRequest.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    Builder jobIdResult = FileIngestRequest.builder().file("42").fileSizeBytes(3L).jobId("42");
    FileIngestRequest buildResult = jobIdResult
        .receivedTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .tableId("42")
        .build();
    Builder jobIdResult2 = FileIngestRequest.builder().file("File").fileSizeBytes(3L).jobId("42");
    FileIngestRequest buildResult2 = jobIdResult2
        .receivedTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .tableId("42")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link FileIngestRequest#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link FileIngestRequest#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean FileIngestRequest.equals(Object)", "int FileIngestRequest.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    Builder jobIdResult = FileIngestRequest.builder().file("File").fileSizeBytes(1L).jobId("42");
    FileIngestRequest buildResult = jobIdResult
        .receivedTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .tableId("42")
        .build();
    Builder jobIdResult2 = FileIngestRequest.builder().file("File").fileSizeBytes(3L).jobId("42");
    FileIngestRequest buildResult2 = jobIdResult2
        .receivedTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .tableId("42")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link FileIngestRequest#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link FileIngestRequest#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean FileIngestRequest.equals(Object)", "int FileIngestRequest.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual3() {
    // Arrange
    Builder jobIdResult = FileIngestRequest.builder().file("File").fileSizeBytes(3L).jobId("Job Id");
    FileIngestRequest buildResult = jobIdResult
        .receivedTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .tableId("42")
        .build();
    Builder jobIdResult2 = FileIngestRequest.builder().file("File").fileSizeBytes(3L).jobId("42");
    FileIngestRequest buildResult2 = jobIdResult2
        .receivedTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .tableId("42")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link FileIngestRequest#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link FileIngestRequest#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean FileIngestRequest.equals(Object)", "int FileIngestRequest.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual4() {
    // Arrange
    Builder jobIdResult = FileIngestRequest.builder().file("File").fileSizeBytes(3L).jobId("42");
    FileIngestRequest buildResult = jobIdResult
        .receivedTime(LocalDate.now().atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .tableId("42")
        .build();
    Builder jobIdResult2 = FileIngestRequest.builder().file("File").fileSizeBytes(3L).jobId("42");
    FileIngestRequest buildResult2 = jobIdResult2
        .receivedTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .tableId("42")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link FileIngestRequest#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link FileIngestRequest#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean FileIngestRequest.equals(Object)", "int FileIngestRequest.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual5() {
    // Arrange
    Builder jobIdResult = FileIngestRequest.builder().file("File").fileSizeBytes(3L).jobId("42");
    FileIngestRequest buildResult = jobIdResult
        .receivedTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .tableId("Table Id")
        .build();
    Builder jobIdResult2 = FileIngestRequest.builder().file("File").fileSizeBytes(3L).jobId("42");
    FileIngestRequest buildResult2 = jobIdResult2
        .receivedTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .tableId("42")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link FileIngestRequest#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link FileIngestRequest#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean FileIngestRequest.equals(Object)", "int FileIngestRequest.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange
    Builder jobIdResult = FileIngestRequest.builder().file("File").fileSizeBytes(3L).jobId("42");
    FileIngestRequest buildResult = jobIdResult
        .receivedTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .tableId("42")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, null);
  }

  /**
   * Test {@link FileIngestRequest#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link FileIngestRequest#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean FileIngestRequest.equals(Object)", "int FileIngestRequest.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange
    Builder jobIdResult = FileIngestRequest.builder().file("File").fileSizeBytes(3L).jobId("42");
    FileIngestRequest buildResult = jobIdResult
        .receivedTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .tableId("42")
        .build();

    // Act and Assert
    assertNotEquals(buildResult, "Different type to FileIngestRequest");
  }
}
