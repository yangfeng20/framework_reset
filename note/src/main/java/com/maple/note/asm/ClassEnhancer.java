package com.maple.note.asm;

import org.objectweb.asm.*;

import java.io.FileOutputStream;
import java.lang.reflect.Method;

public class ClassEnhancer extends ClassLoader {

    public static void main(String[] args) throws Exception{
        ClassEnhancer classEnhancer = new ClassEnhancer();

        classEnhancer.enhanceInfoMethod();
    }
    public void enhanceInfoMethod() throws Exception {
        // 读取目标类的字节码文件
        ClassReader classReader = new ClassReader(ClassBufferDemo.SourceClass.class.getName());
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS);

        MethodCodePrinter.printMethodCode(ClassBufferDemo.SourceClass.class.getName(), "info", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Throwable;)V");
        System.out.println("---------------------------------");


        // 定义一个 ClassVisitor，用于访问和修改字节码
        ClassVisitor classVisitor = new ClassVisitor(Opcodes.ASM7, classWriter) {
            @Override
            public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
                if (name.equals("info")) {
                    // 对目标方法进行增强
                    return new MethodVisitor(Opcodes.ASM7, super.visitMethod(access, name, descriptor, signature, exceptions)) {
                        @Override
                        public void visitCode() {
                            super.visitCode();
                            // 找到构建 paramsMap 的操作，即 `buildParamsMap` 方法调用后的第一条指令
                            mv.visitVarInsn(Opcodes.ASTORE, 8);
                            // 在构建 paramsMap 之后插入新的指令，即添加一行代码往 paramsMap 中添加元素
                            mv.visitVarInsn(Opcodes.ALOAD, 8); // 将 paramsMap 加载到栈顶
                            mv.visitLdcInsn("extraKey"); // 将要添加的键名加载到栈顶
                            mv.visitLdcInsn("extraValue"); // 将要添加的值加载到栈顶
                            mv.visitMethodInsn(Opcodes.INVOKEINTERFACE, "java/util/Map", "put", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", true);
                            mv.visitInsn(Opcodes.POP); // 弹出方法返回值
                        }
                    };
                }
                return super.visitMethod(access, name, descriptor, signature, exceptions);
            }
        };
        
        // 对目标类进行访问和修改
        classReader.accept(classVisitor, ClassReader.EXPAND_FRAMES);

        // 获取修改后的字节码
        byte[] enhancedClassBytes = classWriter.toByteArray();

        // 将修改后的字节码写回到新的类文件
        FileOutputStream fos = new FileOutputStream("SourceClass.class");
        fos.write(enhancedClassBytes);
        fos.close();

        Class<?> aClass = this.defineClass(ClassBufferDemo.SourceClass.class.getName(), enhancedClassBytes, 0, enhancedClassBytes.length);

        // 使用修改后的类进行测试
        Class<?> enhancedClass = Class.forName(ClassBufferDemo.SourceClass.class.getName());
        Object enhancedObject = enhancedClass.getDeclaredConstructor().newInstance();
        Method infoMethod = enhancedClass.getMethod("info", String.class, String.class, String.class, String.class, String.class, Object.class, Object.class, Throwable.class);
        MethodCodePrinter.printMethodCode(ClassBufferDemo.SourceClass.class.getName(), "info", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Throwable;)V");
        System.out.println("-----------------------");
        infoMethod.invoke(enhancedObject, "interfaceName", "role", "rt", "result", "interfaceUrl", new Object(), new Object(), null);
    }
}
