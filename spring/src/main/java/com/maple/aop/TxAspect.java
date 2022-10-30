package com.maple.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author 杨锋
 * @date 2022/10/30 11:16
 * desc: order的值越小，代表优先级越高
 */

@Component
@Aspect
@Order(20)
public class TxAspect {

    @Around("execution(User *.createUser(UserParam))")
    public Object pointcut(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        System.out.println("-----环绕开始");
        Object proceed = proceedingJoinPoint.proceed();
        System.out.println("-----环绕结束");
        return proceed;
    }
}
