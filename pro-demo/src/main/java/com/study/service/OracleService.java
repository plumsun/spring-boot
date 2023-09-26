package com.study.service;

import com.study.entity.ClBizApplyEntity;
import com.study.entity.ClCodShbesEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;


@Service
public interface OracleService {

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
     * 保存数据
     * @param entity
     * @return
     */
    String save(ClBizApplyEntity entity);

    /**
     * 逻辑删除操作
     * @param id
     * @return
     */
    void deleteFlag(Long id);

    /**
     *
     */
    String findData(Long id);

    /**
     *
     */
    void getCount();
}
