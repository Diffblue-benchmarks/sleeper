package sleeper.bulkimport.starter.executor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.properties.instance.InstanceProperties;

class EmrJarLocationDiffblueTest {
  /**
   * Test {@link EmrJarLocation#getJarLocation(InstanceProperties)}.
   * <ul>
   *   <li>Then return {@code s3a://null/bulk-import-runner-null.jar}.</li>
   * </ul>
   * <p>
   * Method under test: {@link EmrJarLocation#getJarLocation(InstanceProperties)}
   */
  @Test
  @DisplayName("Test getJarLocation(InstanceProperties); then return 's3a://null/bulk-import-runner-null.jar'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.lang.String EmrJarLocation.getJarLocation(InstanceProperties)"})
  void testGetJarLocation_thenReturnS3aNullBulkImportRunnerNullJar() {
    // Arrange, Act and Assert
    assertEquals("s3a://null/bulk-import-runner-null.jar", EmrJarLocation.getJarLocation(new InstanceProperties()));
  }
}
