package grails.plugin.xss.sanitizer.util;

import org.owasp.esapi.ESAPI;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Taken from http://ricardozuasti.com/2012/stronger-anti-cross-site-scripting-xss-filter-for-java-web-apps/
 */
public class XssSanitizerUtil {

    private static List<Pattern> XSS_INPUT_PATTERNS = new ArrayList<Pattern>();

    static {
        // Avoid anything between script tags
        XSS_INPUT_PATTERNS.add(Pattern.compile("<script>(.*?)</script>", Pattern.CASE_INSENSITIVE));

        // avoid iframes
        XSS_INPUT_PATTERNS.add(Pattern.compile("<iframe(.*?)>(.*?)</iframe>", Pattern.CASE_INSENSITIVE));

        // Avoid anything in a src='...' type of expression
        XSS_INPUT_PATTERNS.add(Pattern.compile("src[\r\n]*=[\r\n]*\\\'(.*?)\\\'", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL));

        XSS_INPUT_PATTERNS.add(Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL));

        XSS_INPUT_PATTERNS.add(Pattern.compile("src[\r\n]*=[\r\n]*([^>]+)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL));

        // Remove any lonesome </script> tag
        XSS_INPUT_PATTERNS.add(Pattern.compile("</script>", Pattern.CASE_INSENSITIVE));

        // Remove any lonesome <script ...> tag
        XSS_INPUT_PATTERNS.add(Pattern.compile("<script(.*?)>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL));

        // Avoid eval(...) expressions
        XSS_INPUT_PATTERNS.add(Pattern.compile("eval\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL));

        // Avoid expression(...) expressions
        XSS_INPUT_PATTERNS.add(Pattern.compile("expression\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL));

        // Avoid javascript:... expressions
        XSS_INPUT_PATTERNS.add(Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE));

        // Avoid vbscript:... expressions
        XSS_INPUT_PATTERNS.add(Pattern.compile("vbscript:", Pattern.CASE_INSENSITIVE));

        // Avoid onload= expressions
        XSS_INPUT_PATTERNS.add(Pattern.compile("onload(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL));

        // Avoid any form injection with <...form ...> ... </form ...> tag
        XSS_INPUT_PATTERNS.add(Pattern.compile("<([^<>]*)form([^<>]*)>(.*?)</([^<>]*)form([^<>]*)>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL));
    }

    /**
     * This method takes a string and strips out any potential script injections.
     *
     * @param value - the value to be sanitized
     * @return String - the new "sanitized" string.
     */
    public static String stripXSS(String value) {

        try {

            if (value != null) {

                value = ESAPI.encoder().canonicalize(value);

                // Avoid null characters
                value = value.replaceAll("\0", "");

                // test against known XSS input patterns
                for (Pattern xssInputPattern : XSS_INPUT_PATTERNS) {
                    value = xssInputPattern.matcher(value).replaceAll("");
                }
            }

        } catch (Exception ex) {
            System.out.println("Could not strip XSS from value = " + value + " | ex = " + ex.getMessage());
        }

        return value;
    }

}
