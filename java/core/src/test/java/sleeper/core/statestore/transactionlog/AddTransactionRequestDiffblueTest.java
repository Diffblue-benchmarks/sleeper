package sleeper.core.statestore.transactionlog;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.statestore.AllReferencesToAFile;
import sleeper.core.statestore.FileReferenceStoreQueries;
import sleeper.core.statestore.StateStore;
import sleeper.core.statestore.StateStoreException;
import sleeper.core.statestore.transactionlog.AddTransactionRequest.Builder;
import sleeper.core.statestore.transactionlog.state.StateListenerBeforeApply;
import sleeper.core.statestore.transactionlog.transaction.TransactionType;
import sleeper.core.statestore.transactionlog.transaction.impl.AddFilesTransaction;
import sleeper.core.statestore.transactionlog.transaction.impl.ClearFilesTransaction;
import sleeper.core.statestore.transactionlog.transaction.impl.ClearPartitionsTransaction;

class AddTransactionRequestDiffblueTest {
  /**
   * Test Builder {@link Builder#build()}.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Builder#build()}
   *   <li>{@link Builder#beforeApplyListener(StateListenerBeforeApply)}
   *   <li>{@link Builder#bodyKey(String)}
   *   <li>{@link Builder#serialisedTransaction(String)}
   * </ul>
   */
  @Test
  @DisplayName("Test Builder build()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.beforeApplyListener(StateListenerBeforeApply)", "Builder Builder.bodyKey(String)",
      "AddTransactionRequest Builder.build()", "Builder Builder.serialisedTransaction(String)"})
  void testBuilderBuild() {
    // Arrange
    AddFilesTransaction transaction = new AddFilesTransaction(new ArrayList<>());

    // Act
    AddTransactionRequest actualBuildResult = AddTransactionRequest.withTransaction(transaction)
        .beforeApplyListener(mock(StateListenerBeforeApply.class))
        .bodyKey("Not all who wander are lost")
        .serialisedTransaction("Serialised Transaction")
        .build();

    // Assert
    Optional<String> bodyKey = actualBuildResult.getBodyKey();
    assertEquals("Not all who wander are lost", bodyKey.get());
    Optional<String> serialisedTransaction = actualBuildResult.getSerialisedTransaction();
    assertEquals("Serialised Transaction", serialisedTransaction.get());
    assertEquals(TransactionType.ADD_FILES, actualBuildResult.getTransactionType());
    assertTrue(bodyKey.isPresent());
    assertTrue(serialisedTransaction.isPresent());
    assertSame(transaction, actualBuildResult.getTransaction());
  }

  /**
   * Test {@link AddTransactionRequest#getSerialisedTransaction()}.
   * <p>
   * Method under test: {@link AddTransactionRequest#getSerialisedTransaction()}
   */
  @Test
  @DisplayName("Test getSerialisedTransaction()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Optional AddTransactionRequest.getSerialisedTransaction()"})
  void testGetSerialisedTransaction() {
    // Arrange
    AddTransactionRequest buildResult = AddTransactionRequest
        .withTransaction(new AddFilesTransaction(new ArrayList<>()))
        .beforeApplyListener(mock(StateListenerBeforeApply.class))
        .bodyKey("Not all who wander are lost")
        .serialisedTransaction("Serialised Transaction")
        .build();

    // Act
    Optional<String> actualSerialisedTransaction = buildResult.getSerialisedTransaction();

    // Assert
    assertEquals("Serialised Transaction", actualSerialisedTransaction.get());
    assertTrue(actualSerialisedTransaction.isPresent());
  }

  /**
   * Test {@link AddTransactionRequest#checkBeforeAdd(StateStore)}.
   * <p>
   * Method under test: {@link AddTransactionRequest#checkBeforeAdd(StateStore)}
   */
  @Test
  @DisplayName("Test checkBeforeAdd(StateStore)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean AddTransactionRequest.checkBeforeAdd(StateStore)"})
  void testCheckBeforeAdd() throws StateStoreException {
    // Arrange
    ArrayList<AllReferencesToAFile> files = new ArrayList<>();
    AllReferencesToAFile.Builder filenameResult = AllReferencesToAFile.builder().filename("foo.txt");
    AllReferencesToAFile.Builder lastStateStoreUpdateTimeResult = filenameResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    AllReferencesToAFile buildResult = lastStateStoreUpdateTimeResult.references(new ArrayList<>()).build();
    files.add(buildResult);
    AddTransactionRequest buildResult2 = AddTransactionRequest.withTransaction(new AddFilesTransaction(files))
        .beforeApplyListener(mock(StateListenerBeforeApply.class))
        .bodyKey("Not all who wander are lost")
        .serialisedTransaction("Serialised Transaction")
        .build();

    // Act and Assert
    assertTrue(buildResult2.checkBeforeAdd(mock(StateStore.class)));
  }

  /**
   * Test {@link AddTransactionRequest#checkBeforeAdd(StateStore)}.
   * <p>
   * Method under test: {@link AddTransactionRequest#checkBeforeAdd(StateStore)}
   */
  @Test
  @DisplayName("Test checkBeforeAdd(StateStore)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean AddTransactionRequest.checkBeforeAdd(StateStore)"})
  void testCheckBeforeAdd2() throws StateStoreException {
    // Arrange
    AddTransactionRequest buildResult = AddTransactionRequest.withTransaction(new ClearFilesTransaction())
        .beforeApplyListener(mock(StateListenerBeforeApply.class))
        .bodyKey("Not all who wander are lost")
        .serialisedTransaction("Serialised Transaction")
        .build();

    // Act and Assert
    assertTrue(buildResult.checkBeforeAdd(mock(StateStore.class)));
  }

  /**
   * Test {@link AddTransactionRequest#checkBeforeAdd(StateStore)}.
   * <ul>
   *   <li>Given {@code true}.</li>
   *   <li>Then calls {@link FileReferenceStoreQueries#hasNoFiles()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AddTransactionRequest#checkBeforeAdd(StateStore)}
   */
  @Test
  @DisplayName("Test checkBeforeAdd(StateStore); given 'true'; then calls hasNoFiles()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean AddTransactionRequest.checkBeforeAdd(StateStore)"})
  void testCheckBeforeAdd_givenTrue_thenCallsHasNoFiles() throws StateStoreException {
    // Arrange
    AddTransactionRequest buildResult = AddTransactionRequest.withTransaction(ClearPartitionsTransaction.create())
        .beforeApplyListener(mock(StateListenerBeforeApply.class))
        .bodyKey("Not all who wander are lost")
        .serialisedTransaction("Serialised Transaction")
        .build();
    StateStore stateStore = mock(StateStore.class);
    when(stateStore.hasNoFiles()).thenReturn(true);

    // Act
    boolean actualCheckBeforeAddResult = buildResult.checkBeforeAdd(stateStore);

    // Assert
    verify(stateStore).hasNoFiles();
    assertTrue(actualCheckBeforeAddResult);
  }

  /**
   * Test {@link AddTransactionRequest#checkBeforeAdd(StateStore)}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AddTransactionRequest#checkBeforeAdd(StateStore)}
   */
  @Test
  @DisplayName("Test checkBeforeAdd(StateStore); then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean AddTransactionRequest.checkBeforeAdd(StateStore)"})
  void testCheckBeforeAdd_thenReturnFalse() throws StateStoreException {
    // Arrange
    AddTransactionRequest buildResult = AddTransactionRequest
        .withTransaction(new AddFilesTransaction(new ArrayList<>()))
        .beforeApplyListener(mock(StateListenerBeforeApply.class))
        .bodyKey("Not all who wander are lost")
        .serialisedTransaction("Serialised Transaction")
        .build();

    // Act and Assert
    assertFalse(buildResult.checkBeforeAdd(mock(StateStore.class)));
  }

  /**
   * Test {@link AddTransactionRequest#getTransactionType()}.
   * <ul>
   *   <li>Then return {@code ADD_FILES}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AddTransactionRequest#getTransactionType()}
   */
  @Test
  @DisplayName("Test getTransactionType(); then return 'ADD_FILES'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"TransactionType AddTransactionRequest.getTransactionType()"})
  void testGetTransactionType_thenReturnAddFiles() {
    // Arrange
    AddTransactionRequest buildResult = AddTransactionRequest
        .withTransaction(new AddFilesTransaction(new ArrayList<>()))
        .beforeApplyListener(mock(StateListenerBeforeApply.class))
        .bodyKey("Not all who wander are lost")
        .serialisedTransaction("Serialised Transaction")
        .build();

    // Act and Assert
    assertEquals(TransactionType.ADD_FILES, buildResult.getTransactionType());
  }

  /**
   * Test {@link AddTransactionRequest#getTransactionType()}.
   * <ul>
   *   <li>Then return {@code CLEAR_FILES}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AddTransactionRequest#getTransactionType()}
   */
  @Test
  @DisplayName("Test getTransactionType(); then return 'CLEAR_FILES'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"TransactionType AddTransactionRequest.getTransactionType()"})
  void testGetTransactionType_thenReturnClearFiles() {
    // Arrange
    AddTransactionRequest buildResult = AddTransactionRequest.withTransaction(new ClearFilesTransaction())
        .beforeApplyListener(mock(StateListenerBeforeApply.class))
        .bodyKey("Not all who wander are lost")
        .serialisedTransaction("Serialised Transaction")
        .build();

    // Act and Assert
    assertEquals(TransactionType.CLEAR_FILES, buildResult.getTransactionType());
  }

  /**
   * Test {@link AddTransactionRequest#getBodyKey()}.
   * <p>
   * Method under test: {@link AddTransactionRequest#getBodyKey()}
   */
  @Test
  @DisplayName("Test getBodyKey()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Optional AddTransactionRequest.getBodyKey()"})
  void testGetBodyKey() {
    // Arrange
    AddTransactionRequest buildResult = AddTransactionRequest
        .withTransaction(new AddFilesTransaction(new ArrayList<>()))
        .beforeApplyListener(mock(StateListenerBeforeApply.class))
        .bodyKey("Not all who wander are lost")
        .serialisedTransaction("Serialised Transaction")
        .build();

    // Act
    Optional<String> actualBodyKey = buildResult.getBodyKey();

    // Assert
    assertEquals("Not all who wander are lost", actualBodyKey.get());
    assertTrue(actualBodyKey.isPresent());
  }
}
