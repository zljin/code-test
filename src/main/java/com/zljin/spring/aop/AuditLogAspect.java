package com.zljin.spring.aop;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AuditLogAspect {

    @Around("execution(* com.zljin.spring.service.*.*(..))")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("执行方法前");
        Object result = joinPoint.proceed();
        System.out.println("执行方法后");
        return result;
    }
}
