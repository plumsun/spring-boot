package com.example.demo.config;

import com.example.demo.service.AService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Demo controller.
 *
 * @author LiHaoHan
 */
@RestController
@RequestMapping("demo")
public class DemoController {


    @Autowired
    private AService aService;

    /**
     * 公用response测试
     *
     * @return rest result
     * @throws Exception the exception
     */
    @PostMapping("result")
    public void result() throws Exception {
        aService.test();
    }
}
