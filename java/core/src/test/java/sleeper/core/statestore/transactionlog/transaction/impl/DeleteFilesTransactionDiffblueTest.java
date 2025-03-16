package sleeper.core.statestore.transactionlog.transaction.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.statestore.FileReferenceStore;
import sleeper.core.statestore.StateStore;
import sleeper.core.statestore.StateStoreException;
import sleeper.core.statestore.exception.FileHasReferencesException;
import sleeper.core.statestore.exception.FileNotFoundException;
import sleeper.core.statestore.transactionlog.AddTransactionRequest;
import sleeper.core.statestore.transactionlog.state.StateStoreFiles;

class DeleteFilesTransactionDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link DeleteFilesTransaction#DeleteFilesTransaction(List)}
   *   <li>{@link DeleteFilesTransaction#toString()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DeleteFilesTransaction.<init>(List)", "String DeleteFilesTransaction.toString()"})
  void testGettersAndSetters() {
    // Arrange, Act and Assert
    assertEquals("DeleteFilesTransaction{filenames=[]}", (new DeleteFilesTransaction(new ArrayList<>())).toString());
  }

  /**
   * Test {@link DeleteFilesTransaction#synchronousCommit(StateStore)}.
   * <ul>
   *   <li>Then throw {@link FileHasReferencesException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeleteFilesTransaction#synchronousCommit(StateStore)}
   */
  @Test
  @DisplayName("Test synchronousCommit(StateStore); then throw FileHasReferencesException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DeleteFilesTransaction.synchronousCommit(StateStore)"})
  void testSynchronousCommit_thenThrowFileHasReferencesException() throws StateStoreException {
    // Arrange
    DeleteFilesTransaction deleteFilesTransaction = new DeleteFilesTransaction(new ArrayList<>());
    StateStore stateStore = mock(StateStore.class);
    doThrow(new FileHasReferencesException("foo.txt", 3)).when(stateStore)
        .addFilesTransaction(Mockito.<AddTransactionRequest>any());

    // Act and Assert
    assertThrows(FileHasReferencesException.class, () -> deleteFilesTransaction.synchronousCommit(stateStore));
    verify(stateStore).addFilesTransaction(isA(AddTransactionRequest.class));
  }

  /**
   * Test {@link DeleteFilesTransaction#synchronousCommit(StateStore)}.
   * <ul>
   *   <li>When {@link StateStore} {@link FileReferenceStore#addFilesTransaction(AddTransactionRequest)} does nothing.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeleteFilesTransaction#synchronousCommit(StateStore)}
   */
  @Test
  @DisplayName("Test synchronousCommit(StateStore); when StateStore addFilesTransaction(AddTransactionRequest) does nothing")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DeleteFilesTransaction.synchronousCommit(StateStore)"})
  void testSynchronousCommit_whenStateStoreAddFilesTransactionDoesNothing() throws StateStoreException {
    // Arrange
    DeleteFilesTransaction deleteFilesTransaction = new DeleteFilesTransaction(new ArrayList<>());
    StateStore stateStore = mock(StateStore.class);
    doNothing().when(stateStore).addFilesTransaction(Mockito.<AddTransactionRequest>any());

    // Act
    deleteFilesTransaction.synchronousCommit(stateStore);

    // Assert
    verify(stateStore).addFilesTransaction(isA(AddTransactionRequest.class));
  }

  /**
   * Test {@link DeleteFilesTransaction#validate(StateStoreFiles)} with {@code StateStoreFiles}.
   * <ul>
   *   <li>Then throw {@link FileNotFoundException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeleteFilesTransaction#validate(StateStoreFiles)}
   */
  @Test
  @DisplayName("Test validate(StateStoreFiles) with 'StateStoreFiles'; then throw FileNotFoundException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DeleteFilesTransaction.validate(StateStoreFiles)"})
  void testValidateWithStateStoreFiles_thenThrowFileNotFoundException() throws StateStoreException {
    // Arrange
    ArrayList<String> filenames = new ArrayList<>();
    filenames.add("foo");
    DeleteFilesTransaction deleteFilesTransaction = new DeleteFilesTransaction(filenames);

    // Act and Assert
    assertThrows(FileNotFoundException.class, () -> deleteFilesTransaction.validate(new StateStoreFiles()));
  }

  /**
   * Test {@link DeleteFilesTransaction#apply(StateStoreFiles, Instant)} with {@code StateStoreFiles}, {@code Instant}.
   * <ul>
   *   <li>Then throw {@link FileHasReferencesException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeleteFilesTransaction#apply(StateStoreFiles, Instant)}
   */
  @Test
  @DisplayName("Test apply(StateStoreFiles, Instant) with 'StateStoreFiles', 'Instant'; then throw FileHasReferencesException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DeleteFilesTransaction.apply(StateStoreFiles, Instant)"})
  void testApplyWithStateStoreFilesInstant_thenThrowFileHasReferencesException() {
    // Arrange
    ArrayList<String> filenames = new ArrayList<>();
    filenames.add("foo");
    DeleteFilesTransaction deleteFilesTransaction = new DeleteFilesTransaction(filenames);
    StateStoreFiles stateStoreFiles = mock(StateStoreFiles.class);
    doThrow(new FileHasReferencesException("foo.txt", 3)).when(stateStoreFiles).remove(Mockito.<String>any());

    // Act and Assert
    assertThrows(FileHasReferencesException.class, () -> deleteFilesTransaction.apply(stateStoreFiles,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    verify(stateStoreFiles).remove(eq("foo"));
  }

  /**
   * Test {@link DeleteFilesTransaction#isEmpty()}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@code foo}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeleteFilesTransaction#isEmpty()}
   */
  @Test
  @DisplayName("Test isEmpty(); given ArrayList() add 'foo'; then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean DeleteFilesTransaction.isEmpty()"})
  void testIsEmpty_givenArrayListAddFoo_thenReturnFalse() {
    // Arrange
    ArrayList<String> filenames = new ArrayList<>();
    filenames.add("foo");

    // Act and Assert
    assertFalse((new DeleteFilesTransaction(filenames)).isEmpty());
  }

  /**
   * Test {@link DeleteFilesTransaction#isEmpty()}.
   * <ul>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeleteFilesTransaction#isEmpty()}
   */
  @Test
  @DisplayName("Test isEmpty(); then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean DeleteFilesTransaction.isEmpty()"})
  void testIsEmpty_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue((new DeleteFilesTransaction(new ArrayList<>())).isEmpty());
  }

  /**
   * Test {@link DeleteFilesTransaction#equals(Object)}, and {@link DeleteFilesTransaction#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link DeleteFilesTransaction#equals(Object)}
   *   <li>{@link DeleteFilesTransaction#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean DeleteFilesTransaction.equals(Object)", "int DeleteFilesTransaction.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    DeleteFilesTransaction deleteFilesTransaction = new DeleteFilesTransaction(new ArrayList<>());
    DeleteFilesTransaction deleteFilesTransaction2 = new DeleteFilesTransaction(new ArrayList<>());

    // Act and Assert
    assertEquals(deleteFilesTransaction, deleteFilesTransaction2);
    int expectedHashCodeResult = deleteFilesTransaction.hashCode();
    assertEquals(expectedHashCodeResult, deleteFilesTransaction2.hashCode());
  }

  /**
   * Test {@link DeleteFilesTransaction#equals(Object)}, and {@link DeleteFilesTransaction#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link DeleteFilesTransaction#equals(Object)}
   *   <li>{@link DeleteFilesTransaction#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean DeleteFilesTransaction.equals(Object)", "int DeleteFilesTransaction.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    DeleteFilesTransaction deleteFilesTransaction = new DeleteFilesTransaction(new ArrayList<>());

    // Act and Assert
    assertEquals(deleteFilesTransaction, deleteFilesTransaction);
    int expectedHashCodeResult = deleteFilesTransaction.hashCode();
    assertEquals(expectedHashCodeResult, deleteFilesTransaction.hashCode());
  }

  /**
   * Test {@link DeleteFilesTransaction#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeleteFilesTransaction#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean DeleteFilesTransaction.equals(Object)", "int DeleteFilesTransaction.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    ArrayList<String> filenames = new ArrayList<>();
    filenames.add("foo");
    DeleteFilesTransaction deleteFilesTransaction = new DeleteFilesTransaction(filenames);

    // Act and Assert
    assertNotEquals(deleteFilesTransaction, new DeleteFilesTransaction(new ArrayList<>()));
  }

  /**
   * Test {@link DeleteFilesTransaction#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeleteFilesTransaction#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean DeleteFilesTransaction.equals(Object)", "int DeleteFilesTransaction.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new DeleteFilesTransaction(new ArrayList<>()), null);
  }

  /**
   * Test {@link DeleteFilesTransaction#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeleteFilesTransaction#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean DeleteFilesTransaction.equals(Object)", "int DeleteFilesTransaction.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new DeleteFilesTransaction(new ArrayList<>()), "Different type to DeleteFilesTransaction");
  }
}
