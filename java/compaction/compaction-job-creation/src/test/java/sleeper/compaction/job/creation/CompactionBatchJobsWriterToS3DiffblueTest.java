package sleeper.compaction.job.creation;

import static org.mockito.ArgumentMatchers.eq;
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
   * Test {@link CompactionBatchJobsWriterToS3#CompactionBatchJobsWriterToS3(AmazonS3)}.
   * <ul>
   *   <li>Then calls {@link AmazonS3Client#putObject(String, String, String)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionBatchJobsWriterToS3#CompactionBatchJobsWriterToS3(AmazonS3)}
   */
  @Test
  @DisplayName("Test new CompactionBatchJobsWriterToS3(AmazonS3); then calls putObject(String, String, String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void CompactionBatchJobsWriterToS3.<init>(AmazonS3)"})
  void testNewCompactionBatchJobsWriterToS3_thenCallsPutObject() throws SdkClientException, UnknownHostException {
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
      AmazonS3Client s3Client = mock(AmazonS3Client.class);
      when(s3Client.putObject(Mockito.<String>any(), Mockito.<String>any(), Mockito.<String>any()))
          .thenReturn(putObjectResult);

      // Act
      CompactionBatchJobsWriterToS3 actualCompactionBatchJobsWriterToS3 = new CompactionBatchJobsWriterToS3(s3Client);
      actualCompactionBatchJobsWriterToS3.writeJobs("s3://bucket-name/object-key", "Key", new ArrayList<>());

      // Assert
      verify(s3Client).putObject(eq("s3://bucket-name/object-key"), eq("Key"), eq("{\"jobs\":[]}"));
    }
  }

  /**
   * Test {@link CompactionBatchJobsWriterToS3#writeJobs(String, String, List)}.
   * <p>
   * Method under test: {@link CompactionBatchJobsWriterToS3#writeJobs(String, String, List)}
   */
  @Test
  @DisplayName("Test writeJobs(String, String, List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void CompactionBatchJobsWriterToS3.writeJobs(String, String, List)"})
  void testWriteJobs() throws SdkClientException, UnknownHostException {
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
      AmazonS3Client s3Client = mock(AmazonS3Client.class);
      when(s3Client.putObject(Mockito.<String>any(), Mockito.<String>any(), Mockito.<String>any()))
          .thenReturn(putObjectResult);
      CompactionBatchJobsWriterToS3 compactionBatchJobsWriterToS3 = new CompactionBatchJobsWriterToS3(s3Client);

      ArrayList<CompactionJob> compactionJobs = new ArrayList<>();
      Builder builderResult = CompactionJob.builder();
      CompactionJob buildResult = builderResult.inputFiles(new ArrayList<>())
          .jobId("42")
          .outputFile("Output File")
          .partitionId("42")
          .tableId("42")
          .build();
      compactionJobs.add(buildResult);

      // Act
      compactionBatchJobsWriterToS3.writeJobs("s3://bucket-name/object-key", "Key", compactionJobs);

      // Assert
      verify(s3Client).putObject(eq("s3://bucket-name/object-key"), eq("Key"), eq(
          "{\"jobs\":[{\"tableId\":\"42\",\"jobId\":\"42\",\"inputFiles\":[],\"outputFile\":\"Output File\",\"partitionId\":\"42\"}]}"));
    }
  }

  /**
   * Test {@link CompactionBatchJobsWriterToS3#writeJobs(String, String, List)}.
   * <p>
   * Method under test: {@link CompactionBatchJobsWriterToS3#writeJobs(String, String, List)}
   */
  @Test
  @DisplayName("Test writeJobs(String, String, List)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void CompactionBatchJobsWriterToS3.writeJobs(String, String, List)"})
  void testWriteJobs2() throws SdkClientException, UnknownHostException {
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
      AmazonS3Client s3Client = mock(AmazonS3Client.class);
      when(s3Client.putObject(Mockito.<String>any(), Mockito.<String>any(), Mockito.<String>any()))
          .thenReturn(putObjectResult);
      CompactionBatchJobsWriterToS3 compactionBatchJobsWriterToS3 = new CompactionBatchJobsWriterToS3(s3Client);

      ArrayList<CompactionJob> compactionJobs = new ArrayList<>();
      Builder builderResult = CompactionJob.builder();
      CompactionJob buildResult = builderResult.inputFiles(new ArrayList<>())
          .jobId("42")
          .outputFile("Output File")
          .partitionId("42")
          .tableId("42")
          .build();
      compactionJobs.add(buildResult);
      Builder builderResult2 = CompactionJob.builder();
      CompactionJob buildResult2 = builderResult2.inputFiles(new ArrayList<>())
          .jobId("42")
          .outputFile("Output File")
          .partitionId("42")
          .tableId("42")
          .build();
      compactionJobs.add(buildResult2);

      // Act
      compactionBatchJobsWriterToS3.writeJobs("s3://bucket-name/object-key", "Key", compactionJobs);

      // Assert
      verify(s3Client).putObject(eq("s3://bucket-name/object-key"), eq("Key"), eq(
          "{\"jobs\":[{\"tableId\":\"42\",\"jobId\":\"42\",\"inputFiles\":[],\"outputFile\":\"Output File\",\"partitionId\":\"42\"},{\"tableId\":\"42\",\"jobId\":\"42\",\"inputFiles\":[],\"outputFile\":\"Output File\",\"partitionId\":\"42\"}]}"));
    }
  }

  /**
   * Test {@link CompactionBatchJobsWriterToS3#writeJobs(String, String, List)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then calls {@link AmazonS3Client#putObject(String, String, String)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompactionBatchJobsWriterToS3#writeJobs(String, String, List)}
   */
  @Test
  @DisplayName("Test writeJobs(String, String, List); when ArrayList(); then calls putObject(String, String, String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void CompactionBatchJobsWriterToS3.writeJobs(String, String, List)"})
  void testWriteJobs_whenArrayList_thenCallsPutObject() throws SdkClientException, UnknownHostException {
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
      AmazonS3Client s3Client = mock(AmazonS3Client.class);
      when(s3Client.putObject(Mockito.<String>any(), Mockito.<String>any(), Mockito.<String>any()))
          .thenReturn(putObjectResult);
      CompactionBatchJobsWriterToS3 compactionBatchJobsWriterToS3 = new CompactionBatchJobsWriterToS3(s3Client);

      // Act
      compactionBatchJobsWriterToS3.writeJobs("s3://bucket-name/object-key", "Key", new ArrayList<>());

      // Assert
      verify(s3Client).putObject(eq("s3://bucket-name/object-key"), eq("Key"), eq("{\"jobs\":[]}"));
    }
  }
}
