package com.maple.note.clazz;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author yangfeng
 * @date : 2023/3/1 12:47
 * desc:
 */

public class LoaderClass01 extends ClassLoader {

    public static void main(String[] args) throws Exception {
        LoaderClass01 loaderClass = new LoaderClass01();
        Class<?> aClass = Thread.currentThread().getContextClassLoader().loadClass("com.maple.note.test.TestMain");
        Class<?> clazz = loaderClass.findClass("D:\\Develop\\41009\\doc-center-dao\\target\\classes\\com\\yqn\\center\\doc\\po\\DocumentDO.class");
        System.out.println(clazz);
    }


    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] bytes = new byte[1024 * 1024];
        int read = -1;
        try {
            FileInputStream inputStream = new FileInputStream(name);
            read = inputStream.read(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return defineClass("com.yqn.center.doc.po.DocumentDO", bytes, 0, read);
    }


}
