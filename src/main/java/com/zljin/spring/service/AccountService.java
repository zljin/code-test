package com.zljin.spring.service;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Random;

@Service
public class AccountService {
    UserService userService;

    public void printAmount(){
        System.out.println(new Random().nextInt());
    }

    @Resource
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
