package com.zljin.poc.eventdrivenv2.handler;

import com.zljin.poc.eventdrivenv2.bean.Event;

public interface EventHandler {

    void handle(Event event);
}
