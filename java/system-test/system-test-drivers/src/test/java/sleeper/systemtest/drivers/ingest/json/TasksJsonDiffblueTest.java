package sleeper.systemtest.drivers.ingest.json;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mockStatic;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import software.amazon.awssdk.services.ecs.model.RunTaskResponse;
import software.amazon.awssdk.services.ecs.model.Task;

class TasksJsonDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link TasksJson#TasksJson(List)}
   *   <li>{@link TasksJson#toString()}
   *   <li>{@link TasksJson#getTasks()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void TasksJson.<init>(List)", "List TasksJson.getTasks()", "String TasksJson.toString()"})
  void testGettersAndSetters() {
    // Arrange
    ArrayList<Task> tasks = new ArrayList<>();

    // Act
    TasksJson actualTasksJson = new TasksJson(tasks);
    String actualToStringResult = actualTasksJson.toString();
    List<Task> actualTasks = actualTasksJson.getTasks();

    // Assert
    assertEquals("[]", actualToStringResult);
    assertTrue(actualTasks.isEmpty());
    assertSame(tasks, actualTasks);
  }

  /**
   * Test {@link TasksJson#writeToFile(List, Path)}.
   * <p>
   * Method under test: {@link TasksJson#writeToFile(List, Path)}
   */
  @Test
  @DisplayName("Test writeToFile(List, Path)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void TasksJson.writeToFile(List, Path)"})
  void testWriteToFile() throws IOException {
    try (MockedStatic<Files> mockFiles = mockStatic(Files.class)) {

      // Arrange
      mockFiles.when(() -> Files.write(Mockito.<Path>any(), Mockito.<byte[]>any(), isA(OpenOption[].class)))
          .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));

      // Act
      TasksJson.writeToFile(new ArrayList<>(), Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));

      // Assert
      mockFiles.verify(() -> Files.write(Mockito.<Path>any(), Mockito.<byte[]>any(), isA(OpenOption[].class)));
    }
  }

  /**
   * Test {@link TasksJson#writeToFile(List, Path)}.
   * <ul>
   *   <li>Given {@link Files} {@link Files#write(Path, byte[], OpenOption[])} throw {@link IOException#IOException(String)} with {@code foo}.</li>
   *   <li>Then throw {@link IOException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TasksJson#writeToFile(List, Path)}
   */
  @Test
  @DisplayName("Test writeToFile(List, Path); given Files write(Path, byte[], OpenOption[]) throw IOException(String) with 'foo'; then throw IOException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void TasksJson.writeToFile(List, Path)"})
  void testWriteToFile_givenFilesWriteThrowIOExceptionWithFoo_thenThrowIOException() throws IOException {
    try (MockedStatic<Files> mockFiles = mockStatic(Files.class)) {

      // Arrange
      mockFiles.when(() -> Files.write(Mockito.<Path>any(), Mockito.<byte[]>any(), isA(OpenOption[].class)))
          .thenThrow(new IOException("foo"));

      // Act and Assert
      assertThrows(IOException.class,
          () -> TasksJson.writeToFile(new ArrayList<>(), Paths.get(System.getProperty("java.io.tmpdir"), "test.txt")));
      mockFiles.verify(() -> Files.write(Mockito.<Path>any(), Mockito.<byte[]>any(), isA(OpenOption[].class)));
    }
  }

  /**
   * Test {@link TasksJson#from(List)}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()}.</li>
   *   <li>When {@link ArrayList#ArrayList()} addAll {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TasksJson#from(List)}
   */
  @Test
  @DisplayName("Test from(List); given ArrayList(); when ArrayList() addAll ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String TasksJson.from(List)"})
  void testFrom_givenArrayList_whenArrayListAddAllArrayList() {
    // Arrange
    ArrayList<RunTaskResponse> responses = new ArrayList<>();
    responses.addAll(new ArrayList<>());

    // Act and Assert
    assertEquals("{}", TasksJson.from(responses));
  }

  /**
   * Test {@link TasksJson#from(List)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TasksJson#from(List)}
   */
  @Test
  @DisplayName("Test from(List); when ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String TasksJson.from(List)"})
  void testFrom_whenArrayList() {
    // Arrange, Act and Assert
    assertEquals("{}", TasksJson.from(new ArrayList<>()));
  }
}
