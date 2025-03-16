package sleeper.clients.status.report.filestatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.HashMap;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.clients.status.report.filestatus.TableFilesStatus.Builder;
import sleeper.core.statestore.AllReferencesToAllFiles;
import sleeper.core.statestore.FilesReportTestHelper;

class CVSFileStatusReporterDiffblueTest {
  /**
   * Test {@link CVSFileStatusReporter#report(TableFilesStatus, boolean)}.
   * <ul>
   *   <li>Then {@link CVSFileStatusReporter} (default constructor) {@link CVSFileStatusReporter#outputData} size is {@link Short#SIZE}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CVSFileStatusReporter#report(TableFilesStatus, boolean)}
   */
  @Test
  @DisplayName("Test report(TableFilesStatus, boolean); then CVSFileStatusReporter (default constructor) outputData size is SIZE")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void CVSFileStatusReporter.report(TableFilesStatus, boolean)"})
  void testReport_thenCVSFileStatusReporterOutputDataSizeIsSize() {
    // Arrange
    CVSFileStatusReporter cvsFileStatusReporter = new CVSFileStatusReporter();
    Builder builderResult = TableFilesStatus.builder();
    Builder nonLeafPartitionCountResult = builderResult.files(FilesReportTestHelper.noFilesReport())
        .leafPartitionCount(3)
        .nonLeafPartitionCount(3);
    AllReferencesToAllFiles files = FilesReportTestHelper.noFilesReport();
    TableFilesStatus status = nonLeafPartitionCountResult.statistics(TableFilesStatistics.from(files, new HashMap<>()))
        .build();

    // Act
    cvsFileStatusReporter.report(status, true);

    // Assert
    List<Object> objectList = cvsFileStatusReporter.outputData;
    assertEquals(Short.SIZE, objectList.size());
    assertNull(objectList.get(13));
    assertNull(objectList.get(14));
    assertNull(objectList.get(15));
    assertEquals(0L, ((Long) objectList.get(0)).longValue());
    assertEquals(0L, ((Long) objectList.get(1)).longValue());
    assertFalse((Boolean) objectList.get(2));
  }

  /**
   * Test new {@link CVSFileStatusReporter} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link CVSFileStatusReporter}
   */
  @Test
  @DisplayName("Test new CVSFileStatusReporter (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void CVSFileStatusReporter.<init>()"})
  void testNewCVSFileStatusReporter() {
    // Arrange, Act and Assert
    assertTrue((new CVSFileStatusReporter()).outputData.isEmpty());
  }
}
