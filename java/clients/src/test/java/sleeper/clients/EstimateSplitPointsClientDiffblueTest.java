package sleeper.clients;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.schema.Schema;

class EstimateSplitPointsClientDiffblueTest {
  /**
   * Test {@link EstimateSplitPointsClient#estimate(Schema, Configuration, int, int, List)}.
   * <ul>
   *   <li>Given {@link Path#Path(String)} with {@code Path String}.</li>
   *   <li>When {@link Schema}.</li>
   *   <li>Then throw {@link UncheckedIOException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link EstimateSplitPointsClient#estimate(Schema, Configuration, int, int, List)}
   */
  @Test
  @DisplayName("Test estimate(Schema, Configuration, int, int, List); given Path(String) with 'Path String'; when Schema; then throw UncheckedIOException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List EstimateSplitPointsClient.estimate(Schema, Configuration, int, int, List)"})
  void testEstimate_givenPathWithPathString_whenSchema_thenThrowUncheckedIOException()
      throws IOException, IllegalArgumentException {
    // Arrange
    Schema schema = mock(Schema.class);
    Configuration conf = new Configuration();

    ArrayList<Path> parquetPaths = new ArrayList<>();
    parquetPaths.add(new Path("Path String"));

    // Act and Assert
    assertThrows(UncheckedIOException.class,
        () -> EstimateSplitPointsClient.estimate(schema, conf, 10, 3, parquetPaths));
  }
}
