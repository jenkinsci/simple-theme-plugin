package org.jenkinsci.plugins.simpletheme;

import hudson.ExtensionPoint;
import hudson.model.AbstractDescribableImpl;
import java.util.Set;
import org.kohsuke.stapler.Stapler;
import org.kohsuke.stapler.StaplerRequest;

public abstract class ThemeElement extends AbstractDescribableImpl<ThemeElement>
    implements ExtensionPoint {
  public abstract void collectHeaderFragment(Set<String> fragments, boolean injectCss);

  protected String getSafeUrl(String url) {
    if (url != null && url.startsWith("/")) {
      StaplerRequest req = Stapler.getCurrentRequest();
      if (req != null) {
        String context = req.getContextPath();

        return String.format("%s%s", context, url);
      }
    }
    return url;
  }
}
