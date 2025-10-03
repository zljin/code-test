package com.zljin.spring.listener;

import com.zljin.spring.event.SampleEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class SampleListener {
    @EventListener
    public void listen(SampleEvent event) {
        System.out.println("监听消费中 ========> "+event.getInfo());
    }
}
