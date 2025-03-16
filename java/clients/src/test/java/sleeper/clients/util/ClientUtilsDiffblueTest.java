package sleeper.clients.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

class ClientUtilsDiffblueTest {
  /**
   * Test {@link ClientUtils#optionalArgument(String[], int)}.
   * <ul>
   *   <li>Then return {@link Optional#get()} is {@code ClientUtils}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClientUtils#optionalArgument(String[], int)}
   */
  @Test
  @DisplayName("Test optionalArgument(String[], int); then return get() is 'sleeper.clients.util.ClientUtils'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Optional ClientUtils.optionalArgument(String[], int)"})
  void testOptionalArgument_thenReturnGetIsSleeperClientsUtilClientUtils() {
    // Arrange and Act
    Optional<String> actualOptionalArgumentResult = ClientUtils
        .optionalArgument(new String[]{"Args", "sleeper.clients.util.ClientUtils"}, 1);

    // Assert
    assertEquals("sleeper.clients.util.ClientUtils", actualOptionalArgumentResult.get());
    assertTrue(actualOptionalArgumentResult.isPresent());
  }

  /**
   * Test {@link ClientUtils#optionalArgument(String[], int)}.
   * <ul>
   *   <li>When array of {@link String} with {@code Args}.</li>
   *   <li>Then return not Present.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClientUtils#optionalArgument(String[], int)}
   */
  @Test
  @DisplayName("Test optionalArgument(String[], int); when array of String with 'Args'; then return not Present")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Optional ClientUtils.optionalArgument(String[], int)"})
  void testOptionalArgument_whenArrayOfStringWithArgs_thenReturnNotPresent() {
    // Arrange and Act
    Optional<String> actualOptionalArgumentResult = ClientUtils.optionalArgument(new String[]{"Args"}, 1);

    // Assert
    assertFalse(actualOptionalArgumentResult.isPresent());
  }

  /**
   * Test {@link ClientUtils#abbreviatedRecordCount(long)}.
   * <ul>
   *   <li>When {@code 999999}.</li>
   *   <li>Then return {@code 1000K (999,999)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClientUtils#abbreviatedRecordCount(long)}
   */
  @Test
  @DisplayName("Test abbreviatedRecordCount(long); when '999999'; then return '1000K (999,999)'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String ClientUtils.abbreviatedRecordCount(long)"})
  void testAbbreviatedRecordCount_when999999_thenReturn1000k999999() {
    // Arrange, Act and Assert
    assertEquals("1000K (999,999)", ClientUtils.abbreviatedRecordCount(999999L));
  }

  /**
   * Test {@link ClientUtils#abbreviatedRecordCount(long)}.
   * <ul>
   *   <li>When {@code 1000000}.</li>
   *   <li>Then return {@code 1M (1,000,000)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClientUtils#abbreviatedRecordCount(long)}
   */
  @Test
  @DisplayName("Test abbreviatedRecordCount(long); when '1000000'; then return '1M (1,000,000)'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String ClientUtils.abbreviatedRecordCount(long)"})
  void testAbbreviatedRecordCount_when1000000_thenReturn1m1000000() {
    // Arrange, Act and Assert
    assertEquals("1M (1,000,000)", ClientUtils.abbreviatedRecordCount(1000000L));
  }

  /**
   * Test {@link ClientUtils#abbreviatedRecordCount(long)}.
   * <ul>
   *   <li>When {@code 1000000000}.</li>
   *   <li>Then return {@code 1G (1,000,000,000)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClientUtils#abbreviatedRecordCount(long)}
   */
  @Test
  @DisplayName("Test abbreviatedRecordCount(long); when '1000000000'; then return '1G (1,000,000,000)'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String ClientUtils.abbreviatedRecordCount(long)"})
  void testAbbreviatedRecordCount_when1000000000_thenReturn1g1000000000() {
    // Arrange, Act and Assert
    assertEquals("1G (1,000,000,000)", ClientUtils.abbreviatedRecordCount(1000000000L));
  }

  /**
   * Test {@link ClientUtils#abbreviatedRecordCount(long)}.
   * <ul>
   *   <li>When {@code 1000000000000}.</li>
   *   <li>Then return {@code 1T (1,000,000,000,000)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClientUtils#abbreviatedRecordCount(long)}
   */
  @Test
  @DisplayName("Test abbreviatedRecordCount(long); when '1000000000000'; then return '1T (1,000,000,000,000)'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String ClientUtils.abbreviatedRecordCount(long)"})
  void testAbbreviatedRecordCount_when1000000000000_thenReturn1t1000000000000() {
    // Arrange, Act and Assert
    assertEquals("1T (1,000,000,000,000)", ClientUtils.abbreviatedRecordCount(1000000000000L));
  }

  /**
   * Test {@link ClientUtils#abbreviatedRecordCount(long)}.
   * <ul>
   *   <li>When one thousand.</li>
   *   <li>Then return {@code 1K (1,000)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClientUtils#abbreviatedRecordCount(long)}
   */
  @Test
  @DisplayName("Test abbreviatedRecordCount(long); when one thousand; then return '1K (1,000)'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String ClientUtils.abbreviatedRecordCount(long)"})
  void testAbbreviatedRecordCount_whenOneThousand_thenReturn1k1000() {
    // Arrange, Act and Assert
    assertEquals("1K (1,000)", ClientUtils.abbreviatedRecordCount(1000L));
  }

  /**
   * Test {@link ClientUtils#abbreviatedRecordCount(long)}.
   * <ul>
   *   <li>When three.</li>
   *   <li>Then return {@code 3}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClientUtils#abbreviatedRecordCount(long)}
   */
  @Test
  @DisplayName("Test abbreviatedRecordCount(long); when three; then return '3'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String ClientUtils.abbreviatedRecordCount(long)"})
  void testAbbreviatedRecordCount_whenThree_thenReturn3() {
    // Arrange, Act and Assert
    assertEquals("3", ClientUtils.abbreviatedRecordCount(3L));
  }

  /**
   * Test {@link ClientUtils#clearDirectory(Path)}.
   * <ul>
   *   <li>Given {@link Files} {@link Files#delete(Path)} does nothing.</li>
   *   <li>Then calls {@link Files#delete(Path)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClientUtils#clearDirectory(Path)}
   */
  @Test
  @DisplayName("Test clearDirectory(Path); given Files delete(Path) does nothing; then calls delete(Path)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ClientUtils.clearDirectory(Path)"})
  void testClearDirectory_givenFilesDeleteDoesNothing_thenCallsDelete() throws IOException {
    try (MockedStatic<Files> mockFiles = mockStatic(Files.class)) {

      // Arrange
      ArrayList<Path> pathList = new ArrayList<>();
      pathList.add(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));
      pathList.add(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));
      Stream<Path> streamResult = pathList.stream();
      mockFiles.when(() -> Files.delete(Mockito.<Path>any())).thenAnswer(invocation -> null);
      mockFiles.when(() -> Files.walk(Mockito.<Path>any(), isA(FileVisitOption[].class))).thenReturn(streamResult);

      // Act
      ClientUtils.clearDirectory(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));

      // Assert
      mockFiles.verify(() -> Files.delete(Mockito.<Path>any()));
      mockFiles.verify(() -> Files.walk(Mockito.<Path>any(), isA(FileVisitOption[].class)));
    }
  }

  /**
   * Test {@link ClientUtils#clearDirectory(Path)}.
   * <ul>
   *   <li>Given {@link Files} {@link Files#delete(Path)} throw {@link IOException#IOException(String)} with {@code foo}.</li>
   *   <li>Then throw {@link IOException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClientUtils#clearDirectory(Path)}
   */
  @Test
  @DisplayName("Test clearDirectory(Path); given Files delete(Path) throw IOException(String) with 'foo'; then throw IOException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ClientUtils.clearDirectory(Path)"})
  void testClearDirectory_givenFilesDeleteThrowIOExceptionWithFoo_thenThrowIOException() throws IOException {
    try (MockedStatic<Files> mockFiles = mockStatic(Files.class)) {

      // Arrange
      ArrayList<Path> pathList = new ArrayList<>();
      pathList.add(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));
      pathList.add(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));
      Stream<Path> streamResult = pathList.stream();
      mockFiles.when(() -> Files.delete(Mockito.<Path>any())).thenThrow(new IOException("foo"));
      mockFiles.when(() -> Files.walk(Mockito.<Path>any(), isA(FileVisitOption[].class))).thenReturn(streamResult);

      // Act and Assert
      assertThrows(IOException.class,
          () -> ClientUtils.clearDirectory(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt")));
      mockFiles.verify(() -> Files.delete(Mockito.<Path>any()));
      mockFiles.verify(() -> Files.walk(Mockito.<Path>any(), isA(FileVisitOption[].class)));
    }
  }

  /**
   * Test {@link ClientUtils#clearDirectory(Path)}.
   * <ul>
   *   <li>Given {@link Files} {@link Files#walk(Path, FileVisitOption[])} return {@link ArrayList#ArrayList()} stream.</li>
   *   <li>Then calls {@link Files#walk(Path, FileVisitOption[])}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClientUtils#clearDirectory(Path)}
   */
  @Test
  @DisplayName("Test clearDirectory(Path); given Files walk(Path, FileVisitOption[]) return ArrayList() stream; then calls walk(Path, FileVisitOption[])")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ClientUtils.clearDirectory(Path)"})
  void testClearDirectory_givenFilesWalkReturnArrayListStream_thenCallsWalk() throws IOException {
    try (MockedStatic<Files> mockFiles = mockStatic(Files.class)) {

      // Arrange
      ArrayList<Path> pathList = new ArrayList<>();
      Stream<Path> streamResult = pathList.stream();
      mockFiles.when(() -> Files.walk(Mockito.<Path>any(), isA(FileVisitOption[].class))).thenReturn(streamResult);

      // Act
      ClientUtils.clearDirectory(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));

      // Assert
      mockFiles.verify(() -> Files.walk(Mockito.<Path>any(), isA(FileVisitOption[].class)));
    }
  }

  /**
   * Test {@link ClientUtils#clearDirectory(Path)}.
   * <ul>
   *   <li>Given {@link Files} {@link Files#walk(Path, FileVisitOption[])} throw {@link IOException#IOException(String)} with {@code foo}.</li>
   *   <li>Then throw {@link IOException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClientUtils#clearDirectory(Path)}
   */
  @Test
  @DisplayName("Test clearDirectory(Path); given Files walk(Path, FileVisitOption[]) throw IOException(String) with 'foo'; then throw IOException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ClientUtils.clearDirectory(Path)"})
  void testClearDirectory_givenFilesWalkThrowIOExceptionWithFoo_thenThrowIOException() throws IOException {
    try (MockedStatic<Files> mockFiles = mockStatic(Files.class)) {

      // Arrange
      mockFiles.when(() -> Files.walk(Mockito.<Path>any(), isA(FileVisitOption[].class)))
          .thenThrow(new IOException("foo"));

      // Act and Assert
      assertThrows(IOException.class,
          () -> ClientUtils.clearDirectory(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt")));
      mockFiles.verify(() -> Files.walk(Mockito.<Path>any(), isA(FileVisitOption[].class)));
    }
  }

  /**
   * Test {@link ClientUtils#runCommandLogOutput(CommandPipeline)} with {@code pipeline}.
   * <ul>
   *   <li>Then calls {@link ProcessBuilder#startPipeline(List)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClientUtils#runCommandLogOutput(CommandPipeline)}
   */
  @Test
  @DisplayName("Test runCommandLogOutput(CommandPipeline) with 'pipeline'; then calls startPipeline(List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"CommandPipelineResult ClientUtils.runCommandLogOutput(CommandPipeline)"})
  void testRunCommandLogOutputWithPipeline_thenCallsStartPipeline() throws IOException, InterruptedException {
    try (MockedStatic<ProcessBuilder> mockProcessBuilder = mockStatic(ProcessBuilder.class)) {

      // Arrange
      mockProcessBuilder.when(() -> ProcessBuilder.startPipeline(Mockito.<List<ProcessBuilder>>any()))
          .thenReturn(new ArrayList<>());
      Command commandResult = Command.command("Running command: {}");

      // Act
      ClientUtils.runCommandLogOutput(CommandPipeline.pipeline(commandResult, Command.command("Running command: {}")));

      // Assert
      mockProcessBuilder.verify(() -> ProcessBuilder.startPipeline(Mockito.<List<ProcessBuilder>>any()));
    }
  }

  /**
   * Test {@link ClientUtils#runCommandLogOutput(CommandPipeline)} with {@code pipeline}.
   * <ul>
   *   <li>Then return LastExitCode is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClientUtils#runCommandLogOutput(CommandPipeline)}
   */
  @Test
  @DisplayName("Test runCommandLogOutput(CommandPipeline) with 'pipeline'; then return LastExitCode is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"CommandPipelineResult ClientUtils.runCommandLogOutput(CommandPipeline)"})
  void testRunCommandLogOutputWithPipeline_thenReturnLastExitCodeIsOne() throws IOException, InterruptedException {
    try (MockedStatic<ProcessBuilder> mockProcessBuilder = mockStatic(ProcessBuilder.class)) {

      // Arrange
      Process process = mock(Process.class);
      when(process.waitFor()).thenReturn(1);
      when(process.getErrorStream()).thenReturn(new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")));
      when(process.getInputStream()).thenReturn(new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")));

      ArrayList<Process> processList = new ArrayList<>();
      processList.add(process);
      mockProcessBuilder.when(() -> ProcessBuilder.startPipeline(Mockito.<List<ProcessBuilder>>any()))
          .thenReturn(processList);
      Command commandResult = Command.command("Running command: {}");

      // Act
      CommandPipelineResult actualRunCommandLogOutputResult = ClientUtils
          .runCommandLogOutput(CommandPipeline.pipeline(commandResult, Command.command("Running command: {}")));

      // Assert
      verify(process).getErrorStream();
      verify(process).getInputStream();
      verify(process).waitFor();
      mockProcessBuilder.verify(() -> ProcessBuilder.startPipeline(Mockito.<List<ProcessBuilder>>any()));
      assertEquals(1, actualRunCommandLogOutputResult.getLastExitCode());
    }
  }

  /**
   * Test {@link ClientUtils#runCommandLogOutput(CommandPipeline)} with {@code pipeline}.
   * <ul>
   *   <li>Then return LastExitCode is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClientUtils#runCommandLogOutput(CommandPipeline)}
   */
  @Test
  @DisplayName("Test runCommandLogOutput(CommandPipeline) with 'pipeline'; then return LastExitCode is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"CommandPipelineResult ClientUtils.runCommandLogOutput(CommandPipeline)"})
  void testRunCommandLogOutputWithPipeline_thenReturnLastExitCodeIsOne2() throws IOException, InterruptedException {
    try (MockedStatic<ProcessBuilder> mockProcessBuilder = mockStatic(ProcessBuilder.class)) {

      // Arrange
      Process process = mock(Process.class);
      when(process.waitFor()).thenReturn(1);
      when(process.getErrorStream()).thenReturn(new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")));
      when(process.getInputStream()).thenReturn(new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")));
      Process process2 = mock(Process.class);
      when(process2.waitFor()).thenReturn(1);
      when(process2.getErrorStream()).thenReturn(new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")));
      when(process2.getInputStream()).thenReturn(new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")));
      Process process3 = mock(Process.class);
      when(process3.waitFor()).thenReturn(1);
      when(process3.getErrorStream()).thenReturn(new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")));
      when(process3.getInputStream()).thenReturn(new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")));

      ArrayList<Process> processList = new ArrayList<>();
      processList.add(process3);
      processList.add(process2);
      processList.add(process);
      mockProcessBuilder.when(() -> ProcessBuilder.startPipeline(Mockito.<List<ProcessBuilder>>any()))
          .thenReturn(processList);
      Command commandResult = Command.command("Running command: {}");

      // Act
      CommandPipelineResult actualRunCommandLogOutputResult = ClientUtils
          .runCommandLogOutput(CommandPipeline.pipeline(commandResult, Command.command("Running command: {}")));

      // Assert
      verify(process3).getErrorStream();
      verify(process2).getErrorStream();
      verify(process).getErrorStream();
      verify(process3).getInputStream();
      verify(process2).getInputStream();
      verify(process).getInputStream();
      verify(process3).waitFor();
      verify(process2).waitFor();
      verify(process).waitFor();
      mockProcessBuilder.verify(() -> ProcessBuilder.startPipeline(Mockito.<List<ProcessBuilder>>any()));
      assertEquals(1, actualRunCommandLogOutputResult.getLastExitCode());
    }
  }

  /**
   * Test {@link ClientUtils#runCommandLogOutput(CommandPipeline)} with {@code pipeline}.
   * <ul>
   *   <li>Then return LastExitCode is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClientUtils#runCommandLogOutput(CommandPipeline)}
   */
  @Test
  @DisplayName("Test runCommandLogOutput(CommandPipeline) with 'pipeline'; then return LastExitCode is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"CommandPipelineResult ClientUtils.runCommandLogOutput(CommandPipeline)"})
  void testRunCommandLogOutputWithPipeline_thenReturnLastExitCodeIsOne3() throws IOException, InterruptedException {
    try (MockedStatic<ProcessBuilder> mockProcessBuilder = mockStatic(ProcessBuilder.class)) {

      // Arrange
      Process process = mock(Process.class);
      when(process.waitFor()).thenReturn(1);
      when(process.getErrorStream()).thenReturn(new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")));
      when(process.getInputStream()).thenReturn(new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")));
      Process process2 = mock(Process.class);
      when(process2.waitFor()).thenReturn(1);
      when(process2.getErrorStream()).thenReturn(new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")));
      when(process2.getInputStream()).thenReturn(new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")));
      Process process3 = mock(Process.class);
      when(process3.waitFor()).thenReturn(1);
      when(process3.getErrorStream()).thenReturn(new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")));
      when(process3.getInputStream()).thenReturn(new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")));
      Process process4 = mock(Process.class);
      when(process4.waitFor()).thenReturn(1);
      when(process4.getErrorStream()).thenReturn(new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")));
      when(process4.getInputStream()).thenReturn(new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")));
      Process process5 = mock(Process.class);
      when(process5.waitFor()).thenReturn(1);
      when(process5.getErrorStream()).thenReturn(new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")));
      when(process5.getInputStream()).thenReturn(new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")));

      ArrayList<Process> processList = new ArrayList<>();
      processList.add(process5);
      processList.add(process4);
      processList.add(process3);
      processList.add(process2);
      processList.add(process);
      mockProcessBuilder.when(() -> ProcessBuilder.startPipeline(Mockito.<List<ProcessBuilder>>any()))
          .thenReturn(processList);
      Command commandResult = Command.command("Running command: {}");

      // Act
      CommandPipelineResult actualRunCommandLogOutputResult = ClientUtils
          .runCommandLogOutput(CommandPipeline.pipeline(commandResult, Command.command("Running command: {}")));

      // Assert
      verify(process5).getErrorStream();
      verify(process4).getErrorStream();
      verify(process3).getErrorStream();
      verify(process2).getErrorStream();
      verify(process).getErrorStream();
      verify(process5).getInputStream();
      verify(process4).getInputStream();
      verify(process3).getInputStream();
      verify(process2).getInputStream();
      verify(process).getInputStream();
      verify(process5).waitFor();
      verify(process4).waitFor();
      verify(process3).waitFor();
      verify(process2).waitFor();
      verify(process).waitFor();
      mockProcessBuilder.verify(() -> ProcessBuilder.startPipeline(Mockito.<List<ProcessBuilder>>any()));
      assertEquals(1, actualRunCommandLogOutputResult.getLastExitCode());
    }
  }

  /**
   * Test {@link ClientUtils#runCommandInheritIO(CommandPipeline)} with {@code pipeline}.
   * <ul>
   *   <li>Then return LastExitCode is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClientUtils#runCommandInheritIO(CommandPipeline)}
   */
  @Test
  @DisplayName("Test runCommandInheritIO(CommandPipeline) with 'pipeline'; then return LastExitCode is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"CommandPipelineResult ClientUtils.runCommandInheritIO(CommandPipeline)"})
  void testRunCommandInheritIOWithPipeline_thenReturnLastExitCodeIsOne() throws IOException, InterruptedException {
    try (MockedStatic<ProcessBuilder> mockProcessBuilder = mockStatic(ProcessBuilder.class)) {

      // Arrange
      Process process = mock(Process.class);
      when(process.waitFor()).thenReturn(1);

      ArrayList<Process> processList = new ArrayList<>();
      processList.add(process);
      mockProcessBuilder.when(() -> ProcessBuilder.startPipeline(Mockito.<List<ProcessBuilder>>any()))
          .thenReturn(processList);
      Command commandResult = Command.command("Running command: {}");

      // Act
      CommandPipelineResult actualRunCommandInheritIOResult = ClientUtils
          .runCommandInheritIO(CommandPipeline.pipeline(commandResult, Command.command("Running command: {}")));

      // Assert
      verify(process).waitFor();
      mockProcessBuilder.verify(() -> ProcessBuilder.startPipeline(Mockito.<List<ProcessBuilder>>any()));
      assertEquals(1, actualRunCommandInheritIOResult.getLastExitCode());
    }
  }

  /**
   * Test {@link ClientUtils#runCommandInheritIO(CommandPipeline)} with {@code pipeline}.
   * <ul>
   *   <li>Then throw {@link UncheckedIOException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClientUtils#runCommandInheritIO(CommandPipeline)}
   */
  @Test
  @DisplayName("Test runCommandInheritIO(CommandPipeline) with 'pipeline'; then throw UncheckedIOException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"CommandPipelineResult ClientUtils.runCommandInheritIO(CommandPipeline)"})
  void testRunCommandInheritIOWithPipeline_thenThrowUncheckedIOException() throws IOException, InterruptedException {
    try (MockedStatic<ProcessBuilder> mockProcessBuilder = mockStatic(ProcessBuilder.class)) {

      // Arrange
      Process process = mock(Process.class);
      when(process.waitFor()).thenReturn(1);
      Process process2 = mock(Process.class);
      when(process2.waitFor()).thenThrow(new UncheckedIOException(new IOException("Running command: {}")));

      ArrayList<Process> processList = new ArrayList<>();
      processList.add(process2);
      processList.add(process);
      mockProcessBuilder.when(() -> ProcessBuilder.startPipeline(Mockito.<List<ProcessBuilder>>any()))
          .thenReturn(processList);
      Command commandResult = Command.command("Running command: {}");

      // Act and Assert
      assertThrows(UncheckedIOException.class, () -> ClientUtils
          .runCommandInheritIO(CommandPipeline.pipeline(commandResult, Command.command("Running command: {}"))));
      verify(process2).waitFor();
      mockProcessBuilder.verify(() -> ProcessBuilder.startPipeline(Mockito.<List<ProcessBuilder>>any()));
    }
  }

  /**
   * Test {@link ClientUtils#runCommandInheritIO(CommandPipeline)} with {@code pipeline}.
   * <ul>
   *   <li>When {@code Running command: {}}.</li>
   *   <li>Then calls {@link ProcessBuilder#startPipeline(List)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClientUtils#runCommandInheritIO(CommandPipeline)}
   */
  @Test
  @DisplayName("Test runCommandInheritIO(CommandPipeline) with 'pipeline'; when 'Running command: {}'; then calls startPipeline(List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"CommandPipelineResult ClientUtils.runCommandInheritIO(CommandPipeline)"})
  void testRunCommandInheritIOWithPipeline_whenRunningCommand_thenCallsStartPipeline()
      throws IOException, InterruptedException {
    try (MockedStatic<ProcessBuilder> mockProcessBuilder = mockStatic(ProcessBuilder.class)) {

      // Arrange
      mockProcessBuilder.when(() -> ProcessBuilder.startPipeline(Mockito.<List<ProcessBuilder>>any()))
          .thenReturn(new ArrayList<>());
      Command commandResult = Command.command("Running command: {}");

      // Act
      ClientUtils.runCommandInheritIO(CommandPipeline.pipeline(commandResult, Command.command("Running command: {}")));

      // Assert
      mockProcessBuilder.verify(() -> ProcessBuilder.startPipeline(Mockito.<List<ProcessBuilder>>any()));
    }
  }
}
