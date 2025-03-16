package sleeper.compaction.task.creation;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.amazonaws.services.lambda.runtime.Context;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.compaction.task.creation.SafeTerminationTest.FakeContext;
import sleeper.task.common.EC2InstanceDetails;

class SafeTerminationLambdaDiffblueTest {
  /**
   * Test {@link SafeTerminationLambda#findEmptyInstances(Iterable, int, Context)}.
   * <p>
   * Method under test: {@link SafeTerminationLambda#findEmptyInstances(Iterable, int, Context)}
   */
  @Test
  @DisplayName("Test findEmptyInstances(Iterable, int, Context)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Set SafeTerminationLambda.findEmptyInstances(Iterable, int, Context)"})
  void testFindEmptyInstances() {
    // Arrange
    ArrayList<EC2InstanceDetails> detailsIt = new ArrayList<>();
    detailsIt.add(new EC2InstanceDetails("42", "detailsIt",
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(), 3, 3, 3, 3, 10, 3));

    // Act
    Set<String> actualFindEmptyInstancesResult = SafeTerminationLambda.findEmptyInstances(detailsIt, 3,
        new FakeContext());

    // Assert
    assertTrue(actualFindEmptyInstancesResult.isEmpty());
  }

  /**
   * Test {@link SafeTerminationLambda#findEmptyInstances(Iterable, int, Context)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link SafeTerminationLambda#findEmptyInstances(Iterable, int, Context)}
   */
  @Test
  @DisplayName("Test findEmptyInstances(Iterable, int, Context); when ArrayList(); then return Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Set SafeTerminationLambda.findEmptyInstances(Iterable, int, Context)"})
  void testFindEmptyInstances_whenArrayList_thenReturnEmpty() {
    // Arrange
    ArrayList<EC2InstanceDetails> detailsIt = new ArrayList<>();

    // Act
    Set<String> actualFindEmptyInstancesResult = SafeTerminationLambda.findEmptyInstances(detailsIt, 3,
        new FakeContext());

    // Assert
    assertTrue(actualFindEmptyInstancesResult.isEmpty());
  }

  /**
   * Test {@link SafeTerminationLambda#findEmptyInstances(Iterable, int, Context)}.
   * <ul>
   *   <li>When minus one.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SafeTerminationLambda#findEmptyInstances(Iterable, int, Context)}
   */
  @Test
  @DisplayName("Test findEmptyInstances(Iterable, int, Context); when minus one; then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Set SafeTerminationLambda.findEmptyInstances(Iterable, int, Context)"})
  void testFindEmptyInstances_whenMinusOne_thenThrowIllegalArgumentException() {
    // Arrange
    LinkedHashSet<EC2InstanceDetails> detailsIt = new LinkedHashSet<>();
    detailsIt.add(new EC2InstanceDetails("42", "Instance Arn",
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(), 1, 1, 1, 1, 10, 3));

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> SafeTerminationLambda.findEmptyInstances(detailsIt, -1, new FakeContext()));
  }

  /**
   * Test {@link SafeTerminationLambda#findEmptyInstances(Iterable, int, Context)}.
   * <ul>
   *   <li>When zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link SafeTerminationLambda#findEmptyInstances(Iterable, int, Context)}
   */
  @Test
  @DisplayName("Test findEmptyInstances(Iterable, int, Context); when zero")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Set SafeTerminationLambda.findEmptyInstances(Iterable, int, Context)"})
  void testFindEmptyInstances_whenZero() {
    // Arrange
    LinkedHashSet<EC2InstanceDetails> detailsIt = new LinkedHashSet<>();
    detailsIt.add(new EC2InstanceDetails("42", "Instance Arn",
        LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(), 1, 1, 1, 1, 10, 3));

    // Act
    Set<String> actualFindEmptyInstancesResult = SafeTerminationLambda.findEmptyInstances(detailsIt, 0,
        new FakeContext());

    // Assert
    assertTrue(actualFindEmptyInstancesResult.isEmpty());
  }
}
