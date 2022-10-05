package ar.nex.factura;

import java.io.File;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author Renzo
 */
public class ExcelManage {

    private static String[] columns = {"Denominacion", "CUIT", "Fecha", "Tipo", "Letra", "Punto", "Numero", "Neto Gravado", "Neto No Gravado", "IVA", "Otro", "Total", "Origen","Codigo"};

    public void generarExcel(List<Factura> lista, String pathFile) {
        try {

            // Create a Workbook
            Workbook workbook = new XSSFWorkbook(); // new HSSFWorkbook() for generating `.xls` file

            /* CreationHelper helps us create instances of various things like DataFormat, 
           Hyperlink, RichTextString etc, in a format (HSSF, XSSF) independent way */
            CreationHelper createHelper = workbook.getCreationHelper();

            // Create a Sheet
            Sheet sheet = workbook.createSheet("Facturas_Sistema_AFIP");

            // Create a Font for styling header cells
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
//            headerFont.setFontHeightInPoints((short) 14);
//            headerFont.setColor(IndexedColors.RED.getIndex());

            // Create a CellStyle with the font
            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFont(headerFont);

            // Create a Row
            Row headerRow = sheet.createRow(0);

            // Create cells
            for (int i = 0; i < columns.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columns[i]);
                cell.setCellStyle(headerCellStyle);
            }

            // Create Cell Style for formatting Date
            CellStyle dateCellStyle = workbook.createCellStyle();
            dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));

            // Create Other rows and cells with employees data
            int rowNum = 1;
            for (Factura factura : lista) {
                Row row = sheet.createRow(rowNum++);

                row.createCell(0).setCellValue(factura.getDenominacion());
                row.createCell(1).setCellValue(factura.getCuit());
                row.createCell(2).setCellValue(factura.getFecha());
                row.createCell(3).setCellValue(factura.getTipo());
                row.createCell(4).setCellValue(factura.getLetra());
                row.createCell(5).setCellValue(factura.getPuntoComprobante());
                row.createCell(6).setCellValue(factura.getNumeroComprobante());
                row.createCell(7).setCellValue(factura.getNetoGravado());
                row.createCell(8).setCellValue(factura.getNetoNoGravado());
                row.createCell(9).setCellValue(factura.getIVA());
                row.createCell(10).setCellValue(factura.getOtroX());
                row.createCell(11).setCellValue(factura.getTotal());
                row.createCell(12).setCellValue(factura.getOrigen());
                row.createCell(13).setCellValue(factura.getCodigo());
            }

            // Resize all columns to fit the content size
            for (int i = 0; i < columns.length; i++) {
                sheet.autoSizeColumn(i);
            }

            // Write the output to a file
           //FileOutputStream fileOut = new FileOutputStream("poi-generated-file.xlsx");
              FileOutputStream fileOut = new FileOutputStream(pathFile);
            workbook.write(fileOut);
            fileOut.close();

            // Closing the workbook
            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void guardarTodo(List<Factura> lstSistema, List<Factura> lstAfip) {
        try {

            // Create a Workbook
            Workbook workbook = new XSSFWorkbook(); // new HSSFWorkbook() for generating `.xls` file

            /* CreationHelper helps us create instances of various things like DataFormat, 
           Hyperlink, RichTextString etc, in a format (HSSF, XSSF) independent way */
            CreationHelper createHelper = workbook.getCreationHelper();

            // Create a Sheet
            Sheet sheet = workbook.createSheet("Facturas");

            // Create a Font for styling header cells
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            //headerFont.setFontHeightInPoints((short) 14);
            // headerFont.setColor(IndexedColors.RED.getIndex());

            // Create a CellStyle with the font
            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFont(headerFont);

            // Create a Row
            Row headerRow = sheet.createRow(0);

            // Create cells
            for (int i = 0; i < columns.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columns[i]);
                cell.setCellStyle(headerCellStyle);
            }

            // Create Cell Style for formatting Date
            CellStyle dateCellStyle = workbook.createCellStyle();
            dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));

            // Create Other rows and cells with employees data
            int rowNum = 1;
            for (Factura factura : lstSistema) {
                Row row = sheet.createRow(rowNum++);

                row.createCell(0).setCellValue(factura.getDenominacion());
                row.createCell(1).setCellValue(factura.getCuit());
                row.createCell(2).setCellValue(factura.getFecha());
                row.createCell(3).setCellValue(factura.getTipo());
                row.createCell(4).setCellValue(factura.getLetra());
                row.createCell(5).setCellValue(factura.getPuntoComprobante());
                row.createCell(6).setCellValue(factura.getNumeroComprobante());
                row.createCell(7).setCellValue(factura.getNetoGravado());
                row.createCell(8).setCellValue(factura.getNetoNoGravado());
                row.createCell(9).setCellValue(factura.getIVA());
                row.createCell(10).setCellValue(factura.getOtroX());
                row.createCell(11).setCellValue(factura.getTotal());
                row.createCell(12).setCellValue("Sistema");

//            Cell dateOfBirthCell = row.createCell(2);
//            dateOfBirthCell.setCellValue(factura.getDateOfBirth());
//            dateOfBirthCell.setCellStyle(dateCellStyle);
//
//            row.createCell(3)
//                    .setCellValue(factura.getSalary());
            }

            for (Factura factura : lstAfip) {
                Row row = sheet.createRow(rowNum++);

                row.createCell(0).setCellValue(factura.getDenominacion());
                row.createCell(1).setCellValue(factura.getCuit());
                row.createCell(2).setCellValue(factura.getFecha());
                row.createCell(3).setCellValue(factura.getTipo());
                row.createCell(4).setCellValue(factura.getLetra());
                row.createCell(5).setCellValue(factura.getPuntoComprobante());
                row.createCell(6).setCellValue(factura.getNumeroComprobante());
                row.createCell(7).setCellValue(factura.getNetoGravado());
                row.createCell(8).setCellValue(factura.getNetoNoGravado());
                row.createCell(9).setCellValue(factura.getIVA());
                row.createCell(10).setCellValue(factura.getOtroX());
                row.createCell(11).setCellValue(factura.getTotal());
                row.createCell(12).setCellValue("AFIP");

//            Cell dateOfBirthCell = row.createCell(2);
//            dateOfBirthCell.setCellValue(factura.getDateOfBirth());
//            dateOfBirthCell.setCellStyle(dateCellStyle);
//
//            row.createCell(3)
//                    .setCellValue(factura.getSalary());
            }

            // Resize all columns to fit the content size
            for (int i = 0; i < columns.length; i++) {
                sheet.autoSizeColumn(i);
            }

            // Write the output to a file
            FileOutputStream fileOut = new FileOutputStream("C:\\Java\\poi-generated-file.xlsx");
            workbook.write(fileOut);
            fileOut.close();

            // Closing the workbook
            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    String openFile(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "TXT files (*.xls)", "*.xlsx");
        fileChooser.getExtensionFilters().add(extFilter);

        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        // Show save file dialog        
        File file = fileChooser.showOpenDialog(app_stage);
        String pathFile = "Error";
        if (file != null) {
            pathFile = file.getAbsolutePath();
        }
        return pathFile;
    }

    void guardarResultado(List<Factura> lista) throws IOException {

        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "TXT files (*.xls)", "*.xlsx");
        fileChooser.setInitialFileName("Facturas_Sistema_AFIP.xlsx");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(new Stage());
        if (file != null) {
            generarExcel(lista, file.getAbsolutePath());
        }

    }
}
