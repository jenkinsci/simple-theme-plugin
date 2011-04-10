package org.codefirst;

import hudson.Extension;
import hudson.model.PageDecorator;
import net.sf.json.JSONObject;

import org.kohsuke.stapler.StaplerRequest;

@Extension
public class SimpleThemeDecorator extends PageDecorator {
	private String cssUrl;
	private String jsUrl;

	public SimpleThemeDecorator() {
		super(SimpleThemeDecorator.class);
	}

	@Override
	public boolean configure(StaplerRequest req, JSONObject formData)
			throws FormException {
		cssUrl = formData.getString("cssUrl");
		jsUrl = formData.getString("jsUrl");
		System.out.println("======DEBUG" + cssUrl);
		save();
		return super.configure(req, formData);
	}

	public String getCssUrl() {
		System.out.println("======DEBUG(GET)" + cssUrl);
		return cssUrl;
	}

	public String getJsUrl() {
		return jsUrl;
	}

}
