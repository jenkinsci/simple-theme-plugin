package org.jenkinsci.plugins.simpletheme;

import org.kohsuke.stapler.DataBoundSetter;

public abstract class UrlThemeElement extends ThemeElement {

    private String url;

    public String getUrl() {
        return url;
    }

    @DataBoundSetter
    public void setUrl(String url) {
        this.url = url;
    }
}
