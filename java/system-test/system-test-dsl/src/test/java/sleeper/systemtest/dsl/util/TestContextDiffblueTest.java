package sleeper.systemtest.dsl.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.systemtest.dsl.util.TestContext.Builder;

class TestContextDiffblueTest {
  /**
   * Test Builder {@link Builder#build()}.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Builder#build()}
   *   <li>{@link Builder#displayName(String)}
   *   <li>{@link Builder#tags(Set)}
   *   <li>{@link Builder#testClass(Class)}
   *   <li>{@link Builder#testMethod(Method)}
   * </ul>
   */
  @Test
  @DisplayName("Test Builder build()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"TestContext Builder.build()", "Builder Builder.displayName(String)", "Builder Builder.tags(Set)",
      "Builder Builder.testClass(Class)", "Builder Builder.testMethod(Method)"})
  void testBuilderBuild() {
    // Arrange
    Builder displayNameResult = TestContext.builder().displayName("Display Name");
    HashSet<String> tags = new HashSet<>();
    Builder tagsResult = displayNameResult.tags(tags);
    Class<Object> testClass = Object.class;

    // Act
    TestContext actualBuildResult = tagsResult.testClass(testClass).testMethod(null).build();

    // Assert
    assertEquals("Display Name", actualBuildResult.getDisplayName());
    assertFalse(actualBuildResult.getTestMethod().isPresent());
    Optional<Class<?>> testClass2 = actualBuildResult.getTestClass();
    assertTrue(testClass2.isPresent());
    Set<String> tags2 = actualBuildResult.getTags();
    assertTrue(tags2.isEmpty());
    Class<Object> expectedGetResult = Object.class;
    Class<?> getResult = testClass2.get();
    assertEquals(expectedGetResult, getResult);
    assertSame(tags, tags2);
    assertSame(testClass, getResult);
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link TestContext#getDisplayName()}
   *   <li>{@link TestContext#getTags()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String TestContext.getDisplayName()", "Set TestContext.getTags()"})
  void testGettersAndSetters() {
    // Arrange
    Builder displayNameResult = TestContext.builder().displayName("Display Name");
    HashSet<String> tags = new HashSet<>();
    Builder tagsResult = displayNameResult.tags(tags);
    Class<Object> testClass = Object.class;
    TestContext buildResult = tagsResult.testClass(testClass).testMethod(null).build();

    // Act
    String actualDisplayName = buildResult.getDisplayName();
    Set<String> actualTags = buildResult.getTags();

    // Assert
    assertEquals("Display Name", actualDisplayName);
    assertTrue(actualTags.isEmpty());
    assertSame(tags, actualTags);
  }

  /**
   * Test {@link TestContext#getTestClass()}.
   * <p>
   * Method under test: {@link TestContext#getTestClass()}
   */
  @Test
  @DisplayName("Test getTestClass()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Optional TestContext.getTestClass()"})
  void testGetTestClass() {
    // Arrange
    Builder displayNameResult = TestContext.builder().displayName("Display Name");
    Builder tagsResult = displayNameResult.tags(new HashSet<>());
    Class<Object> testClass = Object.class;
    TestContext buildResult = tagsResult.testClass(testClass).testMethod(null).build();

    // Act
    Optional<Class<?>> actualTestClass = buildResult.getTestClass();

    // Assert
    assertTrue(actualTestClass.isPresent());
    Class<Object> expectedGetResult = Object.class;
    assertEquals(expectedGetResult, actualTestClass.get());
  }

  /**
   * Test {@link TestContext#getTestMethod()}.
   * <p>
   * Method under test: {@link TestContext#getTestMethod()}
   */
  @Test
  @DisplayName("Test getTestMethod()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Optional TestContext.getTestMethod()"})
  void testGetTestMethod() {
    // Arrange
    Builder displayNameResult = TestContext.builder().displayName("Display Name");
    Builder tagsResult = displayNameResult.tags(new HashSet<>());
    Class<Object> testClass = Object.class;
    TestContext buildResult = tagsResult.testClass(testClass).testMethod(null).build();

    // Act and Assert
    assertFalse(buildResult.getTestMethod().isPresent());
  }
}
