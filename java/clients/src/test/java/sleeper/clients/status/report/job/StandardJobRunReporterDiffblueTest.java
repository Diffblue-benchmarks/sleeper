package sleeper.clients.status.report.job;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.clients.status.report.job.StandardJobRunReporter.Builder;
import sleeper.clients.status.report.job.StandardJobRunReporter.UpdatePrinter;
import sleeper.clients.status.report.statestore.PageThroughLogs;
import sleeper.clients.util.table.TableFieldDefinition;
import sleeper.clients.util.table.TableWriterFactory;
import sleeper.core.tracker.job.run.AggregatedTaskJobRuns;
import sleeper.core.tracker.job.run.JobRunReport;
import sleeper.core.tracker.job.status.JobRunStartedUpdate;
import sleeper.core.tracker.job.status.JobStatusUpdate;
import sleeper.core.tracker.job.status.TestJobStartedAndFinishedStatus;

class StandardJobRunReporterDiffblueTest {
  /**
   * Test Builder {@link Builder#addProgressFields()}.
   * <ul>
   *   <li>Given withTable builder.</li>
   *   <li>Then return withTable builder.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#addProgressFields()}
   */
  @Test
  @DisplayName("Test Builder addProgressFields(); given withTable builder; then return withTable builder")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.addProgressFields()"})
  void testBuilderAddProgressFields_givenWithTableBuilder_thenReturnWithTableBuilder() {
    // Arrange
    Builder withTableResult = StandardJobRunReporter.withTable(TableWriterFactory.builder());

    // Act and Assert
    assertSame(withTableResult, withTableResult.addProgressFields());
  }

  /**
   * Test Builder {@link Builder#addResultsFields()}.
   * <ul>
   *   <li>Given withTable builder.</li>
   *   <li>Then return withTable builder.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#addResultsFields()}
   */
  @Test
  @DisplayName("Test Builder addResultsFields(); given withTable builder; then return withTable builder")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.addResultsFields()"})
  void testBuilderAddResultsFields_givenWithTableBuilder_thenReturnWithTableBuilder() {
    // Arrange
    Builder withTableResult = StandardJobRunReporter.withTable(TableWriterFactory.builder());

    // Act and Assert
    assertSame(withTableResult, withTableResult.addResultsFields());
  }

  /**
   * Test Builder {@link Builder#build(PrintStream)}.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Builder#build(PrintStream)}
   *   <li>{@link Builder#Builder(TableWriterFactory.Builder)}
   * </ul>
   */
  @Test
  @DisplayName("Test Builder build(PrintStream)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void Builder.<init>(TableWriterFactory.Builder)",
      "StandardJobRunReporter Builder.build(PrintStream)"})
  void testBuilderBuild() {
    // Arrange
    Builder builder = new Builder(TableWriterFactory.builder());

    // Act and Assert
    assertEquals(6, builder.build(new PrintStream(new ByteArrayOutputStream(1))).getFinishedFields().size());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link StandardJobRunReporter#StandardJobRunReporter(PrintStream)}
   *   <li>{@link StandardJobRunReporter#withTable(TableWriterFactory.Builder)}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void StandardJobRunReporter.<init>(PrintStream)",
      "Builder StandardJobRunReporter.withTable(TableWriterFactory.Builder)"})
  void testGettersAndSetters() {
    // Arrange and Act
    StandardJobRunReporter actualStandardJobRunReporter = new StandardJobRunReporter(
        new PrintStream(new ByteArrayOutputStream(1)));
    actualStandardJobRunReporter.withTable(TableWriterFactory.builder());

    // Assert
    List<TableFieldDefinition> finishedFields = actualStandardJobRunReporter.getFinishedFields();
    assertEquals(6, finishedFields.size());
    assertSame(actualStandardJobRunReporter.DURATION, finishedFields.get(1));
    assertSame(actualStandardJobRunReporter.FINISH_TIME, finishedFields.get(0));
    assertSame(actualStandardJobRunReporter.READ_RATE, finishedFields.get(4));
    assertSame(actualStandardJobRunReporter.RECORDS_READ, finishedFields.get(2));
    assertSame(actualStandardJobRunReporter.RECORDS_WRITTEN, finishedFields.get(3));
    assertSame(actualStandardJobRunReporter.WRITE_RATE, finishedFields.get(5));
  }

  /**
   * Test {@link StandardJobRunReporter#StandardJobRunReporter(PrintStream, Builder)}.
   * <ul>
   *   <li>When builder.</li>
   *   <li>Then return FinishedFields size is six.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardJobRunReporter#StandardJobRunReporter(PrintStream, TableWriterFactory.Builder)}
   */
  @Test
  @DisplayName("Test new StandardJobRunReporter(PrintStream, Builder); when builder; then return FinishedFields size is six")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void StandardJobRunReporter.<init>(PrintStream, TableWriterFactory.Builder)"})
  void testNewStandardJobRunReporter_whenBuilder_thenReturnFinishedFieldsSizeIsSix() {
    // Arrange
    PrintStream out = new PrintStream(new ByteArrayOutputStream(1));

    // Act
    StandardJobRunReporter actualStandardJobRunReporter = new StandardJobRunReporter(out, TableWriterFactory.builder());

    // Assert
    List<TableFieldDefinition> finishedFields = actualStandardJobRunReporter.getFinishedFields();
    assertEquals(6, finishedFields.size());
    assertSame(actualStandardJobRunReporter.DURATION, finishedFields.get(1));
    assertSame(actualStandardJobRunReporter.FINISH_TIME, finishedFields.get(0));
    assertSame(actualStandardJobRunReporter.READ_RATE, finishedFields.get(4));
    assertSame(actualStandardJobRunReporter.RECORDS_READ, finishedFields.get(2));
    assertSame(actualStandardJobRunReporter.RECORDS_WRITTEN, finishedFields.get(3));
    assertSame(actualStandardJobRunReporter.WRITE_RATE, finishedFields.get(5));
  }

  /**
   * Test {@link StandardJobRunReporter#printProcessJobRunWithUpdatePrinter(JobRunReport, UpdatePrinter)}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()}.</li>
   *   <li>Then calls {@link AggregatedTaskJobRuns#getStatusUpdates()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardJobRunReporter#printProcessJobRunWithUpdatePrinter(JobRunReport, UpdatePrinter)}
   */
  @Test
  @DisplayName("Test printProcessJobRunWithUpdatePrinter(JobRunReport, UpdatePrinter); given ArrayList(); then calls getStatusUpdates()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void StandardJobRunReporter.printProcessJobRunWithUpdatePrinter(JobRunReport, UpdatePrinter)"})
  void testPrintProcessJobRunWithUpdatePrinter_givenArrayList_thenCallsGetStatusUpdates() {
    // Arrange
    StandardJobRunReporter standardJobRunReporter = new StandardJobRunReporter(
        new PrintStream(new ByteArrayOutputStream(1)));
    AggregatedTaskJobRuns run = mock(AggregatedTaskJobRuns.class);
    when(run.getTaskId()).thenReturn("42");
    when(run.getStatusUpdates()).thenReturn(new ArrayList<>());

    // Act
    standardJobRunReporter.printProcessJobRunWithUpdatePrinter(run, mock(UpdatePrinter.class));

    // Assert
    verify(run).getStatusUpdates();
    verify(run, atLeast(1)).getTaskId();
  }

  /**
   * Test {@link StandardJobRunReporter#printProcessJobRunWithUpdatePrinter(JobRunReport, UpdatePrinter)}.
   * <ul>
   *   <li>Given {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardJobRunReporter#printProcessJobRunWithUpdatePrinter(JobRunReport, UpdatePrinter)}
   */
  @Test
  @DisplayName("Test printProcessJobRunWithUpdatePrinter(JobRunReport, UpdatePrinter); given 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void StandardJobRunReporter.printProcessJobRunWithUpdatePrinter(JobRunReport, UpdatePrinter)"})
  void testPrintProcessJobRunWithUpdatePrinter_givenFalse() {
    // Arrange
    StandardJobRunReporter standardJobRunReporter = new StandardJobRunReporter(
        new PrintStream(new ByteArrayOutputStream(1)));

    ArrayList<JobStatusUpdate> jobStatusUpdateList = new ArrayList<>();
    jobStatusUpdateList.add(mock(JobStatusUpdate.class));
    AggregatedTaskJobRuns run = mock(AggregatedTaskJobRuns.class);
    when(run.getTaskId()).thenReturn("42");
    when(run.getStatusUpdates()).thenReturn(jobStatusUpdateList);
    UpdatePrinter updatePrinter = mock(UpdatePrinter.class);
    when(updatePrinter.print(Mockito.<JobRunReport>any(), Mockito.<JobStatusUpdate>any())).thenReturn(false);

    // Act
    standardJobRunReporter.printProcessJobRunWithUpdatePrinter(run, updatePrinter);

    // Assert
    verify(updatePrinter).print(isA(JobRunReport.class), isA(JobStatusUpdate.class));
    verify(run).getStatusUpdates();
    verify(run, atLeast(1)).getTaskId();
  }

  /**
   * Test {@link StandardJobRunReporter#printProcessJobRunWithUpdatePrinter(JobRunReport, UpdatePrinter)}.
   * <ul>
   *   <li>Given {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardJobRunReporter#printProcessJobRunWithUpdatePrinter(JobRunReport, UpdatePrinter)}
   */
  @Test
  @DisplayName("Test printProcessJobRunWithUpdatePrinter(JobRunReport, UpdatePrinter); given 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void StandardJobRunReporter.printProcessJobRunWithUpdatePrinter(JobRunReport, UpdatePrinter)"})
  void testPrintProcessJobRunWithUpdatePrinter_givenNull() {
    // Arrange
    StandardJobRunReporter standardJobRunReporter = new StandardJobRunReporter(
        new PrintStream(new ByteArrayOutputStream(1)));
    AggregatedTaskJobRuns run = mock(AggregatedTaskJobRuns.class);
    when(run.getTaskId()).thenReturn(null);
    when(run.getStatusUpdates()).thenReturn(new ArrayList<>());

    // Act
    standardJobRunReporter.printProcessJobRunWithUpdatePrinter(run, mock(UpdatePrinter.class));

    // Assert
    verify(run).getStatusUpdates();
    verify(run).getTaskId();
  }

  /**
   * Test {@link StandardJobRunReporter#printProcessJobRunWithUpdatePrinter(JobRunReport, UpdatePrinter)}.
   * <ul>
   *   <li>Given {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardJobRunReporter#printProcessJobRunWithUpdatePrinter(JobRunReport, UpdatePrinter)}
   */
  @Test
  @DisplayName("Test printProcessJobRunWithUpdatePrinter(JobRunReport, UpdatePrinter); given 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void StandardJobRunReporter.printProcessJobRunWithUpdatePrinter(JobRunReport, UpdatePrinter)"})
  void testPrintProcessJobRunWithUpdatePrinter_givenTrue() {
    // Arrange
    StandardJobRunReporter standardJobRunReporter = new StandardJobRunReporter(
        new PrintStream(new ByteArrayOutputStream(1)));

    ArrayList<JobStatusUpdate> jobStatusUpdateList = new ArrayList<>();
    jobStatusUpdateList.add(mock(JobStatusUpdate.class));
    AggregatedTaskJobRuns run = mock(AggregatedTaskJobRuns.class);
    when(run.getTaskId()).thenReturn("42");
    when(run.getStatusUpdates()).thenReturn(jobStatusUpdateList);
    UpdatePrinter updatePrinter = mock(UpdatePrinter.class);
    when(updatePrinter.print(Mockito.<JobRunReport>any(), Mockito.<JobStatusUpdate>any())).thenReturn(true);

    // Act
    standardJobRunReporter.printProcessJobRunWithUpdatePrinter(run, updatePrinter);

    // Assert
    verify(updatePrinter).print(isA(JobRunReport.class), isA(JobStatusUpdate.class));
    verify(run).getStatusUpdates();
    verify(run, atLeast(1)).getTaskId();
  }

  /**
   * Test {@link StandardJobRunReporter#printUpdateTypeInRun(Class, BiConsumer)}.
   * <ul>
   *   <li>Then return not print {@link AggregatedTaskJobRuns} and {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardJobRunReporter#printUpdateTypeInRun(Class, BiConsumer)}
   */
  @Test
  @DisplayName("Test printUpdateTypeInRun(Class, BiConsumer); then return not print AggregatedTaskJobRuns and 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"UpdatePrinter StandardJobRunReporter.printUpdateTypeInRun(Class, BiConsumer)"})
  void testPrintUpdateTypeInRun_thenReturnNotPrintAggregatedTaskJobRunsAndNull() {
    // Arrange
    Class<JobStatusUpdate> type = JobStatusUpdate.class;

    // Act and Assert
    assertFalse(StandardJobRunReporter.printUpdateTypeInRun(type, mock(BiConsumer.class))
        .print(mock(AggregatedTaskJobRuns.class), null));
  }

  /**
   * Test {@link StandardJobRunReporter#printUpdateTypeInRun(Class, BiConsumer)}.
   * <ul>
   *   <li>Then return print {@link AggregatedTaskJobRuns} and {@link JobStatusUpdate}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardJobRunReporter#printUpdateTypeInRun(Class, BiConsumer)}
   */
  @Test
  @DisplayName("Test printUpdateTypeInRun(Class, BiConsumer); then return print AggregatedTaskJobRuns and JobStatusUpdate")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"UpdatePrinter StandardJobRunReporter.printUpdateTypeInRun(Class, BiConsumer)"})
  void testPrintUpdateTypeInRun_thenReturnPrintAggregatedTaskJobRunsAndJobStatusUpdate() {
    // Arrange
    Class<JobStatusUpdate> type = JobStatusUpdate.class;
    BiConsumer<JobRunReport, JobStatusUpdate> printer = mock(BiConsumer.class);
    doNothing().when(printer).accept(Mockito.<JobRunReport>any(), Mockito.<JobStatusUpdate>any());

    // Act
    boolean actualPrintResult = StandardJobRunReporter.printUpdateTypeInRun(type, printer)
        .print(mock(AggregatedTaskJobRuns.class), mock(JobStatusUpdate.class));

    // Assert
    verify(printer).accept(isA(JobRunReport.class), isA(JobStatusUpdate.class));
    assertTrue(actualPrintResult);
  }

  /**
   * Test {@link StandardJobRunReporter#printUpdateType(Class, Consumer)}.
   * <ul>
   *   <li>Then return print {@link AggregatedTaskJobRuns} and {@link JobStatusUpdate}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardJobRunReporter#printUpdateType(Class, Consumer)}
   */
  @Test
  @DisplayName("Test printUpdateType(Class, Consumer); then return print AggregatedTaskJobRuns and JobStatusUpdate")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"UpdatePrinter StandardJobRunReporter.printUpdateType(Class, Consumer)"})
  void testPrintUpdateType_thenReturnPrintAggregatedTaskJobRunsAndJobStatusUpdate() {
    // Arrange
    Class<JobStatusUpdate> type = JobStatusUpdate.class;
    Consumer<JobStatusUpdate> printer = mock(Consumer.class);
    doNothing().when(printer).accept(Mockito.<JobStatusUpdate>any());

    // Act
    boolean actualPrintResult = StandardJobRunReporter.printUpdateType(type, printer)
        .print(mock(AggregatedTaskJobRuns.class), mock(JobStatusUpdate.class));

    // Assert
    verify(printer).accept(isA(JobStatusUpdate.class));
    assertTrue(actualPrintResult);
  }

  /**
   * Test {@link StandardJobRunReporter#printUpdateType(Class, Consumer)}.
   * <ul>
   *   <li>When {@link Consumer}.</li>
   *   <li>Then return not print {@link AggregatedTaskJobRuns} and {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardJobRunReporter#printUpdateType(Class, Consumer)}
   */
  @Test
  @DisplayName("Test printUpdateType(Class, Consumer); when Consumer; then return not print AggregatedTaskJobRuns and 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"UpdatePrinter StandardJobRunReporter.printUpdateType(Class, Consumer)"})
  void testPrintUpdateType_whenConsumer_thenReturnNotPrintAggregatedTaskJobRunsAndNull() {
    // Arrange
    Class<JobStatusUpdate> type = JobStatusUpdate.class;

    // Act and Assert
    assertFalse(StandardJobRunReporter.printUpdateType(type, mock(Consumer.class))
        .print(mock(AggregatedTaskJobRuns.class), null));
  }

  /**
   * Test {@link StandardJobRunReporter#updatePrinters(UpdatePrinter[])}.
   * <ul>
   *   <li>Then return not print {@link AggregatedTaskJobRuns} and {@link JobStatusUpdate}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardJobRunReporter#updatePrinters(UpdatePrinter[])}
   */
  @Test
  @DisplayName("Test updatePrinters(UpdatePrinter[]); then return not print AggregatedTaskJobRuns and JobStatusUpdate")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"UpdatePrinter StandardJobRunReporter.updatePrinters(UpdatePrinter[])"})
  void testUpdatePrinters_thenReturnNotPrintAggregatedTaskJobRunsAndJobStatusUpdate() {
    // Arrange
    UpdatePrinter updatePrinter = mock(UpdatePrinter.class);
    when(updatePrinter.print(Mockito.<JobRunReport>any(), Mockito.<JobStatusUpdate>any())).thenReturn(false);

    // Act
    boolean actualPrintResult = StandardJobRunReporter.updatePrinters(updatePrinter)
        .print(mock(AggregatedTaskJobRuns.class), mock(JobStatusUpdate.class));

    // Assert
    verify(updatePrinter).print(isA(JobRunReport.class), isA(JobStatusUpdate.class));
    assertFalse(actualPrintResult);
  }

  /**
   * Test {@link StandardJobRunReporter#updatePrinters(UpdatePrinter[])}.
   * <ul>
   *   <li>Then return print {@link AggregatedTaskJobRuns} and {@link JobStatusUpdate}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardJobRunReporter#updatePrinters(UpdatePrinter[])}
   */
  @Test
  @DisplayName("Test updatePrinters(UpdatePrinter[]); then return print AggregatedTaskJobRuns and JobStatusUpdate")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"UpdatePrinter StandardJobRunReporter.updatePrinters(UpdatePrinter[])"})
  void testUpdatePrinters_thenReturnPrintAggregatedTaskJobRunsAndJobStatusUpdate() {
    // Arrange
    UpdatePrinter updatePrinter = mock(UpdatePrinter.class);
    when(updatePrinter.print(Mockito.<JobRunReport>any(), Mockito.<JobStatusUpdate>any())).thenReturn(true);

    // Act
    boolean actualPrintResult = StandardJobRunReporter.updatePrinters(updatePrinter)
        .print(mock(AggregatedTaskJobRuns.class), mock(JobStatusUpdate.class));

    // Assert
    verify(updatePrinter).print(isA(JobRunReport.class), isA(JobStatusUpdate.class));
    assertTrue(actualPrintResult);
  }

  /**
   * Test {@link StandardJobRunReporter#printProcessStarted(JobRunStartedUpdate)}.
   * <ul>
   *   <li>Given {@link ByteArrayOutputStream#ByteArrayOutputStream(int)} with one.</li>
   *   <li>Then calls {@link TestJobStartedAndFinishedStatus#getStartTime()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardJobRunReporter#printProcessStarted(JobRunStartedUpdate)}
   */
  @Test
  @DisplayName("Test printProcessStarted(JobRunStartedUpdate); given ByteArrayOutputStream(int) with one; then calls getStartTime()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void StandardJobRunReporter.printProcessStarted(JobRunStartedUpdate)"})
  void testPrintProcessStarted_givenByteArrayOutputStreamWithOne_thenCallsGetStartTime() {
    // Arrange
    StandardJobRunReporter standardJobRunReporter = new StandardJobRunReporter(
        new PrintStream(new ByteArrayOutputStream(1)));
    TestJobStartedAndFinishedStatus update = mock(TestJobStartedAndFinishedStatus.class);
    when(update.getStartTime()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    when(update.getUpdateTime()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act
    standardJobRunReporter.printProcessStarted(update);

    // Assert
    verify(update).getStartTime();
    verify(update).getUpdateTime();
  }

  /**
   * Test {@link StandardJobRunReporter#getFinishedFields()}.
   * <p>
   * Method under test: {@link StandardJobRunReporter#getFinishedFields()}
   */
  @Test
  @DisplayName("Test getFinishedFields()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List StandardJobRunReporter.getFinishedFields()"})
  void testGetFinishedFields() {
    // Arrange
    StandardJobRunReporter standardJobRunReporter = new StandardJobRunReporter(
        new PrintStream(new ByteArrayOutputStream(1)));

    // Act
    List<TableFieldDefinition> actualFinishedFields = standardJobRunReporter.getFinishedFields();

    // Assert
    assertEquals(6, actualFinishedFields.size());
    assertSame(standardJobRunReporter.DURATION, actualFinishedFields.get(1));
    assertSame(standardJobRunReporter.FINISH_TIME, actualFinishedFields.get(0));
    assertSame(standardJobRunReporter.READ_RATE, actualFinishedFields.get(4));
    assertSame(standardJobRunReporter.RECORDS_READ, actualFinishedFields.get(2));
    assertSame(standardJobRunReporter.RECORDS_WRITTEN, actualFinishedFields.get(3));
    assertSame(standardJobRunReporter.WRITE_RATE, actualFinishedFields.get(5));
  }

  /**
   * Test {@link StandardJobRunReporter#formatDecimal(double)}.
   * <ul>
   *   <li>When {@link Double#NaN}.</li>
   *   <li>Then return {@code NaN}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardJobRunReporter#formatDecimal(double)}
   */
  @Test
  @DisplayName("Test formatDecimal(double); when NaN; then return 'NaN'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.lang.String StandardJobRunReporter.formatDecimal(double)"})
  void testFormatDecimal_whenNaN_thenReturnNaN() {
    // Arrange, Act and Assert
    assertEquals("NaN", StandardJobRunReporter.formatDecimal(Double.NaN));
  }

  /**
   * Test {@link StandardJobRunReporter#formatDecimal(double)}.
   * <ul>
   *   <li>When ten.</li>
   *   <li>Then return {@code 10.00}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardJobRunReporter#formatDecimal(double)}
   */
  @Test
  @DisplayName("Test formatDecimal(double); when ten; then return '10.00'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.lang.String StandardJobRunReporter.formatDecimal(double)"})
  void testFormatDecimal_whenTen_thenReturn1000() {
    // Arrange, Act and Assert
    assertEquals("10.00", StandardJobRunReporter.formatDecimal(10.0d));
  }

  /**
   * Test {@link StandardJobRunReporter#formatDurationString(Duration)}.
   * <ul>
   *   <li>When {@link PageThroughLogs#PAGE_MIN_AGE}.</li>
   *   <li>Then return {@code 5m}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardJobRunReporter#formatDurationString(Duration)}
   */
  @Test
  @DisplayName("Test formatDurationString(Duration); when PAGE_MIN_AGE; then return '5m'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.lang.String StandardJobRunReporter.formatDurationString(Duration)"})
  void testFormatDurationString_whenPage_min_age_thenReturn5m() {
    // Arrange, Act and Assert
    assertEquals("5m", StandardJobRunReporter.formatDurationString(PageThroughLogs.PAGE_MIN_AGE));
  }

  /**
   * Test {@link StandardJobRunReporter#getOrNull(Object, Function)}.
   * <ul>
   *   <li>Given {@code Apply}.</li>
   *   <li>When {@link Function} {@link Function#apply(Object)} return {@code Apply}.</li>
   *   <li>Then return {@code Apply}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardJobRunReporter#getOrNull(Object, Function)}
   */
  @Test
  @DisplayName("Test getOrNull(Object, Function); given 'Apply'; when Function apply(Object) return 'Apply'; then return 'Apply'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Object StandardJobRunReporter.getOrNull(Object, Function)"})
  void testGetOrNull_givenApply_whenFunctionApplyReturnApply_thenReturnApply() {
    // Arrange
    Function<Object, Object> getter = mock(Function.class);
    when(getter.apply(Mockito.<Object>any())).thenReturn("Apply");

    // Act
    Object actualOrNull = StandardJobRunReporter.getOrNull("Object", getter);

    // Assert
    verify(getter).apply(isA(Object.class));
    assertEquals("Apply", actualOrNull);
  }

  /**
   * Test {@link StandardJobRunReporter#getOrNull(Object, Function)}.
   * <ul>
   *   <li>When {@link Function}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StandardJobRunReporter#getOrNull(Object, Function)}
   */
  @Test
  @DisplayName("Test getOrNull(Object, Function); when Function; then return 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Object StandardJobRunReporter.getOrNull(Object, Function)"})
  void testGetOrNull_whenFunction_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(StandardJobRunReporter.<Object, Object>getOrNull(null, mock(Function.class)));
  }
}
