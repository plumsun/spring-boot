package com.study.controller;

import com.study.entity.resp.RestResult;
import com.study.utils.PdfU;
import com.study.utils.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.FileOutputStream;
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
    public RestResult createPDF(@RequestParam String name)  {
        Map<String, Object> map = new HashMap<>();
        map.put("name",name);
        try {
            String html = pdfU.generateHtml("test.ftl", map);
            pdfU.generatePdfPlus(html,new FileOutputStream("D:\\test.pdf"),null);
            return ResultUtils.success();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtils.err();
        }
    }
}
