package com.study.controller;

import cn.hutool.json.JSONUtil;
import com.study.entity.RestResult;
import com.study.entity.Test;
import com.study.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
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

    @Autowired
    private StringRedisTemplate redisTemplate;

    @PostMapping("upload")
    public String upload(HttpServletResponse response, HttpServletRequest request, @RequestParam("file") MultipartFile file){
        return "s";
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

    @PostMapping("updShbes")
    @Transactional(rollbackFor = Exception.class)
    public String updShbes(@RequestBody HashMap  map) throws Exception {
        return "";
    }

    @PostMapping("update")
    public String update(@RequestParam Long id,HttpServletResponse response) throws Exception {
       return this.excelService.update(response,id);
    }

    @PostMapping("result")
    public RestResult<Object> result() throws Exception {
        return RestResult.T("成功");
    }

    @PostMapping("redis")
    public void redisTest(){
        ArrayList<Test> list = new ArrayList<>();
        list.add(new Test("1","li"));
        list.add(new Test("1","li"));
        redisTemplate.boundListOps("s").leftPush(JSONUtil.toJsonStr(list));
        list.add(new Test("1","li"));
        list.add(new Test("1","li"));
        redisTemplate.boundListOps("s").leftPush(JSONUtil.toJsonStr(list));
        redisTemplate.opsForList();
    }
}
