package com.zljin.spring.service;

import com.zljin.spring.beans.PaymentRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class PaymentServiceTest {

    @Resource
    PaymentService paymentService;

    @Test
    void test(){
        Assertions.assertTrue(paymentService.processPayment("alipayProcessor",new PaymentRequest()));
        Assertions.assertTrue(paymentService.processPayment("wechatProcessor",new PaymentRequest()));
        Assertions.assertFalse(paymentService.processPayment("WechatProcessor",new PaymentRequest()));
    }


}