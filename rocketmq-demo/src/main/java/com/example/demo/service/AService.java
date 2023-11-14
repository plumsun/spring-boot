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
public class AService {
    @Resource
    private UserDao userDao;

    public List<User> get(List<Integer> ids) {
        try {
            userDao.findAllById(ids);
            return userDao.findAllById(ids);
        } catch (Exception e) {
            log.error("[AService.get()]->数据查询失败", e);
            return List.of();
        }
    }
}
