package sleeper.systemtest.dsl.util;

import static org.junit.jupiter.api.Assertions.assertThrows;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.table.TableProperties;

class NoSnapshotsDriverDiffblueTest {
  /**
   * Test {@link NoSnapshotsDriver#loadLatestFilesSnapshot(InstanceProperties, TableProperties)}.
   * <ul>
   *   <li>Then throw {@link UnsupportedOperationException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NoSnapshotsDriver#loadLatestFilesSnapshot(InstanceProperties, TableProperties)}
   */
  @Test
  @DisplayName("Test loadLatestFilesSnapshot(InstanceProperties, TableProperties); then throw UnsupportedOperationException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "java.util.Optional NoSnapshotsDriver.loadLatestFilesSnapshot(InstanceProperties, TableProperties)"})
  void testLoadLatestFilesSnapshot_thenThrowUnsupportedOperationException() {
    // Arrange
    NoSnapshotsDriver noSnapshotsDriver = new NoSnapshotsDriver();
    InstanceProperties instanceProperties = new InstanceProperties();

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> noSnapshotsDriver
        .loadLatestFilesSnapshot(instanceProperties, new TableProperties(new InstanceProperties())));
  }

  /**
   * Test {@link NoSnapshotsDriver#loadLatestPartitionsSnapshot(InstanceProperties, TableProperties)}.
   * <ul>
   *   <li>Then throw {@link UnsupportedOperationException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NoSnapshotsDriver#loadLatestPartitionsSnapshot(InstanceProperties, TableProperties)}
   */
  @Test
  @DisplayName("Test loadLatestPartitionsSnapshot(InstanceProperties, TableProperties); then throw UnsupportedOperationException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "java.util.Optional NoSnapshotsDriver.loadLatestPartitionsSnapshot(InstanceProperties, TableProperties)"})
  void testLoadLatestPartitionsSnapshot_thenThrowUnsupportedOperationException() {
    // Arrange
    NoSnapshotsDriver noSnapshotsDriver = new NoSnapshotsDriver();
    InstanceProperties instanceProperties = new InstanceProperties();

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> noSnapshotsDriver
        .loadLatestPartitionsSnapshot(instanceProperties, new TableProperties(new InstanceProperties())));
  }
}
