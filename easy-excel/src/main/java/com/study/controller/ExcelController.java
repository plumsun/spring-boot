package com.study.controller;

import com.study.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

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

    @PostMapping("upload")
    public String upload(HttpServletResponse response, HttpServletRequest request, @RequestParam("file") MultipartFile file){
        return this.excelService.analyse(response,request,file);
    }
    @GetMapping("/test")
    public String test(){
        return "s";
    }

    @PostMapping("/demo")
    public void demo(@RequestBody HashMap<String,Integer> map, HttpServletRequest request){
        String qq = request.getHeader("qq");
        System.out.println("qq = " + qq);
        System.out.println("map = " + map);
    }
}
