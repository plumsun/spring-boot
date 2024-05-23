package com.study.config.filter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

public class GzipResponseWrapper extends HttpServletResponseWrapper {

    private GZIPOutputStream gzipOutputStream;

    public GzipResponseWrapper(HttpServletResponse response, GZIPOutputStream gzipOutputStream) {
        super(response);
        this.gzipOutputStream = gzipOutputStream;
    }

    @Override
    public void setContentLength(int len) {
        // Ignore content length header when using gzip
    }



    @Override
    public void flushBuffer() throws IOException {
        gzipOutputStream.finish();
        super.flushBuffer();
    }

    @Override
    public void reset() {
        // Disable reset
    }





    public GZIPOutputStream getGzipOutputStream() {
        return gzipOutputStream;
    }
}
