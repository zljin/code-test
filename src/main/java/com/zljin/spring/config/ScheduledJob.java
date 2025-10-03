package com.zljin.spring.config;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@EnableScheduling
@Component
public class ScheduledJob {

    @Scheduled(fixedRate = 5000) //5s执行一次
    public void generateReport(){
        System.out.println("报表生成");
    }

    @Scheduled(fixedDelay = 5000) //上次执行完后等待5s再执行
    public void syncData(){
        System.out.println("数据同步");
    }

    //10s执行一次
    @Scheduled(cron = "0/10 * * * * ?") //10s一次
    public void cronJob1(){
        System.out.println("缓存刷新");
    }
}
