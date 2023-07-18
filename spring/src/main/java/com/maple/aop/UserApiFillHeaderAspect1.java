package com.maple.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class UserApiFillHeaderAspect1 {

    UserApiFillHeaderAspect1() {
        System.out.println("UserApiFillHeaderAspect1.UserApiFillHeaderAspect1");
    }

    @Around("@annotation(fillHeader)")
    public Object fillHeader(ProceedingJoinPoint joinPoint, FillHeader fillHeader) throws Throwable {
        System.out.println("注解环绕通知1------");
        Object proceed = joinPoint.proceed();
        System.out.println("注解环绕通知1------");
        return proceed;
    }
}