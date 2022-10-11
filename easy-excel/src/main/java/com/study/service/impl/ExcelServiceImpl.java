package com.study.service.impl;

import cn.hutool.http.server.HttpServerResponse;
import com.study.config.ClCodShbesDao;
import com.study.config.GlobalRestExceptionHandler;
import com.study.entity.ClCodShbesEntity;
import com.study.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;


@Transactional(rollbackFor = Exception.class)
@Service
public class ExcelServiceImpl implements ExcelService{


    @Autowired
    ExcelService ExcelService;

    @Autowired
    protected ClCodShbesDao clCodShbesDao;

    /**
     * 新增和修改的功能
     * @param clCodShbesEntity
     * @return
     */
    public String updShbes(ClCodShbesEntity clCodShbesEntity) throws Exception{
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

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @Override
    public String update(HttpServletResponse response, Long id) throws Exception {
        try {
            String str ="s";
            Optional<ClCodShbesEntity> optional = this.clCodShbesDao.findById(id);
            ClCodShbesEntity entity = optional.get();
            this.ExcelService.updateData(entity);
            this.ExcelService.updateTime(entity);
            System.out.println("entity = " + entity);
            int i = 1/0;
            return str;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(id.toString());
        }
    }

    public void updateTime(ClCodShbesEntity entity) throws Exception{
            entity.setOperPlaceName("2");;
            this.clCodShbesDao.save(entity);
            //int i = 1/0;
    }

    public void updateData(ClCodShbesEntity entity) throws Exception{
        try {
            entity.setOperPlaceName("外高桥3期");
            this.clCodShbesDao.save(entity);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
