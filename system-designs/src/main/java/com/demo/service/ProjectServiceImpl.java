package com.demo.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.demo.entity.Project;
import com.demo.entity.ResultVo;
import com.demo.mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;

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

    @Override
    public String getPrepareSql() {
        String str = "";
        SqlSession sqlSession = SqlHelper.sqlSession(Project.class);
        try (PreparedStatement statement = sqlSession.getConnection().prepareStatement("select * from project where `id` = ?;")) {
            statement.setString(1, "sd");
            str = statement.toString();
        } catch (Exception e) {
            log.error("获取sql失败", e);
        }
        return str;
    }
}
