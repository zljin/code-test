package com.zljin.poc.policy.controller;

import com.zljin.poc.policy.service.PaymentService;
import com.zljin.poc.policy.service.PaymentRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/payment/poc")
public class PaymentController {

    /**
     * 当@Autowired 用在 Map 上时，Spring 会自动将匹配特定类型的所有 Bean 注入到这 Map 中，
     * 其中 Map 的 key 默认是 Bean 的名称 (驼峰类命名，或者你自己指定一个名字也行)，value 是 Bean 的实例
     */
    @Resource
    Map<String, PaymentService> paymentServiceMap;

    public boolean processPayment(String paymentType, PaymentRequest request) {
        PaymentService paymentService = paymentServiceMap.get(paymentType);
        if (paymentService == null) {
            System.out.println("payment error..");
            return false;
        }
        System.out.println(paymentType+ " payment success..");
        return paymentService.process(request);
    }

    @GetMapping("/policy")
    public ResponseEntity<String> policy(){
        processPayment("alipayService",new PaymentRequest());
        processPayment("wechatService",new PaymentRequest());
        processPayment("WechatService",new PaymentRequest());
        return ResponseEntity.ok("success.");
    }
}
