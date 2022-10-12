package com.maple.note.excel;

import cn.hutool.core.date.StopWatch;
import cn.hutool.core.io.IoUtil;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

/**
 * @author maple
 * desc:通过poi加Itext将excel转换为pdf
 */
public class ExcelToPdf {

    public static void main(String[] args) throws IOException, DocumentException {
        StopWatch stopWatch = new StopWatch("pdf转换");
        stopWatch.start();
        excelToPdf();
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
    }

    private static InputStream appendExcel(String path) throws IOException{
        return appendExcel(new FileInputStream(path));
    }
    
    private static InputStream appendExcel(InputStream inputStream) throws IOException{
        byte[] bytes = IoUtil.readBytes(inputStream);
        inputStream.close();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);

        // 使用poi读取excel
        XSSFWorkbook readBook = new XSSFWorkbook(byteArrayInputStream);
        XSSFSheet sheet = readBook.getSheetAt(0);
        int columnSize = sheet.getRow(0).getLastCellNum();
        int row = sheet.getPhysicalNumberOfRows();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < columnSize; j++) {
                XSSFCell cell = sheet.getRow(i).getCell(j);
                if (cell==null){
                    continue;
                }
                cell.setCellValue("测试数据");
            }
        }
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        readBook.write(outputStream);
        return IoUtil.toStream(outputStream.toByteArray());
    }

    public static void excelToPdf() throws IOException, DocumentException {
        // 创建pdf文档并指定大小
        Document document = new Document(PageSize.A4.rotate());
        // 设置字体（中文显示）
        BaseFont baseFont = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        Font font = new Font(baseFont);
        font.setSize(12);

        Font whiteFont = new Font(baseFont);
        whiteFont.setSize(12);
        whiteFont.setColor(BaseColor.WHITE);

        // 创建pdf写对象
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\maple\\Downloads\\Documents\\result.pdf"));
        document.open();

        // 使用poi读取excel
        XSSFWorkbook readBook = new XSSFWorkbook(appendExcel("C:\\Users\\maple\\Downloads\\Documents\\UPS COPY EXCEL模板.xlsx"));
        XSSFSheet sheet = readBook.getSheetAt(0);
        int columnSize = sheet.getRow(0).getLastCellNum();
        int row = sheet.getPhysicalNumberOfRows();

        PdfPTable table = new PdfPTable(columnSize);


        for (int i = 0; i < row; i++) {
            for (int j = 0; j < columnSize; j++) {
                String cellData = "";
                XSSFCell cell = sheet.getRow(i).getCell(j);
                if (cell == null) {
                    PdfPCell pdfCell = new PdfPCell();
                    pdfCell.disableBorderSide(15);
                    table.addCell(pdfCell);
                    continue;
                }

                if (CellType.NUMERIC.equals(cell.getCellType())) {
                    cellData = String.valueOf(cell.getNumericCellValue());
                } else {
                    cellData = cell.getStringCellValue();
                }

                // 创建单元格，并添加进table中
                PdfPCell pdfCell = null;
                if (isBase(i,j)) {
                    pdfCell = new PdfPCell(new Paragraph(cellData, font));
                }else {
                    pdfCell = new PdfPCell(new Paragraph(cellData, whiteFont));
                    pdfCell.setBackgroundColor(BaseColor.BLACK);
                }
                pdfCell.disableBorderSide(15);
                table.addCell(pdfCell);
            }
        }

        // 添加table数据
        document.add(table);
        // 关闭资源
        document.close();
        writer.close();
    }

    private static boolean isBase(int row, int column) {
        if (row == 3 && column == 0){
            return false;
        }
        if (row == 3 && column == 2){
            return false;
        }
        if (row == 5 && column == 1){
            return false;
        }
        if (row == 5 && column == 2){
            return false;
        }
        if (row == 7 && column == 2){
            return false;
        }
        if (row == 18 && column == 0){
            return false;
        }
        if (row == 18 && column == 2){
            return false;
        }

        return true;

    }
}

