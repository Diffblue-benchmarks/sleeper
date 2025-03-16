package sleeper.core.statestore.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.statestore.FileReference;
import sleeper.core.statestore.ReplaceFileReferencesRequest;
import sleeper.core.statestore.ReplaceFileReferencesRequest.Builder;

class ReplaceRequestsFailedExceptionDiffblueTest {
  /**
   * Test {@link ReplaceRequestsFailedException#ReplaceRequestsFailedException(List, Exception)}.
   * <p>
   * Method under test: {@link ReplaceRequestsFailedException#ReplaceRequestsFailedException(List, Exception)}
   */
  @Test
  @DisplayName("Test new ReplaceRequestsFailedException(List, Exception)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ReplaceRequestsFailedException.<init>(List, Exception)"})
  void testNewReplaceRequestsFailedException() {
    // Arrange
    ArrayList<ReplaceFileReferencesRequest> failedRequests = new ArrayList<>();
    Exception failure = new Exception("foo");

    // Act
    ReplaceRequestsFailedException actualReplaceRequestsFailedException = new ReplaceRequestsFailedException(
        failedRequests, failure);

    // Assert
    assertEquals("0 replace file reference requests failed to update the state store",
        actualReplaceRequestsFailedException.getLocalizedMessage());
    assertEquals("0 replace file reference requests failed to update the state store",
        actualReplaceRequestsFailedException.getMessage());
    assertEquals(0, actualReplaceRequestsFailedException.getSuppressed().length);
    List<Exception> failures = actualReplaceRequestsFailedException.getFailures();
    assertEquals(1, failures.size());
    assertTrue(actualReplaceRequestsFailedException.getFailedRequests().isEmpty());
    assertTrue(actualReplaceRequestsFailedException.getSuccessfulRequests().isEmpty());
    assertSame(failure, actualReplaceRequestsFailedException.getCause());
    assertSame(failure, failures.get(0));
  }

  /**
   * Test {@link ReplaceRequestsFailedException#ReplaceRequestsFailedException(List, Exception)}.
   * <p>
   * Method under test: {@link ReplaceRequestsFailedException#ReplaceRequestsFailedException(List, Exception)}
   */
  @Test
  @DisplayName("Test new ReplaceRequestsFailedException(List, Exception)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ReplaceRequestsFailedException.<init>(List, Exception)"})
  void testNewReplaceRequestsFailedException2() {
    // Arrange
    ArrayList<ReplaceFileReferencesRequest> failedRequests = new ArrayList<>();
    Builder builderResult = ReplaceFileReferencesRequest.builder();
    Builder jobRunIdResult = builderResult.inputFiles(new ArrayList<>()).jobId("42").jobRunId("42");
    FileReference.Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference newReference = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    ReplaceFileReferencesRequest buildResult = jobRunIdResult.newReference(newReference).taskId("42").build();
    failedRequests.add(buildResult);

    // Act
    ReplaceRequestsFailedException actualReplaceRequestsFailedException = new ReplaceRequestsFailedException(
        failedRequests, new Exception("foo"));

    // Assert
    assertEquals("1 replace file reference requests failed to update the state store",
        actualReplaceRequestsFailedException.getLocalizedMessage());
    assertEquals("1 replace file reference requests failed to update the state store",
        actualReplaceRequestsFailedException.getMessage());
    assertSame(failedRequests, actualReplaceRequestsFailedException.getFailedRequests());
  }

  /**
   * Test {@link ReplaceRequestsFailedException#ReplaceRequestsFailedException(List, Exception)}.
   * <p>
   * Method under test: {@link ReplaceRequestsFailedException#ReplaceRequestsFailedException(List, Exception)}
   */
  @Test
  @DisplayName("Test new ReplaceRequestsFailedException(List, Exception)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ReplaceRequestsFailedException.<init>(List, Exception)"})
  void testNewReplaceRequestsFailedException3() {
    // Arrange
    ArrayList<ReplaceFileReferencesRequest> failedRequests = new ArrayList<>();
    Builder builderResult = ReplaceFileReferencesRequest.builder();
    Builder jobRunIdResult = builderResult.inputFiles(new ArrayList<>()).jobId("42").jobRunId("42");
    FileReference.Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference newReference = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    ReplaceFileReferencesRequest buildResult = jobRunIdResult.newReference(newReference).taskId("42").build();
    failedRequests.add(buildResult);
    Builder builderResult2 = ReplaceFileReferencesRequest.builder();
    Builder jobRunIdResult2 = builderResult2.inputFiles(new ArrayList<>()).jobId("42").jobRunId("42");
    FileReference.Builder jobIdResult2 = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference newReference2 = jobIdResult2
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    ReplaceFileReferencesRequest buildResult2 = jobRunIdResult2.newReference(newReference2).taskId("42").build();
    failedRequests.add(buildResult2);

    // Act
    ReplaceRequestsFailedException actualReplaceRequestsFailedException = new ReplaceRequestsFailedException(
        failedRequests, new Exception("foo"));

    // Assert
    assertEquals("2 replace file reference requests failed to update the state store",
        actualReplaceRequestsFailedException.getLocalizedMessage());
    assertEquals("2 replace file reference requests failed to update the state store",
        actualReplaceRequestsFailedException.getMessage());
    List<ReplaceFileReferencesRequest> failedRequests2 = actualReplaceRequestsFailedException.getFailedRequests();
    assertEquals(2, failedRequests2.size());
    assertEquals(failedRequests2.get(0), failedRequests2.get(1));
  }

  /**
   * Test {@link ReplaceRequestsFailedException#ReplaceRequestsFailedException(List, List, List)}.
   * <p>
   * Method under test: {@link ReplaceRequestsFailedException#ReplaceRequestsFailedException(List, List, List)}
   */
  @Test
  @DisplayName("Test new ReplaceRequestsFailedException(List, List, List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ReplaceRequestsFailedException.<init>(List, List, List)"})
  void testNewReplaceRequestsFailedException4() {
    // Arrange
    ArrayList<ReplaceFileReferencesRequest> successfulRequests = new ArrayList<>();
    ArrayList<ReplaceFileReferencesRequest> failedRequests = new ArrayList<>();

    ArrayList<Exception> failures = new ArrayList<>();
    Exception exception = new Exception("foo");
    failures.add(exception);

    // Act
    ReplaceRequestsFailedException actualReplaceRequestsFailedException = new ReplaceRequestsFailedException(
        successfulRequests, failedRequests, failures);

    // Assert
    assertEquals("0 replace file reference requests failed to update the state store",
        actualReplaceRequestsFailedException.getLocalizedMessage());
    assertEquals("0 replace file reference requests failed to update the state store",
        actualReplaceRequestsFailedException.getMessage());
    assertEquals(0, actualReplaceRequestsFailedException.getSuppressed().length);
    assertTrue(actualReplaceRequestsFailedException.getFailedRequests().isEmpty());
    assertTrue(actualReplaceRequestsFailedException.getSuccessfulRequests().isEmpty());
    assertSame(exception, actualReplaceRequestsFailedException.getCause());
    assertSame(failures, actualReplaceRequestsFailedException.getFailures());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link ReplaceRequestsFailedException#getFailedRequests()}
   *   <li>{@link ReplaceRequestsFailedException#getFailures()}
   *   <li>{@link ReplaceRequestsFailedException#getSuccessfulRequests()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List ReplaceRequestsFailedException.getFailedRequests()",
      "List ReplaceRequestsFailedException.getFailures()",
      "List ReplaceRequestsFailedException.getSuccessfulRequests()"})
  void testGettersAndSetters() {
    // Arrange
    ArrayList<ReplaceFileReferencesRequest> failedRequests = new ArrayList<>();
    Exception failure = new Exception("foo");
    ReplaceRequestsFailedException replaceRequestsFailedException = new ReplaceRequestsFailedException(failedRequests,
        failure);

    // Act
    List<ReplaceFileReferencesRequest> actualFailedRequests = replaceRequestsFailedException.getFailedRequests();
    List<Exception> actualFailures = replaceRequestsFailedException.getFailures();
    List<ReplaceFileReferencesRequest> actualSuccessfulRequests = replaceRequestsFailedException
        .getSuccessfulRequests();

    // Assert
    assertEquals(1, actualFailures.size());
    assertTrue(actualFailedRequests.isEmpty());
    assertTrue(actualSuccessfulRequests.isEmpty());
    assertSame(failure, actualFailures.get(0));
    assertSame(failedRequests, actualFailedRequests);
  }
}
