package sleeper.systemtest.dsl.ingest;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.statestore.StateStore;
import sleeper.core.statestore.transactionlog.AddTransactionRequest;
import sleeper.systemtest.dsl.instance.SystemTestInstanceContext;
import sleeper.systemtest.dsl.sourcedata.IngestSourceFilesContext;

class SystemTestIngestToStateStoreDiffblueTest {
  /**
   * Test {@link SystemTestIngestToStateStore#addFileOnPartition(String, String, long)}.
   * <p>
   * Method under test: {@link SystemTestIngestToStateStore#addFileOnPartition(String, String, long)}
   */
  @Test
  @DisplayName("Test addFileOnPartition(String, String, long)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "SystemTestIngestToStateStore SystemTestIngestToStateStore.addFileOnPartition(String, String, long)"})
  void testAddFileOnPartition() throws Exception {
    // Arrange
    StateStore stateStore = mock(StateStore.class);
    doNothing().when(stateStore).addTransaction(Mockito.<AddTransactionRequest>any());
    SystemTestInstanceContext instance = mock(SystemTestInstanceContext.class);
    when(instance.getStateStore()).thenReturn(stateStore);
    IngestSourceFilesContext ingestSource = mock(IngestSourceFilesContext.class);
    when(ingestSource.getFilePath(Mockito.<String>any())).thenReturn("/directory/foo.txt");
    SystemTestIngestToStateStore systemTestIngestToStateStore = new SystemTestIngestToStateStore(instance,
        ingestSource);

    // Act
    SystemTestIngestToStateStore actualAddFileOnPartitionResult = systemTestIngestToStateStore
        .addFileOnPartition("Name", "42", 1L);

    // Assert
    verify(stateStore).addTransaction(isA(AddTransactionRequest.class));
    verify(instance).getStateStore();
    verify(ingestSource).getFilePath(eq("Name"));
    assertSame(systemTestIngestToStateStore, actualAddFileOnPartitionResult);
  }

  /**
   * Test {@link SystemTestIngestToStateStore#addFileWithRecordEstimatesOnPartitions(String, Map)}.
   * <p>
   * Method under test: {@link SystemTestIngestToStateStore#addFileWithRecordEstimatesOnPartitions(String, Map)}
   */
  @Test
  @DisplayName("Test addFileWithRecordEstimatesOnPartitions(String, Map)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "SystemTestIngestToStateStore SystemTestIngestToStateStore.addFileWithRecordEstimatesOnPartitions(String, Map)"})
  void testAddFileWithRecordEstimatesOnPartitions() throws Exception {
    // Arrange
    StateStore stateStore = mock(StateStore.class);
    doNothing().when(stateStore).addTransaction(Mockito.<AddTransactionRequest>any());
    SystemTestInstanceContext instance = mock(SystemTestInstanceContext.class);
    when(instance.getStateStore()).thenReturn(stateStore);
    IngestSourceFilesContext ingestSource = mock(IngestSourceFilesContext.class);
    when(ingestSource.getFilePath(Mockito.<String>any())).thenReturn("/directory/foo.txt");
    SystemTestIngestToStateStore systemTestIngestToStateStore = new SystemTestIngestToStateStore(instance,
        ingestSource);

    // Act
    SystemTestIngestToStateStore actualAddFileWithRecordEstimatesOnPartitionsResult = systemTestIngestToStateStore
        .addFileWithRecordEstimatesOnPartitions("Name", new HashMap<>());

    // Assert
    verify(stateStore).addTransaction(isA(AddTransactionRequest.class));
    verify(instance).getStateStore();
    verify(ingestSource).getFilePath(eq("Name"));
    assertSame(systemTestIngestToStateStore, actualAddFileWithRecordEstimatesOnPartitionsResult);
  }

  /**
   * Test {@link SystemTestIngestToStateStore#addFileWithRecordEstimatesOnPartitions(String, Map)}.
   * <ul>
   *   <li>Given {@code 42}.</li>
   *   <li>When {@link HashMap#HashMap()} {@code 42} is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link SystemTestIngestToStateStore#addFileWithRecordEstimatesOnPartitions(String, Map)}
   */
  @Test
  @DisplayName("Test addFileWithRecordEstimatesOnPartitions(String, Map); given '42'; when HashMap() '42' is zero")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "SystemTestIngestToStateStore SystemTestIngestToStateStore.addFileWithRecordEstimatesOnPartitions(String, Map)"})
  void testAddFileWithRecordEstimatesOnPartitions_given42_whenHashMap42IsZero() throws Exception {
    // Arrange
    StateStore stateStore = mock(StateStore.class);
    doNothing().when(stateStore).addTransaction(Mockito.<AddTransactionRequest>any());
    SystemTestInstanceContext instance = mock(SystemTestInstanceContext.class);
    when(instance.getStateStore()).thenReturn(stateStore);
    IngestSourceFilesContext ingestSource = mock(IngestSourceFilesContext.class);
    when(ingestSource.getFilePath(Mockito.<String>any())).thenReturn("/directory/foo.txt");
    SystemTestIngestToStateStore systemTestIngestToStateStore = new SystemTestIngestToStateStore(instance,
        ingestSource);

    HashMap<String, Long> recordsByPartition = new HashMap<>();
    recordsByPartition.put("42", 0L);
    recordsByPartition.put("foo", 1L);

    // Act
    SystemTestIngestToStateStore actualAddFileWithRecordEstimatesOnPartitionsResult = systemTestIngestToStateStore
        .addFileWithRecordEstimatesOnPartitions("Name", recordsByPartition);

    // Assert
    verify(stateStore).addTransaction(isA(AddTransactionRequest.class));
    verify(instance).getStateStore();
    verify(ingestSource).getFilePath(eq("Name"));
    assertSame(systemTestIngestToStateStore, actualAddFileWithRecordEstimatesOnPartitionsResult);
  }

  /**
   * Test {@link SystemTestIngestToStateStore#addFileWithRecordEstimatesOnPartitions(String, Map)}.
   * <ul>
   *   <li>Given {@code foo}.</li>
   *   <li>When {@link HashMap#HashMap()} {@code foo} is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link SystemTestIngestToStateStore#addFileWithRecordEstimatesOnPartitions(String, Map)}
   */
  @Test
  @DisplayName("Test addFileWithRecordEstimatesOnPartitions(String, Map); given 'foo'; when HashMap() 'foo' is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "SystemTestIngestToStateStore SystemTestIngestToStateStore.addFileWithRecordEstimatesOnPartitions(String, Map)"})
  void testAddFileWithRecordEstimatesOnPartitions_givenFoo_whenHashMapFooIsOne() throws Exception {
    // Arrange
    StateStore stateStore = mock(StateStore.class);
    doNothing().when(stateStore).addTransaction(Mockito.<AddTransactionRequest>any());
    SystemTestInstanceContext instance = mock(SystemTestInstanceContext.class);
    when(instance.getStateStore()).thenReturn(stateStore);
    IngestSourceFilesContext ingestSource = mock(IngestSourceFilesContext.class);
    when(ingestSource.getFilePath(Mockito.<String>any())).thenReturn("/directory/foo.txt");
    SystemTestIngestToStateStore systemTestIngestToStateStore = new SystemTestIngestToStateStore(instance,
        ingestSource);

    HashMap<String, Long> recordsByPartition = new HashMap<>();
    recordsByPartition.put("foo", 1L);

    // Act
    SystemTestIngestToStateStore actualAddFileWithRecordEstimatesOnPartitionsResult = systemTestIngestToStateStore
        .addFileWithRecordEstimatesOnPartitions("Name", recordsByPartition);

    // Assert
    verify(stateStore).addTransaction(isA(AddTransactionRequest.class));
    verify(instance).getStateStore();
    verify(ingestSource).getFilePath(eq("Name"));
    assertSame(systemTestIngestToStateStore, actualAddFileWithRecordEstimatesOnPartitionsResult);
  }
}
