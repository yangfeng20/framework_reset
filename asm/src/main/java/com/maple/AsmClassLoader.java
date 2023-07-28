package com.maple;

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
        String strData = new String(bytes);
        String[] split = strData.split("\u0000\u0005\u0001\u0000\u0012");
        String fullClassName = split[1].split("\u0007")[0].replace("/", ".");
        return defineClass(fullClassName, bytes, 0, bytes.length);
    }
}
