package org.jenkinsci.plugins.thememanager;

import edu.umd.cs.findbugs.annotations.CheckForNull;
import edu.umd.cs.findbugs.annotations.NonNull;
import hudson.Extension;
import hudson.model.User;
import hudson.model.UserProperty;
import hudson.model.UserPropertyDescriptor;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.DataBoundSetter;

public class ThemeUserProperty extends UserProperty {

  private ThemeManagerFactory theme;

  @DataBoundConstructor
  public ThemeUserProperty() {}

  public ThemeManagerFactory getTheme() {
    return theme;
  }

  @DataBoundSetter
  public void setTheme(ThemeManagerFactory theme) {
    this.theme = theme;
  }

  @CheckForNull
  public static Theme forCurrentUser() {
    final User current = User.current();
    if (current == null) {
      return null;
    }

    ThemeUserProperty property = current.getProperty(ThemeUserProperty.class);
    if (property.theme == null) {
      return null;
    }

    return property.getTheme().getTheme();
  }

  @Extension
  public static class ThemeUserPropertyDescriptor extends UserPropertyDescriptor {

    @Override
    public boolean isEnabled() {
      return ThemeManagerPageDecorator.get().isUserThemeAllowed();
    }

    @NonNull
    @Override
    public String getDisplayName() {
      return "Theme";
    }

    @Override
    public UserProperty newInstance(User user) {
      return new ThemeUserProperty();
    }
  }
}
