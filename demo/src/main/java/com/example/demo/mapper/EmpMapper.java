package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.pojo.Emp;
import org.apache.ibatis.annotations.Mapper;

/**
 * @description:
 * @date: 2022/7/22 15:09
 * @author: LiHaoHan
 * @program: com.example.demo.mapper
 */
@Mapper
public interface EmpMapper extends BaseMapper<Emp> {

}
