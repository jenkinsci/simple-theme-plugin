package org.jenkinsci.plugins.thememanager;

import hudson.ExtensionPoint;
import hudson.model.AbstractDescribableImpl;
import jenkins.model.Jenkins;
import org.kohsuke.stapler.DataBoundConstructor;

/**
 * Pluggable ability to add additional built-in themes. The descriptor's display name will be used
 * for the UI label
 *
 * <p>Since the user can configure this class, you must have a {@link DataBoundConstructor}.
 *
 * @see ThemeManagerPageDecorator
 * @see ThemeManagerPageDecorator
 */
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
