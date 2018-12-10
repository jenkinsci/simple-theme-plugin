package org.jenkinsci.plugins.simpletheme;

import hudson.ExtensionPoint;
import hudson.model.AbstractDescribableImpl;
import java.util.Set;

public abstract class ThemeElement extends AbstractDescribableImpl<ThemeElement>
    implements ExtensionPoint {

  public abstract void collectHeaderFragment(Set<String> fragments, boolean injectCss);
}
