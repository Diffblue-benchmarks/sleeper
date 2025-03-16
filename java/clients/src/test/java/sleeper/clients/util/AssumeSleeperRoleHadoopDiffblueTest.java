package sleeper.clients.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.yarn.conf.YarnConfiguration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class AssumeSleeperRoleHadoopDiffblueTest {
  /**
   * Test {@link AssumeSleeperRoleHadoop#setS3ACredentials(Configuration)}.
   * <p>
   * Method under test: {@link AssumeSleeperRoleHadoop#setS3ACredentials(Configuration)}
   */
  @Test
  @DisplayName("Test setS3ACredentials(Configuration)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Configuration AssumeSleeperRoleHadoop.setS3ACredentials(Configuration)"})
  void testSetS3ACredentials() {
    // Arrange
    AssumeSleeperRoleHadoop assumeSleeperRoleHadoop = new AssumeSleeperRoleHadoop("Role Arn", "Role Session Name");

    Configuration configuration = new Configuration();
    configuration.addResource("fs.s3a.aws.credentials.provider", true);

    // Act and Assert
    assertSame(configuration, assumeSleeperRoleHadoop.setS3ACredentials(configuration));
  }

  /**
   * Test {@link AssumeSleeperRoleHadoop#setS3ACredentials(Configuration)}.
   * <ul>
   *   <li>Given {@link Configuration#Configuration()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AssumeSleeperRoleHadoop#setS3ACredentials(Configuration)}
   */
  @Test
  @DisplayName("Test setS3ACredentials(Configuration); given Configuration()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Configuration AssumeSleeperRoleHadoop.setS3ACredentials(Configuration)"})
  void testSetS3ACredentials_givenConfiguration() {
    // Arrange
    AssumeSleeperRoleHadoop assumeSleeperRoleHadoop = new AssumeSleeperRoleHadoop("Role Arn", "Role Session Name");

    Configuration configuration = new Configuration();
    configuration.addResource(new Configuration());
    configuration.addResource("fs.s3a.aws.credentials.provider");

    // Act and Assert
    assertSame(configuration, assumeSleeperRoleHadoop.setS3ACredentials(configuration));
  }

  /**
   * Test {@link AssumeSleeperRoleHadoop#setS3ACredentials(Configuration)}.
   * <ul>
   *   <li>Given {@link Path#Path(String)} with pathString is {@code fs.s3a.aws.credentials.provider}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AssumeSleeperRoleHadoop#setS3ACredentials(Configuration)}
   */
  @Test
  @DisplayName("Test setS3ACredentials(Configuration); given Path(String) with pathString is 'fs.s3a.aws.credentials.provider'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Configuration AssumeSleeperRoleHadoop.setS3ACredentials(Configuration)"})
  void testSetS3ACredentials_givenPathWithPathStringIsFsS3aAwsCredentialsProvider() throws IllegalArgumentException {
    // Arrange
    AssumeSleeperRoleHadoop assumeSleeperRoleHadoop = new AssumeSleeperRoleHadoop("Role Arn", "Role Session Name");

    Configuration configuration = new Configuration();
    configuration.addResource(new Path("fs.s3a.aws.credentials.provider"));
    configuration.addResource("fs.s3a.aws.credentials.provider");

    // Act and Assert
    assertSame(configuration, assumeSleeperRoleHadoop.setS3ACredentials(configuration));
  }

  /**
   * Test {@link AssumeSleeperRoleHadoop#setS3ACredentials(Configuration)}.
   * <ul>
   *   <li>Then return {@link Configuration#Configuration()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AssumeSleeperRoleHadoop#setS3ACredentials(Configuration)}
   */
  @Test
  @DisplayName("Test setS3ACredentials(Configuration); then return Configuration()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Configuration AssumeSleeperRoleHadoop.setS3ACredentials(Configuration)"})
  void testSetS3ACredentials_thenReturnConfiguration() {
    // Arrange
    AssumeSleeperRoleHadoop assumeSleeperRoleHadoop = new AssumeSleeperRoleHadoop("Role Arn", "Role Session Name");

    Configuration configuration = new Configuration();
    configuration.addResource("fs.s3a.aws.credentials.provider");

    // Act and Assert
    assertSame(configuration, assumeSleeperRoleHadoop.setS3ACredentials(configuration));
  }

  /**
   * Test {@link AssumeSleeperRoleHadoop#setS3ACredentials(Configuration)}.
   * <ul>
   *   <li>Then return {@link YarnConfiguration}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AssumeSleeperRoleHadoop#setS3ACredentials(Configuration)}
   */
  @Test
  @DisplayName("Test setS3ACredentials(Configuration); then return YarnConfiguration")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Configuration AssumeSleeperRoleHadoop.setS3ACredentials(Configuration)"})
  void testSetS3ACredentials_thenReturnYarnConfiguration() {
    // Arrange
    AssumeSleeperRoleHadoop assumeSleeperRoleHadoop = new AssumeSleeperRoleHadoop("Role Arn", "Role Session Name");
    YarnConfiguration configuration = new YarnConfiguration(new Configuration());

    // Act
    Configuration actualSetS3ACredentialsResult = assumeSleeperRoleHadoop.setS3ACredentials(configuration);

    // Assert
    assertTrue(actualSetS3ACredentialsResult instanceof YarnConfiguration);
    assertEquals(954, configuration.size());
    assertEquals(954, actualSetS3ACredentialsResult.size());
    assertSame(configuration, actualSetS3ACredentialsResult);
  }

  /**
   * Test {@link AssumeSleeperRoleHadoop#setS3ACredentials(Configuration)}.
   * <ul>
   *   <li>When {@link Configuration#Configuration()}.</li>
   *   <li>Then return {@link Configuration#Configuration()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AssumeSleeperRoleHadoop#setS3ACredentials(Configuration)}
   */
  @Test
  @DisplayName("Test setS3ACredentials(Configuration); when Configuration(); then return Configuration()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Configuration AssumeSleeperRoleHadoop.setS3ACredentials(Configuration)"})
  void testSetS3ACredentials_whenConfiguration_thenReturnConfiguration() {
    // Arrange
    AssumeSleeperRoleHadoop assumeSleeperRoleHadoop = new AssumeSleeperRoleHadoop("Role Arn", "Role Session Name");
    Configuration configuration = new Configuration();

    // Act and Assert
    assertSame(configuration, assumeSleeperRoleHadoop.setS3ACredentials(configuration));
  }
}
