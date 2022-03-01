package com.demo.logdemo.feign;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.net.URI;

/**
 * @description:
 * @date: 2021/11/19 16:11
 * @author: LiHaoHan
 * @program: com.demo.logdemo.feign
 */
@Component
public class DemoRemoteHystrix implements FallbackFactory<DemoRemote> {
    @Override
    public DemoRemote create(Throwable throwable) {
        return new DemoRemote() {
            @Override
            public String getToken(URI uri) {
                return "ERROR";
            }
        };
    }
}
