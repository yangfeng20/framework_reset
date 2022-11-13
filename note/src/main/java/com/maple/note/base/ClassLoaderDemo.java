package com.maple.note.base;

/**
 * @author 杨锋
 * @date 2022/11/12 18:19
 * desc:
 */

public class ClassLoaderDemo {

    public static void main1(String[] args) throws Exception{

        // 应该是没有调用解析方法，所有不会发生类加载
        Class<?> clazz = Thread.currentThread().getContextClassLoader().loadClass("com.maple.note.base.ClassLoaderDemo$Student");
        System.out.println("加载结束，开始等待");
        Thread.sleep(2000);

        clazz.newInstance();
    }

    public static void main2(String[] args) throws Exception{
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Class<?> clazz = classLoader.loadClass("com.maple.note.base.ClassLoaderDemo$Student");
        // protected final void resolveClass(Class<?> c)


        System.out.println("加载结束，开始等待");
        Thread.sleep(2000);

        clazz.newInstance();
    }

    public static void main(String[] args)throws Exception {
        Class<?> clazz = Class.forName("com.maple.note.base.ClassLoaderDemo$Student");
        System.out.println("加载结束，开始等待");
        Thread.sleep(2000);

        clazz.newInstance();
    }


    static class Student{
        static {
            System.out.println("Student.static 初始值设定项");
        }
    }
}
