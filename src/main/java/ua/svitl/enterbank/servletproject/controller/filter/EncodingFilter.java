package ua.svitl.enterbank.servletproject.controller.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class EncodingFilter implements Filter {
    private static final Logger LOG = LogManager.getLogger(EncodingFilter.class);
    private String encoding;

    @Override
    public void init(FilterConfig filterConfig) {
        LOG.debug("Filter initialization starts");
        encoding = filterConfig.getInitParameter("encoding");
        LOG.trace("Encoding from web.xml: {}", encoding);
        LOG.debug("Filter initialization ends");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        LOG.debug("Filter starts");

        HttpServletRequest httpRequest = (HttpServletRequest)request;
        LOG.trace("Request uri: {}", httpRequest.getRequestURI());

        String requestEncoding = request.getCharacterEncoding();
        if (requestEncoding == null) {
            LOG.trace("Request encoding = null, set encoding: {}", encoding);
            request.setCharacterEncoding(encoding);
        }
        response.setContentType("text/html; " + encoding);
        response.setCharacterEncoding(encoding);

        LOG.debug("Filter finished");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        LOG.debug("Filter destruction starts");
        LOG.debug("Filter destruction ends");
    }
}
