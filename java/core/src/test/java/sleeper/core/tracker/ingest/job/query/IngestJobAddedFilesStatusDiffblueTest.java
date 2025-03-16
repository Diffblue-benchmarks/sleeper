package sleeper.core.tracker.ingest.job.query;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.tracker.ingest.job.query.IngestJobAddedFilesStatus.Builder;

class IngestJobAddedFilesStatusDiffblueTest {
  /**
   * Test Builder {@link Builder#build()}.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Builder#build()}
   *   <li>{@link Builder#fileCount(int)}
   *   <li>{@link Builder#updateTime(Instant)}
   *   <li>{@link Builder#writtenTime(Instant)}
   * </ul>
   */
  @Test
  @DisplayName("Test Builder build()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void Builder.<init>()", "IngestJobAddedFilesStatus Builder.build()",
      "Builder Builder.fileCount(int)", "Builder Builder.updateTime(Instant)", "Builder Builder.writtenTime(Instant)"})
  void testBuilderBuild() {
    // Arrange
    Builder fileCountResult = IngestJobAddedFilesStatus.builder().fileCount(3);
    Builder updateTimeResult = fileCountResult
        .updateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act
    IngestJobAddedFilesStatus actualBuildResult = updateTimeResult
        .writtenTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();

    // Assert
    Instant updateTime = actualBuildResult.getUpdateTime();
    assertEquals(0, updateTime.getNano());
    assertEquals(0L, updateTime.getEpochSecond());
    assertEquals(3, actualBuildResult.getFileCount());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link IngestJobAddedFilesStatus#toString()}
   *   <li>{@link IngestJobAddedFilesStatus#getFileCount()}
   *   <li>{@link IngestJobAddedFilesStatus#getUpdateTime()}
   *   <li>{@link IngestJobAddedFilesStatus#getWrittenTime()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"int IngestJobAddedFilesStatus.getFileCount()",
      "Instant IngestJobAddedFilesStatus.getUpdateTime()", "Instant IngestJobAddedFilesStatus.getWrittenTime()",
      "String IngestJobAddedFilesStatus.toString()"})
  void testGettersAndSetters() {
    // Arrange
    Builder fileCountResult = IngestJobAddedFilesStatus.builder().fileCount(3);
    Builder updateTimeResult = fileCountResult
        .updateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    IngestJobAddedFilesStatus buildResult = updateTimeResult
        .writtenTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();

    // Act
    String actualToStringResult = buildResult.toString();
    int actualFileCount = buildResult.getFileCount();
    Instant actualUpdateTime = buildResult.getUpdateTime();
    Instant actualWrittenTime = buildResult.getWrittenTime();

    // Assert
    assertEquals("IngestJobAddedFilesStatus{writtenTime=1970-01-01T00:00:00Z, updateTime=1970-01-01T00:00:00Z,"
        + " fileCount=3}", actualToStringResult);
    assertEquals(3, actualFileCount);
    Instant instant = actualWrittenTime.EPOCH;
    assertSame(instant, actualUpdateTime);
    assertSame(instant, actualWrittenTime);
  }

  /**
   * Test {@link IngestJobAddedFilesStatus#equals(Object)}, and {@link IngestJobAddedFilesStatus#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link IngestJobAddedFilesStatus#equals(Object)}
   *   <li>{@link IngestJobAddedFilesStatus#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestJobAddedFilesStatus.equals(Object)", "int IngestJobAddedFilesStatus.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    Builder fileCountResult = IngestJobAddedFilesStatus.builder().fileCount(3);
    Builder updateTimeResult = fileCountResult
        .updateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    IngestJobAddedFilesStatus buildResult = updateTimeResult
        .writtenTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();
    Builder fileCountResult2 = IngestJobAddedFilesStatus.builder().fileCount(3);
    Builder updateTimeResult2 = fileCountResult2
        .updateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    IngestJobAddedFilesStatus buildResult2 = updateTimeResult2
        .writtenTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();

    // Act and Assert
    assertEquals(buildResult, buildResult2);
    int expectedHashCodeResult = buildResult.hashCode();
    assertEquals(expectedHashCodeResult, buildResult2.hashCode());
  }

  /**
   * Test {@link IngestJobAddedFilesStatus#equals(Object)}, and {@link IngestJobAddedFilesStatus#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link IngestJobAddedFilesStatus#equals(Object)}
   *   <li>{@link IngestJobAddedFilesStatus#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestJobAddedFilesStatus.equals(Object)", "int IngestJobAddedFilesStatus.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    Builder fileCountResult = IngestJobAddedFilesStatus.builder().fileCount(3);
    Builder updateTimeResult = fileCountResult
        .updateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    IngestJobAddedFilesStatus buildResult = updateTimeResult
        .writtenTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();

    // Act and Assert
    assertEquals(buildResult, buildResult);
    int expectedHashCodeResult = buildResult.hashCode();
    assertEquals(expectedHashCodeResult, buildResult.hashCode());
  }

  /**
   * Test {@link IngestJobAddedFilesStatus#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobAddedFilesStatus#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestJobAddedFilesStatus.equals(Object)", "int IngestJobAddedFilesStatus.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    Builder builder = mock(Builder.class);
    when(builder.fileCount(anyInt())).thenReturn(IngestJobAddedFilesStatus.builder());
    Builder fileCountResult = builder.fileCount(3);
    Builder updateTimeResult = fileCountResult
        .updateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    IngestJobAddedFilesStatus buildResult = updateTimeResult
        .writtenTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();
    Builder fileCountResult2 = IngestJobAddedFilesStatus.builder().fileCount(3);
    Builder updateTimeResult2 = fileCountResult2
        .updateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    IngestJobAddedFilesStatus buildResult2 = updateTimeResult2
        .writtenTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link IngestJobAddedFilesStatus#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobAddedFilesStatus#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestJobAddedFilesStatus.equals(Object)", "int IngestJobAddedFilesStatus.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    Builder builder = mock(Builder.class);
    when(builder.updateTime(Mockito.<Instant>any())).thenReturn(IngestJobAddedFilesStatus.builder());
    Builder builder2 = mock(Builder.class);
    when(builder2.fileCount(anyInt())).thenReturn(builder);
    Builder fileCountResult = builder2.fileCount(3);
    Builder updateTimeResult = fileCountResult
        .updateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    IngestJobAddedFilesStatus buildResult = updateTimeResult
        .writtenTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();
    Builder fileCountResult2 = IngestJobAddedFilesStatus.builder().fileCount(3);
    Builder updateTimeResult2 = fileCountResult2
        .updateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    IngestJobAddedFilesStatus buildResult2 = updateTimeResult2
        .writtenTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link IngestJobAddedFilesStatus#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobAddedFilesStatus#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestJobAddedFilesStatus.equals(Object)", "int IngestJobAddedFilesStatus.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual3() {
    // Arrange
    Builder builder = mock(Builder.class);
    when(builder.writtenTime(Mockito.<Instant>any())).thenReturn(IngestJobAddedFilesStatus.builder());
    Builder builder2 = mock(Builder.class);
    when(builder2.updateTime(Mockito.<Instant>any())).thenReturn(builder);
    Builder builder3 = mock(Builder.class);
    when(builder3.fileCount(anyInt())).thenReturn(builder2);
    Builder fileCountResult = builder3.fileCount(3);
    Builder updateTimeResult = fileCountResult
        .updateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    IngestJobAddedFilesStatus buildResult = updateTimeResult
        .writtenTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();
    Builder fileCountResult2 = IngestJobAddedFilesStatus.builder().fileCount(3);
    Builder updateTimeResult2 = fileCountResult2
        .updateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    IngestJobAddedFilesStatus buildResult2 = updateTimeResult2
        .writtenTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link IngestJobAddedFilesStatus#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobAddedFilesStatus#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestJobAddedFilesStatus.equals(Object)", "int IngestJobAddedFilesStatus.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange
    Builder fileCountResult = IngestJobAddedFilesStatus.builder().fileCount(3);
    Builder updateTimeResult = fileCountResult
        .updateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    IngestJobAddedFilesStatus buildResult = updateTimeResult
        .writtenTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();

    // Act and Assert
    assertNotEquals(buildResult, null);
  }

  /**
   * Test {@link IngestJobAddedFilesStatus#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobAddedFilesStatus#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestJobAddedFilesStatus.equals(Object)", "int IngestJobAddedFilesStatus.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange
    Builder fileCountResult = IngestJobAddedFilesStatus.builder().fileCount(3);
    Builder updateTimeResult = fileCountResult
        .updateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    IngestJobAddedFilesStatus buildResult = updateTimeResult
        .writtenTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .build();

    // Act and Assert
    assertNotEquals(buildResult, "Different type to IngestJobAddedFilesStatus");
  }
}
