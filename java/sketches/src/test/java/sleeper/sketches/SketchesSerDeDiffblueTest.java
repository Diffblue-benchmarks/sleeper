package sleeper.sketches;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.apache.datasketches.memory.Memory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.sketches.SketchesSerDe.ArrayOfByteArraysSerSe;

class SketchesSerDeDiffblueTest {
  /**
   * Test ArrayOfByteArraysSerSe {@link ArrayOfByteArraysSerSe#deserializeFromMemory(Memory, int)}.
   * <ul>
   *   <li>Then return array length is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link ArrayOfByteArraysSerSe#deserializeFromMemory(Memory, int)}
   */
  @Test
  @DisplayName("Test ArrayOfByteArraysSerSe deserializeFromMemory(Memory, int); then return array length is zero")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"com.facebook.collections.ByteArray[] ArrayOfByteArraysSerSe.deserializeFromMemory(Memory, int)"})
  void testArrayOfByteArraysSerSeDeserializeFromMemory_thenReturnArrayLengthIsZero() {
    // Arrange, Act and Assert
    assertEquals(0, (new ArrayOfByteArraysSerSe()).deserializeFromMemory(mock(Memory.class), 0).length);
  }
}
