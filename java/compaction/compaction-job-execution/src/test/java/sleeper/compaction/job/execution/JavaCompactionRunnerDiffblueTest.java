package sleeper.compaction.job.execution;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import org.apache.hadoop.conf.Configuration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.compaction.core.job.CompactionJob;
import sleeper.core.iterator.CloseableIterator;
import sleeper.core.iterator.ConcatenatingIterator;
import sleeper.core.iterator.IteratorCreationException;
import sleeper.core.iterator.MergingIterator;
import sleeper.core.record.Record;
import sleeper.core.schema.Field;
import sleeper.core.schema.Schema;
import sleeper.core.schema.Schema.Builder;
import sleeper.core.schema.type.ByteArrayType;
import sleeper.core.util.ObjectFactory;

class JavaCompactionRunnerDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link JavaCompactionRunner#JavaCompactionRunner(ObjectFactory, Configuration)}
   *   <li>{@link JavaCompactionRunner#isHardwareAccelerated()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JavaCompactionRunner.<init>(ObjectFactory, Configuration)",
    "boolean JavaCompactionRunner.isHardwareAccelerated()"
  })
  void testGettersAndSetters() {
    // Arrange
    ObjectFactory objectFactory = ObjectFactory.noUserJars();

    // Act
    JavaCompactionRunner actualJavaCompactionRunner =
        new JavaCompactionRunner(objectFactory, new Configuration());

    // Assert
    assertFalse(actualJavaCompactionRunner.isHardwareAccelerated());
  }

  /**
   * Test {@link JavaCompactionRunner#getMergingIterator(ObjectFactory, Schema, CompactionJob,
   * List)}.
   *
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()}.
   *   <li>Then throw {@link IteratorCreationException}.
   * </ul>
   *
   * <p>Method under test: {@link JavaCompactionRunner#getMergingIterator(ObjectFactory, Schema,
   * CompactionJob, List)}
   */
  @Test
  @DisplayName(
      "Test getMergingIterator(ObjectFactory, Schema, CompactionJob, List); given ArrayList(); then throw IteratorCreationException")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "CloseableIterator JavaCompactionRunner.getMergingIterator(ObjectFactory, Schema, CompactionJob, List)"
  })
  void testGetMergingIterator_givenArrayList_thenThrowIteratorCreationException()
      throws IteratorCreationException {
    // Arrange
    ObjectFactory objectFactory = ObjectFactory.noUserJars();

    Schema schema = mock(Schema.class);
    when(schema.getRowKeyFieldNames()).thenReturn(new ArrayList<>());
    when(schema.getRowKeyTypes()).thenReturn(new ArrayList<>());
    when(schema.getSortKeyFieldNames()).thenReturn(new ArrayList<>());
    when(schema.getSortKeyTypes()).thenReturn(new ArrayList<>());

    CompactionJob compactionJob = mock(CompactionJob.class);
    when(compactionJob.getIteratorClassName()).thenReturn("Iterator Class Name");

    // Act and Assert
    assertThrows(
        IteratorCreationException.class,
        () ->
            JavaCompactionRunner.getMergingIterator(
                objectFactory, schema, compactionJob, new ArrayList<>()));
    verify(compactionJob, atLeast(1)).getIteratorClassName();
    verify(schema).getRowKeyFieldNames();
    verify(schema).getRowKeyTypes();
    verify(schema).getSortKeyFieldNames();
    verify(schema).getSortKeyTypes();
  }

  /**
   * Test {@link JavaCompactionRunner#getMergingIterator(ObjectFactory, Schema, CompactionJob,
   * List)}.
   *
   * <ul>
   *   <li>Given {@link ConcatenatingIterator#ConcatenatingIterator(List)} with suppliers is {@link
   *       ArrayList#ArrayList()}.
   * </ul>
   *
   * <p>Method under test: {@link JavaCompactionRunner#getMergingIterator(ObjectFactory, Schema,
   * CompactionJob, List)}
   */
  @Test
  @DisplayName(
      "Test getMergingIterator(ObjectFactory, Schema, CompactionJob, List); given ConcatenatingIterator(List) with suppliers is ArrayList()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "CloseableIterator JavaCompactionRunner.getMergingIterator(ObjectFactory, Schema, CompactionJob, List)"
  })
  void testGetMergingIterator_givenConcatenatingIteratorWithSuppliersIsArrayList()
      throws IteratorCreationException {
    // Arrange
    ObjectFactory objectFactory = ObjectFactory.noUserJars();

    Schema schema = mock(Schema.class);
    when(schema.getRowKeyFieldNames()).thenReturn(new ArrayList<>());
    when(schema.getRowKeyTypes()).thenReturn(new ArrayList<>());
    when(schema.getSortKeyFieldNames()).thenReturn(new ArrayList<>());
    when(schema.getSortKeyTypes()).thenReturn(new ArrayList<>());

    CompactionJob compactionJob = mock(CompactionJob.class);
    when(compactionJob.getIteratorClassName()).thenReturn("Iterator Class Name");

    ArrayList<CloseableIterator<Record>> inputIterators = new ArrayList<>();
    inputIterators.add(new ConcatenatingIterator(new ArrayList<>()));

    // Act and Assert
    assertThrows(
        IteratorCreationException.class,
        () ->
            JavaCompactionRunner.getMergingIterator(
                objectFactory, schema, compactionJob, inputIterators));
    verify(compactionJob, atLeast(1)).getIteratorClassName();
    verify(schema).getRowKeyFieldNames();
    verify(schema).getRowKeyTypes();
    verify(schema).getSortKeyFieldNames();
    verify(schema).getSortKeyTypes();
  }

  /**
   * Test {@link JavaCompactionRunner#getMergingIterator(ObjectFactory, Schema, CompactionJob,
   * List)}.
   *
   * <ul>
   *   <li>Given {@link ConcatenatingIterator#ConcatenatingIterator(List)} with suppliers is {@link
   *       ArrayList#ArrayList()}.
   * </ul>
   *
   * <p>Method under test: {@link JavaCompactionRunner#getMergingIterator(ObjectFactory, Schema,
   * CompactionJob, List)}
   */
  @Test
  @DisplayName(
      "Test getMergingIterator(ObjectFactory, Schema, CompactionJob, List); given ConcatenatingIterator(List) with suppliers is ArrayList()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "CloseableIterator JavaCompactionRunner.getMergingIterator(ObjectFactory, Schema, CompactionJob, List)"
  })
  void testGetMergingIterator_givenConcatenatingIteratorWithSuppliersIsArrayList2()
      throws IteratorCreationException {
    // Arrange
    ObjectFactory objectFactory = ObjectFactory.noUserJars();

    Schema schema = mock(Schema.class);
    when(schema.getRowKeyFieldNames()).thenReturn(new ArrayList<>());
    when(schema.getRowKeyTypes()).thenReturn(new ArrayList<>());
    when(schema.getSortKeyFieldNames()).thenReturn(new ArrayList<>());
    when(schema.getSortKeyTypes()).thenReturn(new ArrayList<>());

    CompactionJob compactionJob = mock(CompactionJob.class);
    when(compactionJob.getIteratorClassName()).thenReturn("Iterator Class Name");

    ArrayList<CloseableIterator<Record>> inputIterators = new ArrayList<>();
    inputIterators.add(new ConcatenatingIterator(new ArrayList<>()));
    inputIterators.add(new ConcatenatingIterator(new ArrayList<>()));

    // Act and Assert
    assertThrows(
        IteratorCreationException.class,
        () ->
            JavaCompactionRunner.getMergingIterator(
                objectFactory, schema, compactionJob, inputIterators));
    verify(compactionJob, atLeast(1)).getIteratorClassName();
    verify(schema).getRowKeyFieldNames();
    verify(schema).getRowKeyTypes();
    verify(schema).getSortKeyFieldNames();
    verify(schema).getSortKeyTypes();
  }

  /**
   * Test {@link JavaCompactionRunner#getMergingIterator(ObjectFactory, Schema, CompactionJob,
   * List)}.
   *
   * <ul>
   *   <li>Given {@link Field#Field(String, Type)} with name is {@code 42} and type is {@link
   *       ByteArrayType} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link JavaCompactionRunner#getMergingIterator(ObjectFactory, Schema,
   * CompactionJob, List)}
   */
  @Test
  @DisplayName(
      "Test getMergingIterator(ObjectFactory, Schema, CompactionJob, List); given Field(String, Type) with name is '42' and type is ByteArrayType (default constructor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "CloseableIterator JavaCompactionRunner.getMergingIterator(ObjectFactory, Schema, CompactionJob, List)"
  })
  void testGetMergingIterator_givenFieldWithNameIs42AndTypeIsByteArrayType()
      throws IteratorCreationException {
    // Arrange
    ObjectFactory objectFactory = ObjectFactory.noUserJars();

    ArrayList<Field> rowKeyFields = new ArrayList<>();
    Field field = new Field("42", new ByteArrayType());
    rowKeyFields.add(field);
    Field field2 = new Field("Name", new ByteArrayType());
    rowKeyFields.add(field2);

    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);

    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();

    CompactionJob.Builder builderResult = CompactionJob.builder();
    CompactionJob compactionJob =
        builderResult
            .inputFiles(new ArrayList<>())
            .jobId("42")
            .outputFile("Output File")
            .partitionId("42")
            .tableId("42")
            .build();

    // Act
    CloseableIterator<Record> actualMergingIterator =
        JavaCompactionRunner.getMergingIterator(
            objectFactory, schema, compactionJob, new ArrayList<>());

    // Assert
    assertTrue(actualMergingIterator instanceof MergingIterator);
    assertFalse(actualMergingIterator.hasNext());
  }

  /**
   * Test {@link JavaCompactionRunner#getMergingIterator(ObjectFactory, Schema, CompactionJob,
   * List)}.
   *
   * <ul>
   *   <li>Given {@link Field#Field(String, Type)} with name is {@code 42} and type is {@link
   *       ByteArrayType} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link JavaCompactionRunner#getMergingIterator(ObjectFactory, Schema,
   * CompactionJob, List)}
   */
  @Test
  @DisplayName(
      "Test getMergingIterator(ObjectFactory, Schema, CompactionJob, List); given Field(String, Type) with name is '42' and type is ByteArrayType (default constructor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "CloseableIterator JavaCompactionRunner.getMergingIterator(ObjectFactory, Schema, CompactionJob, List)"
  })
  void testGetMergingIterator_givenFieldWithNameIs42AndTypeIsByteArrayType2()
      throws IteratorCreationException {
    // Arrange
    ObjectFactory objectFactory = ObjectFactory.noUserJars();

    ArrayList<Field> rowKeyFields = new ArrayList<>();
    Field field = new Field("Name", new ByteArrayType());
    rowKeyFields.add(field);

    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);

    ArrayList<Field> sortKeyFields = new ArrayList<>();
    Field field2 = new Field("42", new ByteArrayType());
    sortKeyFields.add(field2);

    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(sortKeyFields);
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();

    CompactionJob.Builder builderResult = CompactionJob.builder();
    CompactionJob compactionJob =
        builderResult
            .inputFiles(new ArrayList<>())
            .jobId("42")
            .outputFile("Output File")
            .partitionId("42")
            .tableId("42")
            .build();

    // Act
    CloseableIterator<Record> actualMergingIterator =
        JavaCompactionRunner.getMergingIterator(
            objectFactory, schema, compactionJob, new ArrayList<>());

    // Assert
    assertTrue(actualMergingIterator instanceof MergingIterator);
    assertFalse(actualMergingIterator.hasNext());
  }

  /**
   * Test {@link JavaCompactionRunner#getMergingIterator(ObjectFactory, Schema, CompactionJob,
   * List)}.
   *
   * <ul>
   *   <li>Then return {@link MergingIterator}.
   * </ul>
   *
   * <p>Method under test: {@link JavaCompactionRunner#getMergingIterator(ObjectFactory, Schema,
   * CompactionJob, List)}
   */
  @Test
  @DisplayName(
      "Test getMergingIterator(ObjectFactory, Schema, CompactionJob, List); then return MergingIterator")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "CloseableIterator JavaCompactionRunner.getMergingIterator(ObjectFactory, Schema, CompactionJob, List)"
  })
  void testGetMergingIterator_thenReturnMergingIterator() throws IteratorCreationException {
    // Arrange
    ObjectFactory objectFactory = ObjectFactory.noUserJars();

    ArrayList<Field> rowKeyFields = new ArrayList<>();
    Field field = new Field("Name", new ByteArrayType());
    rowKeyFields.add(field);

    Builder rowKeyFieldsResult = Schema.builder().rowKeyFields(rowKeyFields);

    Builder sortKeyFieldsResult = rowKeyFieldsResult.sortKeyFields(new ArrayList<>());
    Schema schema = sortKeyFieldsResult.valueFields(new ArrayList<>()).build();

    CompactionJob.Builder builderResult = CompactionJob.builder();
    CompactionJob compactionJob =
        builderResult
            .inputFiles(new ArrayList<>())
            .jobId("42")
            .outputFile("Output File")
            .partitionId("42")
            .tableId("42")
            .build();

    // Act
    CloseableIterator<Record> actualMergingIterator =
        JavaCompactionRunner.getMergingIterator(
            objectFactory, schema, compactionJob, new ArrayList<>());

    // Assert
    assertTrue(actualMergingIterator instanceof MergingIterator);
    assertFalse(actualMergingIterator.hasNext());
  }

  /**
   * Test {@link JavaCompactionRunner#supportsIterators()}.
   *
   * <p>Method under test: {@link JavaCompactionRunner#supportsIterators()}
   */
  @Test
  @DisplayName("Test supportsIterators()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JavaCompactionRunner.supportsIterators()"})
  void testSupportsIterators() {
    // Arrange
    ObjectFactory objectFactory = ObjectFactory.noUserJars();
    JavaCompactionRunner javaCompactionRunner =
        new JavaCompactionRunner(objectFactory, new Configuration());

    // Act and Assert
    assertTrue(javaCompactionRunner.supportsIterators());
  }

  /**
   * Test {@link JavaCompactionRunner#implementationLanguage()}.
   *
   * <p>Method under test: {@link JavaCompactionRunner#implementationLanguage()}
   */
  @Test
  @DisplayName("Test implementationLanguage()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String JavaCompactionRunner.implementationLanguage()"})
  void testImplementationLanguage() {
    // Arrange
    ObjectFactory objectFactory = ObjectFactory.noUserJars();
    JavaCompactionRunner javaCompactionRunner =
        new JavaCompactionRunner(objectFactory, new Configuration());

    // Act and Assert
    assertEquals("Java", javaCompactionRunner.implementationLanguage());
  }
}
