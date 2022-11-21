package com.study.util;

import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * @author LiHaoHan
 * @date 2022/11/21
 */
public class DateUtil {

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
}
