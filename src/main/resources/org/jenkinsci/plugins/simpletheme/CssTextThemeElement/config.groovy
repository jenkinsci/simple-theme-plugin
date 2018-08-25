package org.jenkinsci.plugins.simpletheme.JsUrlThemeElement

import lib.FormTagLib

def f = namespace(FormTagLib)

f.entry(field: 'text', title: _("Extra CSS"), help: "/plugin/simple-theme-plugin/help-cssRules.html") {
    f.textarea()
}
