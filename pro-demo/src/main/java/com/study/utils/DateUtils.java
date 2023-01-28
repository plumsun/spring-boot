package com.study.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

/**
 * @author LiHaoHan
 * @date 2022/11/21
 */
public class DateUtils {

    private DateUtils() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 获取当前系统时间戳
     *
     * @return str
     */
    public static String currentMillisecondTimestamp() {
        //获取毫秒数
        long time = LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        return String.valueOf(time);
    }

    /**
     * 计算两个时间的差值，如果结束时间早于开始时间结果为负数
     *
     */
    public static long between(LocalDateTime startTime, LocalDateTime endTime, ChronoUnit unit){
        return unit.between(startTime,endTime);
    }
}
