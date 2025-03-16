package sleeper.systemtest.dsl.sourcedata;

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
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.table.TableProperties;
import sleeper.core.record.Record;
import sleeper.systemtest.dsl.instance.SystemTestInstanceContext;

class SystemTestLocalFilesDiffblueTest {
  /**
   * Test {@link SystemTestLocalFiles#createWithNumberedRecords(String, LongStream)}.
   * <ul>
   *   <li>Then calls {@link SystemTestInstanceContext#getTableProperties()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SystemTestLocalFiles#createWithNumberedRecords(String, LongStream)}
   */
  @Test
  @DisplayName("Test createWithNumberedRecords(String, LongStream); then calls getTableProperties()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SystemTestLocalFiles.createWithNumberedRecords(String, LongStream)"})
  void testCreateWithNumberedRecords_thenCallsGetTableProperties() {
    // Arrange
    GenerateNumberedRecords generateNumberedRecords = mock(GenerateNumberedRecords.class);

    ArrayList<Record> resultRecordList = new ArrayList<>();
    when(generateNumberedRecords.iteratorFrom(Mockito.<LongStream>any())).thenReturn(resultRecordList.iterator());
    SystemTestInstanceContext instance = mock(SystemTestInstanceContext.class);
    when(instance.getTableProperties()).thenReturn(new TableProperties(new InstanceProperties()));
    when(instance.numberedRecords()).thenReturn(generateNumberedRecords);
    IngestLocalFilesDriver driver = mock(IngestLocalFilesDriver.class);
    doNothing().when(driver)
        .writeFile(Mockito.<TableProperties>any(), Mockito.<Path>any(), Mockito.<Iterator<Record>>any());

    // Act
    (new SystemTestLocalFiles(instance, driver, Paths.get(System.getProperty("java.io.tmpdir"), "test.txt")))
        .createWithNumberedRecords("File", null);

    // Assert
    verify(instance).getTableProperties();
    verify(instance).numberedRecords();
    verify(generateNumberedRecords).iteratorFrom(isNull());
    verify(driver).writeFile(isA(TableProperties.class), isA(Path.class), isA(Iterator.class));
  }
}
