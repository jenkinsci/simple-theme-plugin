package org.jenkinsci.plugins.thememanager.none;

import edu.umd.cs.findbugs.annotations.NonNull;
import hudson.Extension;
import org.jenkinsci.plugins.thememanager.Theme;
import org.jenkinsci.plugins.thememanager.ThemeManagerFactory;
import org.jenkinsci.plugins.thememanager.ThemeManagerFactoryDescriptor;
import org.kohsuke.stapler.DataBoundConstructor;

public class NoOpThemeManagerFactory extends ThemeManagerFactory {

  @DataBoundConstructor
  public NoOpThemeManagerFactory() {}

  @Override
  public Theme getTheme() {
    return Theme.builder().build();
  }

  @Extension
  public static class NoOpThemeManagerFactoryDescriptor extends ThemeManagerFactoryDescriptor {

    @NonNull
    @Override
    public String getDisplayName() {
      return "None";
    }

    @Override
    public ThemeManagerFactory getInstance() {
      return new NoOpThemeManagerFactory();
    }
  }
}
