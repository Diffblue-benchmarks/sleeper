package sleeper.cdk.stack.core;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.cdk.stack.core.StateStoreGrants.Access;

class StateStoreGrantsDiffblueTest {
  /**
   * Test Access getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Access#canRead()}
   *   <li>{@link Access#canWrite()}
   * </ul>
   */
  @Test
  @DisplayName("Test Access getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean Access.canRead()", "boolean Access.canWrite()"})
  void testAccessGettersAndSetters() {
    // Arrange
    Access valueOfResult = Access.valueOf("NO_ACCESS");

    // Act
    boolean actualCanReadResult = valueOfResult.canRead();

    // Assert
    assertFalse(actualCanReadResult);
    assertFalse(valueOfResult.canWrite());
  }

  /**
   * Test {@link StateStoreGrants#readPartitions()}.
   * <p>
   * Method under test: {@link StateStoreGrants#readPartitions()}
   */
  @Test
  @DisplayName("Test readPartitions()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"StateStoreGrants StateStoreGrants.readPartitions()"})
  void testReadPartitions() {
    // Arrange and Act
    StateStoreGrants actualReadPartitionsResult = StateStoreGrants.readPartitions();

    // Assert
    assertFalse(actualReadPartitionsResult.canReadActiveFiles());
    assertFalse(actualReadPartitionsResult.canReadActiveOrReadyForGCFiles());
    assertTrue(actualReadPartitionsResult.canReadAny());
    assertTrue(actualReadPartitionsResult.canReadPartitions());
  }

  /**
   * Test {@link StateStoreGrants#readWritePartitions()}.
   * <p>
   * Method under test: {@link StateStoreGrants#readWritePartitions()}
   */
  @Test
  @DisplayName("Test readWritePartitions()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"StateStoreGrants StateStoreGrants.readWritePartitions()"})
  void testReadWritePartitions() {
    // Arrange and Act
    StateStoreGrants actualReadWritePartitionsResult = StateStoreGrants.readWritePartitions();

    // Assert
    assertFalse(actualReadWritePartitionsResult.canReadActiveFiles());
    assertFalse(actualReadWritePartitionsResult.canReadActiveOrReadyForGCFiles());
    assertTrue(actualReadWritePartitionsResult.canReadAny());
    assertTrue(actualReadWritePartitionsResult.canReadPartitions());
  }

  /**
   * Test {@link StateStoreGrants#readPartitionsReadWriteActiveFiles()}.
   * <p>
   * Method under test: {@link StateStoreGrants#readPartitionsReadWriteActiveFiles()}
   */
  @Test
  @DisplayName("Test readPartitionsReadWriteActiveFiles()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"StateStoreGrants StateStoreGrants.readPartitionsReadWriteActiveFiles()"})
  void testReadPartitionsReadWriteActiveFiles() {
    // Arrange and Act
    StateStoreGrants actualReadPartitionsReadWriteActiveFilesResult = StateStoreGrants
        .readPartitionsReadWriteActiveFiles();

    // Assert
    assertTrue(actualReadPartitionsReadWriteActiveFilesResult.canReadActiveFiles());
    assertTrue(actualReadPartitionsReadWriteActiveFilesResult.canReadActiveOrReadyForGCFiles());
    assertTrue(actualReadPartitionsReadWriteActiveFilesResult.canReadAny());
    assertTrue(actualReadPartitionsReadWriteActiveFilesResult.canReadPartitions());
  }

  /**
   * Test {@link StateStoreGrants#readActiveFilesReadWritePartitions()}.
   * <p>
   * Method under test: {@link StateStoreGrants#readActiveFilesReadWritePartitions()}
   */
  @Test
  @DisplayName("Test readActiveFilesReadWritePartitions()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"StateStoreGrants StateStoreGrants.readActiveFilesReadWritePartitions()"})
  void testReadActiveFilesReadWritePartitions() {
    // Arrange and Act
    StateStoreGrants actualReadActiveFilesReadWritePartitionsResult = StateStoreGrants
        .readActiveFilesReadWritePartitions();

    // Assert
    assertTrue(actualReadActiveFilesReadWritePartitionsResult.canReadActiveFiles());
    assertTrue(actualReadActiveFilesReadWritePartitionsResult.canReadActiveOrReadyForGCFiles());
    assertTrue(actualReadActiveFilesReadWritePartitionsResult.canReadAny());
    assertTrue(actualReadActiveFilesReadWritePartitionsResult.canReadPartitions());
  }

  /**
   * Test {@link StateStoreGrants#readWriteAllFilesAndPartitions()}.
   * <p>
   * Method under test: {@link StateStoreGrants#readWriteAllFilesAndPartitions()}
   */
  @Test
  @DisplayName("Test readWriteAllFilesAndPartitions()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"StateStoreGrants StateStoreGrants.readWriteAllFilesAndPartitions()"})
  void testReadWriteAllFilesAndPartitions() {
    // Arrange and Act
    StateStoreGrants actualReadWriteAllFilesAndPartitionsResult = StateStoreGrants.readWriteAllFilesAndPartitions();

    // Assert
    assertTrue(actualReadWriteAllFilesAndPartitionsResult.canReadActiveFiles());
    assertTrue(actualReadWriteAllFilesAndPartitionsResult.canReadActiveOrReadyForGCFiles());
    assertTrue(actualReadWriteAllFilesAndPartitionsResult.canReadAny());
    assertTrue(actualReadWriteAllFilesAndPartitionsResult.canReadPartitions());
  }

  /**
   * Test {@link StateStoreGrants#readActiveFilesAndPartitions()}.
   * <p>
   * Method under test: {@link StateStoreGrants#readActiveFilesAndPartitions()}
   */
  @Test
  @DisplayName("Test readActiveFilesAndPartitions()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"StateStoreGrants StateStoreGrants.readActiveFilesAndPartitions()"})
  void testReadActiveFilesAndPartitions() {
    // Arrange and Act
    StateStoreGrants actualReadActiveFilesAndPartitionsResult = StateStoreGrants.readActiveFilesAndPartitions();

    // Assert
    assertTrue(actualReadActiveFilesAndPartitionsResult.canReadActiveFiles());
    assertTrue(actualReadActiveFilesAndPartitionsResult.canReadActiveOrReadyForGCFiles());
    assertTrue(actualReadActiveFilesAndPartitionsResult.canReadAny());
    assertTrue(actualReadActiveFilesAndPartitionsResult.canReadPartitions());
  }

  /**
   * Test {@link StateStoreGrants#readAllFilesAndPartitions()}.
   * <p>
   * Method under test: {@link StateStoreGrants#readAllFilesAndPartitions()}
   */
  @Test
  @DisplayName("Test readAllFilesAndPartitions()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"StateStoreGrants StateStoreGrants.readAllFilesAndPartitions()"})
  void testReadAllFilesAndPartitions() {
    // Arrange and Act
    StateStoreGrants actualReadAllFilesAndPartitionsResult = StateStoreGrants.readAllFilesAndPartitions();

    // Assert
    assertTrue(actualReadAllFilesAndPartitionsResult.canReadActiveFiles());
    assertTrue(actualReadAllFilesAndPartitionsResult.canReadActiveOrReadyForGCFiles());
    assertTrue(actualReadAllFilesAndPartitionsResult.canReadAny());
    assertTrue(actualReadAllFilesAndPartitionsResult.canReadPartitions());
  }

  /**
   * Test {@link StateStoreGrants#readWriteReadyForGCFiles()}.
   * <p>
   * Method under test: {@link StateStoreGrants#readWriteReadyForGCFiles()}
   */
  @Test
  @DisplayName("Test readWriteReadyForGCFiles()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"StateStoreGrants StateStoreGrants.readWriteReadyForGCFiles()"})
  void testReadWriteReadyForGCFiles() {
    // Arrange and Act
    StateStoreGrants actualReadWriteReadyForGCFilesResult = StateStoreGrants.readWriteReadyForGCFiles();

    // Assert
    assertFalse(actualReadWriteReadyForGCFilesResult.canReadActiveFiles());
    assertFalse(actualReadWriteReadyForGCFilesResult.canReadPartitions());
    assertTrue(actualReadWriteReadyForGCFilesResult.canReadActiveOrReadyForGCFiles());
    assertTrue(actualReadWriteReadyForGCFilesResult.canReadAny());
  }

  /**
   * Test {@link StateStoreGrants#readWriteActiveAndReadyForGCFiles()}.
   * <p>
   * Method under test: {@link StateStoreGrants#readWriteActiveAndReadyForGCFiles()}
   */
  @Test
  @DisplayName("Test readWriteActiveAndReadyForGCFiles()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"StateStoreGrants StateStoreGrants.readWriteActiveAndReadyForGCFiles()"})
  void testReadWriteActiveAndReadyForGCFiles() {
    // Arrange and Act
    StateStoreGrants actualReadWriteActiveAndReadyForGCFilesResult = StateStoreGrants
        .readWriteActiveAndReadyForGCFiles();

    // Assert
    assertFalse(actualReadWriteActiveAndReadyForGCFilesResult.canReadPartitions());
    assertTrue(actualReadWriteActiveAndReadyForGCFilesResult.canReadActiveFiles());
    assertTrue(actualReadWriteActiveAndReadyForGCFilesResult.canReadActiveOrReadyForGCFiles());
    assertTrue(actualReadWriteActiveAndReadyForGCFilesResult.canReadAny());
  }

  /**
   * Test {@link StateStoreGrants#canWriteActiveFiles()}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreGrants#canWriteActiveFiles()}
   */
  @Test
  @DisplayName("Test canWriteActiveFiles(); then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean StateStoreGrants.canWriteActiveFiles()"})
  void testCanWriteActiveFiles_thenReturnFalse() {
    // Arrange
    StateStoreGrants buildResult = StateStoreGrants.builder()
        .activeFiles(Access.NO_ACCESS)
        .partitions(Access.NO_ACCESS)
        .readyForGCFiles(Access.NO_ACCESS)
        .build();

    // Act and Assert
    assertFalse(buildResult.canWriteActiveFiles());
  }

  /**
   * Test {@link StateStoreGrants#canWriteActiveFiles()}.
   * <ul>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreGrants#canWriteActiveFiles()}
   */
  @Test
  @DisplayName("Test canWriteActiveFiles(); then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean StateStoreGrants.canWriteActiveFiles()"})
  void testCanWriteActiveFiles_thenReturnTrue() {
    // Arrange
    StateStoreGrants buildResult = StateStoreGrants.builder()
        .activeFiles(Access.READ_WRITE)
        .partitions(Access.NO_ACCESS)
        .readyForGCFiles(Access.NO_ACCESS)
        .build();

    // Act and Assert
    assertTrue(buildResult.canWriteActiveFiles());
  }

  /**
   * Test {@link StateStoreGrants#canReadActiveFiles()}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreGrants#canReadActiveFiles()}
   */
  @Test
  @DisplayName("Test canReadActiveFiles(); then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean StateStoreGrants.canReadActiveFiles()"})
  void testCanReadActiveFiles_thenReturnFalse() {
    // Arrange
    StateStoreGrants buildResult = StateStoreGrants.builder()
        .activeFiles(Access.NO_ACCESS)
        .partitions(Access.NO_ACCESS)
        .readyForGCFiles(Access.NO_ACCESS)
        .build();

    // Act and Assert
    assertFalse(buildResult.canReadActiveFiles());
  }

  /**
   * Test {@link StateStoreGrants#canReadActiveFiles()}.
   * <ul>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreGrants#canReadActiveFiles()}
   */
  @Test
  @DisplayName("Test canReadActiveFiles(); then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean StateStoreGrants.canReadActiveFiles()"})
  void testCanReadActiveFiles_thenReturnTrue() {
    // Arrange
    StateStoreGrants buildResult = StateStoreGrants.builder()
        .activeFiles(Access.READ)
        .partitions(Access.NO_ACCESS)
        .readyForGCFiles(Access.NO_ACCESS)
        .build();

    // Act and Assert
    assertTrue(buildResult.canReadActiveFiles());
  }

  /**
   * Test {@link StateStoreGrants#canWritePartitions()}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreGrants#canWritePartitions()}
   */
  @Test
  @DisplayName("Test canWritePartitions(); then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean StateStoreGrants.canWritePartitions()"})
  void testCanWritePartitions_thenReturnFalse() {
    // Arrange
    StateStoreGrants buildResult = StateStoreGrants.builder()
        .activeFiles(Access.NO_ACCESS)
        .partitions(Access.NO_ACCESS)
        .readyForGCFiles(Access.NO_ACCESS)
        .build();

    // Act and Assert
    assertFalse(buildResult.canWritePartitions());
  }

  /**
   * Test {@link StateStoreGrants#canWritePartitions()}.
   * <ul>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreGrants#canWritePartitions()}
   */
  @Test
  @DisplayName("Test canWritePartitions(); then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean StateStoreGrants.canWritePartitions()"})
  void testCanWritePartitions_thenReturnTrue() {
    // Arrange
    StateStoreGrants buildResult = StateStoreGrants.builder()
        .activeFiles(Access.NO_ACCESS)
        .partitions(Access.READ_WRITE)
        .readyForGCFiles(Access.NO_ACCESS)
        .build();

    // Act and Assert
    assertTrue(buildResult.canWritePartitions());
  }

  /**
   * Test {@link StateStoreGrants#canReadPartitions()}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreGrants#canReadPartitions()}
   */
  @Test
  @DisplayName("Test canReadPartitions(); then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean StateStoreGrants.canReadPartitions()"})
  void testCanReadPartitions_thenReturnFalse() {
    // Arrange
    StateStoreGrants buildResult = StateStoreGrants.builder()
        .activeFiles(Access.NO_ACCESS)
        .partitions(Access.NO_ACCESS)
        .readyForGCFiles(Access.NO_ACCESS)
        .build();

    // Act and Assert
    assertFalse(buildResult.canReadPartitions());
  }

  /**
   * Test {@link StateStoreGrants#canReadPartitions()}.
   * <ul>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreGrants#canReadPartitions()}
   */
  @Test
  @DisplayName("Test canReadPartitions(); then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean StateStoreGrants.canReadPartitions()"})
  void testCanReadPartitions_thenReturnTrue() {
    // Arrange
    StateStoreGrants buildResult = StateStoreGrants.builder()
        .activeFiles(Access.NO_ACCESS)
        .partitions(Access.READ)
        .readyForGCFiles(Access.NO_ACCESS)
        .build();

    // Act and Assert
    assertTrue(buildResult.canReadPartitions());
  }

  /**
   * Test {@link StateStoreGrants#canReadAny()}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreGrants#canReadAny()}
   */
  @Test
  @DisplayName("Test canReadAny(); then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean StateStoreGrants.canReadAny()"})
  void testCanReadAny_thenReturnFalse() {
    // Arrange
    StateStoreGrants buildResult = StateStoreGrants.builder()
        .activeFiles(Access.NO_ACCESS)
        .partitions(Access.NO_ACCESS)
        .readyForGCFiles(Access.NO_ACCESS)
        .build();

    // Act and Assert
    assertFalse(buildResult.canReadAny());
  }

  /**
   * Test {@link StateStoreGrants#canReadAny()}.
   * <ul>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreGrants#canReadAny()}
   */
  @Test
  @DisplayName("Test canReadAny(); then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean StateStoreGrants.canReadAny()"})
  void testCanReadAny_thenReturnTrue() {
    // Arrange
    StateStoreGrants buildResult = StateStoreGrants.builder()
        .activeFiles(Access.READ)
        .partitions(Access.NO_ACCESS)
        .readyForGCFiles(Access.NO_ACCESS)
        .build();

    // Act and Assert
    assertTrue(buildResult.canReadAny());
  }

  /**
   * Test {@link StateStoreGrants#canWriteAny()}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreGrants#canWriteAny()}
   */
  @Test
  @DisplayName("Test canWriteAny(); then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean StateStoreGrants.canWriteAny()"})
  void testCanWriteAny_thenReturnFalse() {
    // Arrange
    StateStoreGrants buildResult = StateStoreGrants.builder()
        .activeFiles(Access.NO_ACCESS)
        .partitions(Access.NO_ACCESS)
        .readyForGCFiles(Access.NO_ACCESS)
        .build();

    // Act and Assert
    assertFalse(buildResult.canWriteAny());
  }

  /**
   * Test {@link StateStoreGrants#canWriteAny()}.
   * <ul>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateStoreGrants#canWriteAny()}
   */
  @Test
  @DisplayName("Test canWriteAny(); then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean StateStoreGrants.canWriteAny()"})
  void testCanWriteAny_thenReturnTrue() {
    // Arrange
    StateStoreGrants buildResult = StateStoreGrants.builder()
        .activeFiles(Access.READ_WRITE)
        .partitions(Access.NO_ACCESS)
        .readyForGCFiles(Access.NO_ACCESS)
        .build();

    // Act and Assert
    assertTrue(buildResult.canWriteAny());
  }
}
