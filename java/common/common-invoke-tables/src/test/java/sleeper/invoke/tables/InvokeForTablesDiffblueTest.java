package sleeper.invoke.tables;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.SendMessageBatchRequest;
import com.amazonaws.services.sqs.model.SendMessageBatchResult;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.table.InMemoryTableIndex;
import sleeper.core.table.TableIndex;
import sleeper.core.table.TableStatus;

class InvokeForTablesDiffblueTest {
  /**
   * Test {@link InvokeForTables#sendOneMessagePerTable(AmazonSQS, String, Stream)}.
   * <ul>
   *   <li>Given {@link SendMessageBatchResult} (default constructor).</li>
   *   <li>Then calls {@link AmazonSQS#sendMessageBatch(SendMessageBatchRequest)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link InvokeForTables#sendOneMessagePerTable(AmazonSQS, String, Stream)}
   */
  @Test
  @DisplayName("Test sendOneMessagePerTable(AmazonSQS, String, Stream); given SendMessageBatchResult (default constructor); then calls sendMessageBatch(SendMessageBatchRequest)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void InvokeForTables.sendOneMessagePerTable(AmazonSQS, String, Stream)"})
  void testSendOneMessagePerTable_givenSendMessageBatchResult_thenCallsSendMessageBatch() {
    // Arrange
    AmazonSQS sqsClient = mock(AmazonSQS.class);
    when(sqsClient.sendMessageBatch(Mockito.<SendMessageBatchRequest>any())).thenReturn(new SendMessageBatchResult());

    ArrayList<TableStatus> tableStatusList = new ArrayList<>();
    tableStatusList.add(TableStatus.uniqueIdAndName("42", "Table Name", true));
    Stream<TableStatus> tables = tableStatusList.stream();

    // Act
    InvokeForTables.sendOneMessagePerTable(sqsClient, "https://example.org/example", tables);

    // Assert
    verify(sqsClient).sendMessageBatch(isA(SendMessageBatchRequest.class));
  }

  /**
   * Test {@link InvokeForTables#sendOneMessagePerTableByName(AmazonSQS, String, TableIndex, List)}.
   * <ul>
   *   <li>Then calls {@link AmazonSQS#sendMessageBatch(SendMessageBatchRequest)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link InvokeForTables#sendOneMessagePerTableByName(AmazonSQS, String, TableIndex, List)}
   */
  @Test
  @DisplayName("Test sendOneMessagePerTableByName(AmazonSQS, String, TableIndex, List); then calls sendMessageBatch(SendMessageBatchRequest)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void InvokeForTables.sendOneMessagePerTableByName(AmazonSQS, String, TableIndex, List)"})
  void testSendOneMessagePerTableByName_thenCallsSendMessageBatch() {
    // Arrange
    AmazonSQS sqsClient = mock(AmazonSQS.class);
    when(sqsClient.sendMessageBatch(Mockito.<SendMessageBatchRequest>any())).thenReturn(new SendMessageBatchResult());
    InMemoryTableIndex tableIndex = mock(InMemoryTableIndex.class);
    Optional<TableStatus> ofResult = Optional.of(TableStatus.uniqueIdAndName("42", "Table Name", true));
    when(tableIndex.getTableByName(Mockito.<String>any())).thenReturn(ofResult);

    ArrayList<String> tableNames = new ArrayList<>();
    tableNames.add("foo");

    // Act
    InvokeForTables.sendOneMessagePerTableByName(sqsClient, "https://example.org/example", tableIndex, tableNames);

    // Assert
    verify(sqsClient).sendMessageBatch(isA(SendMessageBatchRequest.class));
    verify(tableIndex).getTableByName(eq("foo"));
  }

  /**
   * Test {@link InvokeForTables#sendOneMessagePerTableByName(AmazonSQS, String, TableIndex, List)}.
   * <ul>
   *   <li>Then calls {@link AmazonSQS#sendMessageBatch(SendMessageBatchRequest)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link InvokeForTables#sendOneMessagePerTableByName(AmazonSQS, String, TableIndex, List)}
   */
  @Test
  @DisplayName("Test sendOneMessagePerTableByName(AmazonSQS, String, TableIndex, List); then calls sendMessageBatch(SendMessageBatchRequest)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void InvokeForTables.sendOneMessagePerTableByName(AmazonSQS, String, TableIndex, List)"})
  void testSendOneMessagePerTableByName_thenCallsSendMessageBatch2() {
    // Arrange
    AmazonSQS sqsClient = mock(AmazonSQS.class);
    when(sqsClient.sendMessageBatch(Mockito.<SendMessageBatchRequest>any())).thenReturn(new SendMessageBatchResult());
    InMemoryTableIndex tableIndex = mock(InMemoryTableIndex.class);
    Optional<TableStatus> ofResult = Optional.of(TableStatus.uniqueIdAndName("42", "Table Name", true));
    when(tableIndex.getTableByName(Mockito.<String>any())).thenReturn(ofResult);

    ArrayList<String> tableNames = new ArrayList<>();
    tableNames.add("foo");
    tableNames.add("foo");

    // Act
    InvokeForTables.sendOneMessagePerTableByName(sqsClient, "https://example.org/example", tableIndex, tableNames);

    // Assert
    verify(sqsClient).sendMessageBatch(isA(SendMessageBatchRequest.class));
    verify(tableIndex, atLeast(1)).getTableByName(eq("foo"));
  }
}
