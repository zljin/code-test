package com.zljin.poc.eventdrivenv2.handler.impl;

import com.zljin.poc.eventdrivenv2.bean.Event;
import com.zljin.poc.eventdrivenv2.handler.EventHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DevEventHandler implements EventHandler {
    @Override
    public void handle(Event event) {
        log.info("dev event handler");
        log.info("event:{}", event);
    }
}
