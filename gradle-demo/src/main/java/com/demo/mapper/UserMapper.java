package com.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.Mapper;
import com.demo.entity.Project;

/**
 * @date: 2023/7/5
 * @author: LiHaoHan
 */
@org.apache.ibatis.annotations.Mapper
public interface UserMapper extends Mapper<Project> {

    Project getPro(String id);
}
