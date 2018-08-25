package org.jenkinsci.plugins.simpletheme;

import hudson.Extension;
import hudson.model.Descriptor;
import jenkins.model.Jenkins;
import org.jenkinsci.Symbol;
import org.kohsuke.stapler.DataBoundConstructor;

import java.text.MessageFormat;
import java.util.Set;

public class FaviconUrlThemeElement extends UrlThemeElement {
    private static final String SCRIPT_INCLUDE = "<script src=\"{0}/plugin/simple-theme-plugin/simple-theme.js\"></script>";
    private static final String FAVICON_SCRIPT = "<script>\n" +
            "window[''org.jenkinsci.plugins.simpletheme''].replaceFavicon(\"{0}\");\n" +
            "</script>";

    @DataBoundConstructor
    public FaviconUrlThemeElement (String url) {
        setUrl(url);
    }

    @Override
    public void collectHeaderFragment(Set<String> fragments, boolean injectCss) {
        fragments.add(MessageFormat.format(SCRIPT_INCLUDE, Jenkins.getActiveInstance().getRootUrl()));
        fragments.add(MessageFormat.format(FAVICON_SCRIPT, getUrl()));
    }

    @Extension
    @Symbol("faviconUrl")
    public static final class DescriptorImpl extends Descriptor<ThemeElement> {

        @Override
        public String getDisplayName() {
            return Messages.faviconURL();
        }
    }
}
