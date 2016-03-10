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
		super(SimpleThemeDecorator.class);
		load();
	}

	@Override
	public boolean configure(StaplerRequest req, JSONObject formData)
			throws FormException {
		cssUrl = formData.getString("cssUrl");
		jsUrl = formData.getString("jsUrl");
		faviconUrl = formData.getString("faviconUrl");
		save();
		return super.configure(req, formData);
	}

	public String getCssUrl() {
		return cssUrl;
	}

	public String getJsUrl() {
		return jsUrl;
	}

	public String getFaviconUrl() {
		return faviconUrl;
	}

}
