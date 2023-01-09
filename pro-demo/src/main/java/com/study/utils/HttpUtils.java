package com.study.utils;


import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author LiHaoHan
 * @date 2023/1/9
 */
public class HttpUtils {



    public static HttpServletResponse buildPdfResp(HttpServletResponse response) throws UnsupportedEncodingException {
        response.setContentType("application/pdf;charset=UTF-8");
        long time = System.currentTimeMillis();
        response.setHeader("Content-Disposition", "attachment;filename=\"" + URLEncoder.encode(time + ".pdf", "UTF-8") + "\"");
        response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
        return response;
    }

    public static HttpServletResponse buildFileResp(HttpServletResponse response) throws UnsupportedEncodingException {
        response.setContentType("application/pdf;charset=UTF-8");
        long time = System.currentTimeMillis();
        response.setHeader("Content-Disposition", "attachment;filename=\"" + URLEncoder.encode(time + ".pdf", "UTF-8") + "\"");
        response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
        return response;
    }
}
