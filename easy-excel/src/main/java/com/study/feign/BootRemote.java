package com.study.feign;

import com.study.feign.fallback.BootRemoteHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @description:
 * @date: 2021/12/3 12:07
 * @author: LiHaoHan
 * @program: com.study
 */
@FeignClient(name = "easy", fallback = BootRemoteHystrix.class)
public interface BootRemote {

    @GetMapping("/test")
    String test();
}
