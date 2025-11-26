package org.jenkinsci.plugins.simpletheme;

import hudson.Extension;
import java.security.SecureRandom;
import java.util.Base64;
import jenkins.security.csp.Contributor;
import jenkins.security.csp.CspBuilder;
import jenkins.security.csp.Directive;
import jenkins.security.csp.FetchDirective;
import org.kohsuke.accmod.restrictions.suppressions.SuppressRestrictedWarnings;
import org.kohsuke.stapler.Stapler;
import org.kohsuke.stapler.StaplerRequest2;

@Extension
@SuppressRestrictedWarnings({Contributor.class, CspBuilder.class, FetchDirective.class})
public class NonceContributor implements Contributor {
    @Override
    public void apply(CspBuilder cspBuilder) {
        final StaplerRequest2 req = Stapler.getCurrentRequest2();
        if (req != null) {
            // TODO We need to be in a Stapler context for this to work, is this enough?
            String nonce = getOrGenerateNonce(req);
            cspBuilder
                    // TODO Remove initialization once https://github.com/jenkinsci/jenkins/issues/23854 is fixed
                    .initialize(FetchDirective.SCRIPT_SRC, Directive.SELF)
                    .add(Directive.SCRIPT_SRC_ELEM, "'nonce-" + nonce + "'");
        }
    }

    static synchronized String getOrGenerateNonce(StaplerRequest2 req) {
        if (req == null) {
            return null;
        }
        String nonce = (String) req.getAttribute(NonceContributor.class.getName());
        if (nonce == null) {
            byte[] bytes = new byte[32];
            RANDOM.nextBytes(bytes);
            nonce = Base64.getEncoder().encodeToString(bytes);
            req.setAttribute(NonceContributor.class.getName(), nonce);
        }
        return nonce;
    }

    private static final SecureRandom RANDOM = new SecureRandom();
}
