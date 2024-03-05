package com.study.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import com.study.utils.HttpUtils;
import com.study.utils.PdfU;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The type File controller.
 *
 * @author LiHaoHan
 */
@Slf4j
@RestController
@RequestMapping("/file")
public class FileController {

    /**
     * The Pdf u.
     */
    @Resource
    PdfU pdfU;

    // todo 文件上传


    /**
     * Create pdf.
     *
     * @param name     the name
     * @param response the response
     * @throws Exception the exception
     */
    @GetMapping("/toPDF")
    public void createPDF(@RequestParam String name, HttpServletResponse response) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        String html = pdfU.generateHtml("test.ftl", map);
        ServletOutputStream outputStream = response.getOutputStream();
        pdfU.generatePdfPlus(html, outputStream);
        HttpUtils.buildPdfResp(response, name);
    }

    @GetMapping("/toExcel")
    public void toExcel(@RequestParam String name, HttpServletResponse response) throws Exception {
        this.write();
    }

    public void write() throws IOException {
        FileOutputStream outputStream = new FileOutputStream(new File("D:/test.xlsx"));
        List<List<String>> list = new ArrayList<>();
        ArrayList<String> objects1 = new ArrayList<>();
        objects1.add("1");
        ArrayList<String> objects2 = new ArrayList<>();
        objects1.add("2");
        ArrayList<String> objects3 = new ArrayList<>();
        objects1.add("3");
        list.add(objects1);
        list.add(objects2);
        list.add(objects3);
        ArrayList<String> strings = new ArrayList<>();
        ArrayList<String> title = new ArrayList<>();
        title.add("2");
        // 输出 Excel
        EasyExcel.write(outputStream)
                .includeColumnFieldNames(title) // 只要导出对象中字段的数据
                .autoCloseStream(false) // 不要自动关闭，交给 Servlet 自己处理
                .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy()) // 基于 column 长度，自动适配。最大 255 宽度
                .sheet("sheetName").doWrite(strings);
    }
}
