package sleeper.core.statestore.transactionlog.log;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.properties.table.TableProperties;
import sleeper.core.properties.table.TableProperty;
import sleeper.core.statestore.commit.StateStoreCommitRequest;
import sleeper.core.statestore.testutils.InMemoryTransactionBodyStore;
import sleeper.core.statestore.transactionlog.transaction.StateStoreTransaction;
import sleeper.core.statestore.transactionlog.transaction.impl.AddFilesTransaction;

class TransactionBodyStoreDiffblueTest {
  /**
   * Test {@link TransactionBodyStore#getTransaction(StateStoreCommitRequest)}.
   * <ul>
   *   <li>Then return {@link AddFilesTransaction#AddFilesTransaction(List)} with files is {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TransactionBodyStore#getTransaction(StateStoreCommitRequest)}
   */
  @Test
  @DisplayName("Test getTransaction(StateStoreCommitRequest); then return AddFilesTransaction(List) with files is ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"StateStoreTransaction TransactionBodyStore.getTransaction(StateStoreCommitRequest)"})
  void testGetTransaction_thenReturnAddFilesTransactionWithFilesIsArrayList() {
    // Arrange
    InMemoryTransactionBodyStore inMemoryTransactionBodyStore = new InMemoryTransactionBodyStore();
    StateStoreCommitRequest request = mock(StateStoreCommitRequest.class);
    AddFilesTransaction addFilesTransaction = new AddFilesTransaction(new ArrayList<>());
    Optional<StateStoreTransaction<?>> ofResult = Optional.of(addFilesTransaction);
    Mockito.<Optional<StateStoreTransaction<?>>>when(request.getTransactionIfHeld()).thenReturn(ofResult);

    // Act
    StateStoreTransaction<?> actualTransaction = inMemoryTransactionBodyStore.getTransaction(request);

    // Assert
    verify(request).getTransactionIfHeld();
    assertSame(addFilesTransaction, actualTransaction);
  }

  /**
   * Test {@link TransactionBodyStore#createObjectKey(String, Instant, String)} with {@code tableId}, {@code now}, {@code uuid}.
   * <p>
   * Method under test: {@link TransactionBodyStore#createObjectKey(String, Instant, String)}
   */
  @Test
  @DisplayName("Test createObjectKey(String, Instant, String) with 'tableId', 'now', 'uuid'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String TransactionBodyStore.createObjectKey(String, Instant, String)"})
  void testCreateObjectKeyWithTableIdNowUuid() {
    // Arrange, Act and Assert
    assertEquals("42/statestore/transactions/1970-01-01T00:00:00Z-01234567-89AB-CDEF-FEDC-BA9876543210.json",
        TransactionBodyStore.createObjectKey("42",
            LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
            "01234567-89AB-CDEF-FEDC-BA9876543210"));
  }

  /**
   * Test {@link TransactionBodyStore#createObjectKey(TableProperties)} with {@code tableProperties}.
   * <ul>
   *   <li>Given {@code Get}.</li>
   *   <li>Then calls {@link TableProperties#get(TableProperty)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TransactionBodyStore#createObjectKey(TableProperties)}
   */
  @Test
  @DisplayName("Test createObjectKey(TableProperties) with 'tableProperties'; given 'Get'; then calls get(TableProperty)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String TransactionBodyStore.createObjectKey(TableProperties)"})
  void testCreateObjectKeyWithTableProperties_givenGet_thenCallsGet() {
    // Arrange
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.get(Mockito.<TableProperty>any())).thenReturn("Get");

    // Act
    TransactionBodyStore.createObjectKey(tableProperties);

    // Assert
    verify(tableProperties).get(isA(TableProperty.class));
  }
}
