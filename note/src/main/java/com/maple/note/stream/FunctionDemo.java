package com.maple.note.stream;

import java.util.function.Function;

/**
 * @author 杨锋
 * @date 2022/10/22 11:46
 * desc:
 */

public class FunctionDemo {
    public static void main(String[] args) {
        Function<Integer, String> function = (param) -> {
            // function的接口规定了参数类型，使用泛型
            return param + "   test";
        };


        System.out.println(function.apply(1));
    }
}
