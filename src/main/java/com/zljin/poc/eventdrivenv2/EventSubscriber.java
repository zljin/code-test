package com.zljin.poc.eventdrivenv2;

import com.zljin.poc.eventdrivenv2.bean.Event;
import com.zljin.poc.eventdrivenv2.handler.EventHandler;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Flow;

@Slf4j
@Component
public class EventSubscriber implements Flow.Subscriber<Event> {

    @Resource
    private Map<String, EventHandler> subscriberMap = new HashMap<>();

    private Flow.Subscription subscription;

    private static final int BACK_PRESSURE_COUNT = 1;

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        log.info("EventSubscriber 已订阅，请求 1 个事件");
        subscription.request(BACK_PRESSURE_COUNT);
    }

    @Override
    public void onNext(Event item) {
        try {
            log.info("收到事件: {}, 类型: {}", item.getName(), item.getEventType());
            EventHandler handler = subscriberMap.get(item.getEventType());
            if (handler != null) {
                handler.handle(item);
            } else {
                log.warn("未找到类型为 {} 的事件处理器", item.getEventType());
            }
        } catch (Exception e) {
            log.error("处理事件时出错: {}", e.getMessage(), e);
        } finally {
            // 处理完当前事件后，请求下一个事件
            subscription.request(BACK_PRESSURE_COUNT);
        }
    }

    @Override
    public void onError(Throwable throwable) {
        log.error("事件流发生错误: {} 会终止订阅", throwable.getMessage(), throwable);
    }

    @Override
    public void onComplete() {
        log.info("事件流结束");
    }
}

