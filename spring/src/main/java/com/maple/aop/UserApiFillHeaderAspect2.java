package com.maple.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class UserApiFillHeaderAspect2 implements Ordered {

    UserApiFillHeaderAspect2() {
        System.out.println("UserApiFillHeaderAspect1.UserApiFillHeaderAspect2");
    }

    @Around("@annotation(fillHeader)")
    public Object fillHeader(ProceedingJoinPoint joinPoint, FillHeader fillHeader) throws Throwable {
        System.out.println("注解环绕通知2------");
        Object proceed = joinPoint.proceed();
        System.out.println("注解环绕通知2------");
        return proceed;
    }

    @Override
    public int getOrder() {
        return 1;
    }
}