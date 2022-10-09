package com.study.service;

import com.study.entity.ClCodShbesEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




@Service
public interface ExcelService{

    String updShbes(ClCodShbesEntity clCodShbesEntity) throws Exception;

    String update(Long id) throws Exception;

    void updateData(ClCodShbesEntity entity) throws Exception;

    void updateTime(ClCodShbesEntity entity) throws Exception;
}
