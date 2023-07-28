package com.maple.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author 杨锋
 * @date 2022/10/30 11:26
 * desc:
 */

@Component
@Aspect
public class UserApiFillHeaderAspect {

    UserApiFillHeaderAspect() {
        System.out.println("UserApiFillHeaderAspect.UserApiFillHeaderAspect");
    }

    @Around("@annotation(fillHeader)")
    public Object fillHeader(ProceedingJoinPoint joinPoint, FillHeader fillHeader) throws Throwable {
        System.out.println("注解环绕通知------");
        Object proceed = joinPoint.proceed();
        System.out.println("注解环绕通知------");
        return proceed;
    }


    @Before("p1() || p2()")
    public void testAspect(JoinPoint joinPoint) {
        System.out.println("切面执行: " +joinPoint);

    }

    @Pointcut("execution(public boolean com.maple.aop.UserInterface.test02(..))")
    public void p1() {

    }

    @Pointcut("execution(public void com.maple.aop.UserInterface.test01(..))")
    public void p2() {

    }


}
