package com.zljin.spring.config;

import com.zljin.spring.beans.MyBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean(initMethod = "initMethod")
    public MyBean myBean(){
        return new MyBean();
    }

}
