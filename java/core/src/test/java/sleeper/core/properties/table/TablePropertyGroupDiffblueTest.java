package sleeper.core.properties.table;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.properties.PropertyGroup;

class TablePropertyGroupDiffblueTest {
  /**
   * Test {@link TablePropertyGroup#getAll()}.
   * <p>
   * Method under test: {@link TablePropertyGroup#getAll()}
   */
  @Test
  @DisplayName("Test getAll()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List TablePropertyGroup.getAll()"})
  void testGetAll() {
    // Arrange and Act
    List<PropertyGroup> actualAll = TablePropertyGroup.getAll();

    // Assert
    assertEquals(9, actualAll.size());
    PropertyGroup getResult = actualAll.get(6);
    assertEquals("Bulk Import", getResult.getName());
    PropertyGroup getResult2 = actualAll.get(3);
    assertEquals("Compaction", getResult2.getName());
    PropertyGroup getResult3 = actualAll.get(0);
    assertEquals("Data Definition", getResult3.getName());
    PropertyGroup getResult4 = actualAll.get(2);
    assertEquals("Data Storage", getResult4.getName());
    PropertyGroup getResult5 = actualAll.get(7);
    assertEquals("Ingest Batcher", getResult5.getName());
    PropertyGroup getResult6 = actualAll.get(5);
    assertEquals("Ingest", getResult6.getName());
    PropertyGroup getResult7 = actualAll.get(4);
    assertEquals("Metadata", getResult7.getName());
    PropertyGroup getResult8 = actualAll.get(1);
    assertEquals("Partition Splitting", getResult8.getName());
    PropertyGroup getResult9 = actualAll.get(8);
    assertEquals("Query Execution", getResult9.getName());
    assertEquals("The following table properties relate to bulk import, i.e. ingesting data using Spark jobs running on"
        + " EMR or EKS.", getResult.getDescription());
    assertEquals("The following table properties relate to bulk import, i.e. ingesting data using Spark jobs running on"
        + " EMR or EKS.", getResult.toString());
    assertEquals("The following table properties relate to compactions.", getResult2.getDescription());
    assertEquals("The following table properties relate to compactions.", getResult2.toString());
    assertEquals("The following table properties relate to ingest.", getResult6.getDescription());
    assertEquals("The following table properties relate to ingest.", getResult6.toString());
    assertEquals("The following table properties relate to partition splitting.", getResult8.getDescription());
    assertEquals("The following table properties relate to partition splitting.", getResult8.toString());
    assertEquals("The following table properties relate to query execution", getResult9.getDescription());
    assertEquals("The following table properties relate to query execution", getResult9.toString());
    assertEquals("The following table properties relate to storing and retrieving metadata for tables.",
        getResult7.getDescription());
    assertEquals("The following table properties relate to storing and retrieving metadata for tables.",
        getResult7.toString());
    assertEquals("The following table properties relate to the definition of data inside a table.",
        getResult3.getDescription());
    assertEquals("The following table properties relate to the definition of data inside a table.",
        getResult3.toString());
    assertEquals("The following table properties relate to the ingest batcher.", getResult5.getDescription());
    assertEquals("The following table properties relate to the ingest batcher.", getResult5.toString());
    assertEquals("The following table properties relate to the storage of data inside a table.",
        getResult4.getDescription());
    assertEquals("The following table properties relate to the storage of data inside a table.", getResult4.toString());
    assertNull(getResult3.getDetails());
    assertNull(getResult8.getDetails());
    assertNull(getResult4.getDetails());
    assertNull(getResult2.getDetails());
    assertNull(getResult7.getDetails());
    assertNull(getResult6.getDetails());
    assertNull(getResult.getDetails());
    assertNull(getResult5.getDetails());
    assertNull(getResult9.getDetails());
  }
}
