package com.study.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.study.entity.resp.RestResult;
import com.study.utils.ObjectUtils;
import com.study.utils.PdfU;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * @author LiHaoHan
 * @date 2023/1/5
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ObjTest {

    @Resource
    PdfU pdfU;


    @Test
    public void objFieldTest() {
        com.study.entity.Test test = new com.study.entity.Test();
        RestResult result = ObjectUtils.validate(test);
        String s = JSONObject.toJSONString(result);
        System.out.println("s = " + s);
    }

    @Test
    public void templateTest() throws Exception {
        String html = pdfU.generateHtml("background.ftl", new HashMap<>());
    }
}
