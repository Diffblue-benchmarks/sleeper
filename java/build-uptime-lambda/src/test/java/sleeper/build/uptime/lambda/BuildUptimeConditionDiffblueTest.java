package sleeper.build.uptime.lambda;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import software.amazon.awssdk.services.s3.S3Client;

class BuildUptimeConditionDiffblueTest {
  /**
   * Test {@link BuildUptimeCondition#of(BuildUptimeEvent)}.
   * <p>
   * Method under test: {@link BuildUptimeCondition#of(BuildUptimeEvent)}
   */
  @Test
  @DisplayName("Test of(BuildUptimeEvent)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"BuildUptimeCondition BuildUptimeCondition.of(BuildUptimeEvent)"})
  void testOf() {
    // Arrange
    BuildUptimeEvent event = mock(BuildUptimeEvent.class);
    when(event.getCondition()).thenReturn("Condition");
    when(event.getTestBucket()).thenReturn("s3://bucket-name/object-key");

    // Act
    BuildUptimeCondition actualOfResult = BuildUptimeCondition.of(event);

    // Assert
    verify(event).getCondition();
    verify(event).getTestBucket();
    assertFalse(actualOfResult.check((GetS3ObjectAsString) null, null));
    assertFalse(actualOfResult.check((S3Client) null, null));
  }
}
