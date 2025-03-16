package sleeper.compaction.core.job.creation;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.statestore.AssignJobIdRequest;
import sleeper.core.statestore.StateStore;
import sleeper.core.statestore.commit.StateStoreCommitRequest;
import sleeper.core.statestore.commit.StateStoreCommitRequestSender;
import sleeper.core.statestore.transactionlog.AddTransactionRequest;
import sleeper.core.table.TableStatus;

class AssignJobIdToFilesDiffblueTest {
  /**
   * Test {@link AssignJobIdToFiles#synchronous(StateStore)}.
   * <p>
   * Method under test: {@link AssignJobIdToFiles#synchronous(StateStore)}
   */
  @Test
  @DisplayName("Test synchronous(StateStore)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"AssignJobIdToFiles AssignJobIdToFiles.synchronous(StateStore)"})
  void testSynchronous() {
    // Arrange
    StateStore stateStore = mock(StateStore.class);
    doNothing().when(stateStore).addFilesTransaction(Mockito.<AddTransactionRequest>any());

    // Act
    AssignJobIdToFiles actualSynchronousResult = AssignJobIdToFiles.synchronous(stateStore);
    ArrayList<AssignJobIdRequest> assignJobIdRequestList = new ArrayList<>();
    actualSynchronousResult.assignJobIds(assignJobIdRequestList, TableStatus.uniqueIdAndName("42", "Table Name", true));

    // Assert
    verify(stateStore).addFilesTransaction(isA(AddTransactionRequest.class));
  }

  /**
   * Test {@link AssignJobIdToFiles#byQueue(StateStoreCommitRequestSender)}.
   * <ul>
   *   <li>Then calls {@link StateStoreCommitRequestSender#send(StateStoreCommitRequest)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AssignJobIdToFiles#byQueue(StateStoreCommitRequestSender)}
   */
  @Test
  @DisplayName("Test byQueue(StateStoreCommitRequestSender); then calls send(StateStoreCommitRequest)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"AssignJobIdToFiles AssignJobIdToFiles.byQueue(StateStoreCommitRequestSender)"})
  void testByQueue_thenCallsSend() {
    // Arrange
    StateStoreCommitRequestSender queueSender = mock(StateStoreCommitRequestSender.class);
    doNothing().when(queueSender).send(Mockito.<StateStoreCommitRequest>any());

    // Act
    AssignJobIdToFiles actualByQueueResult = AssignJobIdToFiles.byQueue(queueSender);
    ArrayList<AssignJobIdRequest> assignJobIdRequestList = new ArrayList<>();
    actualByQueueResult.assignJobIds(assignJobIdRequestList, TableStatus.uniqueIdAndName("42", "Table Name", true));

    // Assert
    verify(queueSender).send(isA(StateStoreCommitRequest.class));
  }
}
