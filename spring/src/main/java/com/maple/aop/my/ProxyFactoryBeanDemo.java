package com.maple.aop.my;

import com.maple.aop.my.service.StudentService;
import com.maple.aop.my.service.StudentServiceImpl;
import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.lang.reflect.Method;

/**
 * @author 杨锋
 * @date 2022/11/19 20:15
 * desc:
 */

@ComponentScan("com.maple.aop.my")
@EnableAspectJAutoProxy
public class ProxyFactoryBeanDemo {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ProxyFactoryBeanDemo.class);
        StudentService proxyObject = applicationContext.getBean("proxyObject", StudentService.class);
        proxyObject.addStudent();
        System.out.println("--------------------");
        proxyObject.deleteStudent();

    }


    /**
     * 第一种方式
     */
    @Bean("targetObject")
    public StudentService studentService() {
        return new StudentServiceImpl();
    }

    @Bean("proxyObject")
    public ProxyFactoryBean proxy(StudentService studentService) {
        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        proxyFactoryBean.setTarget(studentService);
        proxyFactoryBean.addAdvice(getAdvice("before"));

        return proxyFactoryBean;
    }


    /**
     * 第二种方式
     * todo 在实例化前的
     */
    @Bean
    public BeanNameAutoProxyCreator beanNameAutoProxyCreator() {
        BeanNameAutoProxyCreator beanNameAutoProxyCreator = new BeanNameAutoProxyCreator();
        // beanName以target开头的都产生代理对象
        beanNameAutoProxyCreator.setBeanNames("target*");
        beanNameAutoProxyCreator.setInterceptorNames("afterReturning");
        return beanNameAutoProxyCreator;
    }

    @Bean
    public Advice afterReturning() {
        return getAdvice("afterReturning");
    }


    /**
     * 第3种方式
     * todo 在初始化后的
     * 需要下面的DefaultAdvisorAutoProxyCreator支持
     * @see #defaultAdvisorAutoProxyCreator()
     * <p>
     * Advisor = advice（通知）+joinPoint （切点）
     */
    @Bean
    public DefaultPointcutAdvisor defaultPointcutAdvisor() {
        NameMatchMethodPointcut nameMatchMethodPointcut = new NameMatchMethodPointcut();
        // 限制方法名
        nameMatchMethodPointcut.setMappedName("addStudent");
        return new DefaultPointcutAdvisor(nameMatchMethodPointcut, getAdvice("around"));
    }
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        return new DefaultAdvisorAutoProxyCreator();
    }



    public Advice getAdvice(String type) {

        switch (type) {
            case "before":
                return new MethodBeforeAdvice() {
                    @Override
                    public void before(Method method, Object[] args, Object target) throws Throwable {
                        System.out.println("before");
                    }
                };
            case "afterReturning":
                return new AfterReturningAdvice() {
                    @Override
                    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
                        System.out.println("afterReturning");
                    }
                };

            case "around":
                return new MethodInterceptor() {
                    @Override
                    public Object invoke(MethodInvocation invocation) throws Throwable {
                        System.out.println("around");
                        return invocation.proceed();
                    }
                };

            case "afterThrowing":
                return new ThrowsAdvice() {
                    public void afterThrowing(Method method, Object[] args, Object target, Exception ex) {
                        System.out.println("afterThrowing");
                    }
                };
            default:
                throw new RuntimeException("无效的通知类型");
        }

    }
}
