package com.maple.note.asm;

import org.objectweb.asm.*;

public class MethodCodePrinter {
    
    public static void printMethodCode(String className, String methodName, String methodDesc) throws Exception {
        // 使用类加载器加载目标类
        ClassReader classReader = new ClassReader(className);
        
        // 定义一个 ClassVisitor，用于访问和修改字节码
        ClassVisitor classVisitor = new ClassVisitor(Opcodes.ASM7) {
            @Override
            public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
                if (name.equals(methodName) && descriptor.equals(methodDesc)) {
                    // 找到目标方法，创建一个 MethodVisitor 输出方法代码逻辑
                    return new MethodVisitor(Opcodes.ASM7) {
                        @Override
                        public void visitMethodInsn(int opcode, String owner, String name, String descriptor, boolean isInterface) {
                            // 输出方法调用指令
                            System.out.println("Method Call: " + owner + "." + name + descriptor);
                            super.visitMethodInsn(opcode, owner, name, descriptor, isInterface);
                        }

                        @Override
                        public void visitFieldInsn(int opcode, String owner, String name, String descriptor) {
                            // 输出字段访问指令
                            System.out.println("Field Access: " + owner + "." + name + descriptor);
                            super.visitFieldInsn(opcode, owner, name, descriptor);
                        }

                        @Override
                        public void visitVarInsn(int opcode, int var) {
                            // 输出变量操作指令
                            System.out.println("Variable Operation: " + opcode + " var" + var);
                            super.visitVarInsn(opcode, var);
                        }
                        
                        // 其他方法指令的输出可以根据需求进行类似的实现
                        
                    };
                }
                return super.visitMethod(access, name, descriptor, signature, exceptions);
            }
        };
        
        // 对目标类进行访问和修改
        classReader.accept(classVisitor, ClassReader.EXPAND_FRAMES);
    }

    public static void main(String[] args) throws Exception {
        printMethodCode("com.maple.note.asm.ClassBufferDemo.SourceClass", "info", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Throwable;)V");
    }
}
