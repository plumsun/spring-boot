package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author LiHaoHan Created on 2023/11/2
 */
@Slf4j
@Service
public class AServiceImpl implements AService{
    @Resource
    private UserDao userDao;
    @Override
    public List<User> get(List<Integer> ids) {
        try {
            userDao.findAllById(ids);
            return userDao.findAllById(ids);
        } catch (Exception e) {
            log.error("[AService.get()]->数据查询失败", e);
            return List.of();
        }
    }

    @Override
    // @Transactional(rollbackFor = Exception.class)
    public void test(){
        System.out.println("retry................");
            int i = 1 / 0;
    }

    @Override
    public void recover() {
        System.out.println("Recover................");
    }
}
