package com.maple.note.test;

import java.util.HashMap;

/**
 * @author 杨锋
 * @date 2022/12/8 17:32
 * desc:
 */

public class MapRemoveTest {
    public static void main(String[] args) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("key1", 1);
        map.put("key2", 2);
        map.put("key3", 3);

        System.out.println("map.remove(\"key1\") = " + map.remove("key1"));

        System.out.println("map = " + map);
    }
}
