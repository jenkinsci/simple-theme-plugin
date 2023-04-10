package org.jenkinsci.plugins.simpletheme;

import static io.jenkins.plugins.casc.misc.Util.getUnclassifiedRoot;
import static io.jenkins.plugins.casc.misc.Util.toStringFromYamlFile;
import static io.jenkins.plugins.casc.misc.Util.toYamlString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;

import io.jenkins.plugins.casc.ConfigurationContext;
import io.jenkins.plugins.casc.ConfiguratorRegistry;
import io.jenkins.plugins.casc.misc.ConfiguredWithCode;
import io.jenkins.plugins.casc.misc.JenkinsConfiguredWithCodeRule;
import io.jenkins.plugins.casc.model.CNode;
import org.codefirst.SimpleThemeDecorator;
import org.junit.ClassRule;
import org.junit.Test;

public class ConfigurationAsCodeTest {

    @ClassRule
    @ConfiguredWithCode("ConfigurationAsCode.yml")
    public static JenkinsConfiguredWithCodeRule j = new JenkinsConfiguredWithCodeRule();

    @Test
    public void testConfig() {
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

    @Test
    public void testExport() throws Exception {
        ConfigurationContext context = new ConfigurationContext(ConfiguratorRegistry.get());
        CNode yourAttribute = getUnclassifiedRoot(context).get("simple-theme-plugin");

        String exported = toYamlString(yourAttribute);

        String expected = toStringFromYamlFile(this, "ConfigurationAsCodeExport.yml");

        assertThat(exported, is(expected));
    }
}
