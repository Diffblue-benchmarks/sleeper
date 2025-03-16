package sleeper.core.deploy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mockStatic;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

class GeneratePropertiesTemplatesDiffblueTest {
  /**
   * Test {@link GeneratePropertiesTemplates#fromRepositoryPath(Path)}.
   * <ul>
   *   <li>Then calls {@link Files#createDirectories(Path, FileAttribute[])}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GeneratePropertiesTemplates#fromRepositoryPath(Path)}
   */
  @Test
  @DisplayName("Test fromRepositoryPath(Path); then calls createDirectories(Path, FileAttribute[])")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void GeneratePropertiesTemplates.fromRepositoryPath(Path)"})
  void testFromRepositoryPath_thenCallsCreateDirectories() throws IOException {
    try (MockedStatic<Files> mockFiles = mockStatic(Files.class)) {

      // Arrange
      mockFiles.when(() -> Files.newBufferedWriter(Mockito.<Path>any(), isA(OpenOption[].class)))
          .thenReturn(new BufferedWriter(new StringWriter(), 1));
      mockFiles.when(() -> Files.createDirectories(Mockito.<Path>any(), isA(FileAttribute[].class)))
          .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));

      // Act
      GeneratePropertiesTemplates.fromRepositoryPath(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));

      // Assert
      mockFiles.verify(() -> Files.createDirectories(Mockito.<Path>any(), isA(FileAttribute[].class)), atLeast(1));
      mockFiles.verify(() -> Files.newBufferedWriter(Mockito.<Path>any(), isA(OpenOption[].class)), atLeast(1));
    }
  }

  /**
   * Test {@link GeneratePropertiesTemplates#fromRepositoryPath(Path)}.
   * <ul>
   *   <li>Then throw {@link IOException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GeneratePropertiesTemplates#fromRepositoryPath(Path)}
   */
  @Test
  @DisplayName("Test fromRepositoryPath(Path); then throw IOException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void GeneratePropertiesTemplates.fromRepositoryPath(Path)"})
  void testFromRepositoryPath_thenThrowIOException() throws IOException {
    try (MockedStatic<Files> mockFiles = mockStatic(Files.class)) {

      // Arrange
      mockFiles.when(() -> Files.newBufferedWriter(Mockito.<Path>any(), isA(OpenOption[].class)))
          .thenThrow(new IOException("example/full"));
      mockFiles.when(() -> Files.createDirectories(Mockito.<Path>any(), isA(FileAttribute[].class)))
          .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));

      // Act and Assert
      assertThrows(IOException.class, () -> GeneratePropertiesTemplates
          .fromRepositoryPath(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt")));
      mockFiles.verify(() -> Files.createDirectories(Mockito.<Path>any(), isA(FileAttribute[].class)));
      mockFiles.verify(() -> Files.newBufferedWriter(Mockito.<Path>any(), isA(OpenOption[].class)));
    }
  }

  /**
   * Test {@link GeneratePropertiesTemplates#writeExampleBasicInstanceProperties(Writer)}.
   * <ul>
   *   <li>Then {@link StringWriter#StringWriter()} toString is a string.</li>
   * </ul>
   * <p>
   * Method under test: {@link GeneratePropertiesTemplates#writeExampleBasicInstanceProperties(Writer)}
   */
  @Test
  @DisplayName("Test writeExampleBasicInstanceProperties(Writer); then StringWriter() toString is a string")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void GeneratePropertiesTemplates.writeExampleBasicInstanceProperties(Writer)"})
  void testWriteExampleBasicInstanceProperties_thenStringWriterToStringIsAString() {
    // Arrange
    StringWriter writer = new StringWriter();

    // Act
    GeneratePropertiesTemplates.writeExampleBasicInstanceProperties(writer);

    // Assert
    assertEquals("\n" + "## The following properties are commonly used throughout Sleeper.\n" + "\n"
        + "# A string to uniquely identify this deployment. This should be no longer than 20 chars. It" + " should be\n"
        + "# globally unique as it will be used to name AWS resources such as S3 buckets.\n"
        + "sleeper.id=basic-example\n" + "\n" + "# The S3 bucket containing the jar files of the Sleeper components.\n"
        + "sleeper.jars.bucket=the name of the bucket containing your jars, e.g. sleeper-<insert-unique-name-here>-jars"
        + "\n" + "\n"
        + "# A comma-separated list of the jars containing application specific iterator code. These jars are\n"
        + "# assumed to be in the bucket given by sleeper.jars.bucket, e.g. if that bucket contains two" + " iterator\n"
        + "# jars called iterator1.jar and iterator2.jar then the property should be\n"
        + "# 'sleeper.userjars=iterator1.jar,iterator2.jar'.\n" + "# sleeper.userjars=\n" + "\n"
        + "# Whether to keep the sleeper table bucket, Dynamo tables, query results bucket, etc., when the\n"
        + "# instance is destroyed.\n" + "sleeper.retain.infra.after.destroy=true\n" + "\n"
        + "# The optional stacks to deploy. Not case sensitive.\n"
        + "# Valid values: [IngestStack, IngestBatcherStack, EmrServerlessBulkImportStack, EmrBulkImportStack," + "\n"
        + "# PersistentEmrBulkImportStack, EksBulkImportStack, EmrStudioStack, QueryStack, WebSocketQueryStack," + "\n"
        + "# AthenaStack, KeepLambdaWarmStack, CompactionStack, GarbageCollectorStack, PartitionSplittingStack," + "\n"
        + "# DashboardStack, TableMetricsStack]\n"
        + "sleeper.optional.stacks=IngestStack,IngestBatcherStack,EmrServerlessBulkImportStack,EmrStudioStack"
        + ",QueryStack,AthenaStack,CompactionStack,GarbageCollectorStack,PartitionSplittingStack,DashboardStack"
        + ",TableMetricsStack\n" + "\n"
        + "# The AWS account number. This is the AWS account that the instance will be deployed to.\n"
        + "sleeper.account=1234567890\n" + "\n" + "# The AWS region to deploy to.\n" + "sleeper.region=eu-west-2\n"
        + "\n" + "# The id of the VPC to deploy to.\n" + "sleeper.vpc=1234567890\n" + "\n"
        + "# A comma separated list of subnets to deploy to. ECS tasks will be run across multiple" + " subnets. EMR\n"
        + "# clusters will be deployed in a subnet chosen when the cluster is created.\n"
        + "sleeper.subnets=subnet-abcdefgh\n" + "\n"
        + "# An email address used by the TopicStack to publish SNS notifications of errors.\n"
        + "# sleeper.errors.email=\n", writer.toString());
  }

  /**
   * Test {@link GeneratePropertiesTemplates#writeExampleBasicTableProperties(Writer)}.
   * <ul>
   *   <li>Then {@link StringWriter#StringWriter()} toString is a string.</li>
   * </ul>
   * <p>
   * Method under test: {@link GeneratePropertiesTemplates#writeExampleBasicTableProperties(Writer)}
   */
  @Test
  @DisplayName("Test writeExampleBasicTableProperties(Writer); then StringWriter() toString is a string")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void GeneratePropertiesTemplates.writeExampleBasicTableProperties(Writer)"})
  void testWriteExampleBasicTableProperties_thenStringWriterToStringIsAString() {
    // Arrange
    StringWriter writer = new StringWriter();

    // Act
    GeneratePropertiesTemplates.writeExampleBasicTableProperties(writer);

    // Assert
    assertEquals("\n" + "## The following table properties relate to the definition of data inside a table.\n" + "\n"
        + "# A unique name identifying this table.\n" + "sleeper.table.name=example-table\n" + "\n"
        + "# Fully qualified class of a custom iterator to use when iterating over the values in this table.\n"
        + "# Defaults to nothing.\n" + "sleeper.table.iterator.class.name=sleeper.core.iterator.impl.AgeOffIterator\n"
        + "\n" + "# Iterator configuration. An iterator will be initialised with the following configuration.\n"
        + "sleeper.table.iterator.config=b,3600000\n" + "\n" + "\n"
        + "## The following table properties relate to partition splitting.\n" + "\n"
        + "# Splits file which will be used to initialise the partitions for this table. Defaults to" + " nothing and\n"
        + "# the table will be created with a single root partition.\n"
        + "sleeper.table.splits.file=example/full/splits.txt\n", writer.toString());
  }

  /**
   * Test {@link GeneratePropertiesTemplates#writeTablePropertiesTemplate(Writer)}.
   * <ul>
   *   <li>Then {@link StringWriter#StringWriter()} toString is a string.</li>
   * </ul>
   * <p>
   * Method under test: {@link GeneratePropertiesTemplates#writeTablePropertiesTemplate(Writer)}
   */
  @Test
  @DisplayName("Test writeTablePropertiesTemplate(Writer); then StringWriter() toString is a string")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void GeneratePropertiesTemplates.writeTablePropertiesTemplate(Writer)"})
  void testWriteTablePropertiesTemplate_thenStringWriterToStringIsAString() {
    // Arrange
    StringWriter out = new StringWriter();

    // Act
    GeneratePropertiesTemplates.writeTablePropertiesTemplate(out);

    // Assert
    assertEquals("#################################################################################\n"
        + "#                           SLEEPER TABLE PROPERTIES                            #\n"
        + "#################################################################################\n" + "\n"
        + "###################\n" + "# Template Values #\n" + "###################\n" + "\n"
        + "## The following table properties relate to the definition of data inside a table.\n" + "\n"
        + "# A unique name identifying this table.\n" + "sleeper.table.name=changeme\n" + "\n" + "\n"
        + "## The following table properties relate to the storage of data inside a table.\n" + "\n"
        + "# The size of the row group in the Parquet files - defaults to the value in the instance" + " properties.\n"
        + "sleeper.table.rowgroup.size=8388608\n" + "\n"
        + "# The size of the page in the Parquet files - defaults to the value in the instance properties.\n"
        + "sleeper.table.page.size=131072\n" + "\n"
        + "# The compression codec to use for this table. Defaults to the value in the instance properties.\n"
        + "# Valid values are: [uncompressed, snappy, gzip, lzo, brotli, lz4, zstd]\n"
        + "sleeper.table.compression.codec=zstd\n" + "\n"
        + "# A file will not be deleted until this number of minutes have passed after it has been marked as\n"
        + "# ready for garbage collection. The reason for not deleting files immediately after they have been\n"
        + "# marked as ready for garbage collection is that they may still be in use by queries. Defaults" + " to the\n"
        + "# value set in the instance properties.\n" + "sleeper.table.gc.delay.minutes=15\n" + "\n" + "\n"
        + "## The following table properties relate to storing and retrieving metadata for tables.\n" + "\n"
        + "# The name of the class used for the state store. The default is DynamoDBTransactionLogStateStore.\n"
        + "# Options are:\n" + "# sleeper.statestore.transactionlog.DynamoDBTransactionLogStateStore\n"
        + "# sleeper.statestore.transactionlog.DynamoDBTransactionLogStateStoreNoSnapshots\n"
        + "sleeper.table.statestore.classname=sleeper.statestore.transactionlog.DynamoDBTransactionLogStateStore"
        + "\n", out.toString());
  }
}
