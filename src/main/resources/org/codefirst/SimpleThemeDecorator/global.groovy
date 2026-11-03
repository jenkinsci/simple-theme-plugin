package org.codefirst.SimpleThemeDecorator

import lib.FormTagLib

def f = namespace(FormTagLib)

f.section(title: _("Customizable theme")) {
    f.entry(title: _("Theme elements")) {
        f.description(_("External themes may be blocked by Jenkins Content Security Policy (CSP). See https://www.jenkins.io/doc/book/security/csp/"))
        f.repeatableHeteroProperty(field: "elements", hasHeader: true)
    }
}