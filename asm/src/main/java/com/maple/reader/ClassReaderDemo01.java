package com.maple.reader;

import com.maple.AsmClassLoader;
import com.maple.visitor.entity.TestClass;
import org.objectweb.asm.*;

import java.lang.reflect.Field;

/**
 * @author yangfeng
 * @date : 2023/7/29 11:05
 * desc:
 */

public class ClassReaderDemo01 {
    public static void main(String[] args) throws Exception {
        ClassReader classReader = new ClassReader(TestClass.class.getName());
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        ClassVisitor classVisitor = new MyClassVisitor(Opcodes.ASM9, classWriter);

        classReader.accept(classVisitor, ClassReader.SKIP_DEBUG + ClassReader.SKIP_FRAMES);
        byte[] bytes = classWriter.toByteArray();


        Class<?> clazz = new AsmClassLoader().loadClass(bytes);
        System.out.println(clazz);
        System.out.println(clazz.newInstance());
        for (Field field : clazz.getDeclaredFields()) {
            System.out.println(field.getName());
        }
    }


    static class MyClassVisitor extends ClassVisitor {

        private boolean added;

        public MyClassVisitor(int api, ClassVisitor classVisitor) {
            super(api, classVisitor);
        }

        @Override
        public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
            super.visit(Opcodes.V1_7, access, name, signature, superName, interfaces);
        }

        @Override
        public FieldVisitor visitField(int access, String name, String descriptor, String signature, Object value) {
            return super.visitField(access, name, descriptor, signature, value);
        }

        @Override
        public void visitEnd() {
            if (!added) {
                // note 描述符记得加【;】
                FieldVisitor fv1 = super.visitField(Opcodes.ACC_PRIVATE, "name4", "Ljava/lang/String;", null, null);
                fv1.visitEnd();

                cv.visitField(Opcodes.ACC_PRIVATE, "age", "I", null, 10).visitEnd();
                added = true;
            }
            super.visitEnd();
        }
    }
}
