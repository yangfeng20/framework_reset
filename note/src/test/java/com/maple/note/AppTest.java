package com.maple.note;

import org.junit.Test;

import java.util.function.Supplier;

/**
 * Unit test for simple App.
 */
public class AppTest {

    @Test
    public void test01() {
        Supplier<Integer> generic = ()->{
            System.out.println("AppTest.test01.generic");
            return 1;
        };

        System.out.println(generic.get());
        System.out.println(generic.get());
        System.out.println(generic.get());
        System.out.println(generic.get());
        System.out.println(generic.get());
    }



}
