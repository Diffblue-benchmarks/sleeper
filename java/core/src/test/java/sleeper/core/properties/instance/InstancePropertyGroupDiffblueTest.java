package sleeper.core.properties.instance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.properties.PropertyGroup;

class InstancePropertyGroupDiffblueTest {
  /**
   * Test {@link InstancePropertyGroup#getAll()}.
   * <p>
   * Method under test: {@link InstancePropertyGroup#getAll()}
   */
  @Test
  @DisplayName("Test getAll()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List InstancePropertyGroup.getAll()"})
  void testGetAll() {
    // Arrange and Act
    List<PropertyGroup> actualAll = InstancePropertyGroup.getAll();

    // Assert
    assertEquals(12, actualAll.size());
    PropertyGroup getResult = actualAll.get(10);
    assertEquals("Athena", getResult.getName());
    PropertyGroup getResult2 = actualAll.get(3);
    assertEquals("Bulk Import", getResult2.getName());
    PropertyGroup getResult3 = actualAll.get(0);
    assertEquals("Common", getResult3.getName());
    PropertyGroup getResult4 = actualAll.get(6);
    assertEquals("Compaction", getResult4.getName());
    PropertyGroup getResult5 = actualAll.get(5);
    assertEquals("Garbage Collector", getResult5.getName());
    PropertyGroup getResult6 = actualAll.get(2);
    assertEquals("Ingest", getResult6.getName());
    PropertyGroup getResult7 = actualAll.get(9);
    assertEquals("Logging", getResult7.getName());
    PropertyGroup getResult8 = actualAll.get(8);
    assertEquals("Metrics", getResult8.getName());
    assertEquals(
        "Note that on EMR, the total resource allocation must align with the instance types used for the cluster."
            + " For the maximum memory usage, combine the memory and memory overhead properties, and compare against"
            + " the maximum memory allocation for YARN in the Hadoop task configuration:\n" + "\n"
            + "https://docs.aws.amazon.com/emr/latest/ReleaseGuide/emr-hadoop-task-config.html\n" + "\n"
            + "As an example, if we use m7i.xlarge for executor instances, that has a maximum allocation of 54272"
            + " MiB, or 53 GiB. If we want 3 executors per instance, we can have 53 GiB / 3 = 18,090.666 MiB per"
            + " executor. We can set the executor memory to 16 GiB, and the executor memory overhead to the remainder"
            + " of that amount, which is 18,090 MiB - 16 GiB = 1,706 MiB, or 1.666 GiB. This is just above the default"
            + " Spark memory overhead factor of 0.1, i.e. 16 GiB x 0.1 = 1.6 GiB.\n" + "\n"
            + "Also see EMR best practices:\n" + "\n"
            + "https://aws.github.io/aws-emr-best-practices/docs/bestpractices/Applications/Spark/best_practices/"
            + "#bp-516----tune-driverexecutor-memory-cores-and-sparksqlshufflepartitions-to-fully-utilize-cluster"
            + "-resources",
        getResult2.getDetails());
    PropertyGroup getResult9 = actualAll.get(4);
    assertEquals("Partition Splitting", getResult9.getName());
    PropertyGroup getResult10 = actualAll.get(7);
    assertEquals("Query", getResult10.getName());
    PropertyGroup getResult11 = actualAll.get(11);
    assertEquals("Table Property Default", getResult11.getName());
    PropertyGroup getResult12 = actualAll.get(1);
    assertEquals("Table State", getResult12.getName());
    assertEquals("The following properties are commonly used throughout Sleeper.", getResult3.getDescription());
    assertEquals("The following properties are commonly used throughout Sleeper.", getResult3.toString());
    assertEquals("The following properties relate to bulk import, i.e. ingesting data using Spark jobs running on EMR"
        + " or EKS.", getResult2.getDescription());
    assertEquals("The following properties relate to bulk import, i.e. ingesting data using Spark jobs running on EMR"
        + " or EKS.", getResult2.toString());
    assertEquals("The following properties relate to compactions.", getResult4.getDescription());
    assertEquals("The following properties relate to compactions.", getResult4.toString());
    assertEquals("The following properties relate to default values used by table properties.",
        getResult11.getDescription());
    assertEquals("The following properties relate to default values used by table properties.", getResult11.toString());
    assertEquals("The following properties relate to garbage collection.", getResult5.getDescription());
    assertEquals("The following properties relate to garbage collection.", getResult5.toString());
    assertEquals("The following properties relate to handling the state of Sleeper tables.",
        getResult12.getDescription());
    assertEquals("The following properties relate to handling the state of Sleeper tables.", getResult12.toString());
    assertEquals("The following properties relate to logging.", getResult7.getDescription());
    assertEquals("The following properties relate to logging.", getResult7.toString());
    assertEquals("The following properties relate to metrics.", getResult8.getDescription());
    assertEquals("The following properties relate to metrics.", getResult8.toString());
    assertEquals("The following properties relate to queries.", getResult10.getDescription());
    assertEquals("The following properties relate to queries.", getResult10.toString());
    assertEquals("The following properties relate to standard ingest.", getResult6.getDescription());
    assertEquals("The following properties relate to standard ingest.", getResult6.toString());
    assertEquals("The following properties relate to the integration with Athena.", getResult.getDescription());
    assertEquals("The following properties relate to the integration with Athena.", getResult.toString());
    assertEquals("The following properties relate to the splitting of partitions.", getResult9.getDescription());
    assertEquals("The following properties relate to the splitting of partitions.", getResult9.toString());
    assertNull(getResult3.getDetails());
    assertNull(getResult12.getDetails());
    assertNull(getResult.getDetails());
    assertNull(getResult11.getDetails());
    assertNull(getResult6.getDetails());
    assertNull(getResult9.getDetails());
    assertNull(getResult5.getDetails());
    assertNull(getResult4.getDetails());
    assertNull(getResult10.getDetails());
    assertNull(getResult8.getDetails());
    assertNull(getResult7.getDetails());
  }
}
