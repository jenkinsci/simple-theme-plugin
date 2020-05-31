package org.jenkinsci.plugins.thememanager;

import hudson.ExtensionPoint;
import hudson.model.AbstractDescribableImpl;

public abstract class ThemeManagerFactory extends AbstractDescribableImpl<ThemeManagerFactory>
    implements ExtensionPoint {

  public abstract Theme getTheme();

  @Override
  public ThemeManagerFactoryDescriptor getDescriptor() {
    return (ThemeManagerFactoryDescriptor) super.getDescriptor();
  }
}
