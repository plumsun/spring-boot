package com.study.job;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.study.entity.ClCodShbesEntity;
import com.study.service.OracleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;

import java.util.Date;

/**
 * spring 原生定时任务demo
 *
 * @author User
 */
@Configuration
@EnableScheduling
@EnableAsync
@Slf4j
public class SpringTasks implements SchedulingConfigurer {

    @Autowired
    OracleService oracleService;

    @Value("${task.cron}")
    private String cron;

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.addTriggerTask(() -> {
            Date date = new Date();
            String format = DateUtil.format(date, DatePattern.NORM_DATETIME_PATTERN);
            log.info("定时任务启动:{},时间:{},开始调用", cron, format);
            try {
                this.oracleService.updateTime(new ClCodShbesEntity());
            } catch (Exception e) {
                log.error("Task-Error", e);
            }
            log.info("定时任务调用完成,用时{}", DateUtil.between(date, new Date(), DateUnit.MS));
        }, triggerContext -> new CronTrigger(cron).nextExecutionTime(triggerContext));
    }
}
