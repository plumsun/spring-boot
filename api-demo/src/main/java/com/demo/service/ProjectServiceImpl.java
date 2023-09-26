package com.demo.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.entity.Project;
import com.demo.entity.ResultVo;
import com.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @date: 2023/7/5
 * @author: LiHaoHan
 */
@Service
public class ProjectServiceImpl extends ServiceImpl<UserMapper, Project> implements ProjectService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public ResultVo getData() {
        return userMapper.getPro(null);
    }
}
