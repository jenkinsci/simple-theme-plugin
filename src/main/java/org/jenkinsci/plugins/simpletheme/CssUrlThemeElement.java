package org.jenkinsci.plugins.simpletheme;

import hudson.Extension;
import hudson.model.Descriptor;
import org.jenkinsci.Symbol;
import org.kohsuke.stapler.DataBoundConstructor;

import java.text.MessageFormat;
import java.util.Set;

public class CssUrlThemeElement extends UrlThemeElement {

    private static final String CSS_HTML = "<link type=\"text/css\" rel=\"stylesheet\" href=\"{0}\"/>";

    @DataBoundConstructor
    public CssUrlThemeElement (String url) {
        setUrl(url);
    }

    @Override
    public void collectHeaderFragment(Set<String> fragments, boolean injectCss) {
        if (injectCss) {
            fragments.add(MessageFormat.format(CSS_HTML, getUrl()));
        }
    }

    @Extension
    @Symbol("cssUrl")
    public static final class DescriptorImpl extends Descriptor<ThemeElement> {

        @Override
        public String getDisplayName() {
            return Messages.cssURL();
        }
    }
}
