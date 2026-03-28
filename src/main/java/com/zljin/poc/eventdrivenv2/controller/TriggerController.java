package com.zljin.poc.eventdrivenv2.controller;


import com.zljin.poc.eventdrivenv2.EventRiskChecker;
import com.zljin.poc.eventdrivenv2.bean.Event;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.SubmissionPublisher;

@RestController
@RequestMapping("/event/poc/v2")
public class TriggerController {

    @Resource(name = "eventPublisherV2")
    private SubmissionPublisher<Event> publisher;

    @Resource
    private EventRiskChecker eventRiskChecker;

    @GetMapping("/create")
    public ResponseEntity<String> createOrder() {
        for(int i = 0;i<10;i++){
            Event event = new Event();
            event.setEventType("devEventHandler");
            event.setName("devEvent"+i);
            event.setData("apple"+i);
            publisher.submit(event);
        }
        releaseProduct();
        return ResponseEntity.ok("ok");
    }

    @GetMapping("/start-rick-check")
    public ResponseEntity<String> startRiskCheck(){
        eventRiskChecker.start();
        return ResponseEntity.ok("ok");

    }

    @GetMapping("/stop-rick-check")
    public ResponseEntity<String> stopRiskCheck(){
        eventRiskChecker.stop();
        return ResponseEntity.ok("ok");
    }

    private void releaseProduct(){
        Event releaseEvent = new Event();
        releaseEvent.setEventType("releaseEventHandler");
        releaseEvent.setName("releaseEvent");
        releaseEvent.setData("sell apple list");
        publisher.submit(releaseEvent);
    }
}
