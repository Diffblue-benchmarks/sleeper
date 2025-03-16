package sleeper.systemtest.datageneration;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.SendMessageResult;
import com.diffblue.cover.annotations.MaintainedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;
import sleeper.core.properties.instance.InstanceProperties;
import sleeper.core.properties.instance.InstanceProperty;
import sleeper.core.properties.table.TableProperties;
import sleeper.core.properties.validation.IngestQueue;
import sleeper.systemtest.configuration.SystemTestProperty;
import sleeper.systemtest.configuration.SystemTestPropertyValues;

public class IngestRandomDataViaQueueDiffblueTest {
  /**
   * Test {@link IngestRandomDataViaQueue#sendJob(String, String, SystemTestPropertyValues, InstanceIngestSession)}.
   * <ul>
   *   <li>Given {@link InstanceProperties#InstanceProperties()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestRandomDataViaQueue#sendJob(String, String, SystemTestPropertyValues, InstanceIngestSession)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({
      "void IngestRandomDataViaQueue.sendJob(String, String, SystemTestPropertyValues, InstanceIngestSession)"})
  public void testSendJob_givenInstanceProperties() {
    // Arrange
    SystemTestPropertyValues systemTestProperties = mock(SystemTestPropertyValues.class);
    when(systemTestProperties.getEnumValue(Mockito.<SystemTestProperty>any(), Mockito.<Class<IngestQueue>>any()))
        .thenReturn(IngestQueue.STANDARD_INGEST);
    AmazonSQS amazonSQS = mock(AmazonSQS.class);
    when(amazonSQS.sendMessage(Mockito.<String>any(), Mockito.<String>any())).thenReturn(new SendMessageResult());
    InstanceIngestSession session = mock(InstanceIngestSession.class);
    when(session.sqs()).thenReturn(amazonSQS);
    when(session.instanceProperties()).thenReturn(new InstanceProperties());
    when(session.tableProperties()).thenReturn(new TableProperties(new InstanceProperties()));

    // Act
    IngestRandomDataViaQueue.sendJob("42", "Dir", systemTestProperties, session);

    // Assert
    verify(amazonSQS).sendMessage(isNull(), eq("{\"id\":\"42\",\"files\":[\"Dir\"]}"));
    verify(systemTestProperties).getEnumValue(isA(SystemTestProperty.class), isA(Class.class));
    verify(session).instanceProperties();
    verify(session).sqs();
    verify(session).tableProperties();
  }

  /**
   * Test {@link IngestRandomDataViaQueue#sendJob(String, String, SystemTestPropertyValues, InstanceIngestSession)}.
   * <ul>
   *   <li>Given {@link InstanceProperties} {@link InstanceProperties#get(InstanceProperty)} return {@code Get}.</li>
   *   <li>Then calls {@link InstanceProperties#get(InstanceProperty)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link IngestRandomDataViaQueue#sendJob(String, String, SystemTestPropertyValues, InstanceIngestSession)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({
      "void IngestRandomDataViaQueue.sendJob(String, String, SystemTestPropertyValues, InstanceIngestSession)"})
  public void testSendJob_givenInstancePropertiesGetReturnGet_thenCallsGet() {
    // Arrange
    SystemTestPropertyValues systemTestProperties = mock(SystemTestPropertyValues.class);
    when(systemTestProperties.getEnumValue(Mockito.<SystemTestProperty>any(), Mockito.<Class<IngestQueue>>any()))
        .thenReturn(IngestQueue.STANDARD_INGEST);
    InstanceProperties instanceProperties = mock(InstanceProperties.class);
    when(instanceProperties.get(Mockito.<InstanceProperty>any())).thenReturn("Get");
    AmazonSQS amazonSQS = mock(AmazonSQS.class);
    when(amazonSQS.sendMessage(Mockito.<String>any(), Mockito.<String>any())).thenReturn(new SendMessageResult());
    InstanceIngestSession session = mock(InstanceIngestSession.class);
    when(session.sqs()).thenReturn(amazonSQS);
    when(session.instanceProperties()).thenReturn(instanceProperties);
    when(session.tableProperties()).thenReturn(new TableProperties(new InstanceProperties()));

    // Act
    IngestRandomDataViaQueue.sendJob("42", "Dir", systemTestProperties, session);

    // Assert
    verify(amazonSQS).sendMessage(eq("Get"), eq("{\"id\":\"42\",\"files\":[\"Dir\"]}"));
    verify(systemTestProperties).getEnumValue(isA(SystemTestProperty.class), isA(Class.class));
    verify(instanceProperties).get(isA(InstanceProperty.class));
    verify(session).instanceProperties();
    verify(session).sqs();
    verify(session).tableProperties();
  }
}
