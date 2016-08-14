package grails.plugin.xss.sanitizer.filter.wrapper;


import grails.plugin.xss.sanitizer.util.XssSanitizerUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;


/**
 * Taken from https://github.com/matcgi/XssSanitizer/blob/master/src/java/org/tonyzampogna/xss/sanitizer/filter/wrapper/XssRequestWrapper.java
 */
public class XssRequestWrapper extends HttpServletRequestWrapper {

    public XssRequestWrapper(HttpServletRequest servletRequest) {
        super(servletRequest);
    }

    @Override
    public String[] getParameterValues(String parameter) {
        String[] values = super.getParameterValues(parameter);

        if (values == null) {
            return null;
        }

        int count = values.length;
        String[] encodedValues = new String[count];
        for (int i = 0; i < count; i++) {
            encodedValues[i] = XssSanitizerUtil.stripXSS(values[i]);
        }

        return encodedValues;
    }

    @Override
    public String getParameter(String parameter) {
        String value = super.getParameter(parameter);
        if (value != null) {
            value = XssSanitizerUtil.stripXSS(value);
        }
        return value;
    }

    @Override
    public String getHeader(String name) {
        String value = super.getHeader(name);
        if (value != null) {
            value = XssSanitizerUtil.stripXSS(value);
        }
        return value;
    }
}