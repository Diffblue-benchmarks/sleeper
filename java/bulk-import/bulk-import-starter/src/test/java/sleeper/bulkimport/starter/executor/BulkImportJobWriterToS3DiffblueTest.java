package sleeper.bulkimport.starter.executor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.bulkimport.core.job.BulkImportJob;
import sleeper.core.properties.SleeperPropertyIndex;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.instance.InstanceProperty;

class BulkImportJobWriterToS3DiffblueTest {
  /**
   * Test {@link BulkImportJobWriterToS3#BulkImportJobWriterToS3(InstanceProperties, AmazonS3)}.
   * <p>
   * Method under test: {@link BulkImportJobWriterToS3#BulkImportJobWriterToS3(InstanceProperties, AmazonS3)}
   */
  @Test
  @DisplayName("Test new BulkImportJobWriterToS3(InstanceProperties, AmazonS3)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void BulkImportJobWriterToS3.<init>(InstanceProperties, AmazonS3)"})
  void testNewBulkImportJobWriterToS3() {
    // Arrange
    InstanceProperties instanceProperties = new InstanceProperties();

    // Act and Assert
    InstanceProperties instanceProperties2 = (new BulkImportJobWriterToS3(instanceProperties,
        new AmazonS3Client())).instanceProperties;
    SleeperPropertyIndex<InstanceProperty> propertiesIndex = instanceProperties2.getPropertiesIndex();
    List<InstanceProperty> cdkDefined = propertiesIndex.getCdkDefined();
    assertEquals(131, cdkDefined.size());
    List<InstanceProperty> userDefined = propertiesIndex.getUserDefined();
    assertEquals(325, userDefined.size());
    List<InstanceProperty> all = propertiesIndex.getAll();
    assertEquals(456, all.size());
    InstanceProperty getResult = all.get(0);
    assertFalse(getResult.isEditable());
    InstanceProperty getResult2 = all.get(454);
    assertFalse(getResult2.isEditable());
    InstanceProperty getResult3 = all.get(455);
    assertFalse(getResult3.isEditable());
    InstanceProperty getResult4 = cdkDefined.get(0);
    assertFalse(getResult4.isEditable());
    InstanceProperty getResult5 = cdkDefined.get(1);
    assertFalse(getResult5.isEditable());
    assertFalse(getResult.isIncludedInBasicTemplate());
    InstanceProperty getResult6 = all.get(1);
    assertFalse(getResult6.isIncludedInBasicTemplate());
    assertFalse(getResult2.isIncludedInBasicTemplate());
    assertFalse(getResult3.isIncludedInBasicTemplate());
    assertFalse(getResult4.isIncludedInBasicTemplate());
    assertFalse(getResult5.isIncludedInBasicTemplate());
    InstanceProperty getResult7 = userDefined.get(323);
    assertFalse(getResult7.isIncludedInBasicTemplate());
    InstanceProperty getResult8 = userDefined.get(324);
    assertFalse(getResult8.isIncludedInBasicTemplate());
    assertFalse(getResult2.isIncludedInTemplate());
    assertFalse(getResult3.isIncludedInTemplate());
    assertFalse(getResult4.isIncludedInTemplate());
    assertFalse(getResult5.isIncludedInTemplate());
    assertFalse(getResult.isSetByCdk());
    assertFalse(getResult6.isSetByCdk());
    assertFalse(getResult7.isSetByCdk());
    assertFalse(getResult8.isSetByCdk());
    assertFalse(getResult2.isUserDefined());
    assertFalse(getResult3.isUserDefined());
    assertFalse(getResult4.isUserDefined());
    assertFalse(getResult5.isUserDefined());
    Stream<Entry<String, String>> unknownProperties = instanceProperties2.getUnknownProperties();
    assertTrue(unknownProperties.limit(5).collect(Collectors.toList()).isEmpty());
    assertTrue(instanceProperties2.toMap().isEmpty());
    assertTrue(instanceProperties2.getTags().isEmpty());
    assertTrue(getResult6.isEditable());
    assertTrue(getResult7.isEditable());
    assertTrue(getResult8.isEditable());
    assertTrue(getResult.isIgnoreEmptyValue());
    assertTrue(getResult6.isIgnoreEmptyValue());
    assertTrue(getResult2.isIgnoreEmptyValue());
    assertTrue(getResult3.isIgnoreEmptyValue());
    assertTrue(getResult4.isIgnoreEmptyValue());
    assertTrue(getResult5.isIgnoreEmptyValue());
    assertTrue(getResult7.isIgnoreEmptyValue());
    assertTrue(getResult8.isIgnoreEmptyValue());
    assertTrue(getResult.isIncludedInTemplate());
    assertTrue(getResult6.isIncludedInTemplate());
    assertTrue(getResult7.isIncludedInTemplate());
    assertTrue(getResult8.isIncludedInTemplate());
    assertTrue(getResult2.isSetByCdk());
    assertTrue(getResult3.isSetByCdk());
    assertTrue(getResult4.isSetByCdk());
    assertTrue(getResult5.isSetByCdk());
    assertTrue(getResult.isUserDefined());
    assertTrue(getResult6.isUserDefined());
    assertTrue(getResult7.isUserDefined());
    assertTrue(getResult8.isUserDefined());
  }

  /**
   * Test {@link BulkImportJobWriterToS3#writeJobToBulkImportBucket(BulkImportJob, String)}.
   * <ul>
   *   <li>Then throw {@link RuntimeException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BulkImportJobWriterToS3#writeJobToBulkImportBucket(BulkImportJob, String)}
   */
  @Test
  @DisplayName("Test writeJobToBulkImportBucket(BulkImportJob, String); then throw RuntimeException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void BulkImportJobWriterToS3.writeJobToBulkImportBucket(BulkImportJob, String)"})
  void testWriteJobToBulkImportBucket_thenThrowRuntimeException() {
    // Arrange
    InstanceProperties instanceProperties = new InstanceProperties();

    // Act and Assert
    assertThrows(RuntimeException.class, () -> (new BulkImportJobWriterToS3(instanceProperties, new AmazonS3Client()))
        .writeJobToBulkImportBucket(mock(BulkImportJob.class), "Job Run ID"));
  }
}
