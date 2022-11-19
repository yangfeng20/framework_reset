package com.maple.aop.my;

import com.maple.aop.my.service.StudentService;
import com.maple.aop.my.service.StudentServiceImpl;
import org.aopalliance.aop.Advice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.PointcutAdvisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.StaticMethodMatcherPointcut;

import java.lang.reflect.Method;

/**
 * @author 杨锋
 * @date 2022/11/19 19:42
 * desc: 直接使用spring的代理工厂创建代理对象
 */

public class CustomAopDemo {
    public static void main(String[] args) {

        // 创建目标对象
        StudentService studentService = new StudentServiceImpl();

        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(studentService);

        // 只添加代理逻辑
        proxyFactory.addAdvice(new MethodBeforeAdvice() {
            @Override
            public void before(Method method, Object[] args, Object target) throws Throwable {
                System.out.println("before advice 无切入条件");
            }
        });

        // 添加代理逻辑的同时指定那些方法需要执行代理逻辑
        proxyFactory.addAdvisor(new PointcutAdvisor() {
            @Override
            public Pointcut getPointcut() {
                return new StaticMethodMatcherPointcut() {
                    @Override
                    public boolean matches(Method method, Class<?> targetClass) {
                        return "addStudent".equals(method.getName());
                    }
                };
            }

            @Override
            public Advice getAdvice() {
                return new MethodBeforeAdvice() {
                    @Override
                    public void before(Method method, Object[] args, Object target) throws Throwable {
                        System.out.println("before Advisor 有切入条件");
                    }
                };
            }

            @Override
            public boolean isPerInstance() {
                return false;
            }
        });

        StudentService proxy = (StudentService) proxyFactory.getProxy();
        proxy.addStudent();
        System.out.println("----------------------------------------");
        proxy.deleteStudent();
    }
}
