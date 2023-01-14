package com.maple.note.stream;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author yangfeng
 * @date : 2023/1/14 11:03
 * desc: note 可以通过peek调试查看流中的元素，也可以通过map改变源集合（但是不建议）。
 */

public class StreamPeek {
    public static void main(String[] args) {

        List<Resource> param = Stream.of(new Resource(1), new Resource(1), new Resource(1), new Resource(1), new Resource(1), new Resource(1)).collect(Collectors.toList());
        param.stream()
                .peek(System.out::println)
                .map(resource -> {
                    resource.setAge(20);
                    return resource;
                }).peek(System.out::println)
                .mapToInt(Resource::getAge)
                .map(item->item+10)
                .forEach(System.out::println);


        System.out.println(param);


    }


    @AllArgsConstructor
    @Data
    static class Resource {
        private Integer age;


    }
}
