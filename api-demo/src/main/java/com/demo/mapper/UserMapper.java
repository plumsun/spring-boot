package com.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.entity.Project;
import com.demo.entity.QueryVo;
import com.demo.entity.ResultVo;

/**
 * @date: 2023/7/5
 * @author: LiHaoHan
 */
@org.apache.ibatis.annotations.Mapper
public interface UserMapper extends BaseMapper<Project> {

    ResultVo getPro(QueryVo id);

}
