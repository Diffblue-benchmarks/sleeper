package sleeper.core.statestore.transactionlog.log;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Consumer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.statestore.testutils.InMemoryTransactionBodyStore;
import sleeper.core.statestore.transactionlog.AddTransactionRequest;
import sleeper.core.statestore.transactionlog.transaction.StateStoreTransaction;
import sleeper.core.statestore.transactionlog.transaction.TransactionSerDe;
import sleeper.core.statestore.transactionlog.transaction.TransactionType;
import sleeper.core.statestore.transactionlog.transaction.impl.AddFilesTransaction;
import sleeper.core.statestore.transactionlog.transaction.impl.ClearFilesTransaction;

class TransactionLogEntryDiffblueTest {
  /**
   * Test {@link TransactionLogEntry#TransactionLogEntry(long, Instant, TransactionType, String)}.
   * <p>
   * Method under test: {@link TransactionLogEntry#TransactionLogEntry(long, Instant, TransactionType, String)}
   */
  @Test
  @DisplayName("Test new TransactionLogEntry(long, Instant, TransactionType, String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void TransactionLogEntry.<init>(long, Instant, TransactionType, String)"})
  void testNewTransactionLogEntry() {
    // Arrange
    Instant updateTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    // Act
    TransactionLogEntry actualTransactionLogEntry = new TransactionLogEntry(1L, updateTime, TransactionType.ADD_FILES,
        "Not all who wander are lost");

    // Assert
    Optional<String> bodyKey = actualTransactionLogEntry.getBodyKey();
    assertEquals("Not all who wander are lost", bodyKey.get());
    assertEquals(1L, actualTransactionLogEntry.getTransactionNumber());
    assertEquals(TransactionType.ADD_FILES, actualTransactionLogEntry.getTransactionType());
    assertFalse(actualTransactionLogEntry.getTransaction().isPresent());
    assertTrue(bodyKey.isPresent());
    Instant expectedUpdateTime = updateTime.EPOCH;
    assertSame(expectedUpdateTime, actualTransactionLogEntry.getUpdateTime());
  }

  /**
   * Test {@link TransactionLogEntry#TransactionLogEntry(long, Instant, StateStoreTransaction)}.
   * <ul>
   *   <li>Then return TransactionType is {@code ADD_FILES}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TransactionLogEntry#TransactionLogEntry(long, Instant, StateStoreTransaction)}
   */
  @Test
  @DisplayName("Test new TransactionLogEntry(long, Instant, StateStoreTransaction); then return TransactionType is 'ADD_FILES'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void TransactionLogEntry.<init>(long, Instant, StateStoreTransaction)"})
  void testNewTransactionLogEntry_thenReturnTransactionTypeIsAddFiles() {
    // Arrange
    Instant updateTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    AddFilesTransaction transaction = new AddFilesTransaction(new ArrayList<>());

    // Act
    TransactionLogEntry actualTransactionLogEntry = new TransactionLogEntry(1L, updateTime, transaction);

    // Assert
    assertEquals(TransactionType.ADD_FILES, actualTransactionLogEntry.getTransactionType());
    assertSame(transaction, actualTransactionLogEntry.getTransaction().get());
  }

  /**
   * Test {@link TransactionLogEntry#TransactionLogEntry(long, Instant, StateStoreTransaction, String)}.
   * <ul>
   *   <li>Then return TransactionType is {@code ADD_FILES}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TransactionLogEntry#TransactionLogEntry(long, Instant, StateStoreTransaction, String)}
   */
  @Test
  @DisplayName("Test new TransactionLogEntry(long, Instant, StateStoreTransaction, String); then return TransactionType is 'ADD_FILES'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void TransactionLogEntry.<init>(long, Instant, StateStoreTransaction, String)"})
  void testNewTransactionLogEntry_thenReturnTransactionTypeIsAddFiles2() {
    // Arrange
    Instant updateTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    AddFilesTransaction transaction = new AddFilesTransaction(new ArrayList<>());

    // Act
    TransactionLogEntry actualTransactionLogEntry = new TransactionLogEntry(1L, updateTime, transaction,
        "Serialised Transaction");

    // Assert
    assertEquals(TransactionType.ADD_FILES, actualTransactionLogEntry.getTransactionType());
    assertSame(transaction, actualTransactionLogEntry.getTransaction().get());
  }

  /**
   * Test {@link TransactionLogEntry#TransactionLogEntry(long, Instant, StateStoreTransaction)}.
   * <ul>
   *   <li>Then Transaction return {@link ClearFilesTransaction}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TransactionLogEntry#TransactionLogEntry(long, Instant, StateStoreTransaction)}
   */
  @Test
  @DisplayName("Test new TransactionLogEntry(long, Instant, StateStoreTransaction); then Transaction return ClearFilesTransaction")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void TransactionLogEntry.<init>(long, Instant, StateStoreTransaction)"})
  void testNewTransactionLogEntry_thenTransactionReturnClearFilesTransaction() {
    // Arrange
    Instant updateTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    ClearFilesTransaction transaction = new ClearFilesTransaction();

    // Act
    TransactionLogEntry actualTransactionLogEntry = new TransactionLogEntry(1L, updateTime, transaction);

    // Assert
    StateStoreTransaction<?> getResult = actualTransactionLogEntry.getTransaction().get();
    assertTrue(getResult instanceof ClearFilesTransaction);
    assertEquals(TransactionType.CLEAR_FILES, actualTransactionLogEntry.getTransactionType());
    assertFalse(getResult.isEmpty());
    assertSame(transaction, getResult);
  }

  /**
   * Test {@link TransactionLogEntry#TransactionLogEntry(long, Instant, StateStoreTransaction, String)}.
   * <ul>
   *   <li>Then Transaction return {@link ClearFilesTransaction}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TransactionLogEntry#TransactionLogEntry(long, Instant, StateStoreTransaction, String)}
   */
  @Test
  @DisplayName("Test new TransactionLogEntry(long, Instant, StateStoreTransaction, String); then Transaction return ClearFilesTransaction")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void TransactionLogEntry.<init>(long, Instant, StateStoreTransaction, String)"})
  void testNewTransactionLogEntry_thenTransactionReturnClearFilesTransaction2() {
    // Arrange
    Instant updateTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    ClearFilesTransaction transaction = new ClearFilesTransaction();

    // Act
    TransactionLogEntry actualTransactionLogEntry = new TransactionLogEntry(1L, updateTime, transaction,
        "Serialised Transaction");

    // Assert
    StateStoreTransaction<?> getResult = actualTransactionLogEntry.getTransaction().get();
    assertTrue(getResult instanceof ClearFilesTransaction);
    assertEquals(TransactionType.CLEAR_FILES, actualTransactionLogEntry.getTransactionType());
    assertFalse(getResult.isEmpty());
    assertSame(transaction, getResult);
  }

  /**
   * Test {@link TransactionLogEntry#fromRequest(long, Instant, AddTransactionRequest)}.
   * <ul>
   *   <li>Then return not Transaction Present.</li>
   * </ul>
   * <p>
   * Method under test: {@link TransactionLogEntry#fromRequest(long, Instant, AddTransactionRequest)}
   */
  @Test
  @DisplayName("Test fromRequest(long, Instant, AddTransactionRequest); then return not Transaction Present")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"TransactionLogEntry TransactionLogEntry.fromRequest(long, Instant, AddTransactionRequest)"})
  void testFromRequest_thenReturnNotTransactionPresent() {
    // Arrange
    Instant updateTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    AddTransactionRequest request = mock(AddTransactionRequest.class);
    when(request.getTransactionType()).thenReturn(TransactionType.ADD_FILES);
    Optional<String> ofResult = Optional.of("foo");
    when(request.getBodyKey()).thenReturn(ofResult);

    // Act
    TransactionLogEntry actualFromRequestResult = TransactionLogEntry.fromRequest(1L, updateTime, request);

    // Assert
    verify(request).getBodyKey();
    verify(request).getTransactionType();
    assertFalse(actualFromRequestResult.getTransaction().isPresent());
    assertEquals(ofResult, actualFromRequestResult.getBodyKey());
  }

  /**
   * Test {@link TransactionLogEntry#fromRequest(long, Instant, AddTransactionRequest)}.
   * <ul>
   *   <li>Then return Transaction is {@link AddFilesTransaction#AddFilesTransaction(List)} with files is {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TransactionLogEntry#fromRequest(long, Instant, AddTransactionRequest)}
   */
  @Test
  @DisplayName("Test fromRequest(long, Instant, AddTransactionRequest); then return Transaction is AddFilesTransaction(List) with files is ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"TransactionLogEntry TransactionLogEntry.fromRequest(long, Instant, AddTransactionRequest)"})
  void testFromRequest_thenReturnTransactionIsAddFilesTransactionWithFilesIsArrayList() {
    // Arrange
    Instant updateTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    AddTransactionRequest request = mock(AddTransactionRequest.class);
    Optional<String> emptyResult = Optional.empty();
    when(request.getBodyKey()).thenReturn(emptyResult);
    Optional<String> ofResult = Optional.of("foo");
    when(request.getSerialisedTransaction()).thenReturn(ofResult);
    AddFilesTransaction addFilesTransaction = new AddFilesTransaction(new ArrayList<>());
    Mockito.<StateStoreTransaction<?>>when(request.getTransaction()).thenReturn(addFilesTransaction);

    // Act
    TransactionLogEntry actualFromRequestResult = TransactionLogEntry.fromRequest(1L, updateTime, request);

    // Assert
    verify(request).getBodyKey();
    verify(request).getSerialisedTransaction();
    verify(request).getTransaction();
    assertSame(addFilesTransaction, actualFromRequestResult.getTransaction().get());
  }

  /**
   * Test {@link TransactionLogEntry#fromRequest(long, Instant, AddTransactionRequest)}.
   * <ul>
   *   <li>Then Transaction return {@link ClearFilesTransaction}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TransactionLogEntry#fromRequest(long, Instant, AddTransactionRequest)}
   */
  @Test
  @DisplayName("Test fromRequest(long, Instant, AddTransactionRequest); then Transaction return ClearFilesTransaction")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"TransactionLogEntry TransactionLogEntry.fromRequest(long, Instant, AddTransactionRequest)"})
  void testFromRequest_thenTransactionReturnClearFilesTransaction() {
    // Arrange
    Instant updateTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    AddTransactionRequest request = mock(AddTransactionRequest.class);
    Optional<String> emptyResult = Optional.empty();
    when(request.getBodyKey()).thenReturn(emptyResult);
    Optional<String> ofResult = Optional.of("foo");
    when(request.getSerialisedTransaction()).thenReturn(ofResult);
    ClearFilesTransaction clearFilesTransaction = new ClearFilesTransaction();
    Mockito.<StateStoreTransaction<?>>when(request.getTransaction()).thenReturn(clearFilesTransaction);

    // Act
    TransactionLogEntry actualFromRequestResult = TransactionLogEntry.fromRequest(1L, updateTime, request);

    // Assert
    verify(request).getBodyKey();
    verify(request).getSerialisedTransaction();
    verify(request).getTransaction();
    StateStoreTransaction<?> getResult = actualFromRequestResult.getTransaction().get();
    assertTrue(getResult instanceof ClearFilesTransaction);
    assertEquals(TransactionType.CLEAR_FILES, actualFromRequestResult.getTransactionType());
    assertFalse(getResult.isEmpty());
    assertSame(clearFilesTransaction, getResult);
  }

  /**
   * Test {@link TransactionLogEntry#getBodyKey()}.
   * <ul>
   *   <li>Given {@link AddFilesTransaction#AddFilesTransaction(List)} with files is {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return not Present.</li>
   * </ul>
   * <p>
   * Method under test: {@link TransactionLogEntry#getBodyKey()}
   */
  @Test
  @DisplayName("Test getBodyKey(); given AddFilesTransaction(List) with files is ArrayList(); then return not Present")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Optional TransactionLogEntry.getBodyKey()"})
  void testGetBodyKey_givenAddFilesTransactionWithFilesIsArrayList_thenReturnNotPresent() {
    // Arrange
    Instant updateTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

    // Act and Assert
    assertFalse(
        (new TransactionLogEntry(1L, updateTime, new AddFilesTransaction(new ArrayList<>()))).getBodyKey().isPresent());
  }

  /**
   * Test {@link TransactionLogEntry#getTransaction()}.
   * <ul>
   *   <li>Then return Present.</li>
   * </ul>
   * <p>
   * Method under test: {@link TransactionLogEntry#getTransaction()}
   */
  @Test
  @DisplayName("Test getTransaction(); then return Present")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Optional TransactionLogEntry.getTransaction()"})
  void testGetTransaction_thenReturnPresent() {
    // Arrange
    Instant updateTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    AddFilesTransaction transaction = new AddFilesTransaction(new ArrayList<>());

    // Act
    Optional<StateStoreTransaction<?>> actualTransaction = (new TransactionLogEntry(1L, updateTime, transaction))
        .getTransaction();

    // Assert
    assertTrue(actualTransaction.isPresent());
    assertSame(transaction, actualTransaction.get());
  }

  /**
   * Test {@link TransactionLogEntry#withSerialisedTransactionOrObjectKey(TransactionSerDe, Consumer, Consumer)}.
   * <p>
   * Method under test: {@link TransactionLogEntry#withSerialisedTransactionOrObjectKey(TransactionSerDe, Consumer, Consumer)}
   */
  @Test
  @DisplayName("Test withSerialisedTransactionOrObjectKey(TransactionSerDe, Consumer, Consumer)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void TransactionLogEntry.withSerialisedTransactionOrObjectKey(TransactionSerDe, Consumer, Consumer)"})
  void testWithSerialisedTransactionOrObjectKey() {
    // Arrange
    Instant updateTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    TransactionLogEntry transactionLogEntry = new TransactionLogEntry(1L, updateTime,
        new AddFilesTransaction(new ArrayList<>()), "Serialised Transaction");
    TransactionSerDe serDe = TransactionSerDe.forFileTransactions();
    Consumer<String> withTransaction = mock(Consumer.class);
    doNothing().when(withTransaction).accept(Mockito.<String>any());

    // Act
    transactionLogEntry.withSerialisedTransactionOrObjectKey(serDe, withTransaction, mock(Consumer.class));

    // Assert
    verify(withTransaction).accept(eq("Serialised Transaction"));
  }

  /**
   * Test {@link TransactionLogEntry#withSerialisedTransactionOrObjectKey(TransactionSerDe, Consumer, Consumer)}.
   * <ul>
   *   <li>Then calls {@link Consumer#accept(Object)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TransactionLogEntry#withSerialisedTransactionOrObjectKey(TransactionSerDe, Consumer, Consumer)}
   */
  @Test
  @DisplayName("Test withSerialisedTransactionOrObjectKey(TransactionSerDe, Consumer, Consumer); then calls accept(Object)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void TransactionLogEntry.withSerialisedTransactionOrObjectKey(TransactionSerDe, Consumer, Consumer)"})
  void testWithSerialisedTransactionOrObjectKey_thenCallsAccept() {
    // Arrange
    Instant updateTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    TransactionLogEntry transactionLogEntry = new TransactionLogEntry(1L, updateTime,
        new AddFilesTransaction(new ArrayList<>()));
    TransactionSerDe serDe = TransactionSerDe.forFileTransactions();
    Consumer<String> withTransaction = mock(Consumer.class);
    doNothing().when(withTransaction).accept(Mockito.<String>any());

    // Act
    transactionLogEntry.withSerialisedTransactionOrObjectKey(serDe, withTransaction, mock(Consumer.class));

    // Assert
    verify(withTransaction).accept(eq("{\"files\":[]}"));
  }

  /**
   * Test {@link TransactionLogEntry#getTransactionOrLoadFromPointer(String, TransactionBodyStore)}.
   * <p>
   * Method under test: {@link TransactionLogEntry#getTransactionOrLoadFromPointer(String, TransactionBodyStore)}
   */
  @Test
  @DisplayName("Test getTransactionOrLoadFromPointer(String, TransactionBodyStore)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "StateStoreTransaction TransactionLogEntry.getTransactionOrLoadFromPointer(String, TransactionBodyStore)"})
  void testGetTransactionOrLoadFromPointer() {
    // Arrange
    Instant updateTime = LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant();
    AddFilesTransaction transaction = new AddFilesTransaction(new ArrayList<>());
    TransactionLogEntry transactionLogEntry = new TransactionLogEntry(1L, updateTime, transaction);

    // Act and Assert
    assertSame(transaction,
        transactionLogEntry.getTransactionOrLoadFromPointer("42", new InMemoryTransactionBodyStore()));
  }
}
