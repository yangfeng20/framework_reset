package com.maple.note.base;

import cn.hutool.crypto.digest.MD5;

public class NumberSetGenerator {
    public static void main(String[] args) {
        System.out.println(MD5.create().digestHex("204252"));
    }
}
