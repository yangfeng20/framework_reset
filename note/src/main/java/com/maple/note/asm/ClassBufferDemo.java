package com.maple.note.asm;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yangfeng
 * @date : 2023/7/12 19:52
 * desc:
 */

public class ClassBufferDemo {

    public static void main(String[] args) {

    }


    public static class SourceClass {


        public static void info() {
            System.out.println("aa");
            Map<String, Object> map = new HashMap<>();
            System.out.println(map);
        }

        public static void info(String interfaceName
                , String role
                , String rt
                , String result
                , String interfaceUrl
                , Object req
                , Object resp
                , Throwable exp) {
            Map<String, Object> map = new HashMap<>();
            System.out.println(map);
        }
    }


}
