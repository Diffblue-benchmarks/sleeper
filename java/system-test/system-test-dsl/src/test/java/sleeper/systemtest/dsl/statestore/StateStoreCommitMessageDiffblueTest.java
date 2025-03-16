package sleeper.systemtest.dsl.statestore;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.table.TableProperties;
import sleeper.core.statestore.FileReference;
import sleeper.core.statestore.FileReference.Builder;
import sleeper.core.statestore.commit.StateStoreCommitRequest;
import sleeper.core.statestore.transactionlog.transaction.TransactionType;
import sleeper.systemtest.dsl.statestore.StateStoreCommitMessage.Commit;

class StateStoreCommitMessageDiffblueTest {
  /**
   * Test {@link StateStoreCommitMessage#addFileWithJob(FileReference)}.
   * <p>
   * Method under test: {@link StateStoreCommitMessage#addFileWithJob(FileReference)}
   */
  @Test
  @DisplayName("Test addFileWithJob(FileReference)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Commit StateStoreCommitMessage.addFileWithJob(FileReference)"})
  void testAddFileWithJob() {
    // Arrange
    Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference file = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();

    // Act
    Commit actualAddFileWithJobResult = StateStoreCommitMessage.addFileWithJob(file);
    InstanceProperties instanceProperties = new InstanceProperties();
    StateStoreCommitRequest actualCreateMessageResult = actualAddFileWithJobResult.createMessage(
        new StateStoreCommitMessageFactory(instanceProperties, new TableProperties(new InstanceProperties())));

    // Assert
    assertNull(actualCreateMessageResult.getBodyKey());
    assertNull(actualCreateMessageResult.getTableId());
    assertEquals(TransactionType.ADD_FILES, actualCreateMessageResult.getTransactionType());
  }

  /**
   * Test {@link StateStoreCommitMessage#addFiles(List)}.
   * <p>
   * Method under test: {@link StateStoreCommitMessage#addFiles(List)}
   */
  @Test
  @DisplayName("Test addFiles(List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Commit StateStoreCommitMessage.addFiles(List)"})
  void testAddFiles() {
    // Arrange
    ArrayList<FileReference> files = new ArrayList<>();
    Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference buildResult = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    files.add(buildResult);

    // Act
    Commit actualAddFilesResult = StateStoreCommitMessage.addFiles(files);
    InstanceProperties instanceProperties = new InstanceProperties();
    StateStoreCommitRequest actualCreateMessageResult = actualAddFilesResult.createMessage(
        new StateStoreCommitMessageFactory(instanceProperties, new TableProperties(new InstanceProperties())));

    // Assert
    assertNull(actualCreateMessageResult.getBodyKey());
    assertNull(actualCreateMessageResult.getTableId());
    assertEquals(TransactionType.ADD_FILES, actualCreateMessageResult.getTransactionType());
  }

  /**
   * Test {@link StateStoreCommitMessage#addFiles(List)}.
   * <p>
   * Method under test: {@link StateStoreCommitMessage#addFiles(List)}
   */
  @Test
  @DisplayName("Test addFiles(List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Commit StateStoreCommitMessage.addFiles(List)"})
  void testAddFiles2() {
    // Arrange
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

    // Act
    Commit actualAddFilesResult = StateStoreCommitMessage.addFiles(files);
    InstanceProperties instanceProperties = new InstanceProperties();
    StateStoreCommitRequest actualCreateMessageResult = actualAddFilesResult.createMessage(
        new StateStoreCommitMessageFactory(instanceProperties, new TableProperties(new InstanceProperties())));

    // Assert
    assertNull(actualCreateMessageResult.getBodyKey());
    assertNull(actualCreateMessageResult.getTableId());
    assertEquals(TransactionType.ADD_FILES, actualCreateMessageResult.getTransactionType());
  }

  /**
   * Test {@link StateStoreCommitMessage#addFiles(List)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreCommitMessage#addFiles(List)}
   */
  @Test
  @DisplayName("Test addFiles(List); when ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Commit StateStoreCommitMessage.addFiles(List)"})
  void testAddFiles_whenArrayList() {
    // Arrange and Act
    Commit actualAddFilesResult = StateStoreCommitMessage.addFiles(new ArrayList<>());
    InstanceProperties instanceProperties = new InstanceProperties();
    StateStoreCommitRequest actualCreateMessageResult = actualAddFilesResult.createMessage(
        new StateStoreCommitMessageFactory(instanceProperties, new TableProperties(new InstanceProperties())));

    // Assert
    assertNull(actualCreateMessageResult.getBodyKey());
    assertNull(actualCreateMessageResult.getTableId());
    assertEquals(TransactionType.ADD_FILES, actualCreateMessageResult.getTransactionType());
  }

  /**
   * Test {@link StateStoreCommitMessage#addFile(FileReference)}.
   * <p>
   * Method under test: {@link StateStoreCommitMessage#addFile(FileReference)}
   */
  @Test
  @DisplayName("Test addFile(FileReference)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Commit StateStoreCommitMessage.addFile(FileReference)"})
  void testAddFile() {
    // Arrange
    Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference file = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();

    // Act
    Commit actualAddFileResult = StateStoreCommitMessage.addFile(file);
    InstanceProperties instanceProperties = new InstanceProperties();
    StateStoreCommitRequest actualCreateMessageResult = actualAddFileResult.createMessage(
        new StateStoreCommitMessageFactory(instanceProperties, new TableProperties(new InstanceProperties())));

    // Assert
    assertNull(actualCreateMessageResult.getBodyKey());
    assertNull(actualCreateMessageResult.getTableId());
    assertEquals(TransactionType.ADD_FILES, actualCreateMessageResult.getTransactionType());
  }

  /**
   * Test {@link StateStoreCommitMessage#addPartitionFile(String, String, long)}.
   * <p>
   * Method under test: {@link StateStoreCommitMessage#addPartitionFile(String, String, long)}
   */
  @Test
  @DisplayName("Test addPartitionFile(String, String, long)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Commit StateStoreCommitMessage.addPartitionFile(String, String, long)"})
  void testAddPartitionFile() {
    // Arrange and Act
    Commit actualAddPartitionFileResult = StateStoreCommitMessage.addPartitionFile("42", "foo.txt", 1L);
    InstanceProperties instanceProperties = new InstanceProperties();
    StateStoreCommitRequest actualCreateMessageResult = actualAddPartitionFileResult.createMessage(
        new StateStoreCommitMessageFactory(instanceProperties, new TableProperties(new InstanceProperties())));

    // Assert
    assertNull(actualCreateMessageResult.getBodyKey());
    assertNull(actualCreateMessageResult.getTableId());
    assertEquals(TransactionType.ADD_FILES, actualCreateMessageResult.getTransactionType());
  }
}
