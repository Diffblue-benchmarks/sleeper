package sleeper.systemtest.drivers.ingest;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.properties.table.TableProperties;
import sleeper.core.properties.table.TableProperty;
import sleeper.core.record.Record;
import sleeper.core.schema.Field;
import sleeper.core.schema.Schema;
import sleeper.core.schema.Schema.Builder;
import sleeper.core.schema.type.ByteArrayType;

class HadoopLocalFilesDriverDiffblueTest {
  /**
   * Test {@link HadoopLocalFilesDriver#writeFile(TableProperties, Path, Iterator)}.
   * <ul>
   *   <li>Given {@link IOException#IOException(String)} with {@code /}.</li>
   *   <li>Then throw {@link UncheckedIOException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link HadoopLocalFilesDriver#writeFile(TableProperties, Path, Iterator)}
   */
  @Test
  @DisplayName("Test writeFile(TableProperties, Path, Iterator); given IOException(String) with '/'; then throw UncheckedIOException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void HadoopLocalFilesDriver.writeFile(TableProperties, Path, Iterator)"})
  void testWriteFile_givenIOExceptionWithSlash_thenThrowUncheckedIOException() {
    // Arrange
    HadoopLocalFilesDriver hadoopLocalFilesDriver = new HadoopLocalFilesDriver();

    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema buildResult = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();
    TableProperties tableProperties = mock(TableProperties.class);
    when(tableProperties.get(Mockito.<TableProperty>any())).thenThrow(new UncheckedIOException(new IOException("/")));
    when(tableProperties.getSchema()).thenReturn(buildResult);
    Path filePath = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    ArrayList<Record> resultRecordList = new ArrayList<>();

    // Act and Assert
    assertThrows(UncheckedIOException.class,
        () -> hadoopLocalFilesDriver.writeFile(tableProperties, filePath, resultRecordList.iterator()));
    verify(tableProperties).get(isA(TableProperty.class));
    verify(tableProperties).getSchema();
  }
}
