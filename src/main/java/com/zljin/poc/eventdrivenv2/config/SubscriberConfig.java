package com.zljin.poc.eventdrivenv2.config;

import com.zljin.poc.eventdrivenv2.EventSubscriber;
import com.zljin.poc.eventdrivenv2.bean.Event;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.SubmissionPublisher;

@Configuration
public class SubscriberConfig {

    @Resource
    private EventSubscriber eventSubscriber;

    @Bean(name = "eventPublisherV2")
    public SubmissionPublisher<Event> publisher() {
        SubmissionPublisher<Event> publisher = new SubmissionPublisher<>();
        publisher.subscribe(eventSubscriber);
        return publisher;
    }

}
