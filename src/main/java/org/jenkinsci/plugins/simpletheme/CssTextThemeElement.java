package org.jenkinsci.plugins.simpletheme;

import hudson.Extension;
import hudson.model.Descriptor;
import org.jenkinsci.Symbol;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.DataBoundSetter;

import java.text.MessageFormat;
import java.util.Set;

public class CssTextThemeElement extends ThemeElement {
    private static final String CSS_HTML = "<style type=\"text/css\"><![CDATA[\n{0}\n]]></style>";

    private String text;

    @DataBoundConstructor
    public CssTextThemeElement (String url) {
        setText(url);
    }

    @DataBoundSetter
    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    @Override
    public void collectHeaderFragment(Set<String> fragments, boolean injectCss) {
        fragments.add(MessageFormat.format(CSS_HTML, getText()));
    }


    @Extension
    @Symbol("cssText")
    public static final class DescriptorImpl extends Descriptor<ThemeElement> {

        @Override
        public String getDisplayName() {
            return Messages.cssText();
        }
    }
}
