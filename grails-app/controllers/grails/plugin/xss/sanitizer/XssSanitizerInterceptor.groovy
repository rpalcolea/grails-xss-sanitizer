package grails.plugin.xss.sanitizer

import grails.plugin.xss.sanitizer.util.XssSanitizerUtil

class XssSanitizerInterceptor {

    XssSanitizerInterceptor() {
        matchAll()
    }

    boolean before() {
        if (grailsApplication.config.xssSanitizer.enabled)
            sanitizeParameters(params)

        return true
    }


    /**
     * Loop through each of the Grails request parameters
     * and "sanitize" each value.
     */
    private void sanitizeParameters(parameters) {
        parameters.each { entry ->
            if (entry.value instanceof String) {
                entry.value = XssSanitizerUtil.stripXSS(entry.value)
            }
        }
    }
}
