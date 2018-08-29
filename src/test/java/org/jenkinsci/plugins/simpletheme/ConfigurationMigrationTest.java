package org.jenkinsci.plugins.simpletheme;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import org.codefirst.SimpleThemeDecorator;
import org.junit.Rule;
import org.junit.Test;
import org.jvnet.hudson.test.JenkinsRule;
import org.jvnet.hudson.test.recipes.LocalData;

public class ConfigurationMigrationTest {
  @Rule public JenkinsRule j = new JenkinsRule();

  @Test
  @LocalData
  public void testFrom04() throws Exception {
    j.configRoundtrip();
    j.waitUntilNoActivity();

    SimpleThemeDecorator decorator = j.jenkins.getDescriptorByType(SimpleThemeDecorator.class);

    assertThat(decorator.getElements(), hasSize(4));
    assertThat(
        decorator.getElements(),
        containsInAnyOrder(
            hasProperty("url", containsString("OLD04.css")),
            hasProperty("text", equalTo("OLD04RULES")),
            hasProperty("url", containsString("OLD04.js")),
            hasProperty("url", equalTo("OLDFAV04.png"))));
  }

  @Test
  @LocalData
  public void testFrom04Empty() throws Exception {
    j.configRoundtrip();
    j.waitUntilNoActivity();

    SimpleThemeDecorator decorator = j.jenkins.getDescriptorByType(SimpleThemeDecorator.class);

    assertThat(decorator.getElements(), hasSize(0));
  }
}
