package com.maple;

import org.objectweb.asm.ClassReader;

/**
 * @author yangfeng
 * @date : 2023/7/25 15:18
 * desc:
 */

public class AsmClassLoader extends ClassLoader {

    /**
     * 加载类
     *
     * @param bytes     字节
     * @param className 全类名 点号格式 例如：【com.maple.TestMain】
     * @return {@link Class}<{@link ?}>
     */
    public Class<?> loadClass(byte[] bytes, String className) {
        return defineClass(className, bytes, 0, bytes.length);
    }

    public Class<?> loadClass(byte[] bytes) {
        ClassReader classReader = new ClassReader(bytes);
        return defineClass(classReader.getClassName().replace("/", "."), bytes, 0, bytes.length);
    }
}
