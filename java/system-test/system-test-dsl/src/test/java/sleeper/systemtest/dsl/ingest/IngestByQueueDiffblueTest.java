package sleeper.systemtest.dsl.ingest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.instance.InstanceProperty;
import sleeper.core.properties.testutils.DummyInstanceProperty;
import sleeper.systemtest.dsl.instance.SystemTestInstanceContext;

class IngestByQueueDiffblueTest {
  /**
   * Test {@link IngestByQueue#sendJobGetId(InstanceProperty, List)} with {@code queueUrlProperty}, {@code files}.
   * <ul>
   *   <li>Given {@code 42}.</li>
   *   <li>When {@link ArrayList#ArrayList()} add {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestByQueue#sendJobGetId(InstanceProperty, List)}
   */
  @Test
  @DisplayName("Test sendJobGetId(InstanceProperty, List) with 'queueUrlProperty', 'files'; given '42'; when ArrayList() add '42'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String IngestByQueue.sendJobGetId(InstanceProperty, List)"})
  void testSendJobGetIdWithQueueUrlPropertyFiles_given42_whenArrayListAdd42() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    SystemTestInstanceContext instance = mock(SystemTestInstanceContext.class);
    when(instance.getTableName()).thenReturn("Table Name");
    when(instance.getInstanceProperties()).thenReturn(instanceProperties);
    IngestByQueueDriver driver = mock(IngestByQueueDriver.class);
    when(driver.sendJobGetId(Mockito.<String>any(), Mockito.<String>any(), Mockito.<List<String>>any()))
        .thenReturn("42");
    IngestByQueue ingestByQueue = new IngestByQueue(instance, driver);
    DummyInstanceProperty queueUrlProperty = new DummyInstanceProperty("Property Name");

    ArrayList<String> files = new ArrayList<>();
    files.add("42");
    files.add("foo");

    // Act
    String actualSendJobGetIdResult = ingestByQueue.sendJobGetId(queueUrlProperty, files);

    // Assert
    verify(instanceProperties).get(isA(InstanceProperty.class));
    verify(driver).sendJobGetId(eq("Get"), eq("Table Name"), isA(List.class));
    verify(instance).getInstanceProperties();
    verify(instance).getTableName();
    assertEquals("42", actualSendJobGetIdResult);
  }

  /**
   * Test {@link IngestByQueue#sendJobGetId(InstanceProperty, List)} with {@code queueUrlProperty}, {@code files}.
   * <ul>
   *   <li>Given {@code foo}.</li>
   *   <li>When {@link ArrayList#ArrayList()} add {@code foo}.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestByQueue#sendJobGetId(InstanceProperty, List)}
   */
  @Test
  @DisplayName("Test sendJobGetId(InstanceProperty, List) with 'queueUrlProperty', 'files'; given 'foo'; when ArrayList() add 'foo'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String IngestByQueue.sendJobGetId(InstanceProperty, List)"})
  void testSendJobGetIdWithQueueUrlPropertyFiles_givenFoo_whenArrayListAddFoo() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    SystemTestInstanceContext instance = mock(SystemTestInstanceContext.class);
    when(instance.getTableName()).thenReturn("Table Name");
    when(instance.getInstanceProperties()).thenReturn(instanceProperties);
    IngestByQueueDriver driver = mock(IngestByQueueDriver.class);
    when(driver.sendJobGetId(Mockito.<String>any(), Mockito.<String>any(), Mockito.<List<String>>any()))
        .thenReturn("42");
    IngestByQueue ingestByQueue = new IngestByQueue(instance, driver);
    DummyInstanceProperty queueUrlProperty = new DummyInstanceProperty("Property Name");

    ArrayList<String> files = new ArrayList<>();
    files.add("foo");

    // Act
    String actualSendJobGetIdResult = ingestByQueue.sendJobGetId(queueUrlProperty, files);

    // Assert
    verify(instanceProperties).get(isA(InstanceProperty.class));
    verify(driver).sendJobGetId(eq("Get"), eq("Table Name"), isA(List.class));
    verify(instance).getInstanceProperties();
    verify(instance).getTableName();
    assertEquals("42", actualSendJobGetIdResult);
  }

  /**
   * Test {@link IngestByQueue#sendJobGetId(InstanceProperty, List)} with {@code queueUrlProperty}, {@code files}.
   * <ul>
   *   <li>Then return {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestByQueue#sendJobGetId(InstanceProperty, List)}
   */
  @Test
  @DisplayName("Test sendJobGetId(InstanceProperty, List) with 'queueUrlProperty', 'files'; then return '42'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String IngestByQueue.sendJobGetId(InstanceProperty, List)"})
  void testSendJobGetIdWithQueueUrlPropertyFiles_thenReturn42() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    SystemTestInstanceContext instance = mock(SystemTestInstanceContext.class);
    when(instance.getTableName()).thenReturn("Table Name");
    when(instance.getInstanceProperties()).thenReturn(instanceProperties);
    IngestByQueueDriver driver = mock(IngestByQueueDriver.class);
    when(driver.sendJobGetId(Mockito.<String>any(), Mockito.<String>any(), Mockito.<List<String>>any()))
        .thenReturn("42");
    IngestByQueue ingestByQueue = new IngestByQueue(instance, driver);
    DummyInstanceProperty queueUrlProperty = new DummyInstanceProperty("Property Name");

    // Act
    String actualSendJobGetIdResult = ingestByQueue.sendJobGetId(queueUrlProperty, new ArrayList<>());

    // Assert
    verify(instanceProperties).get(isA(InstanceProperty.class));
    verify(driver).sendJobGetId(eq("Get"), eq("Table Name"), isA(List.class));
    verify(instance).getInstanceProperties();
    verify(instance).getTableName();
    assertEquals("42", actualSendJobGetIdResult);
  }

  /**
   * Test {@link IngestByQueue#sendJobGetId(InstanceProperty, String, List)} with {@code queueUrlProperty}, {@code tableName}, {@code files}.
   * <ul>
   *   <li>Given {@code 42}.</li>
   *   <li>When {@link ArrayList#ArrayList()} add {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestByQueue#sendJobGetId(InstanceProperty, String, List)}
   */
  @Test
  @DisplayName("Test sendJobGetId(InstanceProperty, String, List) with 'queueUrlProperty', 'tableName', 'files'; given '42'; when ArrayList() add '42'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String IngestByQueue.sendJobGetId(InstanceProperty, String, List)"})
  void testSendJobGetIdWithQueueUrlPropertyTableNameFiles_given42_whenArrayListAdd42() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    SystemTestInstanceContext instance = mock(SystemTestInstanceContext.class);
    when(instance.getInstanceProperties()).thenReturn(instanceProperties);
    IngestByQueueDriver driver = mock(IngestByQueueDriver.class);
    when(driver.sendJobGetId(Mockito.<String>any(), Mockito.<String>any(), Mockito.<List<String>>any()))
        .thenReturn("42");
    IngestByQueue ingestByQueue = new IngestByQueue(instance, driver);
    DummyInstanceProperty queueUrlProperty = new DummyInstanceProperty("Property Name");

    ArrayList<String> files = new ArrayList<>();
    files.add("42");
    files.add("foo");

    // Act
    String actualSendJobGetIdResult = ingestByQueue.sendJobGetId(queueUrlProperty, "Table Name", files);

    // Assert
    verify(instanceProperties).get(isA(InstanceProperty.class));
    verify(driver).sendJobGetId(eq("Get"), eq("Table Name"), isA(List.class));
    verify(instance).getInstanceProperties();
    assertEquals("42", actualSendJobGetIdResult);
  }

  /**
   * Test {@link IngestByQueue#sendJobGetId(InstanceProperty, String, List)} with {@code queueUrlProperty}, {@code tableName}, {@code files}.
   * <ul>
   *   <li>Given {@code foo}.</li>
   *   <li>When {@link ArrayList#ArrayList()} add {@code foo}.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestByQueue#sendJobGetId(InstanceProperty, String, List)}
   */
  @Test
  @DisplayName("Test sendJobGetId(InstanceProperty, String, List) with 'queueUrlProperty', 'tableName', 'files'; given 'foo'; when ArrayList() add 'foo'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String IngestByQueue.sendJobGetId(InstanceProperty, String, List)"})
  void testSendJobGetIdWithQueueUrlPropertyTableNameFiles_givenFoo_whenArrayListAddFoo() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    SystemTestInstanceContext instance = mock(SystemTestInstanceContext.class);
    when(instance.getInstanceProperties()).thenReturn(instanceProperties);
    IngestByQueueDriver driver = mock(IngestByQueueDriver.class);
    when(driver.sendJobGetId(Mockito.<String>any(), Mockito.<String>any(), Mockito.<List<String>>any()))
        .thenReturn("42");
    IngestByQueue ingestByQueue = new IngestByQueue(instance, driver);
    DummyInstanceProperty queueUrlProperty = new DummyInstanceProperty("Property Name");

    ArrayList<String> files = new ArrayList<>();
    files.add("foo");

    // Act
    String actualSendJobGetIdResult = ingestByQueue.sendJobGetId(queueUrlProperty, "Table Name", files);

    // Assert
    verify(instanceProperties).get(isA(InstanceProperty.class));
    verify(driver).sendJobGetId(eq("Get"), eq("Table Name"), isA(List.class));
    verify(instance).getInstanceProperties();
    assertEquals("42", actualSendJobGetIdResult);
  }

  /**
   * Test {@link IngestByQueue#sendJobGetId(InstanceProperty, String, List)} with {@code queueUrlProperty}, {@code tableName}, {@code files}.
   * <ul>
   *   <li>Then return {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestByQueue#sendJobGetId(InstanceProperty, String, List)}
   */
  @Test
  @DisplayName("Test sendJobGetId(InstanceProperty, String, List) with 'queueUrlProperty', 'tableName', 'files'; then return '42'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String IngestByQueue.sendJobGetId(InstanceProperty, String, List)"})
  void testSendJobGetIdWithQueueUrlPropertyTableNameFiles_thenReturn42() {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    SystemTestInstanceContext instance = mock(SystemTestInstanceContext.class);
    when(instance.getInstanceProperties()).thenReturn(instanceProperties);
    IngestByQueueDriver driver = mock(IngestByQueueDriver.class);
    when(driver.sendJobGetId(Mockito.<String>any(), Mockito.<String>any(), Mockito.<List<String>>any()))
        .thenReturn("42");
    IngestByQueue ingestByQueue = new IngestByQueue(instance, driver);
    DummyInstanceProperty queueUrlProperty = new DummyInstanceProperty("Property Name");

    // Act
    String actualSendJobGetIdResult = ingestByQueue.sendJobGetId(queueUrlProperty, "Table Name", new ArrayList<>());

    // Assert
    verify(instanceProperties).get(isA(InstanceProperty.class));
    verify(driver).sendJobGetId(eq("Get"), eq("Table Name"), isA(List.class));
    verify(instance).getInstanceProperties();
    assertEquals("42", actualSendJobGetIdResult);
  }

  /**
   * Test {@link IngestByQueue#sendJobToAllTablesGetIds(InstanceProperty, List)}.
   * <ul>
   *   <li>Given {@code 42}.</li>
   *   <li>When {@link ArrayList#ArrayList()} add {@code 42}.</li>
   *   <li>Then return size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestByQueue#sendJobToAllTablesGetIds(InstanceProperty, List)}
   */
  @Test
  @DisplayName("Test sendJobToAllTablesGetIds(InstanceProperty, List); given '42'; when ArrayList() add '42'; then return size is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List IngestByQueue.sendJobToAllTablesGetIds(InstanceProperty, List)"})
  void testSendJobToAllTablesGetIds_given42_whenArrayListAdd42_thenReturnSizeIsOne() {
    // Arrange
    ArrayList<String> stringList = new ArrayList<>();
    stringList.add("foo");
    Stream<String> streamResult = stringList.stream();
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    SystemTestInstanceContext instance = mock(SystemTestInstanceContext.class);
    when(instance.getInstanceProperties()).thenReturn(instanceProperties);
    when(instance.streamDeployedTableNames()).thenReturn(streamResult);
    IngestByQueueDriver driver = mock(IngestByQueueDriver.class);
    when(driver.sendJobGetId(Mockito.<String>any(), Mockito.<String>any(), Mockito.<List<String>>any()))
        .thenReturn("42");
    IngestByQueue ingestByQueue = new IngestByQueue(instance, driver);
    DummyInstanceProperty queueUrlProperty = new DummyInstanceProperty("Property Name");

    ArrayList<String> files = new ArrayList<>();
    files.add("42");
    files.add("foo");

    // Act
    List<String> actualSendJobToAllTablesGetIdsResult = ingestByQueue.sendJobToAllTablesGetIds(queueUrlProperty, files);

    // Assert
    verify(instanceProperties).get(isA(InstanceProperty.class));
    verify(driver).sendJobGetId(eq("Get"), eq("foo"), isA(List.class));
    verify(instance).getInstanceProperties();
    verify(instance).streamDeployedTableNames();
    assertEquals(1, actualSendJobToAllTablesGetIdsResult.size());
    assertEquals("42", actualSendJobToAllTablesGetIdsResult.get(0));
  }

  /**
   * Test {@link IngestByQueue#sendJobToAllTablesGetIds(InstanceProperty, List)}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@code 42}.</li>
   *   <li>Then return size is two.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestByQueue#sendJobToAllTablesGetIds(InstanceProperty, List)}
   */
  @Test
  @DisplayName("Test sendJobToAllTablesGetIds(InstanceProperty, List); given ArrayList() add '42'; then return size is two")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List IngestByQueue.sendJobToAllTablesGetIds(InstanceProperty, List)"})
  void testSendJobToAllTablesGetIds_givenArrayListAdd42_thenReturnSizeIsTwo() {
    // Arrange
    ArrayList<String> stringList = new ArrayList<>();
    stringList.add("42");
    stringList.add("foo");
    Stream<String> streamResult = stringList.stream();
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    SystemTestInstanceContext instance = mock(SystemTestInstanceContext.class);
    when(instance.getInstanceProperties()).thenReturn(instanceProperties);
    when(instance.streamDeployedTableNames()).thenReturn(streamResult);
    IngestByQueueDriver driver = mock(IngestByQueueDriver.class);
    when(driver.sendJobGetId(Mockito.<String>any(), Mockito.<String>any(), Mockito.<List<String>>any()))
        .thenReturn("42");
    IngestByQueue ingestByQueue = new IngestByQueue(instance, driver);
    DummyInstanceProperty queueUrlProperty = new DummyInstanceProperty("Property Name");

    // Act
    List<String> actualSendJobToAllTablesGetIdsResult = ingestByQueue.sendJobToAllTablesGetIds(queueUrlProperty,
        new ArrayList<>());

    // Assert
    verify(instanceProperties, atLeast(1)).get(isA(InstanceProperty.class));
    verify(driver, atLeast(1)).sendJobGetId(eq("Get"), Mockito.<String>any(), isA(List.class));
    verify(instance, atLeast(1)).getInstanceProperties();
    verify(instance).streamDeployedTableNames();
    assertEquals(2, actualSendJobToAllTablesGetIdsResult.size());
    assertEquals("42", actualSendJobToAllTablesGetIdsResult.get(0));
    assertEquals("42", actualSendJobToAllTablesGetIdsResult.get(1));
  }

  /**
   * Test {@link IngestByQueue#sendJobToAllTablesGetIds(InstanceProperty, List)}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add empty string.</li>
   *   <li>Then return size is three.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestByQueue#sendJobToAllTablesGetIds(InstanceProperty, List)}
   */
  @Test
  @DisplayName("Test sendJobToAllTablesGetIds(InstanceProperty, List); given ArrayList() add empty string; then return size is three")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List IngestByQueue.sendJobToAllTablesGetIds(InstanceProperty, List)"})
  void testSendJobToAllTablesGetIds_givenArrayListAddEmptyString_thenReturnSizeIsThree() {
    // Arrange
    ArrayList<String> stringList = new ArrayList<>();
    stringList.add("");
    stringList.add("42");
    stringList.add("foo");
    Stream<String> streamResult = stringList.stream();
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    SystemTestInstanceContext instance = mock(SystemTestInstanceContext.class);
    when(instance.getInstanceProperties()).thenReturn(instanceProperties);
    when(instance.streamDeployedTableNames()).thenReturn(streamResult);
    IngestByQueueDriver driver = mock(IngestByQueueDriver.class);
    when(driver.sendJobGetId(Mockito.<String>any(), Mockito.<String>any(), Mockito.<List<String>>any()))
        .thenReturn("42");
    IngestByQueue ingestByQueue = new IngestByQueue(instance, driver);
    DummyInstanceProperty queueUrlProperty = new DummyInstanceProperty("Property Name");

    // Act
    List<String> actualSendJobToAllTablesGetIdsResult = ingestByQueue.sendJobToAllTablesGetIds(queueUrlProperty,
        new ArrayList<>());

    // Assert
    verify(instanceProperties, atLeast(1)).get(isA(InstanceProperty.class));
    verify(driver, atLeast(1)).sendJobGetId(eq("Get"), Mockito.<String>any(), isA(List.class));
    verify(instance, atLeast(1)).getInstanceProperties();
    verify(instance).streamDeployedTableNames();
    assertEquals(3, actualSendJobToAllTablesGetIdsResult.size());
    assertEquals("42", actualSendJobToAllTablesGetIdsResult.get(0));
    assertEquals("42", actualSendJobToAllTablesGetIdsResult.get(1));
    assertEquals("42", actualSendJobToAllTablesGetIdsResult.get(2));
  }

  /**
   * Test {@link IngestByQueue#sendJobToAllTablesGetIds(InstanceProperty, List)}.
   * <ul>
   *   <li>Given {@code foo}.</li>
   *   <li>When {@link ArrayList#ArrayList()} add {@code foo}.</li>
   *   <li>Then return size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestByQueue#sendJobToAllTablesGetIds(InstanceProperty, List)}
   */
  @Test
  @DisplayName("Test sendJobToAllTablesGetIds(InstanceProperty, List); given 'foo'; when ArrayList() add 'foo'; then return size is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List IngestByQueue.sendJobToAllTablesGetIds(InstanceProperty, List)"})
  void testSendJobToAllTablesGetIds_givenFoo_whenArrayListAddFoo_thenReturnSizeIsOne() {
    // Arrange
    ArrayList<String> stringList = new ArrayList<>();
    stringList.add("foo");
    Stream<String> streamResult = stringList.stream();
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    SystemTestInstanceContext instance = mock(SystemTestInstanceContext.class);
    when(instance.getInstanceProperties()).thenReturn(instanceProperties);
    when(instance.streamDeployedTableNames()).thenReturn(streamResult);
    IngestByQueueDriver driver = mock(IngestByQueueDriver.class);
    when(driver.sendJobGetId(Mockito.<String>any(), Mockito.<String>any(), Mockito.<List<String>>any()))
        .thenReturn("42");
    IngestByQueue ingestByQueue = new IngestByQueue(instance, driver);
    DummyInstanceProperty queueUrlProperty = new DummyInstanceProperty("Property Name");

    ArrayList<String> files = new ArrayList<>();
    files.add("foo");

    // Act
    List<String> actualSendJobToAllTablesGetIdsResult = ingestByQueue.sendJobToAllTablesGetIds(queueUrlProperty, files);

    // Assert
    verify(instanceProperties).get(isA(InstanceProperty.class));
    verify(driver).sendJobGetId(eq("Get"), eq("foo"), isA(List.class));
    verify(instance).getInstanceProperties();
    verify(instance).streamDeployedTableNames();
    assertEquals(1, actualSendJobToAllTablesGetIdsResult.size());
    assertEquals("42", actualSendJobToAllTablesGetIdsResult.get(0));
  }

  /**
   * Test {@link IngestByQueue#sendJobToAllTablesGetIds(InstanceProperty, List)}.
   * <ul>
   *   <li>Then return size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestByQueue#sendJobToAllTablesGetIds(InstanceProperty, List)}
   */
  @Test
  @DisplayName("Test sendJobToAllTablesGetIds(InstanceProperty, List); then return size is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List IngestByQueue.sendJobToAllTablesGetIds(InstanceProperty, List)"})
  void testSendJobToAllTablesGetIds_thenReturnSizeIsOne() {
    // Arrange
    ArrayList<String> stringList = new ArrayList<>();
    stringList.add("foo");
    Stream<String> streamResult = stringList.stream();
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    SystemTestInstanceContext instance = mock(SystemTestInstanceContext.class);
    when(instance.getInstanceProperties()).thenReturn(instanceProperties);
    when(instance.streamDeployedTableNames()).thenReturn(streamResult);
    IngestByQueueDriver driver = mock(IngestByQueueDriver.class);
    when(driver.sendJobGetId(Mockito.<String>any(), Mockito.<String>any(), Mockito.<List<String>>any()))
        .thenReturn("42");
    IngestByQueue ingestByQueue = new IngestByQueue(instance, driver);
    DummyInstanceProperty queueUrlProperty = new DummyInstanceProperty("Property Name");

    // Act
    List<String> actualSendJobToAllTablesGetIdsResult = ingestByQueue.sendJobToAllTablesGetIds(queueUrlProperty,
        new ArrayList<>());

    // Assert
    verify(instanceProperties).get(isA(InstanceProperty.class));
    verify(driver).sendJobGetId(eq("Get"), eq("foo"), isA(List.class));
    verify(instance).getInstanceProperties();
    verify(instance).streamDeployedTableNames();
    assertEquals(1, actualSendJobToAllTablesGetIdsResult.size());
    assertEquals("42", actualSendJobToAllTablesGetIdsResult.get(0));
  }

  /**
   * Test {@link IngestByQueue#sendJobToAllTablesGetIds(InstanceProperty, List)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestByQueue#sendJobToAllTablesGetIds(InstanceProperty, List)}
   */
  @Test
  @DisplayName("Test sendJobToAllTablesGetIds(InstanceProperty, List); when ArrayList(); then return Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List IngestByQueue.sendJobToAllTablesGetIds(InstanceProperty, List)"})
  void testSendJobToAllTablesGetIds_whenArrayList_thenReturnEmpty() {
    // Arrange
    SystemTestInstanceContext instance = mock(SystemTestInstanceContext.class);

    ArrayList<String> stringList = new ArrayList<>();
    Stream<String> streamResult = stringList.stream();
    when(instance.streamDeployedTableNames()).thenReturn(streamResult);
    IngestByQueue ingestByQueue = new IngestByQueue(instance, mock(IngestByQueueDriver.class));
    DummyInstanceProperty queueUrlProperty = new DummyInstanceProperty("Property Name");

    // Act
    List<String> actualSendJobToAllTablesGetIdsResult = ingestByQueue.sendJobToAllTablesGetIds(queueUrlProperty,
        new ArrayList<>());

    // Assert
    verify(instance).streamDeployedTableNames();
    assertTrue(actualSendJobToAllTablesGetIdsResult.isEmpty());
  }
}
