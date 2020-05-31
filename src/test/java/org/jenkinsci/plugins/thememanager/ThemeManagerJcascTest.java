package org.jenkinsci.plugins.thememanager;

import static io.jenkins.plugins.casc.misc.Util.getUnclassifiedRoot;
import static io.jenkins.plugins.casc.misc.Util.toStringFromYamlFile;
import static io.jenkins.plugins.casc.misc.Util.toYamlString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;

import io.jenkins.plugins.casc.ConfigurationContext;
import io.jenkins.plugins.casc.ConfiguratorRegistry;
import io.jenkins.plugins.casc.misc.ConfiguredWithCode;
import io.jenkins.plugins.casc.misc.JenkinsConfiguredWithCodeRule;
import io.jenkins.plugins.casc.model.CNode;
import org.jenkinsci.plugins.thememanager.none.NoOpThemeManagerFactory;
import org.junit.ClassRule;
import org.junit.Test;

public class ThemeManagerJcascTest {

  @ClassRule
  @ConfiguredWithCode("ConfigurationAsCode.yml")
  public static JenkinsConfiguredWithCodeRule j = new JenkinsConfiguredWithCodeRule();

  @Test
  public void testConfig() {
    ThemeManagerPageDecorator decorator = ThemeManagerPageDecorator.get();

    ThemeManagerFactory theme = decorator.getTheme();
    assertNotNull(theme);

    assertThat(decorator.isDisableUserThemes(), is(true));
    assertThat(theme, instanceOf(NoOpThemeManagerFactory.class));
  }

  @Test
  public void testExport() throws Exception {
    ConfigurationContext context = new ConfigurationContext(ConfiguratorRegistry.get());
    CNode yourAttribute = getUnclassifiedRoot(context).get("themeManager");

    String exported = toYamlString(yourAttribute);

    String expected = toStringFromYamlFile(this, "ConfigurationAsCodeExport.yml");

    assertThat(exported, is(expected));
  }
}
