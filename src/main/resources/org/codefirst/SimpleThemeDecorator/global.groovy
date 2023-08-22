package org.codefirst.SimpleThemeDecorator

import lib.FormTagLib

def f = namespace(FormTagLib)

f.section(title: _("Customizable theme")) {
    f.entry(title: _("Theme elements")) {
        f.repeatableHeteroProperty(field: "elements", hasHeader: true)
    }
}
