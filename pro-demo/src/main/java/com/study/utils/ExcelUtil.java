package com.study.utils;

import cn.hutool.core.util.ReflectUtil;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.easipass.business.enums.BusinessErrorCode;
import com.easipass.business.exception.BusinessException;
import com.univocity.parsers.common.processor.RowListProcessor;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
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
                                      Collection<T> data) {
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
                                      Collection<T> data) {
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

    public static <T> List<T> csvRead(InputStream inputStream, Class<T> clazz, ServletUtils utils) {
        List<T> list = new ArrayList<>();
        try {
            CsvParserSettings parserSettings = new CsvParserSettings();
            parserSettings.setLineSeparatorDetectionEnabled(true);//借助于配置对象自动侦测输入中包含何种行分隔离符序列
            RowListProcessor rowListProcessor = new RowListProcessor();//用来将每个解析的行存储在列表中
            parserSettings.setRowProcessor(rowListProcessor);
            parserSettings.setHeaderExtractionEnabled(true);//把第一个解析行看作文件中每列的标题
            CsvParser parser = new CsvParser(parserSettings);
            parser.parse(inputStream);
            //String[] headers = rowListProcessor.getHeaders();
            List<String[]> rows = rowListProcessor.getRows();
            for (String[] row : rows) {
                String[] split = row[0].split("\t");
                T t = ReflectUtil.newInstance(clazz);
                Field[] fields = ReflectUtil.getFields(t.getClass());
                for (Field field : fields) {
                    if (field.isAnnotationPresent(ExcelProperty.class)) {
                        ExcelProperty annotation = field.getAnnotation(ExcelProperty.class);
                        int index = annotation.index();
                        if (index < split.length) {
                            String temp = split[index];
                            if (StringUtils.isNotBlank(temp)) {
                                ReflectUtil.setFieldValue(t, field, temp);
                            }
                        }
                    }
                }
                ReflectUtil.invoke(t, "accept", utils);
                list.add(t);
            }

        } catch (Exception e) {
            throw new BusinessException(BusinessErrorCode.IMPORT_INFO_EXCEPTION);
        }
        return list;
    }


}
