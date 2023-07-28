package com.maple;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;

import java.util.concurrent.atomic.AtomicReference;

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

        AtomicReference<String> atomicReference = new AtomicReference<>();
        String fullClassName;
        classReader.accept(new ClassVisitor(Opcodes.ASM9) {
            @Override
            public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
                atomicReference.set(name);
                super.visit(version, access, name, signature, superName, interfaces);
            }
        }, 0);

        return defineClass(atomicReference.get().replace("/", "."), bytes, 0, bytes.length);
    }
}
