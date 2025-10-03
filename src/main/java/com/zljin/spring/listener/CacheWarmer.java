package com.zljin.spring.listener;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 缓存异步预热
 * ApplicationReadyEvent: 等所有 Bean 都初始化完成后再执行预热，不会影响应用启动
 * +线程池异步操作
 */
@Component
public class CacheWarmer implements ApplicationListener<ApplicationReadyEvent> {

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        System.out.println("应用就绪，开始预热所有缓存...");
        //do 异步操作...
    }
}
