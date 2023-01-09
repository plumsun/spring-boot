package com.study.controller;

import com.study.entity.resp.RestResult;
import com.study.utils.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: other控制层
 * @date: 2022/11/9 20:42
 * @author: LiHaoHan
 * @program: com.study.controller
 */
@Slf4j
@RestController
@RequestMapping("demo")
public class DemoController {

    /**
     * 公用response测试
     *
     * @return
     * @throws Exception
     */
    @PostMapping("result")
    public RestResult result() throws Exception {
        return ResultUtils.success("成功");
    }
}
