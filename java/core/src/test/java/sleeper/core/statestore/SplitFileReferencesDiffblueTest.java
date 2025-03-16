package sleeper.core.statestore;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.statestore.transactionlog.AddTransactionRequest;

class SplitFileReferencesDiffblueTest {
  /**
   * Test {@link SplitFileReferences#split()}.
   * <ul>
   *   <li>Then calls {@link FileReferenceStore#addFilesTransaction(AddTransactionRequest)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SplitFileReferences#split()}
   */
  @Test
  @DisplayName("Test split(); then calls addFilesTransaction(AddTransactionRequest)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SplitFileReferences.split()"})
  void testSplit_thenCallsAddFilesTransaction() throws StateStoreException {
    // Arrange
    StateStore store = mock(StateStore.class);
    when(store.getFileReferencesWithNoJobId()).thenReturn(new ArrayList<>());
    when(store.getAllPartitions()).thenReturn(new ArrayList<>());
    doNothing().when(store).addFilesTransaction(Mockito.<AddTransactionRequest>any());

    // Act
    SplitFileReferences.from(store).split();

    // Assert
    verify(store).addFilesTransaction(isA(AddTransactionRequest.class));
    verify(store).getFileReferencesWithNoJobId();
    verify(store).getAllPartitions();
  }

  /**
   * Test {@link SplitFileReferences#split()}.
   * <ul>
   *   <li>Then throw {@link StateStoreException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SplitFileReferences#split()}
   */
  @Test
  @DisplayName("Test split(); then throw StateStoreException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SplitFileReferences.split()"})
  void testSplit_thenThrowStateStoreException() throws StateStoreException {
    // Arrange
    StateStore stateStore = mock(StateStore.class);
    when(stateStore.getFileReferencesWithNoJobId()).thenThrow(new StateStoreException("An error occurred"));

    // Act and Assert
    assertThrows(StateStoreException.class, () -> (new SplitFileReferences(stateStore)).split());
    verify(stateStore).getFileReferencesWithNoJobId();
  }
}
