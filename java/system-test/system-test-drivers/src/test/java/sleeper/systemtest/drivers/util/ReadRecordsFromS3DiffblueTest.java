package sleeper.systemtest.drivers.util;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import com.amazonaws.services.s3.model.Owner;
import com.amazonaws.services.s3.model.RestoreStatus;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;
import org.apache.hadoop.conf.Configuration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.schema.Schema;

class ReadRecordsFromS3DiffblueTest {
  /**
   * Test {@link ReadRecordsFromS3#getRecords(Schema, S3ObjectSummary, Configuration)}.
   * <ul>
   *   <li>Given {@code bucket-name}.</li>
   *   <li>Then throw {@link RuntimeException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReadRecordsFromS3#getRecords(Schema, S3ObjectSummary, Configuration)}
   */
  @Test
  @DisplayName("Test getRecords(Schema, S3ObjectSummary, Configuration); given 'bucket-name'; then throw RuntimeException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.util.stream.Stream ReadRecordsFromS3.getRecords(Schema, S3ObjectSummary, Configuration)"})
  void testGetRecords_givenBucketName_thenThrowRuntimeException() {
    // Arrange
    Schema schema = mock(Schema.class);

    S3ObjectSummary s3ObjectSummary = new S3ObjectSummary();
    s3ObjectSummary.setBucketName("bucket-name");
    s3ObjectSummary.setETag("E Tag");
    s3ObjectSummary.setKey("Key");
    s3ObjectSummary
        .setLastModified(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    s3ObjectSummary.setOwner(new Owner("42", "Display Name"));
    s3ObjectSummary.setRestoreStatus(new RestoreStatus());
    s3ObjectSummary.setSize(3L);
    s3ObjectSummary.setStorageClass("Storage Class");

    // Act and Assert
    assertThrows(RuntimeException.class,
        () -> ReadRecordsFromS3.getRecords(schema, s3ObjectSummary, new Configuration()));
  }

  /**
   * Test {@link ReadRecordsFromS3#getRecords(Schema, S3ObjectSummary, Configuration)}.
   * <ul>
   *   <li>Given {@code bucket-name}.</li>
   *   <li>When {@code null}.</li>
   *   <li>Then throw {@link RuntimeException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReadRecordsFromS3#getRecords(Schema, S3ObjectSummary, Configuration)}
   */
  @Test
  @DisplayName("Test getRecords(Schema, S3ObjectSummary, Configuration); given 'bucket-name'; when 'null'; then throw RuntimeException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.util.stream.Stream ReadRecordsFromS3.getRecords(Schema, S3ObjectSummary, Configuration)"})
  void testGetRecords_givenBucketName_whenNull_thenThrowRuntimeException() {
    // Arrange
    S3ObjectSummary s3ObjectSummary = new S3ObjectSummary();
    s3ObjectSummary.setBucketName("bucket-name");
    s3ObjectSummary.setETag("E Tag");
    s3ObjectSummary.setKey("Key");
    s3ObjectSummary
        .setLastModified(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    s3ObjectSummary.setOwner(new Owner("42", "Display Name"));
    s3ObjectSummary.setRestoreStatus(new RestoreStatus());
    s3ObjectSummary.setSize(3L);
    s3ObjectSummary.setStorageClass("Storage Class");

    // Act and Assert
    assertThrows(RuntimeException.class,
        () -> ReadRecordsFromS3.getRecords(null, s3ObjectSummary, new Configuration()));
  }

  /**
   * Test {@link ReadRecordsFromS3#getRecords(Schema, S3ObjectSummary, Configuration)}.
   * <ul>
   *   <li>Given {@code U/U@U}.</li>
   *   <li>When {@link S3ObjectSummary} (default constructor) Key is {@code U/U@U}.</li>
   *   <li>Then throw {@link RuntimeException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReadRecordsFromS3#getRecords(Schema, S3ObjectSummary, Configuration)}
   */
  @Test
  @DisplayName("Test getRecords(Schema, S3ObjectSummary, Configuration); given 'U/U@U'; when S3ObjectSummary (default constructor) Key is 'U/U@U'; then throw RuntimeException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.util.stream.Stream ReadRecordsFromS3.getRecords(Schema, S3ObjectSummary, Configuration)"})
  void testGetRecords_givenUUU_whenS3ObjectSummaryKeyIsUUU_thenThrowRuntimeException() {
    // Arrange
    Schema schema = mock(Schema.class);

    S3ObjectSummary s3ObjectSummary = new S3ObjectSummary();
    s3ObjectSummary.setBucketName("bucket-name");
    s3ObjectSummary.setETag("E Tag");
    s3ObjectSummary.setKey("U/U@U");
    s3ObjectSummary
        .setLastModified(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    s3ObjectSummary.setOwner(new Owner("42", "Display Name"));
    s3ObjectSummary.setRestoreStatus(new RestoreStatus());
    s3ObjectSummary.setSize(3L);
    s3ObjectSummary.setStorageClass("Storage Class");

    // Act and Assert
    assertThrows(RuntimeException.class,
        () -> ReadRecordsFromS3.getRecords(schema, s3ObjectSummary, new Configuration()));
  }
}
