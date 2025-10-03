package com.zljin.spring.beans;

import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;

/**
 *
 1.6 @PostConstruct、init-method 和afterPropertiesSet 的执行顺序是什么？各有什么特点？
 */
public class MyBean implements InitializingBean {

    /**
     * Java本身的标准注解，和Spring无依赖关系
     */
    @PostConstruct
    public void postConstruct(){
        System.out.println("1. postConstruct");
    }

    /**
     * 需要实现InitializingBean 接口的，与Spring有强耦合
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("2. afterPropertiesSet");
    }

    /**
     * 自定义配置的 init-method 方法，不侵入
     * 使用第三方类灵活指定初始化方法
     */
    public void initMethod() {
        System.out.println("3. initMethod");
    }
}
