package com.zljin.poc.eventdriven.trigger;


import com.zljin.poc.eventdriven.events.DevEvent;
import com.zljin.poc.eventdriven.events.ReleaseEvent;
import com.zljin.poc.eventdriven.events.ReviewEvent;
import com.zljin.poc.eventdriven.events.TestEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/event/poc")
public class EventController {

    @Autowired
    ApplicationEventPublisher eventPublisher;

    @GetMapping("/create")
    public ResponseEntity<String> createOrder() {
        DevEvent event1 = new DevEvent("1", "phil");
        ReviewEvent event2 = new ReviewEvent("2", "thomas");
        TestEvent event3 = new TestEvent("3", "christy");
        ReleaseEvent event4 = new ReleaseEvent("4", "evan");
        eventPublisher.publishEvent(event1);
        simulateCost();

        eventPublisher.publishEvent(event2);

        simulateCost();
        eventPublisher.publishEvent(event3);

        simulateCost();
        eventPublisher.publishEvent(event4);

        simulateCost();
        return ResponseEntity.ok("ok");
    }

    private void simulateCost(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {

        }
    }
}
