package org.jenkinsci.plugins.simpletheme;

import hudson.Extension;
import hudson.ExtensionList;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jenkins.security.csp.Contributor;
import jenkins.security.csp.CspBuilder;
import jenkins.security.csp.Directive;
import org.codefirst.SimpleThemeDecorator;
import org.kohsuke.accmod.restrictions.suppressions.SuppressRestrictedWarnings;
import org.kohsuke.stapler.Stapler;
import org.kohsuke.stapler.StaplerRequest2;

/**
 * Allow the specific configured URLs from Simple Theme Plugin in CSP headers.
 */
@SuppressRestrictedWarnings({Contributor.class, CspBuilder.class})
@Extension
public class SimpleThemeUrlContributor implements Contributor {

    private static final Logger LOGGER = Logger.getLogger(SimpleThemeUrlContributor.class.getName());

    @Override
    public void apply(CspBuilder cspBuilder) {
        final SimpleThemeDecorator decorator = ExtensionList.lookupSingleton(SimpleThemeDecorator.class);
        for (ThemeElement element : decorator.getElements()) {
            if (element instanceof UrlThemeElement) {
                String url = ((UrlThemeElement) element).getUrl();
                if (url != null && !url.isEmpty()) {
                    try {
                        URI uri = new URI(url);
                        if (uri.isAbsolute()) {
                            LOGGER.log(Level.FINE, "Allowing absolute URL in CSP: " + uri);
                            if (element instanceof JsUrlThemeElement) {
                                cspBuilder.add(Directive.SCRIPT_SRC, uri.toString());
                            } else if (element instanceof CssUrlThemeElement) {
                                cspBuilder.add(Directive.STYLE_SRC, uri.toString());
                            }
                        } else if (uri.getHost() != null && Stapler.getCurrentRequest2() != null) {
                            // protocol-relative URL
                            StaplerRequest2 request = Stapler.getCurrentRequest2();
                            String scheme = request.getScheme();
                            LOGGER.log(Level.FINE, "Allowing scheme-relative URL in CSP: " + scheme + ":" + uri);
                            if (element instanceof JsUrlThemeElement) {
                                cspBuilder.add(Directive.SCRIPT_SRC, scheme + ":" + uri);
                            } else if (element instanceof CssUrlThemeElement) {
                                cspBuilder.add(Directive.STYLE_SRC, scheme + ":" + uri);
                            }
                        }
                    } catch (URISyntaxException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }
}
