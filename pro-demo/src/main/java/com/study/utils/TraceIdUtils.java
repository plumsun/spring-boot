package com.study.utils;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

/**
 * @description:
 * @date: 2022/11/12 10:17
 * @author: LiHaoHan
 * @program: com.study.util
 */
@Slf4j
public class TraceIdUtils {

    public static final String TRACE_ID = "traceId";

    private static final ThreadLocal<String> local = new ThreadLocal<>();

    private TraceIdUtils() {
        throw new IllegalStateException("Utility class");
    }


    /**
     * get traceId
     */
    public static String getTraceId() {
        return local.get();
    }

    public static void setTraceId(String traceId) {
        if (traceId.isEmpty()) {
            traceId = createTraceId();
        }
        local.set(traceId);
        MDC.put(TRACE_ID, traceId);
    }

    /**
     * remove traceId from the current thread
     */
    public static void remove() {
        if (local.get().isEmpty()) {
            return;
        }
        local.remove();
    }

    /**
     * Create new traceId
     */
    public static String createTraceId() {
        final StringBuilder traceId = new StringBuilder();
        //获取当前主机ip
        String localHost = null;
        try {
            localHost = InetAddress.getLocalHost().getHostAddress().replace("\\.", "");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        log.info("localhost:{}", localHost);
        String hex = intToHex(localHost);
        //获取当前进程id
        String[] split = ManagementFactory.getRuntimeMXBean().getName().split("@");
        log.info("split:{}", Arrays.toString(split));
        traceId.append(hex).append(System.currentTimeMillis()).append(split[0]);
        final String str = traceId.toString();
        log.info("traceId:{}", str);
        return str;
    }

    private static String intToHex(String ip) {
        StringBuilder sb = new StringBuilder(8);
        String a;
        long num = Long.parseLong(ip);
        char[] b = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        while (num != 0) {
            sb.append(b[(int) (num % 16)]);
            num = num / 16;
        }
        a = sb.reverse().toString();
        return a;
    }
}
