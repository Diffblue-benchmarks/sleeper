package sleeper.ingest.core.job;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.table.InMemoryTableIndex;
import sleeper.core.table.TableIndex;
import sleeper.core.tracker.ingest.job.InMemoryIngestJobTracker;
import sleeper.core.tracker.ingest.job.IngestJobTracker;
import sleeper.ingest.core.job.IngestJobMessageHandler.Builder;

class IngestJobMessageHandlerDiffblueTest {
  /**
   * Test {@link IngestJobMessageHandler#builder()}.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link IngestJobMessageHandler#builder()}
   *   <li>{@link IngestJobMessageHandler#applyIngestJobChanges(BiFunction)}
   *   <li>{@link IngestJobMessageHandler#deserialiser(Function)}
   *   <li>{@link IngestJobMessageHandler#expandDirectories(Function)}
   *   <li>{@link IngestJobMessageHandler#ingestJobTracker(IngestJobTracker)}
   *   <li>{@link IngestJobMessageHandler#jobIdSupplier(Supplier)}
   *   <li>{@link IngestJobMessageHandler#tableIndex(TableIndex)}
   *   <li>{@link IngestJobMessageHandler#timeSupplier(Supplier)}
   * </ul>
   */
  @Test
  @DisplayName("Test builder()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.applyIngestJobChanges(BiFunction)", "IngestJobMessageHandler Builder.build()",
      "Builder Builder.deserialiser(Function)", "Builder Builder.expandDirectories(Function)",
      "Builder Builder.ingestJobTracker(IngestJobTracker)", "Builder Builder.jobIdSupplier(Supplier)",
      "Builder Builder.tableIndex(TableIndex)", "Builder Builder.timeSupplier(Supplier)",
      "Builder Builder.toIngestJob(Function)"})
  void testBuilder() {
    // Arrange and Act
    Builder<?> actualBuilderResult = IngestJobMessageHandler.builder();
    Builder<Object> actualExpandDirectoriesResult = actualBuilderResult.applyIngestJobChanges(mock(BiFunction.class))
        .<Object>deserialiser(mock(Function.class))
        .expandDirectories(mock(Function.class));
    Builder<Object> actualJobIdSupplierResult = actualExpandDirectoriesResult
        .ingestJobTracker(new InMemoryIngestJobTracker())
        .jobIdSupplier(mock(Supplier.class));
    Builder<Object> actualTableIndexResult = actualJobIdSupplierResult.tableIndex(new InMemoryTableIndex());

    // Assert
    assertSame(actualTableIndexResult, actualTableIndexResult.timeSupplier(mock(Supplier.class)));
  }
}
