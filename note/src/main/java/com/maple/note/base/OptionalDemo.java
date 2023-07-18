package com.maple.note.base;

import com.google.common.collect.Lists;

import java.util.ArrayList;

/**
 * @author yangfeng
 * @date : 2023/5/18 15:53
 * desc:
 */

public class OptionalDemo {
    public static void main(String[] args) {
        ArrayList<Integer> list = Lists.newArrayList(1, 2, 3);
        ArrayList<Integer> list1 = Lists.newArrayList(4, 5, 6);

        list.addAll(list1);

        System.out.println(list);
    }


}
