package sleeper.systemtest.drivers.nightly;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

class NightlyTestOutputDiffblueTest {
  /**
   * Test {@link NightlyTestOutput#NightlyTestOutput(List)}.
   * <ul>
   *   <li>Then return Tests is {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NightlyTestOutput#NightlyTestOutput(List)}
   */
  @Test
  @DisplayName("Test new NightlyTestOutput(List); then return Tests is ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void NightlyTestOutput.<init>(List)"})
  void testNewNightlyTestOutput_thenReturnTestsIsArrayList() {
    // Arrange
    ArrayList<TestResult> tests = new ArrayList<>();
    TestResult buildResult = TestResult.builder().exitCode(1).instanceId("42").testName("Test Name").build();
    tests.add(buildResult);

    // Act and Assert
    assertSame(tests, (new NightlyTestOutput(tests)).getTests());
  }

  /**
   * Test {@link NightlyTestOutput#NightlyTestOutput(List)}.
   * <ul>
   *   <li>Then return Tests is {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NightlyTestOutput#NightlyTestOutput(List)}
   */
  @Test
  @DisplayName("Test new NightlyTestOutput(List); then return Tests is ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void NightlyTestOutput.<init>(List)"})
  void testNewNightlyTestOutput_thenReturnTestsIsArrayList2() {
    // Arrange
    ArrayList<TestResult> tests = new ArrayList<>();
    TestResult buildResult = TestResult.builder().exitCode(1).instanceId("42").testName("Test Name").build();
    tests.add(buildResult);
    TestResult buildResult2 = TestResult.builder().exitCode(1).instanceId("42").testName("Test Name").build();
    tests.add(buildResult2);

    // Act and Assert
    assertSame(tests, (new NightlyTestOutput(tests)).getTests());
  }

  /**
   * Test {@link NightlyTestOutput#NightlyTestOutput(List)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return Tests Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link NightlyTestOutput#NightlyTestOutput(List)}
   */
  @Test
  @DisplayName("Test new NightlyTestOutput(List); when ArrayList(); then return Tests Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void NightlyTestOutput.<init>(List)"})
  void testNewNightlyTestOutput_whenArrayList_thenReturnTestsEmpty() {
    // Arrange, Act and Assert
    assertTrue((new NightlyTestOutput(new ArrayList<>())).getTests().isEmpty());
  }

  /**
   * Test {@link NightlyTestOutput#uploadToS3(AmazonS3, String, NightlyTestTimestamp)}.
   * <ul>
   *   <li>Given {@code false}.</li>
   *   <li>Then calls {@link AmazonS3Client#doesObjectExist(String, String)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NightlyTestOutput#uploadToS3(AmazonS3, String, NightlyTestTimestamp)}
   */
  @Test
  @DisplayName("Test uploadToS3(AmazonS3, String, NightlyTestTimestamp); given 'false'; then calls doesObjectExist(String, String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void NightlyTestOutput.uploadToS3(AmazonS3, String, NightlyTestTimestamp)"})
  void testUploadToS3_givenFalse_thenCallsDoesObjectExist() throws SdkClientException, UnknownHostException {
    try (MockedStatic<InetAddress> mockInetAddress = mockStatic(InetAddress.class)) {

      // Arrange
      mockInetAddress.when(() -> InetAddress.getByAddress(Mockito.<byte[]>any())).thenReturn(mock(InetAddress.class));
      mockInetAddress.when(() -> InetAddress.getByName(Mockito.<String>any())).thenReturn(mock(InetAddress.class));
      mockInetAddress.when(() -> InetAddress.getAllByName(Mockito.<String>any()))
          .thenReturn(new InetAddress[]{mock(InetAddress.class)});
      NightlyTestOutput emptyOutputResult = NightlyTestOutputTestHelper.emptyOutput();

      PutObjectResult putObjectResult = new PutObjectResult();
      putObjectResult.setBucketKeyEnabled(true);
      putObjectResult.setContentMd5("MjdjN2NmNDAwMjI5MTAzZTAwYzZkODgzMDAyOWUyOWI=");
      putObjectResult.setETag("E Tag");
      putObjectResult
          .setExpirationTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
      putObjectResult.setExpirationTimeRuleId("42");
      putObjectResult.setMetadata(new ObjectMetadata());
      putObjectResult.setRequesterCharged(true);
      putObjectResult.setSSEAlgorithm("Algorithm");
      putObjectResult.setSSECustomerAlgorithm("Algorithm");
      putObjectResult.setSSECustomerKeyMd5("27c7cf400229103e00c6d8830029e29b");
      putObjectResult.setVersionId("42");
      AmazonS3Client s3Client = mock(AmazonS3Client.class);
      when(s3Client.doesObjectExist(Mockito.<String>any(), Mockito.<String>any())).thenReturn(false);
      when(s3Client.putObject(Mockito.<String>any(), Mockito.<String>any(), Mockito.<String>any()))
          .thenReturn(putObjectResult);

      // Act
      emptyOutputResult.uploadToS3(s3Client, "s3://bucket-name/object-key",
          NightlyTestTimestamp.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

      // Assert
      verify(s3Client).doesObjectExist(eq("s3://bucket-name/object-key"), eq("summary.json"));
      verify(s3Client, atLeast(1)).putObject(eq("s3://bucket-name/object-key"), Mockito.<String>any(),
          Mockito.<String>any());
    }
  }

  /**
   * Test {@link NightlyTestOutput#uploads()}.
   * <p>
   * Method under test: {@link NightlyTestOutput#uploads()}
   */
  @Test
  @DisplayName("Test uploads()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Stream NightlyTestOutput.uploads()"})
  void testUploads() {
    // Arrange and Act
    Stream<NightlyTestUploadFile> actualUploadsResult = NightlyTestOutputTestHelper.emptyOutput().uploads();

    // Assert
    assertTrue(actualUploadsResult.limit(5).collect(Collectors.toList()).isEmpty());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link NightlyTestOutput#toString()}
   *   <li>{@link NightlyTestOutput#getTests()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List NightlyTestOutput.getTests()", "String NightlyTestOutput.toString()"})
  void testGettersAndSetters() {
    // Arrange
    ArrayList<TestResult> tests = new ArrayList<>();
    NightlyTestOutput nightlyTestOutput = new NightlyTestOutput(tests);

    // Act
    String actualToStringResult = nightlyTestOutput.toString();
    List<TestResult> actualTests = nightlyTestOutput.getTests();

    // Assert
    assertEquals("NightlyTestOutput{tests=[]}", actualToStringResult);
    assertTrue(actualTests.isEmpty());
    assertSame(tests, actualTests);
  }

  /**
   * Test {@link NightlyTestOutput#from(Path)}.
   * <ul>
   *   <li>When Property is {@code java.io.tmpdir} is empty string.</li>
   *   <li>Then return Tests Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link NightlyTestOutput#from(Path)}
   */
  @Test
  @DisplayName("Test from(Path); when Property is 'java.io.tmpdir' is empty string; then return Tests Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"NightlyTestOutput NightlyTestOutput.from(Path)"})
  void testFrom_whenPropertyIsJavaIoTmpdirIsEmptyString_thenReturnTestsEmpty() throws IOException {
    // Arrange, Act and Assert
    assertTrue(NightlyTestOutput.from(Paths.get(System.getProperty("java.io.tmpdir"), "")).getTests().isEmpty());
  }

  /**
   * Test {@link NightlyTestOutput#from(Path)}.
   * <ul>
   *   <li>When Property is {@code java.io.tmpdir} is {@code test.txt}.</li>
   *   <li>Then return Tests Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link NightlyTestOutput#from(Path)}
   */
  @Test
  @DisplayName("Test from(Path); when Property is 'java.io.tmpdir' is 'test.txt'; then return Tests Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"NightlyTestOutput NightlyTestOutput.from(Path)"})
  void testFrom_whenPropertyIsJavaIoTmpdirIsTestTxt_thenReturnTestsEmpty() throws IOException {
    // Arrange, Act and Assert
    assertTrue(
        NightlyTestOutput.from(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt")).getTests().isEmpty());
  }

  /**
   * Test {@link NightlyTestOutput#equals(Object)}, and {@link NightlyTestOutput#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link NightlyTestOutput#equals(Object)}
   *   <li>{@link NightlyTestOutput#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean NightlyTestOutput.equals(Object)", "int NightlyTestOutput.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    NightlyTestOutput emptyOutputResult = NightlyTestOutputTestHelper.emptyOutput();
    NightlyTestOutput emptyOutputResult2 = NightlyTestOutputTestHelper.emptyOutput();

    // Act and Assert
    assertEquals(emptyOutputResult, emptyOutputResult2);
    int expectedHashCodeResult = emptyOutputResult.hashCode();
    assertEquals(expectedHashCodeResult, emptyOutputResult2.hashCode());
  }

  /**
   * Test {@link NightlyTestOutput#equals(Object)}, and {@link NightlyTestOutput#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link NightlyTestOutput#equals(Object)}
   *   <li>{@link NightlyTestOutput#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean NightlyTestOutput.equals(Object)", "int NightlyTestOutput.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    NightlyTestOutput emptyOutputResult = NightlyTestOutputTestHelper.emptyOutput();

    // Act and Assert
    assertEquals(emptyOutputResult, emptyOutputResult);
    int expectedHashCodeResult = emptyOutputResult.hashCode();
    assertEquals(expectedHashCodeResult, emptyOutputResult.hashCode());
  }

  /**
   * Test {@link NightlyTestOutput#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link NightlyTestOutput#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean NightlyTestOutput.equals(Object)", "int NightlyTestOutput.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(NightlyTestOutputTestHelper.emptyOutput(), null);
  }

  /**
   * Test {@link NightlyTestOutput#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link NightlyTestOutput#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean NightlyTestOutput.equals(Object)", "int NightlyTestOutput.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(NightlyTestOutputTestHelper.emptyOutput(), null);
  }

  /**
   * Test {@link NightlyTestOutput#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link NightlyTestOutput#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean NightlyTestOutput.equals(Object)", "int NightlyTestOutput.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(NightlyTestOutputTestHelper.emptyOutput(), "Different type to NightlyTestOutput");
  }
}
