package sleeper.clients.admin.properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.properties.SleeperProperties;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.table.TableProperties;

class UpdatePropertiesRequestDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link UpdatePropertiesRequest#UpdatePropertiesRequest(PropertiesDiff, SleeperProperties)}
   *   <li>{@link UpdatePropertiesRequest#getDiff()}
   *   <li>{@link UpdatePropertiesRequest#getUpdatedProperties()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void UpdatePropertiesRequest.<init>(PropertiesDiff, SleeperProperties)",
      "PropertiesDiff UpdatePropertiesRequest.getDiff()",
      "SleeperProperties UpdatePropertiesRequest.getUpdatedProperties()"})
  void testGettersAndSetters() {
    // Arrange
    PropertiesDiff diff = PropertiesDiff.noChanges();
    InstanceProperties instanceProperties = new InstanceProperties();

    // Act
    UpdatePropertiesRequest<SleeperProperties<?>> actualUpdatePropertiesRequest = new UpdatePropertiesRequest<>(diff,
        instanceProperties);
    PropertiesDiff actualDiff = actualUpdatePropertiesRequest.getDiff();

    // Assert
    assertSame(instanceProperties, actualUpdatePropertiesRequest.getUpdatedProperties());
    assertSame(diff, actualDiff);
  }

  /**
   * Test {@link UpdatePropertiesRequest#getInvalidProperties()}.
   * <ul>
   *   <li>Given noChanges {@link InstanceProperties#InstanceProperties()}.</li>
   *   <li>Then return size is six.</li>
   * </ul>
   * <p>
   * Method under test: {@link UpdatePropertiesRequest#getInvalidProperties()}
   */
  @Test
  @DisplayName("Test getInvalidProperties(); given noChanges InstanceProperties(); then return size is six")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.util.Set UpdatePropertiesRequest.getInvalidProperties()"})
  void testGetInvalidProperties_givenNoChangesInstanceProperties_thenReturnSizeIsSix() {
    // Arrange
    UpdatePropertiesRequest<SleeperProperties<?>> noChangesResult = UpdatePropertiesRequestTestHelper
        .noChanges(new InstanceProperties());

    // Act and Assert
    assertEquals(6, noChangesResult.getInvalidProperties().size());
  }

  /**
   * Test {@link UpdatePropertiesRequest#getInvalidProperties()}.
   * <ul>
   *   <li>Then return size is two.</li>
   * </ul>
   * <p>
   * Method under test: {@link UpdatePropertiesRequest#getInvalidProperties()}
   */
  @Test
  @DisplayName("Test getInvalidProperties(); then return size is two")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.util.Set UpdatePropertiesRequest.getInvalidProperties()"})
  void testGetInvalidProperties_thenReturnSizeIsTwo() {
    // Arrange
    UpdatePropertiesRequest<SleeperProperties<?>> noChangesResult = UpdatePropertiesRequestTestHelper
        .noChanges(new TableProperties(new InstanceProperties()));

    // Act and Assert
    assertEquals(2, noChangesResult.getInvalidProperties().size());
  }
}
