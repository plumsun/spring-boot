package com.study.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// *
//  * @description:
//  * @date: 2021/11/4 9:44
//  * @author: LiHaoHan
//  * @program: com.study.service


@Service
public interface ExcelService{

    String analyse(HttpServletResponse response, HttpServletRequest request, MultipartFile file);
}
