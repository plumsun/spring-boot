package com.study.service.impl;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.study.service.FtpService;
import com.study.utils.WebServiceClientUs;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * @description:
 * @date: 2022/11/9 20:48
 * @author: LiHaoHan
 * @program: com.study.service.impl
 */
@Slf4j
@Service
public class FtpServiceImpl implements FtpService {


    @Override
    public void timeOut(Map<String, Object> map) throws Exception {
        try {
            String requestUrl = "http://192.168.12.125/MSAWebService/BoxingInfo.asmx?wsdl";
            Object containCert = map.get("containCert");
            Object ctnNo = map.get("ctnNo");
            Object containDate = map.get("containDate");
            Map<String, String> result = WebServiceClientUs.callWebSVDiff(requestUrl, "CheckBoxing",
                    containCert, ctnNo, containDate, DateUtil.format(new Date(), DatePattern.PURE_DATETIME_PATTERN));
        } catch (Exception e) {
            throw e;
        }

    }
}
