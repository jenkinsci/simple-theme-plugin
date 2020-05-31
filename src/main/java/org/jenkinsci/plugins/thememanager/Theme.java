package org.jenkinsci.plugins.thememanager;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;

import java.text.MessageFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Theme {

  private static final String JS_HTML = "<script type=\"text/javascript\" src=\"{0}\"></script>";
  private static final String CSS_HTML =
      "<link type=\"text/css\" rel=\"stylesheet\" href=\"{0}\"/>";

  private List<String> cssUrls;
  private List<String> javascriptUrls;

  private Theme(List<String> cssUrls, List<String> javascriptUrls) {
    this.cssUrls = cssUrls;
    this.javascriptUrls = javascriptUrls;
  }

  public Set<String> generateHeaderElements(boolean injectCss) {
    Set<String> headerElements = new HashSet<>();

    if (injectCss) {
      for (String cssUrl : cssUrls) {
        headerElements.add(MessageFormat.format(CSS_HTML, cssUrl));
      }
    }

    for (String javascriptUrl : javascriptUrls) {
      headerElements.add(MessageFormat.format(JS_HTML, javascriptUrl));
    }

    return headerElements;
  }

  public List<String> getCssUrls() {
    return cssUrls;
  }

  public List<String> getJavascriptUrls() {
    return javascriptUrls;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {
    private List<String> cssUrls = emptyList();
    private List<String> javascriptUrls = emptyList();

    public Builder() {}

    public Builder withCssUrl(String cssUrl) {
      this.cssUrls = singletonList(cssUrl);
      return this;
    }

    public Builder withCssUrls(List<String> cssUrls) {
      this.cssUrls = cssUrls;
      return this;
    }

    public Builder withJavascriptUrl(String javascriptUrl) {
      this.javascriptUrls = singletonList(javascriptUrl);
      return this;
    }

    public Builder withJavascriptUrls(List<String> javascriptUrls) {
      this.javascriptUrls = javascriptUrls;
      return this;
    }

    public Theme build() {
      return new Theme(cssUrls, javascriptUrls);
    }
  }
}
