package com.maple.note;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicLong;

import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }


    @Test
    public void t6() throws InterruptedException {
        AtomicLong atomicInteger = new AtomicLong(0);
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 100000000; i++) {
                atomicInteger.addAndGet(1);
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 100000000; i++) {
                atomicInteger.addAndGet(1);
            }
        });
        thread1.start();
        thread2.start();
        long start = System.currentTimeMillis();
        Thread.sleep(1000);
        System.out.println(System.currentTimeMillis() -start);
        System.out.println(atomicInteger.get());
        System.out.println("==============");
        System.out.println(System.currentTimeMillis() - start);
    }
}
