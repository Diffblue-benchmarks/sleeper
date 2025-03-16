package sleeper.clients;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClient;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.apache.hadoop.conf.Configuration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.clients.util.console.ConsoleInput;
import sleeper.clients.util.console.ConsoleOutput;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.table.TableProperties;
import sleeper.core.properties.table.TablePropertiesProvider;
import sleeper.core.properties.table.TablePropertiesStore;
import sleeper.core.properties.table.TablePropertiesStore.Client;
import sleeper.core.statestore.StateStore;
import sleeper.core.statestore.StateStoreException;
import sleeper.core.statestore.StateStoreProvider;
import sleeper.core.statestore.StateStoreProvider.Factory;
import sleeper.core.table.InMemoryTableIndex;
import sleeper.core.table.TableIndex;
import sleeper.core.util.ObjectFactory;
import sleeper.core.util.ObjectFactoryException;
import sleeper.query.core.model.Query;

class QueryClientDiffblueTest {
  /**
   * Test {@link QueryClient#QueryClient(InstanceProperties, TableIndex, TablePropertiesProvider, ConsoleInput, ConsoleOutput, ObjectFactory, StateStoreProvider)}.
   * <p>
   * Method under test: {@link QueryClient#QueryClient(InstanceProperties, TableIndex, TablePropertiesProvider, ConsoleInput, ConsoleOutput, ObjectFactory, StateStoreProvider)}
   */
  @Test
  @DisplayName("Test new QueryClient(InstanceProperties, TableIndex, TablePropertiesProvider, ConsoleInput, ConsoleOutput, ObjectFactory, StateStoreProvider)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void QueryClient.<init>(InstanceProperties, TableIndex, TablePropertiesProvider, ConsoleInput, ConsoleOutput, ObjectFactory, StateStoreProvider)"})
  void testNewQueryClient() {
    // Arrange
    InstanceProperties instanceProperties = new InstanceProperties();
    InMemoryTableIndex tableIndex = new InMemoryTableIndex();
    InstanceProperties instanceProperties2 = new InstanceProperties();
    TablePropertiesProvider tablePropertiesProvider = new TablePropertiesProvider(instanceProperties2,
        new TablePropertiesStore(new InMemoryTableIndex(), mock(Client.class)));

    ConsoleInput in = new ConsoleInput(null);
    ConsoleOutput out = new ConsoleOutput(new PrintStream(new ByteArrayOutputStream(1)));
    ObjectFactory objectFactory = ObjectFactory.noUserJars();

    // Act
    QueryClient actualQueryClient = new QueryClient(instanceProperties, tableIndex, tablePropertiesProvider, in, out,
        objectFactory, new StateStoreProvider(3, mock(Factory.class)));

    // Assert
    assertNull(actualQueryClient.getTableProperties());
    assertSame(instanceProperties, actualQueryClient.getInstanceProperties());
  }

  /**
   * Test {@link QueryClient#QueryClient(AmazonS3, InstanceProperties, AmazonDynamoDB, Configuration, ConsoleInput, ConsoleOutput)}.
   * <ul>
   *   <li>Then return InstanceProperties is {@link InstanceProperties#InstanceProperties()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link QueryClient#QueryClient(AmazonS3, InstanceProperties, AmazonDynamoDB, Configuration, ConsoleInput, ConsoleOutput)}
   */
  @Test
  @DisplayName("Test new QueryClient(AmazonS3, InstanceProperties, AmazonDynamoDB, Configuration, ConsoleInput, ConsoleOutput); then return InstanceProperties is InstanceProperties()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void QueryClient.<init>(AmazonS3, InstanceProperties, AmazonDynamoDB, Configuration, ConsoleInput, ConsoleOutput)"})
  void testNewQueryClient_thenReturnInstancePropertiesIsInstanceProperties() throws ObjectFactoryException {
    // Arrange
    AmazonS3Client s3Client = new AmazonS3Client();
    InstanceProperties instanceProperties = new InstanceProperties();
    AmazonDynamoDBAsyncClient dynamoDBClient = new AmazonDynamoDBAsyncClient();
    Configuration conf = new Configuration();
    ConsoleInput in = new ConsoleInput(null);

    // Act and Assert
    assertSame(instanceProperties, (new QueryClient(s3Client, instanceProperties, dynamoDBClient, conf, in,
        new ConsoleOutput(new PrintStream(new ByteArrayOutputStream(1))))).getInstanceProperties());
  }

  /**
   * Test {@link QueryClient#QueryClient(AmazonS3, InstanceProperties, AmazonDynamoDB, ConsoleInput, ConsoleOutput, ObjectFactory, StateStoreProvider)}.
   * <ul>
   *   <li>Then return InstanceProperties is {@link InstanceProperties#InstanceProperties()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link QueryClient#QueryClient(AmazonS3, InstanceProperties, AmazonDynamoDB, ConsoleInput, ConsoleOutput, ObjectFactory, StateStoreProvider)}
   */
  @Test
  @DisplayName("Test new QueryClient(AmazonS3, InstanceProperties, AmazonDynamoDB, ConsoleInput, ConsoleOutput, ObjectFactory, StateStoreProvider); then return InstanceProperties is InstanceProperties()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void QueryClient.<init>(AmazonS3, InstanceProperties, AmazonDynamoDB, ConsoleInput, ConsoleOutput, ObjectFactory, StateStoreProvider)"})
  void testNewQueryClient_thenReturnInstancePropertiesIsInstanceProperties2() {
    // Arrange
    AmazonS3Client s3Client = new AmazonS3Client();
    InstanceProperties instanceProperties = new InstanceProperties();
    AmazonDynamoDBAsyncClient dynamoDBClient = new AmazonDynamoDBAsyncClient();
    ConsoleInput in = new ConsoleInput(null);
    ConsoleOutput out = new ConsoleOutput(new PrintStream(new ByteArrayOutputStream(1)));
    ObjectFactory objectFactory = ObjectFactory.noUserJars();

    // Act and Assert
    assertSame(instanceProperties, (new QueryClient(s3Client, instanceProperties, dynamoDBClient, in, out,
        objectFactory, new StateStoreProvider(3, mock(Factory.class)))).getInstanceProperties());
  }

  /**
   * Test {@link QueryClient#init(TableProperties)}.
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link QueryClient#init(TableProperties)}
   */
  @Test
  @DisplayName("Test init(TableProperties); then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void QueryClient.init(TableProperties)"})
  void testInit_thenThrowIllegalArgumentException() throws StateStoreException {
    // Arrange
    StateStore stateStore = mock(StateStore.class);
    when(stateStore.getAllPartitions()).thenThrow(new IllegalArgumentException("foo"));
    Factory stateStoreFactory = mock(Factory.class);
    when(stateStoreFactory.getStateStore(Mockito.<TableProperties>any())).thenReturn(stateStore);
    StateStoreProvider stateStoreProvider = new StateStoreProvider(3, stateStoreFactory);

    AmazonS3Client s3Client = new AmazonS3Client();
    InstanceProperties instanceProperties = new InstanceProperties();
    AmazonDynamoDBAsyncClient dynamoDBClient = new AmazonDynamoDBAsyncClient();
    ConsoleInput in = new ConsoleInput(null);
    ConsoleOutput out = new ConsoleOutput(new PrintStream(new ByteArrayOutputStream(1)));
    QueryClient queryClient = new QueryClient(s3Client, instanceProperties, dynamoDBClient, in, out,
        ObjectFactory.noUserJars(), stateStoreProvider);

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> queryClient.init(new TableProperties(new InstanceProperties())));
    verify(stateStore).getAllPartitions();
    verify(stateStoreFactory).getStateStore(isA(TableProperties.class));
  }

  /**
   * Test {@link QueryClient#submitQuery(TableProperties, Query)}.
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link QueryClient#submitQuery(TableProperties, Query)}
   */
  @Test
  @DisplayName("Test submitQuery(TableProperties, Query); then throw IllegalArgumentException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void QueryClient.submitQuery(TableProperties, Query)"})
  void testSubmitQuery_thenThrowIllegalArgumentException() throws ObjectFactoryException {
    // Arrange
    AmazonS3Client s3Client = new AmazonS3Client();
    InstanceProperties instanceProperties = new InstanceProperties();
    AmazonDynamoDBAsyncClient dynamoDBClient = new AmazonDynamoDBAsyncClient();
    Configuration conf = new Configuration();
    ConsoleInput in = new ConsoleInput(null);
    QueryClient queryClient = new QueryClient(s3Client, instanceProperties, dynamoDBClient, conf, in,
        new ConsoleOutput(new PrintStream(new ByteArrayOutputStream(1))));
    TableProperties tableProperties = new TableProperties(new InstanceProperties());
    Query query = mock(Query.class);
    when(query.getTableName()).thenThrow(new IllegalArgumentException("foo"));

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> queryClient.submitQuery(tableProperties, query));
    verify(query).getTableName();
  }
}
