package sleeper.systemtest.dsl.ingest;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.bulkimport.core.job.BulkImportJob;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.table.TableProperties;
import sleeper.core.properties.table.TableProperty;
import sleeper.core.util.PollWithRetries;
import sleeper.systemtest.dsl.instance.DeployedSleeperInstances;
import sleeper.systemtest.dsl.instance.DeployedSystemTestResources;
import sleeper.systemtest.dsl.instance.SleeperInstanceDriver;
import sleeper.systemtest.dsl.instance.SystemTestInstanceContext;
import sleeper.systemtest.dsl.instance.SystemTestParameters;
import sleeper.systemtest.dsl.sourcedata.IngestSourceFilesContext;
import sleeper.systemtest.dsl.util.TestContext;
import sleeper.systemtest.dsl.util.WaitForJobs;

class SystemTestDirectBulkImportDiffblueTest {
  /**
   * Test {@link SystemTestDirectBulkImport#sendSourceFiles(String[])}.
   * <p>
   * Method under test: {@link SystemTestDirectBulkImport#sendSourceFiles(String[])}
   */
  @Test
  @DisplayName("Test sendSourceFiles(String[])")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"SystemTestDirectBulkImport SystemTestDirectBulkImport.sendSourceFiles(String[])"})
  void testSendSourceFiles() {
    // Arrange
    SystemTestInstanceContext instance = mock(SystemTestInstanceContext.class);
    when(instance.getTableProperties()).thenReturn(new TableProperties(new InstanceProperties()));
    DeployedSystemTestResources systemTest = mock(DeployedSystemTestResources.class);
    when(systemTest.getSystemTestBucketName()).thenReturn("bucket-name");
    IngestSourceFilesContext sourceFiles = new IngestSourceFilesContext(systemTest,
        mock(SystemTestInstanceContext.class));

    DirectBulkImportDriver driver = mock(DirectBulkImportDriver.class);
    doNothing().when(driver).sendJob(Mockito.<BulkImportJob>any());
    SystemTestDirectBulkImport systemTestDirectBulkImport = new SystemTestDirectBulkImport(instance, sourceFiles,
        driver, null);

    // Act
    SystemTestDirectBulkImport actualSendSourceFilesResult = systemTestDirectBulkImport.sendSourceFiles("Files");

    // Assert
    verify(driver).sendJob(isA(BulkImportJob.class));
    verify(systemTest).getSystemTestBucketName();
    verify(instance).getTableProperties();
    assertSame(systemTestDirectBulkImport, actualSendSourceFilesResult);
  }

  /**
   * Test {@link SystemTestDirectBulkImport#sendSourceFiles(String[])}.
   * <p>
   * Method under test: {@link SystemTestDirectBulkImport#sendSourceFiles(String[])}
   */
  @Test
  @DisplayName("Test sendSourceFiles(String[])")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"SystemTestDirectBulkImport SystemTestDirectBulkImport.sendSourceFiles(String[])"})
  void testSendSourceFiles2() {
    // Arrange
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.get(Mockito.<TableProperty>any())).thenReturn("Get");
    SystemTestInstanceContext instance = mock(SystemTestInstanceContext.class);
    when(instance.getTableProperties()).thenReturn(tableProperties);
    IngestSourceFilesContext sourceFiles = mock(IngestSourceFilesContext.class);
    when(sourceFiles.getIngestJobFilesInBucket(Mockito.<Stream<String>>any())).thenReturn(new ArrayList<>());
    DirectBulkImportDriver driver = mock(DirectBulkImportDriver.class);
    doNothing().when(driver).sendJob(Mockito.<BulkImportJob>any());
    SystemTestDirectBulkImport systemTestDirectBulkImport = new SystemTestDirectBulkImport(instance, sourceFiles,
        driver, null);

    // Act
    SystemTestDirectBulkImport actualSendSourceFilesResult = systemTestDirectBulkImport.sendSourceFiles("Files");

    // Assert
    verify(tableProperties, atLeast(1)).get(Mockito.<TableProperty>any());
    verify(driver).sendJob(isA(BulkImportJob.class));
    verify(instance).getTableProperties();
    verify(sourceFiles).getIngestJobFilesInBucket(isA(Stream.class));
    assertSame(systemTestDirectBulkImport, actualSendSourceFilesResult);
  }

  /**
   * Test {@link SystemTestDirectBulkImport#sendSourceFiles(String[])}.
   * <p>
   * Method under test: {@link SystemTestDirectBulkImport#sendSourceFiles(String[])}
   */
  @Test
  @DisplayName("Test sendSourceFiles(String[])")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"SystemTestDirectBulkImport SystemTestDirectBulkImport.sendSourceFiles(String[])"})
  void testSendSourceFiles3() {
    // Arrange
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.get(Mockito.<TableProperty>any())).thenReturn("Get");
    SystemTestInstanceContext instance = mock(SystemTestInstanceContext.class);
    when(instance.getTableProperties()).thenReturn(tableProperties);
    DirectBulkImportDriver driver = mock(DirectBulkImportDriver.class);
    doNothing().when(driver).sendJob(Mockito.<BulkImportJob>any());
    SystemTestDirectBulkImport systemTestDirectBulkImport = new SystemTestDirectBulkImport(instance,
        new IngestSourceFilesContext(mock(DeployedSystemTestResources.class), mock(SystemTestInstanceContext.class)),
        driver, null);

    // Act
    SystemTestDirectBulkImport actualSendSourceFilesResult = systemTestDirectBulkImport.sendSourceFiles();

    // Assert
    verify(tableProperties, atLeast(1)).get(Mockito.<TableProperty>any());
    verify(driver).sendJob(isA(BulkImportJob.class));
    verify(instance).getTableProperties();
    assertSame(systemTestDirectBulkImport, actualSendSourceFilesResult);
  }

  /**
   * Test {@link SystemTestDirectBulkImport#sendSourceFiles(String[])}.
   * <ul>
   *   <li>Then calls {@link DeployedSystemTestResources#getSystemTestBucketName()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SystemTestDirectBulkImport#sendSourceFiles(String[])}
   */
  @Test
  @DisplayName("Test sendSourceFiles(String[]); then calls getSystemTestBucketName()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"SystemTestDirectBulkImport SystemTestDirectBulkImport.sendSourceFiles(String[])"})
  void testSendSourceFiles_thenCallsGetSystemTestBucketName() {
    // Arrange
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.get(Mockito.<TableProperty>any())).thenReturn("Get");
    SystemTestInstanceContext instance = mock(SystemTestInstanceContext.class);
    when(instance.getTableProperties()).thenReturn(tableProperties);
    DeployedSystemTestResources systemTest = mock(DeployedSystemTestResources.class);
    when(systemTest.getSystemTestBucketName()).thenReturn("bucket-name");
    IngestSourceFilesContext sourceFiles = new IngestSourceFilesContext(systemTest,
        mock(SystemTestInstanceContext.class));

    DirectBulkImportDriver driver = mock(DirectBulkImportDriver.class);
    doNothing().when(driver).sendJob(Mockito.<BulkImportJob>any());
    SystemTestDirectBulkImport systemTestDirectBulkImport = new SystemTestDirectBulkImport(instance, sourceFiles,
        driver, null);

    // Act
    SystemTestDirectBulkImport actualSendSourceFilesResult = systemTestDirectBulkImport.sendSourceFiles("Files");

    // Assert
    verify(tableProperties, atLeast(1)).get(Mockito.<TableProperty>any());
    verify(driver).sendJob(isA(BulkImportJob.class));
    verify(systemTest).getSystemTestBucketName();
    verify(instance).getTableProperties();
    assertSame(systemTestDirectBulkImport, actualSendSourceFilesResult);
  }

  /**
   * Test {@link SystemTestDirectBulkImport#sendSourceFiles(String[])}.
   * <ul>
   *   <li>When {@code Files} and {@code 42}.</li>
   *   <li>Then calls {@link DeployedSystemTestResources#getSystemTestBucketName()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SystemTestDirectBulkImport#sendSourceFiles(String[])}
   */
  @Test
  @DisplayName("Test sendSourceFiles(String[]); when 'Files' and '42'; then calls getSystemTestBucketName()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"SystemTestDirectBulkImport SystemTestDirectBulkImport.sendSourceFiles(String[])"})
  void testSendSourceFiles_whenFilesAnd42_thenCallsGetSystemTestBucketName() {
    // Arrange
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.get(Mockito.<TableProperty>any())).thenReturn("Get");
    SystemTestInstanceContext instance = mock(SystemTestInstanceContext.class);
    when(instance.getTableProperties()).thenReturn(tableProperties);
    DeployedSystemTestResources systemTest = mock(DeployedSystemTestResources.class);
    when(systemTest.getSystemTestBucketName()).thenReturn("bucket-name");
    IngestSourceFilesContext sourceFiles = new IngestSourceFilesContext(systemTest,
        mock(SystemTestInstanceContext.class));

    DirectBulkImportDriver driver = mock(DirectBulkImportDriver.class);
    doNothing().when(driver).sendJob(Mockito.<BulkImportJob>any());
    SystemTestDirectBulkImport systemTestDirectBulkImport = new SystemTestDirectBulkImport(instance, sourceFiles,
        driver, null);

    // Act
    SystemTestDirectBulkImport actualSendSourceFilesResult = systemTestDirectBulkImport.sendSourceFiles("Files", "42");

    // Assert
    verify(tableProperties, atLeast(1)).get(Mockito.<TableProperty>any());
    verify(driver).sendJob(isA(BulkImportJob.class));
    verify(systemTest, atLeast(1)).getSystemTestBucketName();
    verify(instance).getTableProperties();
    assertSame(systemTestDirectBulkImport, actualSendSourceFilesResult);
  }

  /**
   * Test {@link SystemTestDirectBulkImport#waitForJobs(PollWithRetries)}.
   * <p>
   * Method under test: {@link SystemTestDirectBulkImport#waitForJobs(PollWithRetries)}
   */
  @Test
  @DisplayName("Test waitForJobs(PollWithRetries)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SystemTestDirectBulkImport.waitForJobs(PollWithRetries)"})
  void testWaitForJobs() {
    // Arrange
    WaitForJobs waitForJobs = mock(WaitForJobs.class);
    doNothing().when(waitForJobs).waitForJobs(Mockito.<Collection<String>>any(), Mockito.<PollWithRetries>any());
    SystemTestInstanceContext instance = new SystemTestInstanceContext(mock(SystemTestParameters.class),
        mock(DeployedSleeperInstances.class), mock(SleeperInstanceDriver.class), mock(TestContext.class));

    // Act
    (new SystemTestDirectBulkImport(instance,
        new IngestSourceFilesContext(mock(DeployedSystemTestResources.class), mock(SystemTestInstanceContext.class)),
        mock(DirectBulkImportDriver.class), waitForJobs)).waitForJobs(mock(PollWithRetries.class));

    // Assert
    verify(waitForJobs).waitForJobs(isA(Collection.class), isA(PollWithRetries.class));
  }
}
