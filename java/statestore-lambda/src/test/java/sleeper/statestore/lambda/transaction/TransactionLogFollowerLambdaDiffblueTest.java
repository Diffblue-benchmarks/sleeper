package sleeper.statestore.lambda.transaction;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.DynamodbEvent;
import com.amazonaws.services.lambda.runtime.events.DynamodbEvent.DynamodbStreamRecord;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class TransactionLogFollowerLambdaDiffblueTest {
  /**
   * Test {@link TransactionLogFollowerLambda#handleRequest(DynamodbEvent, Context)} with {@code DynamodbEvent}, {@code Context}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TransactionLogFollowerLambda#handleRequest(DynamodbEvent, Context)}
   */
  @Test
  @DisplayName("Test handleRequest(DynamodbEvent, Context) with 'DynamodbEvent', 'Context'; then return 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.lang.Void TransactionLogFollowerLambda.handleRequest(DynamodbEvent, Context)"})
  void testHandleRequestWithDynamodbEventContext_thenReturnNull() {
    // Arrange
    TransactionLogFollowerLambda transactionLogFollowerLambda = new TransactionLogFollowerLambda();

    ArrayList<DynamodbStreamRecord> records = new ArrayList<>();
    records.add(new DynamodbStreamRecord());

    DynamodbEvent event = new DynamodbEvent();
    event.setRecords(records);

    // Act and Assert
    assertNull(transactionLogFollowerLambda.handleRequest(event, mock(Context.class)));
  }
}
