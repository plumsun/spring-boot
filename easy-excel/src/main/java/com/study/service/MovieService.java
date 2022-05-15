package com.study.service;

import com.study.entity.MultipartFileParam;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @description:
 * @date: 2022/4/19 20:03
 * @author: LiHaoHan
 * @program: com.study.service
 */
@Service
public interface MovieService {


    String playAndDownload(String range, HttpServletRequest request, HttpServletResponse response, String fileName);

    String upload(HttpServletRequest request, HttpServletResponse response, MultipartFileParam param);

    Integer check(String filNameMD5,String fileName);

    void fileMerge(HttpServletRequest request);

}
