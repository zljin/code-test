package com.zljin.spring.service;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 1. Spring 作用域
 *
 * 默认为 singleton
 */

//@Scope("prototype")
//@Scope("singleton")
@Component
public class UserService {

    AccountService accountService;

    public void say(){
        System.out.println("hi");
    }

    @Resource
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }
}
