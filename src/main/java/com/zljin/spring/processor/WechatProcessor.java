package com.zljin.spring.processor;

import com.zljin.spring.beans.PaymentRequest;
import org.springframework.stereotype.Component;

@Component
public class WechatProcessor implements PaymentProcessor{
    @Override
    public boolean process(PaymentRequest request) {
        return true;
    }
}
