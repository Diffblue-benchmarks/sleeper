package sleeper.systemtest.dsl.ingest;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.LongStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.record.Record;
import sleeper.systemtest.dsl.instance.DeployedSleeperInstances;
import sleeper.systemtest.dsl.instance.SleeperInstanceDriver;
import sleeper.systemtest.dsl.instance.SystemTestInstanceContext;
import sleeper.systemtest.dsl.instance.SystemTestParameters;
import sleeper.systemtest.dsl.sourcedata.GenerateNumberedRecords;
import sleeper.systemtest.dsl.util.TestContext;

class SystemTestDirectIngestDiffblueTest {
  /**
   * Test {@link SystemTestDirectIngest#numberedRecords(LongStream)}.
   * <p>
   * Method under test: {@link SystemTestDirectIngest#numberedRecords(LongStream)}
   */
  @Test
  @DisplayName("Test numberedRecords(LongStream)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"SystemTestDirectIngest SystemTestDirectIngest.numberedRecords(LongStream)"})
  void testNumberedRecords() {
    // Arrange
    GenerateNumberedRecords generateNumberedRecords = mock(GenerateNumberedRecords.class);

    ArrayList<Record> resultRecordList = new ArrayList<>();
    when(generateNumberedRecords.iteratorFrom(Mockito.<LongStream>any())).thenReturn(resultRecordList.iterator());
    SystemTestInstanceContext instance = mock(SystemTestInstanceContext.class);
    when(instance.numberedRecords()).thenReturn(generateNumberedRecords);
    DirectIngestDriver driver = mock(DirectIngestDriver.class);
    doNothing().when(driver).ingest(Mockito.<Path>any(), Mockito.<Iterator<Record>>any());
    SystemTestDirectIngest systemTestDirectIngest = new SystemTestDirectIngest(instance, driver,
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));

    // Act
    SystemTestDirectIngest actualNumberedRecordsResult = systemTestDirectIngest.numberedRecords(null);

    // Assert
    verify(driver).ingest(isA(Path.class), isA(Iterator.class));
    verify(instance).numberedRecords();
    verify(generateNumberedRecords).iteratorFrom(isNull());
    assertSame(systemTestDirectIngest, actualNumberedRecordsResult);
  }

  /**
   * Test {@link SystemTestDirectIngest#records(Record[])}.
   * <ul>
   *   <li>When {@link Record#Record()}.</li>
   *   <li>Then calls {@link DirectIngestDriver#ingest(Path, Iterator)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SystemTestDirectIngest#records(Record[])}
   */
  @Test
  @DisplayName("Test records(Record[]); when Record(); then calls ingest(Path, Iterator)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SystemTestDirectIngest.records(Record[])"})
  void testRecords_whenRecord_thenCallsIngest() {
    // Arrange
    DirectIngestDriver driver = mock(DirectIngestDriver.class);
    doNothing().when(driver).ingest(Mockito.<Path>any(), Mockito.<Iterator<Record>>any());
    SystemTestDirectIngest systemTestDirectIngest = new SystemTestDirectIngest(
        new SystemTestInstanceContext(mock(SystemTestParameters.class), mock(DeployedSleeperInstances.class),
            mock(SleeperInstanceDriver.class), mock(TestContext.class)),
        driver, Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));

    // Act
    systemTestDirectIngest.records(new Record());

    // Assert
    verify(driver).ingest(isA(Path.class), isA(Iterator.class));
  }
}
