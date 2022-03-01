package com.demo.logdemo.feign;

import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;

import java.net.URI;

/**
 * @description:
 * @date: 2021/11/19 15:57
 * @author: LiHaoHan
 * @program: com.demo.logdemo.feign
 */
@FeignClient(name = "mixStream", fallback = DemoRemoteHystrix.class)
public interface DemoRemote {
    @RequestLine("GET")
    String getToken(URI uri);
}
