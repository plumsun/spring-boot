package com.study.controller;

import cn.hutool.core.lang.hash.Hash;
import com.study.entity.ClCodShbesEntity;
import com.study.service.ExcelService;
import oracle.jdbc.proxy.annotation.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
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

    @PostMapping("updShbes")
    @Transactional(rollbackFor = Exception.class)
    public String updShbes(@RequestBody HashMap  map) throws Exception {
        return "";
    }

    @PostMapping("update")
    public String update(@RequestParam Long id) throws Exception {
       return this.excelService.update(id);
    }
}
