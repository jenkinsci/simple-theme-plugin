(function(global, document) {
    "use strict";

    function removeAll() {
        const links = document.getElementsByTagName('link');

        for (const link of links) {
            if (link.rel.split(/\s+/).some(e => e === 'icon')) {
                link.remove();
            }
        }
    }

    function add(url) {
        const link = document.createElement('link');
        link.setAttribute('rel', 'icon');
        document.getElementsByTagName('head')[0].appendChild(link);
        link.setAttribute('href', url);
    }

    document.addEventListener("DOMContentLoaded", function(event) {
        const script = document.getElementById("simple-theme-script");
        removeAll();
        add(script.dataset.url);
    });

})(this, document);