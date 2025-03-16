package sleeper.bulkimport.starter;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Supplier;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.bulkimport.core.job.BulkImportJob;
import sleeper.bulkimport.starter.executor.BulkImportExecutor;
import sleeper.bulkimport.starter.executor.BulkImportExecutor.WriteJobToBucket;
import sleeper.bulkimport.starter.executor.PlatformExecutor;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.table.TablePropertiesProvider;
import sleeper.core.properties.table.TablePropertiesStore;
import sleeper.core.properties.table.TablePropertiesStore.Client;
import sleeper.core.statestore.StateStoreProvider;
import sleeper.core.statestore.StateStoreProvider.Factory;
import sleeper.core.table.InMemoryTableIndex;
import sleeper.core.tracker.ingest.job.InMemoryIngestJobTracker;
import sleeper.ingest.core.job.IngestJobMessageHandler;

class BulkImportStarterLambdaDiffblueTest {
  /**
   * Test {@link BulkImportStarterLambda#BulkImportStarterLambda(BulkImportExecutor, IngestJobMessageHandler)}.
   * <ul>
   *   <li>Given empty.</li>
   *   <li>Then calls {@link IngestJobMessageHandler#deserialiseAndValidate(String)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BulkImportStarterLambda#BulkImportStarterLambda(BulkImportExecutor, IngestJobMessageHandler)}
   */
  @Test
  @DisplayName("Test new BulkImportStarterLambda(BulkImportExecutor, IngestJobMessageHandler); given empty; then calls deserialiseAndValidate(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void BulkImportStarterLambda.<init>(BulkImportExecutor, IngestJobMessageHandler)"})
  void testNewBulkImportStarterLambda_givenEmpty_thenCallsDeserialiseAndValidate() {
    // Arrange
    InstanceProperties instanceProperties = new InstanceProperties();
    InstanceProperties instanceProperties2 = new InstanceProperties();
    TablePropertiesProvider tablePropertiesProvider = new TablePropertiesProvider(instanceProperties2,
        new TablePropertiesStore(new InMemoryTableIndex(), mock(Client.class)));

    StateStoreProvider stateStoreProvider = new StateStoreProvider(3, mock(Factory.class));

    BulkImportExecutor executor = new BulkImportExecutor(instanceProperties, tablePropertiesProvider,
        stateStoreProvider, new InMemoryIngestJobTracker(), mock(WriteJobToBucket.class), mock(PlatformExecutor.class),
        mock(Supplier.class));

    IngestJobMessageHandler<BulkImportJob> messageHandler = mock(IngestJobMessageHandler.class);
    Optional<BulkImportJob> emptyResult = Optional.empty();
    when(messageHandler.deserialiseAndValidate(Mockito.<String>any())).thenReturn(emptyResult);

    // Act
    BulkImportStarterLambda actualBulkImportStarterLambda = new BulkImportStarterLambda(executor, messageHandler);
    Void actualHandleRequestResult = actualBulkImportStarterLambda.handleRequest(
        BulkImportStarterLambdaTestHelper.getSqsEvent("Not all who wander are lost"), mock(Context.class));

    // Assert
    verify(messageHandler).deserialiseAndValidate(eq("Not all who wander are lost"));
    assertNull(actualHandleRequestResult);
  }

  /**
   * Test {@link BulkImportStarterLambda#BulkImportStarterLambda(BulkImportExecutor, IngestJobMessageHandler)}.
   * <ul>
   *   <li>When {@link IngestJobMessageHandler}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BulkImportStarterLambda#BulkImportStarterLambda(BulkImportExecutor, IngestJobMessageHandler)}
   */
  @Test
  @DisplayName("Test new BulkImportStarterLambda(BulkImportExecutor, IngestJobMessageHandler); when IngestJobMessageHandler")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void BulkImportStarterLambda.<init>(BulkImportExecutor, IngestJobMessageHandler)"})
  void testNewBulkImportStarterLambda_whenIngestJobMessageHandler() {
    // Arrange
    InstanceProperties instanceProperties = new InstanceProperties();
    InstanceProperties instanceProperties2 = new InstanceProperties();
    TablePropertiesProvider tablePropertiesProvider = new TablePropertiesProvider(instanceProperties2,
        new TablePropertiesStore(new InMemoryTableIndex(), mock(Client.class)));

    StateStoreProvider stateStoreProvider = new StateStoreProvider(3, mock(Factory.class));

    // Act
    BulkImportStarterLambda actualBulkImportStarterLambda = new BulkImportStarterLambda(
        new BulkImportExecutor(instanceProperties, tablePropertiesProvider, stateStoreProvider,
            new InMemoryIngestJobTracker(), mock(WriteJobToBucket.class), mock(PlatformExecutor.class),
            mock(Supplier.class)),
        mock(IngestJobMessageHandler.class));
    SQSEvent event = BulkImportStarterLambdaTestHelper.getSqsEvent("Not all who wander are lost");
    event.setRecords(new ArrayList<>());

    // Assert
    assertNull(actualBulkImportStarterLambda.handleRequest(event, mock(Context.class)));
  }
}
