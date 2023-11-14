package com.study.utils;

import com.study.entity.resp.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * The type Http resp utils.
 *
 * @author Lihaohan
 */
@Slf4j
public class HttpRespUtils {

    private HttpRespUtils() {
    }

    /**
     * Load excel resp http servlet response.
     *
     * @param response the response
     * @param fileName the file name
     * @return the http servlet response
     * @throws UnsupportedEncodingException the unsupported encoding exception
     */
    public static void loadExcelResp(HttpServletResponse response, String fileName) throws IOException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("UTF-8");
        String encode = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                String.format("attachment;filename=%s.xlsx", encode));
    }

    /**
     * Load pdf resp http servlet response.
     *
     * @param response the response
     * @param fileName the file name
     * @return the http servlet response
     * @throws UnsupportedEncodingException the unsupported encoding exception
     */
    public static void loadPdfResp(HttpServletResponse response, String fileName) throws IOException {
        response.setContentType("application/pdf;charset=UTF-8");
        String encode = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                String.format("attachment;filename=%s.pdf", encode));
        response.setHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION);
    }

    /**
     * Reset resp http servlet response.
     *
     * @param response the response
     * @return the http servlet response
     * @throws IOException the io exception
     */
    public static void resetResp(HttpServletResponse response) throws IOException {
        response.reset();
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().println(Response.err());
    }
}
