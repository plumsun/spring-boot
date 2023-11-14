package com.study.controller;

import com.study.entity.Test;
import com.study.entity.resp.RestResult;
import com.study.service.DemoService;
import com.study.utils.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * The type Demo controller.
 *
 * @author LiHaoHan
 */
@Slf4j
@RestController
@RequestMapping("demo")
public class DemoController {


    @Resource
    private DemoService demoService;

    /**
     * 公用response测试
     *
     * @return rest result
     * @throws Exception the exception
     */
    @PostMapping("result")
    public RestResult result() throws Exception {
        return ResultUtils.success("成功");
    }

    /**
     * Get rest result.
     *
     * @param test the test
     * @return the rest result
     */
    @PostMapping("get")
    public RestResult get(@RequestBody Test test){
        return ResultUtils.success(test);
    }

    @GetMapping("/thread-demo")
    public RestResult threadDemo(){
        this.demoService.threadTest();
        return ResultUtils.success();
    }
}
