package com.maple.note.test;

import java.util.HashMap;

/**
 * @author yangfeng
 * @date : 2023/12/6 10:11
 * desc:
 */

public class ListAddAllTest {
    public static void main(String[] args) {
        HashMap<String, String> map1 = new HashMap<>();
        HashMap<String, String> map2 = new HashMap<>();

        map1.put("1", "map1-v");
        map2.put("1", "map2-v");
        map1.putAll(map2);

        System.out.println(map1);
        System.out.println(map2);
    }
}
