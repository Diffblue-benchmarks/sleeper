package sleeper.ingest.runner.impl;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.iterator.CloseableIterator;
import sleeper.core.iterator.ConcatenatingIterator;
import sleeper.core.partition.PartitionTree;
import sleeper.core.properties.validation.IngestFileWritingStrategy;
import sleeper.core.schema.Field;
import sleeper.core.schema.Schema;
import sleeper.core.schema.Schema.Builder;
import sleeper.core.schema.type.ByteArrayType;
import sleeper.core.statestore.FileReference;

class IngesterIntoPartitionsDiffblueTest {
  /**
   * Test {@link IngesterIntoPartitions#initiateIngest(CloseableIterator, PartitionTree)}.
   * <ul>
   *   <li>Then return {@link CompletableFuture#get()} Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngesterIntoPartitions#initiateIngest(CloseableIterator, PartitionTree)}
   */
  @Test
  @DisplayName("Test initiateIngest(CloseableIterator, PartitionTree); then return get() Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"CompletableFuture IngesterIntoPartitions.initiateIngest(CloseableIterator, PartitionTree)"})
  void testInitiateIngest_thenReturnGetEmpty() throws IOException, InterruptedException, ExecutionException {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema sleeperSchema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    IngesterIntoPartitions ingesterIntoPartitions = new IngesterIntoPartitions(sleeperSchema, mock(Function.class),
        IngestFileWritingStrategy.ONE_FILE_PER_LEAF);

    // Act
    CompletableFuture<List<FileReference>> actualInitiateIngestResult = ingesterIntoPartitions
        .initiateIngest(new ConcatenatingIterator(new ArrayList<>()), null);

    // Assert
    assertTrue(actualInitiateIngestResult.get().isEmpty());
    assertTrue(actualInitiateIngestResult.isDone());
  }

  /**
   * Test {@link IngesterIntoPartitions#ingestOneFilePerLeafPartition(CloseableIterator, PartitionTree)}.
   * <ul>
   *   <li>Then return {@link CompletableFuture#get()} Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngesterIntoPartitions#ingestOneFilePerLeafPartition(CloseableIterator, PartitionTree)}
   */
  @Test
  @DisplayName("Test ingestOneFilePerLeafPartition(CloseableIterator, PartitionTree); then return get() Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "CompletableFuture IngesterIntoPartitions.ingestOneFilePerLeafPartition(CloseableIterator, PartitionTree)"})
  void testIngestOneFilePerLeafPartition_thenReturnGetEmpty()
      throws IOException, InterruptedException, ExecutionException {
    // Arrange
    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema sleeperSchema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    IngesterIntoPartitions ingesterIntoPartitions = new IngesterIntoPartitions(sleeperSchema, mock(Function.class),
        IngestFileWritingStrategy.ONE_FILE_PER_LEAF);

    // Act
    CompletableFuture<List<FileReference>> actualIngestOneFilePerLeafPartitionResult = ingesterIntoPartitions
        .ingestOneFilePerLeafPartition(new ConcatenatingIterator(new ArrayList<>()), null);

    // Assert
    assertTrue(actualIngestOneFilePerLeafPartitionResult.get().isEmpty());
    assertTrue(actualIngestOneFilePerLeafPartitionResult.isDone());
  }
}
