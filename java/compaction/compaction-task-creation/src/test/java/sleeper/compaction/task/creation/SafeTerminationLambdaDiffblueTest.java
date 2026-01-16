package sleeper.compaction.task.creation;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.amazonaws.services.lambda.runtime.Context;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.task.common.EC2InstanceDetails;

class SafeTerminationLambdaDiffblueTest {
  /**
   * Test {@link SafeTerminationLambda#findEmptyInstances(Iterable, int, Context)}.
   *
   * <ul>
   *   <li>Given {@link IllegalArgumentException#IllegalArgumentException()}.
   * </ul>
   *
   * <p>Method under test: {@link SafeTerminationLambda#findEmptyInstances(Iterable, int, Context)}
   */
  @Test
  @DisplayName("Test findEmptyInstances(Iterable, int, Context); given IllegalArgumentException()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Set SafeTerminationLambda.findEmptyInstances(Iterable, int, Context)"})
  void testFindEmptyInstances_givenIllegalArgumentException() {
    // Arrange
    ArrayList<EC2InstanceDetails> detailsIt = new ArrayList<>();
    EC2InstanceDetails ec2InstanceDetails =
        new EC2InstanceDetails(
            "42",
            "detailsIt",
            LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
            3,
            3,
            3,
            3,
            10,
            3);
    detailsIt.add(ec2InstanceDetails);

    Context context = mock(Context.class);
    when(context.getRemainingTimeInMillis()).thenThrow(new IllegalArgumentException());

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> SafeTerminationLambda.findEmptyInstances(detailsIt, 3, context));
    verify(context).getRemainingTimeInMillis();
  }

  /**
   * Test {@link SafeTerminationLambda#findEmptyInstances(Iterable, int, Context)}.
   *
   * <ul>
   *   <li>Given one hundred ninety-nine.
   * </ul>
   *
   * <p>Method under test: {@link SafeTerminationLambda#findEmptyInstances(Iterable, int, Context)}
   */
  @Test
  @DisplayName("Test findEmptyInstances(Iterable, int, Context); given one hundred ninety-nine")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Set SafeTerminationLambda.findEmptyInstances(Iterable, int, Context)"})
  void testFindEmptyInstances_givenOneHundredNinetyNine() {
    // Arrange
    ArrayList<EC2InstanceDetails> detailsIt = new ArrayList<>();
    EC2InstanceDetails ec2InstanceDetails =
        new EC2InstanceDetails(
            "42",
            "detailsIt",
            LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
            3,
            3,
            3,
            3,
            10,
            3);
    detailsIt.add(ec2InstanceDetails);

    Context context = mock(Context.class);
    when(context.getRemainingTimeInMillis()).thenReturn(199);

    // Act
    Set<String> actualFindEmptyInstancesResult =
        SafeTerminationLambda.findEmptyInstances(detailsIt, 3, context);

    // Assert
    verify(context).getRemainingTimeInMillis();
    assertTrue(actualFindEmptyInstancesResult.isEmpty());
  }

  /**
   * Test {@link SafeTerminationLambda#findEmptyInstances(Iterable, int, Context)}.
   *
   * <ul>
   *   <li>Given two hundred.
   * </ul>
   *
   * <p>Method under test: {@link SafeTerminationLambda#findEmptyInstances(Iterable, int, Context)}
   */
  @Test
  @DisplayName("Test findEmptyInstances(Iterable, int, Context); given two hundred")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Set SafeTerminationLambda.findEmptyInstances(Iterable, int, Context)"})
  void testFindEmptyInstances_givenTwoHundred() {
    // Arrange
    ArrayList<EC2InstanceDetails> detailsIt = new ArrayList<>();
    EC2InstanceDetails ec2InstanceDetails =
        new EC2InstanceDetails(
            "42",
            "detailsIt",
            LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
            3,
            3,
            3,
            3,
            10,
            3);
    detailsIt.add(ec2InstanceDetails);

    Context context = mock(Context.class);
    when(context.getRemainingTimeInMillis()).thenReturn(200);

    // Act
    Set<String> actualFindEmptyInstancesResult =
        SafeTerminationLambda.findEmptyInstances(detailsIt, 3, context);

    // Assert
    verify(context).getRemainingTimeInMillis();
    assertTrue(actualFindEmptyInstancesResult.isEmpty());
  }

  /**
   * Test {@link SafeTerminationLambda#findEmptyInstances(Iterable, int, Context)}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link SafeTerminationLambda#findEmptyInstances(Iterable, int, Context)}
   */
  @Test
  @DisplayName(
      "Test findEmptyInstances(Iterable, int, Context); when ArrayList(); then return Empty")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Set SafeTerminationLambda.findEmptyInstances(Iterable, int, Context)"})
  void testFindEmptyInstances_whenArrayList_thenReturnEmpty() {
    // Arrange and Act
    Set<String> actualFindEmptyInstancesResult =
        SafeTerminationLambda.findEmptyInstances(new ArrayList<>(), 3, mock(Context.class));

    // Assert
    assertTrue(actualFindEmptyInstancesResult.isEmpty());
  }

  /**
   * Test {@link SafeTerminationLambda#findEmptyInstances(Iterable, int, Context)}.
   *
   * <ul>
   *   <li>When minus one.
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link SafeTerminationLambda#findEmptyInstances(Iterable, int, Context)}
   */
  @Test
  @DisplayName(
      "Test findEmptyInstances(Iterable, int, Context); when minus one; then throw IllegalArgumentException")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Set SafeTerminationLambda.findEmptyInstances(Iterable, int, Context)"})
  void testFindEmptyInstances_whenMinusOne_thenThrowIllegalArgumentException() {
    // Arrange
    LinkedHashSet<EC2InstanceDetails> detailsIt = new LinkedHashSet<>();
    EC2InstanceDetails ec2InstanceDetails =
        new EC2InstanceDetails(
            "42",
            "Instance Arn",
            LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
            1,
            1,
            1,
            1,
            10,
            3);
    detailsIt.add(ec2InstanceDetails);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> SafeTerminationLambda.findEmptyInstances(detailsIt, -1, mock(Context.class)));
  }

  /**
   * Test {@link SafeTerminationLambda#findEmptyInstances(Iterable, int, Context)}.
   *
   * <ul>
   *   <li>When zero.
   * </ul>
   *
   * <p>Method under test: {@link SafeTerminationLambda#findEmptyInstances(Iterable, int, Context)}
   */
  @Test
  @DisplayName("Test findEmptyInstances(Iterable, int, Context); when zero")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Set SafeTerminationLambda.findEmptyInstances(Iterable, int, Context)"})
  void testFindEmptyInstances_whenZero() {
    // Arrange
    LinkedHashSet<EC2InstanceDetails> detailsIt = new LinkedHashSet<>();
    EC2InstanceDetails ec2InstanceDetails =
        new EC2InstanceDetails(
            "42",
            "Instance Arn",
            LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(),
            1,
            1,
            1,
            1,
            10,
            3);
    detailsIt.add(ec2InstanceDetails);

    // Act
    Set<String> actualFindEmptyInstancesResult =
        SafeTerminationLambda.findEmptyInstances(detailsIt, 0, mock(Context.class));

    // Assert
    assertTrue(actualFindEmptyInstancesResult.isEmpty());
  }
}
