package org.codefirst;

import hudson.Extension;
import hudson.model.PageDecorator;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.jenkinsci.Symbol;
import org.jenkinsci.plugins.simpletheme.CssTextThemeElement;
import org.jenkinsci.plugins.simpletheme.CssUrlThemeElement;
import org.jenkinsci.plugins.simpletheme.FaviconUrlThemeElement;
import org.jenkinsci.plugins.simpletheme.JsUrlThemeElement;
import org.jenkinsci.plugins.simpletheme.ThemeElement;
import org.kohsuke.stapler.Ancestor;
import org.kohsuke.stapler.DataBoundSetter;
import org.kohsuke.stapler.Stapler;
import org.kohsuke.stapler.StaplerRequest;

@Extension
@Symbol("simple-theme-plugin")
public class SimpleThemeDecorator extends PageDecorator {

  private List<ThemeElement> elements = new ArrayList<>();

  private transient String cssUrl;
  private transient String cssRules;
  private transient String jsUrl;
  private transient String faviconUrl;

  public SimpleThemeDecorator() {
    super();
    load();
  }

  @Override
  public boolean configure(StaplerRequest req, JSONObject formData) throws FormException {
    req.bindJSON(this, formData);
    save();
    return true;
  }

  public List<ThemeElement> getElements() {
    return elements;
  }

  @DataBoundSetter
  public void setElements(List<ThemeElement> elements) {
    this.elements = elements;
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

  protected Object readResolve() {
    if (StringUtils.isNotBlank(cssUrl)) {
      elements.add(new CssUrlThemeElement(cssUrl));
    }
    if (StringUtils.isNotBlank(cssRules)) {
      elements.add(new CssTextThemeElement(cssRules));
    }
    if (StringUtils.isNotBlank(jsUrl)) {
      elements.add(new JsUrlThemeElement(jsUrl));
    }
    if (StringUtils.isNotBlank(faviconUrl)) {
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
    return StringUtils.join(data, "\n");
  }

  /**
   * Filter to only inject CSS into "normal" Jenkins pages. Some plugins replace the whole layout of
   * Jenkins and we don't want to disturb them.
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
    return !o.getClass().getName().startsWith("com.smartcodeltd.jenkinsci.plugins.buildmonitor");
  }
}
