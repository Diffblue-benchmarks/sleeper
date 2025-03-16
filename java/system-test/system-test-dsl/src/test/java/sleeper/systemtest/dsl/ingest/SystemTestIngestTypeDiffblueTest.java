package sleeper.systemtest.dsl.ingest;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.properties.table.TableProperty;
import sleeper.systemtest.dsl.instance.SystemTestInstanceContext;

class SystemTestIngestTypeDiffblueTest {
  /**
   * Test {@link SystemTestIngestType#applyTo(SystemTestInstanceContext)}.
   * <ul>
   *   <li>Then calls {@link SystemTestInstanceContext#updateTableProperties(Map)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SystemTestIngestType#applyTo(SystemTestInstanceContext)}
   */
  @Test
  @DisplayName("Test applyTo(SystemTestInstanceContext); then calls updateTableProperties(Map)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SystemTestIngestType.applyTo(SystemTestInstanceContext)"})
  void testApplyTo_thenCallsUpdateTableProperties() {
    // Arrange
    SystemTestIngestType asyncWriteBackedByArrowResult = SystemTestIngestType.asyncWriteBackedByArrow();
    SystemTestInstanceContext instance = mock(SystemTestInstanceContext.class);
    doNothing().when(instance).updateTableProperties(Mockito.<Map<TableProperty, String>>any());

    // Act
    asyncWriteBackedByArrowResult.applyTo(instance);

    // Assert
    verify(instance).updateTableProperties(isA(Map.class));
  }
}
