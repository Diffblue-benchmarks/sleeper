package sleeper.clients.docker.stack;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.clients.docker.stack.ConfigurationDockerStack.Builder;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.instance.InstanceProperty;

class ConfigurationDockerStackDiffblueTest {
  /**
   * Test Builder {@link Builder#build()}.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Builder#build()}
   *   <li>{@link Builder#instanceProperties(InstanceProperties)}
   *   <li>{@link Builder#s3Client(AmazonS3)}
   * </ul>
   */
  @Test
  @DisplayName("Test Builder build()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void Builder.<init>()", "ConfigurationDockerStack Builder.build()",
      "Builder Builder.instanceProperties(InstanceProperties)", "Builder Builder.s3Client(AmazonS3)"})
  void testBuilderBuild() {
    // Arrange
    Builder builderResult = ConfigurationDockerStack.builder();
    InstanceProperties instanceProperties = new InstanceProperties();
    Builder instancePropertiesResult = builderResult.instanceProperties(instanceProperties);

    // Act and Assert
    assertSame(instanceProperties,
        instancePropertiesResult.s3Client(new AmazonS3Client()).build().getInstanceProperties());
  }

  /**
   * Test {@link ConfigurationDockerStack#from(InstanceProperties, AmazonS3)}.
   * <p>
   * Method under test: {@link ConfigurationDockerStack#from(InstanceProperties, AmazonS3)}
   */
  @Test
  @DisplayName("Test from(InstanceProperties, AmazonS3)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"ConfigurationDockerStack ConfigurationDockerStack.from(InstanceProperties, AmazonS3)"})
  void testFrom() {
    // Arrange
    InstanceProperties instanceProperties = new InstanceProperties();

    // Act and Assert
    assertSame(instanceProperties,
        ConfigurationDockerStack.from(instanceProperties, new AmazonS3Client()).getInstanceProperties());
  }

  /**
   * Test {@link ConfigurationDockerStack#deploy()}.
   * <ul>
   *   <li>Then calls {@link AmazonS3Client#createBucket(String)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ConfigurationDockerStack#deploy()}
   */
  @Test
  @DisplayName("Test deploy(); then calls createBucket(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ConfigurationDockerStack.deploy()"})
  void testDeploy_thenCallsCreateBucket() throws SdkClientException {
    // Arrange
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    Builder instancePropertiesResult = ConfigurationDockerStack.builder().instanceProperties(instanceProperties);
    AmazonS3Client s3Client = mock(AmazonS3Client.class);
    when(s3Client.createBucket(Mockito.<String>any())).thenReturn(new Bucket("bucket-name"));
    ConfigurationDockerStack buildResult = instancePropertiesResult.s3Client(s3Client).build();

    // Act
    buildResult.deploy();

    // Assert
    verify(s3Client).createBucket(eq("Get"));
    verify(instanceProperties).get(isA(InstanceProperty.class));
  }

  /**
   * Test {@link ConfigurationDockerStack#getInstanceProperties()}.
   * <p>
   * Method under test: {@link ConfigurationDockerStack#getInstanceProperties()}
   */
  @Test
  @DisplayName("Test getInstanceProperties()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"InstanceProperties ConfigurationDockerStack.getInstanceProperties()"})
  void testGetInstanceProperties() {
    // Arrange
    Builder builderResult = ConfigurationDockerStack.builder();
    InstanceProperties instanceProperties = new InstanceProperties();
    Builder instancePropertiesResult = builderResult.instanceProperties(instanceProperties);
    ConfigurationDockerStack buildResult = instancePropertiesResult.s3Client(new AmazonS3Client()).build();

    // Act and Assert
    assertSame(instanceProperties, buildResult.getInstanceProperties());
  }
}
