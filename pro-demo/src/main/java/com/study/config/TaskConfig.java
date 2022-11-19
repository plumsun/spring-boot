package com.study.config;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.study.entity.ClCodShbesEntity;
import com.study.service.OracleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;

import java.util.Date;

@Configuration
@EnableScheduling
@EnableAsync
@Slf4j
public class TaskConfig implements SchedulingConfigurer {

    @Autowired
    OracleService oracleService;

    private String cron ="0 0/1 * * * ?";

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.addTriggerTask(() ->{
            Date date = new Date();
            String format = DateUtil.format(date, DatePattern.NORM_DATETIME_PATTERN);
            log.info("定时任务启动:{},时间:{},开始调用", cron, format);
            try {
                this.oracleService.updateTime(new ClCodShbesEntity());
            } catch (Exception e) {
                log.error("Task-Error",e);
            }
            log.info( "定时任务调用完成,用时{}", DateUtil.between(date,new Date(), DateUnit.MS));
        },triggerContext -> {
            return new CronTrigger(cron).nextExecutionTime(triggerContext);
        });
    }
}
