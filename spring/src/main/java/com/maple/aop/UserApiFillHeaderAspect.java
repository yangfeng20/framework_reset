package com.maple.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author 杨锋
 * @date 2022/10/30 11:26
 * desc:
 */

@Component
@Aspect
public class UserApiFillHeaderAspect {

    @Around("@annotation(fillHeader)")
    public Object fillHeader(ProceedingJoinPoint joinPoint, FillHeader fillHeader) throws Throwable{
        System.out.println("注解环绕通知------");
        Object proceed = joinPoint.proceed();
        System.out.println("注解环绕通知------");
        return proceed;
    }
}
