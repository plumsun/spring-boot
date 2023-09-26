package com.demo.controller;

import com.demo.model.Response;
import com.demo.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 测试
 *
 * @author LiHaoHan
 * @date 2023/2/17
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private ProjectService projectService;

    @GetMapping("/")
    public Response get(){
        return Response.success();
    }

    @GetMapping("/feign")
    public Response feign(){
        return Response.success();
    }

    @GetMapping("/get")
    public Response getData(){
        return Response.success(projectService.getData());
    }
}
