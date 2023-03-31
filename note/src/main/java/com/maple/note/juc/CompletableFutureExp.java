package com.maple.note.juc;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author yangfeng
 * @date : 2023/3/31 9:15
 * desc:
 */

public class CompletableFutureExp {
    public static void main(String[] args) {

        List<String> list = Stream.of(1, 2, 3, 4).map(item -> CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "正在执行" + item);
            if (item % 2 == 0) {
                throw new RuntimeException("发送异常" + item);
            }
            return "" + item;
        }))
                .peek(future->{
                    System.out.println(future);
                })
                .map(future->{
                    return future.exceptionally(exp -> {
                        System.out.println("except:" + exp.getMessage());
                        return "空";
                    });
                })
                .peek(future->{
                    System.out.println(future);
                })
                .collect(Collectors.toList())
                .stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());


        System.out.println("结束-------------------");
        System.out.println(list);

    }
}
