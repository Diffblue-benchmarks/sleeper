package sleeper.clients.deploy;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import sleeper.clients.deploy.StackDockerImage.Builder;
import sleeper.clients.deploy.UploadDockerImages.CopyFile;
import sleeper.clients.util.CommandPipeline;
import sleeper.clients.util.CommandPipelineRunner;
import sleeper.clients.util.EcrRepositoryCreator;
import sleeper.clients.util.EcrRepositoryCreator.Client;
import sleeper.clients.util.InMemoryEcrRepositories;
import sleeper.core.deploy.LambdaJar;

class UploadDockerImagesDiffblueTest {
  /**
   * Test {@link UploadDockerImages#upload(UploadDockerImagesRequest)} with {@code request}.
   * <ul>
   *   <li>Then calls {@link EcrRepositoryCreator.Client#versionExistsInRepository(String, String)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link UploadDockerImages#upload(UploadDockerImagesRequest)}
   */
  @Test
  @DisplayName("Test upload(UploadDockerImagesRequest) with 'request'; then calls versionExistsInRepository(String, String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void UploadDockerImages.upload(UploadDockerImagesRequest)"})
  void testUploadWithRequest_thenCallsVersionExistsInRepository() throws IOException, InterruptedException {
    try (MockedStatic<ProcessBuilder> mockProcessBuilder = mockStatic(ProcessBuilder.class)) {

      // Arrange
      ArrayList<Process> processList = new ArrayList<>();
      processList.add(mock(Process.class));
      processList.add(mock(Process.class));
      mockProcessBuilder.when(() -> ProcessBuilder.startPipeline(Mockito.<List<ProcessBuilder>>any()))
          .thenReturn(processList);
      Client ecrClient = mock(Client.class);
      when(ecrClient.versionExistsInRepository(Mockito.<String>any(), Mockito.<String>any())).thenReturn(true);
      UploadDockerImages buildResult = UploadDockerImages.builder()
          .baseDockerDirectory(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
          .copyFile(mock(CopyFile.class))
          .ecrClient(ecrClient)
          .jarsDirectory(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
          .build();

      ArrayList<StackDockerImage> images = new ArrayList<>();
      Builder imageNameResult = StackDockerImage.builder()
          .createEmrServerlessPolicy(true)
          .directoryName("/directory")
          .imageName("Image Name");
      StackDockerImage buildResult2 = imageNameResult.lambdaJar(LambdaJar.withFormatAndImage("Format", "Image Name"))
          .build();
      images.add(buildResult2);
      UploadDockerImagesRequest request = UploadDockerImagesRequest.builder()
          .account("3")
          .ecrPrefix("Ecr Prefix")
          .images(images)
          .region("us-east-2")
          .version("1.0.2")
          .build();

      // Act
      buildResult.upload(request);

      // Assert
      verify(ecrClient).versionExistsInRepository(eq("Ecr Prefix/Image Name"), eq("1.0.2"));
    }
  }

  /**
   * Test {@link UploadDockerImages#upload(UploadDockerImagesRequest)} with {@code request}.
   * <ul>
   *   <li>Then calls {@link EcrRepositoryCreator.Client#versionExistsInRepository(String, String)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link UploadDockerImages#upload(UploadDockerImagesRequest)}
   */
  @Test
  @DisplayName("Test upload(UploadDockerImagesRequest) with 'request'; then calls versionExistsInRepository(String, String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void UploadDockerImages.upload(UploadDockerImagesRequest)"})
  void testUploadWithRequest_thenCallsVersionExistsInRepository2() throws IOException, InterruptedException {
    try (MockedStatic<ProcessBuilder> mockProcessBuilder = mockStatic(ProcessBuilder.class)) {

      // Arrange
      ArrayList<Process> processList = new ArrayList<>();
      processList.add(mock(Process.class));
      processList.add(mock(Process.class));
      mockProcessBuilder.when(() -> ProcessBuilder.startPipeline(Mockito.<List<ProcessBuilder>>any()))
          .thenReturn(processList);
      Client ecrClient = mock(Client.class);
      when(ecrClient.versionExistsInRepository(Mockito.<String>any(), Mockito.<String>any())).thenReturn(true);
      UploadDockerImages buildResult = UploadDockerImages.builder()
          .baseDockerDirectory(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
          .copyFile(mock(CopyFile.class))
          .ecrClient(ecrClient)
          .jarsDirectory(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
          .build();

      ArrayList<StackDockerImage> images = new ArrayList<>();
      Builder imageNameResult = StackDockerImage.builder()
          .createEmrServerlessPolicy(true)
          .directoryName("/directory")
          .imageName("Image Name");
      StackDockerImage buildResult2 = imageNameResult.lambdaJar(LambdaJar.withFormatAndImage("Format", "Image Name"))
          .build();
      images.add(buildResult2);
      Builder imageNameResult2 = StackDockerImage.builder()
          .createEmrServerlessPolicy(true)
          .directoryName("/directory")
          .imageName("Image Name");
      StackDockerImage buildResult3 = imageNameResult2.lambdaJar(LambdaJar.withFormatAndImage("Format", "Image Name"))
          .build();
      images.add(buildResult3);
      UploadDockerImagesRequest request = UploadDockerImagesRequest.builder()
          .account("3")
          .ecrPrefix("Ecr Prefix")
          .images(images)
          .region("us-east-2")
          .version("1.0.2")
          .build();

      // Act
      buildResult.upload(request);

      // Assert
      verify(ecrClient, atLeast(1)).versionExistsInRepository(eq("Ecr Prefix/Image Name"), eq("1.0.2"));
    }
  }

  /**
   * Test {@link UploadDockerImages#upload(CommandPipelineRunner, UploadDockerImagesRequest)} with {@code runCommand}, {@code request}.
   * <p>
   * Method under test: {@link UploadDockerImages#upload(CommandPipelineRunner, UploadDockerImagesRequest)}
   */
  @Test
  @DisplayName("Test upload(CommandPipelineRunner, UploadDockerImagesRequest) with 'runCommand', 'request'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void UploadDockerImages.upload(CommandPipelineRunner, UploadDockerImagesRequest)"})
  void testUploadWithRunCommandRequest() throws IOException, InterruptedException {
    // Arrange
    CopyFile copyFile = mock(CopyFile.class);
    doNothing().when(copyFile).copyWrappingExceptions(Mockito.<Path>any(), Mockito.<Path>any());
    UploadDockerImages.Builder copyFileResult = UploadDockerImages.builder()
        .baseDockerDirectory(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .copyFile(copyFile);
    UploadDockerImages buildResult = copyFileResult.ecrClient(new InMemoryEcrRepositories())
        .jarsDirectory(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .build();
    CommandPipelineRunner runCommand = mock(CommandPipelineRunner.class);
    doNothing().when(runCommand).runOrThrow(isA(String[].class));
    doNothing().when(runCommand).runOrThrow(Mockito.<CommandPipeline>any());

    ArrayList<StackDockerImage> images = new ArrayList<>();
    Builder imageNameResult = StackDockerImage.builder()
        .createEmrServerlessPolicy(false)
        .directoryName("/directory")
        .imageName("Image Name");
    StackDockerImage buildResult2 = imageNameResult.lambdaJar(LambdaJar.withFormatAndImage("Format", "Image Name"))
        .build();
    images.add(buildResult2);
    UploadDockerImagesRequest request = UploadDockerImagesRequest.builder()
        .account("3")
        .ecrPrefix("Ecr Prefix")
        .images(images)
        .region("us-east-2")
        .version("1.0.2")
        .build();

    // Act
    buildResult.upload(runCommand, request);

    // Assert
    verify(copyFile).copyWrappingExceptions(isA(Path.class), isA(Path.class));
    verify(runCommand, atLeast(1)).runOrThrow(isA(String[].class));
    verify(runCommand).runOrThrow(isA(CommandPipeline.class));
  }

  /**
   * Test {@link UploadDockerImages#upload(CommandPipelineRunner, UploadDockerImagesRequest)} with {@code runCommand}, {@code request}.
   * <p>
   * Method under test: {@link UploadDockerImages#upload(CommandPipelineRunner, UploadDockerImagesRequest)}
   */
  @Test
  @DisplayName("Test upload(CommandPipelineRunner, UploadDockerImagesRequest) with 'runCommand', 'request'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void UploadDockerImages.upload(CommandPipelineRunner, UploadDockerImagesRequest)"})
  void testUploadWithRunCommandRequest2() throws IOException, InterruptedException {
    // Arrange
    UploadDockerImages.Builder copyFileResult = UploadDockerImages.builder()
        .baseDockerDirectory(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .copyFile(mock(CopyFile.class));
    UploadDockerImages buildResult = copyFileResult.ecrClient(new InMemoryEcrRepositories())
        .jarsDirectory(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .build();
    CommandPipelineRunner runCommand = mock(CommandPipelineRunner.class);
    doNothing().when(runCommand).runOrThrow(isA(String[].class));
    doNothing().when(runCommand).runOrThrow(Mockito.<CommandPipeline>any());

    ArrayList<StackDockerImage> images = new ArrayList<>();
    StackDockerImage buildResult2 = StackDockerImage.builder()
        .createEmrServerlessPolicy(true)
        .directoryName("/directory")
        .imageName("Image Name")
        .lambdaJar(null)
        .build();
    images.add(buildResult2);
    UploadDockerImagesRequest request = UploadDockerImagesRequest.builder()
        .account("3")
        .ecrPrefix("Ecr Prefix")
        .images(images)
        .region("us-east-2")
        .version("1.0.2")
        .build();

    // Act
    buildResult.upload(runCommand, request);

    // Assert
    verify(runCommand, atLeast(1)).runOrThrow(isA(String[].class));
    verify(runCommand).runOrThrow(isA(CommandPipeline.class));
  }

  /**
   * Test {@link UploadDockerImages#upload(CommandPipelineRunner, UploadDockerImagesRequest)} with {@code runCommand}, {@code request}.
   * <ul>
   *   <li>Then calls {@link CopyFile#copyWrappingExceptions(Path, Path)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link UploadDockerImages#upload(CommandPipelineRunner, UploadDockerImagesRequest)}
   */
  @Test
  @DisplayName("Test upload(CommandPipelineRunner, UploadDockerImagesRequest) with 'runCommand', 'request'; then calls copyWrappingExceptions(Path, Path)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void UploadDockerImages.upload(CommandPipelineRunner, UploadDockerImagesRequest)"})
  void testUploadWithRunCommandRequest_thenCallsCopyWrappingExceptions() throws IOException, InterruptedException {
    // Arrange
    CopyFile copyFile = mock(CopyFile.class);
    doNothing().when(copyFile).copyWrappingExceptions(Mockito.<Path>any(), Mockito.<Path>any());
    UploadDockerImages.Builder copyFileResult = UploadDockerImages.builder()
        .baseDockerDirectory(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .copyFile(copyFile);
    UploadDockerImages buildResult = copyFileResult.ecrClient(new InMemoryEcrRepositories())
        .jarsDirectory(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .build();
    CommandPipelineRunner runCommand = mock(CommandPipelineRunner.class);
    doNothing().when(runCommand).runOrThrow(isA(String[].class));
    doNothing().when(runCommand).runOrThrow(Mockito.<CommandPipeline>any());

    ArrayList<StackDockerImage> images = new ArrayList<>();
    Builder imageNameResult = StackDockerImage.builder()
        .createEmrServerlessPolicy(true)
        .directoryName("/directory")
        .imageName("Image Name");
    StackDockerImage buildResult2 = imageNameResult.lambdaJar(LambdaJar.withFormatAndImage("Format", "Image Name"))
        .build();
    images.add(buildResult2);
    UploadDockerImagesRequest request = UploadDockerImagesRequest.builder()
        .account("3")
        .ecrPrefix("Ecr Prefix")
        .images(images)
        .region("us-east-2")
        .version("1.0.2")
        .build();

    // Act
    buildResult.upload(runCommand, request);

    // Assert
    verify(copyFile).copyWrappingExceptions(isA(Path.class), isA(Path.class));
    verify(runCommand, atLeast(1)).runOrThrow(isA(String[].class));
    verify(runCommand).runOrThrow(isA(CommandPipeline.class));
  }

  /**
   * Test {@link UploadDockerImages#upload(CommandPipelineRunner, UploadDockerImagesRequest)} with {@code runCommand}, {@code request}.
   * <ul>
   *   <li>Then throw {@link InterruptedException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link UploadDockerImages#upload(CommandPipelineRunner, UploadDockerImagesRequest)}
   */
  @Test
  @DisplayName("Test upload(CommandPipelineRunner, UploadDockerImagesRequest) with 'runCommand', 'request'; then throw InterruptedException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void UploadDockerImages.upload(CommandPipelineRunner, UploadDockerImagesRequest)"})
  void testUploadWithRunCommandRequest_thenThrowInterruptedException() throws IOException, InterruptedException {
    // Arrange
    UploadDockerImages.Builder copyFileResult = UploadDockerImages.builder()
        .baseDockerDirectory(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .copyFile(mock(CopyFile.class));
    UploadDockerImages buildResult = copyFileResult.ecrClient(new InMemoryEcrRepositories())
        .jarsDirectory(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
        .build();
    CommandPipelineRunner runCommand = mock(CommandPipelineRunner.class);
    doThrow(new InterruptedException("Images expected: {}")).when(runCommand)
        .runOrThrow(Mockito.<CommandPipeline>any());

    ArrayList<StackDockerImage> images = new ArrayList<>();
    Builder imageNameResult = StackDockerImage.builder()
        .createEmrServerlessPolicy(true)
        .directoryName("/directory")
        .imageName("Image Name");
    StackDockerImage buildResult2 = imageNameResult.lambdaJar(LambdaJar.withFormatAndImage("Format", "Image Name"))
        .build();
    images.add(buildResult2);
    UploadDockerImagesRequest request = UploadDockerImagesRequest.builder()
        .account("3")
        .ecrPrefix("Ecr Prefix")
        .images(images)
        .region("us-east-2")
        .version("1.0.2")
        .build();

    // Act and Assert
    assertThrows(InterruptedException.class, () -> buildResult.upload(runCommand, request));
    verify(runCommand).runOrThrow(isA(CommandPipeline.class));
  }
}
