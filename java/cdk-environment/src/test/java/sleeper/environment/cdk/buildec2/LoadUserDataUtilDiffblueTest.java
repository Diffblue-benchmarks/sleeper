package sleeper.environment.cdk.buildec2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.environment.cdk.config.AppContext;
import sleeper.environment.cdk.config.BooleanParameter;
import sleeper.environment.cdk.config.IntParameter;
import sleeper.environment.cdk.config.StringParameter;

class LoadUserDataUtilDiffblueTest {
  /**
   * Test {@link LoadUserDataUtil#writeFilesYaml(BuildEC2Parameters)}.
   * <ul>
   *   <li>Given {@link IllegalStateException#IllegalStateException(String)} with {@code foo}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LoadUserDataUtil#writeFilesYaml(BuildEC2Parameters)}
   */
  @Test
  @DisplayName("Test writeFilesYaml(BuildEC2Parameters); given IllegalStateException(String) with 'foo'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String LoadUserDataUtil.writeFilesYaml(BuildEC2Parameters)"})
  void testWriteFilesYaml_givenIllegalStateExceptionWithFoo() {
    // Arrange
    BuildEC2Parameters params = mock(BuildEC2Parameters.class);
    when(params.isNightlyTestEnabled()).thenThrow(new IllegalStateException("foo"));

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> LoadUserDataUtil.writeFilesYaml(params));
    verify(params).isNightlyTestEnabled();
  }

  /**
   * Test {@link LoadUserDataUtil#writeFilesYaml(BuildEC2Parameters)}.
   * <ul>
   *   <li>Given one.</li>
   *   <li>When {@link AppContext} {@link AppContext#get(BooleanParameter)} return {@code false}.</li>
   *   <li>Then calls {@link AppContext#get(BooleanParameter)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LoadUserDataUtil#writeFilesYaml(BuildEC2Parameters)}
   */
  @Test
  @DisplayName("Test writeFilesYaml(BuildEC2Parameters); given one; when AppContext get(BooleanParameter) return 'false'; then calls get(BooleanParameter)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String LoadUserDataUtil.writeFilesYaml(BuildEC2Parameters)"})
  void testWriteFilesYaml_givenOne_whenAppContextGetReturnFalse_thenCallsGet() {
    // Arrange
    AppContext context = mock(AppContext.class);
    when(context.get(Mockito.<BooleanParameter>any())).thenReturn(false);
    when(context.get(Mockito.<IntParameter>any())).thenReturn(1);
    when(context.get(Mockito.<StringParameter>any())).thenReturn("Get");
    BuildEC2Parameters params = BuildEC2Parameters.builder()
        .context(context)
        .testBucket("s3://bucket-name/object-key")
        .build();

    // Act
    String actualWriteFilesYamlResult = LoadUserDataUtil.writeFilesYaml(params);

    // Assert
    verify(context).get(isA(BooleanParameter.class));
    verify(context).get(isA(IntParameter.class));
    verify(context, atLeast(1)).get(Mockito.<StringParameter>any());
    assertEquals("", actualWriteFilesYamlResult);
  }

  /**
   * Test {@link LoadUserDataUtil#writeFilesYaml(BuildEC2Parameters)}.
   * <ul>
   *   <li>Then calls {@link BuildEC2Parameters#fillUserDataTemplate(String)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LoadUserDataUtil#writeFilesYaml(BuildEC2Parameters)}
   */
  @Test
  @DisplayName("Test writeFilesYaml(BuildEC2Parameters); then calls fillUserDataTemplate(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String LoadUserDataUtil.writeFilesYaml(BuildEC2Parameters)"})
  void testWriteFilesYaml_thenCallsFillUserDataTemplate() {
    // Arrange
    BuildEC2Parameters params = mock(BuildEC2Parameters.class);
    when(params.fillUserDataTemplate(Mockito.<String>any()))
        .thenThrow(new IllegalStateException("write-files-nightly-tests.yaml"));
    when(params.isNightlyTestEnabled()).thenReturn(true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> LoadUserDataUtil.writeFilesYaml(params));
    verify(params).fillUserDataTemplate(eq(
        "{\n  \"deployId\": \"${deployId}\",\n  \"vpc\": \"${vpc}\",\n  \"subnets\": \"${subnets}\",\n  \"resultsBucket\": \"${testBucket}\",\n  \"repoPath\": \"${fork}/${repository}\",\n  \"mergeToMainOnTestType\": {\n    \"performance\": false,\n    \"functional\": false\n  },\n  \"gitHubApp\": {\n    \"privateKeyFile\": \"/sleeper-builder/<my GitHub App private key uploaded to Sleeper builder>.pem\",\n    \"appId\": \"my GitHub App ID\",\n    \"installationId\": \"my GitHub App installation ID\"\n  }\n}\n"));
    verify(params).isNightlyTestEnabled();
  }

  /**
   * Test {@link LoadUserDataUtil#writeFilesYaml(BuildEC2Parameters)}.
   * <ul>
   *   <li>When {@link BuildEC2Parameters} {@link BuildEC2Parameters#isNightlyTestEnabled()} return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LoadUserDataUtil#writeFilesYaml(BuildEC2Parameters)}
   */
  @Test
  @DisplayName("Test writeFilesYaml(BuildEC2Parameters); when BuildEC2Parameters isNightlyTestEnabled() return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String LoadUserDataUtil.writeFilesYaml(BuildEC2Parameters)"})
  void testWriteFilesYaml_whenBuildEC2ParametersIsNightlyTestEnabledReturnFalse() {
    // Arrange
    BuildEC2Parameters params = mock(BuildEC2Parameters.class);
    when(params.isNightlyTestEnabled()).thenReturn(false);

    // Act
    String actualWriteFilesYamlResult = LoadUserDataUtil.writeFilesYaml(params);

    // Assert
    verify(params).isNightlyTestEnabled();
    assertEquals("", actualWriteFilesYamlResult);
  }

  /**
   * Test {@link LoadUserDataUtil#nightlyTestSettingsJson(BuildEC2Parameters)}.
   * <ul>
   *   <li>Given {@code false}.</li>
   *   <li>Then return a string.</li>
   * </ul>
   * <p>
   * Method under test: {@link LoadUserDataUtil#nightlyTestSettingsJson(BuildEC2Parameters)}
   */
  @Test
  @DisplayName("Test nightlyTestSettingsJson(BuildEC2Parameters); given 'false'; then return a string")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String LoadUserDataUtil.nightlyTestSettingsJson(BuildEC2Parameters)"})
  void testNightlyTestSettingsJson_givenFalse_thenReturnAString() {
    // Arrange
    AppContext context = mock(AppContext.class);
    when(context.get(Mockito.<BooleanParameter>any())).thenReturn(false);
    when(context.get(Mockito.<IntParameter>any())).thenReturn(1);
    when(context.get(Mockito.<StringParameter>any())).thenReturn("Get");
    BuildEC2Parameters params = BuildEC2Parameters.builder()
        .context(context)
        .testBucket("s3://bucket-name/object-key")
        .build();

    // Act
    String actualNightlyTestSettingsJsonResult = LoadUserDataUtil.nightlyTestSettingsJson(params);

    // Assert
    verify(context).get(isA(BooleanParameter.class));
    verify(context).get(isA(IntParameter.class));
    verify(context, atLeast(1)).get(Mockito.<StringParameter>any());
    assertEquals(
        "{\n" + "  \"deployId\": \"${deployId}\",\n" + "  \"vpc\": \"${vpc}\",\n" + "  \"subnets\": \"${subnets}\",\n"
            + "  \"resultsBucket\": \"${testBucket}\",\n" + "  \"repoPath\": \"Get/Get\",\n"
            + "  \"mergeToMainOnTestType\": {\n" + "    \"performance\": false,\n" + "    \"functional\": false\n"
            + "  },\n" + "  \"gitHubApp\": {\n"
            + "    \"privateKeyFile\": \"/sleeper-builder/<my GitHub App private key uploaded to Sleeper"
            + " builder>.pem\",\n" + "    \"appId\": \"my GitHub App ID\",\n"
            + "    \"installationId\": \"my GitHub App installation ID\"\n" + "  }\n" + "}\n",
        actualNightlyTestSettingsJsonResult);
  }

  /**
   * Test {@link LoadUserDataUtil#nightlyTestSettingsJson(BuildEC2Parameters)}.
   * <ul>
   *   <li>Then return {@code Fill User Data Template}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LoadUserDataUtil#nightlyTestSettingsJson(BuildEC2Parameters)}
   */
  @Test
  @DisplayName("Test nightlyTestSettingsJson(BuildEC2Parameters); then return 'Fill User Data Template'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String LoadUserDataUtil.nightlyTestSettingsJson(BuildEC2Parameters)"})
  void testNightlyTestSettingsJson_thenReturnFillUserDataTemplate() {
    // Arrange
    BuildEC2Parameters params = mock(BuildEC2Parameters.class);
    when(params.fillUserDataTemplate(Mockito.<String>any())).thenReturn("Fill User Data Template");

    // Act
    String actualNightlyTestSettingsJsonResult = LoadUserDataUtil.nightlyTestSettingsJson(params);

    // Assert
    verify(params).fillUserDataTemplate(eq(
        "{\n  \"deployId\": \"${deployId}\",\n  \"vpc\": \"${vpc}\",\n  \"subnets\": \"${subnets}\",\n  \"resultsBucket\": \"${testBucket}\",\n  \"repoPath\": \"${fork}/${repository}\",\n  \"mergeToMainOnTestType\": {\n    \"performance\": false,\n    \"functional\": false\n  },\n  \"gitHubApp\": {\n    \"privateKeyFile\": \"/sleeper-builder/<my GitHub App private key uploaded to Sleeper builder>.pem\",\n    \"appId\": \"my GitHub App ID\",\n    \"installationId\": \"my GitHub App installation ID\"\n  }\n}\n"));
    assertEquals("Fill User Data Template", actualNightlyTestSettingsJsonResult);
  }

  /**
   * Test {@link LoadUserDataUtil#nightlyTestSettingsJson(BuildEC2Parameters)}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LoadUserDataUtil#nightlyTestSettingsJson(BuildEC2Parameters)}
   */
  @Test
  @DisplayName("Test nightlyTestSettingsJson(BuildEC2Parameters); then throw IllegalStateException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String LoadUserDataUtil.nightlyTestSettingsJson(BuildEC2Parameters)"})
  void testNightlyTestSettingsJson_thenThrowIllegalStateException() {
    // Arrange
    BuildEC2Parameters params = mock(BuildEC2Parameters.class);
    when(params.fillUserDataTemplate(Mockito.<String>any()))
        .thenThrow(new IllegalStateException("nightlyTestSettings.json"));

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> LoadUserDataUtil.nightlyTestSettingsJson(params));
    verify(params).fillUserDataTemplate(eq(
        "{\n  \"deployId\": \"${deployId}\",\n  \"vpc\": \"${vpc}\",\n  \"subnets\": \"${subnets}\",\n  \"resultsBucket\": \"${testBucket}\",\n  \"repoPath\": \"${fork}/${repository}\",\n  \"mergeToMainOnTestType\": {\n    \"performance\": false,\n    \"functional\": false\n  },\n  \"gitHubApp\": {\n    \"privateKeyFile\": \"/sleeper-builder/<my GitHub App private key uploaded to Sleeper builder>.pem\",\n    \"appId\": \"my GitHub App ID\",\n    \"installationId\": \"my GitHub App installation ID\"\n  }\n}\n"));
  }
}
