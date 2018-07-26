package org.jenkinsci.plugins.simpletheme.JsUrlThemeElement

import lib.FormTagLib

def f = namespace(FormTagLib)

f.entry(field: "url", title: _("URL of theme JavaScript")) {
    f.textbox()
}
