package grails.plugin.xss.sanitizer

import grails.plugins.*
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.core.Ordered
import grails.plugin.xss.sanitizer.filter.XssFilter

class XssSanitizerGrailsPlugin extends Plugin {

    // the version or versions of Grails the plugin is designed for
    def grailsVersion = "3.3.0 > *"
    // resources that are excluded from plugin packaging
    def pluginExcludes = [
        "grails-app/views/error.gsp"
    ]

    // TODO Fill in these fields
    def title = "Xss Sanitizer" // Headline display name of the plugin
    def author = "Roberto Perez Alcolea"
    def authorEmail = "roberto@perezalcolea.info"
    def description = '''\
Grails plugin for sanitizing XSS from the user input. This plugin uses OWASP ESAPI library to sanitize request parameters. This reduces the risk of dangerous XSS request parameters possibly being rendered on the client.
'''
    def profiles = ['web']

    def documentation = "http://grails.org/plugin/xss-sanitizer"

    def license = "APACHE"

    def issueManagement = [ system: "Github", url: "https://github.com/rpalcolea/grails-xss-sanitizer/issues" ]

    def scm = [ url: "https://github.com/rpalcolea/grails-xss-sanitizer" ]

    /**
     * Add XssFilter
     */
    Closure doWithSpring() {{->
        xssFilter(FilterRegistrationBean) {
            filter = bean(XssFilter)
            urlPatterns = ['/*']
            order = Ordered.HIGHEST_PRECEDENCE
        }
    }}
}
