package org.jenkinsci.plugins.simpletheme;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import io.jenkins.plugins.casc.ConfigurationAsCode;
import io.jenkins.plugins.casc.ConfiguratorException;
import org.codefirst.SimpleThemeDecorator;
import org.junit.Rule;
import org.junit.Test;
import org.jvnet.hudson.test.JenkinsRule;

public class ConfigurationAsCodeTest {

  @Rule public JenkinsRule j = new JenkinsRule();

  @Test
  public void testConfig() throws ConfiguratorException {
    ConfigurationAsCode.get()
        .configure(ConfigurationAsCodeTest.class.getResource("ConfigurationAsCode.yml").toString());

    SimpleThemeDecorator decorator = j.jenkins.getDescriptorByType(SimpleThemeDecorator.class);

    assertNotNull(decorator.getElements());
    assertThat(decorator.getElements(), hasSize(4));
    assertThat(
        decorator.getElements(),
        containsInAnyOrder(
            hasProperty("url", containsString("test.css")),
            hasProperty("text", containsString(".testcss")),
            hasProperty("url", containsString("test.js")),
            hasProperty("url", containsString("Favicon.ico"))));
  }
}
