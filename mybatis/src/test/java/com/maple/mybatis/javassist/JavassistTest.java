package com.maple.mybatis.javassist;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 * @author 杨锋
 * @date 2022/10/14 14:27
 * desc:
 */

public class JavassistTest {

    @Test
    public void testGenClass() throws Exception{
        ClassPool classPool = ClassPool.getDefault();
        // todo jdk8之后需要配置vm参数

        // 构建类
        CtClass ctClass = classPool.makeClass("com.maple.GenClassTest");

        // 构建方法
        String methodCode = "public void method(){System.out.println(123);}";
        CtMethod method = CtMethod.make(methodCode, ctClass);

        // 类中添加方法
        ctClass.addMethod(method);

        // 内存中生成class
        Class<?> genClass = ctClass.toClass();

        // 调用
        Object instance = genClass.newInstance();
        Method genClassMethod = genClass.getMethod("method");
        genClassMethod.invoke(instance);
    }


    /**
     * 测试impl接口
     */
    @Test
    public void testImplInterface() throws Exception{
        ClassPool classPool = ClassPool.getDefault();
        CtClass ctClass = classPool.makeClass("com.maple.mybatis.javassist.InterfaceTestImpl");

        // 实现接口
        CtClass makeInterface = classPool.makeInterface("com.maple.mybatis.javassist.InterfaceTest");
        ctClass.addInterface(makeInterface);

        // 如果是需要动态的实现方法，只需要通过反射去获取接口的信息就可以了
        String methodCode = "public void interMethod(){System.out.println(123456789);}";
        CtMethod method = CtMethod.make(methodCode, ctClass);
        ctClass.addMethod(method);

        Class<?> interfaceImplClass = ctClass.toClass();
        Object instance = interfaceImplClass.newInstance();

        InterfaceTest interfaceTestImpl = (InterfaceTest) instance;
        interfaceTestImpl.interMethod();


        Method interMethod = interfaceImplClass.getMethod("interMethod");
        interMethod.invoke(instance);
    }
}
