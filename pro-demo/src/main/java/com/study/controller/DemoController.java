package com.study.controller;

import com.study.entity.Test;
import com.study.entity.resp.RestResult;
import com.study.utils.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Demo controller.
 *
 * @author LiHaoHan
 */
@Slf4j
@RestController
@RequestMapping("demo")
public class DemoController {

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
}
