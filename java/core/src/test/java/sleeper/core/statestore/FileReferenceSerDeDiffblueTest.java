package sleeper.core.statestore;

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
import sleeper.core.statestore.FileReference.Builder;

class FileReferenceSerDeDiffblueTest {
  /**
   * Test {@link FileReferenceSerDe#toJson(FileReference)}.
   * <p>
   * Method under test: {@link FileReferenceSerDe#toJson(FileReference)}
   */
  @Test
  @DisplayName("Test toJson(FileReference)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String FileReferenceSerDe.toJson(FileReference)"})
  void testToJson() {
    // Arrange
    FileReferenceSerDe fileReferenceSerDe = new FileReferenceSerDe();
    Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference file = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();

    // Act and Assert
    assertEquals(
        "{\"filename\":\"foo.txt\",\"partitionId\":\"42\",\"numberOfRecords\":1,\"jobId\":\"42\",\"lastStateStoreUpdateTime\""
            + ":0,\"countApproximate\":true,\"onlyContainsDataForThisPartition\":true}",
        fileReferenceSerDe.toJson(file));
  }

  /**
   * Test {@link FileReferenceSerDe#toJson(FileReference)}.
   * <p>
   * Method under test: {@link FileReferenceSerDe#toJson(FileReference)}
   */
  @Test
  @DisplayName("Test toJson(FileReference)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String FileReferenceSerDe.toJson(FileReference)"})
  void testToJson2() {
    // Arrange
    FileReferenceSerDe fileReferenceSerDe = new FileReferenceSerDe();
    Builder jobIdResult = FileReference.builder().countApproximate(false).filename("foo.txt").jobId("42");
    FileReference file = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();

    // Act and Assert
    assertEquals(
        "{\"filename\":\"foo.txt\",\"partitionId\":\"42\",\"numberOfRecords\":1,\"jobId\":\"42\",\"lastStateStoreUpdateTime\""
            + ":0,\"countApproximate\":false,\"onlyContainsDataForThisPartition\":true}",
        fileReferenceSerDe.toJson(file));
  }

  /**
   * Test {@link FileReferenceSerDe#toJson(FileReference)}.
   * <p>
   * Method under test: {@link FileReferenceSerDe#toJson(FileReference)}
   */
  @Test
  @DisplayName("Test toJson(FileReference)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String FileReferenceSerDe.toJson(FileReference)"})
  void testToJson3() {
    // Arrange
    FileReferenceSerDe fileReferenceSerDe = new FileReferenceSerDe();
    Builder jobIdResult = FileReference.builder().countApproximate(true).filename("").jobId("42");
    FileReference file = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();

    // Act and Assert
    assertEquals(
        "{\"filename\":\"\",\"partitionId\":\"42\",\"numberOfRecords\":1,\"jobId\":\"42\",\"lastStateStoreUpdateTime\":0,"
            + "\"countApproximate\":true,\"onlyContainsDataForThisPartition\":true}",
        fileReferenceSerDe.toJson(file));
  }

  /**
   * Test {@link FileReferenceSerDe#toJson(FileReference)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link FileReferenceSerDe#toJson(FileReference)}
   */
  @Test
  @DisplayName("Test toJson(FileReference); when 'null'; then return 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String FileReferenceSerDe.toJson(FileReference)"})
  void testToJson_whenNull_thenReturnNull() {
    // Arrange, Act and Assert
    assertEquals("null", (new FileReferenceSerDe()).toJson(null));
  }

  /**
   * Test {@link FileReferenceSerDe#fromJson(String)}.
   * <ul>
   *   <li>When empty string.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link FileReferenceSerDe#fromJson(String)}
   */
  @Test
  @DisplayName("Test fromJson(String); when empty string; then return 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"FileReference FileReferenceSerDe.fromJson(String)"})
  void testFromJson_whenEmptyString_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull((new FileReferenceSerDe()).fromJson(""));
  }

  /**
   * Test {@link FileReferenceSerDe#collectionToJson(Collection)}.
   * <p>
   * Method under test: {@link FileReferenceSerDe#collectionToJson(Collection)}
   */
  @Test
  @DisplayName("Test collectionToJson(Collection)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String FileReferenceSerDe.collectionToJson(Collection)"})
  void testCollectionToJson() {
    // Arrange
    FileReferenceSerDe fileReferenceSerDe = new FileReferenceSerDe();

    ArrayList<FileReference> files = new ArrayList<>();
    Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference buildResult = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    files.add(buildResult);

    // Act and Assert
    assertEquals(
        "[{\"filename\":\"foo.txt\",\"partitionId\":\"42\",\"numberOfRecords\":1,\"jobId\":\"42\",\"lastStateStoreUpdateTime"
            + "\":0,\"countApproximate\":true,\"onlyContainsDataForThisPartition\":true}]",
        fileReferenceSerDe.collectionToJson(files));
  }

  /**
   * Test {@link FileReferenceSerDe#collectionToJson(Collection)}.
   * <p>
   * Method under test: {@link FileReferenceSerDe#collectionToJson(Collection)}
   */
  @Test
  @DisplayName("Test collectionToJson(Collection)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String FileReferenceSerDe.collectionToJson(Collection)"})
  void testCollectionToJson2() {
    // Arrange
    FileReferenceSerDe fileReferenceSerDe = new FileReferenceSerDe();

    ArrayList<FileReference> files = new ArrayList<>();
    Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference buildResult = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    files.add(buildResult);
    Builder jobIdResult2 = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference buildResult2 = jobIdResult2
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    files.add(buildResult2);

    // Act and Assert
    assertEquals(
        "[{\"filename\":\"foo.txt\",\"partitionId\":\"42\",\"numberOfRecords\":1,\"jobId\":\"42\",\"lastStateStoreUpdateTime"
            + "\":0,\"countApproximate\":true,\"onlyContainsDataForThisPartition\":true},{\"filename\":\"foo.txt\",\"partitionId"
            + "\":\"42\",\"numberOfRecords\":1,\"jobId\":\"42\",\"lastStateStoreUpdateTime\":0,\"countApproximate\":true,"
            + "\"onlyContainsDataForThisPartition\":true}]",
        fileReferenceSerDe.collectionToJson(files));
  }

  /**
   * Test {@link FileReferenceSerDe#collectionToJson(Collection)}.
   * <p>
   * Method under test: {@link FileReferenceSerDe#collectionToJson(Collection)}
   */
  @Test
  @DisplayName("Test collectionToJson(Collection)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String FileReferenceSerDe.collectionToJson(Collection)"})
  void testCollectionToJson3() {
    // Arrange
    FileReferenceSerDe fileReferenceSerDe = new FileReferenceSerDe();

    ArrayList<FileReference> files = new ArrayList<>();
    Builder jobIdResult = FileReference.builder().countApproximate(false).filename("foo.txt").jobId("42");
    FileReference buildResult = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    files.add(buildResult);

    // Act and Assert
    assertEquals(
        "[{\"filename\":\"foo.txt\",\"partitionId\":\"42\",\"numberOfRecords\":1,\"jobId\":\"42\",\"lastStateStoreUpdateTime"
            + "\":0,\"countApproximate\":false,\"onlyContainsDataForThisPartition\":true}]",
        fileReferenceSerDe.collectionToJson(files));
  }

  /**
   * Test {@link FileReferenceSerDe#collectionToJson(Collection)}.
   * <p>
   * Method under test: {@link FileReferenceSerDe#collectionToJson(Collection)}
   */
  @Test
  @DisplayName("Test collectionToJson(Collection)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String FileReferenceSerDe.collectionToJson(Collection)"})
  void testCollectionToJson4() {
    // Arrange
    FileReferenceSerDe fileReferenceSerDe = new FileReferenceSerDe();

    ArrayList<FileReference> files = new ArrayList<>();
    Builder jobIdResult = FileReference.builder().countApproximate(true).filename("").jobId("42");
    FileReference buildResult = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    files.add(buildResult);

    // Act and Assert
    assertEquals(
        "[{\"filename\":\"\",\"partitionId\":\"42\",\"numberOfRecords\":1,\"jobId\":\"42\",\"lastStateStoreUpdateTime\":0,"
            + "\"countApproximate\":true,\"onlyContainsDataForThisPartition\":true}]",
        fileReferenceSerDe.collectionToJson(files));
  }

  /**
   * Test {@link FileReferenceSerDe#collectionToJson(Collection)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return {@code []}.</li>
   * </ul>
   * <p>
   * Method under test: {@link FileReferenceSerDe#collectionToJson(Collection)}
   */
  @Test
  @DisplayName("Test collectionToJson(Collection); when ArrayList(); then return '[]'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String FileReferenceSerDe.collectionToJson(Collection)"})
  void testCollectionToJson_whenArrayList_thenReturnLeftSquareBracketRightSquareBracket() {
    // Arrange
    FileReferenceSerDe fileReferenceSerDe = new FileReferenceSerDe();

    // Act and Assert
    assertEquals("[]", fileReferenceSerDe.collectionToJson(new ArrayList<>()));
  }

  /**
   * Test {@link FileReferenceSerDe#listFromJson(String)}.
   * <ul>
   *   <li>When empty string.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link FileReferenceSerDe#listFromJson(String)}
   */
  @Test
  @DisplayName("Test listFromJson(String); when empty string; then return 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.util.List FileReferenceSerDe.listFromJson(String)"})
  void testListFromJson_whenEmptyString_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull((new FileReferenceSerDe()).listFromJson(""));
  }

  /**
   * Test new {@link FileReferenceSerDe} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link FileReferenceSerDe}
   */
  @Test
  @DisplayName("Test new FileReferenceSerDe (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void FileReferenceSerDe.<init>()"})
  void testNewFileReferenceSerDe() {
    // Arrange, Act and Assert
    assertEquals("null", (new FileReferenceSerDe()).toJson(null));
  }
}
