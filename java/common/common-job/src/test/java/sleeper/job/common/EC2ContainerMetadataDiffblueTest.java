package sleeper.job.common;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.IOException;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import software.amazon.awssdk.services.ecs.EcsClient;

class EC2ContainerMetadataDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link EC2ContainerMetadata#EC2ContainerMetadata(String, String, String, String, String)}
   *   <li>{@link EC2ContainerMetadata#toString()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void EC2ContainerMetadata.<init>(String, String, String, String, String)",
      "String EC2ContainerMetadata.toString()"})
  void testGettersAndSetters() {
    // Arrange, Act and Assert
    assertEquals(
        "ContainerMetadata [clusterName=Cluster Name, instanceARN=Instance ARN, instanceID=Instance ID, az=Az,"
            + " status=Status]",
        (new EC2ContainerMetadata("Cluster Name", "Instance ARN", "Instance ID", "Az", "Status")).toString());
  }

  /**
   * Test {@link EC2ContainerMetadata#retrieveContainerMetadata(EcsClient)}.
   * <p>
   * Method under test: {@link EC2ContainerMetadata#retrieveContainerMetadata(EcsClient)}
   */
  @Test
  @DisplayName("Test retrieveContainerMetadata(EcsClient)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Optional EC2ContainerMetadata.retrieveContainerMetadata(EcsClient)"})
  void testRetrieveContainerMetadata() throws IOException {
    // Arrange and Act
    Optional<EC2ContainerMetadata> actualRetrieveContainerMetadataResult = EC2ContainerMetadata
        .retrieveContainerMetadata(mock(EcsClient.class));

    // Assert
    assertFalse(actualRetrieveContainerMetadataResult.isPresent());
  }

  /**
   * Test {@link EC2ContainerMetadata#equals(Object)}, and {@link EC2ContainerMetadata#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link EC2ContainerMetadata#equals(Object)}
   *   <li>{@link EC2ContainerMetadata#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean EC2ContainerMetadata.equals(Object)", "int EC2ContainerMetadata.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    EC2ContainerMetadata ec2ContainerMetadata = new EC2ContainerMetadata("Cluster Name", "Instance ARN", "Instance ID",
        "Az", "Status");
    EC2ContainerMetadata ec2ContainerMetadata2 = new EC2ContainerMetadata("Cluster Name", "Instance ARN", "Instance ID",
        "Az", "Status");

    // Act and Assert
    assertEquals(ec2ContainerMetadata, ec2ContainerMetadata2);
    int expectedHashCodeResult = ec2ContainerMetadata.hashCode();
    assertEquals(expectedHashCodeResult, ec2ContainerMetadata2.hashCode());
  }

  /**
   * Test {@link EC2ContainerMetadata#equals(Object)}, and {@link EC2ContainerMetadata#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link EC2ContainerMetadata#equals(Object)}
   *   <li>{@link EC2ContainerMetadata#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean EC2ContainerMetadata.equals(Object)", "int EC2ContainerMetadata.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    EC2ContainerMetadata ec2ContainerMetadata = new EC2ContainerMetadata("Cluster Name", "Instance ARN", "Instance ID",
        "Az", "Status");

    // Act and Assert
    assertEquals(ec2ContainerMetadata, ec2ContainerMetadata);
    int expectedHashCodeResult = ec2ContainerMetadata.hashCode();
    assertEquals(expectedHashCodeResult, ec2ContainerMetadata.hashCode());
  }

  /**
   * Test {@link EC2ContainerMetadata#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link EC2ContainerMetadata#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean EC2ContainerMetadata.equals(Object)", "int EC2ContainerMetadata.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    EC2ContainerMetadata ec2ContainerMetadata = new EC2ContainerMetadata(null, "Instance ARN", "Instance ID", "Az",
        "Status");

    // Act and Assert
    assertNotEquals(ec2ContainerMetadata,
        new EC2ContainerMetadata("Cluster Name", "Instance ARN", "Instance ID", "Az", "Status"));
  }

  /**
   * Test {@link EC2ContainerMetadata#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link EC2ContainerMetadata#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean EC2ContainerMetadata.equals(Object)", "int EC2ContainerMetadata.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    EC2ContainerMetadata ec2ContainerMetadata = new EC2ContainerMetadata("Cluster Name", null, "Instance ID", "Az",
        "Status");

    // Act and Assert
    assertNotEquals(ec2ContainerMetadata,
        new EC2ContainerMetadata("Cluster Name", "Instance ARN", "Instance ID", "Az", "Status"));
  }

  /**
   * Test {@link EC2ContainerMetadata#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link EC2ContainerMetadata#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean EC2ContainerMetadata.equals(Object)", "int EC2ContainerMetadata.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual3() {
    // Arrange
    EC2ContainerMetadata ec2ContainerMetadata = new EC2ContainerMetadata("Cluster Name", "Instance ARN", null, "Az",
        "Status");

    // Act and Assert
    assertNotEquals(ec2ContainerMetadata,
        new EC2ContainerMetadata("Cluster Name", "Instance ARN", "Instance ID", "Az", "Status"));
  }

  /**
   * Test {@link EC2ContainerMetadata#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link EC2ContainerMetadata#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean EC2ContainerMetadata.equals(Object)", "int EC2ContainerMetadata.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual4() {
    // Arrange
    EC2ContainerMetadata ec2ContainerMetadata = new EC2ContainerMetadata("Cluster Name", "Instance ARN", "Instance ID",
        null, "Status");

    // Act and Assert
    assertNotEquals(ec2ContainerMetadata,
        new EC2ContainerMetadata("Cluster Name", "Instance ARN", "Instance ID", "Az", "Status"));
  }

  /**
   * Test {@link EC2ContainerMetadata#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link EC2ContainerMetadata#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean EC2ContainerMetadata.equals(Object)", "int EC2ContainerMetadata.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual5() {
    // Arrange
    EC2ContainerMetadata ec2ContainerMetadata = new EC2ContainerMetadata("Cluster Name", "Instance ARN", "Instance ID",
        "Az", null);

    // Act and Assert
    assertNotEquals(ec2ContainerMetadata,
        new EC2ContainerMetadata("Cluster Name", "Instance ARN", "Instance ID", "Az", "Status"));
  }

  /**
   * Test {@link EC2ContainerMetadata#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link EC2ContainerMetadata#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean EC2ContainerMetadata.equals(Object)", "int EC2ContainerMetadata.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new EC2ContainerMetadata("Cluster Name", "Instance ARN", "Instance ID", "Az", "Status"), null);
  }

  /**
   * Test {@link EC2ContainerMetadata#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link EC2ContainerMetadata#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean EC2ContainerMetadata.equals(Object)", "int EC2ContainerMetadata.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new EC2ContainerMetadata("Cluster Name", "Instance ARN", "Instance ID", "Az", "Status"),
        "Different type to EC2ContainerMetadata");
  }
}
