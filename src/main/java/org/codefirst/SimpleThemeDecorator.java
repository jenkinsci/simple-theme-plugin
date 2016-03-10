package org.codefirst;

import hudson.Extension;
import hudson.model.PageDecorator;
import net.sf.json.JSONObject;
import org.kohsuke.stapler.StaplerRequest;

@Extension
public class SimpleThemeDecorator extends PageDecorator {
	private String cssUrl;
	private String jsUrl;
	private String faviconUrl;

	public SimpleThemeDecorator() {
		super();
		load();
	}

	@Override
	public boolean configure(StaplerRequest req, JSONObject formData)
			throws FormException {
		req.bindJSON(this, formData);
		save();
		return true;
	}

	public void setCssUrl(String cssUrl) {
		this.cssUrl = cssUrl;
	}

	public String getCssUrl() {
		return cssUrl;
	}

	public void setJsUrl(String jsUrl) {
		this.jsUrl = jsUrl;
	}

	public String getJsUrl() {
		return jsUrl;
	}

	public void setFaviconUrl(String faviconUrl) {
		this.faviconUrl = faviconUrl;
	}

	public String getFaviconUrl() {
		return faviconUrl;
	}

}
