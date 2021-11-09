package com.study.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.dao.UserMapper;
import com.study.entity.User;
import com.study.listener.ExcelListener;
import com.study.service.ExcelService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


/**
 * @description:
 * @date: 2021/11/4 9:45
 * @author: LiHaoHan
 * @program: com.study.service.impl
 */
@Service
public class ExcelServiceImpl extends ServiceImpl<UserMapper, User> implements ExcelService{


    @Override
    public String analyse(MultipartFile file) {
        try {
            EasyExcel.read(file.getInputStream(), new ExcelListener<>(this)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
