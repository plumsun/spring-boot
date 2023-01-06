package com.study.service.impl;

import com.study.repository.ClBizApplyDao;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;


/**
 * @author LiHaoHan
 * @date 2022/12/7
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class OracleTest {

    @NotNull
    @Autowired
    private ClBizApplyDao clBizApplyDao;


    @Test
    public void getBefore(){
        System.out.println("entity:" + this.clBizApplyDao.findById(408L));
    }

    @Transactional(rollbackFor = Exception.class)
    @Test
    public void deleteFlagTest() {
        this.clBizApplyDao.deleteById(408L);
    }
}