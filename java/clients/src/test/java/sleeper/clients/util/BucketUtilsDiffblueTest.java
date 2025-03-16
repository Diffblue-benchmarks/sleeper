package sleeper.clients.util;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ListObjectsV2Request;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.HeadBucketRequest;
import software.amazon.awssdk.services.s3.model.HeadBucketRequest.Builder;

class BucketUtilsDiffblueTest {
  /**
   * Test {@link BucketUtils#doesBucketExist(S3Client, String)}.
   * <ul>
   *   <li>Given {@code null}.</li>
   *   <li>When {@link S3Client} {@link S3Client#headBucket(Consumer)} return {@code null}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BucketUtils#doesBucketExist(S3Client, String)}
   */
  @Test
  @DisplayName("Test doesBucketExist(S3Client, String); given 'null'; when S3Client headBucket(Consumer) return 'null'; then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean BucketUtils.doesBucketExist(S3Client, String)"})
  void testDoesBucketExist_givenNull_whenS3ClientHeadBucketReturnNull_thenReturnTrue()
      throws AwsServiceException, software.amazon.awssdk.core.exception.SdkClientException {
    // Arrange
    S3Client s3 = mock(S3Client.class);
    when(s3.headBucket(Mockito.<Consumer<Builder>>any())).thenReturn(null);

    // Act
    boolean actualDoesBucketExistResult = BucketUtils.doesBucketExist(s3, "bucket-name");

    // Assert
    verify(s3).headBucket(isA(Consumer.class));
    assertTrue(actualDoesBucketExistResult);
  }

  /**
   * Test {@link BucketUtils#deleteAllObjectsInBucketWithPrefix(AmazonS3, String, String)}.
   * <ul>
   *   <li>Then calls {@link AmazonS3Client#listObjectsV2(ListObjectsV2Request)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BucketUtils#deleteAllObjectsInBucketWithPrefix(AmazonS3, String, String)}
   */
  @Test
  @DisplayName("Test deleteAllObjectsInBucketWithPrefix(AmazonS3, String, String); then calls listObjectsV2(ListObjectsV2Request)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void BucketUtils.deleteAllObjectsInBucketWithPrefix(AmazonS3, String, String)"})
  void testDeleteAllObjectsInBucketWithPrefix_thenCallsListObjectsV2() throws SdkClientException, UnknownHostException {
    try (MockedStatic<InetAddress> mockInetAddress = mockStatic(InetAddress.class)) {

      // Arrange
      mockInetAddress.when(() -> InetAddress.getByAddress(Mockito.<byte[]>any())).thenReturn(mock(InetAddress.class));
      mockInetAddress.when(() -> InetAddress.getByName(Mockito.<String>any())).thenReturn(mock(InetAddress.class));
      mockInetAddress.when(() -> InetAddress.getAllByName(Mockito.<String>any()))
          .thenReturn(new InetAddress[]{mock(InetAddress.class)});
      ListObjectsV2Result listObjectsV2Result = mock(ListObjectsV2Result.class);
      when(listObjectsV2Result.isTruncated()).thenReturn(false);
      when(listObjectsV2Result.getNextContinuationToken()).thenReturn("ABC123");
      when(listObjectsV2Result.getObjectSummaries()).thenReturn(new ArrayList<>());
      doNothing().when(listObjectsV2Result).setBucketName(Mockito.<String>any());
      doNothing().when(listObjectsV2Result).setCommonPrefixes(Mockito.<List<String>>any());
      doNothing().when(listObjectsV2Result).setContinuationToken(Mockito.<String>any());
      doNothing().when(listObjectsV2Result).setDelimiter(Mockito.<String>any());
      doNothing().when(listObjectsV2Result).setEncodingType(Mockito.<String>any());
      doNothing().when(listObjectsV2Result).setKeyCount(anyInt());
      doNothing().when(listObjectsV2Result).setMaxKeys(anyInt());
      doNothing().when(listObjectsV2Result).setNextContinuationToken(Mockito.<String>any());
      doNothing().when(listObjectsV2Result).setPrefix(Mockito.<String>any());
      doNothing().when(listObjectsV2Result).setRequesterCharged(anyBoolean());
      doNothing().when(listObjectsV2Result).setStartAfter(Mockito.<String>any());
      doNothing().when(listObjectsV2Result).setTruncated(anyBoolean());
      listObjectsV2Result.setBucketName("bucket-name");
      listObjectsV2Result.setCommonPrefixes(new ArrayList<>());
      listObjectsV2Result.setContinuationToken("ABC123");
      listObjectsV2Result.setDelimiter("Delimiter");
      listObjectsV2Result.setEncodingType("UTF-8");
      listObjectsV2Result.setKeyCount(3);
      listObjectsV2Result.setMaxKeys(3);
      listObjectsV2Result.setNextContinuationToken("ABC123");
      listObjectsV2Result.setPrefix("Prefix");
      listObjectsV2Result.setRequesterCharged(true);
      listObjectsV2Result.setStartAfter("Start After");
      listObjectsV2Result.setTruncated(true);
      AmazonS3Client s3Client = mock(AmazonS3Client.class);
      when(s3Client.listObjectsV2(Mockito.<ListObjectsV2Request>any())).thenReturn(listObjectsV2Result);

      // Act
      BucketUtils.deleteAllObjectsInBucketWithPrefix(s3Client, "bucket-name", "Prefix");

      // Assert
      verify(s3Client).listObjectsV2(isA(ListObjectsV2Request.class));
      verify(listObjectsV2Result).getNextContinuationToken();
      verify(listObjectsV2Result).getObjectSummaries();
      verify(listObjectsV2Result).isTruncated();
      verify(listObjectsV2Result).setBucketName(eq("bucket-name"));
      verify(listObjectsV2Result).setCommonPrefixes(isA(List.class));
      verify(listObjectsV2Result).setContinuationToken(eq("ABC123"));
      verify(listObjectsV2Result).setDelimiter(eq("Delimiter"));
      verify(listObjectsV2Result).setEncodingType(eq("UTF-8"));
      verify(listObjectsV2Result).setKeyCount(eq(3));
      verify(listObjectsV2Result).setMaxKeys(eq(3));
      verify(listObjectsV2Result).setNextContinuationToken(eq("ABC123"));
      verify(listObjectsV2Result).setPrefix(eq("Prefix"));
      verify(listObjectsV2Result).setRequesterCharged(eq(true));
      verify(listObjectsV2Result).setStartAfter(eq("Start After"));
      verify(listObjectsV2Result).setTruncated(eq(true));
    }
  }
}
