package sleeper.ingest.batcher.submitter;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import com.amazonaws.services.lambda.runtime.events.SQSEvent.SQSMessage;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import org.apache.hadoop.conf.Configuration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.table.InMemoryTableIndex;
import sleeper.ingest.batcher.core.testutil.InMemoryIngestBatcherStore;

class IngestBatcherSubmitterLambdaDiffblueTest {
  /**
   * Test {@link IngestBatcherSubmitterLambda#IngestBatcherSubmitterLambda()}.
   * <p>
   * Method under test: {@link IngestBatcherSubmitterLambda#IngestBatcherSubmitterLambda()}
   */
  @Test
  @DisplayName("Test new IngestBatcherSubmitterLambda()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void IngestBatcherSubmitterLambda.<init>()"})
  void testNewIngestBatcherSubmitterLambda() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> new IngestBatcherSubmitterLambda());
  }

  /**
   * Test {@link IngestBatcherSubmitterLambda#handleRequest(SQSEvent, Context)} with {@code SQSEvent}, {@code Context}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link SQSEvent.SQSMessage} (default constructor).</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestBatcherSubmitterLambda#handleRequest(SQSEvent, Context)}
   */
  @Test
  @DisplayName("Test handleRequest(SQSEvent, Context) with 'SQSEvent', 'Context'; given ArrayList() add SQSMessage (default constructor); then return 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.lang.Void IngestBatcherSubmitterLambda.handleRequest(SQSEvent, Context)"})
  void testHandleRequestWithSQSEventContext_givenArrayListAddSQSMessage_thenReturnNull() {
    // Arrange
    InMemoryIngestBatcherStore store = new InMemoryIngestBatcherStore();
    InstanceProperties instanceProperties = new InstanceProperties();
    InMemoryTableIndex tableIndex = new InMemoryTableIndex();
    IngestBatcherSubmitterLambda ingestBatcherSubmitterLambda = new IngestBatcherSubmitterLambda(store,
        instanceProperties, tableIndex, new Configuration());

    ArrayList<SQSMessage> records = new ArrayList<>();
    records.add(new SQSMessage());

    SQSEvent input = new SQSEvent();
    input.setRecords(records);

    // Act and Assert
    assertNull(ingestBatcherSubmitterLambda.handleRequest(input, mock(Context.class)));
  }

  /**
   * Test {@link IngestBatcherSubmitterLambda#handleRequest(SQSEvent, Context)} with {@code SQSEvent}, {@code Context}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link SQSEvent.SQSMessage} (default constructor).</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestBatcherSubmitterLambda#handleRequest(SQSEvent, Context)}
   */
  @Test
  @DisplayName("Test handleRequest(SQSEvent, Context) with 'SQSEvent', 'Context'; given ArrayList() add SQSMessage (default constructor); then return 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.lang.Void IngestBatcherSubmitterLambda.handleRequest(SQSEvent, Context)"})
  void testHandleRequestWithSQSEventContext_givenArrayListAddSQSMessage_thenReturnNull2() {
    // Arrange
    InMemoryIngestBatcherStore store = new InMemoryIngestBatcherStore();
    InstanceProperties instanceProperties = new InstanceProperties();
    InMemoryTableIndex tableIndex = new InMemoryTableIndex();
    IngestBatcherSubmitterLambda ingestBatcherSubmitterLambda = new IngestBatcherSubmitterLambda(store,
        instanceProperties, tableIndex, new Configuration());

    ArrayList<SQSMessage> records = new ArrayList<>();
    records.add(new SQSMessage());
    records.add(new SQSMessage());

    SQSEvent input = new SQSEvent();
    input.setRecords(records);

    // Act and Assert
    assertNull(ingestBatcherSubmitterLambda.handleRequest(input, mock(Context.class)));
  }

  /**
   * Test {@link IngestBatcherSubmitterLambda#handleRequest(SQSEvent, Context)} with {@code SQSEvent}, {@code Context}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestBatcherSubmitterLambda#handleRequest(SQSEvent, Context)}
   */
  @Test
  @DisplayName("Test handleRequest(SQSEvent, Context) with 'SQSEvent', 'Context'; given ArrayList(); then return 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.lang.Void IngestBatcherSubmitterLambda.handleRequest(SQSEvent, Context)"})
  void testHandleRequestWithSQSEventContext_givenArrayList_thenReturnNull() {
    // Arrange
    InMemoryIngestBatcherStore store = new InMemoryIngestBatcherStore();
    InstanceProperties instanceProperties = new InstanceProperties();
    InMemoryTableIndex tableIndex = new InMemoryTableIndex();
    IngestBatcherSubmitterLambda ingestBatcherSubmitterLambda = new IngestBatcherSubmitterLambda(store,
        instanceProperties, tableIndex, new Configuration());

    SQSEvent input = new SQSEvent();
    input.setRecords(new ArrayList<>());

    // Act and Assert
    assertNull(ingestBatcherSubmitterLambda.handleRequest(input, mock(Context.class)));
  }

  /**
   * Test {@link IngestBatcherSubmitterLambda#handleRequest(SQSEvent, Context)} with {@code SQSEvent}, {@code Context}.
   * <ul>
   *   <li>Given {@link SQSEvent.SQSMessage} (default constructor) Body is {@code Not all who wander are lost}.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestBatcherSubmitterLambda#handleRequest(SQSEvent, Context)}
   */
  @Test
  @DisplayName("Test handleRequest(SQSEvent, Context) with 'SQSEvent', 'Context'; given SQSMessage (default constructor) Body is 'Not all who wander are lost'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.lang.Void IngestBatcherSubmitterLambda.handleRequest(SQSEvent, Context)"})
  void testHandleRequestWithSQSEventContext_givenSQSMessageBodyIsNotAllWhoWanderAreLost() {
    // Arrange
    InMemoryIngestBatcherStore store = new InMemoryIngestBatcherStore();
    InstanceProperties instanceProperties = new InstanceProperties();
    InMemoryTableIndex tableIndex = new InMemoryTableIndex();
    IngestBatcherSubmitterLambda ingestBatcherSubmitterLambda = new IngestBatcherSubmitterLambda(store,
        instanceProperties, tableIndex, new Configuration());

    SQSMessage sqsMessage = new SQSMessage();
    sqsMessage.setBody("Not all who wander are lost");

    ArrayList<SQSMessage> records = new ArrayList<>();
    records.add(sqsMessage);

    SQSEvent input = new SQSEvent();
    input.setRecords(records);

    // Act and Assert
    assertNull(ingestBatcherSubmitterLambda.handleRequest(input, mock(Context.class)));
  }
}
