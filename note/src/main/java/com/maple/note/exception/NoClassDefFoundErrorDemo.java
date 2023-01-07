package com.maple.note.exception;

/**
 * @author 杨锋
 * @date 2023/1/7 10:26
 * desc:
 */

public class NoClassDefFoundErrorDemo {

    public static void main(String[] args) {

        // 加载Resource类失败，会抛出Error，会导致当前线程终止，如果当前线程是main线程，虚拟机终止
        try {
            Resource resource = new Resource();
        } catch (Throwable e) {
            // 这是抛出的异常是【ExceptionInInitializerError】静态初始化失败（cinit方法执行失败）
            System.out.println(e);
            System.out.println("加载Resource失败");
        }

        // 上面加载类失败，并使用这个类创建对象,就会抛出 【NoClassDefFoundError】
        Resource resource = new Resource();
        resource.method01();
    }


    static class Resource {


        static {
            String str = null;

            str.toString();
        }


        public void method01() {
            System.out.println("Resource.method01");
        }
    }
}
