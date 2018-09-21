package org.jenkinsci.plugins.simpletheme;

import hudson.Extension;
import hudson.model.Descriptor;
import java.text.MessageFormat;
import java.util.Set;
import org.jenkinsci.Symbol;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.DataBoundSetter;

public class CssTextThemeElement extends ThemeElement {

  private static final String CSS_HTML = "<style type=\"text/css\">\n{0}\n</style>";

  private String text;

  @DataBoundConstructor
  public CssTextThemeElement(String url) {
    setText(url);
  }

  public String getText() {
    return text;
  }

  @DataBoundSetter
  public void setText(String text) {
    this.text = text;
  }

  @Override
  public void collectHeaderFragment(Set<String> fragments, boolean injectCss) {
    // This is not escaped at all, but not a security issue: If you can use this plugin
    // to inject CSS, you can also use it to inject extra JavaScript...
    fragments.add(MessageFormat.format(CSS_HTML, getText()));
  }

  @Extension
  @Symbol("cssText")
  public static final class DescriptorImpl extends Descriptor<ThemeElement> {

    @Override
    public String getDisplayName() {
      return Messages.cssText();
    }
  }
}
