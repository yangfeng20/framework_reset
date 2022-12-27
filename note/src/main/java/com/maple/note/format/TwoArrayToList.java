package com.maple.note.format;

import com.alibaba.fastjson.JSON;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author 杨锋
 * @date 2022/12/27 17:39
 * desc:
 */

public class TwoArrayToList {
    public static void main(String[] args) {


    }

    /**
     * 二维数组转换为列表
     */
    public static void twoArrayToList() {
        int[][] twoArr = new int[][]{{1, 2, 3}, {4, 5, 6}};

        // 二维数组转 list
        List<Integer> result = Stream.of(twoArr)
                .map(item -> Arrays.stream(item).boxed().collect(Collectors.toList()))
                .flatMap(Collection::stream).collect(Collectors.toList());


        System.out.println(result);
    }

    /**
     * 基础类型list转包装类型列表
     */
    public static void baseListToPackList() {
        int[] ints = {1, 2, 3};

        //
        List<Integer> result = JSON.parseArray(JSON.toJSONString(ints), Integer.class);

        System.out.println(result);
    }

    /**
     * 数组转list（包装类型）
     */
    public static void arrayToList() {
        Integer[] ints1 = {1, 2, 3};
        int[] ints2 = {1, 2, 3};

        // 包装类型才可以
        List<Integer> result1 = Arrays.asList(ints1);
        List<int[]> result2 = Arrays.asList(ints2);

        System.out.println(result1);
        System.out.println(result2);

    }
}
