package grails.plugin.xss.sanitizer.filter;


import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;
import grails.plugin.xss.sanitizer.filter.wrapper.XssRequestWrapper;

/**
 * Any requests to the HttpRequest or HttpResponse will go through the wrapper.
 * Taken from https://github.com/matcgi/XssSanitizer/blob/master/src/java/org/tonyzampogna/xss/sanitizer/filter/XssFilter.java
 */
public class XssFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        chain.doFilter(new XssRequestWrapper(request), response);
    }
}