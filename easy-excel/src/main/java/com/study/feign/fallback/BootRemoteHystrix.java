package com.study.feign.fallback;

import com.study.feign.BootRemote;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @date: 2021/12/3 12:09
 * @author: LiHaoHan
 * @program: com.study.feign.fallback
 */
@Component
public class BootRemoteHystrix implements FallbackFactory<BootRemote> {

    @Override
    public BootRemote create(Throwable throwable) {
        return new BootRemote() {
            @Override
            public String test() {
                return null;
            }
        };
    }
}
