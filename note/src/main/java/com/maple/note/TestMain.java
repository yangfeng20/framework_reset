package com.maple.note;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 杨锋
 * @date 2022/10/26 19:20
 * desc:
 */


@SuppressWarnings("all")
public class TestMain {
    public static void main(String[] args) throws Exception {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        Thread thread1 = new Thread(() -> {
            long start = System.currentTimeMillis();
            for (int i = 0; i < 400000000; i++) {
                atomicInteger.addAndGet(1);
            }
            System.out.println(Thread.currentThread().getName() + "   " + (System.currentTimeMillis() - start));
        });
        Thread thread2 = new Thread(() -> {
            long start = System.currentTimeMillis();
            for (int i = 0; i < 400000000; i++) {
                atomicInteger.addAndGet(1);
            }
            System.out.println(Thread.currentThread().getName() + "   " + (System.currentTimeMillis() - start));
        });
        thread1.start();
        thread2.start();
        long start = System.currentTimeMillis();
        Thread.sleep(1000);
        System.out.println("total: "+(System.currentTimeMillis() - start));
        System.out.println(atomicInteger.get());
    }
}
