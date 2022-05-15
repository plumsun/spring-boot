package com.study.service.impl;

import com.study.entity.FileUtils;
import com.study.service.ExcelService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


// *
//  * @description:
//  * @date: 2021/11/4 9:45
//  * @author: LiHaoHan
//  * @program: com.study.service.impl


@Service
public class ExcelServiceImpl implements ExcelService{


    @Override
    public String analyse(HttpServletResponse response, HttpServletRequest request, MultipartFile file) {
        try {
            file.getInputStream();
            FileUtils.downloadPart(response,request,file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "成功";
    }
}
