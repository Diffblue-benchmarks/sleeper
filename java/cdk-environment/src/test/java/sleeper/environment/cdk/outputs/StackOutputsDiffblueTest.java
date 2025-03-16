package sleeper.environment.cdk.outputs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.core.exception.SdkServiceException;
import software.amazon.awssdk.services.cloudformation.CloudFormationClient;
import software.amazon.awssdk.services.cloudformation.model.CloudFormationException;
import software.amazon.awssdk.services.cloudformation.model.DescribeStacksRequest;
import software.amazon.awssdk.services.cloudformation.model.DescribeStacksRequest.Builder;

class StackOutputsDiffblueTest {
  /**
   * Test {@link StackOutputs#load(CloudFormationClient, List)}.
   * <ul>
   *   <li>Then calls {@link SdkServiceException#statusCode()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StackOutputs#load(CloudFormationClient, List)}
   */
  @Test
  @DisplayName("Test load(CloudFormationClient, List); then calls statusCode()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"StackOutputs StackOutputs.load(CloudFormationClient, List)"})
  void testLoad_thenCallsStatusCode() throws AwsServiceException, SdkClientException {
    // Arrange
    CloudFormationException cloudFormationException = mock(CloudFormationException.class);
    when(cloudFormationException.statusCode()).thenReturn(400);
    CloudFormationClient cloudFormation = mock(CloudFormationClient.class);
    when(cloudFormation.describeStacks(Mockito.<Consumer<Builder>>any())).thenThrow(cloudFormationException);

    ArrayList<String> stackNames = new ArrayList<>();
    stackNames.add("foo");

    // Act
    StackOutputs actualLoadResult = StackOutputs.load(cloudFormation, stackNames);

    // Assert
    verify(cloudFormationException).statusCode();
    verify(cloudFormation).describeStacks(isA(Consumer.class));
    assertTrue(actualLoadResult.toMap().isEmpty());
  }

  /**
   * Test {@link StackOutputs#load(CloudFormationClient, List)}.
   * <ul>
   *   <li>Then calls {@link SdkServiceException#statusCode()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StackOutputs#load(CloudFormationClient, List)}
   */
  @Test
  @DisplayName("Test load(CloudFormationClient, List); then calls statusCode()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"StackOutputs StackOutputs.load(CloudFormationClient, List)"})
  void testLoad_thenCallsStatusCode2() throws AwsServiceException, SdkClientException {
    // Arrange
    CloudFormationException cloudFormationException = mock(CloudFormationException.class);
    when(cloudFormationException.statusCode()).thenReturn(400);
    CloudFormationClient cloudFormation = mock(CloudFormationClient.class);
    when(cloudFormation.describeStacks(Mockito.<Consumer<Builder>>any())).thenThrow(cloudFormationException);

    ArrayList<String> stackNames = new ArrayList<>();
    stackNames.add("42");
    stackNames.add("foo");

    // Act
    StackOutputs actualLoadResult = StackOutputs.load(cloudFormation, stackNames);

    // Assert
    verify(cloudFormationException, atLeast(1)).statusCode();
    verify(cloudFormation, atLeast(1)).describeStacks(Mockito.<Consumer<Builder>>any());
    assertTrue(actualLoadResult.toMap().isEmpty());
  }

  /**
   * Test {@link StackOutputs#load(CloudFormationClient, List)}.
   * <ul>
   *   <li>When {@link CloudFormationClient}.</li>
   *   <li>Then return toMap Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link StackOutputs#load(CloudFormationClient, List)}
   */
  @Test
  @DisplayName("Test load(CloudFormationClient, List); when CloudFormationClient; then return toMap Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"StackOutputs StackOutputs.load(CloudFormationClient, List)"})
  void testLoad_whenCloudFormationClient_thenReturnToMapEmpty() {
    // Arrange
    CloudFormationClient cloudFormation = mock(CloudFormationClient.class);

    // Act and Assert
    assertTrue(StackOutputs.load(cloudFormation, new ArrayList<>()).toMap().isEmpty());
  }

  /**
   * Test {@link StackOutputs#fromMap(Map)}.
   * <ul>
   *   <li>Given {@code 42}.</li>
   *   <li>When {@link HashMap#HashMap()} {@code 42} is {@link HashMap#HashMap()}.</li>
   *   <li>Then return toMap is {@link HashMap#HashMap()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StackOutputs#fromMap(Map)}
   */
  @Test
  @DisplayName("Test fromMap(Map); given '42'; when HashMap() '42' is HashMap(); then return toMap is HashMap()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"StackOutputs StackOutputs.fromMap(Map)"})
  void testFromMap_given42_whenHashMap42IsHashMap_thenReturnToMapIsHashMap() {
    // Arrange
    HashMap<String, Map<String, String>> outputsByStackName = new HashMap<>();
    outputsByStackName.put("42", new HashMap<>());
    outputsByStackName.put("foo", new HashMap<>());

    // Act and Assert
    assertEquals(outputsByStackName, StackOutputs.fromMap(outputsByStackName).toMap());
  }

  /**
   * Test {@link StackOutputs#fromMap(Map)}.
   * <ul>
   *   <li>Given {@code foo}.</li>
   *   <li>When {@link HashMap#HashMap()} {@code foo} is {@link HashMap#HashMap()}.</li>
   *   <li>Then return toMap size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link StackOutputs#fromMap(Map)}
   */
  @Test
  @DisplayName("Test fromMap(Map); given 'foo'; when HashMap() 'foo' is HashMap(); then return toMap size is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"StackOutputs StackOutputs.fromMap(Map)"})
  void testFromMap_givenFoo_whenHashMapFooIsHashMap_thenReturnToMapSizeIsOne() {
    // Arrange
    HashMap<String, Map<String, String>> outputsByStackName = new HashMap<>();
    outputsByStackName.put("foo", new HashMap<>());

    // Act and Assert
    Map<String, Map<String, String>> toMapResult = StackOutputs.fromMap(outputsByStackName).toMap();
    assertEquals(1, toMapResult.size());
    assertTrue(toMapResult.get("foo").isEmpty());
  }

  /**
   * Test {@link StackOutputs#fromMap(Map)}.
   * <ul>
   *   <li>When {@link HashMap#HashMap()}.</li>
   *   <li>Then return toMap Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link StackOutputs#fromMap(Map)}
   */
  @Test
  @DisplayName("Test fromMap(Map); when HashMap(); then return toMap Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"StackOutputs StackOutputs.fromMap(Map)"})
  void testFromMap_whenHashMap_thenReturnToMapEmpty() {
    // Arrange, Act and Assert
    assertTrue(StackOutputs.fromMap(new HashMap<>()).toMap().isEmpty());
  }

  /**
   * Test {@link StackOutputs#toMap()}.
   * <p>
   * Method under test: {@link StackOutputs#toMap()}
   */
  @Test
  @DisplayName("Test toMap()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Map StackOutputs.toMap()"})
  void testToMap() {
    // Arrange, Act and Assert
    assertTrue(StackOutputs.fromMap(new HashMap<>()).toMap().isEmpty());
  }

  /**
   * Test {@link StackOutputs#equals(Object)}, and {@link StackOutputs#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link StackOutputs#equals(Object)}
   *   <li>{@link StackOutputs#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean StackOutputs.equals(Object)", "int StackOutputs.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    StackOutputs fromMapResult = StackOutputs.fromMap(new HashMap<>());
    StackOutputs fromMapResult2 = StackOutputs.fromMap(new HashMap<>());

    // Act and Assert
    assertEquals(fromMapResult, fromMapResult2);
    int expectedHashCodeResult = fromMapResult.hashCode();
    assertEquals(expectedHashCodeResult, fromMapResult2.hashCode());
  }

  /**
   * Test {@link StackOutputs#equals(Object)}, and {@link StackOutputs#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link StackOutputs#equals(Object)}
   *   <li>{@link StackOutputs#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean StackOutputs.equals(Object)", "int StackOutputs.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    StackOutputs fromMapResult = StackOutputs.fromMap(new HashMap<>());

    // Act and Assert
    assertEquals(fromMapResult, fromMapResult);
    int expectedHashCodeResult = fromMapResult.hashCode();
    assertEquals(expectedHashCodeResult, fromMapResult.hashCode());
  }

  /**
   * Test {@link StackOutputs#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link StackOutputs#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean StackOutputs.equals(Object)", "int StackOutputs.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    HashMap<String, Map<String, String>> outputsByStackName = new HashMap<>();
    outputsByStackName.put("foo", new HashMap<>());
    StackOutputs fromMapResult = StackOutputs.fromMap(outputsByStackName);

    // Act and Assert
    assertNotEquals(fromMapResult, StackOutputs.fromMap(new HashMap<>()));
  }

  /**
   * Test {@link StackOutputs#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link StackOutputs#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean StackOutputs.equals(Object)", "int StackOutputs.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(StackOutputs.fromMap(new HashMap<>()), null);
  }

  /**
   * Test {@link StackOutputs#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link StackOutputs#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean StackOutputs.equals(Object)", "int StackOutputs.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(StackOutputs.fromMap(new HashMap<>()), "Different type to StackOutputs");
  }

  /**
   * Test {@link StackOutputs#toString()}.
   * <p>
   * Method under test: {@link StackOutputs#toString()}
   */
  @Test
  @DisplayName("Test toString()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String StackOutputs.toString()"})
  void testToString() {
    // Arrange, Act and Assert
    assertEquals("StackOutputs{stacks=[]}", StackOutputs.fromMap(new HashMap<>()).toString());
  }
}
