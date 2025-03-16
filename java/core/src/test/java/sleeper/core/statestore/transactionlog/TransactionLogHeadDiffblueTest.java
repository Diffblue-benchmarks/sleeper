package sleeper.core.statestore.transactionlog;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.statestore.transactionlog.TransactionLogHead.Builder;
import sleeper.core.statestore.transactionlog.state.StateStorePartitions;

class TransactionLogHeadDiffblueTest {
  /**
   * Test Builder {@link Builder#forFiles()}.
   * <p>
   * Method under test: {@link Builder#forFiles()}
   */
  @Test
  @DisplayName("Test Builder forFiles()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.forFiles()"})
  void testBuilderForFiles() {
    // Arrange
    Builder<?> builderResult = TransactionLogHead.builder();

    // Act and Assert
    assertSame(builderResult, builderResult.forFiles());
  }

  /**
   * Test Builder {@link Builder#forPartitions()}.
   * <p>
   * Method under test: {@link Builder#forPartitions()}
   */
  @Test
  @DisplayName("Test Builder forPartitions()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.forPartitions()"})
  void testBuilderForPartitions() {
    // Arrange
    Builder<?> builderResult = TransactionLogHead.builder();

    // Act
    Builder<StateStorePartitions> actualForPartitionsResult = builderResult.forPartitions();

    // Assert
    assertEquals(0L, actualForPartitionsResult.build().getLastTransactionNumber());
    assertSame(builderResult, actualForPartitionsResult);
  }
}
