package sleeper.systemtest.drivers.nightly;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
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
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.HashMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

class NightlyTestSummaryTableDiffblueTest {
  /**
   * Test {@link NightlyTestSummaryTable#empty()}.
   * <p>
   * Method under test: {@link NightlyTestSummaryTable#empty()}
   */
  @Test
  @DisplayName("Test empty()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"NightlyTestSummaryTable NightlyTestSummaryTable.empty()"})
  void testEmpty() {
    // Arrange, Act and Assert
    assertEquals("{\n  \"executions\": []\n}", NightlyTestSummaryTable.empty().toJson());
  }

  /**
   * Test {@link NightlyTestSummaryTable#fromJson(String)}.
   * <ul>
   *   <li>When empty string.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NightlyTestSummaryTable#fromJson(String)}
   */
  @Test
  @DisplayName("Test fromJson(String); when empty string; then return 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"NightlyTestSummaryTable NightlyTestSummaryTable.fromJson(String)"})
  void testFromJson_whenEmptyString_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(NightlyTestSummaryTable.fromJson(""));
  }

  /**
   * Test {@link NightlyTestSummaryTable#fromJson(String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NightlyTestSummaryTable#fromJson(String)}
   */
  @Test
  @DisplayName("Test fromJson(String); when 'null'; then return 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"NightlyTestSummaryTable NightlyTestSummaryTable.fromJson(String)"})
  void testFromJson_whenNull_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(NightlyTestSummaryTable.fromJson(null));
  }

  /**
   * Test {@link NightlyTestSummaryTable#fromS3(AmazonS3, String)}.
   * <ul>
   *   <li>Given {@code false}.</li>
   *   <li>Then return toJson is {@code { "executions": [] }}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NightlyTestSummaryTable#fromS3(AmazonS3, String)}
   */
  @Test
  @DisplayName("Test fromS3(AmazonS3, String); given 'false'; then return toJson is '{ \"executions\": [] }'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"NightlyTestSummaryTable NightlyTestSummaryTable.fromS3(AmazonS3, String)"})
  void testFromS3_givenFalse_thenReturnToJsonIsExecutions() throws SdkClientException, UnknownHostException {
    try (MockedStatic<InetAddress> mockInetAddress = mockStatic(InetAddress.class)) {

      // Arrange
      mockInetAddress.when(() -> InetAddress.getByAddress(Mockito.<byte[]>any())).thenReturn(mock(InetAddress.class));
      mockInetAddress.when(() -> InetAddress.getByName(Mockito.<String>any())).thenReturn(mock(InetAddress.class));
      mockInetAddress.when(() -> InetAddress.getAllByName(Mockito.<String>any()))
          .thenReturn(new InetAddress[]{mock(InetAddress.class)});
      AmazonS3Client s3Client = mock(AmazonS3Client.class);
      when(s3Client.doesObjectExist(Mockito.<String>any(), Mockito.<String>any())).thenReturn(false);

      // Act
      NightlyTestSummaryTable actualFromS3Result = NightlyTestSummaryTable.fromS3(s3Client,
          "s3://bucket-name/object-key");

      // Assert
      verify(s3Client).doesObjectExist(eq("s3://bucket-name/object-key"), eq("summary.json"));
      assertEquals("{\n  \"executions\": []\n}", actualFromS3Result.toJson());
    }
  }

  /**
   * Test {@link NightlyTestSummaryTable#saveToS3(AmazonS3, String)}.
   * <ul>
   *   <li>Given empty.</li>
   *   <li>Then calls {@link AmazonS3Client#putObject(String, String, String)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NightlyTestSummaryTable#saveToS3(AmazonS3, String)}
   */
  @Test
  @DisplayName("Test saveToS3(AmazonS3, String); given empty; then calls putObject(String, String, String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void NightlyTestSummaryTable.saveToS3(AmazonS3, String)"})
  void testSaveToS3_givenEmpty_thenCallsPutObject() throws SdkClientException, UnknownHostException {
    try (MockedStatic<InetAddress> mockInetAddress = mockStatic(InetAddress.class)) {

      // Arrange
      mockInetAddress.when(() -> InetAddress.getByAddress(Mockito.<byte[]>any())).thenReturn(mock(InetAddress.class));
      mockInetAddress.when(() -> InetAddress.getByName(Mockito.<String>any())).thenReturn(mock(InetAddress.class));
      mockInetAddress.when(() -> InetAddress.getAllByName(Mockito.<String>any()))
          .thenReturn(new InetAddress[]{mock(InetAddress.class)});
      NightlyTestSummaryTable emptyResult = NightlyTestSummaryTable.empty();

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
      when(s3Client.putObject(Mockito.<String>any(), Mockito.<String>any(), Mockito.<String>any()))
          .thenReturn(putObjectResult);

      // Act
      emptyResult.saveToS3(s3Client, "s3://bucket-name/object-key");

      // Assert
      verify(s3Client, atLeast(1)).putObject(eq("s3://bucket-name/object-key"), Mockito.<String>any(),
          Mockito.<String>any());
    }
  }

  /**
   * Test {@link NightlyTestSummaryTable#saveToS3(AmazonS3, String)}.
   * <ul>
   *   <li>Given {@link PutObjectResult} (default constructor) BucketKeyEnabled is {@code true}.</li>
   *   <li>Then calls {@link AmazonS3Client#putObject(String, String, String)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NightlyTestSummaryTable#saveToS3(AmazonS3, String)}
   */
  @Test
  @DisplayName("Test saveToS3(AmazonS3, String); given PutObjectResult (default constructor) BucketKeyEnabled is 'true'; then calls putObject(String, String, String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void NightlyTestSummaryTable.saveToS3(AmazonS3, String)"})
  void testSaveToS3_givenPutObjectResultBucketKeyEnabledIsTrue_thenCallsPutObject()
      throws SdkClientException, UnknownHostException {
    try (MockedStatic<InetAddress> mockInetAddress = mockStatic(InetAddress.class)) {

      // Arrange
      mockInetAddress.when(() -> InetAddress.getByAddress(Mockito.<byte[]>any())).thenReturn(mock(InetAddress.class));
      mockInetAddress.when(() -> InetAddress.getByName(Mockito.<String>any())).thenReturn(mock(InetAddress.class));
      mockInetAddress.when(() -> InetAddress.getAllByName(Mockito.<String>any()))
          .thenReturn(new InetAddress[]{mock(InetAddress.class)});
      NightlyTestSummaryTable emptyResult = NightlyTestSummaryTable.empty();
      NightlyTestTimestamp timestamp = NightlyTestTimestamp
          .from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
      emptyResult.add(timestamp, NightlyTestOutputTestHelper.emptyOutput());

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
      when(s3Client.putObject(Mockito.<String>any(), Mockito.<String>any(), Mockito.<String>any()))
          .thenReturn(putObjectResult);

      // Act
      emptyResult.saveToS3(s3Client, "s3://bucket-name/object-key");

      // Assert
      verify(s3Client, atLeast(1)).putObject(eq("s3://bucket-name/object-key"), Mockito.<String>any(),
          Mockito.<String>any());
    }
  }

  /**
   * Test {@link NightlyTestSummaryTable#saveToS3(AmazonS3, String)}.
   * <ul>
   *   <li>Given {@link PutObjectResult} (default constructor) BucketKeyEnabled is {@code true}.</li>
   *   <li>Then calls {@link AmazonS3Client#putObject(String, String, String)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NightlyTestSummaryTable#saveToS3(AmazonS3, String)}
   */
  @Test
  @DisplayName("Test saveToS3(AmazonS3, String); given PutObjectResult (default constructor) BucketKeyEnabled is 'true'; then calls putObject(String, String, String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void NightlyTestSummaryTable.saveToS3(AmazonS3, String)"})
  void testSaveToS3_givenPutObjectResultBucketKeyEnabledIsTrue_thenCallsPutObject2()
      throws SdkClientException, UnknownHostException {
    try (MockedStatic<InetAddress> mockInetAddress = mockStatic(InetAddress.class)) {

      // Arrange
      mockInetAddress.when(() -> InetAddress.getByAddress(Mockito.<byte[]>any())).thenReturn(mock(InetAddress.class));
      mockInetAddress.when(() -> InetAddress.getByName(Mockito.<String>any())).thenReturn(mock(InetAddress.class));
      mockInetAddress.when(() -> InetAddress.getAllByName(Mockito.<String>any()))
          .thenReturn(new InetAddress[]{mock(InetAddress.class)});
      NightlyTestSummaryTable emptyResult = NightlyTestSummaryTable.empty();
      NightlyTestTimestamp timestamp = NightlyTestTimestamp
          .from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
      emptyResult.add(timestamp, NightlyTestOutputTestHelper.emptyOutput());
      NightlyTestTimestamp timestamp2 = NightlyTestTimestamp
          .from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
      emptyResult.add(timestamp2, NightlyTestOutputTestHelper.emptyOutput());

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
      when(s3Client.putObject(Mockito.<String>any(), Mockito.<String>any(), Mockito.<String>any()))
          .thenReturn(putObjectResult);

      // Act
      emptyResult.saveToS3(s3Client, "s3://bucket-name/object-key");

      // Assert
      verify(s3Client, atLeast(1)).putObject(eq("s3://bucket-name/object-key"), Mockito.<String>any(),
          Mockito.<String>any());
    }
  }

  /**
   * Test {@link NightlyTestSummaryTable#add(NightlyTestTimestamp, NightlyTestOutput)}.
   * <ul>
   *   <li>Given {@code 42}.</li>
   *   <li>When {@link HashMap#HashMap()} {@code 42} is zero.</li>
   *   <li>Then empty toJson is a string.</li>
   * </ul>
   * <p>
   * Method under test: {@link NightlyTestSummaryTable#add(NightlyTestTimestamp, NightlyTestOutput)}
   */
  @Test
  @DisplayName("Test add(NightlyTestTimestamp, NightlyTestOutput); given '42'; when HashMap() '42' is zero; then empty toJson is a string")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"NightlyTestSummaryTable NightlyTestSummaryTable.add(NightlyTestTimestamp, NightlyTestOutput)"})
  void testAdd_given42_whenHashMap42IsZero_thenEmptyToJsonIsAString() {
    // Arrange
    NightlyTestSummaryTable emptyResult = NightlyTestSummaryTable.empty();
    NightlyTestTimestamp timestamp = NightlyTestTimestamp
        .from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    HashMap<String, Integer> statusCodeByTest = new HashMap<>();
    statusCodeByTest.put("42", 0);
    statusCodeByTest.put("foo", 1);

    // Act
    NightlyTestSummaryTable actualAddResult = emptyResult.add(timestamp,
        NightlyTestOutputTestHelper.outputWithStatusCodeByTest(statusCodeByTest));

    // Assert
    assertEquals("{\n" + "  \"executions\": [\n" + "    {\n" + "      \"startTime\": \"1970-01-01T00:00:00Z\",\n"
        + "      \"tests\": [\n" + "        {\n" + "          \"name\": \"42\",\n" + "          \"exitCode\": 0\n"
        + "        },\n" + "        {\n" + "          \"name\": \"foo\",\n" + "          \"exitCode\": 1\n"
        + "        }\n" + "      ]\n" + "    }\n" + "  ]\n" + "}", emptyResult.toJson());
    assertSame(emptyResult, actualAddResult);
  }

  /**
   * Test {@link NightlyTestSummaryTable#add(NightlyTestTimestamp, NightlyTestOutput)}.
   * <ul>
   *   <li>Given {@code foo}.</li>
   *   <li>When {@link HashMap#HashMap()} {@code foo} is one.</li>
   *   <li>Then empty toJson is a string.</li>
   * </ul>
   * <p>
   * Method under test: {@link NightlyTestSummaryTable#add(NightlyTestTimestamp, NightlyTestOutput)}
   */
  @Test
  @DisplayName("Test add(NightlyTestTimestamp, NightlyTestOutput); given 'foo'; when HashMap() 'foo' is one; then empty toJson is a string")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"NightlyTestSummaryTable NightlyTestSummaryTable.add(NightlyTestTimestamp, NightlyTestOutput)"})
  void testAdd_givenFoo_whenHashMapFooIsOne_thenEmptyToJsonIsAString() {
    // Arrange
    NightlyTestSummaryTable emptyResult = NightlyTestSummaryTable.empty();
    NightlyTestTimestamp timestamp = NightlyTestTimestamp
        .from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    HashMap<String, Integer> statusCodeByTest = new HashMap<>();
    statusCodeByTest.put("foo", 1);

    // Act
    NightlyTestSummaryTable actualAddResult = emptyResult.add(timestamp,
        NightlyTestOutputTestHelper.outputWithStatusCodeByTest(statusCodeByTest));

    // Assert
    assertEquals("{\n" + "  \"executions\": [\n" + "    {\n" + "      \"startTime\": \"1970-01-01T00:00:00Z\",\n"
        + "      \"tests\": [\n" + "        {\n" + "          \"name\": \"foo\",\n" + "          \"exitCode\": 1\n"
        + "        }\n" + "      ]\n" + "    }\n" + "  ]\n" + "}", emptyResult.toJson());
    assertSame(emptyResult, actualAddResult);
  }

  /**
   * Test {@link NightlyTestSummaryTable#add(NightlyTestTimestamp, NightlyTestOutput)}.
   * <ul>
   *   <li>Then empty toJson is {@code { "executions": [ { "startTime": "1970-01-01T00:00:00Z", "tests": [] } ] }}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NightlyTestSummaryTable#add(NightlyTestTimestamp, NightlyTestOutput)}
   */
  @Test
  @DisplayName("Test add(NightlyTestTimestamp, NightlyTestOutput); then empty toJson is '{ \"executions\": [ { \"startTime\": \"1970-01-01T00:00:00Z\", \"tests\": [] } ] }'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"NightlyTestSummaryTable NightlyTestSummaryTable.add(NightlyTestTimestamp, NightlyTestOutput)"})
  void testAdd_thenEmptyToJsonIsExecutionsStartTime19700101t000000zTests() {
    // Arrange
    NightlyTestSummaryTable emptyResult = NightlyTestSummaryTable.empty();
    NightlyTestTimestamp timestamp = NightlyTestTimestamp
        .from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act
    NightlyTestSummaryTable actualAddResult = emptyResult.add(timestamp, NightlyTestOutputTestHelper.emptyOutput());

    // Assert
    assertEquals(
        "{\n  \"executions\": [\n    {\n      \"startTime\": \"1970-01-01T00:00:00Z\",\n      \"tests\": []\n    }\n  ]\n}",
        emptyResult.toJson());
    assertSame(emptyResult, actualAddResult);
  }

  /**
   * Test {@link NightlyTestSummaryTable#toJson()}.
   * <p>
   * Method under test: {@link NightlyTestSummaryTable#toJson()}
   */
  @Test
  @DisplayName("Test toJson()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String NightlyTestSummaryTable.toJson()"})
  void testToJson() {
    // Arrange
    NightlyTestSummaryTable emptyResult = NightlyTestSummaryTable.empty();
    NightlyTestTimestamp timestamp = NightlyTestTimestamp
        .from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    emptyResult.add(timestamp, NightlyTestOutputTestHelper.emptyOutput());
    NightlyTestTimestamp timestamp2 = NightlyTestTimestamp
        .from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    emptyResult.add(timestamp2, NightlyTestOutputTestHelper.emptyOutput());

    // Act and Assert
    assertEquals("{\n" + "  \"executions\": [\n" + "    {\n" + "      \"startTime\": \"1970-01-01T00:00:00Z\",\n"
        + "      \"tests\": []\n" + "    },\n" + "    {\n" + "      \"startTime\": \"1970-01-01T00:00:00Z\",\n"
        + "      \"tests\": []\n" + "    }\n" + "  ]\n" + "}", emptyResult.toJson());
  }

  /**
   * Test {@link NightlyTestSummaryTable#toJson()}.
   * <ul>
   *   <li>Given empty.</li>
   *   <li>Then return {@code { "executions": [] }}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NightlyTestSummaryTable#toJson()}
   */
  @Test
  @DisplayName("Test toJson(); given empty; then return '{ \"executions\": [] }'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String NightlyTestSummaryTable.toJson()"})
  void testToJson_givenEmpty_thenReturnExecutions() {
    // Arrange, Act and Assert
    assertEquals("{\n  \"executions\": []\n}", NightlyTestSummaryTable.empty().toJson());
  }

  /**
   * Test {@link NightlyTestSummaryTable#toJson()}.
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} empty string is one.</li>
   *   <li>Then return a string.</li>
   * </ul>
   * <p>
   * Method under test: {@link NightlyTestSummaryTable#toJson()}
   */
  @Test
  @DisplayName("Test toJson(); given HashMap() empty string is one; then return a string")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String NightlyTestSummaryTable.toJson()"})
  void testToJson_givenHashMapEmptyStringIsOne_thenReturnAString() {
    // Arrange
    HashMap<String, Integer> statusCodeByTest = new HashMap<>();
    statusCodeByTest.put("", 1);
    NightlyTestOutput output = NightlyTestOutputTestHelper.outputWithStatusCodeByTest(statusCodeByTest);
    NightlyTestSummaryTable emptyResult = NightlyTestSummaryTable.empty();
    emptyResult.add(
        NightlyTestTimestamp.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()), output);

    // Act and Assert
    assertEquals("{\n" + "  \"executions\": [\n" + "    {\n" + "      \"startTime\": \"1970-01-01T00:00:00Z\",\n"
        + "      \"tests\": [\n" + "        {\n" + "          \"name\": \"\",\n" + "          \"exitCode\": 1\n"
        + "        }\n" + "      ]\n" + "    }\n" + "  ]\n" + "}", emptyResult.toJson());
  }

  /**
   * Test {@link NightlyTestSummaryTable#toJson()}.
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@code foo} is one.</li>
   *   <li>Then return a string.</li>
   * </ul>
   * <p>
   * Method under test: {@link NightlyTestSummaryTable#toJson()}
   */
  @Test
  @DisplayName("Test toJson(); given HashMap() 'foo' is one; then return a string")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String NightlyTestSummaryTable.toJson()"})
  void testToJson_givenHashMapFooIsOne_thenReturnAString() {
    // Arrange
    HashMap<String, Integer> statusCodeByTest = new HashMap<>();
    statusCodeByTest.put("foo", 1);
    NightlyTestOutput output = NightlyTestOutputTestHelper.outputWithStatusCodeByTest(statusCodeByTest);
    NightlyTestSummaryTable emptyResult = NightlyTestSummaryTable.empty();
    emptyResult.add(
        NightlyTestTimestamp.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()), output);

    // Act and Assert
    assertEquals("{\n" + "  \"executions\": [\n" + "    {\n" + "      \"startTime\": \"1970-01-01T00:00:00Z\",\n"
        + "      \"tests\": [\n" + "        {\n" + "          \"name\": \"foo\",\n" + "          \"exitCode\": 1\n"
        + "        }\n" + "      ]\n" + "    }\n" + "  ]\n" + "}", emptyResult.toJson());
  }

  /**
   * Test {@link NightlyTestSummaryTable#toJson()}.
   * <ul>
   *   <li>Then return {@code { "executions": [ { "startTime": "1970-01-01T00:00:00Z", "tests": [] } ] }}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NightlyTestSummaryTable#toJson()}
   */
  @Test
  @DisplayName("Test toJson(); then return '{ \"executions\": [ { \"startTime\": \"1970-01-01T00:00:00Z\", \"tests\": [] } ] }'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String NightlyTestSummaryTable.toJson()"})
  void testToJson_thenReturnExecutionsStartTime19700101t000000zTests() {
    // Arrange
    NightlyTestSummaryTable emptyResult = NightlyTestSummaryTable.empty();
    NightlyTestTimestamp timestamp = NightlyTestTimestamp
        .from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    emptyResult.add(timestamp, NightlyTestOutputTestHelper.emptyOutput());

    // Act and Assert
    assertEquals(
        "{\n  \"executions\": [\n    {\n      \"startTime\": \"1970-01-01T00:00:00Z\",\n      \"tests\": []\n    }\n  ]\n}",
        emptyResult.toJson());
  }

  /**
   * Test {@link NightlyTestSummaryTable#toTableString()}.
   * <p>
   * Method under test: {@link NightlyTestSummaryTable#toTableString()}
   */
  @Test
  @DisplayName("Test toTableString()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String NightlyTestSummaryTable.toTableString()"})
  void testToTableString() {
    // Arrange
    NightlyTestSummaryTable emptyResult = NightlyTestSummaryTable.empty();
    NightlyTestTimestamp timestamp = NightlyTestTimestamp
        .from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    emptyResult.add(timestamp, NightlyTestOutputTestHelper.emptyOutput());
    NightlyTestTimestamp timestamp2 = NightlyTestTimestamp
        .from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    emptyResult.add(timestamp2, NightlyTestOutputTestHelper.emptyOutput());

    // Act and Assert
    assertEquals("------------------------\n" + "| START_TIME           |\n" + "| 1970-01-01T00:00:00Z |\n"
        + "| 1970-01-01T00:00:00Z |\n" + "------------------------\n", emptyResult.toTableString());
  }

  /**
   * Test {@link NightlyTestSummaryTable#toTableString()}.
   * <ul>
   *   <li>Given empty.</li>
   *   <li>Then return {@code -------------- | START_TIME | --------------}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NightlyTestSummaryTable#toTableString()}
   */
  @Test
  @DisplayName("Test toTableString(); given empty; then return '-------------- | START_TIME | --------------'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String NightlyTestSummaryTable.toTableString()"})
  void testToTableString_givenEmpty_thenReturnStartTime() {
    // Arrange, Act and Assert
    assertEquals("--------------\n| START_TIME |\n--------------\n", NightlyTestSummaryTable.empty().toTableString());
  }

  /**
   * Test {@link NightlyTestSummaryTable#toTableString()}.
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@code FAILED} is zero.</li>
   *   <li>Then return a string.</li>
   * </ul>
   * <p>
   * Method under test: {@link NightlyTestSummaryTable#toTableString()}
   */
  @Test
  @DisplayName("Test toTableString(); given HashMap() 'FAILED' is zero; then return a string")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String NightlyTestSummaryTable.toTableString()"})
  void testToTableString_givenHashMapFailedIsZero_thenReturnAString() {
    // Arrange
    HashMap<String, Integer> statusCodeByTest = new HashMap<>();
    statusCodeByTest.put("FAILED", 0);
    statusCodeByTest.put("foo", 1);
    NightlyTestOutput output = NightlyTestOutputTestHelper.outputWithStatusCodeByTest(statusCodeByTest);
    NightlyTestSummaryTable emptyResult = NightlyTestSummaryTable.empty();
    emptyResult.add(
        NightlyTestTimestamp.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()), output);

    // Act and Assert
    assertEquals(
        "------------------------------------------\n" + "| START_TIME           | FAILED | foo    |\n"
            + "| 1970-01-01T00:00:00Z | PASSED | FAILED |\n" + "------------------------------------------\n",
        emptyResult.toTableString());
  }

  /**
   * Test {@link NightlyTestSummaryTable#toTableString()}.
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@code foo} is one.</li>
   *   <li>Then return a string.</li>
   * </ul>
   * <p>
   * Method under test: {@link NightlyTestSummaryTable#toTableString()}
   */
  @Test
  @DisplayName("Test toTableString(); given HashMap() 'foo' is one; then return a string")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String NightlyTestSummaryTable.toTableString()"})
  void testToTableString_givenHashMapFooIsOne_thenReturnAString() {
    // Arrange
    HashMap<String, Integer> statusCodeByTest = new HashMap<>();
    statusCodeByTest.put("foo", 1);
    NightlyTestOutput output = NightlyTestOutputTestHelper.outputWithStatusCodeByTest(statusCodeByTest);
    NightlyTestSummaryTable emptyResult = NightlyTestSummaryTable.empty();
    emptyResult.add(
        NightlyTestTimestamp.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()), output);

    // Act and Assert
    assertEquals(
        "---------------------------------\n" + "| START_TIME           | foo    |\n"
            + "| 1970-01-01T00:00:00Z | FAILED |\n" + "---------------------------------\n",
        emptyResult.toTableString());
  }

  /**
   * Test {@link NightlyTestSummaryTable#toTableString()}.
   * <ul>
   *   <li>Then return {@code ------------------------ | START_TIME | | 1970-01-01T00:00:00Z | ------------------------}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NightlyTestSummaryTable#toTableString()}
   */
  @Test
  @DisplayName("Test toTableString(); then return '------------------------ | START_TIME | | 1970-01-01T00:00:00Z | ------------------------'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String NightlyTestSummaryTable.toTableString()"})
  void testToTableString_thenReturnStartTime19700101t000000z() {
    // Arrange
    NightlyTestSummaryTable emptyResult = NightlyTestSummaryTable.empty();
    NightlyTestTimestamp timestamp = NightlyTestTimestamp
        .from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    emptyResult.add(timestamp, NightlyTestOutputTestHelper.emptyOutput());

    // Act and Assert
    assertEquals(
        "------------------------\n| START_TIME           |\n| 1970-01-01T00:00:00Z |\n------------------------\n",
        emptyResult.toTableString());
  }
}
