package com.study.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.study.entity.ClBizApplyEntity;
import com.study.entity.ClCodShbesEntity;
import com.study.exception.ResultBaseException;
import com.study.repository.ClBizApplyDao;
import com.study.repository.ClCodShbesDao;
import com.study.service.OracleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;


@Slf4j
@Service
public class OracleServiceImpl implements OracleService {


    @Autowired
    private OracleService oracleService;

    @Autowired
    private ClBizApplyDao clBizApplyDao;

    @Autowired
    private ClCodShbesDao clCodShbesDao;

    /**
     * 新增和修改的功能
     *
     * @param clCodShbesEntity
     * @return
     */
    @Transactional
    @Override
    public String updShbes(ClCodShbesEntity clCodShbesEntity) throws Exception {
        Date date = new Date(new Timestamp(System.currentTimeMillis()).getTime());
        try {
            clCodShbesEntity.setCreateTime(date);
            clCodShbesEntity.setUpdateTime(date);
            clCodShbesDao.save(clCodShbesEntity);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return "添加成功";
    }

    /**
     * 更新数据
     *
     * propagation:修改事务传播行为
     *  Propagation.NOT_SUPPORTED：无事务
     * @param response
     * @param id
     * @return
     * @throws Exception
     */
    //@Transactional(propagation = Propagation.NOT_SUPPORTED)
    @Transactional(rollbackFor = Exception.class)
    @Override
    public String update(HttpServletResponse response, Long id) throws Exception {
        try {
            String str = "s";
            Optional<ClCodShbesEntity> optional = this.clCodShbesDao.findById(id);
            ClCodShbesEntity entity = optional.get();
            entity.setOperPlaceName("3");
            this.updateTime1(entity);
            return str;
        } catch (Exception e) {
            log.error("error",e);
            //当抛出自定义异常时，需要将catch到异常的case信息也抛出
            throw new ResultBaseException(500, "系统异常", "500", "程序出问题了，请稍后再试",e.getCause());
        }
    }

    /**
     * 更新数据
     *
     * rollbackFor:修改事务异常捕获范围
     * @param entity
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateTime(ClCodShbesEntity entity) throws Exception {
        entity.setUpdateTime(new Date());
        this.clCodShbesDao.save(entity);
        int i = 1 / 0;
    }

    private void updateTime1(ClCodShbesEntity entity) throws Exception {
        entity.setUpdateTime(new Date());
        this.clCodShbesDao.save(entity);
        int i = 1 / 0;
    }

    /**
     * 更新数据
     * @param entity
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateData(ClCodShbesEntity entity) throws Exception {
        try {
            entity.setOperPlaceName("外高桥3期");
            this.save(entity);
        } catch (Exception e) {
            log.error("error",e);
            throw new RuntimeException();
        }
    }

    /**
     * 保存数据
     * @param clCodShbes
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public String save(ClCodShbesEntity clCodShbes) {
        this.clCodShbesDao.save(clCodShbes);
        int i = 1 / 0;
        return "s";
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String save(ClBizApplyEntity entity) {
        ClBizApplyEntity save = this.clBizApplyDao.save(entity);
        return JSONObject.toJSONString(save);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteFlag(Long id) {
        this.clBizApplyDao.deleteById(id);
    }

    @Override
    public String findData(Long id) {
        Optional<ClBizApplyEntity> optional = this.clBizApplyDao.findById(id);
        ClBizApplyEntity clBizApplyEntity = optional.get();
        return JSONObject.toJSONString(clBizApplyEntity);
    }
}
