package sleeper.build.github.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.BigIntegerNode;
import com.fasterxml.jackson.databind.node.BooleanNode;
import com.fasterxml.jackson.databind.node.MissingNode;
import java.math.BigInteger;
import java.time.Instant;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class GitHubRateLimitsDiffblueTest {
  /**
   * Test {@link GitHubRateLimits#remainingLimit(JsonNode)}.
   * <ul>
   *   <li>Given {@link ArrayNode} {@link ArrayNode#get(String)} return False.</li>
   *   <li>Then return zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link GitHubRateLimits#remainingLimit(JsonNode)}
   */
  @Test
  @DisplayName("Test remainingLimit(JsonNode); given ArrayNode get(String) return False; then return zero")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"int GitHubRateLimits.remainingLimit(JsonNode)"})
  void testRemainingLimit_givenArrayNodeGetReturnFalse_thenReturnZero() {
    // Arrange
    ArrayNode arrayNode = mock(ArrayNode.class);
    when(arrayNode.get(Mockito.<String>any())).thenReturn(BooleanNode.getFalse());
    ArrayNode arrayNode2 = mock(ArrayNode.class);
    when(arrayNode2.get(Mockito.<String>any())).thenReturn(arrayNode);
    ArrayNode response = mock(ArrayNode.class);
    when(response.get(Mockito.<String>any())).thenReturn(arrayNode2);

    // Act
    int actualRemainingLimitResult = GitHubRateLimits.remainingLimit(response);

    // Assert
    verify(arrayNode2).get(eq("core"));
    verify(arrayNode).get(eq("remaining"));
    verify(response).get(eq("resources"));
    assertEquals(0, actualRemainingLimitResult);
  }

  /**
   * Test {@link GitHubRateLimits#remainingLimit(JsonNode)}.
   * <ul>
   *   <li>Given {@link ArrayNode} {@link ArrayNode#get(String)} return Instance.</li>
   *   <li>Then return zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link GitHubRateLimits#remainingLimit(JsonNode)}
   */
  @Test
  @DisplayName("Test remainingLimit(JsonNode); given ArrayNode get(String) return Instance; then return zero")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"int GitHubRateLimits.remainingLimit(JsonNode)"})
  void testRemainingLimit_givenArrayNodeGetReturnInstance_thenReturnZero() {
    // Arrange
    ArrayNode arrayNode = mock(ArrayNode.class);
    when(arrayNode.get(Mockito.<String>any())).thenReturn(MissingNode.getInstance());
    ArrayNode arrayNode2 = mock(ArrayNode.class);
    when(arrayNode2.get(Mockito.<String>any())).thenReturn(arrayNode);
    ArrayNode response = mock(ArrayNode.class);
    when(response.get(Mockito.<String>any())).thenReturn(arrayNode2);

    // Act
    int actualRemainingLimitResult = GitHubRateLimits.remainingLimit(response);

    // Assert
    verify(arrayNode2).get(eq("core"));
    verify(arrayNode).get(eq("remaining"));
    verify(response).get(eq("resources"));
    assertEquals(0, actualRemainingLimitResult);
  }

  /**
   * Test {@link GitHubRateLimits#remainingLimit(JsonNode)}.
   * <ul>
   *   <li>Then return one.</li>
   * </ul>
   * <p>
   * Method under test: {@link GitHubRateLimits#remainingLimit(JsonNode)}
   */
  @Test
  @DisplayName("Test remainingLimit(JsonNode); then return one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"int GitHubRateLimits.remainingLimit(JsonNode)"})
  void testRemainingLimit_thenReturnOne() {
    // Arrange
    ArrayNode arrayNode = mock(ArrayNode.class);
    when(arrayNode.get(Mockito.<String>any())).thenReturn(new BigIntegerNode(BigInteger.valueOf(1L)));
    ArrayNode arrayNode2 = mock(ArrayNode.class);
    when(arrayNode2.get(Mockito.<String>any())).thenReturn(arrayNode);
    ArrayNode response = mock(ArrayNode.class);
    when(response.get(Mockito.<String>any())).thenReturn(arrayNode2);

    // Act
    int actualRemainingLimitResult = GitHubRateLimits.remainingLimit(response);

    // Assert
    verify(arrayNode2).get(eq("core"));
    verify(arrayNode).get(eq("remaining"));
    verify(response).get(eq("resources"));
    assertEquals(1, actualRemainingLimitResult);
  }

  /**
   * Test {@link GitHubRateLimits#resetTime(JsonNode)}.
   * <ul>
   *   <li>Given {@link ArrayNode} {@link ArrayNode#get(String)} return False.</li>
   *   <li>Then return EpochSecond is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link GitHubRateLimits#resetTime(JsonNode)}
   */
  @Test
  @DisplayName("Test resetTime(JsonNode); given ArrayNode get(String) return False; then return EpochSecond is zero")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Instant GitHubRateLimits.resetTime(JsonNode)"})
  void testResetTime_givenArrayNodeGetReturnFalse_thenReturnEpochSecondIsZero() {
    // Arrange
    ArrayNode arrayNode = mock(ArrayNode.class);
    when(arrayNode.get(Mockito.<String>any())).thenReturn(BooleanNode.getFalse());
    ArrayNode arrayNode2 = mock(ArrayNode.class);
    when(arrayNode2.get(Mockito.<String>any())).thenReturn(arrayNode);
    ArrayNode response = mock(ArrayNode.class);
    when(response.get(Mockito.<String>any())).thenReturn(arrayNode2);

    // Act
    Instant actualResetTimeResult = GitHubRateLimits.resetTime(response);

    // Assert
    verify(arrayNode2).get(eq("core"));
    verify(arrayNode).get(eq("reset"));
    verify(response).get(eq("resources"));
    assertEquals(0, actualResetTimeResult.getNano());
    assertEquals(0L, actualResetTimeResult.getEpochSecond());
  }

  /**
   * Test {@link GitHubRateLimits#resetTime(JsonNode)}.
   * <ul>
   *   <li>Given {@link ArrayNode} {@link ArrayNode#get(String)} return Instance.</li>
   *   <li>Then return EpochSecond is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link GitHubRateLimits#resetTime(JsonNode)}
   */
  @Test
  @DisplayName("Test resetTime(JsonNode); given ArrayNode get(String) return Instance; then return EpochSecond is zero")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Instant GitHubRateLimits.resetTime(JsonNode)"})
  void testResetTime_givenArrayNodeGetReturnInstance_thenReturnEpochSecondIsZero() {
    // Arrange
    ArrayNode arrayNode = mock(ArrayNode.class);
    when(arrayNode.get(Mockito.<String>any())).thenReturn(MissingNode.getInstance());
    ArrayNode arrayNode2 = mock(ArrayNode.class);
    when(arrayNode2.get(Mockito.<String>any())).thenReturn(arrayNode);
    ArrayNode response = mock(ArrayNode.class);
    when(response.get(Mockito.<String>any())).thenReturn(arrayNode2);

    // Act
    Instant actualResetTimeResult = GitHubRateLimits.resetTime(response);

    // Assert
    verify(arrayNode2).get(eq("core"));
    verify(arrayNode).get(eq("reset"));
    verify(response).get(eq("resources"));
    assertEquals(0, actualResetTimeResult.getNano());
    assertEquals(0L, actualResetTimeResult.getEpochSecond());
  }

  /**
   * Test {@link GitHubRateLimits#resetTime(JsonNode)}.
   * <ul>
   *   <li>Then return EpochSecond is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link GitHubRateLimits#resetTime(JsonNode)}
   */
  @Test
  @DisplayName("Test resetTime(JsonNode); then return EpochSecond is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Instant GitHubRateLimits.resetTime(JsonNode)"})
  void testResetTime_thenReturnEpochSecondIsOne() {
    // Arrange
    ArrayNode arrayNode = mock(ArrayNode.class);
    when(arrayNode.get(Mockito.<String>any())).thenReturn(new BigIntegerNode(BigInteger.valueOf(1L)));
    ArrayNode arrayNode2 = mock(ArrayNode.class);
    when(arrayNode2.get(Mockito.<String>any())).thenReturn(arrayNode);
    ArrayNode response = mock(ArrayNode.class);
    when(response.get(Mockito.<String>any())).thenReturn(arrayNode2);

    // Act
    Instant actualResetTimeResult = GitHubRateLimits.resetTime(response);

    // Assert
    verify(arrayNode2).get(eq("core"));
    verify(arrayNode).get(eq("reset"));
    verify(response).get(eq("resources"));
    assertEquals(0, actualResetTimeResult.getNano());
    assertEquals(1L, actualResetTimeResult.getEpochSecond());
  }
}
