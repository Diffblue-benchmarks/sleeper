package sleeper.query.lambda;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.table.TablePropertiesProvider;
import sleeper.core.properties.table.TablePropertiesStore;
import sleeper.core.properties.table.TablePropertiesStore.Client;
import sleeper.core.properties.testutils.DummyInstanceProperty;
import sleeper.core.table.InMemoryTableIndex;
import sleeper.query.core.model.QueryOrLeafPartitionQuery;
import sleeper.query.core.model.QueryValidationException;
import sleeper.query.runner.tracker.QueryStatusReportListeners;
import sleeper.query.runner.tracker.WebSocketQueryStatusReportDestination;

class QueryMessageHandlerDiffblueTest {
  /**
   * Test {@link QueryMessageHandler#deserialiseAndValidate(String)}.
   * <ul>
   *   <li>Then return not Present.</li>
   * </ul>
   * <p>
   * Method under test: {@link QueryMessageHandler#deserialiseAndValidate(String)}
   */
  @Test
  @DisplayName("Test deserialiseAndValidate(String); then return not Present")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Optional QueryMessageHandler.deserialiseAndValidate(String)"})
  void testDeserialiseAndValidate_thenReturnNotPresent() {
    // Arrange
    InstanceProperties instanceProperties = new InstanceProperties();
    DummyInstanceProperty dummyInstanceProperty = new DummyInstanceProperty("+");
    instanceProperties.addToListIfMissing(dummyInstanceProperty, new ArrayList<>());
    TablePropertiesProvider tablePropertiesProvider = new TablePropertiesProvider(instanceProperties,
        new TablePropertiesStore(new InMemoryTableIndex(), mock(Client.class)));

    QueryStatusReportListeners queryTracker = mock(QueryStatusReportListeners.class);
    doNothing().when(queryTracker).queryFailed(Mockito.<String>any(), Mockito.<Exception>any());
    Supplier<String> invalidQueryIdSupplier = mock(Supplier.class);
    when(invalidQueryIdSupplier.get()).thenReturn("Get");

    // Act
    Optional<QueryOrLeafPartitionQuery> actualDeserialiseAndValidateResult = (new QueryMessageHandler(
        tablePropertiesProvider, queryTracker, invalidQueryIdSupplier))
        .deserialiseAndValidate("Not all who wander are lost");

    // Assert
    verify(invalidQueryIdSupplier).get();
    verify(queryTracker).queryFailed(eq("Get"), isA(Exception.class));
    assertFalse(actualDeserialiseAndValidateResult.isPresent());
  }

  /**
   * Test {@link QueryMessageHandler#deserialiseAndValidate(String)}.
   * <ul>
   *   <li>Then throw {@link QueryValidationException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link QueryMessageHandler#deserialiseAndValidate(String)}
   */
  @Test
  @DisplayName("Test deserialiseAndValidate(String); then throw QueryValidationException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Optional QueryMessageHandler.deserialiseAndValidate(String)"})
  void testDeserialiseAndValidate_thenThrowQueryValidationException() {
    // Arrange
    Supplier<String> invalidQueryIdSupplier = mock(Supplier.class);
    ArrayList<Map<String, String>> statusReportDestinations = new ArrayList<>();
    when(invalidQueryIdSupplier.get())
        .thenThrow(new QueryValidationException("42", statusReportDestinations, new Exception("+")));
    InstanceProperties instanceProperties = new InstanceProperties();
    TablePropertiesProvider tablePropertiesProvider = new TablePropertiesProvider(instanceProperties,
        new TablePropertiesStore(new InMemoryTableIndex(), mock(Client.class)));

    // Act and Assert
    assertThrows(QueryValidationException.class,
        () -> (new QueryMessageHandler(tablePropertiesProvider,
            new WebSocketQueryStatusReportDestination("us-east-2", "https://config.us-east-2.amazonaws.com", "42"),
            invalidQueryIdSupplier)).deserialiseAndValidate("Not all who wander are lost"));
    verify(invalidQueryIdSupplier).get();
  }

  /**
   * Test {@link QueryMessageHandler#deserialiseAndValidate(String)}.
   * <ul>
   *   <li>When empty string.</li>
   *   <li>Then throw {@link QueryValidationException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link QueryMessageHandler#deserialiseAndValidate(String)}
   */
  @Test
  @DisplayName("Test deserialiseAndValidate(String); when empty string; then throw QueryValidationException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Optional QueryMessageHandler.deserialiseAndValidate(String)"})
  void testDeserialiseAndValidate_whenEmptyString_thenThrowQueryValidationException() {
    // Arrange
    Supplier<String> invalidQueryIdSupplier = mock(Supplier.class);
    ArrayList<Map<String, String>> statusReportDestinations = new ArrayList<>();
    when(invalidQueryIdSupplier.get())
        .thenThrow(new QueryValidationException("42", statusReportDestinations, new Exception("+")));
    InstanceProperties instanceProperties = new InstanceProperties();
    TablePropertiesProvider tablePropertiesProvider = new TablePropertiesProvider(instanceProperties,
        new TablePropertiesStore(new InMemoryTableIndex(), mock(Client.class)));

    // Act and Assert
    assertThrows(QueryValidationException.class,
        () -> (new QueryMessageHandler(tablePropertiesProvider,
            new WebSocketQueryStatusReportDestination("us-east-2", "https://config.us-east-2.amazonaws.com", "42"),
            invalidQueryIdSupplier)).deserialiseAndValidate(""));
    verify(invalidQueryIdSupplier).get();
  }
}
