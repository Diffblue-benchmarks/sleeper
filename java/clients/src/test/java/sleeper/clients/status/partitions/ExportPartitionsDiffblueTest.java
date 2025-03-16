package sleeper.clients.status.partitions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.partition.Partition;
import sleeper.core.range.Region;
import sleeper.core.schema.Field;
import sleeper.core.schema.Schema;
import sleeper.core.schema.Schema.Builder;
import sleeper.core.schema.type.ByteArrayType;
import sleeper.core.statestore.StateStore;
import sleeper.core.statestore.StateStoreException;

class ExportPartitionsDiffblueTest {
  /**
   * Test {@link ExportPartitions#ExportPartitions(StateStore, Schema)}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return PartitionsAsJsonStrings Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link ExportPartitions#ExportPartitions(StateStore, Schema)}
   */
  @Test
  @DisplayName("Test new ExportPartitions(StateStore, Schema); given ArrayList(); then return PartitionsAsJsonStrings Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ExportPartitions.<init>(StateStore, Schema)"})
  void testNewExportPartitions_givenArrayList_thenReturnPartitionsAsJsonStringsEmpty() {
    // Arrange
    StateStore stateStore = mock(StateStore.class);
    Schema schema = mock(Schema.class);
    when(schema.getRowKeyFieldNames()).thenReturn(new ArrayList<>());
    when(schema.getRowKeyFields()).thenReturn(new ArrayList<>());

    // Act
    ExportPartitions actualExportPartitions = new ExportPartitions(stateStore, schema);

    // Assert
    verify(schema).getRowKeyFieldNames();
    verify(schema).getRowKeyFields();
    assertTrue(actualExportPartitions.getPartitionsAsJsonStrings().isEmpty());
  }

  /**
   * Test {@link ExportPartitions#ExportPartitions(StateStore, Schema)}.
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ExportPartitions#ExportPartitions(StateStore, Schema)}
   */
  @Test
  @DisplayName("Test new ExportPartitions(StateStore, Schema); then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ExportPartitions.<init>(StateStore, Schema)"})
  void testNewExportPartitions_thenThrowIllegalArgumentException() {
    // Arrange
    StateStore stateStore = mock(StateStore.class);
    Schema schema = mock(Schema.class);
    when(schema.getRowKeyFields()).thenThrow(new IllegalArgumentException("foo"));

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> new ExportPartitions(stateStore, schema));

    verify(schema).getRowKeyFields();
  }

  /**
   * Test {@link ExportPartitions#getPartitionsAsJsonStrings()}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@code foo}.</li>
   *   <li>Then return size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link ExportPartitions#getPartitionsAsJsonStrings()}
   */
  @Test
  @DisplayName("Test getPartitionsAsJsonStrings(); given ArrayList() add 'foo'; then return size is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List ExportPartitions.getPartitionsAsJsonStrings()"})
  void testGetPartitionsAsJsonStrings_givenArrayListAddFoo_thenReturnSizeIsOne() throws StateStoreException {
    // Arrange
    ArrayList<String> childPartitionIds = new ArrayList<>();
    childPartitionIds.add("foo");
    Partition.Builder parentPartitionIdResult = Partition.builder()
        .childPartitionIds(childPartitionIds)
        .dimension(1)
        .id("42")
        .leafPartition(true)
        .parentPartitionId("42");
    Partition buildResult = parentPartitionIdResult.region(new Region(new ArrayList<>())).build();

    ArrayList<Partition> partitionList = new ArrayList<>();
    partitionList.add(buildResult);
    StateStore stateStore = mock(StateStore.class);
    when(stateStore.getAllPartitions()).thenReturn(partitionList);

    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();

    // Act
    List<String> actualPartitionsAsJsonStrings = (new ExportPartitions(stateStore, schema))
        .getPartitionsAsJsonStrings();

    // Assert
    verify(stateStore).getAllPartitions();
    assertEquals(1, actualPartitionsAsJsonStrings.size());
    assertEquals(
        "{\"partitionId\":\"42\",\"isLeafPartition\":true,\"parentPartitionId\":\"42\",\"childPartitionIds\":[\"foo\"],\"region"
            + "\":{\"stringsBase64Encoded\":true},\"dimension\":1}",
        actualPartitionsAsJsonStrings.get(0));
  }

  /**
   * Test {@link ExportPartitions#getPartitionsAsJsonStrings()}.
   * <ul>
   *   <li>Then return Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link ExportPartitions#getPartitionsAsJsonStrings()}
   */
  @Test
  @DisplayName("Test getPartitionsAsJsonStrings(); then return Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List ExportPartitions.getPartitionsAsJsonStrings()"})
  void testGetPartitionsAsJsonStrings_thenReturnEmpty() throws StateStoreException {
    // Arrange
    StateStore stateStore = mock(StateStore.class);
    when(stateStore.getAllPartitions()).thenReturn(new ArrayList<>());

    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();

    // Act
    List<String> actualPartitionsAsJsonStrings = (new ExportPartitions(stateStore, schema))
        .getPartitionsAsJsonStrings();

    // Assert
    verify(stateStore).getAllPartitions();
    assertTrue(actualPartitionsAsJsonStrings.isEmpty());
  }

  /**
   * Test {@link ExportPartitions#getPartitionsAsJsonStrings()}.
   * <ul>
   *   <li>Then return size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link ExportPartitions#getPartitionsAsJsonStrings()}
   */
  @Test
  @DisplayName("Test getPartitionsAsJsonStrings(); then return size is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List ExportPartitions.getPartitionsAsJsonStrings()"})
  void testGetPartitionsAsJsonStrings_thenReturnSizeIsOne() throws StateStoreException {
    // Arrange
    ArrayList<Partition> partitionList = new ArrayList<>();
    Partition.Builder builderResult = Partition.builder();
    Partition.Builder parentPartitionIdResult = builderResult.childPartitionIds(new ArrayList<>())
        .dimension(1)
        .id("42")
        .leafPartition(true)
        .parentPartitionId("42");
    Partition buildResult = parentPartitionIdResult.region(new Region(new ArrayList<>())).build();
    partitionList.add(buildResult);
    StateStore stateStore = mock(StateStore.class);
    when(stateStore.getAllPartitions()).thenReturn(partitionList);

    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();

    // Act
    List<String> actualPartitionsAsJsonStrings = (new ExportPartitions(stateStore, schema))
        .getPartitionsAsJsonStrings();

    // Assert
    verify(stateStore).getAllPartitions();
    assertEquals(1, actualPartitionsAsJsonStrings.size());
    assertEquals(
        "{\"partitionId\":\"42\",\"isLeafPartition\":true,\"parentPartitionId\":\"42\",\"childPartitionIds\":[],\"region\":"
            + "{\"stringsBase64Encoded\":true},\"dimension\":1}",
        actualPartitionsAsJsonStrings.get(0));
  }

  /**
   * Test {@link ExportPartitions#getPartitionsAsJsonStrings()}.
   * <ul>
   *   <li>Then return size is two.</li>
   * </ul>
   * <p>
   * Method under test: {@link ExportPartitions#getPartitionsAsJsonStrings()}
   */
  @Test
  @DisplayName("Test getPartitionsAsJsonStrings(); then return size is two")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List ExportPartitions.getPartitionsAsJsonStrings()"})
  void testGetPartitionsAsJsonStrings_thenReturnSizeIsTwo() throws StateStoreException {
    // Arrange
    ArrayList<Partition> partitionList = new ArrayList<>();
    Partition.Builder builderResult = Partition.builder();
    Partition.Builder parentPartitionIdResult = builderResult.childPartitionIds(new ArrayList<>())
        .dimension(1)
        .id("42")
        .leafPartition(true)
        .parentPartitionId("42");
    Partition buildResult = parentPartitionIdResult.region(new Region(new ArrayList<>())).build();
    partitionList.add(buildResult);
    Partition.Builder builderResult2 = Partition.builder();
    Partition.Builder parentPartitionIdResult2 = builderResult2.childPartitionIds(new ArrayList<>())
        .dimension(1)
        .id("42")
        .leafPartition(true)
        .parentPartitionId("42");
    Partition buildResult2 = parentPartitionIdResult2.region(new Region(new ArrayList<>())).build();
    partitionList.add(buildResult2);
    StateStore stateStore = mock(StateStore.class);
    when(stateStore.getAllPartitions()).thenReturn(partitionList);

    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();

    // Act
    List<String> actualPartitionsAsJsonStrings = (new ExportPartitions(stateStore, schema))
        .getPartitionsAsJsonStrings();

    // Assert
    verify(stateStore).getAllPartitions();
    assertEquals(2, actualPartitionsAsJsonStrings.size());
    assertEquals(
        "{\"partitionId\":\"42\",\"isLeafPartition\":true,\"parentPartitionId\":\"42\",\"childPartitionIds\":[],\"region\":"
            + "{\"stringsBase64Encoded\":true},\"dimension\":1}",
        actualPartitionsAsJsonStrings.get(0));
    assertEquals(
        "{\"partitionId\":\"42\",\"isLeafPartition\":true,\"parentPartitionId\":\"42\",\"childPartitionIds\":[],\"region\":"
            + "{\"stringsBase64Encoded\":true},\"dimension\":1}",
        actualPartitionsAsJsonStrings.get(1));
  }

  /**
   * Test {@link ExportPartitions#getPartitionsAsJsonStrings()}.
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ExportPartitions#getPartitionsAsJsonStrings()}
   */
  @Test
  @DisplayName("Test getPartitionsAsJsonStrings(); then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List ExportPartitions.getPartitionsAsJsonStrings()"})
  void testGetPartitionsAsJsonStrings_thenThrowIllegalArgumentException() throws StateStoreException {
    // Arrange
    StateStore stateStore = mock(StateStore.class);
    when(stateStore.getAllPartitions()).thenThrow(new IllegalArgumentException("foo"));

    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> (new ExportPartitions(stateStore, schema)).getPartitionsAsJsonStrings());
    verify(stateStore).getAllPartitions();
  }

  /**
   * Test {@link ExportPartitions#writePartitionsToFile(String)}.
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ExportPartitions#writePartitionsToFile(String)}
   */
  @Test
  @DisplayName("Test writePartitionsToFile(String); then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ExportPartitions.writePartitionsToFile(String)"})
  void testWritePartitionsToFile_thenThrowIllegalArgumentException() throws IOException, StateStoreException {
    // Arrange
    StateStore stateStore = mock(StateStore.class);
    when(stateStore.getAllPartitions()).thenThrow(new IllegalArgumentException("foo"));

    ArrayList<Field> rowKeyFields = new ArrayList<>();
    rowKeyFields.add(new Field("Name", new ByteArrayType()));
    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);
    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> (new ExportPartitions(stateStore, schema)).writePartitionsToFile("foo.txt"));
    verify(stateStore).getAllPartitions();
  }
}
