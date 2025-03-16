package sleeper.core.properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.properties.instance.ArrayListIngestProperty;
import sleeper.core.properties.instance.InstancePropertyGroup;
import sleeper.core.properties.instance.UserDefinedInstanceProperty;

class SleeperPropertyIndexDiffblueTest {
  /**
   * Test {@link SleeperPropertyIndex#add(SleeperProperty)}.
   * <ul>
   *   <li>Given {@link SleeperPropertyIndex} (default constructor).</li>
   *   <li>Then {@link SleeperPropertyIndex} (default constructor) All size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyIndex#add(SleeperProperty)}
   */
  @Test
  @DisplayName("Test add(SleeperProperty); given SleeperPropertyIndex (default constructor); then SleeperPropertyIndex (default constructor) All size is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SleeperPropertyIndex.add(SleeperProperty)"})
  void testAdd_givenSleeperPropertyIndex_thenSleeperPropertyIndexAllSizeIsOne() {
    // Arrange
    SleeperPropertyIndex<SleeperProperty> sleeperPropertyIndex = new SleeperPropertyIndex<>();
    UserDefinedInstanceProperty userDefinedInstanceProperty = ArrayListIngestProperty.MAX_IN_MEMORY_BATCH_SIZE;

    // Act
    sleeperPropertyIndex.add(userDefinedInstanceProperty);

    // Assert
    List<SleeperProperty> all = sleeperPropertyIndex.getAll();
    assertEquals(1, all.size());
    assertSame(userDefinedInstanceProperty, all.get(0));
  }

  /**
   * Test {@link SleeperPropertyIndex#add(SleeperProperty)}.
   * <ul>
   *   <li>Then {@link SleeperPropertyIndex} (default constructor) All size is two.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyIndex#add(SleeperProperty)}
   */
  @Test
  @DisplayName("Test add(SleeperProperty); then SleeperPropertyIndex (default constructor) All size is two")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SleeperPropertyIndex.add(SleeperProperty)"})
  void testAdd_thenSleeperPropertyIndexAllSizeIsTwo() {
    // Arrange
    SleeperPropertyIndex<SleeperProperty> sleeperPropertyIndex = new SleeperPropertyIndex<>();
    sleeperPropertyIndex.add(ArrayListIngestProperty.MAX_IN_MEMORY_BATCH_SIZE);
    UserDefinedInstanceProperty userDefinedInstanceProperty = ArrayListIngestProperty.MAX_IN_MEMORY_BATCH_SIZE;

    // Act
    sleeperPropertyIndex.add(userDefinedInstanceProperty);

    // Assert
    List<SleeperProperty> all = sleeperPropertyIndex.getAll();
    assertEquals(2, all.size());
    assertSame(userDefinedInstanceProperty, all.get(0));
    assertSame(userDefinedInstanceProperty, all.get(1));
  }

  /**
   * Test {@link SleeperPropertyIndex#addAll(Collection)}.
   * <ul>
   *   <li>Given {@link ArrayListIngestProperty#MAX_IN_MEMORY_BATCH_SIZE}.</li>
   *   <li>Then {@link SleeperPropertyIndex} (default constructor) All is {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyIndex#addAll(Collection)}
   */
  @Test
  @DisplayName("Test addAll(Collection); given MAX_IN_MEMORY_BATCH_SIZE; then SleeperPropertyIndex (default constructor) All is ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SleeperPropertyIndex.addAll(Collection)"})
  void testAddAll_givenMax_in_memory_batch_size_thenSleeperPropertyIndexAllIsArrayList() {
    // Arrange
    SleeperPropertyIndex<SleeperProperty> sleeperPropertyIndex = new SleeperPropertyIndex<>();

    ArrayList<SleeperProperty> properties = new ArrayList<>();
    properties.add(ArrayListIngestProperty.MAX_IN_MEMORY_BATCH_SIZE);
    properties.add(ArrayListIngestProperty.MAX_IN_MEMORY_BATCH_SIZE);

    // Act
    sleeperPropertyIndex.addAll(properties);

    // Assert
    assertEquals(properties, sleeperPropertyIndex.getAll());
    assertEquals(properties, sleeperPropertyIndex.getUserDefined());
  }

  /**
   * Test {@link SleeperPropertyIndex#addAll(Collection)}.
   * <ul>
   *   <li>Given {@link ArrayListIngestProperty#MAX_IN_MEMORY_BATCH_SIZE}.</li>
   *   <li>Then {@link SleeperPropertyIndex} (default constructor) All size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyIndex#addAll(Collection)}
   */
  @Test
  @DisplayName("Test addAll(Collection); given MAX_IN_MEMORY_BATCH_SIZE; then SleeperPropertyIndex (default constructor) All size is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SleeperPropertyIndex.addAll(Collection)"})
  void testAddAll_givenMax_in_memory_batch_size_thenSleeperPropertyIndexAllSizeIsOne() {
    // Arrange
    SleeperPropertyIndex<SleeperProperty> sleeperPropertyIndex = new SleeperPropertyIndex<>();

    ArrayList<SleeperProperty> properties = new ArrayList<>();
    properties.add(ArrayListIngestProperty.MAX_IN_MEMORY_BATCH_SIZE);

    // Act
    sleeperPropertyIndex.addAll(properties);

    // Assert
    assertEquals(1, sleeperPropertyIndex.getAll().size());
    assertEquals(1, sleeperPropertyIndex.getUserDefined().size());
  }

  /**
   * Test {@link SleeperPropertyIndex#addAll(Collection)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then {@link SleeperPropertyIndex} (default constructor) All Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link SleeperPropertyIndex#addAll(Collection)}
   */
  @Test
  @DisplayName("Test addAll(Collection); when ArrayList(); then SleeperPropertyIndex (default constructor) All Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SleeperPropertyIndex.addAll(Collection)"})
  void testAddAll_whenArrayList_thenSleeperPropertyIndexAllEmpty() {
    // Arrange
    SleeperPropertyIndex<SleeperProperty> sleeperPropertyIndex = new SleeperPropertyIndex<>();

    // Act
    sleeperPropertyIndex.addAll(new ArrayList<>());

    // Assert that nothing has changed
    assertTrue(sleeperPropertyIndex.getAll().isEmpty());
    assertTrue(sleeperPropertyIndex.getUserDefined().isEmpty());
  }

  /**
   * Test {@link SleeperPropertyIndex#getAll()}.
   * <p>
   * Method under test: {@link SleeperPropertyIndex#getAll()}
   */
  @Test
  @DisplayName("Test getAll()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List SleeperPropertyIndex.getAll()"})
  void testGetAll() {
    // Arrange
    SleeperPropertyIndex<SleeperProperty> sleeperPropertyIndex = new SleeperPropertyIndex<>();

    // Act and Assert
    assertTrue(sleeperPropertyIndex.getAll().isEmpty());
  }

  /**
   * Test {@link SleeperPropertyIndex#getUserDefined()}.
   * <p>
   * Method under test: {@link SleeperPropertyIndex#getUserDefined()}
   */
  @Test
  @DisplayName("Test getUserDefined()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List SleeperPropertyIndex.getUserDefined()"})
  void testGetUserDefined() {
    // Arrange
    SleeperPropertyIndex<SleeperProperty> sleeperPropertyIndex = new SleeperPropertyIndex<>();

    // Act and Assert
    assertTrue(sleeperPropertyIndex.getUserDefined().isEmpty());
  }

  /**
   * Test {@link SleeperPropertyIndex#getCdkDefined()}.
   * <p>
   * Method under test: {@link SleeperPropertyIndex#getCdkDefined()}
   */
  @Test
  @DisplayName("Test getCdkDefined()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List SleeperPropertyIndex.getCdkDefined()"})
  void testGetCdkDefined() {
    // Arrange
    SleeperPropertyIndex<SleeperProperty> sleeperPropertyIndex = new SleeperPropertyIndex<>();

    // Act and Assert
    assertTrue(sleeperPropertyIndex.getCdkDefined().isEmpty());
  }

  /**
   * Test {@link SleeperPropertyIndex#getByName(String)}.
   * <p>
   * Method under test: {@link SleeperPropertyIndex#getByName(String)}
   */
  @Test
  @DisplayName("Test getByName(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.util.Optional SleeperPropertyIndex.getByName(String)"})
  void testGetByName() {
    // Arrange
    SleeperPropertyIndex<SleeperProperty> sleeperPropertyIndex = new SleeperPropertyIndex<>();

    // Act and Assert
    assertFalse(sleeperPropertyIndex.getByName("Property Name").isPresent());
  }

  /**
   * Test {@link SleeperPropertyIndex#getAllInGroup(PropertyGroup)}.
   * <p>
   * Method under test: {@link SleeperPropertyIndex#getAllInGroup(PropertyGroup)}
   */
  @Test
  @DisplayName("Test getAllInGroup(PropertyGroup)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List SleeperPropertyIndex.getAllInGroup(PropertyGroup)"})
  void testGetAllInGroup() {
    // Arrange
    SleeperPropertyIndex<SleeperProperty> sleeperPropertyIndex = new SleeperPropertyIndex<>();

    // Act and Assert
    assertTrue(sleeperPropertyIndex.getAllInGroup(InstancePropertyGroup.ATHENA).isEmpty());
  }

  /**
   * Test new {@link SleeperPropertyIndex} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link SleeperPropertyIndex}
   */
  @Test
  @DisplayName("Test new SleeperPropertyIndex (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SleeperPropertyIndex.<init>()"})
  void testNewSleeperPropertyIndex() {
    // Arrange and Act
    SleeperPropertyIndex<SleeperProperty> actualSleeperPropertyIndex = new SleeperPropertyIndex<>();

    // Assert
    assertTrue(actualSleeperPropertyIndex.getAll().isEmpty());
    assertTrue(actualSleeperPropertyIndex.getCdkDefined().isEmpty());
    assertTrue(actualSleeperPropertyIndex.getUserDefined().isEmpty());
  }
}
