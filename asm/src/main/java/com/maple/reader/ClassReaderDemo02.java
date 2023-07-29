package com.maple.reader;

import com.maple.AsmClassLoader;
import com.maple.visitor.entity.TestClass;
import org.objectweb.asm.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author yangfeng
 * @date : 2023/7/29 15:30
 * desc:
 */

public class ClassReaderDemo02 {
    public static void main(String[] args) throws Exception {
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        ClassReader classReader = new ClassReader(TestClass.class.getName());
        ClassUpdate classVisitor = new ClassUpdate(Opcodes.ASM9, classWriter);

        {
            classVisitor.deleteFieldName = "name";
            classVisitor.deleteMethodName = "test01";
            classVisitor.addFieldName = "name5";
            classVisitor.addFieldDescriptor = "Ljava/lang/String;";
            classVisitor.addMethodName = "test02";
            classVisitor.addMethodDescriptor = "(Ljava/lang/Object;)V";
        }


        classReader.accept(classVisitor, ClassReader.SKIP_DEBUG + ClassReader.SKIP_FRAMES);

        byte[] updatedBytes = classWriter.toByteArray();
        Class<?> updatedClass = new AsmClassLoader().loadClass(updatedBytes);
        System.out.println(updatedClass);
        Object instance = updatedClass.newInstance();
        System.out.println(instance);
        System.out.println("---------------------------");
        for (Field field : updatedClass.getDeclaredFields()) {
            field.setAccessible(true);
            System.out.println("字段：" + field + " 字段值：" + field.get(instance));
        }

        for (Method method : updatedClass.getDeclaredMethods()) {
            System.out.println("方法： " + method);
        }
        System.out.println("---------------------------");
        Method test02 = updatedClass.getDeclaredMethod("test02", Object.class);
        test02.invoke(instance, new Object());

    }


    static class ClassUpdate extends ClassVisitor {

        protected String deleteFieldName;
        protected String deleteMethodName;
        protected String addFieldName;
        protected String addFieldDescriptor;
        protected String addMethodName;
        protected String addMethodDescriptor;

        protected ClassUpdate(int api, ClassVisitor classVisitor) {
            super(api, classVisitor);
        }

        @Override
        public FieldVisitor visitField(int access, String name, String descriptor, String signature, Object value) {
            // 删除字段
            if (name.equals(deleteFieldName)) {
                return null;
            }
            return super.visitField(access, name, descriptor, signature, value);
        }


        @Override
        public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
            // 删除方法
            if (name.equals(deleteMethodName)) {
                return null;
            }
            return super.visitMethod(access, name, descriptor, signature, exceptions);
        }

        @Override
        public void visitEnd() {
            // note 可能存在重复调用的风险
            // 添加字段
            super.visitField(Opcodes.ACC_PUBLIC, addFieldName, addFieldDescriptor, null, null).visitEnd();
            // 添加方法
            MethodVisitor methodVisitor = super.visitMethod(Opcodes.ACC_PUBLIC, addMethodName, addMethodDescriptor, null, null);
            methodVisitor.visitCode();
            methodVisitor.visitVarInsn(Opcodes.ALOAD, 0);
            System.out.println();
            methodVisitor.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            methodVisitor.visitLdcInsn("hello world");
            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
            methodVisitor.visitMaxs(0, 0);
            methodVisitor.visitInsn(Opcodes.RETURN);
            methodVisitor.visitEnd();
            super.visitEnd();
        }
    }
}
