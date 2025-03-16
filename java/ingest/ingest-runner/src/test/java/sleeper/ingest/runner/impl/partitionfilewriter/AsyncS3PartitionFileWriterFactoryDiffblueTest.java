package sleeper.ingest.runner.impl.partitionfilewriter;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.ingest.runner.impl.partitionfilewriter.AsyncS3PartitionFileWriterFactory.Builder;
import software.amazon.awssdk.services.s3.S3AsyncClient;
import software.amazon.awssdk.services.s3.internal.crossregion.S3CrossRegionAsyncClient;

class AsyncS3PartitionFileWriterFactoryDiffblueTest {
  /**
   * Test Builder {@link Builder#filePathPrefix(String)}.
   * <p>
   * Method under test: {@link Builder#filePathPrefix(String)}
   */
  @Test
  @DisplayName("Test Builder filePathPrefix(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.filePathPrefix(String)"})
  void testBuilderFilePathPrefix() {
    // Arrange
    Builder builderResult = AsyncS3PartitionFileWriterFactory.builder();

    // Act and Assert
    assertSame(builderResult, builderResult.filePathPrefix("/directory/foo.txt"));
  }

  /**
   * Test Builder {@link Builder#s3AsyncClientOrDefaultFromProperties(S3AsyncClient, InstanceProperties)}.
   * <ul>
   *   <li>Then return builder.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#s3AsyncClientOrDefaultFromProperties(S3AsyncClient, InstanceProperties)}
   */
  @Test
  @DisplayName("Test Builder s3AsyncClientOrDefaultFromProperties(S3AsyncClient, InstanceProperties); then return builder")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.s3AsyncClientOrDefaultFromProperties(S3AsyncClient, InstanceProperties)"})
  void testBuilderS3AsyncClientOrDefaultFromProperties_thenReturnBuilder() {
    // Arrange
    Builder builderResult = AsyncS3PartitionFileWriterFactory.builder();
    S3CrossRegionAsyncClient s3AsyncClient = new S3CrossRegionAsyncClient(mock(S3AsyncClient.class));

    // Act and Assert
    assertSame(builderResult,
        builderResult.s3AsyncClientOrDefaultFromProperties(s3AsyncClient, new InstanceProperties()));
  }
}
