package com.maple.note.base;

/**
 * @author 杨锋
 * @date 2022/11/11 7:50
 * desc:
 */

public class OperatorDemo {
    public static void main(String[] args) {
        int x = 0;
        int y = 0;
        int z = 0;

        // 短路与和短路或，都只关系自己两边的，其中一个满足条件就不会之前后面的判断，短路了 todo 都只是针对运算符两边的表达式而言
        // todo 【&& 是都为true才会返回true,所有当第一个条件为false时，就不会执行另一个表达式】
        // todo 【|| 是任意一个为true就返回true，所有当第一个条件为true是，就不会执行另外一个表达式】
        if (false && true || (x++ == 0)) {
            System.out.println("x = " + x);
            System.out.println("y = " + y);
            System.out.println("z = " + z);
        } else {
            System.out.println("else");
        }


        // | 并不会短路，后面的表达式还会执行
        int a = 0;
        if (true | (a++==0)){
            System.out.println("a = " + a);
        }

        // || 发生短路，后面的表达式不会执行
        int b = 0;
        if (true || (b++==0)){
            System.out.println("b = " + b);
        }
    }
}
