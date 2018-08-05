package org.jenkinsci.plugins.simpletheme;

import hudson.util.VersionNumber;
import jenkins.model.Jenkins;
import org.codefirst.SimpleThemeDecorator;
import org.jenkinsci.plugins.casc.misc.ConfiguredWithCode;
import org.jenkinsci.plugins.casc.misc.JenkinsConfiguredWithCodeRule;
import org.junit.Assume;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class ConfigurationAsCodeTest {
    @Rule
    public JenkinsConfiguredWithCodeRule j = new JenkinsConfiguredWithCodeRule();

    @BeforeClass
    public static void guardOlderJenkins() {
        // Configuration-as-Code requires Jenkins 2.60.3
        Assume.assumeTrue(Jenkins.getVersion().isNewerThan(new VersionNumber("2.60.3")));
    }

    @Test
    @ConfiguredWithCode("ConfigurationAsCode.yml")
    public void testConfig() {
        SimpleThemeDecorator decorator = j.jenkins.getDescriptorByType(SimpleThemeDecorator.class);

        assertNotNull(decorator.getElements());
        assertThat(decorator.getElements(), hasSize(4));
        assertThat(decorator.getElements(),
                containsInAnyOrder(
                        hasProperty("url", containsString("test.css")),
                        hasProperty("text", containsString(".testcss")),
                        hasProperty("url", containsString("test.js")),
                        hasProperty("url", containsString("Favicon.ico"))
                )
        );

    }
}
