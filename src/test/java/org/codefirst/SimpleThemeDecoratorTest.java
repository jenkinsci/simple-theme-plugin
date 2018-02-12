package org.codefirst;

import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.junit.Rule;
import org.junit.Test;
import org.jvnet.hudson.test.JenkinsRule;
import org.xml.sax.SAXException;

import java.io.IOException;

import static com.gargoylesoftware.htmlunit.WebAssert.assertElementPresentByXPath;
import static org.codehaus.groovy.runtime.DefaultGroovyMethods.last;

public class SimpleThemeDecoratorTest {
    @Rule
    public JenkinsRule j = new JenkinsRule();

    @Test
    public void testStuffIsInjected() throws IOException, SAXException {
        HtmlPage configPage = j.createWebClient().goTo("configure");
        HtmlForm form = configPage.getFormByName("config");
        form.getInputByName("_.cssUrl").setValueAttribute("SOMECSSFILE.css");
        form.getTextAreaByName("_.cssRules").setText("SOMECSS");
        form.getInputByName("_.jsUrl").setValueAttribute("https://example.bogus/SOMEJS.js");
        form.getInputByName("_.faviconUrl").setValueAttribute("FAVICON.png");
        last(form.getHtmlElementsByTagName("button")).click();

        HtmlPage page = j.createWebClient().goTo("");
        assertElementPresentByXPath(page, "//link[@href='SOMECSSFILE.css']");
        assertElementPresentByXPath(page, "//style[text()='SOMECSS']");
        assertElementPresentByXPath(page, "//script[contains(@src,'SOMEJS.js')]");

        assertElementPresentByXPath(page, "//script[contains(@src,'simple-theme.js')]");
        assertElementPresentByXPath(page, "//script[contains(text(),'FAVICON.png')]");
    }
}
