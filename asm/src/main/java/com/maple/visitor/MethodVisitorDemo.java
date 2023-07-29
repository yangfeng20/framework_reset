package com.maple.visitor;

import com.maple.AsmClassLoader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * @author yangfeng
 * @date : 2023/7/29 9:29
 * desc:
 */

public class MethodVisitorDemo {
    public static void main(String[] args) throws Exception{

        ClassWriter classWriter = VisitorUtils.newClassWriter("com/maple/TestClass");

        MethodVisitor methodVisitor = classWriter.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null);
        methodVisitor.visitCode();
        methodVisitor.visitVarInsn(Opcodes.ALOAD, 0);
        methodVisitor.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
        methodVisitor.visitInsn(Opcodes.RETURN);
        methodVisitor.visitMaxs(1, 1);
        methodVisitor.visitEnd();


        MethodVisitor mv1 = classWriter.visitMethod(Opcodes.ACC_STATIC, "<clinit>", "()V", null, null);
        mv1.visitCode();
        mv1.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        mv1.visitLdcInsn("输出------------");
        mv1.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
        mv1.visitInsn(Opcodes.RETURN);
        mv1.visitMaxs(2, 0);
        mv1.visitEnd();


        classWriter.visitEnd();

        byte[] bytes = classWriter.toByteArray();
        Class<?> clazz = new AsmClassLoader().loadClass(bytes);
        System.out.println(clazz);
        Object instance = clazz.newInstance();
        System.out.println(instance);
    }
}
