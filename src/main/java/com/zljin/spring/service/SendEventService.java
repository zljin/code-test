package com.zljin.spring.service;

import com.zljin.spring.event.SampleEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class SendEventService {

    @Autowired
    ApplicationContext applicationContext;

    public void sendEvent(String info) {
        applicationContext.publishEvent(new SampleEvent(this,info));
    }
}
