package sleeper.cdk.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.fasterxml.jackson.databind.node.MissingNode;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.instance.InstanceProperty;
import sleeper.core.properties.table.TableProperties;
import software.amazon.awscdk.RemovalPolicy;
import software.amazon.awscdk.services.logs.ILogGroup;
import software.amazon.awscdk.services.logs.LogGroup;
import software.amazon.awscdk.services.logs.RetentionDays;
import software.constructs.Construct;
import software.constructs.Node;

class UtilsDiffblueTest {
  /**
   * Test {@link Utils#createDefaultEnvironment(InstanceProperties)}.
   * <ul>
   *   <li>Then return size is two.</li>
   * </ul>
   * <p>
   * Method under test: {@link Utils#createDefaultEnvironment(InstanceProperties)}
   */
  @Test
  @DisplayName("Test createDefaultEnvironment(InstanceProperties); then return size is two")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Map Utils.createDefaultEnvironment(InstanceProperties)"})
  void testCreateDefaultEnvironment_thenReturnSizeIsTwo() {
    // Arrange and Act
    Map<String, String> actualCreateDefaultEnvironmentResult = Utils
        .createDefaultEnvironment(UtilsTestHelper.createUserDefinedInstanceProperties());

    // Assert
    assertEquals(2, actualCreateDefaultEnvironmentResult.size());
    assertEquals(
        "--add-opens=java.base/java.nio=ALL-UNNAMED --add-opens=java.base/sun.nio.ch=ALL-UNNAMED --add-opens"
            + "=java.base/java.util=ALL-UNNAMED --add-opens=java.base/java.lang.invoke=ALL-UNNAMED ",
        actualCreateDefaultEnvironmentResult.get("JAVA_TOOL_OPTIONS"));
    assertNull(actualCreateDefaultEnvironmentResult.get("SLEEPER_CONFIG_BUCKET"));
  }

  /**
   * Test {@link Utils#createDefaultEnvironmentNoConfigBucket(InstanceProperties)}.
   * <ul>
   *   <li>Then return size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link Utils#createDefaultEnvironmentNoConfigBucket(InstanceProperties)}
   */
  @Test
  @DisplayName("Test createDefaultEnvironmentNoConfigBucket(InstanceProperties); then return size is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Map Utils.createDefaultEnvironmentNoConfigBucket(InstanceProperties)"})
  void testCreateDefaultEnvironmentNoConfigBucket_thenReturnSizeIsOne() {
    // Arrange and Act
    Map<String, String> actualCreateDefaultEnvironmentNoConfigBucketResult = Utils
        .createDefaultEnvironmentNoConfigBucket(UtilsTestHelper.createUserDefinedInstanceProperties());

    // Assert
    assertEquals(1, actualCreateDefaultEnvironmentNoConfigBucketResult.size());
    assertEquals(
        "--add-opens=java.base/java.nio=ALL-UNNAMED --add-opens=java.base/sun.nio.ch=ALL-UNNAMED --add-opens"
            + "=java.base/java.util=ALL-UNNAMED --add-opens=java.base/java.lang.invoke=ALL-UNNAMED ",
        actualCreateDefaultEnvironmentNoConfigBucketResult.get("JAVA_TOOL_OPTIONS"));
  }

  /**
   * Test {@link Utils#cleanInstanceId(String)} with {@code instanceId}.
   * <p>
   * Method under test: {@link Utils#cleanInstanceId(String)}
   */
  @Test
  @DisplayName("Test cleanInstanceId(String) with 'instanceId'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String Utils.cleanInstanceId(String)"})
  void testCleanInstanceIdWithInstanceId() {
    // Arrange, Act and Assert
    assertEquals("42", Utils.cleanInstanceId("42"));
  }

  /**
   * Test {@link Utils#cleanInstanceId(InstanceProperties)} with {@code properties}.
   * <ul>
   *   <li>Given {@code Get}.</li>
   *   <li>Then return {@code get}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Utils#cleanInstanceId(InstanceProperties)}
   */
  @Test
  @DisplayName("Test cleanInstanceId(InstanceProperties) with 'properties'; given 'Get'; then return 'get'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String Utils.cleanInstanceId(InstanceProperties)"})
  void testCleanInstanceIdWithProperties_givenGet_thenReturnGet() {
    // Arrange
    InstanceProperties properties = mock(InstanceProperties.class);
    when(properties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");

    // Act
    String actualCleanInstanceIdResult = Utils.cleanInstanceId(properties);

    // Assert
    verify(properties).get(isA(InstanceProperty.class));
    assertEquals("get", actualCleanInstanceIdResult);
  }

  /**
   * Test {@link Utils#createECSContainerLogDriver(ILogGroup)}.
   * <ul>
   *   <li>Given Instance.</li>
   *   <li>Then calls {@link LogGroup#getLogGroupName()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Utils#createECSContainerLogDriver(ILogGroup)}
   */
  @Test
  @DisplayName("Test createECSContainerLogDriver(ILogGroup); given Instance; then calls getLogGroupName()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"software.amazon.awscdk.services.ecs.LogDriver Utils.createECSContainerLogDriver(ILogGroup)"})
  void testCreateECSContainerLogDriver_givenInstance_thenCallsGetLogGroupName() {
    // Arrange
    LogGroup logGroup = mock(LogGroup.class);
    when(logGroup.$jsii$toJson()).thenReturn(MissingNode.getInstance());
    when(logGroup.getLogGroupName()).thenReturn("Log Group Name");

    // Act
    Utils.createECSContainerLogDriver(logGroup);

    // Assert
    verify(logGroup).getLogGroupName();
    verify(logGroup).$jsii$toJson();
  }

  /**
   * Test {@link Utils#getRetentionDays(int)}.
   * <ul>
   *   <li>When {@code 1827}.</li>
   *   <li>Then return {@code FIVE_YEARS}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Utils#getRetentionDays(int)}
   */
  @Test
  @DisplayName("Test getRetentionDays(int); when '1827'; then return 'FIVE_YEARS'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"RetentionDays Utils.getRetentionDays(int)"})
  void testGetRetentionDays_when1827_thenReturnFiveYears() {
    // Arrange, Act and Assert
    assertEquals(RetentionDays.FIVE_YEARS, Utils.getRetentionDays(1827));
  }

  /**
   * Test {@link Utils#getRetentionDays(int)}.
   * <ul>
   *   <li>When {@code 3653}.</li>
   *   <li>Then return {@code TEN_YEARS}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Utils#getRetentionDays(int)}
   */
  @Test
  @DisplayName("Test getRetentionDays(int); when '3653'; then return 'TEN_YEARS'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"RetentionDays Utils.getRetentionDays(int)"})
  void testGetRetentionDays_when3653_thenReturnTenYears() {
    // Arrange, Act and Assert
    assertEquals(RetentionDays.TEN_YEARS, Utils.getRetentionDays(3653));
  }

  /**
   * Test {@link Utils#getRetentionDays(int)}.
   * <ul>
   *   <li>When five hundred forty-five.</li>
   *   <li>Then return {@code EIGHTEEN_MONTHS}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Utils#getRetentionDays(int)}
   */
  @Test
  @DisplayName("Test getRetentionDays(int); when five hundred forty-five; then return 'EIGHTEEN_MONTHS'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"RetentionDays Utils.getRetentionDays(int)"})
  void testGetRetentionDays_whenFiveHundredFortyFive_thenReturnEighteenMonths() {
    // Arrange, Act and Assert
    assertEquals(RetentionDays.EIGHTEEN_MONTHS, Utils.getRetentionDays(545));
  }

  /**
   * Test {@link Utils#getRetentionDays(int)}.
   * <ul>
   *   <li>When five.</li>
   *   <li>Then return {@code FIVE_DAYS}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Utils#getRetentionDays(int)}
   */
  @Test
  @DisplayName("Test getRetentionDays(int); when five; then return 'FIVE_DAYS'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"RetentionDays Utils.getRetentionDays(int)"})
  void testGetRetentionDays_whenFive_thenReturnFiveDays() {
    // Arrange, Act and Assert
    assertEquals(RetentionDays.FIVE_DAYS, Utils.getRetentionDays(5));
  }

  /**
   * Test {@link Utils#getRetentionDays(int)}.
   * <ul>
   *   <li>When four hundred.</li>
   *   <li>Then return {@code THIRTEEN_MONTHS}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Utils#getRetentionDays(int)}
   */
  @Test
  @DisplayName("Test getRetentionDays(int); when four hundred; then return 'THIRTEEN_MONTHS'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"RetentionDays Utils.getRetentionDays(int)"})
  void testGetRetentionDays_whenFourHundred_thenReturnThirteenMonths() {
    // Arrange, Act and Assert
    assertEquals(RetentionDays.THIRTEEN_MONTHS, Utils.getRetentionDays(400));
  }

  /**
   * Test {@link Utils#getRetentionDays(int)}.
   * <ul>
   *   <li>When fourteen.</li>
   *   <li>Then return {@code TWO_WEEKS}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Utils#getRetentionDays(int)}
   */
  @Test
  @DisplayName("Test getRetentionDays(int); when fourteen; then return 'TWO_WEEKS'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"RetentionDays Utils.getRetentionDays(int)"})
  void testGetRetentionDays_whenFourteen_thenReturnTwoWeeks() {
    // Arrange, Act and Assert
    assertEquals(RetentionDays.TWO_WEEKS, Utils.getRetentionDays(14));
  }

  /**
   * Test {@link Utils#getRetentionDays(int)}.
   * <ul>
   *   <li>When minus one.</li>
   *   <li>Then return {@code INFINITE}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Utils#getRetentionDays(int)}
   */
  @Test
  @DisplayName("Test getRetentionDays(int); when minus one; then return 'INFINITE'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"RetentionDays Utils.getRetentionDays(int)"})
  void testGetRetentionDays_whenMinusOne_thenReturnInfinite() {
    // Arrange, Act and Assert
    assertEquals(RetentionDays.INFINITE, Utils.getRetentionDays(-1));
  }

  /**
   * Test {@link Utils#getRetentionDays(int)}.
   * <ul>
   *   <li>When ninety.</li>
   *   <li>Then return {@code THREE_MONTHS}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Utils#getRetentionDays(int)}
   */
  @Test
  @DisplayName("Test getRetentionDays(int); when ninety; then return 'THREE_MONTHS'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"RetentionDays Utils.getRetentionDays(int)"})
  void testGetRetentionDays_whenNinety_thenReturnThreeMonths() {
    // Arrange, Act and Assert
    assertEquals(RetentionDays.THREE_MONTHS, Utils.getRetentionDays(90));
  }

  /**
   * Test {@link Utils#getRetentionDays(int)}.
   * <ul>
   *   <li>When one hundred eighty.</li>
   *   <li>Then return {@code SIX_MONTHS}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Utils#getRetentionDays(int)}
   */
  @Test
  @DisplayName("Test getRetentionDays(int); when one hundred eighty; then return 'SIX_MONTHS'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"RetentionDays Utils.getRetentionDays(int)"})
  void testGetRetentionDays_whenOneHundredEighty_thenReturnSixMonths() {
    // Arrange, Act and Assert
    assertEquals(RetentionDays.SIX_MONTHS, Utils.getRetentionDays(180));
  }

  /**
   * Test {@link Utils#getRetentionDays(int)}.
   * <ul>
   *   <li>When one hundred fifty.</li>
   *   <li>Then return {@code FIVE_MONTHS}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Utils#getRetentionDays(int)}
   */
  @Test
  @DisplayName("Test getRetentionDays(int); when one hundred fifty; then return 'FIVE_MONTHS'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"RetentionDays Utils.getRetentionDays(int)"})
  void testGetRetentionDays_whenOneHundredFifty_thenReturnFiveMonths() {
    // Arrange, Act and Assert
    assertEquals(RetentionDays.FIVE_MONTHS, Utils.getRetentionDays(150));
  }

  /**
   * Test {@link Utils#getRetentionDays(int)}.
   * <ul>
   *   <li>When one hundred twenty.</li>
   *   <li>Then return {@code FOUR_MONTHS}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Utils#getRetentionDays(int)}
   */
  @Test
  @DisplayName("Test getRetentionDays(int); when one hundred twenty; then return 'FOUR_MONTHS'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"RetentionDays Utils.getRetentionDays(int)"})
  void testGetRetentionDays_whenOneHundredTwenty_thenReturnFourMonths() {
    // Arrange, Act and Assert
    assertEquals(RetentionDays.FOUR_MONTHS, Utils.getRetentionDays(120));
  }

  /**
   * Test {@link Utils#getRetentionDays(int)}.
   * <ul>
   *   <li>When one.</li>
   *   <li>Then return {@code ONE_DAY}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Utils#getRetentionDays(int)}
   */
  @Test
  @DisplayName("Test getRetentionDays(int); when one; then return 'ONE_DAY'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"RetentionDays Utils.getRetentionDays(int)"})
  void testGetRetentionDays_whenOne_thenReturnOneDay() {
    // Arrange, Act and Assert
    assertEquals(RetentionDays.ONE_DAY, Utils.getRetentionDays(1));
  }

  /**
   * Test {@link Utils#getRetentionDays(int)}.
   * <ul>
   *   <li>When seven hundred thirty-one.</li>
   *   <li>Then return {@code TWO_YEARS}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Utils#getRetentionDays(int)}
   */
  @Test
  @DisplayName("Test getRetentionDays(int); when seven hundred thirty-one; then return 'TWO_YEARS'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"RetentionDays Utils.getRetentionDays(int)"})
  void testGetRetentionDays_whenSevenHundredThirtyOne_thenReturnTwoYears() {
    // Arrange, Act and Assert
    assertEquals(RetentionDays.TWO_YEARS, Utils.getRetentionDays(731));
  }

  /**
   * Test {@link Utils#getRetentionDays(int)}.
   * <ul>
   *   <li>When seven.</li>
   *   <li>Then return {@code ONE_WEEK}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Utils#getRetentionDays(int)}
   */
  @Test
  @DisplayName("Test getRetentionDays(int); when seven; then return 'ONE_WEEK'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"RetentionDays Utils.getRetentionDays(int)"})
  void testGetRetentionDays_whenSeven_thenReturnOneWeek() {
    // Arrange, Act and Assert
    assertEquals(RetentionDays.ONE_WEEK, Utils.getRetentionDays(7));
  }

  /**
   * Test {@link Utils#getRetentionDays(int)}.
   * <ul>
   *   <li>When sixty.</li>
   *   <li>Then return {@code TWO_MONTHS}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Utils#getRetentionDays(int)}
   */
  @Test
  @DisplayName("Test getRetentionDays(int); when sixty; then return 'TWO_MONTHS'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"RetentionDays Utils.getRetentionDays(int)"})
  void testGetRetentionDays_whenSixty_thenReturnTwoMonths() {
    // Arrange, Act and Assert
    assertEquals(RetentionDays.TWO_MONTHS, Utils.getRetentionDays(60));
  }

  /**
   * Test {@link Utils#getRetentionDays(int)}.
   * <ul>
   *   <li>When ten.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Utils#getRetentionDays(int)}
   */
  @Test
  @DisplayName("Test getRetentionDays(int); when ten; then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"RetentionDays Utils.getRetentionDays(int)"})
  void testGetRetentionDays_whenTen_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> Utils.getRetentionDays(10));
  }

  /**
   * Test {@link Utils#getRetentionDays(int)}.
   * <ul>
   *   <li>When thirty.</li>
   *   <li>Then return {@code ONE_MONTH}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Utils#getRetentionDays(int)}
   */
  @Test
  @DisplayName("Test getRetentionDays(int); when thirty; then return 'ONE_MONTH'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"RetentionDays Utils.getRetentionDays(int)"})
  void testGetRetentionDays_whenThirty_thenReturnOneMonth() {
    // Arrange, Act and Assert
    assertEquals(RetentionDays.ONE_MONTH, Utils.getRetentionDays(30));
  }

  /**
   * Test {@link Utils#getRetentionDays(int)}.
   * <ul>
   *   <li>When three hundred sixty-five.</li>
   *   <li>Then return {@code ONE_YEAR}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Utils#getRetentionDays(int)}
   */
  @Test
  @DisplayName("Test getRetentionDays(int); when three hundred sixty-five; then return 'ONE_YEAR'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"RetentionDays Utils.getRetentionDays(int)"})
  void testGetRetentionDays_whenThreeHundredSixtyFive_thenReturnOneYear() {
    // Arrange, Act and Assert
    assertEquals(RetentionDays.ONE_YEAR, Utils.getRetentionDays(365));
  }

  /**
   * Test {@link Utils#getRetentionDays(int)}.
   * <ul>
   *   <li>When three.</li>
   *   <li>Then return {@code THREE_DAYS}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Utils#getRetentionDays(int)}
   */
  @Test
  @DisplayName("Test getRetentionDays(int); when three; then return 'THREE_DAYS'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"RetentionDays Utils.getRetentionDays(int)"})
  void testGetRetentionDays_whenThree_thenReturnThreeDays() {
    // Arrange, Act and Assert
    assertEquals(RetentionDays.THREE_DAYS, Utils.getRetentionDays(3));
  }

  /**
   * Test {@link Utils#getAllTableProperties(InstanceProperties, Construct)} with {@code instanceProperties}, {@code scope}.
   * <p>
   * Method under test: {@link Utils#getAllTableProperties(InstanceProperties, Construct)}
   */
  @Test
  @DisplayName("Test getAllTableProperties(InstanceProperties, Construct) with 'instanceProperties', 'scope'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream Utils.getAllTableProperties(InstanceProperties, Construct)"})
  void testGetAllTablePropertiesWithInstancePropertiesScope() {
    // Arrange
    InstanceProperties instanceProperties = UtilsTestHelper.createUserDefinedInstanceProperties();
    Node node = mock(Node.class);
    when(node.tryGetContext(Mockito.<String>any())).thenReturn("Try Get Context");
    Construct scope = mock(Construct.class);
    when(scope.getNode()).thenReturn(node);

    // Act
    Stream<TableProperties> actualAllTableProperties = Utils.getAllTableProperties(instanceProperties, scope);

    // Assert
    verify(scope).getNode();
    verify(node).tryGetContext(eq("propertiesfile"));
    assertTrue(actualAllTableProperties.limit(5).collect(Collectors.toList()).isEmpty());
  }

  /**
   * Test {@link Utils#getAllTableProperties(InstanceProperties, Function)} with {@code instanceProperties}, {@code tryGetContext}.
   * <p>
   * Method under test: {@link Utils#getAllTableProperties(InstanceProperties, Function)}
   */
  @Test
  @DisplayName("Test getAllTableProperties(InstanceProperties, Function) with 'instanceProperties', 'tryGetContext'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream Utils.getAllTableProperties(InstanceProperties, Function)"})
  void testGetAllTablePropertiesWithInstancePropertiesTryGetContext() {
    // Arrange
    InstanceProperties instanceProperties = UtilsTestHelper.createUserDefinedInstanceProperties();

    // Act
    Stream<TableProperties> actualAllTableProperties = Utils.getAllTableProperties(instanceProperties,
        Utils::cleanInstanceId);

    // Assert
    assertTrue(actualAllTableProperties.limit(5).collect(Collectors.toList()).isEmpty());
  }

  /**
   * Test {@link Utils#shouldDeployPaused(Construct)}.
   * <ul>
   *   <li>Given {@link Node} {@link Node#tryGetContext(String)} return {@link Boolean#TRUE} toString.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Utils#shouldDeployPaused(Construct)}
   */
  @Test
  @DisplayName("Test shouldDeployPaused(Construct); given Node tryGetContext(String) return TRUE toString; then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean Utils.shouldDeployPaused(Construct)"})
  void testShouldDeployPaused_givenNodeTryGetContextReturnTrueToString_thenReturnTrue() {
    // Arrange
    Node node = mock(Node.class);
    when(node.tryGetContext(Mockito.<String>any())).thenReturn(Boolean.TRUE.toString());
    Construct scope = mock(Construct.class);
    when(scope.getNode()).thenReturn(node);

    // Act
    boolean actualShouldDeployPausedResult = Utils.shouldDeployPaused(scope);

    // Assert
    verify(scope).getNode();
    verify(node).tryGetContext(eq("deployPaused"));
    assertTrue(actualShouldDeployPausedResult);
  }

  /**
   * Test {@link Utils#shouldDeployPaused(Construct)}.
   * <ul>
   *   <li>Given {@link Node} {@link Node#tryGetContext(String)} return {@code Try Get Context}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Utils#shouldDeployPaused(Construct)}
   */
  @Test
  @DisplayName("Test shouldDeployPaused(Construct); given Node tryGetContext(String) return 'Try Get Context'; then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean Utils.shouldDeployPaused(Construct)"})
  void testShouldDeployPaused_givenNodeTryGetContextReturnTryGetContext_thenReturnFalse() {
    // Arrange
    Node node = mock(Node.class);
    when(node.tryGetContext(Mockito.<String>any())).thenReturn("Try Get Context");
    Construct scope = mock(Construct.class);
    when(scope.getNode()).thenReturn(node);

    // Act
    boolean actualShouldDeployPausedResult = Utils.shouldDeployPaused(scope);

    // Assert
    verify(scope).getNode();
    verify(node).tryGetContext(eq("deployPaused"));
    assertFalse(actualShouldDeployPausedResult);
  }

  /**
   * Test {@link Utils#removalPolicy(InstanceProperties)}.
   * <ul>
   *   <li>When createUserDefinedInstanceProperties.</li>
   *   <li>Then return {@code RETAIN}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Utils#removalPolicy(InstanceProperties)}
   */
  @Test
  @DisplayName("Test removalPolicy(InstanceProperties); when createUserDefinedInstanceProperties; then return 'RETAIN'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"RemovalPolicy Utils.removalPolicy(InstanceProperties)"})
  void testRemovalPolicy_whenCreateUserDefinedInstanceProperties_thenReturnRetain() {
    // Arrange, Act and Assert
    assertEquals(RemovalPolicy.RETAIN, Utils.removalPolicy(UtilsTestHelper.createUserDefinedInstanceProperties()));
  }

  /**
   * Test {@link Utils#normaliseSize(String)}.
   * <ul>
   *   <li>When {@code 9U}.</li>
   *   <li>Then return {@code U9}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Utils#normaliseSize(String)}
   */
  @Test
  @DisplayName("Test normaliseSize(String); when '9U'; then return 'U9'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String Utils.normaliseSize(String)"})
  void testNormaliseSize_when9u_thenReturnU9() {
    // Arrange, Act and Assert
    assertEquals("U9", Utils.normaliseSize("9U"));
  }

  /**
   * Test {@link Utils#normaliseSize(String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Utils#normaliseSize(String)}
   */
  @Test
  @DisplayName("Test normaliseSize(String); when 'null'; then return 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String Utils.normaliseSize(String)"})
  void testNormaliseSize_whenNull_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(Utils.normaliseSize(null));
  }

  /**
   * Test {@link Utils#normaliseSize(String)}.
   * <ul>
   *   <li>When {@code Size}.</li>
   *   <li>Then return {@code Size}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Utils#normaliseSize(String)}
   */
  @Test
  @DisplayName("Test normaliseSize(String); when 'Size'; then return 'Size'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String Utils.normaliseSize(String)"})
  void testNormaliseSize_whenSize_thenReturnSize() {
    // Arrange, Act and Assert
    assertEquals("Size", Utils.normaliseSize("Size"));
  }
}
