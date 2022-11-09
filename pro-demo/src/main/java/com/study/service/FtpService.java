package com.study.service;

import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @description:
 * @date: 2022/11/9 20:46
 * @author: LiHaoHan
 * @program: com.study.service
 */
@Service
public interface FtpService {

    void timeOut(Map<String, Object> map) throws Exception;
}
