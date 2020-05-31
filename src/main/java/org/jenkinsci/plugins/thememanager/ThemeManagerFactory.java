package org.jenkinsci.plugins.thememanager;

import hudson.ExtensionPoint;
import hudson.model.AbstractDescribableImpl;
import jenkins.model.Jenkins;

public abstract class ThemeManagerFactory extends AbstractDescribableImpl<ThemeManagerFactory>
    implements ExtensionPoint {

  public abstract Theme getTheme();

  public String getCssUrl() {
    ThemeManagerFactoryDescriptor descriptor = getDescriptor();
    return Jenkins.get().getRootUrl()
        + descriptor.getDescriptorUrl()
        + "/"
        + descriptor.getThemeCssSuffix();
  }

  @Override
  public ThemeManagerFactoryDescriptor getDescriptor() {
    return (ThemeManagerFactoryDescriptor) super.getDescriptor();
  }
}
