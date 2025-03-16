package sleeper.ingest.core;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Supplier;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.iterator.IteratorCreationException;
import sleeper.core.statestore.StateStoreException;
import sleeper.core.tracker.ingest.job.InMemoryIngestJobTracker;
import sleeper.core.tracker.ingest.task.InMemoryIngestTaskTracker;
import sleeper.core.tracker.job.run.JobRunSummary;
import sleeper.ingest.core.IngestTask.MessageHandle;
import sleeper.ingest.core.IngestTask.MessageReceiver;
import sleeper.ingest.core.job.IngestJob;
import sleeper.ingest.core.job.IngestJob.Builder;
import sleeper.ingest.core.job.IngestJobHandler;

class IngestTaskDiffblueTest {
  /**
   * Test {@link IngestTask#run()}.
   * <ul>
   *   <li>Given {@link IngestJobHandler} {@link IngestJobHandler#ingest(IngestJob, String)} return {@code null}.</li>
   *   <li>Then calls {@link MessageHandle#close()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestTask#run()}
   */
  @Test
  @DisplayName("Test run(); given IngestJobHandler ingest(IngestJob, String) return 'null'; then calls close()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void IngestTask.run()"})
  void testRun_givenIngestJobHandlerIngestReturnNull_thenCallsClose()
      throws IOException, IteratorCreationException, StateStoreException {
    // Arrange
    Supplier<String> jobRunIdSupplier = mock(Supplier.class);
    when(jobRunIdSupplier.get()).thenReturn("Get");
    Supplier<Instant> timeSupplier = mock(Supplier.class);
    when(timeSupplier.get()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    MessageHandle messageHandle = mock(MessageHandle.class);
    doNothing().when(messageHandle).failed();
    Builder builderResult = IngestJob.builder();
    IngestJob buildResult = builderResult.files(new ArrayList<>())
        .id("42")
        .tableId("42")
        .tableName("Table Name")
        .build();
    when(messageHandle.getJob()).thenReturn(buildResult);
    doNothing().when(messageHandle).close();
    Optional<MessageHandle> ofResult = Optional.of(messageHandle);
    MessageReceiver messageReceiver = mock(MessageReceiver.class);
    when(messageReceiver.receiveMessage()).thenReturn(ofResult);
    IngestJobHandler ingester = mock(IngestJobHandler.class);
    when(ingester.ingest(Mockito.<IngestJob>any(), Mockito.<String>any())).thenReturn(null);
    InMemoryIngestJobTracker jobTracker = new InMemoryIngestJobTracker();

    // Act
    (new IngestTask(jobRunIdSupplier, timeSupplier, messageReceiver, ingester, jobTracker,
        new InMemoryIngestTaskTracker(), "42")).run();

    // Assert
    verify(jobRunIdSupplier).get();
    verify(timeSupplier, atLeast(1)).get();
    verify(messageHandle).close();
    verify(messageHandle).failed();
    verify(messageHandle).getJob();
    verify(messageReceiver).receiveMessage();
    verify(ingester).ingest(isA(IngestJob.class), eq("Get"));
  }

  /**
   * Test {@link IngestTask#run()}.
   * <ul>
   *   <li>Given {@link MessageReceiver} {@link MessageReceiver#receiveMessage()} return empty.</li>
   *   <li>Then calls {@link Supplier#get()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestTask#run()}
   */
  @Test
  @DisplayName("Test run(); given MessageReceiver receiveMessage() return empty; then calls get()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void IngestTask.run()"})
  void testRun_givenMessageReceiverReceiveMessageReturnEmpty_thenCallsGet() {
    // Arrange
    Supplier<String> jobRunIdSupplier = mock(Supplier.class);
    when(jobRunIdSupplier.get()).thenReturn("Get");
    Supplier<Instant> timeSupplier = mock(Supplier.class);
    when(timeSupplier.get()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    MessageReceiver messageReceiver = mock(MessageReceiver.class);
    Optional<MessageHandle> emptyResult = Optional.empty();
    when(messageReceiver.receiveMessage()).thenReturn(emptyResult);
    IngestJobHandler ingester = mock(IngestJobHandler.class);
    InMemoryIngestJobTracker jobTracker = new InMemoryIngestJobTracker();

    // Act
    (new IngestTask(jobRunIdSupplier, timeSupplier, messageReceiver, ingester, jobTracker,
        new InMemoryIngestTaskTracker(), "42")).run();

    // Assert
    verify(timeSupplier, atLeast(1)).get();
    verify(messageReceiver).receiveMessage();
  }

  /**
   * Test {@link IngestTask#start()}.
   * <ul>
   *   <li>Then calls {@link Supplier#get()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestTask#start()}
   */
  @Test
  @DisplayName("Test start(); then calls get()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void IngestTask.start()"})
  void testStart_thenCallsGet() {
    // Arrange
    Supplier<Instant> timeSupplier = mock(Supplier.class);
    when(timeSupplier.get()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    Supplier<String> jobRunIdSupplier = mock(Supplier.class);
    MessageReceiver messageReceiver = mock(MessageReceiver.class);
    IngestJobHandler ingester = mock(IngestJobHandler.class);
    InMemoryIngestJobTracker jobTracker = new InMemoryIngestJobTracker();

    // Act
    (new IngestTask(jobRunIdSupplier, timeSupplier, messageReceiver, ingester, jobTracker,
        new InMemoryIngestTaskTracker(), "42")).start();

    // Assert
    verify(timeSupplier).get();
  }

  /**
   * Test {@link IngestTask#handleOneMessage()}.
   * <ul>
   *   <li>Given {@link IngestJobHandler} {@link IngestJobHandler#ingest(IngestJob, String)} return {@code null}.</li>
   *   <li>Then calls {@link MessageHandle#failed()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestTask#handleOneMessage()}
   */
  @Test
  @DisplayName("Test handleOneMessage(); given IngestJobHandler ingest(IngestJob, String) return 'null'; then calls failed()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestTask.handleOneMessage()"})
  void testHandleOneMessage_givenIngestJobHandlerIngestReturnNull_thenCallsFailed()
      throws IOException, IteratorCreationException, StateStoreException {
    // Arrange
    Supplier<String> jobRunIdSupplier = mock(Supplier.class);
    when(jobRunIdSupplier.get()).thenReturn("Get");
    Supplier<Instant> timeSupplier = mock(Supplier.class);
    when(timeSupplier.get()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    MessageHandle messageHandle = mock(MessageHandle.class);
    doNothing().when(messageHandle).failed();
    Builder builderResult = IngestJob.builder();
    IngestJob buildResult = builderResult.files(new ArrayList<>())
        .id("42")
        .tableId("42")
        .tableName("Table Name")
        .build();
    when(messageHandle.getJob()).thenReturn(buildResult);
    doNothing().when(messageHandle).close();
    Optional<MessageHandle> ofResult = Optional.of(messageHandle);
    MessageReceiver messageReceiver = mock(MessageReceiver.class);
    when(messageReceiver.receiveMessage()).thenReturn(ofResult);
    IngestJobHandler ingester = mock(IngestJobHandler.class);
    when(ingester.ingest(Mockito.<IngestJob>any(), Mockito.<String>any())).thenReturn(null);
    InMemoryIngestJobTracker jobTracker = new InMemoryIngestJobTracker();

    // Act
    boolean actualHandleOneMessageResult = (new IngestTask(jobRunIdSupplier, timeSupplier, messageReceiver, ingester,
        jobTracker, new InMemoryIngestTaskTracker(), "42")).handleOneMessage();

    // Assert
    verify(jobRunIdSupplier).get();
    verify(timeSupplier, atLeast(1)).get();
    verify(messageHandle).close();
    verify(messageHandle).failed();
    verify(messageHandle).getJob();
    verify(messageReceiver).receiveMessage();
    verify(ingester).ingest(isA(IngestJob.class), eq("Get"));
    assertFalse(actualHandleOneMessageResult);
  }

  /**
   * Test {@link IngestTask#handleOneMessage()}.
   * <ul>
   *   <li>Given {@link MessageReceiver} {@link MessageReceiver#receiveMessage()} return empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestTask#handleOneMessage()}
   */
  @Test
  @DisplayName("Test handleOneMessage(); given MessageReceiver receiveMessage() return empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestTask.handleOneMessage()"})
  void testHandleOneMessage_givenMessageReceiverReceiveMessageReturnEmpty() {
    // Arrange
    MessageReceiver messageReceiver = mock(MessageReceiver.class);
    Optional<MessageHandle> emptyResult = Optional.empty();
    when(messageReceiver.receiveMessage()).thenReturn(emptyResult);
    Supplier<String> jobRunIdSupplier = mock(Supplier.class);
    Supplier<Instant> timeSupplier = mock(Supplier.class);
    IngestJobHandler ingester = mock(IngestJobHandler.class);
    InMemoryIngestJobTracker jobTracker = new InMemoryIngestJobTracker();

    // Act
    boolean actualHandleOneMessageResult = (new IngestTask(jobRunIdSupplier, timeSupplier, messageReceiver, ingester,
        jobTracker, new InMemoryIngestTaskTracker(), "42")).handleOneMessage();

    // Assert
    verify(messageReceiver).receiveMessage();
    assertFalse(actualHandleOneMessageResult);
  }

  /**
   * Test {@link IngestTask#handleOneMessage()}.
   * <ul>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestTask#handleOneMessage()}
   */
  @Test
  @DisplayName("Test handleOneMessage(); then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean IngestTask.handleOneMessage()"})
  void testHandleOneMessage_thenReturnTrue() throws IOException, IteratorCreationException, StateStoreException {
    // Arrange
    Supplier<String> jobRunIdSupplier = mock(Supplier.class);
    when(jobRunIdSupplier.get()).thenReturn("Get");
    Supplier<Instant> timeSupplier = mock(Supplier.class);
    when(timeSupplier.get()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    MessageHandle messageHandle = mock(MessageHandle.class);
    doNothing().when(messageHandle).completed(Mockito.<JobRunSummary>any());
    Builder builderResult = IngestJob.builder();
    IngestJob buildResult = builderResult.files(new ArrayList<>())
        .id("42")
        .tableId("42")
        .tableName("Table Name")
        .build();
    when(messageHandle.getJob()).thenReturn(buildResult);
    doNothing().when(messageHandle).close();
    Optional<MessageHandle> ofResult = Optional.of(messageHandle);
    MessageReceiver messageReceiver = mock(MessageReceiver.class);
    when(messageReceiver.receiveMessage()).thenReturn(ofResult);
    IngestJobHandler ingester = mock(IngestJobHandler.class);
    when(ingester.ingest(Mockito.<IngestJob>any(), Mockito.<String>any())).thenReturn(IngestResult.noFiles());
    InMemoryIngestJobTracker jobTracker = new InMemoryIngestJobTracker();

    // Act
    boolean actualHandleOneMessageResult = (new IngestTask(jobRunIdSupplier, timeSupplier, messageReceiver, ingester,
        jobTracker, new InMemoryIngestTaskTracker(), "42")).handleOneMessage();

    // Assert
    verify(jobRunIdSupplier).get();
    verify(timeSupplier, atLeast(1)).get();
    verify(messageHandle).close();
    verify(messageHandle).completed(isA(JobRunSummary.class));
    verify(messageHandle).getJob();
    verify(messageReceiver).receiveMessage();
    verify(ingester).ingest(isA(IngestJob.class), eq("Get"));
    assertTrue(actualHandleOneMessageResult);
  }
}
