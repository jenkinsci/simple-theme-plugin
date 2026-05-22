package org.codefirst.SimpleThemeDecorator

import lib.FormTagLib

def f = namespace(FormTagLib)

f.section(title: _("Customizable theme")) {
    f.entry(title: _("Theme elements")) {
    f.description("External themes may be blocked by Jenkins CSP.")
    f.repeatableHeteroProperty(field: "elements", hasHeader: true)
}
}
