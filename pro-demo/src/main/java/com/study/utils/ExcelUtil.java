package com.study.utils;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.List;

/**
 * excle样式工具类
 */
@Slf4j
public class ExcelUtil {


    private ExcelUtil() {
    }

    /**
     * 写入一个sheet
     *
     * @param fileName
     * @param aClass
     * @param sheetName
     * @param data
     * @param <T>
     */
    public static <T> void excelWrite(HttpServletResponse response,
                                      String fileName,
                                      Class<?> aClass,
                                      String sheetName,
                                      Collection<T> data) throws IOException {
        try {
            HttpUtils.loadExcelResp(response, fileName);
            // 这里需要设置不关闭流
            EasyExcelFactory.write(response.getOutputStream(), aClass)
                    .autoCloseStream(Boolean.FALSE)
                    .sheet(sheetName)
                    .doWrite(data);
        } catch (Exception e) {
            log.error("excelWrite error", e);
            // 重置response
            HttpUtils.resetResp(response);
        }
    }

    /**
     * 写入一个sheet
     *
     * @param <T>
     * @param fileName
     * @param sheetName
     * @param data
     * @return
     */
    public static <T> void excelWrite(HttpServletResponse response,
                                      String fileName,
                                      List<List<String>> head,
                                      String sheetName,
                                      Collection<T> data) throws IOException {
        try {
            HttpUtils.loadExcelResp(response, fileName);
            // 这里需要设置不关闭流
            EasyExcelFactory.write(response.getOutputStream())
                    .autoCloseStream(Boolean.FALSE)
                    .sheet(sheetName)
                    .head(head)
                    .doWrite(data);
        } catch (Exception e) {
            log.error("excelWrite error", e);
            // 重置response
            HttpUtils.resetResp(response);
        }
    }

    /**
     * 读取sheet
     */
    public static <T> List<T> excelRead(InputStream inputStream, ServletUtils utils) {
        ExcelReaderBuilder read = EasyExcelFactory.read(inputStream, new ExcelListener(utils));
        //  这里可以指定sheet名字或者sheet no
        return read.sheet().doReadSync();
    }

    public static <T> List<T> excelRead(InputStream inputStream, Class<T> clazz, ServletUtils utils) {
        ExcelReaderBuilder read = EasyExcelFactory.read(inputStream, clazz, new ExcelListener(utils));
        //  这里可以指定sheet名字或者sheet no
        return read.sheet().doReadSync();
    }

}
