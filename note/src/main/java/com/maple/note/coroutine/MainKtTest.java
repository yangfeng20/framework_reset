package com.maple.note.coroutine;

/**
 * @author yangfeng
 * @date : 2023/8/16 21:23
 * desc:
 */

public class MainKtTest {
    public static void main(String[] args) throws Exception{
        CoroutineDemo instance = CoroutineDemo.INSTANCE;
        instance.test01();
        Class<?> clazz = Class.forName("com.maple.note.coroutine.CoroutineDemo");
        System.out.println(clazz);
    }
}
