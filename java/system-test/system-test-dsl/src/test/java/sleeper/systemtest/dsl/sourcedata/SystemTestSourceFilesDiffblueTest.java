package sleeper.systemtest.dsl.sourcedata;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.stream.LongStream;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.record.Record;
import sleeper.core.schema.Schema;
import sleeper.systemtest.dsl.instance.DeployedSleeperInstances;
import sleeper.systemtest.dsl.instance.DeployedSystemTestResources;
import sleeper.systemtest.dsl.instance.SleeperInstanceDriver;
import sleeper.systemtest.dsl.instance.SystemTestInstanceContext;
import sleeper.systemtest.dsl.instance.SystemTestParameters;
import sleeper.systemtest.dsl.util.SystemTestSchema;
import sleeper.systemtest.dsl.util.TestContext;

class SystemTestSourceFilesDiffblueTest {
  /**
   * Test {@link SystemTestSourceFiles#inDataBucket()}.
   * <p>
   * Method under test: {@link SystemTestSourceFiles#inDataBucket()}
   */
  @Test
  @DisplayName("Test inDataBucket()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"SystemTestSourceFiles SystemTestSourceFiles.inDataBucket()"})
  void testInDataBucket() {
    // Arrange
    SystemTestInstanceContext instance = new SystemTestInstanceContext(mock(SystemTestParameters.class),
        mock(DeployedSleeperInstances.class), mock(SleeperInstanceDriver.class), mock(TestContext.class));

    SystemTestSourceFiles systemTestSourceFiles = new SystemTestSourceFiles(instance,
        new IngestSourceFilesContext(mock(DeployedSystemTestResources.class), mock(SystemTestInstanceContext.class)),
        mock(IngestSourceFilesDriver.class));

    // Act and Assert
    assertSame(systemTestSourceFiles, systemTestSourceFiles.inDataBucket());
  }

  /**
   * Test {@link SystemTestSourceFiles#createWithNumberedRecords(String, LongStream)} with {@code filename}, {@code numbers}.
   * <p>
   * Method under test: {@link SystemTestSourceFiles#createWithNumberedRecords(String, LongStream)}
   */
  @Test
  @DisplayName("Test createWithNumberedRecords(String, LongStream) with 'filename', 'numbers'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"SystemTestSourceFiles SystemTestSourceFiles.createWithNumberedRecords(String, LongStream)"})
  void testCreateWithNumberedRecordsWithFilenameNumbers() {
    // Arrange
    GenerateNumberedRecords generateNumberedRecords = mock(GenerateNumberedRecords.class);

    ArrayList<Record> resultRecordList = new ArrayList<>();
    Stream<Record> streamResult = resultRecordList.stream();
    when(generateNumberedRecords.streamFrom(Mockito.<LongStream>any())).thenReturn(streamResult);
    SystemTestInstanceContext instance = mock(SystemTestInstanceContext.class);
    when(instance.numberedRecords()).thenReturn(generateNumberedRecords);
    IngestSourceFilesContext context = mock(IngestSourceFilesContext.class);
    doNothing().when(context)
        .writeFile(Mockito.<IngestSourceFilesDriver>any(), Mockito.<String>any(), anyBoolean(),
            Mockito.<Stream<Record>>any());
    SystemTestSourceFiles systemTestSourceFiles = new SystemTestSourceFiles(instance, context,
        mock(IngestSourceFilesDriver.class));

    // Act
    SystemTestSourceFiles actualCreateWithNumberedRecordsResult = systemTestSourceFiles
        .createWithNumberedRecords("foo.txt", null);

    // Assert
    verify(instance).numberedRecords();
    verify(generateNumberedRecords).streamFrom(isNull());
    verify(context).writeFile(isA(IngestSourceFilesDriver.class), eq("foo.txt"), eq(false), isA(Stream.class));
    assertSame(systemTestSourceFiles, actualCreateWithNumberedRecordsResult);
  }

  /**
   * Test {@link SystemTestSourceFiles#createWithNumberedRecords(Schema, String, LongStream)} with {@code schema}, {@code filename}, {@code numbers}.
   * <p>
   * Method under test: {@link SystemTestSourceFiles#createWithNumberedRecords(Schema, String, LongStream)}
   */
  @Test
  @DisplayName("Test createWithNumberedRecords(Schema, String, LongStream) with 'schema', 'filename', 'numbers'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "SystemTestSourceFiles SystemTestSourceFiles.createWithNumberedRecords(Schema, String, LongStream)"})
  void testCreateWithNumberedRecordsWithSchemaFilenameNumbers() {
    // Arrange
    GenerateNumberedRecords generateNumberedRecords = mock(GenerateNumberedRecords.class);

    ArrayList<Record> resultRecordList = new ArrayList<>();
    Stream<Record> streamResult = resultRecordList.stream();
    when(generateNumberedRecords.streamFrom(Mockito.<LongStream>any())).thenReturn(streamResult);
    SystemTestInstanceContext instance = mock(SystemTestInstanceContext.class);
    when(instance.numberedRecords(Mockito.<Schema>any())).thenReturn(generateNumberedRecords);
    IngestSourceFilesContext context = mock(IngestSourceFilesContext.class);
    doNothing().when(context)
        .writeFile(Mockito.<IngestSourceFilesDriver>any(), Mockito.<Schema>any(), Mockito.<String>any(), anyBoolean(),
            Mockito.<Stream<Record>>any());
    SystemTestSourceFiles systemTestSourceFiles = new SystemTestSourceFiles(instance, context,
        mock(IngestSourceFilesDriver.class));

    // Act
    SystemTestSourceFiles actualCreateWithNumberedRecordsResult = systemTestSourceFiles
        .createWithNumberedRecords(SystemTestSchema.DEFAULT_SCHEMA, "foo.txt", null);

    // Assert
    verify(instance).numberedRecords(isA(Schema.class));
    verify(generateNumberedRecords).streamFrom(isNull());
    verify(context).writeFile(isA(IngestSourceFilesDriver.class), isA(Schema.class), eq("foo.txt"), eq(false),
        isA(Stream.class));
    assertSame(systemTestSourceFiles, actualCreateWithNumberedRecordsResult);
  }
}
