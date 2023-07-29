package com.maple.visitor;

import com.maple.AsmClassLoader;
import com.maple.base.AsmPrint;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * @author yangfeng
 * @date : 2023/7/29 10:24
 * desc:
 */

public class VisitorDemo01 {
    public static void main(String[] args) throws Exception{
        ClassWriter classWriter = VisitorUtils.newClassWriter("com/maple/Test");

        MethodVisitor mv1 = classWriter.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null);
        mv1.visitCode();
        mv1.visitVarInsn(Opcodes.ALOAD, 0);
        mv1.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
        mv1.visitInsn(Opcodes.RETURN);
        mv1.visitMaxs(4,5);
        mv1.visitEnd();

        classWriter.visitEnd();
        byte[] bytes = classWriter.toByteArray();
        AsmPrint.printByteCode(bytes);
        Class<?> clazz = new AsmClassLoader().loadClass(bytes);
        System.out.println(clazz.newInstance());
    }
}
