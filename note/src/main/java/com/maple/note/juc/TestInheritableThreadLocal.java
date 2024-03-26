package com.maple.note.juc;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestInheritableThreadLocal {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ThreadLocal<String> threadLocal = new InheritableThreadLocal<String>() {
            @Override
            protected String initialValue() {
                return "A";
            }
        };
        threadLocal.set("B");
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        //1、子线程第一次获取ThreadLocal
        executorService.submit(() -> System.out.println("子线程ThreadLocal："+threadLocal.get())).get();
        Thread.sleep(1000);
        //2、父线程修改ThreadLocal
        threadLocal.set("C");
        System.out.println("父线程修改ThreadLocal为"+threadLocal.get());
        //3、子线程第二次获取ThreadLocal
        executorService.submit(() -> System.out.println("子线程ThreadLocal："+threadLocal.get())).get();
    }

}