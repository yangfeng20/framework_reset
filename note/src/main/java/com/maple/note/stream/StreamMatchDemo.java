package com.maple.note.stream;

import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author yangfeng
 * @date : 2023/4/23 14:48
 * desc:
 */

public class StreamMatchDemo {
    public static void main(String[] args) {
        List<Student> list = Stream.of(new Student(), new Student(), new Student()).collect(Collectors.toList());


        new MyStream<>(list.stream()).enable(()->{
            System.out.println("enable");
            return true;
        }).forEach(item->{
            item.setId(2);
        });

        System.out.println(list);
    }

    @Data
    //@AllArgsConstructor
    static class Student{
        private Integer id = 1;
    }
}
