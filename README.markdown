# Simple Theme Plugin for Jenkins

[![Jenkins Plugin](https://img.shields.io/jenkins/plugin/v/simple-theme-plugin.svg)](https://plugins.jenkins.io/simple-theme-plugin)
[![GitHub release](https://img.shields.io/github/release/jenkinsci/simple-theme-plugin.svg?label=release)](https://github.com/jenkinsci/simple-theme-plugin/releases/latest)
[![Jenkins Plugin Installs](https://img.shields.io/jenkins/plugin/i/simple-theme-plugin.svg?color=blue)](https://plugins.jenkins.io/simple-theme-plugin)
[![Build Status](https://ci.jenkins.io/buildStatus/icon?job=Plugins%2Fsimple-theme-plugin%2Fmain)](https://ci.jenkins.io/job/Plugins/job/simple-theme-plugin/job/main/)
[![GitHub license](https://img.shields.io/github/license/jenkinsci/simple-theme-plugin.svg)](https://github.com/jenkinsci/simple-theme-plugin/blob/main/LICENSE.txt)
[![Maintenance](https://img.shields.io/maintenance/yes/2024.svg)]()

This plugin allows to customize Jenkin's appearance with custom
CSS and JavaScript. It also allows to replace the Favicon.

For a more user-centric approch to theming, take a look at the
[Theme Manager](https://plugins.jenkins.io/theme-manager/) plugin.

## Themes

Some themes can be found on GitHub, but most of them weren't updated in
multiple years, so I removed them from this README.

## Authors

* @TobiX
* @mallowlabs

## Contributing

If you want to contribute to this plugin, you probably will need a Jenkins
plugin developement environment. This basically means a current version of Java
(Java 17 should probably be okay for now) and [Apache Maven]. See the [Jenkins
Plugin Tutorial] for details.

If you have the proper environment, typing:

    $ mvn verify

should create a plugin as `target/*.hpi`, which you can install in your Jenkins
instance. Running

    $ mvn hpi:run -Djenkins.version=2.452.2

allows you to spin up a test Jenkins instance on [localhost] to test your
local changes before commiting.

[Apache Maven]: https://maven.apache.org/
[Jenkins Plugin Tutorial]: https://jenkins.io/doc/developer/tutorial/prepare/
[localhost]: http://localhost:8080/jenkins/

### Code Style

This plugin uses the [Google Java Code Style], please try to adhere to that
style whenever adding new files or making changes to existing files. The style
is enforced using the [spotless] plugin, if the build fails because you were
using the "wrong" style, you can fix it by running:

    $ mvn spotless:apply

to reformat Java code in the proper style.

[Google Java Code Style]: https://google.github.io/styleguide/javaguide.html
[spotless]: https://github.com/diffplug/spotless

## License

The MIT License (MIT)

- Copyright (c) 2011 mallowlabs
- Copyright (c) 2018-2022 TobiX

See [LICENSE](LICENSE)

## Changelog

* See [GitHub Releases](https://github.com/jenkinsci/simple-theme-plugin/releases) for the recent versions
* See the [old changelog](CHANGELOG.old.md) for versions 0.5.1 and older

