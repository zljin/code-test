package com.zljin.spring.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Condition注解用法
 */
@Component
@ConditionalOnExpression("'${supportedEntites}'.contains(\"cn\")")
public class ConditionBean {

    @PostConstruct
    public void postConstruct(){
        //当环境变量supportedEntites=cn 的时候就会初始化成功
        System.out.println("ConditionBean --> 初始化成功");
    }
}
