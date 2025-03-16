package sleeper.core.statestore.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
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
import sleeper.core.statestore.FileReference.Builder;
import sleeper.core.statestore.SplitFileReferenceRequest;

class SplitRequestsFailedExceptionDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link SplitRequestsFailedException#SplitRequestsFailedException(String, List, List)}
   *   <li>{@link SplitRequestsFailedException#getFailedRequests()}
   *   <li>{@link SplitRequestsFailedException#getSuccessfulRequests()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SplitRequestsFailedException.<init>(String, List, List)",
      "List SplitRequestsFailedException.getFailedRequests()",
      "List SplitRequestsFailedException.getSuccessfulRequests()"})
  void testGettersAndSetters() {
    // Arrange
    ArrayList<SplitFileReferenceRequest> successfulRequests = new ArrayList<>();
    ArrayList<SplitFileReferenceRequest> failedRequests = new ArrayList<>();

    // Act
    SplitRequestsFailedException actualSplitRequestsFailedException = new SplitRequestsFailedException(
        "An error occurred", successfulRequests, failedRequests);
    List<SplitFileReferenceRequest> actualFailedRequests = actualSplitRequestsFailedException.getFailedRequests();
    List<SplitFileReferenceRequest> actualSuccessfulRequests = actualSplitRequestsFailedException
        .getSuccessfulRequests();

    // Assert
    assertEquals("An error occurred", actualSplitRequestsFailedException.getMessage());
    assertNull(actualSplitRequestsFailedException.getCause());
    assertEquals(0, actualSplitRequestsFailedException.getSuppressed().length);
    assertTrue(actualFailedRequests.isEmpty());
    assertTrue(actualSuccessfulRequests.isEmpty());
    assertSame(failedRequests, actualFailedRequests);
    assertSame(successfulRequests, actualSuccessfulRequests);
  }

  /**
   * Test {@link SplitRequestsFailedException#SplitRequestsFailedException(List, List, Throwable)}.
   * <p>
   * Method under test: {@link SplitRequestsFailedException#SplitRequestsFailedException(List, List, Throwable)}
   */
  @Test
  @DisplayName("Test new SplitRequestsFailedException(List, List, Throwable)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SplitRequestsFailedException.<init>(List, List, Throwable)"})
  void testNewSplitRequestsFailedException() {
    // Arrange
    ArrayList<SplitFileReferenceRequest> successfulRequests = new ArrayList<>();
    ArrayList<SplitFileReferenceRequest> failedRequests = new ArrayList<>();
    Throwable cause = new Throwable();

    // Act
    SplitRequestsFailedException actualSplitRequestsFailedException = new SplitRequestsFailedException(
        successfulRequests, failedRequests, cause);

    // Assert
    assertEquals("0 split requests failed to update the state store",
        actualSplitRequestsFailedException.getLocalizedMessage());
    assertEquals("0 split requests failed to update the state store", actualSplitRequestsFailedException.getMessage());
    assertEquals(0, actualSplitRequestsFailedException.getSuppressed().length);
    assertTrue(actualSplitRequestsFailedException.getFailedRequests().isEmpty());
    assertTrue(actualSplitRequestsFailedException.getSuccessfulRequests().isEmpty());
    assertSame(cause, actualSplitRequestsFailedException.getCause());
  }

  /**
   * Test {@link SplitRequestsFailedException#SplitRequestsFailedException(List, List, Throwable)}.
   * <ul>
   *   <li>Then return SuccessfulRequests is {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SplitRequestsFailedException#SplitRequestsFailedException(List, List, Throwable)}
   */
  @Test
  @DisplayName("Test new SplitRequestsFailedException(List, List, Throwable); then return SuccessfulRequests is ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SplitRequestsFailedException.<init>(List, List, Throwable)"})
  void testNewSplitRequestsFailedException_thenReturnSuccessfulRequestsIsArrayList() {
    // Arrange
    ArrayList<FileReference> newReferences = new ArrayList<>();
    Builder jobIdResult = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference buildResult = jobIdResult
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    newReferences.add(buildResult);
    Builder jobIdResult2 = FileReference.builder().countApproximate(true).filename("foo.txt").jobId("42");
    FileReference oldReference = jobIdResult2
        .lastStateStoreUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())
        .numberOfRecords(1L)
        .onlyContainsDataForThisPartition(true)
        .partitionId("42")
        .build();
    SplitFileReferenceRequest splitFileReferenceRequest = new SplitFileReferenceRequest(oldReference, newReferences);

    ArrayList<SplitFileReferenceRequest> successfulRequests = new ArrayList<>();
    successfulRequests.add(splitFileReferenceRequest);
    ArrayList<SplitFileReferenceRequest> failedRequests = new ArrayList<>();

    // Act and Assert
    assertSame(successfulRequests,
        (new SplitRequestsFailedException(successfulRequests, failedRequests, new Throwable()))
            .getSuccessfulRequests());
  }
}
