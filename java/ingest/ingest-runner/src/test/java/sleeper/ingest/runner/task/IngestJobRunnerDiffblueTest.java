package sleeper.ingest.runner.task;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.sqs.AmazonSQSAsyncClient;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.IOException;
import java.util.function.Supplier;
import org.apache.hadoop.conf.Configuration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.iterator.IteratorCreationException;
import sleeper.core.properties.PropertiesReloader;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.table.TablePropertiesProvider;
import sleeper.core.properties.table.TablePropertiesStore;
import sleeper.core.properties.table.TablePropertiesStore.Client;
import sleeper.core.statestore.StateStoreException;
import sleeper.core.statestore.StateStoreProvider;
import sleeper.core.statestore.StateStoreProvider.Factory;
import sleeper.core.table.InMemoryTableIndex;
import sleeper.core.tracker.ingest.job.InMemoryIngestJobTracker;
import sleeper.core.util.ObjectFactory;
import sleeper.ingest.core.job.IngestJob;

class IngestJobRunnerDiffblueTest {
  /**
   * Test {@link IngestJobRunner#ingest(IngestJob, String)}.
   * <ul>
   *   <li>Given {@link RuntimeException#RuntimeException(String)} with {@code foo}.</li>
   *   <li>Then throw {@link RuntimeException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestJobRunner#ingest(IngestJob, String)}
   */
  @Test
  @DisplayName("Test ingest(IngestJob, String); given RuntimeException(String) with 'foo'; then throw RuntimeException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"sleeper.ingest.core.IngestResult IngestJobRunner.ingest(IngestJob, String)"})
  void testIngest_givenRuntimeExceptionWithFoo_thenThrowRuntimeException()
      throws IOException, IteratorCreationException, StateStoreException {
    // Arrange
    PropertiesReloader propertiesReloader = mock(PropertiesReloader.class);
    doNothing().when(propertiesReloader).reloadIfNeeded();
    ObjectFactory objectFactory = ObjectFactory.noUserJars();
    InstanceProperties instanceProperties = new InstanceProperties();
    InstanceProperties instanceProperties2 = new InstanceProperties();
    TablePropertiesProvider tablePropertiesProvider = new TablePropertiesProvider(instanceProperties2,
        new TablePropertiesStore(new InMemoryTableIndex(), mock(Client.class)));

    StateStoreProvider stateStoreProvider = new StateStoreProvider(3, mock(Factory.class));

    InMemoryIngestJobTracker tracker = new InMemoryIngestJobTracker();
    AmazonS3Client s3Client = new AmazonS3Client();
    AmazonSQSAsyncClient sqsClient = new AmazonSQSAsyncClient();
    IngestJobRunner ingestJobRunner = new IngestJobRunner(objectFactory, instanceProperties, tablePropertiesProvider,
        propertiesReloader, stateStoreProvider, tracker, "42", "Local Dir", s3Client, null, sqsClient,
        new Configuration(), mock(Supplier.class));
    IngestJob job = mock(IngestJob.class);
    when(job.getTableName()).thenThrow(new RuntimeException("foo"));

    // Act and Assert
    assertThrows(RuntimeException.class, () -> ingestJobRunner.ingest(job, "42"));
    verify(propertiesReloader).reloadIfNeeded();
    verify(job).getTableName();
  }
}
