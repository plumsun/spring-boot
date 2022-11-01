package com.study.service;

import com.study.entity.ClCodShbesEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;


@Service
public interface ExcelService{

    /**
     * 新增和修改的功能
     * @param clCodShbesEntity
     * @return
     * @throws Exception
     */
    String updShbes(ClCodShbesEntity clCodShbesEntity) throws Exception;

    /**
     * 更新数据
     * @param response
     * @param id
     * @return
     * @throws Exception
     */
    String update( HttpServletResponse response,Long id) throws Exception;

    /**
     * 更新数据
     * @param entity
     * @throws Exception
     */
    void updateData(ClCodShbesEntity entity) throws Exception;

    /**
     * 更新时间
     * @param entity
     * @throws Exception
     */
    void updateTime(ClCodShbesEntity entity) throws Exception;

    /**
     * 保存数据
     * @param clCodShbes
     * @return
     */
    String save(ClCodShbesEntity clCodShbes);


    /**
     * 远程调用
     */
    void rpc(Map<String,Object> map) throws Exception;
}
