package com.zljin.spring.service;

import com.zljin.spring.beans.PaymentRequest;
import com.zljin.spring.processor.PaymentProcessor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 当@Autowired 用在 Map 上时，Spring 会自动将匹配特定类型的所有 Bean 注入到这 Map 中，
 * 其中 Map 的 key 默认是 Bean 的名称 (驼峰类命名，或者你自己指定一个名字也行)，value 是 Bean 的实例
 */
@Service
public class PaymentService {

    @Resource
    Map<String, PaymentProcessor> processorMap;

    public boolean processPayment(String paymentType, PaymentRequest request) {
        PaymentProcessor paymentProcessor = processorMap.get(paymentType);
        if (paymentProcessor == null) {
            System.out.println("payment error..");
            return false;
        }
        System.out.println(paymentType+ " payment success..");
        return paymentProcessor.process(request);
    }
}
