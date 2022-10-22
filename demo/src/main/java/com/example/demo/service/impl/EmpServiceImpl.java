package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.mapper.EmpMapper;
import com.example.demo.pojo.Emp;
import com.example.demo.service.EmpService;
import org.springframework.stereotype.Service;


/**
 * @description:
 * @date: 2022/7/22 15:10
 * @author: LiHaoHan
 * @program: com.example.demo.service.impl
 */
@Service
public class EmpServiceImpl extends ServiceImpl<EmpMapper,Emp> implements EmpService {

}
