package com.maple.note.genericity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author 杨锋
 * @date 2022/10/31 23:03
 * desc:
 */

public class GenericityDemo {

    public static void main(String[] args) {
        method1(new Student());
        method1(new Object());

        method2(new ArrayList<>());
        //method2_3(new ArrayList<Pupil>());
        method3(new ArrayList<Student>());
        method4(new ArrayList<Object>());
        String s = method5("");
        String s1 = method6(String.class);
        copy(new ArrayList<Person>(), new ArrayList<Student>());
        Collections.copy(new ArrayList<Student>(), new ArrayList<>());

    }

    public static <T> void method1(T t) {

    }

    public static void method2(List<?> list) {

    }

    public static void method2_3(List<Student> list){

    }

    /**
     * <? extends Student> extends标识上仙通配符，可以是当前类的子类（但是不能是Student的父类）
     * 也就代表他一定是Student或者Student的子类，那么就一定可以调用Student的方法
     *
     * @param list list
     */
    public static void method3(List<? extends Student> list) {
        Student student = list.get(0);

    }

    /**
     * 下限通配符 <? super Student> super标识最低只能是Student，可以是他的父类，但是不是是他的子类
     * 这里也就是说可能是Student或者Student的父类，最终可以追到Object，所有不能使用Student的方法
     *
     * @param list list
     */
    public static void method4(List<? super Student> list) {


    }

    public static <T> void copy(List<? super Student> dest, List<? extends Student> src){
        Student student = src.get(0);

    }


    /**
     * method5：需要传实际的类型对象，
     *
     * @param t t
     * @return {@link T}
     */
    public static <T> T method5(T t) {
        return (T) new Object();
    }

    public static <T> T method6(Class<T> t) {
        return (T) new Object();
    }


    /**
     * 人
     */
    static class Person {

    }


    /**
     * 学生
     */
    static class Student extends Person {

    }

    /**
     * 小学生
     */
    static class Pupil extends Student {

    }
}
