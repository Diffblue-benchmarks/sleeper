package sleeper.core.deploy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.deploy.LambdaHandler.Builder;
import sleeper.core.properties.validation.OptionalStack;

class LambdaHandlerDiffblueTest {
  /**
   * Test {@link LambdaHandler#all()}.
   * <p>
   * Method under test: {@link LambdaHandler#all()}
   */
  @Test
  @DisplayName("Test all()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List LambdaHandler.all()"})
  void testAll() {
    // Arrange and Act
    List<LambdaHandler> actualAllResult = LambdaHandler.all();

    // Assert
    assertEquals(34, actualAllResult.size());
    assertEquals("sleeper.athena.composite.IteratorApplyingCompositeHandler", actualAllResult.get(1).getHandler());
    LambdaHandler getResult = actualAllResult.get(0);
    assertEquals("sleeper.athena.composite.SimpleCompositeHandler", getResult.getHandler());
    LambdaHandler getResult2 = actualAllResult.get(2);
    assertEquals("sleeper.bulkimport.starter.BulkImportStarterLambda", getResult2.getHandler());
    LambdaHandler getResult3 = actualAllResult.get(5);
    assertEquals("sleeper.ingest.batcher.job.creator.IngestBatcherJobCreatorLambda::eventHandler",
        getResult3.getHandler());
    LambdaHandler getResult4 = actualAllResult.get(4);
    assertEquals("sleeper.ingest.batcher.submitter.IngestBatcherSubmitterLambda::handleRequest",
        getResult4.getHandler());
    LambdaHandler getResult5 = actualAllResult.get(3);
    assertEquals("sleeper.ingest.starter.RunIngestTasksLambda::eventHandler", getResult5.getHandler());
    LambdaHandler getResult6 = actualAllResult.get(28);
    assertEquals("sleeper.statestore.lambda.snapshot.TransactionLogSnapshotCreationLambda::handleRequest",
        getResult6.getHandler());
    LambdaHandler getResult7 = actualAllResult.get(30);
    assertEquals("sleeper.statestore.lambda.snapshot.TransactionLogSnapshotDeletionLambda::handleRequest",
        getResult7.getHandler());
    LambdaHandler getResult8 = actualAllResult.get(29);
    assertEquals("sleeper.statestore.lambda.snapshot.TransactionLogSnapshotDeletionTriggerLambda::handleRequest",
        getResult8.getHandler());
    LambdaHandler getResult9 = actualAllResult.get(33);
    assertEquals("sleeper.statestore.lambda.transaction.TransactionLogFollowerLambda::handleRequest",
        getResult9.getHandler());
    LambdaHandler getResult10 = actualAllResult.get(Integer.SIZE);
    assertEquals("sleeper.statestore.lambda.transaction.TransactionLogTransactionDeletionLambda::handleRequest",
        getResult10.getHandler());
    LambdaHandler getResult11 = actualAllResult.get(31);
    assertEquals("sleeper.statestore.lambda.transaction.TransactionLogTransactionDeletionTriggerLambda::handleRequest",
        getResult11.getHandler());
    assertEquals(1, getResult.getOptionalStacks().size());
    assertEquals(1, getResult5.getOptionalStacks().size());
    List<OptionalStack> optionalStacks = getResult4.getOptionalStacks();
    assertEquals(1, optionalStacks.size());
    assertEquals(4, getResult2.getOptionalStacks().size());
    List<OptionalStack> optionalStacks2 = getResult6.getOptionalStacks();
    assertTrue(optionalStacks2.isEmpty());
    assertEquals(optionalStacks, getResult3.getOptionalStacks());
    assertSame(optionalStacks2, getResult8.getOptionalStacks());
    assertSame(optionalStacks2, getResult7.getOptionalStacks());
    assertSame(optionalStacks2, getResult11.getOptionalStacks());
    assertSame(optionalStacks2, getResult9.getOptionalStacks());
    assertSame(optionalStacks2, getResult10.getOptionalStacks());
  }

  /**
   * Test Builder {@link Builder#build()}.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Builder#build()}
   *   <li>{@link Builder#handler(String)}
   *   <li>{@link Builder#jar(LambdaJar)}
   *   <li>{@link Builder#optionalStacks(List)}
   * </ul>
   */
  @Test
  @DisplayName("Test Builder build()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"LambdaHandler Builder.build()", "Builder Builder.handler(String)",
      "Builder Builder.jar(LambdaJar)", "Builder Builder.optionalStacks(List)"})
  void testBuilderBuild() {
    // Arrange
    Builder jarResult = LambdaHandler.builder().handler("Handler").jar(LambdaJar.ATHENA);
    ArrayList<OptionalStack> optionalStacks = new ArrayList<>();

    // Act
    LambdaHandler actualBuildResult = jarResult.optionalStacks(optionalStacks).build();

    // Assert
    assertEquals("Handler", actualBuildResult.getHandler());
    LambdaJar jar = actualBuildResult.getJar();
    assertEquals("athena-0.29.0-SNAPSHOT.jar", jar.getFilename());
    assertEquals("athena-lambda", jar.getImageName());
    List<OptionalStack> optionalStacks2 = actualBuildResult.getOptionalStacks();
    assertTrue(optionalStacks2.isEmpty());
    assertSame(optionalStacks, optionalStacks2);
  }

  /**
   * Test Builder {@link Builder#core()}.
   * <p>
   * Method under test: {@link Builder#core()}
   */
  @Test
  @DisplayName("Test Builder core()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.core()"})
  void testBuilderCore() {
    // Arrange
    Builder builderResult = LambdaHandler.builder();

    // Act and Assert
    assertSame(builderResult, builderResult.core());
  }

  /**
   * Test Builder {@link Builder#optionalStack(OptionalStack)}.
   * <p>
   * Method under test: {@link Builder#optionalStack(OptionalStack)}
   */
  @Test
  @DisplayName("Test Builder optionalStack(OptionalStack)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.optionalStack(OptionalStack)"})
  void testBuilderOptionalStack() {
    // Arrange
    Builder builderResult = LambdaHandler.builder();

    // Act and Assert
    assertSame(builderResult, builderResult.optionalStack(OptionalStack.IngestStack));
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link LambdaHandler#toString()}
   *   <li>{@link LambdaHandler#getHandler()}
   *   <li>{@link LambdaHandler#getJar()}
   *   <li>{@link LambdaHandler#getOptionalStacks()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String LambdaHandler.getHandler()", "LambdaJar LambdaHandler.getJar()",
      "List LambdaHandler.getOptionalStacks()", "String LambdaHandler.toString()"})
  void testGettersAndSetters() {
    // Arrange
    LambdaHandler lambdaHandler = LambdaHandler.ATHENA_ITERATORS_COMPOSITE;

    // Act
    String actualToStringResult = lambdaHandler.toString();
    String actualHandler = lambdaHandler.getHandler();
    LambdaJar actualJar = lambdaHandler.getJar();
    List<OptionalStack> actualOptionalStacks = lambdaHandler.getOptionalStacks();

    // Assert
    assertEquals(
        "LambdaHandler{jar=LambdaJar{filename=athena-0.29.0-SNAPSHOT.jar, imageName=athena-lambda}, handler"
            + "=sleeper.athena.composite.IteratorApplyingCompositeHandler, optionalStacks=[AthenaStack]}",
        actualToStringResult);
    assertEquals("sleeper.athena.composite.IteratorApplyingCompositeHandler", actualHandler);
    assertEquals(1, actualOptionalStacks.size());
    assertEquals(OptionalStack.AthenaStack, actualOptionalStacks.get(0));
    assertSame(actualJar.ATHENA, actualJar);
  }

  /**
   * Test {@link LambdaHandler#isDeployed(Collection)}.
   * <ul>
   *   <li>Given {@link LambdaHandler#ATHENA_ITERATORS_COMPOSITE}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LambdaHandler#isDeployed(Collection)}
   */
  @Test
  @DisplayName("Test isDeployed(Collection); given ATHENA_ITERATORS_COMPOSITE; then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean LambdaHandler.isDeployed(Collection)"})
  void testIsDeployed_givenAthena_iterators_composite_thenReturnFalse() {
    // Arrange
    ArrayList<OptionalStack> stacks = new ArrayList<>();
    stacks.add(OptionalStack.IngestStack);

    // Act and Assert
    assertFalse(LambdaHandler.ATHENA_ITERATORS_COMPOSITE.isDeployed(stacks));
  }

  /**
   * Test {@link LambdaHandler#isDeployed(Collection)}.
   * <ul>
   *   <li>Given {@link LambdaHandler#ATHENA_ITERATORS_COMPOSITE}.</li>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LambdaHandler#isDeployed(Collection)}
   */
  @Test
  @DisplayName("Test isDeployed(Collection); given ATHENA_ITERATORS_COMPOSITE; when ArrayList(); then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean LambdaHandler.isDeployed(Collection)"})
  void testIsDeployed_givenAthena_iterators_composite_whenArrayList_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(LambdaHandler.ATHENA_ITERATORS_COMPOSITE.isDeployed(new ArrayList<>()));
  }

  /**
   * Test {@link LambdaHandler#isDeployed(Collection)}.
   * <ul>
   *   <li>Given {@link LambdaHandler#AUTO_DELETE_S3_OBJECTS}.</li>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LambdaHandler#isDeployed(Collection)}
   */
  @Test
  @DisplayName("Test isDeployed(Collection); given AUTO_DELETE_S3_OBJECTS; when ArrayList(); then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean LambdaHandler.isDeployed(Collection)"})
  void testIsDeployed_givenAuto_delete_s3_objects_whenArrayList_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(LambdaHandler.AUTO_DELETE_S3_OBJECTS.isDeployed(new ArrayList<>()));
  }

  /**
   * Test {@link LambdaHandler#isDeployed(Collection)}.
   * <ul>
   *   <li>Given {@code IngestBatcherStack}.</li>
   *   <li>When {@link ArrayList#ArrayList()} add {@code IngestBatcherStack}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LambdaHandler#isDeployed(Collection)}
   */
  @Test
  @DisplayName("Test isDeployed(Collection); given 'IngestBatcherStack'; when ArrayList() add 'IngestBatcherStack'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean LambdaHandler.isDeployed(Collection)"})
  void testIsDeployed_givenIngestBatcherStack_whenArrayListAddIngestBatcherStack() {
    // Arrange
    ArrayList<OptionalStack> stacks = new ArrayList<>();
    stacks.add(OptionalStack.IngestBatcherStack);
    stacks.add(OptionalStack.IngestStack);

    // Act and Assert
    assertFalse(LambdaHandler.ATHENA_ITERATORS_COMPOSITE.isDeployed(stacks));
  }

  /**
   * Test {@link LambdaHandler#isDeployed(Collection)}.
   * <ul>
   *   <li>Given {@link LambdaHandler#INGEST_BATCHER_JOB_CREATOR}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LambdaHandler#isDeployed(Collection)}
   */
  @Test
  @DisplayName("Test isDeployed(Collection); given INGEST_BATCHER_JOB_CREATOR; then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean LambdaHandler.isDeployed(Collection)"})
  void testIsDeployed_givenIngest_batcher_job_creator_thenReturnTrue() {
    // Arrange
    ArrayList<OptionalStack> stacks = new ArrayList<>();
    stacks.add(OptionalStack.IngestBatcherStack);
    stacks.add(OptionalStack.IngestStack);

    // Act and Assert
    assertTrue(LambdaHandler.INGEST_BATCHER_JOB_CREATOR.isDeployed(stacks));
  }

  /**
   * Test {@link LambdaHandler#isDeployedOptional(Collection)}.
   * <ul>
   *   <li>Given {@link LambdaHandler#ATHENA_ITERATORS_COMPOSITE}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LambdaHandler#isDeployedOptional(Collection)}
   */
  @Test
  @DisplayName("Test isDeployedOptional(Collection); given ATHENA_ITERATORS_COMPOSITE; then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean LambdaHandler.isDeployedOptional(Collection)"})
  void testIsDeployedOptional_givenAthena_iterators_composite_thenReturnFalse() {
    // Arrange
    ArrayList<OptionalStack> stacks = new ArrayList<>();
    stacks.add(OptionalStack.IngestStack);

    // Act and Assert
    assertFalse(LambdaHandler.ATHENA_ITERATORS_COMPOSITE.isDeployedOptional(stacks));
  }

  /**
   * Test {@link LambdaHandler#isDeployedOptional(Collection)}.
   * <ul>
   *   <li>Given {@code IngestBatcherStack}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LambdaHandler#isDeployedOptional(Collection)}
   */
  @Test
  @DisplayName("Test isDeployedOptional(Collection); given 'IngestBatcherStack'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean LambdaHandler.isDeployedOptional(Collection)"})
  void testIsDeployedOptional_givenIngestBatcherStack() {
    // Arrange
    ArrayList<OptionalStack> stacks = new ArrayList<>();
    stacks.add(OptionalStack.IngestBatcherStack);
    stacks.add(OptionalStack.IngestStack);

    // Act and Assert
    assertFalse(LambdaHandler.ATHENA_ITERATORS_COMPOSITE.isDeployedOptional(stacks));
  }

  /**
   * Test {@link LambdaHandler#isDeployedOptional(Collection)}.
   * <ul>
   *   <li>Given {@link LambdaHandler#INGEST_BATCHER_JOB_CREATOR}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LambdaHandler#isDeployedOptional(Collection)}
   */
  @Test
  @DisplayName("Test isDeployedOptional(Collection); given INGEST_BATCHER_JOB_CREATOR; then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean LambdaHandler.isDeployedOptional(Collection)"})
  void testIsDeployedOptional_givenIngest_batcher_job_creator_thenReturnTrue() {
    // Arrange
    ArrayList<OptionalStack> stacks = new ArrayList<>();
    stacks.add(OptionalStack.IngestBatcherStack);
    stacks.add(OptionalStack.IngestStack);

    // Act and Assert
    assertTrue(LambdaHandler.INGEST_BATCHER_JOB_CREATOR.isDeployedOptional(stacks));
  }

  /**
   * Test {@link LambdaHandler#isDeployedOptional(Collection)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LambdaHandler#isDeployedOptional(Collection)}
   */
  @Test
  @DisplayName("Test isDeployedOptional(Collection); when ArrayList(); then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean LambdaHandler.isDeployedOptional(Collection)"})
  void testIsDeployedOptional_whenArrayList_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(LambdaHandler.ATHENA_ITERATORS_COMPOSITE.isDeployedOptional(new ArrayList<>()));
  }

  /**
   * Test {@link LambdaHandler#equals(Object)}, and {@link LambdaHandler#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link LambdaHandler#equals(Object)}
   *   <li>{@link LambdaHandler#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean LambdaHandler.equals(Object)", "int LambdaHandler.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    LambdaHandler lambdaHandler = LambdaHandler.ATHENA_ITERATORS_COMPOSITE;
    LambdaHandler lambdaHandler2 = LambdaHandler.ATHENA_ITERATORS_COMPOSITE;

    // Act and Assert
    assertEquals(lambdaHandler, lambdaHandler2);
    int expectedHashCodeResult = lambdaHandler.hashCode();
    assertEquals(expectedHashCodeResult, lambdaHandler2.hashCode());
  }

  /**
   * Test {@link LambdaHandler#equals(Object)}, and {@link LambdaHandler#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link LambdaHandler#equals(Object)}
   *   <li>{@link LambdaHandler#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean LambdaHandler.equals(Object)", "int LambdaHandler.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    LambdaHandler lambdaHandler = LambdaHandler.ATHENA_ITERATORS_COMPOSITE;

    // Act and Assert
    assertEquals(lambdaHandler, lambdaHandler);
    int expectedHashCodeResult = lambdaHandler.hashCode();
    assertEquals(expectedHashCodeResult, lambdaHandler.hashCode());
  }

  /**
   * Test {@link LambdaHandler#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link LambdaHandler#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean LambdaHandler.equals(Object)", "int LambdaHandler.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(LambdaHandler.ATHENA_SIMPLE_COMPOSITE, LambdaHandler.ATHENA_ITERATORS_COMPOSITE);
  }

  /**
   * Test {@link LambdaHandler#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link LambdaHandler#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean LambdaHandler.equals(Object)", "int LambdaHandler.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange, Act and Assert
    assertNotEquals(LambdaHandler.AUTO_DELETE_S3_OBJECTS, LambdaHandler.ATHENA_ITERATORS_COMPOSITE);
  }

  /**
   * Test {@link LambdaHandler#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link LambdaHandler#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean LambdaHandler.equals(Object)", "int LambdaHandler.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(LambdaHandler.ATHENA_ITERATORS_COMPOSITE, null);
  }

  /**
   * Test {@link LambdaHandler#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link LambdaHandler#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean LambdaHandler.equals(Object)", "int LambdaHandler.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(LambdaHandler.ATHENA_ITERATORS_COMPOSITE, "Different type to LambdaHandler");
  }
}
