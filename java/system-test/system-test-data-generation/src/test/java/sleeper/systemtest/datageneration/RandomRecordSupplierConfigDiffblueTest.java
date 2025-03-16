package sleeper.systemtest.datageneration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MaintainedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.apache.commons.math3.random.ISAACRandom;
import org.apache.commons.math3.random.RandomGenerator;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;
import sleeper.systemtest.configuration.SystemTestProperties;
import sleeper.systemtest.configuration.SystemTestProperty;
import sleeper.systemtest.configuration.SystemTestPropertyValues;

public class RandomRecordSupplierConfigDiffblueTest {
  /**
   * Test {@link RandomRecordSupplierConfig#RandomRecordSupplierConfig(int, int, long, long, int, int, int, int, RandomGenerator)}.
   * <p>
   * Method under test: {@link RandomRecordSupplierConfig#RandomRecordSupplierConfig(int, int, long, long, int, int, int, int, RandomGenerator)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({
      "void RandomRecordSupplierConfig.<init>(int, int, long, long, int, int, int, int, RandomGenerator)"})
  public void testNewRandomRecordSupplierConfig() {
    // Arrange
    ISAACRandom generator = new ISAACRandom();

    // Act
    RandomRecordSupplierConfig actualRandomRecordSupplierConfig = new RandomRecordSupplierConfig(1, 3, 1L, 1L, 3, 3, 3,
        3, generator);

    // Assert
    RandomGenerator generator2 = actualRandomRecordSupplierConfig.getGenerator();
    assertTrue(generator2 instanceof ISAACRandom);
    assertEquals(1, actualRandomRecordSupplierConfig.getMinRandomInt());
    assertEquals(1L, actualRandomRecordSupplierConfig.getMaxRandomLong());
    assertEquals(1L, actualRandomRecordSupplierConfig.getMinRandomLong());
    assertEquals(3, actualRandomRecordSupplierConfig.getMaxEntriesInRandomList());
    assertEquals(3, actualRandomRecordSupplierConfig.getMaxEntriesInRandomMap());
    assertEquals(3, actualRandomRecordSupplierConfig.getMaxRandomInt());
    assertEquals(3, actualRandomRecordSupplierConfig.getRandomByteArrayLength());
    assertEquals(3, actualRandomRecordSupplierConfig.getRandomStringLength());
    assertSame(generator, generator2);
  }

  /**
   * Test {@link RandomRecordSupplierConfig#RandomRecordSupplierConfig(SystemTestProperties)}.
   * <p>
   * Method under test: {@link RandomRecordSupplierConfig#RandomRecordSupplierConfig(SystemTestProperties)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void RandomRecordSupplierConfig.<init>(SystemTestProperties)"})
  public void testNewRandomRecordSupplierConfig2() {
    // Arrange and Act
    RandomRecordSupplierConfig actualRandomRecordSupplierConfig = new RandomRecordSupplierConfig(
        new SystemTestProperties());

    // Assert
    assertNull(actualRandomRecordSupplierConfig.getGenerator());
    assertEquals(0, actualRandomRecordSupplierConfig.getMinRandomInt());
    assertEquals(0L, actualRandomRecordSupplierConfig.getMinRandomLong());
    assertEquals(10, actualRandomRecordSupplierConfig.getMaxEntriesInRandomList());
    assertEquals(10, actualRandomRecordSupplierConfig.getMaxEntriesInRandomMap());
    assertEquals(10, actualRandomRecordSupplierConfig.getRandomByteArrayLength());
    assertEquals(10, actualRandomRecordSupplierConfig.getRandomStringLength());
    assertEquals(100000000, actualRandomRecordSupplierConfig.getMaxRandomInt());
    assertEquals(10000000000L, actualRandomRecordSupplierConfig.getMaxRandomLong());
  }

  /**
   * Test {@link RandomRecordSupplierConfig#RandomRecordSupplierConfig(SystemTestPropertyValues)}.
   * <p>
   * Method under test: {@link RandomRecordSupplierConfig#RandomRecordSupplierConfig(SystemTestPropertyValues)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void RandomRecordSupplierConfig.<init>(SystemTestPropertyValues)"})
  public void testNewRandomRecordSupplierConfig3() {
    // Arrange
    SystemTestPropertyValues systemTestProperties = mock(SystemTestPropertyValues.class);
    when(systemTestProperties.getInt(Mockito.<SystemTestProperty>any())).thenReturn(1);
    when(systemTestProperties.getLong(Mockito.<SystemTestProperty>any())).thenReturn(1L);

    // Act
    RandomRecordSupplierConfig actualRandomRecordSupplierConfig = new RandomRecordSupplierConfig(systemTestProperties);

    // Assert
    verify(systemTestProperties, atLeast(1)).getInt(Mockito.<SystemTestProperty>any());
    verify(systemTestProperties, atLeast(1)).getLong(Mockito.<SystemTestProperty>any());
    assertNull(actualRandomRecordSupplierConfig.getGenerator());
    assertEquals(1, actualRandomRecordSupplierConfig.getMaxEntriesInRandomList());
    assertEquals(1, actualRandomRecordSupplierConfig.getMaxEntriesInRandomMap());
    assertEquals(1, actualRandomRecordSupplierConfig.getMaxRandomInt());
    assertEquals(1, actualRandomRecordSupplierConfig.getMinRandomInt());
    assertEquals(1, actualRandomRecordSupplierConfig.getRandomByteArrayLength());
    assertEquals(1, actualRandomRecordSupplierConfig.getRandomStringLength());
    assertEquals(1L, actualRandomRecordSupplierConfig.getMaxRandomLong());
    assertEquals(1L, actualRandomRecordSupplierConfig.getMinRandomLong());
  }

  /**
   * Test {@link RandomRecordSupplierConfig#RandomRecordSupplierConfig(SystemTestPropertyValues, RandomGenerator)}.
   * <p>
   * Method under test: {@link RandomRecordSupplierConfig#RandomRecordSupplierConfig(SystemTestPropertyValues, RandomGenerator)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void RandomRecordSupplierConfig.<init>(SystemTestPropertyValues, RandomGenerator)"})
  public void testNewRandomRecordSupplierConfig4() {
    // Arrange
    SystemTestPropertyValues systemTestProperties = mock(SystemTestPropertyValues.class);
    when(systemTestProperties.getInt(Mockito.<SystemTestProperty>any())).thenReturn(1);
    when(systemTestProperties.getLong(Mockito.<SystemTestProperty>any())).thenReturn(1L);
    ISAACRandom generator = new ISAACRandom();

    // Act
    RandomRecordSupplierConfig actualRandomRecordSupplierConfig = new RandomRecordSupplierConfig(systemTestProperties,
        generator);

    // Assert
    verify(systemTestProperties, atLeast(1)).getInt(Mockito.<SystemTestProperty>any());
    verify(systemTestProperties, atLeast(1)).getLong(Mockito.<SystemTestProperty>any());
    RandomGenerator generator2 = actualRandomRecordSupplierConfig.getGenerator();
    assertTrue(generator2 instanceof ISAACRandom);
    assertEquals(1, actualRandomRecordSupplierConfig.getMaxEntriesInRandomList());
    assertEquals(1, actualRandomRecordSupplierConfig.getMaxEntriesInRandomMap());
    assertEquals(1, actualRandomRecordSupplierConfig.getMaxRandomInt());
    assertEquals(1, actualRandomRecordSupplierConfig.getMinRandomInt());
    assertEquals(1, actualRandomRecordSupplierConfig.getRandomByteArrayLength());
    assertEquals(1, actualRandomRecordSupplierConfig.getRandomStringLength());
    assertEquals(1L, actualRandomRecordSupplierConfig.getMaxRandomLong());
    assertEquals(1L, actualRandomRecordSupplierConfig.getMinRandomLong());
    assertSame(generator, generator2);
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link RandomRecordSupplierConfig#getGenerator()}
   *   <li>{@link RandomRecordSupplierConfig#getMaxEntriesInRandomList()}
   *   <li>{@link RandomRecordSupplierConfig#getMaxEntriesInRandomMap()}
   *   <li>{@link RandomRecordSupplierConfig#getMaxRandomInt()}
   *   <li>{@link RandomRecordSupplierConfig#getMaxRandomLong()}
   *   <li>{@link RandomRecordSupplierConfig#getMinRandomInt()}
   *   <li>{@link RandomRecordSupplierConfig#getMinRandomLong()}
   *   <li>{@link RandomRecordSupplierConfig#getRandomByteArrayLength()}
   *   <li>{@link RandomRecordSupplierConfig#getRandomStringLength()}
   * </ul>
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"RandomGenerator RandomRecordSupplierConfig.getGenerator()",
      "int RandomRecordSupplierConfig.getMaxEntriesInRandomList()",
      "int RandomRecordSupplierConfig.getMaxEntriesInRandomMap()", "int RandomRecordSupplierConfig.getMaxRandomInt()",
      "long RandomRecordSupplierConfig.getMaxRandomLong()", "int RandomRecordSupplierConfig.getMinRandomInt()",
      "long RandomRecordSupplierConfig.getMinRandomLong()", "int RandomRecordSupplierConfig.getRandomByteArrayLength()",
      "int RandomRecordSupplierConfig.getRandomStringLength()"})
  public void testGettersAndSetters() {
    // Arrange
    RandomRecordSupplierConfig randomRecordSupplierConfig = new RandomRecordSupplierConfig(new SystemTestProperties());

    // Act
    RandomGenerator actualGenerator = randomRecordSupplierConfig.getGenerator();
    int actualMaxEntriesInRandomList = randomRecordSupplierConfig.getMaxEntriesInRandomList();
    int actualMaxEntriesInRandomMap = randomRecordSupplierConfig.getMaxEntriesInRandomMap();
    int actualMaxRandomInt = randomRecordSupplierConfig.getMaxRandomInt();
    long actualMaxRandomLong = randomRecordSupplierConfig.getMaxRandomLong();
    int actualMinRandomInt = randomRecordSupplierConfig.getMinRandomInt();
    long actualMinRandomLong = randomRecordSupplierConfig.getMinRandomLong();
    int actualRandomByteArrayLength = randomRecordSupplierConfig.getRandomByteArrayLength();

    // Assert
    assertNull(actualGenerator);
    assertEquals(0, actualMinRandomInt);
    assertEquals(0L, actualMinRandomLong);
    assertEquals(10, actualMaxEntriesInRandomList);
    assertEquals(10, actualMaxEntriesInRandomMap);
    assertEquals(10, actualRandomByteArrayLength);
    assertEquals(10, randomRecordSupplierConfig.getRandomStringLength());
    assertEquals(100000000, actualMaxRandomInt);
    assertEquals(10000000000L, actualMaxRandomLong);
  }
}
