package com.study.service.impl;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.study.repository.ClCodShbesDao;
import com.study.entity.ClCodShbesEntity;
import com.study.exception.ResultBaseException;
import com.study.service.ExcelService;
import com.study.util.WebServiceClientU;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;
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

    @Override
    public void rpc(Map<String,Object> map) throws Exception {
        try {
            String requestUrl = "http://192.168.12.125/MSAWebService/BoxingInfo.asmx?wsdl";
            Object containCert = map.get("containCert");
            Object ctnNo = map.get("ctnNo");
            Object containDate = map.get("containDate");
            Map<String, String> result = WebServiceClientU.callWebSVDiff(requestUrl, "CheckBoxing",
                    containCert, ctnNo, containDate, DateUtil.format(new Date(), DatePattern.PURE_DATETIME_PATTERN));
        } catch (Exception e) {
            throw e;
        }

    }
}
