package org.jenkinsci.plugins.simpletheme;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;

import org.codefirst.SimpleThemeDecorator;
import org.junit.jupiter.api.Test;
import org.jvnet.hudson.test.JenkinsRule;
import org.jvnet.hudson.test.junit.jupiter.WithJenkins;
import org.jvnet.hudson.test.recipes.LocalData;

@WithJenkins
class ConfigurationMigrationTest {

    @Test
    @LocalData
    void testFrom04(JenkinsRule j) throws Exception {
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
    void testFrom04Empty(JenkinsRule j) throws Exception {
        j.configRoundtrip();
        j.waitUntilNoActivity();

        SimpleThemeDecorator decorator = j.jenkins.getDescriptorByType(SimpleThemeDecorator.class);

        assertThat(decorator.getElements(), hasSize(0));
    }
}
