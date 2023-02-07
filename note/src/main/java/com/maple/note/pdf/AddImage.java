package com.maple.note.pdf;

import com.google.common.collect.Lists;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yangfeng
 * @date : 2023/2/6 9:32
 * desc:
 */

public class AddImage {


    private static int blank = 5;


    public static void main(String[] args) throws Exception {
        List<String> imageList = Lists.newArrayList("C:\\Users\\maple\\Downloads\\Temp\\aaaaa.jpg");
        ByteArrayOutputStream outputStream = addPdfMark("C:\\Users\\maple\\Downloads\\Temp\\6.24仁川老港.pdf", imageList);

        FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\maple\\Downloads\\Temp\\result.pdf");

        // 写入
        fileOutputStream.write(outputStream.toByteArray());
    }

    public static ByteArrayOutputStream addPdfMark(String InPdfFile, List<String> imgList) throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            // 获取PDF文档信息
            Map<String, Object> pdfMsg = getPdfMsg(InPdfFile);
            // 开始计算图片起始位置 * PDF文档宽度 - （所有图片的宽度 + 每张图片右侧加5 个单位的空白 ）
            float startAddress = Float.valueOf(pdfMsg.get("width").toString());
            for (String imgPath : imgList) {
                startAddress -= (Double.valueOf(getImgMsg(imgPath).get("width").toString()) + blank);
            }

            PdfReader reader = new PdfReader(InPdfFile, "PDF".getBytes());
            PdfStamper stamp = new PdfStamper(reader, byteArrayOutputStream);

            for (String imgPath : imgList) {
                Image img = Image.getInstance(imgPath);// 插入水印

                // 获取操作的页面

                // note 根据域的大小缩放图片 这一步很重要
                img.scaleToFit(20, 20);


                // 设置图片水印的位置。
                img.setAbsolutePosition(10, 10);
                // 开始水印 如果需要每一页都加图片，这里添加循环即可
                PdfContentByte pdfContentByte = stamp.getUnderContent(Integer.valueOf(pdfMsg.get("pageSize").toString()));
                pdfContentByte.addImage(img);
                startAddress += Float.valueOf(Float.valueOf(getImgMsg(imgPath).get("width").toString()) + blank);
            }

            // note 好像这个也必须关闭
            stamp.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return byteArrayOutputStream;
    }

    public static Map<String, Object> getPdfMsg(String filePath) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        try {
            // 获取PDF共有几页
            PdfReader pdfReader = new PdfReader(new FileInputStream(filePath));
            int pages = pdfReader.getNumberOfPages();
            // System.err.println(pages);
            map.put("pageSize", pages);

            // 获取PDF 的宽高
            PdfReader pdfreader = new PdfReader(filePath);
            Document document = new Document(pdfreader.getPageSize(pages));
            float widths = document.getPageSize().getWidth();
            // 获取页面高度
            float heights = document.getPageSize().getHeight();
            // System.out.println("widths = " + widths + ", heights = " + heights);
            map.put("width", widths);
            map.put("height", heights);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    public static Map<String, Object> getImgMsg(String imgPath) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        try {
            File picture = new File(imgPath);
            BufferedImage sourceImg = ImageIO.read(new FileInputStream(picture));
            // System.out.println("=源图宽度===>"+sourceImg.getWidth()); // 源图宽度
            // System.out.println("=源图高度===>"+sourceImg.getHeight()); // 源图高度
            map.put("width", sourceImg.getWidth());
            map.put("height", sourceImg.getHeight());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

}
