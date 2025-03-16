package sleeper.core.statestore.transactionlog.transaction.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.statestore.FileReferenceStore;
import sleeper.core.statestore.StateStore;
import sleeper.core.statestore.StateStoreException;
import sleeper.core.statestore.transactionlog.AddTransactionRequest;
import sleeper.core.statestore.transactionlog.state.StateStoreFiles;

class ClearFilesTransactionDiffblueTest {
  /**
   * Test {@link ClearFilesTransaction#synchronousCommit(StateStore)}.
   * <ul>
   *   <li>Then throw {@link StateStoreException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClearFilesTransaction#synchronousCommit(StateStore)}
   */
  @Test
  @DisplayName("Test synchronousCommit(StateStore); then throw StateStoreException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ClearFilesTransaction.synchronousCommit(StateStore)"})
  void testSynchronousCommit_thenThrowStateStoreException() throws StateStoreException {
    // Arrange
    ClearFilesTransaction clearFilesTransaction = new ClearFilesTransaction();
    StateStore stateStore = mock(StateStore.class);
    doThrow(new StateStoreException("An error occurred")).when(stateStore)
        .addFilesTransaction(Mockito.<AddTransactionRequest>any());

    // Act and Assert
    assertThrows(StateStoreException.class, () -> clearFilesTransaction.synchronousCommit(stateStore));
    verify(stateStore).addFilesTransaction(isA(AddTransactionRequest.class));
  }

  /**
   * Test {@link ClearFilesTransaction#synchronousCommit(StateStore)}.
   * <ul>
   *   <li>When {@link StateStore} {@link FileReferenceStore#addFilesTransaction(AddTransactionRequest)} does nothing.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClearFilesTransaction#synchronousCommit(StateStore)}
   */
  @Test
  @DisplayName("Test synchronousCommit(StateStore); when StateStore addFilesTransaction(AddTransactionRequest) does nothing")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ClearFilesTransaction.synchronousCommit(StateStore)"})
  void testSynchronousCommit_whenStateStoreAddFilesTransactionDoesNothing() throws StateStoreException {
    // Arrange
    ClearFilesTransaction clearFilesTransaction = new ClearFilesTransaction();
    StateStore stateStore = mock(StateStore.class);
    doNothing().when(stateStore).addFilesTransaction(Mockito.<AddTransactionRequest>any());

    // Act
    clearFilesTransaction.synchronousCommit(stateStore);

    // Assert
    verify(stateStore).addFilesTransaction(isA(AddTransactionRequest.class));
  }

  /**
   * Test {@link ClearFilesTransaction#apply(StateStoreFiles, Instant)} with {@code StateStoreFiles}, {@code Instant}.
   * <ul>
   *   <li>Then calls {@link StateStoreFiles#clear()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClearFilesTransaction#apply(StateStoreFiles, Instant)}
   */
  @Test
  @DisplayName("Test apply(StateStoreFiles, Instant) with 'StateStoreFiles', 'Instant'; then calls clear()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ClearFilesTransaction.apply(StateStoreFiles, Instant)"})
  void testApplyWithStateStoreFilesInstant_thenCallsClear() {
    // Arrange
    ClearFilesTransaction clearFilesTransaction = new ClearFilesTransaction();
    StateStoreFiles stateStoreFiles = mock(StateStoreFiles.class);
    doNothing().when(stateStoreFiles).clear();

    // Act
    clearFilesTransaction.apply(stateStoreFiles,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Assert
    verify(stateStoreFiles).clear();
  }

  /**
   * Test {@link ClearFilesTransaction#equals(Object)}, and {@link ClearFilesTransaction#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link ClearFilesTransaction#equals(Object)}
   *   <li>{@link ClearFilesTransaction#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean ClearFilesTransaction.equals(Object)", "int ClearFilesTransaction.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    ClearFilesTransaction clearFilesTransaction = new ClearFilesTransaction();
    ClearFilesTransaction clearFilesTransaction2 = new ClearFilesTransaction();

    // Act and Assert
    assertEquals(clearFilesTransaction, clearFilesTransaction2);
    int expectedHashCodeResult = clearFilesTransaction.hashCode();
    assertEquals(expectedHashCodeResult, clearFilesTransaction2.hashCode());
  }

  /**
   * Test {@link ClearFilesTransaction#equals(Object)}, and {@link ClearFilesTransaction#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link ClearFilesTransaction#equals(Object)}
   *   <li>{@link ClearFilesTransaction#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean ClearFilesTransaction.equals(Object)", "int ClearFilesTransaction.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    ClearFilesTransaction clearFilesTransaction = new ClearFilesTransaction();

    // Act and Assert
    assertEquals(clearFilesTransaction, clearFilesTransaction);
    int expectedHashCodeResult = clearFilesTransaction.hashCode();
    assertEquals(expectedHashCodeResult, clearFilesTransaction.hashCode());
  }

  /**
   * Test {@link ClearFilesTransaction#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClearFilesTransaction#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean ClearFilesTransaction.equals(Object)", "int ClearFilesTransaction.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new ClearFilesTransaction(), 3);
  }

  /**
   * Test {@link ClearFilesTransaction#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClearFilesTransaction#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean ClearFilesTransaction.equals(Object)", "int ClearFilesTransaction.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new ClearFilesTransaction(), null);
  }

  /**
   * Test {@link ClearFilesTransaction#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClearFilesTransaction#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean ClearFilesTransaction.equals(Object)", "int ClearFilesTransaction.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new ClearFilesTransaction(), "Different type to ClearFilesTransaction");
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>default or parameterless constructor of {@link ClearFilesTransaction}
   *   <li>{@link ClearFilesTransaction#validate(StateStoreFiles)}
   *   <li>{@link ClearFilesTransaction#toString()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ClearFilesTransaction.<init>()", "java.lang.String ClearFilesTransaction.toString()",
      "void ClearFilesTransaction.validate(StateStoreFiles)"})
  void testGettersAndSetters() throws StateStoreException {
    // Arrange and Act
    ClearFilesTransaction actualClearFilesTransaction = new ClearFilesTransaction();
    actualClearFilesTransaction.validate(new StateStoreFiles());

    // Assert
    assertEquals("ClearFilesTransaction{}", actualClearFilesTransaction.toString());
  }
}
