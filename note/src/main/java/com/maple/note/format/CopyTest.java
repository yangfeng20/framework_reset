package com.maple.note.format;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author 杨锋
 * @date 2022/10/18 15:59
 * desc:
 */

public class CopyTest {
    public static void main(String[] args) {

        List<Integer> list = ofIter(10).map(index -> index).collect(Collectors.toList());
        System.out.println(list);

    }


    public static Stream<Integer> ofIter(int limit) {
        return Stream.iterate(0, i -> i + 1).limit(limit);
    }
}
