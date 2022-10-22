package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.pojo.CacheNumber;
import org.apache.ibatis.annotations.Mapper;

/**
 * @description:
 * @date: 2022/8/2 18:56
 * @author: LiHaoHan
 * @program: com.example.demo.mapper
 */
@Mapper
public interface CacheMapper extends BaseMapper<CacheNumber> {
}
