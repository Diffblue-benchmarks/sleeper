package sleeper.clients.status.report.ingest.job;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.clients.status.report.ingest.job.IngestQueueMessages.Builder;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.task.common.QueueMessageCount;
import sleeper.task.common.QueueMessageCount.Client;

class IngestQueueMessagesDiffblueTest {
  /**
   * Test Builder {@link Builder#build()}.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Builder#build()}
   *   <li>{@link Builder#eksMessages(Integer)}
   *   <li>{@link Builder#emrMessages(Integer)}
   *   <li>{@link Builder#ingestMessages(Integer)}
   *   <li>{@link Builder#persistentEmrMessages(Integer)}
   * </ul>
   */
  @Test
  @DisplayName("Test Builder build()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"IngestQueueMessages Builder.build()", "Builder Builder.eksMessages(Integer)",
      "Builder Builder.emrMessages(Integer)", "Builder Builder.ingestMessages(Integer)",
      "Builder Builder.persistentEmrMessages(Integer)"})
  void testBuilderBuild() {
    // Arrange, Act and Assert
    assertEquals(4,
        IngestQueueMessages.builder()
            .eksMessages(1)
            .emrMessages(1)
            .ingestMessages(1)
            .persistentEmrMessages(1)
            .build()
            .getTotalMessages());
  }

  /**
   * Test {@link IngestQueueMessages#from(InstanceProperties, Client)}.
   * <ul>
   *   <li>When {@link InstanceProperties#InstanceProperties()}.</li>
   *   <li>Then return TotalMessages is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestQueueMessages#from(InstanceProperties, QueueMessageCount.Client)}
   */
  @Test
  @DisplayName("Test from(InstanceProperties, Client); when InstanceProperties(); then return TotalMessages is zero")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"IngestQueueMessages IngestQueueMessages.from(InstanceProperties, QueueMessageCount.Client)"})
  void testFrom_whenInstanceProperties_thenReturnTotalMessagesIsZero() {
    // Arrange, Act and Assert
    assertEquals(0, IngestQueueMessages.from(new InstanceProperties(), mock(Client.class)).getTotalMessages());
  }

  /**
   * Test {@link IngestQueueMessages#getTotalMessages()}.
   * <p>
   * Method under test: {@link IngestQueueMessages#getTotalMessages()}
   */
  @Test
  @DisplayName("Test getTotalMessages()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"int IngestQueueMessages.getTotalMessages()"})
  void testGetTotalMessages() {
    // Arrange
    IngestQueueMessages buildResult = IngestQueueMessages.builder()
        .eksMessages(1)
        .emrMessages(1)
        .ingestMessages(1)
        .persistentEmrMessages(1)
        .build();

    // Act and Assert
    assertEquals(4, buildResult.getTotalMessages());
  }

  /**
   * Test {@link IngestQueueMessages#equals(Object)}, and {@link IngestQueueMessages#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link IngestQueueMessages#equals(Object)}
   *   <li>{@link IngestQueueMessages#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestQueueMessages.equals(Object)", "int IngestQueueMessages.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    IngestQueueMessages buildResult = IngestQueueMessages.builder()
        .eksMessages(1)
        .emrMessages(1)
        .ingestMessages(1)
        .persistentEmrMessages(1)
        .build();
    IngestQueueMessages buildResult2 = IngestQueueMessages.builder()
        .eksMessages(1)
        .emrMessages(1)
        .ingestMessages(1)
        .persistentEmrMessages(1)
        .build();

    // Act and Assert
    assertEquals(buildResult, buildResult2);
    int expectedHashCodeResult = buildResult.hashCode();
    assertEquals(expectedHashCodeResult, buildResult2.hashCode());
  }

  /**
   * Test {@link IngestQueueMessages#equals(Object)}, and {@link IngestQueueMessages#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link IngestQueueMessages#equals(Object)}
   *   <li>{@link IngestQueueMessages#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestQueueMessages.equals(Object)", "int IngestQueueMessages.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    IngestQueueMessages buildResult = IngestQueueMessages.builder()
        .eksMessages(1)
        .emrMessages(1)
        .ingestMessages(1)
        .persistentEmrMessages(1)
        .build();

    // Act and Assert
    assertEquals(buildResult, buildResult);
    int expectedHashCodeResult = buildResult.hashCode();
    assertEquals(expectedHashCodeResult, buildResult.hashCode());
  }

  /**
   * Test {@link IngestQueueMessages#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestQueueMessages#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestQueueMessages.equals(Object)", "int IngestQueueMessages.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    IngestQueueMessages buildResult = IngestQueueMessages.builder()
        .eksMessages(0)
        .emrMessages(1)
        .ingestMessages(1)
        .persistentEmrMessages(1)
        .build();
    IngestQueueMessages buildResult2 = IngestQueueMessages.builder()
        .eksMessages(1)
        .emrMessages(1)
        .ingestMessages(1)
        .persistentEmrMessages(1)
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link IngestQueueMessages#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestQueueMessages#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestQueueMessages.equals(Object)", "int IngestQueueMessages.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    IngestQueueMessages buildResult = IngestQueueMessages.builder()
        .eksMessages(1)
        .emrMessages(0)
        .ingestMessages(1)
        .persistentEmrMessages(1)
        .build();
    IngestQueueMessages buildResult2 = IngestQueueMessages.builder()
        .eksMessages(1)
        .emrMessages(1)
        .ingestMessages(1)
        .persistentEmrMessages(1)
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link IngestQueueMessages#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestQueueMessages#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestQueueMessages.equals(Object)", "int IngestQueueMessages.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual3() {
    // Arrange
    IngestQueueMessages buildResult = IngestQueueMessages.builder()
        .eksMessages(1)
        .emrMessages(1)
        .ingestMessages(0)
        .persistentEmrMessages(1)
        .build();
    IngestQueueMessages buildResult2 = IngestQueueMessages.builder()
        .eksMessages(1)
        .emrMessages(1)
        .ingestMessages(1)
        .persistentEmrMessages(1)
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link IngestQueueMessages#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestQueueMessages#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestQueueMessages.equals(Object)", "int IngestQueueMessages.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual4() {
    // Arrange
    IngestQueueMessages buildResult = IngestQueueMessages.builder()
        .eksMessages(1)
        .emrMessages(1)
        .ingestMessages(1)
        .persistentEmrMessages(0)
        .build();
    IngestQueueMessages buildResult2 = IngestQueueMessages.builder()
        .eksMessages(1)
        .emrMessages(1)
        .ingestMessages(1)
        .persistentEmrMessages(1)
        .build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link IngestQueueMessages#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestQueueMessages#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestQueueMessages.equals(Object)", "int IngestQueueMessages.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange
    IngestQueueMessages buildResult = IngestQueueMessages.builder()
        .eksMessages(1)
        .emrMessages(1)
        .ingestMessages(1)
        .persistentEmrMessages(1)
        .build();

    // Act and Assert
    assertNotEquals(buildResult, null);
  }

  /**
   * Test {@link IngestQueueMessages#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestQueueMessages#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestQueueMessages.equals(Object)", "int IngestQueueMessages.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange
    IngestQueueMessages buildResult = IngestQueueMessages.builder()
        .eksMessages(1)
        .emrMessages(1)
        .ingestMessages(1)
        .persistentEmrMessages(1)
        .build();

    // Act and Assert
    assertNotEquals(buildResult, "Different type to IngestQueueMessages");
  }

  /**
   * Test {@link IngestQueueMessages#toString()}.
   * <p>
   * Method under test: {@link IngestQueueMessages#toString()}
   */
  @Test
  @DisplayName("Test toString()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.lang.String IngestQueueMessages.toString()"})
  void testToString() {
    // Arrange
    IngestQueueMessages buildResult = IngestQueueMessages.builder()
        .eksMessages(1)
        .emrMessages(1)
        .ingestMessages(1)
        .persistentEmrMessages(1)
        .build();

    // Act and Assert
    assertEquals("IngestQueueMessages{ingestMessages=1, emrMessages=1, persistentEmrMessages=1, eksMessages=1}",
        buildResult.toString());
  }
}
