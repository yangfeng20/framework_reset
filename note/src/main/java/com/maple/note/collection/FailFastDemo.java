package com.maple.note.collection;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author yangfeng
 * @date : 2023/5/16 9:06
 * desc:
 */

public class FailFastDemo {
    public static void main(String[] args) {
        List<Integer> list = Stream.of(0, 1, 2, 3, 4, 5).collect(Collectors.toList());

        for (int i = 0; i < list.size(); i++) {
            Integer ele = list.get(i);
            System.out.println(ele);

            list.remove(i);
            System.out.println();
        }
    }
}
