package com.zljin.poc.policy.service.impl;

import com.zljin.poc.policy.service.PaymentService;
import com.zljin.poc.policy.service.PaymentRequest;
import org.springframework.stereotype.Component;

@Component
public class WechatService implements PaymentService {
    @Override
    public boolean process(PaymentRequest request) {
        System.out.println("采用微信支付");
        return true;
    }
}
