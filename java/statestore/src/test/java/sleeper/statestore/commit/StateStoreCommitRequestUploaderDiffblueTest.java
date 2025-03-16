package sleeper.statestore.commit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import com.amazonaws.services.s3.AmazonS3Client;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.statestore.commit.StateStoreCommitRequest;
import sleeper.core.statestore.transactionlog.transaction.TransactionSerDeProvider;
import sleeper.core.statestore.transactionlog.transaction.TransactionType;
import sleeper.statestore.transactionlog.S3TransactionBodyStore;

class StateStoreCommitRequestUploaderDiffblueTest {
  /**
   * Test {@link StateStoreCommitRequestUploader#serialiseAndUploadIfTooBig(StateStoreCommitRequest)}.
   * <p>
   * Method under test: {@link StateStoreCommitRequestUploader#serialiseAndUploadIfTooBig(StateStoreCommitRequest)}
   */
  @Test
  @DisplayName("Test serialiseAndUploadIfTooBig(StateStoreCommitRequest)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "java.lang.String StateStoreCommitRequestUploader.serialiseAndUploadIfTooBig(StateStoreCommitRequest)"})
  void testSerialiseAndUploadIfTooBig() {
    // Arrange
    InstanceProperties instanceProperties = new InstanceProperties();
    StateStoreCommitRequestUploader stateStoreCommitRequestUploader = new StateStoreCommitRequestUploader(
        new S3TransactionBodyStore(instanceProperties, new AmazonS3Client(), mock(TransactionSerDeProvider.class)),
        mock(TransactionSerDeProvider.class));

    // Act and Assert
    assertEquals("{\"tableId\":\"42\",\"transactionType\":\"ADD_FILES\",\"bodyKey\":\"Not all who wander are lost\"}",
        stateStoreCommitRequestUploader.serialiseAndUploadIfTooBig(
            StateStoreCommitRequest.create("42", "Not all who wander are lost", TransactionType.ADD_FILES)));
  }
}
