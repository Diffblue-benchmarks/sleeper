package sleeper.bulkimport.starter.executor;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.amazonaws.services.stepfunctions.AWSStepFunctions;
import com.amazonaws.services.stepfunctions.model.StartExecutionRequest;
import com.amazonaws.services.stepfunctions.model.StartExecutionResult;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import sleeper.bulkimport.core.job.BulkImportJob;
import sleeper.bulkimport.core.job.BulkImportJob.Builder;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.instance.InstanceProperty;

class StateMachinePlatformExecutorDiffblueTest {
  /**
   * Test {@link StateMachinePlatformExecutor#runJobOnPlatform(BulkImportArguments)}.
   * <p>
   * Method under test: {@link StateMachinePlatformExecutor#runJobOnPlatform(BulkImportArguments)}
   */
  @Test
  @DisplayName("Test runJobOnPlatform(BulkImportArguments)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void StateMachinePlatformExecutor.runJobOnPlatform(BulkImportArguments)"})
  void testRunJobOnPlatform() throws UnknownHostException {
    try (MockedStatic<InetAddress> mockInetAddress = mockStatic(InetAddress.class)) {

      // Arrange
      mockInetAddress.when(() -> InetAddress.getAllByName(Mockito.<String>any()))
          .thenReturn(new InetAddress[]{mock(InetAddress.class)});
      AWSStepFunctions stepFunctions = mock(AWSStepFunctions.class);
      when(stepFunctions.startExecution(Mockito.<StartExecutionRequest>any())).thenReturn(new StartExecutionResult());
      InstanceProperties instanceProperties = mock(InstanceProperties.class);
      when(instanceProperties.getBoolean(Mockito.<InstanceProperty>any())).thenReturn(true);
      when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
      StateMachinePlatformExecutor stateMachinePlatformExecutor = new StateMachinePlatformExecutor(stepFunctions,
          instanceProperties);
      InstanceProperties instanceProperties2 = mock(InstanceProperties.class);
      when(instanceProperties2.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
      BulkImportArguments.Builder builderResult = BulkImportArguments.builder();
      Builder classNameResult = BulkImportJob.builder().className("Class Name");
      Builder idResult = classNameResult.files(new ArrayList<>()).id("spark.driver.extraJavaOptions");
      BulkImportJob bulkImportJob = idResult.platformSpec(new HashMap<>())
          .sparkConf("Key", "42")
          .tableId("42")
          .tableName("Table Name")
          .build();
      BulkImportArguments arguments = builderResult.bulkImportJob(bulkImportJob)
          .instanceProperties(instanceProperties2)
          .jobRunId("42")
          .build();

      // Act
      stateMachinePlatformExecutor.runJobOnPlatform(arguments);

      // Assert
      verify(stepFunctions).startExecution(isA(StartExecutionRequest.class));
      verify(instanceProperties).getBoolean(isA(InstanceProperty.class));
      verify(instanceProperties2).get(isA(InstanceProperty.class));
      verify(instanceProperties, atLeast(1)).get(Mockito.<InstanceProperty>any());
    }
  }

  /**
   * Test {@link StateMachinePlatformExecutor#runJobOnPlatform(BulkImportArguments)}.
   * <p>
   * Method under test: {@link StateMachinePlatformExecutor#runJobOnPlatform(BulkImportArguments)}
   */
  @Test
  @DisplayName("Test runJobOnPlatform(BulkImportArguments)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void StateMachinePlatformExecutor.runJobOnPlatform(BulkImportArguments)"})
  void testRunJobOnPlatform2() throws UnknownHostException {
    try (MockedStatic<InetAddress> mockInetAddress = mockStatic(InetAddress.class)) {

      // Arrange
      mockInetAddress.when(() -> InetAddress.getAllByName(Mockito.<String>any()))
          .thenReturn(new InetAddress[]{mock(InetAddress.class)});
      AWSStepFunctions stepFunctions = mock(AWSStepFunctions.class);
      when(stepFunctions.startExecution(Mockito.<StartExecutionRequest>any())).thenReturn(new StartExecutionResult());
      InstanceProperties instanceProperties = mock(InstanceProperties.class);
      when(instanceProperties.getBoolean(Mockito.<InstanceProperty>any())).thenReturn(true);
      when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
      StateMachinePlatformExecutor stateMachinePlatformExecutor = new StateMachinePlatformExecutor(stepFunctions,
          instanceProperties);
      InstanceProperties instanceProperties2 = mock(InstanceProperties.class);
      when(instanceProperties2.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
      BulkImportArguments.Builder builderResult = BulkImportArguments.builder();
      Builder classNameResult = BulkImportJob.builder().className("Class Name");
      Builder idResult = classNameResult.files(new ArrayList<>()).id("42");
      BulkImportJob bulkImportJob = idResult.platformSpec(new HashMap<>())
          .sparkConf("", "42")
          .tableId("42")
          .tableName("Table Name")
          .build();
      BulkImportArguments arguments = builderResult.bulkImportJob(bulkImportJob)
          .instanceProperties(instanceProperties2)
          .jobRunId("42")
          .build();

      // Act
      stateMachinePlatformExecutor.runJobOnPlatform(arguments);

      // Assert
      verify(stepFunctions).startExecution(isA(StartExecutionRequest.class));
      verify(instanceProperties).getBoolean(isA(InstanceProperty.class));
      verify(instanceProperties2).get(isA(InstanceProperty.class));
      verify(instanceProperties, atLeast(1)).get(Mockito.<InstanceProperty>any());
    }
  }

  /**
   * Test {@link StateMachinePlatformExecutor#runJobOnPlatform(BulkImportArguments)}.
   * <ul>
   *   <li>Given {@link InstanceProperties} {@link InstanceProperties#get(InstanceProperty)} return empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateMachinePlatformExecutor#runJobOnPlatform(BulkImportArguments)}
   */
  @Test
  @DisplayName("Test runJobOnPlatform(BulkImportArguments); given InstanceProperties get(InstanceProperty) return empty string")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void StateMachinePlatformExecutor.runJobOnPlatform(BulkImportArguments)"})
  void testRunJobOnPlatform_givenInstancePropertiesGetReturnEmptyString() throws UnknownHostException {
    try (MockedStatic<InetAddress> mockInetAddress = mockStatic(InetAddress.class)) {

      // Arrange
      mockInetAddress.when(() -> InetAddress.getAllByName(Mockito.<String>any()))
          .thenReturn(new InetAddress[]{mock(InetAddress.class)});
      AWSStepFunctions stepFunctions = mock(AWSStepFunctions.class);
      when(stepFunctions.startExecution(Mockito.<StartExecutionRequest>any())).thenReturn(new StartExecutionResult());
      InstanceProperties instanceProperties = mock(InstanceProperties.class);
      when(instanceProperties.getBoolean(Mockito.<InstanceProperty>any())).thenReturn(true);
      when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("");
      StateMachinePlatformExecutor stateMachinePlatformExecutor = new StateMachinePlatformExecutor(stepFunctions,
          instanceProperties);
      InstanceProperties instanceProperties2 = mock(InstanceProperties.class);
      when(instanceProperties2.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
      BulkImportArguments.Builder builderResult = BulkImportArguments.builder();
      Builder classNameResult = BulkImportJob.builder().className("Class Name");
      Builder idResult = classNameResult.files(new ArrayList<>()).id("42");
      BulkImportJob bulkImportJob = idResult.platformSpec(new HashMap<>())
          .sparkConf("Key", "42")
          .tableId("42")
          .tableName("Table Name")
          .build();
      BulkImportArguments arguments = builderResult.bulkImportJob(bulkImportJob)
          .instanceProperties(instanceProperties2)
          .jobRunId("42")
          .build();

      // Act
      stateMachinePlatformExecutor.runJobOnPlatform(arguments);

      // Assert
      verify(stepFunctions).startExecution(isA(StartExecutionRequest.class));
      verify(instanceProperties).getBoolean(isA(InstanceProperty.class));
      verify(instanceProperties2).get(isA(InstanceProperty.class));
      verify(instanceProperties, atLeast(1)).get(Mockito.<InstanceProperty>any());
    }
  }

  /**
   * Test {@link StateMachinePlatformExecutor#runJobOnPlatform(BulkImportArguments)}.
   * <ul>
   *   <li>Then calls {@link AWSStepFunctions#startExecution(StartExecutionRequest)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StateMachinePlatformExecutor#runJobOnPlatform(BulkImportArguments)}
   */
  @Test
  @DisplayName("Test runJobOnPlatform(BulkImportArguments); then calls startExecution(StartExecutionRequest)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void StateMachinePlatformExecutor.runJobOnPlatform(BulkImportArguments)"})
  void testRunJobOnPlatform_thenCallsStartExecution() throws UnknownHostException {
    try (MockedStatic<InetAddress> mockInetAddress = mockStatic(InetAddress.class)) {

      // Arrange
      mockInetAddress.when(() -> InetAddress.getAllByName(Mockito.<String>any()))
          .thenReturn(new InetAddress[]{mock(InetAddress.class)});
      AWSStepFunctions stepFunctions = mock(AWSStepFunctions.class);
      when(stepFunctions.startExecution(Mockito.<StartExecutionRequest>any())).thenReturn(new StartExecutionResult());
      InstanceProperties instanceProperties = mock(InstanceProperties.class);
      when(instanceProperties.getBoolean(Mockito.<InstanceProperty>any())).thenReturn(true);
      when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
      StateMachinePlatformExecutor stateMachinePlatformExecutor = new StateMachinePlatformExecutor(stepFunctions,
          instanceProperties);
      InstanceProperties instanceProperties2 = mock(InstanceProperties.class);
      when(instanceProperties2.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
      BulkImportArguments.Builder builderResult = BulkImportArguments.builder();
      Builder classNameResult = BulkImportJob.builder().className("Class Name");
      Builder idResult = classNameResult.files(new ArrayList<>()).id("42");
      BulkImportJob bulkImportJob = idResult.platformSpec(new HashMap<>())
          .sparkConf("Key", "42")
          .tableId("42")
          .tableName("Table Name")
          .build();
      BulkImportArguments arguments = builderResult.bulkImportJob(bulkImportJob)
          .instanceProperties(instanceProperties2)
          .jobRunId("42")
          .build();

      // Act
      stateMachinePlatformExecutor.runJobOnPlatform(arguments);

      // Assert
      verify(stepFunctions).startExecution(isA(StartExecutionRequest.class));
      verify(instanceProperties).getBoolean(isA(InstanceProperty.class));
      verify(instanceProperties2).get(isA(InstanceProperty.class));
      verify(instanceProperties, atLeast(1)).get(Mockito.<InstanceProperty>any());
    }
  }
}
