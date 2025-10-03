package com.zljin.spring.processor;

import com.zljin.spring.beans.PaymentRequest;

public interface PaymentProcessor {

    boolean process(PaymentRequest request);
}
