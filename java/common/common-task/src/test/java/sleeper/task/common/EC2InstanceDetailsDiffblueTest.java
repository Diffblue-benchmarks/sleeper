package sleeper.task.common;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.function.Consumer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.services.ecs.EcsClient;
import software.amazon.awssdk.services.ecs.model.ListContainerInstancesRequest;
import software.amazon.awssdk.services.ecs.model.ListContainerInstancesRequest.Builder;

class EC2InstanceDetailsDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link EC2InstanceDetails#EC2InstanceDetails(String, String, Instant, int, int, int, int, int, int)}
   *   <li>{@link EC2InstanceDetails#toString()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void EC2InstanceDetails.<init>(String, String, Instant, int, int, int, int, int, int)",
      "String EC2InstanceDetails.toString()"})
  void testGettersAndSetters() {
    // Arrange and Act
    EC2InstanceDetails actualEc2InstanceDetails = new EC2InstanceDetails("42", "Instance Arn",
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(), 1, 1, 1, 1, 10, 3);

    // Assert
    assertEquals(
        "InstanceDetails [instanceId=42, instanceArn=Instance Arn, registered=1970-01-01T00:00:00Z, availableCPU=1,"
            + " availableRAM=1, totalCPU=1, totalRAM=1, numRunningTasks=10, numPendingTasks=3]",
        actualEc2InstanceDetails.toString());
    Instant instant = actualEc2InstanceDetails.registered;
    assertEquals(0, instant.getNano());
    assertEquals(0L, instant.getEpochSecond());
  }

  /**
   * Test {@link EC2InstanceDetails#fetchInstanceDetails(String, EcsClient)}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link EC2InstanceDetails#fetchInstanceDetails(String, EcsClient)}
   */
  @Test
  @DisplayName("Test fetchInstanceDetails(String, EcsClient); then throw IllegalStateException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.util.Map EC2InstanceDetails.fetchInstanceDetails(String, EcsClient)"})
  void testFetchInstanceDetails_thenThrowIllegalStateException() throws AwsServiceException, SdkClientException {
    // Arrange
    EcsClient ecsClient = mock(EcsClient.class);
    when(ecsClient.listContainerInstancesPaginator(Mockito.<Consumer<Builder>>any()))
        .thenThrow(new IllegalStateException("foo"));

    // Act and Assert
    assertThrows(IllegalStateException.class,
        () -> EC2InstanceDetails.fetchInstanceDetails("Ecs Cluster Name", ecsClient));
    verify(ecsClient).listContainerInstancesPaginator(isA(Consumer.class));
  }

  /**
   * Test {@link EC2InstanceDetails#streamInstances(String, EcsClient)}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link EC2InstanceDetails#streamInstances(String, EcsClient)}
   */
  @Test
  @DisplayName("Test streamInstances(String, EcsClient); then throw IllegalStateException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.util.stream.Stream EC2InstanceDetails.streamInstances(String, EcsClient)"})
  void testStreamInstances_thenThrowIllegalStateException() throws AwsServiceException, SdkClientException {
    // Arrange
    EcsClient ecsClient = mock(EcsClient.class);
    when(ecsClient.listContainerInstancesPaginator(Mockito.<Consumer<Builder>>any()))
        .thenThrow(new IllegalStateException("foo"));

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> EC2InstanceDetails.streamInstances("Ecs Cluster Name", ecsClient));
    verify(ecsClient).listContainerInstancesPaginator(isA(Consumer.class));
  }

  /**
   * Test {@link EC2InstanceDetails#equals(Object)}, and {@link EC2InstanceDetails#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link EC2InstanceDetails#equals(Object)}
   *   <li>{@link EC2InstanceDetails#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean EC2InstanceDetails.equals(Object)", "int EC2InstanceDetails.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    EC2InstanceDetails ec2InstanceDetails = new EC2InstanceDetails("42", "Instance Arn",
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(), 1, 1, 1, 1, 10, 3);
    EC2InstanceDetails ec2InstanceDetails2 = new EC2InstanceDetails("42", "Instance Arn",
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(), 1, 1, 1, 1, 10, 3);

    // Act and Assert
    assertEquals(ec2InstanceDetails, ec2InstanceDetails2);
    int expectedHashCodeResult = ec2InstanceDetails.hashCode();
    assertEquals(expectedHashCodeResult, ec2InstanceDetails2.hashCode());
  }

  /**
   * Test {@link EC2InstanceDetails#equals(Object)}, and {@link EC2InstanceDetails#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link EC2InstanceDetails#equals(Object)}
   *   <li>{@link EC2InstanceDetails#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean EC2InstanceDetails.equals(Object)", "int EC2InstanceDetails.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    EC2InstanceDetails ec2InstanceDetails = new EC2InstanceDetails("42", "Instance Arn",
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(), 1, 1, 1, 1, 10, 3);

    // Act and Assert
    assertEquals(ec2InstanceDetails, ec2InstanceDetails);
    int expectedHashCodeResult = ec2InstanceDetails.hashCode();
    assertEquals(expectedHashCodeResult, ec2InstanceDetails.hashCode());
  }

  /**
   * Test {@link EC2InstanceDetails#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link EC2InstanceDetails#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean EC2InstanceDetails.equals(Object)", "int EC2InstanceDetails.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    EC2InstanceDetails ec2InstanceDetails = new EC2InstanceDetails("Instance Id", "Instance Arn",
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(), 1, 1, 1, 1, 10, 3);

    // Act and Assert
    assertNotEquals(ec2InstanceDetails, new EC2InstanceDetails("42", "Instance Arn",
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(), 1, 1, 1, 1, 10, 3));
  }

  /**
   * Test {@link EC2InstanceDetails#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link EC2InstanceDetails#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean EC2InstanceDetails.equals(Object)", "int EC2InstanceDetails.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    EC2InstanceDetails ec2InstanceDetails = new EC2InstanceDetails("42", null,
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(), 1, 1, 1, 1, 10, 3);

    // Act and Assert
    assertNotEquals(ec2InstanceDetails, new EC2InstanceDetails("42", "Instance Arn",
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(), 1, 1, 1, 1, 10, 3));
  }

  /**
   * Test {@link EC2InstanceDetails#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link EC2InstanceDetails#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean EC2InstanceDetails.equals(Object)", "int EC2InstanceDetails.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual3() {
    // Arrange
    EC2InstanceDetails ec2InstanceDetails = new EC2InstanceDetails("42", "Instance Arn",
        LocalDate.now().atStartOfDay().atZone(ZoneOffset.UTC).toInstant(), 1, 1, 1, 1, 10, 3);

    // Act and Assert
    assertNotEquals(ec2InstanceDetails, new EC2InstanceDetails("42", "Instance Arn",
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(), 1, 1, 1, 1, 10, 3));
  }

  /**
   * Test {@link EC2InstanceDetails#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link EC2InstanceDetails#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean EC2InstanceDetails.equals(Object)", "int EC2InstanceDetails.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual4() {
    // Arrange
    EC2InstanceDetails ec2InstanceDetails = new EC2InstanceDetails("42", "Instance Arn",
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(), 3, 1, 1, 1, 10, 3);

    // Act and Assert
    assertNotEquals(ec2InstanceDetails, new EC2InstanceDetails("42", "Instance Arn",
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(), 1, 1, 1, 1, 10, 3));
  }

  /**
   * Test {@link EC2InstanceDetails#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link EC2InstanceDetails#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean EC2InstanceDetails.equals(Object)", "int EC2InstanceDetails.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual5() {
    // Arrange
    EC2InstanceDetails ec2InstanceDetails = new EC2InstanceDetails("42", "Instance Arn",
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(), 1, 3, 1, 1, 10, 3);

    // Act and Assert
    assertNotEquals(ec2InstanceDetails, new EC2InstanceDetails("42", "Instance Arn",
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(), 1, 1, 1, 1, 10, 3));
  }

  /**
   * Test {@link EC2InstanceDetails#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link EC2InstanceDetails#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean EC2InstanceDetails.equals(Object)", "int EC2InstanceDetails.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual6() {
    // Arrange
    EC2InstanceDetails ec2InstanceDetails = new EC2InstanceDetails("42", "Instance Arn",
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(), 1, 1, 3, 1, 10, 3);

    // Act and Assert
    assertNotEquals(ec2InstanceDetails, new EC2InstanceDetails("42", "Instance Arn",
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(), 1, 1, 1, 1, 10, 3));
  }

  /**
   * Test {@link EC2InstanceDetails#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link EC2InstanceDetails#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean EC2InstanceDetails.equals(Object)", "int EC2InstanceDetails.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual7() {
    // Arrange
    EC2InstanceDetails ec2InstanceDetails = new EC2InstanceDetails("42", "Instance Arn",
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(), 1, 1, 1, 3, 10, 3);

    // Act and Assert
    assertNotEquals(ec2InstanceDetails, new EC2InstanceDetails("42", "Instance Arn",
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(), 1, 1, 1, 1, 10, 3));
  }

  /**
   * Test {@link EC2InstanceDetails#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link EC2InstanceDetails#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean EC2InstanceDetails.equals(Object)", "int EC2InstanceDetails.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual8() {
    // Arrange
    EC2InstanceDetails ec2InstanceDetails = new EC2InstanceDetails("42", "Instance Arn",
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(), 1, 1, 1, 1, 1, 3);

    // Act and Assert
    assertNotEquals(ec2InstanceDetails, new EC2InstanceDetails("42", "Instance Arn",
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(), 1, 1, 1, 1, 10, 3));
  }

  /**
   * Test {@link EC2InstanceDetails#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link EC2InstanceDetails#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean EC2InstanceDetails.equals(Object)", "int EC2InstanceDetails.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual9() {
    // Arrange
    EC2InstanceDetails ec2InstanceDetails = new EC2InstanceDetails("42", "Instance Arn",
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(), 1, 1, 1, 1, 10, 10);

    // Act and Assert
    assertNotEquals(ec2InstanceDetails, new EC2InstanceDetails("42", "Instance Arn",
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(), 1, 1, 1, 1, 10, 3));
  }

  /**
   * Test {@link EC2InstanceDetails#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link EC2InstanceDetails#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean EC2InstanceDetails.equals(Object)", "int EC2InstanceDetails.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new EC2InstanceDetails("42", "Instance Arn",
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(), 1, 1, 1, 1, 10, 3), null);
  }

  /**
   * Test {@link EC2InstanceDetails#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link EC2InstanceDetails#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean EC2InstanceDetails.equals(Object)", "int EC2InstanceDetails.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(
        new EC2InstanceDetails("42", "Instance Arn",
            LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(), 1, 1, 1, 1, 10, 3),
        "Different type to EC2InstanceDetails");
  }
}
