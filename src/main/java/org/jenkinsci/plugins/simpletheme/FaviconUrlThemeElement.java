package org.jenkinsci.plugins.simpletheme;

import hudson.Extension;
import hudson.model.Descriptor;
import java.text.MessageFormat;
import java.util.Set;
import jenkins.model.Jenkins;
import org.jenkinsci.Symbol;
import org.kohsuke.stapler.DataBoundConstructor;

public class FaviconUrlThemeElement extends UrlThemeElement {

    private static final String SCRIPT_INCLUDE =
            "<script id=\"simple-theme-script\" data-url=\"{0}\" src=\"{1}/plugin/simple-theme-plugin/simple-theme.js\"></script>";

    @DataBoundConstructor
    public FaviconUrlThemeElement(String url) {
        setUrl(url);
    }

    @Override
    public void collectHeaderFragment(Set<String> fragments, boolean injectCss) {
        fragments.add(
                MessageFormat.format(SCRIPT_INCLUDE, getUrl(), Jenkins.get().getRootUrlFromRequest()));
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
