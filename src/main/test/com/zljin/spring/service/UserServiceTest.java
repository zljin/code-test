package com.zljin.spring.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;



@SpringBootTest
class UserServiceTest {

    @Resource
    UserService userService;

    @Resource
    UserService userService2;

    @Test
    void test(){
        System.out.println(userService);
        System.out.println(userService2);
    }

}