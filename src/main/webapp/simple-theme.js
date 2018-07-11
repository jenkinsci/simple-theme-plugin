(function(global, document) {
    "use strict";

    function removeAll() {
        var links = document.getElementsByTagName('link'),
            link, i;

        for (i = 0; i < links.length; i++) {
            link = links[i];
            if (link.rel === 'shortcut icon' || link.rel === 'icon') {
                link.parentNode.removeChild(link);
            }
        }
    }

    function add(url) {
        var link = document.createElement('link');
        link.setAttribute('rel', 'icon');
        document.getElementsByTagName('head')[0].appendChild(link);
        link.setAttribute('href', url);
    }

    function replaceFavicon(url) {
        document.addEventListener("DOMContentLoaded", function(event) {
            removeAll();
            add(url);
        });
    }

    global['org.jenkinsci.plugins.simpletheme'] = {
        replaceFavicon: replaceFavicon
    };
})(this, document);
