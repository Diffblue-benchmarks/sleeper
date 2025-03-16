package sleeper.systemtest.drivers.nightly;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
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
import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import sleeper.systemtest.drivers.nightly.NightlyTestUploader.Builder;

class NightlyTestUploaderDiffblueTest {
  /**
   * Test {@link NightlyTestUploader#upload(NightlyTestUploadFile)} with {@code file}.
   * <ul>
   *   <li>Then calls {@link AmazonS3Client#putObject(String, String, File)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NightlyTestUploader#upload(NightlyTestUploadFile)}
   */
  @Test
  @DisplayName("Test upload(NightlyTestUploadFile) with 'file'; then calls putObject(String, String, File)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void NightlyTestUploader.upload(NightlyTestUploadFile)"})
  void testUploadWithFile_thenCallsPutObject() throws SdkClientException {
    // Arrange
    PutObjectResult putObjectResult = new PutObjectResult();
    putObjectResult.setContentMd5("MjdjN2NmNDAwMjI5MTAzZTAwYzZkODgzMDAyOWUyOWI=");
    AmazonS3Client s3Client = mock(AmazonS3Client.class);
    when(s3Client.putObject(Mockito.<String>any(), Mockito.<String>any(), Mockito.<File>any()))
        .thenReturn(putObjectResult);
    Builder s3ClientResult = NightlyTestUploader.builder().bucketName("bucket-name").s3Client(s3Client);
    NightlyTestUploader buildResult = s3ClientResult
        .timestamp(
            NightlyTestTimestamp.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()))
        .build();

    // Act
    buildResult
        .upload(NightlyTestUploadFile.fileInUploadDir(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt")));

    // Assert
    verify(s3Client).putObject(eq("bucket-name"), eq("19700101_000000/test.txt"), isA(File.class));
  }

  /**
   * Test {@link NightlyTestUploader#upload(NightlyTestOutput)} with {@code output}.
   * <ul>
   *   <li>Then calls {@link AmazonS3#doesObjectExist(String, String)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NightlyTestUploader#upload(NightlyTestOutput)}
   */
  @Test
  @DisplayName("Test upload(NightlyTestOutput) with 'output'; then calls doesObjectExist(String, String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void NightlyTestUploader.upload(NightlyTestOutput)"})
  void testUploadWithOutput_thenCallsDoesObjectExist() throws SdkClientException, UnknownHostException {
    try (MockedStatic<InetAddress> mockInetAddress = mockStatic(InetAddress.class)) {

      // Arrange
      mockInetAddress.when(() -> InetAddress.getByAddress(Mockito.<byte[]>any())).thenReturn(mock(InetAddress.class));
      mockInetAddress.when(() -> InetAddress.getByName(Mockito.<String>any())).thenReturn(mock(InetAddress.class));
      mockInetAddress.when(() -> InetAddress.getAllByName(Mockito.<String>any()))
          .thenReturn(new InetAddress[]{mock(InetAddress.class)});

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
      AmazonS3 s3Client = mock(AmazonS3.class);
      when(s3Client.doesObjectExist(Mockito.<String>any(), Mockito.<String>any())).thenReturn(false);
      when(s3Client.putObject(Mockito.<String>any(), Mockito.<String>any(), Mockito.<String>any()))
          .thenReturn(putObjectResult);
      Builder s3ClientResult = NightlyTestUploader.builder().bucketName("bucket-name").s3Client(s3Client);
      NightlyTestUploader buildResult = s3ClientResult
          .timestamp(
              NightlyTestTimestamp.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()))
          .build();

      // Act
      buildResult.upload(NightlyTestOutputTestHelper.emptyOutput());

      // Assert
      verify(s3Client).doesObjectExist(eq("bucket-name"), eq("summary.json"));
      verify(s3Client, atLeast(1)).putObject(eq("bucket-name"), Mockito.<String>any(), Mockito.<String>any());
    }
  }
}
