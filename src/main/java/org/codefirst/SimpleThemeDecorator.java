package org.codefirst;

import hudson.Extension;
import hudson.model.PageDecorator;
import hudson.model.Descriptor;
import net.sf.json.JSONObject;
import org.jenkinsci.Symbol;
import org.kohsuke.stapler.Ancestor;
import org.kohsuke.stapler.Stapler;
import org.kohsuke.stapler.StaplerRequest;
import org.kohsuke.stapler.DataBoundSetter;

import java.util.List;

@Extension
@Symbol("simple-theme-plugin")
public class SimpleThemeDecorator extends PageDecorator {
    // CASC: PageDecorator extends Descriptor

    private String cssUrl;
    private String cssRules;
    private String jsUrl;
    private String faviconUrl;

    public SimpleThemeDecorator() {
        super();
        load();
    }

    @Override
    public boolean configure(StaplerRequest req, JSONObject formData)
            throws FormException {
        // reset values to default before data-binding
        this.cssUrl = null;
        this.cssRules = null;
        this.jsUrl = null;
        this.faviconUrl = null;

        req.bindJSON(this, formData);
        save();
        return true;
    }

    public String getCssUrl() {
        return cssUrl;
    }

    @DataBoundSetter
    public void setCssUrl(String cssUrl) {
        this.cssUrl = cssUrl;
    }

    public String getCssRules() {
        return cssRules;
    }

    @DataBoundSetter
    public void setCssRules(String cssRules) {
        this.cssRules = cssRules;
    }

    public String getJsUrl() {
        return jsUrl;
    }

    @DataBoundSetter
    public void setJsUrl(String jsUrl) {
        this.jsUrl = jsUrl;
    }

    public String getFaviconUrl() {
        return faviconUrl;
    }

    @DataBoundSetter
    public void setFaviconUrl(String faviconUrl) {
        this.faviconUrl = faviconUrl;
    }

    /**
     * Filter to only inject CSS into "normal" Jenkins pages. Some plugins
     * replace the whole layout of Jenkins and we don't want to disturb them.
     *
     * @return true if it is okay to inject CSS
     */
    public boolean shouldInjectCss() {
        StaplerRequest req = Stapler.getCurrentRequest();
        if (req == null) {
            return false;
        }

        List<Ancestor> ancestors = req.getAncestors();
        if (ancestors == null || ancestors.size() == 0) {
            return false;
        }

        Ancestor a = ancestors.get(ancestors.size() - 1);
        Object o = a.getObject();

        // We don't want to style the build-monitor-plugin
        if (o.getClass().getName().startsWith("com.smartcodeltd.jenkinsci.plugins.buildmonitor")) {
            return false;
        }

        return true;
    }

}
