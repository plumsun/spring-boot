package com.study.controller;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.study.entity.ClCodShbesEntity;
import com.study.entity.RestResult;
import com.study.entity.ResultBaseException;
import com.study.entity.Test;
import com.study.service.ExcelService;
import com.study.util.WebServiceClientU;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @date: 2021/11/4 9:42
 * @author: LiHaoHan
 * @program: com.study.controller
 */
@Slf4j
@RestController
@RequestMapping("excel")
public class ExcelController {

    @Autowired
    private ExcelService excelService;

    @Autowired
    private StringRedisTemplate redisTemplate;

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

    @GetMapping("/test")
    public String test(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        System.out.println("request = " + requestURI);
        return "s";
    }

    @PostMapping("/demo")
    public void demo(@RequestBody HashMap<String, Integer> map, HttpServletRequest request) {
        String qq = request.getHeader("qq");
        System.out.println("qq = " + qq);
        System.out.println("map = " + map);
    }

    /**
     * webSerivce超时测试
     *
     * @param map
     * @throws Exception
     */
    @PostMapping("web")
    public void checkBoxing(@RequestBody HashMap map) throws Exception {
        ArrayList<String> strings = new ArrayList<>();
        try {
            this.excelService.rpc(map);
        } catch (RuntimeException e){
            throw e;
        } catch (IOException e){
            log.error("io",e);
        }
    }

    /**
     * 更新数据
     * @param id
     * @param response
     * @return
     * @throws Exception
     */
    @PostMapping("update")
    public String update(@RequestParam Long id, HttpServletResponse response) throws Exception {
        return this.excelService.update(response, id);
    }

    /**
     * 保存数据
     * @param clCodShbes
     * @param response
     * @return
     * @throws Exception
     */
    @PostMapping("save")
    public String save(@RequestBody ClCodShbesEntity clCodShbes, HttpServletResponse response) throws Exception {
        return this.excelService.save(clCodShbes);
    }

    /**
     * 公用response测试
     * @return
     * @throws Exception
     */
    @PostMapping("result")
    public RestResult<Object> result() throws Exception {
        return RestResult.T("成功");
    }

    /**
     * redis测试
     */
    @PostMapping("redis")
    public void redisTest() {
        ArrayList<Test> list = new ArrayList<>();
        list.add(new Test("1", "li"));
        list.add(new Test("1", "li"));
        redisTemplate.boundListOps("s").leftPush(JSONUtil.toJsonStr(list));
        list.add(new Test("1", "li"));
        list.add(new Test("1", "li"));
        redisTemplate.boundListOps("s").leftPush(JSONUtil.toJsonStr(list));
        redisTemplate.opsForList();
    }
}
