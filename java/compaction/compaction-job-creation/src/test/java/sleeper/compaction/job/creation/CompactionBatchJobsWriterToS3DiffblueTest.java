package sleeper.compaction.job.creation;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3EncryptionClientV2;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import sleeper.compaction.core.job.CompactionJob;
import sleeper.compaction.core.job.CompactionJob.Builder;

class CompactionBatchJobsWriterToS3DiffblueTest {
  /**
   * Test {@link CompactionBatchJobsWriterToS3#writeJobs(String, String, List)}.
   *
   * <p>Method under test: {@link CompactionBatchJobsWriterToS3#writeJobs(String, String, List)}
   */
  @Test
  @DisplayName("Test writeJobs(String, String, List)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void CompactionBatchJobsWriterToS3.writeJobs(String, String, List)"})
  void testWriteJobs() throws SdkClientException, UnknownHostException {
    // Arrange
    try (MockedStatic<InetAddress> mockInetAddress = mockStatic(InetAddress.class)) {
      mockInetAddress
          .when(() -> InetAddress.getByAddress(Mockito.<byte[]>any()))
          .thenReturn(mock(InetAddress.class));
      mockInetAddress.when(InetAddress::getLocalHost).thenReturn(mock(InetAddress.class));
      mockInetAddress
          .when(() -> InetAddress.getByName(Mockito.<String>any()))
          .thenReturn(mock(InetAddress.class));
      mockInetAddress
          .when(() -> InetAddress.getAllByName(Mockito.<String>any()))
          .thenReturn(new InetAddress[] {mock(InetAddress.class)});

      PutObjectResult putObjectResult = new PutObjectResult();
      putObjectResult.setBucketKeyEnabled(true);
      putObjectResult.setContentMd5("MjdjN2NmNDAwMjI5MTAzZTAwYzZkODgzMDAyOWUyOWI=");
      putObjectResult.setETag("E Tag");
      putObjectResult.setExpirationTime(
          Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
      putObjectResult.setExpirationTimeRuleId("42");
      putObjectResult.setMetadata(new ObjectMetadata());
      putObjectResult.setRequesterCharged(true);
      putObjectResult.setSSEAlgorithm("Algorithm");
      putObjectResult.setSSECustomerAlgorithm("Algorithm");
      putObjectResult.setSSECustomerKeyMd5("27c7cf400229103e00c6d8830029e29b");
      putObjectResult.setVersionId("42");

      AmazonS3EncryptionClientV2 s3Client = mock(AmazonS3EncryptionClientV2.class);
      when(s3Client.putObject(Mockito.<String>any(), Mockito.<String>any(), Mockito.<String>any()))
          .thenReturn(putObjectResult);
      CompactionBatchJobsWriterToS3 compactionBatchJobsWriterToS3 =
          new CompactionBatchJobsWriterToS3(s3Client);

      ArrayList<CompactionJob> compactionJobs = new ArrayList<>();

      Builder builderResult = CompactionJob.builder();
      compactionJobs.add(
          builderResult
              .inputFiles(new ArrayList<>())
              .jobId("42")
              .outputFile("Output File")
              .partitionId("42")
              .tableId("42")
              .build());

      // Act
      compactionBatchJobsWriterToS3.writeJobs("s3://bucket-name/object-key", "Key", compactionJobs);

      // Assert
      verify(s3Client)
          .putObject(
              "s3://bucket-name/object-key",
              "Key",
              "{\"jobs\":[{\"tableId\":\"42\",\"jobId\":\"42\",\"inputFiles\":[],\"outputFile\":\"Output File\",\"partitionId\":\"42\"}]}");
    }
  }

  /**
   * Test {@link CompactionBatchJobsWriterToS3#writeJobs(String, String, List)}.
   *
   * <p>Method under test: {@link CompactionBatchJobsWriterToS3#writeJobs(String, String, List)}
   */
  @Test
  @DisplayName("Test writeJobs(String, String, List)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void CompactionBatchJobsWriterToS3.writeJobs(String, String, List)"})
  void testWriteJobs2() throws SdkClientException, UnknownHostException {
    // Arrange
    try (MockedStatic<InetAddress> mockInetAddress = mockStatic(InetAddress.class)) {
      mockInetAddress
          .when(() -> InetAddress.getByAddress(Mockito.<byte[]>any()))
          .thenReturn(mock(InetAddress.class));
      mockInetAddress.when(InetAddress::getLocalHost).thenReturn(mock(InetAddress.class));
      mockInetAddress
          .when(() -> InetAddress.getByName(Mockito.<String>any()))
          .thenReturn(mock(InetAddress.class));
      mockInetAddress
          .when(() -> InetAddress.getAllByName(Mockito.<String>any()))
          .thenReturn(new InetAddress[] {mock(InetAddress.class)});

      PutObjectResult putObjectResult = new PutObjectResult();
      putObjectResult.setBucketKeyEnabled(true);
      putObjectResult.setContentMd5("MjdjN2NmNDAwMjI5MTAzZTAwYzZkODgzMDAyOWUyOWI=");
      putObjectResult.setETag("E Tag");
      putObjectResult.setExpirationTime(
          Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
      putObjectResult.setExpirationTimeRuleId("42");
      putObjectResult.setMetadata(new ObjectMetadata());
      putObjectResult.setRequesterCharged(true);
      putObjectResult.setSSEAlgorithm("Algorithm");
      putObjectResult.setSSECustomerAlgorithm("Algorithm");
      putObjectResult.setSSECustomerKeyMd5("27c7cf400229103e00c6d8830029e29b");
      putObjectResult.setVersionId("42");

      AmazonS3EncryptionClientV2 s3Client = mock(AmazonS3EncryptionClientV2.class);
      when(s3Client.putObject(Mockito.<String>any(), Mockito.<String>any(), Mockito.<String>any()))
          .thenReturn(putObjectResult);
      CompactionBatchJobsWriterToS3 compactionBatchJobsWriterToS3 =
          new CompactionBatchJobsWriterToS3(s3Client);

      ArrayList<CompactionJob> compactionJobs = new ArrayList<>();

      Builder builderResult = CompactionJob.builder();
      compactionJobs.add(
          builderResult
              .inputFiles(new ArrayList<>())
              .jobId("42")
              .outputFile("Output File")
              .partitionId("42")
              .tableId("42")
              .build());

      Builder builderResult2 = CompactionJob.builder();
      compactionJobs.add(
          builderResult2
              .inputFiles(new ArrayList<>())
              .jobId("42")
              .outputFile("Output File")
              .partitionId("42")
              .tableId("42")
              .build());

      // Act
      compactionBatchJobsWriterToS3.writeJobs("s3://bucket-name/object-key", "Key", compactionJobs);

      // Assert
      verify(s3Client)
          .putObject(
              "s3://bucket-name/object-key",
              "Key",
              "{\"jobs\":[{\"tableId\":\"42\",\"jobId\":\"42\",\"inputFiles\":[],\"outputFile\":\"Output File\",\"partitionId\":\"42\"},{\"tableId\":\"42\",\"jobId\":\"42\",\"inputFiles\":[],\"outputFile\":\"Output File\",\"partitionId\":\"42\"}]}");
    }
  }

  /**
   * Test {@link CompactionBatchJobsWriterToS3#writeJobs(String, String, List)}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.
   *   <li>Then calls {@link AmazonS3EncryptionClientV2#putObject(String, String, String)}.
   * </ul>
   *
   * <p>Method under test: {@link CompactionBatchJobsWriterToS3#writeJobs(String, String, List)}
   */
  @Test
  @DisplayName(
      "Test writeJobs(String, String, List); when ArrayList(); then calls putObject(String, String, String)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void CompactionBatchJobsWriterToS3.writeJobs(String, String, List)"})
  void testWriteJobs_whenArrayList_thenCallsPutObject()
      throws SdkClientException, UnknownHostException {
    // Arrange
    try (MockedStatic<InetAddress> mockInetAddress = mockStatic(InetAddress.class)) {
      mockInetAddress
          .when(() -> InetAddress.getByAddress(Mockito.<byte[]>any()))
          .thenReturn(mock(InetAddress.class));
      mockInetAddress.when(InetAddress::getLocalHost).thenReturn(mock(InetAddress.class));
      mockInetAddress
          .when(() -> InetAddress.getByName(Mockito.<String>any()))
          .thenReturn(mock(InetAddress.class));
      mockInetAddress
          .when(() -> InetAddress.getAllByName(Mockito.<String>any()))
          .thenReturn(new InetAddress[] {mock(InetAddress.class)});

      PutObjectResult putObjectResult = new PutObjectResult();
      putObjectResult.setBucketKeyEnabled(true);
      putObjectResult.setContentMd5("MjdjN2NmNDAwMjI5MTAzZTAwYzZkODgzMDAyOWUyOWI=");
      putObjectResult.setETag("E Tag");
      putObjectResult.setExpirationTime(
          Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
      putObjectResult.setExpirationTimeRuleId("42");
      putObjectResult.setMetadata(new ObjectMetadata());
      putObjectResult.setRequesterCharged(true);
      putObjectResult.setSSEAlgorithm("Algorithm");
      putObjectResult.setSSECustomerAlgorithm("Algorithm");
      putObjectResult.setSSECustomerKeyMd5("27c7cf400229103e00c6d8830029e29b");
      putObjectResult.setVersionId("42");

      AmazonS3EncryptionClientV2 s3Client = mock(AmazonS3EncryptionClientV2.class);
      when(s3Client.putObject(Mockito.<String>any(), Mockito.<String>any(), Mockito.<String>any()))
          .thenReturn(putObjectResult);
      CompactionBatchJobsWriterToS3 compactionBatchJobsWriterToS3 =
          new CompactionBatchJobsWriterToS3(s3Client);

      // Act
      compactionBatchJobsWriterToS3.writeJobs(
          "s3://bucket-name/object-key", "Key", new ArrayList<>());

      // Assert
      verify(s3Client).putObject("s3://bucket-name/object-key", "Key", "{\"jobs\":[]}");
    }
  }
}
