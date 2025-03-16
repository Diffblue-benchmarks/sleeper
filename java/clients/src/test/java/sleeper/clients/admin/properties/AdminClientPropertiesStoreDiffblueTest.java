package sleeper.clients.admin.properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClient;
import com.amazonaws.services.s3.AmazonS3Client;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import sleeper.clients.admin.properties.AdminClientPropertiesStore.ConfigStoreException;
import sleeper.clients.admin.properties.AdminClientPropertiesStore.CouldNotLoadInstanceProperties;
import sleeper.clients.admin.properties.AdminClientPropertiesStore.CouldNotLoadProperties;
import sleeper.clients.admin.properties.AdminClientPropertiesStore.CouldNotLoadTableProperties;
import sleeper.clients.admin.properties.AdminClientPropertiesStore.CouldNotSaveInstanceProperties;
import sleeper.clients.admin.properties.AdminClientPropertiesStore.CouldNotSaveProperties;
import sleeper.clients.admin.properties.AdminClientPropertiesStore.CouldNotSaveTableProperties;
import sleeper.clients.deploy.UploadDockerImages;
import sleeper.clients.deploy.UploadDockerImages.Builder;
import sleeper.clients.deploy.UploadDockerImages.CopyFile;
import sleeper.clients.util.InMemoryEcrRepositories;
import sleeper.clients.util.cdk.InvokeCdkForInstance;
import sleeper.core.properties.SleeperPropertyIndex;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.instance.InstanceProperty;

class AdminClientPropertiesStoreDiffblueTest {
  /**
   * Test ConfigStoreException {@link ConfigStoreException#ConfigStoreException(String, Throwable)}.
   * <p>
   * Method under test: {@link ConfigStoreException#ConfigStoreException(String, Throwable)}
   */
  @Test
  @DisplayName("Test ConfigStoreException new ConfigStoreException(String, Throwable)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ConfigStoreException.<init>(String, Throwable)"})
  void testConfigStoreExceptionNewConfigStoreException() {
    // Arrange
    Throwable cause = new Throwable();

    // Act
    ConfigStoreException actualConfigStoreException = new ConfigStoreException("An error occurred", cause);

    // Assert
    assertEquals("An error occurred", actualConfigStoreException.getMessage());
    assertEquals(0, actualConfigStoreException.getSuppressed().length);
    assertSame(cause, actualConfigStoreException.getCause());
  }

  /**
   * Test CouldNotLoadInstanceProperties {@link CouldNotLoadInstanceProperties#CouldNotLoadInstanceProperties(String, Throwable)}.
   * <p>
   * Method under test: {@link CouldNotLoadInstanceProperties#CouldNotLoadInstanceProperties(String, Throwable)}
   */
  @Test
  @DisplayName("Test CouldNotLoadInstanceProperties new CouldNotLoadInstanceProperties(String, Throwable)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void CouldNotLoadInstanceProperties.<init>(String, Throwable)"})
  void testCouldNotLoadInstancePropertiesNewCouldNotLoadInstanceProperties() {
    // Arrange
    Throwable cause = new Throwable();

    // Act
    CouldNotLoadInstanceProperties actualCouldNotLoadInstanceProperties = new CouldNotLoadInstanceProperties("42",
        cause);

    // Assert
    assertEquals("Could not load properties for instance 42",
        actualCouldNotLoadInstanceProperties.getLocalizedMessage());
    assertEquals("Could not load properties for instance 42", actualCouldNotLoadInstanceProperties.getMessage());
    assertEquals(0, actualCouldNotLoadInstanceProperties.getSuppressed().length);
    assertSame(cause, actualCouldNotLoadInstanceProperties.getCause());
  }

  /**
   * Test CouldNotLoadProperties {@link CouldNotLoadProperties#CouldNotLoadProperties(String, Throwable)}.
   * <p>
   * Method under test: {@link CouldNotLoadProperties#CouldNotLoadProperties(String, Throwable)}
   */
  @Test
  @DisplayName("Test CouldNotLoadProperties new CouldNotLoadProperties(String, Throwable)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void CouldNotLoadProperties.<init>(String, Throwable)"})
  void testCouldNotLoadPropertiesNewCouldNotLoadProperties() {
    // Arrange
    Throwable cause = new Throwable();

    // Act
    CouldNotLoadProperties actualCouldNotLoadProperties = new CouldNotLoadProperties("Not all who wander are lost",
        cause);

    // Assert
    assertEquals("Not all who wander are lost", actualCouldNotLoadProperties.getMessage());
    assertEquals(0, actualCouldNotLoadProperties.getSuppressed().length);
    assertSame(cause, actualCouldNotLoadProperties.getCause());
  }

  /**
   * Test CouldNotLoadTableProperties {@link CouldNotLoadTableProperties#CouldNotLoadTableProperties(String, String, Throwable)}.
   * <p>
   * Method under test: {@link CouldNotLoadTableProperties#CouldNotLoadTableProperties(String, String, Throwable)}
   */
  @Test
  @DisplayName("Test CouldNotLoadTableProperties new CouldNotLoadTableProperties(String, String, Throwable)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void CouldNotLoadTableProperties.<init>(String, String, Throwable)"})
  void testCouldNotLoadTablePropertiesNewCouldNotLoadTableProperties() {
    // Arrange
    Throwable cause = new Throwable();

    // Act
    CouldNotLoadTableProperties actualCouldNotLoadTableProperties = new CouldNotLoadTableProperties("42", "Table Name",
        cause);

    // Assert
    assertEquals("Could not load properties for table Table Name in instance 42",
        actualCouldNotLoadTableProperties.getLocalizedMessage());
    assertEquals("Could not load properties for table Table Name in instance 42",
        actualCouldNotLoadTableProperties.getMessage());
    assertEquals(0, actualCouldNotLoadTableProperties.getSuppressed().length);
    assertSame(cause, actualCouldNotLoadTableProperties.getCause());
  }

  /**
   * Test CouldNotSaveInstanceProperties {@link CouldNotSaveInstanceProperties#CouldNotSaveInstanceProperties(String, Throwable)}.
   * <p>
   * Method under test: {@link CouldNotSaveInstanceProperties#CouldNotSaveInstanceProperties(String, Throwable)}
   */
  @Test
  @DisplayName("Test CouldNotSaveInstanceProperties new CouldNotSaveInstanceProperties(String, Throwable)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void CouldNotSaveInstanceProperties.<init>(String, Throwable)"})
  void testCouldNotSaveInstancePropertiesNewCouldNotSaveInstanceProperties() {
    // Arrange
    Throwable cause = new Throwable();

    // Act
    CouldNotSaveInstanceProperties actualCouldNotSaveInstanceProperties = new CouldNotSaveInstanceProperties("42",
        cause);

    // Assert
    assertEquals("Could not save properties for instance 42",
        actualCouldNotSaveInstanceProperties.getLocalizedMessage());
    assertEquals("Could not save properties for instance 42", actualCouldNotSaveInstanceProperties.getMessage());
    assertEquals(0, actualCouldNotSaveInstanceProperties.getSuppressed().length);
    assertSame(cause, actualCouldNotSaveInstanceProperties.getCause());
  }

  /**
   * Test CouldNotSaveProperties {@link CouldNotSaveProperties#CouldNotSaveProperties(String, Throwable)}.
   * <p>
   * Method under test: {@link CouldNotSaveProperties#CouldNotSaveProperties(String, Throwable)}
   */
  @Test
  @DisplayName("Test CouldNotSaveProperties new CouldNotSaveProperties(String, Throwable)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void CouldNotSaveProperties.<init>(String, Throwable)"})
  void testCouldNotSavePropertiesNewCouldNotSaveProperties() {
    // Arrange
    Throwable cause = new Throwable();

    // Act
    CouldNotSaveProperties actualCouldNotSaveProperties = new CouldNotSaveProperties("Not all who wander are lost",
        cause);

    // Assert
    assertEquals("Not all who wander are lost", actualCouldNotSaveProperties.getMessage());
    assertEquals(0, actualCouldNotSaveProperties.getSuppressed().length);
    assertSame(cause, actualCouldNotSaveProperties.getCause());
  }

  /**
   * Test CouldNotSaveTableProperties {@link CouldNotSaveTableProperties#CouldNotSaveTableProperties(String, String, Throwable)}.
   * <p>
   * Method under test: {@link CouldNotSaveTableProperties#CouldNotSaveTableProperties(String, String, Throwable)}
   */
  @Test
  @DisplayName("Test CouldNotSaveTableProperties new CouldNotSaveTableProperties(String, String, Throwable)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void CouldNotSaveTableProperties.<init>(String, String, Throwable)"})
  void testCouldNotSaveTablePropertiesNewCouldNotSaveTableProperties() {
    // Arrange
    Throwable cause = new Throwable();

    // Act
    CouldNotSaveTableProperties actualCouldNotSaveTableProperties = new CouldNotSaveTableProperties("42", "Table Name",
        cause);

    // Assert
    assertEquals("Could not save properties for table Table Name in instance 42",
        actualCouldNotSaveTableProperties.getLocalizedMessage());
    assertEquals("Could not save properties for table Table Name in instance 42",
        actualCouldNotSaveTableProperties.getMessage());
    assertEquals(0, actualCouldNotSaveTableProperties.getSuppressed().length);
    assertSame(cause, actualCouldNotSaveTableProperties.getCause());
  }

  /**
   * Test {@link AdminClientPropertiesStore#loadInstanceProperties(String)}.
   * <ul>
   *   <li>Then return toMap size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link AdminClientPropertiesStore#loadInstanceProperties(String)}
   */
  @Test
  @DisplayName("Test loadInstanceProperties(String); then return toMap size is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"InstanceProperties AdminClientPropertiesStore.loadInstanceProperties(String)"})
  void testLoadInstanceProperties_thenReturnToMapSizeIsOne() throws SdkClientException, UnknownHostException {
    try (MockedStatic<InetAddress> mockInetAddress = mockStatic(InetAddress.class)) {

      // Arrange
      mockInetAddress.when(() -> InetAddress.getByAddress(Mockito.<byte[]>any())).thenReturn(mock(InetAddress.class));
      mockInetAddress.when(() -> InetAddress.getByName(Mockito.<String>any())).thenReturn(mock(InetAddress.class));
      mockInetAddress.when(() -> InetAddress.getAllByName(Mockito.<String>any()))
          .thenReturn(new InetAddress[]{mock(InetAddress.class)});
      AmazonS3Client s3 = mock(AmazonS3Client.class);
      when(s3.getObjectAsString(Mockito.<String>any(), Mockito.<String>any())).thenReturn("Object As String");
      AmazonDynamoDBAsyncClient dynamoDB = new AmazonDynamoDBAsyncClient();
      InvokeCdkForInstance cdk = InvokeCdkForInstance.builder()
          .jarsDirectory(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
          .propertiesFile(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
          .version("1.0.2")
          .build();
      Path generatedDirectory = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
      Builder copyFileResult = UploadDockerImages.builder()
          .baseDockerDirectory(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
          .copyFile(mock(CopyFile.class));
      UploadDockerImages uploadDockerImages = copyFileResult.ecrClient(new InMemoryEcrRepositories())
          .jarsDirectory(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
          .build();

      // Act
      InstanceProperties actualLoadInstancePropertiesResult = (new AdminClientPropertiesStore(s3, dynamoDB, cdk,
          generatedDirectory, uploadDockerImages)).loadInstanceProperties("42");

      // Assert
      verify(s3).getObjectAsString(eq("sleeper-42-config"), eq("instance.properties"));
      Map<String, String> toMapResult = actualLoadInstancePropertiesResult.toMap();
      assertEquals(1, toMapResult.size());
      assertEquals("As String", toMapResult.get("Object"));
      Properties properties = actualLoadInstancePropertiesResult.getProperties();
      assertEquals(1, properties.size());
      assertEquals("As String", properties.get("Object"));
      Stream<Entry<String, String>> unknownProperties = actualLoadInstancePropertiesResult.getUnknownProperties();
      assertEquals(1, unknownProperties.limit(5).collect(Collectors.toList()).size());
      SleeperPropertyIndex<InstanceProperty> propertiesIndex = actualLoadInstancePropertiesResult.getPropertiesIndex();
      assertEquals(131, propertiesIndex.getCdkDefined().size());
      assertEquals(325, propertiesIndex.getUserDefined().size());
      assertEquals(456, propertiesIndex.getAll().size());
      assertTrue(actualLoadInstancePropertiesResult.getTags().isEmpty());
      assertTrue(actualLoadInstancePropertiesResult.getTagsProperties().isEmpty());
    }
  }
}
