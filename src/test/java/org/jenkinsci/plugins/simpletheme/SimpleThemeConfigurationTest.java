package org.jenkinsci.plugins.simpletheme;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.htmlunit.WebAssert.assertElementPresentByXPath;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import org.codefirst.SimpleThemeDecorator;
import org.htmlunit.html.HtmlElement;
import org.htmlunit.html.HtmlForm;
import org.htmlunit.html.HtmlInput;
import org.htmlunit.html.HtmlPage;
import org.htmlunit.html.HtmlTextArea;
import org.junit.Rule;
import org.junit.Test;
import org.jvnet.hudson.test.JenkinsRule;

public class SimpleThemeConfigurationTest {

    @Rule
    public JenkinsRule j = new JenkinsRule();

    @Test
    public void testConfigRoundTrip() throws Exception {
        SimpleThemeDecorator decorator = j.jenkins.getDescriptorByType(SimpleThemeDecorator.class);
        fill(decorator);

        j.configRoundtrip();

        decorator = j.jenkins.getDescriptorByType(SimpleThemeDecorator.class);

        assertNotNull(decorator.getElements());
        assertThat(decorator.getElements(), hasSize(4));
    }

    @Test
    public void testConfigCreatesHtml() throws Exception {
        SimpleThemeDecorator decorator = j.jenkins.getDescriptorByType(SimpleThemeDecorator.class);
        fill(decorator);

        HtmlPage configPage = j.createWebClient().goTo("configure");
        HtmlForm form = configPage.getFormByName("config");

        urlThemeInput(form, CssUrlThemeElement.class).setValue("SOMECSSFILE.css");
        textThemeInput(form).setText("SOMECSS");
        urlThemeInput(form, JsUrlThemeElement.class).setValue("https://example.bogus/SOMEJS.js");
        urlThemeInput(form, FaviconUrlThemeElement.class).setValue("FAVICON.png");
        j.submit(form);

        HtmlPage page = j.createWebClient().goTo("");
        assertElementPresentByXPath(page, "//link[@href='SOMECSSFILE.css']");
        assertElementPresentByXPath(page, "//style[contains(text(),'SOMECSS')]");
        assertElementPresentByXPath(page, "//script[contains(@src,'SOMEJS.js')]");

        assertElementPresentByXPath(page, "//script[contains(@src,'simple-theme.js')]");
        assertElementPresentByXPath(page, "//script[contains(text(),'FAVICON.png')]");
    }

    private void fill(SimpleThemeDecorator decorator) {
        List<ThemeElement> configElements = new ArrayList<>();
        configElements.add(new CssTextThemeElement("DEFAULT"));
        configElements.add(new CssUrlThemeElement("DEFAULT.css"));
        configElements.add(new FaviconUrlThemeElement("DEFAULT.png"));
        configElements.add(new JsUrlThemeElement("https://example.bogus/DEFAULT.js"));
        decorator.setElements(configElements);
        decorator.save();
    }

    List<HtmlElement> heteroElements(HtmlElement parent, Class<? extends ThemeElement> element) {
        return parent.getElementsByAttribute("div", "descriptorid", element.getName());
    }

    HtmlInput urlThemeInput(HtmlElement parent, Class<? extends UrlThemeElement> element) {
        for (HtmlElement entry : heteroElements(parent, element)) {
            for (HtmlElement input : entry.getElementsByAttribute("input", "name", "_.url")) {
                return (HtmlInput) input;
            }
        }
        return null;
    }

    HtmlTextArea textThemeInput(HtmlElement parent) {
        for (HtmlElement entry : heteroElements(parent, CssTextThemeElement.class)) {
            for (HtmlElement input : entry.getElementsByAttribute("textarea", "name", "_.text")) {
                return (HtmlTextArea) input;
            }
        }
        return null;
    }
}
