package com.maple.aop.my;

import com.maple.aop.my.service.StudentService;
import com.maple.aop.my.service.StudentServiceImpl;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.lang.reflect.Method;

/**
 * @author 杨锋
 * @date 2022/11/19 20:15
 * desc:
 */

@ComponentScan("com.maple.aop.my")
public class ProxyFactoryBeanDemo {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ProxyFactoryBeanDemo.class);
        StudentService proxyObject = applicationContext.getBean("proxyObject", StudentService.class);
        proxyObject.addStudent();

    }


    @Bean("targetObject")
    public StudentService studentService(){
        return new StudentServiceImpl();
    }


    @Bean("proxyObject")
    public ProxyFactoryBean proxy(StudentService studentService){
        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        proxyFactoryBean.setTarget(studentService);
        proxyFactoryBean.addAdvice(new MethodBeforeAdvice() {
            @Override
            public void before(Method method, Object[] args, Object target) throws Throwable {
                System.out.println("before");
            }
        });

        return proxyFactoryBean;
    }
}
