package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.mapper.CacheMapper;
import com.example.demo.pojo.CacheNumber;
import com.example.demo.service.CacheService;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @date: 2022/8/2 19:00
 * @author: LiHaoHan
 * @program: com.example.demo.service.impl
 */
@Service
public class CacheServiceImpl extends ServiceImpl<CacheMapper, CacheNumber> implements CacheService {
}
