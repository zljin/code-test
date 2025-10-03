package com.zljin.spring.controller;

import com.zljin.spring.service.SendEventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/event")
public class EventController {

    private final SendEventService sendEventService;

    public EventController(SendEventService sendEventService) {
        this.sendEventService = sendEventService;
    }

    @GetMapping("/send/{info}")
    public ResponseEntity<String> send(@PathVariable String info) {
        sendEventService.sendEvent(info);
        return ResponseEntity.status(200).body("ok");
    }

    @PostMapping("/propose")
    public ResponseEntity<String> proposePayment() {
        return ResponseEntity.status(200).body("ok");
    }
}
