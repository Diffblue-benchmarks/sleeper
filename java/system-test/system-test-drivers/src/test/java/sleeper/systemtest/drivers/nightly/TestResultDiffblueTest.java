package sleeper.systemtest.drivers.nightly;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.systemtest.drivers.nightly.TestResult.Builder;

class TestResultDiffblueTest {
  /**
   * Test Builder {@link Builder#build()}.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Builder#build()}
   *   <li>{@link Builder#exitCode(int)}
   *   <li>{@link Builder#instanceId(String)}
   *   <li>{@link Builder#testName(String)}
   * </ul>
   */
  @Test
  @DisplayName("Test Builder build()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"TestResult Builder.build()", "Builder Builder.exitCode(int)",
      "Builder Builder.instanceId(String)", "Builder Builder.testName(String)"})
  void testBuilderBuild() {
    // Arrange and Act
    TestResult actualBuildResult = TestResult.builder().exitCode(1).instanceId("42").testName("Test Name").build();

    // Assert
    assertEquals("42", actualBuildResult.getInstanceId());
    assertEquals("Test Name", actualBuildResult.getTestName());
    assertEquals(1, actualBuildResult.getExitCode());
  }

  /**
   * Test Builder {@link Builder#logFile(Path)}.
   * <p>
   * Method under test: {@link Builder#logFile(Path)}
   */
  @Test
  @DisplayName("Test Builder logFile(Path)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.logFile(Path)"})
  void testBuilderLogFile() {
    // Arrange
    Builder builderResult = TestResult.builder();

    // Act and Assert
    assertSame(builderResult, builderResult.logFile(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt")));
  }

  /**
   * Test Builder {@link Builder#outputFiles(List)}.
   * <ul>
   *   <li>Given Property is {@code java.io.tmpdir} is array of {@link String} with {@code test.txt}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#outputFiles(List)}
   */
  @Test
  @DisplayName("Test Builder outputFiles(List); given Property is 'java.io.tmpdir' is array of String with 'test.txt'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.outputFiles(List)"})
  void testBuilderOutputFiles_givenPropertyIsJavaIoTmpdirIsArrayOfStringWithTestTxt() {
    // Arrange
    Builder builderResult = TestResult.builder();

    ArrayList<Path> outputFiles = new ArrayList<>();
    outputFiles.add(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));

    // Act and Assert
    assertSame(builderResult, builderResult.outputFiles(outputFiles));
  }

  /**
   * Test Builder {@link Builder#outputFiles(List)}.
   * <ul>
   *   <li>Given Property is {@code java.io.tmpdir} is array of {@link String} with {@code test.txt}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#outputFiles(List)}
   */
  @Test
  @DisplayName("Test Builder outputFiles(List); given Property is 'java.io.tmpdir' is array of String with 'test.txt'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.outputFiles(List)"})
  void testBuilderOutputFiles_givenPropertyIsJavaIoTmpdirIsArrayOfStringWithTestTxt2() {
    // Arrange
    Builder builderResult = TestResult.builder();

    ArrayList<Path> outputFiles = new ArrayList<>();
    outputFiles.add(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));
    outputFiles.add(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));

    // Act and Assert
    assertSame(builderResult, builderResult.outputFiles(outputFiles));
  }

  /**
   * Test Builder {@link Builder#outputFiles(List)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#outputFiles(List)}
   */
  @Test
  @DisplayName("Test Builder outputFiles(List); when ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.outputFiles(List)"})
  void testBuilderOutputFiles_whenArrayList() {
    // Arrange
    Builder builderResult = TestResult.builder();

    // Act and Assert
    assertSame(builderResult, builderResult.outputFiles(new ArrayList<>()));
  }

  /**
   * Test Builder {@link Builder#siteFile(Path)}.
   * <p>
   * Method under test: {@link Builder#siteFile(Path)}
   */
  @Test
  @DisplayName("Test Builder siteFile(Path)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.siteFile(Path)"})
  void testBuilderSiteFile() {
    // Arrange
    Builder builderResult = TestResult.builder();

    // Act and Assert
    assertSame(builderResult, builderResult.siteFile(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt")));
  }

  /**
   * Test {@link TestResult#uploads()}.
   * <p>
   * Method under test: {@link TestResult#uploads()}
   */
  @Test
  @DisplayName("Test uploads()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream TestResult.uploads()"})
  void testUploads() {
    // Arrange
    TestResult buildResult = TestResult.builder().exitCode(1).instanceId("42").testName("Test Name").build();

    // Act
    Stream<NightlyTestUploadFile> actualUploadsResult = buildResult.uploads();

    // Assert
    assertTrue(actualUploadsResult.limit(5).collect(Collectors.toList()).isEmpty());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link TestResult#toString()}
   *   <li>{@link TestResult#getExitCode()}
   *   <li>{@link TestResult#getInstanceId()}
   *   <li>{@link TestResult#getTestName()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"int TestResult.getExitCode()", "String TestResult.getInstanceId()",
      "String TestResult.getTestName()", "String TestResult.toString()"})
  void testGettersAndSetters() {
    // Arrange
    TestResult buildResult = TestResult.builder().exitCode(1).instanceId("42").testName("Test Name").build();

    // Act
    String actualToStringResult = buildResult.toString();
    int actualExitCode = buildResult.getExitCode();
    String actualInstanceId = buildResult.getInstanceId();

    // Assert
    assertEquals("42", actualInstanceId);
    assertEquals("Test Name", buildResult.getTestName());
    assertEquals("TestResult{testName='Test Name', exitCode=1, instanceId='42', rootFiles=[], nestedFiles=[]}",
        actualToStringResult);
    assertEquals(1, actualExitCode);
  }

  /**
   * Test {@link TestResult#equals(Object)}, and {@link TestResult#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link TestResult#equals(Object)}
   *   <li>{@link TestResult#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean TestResult.equals(Object)", "int TestResult.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    TestResult buildResult = TestResult.builder().exitCode(1).instanceId("42").testName("Test Name").build();
    TestResult buildResult2 = TestResult.builder().exitCode(1).instanceId("42").testName("Test Name").build();

    // Act and Assert
    assertEquals(buildResult, buildResult2);
    int expectedHashCodeResult = buildResult.hashCode();
    assertEquals(expectedHashCodeResult, buildResult2.hashCode());
  }

  /**
   * Test {@link TestResult#equals(Object)}, and {@link TestResult#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link TestResult#equals(Object)}
   *   <li>{@link TestResult#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean TestResult.equals(Object)", "int TestResult.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    TestResult buildResult = TestResult.builder().exitCode(1).instanceId("42").testName("Test Name").build();

    // Act and Assert
    assertEquals(buildResult, buildResult);
    int expectedHashCodeResult = buildResult.hashCode();
    assertEquals(expectedHashCodeResult, buildResult.hashCode());
  }

  /**
   * Test {@link TestResult#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link TestResult#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean TestResult.equals(Object)", "int TestResult.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    TestResult buildResult = TestResult.builder().exitCode(0).instanceId("42").testName("Test Name").build();
    TestResult buildResult2 = TestResult.builder().exitCode(1).instanceId("42").testName("Test Name").build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link TestResult#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link TestResult#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean TestResult.equals(Object)", "int TestResult.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    TestResult buildResult = TestResult.builder().exitCode(1).instanceId("Instance Id").testName("Test Name").build();
    TestResult buildResult2 = TestResult.builder().exitCode(1).instanceId("42").testName("Test Name").build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link TestResult#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link TestResult#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean TestResult.equals(Object)", "int TestResult.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual3() {
    // Arrange
    TestResult buildResult = TestResult.builder().exitCode(1).instanceId("42").testName("42").build();
    TestResult buildResult2 = TestResult.builder().exitCode(1).instanceId("42").testName("Test Name").build();

    // Act and Assert
    assertNotEquals(buildResult, buildResult2);
  }

  /**
   * Test {@link TestResult#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link TestResult#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean TestResult.equals(Object)", "int TestResult.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange
    TestResult buildResult = TestResult.builder().exitCode(1).instanceId("42").testName("Test Name").build();

    // Act and Assert
    assertNotEquals(buildResult, null);
  }

  /**
   * Test {@link TestResult#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link TestResult#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean TestResult.equals(Object)", "int TestResult.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange
    TestResult buildResult = TestResult.builder().exitCode(1).instanceId("42").testName("Test Name").build();

    // Act and Assert
    assertNotEquals(buildResult, "Different type to TestResult");
  }
}
