package com.study.util;

import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @description:
 * @date: 2022/11/12 10:17
 * @author: LiHaoHan
 * @program: com.study.util
 */
public class TraceIdUtils {

    public static StringBuilder createTraceId() throws UnknownHostException {
        StringBuilder traceId = new StringBuilder();
        //获取当前主机ip
        String localHost = InetAddress.getLocalHost().getHostAddress().replaceAll("\\.","");
        String hex = intToHex(new Integer(localHost));
        //获取当前进程id
        String[] split = ManagementFactory.getRuntimeMXBean().getName().split("@");
        traceId.append(hex).append(System.currentTimeMillis()).append(split[0]);
        return traceId;
    }

    private static String intToHex(int n) {
        StringBuilder sb = new StringBuilder(8);
        String a;
        char []b = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        while(n != 0){
            sb = sb.append(b[n%16]);
            n = n/16;
        }
        a = sb.reverse().toString();
        return a;
    }
}
