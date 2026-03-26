package com.zljin.poc.eventdriven.listener;

import com.zljin.poc.eventdriven.events.DevEvent;
import com.zljin.poc.eventdriven.events.ReleaseEvent;
import com.zljin.poc.eventdriven.events.ReviewEvent;
import com.zljin.poc.eventdriven.events.TestEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DevOpsCycleListener {

    @EventListener
    public void handleDevEvent(DevEvent event) {
        log.info("doing dev {},{}", event.getInfo(), event.getEventType());
    }

    @EventListener
    public void handleReviewEvent(ReviewEvent event) {
        log.info("doing review {},{}", event.getInfo(), event.getEventType());
    }

    @EventListener
    public void handleTestEvent(TestEvent event) {
        log.info("doing test {},{}", event.getInfo(), event.getEventType());
    }

    @EventListener
    public void handleReleaseEvent(ReleaseEvent event) {
        log.info("doing release {},{}", event.getInfo(), event.getEventType());
    }
}
