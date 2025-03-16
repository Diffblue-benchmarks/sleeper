package sleeper.build.github.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.MappingJsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.DefaultDeserializationContext;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker.Std;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import com.fasterxml.jackson.databind.jsontype.impl.StdSubtypeResolver;
import com.fasterxml.jackson.databind.ser.BeanSerializerFactory;
import com.fasterxml.jackson.databind.ser.DefaultSerializerProvider;
import com.fasterxml.jackson.databind.ser.DefaultSerializerProvider.Impl;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class JacksonProviderDiffblueTest {
  /**
   * Test {@link JacksonProvider#getContext(Class)}.
   * <p>
   * Method under test: {@link JacksonProvider#getContext(Class)}
   */
  @Test
  @DisplayName("Test getContext(Class)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"ObjectMapper JacksonProvider.getContext(Class)"})
  void testGetContext() {
    // Arrange
    JacksonProvider jacksonProvider = new JacksonProvider();
    Class<Object> type = Object.class;

    // Act
    ObjectMapper actualContext = jacksonProvider.getContext(type);

    // Assert
    JsonFactory factory = actualContext.getFactory();
    assertTrue(factory instanceof MappingJsonFactory);
    assertTrue(actualContext.getDeserializationContext() instanceof DefaultDeserializationContext.Impl);
    assertTrue(actualContext.getVisibilityChecker() instanceof Std);
    assertTrue(actualContext.getPolymorphicTypeValidator() instanceof LaissezFaireSubTypeValidator);
    assertTrue(actualContext.getSubtypeResolver() instanceof StdSubtypeResolver);
    assertTrue(actualContext.getSerializerFactory() instanceof BeanSerializerFactory);
    assertTrue(actualContext.getSerializerProvider() instanceof Impl);
    assertTrue(actualContext.getSerializerProviderInstance() instanceof Impl);
    assertTrue(actualContext.getDateFormat() instanceof StdDateFormat);
    assertNull(actualContext.getInjectableValues());
    assertNull(actualContext.getPropertyNamingStrategy());
    assertEquals(1, actualContext.getRegisteredModuleIds().size());
    assertSame(factory, actualContext.getJsonFactory());
  }

  /**
   * Test {@link JacksonProvider#mapper()}.
   * <p>
   * Method under test: {@link JacksonProvider#mapper()}
   */
  @Test
  @DisplayName("Test mapper()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"ObjectMapper JacksonProvider.mapper()"})
  void testMapper() {
    // Arrange and Act
    ObjectMapper actualMapperResult = JacksonProvider.mapper();

    // Assert
    JsonFactory factory = actualMapperResult.getFactory();
    assertTrue(factory instanceof MappingJsonFactory);
    assertTrue(actualMapperResult.getDeserializationContext() instanceof DefaultDeserializationContext.Impl);
    assertTrue(actualMapperResult.getVisibilityChecker() instanceof Std);
    assertTrue(actualMapperResult.getPolymorphicTypeValidator() instanceof LaissezFaireSubTypeValidator);
    assertTrue(actualMapperResult.getSubtypeResolver() instanceof StdSubtypeResolver);
    assertTrue(actualMapperResult.getSerializerFactory() instanceof BeanSerializerFactory);
    assertTrue(actualMapperResult.getSerializerProvider() instanceof Impl);
    assertTrue(actualMapperResult.getSerializerProviderInstance() instanceof Impl);
    assertTrue(actualMapperResult.getDateFormat() instanceof StdDateFormat);
    assertNull(actualMapperResult.getInjectableValues());
    assertNull(actualMapperResult.getPropertyNamingStrategy());
    assertEquals(1, actualMapperResult.getRegisteredModuleIds().size());
    assertSame(factory, actualMapperResult.getJsonFactory());
  }
}
