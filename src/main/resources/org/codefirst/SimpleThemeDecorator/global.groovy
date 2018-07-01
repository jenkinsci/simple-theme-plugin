package org.codefirst.SimpleThemeDecorator

import lib.FormTagLib

def f = namespace(FormTagLib)

f.section(title: _("Theme")) {
    f.entry(field: "cssUrl",title: _("URL of theme CSS")) {
        f.textbox()
    }

    f.entry(field: 'cssRules', title: _("Extra CSS"), help: "/plugin/simple-theme-plugin/help-cssRules.html") {
        f.textarea()
    }

    f.entry(field: "jsUrl", title: _("URL of theme JavaScript")) {
        f.textbox()
    }

    f.entry(field: "faviconUrl", title: _("URL of theme Favicon")) {
        f.textbox()
    }
}
