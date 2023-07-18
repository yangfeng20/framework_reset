package com.maple.note.test;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * @author yangfeng
 * @date : 2023/6/9 16:21
 * desc:
 */

public class InnerTest {

    public static void main(String[] args) throws Exception {

        ArrayList<Integer> list = new ArrayList<>();
        list.add(null);

        System.out.println(list.stream().collect(Collectors.toList()));
        //
        //try {
        //    test01();
        //} catch (Exception e) {
        //    throw new RuntimeException(e);
        //}
    }


    public static void test01() throws Exception {
        test02();
    }

    public static void test02() throws Exception {
        try {
            test03();
        } catch (Exception e) {
            throw new AppException02("test02", e);
        }
    }

    public static void test03() throws Exception {
        try {
            test04();
        } catch (Exception e) {
            throw new AppException03("test03", e);
        }

    }

    public static void test04() throws Exception {
        throw new AppException04("test04");
    }

    static class AppException01 extends RuntimeException {
        public AppException01(String message) {
            super(message);
        }

        public AppException01(String message, Throwable e) {
            super(message, e);
        }
    }

    static class AppException02 extends RuntimeException {
        public AppException02(String message) {
            super(message);
        }

        public AppException02(String message, Throwable e) {
            super(message, e);
        }
    }

    static class AppException03 extends RuntimeException {
        public AppException03(String message) {
            super(message);
        }

        public AppException03(String message, Throwable e) {
            super(message, e);
        }
    }

    static class AppException04 extends RuntimeException {

        public AppException04(String message) {
            super(message);
        }

        public AppException04(String message, Throwable e) {
            super(message, e);
        }

    }


}
