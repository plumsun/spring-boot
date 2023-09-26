package com.study.controller;

import com.study.service.AopService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * aop控制层
 *
 * @author LiHaoHan
 */
@Slf4j
@RestController
@RequestMapping("aop")
public class AopController {


    @Resource
    private AopService aopService;


    /**
     * 切面测试，请求日志打印
     *
     * @param request the request
     * @param id      the id
     * @param map     the map
     * @return string
     * @throws Exception the exception
     */
    @PostMapping("pointTest")
    public String upload(HttpServletRequest request,@RequestParam("id") String id,@RequestBody HashMap<String, Integer> map) throws Exception{
        return id;
    }

    /**
     * 测试request对象
     *
     * @param request the request
     * @return string
     */
    @GetMapping("/test")
    public String test(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        System.out.println("request = " + requestURI);
        return "s";
    }

    /**
     * 测试aop日志打印
     *
     * @param map     the map
     * @param request the request
     */
    @PostMapping("/demo")
    public void demo(@RequestBody HashMap<String, Integer> map, HttpServletRequest request) {
        String qq = request.getHeader("qq");
        System.out.println("qq = " + qq);
        System.out.println("map = " + map);
    }

    /**
     * 异步方法异常测试
     *
     * @return the string
     * @throws Exception the exception
     */
    @GetMapping("async")
    public String asyncTest() throws Exception {
        this.aopService.asyncTest();
        return "";
    }

    /**
     * 异步方法异常测试
     *
     * @return the string
     * @throws Exception the exception
     */
    @GetMapping("asyncVoid")
    public String asyncVoid() throws Exception {
        this.aopService.asyncVoidTest();
        return "";
    }
}
