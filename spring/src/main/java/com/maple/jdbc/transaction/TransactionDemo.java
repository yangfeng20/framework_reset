package com.maple.jdbc.transaction;

import com.maple.jdbc.SpringConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author yangfeng
 * @date : 2023/6/16 15:19
 * desc:
 */

@SuppressWarnings("all")
public class TransactionDemo {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);

        Service01 service = applicationContext.getBean(Service01.class);

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 10, 0L,
                TimeUnit.SECONDS, new ArrayBlockingQueue<>(10), Thread::new, new ThreadPoolExecutor.AbortPolicy());


        for (int i = 0; i < 10; i++) {
            int finalI = i;
            threadPoolExecutor.execute(() -> {
                try {
                    service.buy(1L);
                } catch (Exception e) {
                    System.out.println(finalI + e.getMessage());
                }
            });
        }

        threadPoolExecutor.shutdown();

    }
}
