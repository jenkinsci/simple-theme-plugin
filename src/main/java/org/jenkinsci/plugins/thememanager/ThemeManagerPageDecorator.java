package org.jenkinsci.plugins.thememanager;

import hudson.Extension;
import hudson.ExtensionList;
import hudson.model.PageDecorator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.kohsuke.stapler.Ancestor;
import org.kohsuke.stapler.DataBoundSetter;
import org.kohsuke.stapler.Stapler;
import org.kohsuke.stapler.StaplerRequest;

@Extension
public class ThemeManagerPageDecorator extends PageDecorator {

  private ThemeManagerFactory theme;
  private boolean userThemeAllowed;

  private static final String CSS_HTML =
      "<link type=\"text/css\" rel=\"stylesheet\" href=\"{0}\"/>";

  public ThemeManagerPageDecorator() {
    load();
  }

  public static ThemeManagerPageDecorator get() {
    return ExtensionList.lookupSingleton(ThemeManagerPageDecorator.class);
  }

  @Override
  public boolean configure(StaplerRequest req, JSONObject formData) {
    req.bindJSON(this, formData);
    save();
    return true;
  }

  @DataBoundSetter
  public void setTheme(ThemeManagerFactory theme) {
    this.theme = theme;
  }

  public ThemeManagerFactory getTheme() {
    return theme;
  }

  public boolean isUserThemeAllowed() {
    return userThemeAllowed;
  }

  @DataBoundSetter
  public void setUserThemeAllowed(boolean userThemeAllowed) {
    this.userThemeAllowed = userThemeAllowed;
  }

  /** Get the complete header HTML for all configured theme elements. */
  public String getHeaderHtml() {
    if (userThemeAllowed) {
      Theme theme = ThemeUserProperty.forCurrentUser();
      if (theme != null) {
        boolean injectCss = shouldInjectCss();
        Set<String> data = new LinkedHashSet<>(theme.generateHeaderElements(injectCss));
        return StringUtils.join(data, "\n");
      }
    }

    if (theme != null) {
      boolean injectCss = shouldInjectCss();
      Set<String> data = new LinkedHashSet<>(theme.getTheme().generateHeaderElements(injectCss));
      return StringUtils.join(data, "\n");
    }

    return null;
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
