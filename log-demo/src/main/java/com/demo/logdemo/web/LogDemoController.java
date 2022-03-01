package com.demo.logdemo.web;

import com.demo.logdemo.service.LogDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @date: 2021/11/19 15:58
 * @author: LiHaoHan
 * @program: com.demo.logdemo.web
 */
@RequestMapping
@RestController
public class LogDemoController {
    @Autowired
    private LogDemoService logDemoService;

    @GetMapping("/test")
    public String test(@RequestParam("param")String param){
        try {
            return logDemoService.getToken(param);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
