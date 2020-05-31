package org.jenkinsci.plugins.thememanager;

import hudson.Extension;
import hudson.ExtensionList;
import hudson.model.PageDecorator;
import java.util.LinkedHashSet;
import java.util.Set;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.codefirst.SimpleThemeDecorator;
import org.jenkinsci.Symbol;
import org.kohsuke.stapler.DataBoundSetter;
import org.kohsuke.stapler.StaplerRequest;

@Extension
@Symbol("themeManager")
public class ThemeManagerPageDecorator extends PageDecorator {

  private ThemeManagerFactory theme;
  private boolean disableUserThemes;

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

  public boolean isDisableUserThemes() {
    return disableUserThemes;
  }

  @DataBoundSetter
  public void setDisableUserThemes(boolean disableUserThemes) {
    this.disableUserThemes = disableUserThemes;
  }

  /** Get the complete header HTML for all configured theme elements. */
  public String getHeaderHtml() {
    if (!disableUserThemes) {
      Theme theme = ThemeUserProperty.forCurrentUser();
      if (theme != null) {
        boolean injectCss = SimpleThemeDecorator.shouldInjectCss();
        Set<String> data = new LinkedHashSet<>(theme.generateHeaderElements(injectCss));
        return StringUtils.join(data, "\n");
      }
    }

    if (theme != null) {
      boolean injectCss = SimpleThemeDecorator.shouldInjectCss();
      Set<String> data = new LinkedHashSet<>(theme.getTheme().generateHeaderElements(injectCss));
      return StringUtils.join(data, "\n");
    }

    return null;
  }
}
