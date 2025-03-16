package sleeper.systemtest.dsl.instance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.table.TableProperties;
import sleeper.core.schema.Schema;
import sleeper.systemtest.dsl.util.SystemTestSchema;
import sleeper.systemtest.dsl.util.TestContext;

class SystemTestInstanceContextDiffblueTest {
  /**
   * Test {@link SystemTestInstanceContext#numberedRecords(Schema)} with {@code Schema}.
   * <p>
   * Method under test: {@link SystemTestInstanceContext#numberedRecords(Schema)}
   */
  @Test
  @DisplayName("Test numberedRecords(Schema) with 'Schema'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "sleeper.systemtest.dsl.sourcedata.GenerateNumberedRecords SystemTestInstanceContext.numberedRecords(Schema)"})
  void testNumberedRecordsWithSchema() {
    // Arrange, Act and Assert
    Set<String> keys = (new SystemTestInstanceContext(mock(SystemTestParameters.class),
        mock(DeployedSleeperInstances.class), mock(SleeperInstanceDriver.class), mock(TestContext.class)))
        .numberedRecords(SystemTestSchema.DEFAULT_SCHEMA)
        .generateRecord(1L)
        .getKeys();
    assertEquals(3, keys.size());
    assertTrue(keys.contains("key"));
    assertTrue(keys.contains("timestamp"));
    assertTrue(keys.contains("value"));
  }

  /**
   * Test {@link SystemTestInstanceContext#getTestTableName(TableProperties)} with {@code tableProperties}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SystemTestInstanceContext#getTestTableName(TableProperties)}
   */
  @Test
  @DisplayName("Test getTestTableName(TableProperties) with 'tableProperties'; then return 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String SystemTestInstanceContext.getTestTableName(TableProperties)"})
  void testGetTestTableNameWithTableProperties_thenReturnNull() {
    // Arrange
    SystemTestInstanceContext systemTestInstanceContext = new SystemTestInstanceContext(
        mock(SystemTestParameters.class), mock(DeployedSleeperInstances.class), mock(SleeperInstanceDriver.class),
        mock(TestContext.class));

    // Act and Assert
    assertNull(systemTestInstanceContext.getTestTableName(new TableProperties(new InstanceProperties())));
  }
}
