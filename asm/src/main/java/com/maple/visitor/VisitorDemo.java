package com.maple.visitor;

import com.maple.AsmClassLoader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

/**
 * @author yangfeng
 * @date : 2023/7/25 14:14
 * desc:
 */

public class VisitorDemo {
    public static void main(String[] args) throws Exception{
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES);

        classWriter.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC + Opcodes.ACC_ABSTRACT + Opcodes.ACC_SUPER, "com/maple/TestMain",
                null, "java/lang/Object", null);
        classWriter.visitEnd();

        classWriter.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "(Ljava/lang/Object;)I", null, null);
        classWriter.visitEnd();

        byte[] bytes = classWriter.toByteArray();


        Class<?> clazz = new AsmClassLoader().loadClass(bytes);
        System.out.println(clazz);


    }
}
