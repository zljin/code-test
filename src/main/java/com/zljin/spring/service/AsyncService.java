package com.zljin.spring.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class AsyncService {

    /**
     * @param to
     * @param content
     */
    @Async
    public void sendEmail(String to, String content) {
        System.out.println("开始发送邮件给: " + to);
        try {
            Thread.sleep(7000); // 模拟耗时操作
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("邮件发送完成: " + to);
    }

    @Async
    public CompletableFuture<String> processData(String data) {
        System.out.println("开始解析数据: " + data);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String result = data + " - 处理完成";
        return CompletableFuture.completedFuture(result);
    }

    @Async
    public CompletableFuture<String> processData1(String data) {
        System.out.println("开始清理数据: " + data);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String result = data + " - 处理完成";
        return CompletableFuture.completedFuture(result);
    }

}
