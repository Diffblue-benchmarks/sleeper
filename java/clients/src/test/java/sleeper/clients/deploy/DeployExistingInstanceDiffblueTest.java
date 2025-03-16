package sleeper.clients.deploy;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.util.ArrayList;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import sleeper.clients.deploy.DeployExistingInstance.Builder;
import sleeper.clients.util.CommandPipelineRunner;
import sleeper.clients.util.cdk.CdkDeploy;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.table.TableProperties;
import software.amazon.awssdk.services.ecr.EcrClient;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.internal.crossregion.S3CrossRegionSyncClient;

class DeployExistingInstanceDiffblueTest {
  /**
   * Test Builder {@link Builder#clients(S3Client, EcrClient)}.
   * <p>
   * Method under test: {@link Builder#clients(S3Client, EcrClient)}
   */
  @Test
  @DisplayName("Test Builder clients(S3Client, EcrClient)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.clients(S3Client, EcrClient)"})
  void testBuilderClients() {
    // Arrange
    Builder builderResult = DeployExistingInstance.builder();

    // Act and Assert
    assertSame(builderResult,
        builderResult.clients(new S3CrossRegionSyncClient(mock(S3Client.class)), mock(EcrClient.class)));
  }

  /**
   * Test Builder {@link Builder#tableProperties(TableProperties[])}.
   * <ul>
   *   <li>Then return builder.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#tableProperties(TableProperties[])}
   */
  @Test
  @DisplayName("Test Builder tableProperties(TableProperties[]); then return builder")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.tableProperties(TableProperties[])"})
  void testBuilderTableProperties_thenReturnBuilder() {
    // Arrange
    Builder builderResult = DeployExistingInstance.builder();

    // Act and Assert
    assertSame(builderResult, builderResult.tableProperties(new TableProperties(new InstanceProperties())));
  }

  /**
   * Test {@link DeployExistingInstance#update()}.
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DeployExistingInstance#update()}
   */
  @Test
  @DisplayName("Test update(); then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DeployExistingInstance.update()"})
  void testUpdate_thenThrowIllegalArgumentException() throws IOException, InterruptedException {
    try (MockedStatic<Files> mockFiles = mockStatic(Files.class)) {

      // Arrange
      mockFiles.when(() -> Files.writeString(Mockito.<Path>any(), Mockito.<CharSequence>any(), isA(OpenOption[].class)))
          .thenThrow(new IllegalArgumentException(" "));
      mockFiles.when(() -> Files.newBufferedWriter(Mockito.<Path>any(), isA(OpenOption[].class)))
          .thenReturn(new BufferedWriter(new StringWriter(), 1));
      mockFiles.when(() -> Files.createDirectories(Mockito.<Path>any(), isA(FileAttribute[].class)))
          .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));

      ArrayList<Path> pathList = new ArrayList<>();
      Stream<Path> streamResult = pathList.stream();
      mockFiles.when(() -> Files.walk(Mockito.<Path>any(), isA(FileVisitOption[].class))).thenReturn(streamResult);
      Builder builderResult = DeployExistingInstance.builder();
      CdkDeploy deployCommand = CdkDeploy.builder()
          .deployPaused(true)
          .ensureNewInstance(true)
          .skipVersionCheck(true)
          .build();
      Builder instanceIdResult = builderResult.deployCommand(deployCommand).instanceId("42");
      Builder scriptsDirectoryResult = instanceIdResult.properties(new InstanceProperties())
          .runCommand(mock(CommandPipelineRunner.class))
          .scriptsDirectory(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));
      DeployExistingInstance buildResult = scriptsDirectoryResult.tablePropertiesList(new ArrayList<>()).build();

      // Act and Assert
      assertThrows(IllegalArgumentException.class, () -> buildResult.update());
      mockFiles.verify(() -> Files.createDirectories(Mockito.<Path>any(), isA(FileAttribute[].class)));
      mockFiles.verify(() -> Files.newBufferedWriter(Mockito.<Path>any(), isA(OpenOption[].class)));
      mockFiles.verify(() -> Files.walk(Mockito.<Path>any(), isA(FileVisitOption[].class)));
      mockFiles
          .verify(() -> Files.writeString(Mockito.<Path>any(), Mockito.<CharSequence>any(), isA(OpenOption[].class)));
    }
  }
}
