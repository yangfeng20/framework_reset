package com.maple.visitor;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

/**
 * @author yangfeng
 * @date : 2023/7/29 9:49
 * desc:
 */

public class VisitorUtils implements Opcodes {


    public static ClassWriter newClassWriter(String name) {
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        classWriter.visit(V1_8, ACC_PUBLIC + ACC_SUPER, name, null, "java/lang/Object", null);
        return classWriter;
    }



}
