package com.study.controller;

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
import java.util.HashMap;
import java.util.Map;

/**
 * @description: file控制层
 * @date: 2022/11/9 20:13
 * @author: LiHaoHan
 * @program: com.study.controller
 */
@Slf4j
@RestController
@RequestMapping("/file")
public class FileController {

    @Resource
    PdfU pdfU;

    //todo 文件上传


    @GetMapping("/toPDF")
    public void createPDF(@RequestParam String name, HttpServletResponse response) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        String html = pdfU.generateHtml("test.ftl", map);
        HttpServletResponse resp = HttpUtils.buildPdfResp(response);
        ServletOutputStream outputStream = resp.getOutputStream();
        pdfU.generatePdfPlus(html, outputStream);
    }
}
