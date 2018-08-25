package org.jenkinsci.plugins.simpletheme.CssUrlThemeElement

import lib.FormTagLib

def f = namespace(FormTagLib)

f.entry(field: "url", title: _("URL of theme Favicon")) {
    f.textbox()
}

