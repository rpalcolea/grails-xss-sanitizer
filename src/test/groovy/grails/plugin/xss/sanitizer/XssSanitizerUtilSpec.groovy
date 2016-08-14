package grails.plugin.xss.sanitizer

import spock.lang.Specification
import grails.plugin.xss.sanitizer.util.XssSanitizerUtil

class XssSanitizerUtilSpec extends Specification {
    def setupSpec() {
        System.setProperty("org.owasp.esapi.resources", "grails-app/conf")
    }

    def cleanupSpec() {
        System.clearProperty("org.owasp.esapi.resources")
    }

    void "should strip out null characters"(){
        when:
        String output = XssSanitizerUtil.stripXSS("\0")

        then:
        output == ""
    }

    public void "should strip out content of script tags"() {
        when:
        String output = XssSanitizerUtil.stripXSS("valid-content<script>xss-content</script>")

        then:
        output == "valid-content"
    }

    public void "should strip out trailing script tag"() {
        when:
        String output = XssSanitizerUtil.stripXSS("</script>valid-content")

        then:
        output == "valid-content"
    }

    public void "should strip out starting script tag"() {
        when:
        String output = XssSanitizerUtil.stripXSS("<script src='xss.js'>valid-content")

        then:
        output == "valid-content"
    }

    public void "should strip out eval attribute"() {
        when:
        String output = XssSanitizerUtil.stripXSS("eval('xss-js-content')")

        then:
        output == ""
    }

    public void "should strip out expression attribute"() {
        when:
        String output = XssSanitizerUtil.stripXSS("expression('xss-content')")

        then:
        output == ""
    }

    public void "should strip out on load attribute"() {
        when:
        String output = XssSanitizerUtil.stripXSS("onload=xss.execute()")

        then:
        output == "xss.execute()"
    }

    public void "should strip out javascript protocol"() {
        when:
        String output = XssSanitizerUtil.stripXSS("javascript:xss.execute()")

        then:
        output == "xss.execute()"
    }

    public void "should strip out vbcript protocol"() {
        when:
        String output = XssSanitizerUtil.stripXSS("vbscript:xss.execute()")

        then:
        output == "xss.execute()"
    }

    public void "should strip out src attribute"() {
        when:
        String output = XssSanitizerUtil.stripXSS("<img src='xss.jpg'>")

        then:
        output == "<img >"
    }

    public void "should strip out content of iframe tags"() {
        when:
        String output = XssSanitizerUtil.stripXSS("<iframe src='xss.html'>xss-content</iframe>")

        then:
        output == ""
    }

    public void "should strip out content of form"() {
        when:
        String output = XssSanitizerUtil.stripXSS("<form action=''><input id='formInjection'></form>")

        then:
        output == ""
    }
}
