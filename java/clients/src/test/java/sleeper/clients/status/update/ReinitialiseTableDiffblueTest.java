package sleeper.clients.status.update;

import static org.junit.jupiter.api.Assertions.assertThrows;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClient;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class ReinitialiseTableDiffblueTest {
  /**
   * Test {@link ReinitialiseTable#ReinitialiseTable(AmazonS3, AmazonDynamoDB, String, String, boolean)}.
   * <ul>
   *   <li>When empty string.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReinitialiseTable#ReinitialiseTable(AmazonS3, AmazonDynamoDB, String, String, boolean)}
   */
  @Test
  @DisplayName("Test new ReinitialiseTable(AmazonS3, AmazonDynamoDB, String, String, boolean); when empty string; then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ReinitialiseTable.<init>(AmazonS3, AmazonDynamoDB, String, String, boolean)"})
  void testNewReinitialiseTable_whenEmptyString_thenThrowIllegalArgumentException() {
    // Arrange
    AmazonS3Client s3Client = new AmazonS3Client();

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> new ReinitialiseTable(s3Client, new AmazonDynamoDBAsyncClient(), "", "", true));

  }

  /**
   * Test {@link ReinitialiseTable#ReinitialiseTable(AmazonS3, AmazonDynamoDB, String, String, boolean)}.
   * <ul>
   *   <li>When empty string.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReinitialiseTable#ReinitialiseTable(AmazonS3, AmazonDynamoDB, String, String, boolean)}
   */
  @Test
  @DisplayName("Test new ReinitialiseTable(AmazonS3, AmazonDynamoDB, String, String, boolean); when empty string; then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ReinitialiseTable.<init>(AmazonS3, AmazonDynamoDB, String, String, boolean)"})
  void testNewReinitialiseTable_whenEmptyString_thenThrowIllegalArgumentException2() {
    // Arrange
    AmazonS3Client s3Client = new AmazonS3Client();

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> new ReinitialiseTable(s3Client, new AmazonDynamoDBAsyncClient(), "42", "", true));

  }
}
