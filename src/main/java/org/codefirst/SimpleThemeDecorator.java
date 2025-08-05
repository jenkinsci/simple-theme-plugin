package org.codefirst;

import edu.umd.cs.findbugs.annotations.NonNull;
import hudson.Extension;
import hudson.model.PageDecorator;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import jenkins.appearance.AppearanceCategory;
import jenkins.model.GlobalConfigurationCategory;
import net.sf.json.JSONObject;
import org.jenkinsci.Symbol;
import org.jenkinsci.plugins.simpletheme.CssTextThemeElement;
import org.jenkinsci.plugins.simpletheme.CssUrlThemeElement;
import org.jenkinsci.plugins.simpletheme.FaviconUrlThemeElement;
import org.jenkinsci.plugins.simpletheme.JsUrlThemeElement;
import org.jenkinsci.plugins.simpletheme.ThemeElement;
import org.kohsuke.stapler.Ancestor;
import org.kohsuke.stapler.DataBoundSetter;
import org.kohsuke.stapler.Stapler;
import org.kohsuke.stapler.StaplerRequest2;

@Extension
@Symbol("simpleTheme")
public class SimpleThemeDecorator extends PageDecorator {

    private List<ThemeElement> elements = new ArrayList<>();

    @Deprecated
    private transient String cssUrl;

    @Deprecated
    private transient String cssRules;

    @Deprecated
    private transient String jsUrl;

    @Deprecated
    private transient String faviconUrl;

    public SimpleThemeDecorator() {
        super();
        load();
    }

    @Override
    public boolean configure(StaplerRequest2 req, JSONObject formData) throws FormException {
        elements.clear();
        req.bindJSON(this, formData);
        save();
        return true;
    }

    @NonNull
    @Override
    public GlobalConfigurationCategory getCategory() {
        return GlobalConfigurationCategory.get(AppearanceCategory.class);
    }

    public List<ThemeElement> getElements() {
        return elements;
    }

    @DataBoundSetter
    public void setElements(List<ThemeElement> elements) {
        this.elements = elements;
    }

    @Deprecated
    public String getCssUrl() {
        return cssUrl;
    }

    @DataBoundSetter
    @Deprecated
    public void setCssUrl(String cssUrl) {
        this.cssUrl = cssUrl;
    }

    @Deprecated
    public String getCssRules() {
        return cssRules;
    }

    @DataBoundSetter
    @Deprecated
    public void setCssRules(String cssRules) {
        this.cssRules = cssRules;
    }

    @Deprecated
    public String getJsUrl() {
        return jsUrl;
    }

    @DataBoundSetter
    @Deprecated
    public void setJsUrl(String jsUrl) {
        this.jsUrl = jsUrl;
    }

    @Deprecated
    public String getFaviconUrl() {
        return faviconUrl;
    }

    @DataBoundSetter
    @Deprecated
    public void setFaviconUrl(String faviconUrl) {
        this.faviconUrl = faviconUrl;
    }

    @SuppressWarnings("deprecation")
    protected Object readResolve() {
        if (cssUrl != null && !cssUrl.isBlank()) {
            elements.add(new CssUrlThemeElement(cssUrl));
        }
        if (cssRules != null && !cssRules.isBlank()) {
            elements.add(new CssTextThemeElement(cssRules));
        }
        if (jsUrl != null && !jsUrl.isBlank()) {
            elements.add(new JsUrlThemeElement(jsUrl));
        }
        if (faviconUrl != null && !faviconUrl.isBlank()) {
            elements.add(new FaviconUrlThemeElement(faviconUrl));
        }
        return this;
    }

    /** Get the complete header HTML for all configured theme elements. */
    public String getHeaderHtml() {
        Set<String> data = new LinkedHashSet<>();
        boolean injectCss = shouldInjectCss();
        for (ThemeElement element : elements) {
            element.collectHeaderFragment(data, injectCss);
        }
        return String.join("\n", data);
    }

    /**
     * Filter to only inject CSS into "normal" Jenkins pages. Some plugins replace the whole layout of
     * Jenkins and we don't want to disturb them.
     *
     * @return true if it is okay to inject CSS
     */
    public boolean shouldInjectCss() {
        StaplerRequest2 req = Stapler.getCurrentRequest2();
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
        return !o.getClass().getName().startsWith("com.smartcodeltd.jenkinsci.plugins.buildmonitor");
    }
}
