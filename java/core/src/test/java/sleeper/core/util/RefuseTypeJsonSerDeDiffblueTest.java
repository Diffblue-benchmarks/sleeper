package sleeper.core.util;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.fasterxml.jackson.databind.type.PlaceholderForType;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import java.lang.reflect.Type;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class RefuseTypeJsonSerDeDiffblueTest {
  /**
   * Test {@link RefuseTypeJsonSerDe#deserialize(JsonElement, Type, JsonDeserializationContext)}.
   * <ul>
   *   <li>Then throw {@link UnsupportedOperationException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RefuseTypeJsonSerDe#deserialize(JsonElement, Type, JsonDeserializationContext)}
   */
  @Test
  @DisplayName("Test deserialize(JsonElement, Type, JsonDeserializationContext); then throw UnsupportedOperationException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Object RefuseTypeJsonSerDe.deserialize(JsonElement, Type, JsonDeserializationContext)"})
  void testDeserialize_thenThrowUnsupportedOperationException() throws JsonParseException {
    // Arrange
    RefuseTypeJsonSerDe<Object> refuseTypeJsonSerDe = new RefuseTypeJsonSerDe<>();
    JsonArray json = new JsonArray(3);

    // Act and Assert
    assertThrows(UnsupportedOperationException.class,
        () -> refuseTypeJsonSerDe.deserialize(json, new PlaceholderForType(1), mock(JsonDeserializationContext.class)));
  }

  /**
   * Test {@link RefuseTypeJsonSerDe#serialize(Object, Type, JsonSerializationContext)}.
   * <ul>
   *   <li>Then throw {@link UnsupportedOperationException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RefuseTypeJsonSerDe#serialize(Object, Type, JsonSerializationContext)}
   */
  @Test
  @DisplayName("Test serialize(Object, Type, JsonSerializationContext); then throw UnsupportedOperationException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"JsonElement RefuseTypeJsonSerDe.serialize(Object, Type, JsonSerializationContext)"})
  void testSerialize_thenThrowUnsupportedOperationException() {
    // Arrange
    RefuseTypeJsonSerDe<Object> refuseTypeJsonSerDe = new RefuseTypeJsonSerDe<>();

    // Act and Assert
    assertThrows(UnsupportedOperationException.class,
        () -> refuseTypeJsonSerDe.serialize("Src", new PlaceholderForType(1), mock(JsonSerializationContext.class)));
  }
}
