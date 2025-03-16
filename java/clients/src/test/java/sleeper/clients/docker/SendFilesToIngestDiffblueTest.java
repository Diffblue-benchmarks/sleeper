package sleeper.clients.docker;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.instance.InstanceProperty;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest.Builder;

class SendFilesToIngestDiffblueTest {
  /**
   * Test {@link SendFilesToIngest#uploadFilesAndSendJob(InstanceProperties, String, List, AmazonS3, SqsClient)}.
   * <ul>
   *   <li>Given {@code null}.</li>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then calls {@link SqsClient#sendMessage(Consumer)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SendFilesToIngest#uploadFilesAndSendJob(InstanceProperties, String, List, AmazonS3, SqsClient)}
   */
  @Test
  @DisplayName("Test uploadFilesAndSendJob(InstanceProperties, String, List, AmazonS3, SqsClient); given 'null'; when ArrayList(); then calls sendMessage(Consumer)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void SendFilesToIngest.uploadFilesAndSendJob(InstanceProperties, String, List, AmazonS3, SqsClient)"})
  void testUploadFilesAndSendJob_givenNull_whenArrayList_thenCallsSendMessage()
      throws AwsServiceException, SdkClientException {
    // Arrange
    InstanceProperties properties = new InstanceProperties();
    ArrayList<Path> filePaths = new ArrayList<>();
    AmazonS3Client s3Client = new AmazonS3Client();
    SqsClient sqsClient = mock(SqsClient.class);
    when(sqsClient.sendMessage(Mockito.<Consumer<Builder>>any())).thenReturn(null);

    // Act
    SendFilesToIngest.uploadFilesAndSendJob(properties, "Table Name", filePaths, s3Client, sqsClient);

    // Assert
    verify(sqsClient).sendMessage(isA(Consumer.class));
  }

  /**
   * Test {@link SendFilesToIngest#uploadFilesAndSendJob(InstanceProperties, String, List, AmazonS3, SqsClient)}.
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SendFilesToIngest#uploadFilesAndSendJob(InstanceProperties, String, List, AmazonS3, SqsClient)}
   */
  @Test
  @DisplayName("Test uploadFilesAndSendJob(InstanceProperties, String, List, AmazonS3, SqsClient); then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void SendFilesToIngest.uploadFilesAndSendJob(InstanceProperties, String, List, AmazonS3, SqsClient)"})
  void testUploadFilesAndSendJob_thenThrowIllegalArgumentException() throws AwsServiceException, SdkClientException {
    // Arrange
    InstanceProperties properties = new InstanceProperties();
    ArrayList<Path> filePaths = new ArrayList<>();
    AmazonS3Client s3Client = new AmazonS3Client();
    SqsClient sqsClient = mock(SqsClient.class);
    when(sqsClient.sendMessage(Mockito.<Consumer<Builder>>any())).thenThrow(new IllegalArgumentException("foo"));

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> SendFilesToIngest.uploadFilesAndSendJob(properties, "Table Name", filePaths, s3Client, sqsClient));
    verify(sqsClient).sendMessage(isA(Consumer.class));
  }

  /**
   * Test {@link SendFilesToIngest#uploadFiles(InstanceProperties, List, AmazonS3)}.
   * <ul>
   *   <li>Then calls {@link AmazonS3Client#putObject(String, String, File)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SendFilesToIngest#uploadFiles(InstanceProperties, List, AmazonS3)}
   */
  @Test
  @DisplayName("Test uploadFiles(InstanceProperties, List, AmazonS3); then calls putObject(String, String, File)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SendFilesToIngest.uploadFiles(InstanceProperties, List, AmazonS3)"})
  void testUploadFiles_thenCallsPutObject() throws com.amazonaws.SdkClientException {
    // Arrange
    InstanceProperties properties = mock(InstanceProperties.class);
    when(properties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");

    ArrayList<Path> filePaths = new ArrayList<>();
    filePaths.add(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));

    PutObjectResult putObjectResult = new PutObjectResult();
    putObjectResult.setContentMd5("MjdjN2NmNDAwMjI5MTAzZTAwYzZkODgzMDAyOWUyOWI=");
    AmazonS3Client s3Client = mock(AmazonS3Client.class);
    when(s3Client.putObject(Mockito.<String>any(), Mockito.<String>any(), Mockito.<File>any()))
        .thenReturn(putObjectResult);

    // Act
    SendFilesToIngest.uploadFiles(properties, filePaths, s3Client);

    // Assert
    verify(s3Client).putObject(eq("Get"), eq("ingest/test.txt"), isA(File.class));
    verify(properties).get(isA(InstanceProperty.class));
  }

  /**
   * Test {@link SendFilesToIngest#uploadFiles(InstanceProperties, List, AmazonS3)}.
   * <ul>
   *   <li>Then calls {@link AmazonS3Client#putObject(String, String, File)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SendFilesToIngest#uploadFiles(InstanceProperties, List, AmazonS3)}
   */
  @Test
  @DisplayName("Test uploadFiles(InstanceProperties, List, AmazonS3); then calls putObject(String, String, File)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SendFilesToIngest.uploadFiles(InstanceProperties, List, AmazonS3)"})
  void testUploadFiles_thenCallsPutObject2() throws com.amazonaws.SdkClientException {
    // Arrange
    InstanceProperties properties = mock(InstanceProperties.class);
    when(properties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");

    ArrayList<Path> filePaths = new ArrayList<>();
    filePaths.add(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));
    filePaths.add(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));

    PutObjectResult putObjectResult = new PutObjectResult();
    putObjectResult.setContentMd5("MjdjN2NmNDAwMjI5MTAzZTAwYzZkODgzMDAyOWUyOWI=");
    AmazonS3Client s3Client = mock(AmazonS3Client.class);
    when(s3Client.putObject(Mockito.<String>any(), Mockito.<String>any(), Mockito.<File>any()))
        .thenReturn(putObjectResult);

    // Act
    SendFilesToIngest.uploadFiles(properties, filePaths, s3Client);

    // Assert
    verify(s3Client, atLeast(1)).putObject(eq("Get"), eq("ingest/test.txt"), isA(File.class));
    verify(properties, atLeast(1)).get(isA(InstanceProperty.class));
  }

  /**
   * Test {@link SendFilesToIngest#sendJobForFiles(InstanceProperties, String, List, SqsClient)}.
   * <ul>
   *   <li>Given {@code null}.</li>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then calls {@link SqsClient#sendMessage(Consumer)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SendFilesToIngest#sendJobForFiles(InstanceProperties, String, List, SqsClient)}
   */
  @Test
  @DisplayName("Test sendJobForFiles(InstanceProperties, String, List, SqsClient); given 'null'; when ArrayList(); then calls sendMessage(Consumer)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SendFilesToIngest.sendJobForFiles(InstanceProperties, String, List, SqsClient)"})
  void testSendJobForFiles_givenNull_whenArrayList_thenCallsSendMessage()
      throws AwsServiceException, SdkClientException {
    // Arrange
    InstanceProperties properties = new InstanceProperties();
    ArrayList<Path> filePaths = new ArrayList<>();
    SqsClient sqsClient = mock(SqsClient.class);
    when(sqsClient.sendMessage(Mockito.<Consumer<Builder>>any())).thenReturn(null);

    // Act
    SendFilesToIngest.sendJobForFiles(properties, "Table Name", filePaths, sqsClient);

    // Assert
    verify(sqsClient).sendMessage(isA(Consumer.class));
  }

  /**
   * Test {@link SendFilesToIngest#sendJobForFiles(InstanceProperties, String, List, SqsClient)}.
   * <ul>
   *   <li>Given Property is {@code java.io.tmpdir} is array of {@link String} with {@code test.txt}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SendFilesToIngest#sendJobForFiles(InstanceProperties, String, List, SqsClient)}
   */
  @Test
  @DisplayName("Test sendJobForFiles(InstanceProperties, String, List, SqsClient); given Property is 'java.io.tmpdir' is array of String with 'test.txt'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SendFilesToIngest.sendJobForFiles(InstanceProperties, String, List, SqsClient)"})
  void testSendJobForFiles_givenPropertyIsJavaIoTmpdirIsArrayOfStringWithTestTxt()
      throws AwsServiceException, SdkClientException {
    // Arrange
    InstanceProperties properties = new InstanceProperties();

    ArrayList<Path> filePaths = new ArrayList<>();
    filePaths.add(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));
    SqsClient sqsClient = mock(SqsClient.class);
    when(sqsClient.sendMessage(Mockito.<Consumer<Builder>>any())).thenReturn(null);

    // Act
    SendFilesToIngest.sendJobForFiles(properties, "Table Name", filePaths, sqsClient);

    // Assert
    verify(sqsClient).sendMessage(isA(Consumer.class));
  }

  /**
   * Test {@link SendFilesToIngest#sendJobForFiles(InstanceProperties, String, List, SqsClient)}.
   * <ul>
   *   <li>Given Property is {@code java.io.tmpdir} is array of {@link String} with {@code test.txt}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SendFilesToIngest#sendJobForFiles(InstanceProperties, String, List, SqsClient)}
   */
  @Test
  @DisplayName("Test sendJobForFiles(InstanceProperties, String, List, SqsClient); given Property is 'java.io.tmpdir' is array of String with 'test.txt'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SendFilesToIngest.sendJobForFiles(InstanceProperties, String, List, SqsClient)"})
  void testSendJobForFiles_givenPropertyIsJavaIoTmpdirIsArrayOfStringWithTestTxt2()
      throws AwsServiceException, SdkClientException {
    // Arrange
    InstanceProperties properties = new InstanceProperties();

    ArrayList<Path> filePaths = new ArrayList<>();
    filePaths.add(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));
    filePaths.add(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));
    SqsClient sqsClient = mock(SqsClient.class);
    when(sqsClient.sendMessage(Mockito.<Consumer<Builder>>any())).thenReturn(null);

    // Act
    SendFilesToIngest.sendJobForFiles(properties, "Table Name", filePaths, sqsClient);

    // Assert
    verify(sqsClient).sendMessage(isA(Consumer.class));
  }

  /**
   * Test {@link SendFilesToIngest#sendJobForFiles(InstanceProperties, String, List, SqsClient)}.
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SendFilesToIngest#sendJobForFiles(InstanceProperties, String, List, SqsClient)}
   */
  @Test
  @DisplayName("Test sendJobForFiles(InstanceProperties, String, List, SqsClient); then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SendFilesToIngest.sendJobForFiles(InstanceProperties, String, List, SqsClient)"})
  void testSendJobForFiles_thenThrowIllegalArgumentException() throws AwsServiceException, SdkClientException {
    // Arrange
    InstanceProperties properties = new InstanceProperties();
    ArrayList<Path> filePaths = new ArrayList<>();
    SqsClient sqsClient = mock(SqsClient.class);
    when(sqsClient.sendMessage(Mockito.<Consumer<Builder>>any())).thenThrow(new IllegalArgumentException("foo"));

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> SendFilesToIngest.sendJobForFiles(properties, "Table Name", filePaths, sqsClient));
    verify(sqsClient).sendMessage(isA(Consumer.class));
  }
}
