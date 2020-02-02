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

* @TobiX
* @mallowlabs

## Contributing

If you want to contribute to this plugin, you probably will need a Jenkins
plugin developement environment. This basically means a current version of Java
(Java 8 should probably be okay for now) and [Apache Maven]. See the [Jenkins
Plugin Tutorial] for details.

If you have the proper environment, typing:

    $ mvn verify

should create a plugin as `target/*.hpi`, which you can install in your Jenkins
instance. Running

    $ mvn hpi:run -Djenkins.version=2.107.3

allows you to spin up a test Jenkins instance on [localhost] to test your
local changes before commiting.

[Apache Maven]: https://maven.apache.org/
[Jenkins Plugin Tutorial]: https://jenkins.io/doc/developer/tutorial/prepare/
[localhost]: http://localhost:8080/jenkins/

### Code Style

This plugin uses the [Google Java Code Style] and enforces that fact when
building. If your build fails with

    [ERROR] Found 1 non-complying files, failing build
    [ERROR] To fix formatting errors, run "mvn fmt:format"

Just do exactly that:

    $ mvn fmt:format

to reformat all Java code in the proper style.

[Google Java Code Style]: https://google.github.io/styleguide/javaguide.html

## License

The MIT License (MIT)

- Copyright (c) 2011 mallowlabs
- Copyright (c) 2018-2020 TobiX

See [LICENSE](LICENSE)

## Changelog

* See [GitHub Releases](https://github.com/jenkinsci/simple-theme-plugin/releases) for the recent versions
* See the [old changelog](CHANGELOG.old.md) for versions 0.5.1 and older

