package sleeper.systemtest.dsl.util;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sleeper.core.deploy.SleeperScheduleRule;
import sleeper.core.deploy.SleeperScheduleRule.InstanceRule;

class NoScheduleRulesDriverDiffblueTest {
  /**
   * Test {@link NoScheduleRulesDriver#enableRule(InstanceRule)}.
   * <p>
   * Method under test: {@link NoScheduleRulesDriver#enableRule(InstanceRule)}
   */
  @Test
  @DisplayName("Test enableRule(InstanceRule)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void NoScheduleRulesDriver.enableRule(InstanceRule)"})
  void testEnableRule() {
    // Arrange
    NoScheduleRulesDriver noScheduleRulesDriver = new NoScheduleRulesDriver();
    InstanceRule rule = mock(InstanceRule.class);
    when(rule.getRuleName()).thenReturn("Rule Name");

    // Act
    noScheduleRulesDriver.enableRule(rule);

    // Assert
    verify(rule).getRuleName();
  }

  /**
   * Test {@link NoScheduleRulesDriver#disableRule(InstanceRule)}.
   * <p>
   * Method under test: {@link NoScheduleRulesDriver#disableRule(InstanceRule)}
   */
  @Test
  @DisplayName("Test disableRule(InstanceRule)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void NoScheduleRulesDriver.disableRule(InstanceRule)"})
  void testDisableRule() {
    // Arrange
    NoScheduleRulesDriver noScheduleRulesDriver = new NoScheduleRulesDriver();
    InstanceRule rule = mock(InstanceRule.class);
    when(rule.getRuleName()).thenReturn("Rule Name");

    // Act
    noScheduleRulesDriver.disableRule(rule);

    // Assert
    verify(rule).getRuleName();
  }
}
