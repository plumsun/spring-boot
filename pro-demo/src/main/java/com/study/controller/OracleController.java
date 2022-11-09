package com.study.controller;

import com.study.entity.ClCodShbesEntity;
import com.study.service.OracleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * @description:
 * @date: 2022/11/9 20:57
 * @author: LiHaoHan
 * @program: com.study.controller
 */
@Slf4j
@RestController
@RequestMapping("oracle")
public class OracleController {

    @Resource
    private OracleService oracleService;

    /**
     * 更新数据
     * @param id
     * @param response
     * @return
     * @throws Exception
     */
    @PostMapping("update")
    public String update(@RequestParam Long id, HttpServletResponse response) throws Exception {
        return this.oracleService.update(response, id);
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
        System.out.println("clCodShbes = " + clCodShbes);
        return this.oracleService.save(clCodShbes);
    }
}
