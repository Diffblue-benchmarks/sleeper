package sleeper.clients.util;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.regions.Region;

class AssumeSleeperRoleV2DiffblueTest {
  /**
   * Test {@link AssumeSleeperRoleV2#regionProvider()}.
   * <ul>
   *   <li>Then return Region is Region {@link Region#US_EAST_2}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AssumeSleeperRoleV2#regionProvider()}
   */
  @Test
  @DisplayName("Test regionProvider(); then return Region is Region US_EAST_2")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"software.amazon.awssdk.regions.providers.AwsRegionProvider AssumeSleeperRoleV2.regionProvider()"})
  void testRegionProvider_thenReturnRegionIsRegionUs_east_2() {
    // Arrange and Act
    Region actualRegion = (new AssumeSleeperRoleV2("us-east-2", mock(AwsCredentialsProvider.class))).regionProvider()
        .getRegion();

    // Assert
    assertSame(actualRegion.US_EAST_2, actualRegion);
  }
}
