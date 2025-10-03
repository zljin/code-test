package com.zljin.spring.event;

import org.springframework.context.ApplicationEvent;

public class SampleEvent extends ApplicationEvent {

    private String info;

    public SampleEvent(Object source,String info) {
        super(source);
        this.info = info;
    }

    public String getInfo() {
        return info;
    }
}
