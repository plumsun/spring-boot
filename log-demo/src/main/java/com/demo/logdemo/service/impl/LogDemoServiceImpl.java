package com.demo.logdemo.service.impl;

import com.demo.logdemo.feign.DemoRemote;
import com.demo.logdemo.service.LogDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;

/**
 * @description:
 * @date: 2021/11/19 15:59
 * @author: LiHaoHan
 * @program: com.demo.logdemo.service.impl
 */
@Service
public class LogDemoServiceImpl implements LogDemoService {

    @Autowired
    private DemoRemote demoRemote;

    @Override
    public String getToken(String param) throws Exception {
        URI uri = new URI("http://10.186.204.72:8003/video-stream-srv/scheduled/getVrBoxAccessToken");
        return demoRemote.getToken(uri);
    }
}
