package com.maple.note.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * pdf分割
 *
 * @author maple
 * @date 2023/01/04
 */
public class PdfSplit {

    public static void main(String[] args) {
        String pdfFilePath = "D:\\学习\\Typora笔记\\简历\\2022新简历.pdf";
        // 只需要第一页和第二页并覆盖之前的文件
        pdfSplitAndOverride(pdfFilePath, 1, 2);
    }

    /**
     * pdf分割
     * 产生一个新文件，不会影响原有文件
     *
     * @param pdfFile pdf文件路径
     * @param from    从那一页开始
     * @param end     到那一页结束
     */
    public static void pdfSplit(String pdfFile, Integer from, Integer end) {

        Document document = null;
        PdfCopy copy = null;
        try {
            PdfReader reader = new PdfReader(pdfFile);
            int n = reader.getNumberOfPages();
            if (end == 0) {
                end = n;
            }
            List<String> savePaths = new ArrayList<>();
            int a = pdfFile.lastIndexOf(".pdf");
            String staticPath = pdfFile.substring(0, a);
            String savePath = staticPath + "_from_" + from + "_to_" + end + "_.pdf";
            savePaths.add(savePath);
            document = new Document(reader.getPageSize(1));
            copy = new PdfCopy(document, new FileOutputStream(savePaths.get(0)));
            document.open();
            for (int j = from; j <= end; j++) {
                document.newPage();
                PdfImportedPage page = copy.getImportedPage(reader, j);
                copy.addPage(page);
            }
            document.close();

            System.out.println("pdf截取完成，保存在：" + savePath);
        } catch (IOException | DocumentException e) {
            System.out.println(e.getMessage());
        }

        throw new RuntimeException("pdf截取失败");
    }

    /**
     * pdf切割并覆盖
     * 覆盖源文件
     *
     * @param pdfFile pdf文件路径
     * @param from    从那一页开始
     * @param end     到那一页结束
     */
    public static void pdfSplitAndOverride(String pdfFile, Integer from, Integer end) {
        Document document = null;
        PdfCopy copy = null;
        ByteArrayOutputStream outputStream;
        try {
            PdfReader reader = new PdfReader(pdfFile);
            int n = reader.getNumberOfPages();
            if (end == 0) {
                end = n;
            }
            document = new Document(reader.getPageSize(1));
            outputStream = new ByteArrayOutputStream();
            copy = new PdfCopy(document, outputStream);
            document.open();
            for (int j = from; j <= end; j++) {
                document.newPage();
                PdfImportedPage page = copy.getImportedPage(reader, j);
                copy.addPage(page);
            }
            document.close();
            reader.close();
        } catch (IOException | DocumentException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

        try {
            new FileOutputStream(pdfFile).write(outputStream.toByteArray());
            System.out.println("\npdf截取完成，保存在：" + pdfFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}