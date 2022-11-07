package com.maple.note.format;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 杨锋
 * @date 2022/10/31 19:18
 * desc:
 */

public class DataConvertDemo {


    final static ThreadLocal<SimpleDateFormat> simpleDateFormatThreadLocal = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX"));

    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
        test();
    }


    public static void test(){


        SimpleDateFormat simpleDateFormat = simpleDateFormatThreadLocal.get();
        System.out.println(simpleDateFormat.format(new Date(1611304352168L)));
    }


}
