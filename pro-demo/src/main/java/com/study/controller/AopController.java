package com.study.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * @description: aop控制层
 * @date: 2021/11/4 9:42
 * @author: LiHaoHan
 * @program: com.study.controller
 */
@Slf4j
@RestController
@RequestMapping("aop")
public class AopController {

    /**
     * 切面测试，请求日志打印
     * @param request
     * @param id
     * @param map
     * @return
     * @throws Exception
     */
    @PostMapping("pointTest")
    public String upload(HttpServletRequest request,@RequestParam("id") String id,@RequestBody HashMap<String, Integer> map) throws Exception{
        return id;
    }

    /**
     * 测试request对象
     * @param request
     * @return
     */
    @GetMapping("/test")
    public String test(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        System.out.println("request = " + requestURI);
        return "s";
    }

    /**
     * 测试aop日志打印
     * @param map
     * @param request
     */
    @PostMapping("/demo")
    public void demo(@RequestBody HashMap<String, Integer> map, HttpServletRequest request) {
        String qq = request.getHeader("qq");
        System.out.println("qq = " + qq);
        System.out.println("map = " + map);
    }

}
