# grails-xss-sanitizer

[![Build Status](https://travis-ci.org/rpalcolea/grails-xss-sanitizer.svg?branch=master)](https://travis-ci.org/rpalcolea/grails-xss-sanitizer)
[ ![Download](https://api.bintray.com/packages/rpalcolea/plugins/xss-sanitizer/images/download.svg) ](https://bintray.com/rpalcolea/plugins/xss-sanitizer/_latestVersion)
[![Slack Signup](http://slack-signup.grails.org/badge.svg)](http://slack-signup.grails.org)

Grails 3 plugin for sanitizing XSS from the user input. This is a port of the Grails `2.x` version by @tonyzampogna (Tony Zampogna) https://github.com/tonyzampogna/XssSanitizer

This plugin uses OWASP ESAPI library to sanitize request parameters. This reduces the risk of dangerous XSS request parameters possibly being rendered on the client.

Installation
----------

Add the following dependencies in `build.gradle`
```
dependencies {
...
    compile 'org.grails.plugins:xss-sanitizer:1.0.+'
...
}
```

Description
----------

This plugin will add the automatic ability to strip / clean out unwanted XSS code in the browser. The plugin strips code that comes in via the request object. Also, any Servlets will use an extend HttpServletRequest so that request parameters used from that servlet will be stripped as well.

Just adding this plugin to you project with the installation instructions above and adding the following Config will activate it:

```yaml
	xssSanitizer:
	     enabled: true
```

There is an XssSanitizerUtil class that can also be used to strip strings out.

Sponsors
-------
[![Alt text](https://www.yourkit.com/images/yklogo.png "YourKit")](https://www.yourkit.com/.net/profiler/index.jsp)

YourKit supports open source projects with its full-featured Java Profiler.
YourKit, LLC is the creator of <a href="https://www.yourkit.com/java/profiler/index.jsp">YourKit Java Profiler</a>
and <a href="https://www.yourkit.com/.net/profiler/index.jsp">YourKit .NET Profiler</a>,
innovative and intelligent tools for profiling Java and .NET applications.
