# Simple Theme Plugin for Jenkins

This plugin allows to customize Jenkin's appearance with custom
CSS and JavaScript. It also allows to replace the Favicon.

## Themes

Some themes can be found on GitHub:

- ["Atlassian"](https://github.com/djonsson/jenkins-atlassian-theme)
- [Doony](https://github.com/kevinburke/doony)
- [Isotope11](https://github.com/isotope11/jenkins-isotope-style)
- [Material](http://afonsof.com/jenkins-material-theme/)
- [Neo2](https://tobix.github.io/jenkins-neo2-theme/)
- [Rackspace Canon](https://github.com/rackerlabs/canon-jenkins)

## Authors

* @mallowlabs
* @TobiX

## How to build

You need to prepare Jenkins plugins development environment.
See [Plugin Tutorial](https://wiki.jenkins-ci.org/display/JENKINS/Plugin+tutorial).

After preparation, type below command:

    $ mvn package

You will get `target/*.hpi`.

## License

The MIT License (MIT)

- Copyright (c) 2011 mallowlabs
- Copyright (c) 2018 TobiX

See [LICENSE](LICENSE)

