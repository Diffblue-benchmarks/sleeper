package sleeper.core.statestore.transactionlog.transaction.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
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
import sleeper.core.schema.Field;
import sleeper.core.schema.Schema;
import sleeper.core.schema.Schema.Builder;
import sleeper.core.schema.type.ByteArrayType;
import sleeper.core.schema.type.IntType;
import sleeper.core.schema.type.LongType;
import sleeper.core.schema.type.StringType;
import sleeper.core.statestore.FileReferenceStoreQueries;
import sleeper.core.statestore.PartitionStore;
import sleeper.core.statestore.StateStore;
import sleeper.core.statestore.StateStoreException;
import sleeper.core.statestore.transactionlog.AddTransactionRequest;
import sleeper.core.statestore.transactionlog.state.StateStorePartitions;

class InitialisePartitionsTransactionDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link InitialisePartitionsTransaction#InitialisePartitionsTransaction(List)}
   *   <li>{@link InitialisePartitionsTransaction#validate(StateStorePartitions)}
   *   <li>{@link InitialisePartitionsTransaction#toString()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void InitialisePartitionsTransaction.<init>(List)",
      "String InitialisePartitionsTransaction.toString()",
      "void InitialisePartitionsTransaction.validate(StateStorePartitions)"})
  void testGettersAndSetters() {
    // Arrange and Act
    InitialisePartitionsTransaction actualInitialisePartitionsTransaction = new InitialisePartitionsTransaction(
        new ArrayList<>());
    actualInitialisePartitionsTransaction.validate(new StateStorePartitions());

    // Assert
    assertEquals("InitialisePartitionsTransaction{partitions=[]}", actualInitialisePartitionsTransaction.toString());
  }

  /**
   * Test {@link InitialisePartitionsTransaction#singlePartition(Schema)}.
   * <p>
   * Method under test: {@link InitialisePartitionsTransaction#singlePartition(Schema)}
   */
  @Test
  @DisplayName("Test singlePartition(Schema)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"InitialisePartitionsTransaction InitialisePartitionsTransaction.singlePartition(Schema)"})
  void testSinglePartition() {
    // Arrange
    ArrayList<String> stringList = new ArrayList<>();
    stringList.add("Constructing partition tree from no split points - tree will consist of one partition");

    ArrayList<Field> fieldList = new ArrayList<>();
    fieldList.add(new Field("Constructing partition tree from no split points - tree will consist of one partition",
        new IntType()));
    Schema schema = mock(Schema.class);
    when(schema.getRowKeyFieldNames()).thenReturn(stringList);
    when(schema.getRowKeyFields()).thenReturn(fieldList);

    // Act
    InitialisePartitionsTransaction actualSinglePartitionResult = InitialisePartitionsTransaction
        .singlePartition(schema);

    // Assert
    verify(schema).getRowKeyFieldNames();
    verify(schema, atLeast(1)).getRowKeyFields();
    assertFalse(actualSinglePartitionResult.isEmpty());
  }

  /**
   * Test {@link InitialisePartitionsTransaction#singlePartition(Schema)}.
   * <p>
   * Method under test: {@link InitialisePartitionsTransaction#singlePartition(Schema)}
   */
  @Test
  @DisplayName("Test singlePartition(Schema)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"InitialisePartitionsTransaction InitialisePartitionsTransaction.singlePartition(Schema)"})
  void testSinglePartition2() {
    // Arrange
    ArrayList<String> stringList = new ArrayList<>();
    stringList.add("Constructing partition tree from no split points - tree will consist of one partition");

    ArrayList<Field> fieldList = new ArrayList<>();
    fieldList.add(new Field("Constructing partition tree from no split points - tree will consist of one partition",
        new StringType()));
    Schema schema = mock(Schema.class);
    when(schema.getRowKeyFieldNames()).thenReturn(stringList);
    when(schema.getRowKeyFields()).thenReturn(fieldList);

    // Act
    InitialisePartitionsTransaction actualSinglePartitionResult = InitialisePartitionsTransaction
        .singlePartition(schema);

    // Assert
    verify(schema).getRowKeyFieldNames();
    verify(schema, atLeast(1)).getRowKeyFields();
    assertFalse(actualSinglePartitionResult.isEmpty());
  }

  /**
   * Test {@link InitialisePartitionsTransaction#singlePartition(Schema)}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return not Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link InitialisePartitionsTransaction#singlePartition(Schema)}
   */
  @Test
  @DisplayName("Test singlePartition(Schema); given ArrayList(); then return not Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"InitialisePartitionsTransaction InitialisePartitionsTransaction.singlePartition(Schema)"})
  void testSinglePartition_givenArrayList_thenReturnNotEmpty() {
    // Arrange
    Schema schema = mock(Schema.class);
    when(schema.getRowKeyFieldNames()).thenReturn(new ArrayList<>());
    when(schema.getRowKeyFields()).thenReturn(new ArrayList<>());

    // Act
    InitialisePartitionsTransaction actualSinglePartitionResult = InitialisePartitionsTransaction
        .singlePartition(schema);

    // Assert
    verify(schema).getRowKeyFieldNames();
    verify(schema, atLeast(1)).getRowKeyFields();
    assertFalse(actualSinglePartitionResult.isEmpty());
  }

  /**
   * Test {@link InitialisePartitionsTransaction#singlePartition(Schema)}.
   * <ul>
   *   <li>Then return not Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link InitialisePartitionsTransaction#singlePartition(Schema)}
   */
  @Test
  @DisplayName("Test singlePartition(Schema); then return not Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"InitialisePartitionsTransaction InitialisePartitionsTransaction.singlePartition(Schema)"})
  void testSinglePartition_thenReturnNotEmpty() {
    // Arrange
    ArrayList<String> stringList = new ArrayList<>();
    stringList.add("Constructing partition tree from no split points - tree will consist of one partition");

    ArrayList<Field> fieldList = new ArrayList<>();
    fieldList.add(new Field("Constructing partition tree from no split points - tree will consist of one partition",
        new LongType()));
    Schema schema = mock(Schema.class);
    when(schema.getRowKeyFieldNames()).thenReturn(stringList);
    when(schema.getRowKeyFields()).thenReturn(fieldList);

    // Act
    InitialisePartitionsTransaction actualSinglePartitionResult = InitialisePartitionsTransaction
        .singlePartition(schema);

    // Assert
    verify(schema).getRowKeyFieldNames();
    verify(schema, atLeast(1)).getRowKeyFields();
    assertFalse(actualSinglePartitionResult.isEmpty());
  }

  /**
   * Test {@link InitialisePartitionsTransaction#singlePartition(Schema)}.
   * <ul>
   *   <li>Then return not Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link InitialisePartitionsTransaction#singlePartition(Schema)}
   */
  @Test
  @DisplayName("Test singlePartition(Schema); then return not Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"InitialisePartitionsTransaction InitialisePartitionsTransaction.singlePartition(Schema)"})
  void testSinglePartition_thenReturnNotEmpty2() {
    // Arrange
    ArrayList<String> stringList = new ArrayList<>();
    stringList.add("Constructing partition tree from no split points - tree will consist of one partition");

    ArrayList<Field> fieldList = new ArrayList<>();
    fieldList.add(new Field("Constructing partition tree from no split points - tree will consist of one partition",
        new ByteArrayType()));
    Schema schema = mock(Schema.class);
    when(schema.getRowKeyFieldNames()).thenReturn(stringList);
    when(schema.getRowKeyFields()).thenReturn(fieldList);

    // Act
    InitialisePartitionsTransaction actualSinglePartitionResult = InitialisePartitionsTransaction
        .singlePartition(schema);

    // Assert
    verify(schema).getRowKeyFieldNames();
    verify(schema, atLeast(1)).getRowKeyFields();
    assertFalse(actualSinglePartitionResult.isEmpty());
  }

  /**
   * Test {@link InitialisePartitionsTransaction#singlePartition(Schema)}.
   * <ul>
   *   <li>Then throw {@link StateStoreException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link InitialisePartitionsTransaction#singlePartition(Schema)}
   */
  @Test
  @DisplayName("Test singlePartition(Schema); then throw StateStoreException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"InitialisePartitionsTransaction InitialisePartitionsTransaction.singlePartition(Schema)"})
  void testSinglePartition_thenThrowStateStoreException() {
    // Arrange
    Schema schema = mock(Schema.class);
    when(schema.getRowKeyFields()).thenThrow(new StateStoreException("An error occurred"));

    // Act and Assert
    assertThrows(StateStoreException.class, () -> InitialisePartitionsTransaction.singlePartition(schema));
    verify(schema).getRowKeyFields();
  }

  /**
   * Test {@link InitialisePartitionsTransaction#synchronousCommit(StateStore)}.
   * <ul>
   *   <li>Then throw {@link StateStoreException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link InitialisePartitionsTransaction#synchronousCommit(StateStore)}
   */
  @Test
  @DisplayName("Test synchronousCommit(StateStore); then throw StateStoreException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void InitialisePartitionsTransaction.synchronousCommit(StateStore)"})
  void testSynchronousCommit_thenThrowStateStoreException() throws StateStoreException {
    // Arrange
    InitialisePartitionsTransaction createResult = ClearPartitionsTransaction.create();
    StateStore stateStore = mock(StateStore.class);
    doThrow(new StateStoreException("An error occurred")).when(stateStore)
        .addPartitionsTransaction(Mockito.<AddTransactionRequest>any());

    // Act and Assert
    assertThrows(StateStoreException.class, () -> createResult.synchronousCommit(stateStore));
    verify(stateStore).addPartitionsTransaction(isA(AddTransactionRequest.class));
  }

  /**
   * Test {@link InitialisePartitionsTransaction#synchronousCommit(StateStore)}.
   * <ul>
   *   <li>When {@link StateStore} {@link PartitionStore#addPartitionsTransaction(AddTransactionRequest)} does nothing.</li>
   * </ul>
   * <p>
   * Method under test: {@link InitialisePartitionsTransaction#synchronousCommit(StateStore)}
   */
  @Test
  @DisplayName("Test synchronousCommit(StateStore); when StateStore addPartitionsTransaction(AddTransactionRequest) does nothing")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void InitialisePartitionsTransaction.synchronousCommit(StateStore)"})
  void testSynchronousCommit_whenStateStoreAddPartitionsTransactionDoesNothing() throws StateStoreException {
    // Arrange
    InitialisePartitionsTransaction createResult = ClearPartitionsTransaction.create();
    StateStore stateStore = mock(StateStore.class);
    doNothing().when(stateStore).addPartitionsTransaction(Mockito.<AddTransactionRequest>any());

    // Act
    createResult.synchronousCommit(stateStore);

    // Assert
    verify(stateStore).addPartitionsTransaction(isA(AddTransactionRequest.class));
  }

  /**
   * Test {@link InitialisePartitionsTransaction#apply(StateStorePartitions, Instant)} with {@code StateStorePartitions}, {@code Instant}.
   * <ul>
   *   <li>Then {@link StateStorePartitions} (default constructor) all Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link InitialisePartitionsTransaction#apply(StateStorePartitions, Instant)}
   */
  @Test
  @DisplayName("Test apply(StateStorePartitions, Instant) with 'StateStorePartitions', 'Instant'; then StateStorePartitions (default constructor) all Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void InitialisePartitionsTransaction.apply(StateStorePartitions, Instant)"})
  void testApplyWithStateStorePartitionsInstant_thenStateStorePartitionsAllEmpty() {
    // Arrange
    InitialisePartitionsTransaction createResult = ClearPartitionsTransaction.create();
    StateStorePartitions stateStorePartitions = new StateStorePartitions();

    // Act
    createResult.apply(stateStorePartitions,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Assert that nothing has changed
    assertTrue(stateStorePartitions.all().isEmpty());
  }

  /**
   * Test {@link InitialisePartitionsTransaction#apply(StateStorePartitions, Instant)} with {@code StateStorePartitions}, {@code Instant}.
   * <ul>
   *   <li>Then {@link StateStorePartitions} (default constructor) all size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link InitialisePartitionsTransaction#apply(StateStorePartitions, Instant)}
   */
  @Test
  @DisplayName("Test apply(StateStorePartitions, Instant) with 'StateStorePartitions', 'Instant'; then StateStorePartitions (default constructor) all size is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void InitialisePartitionsTransaction.apply(StateStorePartitions, Instant)"})
  void testApplyWithStateStorePartitionsInstant_thenStateStorePartitionsAllSizeIsOne() {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    InitialisePartitionsTransaction singlePartitionResult = InitialisePartitionsTransaction.singlePartition(schema);
    StateStorePartitions stateStorePartitions = new StateStorePartitions();

    // Act
    singlePartitionResult.apply(stateStorePartitions,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Assert
    assertEquals(1, stateStorePartitions.all().size());
  }

  /**
   * Test {@link InitialisePartitionsTransaction#checkBefore(StateStore)}.
   * <ul>
   *   <li>Given {@code false}.</li>
   *   <li>When {@link StateStore} {@link FileReferenceStoreQueries#hasNoFiles()} return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link InitialisePartitionsTransaction#checkBefore(StateStore)}
   */
  @Test
  @DisplayName("Test checkBefore(StateStore); given 'false'; when StateStore hasNoFiles() return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void InitialisePartitionsTransaction.checkBefore(StateStore)"})
  void testCheckBefore_givenFalse_whenStateStoreHasNoFilesReturnFalse() throws StateStoreException {
    // Arrange
    InitialisePartitionsTransaction createResult = ClearPartitionsTransaction.create();
    StateStore stateStore = mock(StateStore.class);
    when(stateStore.hasNoFiles()).thenReturn(false);

    // Act and Assert
    assertThrows(StateStoreException.class, () -> createResult.checkBefore(stateStore));
    verify(stateStore).hasNoFiles();
  }

  /**
   * Test {@link InitialisePartitionsTransaction#checkBefore(StateStore)}.
   * <ul>
   *   <li>Given {@link StateStoreException#StateStoreException(String)} with message is {@code An error occurred}.</li>
   * </ul>
   * <p>
   * Method under test: {@link InitialisePartitionsTransaction#checkBefore(StateStore)}
   */
  @Test
  @DisplayName("Test checkBefore(StateStore); given StateStoreException(String) with message is 'An error occurred'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void InitialisePartitionsTransaction.checkBefore(StateStore)"})
  void testCheckBefore_givenStateStoreExceptionWithMessageIsAnErrorOccurred() throws StateStoreException {
    // Arrange
    InitialisePartitionsTransaction createResult = ClearPartitionsTransaction.create();
    StateStore stateStore = mock(StateStore.class);
    when(stateStore.hasNoFiles()).thenThrow(new StateStoreException("An error occurred"));

    // Act and Assert
    assertThrows(StateStoreException.class, () -> createResult.checkBefore(stateStore));
    verify(stateStore).hasNoFiles();
  }

  /**
   * Test {@link InitialisePartitionsTransaction#checkBefore(StateStore)}.
   * <ul>
   *   <li>Given {@code true}.</li>
   *   <li>When {@link StateStore} {@link FileReferenceStoreQueries#hasNoFiles()} return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link InitialisePartitionsTransaction#checkBefore(StateStore)}
   */
  @Test
  @DisplayName("Test checkBefore(StateStore); given 'true'; when StateStore hasNoFiles() return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void InitialisePartitionsTransaction.checkBefore(StateStore)"})
  void testCheckBefore_givenTrue_whenStateStoreHasNoFilesReturnTrue() throws StateStoreException {
    // Arrange
    InitialisePartitionsTransaction createResult = ClearPartitionsTransaction.create();
    StateStore stateStore = mock(StateStore.class);
    when(stateStore.hasNoFiles()).thenReturn(true);

    // Act
    createResult.checkBefore(stateStore);

    // Assert
    verify(stateStore).hasNoFiles();
  }

  /**
   * Test {@link InitialisePartitionsTransaction#equals(Object)}, and {@link InitialisePartitionsTransaction#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link InitialisePartitionsTransaction#equals(Object)}
   *   <li>{@link InitialisePartitionsTransaction#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean InitialisePartitionsTransaction.equals(Object)",
      "int InitialisePartitionsTransaction.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    InitialisePartitionsTransaction createResult = ClearPartitionsTransaction.create();
    InitialisePartitionsTransaction createResult2 = ClearPartitionsTransaction.create();

    // Act and Assert
    assertEquals(createResult, createResult2);
    int expectedHashCodeResult = createResult.hashCode();
    assertEquals(expectedHashCodeResult, createResult2.hashCode());
  }

  /**
   * Test {@link InitialisePartitionsTransaction#equals(Object)}, and {@link InitialisePartitionsTransaction#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link InitialisePartitionsTransaction#equals(Object)}
   *   <li>{@link InitialisePartitionsTransaction#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean InitialisePartitionsTransaction.equals(Object)",
      "int InitialisePartitionsTransaction.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    InitialisePartitionsTransaction createResult = ClearPartitionsTransaction.create();

    // Act and Assert
    assertEquals(createResult, createResult);
    int expectedHashCodeResult = createResult.hashCode();
    assertEquals(expectedHashCodeResult, createResult.hashCode());
  }

  /**
   * Test {@link InitialisePartitionsTransaction#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link InitialisePartitionsTransaction#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean InitialisePartitionsTransaction.equals(Object)",
      "int InitialisePartitionsTransaction.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(ClearPartitionsTransaction.create(), 1);
  }

  /**
   * Test {@link InitialisePartitionsTransaction#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link InitialisePartitionsTransaction#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean InitialisePartitionsTransaction.equals(Object)",
      "int InitialisePartitionsTransaction.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(ClearPartitionsTransaction.create(), null);
  }

  /**
   * Test {@link InitialisePartitionsTransaction#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link InitialisePartitionsTransaction#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean InitialisePartitionsTransaction.equals(Object)",
      "int InitialisePartitionsTransaction.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(ClearPartitionsTransaction.create(), "Different type to InitialisePartitionsTransaction");
  }
}
