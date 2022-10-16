package com.maple.mybatis.table.mapping.interceptor;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;

import java.sql.Connection;
import java.util.Properties;

/**
 * @author 杨锋
 * @date 2022/10/16 11:12
 * desc:
 */


/**
 * 拦截指定指定类的指定方法，以及这个方法的参数类型，因为防止重载；类似aop
 */
@Intercepts(value = {@Signature(
        type = StatementHandler.class,
        method = "prepare",
        args = {
                Connection.class,
                Integer.class
        }
)})
public class MyInterceptor implements Interceptor {


    public MyInterceptor() {
        System.out.println("-----------------------------------这是我的自定义拦截器");
        System.out.println("初始化——插件加载");
        System.out.println("-----------------------------------这是我的自定义拦截器");
    }


    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("-----------------------------------这是我的自定义拦截器");
        System.out.println("invocation = " + invocation);
        Object returnObject = invocation.proceed();
        System.out.println("-----------------------------------这是我的自定义拦截器");
        return returnObject;
    }

    @Override
    public Object plugin(Object target) {
        System.out.println("-----------------------------------这是我的自定义拦截器");
        System.out.println("target = " + target);
        System.out.println("-----------------------------------这是我的自定义拦截器");
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        System.out.println("-----------------------------------这是我的自定义拦截器");
        System.out.println("properties = " + properties);
        System.out.println("-----------------------------------这是我的自定义拦截器");

    }
}
