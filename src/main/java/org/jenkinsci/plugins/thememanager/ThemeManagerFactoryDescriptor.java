package org.jenkinsci.plugins.thememanager;

import hudson.DescriptorExtensionList;
import hudson.model.Descriptor;
import jenkins.model.Jenkins;

public abstract class ThemeManagerFactoryDescriptor extends Descriptor<ThemeManagerFactory> {

  public abstract ThemeManagerFactory getInstance();

  public String getThemeCssSuffix() {
    return "theme.css";
  };

  public String getThemeJsSuffix() {
    return "theme.js";
  };

  public static DescriptorExtensionList<ThemeManagerFactory, ThemeManagerFactoryDescriptor> all() {
    return Jenkins.get().getDescriptorList(ThemeManagerFactory.class);
  }
}
