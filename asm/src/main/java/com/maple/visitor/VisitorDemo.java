package com.maple.visitor;

import com.maple.AsmClassLoader;
import com.maple.base.AsmPrint;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

/**
 * @author yangfeng
 * @date : 2023/7/25 14:14
 * desc:
 */

public class VisitorDemo {
    public static void main(String[] args) throws Exception {
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES);

        classWriter.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC + Opcodes.ACC_ABSTRACT + Opcodes.ACC_INTERFACE,
                "com/maple/Test", "", "java/lang/Object", new String[]{"java/lang/Cloneable"});


        classWriter.visitField(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC + Opcodes.ACC_FINAL, "filed1", "I", null, 1)
                .visitEnd();

        classWriter.visitField(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC + Opcodes.ACC_FINAL, "field2", "I", null, 2)
                .visitEnd();

        classWriter.visitField(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC + Opcodes.ACC_FINAL, "field3", "I", null, -1)
                .visitEnd();

        classWriter.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_ABSTRACT, "compareTo", "(Ljava/lang/Object;)I", null, null)
                .visitEnd();

        classWriter.visitEnd();


        Class<?> clazz = new AsmClassLoader().loadClass(classWriter.toByteArray());
        System.out.println(clazz);
        System.out.println("---------------------------");
        AsmPrint.printAsmCode(clazz);
    }
}
