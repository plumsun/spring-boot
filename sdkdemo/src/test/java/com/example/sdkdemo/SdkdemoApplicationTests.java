package com.example.sdkdemo;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TemporalUtil;
import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

class SdkdemoApplicationTests {


    @Test
    void contextLoads() {
        int num = 7;
        String str = "2022-01-03";
        LocalDate date = LocalDate.parse(str);
        long daysDifference = ChronoUnit.DAYS.between(date, LocalDate.now());

        // 增加天数
        // LocalDate newDate = date.plusDays(daysToAdd);
    }

    @Test
    void contextLoads1() throws ParseException {
        List<Date> objects = DateUtil.rangeFunc(DateFormat.getDateInstance().parse("2024-03-27 00:00:00")
                , DateFormat.getDateInstance().parse("2024-05-01 00:00:00")
                , DateField.DAY_OF_WEEK
                , date -> date);
        System.out.println("objects = " + objects);
        // 增加天数
        // LocalDate newDate = date.plusDays(daysToAdd);
    }

    @Test
    void test() {
        // 给定的起始日期字符串和频率
        String str = "2022-01-03";
        int num = 7; // 每隔 7 天生成一个日期

        // 解析起始日期
        LocalDate startDate = LocalDate.parse(str);

        // 计算当前日期
        LocalDate currentDate = LocalDate.now();

        // 计算起始日期与当前日期之间的天数差
        long daysDifference = ChronoUnit.DAYS.between(startDate, currentDate);

        // 计算需要生成的日期个数
        long l = daysDifference % num;

        LocalDate endDate = startDate.plusDays(l);

        long daysDifference2 = ChronoUnit.DAYS.between(endDate, currentDate);

        LocalDate minus = LocalDate.now().minus(0l, ChronoUnit.DAYS);
        System.out.println("minus = " + minus);

        // 生成日期并打印结果
        System.out.println("起始日期: " + str);
        System.out.println("当前日期: " + currentDate);
        System.out.println("生成的日期:");

        LocalDate nextDate = startDate;
        // for (int i = 0; i < numDates; i++) {
        //     // 将下一个日期设为起始日期加上频率乘以当前循环次数
        //     if (nextDate.isAfter(currentDate)) {
        //         System.out.println(nextDate);
        //     }
        // }
    }

    @Test
    public void test3(){
        LocalDate nowDate = LocalDate.parse("2024-04-19");
        LocalDate visitDate = LocalDate.parse("2022-01-12");
        int frequency = 28;
        // 求出离当前时间最近的一次拜访时间（在当前日期之前）
        long daysDifference = TemporalUtil.between(visitDate, nowDate, ChronoUnit.DAYS);
        long l = daysDifference % frequency;
        visitDate = nowDate.minus(l, ChronoUnit.DAYS);
        System.out.println("visitDate = " + visitDate);
        // 计算偏移过后的日期
        LocalDate offset = TemporalUtil.offset(nowDate, 2, ChronoUnit.MONTHS);
        // 相差天数
        long betweenDay = TemporalUtil.between(visitDate, offset, ChronoUnit.DAYS);
        // 计算次数
        int visitsNumberInRange = (int) Math.floor((double) betweenDay / frequency);
        for (int i = 0; i < visitsNumberInRange; i++) {
            // 时间范围内拜访明细数据
            LocalDate date = TemporalUtil.offset(visitDate, (long) (i + 1) * frequency, ChronoUnit.DAYS);
            System.out.println("date = " + date);
        }
    }

    @Test
    public void test6(){
        LocalDate visitDate = LocalDate.parse("2022-01-12");
        int frequency = 28;
        while (true){
            visitDate = TemporalUtil.offset(visitDate, frequency, ChronoUnit.DAYS);
            System.out.println("visitDate = " + visitDate);
            if(visitDate.isAfter(LocalDate.parse("2024-04-19"))){
                return;
            }
        }
    }

}
