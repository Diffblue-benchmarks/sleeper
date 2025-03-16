package sleeper.statestore.transactionlog;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.instance.InstanceProperty;
import sleeper.core.statestore.transactionlog.log.StoreTransactionBodyResult;
import sleeper.core.statestore.transactionlog.transaction.StateStoreTransaction;
import sleeper.core.statestore.transactionlog.transaction.TransactionSerDe;
import sleeper.core.statestore.transactionlog.transaction.TransactionSerDeProvider;
import sleeper.core.statestore.transactionlog.transaction.TransactionType;
import sleeper.core.statestore.transactionlog.transaction.impl.AddFilesTransaction;

class S3TransactionBodyStoreDiffblueTest {
  /**
   * Test {@link S3TransactionBodyStore#store(String, String, StateStoreTransaction)} with {@code key}, {@code tableId}, {@code transaction}.
   * <ul>
   *   <li>Then calls {@link AmazonS3Client#putObject(String, String, String)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link S3TransactionBodyStore#store(String, String, StateStoreTransaction)}
   */
  @Test
  @DisplayName("Test store(String, String, StateStoreTransaction) with 'key', 'tableId', 'transaction'; then calls putObject(String, String, String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void S3TransactionBodyStore.store(String, String, StateStoreTransaction)"})
  void testStoreWithKeyTableIdTransaction_thenCallsPutObject() throws SdkClientException, UnknownHostException {
    try (MockedStatic<InetAddress> mockInetAddress = mockStatic(InetAddress.class)) {

      // Arrange
      mockInetAddress.when(() -> InetAddress.getByAddress(Mockito.<byte[]>any())).thenReturn(mock(InetAddress.class));
      mockInetAddress.when(() -> InetAddress.getByName(Mockito.<String>any())).thenReturn(mock(InetAddress.class));
      mockInetAddress.when(() -> InetAddress.getAllByName(Mockito.<String>any()))
          .thenReturn(new InetAddress[]{mock(InetAddress.class)});
      InstanceProperties instanceProperties = mock(InstanceProperties.class);
      when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");

      PutObjectResult putObjectResult = new PutObjectResult();
      putObjectResult.setBucketKeyEnabled(true);
      putObjectResult.setContentMd5("MjdjN2NmNDAwMjI5MTAzZTAwYzZkODgzMDAyOWUyOWI=");
      putObjectResult.setETag("E Tag");
      putObjectResult
          .setExpirationTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
      putObjectResult.setExpirationTimeRuleId("42");
      putObjectResult.setMetadata(new ObjectMetadata());
      putObjectResult.setRequesterCharged(true);
      putObjectResult.setSSEAlgorithm("Algorithm");
      putObjectResult.setSSECustomerAlgorithm("Algorithm");
      putObjectResult.setSSECustomerKeyMd5("27c7cf400229103e00c6d8830029e29b");
      putObjectResult.setVersionId("42");
      AmazonS3Client s3Client = mock(AmazonS3Client.class);
      when(s3Client.putObject(Mockito.<String>any(), Mockito.<String>any(), Mockito.<String>any()))
          .thenReturn(putObjectResult);
      TransactionSerDeProvider serDeProvider = mock(TransactionSerDeProvider.class);
      when(serDeProvider.getByTableId(Mockito.<String>any())).thenReturn(TransactionSerDe.forFileTransactions());
      S3TransactionBodyStore s3TransactionBodyStore = new S3TransactionBodyStore(instanceProperties, s3Client,
          serDeProvider);

      // Act
      s3TransactionBodyStore.store("Key", "42", new AddFilesTransaction(new ArrayList<>()));

      // Assert
      verify(s3Client).putObject(eq("Get"), eq("Key"), eq("{\"files\":[]}"));
      verify(instanceProperties).get(isA(InstanceProperty.class));
      verify(serDeProvider).getByTableId(eq("42"));
    }
  }

  /**
   * Test {@link S3TransactionBodyStore#store(String, String, StateStoreTransaction)} with {@code key}, {@code tableId}, {@code transaction}.
   * <ul>
   *   <li>Then calls {@link TransactionSerDe#toJson(StateStoreTransaction)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link S3TransactionBodyStore#store(String, String, StateStoreTransaction)}
   */
  @Test
  @DisplayName("Test store(String, String, StateStoreTransaction) with 'key', 'tableId', 'transaction'; then calls toJson(StateStoreTransaction)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void S3TransactionBodyStore.store(String, String, StateStoreTransaction)"})
  void testStoreWithKeyTableIdTransaction_thenCallsToJson() throws SdkClientException, UnknownHostException {
    try (MockedStatic<InetAddress> mockInetAddress = mockStatic(InetAddress.class)) {

      // Arrange
      mockInetAddress.when(() -> InetAddress.getByAddress(Mockito.<byte[]>any())).thenReturn(mock(InetAddress.class));
      mockInetAddress.when(() -> InetAddress.getByName(Mockito.<String>any())).thenReturn(mock(InetAddress.class));
      mockInetAddress.when(() -> InetAddress.getAllByName(Mockito.<String>any()))
          .thenReturn(new InetAddress[]{mock(InetAddress.class)});
      InstanceProperties instanceProperties = mock(InstanceProperties.class);
      when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");

      PutObjectResult putObjectResult = new PutObjectResult();
      putObjectResult.setBucketKeyEnabled(true);
      putObjectResult.setContentMd5("MjdjN2NmNDAwMjI5MTAzZTAwYzZkODgzMDAyOWUyOWI=");
      putObjectResult.setETag("E Tag");
      putObjectResult
          .setExpirationTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
      putObjectResult.setExpirationTimeRuleId("42");
      putObjectResult.setMetadata(new ObjectMetadata());
      putObjectResult.setRequesterCharged(true);
      putObjectResult.setSSEAlgorithm("Algorithm");
      putObjectResult.setSSECustomerAlgorithm("Algorithm");
      putObjectResult.setSSECustomerKeyMd5("27c7cf400229103e00c6d8830029e29b");
      putObjectResult.setVersionId("42");
      AmazonS3Client s3Client = mock(AmazonS3Client.class);
      when(s3Client.putObject(Mockito.<String>any(), Mockito.<String>any(), Mockito.<String>any()))
          .thenReturn(putObjectResult);
      TransactionSerDe transactionSerDe = mock(TransactionSerDe.class);
      when(transactionSerDe.toJson(Mockito.<StateStoreTransaction<Object>>any())).thenReturn("Json");
      TransactionSerDeProvider serDeProvider = mock(TransactionSerDeProvider.class);
      when(serDeProvider.getByTableId(Mockito.<String>any())).thenReturn(transactionSerDe);
      S3TransactionBodyStore s3TransactionBodyStore = new S3TransactionBodyStore(instanceProperties, s3Client,
          serDeProvider);

      // Act
      s3TransactionBodyStore.store("Key", "42", new AddFilesTransaction(new ArrayList<>()));

      // Assert
      verify(s3Client).putObject(eq("Get"), eq("Key"), eq("Json"));
      verify(instanceProperties).get(isA(InstanceProperty.class));
      verify(transactionSerDe).toJson(isA(StateStoreTransaction.class));
      verify(serDeProvider).getByTableId(eq("42"));
    }
  }

  /**
   * Test {@link S3TransactionBodyStore#storeIfTooBig(String, StateStoreTransaction)}.
   * <ul>
   *   <li>Then return SerialisedTransaction is {@code {"files":[]}}.</li>
   * </ul>
   * <p>
   * Method under test: {@link S3TransactionBodyStore#storeIfTooBig(String, StateStoreTransaction)}
   */
  @Test
  @DisplayName("Test storeIfTooBig(String, StateStoreTransaction); then return SerialisedTransaction is '{\"files\":[]}'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"StoreTransactionBodyResult S3TransactionBodyStore.storeIfTooBig(String, StateStoreTransaction)"})
  void testStoreIfTooBig_thenReturnSerialisedTransactionIsFiles() {
    // Arrange
    TransactionSerDeProvider serDeProvider = mock(TransactionSerDeProvider.class);
    when(serDeProvider.getByTableId(Mockito.<String>any())).thenReturn(TransactionSerDe.forFileTransactions());
    InstanceProperties instanceProperties = new InstanceProperties();
    S3TransactionBodyStore s3TransactionBodyStore = new S3TransactionBodyStore(instanceProperties, new AmazonS3Client(),
        serDeProvider);

    // Act
    StoreTransactionBodyResult actualStoreIfTooBigResult = s3TransactionBodyStore.storeIfTooBig("42",
        new AddFilesTransaction(new ArrayList<>()));

    // Assert
    verify(serDeProvider).getByTableId(eq("42"));
    Optional<String> serialisedTransaction = actualStoreIfTooBigResult.getSerialisedTransaction();
    assertEquals("{\"files\":[]}", serialisedTransaction.get());
    assertFalse(actualStoreIfTooBigResult.getBodyKey().isPresent());
    assertTrue(serialisedTransaction.isPresent());
  }

  /**
   * Test {@link S3TransactionBodyStore#getBody(String, String, TransactionType)}.
   * <ul>
   *   <li>Given {@link AmazonS3Client} {@link AmazonS3Client#getObjectAsString(String, String)} return empty string.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link S3TransactionBodyStore#getBody(String, String, TransactionType)}
   */
  @Test
  @DisplayName("Test getBody(String, String, TransactionType); given AmazonS3Client getObjectAsString(String, String) return empty string; then return 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"StateStoreTransaction S3TransactionBodyStore.getBody(String, String, TransactionType)"})
  void testGetBody_givenAmazonS3ClientGetObjectAsStringReturnEmptyString_thenReturnNull()
      throws SdkClientException, UnknownHostException {
    try (MockedStatic<InetAddress> mockInetAddress = mockStatic(InetAddress.class)) {

      // Arrange
      mockInetAddress.when(() -> InetAddress.getByAddress(Mockito.<byte[]>any())).thenReturn(mock(InetAddress.class));
      mockInetAddress.when(() -> InetAddress.getByName(Mockito.<String>any())).thenReturn(mock(InetAddress.class));
      mockInetAddress.when(() -> InetAddress.getAllByName(Mockito.<String>any()))
          .thenReturn(new InetAddress[]{mock(InetAddress.class)});
      InstanceProperties instanceProperties = mock(InstanceProperties.class);
      when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
      AmazonS3Client s3Client = mock(AmazonS3Client.class);
      when(s3Client.getObjectAsString(Mockito.<String>any(), Mockito.<String>any())).thenReturn("");
      TransactionSerDeProvider serDeProvider = mock(TransactionSerDeProvider.class);
      when(serDeProvider.getByTableId(Mockito.<String>any())).thenReturn(TransactionSerDe.forFileTransactions());

      // Act
      StateStoreTransaction<?> actualBody = (new S3TransactionBodyStore(instanceProperties, s3Client, serDeProvider))
          .getBody("Key", "42", TransactionType.ADD_FILES);

      // Assert
      verify(s3Client).getObjectAsString(eq("Get"), eq("Key"));
      verify(instanceProperties).get(isA(InstanceProperty.class));
      verify(serDeProvider).getByTableId(eq("42"));
      assertNull(actualBody);
    }
  }

  /**
   * Test {@link S3TransactionBodyStore#getBody(String, String, TransactionType)}.
   * <ul>
   *   <li>Then return {@link AddFilesTransaction#AddFilesTransaction(List)} with files is {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link S3TransactionBodyStore#getBody(String, String, TransactionType)}
   */
  @Test
  @DisplayName("Test getBody(String, String, TransactionType); then return AddFilesTransaction(List) with files is ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"StateStoreTransaction S3TransactionBodyStore.getBody(String, String, TransactionType)"})
  void testGetBody_thenReturnAddFilesTransactionWithFilesIsArrayList() throws SdkClientException, UnknownHostException {
    try (MockedStatic<InetAddress> mockInetAddress = mockStatic(InetAddress.class)) {

      // Arrange
      mockInetAddress.when(() -> InetAddress.getByAddress(Mockito.<byte[]>any())).thenReturn(mock(InetAddress.class));
      mockInetAddress.when(() -> InetAddress.getByName(Mockito.<String>any())).thenReturn(mock(InetAddress.class));
      mockInetAddress.when(() -> InetAddress.getAllByName(Mockito.<String>any()))
          .thenReturn(new InetAddress[]{mock(InetAddress.class)});
      InstanceProperties instanceProperties = mock(InstanceProperties.class);
      when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
      AmazonS3Client s3Client = mock(AmazonS3Client.class);
      when(s3Client.getObjectAsString(Mockito.<String>any(), Mockito.<String>any())).thenReturn("Object As String");
      TransactionSerDe transactionSerDe = mock(TransactionSerDe.class);
      AddFilesTransaction addFilesTransaction = new AddFilesTransaction(new ArrayList<>());
      Mockito
          .<StateStoreTransaction<?>>when(
              transactionSerDe.toTransaction(Mockito.<TransactionType>any(), Mockito.<String>any()))
          .thenReturn(addFilesTransaction);
      TransactionSerDeProvider serDeProvider = mock(TransactionSerDeProvider.class);
      when(serDeProvider.getByTableId(Mockito.<String>any())).thenReturn(transactionSerDe);

      // Act
      StateStoreTransaction<?> actualBody = (new S3TransactionBodyStore(instanceProperties, s3Client, serDeProvider))
          .getBody("Key", "42", TransactionType.ADD_FILES);

      // Assert
      verify(s3Client).getObjectAsString(eq("Get"), eq("Key"));
      verify(instanceProperties).get(isA(InstanceProperty.class));
      verify(transactionSerDe).toTransaction(eq(TransactionType.ADD_FILES), eq("Object As String"));
      verify(serDeProvider).getByTableId(eq("42"));
      assertSame(addFilesTransaction, actualBody);
    }
  }

  /**
   * Test {@link S3TransactionBodyStore#delete(String)}.
   * <ul>
   *   <li>Given {@link AmazonS3Client} {@link AmazonS3Client#deleteObject(String, String)} does nothing.</li>
   *   <li>Then calls {@link AmazonS3Client#deleteObject(String, String)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link S3TransactionBodyStore#delete(String)}
   */
  @Test
  @DisplayName("Test delete(String); given AmazonS3Client deleteObject(String, String) does nothing; then calls deleteObject(String, String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void S3TransactionBodyStore.delete(String)"})
  void testDelete_givenAmazonS3ClientDeleteObjectDoesNothing_thenCallsDeleteObject()
      throws SdkClientException, UnknownHostException {
    try (MockedStatic<InetAddress> mockInetAddress = mockStatic(InetAddress.class)) {

      // Arrange
      mockInetAddress.when(() -> InetAddress.getByAddress(Mockito.<byte[]>any())).thenReturn(mock(InetAddress.class));
      mockInetAddress.when(() -> InetAddress.getByName(Mockito.<String>any())).thenReturn(mock(InetAddress.class));
      mockInetAddress.when(() -> InetAddress.getAllByName(Mockito.<String>any()))
          .thenReturn(new InetAddress[]{mock(InetAddress.class)});
      InstanceProperties instanceProperties = mock(InstanceProperties.class);
      when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
      AmazonS3Client s3Client = mock(AmazonS3Client.class);
      doNothing().when(s3Client).deleteObject(Mockito.<String>any(), Mockito.<String>any());

      // Act
      (new S3TransactionBodyStore(instanceProperties, s3Client, mock(TransactionSerDeProvider.class))).delete("Key");

      // Assert
      verify(s3Client).deleteObject(eq("Get"), eq("Key"));
      verify(instanceProperties).get(isA(InstanceProperty.class));
    }
  }
}
