package com.maple.note.args;

import cn.hutool.core.lang.Console;

/**
 * @author yangfeng
 * @date : 2023/4/23 10:46
 * desc:
 */

public class Demo {
    public static void main(String[] args) {

        // 系统环境变量
        String username = System.getenv("username");
        System.out.println(username);

        // vm参数 【虚拟机环境参数】：-Daaa=bbb
        String vmParam = System.getProperty("aaa");
        System.out.println(vmParam);


        // 程序参数 --cc=dd
        Console.print(args);
        System.out.println();

    }
}
