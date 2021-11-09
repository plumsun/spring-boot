package com.study.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.study.entity.User;
import org.springframework.stereotype.Repository;

/**
 * @description:
 * @date: 2021/11/4 10:17
 * @author: LiHaoHan
 * @program: com.study.dao
 */
@Repository
public interface UserMapper extends BaseMapper<User> {
}
