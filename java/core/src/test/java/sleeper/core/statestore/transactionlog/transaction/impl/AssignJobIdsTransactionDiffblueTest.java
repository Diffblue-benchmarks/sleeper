package sleeper.core.statestore.transactionlog.transaction.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.statestore.FileReference;
import sleeper.core.statestore.FileReference.Builder;
import sleeper.core.statestore.FileReferenceStore;
import sleeper.core.statestore.StateStore;
import sleeper.core.statestore.exception.FileReferenceAssignedToJobException;
import sleeper.core.statestore.transactionlog.AddTransactionRequest;

class AssignJobIdsTransactionDiffblueTest {
  /**
   * Test {@link AssignJobIdsTransaction#AssignJobIdsTransaction(List)}.
   * <p>
   * Method under test: {@link AssignJobIdsTransaction#AssignJobIdsTransaction(List)}
   */
  @Test
  @DisplayName("Test new AssignJobIdsTransaction(List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AssignJobIdsTransaction.<init>(List)"})
  void testNewAssignJobIdsTransaction() {
    // Arrange, Act and Assert
    assertTrue((new AssignJobIdsTransaction(new ArrayList<>())).isEmpty());
  }

  /**
   * Test {@link AssignJobIdsTransaction#synchronousCommit(StateStore)}.
   * <ul>
   *   <li>Then throw {@link FileReferenceAssignedToJobException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AssignJobIdsTransaction#synchronousCommit(StateStore)}
   */
  @Test
  @DisplayName("Test synchronousCommit(StateStore); then throw FileReferenceAssignedToJobException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AssignJobIdsTransaction.synchronousCommit(StateStore)"})
  void testSynchronousCommit_thenThrowFileReferenceAssignedToJobException() {
    // Arrange
    AssignJobIdsTransaction assignJobIdsTransaction = new AssignJobIdsTransaction(new ArrayList<>());
    StateStore stateStore = mock(StateStore.class);
    Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference fileReference = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    doThrow(new FileReferenceAssignedToJobException(fileReference)).when(stateStore)
        .addFilesTransaction(Mockito.<AddTransactionRequest>any());

    // Act and Assert
    assertThrows(FileReferenceAssignedToJobException.class,
        () -> assignJobIdsTransaction.synchronousCommit(stateStore));
    verify(stateStore).addFilesTransaction(isA(AddTransactionRequest.class));
  }

  /**
   * Test {@link AssignJobIdsTransaction#synchronousCommit(StateStore)}.
   * <ul>
   *   <li>When {@link StateStore} {@link FileReferenceStore#addFilesTransaction(AddTransactionRequest)} does nothing.</li>
   * </ul>
   * <p>
   * Method under test: {@link AssignJobIdsTransaction#synchronousCommit(StateStore)}
   */
  @Test
  @DisplayName("Test synchronousCommit(StateStore); when StateStore addFilesTransaction(AddTransactionRequest) does nothing")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AssignJobIdsTransaction.synchronousCommit(StateStore)"})
  void testSynchronousCommit_whenStateStoreAddFilesTransactionDoesNothing() {
    // Arrange
    AssignJobIdsTransaction assignJobIdsTransaction = new AssignJobIdsTransaction(new ArrayList<>());
    StateStore stateStore = mock(StateStore.class);
    doNothing().when(stateStore).addFilesTransaction(Mockito.<AddTransactionRequest>any());

    // Act
    assignJobIdsTransaction.synchronousCommit(stateStore);

    // Assert
    verify(stateStore).addFilesTransaction(isA(AddTransactionRequest.class));
  }

  /**
   * Test {@link AssignJobIdsTransaction#isEmpty()}.
   * <p>
   * Method under test: {@link AssignJobIdsTransaction#isEmpty()}
   */
  @Test
  @DisplayName("Test isEmpty()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean AssignJobIdsTransaction.isEmpty()"})
  void testIsEmpty() {
    // Arrange, Act and Assert
    assertTrue((new AssignJobIdsTransaction(new ArrayList<>())).isEmpty());
  }

  /**
   * Test {@link AssignJobIdsTransaction#equals(Object)}, and {@link AssignJobIdsTransaction#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link AssignJobIdsTransaction#equals(Object)}
   *   <li>{@link AssignJobIdsTransaction#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean AssignJobIdsTransaction.equals(Object)", "int AssignJobIdsTransaction.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    AssignJobIdsTransaction assignJobIdsTransaction = new AssignJobIdsTransaction(new ArrayList<>());
    AssignJobIdsTransaction assignJobIdsTransaction2 = new AssignJobIdsTransaction(new ArrayList<>());

    // Act and Assert
    assertEquals(assignJobIdsTransaction, assignJobIdsTransaction2);
    int expectedHashCodeResult = assignJobIdsTransaction.hashCode();
    assertEquals(expectedHashCodeResult, assignJobIdsTransaction2.hashCode());
  }

  /**
   * Test {@link AssignJobIdsTransaction#equals(Object)}, and {@link AssignJobIdsTransaction#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link AssignJobIdsTransaction#equals(Object)}
   *   <li>{@link AssignJobIdsTransaction#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean AssignJobIdsTransaction.equals(Object)", "int AssignJobIdsTransaction.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    AssignJobIdsTransaction assignJobIdsTransaction = new AssignJobIdsTransaction(new ArrayList<>());

    // Act and Assert
    assertEquals(assignJobIdsTransaction, assignJobIdsTransaction);
    int expectedHashCodeResult = assignJobIdsTransaction.hashCode();
    assertEquals(expectedHashCodeResult, assignJobIdsTransaction.hashCode());
  }

  /**
   * Test {@link AssignJobIdsTransaction#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link AssignJobIdsTransaction#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean AssignJobIdsTransaction.equals(Object)", "int AssignJobIdsTransaction.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new AssignJobIdsTransaction(new ArrayList<>()), 1);
  }

  /**
   * Test {@link AssignJobIdsTransaction#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link AssignJobIdsTransaction#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean AssignJobIdsTransaction.equals(Object)", "int AssignJobIdsTransaction.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new AssignJobIdsTransaction(new ArrayList<>()), null);
  }

  /**
   * Test {@link AssignJobIdsTransaction#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link AssignJobIdsTransaction#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean AssignJobIdsTransaction.equals(Object)", "int AssignJobIdsTransaction.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new AssignJobIdsTransaction(new ArrayList<>()), "Different type to AssignJobIdsTransaction");
  }

  /**
   * Test {@link AssignJobIdsTransaction#toString()}.
   * <p>
   * Method under test: {@link AssignJobIdsTransaction#toString()}
   */
  @Test
  @DisplayName("Test toString()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.lang.String AssignJobIdsTransaction.toString()"})
  void testToString() {
    // Arrange, Act and Assert
    assertEquals("AssignJobIdsTransaction{requests=[]}", (new AssignJobIdsTransaction(new ArrayList<>())).toString());
  }
}
