package com.study.service.impl;

import cn.hutool.http.server.HttpServerResponse;
import com.study.config.ClCodShbesDao;
import com.study.config.GlobalRestExceptionHandler;
import com.study.entity.ClCodShbesEntity;
import com.study.entity.ResultBaseException;
import com.study.service.ExcelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;


@Slf4j
@Transactional(rollbackFor = Exception.class)
@Service
public class ExcelServiceImpl implements ExcelService {


    @Autowired
    ExcelService excelService;

    @Autowired
    protected ClCodShbesDao clCodShbesDao;

    /**
     * 新增和修改的功能
     *
     * @param clCodShbesEntity
     * @return
     */
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

    @Override
    public String update(HttpServletResponse response, Long id) throws Exception {
        try {
            String str = "s";
            Optional<ClCodShbesEntity> optional = this.clCodShbesDao.findById(id);
            ClCodShbesEntity entity = optional.get();
            entity.setOperPlaceName("3");
            this.clCodShbesDao.save(entity);
            excelService.updateTime(entity);
            return str;
        } catch (Exception e) {
            //当抛出自定义异常时，需要将catch到异常的case信息也抛出
            throw new ResultBaseException(500, "系统异常", "500", "程序出问题了，请稍后再试",e.getCause());
        }
    }

    @Override
    public void updateTime(ClCodShbesEntity entity) throws Exception {
        int i = 1 / 0;
    }

    @Override
    public void updateData(ClCodShbesEntity entity) throws Exception {
        try {
            entity.setOperPlaceName("外高桥3期");
            this.clCodShbesDao.save(entity);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }


    @Override
    public String save(ClCodShbesEntity clCodShbes) {
        this.clCodShbesDao.save(clCodShbes);
        return "s";
    }
}
