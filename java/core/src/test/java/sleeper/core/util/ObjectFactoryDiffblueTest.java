package sleeper.core.util;

import static org.junit.jupiter.api.Assertions.assertThrows;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class ObjectFactoryDiffblueTest {
  /**
   * Test {@link ObjectFactory#getObject(String, Class)}.
   * <ul>
   *   <li>Given noUserJars.</li>
   *   <li>When {@code Class Name}.</li>
   *   <li>Then throw {@link ObjectFactoryException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ObjectFactory#getObject(String, Class)}
   */
  @Test
  @DisplayName("Test getObject(String, Class); given noUserJars; when 'Class Name'; then throw ObjectFactoryException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Object ObjectFactory.getObject(String, Class)"})
  void testGetObject_givenNoUserJars_whenClassName_thenThrowObjectFactoryException() throws ObjectFactoryException {
    // Arrange
    ObjectFactory noUserJarsResult = ObjectFactory.noUserJars();
    Class<Object> parentClass = Object.class;

    // Act and Assert
    assertThrows(ObjectFactoryException.class, () -> noUserJarsResult.getObject("Class Name", parentClass));
  }

  /**
   * Test {@link ObjectFactory#getObject(String, Class)}.
   * <ul>
   *   <li>When {@code ObjectFactory}.</li>
   *   <li>Then throw {@link ObjectFactoryException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ObjectFactory#getObject(String, Class)}
   */
  @Test
  @DisplayName("Test getObject(String, Class); when 'sleeper.core.util.ObjectFactory'; then throw ObjectFactoryException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Object ObjectFactory.getObject(String, Class)"})
  void testGetObject_whenSleeperCoreUtilObjectFactory_thenThrowObjectFactoryException() throws ObjectFactoryException {
    // Arrange
    ObjectFactory noUserJarsResult = ObjectFactory.noUserJars();
    Class<Object> parentClass = Object.class;

    // Act and Assert
    assertThrows(ObjectFactoryException.class,
        () -> noUserJarsResult.getObject("sleeper.core.util.ObjectFactory", parentClass));
  }
}
