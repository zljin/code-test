package com.zljin.spring.controller;

import com.zljin.spring.service.AsyncService;
import com.zljin.spring.service.SendEventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/event")
public class EventController {

    private final SendEventService sendEventService;

    @Resource
    AsyncService asyncService;

    public EventController(SendEventService sendEventService) {
        this.sendEventService = sendEventService;
    }

    @GetMapping("/test-async")
    public String testAsync() {
        System.out.println("控制器开始 - 线程: " + Thread.currentThread().getName());

        // 调用异步方法 - 会立即返回，不阻塞
        asyncService.sendEmail("user@example.com", "Hello World");

        System.out.println("控制器结束 - 异步方法已在后台执行");
        return "异步任务已启动";
    }

    @GetMapping("/test-async-result")
    public String testAsyncWithResult() throws Exception {
        // 调用带返回值的异步方法
        CompletableFuture<String> task1 = asyncService.processData("test-data1");
        CompletableFuture<String> task2 = asyncService.processData("test-data2");

        CompletableFuture.allOf(task1, task2).join(); // 等待所有任务完成

        String result = task1.get() + task2.get();
        return "处理结果: " + result;
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
