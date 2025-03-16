package sleeper.systemtest.drivers.ingest.json;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class TasksSummaryJsonDiffblueTest {
  /**
   * Test {@link TasksSummaryJson#toString()}.
   * <p>
   * Method under test: {@link TasksSummaryJson#toString()}
   */
  @Test
  @DisplayName("Test toString()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.lang.String TasksSummaryJson.toString()"})
  void testToString() {
    // Arrange, Act and Assert
    assertEquals("{\n  \"numTasks\": 0,\n  \"countByDesiredStatus\": {},\n  \"countByLastStatus\": {}\n}",
        (new TasksSummaryJson(new ArrayList<>())).toString());
  }
}
