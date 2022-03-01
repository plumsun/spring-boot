package com.study.controller;

import com.study.feign.BootRemote;
import com.study.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @description:
 * @date: 2021/11/4 9:42
 * @author: LiHaoHan
 * @program: com.study.controller
 */
@RestController
@RequestMapping("excel")
public class ExcelController {

    @Autowired
    private ExcelService excelService;

    @Autowired
    BootRemote bootRemote;

    @PostMapping("upload")
    public String upload(@RequestParam("file")MultipartFile file){
        return "this.excelService.analyse(file)";
    }

    @GetMapping("test")
    public String test(){
        return bootRemote.test();
    }
}
