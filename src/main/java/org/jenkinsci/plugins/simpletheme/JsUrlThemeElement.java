package org.jenkinsci.plugins.simpletheme;

import hudson.Extension;
import hudson.model.Descriptor;
import java.text.MessageFormat;
import java.util.Set;
import org.jenkinsci.Symbol;
import org.kohsuke.stapler.DataBoundConstructor;

public class JsUrlThemeElement extends UrlThemeElement {

  private static final String JS_HTML = "<script type=\"text/javascript\" src=\"{0}\"></script>";

  @DataBoundConstructor
  public JsUrlThemeElement(String url) {
    setUrl(url);
  }

  @Override
  public void collectHeaderFragment(Set<String> fragments, boolean injectCss) {
    fragments.add(MessageFormat.format(JS_HTML, getSafeUrl(getUrl())));
  }

  @Extension
  @Symbol("jsUrl")
  public static final class DescriptorImpl extends Descriptor<ThemeElement> {

    @Override
    public String getDisplayName() {
      return Messages.jsURL();
    }
  }
}
