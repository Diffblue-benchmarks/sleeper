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
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.partition.Partition;
import sleeper.core.partition.Partition.Builder;
import sleeper.core.range.Region;
import sleeper.core.statestore.PartitionStore;
import sleeper.core.statestore.StateStore;
import sleeper.core.statestore.StateStoreException;
import sleeper.core.statestore.transactionlog.AddTransactionRequest;
import sleeper.core.statestore.transactionlog.state.StateStorePartitions;

class SplitPartitionTransactionDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link SplitPartitionTransaction#SplitPartitionTransaction(Partition, List)}
   *   <li>{@link SplitPartitionTransaction#toString()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SplitPartitionTransaction.<init>(Partition, List)",
      "String SplitPartitionTransaction.toString()"})
  void testGettersAndSetters() {
    // Arrange
    Builder builderResult = Partition.builder();
    Builder parentPartitionIdResult = builderResult.childPartitionIds(new ArrayList<>())
        .dimension(1)
        .id("42")
        .leafPartition(true)
        .parentPartitionId("42");
    Partition parent = parentPartitionIdResult.region(new Region(new ArrayList<>())).build();

    // Act and Assert
    assertEquals(
        "SplitPartitionTransaction{parent=Partition{region=Region{rowKeyFieldNameToRange={}}, id='42',"
            + " leafPartition=true, parentPartitionId='42', childPartitionIds=[], dimension=1}, newChildren=[]}",
        (new SplitPartitionTransaction(parent, new ArrayList<>())).toString());
  }

  /**
   * Test {@link SplitPartitionTransaction#synchronousCommit(StateStore)}.
   * <ul>
   *   <li>Then throw {@link StateStoreException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SplitPartitionTransaction#synchronousCommit(StateStore)}
   */
  @Test
  @DisplayName("Test synchronousCommit(StateStore); then throw StateStoreException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SplitPartitionTransaction.synchronousCommit(StateStore)"})
  void testSynchronousCommit_thenThrowStateStoreException() {
    // Arrange
    Builder builderResult = Partition.builder();
    Builder parentPartitionIdResult = builderResult.childPartitionIds(new ArrayList<>())
        .dimension(1)
        .id("42")
        .leafPartition(true)
        .parentPartitionId("42");
    Partition parent = parentPartitionIdResult.region(new Region(new ArrayList<>())).build();
    SplitPartitionTransaction splitPartitionTransaction = new SplitPartitionTransaction(parent, new ArrayList<>());
    StateStore stateStore = mock(StateStore.class);
    doThrow(new StateStoreException("An error occurred")).when(stateStore)
        .addPartitionsTransaction(Mockito.<AddTransactionRequest>any());

    // Act and Assert
    assertThrows(StateStoreException.class, () -> splitPartitionTransaction.synchronousCommit(stateStore));
    verify(stateStore).addPartitionsTransaction(isA(AddTransactionRequest.class));
  }

  /**
   * Test {@link SplitPartitionTransaction#synchronousCommit(StateStore)}.
   * <ul>
   *   <li>When {@link StateStore} {@link PartitionStore#addPartitionsTransaction(AddTransactionRequest)} does nothing.</li>
   * </ul>
   * <p>
   * Method under test: {@link SplitPartitionTransaction#synchronousCommit(StateStore)}
   */
  @Test
  @DisplayName("Test synchronousCommit(StateStore); when StateStore addPartitionsTransaction(AddTransactionRequest) does nothing")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SplitPartitionTransaction.synchronousCommit(StateStore)"})
  void testSynchronousCommit_whenStateStoreAddPartitionsTransactionDoesNothing() {
    // Arrange
    Builder builderResult = Partition.builder();
    Builder parentPartitionIdResult = builderResult.childPartitionIds(new ArrayList<>())
        .dimension(1)
        .id("42")
        .leafPartition(true)
        .parentPartitionId("42");
    Partition parent = parentPartitionIdResult.region(new Region(new ArrayList<>())).build();
    SplitPartitionTransaction splitPartitionTransaction = new SplitPartitionTransaction(parent, new ArrayList<>());
    StateStore stateStore = mock(StateStore.class);
    doNothing().when(stateStore).addPartitionsTransaction(Mockito.<AddTransactionRequest>any());

    // Act
    splitPartitionTransaction.synchronousCommit(stateStore);

    // Assert
    verify(stateStore).addPartitionsTransaction(isA(AddTransactionRequest.class));
  }

  /**
   * Test {@link SplitPartitionTransaction#validate(StateStorePartitions)} with {@code StateStorePartitions}.
   * <p>
   * Method under test: {@link SplitPartitionTransaction#validate(StateStorePartitions)}
   */
  @Test
  @DisplayName("Test validate(StateStorePartitions) with 'StateStorePartitions'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SplitPartitionTransaction.validate(StateStorePartitions)"})
  void testValidateWithStateStorePartitions() throws StateStoreException {
    // Arrange
    Builder builderResult = Partition.builder();
    Builder parentPartitionIdResult = builderResult.childPartitionIds(new ArrayList<>())
        .dimension(1)
        .id("42")
        .leafPartition(true)
        .parentPartitionId("42");
    Partition parent = parentPartitionIdResult.region(new Region(new ArrayList<>())).build();
    SplitPartitionTransaction splitPartitionTransaction = new SplitPartitionTransaction(parent, new ArrayList<>());

    StateStorePartitions stateStorePartitions = new StateStorePartitions();
    Builder builderResult2 = Partition.builder();
    Builder parentPartitionIdResult2 = builderResult2.childPartitionIds(new ArrayList<>())
        .dimension(1)
        .id("42")
        .leafPartition(false)
        .parentPartitionId("42");
    Partition partition = parentPartitionIdResult2.region(new Region(new ArrayList<>())).build();
    stateStorePartitions.put(partition);

    // Act and Assert
    assertThrows(StateStoreException.class, () -> splitPartitionTransaction.validate(stateStorePartitions));
  }

  /**
   * Test {@link SplitPartitionTransaction#validate(StateStorePartitions)} with {@code StateStorePartitions}.
   * <p>
   * Method under test: {@link SplitPartitionTransaction#validate(StateStorePartitions)}
   */
  @Test
  @DisplayName("Test validate(StateStorePartitions) with 'StateStorePartitions'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SplitPartitionTransaction.validate(StateStorePartitions)"})
  void testValidateWithStateStorePartitions2() throws StateStoreException {
    // Arrange
    ArrayList<String> childPartitionIds = new ArrayList<>();
    childPartitionIds.add("");
    Builder parentPartitionIdResult = Partition.builder()
        .childPartitionIds(childPartitionIds)
        .dimension(1)
        .id("42")
        .leafPartition(true)
        .parentPartitionId("42");
    Partition parent = parentPartitionIdResult.region(new Region(new ArrayList<>())).build();

    ArrayList<Partition> newChildren = new ArrayList<>();
    Builder builderResult = Partition.builder();
    Builder parentPartitionIdResult2 = builderResult.childPartitionIds(new ArrayList<>())
        .dimension(1)
        .id("")
        .leafPartition(false)
        .parentPartitionId("42");
    Partition buildResult = parentPartitionIdResult2.region(new Region(new ArrayList<>())).build();
    newChildren.add(buildResult);
    SplitPartitionTransaction splitPartitionTransaction = new SplitPartitionTransaction(parent, newChildren);

    StateStorePartitions stateStorePartitions = new StateStorePartitions();
    Builder builderResult2 = Partition.builder();
    Builder parentPartitionIdResult3 = builderResult2.childPartitionIds(new ArrayList<>())
        .dimension(1)
        .id("42")
        .leafPartition(true)
        .parentPartitionId("42");
    Partition partition = parentPartitionIdResult3.region(new Region(new ArrayList<>())).build();
    stateStorePartitions.put(partition);

    // Act and Assert
    assertThrows(StateStoreException.class, () -> splitPartitionTransaction.validate(stateStorePartitions));
  }

  /**
   * Test {@link SplitPartitionTransaction#validate(StateStorePartitions)} with {@code StateStorePartitions}.
   * <p>
   * Method under test: {@link SplitPartitionTransaction#validate(StateStorePartitions)}
   */
  @Test
  @DisplayName("Test validate(StateStorePartitions) with 'StateStorePartitions'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SplitPartitionTransaction.validate(StateStorePartitions)"})
  void testValidateWithStateStorePartitions3() throws StateStoreException {
    // Arrange
    ArrayList<String> childPartitionIds = new ArrayList<>();
    childPartitionIds.add("");
    Builder parentPartitionIdResult = Partition.builder()
        .childPartitionIds(childPartitionIds)
        .dimension(1)
        .id("42")
        .leafPartition(true)
        .parentPartitionId("42");
    Partition parent = parentPartitionIdResult.region(new Region(new ArrayList<>())).build();

    ArrayList<Partition> newChildren = new ArrayList<>();
    Builder builderResult = Partition.builder();
    Builder parentPartitionIdResult2 = builderResult.childPartitionIds(new ArrayList<>())
        .dimension(1)
        .id("")
        .leafPartition(true)
        .parentPartitionId("Parent Partition Id");
    Partition buildResult = parentPartitionIdResult2.region(new Region(new ArrayList<>())).build();
    newChildren.add(buildResult);
    SplitPartitionTransaction splitPartitionTransaction = new SplitPartitionTransaction(parent, newChildren);

    StateStorePartitions stateStorePartitions = new StateStorePartitions();
    Builder builderResult2 = Partition.builder();
    Builder parentPartitionIdResult3 = builderResult2.childPartitionIds(new ArrayList<>())
        .dimension(1)
        .id("42")
        .leafPartition(true)
        .parentPartitionId("42");
    Partition partition = parentPartitionIdResult3.region(new Region(new ArrayList<>())).build();
    stateStorePartitions.put(partition);

    // Act and Assert
    assertThrows(StateStoreException.class, () -> splitPartitionTransaction.validate(stateStorePartitions));
  }

  /**
   * Test {@link SplitPartitionTransaction#validate(StateStorePartitions)} with {@code StateStorePartitions}.
   * <p>
   * Method under test: {@link SplitPartitionTransaction#validate(StateStorePartitions)}
   */
  @Test
  @DisplayName("Test validate(StateStorePartitions) with 'StateStorePartitions'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SplitPartitionTransaction.validate(StateStorePartitions)"})
  void testValidateWithStateStorePartitions4() throws StateStoreException {
    // Arrange
    ArrayList<String> childPartitionIds = new ArrayList<>();
    childPartitionIds.add("");
    Builder parentPartitionIdResult = Partition.builder()
        .childPartitionIds(childPartitionIds)
        .dimension(1)
        .id("42")
        .leafPartition(true)
        .parentPartitionId("42");
    Partition parent = parentPartitionIdResult.region(new Region(new ArrayList<>())).build();

    ArrayList<Partition> newChildren = new ArrayList<>();
    Builder builderResult = Partition.builder();
    Builder parentPartitionIdResult2 = builderResult.childPartitionIds(new ArrayList<>())
        .dimension(1)
        .id("")
        .leafPartition(true)
        .parentPartitionId("42");
    Partition buildResult = parentPartitionIdResult2.region(new Region(new ArrayList<>())).build();
    newChildren.add(buildResult);
    SplitPartitionTransaction splitPartitionTransaction = new SplitPartitionTransaction(parent, newChildren);

    StateStorePartitions stateStorePartitions = new StateStorePartitions();
    Builder builderResult2 = Partition.builder();
    Builder parentPartitionIdResult3 = builderResult2.childPartitionIds(new ArrayList<>())
        .dimension(1)
        .id("")
        .leafPartition(true)
        .parentPartitionId("42");
    Partition partition = parentPartitionIdResult3.region(new Region(new ArrayList<>())).build();
    stateStorePartitions.put(partition);
    Builder builderResult3 = Partition.builder();
    Builder parentPartitionIdResult4 = builderResult3.childPartitionIds(new ArrayList<>())
        .dimension(1)
        .id("42")
        .leafPartition(true)
        .parentPartitionId("42");
    Partition partition2 = parentPartitionIdResult4.region(new Region(new ArrayList<>())).build();
    stateStorePartitions.put(partition2);

    // Act and Assert
    assertThrows(StateStoreException.class, () -> splitPartitionTransaction.validate(stateStorePartitions));
  }

  /**
   * Test {@link SplitPartitionTransaction#validate(StateStorePartitions)} with {@code StateStorePartitions}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link SplitPartitionTransaction#validate(StateStorePartitions)}
   */
  @Test
  @DisplayName("Test validate(StateStorePartitions) with 'StateStorePartitions'; given ArrayList() add empty string")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SplitPartitionTransaction.validate(StateStorePartitions)"})
  void testValidateWithStateStorePartitions_givenArrayListAddEmptyString() throws StateStoreException {
    // Arrange
    ArrayList<String> childPartitionIds = new ArrayList<>();
    childPartitionIds.add("");
    Builder parentPartitionIdResult = Partition.builder()
        .childPartitionIds(childPartitionIds)
        .dimension(1)
        .id("42")
        .leafPartition(true)
        .parentPartitionId("42");
    Partition parent = parentPartitionIdResult.region(new Region(new ArrayList<>())).build();
    SplitPartitionTransaction splitPartitionTransaction = new SplitPartitionTransaction(parent, new ArrayList<>());

    StateStorePartitions stateStorePartitions = new StateStorePartitions();
    Builder builderResult = Partition.builder();
    Builder parentPartitionIdResult2 = builderResult.childPartitionIds(new ArrayList<>())
        .dimension(1)
        .id("42")
        .leafPartition(true)
        .parentPartitionId("42");
    Partition partition = parentPartitionIdResult2.region(new Region(new ArrayList<>())).build();
    stateStorePartitions.put(partition);

    // Act and Assert
    assertThrows(StateStoreException.class, () -> splitPartitionTransaction.validate(stateStorePartitions));
  }

  /**
   * Test {@link SplitPartitionTransaction#validate(StateStorePartitions)} with {@code StateStorePartitions}.
   * <ul>
   *   <li>When {@link StateStorePartitions} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link SplitPartitionTransaction#validate(StateStorePartitions)}
   */
  @Test
  @DisplayName("Test validate(StateStorePartitions) with 'StateStorePartitions'; when StateStorePartitions (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SplitPartitionTransaction.validate(StateStorePartitions)"})
  void testValidateWithStateStorePartitions_whenStateStorePartitions() throws StateStoreException {
    // Arrange
    Builder builderResult = Partition.builder();
    Builder parentPartitionIdResult = builderResult.childPartitionIds(new ArrayList<>())
        .dimension(1)
        .id("42")
        .leafPartition(true)
        .parentPartitionId("42");
    Partition parent = parentPartitionIdResult.region(new Region(new ArrayList<>())).build();
    SplitPartitionTransaction splitPartitionTransaction = new SplitPartitionTransaction(parent, new ArrayList<>());

    // Act and Assert
    assertThrows(StateStoreException.class, () -> splitPartitionTransaction.validate(new StateStorePartitions()));
  }

  /**
   * Test {@link SplitPartitionTransaction#apply(StateStorePartitions, Instant)} with {@code StateStorePartitions}, {@code Instant}.
   * <p>
   * Method under test: {@link SplitPartitionTransaction#apply(StateStorePartitions, Instant)}
   */
  @Test
  @DisplayName("Test apply(StateStorePartitions, Instant) with 'StateStorePartitions', 'Instant'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SplitPartitionTransaction.apply(StateStorePartitions, Instant)"})
  void testApplyWithStateStorePartitionsInstant() {
    // Arrange
    ArrayList<Partition> newChildren = new ArrayList<>();
    Builder builderResult = Partition.builder();
    Builder parentPartitionIdResult = builderResult.childPartitionIds(new ArrayList<>())
        .dimension(1)
        .id("42")
        .leafPartition(true)
        .parentPartitionId("42");
    Partition buildResult = parentPartitionIdResult.region(new Region(new ArrayList<>())).build();
    newChildren.add(buildResult);
    Builder builderResult2 = Partition.builder();
    Builder parentPartitionIdResult2 = builderResult2.childPartitionIds(new ArrayList<>())
        .dimension(1)
        .id("42")
        .leafPartition(true)
        .parentPartitionId("42");
    Partition parent = parentPartitionIdResult2.region(new Region(new ArrayList<>())).build();
    SplitPartitionTransaction splitPartitionTransaction = new SplitPartitionTransaction(parent, newChildren);
    StateStorePartitions stateStorePartitions = new StateStorePartitions();

    // Act
    splitPartitionTransaction.apply(stateStorePartitions,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Assert
    assertEquals(1, stateStorePartitions.all().size());
  }

  /**
   * Test {@link SplitPartitionTransaction#apply(StateStorePartitions, Instant)} with {@code StateStorePartitions}, {@code Instant}.
   * <p>
   * Method under test: {@link SplitPartitionTransaction#apply(StateStorePartitions, Instant)}
   */
  @Test
  @DisplayName("Test apply(StateStorePartitions, Instant) with 'StateStorePartitions', 'Instant'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SplitPartitionTransaction.apply(StateStorePartitions, Instant)"})
  void testApplyWithStateStorePartitionsInstant2() {
    // Arrange
    ArrayList<Partition> newChildren = new ArrayList<>();
    Builder builderResult = Partition.builder();
    Builder parentPartitionIdResult = builderResult.childPartitionIds(new ArrayList<>())
        .dimension(1)
        .id("42")
        .leafPartition(true)
        .parentPartitionId("42");
    Partition buildResult = parentPartitionIdResult.region(new Region(new ArrayList<>())).build();
    newChildren.add(buildResult);
    Builder builderResult2 = Partition.builder();
    Builder parentPartitionIdResult2 = builderResult2.childPartitionIds(new ArrayList<>())
        .dimension(1)
        .id("42")
        .leafPartition(true)
        .parentPartitionId("42");
    Partition buildResult2 = parentPartitionIdResult2.region(new Region(new ArrayList<>())).build();
    newChildren.add(buildResult2);
    Builder builderResult3 = Partition.builder();
    Builder parentPartitionIdResult3 = builderResult3.childPartitionIds(new ArrayList<>())
        .dimension(1)
        .id("42")
        .leafPartition(true)
        .parentPartitionId("42");
    Partition parent = parentPartitionIdResult3.region(new Region(new ArrayList<>())).build();
    SplitPartitionTransaction splitPartitionTransaction = new SplitPartitionTransaction(parent, newChildren);
    StateStorePartitions stateStorePartitions = new StateStorePartitions();

    // Act
    splitPartitionTransaction.apply(stateStorePartitions,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Assert
    assertEquals(1, stateStorePartitions.all().size());
  }

  /**
   * Test {@link SplitPartitionTransaction#apply(StateStorePartitions, Instant)} with {@code StateStorePartitions}, {@code Instant}.
   * <ul>
   *   <li>Then {@link StateStorePartitions} (default constructor) all size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link SplitPartitionTransaction#apply(StateStorePartitions, Instant)}
   */
  @Test
  @DisplayName("Test apply(StateStorePartitions, Instant) with 'StateStorePartitions', 'Instant'; then StateStorePartitions (default constructor) all size is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SplitPartitionTransaction.apply(StateStorePartitions, Instant)"})
  void testApplyWithStateStorePartitionsInstant_thenStateStorePartitionsAllSizeIsOne() {
    // Arrange
    Builder builderResult = Partition.builder();
    Builder parentPartitionIdResult = builderResult.childPartitionIds(new ArrayList<>())
        .dimension(1)
        .id("42")
        .leafPartition(true)
        .parentPartitionId("42");
    Partition parent = parentPartitionIdResult.region(new Region(new ArrayList<>())).build();
    SplitPartitionTransaction splitPartitionTransaction = new SplitPartitionTransaction(parent, new ArrayList<>());
    StateStorePartitions stateStorePartitions = new StateStorePartitions();

    // Act
    splitPartitionTransaction.apply(stateStorePartitions,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Assert
    assertEquals(1, stateStorePartitions.all().size());
  }

  /**
   * Test {@link SplitPartitionTransaction#equals(Object)}, and {@link SplitPartitionTransaction#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link SplitPartitionTransaction#equals(Object)}
   *   <li>{@link SplitPartitionTransaction#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SplitPartitionTransaction.equals(Object)", "int SplitPartitionTransaction.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    Builder builderResult = Partition.builder();
    Builder parentPartitionIdResult = builderResult.childPartitionIds(new ArrayList<>())
        .dimension(1)
        .id("42")
        .leafPartition(true)
        .parentPartitionId("42");
    Partition parent = parentPartitionIdResult.region(new Region(new ArrayList<>())).build();
    SplitPartitionTransaction splitPartitionTransaction = new SplitPartitionTransaction(parent, new ArrayList<>());
    Builder builderResult2 = Partition.builder();
    Builder parentPartitionIdResult2 = builderResult2.childPartitionIds(new ArrayList<>())
        .dimension(1)
        .id("42")
        .leafPartition(true)
        .parentPartitionId("42");
    Partition parent2 = parentPartitionIdResult2.region(new Region(new ArrayList<>())).build();
    SplitPartitionTransaction splitPartitionTransaction2 = new SplitPartitionTransaction(parent2, new ArrayList<>());

    // Act and Assert
    assertEquals(splitPartitionTransaction, splitPartitionTransaction2);
    int expectedHashCodeResult = splitPartitionTransaction.hashCode();
    assertEquals(expectedHashCodeResult, splitPartitionTransaction2.hashCode());
  }

  /**
   * Test {@link SplitPartitionTransaction#equals(Object)}, and {@link SplitPartitionTransaction#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link SplitPartitionTransaction#equals(Object)}
   *   <li>{@link SplitPartitionTransaction#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SplitPartitionTransaction.equals(Object)", "int SplitPartitionTransaction.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    Builder builderResult = Partition.builder();
    Builder parentPartitionIdResult = builderResult.childPartitionIds(new ArrayList<>())
        .dimension(1)
        .id("42")
        .leafPartition(true)
        .parentPartitionId("42");
    Partition parent = parentPartitionIdResult.region(new Region(new ArrayList<>())).build();
    SplitPartitionTransaction splitPartitionTransaction = new SplitPartitionTransaction(parent, new ArrayList<>());

    // Act and Assert
    assertEquals(splitPartitionTransaction, splitPartitionTransaction);
    int expectedHashCodeResult = splitPartitionTransaction.hashCode();
    assertEquals(expectedHashCodeResult, splitPartitionTransaction.hashCode());
  }

  /**
   * Test {@link SplitPartitionTransaction#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link SplitPartitionTransaction#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SplitPartitionTransaction.equals(Object)", "int SplitPartitionTransaction.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    ArrayList<String> childPartitionIds = new ArrayList<>();
    childPartitionIds.add("foo");
    Builder parentPartitionIdResult = Partition.builder()
        .childPartitionIds(childPartitionIds)
        .dimension(1)
        .id("42")
        .leafPartition(true)
        .parentPartitionId("42");
    Partition parent = parentPartitionIdResult.region(new Region(new ArrayList<>())).build();
    SplitPartitionTransaction splitPartitionTransaction = new SplitPartitionTransaction(parent, new ArrayList<>());
    Builder builderResult = Partition.builder();
    Builder parentPartitionIdResult2 = builderResult.childPartitionIds(new ArrayList<>())
        .dimension(1)
        .id("42")
        .leafPartition(true)
        .parentPartitionId("42");
    Partition parent2 = parentPartitionIdResult2.region(new Region(new ArrayList<>())).build();

    // Act and Assert
    assertNotEquals(splitPartitionTransaction, new SplitPartitionTransaction(parent2, new ArrayList<>()));
  }

  /**
   * Test {@link SplitPartitionTransaction#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link SplitPartitionTransaction#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SplitPartitionTransaction.equals(Object)", "int SplitPartitionTransaction.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    ArrayList<Partition> newChildren = new ArrayList<>();
    Builder builderResult = Partition.builder();
    Builder parentPartitionIdResult = builderResult.childPartitionIds(new ArrayList<>())
        .dimension(1)
        .id("42")
        .leafPartition(true)
        .parentPartitionId("42");
    Partition buildResult = parentPartitionIdResult.region(new Region(new ArrayList<>())).build();
    newChildren.add(buildResult);
    Builder builderResult2 = Partition.builder();
    Builder parentPartitionIdResult2 = builderResult2.childPartitionIds(new ArrayList<>())
        .dimension(1)
        .id("42")
        .leafPartition(true)
        .parentPartitionId("42");
    Partition parent = parentPartitionIdResult2.region(new Region(new ArrayList<>())).build();
    SplitPartitionTransaction splitPartitionTransaction = new SplitPartitionTransaction(parent, newChildren);
    Builder builderResult3 = Partition.builder();
    Builder parentPartitionIdResult3 = builderResult3.childPartitionIds(new ArrayList<>())
        .dimension(1)
        .id("42")
        .leafPartition(true)
        .parentPartitionId("42");
    Partition parent2 = parentPartitionIdResult3.region(new Region(new ArrayList<>())).build();

    // Act and Assert
    assertNotEquals(splitPartitionTransaction, new SplitPartitionTransaction(parent2, new ArrayList<>()));
  }

  /**
   * Test {@link SplitPartitionTransaction#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link SplitPartitionTransaction#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SplitPartitionTransaction.equals(Object)", "int SplitPartitionTransaction.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange
    Builder builderResult = Partition.builder();
    Builder parentPartitionIdResult = builderResult.childPartitionIds(new ArrayList<>())
        .dimension(1)
        .id("42")
        .leafPartition(true)
        .parentPartitionId("42");
    Partition parent = parentPartitionIdResult.region(new Region(new ArrayList<>())).build();

    // Act and Assert
    assertNotEquals(new SplitPartitionTransaction(parent, new ArrayList<>()), null);
  }

  /**
   * Test {@link SplitPartitionTransaction#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link SplitPartitionTransaction#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SplitPartitionTransaction.equals(Object)", "int SplitPartitionTransaction.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange
    Builder builderResult = Partition.builder();
    Builder parentPartitionIdResult = builderResult.childPartitionIds(new ArrayList<>())
        .dimension(1)
        .id("42")
        .leafPartition(true)
        .parentPartitionId("42");
    Partition parent = parentPartitionIdResult.region(new Region(new ArrayList<>())).build();

    // Act and Assert
    assertNotEquals(new SplitPartitionTransaction(parent, new ArrayList<>()),
        "Different type to SplitPartitionTransaction");
  }
}
