package sleeper.ingest.runner;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.iterator.IteratorCreationException;
import sleeper.core.record.Record;
import sleeper.core.statestore.StateStoreException;
import sleeper.ingest.runner.impl.IngestCoordinator;

class IngestRecordsFromIteratorDiffblueTest {
  /**
   * Test {@link IngestRecordsFromIterator#IngestRecordsFromIterator(IngestCoordinator, Iterator)}.
   * <p>
   * Method under test: {@link IngestRecordsFromIterator#IngestRecordsFromIterator(IngestCoordinator, Iterator)}
   */
  @Test
  @DisplayName("Test new IngestRecordsFromIterator(IngestCoordinator, Iterator)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void IngestRecordsFromIterator.<init>(IngestCoordinator, Iterator)"})
  void testNewIngestRecordsFromIterator() throws IOException, IteratorCreationException, StateStoreException {
    // Arrange
    IngestCoordinator<Record> ingestCoordinator = mock(IngestCoordinator.class);

    ArrayList<Record> resultRecordList = new ArrayList<>();

    // Act and Assert
    assertNull((new IngestRecordsFromIterator(ingestCoordinator, resultRecordList.iterator())).write());
  }
}
