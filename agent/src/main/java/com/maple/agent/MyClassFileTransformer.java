package com.maple.agent;

import jdk.internal.org.objectweb.asm.util.CheckClassAdapter;
import org.objectweb.asm.*;

import java.io.PrintWriter;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;
import java.util.List;

/**
 * @author yangfeng
 * @date : 2023/7/31 11:04
 * desc:
 */

public class MyClassFileTransformer implements ClassFileTransformer, Opcodes {

    private Instrumentation instrumentation;

    private List<String> classList;

    public MyClassFileTransformer(Instrumentation instrumentation) {
        this.instrumentation = instrumentation;
    }

    public void retransformClasses(Instrumentation instrumentation) {
        Class<?>[] allLoadedClasses = instrumentation.getAllLoadedClasses();
        //classList = Arrays.stream(allLoadedClasses).map(Class::getName).collect(Collectors.toList());
        //for (Class<?> clazz : allLoadedClasses) {
        //    try {
        //        instrumentation.retransformClasses(clazz);
        //    } catch (Throwable e) {
        //        System.out.println("不可修改类：" + clazz.getName() + " 异常信息：" + e);
        //    }
        //}
    }

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        if ("java/lang/StringBuilder".equals(className) || "java/nio/CharBuffer".equals(className) || "java/lang/StringBuffer".equals(className) || "java/lang/Throwable".equals(className)) {
            return classfileBuffer;
        }
        System.out.println("转换：" + className);
        ClassReader classReader = new ClassReader(classfileBuffer);
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        ClassVisitor classVisitor = new MyClassVisitor(classWriter, classList, classReader);

        classReader.accept(classVisitor, ClassReader.SKIP_FRAMES);
        byte[] bytes = classWriter.toByteArray();

        if ("java/lang/Object".equals(className)) {
            CheckClassAdapter.verify(new jdk.internal.org.objectweb.asm.ClassReader(bytes), false, new PrintWriter(System.out));
        }
        return bytes;

    }


    static class MyClassVisitor extends ClassVisitor {

        private String className;


        public MyClassVisitor(ClassVisitor classVisitor, List<String> classList, ClassReader classReader) {
            super(Opcodes.ASM8, classVisitor);
        }

        @Override
        public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
            this.className = name;
            super.visit(version, access, name, signature, superName, interfaces);
        }

        @Override
        public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
            // 在每个方法开始前插入代码
            MethodVisitor methodVisitor = cv.visitMethod(access, name, descriptor, signature, exceptions);
            if (!"toString".equals(name)) {
                return methodVisitor;
            }
            return new MyMethodVisitor(methodVisitor, name, className);
        }
    }

    static class MyMethodVisitor extends MethodVisitor {
        private final String methodName;
        private final String className;

        public MyMethodVisitor(MethodVisitor methodVisitor, String methodName, String className) {
            super(Opcodes.ASM8, methodVisitor);
            this.methodName = methodName;
            this.className = className;
        }

        @Override
        public void visitCode() {
            mv.visitCode();
            if ("java/lang/Object".equals(className)) {
                // 重写方法体
                //Label label0 = new Label();
                //mv.visitLabel(label0);
                //mv.visitLineNumber(236, label0);
                //mv.visitTypeInsn(NEW, "java/lang/StringBuilder");
                //mv.visitInsn(DUP);
                //mv.visitMethodInsn(INVOKESPECIAL, "java/lang/StringBuilder", "<init>", "()V", false);
                //mv.visitVarInsn(ALOAD, 0);
                //mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Object", "getClass", "()Ljava/lang/Class;", false);
                //mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Class", "getName", "()Ljava/lang/String;", false);
                //mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
                //mv.visitLdcInsn("@");
                //mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
                //mv.visitVarInsn(ALOAD, 0);
                //mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Object", "hashCode", "()I", false);
                //mv.visitMethodInsn(INVOKESTATIC, "java/lang/Integer", "toHexString", "(I)Ljava/lang/String;", false);
                //mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
                //mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "toString", "()Ljava/lang/String;", false);
                //mv.visitInsn(ARETURN);
                //mv.visitMaxs(2, 1);
                //mv.visitEnd();
                return;
            }
            if ("toString".equals(methodName)) {
                mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/Object", "toString", "()Ljava/lang/String;", false);
                mv.visitLdcInsn("增强toString："); // 添加的特定字符串
                mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/String", "concat", "(Ljava/lang/String;)Ljava/lang/String;", false);
                mv.visitInsn(Opcodes.ARETURN);
            }
            super.visitCode();
        }
    }
}
