package com.study.config.filter;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

public class GzipResponseFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Check if the client accepts gzip encoding
        String encoding = request.getHeader("Accept-Encoding");
        if (encoding != null && encoding.contains("gzip")) {
            // Create a ByteArrayOutputStream to capture the response
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            // Wrap the response with a GZIPOutputStream
            GZIPOutputStream gzipOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            // Create a new response wrapper with gzip stream
            GzipResponseWrapper responseWrapper = new GzipResponseWrapper(response, gzipOutputStream);
            // Proceed with the filter chain
            filterChain.doFilter(request, responseWrapper);
            // Close the gzip stream
            gzipOutputStream.finish();
            // Write the compressed response to the original response
            response.setHeader("Content-Encoding", "gzip");
            response.getOutputStream().write(byteArrayOutputStream.toByteArray());
        } else {
            // If client does not support gzip, proceed with the filter chain
            filterChain.doFilter(request, response);
        }
    }
}