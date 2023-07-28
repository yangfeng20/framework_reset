package com.maple.base;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.util.ASMifier;
import org.objectweb.asm.util.Printer;
import org.objectweb.asm.util.Textifier;
import org.objectweb.asm.util.TraceClassVisitor;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * 这里的代码是参考自{@link org.objectweb.asm.util.Printer#main(String[], String, Printer, PrintWriter, PrintWriter)}
 *
 * @author 51cto
 */
public class AsmPrint {

    public static void main(String[] args) throws IOException {
        printAsmCode(CodeSource.class);
    }

    private static void printAsmCode(Class<?> clazz) throws IOException {
        printCode(clazz, CodeType.ASM_CODE);
    }

    private static void printByteCode(Class<?> clazz) throws IOException {
        printCode(clazz, CodeType.BYTE_CODE);
    }

    private static void printCode(Class<?> clazz, CodeType codeType) throws IOException {
        if (clazz == null) {
            throw new NullPointerException();
        }

        // (1) 设置参数
        int parsingOptions = ClassReader.SKIP_FRAMES | ClassReader.SKIP_DEBUG;

        Printer printer = null;
        if (CodeType.ASM_CODE.equals(codeType)) {
            printer = new ASMifier();
        } else if (CodeType.BYTE_CODE.equals(codeType)) {
            printer = new Textifier();
        } else {
            throw new IllegalArgumentException("无效的code类型");
        }

        PrintWriter printWriter = new PrintWriter(System.out, true);
        TraceClassVisitor traceClassVisitor = new TraceClassVisitor(null, printer, printWriter);
        new ClassReader(clazz.getName()).accept(traceClassVisitor, parsingOptions);
    }


    private enum CodeType {
        /**
         * 打印的code类型
         */
        ASM_CODE,
        BYTE_CODE,
    }
}
